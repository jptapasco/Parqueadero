<?php
// Habilitar el acceso a la API desde cualquier origen
header("Access-Control-Allow-Origin: *");
// Permitir los métodos GET y POST
header("Access-Control-Allow-Methods: GET, POST");
// Permitir el encabezado Content-Type
header("Access-Control-Allow-Headers: Content-Type");

// Incluir el archivo de conexión a la base de datos
include '../Conexion.php';

// Verificar si se han recibido todos los datos esperados en la solicitud POST
if (!empty($_POST['nit']) && !empty($_POST['nombre']) && !empty($_POST['direccion']) && !empty($_POST['telefono'])) {
    // Obtener los datos de la solicitud POST
    $nit = $_POST['nit'];
    $nombre = $_POST['nombre'];
    $direccion = $_POST['direccion'];
    $telefono = $_POST['telefono'];

    try {
        // Actualizar los datos basados en el 'nit'
        $consulta = $base_de_datos->prepare("UPDATE parqueadero SET nombre=:nom, direccion=:dir, telefono=:tel WHERE nit = :nit ");
        $consulta->bindParam(':nom', $nombre);
        $consulta->bindParam(':dir', $direccion);
        $consulta->bindParam(':tel', $telefono);
        $consulta->bindParam(':nit', $nit);

        // Ejecutar la actualización
        $proceso = $consulta->execute();

        if ($proceso) {
            // Si la actualización se realizó con éxito, devolver una respuesta JSON positiva
            $respuesta = [
                'status' => true,
                'message' => "OK##PARQUEADERO##UPDATE"
            ];
            echo json_encode($respuesta);
        } else {
            // Si hubo un error durante la actualización, devolver una respuesta JSON de error
            $respuesta = [
                'status' => false,
                'message' => "ERROR##PARQUEADERO##UPDATE"
            ];
            echo json_encode($respuesta);
        }
    } catch (Exception $e) {
        // Si hubo una excepción durante la ejecución, devolver una respuesta JSON de error junto con detalles de la excepción
        $respuesta = [
            'status' => false,
            'message' => "ERROR##SQL",
            'exception' => $e->getMessage()
        ];
        echo json_encode($respuesta);
    }
} else {
    // Si no se recibieron todos los datos esperados en la solicitud POST, devolver una respuesta JSON de error
    $respuesta = [
        'status' => false,
        'message' => "ERROR##DATOS##POST"
    ];
    echo json_encode($respuesta);
}
?>
