/**
 * Calcula a quantidade de dias entre duas datas, as duas datas terao que estar no
 * formato DD/MM/YYYY
 * 
 * @author Raphael Rossiter
 * @date 22/03/2010
 * 
 * @return quantidade de dias entre duas datas
 */
function diasEntreDatas(dataInicial, dataFinal) {
 
	var mes, dataAtual, dataInicial, arrDataInicial, novaDataInicial;
 	mes = new Array("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
	
	arrDataFinal = dataFinal.split("/");
 	arrDataInicial = dataInicial.split("/");
 	
 	novaDataInicial = mes[(arrDataInicial[1] - 1)] + ' ' + arrDataInicial[0] + ' ' + arrDataInicial[2];
 	novaDataFinal = mes[(arrDataFinal[1] - 1)] + ' ' + arrDataFinal[0] + ' ' + arrDataFinal[2];
 
 	return (((Date.parse(novaDataFinal))-(Date.parse(novaDataInicial)))/(24*60*60*1000)).toFixed(0);
}


/**
 * Valida uma data no formato DD/MM/YYYY apenas retornando um valor boleano,
 * sem retornar nenhum alert
 * 
 * @author Raphael Rossiter
 * @date 22/03/2010
 * 
 * @return boleano
 */
function validaDataSemMensagem(campo){

	if (campo.value!="") {
		
		erro=0;
		hoje = new Date();
		anoAtual = hoje.getFullYear();
		barras = campo.value.split("/");
        
		if (barras.length == 3){
			
			dia = barras[0];
			mes = barras[1];
			ano = barras[2];
            
			resultado = (!isNaN(dia) && (dia > 0) && (dia < 32)) && (!isNaN(mes) && (mes > 0) && (mes < 13)) && (!isNaN(ano) && (ano.length == 4) && ano >= 1900);
            
			if (!resultado){
				
				campo.focus();
				return false;
			}
		}
		else{
			campo.focus();
			return false;
		}
	
		return true;
	}
}

/**
 * Calcula a quantidade de anos completos, existentes entre duas datas 
 * 
 * @author Raphael Rossiter
 * @date 16/06/2009
 * 
 * @return quantidade de anos
 */	
function anosEntreDatas(dataInicial, dataFinal){
		
	var idade = 0;
	
	while (comparaData(dataInicial, "<", dataFinal)){
			
		var diaInicial = dataInicial.substr(0,2);
        var mesInicial = dataInicial.substr(3,2);
		var anoInicial = dataInicial.substr(6,4);
	
		var diaFinal = dataFinal.substr(0,2);
        var mesFinal = dataFinal.substr(3,2);
		var anoFinal = dataFinal.substr(6,4);
	
		anoInicial++;
		dataInicial = diaInicial + "/" + mesInicial + "/" + anoInicial;
	
		if (parseInt(anoInicial) == parseInt(anoFinal)){
				
			if (parseInt(mesInicial) < parseInt(mesFinal) ||
				( parseInt(mesInicial) == parseInt(mesFinal) && parseInt(diaInicial) <= parseInt(diaFinal) ) ){
					
				idade++;
			}
	
			break;
		}
			
		idade++;
	
	}
		
	return idade;
}

/*
funcao que envia dados para a pagina que abriu o popup -- A pagina que usa a
pesquisa do popup deve ter a funcao 'recuperarDadosPopup' para setar os
dados no respectivo form.

codigoRegistro - codigo do registro que foi selecionado no popup
descricaoRegistro - descricao do registro que foi selecionado no popup
tipoConsulta - descricao de que tipo de registro o usuario pretende pesquisar

*/
function enviarDados(codigoRegistro, descricaoRegistro, tipoConsulta) {
   opener.recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta);
   self.close();
}


/*
funcao que envia dados para a pagina que abriu o popup -- A pagina que usa a
pesquisa do popup deve ter a funcao 'recuperarDadosPopup' para setar os
dados no respectivo form.

codigoRegistro - codigo do registro que foi selecionado no popup
descricaoRegistro - descricao do registro que foi selecionado no popup
tipoRegistro - tipo do registro que foi selecionado no popup
auxRegistro - registro aux que foi selecionado no popup
tipoConsulta - descricao de que tipo de registro o usuario pretende pesquisar

*/
function enviarDadosInserirColecao(codigoRegistro, descricaoRegistro,tipoRegistro,auxRegistro,tipoConsulta) {
   opener.recuperarDadosPopupInserirColecao(codigoRegistro, descricaoRegistro,tipoRegistro,auxRegistro, tipoConsulta);
   self.close();
}


function enviarDadosParametros(nomeActionExibirPagina, idCampo, descricaoCampo, tipoConsulta) {

	window.location.href =
	'/gsan/' + nomeActionExibirPagina + '.do?idCampoEnviarDados=' + idCampo +
	'&descricaoCampoEnviarDados=' + descricaoCampo + '&tipoConsulta=' + tipoConsulta;	
}

function enviarDadosParametrosFecharPopup(nomeActionExibirPagina, idCampo, descricaoCampo, tipoConsulta) {
	opener.window.location.href =
	'/gsan/' + nomeActionExibirPagina + '.do?idCampoEnviarDados=' + idCampo +
	'&descricaoCampoEnviarDados=' + descricaoCampo + '&tipoConsulta=' + tipoConsulta;
	self.close();

}


//=====================================================================================================================

function enviarDadosMunicipioDdd(codigoRegistro,descricaoRegistro,codigoDdd,tipoConsulta) {
  opener.recuperarDadosMunicipioDdd(codigoRegistro,descricaoRegistro,codigoDdd,tipoConsulta);
  self.close();
}


function enviarDadosCodigoEnderecamentoPostal(codigoRegistro, descricaoRegistro, codigoLogradouro, tipoConsulta){
   opener.recuperarDadosCodigoEnderecamentoPostal(codigoRegistro, descricaoRegistro, codigoLogradouro,tipoConsulta);
   self.close();
}

//Enviar Dados com 4 parametros
function enviarDadosQuatroParametros(codigoRegistro, descricaoRegistro, codigoAuxiliar, tipoConsulta){
   opener.recuperarDadosQuatroParametros(codigoRegistro, descricaoRegistro, codigoAuxiliar,tipoConsulta);
   self.close();
}


//Enviar Dados com 5 parametros
function enviarDadosCincoParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, descricaoRegistro3, tipoConsulta){
   opener.recuperarDadosCincoParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, descricaoRegistro3, tipoConsulta);
   self.close();
}

//Enviar Dados com 6 parametros
function enviarDadosSeisParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, descricaoRegistro3, descricaoRegistro4, tipoConsulta){
    opener.recuperarDadosSeisParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, descricaoRegistro3, descricaoRegistro4, tipoConsulta);
   self.close();
}

//=====================================================================================================================


function enviarDadosQuatroParametrosCaminhoRetorno(nomeActionExibirPagina, codigoRegistro, descricaoRegistro, codigoAuxiliar, tipoConsulta) {
	window.location.href =
	'/gsan/' + nomeActionExibirPagina + '.do?idCampoEnviarDados=' + codigoRegistro +
	'&descricaoCampoEnviarDados=' + descricaoRegistro + '&codigoAuxiliarEnviarDados=' + codigoAuxiliar + 
	'&tipoConsulta=' + tipoConsulta;

}

function enviarDadosCincoParametrosCaminhoRetorno(nomeActionExibirPagina, codigoRegistro, descricaoRegistro1, descricaoRegistro2, descricaoRegistro3, tipoConsulta) {
	window.location.href =
	'/gsan/' + nomeActionExibirPagina + '.do?idCampoEnviarDados=' + codigoRegistro +
	'&descricaoCampoEnviarDados1=' + descricaoRegistro1 + '&descricaoCampoEnviarDgit ados2=' + descricaoRegistro2 + 
	'&descricaoCampoEnviarDados3=' + descricaoRegistro3 + '&tipoConsulta=' + tipoConsulta;

}

function enviarDadosSeisParametrosCaminhoRetorno(nomeActionExibirPagina, codigoRegistro, descricaoRegistro1, descricaoRegistro2, descricaoRegistro3, descricaoRegistro4, tipoConsulta) {
	window.location.href =
	'/gsan/' + nomeActionExibirPagina + '.do?idCampoEnviarDados=' + codigoRegistro +
	'&descricaoCampoEnviarDados1=' + descricaoRegistro1 + '&descricaoCampoEnviarDados2=' + descricaoRegistro2 + 
	'&descricaoCampoEnviarDados3=' + descricaoRegistro3 + '&descricaoCampoEnviarDados4=' + descricaoRegistro4 + '&tipoConsulta=' + tipoConsulta;

}

/* funcao que abre um popup com o caminho informado
   EX: abrirPopup(String , int, int)
   OBS - O popup sera aberto no centro da tela
*/
function abrirPopup(caminho, altura, largura) {
	abrirPopupDeNome(caminho, altura, largura, 'Pesquisar', 'yes');
}

/* funcao que abre um popup com o caminho informado
   EX: abrirPopup(String , int, int)
   OBS - O popup sera aberto no centro da tela
*/
function abrirPopupDeNome(caminho, altura, largura, nome, status) {

   //Para abrir o popup centralizado ======
	var height = window.screen.height - 160;
	var width = window.screen.width;
	var top = (height - altura)/2;
	var left = (width - largura)/2;
   //======================================

   window.open(caminho,nome,'top=' + top + ',left='+ left +',location=no,screenY=0,screenX=0,menubar=no,status=' + status + ',toolbar=no,scrollbars=yes,resizable=no,width=' + largura + ',height=' + altura);
}


function abrirPopupHelp(caminho, altura, largura) {

   //Para abrir o popup centralizado ======
	var height = window.screen.height - 160;
	var width = window.screen.width;
	var top = (height - altura)/2;
	var left = (width - largura)/2;
   //======================================

   window.open(caminho,'_new','top=' + top + ',left='+ left +',location=no,screenY=0,screenX=0,menubar=no,status=yes,toolbar=no,scrollbars=yes,resizable=no,width=' + largura + ',height=' + altura);
}


function abrirPopupComSubmit(caminho, altura, largura, nomeJanela) {

   //Para abrir o popup centralizado ======
	var height = window.screen.height - 160;
	var width = window.screen.width;
	var top = (height - altura)/2;
	var left = (width - largura)/2;
   //======================================

   window.open('',nomeJanela,'top=' + top + ',left='+ left +',location=no,screenY=0,screenX=0,menubar=no,status=yes,toolbar=no,scrollbars=yes,resizable=no,width=' + largura + ',height=' + altura);

   var form = document.forms[0];
   form.action = caminho;
   form.target = nomeJanela;

   submeterFormPadrao(form);
   
   form.target = "_top";
}

function abrirPopupComSubmitFullScreen(caminho, nomeJanela) {

	   //Para abrir o popup centralizado ======
		var height = window.screen.height;
		var width = window.screen.width;
		var top = 0;
		var left = 0;
		var fullscreen = 'yes';
	   //======================================

		window.open('',nomeJanela,'top=' + top + ',left='+ left +',location=no,screenY=0,screenX=0,menubar=no,status=yes,toolbar=no,scrollbars=yes,resizable=no,width=' + width + ',height=' + height);

	   var form = document.forms[0];
	   form.action = caminho;
	   form.target = nomeJanela;

	   submeterFormPadrao(form);
	   
	   form.target = "_top";
}

/* funcao que abre um popup com o caminho informado (Ira abrir sempre de acordo com a resolucao do monitor)
   EX: abrirPopupRelatorio(String)
*/
function abrirPopupRelatorio(caminho) {

   //Carrega a resolucao do monitor =======
   var altura = window.screen.width
   var largura = window.screen.height;
	
	
   //======================================
   window.open(caminho,'Relatorio','top=0,left=0,location=no,screenY=0,screenX=0,menubar=no,status=yes,toolbar=no,scrollbars=yes,resizable=yes,width=' + largura + ',height=' + altura);
}


/*
funcao que adiciona campos dinamicos em paginas html.
As paginas devem ter <div id="newElements"></div> no trecho onde o conteudo dinamico
deve aparecer

	nomeForm - Nome do html form que sera modificado
	nomeCampoQuantidadeElementos - Nome do campo que guarda quantos elementos dinamicos serao
criados
	nomeLabelCampo - Label do campo dinamico

*/
function addElement(nomeForm, nomeCampoQuantidadeElementos, nomeLabelCampo)
{
   var newElement = '';

   for (i=1; i<=document.forms[nomeForm].elements[nomeCampoQuantidadeElementos].value; i++) {
   	newElement += nomeLabelCampo + ' ' + i + ': <input type="text" name=campoDinamico'+i+'><br>';
   }

   document.getElementById('newElements').innerHTML += newElement;

}



/*
funcao que adiciona campos dinamicos em paginas html seguidos por campo select.
As paginas devem ter <div id="newElements"></div> no trecho onde o conteudo dinamico
deve aparecer

	nomeForm - Nome do html form que sera modificado
	nomeCampoQuantidadeElementos - Nome do campo que guarda quantos elementos dinamicos serao
criados
	nomeLabelCampo - Label do campo dinamico
	stringSelect - trecho de codigo html que representa o select desejado

*/
function addElementSelect(nomeForm, nomeCampoQuantidadeElementos, nomeLabelCampo, stringSelect) {

   var newElement = '';


   for (i=1; i<=document.forms[nomeForm].elements[nomeCampoQuantidadeElementos].value; i++) {
        campoStringSelect = '<select name=selectDinamico'+i+'>' + stringSelect
   	newElement += nomeLabelCampo + ' ' + i + ': <input type="text" name=campoDinamico'+i+'>' + campoStringSelect + '<br>';
   }

   document.getElementById('newElements').innerHTML += newElement;


}


//Mesma coisa do addElementSelect com adaptacao para as paginas que usarao telefone
function addCamposTelefone(nomeForm, nomeCampoQuantidadeElementos, nomeLabelCampo, stringSelect) {

   var newElement = '';


   for (i=1; i<=document.forms[nomeForm].elements[nomeCampoQuantidadeElementos].value; i++) {
        campoStringSelect = '<select name=codigoTipoTelefone'+i+'>' + stringSelect
   		newElement += 'DDD: ' + '<input type="text" size="2" maxlength="2" name=ddd'+i+'> ' +
		nomeLabelCampo + ' ' + i + ': <input type="text" size="8" maxlength="8" name=numeroTelefone'+i+'>' +
		campoStringSelect + '<br>';
   }

   document.getElementById('newElements').innerHTML += newElement;
}



//funcao que limpa os campos dinamicos de uma pagina
function removeElements() {
	document.getElementById('newElements').innerHTML = '';
}



//funcao que manipula select multiplo movendo os dados de um select para outro
//nomeForm - Nome do form a ser manipulado
//nomeCampoMenu1 - Nome do campo do select 1 que contem os dados disponoveis
//nomeCampoMenu2 - Nome do campo do select 2 que contem os dados cadastrados

function MoverDadosSelectMenu1PARAMenu2(nomeForm, nomeCampoMenu1, nomeCampoMenu2) {

    var m1 = document.forms[nomeForm].elements[nomeCampoMenu1];
    var m2 = document.forms[nomeForm].elements[nomeCampoMenu2];

    m1len = m1.length ;
    for ( i=0; i<m1len ; i++){
        if (m1.options[i].selected == true ) {
            m2len = m2.length;
            m2.options[m2len]= new Option(m1.options[i].text, m1.options[i].value);
        }
    }

    for ( i = (m1len -1); i>=0; i--){
        if (m1.options[i].selected == true ) {
            m1.options[i] = null;
        }
    }
}



//funcao que manipula select multiplo movendo os dados de um select para outro
//nomeForm - Nome do form a ser manipulado
//nomeCampoMenu1 - Nome do campo do select 1 que contem os dados disponoveis
//nomeCampoMenu2 - Nome do campo do select 2 que contem os dados cadastrados

