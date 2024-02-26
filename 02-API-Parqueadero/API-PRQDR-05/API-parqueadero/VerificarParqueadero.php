<?php
header("Access-Control-Allow-Origin: * ");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");
include '../Conexion.php';

if (!empty($_POST['nit'])) {
    $nit = $_POST['nit'];

    try {
        $consulta = $base_de_datos->prepare("SELECT * FROM parqueadero WHERE nit = :nit");
        $consulta->bindParam(':nit', $nit);
        $consulta->execute();

        if ($consulta->rowCount() > 0) {
            $registros = $consulta->fetchAll(PDO::FETCH_ASSOC);
            $respuesta = [
                'status' => true,
                'registros' => $registros
            ];
            echo json_encode($respuesta);
        } else {
            $respuesta = [
                'status' => false,
                'message' => "No se encontraron resultados para los valores proporcionados en nit o nombre."
            ];
            echo json_encode($respuesta);
        }
    } catch (Exception $e) {
        $respuesta = [
            'status' => false,
            'message' => "Error en la consulta SQL: " . $e->getMessage()
        ];
        echo json_encode($respuesta);
    }
} else {
    $respuesta = [
        'status' => false,
        'message' => "Los campos 'nit' y 'nombre' en la solicitud POST están vacíos."
    ];
    echo json_encode($respuesta);
}
?>
