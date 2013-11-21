<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarOrdemServicoActionForm" dynamicJavascript="false" />

<script language="JavaScript">
	
	function imprimirOS(){

		/*chave = selecionouOrdemServico();
		if(chave != false){
			chave = retornaValor(chave);		

	    	window.location.href='gerarRelatorioOrdemServicoAction.do?idOrdemServico='+chave;
		}*/

   		var form = document.forms[0];
		var chaves = montarChaveOs();

		if(chaves != null && chaves != '' && chaves != 'undefined'){
			form.ButtonImprimirOs.disabled = true;
			window.location.href='gerarRelatorioOrdemServicoAction.do?idsOS='+chaves;
		}else{
			alert('É necessário marcar alguma Ordem de Serviço');
			return false;
		}
	}	
	
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

	function montarChaveOs(){
   		var form = document.forms[0];

		var selecionados = form.elements['osSelecionada'];
		var chave = "";

		for (j=0; j< selecionados.length; j++) {

			if(selecionados[j].checked){
				
				var valorObjeto = retornaValor(selecionados[j].value);

				if(chaveJaSelecionada(valorObjeto,chave) == false){
					if(chave != ""){
						chave = chave+"$"+valorObjeto;
					}else{
						chave = valorObjeto;
					}
				}
			}
		}
		return chave;		
	}
	
	function retornaValor(chave){
		myString = new String(chave);
		splitString = myString.split("___");
		//Primeira posição idOs	
		return splitString[0];
	}
	
	function chaveJaSelecionada(valorObjeto,chavePesquisa){

		var retorno = false;
		var form = document.forms[0];

		if(chavePesquisa != null && chavePesquisa != ""){

			myString = new String(chavePesquisa);
			splitString = myString.split("$");

			for (i=0; i< splitString.length; i++) {
				if(splitString[i] == valorObjeto){
					retorno = true;
					break;
				}
			}
		}

		return retorno;
	}	
	
	function habilitaBotaoImprimirOS() {
		var form = document.forms[0];
	
			form.ButtonImprimirOs.disabled = false;
		
	}
	
	function voltar(manterOS) {
		var form = document.forms[0];
		if(manterOS == ""){
		 window.location.href='/gsan/exibirFiltrarOrdemServicoAction.do'
		}else{
			window.location.href='/gsan/'+manterOS;
		}
	}

	function mudaCorUrgencia(){
		css_color_change('1','red');					
	}

</script>	

</head>