function MoverTodosDadosSelectMenu2PARAMenu1(nomeForm, nomeCampoMenu1, nomeCampoMenu2) {

    var m1 = document.forms[nomeForm].elements[nomeCampoMenu1];
    var m2 = document.forms[nomeForm].elements[nomeCampoMenu2];
    
    //Seleciona todos os itens do menu2 para enviar para o menu1
    enviarSelectMultiplo(nomeForm, nomeCampoMenu2);

       m2len = m2.length;
        for ( i=0; i<m2len ; i++){
            if (m2.options[i].selected == true && m2.options[i].value != '-1') {
                m1len = m1.length;
                m1.options[m1len]= new Option(m2.options[i].text, m2.options[i].value);
            }
        }
        for ( i=(m2len-1); i>=0; i--) {
            if (m2.options[i].selected == true ) {
                m2.options[i] = null;
            }
        }
}



//funcao que manipula select multiplo movendo os dados de um select para outro
//nomeForm - Nome do form a ser manipulado
//nomeCampoMenu1 - Nome do campo do select 1 que contem os dados disponoveis
//nomeCampoMenu2 - Nome do campo do select 2 que contem os dados cadastrados

function MoverTodosDadosSelectMenu1PARAMenu2(nomeForm, nomeCampoMenu1, nomeCampoMenu2) {

    var m1 = document.forms[nomeForm].elements[nomeCampoMenu1];
    var m2 = document.forms[nomeForm].elements[nomeCampoMenu2];
    
    //Seleciona todos os itens do menu1 para enviar para o menu2
    enviarSelectMultiplo(nomeForm, nomeCampoMenu1);

    m1len = m1.length ;
    for ( i=0; i<m1len ; i++){
       	if (m1.options[i].selected == true && m1.options[i].value != '-1') {    
            m2len = m2.length;
            m2.options[m2len]= new Option(m1.options[i].text, m1.options[i].value);
        }
    }

    for ( i = (m1len -1); i>=0; i--){
        if (m1.options[i].selected == true ) {
            m1.options[i] = null;
        }
    }
}



//funcao que manipula select multiplo movendo os dados de um select para outro
//nomeForm - Nome do form a ser manipulado
//nomeCampoMenu1 - Nome do campo do select 1 que contem os dados disponoveis
//nomeCampoMenu2 - Nome do campo do select 2 que contem os dados cadastrados

function MoverDadosSelectMenu2PARAMenu1(nomeForm, nomeCampoMenu1, nomeCampoMenu2) {

    var m1 = document.forms[nomeForm].elements[nomeCampoMenu1];
    var m2 = document.forms[nomeForm].elements[nomeCampoMenu2];

    m2len = m2.length;
        for ( i=0; i<m2len ; i++){
            if (m2.options[i].selected == true ) {
            	if (m2.options[i].value != '-1') {
               		m1len = m1.length;
                	m1.options[m1len]= new Option(m2.options[i].text, m2.options[i].value);
                }
            }
        }
        for ( i=(m2len-1); i>=0; i--) {
            if (m2.options[i].selected == true ) {
            	if (m2.options[i].value != '-1') {
                	m2.options[i] = null;
                }
            }
        }
}

//funcao que seleciona todos os options do select multiplo para serem enviados
//para processamento
function enviarSelectMultiplo(nomeForm, nomeCampoMenu2) {
    var m2 = document.forms[nomeForm].elements[nomeCampoMenu2];

	if (m2.length == 0) {
		m2.options[0] = new Option('', '-1');	
	} else {
		if (m2.options[0].value == '-1') {
			m2.options[0] = null;
		}
	}


	for (i=0; i<m2.length; i++) {
		if (m2.options[i] != null) {		
			m2.options[i].selected = true;
		}
	}
	

}



//funcao que limita o no de caracteres num campo do tipo "textarea" ou "text"
function limitText(limitField, limitCount, limitNum) {
	if (limitField.value.length > limitNum) {
		limitField.value = limitField.value.substring(0, limitNum);
	} else {
		limitCount.value = limitNum - limitField.value.length;
	}
}

//Autor: Raphael Rossiter
//limitField = Objeto textarea que ira ser limitado
//limitNum = quantidade maxima de caracteres
// "utilizado" = Local onde sera apresentado a quantidade de caracteres que foi utilizada
// "limite" = Local onde sera apresentado a quantidade de caracteres que falta para bater o limite
// EX: <span id="utilizado">0</span>/<span id="limite">10</span> limitNum = 10
function limitTextArea(limitField, limitNum, utilizado, limite) {
	if (limitField.value.length > limitNum) {
		limitField.value = limitField.value.substring(0, limitNum);
	} else {
		utilizado.innerHTML = limitField.value.length;
		limite.innerHTML = limitNum - limitField.value.length;
	}
}




// Limpa um campo do formulario informado como parametro
function limparCampoPesquisa(nomeForm, nomeCampo) {
  var campo = document.forms[nomeForm].elements[nomeCampo];
  campo.value = "";
}

function limparCampos(){
	for(var i = 0; i< arguments.length; i++){
		document.getElementsByName(arguments[i])[0].value = '';
	}
}


// funcao utilizada no mecanismode processo quando o usuario clica no botao avancar
function botaoAvancar(uri){

// alert(uri);
// alert(uri.match('inserirUsuarioWizardAction.do'));
// alert(uri.value.indexOf('inserirUsuarioWizard.do'));
 
 
 
  document.forms[0].action=uri;
  var cancelarValidacao = false;

  //Parte que verifica se a validacao foi desabilitada
  for (i=0; i < document.forms[0].elements.length; i++) {
    if (document.forms[0].elements[i].name == 'cancelarValidacao') {
      if (document.forms[0].elements[i].value == 'true') {
	cancelarValidacao = true;
	break;
      }
    }
  }

  if (cancelarValidacao || (eval('validate' + document.forms[0].name + '(document.forms[0]);'))) {
    for (i=0; i < document.forms[0].elements.length; i++) {
      if (document.forms[0].elements[i].disabled ) {
        document.forms[0].elements[i].disabled = false;
      }
    }
    
    //Alterado por pedro alexandre dia 11/08/2006
    //Trecho adicionado para os processo de usuario
    //porque os campos login e email nao podem ser colocados para caixa alta
    var colocarUpperCase = true;
    
    if((uri.match('inserirUsuarioWizardAction.do') != null || uri.match('atualizarUsuarioWizardAction.do') != null) 
    	|| (uri.match('inserirClienteWizardAction.do') != null || uri.match('atualizarClienteWizardAction.do') != null)
    	|| uri.match('informarParametrosSistemaWizardAction.do') != null){
	 
	  colocarUpperCase = false;
	}

    if(colocarUpperCase){
	  toUpperCase(document.forms[0]);
	}
	
    document.forms[0].submit();
  }
}

// funcao utilizada no mecanismode processo quando o usuario clica no botao avancar
function botaoAvancarRA(uri){

  window.location.href = uri;
}


function botaoDesabilita(form){
  if (eval('validate' + form.name + '(form);')) {
    for (i=0; i < document.forms[0].elements.length; i++) {
      if (document.forms[0].elements[i].disabled ) {
        document.forms[0].elements[i].disabled = false;
      }
    }
	toUpperCase(document.forms[0]);
    document.forms[0].submit();
  }
}

function botaoDesabilitaSemValidacao(form){
  for (i=0; i < document.forms[0].elements.length; i++) {
      if (document.forms[0].elements[i].disabled ) {
        document.forms[0].elements[i].disabled = false;
      }
    }
}


// funcao utilizada no mecanismode processo quando o usuario clica no botao avancar
function botaoAvancarTelaEspera(uri){

  document.forms[0].action=uri;
  var cancelarValidacao = false;

  //Parte que verifica se a validacao foi desabilitada
  for (i=0; i < document.forms[0].elements.length; i++) {
    if (document.forms[0].elements[i].name == 'cancelarValidacao') {
      if (document.forms[0].elements[i].value.toLowerCase() == 'true') {
	cancelarValidacao = true;
	break;
      }
    }
  }

  if (cancelarValidacao || (eval('validate' + document.forms[0].name + '(document.forms[0]);'))) {
    for (i=0; i < document.forms[0].elements.length; i++) {
      if (document.forms[0].elements[i].disabled ) {
        document.forms[0].elements[i].disabled = false;
      }
    }
    
    //Alterado por pedro alexandre dia 11/08/2006
    //Trecho adicionado para os processo de usuario
    //porque os campos login e email terao podem ser colocados para caixa alta
    var colocarUpperCase = true;
    
    if((uri.match('inserirUsuarioWizardAction.do') != null || uri.match('atualizarUsuarioWizardAction.do') != null) 
    	|| (uri.match('inserirClienteWizardAction.do') != null || uri.match('atualizarClienteWizardAction.do') != null)){
	  colocarUpperCase = false;
	}

    if(colocarUpperCase){
	  toUpperCase(document.forms[0]);
	}

	
	eval('submitForm(document.forms[0]);')
    
  }
}




// funcao utilizada no mecanismo de processo quando o usuario clica no botao voltar
function botaoVoltar(uri){
  document.forms[0].action = uri;
  toUpperCase(document.forms[0]);
  document.forms[0].submit();
}


function validarHora(horaMinuto) {

  if (horaMinuto != "") {
    var horaMinuto = horaMinuto.split(':');
    if (horaMinuto.length == 2) {
      if(horaMinuto[0] <= 23 && horaMinuto[0] >= 0 &&
         horaMinuto[1] <= 59 && horaMinuto[1] >= 0){
          return true;
      }
	  else {
		return false;
      }
	}
	else {
      return false;
    }
  }
  else {
	return true;
  }

}



// funcao utilizada quando o usuario deseja desabilitar o campo na hora de submeter a pagina
function desabilitaCampos(){
    for (i=0; i < document.forms[0].elements.length; i++) {
      if (document.forms[0].elements[i].disabled ) {
        document.forms[0].elements[i].disabled = false;
      }
    }
}



/* Utilizado nos formularios que realizam pesquisas atravas do botao Enter
   EX: pesquisaEnter(event, String)
   event - Captura qual foi o evento realizado pelo usuario (Padrao)
*/
function pesquisaEnter(tecla, caminhoActionReload, objetoPesquisa) {

	var form = document.forms[0];

	if (document.all) {
        var codigo = event.keyCode;
	}
	else{
        var codigo = tecla.which;
    }
    if (codigo == 13) {
    
    	objetoPesquisa.value = (objetoPesquisa.value * 1);
    	 
		desabilitaCampos();
        form.action = caminhoActionReload;	
        toUpperCase(form);
        form.submit();
    }
	else{
        return true;
    }
}

/* Utilizado nos formularios que realizam pesquisas atravas do botao Enter
   EX: pesquisaEnter(event, String)
   event - Captura qual foi o evento realizado pelo usuario (Padrao)
*/
function pesquisaEnterAceitaZERO(tecla, caminhoActionReload, objetoPesquisa) {

	var form = document.forms[0];

	if (document.all) {
        var codigo = event.keyCode;
	}
	else{
        var codigo = tecla.which;
    }
    if (codigo == 13) {
		desabilitaCampos();
        form.action = caminhoActionReload;
		toUpperCase(form);
        form.submit();
    }
	else{
        return true;
    }
}


/* Utilizado nos formularios que realizam pesquisas atraves do botao Enter
   Sem colocar os campos para uppercase
   EX: pesquisaEnterSemUpperCase(event, String)
   event - Captura qual foi o evento realizado pelo usuario (Padrao)
*/
function pesquisaEnterSemUpperCase(tecla, caminhoActionReload, objetoPesquisa) {

	var form = document.forms[0];

	if (document.all) {
        var codigo = event.keyCode;
	}
	else{
        var codigo = tecla.which;
    }

    if (codigo == 13) {
    
    	objetoPesquisa.value = (objetoPesquisa.value * 1);
    	 
		desabilitaCampos();
        form.action = caminhoActionReload;
        form.submit();
    }
	else{
        return true;
    }
}


/* Utilizado nos formularios que terao realizam pesquisas atraves do botao ENTER
   EX: pesquisaEnterSemEvento(String)
*/
function pesquisaEnterSemEvento(caminhoActionReload) {

	var form = document.forms[0];
	
	desabilitaCampos();
    form.action = caminhoActionReload;
	toUpperCase(form);
    form.submit();
    
}


// Redireciona o submit do formulario para a url passada como parametro
function redirecionarSubmit(caminhoAction) {

   var form = document.forms[0];
   form.action = caminhoAction;
   toUpperCase(form);
   form.submit();

   return true;

 }

//Redireciona o submit do formulario para a url passada como parametro
//sem colocar os campos do form para caixa alta
function redirecionarSubmitSemUpperCase(caminhoAction) {
   var form = document.forms[0];
   form.action = caminhoAction;
   form.submit();
   return true;
 }


/* Redireciona o submit do formulario para a url passada como parametro.
   Realiza a validacao definida no validate para o formulario que esta sendo utilizado */
function redirecionarSubmitComValidacao(caminhoAction) {

   if (eval('validate' + document.forms[0].name + '(document.forms[0]);')) {
	  var form = document.forms[0];
      form.action = caminhoAction;
	  toUpperCase(form);
      form.submit();
	}

   return true;
}




/* Utilizado nos formularios que realizam pesquisas atraves do botao Enter
   EX: validaEnter(event, String, String)
   event - Captura qual foi o evento realizado pelo usuario (Padrao)
   caminhoActionReload - Action responsavel por realizar a consulta.
   nomeCampo - Nome do campo que possui o codigo para ser consultado (Recebe uma string).
   OBS - Realiza uma filtragem no que foi digitado pelo usuario (so aceita inteiros maiores que zero)
*/
function validaEnter(tecla, caminhoActionReload, nomeCampo) {

	var form = document.forms[0];

	
	var objetoCampo = eval("form." + nomeCampo);
	var valorCampo = eval("form." + nomeCampo + ".value");

	if (document.all) {
		var codigo = event.keyCode;
    }
	else {
       var codigo = tecla.which;
    }

	if (codigo == 13) {
		if (!isNaN(valorCampo) && valorCampo.length > 0 && valorCampo.indexOf(',') == -1 &&
			valorCampo.indexOf('.') == -1 && ((valorCampo * 1) > 0)){
			
			// Retira os espacos em branco da string passada.
			objetoCampo.value = trim(valorCampo);
			if (objetoCampo.value.length > 0){
				return pesquisaEnter(tecla, caminhoActionReload, objetoCampo);
			}
		}
	}
	else{
		return true;
	}
}

/*
*
* Validacao do enter aceitando o valor zero como parametro
* Data: 15/05/2007
* Autor: Raphael Rossiter
*/
function validaEnterAceitaZERO(tecla, caminhoActionReload, nomeCampo) {

	var form = document.forms[0];

	
	var objetoCampo = eval("form." + nomeCampo);
	var valorCampo = eval("form." + nomeCampo + ".value");

	if (document.all) {
		var codigo = event.keyCode;
    }
	else {
       var codigo = tecla.which;
    }

	if (codigo == 13) {
		if (!isNaN(valorCampo) && valorCampo.length > 0 && valorCampo.indexOf(',') == -1 &&
			valorCampo.indexOf('.') == -1){
			
			// Retira os espacos em branco da string passada.
			objetoCampo.value = trim(valorCampo);
			if (objetoCampo.value.length > 0){
				return pesquisaEnterAceitaZERO(tecla, caminhoActionReload, objetoCampo);
			}
		}
	}
	else{
		return true;
	}
}


