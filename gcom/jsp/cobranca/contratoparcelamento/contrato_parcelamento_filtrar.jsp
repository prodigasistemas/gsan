<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">


<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
	
 <link rel="stylesheet" href="<bean:message key="caminho.css"/>jquery.autocomplete.css" type="text/css" />
	
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="FiltrarContratoParcelamentoClienteActionForm" dynamicJavascript="false" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js" ></script>
	
 <script src="<bean:message key="caminho.js"/>jquery/jquery.js"></script>
 <script type="text/javascript" src="<bean:message key="caminho.js"/>jquery/jquery.autocomplete.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.js"/>popup.js"></script>	
	
<script language="JavaScript" 
	type="text/JavaScript"><!--


     
     function validaForm(){
     	document.forms[0].action = "filtrarContratoParcelamentoClienteAction.do";
     	document.forms[0].submit();
     }
     
	
	function limparForm(){
		var form = document.forms[0];
		
		
		form.idContratoParcelamento.value = "";
		form.idContratoAnterior.value = "";
		form.idClienteContrato.value = "";
		form.contratoClienteDescricaoFiltro.value = "";
		form.idUsuarioResponsavel.value = "";
		form.contratoUsuarioResponsavelDescricaoFiltro.value = "";
		form.periodoNegociacaoInicial.value = "";
		form.periodoNegociacaoFinal.value = "";
		form.periodoImplantacaoInicial.value = "";
		form.periodoImplantacaoFinal.value = "";
		
	 	for(i=0;i<form.situacaoPagamento.length;i++){

	 		if(form.situacaoPagamento[i].value == "0")
	 			form.situacaoPagamento[i].checked = true;
	 		else
	 			form.situacaoPagamento[i].checked = false;
 		}
		
		for(i=0;i<form.situacaoCancelamento.length;i++){

	 		if(form.situacaoCancelamento[i].value == "0")
	 			form.situacaoCancelamento[i].checked = true;
	 		else
	 			form.situacaoCancelamento[i].checked = false;
 		}
 		
 		
		form.periodoCancelamentoInicial.value = "";
	 	form.periodoCancelamentoFinal.value = "";
	 	
	 	form.periodoCancelamentoInicial.disabled = false;
	 	form.periodoCancelamentoFinal.disabled = false;
	 	form.colecaoContratoMotivoCancelamento.disabled = false;
		 	
		 	
	 	for (i=0;i<form.colecaoContratoMotivoCancelamento.options.length;i++)
			form.colecaoContratoMotivoCancelamento.options[i].selected=false; 
	}
	
	function replicaData(campoInicial,campoFinal){
     campoFinal.value = campoInicial.value;
	}		
	
	/* Chama Popup */ 
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			} else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				} else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	 }
	
	 function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
			var form = document.forms[0];
		    if(tipoConsulta == 'usuario'){
		    
	  	      	form.loginUsuario.value = codigoRegistro;
		      	form.nomeUsuario.value = descricaoRegistro;
		      	form.nomeUsuario.style.color = '#000000';
      		}
      		else if(tipoConsulta == 'contratoParcelamento'){
      			form.idContratoAnterior.value = descricaoRegistro;
      		}
	 }
	 
	 function recuperarDadosPopupClienteContrParcel(codigoRegistro, descricaoRegistro,cnpj,tipoConsulta){
		var form = document.forms[0];
	 	if(tipoConsulta == 'cliente'){
		    	form.clienteAutocomplete.value = codigoRegistro + " - " + descricaoRegistro;
		    	//form.contratoClienteDescricaoFiltro.style.color="#000000";
  		}
	 }
	 
	 function limparClienteContrato(){
		var form = document.forms[0];
		form.clienteAutocomplete.value = "";
		form.cnpjCpfCliente.value = "";
	 }
         
	function limparUsuario() {
		var form = document.forms[0];
		
      	form.loginUsuario.value = "";
	    form.nomeUsuario.value = "";
	
		form.loginUsuario.focus();
	}	
	
	 function reloadCancelamento(obj){
	 	var valor = obj.value;
		var form = document.forms[0];	 	
	 	if(valor == 1){
		 	form.periodoCancelamentoInicial.disabled = true;
		 	form.periodoCancelamentoFinal.disabled = true;
		 	form.colecaoContratoMotivoCancelamento.disabled = true;
		 	
		 	form.periodoCancelamentoInicial.value = "";
		 	form.periodoCancelamentoFinal.value = "";
		 	
		 	for (i=0;i<form.colecaoContratoMotivoCancelamento.options.length;i++)
				form.colecaoContratoMotivoCancelamento.options[i].selected=false; 
	 	}
	 	else{
		 	form.periodoCancelamentoInicial.disabled = false;
		 	form.periodoCancelamentoFinal.disabled = false;
		 	form.colecaoContratoMotivoCancelamento.disabled = false;
	 	}
	 
	 }


