<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="gcom.cadastro.cliente.ClienteImovel"%>
<%@ page import="gcom.cadastro.imovel.ImovelSubcategoria"%>

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="ConsultarImovelActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">

<!-- Begin
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

   	var form = document.forms[0];

    if (tipoConsulta == 'imovel') {
      form.idImovelDadosCadastrais.value = codigoRegistro;
      form.matriculaImovelDadosCadastrais.value = descricaoRegistro;
      form.matriculaImovelDadosCadastrais.style.color = "#000000";
	  form.action = 'consultarImovelWizardAction.do?action=exibirConsultarImovelDadosCadastraisAction&indicadorNovo=OK'
	  form.submit();
    }
    
}

function limparForm(){
   	var form = document.forms[0];
	form.action = 'consultarImovelWizardAction.do?action=exibirConsultarImovelDadosCadastraisAction&limparForm=OK'
	form.submit();
}



function verificaExibicaoRelatorioDadosCadastraisImovel(){
	var form = document.forms[0];
	
	if (form.idImovelDadosCadastrais.value.length > 0 && form.matriculaImovelDadosCadastrais.value.length > 0) {
		toggleBoxCaminho('demodiv',1,'gerarRelatorioDadosCadastraisImovelAction.do');
	} else {
		alert('Informe Imóvel');
	}

}


function habilitaMatricula() {
	var form = document.forms[0];
	
	if (form.idImovelDadosCadastrais.value != null && form.matriculaImovelDadosCadastrais.value != null && form.matriculaImovelDadosCadastrais.value != "" &&
	form.matriculaImovelDadosCadastrais.value != "IMÓVEL INEXISTENTE"){
	
		form.idImovelDadosCadastrais.disabled = true;
	} else {
		form.idImovelDadosCadastrais.disabled = false;
	}
}

function pesquisarImovel() {
	var form = document.forms[0];
 	
 	if (form.idImovelDadosCadastrais.disabled ) {
 		alert("Para realizar uma pesquisa de imóvel é necessário apagar o imóvel atual.")
	} else {
		abrirPopup('exibirPesquisarImovelAction.do', 400, 800)
	}
}



-->

function carregaMaps() {

	var form = document.forms[0];
			  
	var url = "http://vazamento.cosanpa.pa.gov.br/gsan/imovel_mapa.asp?coord="+form.coordenadaXDadosCadastrais.value +","+form.coordenadaYDadosCadastrais.value ;
	
	window.open(url, '_blank' );
	
}


</script>
</head>

