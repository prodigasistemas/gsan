
/*
*JavaScript Utilizado pela aba localiza??o do caso de uso Filtrar Imovel Outros Criterios
*/

/*
recupera os dados vindos pelo metodo enviarDadosQuatroParametros 
*/

function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

	var form = document.ImovelOutrosCriteriosActionForm;

	if (tipoConsulta == 'setorComercialOrigem') {
      form.setorComercialOrigemCD.value = codigoRegistro;
      form.setorComercialOrigemID.value = idRegistro;
	  form.nomeSetorComercialOrigem.value = descricaoRegistro;
	  form.nomeSetorComercialOrigem.style.color = "#000000";
	  
	  form.setorComercialDestinoCD.value = codigoRegistro;
      form.setorComercialDestinoID.value = idRegistro;
	  form.nomeSetorComercialDestino.value = descricaoRegistro;
	  form.nomeSetorComercialDestino.style.color = "#000000";
	  form.quadraOrigemNM.focus();
	}

	if (tipoConsulta == 'setorComercialDestino') {
      form.setorComercialDestinoCD.value = codigoRegistro;
      form.setorComercialDestinoID.value = idRegistro;
	  form.nomeSetorComercialDestino.value = descricaoRegistro;
	  form.nomeSetorComercialDestino.style.color = "#000000"; 
	  form.quadraDestinoNM.focus();
	}

//	if (tipoConsulta == 'quadraOrigem') {
  //    form.quadraOrigemNM.value = codigoRegistro;
	//  form.quadraOrigemID.value = idRegistro;
//	  form.quadraMensagemOrigem.value =  descricaoRegistro;
	//  form.quadraMensagemOrigem.style.color = "#000000";
	  
//	  form.quadraDestinoNM.value = codigoRegistro;
	//  form.quadraDestinoID.value = idRegistro;
	  //form.quadraMensagemDestino.value = descricaoRegistro;
//    }

//	if (tipoConsulta == 'quadraDestino') {
  //    form.quadraDestinoNM.value = codigoRegistro;
	//  form.quadraDestinoID.value = idRegistro;
// 	  form.quadraMensagemDestino.value = descricaoRegistro;
 //	  form.quadraMensagemDestino.style.color = "#000000";
	//}
	form.action = 'exibirFiltrarImovelOutrosCriteriosConsumidoresInscricao.do?menu=sim&gerarRelatorio=RelatorioCadastroConsumidoresInscricao&limpar=S';
	form.submit();

}

/*
recupera os dados vindos pelo metodo enviarDadosQuatroParametros 
*/

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	var form = document.ImovelOutrosCriteriosActionForm;

	if (tipoConsulta == 'localidadeOrigem') {
      form.localidadeOrigemID.value = codigoRegistro;
	  form.nomeLocalidadeOrigem.value = descricaoRegistro;
	  form.nomeLocalidadeOrigem.style.color = "#000000";
	  
	  form.localidadeDestinoID.value = codigoRegistro;
      form.nomeLocalidadeDestino.value = descricaoRegistro;
      form.nomeLocalidadeDestino.style.color = "#000000";
      form.setorComercialOrigemCD.focus();
	}

	if (tipoConsulta == 'localidadeDestino') {
      form.localidadeDestinoID.value = codigoRegistro;
      form.nomeLocalidadeDestino.value = descricaoRegistro;
   	  form.setorComercialDestinoCD.focus();
	 		  
	}

	if(tipoConsulta == 'municipio'){
		form.idMunicipio.value = codigoRegistro;
		form.nomeMunicipio.value = descricaoRegistro;
		form.nomeMunicipio.style.color = "#000000";
		form.idBairro.focus();
	}

	if(tipoConsulta == 'cep'){
		form.CEP.value = codigoRegistro;
		form.descricaoCep.value = descricaoRegistro;
		form.descricaoCep.style.color = "#000000";
 	    form.idLogradouro.focus();
	}

	
	if(tipoConsulta == 'bairro'){
		form.idBairro.value = codigoRegistro;
		form.nomeBairro.value = descricaoRegistro;
		form.nomeBairro.style.color = "#000000";
	    form.CEP.focus();
	}
	
	if(tipoConsulta == 'logradouro'){
		form.idLogradouro.value = codigoRegistro;
		form.nomeLogradouro.value = descricaoRegistro;
		form.nomeLogradouro.style.color = "#000000";
	}
	form.action = 'exibirFiltrarImovelOutrosCriteriosConsumidoresInscricao.do?menu=sim&gerarRelatorio=RelatorioCadastroConsumidoresInscricao&limpar=S';
	form.submit();
}

