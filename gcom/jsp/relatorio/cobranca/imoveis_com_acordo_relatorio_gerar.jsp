<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>


<%@page import="gcom.util.Util" isELIgnored="false"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="GerarRelatorioImoveisComAcordoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<SCRIPT LANGUAGE="JavaScript"><!--

	
	function limparLocalidadeOrigem() {
		var form = document.forms[0];
		form.idLocalidadeInicial.value = "";
		form.nomeLocalidadeInicial.value = "";
		form.idLocalidadeFinal.value = "";
		form.nomeLocalidadeFinal.value = "";
		bloquearBotaoGerar();
	}
	
	function limparLocalidadeOrigemTecla() {
		var form = document.forms[0];
		form.nomeLocalidadeInicial.value = "";
		form.nomeLocalidadeFinal.value = "";
		bloquearBotaoGerar();
	}
	
	function limparLocalidadeDestino() {
		var form = document.forms[0];
		form.idLocalidadeFinal.value = "";
		form.nomeLocalidadeFinal.value = "";
		bloquearBotaoGerar();
	}
	
	function limparLocalidadeDestinoTecla() {
		var form = document.forms[0];
		form.nomeLocalidadeFinal.value = "";
		bloquearBotaoGerar();
	}
	
	function limparSetorComercialOrigem() {
		var form = document.forms[0];
		form.setorComercialInicial.value = "";
		form.nomeSetorComercialInicial.value = "";
		form.setorComercialFinal.value = "";
		form.nomeSetorComercialFinal.value = "";
		bloquearBotaoGerar();
	}
	
	function limparSetorComercialOrigemTecla() {
		var form = document.forms[0];
		form.nomeSetorComercialInicial.value = "";
		form.nomeSetorComercialFinal.value = "";
		bloquearBotaoGerar();
	}
	
	function limparSetorComercialDestino() {
		var form = document.forms[0];
		form.setorComercialFinal.value = "";
		form.nomeSetorComercialFinal.value = "";
		bloquearBotaoGerar();
	}
	
	function limparSetorComercialDestinoTecla() {
		var form = document.forms[0];
		form.nomeSetorComercialFinal.value = "";
		bloquearBotaoGerar();
	}
	
	function pesquisarLocalidadeOrigem() {
		var form = document.forms[0];

		if (form.idLocalidadeInicial.disabled == false)  {
			form.tipoPesquisa.value = 'origem';
			abrirPopup('exibirPesquisarLocalidadeAction.do', 275, 480);
		}
	}
	
	function pesquisarLocalidadeDestino() {
		var form = document.forms[0];

		if (form.idLocalidadeFinal.disabled == false)  {
			form.tipoPesquisa.value = 'destino';
			abrirPopup('exibirPesquisarLocalidadeAction.do', 275, 480);
		}
	}
	
	function pesquisarSetorComercialOrigem() {
		var form = document.forms[0];

		if (form.setorComercialInicial.disabled == false) {
			form.tipoPesquisa.value = 'origem';
			abrirPopupDependencia('exibirPesquisarSetorComercialAction.do', form.idLocalidadeInicial.value, 'Localidade Inicial', 275, 480);
		}
	}
	
	function pesquisarSetorComercialDestino() {
		var form = document.forms[0];
		
		if (form.setorComercialFinal.disabled == false) {
			form.tipoPesquisa.value = 'destino';
			abrirPopupDependencia('exibirPesquisarSetorComercialAction.do', form.idLocalidadeInicial.value, 'Localidade Inicial', 275, 480);
		}
	}
	
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.forms[0];
   
	    if (tipoConsulta == 'localidade') {
    		if (form.tipoPesquisa.value == 'origem') {
    			form.idLocalidadeInicial.value = codigoRegistro;
				form.nomeLocalidadeInicial.value = descricaoRegistro;
				form.nomeLocalidadeInicial.style.color = "#000000";
				form.idLocalidadeFinal.value = codigoRegistro;
				form.nomeLocalidadeFinal.value = descricaoRegistro;
				form.nomeLocalidadeFinal.style.color = "#000000";
			} else {
				form.idLocalidadeFinal.value = codigoRegistro;
				form.nomeLocalidadeFinal.value = descricaoRegistro;
				form.nomeLocalidadeFinal.style.color = "#000000";
			}
    	} else if (tipoConsulta == 'setorComercial') {
    		if (form.tipoPesquisa.value == 'origem') {
    			form.setorComercialInicial.value = codigoRegistro;
				form.nomeSetorComercialInicial.value = descricaoRegistro;
				form.nomeSetorComercialInicial.style.color = "#000000";
				form.setorComercialFinal.value = codigoRegistro;
				form.nomeSetorComercialFinal.value = descricaoRegistro;
				form.nomeSetorComercialFinal.style.color = "#000000";
			} else {
				form.setorComercialFinal.value = codigoRegistro;
				form.nomeSetorComercialFinal.value = descricaoRegistro;
				form.nomeSetorComercialFinal.style.color = "#000000";
			}
    	}
    
    }

	function validarInformacaoSetorComercial(){
		var form =  document.forms[0];
		
		if(form.setorComercialInicial.value !=null && form.setorComercialInicial.value != ''
		&& form.idLocalidadeInicial.value != form.idLocalidadeFinal.value){
			alert('A Localidade Final não pode ser diferente da Localidade Inicial');
			return false;
		
		}else{
		
		return true;
		}
	
	}
	
	function validarSetorComercial(){
		var form = document.forms[0];
		
		if(form.idUnidadeNegocio.value == null || form.idUnidadeNegocio.value == ''){
		
			if(form.setorComercialInicial.value != null && form.setorComercialInicial.value != '' 
					&& (form.setorComercialFinal.value == null || form.setorComercialFinal.value == '')){
					
				alert('Informe Setor Comercial Final.');
				return false;		
					
			}else if (form.setorComercialInicial.value != null && form.setorComercialInicial.value != '' 
					&& (form.setorComercialFinal.value == null || form.setorComercialFinal.value == '')){		
				
				alert('Informe Setor Comercial Inicial.');	
				return false;
				
			}else{
				return true;
			}
		}else{
				return true;
			}
	}
	
	function validarLocalidade(){
		var form = document.forms[0];
		
		if(form.idUnidadeNegocio.value == null || form.idUnidadeNegocio.value == '') {
			
			if(form.idLocalidadeInicial.value != null && form.idLocalidadeInicial.value != '' 
				&& (form.idLocalidadeFinal.value == null || form.idLocalidadeFinal.value == '')){
					
				alert('Informe Localidade Final.');	
				return false;	
					
			}else if ((form.idLocalidadeFinal.value != null || form.idLocalidadeFinal.value != '')
						&& (form.idLocalidadeInicial.value == null || form.idLocalidadeInicial.value == '')){		
				
				alert('Informe Localidade Inicial.');	
				return false;
				
			}else{
				return true;
			}
			
		}else{
				return true;
			}
	}

	
	function validaForm() {
		var form =  document.forms[0];
			if (validateGerarRelatorioImoveisComAcordoActionForm(form)){
					submeterFormPadrao(form);
				}
	}
	
	function pesquisarQuantidadeImoveis() {
		var form = document.forms[0];
		if(validateGerarRelatorioImoveisComAcordoActionForm(form)){
			form.action = 'exibirGerarRelatorioImoveisComAcordoAction.do?quantidade=sim';
		    submeterFormPadrao(form);
	    }
	    
	}
	
	function liberaFiltrar(){
	
		var form = document.forms[0];
		
		if(form.periodoInicialAcordo.value != null  && form.periodoInicialAcordo.value != ''
			&& form.periodoFinalAcordo.value != null  && form.periodoFinalAcordo.value != ''){
		
			form.filtrar.disabled = false;
		}else{
			form.filtrar.disabled = true;
		}
	
	}
	
	function verificarBotaoGerar(){
		var form = document.forms[0];
		
		if (form.quantidade.value > 0){
		
			form.filtrar.disabled = false;
		}else{
			form.filtrar.disabled = true;
		}
	
	}
	
	function bloquearBotaoGerar(){
		var form = document.forms[0];
		form.quantidade.value = '';
		form.filtrar.disabled = true;
	}
	
	
