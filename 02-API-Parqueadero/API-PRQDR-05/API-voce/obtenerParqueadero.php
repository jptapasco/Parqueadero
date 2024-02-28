
<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

<<<<<<< HEAD
if (!$base_de_datos) {
    echo json_encode(['error' => 'No se pudo conectar a la base de datos.']);
    exit();
}

$consulta = $base_de_datos->query(" SELECT t.id, tar.tipo_vehiculo, rv.placa, rv.responsable, tar.Tarifa, t.create_entrada FROM ticket t
    JOIN tarifas tar ON t.id_tarifa = tar.id
    JOIN registro_vehiculos rv ON t.placa = rv.placa WHERE t.salida IS NULL
");

// Verifica si la consulta se realizó correctamente
if (!$consulta) {
    echo json_encode(['error' => 'Error en la consulta: ' . implode(":", $base_de_datos->errorInfo())]);
    exit();
}

$datos = $consulta->fetchAll();

$respuesta['registros'] = $datos;
=======
if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['id_asignacion'])) {

    $id_asignacion = $_POST['id_asignacion'];

    $consulta = $base_de_datos->prepare("SELECT t.id, tar.tipo_vehiculo, rv.placa, rv.responsable, tar.Tarifa, t.create_entrada FROM ticket t
    JOIN tarifas tar ON t.id_tarifa = tar.id
    JOIN registro_vehiculos rv ON t.placa = rv.placa WHERE t.salida IS NULL AND id_asignacion = :ida");
    $consulta->bindParam(':ida', $id_asignacion);

    // Ejecutar la consulta
    $consulta->execute();

    // Verifica si la consulta se realizó correctamente
    if (!$consulta) {
        echo json_encode(['error' => 'Error en la consulta: ' . implode(":", $base_de_datos->errorInfo())]);
        exit();
    }

    $datos = $consulta->fetchAll();

    $respuesta['registros'] = $datos;

} else {
    $respuesta = [
        'status' => false,
        'message' => "Datos incompletos"
    ];
}

>>>>>>> dev01
echo json_encode($respuesta);
?>