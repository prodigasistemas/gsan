<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@page import="gcom.util.ConstantesSistema"%>
<%@page import="gcom.cadastro.imovel.EntidadeBeneficente"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

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

function remover(objeto){

	if (CheckboxNaoVazio(objeto)){
		if (confirm ("Confirma remoção?")) {
			document.forms[0].action = "/gsan/removerClienteAction.do"
			document.forms[0].submit();
		 }
	}
}
function anoMesParaMesAnoComBarra( anoMes ){

var ano = anoMes.substring(0,4);
var mes = anoMes.substring(4);
var mesAnoCombarra = mes+"/"+ano;
return mesAnoCombarra; 
}

</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/removerEntidadeBeneficenteAction.do" 
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post"
	onsubmit="return CheckboxNaoVazio(document.ManutencaoRegistroActionForm.idRegistrosRemocao) && confirm('Confirmar exclusao?')" >

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
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Manter Entidade Beneficente</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>
	
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4" height="23">
						<font color="#000000" style="font-size:10px" face="Verdana, Arial, Helvetica, sans-serif">
						<strong>Entidades Beneficente Cadastradas:</strong>
						</font>
					</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroClienteManter', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
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
							<!--header da tabela interna -->
							<tr bgcolor="#99CCFF">
							
								<td width="5%">
								<div align="center">
								<strong><a href="javascript:facilitador(this);" id="0">Todos</a></strong>
								</div>
								</td>
								
								<td width="7%" align="center">
									<strong>C&oacute;digo</strong>
								</td>
								
								<td width="26%" align="center">
									<strong>Cliente</strong>
								</td>

								<td width="22%" align="center">
									<strong>Empresa</strong>
								</td>
								
								<td width="13%" align="center">
									<strong>In&iacute;cio Ades&atilde;o</strong>
								</td>			
								
								<td width="10%" align="center">
									<strong>Fim Ades&atilde;o</strong>
								</td>													

							</tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<td>
						<table width="100%" bgcolor="#99CCFF">
						<!--Esquema de paginação-->
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"				
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg"/>
							<pg:param name="q"/>
							<logic:present name="colecaoEntidadeBeneficente">
								<%int cont = 0;%>
								<logic:iterate name="colecaoEntidadeBeneficente" id="entidadeBeneficente" type="EntidadeBeneficente">
									<pg:item>
										<%cont = cont + 1;
										if (cont % 2 == 0) {
										%>
										<tr bgcolor="#cbe5fe">
										<%
										} else {
										%>
										<tr bgcolor="#FFFFFF">
										<%
										}
										%>
										<td width="5%">
										<div align="center"><input type="checkbox"
										name="idRegistrosRemocao"
										value="<bean:write name="entidadeBeneficente" property="id"/>"></div>
										</td>
										
										<td width="7%" align="center">
										<logic:notEqual name="entidadeBeneficente" property="indicadorUso"
										value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
										<html:link
										href="/gsan/exibirAtualizarEntidadeBeneficenteAction.do?manter=sim"
										paramId="idEntidadeBeneficente" paramProperty="id"
										paramName="entidadeBeneficente"
										title=""
										>
										<bean:write name="entidadeBeneficente" property="id" />
										</html:link>
										</logic:notEqual> 
										
										<logic:equal name="entidadeBeneficente"	property="indicadorUso"
										value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
										<font color="#CC0000"><bean:write name="entidadeBeneficente" property="id" /></font>
										</logic:equal>
										</td>
										
										<td width="26%">
										<div align="center">
										<bean:write name="entidadeBeneficente" property="cliente.nome"/>
										</div>
										</td>
										
										<td width="22%">
										<div align="center">
										<bean:write name="entidadeBeneficente" property="empresa.descricao"/>
										</div>
										</td>
										
										<td width="13%">
										<div align="center">
										<bean:write name="entidadeBeneficente" property="inicioMesAnoAdesao2"/>
										</div>
										</td>
										
										<td width="10%">
										<div align="center">
										<bean:write name="entidadeBeneficente" property="fimMesAnoAdesao2"/>
										</div>
										</td>

											
										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
						<!-- Fim do esquema de paginação -->
						</table>
						
						
						<table width="100%" border="0">
								<tr>
									<td align="center"><strong><%@ include
										file="/jsp/util/indice_pager_novo.jsp"%></strong>
									</td>
								</tr>
								
								<tr>
									<td>
									<font color="#FF0000"> 
									<html:submit
										property="buttonRemover" styleClass="bottonRightCol"
										value="Remover" />
									</font> 
										
										<input name="button" type="button"
										class="bottonRightCol" value="Voltar Filtro"
										onclick="window.location.href='/gsan/exibirFiltrarEntidadeBeneficenteAction.do?limpar=sim'"
										align="left" style="width: 80px;">
									</td>
								</tr>
								
						
						</table>
						
				
					</td>
				</tr>
			</table>		
		</pg:pager>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>


 
<%@ include file="/jsp/util/rodape.jsp"%>

</body>
</html:form>
</html:html>