<body leftmargin="5" topmargin="5" onload="javascript:habilitaMatricula();setarFoco('idImovelDadosCadastrais')">
<html:form action="/exibirConsultarImovelAction.do"
	name="ConsultarImovelActionForm"
	type="gcom.gui.cadastro.imovel.ConsultarImovelActionForm" method="post"
	onsubmit="return validateConsultarImovelActionForm(this);">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_consulta.jsp?numeroPagina=1" />

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

			<td valign="top" class="centercoltext"><!--Início Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>

					<td class="parabg">&nbsp;</td>

					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
						    <td>
							    <table width="100%" align="center" bgcolor="#99CCFF" border="0">
								    <tr>
									    <td align="left" width="4%">
											<img border="0" width="25" height="25"
												src="<bean:message key="caminho.imagens"/>informacao.gif"
												onmouseover="this.T_BGCOLOR='whitesmoke';this.T_LEFT=true;return escape( '${ConsultarImovelActionForm.hint2}' ); "/>
									    </td>						    						
										<td align="center" width="96%"><strong>Dados do Imóvel<logic:present name="imovelExcluido" scope="request"><font color="#ff0000"> (Excluído)</font></logic:present></strong></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" border="0">
								<tr>
									<td bordercolor="#000000" width="25%"><strong>Im&oacute;vel:<font
										color="#FF0000">*</font></strong></td>
									<td width="75%" colspan="3">
										<html:text property="idImovelDadosCadastrais" 
												   maxlength="9" 
												   size="9"
		   										   onkeypress="validaEnterComMensagem(event, 'consultarImovelWizardAction.do?action=exibirConsultarImovelDadosCadastraisAction&indicadorNovo=OK','idImovelDadosCadastrais','Im&oacute;vel');return isCampoNumerico(event);"
		   										   /> 
									<a
										href="javascript:pesquisarImovel();">
									<img width="23" height="21"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										border="0" title="Pesquisar Imóvel"/></a> <logic:present
										name="idImovelDadosCadastraisNaoEncontrado" scope="request">
										<html:text property="matriculaImovelDadosCadastrais" size="40"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" 
											title="Localidade.Setor.Quadra.Lote.Sublote"/>

									</logic:present> <logic:notPresent
										name="idImovelDadosCadastraisNaoEncontrado" scope="request">
										<logic:present name="valorMatriculaImovelDadosCadastrais"
											scope="request">
											<html:text property="matriculaImovelDadosCadastrais"
												size="40" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" 
												title="Localidade.Setor.Quadra.Lote.Sublote"/>
										</logic:present>
										<logic:notPresent name="valorMatriculaImovelDadosCadastrais"
											scope="request">
											<html:text property="matriculaImovelDadosCadastrais"
												size="40" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" 
												title="Localidade.Setor.Quadra.Lote.Sublote"/>
										</logic:notPresent>
									</logic:notPresent> <a href="javascript:limparForm();"> <img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" /></a></td>

								</tr>
								<tr>
									<td height="10">
									<div class="style9"><strong>Situação de Água:</strong></div>
									</td>
									<td><html:text property="situacaoAguaDadosCadastrais"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="15" maxlength="15" /></td>
									<td width="90"><strong>Situação de Esgoto:</strong></td>
									<td width="120"><html:text
										property="situacaoEsgotoDadosCadastrais" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="15" maxlength="15" /></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
							<td align="center">
							<div class="style9"><strong>Endere&ccedil;o </strong></div>

							</td>
						</tr>
						<tr bgcolor="#FFFFFF">
							<td align="center">
							<div class="style9">${enderecoImovelDadosCadastrais} &nbsp;</div>
							</td>
						</tr>
						<logic:equal name="desabilitaMunicipioLocalidade" value="OK" scope="request">
							<tr>
								<td>
									<strong>Município:</strong>
									<html:text property="descricaoMunicipio" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</td>
							</tr>
						</logic:equal>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%" border="0">
						<tr>
							<td colspan="4">
							<table width="100%" align="center" bgcolor="#90c7fc" border="0">
								<tr bordercolor="#79bbfd">
									<td colspan="5" align="center" bgcolor="#79bbfd"><strong>Clientes</strong></td>
								</tr>
								<tr bordercolor="#000000">
									<td width="28%" bgcolor="#90c7fc" align="center">
									<div class="style9"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Nome do
									Cliente</strong> </font></div>
									</td>
									<td width="17%" bgcolor="#90c7fc" align="center">
									<div class="style9"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo da
									Rela&ccedil;&atilde;o</strong> </font></div>
									</td>
									<td width="19%" bgcolor="#90c7fc" align="center">
									<div class="style9"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Data
									In&iacute;cio Rela&ccedil;&atilde;o</strong></font></div>
									</td>
									<td width="16%" bgcolor="#90c7fc" align="center">
									<div class="style9"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Telefone</strong></font>
									</div>
									</td>
									<td width="20%" bgcolor="#90c7fc" align="center"><font
										color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>CPF/CNPJ</strong>
									</font></td>
								</tr>
								<tr>
									<td width="100%" colspan="5">
									<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%" align="left" bgcolor="#99CCFF">
										<!--corpo da segunda tabela-->
										<%int cont = 0;%>
										<logic:notEmpty name="imovelClientes">
											<logic:iterate name="imovelClientes" id="imovelCliente"
												type="ClienteImovel">
												<%cont = cont + 1;
			if (cont % 2 == 0) {%>
												<tr bgcolor="#cbe5fe">
													<%} else {%>
												<tr bgcolor="#FFFFFF">
													<%}%>
													<td width="28%" bordercolor="#90c7fc" align="left">
													<div class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <logic:present
														name="imovelCliente" property="cliente">
														<a
															href="javascript:abrirPopup('exibirConsultarClienteAction.do?desabilitarPesquisaCliente=SIM&codigoCliente='+<bean:write name="imovelCliente" property="cliente.id" />, 500, 800);">
														<bean:write name="imovelCliente" property="cliente.nome" />
														</a>
													</logic:present> </font></div>
													</td>
													<td width="17%" align="left">
													<div class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <logic:present
														name="imovelCliente" property="clienteRelacaoTipo">
														<bean:write name="imovelCliente"
															property="clienteRelacaoTipo.descricao" />
													</logic:present> </font></div>
													</td>
													<td width="19%" align="center">
													<div class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> </font></div>
													<bean:write name="imovelCliente"
														property="dataInicioRelacao" formatKey="date.format" /></td>

													<td width="16%" align="right"><logic:notEmpty
														name="imovelCliente" property="cliente">
														<bean:define name="imovelCliente" property="cliente"
															id="cliente" />
														<logic:notEmpty name="cliente" property="clienteFones">
															<bean:define name="cliente" property="clienteFones"
																id="clienteFones" />
															<logic:iterate name="clienteFones" id="clienteFone">
																<div class="style9">
																<div align="center"><font color="#000000"
																	style="font-size:9px"
																	face="Verdana, Arial, Helvetica, sans-serif"> </font></div>
																<bean:write name="clienteFone" property="dddTelefone" />
																</div>
															</logic:iterate>
														</logic:notEmpty>
													</logic:notEmpty></td>
													<td width="20%" align="right"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
														name="imovelCliente" property="cliente.cpf">
														<bean:write name="imovelCliente"
															property="cliente.cpfFormatado" />
													</logic:notEmpty> <logic:notEmpty name="imovelCliente"
														property="cliente.cnpj">
														<bean:write name="imovelCliente"
															property="cliente.cnpjFormatado" />
													</logic:notEmpty> </font></td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
									</table>
									</div>
									</td>
								</tr>

							</table>
							</td>
						</tr>
						<%int quantidadeTotalEconomias = 0;

			%>

						<tr>
							<td colspan="4">
							<table width="100%" align="center" bgcolor="#90c7fc" border="0">
								<tr bordercolor="#79bbfd">
									<td colspan="3" align="center" bgcolor="#79bbfd"><strong>Categorias,
									Subcategorias e Economias</strong></td>
								</tr>
								<tr bordercolor="#000000">
									<td width="19%" bgcolor="#90c7fc" align="center">
									<div class="style9"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Categoria</strong>
									</font></div>
									</td>
									<td width="56%" bgcolor="#90c7fc" align="center">
									<div class="style9"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Subcategoria</strong></font>
									</div>
									</td>
									<td width="25%" bgcolor="#90c7fc" align="center">
									<div class="style9"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Quantidade
									de Economias</strong> </font></div>
									</td>
								</tr>

								<tr>
									<td width="100%" colspan="3">
									<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%" align="left" bgcolor="#99CCFF">
										<!--corpo da segunda tabela-->
										<%cont = 0;%>
										<logic:notEmpty name="imovelSubcategorias">
											<logic:iterate name="imovelSubcategorias"
												id="imovelSubcategoria" type="ImovelSubcategoria">
												<%cont = cont + 1;
			if (cont % 2 == 0) {%>
												<tr bgcolor="#cbe5fe">
													<%} else {%>
												<tr bgcolor="#FFFFFF">
													<%}%>

													<td width="19%" align="left">
													<div align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <logic:present
														name="imovelSubcategoria" property="comp_id">

														<bean:define name="imovelSubcategoria" property="comp_id"
															id="comp_id" />
														<logic:present name="comp_id" property="subcategoria">


															<bean:define name="comp_id" property="subcategoria"
																id="subcategoria" />

															<logic:present name="subcategoria" property="categoria">

																<bean:define name="subcategoria" property="categoria"
																	id="categoria" />
																<bean:write name="categoria" property="descricao" />
															</logic:present>
														</logic:present>
													</logic:present> </font></div>
													</td>
													<td width="56%" align="left">
													<div align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <logic:present
														name="imovelSubcategoria" property="comp_id">

														<bean:define name="imovelSubcategoria" property="comp_id"
															id="comp_id" />
														<logic:present name="comp_id" property="subcategoria">
															<bean:define name="comp_id" property="subcategoria"
																id="subcategoria" />
															<bean:write name="subcategoria" property="descricao" />
														</logic:present>
													</logic:present> </font></div>
													</td>
													<td width="25%" align="right">
													<div align="right"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <logic:present
														name="imovelSubcategoria" property="quantidadeEconomias">
														<bean:write name="imovelSubcategoria"
															property="quantidadeEconomias" />
													</logic:present> </font></div>
													</td>


													<%quantidadeTotalEconomias = ((ImovelSubcategoria) imovelSubcategoria)
					.getQuantidadeEconomias()
					+ quantidadeTotalEconomias;

			%>


												</tr>
											</logic:iterate>
										</logic:notEmpty>
									</table>
									</div>
									</td>
								</tr>

								<tr bgcolor="#FFFFFF">
									<td width="19%" bgcolor="#90c7fc" align="center">
									<div class="style9" align="center"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total de
									Economias</strong></font></div>
									</td>
									<td width="54%" align="left">&nbsp;</td>
									<td width="27%" align="right">
									<div align="right"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <%=quantidadeTotalEconomias%></font></div>
									</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td width="28%" height="10" align="left">
							<div class="style9"><strong>Perfil do Imóvel:</strong></div>
							</td>
							<td width="22%" align="left">
							<div class="style9"><html:text
								property="imovelPerfilDadosCadastrais" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></div>
							</td>
							<td width="32%" align="left"><strong>Tipo de Despejo</strong></td>
							<td width="18%" align="left"><html:text
								property="despejoDadosCadastrais" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></td>
						</tr>
						<tr>
							<td align="left"><strong>&Aacute;rea Constru&iacute;da:</strong></td>
							<td align="left"><html:text
								property="areaConstruidaDadosDadosCadastrais" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></td>

							<td align="left"><strong>Testada do Lote:</strong></td>
							<td align="left"><html:text property="testadaLoteDadosCadastrais"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></td>
						</tr>
						<tr>
							<td height="10">
							<div class="style9"><strong>Vol. Reservat&oacute;rio Inferior: </strong>
							</div>
							</td>
							<td><html:text
								property="volumeReservatorioInferiorDadosCadastrais"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></td>
							<td><strong>Vol. Reservat&oacute;rio Superior:</strong></td>
							<td><html:text
								property="volumeReservatorioSuperiorDadosCadastrais"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></td>
						</tr>
						<tr>
							<td height="10" align="left">
							<div class="style9"><strong>Volume da Piscina:</strong></div>
							</td>
							<td align="left">
							<div class="style9"><html:text
								property="volumePiscinaDadosCadastrais" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></div>
							</td>
							<td align="left"><strong>Fonte de Abastecimento:</strong></td>
							<td align="left"><html:text
								property="fonteAbastecimentoDadosCadastrais" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></td>
						</tr>
						<tr>
							<td align="left"><strong>Po&ccedil;o:</strong></td>
							<td colspan="3" align="left">
								<html:text property="pocoTipoDadosCadastrais" 
										   readonly="true"
										   style="background-color:#EFEFEF; border:0; color: #000000"
 										   size="20" 
 										   maxlength="20" 
 										   />
							</td>
						</tr>
						<tr>
							<td height="10" align="left">
							<div class="style9"><strong>Distrito de Abastecimento:</strong></div>
							</td>
							<td align="left">
							<div class="style9"><html:text
								property="distritoOperacionalDadosCadastrais" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></div>
							</td>
							<td align="left"><strong>Divisão de Esgoto:</strong></td>
							<td align="left"><html:text
								property="divisaoEsgotoDadosCadastrais" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></td>
						</tr>
						<tr>
							<td height="21" align="left">
							<div class="style9"><strong>Pavimento de Rua:</strong></div>
							</td>
							<td align="left">
							<div class="style9"><html:text
								property="pavimentoRuaDadosCadastrais" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></div>
							</td>
							<td align="left"><strong>Pavimento de Cal&ccedil;ada:</strong></td>
							<td align="left"><html:text
								property="pavimentoCalcadaDadosCadastrais" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></td>
						</tr>
						<tr>
							<td height="10" align="left">
							<div class="style9"><strong>Pontos Utiliza&ccedil;&atilde;o </strong></div>
							</td>
							<td align="left">
							<div class="style9"><html:text
								property="numeroPontosUtilizacaoDadosCadastrais" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></div>
							</td>
							<td align="left"><strong>N&uacute;mero de Moradores:</strong></td>
							<td align="left"><html:text
								property="numeroMoradoresDadosCadastrais" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></td>
						</tr>
						<tr>
							<td height="10" align="left">
							<div class="style9"><strong>N&uacute;mero do IPTU:</strong></div>
							</td>
							<td align="left">
							<div class="style9"><html:text
								property="numeroIptuDadosCadastrais" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></div>
							</td>
							<td align="left"><strong>Contrato Comp. Energia:</strong></td>
							<td align="left"><html:text property="numeroCelpeDadosCadastrais"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></td>
						</tr>
						<tr>
							<td height="10" align="left">
							<div class="style9"><strong>Latitude:</strong></div>
							</td>
							<td align="left">
							<div class="style9"><html:text
								property="coordenadaXDadosCadastrais" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></div>
							</td>
							<td align="left"><strong>Longitude:</strong></td>
							<td align="left"><html:text property="coordenadaYDadosCadastrais"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></td>
						</tr>
						<tr>
							<td height="10" align="left">
							<div class="style9"><strong>Ocorr&ecirc;ncia de Cadastro:</strong></div>
							</td>
							<td colspan="2" align="left">
							<div class="style9"><html:text
								property="cadastroOcorrenciaDadosCadastrais" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></div>
							</td>
							<td align="left">
							<a href="javascript:carregaMaps();">
							<img border="0" src="<bean:message key="caminho.imagens"/>googlemaps.gif"
												title="Buscar coordenadas" /></a>
							</td>
						</tr>
						<tr>
							<td align="left"><strong>Anormalidade de Localidade Pólo:</strong></td>
							<td height="10" colspan="3" align="left"><html:text
								property="eloAnormalidadeDadosCadastrais" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></td>
						</tr>
						<tr>
							<td align="left"><strong> Im&oacute;vel Condom&iacute;nio</strong></td>
							<td align="left"><html:text
								property="indicadorImovelCondominioDadosCadastrais"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></td>
							<td height="10" align="left">
							<div class="style9"><strong>Matr&iacute;cula Im&oacute;vel
							Condom&iacute;nio:</strong></div>
							</td>
							<td align="left">
							<div class="style9"><html:text
								property="imovelCondominioDadosCadastrais" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></div>
							</td>
						</tr>
						<tr>
							<td height="10" align="left">
							<div class="style9"><strong>Matr&iacute;cula Im&oacute;vel
							Principal:</strong></div>
							</td>
							<td align="left">
							<div class="style9"><html:text
								property="imovelPrincipalDadosCadastrais" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></div>
							</td>
						  	<logic:present name="apresentarIndicadorNivelInstalacaoEsgoto" scope="request">
									<td height="10" align="left">
									<div class="style9"><strong>Existe n&iacute;vel para instala&ccedil;&atilde;o de esgoto:</strong></div>
									</td>
									<td align="left">
							 			<div class="style9"><html:text
											 property="indicadorNivelInstalacaoEsgotoDadosCadastrais" readonly="true"
											 style="background-color:#EFEFEF; border:0; color: #000000"
											 size="20" maxlength="20" />
										</div>
									</td> 
							</logic:present>
						</tr>
						<tr>
							<td height="10" align="left">
							<div class="style9"><strong>Jardim:</strong></div>
							</td>
							<td align="left">
							<div class="style9"><html:text
								property="jardimDadosCadastrais" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></div>
							</td>
							<td height="10" align="left">
							<div class="style9"><strong>Tipo de Habita&ccedil;&atilde;o:</strong></div>
							</td>
							<td align="left">
							<div class="style9"><html:text
								property="tipoHabitacaoDadosCadastrais" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></div>
							</td>
						</tr>
						<tr>
							<td height="10" align="left">
							<div class="style9"><strong>Tipo de Propriedade:</strong></div>
							</td>
							<td align="left">
							<div class="style9"><html:text
								property="tipoPropriedadeDadosCadastrais" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></div>
							</td>
							<td height="10" align="left">
							<div class="style9"><strong>Tipo de Constru&ccedil;&atilde;o:</strong></div>
							</td>
							<td align="left">
							<div class="style9"><html:text
								property="tipoConstrucaoDadosCadastrais" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></div>
							</td>
						</tr>
						<tr>
							<td height="10" align="left">
							<div class="style9"><strong>Tipo de Cobertura:</strong></div>
							</td>
							<td align="left">
							<div class="style9"><html:text
								property="tipoCoberturaDadosCadastrais" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></div>
							</td>
						</tr>
						<tr>
							<td height="10" align="left">
								<div class="style9"><strong>Data geração comunicado faturamento:</strong></div>
							</td>
							<td align="left">
								<div class="style9">
									<html:text property="dataEmissaoComunicadoIrregularidadeFaturamento" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									size="20" maxlength="20" />
								</div>
							</td>
						</tr>
						<tr>
							<td height="10" align="left">
							<div class="style9"><strong>Data de Processamento:</strong></div>
							</td>
							<td align="left">
							<div class="style9"><html:text
								property="dataProcessamento" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="20" maxlength="20" /></div>
							</td>
						</tr>
							<tr>
							<td height="10" align="left">
							<div class="style9"><strong>Oberva&ccedil;&atilde;o Categoria:</strong></div>
							</td>
							<td align="left">
							<div class="style9"><html:text
								property="observacaoCategoriaDadosCadastrais" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="40" maxlength="200" /></div>
							</td>
						</tr>
					</table>
						<tr>
							<td align="left" width="100%">
								  <div align="right">
									   <a href="javascript:verificaExibicaoRelatorioDadosCadastraisImovel();">
											<img border="0" src="<bean:message key="caminho.imagens"/>print.gif"
												title="Imprimir Dados Cadastrais" /> 
										</a>
								  </div>
							</td>
						</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_consulta.jsp?numeroPagina=1" /></div>
					</td>
				</tr>
			</table>

			</td>
		</tr>
	</table>
	<p>&nbsp;</p>

	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioDadosCadastraisImovelAction.do"/>
	<%@ include file="/jsp/util/rodape.jsp"%>
	<%@ include file="/jsp/util/tooltip.jsp"%>
</html:form>
</body>
</html:html>
