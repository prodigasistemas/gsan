<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.faturamento.FaturamentoAtividade" isELIgnored="false"%>
<%@ page import="gcom.util.ConstantesSistema" %>


<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirComandoAtividadeFaturamentoActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript">
<!--
var bCancel = false; 

function validateInserirComandoAtividadeFaturamentoActionForm(form) {                                                                   
	if (bCancel) 
		return true; 
	else 
		return validateDate(form); 
} 

function DateValidations () { 
	this.aa = new Array("vencimentoGrupo", "Vencimento do Grupo inválido.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
} 

function validarForm(form){
	// Objetos
	var objGrupoFaturamento = returnObject(form, "grupoFaturamentoID");
	var objAtividadeFaturamento = returnObject(form, "atividadeFaturamentoID");

	// Constantes
	var NUMERO_NAO_INFORMADO = document.getElementById("NUMERO_NAO_INFORMADO").value;
	var ATIVIDADE_FATURAR_GRUPO_ID = document.getElementById("ATIVIDADE_FATURAR_GRUPO_ID").value;
	var DATA_CORRENTE = document.getElementById("DATA_CORRENTE").value;
	var DATA_VENCIMENTO = document.getElementById("DATA_VENCIMENTO");

	var MES_REFERENCIA = retornarParteData(DATA_VENCIMENTO, true);
	var ANO_REFERENCIA = retornarParteData(DATA_VENCIMENTO, false);

	if (objGrupoFaturamento.value == NUMERO_NAO_INFORMADO){
		alert("Informe Grupo de Faturamento");
		objGrupoFaturamento.focus();
	}
	else if (objAtividadeFaturamento.value == NUMERO_NAO_INFORMADO){
		alert("Informe Atividade de Faturamento");
		objAtividadeFaturamento.focus();
	}
	else if (objAtividadeFaturamento.value == ATIVIDADE_FATURAR_GRUPO_ID){
		var objVencimentoGrupo = returnObject(form, "vencimentoGrupo");

		var MES_VENCIMENTO = retornarParteData(DATA_VENCIMENTO, true);
		var ANO_VENCIMENTO = retornarParteData(DATA_VENCIMENTO, false);

		if (!validateInserirComandoAtividadeFaturamentoActionForm(form)){}
		else if (comparaData(objVencimentoGrupo.value, "<", DATA_CORRENTE) ||
				 comparaData(objVencimentoGrupo.value, "=", DATA_CORRENTE)){
			alert("Vencimento do Grupo deve ser maior que " + DATA_CORRENTE);
			objVencimentoGrupo.focus();
		}
		//else if (MES_VENCIMENTO != retornarParteData(objVencimentoGrupo, true) || 
			//	 ANO_VENCIMENTO != retornarParteData(objVencimentoGrupo, false)){
			//alert("Mês e ano do Vencimento do Grupo não podem ser alterados.");
		//}
		else{
			form.action = "/gsan/inserirComandoAtividadeFaturamentoAction.do";
			submeterFormPadrao(form);
		}
	}
	else{
		form.action = "/gsan/inserirComandoAtividadeFaturamentoAction.do";
		submeterFormPadrao(form);
	}
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

function rotasNaoHabilitadas(){

	// Objetos
	var objGrupoFaturamento = returnObject(document.forms[0], "grupoFaturamentoID");
	var objAtividadeFaturamento = returnObject(document.forms[0], "atividadeFaturamentoID");

	// Constantes
	var NUMERO_NAO_INFORMADO = document.getElementById("NUMERO_NAO_INFORMADO").value;

	if (objGrupoFaturamento.value == NUMERO_NAO_INFORMADO){
		alert("Informe Grupo de Faturamento");
		objGrupoFaturamento.focus();
	}
	else if (objAtividadeFaturamento.value == NUMERO_NAO_INFORMADO){
		alert("Informe Atividade de Faturamento");
		objAtividadeFaturamento.focus();
	}
	else{
		abrirPopup('exibirRotasNaoHabilitadasAction.do?grupo=' + objGrupoFaturamento.value + '&atividade=' + objAtividadeFaturamento.value, 210, 720);
	}
}

//-->
</script>
</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">
<html:form action="/exibirInserirComandoAtividadeFaturamentoAction.do" method="post">

<input type="hidden" id="ATIVIDADE_FATURAR_GRUPO_ID" value="<%= "" + FaturamentoAtividade.FATURAR_GRUPO%>">
<input type="hidden" id="NUMERO_NAO_INFORMADO" value="<%= "" + ConstantesSistema.NUMERO_NAO_INFORMADO%>">

<!-- 
Esteja menor que a current date mais o número mínimo de dias entre a data de emissão 
e a data de vencimento da conta (PARM_NNMINIMODIASEMISSAOVENCIMENTO da tabela SISTEMA_PARAMETROS).
-->
<input type="hidden" id="DATA_CORRENTE" value="${sessionScope.dataCorrente}">
<input type="hidden" id="DATA_VENCIMENTO" value="${sessionScope.exibirCampoVencimentoGrupo}">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

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
		<td width="625" valign="top" class="centercoltext">
	        <table height="100%">
		        <tr><td></td></tr>
	      	</table>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
					<td class="parabg">Inserir Comando de Atividade de Faturamento</td>
					<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
				</tr>
			</table>
			<p>&nbsp;</p>

<!-- ============================================================================================================================== -->

			<table width="100%" border="0">
				<tr> 
					<td>
						Para determinar a atividade de faturamento a  ser comandada, informe os dados abaixo:
					</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoComandoAtividadeInserir', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>			
				</tr>
				</table>
				<table width="100%" border="0">
				<tr> 
					<td>
						<strong>Grupo de Faturamento:<font color="#FF0000">*</font></strong>
					</td>
			        <td>
						<html:select property="grupoFaturamentoID" style="width: 200px;" tabindex="1" 
							onchange="redirecionarSubmit('exibirInserirComandoAtividadeFaturamentoAction.do');">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoGrupoFaturamento" labelProperty="descricao" property="id"/>
						</html:select>
					</td>
					<td><strong>Refer&ecirc;ncia Faturamento:</strong></td>
			        <td>
						<html:text property="referenciaFaturamento" size="7" maxlength="7" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</td>
				</tr>
				<tr> 
					<td><strong>Atividade de Faturamento:<font color="#FF0000">*</font></strong></td>
					<td colspan="3">
						<html:select property="atividadeFaturamentoID" style="width: 200px;" tabindex="3" 
							onchange="redirecionarSubmit('exibirInserirComandoAtividadeFaturamentoAction.do?pesquisa=atividade');">
							<logic:present name="colecaoAtividadeFaturamento">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoAtividadeFaturamento" labelProperty="descricao" property="id"/>
							</logic:present>
						</html:select>
				  </td>
				</tr>
				<tr> 
					<td height="17"><strong></strong></td>
					<td colspan="3">
						<strong><font color="#FF0000">*</font></strong> 
	                    Campos obrigat&oacute;rios
					</td>
				</tr>
				<tr> 
					<td colspan="4"></td>
				</tr>

				<logic:present name="exibirCampoVencimentoGrupo" scope="session">
				<tr> 
					<td><strong>Vencimento do Grupo:<font color="#FF0000">*</font></strong></td>
					<td colspan="3">
						<html:text property="vencimentoGrupo" size="10" maxlength="10" tabindex="4" 
							onkeyup="mascaraData(this, event);"/>
						<a href="javascript:abrirCalendario('InserirComandoAtividadeFaturamentoActionForm', 'vencimentoGrupo')">
							<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
								width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
						<font size="2">dd/mm/aaaa</font>		
					</td>
				</tr>
				</logic:present>

				<tr> 
					<td colspan="4" height="10"></td>
				</tr>
				<tr> 
					<td height="17"><strong>Rotas habilitadas do grupo:</strong></td>
					<td colspan="3" align="right"> 
						<input type="button" class="bottonRightCol" name="popupRotasNaoHabilitadas" 
							value="Rotas N&atilde;o Habilitadas" onclick="rotasNaoHabilitadas();" tabindex="5">
					</td>
				</tr>
				<tr> 
					<td colspan="4">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr> 
								<td> 
									<table width="100%" bgcolor="#99CCFF">
					                    <tr bgcolor="#99CCFF"> 
											<td width="11%" align="center"><strong>Grupo</strong></td>
					                        <td width="6%" align="center"><strong>Ger&ecirc;ncia</strong></td>
					                        <td width="7%" align="center"><strong>Unidade Negócio</strong></td>
					                        <td width="26%" align="center"><strong>Localidade</strong></td>
					                        <td width="25%" align="center"><strong>Setor</strong></td>
					                        <td width="25%" align="center"><strong>Rota</strong></td>
					                    </tr>
				                    </table>
								</td>
				            </tr>
			            </table>

						<logic:present name="colecaoRotasHabilitadas" scope="request">
						<div style="width: 100%; height: 100; overflow: auto;">
						<table width="100%" cellpadding="0" cellspacing="0">
				            <tr> 
								<td> 
									<% int cont = 0;%>
									<table width="100%" align="center" bgcolor="#99CCFF">
									<logic:iterate name="colecaoRotasHabilitadas" id="rota">
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
										<%} else {%>
										<tr bgcolor="#FFFFFF">
										<%}%>
											<td width="11%" height="22" align="center">
												<bean:write name="rota" property="faturamentoGrupo.id"/>
											</td>
											<td width="9%" align="center">
												<logic:present name="rota" property="setorComercial">
													<bean:write name="rota" property="setorComercial.localidade.gerenciaRegional.nomeAbreviado"/>
												</logic:present>
												<logic:notPresent name="rota" property="setorComercial">&nbsp;</logic:notPresent>
											</td>
											<td width="9%" align="center">
												<logic:present name="rota" property="setorComercial">
													<logic:present name="rota" property="setorComercial.localidade">
														<bean:write name="rota" property="setorComercial.localidade.unidadeNegocio.nomeAbreviado"/>
													</logic:present>
												</logic:present>
												<logic:notPresent name="rota" property="setorComercial">&nbsp;</logic:notPresent>
											</td>

											<td width="26%" align="left">
												<logic:present name="rota" property="setorComercial">
													<bean:write name="rota" property="setorComercial.localidade.descricao"/>
												</logic:present>
												<logic:notPresent name="rota" property="setorComercial">&nbsp;</logic:notPresent>	
											</td>
											<td width="27%" align="center">
												<logic:present name="rota" property="setorComercial">
													<bean:write name="rota" property="setorComercial.codigo"/>
												</logic:present>
												<logic:notPresent name="rota" property="setorComercial">&nbsp;</logic:notPresent>
											</td>
											<td width="24%" align="center">
												<bean:write name="rota" property="codigo"/>
											</td>
										</tr>
									</logic:iterate>
									</table>
								</td>
				            </tr>
						</table>
						</div>
						</logic:present>
					</td>
				</tr>
				<tr> 
					<td><br></td>
				</tr>
				<tr>
					<td colspan="4">
						<table border="0" width="100%">
							<tr>
								<td>
									<input type="button" name="Button" class="bottonRightCol" value="Desfazer"
										onClick="javascript:window.location.href='/gsan/exibirInserirComandoAtividadeFaturamentoAction.do?menu=sim'">
									<input type="button" name="Button" class="bottonRightCol" value="Cancelar"
										onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"/>
								</td>
								<td align="right">
								<gsan:controleAcessoBotao name="Button" value="Inserir"
							  onclick="javascript:validarForm(document.forms[0]);" url="inserirComandoAtividadeFaturamentoAction.do"/>
								<!-- <input type="button" name="botaoInserir" class="bottonRightCol" value="Inserir" 
										onClick="javascript:validarForm(document.forms[0])"/> -->
								</td>
							</tr>
						</table>	
					</td>			
				</tr>
			</table>

<!-- ============================================================================================================================== -->

			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>