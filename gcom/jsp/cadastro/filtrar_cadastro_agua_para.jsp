<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="DadosRecadastramentoAguaParaActionForm" dynamicJavascript="true" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
<!-- 

-->
</script>
</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">
<html:form action="/filtrarRecadastramentoAguaParaAction.do" enctype="multipart/form-data" name="DadosRecadastramentoAguaParaActionForm" type="gcom.gui.cadastro.DadosRecadastramentoAguaParaActionForm" method="post">

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
			
			<td width="615" valign="top" class="centercoltext">
				<table height="100%">
				<tr>
					<td></td>
				</tr>
			    </table>
				<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
					<tr>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
						</td>
						<td class="parabg">Formularios Recadastramento Água Pará</td>
						<td width="11" valign="top">
							<img border="0" src="imagens/parahead_right.gif" />
						</td>
					</tr>
					</table>
			<p>&nbsp;</p>
			<table width="100%" cellspacing="0">
						<tr>
							<td align="center" width="100%">

								<table width="100%" border="0" bgcolor="#90c7fc">

									<tr bgcolor="#79bbfd" width="100%">
										<td height="18" colspan="6" align="center"><strong>Dados
												Recadastramento Água Pará</strong></td>
									</tr>

									<tr>

										<td bgcolor="#90c7fc" align="center" width="30%">
											<div align="center">
												<strong>Matricula Imovel</strong>
											</div>
										</td>

										<td bgcolor="#90c7fc" width="50%">
											<div align="center">
												<strong>Nome Cliente</strong>
											</div>
										</td>
                                        <td bgcolor="#90c7fc" width="20%">
											<div align="center">
												<strong>Situação</strong>
											</div>
										</td>
									</tr>

									<tr bordercolor="#000000">

										<logic:present
											name="colecaoConsultarRecadastramentoAguaParaHelper">
											<%
												int cont = 0;
											%>
											<logic:iterate
												name="colecaoConsultarRecadastramentoAguaParaHelper"
												id="consultarRecadastramentoAguaParaHelper">
												<%
													cont = cont + 1;
																if (cont % 2 == 0) {
												%>
												<tr bgcolor="#cbe5fe">
													<%
														} else {
													%>
													<tr bgcolor="#FFFFFF">
														<%
															}
														%>

														<td align="center">
														<a
														href="dadosRecadastramentoAguaParaAction.do?cpf=${consultarRecadastramentoAguaParaHelper.cpf};">
														${consultarRecadastramentoAguaParaHelper.idImovel}
														</a></td>

														<td align="center">${consultarRecadastramentoAguaParaHelper.nome}</td>
														<td align="center">${consultarRecadastramentoAguaParaHelper.situacao}</td>
													</tr>
											</logic:iterate>
										</logic:present>
									</tr>
								</table>
						</tr>
					</table>
			<table width="100%" border="0">
				<tr>
				<td height="24">
					<div align="right">
					<input name="Button" type="button" class="bottonRightCol"
						value="Voltar" onClick="window.location.href='/gsan/exibirRecadastramentoAguaPara.do'"></div>
					</td>
				</tr>
			</table>
	</td>
				<p>&nbsp;</p>
			
			
			
			<p>&nbsp;</p>
		</tr>
		<%@ include file="/jsp/util/rodape.jsp"%>
	</table>
	<p>&nbsp;</p>
</html:form>
</body>
</html:html>