/* Utilizado nos formularios que realizam pesquisas atraves do botao Enter
   EX: validaEnter(event, String, String)
   event - Captura qual foi o evento realizado pelo usuario (Padrao)
   caminhoActionReload - Action responsavel por realizar a consulta.
   nomeCampo - Nome do campo que possui o codigo para ser consultado (Recebe uma string).
   OBS - Realiza uma filtragem no que foi digitado pelo usuario (so aceita inteiros maiores que zero)
   
   Author: Raphael Rossiter, Fernanda Paiva
*/
function validaEnterComMensagem(tecla, caminhoActionReload, nomeCampoForm, nomeCampoMSG) {

	var form = document.forms[0];

	var objetoCampo = eval("form." + nomeCampoForm);
	var valorCampo = trim(eval("form." + nomeCampoForm + ".value"));
	var indesejaveis = "~{}^%$[]@|`\\<?\#?!;*>\"\'";
	var teste = true;
	
	if (document.all) {
		var codigo = event.keyCode;
    }
	else {
       var codigo = tecla.which;
    }

	if (codigo == 13) {
	    if (valorCampo.length == 0){
	     alert("Informe " + nomeCampoMSG + ".");
	     return false;
	    }
	
		if (!testarCampoValorZero(objetoCampo, nomeCampoMSG)){
			return false;
		}
		
		// alterado para verificar se foi digitado algum caracter especial - Fernanda Paiva - 11/07/2006
		for (var i=0; i<indesejaveis.length; i++) {
			if ((valorCampo.indexOf(indesejaveis.charAt(i))) != -1 ){
				teste = false;
			}
      	}
      	if(teste == false)
      	{
	      	alert(nomeCampoMSG + " possui caracteres especiais.");
			return false;
		}
		else if (valorCampo.length > 0 && (isNaN(valorCampo) || valorCampo.indexOf(',') != -1 ||
			valorCampo.indexOf('.') != -1)){

			alert(nomeCampoMSG + " deve somente conter n�meros positivos.");
			return false;
		}
		else{
			validaEnter(tecla, caminhoActionReload, nomeCampoForm);
		}
	}
	else{
		return false;
	}
}


/*
*
* Validacao do enter aceitando o valor zero como parametro
* Data: 18/05/2007
* Autor: Raphael Rossiter
*/
function validaEnterComMensagemAceitaZERO(tecla, caminhoActionReload, nomeCampoForm, nomeCampoMSG) {

	var form = document.forms[0];

	var objetoCampo = eval("form." + nomeCampoForm);
	var valorCampo = trim(eval("form." + nomeCampoForm + ".value"));
	var indesejaveis = "~{}^%$[]@|`\\<?\#?!;*>\"\'";
	var teste = true;
	
	if (document.all) {
		var codigo = event.keyCode;
    }
	else {
       var codigo = tecla.which;
    }

	if (codigo == 13) {
	    if (valorCampo.length == 0){
	     alert("Informe " + nomeCampoMSG + ".");
	     return false;
	    }
	
		// alterado para verificar se foi digitado algum caracter especial - Fernanda Paiva - 11/07/2006
		for (var i=0; i<indesejaveis.length; i++) {
			if ((valorCampo.indexOf(indesejaveis.charAt(i))) != -1 ){
				teste = false;
			}
      	}
      	if(teste == false)
      	{
	      	alert(nomeCampoMSG + " possui caracteres especiais.");
			return false;
		}
		else if (valorCampo.length > 0 && (isNaN(valorCampo) || valorCampo.indexOf(',') != -1 ||
			valorCampo.indexOf('.') != -1)){

			alert(nomeCampoMSG + " deve somente conter n�meros positivos.");
			return false;
		}
		else{
			validaEnterAceitaZERO(tecla, caminhoActionReload, nomeCampoForm);
		}
	}
	else{
		return false;
	}
}

/* Utilizado nos formularios que realizam pesquisas atraves do botao Enter,
   difere da funcao de validaEnter,por que pode ser passado uma string
   EX: validaEnterString(event, String, String)
   event - Captura qual foi o evento realizado pelo usuario (Padrao)
   caminhoActionReload - Action responsavel por realizar a consulta.
   nomeCampo - Nome do campo que possui a string para ser consultado (Recebe uma string).
   OBS - Realiza uma filtragem no que foi digitado pelo usuario 
*/
function validaEnterString(tecla, caminhoActionReload, nomeCampo) {

	var form = document.forms[0];

	var objetoCampo = eval("form." + nomeCampo);
	var valorCampo = eval("form." + nomeCampo + ".value");

	if (document.all) {
		var codigo = event.keyCode;
    }
	else {
       var codigo = tecla.which;
    }

	if (codigo == 13) {
		if (valorCampo.length > 0){

			// Retira os espacos em branco da string passada.
			objetoCampo.value = trim(valorCampo);
			if (objetoCampo.value.length > 0){
				return pesquisaEnter(tecla, caminhoActionReload, form);
			}
		}
	}
	else{
		return true;
	}
}

/* Utilizado nos formularios que realizam pesquisas atraves do botao Enter,
   difere da funcao de validaEnterString,por que terao coloca todas as letras para maiusculas
   EX: validaEnterStringSemUpperCase(event, String, String)
   event - Captura qual foi o evento realizado pelo usuario (Padrao)
   caminhoActionReload - Action responsavel por realizar a consulta.
   nomeCampo - Nome do campo que possui a string para ser consultado (Recebe uma string).
   OBS - Realiza uma filtragem no que foi digitado pelo usuario 
*/
function validaEnterStringSemUpperCase(tecla, caminhoActionReload, nomeCampo) {

	var form = document.forms[0];

	var objetoCampo = eval("form." + nomeCampo);
	var valorCampo = eval("form." + nomeCampo + ".value");

	if (document.all) {
		var codigo = event.keyCode;
    }
	else {
       var codigo = tecla.which;
    }

	if (codigo == 13) {
		if (valorCampo.length > 0){

			// Retira os espacos em branco da string passada.
			objetoCampo.value = trim(valorCampo);
			if (objetoCampo.value.length > 0){
				return pesquisaEnterSemUpperCase(tecla, caminhoActionReload, form);
			}
		}
	}
	else{
		return true;
	}
}

/* Utilizado nos formularios que realizam pesquisas que terao irao utilizar o botao ENTER
   EX: validaEnter(String, String)
   caminhoActionReload - Action responsavel por realizar a consulta.
   nomeCampo - Nome do campo que possui o codigo para ser consultado (Recebe uma string).
   OBS - Realiza uma filtragem no que foi digitado pelo usuario (so aceita inteiros maiores que zero)
*/
function validaEnterSemEvento(caminhoActionReload, nomeCampo) {

	var form = document.forms[0];

	var objetoCampo = eval("form." + nomeCampo);
	var valorCampo = eval("form." + nomeCampo + ".value");

	
	if (!isNaN(valorCampo) && valorCampo.length > 0 && valorCampo.indexOf(',') == -1 &&
		valorCampo.indexOf('.') == -1 && ((valorCampo * 1) > 0)){

		// Retira os espacos em branco da string passada.
		objetoCampo.value = trim(valorCampo);
		if (objetoCampo.value.length > 0){
			return pesquisaEnterSemEvento(caminhoActionReload);
		}
	}
	else{
		return true;
	}
}


/* Realiza uma consulta de acordo com a dependencia passada como parametro
   tecla - Acao realizada pelo usuario (valor padrao = event).
   caminhoActionReload - Action responsavel por realizar a consulta.
   nomeCampo - Objeto HTML que sera consultado de acordo com a dependencia passada (e para passar o objeto HTML e terao uma string).
   idDependencia - codigo do objeto que sera utilizado como dependencia (e para passar um valor do tipo inteiro).
   nomeDependencia - Nome que sera utilizado na mensagem de retorno para o usuario, caso ocorra alguma excecao (e para passar uma string).
*/
function validaEnterDependencia(tecla, caminhoActionReload, nomeCampo, idDependencia, nomeDependencia){
      var form = document.forms[0];

      if (document.all) {
		var codigo = event.keyCode;
      }
	  else {
        var codigo = tecla.which;
      }
      if (codigo == 13) {

		  if( idDependencia.length < 1 || isNaN(idDependencia) || ((idDependencia * 1) == 0) ||
			  idDependencia.indexOf(',') != -1 || idDependencia.indexOf('.') != -1 || ((idDependencia * 1) == 0)){
			nomeCampo.value = "";
            alert('Informe ' + nomeDependencia);
            return false;
          }
		  else{
            validaEnter(tecla,caminhoActionReload, nomeCampo.name);
          }
      }
	  else {
		return true;
      }
}

/*
*
* Validacao do enter aceitando o valor zero como parametro
* Data: 15/05/2007
* Autor: Raphael Rossiter
*/
function validaEnterDependenciaAceitaZERO(tecla, caminhoActionReload, nomeCampo, idDependencia, nomeDependencia){
      var form = document.forms[0];

      if (document.all) {
		var codigo = event.keyCode;
      }
	  else {
        var codigo = tecla.which;
      }
      if (codigo == 13) {

		  if( idDependencia.length < 1 || isNaN(idDependencia) || ((idDependencia * 1) == 0) ||
			  idDependencia.indexOf(',') != -1 || idDependencia.indexOf('.') != -1 || ((idDependencia * 1) == 0)){
			nomeCampo.value = "";
            alert('Informe ' + nomeDependencia);
            return false;
          }
		  else{
            validaEnterAceitaZERO(tecla,caminhoActionReload, nomeCampo.name);
          }
      }
	  else {
		return true;
      }
}


/* Realiza uma consulta de acordo com a dependencia passada como parametro
   tecla - Acao realizada pelo usuario (valor padrao = event).
   caminhoActionReload - Action responsavel por realizar a consulta.
   nomeCampo - Objeto HTML que sera consultado de acordo com a dependencia passada (e para passar o objeto HTML e terao uma string).
   idDependencia - codigo do objeto que sera utilizado como dependencia (e para passar um valor do tipo inteiro).
   nomeDependencia - Nome que sera utilizado na mensagem de retorno para o usuario, caso ocorra alguma excecao (e para passar uma string).
*/
function validaEnterDependenciaComMensagem(tecla, caminhoActionReload, nomeCampo, idDependencia, nomeDependencia, nomeCampoMSG){
      var form = document.forms[0];
	  var indesejaveis = "~{}^%$[]@|`\\<?\#?!;*>\"\'";
	  var teste = true;
	
      if (document.all) {
		var codigo = event.keyCode;
      }
	  else {
        var codigo = tecla.which;
      }
      if (codigo == 13) {

		  if (nomeCampo.value.length == 0){
	       	alert("Informe " + nomeCampoMSG + ".");
	     	return false;
	      }
	      else if (!testarCampoValorZero(nomeCampo, nomeCampoMSG)){
			return false;
		  }
		  else if ( idDependencia.length < 1 || isNaN(idDependencia) || ((idDependencia * 1) == 0) ||
			  idDependencia.indexOf(',') != -1 || idDependencia.indexOf('.') != -1 || ((idDependencia * 1) == 0)){
			
			nomeCampo.value = "";
            alert('Informe ' + nomeDependencia);
            return false;
            
          }
          
        // alterado para verificar se foi digitado algum caracter especial - Vivianne Sousa - 27/07/2006
		for (var i=0; i<indesejaveis.length; i++) {
			if ((nomeCampo.value.indexOf(indesejaveis.charAt(i))) != -1 ){
				teste = false;
			}
      	}
      	if(teste == false)
      	{
	      	alert(nomeCampoMSG + " possui caracteres especiais.");
			return false;
		}
          
          
		  else if (nomeCampo.value.length > 0 && (isNaN(nomeCampo.value) || nomeCampo.value.indexOf(',') != -1 ||
					nomeCampo.value.indexOf('.') != -1)){

			alert(nomeCampoMSG + " deve somente conter n�meros positivos.");
			return false;
		  
		  }else{
            
            validaEnter(tecla,caminhoActionReload, nomeCampo.name);
          }
      }
	  else {
		return true;
      }
}

/*
* Aceitar zero como parametro ZERO
* Autor: Raphael Rossiter
* Data: 17/05/2007
*/
function validaEnterDependenciaComMensagemAceitaZERO(tecla, caminhoActionReload, nomeCampo, idDependencia, nomeDependencia, nomeCampoMSG){
      var form = document.forms[0];
	  var indesejaveis = "~{}^%$[]@|`\\<?\#?!;*>\"\'";
	  var teste = true;
	
      if (document.all) {
		var codigo = event.keyCode;
      }
	  else {
        var codigo = tecla.which;
      }
      if (codigo == 13) {

		  if (nomeCampo.value.length == 0){
	       	alert("Informe " + nomeCampoMSG + ".");
	     	return false;
	      }
	      else if ( idDependencia.length < 1 || isNaN(idDependencia) || ((idDependencia * 1) == 0) ||
			  idDependencia.indexOf(',') != -1 || idDependencia.indexOf('.') != -1 || ((idDependencia * 1) == 0)){
			
			nomeCampo.value = "";
            alert('Informe ' + nomeDependencia);
            return false;
            
          }
          
        // alterado para verificar se foi digitado algum caracter especial - Vivianne Sousa - 27/07/2006
		for (var i=0; i<indesejaveis.length; i++) {
			if ((nomeCampo.value.indexOf(indesejaveis.charAt(i))) != -1 ){
				teste = false;
			}
      	}
      	if(teste == false)
      	{
	      	alert(nomeCampoMSG + " possui caracteres especiais.");
			return false;
		}
          
          
		  else if (nomeCampo.value.length > 0 && (isNaN(nomeCampo.value) || nomeCampo.value.indexOf(',') != -1 ||
					nomeCampo.value.indexOf('.') != -1)){

			alert(nomeCampoMSG + " deve somente conter n�meros positivos.");
			return false;
		  
		  }else{
            
            validaEnterAceitaZERO(tecla,caminhoActionReload, nomeCampo.name);
          }
      }
	  else {
		return true;
      }
}



function pesquisaColecaoReload(caminhoActionReload, nomeCampo) {

	var form = document.forms[0];

	var valorCampo = eval("form." + nomeCampo + ".value");
    form.action = caminhoActionReload;
	toUpperCase(form);
    form.submit();
}




// A duas funcoes abaixo tem por finalidade redimensionar um popup
/* Basta chamar a funcao resizePage(url, largura, altura) passando como
   parametro respectivamente o link, a nova largura e a nova altura do popup */
// EX: resizePageComLink("teste.html", 350, 230);
//=================================================================
  function resizePageComLink(url, largura, altura){
       window.location.href = url;

	   //Para abrir o popup centralizado
		var height = window.screen.height - 160;
		var width = window.screen.width;
		var top = (height - altura)/2;
		var left = (width - largura)/2;

		resizeNow(largura, altura, top, left);
  }

  function resizePageSemLink(largura, altura){

	   //Para abrir o popup centralizado
		var height = window.screen.height - 160;
		var width = window.screen.width;
		var top = (height - altura)/2;
		var left = (width - largura)/2;

		resizeNow(largura, altura, top, left);
  }

  function resizeNow(largura, altura, top, left){
       window.resizeTo(largura, altura);
       window.moveTo(left , top);
  }
//=================================================================




// Realiza um reload na pagina inicial de acordo com a url passada
function chamarReload(pagina){
	opener.window.location.href=pagina
}


// Realiza um reload na pagina inicial
function chamarReloadSemParametro(){
	opener.window.location.href = opener.window.location.href;
//	opener.window.location.reload();
}


// Realiza um submit na pagina inicial
function chamarSubmit(){
	toUpperCase(opener.window.document.forms[0]);
	opener.window.document.forms[0].submit();
}


// Realiza um submit na pagina inicial de acordo com a url passada
function chamarSubmitComUrl(pagina){
	toUpperCase(opener.window.document.forms[0]);
	opener.window.document.forms[0].action = pagina; 
	opener.window.document.forms[0].submit();
}


// Realiza um submit na pagina inicial com passagem de parametro (Utilizado na localidade)
function chamarSubmitEspecial(operacao){

	if (operacao == "1" || operacao == "2"){
		opener.window.document.getElementById("limparCampos").value = "1";
	}

	toUpperCase(opener.window.document.forms[0]);
	opener.window.document.forms[0].submit();
}



/* Retorna um objeto do formulario de acordo com os parametros passados
   form - Formulario que esta sendo utilizado (e para passar o objeto HTML e terao uma string).
   nome - Nome do objeto HTML que sera retornado (e para passar uma string).
*/
function returnObject(form, nome){
	var retorno;
	for(indice = 0; indice < form.elements.length; indice++){
		campo = form.elements[indice];
		if (campo.name == nome){
			retorno = campo;
			break;
		}
	}
	return retorno;
}




//Ativa todos os elementos do tipo checkbox do formulario existente
function marcarTodos(){

	for (var i=0;i < document.forms[0].elements.length;i++){
		var elemento = document.forms[0].elements[i];
		if (elemento.type == "checkbox" && elemento.disabled == false){
			elemento.checked = true;
		}
	}
}

