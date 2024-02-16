<?php 
    header("Access-Control-Allow-Origin: * "); // Permite el acceso desde cualquier origen, o usa "http://localhost" si solo quieres permitirlo desde localhost.
    header("Access-Control-Allow-Methods: GET, POST");
    header("Access-Control-Allow-Headers: Content-Type");

    include '../Conexion.php';

    try {
        // Consulta para seleccionar personas que no tienen empresas
        $consulta = $base_de_datos->query("SELECT persona.* FROM persona
            LEFT JOIN usuario ON persona.id = usuario.id_persona
            LEFT JOIN asignacion ON usuario.id = asignacion.id_usuario
            WHERE asignacion.id_usuario IS NULL");
        $datos = $consulta->fetchAll();

        if ($datos) {
            $respuesta = [
                'status' => true,
                'registros' => $datos
            ];
        } else {
            $respuesta = [
                'status' => false,
                'message' => 'No se encontraron personas sin empresas.'
            ];
        }
    } catch (Exception $e) {
        // Manejo de excepciones
        $respuesta = [
            'status' => false,
            'message' => 'Error en el servidor: ' . $e->getMessage()
        ];
    }

    echo json_encode($respuesta);
?>