/*
Fun??o que valida as informa??es preenchidas no form
*/

function validarForm(form){
	// Campos relacionados a inscri??o de origem
	var localidadeOrigem = form.localidadeOrigemID;
	var setorComercialOrigem = form.setorComercialOrigemCD;
	var quadraOrigem = form.quadraOrigemNM;
	var loteOrigem = form.loteOrigem;

	// Campos relacionados a inscri??o de destino
	var localidadeDestino = form.localidadeDestinoID;
	var setorComercialDestino = form.setorComercialDestinoCD;
	var quadraDestino = form.quadraDestinoNM;
	var loteDestino = form.loteDestino;

	var obrigatorio = true;

	//Origem
	//if (localidadeOrigem.value.length < 1){
	//	alert("Informe a Localidade da inscri??o de origem");
	//	localidadeOrigem.focus();
	//}else
	if (!testarCampoValorZero(localidadeOrigem, "Localidade Inicial.")){
		localidadeOrigem.focus();
	}else if (setorComercialOrigem.value.length > 0 && 
			  !testarCampoValorZero(setorComercialOrigem, "Setor Comercial Inicial.")){

		setorComercialOrigem.focus();
	}else if (quadraOrigem.value.length > 0 && !testarCampoValorZero(quadraOrigem, "Quadra Inicial.")){
		quadraOrigem.focus();
	}else if (loteOrigem.value.length > 0){
		loteOrigem.focus();
	}else{
		obrigatorio = campoObrigatorio(setorComercialOrigem, quadraOrigem, "Setor Comercial Inicial.");
		if (!obrigatorio){
			obrigatorio = campoObrigatorio(quadraOrigem, loteOrigem, "Quadra Inicial.");
		}
	}

	//Destino
	if (!obrigatorio){
		if (localidadeDestino.value.length < 1 && localidadeOrigem.value.length > 0){
			alert("Localidade Final.");
			localidadeDestino.focus();
			obrigatorio = true;
		}else if (!testarCampoValorZero(localidadeDestino, "Localidade Final.")){
			localidadeDestino.focus();
			obrigatorio = true;
		}else if (setorComercialDestino.value.length > 0 && 
			  !testarCampoValorZero(setorComercialDestino, "Setor Comercial Final.")){

			setorComercialDestino.focus();
			obrigatorio = true;
		}else if (quadraDestino.value.length > 0 && !testarCampoValorZero(quadraDestino, "Quadra Final.")){
			quadraDestino.focus();
			obrigatorio = true;
		}else if (loteDestino.value.length > 0){
			loteDestino.focus();
			obrigatorio = true;
		}else{
			obrigatorio = campoObrigatorio(setorComercialDestino, quadraDestino, "Informe Setor Comercial Final.");
			if (!obrigatorio){
				obrigatorio = campoObrigatorio(quadraDestino, loteDestino, "Informe Quadra Final.");
			}
		}
	}

	//Origem - Destino
	if (!obrigatorio){
		obrigatorio = campoObrigatorio(setorComercialDestino, setorComercialOrigem, "Informe Setor Comercial Final.");
		if (!obrigatorio){
			obrigatorio = campoObrigatorio(quadraDestino, quadraOrigem, "Informe Quadra Inicial.");
			if (!obrigatorio){
				obrigatorio = campoObrigatorio(loteDestino, loteOrigem, "Informe Lote Inicial");
			}
		}
	}
	
	//
	//valida se os campos preenchido tao com seus correspondentes preenchidos tbm(obrigatorio)
	//
	if(!validaPreenchimentoCamposFaixa()){
		obrigatorio = true;
	}
	
	//valida questoes referente a faixa de campos(campo inicial tem q ser menor ou igual ao final)	
	if(validaPesquisaFaixaSetor()){
		obrigatorio = true;
	}
	//valida questoes referente a faixa de campos(campo inicial tem q ser menor ou igual ao final)	
	if(validaPesquisaFaixaQuadra()){
		obrigatorio = true;
	}
	//valida questoes referente a faixa de campos(campo inicial tem q ser menor ou igual ao final)	
	if(validaPesquisaFaixaLote()){
		obrigatorio = true;
	}
	
	//valida questoes referente a faixa de campos(campo inicial tem q ser menor ou igual ao final)	
	if(validaPesquisaFaixaLocalidade()){
		obrigatorio = true;
	}
	

	// Confirma a valida??o do formul?rio
	if (!obrigatorio && validateImovelOutrosCriteriosActionForm(form)){
		form.target = 'Relatorio';
		form.submit();	
	}

}