function marcarTodosListBox(nomeListBox){

	for (var i=0;i < document.forms[0].elements.length;i++){
		var elemento = document.forms[0].elements[i];
		if (elemento.type == "checkbox" && elemento.name == nomeListBox){
			elemento.checked = true;
		}
	}
}


//Desativa todos os elementos do tipo checkbox do formulario existente
function desmarcarTodos() {

	for (var i=0;i < document.forms[0].elements.length;i++){
		var elemento = document.forms[0].elements[i];
		if (elemento.type == "checkbox"){
			elemento.checked = false;
		}
	}
}

function desmarcarTodosListBox(nomeListBox){

	for (var i=0;i < document.forms[0].elements.length;i++){
		var elemento = document.forms[0].elements[i];
		if (elemento.type == "checkbox" && elemento.name == nomeListBox){
			elemento.checked = false;
		}
	}
}


//Testa se o campo foi digitado somente com zeros
function testarCampoValorZero(valor, nomeCampo) {

	var retorno = true;
	if(valor != null){
	var conteudo = valor.value.replace(",","");
	var conteudo = conteudo.replace(".","");
	
	if (trim(valor.value).length > 0){
	
		if (isNaN(valor.value)) {
           
			for (x= 0; x < conteudo.length; x++){
				
				if (conteudo.substr(x, 1) != "0"){
					retorno = true;
					break;
				}
				else{
					retorno = false;	
				}
			}
			
			if (!retorno){
				alert( nomeCampo + ' deve somente conter n�meros positivos.');
			}
		}
		else {
		
			var intValorCampo = valor.value * 1;
	
			if (intValorCampo == 0) {
				alert( nomeCampo + ' deve somente conter n�meros positivos.');
        		retorno =  false;
			}
		}
	}
	}
	
	return retorno;
}

function testarCampoValorZeroDecimal(valor, nomeCampo) {

	var retorno = true;
	
	var conteudo = valor.value.replace(",","");
	var conteudo = conteudo.replace(".","");
	
	if (trim(valor.value).length > 0){
	
		if (isNaN(valor.value)) {
           
			for (x= 0; x < conteudo.length; x++){
				
				if (conteudo.substr(x, 1) != "0"){
					retorno = true;
					break;
				}
				else{
					retorno = false;	
				}
			}
			
			if (!retorno){
				alert( nomeCampo + ' deve somente conter n�meros decimais positivos.');
			}
		}
		else {
		
			var intValorCampo = valor.value * 1;
	
			if (intValorCampo == 0) {
				alert( nomeCampo + ' deve somente conter n�meros  decimais positivos.');
        		retorno =  false;
			}
		}
	}
	
	return retorno;
}

//Testa se o campo foi digitado somente com zeros
function testarCampoValorInteiroComZero(valor, nomeCampo) {

	var retorno = true;
	if(valor != null){
	var conteudo = valor.value.replace(",","");
	var conteudo = conteudo.replace(".","");
	
	if (trim(valor.value).length > 0){
	    if (isNaN(valor.value)) {
           	 alert( nomeCampo + ' deve somente conter n�meros positivos.');
        	 retorno =  false;
			}
		}
	}
	return retorno;
}

function testarCampoValorDecimalComZero(valor, nomeCampo) {

	var retorno = true;
	
	var conteudo = valor.value.replace(",","");
	var conteudo = conteudo.replace(".","");
	
	 var iValue = parseFloat(valor.value);
	
	if (trim(valor.value).length > 0){
	    if (isNaN(iValue)) {
           	alert( nomeCampo + ' deve somente conter n�meros decimais positivos.');
			 retorno =  false;
			}
	}
	
	return retorno;
}



/**
* Testa se em campos Decimal tem o valor 0
* Author: Rafael Santos
* Data: 28/06/2006
*/
function testarCampoDecimalValorZero(valor, nomeCampo) {

	var retorno = true;
	
	var conteudo = valor.value.replace(",","");
	var conteudo = conteudo.replace(".","");
	
	if (trim(valor.value).length > 0){
	
		if (isNaN(valor.value)) {
           
			for (x= 0; x < conteudo.length; x++){
				
				if (conteudo.substr(x, 1) != "0"){
					retorno = true;
					break;
				}
				else{
					retorno = false;	
				}
			}
			
			if (!retorno){
				alert( nomeCampo + ' deve somente conter n�meros decimais positivos.');
			}
		}
		else {
		
			var intValorCampo = valor.value * 1;
	
			if (intValorCampo == 0) {
				alert( nomeCampo + ' deve somente conter n�meros decimais positivos.');
        		retorno =  false;
			}
		}
	}
	
	return retorno;
}




function abrirPopupDependencia(url, idDependencia, nomeMSG, altura, largura){

	if (idDependencia.length < 1 || isNaN(idDependencia) || ((idDependencia * 1) == 0)){
		alert("Informe " + nomeMSG);
	}
	else{
		abrirPopup(url , altura, largura);
	}
}





function redirecionarSubmitDependencia(caminhoAction,idDependencia,nomeMSG) {

	 if (idDependencia.length < 1 || isNaN(idDependencia) || parseInt(idDependencia) == 0){
        alert("Informe " + nomeMSG);
     }
     else{
		var form = document.forms[0];
		form.action = caminhoAction;
		toUpperCase(form);
		form.submit();
     }
}


/* Coloca toda a cadeia de caracteres em caixa alta para os campos do tipo "text" e "hidden".
   Parametro: O formulario que esta sendo utilizado. */
function toUpperCase(form){

	for (var i=0;i < form.elements.length;i++){
		var elemento = form.elements[i];
		if (elemento.type == "text"){
			
			//Retira os espacos em branco da string passada
			elemento.value = trim(elemento.value);
			
			if (elemento.style == null || elemento.style.textTransform != "none"){
				
				elemento.value = elemento.value.toUpperCase();
			}
		}
		else if (elemento.type == "hidden"){

			//Retira os espacos em branco da string passada
			elemento.value = trim(elemento.value);

			elemento.value = elemento.value.toUpperCase();
		}
	}
}


function submeterFormPadrao(form){
     toUpperCase(form)
	 form.submit();
}




/** Formata os caracteres digitados pelo usuario no formato DD/MM/AAAA **/
//	Recebe como parametro o evento realizado pelo usuario (comando event) e o indice do campo que sera formatado
//	Funciona em ambos os browsers (Firefox e IE).

function formataData(e, campo){

	if (blockNumbers(e)){
		campo.value = filtraCampo(campo);
		vr = campo.value;
		tam = vr.length;

		if ( tam > 2 && tam < 5 )
			campo.value = vr.substr( 0, tam - 2  ) + '/' + vr.substr( tam - 2, tam );
		if ( tam >= 5 && tam <= 8 ) 
			campo.value = vr.substr( 0, 2 ) + '/' + vr.substr( 2, 2 ) + '/' + vr.substr( 4, 4 );
	}
	else{
		if(window.event) {
			// for IE, e.keyCode or window.event.keyCode can be used
			if(e.keyCode != 13 && e.keyCode != 8 && e.keyCode != 9 && e.keyCode != 46){
				e.keyCode = 0;
			}
		}
		else{
			// netscape
			if(e.keyCode != 13 && e.keyCode != 8 && e.keyCode != 9 && e.keyCode != 46){
				e.preventDefault();
			}
		}
	}
}



/** Tratamento para os campos que serao formatados **/
//	Recebe como parametro o campo que sera formatado
//	Funciona em ambos os browsers (Firefox e IE)

function filtraCampo(campo){
	var s = "";
	var cp = "";
	vr = campo.value;
	tam = vr.length;
	for (i = 0; i < tam ; i++) {  
		if (vr.substring(i,i + 1) != "/" && vr.substring(i,i + 1) != "-" && vr.substring(i,i + 1) != "."  && vr.substring(i,i + 1) != "," ){
		 	s = s + vr.substring(i,i + 1);}
	}
	campo.value = s;
	return cp = campo.value
}




/** Desabilita a digitacao de caracteres que terao forem n�meros **/
//	Recebe como parametro o evento realizado pelo usuario (comando event)
//	Funciona em ambos os browsers (Firefox e IE)
	
function blockNumbers(e)
{
    var key;
    var keychar;
    var reg;
    
    if(window.event) {
        // for IE
        key = e.keyCode; 
    }
    else{
        // netscape
        key = e.which; 
    }
    

    keychar = String.fromCharCode(key);
    reg = /\d/;

    return reg.test(keychar);
    
}



/** Formata os caracteres digitados pelo usuario no formato HH:MM **/
//	Recebe como parametro o evento realizado pelo usuario (comando event) e o campo que sera formatado
//	Funciona em ambos os browsers (Firefox e IE).

function formataHora(e, campo){

	if (blockNumbers(e)){
		campo.value = filtraCampo(campo);
		vr = campo.value;
		tam = vr.length;

		if ( tam > 2 && tam < 5 )
			campo.value = vr.substr( 0, tam - 1  ) + ':' + vr.substr( tam - 1, tam );
	}
	else{
		if(window.event) {
			// for IE
			if(e.keyCode != 13 && e.keyCode != 8 && e.keyCode != 9 && e.keyCode != 46){
				e.keyCode = 0;
			}
		}
		else{
			// netscape
			if(e.keyCode != 13 && e.keyCode != 8 && e.keyCode != 9 && e.keyCode != 46){
				e.preventDefault();
			}
		}
	}
}


/** Valida hora (HH:MM) **/
//	Recebe como parametro o campo que sera validado
//	Funciona em ambos os browsers (Firefox e IE).

function validaHoraMinuto(timeStr) {

	var timePat = /^(\d{2}):(\d{2})(:(\d{2}))?(\s?(AM|am|PM|pm))?$/;

	var matchArray = timeStr.match(timePat);

	if (matchArray == null) {
		alert("Hora inv�lida.");
		return false;
	}

	hour = matchArray[1];
	minute = matchArray[2];


	if (hour < 0  || hour > 23) {
		alert("Hora deve estar entre 0 e 23.");
		return false;
	}

	if (minute < 0 || minute > 59) {
		alert ("Minuto deve estar entre 0 e 59.");
		return false;
	}

	return true;
}

// Validacao com o nome do campo na mensagem
// Roberta Costa 03/08/06
function validaHoraMinutoMensagem(timeStr, campo) {

	var timePat = /^(\d{2}):(\d{2})(:(\d{2}))?(\s?(AM|am|PM|pm))?$/;

	var matchArray = timeStr.match(timePat);

	if (matchArray == null) {
		alert(campo + " inv�lida.");
		return false;
	}

	hour = matchArray[1];
	minute = matchArray[2];


	if (hour < 0  || hour > 23) {
		alert("Hora deve estar entre 0 e 23.");
		return false;
	}

	if (minute < 0 || minute > 59) {
		alert ("Minuto deve estar entre 0 e 59.");
		return false;
	}

	return true;
}

function validarHoraCompleta(horaMinutoSegundo) {
	if (horaMinutoSegundo != "") {
    	var horaMinutoSegundo = horaMinutoSegundo.split(':');
	    if (horaMinutoSegundo.length == 3) {
    		if(horaMinutoSegundo[0] <= 23 && horaMinutoSegundo[0] >= 0 &&
		       horaMinutoSegundo[1] <= 59 && horaMinutoSegundo[1] >= 0 &&
   			   horaMinutoSegundo[2] <= 59 && horaMinutoSegundo[2] >= 0){
          	   return true;
      	    }
   			else {
   				alert ("Hora inv�lida.");
  				return false;
      		}
 		}
 		else {
 			alert ("Hora inv�lida.");
      		return false;
    	}
	}
  	else {
  		alert ("Hora inv�lida.");
 		return true;
  	}
}

// Validacao com o nome do campo na mensagem
// Roberta Costa 03/08/06
function validarHoraCompletaMensagem(horaMinutoSegundo, campo) {
	if (horaMinutoSegundo != "") {
    	var horaMinutoSegundo = horaMinutoSegundo.split(':');
	    if (horaMinutoSegundo.length == 3) {
    		if(horaMinutoSegundo[0] <= 23 && horaMinutoSegundo[0] >= 0 &&
		       horaMinutoSegundo[1] <= 59 && horaMinutoSegundo[1] >= 0 &&
   			   horaMinutoSegundo[2] <= 59 && horaMinutoSegundo[2] >= 0){
          	   return true;
      	    }
   			else {
   				alert (campo + " inv�lida.");
  				return false;
      		}
 		}
 		else {
 			alert (campo + " inv�lida.");
      		return false;
    	}
	}
  	else {
  		alert (campo + " inv�lida.");
 		return true;
  	}
}

/** Utilizada para o tratamento de campos que possuam casas decimais,
	converte o caracter "," pelo caracter "." com o objetivo de evitar exceptions nos Actions. **/
//	Recebe o campo que sofrera a modificacao.
function converteVirgula(objText){
	if (objText.value.length > 0){
		objText.value = objText.value.replace(",", "."); 
	}

}


function convertePonto(objText){
	if (objText.value.length > 0){
		objText.value = objText.value.replace(".", ","); 
	}

}


//Compara duas datas no formato de strings
// Hugo Leonardo  19/01/2011
// Adicionado ">=" e "<="
function comparaData(data1, operador, data2 ){

        sDia1 = data1.substr(0,2);
        sMes1 = data1.substr(3,2);
        sAno1 = data1.substr(6,4);
        sdata1 = new Date(sMes1 +"/"+ sDia1 +"/"+ sAno1);

        sDia2 = data2.substr(0,2);
        sMes2 = data2.substr(3,2);
        sAno2 = data2.substr(6,4);
        sdata2 = new Date(sMes2 +"/"+ sDia2 +"/"+ sAno2);

        if (operador == '<'){
                if ( sdata1 < sdata2 ){
                        return true;
                } else {
                        return false;
                }
        }
        
        if (operador == '<='){
                if ( sdata1 < sdata2 || (trim(data1) == trim(data2))){
                        return true;
                } else {
                        return false;
                }
        }

        if (operador ==  '>'){
                if ( sdata1 > sdata2 ){
                        return true;
                } else {
                        return false;
                }
        }
        
        if (operador ==  '>='){
                if ( sdata1 > sdata2 || (trim(data1) == trim(data2))){
                        return true;
                } else {
                        return false;
                }
        }
        
        if (operador ==  '='){
        		if ( trim(data1) == trim(data2) ){
                        return true;
                } else {
                		return false;
                }
        }
}

//Compara dois peridos (mesAno) no formato de strings
// Hugo Leonardo
function comparaMesAno(mesAno1, operador, mesAno2 ){

        sMes1 = mesAno1.substr(0,2);
        sAno1 = mesAno1.substr(3,7);
        smesAno1 = sAno1+sMes1 ;

        sMes2 = mesAno2.substr(0,2);
        sAno2 = mesAno2.substr(3,7);
        smesAno2 = sAno2+sMes2;

        if (operador == '<'){
                if ( smesAno1 < sdmesAno2 ){
                        return true;
                } else {
                        return false;
                }
        }
        
        if (operador == '<='){
                if ( smesAno1 < smesAno2 || (trim(smesAno1) == trim(smesAno2))){
                        return true;
                } else {
                        return false;
                }
        }

        if (operador ==  '>'){
                if ( smesAno1 > smesAno2 ){
                        return true;
                } else {
                        return false;
                }
        }
        
        if (operador == '>='){
                if ( smesAno1 > sdmesAno2 || (trim(smesAno1) == trim(sdmesAno2))){
                        return true;
                } else {
                        return false;
                }
        }
        
        if (operador ==  '='){
        		if ( trim(smesAno1) == trim(smesAno2) ){
                        return true;
                } else {
                		return false;
                }
        }
}

/**
*
* funcao similar ao trim() do asp que tira os espacos em branco de antes e depois do texto. 
*
**/
function trim(str){

	while (str.charAt(0) == " "){
		str = str.substr(1,str.length -1);
	}
	
	while (str.charAt(str.length-1) == " "){
		str = str.substr(0,str.length-1);
	}
	
	return str;
} 



