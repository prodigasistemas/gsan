<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript"src="<bean:message key="caminho.js"/>util.js"></script>
<html:javascript staticJavascript="false"  formName="FaixaForm" />
<script language="JavaScript">
 
	function validarForm(form) {

		if(validateFaixaForm(form) && validarValorFinalFaixa(form)){
	  		submeterFormPadrao(form);	
		}	
	}


	function validarValorFinalFaixa(form){

		retorno = true;
		
		if(parseInt(form.valorFinal.value) < parseInt(form.valorInicial.value)){
			alert('Valor da faixa final informado é menor que o valor inicial da faixa informado.');
			retorno = false;
		}

		return retorno;
				
	}
	
	function chamarReload(){	
		chamarSubmitComUrlSemUpperCase('/gsan/exibirGerarRelatorioDocumentosAReceberAction.do');
	}

</script>

</head>

<body>

<logic:present name="reload" scope="request">
	<body leftmargin="5" topmargin="5"
		onload="resizePageSemLink(400, 400);chamarReload();window.close();">
</logic:present>

<logic:notPresent name="reload">
	<body leftmargin="5" topmargin="5"
		onload="resizePageSemLink(600, 400);">
</logic:notPresent>


<html:form action="/adicionarFaixaAction"
	name="FaixaForm"
	type="gcom.gui.relatorio.cobranca.FaixaForm"
	method="post">
	
	<table width="530" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="452" valign="top" class="centercoltext">
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
					<td class="parabg">Adicionar Faixa de Dias Vencidos para Documentos a Vencer</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="4">Preencha os campos para inserir uma faixa de
					dias vencidos:</td>
				</tr>
				
				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
				</tr>
				
				<tr>
					<td width="25%">
						<strong>
							Descrição da Faixa:<font color="#FF0000">*</font>
						</strong>
					</td>					
					<td>
						<html:text maxlength="20"
							tabindex="1"
							property="descricao" 
							size="20"
							/>
					</td>
				</tr>
				
				<tr>
					<td width="25%">
						<strong>
							Valor Inicial da Faixa:<font color="#FF0000">*</font>
						</strong>
					</td>					
					<td>
						<html:text maxlength="5"
							tabindex="2"
							property="valorInicial" 
							size="7" 
							onkeypress="return isCampoNumerico(event);"/>
					</td>
				</tr>
				
				<tr>
					<td width="25%">
						<strong>
							Valor Final da Faixa:<font color="#FF0000">*</font>
						</strong>
					</td>					
					<td>
						<html:text maxlength="5"
							tabindex="3"
							property="valorFinal" 
							size="7" 
							onkeypress="return isCampoNumerico(event);"/>
					</td>
				</tr>
			
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>

				<tr>
					<td height="0">&nbsp;</td>
					<td colspan="3">&nbsp;</td>
				</tr>

				<tr>
					<td height="24" colspan="4">
						<div align="right">
							<input type="button" name="Button"
								class="bottonRightCol" value="Inserir" tabindex="3"
								onClick="validarForm(document.forms[0]);" /> 
							<input type="button"
								name="Button" class="bottonRightCol" value="Fechar" tabindex="4"
								onClick="window.close()" />
						</div>
					</td>
					<td colspan="3">&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
	</table>
</html:form>
</html:html>
