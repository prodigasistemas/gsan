<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.Pagina,gcom.util.ConstantesSistema,java.util.Collection"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js">
</script>
<script>
<!--
function facilitador(objeto){
	if (objeto.value == "0" || objeto.value == undefined){
		objeto.value = "1";
		marcarTodos();
	}
	else{
		objeto.value = "0";
		desmarcarTodos();
	}
}
function verficarSelecao(objeto, tipoObjeto){

       var indice;
       var array = new Array(objeto.length);
       var selecionado = "";
	   var formulario = document.forms[0]; 

	   for(indice = 0; indice < formulario.elements.length; indice++){
		 if (formulario.elements[indice].type == tipoObjeto && formulario.elements[indice].checked == true) {
			selecionado = formulario.elements[indice].value;
			break;
		 }
	   }

       if (selecionado.length < 1) {
         alert('Nenhum registro selecionado.');
       }else {
		 if (confirm ("Confirma remoção?")) {
			redirecionarSubmit("/gsan/removerDevolucaoAction.do");
		}
		}
   }
-->
</script>
</head>
<body leftmargin="5" topmargin="5">
<html:form action="/exibirManterDevolucaoAction"
	name="ManterDevolucaoActionForm"
	type="gcom.gui.arrecadacao.ManterDevolucaoActionForm" method="post">
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="120" valign="top" class="leftcoltext">
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
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Manter Devoluções</td>
					<td width="11" valign="top"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<!-- Início do Corpo - Fernanda Paiva-->
			<p>&nbsp;</p>
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td colspan="11"><font color="#000000" style="font-size:10px"
						face="Verdana, Arial, Helvetica, sans-serif"> <strong>Devoluções
					Encontradas:</strong> </font></td>
				</tr>
				<tr>
					<td colspan="11" bgcolor="#000000" height="2"></td>
				</tr>
				<tr>
					<td colspan="11">
					<table width="100%" bgcolor="#99CCFF">
						<tr bordercolor="#90c7fc">
							<td width="6%" bgcolor="#90c7fc" rowspan="2">
							<div align="center"><strong><a
								href="javascript:facilitador(this);">Todas</a></strong></div>
							</td>
							<td width="10%" rowspan="2" bgcolor="#90c7fc">
							<div align="center"><strong>Im&oacute;vel</strong></div>
							</td>
							<td width="7%" rowspan="2" bgcolor="#90c7fc">
							<div align="center"><strong>Cliente</strong></div>
							</td>
							<td colspan="3" bgcolor="#90c7fc">
							<div align="center"><strong>Aviso Banc&aacute;rio</strong></div>
							</td>
							<td width="20%" rowspan="2" bgcolor="#90c7fc">
							<div align="center"><strong>Tipo do Documento da Guia de
							Devolu&ccedil;&atilde;o</strong></div>
							</td>
							<td width="11%" rowspan="2" bgcolor="#90c7fc">
							<div align="center"><strong>Valor da Devolu&ccedil;&atilde;o</strong></div>
							</td>
							<td width="11%" rowspan="2" bgcolor="#90c7fc">
							<div align="center"><strong>Data da Devolu&ccedil;&atilde;o</strong></div>
							</td>
							<td width="10%" rowspan="2" bgcolor="#90c7fc">
							<div align="center"><strong>Situa&ccedil;&atilde;o Atual</strong></div>
							</td>
						</tr>
						<tr bordercolor="#90c7fc">
							<td width="7%" bgcolor="#cbe5fe">
							<div align="center"><strong>Agente</strong></div>
							</td>
							<td width="11%" bgcolor="#cbe5fe">
							<div align="center"><strong>Data Lan&ccedil;amento</strong></div>
							</td>
							<td width="7%" bgcolor="#cbe5fe">
							<div align="center"><strong>Seq.</strong></div>
							</td>
						</tr>
						<%String cor = "#cbe5fe";%>
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg" />
							<pg:param name="q" />
							<%--Esquema de paginação--%>
							<logic:present name="colecaoDevolucoes" scope="session">
								<logic:iterate name="colecaoDevolucoes" id="devolucao">
									<pg:item>
										<%if (cor.equalsIgnoreCase("#cbe5fe")) {
				cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF">
											<%} else {
				cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="6%">
											<div align="center"><input type="checkbox"
												name="idRegistrosRemocao"
												value="<bean:write name="devolucao" property="id"/>" /></div>
											</td>
											<td width="10%" align="center"><logic:notEmpty
												name="devolucao" property="imovel">
												<bean:define name="devolucao" property="imovel" id="imovel" />
												<bean:write name="imovel" property="id" />
											</logic:notEmpty></td>
											<td width="7%" align="center"><logic:notEmpty
												name="devolucao" property="cliente">
												<bean:define name="devolucao" property="cliente"
													id="cliente" />
												<bean:write name="cliente" property="id" />
											</logic:notEmpty></td>
											<td width="7%" align="center"><logic:notEmpty
												name="devolucao" property="avisoBancario">
												<bean:write name="devolucao"
													property="avisoBancario.arrecadador.codigoAgente" />
											</logic:notEmpty></td>
											<td width="11%" align="center"><logic:notEmpty
												name="devolucao" property="avisoBancario">
												<bean:write name="devolucao"
													property="avisoBancario.dataLancamento" format="dd/MM/yyyy" />
											</logic:notEmpty></td>
											<td width="7%" align="center"><logic:notEmpty
												name="devolucao" property="avisoBancario">
												<bean:write name="devolucao"
													property="avisoBancario.numeroSequencial" />
											</logic:notEmpty></td>
											<td width="20%" align="left"><logic:notEmpty name="devolucao"
												property="guiaDevolucao">
												<bean:write name="devolucao"
													property="guiaDevolucao.documentoTipo.descricaoDocumentoTipo" />
											</logic:notEmpty></td>
											<td width="11%" align="right"><logic:notEmpty
												name="devolucao" property="valorDevolucao">
												<a
													href="exibirAtualizarDevolucaoAction.do?manter=sim&idRegistroAtualizacao=<bean:write name="devolucao" property="id" />">
												<bean:write name="devolucao" property="valorDevolucao"
													formatKey="money.format" /> </a>
											</logic:notEmpty></td>
											<td width="11%" align="center"><bean:write name="devolucao"
												property="dataDevolucao" format="dd/MM/yyyy" /></td>
											<td width="10%" align="left"><logic:notEmpty name="devolucao"
												property="devolucaoSituacaoAtual">
												<bean:write name="devolucao"
													property="devolucaoSituacaoAtual.descricaoAbreviado" />
											</logic:notEmpty></td>
										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="5">
					<table width="100%" border="0">
						<tr>
							<td colspan="5">
							<table width="100%" border="0">
								<tr>
									<td valign="top">
									  <%-- <input name="Remover" type="button" class="bottonRightCol" value="Remover" onclick="verficarSelecao(idRegistrosRemocao, 'checkbox')"> --%>
									  <gsan:controleAcessoBotao name="Remover" value="Remover" onclick="javascript:verficarSelecao(idRegistrosRemocao, 'checkbox');" url="removerDevolucaoAction.do"/>
									  
									  <input
										name="button" type="button" class="bottonRightCol"
										value="Voltar Filtro"
										onclick="window.location.href='/gsan/exibirFiltrarDevolucaoAction.do?tela=manterDevolucao&botaoAtualizar=botaoAtualizar'"
										align="left" style="width: 80px;"></td>
									<td valign="top">
									<div align="right"><a href="javascript:toggleBox('demodiv',1);">
									<img border="0"
										src="<bean:message key="caminho.imagens"/>print.gif"
										title="Imprimir Devoluções" /> </a></div>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td>
					<div align="center"><strong><%@ include
						file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
					</td>
					</pg:pager>
				</tr>
			</table>
			<%-- Fim do esquema de paginação --%> <!-- Fim do Corpo --></td>
		</tr>
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioDevolucaoAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