// Formatacao: MM/AAAA 
//EX: onkeyup="mascaraAnoMes(objeto, event);" 
function mascaraAnoMes(mydata, tecla){ 
     
	 if (document.all) {
        var codigo = event.keyCode;
	 }
	 else{
        var codigo = tecla.which;
	 }

	 if (mydata.value.length == 2 && (codigo != 8 && codigo != 46)){ 
         mydata.value = mydata.value + '/'; 
     } 

     //if (mydata.value.length == 7){ 
        // verificaAnoMes(mydata); 
     //}
} 

//Valida um campo do tipo Ano m�s           
function verificaAnoMes(mydata) {
	
	var situacao = true;
	
	if (mydata.value.length == 7) {
	
		dia = 1;
    	mes = mydata.value.substring(0,2); 
    	ano = mydata.value.substring(3,7); 

    	if ((!isNaN(mes) || !isNaN(ano)) && (mes.indexOf('.') == -1 && mes.indexOf(',') == -1 && mes.indexOf('/') == -1) &&
			(ano.indexOf('.') == -1 && ano.indexOf(',') == -1 && ano.indexOf('/') == -1)) {
    	
    		// verifica se o mes e valido 
	    	if ((mes * 1) >= 1 && (mes * 1) <= 12 ) { 
	        
	        	// verifica se o ano e valido
	        	if ((ano * 1) != 0 && (ano * 1) >= 1980) {
	        	
	        		// verifica se e ano bissexto 
	    			if ((mes * 1) == 2 && ((dia * 1) < 1 || (dia * 1) > 29 
	    				|| ((dia * 1) > 28 && (((ano * 1) / 4) != (ano * 1) / 4)))) { 
	       				situacao = false; 
	    			}
	        	}
	        	else{
	        		situacao = false;
	        	}
	    	}
	    	else{
	    		situacao = false;
	    	} 
		}
		else{
			situacao = false;
		}
    }
    else if (mydata.value.length > 0){
    	situacao = false;
    }
    
    if (!situacao) { 
	   alert("M�s/Ano inv�lidos.");
	   mydata.value = "";
	   mydata.focus(); 
	}
	
	return situacao;
}


function verificaAnoMesEspecial(mydata) {
	
	var situacao = true;
	
	if (mydata.value.length == 7) {
	
		dia = 1;
    	mes = mydata.value.substring(0,2); 
    	ano = mydata.value.substring(3,7); 

    	if ((!isNaN(mes) || !isNaN(ano)) && (mes.indexOf('.') == -1 && mes.indexOf(',') == -1 && mes.indexOf('/') == -1) &&
			(ano.indexOf('.') == -1 && ano.indexOf(',') == -1 && ano.indexOf('/') == -1)) {
    	
    		// verifica se o mes e valido 
	    	if ((mes * 1) >= 1 && (mes * 1) <= 12 ) { 
	        
	        	// verifica se o ano e valido
	        	if ((ano * 1) != 0 && (ano * 1) >= 1980) {
	        	
	        		// verifica se e ano bissexto 
	    			if ((mes * 1) == 2 && ((dia * 1) < 1 || (dia * 1) > 29 
	    				|| ((dia * 1) > 28 && (((ano * 1) / 4) != (ano * 1) / 4)))) { 
	       				situacao = false;
	       				alert("M�s e/ou Ano inv�lidos!");
	   					mydata.value = "";
	   					mydata.focus(); 
	    			}
	        	}
	        	else{
	        		situacao = false;
	        		alert("M�s e/ou Ano inv�lidos!");
	   				mydata.value = "";
	   				mydata.focus();
	        	} 
	    	}
	    	else{
	    		situacao = false;
	    		alert("M�s e/ou Ano inv�lidos!");
	   			mydata.value = "";
	   			mydata.focus();
	    	} 
		}
		else{
			situacao = false;
			alert("M�s e/ou Ano inv�lidos!");
	   		mydata.value = "";
	   		mydata.focus();
		}
    }
    
    
    return situacao;
}

// valida AnoMes sem retornar um alert
function validaAnoMesSemAlert(mydata) {
	
	var situacao = true;
	
	if (mydata.value.length == 7) {
	
		dia = 1;
    	mes = mydata.value.substring(0,2); 
    	ano = mydata.value.substring(3,7); 

    	if ((!isNaN(mes) || !isNaN(ano)) && (mes.indexOf('.') == -1 && mes.indexOf(',') == -1 && mes.indexOf('/') == -1) &&
			(ano.indexOf('.') == -1 && ano.indexOf(',') == -1 && ano.indexOf('/') == -1)) {
    	
    		// verifica se o mes e valido 
	    	if ((mes * 1) >= 1 && (mes * 1) <= 12 ) { 
	        
	        	// verifica se o ano e valido
	        	if ((ano * 1) != 0 && (ano * 1) >= 1980) {
	        	
	        		// verifica se e ano bissexto 
	    			if ((mes * 1) == 2 && ((dia * 1) < 1 || (dia * 1) > 29 
	    				|| ((dia * 1) > 28 && (((ano * 1) / 4) != (ano * 1) / 4)))) { 
	       				situacao = false;
	       				mydata.value = "";
	   					mydata.focus(); 
	    			}
	        	}
	        	else{
	        		situacao = false;
	        		mydata.value = "";
	   				mydata.focus();
	        	} 
	    	}
	    	else{
	    		situacao = false;
	    		mydata.value = "";
	   			mydata.focus();
	    	} 
		}
		else{
			situacao = false;
	   		mydata.value = "";
	   		mydata.focus();
		}
    }
    return situacao;
}

//Author:Rafael Santos Data:30/03/2006 - Verifica o Ano Mes com a mensagem do alert personalida ao campo 
function verificaAnoMesMensagemPersonalizada(mydata,mensagem) {
	
	var situacao = true;
	if (mydata.value.length > 0) {
	
		if (mydata.value.length == 7) {
	
			dia = 1;
    		mes = mydata.value.substring(0,2); 
	    	ano = mydata.value.substring(3,7); 

    		if ((!isNaN(mes) || !isNaN(ano)) && (mes.indexOf('.') == -1 && mes.indexOf(',') == -1 && mes.indexOf('/') == -1) &&
				(ano.indexOf('.') == -1 && ano.indexOf(',') == -1 && ano.indexOf('/') == -1)) {
    	
    			// verifica se o mes e valido 
	    		if ((mes * 1) >= 1 && (mes * 1) <= 12 ) { 
	        
		        	// verifica se o ano e valido
	        		if ((ano * 1) != 0 && (ano * 1) >= 1980) {
	        	
			        	// verifica se e ano bissexto 
			    		if ((mes * 1) == 2 && ((dia * 1) < 1 || (dia * 1) > 29 
			    			|| ((dia * 1) > 28 && (((ano * 1) / 4) != (ano * 1) / 4)))) { 
			       			situacao = false;
			       			alert(mensagem);
			       			mydata.value = "";
			   				mydata.focus(); 
			    		}
			        }
			        else{
			        	situacao = false;
			        	alert(mensagem);
			        	mydata.value = "";
			   			mydata.focus();
			        }
			    }
		    	else{
	    			situacao = false;
	    			alert(mensagem);
	   				mydata.value = "";
		   			mydata.focus();
		    	} 
			}
			else{
				situacao = false;
				alert(mensagem);
		   		mydata.value = "";
		   		mydata.focus();
			}
	    }else{
			situacao = false;
			alert(mensagem);
	   		mydata.value = "";
	   		mydata.focus();
		}
	}
    
    
    return situacao;
}


// valida se ano mes eh numerico - Fernanda Karla
function validaAnoMesNumerico(mydata) {
	var situacao = true;
	if (mydata != "") {
		mes = mydata.value.substring(0,2); 
    	ano = mydata.value.substring(3,7); 
    	if (isNaN(mes) || isNaN(ano)){
			situacao = false;
	   		mydata.value = "";
	  		mydata.focus();
		}
	}
	return situacao;
}

// Formatacao de Data: DD/MM/AAAA 
//EX: onkeyup="mascara_data(this, event);" 
function mascaraData(mydata, tecla){ 
     
	 
	 
	 if (document.all) {
        var codigo = event.keyCode;
	 }
	 else{
        var codigo = tecla.which;
	 }

	 if (mydata.value.length == 2 && (codigo != 8 && codigo != 46)){ 
         mydata.value = mydata.value + '/'; 
     } 

     /*if (mydata.value.length == 10){ 
         verificaData(mydata); 
     }*/
     
	 if (mydata.value.length == 5 && (codigo != 8 && codigo != 46)){ 
         mydata.value = mydata.value + '/'; 
     } 
	 
} 

// Formatacao de Hora: HH:MM
//EX: onkeyup="mascaraHora(this, event);" 
function mascaraHora(mydata, tecla){ 
	 
	 var valido = false;
	 
	 if (document.all) {
        var codigo = event.keyCode;
	 } else {
        var codigo = tecla.which;
	 }
	 
	 if (mydata.value.length == 2 && (codigo != 8 && codigo != 46)){ 
         mydata.value = mydata.value + ':'; 
     }  
     
     if (mydata.value.length == 5){ 
		//valido = false;
    	var horaMinuto = mydata.value.split(':');
    	if (horaMinuto.length == 2) {    
    		if(horaMinuto[0] <= 23 && horaMinuto[0] >= 0 &&
    			horaMinuto[1] <= 59 && horaMinuto[1] >= 0){
    			valido = true;
    		}
    	}
    	
    	if (!valido)  {
			alert('Hora inv�lida.');
			mydata.value =  '';
			mydata.focus();
    	}
    }
    
    return valido;	
}


// Formatacao de Hora: HH:MM
//EX: onkeyup="mascaraHora(this, event);" 
function mascaraHoraSemMensagem(mydata, tecla){ 
	 
	 var valido = false;
	 
	 if (document.all) {
        var codigo = event.keyCode;
	 } else {
        var codigo = tecla.which;
	 }
	 
	 if (mydata.value.length == 2 && (codigo != 8 && codigo != 46)){ 
         mydata.value = mydata.value + ':'; 
     }  
     
     if (mydata.value.length == 5){ 
		
    	var horaMinuto = mydata.value.split(':');
    	if (horaMinuto.length == 2) {    
    		if(horaMinuto[0] <= 23 && horaMinuto[0] >= 0 &&
    			horaMinuto[1] <= 59 && horaMinuto[1] >= 0){
    			valido = true;
    		}
    	}
    }
    
    return valido;	
}

// Formata Hora: HH:MM e exibe a mensagem de erro com o nome do campo
// EX: onkeyup="mascaraHormascaraHoraMensagema(this, event, 'Nome Campo');" 
// Roberta Costa 01/08/2006
function mascaraHoraMensagem(mydata, tecla, campo){ 
	 if (document.all) {
        var codigo = event.keyCode;
	 } else {
        var codigo = tecla.which;
	 }
	 
	 if (mydata.value.length == 2 && (codigo != 8 && codigo != 46)){ 
         mydata.value = mydata.value + ':'; 
     }  
     
     if (mydata.value.length == 5){ 
		var valido = false;
    	var horaMinuto = mydata.value.split(':');
    	if (horaMinuto.length == 2) {    
    		if(horaMinuto[0] <= 23 && horaMinuto[0] >= 0 &&
    			horaMinuto[1] <= 59 && horaMinuto[1] >= 0){
    			valido = true;
    		}
    	}
    	
    	if (!valido)  {
			alert(campo+' inv�lida.'  );
			mydata.value =  '';
			mydata.focus();
    	}
    }	
}

// Formatacao e Validacao Hora: HH:MM:SS
//EX: onkeyup="mascaraHoraMinutoSegundo(this, event);" 
function mascaraHoraMinutoSegundo(mydata, tecla){ 
	 if (document.all) {
        var codigo = event.keyCode;
	 } else {
        var codigo = tecla.which;
	 }
	 
	 if (mydata.value.length == 2 && (codigo != 8 && codigo != 46)){ 
         mydata.value = mydata.value + ':'; 
     }
	 
	 if (mydata.value.length == 5 && (codigo != 8 && codigo != 46)){ 
         mydata.value = mydata.value + ':'; 
     }
     
     if (mydata.value.length == 8){ 
		var valido = false;
    	var horaMinuto = mydata.value.split(':');
    	if (horaMinuto.length == 3) {    
    		if(horaMinuto[0] <= 23 && horaMinuto[0] >= 0 &&
    			horaMinuto[1] <= 59 && horaMinuto[1] >= 0 &&
				horaMinuto[2] <= 59 && horaMinuto[2] >= 0){
    			valido = true;
    		}
    	}
    	
    	if (!valido)  {
			alert('Hora inv�lida.');
			mydata.value =  '';
			mydata.focus();
    	}
    }	
}


// Formata Hora: HH:MM:SS e exibe a mensagem de erro com o nome do campo
// EX: onkeyup="mascaraHoraMinutoSegundo(this, event, 'Nome Campo');" 
// Roberta Costa 01/08/2006
function mascaraHoraMinutoSegundoMensagem(mydata, tecla, campo){ 
	 if (document.all) {
        var codigo = event.keyCode;
	 } else {
        var codigo = tecla.which;
	 }
	 
	 if (mydata.value.length == 2 && (codigo != 8 && codigo != 46)){ 
         mydata.value = mydata.value + ':'; 
     }
	 
	 if (mydata.value.length == 5 && (codigo != 8 && codigo != 46)){ 
         mydata.value = mydata.value + ':'; 
     }
     
     if (mydata.value.length == 8){ 
		var valido = false;
    	var horaMinuto = mydata.value.split(':');
    	if (horaMinuto.length == 3) {    
    		if(horaMinuto[0] <= 23 && horaMinuto[0] >= 0 &&
    			horaMinuto[1] <= 59 && horaMinuto[1] >= 0 &&
				horaMinuto[2] <= 59 && horaMinuto[2] >= 0){
    			valido = true;
    		}
    	}
    	
    	if (!valido)  {
			alert(campo+' inv�lida.');
			mydata.value =  '';
			mydata.focus();
    	}
    }	
}

//Author: Rafael Santos Data: 30/03/2006 Valida a se a Data e uma data valida e critica com a mensagem personalizada enviada          
function verificaDataMensagemPersonalizada(mydata,mensagem) {
	
	
  situacao = true;      
  		
			
   if (mydata.value.length > 0) {
		
	 	if (mydata.value.length == 10) {
	
			
			dia = mydata.value.substring(0,2);
            mes = mydata.value.substring(3,5); 
            ano = mydata.value.substring(6,10); 

                
            if(!isNaN(dia) && !isNaN(mes) && !isNaN(ano)){
	            
              
		            // verifica se o mes e valido 
		            if ((mes * 1) >= 1 && (mes * 1) <= 12 ) {
		            
		            	// verifica se o ano e valido
			        	if ((ano * 1) != 0 && (ano * 1) >= 1900) {
			        	
			        		// verifica se e ano bissexto 
			    			if ((mes * 1) == 2 && ((dia * 1) < 1 || (dia * 1) > 29 
			    				|| ((dia * 1) > 28 && (((ano * 1) / 4) != (ano * 1) / 4)))) { 
			       				situacao = false; 
			    			}
			    			// verifica o dia valido para cada mes
			    			else if (((dia * 1) < 1 || (dia * 1) > 30) && ((mes * 1) == 4 || (mes * 1) == 6 || (mes * 1) == 9 || (mes * 1) == 11 ) || (dia * 1) > 31){
			    				situacao = false;
			    			}
			        	}
			        	else{
			        		situacao = false;
			        	} 
			        }
			        else{	            
		                situacao = false; 
		            }
	        }
            else{
              situacao = false; 
            } 
    
            if (situacao == false) { 
                alert(mensagem);
                
                mydata.value='';
                mydata.focus(); 
            } 

			}else{
				situacao = false;
	    	   alert(mensagem);
                
		         mydata.value='';
		         mydata.focus(); 
  			
				
			}
				
	}
				
	return situacao;
            
} 

