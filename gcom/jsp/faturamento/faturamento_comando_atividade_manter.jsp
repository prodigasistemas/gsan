<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.faturamento.FaturamentoAtividadeCronograma"%>
<%@ page import="gcom.util.Util"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript">
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

// Verifica se há item selecionado
function verficarSelecao(objeto){
	if (CheckboxNaoVazio(objeto)){
		if (confirm ("Confirma remoção?")) {
			document.forms[0].submit();
		 }
	}
}

//-->
</script>
</head>
<body leftmargin="5" topmargin="5">

<html:form action="/removerComandoAtividadeFaturamentoAction" method="post">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
    	<td width="115" valign="top" class="leftcoltext">
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
        <p>&nbsp;</p>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
					<td class="parabg">Manter Comando de Atividade de Faturamento</td>
					<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>
			<table width="590" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4" height="23"><font color="#000000" style="font-size: 10px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Atividades de Faturamento Comandadas e não realizadas:</strong></font></td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoComandoAtividadeManter', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>	
				</tr>	
				</table>
				<table width="590" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
				    <td bgcolor="#000000" height="2"></td>
				</tr>
				<tr>
					<td>
						<table width="590" bgcolor="#99CCFF" border="0">
							<tr bgcolor="#99CCFF">
								<td align="center" width="45">
									<a href="javascript:facilitador(this);" id="0"><strong>Todos</strong></a>
								</td>
								<td align="center" width="50"><strong>Grupo</strong></td>
								<td align="center" width="70"><strong>Referência</strong></td>
								<td align="center" width="160"><strong>Atividade</strong></td>
								<td align="center" width="130"><strong>Data do Comando</strong></td>
								<td align="center" width="130"><strong>Data Prevista</strong></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
		            <td>
			            <table width="590" bgcolor="#99CCFF">
							<%int cont = 0;%>
							<%--Esquema de paginação--%>
					       	<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
								export="currentPageNumber=pageNumber;pageOffset"				
								maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg"/>
							<pg:param name="q"/>
							<logic:iterate name="colecaoAtividadesAtualizacao" 
								id="faturamentoAtividadeCronograma" type="FaturamentoAtividadeCronograma">
							<pg:item>
							<%cont = cont + 1;
							if (cont % 2 == 0) {%>
							<tr bgcolor="#cbe5fe">
							<%} else {%>
							<tr bgcolor="#FFFFFF">
							<%}%>
								<td align="center" width="50">
									<html:checkbox property="faturamentoAtividadeCronogramaID" value="<%="" + faturamentoAtividadeCronograma.getId()%>"/>
								</td>
								<td align="center" width="50">
									<bean:write name="faturamentoAtividadeCronograma" property="faturamentoGrupoCronogramaMensal.faturamentoGrupo.id"/>
								</td>
								<td align="center" width="70">
									<%="" + Util.formatarAnoMesParaMesAno(faturamentoAtividadeCronograma.getFaturamentoGrupoCronogramaMensal().getFaturamentoGrupo().getAnoMesReferencia())%>
								</td>
								<td width="160">
									<html:link href="/gsan/exibirAtualizarComandoAtividadeFaturamentoAction.do" paramId="faturamentoAtividadeCronogramaID" paramProperty="id" paramName="faturamentoAtividadeCronograma" title="<%="" + faturamentoAtividadeCronograma.getFaturamentoAtividade().getDescricao()%>"><bean:write name="faturamentoAtividadeCronograma" property="faturamentoAtividade.descricao"/></html:link>
								</td>
								<td align="center" width="130">
									<%="" + Util.formatarData(faturamentoAtividadeCronograma.getComando())%>
								</td>
								<td align="center" width="130">
									<logic:present name="faturamentoAtividadeCronograma" property="dataPrevista">
										<%="" + Util.formatarData(faturamentoAtividadeCronograma.getDataPrevista())%>
									</logic:present>
								</td>
							</tr>
							</pg:item>
							</logic:iterate>
						</table>
					</td>
				</tr>
				<tr bordercolor="#90c7fc">
    	           	<td>
						<table width="100%">
							<tr>
								<td>
									<gsan:controleAcessoBotao name="Button" value="Remover"
							  onclick="verficarSelecao(faturamentoAtividadeCronogramaID);" url="removerComandoAtividadeFaturamentoAction.do"/>
								<!--
									<input name="Button" type="button" class="bottonRightCol" value="Remover" onclick="verficarSelecao(faturamentoAtividadeCronogramaID)" align="left" style="width: 70px;">
								-->
								</td>
                        		<!-- 
                        		<td align="right" valign="top">
		                             <a href="javascript:abrirPopupRelatorio('');">
										<img align="right" border="0" src="<bean:message key='caminho.imagens'/>print.gif"  title=""/></a>
		                        </td>
		                        -->
							</tr>
        		        </table>
						<table width="100%" border="0">
							<tr>
								<td align="center">
									<strong><%@ include file="/jsp/util/indice_pager_novo.jsp"%></strong>
								</td>
							</tr>
						</table>
					</td>
	            </tr>
			</table>
			</pg:pager>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>
<%@ include file="/jsp/util/rodape.jsp"%>

</body>
</html:form>
</html:html>