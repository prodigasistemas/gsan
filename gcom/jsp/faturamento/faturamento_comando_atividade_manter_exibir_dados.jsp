<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.faturamento.FaturamentoAtividade"	isELIgnored="false" %>
<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirComandoAtividadeFaturamentoActionForm"
	dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
<!--
function validateInserirComandoAtividadeFaturamentoActionForm(form){
	// Constantes
	var DATA_CORRENTE = document.getElementById("DATA_CORRENTE").value;
	var DATA_VENCIMENTO = document.getElementById("DATA_VENCIMENTO");
	
	var objVencimentoGrupo = returnObject(form, "vencimentoGrupo");

	if (objVencimentoGrupo != undefined){
	
		var MES_VENCIMENTO = retornarParteData(DATA_VENCIMENTO, true);
		var ANO_VENCIMENTO = retornarParteData(DATA_VENCIMENTO, false);
	
		if (!validateDate(form)){}
		else if (comparaData(objVencimentoGrupo.value, "<", DATA_CORRENTE) ||
				 comparaData(objVencimentoGrupo.value, "=", DATA_CORRENTE)){
			alert("Vencimento do Grupo deve ser maior que " + DATA_CORRENTE+".");
			objVencimentoGrupo.focus();
		}
		else if (MES_VENCIMENTO != retornarParteData(objVencimentoGrupo, true) || 
				 ANO_VENCIMENTO != retornarParteData(objVencimentoGrupo, false)){
			alert("Mês e ano do Vencimento do Grupo não podem ser alterados.");
		}
		else{
			submeterFormPadrao(form);
		}
	}
	else{
		submeterFormPadrao(form);
	} 
}

