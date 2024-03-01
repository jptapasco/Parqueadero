<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");
header('Content-Type: application/json');

include 'Conexion.php';

if (!empty($_POST['id_asignacion']) || !empty($_GET['id_asignacion'])) {
    $id_asignacion = (!empty($_POST['id_asignacion']))? $_POST['id_asignacion'] : $_GET['id_asignacion'];

    if ($id_asignacion !== null) {
        try {
            $consulta = $base_de_datos->prepare("SELECT id_parqueadero, id_usuario FROM asignacion WHERE id = :ida");
            $consulta->bindParam(':ida', $id_asignacion);
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
                    'message' => "No se encontraron datos para la asignacion proporcionada"
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
            'message' => "Datos incompletos. Por favor, proporcione un id"
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
