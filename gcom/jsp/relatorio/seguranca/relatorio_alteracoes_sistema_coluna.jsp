<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ include file="/jsp/util/telaespera.jsp"%>

<%@page import="gcom.util.ConstantesSistema"%>
<%@page import="gcom.cadastro.unidade.UnidadeOrganizacional"%>
<%@page import="gcom.cadastro.localidade.UnidadeNegocio"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<gsan:i18n key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<html:javascript staticJavascript="false"  formName="GerarRelatorioAlteracoesSistemaColunaForm"/>
<script language="JavaScript">
<!-- Begin
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];

    if (tipoConsulta == 'unidadeOrganizacional') {

    	form.descUnidadeOrganizacional.style.color = "#000000";
    	form.idUnidadeOrganizacional.value = codigoRegistro;
    	form.descUnidadeOrganizacional.value = descricaoRegistro;
    	
	}

    if (tipoConsulta == 'unidadeSuperior') {

      	form.idUnidadeSuperior.value = codigoRegistro;
      	form.descUnidadeSuperior.value = descricaoRegistro;
		form.descUnidadeSuperior.style.color = "#000000";

    }

    if (tipoConsulta == 'funcionalidade') {
    	form.descFuncionalidade.style.color = "#000000";
   		form.idFuncionalidade.value = codigoRegistro;
   		//form.descFuncionalidade.value = descricaoRegistro;
   	    form.action='exibirGerarRelatorioAlteracoesSistemaColunaAction.do';
        form.submit();

   		return;
    }

    if (tipoConsulta == 'TabelaColuna') {
    	form.descColuna.style.color = "#000000";
  		form.idColuna.value = codigoRegistro;
   		form.descColuna.value = descricaoRegistro;
	}

    if (tipoConsulta == 'usuario') {
    	form.descUsuario.style.color = "#000000";
  		form.idUsuario.value = codigoRegistro;
   		form.descUsuario.value = descricaoRegistro;
	}

    if (tipoConsulta == 'gerenciaRegional') {
    	form.descGerenciaRegional.style.color = "#000000";
    	form.idGerenciaRegional.value = codigoRegistro;
    	form.descGerenciaRegional.value = descricaoRegistro;
    }

    if (tipoConsulta == 'localidade') {
    	form.descLocalidade.style.color = "#000000";
    	form.idLocalidade.value = codigoRegistro;
    	form.descLocalidade.value = descricaoRegistro;
    }

    validarExibicaoUnidade();
} 

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
	if(objetoRelacionado.disabled != true){
		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		} else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			} else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
			}
		}
	}
}

function validarForm(){
	var form = document.forms[0];

	if( validateGerarRelatorioAlteracoesSistemaColunaForm(form)){
		toggleBox('demodiv',1);
	}

	
}

function limpar(tipo) {

	var form = document.forms[0];
	
	if (tipo == 'localidade'){
		form.idLocalidade.value = "";
		form.descLocalidade.value = "";
	}
	
	if (tipo == 'funcionalidade'){
		form.idFuncionalidade.value = "";
		form.descFuncionalidade.value = "";
		form.action='exibirGerarRelatorioAlteracoesSistemaColunaAction.do';
        form.submit();
	}

	if (tipo == 'coluna'){
		form.idColuna.value = "";
		form.descColuna.value = "";
	}

	if (tipo == 'unidadeOrganizacional') {
		form.idUnidadeOrganizacional.value = "";
		form.descUnidadeOrganizacional.value = "";
	}

	if (tipo == 'unidadeSuperior') {
		form.idUnidadeSuperior.value = "";
		form.descUnidadeSuperior.value = "";
	}
	
	if (tipo == 'usuario') {
		form.idUsuario.value = "";
		form.descUsuario.value = "";
	}

	if (tipo == 'gerenciaRegional') {
		form.idGerenciaRegional.value = "";
		form.descGerenciaRegional.value = "";
	}

	validarExibicaoUnidade();

}

