<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.gui.*, java.util.*" isELIgnored="false"%>


<% String numeroPagina = request.getParameter("numeroPagina");
   String numeroPaginaAnterior = (Integer.parseInt(numeroPagina) - 1) + "";
   String numeroPaginaPosterior = (Integer.parseInt(numeroPagina) + 1) + "";
   StatusWizard statusWizard = (StatusWizard)session.getAttribute("statusWizard");
   StatusWizard.StatusWizardItem itemWizard = statusWizard.retornarItemWizard(Integer.parseInt(numeroPagina));
   pageContext.setAttribute("numeroPagina", numeroPagina);
   pageContext.setAttribute("itemWizard", itemWizard);
   pageContext.setAttribute("numeroPaginaAnterior", numeroPaginaAnterior);
   pageContext.setAttribute("numeroPaginaPosterior", numeroPaginaPosterior);
//   System.out.println("teste422");
%>


  <table width="50%" border="0" align="right" cellpadding="0" cellspacing="0">
  	<tr>
	<td>
		<input name="cancelar" type="button" class="bottonRightCol" value="Cancelar" onClick="javascript:window.location.href='/gsan/<bean:write name="statusWizard" property="caminhoActionCancelamento"/>.do'"/>
	</td>
	<td>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</td>
      <logic:equal name="numeroPagina" value="1">
        <td width="15">
        </td>
        <td width="69">
        </td>
      </logic:equal>
      <logic:greaterThan name="numeroPagina" value="1">
        <td width="15">
          <a href="javascript:botaoAvancar('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?destino=<bean:write name="numeroPaginaAnterior"/>&action=<bean:write name="itemWizard" property="caminhoActionFinal"/>');">
            <img src="imagens/voltar.gif" width="15" height="24" border="0">
          </a>
        </td>
        <td width="69">
          <input name="voltar" type="button" class="bottonRightCol" value="  Voltar  " onClick="javascript:botaoAvancar('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?destino=<bean:write name="numeroPaginaAnterior"/>&action=<bean:write name="itemWizard" property="caminhoActionFinal"/>');"/>
        </td>
      </logic:greaterThan>
      <td width="3"></td>
<%--      <logic:equal name="numeroPagina" value="<%=""+((StatusWizard)session.getAttribute("statusWizard")).getRelacaoNumeroPaginaCaminho().size()%>">
        <td width="62">
          <input name="avancar" type="button" class="bottonRightCol" value="Executar" onclick="javascript:botaoAvancar('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?destino=<bean:write name="numeroPaginaPosterior"/>&action=<bean:write name="itemWizard" property="caminhoActionFinal"/>');" />
        </td>
        <td width="20">
          <a href="javascript:botaoAvancar('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?destino=<bean:write name="numeroPaginaPosterior"/>&action=<bean:write name="itemWizard" property="caminhoActionFinal"/>');">
            <img src="imagens/avancar.gif" width="15" height="24" border="0"/>
          </a>
        </td>
      </logic:equal>
--%>
      <logic:lessThan name="numeroPagina" value="<%=""+((StatusWizard)session.getAttribute("statusWizard")).getRelacaoNumeroPaginaCaminho().size()%>">
        <td width="62">
          <input name="avancar" type="button" class="bottonRightCol" value="Avan&ccedil;ar" onClick="javascript:botaoAvancar('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?destino=<bean:write name="numeroPaginaPosterior"/>&action=<bean:write name="itemWizard" property="caminhoActionFinal"/>');" />
        </td>
        <td width="20">
          <a href="javascript:botaoAvancar('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?destino=<bean:write name="numeroPaginaPosterior"/>&action=<bean:write name="itemWizard" property="caminhoActionFinal"/>');" >
            <img src="imagens/avancar.gif" width="15" height="24" border="0"/>
          </a>
        </td>
      </logic:lessThan>
	<td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</td>
        <td width="20">
          <input name="concluir" type="button" class="bottonRightCol" value="Concluir" onClick="javascript:botaoAvancar('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?concluir=true&action=<bean:write name="itemWizard" property="caminhoActionFinal"/>');" />
	</td>
    </tr>
  </table>

