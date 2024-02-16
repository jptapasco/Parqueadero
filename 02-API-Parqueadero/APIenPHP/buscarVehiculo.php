<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include 'Conexion.php';

$placaABuscar = $_GET['busqueda'];

try {
    $consulta = $base_de_datos->prepare("SELECT t.id, t.placa, t.create_entrada, t.salida, tar.tipo_vehiculo, tar.Tarifa, rv.responsable
        FROM ticket t
        LEFT JOIN tarifas tar ON t.id_tarifa = tar.id
        LEFT JOIN registro_vehiculos rv ON t.placa = rv.placa
        WHERE t.placa = :placa");
    $consulta->bindParam(':placa', $placaABuscar, PDO::PARAM_STR);
    $consulta->execute();
    $datos = $consulta->fetchAll();

    $respuesta['registros'] = $datos;
    echo json_encode($respuesta);
} catch (PDOException $e) {
    echo "Error en la consulta: " . $e->getMessage();
}
?>