function abrirPopupValidando(caminho, altura, largura){
	var form = document.forms[0];

	if(form.idUnidadeOrganizacional.disabled==false){
		abrirPopupDeNome(caminho, altura, largura, 'Pesquisar', 'yes');
	}
}

function mudarValorTipo(valor){
	var form = document.forms[0];
	
	if(valor == 1){
		form.action='exibirGerarRelatorioAlteracoesSistemaColunaAction.do?tipoRelatorio=1';
        form.submit();
	}else if(valor == 2){
		form.action='exibirGerarRelatorioAlteracoesSistemaColunaAction.do?tipoRelatorio=2';
        form.submit();
	}
}
function replicarCampo(campoFinal,dataInicial){
	campoFinal.value = dataInicial.value;
}

function selecionarFiltro(tipo){
	var form = document.forms[0];
	if(tipo==1){
		form.tipoRelatorioEscolhido.value = 1;
		document.getElementById('tipoUsuario').style.display = 'block';
		document.getElementById('tipoLocalidade').style.display = 'none';
	}else if(tipo==2){
		form.tipoRelatorioEscolhido.value = 2;
		document.getElementById('tipoUsuario').style.display = 'none';
		document.getElementById('tipoLocalidade').style.display = 'block';
	}
}

function adicionarUnidadeOrganizacional(){
	var form = document.forms[0];

	if(form.idUnidadeOrganizacional.value!=""){
		form.action='adicionarUnidadeOrganizacionalAction.do';
        form.submit();
	}else{
		alert("Informe a Unidade Organizacional para adicionar ao filtro.");
	}
}

function validarExibicaoUnidade(){

	var form = document.forms[0];

	if(form.idUnidadeSuperior.value!=''){
		form.idUnidadeOrganizacional.value = "";
		form.descUnidadeOrganizacional.value = "";
		form.idUnidadeOrganizacional.disabled = true;
	}else{
		form.idUnidadeOrganizacional.disabled = false;
	}
		
	if(form.idUnidadeOrganizacional.value!=''){
		form.idUnidadeSuperior.value = "";
		form.descUnidadeSuperior.value = "";
		form.idUnidadeSuperior.disabled = true;
	}else{
		form.idUnidadeSuperior.disabled = false;
	}
}

function verificarColUnidadeOrganizacional(flagColecaoVazia){

	var form = document.forms[0];

	if(flagColecaoVazia=='nao'){
		form.idUnidadeSuperior.value = "";
		form.descUnidadeSuperior.value = "";
		form.idUnidadeSuperior.disabled = true;
	}else{
		validarExibicaoUnidade();	
	}
}

-->
</script>

