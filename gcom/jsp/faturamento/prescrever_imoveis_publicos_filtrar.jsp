<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="PrescreverImoveisPublicosActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<SCRIPT LANGUAGE="JavaScript">

	function selecionarPrescricao(){
		var form = document.forms[0]; 
		
		// Manual
		if(form.formaPrescricao[0].checked){
			
			form.idImovel.disabled = false;
			form.idCliente.disabled = false;
			form.periodoInicial.disabled = false;
			form.periodoFinal.disabled = false;
			
			if(form.idImovel.value != '' || form.idCliente.value != ''){
				form.idsEsferaPoder.disabled = true;
				form.idsEsferaPoder.selectedIndex = "-1";
			}else{
				form.idsEsferaPoder.selectedIndex = "-1";
			}
		
		// Automática
		}else if(form.formaPrescricao[1].checked){
			
			form.idImovel.disabled = true;
			form.idCliente.disabled = true;
			form.periodoInicial.disabled = true;
			form.periodoFinal.disabled = true;
			
			form.idsEsferaPoder.selectedIndex = "-1";
			
			form.idCliente.value = '';
	    	form.descricaoCliente.value = '';
	    	form.idImovel.value = '';
	    	form.descricaoImovel.value = '';
		}
	}
	
	function desabilita(){
		var form = document.forms[0]; 

		if( form.idImovel.value != ''){
			
			form.idCliente.disabled = true;
			form.idsEsferaPoder.disabled = true;
			form.idsEsferaPoder.selectedIndex = "-1";
		
		}else if(form.idCliente.value != ''){
			
			form.idImovel.disabled = true;
			form.idsEsferaPoder.disabled = true;
			form.idsEsferaPoder.selectedIndex = "-1";
		}
	}
	
	function desabilitarImovel(){
		var form = document.forms[0]; 
		
		if (form.idCliente.value != "") {
			
			form.idImovel.disabled = true;
			form.idsEsferaPoder.selectedIndex = "-1";
			form.idsEsferaPoder.disabled = true;
		}else {
			
			form.idImovel.disabled = false;
			form.idsEsferaPoder.disabled = false;
		}
		
		form.idCliente.disabled = false;
		form.idImovel.value = '';
	    form.descricaoImovel.value = '';
	}
	
	function desabilitarCliente(){
		var form = document.forms[0]; 
		
		if (form.idImovel.value != "") {
			
			form.idCliente.disabled = true;
			form.idsEsferaPoder.selectedIndex = "-1";
			form.idsEsferaPoder.disabled = true;
		}else {
			
			form.idCliente.disabled = false;
			form.idsEsferaPoder.disabled = false;
		}
		
		form.idImovel.disabled = false;
		form.idCliente.value = '';
	    form.descricaoCliente.value = '';
	}
	
	function reload(){
		var form = document.forms[0];
		
		form.action = 'exibirPrescreverImoveisPublicosAction.do';
 		form.submit();
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
					//abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
					abrirPopupComSubmit(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}

	/* Recuperar Popup */
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
		
	  	if (tipoConsulta == 'cliente') {
        	form.idCliente.value = codigoRegistro;	
	  		form.descricaoCliente.value = descricaoRegistro;
	  		form.descricaoCliente.style.color = "#000000"; 
	  		
	  		form.idImovel.disabled = true;    
	  		
	  		form.idsEsferaPoder.selectedIndex = "-1";
			form.idsEsferaPoder.disabled = true;  
      	}

		if (tipoConsulta == 'imovel') {
	      	form.idImovel.value = codigoRegistro;
		  	form.descricaoImovel.value = descricaoRegistro;
		  	form.descricaoImovel.style.color = "#000000"; 
		  	
		  	form.idCliente.disabled = true;      
		  	
		  	form.idsEsferaPoder.selectedIndex = "-1";
			form.idsEsferaPoder.disabled = true; 
		}
		
	}
	
	/* Limpar Form */
	function limparForm(){
		
		window.location.href = '/gsan/exibirPrescreverImoveisPublicosAction.do?menu=sim';
	}

	/* Limpar Cliente */
	function limparCliente() {
		
		var form = document.forms[0];
		
      	form.idCliente.value = '';
	    form.descricaoCliente.value = '';
		form.idCliente.focus();
		
		form.idImovel.disabled = false;
		
		reload();
	}
	
	/* Limpar Imovel */
	function limparImovel() {
		
		var form = document.forms[0];

      	form.idImovel.value = '';
	    form.descricaoImovel.value = '';
		form.idImovel.focus();
		
		form.idCliente.disabled = false;
		
		reload();
	}
	
	/* Limpar Data */
	function limparData() {
		
		var form = document.forms[0];
		
	    form.periodoInicial.value = '';
	    form.periodoFinal.value = '';
	}

	/* Replica Período */
	function replicaData() {
		
		var form = document.forms[0]; 
		form.periodoFinal.value = form.periodoInicial.value;
	}
	
	function validaForm(){
		
		var form = document.forms[0];
		
		// Manual
		if(form.formaPrescricao[0].checked){
			
			if(validatePrescreverImoveisPublicosActionForm(form)){
				
				if(form.idImovel.value != '' && form.idCliente.value == ''){
					
					if (form.periodoInicial.value != '' && form.periodoFinal.value != ''){
						
						if( validaData(form.periodoInicial) && validaData(form.periodoFinal)){
							
							if ( comparaData(form.periodoInicial.value, "<", form.periodoFinal.value )
									|| comparaData(form.periodoInicial.value, "=", form.periodoFinal.value) ){
				  				
				  				submeterFormPadrao(form);
				  			}else{
				  				alert('Data final do período é anterior à data inicial.');			
				  			}
				  		}
					}
				}else if(form.idCliente.value != '' && form.idImovel.value == ''){
					
					if (form.periodoInicial.value != '' && form.periodoFinal.value != ''){
						
						if( validaData(form.periodoInicial) && validaData(form.periodoFinal)){
							
							if ( comparaData(form.periodoInicial.value, "<", form.periodoFinal.value )
									|| comparaData(form.periodoInicial.value, "=", form.periodoFinal.value) ){
				  				
				  				submeterFormPadrao(form);
				  			}else{
				  				alert('Data final do período é anterior à data inicial.');			
				  			}
				  		}
					}
				}
				else{
					if (form.idsEsferaPoder.selectedIndex >= 0 ){
		
						if (form.periodoInicial.value != '' && form.periodoFinal.value != ''){
							
							if( validaData(form.periodoInicial) && validaData(form.periodoFinal)){
								
								if ( comparaData(form.periodoInicial.value, "<", form.periodoFinal.value )
										|| comparaData(form.periodoInicial.value, "=", form.periodoFinal.value) ){
					  				
					  				submeterFormPadrao(form);
					  			}else{
					  				alert('Data final do período é anterior à data inicial.');			
					  			}
					  		}
						}
					}else{
						alert('Informe a(s) Esfera(s) de Poder.');
					}
				}
			}
		// Automático 
		}else if(form.formaPrescricao[1].checked){
	
			if (form.idsEsferaPoder.selectedIndex >= 0 ){

			  	submeterFormPadrao(form);
				
			}else{
				alert('Informe a(s) Esfera(s) de Poder.');
			}
		}
	}
	
