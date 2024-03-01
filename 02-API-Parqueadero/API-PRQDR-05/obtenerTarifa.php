<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");
header('Content-Type: application/json');

include 'Conexion.php';

if (!empty($_POST['id_tarifa']) || !empty($_GET['id_tarifa'])) {
    $id_tarifa = (!empty($_POST['id_tarifa']))? $_POST['id_tarifa'] : $_GET['id_tarifa'];

    if ($id_tarifa !== null) {
        try {
            $consulta = $base_de_datos->prepare("SELECT tipo_vehiculo ,Tarifa FROM tarifas WHERE id = :idt");
            $consulta->bindParam(':idt', $id_tarifa);
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
                    'message' => "No se encontraron datos para la tarifa proporcionada"
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
