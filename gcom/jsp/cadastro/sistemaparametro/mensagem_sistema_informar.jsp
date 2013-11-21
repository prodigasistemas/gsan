<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@page isELIgnored="false"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
	<html:javascript staticJavascript="false"  formName="InformarMensagemSistemaActionForm"
	dynamicJavascript="true" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js">
</script>	

<script language="JavaScript">
   
	function validarForm() {
    var form = document.forms[0];
	  if (validateInformarMensagemSistemaActionForm(form)){
			submeterFormPadrao(form);
		}		  
   	}
   	  
</script>
</head>



<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/informarMensagemSistemaAction.do"
	name="InformarMensagemSistemaActionForm"
	type="gcom.gui.cadastro.sistemaparametro.InformarMensagemSistemaActionForm"
	method="post"
	onsubmit="return validateInformarMensagemSistemaActionForm(this);">
	
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
				<td class="parabg">Inserir Mensagem do Sistema</td>
				<td width="11" valign="top"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>

		<p>&nbsp;</p>
		<table width="100%" border="0">
			<tr>
				<td colspan="2">Para exibir uma nova mensagem no sistema , informe o dado abaixo:</td>
			</tr>
			<tr>
				<td> </td>
			</tr>
			
			<tr>
				<td> </td>
			</tr>
			
			<tr>
				<td colspan="2"><strong>Mensagem do Sistema:</strong></td>
			</tr>
			
			<tr>
				<td colspan="2"><html:textarea property="mensagemSistema" cols="59" rows="4" 
								onkeyup=" validarTamanhoMaximoTextArea(this,200);" tabindex="1"/>
				</td>
			</tr>
			
			<table width="100%">
					
					<tr>
						  <td width="40%" align="left">				
							<input type="button" name="ButtonReset" class="bottonRightCol"
							value="Desfazer" onclick="javascript:window.location.href='/gsan/exibirInformarMensagemSistemaAction.do'"> 
							
							<input type="button"
							name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						  </td>
						  
						  <td align="right"><input type="button" name="Button" class="bottonRightCol"
								value="Salvar" onclick="validarForm();" tabindex="2"/>
						  </td>
					</tr>
			</table>
			
          </table>
          
</table>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</html:html>


