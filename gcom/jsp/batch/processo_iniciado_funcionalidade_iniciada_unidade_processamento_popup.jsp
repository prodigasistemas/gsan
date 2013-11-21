<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Refresh" Content="300">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

</head>

<body leftmargin="0" topmargin="0">

<%
// Página editada por Vinicius Medeiros


// Calcula a porcentagem dos processos
int vl1=Integer.parseInt(request.getAttribute("tamanhoColecaoFinalizada").toString());
int vl2=Integer.parseInt(request.getAttribute("tamanhoColecaoInacabada").toString());
int somaTotal=vl1+vl2;

int porcentagem = 0;

	porcentagem = (vl1 * 100)/somaTotal;
%>

<table width="470" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="100%" height="346" valign="top" class="centercoltext">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">

			<tr>
				<td width="11"><img
					src="<bean:message key="caminho.imagens"/>parahead_left.gif"
					border="0" /></td>
				<td class="parabg">Processo Iniciado: Unidades de Processamento</td>

				<td width="11"><img
					src="<bean:message key="caminho.imagens"/>parahead_right.gif"
					border="0" /></td>
			</tr>
		</table>


		<p>&nbsp;</p>
		<p><strong>${requestScope.descricao}</strong></p>
		<p>&nbsp;</p>
			

		<table width="99%" height="25" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" border="0" bordercolor="#cbe5fe" bordercollapse="collapse" borderstyle="dashed">
			<tr valign="center">
				<td width="<%=porcentagem%>%" align="center" valign="top" background="imagens/loadingbar.jpg" ><strong></strong></td>
				<td width="*">&nbsp;</td>			
			</tr>
		</table>
		<table border="0" width="100%"> 
			<tr>
				<td align="center">
					<strong><%=porcentagem%>% Processados
					</strong>
				</td>
			</tr>
		</table>
		<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
				<td width="37%" height="112">
				<div align="center">
		
					<table width="100%" bgcolor="#99CCFF" border="0">
						<!--header da tabela interna -->
						<tr bordercolor="#FFFFFF" bgcolor="#99CCFF">
							<td width="53%" colspan="2">
								<div align="center"><strong>Unidade:
									${requestScope.unidadeProcessamento}</strong></div>
							</td>
						</tr>
						
						<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">
						<th>
							<table>
								<tr>
									<td height="17" bgcolor="#FFFFFF">
										<strong>
											PROCESSADAS: ${requestScope.tamanhoColecaoFinalizada}
										</strong> 
										<input class="bottonRightCol" type="button" value="Atualizar" onclick="javascript:window.location.href=window.location.href"/>
									
									</td>
								</tr>							
							</table>
						</th>
						<th>
							<table>
								<tr>
									<td height="17" bgcolor="#FFFFFF">
										<strong>
											A PROCESSAR: ${requestScope.tamanhoColecaoInacabada}
										</strong> 
										<input class="bottonRightCol" type="button" value="Atualizar" onclick="javascript:window.location.href=window.location.href"/>
									</td>
								</tr>							
							</table>
						</th>
						</tr>
						<tr>
							<th valign="top">
								<table width="100%">
											<%int cont = 0;%>
											<logic:iterate name="colecaoFinalizada" id="unidadeIniciada">
												<%cont = cont + 1;
												if (cont % 2 == 0) {%>
													<tr bgcolor="#cbe5fe">
												<%} else {%>
													<tr bgcolor="#FFFFFF">
												<%}%>
								
												<td height="17" bgcolor="#cbe5fe" width="100%">
													${unidadeIniciada.codigoRealUnidadeProcessamento}
												</td>
											</logic:iterate>
											
								</table>
							</th>		
							<th valign="top">
								<table border="0" width="100%">
									<%cont = 0;%>
									<logic:iterate name="colecaoInacabada" id="idUnidadeIniciada">
									<%cont = cont + 1;

									if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
									<%} else {%>
										<tr bgcolor="#FFFFFF">
									<%}%>

										<td height="17" bgcolor="#cbe5fe" width="100%">
											${idUnidadeIniciada}
										</td>
									</logic:iterate>
									
								</table>
							</th>		
						</tr>
					</table>
				</div>
				</td>
			</tr>
			</table>
		</td>
	</tr>
</table>


<p>&nbsp;</p>


</body>
</html:html>
