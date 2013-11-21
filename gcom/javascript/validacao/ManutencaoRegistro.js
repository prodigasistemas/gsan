
function CheckboxNaoVazio(campo)
{
    form = document.forms[0];
	retorno = false;

	for(indice = 0; indice < form.elements.length; indice++){
		if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
			retorno = true;
			break;
		}
	}

	if (!retorno){
		alert('Nenhum registro selecionado para remoção.'); 
	}

	return retorno;
}

function CheckboxNaoVazioAtt(campo)
{
    form = document.forms[0];
	retorno = false;

	for(indice = 0; indice < form.elements.length; indice++){
		if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
			retorno = true;
			break;
		}
	}

	if (!retorno){
		alert('Nenhum registro selecionado para atualização.'); 
	}

	return retorno;
}

function CheckboxNaoVazioMensagem(mensagem, campo)
{
    
	form = document.forms[0];
	retorno = false;

	for(indice = 0; indice < form.elements.length; indice++){
		if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
			retorno = true;
			break;
		}
	}

	if (!retorno){
		alert(mensagem + ' para exclus?o.');
	}

	return retorno;
}



function CheckboxNaoVazioMensagemGenerico(mensagem, campo)
{
    form = document.forms[0];
	retorno = false;

	for(indice = 0; indice < form.elements.length; indice++){
		if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
			retorno = true;
			break;
		}
	}

	if (!retorno){
		alert(mensagem);
	}

	return retorno;
}


function obterValorCheckboxMarcado(){
	form = document.forms[0];
	retorno = "";

	for(indice = 0; indice < form.elements.length; indice++){
		if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
			retorno = retorno + form.elements[indice].value + ",";
		}
	}

	retorno = retorno.substring(0, retorno.length - 1);

	return retorno;
}


function obterValorCheckboxMarcadoPorNome(nomeCampo){
	form = document.forms[0];
	retorno = "";

	for(indice = 0; indice < form.elements.length; indice++){
		if (form.elements[indice].type == "checkbox" && form.elements[indice].name == nomeCampo &&
			form.elements[indice].checked == true) {
			retorno = retorno + form.elements[indice].value + ",";
		}
	}

	retorno = retorno.substring(0, retorno.length - 1);

	return retorno;
}


function RadioNaoVazioMensagem(mensagem, campo)
{
    
	form = document.forms[0];
	retorno = false;

	for(indice = 0; indice < form.elements.length; indice++){
		if (form.elements[indice].type == "radio" && form.elements[indice].checked == true) {
			retorno = true;
			break;
		}
	}

	if (!retorno){
		alert("Selecione um(a) " + mensagem + ".");
	}

	return retorno;
}


function obterValorRadioMarcado(){
	var form = document.forms[0];
	retorno = "";

	for(indice = 0; indice < form.elements.length; indice++){
		if (form.elements[indice].type == "radio" && form.elements[indice].checked == true) {
			retorno = form.elements[indice].value;
			break;
		}
	}

	return retorno;
}


function reiniciarListBox(listBox){
    	
	for ( i = (listBox.length -1); i >= 0; i--){
        	
		if (listBox.options[i].value.length > 0 && listBox.options[i].value != "-1") {
        	listBox.options[i] = null;
       	}
   	}
}

function CheckboxAutorizarNaoVazio(campo)
{
    form = document.forms[0];
	retorno = false;

	for(indice = 0; indice < form.elements.length; indice++){
		if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true && form.elements[indice].name == "idRegistrosAutorizar") {
			retorno = true;
			break;
		}
	}

	if (!retorno){
		alert('Nenhum registro selecionado para autoriza??o.'); 
	}

	return retorno;
}
function CheckboxRemoverNaoVazio(campo)
{
    form = document.forms[0];
	retorno = false;

	for(indice = 0; indice < form.elements.length; indice++){
		if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true && form.elements[indice].name == "idRegistrosRemocao") {
			retorno = true;
			break;
		}
	}

	if (!retorno){
		alert('Nenhum registro selecionado para remo??o.'); 
	}

	return retorno;
}

function obterValorRadioMarcadoPorNome(nomeCampo){
	var form = document.forms[0];
	retorno = "";

	for(indice = 0; indice < form.elements.length; indice++){
		if (form.elements[indice].type == "radio" && form.elements[indice].name == nomeCampo 
			&& form.elements[indice].checked == true) {
			retorno = form.elements[indice].value;
			break;
		}
	}

	return retorno;
}

function RadioNaoVazioMensagemPorNome(mensagem, nomeCampo){
    
	form = document.forms[0];
	retorno = false;

	for(indice = 0; indice < form.elements.length; indice++){
		if (form.elements[indice].type == "radio" && form.elements[indice].name == nomeCampo
			&& form.elements[indice].checked == true) {
			
			retorno = true;
			break;
		}
	}

	if (!retorno){
		alert("Selecione um(a) " + mensagem + ".");
	}

	return retorno;
}
