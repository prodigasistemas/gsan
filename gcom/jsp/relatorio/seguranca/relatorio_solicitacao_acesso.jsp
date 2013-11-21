<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@page
	import="gcom.seguranca.acesso.usuario.SolicitacaoAcesso"%>

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

</script>
</head>

<body leftmargin="5" topmargin="5" onload="">

<html:form action="/gerarRelatorioSolicitacaoAcessoAction" method="post" >

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
					<td class="parabg">Gerar Relatório de Solicitação de Acesso</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>

				</tr>
			</table>

			<table width="100%" cellpadding="0" cellspacing="0">
			
				<tr>
					<td colspan="2">Para gerar o relat&oacute;rio, informe os dados abaixo:</td>
				</tr>
				
				<tr>
					<td colspan="7" height="23"><font style="font-size: 10px;"
						color="#000000" face="Verdana, Arial, Helvetica, sans-serif"> 
						
						<strong>Solicição de Senha Cadastradas:</strong> </font>
					</td>
				</tr>
				<tr>
					<td colspan="7" bgcolor="#000000" height="2" valign="baseline"></td>
				</tr>
				<tr>
					<td>
					<table width="100%" align="center" bgcolor="#90c7fc" border="0"
						cellpadding="0" cellspacing="0">
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" bgcolor="#90c7fc">
								<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">

									<td width="10%">
										<div align="center"><strong>Data da Solicitação</strong></div>
									</td>
									
									<td width="10%">
										<div align="center"><strong>Data de Cadastro</strong></div>
									</td>
									
									<td width="18%">
										<div align="center"><strong>Matrícula/CPF</strong></div>
									</td>
									
									<td width="25%">
										<div align="center"><strong>Usuário</strong></div>
									</td>
									
									<td width="25%">
										<div align="center"><strong>Situação</strong></div>
									</td>

								</tr>
								<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
									export="currentPageNumber=pageNumber;pageOffset"
									maxPageItems="10" items="${sessionScope.totalRegistros}">
									<pg:param name="pg" />
									<pg:param name="q" />
									<%int cont = 0;%>
									<logic:iterate name="colecaoSolicitacaoAcesso"
										id="solicitacaoAcesso" type="SolicitacaoAcesso">
										<pg:item>
											<%cont = cont + 1;
												if (cont % 2 == 0) {%>
											<tr bgcolor="#cbe5fe">
												<%} else {%>
											<tr bgcolor="#FFFFFF">
												<%}%>

												<td width="10%" align="center">
													<bean:write name="solicitacaoAcesso" property="dataSolicitacao" format="dd/MM/yyyy" />
												</td>
												
												<td width="10%" align="center">
													<bean:write name="solicitacaoAcesso" property="dataCadastramento" format="dd/MM/yyyy" />
												</td>
												
												<td width="18%" align="center">
													<%if (solicitacaoAcesso.getFuncionario() != null) {%>
														<bean:write name="solicitacaoAcesso" property="funcionario.id"/>
													<%} else if (solicitacaoAcesso.getCpf() != null){%>
														<bean:write name="solicitacaoAcesso" property="cpf"/>
													<%}%>
												</td>
												
												<td width="25%">
													<div align="center"><bean:write name="solicitacaoAcesso" property="nomeUsuario"/></div>
												</td>
												
												<td width="25%"align="center">
													<bean:write name="solicitacaoAcesso" property="solicitacaoAcessoSituacao.descricao"/>
												</td>
												
											</tr>
										</pg:item>
									</logic:iterate>
							</table>
							<table width="100%" border="0">
								<tr>
									<td align="center"><strong><%@ include
										file="/jsp/util/indice_pager_novo.jsp"%></strong></td>
								</tr>
								</pg:pager>
								<tr>
									<td>
										<input name="button" type="button"
											   class="bottonRightCol" value="Voltar Filtro"
											   onclick="window.location.href='<html:rewrite page="/exibirFiltrarSolicitacaoAcessoAction.do?objeto=relatorio&limpar=S"/>'"
											   align="left" style="width: 80px;">
									</td>
									<td valign="top">
										<div align="right"><a href="javascript:toggleBox('demodiv',1);">
										<img border="0"	src="<bean:message key="caminho.imagens"/>print.gif" title="Imprimir Solicitação de Acesso" /> </a></div>
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
			</td>
		</tr>
	</table>
	<p>&nbsp;</p>
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioSolicitacaoAcessoAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
