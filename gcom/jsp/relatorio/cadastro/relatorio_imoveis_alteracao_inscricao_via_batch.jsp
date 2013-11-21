<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ include file="/jsp/util/telaespera.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GCOM - Sistema de Gest&atilde;o Comercial</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="GerarRelatorioImoveisAlteracaoInscricaoViaBatchForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
		
	function validarForm(){
		var form = document.forms[0];
		
		if(validateGerarRelatorioImoveisAlteracaoInscricaoViaBatchForm(form)){
			
			if(form.escolhaRelatorio.value != "-1"){
				if (form.dataInicio.value != '' && form.dataFim.value != ''){
					if(validaData(form.dataInicio) && validaData(form.dataFim)){
						if (comparaData(form.dataInicio.value, "<=", form.dataFim.value )){
			  				
			  				toggleBox('demodiv',1);
			  			}else{
			  				alert('O período de alteração final é anterior ao período de alteração inicial.');			
			  			}
			  		}
			  	}
			}else{
				alert("Informe o Tipo do Relatório.");
			}
		}
	}
  	
  	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];
	
		if (tipoConsulta == 'localidadeOrigem') {
	    	form.localidadeOrigemID.value = codigoRegistro;
		  	form.nomeLocalidadeOrigem.value = descricaoRegistro;
		  	form.nomeLocalidadeOrigem.style.color = "#000000";
		  
		  	form.localidadeDestinoID.value = codigoRegistro;
	      	form.nomeLocalidadeDestino.value = descricaoRegistro;
	      	form.nomeLocalidadeDestino.style.color = "#000000";
	      	form.setorComercialOrigemCD.focus();
		}
	
		if (tipoConsulta == 'localidadeDestino') {
	      	form.localidadeDestinoID.value = codigoRegistro;
	      	form.nomeLocalidadeDestino.value = descricaoRegistro;
	   	  	form.setorComercialDestinoCD.focus();		  
		}
		
		if (tipoConsulta == 'setorComercialOrigem') {
	      	form.setorComercialOrigemCD.value = codigoRegistro;
	      	form.setorComercialOrigemID.value = idRegistro;
		  	form.nomeSetorComercialOrigem.value = descricaoRegistro;
		  	form.nomeSetorComercialOrigem.style.color = "#000000";
		  
		  	form.setorComercialDestinoCD.value = codigoRegistro;
	      	form.setorComercialDestinoID.value = idRegistro;
		  	form.nomeSetorComercialDestino.value = descricaoRegistro;
		  	form.nomeSetorComercialDestino.style.color = "#000000";
		  	form.quadraOrigemNM.focus();
		}
		
	}
	
	function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

		var form = document.forms[0];
	
		if (tipoConsulta == 'setorComercialOrigem') {
	      	form.setorComercialOrigemCD.value = codigoRegistro;
	      	form.setorComercialOrigemID.value = idRegistro;
		  	form.nomeSetorComercialOrigem.value = descricaoRegistro;
		  	form.nomeSetorComercialOrigem.style.color = "#000000";
		  
		  	form.setorComercialDestinoCD.value = codigoRegistro;
	      	form.setorComercialDestinoID.value = idRegistro;
		  	form.nomeSetorComercialDestino.value = descricaoRegistro;
		  	form.nomeSetorComercialDestino.style.color = "#000000";
		  	form.quadraOrigemNM.focus();
		}
	
		if (tipoConsulta == 'setorComercialDestino') {
	      	form.setorComercialDestinoCD.value = codigoRegistro;
	      	form.setorComercialDestinoID.value = idRegistro;
		  	form.nomeSetorComercialDestino.value = descricaoRegistro;
		  	form.nomeSetorComercialDestino.style.color = "#000000"; 
		  	form.quadraDestinoNM.focus();
		}

	}
	
	function validarLocalidade(){
		var form = document.forms[0];
		
		if( form.localidadeOrigemID.value == form.localidadeDestinoID.value ){
			form.setorComercialOrigemID.disabled = false;
			form.setorComercialDestinoID.disabled = false;
		}
		else if( form.localidadeOrigemID.value != form.localidadeDestinoID.value ){
			form.setorComercialOrigemID.disabled = true;
			form.setorComercialDestinoID.disabled = true;
			form.setorComercialOrigemID.value = '';
			form.setorComercialDestinoID.value = '';
			form.quadraOrigemID.value = '';
			form.quadraDestinoID.value = '';
		}
		else if( form.setorComercialOrigemID.value != form.setorComercialDestinoID.value ){
			form.quadraOrigemID.disabled = false;
			form.quadraDestinoID.disabled = false;
		}
	}
	
	function limparDestino(tipo){
		var form = document.forms[0];
		
		switch(tipo){
			/*case 1: //De localidade pra baixo
				if(form.localidadeOrigemID.value != form.localidadeDestinoID.value){
					form.localidadeDestinoID.value = "";
					form.nomeLocalidadeDestino.value = "";					
					form.setorComercialDestinoCD.value = "";
					form.setorComercialDestinoID.value = ""; 
				}*/
			    
			case 2: //De setor para baixo		 
				if(form.setorComercialOrigemCD.value != form.setorComercialDestinoCD.value){  
				   form.nomeSetorComercialDestino.value = "";
				   form.quadraDestinoNM.value = "";
				   form.quadraDestinoID.value = "";		   
				} 		   		   
			case 3://De quadra pra baixo
			   if(form.quadraOrigemNM.value != form.quadraDestinoNM.value){
		           form.loteDestino.value = "";
		       }
		           
		}
	
	}
	
	function limparBorrachaOrigem(tipo){
		var form = document.forms[0];
	
		switch(tipo){
			case 1: //De localidara pra baixo
			    if(!form.localidadeOrigemID.disabled){
					form.localidadeOrigemID.value = "";
					form.nomeLocalidadeOrigem.value = "";
					form.localidadeDestinoID.value = "";
					form.nomeLocalidadeDestino.value = "";
	
					desabilitaIntervaloDiferente(1);
				}else{
					break;
				}
				
			case 2: //De setor para baixo
			    if(!form.setorComercialOrigemCD.disabled){
			    	form.setorComercialOrigemCD.value = "";
			     	form.setorComercialOrigemID.value = "";
			     	form.nomeSetorComercialOrigem.value = "";
		   
			     	form.setorComercialDestinoCD.value = "";
			     	form.setorComercialDestinoID.value = "";		   
			     	form.nomeSetorComercialDestino.value = "";
			     	desabilitaIntervaloDiferente(2);
			    }else{
			     	break;
			    }
			case 3://De quadra pra baixo
			    if(!form.quadraOrigemNM.disabled){
					form.quadraOrigemNM.value = "";
				    form.quadraOrigemID.value = "";
		
				    form.loteOrigem.value = "";
		
				    form.quadraDestinoNM.value = "";
				    form.quadraDestinoID.value = "";
		        
				    form.loteDestino.value = "";
				   
				    form.subLoteOrigem.value = "";
			   		form.subLoteDestino.value = "";
				   
				    desabilitaIntervaloDiferente(3);
		            limparMsgQuadraInexistente();
			 	}
		}
	
	}
	
	function desabilitaIntervaloDiferente(tipo){
		var form = document.forms[0];
		
		switch(tipo){
			case 1: //De localidade 
			    if(form.localidadeOrigemID.value != form.localidadeDestinoID.value || form.localidadeOrigemID.lenght <= 0 ){
			    	limparBorrachaOrigem(2);
			        form.setorComercialOrigemCD.disabled = true;
				 	form.quadraOrigemNM.disabled = true;
			     	form.loteOrigem.disabled = true;
	
				 	form.setorComercialDestinoCD.disabled = true;
				 	form.quadraDestinoNM.disabled = true;
			     	form.loteDestino.disabled = true;
					form.quadraOrigemNM.disabled = true;
			     	form.loteOrigem.disabled = true;
				 	form.quadraDestinoNM.disabled = true;
			     	form.loteDestino.disabled = true;
			     	
			     	form.subLoteOrigem.disabled = true;
			     	form.subLoteDestino.disabled = true;
	             
	             	form.loteOrigem.value = "";
	             	form.quadraOrigemNM.value = "";	
	             	form.quadraOrigemID.value = "";
	             	limparPesquisaQuadraInicial();
	             	form.loteDestino.value = "";
	             	form.quadraDestinoNM.value = "";	
	             	form.quadraDestinoID.value = "";
	             	limparPesquisaQuadraFinal();
	             	form.quadraOrigemNM.disabled = true;
			     	form.loteOrigem.disabled = true;
				 	form.quadraDestinoNM.disabled = true;
			     	form.loteDestino.disabled = true;
	             
	             	form.loteOrigem.value = "";
	             	form.quadraOrigemNM.value = "";	
	             	form.quadraOrigemID.value = "";
	             	limparPesquisaQuadraInicial();
	             	form.loteDestino.value = "";
	             	form.quadraDestinoNM.value = "";	
	             	form.quadraDestinoID.value = "";
	             	limparPesquisaQuadraFinal();
	             
	             	form.loteOrigem.value = "";
	             	
	             	form.subLoteOrigem.value = "";
			     	form.subLoteDestino.value = "";
			 
				}else{
				 	form.setorComercialOrigemCD.disabled = false;
				 	form.quadraOrigemNM.disabled = false;
			     	form.loteOrigem.disabled = false;
	
				 	form.setorComercialDestinoCD.disabled = false;
				 	form.quadraDestinoNM.disabled = false;
			     	form.loteDestino.disabled = false;
			     	
			     	form.quadraOrigemNM.disabled = false;
			     	form.loteOrigem.disabled = false;
				 	form.quadraDestinoNM.disabled = false;
			     	form.loteDestino.disabled = false;
			     	
			     	form.subLoteOrigem.disabled = false;
			     	form.subLoteDestino.disabled = false;
				}
				
				break;					
				   		   
			case 2: //De setor Comercial		   
	
			    if(form.setorComercialOrigemCD.value != form.setorComercialDestinoCD.value || form.setorComercialOrigemCD.disabled == true ){
			    	
				  	form.quadraOrigemNM.disabled = true;
			     	form.loteOrigem.disabled = true;
				 	form.quadraDestinoNM.disabled = true;
			     	form.loteDestino.disabled = true;
			     	
			     	form.subLoteOrigem.disabled = true;
			     	form.subLoteDestino.disabled = true;
	             
	             	form.loteOrigem.value = "";
	             	form.quadraOrigemNM.value = "";	
	             	form.quadraOrigemID.value = "";
	             	limparPesquisaQuadraInicial();
	             	form.loteDestino.value = "";
	             	form.quadraDestinoNM.value = "";	
	             	form.quadraDestinoID.value = "";
	             	limparPesquisaQuadraFinal();
	             	form.subLoteOrigem.value = "";
			     	form.subLoteDestino.value = "";
	
	  			}else{
				 	form.quadraOrigemNM.disabled = false;
			     	form.loteOrigem.disabled = false;
				 	form.quadraDestinoNM.disabled = false;
			     	form.loteDestino.disabled = false;
				}
				
				break;
	           
			case 3://De quadra 
				if(form.quadraOrigemNM.value != form.quadraDestinoNM.value || form.quadraOrigemNM.disabled == true){
					form.loteOrigem.disabled = true;
				 	form.loteDestino.disabled = true;
	             	
	             	form.loteOrigem.value = "";
	             	form.loteDestino.value = "";
	             	
	             	form.subLoteOrigem.disabled = true;
				 	form.subLoteDestino.disabled = true;
	             	
	             	form.subLoteOrigem.value = "";
	             	form.subLoteDestino.value = "";
				 
				}else{
				 	form.loteOrigem.disabled = false;
				 	form.loteDestino.disabled = false;
				 	
				 	form.subLoteOrigem.disabled = false;
				 	form.subLoteDestino.disabled = false;
				}
				
				break;
		}
	}
	
	function limparPesquisaQuadraFinal() {
    	var form = document.forms[0];
    	
    	form.quadraDestinoNM.value = "";
    	var msgQuadra = document.getElementById("msgQuadraFinal");
	
		if (msgQuadra != null){
		    msgQuadra.innerHTML = "";
		}
	}
	
	function limparBorrachaDestino(tipo){
		var form = document.forms[0];
	
		switch(tipo){
			case 1: //De localidade pra baixo
			     form.localidadeDestinoID.value = "";
				 form.nomeLocalidadeDestino.value = "";					
				 form.setorComercialDestinoCD.value = "";
				
			case 2: //De setor para baixo		   
			   form.setorComercialDestinoID.value = ""; 
			   form.nomeSetorComercialDestino.value = "";		   
	   		   form.setorComercialDestinoCD.value = "";
			   
			case 3://De quadra pra baixo
			   form.quadraDestinoNM.value = "";
			   form.quadraDestinoID.value = "";
	           form.loteDestino.value = "";
	     }
	}
	
	function duplicarSetorComercial(){
		var form = document.forms[0];
		
		form.setorComercialDestinoCD.value = form.setorComercialOrigemCD.value;
	} 
	
	
	function limparOrigem(tipo){
		var form = document.forms[0];
	
		switch(tipo){
			case 1: //De localidade pra baixo
	
				form.nomeLocalidadeOrigem.value = "";
				form.localidadeDestinoID.value = "";
				form.nomeLocalidadeDestino.value = "";
				form.setorComercialOrigemCD.value = "";
			    form.setorComercialOrigemID.value = "";
				habilitaSQlS();
				
			case 2: //De setor para baixo
	
			   form.nomeSetorComercialOrigem.value = "";
			   form.setorComercialDestinoCD.value = "";
			   form.setorComercialDestinoID.value = "";		   
			   form.nomeSetorComercialDestino.value = "";
			   form.quadraOrigemNM.value = "";
			   form.quadraOrigemID.value = "";

			case 3://De quadra pra baixo
	
			   form.quadraDestinoNM.value = "";
			   form.quadraDestinoID.value = "";
	        
			   form.loteDestino.value = "";
			   form.loteOrigem.value = "";
			   
			   limparMsgQuadraInexistente();		   
		}
	}
	
	function habilitaSQlS(){
		var form = document.forms[0];
		
		if (form.localidadeOrigemID.value.length < 1){
			form.setorComercialOrigemCD.disabled = false;
		 	form.quadraOrigemNM.disabled = false;
	     	form.loteOrigem.disabled = false;
	
		 	form.setorComercialDestinoCD.disabled = false;
		 	form.quadraDestinoNM.disabled = false;
	     	form.loteDestino.disabled = false;
		}    	
	}
	
	function limparMsgQuadraInexistente() {
		var msgQuadraOrigem = document.getElementById("msgQuadraInicial");
		var msgQuadraDestino = document.getElementById("msgQuadraFinal");
		
		if (msgQuadraOrigem != null){
			msgQuadraOrigem.innerHTML = "";
		}
		
		if (msgQuadraDestino != null){
			msgQuadraDestino.innerHTML = "";
		}
	
	}
	
	function duplicarLocalidade(){
		var formulario = document.forms[0]; 
		formulario.localidadeDestinoID.value = formulario.localidadeOrigemID.value;
	}  
	
	function duplicarSetorComercial(){
		var formulario = document.forms[0]; 
		formulario.setorComercialDestinoCD.value = formulario.setorComercialOrigemCD.value;
	}  
	
	function duplicarQuadra(){
		var formulario = document.forms[0]; 
		formulario.quadraDestinoNM.value = formulario.quadraOrigemNM.value;
	}  
	
	function duplicarLote(){
		var formulario = document.forms[0]; 
		formulario.loteDestino.value = formulario.loteOrigem.value;
	}  
	
	function duplicarSubLote(){
		var formulario = document.forms[0]; 
		formulario.subLoteDestino.value = formulario.subLoteOrigem.value;
	}
	
	function limparPesquisaQuadraInicial() {
	    var form = document.forms[0];
	    
	    form.quadraOrigemNM.value = "";
	    var msgQuadra = document.getElementById("msgQuadraInicial");
		
		if (msgQuadra != null){
		    msgQuadra.innerHTML = "";
		}
	}
	
	function limpar(){
  		var form = document.forms[0];
		
		form.escolhaRelatorio.value = "-1";
		form.dataInicio.value = "";
		form.dataFim.value = "";
		form.localidadeOrigemID.value = "";
		form.nomeLocalidadeOrigem.value = "";
		form.setorComercialOrigemCD.value = "";
		form.setorComercialOrigemID.value = "";
		form.nomeSetorComercialOrigem.value = "";
		form.quadraOrigemNM.value = "";
		form.quadraOrigemID.value = "";
		form.loteOrigem.value = "";
		form.subLoteOrigem.value = "";
		form.localidadeDestinoID.value = "";
		form.nomeLocalidadeDestino.value = "";
		form.setorComercialDestinoCD.value = "";
		form.setorComercialDestinoID.value = "";
		form.nomeSetorComercialDestino.value = "";
		form.quadraDestinoNM.value = "";
		form.quadraDestinoID.value = "";
		form.loteDestino.value = "";
		form.subLoteDestino.value = "";
  	}
  	
  	function replicarData(){
  		var form = document.forms[0];
  		
		form.dataFim.value = form.dataInicio.value;
  	}
  	
  	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg){

		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		}
		else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}
			else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
			}
		}
	}
		  	
