<?php
    $msg = $_POST['mensagem'];

    // CRIAR ARRAY
    $mag_array = array(
        "mensagem"=>$msg
    );
    //CONVERTER ARRAY PARA JSON
    $msg_json = json_encode($mag_array);
    // DEFINIR A URL DA API QUE SERÁ UTILIZADA
    $url = "http://10.107.134.3:8080/chat";

    // ABRIR A CONEXÃO PARA A API
    $con = curl_init($url);

    // DEFINIR O METODO DA RUQUISIÇÃO HTTP PARA POST
    curl_setopt($con, CURLOPT_CUSTOMREQUEST, "POST");

    //DEFINIR O CONTEÚDO DO BODY DA MENSAGEM
    curl_setopt($con, CURLOPT_POSTFIELDS, $msg_json);

    //DEFINIR SE ACEITAMOS RETORNO
    curl_setopt($con, CURLOPT_RETURNTRANSFER, true);

    // DEFINIR HEADER NECESSÁRIOS
    curl_setopt($con, CURLOPT_HTTPHEADER, 
        array('Content-Type: application/json',
        'Content-Length: ' . strlen($msg_json))
    );

  $jsonRetorno = curl_exec($con);
?>