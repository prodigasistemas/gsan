<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="MonitorarLeituraMobileActionForm" dynamicJavascript="true" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function controlarDivConsumos( div ){
  if ( div.style.display == 'block' ){
	div.style.display = 'none';
  } else {
  	div.style.display = 'block';
  }

}

</script>

</head>

<body leftmargin="5" topmargin="5" >
	<table width="300"  border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="100%" valign="top" class="centercoltext">
			<table height="100%"><tr><td></td></tr></table>
			<p>&nbsp;</p>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Comunicados de Alto Consumo</td>
					<td width="11" valign="top"><img border="0"src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>

			<p>&nbsp;</p>
			<table width="100%" border="0">
						
				<tr>
				<td colspan="4">		
				<table width="100%" align="center" bgcolor="#90c7fc" border="0"
					cellpadding="0" cellspacing="0">
					<tr bgcolor="#cbe5fe">
						<td width="100%" align="center">
						<div style="height:250px;overflow:auto">
						<table>	
							<tr bordercolor="#000000" bgcolor="#90c7fc">
							
								<c:if test="${sessionScope.temPermissao!=null && sessionScope.temPermissao}" >												
									<td bgcolor="#90c7fc">
										&nbsp;
									</td>
								</c:if>
								<td width="20%" bgcolor="#90c7fc">
									<div align="center"><strong>Referência</strong></div>
								</td>
								
								<td width="10%" bgcolor="#90c7fc">
									<div align="center"><strong>Data da geração</strong></div>
								</td>
								
								<%int cont = 0;%>
								
								<logic:iterate name="comunicados" id="helper" scope="session">

								<%cont = cont + 1; if (cont % 2 == 0) {%>
								
									<tr bgcolor="#cbe5fe">
										<%} else {%>
									<tr bgcolor="#FFFFFF">
										<%}%>
										
										<c:if test="${sessionScope.temPermissao!=null && sessionScope.temPermissao}" >												
											<td>
						                        <img border="0" src="imagens/nolines_plus.gif" onclick="javascript:controlarDivConsumos( document.getElementById('divConsumos${helper.idImovel}') );" />
											</td>
											
										</c:if>

										<td align="center" onmouseover="this.T_BGCOLOR='whitesmoke';this.T_LEFT=true;return escape( '${helper.referenciaFormatada}' ); ">
											${helper.referenciaFormatada}
										</td>									
										
										<td align="center">
											${helper.geracao}
										</td>
										
									</tr>											
									
								</logic:iterate>
							</tr>
						</table>
						</div>		
						</td>
				  </tr>
				  			  
				</table>
			
				<tr>
					<td align="left">
						<input type="button"
							onclick="window.close()" class="bottonRightCol" value="Fechar"
							style="width: 70px;">
					</td>
				</tr>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</body>
</html:html>