<body leftmargin="5" topmargin="5" onload="javaScript:mudaCorUrgencia();">
<html:form action="/gerarRelatorioFiltrarOrdemServicoAction" method="post" >
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
		<td width="590" valign="top" class="centercoltext">
            <table>
              <tr><td></td></tr>
            </table>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
				<td class="parabg">Manter Ordem de Servi&ccedil;o</td>
                <td width="11" valign="top"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
              </tr>
            </table>
            <!-- Início do Corpo -->
            <p>&nbsp;</p>
            <table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td colspan="5">
						<font color="#000000" style="font-size:10px" face="Verdana, Arial, Helvetica, sans-serif">
						<strong>Total de Ordens de Serviço encontradas: ${sessionScope.totalRegistros}</strong></font></td>
				</tr>
				<tr>
					<td colspan="5">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="5">
						<table width="100%" bgcolor="#99CCFF">
			 				<tr bordercolor="#90c7fc" bgcolor="#90c7fc"> 
			 				    <td width="7%" bgcolor="#90c7fc">
								  <div align="center"><strong><a href="javascript:facilitador(this);"id="0">Todos</a></strong></div>
								</td>
								<td width="8%" ><div align="center"><strong>Ordem de Servi&ccedil;o</strong></div></td>
				                <td width="20%"><div align="center"><strong>Tipo de Servi&ccedil;o</strong></div></td>
						        <td width="9%" ><div align="center"><strong>N&uacute;mero do RA</strong></div></td>
				                <td width="9%" ><div align="center"><strong>Im&oacute;vel</strong></div></td>
				                <td width="7%" ><div align="center"><strong>Sit.</strong></div></td>
			                  	<td width="5%" ><div align="center"><strong>Data de Gera&ccedil;&atilde;o</strong></div></td>
			                  	<td width="5%" ><div align="center"><strong>Data de Emissão</strong></div></td>
			                  	<td width="11%"><div align="center"><strong>Perfil do Imóvel</strong></div></td>
				                <td width="19%"><div align="center"><strong>Unidade Atual</strong></div></td>
							</tr>
							<tr>
								<%--Esquema de paginação--%>
								<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
										  export="currentPageNumber=pageNumber;pageOffset"
							             maxPageItems="10" items="${sessionScope.totalRegistros}">
									<pg:param name="q" />
									<pg:param name="pg"/>
									<logic:present name="colecaoOSHelper">
									<%--Esquema de paginação--%>
									<c:set var="count" value="0"/>
									<logic:iterate name="colecaoOSHelper" id="helper">
									    
										<pg:item>
			                     		  		<c:set var="count" value="${count+1}"/>
			                        		<c:choose>
			                        			<c:when test="${count%2 == '1'}">
			                        				<tr bgcolor="#FFFFFF">
			                        			</c:when>
			                        			<c:otherwise>
			                        				<tr bgcolor="#cbe5fe">
			                        			</c:otherwise>
			                        		</c:choose>	
			                        			<td align="center" bordercolor="#90c7fc" class="${helper.indicadorUrgencia}">
													<input type="checkbox"
														name="osSelecionada" 
														value="<bean:write name="helper" property="ordemServico.id"/>" 
														onchange="javascript:montarChaveOs(this); habilitaBotaoImprimirOS(); "/>
					                      		</td>										
												<td bordercolor="#90c7fc" >
						                        	<div align="center">
						                        		<a  title="Consultar Dados da Ordem de Serviço" href="javascript:window.location.href='<html:rewrite page="/exibirConsultarDadosOrdemServicoAction.do?numeroOS=${helper.ordemServico.id}"/>';" class="${helper.indicadorUrgencia}" >
															<font size=1><bean:write name="helper" property="ordemServico.id" /></font>
														</a>
													</div>
												</td>

												<logic:equal name="helper" property="indicadorUrgencia" value="1">
													<logic:present name="helper" property="hint1">
														<td bordercolor="#90c7fc" class="${helper.indicadorUrgencia}" valign="center" 
														onmouseover="this.T_BGCOLOR='whitesmoke';this.T_RIGHT=true;return escape('<bean:write name="helper" property="hint1"/>');">
															<div align="center">
															<logic:notEmpty name="helper" property="ordemServico.servicoTipo">
																<font size=1><bean:write name="helper" property="ordemServico.servicoTipo.descricao"/></font>
															</logic:notEmpty>
															</div>	
														</td>
													</logic:present>
													
													<logic:notPresent name="helper" property="hint1">
													<td bordercolor="#90c7fc" class="${helper.indicadorUrgencia}">
														<div align="center">
														<logic:notEmpty name="helper" property="ordemServico.servicoTipo">
															<font size=1><bean:write name="helper" property="ordemServico.servicoTipo.descricao"/></font>
														</logic:notEmpty>
														</div>	
													</td>
													</logic:notPresent>	
												</logic:equal>
												
												<logic:equal name="helper" property="indicadorUrgencia" value="2">
													<td bordercolor="#90c7fc" class="${helper.indicadorUrgencia}">
														<div align="center">
														<logic:notEmpty name="helper" property="ordemServico.servicoTipo">
															<font size=1><bean:write name="helper" property="ordemServico.servicoTipo.descricao"/></font>
														</logic:notEmpty>
														</div>		
													</td>
												</logic:equal>
											




												<td bordercolor="#90c7fc" class="${helper.indicadorUrgencia}">
						                        	<div align="center">
													<logic:notEmpty name="helper" property="ordemServico.registroAtendimento">
					                        		<a  title="Consultar Dados do Registro de Atendimento" 
					                        			href="javascript:abrirPopup('exibirConsultarRegistroAtendimentoPopupAction.do?numeroRA=${helper.ordemServico.registroAtendimento.id}', 400, 600 );" class="${helper.indicadorUrgencia}">
														
														<font size=1><bean:write name="helper" property="ordemServico.registroAtendimento.id" /></font> 
													</a>
													</logic:notEmpty>
				
													</div>	
												</td>

												<td bordercolor="#90c7fc" class="${helper.indicadorUrgencia}">
						                        	<div align="center">
														<logic:notEmpty name="helper" property="imovel">
															<font size=1><bean:write name="helper" property="imovel.id"/></font> 
														</logic:notEmpty>
													</div>	
												</td>

												<td bordercolor="#90c7fc" class="${helper.indicadorUrgencia}">
						                        	<div align="center">
														<logic:notEmpty name="helper" property="situacao">
															<font size=1><bean:write name="helper" property="situacao" /></font> 
														</logic:notEmpty>
													</div>	
												</td>

												<td bordercolor="#90c7fc" class="${helper.indicadorUrgencia}">
						                        	<div align="center">
														<logic:notEmpty name="helper" property="ordemServico.dataGeracao">
															<font size=1><bean:write name="helper" property="ordemServico.dataGeracao"  format="dd/MM/yyyy" /></font> 
														</logic:notEmpty>
													</div>	
												</td>
												<td bordercolor="#90c7fc" class="${helper.indicadorUrgencia}">
						                        	<div align="center">
														<logic:notEmpty name="helper" property="ordemServico.dataEmissao">
															<font size=1><bean:write name="helper" property="ordemServico.dataEmissao" format="dd/MM/yyyy HH:mm:ss" /></font> 
														</logic:notEmpty>
													</div>	
												</td>
												
												<td bordercolor="#90c7fc" class="${helper.indicadorUrgencia}">
				                        	        <div>
												        <logic:notEmpty name="helper" property="perfilImovel">
													        <font size=1><bean:write name="helper" property="perfilImovel.descricao" /></font>
												        </logic:notEmpty>
											        </div>	
										        </td>
																								
												<td bordercolor="#90c7fc" class="${helper.indicadorUrgencia}">
						                        	<div align="center">
														<logic:notEmpty name="helper" property="unidadeAtual">
															<font size=1><bean:write name="helper" property="unidadeAtual.descricao" /></font>
														</logic:notEmpty>
													</div>	
												</td>
											</tr>										
										</pg:item>										
																				
									</logic:iterate>
									</logic:present>
							</table>
			
							</td>
						</tr>

		</table>
		<table width="100%" border="0">
			<tr>
				<td>
					<div align="center">
						<strong><%@ include
						file="/jsp/util/indice_pager_novo.jsp"%>
						</strong>
					</div>
				</td>
			</tr>
			<tr>
				<!-- 
				<td height="24"><input type="button" class="bottonRightCol"
					value="Voltar Filtro"
					onclick="window.location.href='<html:rewrite page="/exibirFiltrarOrdemServicoAction.do"/>'"/></td>
				-->
				<td height="24"><input type="button" class="bottonRightCol"
					value="Voltar Filtro"
					onclick="javascript:voltar('${caminhoRetornoOS}');"/></td>
              
              <td colspan="4" align="right">
              <a href="javascript:toggleBox('demodiv',1);" name="ImprimirOrdensServico">
				  <img border="0" src="<bean:message key="caminho.imagens"/>print.gif" title="Imprimir Ordens de Serviço" />
			  </a>
              </td>
              
              <td align="right">                       				
                <input name="ButtonImprimirOs"
                       type="button" class="bottonRightCol"
                       onClick="javascript:imprimirOS();" value="Imprimir OS">                     
			  </td>					
			</tr>
			
		</table>
		</pg:pager></td>
	</tr>

</table>

<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioFiltrarOrdemServicoAction.do" />

</body>
<%@ include file="/jsp/util/tooltip.jsp"%>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</html:html>