<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ page import="java.util.Collection,java.util.HashMap,java.util.Iterator,gcom.util.ConstantesSistema" %>
<%@ page import="gcom.util.Util" %>
<%@ page import="gcom.seguranca.acesso.OperacaoEfetuada" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script>
<!--

function filtrar() {

	document.forms[0].action = 'ExibirFiltrarOperacaoEfetuadaAction.do';
	document.forms[0].submit();

}

function consultarOperacao(id){
	document.forms[0].idOperacaoEfetuada.value = id;
	document.forms[0].action = 'ConsultarOperacaoEfetuadaAction.do';
	document.forms[0].submit();
}
-->
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/ConsultarOperacaoEfetuadaAction" method="post" >
<html:hidden name="ConsultarOperacaoEfetuadaActionForm" property="idOperacaoEfetuada" />

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

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
      </div></td>
    <td width="625" valign="top" class="centercoltext">
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Consultar Operação</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
       </table>
            <table width="100%" cellpadding="0" cellspacing="0">
	      <tr>
                 <td colspan="4" height="23">&nbsp;</td>
              </tr>
              <tr> 
  				<td colspan="4" height="23"><font style="font-size: 10px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
		   			<strong>Opera&ccedil;&otilde;es Realizadas</font>
	        		<logic:present name="nomeOperacaoSelecionada">
	        		  <logic:notEmpty name="nomeOperacaoSelecionada">
	        		    de 
	        			<font style="font-size: 10px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="nomeOperacaoSelecionada" />
						</font>
						</logic:notEmpty>
					</logic:present>
	        		<logic:present name="nomeUsuario">
						por
	        			<font style="font-size: 10px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="nomeUsuario" />
						</font>
					</logic:present>
					:
		   			</strong>
		   		</td>
              </tr>
			<tr>
	            <td colspan="4" bgcolor="#000000" height="2">
	            </td>
	        </tr>
	        
               <tr>
                 <td > <%--<div style="width: 100%; height: 100%; overflow: auto;">--%>

                      <%--Esquema de paginação--%>
                     <pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"				
							maxPageItems="10" items="${sessionScope.totalRegistros}">
                      <pg:param name="pg"/>
                      <pg:param name="q"/>

<%
%>
<table align="center" bgcolor="#99ccff" width="100%" >
              <tr bordercolor="#000000"> 

                <td bgcolor="#90c7fc" width="21%"><div align="center"><strong>Data da Realização</strong></div></td>        
			<logic:equal name="variasOperacoesSelecionadas" value="true">
                <td bgcolor="#90c7fc" width="38%"><div align="center"><strong>Nome da Operação</strong></div></td>
            </logic:equal>
			<logic:present name="descricaoArgumento">
                <td bgcolor="#90c7fc" width="15%"><div align="center"><strong><bean:write name="descricaoArgumento" /></strong></div></td>
			</logic:present>                   
			<logic:notPresent name="descricaoArgumento">                
                <td bgcolor="#90c7fc" width="15%"><div align="center"><strong>Identificador</strong></div></td>
			</logic:notPresent>                                
                <td bgcolor="#90c7fc" width="38%"><div align="center"><strong>Outros dados</strong></div></td>				
				<logic:notPresent name="nomeUsuario">
                	<td bgcolor="#90c7fc" width="26%"><div align="center"><strong>Usuário que Realizou a Operação</strong></div></td>
                </logic:notPresent>
              </tr>
              
						<%int cont=0;
						%>
<logic:present name="operacaoEfetuada" >
<logic:iterate name="operacaoEfetuada" id="operacaoEfetuada">

                        <pg:item>
                          <%
                              String outrosDadosHTML = "";
							  String[][] outrosDados = ((OperacaoEfetuada) operacaoEfetuada).formatarDadosAdicionais();
							  if (outrosDados != null) {
	                              for(int i = 0; i < outrosDados.length; i++){                            	  
	                            	  outrosDadosHTML += outrosDados[i][0] + ": " + outrosDados[i][1] + "<BR>";
	                              }
							  }

