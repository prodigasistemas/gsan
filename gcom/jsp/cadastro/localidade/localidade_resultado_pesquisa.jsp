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
function enviarIdParaInserir(idLocalidade) {
	opener.redirecionarSubmitAtualizar(idLocalidade);
	self.close();
}
</script>
</head>

<body leftmargin="0" topmargin="0" onload="resizePageSemLink(680, 430);">
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
          <td class="parabg">Pesquisa de Localidade</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0" bgcolor="#90c7fc">
        <tr align="left">
          <td width="8%" align="center"><strong>C&oacute;digo</strong></td>
          <td width="62%" align="center"><strong>Localidade</strong> </td>
          <td width="30%" align="center"><strong>Gerência Regional</strong> </td>
        </tr>
        <%--Esquema de paginação--%>
		<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
				export="currentPageNumber=pageNumber;pageOffset"
				maxPageItems="10" items="${sessionScope.totalRegistros}">
				<pg:param name="q" />

					<%int cont = 0;%>
				        <logic:iterate name="localidades" id="localidade">
						<pg:item>
							<%cont = cont + 1;
							if (cont % 2 == 0) {%>
								<tr bgcolor="#cbe5fe">								
							<%} else {	%>
								<tr bgcolor="#FFFFFF">
							<%}%>

          <td align="center">
				<logic:notEqual name="localidade" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
					<bean:write name="localidade" property="id"/>
	            </logic:notEqual>
	            <logic:equal name="localidade" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
					<font color="#CC0000"> 
						<bean:write name="localidade" property="id"/>
					</font>
	            </logic:equal>
          </td>
          <td>
           <logic:equal name="tipoPesquisa" value="origem" >
            
              <logic:notEqual name="localidade" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
              	  <logic:present name="consulta">
						<a href="javascript:enviarIdParaInserir(${localidade.id})"> <bean:write
							name="localidade" property="descricao" /> 
						</a>
				  </logic:present>
				  
				  <logic:notPresent name="consulta">
						<a href="javascript:enviarDados('<bean:write name="localidade" property="id"/>', '<bean:write name="localidade" property="descricao"/>', 'localidadeOrigem');">
							<bean:write name="localidade" property="descricao"/>
						</a>
				  </logic:notPresent>
              </logic:notEqual>
              
              
              <logic:equal name="localidade" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
              	  <logic:present name="consulta">
						<a href="javascript:enviarIdParaInserir(${localidade.id})"> <bean:write
							name="localidade" property="descricao" /> 
						</a>
				  </logic:present>
				  
				  <logic:notPresent name="consulta">
						<a href="javascript:enviarDados('<bean:write name="localidade" property="id"/>', '<bean:write name="localidade" property="descricao"/>', 'localidadeOrigem');">
		     	    		<font color="#CC0000"> 
								<bean:write name="localidade" property="descricao"/>
							</font>
				 		</a>
				  </logic:notPresent>
			  </logic:equal>
          
           </logic:equal>
           
           <logic:equal name="tipoPesquisa" value="destino">
            
              <logic:notEqual name="localidade" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
              	  <logic:present name="consulta">
						<a href="javascript:enviarIdParaInserir(${localidade.id})"> <bean:write
							name="localidade" property="descricao" /> 
						</a>
				  </logic:present>
				  
				  <logic:notPresent name="consulta">
						<a href="javascript:enviarDados('<bean:write name="localidade" property="id"/>', '<bean:write name="localidade" property="descricao"/>', 'localidadeDestino');">
							<bean:write name="localidade" property="descricao"/>
						</a>
				  </logic:notPresent>
              </logic:notEqual>
              
              
              <logic:equal name="localidade" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
              	  <logic:present name="consulta">
						<a href="javascript:enviarIdParaInserir(${localidade.id})"> <bean:write
							name="localidade" property="descricao" /> 
						</a>
				  </logic:present>
				  
				  <logic:notPresent name="consulta">
						<a href="javascript:enviarDados('<bean:write name="localidade" property="id"/>', '<bean:write name="localidade" property="descricao"/>', 'localidadeDestino');">
		     	    		<font color="#CC0000"> 
								<bean:write name="localidade" property="descricao"/>
							</font>
				 		</a>
				  </logic:notPresent>
			  </logic:equal>
          
           </logic:equal>
           
           <logic:notEqual name="tipoPesquisa" value="origem">
			<logic:notEqual name="tipoPesquisa" value="destino">
				<logic:equal name="tipoPesquisa" value="selecionarRotas">
           
				   <logic:notEqual name="localidade" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
		              	  <logic:present name="consulta">
								<a href="javascript:enviarIdParaInserir(${localidade.id})"> <bean:write
									name="localidade" property="descricao" /> 
								</a>
						  </logic:present>
						  
						  <logic:notPresent name="consulta">
							   <a href="javascript:enviarDadosParametros('exibirSelecionarRotaAction', '<bean:write name="localidade" property="id"/>', '<bean:write name="localidade" property="descricao"/>', 'localidade');">
								  <bean:write name="localidade" property="descricao"/>
							   </a>
						  </logic:notPresent>		           
		           </logic:notEqual>
              	   
              	   <logic:equal name="localidade" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
		              	  <logic:present name="consulta">
								<a href="javascript:enviarIdParaInserir(${localidade.id})"> <bean:write
									name="localidade" property="descricao" /> 
								</a>
						  </logic:present>
						  
						  <logic:notPresent name="consulta">
					     	  <a href="javascript:enviarDadosParametros('exibirSelecionarRotaAction', '<bean:write name="localidade" property="id"/>', '<bean:write name="localidade" property="descricao"/>', 'localidade');">
						     	 <font color="#CC0000"> 
									<bean:write name="localidade" property="descricao"/>
								 </font>
							  </a>
						  </logic:notPresent>				     	 
				  </logic:equal>
				</logic:equal>

				<logic:notEqual name="tipoPesquisa" value="selecionarRotas">
           
					<logic:notEmpty name="caminhoRetornoTelaPesquisaLocalidade">
                   
						<logic:equal name="caminhoRetornoTelaPesquisaLocalidade" value="exibirPesquisarImovelAction">
						
							<logic:notEqual name="localidade" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
				              	  <logic:present name="consulta">
										<a href="javascript:enviarIdParaInserir(${localidade.id})"> <bean:write
											name="localidade" property="descricao" /> 
										</a>
								  </logic:present>
								  
								  <logic:notPresent name="consulta">
							           <a href="javascript:enviarDadosParametros('exibirPesquisarImovelAction', '<bean:write name="localidade" property="id"/>', '<bean:write name="localidade" property="descricao"/>', 'localidade');">
									      <bean:write name="localidade" property="descricao"/>
									   </a>
								  </logic:notPresent>						          
				            </logic:notEqual>
				            
		              	    <logic:equal name="localidade" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
				              	  <logic:present name="consulta">
										<a href="javascript:enviarIdParaInserir(${localidade.id})"> <bean:write
											name="localidade" property="descricao" /> 
										</a>
								  </logic:present>
								  
								  <logic:notPresent name="consulta">
										<a href="javascript:enviarDadosParametros('exibirPesquisarImovelAction', '<bean:write name="localidade" property="id"/>', '<bean:write name="localidade" property="descricao"/>', 'localidade');">
								     	   <font color="#CC0000"> 
												<bean:write name="localidade" property="descricao"/>
											</font>
										</a>
								  </logic:notPresent>						     	   
							</logic:equal>
						</logic:equal>
						
						
						<logic:equal name="caminhoRetornoTelaPesquisaLocalidade" value="exibirPesquisarArrecadadorAction">
							<logic:notEqual name="localidade" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
					           	  <logic:present name="consulta">
										<a href="javascript:enviarIdParaInserir(${localidade.id})"> <bean:write
											name="localidade" property="descricao" /> 
										</a>
								  </logic:present>
								  
								  <logic:notPresent name="consulta">
							           <a href="javascript:enviarDadosParametros('exibirPesquisarArrecadadorAction', '<bean:write name="localidade" property="id"/>', '<bean:write name="localidade" property="descricao"/>', 'localidade');">
									      <bean:write name="localidade" property="descricao"/>
									   </a>
								  </logic:notPresent>	
				            </logic:notEqual>
		              	    <logic:equal name="localidade" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
					     	   	  <logic:present name="consulta">
										<a href="javascript:enviarIdParaInserir(${localidade.id})"> <bean:write
											name="localidade" property="descricao" /> 
										</a>
								  </logic:present>
								  
								  <logic:notPresent name="consulta">
							     	   <a href="javascript:enviarDadosParametros('exibirPesquisarArrecadadorAction', '<bean:write name="localidade" property="id"/>', '<bean:write name="localidade" property="descricao"/>', 'localidade');">
								     	   <font color="#CC0000"> 
												<bean:write name="localidade" property="descricao"/>
											</font>
									   </a>
								  </logic:notPresent>	
							</logic:equal>
						</logic:equal>


						<logic:equal name="caminhoRetornoTelaPesquisaLocalidade" value="exibirPesquisarUnidadeOrganizacionalAction">
							<logic:notEqual name="localidade" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
					           	  <logic:present name="consulta">
										<a href="javascript:enviarIdParaInserir(${localidade.id})"> <bean:write
											name="localidade" property="descricao" /> 
										</a>
								  </logic:present>
								  
								  <logic:notPresent name="consulta">
							           <a href="javascript:enviarDadosParametros('exibirPesquisarUnidadeOrganizacionalAction', '<bean:write name="localidade" property="id"/>', '<bean:write name="localidade" property="descricao"/>', 'localidade');">
									      <bean:write name="localidade" property="descricao"/>
									   </a>
								  </logic:notPresent>	
				            </logic:notEqual>
		              	    <logic:equal name="localidade" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
					     	   	  <logic:present name="consulta">
										<a href="javascript:enviarIdParaInserir(${localidade.id})"> <bean:write
											name="localidade" property="descricao" /> 
										</a>
								  </logic:present>
								  
								  <logic:notPresent name="consulta">
							     	   <a href="javascript:enviarDadosParametros('exibirPesquisarUnidadeOrganizacionalAction', '<bean:write name="localidade" property="id"/>', '<bean:write name="localidade" property="descricao"/>', 'localidade');">
								     	   <font color="#CC0000"> 
												<bean:write name="localidade" property="descricao"/>
											</font>
									   </a>
								  </logic:notPresent>	
							</logic:equal>
						</logic:equal>
						
						<logic:equal name="caminhoRetornoTelaPesquisaLocalidade" value="exibirPesquisarInformarRotaLeituraAction">
							<logic:notEqual name="localidade" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
					           	  <logic:present name="consulta">
										<a href="javascript:enviarIdParaInserir(${localidade.id})"> <bean:write
											name="localidade" property="descricao" /> 
										</a>
								  </logic:present>
								  
								  <logic:notPresent name="consulta">
							           <a href="javascript:enviarDadosParametros('exibirPesquisarInformarRotaLeituraAction', '<bean:write name="localidade" property="id"/>', '<bean:write name="localidade" property="descricao"/>', 'localidade');">
									      <bean:write name="localidade" property="descricao"/>
									   </a>
								  </logic:notPresent>	
				            </logic:notEqual>
		              	    <logic:equal name="localidade" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
					     	   	  <logic:present name="consulta">
										<a href="javascript:enviarIdParaInserir(${localidade.id})"> <bean:write
											name="localidade" property="descricao" /> 
										</a>
								  </logic:present>
								  
								  <logic:notPresent name="consulta">
							     	   <a href="javascript:enviarDadosParametros('exibirPesquisarInformarRotaLeituraAction', '<bean:write name="localidade" property="id"/>', '<bean:write name="localidade" property="descricao"/>', 'localidade');">
								     	   <font color="#CC0000"> 
												<bean:write name="localidade" property="descricao"/>
											</font>
									   </a>
								  </logic:notPresent>	
							</logic:equal>
						</logic:equal>

					</logic:notEmpty>

					<logic:empty name="caminhoRetornoTelaPesquisaLocalidade">
							<logic:notEqual name="localidade" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
					          	  <logic:present name="consulta">
										<a href="javascript:enviarIdParaInserir(${localidade.id})"> <bean:write
											name="localidade" property="descricao" /> 
										</a>
								  </logic:present>
								  
								  <logic:notPresent name="consulta">
							           <a href="javascript:enviarDados('<bean:write name="localidade" property="id"/>', '<bean:write name="localidade" property="descricao"/>', 'localidade');">
									      <bean:write name="localidade" property="descricao"/>
									   </a>
								  </logic:notPresent>	
				            </logic:notEqual>
		              	    <logic:equal name="localidade" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
								  <logic:present name="consulta">
										<a href="javascript:enviarIdParaInserir(${localidade.id})"> <bean:write
											name="localidade" property="descricao" /> 
										</a>
								  </logic:present>
								  
								  <logic:notPresent name="consulta">
										<a href="javascript:enviarDados('<bean:write name="localidade" property="id"/>', '<bean:write name="localidade" property="descricao"/>', 'localidade');">
								     	   <font color="#CC0000"> 
												<bean:write name="localidade" property="descricao"/>
											</font>
										</a>
								  </logic:notPresent>	
							</logic:equal>
					</logic:empty>

				</logic:notEqual>
           </logic:notEqual>
           </logic:notEqual>
	  	</td>
        <td>
            <logic:notEqual name="localidade" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
		       <bean:write name="localidade" property="gerenciaRegional.nome"/>
			</logic:notEqual>
		    <logic:equal name="localidade" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
			 	<font color="#CC0000"> 
			 		<bean:write name="localidade" property="gerenciaRegional.nome"/>
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
          <td height="24"><input type="button" class="bottonRightCol" value="Voltar Pesquisa" onclick="window.location.href='<html:rewrite page="/exibirPesquisarLocalidadeAction.do?objetoConsulta=1"/>'"/></td>
        </tr>
       </table>
      </td>
  </tr>
</table>
</body>
</html:html>
