<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page
	import="gcom.util.Util, gcom.cobranca.CobrancaAcaoAtividadeComando"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<html:javascript staticJavascript="false"
	formName="ExibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm" dynamicJavascript="true" />
	
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript">
	
</script>

</head>

<body leftmargin="0" topmargin="0">

<form method="post">
<table width="700" border="0" cellpadding="0" cellspacing="5">
	<tr>
		<td width="690" valign="top" class="centercoltext">
		<table height="100%">
			<tr>
				<td></td>
			</tr>
		</table>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img src="imagens/parahead_left.gif"
					editor="Webstyle4"
					moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws"
					border="0" /></td>
				<td class="parabg">Consultar<strong> </strong>Dados do Comando de
				A&ccedil;&atilde;o de Cobran&ccedil;a do Cronograma<font size="-1">&nbsp;</font></td>
				<td width="11"><img src="imagens/parahead_right.gif"
					editor="Webstyle4"
					moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws"
					border="0" /></td>
			</tr>
		</table>
		<table width="100%" border="0">
			<tr>
				<td height="234">
				<table width="100%" border="0">
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
					<tr>
						<td height="24"><strong><strong>Grupo de Cobran&ccedil;a:</strong></strong></td>
						<td colspan="3"><strong><strong><strong> 
						<html:text maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm"
					property="grupoCobranca" size="30"/> 
					</strong> </strong></strong></td>
					</tr>
					<tr>
						<td height="24"><strong>Refer&ecirc;ncia da Cobran&ccedil;a:</strong></td>
						<td colspan="3"><strong><strong><strong><strong> 
						<html:text maxlength="7" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm"
					property="referenciaCobranca" size="7"/> 
						</strong></strong> </strong></strong></td>
					</tr>
					<tr>
						<td width="31%" height="24"><strong>A&ccedil;&atilde;o de
						Cobran&ccedil;a:</strong></td>
						<td width="69%" colspan="3"><strong><strong> 
						<html:text maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm"
					property="acaoCobranca" size="30"/> 
					<strong> </strong> </strong></strong></td>
					</tr>
					<tr>
						<td height="24"><strong>Atividade de Cobran&ccedil;a:</strong></td>
						<td colspan="3"><strong><strong> 
						<html:text maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm"
					property="atividadeCobranca" size="30"/> 
						</strong></strong></td>
					</tr>
					<tr>
						<td height="24" colspan="4">
						<hr>
						</td>
					</tr>
					<tr>
						<td height="24"><strong>Data Prevista do Cronograma:</strong></td>
						<td colspan="3"><strong><strong> 
						<html:text maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm"
					property="dataPrevistaCronograma" size="10"/> 
						<strong> </strong> </strong></strong></td>
					</tr>
					<tr>
						<td height="24"><strong>Data e Hora do Comando:</strong></td>
						<td colspan="3"><strong><strong> 
												<html:text maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm"
					property="dataComando" size="10"/> 
							<strong> 
						<html:text maxlength="8" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm"
					property="horaComando" size="8"/> 
							</strong> </strong></strong></td>
					</tr>
					<tr>
						<td height="24"><strong>Data e Hora de Realiza&ccedil;&atilde;o:</strong></td>
						<td colspan="3"><strong><strong> 
						<html:text maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm"
					property="dataRealizacao" size="10"/> 						
							<strong> 
						<html:text maxlength="8" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm"
					property="horaRealizacao" size="8"/> 
							</strong> </strong></strong></td>
					</tr>
					<tr>
						<td height="24" colspan="4">
						<hr>
						</td>
					</tr>
					<tr>
						<td height="24"><strong> Valor dos Documentos:</strong></td>
						<td colspan="3"><strong><strong> 
						<html:text maxlength="14" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm"
					property="valorDocumentos" size="14"/> 						
						 </strong></strong></td>
					</tr>
					<tr>
						<td height="24"><strong> Quantidade de Documentos:</strong></td>
						<td colspan="3"><strong><strong> 
						<html:text maxlength="11" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm"
					property="quantidadeDocumentos" size="11"/> 						
						 </strong></strong></td>
					</tr>
					<tr>
						<td height="24"><strong> Quantidade de Itens dos Documentos:</strong></td>
						<td colspan="3"><strong><strong>
						<html:text maxlength="11" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm"
					property="quantidadeItensDocumentos" size="11"/> 						
						 </strong></strong></td>
					</tr>
					<tr>
						<td height="24" colspan="4">
						<hr>
						</td>
					</tr>
					<tr>
						<td height="24"><strong>Situa&ccedil;&atilde;o do Cronograma: </strong></td>
						<td colspan="3"><strong><strong>
						<html:text maxlength="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm"
					property="situacaoCronograma" size="20"/> 						
						 </strong></strong></td>
					</tr>
					<tr>
						<td height="24"><strong>Situa&ccedil;&atilde;o do Comando: </strong></td>
						<td colspan="3"><strong><strong>
						<html:text maxlength="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm"
					property="situacaoComando" size="20"/> 						
						 </strong></strong></td>
					</tr>
					<tr>
						<td height="24"><strong> </strong></td>
						<td colspan="3">&nbsp;</td>
					</tr>
					
					<tr>
					    <logic:present name="emitir">
						<td height="27">
						<div align="left"><input name="Button2" type="button"
							class="bottonRightCol" value="Emitir Protocolo"
							onclick="window.location.href='<html:rewrite page="/gerarRelatorioEmitirProtocoloDocumentoCobranca.do"/>'"/>							
						</div> 
						</td>
						<td height="27"> 
							<input name="Button2" type="button" class="bottonRightCol" value="Cancelar Documentos"
							onclick="if (confirm('Confirma remoção?')) window.location.href='<html:rewrite page="/cancelarDocumentosCobrancaAction.do?idCobrancaAcaoAtividadeCronograma=${cobrancaAcaoAtividadeCronograma.id}"/>'"/>
						</td>
						</logic:present>
						<logic:notPresent name="emitir">
						<td height="27">
						<div align="left"><input name="Button2" type="button"
							class="bottonRightCol" value="Emitir Protocolo" disabled="true"></div>
						</td>
						<td height="27"> 
							<input name="Button2" type="button" class="bottonRightCol" value="Cancelar Documentos"
							onclick="if (confirm('Confirma remoção?')) window.location.href='<html:rewrite page="/cancelarDocumentosCobrancaAction.do?idCobrancaAcaoAtividadeCronograma=${cobrancaAcaoAtividadeCronograma.id}"/>'"/>
						</td>
						</logic:notPresent>
						<td height="27" colspan="3">
						<logic:notPresent name="permissaoEmitir">
						  <input name="Button2" type="button"
							class="bottonRightCol" value="Emitir Documento de Cobrança" disabled="true"
							onclick="window.location.href='<html:rewrite page="/gerarRelatorioNotificacaoDebitoAction.do?idCobrancaAcaoAtividadeCronograma=${cobrancaAcaoAtividadeCronograma.id}"/>'"/>
						</logic:notPresent>
						<logic:present name="permissaoEmitir"><!--
						  <input name="Button2" type="button"
							class="bottonRightCol" value="Emitir Documento de Cobrança"
							onclick="window.location.href='<html:rewrite page="/gerarRelatorioNotificacaoDebitoAction.do?idCobrancaAcaoAtividadeCronograma=${cobrancaAcaoAtividadeCronograma.id}"/>'"/>
						-->
						 <input name="Button2" type="button"
							class="bottonRightCol" value="Emitir Documento de Cobrança"
							onclick="window.location.href='<html:rewrite page="/gerarRelatorioComandoDocumentoCobrancaAction.do?idCobrancaAcaoAtividadeCronograma=${cobrancaAcaoAtividadeCronograma.id}"/>'"/></logic:present>
						<div align="right"><input name="Button2" type="button"
							class="bottonRightCol" value="Fechar"
							onClick="javascript:window.close();"></div>
						</td>
					</tr>					
				</table>
				</td>
			</tr>
		</table>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>
</form>
</body>
</html:html>
