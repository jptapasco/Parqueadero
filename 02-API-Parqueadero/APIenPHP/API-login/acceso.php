<?php 
    header("Access-Control-Allow-Origin: *");
    header("Access-Control-Allow-Methods: GET, POST");
    header("Access-Control-Allow-Headers: Content-Type");
    
    include '../Conexion.php';

    if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['correo']) && isset($_POST['contrasena'])) {
        $correo = $_POST['correo'];
        $contrasena = $_POST['contrasena'];

        try {
            $consulta = $base_de_datos->prepare("SELECT * FROM usuario WHERE email = :cor AND contrasenia = :con ");
            $consulta->bindParam(':cor', $correo);
            $consulta->bindParam(':con', $contrasena);
            $proceso = $consulta->execute();

            if ($proceso) {
                $usuario = $consulta->fetch(PDO::FETCH_ASSOC);
                $respuesta = [
                    'success' => true,
                    'message' => "Inicio de sesión exitoso",
                    'user' => $usuario
                ];
            } else {
                $respuesta = [
                    'success' => false,
                    'message' => "Error en la autenticación"
                ];
            }
        } catch (Exception $e) {
            $respuesta = [
                'success' => false,
                'message' => "Error en la consulta",
                'exception' => $e
            ];
        }
    } else {
        $respuesta = [
            'success' => false,
            'message' => "Datos de inicio de sesión incompletos"
        ];
    }
    echo json_encode($respuesta);
?>
