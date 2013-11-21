<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.util.ConstantesSistema"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>


<SCRIPT LANGUAGE="JavaScript">
<!--

//Vivianne
function desfazer(){
	var form = document.FiltrarSituacaoEspecialFaturamentoActionForm;
	form.idsCategoria.value = '-1';
	limparBorrachaOrigem(1);
	limpar(3);
	limpar(1);
	bloquearM();
	form.idImovel.focus;
}
//Vivianne
function limparMatricula(){
	var form = document.FiltrarSituacaoEspecialFaturamentoActionForm;
	form.inscricaoImovel.value = '';
	limparEndereco();
}
//Vivianne
function limparEndereco(){
var form = document.FiltrarSituacaoEspecialFaturamentoActionForm;
	if ((form.idImovel.value == '') && (!form.idImovel.disabled)){
		form.action = 'exibirFiltrarSituacaoEspecialFaturamentoAction.do';
		form.submit();
	}
}
//Vivianne
function replicarLocalidade(){
	var formulario = document.forms[0]; 
	formulario.localidadeDestinoID.value = formulario.localidadeOrigemID.value;
	formulario.setorComercialOrigemCD.focus;
}  
//Hugo Fernando
function replicarPeriodo(){
	var formulario = document.forms[0]; 
	formulario.mesAnoReferenciaFaturamentoFinal.value = formulario.mesAnoReferenciaFaturamentoInicial.value;
	formulario.mesAnoReferenciaFaturamentoInicial.focus;

} 

 -->    
</script>  

<SCRIPT LANGUAGE="JavaScript">
<!--
//savio
function quantidadeImovel(){
var form = document.FiltrarSituacaoEspecialFaturamentoActionForm;
return (form.quantidadeImoveisCOMSituacaoEspecialFaturamento.value * 1) + (form.quantidadeImoveisSEMSituacaoEspecialFaturamento.value * 1); 

}
 -->    
</script>  

 

<SCRIPT LANGUAGE="JavaScript">
<!--
function limpar(tipo){

	var form = document.FiltrarSituacaoEspecialFaturamentoActionForm;
   
	switch (tipo){
	//savio
	
     case 1:
		   form.idImovel.value = "";
		   form.inscricaoImovel.value = "";
		   //document.getElementById("endereco").innerHTML = "&nbsp;";
		   limparEndereco();
		   bloquearLSQLS();
	   
		   break;   
		   //savio
	   case 3:
		   form.localidadeDestinoID.value = "";
		   form.nomeLocalidadeDestino.value = "";
		   break;  

	   default:
          break;
	}
}
 -->    
</script>  

<SCRIPT LANGUAGE="JavaScript">
<!--

function bloquearLSQLS(){
	var form = document.FiltrarSituacaoEspecialFaturamentoActionForm;
	
	if(form.idImovel.value != "" ){//se matricula imovel nao for vazio entao deshabilita todos os texts
		form.localidadeOrigemID.disabled = true;
        form.mesAnoReferenciaFaturamentoInicial.disabled = true;
        form.localidadeOrigemID.value = "";
        form.mesAnoReferenciaFaturamentoInicial.value = "";
        
		form.localidadeDestinoID.disabled = true;
		form.mesAnoReferenciaFaturamentoFinal.disabled = true;
		form.localidadeDestinoID.value = "";
		form.mesAnoReferenciaFaturamentoFinal.value = "";
	
		limpar(3);
		
		return false;
	}

	if(form.idImovel.value == ""){//se matricula imovel for vazio entao habilita todos os texts

		//document.getElementById("endereco").innerHTML = '&nbsp;';
		form.inscricaoImovel.value = "";
		form.localidadeOrigemID.disabled = false;
		form.mesAnoReferenciaFaturamentoInicial.disabled = false;
		
		form.localidadeDestinoID.disabled = false;
		form.mesAnoReferenciaFaturamentoFinal.disabled = false;
	}
}


function bloquearM(){
	var form = document.FiltrarSituacaoEspecialFaturamentoActionForm;

	if(form.localidadeOrigemID.value != "" || form.mesAnoReferenciaFaturamentoInicial.value != "" || form.localidadeDestinoID.value != "" || form.mesAnoReferenciaFaturamentoFinal.value != ""){
	  form.idImovel.value = "";
	  form.idImovel.disabled = true;
	}
	if(form.localidadeOrigemID.value == "" && form.mesAnoReferenciaFaturamentoInicial.value == "" && form.localidadeDestinoID.value == "" && form.mesAnoReferenciaFaturamentoFinal.value == ""){
	  form.idImovel.disabled = false;
	}
}

 -->    
