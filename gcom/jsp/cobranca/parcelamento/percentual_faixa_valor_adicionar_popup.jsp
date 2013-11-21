<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.util.Util" %>
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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirPrestacoesParcelamentoPerfilActionForm" />

<script language="JavaScript">
	function desfazer(){
		form = document.forms[0];
		form.valorMaxPercFaixaValor.value = '';
		form.percentualPercFaixaValor.value = '';
	}
	
	function voltar(){
		form = document.forms[0];
		form.action ="exibirInserirPrestacoesParcelamentoPerfilAction.do";
		submeterFormPadrao(form);
	}

	function validarForm(form){
		if (validaCampoZeroAdicionar()){
		    	form.submit();
		}    	
	}
	
	function validaCampoZeroAdicionar() {
		var form = document.forms[0];
		var msg2 = '';

		if(!testarValorZero(form.valorMaxPercFaixaValor)) {
			msg2 = msg2 + 'Valor Mínimo deve somente conter números positivos.\n';
		}
		if(!testarValorZero(form.percentualPercFaixaValor)) {
			msg2 = msg2 + 'Percentual deve somente conter números decimais positivos.\n';
		}
		if( msg2 != '' ){
			alert(msg2);
			return false;
		}else{
			return true;
		}
	}
	
	//Testa se o campo foi digitado somente com zeros
	function testarValorZero(valor) {
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
			}
			else {
				var intValorCampo = valor.value * 1;
				if (intValorCampo == 0) {
	        		retorno =  false;
				}
			}
		}
		return retorno;
	}

</script>

</head>


<body leftmargin="5" topmargin="5"
	onload="javascript:resizePageSemLink(480, 260);javascript:setarFoco('${requestScope.nomeCampo}');">


<html:form action="/adicionarPercentualFaixaValorPopupAction"
	name="InserirPrestacoesParcelamentoPerfilActionForm"
	type="gcom.gui.cobranca.parcelamento.InserirPrestacoesParcelamentoPerfilActionForm"
	method="post">

	<table width="450" border="0" cellspacing="5" cellpadding="0">
		<tr>

			<td width="100%" valign="top" class="centercoltext">
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
					<td class="parabg">Adicionar Percentual Por Faixa de Valor</td>

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
					<p>Preencha os campos para inserir um percentual por faixa de valor:</p>
					<p>&nbsp;</p>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong> Valor Mínimo:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:text property="valorMaxPercFaixaValor" size="14"
							maxlength="14" tabindex="1" 
							onkeyup="formataValorMonetario(this, 14)" 
							style="text-align:right;" />
					</td>
				</tr>
				<tr>
					<td width="30%"><strong> Percentual:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:text property="percentualPercFaixaValor" size="6"
							maxlength="6" tabindex="2" 
							onkeyup="formataValorMonetario(this, 6)" 
							style="text-align:right;" />
					</td>
				</tr>

			</table>
			
			<table width="100%" border="0">
				<tr>
					<td valign="top"><input type="button" name="ButtonCancelar"
						class="bottonRightCol" value="Voltar" onClick="voltar();"> &nbsp;
					<input name="button" type="button" class="bottonRightCol"
						value="Desfazer" onclick="desfazer();"></td>
					<td valign="top">
					<div align="right"><input name="botaoInserir" type="button"
						class="bottonRightCol" value="Inserir"
						onclick="validarForm(document.forms[0]);" tabindex="17"></div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
	</table>
</body>
</html:form>
</html:html>
