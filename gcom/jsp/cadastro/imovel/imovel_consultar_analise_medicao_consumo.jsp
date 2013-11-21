<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>


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
	}
</style>

<script language="JavaScript">
<!-- Begin

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'imovel') {
      form.idImovelAnaliseMedicaoConsumo.value = codigoRegistro;
      form.matriculaImovelAnaliseMedicaoConsumo.value = descricaoRegistro;
      form.matriculaImovelAnaliseMedicaoConsumo.style.color = "#000000";
	  form.action = 'consultarImovelWizardAction.do?action=exibirConsultarImovelDadosAnaliseMedicaoConsumoAction&indicadorNovo=OK'
	  form.submit();
    }
    
}

function limparForm(){
   	var form = document.forms[0];
                                                     	 
	form.action = 'consultarImovelWizardAction.do?action=exibirConsultarImovelDadosAnaliseMedicaoConsumoAction&limparForm=OK'
	form.submit();
}

	function extendeTabela(tabela,display){
		var form = document.forms[0];

		if(display){
 			eval('layerHide'+tabela).style.display = 'none';
 			eval('layerShow'+tabela).style.display = 'block';
		}else{
			eval('layerHide'+tabela).style.display = 'block';
 			eval('layerShow'+tabela).style.display = 'none';
		}
	}

function verificarExibicaoRelatorio() {
	var form = document.forms[0];
	
	if (form.idImovelAnaliseMedicaoConsumo.value.length > 0 && form.matriculaImovelAnaliseMedicaoConsumo.value.length > 0) {
		toggleBoxCaminho('demodiv',1,'gerarRelatorioResumoImovelMicromedicaoAction.do');
	} else {
		alert('Informe Imóvel');
	}
	
}

function verificarExibicaoRelatorioMedicaoPoco() {
	var form = document.forms[0];
	
	if (form.idImovelAnaliseMedicaoConsumo.value.length > 0 && form.matriculaImovelAnaliseMedicaoConsumo.value.length > 0) {
		toggleBoxCaminho('demodiv',1,'gerarRelatorioHistoricoMedicaoPocoAction.do');
	} else {
		alert('Informe Imóvel');
	}
	
}


function verificaExibicaoRelatorioAnaliseMedicaoConsumoImovel(){
	var form = document.forms[0];
	
	if (form.idImovelAnaliseMedicaoConsumo.value.length > 0 && form.matriculaImovelAnaliseMedicaoConsumo.value.length > 0) {
		toggleBoxCaminho('demodiv',1,'gerarRelatorioDadosAnaliseMedicaoConsumoImovelAction.do');
	} else {
		alert('Informe Imóvel');
	}

}

function habilitaMatricula() {
	var form = document.forms[0];
	
	if (form.idImovelAnaliseMedicaoConsumo.value != null && form.matriculaImovelAnaliseMedicaoConsumo.value != null && 
		form.matriculaImovelAnaliseMedicaoConsumo.value != "" && form.matriculaImovelAnaliseMedicaoConsumo.value != "IMÓVEL INEXISTENTE"){
	
		form.idImovelAnaliseMedicaoConsumo.disabled = true;
	} else {
		form.idImovelAnaliseMedicaoConsumo.disabled = false;
	}
}

function pesquisarImovel() {
	var form = document.forms[0];
 	
 	if (form.idImovelAnaliseMedicaoConsumo.disabled ) {
 		alert("Para realizar uma pesquisa de imóvel é necessário apagar o imóvel atual.")
	} else {
		abrirPopup('exibirPesquisarImovelAction.do', 400, 800)
	}
}

