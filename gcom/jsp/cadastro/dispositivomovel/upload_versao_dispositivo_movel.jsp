<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>


<%@page isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript">

	function validarForm(){
	   var form = document.forms[0];
	   var mensagem = '';
	   
	   if(form.arquivoJad.value != '' && form.arquivoJar.value != '') {

	  	 	if(confirm('Confirma upload nova versão ?')){
	    	   submitForm(form);
		   	}
		  
	   }else{

		  if(form.arquivoJad.value == ''){
		     mensagem = mensagem + '\n Informe Arquivo JAD.';
		  }

		  if(form.arquivoJar.value == ''){
			     mensagem = mensagem + '\n Informe Arquivo JAR.';
		  }
		  
		  alert(mensagem);
	   }
	}

</script>

</head>
<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<div id="formDiv">

<form action="/gsan/uploadVersaoDispositivoMovelAction.do"
	method="post"
	enctype="multipart/form-data">


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
				<td class="parabg">Upload nova versão GSAN Dispositivo Móvel</td>
				<td width="11" valign="top"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>

		<p>&nbsp;</p>
		<table width="100%" border="0">
			<tr>
				<td colspan="2">Para realizar upload dos arquivos, informe os
				dados abaixo:</td>
			</tr>
			
			<tr>
				<td>&nbsp;</td>
			</tr>
			
			<tr>
				<td><strong>Arquivo JAD:<font color="#FF0000">*</font></strong></td>
				<td><input type="file" style="textbox" name="arquivoJad"
					size="50" /></td>
			</tr>
			
			<tr>
				<td><strong>Arquivo JAR:<font color="#FF0000">*</font></strong></td>
				<td><input type="file" style="textbox" name="arquivoJar"
					size="50" /></td>
			</tr>
			
			<tr>
				<td>&nbsp;</td>
				<td align="left"><font color="#FF0000">*</font> Campo
				Obrigat&oacute;rio</td>
			</tr>

		</table>

		<table width="100%">
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			
			<tr>
				<td align="left">
					<input name="Button" type="button"
						class="bottonRightCol" value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirUploadVersaoDispositivoMovelAction.do?menu=sim"/>'">
					&nbsp;
					<input type="button" name="ButtonCancelar"
						class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
				</td>
				<td align="right">				
					<input type="button" 
								name="Button" 
								class="bottonRightCol"
								value="Upload"
								onclick="javascript:validarForm();"/>			
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>	
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
	</tr>

</table>
<%@ include file="/jsp/util/rodape.jsp"%></form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>