</script>  

<SCRIPT LANGUAGE="JavaScript">
<!--
/* hugo fernando
Esta funcao, verifica se o formulario pode ser submetido de acordo com os campos informados
no formulario:
- Caso o usuario informar o Imovel o form pode ser submetido.
- Coso o usuario nao informar o imovel, ele precisa informar as Localidades 
e os periodos obrigatoriamente. 
*/
function validarESubmeterForm(){
form                               = document.forms[0];
idImovel                           = form.idImovel.value;
localidadeOrigemID                 = form.localidadeOrigemID.value;
mesAnoReferenciaFaturamentoInicial = form.mesAnoReferenciaFaturamentoInicial.value;
localidadeDestinoID                = form.localidadeDestinoID.value;
mesAnoReferenciaFaturamentoFinal   = form.mesAnoReferenciaFaturamentoFinal.value;
erro                               = false;
msg                                = "Por favor Informe:\n";

if(idImovel == "" && localidadeOrigemID == "" && localidadeDestinoID == ""
 && mesAnoReferenciaFaturamentoInicial == "" && mesAnoReferenciaFaturamentoFinal == ""){
 erro = true;
alert("Informe Imóvel ou Localidades e Períodos");
form.idImovel.focus();
}

else if(localidadeOrigemID != "" || localidadeDestinoID != "" || mesAnoReferenciaFaturamentoInicial != ""
 || mesAnoReferenciaFaturamentoFinal != "" ){

if(localidadeOrigemID == ""){
form.localidadeOrigemID.focus();
msg += "Localidade Inicial\n";
erro = true;
}

if(mesAnoReferenciaFaturamentoInicial == ""){
msg += "Período Inicial\n";
if(localidadeOrigemID != "")
form.mesAnoReferenciaFaturamentoInicial.focus();
erro = true;
}

if(localidadeDestinoID == ""){
msg += "Localidade Final\n";
if(localidadeOrigemID != "" && mesAnoReferenciaFaturamentoInicial != "")
form.localidadeDestinoID.focus();
erro = true;
}

if(mesAnoReferenciaFaturamentoFinal == ""){
msg += "Período Final\n";
if(mesAnoReferenciaFaturamentoInicial != "" && localidadeOrigemID != "" && localidadeDestinoID != "")
form.mesAnoReferenciaFaturamentoFinal.focus();
erro = true;
}
if(erro == true){
alert(msg);
}
}

if (erro == false){
document.forms[0].action = "filtrarSituacaoEspecialFaturamentoAction.do";
document.forms[0].submit();
}

}// fim da funcao validarESubmeterForm();


function limparForm(){
	var form = document.forms[0];

	//form.nomeSetorComercialOrigem.style.color = "#000000";
	//desabilitaIntervaloDiferente(2);
	
	//matricula	
	resetarEHabilitar(form.idImovel);
	resetarEHabilitar(form.inscricaoImovel);

	if(document.getElementById("divEndereco")){
		document.getElementById("divEndereco").style.display = 'none';
	}
	
	//localidade e periodo inicial
	resetarEHabilitar(form.localidadeOrigemID);	
	resetarEHabilitar(form.nomeLocalidadeOrigem);
	resetarEHabilitar(form.mesAnoReferenciaFaturamentoInicial);	

	//localidade e periodo final
	resetarEHabilitar(form.localidadeDestinoID);	
	resetarEHabilitar(form.nomeLocalidadeDestino);
	resetarEHabilitar(form.mesAnoReferenciaFaturamentoFinal);		


	//situacao
	form.idFaturamentoSituacaoTipo.value = '-1';
	form.idFaturamentoSituacaoTipo.disabled = false;
	
}

function resetarEHabilitar(campo){
	campo.value = '';
	campo.disabled = false;
}

