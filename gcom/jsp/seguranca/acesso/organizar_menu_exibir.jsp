<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@page isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript">
	function validarForm(){
	   var form = document.forms[0];
   	   form.submit();
	}
</script>

</head>
<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/exibirOrganizarMenuArvoreAction.do"
	name="OrganizarMenuActionForm"
	type="gcom.gui.seguranca.acesso.OrganizarMenuActionForm"
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
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
				<td class="parabg">Organizar Menu</td>
				<td width="11" valign="top"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>

		<p>&nbsp;</p>
		<table width="100%" border="0">

			<tr>
				<td colspan="2">Para organizar o menu informe, informe os dados abaixo:</td>
			</tr>

			<tr>
				<td>
					<strong>Modulo:</strong>
					<font color="#FF0000">*</font>
				</td>
				
				<td>
					<html:select property="modulo" style="width: 230px;">
						<logic:present name="colecaoModulo" scope="session">
							<html:options collection="colecaoModulo"
								labelProperty="descricaoModulo" 
								property="id" />
						</logic:present>
					</html:select> 														
				</td>
			</tr>
			
			<tr>
				<td>&nbsp;</td>
				<td align="left"><font color="#FF0000">*</font> Campo
				Obrigat&oacute;rio</td>
			</tr>
		</table>

		<table width="100%">
			<tr>
				<td align="left">
					<input type="button"
						name="ButtonCancelar" 
						class="bottonRightCol" 
						value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
				</td>
				
				<td align="right">
					<gsan:controleAcessoBotao name="Button" 
				  		value="Exibir Árvore de Funcionalidades" 
				  		onclick="javascript:validarForm();" 
				  		url="exibirOrganizarMenuArvoreAction.do"/>
				  	
				</td>
			</tr>
		</table>
	</tr>


</table>
<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
