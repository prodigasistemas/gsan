<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="gcom.util.ConstantesSistema"%>
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

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="GerarRelatorioContasEmRevisaoActionForm" />

<script language="JavaScript">

function validarForm(form){

	if(validateGerarRelatorioContasEmRevisaoActionForm(form)){
		if(form.idLocalidadeInicial.value.length == 0) {
			alert('Informe Localidade Inicial');
		} else if(form.idLocalidadeFinal.value.length == 0) {
			alert('Informe Localidade Final');
		} else {
	    	submeterFormPadrao(form);
	    }
	}
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'localidade') {
      form.idLocalidadeInicial.value = codigoRegistro;
      form.nomeLocalidadeInicial.value = descricaoRegistro;
      form.nomeLocalidadeInicial.style.color = "#000000";
    }
    
    if (tipoConsulta == 'elo') {
      form.idElo.value = codigoRegistro;
      form.nomeElo.value = descricaoRegistro;
      form.nomeElo.style.color = "#000000";
    }
    
}

function loadUnidadeNegocios() {
    var form = document.forms[0];
    
	var idGerenciaRegional = form.idGerenciaRegional.value;
	window.location.href = "exibirGerarRelatorioContasEmRevisaoAction.do?idGerenciaRegional=" + idGerenciaRegional
}

function limparEloTecla(){
	var form = document.forms[0];

	form.nomeElo.value = "";
}

function limparElo(){
	var form = document.forms[0];

	form.idElo.value = "";
	form.nomeElo.value = "";
}

function limparLocalidadeInicialTecla(){
	var form = document.forms[0];

	form.nomeLocalidadeInicial.value = "";
}

function limparLocalidadeFinalTecla(){
	var form = document.forms[0];

	form.nomeLocalidadeFinal.value = "";
}

function limparLocalidadeInicial(){
	var form = document.forms[0];

	form.idLocalidadeInicial.value = "";
	form.nomeLocalidadeInicial.value = "";
}

function limparLocalidadeFinal(){
	var form = document.forms[0];

	form.idLocalidadeFinal.value = "";
	form.nomeLocalidadeFinal.value = "";
}

function limparSetorComercialInicial(){
	var form = document.forms[0];

	form.codigoSetorComercialInicial.value = "";
	form.nomeSetorComercialInicial.value = "";
}

function limparSetorComercialFinal(){
	var form = document.forms[0];

	form.codigoSetorComercialFinal.value = "";
	form.nomeSetorComercialFinal.value = "";
}
function desabilitaIntervaloDiferente(){
	var form = document.GerarRelatorioContasEmRevisaoActionForm;	
		    if(form.idLocalidadeInicial.value != form.idLocalidadeFinal.value){	        
		        form.codigoSetorComercialInicial.disabled = true;		
			 	form.codigoSetorComercialFinal.disabled = true;
			 	form.codigoSetorComercialInicial.value = "";		
			 	form.codigoSetorComercialFinal.value = "";
	 			form.nomeSetorComercialInicial.value = "";		
			 	form.nomeSetorComercialFinal.value = "";
			  }else{
			 	form.codigoSetorComercialInicial.disabled = false;		
			 	form.codigoSetorComercialFinal.disabled = false;
			 }		
}
function reload(){
	var form = document.GerarRelatorioContasEmRevisaoActionForm;
	
	form.action = 'exibirGerarRelatorioContasEmRevisaoAction.do';
	form.submit();
}

</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');desabilitaIntervaloDiferente();">

