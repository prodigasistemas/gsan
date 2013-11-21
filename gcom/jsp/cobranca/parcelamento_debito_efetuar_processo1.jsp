<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page isELIgnored="false"%>
<%@ page import="gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="EfetuarParcelamentoDebitosActionForm" dynamicJavascript="false" />

<script language="JavaScript">
<!-- Begin 
 
var bCancel = false; 

function validateEfetuarParcelamentoDebitosActionForm(form) {                                                                   
	if (bCancel) 
		return true; 
	else 
		return validateCaracterEspecial(form) 
			&& validateRequired(form) 
			&& validateLong(form) 
			&& validateDate(form) 
			&& validaTodosRadioButton() 
			&& validaMesAnoInicio(form.inicioIntervaloParcelamento)
			&& validaMesAnoFim(form.fimIntervaloParcelamento)
			&& validaMesAnoInicioMesAnoFimRequerid()
			&& validaMesAnoInicioMenorQueMesAnoFim()
			&& validateCpf(form) ; 
} 

function validaMesAnoInicio(mesAno){
	if(mesAno.value != ""){
		return verificaAnoMesMensagemPersonalizada(mesAno,"Período Inicial do Intervalo do Parcelamento inválido.");
	}else{
		return true;
	}
}

 function cpf () { 
     this.aa = new Array("cpfClienteParcelamentoDigitado", "CPF inválido.", new Function ("varName", " return this[varName];"));
    } 

function validaMesAnoFim(mesAno){
	if(mesAno.value != ""){
		return verificaAnoMesMensagemPersonalizada(mesAno,"Período Final do Intervalo do Parcelamento inválido.");
	}else{
		return true;
	}
}

function validaMesAnoInicioMenorQueMesAnoFim(){

var form = document.forms[0];
var mesAnoInicio = form.inicioIntervaloParcelamento.value;
var mesAnoFim = form.fimIntervaloParcelamento.value;

 if ((mesAnoInicio!= '' && mesAnoFim != '') &&
  (comparaMesAno(mesAnoInicio, ">", mesAnoFim))){
 	alert('Período Inicial do Intervalo do Parcelamento não pode ser maior que Período Final do Intervalo do Parcelamento.')
 	return false;
 }else{
 	return true;
 }
}

function validaMesAnoInicioMesAnoFimRequerid(){

var form = document.forms[0];
var mesAnoInicio = form.inicioIntervaloParcelamento.value;
var mesAnoFim = form.fimIntervaloParcelamento.value;

 if (mesAnoInicio == '' && mesAnoFim != ''){
 	alert('Informe Período Inicial do Intervalo do Parcelamento.')
 	return false;
 }else if (mesAnoInicio != '' && mesAnoFim == ''){
 	alert('Informe Período Final do Intervalo do Parcelamento.')
 	return false;	
 }else {
 	return true;
 }
}

