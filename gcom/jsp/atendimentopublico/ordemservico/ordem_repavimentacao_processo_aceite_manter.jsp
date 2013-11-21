<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.atendimentopublico.ordemservico.bean.OSPavimentoRetornoHelper"%>


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

function verificarExibicaoRelatorio() {
	var form = document.forms[0];
	
	toggleBox('demodiv',1);
	
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

function montarChaveOs(obj){
	var chave = "";
	for (var i=0;i < document.forms[0].elements.length;i++){		
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
	document.forms[0].registrosHidden.value = chave;
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

function botaoAceitarOSConvergente(){

	var form = document.forms[0];
	
	abrirPopupComSubmit('exibirAtualizarOrdemRepavimentacaoProcessoAceitePopupAction.do?numeroOS=' + document.forms[0].registrosHidden.value +'&acao=aceitarOSConvergente' + '&page.offset='+ document.forms[0].manterPaginaAux.value, 300, 700, 'atualizar');
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
				
				abrirPopupComSubmit('exibirAtualizarOrdemRepavimentacaoProcessoAceitePopupAction.do?numeroOS=' + document.forms[0].registrosHidden.value + '&page.offset='+ document.forms[0].manterPaginaAux.value + 'acao=botao' , 300, 700, 'atualizar');
				
			//	form.action = 'exibirAtualizarOrdemRepavimentacaoProcessoAceitePopupAction.do';
			//	form.submit();
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
	
-->
</script>


</head>

<body leftmargin="5" topmargin="5" onload="">

<html:form action="/exibirManterOrdemRepavimentacaoProcessoAceiteAction" method="post"
	name="FiltrarOrdemRepavimentacaoProcessoAceiteActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.FiltrarOrdemRepavimentacaoProcessoAceiteActionForm" method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
			<html:hidden property="manterPaginaAux" />
			<html:hidden property="registrosHidden" />

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
          <td class="parabg" bordercolor="#90c7fc">Ordens de Repavimentação em Processo de Aceite</td>
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
		    				 size="10" 
		    				 maxlength="10"
		    				 disabled="true"/>  
		 	  </td>	 
		   	  <td width="20%" align="left">
		    	  <strong>Retorno do Serviço: </strong> 
		  	  </td>
		  	  <td width="37%" align="left">
			      <html:text property="periodoRetornoServicoInicial" 
			    			 size="10" 
			    			 maxlength="10" 
			    			 tabindex="2"
							 disabled="true"/>
              	  <strong>  a  </strong>
			      <html:text property="periodoRetornoServicoFinal" 
							 size="10" 
							 maxlength="10" 
							 tabindex="3"  
							 disabled="true"/> 
		 	  </td>
		  </tr>		
		
		  <p><BR></p>	
 		  	
	      <tr>		  
		      <td width="16%" >
		          <strong>Situação do Aceite: <font color="#ff0000"></font></strong><strong><font color="#ff0000"></font></strong>
		  	  </td>
		  	  <td width="16%" align="left">                   
	              <html:text  property="situacaoAceiteDescricao" 
		    		   		  size="10" 
		    				  maxlength="10"
		    				  disabled="true"/>  
		 	  </td>		  	  
		  	  <td width="20%">
		          <strong>Aceite do Serviço:</strong> 
		  	  </td>
		  	  <td width="37%" align="left">
		    	  <html:text  property="periodoAceiteServicoInicial" 
		    				  size="10" 
		    				  maxlength="10" 
		    				  tabindex="5" 
		    				  disabled="true"/>  
	              <strong> a </strong> 
				  <html:text property="periodoAceiteServicoFinal" 
						     size="10" 
						     maxlength="10" 
						     tabindex="6" 
						     disabled="true" /> 
		 	  </td>		  
	  	  </tr>
		  

	  </table>
	  
	  
	 
	 <table border="0">
		<tr bgcolor="#cbe5fe">
		  	<td width="18%">
				<strong>Unidade de Encerramento: <font color="#ff0000"></font></strong>			 
			</td>
			<td width="12%"  align="left">
				<html:text property="idUnidadeOrganizacional" 
			   size="5"
			   maxlength="4"
			   disabled="true"/>  
			</td>
			<td>
				<html:text property="descricaoUnidadeOrganizacional" 
			   size="40"
			   maxlength="40"
			   disabled="true"/>
			</td>
		</tr>	
		<tr bgcolor="#cbe5fe">
		      <td colspan="4"><p><HR><p><p></td>
	     </tr>			
	</table>			
			

	  <table align="center" bgcolor="#99ccff" border="0" cellpadding="0" cellspacing="0" width="100%">
	      <tbody>
		      <tr bgcolor="#cbe5fe">
		 	      <td bordercolor="#90c7fc" border="0" width="30%">&nbsp;<strong>Ordens de Serviço Selecionadas:</strong> </td>
                	  <td bordercolor="#90c7fc" width="50%">
                 	      <html:text  property="ordensServicoSelecionadas" 
    				        	      size="10" 
    								  maxlength="10"
		     						  disabled="true"/> 
                      </td>
				</tr>
			    <tr bgcolor="#cbe5fe">
		      		<td colspan="4"><p><HR><p><p></td>
		     	</tr>
		  </tbody>
	  </table>

	  <!-- Início do Corpo -->
      <table border="0" cellpadding="0" cellspacing="0" width="100%">
          <tbody>
		      <tr>
			      <td colspan="11">&nbsp;</td>
			  </tr>
				
				<tr>
                  <td colspan="11">
					<table bgcolor="#99ccff" width="100%" border="0">
					<tbody>
					  <tr bordercolor="#90c7fc" bgcolor="#cbe5fe"> 
			 			<td bgcolor="#90c7fc" width="6%">
						  <div align="center"><strong>
					      <a href="javascript:facilitador(this);" id="0">Todos</a></strong></div>
					    </td>
						<td width="9%" ><div align="center"><strong>O.S.</strong></div></td>
		                <td width="85%" bgcolor="#90c7fc">
			                <table bgcolor="#99ccff" width="100%" border="0">
			                	<tr bordercolor="#90c7fc" bgcolor="#cbe5fe">
					                <td width="83%"  colspan="6"><div align="center">
					                	<strong>Endereço</strong></div>
					                </td>
					                <td width="17%"  colspan="2"><div align="center">
					                	<strong>Motivo</strong></div>
					                </td>
				                </tr>
				                <tr bordercolor="#90c7fc" bgcolor="#cbe5fe">
							        <td width="110" ><div align="center"><strong><p>Pvt.Rua</p> T / (m2)</strong></div></td>
							        <td width="110" ><div align="center"><strong><p>Pvt.Rua-Ret</p> T / (m2)</strong></div></td>
					                <td width="90" ><div align="center"><strong><p>Pvt.Cal</p>T / (m2)</strong></div></td>
					                <td width="110" ><div align="center"><strong><p>Pvt.Cal-Ret</p> T / (m2)</strong></div></td>
				                  	<td width="90" ><div align="center"><strong>Dt.Retorno</strong></div></td>
				                  	<td width="90" ><div align="center"><strong>Dt.Rejeição</strong></div></td>
				                  	<td width="90" ><div align="center"><strong>Aceite</strong></div></td>
							        <td width="90" ><div align="center"><strong>Dt.Aceite</strong></div></td>								
						        </tr>
						        
						    </table>
					    </td>
				      </tr>

				<tr bgcolor="#ffffff">
				
				<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
						export="currentPageNumber=pageNumber;pageOffset"
						maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg" />
							<pg:param name="q" />

					<logic:present name="collOrdemServicoPavimentoAceite">
						<%int cont = 1;%>								
							<logic:iterate name="collOrdemServicoPavimentoAceite" 
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
										    <% if  ((oSPavimentoRetornoHelper.getOrdemServicoPavimento().getIndicadorAceite() != null && 
										    			!oSPavimentoRetornoHelper.getOrdemServicoPavimento().getIndicadorAceite().toString().equals("2")) ||
										    			oSPavimentoRetornoHelper.getOrdemServicoPavimento().getMotivoRejeicao() != null) { %>												
											<input type="checkbox" 
												   name="idRegistro" 
											 	   value="<bean:write name="oSPavimentoRetornoHelper" property="ordemServico.id"/>"  
												   onchange="javascript:montarChaveOs(this);"
												   disabled="disabled" > 
										    <%} else { %>
										    	<input type="checkbox" 
												       name="idRegistro" 
											 	       value="<bean:write name="oSPavimentoRetornoHelper" property="ordemServico.id"/>"  
												       onchange="javascript:montarChaveOs(this);"> 
										    <%} %>
										</td>
										<td bordercolor="#90c7fc">
											<div align="center">											
											   	<% if  ((oSPavimentoRetornoHelper.getOrdemServicoPavimento().getIndicadorAceite() != null &&
											   			!oSPavimentoRetornoHelper.getOrdemServicoPavimento().getIndicadorAceite().toString().equals("2")) ||
											   			oSPavimentoRetornoHelper.getOrdemServicoPavimento().getMotivoRejeicao() != null) { %>
											       	<bean:write name="oSPavimentoRetornoHelper" property="ordemServico.id" />
										       	<%} else { %>		
											       	<logic:notPresent name="usuarioTipoRepavimentadora" scope="session">
												       	<a href="javascript:abrirPopup('exibirAtualizarOrdemRepavimentacaoProcessoAceitePopupAction.do?numeroOS=${oSPavimentoRetornoHelper.ordemServico.id}&page.offset='+ document.forms[0].manterPaginaAux.value + '&acao=link' , 300, 700);">
												       	    <bean:write name="oSPavimentoRetornoHelper" property="ordemServico.id" />
												       	</a>
											       	</logic:notPresent>
											       	
											       	<logic:present name="usuarioTipoRepavimentadora" scope="session">
											       		<bean:write name="oSPavimentoRetornoHelper" property="ordemServico.id" />
											       	</logic:present>		
										       	<%} %>		
											</div>
										</td>
				
										<td bgcolor="#99ccff">
											
													
											<table width="100%" border="0">
											<% if (cont % 2 == 0) {%>
											<tr bgcolor="#FFFFFF">
												<%} else {
	
												%>
											<tr bgcolor="#cbe5fe" >
												<%}%>
												<td colspan="6" width="83%" >
												<div width="83%">
													<logic:present  name="oSPavimentoRetornoHelper" property="endereco">	
														<logic:notEmpty name="oSPavimentoRetornoHelper" property="endereco">							
															 <a href="javascript:abrirPopup('exibirConsultarDadosOrdemServicoPopupAction.do?numeroOS=${oSPavimentoRetornoHelper.ordemServico.id}', 475, 600);">
														 	   <bean:write name="oSPavimentoRetornoHelper" property="endereco" /> 
															 </a>	
														</logic:notEmpty>
													</logic:present>
												</div>
												</td>

												<td colspan="2" width="17%">
												<div width="17%" >
													<logic:present  name="oSPavimentoRetornoHelper" property="motivo">	
														<logic:notEmpty name="oSPavimentoRetornoHelper" property="motivo">							
															 <a href="javascript:abrirPopup('exibirConsultarDescricaoMotivoAceiteRepavimentacaoPopupAction.do?idOSPav=${oSPavimentoRetornoHelper.ordemServicoPavimento.id}', 400, 500);">
														 	   <bean:write name="oSPavimentoRetornoHelper" property="motivo" /> 
															 </a>	
														</logic:notEmpty>
													</logic:present>
												</div>
												</td>
											</tr>
											</table>
											
											<table bgcolor="#99ccff">
											<% if (cont % 2 == 0) {%>
											<tr bgcolor="#FFFFFF">
												<%} else {
	
												%>
											<tr bgcolor="#cbe5fe">
												<%}%>
												<td align="center" width="110">
													<div width="110" >
														<logic:present  name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.pavimentoRua">												
															<bean:write name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.pavimentoRua.descricaoAbreviada"/> - 
														 	<bean:write name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.areaPavimentoRua"/>
														</logic:present>
														
														<logic:notPresent  name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.pavimentoRua">												
															&nbsp;
														</logic:notPresent>
												  	</div>	
												</td>	

												<td align="center" width="110" >
													<div width="110" > 
														<logic:present  name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.pavimentoRuaRetorno">												
															<bean:write name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.pavimentoRuaRetorno.descricaoAbreviada"/> - 
													 		<bean:write name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.areaPavimentoRuaRetorno"/>
														</logic:present>
														
														<logic:notPresent  name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.pavimentoCalcada">												
															&nbsp;
														</logic:notPresent>
												  	</div>	
												</td>
												 
												<td align="center" width="90">
													<div width="90" align="center">
														<logic:present  name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.pavimentoCalcada">												
															<bean:write name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.pavimentoCalcada.descricaoAbreviada"/> - 
														 	<bean:write name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.areaPavimentoCalcada"/>
														</logic:present>
														
														<logic:notPresent  name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.pavimentoCalcada">												
															&nbsp;
														</logic:notPresent>
												  	</div>	
												</td>

												<td align="center" width="110">																								
											      	<div width="110" align="center">
														<logic:present  name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.pavimentoCalcadaRetorno">												
															<bean:write name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.pavimentoCalcadaRetorno.descricaoAbreviada"/> - 
														 	<bean:write name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.areaPavimentoCalcadaRetorno"/>
														</logic:present>
														
														<logic:notPresent  name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.pavimentoCalcada">												
															&nbsp;
														</logic:notPresent>	
												  	</div>						         
										      	</td>											
											   
										     	<td align="center" width="90">
						                            <div width="90" align="center">   
							                             <logic:present  name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.dataExecucao">	
							                             	<%=""+ Util.formatarData(((OSPavimentoRetornoHelper) oSPavimentoRetornoHelper).getOrdemServicoPavimento().getDataExecucao())%>		
							                             </logic:present>
						                             
						                             	<logic:notPresent  name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.dataExecucao">												
															&nbsp;
														</logic:notPresent>						                                
												 	</div>
												 </td>	
												 
												 <td align="center" width="90">
             									 	<div width="90" align="center">
							                            <logic:present  name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.dataRejeicao">						
															<%=""+ Util.formatarData(((OSPavimentoRetornoHelper) oSPavimentoRetornoHelper).getOrdemServicoPavimento().getDataRejeicao())%>
														</logic:present>
													
														<logic:notPresent  name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.dataRejeicao">												
															&nbsp;
														</logic:notPresent>
													</div>	
												 </td>
												 
												 <td align="center" width="90">
												 	<div width="90" align="center">
												 		<% if  (oSPavimentoRetornoHelper.getOrdemServicoPavimento().getIndicadorAceite() == null) { %>														
														<logic:present  name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.indicadorAceite">	
							                                &nbsp;
							                            </logic:present>
							                            <%} else if (oSPavimentoRetornoHelper.getOrdemServicoPavimento().getIndicadorAceite().compareTo(new Short("1"))==0) { %>	
							                            	Aceita
							                            <%} else if (oSPavimentoRetornoHelper.getOrdemServicoPavimento().getIndicadorAceite().compareTo(new Short("2"))==0) {%>	
							                            	Não Aceita
							                            <%} %>        										 
             									 	</div>
             									 </td>
            									 
             									 <td align="center" width="90">
             									 	<div width="90" >
							                            <logic:present  name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.dataAceite">						
															<%=""+ Util.formatarData(((OSPavimentoRetornoHelper) oSPavimentoRetornoHelper).getOrdemServicoPavimento().getDataAceite())%>
														</logic:present>
													
														<logic:notPresent  name="oSPavimentoRetornoHelper" property="ordemServicoPavimento.dataAceite">												
															&nbsp;
														</logic:notPresent>
													</div>	
												 </td>
											</tr>											
											</table>
										</td>
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
			<tr align="center">
				<td colspan="7">
				<div align="center"><strong><%@ include
					file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
				</td>					
	        		</pg:pager>
			</tr>
				
			<tr>
				<td align="left" width="10">			
					<input name="button" 
					       type="button" 	
					       class="bottonRightCol" 
					       value="Voltar Filtro" 
					       onclick="window.location.href='<html:rewrite page="/exibirFiltrarOrdemRepavimentacaoProcessoAceiteAction.do?limpar=S"/>'"
						   align="left" style="width: 80px;">
				</td>
				
				<logic:present name="usuarioTipoRepavimentadora" scope="session">
					<td align="left" width="15" >
					 	<input name="ButtonImprimirOs" 
					 		   disabled="disabled" 
					 		   type="button" 
					 		   class="bottonRightCol"  
					 		   onClick="javascript:botaoConcordanciaDemandas();" 
					 		   value="Informar Aceite">    	 
				    </td>
			    </logic:present>
			    
			    <logic:notPresent name="usuarioTipoRepavimentadora" scope="session">
					
					<logic:present name="exiteOrdemRepavSemAceite" scope="session">
						<logic:equal name="exiteOrdemRepavSemAceite" value="true">
							<td align="left" width="15" >
							 	<input name="ButtonImprimirOs" type="button" class="bottonRightCol"  
							 		   onClick="javascript:botaoConcordanciaDemandas();" 
							 		   value="Informar Aceite">    	 
				    		</td>
						</logic:equal>
						<logic:notEqual name="exiteOrdemRepavSemAceite" value="true">
							<td align="left" width="15" >
							 	<input name="ButtonImprimirOs" type="button" class="bottonRightCol"  
							 		   onClick="javascript:botaoConcordanciaDemandas();" disabled="disabled"
							 		   value="Informar Aceite">    	 
				    		</td>
						</logic:notEqual>
					</logic:present>
					
					<logic:notPresent name="exiteOrdemRepavSemAceite" scope="session">
						<td align="left" width="15" >
						 	<input name="ButtonImprimirOs" type="button" class="bottonRightCol"  
						 		   disabled="disabled"
						 		   onClick="javascript:botaoConcordanciaDemandas();" 
						 		   value="Informar Aceite">    	 
				    	</td>
					</logic:notPresent>
					  
			    </logic:notPresent>
			</tr>
				
			<tr>	
                <td align="right" colspan="7">
				    <div align="right">
					    <a href="javascript:verificarExibicaoRelatorio();">
						<img border="0"
							src="<bean:message key="caminho.imagens"/>print.gif"
							title="Imprimir" /> </a>
				    </div>
				</td>				
		   </tr>
	<%-- Fim do esquema de paginação --%>
	</tbody>
	</table>
       
			</td>
		</tr>
	</table>  
    
   	 <jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelacaoOrdemRepavimentacaoProcessoAceiteAction.do" />
     <%@ include file="/jsp/util/rodape.jsp"%>        

</body>
</html:form>
</html:html>
