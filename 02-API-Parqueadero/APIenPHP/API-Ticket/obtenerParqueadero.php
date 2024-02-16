<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['id_usuario'])) {
    $id_usuario = $_POST['id_usuario'];

    try {
        $consulta = $base_de_datos->prepare("SELECT parqueadero.nit, parqueadero.nombre, parqueadero.direccion, parqueadero.telefono, 
        COUNT(asignacion.id_usuario) as num_vendedores
        FROM parqueadero
        INNER JOIN asignacion ON parqueadero.nit = asignacion.id_parqueadero
        WHERE asignacion.id_usuario = :id_usuario
        GROUP BY parqueadero.nit, parqueadero.nombre, parqueadero.direccion, parqueadero.telefono");
        $consulta->bindParam(':id_usuario', $id_usuario);
        $proceso = $consulta->execute();

        if ($proceso) {
            $parqueaderos = $consulta->fetchAll(PDO::FETCH_ASSOC);
            $respuesta = [
                'success' => true,
                'message' => "Parqueaderos obtenidos exitosamente",
                'parqueaderos' => $parqueaderos
            ];
        } else {
            $respuesta = [
                'success' => false,
                'message' => "Error en la consulta"
            ];
        }
    } catch (Exception $e) {
        $respuesta = [
            'success' => false,
            'message' => "Error en la consulta",
            'exception' => $e
        ];
    }
} else {
    $respuesta = [
        'success' => false,
        'message' => "Datos incompletos"
    ];
}

echo json_encode($respuesta);
?>