/*
Deve ser pra validar as dependencias ------ Em teste
*/
function campoObrigatorio(campoDependencia, dependente, msg){
	if (dependente.value.length < 1){
		return false
	}
	else if (campoDependencia.value.length < 1){
		alert(msg);
		campoDependencia.focus();
		return true
	}
}

/*
Em teste ---------n?o sei pq foi usada ja q existe uma no util.js
verificar se ela tem algo diferente
*/

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg){

	if (objeto == null || codigoObjeto == null){
		abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
	}
	else{
		if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
			alert(msg);
		}
		else{
			abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
		}
	}
}

/*
serveria pra bloquear campos
*/

function validarLocalidade(){
	var form = document.ImovelOutrosCriteriosActionForm;
	if( form.localidadeOrigemID.value == form.localidadeDestinoID.value ){
		form.setorComercialOrigemID.disabled = false;
		form.setorComercialDestinoID.disabled = false;
	}
	else if( form.localidadeOrigemID.value != form.localidadeDestinoID.value ){
		form.setorComercialOrigemID.disabled = true;
		form.setorComercialDestinoID.disabled = true;
		form.setorComercialOrigemID.value = '';
		form.setorComercialDestinoID.value = '';
		form.quadraOrigemID.value = '';
		form.quadraDestinoID.value = '';
	}
	else if( form.setorComercialOrigemID.value != form.setorComercialDestinoID.value ){
			form.quadraOrigemID.disabled = false;
			form.quadraDestinoID.disabled = false;
		}

}
/*
Ainda naum sei pra q serve -----------TESTE
*/
function enviarDadosLocalidade(){
	var form = document.ImovelOutrosCriteriosActionForm;

	if( form.inscricaoTipo.value == 'destino' ){

	}
	else if( (form.localidadeOrigemID.value != '' && form.localidadeDestinoID.value == '') ||
	     (form.localidadeOrigemID.value == form.localidadeDestinoID.value) ||
	     (form.quadraOrigemID.value == form.quadraDestinoID.value)){
	     
		//alert(form.inscricaoTipo.value);
		
		if( !(form.localidadeDestinoID.value != '') ){
		form.nomeLocalidadeDestino.value = form.nomeLocalidadeOrigem.value;
		form.localidadeDestinoID.value = form.localidadeOrigemID.value;
		form.nomeLocalidadeDestino.style.color = "#000000";				
		}
		
		if( (form.setorComercialOrigemCD.value != '') && 
		    (form.localidadeOrigemID.value == form.localidadeDestinoID.value) && 
		    (form.nomeSetorComercialDestino.value == '')){

        	form.setorComercialDestinoCD.value = form.setorComercialOrigemCD.value;
			form.nomeSetorComercialDestino.value = form.nomeSetorComercialOrigem.value;
			form.nomeSetorComercialDestino.style.color = "#000000";


			form.inscricaoTipo.value = 'destino';
		  }
		  else if( (form.inscricaoTipo.value == 'origem') && form.localidadeOrigemID.value == form.localidadeDestinoID.value && (form.setorComercialOrigemCD.value == form.setorComercialDestinoCD.value)){
			 form.quadraDestinoNM.value = form.quadraOrigemNM.value;
//			 form.quadraMensagemDestino.value = form.quadraMensagemOrigem.value;
	//    	 form.quadraMensagemDestino.style.color = "#000000";					 
					  
		  }
		  
		}
		else if( form.nomeLocalidadeDestino.value != form.nomeLocalidadeOrigem.value ){
			validarLocalidade();
		}	  
	}


