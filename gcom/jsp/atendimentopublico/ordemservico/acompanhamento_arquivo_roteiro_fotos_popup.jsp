<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="gcom.atendimentopublico.ordemservico.OrdemServicoFoto"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body>

<div id="gallery">
    <ul>
    	<logic:iterate name="colecaoFotoOS" id="osFoto" type="gcom.atendimentopublico.ordemservico.OrdemServicoFoto">
			<li>
				<a href='/gsan/jsp/atendimentopublico/ordemservico/ordem_servico_foto.jsp?idFoto=<bean:write name="osFoto" property="id"/>'
					title='<bean:write name="osFoto" property="descricaoFoto"/>'>
					<img width="72px" height="72px" 
						src="/gsan/jsp/atendimentopublico/ordemservico/ordem_servico_foto.jsp?idFoto=<bean:write name="osFoto" property="id"/>"/>
				</a>
			</li>
		</logic:iterate>
    </ul>
</div>
</body>
</html>