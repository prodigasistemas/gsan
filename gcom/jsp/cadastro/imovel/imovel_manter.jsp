<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="gcom.cadastro.imovel.Imovel"%>
<%@ page import="gcom.cadastro.cliente.ClienteImovel"%>

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
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

function  remover(){
	if(CheckboxNaoVazio(document.ManutencaoRegistroActionForm.idRegistrosRemocao) && confirm('Confirma remoção ?')){
	    var form = document.forms[0];
    	form.action = "removerImovelAction.do";
	    form.submit()	
	}
	
}

-->
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/removerImovelAction"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post"
	onsubmit="">

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
			<td width="660" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Manter Imóvel</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4" height="23"><font color="#000000"
						style="font-size:10px"
						face="Verdana, Arial, Helvetica, sans-serif"> <strong>Imoveis
					Cadastrados:</strong></font></td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroImovelManter', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>	
				</tr>
			</table>	
			<table width="100%" cellpadding="0" cellspacing="0">	
				<tr>
					<td colspan="4" bgcolor="#000000" height="2"></td>
				</tr>
				<tr>
					<td>
					<table width="100%" bgcolor="#99CCFF">
						<tr bordercolor="#000000">
							<td width="50" bgcolor="#90c7fc" align="center"><strong><a
								href="javascript:facilitador(this);">Todos</a></strong></td>
							<td width="67" bordercolor="#000000" bgcolor="#90c7fc"
								align="center">
							<div align="center"><strong>Matrícula</strong></div>
							</td>
							<td width="482" bgcolor="#90c7fc" align="center"><strong>Endereço
							Imóvel</strong></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td><%--<div style="width: 100%; height: 100%; overflow: auto;">--%>
					<table width="100%" bgcolor="#99CCFF">
						<%--Esquema de paginação--%>
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">

							<pg:param name="pg" />
							<pg:param name="q" />
							<%int cont = 0;%>
							<logic:iterate name="imoveisFiltrados" id="clienteImovel">
								<pg:item>
									<%cont = cont + 1;
			if (cont % 2 == 0) {%>
									<tr bgcolor="#cbe5fe">
										<%} else {

			%>
									<tr bgcolor="#FFFFFF">
										<%}%>
										<td width="50">
										<%
  										 String data = "";
 										 String id = "";
										 if(((ClienteImovel)clienteImovel).getImovel().getUltimaAlteracao() != null){
											data = new Long(((ClienteImovel)clienteImovel).getImovel().getUltimaAlteracao().getTime()).toString();	 
										 }	
										 
										 id = ((ClienteImovel)clienteImovel).getImovel().getId().toString();
										 
										%>
										
									
										<div align="center"><input type="checkbox"
											name="idRegistrosRemocao"
											value=<%=id+"-"+data%> />
											
											
											</div>
										</td>
										<td width="67">
										<div align="center"> <bean:define name="clienteImovel"
											property="imovel" id="imovel" />  <bean:write name="imovel" property="id" />
										</div>
										</td>
										<td width="482"><bean:define name="clienteImovel"
											property="imovel" id="imovel" /> <html:link
											page="/exibirAtualizarImovelAction.do" paramName="imovel"
											paramProperty="id" paramId="idRegistroAtualizacao">
											<bean:write name="imovel" property="enderecoFormatado" />
											
										</html:link></td>
										
									</tr>
								</pg:item>
							</logic:iterate>
					</table>
					<table width="100%">
						<tr>
							<td>
							<gsan:controleAcessoBotao name="Button" value="Remover"
							  onclick="javascript:remover();" url="removerImovelAction.do"/><!--
							<input name="botaoSubmit" type="button" class="bottonRightCol"
								value="Remover" align="left" style="width: 70px;" onclick="javascript:remover();">--> <input
								name="button" type="button" class="bottonRightCol"
								value="Voltar Filtro"
								onclick="window.location.href='<html:rewrite page="/exibirFiltrarImovelAction.do"/>'"
								align="left" style="width: 80px;"></td>
								<td align="right" valign="top"><a
								href="javascript:toggleBox('demodiv',1);"> <img align="right"
								border="0" src="<bean:message key='caminho.imagens'/>print.gif"
								title="Imprimir Imóveis" /> </a></td>
						</tr>
					</table>
					<table width="100%" border="0">
						<tr>
							<td>
							<div align="center"><strong><%@ include
								file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
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
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioImovelManterAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>

