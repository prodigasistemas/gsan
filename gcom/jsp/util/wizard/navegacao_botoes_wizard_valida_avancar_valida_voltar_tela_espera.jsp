<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.gui.*" isELIgnored="false"%>


<% 
String numeroPagina = request.getParameter("numeroPagina");
String numeroPaginaAnterior = (Integer.parseInt(numeroPagina) - 1) + "";
String numeroPaginaPosterior = (Integer.parseInt(numeroPagina) + 1) + "";
StatusWizard statusWizard = (StatusWizard)session.getAttribute("statusWizard");
StatusWizard.StatusWizardItem itemWizard = statusWizard.retornarItemWizard(Integer.parseInt(numeroPagina));
pageContext.setAttribute("numeroPagina", numeroPagina);
pageContext.setAttribute("itemWizard", itemWizard);
pageContext.setAttribute("numeroPaginaAnterior", numeroPaginaAnterior);
pageContext.setAttribute("numeroPaginaPosterior", numeroPaginaPosterior);
%>

<table width="100%" border="0" align="right" cellpadding="0" cellspacing="3">
	<tr>
		<logic:greaterThan name="numeroPagina" value="1">
        	<td width="50%" align="right">
				<a href="javascript:botaoAvancarTelaEspera('/gsan/<bean:write name="statusWizard" 
					property="caminhoActionPrincipalWizard"/>.do?destino=<bean:write name="numeroPaginaAnterior"/>&action=<bean:write name="itemWizard" property="caminhoActionFinal"/>');">
		            <img src="imagens/voltar.gif" border="0"></a>
			</td>
			<td width="10%">
				<input name="voltar" type="button" class="buttonAbaRodape" value="Voltar" style="width: 80px"
					onClick="javascript:botaoAvancarTelaEspera('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?destino=<bean:write name="numeroPaginaAnterior"/>&action=<bean:write name="itemWizard" property="caminhoActionFinal"/>');"/>
			</td>
		</logic:greaterThan>
		<logic:lessThan name="numeroPagina" value="<%=""+((StatusWizard)session.getAttribute("statusWizard")).getRelacaoNumeroPaginaCaminho().size()%>">
			<logic:equal name="numeroPagina" value="1">		
				<td width="60%">&nbsp;</td>
			</logic:equal>
			<td width="10%">
				<input name="avancar" type="button" class="buttonAbaRodape" value="Avan&ccedil;ar" style="width: 80px"
					onClick="javascript:botaoAvancarTelaEspera('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?destino=<bean:write name="numeroPaginaPosterior"/>&action=<bean:write name="itemWizard" property="caminhoActionFinal"/>');" />
			</td>
			<td width="10%">
				<a href="javascript:botaoAvancarTelaEspera('/gsan/<bean:write name="statusWizard" 
					property="caminhoActionPrincipalWizard"/>.do?destino=<bean:write name="numeroPaginaPosterior"/>&action=<bean:write name="itemWizard" property="caminhoActionFinal"/>');" >
				<img src="imagens/avancar.gif" border="0"/></a>
			</td>
		</logic:lessThan>
	</tr>
	<tr>
		<td colspan="9" width="100%" height="1px" bgcolor="#000000"></td>
	</tr>
	<tr>
		<td colspan="7">
			<logic:notEmpty name="statusWizard" property="caminhoActionVoltarFiltro">
				<input name="Voltar Filtro" type="button" class="bottonRightCol" value="Voltar" style="width: 80px"
					onClick="javascript:window.location.href='/gsan/<bean:write name="statusWizard" property="caminhoActionVoltarFiltro"/>.do';" />&nbsp;
			</logic:notEmpty>
			<logic:present name="statusWizard" property="caminhoActionDesfazerVoltarAvancar">
				<input name="desfazer" type="button" class="bottonRightCol" value="Desfazer" style="width: 80px"
					onClick="javascript:window.location.href='${statusWizard.caminhoActionDesfazerVoltarAvancar}';" />&nbsp;
			</logic:present>	
			<input name="cancelar" type="button" class="bottonRightCol" value="Cancelar" 
				onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"/>
		</td>
		<td width="20%" colspan="2" align="right">
        	<input name="concluir" type="button" id="botaoConcluir" class="bottonRightCol" value="Concluir" 
        		onClick="javascript:botaoAvancarTelaEspera('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?concluir=true&action=<bean:write name="itemWizard" property="caminhoActionFinal"/>');" />
		</td>
	</tr>
</table>