<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@page import="gcom.cadastro.cliente.ClienteTipo"%>
<%@page import="gcom.util.ConstantesSistema"%>

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

	if (CheckboxRemoverNaoVazio(objeto)){
		if (confirm ("Confirma remoção?")) {
			document.forms[0].action = "/gsan/removerProcessoIniciadoAction.do"
			document.forms[0].submit();
		 }
	}
}

function autorizar(objeto){

	if (CheckboxAutorizarNaoVazio(objeto)){
		if (confirm ("Confirma autorização?")) {
			document.forms[0].action = "/gsan/autorizarProcessoIniciadoAction.do"
			document.forms[0].submit();
		 }
	}
}

function consultarf(){
	var form = document.forms[0];


		if (validaData(form.dataInicial) && validaData(form.dataFinal)) {
		
			if(form.dataInicial.value != "" && form.dataFinal.value != "") {
				form.action = '/gsan/exibirAutorizarRelatoriosBatchAction.do?dataInicialSelecionado='+form.dataInicial.value+'&dataFinalSelecionado='+form.dataFinal.value;
				form.submit();
			}
			
		}
}
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/exibirAutorizarRelatoriosBatchAction" 
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post">

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
					<td class="parabg">Autorizar Relat&oacute;rios/Processos Batch</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>
	
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td><strong>Data:</strong> </td>
					<td colspan="1" height="23">
					<input type="text" onkeyup="mascaraData(this, event);somente_numero(this);" value="<%=request.getAttribute("dataInicial")%>" size="10" maxlength="10" name="dataInicial"/>
					<strong>&agrave;</strong>					
					<input type="text" onkeyup="mascaraData(this, event);somente_numero(this);" value="${requestScope.dataFinal}" size="10" maxlength="10" name="dataFinal"/>
					<strong>(dd/mm/aaaa)</strong>
					&nbsp;
					<input type="button" class="bottonRightCol" align="left" value="Consultar" name="consultar" onclick="javascript:consultarf();"/>				
					</td>
				</tr>
				<tr>
					<td colspan="4" height="23">
						<font color="#000000" style="font-size:10px" face="Verdana, Arial, Helvetica, sans-serif">
						<strong>Relat&oacute;rios/Processos Encontrados:</strong>
						</font>
					</td>
					<!-- HELP Processamento dos relatorios Bacth 
					<td align="right"><a href="javascript: abrirPopup('/gsan/help/help.jsp?mapIDHelpSet=clienteManter', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
					 -->
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
								<td width="49" align="center">
									<strong>Remover</strong>
								</td>
								<td width="49" align="center">
									<strong>Autorizar</strong>
								</td>
								<td width="347" align="center">
									<strong>Relat&oacute;rio/Processo</strong>
								</td>
								<td width="62" align="center">
									<strong>Data/Hora</strong>
								</td>
								<td width="165" align="center">
									<strong>Usu&aacute;rio</strong>
								</td>
								<td width="165" align="center">
									<strong>Situa&ccedil;&atilde;o</strong>
								</td>
							</tr>
											
<!-- --------------------------------tentativa de exibição do filtro Processo Iniciado--------------------------------- -->				
				<%--Esquema de paginação--%> 
				<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
					export="currentPageNumber=pageNumber;pageOffset"				
					maxPageItems="10" items="${sessionScope.totalRegistros}">
					<pg:param name="pg"/>
					<pg:param name="q"/>
					<logic:present name="collProcessoIniciado">
							<logic:iterate name="collProcessoIniciado" id="processoIniciado">							
							<pg:item>
								<tr bgcolor="#cbe5fe">
									<td>								
 									<div align="center">
										<input type="checkbox" name="idRegistrosRemocao" value="<bean:write name="processoIniciado" property="id"/>"/>
									</div>
									</td>
									<td>								
									<div align="center">
										<input type="checkbox" name="idRegistrosAutorizar" value="<bean:write name="processoIniciado" property="id"/>"/>
									</div>
									</td>
									<td>
									<div align="left"><bean:write name="processoIniciado"
										property="processo.descricaoProcesso" /></div>
									</td>
									<td>
									<div align="center">
										<logic:present name="processoIniciado"	property="dataHoraComando">
											<bean:write name="processoIniciado" format="dd/MM/yyyy HH:mm:ss"
												property="dataHoraComando" />
										</logic:present>
										<logic:notPresent name="processoIniciado" property="dataHoraComando">
											&nbsp;
										</logic:notPresent>
									</div>	
									</td>
									<td>
									<div align="center"><bean:write name="processoIniciado"
										property="usuario.nomeUsuario" /></div>
									</td>
									<td>
									<div align="center"><bean:write name="processoIniciado"
										property="processoSituacao.descricao" /></div>
									</td>								
								</tr>
							</pg:item>
							</logic:iterate>
						</logic:present>
						<%-- Fim do esquema de paginação --%>
						</table>
					</td>
				</tr>	
						

<!-- --------------------------------tentativa de exibição do filtro Processo Iniciado--------------------------------- -->				
				
				<tr>
 					<td>
 					 
						<table width="100%">
							<tr>
								<td>							
									<input type="button" class="bottonRightCol"
										value="Remover" name="removerProcessoIniciado" onclick="remover(idRegistrosRemocao);"
										url="removerProcessoIniciadoAction.do"/>
										
									<input type="button" class="bottonRightCol" 
										value="Autorizar" name="autorizarProcessoIniciado" onclick="autorizar(idRegistrosAutorizar);"
										url="autorizarProcessoIniciadoAction.do">
								</td>
							</tr>
						</table>					
						<table width="100%" border="0">
							<tr>
								<td align="center">
									<strong><%@ include file="/jsp/util/indice_pager_novo.jsp"%></strong>
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