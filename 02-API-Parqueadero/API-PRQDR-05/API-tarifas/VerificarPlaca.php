<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['placa'])) {
    $placa = $_POST['placa'];

    try {
        $consulta = $base_de_datos->prepare("SELECT ticket.*, registro_vehiculos.responsable, tarifas.tipo_vehiculo
            FROM ticket
            LEFT JOIN registro_vehiculos ON ticket.placa = registro_vehiculos.placa
            LEFT JOIN tarifas ON ticket.id_tarifa = tarifas.id
            WHERE ticket.placa = :placa");
        $consulta->bindParam(':placa', $placa);
        $proceso = $consulta->execute();

        if ($proceso) {
            $datosTicket = $consulta->fetch(PDO::FETCH_ASSOC);
            if ($datosTicket) {
                $respuesta = [
                    'status' => true,
                    'message' => "La placa ya existe en la tabla de tickets",
                    'responsable' => $datosTicket['responsable'],
                    'tipo_vehiculo' => $datosTicket['tipo_vehiculo']
                ];
            } else {
                $respuesta = [
                    'status' => false,
                    'message' => "La placa no existe en la tabla de tickets"
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
