<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.util.ConstantesSistema,java.util.Collection"%>

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
<html:javascript staticJavascript="false"
	formName="FiltrarDadosDiariosArrecadacaoActionForm"
	dynamicJavascript="false" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
<!-- Begin

var bCancel = false; 

function validateFiltrarDadosDiariosArrecadacaoActionForm(form) {                                                                   
	if (bCancel) 
		return true; 
	else 
		return validateCaracterEspecial(form) && validateLong(form); 
} 

function caracteresespeciais () { 
	this.aa = new Array("periodoArrecadacaoInicio", "Informe Período da Arrecadação Inicial.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("periodoArrecadacaoFim", "Informe Período da Arrecadação Final.", new Function ("varName", " return this[varName];"));
} 

function IntegerValidations () { 
	this.aa = new Array("localidade", "Localidade deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("idElo", "Elo Pólo deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.ac = new Array("idArrecadador", "Arrecadador deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
} 

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) 
{
	var form = document.FiltrarDadosDiariosArrecadacaoActionForm;

    if (tipoConsulta == 'localidade') {
    	form.localidade.value = codigoRegistro;
        form.descricaoLocalidade.value = descricaoRegistro;
    }
	if (tipoConsulta == 'arrecadador') {
    	form.idArrecadador.value = codigoRegistro;
        form.nomeArrecadador.value = descricaoRegistro;
        form.nomeArrecadador.style.color = "#000000";
    }
    if (tipoConsulta == 'elo') {
    	form.idElo.value = codigoRegistro;
        form.nomeElo.value = descricaoRegistro;
    }
}

function limparLocalidade() {
	var form = document.FiltrarDadosDiariosArrecadacaoActionForm;
	form.localidade.value = "";
	form.descricaoLocalidade.value = "";
	controleEloLocalidade(document.FiltrarDadosDiariosArrecadacaoActionForm);
}
function limparElo() {
	var form = document.FiltrarDadosDiariosArrecadacaoActionForm;
	form.idElo.value = "";
	form.nomeElo.value = "";
	controleEloLocalidade(document.FiltrarDadosDiariosArrecadacaoActionForm);
	limparLocalidade();
}
function limparArrecadador() {
	var form = document.FiltrarDadosDiariosArrecadacaoActionForm;
	form.idArrecadador.value = "";
	form.nomeArrecadador.value = "";
}    
function replicaDados(campoOrigem, campoDestino){
	campoDestino.value = campoOrigem.value;
}

function limparLocalidadeDoElo(campoOrigem, campoDestino){
	var form = document.FiltrarDadosDiariosArrecadacaoActionForm;
	campoDestino.value = "";
	campoOrigem.value = "";
	form.nomeElo.value="";
}
function controleGerencia(){
	var form = document.FiltrarDadosDiariosArrecadacaoActionForm;
	if(form.idGerenciaRegional.value > 0){
		form.localidade.disabled = true;
		form.idElo.disabled = true;
	}
	else
	{
		form.localidade.disabled = false;
		form.idElo.disabled = false;
	}
}

function controleEloLocalidade(){
	var form = document.FiltrarDadosDiariosArrecadacaoActionForm;
	if(form.idElo.value > 0 || form.localidade.value > 0){
		form.idGerenciaRegional.disabled = true;
	}
	else
	{
		form.idGerenciaRegional.disabled = false;
	}
}
function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado,nomeDependencia,valorDependencia)
{
	if(objetoRelacionado.disabled != true){
		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo + "&"+ nomeDependencia + "=" + valorDependencia, altura, largura);
		}
		else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}
			else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo + "&" + nomeDependencia + "=" + valorDependencia, altura, largura);
			}
		}
	}
}

