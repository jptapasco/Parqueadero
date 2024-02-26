<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include 'Conexion.php';

    //$consulta = $base_de_datos->query("SELECT * FROM ticket"); 
$consulta = $base_de_datos->query("SELECT t.id, t.placa, t.create_entrada, t.salida, tar.tipo_vehiculo, tar.Tarifa, rv.responsable
    FROM ticket t
    LEFT JOIN tarifas tar ON t.id_tarifa = tar.id
    LEFT JOIN registro_vehiculos rv ON t.placa = rv.placa");
$datos = $consulta->fetchAll();

$respuesta['registros'] = $datos;
echo json_encode($respuesta);
?>
