<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@page import="gcom.seguranca.acesso.Operacao"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script>
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
			document.forms[0].action = "/gsan/removerOperacaoAction.do"
			document.forms[0].submit();
		 }
	}
 }

-->
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/removerOperacaoAction"
		   name="ManutencaoRegistroActionForm"
		   type="gcom.gui.ManutencaoRegistroActionForm" 
		   method="post"
		   onsubmit="return CheckboxNaoVazio(document.ManutencaoRegistroActionForm.idRegistrosRemocao) && confirm('Confirmar exclusao?')">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp"%>

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
	  </div>
	</td>
	<td width="625" valign="top" class="centercoltext">
	  <table height="100%">
		<tr>
		  <td></td>
		</tr>
	  </table>

	  <table>
		<tr>
		  <td></td>
		</tr>
	  </table>
	  
	  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	    <tr>
		  <td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
		  <td class="parabg">Manter Operação</td>
		  <td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif" /></td>
		</tr>
	  </table>

	  <table width="100%" cellpadding="0" cellspacing="0">
		<tr>
		  <td colspan="7" height="23">
		    <font style="font-size: 10px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif"> 
		      <strong>Operações Cadastradas:</strong> 
		    </font>
		  </td>
		</tr>
		<tr>
		  <td colspan="7" bgcolor="#000000" height="2" valign="baseline"></td>
		</tr>
		<tr>
		  <td>
			<table width="100%" align="center" bgcolor="#90c7fc" border="0" cellpadding="0" cellspacing="0">
			  <tr bgcolor="#cbe5fe">
				<td width="100%" align="center">
				  <table width="100%" bgcolor="#90c7fc">
					<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
					  <td>
						<div align="center">
						  <strong>
						    <a href="javascript:facilitador(this);" id="0">Todos</a>
						  </strong>
						</div>
					  </td>

					  <td>
						<div align="center">
						  <strong>Descrição da Operação</strong>
						</div>
					  </td>

					  <td>
						<div align="center">
						  <strong>Argumento de Pesquisa</strong>
						</div>
					  </td>

					  <td>
						<div align="center">
						  <strong>Tipo da Operação</strong>
						</div>
					  </td>
					  
					  <td>
						<div align="center">
						  <strong>Funcionalidade</strong>
						</div>
					  </td>
					</tr>
					
					<%--inicio do esquema de paginação--%>
					<pg:pager isOffset="true" index="half-full" maxIndexPages="10" export="currentPageNumber=pageNumber;pageOffset"	maxPageItems="10" items="${sessionScope.totalRegistros}">
					  <pg:param name="pg" />
					  <pg:param name="q" />
						<%int cont = 0;%>
						  <logic:iterate name="colecaoOperacao" id="operacao" type="Operacao">
							<pg:item>
							  <%cont = cont + 1;
							  if (cont % 2 == 0) {%>
								<tr bgcolor="#FFFFFF">
							  <%} else {%>
								<tr bgcolor="#cbe5fe">
							  <%}%>
								  <td>
									<div align="center">
									  <input type="checkbox" name="idRegistrosRemocao" value="<bean:write name="operacao" property="id"/>">
									</div>
								  </td>

								  <td align="center">
								    <html:link href="/gsan/exibirAtualizarOperacaoAction.do?manter=sim"
											   paramId="idRegistroAtualizar" 
											   paramProperty="id"
											   paramName="operacao"
											   title="<%="" + operacao.getDescricao()%>">
											   <bean:write name="operacao" property="descricao" />
									</html:link>
								  </td>

										  
								  <td>
									<div align="center">
									  <logic:empty name="operacao" property="tabelaColuna">
									  	&nbsp;
									  </logic:empty>
									  <logic:notEmpty name="operacao" property="tabelaColuna">									  
									    <bean:write name="operacao" property="tabelaColuna.coluna" />
									  </logic:notEmpty>
									</div>
								  </td>

								  <td>
									<div align="center">
									  <bean:write name="operacao" property="operacaoTipo.descricao" />
									</div>
								  </td>
								  
								  <td>
									<div align="center">
									  <bean:write name="operacao" property="funcionalidade.descricao" />
									</div>
								  </td>
								  
								</tr>
							</pg:item>
						  </logic:iterate>
						</table>
						
						<table width="100%" border="0">
						  <tr>
							<td align="center"><strong><%@ include file="/jsp/util/indice_pager_novo.jsp"%></strong></td>
						  </tr>
					</pg:pager>
						  <tr>
							<td>
							  <font color="#FF0000"> 
							    <%--Botão de controle de acesso --%>
							    <gsan:controleAcessoBotao name="Button" value="Remover" onclick="verficarSelecao(idRegistrosRemocao)" url="removerOperacaoAction.do" align="left"/>
							  </font> 
							  <input name="button" 
							     	 type="button" 
							     	 class="bottonRightCol" 
							     	 value="Voltar Filtro"
									 onclick="window.location.href='<html:rewrite page="/exibirFiltrarOperacaoAction.do?limpar=S"/>'"
									 align="left" 
									 style="width: 80px;">
							</td>
							<td align="right" valign="top">
                              <a href="javascript:toggleBox('demodiv',1);">
                                <img align="right" border="0" src="<bean:message key='caminho.imagens'/>print.gif" title="Imprimir Operações"/>
                              </a>
                            </td>
						  </tr>
						</table>
					  </td>
					</tr>
 				  </table>
				  
				  <p>&nbsp;</p>
					
				</td>
			  </tr>
			</table>
		  </td>
		</tr>
	 </table>
	 
<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioOperacaoManterAction.do"/>	 
<%@ include file="/jsp/util/rodape.jsp"%>

</body>
</html:form>
</html:html>