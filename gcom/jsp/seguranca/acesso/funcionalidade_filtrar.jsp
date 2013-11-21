<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="FiltrarFuncionalidadeActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(form){

		if(testarCampoValorZero(document.FiltrarFuncionalidadeActionForm.codigo, 'Código') && 
			testarCampoValorZero(document.FiltrarFuncionalidadeActionForm.descricao, 'Descrição') && 
			testarCampoValorZero(document.FiltrarFuncionalidadeActionForm.modulo, 'Módulo')) {

			if(validateFiltrarFuncionalidadeActionForm(form)){
    			submeterFormPadrao(form);
			}
		}
	}

	function limparCodigoFiltrarFuncionalidade(form) {
    	form.codigo.value = "";
	}

	function limparDescricaoFiltrarFuncionalidade(form) {
		form.descricao.value = "";
	}

</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="filtrarFuncionalidadeAction" method="post"
	name="FiltrarFuncionalidadeActionForm"
	type="gcom.gui.seguranca.acesso.FiltrarFuncionalidadeActionForm"
	onsubmit="return validateFiltrarFuncionalidadeActionForm(this);">
	
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
			<td width="600" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Filtrar Funcionalidade</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para manter a(s) funcionalidades(s), informe os
					dados abaixo:</td>
					<td width="80" align="right"><html:checkbox property="atualizar" value="1" /><strong>Atualizar</strong></td>
     			</tr>
				<tr>
					<td width="120"><strong>Código:</strong></td>
					<td colspan="2"><strong> <html:text property="codigo" size="10" maxlength="9" />
					</strong> <a
						href="javascript:limparCodigoFiltrarFuncionalidade(document.forms[0]);">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>

				<tr>
					<td width="120"><strong>Descri&ccedil;&atilde;o:</strong></td>

					<td colspan="3"><strong> <html:text property="descricao" size="60"
						maxlength="60" /> </strong> <a
						href="javascript:limparDescricaoFiltrarFuncionalidade(document.forms[0]);">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>

				<tr>
					<td width="120"><strong>Módulo:</strong></td>
					<td colspan="2"><html:select property="modulo">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoModulo"
							labelProperty="descricaoModulo" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>

				<tr>
					<td width="120"><strong>Ponto de Entrada:</strong></td>
					<td colspan="2"><strong> <html:radio property="indicadorPontoEntrada" value="1" />
					<strong>Sim <html:radio property="indicadorPontoEntrada" value="2" />
					N&atilde;o</strong> </strong></td>

				</tr>


				<tr>

				</tr>

				<tr>
					<td align="right" height="24" colspan="4"> <input type="button" name="Button"
						class="bottonRightCol" value="Filtrar"  
						onClick="javascript:validarForm(document.forms[0])" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>
</html:form>
</body>
</html:html>

