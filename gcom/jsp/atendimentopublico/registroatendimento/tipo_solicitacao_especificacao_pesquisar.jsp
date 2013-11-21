<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="gcom.util.ConstantesSistema" isELIgnored="false"%>

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
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
<!--
    
    function validarForm(form){      
      
      if(validatePesquisarTipoSolicitacaoEspecificacoesActionForm(form)){
     		form.submit();
	  }
    }

-->
</script>


</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(743, 445);">
<html:form action="/pesquisarTipoSolicitacaoEspecificacoesAction"
	method="post" name="PesquisarTipoSolicitacaoEspecificacoesActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.PesquisarTipoSolicitacaoEspecificacoesActionForm"
	onsubmit="return validatePesquisarTipoSolicitacaoEspecificacoesActionForm(this);">
	<table width="730" border="0" cellspacing="5" cellpadding="0">

		<tr>
			<td width="720" valign="top" class="centercoltext">
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
					<td class="parabg">Pesquisar Tipo de Solicitação com Especificações</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<!-- ============================================================================================================================== -->

			<table width="100%" border="0">
				<tr>
					<td colspan="4">Preencha o campo para pesquisar um tipo de
					solcitação com especificações:</td>
				</tr>
				<tr>
					<td><strong>Descrição do Tipo da Solicitação:</strong></td>
					<td><html:text property="descricao" size="50" maxlength="50" /></td>
				</tr>
				<tr>
					<td><strong>Grupo do Tipo da Solicitação<font color="#000000"></font>:</strong></td>
					<td><html:select property="solicitacaoTipoGrupo">

						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoSolicitacaoTipoGrupo"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>

				<tr>
					<td width="40%"><strong>Indicador de Falta D'Água:<font
						color="#FF0000">*</font></strong></td>
					<td width="60%"><strong> <html:radio property="indicadorFaltaAgua"
						value="1" /> <strong>Sim <html:radio
						property="indicadorFaltaAgua" value="2" /> Não <html:radio
						property="indicadorFaltaAgua" value="" /> Todas</strong> </strong></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<logic:present name="caminhoRetornoTelaPesquisaTipoSolicitacaoEspecificacoes">
						<INPUT TYPE="button" class="bottonRightCol" value="Voltar"
							onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaTipoSolicitacaoEspecificacoes}.do')" />
					</logic:present>
					<td colspan="2"><input name="Button" type="button"
						class="bottonRightCol" value="Limpar" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirPesquisarTipoSolicitacaoEspecificacoesAction.do"/>'">
					<td align="right" height="24"><input type="submit" name="Button"
						class="bottonRightCol" value="Pesquisar"
						onClick="javascript:validarForm(document.forms[0])" /></td>
					<td>&nbsp;</td>
				</tr>
			</table>

			<p>&nbsp;</p>
			</td>
		</tr>
	</table>


</html:form>
</html:html>
