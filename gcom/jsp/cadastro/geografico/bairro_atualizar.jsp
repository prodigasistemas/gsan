<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.cadastro.geografico.BairroArea" %>
<%@ page import="gcom.util.Pagina, gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="BairroActionForm" />
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

    }
  }
 function limparPesquisa() {
    var form = document.BairroActionForm;

    form.idMunicipio.value = "";
    form.nomeMunicipio.value = "";

  }

function validarForm(form){
if(testarCampoValorZero(document.BairroActionForm.codigoBairroPrefeitura, 'Código Bairro da Prefeitura')){
if(validateBairroActionForm(form)){
	form.action = 'atualizarBairroAction.do';
    submeterFormPadrao(form);
}
}
}
-->
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/atualizarBairroAction.do" name="BairroActionForm"
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
					<td class="parabg">Atualizar Bairro</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>
			<table bordercolor="#000000" width="100%" cellspacing="0">
				<tr>
					<td colspan="2">
					Para atualizar um bairro, informe os dados abaixo:
					</td>
					<logic:present scope="application" name="urlHelp">
								<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroEnderecoBairroAtualizar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
								<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>					
					</tr>
      		</table>
    
      		<table width="100%" border="0">
				<tr>
					<td width="183"><strong>Munic&iacute;pio:<font color="#FF0000">*</font></strong></td>

					<td width="432"><html:text maxlength="4" style="background-color:#EFEFEF; border:0; color: #000000" property="idMunicipio" 
						size="4" readonly="true" /> <html:text maxlength="30"
						property="nomeMunicipio" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000"
						size="40" /></td>

				</tr>
				<tr>
					<td height="29"><strong>C&oacute;digo do Bairro:<font
						color="#FF0000">*</font></strong></td>
					<td><html:text property="codigoBairro" style="background-color:#EFEFEF; border:0; color: #000000"  size="5"
						maxlength="4" readonly="true" /></td>
				</tr>
				<tr>
					<td height="31"><strong> Nome do Bairro:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="nomeBairro" tabindex="1" size="30"
						maxlength="30" /></td>
				</tr>
				<tr>
					<td><strong>C&oacute;digo do Bairro na Prefeitura: </strong></td>
					<td align="right">
					<div align="left"><html:text maxlength="4"
						property="codigoBairroPrefeitura" tabindex="2" size="4" /></div>
					</td>
				</tr>
				<tr>
					<td><strong>Indicador de Uso:</strong></td>
					<td align="right">
					<div align="left"><html:radio property="indicadorUso" tabindex="3"
						value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>" /> <strong>Ativo</strong>
					<html:radio property="indicadorUso" tabindex="4"
						value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>" />
					<strong>Inativo</strong></div>
					</td>
				</tr>
				
				<tr>
					<td><strong>Áreas do Bairro:<font color="#FF0000">*</font></strong></td>
					<td align="right"><input name="Button" type="button"
							class="bottonRightCol" value="Adicionar" align="right" id="botaoAdicionar"
							onclick="javascript:abrirPopupComSubmit('exibirAtualizarBairroAction.do?redirecionarPagina=atualizarAreaBairro',400,600);" />
					</td>
				
				</tr>
				
				<tr>
				<td colspan="2">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr bordercolor="#000000"> 
					      <td bgcolor="#90c7fc" align="center" width="15%" height="20"><div align="center"><strong>Remover</strong></div></td>
					      <td bgcolor="#90c7fc" align="center" width="45%" height="20"><strong>Nome da Área do Bairro</strong></td>
					      <td bgcolor="#90c7fc" align="center" width="40%" height="20"><strong>Distrito Operacinal</strong></td>
					   </tr>
						<logic:present name="colecaoBairroArea">
							<tr>
								<td colspan="3">

								<div style="width: 100%; height: 100%; overflow: auto;">
								<table width="100%" bgcolor="#99CCFF">

									<%int cont = 1;%>
									<logic:iterate name="colecaoBairroArea"
										id="bairroArea" type="BairroArea">
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
											<td width="45%">
												<a href="javascript:abrirPopup('exibirInserirAreaBairroPopupAction.do?ultimaAlteracao=<bean:write name="bairroArea" property="ultimaAlteracao.time"/>&retornarTela=exibirAtualizarBairroAction.do',400,600)"><bean:write name="bairroArea" property="nome"/></a>
											</td>
											<td width="40%">
											<logic:present name="bairroArea" property="distritoOperacional">
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
				<td colspan="3">
				<table width="100%">
				<tr>
					<td>
					<logic:present scope="session" name="manter">
						<input name="Submit222"
						class="bottonRightCol" value="Voltar" type="button"
						onclick="javascript:history.back();">
						</logic:present>
					<logic:notPresent scope="session" name="manter">
						<logic:present name="consulta"><input name="Submit222"
						class="bottonRightCol" value="Voltar" type="button"
						onclick="window.location.href='/gsan/exibirFiltrarBairroAction.do?menu=sim';"></logic:present>				
						<logic:notPresent name="consulta">
						<input name="Submit222"
						class="bottonRightCol" value="Voltar" type="button"
						onclick="window.location.href='/gsan/exibirFiltrarBairroAction.do';">
						</logic:notPresent>
					</logic:notPresent>
					
					<input name="Submit22"
						class="bottonRightCol" value="Desfazer" type="button"
						onclick="window.location.href='/gsan/exibirAtualizarBairroAction.do';">	
					<input name="Submit23" class="bottonRightCol" value="Cancelar"
						type="button"
						onclick="window.location.href='/gsan/telaPrincipal.do'"> </td>
					<td align="right">
					
					<gsan:controleAcessoBotao name="Button" value="Atualizar"
							  onclick="javascript:document.forms[0].target='';validarForm(document.forms[0]);" url="atualizarBairroAction.do"/>
					<!--
					<input name="botao" class="bottonRightCol"
						value="Atualizar" onclick="javascript:validarForm(document.forms[0])" type="button">--></td>
				</tr>
				</table>
				</td>
				</tr>
				<!-- 
				<tr>
					<td><input type="button" name="Button" tabindex="5"
						class="bottonRightCol" value="Atualizar"
						onClick="javascript:validarForm(document.forms[0])" /></td>
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
