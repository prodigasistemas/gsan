<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ page import="gcom.gui.faturamento.bean.AnalisarImoveisReleituraHelper" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script>

function facilitador(objeto){
	if (objeto.id == "0" || objeto.id == undefined){
		objeto.id = "1";
		marcarTodos();
	}
	else{
		objeto.id = "0";
		desmarcarTodos();
	}
}

function validaForm(form){
	document.forms[0].action ='filtrarReleituraImoveisAction.do';
	document.forms[0].submit();
}

</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/filtrarReleituraImoveisAction"
	name="FiltrarAnalisarReleituraImoveisActionForm"
  	type="gcom.gui.faturamento.FiltrarAnalisarReleituraImoveisActionForm"
    method="post">	

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="130" valign="top" class="leftcoltext">
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
			<td width="625" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Im&oacute;veis com releitura  </td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>

				</tr>
			</table>

			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="7" height="23"><font style="font-size: 10px;"
						color="#000000" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Im&oacute;veis:</strong> </font>
					</td>
				</tr>
				<tr>
					<!--<td colspan="4" bgcolor="#3399FF"> -->
					<td colspan="7" bgcolor="#000000" height="2" valign="baseline"></td>
				</tr>
				<tr>
					<td>
					<table width="100%" align="center" bgcolor="#90c7fc" border="1"
						cellpadding="0" cellspacing="0">
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" bgcolor="#90c7fc">
								<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
									<td width="10%">
									<div align="center"><strong>Status</strong></div>
									</td>								
									<td width="10%">
									<div align="center"><strong>Localidade</strong></div>
									</td>
									<td width="10%">
									<div align="center"><strong>Setor</strong></div>
									</td>
									<td width="10%">
									<div align="center"><strong>Rota</strong></div>
									</td>
									<td width="10%">
									<div align="center"><strong>Quadra</strong></div>
									</td>

									<td width="50%">
									<div align="center"><strong>Im&oacute;vel</strong></div>
									</td>
									<td width="10%">
									<div align="center"><strong>Recebeu Mensagem</strong></div>
									</td>									
								</tr>
							</table>
							
							<div style="height: 300px;overflow: auto;">					
								<table width="100%" bgcolor="#90c7fc" cellpadding="0" cellspacing="0">
									<tr>
										<td>
											<logic:present name="colAnalisarImoveisReleituraHelper" scope="session">
												<%int cont = 0;%>
												<logic:iterate name="colAnalisarImoveisReleituraHelper"
													id="analisarImoveisReleituraHelper" scope="session">
												<%cont = cont + 1;
												  
												  if (cont % 2 == 0) {%>
													<tr bgcolor="#cbe5fe" class="styleFontePequena">
												<%} else {%>
													<tr bgcolor="#FFFFFF" class="styleFontePequena">
												<%}%>
														<td width="10%" align="center">
														
														<logic:equal name="analisarImoveisReleituraHelper" property="idSituacaoReleitura" value='<%=AnalisarImoveisReleituraHelper.RELEITURA_NAO_REALIZADA+""%>'>														
															<table>
																<tr>
																	<td width="33%" align="justify" style="background: red;">&nbsp;</td>
																</tr>
															</table>																											
														</logic:equal>
														
														<logic:equal name="analisarImoveisReleituraHelper" property="idSituacaoReleitura" value='<%=AnalisarImoveisReleituraHelper.RELEITURA_PENDENTE+""%>'>														
															<table>
																<tr>
																	<td width="33%" align="justify" style="background: yellow;">&nbsp;</td>
																</tr>
															</table>																											
														</logic:equal>
														
														<logic:equal name="analisarImoveisReleituraHelper" property="idSituacaoReleitura" value='<%=AnalisarImoveisReleituraHelper.RELEITURA_REALIZADA+""%>'>														
															<table>
																<tr>
																	<td width="33%" align="justify" style="background: green;">&nbsp;</td>
																</tr>
															</table>																											
														</logic:equal>
														
														</td>
														<td width="10%" align="center">${analisarImoveisReleituraHelper.idLocalidade}</td>									
														<td width="10%" align="center">${analisarImoveisReleituraHelper.codigoSetorComercial}</td>									
														<td width="10%" align="center">${analisarImoveisReleituraHelper.codigoRota}</td>									
														<td width="10%" align="center">${analisarImoveisReleituraHelper.idQuadra}</td>									
														<td width="40%" align="center" onmouseover="this.T_BGCOLOR='whitesmoke';this.T_LEFT=true;return escape( '${analisarImoveisReleituraHelper.hint}' ); ">${analisarImoveisReleituraHelper.matriculaImovel}</td>									
														<td width="10%" align="center">${analisarImoveisReleituraHelper.recebeuMensagem}</td>									
													</tr>															
												</logic:iterate>
											</logic:present>
										</td>
									</tr>
								</table>
							</div>
							</table>														
							</td>
						</tr>
						<tr>
							<td>
								<table>
									<tr>
										<td width="30%">&nbsp;</td>
										<td style="background: red;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
										<td>releitura não realizada</td>								
										<td style="background: yellow;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
										<td>releitura pendente</td>								
										<td style="background: green;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
										<td>releitura realizada</td>
									</tr>
								</table>
							</td>						
						</tr>
						
						<tr>
							<td><input name="button" type="button"
									class="bottonRightCol" value="Voltar Filtro"
									onclick="window.location.href='<html:rewrite page="/exibirFiltrarReleituraImoveisAction.do?menu=sim"/>'"
									align="left" style="width: 80px;">
								<input type="button" class="bottonRightCol" value="Atualizar"
									onClick="javascript:document.forms[0].submit();">	
									
								<div style="float: right;">
									<img onclick="javascript: toggleBox('demodiv',1);" style="cursor: pointer;"
									src="imagens/print.gif" alt="" width="28"
									height="26" />
									
									</div>
							</td>
						</tr>
					</table>
					<p>&nbsp;</p>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioReleituraImoveisAction.do"/>
	<%@ include file="/jsp/util/rodape.jsp"%>
	<%@ include file="/jsp/util/tooltip.jsp"%>
</html:form>
</body>
</html:html>
