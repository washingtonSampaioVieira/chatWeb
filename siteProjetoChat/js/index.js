
const btnNovaMensagem = document.getElementById('btn_enviar');
const txtMensagem = document.getElementById('caixa_mensagem');
const tela = document.getElementById("mensagem");

// window.setInterval('getMensagens()', 5000);

const getMensagens = () =>{
    $.ajax({
        type: 'GET',
        dataType: 'json',
        crossDomain: true,
        url: 'http://10.107.134.3:8080/chat',
        complete: function(response){
            let jsonMensagem = JSON.parse(response.responseText);
            console.log(jsonMensagem[0].mensagem);
    
            let fim = jsonMensagem.length;
    
            for(let i= 0; i<fim ; i++){
                let elemento = document.createElement("div");
                elemento.setAttribute("class", "mensagem_recebida");
                elemento.innerHTML  = jsonMensagem[i].mensagem;
                tela.appendChild(elemento);
            }
    
        },
        error: function(){
            let elemento = document.createElement("div");
                elemento.setAttribute("class", "mensagem_recebida");
                elemento.innerHTML  = "Deu Ruim."
                tela.appendChild(elemento);
        }   
    });
    
}
$(document).ready( function(){
    $("#btn_enviar").click(function(){

        let texto = $("#caixa_mensagem").val();

        $.ajax({
            type: 'POST',
            url: '../mensagem.php',
            data: {mensagem: texto},
            complete: function(response){
                console.log(response.responseText);
            },
            error: function(){
                
            }   
        });



    });



        
    
    
    
    
      
    
});