/*

Limpa campos da faixa final(localidade final, setor comercial final,
quadra final, lote final) conforme o parametro passado
*/
function limparDestino(tipo){
	var form = document.ImovelOutrosCriteriosActionForm;
	
	switch(tipo){
		case 1: //De localidade pra baixo
			if(form.localidadeOrigemID.value != form.localidadeDestinoID.value){
				form.localidadeDestinoID.value = "";
				form.nomeLocalidadeDestino.value = "";					
				form.setorComercialDestinoCD.value = "";
				form.setorComercialDestinoID.value = ""; 
			}
		    
		case 2: //De setor para baixo		 
			if(form.setorComercialOrigemCD.value != form.setorComercialDestinoCD.value){  
			   form.nomeSetorComercialDestino.value = "";
			   form.quadraDestinoNM.value = "";
			   form.quadraDestinoID.value = "";		   
			} 		   		   
		case 3://De quadra pra baixo
		   if(form.quadraOrigemNM.value != form.quadraDestinoNM.value){
	           form.loteDestino.value = "";
	       }
	           
	}

}

/*

Limpa campos da faixa Inicial(localidade inicial, setor comercial inicial,
quadra inicial, lote inicial) conforme o parametro passado
*/

function limparOrigem(tipo){
	var form = document.ImovelOutrosCriteriosActionForm;

		
	switch(tipo){
		case 1: //De localidade pra baixo

			form.nomeLocalidadeOrigem.value = "";
			form.localidadeDestinoID.value = "";
			form.nomeLocalidadeDestino.value = "";
			form.setorComercialOrigemCD.value = "";
		    form.setorComercialOrigemID.value = "";
			habilitaSQlS();
			
		case 2: //De setor para baixo

		   form.nomeSetorComercialOrigem.value = "";
		   form.setorComercialDestinoCD.value = "";
		   form.setorComercialDestinoID.value = "";		   
		   form.nomeSetorComercialDestino.value = "";
		   form.quadraOrigemNM.value = "";
		   form.quadraOrigemID.value = "";
///		   alert("limpar origem 2");
		case 3://De quadra pra baixo

		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";
        
		   form.loteDestino.value = "";
		   form.loteOrigem.value = "";
		   
		   limparMsgQuadraInexistente();		   
	}
}

/*
Limpa os campos da faixa final pelo click na borracha(conforme parametro passado)
*/
function limparBorrachaDestino(tipo){
	var form = document.ImovelOutrosCriteriosActionForm;

	switch(tipo){
		case 1: //De localidade pra baixo
		     form.localidadeDestinoID.value = "";
			 form.nomeLocalidadeDestino.value = "";					
			 form.setorComercialDestinoCD.value = "";
			
		case 2: //De setor para baixo		   
		   form.setorComercialDestinoID.value = ""; 
		   form.nomeSetorComercialDestino.value = "";		   
   		   form.setorComercialDestinoCD.value = "";
		   
		case 3://De quadra pra baixo
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";

           form.loteDestino.value = "";
     }
}

/*
Limpa campos Municipio, bairro, Logradouro, cep
*/

function limparUltimosCampos(tipo){
	var form = document.ImovelOutrosCriteriosActionForm;
	
	//if(form.idMunicipio.value == "")
		//limparUltimosCampos(1);
	
	switch(tipo){
		case 1: //municipio
			form.nomeMunicipio.value = "";
			form.idBairro.value = "";
		case 2: //bairro
			form.nomeBairro.value = "";
			form.idLogradouro.value ="";			
		case 3://logradouro
			form.nomeLogradouro.value = "";
			form.CEP.value = "";
		case 4://cep
			form.descricaoCep.value = "";
	}
}

/*
Limpa campos Municipio, bairro, cep, Logradouro
*/

function limparMBCL(tipo){
	var form = document.ImovelOutrosCriteriosActionForm;
	switch(tipo){
		case 1:
			form.idMunicipio.value = "";
			form.nomeMunicipio.value = "";
		case 2:
			form.idBairro.value = "";
			form.nomeBairro.value = "";
		case 3:
			form.idLogradouro.value = "";
			form.nomeLogradouro.value = "";
		case 4:
			form.CEP.value = "";
			form.descricaoCep.value = "";

	}
}

