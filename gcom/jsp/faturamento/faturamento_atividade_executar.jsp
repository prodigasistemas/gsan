<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>

<%@ page import="gcom.faturamento.bean.ExecutarAtividadeFaturamentoHelper"%>
<%@ page import="gcom.util.Pagina, gcom.util.ConstantesSistema, gcom.util.Util" %>
<%@ page import="java.util.Collection"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>

<SCRIPT LANGUAGE="JavaScript">
<!--

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
function validarForm(){
	
	var mensagem = 'Selecione as atividades de faturamento para execução';

	if (CheckboxNaoVazioMensagemGenerico(mensagem,document.forms[0].idsAtividadesCobrancaCronograma) && CheckboxNaoVazioMensagemGenerico(mensagem,document.forms[0].idsAtividadesCobrancaEventuais)){
	  submeterFormPadrao(document.forms[0]);
	}
}
//-->
</SCRIPT>


</head>

<body leftmargin="5" topmargin="5">


<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<html:form action="/executarAtividadeFaturamentoAction" method="post">
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
		          <td class="parabg">Executar Atividade de Faturamento</td>
		          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
	    	    </tr>
		    </table>
			<!-- Início do Corpo - Roberta Costa -->
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td><strong> Atividades do cronograma de faturamento comandadas para execução:</strong></td>
				</tr>
			</table> 
			<table width="100%" border="0" bgcolor="#99CCFF">
				<tr bgcolor="79BBFD">
					<td align="center" width="5%" rowspan="2"><A HREF="javascript:facilitador(this);" id="0"><strong>Todos</strong></A></td>
					<td align="center" width="10%" rowspan="2"><strong>Grupo</strong></td>
					<td align="center" width="10%" rowspan="2"><strong>Mês/Ano</strong></td>
					<td align="center" width="40%" rowspan="2"><strong>Atividade</strong></td>
					<td align="center" width="15%" rowspan="2"><strong>Data Prevista</strong></td>
					<td align="center" width="20%" colspan="2"><strong>Comando</strong></td>
				</tr>
				<tr>
					<td align="center" width="50%" bgcolor="#cbe5fe"><FONT COLOR="#000000"><strong>Data</strong></FONT></td>
					<td align="center" width="50%" bgcolor="#cbe5fe"><FONT COLOR="#000000"><strong>Hora</strong></FONT></td>
				</tr>
				<% String cor = "#FFFFFF";%>

				<%--Esquema de paginação--%>
		        <pg:pager isOffset="true" index="half-full" maxIndexPages="10"
		        	export="currentPageNumber=pageNumber;pageOffset"	
		        		maxPageItems="10" items="${sessionScope.totalRegistros}">
				<pg:param name="pg"/>
				<pg:param name="q"/>
				<logic:iterate name="colecaoExecutarAtividadeFaturamento" id="atividade" type="ExecutarAtividadeFaturamentoHelper">
			        <pg:item>
					<%if (cor.equalsIgnoreCase("#FFFFFF")){
						cor = "#cbe5fe";%>
					<tr bgcolor="#cbe5fe">
					<%} else{
							cor = "#FFFFFF";%>
					<tr bgcolor="#FFFFFF">
					<%}%>
						<td align="center" width="5%">
							<html:checkbox property="idsFaturamentoAtividadeCronograma" value="<%="" + atividade.getIdFaturamentoAtividadeCronograma()%>"/>
						</td>
						<logic:present name="atividade" property="idGrupoFaturamento">
							<td align="center" width="10%">
								<bean:write name="atividade" property="idGrupoFaturamento"/>
							</td>
						</logic:present>
						<logic:notPresent name="atividade" property="idGrupoFaturamento">
							<td align="center" width="10%">&nbsp;</td>
						</logic:notPresent>
						<logic:present name="atividade" property="anoMesFaturamento">
							<td align="center" width="10%">
								<%= Util.formatarAnoMesParaMesAno(atividade.getAnoMesFaturamento())%>
							</td>
						</logic:present>
						<logic:notPresent name="atividade" property="anoMesFaturamento">
							<td align="center" width="10%">&nbsp;</td>
						</logic:notPresent>
						<logic:present name="atividade" property="descricaoAtividade">
							<td align="center" width="40%">
								<bean:write name="atividade" property="descricaoAtividade"/>
							</td>
						</logic:present>
						<logic:notPresent name="atividade" property="descricaoAtividade">
							<td align="center" width="40%">&nbsp;</td>
						</logic:notPresent>
						<logic:present name="atividade" property="dataPrevista">
							<td align="center" width="15%">
								<bean:write name="atividade" property="dataPrevista" formatKey="date.format"/>
							</td>
						</logic:present>
						<logic:notPresent name="atividade" property="dataPrevista">
							<td align="center" width="15%">&nbsp;</td>
						</logic:notPresent>
						<logic:present name="atividade" property="comando">
							<td align="center" width="20%">
								<bean:write name="atividade" property="comando" formatKey="date.format"/>
							</td>
						</logic:present>
						<logic:notPresent name="atividade" property="comando">
							<td align="center" width="20%">&nbsp;</td>
						</logic:notPresent>
						<logic:present name="atividade" property="comando">
							<td align="center" width="20%">
								<bean:write name="atividade" property="comando" formatKey="hour.format"/>
							</td>
						</logic:present>
						<logic:notPresent name="atividade" property="comando">
							<td align="center" width="20%">&nbsp;</td>
						</logic:notPresent>
					</tr>
					</pg:item>
				</logic:iterate>
			</table>
	        <table width="100%">
	          <tr>
	            <td width="50%"> 
	              <input type="button" onclick="window.location.href='/gsan/telaPrincipal.do'" class="bottonRightCol" value="Cancelar" style="width: 70px;">
			    </td>
	            <td align="right"> 
	              <input type="button" onclick="validarForm();" class="bottonRightCol" value="Executar" style="width: 70px;">
			    </td>
	          </tr>
	        </table>
			<table width="100%" border="0">
				<tr>
		          <td align="center">
		            <strong><%@ include	file="/jsp/util/indice_pager_novo.jsp"%></strong>
		          </td>
		        </tr>
			</table>
			</pg:pager>
						<!-- Fim do Corpo - Roberta Costa -->
						<p>&nbsp;</p>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>