-->
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:habilitaMatricula();setarFoco('idImovelAnaliseMedicaoConsumo')">
<html:form action="/exibirConsultarImovelAction.do"
	name="ConsultarImovelActionForm"
	type="gcom.gui.cadastro.imovel.ConsultarImovelActionForm" method="post"
	onsubmit="return validateConsultarImovelActionForm(this);">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_consulta.jsp?numeroPagina=3" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<table width="773" border="0" cellspacing="5" cellpadding="0">
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
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<!--Fim Tabela Reference a Páginação da Tela de Processo--> <!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<table width="100%" border="0">
				<tr>
					<td colspan="4">
					<table align="center" bgcolor="#99ccff" border="0" width="100%">
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
							<td align="center" width="100%">
							<table border="0" width="100%">
								<tr>
									<td bordercolor="#000000" width="25%"><strong>Im&oacute;vel:<font
										color="#FF0000">*</font></strong></td>
									<td width="75%" colspan="3"><html:text
										property="idImovelAnaliseMedicaoConsumo" maxlength="9"
										size="9"
										onkeypress="validaEnterComMensagem(event, 'consultarImovelWizardAction.do?action=exibirConsultarImovelDadosAnaliseMedicaoConsumoAction&indicadorNovo=OK','idImovelAnaliseMedicaoConsumo','Im&oacute;vel');return isCampoNumerico(event);" 
										/>
									<a
										href="javascript:pesquisarImovel();">
									<img width="23" height="21"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										border="0" title="Pesquisar Imóvel"/></a> <logic:present
										name="idImovelAnaliseMedicaoConsumoNaoEncontrado"
										scope="request">
										<html:text property="matriculaImovelAnaliseMedicaoConsumo"
											size="40" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" 
											title="Localidade.Setor.Quadra.Lote.Sublote"/>
									</logic:present> <logic:notPresent
										name="idImovelAnaliseMedicaoConsumoNaoEncontrado"
										scope="request">
										<logic:present
											name="valorMatriculaImovelAnaliseMedicaoConsumo"
											scope="request">
											<html:text property="matriculaImovelAnaliseMedicaoConsumo"
												size="40" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" 
												title="Localidade.Setor.Quadra.Lote.Sublote"/>
										</logic:present>
										<logic:notPresent
											name="valorMatriculaImovelAnaliseMedicaoConsumo"
											scope="request">
											<html:text property="matriculaImovelAnaliseMedicaoConsumo"
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
									<div class="style9"><strong>Situa&ccedil;&atilde;o de
									&Aacute;gua:</strong></div>
									</td>
									
									<td>
										<html:text property="situacaoAguaAnaliseMedicaoConsumo"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="10" maxlength="10" />									
									</td>
									
									<logic:notPresent name="consultarImovelActionForm" property="hint1">
										<td valign="center">
											<strong>&nbsp;&nbsp;&nbsp;&nbsp;Situação de Esgoto:</strong>
										</td>
									</logic:notPresent>

									<logic:present name="consultarImovelActionForm" property="hint1">
										<td valign="center"><img border="0" width="25" height="25"
											src="<bean:message key="caminho.imagens"/>informacao.gif"
											onmouseover="this.T_BGCOLOR='whitesmoke';this.T_LEFT=true;return escape( '${sessionScope.consultarImovelActionForm.hint1}' ); "/><strong>&nbsp;&nbsp;&nbsp;&nbsp;Situação de Esgoto:</strong></td>
									</logic:present>										
									<td><html:text
										property="situacaoEsgotoAnaliseMedicaoConsumo" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="10" maxlength="10" /></td>
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
							<div class="style9">${enderecoAnaliseMedicaoConsumo} &nbsp;</div>
							</td>

						</tr>
					</table>
					</td>
				</tr>


				<tr>
					<td>
					<table width="100%" bgcolor="#99CCFF">

						<!--header da tabela interna -->
						<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
							<td width="15%">
							<div align="center">Grupo de Faturamento</div>
							</td>
							<td width="15%">
							<div align="center">Dia Vencimento</div>
							</td>

							<td width="20%">
							<div align="center">M&ecirc;s/Ano do Faturamento</div>
							</td>
							<td width="15%">
							<div align="center">Empresa Leiturista</div>
							</td>
							<td width="10%">
							<div align="center">Rota</div>
							</td>
							<td width="15%">
							<div align="center">Seq. Rota</div>
							</td>
						</tr>
						<tr bgcolor="#FFFFFF" class="styleFontePequena">
							<td>
							<div align="center">${sessionScope.consultarImovelActionForm.grupoFaturamento}
							&nbsp;</div>

							</td>
							<td>
							<div align="center">${sessionScope.consultarImovelActionForm.diaVencimento}
							&nbsp;</div>

							</td>
							<td>
							<div align="center">${sessionScope.consultarImovelActionForm.mesAnoFaturamentoCorrente}
							&nbsp;</div>
							</td>

							<td>
							<div align="center">${sessionScope.consultarImovelActionForm.empresaLeitura}
							&nbsp;</div>
							</td>
							<td>
							<div align="center">${sessionScope.consultarImovelActionForm.rota}
							&nbsp;</div>
							</td>
							<td>
							<div align="center">${sessionScope.consultarImovelActionForm.sequencialRota}
							&nbsp;</div>
							</td>
						</tr>

					</table>
					</td>
				<tr>
					<td>

					<table width="100%" bgcolor="#99CCFF">
						<tr>
							<td colspan="5" bgcolor="#79bbfd" align="center"><strong>Dados da
							Ligação de Água<strong></td>

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
							<td align="center">${sessionScope.consultarImovelActionForm.dataLigacaoAgua}
							&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.dataCorteAgua}
							&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.dataReligacaoAgua}
							&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.dataSupressaoAgua}
							&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.dataRestabelecimentoAgua}
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
									<td align="center">${sessionScope.consultarImovelActionForm.descricaoLigacaoAguaDiametro}
									&nbsp;</td>
									<td align="center">${sessionScope.consultarImovelActionForm.descricaoLigacaoAguaMaterial}
									&nbsp;</td>
									<td align="center">${sessionScope.consultarImovelActionForm.descricaoligacaoAguaPerfil}&nbsp;</td>
									<td align="center">${sessionScope.consultarImovelActionForm.numeroConsumominimoAgua}&nbsp;</td>
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
							<td colspan="4" bgcolor="#79bbfd" align="center"><strong>Dados do
							Hidr&ocirc;metro da Ligação de Água</strong></td>

						</tr>
						<!--header da tabela interna -->
						<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
							<td width="25%" align="center">Tipo de Medi&ccedil;&atilde;o</td>

							<td width="26%" align="center">Hidr&ocirc;metro</td>
							<td width="22%" align="center">Data de Instala&ccedil;&atilde;o</td>
							<td width="27%" align="center">Capacidade</td>
						</tr>

						<tr bgcolor="#FFFFFF" class="styleFontePequena">
							<td align="center">${sessionScope.consultarImovelActionForm.tipoMedicao}
							&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.numeroHidrometro}
							&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.instalacaoHidrometro}
							&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.capacidadeHidrometro}
							&nbsp;</td>
						</tr>
						<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
							<td align="center">Tipo de Hidr&ocirc;metro</td>
							<td align="center">Marca</td>
							<td align="center">Local de Instala&ccedil;&atilde;o</td>
							<td align="center">Di&acirc;metro</td>

						</tr>
						<tr bgcolor="#FFFFFF" class="styleFontePequena">
							<td align="center">${sessionScope.consultarImovelActionForm.tipoHidrometro}
							&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.marcaHidrometro}
							&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.localInstalacaoHidrometro}&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.diametroHidrometro}
							&nbsp;</td>
						</tr>

						<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
							<td colspan="2" align="center">Prote&ccedil;&atilde;o</td>
							<td align="center">Indicador de Cavalete</td>
							<td align="center">Ano de Fabrica&ccedil;&atilde;o</td>

						</tr>

						<tr bgcolor="#FFFFFF" class="styleFontePequena">
							<td colspan="2" align="center">${sessionScope.consultarImovelActionForm.protecaoHidrometro}
							&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.indicadorCavalete}
							&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.anoFabricacao}
							&nbsp;</td>
						</tr>
						<!--header da tabela interna -->
						<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
							<td align="center">Tipo Relojoaria</td>
							<td colspan="2" align="center">Usuário Responsável Instalação</td>
							<td align="center">Número Lacre Instalação</td>
						</tr>

						<tr bgcolor="#FFFFFF" class="styleFontePequena">
							<td align="center">${sessionScope.consultarImovelActionForm.tipoRelojoaria}
							&nbsp;</td>
							<td colspan="2" align="center">${sessionScope.consultarImovelActionForm.usuarioResponsavelInstalacao}
							&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.numeroLacreInstalacao}
							&nbsp;</td>
						</tr>
						
					</table>
					</td>
				</tr>
				<tr>
					<td>

					<table width="100%" bgcolor="#99CCFF">
						<tr>
							<td colspan="5" bgcolor="#79bbfd" align="center"><strong>Dados da
							Ligação de Esgoto</strong></td>

						</tr>
						<!--header da tabela interna -->
						<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
							<td width="19%" align="center">Data da Ligação</td>
							<td align="center" width="27%">Diametro</td>
							<td align="center" width="27%">Material</td>
							<td colspan="2" align="center" width="27%">Perfil de Ligação</td>
						</tr>

						<tr bgcolor="#FFFFFF" class="styleFontePequena">
							<td align="center">${sessionScope.consultarImovelActionForm.dataLigacaoEsgoto}
							&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.descricaoLigacaoEsgotoDiametro}
							&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.descricaoLigacaoEsgotoMaterial}
							&nbsp;</td>
							<td colspan="2" align="center">${sessionScope.consultarImovelActionForm.descricaoligacaoEsgotoPerfil}
							&nbsp;</td>
						</tr>
						<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
							<td align="center" width="20%">Consumo Minimo</td>
							<td align="center" width="15%">Percentual de Esgoto</td>
							<td align="center" width="15%">Percentual de Coleta</td>
							<td colspan="2" align="center" width="50%">Indicador de Poço</td>

						</tr>
						<tr bgcolor="#FFFFFF" class="styleFontePequena">
							<td align="center">${sessionScope.consultarImovelActionForm.numeroConsumominimoEsgoto}
							&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.percentualEsgoto}
							&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.percentualAguaConsumidaColetada}&nbsp;</td>
							<td colspan="2" align="center">${sessionScope.consultarImovelActionForm.descricaoPocoTipo}&nbsp;</td>
						</tr>
						<tr>
						<td colspan="5">
						<table width="100%">
						<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
							<td align="center" width="25%">Condição Esgotamento</td>
							<td align="center" width="25%">Sistema de Caixa de Inspeção</td>
							<td align="center" width="25%">Destino dos Dejetos</td>
							<td align="center" width="25%">Destino das Águas Pluviais</td>
						</tr>
						<tr bgcolor="#FFFFFF" class="styleFontePequena">
							<td align="center">${sessionScope.consultarImovelActionForm.condicaoEsgotamento}
							&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.sistemaCaixaInspecao}&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.destinoDejetos}&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.destinoAguasPluviais}&nbsp;</td>
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
							<td colspan="4" bgcolor="#79bbfd" align="center"><strong>Dados do
							Hidr&ocirc;metro do Poço</strong></td>

						</tr>
						<!--header da tabela interna -->
						<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
							<td width="25%" align="center">Tipo de Medi&ccedil;&atilde;o</td>

							<td width="26%" align="center">Hidr&ocirc;metro</td>
							<td width="22%" align="center">Data de Instala&ccedil;&atilde;o</td>
							<td width="27%" align="center">Capacidade</td>
						</tr>

						<tr bgcolor="#FFFFFF" class="styleFontePequena">
							<td align="center">${sessionScope.consultarImovelActionForm.tipoMedicaoPoco}
							&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.numeroHidrometroPoco}
							&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.instalacaoHidrometroPoco}
							&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.capacidadeHidrometroPoco}
							&nbsp;</td>
						</tr>
						<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
							<td align="center">Tipo de Hidr&ocirc;metro</td>
							<td align="center">Marca</td>
							<td align="center">Local de Instala&ccedil;&atilde;o</td>
							<td align="center">Di&acirc;metro</td>

						</tr>
						<tr bgcolor="#FFFFFF" class="styleFontePequena">
							<td align="center">${sessionScope.consultarImovelActionForm.tipoHidrometroPoco}
							&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.marcaHidrometroPoco}
							&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.localInstalacaoHidrometroPoco}&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.diametroHidrometroPoco}
							&nbsp;</td>
						</tr>

						<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
							<td colspan="2" align="center">Prote&ccedil;&atilde;o</td>
							<td align="center">Indicador de Cavalete</td>
							<td align="center">Ano de Fabrica&ccedil;&atilde;o</td>

						</tr>

						<tr bgcolor="#FFFFFF" class="styleFontePequena">
							<td colspan="2" align="center">${sessionScope.consultarImovelActionForm.protecaoHidrometroPoco}
							&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.indicadorCavaletePoco}
							&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.anoFabricacaoPoco}
							&nbsp;</td>
						</tr>
						<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
							<td align="center">Tipo Relojoaria</td>
							<td colspan="2" align="center">Usuário Responsável Instalação</td>
							<td align="center">Número Lacre Instalação</td>
						</tr>

						<tr bgcolor="#FFFFFF" class="styleFontePequena">
							<td align="center">${sessionScope.consultarImovelActionForm.tipoRelojoariaPoco}
							&nbsp;</td>
							<td colspan="2" align="center">${sessionScope.consultarImovelActionForm.usuarioResponsavelInstalacaoPoco}
							&nbsp;</td>
							<td align="center">${sessionScope.consultarImovelActionForm.numeroLacreInstalacaoPoco}
							&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>









				<!-- Dados do LigacaoAgua -->
				<tr>
					<td>
					<div id="layerHideLigacaoAgua" style="display:block">
					<table width="100%" border="0" bgcolor="#99CCFF">
						<tr bgcolor="#99CCFF">
							<td align="center"><span class="style2"> <a
								href="javascript:extendeTabela('LigacaoAgua',true);" /> <b>Histórico
							de Medição e Consumo da Ligação de Agua</b> </a> </span></td>
						</tr>
					</table>
					</div>

					<div id="layerShowLigacaoAgua" style="display:none">
					<table width="100%" border="0" bgcolor="#99CCFF">
						<tr bgcolor="#99CCFF">
							<td align="center"><span class="style2"> <a
								href="javascript:extendeTabela('LigacaoAgua',false);" /> <b>Histórico
							de Medição e Consumo da Ligação de Agua</b> </a> </span></td>
						</tr>

						<tr>
							<td>
							<table width="100%" bgcolor="#99CCFF">

								<tr>
									<td colspan="6" bgcolor="#79bbfd" align="center"><strong>Dados
									da Medi&ccedil;&atilde;o do M&ecirc;s</strong></td>
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
									<td align="center">${sessionScope.consultarImovelActionForm.dtLeituraAnterior}
									&nbsp;</td>
									<td align="center">${sessionScope.consultarImovelActionForm.leituraAnterior}
									&nbsp;</td>
									<td align="center">${sessionScope.consultarImovelActionForm.dtLeituraInformada}
									&nbsp;</td>
									<td align="center">${sessionScope.consultarImovelActionForm.leituraAtualInformada}
									&nbsp;</td>
									<td align="center">${sessionScope.consultarImovelActionForm.dtLeituraFaturada}
									&nbsp;</td>
									<td align="center">${sessionScope.consultarImovelActionForm.leituraAtualFaturada}
									&nbsp;</td>
								</tr>
								<tr>
									<td colspan="6">
									<table>
										<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
											<td width="20%" align="center">Situa&ccedil;&atilde;o da
											Leitura Atual</td>
											<td width="10%" align="center">Funcion&aacute;rio</td>
											<td width="25%" align="center">Anorm. de Leitura Inf.</td>
											<td width="25%" align="center">Anorm. Leitura Fat.</td>
											<td width="20%" align="center">Consumo Médio Hidr&ocirc;metro</td>
										</tr>
										<tr bgcolor="#FFFFFF" class="styleFontePequena">
											<td align="center">${sessionScope.consultarImovelActionForm.situacaoLeituraAtual}
											&nbsp;</td>
											<td align="center">${sessionScope.consultarImovelActionForm.codigoFuncionario}
											&nbsp;</td>
											<td align="center">${sessionScope.consultarImovelActionForm.anormalidadeLeituraInformada}&nbsp;</td>
											<td align="center">${sessionScope.consultarImovelActionForm.anormalidadeLeituraFaturada}&nbsp;</td>
											<td align="center">${sessionScope.consultarImovelActionForm.consumoMedioHidrometro}&nbsp;</td>
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
									<td colspan="4" bgcolor="#79bbfd">

									<div align="center"><strong>Dados do Consumo do M&ecirc;s da
									Ligação de Água</strong></div>
									</td>

								</tr>

								<!--header da tabela interna -->

								<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
									<td width="20%" align="center">Consumo Medido</td>
									<td width="22%" align="center">Consumo Faturado</td>

									<td width="24%" align="center">Consumo do Rateio</td>
									<td width="34%" align="center">Consumo M&eacute;dio do Imóvel</td>
								</tr>
								<tr bgcolor="#FFFFFF" class="styleFontePequena">
									<td align="center">${sessionScope.consultarImovelActionForm.consumoMedido}
									&nbsp;</td>
									<td align="center">${sessionScope.consultarImovelActionForm.consumoFaturado}
									&nbsp;</td>
									<td align="center">${sessionScope.consultarImovelActionForm.consumoRateio}
									&nbsp;</td>
									<td align="center">${sessionScope.consultarImovelActionForm.consumoMedioImovel}
									&nbsp;</td>
								</tr>

								<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
									<td width="29%" align="center">Anormalidade de Consumo</td>
									<td width="26%" align="center">Percentual de
									Varia&ccedil;&atilde;o</td>
									<td width="22%" align="center">Dias de Consumo</td>

									<td width="23%" align="center">Tipo de Consumo</td>
								</tr>
								<tr bgcolor="#FFFFFF" class="styleFontePequena">
									<td align="center">${sessionScope.consultarImovelActionForm.anormalidadeConsumo}
									&nbsp;</td>
									<td align="center">${sessionScope.consultarImovelActionForm.percentualVariacao}
									&nbsp;</td>
									<td align="center">${sessionScope.consultarImovelActionForm.diasConsumo}
									&nbsp;</td>
									<td align="center">${sessionScope.consultarImovelActionForm.consumoTipo}
									&nbsp;</td>
								</tr>

							</table>
							</td>
						</tr>

						<tr>
							<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td colspan="9" bgcolor="#79bbfd" align="center"><strong>Hist&oacute;rico
									de Medi&ccedil;&atilde;o</strong></td>
								</tr>

								<tr bgcolor="#90c7fc" class="styleFontePeqNegrito">
									<td width="9%" align="center">M&ecirc;s/Ano</td>
									<td width="11%" align="center">Dt. Leit. Inform.</td>
									<td width="9%" align="center">Leit. Inform.</td>
									<td width="11%" align="center">Dt. Leit. Fat.</td>
									<td width="10%" align="center">Leit. Fat.</td>
									<td width="9%" align="center">Cons. Médio</td>
									<td width="12%" align="center">Anorm. Informada</td>
									<td width="10%" align="center">Anorm. Faturada</td>
									<td width="19%" align="center">Sit. Leit. Atual</td>
								</tr>
								<tr bordercolor="#90c7fc">
									<td colspan="9" height="50">
									<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%" align="left" bgcolor="#90c7fc">
										<!--corpo da segunda tabela-->
										<%int cont = 0;%>
										<logic:notEmpty name="medicoesHistoricos">

											<logic:iterate name="medicoesHistoricos"
												id="medicaoHistorico">
												<%cont = cont + 1;
							if (cont % 2 == 0) {%>
												<tr bgcolor="#cbe5fe" class="styleFontePequena">
													<%} else {

							%>
												<tr bgcolor="#FFFFFF" class="styleFontePequena">
													<%}%>
													<td width="9%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
													${medicaoHistorico.mesAno} &nbsp;</font></td>
													<td width="9%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
														name="medicaoHistorico"
														property="dataLeituraAtualInformada"
														formatKey="date.format" /> </font></td>
													<td width="11%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
													${medicaoHistorico.leituraAtualInformada} &nbsp;</font></td>
													<td width="9%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
														name="medicaoHistorico"
														property="dataLeituraAtualFaturamento"
														formatKey="date.format" /> </font></td>

													<td width="10%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
													${medicaoHistorico.leituraAtualFaturamento} &nbsp;</font></td>
													<td width="10%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
													${medicaoHistorico.consumoMedioHidrometro} &nbsp;</font></td>
													<td width="14%" align="center"><font color="#000000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">									
												    <a title="${medicaoHistorico.leituraAnormalidadeInformada.descricao}">
														 ${medicaoHistorico.leituraAnormalidadeInformada.id}
													</a>
													&nbsp;</font></td>
													<td width="14%" align="center"><font color="#000000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
													<a title="${medicaoHistorico.leituraAnormalidadeFaturamento.descricao}">
														 ${medicaoHistorico.leituraAnormalidadeFaturamento.id}
													</a>																									
													</font>
													</td>
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
							<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="0">

								<tr>
									<td colspan="7" bgcolor="#79bbfd" align="center"><strong>Hist&oacute;rico
									de Consumo</strong></td>

								</tr>

								<tr bgcolor="#90c7fc" class="styleFontePeqNegrito">
									<td width="13%" align="center">M&ecirc;s/Ano</td>

									<td width="13%" align="center">Consumo Medido</td>
									<td width="15%" align="center">Consumo Faturado</td>
									<td width="13%" align="center">Consumo Médio</td>
									<td width="13%" align="center">Anormalidade Consumo</td>
									<td width="10%" align="center">Dias de Consumo</td>
									<td width="23%" align="center">Tipo de Consumo</td>
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
													<td width="13%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
													${imovelMicromedicao.consumoHistorico.mesAno} &nbsp;</font></td>
													<td width="13%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
													${imovelMicromedicao.medicaoHistorico.numeroConsumoMes}
													&nbsp;</font></td>
													<td width="15%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
													${imovelMicromedicao.consumoHistorico.numeroConsumoFaturadoMes}
													&nbsp;</font></td>
													<td width="13%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
													${imovelMicromedicao.consumoHistorico.consumoMedio}
													&nbsp;</font></td>
													
													<td width="13%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
													<a title="${imovelMicromedicao.consumoHistorico.consumoAnormalidade.descricao}">
														${imovelMicromedicao.consumoHistorico.consumoAnormalidade.descricaoAbreviada}
													</a>
													&nbsp;</font></td>
													
													<td width="10%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
													${imovelMicromedicao.qtdDias}&nbsp;</font></td>
													<td width="23%"><font color="#000000" style="font-size:9px"
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
								<tr>
									<td colspan="7" align="right">
									<table>
										<tr>
											<td width="70%">&nbsp;</td>
											<td><a href="javascript:verificarExibicaoRelatorio();"> <img
												border="0"
												src="<bean:message key='caminho.imagens'/>print.gif"
												title="Imprimir Histórico de Medição e Consumo" /> </a></td>
										</tr>
									</table>
									</td>

								</tr>
							</table>
							</td>
						</tr>


					</table>
					</div>
					</td>
				</tr>

				<!-- Dados do LigacaoEsgoto -->
				<tr>
					<td>
					<div id="layerHideLigacaoEsgoto" style="display:block">
					<table width="100%" border="0" bgcolor="#99CCFF">
						<tr bgcolor="#99CCFF">
							<td align="center"><span class="style2"> <a
								href="javascript:extendeTabela('LigacaoEsgoto',true);" /> <b>Histórico
							de Medição do Poço e Volume da Ligação de Esgoto</b> </a> </span></td>
						</tr>
					</table>
					</div>

					<div id="layerShowLigacaoEsgoto" style="display:none">
					<table width="100%" border="0" bgcolor="#99CCFF">
						<tr bgcolor="#99CCFF">
							<td align="center"><span class="style2"> <a
								href="javascript:extendeTabela('LigacaoEsgoto',false);" /> <b>Histórico
							de Medição do Poço e Volume da Ligação de Esgoto</b> </a> </span></td>
						</tr>

						<tr>
							<td>
							<table width="100%" bgcolor="#99CCFF">

								<tr>
									<td colspan="6" bgcolor="#79bbfd" align="center"><strong>Dados
									da Medi&ccedil;&atilde;o do M&ecirc;s do Poço</strong></td>
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
									<td align="center">${sessionScope.consultarImovelActionForm.dtLeituraAnteriorPoco}
									&nbsp;</td>
									<td align="center">${sessionScope.consultarImovelActionForm.leituraAnteriorPoco}
									&nbsp;</td>
									<td align="center">${sessionScope.consultarImovelActionForm.dtLeituraInformadaPoco}
									&nbsp;</td>
									<td align="center">${sessionScope.consultarImovelActionForm.leituraAtualInformadaPoco}
									&nbsp;</td>
									<td align="center">${sessionScope.consultarImovelActionForm.dtLeituraFaturadaPoco}
									&nbsp;</td>
									<td align="center">${sessionScope.consultarImovelActionForm.leituraAtualFaturadaPoco}
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
											<td align="center">${sessionScope.consultarImovelActionForm.situacaoLeituraAtualPoco}
											&nbsp;</td>
											<td align="center">${sessionScope.consultarImovelActionForm.codigoFuncionarioPoco}
											&nbsp;</td>
											<td align="center">${sessionScope.consultarImovelActionForm.anormalidadeLeituraInformadaPoco}&nbsp;</td>
											<td align="center">${sessionScope.consultarImovelActionForm.anormalidadeLeituraFaturadaPoco}&nbsp;</td>
											<td align="center">${sessionScope.consultarImovelActionForm.consumoMedioHidrometroPoco}&nbsp;</td>
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
									<td colspan="4" bgcolor="#79bbfd">

									<div align="center"><strong>Dados de Volume do Mês da Ligação
									de Esgoto</strong></div>
									</td>

								</tr>

								<!--header da tabela interna -->

								<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
									<td width="20%" align="center">Consumo Medido</td>
									<td width="22%" align="center">Consumo Faturado</td>

									<td width="24%" align="center">Consumo do Rateio</td>
									<td width="34%" align="center">Consumo M&eacute;dio do Imóvel</td>
								</tr>
								<tr bgcolor="#FFFFFF" class="styleFontePequena">
									<td align="center">${sessionScope.consultarImovelActionForm.consumoMedidoEsgoto}
									&nbsp;</td>
									<td align="center">${sessionScope.consultarImovelActionForm.consumoFaturadoEsgoto}
									&nbsp;</td>
									<td align="center">${sessionScope.consultarImovelActionForm.consumoRateioEsgoto}
									&nbsp;</td>
									<td align="center">${sessionScope.consultarImovelActionForm.consumoMedioImovelEsgoto}
									&nbsp;</td>
								</tr>

								<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
									<td width="29%" align="center">Anormalidade de Consumo</td>
									<td width="26%" align="center">Percentual de
									Varia&ccedil;&atilde;o</td>
									<td width="22%" align="center">Dias de Consumo</td>

									<td width="23%" align="center">Tipo de Consumo</td>
								</tr>
								<tr bgcolor="#FFFFFF" class="styleFontePequena">
									<td align="center">${sessionScope.consultarImovelActionForm.anormalidadeConsumoEsgoto}
									&nbsp;</td>
									<td align="center">${sessionScope.consultarImovelActionForm.percentualVariacaoEsgoto}
									&nbsp;</td>
									<td align="center">${sessionScope.consultarImovelActionForm.diasConsumoEsgoto}
									&nbsp;</td>
									<td align="center">${sessionScope.consultarImovelActionForm.consumoTipoEsgoto}
									&nbsp;</td>
								</tr>
							</table>
							</td>
						</tr>

						<tr>
							<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td colspan="9" bgcolor="#79bbfd" align="center"><strong>Hist&oacute;rico
									de Medi&ccedil;&atilde;o do Poço</strong></td>
								</tr>

								<tr bgcolor="#90c7fc" class="styleFontePeqNegrito">
									<td width="9%" align="center">M&ecirc;s/Ano</td>
									<td width="12%" align="center">Dt. Leit. Inform.</td>
									<td width="9%" align="center">Leit. Inform.</td>
									<td width="11%" align="center">Dt. Leit. Fat.</td>
									<td width="10%" align="center">Leit. Fat.</td>
									<td width="10%" align="center">Cons. Médio</td>
									<td width="12%" align="center">Anorm. Informada</td>
									<td width="12%" align="center">Anorm. Faturada</td>
									<td width="15%" align="center">Sit. Leit. Atual</td>
								</tr>
								<tr bordercolor="#90c7fc">
									<td colspan="9" height="50">
									<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%" align="left" bgcolor="#90c7fc">
										<!--corpo da segunda tabela-->
										<%cont = 0;%>
										<logic:notEmpty name="medicoesHistoricosPoco">

											<logic:iterate name="medicoesHistoricosPoco"
												id="medicaoHistorico">
												<%cont = cont + 1;
							if (cont % 2 == 0) {%>
												<tr bgcolor="#cbe5fe" class="styleFontePequena">
													<%} else {

							%>
												<tr bgcolor="#FFFFFF" class="styleFontePequena">
													<%}%>
													<td width="9%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">${medicaoHistorico.mesAno}</font></td>
													<td width="12%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
														name="medicaoHistorico"
														property="dataLeituraAtualInformada"
														formatKey="date.format" /> </font></td>
													<td width="9%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
													${medicaoHistorico.leituraAtualInformada} &nbsp;</font></td>
													<td width="11%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
														name="medicaoHistorico"
														property="dataLeituraAtualFaturamento"
														formatKey="date.format" /> </font></td>

													<td width="10%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
													${medicaoHistorico.leituraAtualFaturamento} &nbsp;</font></td>
													<td width="10%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
													${medicaoHistorico.consumoMedioHidrometro} &nbsp;</font></td>
													<td width="12%" align="center"><font color="#000000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
													    <a title="${medicaoHistorico.leituraAnormalidadeInformada.descricao}">
														  ${medicaoHistorico.leituraAnormalidadeInformada.id}
														</a>
													</font></td>
													<td width="12%" align="center"><font color="#000000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
														<a title="${medicaoHistorico.leituraAnormalidadeFaturamento.descricao}">
														 ${medicaoHistorico.leituraAnormalidadeFaturamento.id}
														</a>
													</font></td>
													<td width="15%"><font color="#000000" style="font-size:9px"
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
							<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="0">

								<tr>
									<td colspan="7" bgcolor="#79bbfd" align="center"><strong>Hist&oacute;rico
									do Volume da Ligação de Esgoto</strong></td>

								</tr>

								<tr bgcolor="#90c7fc" class="styleFontePeqNegrito">
									<td width="13%" align="center">M&ecirc;s/Ano</td>

									<td width="13%" align="center">Consumo Medido</td>
									<td width="15%" align="center">Consumo Faturado</td>
									<td width="13%" align="center">Consumo Médio</td>
									<td width="13%" align="center">Anormalidade Consumo</td>
									<td width="10%" align="center">Dias de Consumo</td>
									<td width="23%" align="center">Tipo de Consumo</td>
								</tr>
								<tr bordercolor="#90c7fc">
									<td colspan="7">
									<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%" align="left" bgcolor="#90c7fc">
										<!--corpo da segunda tabela-->
										<%cont = 0;%>
										<logic:notEmpty name="imoveisMicromedicaoEsgoto">

											<logic:iterate name="imoveisMicromedicaoEsgoto"
												id="imovelMicromedicao">
												<%cont = cont + 1;
							if (cont % 2 == 0) {%>
												<tr bgcolor="#cbe5fe" class="styleFontePequena">
													<%} else {

							%>
												<tr bgcolor="#FFFFFF" class="styleFontePequena">
													<%}%>
													<td width="13%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
													${imovelMicromedicao.consumoHistorico.mesAno} &nbsp;</font></td>
													<td width="13%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
													${imovelMicromedicao.medicaoHistorico.numeroConsumoMes}
													&nbsp;</font></td>
													<td width="15%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
													${imovelMicromedicao.consumoHistorico.numeroConsumoFaturadoMes}
													&nbsp;</font></td>
													<td width="13%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
													${imovelMicromedicao.consumoHistorico.consumoMedio}
													&nbsp;</font></td>
													
													<td width="13%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
														<a title="${imovelMicromedicao.consumoHistorico.consumoAnormalidade.descricao}">
															${imovelMicromedicao.consumoHistorico.consumoAnormalidade.descricaoAbreviada}
														</a>
													&nbsp;</font></td>
													
													<td width="10%" align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
													${imovelMicromedicao.qtdDias}&nbsp;</font></td>
													<td width="23%"><font color="#000000" style="font-size:9px"
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
								<tr>
								<td colspan="7" align="right">
								<table>
									<tr>
										<td width="70%">&nbsp;</td>
										<td><a href="javascript:verificarExibicaoRelatorioMedicaoPoco();"> 
												<img border="0"
													src="<bean:message key='caminho.imagens'/>print.gif"
													title="Imprimir Histórico de Volume da Ligação de Esgoto" /> 
											</a>
										</td>
									</tr>
								</table>
								</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</div>
					</td>
				</tr>

				<tr>
					<td colspan="6">&nbsp;</td>
				</tr>

	 			<tr>
					<td align="left" colspan="6">
						  <div align="right">
							   <a href="javascript:verificaExibicaoRelatorioAnaliseMedicaoConsumoImovel();">
									<img border="0" src="<bean:message key="caminho.imagens"/>print.gif"
										title="Imprimir Análise Ligação Consumo" /> 
								</a>
						  </div>
					</td>
				</tr>
				
				<tr>
					<td colspan="6">
						<table width="100%" border="0">
							<tr>
								<td colspan="2">
								<div align="right"><jsp:include
									page="/jsp/util/wizard/navegacao_botoes_wizard_consulta.jsp?numeroPagina=3" /></div>
								</td>
							</tr>
						</table>
					</td>
				</tr>

			</table>
			<br>
			</td>
		</tr>
	</table>

	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=" />
	<%@ include file="/jsp/util/rodape.jsp"%>
    <%@ include file="/jsp/util/tooltip.jsp" %>
	
</html:form>
</body>
</html:html>