</head>
<html:html>
<body onload="validarExibicaoUnidade();verificarColUnidadeOrganizacional('${requestScope.colecaoUnidadeVazia}');">
<div id="formDiv"><html:form action="/gerarRelatorioAlteracoesSistemaColunaAction.do"
	name="GerarRelatorioAlteracoesSistemaColunaForm"
	type="gcom.gui.relatorio.seguranca.GerarRelatorioAlteracoesSistemaColunaForm"
	method="post">

	<html:hidden property="tipoRelatorioEscolhido"/>
	
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
					<td class="parabg">Gerar Relatório Alterações no Sistema por Coluna</td>
					<td width="11" valign="top">
						<img border="0"
							src="imagens/parahead_right.gif" />
					</td>
				</tr>
			</table>

			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">
						Para gerar o relatório, Informe os campos abaixo:
					</td>
				</tr>

				<tr>
					<td width="20%">
						<strong>
							Relatório:<font color="#FF0000">*</font>
						</strong>
					</td>					
					<td>
						
						<html:radio property="tipoRelatorio"
						    value="1" 
							onclick="selecionarFiltro(this.value);"/>
							Usuário
						<html:radio property="tipoRelatorio"
						    value="2" 
							onclick="selecionarFiltro(this.value);"/>
							Localidade
					</td>
				</tr>

				<tr>
					<td>
						<strong>
							Funcionalidade:
						</strong>
					</td>					
					<td>
						<html:text property="idFuncionalidade"
							size="5" maxlength="3" tabindex="6"
							onkeypress="validaEnter(event, 'exibirGerarRelatorioAlteracoesSistemaColunaAction.do', 'idFuncionalidade'); return isCampoNumerico(event);" />
				
						<a href="javascript:abrirPopup('exibirPesquisarFuncionalidadeAction.do', 400, 800);">
							<img width="23" height="21" border="0" 
								src="<gsan:i18n key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Funcionalidade"/></a> 
						
						<logic:present name="funcionalidadeEncontrada" scope="session">
							<html:text property="descFuncionalidade" readonly="true"
								style="background-color:#EFEFEF; border:0" size="40"
								maxlength="40" />
						</logic:present>
	
						<logic:notPresent name="funcionalidadeEncontrada" scope="session">
							<html:text property="descFuncionalidade" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								size="40" maxlength="40" />
						</logic:notPresent>
										
						<a href="javascript:limpar('funcionalidade');">
							<img src="<gsan:i18n key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Limpar Funcionalidade" /> 
						</a> 
					</td>
				</tr>

				<tr>
					<td>
						<strong>
							Operação:
						</strong>
					</td>					
					<td>
						<html:select property="idOperacao">
							<logic:notEmpty name="GerarRelatorioAlteracoesSistemaColunaForm" property="colecaoOperacoes">
								<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
								<html:optionsCollection name="GerarRelatorioAlteracoesSistemaColunaForm" 
									property="colecaoOperacoes" label="descricao" value="id"/>
							</logic:notEmpty>
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>
							Coluna:
						<font color="#FF0000">*</font></strong>
					</td>					
					<td>
						<html:text property="idColuna"
							size="8" maxlength="8" tabindex="6"
							onkeypress="validaEnter(event, 'exibirGerarRelatorioAlteracoesSistemaColunaAction.do', 'idColuna'); return isCampoNumerico(event);" />
				
						<a href="javascript:abrirPopup('exibirPesquisarColunaTabelaAction.do', 346, 590);">
							<img width="23" height="21" border="0" 
								src="<gsan:i18n key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Coluna"/></a> 
						
						<logic:present name="colunaEncontrada" scope="session">
							<html:text property="descColuna" readonly="true"
								style="background-color:#EFEFEF; border:0" size="40"
								maxlength="40" />
						</logic:present>
						
						<logic:notPresent name="colunaEncontrada" scope="session">
							<html:text property="descColuna" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								size="40" maxlength="40" />
						</logic:notPresent>
										
						<a href="javascript:limpar('coluna');">
							<img src="<gsan:i18n key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Limpar Coluna" /> 
						</a> 
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>
							Por Meio:<font color="#FF0000">*</font>
						</strong>
					</td>					
					<td>
						<html:select property="idMeioSolicitacao">
							<logic:notEmpty name="GerarRelatorioAlteracoesSistemaColunaForm" property="colecaoMeiosSolicitacao">
								<option value="0">TODOS</option>
								<html:optionsCollection name="GerarRelatorioAlteracoesSistemaColunaForm" 
									property="colecaoMeiosSolicitacao" label="descricao" value="id"/>
							</logic:notEmpty>
						</html:select>
					</td>
				</tr>
				
	            <tr> 
	                  <td><strong>Período:<font color="#FF0000">*</font></strong></td>
                  <td> 
                  	<html:text name="GerarRelatorioAlteracoesSistemaColunaForm" 
                  	 	onkeyup="mascaraData(this, event);replicarCampo(document.forms[0].periodoFinal,this);"
                  	 	property="periodoInicial" size="10" maxlength="10"
                  	 	onkeypress="javascript:return isCampoNumerico(event);"/> 
					<a href="javascript:abrirCalendario('GerarRelatorioAlteracoesSistemaColunaForm', 'periodoInicial')">
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" alt="Exibir Calendário" /></a>
                   	<strong>a</strong> 
                    <html:text name="GerarRelatorioAlteracoesSistemaColunaForm" 
                    	onkeyup="mascaraData(this, event);" property="periodoFinal" size="10" maxlength="10" 
                    	onkeypress="javascript:return isCampoNumerico(event);"/> 
					<a href="javascript:abrirCalendario('GerarRelatorioAlteracoesSistemaColunaForm', 'periodoFinal')">
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" alt="Exibir Calendário" /></a>
					<strong>dd/mm/aaaa</strong></td>
				</tr>	
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<div id="tipoUsuario" style="display:none;">
					<table border="0">
					<tr>
							<td>
								<strong>
									Unidade Superior:
								</strong>
							</td>					
							<td>
								<html:text property="idUnidadeSuperior"
									size="5" maxlength="5" tabindex="6" 
									onkeypress="validarExibicaoUnidade();validaEnter(event, 'exibirGerarRelatorioAlteracoesSistemaColunaAction.do', 'idUnidadeSuperior'); return isCampoNumerico(event);" />
						
								<a href="javascript:chamarPopup('exibirPesquisarUnidadeSuperiorAction.do?tipoUnidade=unidadeSuperior', 'unidadeSuperior', null, null, 275, 480, '', document.forms[0].idUnidadeSuperior);">
									<img width="23" height="21" border="0" 
										src="<gsan:i18n key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Unidade Superior"/></a> 
								
								<logic:present name="unidadeSuperiorEncontrada" scope="session">
									<html:text property="descUnidadeSuperior" readonly="true"
										style="background-color:#EFEFEF; border:0" size="40"
										maxlength="40" />
								</logic:present>
			
								<logic:notPresent name="unidadeSuperiorEncontrada" scope="session">
									<html:text property="descUnidadeSuperior" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000"
										size="40" maxlength="40" />
								</logic:notPresent>
												
								<a href="javascript:limpar('unidadeSuperior');">
									<img src="<gsan:i18n key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Limpar Unidade Superior" /> 
								</a> 
							</td>	
					</tr>
					<tr>
							<td>
								<strong>
									Unidade Organizacional:
								</strong>
							</td>					
							<td>
								<html:text property="idUnidadeOrganizacional"
									size="5" maxlength="5" tabindex="6"
									onkeypress="validarExibicaoUnidade();validaEnter(event, 'exibirGerarRelatorioAlteracoesSistemaColunaAction.do', 'idUnidadeOrganizacional'); return isCampoNumerico(event);" />
						
								<a href="javascript:abrirPopupValidando('exibirPesquisarUnidadeOrganizacionalAction.do',  250, 495);">
									<img width="23" height="21" border="0" 
										src="<gsan:i18n key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Unidade Organizacional"/></a> 
								
								<logic:present name="unidadeOrganizacionalEncontrada" scope="session">
									<html:text property="descUnidadeOrganizacional" readonly="true"
										style="background-color:#EFEFEF; border:0" size="40"
										maxlength="40" />
								</logic:present>
			
								<logic:notPresent name="unidadeOrganizacionalEncontrada" scope="session">
									<html:text property="descUnidadeOrganizacional" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000"
										size="40" maxlength="40" />
								</logic:notPresent>
												
								<a href="javascript:limpar('unidadeOrganizacional');">
									<img src="<gsan:i18n key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Limpar Unidade Organizacional" /> 
								</a> 
							</td>	
					</tr>
					<tr>
						<td align="left">
							&nbsp;
						</td>
						<td align="right">
							<input type="button" name="botaoAdicionar"
								class="bottonRightCol" value="Adicionar"
								onclick="adicionarUnidadeOrganizacional();"/>
						</td>
					</tr>	
					<tr>
						<td colspan="2">
						<table width="100%" align="center" bgcolor="#90c7fc">
							<tr bgcolor="#79bbfd">
								<td width="5%" bgcolor="#90c7fc">
								<div align="center"><strong>Todos</strong></div>
								</td>
								<td width="85%" bgcolor="#90c7fc">
								<div align="center"><strong>Unidade Organizacional</strong></div>
								</td>
							</tr>	
								<logic:present name="GerarRelatorioAlteracoesSistemaColunaForm" property="colecaoUnidadeOrganizacional">
									<logic:notEmpty name="GerarRelatorioAlteracoesSistemaColunaForm" property="colecaoUnidadeOrganizacional">
										<%int cont = 0;%>
										<logic:iterate name="GerarRelatorioAlteracoesSistemaColunaForm" property="colecaoUnidadeOrganizacional" 
										id="unidade" type="UnidadeOrganizacional">
												<%cont = cont + 1;
												if (cont % 2 == 0) {%>
												<tr bgcolor="#cbe5fe">
													<%} else {%>
												<tr bgcolor="#FFFFFF">
													<%}%>
													<td>
														<div align="center"><font color="#333333"> <img width="14"
															height="14" border="0"
															src="<bean:message key="caminho.imagens"/>Error.gif"
															onclick="javascript:if(confirm('Confirma remoção?')){redirecionarSubmitSemUpperCase('removerUnidadeOrganizacionalAction.do?id=<bean:write name="unidade" property="id" />');}" />
														</font></div>								
													</td>
													<td width="85%" align="center">
														<bean:write name="unidade" property="descricao" />
													</td>
												</tr>
										</logic:iterate>
									</logic:notEmpty>
								</logic:present>
						</table>
						</td>
					</tr>			
					<tr>
							<td>
								<strong>
									Usuário:
								</strong>
							</td>					
							<td>
								<html:text property="idUsuario"
									size="5" maxlength="5" tabindex="6"
									onkeypress="validaEnter(event, 'exibirGerarRelatorioAlteracoesSistemaColunaAction.do', 'idUsuario'); return isCampoNumerico(event);" />
						
								<a href="javascript:abrirPopup('exibirUsuarioPesquisar.do?indicadorUsoTodos=1', 250, 495);">
									<img width="23" height="21" border="0" 
										src="<gsan:i18n key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Usuário"/></a> 
								
								<logic:present name="usuarioEncontrado" scope="session">
									<html:text property="descUsuario" readonly="true"
										style="background-color:#EFEFEF; border:0" size="40"
										maxlength="40" />
								</logic:present>
			
								<logic:notPresent name="usuarioEncontrado" scope="session">
									<html:text property="descUsuario" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000"
										size="40" maxlength="40" />
								</logic:notPresent>
												
								<a href="javascript:limpar('usuario');">
									<img src="<gsan:i18n key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Limpar Usuário" /> 
								</a> 
							</td>	
					</tr>
					</table>
					</div>	
					</td>
				</tr>
				<tr>
					<td colspan="2">	
					<div id="tipoLocalidade" style="display:none;">
					<table>
						<tr>
							<td>
								<strong>
									Gerência Regional:
								</strong>
							</td>					
							<td>
								<html:text property="idGerenciaRegional"
									size="5" maxlength="5" tabindex="6"
									onkeypress="validaEnter(event, 'exibirGerarRelatorioAlteracoesSistemaColunaAction.do', 'idGerenciaRegional'); return isCampoNumerico(event);" />
						
								<a href="javascript:chamarPopup('exibirPesquisarGerenciaRegionalAction.do', 'Gerência Regional', null, null, 800, 490, '',document.forms[0].idGerenciaRegional);">
									<img width="23" height="21" border="0" 
										src="<gsan:i18n key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Gerencia Regional"/></a> 
								
								<logic:present name="gerenciaRegionalEncontrada" scope="session">
									<html:text property="descGerenciaRegional" readonly="true"
										style="background-color:#EFEFEF; border:0" size="40"
										maxlength="40" />
								</logic:present>
			
								<logic:notPresent name="gerenciaRegionalEncontrada" scope="session">
									<html:text property="descGerenciaRegional" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000"
										size="40" maxlength="40" />
								</logic:notPresent>
												
								<a href="javascript:limpar('gerenciaRegional');">
									<img src="<gsan:i18n key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Limpar Gerencia Regional" /> 
								</a> 
							</td>	
						</tr>
						<tr>
					      	<td width="183" HEIGHT="30"><strong>Unidade de Negócio:</strong></td>
					        <td>
								<html:select property="idUnidadeNegocio" style="width: 250px;" tabindex="5">
									<html:option value="">&nbsp;</html:option>
									<logic:iterate name="colecaoUnidadeNegocio" id="unidadeNegocio" type="UnidadeNegocio">
										<html:option value="<%=""+ unidadeNegocio.getId()%>">
										<%= unidadeNegocio.getNome()%></html:option>
									</logic:iterate>
								</html:select>
							</td>
					     </tr>
						<tr>
							<td>
								<strong>
									Localidade:
								</strong>
							</td>					
							<td>
								<html:text property="idLocalidade"
									size="5" maxlength="5" tabindex="6"
									onkeypress="validaEnter(event, 'exibirGerarRelatorioAlteracoesSistemaColunaAction.do', 'idLocalidade'); return isCampoNumerico(event);" />
						
								<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do', 400, 800);">
									<img width="23" height="21" border="0" 
										src="<gsan:i18n key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Localidade"/></a> 
								
								<logic:present name="localidadeEncontrada" scope="session">
									<html:text property="descLocalidade" readonly="true"
										style="background-color:#EFEFEF; border:0" size="40"
										maxlength="40" />
								</logic:present>
			
								<logic:notPresent name="localidadeEncontrada" scope="session">
									<html:text property="descLocalidade" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000"
										size="40" maxlength="40" />
								</logic:notPresent>
												
								<a href="javascript:limpar('localidade');">
									<img src="<gsan:i18n key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Limpar Localidade" /> 
								</a> 
							</td>	
						</tr>
					</table>
					</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>				
				<tr>
					<td><p>&nbsp;</p></td>
					<td align="right">
						<div align="right"><strong><font color="#FF0000">*</font></strong>
							Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
							<td width="25%">
								<input name="Button" type="button"
									class="bottonRightCol" value="Limpar" align="left"
									onclick="window.location.href='<html:rewrite page="/exibirGerarRelatorioAlteracoesSistemaColunaAction.do?menu=sim"/>'"/> 	
								<input type="button" name="botaoCancelar"
									class="bottonRightCol" value="Cancelar"
									onclick="window.location.href='/gsan/telaPrincipal.do';"/>
							</td>
							<td align="right">						
							<div align="right">
								<a href="javascript:validarForm();">
								<img border="0"
									src="<bean:message key="caminho.imagens"/>print.gif"
									title="Gerar Relatório" /></a>
							</div>		
							</td>			
				</tr>
			</table>
			</td>
		</tr>		
	</table>
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio_tela_espera.jsp?relatorio=gerarRelatorioAlteracoesSistemaColunaAction.do"/>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form></div>
<logic:present name="GerarRelatorioAlteracoesSistemaColunaForm" property="tipoRelatorio">
		<logic:equal name="GerarRelatorioAlteracoesSistemaColunaForm" property="tipoRelatorio" value="1"> 
			<script>selecionarFiltro(1);</script>
		</logic:equal>
		<logic:equal name="GerarRelatorioAlteracoesSistemaColunaForm" property="tipoRelatorio" value="2">
			<script>selecionarFiltro(2);</script>
		</logic:equal>
</logic:present>
</body>
</html:html>
