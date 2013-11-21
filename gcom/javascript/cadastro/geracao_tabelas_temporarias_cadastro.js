/*
*JavaScript Utilizado pela aba localiza??o do caso de uso Filtrar Geracao de Tabelas Temporarias para Atualizacao Cadastral
*/

function limparDestino(tipo){
	var form = document.forms[0];
	
	switch(tipo){
		case 1: //De localidade pra baixo
			if(form.localidadeInicial.value != form.localidadeFinal.value){
				form.nomeLocalidadeFinal.value = "";
				form.codigoSetorComercialInicial.value = "";
				form.codigoSetorComercialFinal.value = "";
		    }
		case 2: //De setor para baixo
			if(form.codigoSetorComercialInicial.value != form.codigoSetorComercialFinal.value){
			   form.nomeSetorComercialFinal.value = "";
			}
	}
}

function validarLocalidade(){
	var form = document.forms[0];
	if( form.localidadeInicial.value != "" ){
		form.codigoSetorComercialInicial.disabled = false;
		form.codigoSetorComercialFinal.disabled = false;
	}
	else if( form.localidadeInicial.value == "" ){
		form.codigoSetorComercialInicial.disabled = true;
		form.codigoSetorComercialFinal.disabled = true;
		form.codigoSetorComercialFinal.value = '';
	}
}

function duplicarLocalidade(){
	var formulario = document.forms[0]; 
	formulario.localidadeFinal.value = formulario.localidadeInicial.value;
}

function limparOrigem(tipo){
	var form = document.forms[0];
	
	switch(tipo){
		case 1: //De localidade pra baixo
			form.nomeLocalidadeInicial.value = "";
			form.localidadeFinal.value = "";
			form.nomeLocalidadeFinal.value = "";
			form.codigoSetorComercialInicial.value = "";
			//habilitaSQlS();
			
		case 2: //De setor para baixo

		   form.nomeSetorComercialInicial.value = "";
		   form.codigoSetorComercialFinal.value = "";   
		   form.nomeSetorComercialFinal.value = "";
	}
}





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

function chamarPopup1(url, tipo, objeto, codigoObjeto, altura, largura, msg){
	if (objeto == null || codigoObjeto == null){
		abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
	}
	else{
		if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
			alert(msg);
		}
		else{
			abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
		}
	}
}

function duplicarSetorComercial(){
	var formulario = document.forms[0]; 
	formulario.codigoSetorComercialFinal.value = formulario.codigoSetorComercialInicial.value;
}

/*
recupera os dados vindos pelo metodo enviarDadosQuatroParametros 
*/

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	var form = document.forms[0];

	if (tipoConsulta == 'localidadeOrigem') {
      form.localidadeInicial.value = codigoRegistro;
	  form.nomeLocalidadeInicial.value = descricaoRegistro;
	  form.nomeLocalidadeInicial.style.color = "#000000";
	  
	  form.localidadeFinal.value = codigoRegistro;
      form.nomeLocalidadeFinal.value = descricaoRegistro;
      form.nomeLocalidadeFinal.style.color = "#000000";
      form.codigoSetorComercialInicial.focus();
      controleSetorComercial();
	}

	if (tipoConsulta == 'localidadeDestino') {
      form.localidadeFinal.value = codigoRegistro;
      form.nomeLocalidadeFinal.value = descricaoRegistro;
   	  form.codigoSetorComercialFinal.focus();
   	  controleSetorComercial();
	}
	
	if (tipoConsulta == 'elo') {
		form.elo.value = codigoRegistro;
		form.nomeElo.value = descricaoRegistro;
		habilitaDesabilitaInscricao();
	}
	
	if (tipoConsulta == 'cliente') {
      limparPesquisaCliente();
      form.cliente.value = codigoRegistro;
      form.clienteNome.value = descricaoRegistro;
      form.clienteNome.style.color = "#000000";
	  habilitaDesabilitaMatriculaCliente(); 
	  habilitaDesabilitaAgenciaInscricao();
    }
    
    if(tipoConsulta == 'imovel'){
      limparPesquisaImovel();
      form.matricula.value = codigoRegistro;
      form.nomeImovel.value = descricaoRegistro;
      form.clienteNome.style.color = "#000000";
      habilitaDesabilitaMatriculaCliente(); 
	  habilitaDesabilitaAgenciaInscricao();
	}
	
	if(tipoConsulta == 'quadra'){
		form.quadraInicial.value = codigoRegistro;
		form.quadraFinal.value = codigoRegistro;
	}
}

/*
recupera os dados vindos pelo metodo enviarDadosQuatroParametros 
*/

function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {
	var form = document.forms[0];

	if (tipoConsulta == 'setorComercialOrigem') {
      form.codigoSetorComercialInicial.value = codigoRegistro;
	  form.nomeSetorComercialInicial.value = descricaoRegistro;
	  form.nomeSetorComercialInicial.style.color = "#000000";
	  
	  form.codigoSetorComercialFinal.value = codigoRegistro;
	  form.nomeSetorComercialFinal.value = descricaoRegistro;
	  form.nomeSetorComercialFinal.style.color = "#000000";
	  controleQuadra();
	}

	if (tipoConsulta == 'setorComercialDestino') {
      form.codigoSetorComercialFinal.value = codigoRegistro;
	  form.nomeSetorComercialFinal.value = descricaoRegistro;
	  form.nomeSetorComercialFinal.style.color = "#000000"; 
	  controleQuadra();
	}
}

