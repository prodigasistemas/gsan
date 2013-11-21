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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="FiltrarFaturaClienteResponsavelActionForm" />
<script language="JavaScript">


<!-- Begin
function validarForm(form){
    var form = document.forms[0];
    
    if(form.value != ''){
		form.imovelId.action = 'adicionarFaturaClienteResponsavelContaPopupAction.do';
   		form.submit();
   	}else{
   		alert('Informe Imóvel');
   	}
}


function chamarSubmitComUrl(pagina){
	toUpperCase(opener.window.document.forms[0]);
	opener.window.document.forms[0].action = pagina; 
	opener.window.document.forms[0].submit();
}


function pesquisarImovel(){
	var form = document.forms[0];

	redirecionarSubmit('exibirPesquisarImovelAction.do?limparForm=1&caminhoRetornoTelaPesquisaImovel=exibirAdicionarFaturaClienteResponsavelContaPopupAction');	
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];
    
    alert('veio');

    if (tipoConsulta == 'tipoDebito') {
      form.idTipoDebito.value = codigoRegistro;
      form.descricaoTipoDebito.value = descricaoRegistro;
      form.descricaoTipoDebito.style.color = "#000000";
	  form.submit();
    }
    
    if (tipoConsulta == 'imovel') {
      form.imovelId.value = codigoRegistro;
      form.inscricao.value = descricaoRegistro;
      form.inscricao.style.color = "#000000";
	  form.submit();
    }
}

function reloadOpener(caminhoRetornoOpener){
  opener.window.document.forms[0].action = caminhoRetornoOpener;
  opener.window.document.forms[0].submit();
}

function limparImovelInscricao(){
	var form = document.forms[0];
	
	form.inscricao.value = "";
}

function limparImovel(){
	var form = document.forms[0];
	
	form.inscricao.value = "";
	form.imovelId.value = "";
}

-->
</script>

</head>

<logic:present name="reloadPage">
	<body leftmargin="5" topmargin="5" onload="resizePageSemLink(610, 350); reloadOpener('${caminhoRetornoOpener}');limparImovel();">
</logic:present>
<logic:notPresent name="reloadPage">
	<body leftmargin="5" topmargin="5" onload="resizePageSemLink(610, 350);">
</logic:notPresent>

<html:form action="/adicionarFaturaClienteResponsavelContaPopupAction.do"
	name="FiltrarFaturaClienteResponsavelActionForm"
	type="gcom.gui.faturamento.FiltrarFaturaClienteResponsavelActionForm"
	method="post">
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
					<td class="parabg">Adicionar conta a fatura de cliente responsável</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="4">Para adicionar uma conta a fatura de cliente responsável, informe os dados abaixo:</td>
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
					<td><strong>Imóvel:</strong><font color="#FF0000">*</font></td>
					<td colspan="3" align="right">
					<div align="left">
						
						<html:text property="imovelId" size="9" 
							onkeypress="validaEnter(event, 'exibirAdicionarFaturaClienteResponsavelContaPopupAction.do?objetoConsulta=1', 'imovelId');"
							onkeyup="limparImovelInscricao();"
							maxlength="9" tabindex="4"  />
							<a href="javascript:pesquisarImovel();"> <img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Imóvel" /></a> 

						<logic:present name="imovelInexistente">
							<html:text property="inscricao" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								size="40" maxlength="45" />
						</logic:present>
						<logic:notPresent name="imovelInexistente">
							<html:text property="inscricao" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent> 
						
						<a href="javascript:limparImovel();"> <img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar Tipo de Débito" /> </a>
					</div>
					</td>
				</tr>
										
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
						onClick="window.close();" />	
                    </td>
                    <td  align="right">
   	                	<input type="button" name="Button" class="bottonRightCol" value="OK" tabindex="3"
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