--></script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');liberaFiltrar();verificarBotaoGerar();">

<html:form action="/gerarRelatorioImoveisComAcordoAction"
	name="GerarRelatorioImoveisComAcordoActionForm"
	type="gcom.gui.relatorio.cobranca.GerarRelatorioImoveisComAcordoActionForm"
	method="post">

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
					<td class="parabg">Gerar Relatório de Acordos</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<input type="hidden" name="tipoPesquisa" />
			<table width="100%" border="0">

				<tr>
					<td colspan="2">Para filtrar o(s) imóvel(eis), informe os dados
					abaixo:</td>
				</tr>
				<tr>
					<td><strong>Período Fim do Acordo:<font color="#FF0000">*</font></strong></td>
					<td><strong> <html:text maxlength="10"
						property="periodoInicialAcordo" size="10" tabindex="1" 
						onkeyup="mascaraData(this, event);  replicarCampo(document.forms[0].periodoFinalAcordo, document.forms[0].periodoInicialAcordo);bloquearBotaoGerar();" />
					<a
						href="javascript:abrirCalendarioReplicando('GerarRelatorioImoveisComAcordoActionForm', 'periodoInicialAcordo', 'periodoFinalAcordo');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					</strong> <html:text maxlength="10" property="periodoFinalAcordo"
						tabindex="2" size="10" onkeypress="javascript:bloquearBotaoGerar();"
						onkeyup="mascaraData(this, event);bloquearBotaoGerar();" /> <a
						href="javascript:abrirCalendario('GerarRelatorioImoveisComAcordoActionForm', 'periodoFinalAcordo')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					(dd/mm/aaaa)</td>
				</tr>
				<tr>
					<td width="30%"><strong>Unidade Negócio:</strong></td>
					<td><html:select property="idUnidadeNegocio" tabindex="3" >
						<logic:notEmpty name="colecaoUnidadeNegocio">
							<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
							<html:options collection="colecaoUnidadeNegocio"
								labelProperty="nome" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>
				<tr>
					<td width="30%"><strong>Gerência Regional:</strong></td>
					<td><html:select property="idGerenciaRegional" tabindex="4" >
						<logic:notEmpty name="colecaoGerenciaRegional">
							<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
							<html:options collection="colecaoGerenciaRegional"
								labelProperty="nome" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>

				<tr>
					<td><strong>Localidade Inicial:</strong></td>
					<td><html:text maxlength="3" property="idLocalidadeInicial"
						size="3" 
						onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioImoveisComAcordoAction.do', 'idLocalidadeInicial' ,'Localidade Inicial');"
						tabindex="5"
						onkeyup="javascript:replicarCampo(form.idLocalidadeFinal, form.idLocalidadeInicial);limparLocalidadeOrigemTecla();bloqueiaDados();desabilitaEmpresa();bloquearBotaoGerar();" />
					<a href="javascript:pesquisarLocalidadeOrigem();"> <img width="23"
						height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Localidade" /></a> <logic:present
						name="localidadeOrigemInexistente" scope="request">
						<html:text property="nomeLocalidadeInicial" size="40"
							maxlength="40" readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent
						name="localidadeOrigemInexistente" scope="request">
						<html:text property="nomeLocalidadeInicial" size="40"
							maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparLocalidadeOrigem();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Localidade" /></a></td>
				</tr>
				<tr>
					<td><strong>Setor Comercial Inicial:</strong></td>
					<td><html:text maxlength="3" property="setorComercialInicial"
						size="3" 
						onkeypress="validaEnterDependenciaComMensagem(event, 'exibirGerarRelatorioImoveisComAcordoAction.do', document.forms[0].setorComercialInicial, document.forms[0].idLocalidadeInicial.value, 'Localidade Inicial', 'Setor Comercial Inicial');"
						tabindex="6"
						onkeyup="javascript:replicarCampo(form.setorComercialFinal, form.setorComercialInicial);limparSetorComercialOrigemTecla();desabilitaEmpresa();bloquearBotaoGerar();" />
					<a href="javascript:pesquisarSetorComercialOrigem();"> <img
						width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Setor Comercial" /></a> <logic:present
						name="setorComercialOrigemInexistente" scope="request">
						<html:text property="nomeSetorComercialInicial" size="40"
							maxlength="40" readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent
						name="setorComercialOrigemInexistente" scope="request">
						<html:text property="nomeSetorComercialInicial" size="40"
							maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a
						href="javascript:limparSetorComercialOrigem();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Setor Comercial" /></a></td>
				</tr>

				<tr>
					<td><strong>Rota Inicial:</strong></td>
					<td><html:text maxlength="5" property="rotaInicial" tabindex="7"  onkeyup="bloquearBotaoGerar();"
						size="5" /></td>
				</tr>

				<tr>
					<td><strong>Sequencial Inicial da Rota:</strong></td>
					<td><html:text maxlength="5" property="sequencialRotaInicial" onkeyup="bloquearBotaoGerar();"
						tabindex="8" size="5" /></td>
				</tr>
				<tr>
					<td><strong>Localidade Final:</strong></td>
					<td><html:text maxlength="3" property="idLocalidadeFinal" size="3" 
						onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioImoveisComAcordoAction.do', 'idLocalidadeFinal' ,'Localidade Final');"
						tabindex="9"
						onkeyup="limparLocalidadeDestinoTecla();desabilitaEmpresa();bloquearBotaoGerar();" />
					<a href="javascript:pesquisarLocalidadeDestino();"> <img width="23"
						height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Localidade" /></a> <logic:present
						name="localidadeDestinoInexistente" scope="request">
						<html:text property="nomeLocalidadeFinal" size="40" maxlength="40"
							readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent
						name="localidadeDestinoInexistente" scope="request">
						<html:text property="nomeLocalidadeFinal" size="40" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparLocalidadeDestino();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Localidade" /></a></td>
				</tr>
				<tr>
					<td><strong>Setor Comercial Final:</strong></td>
					<td><html:text maxlength="3" property="setorComercialFinal" 
						size="3"
						onkeypress="validaEnterDependenciaComMensagem(event, 'exibirGerarRelatorioImoveisComAcordoAction.do', document.forms[0].setorComercialFinal, document.forms[0].idLocalidadeFinal.value, 'Localidade Final', 'Setor Comercial Final');"
						tabindex="10"
						onkeyup="limparSetorComercialDestinoTecla();desabilitaEmpresa();bloquearBotaoGerar();" />
					<a href="javascript:pesquisarSetorComercialDestino();"> <img
						width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Setor Comercial" /></a> <logic:present
						name="setorComercialDestinoInexistente" scope="request">
						<html:text property="nomeSetorComercialFinal" size="40"
							maxlength="40" readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent
						name="setorComercialDestinoInexistente" scope="request">
						<html:text property="nomeSetorComercialFinal" size="40"
							maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a
						href="javascript:limparSetorComercialDestino();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Setor Comercial" /></a></td>
				</tr>

				<tr>
					<td><strong>Rota Final:</strong></td>
					<td><html:text maxlength="5" property="rotaFinal" tabindex="11"  onkeyup="bloquearBotaoGerar();"
						size="5" /></td>
				</tr>
				<tr>
					<td><strong>Sequencial Final da Rota:</strong></td>
					<td><html:text maxlength="5" property="sequencialRotaFinal"  onkeyup="bloquearBotaoGerar();"
						tabindex="12" size="5" /></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="button"
						name="selecionar" class="bottonRightCol" value="Selecionar"
						onClick="javascript:pesquisarQuantidadeImoveis();" /></td>
				</tr>

				<tr>
					<td colspan="2">
					<hr>
					</td>

				</tr>
				<tr>
					<td><strong>Quantidade de Imóveis:</strong></td>
					<td><html:text maxlength="10" property="quantidade" tabindex="13"
						size="10" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /></td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirGerarRelatorioImoveisComAcordoAction.do?menu=sim"/>'">
					<input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="javascript:window.location.href='/gsan/telaPrincipal.do'"></td>
					<td colspan="2" align="right">
						<input type="button" name="filtrar" class="bottonRightCol" value="Gerar"
							onClick="javascript:validaForm();" />
						</td>
				</tr>
			</table>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
