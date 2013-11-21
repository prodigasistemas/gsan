<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="GerarRelatorioColetaMedidorEnergiaActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript">
	
	function validarForm(){
		
		var form = document.forms[0];
		if(validateGerarRelatorioColetaMedidorEnergiaActionForm(form) && 
			validarCampos() && verificarValorFinal()){
			
			toggleBox('demodiv',1);
			//submeterFormPadrao(form);
		}
	}
	
	function campoObrigatorio(){

		var form = document.forms[0];
		var msg = "";
		
		if(form.faturamentoGrupo.value == "-1"){
			msg = "Informe o Grupo de Faturamento.";
		}
		
		if( msg != ""){
			alert(msg);
			return false;
		}
		
		return true;
	}	
	
	function validarCampos(){
		
		var form = document.forms[0];
		
		msg = verificarAtributosInicialFinal(form.localidadeInicial,form.localidadeFinal,"Localidade");
		if( msg != ""){
			alert(msg);
			return false;
		}else{
			msg = verificarAtributosInicialFinal(form.codigoSetorComercialInicial,form.codigoSetorComercialFinal,"Setor Comercial");
			if( msg != ""){
				alert(msg);
				return false;
			} else{
				msg = verificarAtributosInicialFinal(form.rotaInicial,form.rotaFinal,"Rota");
				if( msg != ""){
					alert(msg);
					return false;
				}else{
					msg = verificarAtributosInicialFinal(form.sequencialRotaInicial,form.sequencialRotaFinal,"Sequencial da Rota");
					if( msg != ""){
						alert(msg);
						return false;
					}			
				}
			}
		}
		
		return true;
	}
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,campo){
		if(!campo.disabled){
	  		if (objeto == null || codigoObjeto == null){
	     		if(tipo == "" ){
	      			abrirPopup(url,altura, largura);
	     		}else{
		  			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		 		}
	 		}else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
  		}
	}
	
  	function reloadForm(){
  		var form = document.forms[0];
  	
  		form.action='exibirGerarRelatorioColetaMedidorEnergiaAction.do';
	    form.submit();
  	}
	
  	function limpar(){

  		var form = document.forms[0];

		form.localidadeInicial.value = "";
		form.nomeLocalidadeInicial.value = "";
		form.localidadeFinal.value = "";
		form.nomeLocalidadeFinal.value = "";
		form.nomeLocalidadeInicial.style.color = "#000000";
	  	form.nomeLocalidadeFinal.style.color = "#000000";
	  	
	  	form.codigoSetorComercialInicial.value = "";
		form.setorComercialInicialDescricao.value = "";
		form.codigoSetorComercialFinal.value = "";
		form.setorComercialFinalDescricao.value = "";
		form.setorComercialInicialDescricao.style.color = "#000000";
		form.setorComercialFinalDescricao.style.color = "#000000";
		
		form.rotaInicial.value = "";
		form.rotaFinal.value = "";

		form.sequencialRotaInicial.value = "";
		form.sequencialRotaFinal.value = "";
		
  		form.faturamentoGrupo.value = "-1";
  		
  		form.action='exibirGerarRelatorioColetaMedidorEnergiaAction.do?menu=sim';
	    form.submit();
  	}
  	
  	function replicarLocalidade(){
		var formulario = document.forms[0]; 

		formulario.localidadeFinal.value = formulario.localidadeInicial.value;
	}
	
	function replicarSetorComercial(){
		var formulario = document.forms[0]; 
		
		formulario.codigoSetorComercialFinal.value = formulario.codigoSetorComercialInicial.value;
	}
	
	function replicarRota(){
		var formulario = document.forms[0]; 
		formulario.rotaFinal.value = formulario.rotaInicial.value;
		formulario.sequencialRotaInicial.focus;
	} 
  	
	function replicarSequencialRota(){
		var formulario = document.forms[0];
		formulario.sequencialRotaFinal.value = formulario.sequencialRotaInicial.value;
	} 
	
	function verificarValorFinal(){
		var form = document.forms[0];
		
		if(form.localidadeFinal.value < form.localidadeInicial.value){
			alert("Localidade Final menor que Localidade Inicial.");
			return false;
		}
		
		if(form.codigoSetorComercialFinal.value < form.codigoSetorComercialInicial.value){
			alert("Setor Comercial Final menor que Setor Comercial Inicial.");
			return false;
		}
		
		if(form.rotaFinal.value < form.rotaInicial.value){
			alert("Rota Final menor que Rota Inicial.");
			return false;
		}
		
		if(form.sequencialRotaFinal.value < form.sequencialRotaInicial.value){
			alert("Sequencia de Rota Final menor que Sequencia de Rota Inicial.");
			return false;
		}
		
		return true;
	}
	
	function limparOrigem(tipo){
		var form = document.forms[0];

		switch(tipo){
				   
			case 1: //De localidade pra baixo
				form.nomeLocalidadeInicial.value = "";
				
				form.localidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";
				
				form.codigoSetorComercialInicial.value = "";
			    form.setorComercialInicialDescricao.value = "";
			    
			    form.codigoSetorComercialFinal.value = "";
			    form.setorComercialFinalDescricao.value = "";
			    
			  	form.rotaInicial.value = "";
			   	form.sequencialRotaInicial.value = "";
			   	break;

		    case 2: //De Setor Comercial para baixo
			   form.setorComercialInicialDescricao.value = "";
			   
			   form.codigoSetorComercialFinal.value = "";
			   form.setorComercialFinalDescricao.value = "";
			   
			   form.rotaInicial.value = "";
			   form.sequencialRotaInicial.value = "";
			   break;
			   
		}
	}
	
	function limparBorrachaOrigem(tipo){
		var form = document.forms[0];
		
		switch(tipo){
			case 1: //De localidara pra baixo

				form.localidadeInicial.value = "";
				form.nomeLocalidadeInicial.value = "";
				form.localidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";
				
				form.codigoSetorComercialInicial.value = "";
			    form.setorComercialInicialDescricao.value = "";
			    form.codigoSetorComercialFinal.value = "";
			    form.setorComercialFinalDescricao.value = "";
				break;
				
			case 2: //De Setor Comercial para baixo

				form.codigoSetorComercialInicial.value = "";
			    form.setorComercialInicialDescricao.value = "";
			    form.codigoSetorComercialFinal.value = "";
			    form.setorComercialFinalDescricao.value = "";
				break;
		}
	}
	
	function limparBorrachaDestino(tipo){
		var form = document.forms[0];

		switch(tipo){
			case 1: //De localidade pra baixo
				form.localidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";
				
				form.codigoSetorComercialFinal.value = "";
			    form.setorComercialFinalDescricao.value = "";
				break;		   
				
			case 2: //De Setor Comercial para baixo
			    form.codigoSetorComercialFinal.value = "";
			    form.setorComercialFinalDescricao.value = "";
				break;	
		}
	}
	
	function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

		var form = document.forms[0];
	
		if (tipoConsulta == 'setorComercialOrigem') {
	        form.codigoSetorComercialInicial.value = codigoRegistro;
	  		form.setorComercialInicialDescricao.value = descricaoRegistro;
	  		
	  		form.codigoSetorComercialFinal.value = codigoRegistro;
      		form.setorComercialFinalDescricao.value = descricaoRegistro;
      		
	  		form.setorComercialInicialDescricao.style.color = "#000000";
	  		form.setorComercialFinalDescricao.style.color = "#000000";
	  		
	  		form.rotaInicial.focus();
	     
		}
		
		if (tipoConsulta == 'setorComercialDestino') {
      		
	  		form.codigoSetorComercialFinal.value = codigoRegistro;
      		form.setorComercialFinalDescricao.value = descricaoRegistro;
	  		form.setorComercialFinalDescricao.style.color = "#000000";
	  		
	  		form.rotaInicial.focus();
		}
		
		desabilitaIntervaloDiferente();
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'localidadeOrigem') {
      		
      		form.localidadeInicial.value = codigoRegistro;
	  		form.nomeLocalidadeInicial.value = descricaoRegistro;
	  		
	  		form.localidadeFinal.value = codigoRegistro;
      		form.nomeLocalidadeFinal.value = descricaoRegistro;
      		
	  		form.nomeLocalidadeInicial.style.color = "#000000";
	  		form.nomeLocalidadeFinal.style.color = "#000000";
	  		
	  		form.codigoSetorComercialInicial.focus();
		}

		if (tipoConsulta == 'localidadeDestino') {
		
      		form.localidadeFinal.value = codigoRegistro;
      		form.nomeLocalidadeFinal.value = descricaoRegistro;
	  		form.nomeLocalidadeFinal.style.color = "#000000";
			
		}
		
		if (tipoConsulta == 'setorComercialOrigem') {
      		
      		form.codigoSetorComercialInicial.value = codigoRegistro;
	  		form.setorComercialInicialDescricao.value = descricaoRegistro;
	  		
	  		form.codigoSetorComercialFinal.value = codigoRegistro;
      		form.setorComercialFinalDescricao.value = descricaoRegistro;
      		
	  		form.setorComercialInicialDescricao.style.color = "#000000";
	  		form.setorComercialFinalDescricao.style.color = "#000000";
	  		
	  		form.rotaInicial.focus();
		}
		
		if (tipoConsulta == 'setorComercialDestino') {
      		
	  		form.codigoSetorComercialFinal.value = codigoRegistro;
      		form.setorComercialFinalDescricao.value = descricaoRegistro;
	  		form.setorComercialFinalDescricao.style.color = "#000000";
	  		
	  		form.rotaInicial.focus();
		}
		
		desabilitaIntervaloDiferente();
	}
		
  	function desabilitaIntervaloDiferente(){
		var form = document.GerarRelatorioColetaMedidorEnergiaActionForm;	
	    
	    if(form.localidadeInicial.value != form.localidadeFinal.value){	        
		 	form.codigoSetorComercialInicial.disabled = true;
		 	form.codigoSetorComercialFinal.disabled = true;
		 	form.codigoSetorComercialInicial.value = "";
		 	form.codigoSetorComercialFinal.value = "";
		 	
		 	form.rotaInicial.disabled = true;
		 	form.rotaFinal.disabled = true;
		 	form.sequencialRotaInicial.disabled = true;
		 	form.sequencialRotaFinal.disabled = true;
		 	form.rotaInicial.value = "";
		 	form.rotaFinal.value = "";
		 	form.sequencialRotaInicial.value = "";
		 	form.sequencialRotaFinal.value= "";
		 	
		  } else if(form.codigoSetorComercialInicial.value != form.codigoSetorComercialFinal.value){
		  	form.rotaInicial.disabled = true;
		 	form.rotaFinal.disabled = true;
		 	form.sequencialRotaInicial.disabled = true;
		 	form.sequencialRotaFinal.disabled = true;
		 	form.rotaInicial.value = "";
		 	form.rotaFinal.value = "";
		 	form.sequencialRotaInicial.value = "";
		 	form.sequencialRotaFinal.value= "";
		  	
		  }else if(form.rotaInicial.value != form.rotaFinal.value){
		 	form.rotaInicial.disabled = false;
		 	form.rotaFinal.disabled = false;
		 	form.sequencialRotaInicial.disabled = true;
		 	form.sequencialRotaFinal.disabled = true;
		 	form.sequencialRotaInicial.value = "";
		 	form.sequencialRotaFinal.value= "";
		 	
		 }else{
		 	form.sequencialRotaInicial.disabled = false;
		 	form.sequencialRotaFinal.disabled = false;
		 	
		 }	 
	}	
	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="desabilitaIntervaloDiferente();">

