<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php'; 

if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['tipo_vehiculo']) && isset($_POST['tarifa'])) {
    $tipo_vehiculo = $_POST['tipo_vehiculo'];
    $tarifa = $_POST['tarifa'];

    try {
        // Actualizar la tarifa del tipo de vehículo en la base de datos
        $consulta = $base_de_datos->prepare("UPDATE tarifas SET tarifa = :tarifa WHERE tipo_vehiculo = :tipo_vehiculo");
        $consulta->bindParam(':tarifa', $tarifa);
        $consulta->bindParam(':tipo_vehiculo', $tipo_vehiculo);

        $proceso = $consulta->execute();

        if ($proceso) {
            $respuesta = [
                'status' => true,
                'message' => "La tarifa se actualizó correctamente."
            ];
        } else {
            $respuesta = [
                'status' => false,
                'message' => "Error al actualizar la tarifa."
            ];
        }
    } catch (Exception $e) {
        $respuesta = [
            'status' => false,
            'message' => "Error en la consulta SQL.",
            'exception' => $e
        ];
    }
} else {
    $respuesta = [
        'status' => false,
        'message' => "Datos de actualización incompletos."
    ];
}

echo json_encode($respuesta);
?>
