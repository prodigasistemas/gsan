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
			document.forms[0].action = "/gsan/removerNegativadorRegistroTipoAction.do"
			document.forms[0].submit();
		 }
	}
 }


 
-->
</script>


</head>

<body leftmargin="5" topmargin="5">

<html:form action="/removerNegativadorRegistroTipoAction" method="post"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post"
	onsubmit="return CheckboxNaoVazio(document.ManutencaoRegistroActionForm.idRegistrosRemocao) && confirm('Confirma remoção?')">

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
			<td width="602" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			
		   <!--Início Tabela Reference a Páginação da Tela de Processo-->
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" /></td>
          <td class="parabg">Manter Tipo do Registro do Negativador </td>

          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
        </tr>
      </table>
	  <table width="100%" border="0" cellpadding="1" cellspacing="1" bordercolor="#cbe5fe">
	  <tr bordercolor="#cbe5fe"> 
          <td colspan="6">&nbsp;</td>
        </tr>
        <tr bordercolor="#cbe5fe"> 
          <td colspan="6" bgcolor="#cbe5fe">Tipo(s) do registro  do negativador
          cadastrado: 
            <html:text name="negativador" property="cliente.nome" readonly="true" size="50" maxlength="50" style="background-color:#EFEFEF; border:0" /></td>
        </tr>

        <tr bordercolor="#cbe5fe"> 
          <td width="19%" bgcolor="#79BBFD"><div align="center"><strong><u>Todos</u></strong></div></td>
          <td width="67%" bgcolor="#79BBFD"><div align="center"><strong>Descri&ccedil;&atilde;o do Tipo do Registro </strong></div>          </td>
          <td width="22%" bgcolor="#79BBFD"><div align="center"><strong>C&oacute;digo do Tipo do Registro </strong></div></td> 
        </tr>
       	<%--Esquema de paginação--%>
							<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
								<pg:param name="pg" />
								<pg:param name="q" />

							<logic:present name="collectionNegativadorRegistroTipo">
								<%int cont = 1;%>
								<logic:iterate name="collectionNegativadorRegistroTipo" id="negativadorRegistroTipo">
									<pg:item>
										<%cont = cont + 1;
											if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

											%>
										<tr bgcolor="#cbe5fe">
											<%}%>

											<td width="7%">
											<div align="center"><input type="checkbox"
												name="idRegistrosRemocao"
												value="<bean:write name="negativadorRegistroTipo" property="id"/>"></div>
											</td>											
											<td width="15%">
												<div align="center">
												<logic:notPresent name="acao" scope="session">
												
													  <a href="/gsan/exibirAtualizarNegativadorRegistroTipoAction.do?idRegistroAtualizacao=<bean:write name="negativadorRegistroTipo" property="id"/>
														">
														<bean:write name="negativadorRegistroTipo" property="descricaoRegistroTipo"/>
														</a>
														
														&nbsp;															
												</logic:notPresent></div>
											</td>
											
											<td align="center">
											
											<bean:write name="negativadorRegistroTipo" property="codigoRegistro"/>
												
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
					 	<input name="Button" type="button" class="bottonRightCol" value="Remover" align="left" style="width: 70px;" onclick="verificarSelecao(idRegistrosRemocao);"> 
					 	<input name="button" type="button"
							class="bottonRightCol" value="Voltar Filtro"
							onclick="window.location.href='<html:rewrite page="/exibirFiltrarNegativadorRegistroTipoAction.do?desfazer=S"/>'"
							align="left" style="width: 80px;">
						
					</td>
					<td align="right">      
						<div align="right"><a href="javascript:toggleBox('demodiv',1);">
							<img border="0"
								src="<bean:message key="caminho.imagens"/>print.gif"
								title="Imprimir Tipo do Registro do Negativador" /> </a></div>
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
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioNegativadorRegistroTipoManterAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
