<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<html:html>

<%@ page import="java.util.Collection,gcom.util.ConstantesSistema" %>

<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript">
function enviarIdParaInserir(idSetorComercial) {
	opener.redirecionarSubmitAtualizar(idSetorComercial);
	self.close();
}
</script>
</head>

<body leftmargin="0" topmargin="0" onload="resizePageSemLink(660, 430);">
<table width="630" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="630" valign="top" class="centercoltext"> <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Pesquisa de Setor Comercial</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0" bgcolor="#90c7fc">
        <tr align="left" >
          <td width="20%" align="center"><strong>Localidade</strong></td>
          <td align="center" width="13%"><strong>Código do Setor</strong></td>
          <td width="27%" align="center"><strong>Descrição</strong></td>
          <td width="20%" align="center"><strong>Município</strong></td>
          <td width="20%" align="center"><strong>Setor Alternativo</strong></td>
        </tr>
	<%--Esquema de paginação--%>
		<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
			export="currentPageNumber=pageNumber;pageOffset"
			maxPageItems="10" items="${sessionScope.totalRegistros}">
			<pg:param name="q" />
				<%int cont = 0;%>


         <logic:iterate name="setorComerciais" id="setorComercial">
          <pg:item>
			<%cont = cont + 1;
			if (cont % 2 == 0) {%>
				<tr bgcolor="#cbe5fe">
			<%} else {	%>
				<tr bgcolor="#FFFFFF">
			<%}%>
			
			
		  <td>
            <logic:notEqual name="setorComercial" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
				<bean:write name="setorComercial" property="localidade.descricao"/>
            </logic:notEqual>
            <logic:equal name="setorComercial" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
				<font color="#CC0000"> 
					<bean:write name="setorComercial" property="localidade.descricao"/>
				</font>
            </logic:equal>
          </td>
			
			
			
        <td align="center">
		   <logic:equal name="tipoPesquisa" value="setorComercialOrigem">

				<logic:notEqual name="setorComercial" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
					
					<logic:present name="caminhoRetornoTelaPesquisaSetorComercial" scope="session">
						<div align="center">
							<a href="javascript:enviarDadosParametros('<bean:write name="caminhoRetornoTelaPesquisaSetorComercial" scope="session"/>','<bean:write name="setorComercial" property="codigo"/>', '<bean:write name="setorComercial" property="descricao"/>', 'setorComercialOrigem');">
								<bean:write name="setorComercial" property="codigo"/>
							</a>
						</div>
					</logic:present>

					<logic:notPresent name="caminhoRetornoTelaPesquisaSetorComercial" scope="session">

						<logic:present name="consulta">
							<a href="javascript:enviarIdParaInserir(${setorComercial.id})"> 
								<bean:write name="setorComercial" property="codigo" /> 
							</a>
						</logic:present>
	
						<logic:notPresent name="consulta">
							<a href="javascript:enviarDadosQuatroParametros('<bean:write name="setorComercial" property="id"/>', '<bean:write name="setorComercial" property="descricao"/>', '<bean:write name="setorComercial" property="codigo"/>','setorComercialOrigem');">
								<bean:write name="setorComercial" property="codigo"/>
							</a>
						</logic:notPresent>

					</logic:notPresent>
					
	            </logic:notEqual>

	            <logic:equal name="setorComercial" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            

					<logic:present name="caminhoRetornoTelaPesquisaSetorComercial" scope="session">
						<div align="center">
							<a href="javascript:enviarDadosParametros('<bean:write name="caminhoRetornoTelaPesquisaSetorComercial" scope="session"/>','<bean:write name="setorComercial" property="codigo"/>', '<bean:write name="setorComercial" property="descricao"/>', 'setorComercialOrigem');">
								<bean:write name="setorComercial" property="codigo"/>
							</a>
						</div>
					</logic:present>

					<logic:notPresent name="caminhoRetornoTelaPesquisaSetorComercial" scope="session">

						<logic:present name="consulta">
							<a href="javascript:enviarIdParaInserir(${setorComercial.id})"> 
								<font color="#CC0000"> 
									<bean:write name="setorComercial" property="codigo" /> 
								</font>
							</a>
						</logic:present>
						<logic:notPresent name="consulta">
							<a href="javascript:enviarDadosQuatroParametros('<bean:write name="setorComercial" property="id"/>', '<bean:write name="setorComercial" property="descricao"/>', '<bean:write name="setorComercial" property="codigo"/>','setorComercialOrigem');">
								<font color="#CC0000"> 
									<bean:write name="setorComercial" property="codigo"/>
								</font>
							</a>
						</logic:notPresent>
					</logic:notPresent>
	            </logic:equal>
	            
           </logic:equal>

           <logic:equal name="tipoPesquisa" value="setorComercialDestino">
				<logic:notEqual name="setorComercial" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            

					<logic:present name="caminhoRetornoTelaPesquisaSetorComercial" scope="session">
						<div align="center">
							<a href="javascript:enviarDadosParametros('<bean:write name="caminhoRetornoTelaPesquisaSetorComercial" scope="session"/>','<bean:write name="setorComercial" property="codigo"/>', '<bean:write name="setorComercial" property="descricao"/>', 'setorComercialDestino');">
								<bean:write name="setorComercial" property="codigo"/>
							</a>
						</div>
					</logic:present>

					<logic:notPresent name="caminhoRetornoTelaPesquisaSetorComercial" scope="session">

						<logic:present name="consulta">
							<a href="javascript:enviarIdParaInserir(${setorComercial.id})"> 
								<bean:write name="setorComercial" property="codigo" /> 
							</a>
						</logic:present>
						<logic:notPresent name="consulta">
							<a href="javascript:enviarDadosQuatroParametros('<bean:write name="setorComercial" property="id"/>', '<bean:write name="setorComercial" property="descricao"/>', '<bean:write name="setorComercial" property="codigo"/>','setorComercialDestino');">
								<bean:write name="setorComercial" property="codigo"/>
							</a>
						</logic:notPresent>
					</logic:notPresent>
	            </logic:notEqual>

	            <logic:equal name="setorComercial" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            

					<logic:present name="caminhoRetornoTelaPesquisaSetorComercial" scope="session">
						<div align="center">
							<a href="javascript:enviarDadosParametros('<bean:write name="caminhoRetornoTelaPesquisaSetorComercial" scope="session"/>','<bean:write name="setorComercial" property="codigo"/>', '<bean:write name="setorComercial" property="descricao"/>', 'setorComercialDestino');">
								<bean:write name="setorComercial" property="codigo"/>
							</a>
						</div>
					</logic:present>

					<logic:notPresent name="caminhoRetornoTelaPesquisaSetorComercial" scope="session">
						<logic:present name="consulta">
							<a href="javascript:enviarIdParaInserir(${setorComercial.id})"> 
								<font color="#CC0000"> 
									<bean:write name="setorComercial" property="codigo" /> 
								</font>
							</a>
						</logic:present>
						<logic:notPresent name="consulta">
							<a href="javascript:enviarDadosQuatroParametros('<bean:write name="setorComercial" property="id"/>', '<bean:write name="setorComercial" property="descricao"/>', '<bean:write name="setorComercial" property="codigo"/>','setorComercialDestino');">
								<font color="#CC0000"> 
									<bean:write name="setorComercial" property="codigo"/>
								</font>
							</a>
						</logic:notPresent>
					</logic:notPresent>
					
	            </logic:equal>
           </logic:equal>

           <logic:notEqual name="tipoPesquisa" value="setorComercialOrigem">

             <logic:notEqual name="tipoPesquisa" value="setorComercialDestino">

				<logic:equal name="tipoPesquisa" value="selecionarRotas">
           
					<logic:notEqual name="setorComercial" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
						<logic:present name="consulta">
							<a href="javascript:enviarIdParaInserir(${setorComercial.id})"> 
								<bean:write name="setorComercial" property="codigo" /> 
							</a>
						</logic:present>
						<logic:notPresent name="consulta">
							<a href="javascript:enviarDadosParametros('exibirSelecionarRotaAction', '<bean:write name="setorComercial" property="codigo"/>', '<bean:write name="setorComercial" property="descricao"/>', 'setorComercial');">
								<bean:write name="setorComercial" property="codigo"/>
							</a>
						</logic:notPresent>
		            </logic:notEqual>
		            <logic:equal name="setorComercial" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
						<logic:present name="consulta">
							<a href="javascript:enviarIdParaInserir(${setorComercial.id})"> 
								<font color="#CC0000"> 
									<bean:write name="setorComercial" property="codigo" /> 
								</font>
							</a>
						</logic:present>
						<logic:notPresent name="consulta">
							<a href="javascript:enviarDadosParametros('exibirSelecionarRotaAction', '<bean:write name="setorComercial" property="codigo"/>', '<bean:write name="setorComercial" property="descricao"/>', 'setorComercial');">
								<font color="#CC0000"> 
									<bean:write name="setorComercial" property="codigo"/>
								</font>
							</a>
						</logic:notPresent>
		            </logic:equal>
				</logic:equal>

				<logic:notEqual name="tipoPesquisa" value="selecionarRotas">
					
					<logic:present name="caminhoRetornoTelaPesquisaSetorComercial" scope="session">
                   
						<logic:equal name="caminhoRetornoTelaPesquisaSetorComercial" scope="session" value="exibirPesquisarImovelAction">
							<logic:notEqual name="setorComercial" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
								<logic:present name="consulta">
									<a href="javascript:enviarIdParaInserir(${setorComercial.id})"> 
										<bean:write name="setorComercial" property="codigo" /> 
									</a>
								</logic:present>
								<logic:notPresent name="consulta">
									<a href="javascript:enviarDadosParametros('exibirPesquisarImovelAction', '<bean:write name="setorComercial" property="codigo"/>', '<bean:write name="setorComercial" property="descricao"/>', 'setorComercial');">
										<bean:write name="setorComercial" property="codigo"/>
									</a>
								</logic:notPresent>
				            </logic:notEqual>
				            <logic:equal name="setorComercial" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
								<logic:present name="consulta">
									<a href="javascript:enviarIdParaInserir(${setorComercial.id})"> 
										<font color="#CC0000"> 
											<bean:write name="setorComercial" property="codigo" /> 
										</font>
									</a>
								</logic:present>
								<logic:notPresent name="consulta">
									<a href="javascript:enviarDadosParametros('exibirPesquisarImovelAction', '<bean:write name="setorComercial" property="codigo"/>', '<bean:write name="setorComercial" property="descricao"/>', 'setorComercial');">
										<font color="#CC0000"> 
											<bean:write name="setorComercial" property="codigo"/>
										</font>
									</a>
								</logic:notPresent>
				            </logic:equal>
						</logic:equal>
						
						<logic:equal name="caminhoRetornoTelaPesquisaSetorComercial" scope="session" value="exibirPesquisarInformarRotaLeituraAction">
							<logic:notEqual name="setorComercial" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
								<logic:present name="consulta">
									<a href="javascript:enviarIdParaInserir(${setorComercial.id})"> 
										<bean:write name="setorComercial" property="codigo" /> 
									</a>
								</logic:present>
								<logic:notPresent name="consulta">
									<a href="javascript:enviarDadosParametros('exibirPesquisarInformarRotaLeituraAction', '<bean:write name="setorComercial" property="codigo"/>', '<bean:write name="setorComercial" property="descricao"/>', 'setorComercial');">
										<bean:write name="setorComercial" property="codigo"/>
									</a>
								</logic:notPresent>
				            </logic:notEqual>
				            <logic:equal name="setorComercial" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
								<logic:present name="consulta">
									<a href="javascript:enviarIdParaInserir(${setorComercial.id})"> 
										<font color="#CC0000"> 
											<bean:write name="setorComercial" property="codigo" /> 
										</font>
									</a>
								</logic:present>
								<logic:notPresent name="consulta">
									<a href="javascript:enviarDadosParametros('exibirPesquisarInformarRotaLeituraAction', '<bean:write name="setorComercial" property="codigo"/>', '<bean:write name="setorComercial" property="descricao"/>', 'setorComercial');">
										<font color="#CC0000"> 
											<bean:write name="setorComercial" property="codigo"/>
										</font>
									</a>
								</logic:notPresent>
				            </logic:equal>
						</logic:equal>

					</logic:present>
                
					<logic:notPresent name="caminhoRetornoTelaPesquisaSetorComercial" scope="session">
					
						<logic:notEqual name="setorComercial" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
							<logic:present name="consulta">
								<a href="javascript:enviarIdParaInserir(${setorComercial.id})"> 
									<bean:write name="setorComercial" property="codigo" /> 
								</a>
							</logic:present>
							<logic:notPresent name="consulta">
								<a href="javascript:enviarDados('<bean:write name="setorComercial" property="codigo"/>', '<bean:write name="setorComercial" property="descricao"/>','setorComercial');">
									<bean:write name="setorComercial" property="codigo"/>
								</a>
							</logic:notPresent>
			            </logic:notEqual>
			            <logic:equal name="setorComercial" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
							<logic:present name="consulta">
								<a href="javascript:enviarIdParaInserir(${setorComercial.id})"> 
									<font color="#CC0000"> 
										<bean:write name="setorComercial" property="codigo" /> 
									</font>
								</a>
							</logic:present>
							<logic:notPresent name="consulta">
								<a href="javascript:enviarDados('<bean:write name="setorComercial" property="codigo"/>', '<bean:write name="setorComercial" property="descricao"/>','setorComercial');">
									<font color="#CC0000"> 
										<bean:write name="setorComercial" property="codigo"/>
									</font>
								</a>
							</logic:notPresent>
			            </logic:equal>
					</logic:notPresent>

				</logic:notEqual>

			</logic:notEqual>

           </logic:notEqual>
          </td>	
			
			
          <td>
            <logic:notEqual name="setorComercial" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
				<bean:write name="setorComercial" property="descricao"/>
            </logic:notEqual>
            <logic:equal name="setorComercial" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
				<font color="#CC0000"> 
					<bean:write name="setorComercial" property="descricao"/>
				</font>
            </logic:equal>
          </td>
          

          
          <td>
            <logic:notEqual name="setorComercial" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
				<bean:write name="setorComercial" property="municipio.nome"/>
            </logic:notEqual>
            <logic:equal name="setorComercial" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
				<font color="#CC0000"> 
					<bean:write name="setorComercial" property="municipio.nome"/>
				</font>
            </logic:equal>
		  </td>
		  
		  <td>
            <logic:notEqual name="setorComercial" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
				<bean:write name="setorComercial" property="indicadorSetorAlternativo"/>
            </logic:notEqual>
            <logic:equal name="setorComercial" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
				<font color="#CC0000"> 
					<bean:write name="setorComercial" property="indicadorSetorAlternativo"/>
				</font>
            </logic:equal>
          </td>
		  
        </tr>
	</pg:item>
      </logic:iterate>
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
      <table width="100%" border="0">

         <tr>
          <td height="24"><input type="button" class="bottonRightCol" value="Voltar Pesquisa" onclick="window.location.href='<html:rewrite page="/exibirPesquisarSetorComercialAction.do?objetoConsulta=1"/>'"/></td>
        </tr>
      </table>
      <%-- Fim do esquema de paginação --%>
      </td>
  </tr>
  </table>

</body>
</html:html>