function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

	var form = document.FiltrarSituacaoEspecialFaturamentoActionForm;

	if (tipoConsulta == 'setorComercialOrigem') {
      form.setorComercialOrigemCD.value = codigoRegistro;
      form.setorComercialOrigemID.value = idRegistro;
	  form.nomeSetorComercialOrigem.value = descricaoRegistro;
	  form.nomeSetorComercialOrigem.style.color = "#000000";
	  form.setorComercialDestinoCD.value = codigoRegistro;
      form.setorComercialDestinoID.value = idRegistro;
	  form.nomeSetorComercialDestino.value = descricaoRegistro;
	  form.nomeSetorComercialDestino.style.color = "#000000"; 
	  desabilitaIntervaloDiferente(2);
	  form.quadraOrigemNM.focus();
	}

	if (tipoConsulta == 'setorComercialDestino') {
      form.setorComercialDestinoCD.value = codigoRegistro;
      form.setorComercialDestinoID.value = idRegistro;
	  form.nomeSetorComercialDestino.value = descricaoRegistro;
	  form.nomeSetorComercialDestino.style.color = "#000000"; 
	  desabilitaIntervaloDiferente(2);
  	  form.quadraDestinoNM.focus();
	}
}
 -->    
</script>  

<SCRIPT LANGUAGE="JavaScript">
<!--
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	var form = document.FiltrarSituacaoEspecialFaturamentoActionForm;
	if (tipoConsulta == 'localidadeOrigem') {
      form.localidadeOrigemID.value = codigoRegistro;
	  form.nomeLocalidadeOrigem.value = descricaoRegistro;
	  form.localidadeDestinoID.value = codigoRegistro;
      form.nomeLocalidadeDestino.value = descricaoRegistro;
	  form.nomeLocalidadeOrigem.style.color = "#000000";
	  form.nomeLocalidadeDestino.style.color = "#000000";
	  desabilitaIntervaloDiferente(1);  
	  form.setorComercialOrigemCD.focus();
	  bloquearM()
	}

	if (tipoConsulta == 'localidadeDestino') {
      form.localidadeDestinoID.value = codigoRegistro;
      form.nomeLocalidadeDestino.value = descricaoRegistro;
	  form.nomeLocalidadeDestino.style.color = "#000000";
	  desabilitaIntervaloDiferente(1);
	  form.setorComercialDestinoCD.focus();
	}


	if(tipoConsulta == 'imovel'){
         form.idImovel.value = codigoRegistro;
         document.FiltrarSituacaoEspecialFaturamentoActionForm.action = 'exibirFiltrarSituacaoEspecialFaturamentoAction.do?bloquear=todos';
         document.FiltrarSituacaoEspecialFaturamentoActionForm.submit();
    }
	
	

}


function validarForm(form){
   	// Campos relacionados a inscrição de origem
	var localidadeOrigem = form.localidadeOrigemID;


	// Campos relacionados a inscrição de destino
	var localidadeDestino = form.localidadeDestinoID;
	

	var obrigatorio = false;

	//Origem
	if (localidadeOrigem.value.length < 1){
		alert("A Matrícula ou as Localidades devem ser informadas.");
		form.idImovel.focus();
//		localidadeOrigem.focus();
		obrigatorio = true;
	}else if (!testarCampoValorZero(localidadeOrigem, "Localidade Inicial")){
		localidadeOrigem.focus();
		obrigatorio = true;
	}

	//Destino
	if (!obrigatorio){
		if (localidadeDestino.value.length < 1){
			alert("Informe Localidade Final.");
			localidadeDestino.focus();
			obrigatorio = true;
		}else if (!testarCampoValorZero(localidadeDestino, "Localidade Final")){
			localidadeDestino.focus();
			obrigatorio = true;
		}else if (setorComercialDestino.value.length > 0 && 
			  !testarCampoValorZero(setorComercialDestino, "Setor Comercial Final")){

			setorComercialDestino.focus();
			obrigatorio = true;
		}else if (quadraDestino.value.length > 0 && !testarCampoValorZero(quadraDestino, "Quadra Final")){
			quadraDestino.focus();
			obrigatorio = true;
		}else{
			obrigatorio = campoObrigatorio(setorComercialDestino, quadraDestino, "Informe Setor Comercial Final.");
			if (!obrigatorio){
				obrigatorio = campoObrigatorio(quadraDestino, loteDestino, "Informe Quadra Final.");
			}
			if (!obrigatorio){
			obrigatorio = campoObrigatorio(loteDestino, subloteDestino, "Informe Lote Final.");
		
			}
		}
	}
	
	//Valida campos da inscrição inicial > os campos da inscrição final
	if (eval(localidadeOrigem.value) > eval(localidadeDestino.value)){
		alert('Localidade Final deve ser maior ou igual a Localidade Inicial.');
		obrigatorio = true;
		localidadeDestino.focus();
	}
	
	// Confirma a validação do formulário
	if (!obrigatorio && validateFiltrarSituacaoEspecialFaturamentoActionForm(form)){
		return true;	
	}else{
		return false;
	}

}
 -->    
