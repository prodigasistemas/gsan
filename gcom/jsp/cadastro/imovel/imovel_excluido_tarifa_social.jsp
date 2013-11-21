<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>

<%@ page import="java.util.Collection,gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<title>Compesa - SGCQ</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
</head>

<body leftmargin="5" topmargin="5">

<html:form
  action="/removerImovelAction"
  name="ImovelOutrosCriteriosActionForm"
  type="gcom.gui.cadastro.imovel.ImovelOutrosCriteriosActionForm"
  method="post"
>

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
          <td class="parabg">Manter Imóvel</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
       </table>
            <table width="100%" cellpadding="0" cellspacing="0">
	      <tr>
                 <td colspan="4" height="23"><font color="#000000" style="font-size:10px" face="Verdana, Arial, Helvetica, sans-serif">
		   <strong>Imoveis Cadastrados:</strong></font></td>
              </tr>
              <tr>
                <td colspan="4" bgcolor="#000000" height="2"></td>
              </tr>
              <tr>
               <td>
                <table width="100%" bgcolor="#99CCFF">
                  <tr bordercolor="#000000">
                    <td width="67" bgcolor="#90c7fc" align="center"><strong>Matrícula</strong></td>
                    <td width="75" bordercolor="#000000" bgcolor="#90c7fc" align="center" > <div align="center"><strong>Inscrição</strong></div></td>
                    <td width="82" bgcolor="#90c7fc" align="center"><strong>Cliente Usuário</strong></td>
                    <td width="392" bgcolor="#90c7fc" align="center"><strong>Endereço</strong></td>
                  </tr>
		 </table>
		</td>
	       </tr>
               <tr>
                 <td> <%--<div style="width: 100%; height: 100%; overflow: auto;">--%>
                    <table width="100%" bgcolor="#99CCFF">
                      <%--Esquema de paginação--%>
                      <pg:pager maxIndexPages="10" export="currentPageNumber=pageNumber"
                        index="center" maxPageItems="10">
                      <pg:param name="pg"/>
                      <pg:param name="q"/>
                      <%int cont=0;%>
                      <logic:iterate name="colecaoImoveisExcluidosTarifaSocial" id="imovel">
                        <pg:item>
                          <%
                              cont = cont+1;
                              if (cont%2==0){%>
                                      <tr bgcolor="#FFFFFF">
                           <%}else{ %>
                                      <tr bgcolor="#cbe5fe">
                           <%}%>
                          <td width="67">
                            <div align="center">
                            	${imovel.matriculaImovel}
                            </div>
                          </td>
                          <td width="75">
                            <div align="center">
                            	${imovel.inscricaoFormatada}
                            </div>
                          </td>
                          <td width="82">
                              <html:link page="/consultarTarifaExcluidaDetalheAction.do"
                                         paramName="imovel"
                                         paramProperty="matriculaImovel"
                                         paramId="idRegistroAtualizacao">
                                ${imovel.clienteNome}
                              </html:link>
                          </td>
                          <td width="392">
                            <div align="center">
                            	${imovel.enderecoFormatado}
                            </div>
                          </td>
                        </tr>
                        </pg:item>
                      </logic:iterate>
                    </table>
  	             <table width="100%">
					<tr>
                     <td>
                        <input name="button" type="button" class="bottonRightCol" value="Novo Filtro" onclick="window.location.href='<html:rewrite page="/exibirFiltrarImovelOutrosCriteriosTarifaSocial.do?gerarRelatorio=consultarTarifaExcluida&limpar=S"/>'" align="left" style="width: 80px;">
        	   		 </td>
		      	     <td align="right">
		   	   			<img  border="0" src="<bean:message key="caminho.imagens"/>print.gif">
		            </td>
                   </tr>
                </table>
            <%if(((Collection)session.getAttribute("colecaoImoveisExcluidosTarifaSocial")).size() == ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA){%>
              <%@ include file="/jsp/util/limite_pesquisa.jsp"%>
            <%}%>

            <table width="100%" border="0">
              <tr>
                <td>
                  <strong><%@ include file="/jsp/util/indice_pager.jsp"%></strong>
                </td>
              </tr>
            </table>
          </pg:pager>
       </table>
       <p>&nbsp;</p>
    </td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
