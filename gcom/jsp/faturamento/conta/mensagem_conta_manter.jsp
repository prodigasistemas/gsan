<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ page import="gcom.util.ConstantesSistema,java.util.Collection"%>
<%@ page import="gcom.util.Util"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
function reload() {
	alert("reload");
   	redirecionarSubmit("exibirManterConsumoTarifaAction.do");
      
 }

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

 function novoFiltro(){
	var formRed = "/gsan/exibirFiltrarMensagemContaAction.do?menu=sim";
		redirecionarSubmit(formRed);
 }
  
  	
</script>
</head>
<!-- abrirPopup('/gsan/exibirInserirReajusteConsumoTarifaAction.do?id_r=' + document.forms[0].idRegistrosRemocao, 350, 610); -->
<body leftmargin="5" topmargin="5">
<html:form action="/removerMensagemContaAction"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm"
	onsubmit="return CheckboxNaoVazio(document.ManutencaoRegistroActionForm.idRegistrosRemocao) && confirm('Confirma Remoção?')"
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
					<td class="parabg">Manter Mensagem da Conta</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%">
				<tr>
					<td height="3"><font style="font-size: 10px;"
						color="#000000" face="Verdana, Arial, Helvetica, sans-serif"><strong>Mensagens
					da Conta Cadastradas:</strong></font></td>
 				    <logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoContaMensagemManter', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent> 					
				</tr>
			</table>
					<table width="100%">
					<table>
						<tr>
							<td></td>
						</tr>
					</table>
					<table border="0" width="100%" bgcolor="#99ccff">
						<tr>
							<td colspan="8" bgcolor="#000000" height="2"></td>
						</tr>
						<tr>
							<td bgcolor="#99ccff" width="8%" align="center"><strong><a
								href="javascript:facilitador(this);">Todos</a></strong></td>
							<td bgcolor="#99ccff" align="center"><strong>Referência do
							Faturamento</strong></td>
							<td bgcolor="#99ccff" width="30%" align="center"><strong>Mensagem da Conta</strong></td>
							<td bgcolor="#99ccff" align="center"><strong>Grupo de Faturamento</strong></td>
							<td bgcolor="#99ccff" align="center"><strong>Gerência Regional</strong></td>
							<td bgcolor="#99ccff" align="center"><strong>Localidade</strong></td>
							<td bgcolor="#99ccff" align="center"><strong>Setor</strong></td>
							<td bgcolor="#99ccff" align="center"><strong>Quadra</strong></td>							
						</tr>
						<%String cor = "#FFFFFF";%>
						<pg:pager maxIndexPages="10" export="currentPageNumber=pageNumber"
							index="center" maxPageItems="10">
							<pg:param name="pg" />
							<pg:param name="q" />
							<logic:present name="colecaoContaMensagem" scope="session">
								<logic:iterate name="colecaoContaMensagem" id="contaM">
									<pg:item>
										<%if (cor.equalsIgnoreCase("#FFFFFF")) {
				cor = "#cbe5fe";%>
										<tr bgcolor="#FFFFFF">
										
											<%} else {
				cor = "#FFFFFF";%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td>
											<div align="center"><strong> <input name="idRegistrosRemocao"
												value="<bean:write name="contaM" property="id" />"
												type="checkbox"> </strong></div>
											</td>
											<td align="center"><a href="/gsan/exibirAtualizarMensagemContaAction.do?url=manter&idMensagemConta=<bean:write name="contaM" property="id"/>"><%= Util.formatarAnoMesParaMesAno(((gcom.faturamento.conta.ContaMensagem)contaM).getAnoMesRreferenciaFaturamento()) %></a></td>
											<td align="left"><a href="javascript:abrirPopup('exibirVisualizarMensagemContaAction.do?id=<bean:write name="contaM" property="id"/>');">${contaM.descricaoContaMensagem01}</td>
											<td align="left">${contaM.faturamentoGrupo.descricao}</td>
											<td align="left">${contaM.gerenciaRegional.nomeAbreviado}</td>
											<td align="center">${contaM.localidade.id}</td>
											<td align="center">${contaM.setorComercial.codigo}</td>
											<td align="center">${contaM.quadra.numeroQuadra}</td>											
										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
					</table>
					<br>
					<table width="100%">
						<tr>
							<td><html:submit styleClass="bottonRightCol" value="Remover"
								property="Button" /> <input type="button" name="Button2"
								Class="bottonRightCol" value="Voltar Filtro"
								onclick="javascript:history.back();">
							</td>
							<td align="right"><div align="right"><a href="javascript:toggleBox('demodiv',1);">
							<img border="0"
								src="<bean:message key="caminho.imagens"/>print.gif"
								title="Imprimir Mensagens de Conta" /> </a></div></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<%if (((Collection) session.getAttribute("colecaoContaMensagem"))
					.size() == ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {%>
				<%@ include file="/jsp/util/limite_filtro.jsp"%>
				<%}%>
				<tr>
					<td>
					<div align="center"><strong><%@ include
						file="/jsp/util/indice_pager.jsp"%></strong></div>
					</td>
					</pg:pager>
				</tr>
			</table>
		</tr>
	</table>
		<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioMensagemContaManterAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>

</html:html>
