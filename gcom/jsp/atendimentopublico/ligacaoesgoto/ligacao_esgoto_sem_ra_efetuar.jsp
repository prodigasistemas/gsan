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

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="EfetuarLigacaoEsgotoSemRAActionForm" />

<script language="JavaScript">

	function validaForm() {
		var form = document.EfetuarLigacaoEsgotoSemRAActionForm;
		if (validateEfetuarLigacaoEsgotoSemRAActionForm(form)){
			submeterFormPadrao(form);
		}
	}
	
	function limparForm() {

		var form = document.EfetuarLigacaoEsgotoSemRAActionForm;

		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.cpfCnpjCliente.value = "";
		form.situacaoLigacaoAgua.value = "";
		form.situacaoLigacaoEsgoto.value = "";
		form.dataLigacao.value = "";
		form.diametroLigacao.selectedIndex = 0;
		form.materialLigacao.selectedIndex = 0;
		form.perfilLigacao.selectedIndex = 0;
		form.percentualColeta.value = "";
		form.percentualEsgoto.value = "";
		form.action = 'exibirEfetuarLigacaoEsgotoSemRAAction.do';
		form.submit();
	 }
	
	function limparDecricaoEfetuar() {

		var form = document.EfetuarLigacaoEsgotoSemRAActionForm;

		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.cpfCnpjCliente.value = "";
		form.situacaoLigacaoAgua.value = "";
		form.situacaoLigacaoEsgoto.value = "";
		form.dataLigacao.value = "";
		form.diametroLigacao.selectedIndex = 0;
		form.materialLigacao.selectedIndex = 0;
		form.perfilLigacao.selectedIndex = 0;
		form.percentualColeta.value = "";
		form.percentualEsgoto.value = "";
	 }
	 
	//Chama Popup
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			}
			else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}
				else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisaOrdemServico=" + tipo, altura, largura);
				}
			}
		}
	}
	 
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
	    if (tipoConsulta == 'imovel') {
	    	form.matriculaImovel.value = codigoRegistro;
	      	form.action='exibirEfetuarLigacaoEsgotoSemRAAction.do';
	      	form.submit();
	    }
	}
	
	
	 
</script>
</head>
<body leftmargin="5" topmargin="5" >

