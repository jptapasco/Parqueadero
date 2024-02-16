<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['id_usuario'])) {
    $id_usuario = $_POST['id_usuario'];

    try {
        $consulta = $base_de_datos->prepare("SELECT id FROM asignacion WHERE id_usuario = :id_usuario");
        $consulta->bindParam(':id_usuario', $id_usuario);
        $proceso = $consulta->execute();

        if ($proceso) {
            $asignacion = $consulta->fetch(PDO::FETCH_ASSOC);
            if ($asignacion) {
                $id_asignacion = $asignacion['id'];
                $respuesta = [
                    'status' => true,
                    'message' => "ID de asignación obtenido exitosamente",
                    'id_asignacion' => $id_asignacion
                ];
            } else {
                $respuesta = [
                    'status' => false,
                    'message' => "El usuario no tiene una asignación asociada"
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
