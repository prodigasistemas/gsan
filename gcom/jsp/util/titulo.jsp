<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<title>
	<logic:present scope="application" name="tituloPagina">
		${applicationScope.tituloPagina}
	</logic:present>
	<logic:notPresent scope="application" name="tituloPagina">
		GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento
	</logic:notPresent>
</title>
