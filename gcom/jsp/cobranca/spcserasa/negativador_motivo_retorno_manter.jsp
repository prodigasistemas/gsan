<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>


<title>Compesa - SGCQ</title>
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
function verificarSelecao(objeto){
	if (CheckboxNaoVazio(objeto)){
		if (confirm ("Confirma remoção?")) {
			document.forms[0].action = "/gsan/removerNegativadorRetornoMotivoAction.do"
			document.forms[0].submit();
		 }
	}
}

function verificaCheckAtualizar(){

    form = document.forms[0];
	retorno = 0;

	for(indice = 0; indice < form.elements.length; indice++){
		if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
			retorno++;
			idAtualizar = form.elements[indice];
			alert(idAtualizar.value);
		}
	}

	if (retorno > 1){
		alert('Para atualizar, selecione apenas um registro.'); 
	} else if (retorno == 0){
		alert('Para atualizar, selecione um registro.');
	} else {
		document.forms[0].action = "/gsan/exibirAtualizarNegativadorRetornoMotivoAction.do";
		document.forms[0].submit();
	}
}
-->
</script>


</head>

<body leftmargin="5" topmargin="5">

<html:form action="/removerNegativadorRetornoMotivoAction" method="post"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post"
	onsubmit="return CheckboxNaoVazio(document.ManutencaoRegistroActionForm.idRegistrosRemocao) && confirm('Confirma remoção?')">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<input type="hidden" name="idAtualizar">

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
			<td width="602" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			
			
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" /></td>
          <td class="parabg">Manter Motivo de Retorno do Registro do Negativador</td>

          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
        </tr>
      </table>
	  <table width="100%" border="0" cellpadding="1" cellspacing="0" bordercolor="#cbe5fe">
	  <tr bordercolor="#cbe5fe"> 
          <td colspan="6">&nbsp;</td>
        </tr>
        <tr bordercolor="#cbe5fe"> 
          <td colspan="6" bgcolor="#cbe5fe"> 
          	Negativador cadastrado: <bean:write name="nomeNegativador"/>
           </td>
        </tr>
        <tr bordercolor="#cbe5fe">
          <td width="7%" bgcolor="#79BBFD"><div align="center"><strong><a	href="javascript:facilitador(this);" id="0">Todos</a></strong></div></td>
          <td width="18%" bgcolor="#79BBFD"><div align="center"><strong>C&oacute;digo Motivo</strong></div></td>
          <td width="61%" bgcolor="#79BBFD"><div align="center"><strong>Motivo</strong></div></td>
        </tr>
		
		
		<%--Esquema de paginação--%>
							<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
								<pg:param name="pg" />
								<pg:param name="q" />

							<logic:present name="collectionNegativadorRetornoMotivo">
								<%int cont = 1;%>
								<logic:iterate name="collectionNegativadorRetornoMotivo" id="negativadorRetornoMotivo">
									<pg:item>
										<%cont = cont + 1;
											if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

											%>
										<tr bgcolor="#cbe5fe">
											<%}%>

											<td align="center">
												<input type="checkbox"	name="idRegistrosRemocao"	value="<bean:write name="negativadorRetornoMotivo" property="id"/>" >
											</td>
											
											<td >
												<div align="center">
												<logic:notPresent name="acao" scope="session">
													<a href="/gsan/exibirAtualizarNegativadorRetornoMotivoAction.do?idAtualizar=<bean:write name="negativadorRetornoMotivo" property="id"/>">			
														<bean:write name="negativadorRetornoMotivo" property="codigoRetornoMotivo"/>&nbsp;															
													</a>
												</logic:notPresent></div>
											</td>
											
											<td align="center">
												<bean:write name="negativadorRetornoMotivo" property="descricaoRetornocodigo"/>
											</td>
										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
       
      

        <tr bordercolor="#cbe5fe">
          <td colspan="6">&nbsp;</td>
        </tr>
        
        </table>
        
        
       	<table width="100%">
				<tr>

					<td>
						<input name="ButtonRemover" type="button" class="bottonRightCol" value="Remover" align="left" style="width: 70px;" onclick="verificarSelecao(idRegistrosRemocao);"> 

						<input name="button" type="button"
							class="bottonRightCol" value="Voltar Filtro"
							onclick="window.location.href='<html:rewrite page="/exibirFiltrarNegativadorRetornoMotivoAction.do?desfazer=S"/>'"
							align="left" style="width: 80px;">
						
					</td>
					<td align="right">      
						<div align="right"><a href="javascript:toggleBox('demodiv',1);">
							<img border="0"
								src="<bean:message key="caminho.imagens"/>print.gif"
								title="Imprimir Contrato Negativador" /> </a></div>
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
				</tr>
<%-- Fim do esquema de paginação --%>
	
	
	
	
	
	
			</table>
      
      
			</td>
		</tr>
	</table>
	
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioNegativadorRetornoMotivoManterAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
	
	
</body>
</html:form>
</html:html>
