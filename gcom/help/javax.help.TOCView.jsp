<jsp:useBean id="helpBroker" class="javax.help.ServletHelpBroker" scope="session" />
<%@ page import="javax.help.TOCView, javax.help.Map.ID" %>
<%@ taglib uri="/WEB-INF/jhlib.tld" prefix="jh" %>
<HTML>
<STYLE type="text/css">
    .anchorStyle { text-decoration:none; color:black; font-family: Verdana, Geneva, Arial, Helvetica, sans-serif; font-size: 11px;}
    .anchorBoldStyle { text-decoration:none; color:black; font-weight: bold; font-family: Verdana, Geneva, Arial, Helvetica, sans-serif; font-size: 11px;}
</STYLE>



<BODY BGCOLOR=#EFEFEF>
<SCRIPT LANGUAGE="JavaScript1.3" src="tree.js">
</SCRIPT>

<SCRIPT>
tocTree = new Tree("tocTree", 16, "ccccff", true, false);
<% TOCView curNav = (TOCView)helpBroker.getCurrentNavigatorView(); %>
<jh:tocItem helpBroker="<%= helpBroker %>" tocView="<%= curNav %>" >
tocTree.addTreeNode("<%= parentID %>","<%= nodeID %>","<%= iconURL!=""?iconURL:"null" %>","<%= name %>","<%= helpID %>","<%= contentURL!=""?contentURL:"null" %>","<%= expansionType%>" );
</jh:tocItem>
tocTree.drawTree();
tocTree.refreshTree();
<%
ID id = helpBroker.getCurrentID();
if (id != null) {
%>
    tocTree.selectFromHelpID("<%= id.id%>");
<%
}
%>
</SCRIPT>
</BODY>