/*
a fun??o preencherLoteFinal ? chamad pelo onblur do loteInicial
onde seu conteudo e transportado p o lote final
*/

function preencheLoteFinal(loteInicial){
	var form = document.ImovelOutrosCriteriosActionForm;
	
	form.loteDestino.value = loteInicial.value;
}

/*
Fun??o para validar a pesquisa por faixa de Localidade
*/

function validaPesquisaFaixaLocalidade(){
	var form = document.ImovelOutrosCriteriosActionForm;
	
	retorno = true;
	
	if((form.localidadeOrigemID.value != "" )
		&& (form.localidadeOrigemID.value > form.localidadeDestinoID.value)){
		alert("O c?digo da Localidade Final deve ser maior ou igual ao Inicial.");
			form.localidadeDestinoID.focus();
			retorno = false;
	}
	
	return retorno;
}

/*
Fun??o para validar a pesquisa por faixa de setor comercial
- pesquisa v?lida se as localidades forem iguais e o codigo
inicial for menor ou igual ao final -
*/

function validaPesquisaFaixaSetor(){

	var form = document.ImovelOutrosCriteriosActionForm;
	
	retorno = true;
	
	if(form.setorComercialOrigemCD.value != form.setorComercialDestinoCD.value){
		if(form.localidadeOrigemID.value != form.localidadeDestinoID.value){
		
			alert("Para realizar a pesquisa por faixa de Setor Comercial as Localidade inicial e final devem ser iguais.");
			form.setorComercialDestinoID.focus();
			retorno = false;
		}		
	}
	
	if((form.setorComercialOrigemCD.value != "" )
		&& (form.setorComercialOrigemCD.value > form.setorComercialDestinoCD.value)){
		alert("O c?digo do Setor Comercial Final deve ser maior ou igual ao Inicial.");
			form.setorComercialDestinoID.focus();
			retorno = false;
	}
	
	return retorno;

}

/*
Fun??o para validar a pesquisa por faixa de Quadra
- pesquisa v?lida se os Setores Comeciais forem iguais e o n?mero
inicial for menor ou igual ao final -
*/

function validaPesquisaFaixaQuadra(){
	var form = document.ImovelOutrosCriteriosActionForm;
	
	retorno = true;
	
	if(form.quadraOrigemID.value != form.quadraDestinoID.value){
		if(form.setorComercialOrigemCD.value != setorComercialDestinoCD.value){
			retorno = false;
			alert("Para realizar a pesquisa por faixa de Quadras as Localidade e os Setores Comerciais iniciais e finais devem ser iguais.");
			form.quadraDestinoID.focus();
		}
	}
	
	if((form.quadraOrigemID.value != "" )
		&& (form.quadraOrigemID.value > form.quadraDestinoID.value)){
		alert("O n?mero da Quadra Final deve ser maior ou igual ao Inicial.");
			form.quadraDestinoID.focus();
			retorno = false;
	}
	
	return retorno;
}

/*
Fun??o para validar a pesquisa por faixa de Lote
- pesquisa v?lida se as Quadras forem iguais e o n?mero
inicial for menor ou igual ao final -
*/

function validaPesquisaFaixaLote(){
	var form = document.ImovelOutrosCriteriosActionForm;
	
	retorno = true;
	
	if(form.quadraOrigemID.value != form.quadraDestinoID.value){
		if(form.loteOrigem.value != form.loteDestino.value){
			alert("Para realizar a pesquisa por faixa de Lotes as Localidade, os Setores Comerciais e as Quadras iniciais e finais devem ser iguais.");
			form.loteDestino.focus();
			retorno = false;
		}
	}
	
	if((form.loteOrigem.value != "" )
		&& (form.loteOrigem.value > form.loteDestino.value)){
		alert("O n?mero do Lote Final deve ser maior ou igual ao Inicial.");
			form.loteDestino.focus();
			retorno = false;
	}
	
	return retorno;
}


