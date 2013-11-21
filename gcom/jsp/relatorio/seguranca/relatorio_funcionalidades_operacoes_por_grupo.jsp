<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

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
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

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

function validarForm(){
	var form = document.forms[0];
	var achou = false;
    var ids = form.ids;
    var idSelecionado = '';

	for (var i=0;i<ids.length;i++){
	    if(ids[i].checked){
	    	achou = true;
	      	idSelecionado = ids[i].value; 
	    }
   	}

   	if(achou == true){
		toggleBox('demodiv',1);
   	}else{
	    alert('Selecione um Grupo.');  
   	}
}


</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/gerarRelatorioFuncionalidadesOperacoesPorGrupoAction.do"
	name="GerarRelatorioFuncionalidadesOperacoesPorGrupoActionForm"
	type="gcom.gui.relatorio.seguranca.GerarRelatorioFuncionalidadesOperacoesPorGrupoActionForm"
	method="post" >


	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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
			<td width="600" valign="top" class="centercoltext">
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table align="center" border="0" cellpadding="0" cellspacing="0"
				width="100%">
				<tr>
					<td width="11"><img src="imagens/parahead_left.gif" border="0"></td>

					<td class="parabg">Rela&ccedil;&atilde;o de grupos, funcionalidades e opera&ccedil;&otilde;es</td>
					<td valign="top" width="11"><img src="imagens/parahead_right.gif"
						border="0"></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a P&aacute;gina&ccedil;&atilde;o da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="2" height="23"><font color="#000000"
						style="font-size:10px"
						face="Verdana, Arial, Helvetica, sans-serif"><strong>Grupos:</strong></font></td>
				</tr>
				<tr>
					<td colspan="2" bgcolor="#000000" height="2"></td>
				</tr>
				<tr>
					<td colspan="2">
					<table border="0" bgcolor="#99CCFF" width="100%">
						<tr bordercolor="#000000">
							<td width="10%" bgcolor="#90c7fc">
								<strong>
									<a onclick="this.focus();" id="0" href="javascript:facilitador(this);">Todos</a>
								</strong>
							</td>
							<td width="41%" bgcolor="#90c7fc">
								<div align="center"><strong>Descri&ccedil;&atilde;o</strong></div>
							</td>
							<td width="45%" bgcolor="#90c7fc">
								<div align="center"><strong>Descri&ccedil;&atilde;o Abreviada</strong></div>
							</td>
						</tr>
					</table>
					<table border="0" bgcolor="#99CCFF" width="100%">
						<%--scroll --%>
						<logic:present name="collectionGrupos">
							<tr>
								<td>
									<% String cor = "#cbe5fe";%>
									<div style="width: 100%; height: 274; overflow: auto;">
									
									<table width="100%" align="center" bgcolor="#90c7fc">
										<logic:iterate name="collectionGrupos" id="grupo">
											<%if (cor.equalsIgnoreCase("#cbe5fe")){
													cor = "#FFFFFF";%>
													<tr bgcolor="#FFFFFF">
											<%} else{
													cor = "#cbe5fe";%>
													<tr bgcolor="#cbe5fe">
											<%}%>	
											<td width="10%">
												<div align="center">
												<strong> 
													<input type="checkbox"
														   name="ids"
														   value="<bean:write name="grupo" property="id" />" /> 
												</strong>
												</div>
											</td>
											
											<td width="45%">
												<div align="center">
													<html:link page="/gerarRelatorioFuncionalidadesOperacoesPorGrupoAction.do" 
															   paramName="grupo"
															   paramProperty="id" 
															   paramId="idRegistroGrupo">
														<bean:write name="grupo" property="descricao" />
													</html:link>
												</div>
											</td>
											<td width="45%">
												<div align="center">
													<bean:write name="grupo"
																property="descricaoAbreviada" />
												</div>
											</td>
											
											</tr>
										</logic:iterate>
									</table>
									</div>
								</td>
							</tr>
						</logic:present>	
					</table>
					
					
					<table width="100%" border="0">
						<tr>
							<td align="right">
								<input type="button" 
									   name="Button" 
									   class="bottonRightCol" 
									   value="Imprimir" 
									   onClick="javascript:validarForm()" />
							</td>
						</tr>
					</table>
			</table>
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=/gsan/gerarRelatorioFuncionalidadesOperacoesPorGrupoAction.do?botao=ok" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>