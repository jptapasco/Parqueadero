<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['id_asignacion'])) {
    $id_asignacion = $_POST['id_asignacion'];

    $consulta = $base_de_datos->prepare("SELECT t.id, t.placa, t.create_entrada, t.salida, tar.tipo_vehiculo, tar.Tarifa, rv.responsable
        FROM ticket t
        LEFT JOIN tarifas tar ON t.id_tarifa = tar.id
        LEFT JOIN registro_vehiculos rv ON t.placa = rv.placa WHERE t.salida IS NOT NULL AND id_asignacion = :ida");
    $consulta->bindParam(':ida', $id_asignacion);
    
    // Ejecutar la consulta
    $consulta->execute();
    
    // Obtener los datos
    $datos = $consulta->fetchAll();
    
    $respuesta['registros'] = $datos;
} else {
    $respuesta = [
        'status' => false,
        'message' => "Datos incompletos"
    ];
}

echo json_encode($respuesta);
?>