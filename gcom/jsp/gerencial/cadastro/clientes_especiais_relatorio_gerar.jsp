<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ include file="/jsp/util/telaespera.jsp"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="org.apache.struts.taglib.bean.IncludeTag"%><html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript">
<!-- Begin

function limparClienteResponsavel() {
	var form = document.GerarRelatorioClientesEspeciaisActionForm;
	form.idClienteResponsavel.value = "";
	form.nomeClienteResponsavel.value = "";	
}

function limparLeituraAnormalidade() {
	var form = document.GerarRelatorioClientesEspeciaisActionForm;
	form.idLeituraAnormalidade.value = "";
	form.descricaoLeituraAnormalidade.value = "";	
}

function limparConsumoAnormalidade() {
	var form = document.GerarRelatorioClientesEspeciaisActionForm;
	form.idConsumoAnormalidade.value = "";
	form.descricaoConsumoAnormalidade.value = "";	
}

function validarIntervalos(form) {

	var validacaoLocalizacao = validarIntervalosLocalizacao();

	if(validacaoLocalizacao == false){
		return false;
	}

	if (!isBrancoOuNulo(form.intervaloQtdEcoFinal.value) && isBrancoOuNulo(form.intervaloQtdEcoInicial.value)) {
			alert('Informe Intervalo de Quantidade de Economias Inicial');
			return false;
	}
	if (!isBrancoOuNulo(form.intervaloQtdEcoInicial.value) && isBrancoOuNulo(form.intervaloQtdEcoFinal.value)) {
			alert('Informe Intervalo de Quantidade de Economias Final');
			return false;
	}
	if(parseInt(form.intervaloQtdEcoFinal.value) < parseInt(form.intervaloQtdEcoInicial.value)){
		alert('Intervalo de Quantidade de Economias Final deve ser maior ou igual a Intervalo de Quantidade de Economias Inicial');
		return false;
	}
	
	if (!isBrancoOuNulo(form.intervaloConsumoAguaFinal.value) && isBrancoOuNulo(form.intervaloConsumoAguaInicial.value)) {
			alert('Informe Intervalo de Consumo de Água Inicial');
			return false;
	}  
	if (!isBrancoOuNulo(form.intervaloConsumoAguaInicial.value) && isBrancoOuNulo(form.intervaloConsumoAguaFinal.value)) {
			alert('Informe Intervalo de Consumo de Água Final');
			return false;
	} 
	if(parseInt(form.intervaloConsumoAguaFinal.value) < parseInt(form.intervaloConsumoAguaInicial.value)){
		alert('Intervalo de Consumo de Água Final deve ser maior ou igual a Intervalo de Consumo de Água Inicial');
		return false;
	}

	if (!isBrancoOuNulo(form.intervaloConsumoEsgotoFinal.value) && isBrancoOuNulo(form.intervaloConsumoEsgotoInicial.value)) {
			alert('Informe Intervalo de Consumo de Esgoto Inicial');
			return false;
	} 
	if (!isBrancoOuNulo(form.intervaloConsumoEsgotoInicial.value) && isBrancoOuNulo(form.intervaloConsumoEsgotoFinal.value)) {
			alert('Informe Intervalo de Consumo de Esgoto Final');
			return false;
	} 
	if(parseInt(form.intervaloConsumoEsgotoFinal.value) < parseInt(form.intervaloConsumoEsgotoInicial.value)){
		alert('Intervalo de Consumo de Esgoto Final deve ser maior ou igual a Intervalo de Consumo de Esgoto Inicial');
		return false;
	}

	if (!isBrancoOuNulo(form.intervaloConsumoResponsavelFinal.value) && isBrancoOuNulo(form.intervaloConsumoResponsavelInicial.value)) {
			alert('Informe Intervalo de Consumo por Responsável Inicial');
			return false;
	} 
	if (!isBrancoOuNulo(form.intervaloConsumoResponsavelInicial.value) && isBrancoOuNulo(form.intervaloConsumoResponsavelFinal.value)) {
			alert('Informe Intervalo de Consumo por Responsável Final');
			return false;
	} 
	if(parseInt(form.intervaloConsumoResponsavelFinal.value) < parseInt(form.intervaloConsumoResponsavelInicial.value)){
		alert('Intervalo de Consumo por Responsável Final deve ser maior ou igual a Intervalo de Consumo por Responsável Inicial');
		return false;
	}

	if (!isBrancoOuNulo(form.dataInstalacaoHidrometroFinal.value) && isBrancoOuNulo(form.dataInstalacaoHidrometroInicial.value)) {
			alert('Informe Data de Instalação do Hidrômetro Inicial');
			return false;
	} 
	if (!isBrancoOuNulo(form.dataInstalacaoHidrometroInicial.value) && isBrancoOuNulo(form.dataInstalacaoHidrometroFinal.value)) {
			alert('Informe Data de Instalação do Hidrômetro Final');
			return false;
	}
	if(comparaData(form.dataInstalacaoHidrometroFinal.value,"<",form.dataInstalacaoHidrometroInicial.value)){
		alert('Data de Instalação do Hidrômetro Final deve ser maior ou igual a Data de Instalação do Hidrômetro Inicial');
		return false;
	}

	return true;
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'cliente') {
      form.idClienteResponsavel.value = codigoRegistro;
      form.nomeClienteResponsavel.value = descricaoRegistro;
      form.nomeClienteResponsavel.style.color = "#000000";
    }
    
    if (tipoConsulta == 'leituraAnormalidade') {
      form.idLeituraAnormalidade.value = codigoRegistro;
      form.descricaoLeituraAnormalidade.value = descricaoRegistro;
      form.descricaoLeituraAnormalidade.style.color = "#000000";
    }

    recuperarDadosPopupLocalizacao(codigoRegistro, descricaoRegistro, tipoConsulta);
} 

