<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>


<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="FiltrarPerfilParcelamentoActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
	
	function limpar(){
		form = document.forms[0];
		
		form.idUnidadeResponsavel.value = "-1";
		form.situacaoRetorno.value = "-1";
		form.periodoEncerramentoOsInicial.value = "";		
		form.periodoEncerramentoOsFinal.value = "";		
		form.periodoRetornoServicoInicial.value = "";		
		form.periodoRetornoServicoFinal.value = "";	
		form.periodoRejeicaoInicial.value = "";		
		form.periodoRejeicaoFinal.value = "";	
			
	}
	

	function required () {
		this.aa = new Array("idUnidadeResponsavel","Informe a Unidade Responsável.", new Function ("varName", " return this[varName];"));
  		var form = document.forms[0];
		
		if(form.numeroOS.value == ''){
	    	this.ab = new Array("situacaoRetorno", "Informe a Situação do Retorno.", new Function ("varName", " return this[varName];"));
	    }
	} 
	
	function DateValidations () { 
	     this.aa = new Array("periodoEncerramentoOsInicial", "Data do Encerramento da OS Inicial não é uma data válida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
	     this.ab = new Array("periodoEncerramentoOsFinal", "Data do Encerramento da OS Final não é uma data válida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
	     this.ac = new Array("periodoRetornoServicoInicial", "Data do Retorno do Serviço Inicial não é uma data válida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
	     this.ad = new Array("periodoRetornoServicoFinal", "Data do Retorno do Serviço Final não é uma data válida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
	     this.ae = new Array("periodoRejeicaoInicial", "Data da Rejeição do Serviço Inicial não é uma data válida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
	     this.af = new Array("periodoRejeicaoFinal", "Data da Rejeição do Serviço Final não é uma data válida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
	} 	


	function limparEncerramentoOs(){
		
		var form = document.forms[0];   
	   	
	   	form.periodoEncerramentoOsInicial.value= "";
	   	form.periodoEncerramentoOsFinal.value = ""; 
	   
	   	form.periodoRejeicaoInicial.value= "";
	   	form.periodoRejeicaoFinal.value = ""; 
	 
	}

	function limparRetornoServico(){
		var form = document.forms[0];
	   	form.periodoRetornoServicoInicial.value= "";
	   	form.periodoRetornoServicoFinal.value  = ""; 
	   
	   	form.periodoRejeicaoInicial.value= "";
	   	form.periodoRejeicaoFinal.value = ""; 
	 
	}

	function limparRejeicaoFinal(){
  		var form = document.forms[0];
   
   		if(form.periodoRejeicaoInicial.value == ''){
       		form.periodoRejeicaoFinal.value = "";
   		}
	}

	function limparRejeicaoOs(){
  		var form = document.forms[0];   
   
	   if(form.periodoRejeicaoInicial.value == ''){
	       form.periodoRejeicaoFinal.value = "";
	   }
	   
	   form.periodoEncerramentoOsInicial.value= "";
	   form.periodoEncerramentoOsFinal.value = ""; 
	   form.periodoRetornoServicoInicial.value= "";
	   form.periodoRetornoServicoFinal.value  = ""; 
	}


	function verificarDatas(){
  		var form = document.forms[0];   
	  	if(form.numeroOS.value == '' && 
	  		form.periodoEncerramentoOsInicial.value == '' && form.periodoEncerramentoOsFinal.value == '' &&
	    	form.periodoRetornoServicoInicial.value == '' && form.periodoRetornoServicoFinal.value == '' && 
	     	form.periodoRejeicaoInicial.value == '' && form.periodoRejeicaoFinal.value == ''){    
	       	
	       	alert('Datas de Encerramento da OS, Retorno do Serviço ou Rejeição do Serviço devem ser informados');
	     	return false;
	
	  	} else if ( form.periodoEncerramentoOsInicial.value == '' && form.periodoEncerramentoOsFinal.value != '' ){
			alert('Data inicial de encerramento da OS deve ser informada');
	     	return false;
	
	  	} else if ( form.periodoEncerramentoOsFinal.value == '' && form.periodoEncerramentoOsInicial.value != '' ){
			alert('Data final de encerramento da OS deve ser informada');
	     	return false;
	
	  	}  else if ( form.periodoRetornoServicoInicial.value == '' && form.periodoRetornoServicoFinal.value != '' ){
			alert('Data inicial do retorno do serviço deve ser informada');
	     	return false;
	
	  	}  else if ( form.periodoRetornoServicoFinal.value == '' && form.periodoRetornoServicoInicial.value != '' ){
			alert('Data final do retorno do serviço deve ser informada');
	     	return false;
	     
	  	} else if ( form.periodoRejeicaoInicial.value == '' && form.periodoRejeicaoFinal.value != '' ){
			alert('Data inicial da Rejeição do Serviço deve ser informada');
	     	return false;
	
	  	} else if ( form.periodoRejeicaoFinal.value == '' && form.periodoRejeicaoInicial.value != '' ){
			alert('Data final da Rejeição do Serviço deve ser informada');
	     	return false;
	  
	  	} else if ( form.periodoRejeicaoInicial.value != '' && form.periodoRejeicaoFinal.value != '' && 
	  		comparaData(form.periodoRejeicaoInicial.value, "<", form.periodoRejeicaoFinal.value )){
	    	return true;
	  	}
	          
	 	return true;
	}
	
	function validateFiltrarOrdemProcessoRepavimentacaoActionForm() { 
		var form = document.forms[0];       
         
    	if( verificarDatas() && validateRequired(form) &&  validateDate(form)){
  			submeterFormPadrao(form);
		}
	}

	function carregarMunicipio(){
		
		var form = document.forms[0];
		
		form.action = '/gsan/exibirFiltrarOrdemProcessoRepavimentacaoAction.do';
		form.submit();
	}
	
	function limparBairroDigitacao() {
		var form = document.forms[0];
		
      	form.idBairro.value = '';
	    form.bairroDescricao.value = '';
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
		
		if (tipoConsulta == 'bairro') {
	    	form.idBairro.value = "";
		    form.codigoBairro.value = codigoRegistro;
		    form.bairroDescricao.value = descricaoRegistro;
		    form.bairroDescricao.style.color = '#000000';
	    } else if (tipoConsulta == 'logradouro') {
  	      	form.idLogradouro.value = codigoRegistro;
	      	form.logradouroDescricao.value = descricaoRegistro;
	      	form.logradouroDescricao.style.color = '#000000';
	    }
	}


	function limparBairro() {
		var form = document.forms[0];
		
      	form.idBairro.value = '';
	    form.codigoBairro.value = '';
	    form.bairroDescricao.value = '';
	    
	    form.codigoBairro.focus();
	}
	
	function limparLogradouro() {
		var form = document.forms[0];
		
      	form.idLogradouro.value = '';
	    form.logradouroDescricao.value = '';
	
		form.idLogradouro.focus();
	}

</script>
</head>
<body leftmargin="0" topmargin="0"
onload="setarFoco('${requestScope.nomeCampo}');">
<html:form action="/filtrarOrdemProcessoRepavimentacaoAction"
	name="FiltrarOrdemProcessoRepavimentacaoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.FiltrarOrdemProcessoRepavimentacaoActionForm"
	method="post">


<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>


<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="120" valign="top" class="leftcoltext">
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
		<td valign="top" class="centercoltext">
            <table>
              <tr><td></td></tr>
              
            </table>
         
          <!--Início Tabela Reference a Páginação da Tela de Processo-->
            <table>
              <tr>
                <td></td>
              </tr>
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="11"><img border="0" src="imagens/parahead_left.gif"/></td>
                <td class="parabg">Filtrar Ordens em Processo de Repavimentação </td>
                <td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif"/></td>
              </tr>
            </table>
            <!--Fim Tabela Reference a Páginação da Tela de Processo-->
				
				<p>&nbsp;</p>
				
				<table bgcolor="#cbe5fe" align="center" border="0" cellpadding="0" cellspacing="0" height="24" width="100%"><tbody>
		 		<tr>
				  <td bgcolor="#cbe5fe" colspan="3">
					 <div border="1" align="center">
					   <img width="356" 
					   		height="287" 
					   		border="0" 
					   		src="<bean:message key="caminho.imagens"/>UPA-PrefeituraRecife2.gif" />
						  <br>
						  <br>
					 </div>
				  </td>
				</tr>
				<tr>
				  <td><p><HR> <p></td>
				</tr>
		
		
		 <tr  bgcolor="#cbe5fe" >
 		  <td width="100%" align="center">
 		  
 		  
 		  <table border= "0" width="100%" >	
 		  	
		  <tr>		  
		  	 <td width="16%">
		    	 <strong>Unidade <br> Responsável : <font color="#ff0000"></font></strong><font color="#ff0000">*</font>
		  	</td>			
		  	<td width="24%" align="left">		
		  		<logic:present name="bloquearUnidade">
			       <logic:present name="colecaoUnidadesResponsaveis">  
			           <html:select property="idUnidadeResponsavel" tabindex="1" disabled="true" style="width:100px">
					     <html:options collection="colecaoUnidadesResponsaveis" 
					     			   labelProperty="descricao" 
					     			   property="id" 
					     			   />
					   </html:select>					
				  </logic:present>
			     </logic:present>    	 
			     <logic:notPresent name="bloquearUnidade">
			     <logic:present name="colecaoUnidadesResponsaveis">  
			           <html:select property="idUnidadeResponsavel" tabindex="1"  style="width:100px" onchange="carregarMunicipio()">
	                     <html:option value="">&nbsp;</html:option>
					     <html:options collection="colecaoUnidadesResponsaveis" labelProperty="descricao" property="id" />
					   </html:select>					
				  </logic:present>
			     </logic:notPresent>  
			       
			       
			       
		 	 </td>	 
		   	<td width="15%">
		    	 <strong>Encerramento <br> da OS:</strong> 
		  	</td>
		  	<td width="45%" align="left">
		    	<html:text  property="periodoEncerramentoOsInicial" 
		    				size="10" 
		    				maxlength="10" 
		    				tabindex="2"   
		    				onkeypress="return isCampoNumerico(event);"
		    				onkeyup="mascaraData(this, event),limparRetornoServico();" />
			     		<a	href="javascript:abrirCalendarioReplicando('FiltrarOrdemProcessoRepavimentacaoActionForm', 'periodoEncerramentoOsInicial', 'periodoEncerramentoOsFinal'),limparRetornoServico();" >
							<img border="0" 
								 src="<bean:message key="caminho.imagens"/>calendario.gif" 
								 width="20" 
								 border="0" 
								 align="absmiddle" 
								 alt="Exibir Calendário"
								 title="Calendário"/></a>   
                 <strong>  a  </strong>
				<html:text  property="periodoEncerramentoOsFinal" 
							size="10" 
							maxlength="10" 
							tabindex="3"  
							onkeypress="return isCampoNumerico(event);"
							onkeyup="mascaraData(this, event),limparRetornoServico();" /> 
					  <a href="javascript:abrirCalendario('FiltrarOrdemProcessoRepavimentacaoActionForm', 'periodoEncerramentoOsFinal'),limparRetornoServico()">
						<img border="0" 
							 src="<bean:message key="caminho.imagens"/>calendario.gif" 
							 width="20" 
							 align="absmiddle" 
							 alt="Exibir Calendário" 
							 title="Calendário"/></a>
		 	 </td>			  
	    </tr>		
		
		<p><BR><p>	
 		  	
		  <tr>		  
		  	 <td width="15%" >
		    	 <strong>Situação <br> do Retorno : <font color="#ff0000"></font></strong><strong><font color="#ff0000">*</font>
		  	</td>
		  	<td width="25%" align="left">                   
                     <html:select property="situacaoRetorno" tabindex="4" style="width:100px">
                         <html:option value="">&nbsp;</html:option>
	                     <html:option value="1">Pendente</html:option>
					     <html:option value="2">Concluídas</html:option>
					     <html:option value="4">Rejeitadas</html:option>
					     <html:option value="3">Todas</html:option>
					   </html:select>		
                    
		 	 </td>		  	  
		  	<td width="15%">
		    	 <strong>Retorno <br> do Serviço:</strong> 
		  	</td>
		  	<td width="45%" align="left">
		    	<html:text  property="periodoRetornoServicoInicial" 
		    				size="10" 
		    				maxlength="10" 
		    				tabindex="5" 
		    				onkeyup="mascaraData(this, event),limparEncerramentoOs();" 
		    				onkeypress="return isCampoNumerico(event);"/>
				     <a	href="javascript:abrirCalendarioReplicando('FiltrarOrdemProcessoRepavimentacaoActionForm', 'periodoRetornoServicoInicial', 'periodoRetornoServicoFinal'),limparEncerramentoOs();" >
						<img border="0" 
							 src="<bean:message key="caminho.imagens"/>calendario.gif" 
							 width="20" 
							 align="absmiddle" 
							 alt="Exibir Calendário"
							 title="Calendário"/></a>   
                 <strong> a </strong> 
				<html:text property="periodoRetornoServicoFinal" 
						   size="10" 
						   maxlength="10" 
						   tabindex="6" 
						   onkeypress="return isCampoNumerico(event);"
						   onkeyup="mascaraData(this, event),limparEncerramentoOs();"  /> 
					  <a href="javascript:abrirCalendario('FiltrarOrdemProcessoRepavimentacaoActionForm', 'periodoRetornoServicoFinal'),limparEncerramentoOs();">
						<img border="0" 
							 src="<bean:message key="caminho.imagens"/>calendario.gif" 
							 width="20" 
							 align="absmiddle" 
							 title="Calendário"
							 alt="Exibir Calendário" /></a>
		 	 </td>		  
		  </tr>
		  
		  <p><BR><p>
		  
		  <tr align="left">
		      <td width="15%" >
		      </td>
		      
		      <td width="25%" align="left">
		      </td>
		      
		      <td width="15%" align="left" >
		    	 <strong>Rejeição <br> do Serviço:</strong> 
		  	  </td>
		      <td width="45%" align="left">
			      <html:text  property="periodoRejeicaoInicial" 
			    			  size="10" 
			    			  maxlength="10" 
			    			  tabindex="7" 
			    			  onkeyup="mascaraData(this, event),limparRejeicaoOs();" 
			    			  onkeypress="return isCampoNumerico(event);"
			    			  onkeydown="limparRejeicaoFinal();"/>
				  <a href="javascript:abrirCalendarioReplicando('FiltrarOrdemProcessoRepavimentacaoActionForm', 'periodoRejeicaoInicial', 'periodoRejeicaoFinal');limparRejeicaoOs();limparRejeicaoFinal();" >
				      <img border="0" 
						   src="<bean:message key="caminho.imagens"/>calendario.gif" 
						   width="20" 
						   align="absmiddle" 
						   alt="Exibir Calendário"
						   title="Calendário"/></a>   
                  <strong> a </strong> 
				  <html:text property="periodoRejeicaoFinal" 
							 size="10" 
							 maxlength="10" 
							 tabindex="8" 
							 onkeypress="return isCampoNumerico(event);"
							 onkeyup="mascaraData(this, event),limparRejeicaoOs();" /> 
				  <a href="javascript:abrirCalendario('FiltrarOrdemProcessoRepavimentacaoActionForm', 'periodoRejeicaoFinal'),limparRejeicaoOs();">
				      <img border="0" 
						   src="<bean:message key="caminho.imagens"/>calendario.gif" 
						   width="20" 
						   align="absmiddle" 
						   title="Calendário"
						   alt="Exibir Calendário" /></a>
		 	 </td> 
		  </tr>
	    </table>
 				
					  
		  </td>
		</tr>
	
	
		<tr>
          <td bgcolor="#cbe5fe"><BR></td>
        <tr>
        
		<tr bgcolor="#cbe5fe">
		  <td><p><HR><p><p></td>
	    </tr>
	    <tr>
	    <td>
	    <table border= "0" width="100%">

       	<tr> 
      		<td><strong>N&uacute;mero da OS:</strong></td>
            <td>
                <html:text property="numeroOS" 
                	size="9" 
                	maxlength="9"
                	onkeypress="return isCampoNumerico(event);"/>
                	
              	<font color="#ff0000">* Outros campos serão desprezados</font>
           	</td>
       	</tr>
	    
	    
	    <tr> 
        	<td>
				<strong>Munic&iacute;pio:</strong>
			</td>

        	<td>
				<html:select property="idMunicipio" 
					tabindex="1"  
					style="width:100px">
					
					<html:option value="">&nbsp;</html:option>
					<html:options collection="colecaoUnidadeMunicipio" 
						labelProperty="idMunicipio.nome" 
						property="idMunicipio.id" />
				</html:select>
				
			</td>
        </tr>
        
		<tr> 
        	<td><strong>Bairro:</strong></td>

        	<td>
          		<html:text property="codigoBairro" 
		   			size="4" 
				   	maxlength="4"
				   	onkeypress="javascript:limparBairroDigitacao();validaEnterDependencia(event, 'exibirFiltrarOrdemProcessoRepavimentacaoAction.do', this, document.forms[0].idMunicipio.value, 'Município');return isCampoNumerico(event);"/>

				<a href="javascript:abrirPopupDependencia('exibirPesquisarBairroAction.do?idMunicipio='+document.forms[0].idMunicipio.value+'&indicadorUsoTodos=1', document.forms[0].idMunicipio.value, 'Município', 275, 480);">
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" 
						width="23"	
						height="21" 
						name="imagem" 
						alt="Pesquisar" 
						border="0" 
						title="Pesquisar Bairro"></a>

				<logic:present name="bairroEncontrada" scope="request">
					<html:text property="bairroDescricao" 
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" 
						size="40" 
						maxlength="40" />
				</logic:present> 

				<logic:notPresent name="bairroEncontrada" scope="request">
					<html:text property="bairroDescricao" 
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: #ff0000" 
						size="40" 
						maxlength="40" />
				</logic:notPresent>

				<html:hidden property="idBairro"/>

				<a href="javascript:limparBairro();">
					<img border="0" 
						title="Apagar" 
						src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
      		</td>
		</tr>
		
		<tr>
			<td><strong>Logradouro:</strong></td>
			<td colspan="6">
				<html:text property="idLogradouro" 
						   size="9" 
						   maxlength="9"
						   onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarOrdemProcessoRepavimentacaoAction.do', 'idLogradouro','Logradouro');return isCampoNumerico(event);"/>
					
				<a href="javascript:abrirPopup('exibirPesquisarLogradouroAction.do?codigoMunicipio='+document.forms[0].idMunicipio.value+'&codigoBairro='+document.forms[0].codigoBairro.value+'&indicadorUsoTodos=1&primeriaVez=1', 275, 480);">
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" 
						width="23"	
						height="21" 
						name="imagem" 
						alt="Pesquisar" 
						border="0" 
						title="Pesquisar Logradouro"></a>

				<logic:present name="logradouroEncontrada" scope="request">
					<html:text property="logradouroDescricao" 
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" 
						size="36" 
						maxlength="36" />
				</logic:present> 

				<logic:notPresent name="logradouroEncontrada" scope="request">
					<html:text property="logradouroDescricao" 
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: #ff0000" 
						size="36" 
						maxlength="36" />
				</logic:notPresent>

				<a href="javascript:limparLogradouro();">
					<img border="0" 
						title="Apagar" 
						src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
			</td>
      	</tr>		
		
        </table>
	    </td>
	    </tr>
		<tr bgcolor="#cbe5fe">
		  <td><p><HR><p><p></td>
	    </tr>
	    
	    
	    
		<tr bgcolor="#cbe5fe">
		  <td height="19" align="left">
		  
		 <table width="100%">
		  <tr>
		  
		  <td width="25%">
		     &nbsp;
		  </td>
		  
		  <td width="25%">
		    	<input type="button" 
		    		name="Button" 
		    		class="bottonRightCol" 
		    		value="Cancelar" 
		    		tabindex="6" 
		    		onClick="javascript:window.location.href='/gsan/telaPrincipal.do'" style="width: 80px"/>
		   
		  </td>
		  <td width="25%">
		    	<input type="button" 
		    		class="bottonRightCol" 
		    		value="Consultar" 
		    		onClick="javascript:validateFiltrarOrdemProcessoRepavimentacaoActionForm();">
		  </td>
		  
		   <td width="25%">
		     &nbsp;
		  </td>
		  
		  </tr>
		  </table>
		  
		  
		  
		 </td>
		</tr>
		
	    <tr bgcolor="#cbe5fe">
		  <td><HR>&nbsp;&nbsp;<strong> <font color="#ff0000"></font></strong><strong><font color="#ff0000">*</font>
			  </strong> Campos obrigatórios</td>
		</tr>
		<tr>
		  <td><p><BR><p></td></tr>
	  </table>
					
    			<p>&nbsp;</p>
			</td>

		</tr>
	</table>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