/*
Valida se os campos estao com seus correspondentes preenchidos (obrigatorio)
*/
function validaPreenchimentoCamposFaixa(){
	var form = document.ImovelOutrosCriteriosActionForm;
	
	retorno = true;
	
	if(form.localidadeOrigemID.value != "" && form.localidadeDestinoID.value == ""){
		alert("Informe Localidade Final.");
		form.localidadeDestinoID.focus();
		retorno = false;
	}else if(form.localidadeDestinoID.value != "" && form.localidadeOrigemID.value == ""){
		alert("Informe Localidade Inicial.");
		retorno = false;
	}
	
	if(form.setorComercialOrigemCD.value != "" && form.setorComercialDestinoCD.value == ""){
		alert("Informe Setor Comercial Final.");
		retorno =  false;
	}else if(form.setorComercialDestinoCD.value != "" && form.setorComercialOrigemCD.value == ""){
		alert("Informe Setor Comercial Inicial.");
		retorno = false;
	}
	
	if(form.quadraOrigemID.value != "" && form.quadraDestinoID.value == ""){
		alert("Informe Quadra Final.");
		retorno = false;
	}else if(form.quadraDestinoID.value != "" && form.quadraOrigemID.value == ""){
		alert("Informa Quadra Inicial.");
		retorno = false;
	}
	
	if(form.loteOrigem.value != "" && form.loteDestino.value == ""){
		alert("Informe Lote Final.");
		retorno = false;
	}else if(form.loteDestino.value != "" && form.loteOrigem.value == ""){
		alert("Informe Lote Inicial.");
		retorno = false;
	}
	
	return retorno;
}

function limparPesquisaQuadraInicial() {
    var form = document.forms[0];
    form.quadraOrigemNM.value = "";
    var msgQuadra = document.getElementById("msgQuadraInicial");
	
	if (msgQuadra != null){
	    msgQuadra.innerHTML = "";
	}
}

function limparPesquisaQuadraFinal() {
    var form = document.forms[0];
    form.quadraDestinoNM.value = "";
    var msgQuadra = document.getElementById("msgQuadraFinal");
	
	if (msgQuadra != null){
	    msgQuadra.innerHTML = "";
	}
}

function duplicarLocalidade(){
	var formulario = document.forms[0]; 
	formulario.localidadeDestinoID.value = formulario.localidadeOrigemID.value;
}  


function duplicarSetorComercial(){
	var formulario = document.forms[0]; 
	formulario.setorComercialDestinoCD.value = formulario.setorComercialOrigemCD.value;
}  

function duplicarQuadra(){
	var formulario = document.forms[0]; 
	formulario.quadraDestinoNM.value = formulario.quadraOrigemNM.value;
}  

function duplicarLote(){
	var formulario = document.forms[0]; 
	formulario.loteDestino.value = formulario.loteOrigem.value;
}  

function validarLocalidadeDiferentes(){
	var form = document.forms[0];

	if(form.localidadeOrigemID.value != '' && form.localidadeDestinoID.value == ''){
		alert("Informe Localidade Final.");
		form.localidadeDestinoID.focus();
		return false;
	}
	if(form.localidadeOrigemID.value == '' && form.localidadeDestinoID.value != ''){
		alert("Informe Localidade Inicial.");
		form.localidadeOrigemID.focus();
		return false;
	}
	return true;
	
}

function validarSetoresComerciaisDiferentes(){
	var form = document.forms[0];
	if(form.setorComercialOrigemCD.value!= '' && form.setorComercialDestinoCD.value == ''){
		alert("Informe Setor Comercial Final.");
		form.setorComercialDestinoCD.focus();
		return false;
	}
	if(form.setorComercialOrigemCD.value == '' && form.setorComercialDestinoCD.value != ''){
		alert("Informe Setor Comercial Inicial.");
		form.setorComercialOrigemCD.focus();
		return false;
	}
	return true;
}


function validarQuadrasDiferentes(){
	var form = document.forms[0];
	if(form.quadraOrigemNM.value!= '' && form.quadraDestinoNM.value == ''){
		alert("Informe Quadra Final.");
		form.quadraDestinoNM.focus();
		return false;
	}
	if(form.quadraOrigemNM.value == '' && form.quadraDestinoNM.value != ''){
		alert("Informe Quadra Inicial.");
		form.quadraOrigemNM.focus();
		return false;
	}
	return true;
}

