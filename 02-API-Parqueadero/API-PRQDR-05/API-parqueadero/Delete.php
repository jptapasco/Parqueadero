<?php 
// Habilitar el acceso a la API desde cualquier origen
header("Access-Control-Allow-Origin: *");
// Permitir los métodos GET y POST
header("Access-Control-Allow-Methods: GET, POST");
// Permitir el encabezado Content-Type
header("Access-Control-Allow-Headers: Content-Type");

// Incluir el archivo de conexión a la base de datos
include '../Conexion.php';

// Verificar si se ha recibido el valor de "nit" en la solicitud POST
if (!empty($_POST['nit'])) {
    $nit = $_POST['nit'];

    try {
        // Preparar una consulta para eliminar el registro con el 'nit' proporcionado
        $consulta = $base_de_datos->prepare("DELETE FROM parqueadero WHERE nit = :nit ");
        $consulta->bindParam(':nit', $nit);

        // Ejecutar la consulta de eliminación
        $proceso = $consulta->execute();

        if ($proceso) {
            // Si la eliminación se realizó con éxito, devolver una respuesta JSON positiva
            $respuesta = [
                'status' => true,
                'message' => "OK##DELETE"
            ];
            echo json_encode($respuesta);
        } else {
            // Si hubo un error durante la eliminación, devolver una respuesta JSON de error
            $respuesta = [
                'status' => false,
                'message' => "ERROR##DELETE"
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
    // Si no se recibió el valor de "nit" en la solicitud POST, devolver una respuesta JSON de error
    $respuesta = [
        'status' => false,
        'message' => "ERROR##DATOS##POST"
    ];
    echo json_encode($respuesta);
}
?>
