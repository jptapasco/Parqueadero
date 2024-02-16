<?php 
    header("Access-Control-Allow-Origin: * "); // Permite el acceso desde cualquier origen, o usa "http://localhost" si solo quieres permitirlo desde localhost.
    header("Access-Control-Allow-Methods: GET, POST");
    header("Access-Control-Allow-Headers: Content-Type");

    include '../Conexion.php';

    // Modificamos la consulta para seleccionar solo las personas con asignaciones
    $consulta = $base_de_datos->query("SELECT persona.* FROM persona 
        INNER JOIN usuario ON persona.id = usuario.id_persona
        INNER JOIN asignacion ON usuario.id = asignacion.id_usuario");
    $datos = $consulta->fetchAll();

    $respuesta['registros'] = $datos;
    echo json_encode($respuesta);
?>

