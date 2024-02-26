<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if (!empty($_POST['cedula'])) {
        $cedula = $_POST['cedula'];

        try {
            // Consulta para obtener el ID de la persona por cédula
            $consultaPersona = $base_de_datos->prepare("SELECT * FROM persona WHERE cedula = :cedula");
            $consultaPersona->bindParam(':cedula', $cedula);
            $consultaPersona->execute();
            $resultadoPersona = $consultaPersona->fetch(PDO::FETCH_ASSOC);
        
            if ($resultadoPersona) {
                // Obtén el ID de la persona
                $idPersona = $resultadoPersona['id'];
        
                // Consulta para obtener el ID del usuario por ID de persona
                $consultaUsuario = $base_de_datos->prepare("SELECT id FROM usuario WHERE id_persona = :idPersona");
                $consultaUsuario->bindParam(':idPersona', $idPersona);
                $consultaUsuario->execute();
                $resultadoUsuario = $consultaUsuario->fetch(PDO::FETCH_ASSOC);
        
                if ($resultadoUsuario) {
                    // Obtén el ID del usuario
                    $idUsuario = $resultadoUsuario['id'];
        
                    // Consulta para obtener el ID del parqueadero por nombre
                    $consultaParqueadero = $base_de_datos->prepare("SELECT nit FROM parqueadero WHERE nombre = :parqueadero");
                    $consultaParqueadero->bindParam(':parqueadero', $parqueadero);
                    $consultaParqueadero->execute();
                    $resultadoParqueadero = $consultaParqueadero->fetch(PDO::FETCH_ASSOC);
        
                    if ($resultadoParqueadero) {
                        // Obtén el ID del parqueadero
                        $idParqueadero = $resultadoParqueadero['nit'];
        
                        // Ahora, puedes insertar una fila en la tabla de asignación
                        $consultaAsociacion = $base_de_datos->prepare("INSERT INTO asignacion (id_usuario, id_parqueadero) VALUES (:idUsuario, :idParqueadero)");
                        $consultaAsociacion->bindParam(':idUsuario', $idUsuario);
                        $consultaAsociacion->bindParam(':idParqueadero', $idParqueadero);
                        $consultaAsociacion->execute();
        
                        $respuesta = [
                            'status' => true,
'                            message' => "Persona asociada al parqueadero exitosamente"
                        ];
                        echo json_encode($respuesta);
                    } else {
                        $respuesta = [
                            'status' => false,
                            'message' => "Parqueadero no encontrado"
                        ];
                        echo json_encode($respuesta);
                    }
                } else {
                    $respuesta = [
                        'status' => false,
                        'message' => "Usuario no encontrado"
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
            'message' => "No se proporcionó una cédula"
        ];
        echo json_encode($respuesta);
    }
} else {
    $respuesta = [
        'status' => false,
        'message' => "No se recibió una solicitud POST"
    ];
    echo json_encode($respuesta);
}
?>
