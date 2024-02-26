<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if (!empty($_POST['nombre']) && !empty($_POST['apellidos']) && !empty($_POST['cedula']) && !empty($_POST['celular']) && !empty($_POST['email']) && !empty($_POST['contrasenia']) && !empty($_POST['parqueadero'])) {
        $nombre = $_POST['nombre'];
        $apellidos = $_POST['apellidos'];
        $cedula = $_POST['cedula'];
        $celular = $_POST['celular'];
        $email = $_POST['email'];
        $contrasenia = $_POST['contrasenia'];
        $parqueaderoNombre = $_POST['parqueadero'];

        try {
            // Consulta para obtener el ID del parqueadero por nombre
            $consultaParqueadero = $base_de_datos->prepare("SELECT nit FROM parqueadero WHERE nombre = :nombre");
            $consultaParqueadero->bindParam(':nombre', $parqueaderoNombre);
            $consultaParqueadero->execute();
            $resultadoParqueadero = $consultaParqueadero->fetch(PDO::FETCH_ASSOC);

            if (!$resultadoParqueadero) {
                $respuesta = [
                    'status' => false,
                    'message' => "Parqueadero no encontrado"
                ];
                echo json_encode($respuesta);
            } else {
                $idParqueadero = $resultadoParqueadero['nit'];

                $consulta = $base_de_datos->prepare("INSERT INTO persona (nombre, apellidos, cedula, celular) VALUES(:nom, :ape, :doc, :tel)");

                $consulta->bindParam(':nom', $nombre);
                $consulta->bindParam(':ape', $apellidos);
                $consulta->bindParam(':doc', $cedula);
                $consulta->bindParam(':tel', $celular);

                $proceso = $consulta->execute();

                if ($proceso) {
                    // Insertar datos en la tabla 'usuario' relacionados con la persona y el parqueadero
                    $idPersona = $base_de_datos->lastInsertId();

                    $consultaUsuario = $base_de_datos->prepare("INSERT INTO usuario (id_persona, email, contrasenia, tipo, estado) VALUES(:id, :email, :contrasenia, 'VENDEDOR', 'ACTIVO')");
                    $consultaUsuario->bindParam(':id', $idPersona);
                    $consultaUsuario->bindParam(':email', $email);
                    $consultaUsuario->bindParam(':contrasenia', $contrasenia);

                    $procesoUsuario = $consultaUsuario->execute();

                    if ($procesoUsuario) {
                        $idUsuario = $base_de_datos->lastInsertId(); // Obtener el ID del usuario recién insertado

                        // Insertar en la tabla de asignación
                        $consultaAsignacion = $base_de_datos->prepare("INSERT INTO asignacion (id_parqueadero, id_usuario) VALUES(:id_parqueadero, :id_usuario)");
                        $consultaAsignacion->bindParam(':id_parqueadero', $idParqueadero);
                        $consultaAsignacion->bindParam(':id_usuario', $idUsuario);
                        $procesoAsignacion = $consultaAsignacion->execute();

                        if ($procesoAsignacion) {
                            $respuesta = [
                                'status' => true,
                                'message' => "Registro exitoso"
                            ];
                            echo json_encode($respuesta);
                        } else {
                            $respuesta = [
                                'status' => false,
                                'message' => "Error al registrar asignación"
                            ];
                            echo json_encode($respuesta);
                        }
                    } else {
                        $respuesta = [
                            'status' => false,
                            'message' => "Error al registrar usuario"
                        ];
                        echo json_encode($respuesta);
                    }
                } else {
                    $respuesta = [
                        'status' => false,
                        'message' => "Error al registrar persona"
                    ];
                    echo json_encode($respuesta);
                }
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
