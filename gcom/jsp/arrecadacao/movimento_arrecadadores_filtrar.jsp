<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

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
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="FiltrarMovimentoArrecadadoresActionForm" />

<SCRIPT LANGUAGE="JavaScript">
<!--


function validarForm(form){

	if (validateFiltrarMovimentoArrecadadoresActionForm(form)){
	
		var DATA_ATUAL = document.getElementById("DATA_ATUAL").value;
	
		var dataGeracaoMovimentoInicio = returnObject(form, "dataGeracaoMovimentoInicio");
		var dataGeracaoMovimentoFim = returnObject(form, "dataGeracaoMovimentoFim");
		
		var realizarSubmit = true;
		
		if (dataGeracaoMovimentoInicio.value.length > 0 && comparaData(dataGeracaoMovimentoInicio.value, ">", DATA_ATUAL )){
			realizarSubmit = false;
			alert("Data Inicial do Período de Geração do Movimento posterior à data corrente " + DATA_ATUAL + ".");
			dataGeracaoMovimentoInicio.focus();
		}
		else if (dataGeracaoMovimentoFim.value.length > 0 && comparaData(dataGeracaoMovimentoFim.value, ">", DATA_ATUAL )){
			realizarSubmit = false;
			alert("Data Final do Período de Geração do Movimento posterior à data corrente " + DATA_ATUAL + ".");
			dataGeracaoMovimentoFim.focus();
		}
		else if ((dataGeracaoMovimentoFim.value.length > 0 && dataGeracaoMovimentoFim.value.length > 0) && comparaData(dataGeracaoMovimentoInicio.value, ">", dataGeracaoMovimentoFim.value )){
			realizarSubmit = false;
			alert("Data Final do Período de Geração do Movimento é anterior à Data Inicial.");
			dataGeracaoMovimentoFim.focus();
		}
		if (realizarSubmit){
		
			var dataProcessamentoMovimentoInicio = returnObject(form, "dataProcessamentoMovimentoInicio");
			var dataProcessamentoMovimentoFim = returnObject(form, "dataProcessamentoMovimentoFim");
			
			if (dataProcessamentoMovimentoInicio.value.length > 0 && comparaData(dataProcessamentoMovimentoInicio.value, ">", DATA_ATUAL )){
				realizarSubmit = false;
				alert("Data Inicial do Período de Processamento do Movimento posterior à data corrente " + DATA_ATUAL + ".");
				dataProcessamentoMovimentoInicio.focus();
			}
			else if (dataProcessamentoMovimentoFim.value.length > 0 && comparaData(dataProcessamentoMovimentoFim.value, ">", DATA_ATUAL )){
				realizarSubmit = false;
				alert("Data Final do Período de Processamento do Movimento posterior à data corrente " + DATA_ATUAL + ".");
				dataProcessamentoMovimentoFim.focus();
			}
			else if ((dataProcessamentoMovimentoInicio.value.length > 0 && dataProcessamentoMovimentoFim.value.length > 0) && comparaData(dataProcessamentoMovimentoInicio.value, ">", dataProcessamentoMovimentoFim.value )){
				realizarSubmit = false;
				alert("Data Final do Período de Processamento do Movimento é anterior à Data Inicial.");
				dataProcessamentoMovimentoFim.focus();
			}
		}
		
		if (realizarSubmit){
			submeterFormPadrao(form);
		}
		
	}
}

function replicar(objetoOriginal, objetoReplicacao){

	objetoReplicacao.value = objetoOriginal.value; 

}

function limpar(situacao){

	var form = document.forms[0];

	switch (situacao){
       case 1:
		   form.banco.value = "";
		   form.descricaoBanco.value = "";
		   
		   //Coloca o foco no objeto selecionado
		   form.banco.focus();
		   break;
		   
	   case 2:
		   form.descricaoBanco.value = "";
		   
		   break;
	   	default:
          break;
	}
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];

    if (tipoConsulta == 'arrecadador') {
      form.banco.value = codigoRegistro;
      form.descricaoBanco.value = descricaoRegistro;
      form.descricaoBanco.style.color = "#000000";
    }
}

//-->
</SCRIPT>


</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">

<input type="hidden" id="DATA_ATUAL" value="${requestScope.dataAtual}" />

