<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="java.util.Collection,gcom.util.ConstantesSistema"%>
<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.seguranca.acesso.usuario.UsuarioAlteracao"%>
<%@ page import="gcom.seguranca.transacao.TabelaLinhaColunaAlteracao"%>
<%@ page import="gcom.seguranca.acesso.OperacaoEfetuada"%>
<%@ page import="java.util.Date"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script>
<!--

function abrirRemover(){

	if (CheckboxNaoVazio(document.forms[0].idRegistrosRemocao)) {
		if  (confirm('Confirma remoção?')){
			document.forms[0].action = '<html:rewrite page="/ExcluirAvisoBancarioAction.do"/>';
			document.forms[0].submit();
		}
	}
}

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

-->
</script>


<style>
teste {
	font-size: 5px;
}
</style>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/ExibirAtualizarAvisoBancarioAction" method="post">


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
			<td width="602" valign="top" class="centercoltext">
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

					<logic:present name="acao" scope="session">
						<td class="parabg">Efetuar Análise do Aviso Bancário</td>
					</logic:present>

					<logic:notPresent name="acao" scope="session">
						<td class="parabg">Manter Aviso Bancário</td>
					</logic:notPresent>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td colspan="9" height="23"><font style="font-size: 10px;"
						color="#000000" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Avisos
					Bancários Encontrados:</strong></font></td>
				</tr>
				<tr>
					<td colspan="9" bgcolor="#000000" height="2"></td>
				</tr>
			</table>

			<table width="100%" bgcolor="#90c7fc">

				<%--Esquema de paginação--%>
				<pg:pager isOffset="true" index="half-full" maxIndexPages="10" export="currentPageNumber=pageNumber;pageOffset" maxPageItems="10" items="${sessionScope.totalRegistros}">
					<pg:param name="pg" />
					<pg:param name="q" />
					<%int cont = 0;%>
					<tr bordercolor="#000000">

						 <logic:notPresent name="acao"> 
							<td rowspan="2" bgcolor="#90c7fc" align="center">
							<strong><a  href="javascript:facilitador(this);">Todos</a></strong>
							</td>									
						 </logic:notPresent> 
						<td rowspan="2" bgcolor="#90c7fc" width="13%">
						<div align="center"><strong>Arrecadador</strong></div>
						</td>
						<td rowspan="2" bgcolor="#90c7fc" width="13%">
						<div align="center">
						<p><strong>Data do Lançamento</strong></p>
						</div>
						</td>
						<td rowspan="2" bgcolor="#90c7fc" width="5%">
						<div align="center"><strong>Seq.</strong></div>
						</td>
						<td rowspan="2" bgcolor="#90c7fc" width="8%">
						<div align="center"><strong>Tipo do Aviso</strong></div>
						</td>
						<td bgcolor="#90c7fc">
						<div align="center"><strong>Previsão do Crédito/Débito</strong></div>
						</td>
						<td colspan="2" bgcolor="#90c7fc">
						<div align="center">
						<p><strong>Realização do Crédito/Débito</strong></p>
						</div>
						</td>
						<td rowspan="2" bgcolor="#90c7fc" width="5%">
						<div align="center"><strong>Situação</strong></div>
						</td>
					</tr>
					<tr bordercolor="#99ccff">
						<td bgcolor="#cbe5fe" width="14%">
						<div align="center"><strong>Data Prevista</strong></div>
						</td>
						<td bgcolor="#cbe5fe" width="15%">
						<div align="center"><strong>Data Realizada </strong></div>
						</td>
						<td bgcolor="#cbe5fe" width="15%">
						<div align="center"><strong>Valor Realizado</strong></div>
						</td>
					</tr>

					<logic:present name="collAvisoBancarios">
						<logic:iterate name="collAvisoBancarios" id="avisoBancario"
							indexId="i">
							<pg:item>
								<%cont = cont + 1;
	if (cont % 2 == 0) {%>
								<tr bgcolor="#cbe5fe">
									<%} else {

	%>
								<tr bgcolor="#FFFFFF">
									<%}%>

									<logic:notPresent name="acao" scope="session">
										<td align="center"><input type="checkbox" name="idRegistrosRemocao"
											value="<bean:write name="avisoBancario" property="avisoBancario.id"/>"></td>
									</logic:notPresent>

									<td>
									<div align="center"><bean:write name="avisoBancario"
										property="avisoBancario.arrecadador.id" />&nbsp;</div>
									</td>
									<td>
									<div align="center"><%=Util
							.formatarData(((gcom.arrecadacao.aviso.bean.AvisoBancarioHelper) avisoBancario)
									.getAvisoBancario()
									.getDataLancamento())%>&nbsp;</div>
									</td>
									<td>
									<div align="center">
									
									<logic:present name="acao" scope="session">
									<a href="exibirApresentarAnaliseAvisoBancarioAction.do?botao=sim&manter=sim&idAvisoBancario=<bean:write
										 name="avisoBancario" property="avisoBancario.id" />">
										<bean:write name="avisoBancario" property="avisoBancario.numeroSequencial" />&nbsp;
									</a>
									</logic:present>
				
									<logic:notPresent name="acao" scope="session">
										<a
										href="/gsan/ExibirAtualizarAvisoBancarioAction.do?idAvisoBancario=<bean:write name="avisoBancario" property="avisoBancario.id"/>&manter=sim"><bean:write
										name="avisoBancario"
										property="avisoBancario.numeroSequencial" /></a>&nbsp;
									</logic:notPresent>
									
									</div>
									</td>
									<td>
									<div align="left"><logic:equal name="avisoBancario"
										property="avisoBancario.indicadorCreditoDebito" value="1">
					CRÉDITO
				</logic:equal> <logic:equal name="avisoBancario"
										property="avisoBancario.indicadorCreditoDebito" value="2">
					DÉBITO
				</logic:equal></div>
									</td>
									<td>
									<div align="center"><logic:present name="avisoBancario"
										property="avisoBancario.dataPrevista">
										<%=Util
							.formatarData(((gcom.arrecadacao.aviso.bean.AvisoBancarioHelper) avisoBancario)
									.getAvisoBancario()
									.getDataPrevista())%>
									</logic:present></div>
									</td>
									<td>
									<div align="center"><logic:present name="avisoBancario"
										property="avisoBancario.dataRealizada">
										<%=Util
							.formatarData(((gcom.arrecadacao.aviso.bean.AvisoBancarioHelper) avisoBancario)
									.getAvisoBancario()
									.getDataRealizada())%>
									</logic:present></div>
									</td>
									<td>
									<div align="right"><logic:present name="avisoBancario"
										property="avisoBancario.valorRealizado">
										<%=Util
							.formatarMoedaReal(((gcom.arrecadacao.aviso.bean.AvisoBancarioHelper) avisoBancario)
									.getAvisoBancario()
									.getValorRealizado())%>
									</logic:present></div>
									</td>
									<td>
									<div align="left"><bean:write name="avisoBancario"
										property="tipoAviso" />&nbsp;</div>
									</td>
								</tr>
							</pg:item>
						</logic:iterate>
					</logic:present>
			</table>


			<table width="100%">
				<tr>
					<td valign="top">
						<logic:notPresent name="acao" scope="session">
						  <gsan:controleAcessoBotao name="button" value="Remover" onclick="javascript:abrirRemover();" url="ExcluirAvisoBancarioAction.do" align="left"/>
						  <%-- <input type="button" class="bottonRightCol" value="Remover" align="left" onclick="javascript:abrirRemover();" style="width: 70px;"> --%>
						</logic:notPresent> 
						<logic:present
							scope="session" name="filtrar_manter">
							<input name="Submit222"
								class="bottonRightCol" value="Voltar Filtro" type="button"
								onclick="window.location.href='/gsan/exibirFiltrarAvisoBancarioAction.do';">
						</logic:present> 
						<logic:notPresent scope="session" name="filtrar_manter">
							<logic:notPresent name="acao" scope="session">
								<input name="button" type="button"
									class="bottonRightCol" value="Voltar Filtro"
									onclick="window.location.href='<html:rewrite page="/exibirFiltrarAvisoBancarioAction.do"/>'"
									align="left" style="width: 80px;">
							</logic:notPresent>
							
							<logic:present name="acao" scope="session">
								<input name="button" type="button"
									class="bottonRightCol" value="Voltar Filtro"
									onclick="window.location.href='<html:rewrite page="/exibirFiltrarAvisoBancarioAction.do?acao=efetuar"/>'"
									align="left" style="width: 80px;">
							</logic:present>
						</logic:notPresent>
					</td>
					<td valign="top"><div align="right"><a href="javascript:toggleBox('demodiv',1);">
							<img border="0"
								src="<bean:message key="caminho.imagens"/>print.gif"
								title="Imprimir Avisos Bancários" /> </a></div></td>
				</tr>
			</table>
			
			<table width="100%" border="0">
				<tr>
					<td>
					<div align="center"><strong><%@ include
						file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
					</td>
					</pg:pager>
					<%-- Fim do esquema de paginação --%>
				</tr>

			</table>
			</td>
		</tr>
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioAvisoBancarioManterAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
