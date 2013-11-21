<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%--@ page import="gcom.util.ConstantesSistema"--%>
<%@ page import="gcom.atendimentopublico.ordemservico.ServicoTipoReferencia"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">
	
	
	
	
	
</script>
</head>

<body leftmargin="0" topmargin="0"
	onload="window.focus();resizePageSemLink(600, 430);javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/exibirConsultarDescricaoMotivoAceiteRepavimentacaoPopupAction" 
	name="ConsultarDescricaoMotivoAceiteRepavimentacaoPopupActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.ConsultarDescricaoMotivoAceiteRepavimentacaoPopupActionForm"
	method="post">

	<table width="100%" border="0" cellspacing="5" cellpadding="0">

		<tr>

			<td width="100%" valign="top" class="centercoltext">

				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
	
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
						</td>
						<td class="parabg">Consultar Motivo do Aceite da Repavimentação</td>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
			
				<p>&nbsp;</p>

				<tr>
					<td>
						<table width="100%">
   							<tr>
								<td width="30%"><strong>Motivo:</strong></td>
								<td width="69%">
									<html:textarea
										property="motivo" 
										cols="60" 
										rows="10"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<table width="100%">
						<tr>
							<td align="right">
								<input type="button" name="ButtonFechar" class="bottonRightCol"
								value="Fechar" tabindex="8"	onclick="window.close();" />
							</td>
						</tr>
					</table>		
				</tr>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>