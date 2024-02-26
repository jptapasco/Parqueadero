<?php 
    header("Access-Control-Allow-Origin: * "); // Permite el acceso desde cualquier origen, o usa "http://localhost" si solo quieres permitirlo desde localhost.
    header("Access-Control-Allow-Methods: GET, POST");
    header("Access-Control-Allow-Headers: Content-Type");

    include '../Conexion.php';

    if (!empty($_POST['cedula'])) {
        $cedula = $_POST['cedula'];

        try {
            // Buscar el ID de la persona utilizando la cédula
            $consulta_persona = $base_de_datos->prepare("SELECT id FROM persona WHERE cedula = :cedula");
            $consulta_persona->bindParam(':cedula', $cedula);
            $consulta_persona->execute();
            $resultado_persona = $consulta_persona->fetch();

            if ($resultado_persona) {
                $id_persona = $resultado_persona['id'];

                // Buscar el ID del usuario correspondiente a la persona
                $consulta_usuario = $base_de_datos->prepare("SELECT id FROM usuario WHERE id_persona = :id_persona");
                $consulta_usuario->bindParam(':id_persona', $id_persona);
                $consulta_usuario->execute();
                $resultado_usuario = $consulta_usuario->fetch();

                if ($resultado_usuario) {
                    $id_usuario = $resultado_usuario['id'];

                    // Eliminar la asignación del usuario con el parqueadero
                    $consulta_eliminar = $base_de_datos->prepare("DELETE FROM asignacion WHERE id_usuario = :id_usuario");
                    $consulta_eliminar->bindParam(':id_usuario', $id_usuario);
                    $consulta_eliminar->execute();

                    // Respuesta de éxito
                    $respuesta = [
                        'status' => true,
                        'message' => 'Persona desligada del parqueadero exitosamente.'
                    ];
                } else {
                    // Respuesta si no se encuentra un usuario asociado a la persona
                    $respuesta = [
                        'status' => false,
                        'message' => 'No se encontró ningún usuario asociado a la persona.'
                    ];
                }
            } else {
                // Respuesta si no se encuentra una persona con la cédula proporcionada
                $respuesta = [
                    'status' => false,
                    'message' => 'No se encontró ninguna persona con la cédula proporcionada.'
                ];
            }

            echo json_encode($respuesta);
        } catch (Exception $e) {
            // Manejo de excepciones
            $respuesta = [
                'status' => false,
                'message' => 'Error en el servidor: ' . $e->getMessage()
            ];
            echo json_encode($respuesta);
        }
    } else {
        // Respuesta si no se proporcionó una cédula
        $respuesta = [
            'status' => false,
            'message' => 'No se proporcionó una cédula.'
        ];
        echo json_encode($respuesta);
    }
?>