//
--></script>

<script language="JavaScript">
	function verificaQtdItensUsuario(row, i) { 
		if(i == 200){
			var div = document.getElementById("autocompleteMsgUsuario");
			div.style.display = "block";
			select.hideResultsNow();
		}
		return row[0]; 
	}
	function verificaQtdItensCliente(item, i) { 
		if(i == 200){
			var div = document.getElementById("autocompleteMsgCliente");
			div.style.display = "block";
			select.hideResultsNow();
		}
		
		return item.resultado;
	}

	function parse_cliente(data){
		 return $.map(data, function(item) {
                return {
                        data: item,
                        value: item.resultado,
                        result: item.resultado
                }
        });
	}

	 $(document).ready(function(){
	     	$("#clienteAutocomplete").autocomplete("autocomplete?method=1",
	     			{
  			width:450,
	     		delay:1000,
	     		minChars:3,
	     		matchSubset:1,
	     		max: 200,
	     		mustMatch: true,
	     		matchContains:1,
	     		cacheLength:1,
	     		autoFill:false,
	     		parse: parse_cliente, 
	     		dataType:'json',
				formatItem: verificaQtdItensCliente
		  }).result(function(event, data, formatted) {
			 	 var form = document.forms[0];
				form.cnpjCpfCliente.value = data.cnpj;
				esconderDivMsg('autocompleteMsgCliente');
			});
	  });

</script>

</head>

<body leftmargin="5" topmargin="5">
<html:form action="/filtrarContratoParcelamentoClienteAction.do" 
		   name="FiltrarContratoParcelamentoClienteActionForm" 
		   type="gcom.gui.cobranca.contratoparcelamento.FiltrarContratoParcelamentoClienteActionForm"
		   method="post">

