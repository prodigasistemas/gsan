<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.cadastro.geografico.BairroArea"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="BairroActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
<!-- Begin
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.BairroActionForm;

    if (tipoConsulta == 'municipio') {
      form.idMunicipio.value = codigoRegistro;
      form.nomeMunicipio.value = descricaoRegistro;
      form.nomeMunicipio.style.color = "#000000";
      form.action = "/gsan/exibirInserirBairroAction.do";
      form.submit();

    }
  }
function validarForm(form){

if(testarCampoValorZero(document.BairroActionForm.idMunicipio, 'Município') && testarCampoValorZero(document.BairroActionForm.codigoBairro, 'Código do Bairro') && testarCampoValorZero(document.BairroActionForm.codigoBairroPrefeitura, 'Código do Bairro na Prefeitura')){

if(validateBairroActionForm(form)){
	form.action = 'inserirBairroAction.do';
    submeterFormPadrao(form);
}
}
}

function limpaMunicipio(){
	var form = document.BairroActionForm;
	
		form.nomeMunicipio.value = "";
		form.idMunicipio.value = "";
		form.codigoBairro.value = "";
		form.idMunicipio.focus();
}

function limpaNomeMunicipio(evt){
	var form = document.BairroActionForm;

	// os comentários seriam se caso usuario queira q nao apague ao navegar nas caixinhas de texto.
	
//	var keyCode = document.layers ? evt.which : document.all ? event.keyCode : document.getElementById ? evt.keyCode : 0; 
	
//	if (keyCode != 37 && keyCode != 38 && keyCode != 39 && keyCode != 40){
		form.nomeMunicipio.value = "";
		form.codigoBairro.value = "";
//	}
}

function redirecionarSubmitAtualizar(idBairro) {
	urlRedirect = "/gsan/exibirAtualizarBairroAction.do?idRegistroAtualizacao=" + idBairro;
	redirecionarSubmit(urlRedirect);
}

