<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ page import="gcom.micromedicao.medicao.MedicaoTipo"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="GerarRelatorioAnormalidadeConsumoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">


function limparEloTecla(){
	var form = document.forms[0];
	
	form.nomeElo.value = "";
}

function limparElo(){
	var form = document.forms[0];
	form.idElo.value = "";
	form.nomeElo.value = "";
}

function limparLocalidadeInicial() {
	var form = document.GerarRelatorioAnormalidadeConsumoActionForm;
	form.idLocalidadeInicial.value = "";
	form.nomeLocalidadeInicial.value = "";	
}

function limparLocalidadeFinal() {
	var form = document.GerarRelatorioAnormalidadeConsumoActionForm;
	form.idLocalidadeFinal.value = "";
	form.nomeLocalidadeFinal.value = "";	
}

function limparSetorComercialInicial() {
	var form = document.forms[0];
	form.codigoSetorComercialInicial.value = "";
	form.nomeSetorComercialInicial.value = "";	
	form.idSetorComercialInicial.value = "";
}

function limparSetorComercialFinal() {
	var form = document.forms[0];
	form.codigoSetorComercialFinal.value = "";
	form.nomeSetorComercialFinal.value = "";	
	form.idSetorComercialFinal.value = "";
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];
   
    if (tipoConsulta == 'elo') {
      form.idElo.value = codigoRegistro;
      form.nomeElo.value = descricaoRegistro;
      form.nomeElo.style.color = "#000000";
      form.idLocalidadeInicial.focus();
    }
   
     if (tipoConsulta == 'localidade') {
     
     if (form.tipoPesquisa.value == 'inicial') {
      form.idLocalidadeInicial.value = codigoRegistro;
      form.nomeLocalidadeInicial.value = descricaoRegistro;
      form.nomeLocalidadeInicial.style.color = "#000000";
      form.idLocalidadeFinal.value = codigoRegistro;
      form.nomeLocalidadeFinal.value = descricaoRegistro;
      form.nomeLocalidadeFinal.style.color = "#000000";
      form.idLocalidadeFinal.focus();
    } else {
 	  form.idLocalidadeFinal.value = codigoRegistro;
      form.nomeLocalidadeFinal.value = descricaoRegistro;
      form.nomeLocalidadeFinal.style.color = "#000000";
      form.referencia.focus();
    }
    
    }
    
    if (tipoConsulta == 'setorComercial') {
    	if (form.tipoPesquisa.value == 'inicial') {
			form.nomeSetorComercialInicial.style.color = "#000000";
			form.nomeSetorComercialInicial.value = descricaoRegistro;
			form.codigoSetorComercialInicial.value = codigoRegistro;
		}
	
		if (form.tipoPesquisa.value == 'final') {
			form.nomeSetorComercialFinal.style.color = "#000000";
			form.nomeSetorComercialFinal.value = descricaoRegistro;
			form.codigoSetorComercialFinal.value = codigoRegistro;
		}
    }
    
}

function verificarAnoMesReferencia(){
		var form = GerarRelatorioAnormalidadeConsumoActionForm;
       	
       	if (form.referencia.value.length > 0){
       		form.action = 'exibirGerarRelatorioAnormalidadeConsumoAction.do';
       		form.submit();
       	}
       	
}


function verificarNumOcorrenciasConsecutivas(){
		var form = GerarRelatorioAnormalidadeConsumoActionForm;
		
		if (form.numOcorrenciasConsecutivas.value.length > 0){
			form.action = 'exibirGerarRelatorioAnormalidadeConsumoAction.do';
       		form.submit();
		}
       	
}

function replicaDados(campoOrigem, campoDestino){
	campoDestino.value = campoOrigem.value;
}
	
function limparLeituraAnormalidade() {
	var form = document.GerarRelatorioAnormalidadeConsumoActionForm;
	form.idLeituraAnormalidade.value = "";
	form.descricaoLeituraAnormalidade.value = "";	
}

function chamarPesquisaLocalidadeInicial() {
	document.forms[0].tipoPesquisa.value = 'inicial';
	abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade', 400, 800);
}

