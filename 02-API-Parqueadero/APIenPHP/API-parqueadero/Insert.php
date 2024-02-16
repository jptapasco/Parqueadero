<?php 
    header("Access-Control-Allow-Origin: *"); // Permite el acceso desde cualquier origen, o usa "http://localhost" si solo quieres permitirlo desde localhost.
    header("Access-Control-Allow-Methods: GET, POST");
    header("Access-Control-Allow-Headers: Content-Type");
    
    include '../Conexion.php';

    if (!empty($_POST['nit']) and !empty($_POST['nombre']) and !empty($_POST['direccion']) and !empty($_POST['telefono']) ) {

        $nit = $_POST['nit'];
        $nombre = $_POST['nombre'];
        $direccion = $_POST['direccion'];
        $telefono = $_POST['telefono'];

        try {
            $consulta = $base_de_datos->prepare("INSERT INTO parqueadero (nit, nombre, direccion, telefono) VALUES(:nit, :nom, :dir, :tel) ");

            $consulta->bindParam(':nit', $nit);
            $consulta->bindParam(':nom', $nombre);
            $consulta->bindParam(':dir', $direccion);
            $consulta->bindParam(':tel', $telefono);
            
            $proceso = $consulta->execute();

            if( $proceso ){
                $respuesta = [
                                'status' => true,
                                'mesagge' => "OK##PARQUEADERO##INSERT"
                              ];
                echo json_encode($respuesta);
            }else{
                $respuesta = [
                                'status' => false,
                                'mesagge' => "ERROR##PARQUEADERO##INSERT"
                              ];
                echo json_encode($respuesta);
            }
        } catch (Exception $e) {
            $respuesta = [
                            'status' => false,
                            'mesagge' => "ERROR##SQL",
                            'exception' => $e
                          ];
            echo json_encode($respuesta);
        }
    }else{
        $respuesta = [
                        'status' => false,
                        'mesagge' => "ERROR##DATOS##POST"
                      ];
        echo json_encode($respuesta);
    }
?>
