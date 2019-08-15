<?php
    $client = new SoapClient("http://localhost:8080/AutorWS2/services/AutorImpl?wsdl");

    //var_dump($client->__getFunctions()); 
    //var_dump($client->__getTypes());
?>

<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cliente SOAP - PHP</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="//cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <script src="//cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
</head>
<body>
    <div class="container" style="text-align: center;">
            <?php
                if(isset($_POST["listar"])){
                    echo "<nav class='navbar navbar-expand-lg navbar-dark bg-dark'>";
                    echo "<a class='navbar-brand' href='#'>Cliente SOAP - Listar Autores</a>";
                    echo "</nav>";

                    //Listar Autores
                    $params = array(
                            
                    );

                    $response1 = $client->__soapCall("totalAutores", $params);
                    $response2 = $client->__soapCall("listarAutores", $params);

                    echo "<table id='listar' class='table table-striped table-bordered'>";
                    echo "<thead class='bg-dark' style='color: white;' >";
                    echo "<th>Código</th>";
                    echo "<th>Nacionalidad</th>";
                    echo "<th>Nombre</th>";
                    echo "</thead>";

                    echo "<tbody>";

                    for($i = 0; $i < $response1->return; $i++){
                        echo "<tr>";
                        echo "<td>" . $response2->return[$i]->codigoAutor . "</td>";
                        echo "<td>" . $response2->return[$i]->nacionalidad . "</td>";
                        echo "<td>" . $response2->return[$i]->nombreAutor . "</td>";
                        echo "</tr>";
                    }
                    
                    echo "</tbody>";
                    echo "</table>";

                }else if(isset($_POST["total"])){
                    echo "<nav class='navbar navbar-expand-lg navbar-dark bg-dark'>";
                    echo "<a class='navbar-brand' href='#'>Cliente SOAP - Total Autores</a>";
                    echo "</nav>";

                    //Listar Autores
                    $params = array(
                            
                    );

                    $response1 = $client->__soapCall("totalAutores", $params);

                    echo "<h4>El total de autores es: " . $response1->return . "</h4>";
                }else if(isset($_POST["obtener"])){
                    echo "<nav class='navbar navbar-expand-lg navbar-dark bg-dark'>";
                    echo "<a class='navbar-brand' href='#'>Cliente SOAP - Obtener Autor</a>";
                    echo "</nav>";

                    //Obtener Autores
                    class Obtener {
                        public function __construct($codigo){
                            $this->codigo = $codigo;
                        }
                    }

                    $obtener = new Obtener($_POST["codigo"]);

                    $params3 = array(
                        "codigo" => $obtener
                    );

                    $response3 = $client->__soapCall("obtenerAutor", $params3);

                    if($response3->return != null){
                        echo "<h2>Autor</h2>";
                
                        echo "<label>Codigo Autor: <b>".$response3->return->codigoAutor."</b> </label>";
                        echo "<br>";
                        echo "<br>";
                        echo "<label>Nombre Autor: <b>".$response3->return->nombreAutor."</b> </label>";
                        echo "<br>";
                        echo "<br>";
                        echo "<label>Nacionalidad Autor: <b>".$response3->return->nacionalidad."</b> </label>";
                    }else{
                        echo "<label><b>El codigo del Autor no existe</b></label>";
                    }

                }else if(isset($_POST["modificar"])){
                    echo "<nav class='navbar navbar-expand-lg navbar-dark bg-dark'>";
                    echo "<a class='navbar-brand' href='#'>Cliente SOAP - Modificar Autor</a>";
                    echo "</nav>";

                    //Modificar Autores
                    $params4 = array(
                        "autor" => array(
                            "codigoAutor" => $_POST["codigo"],
                            "nombreAutor" => $_POST["nombre"],
                            "nacionalidad" => $_POST["nacionalidad"],
                        )
                    );

                    $response4 = $client->__soapCall("modificarAutor", array($params4));

                    if($response4->return){
                        echo "<label><b>Los datos han sido modificados</b></label>";
                        //Obtener Autores
                        class Obtener {
                            public function __construct($codigo){
                                $this->codigo = $codigo;
                            }
                        }

                        $obtener = new Obtener($_POST["codigo"]);

                        $params3 = array(
                            "codigo" => $obtener
                        );

                        $response3 = $client->__soapCall("obtenerAutor", $params3);

                        
                        echo "<h2>Autor</h2>";
                
                        echo "<label>Codigo Autor: <b>".$response3->return->codigoAutor."</b> </label>";
                        echo "<br>";
                        echo "<br>";
                        echo "<label>Nombre Autor: <b>".$response3->return->nombreAutor."</b> </label>";
                        echo "<br>";
                        echo "<br>";
                        echo "<label>Nacionalidad Autor: <b>".$response3->return->nacionalidad."</b> </label>";
                        

                    }else{
                        echo "<label><b>No se han podido modificar los datos</b></label>";
                    }


                }else if(isset($_POST["insertar"])){
                    echo "<nav class='navbar navbar-expand-lg navbar-dark bg-dark'>";
                    echo "<a class='navbar-brand' href='#'>Cliente SOAP - Insertar Autor</a>";
                    echo "</nav>";

                    //Modificar Autores
                    $params4 = array(
                        "autor" => array(
                            "codigoAutor" => $_POST["codigo"],
                            "nombreAutor" => $_POST["nombre"],
                            "nacionalidad" => $_POST["nacionalidad"],
                        )
                    );

                    $response4 = $client->__soapCall("insertarAutor", array($params4));

                    if($response4->return){
                        echo "<label><b>El Autor ha sido agregado</b></label>";
                        //Obtener Autores
                        class Obtener {
                            public function __construct($codigo){
                                $this->codigo = $codigo;
                            }
                        }

                        $obtener = new Obtener($_POST["codigo"]);

                        $params3 = array(
                            "codigo" => $obtener
                        );

                        $response3 = $client->__soapCall("obtenerAutor", $params3);

                        echo "<h2>Autor</h2>";
                    
                        echo "<label>Codigo Autor: <b>".$response3->return->codigoAutor."</b> </label>";
                        echo "<br>";
                        echo "<br>";
                        echo "<label>Nombre Autor: <b>".$response3->return->nombreAutor."</b> </label>";
                        echo "<br>";
                        echo "<br>";
                        echo "<label>Nacionalidad Autor: <b>".$response3->return->nacionalidad."</b> </label>";

                    }else{
                        echo "<label><b>Ya existe un Autor con ese codigo</b></label>";
                    }
                }else if(isset($_POST["eliminar"])){
                    echo "<nav class='navbar navbar-expand-lg navbar-dark bg-dark'>";
                    echo "<a class='navbar-brand' href='#'>Cliente SOAP - Eliminar Autor</a>";
                    echo "</nav>";

                    //Obtener Autores
                    class Eliminar {
                        public function __construct($codigo){
                            $this->codigo = $codigo;
                        }
                    }

                    $eliminar = new Eliminar($_POST["codigo"]);

                    $params5 = array(
                        "codigo" => $eliminar
                    );

                    $response5 = $client->__soapCall("deleteAutor", $params5);

                    if($response5->return == 1){
                        echo "<label><b>El Autor ha sido eliminado con exito</b></label>";
                    }else{
                        echo "<label><b>No se ha podido eliminar el Autor</b></label>";
                    }
                }
            ?>
            
    </div>
</body>
</html>
<script>  
    $(document).ready(function(){  
        $('#listar').DataTable({
        language: {
            "decimal": "",
            "emptyTable": "No hay información",
            "info": "Mostrando _START_ a _END_ de _TOTAL_ Entradas",
            "infoEmpty": "Mostrando 0 to 0 of 0 Entradas",
            "infoFiltered": "(Filtrado de _MAX_ total entradas)",
            "infoPostFix": "",
            "thousands": ",",
            "lengthMenu": "Mostrar _MENU_ Entradas",
            "loadingRecords": "Cargando...",
            "processing": "Procesando...",
            "search": "Buscar:",
            "zeroRecords": "Sin resultados encontrados",
            "paginate": {
                "first": "Primero",
                "last": "Ultimo",
                "next": "Siguiente",
                "previous": "Anterior"
            }
        }
    });  
    });  
</script>


<?php
    /*
    


    
    
    */
?>