function validarLocalidadeDiferentes(){
	var form = document.forms[0];

	if(form.localidadeInicial.value != '' && form.localidadeFinal.value == ''){
		alert("Informe Localidade Final.");
		form.localidadeFinal.focus();
		return false;
	}
	if(form.localidadeInicial.value == '' && form.localidadeFinal.value != ''){
		alert("Informe Localidade Inicial.");
		form.localidadeInicial.focus();
		return false;
	}
	return true;
}

function validarSetoresComerciaisDiferentes(){
	var form = document.forms[0];
	if(form.codigoSetorComercialInicial.value!= '' && form.codigoSetorComercialFinal.value == ''){
		alert("Informe Setor Comercial Final.");
		form.codigoSetorComercialFinal.focus();
		return false;
	}
	if(form.codigoSetorComercialInicial.value == '' && form.codigoSetorComercialFinal.value != ''){
		alert("Informe Setor Comercial Inicial.");
		form.codigoSetorComercialInicial.focus();
		return false;
	}
	return true;
}

function verificarSetoresComerciaisMenores(){
	var form = document.forms[0];

	if(form.codigoSetorComercialInicial.value != '' && form.codigoSetorComercialFinal.value != ''){
		if(parseInt(form.codigoSetorComercialFinal.value) < parseInt(form.codigoSetorComercialInicial.value)){
			alert("Setor Comercial Final deve ser maior ou igual a Setor Comercial Inicial.");	
			form.codigoSetorComercialFinal.focus();
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
	
	if(form.localidadeInicial.value != '' && form.localidadeFinal.value != ''){
		if(parseInt(form.localidadeFinal.value) < parseInt(form.localidadeInicial.value)){
			alert("Localidade Final deve ser maior ou igual a Localidade Inicial.");	
			form.localidadeFinal.focus();
			return false;
		}else{
			return true;
		}
	
	}else{
		return true;
	}
}

function controleClassificacao() {
	var form = document.forms[0];
	var sel = 0;
	var obj = form.gerenciaRegional;
	
	if ((form.classificacao.value == 'LOCAL') || (form.classificacao.value == 'SETORCOMERCIAL')) {
		for (var i = 0; i < obj.length; i++) {
			if (obj[i].selected) {
				sel++;
			}
		}
		
		if (sel == 0) {
			abilitaLocalSetor(true);
		}else if (sel == 1) {
			if (obj.selectedIndex == 0) {
				abilitaLocalSetor(true);
			}else {
				abilitaLocalSetor(false);
			}
		}else {
			abilitaLocalSetor(false);
		}
	}
}

function abilitaLocalSetor(condicao) {
	var form = document.forms[0];
	
	if (condicao) {
		form.localidadeInicial.disabled = false;
		document.getElementById('btPesqLocalidadeInicial').style.display = '';
		form.localidadeFinal.disabled = false;
		document.getElementById('btPesqLocalidadeFinal').style.display = '';
		
		form.codigoSetorComercialInicial.disabled = false;
		document.getElementById('btPesqSetorComercialInicial').style.display = '';
		form.codigoSetorComercialFinal.disabled = false;
		document.getElementById('btPesqSetorComercialFinal').style.display = '';
	}else {
		
		form.localidadeInicial.value = "";
		form.nomeLocalidadeInicial.value = "";
		form.localidadeInicial.disabled = true;
		document.getElementById('btPesqLocalidadeInicial').style.display = 'none';
		
		form.localidadeFinal.value = "";
		form.nomeLocalidadeFinal.value = "";
		form.localidadeFinal.disabled = true;
		document.getElementById('btPesqLocalidadeFinal').style.display = 'none';
		
		form.codigoSetorComercialInicial.value = "";
		form.nomeSetorComercialInicial.value = "";
		form.codigoSetorComercialInicial.disabled = true;
		document.getElementById('btPesqSetorComercialInicial').style.display = 'none';
		
		form.codigoSetorComercialFinal.value = "";
		form.nomeSetorComercialFinal.value = "";
		form.codigoSetorComercialFinal.disabled = true;
		document.getElementById('btPesqSetorComercialFinal').style.display = 'none';
	}
}

function limparBorrachaElo() {
	var form = document.forms[0];
	form.elo.value = "";
	form.nomeElo.value = "";
}

function duplicarQuadra(){
	var formulario = document.forms[0]; 
	formulario.quadraFinal.value = formulario.quadraInicial.value;
}

function chamarPopupSetorComercial(url, tipo, objeto, codigoObjeto, altura, largura, msg){
	if (objeto == null || codigoObjeto == null){
		abrirPopup(url + "&" + "tipo=" + tipo, altura, largura);
	}
	else{
		if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
			alert(msg);
		}
		else{
			abrirPopup(url + "&" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
		}
	}
}