</script>  

<SCRIPT LANGUAGE="JavaScript">
<!--
function campoObrigatorio(campoDependencia, dependente, msg){
	if (dependente.value.length < 1){
		return false
	}else if (campoDependencia.value.length < 1){
		alert(msg);
		campoDependencia.focus();
		return true
	}
}

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,campo){
	if(!campo.disabled){
	  if (objeto == null || codigoObjeto == null){
	     if(tipo == "" ){
	      abrirPopup(url,altura, largura);
	     }else{
		  abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		 }
	 }else{
		if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
			alert(msg);
		}
		else{
			abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
		}
	}
  }
}

function validarLocalidade(){
	var form = document.FiltrarSituacaoEspecialFaturamentoActionForm;
	if( form.localidadeOrigemID.value == form.localidadeDestinoID.value ){
		form.setorComercialOrigemID.disabled = false;
		form.setorComercialDestinoID.disabled = false;
	}else if( form.localidadeOrigemID.value != form.localidadeDestinoID.value ){
		form.setorComercialOrigemID.disabled = true;
		form.setorComercialDestinoID.disabled = true;
		form.setorComercialOrigemID.value = '';
		form.setorComercialDestinoID.value = '';
		form.quadraOrigemID.value = '';
		form.quadraDestinoID.value = '';
	}else if( form.setorComercialOrigemID.value != form.setorComercialDestinoID.value ){
			form.quadraOrigemID.disabled = false;
			form.quadraDestinoID.disabled = false;
	}

}

 -->    
</script>  

<SCRIPT LANGUAGE="JavaScript">
<!--
function limparBorracha(tipo){
form                               = document.forms[0];
idImovel                           = form.idImovel.value;
localidadeOrigemID                 = form.localidadeOrigemID.value;
mesAnoReferenciaFaturamentoInicial = form.mesAnoReferenciaFaturamentoInicial.value;
localidadeDestinoID                = form.localidadeDestinoID.value;
mesAnoReferenciaFaturamentoFinal   = form.mesAnoReferenciaFaturamentoFinal.value;

if(tipo == 1){
if(!form.idImovel.disabled){
document.getElementById("divEndereco").style.display = 'none';
form.idImovel.value="";
form.inscricaoImovel.value="";
form.localidadeOrigemID.disabled                 = false;
form.mesAnoReferenciaFaturamentoInicial.disabled = false;
form.localidadeDestinoID.disabled                = false;
form.mesAnoReferenciaFaturamentoFinal.disabled   = false;
form.idImovel.focus();
}
}

else if(tipo == 2){
if(!form.localidadeOrigemID.disabled){
form.localidadeOrigemID.value  = "";
form.localidadeDestinoID.value = "";
form.nomeLocalidadeOrigem.value = "";
form.nomeLocalidadeDestino.value = "";
form.localidadeOrigemID.focus();
if(mesAnoReferenciaFaturamentoInicial == "" && mesAnoReferenciaFaturamentoFinal == "")
form.idImovel.disabled = false;
}
}

else if(tipo == 3){
if(!form.localidadeDestinoID.disabled){
form.localidadeDestinoID.value = "";
form.nomeLocalidadeDestino.value = "";
form.localidadeDestinoID.focus();
if(mesAnoReferenciaFaturamentoInicial == "" && mesAnoReferenciaFaturamentoFinal == "" && localidadeOrigemID == "")
form.idImovel.disabled = false;
}
}
	
}// fim da funcao limparBorrachaOrigem();
 -->    
</script>  

<SCRIPT LANGUAGE="JavaScript">
<!--


function limparOrigem(tipo){
	var form = document.FiltrarSituacaoEspecialFaturamentoActionForm;

	switch(tipo){
		case 1: //De localidade pra baixo
			form.idImovel.disabled = false;
			form.localidadeDestinoID.value = "";
			form.nomeLocalidadeDestino.value = "";
			habilitaSQlS();	
	}
}
 -->    
</script>  

