<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

</head>

<body leftmargin="5" topmargin="5">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="115" valign="top" class="leftcoltext">
				<div align="center">
					<p align="left">&nbsp;</p>
					<p align="left">&nbsp;</p>
					<p align="left">&nbsp;</p>
					<%@ include file="/jsp/util/informacoes_usuario.jsp"%>
					<p align="left">&nbsp;</p>
					<p align="left">&nbsp;</p>
					<p align="left">&nbsp;</p>
					<p align="left">&nbsp;</p>
					<p align="left">&nbsp;</p>
					<p align="left">&nbsp;</p>
					<p align="left">&nbsp;</p>
					<p align="left">&nbsp;</p>
					<p align="left">&nbsp;</p>
					<p align="left">&nbsp;</p>
					<p align="left">&nbsp;</p>
					<p align="left">&nbsp;</p>
					<%@ include file="/jsp/util/mensagens.jsp"%>
					<p align="left">&nbsp;</p>
					<p align="left">&nbsp;</p>
					<p align="left">&nbsp;</p>
					<p align="left">&nbsp;</p>
					<p align="left">&nbsp;</p>
					<p align="left">&nbsp;</p>
					<p align="left">&nbsp;</p>
				</div>
			</td>
			
			<td valign="top" class="centercoltext">
			
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
						
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Pesquisa Observações do Registro de Atendimento</td>
	
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<table width="100%" cellpadding="0" cellspacing="0">
				
				<tr>
					<td height="10" colspan="2"></td>
				</tr>
				
		   		<tr bgcolor="#cbe5fe">
	       			<td height="18" >
	       				<div align="center">
	       					<span class="style2"><b>Observações</b></span>
	       				</div>
	       			</td>
	      		</tr>

				<tr>
					<td>
					<table width="100%" bgcolor="#99CCFF">
	
						<%--Esquema de paginação--%>
	
						<logic:present name="colecaoRA" scope="request">
	
				        	<logic:iterate name="colecaoRA" id="registroAtendimento">

								<tr bordercolor="#79bbfd">
									<td align="left" bgcolor="#79bbfd">
										
										
										<b>
										Número do RA : 
										<bean:write name="registroAtendimento" property="id" /> -  
										<fmt:formatDate value="${registroAtendimento.registroAtendimento}" 
											pattern="dd/MM/yyyy" />
										</b>
									</td>
								</tr>
								<tr bordercolor="#000000">
									<td bgcolor="#90c7fc">
										<html:textarea property="observacao" 
											readonly="true"
											value="${registroAtendimento.observacao}" 
											style="background-color:#EFEFEF; border:0;" 
											cols="70" 
											rows="3"/>
											

									</td>
								</tr>
							</logic:iterate>
						</logic:present>
					</table>
	
					</td>
				</tr>
		</table>

		<table width="100%" border="0">

			<tr>
				<td height="24">
					<input type="button" 
						class="bottonRightCol"
						value="Voltar Pesquisa"
						onclick="window.location.href='/gsan/exibirPesquisarObservacaoRegistroAtendimentoAction.do?menu=sim'"/>
				</td>
			</tr>
		</table>
		
	</tr>

</table>

</body>

</html:html>