function validarForm(form)
{
	var retorno = true;
	urlRedirect = "/gsan/filtrarDadosDiariosArrecadacaoAction.do"
	var objReferenciaInicial 	 = returnObject(form, "periodoArrecadacaoInicio");
	var objReferenciaFinal 		 = returnObject(form, "periodoArrecadacaoFim");
	var objIdElo 	 			 = returnObject(form, "idElo");
	var objIdLocalidade 	 	 = returnObject(form, "localidade");
	var objLocalidadeDoELo 	     = returnObject(form, "localidadeDoElo");
	var objEloDiferente 	     = returnObject(form, "eloDiferente");	
	
	if (validateFiltrarDadosDiariosArrecadacaoActionForm(form))
	{
		// Validações do caso de uso
		/*if ( objIdElo.value != "" && objIdLocalidade.value != "" && objLocalidadeDoELo.value != objIdLocalidade.value && eloDiferente != "NAO"){
			alert("Informe uma localidade válida.");
			return false;
		}
		else*/ 
		if ( objReferenciaInicial.value == "" && objReferenciaFinal.value == ""){
			alert("Informe o Mês/Ano Inicial e Final do Período da Arrecadação");
			return false;
		}
		else if (objReferenciaInicial.value != "" && !verificaAnoMesMensagemPersonalizada(objReferenciaInicial,"Mês/Ano Inicial do Período de Referência da Arrecadação inválido.")){
			return false;
		}
		else if (objReferenciaFinal.value != "" && !verificaAnoMesMensagemPersonalizada(objReferenciaFinal,"Mês/Ano Final do Período de Referência da Arrecadação inválido.")){
			return false;
		} else if ((objReferenciaInicial.value != "" && objReferenciaFinal.value != "" ) && (comparaMesAno(objReferenciaInicial.value, ">", objReferenciaFinal.value))){
			alert("Mês/Ano Inicial do Período de Referência da Arrecadação posterior ao Mês/Ano Final.");
			return false;
		}
		if( retorno == true ){
			redirecionarSubmit(urlRedirect);
		}	
	}
}

