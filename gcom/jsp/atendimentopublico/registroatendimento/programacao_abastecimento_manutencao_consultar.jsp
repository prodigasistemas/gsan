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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false" 
	formName="ConsultarProgramacaoAbastecimentoManutencaoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function validarForm(form){

	if(testarCampoValorZero(document.ConsultarProgramacaoAbastecimentoManutencaoActionForm.municipio, 'Município') && 
	testarCampoValorZero(document.ConsultarProgramacaoAbastecimentoManutencaoActionForm.bairro, 'Bairro') &&
	testarCampoValorZero(document.ConsultarProgramacaoAbastecimentoManutencaoActionForm.mesAnoReferencia, 'Mês e Ano de Referência') && 
	testarCampoValorZero(document.ConsultarProgramacaoAbastecimentoManutencaoActionForm.areaBairro, 'Área de Bairro')) {

		if(validateConsultarProgramacaoAbastecimentoManutencaoActionForm(form)){
			
    		submeterFormPadrao(form);
		}
	}
}
function limparForm(form) {
	  
	    var form = document.ConsultarProgramacaoAbastecimentoManutencaoActionForm;
		form.municipio.value = "";
		form.bairro.value = "";
		form.areaBairro.value = "-1"; 
		form.mesAnoReferencia.value = "";
		form.nomeMunicipio.value = "";
		form.nomeBairro.value = "";
	
	}


    //Recebe os dados do(s) popup(s)
    function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

      var form = document.ConsultarProgramacaoAbastecimentoManutencaoActionForm;

	alert(tipoConsulta);
	alert(codigoRegistro);
	alert(descricaoRegistro);

		if (tipoConsulta == 'municipio') {
		
		form.municipio.value = codigoRegistro;
		form.nomeMunicipio.value = descricaoRegistro;
		form.bairro.value = "";
		form.nomeBairro.value = "";
		form.nomeMunicipio.style.color = "#000000";
	  }
  
  if (tipoConsulta == 'bairro') {
		form.bairro.value = codigoRegistro;
		form.nomeBairro.value = descricaoRegistro;
		form.nomeBairro.style.color = "#000000";
		form.action = 'exibirConsultarProgramacaoAbastecimentoManutencaoAction.do';
        form.submit();
    }

}



function limparMunicipio(){
	var form = document.ConsultarProgramacaoAbastecimentoManutencaoActionForm;
	form.municipio.value = "";
	form.nomeMunicipio.value = "";
}

function limparBairro(){
	var form = document.ConsultarProgramacaoAbastecimentoManutencaoActionForm;
	form.bairro.value = "";
	form.nomeBairro.value = "";
}

function verificarBairro(){
	var form = document.ConsultarProgramacaoAbastecimentoManutencaoActionForm;
	var idMunicipio = trim(form.municipio.value);
    var idBairro = trim(form.bairro.value);
    							
				if ((idMunicipio == null || idMunicipio == "")) {
					alert('Informe o município  antes de informar o bairro');
					limparBairro();					
				} else {
					redirecionarSubmit('exibirPesquisarBairroAction.do?caminhoRetornoTelaPesquisaBairro=exibirConsultarProgramacaoAbastecimentoManutencaoAction&tipo=sembairro&idMunicipio='+document.forms[0].municipio.value);
				}

}

</script>


</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(630, 440);">
<html:form action="/consultarProgramacaoAbastecimentoManutencaoAction"
	method="post"
	onsubmit="return validateConsultarProgramacaoAbastecimentoManutencaoActionForm(this);">
	<table width="600" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="600" valign="top" class="centercoltext">
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
					<td class="parabg">Consultar Programação de Abastecimento e
					Manutenção</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para consultar a programação de abastecimento e
					manutenção, informe os dados abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}atendimentoAbastecimentoManutencaoProgramacaoConsultar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>


				<tr>
					<td><strong>Munic&iacute;pio:</strong></td>
					<td colspan="3"><strong> <html:text maxlength="4"
						property="municipio" size="4"
						onkeypress="javascript:limparBairro();validaEnter(event, 'exibirConsultarProgramacaoAbastecimentoManutencaoAction.do?objetoConsulta=1', 'municipio');" />
					<img width="23" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						style="cursor:hand;"
						onclick="javascript:redirecionarSubmit('exibirPesquisarMunicipioAction.do?caminhoRetornoTelaPesquisaMunicipio=exibirConsultarProgramacaoAbastecimentoManutencaoAction');"
						alt="Pesquisar" /> <logic:present name="municipioNaoEncontrado"
						scope="request">
						<html:text property="nomeMunicipio" size="40" maxlength="30"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="municipioNaoEncontrado"
						scope="request">
						<html:text property="nomeMunicipio" size="40" maxlength="30"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparMunicipio();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a> </strong></td>
				</tr>




				<tr>
					<td><strong>Bairro:</strong></td>
					<td colspan="3"><strong> <html:text maxlength="4" property="bairro"
						size="4" 
						onkeypress="javascript:validaEnterComMensagem(event, 'exibirConsultarProgramacaoAbastecimentoManutencaoAction.do?objetoConsulta=1', 'bairro', 'Bairro');" />
						<a href="javascript:verificarBairro();">
							<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Bairro" /></a>
					<!-- onclick="abrirPopupDependencia('exibirPesquisarBairroAction.do?idMunicipio='+document.forms[0].municipio.value, document.forms[0].municipio.value, 'o município antes de informar o bairro', 400, 800); limparLogradouro();" alt="Pesquisar" />-->
					<logic:present name="bairroNaoEncontrado" scope="request">
						<html:text property="nomeBairro" size="40" maxlength="30"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="bairroNaoEncontrado"
						scope="request">
						<html:text property="nomeBairro" size="40" maxlength="30"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparBairro();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></strong></td>
				</tr>


				<tr>
					<td><strong>Área de Bairro:</strong><font color="#FF0000">*</font></td>
					<td colspan="2"><html:select property="areaBairro">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoBairroArea" labelProperty="nome"
							property="id" />
					</html:select> <font size="1">&nbsp;</font></td>
				</tr>

				<tr>
					<td><strong>Mês e Ano de Referência:</strong></td>
					<td colspan="2"><html:text property="mesAnoReferencia" size="7"
						maxlength="7" onkeyup="javascript:mascaraAnoMes(this,event);" />
					mm/aaaa</td>

				</tr>


				<tr>
					<td height="19"><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>

				<tr>
					<td colspan="2"><input
						name="Button" type="button" class="bottonRightCol" value="Limpar"
						align="left" onclick="javascript:limparForm(document.forms[0]);"></td>
					<td align="right" height="24"><input type="button" name="Button"
						class="bottonRightCol" value="Pesquisar"
						onClick="javascript:validarForm(document.forms[0])" /></td>
				</tr>

			</table>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>

