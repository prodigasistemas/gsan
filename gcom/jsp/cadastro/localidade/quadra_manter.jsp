<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="gcom.cadastro.localidade.Quadra"%>

<%@ page import="gcom.util.Pagina, gcom.util.ConstantesSistema" %>
<%@ page import="java.util.Collection"%>

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
			document.forms[0].action = "/gsan/removerQuadraAction.do"
			document.forms[0].submit();
		 }
	}
}

//-->
</SCRIPT>


</head>

<body leftmargin="5" topmargin="5">


<html:form action="/removerQuadraAction" method="post">

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

        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Manter Quadra</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
		<tr>
			<td height="5" colspan="3"></td>
		</tr>
      </table>

	  <table width="590" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td colspan="4" height="23"><font color="#000000" style="font-size: 10px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Quadras
            encontradas:</strong></font></td>
 			<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroLocalizacaoQuadraManter', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
			</logic:present>
			<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
			</logic:notPresent>
		</tr>
	  </table>
			
	  <table width="100%" cellpadding="0" cellspacing="0">
        <tr>
            <td bgcolor="#000000" height="2"></td>
        </tr>
		<tr>
			<td>
				<table width="100%" bgcolor="#99CCFF">
				<tr bgcolor="#99CCFF">
					<td align="center" width="40"><A HREF="javascript:facilitador(this);" id="0"><strong>Todos</strong></A></td>
					<td align="center" width="40"><FONT COLOR="#000000"><strong>Quadra</strong></FONT></td>
					<td align="center" width="200"><FONT COLOR="#000000"><strong>Localidade</strong></FONT></td>
					<td align="center" width="100"><FONT COLOR="#000000"><strong>Código do Setor</strong></FONT></td>
					<td align="center" width="200"><FONT COLOR="#000000"><strong>Setor Comercial</strong></FONT></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
            <td>
			<!-- <div style="width: 100%; height: 100; overflow: auto;"> -->
            <table width="100%" bgcolor="#99CCFF">


		<% String cor = "#cbe5fe";%>

		<%--Esquema de paginação--%>
		<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
		export="currentPageNumber=pageNumber;pageOffset"
		maxPageItems="10" items="${sessionScope.totalRegistros}">
			<pg:param name="pg" />
			<pg:param name="q" />

		<logic:iterate name="colecaoQuadra" id="quadra" type="Quadra">
          <pg:item>

				<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
					cor = "#FFFFFF";%>
					<tr bgcolor="#FFFFFF" height="18">	
				<%} else{	
					cor = "#cbe5fe";%>
					<tr bgcolor="#cbe5fe" height="18">		
				<%}%>

				<logic:equal name="quadra" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">

				<td align="center" width="40"><html:checkbox property="quadraID" value="<%="" + quadra.getId()%>"/></td>
				<td align="center" width="40"><html:link href="/gsan/exibirAtualizarQuadraAction.do" paramId="idRegistroAtualizar" paramProperty="id" paramName="quadra" title="<%="" + quadra.getNumeroQuadra()%>"><bean:write name="quadra" property="numeroQuadra"/></html:link></td>
				<td width="200"><bean:write name="quadra" property="setorComercial.localidade.descricao"/></td>
				<td align="center" width="100"><bean:write name="quadra" property="setorComercial.codigo"/></td>
				<td width="200"><bean:write name="quadra" property="setorComercial.descricao"/></td>

				</logic:equal>

				<logic:notEqual name="quadra" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">

				<td align="center" width="40"><html:checkbox property="quadraID" value="<%="" + quadra.getId()%>"/></td>
				<td align="center" width="40"><html:link href="/gsan/exibirAtualizarQuadraAction.do" paramId="idRegistroAtualizar" paramProperty="id" paramName="quadra" title="<%="" + quadra.getNumeroQuadra()%>" style="color: #CC0000;"><bean:write name="quadra" property="numeroQuadra"/></html:link></td>
				<td width="200"><span style="color: #CC0000;"><bean:write name="quadra" property="setorComercial.localidade.descricao"/></span></td>
				<td align="center" width="100"><span style="color: #CC0000;"><bean:write name="quadra" property="setorComercial.codigo"/></span></td>
				<td width="200"><span style="color: #CC0000;"><bean:write name="quadra" property="setorComercial.descricao"/></span></td>

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
							<gsan:controleAcessoBotao name="Button" value="Remover"
							  onclick="javascript:verficarSelecao(quadraID);" url="removerQuadraAction.do"/>
							<!-- <input name="Button" type="button" class="bottonRightCol" value="Remover" onclick="verficarSelecao(quadraID)" align="left" style="width: 70px;">-->
								<input name="Button" type="button" class="bottonRightCol" value="Voltar Filtro" onclick="window.location.href='/gsan/exibirFiltrarQuadraAction.do?desfazer=N'" align="left" style="width: 80px;">
							</td>
		                     <td align="right" valign="top">
		                     	<a href="javascript:toggleBox('demodiv',1);">
		                             <img align="right" border="0" src="<bean:message key='caminho.imagens'/>print.gif"  title="Imprimir Quadras"/>
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
<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioQuadraManterAction.do"/>
<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>

</body>
</html:html>
