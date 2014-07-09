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

<link rel="stylesheet"href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">


</script>

</head>

<body leftmargin="0" topmargin="0"
		onload="window.focus();resizePageSemLink(800,535);">

<html:form action="/exibirAnaliseSituacaoArquivoAtualizacaoCadastralPopupAction" 
	name="ExibirAnaliseSituacaoArquivoAtualizacaoCadastralForm"
	type="gcom.gui.cadastro.atualizacaocadastral.ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm"
	method="post">
	
	<table width="702" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="100%" valign="top" class="centercoltext">
				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
				
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
						<td class="parabg">Situação de Transmissão - Arquivos de Atualização Cadastral</td>
						<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
				</table>
				
				<p>&nbsp;</p>
				
				<table width="100%" border="0">
					<tr bordercolor="#000000" bgcolor="#90c7fc" class="styleFontePeqNegrito">
						<td width="10%" bgcolor="#90c7fc"> <strong>Descrição</strong> </td>
						<td width="10%" bgcolor="#90c7fc"> <strong>Quantidade de Imóveis</strong> </td>	
					</tr>
					<tr bgcolor="#FFFFFF" class="styleFontePequena">
						<td colspan="1"> Total de Imóveis na Rota </td>
						<td colspan="1" > <bean:write name="ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm" property="totalImoveis"/> </td>
					</tr>
					<tr bgcolor="#FFFFFF" class="styleFontePequena">
						<td colspan="1"> Imóveis Transmitidos </td>
						<td colspan="1" > <bean:write name="ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm" property="imoveisTransmitidos"/> </td>
					</tr>
	
					<tr bgcolor="#FFFFFF" class="styleFontePequena">
						<td colspan="1"> Imóveis Aprovados </td>
						<td colspan="1" > <bean:write name="ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm" property="imoveisAprovados"/> </td>
					</tr>
					<tr bgcolor="#FFFFFF" class="styleFontePequena">
						<td colspan="1"> Imóveis com Anormalidade </td>
						<td colspan="1" > <bean:write name="ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm" property="imoveisComAnormalidade"/> </td>
					</tr>
					<tr bgcolor="#FFFFFF" class="styleFontePequena">
						<td colspan="1"> Imóveis com Alteração de Hidrômetro </td>
						<td colspan="1" > <bean:write name="ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm" property="imoveisComAlteracaoHidrometro"/> </td>
					</tr>
					<tr bgcolor="#FFFFFF" class="styleFontePequena">
						<td colspan="1"> Imóveis com Alteração de Situação da Ligação de Água </td>
						<td colspan="1" > <bean:write name="ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm" property="imoveisComAlteracaoLigacaoAgua"/> </td>
					</tr>
					<tr bgcolor="#FFFFFF" class="styleFontePequena">
						<td colspan="1"> Imóveis com Alteração de Situação da Ligação de Esgoto </td>
						<td colspan="1" > <bean:write name="ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm" property="imoveisComAlteracaoLigacaoEsgoto"/> </td>
					</tr>
					<tr bgcolor="#FFFFFF" class="styleFontePequena">
						<td colspan="1"> Imóveis com Alteração de Categoria/Subcategoria/Qtd. de Economias </td>
						<td colspan="1" > <bean:write name="ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm" property="imoveisComAlteracaoCategoriaSubEconomias"/> </td>
					</tr>
				</table>
				<p>&nbsp;</p>
			</td>
		</tr>
	</table>	
</html:form>

</body>

</html:html>
