<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['placa']) && isset($_POST['id_asignacion']) && isset($_POST['id_tarifa'])) {
    $placa = $_POST['placa'];
    $id_asignacion = $_POST['id_asignacion'];
    $id_tarifa = $_POST['id_tarifa'];

    try {
        // Insertar un nuevo registro en la tabla de tickets
        $consulta = $base_de_datos->prepare("INSERT INTO ticket (placa, id_asignacion, id_tarifa) VALUES (:placa, :id_asignacion, :id_tarifa)");
        $consulta->bindParam(':placa', $placa);
        $consulta->bindParam(':id_asignacion', $id_asignacion);
        $consulta->bindParam(':id_tarifa', $id_tarifa);

        $proceso = $consulta->execute();

        if ($proceso) {
            $respuesta = [
                'status' => true,
                'message' => "Registro de ticket insertado exitosamente"
            ];
        } else {
            $respuesta = [
                'status' => false,
                'message' => "Error al insertar el registro de ticket"
            ];
        }
    } catch (Exception $e) {
        $respuesta = [
            'status' => false,
            'message' => "Error al insertar el registro de ticket",
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
