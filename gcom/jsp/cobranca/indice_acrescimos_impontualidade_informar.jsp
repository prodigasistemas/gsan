<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

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
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="IndiceAcrescimosImpontualidadeForm" dynamicJavascript="false" />

<script language="JavaScript">

 var bCancel = false;

    function validateIndiceAcrescimosImpontualidadeForm(form) {
        if (bCancel)
      return true;
        else
      return validateRequired(form);  
   }

    function required () { 
	this.aa = new Array("mesAnoReferencia", "Informe Mês/Ano Referência.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("percentualMulta", "Informe Percentual Multa.", new Function ("varName", " return this[varName];"));
	this.ac = new Array("percentualJurosMora", "Informe Percentual Juros de Mora.", new Function ("varName", " return this[varName];"));
	this.af = new Array("fatorCorrecao", "Informe Fator de Correção Calculado.", new Function ("varName", " return this[varName];"));
	this.ag = new Array("percentualLimiteJuros", "Informe Percentual Limite Juros.", new Function ("varName", " return this[varName];"));
	this.ah = new Array("percentualLimiteMulta", "Informe Percentual Limite Multa.", new Function ("varName", " return this[varName];"));

	} 
    
function validarForm(form){

if(validateIndiceAcrescimosImpontualidadeForm(form)){
    submeterFormPadrao(form);
}
}


function desfazer(){
 var form = document.forms[0];
 
 form.mesAnoReferencia.value = '';
 form.percentualMulta.value = '';
 form.percentualJurosMora.value = '';
 form.fatorCorrecao.value = '';
 form.percentualAtualizacaoMonetaria.value = '';
}


function habilitaCampos(){
 var form = document.forms[0];
 if(form.camposDesabilitados.value != ""){
   form.percentualMulta.readOnly = true;
   form.percentualJurosMora.readOnly = true;
   form.fatorCorrecao.readOnly = true;
   form.percentualLimiteJuros.readOnly = true;
   form.percentualLimiteMulta.readOnly = true;
   form.botaoCalcular.disabled = true;
   form.indicadorJurosMensal[0].disabled = true;
   form.indicadorJurosMensal[1].disabled = true;
   form.indicadorMultaMensal[0].disabled = true;
   form.indicadorMultaMensal[1].disabled = true;
 }else{
   form.percentualMulta.readOnly = false;
   form.percentualJurosMora.readOnly = false;
   form.fatorCorrecao.readOnly = false;
   form.percentualLimiteJuros.readOnly = false;
   form.percentualLimiteMulta.readOnly = false;
   form.botaoCalcular.readOnly = false;
   form.indicadorJurosMensal[0].disabled = false;
   form.indicadorJurosMensal[1].disabled = false;
   form.indicadorMultaMensal[0].disabled = false;
   form.indicadorMultaMensal[1].disabled = false;
 }
}

function teclaEnter(tecla){
var form = document.forms[0];
if (document.all) {
		var codigo = event.keyCode;
    }
	else {
       var codigo = tecla.which;
    }

	if (codigo == 13) {
	 form.action = 'exibirInformarIndiceAcrescimosImpontualidadeAction.do'
	 form.submit();
	}
}

function loadPagina(valor){
var form = document.forms[0];
if(form.mesAnoReferencia.value != ""){
if(valor == 'nao'){
 if(!verificaAnoMes(form.mesAnoReferencia)){
  return false;
 }
}
form.action = 'exibirInformarIndiceAcrescimosImpontualidadeAction.do?calcular='+valor;
form.submit();
}

}

function validarCampos(){
var form = document.forms[0];
	if(form.percentualMulta.value == '0,00' || form.percentualMulta.value > '100,00'){
	  alert('Percentual informado invalido');
	  form.percentualMulta.focus();
	  return false;
	}
	
	if(form.percentualJurosMora.value == '0,00' || form.percentualJurosMora.value > '100,00'){
	  alert('Percentual informado invalido');
	  form.percentualJurosMora.focus();
	  return false;
	}
	
	if(form.percentualAtualizacaoMonetaria.value == '0,00' || form.percentualAtualizacaoMonetaria.value > '100,00'){
	  alert('Percentual informado invalido');
	  form.percentualAtualizacaoMonetaria.focus();
	  return false;
	}

}

function desabilitaCalcular(){
 var form = document.forms[0];
 if(form.percentualAtualizacaoMonetaria.value == ''){
  form.botaoCalcular.disabled = true;
 }else{
  form.botaoCalcular.disabled = false;
 }
}


</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');desabilitaCalcular();">

<html:form action="/informarIndiceAcrescimosImpontualidadeAction"
	name="IndiceAcrescimosImpontualidadeForm"
	type="gcom.gui.cobranca.IndiceAcrescimosImpontualidadeForm"
	method="post">

	<html:hidden property="camposDesabilitados" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>

			<td width="115" valign="top" class="leftcoltext">
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
			<td width="600" valign="top" bgcolor="#003399" class="centercoltext">
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
					<td class="parabg">Informar Índice Acréscimos Impontualidade</td>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">
					<p>Para informar os índices, informe os dados abaixo:</p>
					<p>&nbsp;</p>
					</td>
				</tr>
				<tr>
					<td width="25%"><strong>Mês/Ano Referência:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="mesAnoReferencia" size="9"
						onblur="loadPagina('nao');" onkeyup="mascaraAnoMes(this, event);"
						maxlength="7" tabindex="1" /></td>
				</tr>
				<tr>
					<td><strong>Percentual Multa:<font color="#FF0000">*</font></strong></td>
					<td width="25%"><html:text maxlength="8" property="percentualMulta"
						onkeyup="formataValorDecimalQuatroCasas(this, 8)" size="8" tabindex="2" />
					</td>
				</tr>
				<tr>
					<td><strong>Percentual Juros de Mora:<font color="#FF0000">*</font></strong></td>
					<td width="25%"><html:text maxlength="8"
						property="percentualJurosMora"
						onkeyup="formataValorDecimalQuatroCasas(this, 8)" size="8" tabindex="3" />
					</td>
				</tr>
				<tr>
					<td><strong>Percentual Fator Atualização Monetária:<font
						color="#FF0000">*</font></strong></td>
					<td width="25%"><html:text maxlength="6"
						property="percentualAtualizacaoMonetaria"
						onkeyup="formataValorMonetarioNegativo(this, 6);document.forms[0].fatorCorrecao.value='';desabilitaCalcular();"
						size="6" tabindex="4" /> &nbsp;<input name="botaoCalcular"
						type="button" class="bottonRightCol" value="Calcular"
						onclick="loadPagina('sim');"></td>
				</tr>
				<tr>
					<td><strong>Fator de Correção Calculado:</strong></td>
					<td width="25%"><html:text maxlength="6" property="fatorCorrecao"
						size="6" readonly="true" /></td>
				</tr>
				<tr>
					<td><strong>Indicador de Juros Cobrados Mensais:<font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorJurosMensal"
						value="<%=ConstantesSistema.SIM.toString()%>" /> Sim <html:radio
						property="indicadorJurosMensal"
						value="<%=ConstantesSistema.NAO.toString()%>" /> Não</strong></td>
				</tr>
				<tr>
					<td><strong>Indicador de Multa Cobrada Mensal:<font color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorMultaMensal"
						value="<%=ConstantesSistema.SIM.toString()%>" /> Sim <html:radio
						property="indicadorMultaMensal"
						value="<%=ConstantesSistema.NAO.toString()%>" /> Não</strong></td>
				</tr>
				<tr>
					<td><strong>Percentual Limite Juros:<font color="#FF0000">*</font></strong></td>
					<td width="25%"><html:text maxlength="6"
						property="percentualLimiteJuros"
						onkeyup="formataValorMonetario(this, 6)" size="6" /></td>
				</tr>
				<tr>
					<td><strong>Percentual Limite Multa:<font color="#FF0000">*</font></strong></td>
					<td width="25%"><html:text maxlength="6"
						property="percentualLimiteMulta"
						onkeyup="formataValorMonetario(this, 6)" size="6" /></td>
				</tr>
				<tr>
					<td colspan="2">
					<table width="100%" border="0">
						<tr>
							<td valign="top"><input name="button" type="button"
								class="bottonRightCol" value="Desfazer" onclick="window.location.href='/gsan/exibirInformarIndiceAcrescimosImpontualidadeAction.do?menu=sim'">&nbsp;<input
								type="button" name="ButtonCancelar" class="bottonRightCol"
								value="Cancelar"
								onClick="window.location.href='/gsan/telaPrincipal.do'"></td>
							<td valign="top">
							<div align="right"><gsan:controleAcessoBotao name="botaoInformar"
								value="Informar" onclick="validarForm(document.forms[0]);"
								url="informarIndiceAcrescimosImpontualidadeAction.do"
								tabindex="5" /> <%-- <input name="botaoInserir" type="button" class="bottonRightCol" value="Inserir" onclick="validarForm(document.forms[0]);" tabindex="17">--%>
							</div>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>

		</tr>
	</table>



	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
