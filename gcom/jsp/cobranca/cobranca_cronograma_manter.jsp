<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="java.util.Collection,gcom.util.ConstantesSistema" %>

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

function remover(objeto){

	if (CheckboxNaoVazio(objeto)){
		if (confirm ("Confirma remoção?")) {
			document.forms[0].action = "/gsan/removerCobrancaCronogramaAction.do"
			document.forms[0].submit();
		 }
	}
}
-->
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form
  action="/removerCobrancaCronogramaAction"
  name="CobrancaActionForm"
  type="gcom.gui.CobrancaActionForm"
  method="post"
  onsubmit="return CheckboxNaoVazio(document.CobrancaActionForm.idRegistrosRemocao) && confirm('Confirma a(s) remoção(ões)?')"
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
          <td class="parabg">Manter Cronograma de Cobranca</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
       </table>
            <table width="100%" cellpadding="0" cellspacing="0">
	      <tr>
                 <td colspan="4" height="23"><font color="#000000" style="font-size:10px" face="Verdana, Arial, Helvetica, sans-serif">
		   <strong>Cronograma(s) de Cobrança Cadastrado(s):</strong></font></td>
              </tr>
              <tr>
                <td colspan="4" bgcolor="#000000" height="2"></td>
              </tr>
              <tr>
               <td>
                <table width="100%" bgcolor="#99CCFF">
                  <tr bordercolor="#000000">
                    <td width="50" bgcolor="#90c7fc" align="center"><strong><a href="javascript:facilitador(this);">Todos</a></strong></td>
                    <td width="382" bgcolor="#90c7fc" align="center"><strong>Grupo</strong></td>
                    <td width="100" bgcolor="#90c7fc" align="center"><strong>Mês/Ano</strong></td>
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
							<pg:param name="q" />
                      <%int cont=0;%>
                      <logic:iterate name="colecaoCobrancaCronograma" id="cobrancaGrupoCronogramaMensal">
                        <pg:item>
                          <%
                              cont = cont+1;
                              if (cont%2==0){%>
                                      <tr bgcolor="#cbe5fe">
                           <%}else{ %>
                                      <tr bgcolor="#FFFFFF">
                           <%}%>
                          <td width="50">
                            <div align="center">
                              <input type="checkbox" name="idRegistrosRemocao" value="<bean:write name="cobrancaGrupoCronogramaMensal" property="id"/>"/>
                            </div>
                          </td>
                          <td width="382">
                              <html:link page="/exibirAtualizarCobrancaCronogramaAction.do"
                                         paramName="cobrancaGrupoCronogramaMensal"
                                         paramProperty="id"
                                         paramId="idRegistroAtualizacao">
                                ${cobrancaGrupoCronogramaMensal.cobrancaGrupo.descricao} &nbsp;
                              </html:link>
                          </td>
                          <td width="100">
                            <div align="center">
                                ${cobrancaGrupoCronogramaMensal.mesAno} &nbsp;
                            </div>
                          </td>
                        </tr>
                        </pg:item>
                      </logic:iterate>
                    </table>
  	             <table width="100%">
			<tr>
                   <td>
                     <gsan:controleAcessoBotao name="removerCronograma" value="Remover" onclick="remover(idRegistrosRemocao);" url="removerCobrancaCronogramaAction.do"/>
                     <input name="button" type="button" class="bottonRightCol" value="Voltar Filtro" onclick="window.location.href='<html:rewrite page="/exibirFiltrarCobrancaCronogramaAction.do"/>'" align="left" style="width: 80px;">
        		   </td>
		   	   <td align="right">
                              <div align="right"><a href="javascript:toggleBox('demodiv',1);">
							<img border="0"
								src="<bean:message key="caminho.imagens"/>print.gif"
								title="Imprimir Cronogramas de Cobrança" /> </a></div>
		            </td>
                	</tr>
                      </table>
            <table width="100%" border="0">
              <tr>
                <td align="center">
                  <strong><div align="center"><%@ include
						file="/jsp/util/indice_pager_novo.jsp"%></div></strong>
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
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioCronogramaCobrancaManterAction.do" />
<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
