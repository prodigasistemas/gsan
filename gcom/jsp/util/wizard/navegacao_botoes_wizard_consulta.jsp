<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.gui.*"%>

<% 

   String numeroPagina = request.getParameter("numeroPagina");
   String voltarFiltro = request.getParameter("voltarFiltro");
   String numeroPaginaAnterior = (Integer.parseInt(numeroPagina) - 1) + "";
   String numeroPaginaPosterior = (Integer.parseInt(numeroPagina) + 1) + "";
   StatusWizard statusWizard = (StatusWizard)session.getAttribute("statusWizard");
   StatusWizard.StatusWizardItem itemWizard = statusWizard.retornarItemWizard(Integer.parseInt(numeroPagina));
   pageContext.setAttribute("voltarFiltro", voltarFiltro);
   pageContext.setAttribute("numeroPagina", numeroPagina);
   pageContext.setAttribute("itemWizard", itemWizard);
   pageContext.setAttribute("numeroPaginaAnterior", numeroPaginaAnterior);
   pageContext.setAttribute("numeroPaginaPosterior", numeroPaginaPosterior);

	StatusWizard.StatusWizardItem itemAnterior = statusWizard.retornarItemWizard(Integer.parseInt(numeroPaginaAnterior));

	StatusWizard.StatusWizardItem itemPosterior = statusWizard.retornarItemWizard(Integer.parseInt(numeroPaginaPosterior));
	
%>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="3">
	<tr>
		<td width="20%">
		</td>
		<!-- Apresenta na tela o botão Voltar -->
		<logic:equal name="numeroPagina" 
			value="<%=""+((StatusWizard)session.getAttribute("statusWizard")).getRelacaoNumeroPaginaCaminho().size()%>">
			<%StatusWizard.StatusWizardItem item = statusWizard
				.retornarItemWizard(Integer.parseInt(numeroPaginaAnterior));%>
			<td align="right"  width="33%">
				<a href="javascript:window.location.href='/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?action=<%=itemAnterior.getCaminhoActionInicial()%>';">
				            	<img src="imagens/voltar.gif" width="15" height="20" border="0">
            	</a>
			</td>
			<td align="left" width="47%">
				<input name="voltar" type="button" class="bottonRightCol" value="  Voltar  " onClick="javascript:window.location.href='/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?action=<%=itemAnterior.getCaminhoActionInicial()%>';"/>
			</td>
 			&nbsp;&nbsp;
  		</logic:equal>
    		
		<!-- Apresenta na tela o botão Avançar -->
		<logic:equal name="numeroPagina" value="1">
			<td align="right" width="75%">
				<input name="avancar" type="button" class="bottonRightCol" value="Avan&ccedil;ar" onClick="javascript:window.location.href='/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?action=<%=itemPosterior.getCaminhoActionInicial() %>';" />
			</td>
			<td align="left" width="25%">
				<a href="javascript:window.location.href='/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?action=<%=itemPosterior.getCaminhoActionInicial() %>';" >
				            	<img src="imagens/avancar.gif" width="15" height="20" border="0"/></a>
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
				<a href="javascript:window.location.href='/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?action=<%=itemAnterior.getCaminhoActionInicial()%>';">
	            	<img src="imagens/voltar.gif" width="15" height="20" border="0">
            	</a>
				</td>
				<td align="left" width="10%">
					<input name="voltar" type="button" class="bottonRightCol" value="  Voltar  " onClick="javascript:window.location.href='/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?action=<%=itemAnterior.getCaminhoActionInicial()%>';"/>
				</td>
	   			&nbsp;&nbsp;
				<td align="right" width="10%">
					<input name="avancar" type="button" class="bottonRightCol" value="Avan&ccedil;ar" onClick="javascript:window.location.href='/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?action=<%=itemPosterior.getCaminhoActionInicial() %>';" />
				</td>
				<td align="left" width="10%">
				<a href="javascript:window.location.href='/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?action=<%=itemPosterior.getCaminhoActionInicial() %>';" >
				            	<img src="imagens/avancar.gif" width="15" height="20" border="0"/></a>
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
		<logic:notEmpty name="statusWizard" property="caminhoActionVoltarFiltro">
			<td colspan="2" align="left" width="12%">
			<input name="voltar Filtro" type="button" class="bottonRightCol" value="Voltar" style="width: 80px"
				onClick="javascript:window.location.href='/gsan/<bean:write name="statusWizard" property="caminhoActionVoltarFiltro"/>.do';" />
			</td>	
		</logic:notEmpty>
		<td colspan="5" align="left" width="66%">
				&nbsp;&nbsp;
			<input name="cancelar" type="button" class="bottonRightCol" value="Cancelar" style="width: 80px"
				onClick="javascript:window.location.href='/gsan/telaPrincipal.do'" />
		</td>
		<td colspan="2" align="right" width="17%">
		</td>
	</tr>
</table>



