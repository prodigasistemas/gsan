<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.faturamento.FaturamentoAtivCronRota" isELIgnored="false"%>
<%@ page import="gcom.faturamento.FaturamentoAtividade" %>
<%@ page import="gcom.util.Util"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirComandoAtividadeFaturamentoActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript">
<!--
function validateInserirComandoAtividadeFaturamentoActionForm(form){
	// Constantes
	var ATIVIDADE_FATURAR_GRUPO_ID = document.getElementById("ATIVIDADE_FATURAR_GRUPO_ID").value;
	var ATIVIDADE_FATURAMENTO_ID = document.getElementById("ATIVIDADE_FATURAMENTO_ID").value;
	var DATA_CORRENTE = document.getElementById("DATA_CORRENTE");
	var DATA_VENCIMENTO = document.getElementById("DATA_VENCIMENTO");

	var realizarSubmit = true;

	if (ATIVIDADE_FATURAMENTO_ID == ATIVIDADE_FATURAR_GRUPO_ID){
	
		var MES_REFERENCIA = retornarParteData(DATA_VENCIMENTO, true);
		var ANO_REFERENCIA = retornarParteData(DATA_VENCIMENTO, false);
	
		for(indice = 0; indice < form.elements.length; indice++){
		
			if (form.elements[indice].type == "text") {
			
				if (form.elements[indice].value.length < 1){
					alert("Vencimento da Rota é obrigatório");
					form.elements[indice].focus();
					realizarSubmit = false;
					break;
				} 
				else if (!verificaData(form.elements[indice])){
					realizarSubmit = false;
					break;
				}
				else if (comparaData(form.elements[indice].value, "<", DATA_CORRENTE.value)||
				 		 comparaData(form.elements[indice].value, "=", DATA_CORRENTE.value)){
					alert("A data de vencimento da rota deve ser maior que " + DATA_CORRENTE.value);
					realizarSubmit = false;
					form.elements[indice].focus();
					break;
				} 
				else if (MES_REFERENCIA != retornarParteData(form.elements[indice], true) || 
						 ANO_REFERENCIA != retornarParteData(form.elements[indice], false)){
					alert("O mês e ano da data de vencimento não podem ser alterados");
					realizarSubmit = false;
					form.elements[indice].focus();
					break;
				} 
			} 
		}
	}

	if (realizarSubmit){
	
		var REMOCAO_REALIZADA = trim(document.getElementById("REMOCAO_REALIZADA").value);
		
		if (form.action.indexOf('destino') != -1 &&
			REMOCAO_REALIZADA.length > 0){
			alert("Exclusão(ões) efetuada(s) será(ão) desfeita(s)");
		}
		
		submeterFormPadrao(form);
	}
}


/* Retorna parte de uma data no formato de string
   Mês = true
   Ano = false
*/
function retornarParteData(data, tipoRetorno){
	var retorno = "";

	if (data.value.length > 0){

		// Mês
		if (tipoRetorno){
			retorno = data.value.substring(3,5);
		}
		// Ano
		else{
			retorno = data.value.substring(6,10);
		}
	}

	return retorno;
}

function facilitador(objeto){
	if (objeto.id == "0" || objeto.id == undefined){
		objeto.id = "1";
		marcarTodos();
	}
	else{
		objeto.id = "0";
		desmarcarTodos();
	}
}

// Verifica se há item selecionado
function verficarSelecao(objeto){

	if (CheckboxNaoVazio(objeto)){
		if (confirm ("Confirma remoção?")) {
			redirecionarSubmit("atualizarComandoAtividadeFaturamentoWizardAction.do?action=removerSelecaoRotaUniaoAction");
		 }
	}
}
//-->
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/atualizarComandoAtividadeFaturamentoWizardAction" method="post">

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard.jsp?numeroPagina=3"/>

<input type="hidden" id="ATIVIDADE_FATURAR_GRUPO_ID" value="<%= "" + FaturamentoAtividade.FATURAR_GRUPO%>">
<logic:present name="exibirCampoVencimentoGrupo" scope="session">
	<input type="hidden" id="ATIVIDADE_FATURAMENTO_ID" value="<%= "" + FaturamentoAtividade.FATURAR_GRUPO%>">
</logic:present>
<logic:notPresent name="exibirCampoVencimentoGrupo" scope="session">
	<input type="hidden" id="ATIVIDADE_FATURAMENTO_ID" value="0">
</logic:notPresent>

<!-- 
Esteja menor que a current date mais o número mínimo de dias entre a data de emissão 
e a data de vencimento da conta (PARM_NNMINIMODIASEMISSAOVENCIMENTO da tabela SISTEMA_PARAMETROS).
-->
<input type="hidden" id="DATA_CORRENTE" value="${sessionScope.dataCorrente}">
<input type="hidden" id="DATA_VENCIMENTO" value="${sessionScope.exibirCampoVencimentoGrupo}">


