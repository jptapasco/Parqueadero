<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");
header('Content-Type: application/json');

include 'Conexion.php';

if (!empty($_POST['placa']) || !empty($_GET['placa'])) {
    $placa = (!empty($_POST['placa']))? $_POST['placa'] : $_GET['placa'];

    if ($placa !== null) {
        try {
            $consulta = $base_de_datos->prepare("SELECT id, id_asignacion, id_tarifa, create_entrada,salida FROM ticket WHERE placa = :pla AND salida IS NULL");
            $consulta->bindParam(':pla', $placa);
            $consulta->execute();
            $resultado = $consulta->fetch(PDO::FETCH_ASSOC);

            if ($resultado) {
                $respuesta = [
                    'status' => true,
                    'resultado' => $resultado,
                    'message' => "Datos extraídos correctamente"
                ];
            } else {
                $respuesta = [
                    'status' => false,
                    'message' => "No se encontraron datos para la placa proporcionada"
                ];
            }
        } catch (Exception $e) {
            $respuesta = [
                'status' => false,
                'message' => "Ocurrió un error al procesar la solicitud",
                'exception' => $e->getMessage()
            ];
        }
    } else {
        $respuesta = [
            'status' => false,
            'message' => "Datos incompletos. Por favor, proporcione una placa"
        ];
    }
} else {
    $respuesta = [
        'status' => false,
        'message' => "Método HTTP no permitido. Por favor, use POST o GET"
    ];
}

echo json_encode($respuesta);
?>
