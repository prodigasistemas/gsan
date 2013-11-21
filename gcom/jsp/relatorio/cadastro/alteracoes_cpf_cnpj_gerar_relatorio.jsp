<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@page import="gcom.cadastro.unidade.UnidadeOrganizacional"%>
<%@page import="gcom.seguranca.acesso.usuario.Usuario"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="GerarRelatorioResumoArrecadacaoActionForm" />
<script>

function gerarRelatorio(){
	var form = document.forms[0];
	
	if (form.tipoRelatorioEscolhido == null || form.tipoRelatorioEscolhido.value == '') {
		alert("Informe a Opção de Tipo de Relatório.");
	} else if(form.dataInicial.value == ''
		|| form.dataFinal.value == '') {
		alert("Informe o Período.");
	} else if (!validaDataSemMensagem(form.dataInicial)) {
		alert("Data inicial do período inválida.");
	}  else if (!validaDataSemMensagem(form.dataFinal)) {
		alert("Data final do período inválida.");
	} else if (!comparaData(form.dataInicial.value, "<", form.dataFinal.value)) {
		alert("Data final do período é anterior à data inicial do período.");
	} else if(form.tipoRelatorioEscolhido.value == 2){
		if(form.opcaoTotalizacao == null
			|| form.opcaoTotalizacao.value == '') {
			alert("Informe a Opção de Totalização.");
		} 
		if (form.opcaoTotalizacao.value == 'gerenciaRegional'
			&& (form.idGerenciaRegional == null
			|| form.idGerenciaRegional.value == '')){
			alert("Informar gerência regional para opção de totalização selecionada");
		
		} else if (form.opcaoTotalizacao.value == 'gerenciaRegionalLocalidade'
			&& (form.idGerenciaRegionalporLocalidade == null
			|| form.idGerenciaRegionalporLocalidade.value == '')){
			alert("Informar gerência regional por localidade para opção de totalização selecionada");
		
		} else if (form.opcaoTotalizacao.value == 'unidadeNegocio'
			&& (form.idUnidadeNegocio == null
			|| form.idUnidadeNegocio.value == '')){
			alert("Informar unidade de negócio para opção de totalização selecionada");
		
		} else if (form.opcaoTotalizacao.value == 'localidade'
			&& (form.idLocalidade == null
			|| form.idLocalidade.value == '')){
			alert("Informar localidade para opção de totalização selecionada");
		
		} else {
		toggleBox('demodiv',1);
		}
	} else {
		toggleBox('demodiv',1);
	}
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
	}else if(tipo==3){
		form.tipoRelatorioEscolhido.value = 3;
		document.getElementById('tipoUsuario').style.display = 'none';
		document.getElementById('tipoLocalidade').style.display = 'none';
	}
}

