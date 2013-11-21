<jsp:useBean id="helpBroker" class="javax.help.ServletHelpBroker" scope="session" />
<%@ page import="javax.help.SearchView" %>
<%@ taglib uri="/WEB-INF/jhlib.tld" prefix="jh" %>
<HTML>
<% String query = request.getParameter("searchQuery"); %>
<STYLE type="text/css">
    .anchorStyle { text-decoration:none; color:black; margin-left:8pt; font-family: Verdana, Geneva, Arial, Helvetica, sans-serif; font-size: 13px;}
    .anchorBoldStyle { text-decoration:none; color:black; font-weight: bold; margin-left:5pt;}
</STYLE>
<BODY BGCOLOR=#EFEFEF>
<SCRIPT LANGUAGE="JavaScript1.3" src="searchlist.js">
</SCRIPT>

<FORM METHOD="GET" NAME="search" ACTION="javax.help.SearchView.jsp">
<P>Pesquisar:
<INPUT TYPE="text" NAME="searchQuery" VALUE="<%= query!=null?query:"" %>" >
</FORM>

<%
if (query != null) {
    SearchView curNav = (SearchView)helpBroker.getCurrentNavigatorView();
%>
<SCRIPT>
searchList = new SearchList("searchList", 16, "ccccff");
<jh:searchTOCItem searchView="<%= curNav %>" helpBroker="<%= helpBroker %>" query="<%= query %>" >
searchList.addNode("<%= name %>","<%= confidence %>","<%= hits %>","<%= helpID %>","<%= contentURL %>" );
</jh:searchTOCItem>
searchList.drawList();
searchList.refreshList();
searchList.select(0);
</SCRIPT>
<%
}
%>
</BODY>
</HTML>
