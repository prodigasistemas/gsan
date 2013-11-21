<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.cadastro.localidade.Localidade"%>
<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>

<SCRIPT LANGUAGE="JavaScript">
<!--

function facilitador(objeto){
	if (objeto.id == "0" || objeto.id == undefined){
		objeto.id = "1";
		marcarTodos();
	}
	else{
		objeto.id = "0";
		desmarcarTodos();
	}
}

// Verifica se há item selecionado
function verficarSelecao(objeto){

	if (CheckboxNaoVazio(objeto)){
		if (confirm ("Confirma remoção?")) {
			document.forms[0].action = "/gsan/removerLocalidadeAction.do"
			document.forms[0].submit();
		 }
	}
 }

//-->
</SCRIPT>


</head>

<body leftmargin="5" topmargin="5">


<html:form action="/removerLocalidadeAction" method="post">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="115" valign="top" class="leftcoltext">
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
      	</div>
      	</td>

	<td valign="top" class="centercoltext">

        <p>&nbsp;</p>

		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Manter Localidade</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
		</table>



      <table width="590" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td colspan="4" height="23"><font color="#000000" style="font-size: 10px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Localidades
            encontradas:</strong></font></td>
 			<logic:present scope="application" name="urlHelp">
					<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroLocalizacaoLocalidadeManter', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
			</logic:present>
			<logic:notPresent scope="application" name="urlHelp">
					<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
			</logic:notPresent>
        </tr>
        </table>
        <table width="590" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td bgcolor="#000000" height="2"></td>
        </tr>
		<tr>
			<td>
				<table width="590" bgcolor="#99CCFF">
				<tr bgcolor="#99CCFF">
					<td align="center" width="50"><A HREF="javascript:facilitador(this);" id="0"><strong>Todos</strong></A></td>
					<td align="center" width="50"><FONT COLOR="#000000"><strong>Gerência Regional</strong></FONT></td>
					<td align="center" width="50"><FONT COLOR="#000000"><strong>Unidade Negócio</strong></FONT></td>
					<td align="center" width="100"><FONT COLOR="#000000"><strong>Código</strong></FONT></td>
					<td align="center" width="340"><FONT COLOR="#000000"><strong>Nome</strong></FONT></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
            <td>
			<!-- <div style="width: 570; height: 100; overflow: auto;"> -->
            <table width="590" bgcolor="#99CCFF">

		<% String cor = "#cbe5fe";%>

        <%--Esquema de paginação--%>
		<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
		export="currentPageNumber=pageNumber;pageOffset"
		maxPageItems="10" items="${sessionScope.totalRegistros}">
			<pg:param name="pg" />
			<pg:param name="q" />

		<logic:iterate name="colecaoLocalidade" id="localidade" type="Localidade">
          <pg:item>

				<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
					cor = "#FFFFFF";%>
					<tr bgcolor="#FFFFFF" height="18">	
				<%} else{	
					cor = "#cbe5fe";%>
					<tr bgcolor="#cbe5fe" height="18">		
				<%}%>

				<logic:equal name="localidade" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">

				<td align="center" width="50"><html:checkbox property="localidadeSelectID" value="<%="" + localidade.getId()%>"/></td>
				<td align="center" width="50"><bean:write name="localidade" property="gerenciaRegional.nomeAbreviado"/></td>
				<td align="center" width="50"><bean:write name="localidade" property="unidadeNegocio.nomeAbreviado"/></td>				
				<td align="center" width="100"><bean:write name="localidade" property="id"/></td>
				<td width="340"><html:link href="/gsan/exibirAtualizarLocalidadeAction.do" paramId="idRegistroAtualizar" paramProperty="id" paramName="localidade" title="<%="" + localidade.getDescricao()%>"><bean:write name="localidade" property="descricao"/></html:link></td>

				</logic:equal>

				<logic:notEqual name="localidade" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">

				<td align="center" width="50"><html:checkbox property="localidadeSelectID" value="<%="" + localidade.getId()%>"/></td>
				<td align="center" width="50"><span style="color: #CC0000;"><bean:write name="localidade" property="gerenciaRegional.nomeAbreviado"/></span></td>
				<td align="center" width="50"><span style="color: #CC0000;"><bean:write name="localidade" property="unidadeNegocio.nomeAbreviado"/></span></td>				
				<td align="center" width="100"><span style="color: #CC0000;"><bean:write name="localidade" property="id"/></span></td>
				<td width="340"><html:link href="/gsan/exibirAtualizarLocalidadeAction.do" paramId="idRegistroAtualizar" paramProperty="id" paramName="localidade" title="<%="" + localidade.getDescricao()%>" style="color: #CC0000;"><bean:write name="localidade" property="descricao"/></html:link></td>
				</logic:notEqual>

			</tr>


			</pg:item>
		</logic:iterate>
			</table><!-- </div> -->

				</td>
			</tr>
			<tr bordercolor="#90c7fc">
				<td>
                                <table width="100%">
                                <tr>
                                	<td>
										<%--<input name="Button" type="button" class="bottonRightCol" value="Remover" onclick="verficarSelecao(localidadeSelectID)" align="left" style="width: 70px;">--%>
										 <gsan:controleAcessoBotao name="Button" value="Remover" onclick="verficarSelecao(localidadeSelectID)" url="removerLocalidadeAction.do" align="left"/> 
										<input name="Button" type="button" class="bottonRightCol" value="Voltar Filtro" onclick="window.location.href='/gsan/exibirFiltrarLocalidadeAction.do?desfazer=N'" align="left" style="width: 80px;">
									</td>
                                    <td align="right" valign="top">
                                    	<a href="javascript:toggleBox('demodiv',1);">
                                            <img align="right" border="0" src="<bean:message key='caminho.imagens'/>print.gif"  title="Imprimir Localidades"/>
                                            </a>
                                    </td>
                                  </tr>
                                  </table>
                              </td>
                      	</tr>
		</table>


			<table width="100%" border="0">

				<tr>
					<td>
					<div align="center"><strong><%@ include
						file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
					</td>
					</pg:pager>
					<%-- Fim do esquema de paginação --%>
				</tr>

			</table>


	</td>
  </tr>
</table>
<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioLocalidadeManterAction.do"/>
<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>

</body>
</html:html>