function DateValidations () { 
	this.aa = new Array("vencimentoGrupo", "Vencimento do Grupo inválido.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
} 

/* 
Retorna parte de uma data no formato de string
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
//-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form action="/atualizarComandoAtividadeFaturamentoWizardAction" method="post">
	<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard.jsp?numeroPagina=1" />

	<input type="hidden" id="ATIVIDADE_FATURAR_GRUPO_ID"
		value="<%= "" + FaturamentoAtividade.FATURAR_GRUPO%>">
	<input type="hidden" id="NUMERO_NAO_INFORMADO"
		value="<%= "" + ConstantesSistema.NUMERO_NAO_INFORMADO%>">

	<!-- 
	Esteja menor que a current date mais o número mínimo de dias entre a data de emissão 
	e a data de vencimento da conta (PARM_NNMINIMODIASEMISSAOVENCIMENTO da tabela SISTEMA_PARAMETROS).
	-->
	<input type="hidden" ID="DATA_CORRENTE"
		value="${sessionScope.dataCorrente}">
	<input type="hidden" ID="DATA_VENCIMENTO"
		value="${sessionScope.exibirCampoVencimentoGrupo}">


	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<input type="hidden" name="numeroPagina" value="1" />
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
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
					</td>
					<td class="parabg">Atualizar Comando de Atividade de Faturamento</td>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			
			<table width="100%" border="0">
	        <tr>
	          <logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoComandoAtividadeAtualizarAbaComando', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
			  </logic:present>
			  <logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
			  </logic:notPresent>          
	      	</tr>
	      	</table>
<!-- ============================================================================================================================== -->

			<table width="100%" border="0">
				<tr>
					<td colspan="4"></td>
				</tr>
				<tr>
					<td width="30%"><strong>Grupo de Faturamento:</strong></td>
					<td width="20%">
						<html:text property="grupoFaturamentoID" size="12" maxlength="10" readonly="true" 
							style="background-color:#EFEFEF; border:0; color: #000000"/>
					</td>
					<td width="25%"><strong>Refer&ecirc;ncia Faturamento:</strong></td>
					<td>
						<html:text property="referenciaFaturamento" size="8" maxlength="7" tabindex="2" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"/>
					</td>
				</tr>
				<tr>
					<td><strong>Atividade de Faturamento:</strong></td>
					<td colspan="3">
						<html:text property="atividadeFaturamentoID" size="50" maxlength="50" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"/>
					</td>
				</tr>
				<logic:present name="exibirCampoVencimentoGrupo" scope="session">
					<tr>
						<td><strong>Vencimento do Grupo:</strong></td>
						<td colspan="3">
							<html:text property="vencimentoGrupo" size="11"
								maxlength="10" tabindex="4" onkeyup="mascaraData(this, event);" />
							<a href="javascript:abrirCalendario('InserirComandoAtividadeFaturamentoActionForm', 'vencimentoGrupo')">
							<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
								width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
							<font size="2">dd/mm/aaaa</font>		
						</td>
					</tr>
				</logic:present>

				<tr>
					<td height="17"><strong>Rotas do comando:</strong></td>
					<td colspan="3" align="right"></td>
				</tr>
				<tr>
					<td colspan="4">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<table width="100%" bgcolor="#99CCFF">
										<tr bgcolor="#99CCFF">
											<td width="10%" align="center">
												<strong>Grupo</strong>
											</td>
											<td width="6%" align="center">
												<strong>Ger&ecirc;ncia</strong>
											</td>
											<td width="7%" align="center">
												<strong>Unidade Negócio</strong>
											</td>

											<td width="26%" align="center">
												<strong>Localidade</strong>
											</td>
											<td width="25%" align="center"> 
												<strong>Setor</strong>
											</td>
											<td width="25%" align="center">
												<strong>Rota</strong>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>

						<logic:present name="colecaoFaturamentoAtividadeCronogramaRota" scope="session">
						<div style="width: 100%; height: 205; overflow: auto;">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td>
								<%int cont = 0;%>
								<table width="100%" align="center" bgcolor="#99CCFF">
									<logic:iterate name="colecaoFaturamentoAtividadeCronogramaRota"
										id="faturamentoAtividadeCronogramaRota">
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
										<%} else {%>
										<tr bgcolor="#FFFFFF">
										<%}%>
											<td width="10%" height="22" align="center">
												<bean:write name="faturamentoAtividadeCronogramaRota"
													property="faturamentoAtividadeCronograma.faturamentoGrupoCronogramaMensal.faturamentoGrupo.id"/>
											</td>
											<td width="9%" align="center">
												<logic:present name="faturamentoAtividadeCronogramaRota"
													property="rota.setorComercial">
													<bean:write name="faturamentoAtividadeCronogramaRota"
														property="rota.setorComercial.localidade.gerenciaRegional.nomeAbreviado" />
												</logic:present> 
												<logic:notPresent name="faturamentoAtividadeCronogramaRota"
													property="rota.setorComercial">
													&nbsp;
												</logic:notPresent>
											</td>
											<td width="9%" align="center">
												<logic:present name="faturamentoAtividadeCronogramaRota"
													property="rota.setorComercial">
													<bean:write name="faturamentoAtividadeCronogramaRota"
														property="rota.setorComercial.localidade.unidadeNegocio.nomeAbreviado" />
												</logic:present> 
												<logic:notPresent name="faturamentoAtividadeCronogramaRota"
													property="rota.setorComercial">
													&nbsp;
												</logic:notPresent>
											</td>


											<td width="26%" align="left">
												<logic:present name="faturamentoAtividadeCronogramaRota"
													property="rota.setorComercial">
													<bean:write name="faturamentoAtividadeCronogramaRota"
														property="rota.setorComercial.localidade.descricao" />
												</logic:present>
												<logic:notPresent name="faturamentoAtividadeCronogramaRota"
													property="rota.setorComercial">
													&nbsp;
												</logic:notPresent>
											</td>
											<td width="27%" align="center">
												<logic:present name="faturamentoAtividadeCronogramaRota"
													property="rota.setorComercial">
													<bean:write name="faturamentoAtividadeCronogramaRota"
														property="rota.setorComercial.codigo" />
												</logic:present>
												<logic:notPresent name="faturamentoAtividadeCronogramaRota"
													property="rota.setorComercial">
													&nbsp;	
												</logic:notPresent>
											</td>
											<td width="24%">
											<div align="center"><bean:write
												name="faturamentoAtividadeCronogramaRota" property="rota.codigo" /></div>
											</td>
										</tr>

									
									</logic:iterate>

								</table>

								</td>
							</tr>

						</table>

						</div>

					</logic:present></td>
				</tr>
				<tr>
					<td height="17" colspan="4"><strong><font color="#FF0000"> </font></strong></td>
				</tr>

<!-- ============================================================================================================================== -->

				<tr>
					<td colspan="4" align="right">
						<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=1" />
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

