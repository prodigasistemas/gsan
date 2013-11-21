<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.atendimentopublico.ordemservico.bean.OSPavimentoRetornoHelper"%>

<html:html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%@ include file="/jsp/util/titulo.jsp"%>

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<html:javascript staticJavascript="false" 
	formName="FiltrarOrdemProcessoRepavimentacaoActionForm"/>	
	
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

function imprimirOS(){

   		var form = document.forms[0];
		var chaves = montarChaveOs(form);

		if(chaves != null && chaves != '' && chaves != 'undefined'){
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

	function montarChaveOs(form){   			
		var chave = "";
		for (var i=0;i < form.elements.length;i++){		
			var elemento = document.forms[0].elements[i];
			if (elemento.type == "checkbox"){
				if(elemento.checked){				 
					var valorObjeto = retornaValor(elemento.value);
	
					if(chaveJaSelecionada(valorObjeto,chave) == false){
						if(chave != ""){
							chave = chave+"$"+valorObjeto;
						}else{
							chave = valorObjeto;
						}					
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

	function desabilitarBotaoImprimirRelacao(){
	
		var form = document.forms[0];
		
		if ( form.escolhaRelatorio.value != null &&
			 form.escolhaRelatorio.value != "" ) {

			form.ButtonImprimirRelacao.disabled = false;

		} else {

			form.ButtonImprimirRelacao.disabled = true;
		}
	}


	function botaoConcordanciaDemandas(){
		var form = document.forms[0];
		var informePeloMenosUmCampo = false;
		
		var page = "0";
		if ( form.manterPaginaAux.value != null && form.manterPaginaAux.value != "" ){
			page = form.manterPaginaAux.value;
		}
		
		for (var i=0;i < form.elements.length;i++){		

			var elemento = document.forms[0].elements[i];
			
			if (elemento.type == "checkbox"){
			
				if(elemento.checked){	
					informePeloMenosUmCampo = false;
					
					form.action = 'exibirManterOrdemProcessoRepavimentacaoAction.do?acao=concordanciaDemandas&page.offset=' + page;
					form.submit();
					break;
				
				} else {
					informePeloMenosUmCampo = true;
				}
			}
		}

		if (informePeloMenosUmCampo == true) {
			 alert("Selecione pelo menos um Processo de Repavimentação.");
		}
		
	}
	
	function imprimirRelacao(){

		abrirPopup("exibirImprimirRelacaoOrdemServicoRepavimentacaoAction.do",400 , 600);

//		var form = document.forms[0];
		
//		if (confirm("Apenas OS´s com observação de retorno ?")) {
//			form.indicadorOsObservacaoRetorno.value = '1';
//		}else{
//			form.indicadorOsObservacaoRetorno.value = '2';
//		}
		
//		toggleBox('demodiv',1);
	}


</script>


</head>

<body leftmargin="5" topmargin="5" onload="javascript:desabilitarBotaoImprimirRelacao();">

<html:form action="/exibirManterOrdemProcessoRepavimentacaoAction" method="post"
	name="FiltrarOrdemProcessoRepavimentacaoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.FiltrarOrdemProcessoRepavimentacaoActionForm" method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<html:hidden property="manterPaginaAux" />
	<html:hidden property="indicadorOsObservacaoRetorno"/>

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
        <tr bgcolor="#cbe5fe">
          <td width="11" bordercolor="#90c7fc"><img src="<bean:message key="caminho.imagens"/>parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" /></td>
          <td class="parabg" bordercolor="#90c7fc">Acompanhamento  do Processo de Repavimentação - Contrato Prefeituras</td>
          <td width="11" bordercolor="#90c7fc"><img src="<bean:message key="caminho.imagens"/>parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
        </tr>
        
         <tbody><tr>
		  <td><p>&nbsp;<br>	</p></td>
		 </tr>
        </tbody>
        
      </table>
      
      <table border= "0" width="100%" >	
 		  	
		  <tr>		  
		      <td width="16%">
		  	      <strong>Unidade Responsável: <font color="#ff0000"></font></strong>
		  	  </td>
		  	  
		  	  <td width="16%" align="left">		
		  		  <html:text property="descricaoUnidadeResponsavel"
		  		  	style="background-color:#EFEFEF; border:0;"  
		    				 size="10" 
		    				 maxlength="10"
		    				 readonly="true"/>  
		 	  </td>	 
		   	  <td width="20%" align="left">
		    	  <strong>Encerramento da OS: </strong> 
		  	  </td>
		  	  <td width="37%" align="left">
			      <html:text property="periodoEncerramentoOsInicial" 
			      	style="background-color:#EFEFEF; border:0;" 
			    			 size="10" 
			    			 maxlength="10" 
			    			 tabindex="2"
							 readonly="true"/>
              	  <strong>  a  </strong>
			      <html:text property="periodoEncerramentoOsFinal" 
			      	style="background-color:#EFEFEF; border:0;" 
							 size="10" 
							 maxlength="10" 
							 tabindex="3"  
							 readonly="true"/> 
		 	  </td>
		  </tr>		
		
 		  	
	      <tr>		  
		      <td width="16%" >
		          <strong>Situação do Retorno: <font color="#ff0000"></font></strong><strong><font color="#ff0000"></font></strong>
		  	  </td>
		  	  <td width="16%" align="left">                   
	              <html:text  property="situacaoRetornoDescricao" 
	              	style="background-color:#EFEFEF; border:0;" 
		    		   		  size="10" 
		    				  maxlength="10"
		    				  readonly="true"/>  
		 	  </td>		  	  
		  	  <td width="20%">
		          <strong>Retorno do Serviço:</strong> 
		  	  </td>
		  	  <td width="37%" align="left">
		    	  <html:text  property="periodoRetornoServicoInicial" 
		    	  	style="background-color:#EFEFEF; border:0;" 
		    				  size="10" 
		    				  maxlength="10" 
		    				  tabindex="5" 
		    				  readonly="true"/>  
	              <strong> a </strong> 
				  <html:text property="periodoRetornoServicoInicial" 
				  	style="background-color:#EFEFEF; border:0;" 
						     size="10" 
						     maxlength="10" 
						     tabindex="6" 
						     readonly="true" /> 
		 	  </td>		  
	  	  </tr>
	  	  
	  	  
	  	  <tr>
	  	      <td width="16%" > </td>
		  	  <td width="16%" align="left"> </td>
		  	  		  	  
		  	  <td width="20%">
		          <strong>Rejeição do Serviço:</strong> 
		  	  </td>
		  	  <td width="37%" align="left">
		    	  <html:text  property="periodoRejeicaoInicial"
		    	  		style="background-color:#EFEFEF; border:0;"  
		    				  size="10" 
		    				  maxlength="10" 
		    				  tabindex="5" 
		    				  readonly="true"/>  
	              <strong> a </strong> 
				  <html:text property="periodoRejeicaoFinal" 
				  			style="background-color:#EFEFEF; border:0;" 
						     size="10" 
						     maxlength="10" 
						     tabindex="6" 
						     readonly="true" /> 
		 	  </td>		
	  	  </tr>
	  	  
	  	  <tr bgcolor="#cbe5fe">
		      <td colspan="4"><p><HR><p><p></td>
	      </tr>
		  
	  </table>
	  
	  <table align="center" bgcolor="#99ccff" border="0" cellpadding="1" cellspacing="0" width="100%">
		      
		    <tr bgcolor="#cbe5fe">
				<td bordercolor="#90c7fc" border="0" width="30%">&nbsp;<strong>Município:</strong> </td>
              	<td bordercolor="#90c7fc" width="50%">
      				<html:text property="idMunicipio" 
						readonly="true"
						style="background-color:#EFEFEF; border:0;" 
						size="6"
						maxlength="6" />
						
					&nbsp;
      				<html:text property="municipioDescricao" 
						readonly="true"
						style="background-color:#EFEFEF; border:0;" 
						size="30"
						maxlength="30" />						
                </td>
			</tr>

		    <tr bgcolor="#cbe5fe">
				<td bordercolor="#90c7fc" border="0" width="30%">&nbsp;<strong>Bairro:</strong> </td>
              	<td bordercolor="#90c7fc" width="50%">
      				<html:text property="codigoBairro" 
						readonly="true"
						style="background-color:#EFEFEF; border:0;" 
						size="6"
						maxlength="6" />
					&nbsp;
      				<html:text property="bairroDescricao" 
						readonly="true"
						style="background-color:#EFEFEF; border:0;" 
						size="30"
						maxlength="30" />
						
                </td>
			</tr>
			
		    <tr bgcolor="#cbe5fe">
				<td bordercolor="#90c7fc" border="0" width="30%">&nbsp;<strong>Logradouro:</strong> </td>
              	<td bordercolor="#90c7fc" width="50%">
      				<html:text property="idLogradouro" 
						readonly="true"
						style="background-color:#EFEFEF; border:0;" 
						size="6"
						maxlength="6" />
					&nbsp;
      				<html:text property="logradouroDescricao" 
						readonly="true"
						style="background-color:#EFEFEF; border:0;" 
						size="30"
						maxlength="30" />						
                </td>
			</tr>
			
		      
		     
		      <tr bgcolor="#cbe5fe">
		 	      <td bordercolor="#90c7fc" border="0" width="30%">&nbsp;<strong>Ordens de Serviço Selecionadas:</strong> </td>
                	  <td bordercolor="#90c7fc" width="50%">
                 	      <html:text  property="ordensServicoSelecionadas" 
    				        	      size="10" 
    				        	      style="background-color:#EFEFEF; border:0;" 
    								  maxlength="10"
		     						  readonly="true"/> 
                      </td>
				</tr>
			    <tr bgcolor="#cbe5fe">
		      		<td colspan="4"><p><HR><p><p></td>
		     	</tr>
	  </table>

	   <!-- Início do Corpo -->
            <table border="0" cellpadding="0" cellspacing="0" width="100%">
            <tbody>
				<tr>
					<td colspan="11">&nbsp;</td>
				</tr>
				
				<tr>
                  <td colspan="11">
					<table bgcolor="#99ccff" width="100%">
					<tbody>
					  <tr bordercolor="#90c7fc" bgcolor="#cbe5fe"> 
			 			<td bgcolor="#90c7fc" width="6%">
						  <div align="center"><strong>
					    <a href="javascript:facilitador(this);" id="0">Todos</a></strong></div></td>
						<td bgcolor="#90c7fc" width="9%"><div align="center"><strong>O.S.</strong></div></td>
		                <td bgcolor="#90c7fc" width="29%"><div align="center"><strong>Endereço</strong></div></td>
				        <td bgcolor="#90c7fc" width="11%"><div align="center"><strong>Pvt.Rua.<p> T / (m2) </strong></div></td>
		                <td bgcolor="#90c7fc" width="9%"><div align="center"><strong>Pvt.Rua Ret.<p> T / (m2) </strong></div></td>
	                  	<td bgcolor="#90c7fc" width="9%"><div align="center"><strong> Dt.<p> Encerramento</strong></div></td>
	                  	<td bgcolor="#90c7fc" width="9%"><div align="center"><strong>Dt. <p> Retorno </strong></div></td>
	                  	<td bgcolor="#90c7fc" width="9%"><div align="center"><strong>Dt <p> Rejeição </strong></div></td>
				      </tr>
				      
				<tr bgcolor="#ffffff">
				
					<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
								<pg:param name="pg" />
								<pg:param name="q" />

							<logic:present name="collOrdemServicoPavimento">
								<%int cont = 1;%>								
									<logic:iterate name="collOrdemServicoPavimento" 
									id="oSPavimentoRetornoHelper" type="OSPavimentoRetornoHelper">
								
									<pg:item>
										<%cont = cont + 1;
											if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

											%>
										<tr bgcolor="#cbe5fe">
											<%}%>
												<td bordercolor="#90c7fc" align="center">													
													<input type="checkbox" name="idRegistro" 
													 	 value="<bean:write name="oSPavimentoRetornoHelper" property="ordemServico.id"/>"  
														 onchange="javascript:montarChaveOs(this);"> 
												</td>
												<td bordercolor="#90c7fc">
												
													<div align="center">											
													   	<% if  ((oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRuaRetorno() != null && 
													   			oSPavimentoRetornoHelper.getOrdemServicoPavimento().getIndicadorAceite() != null && 
													   			oSPavimentoRetornoHelper.getOrdemServicoPavimento().getIndicadorAceite().toString().equals("1")) ||
													   			oSPavimentoRetornoHelper.getOrdemServicoPavimento().getMotivoRejeicao() != null ) { %>
		
													       	    <bean:write name="oSPavimentoRetornoHelper" property="ordemServico.id" />
		
												       	<%} else { %>		
													       	<a href="javascript:abrirPopup('exibirAtualizarOrdemProcessoRepavimentacaoPopupAction.do?numeroOS=${oSPavimentoRetornoHelper.ordemServico.id}&page.offset='+ document.forms[0].manterPaginaAux.value , 475, 600);">
													       	    <bean:write name="oSPavimentoRetornoHelper" property="ordemServico.id" />
													       	</a>		
												       	<%} %>		
													</div>
												</td>
						
												<td bordercolor="#90c7fc">
													<div align="center">
														<logic:present  name="oSPavimentoRetornoHelper" property="endereco">	
														<logic:notEmpty name="oSPavimentoRetornoHelper" property="endereco">							
															 <a href="javascript:abrirPopup('exibirConsultarDadosOrdemServicoPopupAction.do?numeroOS=${oSPavimentoRetornoHelper.ordemServico.id}', 475, 600);">
														 	   <bean:write name="oSPavimentoRetornoHelper" property="endereco"/> 
															 </a>	
														</logic:notEmpty>
														</logic:present>	
													</div>
												</td>
												 
												<td bordercolor="#90c7fc">
													<div align="center">
														<logic:present  name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.pavimentoRua">												
													 		<bean:write name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.pavimentoRua.id"/> - 
													 		<bean:write name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.areaPavimentoRua"/>										
														</logic:present>
													
														<logic:notPresent  name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.pavimentoRua">										
															&nbsp;
														</logic:notPresent>
													</div>
												</td>
												
												<td bordercolor="#90c7fc"><div align="center"> 
													<div align="center">
														<logic:present  name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.pavimentoRuaRetorno">												
															<bean:write name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.pavimentoRuaRetorno.id"/> - 
															<bean:write name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.areaPavimentoRuaRetorno"/>
														  													
														</logic:present>
														
														<logic:notPresent  name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.pavimentoRuaRetorno">												
														&nbsp;
														</logic:notPresent>
													</div>
												</td>
												
												<td bordercolor="#90c7fc">
													<div align="center">
														<logic:present  name="oSPavimentoRetornoHelper" property="ordemServico.dataEncerramento">						
															<%=""+ Util.formatarData(((OSPavimentoRetornoHelper) oSPavimentoRetornoHelper).getOrdemServico().getDataEncerramento())%>
														</logic:present>
														
														<logic:notPresent  name="oSPavimentoRetornoHelper" property="ordemServico.dataEncerramento">												
														&nbsp;
														</logic:notPresent>
												
													</div>
												</td>
												
												<td bordercolor="#90c7fc">
													<div align="center">																								
												      	<logic:present  name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.dataExecucao">						
															<%=""+ Util.formatarData(((OSPavimentoRetornoHelper) oSPavimentoRetornoHelper).getOrdemServicoPavimento().getDataExecucao())%>
														</logic:present>
														
														<logic:notPresent  name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.dataExecucao">												
															&nbsp;
														</logic:notPresent>									         
										      		</div>
										      	</td>	
										      	
								      			<logic:present name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.dataRejeicao" > 
								      				
													<logic:present name="oSPavimentoRetornoHelper" property="hint1">
														<td valign="center" 
															onmouseover="this.T_BGCOLOR='whitesmoke';this.T_LEFT=true;return escape('<bean:write name="oSPavimentoRetornoHelper" property="hint1"/>');">
															
															<%=""+ Util.formatarData(((OSPavimentoRetornoHelper) oSPavimentoRetornoHelper).getOrdemServicoPavimento().getDataRejeicao())%>
														</td>
														
													</logic:present>
													
								      				<logic:notPresent name="oSPavimentoRetornoHelper" property="hint1">
								      					<td bordercolor="#90c7fc">
									      					<div align="center">
										      					<%=""+ Util.formatarData(((OSPavimentoRetornoHelper) oSPavimentoRetornoHelper).getOrdemServicoPavimento().getDataRejeicao())%>
										      				</div>
									      				</td>
								      				</logic:notPresent>
								      				
								      			</logic:present>
								      			
								      			<logic:notPresent name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.dataRejeicao" >
								      				<td bordercolor="#90c7fc">
									      				<div align="center">
								      						&nbsp;
								      					</div>
								      				</td>
								      			</logic:notPresent>
										      		
										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
      
			</tbody></table>
		</td>
	</tr>
	
	
	</tbody></table>
	
	
    <table border="0" width="100%">
	<tbody>
	
				<tr>
					<td colspan="3" style="width: 80px;">
					<div align="center">
						<strong>
						<%@ include file="/jsp/util/indice_pager_novo.jsp"%></strong>
					</div>
					</td>					
	         		</pg:pager>
				</tr>
				
				<tr>
					<td align="left" height="24%">			
						<input name="button" 
							type="button" 	
							class="bottonRightCol" 
							value="Confirmar Demandas" 
							onclick="javascript:botaoConcordanciaDemandas();"
							align="left" 
							style="width: 150px;">
			  		</td>
				  
				  	<td colspan="3" align="right" width="15%">
					 	<input name="ButtonImprimirOs"  
					 		type="button" 
					 		class="bottonRightCol"  
					 		onClick="javascript:imprimirOS();" 
					 		value="Imprimir OS">
				  	</td>
				</tr>
				
				<tr>
					<td align="left" height="24">			
						<input name="button" 
							type="button" 	
							class="bottonRightCol" 
							value="Voltar Filtro" 
							onclick="window.location.href='<html:rewrite page="/exibirFiltrarOrdemProcessoRepavimentacaoAction.do?limpar=S"/>'"
							align="left" 
							style="width: 80px;">
					</td>
			  		
					<td align="left">
		                <input align="center" 
		                	name="ButtonImprimirRelacao" 
		                	class="bottonRightCol" 
		                	onClick="javascript:imprimirRelacao();" 
		                	value="Imprimir Relação" type="button">			 
	                </td>					
			   </tr>
				
				
<%-- Fim do esquema de paginação --%>
	</table>
	
	
            
			</td>
		</tr>
	</table>  
    
     <%@ include file="/jsp/util/rodape.jsp"%>        

</body>
<%@ include file="/jsp/util/tooltip.jsp"%>
</html:form>
</html:html>
