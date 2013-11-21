<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/menus-taglib.tld" prefix="menu"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AtualizarFaixaDiasVencidosDocumentosReceberActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>


<script language="JavaScript">

function voltar(){
	var form = document.forms[0];
	
	form.action = 'exibirManterFaixaDiasVencidosDocumentosReceberAction.do';
	form.submit();
	
}

function submiter(){
	var form = document.forms[0];
	
	if (validateAtualizarFaixaDiasVencidosDocumentosReceberActionForm(form)){
		
		form.action = 'atualizarFaixaDiasVencidosDocumentosReceberAction.do';
		form.submit();
	}
}

</script>


</head>

<body leftmargin="5" topmargin="5">


<html:form action="/atualizarFaixaDiasVencidosDocumentosReceberAction.do" method="post"
	name="AtualizarFaixaDiasVencidosDocumentosReceberActionForm"
	type="gcom.gui.cobranca.AtualizarFaixaDiasVencidosDocumentosReceberActionForm"
	onsubmit="return validateAtualizarFaixaDiasVencidosDocumentosReceberActionForm(this);
	">


	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext"><%@ include
				file="/jsp/util/informacoes_usuario.jsp"%></td>
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
					<td class="parabg">Atualizar Faixa de Dias Vencidos para Documentos a Receber</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para atualizar a faixa de dias vencidos, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="30%" height="0"><strong>Descri&ccedil;&atilde;o da Faixa :<font
						color="#FF0000">*</font></strong></td>
					<td><html:text property="descricaoFaixa" 
								   maxlength="10"
								   size="12"
								   tabindex="0"
							/>
					</td>
				</tr>
				
				<tr>
					<td width="30%" height="0"><strong>Valor Inicial da Faixa :<font
						color="#FF0000">*</font></strong></td>
					<td><html:text property="valorInicialFaixa" 
								   maxlength="5"
								   onkeypress="return isCampoNumerico(event);"
								   size="6"
								   tabindex="1"
							/>
					</td>
				</tr>
				<tr>
					<td width="30%" height="0"><strong>Valor Final da Faixa :<font
						color="#FF0000">*</font></strong></td>
					<td><html:text property="valorFinalFaixa" 
								   maxlength="5"
								   onkeypress="return isCampoNumerico(event);"
								   size="6"
								   tabindex="2"
							/>
					</td>
				</tr>
				<tr>
					<td width="30%" height="0"><strong>Indicador de Uso:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><strong> 
						<label> 
							<html:radio 
								value="1"
								property="indicadorUso" 
								tabindex="3"
							/> Ativo
						</label> 
						<label>
							<html:radio
								value="0"
								property="indicadorUso" 
								tabindex="4"
							/> Inativo
						</label> </strong>
					</td>
				</tr>
				<tr>
				<tr>
					<td height="0">&nbsp;</td>
					<td colspan="2"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</td>
				</tr>
				<tr>
					<td height="0">&nbsp;</td>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td height="0">&nbsp;</td>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td height="0">
						<input name="Button" type="button"
							   class="bottonRightCol" value="Voltar" align="left"
							   onclick="javascript:voltar();">
						<input name="Button" type="button"
							   class="bottonRightCol" value="Desfazer" align="left"
							   onClick="window.location.href='<html:rewrite page="/exibirAtualizarFaixaDiasVencidosDocumentosReceberAction.do?idRegistroAtualizar=${requestScope.idDocumentosReceberFaixaDiasVencidos}"/>'"> 
						<input name="button" type="button"
							   class="bottonRightCol" value="Cancelar"
							   onclick="window.location.href='/gsan/telaPrincipal.do'"
							   align="left" style="width: 80px;">
					</td>
					<td height="0" align="right"><input name="Button" type="button"
						   class="bottonRightCol" 
						   value="Atualizar" align="right"
						   onClick="javascript:submiter();"></td>

					<td>
					<div align="right"></div>
					</td>
				</tr>
					<tr>
					<td height="0">&nbsp;</td>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td height="0">&nbsp;</td>
					<td colspan="2">&nbsp;</td>
				</tr>	<tr>
					<td height="0">&nbsp;</td>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td height="0">&nbsp;</td>
					<td colspan="2">&nbsp;</td>
				</tr>	<tr>
					<td height="0">&nbsp;</td>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td height="0">&nbsp;</td>
					<td colspan="2">&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
