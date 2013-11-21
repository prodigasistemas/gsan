<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirImovelSituacaoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">


function validarForm(form){
	if (validateInserirImovelSituacaoActionForm(form)){
		form.submit();
	}
}

</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/inserirImovelSituacaoAction.do"
	name="InserirImovelSituacaoActionForm"
	type="gcom.gui.cadastro.imovel.InserirImovelSituacaoActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">

		<tr>
			<td width="150" valign="top" class="leftcoltext">
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
				<tr>
					<td></td>
				</tr>
			</table>



			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Inserir Situação de Imóvel</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>



			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para adicionar a situa&ccedil;&atilde;o de
					im&oacute;vel, informe os dados abaixo:</td>
					<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=imovelSituacaoInserir', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>																	        										
				</tr>
			</table>				
			<table width="100%" border="0">
				<tr>
					<td><strong>Tipo da Situa&ccedil;&atilde;o do Im&oacute;vel:<font
						color="#FF0000">*</font></strong></td>
					<td><html:select property="imovelSituacaoTipo">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="collectionImovelSituacaoTipo"
							labelProperty="descricaoImovelSituacaoTipo" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>

				<tr>
					<td><strong>Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o de
					&Aacute;gua:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="ligacaoAguaSituacao">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="collectionLigacaoAguaSituacao"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>

				<tr>
					<td><strong>Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o de
					Esgoto:</strong></td>
					<td><html:select property="ligacaoEsgotoSituacao">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="collectionLigacaoEsgotoSituacao"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>


				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>
				<tr>
					<td><input name="Button" type="reset" class="bottonRightCol"
						value="Desfazer" align="left"> <input name="Button" type="button"
						class="bottonRightCol" value="Cancelar" align="left"
						onclick="window.location.href='<html:rewrite page="/"/>'"></td>
					<td align="right">
					<gsan:controleAcessoBotao name="Button" value="Inserir"
							  onclick="javascript:validarForm(document.forms[0]);" url="inserirImovelSituacaoAction.do"/>
					<!-- <input name="Button" type="submit"
						class="bottonRightCol" value="Inserir" align="right"> --></td>
				</tr>

			</table>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>


</html:form>
</body>
</html:html>