function chamarPesquisaLocalidadeFinal() {
	document.forms[0].tipoPesquisa.value = 'final';
	abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade', 400, 800);
}

function chamarPesquisaSetorComercialInicial() {
	document.forms[0].tipoPesquisa.value = 'inicial';
	abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidadeInicial.value+'&tipo=setorComercialInicial&indicadorUsoTodos=1',document.forms[0].idLocalidadeInicial.value,'Localidade Inicial', 400, 800);
}

function chamarPesquisaSetorComercialFinal() {
	document.forms[0].tipoPesquisa.value = 'final';
	abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidadeFinal.value+'&tipo=setorComercialFinal&indicadorUsoTodos=1',document.forms[0].idLocalidadeFinal.value,'Localidade Final', 400, 800);
}

function validarForm(form){
		
	if(validateGerarRelatorioAnormalidadeConsumoActionForm(form)){
		if(form.idLocalidadeInicial.value.length > 0 && form.idLocalidadeFinal.value.length == 0) {
			alert('Informe Localidade Final');
		} else if(form.idLocalidadeFinal.value.length > 0 && form.idLocalidadeInicial.value.length == 0) {
			alert('Informe Localidade Inicial');
		} else if(form.intervaloMediaConsumoInicial.value.length > 0 && form.intervaloMediaConsumoFinal.value.length == 0) {
			alert('Informe a Média de Consumo Final');
		} else if(form.intervaloMediaConsumoFinal.value.length > 0 && form.intervaloMediaConsumoInicial.value.length == 0) {
			alert('Informe a Média de Consumo Inicial');
		} else{
	    	submeterFormPadrao(form);
	    }
	}
}


function chamarReload(){
	var form = document.forms[0];
	form.action = 'exibirGerarRelatorioAnormalidadeConsumoAction.do?reload=OK';
    form.submit();
	
}

</script>


