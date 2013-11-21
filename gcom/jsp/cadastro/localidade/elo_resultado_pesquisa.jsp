<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>

<%@ page import="gcom.util.Pagina, gcom.util.ConstantesSistema" %>
<%@ page import="gcom.cadastro.localidade.Localidade"%>
<%@ page import="java.util.Collection"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta name="generator" content="Web page layout created by Xara Webstyle 4 - http://www.xara.com/webstyle/" />
<link rel="stylesheet" href="<bean:message key='caminho.css'/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key='caminho.js'/>util.js" ></script>

</head>

<body leftmargin="0" topmargin="0" onload="resizePageSemLink(760, 400);">
<table width="720" border="0" cellpadding="0" cellspacing="5">
  <tr>
    <td width="710" valign="top" class="centercoltext"> <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="710" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img src="<bean:message key='caminho.imagens'/>parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" /></td>
          <td class="parabg">Pesquisar Elo</td>
          <td width="11"><img src="<bean:message key='caminho.imagens'/>parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
        </tr>
      </table>
      <p>&nbsp;</p>
	
        	<table width="710" cellpadding="0" cellspacing="0"  border="0"  bgcolor="#90c7fc">
                <tr bgcolor="#90c7fc" align="center">
                	<td  width="10%"><strong>Código</strong></td>
                    <td width="68%"><strong>Localidade Pólo</strong></td>
                    <td width="22%"><strong>Gerência Regional</strong></td>
                </tr>
                <tr bgcolor="#90c7fc">
                <td colspan="3">
                <table width="100%">
				<%--Esquema de paginação--%>
					<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
					export="currentPageNumber=pageNumber;pageOffset"
					maxPageItems="10" items="${sessionScope.totalRegistros}">
         
				<pg:param name="pg"/>
				<pg:param name="q"/>
				<%int cont = 0;%>
				<logic:iterate id="elo" name="elos" type="Localidade">
					<pg:item>

			<%cont = cont + 1;
			if (cont % 2 == 0) {%>
				<tr bgcolor="#cbe5fe">				
			<%} else {	%>
				<tr bgcolor="#FFFFFF">
			<%}%>

							  
							  <td align="center">
								<bean:write name="elo" property="id"/>
							  </td>
							  <td >
								<a href="javascript:enviarDados('<bean:write name="elo" property="id"/>', '<bean:write name="elo" property="descricao"/>', 'elo');">
								  <bean:write name="elo" property="descricao"/>
								</a>
								</td>
							  <td >
								<bean:write name="elo" property="gerenciaRegional.nome"/>
							  </td>
						</tr>

			
				</pg:item>
				</logic:iterate>
				</table>
				</td>
				</tr>
				
            </table>
    
    <table width="100%" border="0">
	<tr>
         <td align="center">
            <strong><%@ include file="/jsp/util/indice_pager_novo.jsp"%></strong>
		</td>
    </tr>
	<tr> 
		<td>  
			<INPUT type="button" class="bottonRightCol" value="Voltar Pesquisa" tabindex="1" onclick="window.location.href='/gsan/exibirPesquisarEloAction.do?objetoConsulta=1'">
		</td>
	</tr>
	</table>
	
	</pg:pager> 
	</td>
  </tr>
  
</table>
</body>

</html:html>


























