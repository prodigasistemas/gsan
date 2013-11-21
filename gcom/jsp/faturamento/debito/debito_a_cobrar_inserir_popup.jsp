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
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirDebitoACobrarPopupActionForm" />
<script language="JavaScript">


<!-- Begin
function validarForm(form){
	if(validateInserirDebitoACobrarPopupActionForm(form)){
   		form.submit();
	}
}


function chamarSubmitComUrl(pagina){
	toUpperCase(opener.window.document.forms[0]);
	opener.window.document.forms[0].action = pagina; 
	opener.window.document.forms[0].submit();
}


function pesquisarTipoDebito(){
	var form = document.forms[0];
	if(form.idTipoDebito.disabled == false){
		redirecionarSubmit('exibirPesquisarTipoDebitoAction.do?limparForm=1&tipoFinanciamentoServico=SIM&caminhoRetornoTelaPesquisaTipoDebito=exibirInserirDebitoACobrarPopupAction');	
	}
}

function limparTipoDebito(){
	var form = document.forms[0];
	if(form.idTipoDebito.disabled == false){
		form.idTipoDebito.value = "";
		form.descricaoTipoDebito.value = "";
		form.valorTotalServico.value = ""; 
	}
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];

    if (tipoConsulta == 'tipoDebito') {
      form.idTipoDebito.value = codigoRegistro;
      form.descricaoTipoDebito.value = descricaoRegistro;
      form.descricaoTipoDebito.style.color = "#000000";
	  form.submit();
    }
}

-->
</script>

</head>

<logic:present name="reloadPage">
	<body leftmargin="5" topmargin="5" onload="resizePageSemLink(610, 350); chamarSubmitComUrl('exibirDebitoCreditoDadosSelecaoExtratoAction.do?reloadPage=1'); window.close();">
</logic:present>

<logic:notPresent name="reloadPage">
	<body leftmargin="5" topmargin="5" onload="resizePageSemLink(610, 350);">
</logic:notPresent>

<html:form action="/inserirDebitoACobrarPopupAction"
	name="InserirDebitoACobrarPopupActionForm"
	type="gcom.gui.faturamento.debito.InserirDebitoACobrarPopupActionForm"
	method="post"
	onsubmit="return validateInserirDebitoACobrarPopupActionForm(this);">
	<table width="580" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="530" valign="top" class="centercoltext">
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
					<td class="parabg">Incluir Débito</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="4">Para incluir um Débito, informe os dados abaixo:</td>
					<td align="right"></td>
				</tr>
				</table>
				<table width="100%" border="0">
				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
				</tr>
				
				<tr>
					<td><strong>Tipo de Débito:</strong><font color="#FF0000">*</font></td>
					<td colspan="3" align="right">
					<div align="left">
						
						<html:text property="idTipoDebito" size="9" 
						onkeypress="validaEnter(event, 'exibirInserirDebitoACobrarPopupAction.do?objetoConsulta=1', 'idTipoDebito');"
						maxlength="9" tabindex="4"  />
						<a href="javascript:pesquisarTipoDebito();"> <img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Tipo de Débito" /></a> 
					
						<logic:present name="corDebitoTipo">
							<logic:equal name="corDebitoTipo" value="exception">
								<html:text property="descricaoTipoDebito" value="" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								size="40" maxlength="45" />
							</logic:equal>
							<logic:notEqual name="corDebitoTipo" value="exception">
								<html:text property="descricaoTipoDebito" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						
						<logic:notPresent name="corDebitoTipo">
							<logic:empty name="InserirDebitoACobrarPopupActionForm" property="idTipoDebito">
								<html:text property="descricaoTipoDebito" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="InserirDebitoACobrarPopupActionForm" property="idTipoDebito">
								<html:text property="descricaoTipoDebito" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent> 
						<a href="javascript:limparTipoDebito();"> <img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar Tipo de Débito" /> </a>
					</div>
					</td>
				</tr>
				<logic:equal name="alterarValorSugeridoEmTipoDebito"
									value="true">
				<tr>
					<td><strong>Valor Total do Servi&ccedil;o:<strong><font color="#FF0000">*</font></strong></strong></td>
					<td colspan="3" align="right">
					<div align="left">
						<html:text property="valorTotalServico" style="text-align: right;"
						onkeyup="formataValorMonetario(this, 11);"
						maxlength="14" size="14" tabindex="5" />
					</div>
					<div align="left"><strong></strong></div>
					<div align="left"></div>
					</td>
				</tr>
				</logic:equal>
				<logic:notEqual name="alterarValorSugeridoEmTipoDebito"
									value="true">
									<tr>
					<td><strong>Valor Total do Servi&ccedil;o:<strong><font color="#FF0000">*</font></strong></strong></td>
					<td colspan="3" align="right">
					<div align="left">
						<html:text property="valorTotalServico" 
						onkeyup="formataValorMonetario(this, 11);"
						readonly="true"  style="text-align:right;background-color:#EFEFEF; border:0; color: #000000"
						maxlength="14" size="14" tabindex="5" />
					</div>
					<div align="left"><strong></strong></div>
					<div align="left"></div>
					</td>
				</tr>
				</logic:notEqual>
				
              <tr> 
                <td><strong> <font color="#FF0000"></font></strong></td>
                <td align="right"> <div align="left"><strong><font color="#FF0000">*</font></strong> 
                    Campos obrigat&oacute;rios</div></td>
              </tr> 
				
				
				<tr>
					<td height="0">&nbsp;</td>
					<td colspan="3">&nbsp;</td>
				</tr>
				
				<tr>
					<td>
						<input type="button" name="Button" class="bottonRightCol" value="Fechar" tabindex="4"
						onClick="window.close()" />	
                    </td>
                    <td  align="right">
                    	<input type="button" name="Button" class="bottonRightCol" value="Inserir" tabindex="3"
						onClick="validarForm(document.forms[0]);" />
                    </td>
				</tr>
				
				
			</table>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
	</table>
</html:form>
</body>

</html:html>