function verificaSelecao(){
	var form = document.forms[0];
	if(form.tipoRelatorioEscolhido.value==1){
		document.getElementById('tipoUsuario').style.display = 'block';
		document.getElementById('tipoLocalidade').style.display = 'none';
	}else if(form.tipoRelatorioEscolhido.value==2){
		document.getElementById('tipoUsuario').style.display = 'none';
		document.getElementById('tipoLocalidade').style.display = 'block';
	}else if(form.tipoRelatorioEscolhido.value==3){
		document.getElementById('tipoUsuario').style.display = 'none';
		document.getElementById('tipoLocalidade').style.display = 'none';
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

function limpar(tipo) {

	var form = document.forms[0];
	
	
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
	
	if (tipo == 'localidade') {
		form.idLocalidade.value = "";
		form.descLocalidade.value = "";
	}

	validarExibicaoUnidade();

}

function abrirPopupValidando(caminho, altura, largura){
	var form = document.forms[0];

	if(form.idUnidadeOrganizacional.disabled==false){
		abrirPopupDeNome(caminho, altura, largura, 'Pesquisar', 'yes');
	}
}

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

    if (tipoConsulta == 'usuario') {
    	form.descUsuario.style.color = "#000000";
  		form.idUsuario.value = codigoRegistro;
   		form.descUsuario.value = descricaoRegistro;
	}
	
    if (tipoConsulta == 'localidade') {
    	form.descLocalidade.style.color = "#000000";
  		form.idLocalidade.value = codigoRegistro;
   		form.descLocalidade.value = descricaoRegistro;
   		selecionarOpcao(7);
   		limparCampos(form.opcaoTotalizacao[7]);
   	}

    validarExibicaoUnidade();
} 

function adicionarUnidadeOrganizacional(){
	var form = document.forms[0];

	if(form.idUnidadeOrganizacional.value!=""){
		form.action='/gsan/exibirGerarRelatorioAlteracoesCpfCnpjAction.do?adicionarUnidadeOrganizacional=OK';
        form.submit();
	}else{
		alert("Informe a Unidade Organizacional para adicionar ao filtro.");
	}
}

function adicionarUsuario(){
	var form = document.forms[0];

	if(form.idUsuario.value!=""){
		form.action='/gsan/exibirGerarRelatorioAlteracoesCpfCnpjAction.do?adicionarUsuario=OK';
        form.submit();
	}else{
		alert("Informe o Usuário para adicionar ao filtro.");
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

function removerUnidade(count) {
	if(confirm('Confirma exclusão da unidade organizacional? Sim ou Não?')) {
		redirecionarSubmitSemUpperCase('/gsan/exibirGerarRelatorioAlteracoesCpfCnpjAction.do?removerUnidadeOrganizacional=OK&idRegistro='+count);
	}
}

function removerUsuario(count) {
	if(confirm('Confirma exclusão do usuário? Sim ou Não?')) {
		redirecionarSubmitSemUpperCase('/gsan/exibirGerarRelatorioAlteracoesCpfCnpjAction.do?removerUsuario=OK&idRegistro='+count);
	}
}

function replicaData() {
		var form = document.forms[0];
		form.dataFinal.value = form.dataInicial.value;
}
	
function limparCampos(opcaoTotalizacao){
	var form = document.forms[0];
	
	if (opcaoTotalizacao.value != 'localidade'){
		limpar('localidade') ;
	}
	
	if (opcaoTotalizacao.value != 'gerenciaRegional'){
		form.idGerenciaRegional.value = "-1";
		form.idGerenciaRegional.disabled=true;
	} else {
		form.idGerenciaRegional.disabled=false;
	}	
	
	if (opcaoTotalizacao.value != 'gerenciaRegionalLocalidade'){
		form.idGerenciaRegionalporLocalidade.value = "-1";
		form.idGerenciaRegionalporLocalidade.disabled=true;
	} else {
		form.idGerenciaRegionalporLocalidade.disabled=false;
	}	
	
	if (opcaoTotalizacao.value != 'unidadeNegocio'){
		form.idUnidadeNegocio.value = "-1";
		form.idUnidadeNegocio.disabled=true;
	} else {
		form.idUnidadeNegocio.disabled=false;
	}	
	
}

function selecionarOpcao(posicao){
	var opcaoTot = document.forms[0].opcaoTotalizacao;
	
	for (i = 0; i < opcaoTot.length; i++) {
		if (i != posicao) {
			opcaoTot[i].checked = false;
		}
	}
	opcaoTot[posicao].checked = true;
}

function verificaOpcao(){
	var form = document.forms[0];
	
	if (form.tipoRelatorioEscolhido.value == 2
		&& form.idLocalidade.value != ''
		&& form.descLocalidade.value != ''){
   		selecionarOpcao(7);
   		limparCampos(form.opcaoTotalizacao[7]);
	}
}
</script>


</head>

<body leftmargin="5" topmargin="5" onload="verificaSelecao();validarExibicaoUnidade();verificarColUnidadeOrganizacional('${requestScope.colecaoUnidadeVazia}');verificaOpcao();">
<html:form action="/exibirGerarRelatorioAlteracoesCpfCnpjAction.do"
	name="GerarRelatorioAlteracoesCpfCnpjActionForm"
	type="gcom.gui.relatorio.cadastro.GerarRelatorioAlteracoesCpfCnpjActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellpadding="0" cellspacing="5">
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
			<html:hidden property="tipoRelatorioEscolhido"/>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img
						src="<bean:message key="caminho.imagens"/>parahead_left.gif"
						border="0" /></td>
					<td class="parabg">Relátorio de Alterações de CPF/CNPJ</td>
					<td width="11" valign="top"><img
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para gerar o relatório de alterações de CPF/CNPJ, informe os dados abaixo:</td>
				</tr>				
				<tr>
					<td width="18%"><strong>Op&ccedil;&atilde;o de Tipo de Relatório<font color="#FF0000">*</font></strong></td>
					<td colspan="1">
						<html:radio property="opcaoTipoRelatorio"
						    value="1" 
							onclick="selecionarFiltro(this.value);"/>
							Usuário
						<html:radio property="opcaoTipoRelatorio"
						    value="2" 
							onclick="selecionarFiltro(this.value);"/>
							Localidade
						<html:radio property="opcaoTipoRelatorio"
						    value="3" 
							onclick="selecionarFiltro(this.value);"/>
							Meio
							</td>
				</tr>
				<tr>
					<td colspan="2">
						<div id="tipoUsuario" style="display:none;">
							<table border="0">
							<tr>
								<td>
									<strong>
										Unidade Superior
									</strong>
								</td>					
								<td colspan="6">
									<html:text property="idUnidadeSuperior"
										size="5" maxlength="5" tabindex="6" 
										onkeypress="validarExibicaoUnidade();validaEnter(event, 'exibirGerarRelatorioAlteracoesCpfCnpjAction.do', 'idUnidadeSuperior'); return isCampoNumerico(event);" />
							
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
										Unidade Organizacional
									</strong>
								</td>					
								<td colspan="6">
									<html:text property="idUnidadeOrganizacional"
										size="5" maxlength="5" tabindex="6"
										onkeypress="validarExibicaoUnidade();validaEnter(event, 'exibirGerarRelatorioAlteracoesCpfCnpjAction.do', 'idUnidadeOrganizacional'); return isCampoNumerico(event);" />
							
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
								<td align="right" colspan="6">
									<input type="button" name="botaoAdicionar"
										class="bottonRightCol" value="Adicionar"
										onclick="adicionarUnidadeOrganizacional();"/>
								</td>
							</tr>	
							<tr>
								<td colspan="7">
								<table width="100%" align="center" bgcolor="#90c7fc">
									<tr bgcolor="#79bbfd">
										<td width="5%" bgcolor="#90c7fc">
										<div align="center"><strong>Remover</strong></div>
										</td>
										<td width="85%" bgcolor="#90c7fc">
										<div align="center"><strong>Unidade Organizacional</strong></div>
										</td>
									</tr>	
										<logic:present name="colecaoUnidadeOrganizacional" scope="session">
											<logic:notEmpty name="colecaoUnidadeOrganizacional" scope="session">
												<%int cont = 0;%>
												<logic:iterate name="colecaoUnidadeOrganizacional" scope="session" 
												id="unidade" type="UnidadeOrganizacional">
													<c:set var="cont" value="${cont+1}" />
													<c:choose>
														<c:when test="${cont%2 == '0'}">
															<tr bgcolor="#cbe5fe">
														</c:when>
														<c:otherwise>
															<tr bgcolor="#FFFFFF">
														</c:otherwise>
													</c:choose>
															<td>
																<div align="center"><font color="#333333"> <img width="14"
																	height="14" border="0"
																	src="<bean:message key="caminho.imagens"/>Error.gif"
																	onclick="javascript:removerUnidade('${cont}');" />
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
										Usuário
									</strong>
								</td>					
								<td>
									<html:text property="idUsuario"
										size="5" maxlength="5" tabindex="6"
										onkeypress="validaEnter(event, 'exibirGerarRelatorioAlteracoesCpfCnpjAction.do', 'idUsuario'); return isCampoNumerico(event);" />
							
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
							<tr>
								<td align="left">
									&nbsp;
								</td>
								<td align="right" colspan="6">
									<input type="button" name="botaoAdicionar"
										class="bottonRightCol" value="Adicionar"
										onclick="adicionarUsuario();"/>
								</td>
							</tr>
							<tr>
								<td colspan="7">
								<table width="100%" align="center" bgcolor="#90c7fc">
									<tr bgcolor="#79bbfd">
										<td width="5%" bgcolor="#90c7fc">
										<div align="center"><strong>Remover</strong></div>
										</td>
										<td width="85%" bgcolor="#90c7fc">
										<div align="center"><strong>Usuário</strong></div>
										</td>
									</tr>	
										<logic:present name="colecaoUsuario" scope="session">
											<logic:notEmpty name="colecaoUsuario" scope="session">
												<%int cont2 = 0;%>
												<logic:iterate name="colecaoUsuario" scope="session" 
												id="usuario" type="Usuario">
														<c:set var="cont2" value="${cont2+1}" />
														<c:choose>
															<c:when test="${cont2%2 == '0'}">
																<tr bgcolor="#cbe5fe">
															</c:when>
															<c:otherwise>
																<tr bgcolor="#FFFFFF">
															</c:otherwise>
														</c:choose>
															<td>
																<div align="center"><font color="#333333"> <img width="14"
																	height="14" border="0"
																	src="<bean:message key="caminho.imagens"/>Error.gif"
																	onclick="javascript:removerUsuario('${cont2}');" />
																</font></div>								
															</td>
															<td width="85%" align="center">
																<bean:write name="usuario" property="nomeUsuario" />
															</td>
														</tr>
												</logic:iterate>
											</logic:notEmpty>
										</logic:present>
								</table>
								</td>
							</tr>
							</table>
						</div>
						
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div id="tipoLocalidade" style="display:none;">
							<table border="0">
							<tr>
								<td><strong>Op&ccedil;&atilde;o de Totaliza&ccedil;&atilde;o<font
									color="#FF0000">*</font></strong></td>
								<td colspan="2" align="left"><html:radio
									property="opcaoTotalizacao" value="estado" onclick = "limparCampos(this);"/> <strong>Estado </strong></td>
							</tr>
							<tr>
								<td><strong> <font color="#FF0000"></font></strong></td>
								<td colspan="2" align="left"><strong> <html:radio
									property="opcaoTotalizacao" value="estadoGerencia" onclick = "limparCampos(this);"/> Estado por
								Ger&ecirc;ncia Regional </strong></td>
							</tr>
							
							<tr>
								<td><strong> <font color="#FF0000"></font></strong></td>
								<td colspan="2" align="left"><strong> 
									<html:radio property="opcaoTotalizacao" value="estadoUnidadeNegocio" 
									onclick = "limparCampos(this);"/>
								<strong>Estado por Unidade de Negócio</strong></strong></td>
							</tr>
							
							<tr>
								<td><strong> <font color="#FF0000"></font></strong></td>
								<td colspan="2" align="left"><strong> <html:radio
									property="opcaoTotalizacao" value="estadoLocalidade" onclick = "limparCampos(this);"/> <strong>Estado</strong>
								por Localidade</strong></td>
							</tr>
							
							
							
							<tr>
								<td><strong> <font color="#FF0000"></font></strong></td>
								<td width="36%" align="left"><strong> <html:radio
									property="opcaoTotalizacao" value="gerenciaRegional" onclick = "limparCampos(this);"/> <strong>Ger&ecirc;ncia
								Regional </strong></strong></td>
								<td width="38%" align="left"><strong><strong> <html:select
									property="idGerenciaRegional" disabled="true">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoGerenciaRegional"
										labelProperty="nome" property="id" />
								</html:select> </strong></strong></td>
							</tr>
							<tr>
								<td><strong> <font color="#FF0000"></font></strong></td>
								<td align="left"><strong> <html:radio property="opcaoTotalizacao"
									value="gerenciaRegionalLocalidade" onclick = "limparCampos(this);"/> <strong>Ger&ecirc;ncia
								Regional</strong> por Localidade</strong></td>
								<td align="left"><strong><strong> <strong> <html:select
									property="idGerenciaRegionalporLocalidade" disabled="true">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoGerenciaRegional"
										labelProperty="nome" property="id" />
								</html:select> </strong> </strong></strong></td>
							</tr>
							
									<tr>
									<td><strong> <font color="#FF0000"></font></strong></td>
									<td width="36%" align="left"><strong> 
										<html:radio property="opcaoTotalizacao" value="unidadeNegocio" 
										onclick = "limparCampos(this);"/>
									<strong>Unidade de Negócio </strong></strong></td>
									<td width="38%" align="left"><strong><strong> 
										<html:select property="idUnidadeNegocio" disabled="true">
										<html:option value="-1">&nbsp;</html:option>
										<html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id" />
									</html:select> </strong></strong></td>
								</tr>
								
							
							<tr>
								<td><strong> <font color="#FF0000"></font></strong></td>
								<td align="left"><strong> <html:radio property="opcaoTotalizacao"
									value="localidade" onclick = "limparCampos(this);" /> Localidade</strong></td>
								<td align="left"><html:text
									property="idLocalidade" size="3" maxlength="3"
									onkeypress="validaEnter(event, 'exibirGerarRelatorioAlteracoesCpfCnpjAction.do', 'idLocalidade');" />
								<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do', 400, 800);">
									<img width="23" height="21" border="0" 
										src="<gsan:i18n key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Localidade"/></a> 	
								
									<logic:present name="localidadeEncontrada" scope="session">
										<html:text property="descLocalidade" readonly="true"
											style="background-color:#EFEFEF; border:0" size="30"
											maxlength="30" />
									</logic:present>
				
									<logic:notPresent name="localidadeEncontrada" scope="session">
										<html:text property="descLocalidade" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											size="30" maxlength="30" />
									</logic:notPresent>	
									
									<a href="javascript:limpar('localidade');">
										<img src="<gsan:i18n key="caminho.imagens"/>limparcampo.gif"
											border="0" title="Limpar Localidade" /> 
									</a></td>
							</tr>
							
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td width="26%"><strong>Por Meio</strong></td>
					<td width="74%"><html:select multiple="true" size="3"
						name="GerarRelatorioAlteracoesCpfCnpjActionForm" property="meio">
						<option value="">&nbsp;</option>
						<logic:notEmpty name="colecaoMeiosSolicitacao">
							<html:options name="request" collection="colecaoMeiosSolicitacao"
								labelProperty="descricao" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>
				<tr>
					<td width="26%"><strong>Período<font color="#FF0000">*</font></strong></td>
					<td width="74%"><html:text
						onkeyup="mascaraData(this, event);replicaData(document.forms[0].dataInicial,document.forms[0].dataFinal);"
						property="dataInicial" size="10" maxlength="10" /> <a
						href="javascript:abrirCalendario('GerarRelatorioAlteracoesCpfCnpjActionForm', 'dataInicial');replicaData(document.forms[0].dataInicial,document.forms[0].dataFinal);">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a>

					&nbsp; <html:text onkeyup="mascaraData(this, event);"
						property="dataFinal" size="10" maxlength="10" /> <a
						href="javascript:abrirCalendario('GerarRelatorioAlteracoesCpfCnpjActionForm', 'dataFinal')"><img
						border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa</td>
				</tr>
				<tr>
					<td colspan="1"><font color="#ff0000">
					<input name="Submit23" class="bottonRightCol" value="Cancelar"
						type="button"
						onclick="window.location.href='/gsan/telaPrincipal.do'"> </font></td>
					<td colspan="4" align="right">
						<table border="0" width="100%">
							<tr>
								<td align="right"  width="60%">&nbsp;</td>
								<td align="right"  width="5%">	
								<input type="button" class="bottonRightCol"
									value="Gerar Relat&oacute;rio"
									onclick="gerarRelatorio();">
								</td>
								<td align="right"  width="35%">&nbsp;</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
				</tr>
			</table>
			
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioAlteracoesCpfCnpjAction.do"/> 
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
