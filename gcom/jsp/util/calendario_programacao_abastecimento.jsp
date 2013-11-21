<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<html>
<head>

<title>Calendário Programação Abastecimento - SGCQ</title>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>CalendarioProgramacaoAbastecimento.js"></script>

<SCRIPT TYPE="text/javascript" LANGUAGE="JavaScript"><!--
		document.write(CalendarProgramacaoAbastecimento(${requestScope.mes},${requestScope.ano},'${requestScope.situacoes}'));
	//--></SCRIPT>

</head>
<BODY BGCOLOR="#90c7fc" TOPMARGIN=2 LEFTMARGIN=5>
</body>

</html>



