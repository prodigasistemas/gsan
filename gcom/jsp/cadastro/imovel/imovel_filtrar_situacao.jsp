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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarImovelSituacaoActionForm"
	dynamicJavascript="false" staticJavascript="false" page="3" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">
<!-- Begin

function limparForm(form) {
	
		form.imovelSituacaoTipo.value = "-1";
		form.ligacaoAguaSituacao.value = "-1";
		form.ligacaoEsgotoSituacao.value = "-1"; 
		
	
	}



function validarForm(form){
	form.submit();
}
-->
</script>
</head>


<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<div id="formDiv">
<html:form action="/filtrarImovelSituacaoAction"
	name="FiltrarImovelSituacaoActionForm"
	onsubmit="return validateFiltrarImovelSituacaoActionForm(this);"
	type="gcom.gui.cadastro.imovel.FiltrarImovelSituacaoActionForm"
	method="post">


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
			<td valign="top" class="centercoltext">

			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Consultar Situa&ccedil;&atilde;o do
					Im&oacute;vel</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>

				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>Para consultar a(s)
					situa&ccedil;&atilde;o(&otilde;es) do im&oacute;vel, informe os
					dados abaixo:</td>
				    <td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=imovelSituacaoConsultar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>								
				</tr>
				</table>
				<table width="100%" border="0">
				<tr>
					<td><strong>Tipo da Situa&ccedil;&atilde;o do Im&oacute;vel:</strong></td>
					<td><html:select property="imovelSituacaoTipo">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoImovelSituacaoTipo"
							labelProperty="descricaoImovelSituacaoTipo" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>


				<tr>
					<td><strong>Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o de
					&Aacute;gua:</strong></td>
					<td><html:select property="ligacaoAguaSituacao">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoLigacaoAguaSituacao"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>

				<tr>
					<td><strong>Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o de
					Esgoto:</strong></td>
					<td><html:select property="ligacaoEsgotoSituacao">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoLigacaoEsgotoSituacao"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>


				<tr>
					<td colspan="3"><strong> <font color="#FF0000"></font></strong>
					<div align="left"></div>
					</td>
				</tr>
				<tr>
					<td><input type="button" name="ButtonReset" class="bottonRightCol"
						value="Limpar" onclick="javascript:limparForm(document.forms[0]);">
					<td>&nbsp;</td>
					<td>
					<div align="right">
					<gsan:controleAcessoBotao name="Button" value="Filtrar"
							  onclick="javascript:validarForm(document.forms[0]);" url="filtrarImovelSituacaoAction.do"/>
					<!-- <input type="submit" name="Submit2"
						class="bottonRightCol" value="Filtrar"> --> </div>
					</td>
				</tr>
			</table>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>


</html:form>
</div>
</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/filtrarImovelOutrosCriteriosWizardAction.do?concluir=true&action=validarImovelOutrosCriterios'); }
</script>

</html:html>

