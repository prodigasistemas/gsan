<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.gui.*,java.util.*" isELIgnored="false"%>

<%
String numeroPagina = request.getParameter("numeroPagina");
String voltarFiltro = request.getParameter("voltarFiltro");
String numeroPaginaAnterior = (Integer.parseInt(numeroPagina) - 1) + "";
String numeroPaginaPosterior = (Integer.parseInt(numeroPagina) + 1) + "";
StatusWizard statusWizard = (StatusWizard) session.getAttribute("statusWizard");
StatusWizard.StatusWizardItem itemWizard = statusWizard.retornarItemWizard(Integer.parseInt(numeroPagina));
pageContext.setAttribute("voltarFiltro", voltarFiltro);
pageContext.setAttribute("numeroPagina", numeroPagina);
pageContext.setAttribute("itemWizard", itemWizard);
pageContext.setAttribute("numeroPaginaAnterior", numeroPaginaAnterior);
pageContext.setAttribute("numeroPaginaPosterior", numeroPaginaPosterior);
%>

<logic:notEmpty name="voltarFiltro" scope="page">
	<logic:equal name="numeroPagina" value="1">
		<input type="hidden" name="indicadorVoltar" />
	</logic:equal>
</logic:notEmpty>

<table width="100%" border="0" align="center" cellpadding="0" cellspacing="3">
	<tr>
		<!-- Apresenta na tela o botão Voltar -->
		<logic:equal name="numeroPagina" 
			value="<%=""+((StatusWizard)session.getAttribute("statusWizard")).getRelacaoNumeroPaginaCaminho().size()%>">
			<%StatusWizard.StatusWizardItem item = statusWizard
					.retornarItemWizard(Integer.parseInt(numeroPaginaAnterior));%>
			<td align="right"  width="53%">
				<a href="javascript:botaoAvancarTelaEspera('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?action=<%=item.getCaminhoActionInicial()%>');document.forms[0].submit();">
					<img src="imagens/voltar.gif" border="0"></a>
			</td>
			<td align="left" width="47%">
				<input name="voltar" type="button" class="buttonAbaRodape" value="  Voltar  "
					onClick="javascript:botaoAvancarTelaEspera('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?action=<%=item.getCaminhoActionInicial()%>');document.forms[0].submit();" />
			</td>
   			&nbsp;&nbsp;
   		</logic:equal>

		<!-- Apresenta na tela o botão Avançar -->
		<logic:equal name="numeroPagina" value="1">
			<td align="right" width="75%">
				<input name="avancar" type="button" class="buttonAbaRodape" value="Avan&ccedil;ar"
					onClick="javascript:botaoAvancarTelaEspera('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?destino=<bean:write name="numeroPaginaPosterior"/>&action=<bean:write name="itemWizard" property="caminhoActionFinal"/>');" />
			</td>
			<td align="left" width="25%">
				<a href="javascript:botaoAvancarTelaEspera('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?destino=<bean:write name="numeroPaginaPosterior"/>&action=<bean:write name="itemWizard" property="caminhoActionFinal"/>');">
					<img src="imagens/avancar.gif" border="0" /></a>
			</td>
		</logic:equal>
		
		<!-- Apresenta na tela os dois botões, Voltar e Avançar -->
		<logic:notEqual name="numeroPagina"
			value="<%=""+((StatusWizard)session.getAttribute("statusWizard")).getRelacaoNumeroPaginaCaminho().size()%>">
			<logic:notEqual name="numeroPagina" value="1">
				<%StatusWizard.StatusWizardItem item2 = statusWizard
						.retornarItemWizard(Integer.parseInt(numeroPaginaAnterior));%>
				<td align="right"  width="45%"></td>
				<td align="right"  width="10%">
					<a href="javascript:botaoAvancarTelaEspera('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?action=<%=item2.getCaminhoActionInicial()%>');document.forms[0].submit();">
						<img src="imagens/voltar.gif" border="0"></a>
				</td>
				<td align="left" width="10%">
					<input name="voltar" type="button" class="buttonAbaRodape" value="  Voltar  "
						onClick="javascript:botaoAvancarTelaEspera('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?action=<%=item2.getCaminhoActionInicial()%>');document.forms[0].submit();" />
				</td>
	   			&nbsp;&nbsp;
				<td align="right" width="10%">
					<input name="avancar" type="button" class="buttonAbaRodape" value="Avan&ccedil;ar"
						onClick="javascript:botaoAvancarTelaEspera('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?destino=<bean:write name="numeroPaginaPosterior"/>&action=<bean:write name="itemWizard" property="caminhoActionFinal"/>');" />
				</td>
				<td align="left" width="10%">
					<a href="javascript:botaoAvancarTelaEspera('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?destino=<bean:write name="numeroPaginaPosterior"/>&action=<bean:write name="itemWizard" property="caminhoActionFinal"/>');">
						<img src="imagens/avancar.gif" border="0" /></a>
				</td>
				<td align="right" width="15%"></td>
			</logic:notEqual>
		</logic:notEqual>
	</tr>
</table>
<table width="100%" border="0" align="center" cellpadding="0"cellspacing="3">
	<tr>
		<td colspan="9" width="100%" height="1px" bgcolor="#000000"></td>
	</tr>
	<tr>
		<td colspan="8" align="left" width="80%">
			<logic:notEmpty name="statusWizard" property="caminhoActionVoltarFiltro">
				<input name="Voltar Filtro" type="button" class="bottonRightCol" value="Voltar" style="width: 80px"
					onClick="javascript:window.location.href='/gsan/<bean:write name="statusWizard" property="caminhoActionVoltarFiltro"/>.do';" />&nbsp;
			</logic:notEmpty>
			<logic:present name="statusWizard" property="caminhoActionDesfazerInserir">
				<input name="desfazer" type="button" class="bottonRightCol" value="Desfazer" style="width: 80px"
					onClick="javascript:window.location.href='${statusWizard.caminhoActionDesfazerInserir}';" />&nbsp;
			</logic:present>
			<logic:present name="statusWizard" property="caminhoActionDesfazer">		
				<input name="desfazer" type="button" class="bottonRightCol" value="Desfazer" style="width: 80px"
					onClick="javascript:window.location.href='${statusWizard.caminhoActionDesfazer}';" />&nbsp;
			</logic:present>	
			<input name="cancelar" type="button" class="bottonRightCol" value="Cancelar" style="width: 80px"
				onClick="javascript:window.location.href='/gsan/telaPrincipal.do'" />
		</td>
		<td align="right" width="*">
			<input name="concluir" id="botaoConcluir" type="button" class="bottonRightCol" style="width: 80px" value="Concluir"
				onClick="javascript:botaoAvancar('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?concluir=true&action=<bean:write name="itemWizard" property="caminhoActionFinal"/>');" />
		</td>
	</tr>
</table>