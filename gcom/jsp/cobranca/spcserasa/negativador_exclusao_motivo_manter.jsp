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

 
-->
</script>


</head>

<body leftmargin="5" topmargin="5">

<html:form action="/removerNegativadorExclusaoMotivoAction" method="post"
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
          <td class="parabg">Manter Motivo de Retorno do Registro do Negativador </td>

          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
        </tr>
      </table>
	  <table width="100%" border="0" cellpadding="1" cellspacing="1" bordercolor="#cbe5fe">
        <tr bordercolor="#cbe5fe">
          <td colspan="5">&nbsp;</td>
        </tr>
        <tr bordercolor="#cbe5fe">
          <td colspan="5" bgcolor="#cbe5fe">Motivo(s) de Exclus&atilde;o  do negativador 
            cadastrado:<html:text name="negativador" property="cliente.nome" readonly="true" size="40" style="background-color:#EFEFEF; border:0"/>
             </td>
        </tr>
        <tr bordercolor="#cbe5fe">   
          <td width="18%" bgcolor="#79BBFD"><div align="center"><strong>C&oacute;digo Motivo</strong></div></td>
          <td width="61%" bgcolor="#79BBFD"><div align="center"><strong>Motivo</strong></div></td>
        </tr>
        
        
        	<%--Esquema de paginação--%>
							<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
								<pg:param name="pg" />
								<pg:param name="q" />

							<logic:present name="collectionNegativadorExclusaoMotivo">
								<%int cont = 1;%>
								<logic:iterate name="collectionNegativadorExclusaoMotivo" id="negativadorExclusaoMotivo">
									<pg:item>
										<%cont = cont + 1;
											if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

											%>
										<tr bgcolor="#cbe5fe">
											<%}%>

																						
											<td >
												<div align="center">
												<logic:notPresent name="acao" scope="session">
												
													  <a href="/gsan/exibirAtualizarNegativadorExclusaoMotivoAction.do?idRegistroAtualizacao=<bean:write name="negativadorExclusaoMotivo" property="id"/>
														">
														<bean:write name="negativadorExclusaoMotivo" property="codigoExclusaoMotivo"/>
														</a>
														
														&nbsp;															
												</logic:notPresent></div>
											</td>
											
											<td align="center">
												<bean:write name="negativadorExclusaoMotivo" property="descricaoExclusaoMotivo"/>
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

						<input name="button" type="button"
							class="bottonRightCol" value="Voltar Filtro"
							onclick="window.location.href='<html:rewrite page="/exibirFiltrarNegativadorExclusaoMotivoAction.do?desfazer=S"/>'"
							align="left" style="width: 80px;">
						
					</td>
					<td align="right">      
						<div align="right"><a href="javascript:toggleBox('demodiv',1);">
							<img border="0"
								src="<bean:message key="caminho.imagens"/>print.gif"
								title="Imprimir Motivo da Exclusao do Negativador" /> </a></div>
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
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioNegativadorExclusaoMotivoManterAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>     
        

</body>
</html:form>
</html:html>
