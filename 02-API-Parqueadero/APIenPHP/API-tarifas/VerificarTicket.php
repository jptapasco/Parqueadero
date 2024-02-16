<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET' && isset($_GET['placa'])) {
    $placa = $_GET['placa'];

    try {
        // Realizar una consulta SQL para buscar un ticket en base a la placa
        $consulta = $base_de_datos->prepare("SELECT * FROM ticket WHERE placa = :placa");
        $consulta->bindParam(':placa', $placa);
        $consulta->execute();

        $ticket = $consulta->fetch(PDO::FETCH_ASSOC);

        if ($ticket) {
            echo json_encode($ticket);
        } else {
            echo json_encode([
                'status' => false,
                'message' => "No se encontró un ticket para la placa proporcionada"
            ]);
        }
    } catch (Exception $e) {
        echo json_encode([
            'status' => false,
            'message' => "Error al buscar el ticket",
            'exception' => $e
        ]);
    }
} else {
    echo json_encode([
        'status' => false,
        'message' => "Parámetros incompletos"
    ]);
}
?>
