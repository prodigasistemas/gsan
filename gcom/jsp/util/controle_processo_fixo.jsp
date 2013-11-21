  <table width="30%" border="0" align="right" cellpadding="0" cellspacing="0">
  	<tr>
      <logic:equal name="gerenciadorPaginas" property="paginaCorrente.index" value="1">
        <td width="15">
        </td>
        <td width="69">
        </td>
      </logic:equal>
      <logic:greaterThan name="gerenciadorPaginas" property="paginaCorrente.index" value="1">
        <td width="15">
          <a href="<html:rewrite page="/gerenciadorProcessoAction.do?acao=voltar&numeroPagina="/><bean:write name="gerenciadorPaginas" property="paginaAnterior.index"/>">
            <img src="imagens/voltar.gif" width="15" height="24" border="0">
          </a>
        </td>
        <td width="69">
          <input name="voltar" type="button" class="bottonRightCol" value="  Voltar  " onClick="javascript:botaoVoltar('<html:rewrite page="/gerenciadorProcessoAction.do?acao=voltar&numeroPagina="/><bean:write name="gerenciadorPaginas" property="paginaAnterior.index"/>');"/>
        </td>
      </logic:greaterThan>
      <td width="3"></td>
      <logic:equal name="gerenciadorPaginas" property="paginaCorrente.index" value="<%=((GerenciadorPaginas)session.getAttribute("gerenciadorPaginas")).getSize().toString()%>">
        <td width="62">
          <input name="avancar" type="button" class="bottonRightCol" value="Executar" onClick="javascript:botaoAvancar('<html:rewrite page="<%="/"+((GerenciadorPaginas)session.getAttribute("gerenciadorPaginas")).getPaginaCorrente().getUriFinal()+"Action.do?acao=executar"%>"/>');" />
        </td>
        <td width="20">
          <a href="javascript:botaoAvancar('<html:rewrite page="<%="/"+((GerenciadorPaginas)session.getAttribute("gerenciadorPaginas")).getPaginaCorrente().getUriFinal()+"Action.do?acao=executar"%>"/>');">
            <img src="imagens/avancar.gif" width="15" height="24" border="0"/>
          </a>
        </td>
      </logic:equal>
      <logic:lessThan name="gerenciadorPaginas" property="paginaCorrente.index" value="<%=((GerenciadorPaginas)session.getAttribute("gerenciadorPaginas")).getSize().toString()%>">
        <td width="62">
          <% Pagina paginaCorrente = ((GerenciadorPaginas)session.getAttribute("gerenciadorPaginas")).getPaginaCorrente(); %>
          <% Pagina paginaSeguinte = ((GerenciadorPaginas)session.getAttribute("gerenciadorPaginas")).getPaginaSeguinte(); %>
          <input name="avancar" type="button" class="bottonRightCol" value="Avan&ccedil;ar" onClick="javascript:botaoAvancar('<html:rewrite page="<%="/"+paginaCorrente.getUriFinal()+"Action.do?acao=avancar&numeroPagina="+paginaSeguinte.getIndex() %>"/>');" />
        </td>
        <td width="20">
          <a href="javascript:botaoAvancar('<html:rewrite page="<%="/"+paginaCorrente.getUriFinal()+"Action.do?acao=avancar&numeroPagina="+paginaSeguinte.getIndex() %>"/>');" >
            <img src="imagens/avancar.gif" width="15" height="24" border="0"/>
          </a>
        </td>
      </logic:lessThan>
    </tr>
  </table>