function validarLotesDiferentes(){
	var form = document.forms[0];
	if(form.loteOrigem.value!= '' && form.loteDestino.value == ''){
		alert("Informe Lote Final.");
		form.loteDestino.focus();
		return false;
	}
	if(form.loteOrigem.value == '' && form.loteDestino.value != ''){
		alert("Informe Lote Inicial.");
		form.loteOrigem.focus();
		return false;
	}
	return true;
}


function verificarSetoresComerciaisMenores(){

	var form = document.forms[0];
	if(form.setorComercialOrigemCD.value != '' && form.setorComercialDestinoCD.value != ''){
		if(parseInt(form.setorComercialDestinoCD.value) < parseInt(form.setorComercialOrigemCD.value)){
			alert("Setor Comercial Final deve ser maior ou igual a Setor Comercial Inicial.");	
			form.setorComercialDestinoCD.focus();
			return false;
		}else{
			return true;
		}
	}else{
		return true;
	}
}

function verificarLocalidadeMenores(){
	var form = document.forms[0];
	if(form.localidadeOrigemID.value != '' && form.localidadeDestinoID.value != ''){
		if(parseInt(form.localidadeDestinoID.value) < parseInt(form.localidadeOrigemID.value)){
			alert("Localidade Final deve ser maior ou igual a Localidade Inicial.");	
			form.localidadeDestinoID.focus();
			return false;
		}else{
			return true;
		}
	
	}else{
		return true;
	}
}


function verificarQuadraMenores(){

	var form = document.forms[0];
	if(form.quadraOrigemNM.value != '' && form.quadraDestinoNM.value != ''){
		if(parseInt(form.quadraDestinoNM.value) < parseInt(form.quadraOrigemNM.value)){
			alert("Quadra Final deve ser maior ou igual a Quadra Inicial.");	
			form.quadraDestinoNM.focus();
			return false;
		}else{
			return true;
		}
	}else{
		return true;
	}
}

function verificarLoteMenores(){
	var form = document.forms[0];
	if(form.loteOrigem.value != '' && form.loteDestino.value != ''){
		if((form.loteDestino.value * 1) < (form.loteOrigem.value *1)){
			alert("Lote Final deve ser maior ou igual a Lote Inicial.");	
			form.loteDestino.focus();
			return false;
		}else{
			return true;
		}
	
	}else{
		return true;
	}
}

		   	


function desabilitaIntervaloDiferente(tipo){
	var form = document.forms[0];
	
	switch(tipo){
		case 1: //De localidade 
		    if(form.localidadeOrigemID.value != form.localidadeDestinoID.value || form.localidadeOrigemID.lenght <= 0 ){
		        limparBorrachaOrigem(2);
		        form.setorComercialOrigemCD.disabled = true;
			 	form.quadraOrigemNM.disabled = true;
		     	form.loteOrigem.disabled = true;

			 	form.setorComercialDestinoCD.disabled = true;
			 	form.quadraDestinoNM.disabled = true;
		     	form.loteDestino.disabled = true;
				form.quadraOrigemNM.disabled = true;
		     	form.loteOrigem.disabled = true;
			 	form.quadraDestinoNM.disabled = true;
		     	form.loteDestino.disabled = true;
             
             	form.loteOrigem.value = "";
             	form.quadraOrigemNM.value = "";	
             	form.quadraOrigemID.value = "";
             	limparPesquisaQuadraInicial();
             	form.loteDestino.value = "";
             	form.quadraDestinoNM.value = "";	
             	form.quadraDestinoID.value = "";
             	limparPesquisaQuadraFinal();
             	form.quadraOrigemNM.disabled = true;
		     	form.loteOrigem.disabled = true;
			 	form.quadraDestinoNM.disabled = true;
		     	form.loteDestino.disabled = true;
             
             	form.loteOrigem.value = "";
             	form.quadraOrigemNM.value = "";	
             	form.quadraOrigemID.value = "";
             	limparPesquisaQuadraInicial();
             	form.loteDestino.value = "";
             	form.quadraDestinoNM.value = "";	
             	form.quadraDestinoID.value = "";
             	limparPesquisaQuadraFinal();
             
             	form.loteOrigem.value = "";
		 
			  }else{
			 	form.setorComercialOrigemCD.disabled = false;
			 	form.quadraOrigemNM.disabled = false;
		     	form.loteOrigem.disabled = false;

			 	form.setorComercialDestinoCD.disabled = false;
			 	form.quadraDestinoNM.disabled = false;
		     	form.loteDestino.disabled = false;
		     	
		     	form.quadraOrigemNM.disabled = false;
		     	form.loteOrigem.disabled = false;
			 	form.quadraDestinoNM.disabled = false;
		     	form.loteDestino.disabled = false;
			
			  }
			
			break;					
			   		   
		case 2: //De setor Comercial		   

		    if(form.setorComercialOrigemCD.value != form.setorComercialDestinoCD.value || form.setorComercialOrigemCD.disabled == true ){
		    	
			  	form.quadraOrigemNM.disabled = true;
		     	form.loteOrigem.disabled = true;
			 	form.quadraDestinoNM.disabled = true;
		     	form.loteDestino.disabled = true;
             
             	form.loteOrigem.value = "";
             	form.quadraOrigemNM.value = "";	
             	form.quadraOrigemID.value = "";
             	limparPesquisaQuadraInicial();
             	form.loteDestino.value = "";
             	form.quadraDestinoNM.value = "";	
             	form.quadraDestinoID.value = "";
             	limparPesquisaQuadraFinal();
//             	form.quadraMensagemOrigem.value = "";

  			  }else{
			 	form.quadraOrigemNM.disabled = false;
		     	form.loteOrigem.disabled = false;
			 	form.quadraDestinoNM.disabled = false;
		     	form.loteDestino.disabled = false;
			  }
			
			break;
           
		case 3://De quadra 
		     if(form.quadraOrigemNM.value != form.quadraDestinoNM.value || form.quadraOrigemNM.disabled == true){
			  	form.loteOrigem.disabled = true;
			 	form.loteDestino.disabled = true;
             	
             	form.loteOrigem.value = "";
             	form.loteDestino.value = "";
			 
			  }else{
			 	form.loteOrigem.disabled = false;
			 	form.loteDestino.disabled = false;
			  }
			
			break;
		}
}

