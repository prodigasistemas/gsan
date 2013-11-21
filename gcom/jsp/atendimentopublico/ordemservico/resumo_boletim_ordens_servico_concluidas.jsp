<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false" 
	formName="GerarBoletimOrdensServicoConcluidasActionForm" staticJavascript="false" dynamicJavascript="true"/>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">

	function encerrarBoletim() {
		var form = document.forms[0];
		
		if (confirm("Confirma encerramento?")) {
			submeterFormPadrao(form);
		}else {
			return;
		}
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="window.focus();javascript:setarFoco('${requestScope.nomeCampo}');" >

	<html:form action="/exibirResumoBoletimOrdensServicoConcluidasAction?encerrarBoletim=sim" method="post"
		name="GerarBoletimOrdensServicoConcluidasActionForm"
		type="gcom.gui.atendimentopublico.ordemservico.GerarBoletimOrdensServicoConcluidasActionForm">
		
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
				<td width="600" valign="top" class="centercoltext">
					<table height="100%">
						<tr>
							<td></td>
						</tr>
					</table>
		
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td width="11"><img border="0"
								src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
							<td class="parabg">Resumo das Ordens de Servi&ccedil;o Conclu&iacute;das</td>
							<td width="11"><img border="0"
								src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
						</tr>
					</table>
					<p>&nbsp;</p>
					
					<table width="100%" border="0">
						<tr>
							<td colspan="2">
								<table border="0" width="100%" cellpadding="1" cellspacing="1">
									<tr>
										<td><strong>Local:&nbsp;</strong></td>
										<td align="left"><html:text property="nomeLocalidade" readonly="true"
											style="background-color:#EFEFEF; border:0;" size="40" />
										</td>
										<td align="right"><strong>Refer&ecirc;ncia:&nbsp;</strong></td>
										<td align="left"><html:text property="anoMesReferenciaEncerramento" readonly="true"
											style="background-color:#EFEFEF; border:0;" size="5" />
										</td>
									</tr>
									<tr>
										<td><strong>Firma:&nbsp;</strong></td>
										<td><html:text property="nomeFirma" readonly="true"
											style="background-color:#EFEFEF; border:0;" size="40" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					
					<table width="100%" border="0">
						<tr>
							<td>
								<table border="0" bgcolor="#99CCFF" width="100%">
									<tr>
										<td><strong>Quantidade de Ordens Conclu&iacute;das no m&ecirc;s:</strong></td>
									</tr>
									<tr bgcolor="#cbe5fe">
										<td width="100%" align="center">
											<table width="100%" border="0">
												<tr>
													<td bordercolor="#000000" width="120"><strong>N&atilde;o Fiscalizadas:&nbsp;</strong></td>
													<td align="left"><html:text property="totalNaoFiscalizadas" readonly="true"
														style="background-color:#EFEFEF; border:0; text-align:right;" size="6" />
													</td>
													<td width="120" align="right"><strong>Aprovadas:&nbsp;</strong></td>
													<td align="left"><html:text property="totalAprovadas" readonly="true"
														style="background-color:#EFEFEF; border:0; text-align:right;" size="6" />
													</td>
													<td width="120" align="right"><strong>Total:&nbsp;</strong></td>
													<td align="left"><html:text property="totalNaoFiscalizadasAprovadas" readonly="true"
														style="background-color:#EFEFEF; border:0; text-align:right;" size="6" />
													</td>
												</tr>
												<tr bgcolor="#cbe5fe">
													<td><strong>Reprovadas:&nbsp;</strong></td>
													<td colspan="5" align="left"><html:text property="totalReprovadas" readonly="true"
														style="background-color:#EFEFEF; border:0; text-align:right;" size="6" />
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
						
					<table width="100%" border="0">
						<tr>
							<td>
								<table border="0" bgcolor="#99CCFF" width="100%">
									<tr>
										<td><strong>Quantidade de Ordens Conclu&iacute;das em Meses Anteriores e Aprovadas no M&ecirc;s:</strong></td>
									</tr>
									<tr bgcolor="#cbe5fe">
										<td width="100%" align="center">
											<table width="100%" border="0">
												<tr>
													<td bordercolor="#000000" width="120">
														<html:text property="totalEncerradasMesesAnterioresAprovadasMes" readonly="true"
														style="background-color:#EFEFEF; border:0; text-align:right;" size="6" />
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					
					<table width="100%" border="0">
						<tr>
							<td>
								<table border="0" bgcolor="#99CCFF" width="100%">
									<tr>
										<td><strong>Total do Boletim (Aprovadas / N&atilde;o Fiscalizadas):</strong></td>
									</tr>
									<tr bgcolor="#cbe5fe">
										<td width="100%" align="center">
											<table width="100%" border="0">
												<tr>
													<td bordercolor="#000000" width="120">
														<html:text property="totalBoletim" readonly="true"
														style="background-color:#EFEFEF; border:0; text-align:right;" size="6" />
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					
					<table width="100%" border="0">
						<tr>
							<td>
								<table border="0" bgcolor="#99CCFF" width="100%">
									<tr>
										<td><strong>Resumo das Ordens Aprovadas / N&atilde;o Fiscalizadas por Tipo de Servi&ccedil;o e Local de Instala&ccedil;&atilde;o:</strong></td>
									</tr>
									<tr bgcolor="#cbe5fe">
										<td width="100%" align="center">
											<table width="100%" border="0">
												<tr>
													<td bordercolor="#000000" colspan="6"><strong>Instala&ccedil;&atilde;o:</strong></td>
												</tr>
												<tr>
													<td bordercolor="#000000" width="40"><strong>Muro:</strong></td>
													<td align="left"><html:text property="totalHidrometrosInstaladosMuro" readonly="true"
														style="background-color:#EFEFEF; border:0; text-align:right;" size="6" />
													</td>
													<td width="120" align="right"><strong>Cal&ccedil;ada:&nbsp;</strong></td>
													<td align="left"><html:text property="totalHidrometrosInstaladosCalcada" readonly="true"
														style="background-color:#EFEFEF; border:0; text-align:right;" size="6" />
													</td>
													<td width="120" align="right"><strong>Jardim:&nbsp;</strong></td>
													<td align="left"><html:text property="totalHidrometrosInstaladosJardim" readonly="true"
														style="background-color:#EFEFEF; border:0; text-align:right;" size="6" />
													</td>
												</tr>
												<tr>
													<td bordercolor="#000000" colspan="6"><strong>Substitui&ccedil;&atilde;o:</strong></td>
												</tr>
												<tr>
													<td bordercolor="#000000" width="40"><strong>S&iacute;mples:</strong></td>
													<td align="left"><html:text property="totalHidrometrosSubstituidosSemTrocaCaixa" readonly="true"
														style="background-color:#EFEFEF; border:0; text-align:right;" size="6" />
													</td>
													<td width="120" align="right"><strong>Muro:&nbsp;</strong></td>
													<td align="left"><html:text property="totalHidrometrosSubstituidosComTrocaCaixaMuro" readonly="true"
														style="background-color:#EFEFEF; border:0; text-align:right;" size="6" />
													</td>
													<td width="120" align="right"><strong>Cal&ccedil;ada:&nbsp;</strong></td>
													<td align="left"><html:text property="totalHidrometrosSubstituidosComTrocaCaixaCalcada" readonly="true"
														style="background-color:#EFEFEF; border:0; text-align:right;" size="6" />
													</td>
												</tr>
												<tr>
													<td bordercolor="#000000" width="40"><strong>Troca de Registro:</strong></td>
													<td align="left"><html:text property="totalTrocaRegistro" readonly="true"
														style="background-color:#EFEFEF; border:0; text-align:right;" size="6" />
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					
					<table width="100%" border="0">
						<tr>
							<td valign="top" width="70">
								<input type="button"
									name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
									onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</td>
							<td valign="top" align="left">
								<input type="button"
									name="ButtonVoltar" class="bottonRightCol" value="Voltar"
									onClick="javascript:history.back();">
							</td>
							<td align="right" valign="top">
								<input type="button"
									name="ButtonFiltrar" class="bottonRightCol" value="Encerrar Boletim"
									onClick="javascript:encerrarBoletim();">
							</td>
						</tr>
					</table>
					<br>
				</td>
			</tr>
		</table>
		<%@ include file="/jsp/util/rodape.jsp"%>
	</html:form>
</body>
</html:html>