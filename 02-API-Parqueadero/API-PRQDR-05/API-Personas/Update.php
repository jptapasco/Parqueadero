<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $cedula = $_POST['cedula'];
    $nombre = $_POST['nombre'];
    $apellidos = $_POST['apellidos'];
    $celular = $_POST['celular'];
    $email = $_POST['email'];
    $contrasenia = $_POST['contrasenia'];

    try {
        $base_de_datos->beginTransaction();

        // Actualizar datos en la tabla 'persona'
        $consultaPersona = $base_de_datos->prepare("UPDATE persona SET nombre = :nombre, apellidos = :apellidos, celular = :celular WHERE cedula = :cedula");
        $consultaPersona->bindParam(':nombre', $nombre);
        $consultaPersona->bindParam(':apellidos', $apellidos);
        $consultaPersona->bindParam(':celular', $celular);
        $consultaPersona->bindParam(':cedula', $cedula);
        $consultaPersona->execute();

        // Actualizar datos en la tabla 'usuarios'
        $consultaUsuarios = $base_de_datos->prepare("UPDATE usuario SET email = :email, contrasenia = :contrasenia WHERE email = :email");
        $consultaUsuarios->bindParam(':email', $email);
        $consultaUsuarios->bindParam(':contrasenia', $contrasenia);
        $consultaUsuarios->execute();

        $base_de_datos->commit();

        $respuesta = [
            'status' => true,
            'message' => "Datos actualizados exitosamente"
        ];
        echo json_encode($respuesta);
    } catch (Exception $e) {
        $base_de_datos->rollBack();
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
        'message' => "Método no permitido, use POST"
    ];
    echo json_encode($respuesta);
}
?>