function caracteresespeciais () { 
	this.aa = new Array("matriculaImovel", "Matrícula do Imóvel possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("inicioIntervaloParcelamento", "Período Inicial do Intervalo do Parcelamento possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ac = new Array("fimIntervaloParcelamento", "Período Final do Intervalo do Parcelamento possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	 this.ae = new Array("cpfClienteParcelamentoDigitado", "CPF possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	
} 

function required () { 
	this.aa = new Array("matriculaImovel", "Informe Matrícula do Imóvel.", new Function ("varName", " return this[varName];"));
	this.bb = new Array("idClienteParcelamento", "Informe Cliente Responsável pelo Parcelamento.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("dataParcelamento", "Informe Data do Parcelamento.", new Function ("varName", " return this[varName];"));
	this.ac = new Array("resolucaoDiretoria", "Informe RD do Parcelamento.", new Function ("varName", " return this[varName];"));
	
	//this.ad = new Array("inicioIntervaloParcelamento", "Informe Intervalo do Parcelamento Inicio.", new Function ("varName", " return this[varName];"));
	//this.ae = new Array("fimIntervaloParcelamento", "Informe Intervalo do Parcelamento Fim.", new Function ("varName", " return this[varName];"));
	
	this.af = new Array("indicadorContasRevisao", "Informe Considerar Contas em Revisão (Sim ou Não).", new Function ("varName", " return this[varName];"));
	this.ag = new Array("indicadorGuiasPagamento", "Informe Considerar Guias de Pagamento (Sim ou Não).", new Function ("varName", " return this[varName];"));
	this.ah = new Array("indicadorAcrescimosImpotualidade", "Informe Considerar Acréscimos por Impontualidade (Sim ou Não).", new Function ("varName", " return this[varName];"));
	this.ai = new Array("indicadorDebitosACobrar", "Informe Considerar Débitos a Cobrar (Sim ou Não).", new Function ("varName", " return this[varName];"));
	this.aj = new Array("indicadorCreditoARealizar", "Informe Considerar Créditos a Realizar (Sim ou Não).", new Function ("varName", " return this[varName];"));
	this.al = new Array("cpfClienteParcelamento", "Informe CPF.", new Function ("varName", " return this[varName];"));
} 

function IntegerValidations () { 
	this.aa = new Array("matriculaImovel", "Matrícula do Imóvel deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.ac = new Array("cpfClienteParcelamentoDigitado", "CPF deve somente conter dígitos.", new Function ("varName", " return this[varName];"));
} 

function DateValidations () { 
	this.aa = new Array("dataParcelamento", "Data do Parcelamnento inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
} 


function habilitarPesquisaCliente(form) {
	abrirPopup('exibirPesquisarClienteAction.do?indicadorUsoTodos=1', 400, 800);
}

function limparCliente() {
	var form = document.forms[0];
	form.idClienteParcelamento.value = "";
	form.nomeClienteParcelamento.value = "";
	form.foneClienteParcelamento.value = "";
	form.cpfClienteParcelamento.value = "";
	form.cpfClienteParcelamento.style.display = "none";
	form.cpfClienteParcelamentoDigitado.value = "";
	form.cpfClienteParcelamentoDigitado.style.display = "";
	form.cpfInexistente.value = "";
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];

    if(tipoConsulta == 'imovel'){
      limparForm();      
      form.matriculaImovel.value = codigoRegistro;
      form.action = 'exibirEfetuarParcelamentoDebitosAction.do'
      form.submit();
    }
    
    if (tipoConsulta == 'cliente') {
		form.idClienteParcelamento.value = codigoRegistro;
		form.nomeClienteParcelamento.value = descricaoRegistro;
        form.action = 'efetuarParcelamentoDebitosWizardAction.do?action=exibirEfetuarParcelamentoDebitosProcesso1Action'
	    form.submit();
	}
}

function verificaLimparForm(){
	var form = document.forms[0];
	var matriculaImovel = form.matriculaImovel.value;
	
	if (form.inscricaoImovel.value != null && form.inscricaoImovel.value != ""){
		window.location.href = 'exibirEfetuarParcelamentoDebitosAction.do?guardarMatriculaImovel='+ matriculaImovel;
	}
}
function limparForm(){
	window.location.href = 'exibirEfetuarParcelamentoDebitosAction.do?menu=sim';
}

function verificaExistenciaMatricula(){
	var form = document.forms[0];
	if( form.matriculaImovel.value == "" ){
	 	var ObjNomeCliente = returnObject(form,"nomeCliente");
	 	var ObjSituacaoAgua = returnObject(form,"situacaoAgua");
	 	var ObjSituacaoEsgoto = returnObject(form,"situacaoEsgoto");
	 	var ObjCpfCnpj = returnObject(form,"cpfCnpj");
	 	var ObjInicioIntervaloParcelamento = returnObject(form,"inicioIntervaloParcelamento");
	 	var ObjFimIntervaloParcelamento = returnObject(form,"fimIntervaloParcelamento");
	 	var ObjResolucaoDiretoria = returnObject(form,"resolucaoDiretoria");
	 	
		ObjNomeCliente.value = "";
		ObjSituacaoAgua.value = "";
		ObjSituacaoEsgoto.value = "";
		ObjCpfCnpj.value = "";
	 	ObjInicioIntervaloParcelamento.value = "";
	 	ObjFimIntervaloParcelamento.value = "";
	 	ObjResolucaoDiretoria.value = "";
	 	
		if(!form.indicadorCreditoARealizar[0].checked && !form.indicadorCreditoARealizar[1].checked ){
			form.indicadorCreditoARealizar[1].checked = false;
		}
		if(!form.indicadorDebitosACobrar[0].checked && !form.indicadorDebitosACobrar[1].checked ){
			form.indicadorDebitosACobrar[1].checked = false;
		}	
		// Se existir indicador de Restabelecimento no caso de imovel surprimido
		//if( form.indicadorRestabelecimento ){
			//if(!form.indicadorRestabelecimento[0].checked && !form.indicadorRestabelecimento[1].checked  ){
				//form.indicadorRestabelecimento[1].checked = false;
			//}	 
		//}
		if(!form.indicadorContasRevisao[0].checked && !form.indicadorContasRevisao[1].checked ){
			form.indicadorContasRevisao[1].checked = false;
		}	
		if(!form.indicadorGuiasPagamento[0].checked && !form.indicadorGuiasPagamento[1].checked ){
			form.indicadorGuiasPagamento[1].checked = false;
		}	
		if(!form.indicadorAcrescimosImpotualidade[0].checked && !form.indicadorAcrescimosImpotualidade[1].checked ){
			form.indicadorAcrescimosImpotualidade[1].checked = false;
		}	

	    document.getElementById("endereco").innerHTML = '&nbsp;';
	    document.getElementById("numeroParcelamento").innerHTML = '&nbsp;';
	    document.getElementById("numeroReparcelamento").innerHTML = '&nbsp;';
	    document.getElementById("numeroReparcelamentoConsecutivos").innerHTML = '&nbsp;';
	    document.getElementById("valorTotalContaValores").innerHTML = '&nbsp;';
	    document.getElementById("valorGuiasPagamento").innerHTML = '&nbsp;';
	    document.getElementById("valorAcrescimosImpontualidade").innerHTML = '&nbsp;';
	    document.getElementById("valorDebitoACobrarServico").innerHTML = '&nbsp;';
	    document.getElementById("valorDebitoACobrarParcelamento").innerHTML = '&nbsp;';
	    document.getElementById("valorCreditoARealizar").innerHTML = '&nbsp;';
	    document.getElementById("valorDebitoTotalAtualizado").innerHTML = '&nbsp;';

	}
	if(!form.indicadorCreditoARealizar[0].checked && !form.indicadorCreditoARealizar[1].checked){
		form.indicadorCreditoARealizar[0].checked = true;
	}	
	if(!form.indicadorDebitosACobrar[0].checked && !form.indicadorDebitosACobrar[1].checked){
		form.indicadorDebitosACobrar[0].checked = true;
	}	
	// Se existir indicador de Restabelecimento no caso de imovel surprimido
//	if( form.indicadorRestabelecimento ){
		//if(!form.indicadorRestabelecimento[0].checked && !form.indicadorRestabelecimento[1].checked ){
			//form.indicadorRestabelecimento[0].checked = true;
		//}	
//	}
	if(!form.indicadorContasRevisao[0].checked && !form.indicadorContasRevisao[1].checked ){
		form.indicadorContasRevisao[0].checked = true;
	}	
	if(!form.indicadorGuiasPagamento[0].checked && !form.indicadorGuiasPagamento[1].checked ){
		form.indicadorGuiasPagamento[0].checked = true;
	}	
	if(!form.indicadorAcrescimosImpotualidade[0].checked && !form.indicadorAcrescimosImpotualidade[1].checked ){
		form.indicadorAcrescimosImpotualidade[0].checked = true;
	}	
}

function validaRadioButton(nomeCampo,mensagem){
	var alerta = "";
	if(!nomeCampo[0].checked && !nomeCampo[1].checked){
		alerta = mensagem +" deve ser selecionado.";
	}
	return alerta;
}
function validaTodosRadioButton(){
	var form = document.forms[0];
	var mensagem = "";
	if(form.indicadorRestabelecimento){
		if(validaRadioButton(form.indicadorRestabelecimento,"'Com restabelecimento?'") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorRestabelecimento,"'Com restabelecimento?'")+"\n";
		}
	}
	if(validaRadioButton(form.indicadorContasRevisao,"'Considerar contas em revisão?'") != ""){
		mensagem = mensagem + validaRadioButton(form.indicadorContasRevisao,"'Considerar contas em revisão?'")+"\n";
	}
	if(validaRadioButton(form.indicadorGuiasPagamento,"'Considerar guias de pagamento?'") != ""){
		mensagem = mensagem + validaRadioButton(form.indicadorGuiasPagamento,"'Considerar guias de pagamento?'")+"\n";
	}
	if(validaRadioButton(form.indicadorAcrescimosImpotualidade,"'Considerar Acréscimos por Impontualidade?'") != ""){
		mensagem = mensagem + validaRadioButton(form.indicadorAcrescimosImpotualidade,"'Considerar Acréscimos por Impontualidade?'")+"\n";
	}
	if(validaRadioButton(form.indicadorCreditoARealizar,"'Considerar créditos a realizar?'") != ""){
		mensagem = mensagem + validaRadioButton(form.indicadorCreditoARealizar,"'Considerar créditos a realizar?'")+"\n";
	}
	if(validaRadioButton(form.indicadorDebitosACobrar,"'Considerar débitos a cobrar?'") != ""){
		mensagem = mensagem + validaRadioButton(form.indicadorDebitosACobrar,"'Considerar débitos a cobrar?'")+"\n";
	}
 
	if(mensagem != ""){
		alert(mensagem);
		return false;
	}else{
		return true;
	}
}

function habilitaMatricula() {
	var form = document.forms[0];
	
	if (form.matriculaImovel.value != null && form.inscricaoImovel.value != null && form.inscricaoImovel.value != "" &&
	form.inscricaoImovel.value != "MATRÍCULA INEXISTENTE"){
	
		form.matriculaImovel.disabled = true;
	} else {
		form.matriculaImovel.disabled = false;
	}
}

function pesquisarImovel() {
	var form = document.forms[0];
 	
 	if (form.matriculaImovel.disabled ) {
 		alert("Para realizar uma pesquisa de imóvel é necessário apagar o imóvel atual.")
	} else {
		abrirPopup('exibirPesquisarImovelAction.do', 400, 800);
	}
}



-->
</script>
</head>

<body leftmargin="5" topmargin="5" onload="habilitaMatricula();verificaExistenciaMatricula();javascript:setarFoco('${requestScope.nomeCampo}');">

<div id="formDiv">
<html:form action="/efetuarParcelamentoDebitosWizardAction"
	name="EfetuarParcelamentoDebitosActionForm"
	type="gcom.gui.cobranca.EfetuarParcelamentoDebitosActionForm"
	method="post">

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_tela_espera.jsp?numeroPagina=1"/>	

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="130" valign="top" class="leftcoltext">
			<input type="hidden" name="numeroPagina" value="1"/>
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
		<td width="625" valign="top" class="centercoltext">
	        <table height="100%">
		        <tr><td></td></tr>
	      	</table>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	        	<tr>
		          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
		          <td class="parabg">Efetuar Parcelamento de Débitos</td>
		          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
	    	    </tr>
		    </table>
			<!-- Início do Corpo - Roberta Costa -->
			<p>&nbsp;</p>
					
            <table width="100%" border="0">
				<tr> 
					<td colspan="2">Para efetuar o parcelamento de d&eacute;bitos informe o im&oacute;vel:</td>		
				</tr>
				<tr>
				<td width="45%" >&nbsp;</td>
		        <logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cobrancaParcelamentoEfetuar-AbaImovel', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
				</logic:present>
				<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
				</logic:notPresent>
				</tr>
				<tr> 
					<td colspan="2">
			            <table border="0">
							<tr> 
								<td width="30%">
									<strong>Matr&iacute;cula do Im&oacute;vel:<font color="#FF0000">*</font></strong>
								</td>
								<td>
									<html:hidden property="codigoImovelAntes" />
									<html:text property="matriculaImovel" maxlength="9" size="9"
										onkeypress="validaEnterComMensagem(event, 'exibirEfetuarParcelamentoDebitosAction.do?menu=sim', 'matriculaImovel','Matrícula do Imóvel');return isCampoNumerico(event);" 
										onkeyup="verificaLimparForm()"
										/>
									<a href="javascript:pesquisarImovel();">
										<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>	
										<logic:present name="idImovelNaoEncontrado">
						                  <logic:equal name="idImovelNaoEncontrado" value="exception">
							                    <html:text property="inscricaoImovel" size="30"
								                   maxlength="30" readonly="true"
								                   style="background-color:#EFEFEF; border:0; color: #ff0000" />
						                  </logic:equal>
						                  <logic:notEqual name="idImovelNaoEncontrado" value="exception">
							                   <html:text property="inscricaoImovel" size="30"
								                maxlength="30" readonly="true"
								                style="background-color:#EFEFEF; border:0; color: #000000" />
						                  </logic:notEqual>
					                   </logic:present> 
					                   <logic:notPresent name="idImovelNaoEncontrado">
						                  <logic:empty name="EfetuarParcelamentoDebitosActionForm" property="inscricaoImovel">
							                    <html:text property="inscricaoImovel" size="30" value = ""
								                  maxlength="30" readonly="true"
								                  style="background-color:#EFEFEF; border:0; color: #ff0000" />
						                  </logic:empty>
						               <logic:notEmpty name="EfetuarParcelamentoDebitosActionForm" property="inscricaoImovel">
							                   <html:text property="inscricaoImovel" size="30"
								                  maxlength="30" readonly="true"
								                  style="background-color:#EFEFEF; border:0; color: #000000" />
						               </logic:notEmpty>
						               </logic:notPresent>
										
									<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" onclick="limparForm();" style="cursor: hand;" />
								</td>
							</tr>
							
	
							<tr>
								<td><strong>Cliente Responsável pelo Parcelamento:<font color="#FF0000">*</font></strong></td>
								<td><html:text property="idClienteParcelamento" size="9" maxlength="9"
									onkeyup="validaEnterComMensagem(event, 
									'efetuarParcelamentoDebitosWizardAction.do?action=exibirEfetuarParcelamentoDebitosProcesso1Action', 'idClienteParcelamento','Cliente');"
									onkeypress="document.forms[0].nomeClienteParcelamento.value='';
									document.forms[0].foneClienteParcelamento.value='';
									document.forms[0].cpfClienteParcelamento.value='';
									document.forms[0].cpfClienteParcelamentoDigitado.value='';return isCampoNumerico(event);" />
									<a href="javascript:habilitarPesquisaCliente(document.forms[0]);" >
									<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Cliente"/></a>		
									
									<logic:present name="clienteInexistente" scope="request">
										<html:text property="nomeClienteParcelamento" size="35" maxlength="35"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" />
											
									</logic:present> 
									<logic:notPresent name="clienteInexistente"
										scope="request">
										<html:text property="nomeClienteParcelamento" size="35" maxlength="35"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:notPresent>
									<a href="javascript:limparCliente();"><img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" /></a>
								</td>
							</tr>
							<tr>
							
								<td><strong>Telefone:</strong></td>
			
								<td>
									<logic:present name="clienteInexistente" scope="request">
										<html:text property="foneClienteParcelamento" size="20" maxlength="20"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" />
									</logic:present> 
									
									<logic:notPresent name="clienteInexistente"	scope="request">
										<html:text property="foneClienteParcelamento" size="20" maxlength="20"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />	
									</logic:notPresent>
								</td>
			
							</tr>						
						
							<tr>		
								<td><strong>CPF:<font color="#FF0000">*</font></strong></td>
			
								<td>
									<logic:present name="clienteInexistente" scope="request">
										<html:text property="cpfClienteParcelamentoDigitado" size="11" maxlength="11"
										onblur="document.forms[0].cpfClienteParcelamento.value=document.forms[0].cpfClienteParcelamentoDigitado.value;"
											onkeyup="validaEnterString(event, 'efetuarParcelamentoDebitosWizardAction.do?action=exibirEfetuarParcelamentoDebitosProcesso1Action', 'cpfClienteParcelamentoDigitado','Cliente');"/>
										<html:text property="cpfClienteParcelamento" size="14" maxlength="14" 
											readonly="true" style="background-color:#EFEFEF; border:0;display:none;" />
											<input type="text" name="cpfInexistente" value="" style="background-color:#cbe5fe; border:0; color: #FF0000" >
									</logic:present> 
									
									<logic:notPresent name="clienteInexistente"	scope="request">
										<logic:present name="cpfCliente" scope="request">
											<html:text property="cpfClienteParcelamento" size="14" maxlength="14" 
											readonly="true" style="background-color:#EFEFEF; border:0;" />
											
											<html:text property="cpfClienteParcelamentoDigitado" size="11" maxlength="11" style="display:none;"
											onblur="document.forms[0].cpfClienteParcelamento.value=document.forms[0].cpfClienteParcelamentoDigitado.value;"
											onkeyup="validaEnterString(event, 'efetuarParcelamentoDebitosWizardAction.do?action=exibirEfetuarParcelamentoDebitosProcesso1Action', 'cpfClienteParcelamentoDigitado','Cliente');"/>
											<input type="text" name="cpfInexistente" value="" style="background-color:#cbe5fe; border:0; color: #FF0000" >
										</logic:present>
												
										<logic:notPresent name="cpfCliente" scope="request">
											<logic:present name="cpfInexistente" scope="request">
												<html:text property="cpfClienteParcelamento" size="14" maxlength="14" 
												readonly="true"	style="background-color:#EFEFEF; border:0;display:none;" />
												<html:text property="cpfClienteParcelamentoDigitado" size="11" maxlength="11" style="display:'';"
												onblur="document.forms[0].cpfClienteParcelamento.value=document.forms[0].cpfClienteParcelamentoDigitado.value;"
												onkeyup="validaEnterString(event, 'efetuarParcelamentoDebitosWizardAction.do?action=exibirEfetuarParcelamentoDebitosProcesso1Action', 'cpfClienteParcelamentoDigitado','Cliente');"/>											
												<input type="text" name="cpfInexistente" value="CPF INEXISTENTE" style="background-color:#cbe5fe; border:0; color: #FF0000" >
											</logic:present>
											
											<logic:notPresent name="cpfInexistente" scope="request">
												<html:text property="cpfClienteParcelamento" size="14" maxlength="14" 
												readonly="true"	style="background-color:#EFEFEF; border:0;display:none;" />
												<html:text property="cpfClienteParcelamentoDigitado" size="11" maxlength="11" style="display:'';"
												onblur="document.forms[0].cpfClienteParcelamento.value=document.forms[0].cpfClienteParcelamentoDigitado.value;"
												onkeyup="validaEnterString(event, 'efetuarParcelamentoDebitosWizardAction.do?action=exibirEfetuarParcelamentoDebitosProcesso1Action', 'cpfClienteParcelamentoDigitado','Cliente');"/>											
												<input type="text" name="cpfInexistente" value="" style="background-color:#cbe5fe; border:0; color: #FF0000" >
											</logic:notPresent>
										</logic:notPresent>
									</logic:notPresent>
								</td>
							</tr>
							
			            </table>
					</td>
				</tr>
				<tr> 
					<td colspan="2">
			            <table border="0" bgcolor="#99CCFF" width="100%">
							<tr bgcolor="#99CCFF">
								<td colspan="2" align="center"><strong>Dados do Imóvel</strong></td>
							</tr>
							<tr>
								<td>
									<table border="0" bgcolor="#cbe5fe" width="100%">
										<tr> 
											<td><strong>Cliente Usu&aacute;rio:</strong></td>
											<td>
												<html:text property="nomeCliente" size="45"
													readonly="true" style="background-color:#EFEFEF; border:0" />
											</td>
										</tr>
										<tr> 
											<td><strong>CPF ou CNPJ:</strong></td>
											<td>
												<html:text property="cpfCnpj" size="45"
													readonly="true" style="background-color:#EFEFEF; border:0" />
												<html:hidden property="perfilImovel"/>														
											</td>
										</tr>
										<tr> 
											<td><strong>Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o de &Aacute;gua:</strong></td>
											<td>
												<html:text property="situacaoAgua" size="45"
													readonly="true" style="background-color:#EFEFEF; border:0" />
												<html:hidden property="situacaoAguaId"/>	
											</td>
										</tr>
										<tr> 
											<td><strong>Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o de Esgoto:</strong></td>
											<td>
												<html:text property="situacaoEsgoto" size="45"
													readonly="true" style="background-color:#EFEFEF; border:0" />
												<html:hidden property="situacaoEsgotoId"/>	
											</td>
										</tr>
										<tr> 
											<td><strong>Perfil do Imóvel:</strong></td>
											<td>
												<html:text property="descricaoPerfilImovel" size="45"
													readonly="true" style="background-color:#EFEFEF; border:0" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2"><br>
						<table width="100%" align="center" bgcolor="#99CCFF" border="0">
							<tr>
								<td align="center">
									<strong>Endere&ccedil;o do Im&oacute;vel</strong>
								</td>
							</tr>
							<tr bgcolor="#cbe5fe">
								<td align="center" bgcolor="#FFFFFF" height="20">
									<span id="endereco">
										<logic:present name="EfetuarParcelamentoDebitosActionForm" property="endereco">
											<bean:write name="EfetuarParcelamentoDebitosActionForm" 
												property="endereco"/>
										</logic:present>
									</span>	
									<logic:notPresent name="EfetuarParcelamentoDebitosActionForm" property="endereco">
									&nbsp;
									</logic:notPresent>													
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2" height="23"><br>
						<font color="#000000">
							<strong>Quantidades de Parcelamentos / Reparcelamentos:</strong>
						</font>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<table width="100%" bgcolor="#99CCFF">
							<tr bgcolor="#90c7fc"> 
								<td align="center" width="30%"><strong> Parcelamentos</strong></td>
								<td align="center" width="30%"><strong>Reparcelamentos</strong></td>
								<td align="center" width="*"><strong>Reparcelamentos Consecutivos </strong></td>
							</tr>
							<tr bgcolor="#cbe5fe""> 
								<td align="center" height="20" bgcolor="#FFFFFF">
									<span id="numeroParcelamento">
									<bean:write name="EfetuarParcelamentoDebitosActionForm" 
										property="numeroParcelamento"/>
									</span>	
								</td>
								<td align="center" bgcolor="#FFFFFF">
									<span id="numeroReparcelamento">
									<bean:write name="EfetuarParcelamentoDebitosActionForm" 
										property="numeroReparcelamento"/>
									</span>	
								</td>
								<td align="center" bgcolor="#FFFFFF">
									<span id="numeroReparcelamentoConsecutivos">
									<bean:write name="EfetuarParcelamentoDebitosActionForm" 
										property="numeroReparcelamentoConsecutivos"/>
									</span>	
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2" height="23"><br>
						<font color="#000000">
							<strong>Valor dos D&eacute;bitos do Im&oacute;vel:</strong>
						</font>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<table width="100%" bgcolor="#99CCFF">
							<tr bgcolor="#90c7fc">  
								<td align="center" width="30%">
									<strong> Contas</strong>
								</td>
								<td align="center" width="30%">
									<strong>Guias de Pagamento</strong>
								</td>
								<td align="center" width="40%">
									<strong>Acr&eacute;scimos Impontualidade</strong>
								</td>
							</tr>
							<tr bgcolor="#cbe5fe"> 
								<td align="right" height="20" bgcolor="#FFFFFF">
									<span id="valorTotalContaValores">
									<bean:write name="EfetuarParcelamentoDebitosActionForm" 
										property="valorTotalContasImovel"/>
									</span>	
								</td>
								<td align="right" bgcolor="#FFFFFF">
									<span id="valorGuiasPagamento">
									<bean:write name="EfetuarParcelamentoDebitosActionForm" 
										property="valorGuiasPagamentoImovel"/>
									</span>	
								</td>
								<td align="right" bgcolor="#FFFFFF">
									<span id="valorAcrescimosImpontualidade">
									<logic:notEqual
										name="EfetuarParcelamentoDebitosActionForm" property="valorAcrescimosImpontualidadeImovel"
										value="0,00">
										<a href="javascript:abrirPopup('exibirValorAtualizacaoConsultarPopupAction.do?
											multa=<bean:write name="EfetuarParcelamentoDebitosActionForm" property="valorMultaImovel" />&
											juros=<bean:write name="EfetuarParcelamentoDebitosActionForm" property="valorJurosMoraImovel" />&
											atualizacao=<bean:write name="EfetuarParcelamentoDebitosActionForm" property="valorAtualizacaoMonetariaImovel" />', 220, 605);">
										<bean:write name="EfetuarParcelamentoDebitosActionForm"
											property="valorAcrescimosImpontualidadeImovel" formatKey="money.format" />
										</a>
									</logic:notEqual> 
									<logic:equal name="EfetuarParcelamentoDebitosActionForm"
										property="valorAcrescimosImpontualidadeImovel" value="0,00">
										<bean:write name="EfetuarParcelamentoDebitosActionForm"
											property="valorAcrescimosImpontualidadeImovel" formatKey="money.format" />
									</logic:equal>
									</span>	
								</td>
							</tr>
						</table>
						<br>	
						<table width="100%" bgcolor="#99CCFF">
							<tr bgcolor="#90c7fc">
								<td align="center" colspan="2">
									<strong>D&eacute;bitos a Cobrar</strong>
								</td>
								<td align="center" rowspan="2">
									<strong>Cr&eacute;ditos a Realizar</strong>
								</td>
								<td align="center" rowspan="2">
									<strong>D&eacute;bito Total Atualizado</strong>
								</td>
							</tr>
							<tr bgcolor="#90c7fc"> 
								<td align="center" bgcolor="cbe5fe" width="20%">
									<strong>Servi&ccedil;o</strong>
								</td>
								<td align="center" bgcolor="cbe5fe">
									<strong>Parcelamento</strong>
								</td>
							</tr>
							<tr bgcolor="cbe5fe">
								<td align="right" height="20" bgcolor="#FFFFFF">
									<span id="valorDebitoACobrarServico">
										<logic:notEqual name="EfetuarParcelamentoDebitosActionForm" 
											property="valorDebitoACobrarServicoImovel" value="0,00">
											<a href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=
												<bean:define name="EfetuarParcelamentoDebitosActionForm" property="matriculaImovel" id="imovel" />
													<bean:write name="EfetuarParcelamentoDebitosActionForm" property="matriculaImovel" />&parcelamentoID=', 600, 800);">
												<bean:write name="EfetuarParcelamentoDebitosActionForm" property="valorDebitoACobrarServicoImovel"  formatKey="money.format"/>
											</a>
										</logic:notEqual>
										<logic:equal name="EfetuarParcelamentoDebitosActionForm" 
											property="valorDebitoACobrarServicoImovel" value="0,00">
											<bean:write	name="EfetuarParcelamentoDebitosActionForm" property="valorDebitoACobrarServicoImovel" formatKey="money.format" />
										</logic:equal>
									</span>	
									<html:hidden property="valorDebitoACobrarServicoCurtoPrazo"/>
									<html:hidden property="valorDebitoACobrarServicoLongoPrazo"/>
								</td>
								<td align="right" bgcolor="#FFFFFF">
									<span id="valorDebitoACobrarParcelamento">
										<logic:notEqual name="EfetuarParcelamentoDebitosActionForm" 
											property="valorDebitoACobrarParcelamentoImovel" value="0,00">
											<a href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=
												<bean:define name="EfetuarParcelamentoDebitosActionForm" property="matriculaImovel" id="imovel" />
													<bean:write name="EfetuarParcelamentoDebitosActionForm" property="matriculaImovel" />&parcelamentoID=', 600, 800);">
												<bean:write name="EfetuarParcelamentoDebitosActionForm" property="valorDebitoACobrarParcelamentoImovel"  formatKey="money.format"/>
											</a>
										</logic:notEqual>
										<logic:equal name="EfetuarParcelamentoDebitosActionForm" 
											property="valorDebitoACobrarParcelamentoImovel" value="0,00">
											<bean:write	name="EfetuarParcelamentoDebitosActionForm" property="valorDebitoACobrarParcelamentoImovel" formatKey="money.format" />
										</logic:equal>
									</span>	
									<html:hidden property="valorDebitoACobrarParcelamentoCurtoPrazo"/>
									<html:hidden property="valorDebitoACobrarParcelamentoLongoPrazo"/>
								</td>
								<td align="right" bgcolor="#FFFFFF">
									<span id="valorCreditoARealizar">
										<logic:notEqual name="EfetuarParcelamentoDebitosActionForm" 
											property="valorCreditoARealizarImovel" value="0,00">
											<a href="javascript:abrirPopup('exibirConsultarCreditoARealizarAction.do?imovelID=<bean:define name="EfetuarParcelamentoDebitosActionForm" property="matriculaImovel" id="imovel" />
												<bean:write name="EfetuarParcelamentoDebitosActionForm" property="matriculaImovel" />&parcelamentoID=', 600, 800);">
													<bean:write name="EfetuarParcelamentoDebitosActionForm" property="valorCreditoARealizarImovel" formatKey="money.format"/>
											</a>
										</logic:notEqual>
										<logic:equal name="EfetuarParcelamentoDebitosActionForm" 
											property="valorCreditoARealizarImovel" value="0,00">
											<bean:write	name="EfetuarParcelamentoDebitosActionForm" property="valorCreditoARealizarImovel" formatKey="money.format" />
										</logic:equal>
									</span>	
								</td>
								<td align="right" bgcolor="#FFFFFF">
									<span id="valorDebitoTotalAtualizado">
									<bean:write name="EfetuarParcelamentoDebitosActionForm" 
										property="valorDebitoTotalAtualizadoImovel"/>
									</span>	
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr> 
					<td width="45%"><br>
						<strong>Data do Parcelamento:<font color="#FF0000">*</font></strong>
					</td>
					<td><br>
						<html:text property="dataParcelamento" size="10" maxlength="10" tabindex="2" onkeyup="mascaraData(document.forms[0].dataParcelamento,this)"/>
						<a href="javascript:abrirCalendario('EfetuarParcelamentoDebitosActionForm', 'dataParcelamento')">
	             			<img border="0" align="top" src="<bean:message key='caminho.imagens'/>calendario.gif" width="20" border="0" align="middle" alt="Exibir Calendário"/></a> dd/mm/aaaa
					</td>
				</tr>
				<tr> 
					<td><strong>RD do Parcelamento:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:select property="resolucaoDiretoria">
							<html:option value="">&nbsp;</html:option>
							<html:options collection="colecaoResolucaoDiretoria" labelProperty="numeroResolucaoDiretoria" property="id" />
						</html:select>
					</td>
				</tr>
				<tr> 
					<td><strong>Intervalo do Parcelamento:<font color="#FF0000">*</font></strong></td>
					<td>
					
					<logic:notPresent name="bloqueiaIntervaloParcelamento" scope="session">
						<html:text property="inicioIntervaloParcelamento" onkeyup="mascaraAnoMes(document.forms[0].inicioIntervaloParcelamento,this)" maxlength="7" size="7"/>
					
					
						<%--<html:text property="inicioIntervaloParcelamento" size="7"
							readonly="true" style="background-color:#EFEFEF; border:0" /> --%>
						a 
						<logic:empty name="EfetuarParcelamentoDebitosActionForm" property="inicioIntervaloParcelamento">
						  <html:text property="fimIntervaloParcelamento" onkeyup="mascaraAnoMes(document.forms[0].fimIntervaloParcelamento,this)" maxlength="7" size="7" readonly="true"/>
						</logic:empty>
						<logic:notEmpty name="EfetuarParcelamentoDebitosActionForm" property="inicioIntervaloParcelamento">
						  <html:text property="fimIntervaloParcelamento" onkeyup="mascaraAnoMes(document.forms[0].fimIntervaloParcelamento,this)" maxlength="7" size="7"/>
						</logic:notEmpty>
					</logic:notPresent>

					<logic:present name="bloqueiaIntervaloParcelamento" scope="session">
						<logic:equal name="bloqueiaIntervaloParcelamento" scope="session" value="nao">
							<html:text property="inicioIntervaloParcelamento" onkeyup="mascaraAnoMes(document.forms[0].inicioIntervaloParcelamento,this)" maxlength="7" size="7"/>
							a 
							<logic:empty name="EfetuarParcelamentoDebitosActionForm" property="inicioIntervaloParcelamento">
							  <html:text property="fimIntervaloParcelamento" onkeyup="mascaraAnoMes(document.forms[0].fimIntervaloParcelamento,this)" maxlength="7" size="7" readonly="true"/>
							</logic:empty>
							<logic:notEmpty name="EfetuarParcelamentoDebitosActionForm" property="inicioIntervaloParcelamento">
							  <html:text property="fimIntervaloParcelamento" onkeyup="mascaraAnoMes(document.forms[0].fimIntervaloParcelamento,this)" maxlength="7" size="7"/>
							</logic:notEmpty>
						</logic:equal>	
						
						<logic:equal name="bloqueiaIntervaloParcelamento" scope="session" value="sim">
							<html:text property="inicioIntervaloParcelamento" onkeyup="mascaraAnoMes(document.forms[0].inicioIntervaloParcelamento,this)" maxlength="7" size="7" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
							a 
							 <html:text property="fimIntervaloParcelamento" onkeyup="mascaraAnoMes(document.forms[0].fimIntervaloParcelamento,this)" maxlength="7" size="7" readonly="true"  style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>	
						
						
					</logic:present>
						
						
						
					</td>
				</tr>
				<logic:equal name="EfetuarParcelamentoDebitosActionForm" property="situacaoAguaId" value="<%=LigacaoAguaSituacao.SUPRIMIDO.toString()%>">
				<tr> 
					<td><strong>Com Restabelecimento?<font color="#FF0000">*</font></strong></td>
					<td>
						<strong>
							<html:radio property="indicadorRestabelecimento" value="1"/>Sim
							<html:radio property="indicadorRestabelecimento" value="2"/>N&atilde;o
						</strong>
					</td>
				</tr>
				</logic:equal>
				<logic:equal name="EfetuarParcelamentoDebitosActionForm" property="situacaoAguaId" value="<%=LigacaoAguaSituacao.SUPR_PARC.toString()%>">
				<tr> 
					<td><strong>Com Restabelecimento?<font color="#FF0000">*</font></strong></td>
					<td>
						<strong>
							<html:radio property="indicadorRestabelecimento" value="1"/>Sim
							<html:radio property="indicadorRestabelecimento" value="2"/>N&atilde;o
						</strong>
					</td>
				</tr>
				</logic:equal>
				<logic:equal name="EfetuarParcelamentoDebitosActionForm" property="situacaoAguaId" value="<%=LigacaoAguaSituacao.SUPR_PARC_PEDIDO.toString()%>">
				<tr> 
					<td><strong>Com Restabelecimento?<font color="#FF0000">*</font></strong></td>
					<td>
						<strong>
							<html:radio property="indicadorRestabelecimento" value="1"/>Sim
							<html:radio property="indicadorRestabelecimento" value="2"/>N&atilde;o
						</strong>
					</td>
				</tr>
				</logic:equal>
				<tr> 
					<td><strong>Considerar Contas em Revis&atilde;o?<font color="#FF0000">*</font></strong></td>
					<td>
						<strong>
							<html:radio property="indicadorContasRevisao" value="1"/>Sim
							<html:radio property="indicadorContasRevisao" value="2"/>N&atilde;o
					 	</strong>
					 </td>
				</tr>
				<tr> 
					<td><strong>Considerar Guias de Pagamento?<font color="#FF0000">*</font></strong></td>
					<td>
						<strong>
							<html:radio property="indicadorGuiasPagamento" value="1"/>Sim
							<html:radio property="indicadorGuiasPagamento" value="2"/>N&atilde;o
						</strong>
					</td>
				</tr>
				<tr> 
					<td><strong>Considerar Acr&eacute;scimos por Impontualidade?<font color="#FF0000">*</font></strong></td>
					<td>
						<strong>
							<html:radio property="indicadorAcrescimosImpotualidade" value="1"/>Sim
							<logic:equal name="temPermissaoAcrescimoImpontualidade" value="true">
								<html:radio property="indicadorAcrescimosImpotualidade" value="2"/>N&atilde;o
							</logic:equal>
							<logic:notEqual name="temPermissaoAcrescimoImpontualidade" value="true">
								<html:radio property="indicadorAcrescimosImpotualidade" value="2" disabled="true"/>N&atilde;o
							</logic:notEqual>
						</strong>
					</td>
				</tr>
				<tr> 
					<td><strong>Considerar D&eacute;bitos a Cobrar?<font color="#FF0000">*</font></strong></td>
					<td>
						<strong>
							<html:radio property="indicadorDebitosACobrar" value="1"/>Sim
							<logic:equal name="temPermissaoDebitoACobrar" value="true">
								<html:radio property="indicadorDebitosACobrar" value="2"/>N&atilde;o
							</logic:equal>
							<logic:notEqual name="temPermissaoDebitoACobrar" value="true">
								<html:radio property="indicadorDebitosACobrar" value="2" disabled="true"/>N&atilde;o
							</logic:notEqual>
						</strong>
					</td>
				</tr>
				<tr> 
					<td><strong>Considerar Cr&eacute;ditos a Realizar?<font color="#FF0000">*</font></strong></td>
					<td>
						<strong>
							<html:radio property="indicadorCreditoARealizar" value="1"/>Sim
							<html:radio property="indicadorCreditoARealizar" value="2"/>N&atilde;o
						 </strong>
					</td>
				</tr>
				<logic:present name="empresaDividaAtiva" scope="session">
					<tr> 
						<td><strong>Dívida Ativa?<font color="#FF0000">*</font></strong></td>
						<td>
							<strong>
								<html:radio property="indicadorDividaAtiva" value="1"/>Sim
								<html:radio property="indicadorDividaAtiva" value="2"/>N&atilde;o
							 </strong>
						</td>
					</tr>
				</logic:present>
				<logic:notPresent name="empresaDividaAtiva" scope="session">
					<html:hidden property="indicadorDividaAtiva" value="3"/>
				</logic:notPresent>
				<tr> 
					<td>&nbsp;</td>
					<td><font color="#FF0000">*</font> Campo Obrigat&oacute;rio</td>
				</tr>
				<tr>
					<td colspan="2">
						<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar_tela_espera.jsp?numeroPagina=1"/>
					</td>
				</tr>			
				<!-- Fim do Corpo - Roberta Costa  11/01/2006  -->
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/efetuarParcelamentoDebitosWizardAction.do?concluir=true&action=processarProcesso1Action'); }
</script>

</body>
</html:html>