<html:form action="/filtrarMovimentoArrecadadoresAction" method="post">
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="135" valign="top" class="leftcoltext">
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

				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
						<td class="parabg">Filtrar Movimento de Arrecadador</td>
						<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
				</table>
				<p>&nbsp;</p>

				<table width="100%" border="0">
					<tr>
						<td colspan="2">Para filtrar movimento de arrecadador, informe os dados abaixo:</td>
					</tr>
					
					<tr>
						<td width="183" height="30"><strong>Arrecadador:</strong></td>
						<td width="432">
						    <html:text property="banco" size="4" maxlength="3" tabindex="1" onkeypress="validaEnterComMensagem(event, 'exibirFiltrarMovimentoArrecadadoresAction.do', 'banco', 'Arrecadador'); return isCampoNumerico(event);" />
							<a href="javascript:abrirPopup('exibirPesquisarArrecadadorAction.do');">
								<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Arrecadador" />
							</a> 
							
							<logic:present name="corBanco">
								<logic:equal name="corBanco" value="exception">
									<html:text property="descricaoBanco" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:equal>
	
								<logic:notEqual name="corBanco" value="exception">
									<html:text property="descricaoBanco" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEqual>
							</logic:present> 
							
							<logic:notPresent name="corBanco">
								<logic:empty name="FiltrarMovimentoArrecadadoresActionForm" property="banco">
									<html:text property="descricaoBanco" value="" size="45"	readonly="true"	style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:empty>
								
								<logic:notEmpty name="FiltrarMovimentoArrecadadoresActionForm" property="banco">
									<html:text property="descricaoBanco" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>
							</logic:notPresent> 
							
							<a href="javascript:limpar(1);"> 
							  <img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
							</a>
						</td>
					</tr>
				
					<tr>
						<td height="30"><strong>Remessa:</strong></td>
						<td>
							<%--<logic:equal name="relatorio" scope="request" value="<%=""+ConstantesSistema.SIM%>">--%>
							<logic:equal name="FiltrarMovimentoArrecadadoresActionForm" property="indicadorRelatorio" value="<%=""+ConstantesSistema.SIM%>">
								<html:radio property="remessa" value="<%=""+ConstantesSistema.CODIGO_ENVIO%>" tabindex="2" disabled="true"/><strong><%=ConstantesSistema.ENVIO%>
								<html:radio property="remessa" value="<%=""+ConstantesSistema.CODIGO_RETORNO%>" tabindex="3" /><%=ConstantesSistema.RETORNO%></strong>
								<html:radio property="remessa" value="1" tabindex="3" disabled="true" /><strong>Todos</strong>
							</logic:equal>
							<%--<logic:equal name="relatorio" scope="request" value="<%=""+ConstantesSistema.NAO%>">--%>
							<logic:equal name="FiltrarMovimentoArrecadadoresActionForm" property="indicadorRelatorio" value="<%=""+ConstantesSistema.NAO%>">
								<html:radio property="remessa" value="<%=""+ConstantesSistema.CODIGO_ENVIO%>" tabindex="2" /><strong><%=ConstantesSistema.ENVIO%>
								<html:radio property="remessa" value="<%=""+ConstantesSistema.CODIGO_RETORNO%>" tabindex="3" /><%=ConstantesSistema.RETORNO%></strong>
								<html:radio property="remessa" value="" tabindex="3" /><strong>Todos</strong>
							</logic:equal>
							
						</td>
					</tr>
					
					<tr>
						<td HEIGHT="30"><strong>Identificação do Serviço:</strong></td>
						<td>
							<html:select property="identificacaoServico" style="width: 200px;" tabindex="4">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:option value="<%=ConstantesSistema.DEBITO_AUTOMATICO%>">DÉBITO AUTOMÁTICO</html:option>
								<html:option value="<%=ConstantesSistema.CODIGO_DE_BARRAS%>">CÓDIGO DE BARRAS</html:option>
								<html:option value="<%=ConstantesSistema.FICHA_DE_COMPENSACAO%>">FICHA DE COMPENSAÇÃO</html:option>
							</html:select>
						</td>
					</tr>
					
					<tr>
						<td HEIGHT="30"><strong>Número Sequencial Arquivo (NSA):</strong></td>
						<td><html:text property="nsa" size="7" maxlength="6" tabindex="5" onkeypress="return isCampoNumerico(event);"/></td>
					</tr>
					
					<tr>
						<td><strong>Forma de Arrecadação:</strong></td>
						<td>
							<%--<logic:equal name="relatorio" scope="request" value="<%=""+ConstantesSistema.SIM%>">--%>
							<logic:equal name="FiltrarMovimentoArrecadadoresActionForm" property="indicadorRelatorio" value="<%=""+ConstantesSistema.SIM%>">
								<html:select property="formaArrecadacao" style="width: 230px;">
									<logic:present name="colecaoArrecadacaoForma">
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<html:options collection="colecaoArrecadacaoForma" labelProperty="descricao" property="id" />
									</logic:present>
								</html:select>
							</logic:equal>
							
							<%--<logic:equal name="relatorio" scope="request" value="<%=""+ConstantesSistema.NAO%>">--%>
							<logic:equal name="FiltrarMovimentoArrecadadoresActionForm" property="indicadorRelatorio" value="<%=""+ConstantesSistema.NAO%>">
								<html:select property="formaArrecadacao" style="width: 230px;" disabled="true">
									<logic:present name="colecaoArrecadacaoForma">
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<%--<html:options collection="colecaoArrecadacaoForma" labelProperty="descricao" property="id" /> --%>
									</logic:present>
								</html:select>
							</logic:equal>
							
						</td>
					</tr>
						
					<tr>
						<td colspan="2" height="10"></td>
					</tr>
					
					<tr>
						<td colspan="2">
							<table width="100%" align="center" bgcolor="#99CCFF" border="0" >
								<tr>
									<td><strong>Período do Movimento:</strong></td>
								</tr>
								
								<tr bgcolor="#cbe5fe">
									<td width="100%" align="center">
										<table width="100%" border="0">
											<tr>
												<td height="30" width="150"><strong>Geração:</strong></td>
												<td>
													<html:text property="dataGeracaoMovimentoInicio" size="11" maxlength="10" tabindex="6" onkeyup="mascaraData(this, event);replicar(this, document.forms[0].dataGeracaoMovimentoFim)" 
													onkeypress="return isCampoNumerico(event);"/>
													<a href="javascript:abrirCalendario('FiltrarMovimentoArrecadadoresActionForm', 'dataGeracaoMovimentoInicio');">
														<img align="absmiddle" src="<bean:message key='caminho.imagens'/>calendario.gif" width="20" border="0" alt="Exibir Calendário" tabindex="7" />
													</a>
													<strong>a</strong> 
													<html:text property="dataGeracaoMovimentoFim" size="11" maxlength="10" tabindex="8" 
													onkeyup="mascaraData(this, event);" 
													onkeypress="return isCampoNumerico(event);"/> 
													<a href="javascript:abrirCalendario('FiltrarMovimentoArrecadadoresActionForm', 'dataGeracaoMovimentoFim')" onkeypress="return isCampoNumerico(event);">
														<img align="absmiddle" src="<bean:message key='caminho.imagens'/>calendario.gif" width="20" border="0" alt="Exibir Calendário" tabindex="9" />
													</a>
													dd/mm/aaaa
												</td>
											</tr>
											
											<tr>
												<td height="30" width="150"><strong>Processamento:</strong></td>
												<td>
													<html:text property="dataProcessamentoMovimentoInicio" size="11" maxlength="10" tabindex="10" onkeyup="mascaraData(this, event);replicar(this, document.forms[0].dataProcessamentoMovimentoFim)" 
													onkeypress="return isCampoNumerico(event);"/>
													<a href="javascript:abrirCalendario('FiltrarMovimentoArrecadadoresActionForm', 'dataProcessamentoMovimentoInicio');">
														<img align="absmiddle" src="<bean:message key='caminho.imagens'/>calendario.gif" width="20" border="0" alt="Exibir Calendário" tabindex="11" />
													</a>

													<strong>a</strong> 
													<html:text property="dataProcessamentoMovimentoFim" size="11" maxlength="10" tabindex="12" onkeyup="mascaraData(this, event);" 
													onkeypress="return isCampoNumerico(event);"/> 
													<a href="javascript:abrirCalendario('FiltrarMovimentoArrecadadoresActionForm', 'dataProcessamentoMovimentoFim')">
														<img align="absmiddle" src="<bean:message key='caminho.imagens'/>calendario.gif" width="20" border="0" alt="Exibir Calendário" tabindex="13" />
													</a>
													dd/mm/aaaa
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr>
						<td colspan="2" height="10"></td>
					</tr>
					
					<tr>
						<td colspan="2">
							<table width="100%" align="center" bgcolor="#99CCFF" border="0">
								<tr>
									<td><strong>Movimentos com / sem:</strong></td>
								</tr>
								
								<tr bgcolor="#cbe5fe">
									<td width="100%" align="center">

										<table width="100%" border="0">
											<tr>
												<td HEIGHT="30" width="150"><strong>Itens em Ocorrência:</td>
												<td>
													<%--<logic:equal name="relatorio" scope="request" value="<%=""+ConstantesSistema.NAO%>"> --%>
													<logic:equal name="FiltrarMovimentoArrecadadoresActionForm" property="indicadorRelatorio" value="<%=""+ConstantesSistema.NAO%>">
														<html:select property="movimentoItemOcorrencia" style="width: 200px;" tabindex="14">
															<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
															<html:option value="<%=""+ConstantesSistema.COM_ITENS%>">COM ÍTENS EM OCORRÊNCIA</html:option>
															<html:option value="<%=""+ConstantesSistema.SEM_ITENS%>">SEM ÍTENS EM OCORRÊNCIA</html:option>
														</html:select>
													</logic:equal>
													
													<%--<logic:equal name="relatorio" scope="request" value="<%=""+ConstantesSistema.SIM%>">--%>
													<logic:equal name="FiltrarMovimentoArrecadadoresActionForm" property="indicadorRelatorio" value="<%=""+ConstantesSistema.SIM%>">
														<html:select property="movimentoItemOcorrencia" style="width: 200px;" tabindex="14" disabled="true">
															<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
														</html:select>
													</logic:equal>
													
												</td>
											</tr>
											
											<tr>
												<td height="30" width="150"><strong>Itens não Aceitos:</td>
												<td>
													<%--<logic:equal name="relatorio" scope="request" value="<%=""+ConstantesSistema.NAO%>">--%>
													<logic:equal name="FiltrarMovimentoArrecadadoresActionForm" property="indicadorRelatorio" value="<%=""+ConstantesSistema.NAO%>">
														<html:select property="movimentoItemAceito" style="width: 200px;" tabindex="15">
															<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
															<html:option value="<%=""+ConstantesSistema.COM_ITENS%>">COM ÍTENS NÃO ACEITOS</html:option>
															<html:option value="<%=""+ConstantesSistema.SEM_ITENS%>">SEM ÍTENS NÃO ACEITOS</html:option>
														</html:select>
													</logic:equal>
													<%--<logic:equal name="relatorio" scope="request" value="<%=""+ConstantesSistema.SIM%>">--%>
													<logic:equal name="FiltrarMovimentoArrecadadoresActionForm" property="indicadorRelatorio" value="<%=""+ConstantesSistema.SIM%>">
														<html:select property="movimentoItemAceito" style="width: 200px;" tabindex="15" disabled="true">
															<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
														</html:select>
													</logic:equal>
													
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr>
						<td colspan="2" height="10"></td>
					</tr>
					
					<tr>
						<td HEIGHT="30"><strong>Movimentos Abertos/Fechados:</td>
						<td>
							<%--<logic:equal name="relatorio" scope="request" value="<%=""+ConstantesSistema.NAO%>"> --%>
							<logic:equal name="FiltrarMovimentoArrecadadoresActionForm" property="indicadorRelatorio" value="<%=""+ConstantesSistema.NAO%>">
								<html:select property="movimentoAbertoFechado" style="width: 200px;" tabindex="15">
									<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:option value="<%=""+ConstantesSistema.MOVIMENTO_ABERTO%>">ABERTOS</html:option>
									<html:option value="<%=""+ConstantesSistema.MOVIMENTO_FECHADO%>">FECHADOS</html:option>
								</html:select>
							</logic:equal>
							
							<%--<logic:equal name="relatorio" scope="request" value="<%=""+ConstantesSistema.SIM%>">--%>
							<logic:equal name="FiltrarMovimentoArrecadadoresActionForm" property="indicadorRelatorio" value="<%=""+ConstantesSistema.SIM%>">
								<html:select property="movimentoAbertoFechado" style="width: 200px;" tabindex="15" disabled="true">
									<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								</html:select>
							</logic:equal>
							
						</td>
					</tr>
					
					<tr>
						<td colspan="2" height="10"></td>
					</tr>
					
					<tr>
						<td colspan="4">
							<table width="100%">
								<tr>
									<td>
										<input name="Submit22" class="bottonRightCol" value="Limpar" type="button" onclick="window.location.href='/gsan/exibirFiltrarMovimentoArrecadadoresAction.do?menu=sim';">
									</td>
									<td align="right">
										<gsan:controleAcessoBotao name="botaoFiltrar" value="Filtrar" onclick="javascript:validarForm(document.forms[0]);" url="filtrarMovimentoArrecadadoresAction.do" tabindex="16" /> 
										<%-- <INPUT type="button" onclick="validarForm(document.forms[0]);" name="botaoFiltrar" class="bottonRightCol" value="Filtrar" tabindex="16" style="width: 70px;"> --%>
									</td>
								</tr>
							</table>
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
