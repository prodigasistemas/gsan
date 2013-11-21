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

function finalizarArquivo(motivo, finalizar) {

	if(motivo.value!=''){
		opener.finalizarComMotivo(motivo, finalizar);
		self.close();
	}else{

		alert('Informe Motivo Finalização.');
		
	}
  	
}

</script>

</head>

<body leftmargin="0" topmargin="0"
		onload="window.focus();resizePageSemLink(600,300);">

<html:form action="/exibirInformarMotivoFinalizacaoPopupAction" 
	name="ConsultarArquivoTextoLeituraActionForm"
	type="gcom.gui.micromedicao.ConsultarArquivoTextoLeituraActionForm"
	method="post">
	
	<html:hidden property="finalizar"/>
	
	<table width="550" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="100%" valign="top" class="centercoltext">
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
						<td class="parabg">Informar Motivo Finalização</td>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
				</table>
				<p>&nbsp;</p>
				<table width="100%" border="0">
					<tr>
						<td width="15%"><strong>Motivo Finalização:<font color="#FF0000">*</font></strong></td>
						<td width="85%">
							<html:textarea property="motivoFinalizacao" cols="40" rows="4" onkeyup="limitTextArea(document.forms[0].motivoFinalizacao, 199, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
							<strong><span id="utilizado">0</span>/<span id="limite">200</span></strong>				
						</td>
					</tr>
			
				</table>
			</td>
		</tr>
		<tr>
			<td width="53" height="24" align="right"><input type="button"
							name="Button2" class="bottonRightCol" value="Finalizar"
							onClick="javascript:finalizarArquivo(document.forms[0].motivoFinalizacao, document.forms[0].finalizar);" /></td>
			<td width="12">&nbsp;</td>
	
		</tr>
	</table>
</html:form>

</body>

</html:html>