<html:form action="/gerarRelatorioColetaMedidorEnergiaAction.do"
	name="GerarRelatorioColetaMedidorEnergiaActionForm"
	type="gcom.gui.relatorio.cadastro.micromedicao.GerarRelatorioColetaMedidorEnergiaActionForm"
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
					<td class="parabg">Gerar Relat&oacute;rio de Coleta de Medidor de Energia</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Para gerar o relat&oacute;rio, informe os dados abaixo:</td>
				</tr>	
				
				<tr>
					<td>
						<strong>Grupo Faturamento:<font color="#FF0000"></font></strong>
					</td>

					<td>
						<strong> 
						<html:select property="faturamentoGrupo" style="width: 230px;" tabindex="2">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoFaturamentoGrupo" 
										   scope="request">
										   <html:options collection="colecaoFaturamentoGrupo"
														 labelProperty="descricao" 
														 property="id" 
										   />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>			
              	
				<tr>
					<td><strong>Localidade Inicial:</strong></td>
					<td>	
						<html:text maxlength="3" 
							tabindex="1"
							property="localidadeInicial" 
							size="3"
							onkeyup="javascript:replicarLocalidade();desabilitaIntervaloDiferente();"
							onkeypress="javascript:limparOrigem(1);validaEnterComMensagem(event, 'exibirGerarRelatorioColetaMedidorEnergiaAction.do?objetoConsulta=1','localidadeInicial','Localidade Inicial');return isCampoNumerico(event);"/>
							
						<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '',document.forms[0].localidadeInicial);limparOrigem(1);">
							<img width="23" 
								height="21" 
								border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Localidade" /></a>
								
						<logic:present name="localidadeInicialEncontrada" scope="request">
							<html:text property="nomeLocalidadeInicial" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="localidadeInicialEncontrada" scope="request">
							<html:text property="nomeLocalidadeInicial" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						<a href="javascript:limparBorrachaOrigem(1);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Localidade Final:</strong></td>
					<td>
						<html:text maxlength="3" 
							tabindex="1"
							property="localidadeFinal" 
							size="3"
							onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioColetaMedidorEnergiaAction.do?objetoConsulta=2','localidadeFinal','Localidade Final');return isCampoNumerico(event);"
							onblur="javascript:desabilitaIntervaloDiferente();verificarValorFinal();"/>
						<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', null, null, 275, 480, '',document.forms[0].localidadeFinal);limparBorrachaDestino(1);">
							<img width="23" 
								height="21" 
								border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Localidade" /></a>
								 
						<logic:present name="localidadeFinalEncontrada" scope="request">
							<html:text property="nomeLocalidadeFinal" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="localidadeFinalEncontrada" scope="request">
							<html:text property="nomeLocalidadeFinal" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						
						<a href="javascript:limparBorrachaDestino(1);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Setor Comercial Inicial:<font color="#FF0000"></font></strong></td>
					
					<td>	
						<html:text maxlength="3" 
							tabindex="3"
							property="codigoSetorComercialInicial" 
							size="3"
							onkeyup="javascript:replicarSetorComercial();desabilitaIntervaloDiferente();"
							onkeypress="javascript:limparOrigem(2);validaEnterDependencia(event, 'exibirGerarRelatorioColetaMedidorEnergiaAction.do?objetoConsulta=3', this, document.forms[0].localidadeInicial.value,'Localidade');return isCampoNumerico(event);"/>
							
						<a href="javascript:abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].localidadeInicial.value+'&tipo=setorComercialOrigem&indicadorUsoTodos=1',document.forms[0].localidadeInicial.value,'Localidade', 400, 800);">
		
							<img width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Setor Comercial" /></a> 
								
						<logic:present name="setorComercialInicialEncontrada" scope="request">
							<html:text property="setorComercialInicialDescricao" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="setorComercialInicialEncontrada" scope="request">
							<html:text property="setorComercialInicialDescricao" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						<a href="javascript:limparBorrachaOrigem(2);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Setor Comercial Final:</strong></td>
					<td>
						<html:text maxlength="3" 
							tabindex="1"
							property="codigoSetorComercialFinal" 
							size="3"
							onkeypress="validaEnterDependencia(event, 'exibirGerarRelatorioColetaMedidorEnergiaAction.do?objetoConsulta=4', this, document.forms[0].localidadeInicial.value,'Localidade');return isCampoNumerico(event);"
							onblur="javascript:desabilitaIntervaloDiferente();verificarValorFinal();"/>
						
						<a href="javascript:abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].localidadeInicial.value+'&tipo=setorComercialDestino&indicadorUsoTodos=1',document.forms[0].localidadeInicial.value,'Localidade', 400, 800);">
		
							<img width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Setor Comercial" /></a> 		 

						<logic:present name="setorComercialFinalEncontrada" scope="request">
							<html:text property="setorComercialFinalDescricao" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="setorComercialFinalEncontrada" scope="request">
							<html:text property="setorComercialFinalDescricao" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
						
						<a href="javascript:limparBorrachaDestino(2);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Rota Inicial:</strong></td>
					<td>
						<html:text maxlength="4" 
							tabindex="1"
							property="rotaInicial"
							onkeypress="return isCampoNumerico(event);"
							onkeyup="javascript:replicarRota();desabilitaIntervaloDiferente();"
							size="4"/>
					</td>
				</tr>

				<tr>
					<td><strong>Rota Final:</strong></td>
					<td>
						<html:text maxlength="4" 
							tabindex="1"
							property="rotaFinal"
							onkeypress="return isCampoNumerico(event);" 
							onkeyup="javascript:desabilitaIntervaloDiferente();verificarValorFinal();"
							size="4"/>
					</td>
				</tr>

				<tr>
					<td><strong>Sequencial Inicial da Rota:</strong></td>
					<td>
						<html:text maxlength="4" 
							tabindex="1"
							property="sequencialRotaInicial"
							onkeyup="javascript:replicarSequencialRota();"
							onkeypress="return isCampoNumerico(event);"
							size="4"/>
					</td>
				</tr>

				<tr>
					<td><strong>Sequencial Final da Rota:</strong></td>
					<td>
						<html:text maxlength="4" 
							tabindex="1"
							property="sequencialRotaFinal"
							onkeypress="return isCampoNumerico(event);"
							onkeyup="javascript:verificarValorFinal();"
							size="4"/>
					</td>
				</tr>
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left" colspan="2"><strong><font color="#FF0000">*</font></strong>

					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
								
				<tr>
					<td height="24" >
			          	<input type="button" 
			          		class="bottonRightCol" 
			          		value="Limpar" 
			          		onclick="javascript:limpar();"/>
			          	<font color="#FF0000"> <input type="button"
							name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</font>
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
<%@ include file="/jsp/util/rodape.jsp"%>	
</body>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioColetaMedidorEnergiaAction.do" />

</html:form>
</html:html>