<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext">
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
			<td width="660" valign="top" class="centercoltext">
				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
			
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
					<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Filtrar Contrato de Parcelamento por Cliente</td>
					<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
              </tr>
            </table>
            <table width="100%" border="0" >
			<tr> 
				<td colspan="2">
					Para manter o(s) contrato(s) 
					  de parcelamento por cliente, informe os dados abaixo:</td>
					<td width="90">
				</td>
			</tr>
            <tr> 
                <td width="100"><strong>N&uacute;mero do Contrato:</strong></td>
                <td colspan="2">
	                <strong>
	                  <html:text maxlength="10"
	                  			 size="10"
	                  			 property="idContratoParcelamento"
	                  			 />
	                </strong>
                </td>
              </tr> 	
              
              <tr>
                <td><strong>N&uacute;mero do Contrato Anterior:</strong></td>
                <td colspan="2"><strong>
                  <html:text maxlength="10"
                  			 size="10"
                  			 tabindex="1"
                  			 property="idContratoAnterior"
                  			 onkeypress="javascript:validaEnter(event, 'exibirFiltrarContratoParcelamentoClienteAction.do?pesquisarContratoAnterior=1', 'idContratoAnterior');return isCampoNumerico(event);" />
                <a href="javascript:abrirPopup('exibirContratoParcelamentoPesquisar.do?indicadorUsoTodos=1&limparForm=OK&tipoConsulta=contratoAnterior&indicadorPesquisa=1', 500, 700);">
					<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
					     title="Pesquisar Contrato Anterior" /></a>
              </tr>
              
              <tr>
		          <td class="style3"><strong>Usu&aacute;rio  Respons&aacute;vel:</strong></td>
		          
		           <td colspan="6"><span class="style2"><strong>
						<html:text property="loginUsuario" 
							size="12" 
							maxlength="11"
							style="text-transform: none;"
							onkeypress="javascript:pesquisaEnterSemUpperCase(event, 'exibirFiltrarContratoParcelamentoClienteAction.do?consulta=usuario', 'loginUsuario');"/>
						
						<a href="javascript:chamarPopup('exibirUsuarioPesquisar.do?limparForm=OK&tipoConsulta=usuario&mostrarLogin=S', 'usuario', null, null, 370, 600, '',document.forms[0].loginUsuario);">
							<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" name="imagem" alt="Pesquisar" border="0" title="Pesquisar Usuário"></a>
							 
						<logic:present name="usuarioEncontrado" scope="session">
							<html:text property="nomeUsuario" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" 
								size="36" 
								maxlength="36" />
								
						</logic:present> 
		
						<logic:notPresent name="usuarioEncontrado" scope="session">
							<html:text property="nomeUsuario" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" 
								size="36" 
								maxlength="36" />
						</logic:notPresent>
						
						<a href="javascript:limparUsuario();">
							<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
						</a>
		                 </strong></span>
	              </td>
		      </tr>
              <tr>
		          <td class="style3"><strong>Cliente do Contrato:</strong></td>
		          <td colspan="5">
		          
		          
		          <div class="mensagem" id="autocompleteMsgCliente">
		                	<img onclick="esconderDivMsg('autocompleteMsgCliente');" src="<bean:message key="caminho.imagens"/>close.png" style="cursor: pointer; margin-left: 270px; position: absolute; margin-top: -10px; "  />
		                	Sua pesquisa encontrou mais de 200 registros. <br />
		                	Digite mais caracteres para filtrar os resultados!
		                </div>
		          <strong>
		            <c:if test="${idClienteContrato != null && idClienteContrato != ''}">
		                <bean:define id="clienteAutocomplete" value="${idClienteContrato} - ${contratoClienteDescricaoFiltro}"></bean:define>
						<bean:define id="clienteCnpj" value="${clienteAutocompleteCNPJ}"></bean:define>
					</c:if>
					
		            <input type="text" value="${clienteAutocomplete}" onblur="esconderDivMsg('autocompleteMsgCliente');" onchange="esconderDivMsg('autocompleteMsgCliente');" onkeypress="esconderDivMsg('autocompleteMsgCliente');"  size="40" maxlength="65" id="clienteAutocomplete" name="clienteAutocomplete" title="Campo auto-completável, retorna resultados da busca a partir de três caracteres digitados." />
		            	<a id="btBuscarCliente"
							href="javascript:abrirPopup('exibirPesquisarClienteAction.do?indicadorUsoTodos=1&limparForm=OK&tipoConsulta=cliente', 400, 800); ">
						<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Cliente" /></a> 
						<input type="text" value="${clienteCnpj}" style="border:0; background: #EFEFEF;" size="15" readonly="readonly"  disabled="disabled" name="cnpjCpfCliente" />
		        	</strong>
		       			<a href="javascript:limparClienteContrato();">
							<img border="0" title="Apagar" src="/gsan/imagens/limparcampo.gif">
						</a>
		        	</td>
		        </tr>
              
			<tr>
				<td height="25" colspan="3">
					<div align="left">
						<div align="left">
							<div align="left">
								<hr>
							</div>
						</div>
					</div>
				</td>
			</tr>
              
              <tr>
                <td height="25"><strong>Per&iacute;odo  Negocia&ccedil;&atilde;o 
                  do Contrato<font color="#000000">:</font></strong></td>
					<td height="25" colspan="2">
					<html:text property="periodoNegociacaoInicial" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event);replicaData(document.forms[0].periodoNegociacaoInicial,document.forms[0].periodoNegociacaoFinal);" 
								onkeypress="return isCampoNumerico(event);"
								
								value=""/>
					<a href="javascript:abrirCalendario('FiltrarContratoParcelamentoClienteActionForm', 'periodoNegociacaoInicial')">			
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/></a>
					a 
					<html:text property="periodoNegociacaoFinal" 
						size="11" 
						maxlength="10" 
						tabindex="3" 
						onkeyup="mascaraData(this, event)"  
						onkeypress="return isCampoNumerico(event);"

						value=""/>
					<a href="javascript:abrirCalendario('FiltrarContratoParcelamentoClienteActionForm', 'periodoNegociacaoFinal')">			
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/></a> (99/99/9999)
					
					<div align="left"></div>
				</td>
              </tr>
              
              <tr>
                <td height="25"><strong>Per&iacute;odo   Implanta&ccedil;&atilde;o 
                  do Contrato<font color="#000000">:</font></strong></td>
                <td height="25" colspan="2"><html:text property="periodoImplantacaoInicial" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event);replicaData(document.forms[0].periodoImplantacaoInicial,document.forms[0].periodoImplantacaoFinal);" 
								onkeypress="return isCampoNumerico(event);"

								value=""/>
					<a href="javascript:abrirCalendario('FiltrarContratoParcelamentoClienteActionForm', 'periodoImplantacaoInicial')">			
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/></a>
					a 
					<html:text property="periodoImplantacaoFinal" 
						size="11" 
						maxlength="10" 
						tabindex="3" 
						onkeyup="mascaraData(this, event)"  
						onkeypress="return isCampoNumerico(event);"

						value=""/>
					<a href="javascript:abrirCalendario('FiltrarContratoParcelamentoClienteActionForm', 'periodoImplantacaoFinal')">			
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/></a> (99/99/9999)

					<div align="left"></div>
				</td>
              </tr>
              
			<tr>
				<td height="25" colspan="3">
					<div align="left">
						<div align="left">
							<div align="left">
								<hr>
							</div>
						</div>
					</div>
				</td>
			</tr>
              	
            <tr>
                <td height="25"><strong>Situa&ccedil;&atilde;o  Pagamento
                  do Contrato<font color="#000000">:</font></strong></td>

                <td height="25" colspan="2">
	                <span class="style2">
	                  <strong> 
						  <html:radio property="situacaoPagamento" value="0" onclick="javascript: reload();"/>
		 				  Todos
						  <html:radio property="situacaoPagamento" value="1" onclick="javascript: reload();"/>
		 				  Pendentes
						  <html:radio property="situacaoPagamento" value="2" onclick="javascript: reload();"/>
						  Pagos
						  <br>
					  </strong>
					</span>
				</td>  	
			</tr>
			
			 <tr>
				<td height="25" colspan="3">
					<div align="left">
						<div align="left">
							<div align="left">
								<hr>
							</div>
						</div>
					</div>
				</td>
			</tr>
			
			 <tr>
                <td height="25"><strong> Situa&ccedil;&atilde;o  Cancelamento do Contrato<font color="#000000">:</font></strong></td>
                
                <td height="25" colspan="2">
	                <span class="style2">
	                  <strong> 
						  <html:radio property="situacaoCancelamento" value="0" onclick="javascript: reloadCancelamento(this);"/>
		 				  Todos
						  <html:radio property="situacaoCancelamento" value="1" onclick="javascript: reloadCancelamento(this);"/>
		 				  Não Cancelados
						  <html:radio property="situacaoCancelamento" value="2" onclick="javascript: reloadCancelamento(this);"/>
						  Cancelados
						  <br>
					  </strong>
					</span>
					<div align="left"> </div>
				</td>
              </tr>
              
              <tr>
                <td height="25"><strong>Per&iacute;odo  Cancelamento 
                  do Contrato<font color="#000000">:</font></strong></td>
                <td height="25" colspan="2">
	                <html:text property="periodoCancelamentoInicial" 
									size="11" 
									maxlength="10" 
									tabindex="3" 
									onkeyup="mascaraData(this, event)" 
									onkeypress="return isCampoNumerico(event);replicaData(document.forms[0].periodoCancelamentoInicial,document.forms[0].periodoCancelamentoFinal);"
									value=""/>
					<a href="javascript:abrirCalendario('FiltrarContratoParcelamentoClienteActionForm', 'periodoCancelamentoInicial')">												
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/></a>
					a 
					<html:text property="periodoCancelamentoFinal" 
							size="11" 
							maxlength="10" 
							tabindex="3" 
							onkeyup="mascaraData(this, event)"  
							onkeypress="return isCampoNumerico(event);"
							value=""/>
					<a href="javascript:abrirCalendario('FiltrarContratoParcelamentoClienteActionForm', 'periodoCancelamentoFinal')">			
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/></a> (99/99/9999)

					<div align="left"></div>
                </td>
              </tr>
              
              <tr>
                <td height="25"><strong>Motivo Cancelamento do Contrato<font color="#000000">:</font></strong></td>
                <td height="25" colspan="2"><div align="left">
                  <html:select property="colecaoContratoMotivoCancelamento" style="width: 350px; height:100px;" multiple="true">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present name="colecaoContratoMotivoCancelamento" scope="session">
							<html:options collection="colecaoContratoMotivoCancelamento" labelProperty="descricaoParcelamentoMotivoDesfazer" property="id" />
						</logic:present>
					</html:select>
				</div></td>
              </tr>
              
               <tr> 
                <td height="25">&nbsp;</td>
                <td width="312" height="25">&nbsp;</td>
                <td align="right"> <div align="left"> </div></td>
              </tr>
              
              <tr> 
                <td colspan="3"><p>&nbsp;</p></td>
              </tr>
              
               <tr> 
	               <td colspan="2">
	                	<input type="button" name="adicionar2" 
	                			class="bottonRightCol" 
	                			value="Limpar" 
	                			onclick="javascript:limparForm();">
	                	<input type="button" name="adicionar" 
				                class="bottonRightCol" 
				                value="Cancelar" 
				                onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
	                </td>
                
	                <td colspan="6"> 
		                <div align="right"> 
		                    <input type="button" name="Submit" 
		                    		class="bottonRightCol" 
		                    		value="Filtrar" 
		                    		onclick="validaForm();">
		                </div>
	                </td>
              </tr>
				
				
            </table>
		  </td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>