<html:form action="/gerarRelatorioContasEmRevisaoAction"
	name="GerarRelatorioContasEmRevisaoActionForm"
	type="gcom.gui.relatorio.faturamento.GerarRelatorioContasEmRevisaoActionForm"
	method="post">

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
			<td width="600" valign="top" bgcolor="#003399" class="centercoltext">
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
					<td class="parabg">Gerar Relatórios de Contas em Revisão</td>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para gerar o relatório de contas em revisão,
					informe os dados abaixo:</td>
				</tr>
				<tr>
					<td></td>
				</tr>
				
				<tr>
					<td><strong>Ger&ecirc;ncia Regional:</strong></td>
					<td><html:select property="idGerenciaRegional" tabindex="1" onchange="loadUnidadeNegocios()">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoGerenciaRegional"
							labelProperty="nome" property="id" />
					</html:select></td>
				</tr>
				
				<tr>
					<td width="200">
						<strong>Unidade de Negócio:</strong>
					</td>
					<td colspan="3">
						<html:select property="idUnidadeNegocio" tabindex="2" style="width:200px;">
							<html:option value="-1"> &nbsp; </html:option>
							<html:options collection="colecaoUnidadeNegocio" property="id" 
							labelProperty="nome" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td><strong>Localidade Inicial:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text maxlength="3"
						property="idLocalidadeInicial" size="4" tabindex="3"
						onkeyup="javascript:limparLocalidadeInicialTecla();limparLocalidadeFinalTecla();replicarCampo(document.forms[0].idLocalidadeFinal, document.forms[0].idLocalidadeInicial)"
						onkeypress="javascript:return validaEnter(event, 'exibirGerarRelatorioContasEmRevisaoAction.do', 'idLocalidadeInicial');" 
						onchange="reload();"/>
					<a
						href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do?indicadorUsoTodos=1');">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Localidade" /></a> <logic:present
						name="idLocalidadeInicialNaoEncontrada" scope="request">
						<html:text maxlength="40" property="nomeLocalidadeInicial"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" />
					</logic:present> <logic:notPresent
						name="idLocalidadeInicialNaoEncontrada" scope="request">
						<html:text maxlength="40" property="nomeLocalidadeInicial"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="40" />
					</logic:notPresent> <a href="javascript:limparLocalidadeInicial();limparLocalidadeFinal();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				
				<tr>
					<td width="200">
						<strong>Setor Comercial Inicial:</strong>
					</td>
					<td colspan="3">
						<strong> 
						<html:text property="codigoSetorComercialInicial" size="4" maxlength="4" tabindex="6"
						onkeyup="replicarCampo(document.forms[0].codigoSetorComercialFinal, document.forms[0].codigoSetorComercialInicial);"
						onkeypress="return validaEnterDependencia(event, 'exibirGerarRelatorioContasEmRevisaoAction.do', this, document.forms[0].idLocalidadeInicial.value, 'Localidade Inicial');" />
						
						<a href="javascript:chamarPesquisaSetorComercialInicial();">
							<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar" />
						</a>
						
						<logic:present name="setorComercialInicialInexistente" scope="request">
							<html:text property="nomeSetorComercialInicial" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" size="40" maxlength="40" />
						</logic:present> 
					
						<logic:notPresent name="setorComercialInicialInexistente" scope="request">
							<html:text property="nomeSetorComercialInicial" readonly="true" style="background-color:#EFEFEF; border:0" size="40" maxlength="40" />
						</logic:notPresent> 
					
						<a href="javascript:limparSetorComercialInicial();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /> 
						</a> 
						</strong>
					</td>
				</tr>
				
				<tr>
					<td><strong>Localidade Final:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text maxlength="3"
						property="idLocalidadeFinal" size="4" tabindex="4"
						onblur="desabilitaIntervaloDiferente();"
						onkeyup="javascript:limparLocalidadeFinalTecla();desabilitaIntervaloDiferente();"
						onkeypress="javascript:return validaEnter(event, 'exibirGerarRelatorioContasEmRevisaoAction.do', 'idLocalidadeFinal');"
						onchange="reload();"/>
					<a
						href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do?indicadorUsoTodos=1');">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Localidade" /></a> <logic:present
						name="idLocalidadeFinalNaoEncontrada" scope="request">
						<html:text maxlength="40" property="nomeLocalidadeFinal"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" />
					</logic:present> <logic:notPresent
						name="idLocalidadeFinalNaoEncontrada" scope="request">
						<html:text maxlength="40" property="nomeLocalidadeFinal"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="40" />
					</logic:notPresent> <a href="javascript:limparLocalidadeFinal();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				
				
                 
                <tr>
					<td width="200">
						<strong>Setor Comercial Final:</strong>
					</td>
					<td colspan="3">
						<strong> 
						<html:text property="codigoSetorComercialFinal" size="4" maxlength="4" tabindex="6"
						onkeypress="return validaEnterDependencia(event, 'exibirGerarRelatorioContasEmRevisaoAction.do', this, document.forms[0].idLocalidadeFinal.value, 'Localidade Final');" />
						
						<a href="javascript:chamarPesquisaSetorComercialFinal();">
							<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar" />
						</a>
						
						<logic:present name="setorComercialFinalInexistente" scope="request">
							<html:text property="nomeSetorComercialFinal" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" size="40" maxlength="40" />
						</logic:present> 
					
						<logic:notPresent name="setorComercialFinalInexistente" scope="request">
							<html:text property="nomeSetorComercialFinal" readonly="true" style="background-color:#EFEFEF; border:0" size="40" maxlength="40" />
						</logic:notPresent> 
					
						<a href="javascript:limparSetorComercialFinal();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /> 
						</a> 
						
						</strong>
					</td>
				</tr>
				
				<tr>
					<td width="200">
						<strong>Motivo:</strong>
					</td>
					<td>
						<html:select property="colecaoIdsContaMotivoRevisao" multiple="true" size ="5" tabindex="15" style="width:300px;">
							<html:option value="-1"> &nbsp; </html:option>
							<html:options collection="colecaoContaMotivoRevisao" property="id" labelProperty="descricaoMotivoRevisaoConta"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td><strong>Perfil do Imóvel:</strong></td>
					<td><html:select property="idImovelPerfil" tabindex="6">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoImovelPerfil"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Intervalo de Referência:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text maxlength="7"
						property="referenciaInicial" size="7"
						onkeyup="mascaraAnoMes(this, event);replicarCampo(document.forms[0].referenciaFinal, document.forms[0].referenciaInicial)"
						tabindex="7" /> &nbsp; <strong>a</strong> &nbsp; <html:text maxlength="7"
						property="referenciaFinal" size="7"
						onkeyup="mascaraAnoMes(this, event);" tabindex="8" /></td>
				</tr>
				<tr>
					<td><strong>Categoria:</strong></td>
					<td><html:select property="idCategoria" tabindex="6">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoCategoria"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Esfera Poder:</strong></td>
					<td><html:select property="idEsferaPoder" tabindex="6">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoEsferaPoder"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left" colspan="2"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td valign="top"><input name="button" type="button"
						class="bottonRightCol" value="Limpar" tabindex="4"
						onclick="window.location.href='/gsan/exibirGerarRelatorioContasEmRevisaoAction.do?menu=sim'">&nbsp;</td>
					<td valign="top">
					<div align="right"><input name="button" type="button"
						class="bottonRightCol" value="Gerar"
						onclick="validarForm(document.forms[0]);" tabindex="5"></div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