</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form  action="/gerarRelatorioAnormalidadeConsumoAction" method="post" 
			name="GerarRelatorioAnormalidadeConsumoActionForm"
			type="gcom.gui.faturamento.GerarRelatorioAnormalidadeConsumoActionForm"
			onsubmit="return validateGerarRelatorioAnormalidadeConsumoActionForm(this);">
	
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<input type="hidden" name="tipoPesquisa" />

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
			<td width="625" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Relatório de Anormalidades Informadas por Imóvel</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>

					<td colspan="2">Para gerar Relatório de Anormalidade, informe os dados gerais
					abaixo:</td>
				</tr>
				<tr>
					<td width="190">
						<strong>Gerência Regional:</strong>
					</td>
					<td colspan="3">
						<html:select property="regional" tabindex="1" style="width:300px;" onchange ="javascript:chamarReload();"> 
							<html:option value="-1"> &nbsp; </html:option>
							<logic:iterate name="colecaoGerenciaRegional" id="colecaoGerenciaRegional">
							<html:option value="${colecaoGerenciaRegional.id}" >
								<bean:write name="colecaoGerenciaRegional" property="nomeAbreviado" /> 
					           - <bean:write name="colecaoGerenciaRegional" property="nome" />
							</html:option>
						</logic:iterate>
							
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td width="190">
						<strong>Unidade de Negócio:</strong>
					</td>
					<td colspan="3">
						<html:select property="unidadeNegocio" tabindex="2" style="width:300px;">
							<html:option value="-1"> &nbsp; </html:option>
							<html:options collection="colecaoUnidadeNegocio" property="id" 
							labelProperty="nome" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td width="190">
						<strong>Grupo:</strong>
					</td>
					<td >
						<html:select property="grupo" tabindex="4" style="width:300px;">
							<html:option value="-1"> &nbsp; </html:option>
							<html:options collection="colecaoFaturamentoGrupo" property="id" labelProperty="descricao" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td width="190">
						<strong>Localidade Inicial:</strong>
					</td>
					<td colspan="3">
						<strong> 
						<html:text property="idLocalidadeInicial" size="5" maxlength="3" tabindex="4"
						onkeyup="replicaDados(document.forms[0].idLocalidadeInicial, document.forms[0].idLocalidadeFinal);"
						onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioAnormalidadeConsumoAction.do', 'idLocalidadeInicial', 'Localidade Inicial');return isCampoNumerico(event);" />
						
						<a href="javascript:chamarPesquisaLocalidadeInicial();"><img
							width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Localidade Inicial" /></a>
					
					<logic:present name="localidadeInicialInexistente" scope="request">
						<html:text property="nomeLocalidadeInicial" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" maxlength="40" />
					</logic:present> 
					
					<logic:notPresent name="localidadeInicialInexistente" scope="request">
						<html:text property="nomeLocalidadeInicial" readonly="true"
							style="background-color:#EFEFEF; border:0" size="40"
							maxlength="40" />
					</logic:notPresent> 
					
						<a href="javascript:limparLocalidadeInicial();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar Localidade Inicial" /> 
						</a> 
						</strong>
					</td>
				</tr>

				<tr>
					<td width="190">
						<strong>Localidade Final:</strong>
					</td>
					<td colspan="3">
						<strong> 
						<html:text property="idLocalidadeFinal" size="5" maxlength="3" tabindex="5"
						onkeypress= "validaEnterComMensagem(event, 'exibirGerarRelatorioAnormalidadeConsumoAction.do', 'idLocalidadeFinal', 'Localidade Final');return isCampoNumerico(event);"/>
						 
						<a href="javascript:chamarPesquisaLocalidadeFinal();">
							<img border="0" src="imagens/pesquisa.gif" height="21" width="23"
							title="Pesquisar Localidade Final"></a>
					
					<logic:present name="localidadeFinalInexistente" scope="request">
						<html:text property="nomeLocalidadeFinal" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" maxlength="40" />
					</logic:present> 
					
					<logic:notPresent name="localidadeFinalInexistente" scope="request">
						<html:text property="nomeLocalidadeFinal" readonly="true"
							style="background-color:#EFEFEF; border:0" size="40"
							maxlength="40" />
					</logic:notPresent> 
					
						<a href="javascript:limparLocalidadeFinal();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar Localidade Final" /> 
						</a> 
					</strong>
					</td>
				</tr>
				
				<tr>
					<td width="190">
						<strong>Setor Comercial Inicial:</strong>
					</td>
					<td colspan="3">
						<strong> 
						<html:text property="codigoSetorComercialInicial" size="5" maxlength="4" tabindex="6"
						onkeyup="replicaDados(document.forms[0].codigoSetorComercialInicial, document.forms[0].codigoSetorComercialFinal);"
						onkeypress="validaEnterDependencia(event, 'exibirGerarRelatorioAnormalidadeConsumoAction.do', this, document.forms[0].idLocalidadeInicial.value, 'Localidade Inicial');return isCampoNumerico(event);" />
						
						<a href="javascript:chamarPesquisaSetorComercialInicial();">
							<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Setor Comercial Inicial" /></a>
						
						<logic:present name="setorComercialInicialInexistente" scope="request">
							<html:text property="nomeSetorComercialInicial" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" size="40" maxlength="40" />
						</logic:present> 
					
						<logic:notPresent name="setorComercialInicialInexistente" scope="request">
							<html:text property="nomeSetorComercialInicial" readonly="true" style="background-color:#EFEFEF; border:0" size="40" maxlength="40" />
						</logic:notPresent> 
					
						<a href="javascript:limparSetorComercialInicial();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar Setor Comercial Inicial" /> 
						</a> 
						</strong>
					</td>
				</tr>
                 
                <tr>
					<td width="190">
						<strong>Setor Comercial Final:</strong>
					</td>
					<td colspan="3">
						<strong> 
						<html:text property="codigoSetorComercialFinal" size="5" maxlength="4" tabindex="7"
						onkeypress="validaEnterDependencia(event, 'exibirGerarRelatorioAnormalidadeConsumoAction.do', this, document.forms[0].idLocalidadeFinal.value, 'Localidade Final');return isCampoNumerico(event);" />

						<a href="javascript:chamarPesquisaSetorComercialFinal();">
							<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Setor Comercial Final" /></a>
						
						<logic:present name="setorComercialFinalInexistente" scope="request">
							<html:text property="nomeSetorComercialFinal" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" size="40" maxlength="40" />
						</logic:present> 
					
						<logic:notPresent name="setorComercialFinalInexistente" scope="request">
							<html:text property="nomeSetorComercialFinal" readonly="true" style="background-color:#EFEFEF; border:0" size="40" maxlength="40" />
						</logic:notPresent> 
					
						<a href="javascript:limparSetorComercialFinal();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar Setor Comercial Final" /> 
						</a> 
						
						</strong>
					</td>
				</tr>			
				
				<tr>
					<td width="190"><strong>Quadra Inicial:</strong></td>
					<td colspan="3">
						<html:text maxlength="4" property="quadraInicialNM" size="5"
						onkeypress="validaEnterDependenciaComMensagem(event, 'exibirGerarRelatorioAnormalidadeConsumoAction.do', document.forms[0].quadraInicialNM, document.forms[0].codigoSetorComercialInicial.value, 'Setor Comercial Inicial','Quadra Inicial');return isCampoNumerico(event);"
						tabindex="8"  onkeyup="replicaDados(document.forms[0].quadraInicialNM, document.forms[0].quadraFinalNM);" /> 
						<logic:present name="corQuadraOrigem">
						 	<logic:equal name="corQuadraOrigem" value="exception">
									<span style="color:#ff0000" id="msgOrigem"><bean:write name="GerarRelatorioAnormalidadeConsumoActionForm" property="quadraMensagemInicial"/></span>
							</logic:equal>	
			            </logic:present>	
						<html:hidden property="quadraInicialID" />
					</td>
				</tr>
				
				<tr>
					<td width="190"><strong>Quadra Final:</strong></td>
					<td colspan="3">
						<html:text maxlength="4" property="quadraFinalNM" size="5"
						onkeypress="validaEnterDependenciaComMensagem(event, 'exibirGerarRelatorioAnormalidadeConsumoAction.do', document.forms[0].quadraFinalNM, document.forms[0].codigoSetorComercialFinal.value, 'Setor Comercial Final','Quadra Final');return isCampoNumerico(event);"
						tabindex="9"  />
						<logic:present name="corQuadraDestino">
						 	<logic:equal name="corQuadraDestino" value="exception">
									<span style="color:#ff0000" id="msgQuadraDestino"><bean:write name="GerarRelatorioAnormalidadeConsumoActionForm" property="quadraMensagemFinal"/></span>
							</logic:equal>	
			            </logic:present>
			            <html:hidden property="quadraFinalID" />	
					</td>
				</tr>
				<tr>
					<td width="190">
						<strong>Rota:</strong>
					</td>

					<td colspan="3">
						<html:text  property="rota" size="5" maxlength="4" tabindex="10" onkeypress="return isCampoNumerico(event);" /> 
					</td>
				</tr>
				<tr>
					<td width="190">
						<strong>Empresas:</strong>
					</td>
					<td>
						<html:select property="colecaoIdsEmpresa" multiple="true" size ="5" tabindex="11" style="width:250px;">
							<html:option value="-1"> &nbsp; </html:option>
							<html:options collection="colecaoEmpresa" property="id" labelProperty="descricao"/>
						</html:select>
					</td>
				</tr>	                 
                 <tr>
					<td width="190">
						<strong>Referência:<font color="#FF0000">*</font></strong>
					</td>

					<td colspan="3">
						<html:text  property="referencia" size="7" maxlength="7" tabindex="12"
									onkeypress="javascript:return isCampoNumerico(event);"
									onkeyup="mascaraAnoMes(this, event);" /> (mm/aaaa)
					</td>
				</tr>
				
				<tr>
					<td width="190">
						<strong>Perfil do Imóvel:</strong>
					</td>
					<td >
						<html:select property="idImovelPerfil" tabindex="13" style="width:200px;">
							<html:option value="-1"> &nbsp; </html:option>
							<html:options collection="colecaoImovelPerfil" property="id" labelProperty="descricao" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td width="190">
						<strong>Num. Ocorrências Consecutivas:</strong>
					</td>
					<td colspan="3">
						<html:text property="numOcorrenciasConsecutivas" size="3" maxlength="2" tabindex="14" 
						onblur="javascript:verificarNumOcorrenciasConsecutivas();" disabled="true" />
					</td>
				</tr>
				
				<tr>
					<td width="190">
						<strong>Categoria:</strong>
					</td>
					<td >
						<html:select property="idCategoria" tabindex="9" style="width:200px;">
							<html:option value="-1"> &nbsp; </html:option>
							<html:options collection="colecaoCategoria" property="id" labelProperty="descricao" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td width="190">
						<strong>Ocorrências Iguais:</strong>
					</td>
					<td colspan="3">
						<html:radio property="indicadorOcorrenciasIguais" value="1" tabindex="16" disabled="true" /><strong>Sim
						<html:radio property="indicadorOcorrenciasIguais" value="2" tabindex="17" />Não</strong>
					</td>
				</tr>

				<tr>
					<td width="190">
						<strong>Intervalo de Média de Consumo:</strong>
					</td>
					<td colspan="3">
						<div align="left">
							<strong> 
								<html:text property="intervaloMediaConsumoInicial" size="6" maxlength="6" tabindex="13" 
									onkeyup="replicaDados(document.forms[0].intervaloMediaConsumoInicial,
									document.forms[0].intervaloMediaConsumoFinal);" onkeypress="return isCampoNumerico(event);"  /> a 
								<html:text property="intervaloMediaConsumoFinal" size="6" maxlength="6" tabindex="14" 
								onkeypress="return isCampoNumerico(event);" /> 
							</strong>
						</div>
					</td>
				</tr>

				<tr>
					<td width="190">
						<strong>Anormalidade de Leitura Informada:</strong>
					</td>
					<td>
						<html:select property="colecaoIdsLeituraAnormalidadeInformada" multiple="true" size ="5" tabindex="15" style="width:250px;">
							<html:option value="-1"> &nbsp; </html:option>
							<html:options collection="colecaoLeituraAnormalidade" property="id" labelProperty="descricao"/>
						</html:select>
					</td>
				</tr>
				
				<!-- CRC4561 - comentado por Vivianne Sousa - 07/06/2010 - analista:Adriana Ribeiro
				<tr>
					<td width="190">
						<strong>Anormalidade de Leitura Faturada:</strong>
					</td>
					<td>
						<html:select property="colecaoIdsLeituraAnormalidade" multiple="true" size ="5" tabindex="15" style="width:250px;">
							<html:option value="-1"> &nbsp; </html:option>
							<html:options collection="colecaoLeituraAnormalidade" property="id" labelProperty="descricao"/>
						</html:select>
					</td>
				</tr>

				 <tr>
					<td width="190">
						<strong>Anormalidade de Consumo:</strong>
					</td>
					<td>
						<html:select property="colecaoIdsConsumoAnormalidade" multiple="true" size="5" tabindex="16" style="width:250px;">
							<html:option value="-1"> &nbsp; </html:option>
							<html:options collection="colecaoConsumoAnormalidade" property="id" labelProperty="descricao"/>
						</html:select>
					</td>
				</tr>
				 -->
				<tr>
					<td width="190">
						<strong>Tipo Medição:</strong>
					</td>
					<td colspan="3">
						<html:radio property="tipoMedicao" value="<%=MedicaoTipo.LIGACAO_AGUA.toString()%>" tabindex="17" /><strong>Água
						<html:radio property="tipoMedicao" value="<%=MedicaoTipo.POCO.toString()%>" tabindex="18" />Poço</strong>
					</td>
				</tr>
				
				<tr>
					<td height="19">
						<strong> <font color="#FF0000"></font></strong>
					</td>
					<td align="right">
						<div align="left">
							<strong><font color="#FF0000">*</font></strong> Campos obrigat&oacute;rios
						</div>
					</td>
				</tr>
				
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="window.location.href='/gsan/exibirGerarRelatorioAnormalidadeConsumoAction.do?menu=sim'" tabindex="17">
						<input type="button"name="ButtonCancelar" class="bottonRightCol" value="Cancelar" 
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"></td>
					<td align="right" colspan="2">
						<input name="Button" type="button" class="bottonRightCol" value="Gerar" align="left" tabindex="16"
						onclick="validarForm(document.forms[0]);"></td>
					<td align="right"></td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>
</html:form>
</body>
</html:html>

