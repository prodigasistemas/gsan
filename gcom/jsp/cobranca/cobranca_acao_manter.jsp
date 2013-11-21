<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.cobranca.CobrancaAcao"%>
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
			document.forms[0].action = "/gsan/removerAcaoCobrancaAction.do"
			document.forms[0].submit();
		 }
	}
 }

//-->
</SCRIPT>


</head>

<body leftmargin="5" topmargin="5">


<html:form action="/exibirManterAcaoCobrancaAction" method="post">

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
          <td class="parabg">Manter Ação de Cobrança</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
		</table>



      <table width="590" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td colspan="4" height="23"><font color="#000000" style="font-size: 10px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Ações de Cobrança
            encontradas:</strong></font></td>
 			<td align="right"></td>					    		
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
					<td align="center" width="240"><FONT COLOR="#000000"><strong>Descrição</strong></FONT></td>
					<td align="center" width="75"><FONT COLOR="#000000"><strong>Obrig.</strong></FONT></td>
					<td align="center" width="75"><FONT COLOR="#000000"><strong>Repet.</strong></FONT></td>
					<td align="center" width="75"><FONT COLOR="#000000"><strong>Susp.</strong></FONT></td>
					<td align="center" width="75"><FONT COLOR="#000000"><strong>Taxa</strong></FONT></td>
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

			<logic:iterate name="colecaoCobrancaAcao" id="cobrancaAcao" type="CobrancaAcao">
          	<pg:item>

				<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
					cor = "#FFFFFF";%>
					<tr bgcolor="#FFFFFF" height="18">	
				<%} else{	
					cor = "#cbe5fe";%>
					<tr bgcolor="#cbe5fe" height="18">		
				<%}%>

				<logic:equal name="cobrancaAcao" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">

				<td align="center" width="50"><html:checkbox property="cobrancaAcaoSelectID" value="<%="" + cobrancaAcao.getId()%>"/></td>
				<td width="240"><html:link href="/gsan/exibirAtualizarAcaoCobrancaAction.do" paramId="idRegistroAtualizar" paramProperty="id" paramName="cobrancaAcao" title="<%="" + cobrancaAcao.getDescricaoCobrancaAcao() %>"><bean:write name="cobrancaAcao" property="descricaoCobrancaAcao"/></html:link></td>

				<!-- AÇÃO OBRIGATÓRIA -->
				<logic:present name="cobrancaAcao" property="indicadorObrigatoriedade">
				
					<logic:equal name="cobrancaAcao" property="indicadorObrigatoriedade" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">
						<td width="75">SIM</td>
					</logic:equal>
					
					<logic:notEqual name="cobrancaAcao" property="indicadorObrigatoriedade" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">
						<td width="75">NÃO</td>
					</logic:notEqual>
				
				</logic:present>
				
				<logic:notPresent name="cobrancaAcao" property="indicadorObrigatoriedade">
					<td width="75"></td>
				</logic:notPresent>
				
				<!-- AÇÃO QUE PODE SER REPETIDA -->
				<logic:present name="cobrancaAcao" property="indicadorRepeticao">
				
					<logic:equal name="cobrancaAcao" property="indicadorRepeticao" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">
						<td width="75">SIM</td>
					</logic:equal>
					
					<logic:notEqual name="cobrancaAcao" property="indicadorRepeticao" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">
						<td width="75">NÃO</td>
					</logic:notEqual>
				
				</logic:present>
				
				<logic:notPresent name="cobrancaAcao" property="indicadorRepeticao">
					<td width="75"></td>
				</logic:notPresent>
				
				<!-- AÇÃO QUE PROVOCA SUSPENSÃO -->
				<logic:present name="cobrancaAcao" property="indicadorSuspensaoAbastecimento">
				
					<logic:equal name="cobrancaAcao" property="indicadorSuspensaoAbastecimento" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">
						<td width="75">SIM</td>
					</logic:equal>
					
					<logic:notEqual name="cobrancaAcao" property="indicadorSuspensaoAbastecimento" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">
						<td width="75">NÃO</td>
					</logic:notEqual>
				
				</logic:present>
				
				<logic:notPresent name="cobrancaAcao" property="indicadorSuspensaoAbastecimento">
					<td width="75"></td>
				</logic:notPresent>
				
				<!-- AÇÃO QUE GERA TAXA -->
				<logic:present name="cobrancaAcao" property="indicadorGeracaoTaxa">
				
					<logic:equal name="cobrancaAcao" property="indicadorGeracaoTaxa" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">
						<td width="75">SIM</td>
					</logic:equal>
					
					<logic:notEqual name="cobrancaAcao" property="indicadorGeracaoTaxa" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">
						<td width="75">NÃO</td>
					</logic:notEqual>
				
				</logic:present>
				
				<logic:notPresent name="cobrancaAcao" property="indicadorGeracaoTaxa">
					<td width="75"></td>
				</logic:notPresent>
				
				</logic:equal>
				
				
				<!-- =============================================================================================== -->
				<!-- =============================================================================================== -->
				
				
				<logic:notEqual name="cobrancaAcao" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">

				<td align="center" width="50"><html:checkbox property="cobrancaAcaoSelectID" value="<%="" + cobrancaAcao.getId()%>"/></td>
				<td width="240"><html:link href="/gsan/exibirAtualizarCobrancaAcaoAction.do" paramId="idRegistroAtualizar" paramProperty="id" paramName="cobrancaAcao" title="<%="" + cobrancaAcao.getDescricaoCobrancaAcao() %>" style="color: #CC0000;"><bean:write name="cobrancaAcao" property="descricaoCobrancaAcao"/></html:link></td>
				
				<!-- AÇÃO OBRIGATÓRIA -->
				<logic:present name="cobrancaAcao" property="indicadorObrigatoriedade">
				
					<logic:equal name="cobrancaAcao" property="indicadorObrigatoriedade" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">
						<td width="75"><span style="color: #CC0000;">SIM</span></td>
					</logic:equal>
					
					<logic:notEqual name="cobrancaAcao" property="indicadorObrigatoriedade" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">
						<td width="75"><span style="color: #CC0000;">NÃO</span></td>
					</logic:notEqual>
				
				</logic:present>
				
				<logic:notPresent name="cobrancaAcao" property="indicadorObrigatoriedade">
					<td width="75"></td>
				</logic:notPresent>
				
				<!-- AÇÃO QUE PODE SER REPETIDA -->
				<logic:present name="cobrancaAcao" property="indicadorRepeticao">
				
					<logic:equal name="cobrancaAcao" property="indicadorRepeticao" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">
						<td width="75"><span style="color: #CC0000;">SIM</span></td>
					</logic:equal>
					
					<logic:notEqual name="cobrancaAcao" property="indicadorRepeticao" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">
						<td width="75"><span style="color: #CC0000;">NÃO</span></td>
					</logic:notEqual>
				
				</logic:present>
				
				<logic:notPresent name="cobrancaAcao" property="indicadorRepeticao">
					<td width="75"></td>
				</logic:notPresent>
				
				<!-- AÇÃO QUE PROVOCA SUSPENSÃO -->
				<logic:present name="cobrancaAcao" property="indicadorSuspensaoAbastecimento">
				
					<logic:equal name="cobrancaAcao" property="indicadorSuspensaoAbastecimento" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">
						<td width="75"><span style="color: #CC0000;">SIM</span></td>
					</logic:equal>
					
					<logic:notEqual name="cobrancaAcao" property="indicadorSuspensaoAbastecimento" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">
						<td width="75"><span style="color: #CC0000;">NÃO</span></td>
					</logic:notEqual>
				
				</logic:present>
				
				<logic:notPresent name="cobrancaAcao" property="indicadorSuspensaoAbastecimento">
					<td width="75"></td>
				</logic:notPresent>
				
				<!-- AÇÃO QUE GERA TAXA -->
				<logic:present name="cobrancaAcao" property="indicadorGeracaoTaxa">
				
					<logic:equal name="cobrancaAcao" property="indicadorGeracaoTaxa" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">
						<td width="75"><span style="color: #CC0000;">SIM</span></td>
					</logic:equal>
					
					<logic:notEqual name="cobrancaAcao" property="indicadorGeracaoTaxa" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">
						<td width="75"><span style="color: #CC0000;">NÃO</span></td>
					</logic:notEqual>
				
				</logic:present>
				
				<logic:notPresent name="cobrancaAcao" property="indicadorGeracaoTaxa">
					<td width="75"></td>
				</logic:notPresent>
				
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
				<gsan:controleAcessoBotao name="Button" value="Remover" onclick="verficarSelecao(cobrancaAcaoSelectID)" url="removerAcaoCobrancaAction.do" align="left"/> 
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
			<div align="center"><strong>
				<%@ include file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
		</td>
		
		</pg:pager>
		<%-- Fim do esquema de paginação --%>
	</tr>

	</table>


	</td>
  </tr>
</table>

<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioCobrancaAtividadeManterAction.do"/>
<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>

</body>
</html:html>

