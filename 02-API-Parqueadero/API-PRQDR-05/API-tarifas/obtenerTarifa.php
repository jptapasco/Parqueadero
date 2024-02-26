<?php

header("Access-Control-Allow-Origin: * ");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if (!empty($_GET['tipo_vehiculo'])) {

    $tipo_vehiculo = $_GET['tipo_vehiculo'];

    $consulta = $base_de_datos->prepare("SELECT tarifa FROM tarifas WHERE tipo_vehiculo = :tip");
    $consulta->bindParam(':tip', $tipo_vehiculo);
    $consulta->execute();
    $datos = $consulta->fetchAll();

    $respuesta['registros'] = $datos;
    $respuesta['tarifa'] = $datos[0]['tarifa'];
    echo json_encode($respuesta);
} else {
    echo json_encode(array('error' => 'No se proporciono el tipo de vehículo.'));
}

?>