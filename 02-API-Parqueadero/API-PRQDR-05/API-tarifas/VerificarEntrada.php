<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['placa'])) {
    $placa = $_POST['placa'];

    try {
        $consulta = $base_de_datos->prepare("SELECT * FROM ticket WHERE ticket.placa = :placa AND ticket.salida IS NULL");
        $consulta->bindParam(':placa', $placa);
        $proceso = $consulta->execute();

        if ($proceso) {
            $datosTicket = $consulta->fetch(PDO::FETCH_ASSOC);
            if ($datosTicket) {
                $respuesta = [
                    'status' => true,
                    'message' => "El vehiculo esta en el parqueadero y NO ha salio",
                ];
            } else {
                $respuesta = [
                    'status' => false,
                    'message' => "La placa no esta en parqueadero y no ha ingresado"
                ];
            }
        } else {
            $respuesta = [
                'status' => false,
                'message' => "Error en la consulta"
            ];
        }
    } catch (Exception $e) {
        $respuesta = [
            'status' => false,
            'message' => "Error en la consulta",
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