//Valida a se a Data e uma data valida           
function verificaData (mydata) {
			dia = mydata.value.substring(0,2);
            mes = mydata.value.substring(3,5); 
            ano = mydata.value.substring(6,10); 

            situacao = true;      
                
            if(!isNaN(dia) && !isNaN(mes) && !isNaN(ano)){
	            
              
		            // verifica se o mes e valido 
		            if ((mes * 1) >= 1 && (mes * 1) <= 12 ) { 
			             // verifica se o ano e valido
			        	if ((ano * 1) != 0 && (ano * 1) >= 1900) {
			        	
			        		// verifica se e ano bissexto
							if ( (mes * 1) == 2 &&
							   ( (dia * 1) < 1 ||
							   ( (dia * 1) > 28 &&
							   ( ((ano * 1) % 4) != 0)))) {
							
								situacao = false;
							}

			    			// verifica o dia valido para cada mes
			    			else if (((dia * 1) < 1 || (dia * 1) > 30) && ((mes * 1) == 4 || (mes * 1) == 6 || (mes * 1) == 9 || (mes * 1) == 11 ) || (dia * 1) > 31){
			    				situacao = false;
			    			}
			        	}
			        	else{
			        		situacao = false;
			        	} 
			        }
			        else{	            
		                situacao = false; 
		            }
	        }
            else{
              situacao = false; 
            } 
    
            if (situacao == false) { 
                alert("Data inv�lida.");
                
                mydata.value='';
                mydata.focus(); 
            } 
            
            return situacao;
}

//Valida a se a Data e uma data valida Mensagem
function verificaDataMensagem(mydata, mensagem) {
			dia = mydata.value.substring(0,2);
            mes = mydata.value.substring(3,5); 
            ano = mydata.value.substring(6,10); 

            situacao = true;      
                
            if(!isNaN(dia) && !isNaN(mes) && !isNaN(ano)){
	            
              
		            // verifica se o mes e valido 
		            if ((mes * 1) >= 1 && (mes * 1) <= 12 ) { 
			             // verifica se o ano e valido
			        	if ((ano * 1) != 0 && (ano * 1) >= 1900) {
			        	
			        		// verifica se e ano bissexto 
			    			if ((mes * 1) == 2 && ((dia * 1) < 1 || (dia * 1) > 29 
			    				|| ((dia * 1) > 28 && (((ano * 1) / 4) != (ano * 1) / 4)))) { 
			       				situacao = false; 
			    			}
			    			// verifica o dia valido para cada mes
			    			else if (((dia * 1) < 1 || (dia * 1) > 30) && ((mes * 1) == 4 || (mes * 1) == 6 || (mes * 1) == 9 || (mes * 1) == 11 ) || (dia * 1) > 31){
			    				situacao = false;
			    			}
			        	}
			        	else{
			        		situacao = false;
			        	} 
			        }
			        else{	            
		                situacao = false; 
		            }
	        }
            else{
              situacao = false; 
            } 
    
            if (situacao == false) { 
                alert(mensagem);
                
                mydata.value='';
                mydata.focus(); 
            } 
            
            return situacao;
}     


//Valida a se a Data e uma data valida Mensagem e naum apaga a data do campo
function verificaDataMensagemSemApagar(mydata, mensagem) {
			dia = mydata.value.substring(0,2);
            mes = mydata.value.substring(3,5); 
            ano = mydata.value.substring(6,10); 

            situacao = true;      
                
            if(!isNaN(dia) && !isNaN(mes) && !isNaN(ano)){
	            
              
		            // verifica se o mes e valido 
		            if ((mes * 1) >= 1 && (mes * 1) <= 12 ) { 
			             // verifica se o ano e valido
			        	if ((ano * 1) != 0 && (ano * 1) >= 1900) {
			        	
			        		// verifica se e ano bissexto 
			    			if ((mes * 1) == 2 && ((dia * 1) < 1 || (dia * 1) > 29 
			    				|| ((dia * 1) > 28 && (((ano * 1) / 4) != (ano * 1) / 4)))) { 
			       				situacao = false; 
			    			}
			    			// verifica o dia valido para cada mes
			    			else if (((dia * 1) < 1 || (dia * 1) > 30) && ((mes * 1) == 4 || (mes * 1) == 6 || (mes * 1) == 9 || (mes * 1) == 11 ) || (dia * 1) > 31){
			    				situacao = false;
			    			}
			        	}
			        	else{
			        		situacao = false;
			        	} 
			        }
			        else{	            
		                situacao = false; 
		            }
	        }
            else{
              situacao = false; 
            } 
    
            if (situacao == false) { 
                alert(mensagem);
                
                mydata.focus(); 
            } 
            
            return situacao;
}    


// Retorna a quantidade de algarismos que compoem a parte inteira de um numero decimal
// EX: obterQuantidadeInteiro(10,33) - Retorno = 2
function obterQuantidadeInteiro(valor){

	var contadorInteiro = 0;
	
	if (valor.length > 0 && parseFloat(valor)){
		
		var valorReplace = valor.replace(",",".");
			
			
		for (x = 0; x < valorReplace.length; x++){
				
			if (valorReplace.substr(x, 1) != "."){
				contadorInteiro++;
			}
			else{
				break;
			}
		}
	}

	return contadorInteiro;
}


// Retorna a quantidade de algarismos que compoem a fracao de um numero decimal
// EX: obterQuantidadeFracao(10,3345) - Retorno = 4
function obterQuantidadeFracao(valor){

	var contadorFracao = 0;
	var begin = false;
	
	if (valor.length > 0 && parseFloat(valor)){
		
		var valorReplace = valor.replace(",",".");
			
		for (x = 0; x < valorReplace.length; x++){
				
			if (valorReplace.substr(x, 1) == "."){
				begin = true;
			}
			else if (begin){
				contadorFracao++;
			}
		 
		}
	}

	return contadorFracao;
}


// Retorna os algarismos sem virgula e ponto
// EX: obterNumerosSemVirgulaEPonto(10,3345) - Retorno = 103345
function obterNumerosSemVirgulaEPonto(valor){

	var begin = false;
	
	if (valor.length > 0){
		begin = true;
		var valorReplaceVirgula = valor.replace(",","");
		var valorReplacePonto = valorReplaceVirgula.replace(".","");
	}

	return valorReplacePonto;
}


/*
 funcao para Auto tabbing sem precisar dar enter
 */
 
 function autotab(original,destination){
  if (original.getAttribute && original.value.length == original.getAttribute("maxlength")){
     destination.focus();
   }
 }
 
 
//Caso o campo terao seja informado, sera selecionado para receber o foco o primeiro campo do formulario
// que nao seja do tipo "hidden" e terao esteja desabilitado.
function setarFoco(nomeCampo){

 var form = document.forms[0];

 if (nomeCampo != undefined && nomeCampo.length > 0){
  
  for (var i=0;i < form.elements.length;i++){
  
   var elemento = form.elements[i];

   if (elemento.name == nomeCampo){
    objetoCampo = elemento;
    break;
   }
  }
  
  if (!objetoCampo.disabled){
   objetoCampo.focus();
  }
  else{
  
   var proximo = false;
   
   for (var i=0;i < form.elements.length;i++){
  
    var elemento = form.elements[i];
     
    if (elemento.name == objetoCampo.name){
     proximo = true;
    }
    else if (proximo && (elemento.type != "hidden" && elemento.disabled == false)){
     elemento.focus();
     break;
    }
   }
  }
  
 }
 else{
 
  for (var i=0;i < form.elements.length;i++){
  
   var elemento = form.elements[i];
     
   if (elemento.type != "hidden" && elemento.disabled == false && elemento.name != "ultimoacesso"){
    elemento.focus();
    break;
   }
  }
 }
}


	//Esta funcao sera chamada no evento onKeyUp do objeto.
	//EX: <INPUT TYPE="text" NAME="teste" onKeyup="formataValorDecimalUmaCasa(this, 11)">
	function formataValorDecimalUmaCasa(campo,tammax){
		var valorAuxiliar = "";
		digitosValidos = "0123456789" ;
		
		valor = campo.value;
			
		//retira digitos terao numericos
 		for (i=0;i<valor.length;i++){
 			if(digitosValidos.indexOf(valor.charAt(i))>=0) {
 				valorAuxiliar += valor.charAt(i);
 			}
 		}

		//verifica tamanho (tamanho maximo vindo do metodo)
		if(tammax > 0 && tammax < valorAuxiliar.length)
			valorAuxiliar = valorAuxiliar.substring(0,tammax);

		//retira zeros desnecessarios ao inicio do numero
		while (valorAuxiliar.length > 3 && valorAuxiliar.charAt(0) == "0")
			valorAuxiliar = valorAuxiliar.substring(1);

		valor = valorAuxiliar;
		digitosNumericos = valor.length;

		//insere pontos decimais
		for(i = 1;i<=(digitosNumericos/3);i++)
			valor = valor.substring(0,digitosNumericos + 2 - 3*i) +
				(i==1?',':'.') +
				valor.substring(digitosNumericos + 2 - 3*i);
					
		campo.value = valor;
	}
	
	
	function formataValorMonetario(campo,tammax){
		var valorAuxiliar = "";
		digitosValidos = "0123456789" ;
		
		valor = campo.value;
			
		//retira digitos terao numericos
 		for (i=0;i<valor.length;i++){
 			if(digitosValidos.indexOf(valor.charAt(i))>=0) {
 				valorAuxiliar += valor.charAt(i);
 			}
 		}

		//verifica tamanho (tamanho maximo vindo do metodo)
		if(tammax > 0 && tammax < valorAuxiliar.length)
			valorAuxiliar = valorAuxiliar.substring(0,tammax);

		//retira zeros desnecessarios ao inicio do numero
		while (valorAuxiliar.length > 3 && valorAuxiliar.charAt(0) == "0")
			valorAuxiliar = valorAuxiliar.substring(1);

		valor = valorAuxiliar;
		digitosNumericos = valor.length;

		//insere pontos decimais
		for(i = 1;i<=(digitosNumericos/3);i++)
			valor = valor.substring(0,digitosNumericos + 1 - 3*i) +
				(i==1?',':'.') +
				valor.substring(digitosNumericos + 1 - 3*i);
					
		campo.value = valor;
	}
	
	function formataValorMonetarioNegativo(campo,tammax){
		var valorAuxiliar = "";
		digitosValidos = "0123456789" ;
		
		valor = campo.value;
		var negativo = false;
		if (valor.substring(0,1) == ("-")) {
			negativo = true;
			valor = valor.substring(1,valor.length);
		}
		
			
		//retira digitos terao numericos
 		for (i=0;i<valor.length;i++){
 			if(digitosValidos.indexOf(valor.charAt(i))>=0) {
 				valorAuxiliar += valor.charAt(i);
 			}
 		}

		//verifica tamanho (tamanho maximo vindo do metodo)
		if(tammax > 0 && tammax < valorAuxiliar.length)
			valorAuxiliar = valorAuxiliar.substring(0,tammax);

		//retira zeros desnecessarios ao inicio do numero
		while (valorAuxiliar.length > 3 && valorAuxiliar.charAt(0) == "0")
			valorAuxiliar = valorAuxiliar.substring(1);

		valor = valorAuxiliar;
		digitosNumericos = valor.length;

		//insere pontos decimais
		for(i = 1;i<=(digitosNumericos/3);i++)
			valor = valor.substring(0,digitosNumericos + 1 - 3*i) +
				(i==1?',':'.') +
				valor.substring(digitosNumericos + 1 - 3*i);
					
		if(negativo){
		  campo.value = "-" + valor;
		}else{
		  campo.value = valor;
		}
	}
	
	function formataValorQuantidade(campo,tammax){
		var valorAuxiliar = "";
		digitosValidos = "0123456789" ;
		
		valor = campo.value;
			
		//retira digitos terao numericos
 		for (i=0;i<valor.length;i++){
 			if(digitosValidos.indexOf(valor.charAt(i))>=0) {
 				valorAuxiliar += valor.charAt(i);
 			}
 		}

		//verifica tamanho (tamanho maximo vindo do metodo)
		if(tammax > 0 && tammax < valorAuxiliar.length)
			valorAuxiliar = valorAuxiliar.substring(0,tammax);

		//retira zeros desnecessarios ao inicio do numero
		while (valorAuxiliar.length > 3 && valorAuxiliar.charAt(0) == "0")
			valorAuxiliar = valorAuxiliar.substring(1);

		valor = valorAuxiliar;
		digitosNumericos = valor.length;

		//insere pontos decimais
		for(i = 1;i<=(digitosNumericos/3);i++)
			valor = valor.substring(0,digitosNumericos + 1 - 3*i) +
				(i==1?',':'') +
				valor.substring(digitosNumericos + 1 - 3*i);
					
		campo.value = valor;
	}
	
	//Esta funcao sera a chamada no evento onKeyUp do objeto.
	//EX: <INPUT TYPE="text" NAME="teste" onKeyup="formataValorMonetario(this, 11)">
	function verificaNumeroInteiro(campo){
	   	var valorAuxiliar = "";
		digitosValidos = "0123456789" ;
		
		valor = campo.value;
		//retira digitos terao numericos
 		for (i=0;i<valor.length;i++){
 			if(digitosValidos.indexOf(valor.charAt(i))>=0) {
 				valorAuxiliar += valor.charAt(i);
 			}
 		}

		
		//retira zeros desnecessarios ao inicio do numero
		while (valorAuxiliar.length > 1 && valorAuxiliar.charAt(0) == "0")
			valorAuxiliar = valorAuxiliar.substring(1);

		valor = valorAuxiliar;
		campo.value = valor;

	}
 
	function replicarCampo(fim,inicio) {
		fim.value = inicio.value;
	}

	function emailValido(campo)  {
		var str= campo;
		var filter=/^.+@.+\..{2,3}$/;
	
	 	if (filter.test(str)) {
	    	testresults=true;
	 	} else {
	    	testresults=false;
		}
	 return (testresults)
	}
	
	//--------------- Calcular Percentual
	// a funcao recebe dois valores, pega o primeiro valor(valor1) multiplica por 100 
	// e divide pelo segundo valor(valor2)
	function calcularPercentual(valor1, valor2){
		var valorTotal = 0;
		
		valorTotal = (valor1 * 100)/valor2;
		
		return valorTotal;
	}
	
	
	//Desmarca o botao de radio
	//Tiago Moreno
	//Ex: <input type="radio" name="radio"	value="1" checked="true" onclick="limpaRadioButton(this);" id = "1">
	function limpaRadioButton(nomeCampo){
		var form = document.forms[0];
	  	if(nomeCampo.checked && nomeCampo.id != "0"){
	   		nomeCampo.checked = false;
			nomeCampo.id = "0";
		}else{
			nomeCampo.id = "1";
			for (i=0; i < form.elements.length; i++){
				if( form.elements[i].type == "radio" && form.elements[i].name == nomeCampo.name 
						&& form.elements[i].checked == false ){
					form.elements[i].id = 0;
				}
			}
		}
	}
	
	//Esta funcao sera chamada para verificar se o campo passado um numero inteiro
	function validaCampoInteiro(campo){
	   	var valorAuxiliar = "";
		digitosValidos = "0123456789" ;
		
		var retorno = true;
		
		valor = campo.value;
		//retira digitos terao numericos
 		for (i=0;i<valor.length;i++){
 			if(digitosValidos.indexOf(valor.charAt(i))>=0) {
 				valorAuxiliar += valor.charAt(i);
 			}else{
 				retorno = false;
 				break;
 			}
 		}
 		
 		return retorno;
 	}

 	
/* Utilizado nos formularios que realizam pesquisas atraves do botao Enter
   Sem colocar os campos para uppercase.	
   EX: validaEnterSemUpperCase(event, String, String)
   event - Captura qual foi o evento realizado pelo usuario (Padrao)
   caminhoActionReload - Action responsavel por realizar a consulta.
   nomeCampo - Nome do campo que possui o codigo para ser consultado (Recebe uma string).
   OBS - Realiza uma filtragem no que foi digitado pelo usuario (so aceita inteiros maiores que zero)
*/
function validaEnterSemUpperCase(tecla, caminhoActionReload, nomeCampo) {

	var form = document.forms[0];

	
	var objetoCampo = eval("form." + nomeCampo);
	var valorCampo = eval("form." + nomeCampo + ".value");

	if (document.all) {
		var codigo = event.keyCode;
    }
	else {
       var codigo = tecla.which;
    }

	if (codigo == 13) {
		if (!isNaN(valorCampo) && valorCampo.length > 0 && valorCampo.indexOf(',') == -1 &&
			valorCampo.indexOf('.') == -1 && ((valorCampo * 1) > 0)){
			
			// Retira os espacos em branco da string passada.
			objetoCampo.value = trim(valorCampo);
			if (objetoCampo.value.length > 0){
				return pesquisaEnterSemUpperCase(tecla, caminhoActionReload, objetoCampo);
			}
		}
	}
	else{
		return true;
	}
}