function limparBorrachaOrigem(tipo){
	var form = document.forms[0];

		
	switch(tipo){
		case 1: //De localidara pra baixo
		    if(!form.localidadeOrigemID.disabled){
			form.localidadeOrigemID.value = "";
			form.nomeLocalidadeOrigem.value = "";
			form.localidadeDestinoID.value = "";
			form.nomeLocalidadeDestino.value = "";

			desabilitaIntervaloDiferente(1);
			}else{
			break;
			}
			
		case 2: //De setor para baixo
		    if(!form.setorComercialOrigemCD.disabled){
		     form.setorComercialOrigemCD.value = "";
		     form.setorComercialOrigemID.value = "";
		     form.nomeSetorComercialOrigem.value = "";
	   
		     form.setorComercialDestinoCD.value = "";
		     form.setorComercialDestinoID.value = "";		   
		     form.nomeSetorComercialDestino.value = "";
		     desabilitaIntervaloDiferente(2);
		    }else{
		     break;
		    }
		case 3://De quadra pra baixo
		   if(!form.quadraOrigemNM.disabled){
		   form.quadraOrigemNM.value = "";
		   form.quadraOrigemID.value = "";

		   form.loteOrigem.value = "";

		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";
        
		   form.loteDestino.value = "";
		   desabilitaIntervaloDiferente(3);
           limparMsgQuadraInexistente();
		   }
	}

}


function habilitaSQlS(){
	var form = document.forms[0];
	if (form.localidadeOrigemID.value.length < 1){
	 	form.setorComercialOrigemCD.disabled = false;
	 	form.quadraOrigemNM.disabled = false;
     	form.loteOrigem.disabled = false;

	 	form.setorComercialDestinoCD.disabled = false;
	 	form.quadraDestinoNM.disabled = false;
     	form.loteDestino.disabled = false;
   }    	
}


function limparMsgQuadraInexistente() {
	var msgQuadraOrigem = document.getElementById("msgQuadraInicial");
	var msgQuadraDestino = document.getElementById("msgQuadraFinal");
	
	if (msgQuadraOrigem != null){
		msgQuadraOrigem.innerHTML = "";
	}
	if (msgQuadraDestino != null){
		msgQuadraDestino.innerHTML = "";
	}

}
