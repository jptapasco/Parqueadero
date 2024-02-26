<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['tipo_vehiculo'])) {
    $tipo_vehiculo = $_POST['tipo_vehiculo'];

    try {
        $consulta = $base_de_datos->prepare("SELECT id FROM tarifas WHERE tipo_vehiculo = :tipo_vehiculo");
        $consulta->bindParam(':tipo_vehiculo', $tipo_vehiculo);
        $proceso = $consulta->execute();

        if ($proceso) {
            $tarifa = $consulta->fetch(PDO::FETCH_ASSOC);
            if ($tarifa) {
                $id_tarifa = $tarifa['id'];
                $respuesta = [
                    'success' => true,
                    'message' => "ID de tarifa obtenido exitosamente",
                    'id_tarifa' => $id_tarifa
                ];
            } else {
                $respuesta = [
                    'success' => false,
                    'message' => "No se encontró una tarifa para el tipo de vehículo proporcionado"
                ];
            }
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