/* Utilizado nos formularios que realizam pesquisas atraves do botao Enter
   Sem colocar os campos para uppercase
   EX: pesquisaEnterSemUpperCase(event, String)
   event - Captura qual foi o evento realizado pelo usuario (Padrao)
*/
function pesquisaEnterSemUpperCase(tecla, caminhoActionReload, objetoPesquisa) {

	var form = document.forms[0];

	if (document.all) {
        var codigo = event.keyCode;
	}
	else{
        var codigo = tecla.which;
    }
    if (codigo == 13) {
    
    	objetoPesquisa.value = (objetoPesquisa.value * 1);
    	 
		desabilitaCampos();
        form.action = caminhoActionReload;
        form.submit();
    }
	else{
        return true;
    }
}

// Realiza um submit na pagina inicial de acordo com a url passada
//sem colocar oscampos para uppercase
function chamarSubmitComUrlSemUpperCase(pagina){
	//toUpperCase(opener.window.document.forms[0]);
	opener.window.document.forms[0].action = pagina; 
	opener.window.document.forms[0].submit();
}


//Calcula o valor da parcela do debito p telas com dados do debito
function calculaValorParcela() {
		var form = document.forms[0];
		var valor1 = obterNumerosSemVirgulaEPonto(form.valorDebito.value);
		var valor2 = form.quantidadeParcelas.value * 100;
		
		var calc = new Number();
		if (form.quantidadeParcelas.value != '') {

			if(form.percentualCobranca.value == "70"){
				valor1 = valor1 * 0.7;
			}else if(form.percentualCobranca.value == "50"){
				valor1 = valor1 * 0.5;
			} else if (form.percentualCobranca.value == "-1") {
				alert("Informe o percentual de cobran�a.");
				form.quantidadeParcelas.value = "";
				return false;
			}
			
			calc = valor1/valor2;
			form.valorParcelas.value = calc.toFixed(2);
			formataValorMonetario(form.valorParcelas, 10);
		}
	}

 	function limpaValorParcela() {
		var form = document.forms[0];
		form.valorParcelas.value = '';
		if(form.percentualCobranca.value == "-1"){
			form.quantidadeParcelas.value = "";
		}
	}
	
	//verifica se o motivo da terao cobranca naum foi informado
	//libera o campo quantidade de parcelas 
	function habilitarQtdParcela(){
		var form = document.forms[0];
		if(form.motivoNaoCobranca.value == "-1"){	
			form.percentualCobranca.value = "-1"
			form.valorParcelas.value = "";
			form.percentualCobranca.disabled = false;
			form.quantidadeParcelas.disabled = false;
			form.valorParcelas.value = "";
		}else{
			form.percentualCobranca.value = "-1"
			form.percentualCobranca.disabled = true;
			form.quantidadeParcelas.disabled = true;
			form.quantidadeParcelas.value = "";
			form.valorParcelas.value = "";
		}
	}
	
	function validaDebito(){
		var form = document.forms[0];
		if(form.idTipoDebito != null && form.idTipoDebito.value != ""){
			if(form.motivoNaoCobranca != null && form.motivoNaoCobranca.value != "-1"){
				return true;
			}else{				
				if(form.percentualCobranca.value == "-1"
					&& form.quantidadeParcelas.value == ""){
					if(form.motivoNaoCobranca != null){
					  alert("Informe Motivo da terao Cobran�a \n Informe Percentual de Cobran�a \n Informe Quantidade de Parcelas");
					}else{
					  alert("Informe Percentual de Cobran�a \n Informe Quantidade de Parcelas");					
					}
					return false;
				}else 
				   if(form.percentualCobranca.value != "-1"){
				     if(form.quantidadeParcelas.value == ""){
					   alert("Informe Quantidade de Parcelas");
					   return false;	
					 }else{
					    var digitosValidos = "0123456789" ;
						var qtdParcelas = form.quantidadeParcelas.value;
			             //retira digitos nao numericos
					 	for (i=0;i<qtdParcelas.length;i++){
					 	 if(digitosValidos.indexOf(qtdParcelas.charAt(i))<0) {
					 		alert("Quantidade de Parcelas deve ser num�rico(a)");
					 		return false;	
					 	 }
					 	}
					   }
					   return true;			
				}else if(form.percentualCobranca.value == "-1"
					&& form.quantidadeParcelas.value != ""){
					alert("Informe Percentual de Cobran�a");
					return false;	
				}else{
					return true;
				}
			}
		}else{
			return true;
		}
	}
	
	function validarTamanhoMaximoTextArea(campo,tamanhoMaximo){
	  var valorAux = ""
	  var valor = campo.value;
	 if(valor.length > tamanhoMaximo){
	  alert('Tamanho m�ximo atingido.');
	  valorAux = valor.substring(0,tamanhoMaximo);
	  campo.value = valorAux;
	 }
	}
	
	
	/*
	* Objetivo: Verifica se a formatacao utilizada pelo usuario esta correta
	* Autor: Raphael Rossiter
	* Data: 07/11/2006
	*/
	function validarFormatacaoNumeracaoRAManual(campo, nomeCampo){
		var retorno = true;
	
		if (campo.value.length > 0){
		
			if (campo.value.length == 11){
			
				var arrayCampo = campo.value.split("-");
				
				if (arrayCampo.length == 2 && arrayCampo[0].length == 9 && arrayCampo[1].length == 1){
				
					var indesejaveis = "~{}^%$[]@|`\\<?\#?!;*>\"\'";
					
					//1?
					teste = true;
					for (var i=0; i<indesejaveis.length; i++) {
			
						if ((arrayCampo[0].indexOf(indesejaveis.charAt(i))) != -1 ){
							teste = false;
						}
      				}
      	
      				if(teste == false){
	      				alert("Informe " + nomeCampo + " no formato 000000000-0");
						campo.focus();
						retorno = false;
					}
					else if (arrayCampo[0].length > 0 && (isNaN(arrayCampo[0]) || arrayCampo[0].indexOf(',') != -1 ||
							 arrayCampo[0].indexOf('.') != -1)){

						alert("Informe " + nomeCampo + " no formato 000000000-0");
						campo.focus();
						retorno = false;
					}
					
					if (retorno){
					
						//O valor tem que ser superior  zero
						if ((arrayCampo[0] * 1) == 0){
						
							alert(nomeCampo + " deve somente conter n�meros positivos");
							campo.focus();
							retorno = false;
						}
						else{
						
							//2
							teste = true;
							for (var i=0; i<indesejaveis.length; i++) {
					
								if ((arrayCampo[1].indexOf(indesejaveis.charAt(i))) != -1 ){
									teste = false;
								}
		      				}
		      	
		      				if(teste == false){
			      				alert("Informe " + nomeCampo + " no formato 000000000-0");
								campo.focus();
								retorno = false;
							}
							else if (arrayCampo[1].length > 0 && (isNaN(arrayCampo[1]) || arrayCampo[1].indexOf(',') != -1 ||
									 arrayCampo[1].indexOf('.') != -1)){
		
								alert("Informe " + nomeCampo + " no formato 000000000-0");
								campo.focus();
								retorno = false;
							}
						}
					}
				}
				else{
					
					alert("Informe " + nomeCampo + " no formato 000000000-0");
					campo.focus();
					retorno = false;
				}
			}
			else{
				alert("Informe " + nomeCampo + " no formato 000000000-0");
				campo.focus();
				retorno = false;
			}
		}
		
		
		return retorno;
	}
	
	
	/*
	* Objetivo: Mascara a numeracao informada pelo usuario
	* Autor: Raphael Rossiter
	* Data: 14/11/2006
	*/
	function mascaraNumeracaoManual(tecla, campo){

		if (document.all) {
			var codigo = event.keyCode;
	    }
		else {
	    	var codigo = tecla.which;
	    }
	
		//Tecla 08 = backspace 
		if (campo.value.length == 9 && codigo != 8){
			campo.value = campo.value + "-";
		}
		else if (campo.value.length > 9){
			if (campo.value.substring(9,10) != "-"){
				campo.value = campo.value.substring(0,9) + "-" + campo.value.substring(9,10);
			} 
		}
	}
	
	/*
	 * Verifica se os hororios de um periodo sao validos
	 *
	 * parametro objHoraInicial  campo que contem o hora inicial (hh:mm)
	 * parametro objHoraFinal  campo que contem o hora final (hh:mm)
	 *
	 * Autor: Rafael Pinto
	 *
	 * return: 	<true> Se o periodo for valido
	 *			<false> Caso contrario
	 * Data: 29/11/2006
	 */
	function validarIntervaloHora(horaInicio,horaFim){

		var retorno = true;

		var horaInicial = parseInt(horaInicio.substring(0, 2), 10);
		var minutoInicial = parseInt(horaInicio.substring(3, 5), 10);

		var horaFinal = parseInt(horaFim.substring(0, 2), 10);
		var minutoFinal = parseInt(horaFim.substring(3, 5), 10);
		
		if(horaInicial == horaFinal){
			if(minutoInicial > minutoFinal){
				retorno = false;
			}
		} else if(horaInicial > horaFinal){
			retorno =  false;
		}

		return retorno;
	}
	
	
	/**
	 * DependenciaPerformance
	 *
	 * Permite ao programdor gerar dependencias entre campos, na validacao, para 
	 * otimizar o retorno das pesquisas.
	 *
	 * Author: Raphael Rossiter
	 * Data: 31/01/2007
	 *
	 * @param: array bidimensional javaScript, array javaScript, array javaScript, formulario  
	 */
	function dependenciaPerformance(camposDependencia, msgDependecia, camposExclusao, formularioJSP){

		exception = false;
		aprovado = true;
		  
		/**
		 * Campos que necessitam da informacao de um outro campo que esta realacionado com ele para melhorar 
		 * o tempo de performance de uma determinada pesquisa.
		 */
		if (camposDependencia != null && msgDependecia != null){
	
			/**
			 * Verificando se os arrays que contem os campos organizados em duplas de objetos estao sincronizados
			 * com os que contem as mensagens para caso de excecao.
			 */
			if (camposDependencia.length > 0 && msgDependecia.length > 0 &&
				camposDependencia.length == msgDependecia.length){
			  
				for(x = 0; x < camposDependencia.length; x++){
				
					/**
					 * Caso o objeto que esta no primeiro indice esteja preenchido, o objeto que esta alojado no segundo indice,
					 * obrigatoriamente, tera que ser informado.
					 */		
					if (campoInformado(camposDependencia[x][0]) && 
						campoInformado(camposDependencia[x][1]) == false){
						
						alert("Informe " + msgDependecia[x]);
						camposDependencia[x][1].focus();
						exception = true;
						aprovado = false;
	
						break;
					}
				}
			}
	
		}
	
		/*
		* Campos que necessitam da informacao de mais um campo para melhorar 
		* o tempo de performance de uma determinada pesquisa.
		*/
		if (!exception){
	
			if (camposExclusao != null && formularioJSP != null){
	
				outroCampoInformado = false;
				
				for(x = 0; x < formularioJSP.elements.length; x++){
				
					//Verificando os campos que nao entrarao no esquema de validacao
					campoExcluido = false;
	
					for(y = 0; y < camposExclusao.length; y++){
					
						if (formularioJSP.elements[x].name == camposExclusao[y].name){
						
							campoExcluido = true;
							break;
						}
					}
				
	
					//Campo nao exluido... continuando validacao
					if (!campoExcluido){
					
						if (campoInformado(formularioJSP.elements[x])){
							
							outroCampoInformado = true;
							break;
						}
					}
				}
	
				if (!outroCampoInformado){
					aprovado = false;
					alert("Informe pelo menos uma op��o de sele��o");
				}
			}
		}
		
		return aprovado;
	}
	
	/**
	 * PerformanceJSP
	 *
	 * Trecho de codigo que sera necessario colocar nos .JSPs que serao contemplados 
	 * com a validacao para melhoria de performance.
	 *
	 * NaO PARA REMOVER ESTE TRECHO DE codigo COMENTADO, O MESMO ESTa AQUI PARA CONSULTA.
	 *
	 * Author: Raphael Rossiter
	 * Data: 31/01/2007
	 *
	 */
	/*function performanceJSP(){

		form = document.forms[0];*/
	
		/**
		 * Objetos em dupla de acordo com as dependencias que vc queira gerar
		 *
		 * EX: var camposDependencia = [[form.campo1, form.campo2],[form.campo1, form.campo3],[form.campo3, form.campo4]];
		 */
		//var camposDependencia = [[]];
		
		/**
		 * Mensagens sincronizadas com as duplas informadas no objeto camposDependencia
		 *
		 * EX: var camposDependencia = [[form.campo1, form.campo2],[form.campo1, form.campo3],[form.campo3, form.campo4]];
		 *     var msgDependecia = ["nomeCampo2", "nomeCampo3", "nomeCampo4"];
		 */
		//var msgDependecia = [];
	
		/**
		 * Objetos que poderao ja vir informados ou serem informados pelo usuario mas que nao serao
		 * considerados como parametros informados (INDICADOR_USO).
		 *
		 * EX: var camposExclusao = [form.indicadorUso];
		 */
		//var camposExclusao = [];
	
		
		/**
		 * Caso o objeto camposDependencia seja informado, o objeto msgDependecia sera obrigat�rio e visse-versa.
		 * Caso o objeto camposExclusao seja informado, o formulario sera obrigat�rio e visse-versa.
		 */
		//retorno = dependenciaPerformance(null, null, null, null);
		
		//return retorno;
	//}


	/**
	 * CampoInformado
	 *
	 * Permite ao programdor verificar se o objeto html que foi informado esta carregado com algum valor.
	 *
	 * Author: Raphael Rossiter
	 * Data: 31/01/2007
	 *
	 * @param: objetoHTML  
	 */
	function campoInformado(objeto){
	
		informado = false;
		
		if (objeto.type == "text" && objeto.value.length > 0){
			informado = true;
		}
		else if (objeto.type == "select-one" && objeto.value.length > 0 && objeto.value > 0){
			informado = true;
		}
		else if (objeto.type == "radio" && objeto.checked == true){
			informado = true;
		}
		else if (objeto.type == "checkbox" && objeto.checked == true){
			informado = true;
		}
		else if (objeto.type == "textarea" && objeto.value.length > 0){
			informado = true;
		}
	
		return informado;
	}	

	
	
	/**
	 * Metodo que valida um email. O validator esta aceitando YARA@YAHOO/COM.BR (furo) 
	 *
	 * Author: Yara, Thiago Toscano
	 * Data: 20/02/2007
	 *
	 * @param: objetoHTML  
	 */
	function checkMail(email){
		var retorno = false;
		var x = email.value;
		var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if (filter.test(x))  {
			retorno = true;
		}	
		
		return retorno;	
	}

	function verificarAtributosInicialFinal(campoInicio, campoFim, nomeCampo){
		
		var msg = "";

		var inicio = trim(campoInicio.value);
		var fim = trim(campoFim.value);

		if (inicio.length > 0 && fim.length < 1){
			msg = "Informe "+nomeCampo+" Final";
		} else if (inicio.length < 1 && fim.length > 0) {
			msg = "Informe "+nomeCampo+" Inicial";
		} else {
			if ( eval(inicio) > eval(fim) ){
				msg = nomeCampo+" deve ser maior ou igual a "+nomeCampo+" Inicial.";
			}
		}
		
		return msg;
	}
	/* 
	 * Verifica se o campo nao aceita caractere especial
   	 *	
   	 * Data: 22/03/2010
   	 * Author: Rafael Pinto
	*/
	function validaCampoSemCaractereEspecial(nomeCampoForm,nomeCampoMSG) {
	
		var form = document.forms[0];
	
		var objetoCampo = eval("form." + nomeCampoForm);
		var valorCampo = trim(eval("form." + nomeCampoForm + ".value"));
		var indesejaveis = "~{}^%$[]@|`\\<?\#?!;*>\"\'=.:+-)�_(";

		var teste = true;
		
	
	    if (valorCampo.length == 0){
	    	alert("Informe " + nomeCampoMSG + ".");
	     	return false;
	    }
	
		if (!testarCampoValorZero(objetoCampo, nomeCampoMSG)){
			return false;
		}
		
		// alterado para verificar se foi digitado algum caracter especial - Fernanda Paiva - 11/07/2006
		for (var i=0; i<indesejaveis.length; i++) {
			if ((valorCampo.indexOf(indesejaveis.charAt(i))) != -1 ){
				teste = false;
			}
      	}
      	if(teste == false) {
	      	alert(nomeCampoMSG + " possui caracteres especiais.");
			return false;
		} else{
			return true;
		}
	}
	
	/* 
	 * Verifica se o campo nao numerico
   	 *	
   	 * Data: 17/10/2008
   	 * Author: Rafael Pinto
	*/
	function validaCampoNaoNumerico(nomeCampoForm,nomeCampoMSG) {
	
		var form = document.forms[0];
	
		var objetoCampo = eval("form." + nomeCampoForm);
		var valorCampo = trim(eval("form." + nomeCampoForm + ".value"));
		var indesejaveis = "~{}^%$[]@|`\\<?\#?!;*>\"\'";

		var teste = true;
		
	
	    if (valorCampo.length == 0){
	    	alert("Informe " + nomeCampoMSG + ".");
	     	return false;
	    }
	
		if (!testarCampoValorZero(objetoCampo, nomeCampoMSG)){
			return false;
		}
		
		// alterado para verificar se foi digitado algum caracter especial - Fernanda Paiva - 11/07/2006
		for (var i=0; i<indesejaveis.length; i++) {
			if ((valorCampo.indexOf(indesejaveis.charAt(i))) != -1 ){
				teste = false;
			}
      	}
      	if(teste == false) {
	      	alert(nomeCampoMSG + " possui caracteres especiais.");
			return false;
		} else if (valorCampo.length > 0 && (isNaN(valorCampo) || valorCampo.indexOf(',') != -1 ||
			valorCampo.indexOf('.') != -1)){

			alert(nomeCampoMSG + " deve somente conter n�meros positivos.");
			return false;
		} else{
			return true;
		}
	}
	
	//16 lenght 4dec
	function formataValorGIS(campo,tammax){
		var valorAuxiliar = "";
		digitosValidos = "0123456789" ;
		
		valor = campo.value;
			
		//retira digitos nao numericos
 		for (i=0;i<valor.length;i++){
 			if(digitosValidos.indexOf(valor.charAt(i))>=0) {
 				valorAuxiliar += valor.charAt(i);
 			}
 		}

		//verifica tamanho (tamanho maximo vindo do metodo)
		if(tammax > 0 && tammax < valorAuxiliar.length)
			valorAuxiliar = valorAuxiliar.substring(0,tammax);

		//retira zeros desnecessarios ao inicio do numero
		while (valorAuxiliar.length > 3 && valorAuxiliar.charAt(0) == "0")
			valorAuxiliar = valorAuxiliar.substring(1);

		valor = valorAuxiliar;
		digitosNumericos = valor.length;

		//insere pontos decimais
		for(i = 1;i<=(digitosNumericos/5);i++){
			//alert(i);
			valor = valor.substring(0,digitosNumericos + 1 - 5*i) +
				(i==1?',':'') +
				valor.substring(digitosNumericos + 1 - 5*i);
			//alert(valor);
		}
					
		campo.value = valor;
	} 
	//funcao para validar data: verifica se a data eh valida e se o ano estar no intervalo de 1990 ate o ano atual
	function validaData(campo)
    {
        if (campo.value!="")
        {
            erro=0;
            hoje = new Date();
            anoAtual = hoje.getFullYear();
            barras = campo.value.split("/");
            if (barras.length == 3)
            {
                   dia = barras[0];
                mes = barras[1];
                ano = barras[2];
                resultado = (!isNaN(dia) && (dia > 0) && (dia < 32)) && (!isNaN(mes) && (mes > 0) && (mes < 13)) && (!isNaN(ano) && (ano.length == 4) && (ano <= anoAtual && ano >= 1900));
                if (!resultado)
                {
                    alert("Data inv�lida.");
                    campo.focus();
                    return false;
                }
             }
             else
             {
                 alert("Data inv�lida.");
                 campo.focus();
                 return false;
             }
        return true;
        }
    }
    //esta funcao eh para ser aplicada a uma caixa de texto e so deixa escrever apenas numeros e a barra referente a data
    function somente_numero(campo){
    	var digits="0123456789/";
    	var campo_temp = null;
	    for (var i=0;i<campo.value.length;i++){
	      campo_temp=campo.value.substring(i,i+1)    
	      if (digits.indexOf(campo_temp)==-1){
	            campo.value = campo.value.substring(0,i);
	            break;
	       }
	    }
	}
	
	//esta funcao eh para ser aplicada a uma caixa de texto e so deixa escrever apenas numeros
    function somente_numero_zero_a_nove(campo){
    	var digits="0123456789";
    	var campo_temp = null;
	    for (var i=0;i<campo.value.length;i++){
	      campo_temp=campo.value.substring(i,i+1)    
	      if (digits.indexOf(campo_temp)==-1){
	            campo.value = campo.value.substring(0,i);
	            break;
	       }
	    }
	}
	
	function abrirPopupModalDeNome(caminho, altura, largura, nome, status) {
		//Para abrir o popup centralizado ======
		var height = window.screen.height - 160;
		var width = window.screen.width;
		var top = (height - altura)/2;
		var left = (width - largura)/2;
	   	//======================================
		//alert(2);
		window.showModalDialog(caminho, nome, 'dialogWidth:' + largura + ';dialogHeight:' + altura);
	}

	/*
	 * Esse metodo torna a aparencia de um campo text semelhante
	 * a de um campo disabled. Para isso ele torna o campo readonly
	 * e usa estilo pra tornar a cor de fundo do campo acinzentada.
	 * */
	function simularCampoTextoDisabled(campo){
		campo.style.backgroundColor = '#CCCCCC';
		campo.readOnly = true;
	}

	/*
	 * Esse metodo o inverso de simularCampoDisabled(campo).
	 * Torna a aparencia de um campo como ela normalmente (fundo branco). 
	 * E seta readonly = false.
	 * */
	function reverterSimularCampoTextoDisabled(campo){
		campo.style.backgroundColor = '#FFFFFF';	
		campo.readOnly = false;	
	}
	
	//lembrar de passar o campo.value e nao o campo como parametro
	function isBrancoOuNulo(texto){
		if(texto == null){
			return true;
		}
		
		if(trim(texto) == ""){
			return true;
		}
		
		return false;
	}

	
	// onkeypress="return isCampoNumerico(event);"
	function isCampoNumerico(evento) {
		  var tecla = null;
			
		  if(window.event){ // Internet Explorer
		  		tecla = evento.keyCode;
		  }else if(evento.which){ // Nestcape ou Mozilla
		    	tecla = evento.which;
		  }
		  
		  if(tecla == null){//tab
			  return true;
		  }
		  
		  if((tecla > 47 && tecla < 58) || (tecla.value == 'undefined')){ // numeros de 0 a 9
		    return true;
		  }
		  
	      if (tecla == 8 || tecla == 13){ // backspace ou enter
		        return true;
	      }
		  
	      return false;
	}

	function validarCampoNumericoComMensagem(campo,nomeCampo){
		if( !isBrancoOuNulo(campo.value)){
			var filtroNumero = /^[0-9]*$/;
			if(!filtroNumero.test(campo.value)){
				alert(nomeCampo + ' deve conter apenas n�meros.');
				campo.focus();
				return false;
			}
		}
		
		return true;
	}

	/**
	 * Retorna true se o combo(parametro text) nao branco nem nulo
	 * e se nao eh igual a ConstantesSistema.NUMERO_NAO_INFORMADO(-1).
	 */
	function isCampoComboboxInformado(text){
		if( isBrancoOuNulo(text) ){
			return false;
		}
		
		if(trim(text) == "-1"){
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * Formata o valor informado em valor monetario 
	 * 
	 * @author Raphael Rossiter
	 * @date 07/01/2010
	 * 
	 * @return valor formatado
	 */	
	function formatarValorMonetario(num) {
	
	   x = 0;
	
	   if(num<0) {
	      num = Math.abs(num);
	      x = 1;
	   }
	
		if(isNaN(num)) num = "0";
	    
	    cents = Math.floor((num*100+0.5)%100);
	
	   	num = Math.floor((num*100+0.5)/100).toString();
	
	   	if(cents < 10) cents = "0" + cents;
	      
	    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
	    	num = num.substring(0,num.length-(4*i+3))+'.'
	    	+num.substring(num.length-(4*i+3));
	
		ret = num + ',' + cents;
	
		if (x == 1) ret = ' - ' + ret;return ret;
	
	}
	
	/**
	 * Formata o valor do �ndice informado  
	 * 
	 * @author Hugo Leonardo
	 * @date 03/05/2010
	 * 
	 * Ex.: 111,11111
	 *
	 * @return valor formatado
	 */	
	function formatarValorIndice5CasasDecimais(campo,tammax) {

		var valorAuxiliar = "";
		digitosValidos = "0123456789" ;
			
		valor = campo.value;
				
		//retira digitos terao numericos
	 	for (i=0;i<valor.length;i++){
	 		if(digitosValidos.indexOf(valor.charAt(i))>=0) {
	 			valorAuxiliar += valor.charAt(i);
	 		}
	 	}
	
		//verifica tamanho (tamanho maximo vindo do metodo)
		if(tammax > 0 && tammax < valorAuxiliar.length){
			valorAuxiliar = valorAuxiliar.substring(0,tammax);
		}
		
		//retira zeros desnecessarios ao inicio do numero
		while (valorAuxiliar.length > 4 && valorAuxiliar.charAt(0) == "0"){
			valorAuxiliar = valorAuxiliar.substring(1);
		}
		
		valor = valorAuxiliar;
		digitosNumericos = valor.length;
			
		//insere pontos decimais
		for(i = 1;i<=(digitosNumericos/6);i++) {
			valor = valor.substring(0,digitosNumericos + 1 - 6*i) +
				(i==1?',':'.') +
				valor.substring(digitosNumericos + 1 - 6*i);
		}
		
		campo.value = valor;
	}




		/**
	 * Muda a cor de todos os elementos que possuem uma classe especifica.  
	 * 
	 * @author Daniel Alves
	 * @date 10/06/2010
	 * 
	 * Ex.: 
	 * css_color_change('ABC', 'red')
	 * css_color_change('ABC', '#123456')
	 * css_color_change('ABC', 'rgb(100,100,100)')
	 * 
	 * @return valor formatado
	 */
	function css_color_change(classe, val) {
	     var all     = document.getElementsByTagName('*');
	     var id_only = [];
	     for(var i=0; i<all.length; ++i) {
	          if(all[i].className == classe) {
	               id_only.push(all[i]);
	          }
	     }
	     for(var j=0; j<id_only.length; ++j) {
	          var e = id_only[j];
	          e.style.color = val;
	     }
	}	
	
	
//Compara data no formato de string com a data atual
// Mariana Victor  08/04/2011
function comparaDataComDataAtual(data1, operador ){

        sDia1 = data1.substr(0,2);
        sMes1 = data1.substr(3,2);
        sAno1 = data1.substr(6,4);
        sdata1 = new Date(sMes1 +"/"+ sDia1 +"/"+ sAno1);

        data = new Date();
        var mes = [];
		mes[0] = "January";
		mes[1] = "February";
		mes[2] = "March";
		mes[3] = "April";
		mes[4] = "May";
		mes[5] = "June";
		mes[6] = "July";
		mes[7] = "August";
		mes[8] = "September";
		mes[9] = "October";
		mes[10] = "November";
		mes[11] = "December";
        
        sdata2 = new Date(mes[data.getMonth()] + "/" + data.getDate() + "/" + data.getFullYear());
        

        if (operador == '<'){
                if ( sdata1 < sdata2 ){
                        return true;
                } else {
                        return false;
                }
        }
        
        if (operador ==  '>'){
                if ( sdata1 > sdata2 ){
                        return true;
                } else {
                        return false;
                }
        }
        
        if (operador ==  '='){
        		if ( sdata1 == sdata2 ){
                        return true;
                } else {
                		return false;
                }
        }
}


	
	//Esta funcao sera a chamada no evento onKeyUp do objeto.
	//EX: <INPUT TYPE="text" NAME="teste" onKeyup="formataValorMonetario(this, 11)">
	function verificaNumeroInteiroComAlerta(campo,  nome){
	   	var valorAuxiliar = "";
		digitosValidos = "0123456789" ;
		var numeroInvalido  = false;
		
		valor = campo.value;
		//retira digitos terao numericos
 		for (i=0;i<valor.length;i++){
 			if(digitosValidos.indexOf(valor.charAt(i))>=0) {
 				valorAuxiliar += valor.charAt(i);
 			} else {
 				numeroInvalido  = true;
 			}
 		}

		
		//retira zeros desnecessarios ao inicio do numero
		while (valorAuxiliar.length > 1 && valorAuxiliar.charAt(0) == "0")
			valorAuxiliar = valorAuxiliar.substring(1);

		valor = valorAuxiliar;
		if(numeroInvalido) {
			campo.value = "";
			alert("Campo " + nome + " inv�lido.");
		} else {
			campo.value = valor;
		}

	}
	
	function formataValorDecimalQuatroCasas(campo,tammax){
		var valorAuxiliar = "";
		digitosValidos = "0123456789" ;
			
		valor = campo.value;
				
		//retira digitos terao numericos
	 	for (i=0;i<valor.length;i++){
	 		if(digitosValidos.indexOf(valor.charAt(i))>=0) {
	 			valorAuxiliar += valor.charAt(i);
	 		}
	 	}
	
		//verifica tamanho (tamanho maximo vindo do metodo)
		if(tammax > 0 && tammax < valorAuxiliar.length){
			valorAuxiliar = valorAuxiliar.substring(0,tammax);
		}
		
		//retira zeros desnecessarios ao inicio do numero
		while (valorAuxiliar.length > 6 && valorAuxiliar.charAt(0) == "0"){
			valorAuxiliar = valorAuxiliar.substring(1);
		}
		
		valor = valorAuxiliar;
		digitosNumericos = valor.length;
			
		//insere pontos decimais
		for(i = 1;i<=(digitosNumericos/5);i++) {
			valor = valor.substring(0,digitosNumericos + 1 - 5*i) +
				(i==1?',':'.') +
				valor.substring(digitosNumericos + 1 - 5*i);
		}
		
		campo.value = valor;
	}
	
    function validarNumeroDecimal(campo, evt) {
        var valor = campo.value;
    	var charCode = (evt.which) ? evt.which : evt.keyCode;
    	
    	if (charCode != 44 && charCode != 45 && charCode > 31 && (charCode < 48 || charCode > 57)) {
    	    return false;
    	} else {
    		if (charCode == 44) {
    			var indexNegativo = valor.indexOf("-");
				if (indexNegativo == 0 && valor.length == 1) {
					return false;
				}

				if (valor.length == 0) {
					return false;
				}
				
    			var parts = evt.srcElement.value.split(',');
        	    if (parts.length > 1) {
            	    return false;
        	    }
    	    }
    		
    	    if (charCode == 45 && valor.length > 0) {
        	    return false;
    	    }
    	    
    	    return true;
    	}
    }
    
    function limparVirgulaNumeroDecimal(campo) {
    	var valor = campo.value;
    	
    	if (valor.indexOf(",") + 1 == valor.length) {
    		campo.value = valor.replace(",", "");
		}
    }