//							  if (resumo != null) {
//								  Iterator iterRes = resumo.keySet().iterator();
//								  while(iterRes.hasNext()){
//									  String key = (String) iterRes.next();
//									  String valor = resumo.get(key).toString();
//									  
//								  }
//                              }

                              cont = cont+1;
                              if (cont%2==0){%>
                                      <tr bgcolor="#cbe5fe">
                           <%}else{ %>
                                      <tr bgcolor="#FFFFFF">
                            <%}%> 
                <td><div align="center"><%=Util.formatarDataComHora(((OperacaoEfetuada)operacaoEfetuada).getUltimaAlteracao()) %></div></td>
				<logic:equal name="variasOperacoesSelecionadas" value="true">
                  <td><div align="center" style= "text-transform:uppercase;"><bean:write name="operacaoEfetuada" property="operacao.descricao"  /></div></td>                
                </logic:equal>
                <logic:notPresent name="descricaoArgumento">                      
                  <td><div align="center"><a href="javascript:consultarOperacao('<bean:write name="operacaoEfetuada" property="id" />')">
                      <%=((OperacaoEfetuada)operacaoEfetuada).getArgumentoValor()%></a></div></td>
                </logic:notPresent>
                <logic:present name="descricaoArgumento">
	                  <td><div align="center"><a href="javascript:consultarOperacao('<bean:write name="operacaoEfetuada" property="id" />')">
	                     <%=((OperacaoEfetuada)operacaoEfetuada).getArgumentoValor()%></a></div></td>
                </logic:present>                
			<%
//				if (resumosDados != null){
			%>
	                <td><div align="center">
	                  <%=(outrosDadosHTML == null ? "" : outrosDadosHTML) %>
	                </div></td>
                
            <%
//				}
            %>
            <logic:notPresent name="nomeUsuario">
                <td><div align="center">
                <% 
            	if (((OperacaoEfetuada)operacaoEfetuada).getUsuarioAlteracoes() != null && !((OperacaoEfetuada)operacaoEfetuada).getUsuarioAlteracoes().isEmpty()) {
            		out.print(((gcom.seguranca.acesso.usuario.UsuarioAlteracao)((OperacaoEfetuada)operacaoEfetuada).getUsuarioAlteracoes().iterator().next()).getUsuario().getNomeUsuario());
               	}
                 %>
                </div></td>
            </logic:notPresent>
              </tr>
                        </pg:item>
</logic:iterate>  
</logic:present>
</table>
            <%if(cont == ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA){%>
              <%@ include file="/jsp/util/limite_pesquisa.jsp"%>
            <%}%>

           <table width="100%" border="0">
							<tr>
								<td align="center">
									<strong><%@ include file="/jsp/util/indice_pager_novo.jsp"%></strong>
								</td>
							</tr>
			</table>

            <table width="100%" border="0">
              <tr>
                <td><input name="adicionar" class="bottonRightCol" value="Novo Filtro" onclick="javascript:filtrar();" type="button">
                    <input name="voltar" class="bottonRightCol" value="Voltar" onclick="javascript:history.back();" type="button"></td>
                </td>
                
                <tr>
					<td align="right">
					
					<!-- -->
					<select name="opcaoRelatorio">
						<option
						value="gerarRelatorioConsultarOperacaoEfetuadaAction.do?tipo=RESUMIDO">Relatório Resumido</option>
						<option
						value="gerarRelatorioConsultarOperacaoEfetuadaAction.do?tipo=DETALHADO">Relatório Detalhado</option>
					</select>
					 
					 &nbsp;</td>
					<td align="right">
					<div align="right">
 <a href="javascript:toggleBoxCaminho('demodiv',1, document.forms[0].opcaoRelatorio.value);"> 
<!--<a href="javascript:toggleBoxCaminho('demodiv',1, 'gerarRelatorioConsultarOperacaoEfetuadaAction.do?tipo=DETALHADO' );">-->
					 <img border="0"
						src="<bean:message key="caminho.imagens"/>print.gif"
						title="Imprimir Operações" />  
						</a>
						</div>
						
					</td>
				</tr>
            </table>
            
          </pg:pager>
       </table>
       <p>&nbsp;</p>
    </td>
  </tr>
</table>
<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=" />
<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
