<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="gcom.cobranca.DocumentosReceberFaixaDiasVencidos"%>
<%@ page import="gcom.util.Pagina, gcom.util.ConstantesSistema" %>
<%@ page import="gcom.gui.faturamento.FaturamentoImediatoAjusteHelper" %>
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

function voltar(){
	var form = document.forms[0];
	
	form.action = 'exibirFiltrarFaturamentoImediatoAjusteAction.do?menu=sim';
	form.submit();
	
}

//-->
</SCRIPT>


</head>

<body leftmargin="5" topmargin="5">


<html:form action="filtrarFaturamentoImediatoAjusteAction" >


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

        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Consultar Faturamento Imediato Ajuste</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
		<tr>
			<td height="5" colspan="3"></td>
		</tr>
      </table>

	  <table width="590" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td colspan="4" height="23"><font color="#000000" style="font-size: 10px" face="Verdana, Arial, Helvetica, sans-serif">
            	<strong>Imóveis em Faturamento Imediato Ajuste:</strong></font>
            </td>
		</tr>
	  </table>
			
	  <table width="100%" cellpadding="0" cellspacing="0">
        <tr>
            <td bgcolor="#000000" height="2"></td>
        </tr>
		<tr>
			<td>
				<table width="100%" bgcolor="#99CCFF" border="0">
					<tr bgcolor="#99CCFF">
						<td align="center" width="11%" >
							<FONT COLOR="#000000"><strong>Imóvel</strong></FONT>
						</td>
						<td align="center" width="24%">
							<FONT COLOR="#000000"><strong>Inscrição</strong></FONT>
						</td>
						<td align="center" width="11%">
							<FONT COLOR="#000000"><strong>Ref.</strong></FONT>
						</td>
						<td align="center" width="10%">
							<FONT COLOR="#000000"><strong>Grupo</strong></FONT>
						</td>
						<td align="center" width="10%">
							<FONT COLOR="#000000"><strong>Rota</strong></FONT>
						</td>
						<td align="center" width="10%">
							<FONT COLOR="#000000"><strong>Dif. Valor da Água</strong></FONT>
						</td>
						<td align="center" width="10%">
							<FONT COLOR="#000000"><strong>Dif. Consumo de Água</strong></FONT>
						</td>
						<td align="center" width="10%">
							<FONT COLOR="#000000"><strong>Dif. Valor do Esgoto</strong></FONT>
						</td>
						<td align="center" width="10%">
							<FONT COLOR="#000000"><strong>Dif. Consumo de Esgoto</strong></FONT>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
            <td>
            <table width="100%" bgcolor="#99CCFF">

		<% String cor = "#cbe5fe";%>

		<%--Esquema de paginação--%>
		<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
		export="currentPageNumber=pageNumber;pageOffset"
		maxPageItems="10" items="${sessionScope.totalRegistros}">
			<pg:param name="pg" />
			<pg:param name="q" />

		<logic:iterate name="colecaoFaturamentoImediatoAjuste" id="helper" type="FaturamentoImediatoAjusteHelper">
          <pg:item>

				<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
					cor = "#FFFFFF";%>
					<tr bgcolor="#FFFFFF" height="18">	
				<%} else{	
					cor = "#cbe5fe";%>
					<tr bgcolor="#cbe5fe" height="18">		
				<%}%>

				<td align="center" width="10%"><bean:write name="helper" property="imovelId"/></td>
				<td align="center" width="20%"><bean:write name="helper" property="inscricao"/></td>
				<td align="center" width="10%"><bean:write name="helper" property="mesAnoReferencia"/></td>
				<td align="center" width="10%"><bean:write name="helper" property="faturamentoGrupo"/></td>
				<td align="center" width="10%"><bean:write name="helper" property="rota"/></td>
				<td align="center" width="10%"><bean:write name="helper" property="difValorAgua"/></td>
				<td align="center" width="10%"><bean:write name="helper" property="difConsumoAgua"/></td>
				<td align="center" width="10%"><bean:write name="helper" property="difValorEsgoto"/></td>
				<td align="center" width="10%"><bean:write name="helper" property="difConsumoEsgoto"/></td>
				
			</tr>

			</pg:item>
		</logic:iterate>

			</table><!-- </div> -->

				</td>
			</tr>
			<tr bordercolor="#90c7fc">
				<td>
	                  <table width="100%">
		                 <tr>
							<td><font color="#FF0000">
								<input name="Button" type="button"
							   		   class="bottonRightCol" value="Voltar Filtro" 
							   		   align="left"
							   		   onclick="javascript:voltar();">
								<input name="button" type="button"
									   class="bottonRightCol" value="Cancelar"
									   onclick="window.location.href='/gsan/telaPrincipal.do'"
									   align="left" style="width: 80px;"> 
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
					<%-- Fim do esquema de paginação --%>
				</tr>

			</table>
	</td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>

</body>
</html:html>
