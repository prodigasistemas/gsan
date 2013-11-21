<!-- dados_tarifa_social.jsp puxa o FiltrarDadosTarefaSocialAction
10/12/2005
Feito por Felipe Vieira -->

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ImovelOutrosCriteriosActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<!-- Begin -->
<body>
<html:form action="/exibirFiltrarDadosTarifaSocialAction.do"
	name="ImovelOutrosCriteriosActionForm"
	type="gcom.gui.cadastro.imovel.ImovelOutrosCriteriosActionForm"
	method="post">

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
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="20"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Filtrar Dados da Tarifa Social</td>
					<td width="20"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</td>
			</tr>
			</table>

			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para filtrar dados da tarifa social, informe os
					par&acirc;metros abaixo:</td>
				</tr>
				<tr>
					<td height="18" colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong>Per&iacute;odo de Implanta&ccedil;&atilde;o: </strong></td>

					<td><html:text maxlength="10" property="implantacaoInicial"
						size="10" /><a
						href="javascript:abrirCalendario('ImovelOutrosCriteriosActionForm', 'implantacaoInicial')"><img
						border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
					a <html:text maxlength="10" property="implantacaoFinal" size="10" />
					<a
						href="javascript:abrirCalendario('ImovelOutrosCriteriosActionForm', 'implantacaoFinal')">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
					(dd/mm/aaaa)</td>
				</tr>
				<tr>
					<td><strong>Per&iacute;odo de Exclus&atilde;o: </strong></td>
					<td><html:text maxlength="10" property="periodoExclusaoInicial"
						size="10" /><a
						href="javascript:abrirCalendario('ImovelOutrosCriteriosActionForm', 'periodoExclusaoInicial')"><img
						border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
					a <html:text maxlength="10" property="periodoExclusaoFinal" size="10" />
					<a
						href="javascript:abrirCalendario('ImovelOutrosCriteriosActionForm', 'periodoExclusaoFinal')">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
					(dd/mm/aaaa)</td>
				</tr>
				<tr>
					<td><strong>Motivo de Exclus&atilde;o:</strong></td>

					<td><html:select property="tarifaSocialExclusaoMotivo">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoExclusaoMotivo"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Per&iacute;odo de Validade do Cart&atilde;o do Programa
					Social: </strong></td>
					<td><html:text maxlength="10" property="periodoValidadeCartaoInicial" size="10" /><a
						href="javascript:abrirCalendario('ImovelOutrosCriteriosActionForm', 'periodoValidadeCartaoInicial')">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
					a <html:text maxlength="10" property="periodoValidadeCartaoFinal"
						size="10" /> <a
						href="javascript:abrirCalendario('ImovelOutrosCriteriosActionForm', 'periodoValidadeCartaoFinal')">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
					(dd/mm/aaaa)</td>
				</tr>
				<tr>
					<td><strong>N&uacute;mero de Meses de Ades&atilde;o:</strong></td>
					<td><html:text maxlength="2" property="numeroMesesAdesao" size="2" />
				</tr>
				<tr>
					<td><strong>Tipo do Cart&atilde;o:</strong></td>
					<td><html:select property="tarifaSocialCartaoTipo">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoCartaoTipo"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Tipo de Renda:</strong></td>
					<td><html:select property="tarifaSocialRendaTipo">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoRendaTipo"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Renda Familiar:</strong></td>
					<td><html:text maxlength="7" property="rendaFamiliar" size="8" /></td>
				</tr>
				<tr>
					<td><strong>Consumo Companhia de Energia:</strong></td>

					<td><html:text maxlength="5" property="consumoCelpe" size="5" /></td>
				</tr>
				<tr>
					<td><strong>Per&iacute;odo de Recadastramento: </strong></td>

					<td><html:text maxlength="10"
						property="periodoRecadastramentoInicial" size="10" /><a
						href="javascript:abrirCalendario('ImovelOutrosCriteriosActionForm', 'periodoRecadastramentoInicial')"><img
						border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
					a <html:text maxlength="10" property="periodoRecadastramentoFinal"
						size="10" /> <a
						href="javascript:abrirCalendario('ImovelOutrosCriteriosActionForm', 'periodoRecadastramentoFinal')">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
					(dd/mm/aaaa)</td>
				</tr>
				<tr>
					<td><strong>N&uacute;mero de Recadastramentos: </strong></td>
					<td><html:text maxlength="2"
						property="numeroRecadastramentoInicial" size="2" /></td>
				</tr>
				<tr>
					<td>
					<td>
					<p>
					<td align="right"><input type="submit" name="filtrar"
						class="bottonRightCol" value="Filtrar">
					</p>
					</td>
					</td>
				</tr>
			</table>
			<td>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>