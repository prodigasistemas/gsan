<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript">
<!-- Begin -->
	
	function voltar(){
        var form = document.forms[0];
	      	form.action = '/gsan/exibirPesquisarComandoAcaoCobrancaAction.do';
    	    form.submit();		
	}
	
	function visualizarParametros(idCobrancaAcaoAtividadeComando){
        var form = document.forms[0];
      	form.action = 'exibirResultadoPesquisaComandoAcaoCobrancaParametros.do?idCobrancaAcaoAtividadeComando='+idCobrancaAcaoAtividadeComando;
        form.submit();		
	}

	function visualizarCriterios(idCobrancaAcaoAtividadeComando){
        var form = document.forms[0];
      	form.action = 'exibirResultadoPesquisaComandoAcaoCobrancaCriteriosAction.do?idCobrancaAcaoAtividadeComando='+idCobrancaAcaoAtividadeComando;
        form.submit();		
	}

	
</script>

</head>
<body leftmargin="5" topmargin="5" onload="resizePageSemLink(800,500);">
<form method="post">
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
				<td width="11"><img src="imagens/parahead_left.gif"
					editor="Webstyle4" border="0" /></td>
				<td class="parabg">Atividades Eventuais de Cobran&ccedil;a</td>
				<td width="11"><img src="imagens/parahead_right.gif"
					editor="Webstyle4" border="0" /></td>
			</tr>
		</table>
		<p>&nbsp;</p>

		<table width="100%" border="0" bgcolor="#79bbfd" align="center"
			cellpadding="0" cellspacing="0">
			<!--header da tabela interna -->
			<tr bgcolor="#79bbfd" height="18">
				<td align="center" class="style11"><strong>Comsndos de Ação de Cobrança Eventuais</strong></td>
			</tr>
		</table>

		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="22" class="style1">
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td height="0">


						<table width="100%" bgcolor="#90c7fc">
							<tr bgcolor="#90c7fc">
								<td width="18%" bgcolor="#90c7fc">
								<div align="center"><strong>A&ccedil;&atilde;o</strong></div>
								</td>
								<td width="7%" bgcolor="#90c7fc">
								<div align="center"><strong>Atividade</strong></div>
								</td>
								<td width="14%" bgcolor="#90c7fc">
								<div align="center"><strong>Título</strong></div>
								</td>
								<td width="16%" bgcolor="#90c7fc">
								<div align="center"><strong>Data e Hora de Gera&ccedil;&atilde;o
								do Comando</strong></div>
								</td>
								<td width="16%" bgcolor="#90c7fc">
								<div align="center"><strong>Data e Hora de
								Execu&ccedil;&atilde;o do Comando</strong></div>
								</td>
								<td width="12%" bgcolor="#90c7fc">
								<div align="center"><strong>Usu&aacute;rio que Gerou o Comando</strong></div>
								</td>
								<td width="7%" bgcolor="#90c7fc">
								<div align="center"><strong>Qtde. Docmtos.</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Crit&eacute;rio</strong></div>
								</td>
							</tr>

							<%String cor = "#cbe5fe";%>

							<!--Fim Tabela Reference a Páginação da Tela de Processo-->
							<%--Esquema de paginação--%>
							<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
								export="currentPageNumber=pageNumber;pageOffset"				
								maxPageItems="10" items="${sessionScope.totalRegistros}">

								<pg:param name="pg" />
								<pg:param name="q" />

								<logic:iterate name="colecaoCobrancaAcaoAtividadeComando"
									id="cobrancaAcaoAtividadeComando">
									<pg:item>
										<%if (cor.equalsIgnoreCase("#cbe5fe")) {
				cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF">
											<%} else {
				cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="18%">
											<div align="center"><a
												href="javascript:enviarDados('<bean:write name="cobrancaAcaoAtividadeComando" property="id"/>', '<bean:write name="cobrancaAcaoAtividadeComando" property="descricaoTitulo"/>', 'cobrancaAcaoAtividadeComando');">
											<logic:present name="cobrancaAcaoAtividadeComando"
												property="cobrancaAcao">
												<bean:define name="cobrancaAcaoAtividadeComando"
													property="cobrancaAcao" id="cobrancaAcao" />
												<bean:write name="cobrancaAcao"
													property="descricaoCobrancaAcao" />
											</logic:present> </a></div>
											</td>
											<td width="18%">
											<div align="center"><logic:present
												name="cobrancaAcaoAtividadeComando"
												property="cobrancaAtividade">
												<bean:define name="cobrancaAcaoAtividadeComando"
													property="cobrancaAtividade" id="cobrancaAtividade" />
												<bean:write name="cobrancaAtividade"
													property="descricaoCobrancaAtividade" />
											</logic:present></div>
											</td>
											<td width="16%">
											<div align="center"><bean:write
												name="cobrancaAcaoAtividadeComando"
												property="descricaoTitulo" /></div>
											</td>
											<td width="16%">
											<div align="center"><bean:write
												name="cobrancaAcaoAtividadeComando"
												format="dd/MM/yyyy HH:mm:ss" property="comando" /></div>
											</td>
											
											<td width="16%">
											<div align="center"><bean:write
												name="cobrancaAcaoAtividadeComando"
												format="dd/MM/yyyy HH:mm:ss" property="realizacao" /></div>
											</td>
											<td width="12%">
											<div align="center"><logic:present
												name="cobrancaAcaoAtividadeComando" property="usuario">
												<bean:define name="cobrancaAcaoAtividadeComando"
													property="usuario" id="usuario" />
												<bean:write name="usuario" property="nomeUsuario" />
											</logic:present></div>
											</td>
											<td width="7%">
											<div align="center"><a
												href="javascript:visualizarParametros(<bean:write name="cobrancaAcaoAtividadeComando"
											property="id" />)"><bean:write
												name="cobrancaAcaoAtividadeComando"
												property="quantidadeDocumentos" /></a></div>
											</td>
											<td width="10%">
											<div align="center"><a
												href="javascript:visualizarCriterios(<bean:write name="cobrancaAcaoAtividadeComando"
											property="id" />)">
											<logic:present name="cobrancaAcaoAtividadeComando"
												property="cobrancaCriterio">
												<bean:define name="cobrancaAcaoAtividadeComando"
													property="cobrancaCriterio" id="cobrancaCriterio" />
												<bean:write name="cobrancaCriterio" property="id" />
											</logic:present> </a></div>
											</td>

										</tr>
									</pg:item>

								</logic:iterate>
						</table>
						<table width="100%" border="0">
							<tr >
								<td align="center"><strong><%@ include file="/jsp/util/indice_pager_novo.jsp"%></strong>
								</td>
							</tr>
						</table>

						</td>
					</tr>
				</table>
			<tr>
				<td height="24"><input type="button" name="Submit222"
					class="bottonRightCol" value="Voltar Pesquisa"
					onClick="javascript:voltar();">
			</tr>
			</pg:pager>
			<%-- Fim do esquema de paginação --%>
		</table>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>
</form>
</body>
</html:html>

