<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarActionForm"	dynamicJavascript="false" />


</head>
<body leftmargin="5" topmargin="5"
	onload="window.focus();javascript:resizePageSemLink(530, 260);">

<form action="/exibirPesquisarUsuarioEfetuouInstalacaoRetiradaAction.do"
           method="post">
	<table width="500" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="500" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
					</td>
					<td class="parabg">Pesquisar Usu&aacute;rio</td>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td height="28">Usu&aacute;rio que efetuou a ${requestScope.tipo} do hidr&ocirc;metro:</td>
    			</tr>
    		</table>
    
    		<table width="100%" border="0">
				<tr>
					<td width="18%" height="28"><strong>Login:</strong></td>
					<td width="82%">
						<input type="text" name="loginUsuario" size="10" readonly="true"
						value="${requestScope.loginUsuario}"  style="background-color:#EFEFEF; border:0;"/>
					</td>
				</tr>
				<tr>
					<td><strong>Nome:</strong></td>
					<td>
						<input type="text" name="nomeUsuario" size="50" readonly="true"
						value="${requestScope.nomeUsuario}"  style="background-color:#EFEFEF; border:0;"/>
					</td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td height="24" colspan="2" width="80%">
						<input type="button" class="bottonRightCol" value="Fechar" onclick="javascript:window.close();"/>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</form>
</body>
</html:html>
