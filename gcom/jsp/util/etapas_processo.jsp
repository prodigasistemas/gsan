<div id="Layer1" style="position:absolute; left:460px; top:77px; width:300px; height:12px; z-index:1">
  <table align="right" cellpadding="0" cellspacing="0">
    <tr>
      <logic:iterate name="gerenciadorPaginas" property="paginas" id="pagina" scope="session">
        <td valign="top">
          <logic:equal name="pagina" property="paginaCorrente" value="true">
            <logic:equal name="pagina" property="paginaPreenchida" value="false">
              <img width="34" height="30" border="0" src="<bean:message key="caminho.imagens"/><bean:write name="pagina" property="imagemAtiva"/>"/>
            </logic:equal>
          </logic:equal>
          <logic:equal name="pagina" property="paginaCorrente" value="false">
            <logic:equal name="pagina" property="paginaPreenchida" value="true">
              <logic:equal name="pagina" property="tamanhoMaiorDescricaoEmPixels" value="0" >
                <a href="<html:rewrite page="/gerenciadorProcessoAction.do?numeroPagina="/><bean:write name="pagina" property="index"/>">
                  <img width="34" height="30" border="0" src="<bean:message key="caminho.imagens"/><bean:write name="pagina" property="imagemAtiva"/>"/>
                </a>
              </logic:equal>
              <logic:notEqual name="pagina" property="tamanhoMaiorDescricaoEmPixels" value="0" >
                <a onmouseover="this.T_TITLE='Resumo de <bean:write name="pagina" property="descricao"/>';
                                this.T_BGCOLOR='#FFFFCC';
                                this.T_SHADOWWIDTH=5;
                                this.T_WIDTH=<bean:write name="pagina" property="tamanhoMaiorDescricaoEmPixels"/>;
                                return escape('<bean:write name="pagina" property="hint"/>')" href="<html:rewrite page="/gerenciadorProcessoAction.do?numeroPagina="/><bean:write name="pagina" property="index"/>">
                  <img width="34" height="30" border="0" src="<bean:message key="caminho.imagens"/><bean:write name="pagina" property="imagemAtiva"/>"/>
                </a>
              </logic:notEqual>
            </logic:equal>
            <logic:equal name="pagina" property="paginaPreenchida" value="false">
              <img width="34" height="30" border="0" src="<bean:message key="caminho.imagens"/><bean:write name="pagina" property="imagemInativa"/>"/>
            </logic:equal>
          </logic:equal>
        </td>
        <logic:equal name="pagina" property="paginaCorrente" value="true">
          <td><bean:write name="pagina" property="descricao"/></td>
        </logic:equal>
    </logic:iterate>
    </tr>
  </table>
</div>