function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {
	 recuperarDadosQuatroParametrosSetor(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta);
}

function validarForm(form){

	if(validateGerarRelatorioClientesEspeciaisActionForm(form)){
		if (validarIntervalos(form)) {
			botaoAvancarTelaEspera('/gsan/gerarRelatorioClientesEspeciaisAction.do');
		}
	}
}
-->
</script>

<html:javascript staticJavascript="false"
	formName="GerarRelatorioClientesEspeciaisActionForm" />


</head>

<body leftmargin="5" topmargin="5">
<div id="formDiv">
<html:form action="/gerarRelatorioClientesEspeciaisAction.do"
	name="GerarRelatorioClientesEspeciaisActionForm"
	type="gcom.gui.gerencial.cadastro.GerarRelatorioClientesEspeciaisActionForm"
	method="post"
	onsubmit="return validateGerarRelatorioClientesEspeciaisActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext">
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
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Gerar Relatório de Clientes Especiais</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>

			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="4">Para filtrar o(s) cliente(s) especiais, informe os
					dados abaixo:</td>
				</tr>

				<jsp:include page="/jsp/util/imovel/imovel_filtros_de_gerencia_a_sequencial_rota.jsp">
					<jsp:param name="action" value="exibirGerarRelatorioClientesEspeciaisAction.do"/>
					<jsp:param name="isExibirSequencialRota" value="false"/>
				</jsp:include>

				<tr>
					<td width="200"><strong>Perfil do Imóvel:</strong></td>
					<td colspan="3"><html:select property="idsImovelPerfil"
						style="width: 230px;" multiple="mutiple" size="4" tabindex="5">
						<logic:notEmpty name="colecaoPerfilImovel">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoPerfilImovel"
								labelProperty="descricao" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>

				<tr>
					<td width="200"><strong>Categoria:</strong></td>
					<td colspan="3"><html:select property="idsCategoria"
						style="width: 230px;" multiple="mutiple" size="4" tabindex="6">
						<logic:notEmpty name="colecaoCategoria">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoCategoria"
								labelProperty="descricao" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>

				<tr>
					<td width="200"><strong>Subcategoria:</strong></td>
					<td colspan="3"><html:select property="idsSubCategoria"
						style="width: 230px;" multiple="mutiple" size="4" tabindex="7">
						<logic:notEmpty name="colecaoSubCategoria">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoSubCategoria"
								labelProperty="descricao" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>

				<tr>
					<td width="200"><strong>Situa&ccedil;&atilde;o da
					Liga&ccedil;&atilde;o de &Aacute;gua:</strong></td>
					<td colspan="3"><html:select property="idSituacaoLigacaoAgua"
						tabindex="8">
						<logic:notEmpty name="colecaoSituacaoLigacaoAgua">
							<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
							<html:options collection="colecaoSituacaoLigacaoAgua"
								labelProperty="descricao" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>

				<tr>
					<td width="200"><strong>Situa&ccedil;&atilde;o da
					Liga&ccedil;&atilde;o de Esgoto:</strong></td>
					<td colspan="3"><html:select property="idSituacaoLigacaoEsgoto"
						tabindex="9">
						<logic:notEmpty name="colecaoSituacaoLigacaoEsgoto">
							<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
							<html:options collection="colecaoSituacaoLigacaoEsgoto"
								labelProperty="descricao" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>

				<tr>
					<td width="200"><strong>Intervalo de Quantidade de Economias:</strong></td>
					<td colspan="3">
					<div align="left"><strong> <html:text
						property="intervaloQtdEcoInicial" size="4" maxlength="4"
						onkeyup="replicaDados(document.forms[0].intervaloQtdEcoInicial, document.forms[0].intervaloQtdEcoFinal);"
						onkeypress="return isCampoNumerico(event);"
						tabindex="10" /> 
						a 
						<html:text property="intervaloQtdEcoFinal"
							size="4" maxlength="4" tabindex="11" onkeypress="return isCampoNumerico(event);" /> </strong></div>
					</td>
				</tr>

				<tr>
					<td width="200"><strong>Intervalo de Consumo de Água:</strong></td>
					<td colspan="3">
					<div align="left"><strong> <html:text
						property="intervaloConsumoAguaInicial" size="6" maxlength="6"
						onkeyup="replicaDados(document.forms[0].intervaloConsumoAguaInicial, document.forms[0].intervaloConsumoAguaFinal);"
						onkeypress="return isCampoNumerico(event);"
						tabindex="12" />
						 a 
						<html:text property="intervaloConsumoAguaFinal" size="6" maxlength="6"
							tabindex="13" onkeypress="return isCampoNumerico(event);"/> </strong></div>
					</td>
				</tr>

				<tr>
					<td width="200"><strong>Intervalo de Consumo de Esgoto:</strong></td>
					<td colspan="3">
					<div align="left"><strong> <html:text
						property="intervaloConsumoEsgotoInicial" size="6" maxlength="6"
						onkeyup="replicaDados(document.forms[0].intervaloConsumoEsgotoInicial, document.forms[0].intervaloConsumoEsgotoFinal);"
						onkeypress="return isCampoNumerico(event);"
						tabindex="14" /> a <html:text
						property="intervaloConsumoEsgotoFinal" size="6" maxlength="6"
						tabindex="15" onkeypress="return isCampoNumerico(event);"/> </strong></div>
					</td>
				</tr>

				<tr>
					<td width="200"><strong>Cliente Respons&aacute;vel:</strong></td>
					<td colspan="3"><strong> <html:text property="idClienteResponsavel"
						size="5" maxlength="3" tabindex="16"
						onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioClientesEspeciaisAction.do', 'idClienteResponsavel', 'Cliente Responsável');return isCampoNumerico(event);" />

					<a href="javascript:abrirPopup('exibirPesquisarClienteAction.do');">
					<img border="0" src="imagens/pesquisa.gif" height="21" width="23" title="Pesquisar Cliente"></a>
					<logic:present name="clienteResponsavelInexistente" scope="request">
						<html:text property="nomeClienteResponsavel" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" maxlength="40" />
					</logic:present> <logic:notPresent
						name="clienteResponsavelInexistente" scope="request">
						<html:text property="nomeClienteResponsavel" readonly="true"
							style="background-color:#EFEFEF; border:0" size="40"
							maxlength="40" />
					</logic:notPresent> <a
						href="javascript:limparClienteResponsavel();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a> </strong></td>
				</tr>

				<tr>
					<td width="200"><strong>Intervalo de Consumo por
					Respons&aacute;vel:</strong></td>
					<td colspan="3">
					<div align="left"><strong> <html:text
						property="intervaloConsumoResponsavelInicial" size="4"
						onkeyup="replicaDados(document.forms[0].intervaloConsumoResponsavelInicial, document.forms[0].intervaloConsumoResponsavelFinal);"
						onkeypress="return isCampoNumerico(event);"
						maxlength="4" tabindex="17" /> a <html:text
						property="intervaloConsumoResponsavelFinal" size="4" maxlength="4"
						tabindex="18" onkeypress="return isCampoNumerico(event);"/> </strong></div>
					</td>
				</tr>

				<tr>
					<td width="200"><strong>Data de Instala&ccedil;&atilde;o do
					Hidr&ocirc;metro:</strong></td>
					<td colspan="3">
					<div align="left">
						<html:text property="dataInstalacaoHidrometroInicial" size="10" maxlength="10" tabindex="19" 
							onkeyup="mascaraData(this,event);replicaDados(document.forms[0].dataInstalacaoHidrometroInicial, document.forms[0].dataInstalacaoHidrometroFinal);"
							onkeypress="return isCampoNumerico(event);"/> 

						<a href="javascript:abrirCalendario('GerarRelatorioClientesEspeciaisActionForm', 'dataInstalacaoHidrometroInicial')"><img
							border="0"
							src="<bean:message key="caminho.imagens"/>calendario.gif"
							width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					<strong> a </strong>
					<html:text property="dataInstalacaoHidrometroFinal" size="10" maxlength="10"
						onkeyup="mascaraData(this,event)" tabindex="20" onkeypress="return isCampoNumerico(event);"/> <a
						href="javascript:abrirCalendario('GerarRelatorioClientesEspeciaisActionForm', 'dataInstalacaoHidrometroFinal')"><img
						border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>(dd/mm/aaaa)</div>
					</td>
				</tr>

				<tr>
					<td width="200"><strong>Capacidade do Hidr&ocirc;metro:</strong></td>
					<td colspan="3"><html:select property="idsCapacidadeHidrometro"
						style="width: 230px;" multiple="mutiple" size="4" tabindex="21">
						<logic:notEmpty name="colecaoCapacidadeHidrometro">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoCapacidadeHidrometro"
								labelProperty="descricao" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>

				<tr>
					<td width="200"><strong>Tarifa de Consumo:</strong></td>
					<td colspan="3"><html:select property="idsTarifaConsumo"
						style="width: 230px;" multiple="mutiple" size="4" tabindex="22">
						<logic:notEmpty name="colecaoTarifaConsumo">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoTarifaConsumo"
								labelProperty="descricao" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>

				<tr>
					<td width="200"><strong>Anormalidade de Leitura:</strong></td>
					<td colspan="3"><strong> <html:text
						property="idLeituraAnormalidade" size="5" maxlength="3"
						tabindex="3"
						onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioClientesEspeciaisAction.do', 'idLeituraAnormalidade', 'Anormalidade de Leitura');return isCampoNumerico(event);" />
					<a
						href="javascript:abrirPopup('exibirPesquisarLeituraAnormalidadeAction.do');"><img
						border="0" src="imagens/pesquisa.gif" height="21" width="23" title="Pesquisar Anormalidade de Leitura"></a>
					<logic:present name="anormalidadeLeituraInexistente"
						scope="request">
						<html:text property="descricaoLeituraAnormalidade" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" maxlength="40" />
					</logic:present> <logic:notPresent
						name="anormalidadeLeituraInexistente" scope="request">
						<html:text property="descricaoLeituraAnormalidade" readonly="true"
							style="background-color:#EFEFEF; border:0" size="40"
							maxlength="40" />
					</logic:notPresent> <a
						href="javascript:limparLeituraAnormalidade();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a> </strong></td>
				</tr>

				<tr>
					<td width="200"><strong>&nbsp;</strong></td>
					<td colspan="3"><html:radio property="leituraAnormalidade"
						tabindex="23" value="1" /><strong>Sim</strong> <html:radio
						property="leituraAnormalidade" tabindex="24" value="2" /><strong>Não</strong>
					<html:radio property="leituraAnormalidade" tabindex="25" value="" /><strong>Todos</strong></td>
				</tr>

				<tr>
					<td width="200"><strong>Anormalidade de Consumo:</strong></td>
					<td colspan="3"><html:select property="idConsumoAnormalidade"
						tabindex="1">
						<logic:notEmpty name="colecaoConsumosAnormalidades">
							<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
							<html:options collection="colecaoConsumosAnormalidades"
								labelProperty="descricao" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>

				<tr>
					<td width="200"><strong>&nbsp;</strong></td>
					<td colspan="3"><html:radio property="consumoAnormalidade"
						tabindex="26" value="1" /><strong>Sim</strong> <html:radio
						property="consumoAnormalidade" tabindex="27" value="2" /><strong>Não</strong>
					<html:radio property="consumoAnormalidade" tabindex="28" value="" /><strong>Todos</strong></td>
				</tr>

				<tr>
					<td width="200">&nbsp;</td>
					<td colspan="3" align="right">
					<div align="left"><strong> </strong></div>
					</td>
				</tr>

				<tr>
					<td width="200"><input type="button" name="ButtonReset"
						class="bottonRightCol" value="Limpar"
						onclick="window.location.href='/gsan/exibirGerarRelatorioClientesEspeciaisAction.do?menu=sim';"></td>
					<td colspan="3" align="right"><gsan:controleAcessoBotao
						name="Button" value="Filtrar"
						onclick="validarForm(document.forms[0]);"
						url="gerarRelatorioClientesEspeciaisAction.do" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form></div>
</body>
</html:html>