<html:form action="/efetuarLigacaoEsgotoSemRAAction.do"
	name="EfetuarLigacaoEsgotoSemRAActionForm"
	type="gcom.gui.atendimentopublico.EfetuarLigacaoEsgotoSemRAActionForm"
	method="post">

		<%@ include file="/jsp/util/cabecalho.jsp"%>
		<%@ include file="/jsp/util/menu.jsp"%>

		<table width="770" border="0" cellspacing="5" cellpadding="0">
			<tr>
				<td width="130" valign="top" class="leftcoltext">
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
				<td width="615" valign="top" class="centercoltext">
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
									<td class="parabg">Efetuar Ligação de Esgoto Sem RA</td>
									<td width="11"><img border="0"
										src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
								</tr>
							</table>
							<p>&nbsp;</p>


							<!--Inicio da Tabela Ligação Esgoto -->
							<table width="100%" border="0">
								<tr>

									<td height="31">
									<table width="100%" border="0" align="center">
										<tr>
											<td height="10" colspan="2">Para efetuar a
											liga&ccedil;&atilde;o de esgoto sem RA, informe os dados abaixo:.</td>
										</tr>
										<tr>
											<td height="10" colspan="2">
											<hr>
											</td>
										</tr>
										<tr>
											<td height="10"><strong>Matr&iacute;cula
															do Im&oacute;vel:<strong><font color="#FF0000">*</font></strong>
											</strong></td>

											<td><html:text
												property="matriculaImovel" size="8" maxlength="9"
												onkeypress="validaEnterComMensagem(event, 'exibirEfetuarLigacaoEsgotoSemRAAction.do', 'matriculaImovel','Matrícula do Imóvel');"
												onkeyup="javascript:limparDecricaoEfetuar()" /> <a
												href="javascript:chamarPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 600, 500, '',document.forms[0].matriculaImovel);">

											<img width="23" height="21" border="0"
												src="<bean:message key="caminho.imagens"/>pesquisa.gif"
												title="Pesquisar Imóvel" /></a> <logic:present
												name="matriculaImovelInexistente">
												<html:text property="inscricaoImovel" readonly="true"
													style="background-color:#EFEFEF; border:0; color: #ff0000"
													size="37" maxlength="40" />
											</logic:present> <logic:notPresent
												name="matriculaImovelInexistente">
												<html:text property="inscricaoImovel" readonly="true"
													style="background-color:#EFEFEF; border:0; color: #000000"
													size="37" maxlength="40" />
											</logic:notPresent> <a href="javascript:limparForm();"> <img
												border="0" title="Apagar"
												src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
											</a> </td>
										</tr>

										<tr bgcolor="#cbe5fe">
											<td align="center" colspan="2">
											<table width="100%" border="0" bgcolor="#99CCFF">
												<tr bgcolor="#99CCFF">
													<td height="18" colspan="2">
													<div align="center"><span class="style2"> Dados do Imóvel </span></div>
													</td>
												</tr>
												<tr bgcolor="#cbe5fe">

													<td>
													<table border="0" width="100%">
														<tr>
															<td><strong> Cliente Usu&aacute;rio:</strong></td>
															<td><html:text property="clienteUsuario" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="40"
																maxlength="40" /></td>

														</tr>
														<tr>
															<td><strong>CPF ou CNPJ:</strong></td>
															<td><html:text property="cpfCnpjCliente" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="40"
																maxlength="40" /></td>
														</tr>
														<tr>

															<td><strong>Situa&ccedil;&atilde;o da
															Liga&ccedil;&atilde;o de &Aacute;gua:</strong></td>
															<td><html:text property="situacaoLigacaoAgua"
																readonly="true"
																style="background-color:#EFEFEF; border:0;" size="40"
																maxlength="40" /></td>
														</tr>
														<tr>
															<td><strong>Situa&ccedil;&atilde;o da
															Liga&ccedil;&atilde;o de Esgoto:</strong></td>

															<td><html:text property="situacaoLigacaoEsgoto"
																readonly="true"
																style="background-color:#EFEFEF; border:0;" size="40"
																maxlength="40" /></td>
														</tr>
													</table>
													</td>
												</tr>
											</table>
											</td>

										</tr>
									</table>
									</td>
								</tr>
								<tr>
									<td height="31">
									<table width="100%" border="0" align="center">
										<tr bgcolor="#cbe5fe">
											<td align="center" colspan="2">
											<table width="100%" border="0" bgcolor="#99CCFF">

												<tr bgcolor="#99CCFF">
													<td height="18" colspan="2">
													<div align="center"><span class="style2"> Dados da
													Liga&ccedil;&atilde;o </span></div>
													</td>
												</tr>
												<tr bgcolor="#cbe5fe">
													<td>
													<table border="0" width="100%">
														<tr>

															<td width="42%" height="10"><strong>Data da
															Liga&ccedil;&atilde;o:<font
																color="#FF0000">*</font></strong></td>
															<td colspan="2"><strong> <!-- Campo Data da Ligacao -->
															<html:text property="dataLigacao" size="10"
																maxlength="11" onkeyup="mascaraData(this, event);"/>
																<a href="javascript:abrirCalendario('EfetuarLigacaoEsgotoSemRAActionForm', 'dataLigacao');">
      								                             <img src="/gsan/imagens/calendario.gif" alt="Exibir Calendário" tabindex="4" border="0"></a></strong></td>
														</tr>
														<tr>
															<td><strong> Diametro da Liga&ccedil;&atilde;o:<span
																class="style2"><strong><font color="#FF0000">*</font></strong></span></strong></td>
															<td colspan="2"><strong> <!--Campo Seleção Diametro de Ligação -->
															<html:select property="diametroLigacao"
																style="width: 230px;">
																<logic:present name="colecaoDiametroLigacao"
																	scope="session">
																	<html:option
																		value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
																	<html:options collection="colecaoDiametroLigacao"
																		labelProperty="descricao" property="id" />
																</logic:present>
															</html:select> </strong></td>
														</tr>
														<tr>
															<td class="style3"><strong>Material da
															Liga&ccedil;&atilde;o:<span class="style2"><strong><font
																color="#FF0000">*</font></strong></span></strong></td>
															<td colspan="2"><strong> <!-- Campo Material Ligação -->
															<html:select property="materialLigacao"
																style="width: 230px;">
																<logic:present name="colecaoMaterialLigacao"
																	scope="session">
																	<html:option
																		value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
																	<html:options collection="colecaoMaterialLigacao"
																		labelProperty="descricao" property="id" />
																</logic:present>
															</html:select> </strong></td>
														</tr>
														<tr>
															<td class="style3"><strong>Perfil da
															Liga&ccedil;&atilde;o:<font
																color="#FF0000">*</font></strong></td>
															<td colspan="2"><strong> <html:select
																property="perfilLigacao"
																onchange="redirecionarSubmit('exibirEfetuarLigacaoEsgotoSemRAAction.do');"
																style="width: 230px;">
																<logic:present name="colecaoPerfilLigacao"
																	scope="session">
																	<html:option
																		value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
																	<html:options collection="colecaoPerfilLigacao"
																		labelProperty="descricao" property="id" />
																</logic:present>
															</html:select> </strong></td>

														</tr>

														<logic:equal name="alterarPercentualColetaEsgoto" value="true">
															<tr>
																<td class="style3"><b>Percentual de Coleta:<font color="#ff0000">*</font></b></td>
																<td colspan="2">
																<html:text property="percentualColeta" onkeyup="formataValorMonetario(this, 6);" 
																style="text-align: right;" size="7" maxlength="6" /> %</td>
															</tr>
														</logic:equal>
														
														<logic:notEqual name="alterarPercentualColetaEsgoto" value="true">
															<tr>
																<td class="style3"><b>Percentual de Coleta:<font color="#ff0000">*</font></b></td>
																<td colspan="2">
																	<html:text property="percentualColeta" onkeyup="formataValorMonetario(this, 6);" readonly="true"
																	style="background-color:#EFEFEF; border:0;text-align: right;" size="7" maxlength="6" /> %</td>
															</tr>
														</logic:notEqual>
														
														<tr>
															<td class="style3"><b>Percentual de Esgoto:</b></td>

															<td colspan="2"><html:text property="percentualEsgoto"
																readonly="true"
																style="background-color:#EFEFEF; border:0;text-align: right;"
																size="7" maxlength="6" /> %</td>
														</tr>

														<tr>
															<td><strong>Com Caixa de Gordura?<font color="#FF0000">*</font></strong></td>
															<td><strong> <html:radio property="indicadorCaixaGordura"
																value="1" /> SIM<html:radio
																property="indicadorCaixaGordura" value="2" /> NÃO</strong></td>

														</tr>
														
														<tr>
															<td>
																<strong>Ligação:<font color="#FF0000">*</font></strong>
															</td>
															<td>
																<strong> 
																	<html:radio property="indicadorLigacao" value="2" /> Disponível
																	<html:radio property="indicadorLigacao" value="1" /> Efetivado
																</strong>
															</td>
														</tr>
													</table>
													</td>
												</tr>
											</table>
											</td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							<table width="100%">
								<tr>
									<td colspan="2">
									<table width="100%">

										<tr>
											<td width="40%" align="left">
											   <input type="button" name="ButtonReset"
												class="bottonRightCol" value="Desfazer"
												onClick="limparForm();"> <input type="button"
												name="ButtonCancelar" class="bottonRightCol"
												value="Cancelar"
												onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
											</td>
											<td align="right"><input name="Button" type="button"
												class="bottonRightCol" value="Efetuar"
												onclick="validaForm();"></td>
										</tr>
									</table>
									</td>

								</tr>
								<!--</tr></table></td>-->
							</table>
					</table>
					<!-- Fim do Corpo - Leandro Cavalcanti -->

						<%@ include file="/jsp/util/rodape.jsp"%>

					</html:form>
</body>
</html:html>
