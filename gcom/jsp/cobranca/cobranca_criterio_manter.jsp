<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="java.util.Collection,gcom.util.ConstantesSistema"%>
<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.seguranca.acesso.usuario.UsuarioAlteracao"%>
<%@ page import="gcom.seguranca.transacao.TabelaLinhaColunaAlteracao"%>
<%@ page import="gcom.seguranca.acesso.OperacaoEfetuada"%>
<%@ page import="java.util.Date"%>

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

<script language="JavaScript">
<!-- Begin
	function validarForm(form){
	 if(CheckboxNaoVazio(document.ManutencaoRegistroActionForm.idRegistrosRemocao) && confirm('Confirma remoção?')){
	   form.action = 'removerCriterioCobrancaAction.do';
	   form.submit();			
	 }
	}
-->
</script>

<script>
<!--
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

-->
</script>


</head>

<body leftmargin="5" topmargin="5">

<html:form action="/removerCriterioCobrancaAction" method="post"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post"
	>

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
			<td width="602" valign="top" class="centercoltext">
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

					<td class="parabg">Manter Critério de Cobrança</td>


					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="7" height="23"><font style="font-size: 10px;"
						color="#000000" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Critérios
					de Cobrança Cadastrados:</strong> </font></td>
				</tr>

				<tr>
					<td colspan="7" bgcolor="#000000" height="2"></td>
				</tr>



				<tr>
					<td>
					<table width="100%" bgcolor="#99CCFF">


						<tr bordercolor="#000000">
							<td width="13%">
							<div align="center"><strong><a
								href="javascript:facilitador(this);" id="0">Todos</a></strong></div>
							</td>
							<td align="center" width="55%" bgcolor="#90c7fc"><strong>Descrição
							do Critério de Cobrança</strong></td>
							<td width="32%" bgcolor="#90c7fc" align="center"><strong>Início
							de Vigência</strong></td>
						</tr>

						<%--Esquema de paginação--%>
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg" />
							<pg:param name="q" />

							<logic:present name="collectionCriterioCobranca">
								<%int cont = 1;%>
								<logic:iterate name="collectionCriterioCobranca"
									id="cobrancaCriterio">
									<pg:item>
										<%cont = cont + 1;
			if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

			%>
										<tr bgcolor="#cbe5fe">
											<%}%>

											<td width="13%">
											<div align="center"><input type="checkbox"
												name="idRegistrosRemocao"
												value="<bean:write name="cobrancaCriterio" property="id"/>"></div>
											</td>



											<td width="55%"><a
												href="/gsan/exibirAtualizarCriterioCobrancaAction.do?idRegistroAtualizacao=<bean:write name="cobrancaCriterio" property="id"/>"><bean:write
												name="cobrancaCriterio" property="descricaoCobrancaCriterio" /></a>
											</td>


											<td width="32%" align="center">
											<div><bean:write name="cobrancaCriterio"
												property="dataInicioVigencia" formatKey="date.format" /></div>
											</td>
										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
					</table>
					</td>
				</tr>

			</table>


			<table width="100%">
				<tr>

					<td>
					  <gsan:controleAcessoBotao name="botaoSubmit" value="Remover" onclick="validarForm(document.forms[0]);" url="removerCriterioCobrancaAction.do" align="left"/> 
					   <%--<input name="botaoSubmit" type="submit" class="bottonRightCol" value="Remover" align="left" style="width: 70px;"> --%>
						
						<input
						name="button" type="button" class="bottonRightCol"
						value="Voltar Filtro"
						onclick="window.location.href='<html:rewrite page="/exibirFiltrarCriterioCobrancaAction.do"/>'"
						align="left" style="width: 80px;"></td>
					<td align="right">
					<div align="right"><a href="javascript:toggleBox('demodiv',1);"> <img
						border="0" src="<bean:message key="caminho.imagens"/>print.gif"
						title="Imprimir Critérios de Cobrança" /> </a></div>
				</tr>
			</table>



			<table width="100%" border="0">

				<tr>
					<td>
					<div align="center"><strong><%@ include
						file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
					</td>
					</pg:pager>
					<%-- Fim do esquema de paginação --%>
				</tr>

			</table>
			</td>
		</tr>
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioCriterioCobrancaManterAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
