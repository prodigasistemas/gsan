<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@page isELIgnored="false"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />

<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet"	href="<bean:message key="caminho.css"/>popup.css"	type="text/css">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js">
</script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
	
 <link rel="stylesheet" href="<bean:message key="caminho.css"/>jquery.autocomplete.css" type="text/css" />
 <script src="<bean:message key="caminho.js"/>jquery/jquery.js"></script>
 <script type="text/javascript" src="<bean:message key="caminho.js"/>jquery/jquery.autocomplete.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.js"/>popup.js"></script>
	
  <script language="JavaScript">
 		  function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
			    var form = document.forms[0];
			
			    if(tipoConsulta == 'usuario'){
		    
		  	      	form.loginUsuario.value = codigoRegistro;
			      	form.nomeUsuario.value = descricaoRegistro;
			      	form.nomeUsuario.style.color = '#000000';
			      	
			    } else if(tipoConsulta == 'contratoParcelamento'){
			    	form.numeroContratoAnt.value = descricaoRegistro;
			    	habilitaTipoRelacao();
			    }

		  }
		  
		  function extendeTabela(tabela){
				var form = document.forms[0];
				if(eval('layerShow'+tabela).style.display == 'none'){
					
					if(tabela == 'DadosGerais'){
						eval('layerShowDebitos').style.display = 'none';
						eval('layerShowParcelamento').style.display = 'none';
					}else if(tabela == 'Debitos'){
						eval('layerShowParcelamento').style.display = 'none';
						eval('layerShowDadosGerais').style.display = 'none';
					}else if(tabela == 'Parcelamento'){
						eval('layerShowDebitos').style.display = 'none';
						eval('layerShowDadosGerais').style.display = 'none';
					}
					eval('layerShow'+tabela).style.display = 'block';
				}else{
					eval('layerShow'+tabela).style.display = 'none';
				}
			}
  			
          function validarDadosGerais(){
        	  var form = document.forms[0];
        	  var campos = "Informe: ";
  			  var retorno = true;
  		
  			
  			if(form.dataContrato == null || form.dataContrato.value == ""){
				campos += "\nData do Contrato";
			}else{
	  			if(validarData(form.dataContrato) == false){
	  				alert("Data inválida");
	  				retorno = false;
	  			}else{
	  				var hoje = new Date();
	  	            var anoAtual = hoje.getFullYear();
		  			var mesAtual = hoje.getMonth()+1;
		  			var diaAtual = hoje.getDate();
		  			if(mesAtual < 10){
		  				mesAtual = "0" + mesAtual;
		  			}
		  			if(diaAtual < 10){
		  				diaAtual = "0" + diaAtual;
		  			}
		  			
	  	            if(retorno &&  comparaData(diaAtual+"/"+mesAtual+"/"+anoAtual, "<", form.dataContrato.value)){
	  	            	alert("Data deve ser anterior ou igual à  "+ diaAtual+"/" + mesAtual + "/"+anoAtual + ".");
		  				retorno = false;
		  			}
	  			}
			}
  			
  			if(form.loginUsuario == null || form.loginUsuario.value == ""){
				campos += "\nUsuário Responsável";
			}
  			
  			
  			if((form.numeroContratoAnt != null 
  					&& form.numeroContratoAnt.value != "") 
				&& (form.relacaoAnterior.value == null 
  					|| form.relacaoAnterior.value == "" 
  					|| form.relacaoAnterior.value == "-1")){
				campos += "\nTipo Relação";
			}
  			
  			if(form.autocompleteClienteSuperior.value == "" && form.autocompleteCliente.value == ""){
  				campos += "\nCliente ou Cliente Superior não Informado";
  			}
  			
  			if(form.anoMesDebitoInicio != null && form.anoMesDebitoInicio.value != ""){
  				if(validaAnoMesSemAlert(form.anoMesDebitoInicio) == false){
  					alert("Refência do Débito inválida. Informe outra referência");
	  				retorno = false;
  				} 
  			}
  			
  			if(form.anoMesDebitoFinal != null && form.anoMesDebitoFinal.value != ""){
  				if(validaAnoMesSemAlert(form.anoMesDebitoFinal) == false){
  					alert("Refência do Débito inválida. Informe outra referência");
	  				retorno = false;
  				} 
  			}
  				
			if(form.anoMesDebitoFinal != null && form.anoMesDebitoFinal.value != ""
					&& form.anoMesDebitoInicio != null && form.anoMesDebitoInicio.value != ""){
  					
	  			if(validaAnoMesSemAlert(form.anoMesDebitoFinal) == false ){
	  				alert("Refência do Débito inválida. Informe outra referência");
	  				retorno = false;
	  			}
	  			
	  			var intAnoMesIni = form.anoMesDebitoInicio.value.substring(3,7)+ ""+ form.anoMesDebitoInicio.value.substring(0,2); 
	  			var intAnoMesFim = form.anoMesDebitoFinal.value.substring(3,7)+""+ form.anoMesDebitoFinal.value.substring(0,2); 
	  			
	  			if(retorno && intAnoMesIni > intAnoMesFim){
	  				alert("Referência Final do Período é anterior a Referência Inicial do Período.");
	  				retorno = false;
	  			}
	  			
  				var hoje = new Date();
  	            var anoAtual = hoje.getFullYear();
	  			var mesAtual = hoje.getMonth()+1;
	  			if(mesAtual < 10){
	  				mesAtual = "0" + mesAtual;
	  			}
  	            if(retorno &&  parseInt(anoAtual+""+mesAtual) < parseInt(intAnoMesFim)){
  	            	alert("A Refência Final do Débito não pode ser superior a " + mesAtual + "/"+anoAtual + ". Informe outra referência final.");
	  				retorno = false;
	  			}
	  	            
  			}
  			
			if(form.dataVencimentoInicio != null && form.dataVencimentoInicio.value != ""){
				if(	validarData(form.dataVencimentoInicio) == false ){
	  				alert("Data Inválida");
	  				retorno = false;
	  			}
			}
  				
			if(form.dataVencimentoFinal != null && form.dataVencimentoFinal.value != ""){
 					
	  			if(	validarData(form.dataVencimentoFinal) == false ){
	  				alert("Data Inválida");
	  				retorno = false;
	  			}
			}
	  		if(form.dataVencimentoFinal != null && form.dataVencimentoFinal.value != "" 
	  				&& form.dataVencimentoInicio != null && form.dataVencimentoInicio.value != "" ){
	  			
	  			if(retorno && comparaData(form.dataVencimentoInicio.value, ">", form.dataVencimentoFinal.value)){
	  				alert("Vencimento Final do Período é anterior a Vencimento Inicial do Período.");
	  				retorno = false;
	  			}
	  			
  				var hoje = new Date();
  	            var anoAtual = hoje.getFullYear();
	  			var mesAtual = hoje.getMonth()+1;
	  			var diaAtual = hoje.getDate();
	  			if(mesAtual < 10){
	  				mesAtual = "0" + mesAtual;
	  			}
	  			if(diaAtual < 10){
	  				diaAtual = "0" + diaAtual;
	  			}
	  			
  	            if(retorno &&  comparaData(diaAtual+"/"+mesAtual+"/"+anoAtual, "<", form.dataVencimentoFinal.value)){
  	            	alert("Data de Vecimento do Débito Final não pode ser superior a "+ diaAtual+"/" + mesAtual + "/"+anoAtual + ". Informe outra data final.");
	  				retorno = false;
	  			}
  	            
			}
  			
  			if(campos != "Informe: " ){
				alert(campos);
				retorno = false;
			}
        	  
        	 return retorno;
          }
          
          function validarDebitosPrimeiraEtapa(){
        	  var form = document.forms[0]; 
        	  var checkBoxs = form.contasSelecao;
        	  var checkBoxsDebitoACobrar = form.debitosSelecao;
        	  var selecionouDebitosACobrar = "";
        	  var selecionouDebitosACobrarForm = form.selecionouDebitosACobrar;
        	  var selecionouConta = "";
        	  var selecionouContaForm = form.selecionouContas;
        	
			  if(checkBoxs != undefined){
	        	  if(checkBoxs != null && checkBoxs.length == null){
						if(checkBoxs.checked == true){
							selecionouConta = "sim";
						}
				  }else{
			           	for(var i = 0; i < checkBoxs.length; i++){
			           		if(checkBoxs[i].checked == true){
								selecionouConta = "sim";			
			           		}
			           	}
				   }
			   }
			   
			   if(checkBoxsDebitoACobrar != undefined){
	        	  if(checkBoxsDebitoACobrar != null && checkBoxsDebitoACobrar.length == null){
						if(checkBoxsDebitoACobrar.checked == true){
							selecionouDebitosACobrar = "sim";
						}
				  }else{
			           	for(var i = 0; i < checkBoxsDebitoACobrar.length; i++){
			           		if(checkBoxsDebitoACobrar[i].checked == true){
								selecionouDebitosACobrar = "sim";		
			           		}
			           	}
				   }
	           	}
			   
		   		selecionouContaForm.value = selecionouConta;
	           	selecionouDebitosACobrarForm.value = selecionouDebitosACobrar;
	           	
          }
          
          function validarDebitos(){
        	  var form = document.forms[0]; 
        	  var selecionado = false;
        	  var checkBoxs = form.contasSelecao;
        	  var checkBoxsDebitoACobrar = form.debitosSelecao;
        	  var selecionouDebitosACobrar = "";
        	  var selecionouDebitosACobrarForm = form.selecionouDebitosACobrar;
        	  var selecionouConta = "";
        	  var selecionouContaForm = form.selecionouContas;
        	
			  if(checkBoxs != undefined){
	        	  if(checkBoxs != null && checkBoxs.length == null){
						if(checkBoxs.checked == true){
							selecionado = true; 
							selecionouConta = "sim";
						}
				  }else{
			           	for(var i = 0; i < checkBoxs.length; i++){
			           		if(checkBoxs[i].checked == true){
			           			selecionado = true; 
								selecionouConta = "sim";			
			           		}
			           	}
				   }
			   }
			   
			   if(checkBoxsDebitoACobrar != undefined){
		        	  if(checkBoxsDebitoACobrar != null && checkBoxsDebitoACobrar.length == null){
							if(checkBoxsDebitoACobrar.checked == true){
								selecionado = true; 
								selecionouDebitosACobrar = "sim";
							}
					  }else{
				           	for(var i = 0; i < checkBoxsDebitoACobrar.length; i++){
				           		if(checkBoxsDebitoACobrar[i].checked == true){
				           			selecionado = true; 	
									selecionouDebitosACobrar = "sim";		
				           		}
				           	}
					   }
	           	}
			   
		   		selecionouContaForm.value = selecionouConta;
	           	selecionouDebitosACobrarForm.value = selecionouDebitosACobrar;
	           	
	           	if(selecionado == false){
	           		alert("É necessário selecionar as contas ou débitos a cobrar que irão compor o débito a ser negociado");
	           	}
	           	
	           	return selecionado;
          }
          
          function validarDadosParcelamento(){
        	  var form = document.forms[0]; 
        	 	  var retorno = true;
        		  var tabela = document.getElementById("tabelaParcelaXVlParcelaFinal");
        		  var tBody = tabela.getElementsByTagName('tbody')[0];
        		  var qtdParcelas = tBody.length;
        		  
        		  if(qtdParcelas == null && qtdParcelas <= 0){
        			  alert("Informe os dados das parcelas do contrato.");
        			  retorno = false;
        		  }
        		  
        		 if(retorno 
	        		 	&& form.formaPgto != undefined 
	        		 	&& (form.formaPgto.value == null || form.formaPgto.value == "" || form.formaPgto.value == "-1")){
        			 alert("Informe a forma de pagamento do contrato.");
       			  retorno = false;
        		 }
        		
        		 if(retorno && (form.dataVencimentoPrimParcela.value == null || form.dataVencimentoPrimParcela.value == "")){
        			 alert("Informe a data de vencimento da primeira parcela.");
       			 	retorno = false;
        		 }
        		 
        		 if(retorno && !validarData(form.dataVencimentoPrimParcela)){
        			 alert("Data de Vencimento da 1a. Parcela inválida.");
       			 	retorno = false;
        		 }else{
        			 
        			 var hoje = new Date();
       	            var anoAtual = hoje.getFullYear();
     	  			var mesAtual = hoje.getMonth()+1;
     	  			var diaAtual = hoje.getDate();
     	  			if(mesAtual < 10){
     	  				mesAtual = "0" + mesAtual;
     	  			}
     	  			if(diaAtual < 10){
     	  				diaAtual = "0" + diaAtual;
     	  			}
        			 if(comparaData(diaAtual+"/"+mesAtual+"/"+anoAtual, ">", form.dataVencimentoPrimParcela.value)){
        				 alert("Data de Vencimento da 1ª Parcela " + form.dataVencimentoPrimParcela.value + " não pode ser anterior à data corrente.");
           			 	retorno = false;
        			 }
        			 
        		 }
        		 
        		 var tabela = document.getElementById("tabelaParcelaXVlParcelaFinal");
        		 var tBody = tabela.getElementsByTagName('tbody')[0];
        		 if(retorno && tBody.rows.length <= 0){
        			 alert("Informe a lista de parcelas.");
        			 	retorno = false;
        		 }
        		  
        		  return retorno;
        	  
          }
         </script>
         <script language="JavaScript"> 
          
          function clicouPrimeiraEtapa(numero){
	         validarDebitosPrimeiraEtapa();
    		 var form = document.forms[0]; 
			 form.action = "exibirAtualizarContratoParcelamentoClienteAction.do?method=mostrarPrimeiraEtapa";
       		 form.submit();
          }
          
          function clicouSegundaEtapa(numero){
        	  
        	  if(validarDadosGerais()){
       		     var form = document.forms[0]; 
  				 form.action = "exibirAtualizarContratoParcelamentoClienteAction.do?method=mostrarSegundaEtapa";
         		 form.submit();
         	 }
          }
          
 			function clicouTerceiraEtapa(etapa, numero){
 				
	        	  if(validarDadosGerais() && validarDebitos()){
	       		     var form = document.forms[0];
	  				 form.action = "exibirAtualizarContratoParcelamentoClienteAction.do?method=mostrarTerceiraEtapa";
	         		 form.submit();
	         	 }
          }
          
          function verificaEtapa(etapa,finalizouCadastro){
        	  
        	  var form = document.forms[0];

       		  if(etapa != null && etapa == 'segunda'){
       			extendeTabela('Debitos');
        	  }else if(etapa != null && etapa == 'terceira'){
        		  extendeTabela('Parcelamento');  
        		  form.confirmar.disabled = false;
        	  }else if(etapa != null && etapa == 'informarParcela'){
        		  extendeTabela('Parcelamento'); 
        		  form.confirmar.disabled = false;
        		  
        		  clicouInformarParcela();
        		  
        	  }else if(etapa != null && etapa == 'informouParcelas'){
        		  extendeTabela('Parcelamento'); 
        		  form.confirmar.disabled = false;
        	  }
        	  
          }
          
          function verificaCliqueEnter(tecla, etapa, numero){
        	  
        	  var codigo = null;
        	  if (document.all) {
        		  codigo = event.keyCode;
       		  }else{
       			 codigo = tecla.which;
       		  }
        	  
        	  if (codigo == 13) {

        		  var form = document.forms[0];
        		  var enviaForm = true;
        		  
           		  if(etapa != null && etapa == 'segunda'){
           			 form.action = "exibirAtualizarContratoParcelamentoClienteAction.do?method=mostrarSegundaEtapa";
            	  }else if(etapa != null && (etapa == 'terceira' || etapa == 'informarParcela' || etapa == 'informouParcelas') ){
                	  var numeroParcelasPopUp = document.getElementById("numeroParcelasPopUp");
            		  form.action = "exibirAtualizarContratoParcelamentoClienteAction.do?method=mostrarTerceiraEtapa";
                 	  if(numeroParcelasPopUp != null && numeroParcelasPopUp.value != ""){
                 		 form.action = form.action + "&numeroParcelasPopUp="+numeroParcelasPopUp.value;
                   		}  

                    	if(popupStatus == 1){
                    		form.action = form.action + "&indicadorTela=popup";
                        }else{
                        	form.action = form.action + "&indicadorTela=diretoTela";
                          }

            	  }else{
            		  enviaForm = false;
                  }
        		  
                  if(enviaForm){
 	           		 form.submit();
                  }
                  
       			 return false;
        	  }else{
        		  return true;
          	}
        	  
          }
  </script>
  <script type="text/javascript">
  
  function validarData(campo){

  	if (campo.value!="") {
  		
  		var erro=0;
  		var barras = campo.value.split("/");
  		
          
  		if (barras.length == 3){
  			
  			dia = barras[0];
  			mes = barras[1];
  			ano = barras[2];
              
  			resultado = (!isNaN(dia) && (dia > 0) && (dia < 32)) && (!isNaN(mes) && (mes > 0) && (mes < 13)) && (!isNaN(ano) && (ano.length == 4) && (ano >= 1900));
              
  			if (!resultado){
  				
  				campo.focus();
  				return false;
  			}
  		}
  		else{
  			campo.focus();
  			return false;
  		}
  	
  		return true;
  	}
  }
  </script>
  <script type="text/javascript">
  
  function desfazerContratoParcel(){
	    var form = document.forms[0]; 

	    document.forms[0].reset();
	    form.action = "exibirAtualizarContratoParcelamentoClienteAction.do?method=desfazerContrato";
		 form.submit();
	}
  
	function cancelarContratoParcel(){
	    document.forms[0].reset();
		window.location.href = "/gsan/telaPrincipal.do";
	}
	
	function validarFormulario(){
		
		 if(validarDadosGerais() && validarDebitos() && validarDadosParcelamento()){
		     var form = document.forms[0]; 
		     
	 		 var numeroParcelasPopUp = document.getElementById("numeroParcelasPopUp");
		     
		     form.action = "atualizarContratoParcelamentoClienteAction.do?numeroParcelasPopUp="+numeroParcelasPopUp.value
		     	+"&valorParceladoFinal="+form.valorParceladoFinal.value;
			 form.submit();
		 }
		
	}
	
	function clicouVoltarAtualizar(idClienteContrato){
		if(idClienteContrato != null){
			window.location.href = "/gsan/manterContratoParcelamentoClienteAction.do?idClienteContrato="+idClienteContrato;
		}
	}
	
	function verificaMensagem(mensagemAlerta){
		 if(mensagemAlerta != null && mensagemAlerta != ''){
   			 alert(mensagemAlerta);
   		 }
	}
	
  </script>
	
