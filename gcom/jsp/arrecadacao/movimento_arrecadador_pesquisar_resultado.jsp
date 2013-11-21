<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ page import="gcom.util.ConstantesSistema,java.util.Collection"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarMovimentoArrecadadorActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
<!-- Begin 
	function ValidarForm(){
		var formRed = "/gsan/inserirCategoriaFaixaConsumoTarifaAction.do";
			redirecionarSubmit(formRed);
	}
//End --> 	
</script>
</head>
<logic:equal name="testeInserir" value="false" scope="request">
	<body leftmargin="0" topmargin="0" onload="resizePageSemLink(640,600);">
</logic:equal>
<body onload="resizePageSemLink(790, 440);">
<html:form action="/pesquisarMovimentoArrecadadorAction"
	name="PesquisarMovimentoArrecadadorActionForm"
	onsubmit="return validatePesquisarMovimentoArrecadadorActionForm(this);"
	type="gcom.gui.arrecadacao.PesquisarMovimentoArrecadadorActionForm"
	method="post">

	<table width="750" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="750" valign="top" class="centercoltext">
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
					<td class="parabg">Pesquisa de Movimento de Arrecadadores</td>
					<td width="11"><img src="imagens/parahead_right.gif"
						editor="Webstyle4"
						moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws"
						border="0" /></td>

				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0" bgcolor="#90c7fc">
				<tr align="left" bgcolor="#90c7fc">
					<td width="8%">
					<div align="center"><strong>Banco</strong></div>
					</td>
					<td width="29%">
					<div align="center"><strong>Nome</strong> <strong>do Banco</strong></div>
					</td>
					<td width="8%">
					<div align="center"><strong>Remessa</strong></div>
					</td>
					<td width="7%">
					<div align="center"><strong>NSA</strong></div>
					</td>
					<td width="28%">
					<div align="center"><strong>Identifica&ccedil;&atilde;o do
					Servi&ccedil;o</strong></div>
					</td>
					<td width="20%">
					<div align="center"><strong>Data da Gera&ccedil;&atilde;o</strong></div>
					</td>
				</tr>

				<%String cor = "#cbe5fe";%>
				<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
				export="currentPageNumber=pageNumber;pageOffset"
				maxPageItems="10" items="${sessionScope.totalRegistros}">
					<pg:param name="pg" />
					<pg:param name="q" />
					<logic:present name="colecaoMovimentoArrecadador" scope="session">
						<logic:iterate name="colecaoMovimentoArrecadador" id="movimento">
							<pg:item>
								<%if (cor.equalsIgnoreCase("#FFFFFF")) {
				cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%} else {
				cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%}%>
									<td>
									<div align="center"><bean:write name="movimento"
										property="codigoBanco" /></div>
									</td>

									<td>
										<div>
											
											
												<logic:notEmpty	name="caminhoRetornoTelaPesquisaMovimentoArrecadador">
													<a
														href="javascript:enviarDadosSeisParametrosCaminhoRetorno('<bean:write name="caminhoRetornoTelaPesquisaMovimentoArrecadador"/>', '<bean:write name="movimento" property="id"/>', '<bean:write name="movimento" property="codigoBanco"/>', '<bean:write name="movimento" property="codigoRemessa"/>', '<bean:write name="movimento" property="descricaoIdentificacaoServico"/>', '<bean:write name="movimento" property="numeroSequencialArquivo"/>' ,'movimentoArrecadador');">
													<bean:write name="movimento" property="nomeBanco" /></a>
												</logic:notEmpty>
												
												<logic:empty name="caminhoRetornoTelaPesquisaMovimentoArrecadador">
													<a
													href="javascript:enviarDadosSeisParametros('<bean:write name="movimento" property="id"/>', '<bean:write name="movimento" property="codigoBanco"/>', '<bean:write name="movimento" property="codigoRemessa"/>', '<bean:write name="movimento" property="descricaoIdentificacaoServico"/>', '<bean:write name="movimento" property="numeroSequencialArquivo"/>', 'movimentoArrecadador');">
													<bean:write name="movimento" property="nomeBanco" /> </a>
												</logic:empty> 
												
											
										</div>
									</td>
									
									<td>
									<div><logic:equal name="movimento"
										property="codigoRemessa" value="1">
											ENVIO
										</logic:equal> <logic:equal name="movimento"
										property="codigoRemessa" value="2">
											RETORNO
										</logic:equal></div>
									</td>
									<td>
									<div align="center"><bean:write name="movimento"
										property="numeroSequencialArquivo" /></div>
									</td>
									<td>
									<div><bean:write name="movimento"
										property="descricaoIdentificacaoServico" /></div>
									</td>

									<td>
									<div align="center"><bean:write name="movimento"
										format="dd/MM/yyyy" property="dataGeracao" /></div>
									</td>
								</tr>
							</pg:item>
						</logic:iterate>
					</logic:present>
			
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>
					<div align="center"><strong><%@ include
					file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
					</td>
					</pg:pager>
				</tr>
				
				<tr>
				<td height="24"><input type="button" class="bottonRightCol"
					value="Voltar Pesquisa"
					onclick="window.location.href='<html:rewrite page="/exibirPesquisarMovimentoArrecadadorAction.do?objetoConsulta=1"/>'" /></td>
			</tr>
				
			</table>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>
