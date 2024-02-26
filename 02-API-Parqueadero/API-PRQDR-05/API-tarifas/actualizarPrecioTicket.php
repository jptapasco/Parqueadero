<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['placa']) && isset($_POST['precio'])) {
    // Obtén los valores de placa y precio de la solicitud POST
    $placa = $_POST['placa'];
    $nuevoPrecio = $_POST['precio'];
    $horaSalida = date("Y-m-d H:i:s"); // Obtiene la fecha y hora actual

    try {
        // Actualiza el precio y la hora de salida del ticket en la base de datos
        $consulta = $base_de_datos->prepare("UPDATE ticket SET total = :nuevoPrecio, salida = :horaSalida WHERE placa = :placa");
        $consulta->bindParam(':nuevoPrecio', $nuevoPrecio);
        $consulta->bindParam(':horaSalida', $horaSalida);
        $consulta->bindParam(':placa', $placa);

        $proceso = $consulta->execute();

        if ($proceso) {
            $respuesta = [
                'status' => true,
                'message' => "El precio y la hora de salida del ticket se actualizaron correctamente."
            ];
        } else {
            $respuesta = [
                'status' => false,
                'message' => "Error al actualizar el ticket."
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