</script>

</head>

<body leftmargin="5" topmargin="5" >

<div id="formDiv"><html:form action="/gerarRelatorioImoveisAlteracaoInscricaoViaBatchAction.do"
	name="GerarRelatorioImoveisAlteracaoInscricaoViaBatchForm"
	type="gcom.gui.relatorio.cadastro.imovel.GerarRelatorioImoveisAlteracaoInscricaoViaBatchForm"
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
			<td width="600" valign="top" class="centercoltext">
		        <table height="100%">
			        <tr>
			          <td></td>
			        </tr>
		      	</table>

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Gerar Relatório de Imóveis com Alteração de Inscrição Via Batch</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td style="width: 100%" colspan="4">Para filtrar o(s) imóvel(eis), informe os dados abaixo:</td>
				</tr>
				
				<tr height="1">
					<td colspan="4">&nbsp;</td>
				</tr>
				
				<tr>
					<td width="20%"><strong>Tipo do Relatório:<font color="#FF0000">*</font></strong></td>
					
					<td colspan="2" >
					  	<html:select property="escolhaRelatorio" tabindex="1" >
		                     <html:option value="-1">&nbsp;</html:option>
		                     <html:option value="1">Imóveis alterados com sucesso</html:option>
		                     <html:option value="2">Imóveis sem alteração devido a erro</html:option>
		                     <html:option value="3">Imóveis pendentes de alteração</html:option>		                     						  
						 </html:select>
					</td>
				</tr>
				
				<tr> 
		        	<td><strong>Período de Alteração:<font color="#FF0000">*</font> </strong></td>
		        	<td valign="middle">
			            <html:text maxlength="10" 
			            		   property="dataInicio" 
			            		   size="10" 
			            		   onkeypress="return isCampoNumerico(event);"
			            		   onkeyup="javascript:mascaraData(this,event);replicarData();"/>
			            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" 
			            	 onclick="javascript:abrirCalendarioReplicando('GerarRelatorioImoveisAlteracaoInscricaoViaBatchForm', 'dataInicio', 'dataFim');" 
			            	 width="20" border="0" 
			            	 align="middle" alt="Exibir Calendário" /><strong> a </strong> 
			            <html:text maxlength="10" 
			            		   property="dataFim" 
			            		   size="10"
			            		   onkeypress="javascript:mascaraData(this,event);return isCampoNumerico(event);"/>
			            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" 
			            	 onclick="javascript:abrirCalendario('GerarRelatorioImoveisAlteracaoInscricaoViaBatchForm', 'dataFim')"
			            	 width="20" border="0" 
			            	 align="middle" alt="Exibir Calendário" /> (dd/mm/aaaa) 
		        	</td>
		        </tr>
				
				<tr height="1">
					<td colspan="4">&nbsp;</td>
				</tr>

				<tr>
					<td colspan="4">
						<table width="100%" align="center" bgcolor="#99CCFF" border="0">
							<tr>
								<td colspan="2">
									<strong>Informe os dados da inscri&ccedil;&atilde;o inicial:</strong>
								</td>
							</tr>
							
							<tr bgcolor="#cbe5fe">
								<td width="100%" align="center" colspan="2">
	
									<table width="100%" border="0">
										<tr bgcolor="#cbe5fe">
											<td width="20%"><strong>Localidade:</strong></td>
											<td>
												<html:text tabindex="2" maxlength="3"
														   property="localidadeOrigemID" size="5"
														   onkeypress="validaEnter(event, 'exibirGerarRelatorioImoveisAlteracaoInscricaoViaBatchAction.do?objetoConsulta=1&inscricaoTipo=origem', 'localidadeOrigemID');limparDestino(1);return isCampoNumerico(event);"
														   onclick="validarLocalidade()"
														   onblur="duplicarLocalidade();desabilitaIntervaloDiferente(1);" />
												
												<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '');limparOrigem(1);">
													<img width="23" height="21" border="0"
												         src="<bean:message key="caminho.imagens"/>pesquisa.gif"
														 title="Pesquisar" /></a> 
												
												<logic:present name="corLocalidadeOrigem">
													<logic:equal name="corLocalidadeOrigem" value="exception">
														<html:text property="nomeLocalidadeOrigem" size="45"
																   readonly="true" 
																   style="background-color:#EFEFEF; border:0; color: #ff0000" />
													</logic:equal>
			
													<logic:notEqual name="corLocalidadeOrigem" value="exception">
														<html:text property="nomeLocalidadeOrigem" size="45"
																   readonly="true" 
																   style="background-color:#EFEFEF; border:0; color: #000000" />
													</logic:notEqual>
												</logic:present> 
												
												<logic:notPresent name="corLocalidadeOrigem">
													<logic:empty name="GerarRelatorioImoveisAlteracaoInscricaoViaBatchForm" property="localidadeOrigemID">
														<html:text property="nomeLocalidadeOrigem" value="" size="45"
																   readonly="true"
																   style="background-color:#EFEFEF; border:0; color: #ff0000" />
													</logic:empty>
													
													<logic:notEmpty name="GerarRelatorioImoveisAlteracaoInscricaoViaBatchForm" property="localidadeOrigemID">
														<html:text property="nomeLocalidadeOrigem" size="45"
																   readonly="true" 
																   style="background-color:#EFEFEF; border:0; color: #000000" />
													</logic:notEmpty>
												</logic:notPresent> 
												
												<a href="javascript:limparBorrachaOrigem(1);limparMsgQuadraInexistente();">
													<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
														 border="0" title="Apagar" /></a>
											</td>
										</tr>
										
										<tr>
											<td><strong>Setor Comercial:</strong></td>
											<td>
												<html:text tabindex="3" maxlength="3"
														   property="setorComercialOrigemCD" size="5"
														   onkeyup="limparOrigem(2);"
														   onkeypress="limparDestino(2);validaEnterDependencia(event, 'exibirGerarRelatorioImoveisAlteracaoInscricaoViaBatchAction.do?objetoConsulta=2&inscricaoTipo=origem', document.forms[0].setorComercialOrigemCD, document.forms[0].localidadeOrigemID.value, 'Localidade Inicial.');return isCampoNumerico(event);"
														   onblur="javascript:duplicarSetorComercial();desabilitaIntervaloDiferente(2);" />
											
												<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem','idLocalidade', document.forms[0].localidadeOrigemID.value, 275, 480, 'Informe Localidade Inicial.');limparOrigem(2);">
												<img width="23" height="21" border="0"
												     src="<bean:message key="caminho.imagens"/>pesquisa.gif"
												     title="Pesquisar" /></a> 
											   
												<logic:present name="corSetorComercialOrigem">
													<logic:equal name="corSetorComercialOrigem" value="exception">
														<html:text property="nomeSetorComercialOrigem" size="45"
																   readonly="true" 
																   style="background-color:#EFEFEF; border:0; color: #ff0000" />
													</logic:equal>
			
													<logic:notEqual name="corSetorComercialOrigem" value="exception">
														<html:text property="nomeSetorComercialOrigem" size="45"
																   readonly="true" 
																   style="background-color:#EFEFEF; border:0; color: #000000" />
													</logic:notEqual>
												</logic:present> 
												
												<logic:notPresent name="corSetorComercialOrigem">
													<logic:empty name="GerarRelatorioImoveisAlteracaoInscricaoViaBatchForm" property="setorComercialOrigemCD">
														<html:text property="nomeSetorComercialOrigem" value=""
																   size="45" readonly="true" 
																   style="background-color:#EFEFEF; border:0; color: #ff0000" />
													</logic:empty>
													
													<logic:notEmpty name="GerarRelatorioImoveisAlteracaoInscricaoViaBatchForm" property="setorComercialOrigemCD">
														<html:text property="nomeSetorComercialOrigem" size="45"
																   readonly="true" 
																   style="background-color:#EFEFEF; border:0; color: #000000" />
													</logic:notEmpty>
												</logic:notPresent> 
												
												<a href="javascript:limparBorrachaOrigem(2);limparMsgQuadraInexistente();">
													<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
														 border="0" title="Apagar" /></a> 
												
												<html:hidden property="setorComercialOrigemID" />
											</td>
										</tr>
										
										<tr>
											<td width="183"><strong>Quadra:</strong></td>
											<td width="432">
												<html:text tabindex="4" maxlength="4"
														   property="quadraOrigemNM" size="5"
														   onkeypress="limparOrigem(3);validaEnterDependencia(event, 'exibirGerarRelatorioImoveisAlteracaoInscricaoViaBatchAction.do?objetoConsulta=3&inscricaoTipo=origem', document.forms[0].quadraOrigemNM, document.forms[0].setorComercialOrigemCD.value, 'Setor Comercial Inicial.');return isCampoNumerico(event);"
														   onblur="javascript:duplicarQuadra();desabilitaIntervaloDiferente(3);" />
											
												<logic:present name="corQuadraOrigem" scope="request">
													<span style="color:#ff0000" id="msgQuadraInicial">
														<bean:write scope="request" name="msgQuadraInicial" />
													</span>
												</logic:present> 
												
												<logic:notPresent name="corQuadraOrigem" scope="request"> </logic:notPresent> 
												
												<html:hidden property="quadraOrigemID" /></td>
										</tr>
										
										<tr>
											<td><strong>Lote:</strong></td>
											<td>
												<logic:present name="parametroGerarRelatorio">
													<logic:equal name="parametroGerarRelatorio" value="RelatorioCadastroConsumidoresInscricao">
														<html:text maxlength="4" property="loteOrigem" size="5"
																   onkeypress="return isCampoNumerico(event);"
																   onblur="javascript:duplicarLote();" tabindex="5" />
													</logic:equal>
												
													<logic:notEqual name="parametroGerarRelatorio" value="RelatorioCadastroConsumidoresInscricao">
														<logic:equal name="parametroGerarRelatorio" value="RelatorioImoveisEndereco">
															<html:text maxlength="4" property="loteOrigem" size="5"
																	   onkeypress="return isCampoNumerico(event);"
																	   onblur="javascript:duplicarLote();" tabindex="5" />
														</logic:equal>
														
														<logic:notEqual name="parametroGerarRelatorio" value="RelatorioImoveisEndereco">
															<html:text maxlength="4" property="loteOrigem" size="5"
																       onkeypress="return isCampoNumerico(event);"
																	   onblur="javascript:duplicarLote();" tabindex="5" />
														</logic:notEqual>
													</logic:notEqual>
												</logic:present> 
												
												<logic:notPresent name="parametroGerarRelatorio">
													<html:text maxlength="4" property="loteOrigem" size="5"
															   onkeypress="return isCampoNumerico(event);"
															   onblur="javascript:duplicarLote();" tabindex="5" />
												</logic:notPresent>
											</td>
										</tr>
										
										<tr>
											<td><strong>Sub-Lote:</strong></td>
											<td>
												<logic:present name="parametroGerarRelatorio">
													<logic:equal name="parametroGerarRelatorio" value="RelatorioCadastroConsumidoresInscricao">
														<html:text maxlength="3" property="subLoteOrigem" size="4"
																   onkeypress="return isCampoNumerico(event);"
																   onblur="javascript:duplicarSubLote();" tabindex="6" />
													</logic:equal>
												
													<logic:notEqual name="parametroGerarRelatorio" value="RelatorioCadastroConsumidoresInscricao">
														<logic:equal name="parametroGerarRelatorio" value="RelatorioImoveisEndereco">
															<html:text maxlength="3" property="subLoteOrigem" size="4"
																	   onkeypress="return isCampoNumerico(event);"
																	   onblur="javascript:duplicarSubLote();" tabindex="6" />
														</logic:equal>
														
														<logic:notEqual name="parametroGerarRelatorio" value="RelatorioImoveisEndereco">
															<html:text maxlength="3" property="subLoteOrigem" size="4"
																       onkeypress="return isCampoNumerico(event);"
																	   onblur="javascript:duplicarSubLote();" tabindex="6" />
														</logic:notEqual>
													</logic:notEqual>
												</logic:present> 
												
												<logic:notPresent name="parametroGerarRelatorio">
													<html:text maxlength="3" property="subLoteOrigem" size="4"
															   onkeypress="return isCampoNumerico(event);"
															   onblur="javascript:duplicarSubLote();" tabindex="6" />
												</logic:notPresent>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			
				<tr>
					<td colspan="4">
						<table width="100%" align="center" bgcolor="#99CCFF" border="0">
							<tr>
								<td colspan="2">
									<strong>Informe os dados da inscri&ccedil;&atilde;o Final:</strong></td>
							</tr>
							<tr bgcolor="#cbe5fe">
								<td width="100%" align="center" colspan="2">
									<table width="100%" border="0">
										<tr bgcolor="#cbe5fe">
											<td width="20%"><strong>Localidade:</strong></td>
											<td>
												<html:text maxlength="3" property="localidadeDestinoID"
														   size="5" onkeyup="desabilitaIntervaloDiferente(1);"
														   onkeypress="limparDestino(1);validaEnter(event,'exibirGerarRelatorioImoveisAlteracaoInscricaoViaBatchAction.do?objetoConsulta=1&inscricaoTipo=destino', 'localidadeDestinoID');return isCampoNumerico(event);"
														   tabindex="6" /> 
												<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', null, null, 275, 480, '');limparDestino(1);">
													<img width="23" height="21" border="0"
														 src="<bean:message key="caminho.imagens"/>pesquisa.gif"
														 title="Pesquisar" /></a> 
												
												<logic:present name="corLocalidadeDestino">
													<logic:equal name="corLocalidadeDestino" value="exception">
														<html:text property="nomeLocalidadeDestino" size="45"
																   readonly="true" 
																   style="background-color:#EFEFEF; border:0; color: #ff0000" />
													</logic:equal>
			
													<logic:notEqual name="corLocalidadeDestino" value="exception">
														<html:text property="nomeLocalidadeDestino" size="45"
																   readonly="true" 
																   style="background-color:#EFEFEF; border:0; color: #000000" />
													</logic:notEqual>
												</logic:present> 
												
												<logic:notPresent name="corLocalidadeDestino">
													<logic:empty name="GerarRelatorioImoveisAlteracaoInscricaoViaBatchForm" property="localidadeDestinoID">
														<html:text property="nomeLocalidadeDestino" value=""
																   size="45" readonly="true" 
																   style="background-color:#EFEFEF; border:0; color: #ff0000" />
													</logic:empty>
													
													<logic:notEmpty name="GerarRelatorioImoveisAlteracaoInscricaoViaBatchForm" property="localidadeDestinoID">
														<html:text property="nomeLocalidadeDestino" size="45"
																   readonly="true" 
																   style="background-color:#EFEFEF; border:0; color: 	#000000" />
													</logic:notEmpty>
												</logic:notPresent> 
												
												<a href="javascript:limparBorrachaDestino(1);limparPesquisaQuadraFinal();">
													<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
														 border="0" title="Apagar" /></a>
											</td>
										</tr>
										
										<tr>
											<td><strong>Setor Comercial :</strong></td>
											<td>
												<html:text maxlength="3" property="setorComercialDestinoCD"
														   size="5" onkeyup="limparDestino(2);desabilitaIntervaloDiferente(2);"
														   onkeypress="validaEnterDependencia(event, 'exibirGerarRelatorioImoveisAlteracaoInscricaoViaBatchAction.do?objetoConsulta=2&inscricaoTipo=destino', document.forms[0].setorComercialDestinoCD, document.forms[0].localidadeDestinoID.value, 'Localidade Final.');return isCampoNumerico(event);"
														   tabindex="7" /> 
												
												<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialDestino', 'idLocalidade', document.forms[0].localidadeDestinoID.value, 275, 480, 'Informe Localidade Final.');limparDestino(2);">
													<img width="23" height="21" border="0"
													     src="<bean:message key="caminho.imagens"/>pesquisa.gif"
													     title="Pesquisar" /></a> 
												
												<logic:present name="corSetorComercialDestino">
													<logic:equal name="corSetorComercialDestino" value="exception">
														<html:text property="nomeSetorComercialDestino" size="45"
																   readonly="true" 
																   style="background-color:#EFEFEF; border:0; color: #ff0000" />
													</logic:equal>
			
													<logic:notEqual name="corSetorComercialDestino" value="exception">
														<html:text property="nomeSetorComercialDestino" size="45"
																   readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
													</logic:notEqual>
												</logic:present> 
												
												<logic:notPresent name="corSetorComercialDestino">
													<logic:empty name="GerarRelatorioImoveisAlteracaoInscricaoViaBatchForm" property="setorComercialDestinoCD">
														<html:text property="nomeSetorComercialDestino" value=""
																   size="45" readonly="true" 
																   style="background-color:#EFEFEF; border:0; color: #ff0000" />
													</logic:empty>
													
													<logic:notEmpty name="GerarRelatorioImoveisAlteracaoInscricaoViaBatchForm" property="setorComercialDestinoCD">
														<html:text property="nomeSetorComercialDestino" size="45"
																   readonly="true" 
																   style="background-color:#EFEFEF; border:0; color: #000000" />
													</logic:notEmpty>
												</logic:notPresent> 
												
												<a href="javascript:limparBorrachaDestino(2);limparPesquisaQuadraFinal();">
													<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
														 border="0" title="Apagar" /></a> 
												
												<html:hidden property="setorComercialDestinoID" />
											</td>
										</tr>
										
										<tr>
											<td><strong>Quadra:</strong></td>
											<td>
												<html:text maxlength="4" property="quadraDestinoNM"
														   size="5" onkeyup="desabilitaIntervaloDiferente(3);"
														   onkeypress="limparDestino(3);validaEnterDependencia(event, 'exibirGerarRelatorioImoveisAlteracaoInscricaoViaBatchAction.do?objetoConsulta=3&inscricaoTipo=destino', document.forms[0].quadraDestinoNM, document.forms[0].setorComercialDestinoCD.value, 'Setor Comercial Final.');return isCampoNumerico(event);"
														   tabindex="8" /> 
														   
												<logic:present name="corQuadraDestino" scope="request">
													<span style="color:#ff0000" id="msgQuadraFinal">
														<bean:write scope="request" name="msgQuadraFinal" />
													</span>
												</logic:present> 
												
												<logic:notPresent name="corQuadraDestino" scope="request"> </logic:notPresent> 
												
												<html:hidden property="quadraDestinoID" /> 
											</td>
										</tr>
										
										<tr>
											<td><strong>Lote:</strong></td>
											<td>
												<logic:present name="parametroGerarRelatorio">
													<logic:equal name="parametroGerarRelatorio"
														value="RelatorioCadastroConsumidoresInscricao">
														<html:text maxlength="4" property="loteDestino" size="5"
															tabindex="9" onkeypress="return isCampoNumerico(event);"/>
													</logic:equal>
													
													<logic:notEqual name="parametroGerarRelatorio" value="RelatorioCadastroConsumidoresInscricao">
														<logic:equal name="parametroGerarRelatorio" value="RelatorioImoveisEndereco">
															<html:text maxlength="4" property="loteDestino" size="5"
																	   tabindex="9" 
																	   onkeypress="return isCampoNumerico(event);"/>
														</logic:equal>
														
														<logic:notEqual name="parametroGerarRelatorio" value="RelatorioImoveisEndereco">
															<html:text maxlength="4" property="loteDestino" size="5"
																       tabindex="9" 
																       onkeypress="return isCampoNumerico(event);"/>
														</logic:notEqual>
													</logic:notEqual>
												</logic:present> 
												
												<logic:notPresent name="parametroGerarRelatorio">
													<html:text maxlength="4" property="loteDestino" 
															   onkeypress="return isCampoNumerico(event);"
															   size="5" tabindex="9" />
												</logic:notPresent>
											</td>
										</tr>
										
										<tr>
											<td><strong>Sub-Lote:</strong></td>
											<td>
												<logic:present name="parametroGerarRelatorio">
													<logic:equal name="parametroGerarRelatorio" value="RelatorioCadastroConsumidoresInscricao">
														<html:text maxlength="3" property="subLoteDestino" size="4"
																   onkeypress="return isCampoNumerico(event);"
																   onblur="javascript:duplicarSubLote();" tabindex="6" />
													</logic:equal>
												
													<logic:notEqual name="parametroGerarRelatorio" value="RelatorioCadastroConsumidoresInscricao">
														<logic:equal name="parametroGerarRelatorio" value="RelatorioImoveisEndereco">
															<html:text maxlength="3" property="subLoteDestino" size="4"
																	   onkeypress="return isCampoNumerico(event);"
																	   onblur="javascript:duplicarSubLote();" tabindex="6" />
														</logic:equal>
														
														<logic:notEqual name="parametroGerarRelatorio" value="RelatorioImoveisEndereco">
															<html:text maxlength="3" property="subLoteDestino" size="4"
																       onkeypress="return isCampoNumerico(event);"
																	   onblur="javascript:duplicarSubLote();" tabindex="6" />
														</logic:notEqual>
													</logic:notEqual>
												</logic:present> 
												
												<logic:notPresent name="parametroGerarRelatorio">
													<html:text maxlength="3" property="subLoteDestino" size="4"
															   onkeypress="return isCampoNumerico(event);"
															   tabindex="6" />
												</logic:notPresent>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>

				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				
			
				<tr>
					<td align="center" colspan="4" style="width: 140px;"><font color="#FF0000">*</font> Campo Obrigatório</td>
				</tr>
				
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
							          	
				<tr>
					<td height="24" style="width: 140px;">
			          	<input type="button" 
			          		   class="bottonRightCol" 
			          		   value="Limpar"
			          		   onclick="window.location.href='/gsan/exibirGerarRelatorioImoveisAlteracaoInscricaoViaBatchAction.do?menu=sim'"/>
					</td>
				
					<td align="right">
						<input type="button" 
							   name="Button" 
							   class="bottonRightCol" 
							   value="Gerar" 
							   onClick="javascript:validarForm()" />
					</td>
				</tr>							
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	
	<jsp:include
			page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=/gsan/gerarRelatorioImoveisAlteracaoInscricaoViaBatchAction.do" />	
<%@ include file="/jsp/util/rodape.jsp"%>	
</html:form></div>
</body>
</html:html>
