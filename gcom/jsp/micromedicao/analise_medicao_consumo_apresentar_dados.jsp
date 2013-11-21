<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.micromedicao.medicao.MedicaoTipo" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="LeituraConsumoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<style>
.styleFontePequena{font-size:9px;
                   color: #000000;
				   font:Verdana, Arial, Helvetica, sans-serif}
.styleFontePeqNegrito{font-size:11px;
                   color: #000000;
				   font-weight: bold}
.styleFonteTabelaPrincipal{font-size:12px;
                   color: #FFFFFF;
				   background-color: #5782E6;
				   font-weight: bold}
.styleFonteMenorNegrito{font-size:10px;
                   color: #000000;
				   font-weight: bold}
.buttonAbaRodaPe {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 13px;
	width:100px;
	background-color: #55A8FB;
	border-top: 1px outset #FFFFFF;
	border-right: 1px outset #000099;
	border-bottom: 1px outset #000099;
	border-left: 1px outset #FFFFFF;
	text-transform: none;
</style>
<script>
function imovelAnterior(){
 var form = document.forms[0];
 form.action = "exibirDadosAnaliseMedicaoConsumoAction.do?imovelAnterior=1";
 submeterFormPadrao(form);
}
function proximoImovel(){
 var form = document.forms[0];
 form.action = "exibirDadosAnaliseMedicaoConsumoAction.do?proximoImovel=1";
 submeterFormPadrao(form);
}
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form action="/exibirDadosAnaliseMedicaoConsumoAction.do"
	name="LeituraConsumoActionForm"
	type="gcom.gui.micromedicao.LeituraConsumoActionForm" method="post">

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


			<td width="600" valign="top" class="centercoltext">

			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">An&aacute;lise da Medi&ccedil;&atilde;o e
					Consumo do M&ecirc;s</td>

					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=faturamentoAnaliseMedicaoConsumoApresentarDados', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>								      
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo--> <!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<table width="100%" border="0">
				<logic:present name="sucesso">
					<tr>
						<td colspan="6">
						<table>
							<tr>
								<td><img height="20" width="21"
									src="<bean:message key="caminho.imagens"/>sucesso.gif"></td>
								<td><strong>${requestScope.sucesso}</strong></td>
							</tr>
						</table>
						</td>
					</tr>
				</logic:present>

				<tr>
					<td>
					<table width="100%" bgcolor="#99CCFF">

						<!--header da tabela interna -->
						<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
							<td width="25%">
							<div align="center">Grupo de Faturamento</div>
							</td>

							<td width="25%">
							<div align="center">M&ecirc;s e Ano do Faturamento</div>
							</td>
							<td width="18%">
							<div align="center">Empresa Leiturista</div>
							</td>
							<td width="15%">
							<div align="center">Rota</div>
							</td>
							<td width="15%">
							<div align="center">Seq. Rota</div>
							</td>

						</tr>
						<tr bgcolor="#FFFFFF" class="styleFontePequena">
							<td>
							<div align="center">${sessionScope.leituraConsumoActionForm.grupoFaturamento}
							&nbsp;</div>

							</td>
							<td>
							<div align="center">${sessionScope.leituraConsumoActionForm.mesAnoFaturamentoCorrente}
							&nbsp;</div>
							</td>

							<td>
							<div align="center">${sessionScope.leituraConsumoActionForm.empresaLeitura}
							&nbsp;</div>
							</td>
							<td>
							<div align="center">${sessionScope.leituraConsumoActionForm.rota}
							&nbsp;</div>
							</td>

							<td>
							<div align="center">${sessionScope.leituraConsumoActionForm.seqRota}
							&nbsp;</div>
							</td>
						</tr>

					</table>
				<tr>
					<td>
					<table width="100%" bgcolor="#99CCFF">

						<tr class="styleFonteTabelaPrincipal">
							<td colspan="4" class="styleFonteTabelaPrincipal" align="center">Dados
							do Im&oacute;vel e do Cliente Usu&aacute;rio</td>
						</tr>

						<!--header da tabela interna -->
						<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
							<td width="24%" align="center">Inscri&ccedil;&atilde;o</td>

							<td width="17%" align="center">Matr&iacute;cula Im&oacute;vel</td>
							<td width="27%" align="center">Im&oacute;vel Condom&iacute;no</td>
							<td width="32%" align="center">Matr&iacute;cula do Im&oacute;vel
							Condom&iacute;nio</td>

						</tr>
						<tr bgcolor="#FFFFFF" class="styleFontePequena">
							<td align="center">${sessionScope.leituraConsumoActionForm.inscricaoImovel}
							&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.imovel}
							&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.indicadorImovelCondominio}
							&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.imovelCondominio}&nbsp;</td>
						</tr>

						<tr bgcolor="#79BBFD" class="styleFontePeqNegrito">
							<td colspan="4" width="90%" bgcolor="#99CCFF" align="center">
							Endere&ccedil;o do Im&oacute;vel</td>

						</tr>
						<tr bgcolor="#FFFFFF" class="styleFontePequena">
							<td width="90%" colspan="4" align="center">${sessionScope.leituraConsumoActionForm.enderecoFormatado}&nbsp;</td>
						</tr>
						<tr>
							<td colspan="4">
							<table width="100%" bgcolor="#99CCFF" cellpadding="0"
								class="styleFontePeqNegrito" border="0">

								<!--header da tabela interna -->
								<tr bgcolor="#99CCFF">
									<td width="22%" bgcolor="#99CCFF" align="center">
									Situa&ccedil;&atilde;o de &Aacute;gua</td>

									<td width="24%" align="center">Situa&ccedil;&atilde;o de Esgoto
									</td>
								</tr>

								<tr bgcolor="#FFFFFF" class="styleFontePequena">
									<td align="center">${sessionScope.leituraConsumoActionForm.ligacaoEsgotoSituacao}&nbsp;</td>
									<td align="center">${sessionScope.leituraConsumoActionForm.ligacaoAguaSituacao}&nbsp;</td>
								</tr>
							</table>
							</td>

						</tr>
						<tr>
							<td colspan="4">
							<table width="100%" bgcolor="#99CCFF" border="0">
								<!--header da tabela interna -->
								<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
									<td width="39%" align="center">Nome do Cliente Usu&aacute;rio</td>
									<td width="40%" align="center">CPF/CNPJ</td>
									<td width="21%" align="center">Perfil do Im&oacute;vel</td>
								</tr>
								<tr bgcolor="#FFFFFF" class="styleFontePequena">
									<td height="14" align="center">${sessionScope.leituraConsumoActionForm.clienteNome}&nbsp;</td>
									<td align="center">${sessionScope.leituraConsumoActionForm.clienteCpfCnpj}&nbsp;</td>
									<td align="center">${sessionScope.leituraConsumoActionForm.perfilImovel}&nbsp;</td>
								</tr>
							</table>
							<table width="100%" bgcolor="#99CCFF" border="0">

								<!--header da tabela interna -->
								<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
									<td width="39%" align="center">Categoria</td>
									<td width="40%" align="center">Subcategoria</td>
									<td width="21%" align="center">Quantidade</td>

								</tr>
								<tr bordercolor="#99CCFF">
									<td colspan="3">
									<div style="width: 100%; height: 30; overflow: auto;">
									<table width="100%" align="center" bgcolor="#99CCFF">
										<!--corpo da segunda tabela-->
										<%int cont = 0;%>
										<logic:notEmpty name="colecaoImovelSubcategoria">

											<logic:iterate name="colecaoImovelSubcategoria"
												id="imovelSubcategoria" scope="session">
												<%cont = cont + 1;
							if (cont % 2 == 0) {%>
												<tr bgcolor="#FFFFFF" class="styleFontePequena">

													<%} else {

							%>
												<tr bgcolor="#FFFFFF" class="styleFontePequena">
												
													<%}%>
													<td width="39%" align="center">
													${imovelSubcategoria.comp_id.subcategoria.categoria.descricao}
													&nbsp;</td>
													<td width="40%" align="center">
													${imovelSubcategoria.comp_id.subcategoria.descricao}</td>
													<td width="21%" align="center">
													${imovelSubcategoria.quantidadeEconomias} &nbsp;</td>
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
					</table>

					</td>

				</tr>
				<tr>
					<td>

					<table width="100%" bgcolor="#99CCFF">
						<tr>
							<td colspan="4" class="styleFonteTabelaPrincipal" align="center">

							Dados do Hidr&ocirc;metro</td>

						</tr>
						<!--header da tabela interna -->
						<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
							<td width="25%" align="center">Tipo de Medi&ccedil;&atilde;o</td>

							<td width="26%" align="center">Hidr&ocirc;metro</td>
							<td width="22%" align="center">Data de Instala&ccedil;&atilde;o</td>
							<td width="27%" align="center">Capacidade</td>
						</tr>

						<tr bgcolor="#FFFFFF" class="styleFontePequena">
							<td align="center">${sessionScope.leituraConsumoActionForm.tipoMedicao}
							&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.numeroHidrometro}
							&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.instalacaoHidrometro}
							&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.capacidadeHidrometro}
							&nbsp;</td>
						</tr>
						<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
							<td align="center">Tipo de Hidr&ocirc;metro</td>
							<td align="center">Marca</td>
							<td align="center">Local de Instala&ccedil;&atilde;o</td>
							<td align="center">Di&acirc;metro</td>

						</tr>
						<tr bgcolor="#FFFFFF" class="styleFontePequena">
							<td align="center">${sessionScope.leituraConsumoActionForm.tipoHidrometro}
							&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.marcaHidrometro}
							&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.localInstalacaoHidrometro}&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.diametroHidrometro}
							&nbsp;</td>
						</tr>

						<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
							<td colspan="2" align="center">Prote&ccedil;&atilde;o</td>
							<td align="center">Indicador de Cavalete</td>
							<td align="center">Ano de Fabrica&ccedil;&atilde;o</td>

						</tr>

						<tr bgcolor="#FFFFFF" class="styleFontePequena">
							<td colspan="2" align="center">${sessionScope.leituraConsumoActionForm.protecaoHidrometro}
							&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.indicadorCavalete}
							&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.anoFabricacao}
							&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>

					<table width="100%" bgcolor="#99CCFF">
						<tr>
							<td colspan="5" class="styleFonteTabelaPrincipal" align="center">

							Dados da Ligação de Água</td>

						</tr>
						<!--header da tabela interna -->
						<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
							<td width="20%" align="center">Data da Ligação</td>
							<td width="20%" align="center">Data de Corte</td>
							<td width="20%" align="center">Data da Religação</td>
							<td width="20%" align="center">Data da Supressão</td>
							<td width="20%" align="center">Data do Restabelecimento</td>
						</tr>

						<tr bgcolor="#FFFFFF" class="styleFontePequena">
							<td align="center">${sessionScope.leituraConsumoActionForm.dataLigacaoAgua}
							&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.dataCorteAgua}
							&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.dataReligacaoAgua}
							&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.dataSupressaoAgua}
							&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.dataRestabelecimentoAgua}
							&nbsp;</td>
						</tr>

						<tr>
							<td colspan="5">

							<table width="100%" bgcolor="#99CCFF">
								<!--header da tabela interna -->
								<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
									<td align="center" width="27%">Diametro</td>
									<td align="center" width="27%">Material</td>
									<td align="center" width="27%">Perfil de Ligação</td>
									<td align="center" width="19%">Consumo Mínimo</td>

								</tr>
								<tr bgcolor="#FFFFFF" class="styleFontePequena">
									<td align="center">${sessionScope.leituraConsumoActionForm.descricaoLigacaoAguaDiametro}
									&nbsp;</td>
									<td align="center">${sessionScope.leituraConsumoActionForm.descricaoLigacaoAguaMaterial}
									&nbsp;</td>
									<td align="center">${sessionScope.leituraConsumoActionForm.descricaoligacaoAguaPerfil}&nbsp;</td>
									<td align="center">${sessionScope.leituraConsumoActionForm.numeroConsumominimoAgua}&nbsp;</td>
								</tr>
							</table>
							</td>

						</tr>

					</table>
					</td>
				</tr>
				<tr>
					<td>

					<table width="100%" bgcolor="#99CCFF">
						<tr>
							<td colspan="4" class="styleFonteTabelaPrincipal" align="center">

							Dados da Ligação de Esgoto</td>

						</tr>
						<!--header da tabela interna -->
						<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
							<td width="19%" align="center">Data da Ligação</td>
							<td align="center" width="27%">Diametro</td>
							<td align="center" width="27%">Material</td>
							<td align="center" width="27%">Perfil de Ligação</td>
						</tr>

						<tr bgcolor="#FFFFFF" class="styleFontePequena">
							<td align="center">${sessionScope.leituraConsumoActionForm.dataLigacaoEsgoto}
							&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.descricaoLigacaoEsgotoDiametro}
							&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.descricaoLigacaoEsgotoMaterial}
							&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.descricaoligacaoEsgotoPerfil}
							&nbsp;</td>
						</tr>
						<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
							<td align="center" width="20%">Consumo Minimo</td>
							<td align="center" width="15%">Percentual de Esgoto</td>
							<td align="center" width="15%">Percentual de Coleta</td>
							<td align="center" width="50%">Indicador de Poço</td>

						</tr>
						<tr bgcolor="#FFFFFF" class="styleFontePequena">
							<td align="center">${sessionScope.leituraConsumoActionForm.numeroConsumominimoEsgoto}
							&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.percentualEsgoto}
							&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.percentualAguaConsumidaColetada}&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.descricaoPocoTipo}&nbsp;</td>
						</tr>

					</table>
					</td>
				</tr>
				<tr>
					<td>
					<table width="100%" bgcolor="#99CCFF">

						<tr>
							<td colspan="6" class="styleFonteTabelaPrincipal" align="center">
							Dados da Medi&ccedil;&atilde;o do M&ecirc;s</td>
						</tr>
						<!--header da tabela interna -->
						<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
							<td width="21%" align="center">Dt. Leitura Ant.</td>
							<td width="15%" align="center">Leitura Anter.</td>
							<td width="17%" align="center">Dt. Leitura Inf.</td>
							<td width="11%" align="center">Leitura Inf.</td>
							<td width="20%" align="center">Dt. Leitura Fat.</td>

							<td width="16%" align="center">Leitura Fat.</td>
						</tr>
						<tr bgcolor="#FFFFFF" class="styleFontePequena">
							<td align="center">${sessionScope.leituraConsumoActionForm.dtLeituraAnterior}
							&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.leituraAnterior}
							&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.dtLeituraInformada}
							&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.leituraAtualInformada}
							&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.dtLeituraFaturada}
							&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.leituraAtualFaturada}
							&nbsp;</td>
						</tr>
						<tr>
							<td colspan="6">
							<table>
								<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
									<td width="20%" align="center">Situa&ccedil;&atilde;o da
									Leitura Atual</td>
									<td width="10%" align="center">Funcion&aacute;rio</td>
									<td width="25%" align="center">Anormalidade de Leitura Inf.</td>
									<td width="25%" align="center">Anormalidade Leitura Fat.</td>
									<td width="20%" align="center">Consumo Médio Hidr&ocirc;metro</td>
								</tr>
								<tr bgcolor="#FFFFFF" class="styleFontePequena">
									<td align="center">${sessionScope.leituraConsumoActionForm.situacaoLeituraAtual}
									&nbsp;</td>
									<td align="center">${sessionScope.leituraConsumoActionForm.codigoFuncionario}
									&nbsp;</td>
									<td align="center">${sessionScope.leituraConsumoActionForm.anormalidadeLeituraInformada}&nbsp;</td>
									<td align="center">${sessionScope.leituraConsumoActionForm.anormalidadeLeituraFaturada}&nbsp;</td>
									<td align="center">${sessionScope.leituraConsumoActionForm.consumoMedioHidrometro}&nbsp;</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>
					<table width="100%" bgcolor="#99CCFF">
						<tr>
							<td colspan="4" class="styleFonteTabelaPrincipal">

							<div align="center">Dados do Consumo do M&ecirc;s</div>
							</td>

						</tr>

						<!--header da tabela interna -->
						<logic:notPresent name="habilitaConsumoEsgoto">
							<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
								<td width="20%" align="center">Consumo Medido</td>
								<td width="22%" align="center">Consumo Faturado</td>

								<td width="24%" align="center">Consumo do Rateio</td>
								<td width="34%" align="center">Consumo M&eacute;dio do Imóvel</td>
							</tr>
							<tr bgcolor="#FFFFFF" class="styleFontePequena">
								<td align="center">${sessionScope.leituraConsumoActionForm.consumoMedido}
								&nbsp;</td>
								<td align="center">${sessionScope.leituraConsumoActionForm.consumoFaturado}
								&nbsp;</td>
								<td align="center">${sessionScope.leituraConsumoActionForm.consumoRateio}
								&nbsp;</td>
								<td align="center">${sessionScope.leituraConsumoActionForm.consumoMedioImovel}
								&nbsp;</td>
							</tr>
						</logic:notPresent>
						<logic:present name="habilitaConsumoEsgoto">
								<tr>
								<td colspan="4">
								<table width="100%" bgcolor="#99CCFF">
									<!--header da tabela interna -->

									<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
										<td width="20%" align="center">Consumo Medido</td>
										<td width="20%" align="center">Consumo Faturado</td>

										<td width="20%" align="center">Consumo do Rateio</td>
										<td width="25%" align="center">Consumo M&eacute;dio do Imóvel</td>
										<td width="15%" align="center">Volume Esgoto</td>
									</tr>
									<tr bgcolor="#FFFFFF" class="styleFontePequena">
										<td align="center">${sessionScope.leituraConsumoActionForm.consumoMedido}
										&nbsp;</td>
										<td align="center">${sessionScope.leituraConsumoActionForm.consumoFaturado}
										&nbsp;</td>
										<td align="center">${sessionScope.leituraConsumoActionForm.consumoRateio}
										&nbsp;</td>
										<td align="center">${sessionScope.leituraConsumoActionForm.consumoMedioImovel}
										&nbsp;</td>
										<td align="center">${sessionScope.leituraConsumoActionForm.consumoMesEsgoto}
										&nbsp;</td>
									</tr>
								</table>

								</td>
							</tr>
						</logic:present>
						<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
							<td width="29%" align="center">Anormalidade de Consumo</td>
							<td width="26%" align="center">Percentual de
							Varia&ccedil;&atilde;o</td>
							<td width="22%" align="center">Dias de Consumo</td>

							<td width="23%" align="center">Tipo de Consumo</td>
						</tr>
						<tr bgcolor="#FFFFFF" class="styleFontePequena">
							<td align="center">${sessionScope.leituraConsumoActionForm.anormalidadeConsumo}
							&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.percentualVariacao}
							&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.diasConsumo}
							&nbsp;</td>
							<td align="center">${sessionScope.leituraConsumoActionForm.consumoTipo}
							&nbsp;</td>
						</tr>

					</table>
					</td>
				</tr>

				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td colspan="8" class="styleFonteTabelaPrincipal" align="center">

							Hist&oacute;rico de Medi&ccedil;&atilde;o</td>
						</tr>

						<tr bgcolor="#90c7fc" class="styleFontePeqNegrito">
							<td width="10%" align="center">M&ecirc;s e Ano</td>
							<td width="13%" align="center">Dt. Leitura Informada</td>
							<td width="10%" align="center">Leitura. Informada</td>
							<td width="13%" align="center">Dt. Leitura Faturada</td>
							<td width="12%" align="center">Leitura Faturada</td>

							<td width="15%" align="center">Anormalidade Informada</td>
							<td width="12%" align="center">Anormalidade Faturada</td>
							<td width="17%" align="center">Sit. Leit. Atual</td>
						</tr>
						<tr bordercolor="#90c7fc">
							<td colspan="8" height="50">
							<div style="width: 100%; height: 100%; overflow: auto;">
							<table width="100%" align="left" bgcolor="#90c7fc">
								<!--corpo da segunda tabela-->
								<%cont = 0;%>
								<logic:notEmpty name="medicoesHistoricos">

									<logic:iterate name="medicoesHistoricos" id="medicaoHistorico">
										<%cont = cont + 1;
							if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe" class="styleFontePequena">
											<%} else {

							%>
										<tr bgcolor="#FFFFFF" class="styleFontePequena">
											<%}%>
											<td width="10%" align="center"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif">
											${medicaoHistorico.mesAno} &nbsp;</font></td>
											<td width="13%" align="center"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
												name="medicaoHistorico" property="dataLeituraAtualInformada"
												formatKey="date.format" /> </font></td>
											<td width="11%" align="center"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif">
											${medicaoHistorico.leituraAtualInformada} &nbsp;</font></td>
											<td width="13%" align="center"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
												name="medicaoHistorico"
												property="dataLeituraAtualFaturamento"
												formatKey="date.format" /> </font></td>

											<td width="11%" align="center"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif">
											${medicaoHistorico.leituraAtualFaturamento} &nbsp;</font></td>
											<td width="15%"><font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif">
											${medicaoHistorico.leituraAnormalidadeInformada.descricao}
											&nbsp;</font></td>
											<td width="14%"><font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif">
											${medicaoHistorico.leituraAnormalidadeFaturamento.descricao}
											&nbsp; </font></td>
											<td width="14%"><font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif">
											${medicaoHistorico.leituraSituacaoAtual.descricao} &nbsp;</font>
											</td>
										</tr>
									</logic:iterate>
								</logic:notEmpty>
							</table>
							</div>
							</td>
						</tr>
					</table>
					</td>
				<tr>
				<tr>
					<td><logic:notPresent name="habilitaConsumoEsgoto">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
	
								<tr>
									<td colspan="6" class="styleFonteTabelaPrincipal" align="center">
									Hist&oacute;rico de Consumo</td>
	
								</tr>
	
								<tr bgcolor="#90c7fc" class="styleFontePeqNegrito">
									<td width="16%" align="center">M&ecirc;s e Ano</td>
	
									<td width="15%" align="center">Consumo Medido</td>
									<td width="17%" align="center">Consumo Faturado</td>
									<td width="13%" align="center">Anormalidade Consumo</td>
									<td width="10%" align="center">Dias de Consumo</td>
									<td width="29%" align="center">Tipo de Consumo</td>
								</tr>
								<tr bordercolor="#90c7fc">
									<td colspan="6">
									<div style="width: 100%; height: 50; overflow: auto;">
									<table width="100%" align="left" bgcolor="#90c7fc">
										<!--corpo da segunda tabela-->
										<%cont = 0;%>
										<logic:notEmpty name="imoveisMicromedicao">
	
											<logic:iterate name="imoveisMicromedicao"
												id="imovelMicromedicao">
												<%cont = cont + 1;
									if (cont % 2 == 0) {%>
												<tr bgcolor="#FFFFFF" class="styleFontePequena">
													<%} else {
	
									%>
												<tr bgcolor="#cbe5fe" class="styleFontePequena">												
													<%}%>
													<td width="16%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
													${imovelMicromedicao.consumoHistorico.mesAno} &nbsp;</font></td>
													<td width="15%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
													${imovelMicromedicao.medicaoHistorico.numeroConsumoMes}
													&nbsp;</font></td>
													<td width="17%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
													${imovelMicromedicao.consumoHistorico.numeroConsumoFaturadoMes}
													&nbsp;</font></td>
													<td width="13%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
													${imovelMicromedicao.consumoHistorico.consumoAnormalidade.descricaoAbreviada}
													&nbsp;</font></td>
													<td width="10%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
													${imovelMicromedicao.qtdDias}&nbsp;</font></td>
													<td width="29%"><font color="#000000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
													${imovelMicromedicao.consumoHistorico.consumoTipo.descricao}
													&nbsp;</font></td>
												</tr>
											</logic:iterate>
									</logic:notEmpty>
								</table>
								</div>
								</td>
							</tr>
						</table>
					</logic:notPresent>
					<logic:present name="habilitaConsumoEsgoto">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">

							<tr>
								<td colspan="7" class="styleFonteTabelaPrincipal" align="center">
								Hist&oacute;rico de Consumo</td>

							</tr>

							<tr bgcolor="#90c7fc" class="styleFontePeqNegrito">
								<td width="10%" align="center">M&ecirc;s e Ano</td>
								<td width="13%" align="center">Consumo Medido</td>
								<td width="13%" align="center">Consumo Faturado</td>
								<td width="17%" align="center">Volume Faturado Esgoto</td>
								<td width="17%" align="center">Anormalidade Consumo</td>
								<td width="17%" align="center">Dias de Consumo</td>
								<td width="19%" align="center">Tipo de Consumo</td>
							</tr>
							<tr bordercolor="#90c7fc">
								<td colspan="7">
								<div style="width: 100%; height: 50; overflow: auto;">
								<table width="100%" align="left" bgcolor="#90c7fc">
									<!--corpo da segunda tabela-->
									<%cont = 0;%>
									<logic:notEmpty name="imoveisMicromedicao">

										<logic:iterate name="imoveisMicromedicao"
											id="imovelMicromedicao">
											<%cont = cont + 1;
								if (cont % 2 == 0) {%>
											<tr bgcolor="#cbe5fe" class="styleFontePequena">
												<%} else {

								%>
											<tr bgcolor="#FFFFFF" class="styleFontePequena">																						
												<%}%>
												<td width="10%" align="center"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif">
												${imovelMicromedicao.consumoHistorico.mesAno} &nbsp;</font></td>
												<td width="13%" align="center"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif">
												${imovelMicromedicao.medicaoHistorico.numeroConsumoMes}
												&nbsp;</font></td>
												<td width="13%" align="center"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif">
												${imovelMicromedicao.consumoHistorico.numeroConsumoFaturadoMes}
												&nbsp;</font></td>
												<td width="17%" align="center"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif">
												${imovelMicromedicao.consumoHistoricoEsgoto.numeroConsumoFaturadoMes}
												&nbsp;</font></td>
												<td width="17%" align="center"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif">
												${imovelMicromedicao.consumoHistorico.consumoAnormalidade.descricaoAbreviada}
												&nbsp;</font></td>
												<td width="17%" align="center"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif">
												${imovelMicromedicao.qtdDias}&nbsp;</font></td>
												<td width="99%"><font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif">
												${imovelMicromedicao.consumoHistorico.consumoTipo.descricao}
												&nbsp;</font></td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
								</div>
								</td>
							</tr>
						</table>
						</logic:present>
						</td>
				</tr>

				<tr>
					<td colspan="6">&nbsp;</td>
				</tr>

				<tr>
					<td colspan="6">
					<table cellspacing="0" cellpadding="0" border="0" width="100%">
						<tr>
						<tr>
							<td align="right"><input style="width: 180px" type="button"
								name="Button" class="bottonRightCol"
								value="Subst. Consumos Anteriores"
								onClick="javascript:redirecionarSubmit('/gsan/exibirSubstituirConsumoAnteriorAction.do?idImovel=${sessionScope.leituraConsumoActionForm.imovel}');" />
							<input style="width: 180px" type="button" name="Button"
								class="bottonRightCol" value="Alterar Dados Faturamento"
								onClick="javascript:redirecionarSubmit('/gsan/exibirDadosFaturamentoAction.do?idImovel=${sessionScope.leituraConsumoActionForm.imovel}&idTipoMedicao=${sessionScope.leituraConsumoActionForm.idTipoMedicao}&voltar=S&bloquearCampos=S&telaMedicaoConsumoDadosAnt=1');" />
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td><input type="button" name="Button" class="bottonRightCol"
						value="Voltar"
						onclick="javascript:redirecionarSubmit('/gsan/filtrarExcecoesLeiturasConsumosWizardAction.do?concluir=true&action=validarExcecoesLeiturasConsumosLocalizacao')">
					</td>
				</tr>
				<tr>
					<td colspan="6">
					<table cellspacing="0" cellpadding="0" width="100%" border="0">
						<tr>
							<td width="50%">&nbsp; <logic:notPresent
								name="desabilitaBotaoAnterior">
								<table cellspacing="0" cellpadding="0" width="100%" border="0">
									<tr>
										<td align="right" width="83%"><img
											src="<bean:message key="caminho.imagens"/>voltar.gif"
											onclick="imovelAnterior();"></td>
										<td align="left" width="15%"><input type="button"
											name="Button" class="buttonAbaRodaPe" value="Imóvel Anterior"
											onclick="imovelAnterior();" /></td>
									</tr>
								</table>

							</logic:notPresent></td>
							<td>&nbsp;</td>
							<td width="50%">&nbsp; <logic:notPresent
								name="desabilitaBotaoProximo">
								<table cellspacing="0" cellpadding="0" width="100%" border="0">
									<tr>
										<td align="left" width="15%"><input type="button"
											name="Button" class="buttonAbaRodaPe" value="Próximo Imóvel"
											onclick="proximoImovel();" /></td>
										<td align="left" width="83%"><img
											src="<bean:message key="caminho.imagens"/>avancar.gif"
											onclick="proximoImovel()"></td>
									</tr>
								</table>
							</logic:notPresent></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>

					<td colspan="6">&nbsp;</td>
				</tr>

			</table>
			<br>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>


</html:form>
</body>
</html:html>