<!-- 
Será utilizado para verificar se houve alguma remoção na coleção
-->
<input type="hidden" id="REMOCAO_REALIZADA" value="${requestScope.remocaoRealizada}">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<input type="hidden" name="numeroPagina" value="3"/>
<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
    	<td width="130" valign="top" class="leftcoltext">
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

		<td width="630" valign="top" class="centercoltext">
	        <table height="100%">
    		    <tr><td></td></tr>
	      	</table>
		   	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/>
					</td>
					<td class="parabg">Atualizar de Comando de Atividade de Faturamento</td>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>

<!-- ============================================================================================================================== -->

			<table width="100%" border="0">
				<tr> 
					<td height="17"><strong>Rotas selecionadas:</strong></td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoComandoAtividadeAtualizarAbaConclusao', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>	
				</tr>
			</table>	
			<table width="100%" border="0">	
				<tr> 
					<td colspan="4">
		  				<table width="100%" cellpadding="0" cellspacing="0">
							<tr> 
								<td colspan="2"> 
									<table width="100%" bgcolor="#99CCFF">
                					    <tr bgcolor="#99CCFF"> 
											<td align="center" width="10%">
												<a href="javascript:facilitador(this);" id="0"><strong>Todos</strong></a>
											</td>
											<logic:present name="exibirCampoVencimentoGrupo" scope="session">
												<td width="10%" align="center"><strong>Grupo</strong></td>
												<td width="9%" align="center"><strong>Ger&ecirc;ncia</strong></td>
												<td width="9%" align="center"><strong>Unidade Negócio</strong></td>
												<td width="20%" align="center"><strong>Localidade</strong></td>
												<td width="10%" align="center"><strong>Setor</strong></td>
												<td width="10%" align="center"><strong>Rota</strong></td>
												<td width="30%" align="center"><strong>Data Vencimento</strong></td>
											</logic:present>
											<logic:notPresent name="exibirCampoVencimentoGrupo" scope="session">
												<td width="10%" align="center"><strong>Grupo</strong></td>
												<td width="9%" align="center"><strong>Ger&ecirc;ncia</strong></td>
												<td width="9%" align="center"><strong>Unidade Negócio</strong></td>
												<td width="50%" align="center"><strong>Localidade</strong></td>
												<td width="7%" align="center"><strong>Setor</strong></td>
												<td width="13%" align="center"><strong>Rota&nbsp;&nbsp;&nbsp;</strong></td>
											</logic:notPresent>
					                    </tr>
                    				</table>
								</td>
        				    </tr>
			            </table>

						<logic:present name="colecaoFaturamentoAtividadeCronogramaRotaUniao" scope="session">
						<div style="width: 100%; height: 210; overflow: auto;">
						<table width="100%" cellpadding="0" cellspacing="0">
            				<tr> 
								<td> 
									<%int cont = 0;%>
									<logic:present name="exibirCampoVencimentoGrupo" scope="session">
									<table width="100%" align="center" bgcolor="#99CCFF">
									<logic:iterate name="colecaoFaturamentoAtividadeCronogramaRotaUniao" id="faturamentoAtividadeCronogramaRota" type="FaturamentoAtivCronRota">
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
										<%} else {%>
										<tr bgcolor="#FFFFFF">
										<%}%>
											<td align="center" width="10%">
												<html:checkbox property="idRotaSelecao" 
													value="<%="" + faturamentoAtividadeCronogramaRota.getRota().getId()%>"/>
											</td>
											<td width="10%" align="center">
												<bean:write name="faturamentoAtividadeCronogramaRota" 
													property="rota.faturamentoGrupo.id"/>
											</td>
											<td width="9%" align="center">
												<logic:present name="faturamentoAtividadeCronogramaRota" property="rota.setorComercial">
													<bean:write name="faturamentoAtividadeCronogramaRota" 
														property="rota.setorComercial.localidade.gerenciaRegional.nomeAbreviado"/>
												</logic:present>
												<logic:notPresent name="faturamentoAtividadeCronogramaRota" property="rota.setorComercial">
													&nbsp;
												</logic:notPresent>
											</td>
											<td width="9%" align="center">
												<logic:present name="faturamentoAtividadeCronogramaRota" property="rota.setorComercial">
													<bean:write name="faturamentoAtividadeCronogramaRota" 
														property="rota.setorComercial.localidade.unidadeNegocio.nomeAbreviado"/>
												</logic:present>
												<logic:notPresent name="faturamentoAtividadeCronogramaRota" property="rota.setorComercial">
													&nbsp;
												</logic:notPresent>
											</td>

											<td width="20%" align="left">
												<logic:present name="faturamentoAtividadeCronogramaRota" property="rota.setorComercial">
													<bean:write name="faturamentoAtividadeCronogramaRota" 
														property="rota.setorComercial.localidade.descricao"/>
												</logic:present>
												<logic:notPresent name="faturamentoAtividadeCronogramaRota" property="rota.setorComercial">
													&nbsp;
												</logic:notPresent>	
											</td>
											<td width="10%" align="center">
												<logic:present name="faturamentoAtividadeCronogramaRota" property="rota.setorComercial">
													<bean:write name="faturamentoAtividadeCronogramaRota" 
														property="rota.setorComercial.codigo"/>
												</logic:present>
												<logic:notPresent name="faturamentoAtividadeCronogramaRota" property="rota.setorComercial">
													&nbsp;
												</logic:notPresent>
											</td>
											<td width="10%" align="center">
												<bean:write name="faturamentoAtividadeCronogramaRota" property="rota.codigo"/>
											</td>
											<td width="30%" align="center">
												<logic:present name="faturamentoAtividadeCronogramaRota" property="dataContaVencimento">
													<input type="text" name="data<%="" + faturamentoAtividadeCronogramaRota.getRota().getId()%>" 
														value="<%="" + Util.formatarData(faturamentoAtividadeCronogramaRota.getDataContaVencimento())%>" 
														size="10" maxlength="10" onkeyup="mascaraData(this, event);">
													<a href="javascript:abrirCalendario('InserirComandoAtividadeFaturamentoActionForm', 'data<%="" + faturamentoAtividadeCronogramaRota.getRota().getId()%>')">
														<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
															width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
												</logic:present>
												<logic:notPresent name="faturamentoAtividadeCronogramaRota" property="dataContaVencimento">
													<input type="text" name="data<%="" + faturamentoAtividadeCronogramaRota.getRota().getId()%>" 
														size="10" maxlength="10" onkeyup="mascaraData(this, event);">
													<a href="javascript:abrirCalendario('InserirComandoAtividadeFaturamentoActionForm', 'data<%="" + faturamentoAtividadeCronogramaRota.getRota().getId()%>')">
														<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
															width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
												</logic:notPresent>
											</td>
										</tr>
									</logic:iterate>
									</table>
									</logic:present>

									<logic:notPresent name="exibirCampoVencimentoGrupo" scope="session">
									<table width="100%" align="center" bgcolor="#99CCFF">
									<logic:iterate name="colecaoFaturamentoAtividadeCronogramaRotaUniao" id="faturamentoAtividadeCronogramaRota" type="FaturamentoAtivCronRota">
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
										<%} else {%>
										<tr bgcolor="#FFFFFF">
										<%}%>
											<td align="center" width="10%">
												<html:checkbox property="idRotaSelecao" 
													value="<%="" + faturamentoAtividadeCronogramaRota.getRota().getId()%>"/>
											</td>
											<td width="10%" align="center">
												<bean:write name="faturamentoAtividadeCronogramaRota" 
													property="rota.faturamentoGrupo.id"/>
											</td>
											<td width="9%" align="center">
												<logic:present name="faturamentoAtividadeCronogramaRota" property="rota.setorComercial">
													<bean:write name="faturamentoAtividadeCronogramaRota" 
														property="rota.setorComercial.localidade.gerenciaRegional.nomeAbreviado"/>
												</logic:present>
												<logic:notPresent name="faturamentoAtividadeCronogramaRota" property="rota.setorComercial">
													&nbsp;
												</logic:notPresent>
											</td>
											<td width="9%" align="center">
												<logic:present name="faturamentoAtividadeCronogramaRota" property="rota.setorComercial">
													<bean:write name="faturamentoAtividadeCronogramaRota" 
														property="rota.setorComercial.localidade.unidadeNegocio.nomeAbreviado"/>
												</logic:present>
												<logic:notPresent name="faturamentoAtividadeCronogramaRota" property="rota.setorComercial">
													&nbsp;
												</logic:notPresent>
											</td>

											<td width="50%" align="left">
												<logic:present name="faturamentoAtividadeCronogramaRota" property="rota.setorComercial">
													<bean:write name="faturamentoAtividadeCronogramaRota" 
														property="rota.setorComercial.localidade.descricao"/>
												</logic:present>
												<logic:notPresent name="faturamentoAtividadeCronogramaRota" property="rota.setorComercial">
													&nbsp;</logic:notPresent>	
											</td>
											<td width="10%" align="center">
												<logic:present name="faturamentoAtividadeCronogramaRota" property="rota.setorComercial">
													<bean:write name="faturamentoAtividadeCronogramaRota" 
														property="rota.setorComercial.codigo"/>
												</logic:present>
												<logic:notPresent name="faturamentoAtividadeCronogramaRota" property="rota.setorComercial">
													&nbsp;
												</logic:notPresent>
											</td>
											<td width="10%" align="center">
												<bean:write name="faturamentoAtividadeCronogramaRota" property="rota.codigo"/>
											</td>
										</tr>
									</logic:iterate>
									</table>
									</logic:notPresent>
								</td>
				            </tr>
						</table>
						</div>
						</logic:present>
					</td>
				</tr>
				<tr> 
					<td height="17" colspan="4">
						<logic:present name="colecaoFaturamentoAtividadeCronogramaRotaUniao" scope="session">
							<input type="button" class="bottonRightCol" value="Remover" 
								onclick="verficarSelecao(idRotaSelecao);" style="width: 70px">
						</logic:present>
					</td>
				</tr>
	  
<!-- ============================================================================================================================== -->

				<tr>
					<td colspan="4" align="right">
						<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=3"/>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</td>
	</tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>