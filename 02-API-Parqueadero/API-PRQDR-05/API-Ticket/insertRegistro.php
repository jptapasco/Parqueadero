<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['placa']) && isset($_POST['responsable'])) {
    $placa = $_POST['placa'];
    $responsable = $_POST['responsable'];

    try {
        // Insertar un nuevo registro en la tabla de registro_vehiculos
        $consulta = $base_de_datos->prepare("INSERT INTO registro_vehiculos (placa, responsable) VALUES (:placa, :responsable)");
        $consulta->bindParam(':placa', $placa);
        $consulta->bindParam(':responsable', $responsable);

        $proceso = $consulta->execute();

        if ($proceso) {
            $respuesta = [
                'status' => true,
                'message' => "Registro de vehículo insertado exitosamente"
            ];
        } else {
            $respuesta = [
                'status' => false,
                'message' => "Error al insertar el registro de vehículo"
            ];
        }
    } catch (Exception $e) {
        $respuesta = [
            'status' => false,
            'message' => "Error al insertar el registro de vehículo",
            'exception' => $e
        ];
    }
} else {
    $respuesta = [
        'status' => false,
        'message' => "Datos incompletos"
    ];
}

echo json_encode($respuesta);
?>