</SCRIPT>


</head>

<body leftmargin="5" topmargin="5" onload="selecionarPrescricao();desabilita();">

<div id="formDiv">
<html:form action="/prescreverImoveisPublicosAction.do" 
		   name="PrescreverImoveisPublicosActionForm" 
		   type="gcom.gui.faturamento.PrescreverImoveisPublicosActionForm"
		   method="post">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

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

	<td width="625" valign="top" class="centercoltext">

        <table height="100%">
        <tr>
          <td></td>
        </tr>
      	</table>

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Cancelamento de Débitos Prescritos de Imóveis Públicos</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
			  <tr> 
                	<td colspan="5" bordercolor="#000000" class="style1">Para 
                  		selecionar Débitos para Prescrição, informe os dados abaixo:
                	</td>
              </tr>
	          <tr> 
	              	<td height="24" colspan="4" bordercolor="#000000" class="style1"> 
	                  	<hr>
	                </td>
			  </tr>
              
              <tr> 
              		<td ><strong>Forma de Prescrição:<font color="#FF0000">*</font></strong></td>
                	<td colspan="3">
                		<span class="style2">
		                  <strong> 
							  <html:radio property="formaPrescricao" value="0" onclick="javascript:selecionarPrescricao();reload()" tabindex="1"/>
			 				  Manual
							  <html:radio property="formaPrescricao" value="1" onclick="javascript:selecionarPrescricao();reload()" tabindex="2"/>
							  Automático
						  </strong>
						</span>
			    	</td>
              </tr>
              
              <tr> 
              		<td height="24" colspan="4" bordercolor="#000000" class="style1"> 
                  		<hr>
                	</td>
			  </tr>
			  
			  <tr>
				    <td>
				   		<strong>Cliente:<font color="#FF0000"></font></strong>
				    </td>
                    <td width="81%" height="24" colspan="2">
                   		<html:text maxlength="9" 
                   				   property="idCliente" 
                   				   size="9"  
                   				   tabindex="3" 
                   				   onkeyup="desabilitarImovel()"
                   				   onchange="desabilitarImovel()"
                   				   onkeypress="javascript:validaEnter(event, 'exibirPrescreverImoveisPublicosAction.do', 'idCliente'); return isCampoNumerico(event);"
                   		/>
                      	<a href="javascript:abrirPopup('exibirPesquisarClienteAction.do', 275, 480);">
                        	<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Usuário"/></a>
					
   		      			<logic:present name="clienteInexistente" 
   		      						   scope="request">
							<input type="text" 
								   name="descricaoCliente" 
								   size="40" 
								   readonly="true" 
								   style="background-color:#EFEFEF; border:0; color: #ff0000" 
								   value="<bean:message key="atencao.cliente.inexistente"/>"/>
                      	</logic:present>

                        <logic:notPresent name="clienteInexistente" 
                      					  scope="request">
	                       	<html:text property="descricaoCliente" 
	                       			   size="40" 
	                       			   readonly="true" 
	                       			   style="background-color:#EFEFEF; border:0; color: #000000" />
                        </logic:notPresent>
                        
						<a href="javascript:limparCliente();document.forms[0].idCliente.focus();"> <img
						   src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						   border="0" title="Apagar" /></a>                   
				    </td>
               </tr>
			
               <tr> 
                	<td height="24" colspan="4" bordercolor="#000000" class="style1"> 
                  		<hr>
                	</td>
			   </tr>
			   
               <tr>
				    <td>
				   		<strong>Matrícula:<font color="#FF0000"></font></strong>
				    </td>
                    <td width="81%" height="24" colspan="2">
                   		<html:text maxlength="9" 
                   				   property="idImovel" 
                   				   size="9"  
                   				   tabindex="4" 
                   				   onkeyup="javascript:desabilitarCliente();"
                   				   onchange="javascript:desabilitarCliente();"
                   				   onkeypress="javascript:validaEnter(event, 'exibirPrescreverImoveisPublicosAction.do', 'idImovel'); return isCampoNumerico(event);"
                   		/>
                      	<a href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 275, 480);">
                        	<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Usuário"/></a>
					
   		      			<logic:present name="imovelInexistente" 
   		      						   scope="request">
							<input type="text" 
								   name="descricaoImovel" 
								   size="40" 
								   readonly="true" 
								   style="background-color:#EFEFEF; border:0; color: #ff0000" 
								   value="<bean:message key="atencao.imovel.inexistente"/>"/>
                      	</logic:present>

                        <logic:notPresent name="imovelInexistente" 
                      					  scope="request">
	                       	<html:text property="descricaoImovel" 
	                       			   size="40" 
	                       			   readonly="true" 
	                       			   style="background-color:#EFEFEF; border:0; color: #000000" />
                        </logic:notPresent>
                        
						<a href="javascript:limparImovel();document.forms[0].idImovel.focus();"> <img
						   src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						   border="0" title="Apagar" /></a>                   
				    </td>
               </tr>
              
               <tr> 
                	<td height="24" colspan="4" bordercolor="#000000" class="style1"> 
                  		<hr>
                  	</td>
               </tr>
               
               <tr>
					<logic:equal name="esfera" value="true">
						<td><strong>Esfera Poder: <font color="#FF0000">*</font></strong></td>
					</logic:equal>
					
					<logic:notEqual name="esfera" value="true">
						<td><strong>Esfera Poder: </strong></td>
					</logic:notEqual>
					
					<td colspan="3">
						<html:select property="idsEsferaPoder"
									 style="width: 230px;" 
									 multiple="mutiple" 
									 size="4" 
									 tabindex="5" >
							<logic:notEmpty name="colecaoEsferaPoder">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoEsferaPoder"
											  labelProperty="descricao" 
											  property="id" />
							</logic:notEmpty>
						</html:select>
					</td>
				</tr>
                
                <tr> 
                	<td height="24" colspan="4" bordercolor="#000000" class="style1"> 
                  		<hr>
                  	</td>
               </tr>
               
               <tr> 
                	
                	<logic:equal name="manual" value="true">
                		<td><strong>Per&iacute;odo:<font color="#FF0000">*</font></strong></td>
                	</logic:equal>
                	
                	<logic:notEqual name="manual" value="true">
                		<td><strong>Per&iacute;odo:</strong></td>
                	</logic:notEqual>
                	
                	<td colspan="3"><span class="style2"><strong> 
					
						<html:text property="periodoInicial" 
						 		size="11" 
								maxlength="10" 
								tabindex="6" 
								onkeyup="mascaraData(this, event);replicaData();" 
								onkeypress="return isCampoNumerico(event);" />
						<a href="javascript:abrirCalendarioReplicando('PrescreverImoveisPublicosActionForm', 'periodoInicial', 'periodoFinal');">
							<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/></a>
						 a <html:text property="periodoFinal" 
									size="11" 
									maxlength="10" 
									tabindex="7" 
									onkeyup="mascaraData(this, event);"  
									onkeypress="return isCampoNumerico(event);" />
						<a href="javascript:abrirCalendario('PrescreverImoveisPublicosActionForm', 'periodoFinal');">
							<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/></a>
						</strong>(dd/mm/aaaa)</span>
					</td>
               </tr>

               <tr> 
                	<td>&nbsp;</td>
                	<td colspan="3" align="right">&nbsp;</td>
               </tr>
              
               <tr> 
                	<td>&nbsp;</td>
                	<td colspan="3"> <div align="right"> </div></td>
               </tr>
              
               <tr> 
               		<td colspan="2">
	                	<input type="button" name="adicionar2" 
	                			class="bottonRightCol" 
	                			value="Limpar" 
	                			onclick="javascript:limparForm();" tabindex="8">
	                	<input type="button" name="adicionar" 
				                class="bottonRightCol" 
				                value="Cancelar" tabindex="9"
				                onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
                	</td>
                
                	<td colspan="3"> 
		                
		                <logic:equal name="manual" value="true">
                			<div align="right"> 
			                    <input type="button" name="Submit" 
			                    		class="bottonRightCol" 
			                    		value="Concluir" tabindex="10"
			                    		onclick="javascript:validaForm();">
		                	</div>
                		</logic:equal>
                		
                		<logic:notEqual name="manual" value="true">
                			<div align="right"> 
			                    <input type="button" name="Submit" 
			                    		class="bottonRightCol" 
			                    		value="Inserir" tabindex="10"
			                    		onclick="javascript:validaForm();">
		                	</div>
                		</logic:notEqual>
                		
                	</td>
               </tr>      
      </table>
      <p>&nbsp;</p>
	</td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</div>

</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

</html:html>