-->
</script>
</head>
<body leftmargin="5" topmargin="5">
<html:form action="/exibirFiltrarDadosDiariosArrecadacaoAction"
	name="FiltrarDadosDiariosArrecadacaoActionForm"
	type="gcom.gui.arrecadacao.FiltrarDadosDiariosArrecadacaoActionForm"
	onsubmit="return validateFiltrarDadosDiariosArrecadacaoActionForm(this);"
	method="post" focus="periodoArrecadacaoInicio">

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
					<td class="parabg">Filtrar Dados Diários da Arrecadação</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>
			<!-- Início do Corpo - Fernanda Paiva -->
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr><td colspan="4"><strong>Último Processamento: <logic:present name="sistemaParametro"
						property="dataHoraDadosDiariosArrecadacao">
						<bean:write name="sistemaParametro"
							property="dataHoraDadosDiariosArrecadacao" formatKey="datehour.format"/>
					</logic:present></strong></td></tr>
				<tr>
					<td colspan="4">
					<p>Para filtrar os Dados Diários da Arrecadação, informe os dados
					abaixo:
					</p>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%" border="0">
						<tr>
							<td width="30%"><strong>Período da Arrecadação:<font
								color="#FF0000">*</font></strong></td>
							<td><strong> <html:text maxlength="7"
								property="periodoArrecadacaoInicio" size="7"
								onkeyup="mascaraAnoMes(this, event); replicaDados(document.forms[0].periodoArrecadacaoInicio, document.forms[0].periodoArrecadacaoFim);" 
								onkeypress="return isCampoNumerico(event);"/>
							<strong> a</strong> <html:text maxlength="7"
								property="periodoArrecadacaoFim" size="7"
								onkeyup="mascaraAnoMes(this, event);" 
								onkeypress="return isCampoNumerico(event);"/> </strong> (mm/aaaa)</td>
						</tr>
						<tr>
							<td><strong>Gerência Regional:</strong></td>
							<td><html:select property="idGerenciaRegional" tabindex="2"
								onchange="controleGerencia(this.value);">
								<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
								<logic:notEmpty name="colecaoGerenciasRegionais">
									<logic:iterate name="colecaoGerenciasRegionais"
										id="colecaoGerenciasRegionais">
										<option
											value="<bean:write name="colecaoGerenciasRegionais" property="id"/>">
										<bean:write name="colecaoGerenciasRegionais"
											property="nomeAbreviado" /> - <bean:write
											name="colecaoGerenciasRegionais" property="nome" /></option>
									</logic:iterate>
								</logic:notEmpty>
							</html:select></td>
						</tr>
						<tr>
							<td width="26%"><strong>Localidade Pólo:</strong></td>
							<td width="74%"><html:text property="idElo" size="5"
								maxlength="3"
								onkeyup="limparLocalidadeDoElo(document.forms[0].localidade, document.forms[0].descricaoLocalidade); validaEnterComMensagem(event, 'exibirFiltrarDadosDiariosArrecadacaoAction.do', 'idElo', 'Elo Pólo');"
								onchange="controleEloLocalidade(this.value);" 
								onkeypress="return isCampoNumerico(event);"/> <a
								href="javascript:chamarPopup('exibirPesquisarEloAction.do', 'elo', null, null, 400, 800, null,document.forms[0].idElo,'','');">
							<img border="0" src="imagens/pesquisa.gif" height="21" width="23"></a>
							<logic:present name="eloInexistente" scope="request">
								<html:text property="nomeElo" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									size="40" maxlength="40" />
							</logic:present> <logic:notPresent name="eloInexistente"
								scope="request">
								<html:text property="nomeElo" readonly="true"
									style="background-color:#EFEFEF; border:0" size="40"
									maxlength="40" />
							</logic:notPresent> <a href="javascript:limparElo();"> <img
								border="0" src="imagens/limparcampo.gif" height="21" width="23">
							</a></td>
						</tr>
						<tr>
							<td><strong>Localidade:</strong></td>
							<td><strong> <html:text property="localidade" size="5"
								maxlength="3"
								onkeyup="return validaEnterComMensagem(event, 'exibirFiltrarDadosDiariosArrecadacaoAction.do', 'localidade', 'Localidade');"
								onchange="controleEloLocalidade(this.value);" 
								onkeypress="return isCampoNumerico(event);"/> <a
								href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'localidade', null, null, 400, 800, '',document.forms[0].localidade,'idElo',document.forms[0].idElo.value);">
							<img border="0" src="imagens/pesquisa.gif" height="21" width="23"></a>
							<logic:present name="localidadeInexistente" scope="request">
								<html:text property="descricaoLocalidade" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									size="40" maxlength="40" />
							</logic:present> <logic:notPresent name="localidadeInexistente"
								scope="request">
								<html:text property="descricaoLocalidade" readonly="true"
									style="background-color:#EFEFEF; border:0" size="40"
									maxlength="40" />
							</logic:notPresent> <a href="javascript:limparLocalidade();"> <img
								src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar" /></a> </strong></td>
						</tr>
						<tr>
							<td><strong>Arrecadador:</strong></td>
							<td><strong> <html:text property="idArrecadador" size="5"
								maxlength="3"
								onkeyup="return validaEnterComMensagem(event, 'exibirFiltrarDadosDiariosArrecadacaoAction.do', 'idArrecadador', 'Arrecadador');" 
								onkeypress="return isCampoNumerico(event);"/>
							<a
								href="javascript:chamarPopup('exibirPesquisarArrecadadorAction.do', 'arrecadador', null, null, 400, 800, '',document.forms[0].idArrecadador,'','');">
							<img border="0" src="imagens/pesquisa.gif" height="21" width="23"></a>
							<logic:present name="arrecadadorInexistente" scope="request">
								<html:text property="nomeArrecadador" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									size="40" maxlength="40" />
							</logic:present> <logic:notPresent name="arrecadadorInexistente"
								scope="request">
								<html:text property="nomeArrecadador" readonly="true"
									style="background-color:#EFEFEF; border:0" size="40"
									maxlength="40" />
							</logic:notPresent> <a href="javascript:limparArrecadador();"> <img
								src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar" /></a> </strong></td>
						</tr>
						<tr>
							<td><strong>Perfil do Imóvel:</strong></td>
							<td colspan="4" class="style1"><strong> <html:select
								property="imovelPerfil" style="width: 230px;" multiple="mutiple"
								size="4">
								<logic:present name="colecaoImovelPerfil">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
				         </html:option>
									<html:options collection="colecaoImovelPerfil"
										labelProperty="descricao" property="id" />
								</logic:present>
							</html:select></strong></td>

						</tr>
						<tr>
							<td><strong>Situação da Ligação de Água:</strong></td>
							<td colspan="4" class="style1"><strong> <html:select
								property="ligacaoAgua" style="width: 230px;" multiple="mutiple"
								size="4">
								<logic:present name="colecaoSituacaoLigacaoAgua">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
				         </html:option>
									<html:options collection="colecaoSituacaoLigacaoAgua"
										labelProperty="descricao" property="id" />
								</logic:present>
							</html:select></strong></td>

						</tr>
						<tr>
							<td><strong>Situação da Ligação de Esgoto:</strong></td>
							<td colspan="4" class="style1"><strong> <html:select
								property="ligacaoEsgoto" style="width: 230px;"
								multiple="mutiple" size="4">
								<logic:present name="colecaoSituacaoLigacaoEsgoto">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
				         </html:option>
									<html:options collection="colecaoSituacaoLigacaoEsgoto"
										labelProperty="descricao" property="id" />
								</logic:present>
							</html:select></strong></td>
						</tr>
						<tr>
							<td><strong>Categoria:</strong></td>
							<td colspan="4" class="style1"><strong> <html:select
								property="categoria" style="width: 230px;" multiple="mutiple"
								size="4">
								<logic:present name="colecaoCategoria">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
				         </html:option>
									<html:options collection="colecaoCategoria"
										labelProperty="descricao" property="id" />
								</logic:present>
							</html:select></strong></td>
						</tr>
						<tr>
							<td><strong>Esfera do Poder:</strong></td>
							<td colspan="4" class="style1"><strong> <html:select
								property="esferaPoder" style="width: 230px;" multiple="mutiple"
								size="4">
								<logic:present name="colecaoEsferaPoder">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
				         </html:option>
									<html:options collection="colecaoEsferaPoder"
										labelProperty="descricao" property="id" />
								</logic:present>
							</html:select></strong></td>
						</tr>
						<tr>
							<td class="style1"><strong>Tipo do Documento:</strong></td>
							<td colspan="4" class="style1"><strong> <html:select
								property="documentoTipo" style="width: 230px;"
								multiple="mutiple" size="4">
								<logic:present name="colecaoDocumentoTipo">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
				         </html:option>
									<html:options collection="colecaoDocumentoTipo"
										labelProperty="descricaoDocumentoTipo" property="id" />
								</logic:present>
							</html:select></strong></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td align="left"><font color="#FF0000">*</font> Campo
							Obrigat&oacute;rio</td>

						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>
						<input value="Limpar" class="bottonRightCol" type="button" onclick="javascript:window.location.href='/gsan/exibirFiltrarDadosDiariosArrecadacaoAction.do?menu=sim'">

					</td>
					<td align="right"><html:hidden
						name="FiltrarDadosDiariosArrecadacaoActionForm"
						property="localidadeDoElo" /> <gsan:controleAcessoBotao
						name="Button" value="Filtrar"
						onclick="validarForm(document.forms[0]);"
						url="filtrarDadosDiariosArrecadacaoAction.do" /> <%-- <input value="Filtrar" class="bottonRightCol" type="button" onclick="validarForm(document.forms[0]);"> --%>
					</td>
				</tr>
				<tr>
					<!-- Fim do Corpo - Fernanda Paiva  15/05/2006  -->
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
	<script language="JavaScript">
	<!--
		controleGerencia();
	-->
	</script>
	<script language="JavaScript">
	<!--
		controleEloLocalidade();
	-->
	</script>

</html:form>
</body>
</html:html>
