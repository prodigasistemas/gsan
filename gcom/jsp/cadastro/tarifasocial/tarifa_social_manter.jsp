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
<script>
<!--
function facilitador(objeto){
	if (objeto.value == "0"){
		objeto.value = "1";
		marcarTodos();
	}
	else{
		objeto.value = "0";
		desmarcarTodos();
	}
}

function remover(motivo){
//	alert(motivo)

	document.forms[0].acao.value = 'remover';
	document.forms[0].tarifaSocialExclusaoMotivo.value = motivo;
	document.forms[0].submit();
}

function abrirRemover(){

	if (CheckboxNaoVazio(document.forms[0].idRegistrosRemocao)) {
		if  (confirm('Confirma remoção ?')){
			abrirPopupDeNome('ConsultaImovelTarifaSocialAction.do?acao=exibirRemover', 190, 400, 'remover','yes');
		}
	}
}
-->
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/ConsultaImovelTarifaSocialAction" method="post">
<html:hidden property="acao" />
<html:hidden property="tarifaSocialExclusaoMotivo" />


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
          <td class="parabg">Manter Tarifa Social</td>
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
                    <td width="6%" bgcolor="#90c7fc" align="center"><strong><strong><a
								href="javascript:facilitador(this);">Todos</a></strong></strong></td>
                    <td width="5%" bordercolor="#000000" bgcolor="#90c7fc" align="center" > <div align="center"><strong>Matrícula</strong></div></td>
                    <td width="22%" bordercolor="#000000" bgcolor="#90c7fc" align="center" > <div align="center"><strong>Inscrição</strong></div></td>
                    <td width="17%" bordercolor="#000000" bgcolor="#90c7fc" align="center" > <div align="center"><strong>Cliente Usuário</strong></div></td>
                    <td width="50%" bgcolor="#90c7fc" align="center"><strong>Endereço Imóvel</strong></td>
                  </tr>
		 </table>
		</td>
	       </tr>
               <tr>
                 <td> <%--<div style="width: 100%; height: 100%; overflow: auto;">--%>
                    <table width="100%" bgcolor="#99CCFF">
                      <%--Esquema de paginação--%>
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
                      <pg:param name="pg"/>
                      <pg:param name="q"/>
                      <%int cont=0;%>
<logic:present name="ConsultaImovelTarifaSocialActionForm" property="collObjeto">
<logic:iterate name="ConsultaImovelTarifaSocialActionForm" property="collObjeto" id="imovelCliente">
                      <bean:define id="imovel" name="imovelCliente" property="imovel" />
                      <bean:define id="cliente" name="imovelCliente" property="cliente" />
                        <pg:item>
                          <%
                              cont = cont+1;
                              if (cont%2==0){%>
                                      <tr bgcolor="#cbe5fe">
                           <%}else{ %>
                                      <tr bgcolor="#FFFFFF">
                           <%}%>
                          <td width="6%">
                            <div align="center">
                              <input type="checkbox" name="idRegistrosRemocao" value="<bean:write name="imovel" property="id"/>"/>
                            </div>
                          </td>
                          <td width="5%">
                            <div align="center">
                            <html:link page="/MostrarDadosTarifaSocialAction.do?acao=exibir"
                                         paramName="imovel"
                                         paramProperty="id"
                                         paramId="idRegistroAtualizacao">
                                <bean:write name="imovel" property="id"/>
                            </html:link>
                            </div>
                          </td>
                          <td width="22%">&nbsp;<bean:write name="imovel" property="inscricaoFormatada"/>
                          </td>
                          <td width="17%">&nbsp;<bean:write name="cliente" property="nome"/>
                          </td>
                          <td width="50%">
                                <bean:write name="imovel" property="enderecoFormatado"/>
                          </td>
                        </tr>
                        </pg:item>
</logic:iterate>
</logic:present>
                    </table>
  	             <table width="100%">
			<tr>
                           <td>
                               <input type="button" class="bottonRightCol" value="Remover" align="left" onclick="javascript:abrirRemover();" style="width: 70px;">
                               <input name="button" type="button" class="bottonRightCol" value="Voltar Filtro" onclick="window.location.href='<html:rewrite page="/ConsultaImovelTarifaSocialAction.do?acao=exibir"/>'" align="left" style="width: 80px;">
        		   </td>
		   	   <td align="right">
                        <%--       <img src="<bean:message key="caminho.imagens"/>print.gif"> --%> &nbsp;
		            </td>
                	</tr>
                      </table>
            <table width="100%" border="0">
              <tr>
                <td align="center">
                  <strong><%@ include
								file="/jsp/util/indice_pager_novo.jsp"%></strong>
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
