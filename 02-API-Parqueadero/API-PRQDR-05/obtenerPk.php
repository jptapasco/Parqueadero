<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include 'Conexion.php';

if (!empty($_POST['id_parqueadero']) || !empty($_GET['id_parqueadero'])) {
    $id_parqueadero = (!empty($_POST['id_parqueadero']))? $_POST['id_parqueadero'] : $_GET['id_parqueadero'];

    try {
        $consulta = $base_de_datos->prepare("SELECT * FROM parqueadero WHERE nit =:idp");
        $consulta->bindParam(':idp', $id_parqueadero);
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