<SCRIPT LANGUAGE="JavaScript">
<!--
function limparDestino(tipo){
	var form = document.FiltrarSituacaoEspecialFaturamentoActionForm;

	switch(tipo){
		case 1: //De localidade pra baixo
			if(form.localidadeOrigemID.value != form.localidadeDestinoID.value){
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
	           form.subloteDestino.value = "";
	           
	       }    
	}
}



//-->
</SCRIPT>
<script>
<!-- Begin 

     var bCancel = false; 

    function validateFiltrarSituacaoEspecialFaturamentoActionForm(form) {                                                                   
        if (bCancel) 
      return true; 
        else 
       return validateCaracterEspecial(form) && validateLong(form); 
   } 
 -->    
</script>  

<SCRIPT LANGUAGE="JavaScript">
<!--
    function caracteresespeciais () { 
     this.aa = new Array("localidadeOrigemID", "Localidade Inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("setorComercialOrigemCD", "Setor Comercial Inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("quadraOrigemNM", "Quadra Inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("loteOrigem", "Lote Inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ai = new Array("subloteOrigem", "Sublote Inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("localidadeDestinoID", "Localidade Final possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.af = new Array("setorComercialDestinoCD", "Setor Comercial Final possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ag = new Array("quadraDestinoNM", "Quadra Final possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ah = new Array("loteDestino", "Lote Final possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.aj = new Array("subloteDestino", "Sublote Final possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    } 

    function IntegerValidations () { 
     this.aa = new Array("localidadeOrigemID", "Localidade Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("setorComercialOrigemCD", "Setor Comercial Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("quadraOrigemNM", "Quadra Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("loteOrigem", "Lote Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ai = new Array("subloteOrigem", "Sublote Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("localidadeDestinoID", "Localidade Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.af = new Array("setorComercialDestinoCD", "Setor Comercial Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ag = new Array("quadraDestinoNM", "Quadra Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ah = new Array("loteDestino", "Lote Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.aj = new Array("subloteDestino", "Sublote Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    } 
-->    
</script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarSituacaoEspecialFaturamentoActionForm"
	dynamicJavascript="false" />

</head>

<body leftmargin="5" topmargin="5"
	onload="bloquearM();bloquearLSQLS();desabilitaIntervaloDiferente(${requestScope.campoDesabilita});setarFoco('${requestScope.nomeCampo}');">


<html:form action="/filtrarSituacaoEspecialFaturamentoAction"
	type="gcom.gui.faturamento.FiltrarSituacaoEspecialFaturamentoActionForm"
	name="FiltrarSituacaoEspecialFaturamentoActionForm" method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="130" valign="top" class="leftcoltext">
			<div align="center">
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>

			<%@ include file="/jsp/util/informacoes_usuario.jsp"%>

			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>

			<%@ include file="/jsp/util/mensagens.jsp"%>

			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			</div>
			</td>

			<td width="615" valign="top" class="centercoltext">

			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Filtrar Situação Especial de Faturamento</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>Para filtrar a situa&ccedil;&atilde;o especial de
					faturamento, informe o im&oacute;vel ou o intervalo de
					inscri&ccedil;&atilde;o:</td>
					<td width="100" align="left" colspan="2"><html:checkbox
						property="indicadorAtualizar" value="1" /><strong>Atualizar</strong></td>
				</tr>
				</table>
			<table width="100%" border="0">
				<tr>
					<td width="23%"><strong> Matr&iacute;cula:</strong>
					</td>
					<td>
						<logic:notEqual parameter="bloquear" value="matricula">
							<html:text maxlength="9" property="idImovel" size="9"
								onkeypress="validaEnterComMensagem(event, 'exibirFiltrarSituacaoEspecialFaturamentoAction.do?bloquear=todos', 'idImovel','Matrícula do Imóvel');
								return isCampoNumerico(event);"
								onkeyup="bloquearLSQLS();" tabindex="1" onblur="bloquearLSQLS();" />
								
							<a href="javascript:chamarPopup('exibirPesquisarImovelAction.do', '', null, null, 500, 800, '',document.forms[0].idImovel);"><img
							width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Matrícula" /></a>
							
							<logic:equal name="corImovel" value="exception">
								<html:text property="inscricaoImovel" size="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									 />
							</logic:equal>
	
							<logic:notEqual name="corImovel" value="exception">
								<html:text property="inscricaoImovel" size="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									/>
							</logic:notEqual>

							<a href="javascript:limparBorracha(1);"> <img
								src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar Matrícula" /></a>
						</logic:notEqual> 
					
						<logic:equal parameter="bloquear" value="matricula">
							<html:text maxlength="9" property="idImovel" size="9"
								disabled="true"
								onkeypress="validaEnter(event, 'exibirFiltrarSituacaoEspecialFaturamentoAction.do?bloquear=todos', 'idImovel');
								return isCampoNumerico(event);"
								onkeyup="bloquearLSQLS();document.forms[0].inscricaoImovel.value = '';" />
	
							<a href="javascript:chamarPopup('exibirPesquisarImovelAction.do', '', null, null, 500, 800, '',document.forms[0].idImovel);"><img
							width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Matrícula" /></a>
							<logic:equal name="corImovel" value="exception">
								<html:text property="inscricaoImovel" size="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									disabled="true" />
							</logic:equal>
	
							<logic:notEqual name="corImovel" value="exception">
								<html:text property="inscricaoImovel" size="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									/>
							</logic:notEqual>
	
							<a href="javascript:limparBorracha(1);"> <img
								src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar Matrícula" /></a>
						</logic:equal>
					</td>
				</tr>
				
				<tr>					
				  <td colspan="3">
					<div id="divEndereco">
					<logic:present name= "enderecoFormatado" scope= "request">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<table width="100%" border="0" bgcolor="#90c7fc">
									<tr bgcolor="#90c7fc" height="18">
										<td align="center"><strong>Endere&ccedil;o</strong></td>
									</tr>
									</table>
								</td>
							</tr>
							
								<tr>
									<td>
										<table width="100%" align="center" bgcolor="#99CCFF">
											<!--corpo da segunda tabela-->
												<tr bgcolor="#FFFFFF" height="18">	
													<td>
														<div align="center"><span id="endereco"><bean:write
															name="FiltrarSituacaoEspecialFaturamentoActionForm" property="endereco" />&nbsp;</span></div>
														<html:hidden property="endereco" />
													</td>
												</tr>
										</table>
								  	</td>
								</tr>							
						</table>
					</logic:present></div>
					</td>
				</tr>
				
				<tr>
					<td colspan="3">
					<hr>
					</td>
				</tr>
				<tr>
					<td colspan="3">Informe os dados da inscri&ccedil;&atilde;o
					inicial:</td>
				</tr>
				<tr>
					<logic:equal scope="session" parameter="bloquear" value="todos">
					
						<td><strong>Localidade:</strong></td>
						<td colspan="2">
						
						<html:text maxlength="3" 
							property="localidadeOrigemID"
							size="3"
							disabled="true"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarSituacaoEspecialFaturamentoAction.do?objetoConsulta=1&inscricaoTipo=origem&bloquear=matricula', 'localidadeOrigemID','Código da Localidade');
							return isCampoNumerico(event);"
							tabindex="2" 
							onblur="javascript:replicarLocalidade();" 
							onkeyup = "javascript:bloquearM();limparOrigem(1);" /> 
							
							<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '',document.forms[0].localidadeOrigemID);bloquearM();limparOrigem(1);"><img
							width="23" height="21" border="0" style="cursor:hand;"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Localidade" /></a>
							
							<logic:present name="corLocalidadeOrigem">

							<logic:equal name="corLocalidadeOrigem" value="exception">
								<html:text property="nomeLocalidadeOrigem" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									disabled="true" />
							</logic:equal>

							<logic:notEqual name="corLocalidadeOrigem" value="exception">
								<html:text property="nomeLocalidadeOrigem" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									disabled="true" />
							</logic:notEqual>

						</logic:present> <logic:notPresent name="corLocalidadeOrigem">

							<logic:empty name="FiltrarSituacaoEspecialFaturamentoActionForm"
								property="localidadeOrigemID">
								<html:text property="nomeLocalidadeOrigem" value="" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									disabled="true" />
							</logic:empty>
							<logic:notEmpty name="FiltrarSituacaoEspecialFaturamentoActionForm"
								property="localidadeOrigemID">
								<html:text property="nomeLocalidadeOrigem" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: 	#000000"
									disabled="true" />
							</logic:notEmpty>
						</logic:notPresent> 

						<a
						href="javascript:limparBorracha(2);">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Localidade" /> </a>
				</td>
				
				<tr>
					<td><strong>Per&iacute;odo:</strong></td>
					<td>
					<html:text property="mesAnoReferenciaFaturamentoInicial"
					styleId="mesAnoReferenciaFaturamentoInicial" size="7" maxlength="7"
					onkeypress="return isCampoNumerico(event);"
					onkeyup="mascaraAnoMes(this, event);"
					disabled="true"
					/>
					mm/aaaa
					</td>
			   </tr>
				
				<tr>
					<td colspan="3">
					<hr>
					</td>
				</tr>

				<tr>
					<td colspan="3">Informe os dados da inscri&ccedil;&atilde;o final:</td>
				</tr>
				
				<tr>
					<td><strong>Localidade:</strong></td>
					<td colspan="2">
					<html:text maxlength="3" property="localidadeDestinoID"
						size="3"
						onkeypress="validaEnter(event, 'exibirFiltrarSituacaoEspecialFaturamentoAction.do?objetoConsulta=1&inscricaoTipo=destino&bloquear=matricula&campoDesabilita=1', 'localidadeDestinoID');
						return isCampoNumerico(event);"
						tabindex="7" disabled="true"
						onkeyup = "javascript:bloquearM();limparOrigem(1);" />
											
						<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', null, null, 275, 480, '',form.documents[0].localidadeDestinoID);limparDestino(1);"><img
							width="23" height="21" border="0" style="cursor:hand;"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Localidade" />
						</a>
					
						 <logic:present name="corLocalidadeDestino">

						<logic:equal name="corLocalidadeDestino" value="exception">
							<html:text property="nomeLocalidadeDestino" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								disabled="true" />
						</logic:equal>

						<logic:notEqual name="corLocalidadeDestino" value="exception">
							<html:text property="nomeLocalidadeDestino" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								disabled="true" />
						</logic:notEqual>

					</logic:present> <logic:notPresent name="corLocalidadeDestino">

						<logic:empty name="FiltrarSituacaoEspecialFaturamentoActionForm"
							property="localidadeDestinoID">
							<html:text property="nomeLocalidadeDestino" value="" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								disabled="true" />
						</logic:empty>
						<logic:notEmpty name="FiltrarSituacaoEspecialFaturamentoActionForm"
							property="localidadeDestinoID">
							<html:text property="nomeLocalidadeDestino" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: 	#000000"
								disabled="true" />
						</logic:notEmpty>
					</logic:notPresent>
	
					<a href="javascript:limparBorracha(3);">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" style="cursor:hand; title="Apagar Localidade" /> </a>
						
					</td>
				</tr>
				
				<tr>
					<td><strong>Per&iacute;odo:</strong></td>
					<td>
					<html:text property="mesAnoReferenciaFaturamentoFinal"
					styleId="mesAnoReferenciaFaturamentoFinal" size="7" maxlength="7"
					onkeypress="return isCampoNumerico(event);"
					onkeyup="mascaraAnoMes(this, event);"
					disabled="true"
					/>
					mm/aaaa
					</td>
				</tr>
				
				</logic:equal>
                  
                <!--  -->
				<logic:notEqual scope="session" parameter="bloquear" value="todos">
					<tr>
						<td><strong>Localidade:</strong></td>
						<td colspan="2"><html:text maxlength="3" property="localidadeOrigemID"
							size="3"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarSituacaoEspecialFaturamentoAction.do?objetoConsulta=1&inscricaoTipo=origem&bloquear=matricula', 'localidadeOrigemID','Código da Localidade');
							return isCampoNumerico(event);"
							tabindex="2" 
							onblur="bloquearM();replicarLocalidade();" 
							onkeyup ="bloquearM();"
							/>
					
						<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '',document.forms[0].localidadeOrigemID);
							document.forms[0].localidadeOrigemID.focus();"><img
							width="23" height="21" border="0" 
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Localidade" /></a>
					
							<logic:present
							name="corLocalidadeOrigem">

							<logic:equal name="corLocalidadeOrigem" value="exception">
								<html:text property="nomeLocalidadeOrigem" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>

							<logic:notEqual name="corLocalidadeOrigem" value="exception">
								<html:text property="nomeLocalidadeOrigem" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>

						</logic:present> <logic:notPresent name="corLocalidadeOrigem">

							<logic:empty name="FiltrarSituacaoEspecialFaturamentoActionForm"
								property="localidadeOrigemID">
								<html:text property="nomeLocalidadeOrigem" value="" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="FiltrarSituacaoEspecialFaturamentoActionForm"
								property="localidadeOrigemID">
								<html:text property="nomeLocalidadeOrigem" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: 	#000000" />
							</logic:notEmpty>
						</logic:notPresent> 
						
						<a href="javascript:limparBorracha(2);">
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar Localidade" /> </a>
					</td>
					</tr>
					
					<tr>
					<td><strong>Per&iacute;odo:</strong></td>
					<td>
					<html:text property="mesAnoReferenciaFaturamentoInicial" 
					styleId="mesAnoReferenciaFaturamentoInicial" size="7" maxlength="7"
					onkeypress="return isCampoNumerico(event);"
					onkeyup="mascaraAnoMes(this, event);bloquearM();"
					onblur="replicarPeriodo();bloquearM();" 
					/>
					mm/aaaa
					</td>
					</tr>

				    <tr>
						<td colspan="3">
							<hr>
						</td>
					</tr>

					<tr>
						<td colspan="3">Informe os dados da inscri&ccedil;&atilde;o final:</td>
					</tr>
					
					<tr>
						<td><strong>Localidade:</strong></td>
						<td colspan="2"><html:text maxlength="3" property="localidadeDestinoID"
							size="3"
							onclick="validarLocalidade()"
							onkeypress="validaEnter(event, 'exibirFiltrarSituacaoEspecialFaturamentoAction.do?objetoConsulta=1&inscricaoTipo=destino&bloquear=matricula&bloquear=matricula&campoDesabilita=1', 'localidadeDestinoID');
							return isCampoNumerico(event);"
							tabindex="7"
							onkeyup = "bloquearM();"
							onblur="bloquearM();" />
							
													
						<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', null, null, 275, 480, '',document.forms[0].localidadeDestinoID);
					        document.forms[0].localidadeDestinoID.focus();"><img
							width="23" height="21" border="0" 
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Localidade" /></a>
							
							<logic:present name="corLocalidadeDestino">

							<logic:equal name="corLocalidadeDestino" value="exception">
								<html:text property="nomeLocalidadeDestino" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>

							<logic:notEqual name="corLocalidadeDestino" value="exception">
								<html:text property="nomeLocalidadeDestino" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>

						</logic:present> <logic:notPresent name="corLocalidadeDestino">

							<logic:empty name="FiltrarSituacaoEspecialFaturamentoActionForm"
								property="localidadeDestinoID">
								<html:text property="nomeLocalidadeDestino" value="" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="FiltrarSituacaoEspecialFaturamentoActionForm"
								property="localidadeDestinoID">
								<html:text property="nomeLocalidadeDestino" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: 	#000000" />
							</logic:notEmpty>
						</logic:notPresent> 
						
					<a href="javascript:limparBorracha(3);">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Localidade" /> </a>
							
							</td>
					</tr>
			
			        <tr>
					<td><strong>Per&iacute;odo:</strong></td>
					<td>
					<html:text property="mesAnoReferenciaFaturamentoFinal"
					styleId="mesAnoReferenciaFaturamentoFinal" size="7" maxlength="7"
					onkeypress="return isCampoNumerico(event);"
					onkeyup="mascaraAnoMes(this, event);bloquearM();" 
					onblur="bloquearM();" />
					mm/aaaa
					</td>
					</tr>
				
				</logic:notEqual>

				<tr>
					<td colspan="3">
					<hr>
					</td>
				</tr>
				
					<tr>
					<td colspan="3">
					</td>
				</tr>
				
				<tr>
					<td>
					<strong>Situa&ccedil;&atilde;o:</strong>
					</td>
						<td colspan="2">
						<html:select property="idFaturamentoSituacaoTipo" onchange="" styleId="situacao">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoFatSituacaoTipo" labelProperty="descricao"
								property="id" />
						</html:select>
						</td>
				</tr>
								
			</table>
			<br />
			<table width="100%" border="0">
				<tr>
					<td>
						<input name="botaoLimpar" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="javascript:limparForm();">
						<input type="button" name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
							onClick="javascript:document.forms[0].target='';window.location.href='/gsan/telaPrincipal.do'">
						
					</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td align="right">
						<input name="botaoFiltrar" 
							type="button"
							class="bottonRightCol" 
							value="Filtrar" 
							align="right"
							onClick="javascript:validarESubmeterForm();"/>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>

</body>
</html:html>