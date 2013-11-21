<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

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
	formName="InserirCapacidadeHidrometroActionForm" />

<script language="JavaScript">
  
	function validaForm() {
	  	var form = document.forms[0];
	  	form.action = "/gsan/inserirCapacidadeHidrometroAction.do";
		if(validateInserirCapacidadeHidrometroActionForm(form)) {
	     		  		
				submeterFormPadrao(form);   		  
   	      	
   	    }
	}
	 
 
	function limparForm() {
		var form = document.InserirCapacidadeHidrometroActionForm;
		form.descricao.value = "";
		form.abreviatura.value = "";
	    form.numMinimo.value = "";
	    form.numMaximo.value = "";
	    form.numOrdem.value = "";
	    form.codigo.value = "";
			
	}
	

	
	function reload() {
		var form = document.InserirCapacidadeHidrometroActionForm;
		form.action = "/gsan/exibirInserirCapacidadeHidrometroAction.do";
		form.submit();
	}  
	

</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:desabiltaCombo();">

<html:form action="/inserirCapacidadeHidrometroAction"
	name="InserirCapacidadeHidrometroActionForm"
	type="gcom.gui.micromedicao.hidrometro.InserirCapacidadeHidrometroActionForm"
	method="post"
	onsubmit="return validateInserirCapacidadeHidrometroActionForm(this);">

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

			<!-- centercoltext -->

			<td width="625" valign="top" class="centercoltext">

			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Inserir Capacidade de Hidrômetro</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td height="10" colspan="3">Para adicionar uma capacidade de
					hidrômetro, informe os dados abaixo:</td>
				</tr>
<%--
				<tr>
					<td><strong>Identicador da capacidade de Hidrômetro:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="identificador" size="2"
						maxlength="2" tabindex="2" /></td>
					<td></td>
				</tr>
				 --%>
				<tr>
					<td><strong>Descrição:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="descricao" size="50"
						maxlength="20" tabindex="2" /></td>
				</tr>

				<tr>
					<td><strong>Descrição Abreviada:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="abreviatura" size="6"
						maxlength="6" tabindex="2" /></td>
				</tr>

				<tr>
					<td><strong>Número mínimo de dígitos de leitura do hidrômetro:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="numMinimo" size="1"
						maxlength="1" tabindex="2" /></td>
				</tr>

				<tr>
					<td><strong>Número máximo de dígitos de leitura do hidrômetro:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="numMaximo" size="1"
						maxlength="1" tabindex="2" /></td>
				</tr>

				<tr>
					<td><strong>Número de ordem da capacidade do hidrômetro:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="numOrdem" size="2"
						maxlength="2" tabindex="2" /></td>
				</tr>

				<tr>
					<td><strong>Código da Capacidade do hidrômetro:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="codigo" size="1" maxlength="1"
						tabindex="2" /></td>
				</tr>

				<tr>
					<td></td>
					<td><font color="#FF0000">*</font>&nbsp;Campos obrigat&oacute;rios</td>
				</tr>





				<!-- Botões -->

				<tr>
					<td align="left"><input name="Button" type="button"
						class="bottonRightCol" value="Limpar" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirInserirCapacidadeHidrometroAction.do?desfazer=S"/>'">
					<input type="button" name="ButtonCancelar" class="bottonRightCol"
						value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td colspan="2" align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Inserir" onclick="validaForm();"></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</html:html>
