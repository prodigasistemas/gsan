<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta name="generator" content="Web page layout created by Xara Webstyle 4 - http://www.xara.com/webstyle/" />
<link rel="stylesheet" href="<bean:message key='caminho.css'/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key='caminho.js'/>util.js" ></script>
</head>

<body leftmargin="0" topmargin="0" onLoad="javascript:window.focus();">
<table width="670" border="0" cellpadding="0" cellspacing="5">
	<tr>
		<td width="660" valign="top" class="centercoltext">
			<table height="100%">
				<tr><td></td></tr>
			</table>
			<table width="680" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11"><img src="<bean:message key='caminho.imagens'/>parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" /></td>
					<td class="parabg">Rotas Não Habilitadas</td>
					<td width="11"><img src="<bean:message key='caminho.imagens'/>parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			
			<table width="660" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr> 
								<td> 
									<table width="100%" bgcolor="#99CCFF">
										<tr bgcolor="#99CCFF"> 
											<td width="11%"><div align="center"><strong>Grupo</strong></div></td>
											<td width="9%"><div align="center"><strong>Ger&ecirc;ncia</strong></div></td>
											<td width="8%"><div align="center"><strong>Unidade Negócio</strong></div></td>
											<td width="26%"><div align="center"><strong>Localidade</strong></div></td>
											<td width="25%"><div align="center"><strong>Setor</strong></div></td>
											<td width="25%"><div align="center"><strong>Rota</strong></div></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>

						<logic:present name="colecaoRotasNaoHabilitadas" scope="request">
						<div style="width: 100%; height: 100; overflow: auto;">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr> 
								<td> 
									<%int cont = 0;%>
									<table width="100%" align="center" bgcolor="#99CCFF">
									<logic:iterate name="colecaoRotasNaoHabilitadas" id="rota">
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
										<%} else {%>
										<tr bgcolor="#FFFFFF">
										<%}%>
											<td width="11%" height="22" align="center">
												<bean:write name="rota" property="faturamentoGrupo.id"/>
											</td>
											<td width="9%" align="center">
												<logic:present name="rota" property="setorComercial">
													<bean:write name="rota" property="setorComercial.localidade.gerenciaRegional.nomeAbreviado"/>
												</logic:present>
												<logic:notPresent name="rota" property="setorComercial">&nbsp;</logic:notPresent>
											</td>
											<td width="9%" align="center">
												<logic:present name="rota" property="setorComercial">
													<bean:write name="rota" property="setorComercial.localidade.unidadeNegocio.nomeAbreviado"/>
												</logic:present>
												<logic:notPresent name="rota" property="setorComercial">&nbsp;</logic:notPresent>
											</td>

											<td width="26%" align="left">
												<logic:present name="rota" property="setorComercial">
													<bean:write name="rota" property="setorComercial.localidade.descricao"/>
												</logic:present>
												<logic:notPresent name="rota" property="setorComercial">&nbsp;</logic:notPresent>	
											</td>
											<td width="27%" align="center">
												<logic:present name="rota" property="setorComercial">
													<bean:write name="rota" property="setorComercial.codigo"/>
												</logic:present>
												<logic:notPresent name="rota" property="setorComercial">&nbsp;</logic:notPresent>
											</td>
											<td width="24%" align="center">
												<bean:write name="rota" property="codigo"/>
											</td>
										</tr>
									</logic:iterate>
									</table>
								</td>
				            </tr>
						</table>
						</div>
						</logic:present>
			        </td>
			    </tr>
		   </table>
		</td>
	</tr>
</table>
</body>
</html:html>