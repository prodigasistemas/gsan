<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<SCRIPT LANGUAGE="JavaScript">
	function verificarTelaSucessoRelatorio(telaSucesso){
		var form =  document.forms[0];
		if(telaSucesso == true){
			form.action = "/gsan/telaSucessoRelatorioAction.do";
			form.submit();
		}

	}

</SCRIPT>
<html:html>

<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

</head>

<body leftmargin="5" topmargin="5" onload="javascript:verificarTelaSucessoRelatorio(${requestScope.telaSucessoRelatorio});">
<%@ include file="/jsp/util/cabecalho.jsp"%>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="770" valign="top" class="centercoltext">
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
				<td class="parabg">Sucesso</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>
		<table width="100%" border="0">
			<tr>
				<td width="6%" align="left"><img
					src="<bean:message key="caminho.imagens"/>sucesso2.gif" /></td>
				<td width="43%" align="center">
				<div align="left"><span style="font-size:12px;font-weight: bold;"> <logic:present
					name="mensagemPaginaSucesso">
					<bean:write filter="false" name="mensagemPaginaSucesso" />
				</logic:present> </span></div>
				</td>
				<td width="48%" align="center"></td>
			</tr>
			<tr>
				<td colspan="3">&nbsp;</td>
			</tr>
			<logic:notPresent name = "desabilitaMenu" >
					<tr>
						<td rowspan="2" colspan="2" align="left">
							<logic:notPresent scope="session" name="origemGIS">
								<html:link
									forward="telaPrincipal">
									<strong>Menu Principal</strong>
								</html:link></td>
								<td width="48%" align="right"><a
									href="<bean:message key="caminho.sistema"/><bean:write name="caminhoFuncionalidade"/>">
								<strong> <logic:present name="labelPaginaSucesso">
									<bean:write name="labelPaginaSucesso" />
								</logic:present> </strong> </a>
							</logic:notPresent>
							<logic:present scope="session" name="origemGIS">
								<a onclick="window.close()" href="#"><strong>Fechar janela</strong></a>
							</logic:present>				
						</td>
					</tr>
			</logic:notPresent>
			
			<logic:present name = "desabilitaMenu" >
					<tr>
						<td rowspan="2" colspan="3" width="48%" align="right">
							<logic:notPresent scope="session" name="origemGIS">
								<a href="<bean:message key="caminho.sistema"/><bean:write name="caminhoFuncionalidade"/>">
								<strong> <logic:present name="labelPaginaSucesso">
									<bean:write name="labelPaginaSucesso" />
								</logic:present> </strong> </a>
							</logic:notPresent>
						</td>
					</tr>
			</logic:present>
				
				
			<logic:present name="caminhoAtualizarRegistro">
				<tr>
					<td width="48%" align="right">
						<logic:notPresent scope="session" name="origemGIS">
							<a href="<bean:message key="caminho.sistema"/><bean:write name="caminhoAtualizarRegistro"/>">
							<strong> <logic:present name="labelPaginaAtualizacao">
								<bean:write name="labelPaginaAtualizacao" />
							</logic:present> </strong> </a>
						</logic:notPresent>
					</td>
				</tr>
			</logic:present>
			<logic:present name="caminhoGerarOrdemServico">
				<tr>
					<td width="48%" align="right" colspan="3"><a
						href="<bean:write name="caminhoGerarOrdemServico"/>">
					<strong> <logic:present name="labelGerarOrdemServico">
						<bean:write name="labelGerarOrdemServico" />
					</logic:present> </strong> </a></td>
				</tr>
			</logic:present>
			<logic:present name="caminhoVoltar">
				<tr>
					<td width="48%" align="right" colspan="3"><a
						href="<bean:message key="caminho.sistema"/><bean:write name="caminhoVoltar"/>">
					<strong> <logic:present name="labelVoltar">
						<bean:write name="labelVoltar" />
					</logic:present> </strong> </a></td>
				</tr>
			</logic:present>
			
			<logic:present name="caminhoVoltarJavascript">
				<tr>
					<td width="48%" align="right" colspan="3"><a href="<bean:write name="caminhoVoltarJavascript"/>">
					<strong> <logic:present name="labelVoltarJavascript">
						<bean:write name="labelVoltarJavascript" />
					</logic:present> </strong> </a></td>
				</tr>
			</logic:present>
			
			
			<logic:present name="caminhoRelatorio">
				
				<tr>
					<td width="48%" align="right" colspan="3">
					<a href="javascript:toggleBox('demodiv',1);">
					<img border="0" src="<bean:message key="caminho.imagens"/>print.gif"/></a></td>
				</tr>
				
			</logic:present>
			
			<logic:present name="caminhoRelatorioLink1">
				
				<tr>
					<td width="48%" align="right" colspan="3">
					<a
						href="<bean:message key="caminho.sistema"/><bean:write name="caminhoRelatorioLink1"/>">
					<strong><bean:write name="mensagemRelatorioLink1" /></strong></a></td>
				</tr>
				
			</logic:present>
			
			<logic:present name="caminhoRelatorioLink2">
				
				<tr>
					<td width="48%" align="right" colspan="3">
					<a
						href="<bean:message key="caminho.sistema"/><bean:write name="caminhoRelatorioLink2"/>">
					<strong><bean:write name="mensagemRelatorioLink2" /></strong></a></td>
				</tr>
				
			</logic:present>
			

		</table>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>
<form>
<jsp:include
					page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=${requestScope.caminhoRelatorio}" />
					</form>
<%@ include file="/jsp/util/rodape.jsp"%>

</body>

</html:html>
