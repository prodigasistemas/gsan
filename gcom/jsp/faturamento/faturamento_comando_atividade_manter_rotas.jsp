<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.micromedicao.Rota" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirComandoAtividadeFaturamentoActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript">
<!--
function validateInserirComandoAtividadeFaturamentoActionForm(form){
	return true;
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
			redirecionarSubmit('removerSelecaoRotaAction.do?forward=exibirSelecionarRota');
		 }
	}
}

//-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form action="/atualizarComandoAtividadeFaturamentoWizardAction" method="post">

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard.jsp?numeroPagina=2"/>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<input type="hidden" name="numeroPagina" value="2"/>

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
					<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
					<td class="parabg">Atualizar Comando de Atividade de Faturamento</td>
					<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
				</tr>
			</table>
			<p>&nbsp;</p>

<!-- ============================================================================================================================== -->
			<table width="100%" border="0">
				<tr> 
					<td>Clique em adicionar para informar a(s) rota(s) abaixo: </td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoComandoAtividadeAtualizarAbaRotas', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>	
				</tr>
				</table>
				<table width="100%" border="0">
				<tr> 
					<td height="17"><strong>Rota(s):</strong></td>
					<td colspan="3" align="right">
						<input type="button" class="bottonRightCol" value="Adicionar" tabindex="1" id="botaoRota" 
							onclick="javascript:abrirPopup('exibirSelecionarRotaAction.do?limparForm=ok&grupo='+<%= session.getAttribute("faturamentoCronogramaAtividadeID")%>+'', 500, 680);">
					</td>
				</tr>
				<tr> 
					<td colspan="4">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr> 
				                <td> 
									<table width="100%" bgcolor="#99CCFF">
                					    <tr bgcolor="#99CCFF"> 
											<td align="center" width="10%">
												<a href="javascript:facilitador(this);" id="0"><strong>Todos</strong></a>
											</td>
											<td width="11%" align="center"><strong>Grupo</strong></td>
					                        <td width="9%" align="center"><strong>Ger&ecirc;ncia</strong></td>
					                        <td width="9%" align="center"><strong>Unidade Negócio</strong></td>
					                        <td width="26%" align="center"><strong>Localidade</strong></td>
					                        <td width="20%" align="center"><strong>Setor</strong></td>
					                        <td width="20%" align="center"><strong>Rota</strong></td>
					                    </tr>
                    				</table>
								</td>
    				        </tr>
			            </table>

						<logic:present name="colecaoRotasSelecionadasUsuario" scope="session">
						<div style="width: 100%; height: 205; overflow: auto;">
						<table width="100%" cellpadding="0" cellspacing="0">
				            <tr> 
								<td> 
									<%int cont = 0;%>
									<table width="100%" align="center" bgcolor="#99CCFF">
										<logic:iterate name="colecaoRotasSelecionadasUsuario" id="rota" type="Rota">
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
										<%} else {%>
										<tr bgcolor="#FFFFFF">
										<%}%>
											<td align="center" width="10%">
												<html:checkbox property="idRotaSelecao" value="<%="" + rota.getId()%>"/>
											</td>
											<td align="center" width="11%">
												<logic:present name="rota" property="faturamentoGrupo">
													<bean:write name="rota" property="faturamentoGrupo.id"/>
												</logic:present>
												<logic:notPresent name="rota" property="faturamentoGrupo">
													&nbsp;
												</logic:notPresent>
											</td>
											<td align="center" width="9%">
												<logic:present name="rota" property="setorComercial">
													<bean:write name="rota" property="setorComercial.localidade.gerenciaRegional.nomeAbreviado"/>
												</logic:present>
												<logic:notPresent name="rota" property="setorComercial">
													&nbsp;
												</logic:notPresent>
											</td>
											<td align="center" width="9%">
												<logic:present name="rota" property="setorComercial">
													<bean:write name="rota" property="setorComercial.localidade.unidadeNegocio.nomeAbreviado"/>
												</logic:present>
												<logic:notPresent name="rota" property="setorComercial">
													&nbsp;
												</logic:notPresent>
											</td>

											<td width="26%" align="left">
												<logic:present name="rota" property="setorComercial">
													<bean:write name="rota" property="setorComercial.localidade.descricao"/>
												</logic:present>
												<logic:notPresent name="rota" property="setorComercial">
													&nbsp;
												</logic:notPresent>	
											</td>
											<td align="center" width="20%">
												<logic:present name="rota" property="setorComercial">
													<bean:write name="rota" property="setorComercial.codigo"/>
												</logic:present>
												<logic:notPresent name="rota" property="setorComercial">
													&nbsp;
												</logic:notPresent>
											</td>
											<td align="center" width="20%">
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
					<td height="17" colspan="4">
						<logic:present name="colecaoRotasSelecionadasUsuario" scope="session">
							<input type="button" class="bottonRightCol" value="Remover" tabindex="6" 
								onclick="verficarSelecao(idRotaSelecao);" style="width: 70px">
						</logic:present>
					</td>
				</tr>
	  
<!-- ============================================================================================================================== -->

				<tr>
					<td colspan="4" align="right">
						<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=2"/>
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