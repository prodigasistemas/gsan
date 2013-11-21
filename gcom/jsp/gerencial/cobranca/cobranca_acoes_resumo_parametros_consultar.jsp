<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.cadastro.localidade.GerenciaRegional" %>
<%@ page import="gcom.cadastro.localidade.UnidadeNegocio" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

	
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>


</head>
<body leftmargin="5" topmargin="5">
<html:form action="/consultarResumoAcaoCobrancaWizardAction" 
	name="ResumoAcaoCobrancaActionForm"
	type="gcom.gui.gerencial.cobranca.ResumoAcaoCobrancaActionForm"
	method="post">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_consulta.jsp?numeroPagina=1" />
	
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
					<tr><td></td></tr>
				</table>
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
						</td>
						<td class="parabg">Consultar Resumo das Ações de Cobrança</td>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
				<p>&nbsp;</p>
				<!--Fim Tabela Reference a P&aacute;gina&ccedil;&atilde;o da Tela de Processo-->
				<p>&nbsp;</p>
				<!-- include com a tabela Dados da Geração da Consulta --> <%@ include
				file="/jsp/cobranca/tabela_dados_resumo_acao_consulta.jsp"%> 
				<!-- fim include Dados da Geração da Consulta -->
	              <table width="100%" border="0">
	                <tr>
						<td colspan="2">
							<div align="right"><jsp:include
							page="/jsp/util/wizard/navegacao_botoes_wizard_consulta.jsp?numeroPagina=1" /></div>
						</td>
					</tr>
	              </table>
              
				<p>&nbsp;</p>
			</td>
		</tr>
	</table>				
	<%@ include file="/jsp/util/rodape.jsp"%>
	<%@ include file="/jsp/util/tooltip.jsp" %>
</html:form>
</body>
</html:html>