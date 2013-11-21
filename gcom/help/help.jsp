<jsp:useBean id="helpBroker" class="javax.help.ServletHelpBroker" scope="session" />
<%@ taglib uri="/WEB-INF/jhlib.tld" prefix="jh" %>

<%@ page import="javax.help.*" %>
<%
pageContext.setAttribute("caminhoHelp",gcom.util.ConstantesAplicacao.get("caminho.help"));
%>


<jh:validate helpBroker="<%= helpBroker %>" helpSetName="${pageScope.caminhoHelp}"/>

<html>
<head>
<%
// only an "id" should be set.
String id = request.getParameter("id");
String mapIDHelpSet = request.getParameter("mapIDHelpSet");

if (id == null) {
    // nothing to do
    // in regular java code we would return.
} else {
    if (id != null) {
        // Yep, just update the helpBroker
	// The contentsframe has already been updated
	helpBroker.setCurrentID(id);
    }
}

 	HelpSet helpSetAtual = helpBroker.getHelpSet();
 	

	//Pega as configurações do HelpSet
	javax.help.Map mapa = helpSetAtual.getCombinedMap();

	//Obtém o endereço URL do map.xml para exibí-lo no help		
	helpBroker.setCurrentURL(mapa.getURLFromID(javax.help.Map.ID.create(mapIDHelpSet, helpSetAtual)));
	//URL endereco = (mapa.getURLFromID(javax.help.Map.ID.create(mapIDHelpSet, helpSetAtual)));	

//Enumeration enumeration = mapa.getAllIDs();
//while (enumeration.hasMoreElements()) {
// 	out.println(enumeration.nextElement());
	
//}





//helpBroker.setCurrentURL(new URL("/gsan/help/gcomHTML/Cadastro/Localidade/quadraManter.html"));
//helpBroker.getHelpSet().getCombinedMap();

//out.print(helpBroker.getCurrentNavigatorView());
%>
<%@ include file="/jsp/util/titulo.jsp"%>
<SCRIPT LANGUAGE="JavaScript1.3" src="util.js">
</SCRIPT>
</head>
<FRAMESET ROWS="65,*" NAME="helptop" BORDER=0 FRAMESPACING=0 FRAMEBORDER=NO>
    <FRAMESET COLS="*,0" NAME="upperframe" NORESIZE FRAMEBORDER=NO>
		<FRAME SRC="banner.html" NAME="bannerframe" SCROLLING="NO">
		<FRAME SRC="update.jsp" NAME="updateframe">
    </FRAMESET>
    <FRAMESET COLS="30%,70%" NAME="lowerhelp" BORDER=5 FRAMESPACING=0 FRAMEBORDER=NO>
		<FRAMESET ROWS="40,*" NAME="navigatortop" BORDER=0 FRAMESPACING=0 FRAMEBORDER=NO>
	    	<FRAME SRC="navigator.jsp" NAME="navigatorframe" SCROLLING="NO" FRAMEBORDER=NO>
	    	<FRAME SRC="loading.html" NAME="treeframe" SCROLLING="AUTO" FRAMEBORDER=YES>
		</FRAMESET>
		<FRAMESET ROWS="40,*" NAME="rightpane" FRAMESPACING=0 FRAMEBORDER=NO>
	    	<FRAME SRC="toolbar.html" NAME="toolbarframe" SCROLLING="NO" FRAMEBORDER=NO>
	    	<FRAME SRC="<jsp:getProperty name="helpBroker" property="currentURL" />" NAME="contentsFrame" SCROLLING="AUTO" FRAMEBORDER=YES>
	    </FRAMESET>
    </FRAMESET>
</FRAMESET>
<NOFRAMES>
<BODY bgcolor=white>
Help requires frames to be displayed
</BODY>
</NOFRAMES>
</HTML>
