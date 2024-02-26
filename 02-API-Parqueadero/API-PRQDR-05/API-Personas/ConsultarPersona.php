<?php 
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if (!empty($_POST['cedula'])) {
        $cedula = $_POST['cedula'];

        try {
            // Consulta para obtener los datos de la persona por cédula
            $consultaPersona = $base_de_datos->prepare("SELECT * FROM persona WHERE cedula = :cedula");
            $consultaPersona->bindParam(':cedula', $cedula);
            $consultaPersona->execute();
            $resultadoPersona = $consultaPersona->fetch(PDO::FETCH_ASSOC);

            if ($resultadoPersona) {
                // Obtener el ID de la persona
                $idPersona = $resultadoPersona['id'];

                // Consulta para obtener los datos del usuario asociados a la persona
                $consultaUsuario = $base_de_datos->prepare("SELECT * FROM usuario WHERE id_persona = :idPersona");
                $consultaUsuario->bindParam(':idPersona', $idPersona);
                $consultaUsuario->execute();
                $resultadoUsuario = $consultaUsuario->fetch(PDO::FETCH_ASSOC);

                // Verifica si hay asociación con un usuario
                if ($resultadoUsuario) {
                    // Obtener el ID de usuario
                    $idUsuario = $resultadoUsuario['id'];

                    // Consulta para obtener la parqueadero asociada al usuario
                    $consultaAsignacion = $base_de_datos->prepare("SELECT * FROM asignacion WHERE id_usuario = :idUsuario");
                    $consultaAsignacion->bindParam(':idUsuario', $idUsuario);
                    $consultaAsignacion->execute();
                    $resultadoAsignacion = $consultaAsignacion->fetch(PDO::FETCH_ASSOC);

                    if ($resultadoAsignacion) {
                        $idParqueaderoAsociado = $resultadoAsignacion['id_parqueadero'];

                        // Consulta para obtener los datos de la parqueadero asociada
                        $consultaparqueadero = $base_de_datos->prepare("SELECT * FROM parqueadero WHERE nit = :idParqueaderoAsociado");
                        $consultaparqueadero->bindParam(':idParqueaderoAsociado', $idParqueaderoAsociado);
                        $consultaparqueadero->execute();
                        $resultadoparqueadero = $consultaparqueadero->fetch(PDO::FETCH_ASSOC);

                        $respuesta = [
                            'status' => true,
                            'persona' => $resultadoPersona,
                            'usuario' => $resultadoUsuario,
                            'parqueadero' => $resultadoparqueadero
                        ];
                        echo json_encode($respuesta);
                    } else {
                        $respuesta = [
                            'status' => true,
                            'persona' => $resultadoPersona,
                            'usuario' => $resultadoUsuario,
                            'message' => "Usuario no está asociado a ninguna parqueadero"
                        ];
                        echo json_encode($respuesta);
                    }
                } else {
                    $respuesta = [
                        'status' => true,
                        'persona' => $resultadoPersona,
                        'usuario' => false
                    ];
                    echo json_encode($respuesta);
                }
            } else {
                $respuesta = [
                    'status' => false,
                    'message' => "Persona no encontrada"
                ];
                echo json_encode($respuesta);
            }
        } catch (Exception $e) {
            $respuesta = [
                'status' => false,
                'message' => "Error en la base de datos",
                'exception' => $e->getMessage()
            ];
            echo json_encode($respuesta);
        }
    } else {
        $respuesta = [
            'status' => false,
            'message' => "Faltan campos requeridos en el formulario"
        ];
        echo json_encode($respuesta);
    }
} else {
    $respuesta = [
        'status' => false,
        'message' => "Método no permitido, use POST"
    ];
    echo json_encode($respuesta);
}
?>