-->
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/inserirBairroAction.do" name="BairroActionForm"
	type="gcom.gui.cadastro.geografico.BairroActionForm" method="post"
	onsubmit="return validateBairroActionForm(this);">

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
					<td class="parabg">Inserir Bairro</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>

				</tr>
			</table>

			<p>&nbsp;</p>
			<table bordercolor="#000000" width="100%" cellspacing="0">
				<tr>
					<td colspan="2">Para adicionar o bairro, informe os dados abaixo:
					<logic:present scope="application" name="urlHelp">
								<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroEnderecoBairroInserir', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
								<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>					
					</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td width="183"><strong>Munic&iacute;pio:<font color="#FF0000">*</font></strong></td>
					<td width="432"><html:text maxlength="4"
						onkeyup="javascript:limpaNomeMunicipio(event);"
						property="idMunicipio" size="4" tabindex="1"
						onkeypress="javascript:return validaEnter(event, 'exibirInserirBairroAction.do', 'idMunicipio');" />
					<a
						href="javascript:abrirPopup('exibirPesquisarMunicipioAction.do');">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Municipio" /></a> <logic:present
						name="idMunicipioNaoEncontrado" scope="request">
						<html:text maxlength="30" property="nomeMunicipio" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" />
					</logic:present> <logic:notPresent name="idMunicipioNaoEncontrado"
						scope="request">
						<html:text maxlength="30" property="nomeMunicipio" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="40" />
					</logic:notPresent> <a href="javascript:limpaMunicipio();"><img
						src="imagens/limparcampo.gif" border="0" height="21" width="23"></a>
					</td>

				</tr>
				<tr>
					<td height="29"><strong>C&oacute;digo do Bairro:<font
						color="#FF0000">*</font></strong></td>
					<td><html:text property="codigoBairro" size="5" tabindex="2"
						maxlength="4" /> <a
						href="javascript:abrirPopup('exibirPesquisarBairroAction.do?consulta=sim&idMunicipio='+document.forms[0].idMunicipio.value+'&indicadorUsoTodos=1', 400, 800);"><img
						width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa_verde.gif"
						title="Pesquisar Bairro" /></a></td>
				</tr>
				<tr>
					<td height="31"><strong> Nome do Bairro:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="nomeBairro" size="30" tabindex="3"
						maxlength="30" /></td>
				</tr>
				<tr>
					<td><strong>C&oacute;digo do Bairro na Prefeitura: </strong></td>
					<td align="right">
					<div align="left"><html:text maxlength="4"
						property="codigoBairroPrefeitura" tabindex="4" size="4" /></div>
					</td>
				</tr>

				<tr>
					<td><strong>Áreas do Bairro:<font color="#FF0000">*</font></strong></td>
					<td align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Adicionar" align="right"
						id="botaoAdicionar"
						onclick="javascript:abrirPopupComSubmit('exibirInserirBairroAction.do?redirecionarPagina=inserirAreaBairro',400,600);" />
					</td>

				</tr>

				<tr>
					<td colspan="2">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr bordercolor="#000000">
							<td bgcolor="#90c7fc" align="center" width="15%" height="20">
							<div align="center"><strong>Remover</strong></div>
							</td>
							<td bgcolor="#90c7fc" align="center" width="45%" height="20"><strong>Nome
							da Área do Bairro</strong></td>
							<td bgcolor="#90c7fc" align="center" width="40%" height="20"><strong>Distrito
							Operacinal</strong></td>
						</tr>
						<logic:present name="colecaoBairroArea">
							<tr>
								<td colspan="3">

								<div style="width: 100%; height: 100%; overflow: auto;">
								<table width="100%" bgcolor="#99CCFF">

									<%int cont = 1;%>
									<logic:iterate name="colecaoBairroArea" id="bairroArea"
										type="BairroArea">
										<%cont = cont + 1;
							if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

							%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="15%">
											<div align="center"><font color="#333333"> <img width="14"
												height="14" border="0"
												src="<bean:message key="caminho.imagens"/>Error.gif"
												onclick="javascript:document.forms[0].target='';if(confirm('Confirma remoção?'))
													{redirecionarSubmit('removerAreaBairroAction.do?ultimaAlteracao=<bean:write name="bairroArea" property="ultimaAlteracao.time"/>&tipoRetorno=inserir');}" />
											</font></div>
											</td>
											<td width="45%"><a
												href="javascript:abrirPopup('exibirInserirAreaBairroPopupAction.do?ultimaAlteracao=<bean:write name="bairroArea" property="ultimaAlteracao.time"/>&retornarTela=exibirInserirBairroAction.do',400,600)"><bean:write
												name="bairroArea" property="nome" /></a></td>
											<td width="40%"><logic:present name="bairroArea" property="distritoOperacional">
												<bean:write name="bairroArea" property="distritoOperacional.descricao" />
											</logic:present> 
											<logic:notPresent name="bairroArea" property="distritoOperacional"> &nbsp;</logic:notPresent>
											</td>
										</tr>

									</logic:iterate>

								</table>
								</div>
								</td>
							</tr>
						</logic:present>
					</table>
					</td>
				</tr>


				<tr>
					<td>&nbsp;</td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>



				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong> <font color="#ff0000"> <input name="Submit22"
						class="bottonRightCol" value="Desfazer" type="button"
						onclick="window.location.href='/gsan/exibirInserirBairroAction.do?menu=sim';">

					<input name="Submit23" class="bottonRightCol" value="Cancelar"
						type="button"
						onclick="window.location.href='/gsan/telaPrincipal.do'"> </font></strong></td>
					<td align="right"><gsan:controleAcessoBotao name="Button"
						value="Inserir"
						onclick="javascript:document.forms[0].target='';validarForm(document.forms[0]);"
						url="inserirBairroAction.do" /> <!-- <input type="button" name="Button"
						class="bottonRightCol" tabindex="5" value="Inserir"
						onClick="javascript:validarForm(document.forms[0])" /> --></td>
				</tr>
				<!--
				## Alterado por Tiago Moreno - 18/07/2006 ## 
				<tr>
					<td><input type="button" name="Button" class="bottonRightCol"
						tabindex="5" value="Inserir"
						onClick="" /></td>
					<td>&nbsp;</td>
				</tr>
				 -->
			</table>

			<p>&nbsp;</p>








			</td>
		</tr>
	</table>


	<%@ include file="/jsp/util/rodape.jsp"%>


</html:form>
</body>
</html:html>