</head>
<c:if test="${clienteContrato != null }">
	<bean:define id="tipoCliente" value="cliente"></bean:define>
</c:if>
<c:if test="${clienteSuperiorContrato != null }">
	<bean:define id="tipoCliente" value="clienteSuperior"></bean:define>
</c:if>

<body leftmargin="5" topmargin="5" 
	onkeypress="return verificaCliqueEnter(event,'<c:out value="${etapa}"></c:out>', '<c:out value="${contratoAtualizar.numero}"></c:out>');" 
	onload="verificaEtapa('<c:out value="${etapa}"></c:out>'); verificarClienteHabilitado(<c:out value="${permissaoEspecial}"></c:out>,'<c:out value="${tipoCliente}"></c:out>','<c:out value="${clienteContrato.cliente.nome}"></c:out>','<c:out value="${clienteSuperiorContrato.cliente.nome}"></c:out>'); verificaRadioButtons('<c:out value="${contratoAtualizar.indicadorResponsavel}"></c:out>'); verificaMensagem('<c:out value="${mensagemAlerta}"></c:out>');">

<html:form action="/atualizarContratoParcelamentoClienteAction.do"
	name="AtualizarContratoParcelamentoPorClienteActionForm" 
	type="gcom.gui.cobranca.contratoparcelamento.AtualizarContratoParcelamentoPorClienteActionForm"
	method="post">
	
	<input type="hidden" name="controleCliente" value="" />
	
	<html:hidden property="selecionouDebitosACobrar" /> 
	<html:hidden property="selecionouContas" /> 

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
					<!--In&iacute;cio Tabela Reference a P&aacute;gina&ccedil;&atilde;o da Tela de Processo-->
		            <table>
		              <tr>
		                <td></td>
		              </tr>
		            </table>
		            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		              <tr>
		                <td width="11"><img border="0" src="imagens/parahead_left.gif"/></td>
		                <td class="parabg"> Atualizar Contrato de Parcelamento por Cliente<strong></strong></td>
		                <td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif"/></td>
		              </tr>
		            </table>
		            <!--Fim Tabela Reference a P&aacute;gina&ccedil;&atilde;o da Tela de Processo-->
					 <table width="100%" border="0">
			              <tr> 
			                <td colspan="2"> 
			                <table width="100%">
			                  	
			                  <tr bgcolor="#99CCFF" align="center">
			                    <td height="18" colspan="2" bgcolor="#99CCFF">
			                    	<div align="center">
			                    		<span class="style2"><a href="#" onclick="return clicouPrimeiraEtapa('<c:out value="${contratoAtualizar.numero}"></c:out>');"><b>Dados  Gerais do Contrato</b> </a></span><strong></strong>
		                    		</div>
	                    		</td>
			                  </tr>
		                      <tr align="center">
                  				<td colspan="2">
			                  		<div id="layerShowDadosGerais" style="display:block">
	                            		<jsp:include page="/jsp/cobranca/contratoparcelamento/contrato_parcelamento_atualizar_dados_gerais.jsp" />
	                            	</div>
			                  	</td>
			                  </tr>
			                  
			                  
			                   <tr bgcolor="#99CCFF" align="center">
			                    <td height="18" colspan="2" bgcolor="#99CCFF">
			                    	<div align="center">
			                    		<span class="style2"><a href="#" onclick="return clicouSegundaEtapa('<c:out value="${contratoAtualizar.numero}"></c:out>');"><b>D&eacute;bitos do Cliente</b> </a></span><strong></strong>
		                    		</div>
	                    		</td>
			                  </tr>
		                      <tr align="center">
                  				<td colspan="2">
			                  		<div id="layerShowDebitos" style="display:none;">
										<jsp:include page="/jsp/cobranca/contratoparcelamento/contrato_parcelamento_atualizar_debitos_cliente.jsp"/>
			                  		</div>
			                  	</td>
			                  </tr>
			                  <tr bgcolor="#99CCFF" align="center">
			                    <td height="18" colspan="2" bgcolor="#99CCFF">
			                    	<div align="center">
			                    		<span class="style2"><a href="#" onclick="return clicouTerceiraEtapa('<c:out value="${etapa}"></c:out>', '<c:out value="${contratoAtualizar.numero}"></c:out>');"><b>Dados do Parcelamento</b></a></span><strong></strong>
		                    		</div>
	                    		</td>
			                  </tr>
		                    <tr align="center">
			                  	<td colspan="2">
			                  		<div id="layerShowParcelamento" style="display:none">
											<jsp:include page="/jsp/cobranca/contratoparcelamento/contrato_parcelamento_atualizar_dados_parcelamento.jsp"/>
			                  		</div>
			                  	</td>
			                  </tr>
			               </table>
			               <table border="0">    
			                   
			                    <tr> 
			                      <td width="44%" align="left">&nbsp; </td>
			                      <td width="56%" align="right"><div align="left"><strong><font color="#FF0000">*</font></strong> Campos obrigat&oacute;rios</div></td>
			                    </tr>
			                    <tr> 
			                      <td align="left" colspan="2"> 
			                      	<input name="btnVoltar" onclick="javascript: clicouVoltarAtualizar('<c:out value="${idClienteContratoConsultar}"></c:out>');" type="button" class="bottonRightCol" value="Voltar">
			                      	<input name="btnDefazer" onclick="javascript: desfazerContratoParcel();" type="submit" class="bottonRightCol" value="Desfazer"> 
			                        <input name="cancelar" type="button" onclick="javascript: cancelarContratoParcel();" class="bottonRightCol"  value="Cancelar " /> 
			                      </td>
			                      <td align="right"> <input name="confirmar" <c:if test="${etapa ==  null || etapa != 'terceira'}" >disabled="disabled"</c:if> type="button" class="bottonRightCol" onclick="javascript: validarFormulario();" value="Concluir" /> 
			                      </td>
			                    </tr>
			                  </table></td>
			              </tr>
			             
			            </table>
					
			</td>
			
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
<%@ include file="/jsp/cobranca/contratoparcelamento/contrato_parcelamento_atualizar_informar_valor_parcela_popup.jsp"%>	

				
</body>
</html:html>

