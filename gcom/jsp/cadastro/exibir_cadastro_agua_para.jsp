<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="DadosRecadastramentoAguaParaActionForm" dynamicJavascript="true" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">
<html:form action="/exibirRecadastramentoAguaPara" enctype="multipart/form-data" name="DadosRecadastramentoAguaParaActionForm" type="gcom.gui.cadastro.DadosRecadastramentoAguaParaActionForm" method="post">

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
					    <td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
						</td>
						<td class="parabg">Filtrar Formulario Recadastramento Água Pará</td>
						<td width="16" valign="top">
							<img border="0" src="imagens/parahead_right.gif" />
						</td>
					</tr>
					</table>
			       <p>&nbsp;</p>
			       <table width="100%" cellspacing="0">
						<tr>
							<td bordercolor="#000000" width="10%"><strong>Im&oacute;vel:</strong></td>
							<td width="90%" colspan="3"><html:text
									property="idImovel" maxlength="9" size="9"/>
							</td>
						</tr>
						<tr>
						  <td><strong>Situação do Recadastramento:</strong></td>
						    <td>
							   <html:radio property="situacao" tabindex="1" value="1" /><strong>Aceito</strong> 
							   <html:radio	property="situacao" tabindex="2" value="2" /><strong>Negado</strong>
							   <html:radio property="situacao" tabindex="3" value="3" /><strong>Pendente</strong>
							   <html:radio property="situacao" tabindex="4" value="4" /><strong>Todos</strong>
							   <html:radio property="situacao" tabindex="5" value="0" /><strong>Pesquisar somente por matricula</strong>
						    </td>
						  <td>&nbsp;</td>
						</tr>
						<tr>
							<td width="65" align="right"><input name="Button"
								type="button" class="bottonRightCol" value="Filtrar"
								align="left"
								onClick="javascript:validarForm(document.forms[0]);"
								tabindex="9"></td>
						</tr>
					</table>
	</td>
				<p>&nbsp;</p>
			
			
			
			<p>&nbsp;</p>
		</tr>
		<%@ include file="/jsp/util/rodape.jsp"%>
	</table>
	<p>&nbsp;</p>
</html:form>
<script language="JavaScript">
function validarForm(form){
	var form = document.forms[0];
  	form.action = "filtrarRecadastramentoAguaParaAction.do";
		
	submeterFormPadrao(form);  
}
</script>
</body>
</html:html>

