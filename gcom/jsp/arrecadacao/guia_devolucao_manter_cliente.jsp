<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@page import="gcom.arrecadacao.GuiaDevolucao"%>

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
<script language="JavaScript">

function validarForm(form){
	if(CheckboxNaoVazio(document.ManterGuiaDevolucaoActionForm.idRegistrosRemocao) && confirm('Confirma remoção?')){
	  form.submit();
	}
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

function verificarSelecao(objeto){
		if (CheckboxNaoVazio(objeto)){
		document.forms[0].action = "/gsan/gerarRelatorioGuiaDevolucaoAction.do"
		document.forms[0].submit();
	}
 }
 
	function CheckboxNaoVazio(campo){
	    form = document.forms[0];
		retorno = false;
	
		for(indice = 0; indice < form.elements.length; indice++){
			if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
				retorno = true;
				break;
			}
		}
	
		if (!retorno){
			alert('Selecione pelo menos uma guia de devolução para remover.'); 
		}
	
		return retorno;
	} 
</script>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/manterGuiaDevolucaoAction"
	name="ManterGuiaDevolucaoActionForm"
	type="gcom.gui.arrecadacao.ManterGuiaDevolucaoActionForm" method="post"
	>


	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Cancelar Guia de Devolução do Cliente</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>Dados do Cliente:</td>
				</tr>
				<tr>
					<td width="180"><strong>Código do Cliente:</strong></td>
					<td colspan="3" align="left">
					<div align="left"><html:text property="idCliente" readonly="true"
						style="background-color:#EFEFEF; border:0;" size="7" maxlength="7" /></div>
					</td>
				</tr>
				<tr>
					<td width="180"><strong>Nome do Cliente:</strong></td>
					<td colspan="3" align="left">
					<div align="left"><html:text property="nomeCliente" readonly="true"
						style="background-color:#EFEFEF; border:0;" size="50"
						maxlength="50" /></div>
					</td>
				</tr>
				<tr>
					<td width="180"><strong>CPF/CNPJ:</strong></td>

					<td colspan="3" align="left"><html:text property="cpfCnpj"
						readonly="true" style="background-color:#EFEFEF; border:0;"
						size="20" maxlength="20" /></td>
				</tr>
				<tr>
					<td width="180"><strong>Profiss&atilde;o:</strong></td>

					<td align="right">
					<div align="left"><html:text property="profissao" readonly="true"
						style="background-color:#EFEFEF; border:0;" size="30"
						maxlength="30" /></div>
					</td>
					<td width="180"><strong>Ramo de Atividade:</strong></td>
					<td align="right">
					<div align="left"><html:text property="ramoAtividade"
						readonly="true" style="background-color:#EFEFEF; border:0;"
						size="20" maxlength="20" /></div>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="10"></td>
				</tr>
				<tr>
					<td colspan="4"><strong>Guias de Devolução cadastradas para o
					cliente:</strong>
					<table width="100%" align="center" bgcolor="#90c7fc" border="0"
						cellpadding="0" cellspacing="0">
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" bgcolor="#90c7fc">
								<tr bordercolor="#000000">
									<td width="8%" bgcolor="#90c7fc">
									<div align="center"><strong><a
										href="javascript:facilitador(this);">Todos</a></strong></div>
									</td>
									<td width="17%" bgcolor="#90c7fc" align="center"><strong>Identificação
									da Guia</strong></td>
									<td width="25%" bgcolor="#90c7fc" align="center"><strong>Tipo
									de Crédito</strong></td>
									<td width="20%" bgcolor="#90c7fc" align="center"><strong>Tipo
									do Documento</strong></td>
									<td width="15%" bgcolor="#90c7fc" align="center"><strong>Valor
									da Guia</strong></td>
									<td width="15%" bgcolor="#90c7fc" align="center"><strong>Data
									de Emissão</strong></td>
								</tr>
								<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
									export="currentPageNumber=pageNumber;pageOffset"
									maxPageItems="10" items="${sessionScope.totalRegistros}">
									<pg:param name="pg" />
									<pg:param name="q" />
									<logic:present name="colecaoGuiaDevolucao">
										<%int cont = 1;%>
										<logic:iterate name="colecaoGuiaDevolucao" id="guiaDevolucao"
											type="GuiaDevolucao">
											<pg:item>
												<%cont = cont + 1;
			if (cont % 2 == 0) {%>
												<tr bgcolor="#FFFFFF">
													<%} else {

			%>
												<tr bgcolor="#cbe5fe">
													<%}%>
													<td width="8%">
													<div align="center"><input type="checkbox"
														name="idRegistrosRemocao"
														value="<bean:write name="guiaDevolucao" property="id"/>" /></div>
													</td>
													<%-- 
													<td width="17%" align="center"><html:link
														href="/gsan/exibirAtualizarGuiaDevolucaoAction.do?manter=sim"
														paramId="guiaDevolucaoID" paramProperty="id"
														paramName="guiaDevolucao"
														title="<%="" + guiaDevolucao.getId()%>">${guiaDevolucao.id}</html:link></td> --%>
													<td width="17%" align="left">${guiaDevolucao.id}</td>
													<td width="25%" align="left">${guiaDevolucao.creditoTipo.descricao}&nbsp;</td>
													<td width="20%" align="left">${guiaDevolucao.documentoTipo.descricaoDocumentoTipo}&nbsp;</td>
													<td width="15%" align="right">
														<bean:write name="guiaDevolucao" property="valorDevolucao" formatKey="money.format"/>
													</td>
													<td width="15%" align="center"><bean:write
														name="guiaDevolucao" property="dataEmissao"
														formatKey="date.format" />&nbsp;</td>
												</tr>
											</pg:item>
										</logic:iterate>
									</logic:present>
							</table>
							</td>
						</tr>
					</table>
					<table width="100%" border="0">
						<tr>
							<td colspan="2">
							<div align="center"><strong><%@ include
								file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
							</td>
						</tr>
						</pg:pager>
						<tr>
							<td width="60%">
							  <font color="#FF0000"> 
							    <gsan:controleAcessoBotao name="Button" value="Remover" onclick="javascript:validarForm(document.forms[0]);" url="manterGuiaDevolucaoAction.do"/>
							    <%--<html:submit property="buttonRemover" styleClass="bottonRightCol" value="Remover" /> --%>
							  </font>
							  <font color="#FF0000"> <input
								type="button" name="buttonFiltro" class="bottonRightCol"
								value="Voltar Filtro"
								onClick="javascript:window.location.href='/gsan/exibirFiltrarGuiaDevolucaoAction.do?paginacao=sim'">
								<input type="button" name="" value="Emitir Guia(s) de Devolução" class="bottonRightCol" 
								onclick="verificarSelecao(document.ManterGuiaDevolucaoActionForm.idRegistrosRemocao)"
								/>
							</font></td>
							
							<td valign="top">
							<div align="right"><a href="javascript:toggleBox('demodiv',1);">
							<img border="0"
								src="<bean:message key="caminho.imagens"/>print.gif"
								title="Imprimir Guias de Devolução" /> </a></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioGuiaDevolucaoManterAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
