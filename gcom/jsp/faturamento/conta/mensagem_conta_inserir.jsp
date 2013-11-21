<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>


<%@ page import="gcom.util.ConstantesSistema" %>
<%@ page import="gcom.cadastro.localidade.SetorComercial" %>
<%@ page import="gcom.cadastro.localidade.Quadra" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirMensagemContaActionForm" />

<script language="JavaScript">
 
function validarGrupoFaturamento(visulizar){

	var form = document.forms[0];
	
		var ok = true;
		if(form.gerenciaRegional.value != ""){
			ok = false;
		}
		if(form.localidade.value != ""){
			ok = false;
		}
		if(form.qualidadeAgua.value != ""){
			ok = false;
		}
		
		if(ok == true){
			
			if(form.grupoFaturamento.value != ""){
				
				form.gerenciaRegional.disabled = true;
		    	form.localidade.disabled = true;
		    	form.setorComercial.disabled = true;		    	
		    	form.quadra.disabled = true;		    	
		        
		    	form.qualidadeAgua.disabled = true; 
				form.indiceInicial.value = "";
		 		form.indiceInicial.disabled = true;
		 		form.indiceFinal.value = "";
		 		form.indiceFinal.disabled = true;
		 		
		 		form.selecionar.disabled = true;
			}
			else{
				form.gerenciaRegional.disabled = false;
		    	form.localidade.disabled = false;
		        
		        habilitarQualidadeAgua();
			}
			
		}else{
			if(visulizar == 1){
				alert("Gerência Regional/Localidade/Setor Comercial/Qualidade de Água");
				form.grupoFaturamento.value = "";
			}
		}
}

function desabilitarGrupoFaturamento(){
    
    var form = document.forms[0];
    
    if (form.gerenciaRegional.value != ""){
      
      form.grupoFaturamento.value = "";
      form.grupoFaturamento.disabled = true;
      
      form.localidade.value = "";
      form.localidadeDescricao.value = "";
      form.localidade.disabled = true;
      
	  reiniciarListBox(form.setorComercial);
	  form.setorComercial.disabled = true;
	  
	  reiniciarListBox(form.quadra);
	  form.quadra.disable = true;	  
    }
    else{
      
      form.grupoFaturamento.disabled = false;
      form.localidade.disabled = false;
	  form.setorComercial.disabled = false;
	  form.quadra.disabled = false;	  
    }
}
  
  function desabilitarGrupoFaturamento2(){
    
    var form = document.forms[0];
    
    if (form.localidade.value != ""){

      form.grupoFaturamento.value = "";
      form.grupoFaturamento.disabled = true;
      
      form.gerenciaRegional.value = "";
      form.gerenciaRegional.disabled = true;
      
      form.qualidadeAgua.value = "";
	  form.qualidadeAgua.disabled = true; 
	  form.indiceInicial.value = "";
	  form.indiceInicial.disabled = true;
	  form.indiceFinal.value = "";
  	  form.indiceFinal.disabled = true;
  	  
  	  form.selecionar.disabled = true;
      
    }
    else{
      
      form.grupoFaturamento.disabled = false;
      form.gerenciaRegional.disabled = false;
      
      habilitarQualidadeAgua();
      
    }
  }
  
  function verificaPreenchimentoLocalidade(){
  
  	var form = document.forms[0];
    
    if (form.localidadeDescricao.value == ""){
      
      if (form.gerenciaRegional.value == ""){
      	form.grupoFaturamento.disabled = false;
      }
      else{
      	form.grupoFaturamento.value = "";
      	form.grupoFaturamento.disabled = true;
      }
      
      form.gerenciaRegional.disabled = false;
      form.qualidadeAgua.disabled = false;
      
      habilitarQualidadeAgua();
      
      if (form.qualidadeAgua.value != ""){
      	
      	form.grupoFaturamento.value = "";
      	form.grupoFaturamento.disabled = true;
      
      	form.gerenciaRegional.value = "";
      	form.gerenciaRegional.disabled = true;
      	
      	form.localidade.value = "";
      	form.localidade.disabled = true;
      }
    }
    else{
      
      form.grupoFaturamento.value = "";
      form.grupoFaturamento.disabled = true;
      
      form.gerenciaRegional.value = "";
      form.gerenciaRegional.disabled = true;
      
      form.qualidadeAgua.value = "";
	  form.qualidadeAgua.disabled = true; 
	  form.indiceInicial.value = "";
	  form.indiceInicial.disabled = true;
	  form.indiceFinal.value = "";
  	  form.indiceFinal.disabled = true;
  	  
  	  form.selecionar.disabled = true;
    }
  }
  

 function chamaPopupLocalidade(){
 	var form = document.InserirMensagemContaActionForm;
 	
 	if ((form.gerenciaRegional.disabled == true || form.gerenciaRegional.value == "") && 
 	(form.grupoFaturamento.disabled == true || form.grupoFaturamento.value == "") &&
 	(form.qualidadeAgua.disabled == true || form.qualidadeAgua.value == "")){

 		abrirPopup('exibirPesquisarLocalidadeAction.do');
 		limpaLocalidadeSemApagarCodigo();
 	}
 }
 
 
function validaForm(){
	var form = document.InserirMensagemContaActionForm;
	if (validateInserirMensagemContaActionForm(form)){
		if (verificaAnoMesReferenciaFaturamento(form.referenciaFaturamento) &&
			verificaIndiceParaQualidadeAgua(form)){
			
			submeterFormPadrao(form);
		} 
	}
}

function verificaIndiceParaQualidadeAgua(form){

	msg = "";
	retorno = true;
	
	if (form.qualidadeAgua.value == ""){
		return retorno;
	}
	else{
	
		if (form.indiceInicial.value == ""){
			msg = "Informe Índice Inicial \n";
			form.indiceInicial.focus();
			retorno = false; 
		}
		
		if (form.indiceFinal.value == ""){
			msg = msg + "Informe Índice Final \n";
			form.indiceFinal.focus();
			retorno = false; 
		}
	}
	
	if (!retorno){
		alert(msg);
	}
	
	return retorno;
}
 
 function verificaAnoMesReferenciaFaturamento(mydata) {
	
	var situacao = true;
	
	if (mydata.value.length == 7) {
	
		dia = 1;
    	mes = mydata.value.substring(0,2); 
    	ano = mydata.value.substring(3,7); 

    	if ((!isNaN(mes) || !isNaN(ano)) && (mes.indexOf('.') == -1 && mes.indexOf(',') == -1 && mes.indexOf('/') == -1) &&
			(ano.indexOf('.') == -1 && ano.indexOf(',') == -1 && ano.indexOf('/') == -1)) {
    	
    		// verifica se o mes e valido 
	    	if ((mes * 1) >= 1 && (mes * 1) <= 12 ) { 
	        
	        	// verifica se o ano e valido
	        	if ((ano * 1) != 0 && (ano * 1) >= 1980) {
	        	
	        		// verifica se e ano bissexto 
	    			if ((mes * 1) == 2 && ((dia * 1) < 1 || (dia * 1) > 29 
	    				|| ((dia * 1) > 28 && (((ano * 1) / 4) != (ano * 1) / 4)))) { 
	       				situacao = false; 
	    			}
	        	}
	        	else{
	        		situacao = false;
	        	}
	    	}
	    	else{
	    		situacao = false;
	    	} 
		}
		else{
			situacao = false;
		}
    }
    else if (mydata.value.length > 0){
    	situacao = false;
    }
    
    if (!situacao) { 
	   alert("Referência do Faturamento inválida.");
	   mydata.value = "";
	   mydata.focus(); 
	}
	
	return situacao;
}
 
 
 function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	
	var form = document.InserirMensagemContaActionForm;
	
	if (tipoConsulta == "localidade") {
      
      limpaLocalidade();
      
      form.localidade.value = codigoRegistro;
      form.localidadeDescricao.value = descricaoRegistro;
      form.localidadeDescricao.style.color = "#000000";
      form.setorComercial.focus();
      form.grupoFaturamento.value = "";
      form.grupoFaturamento.disabled = true;
      form.gerenciaRegional.value = "";
      form.gerenciaRegional.disabled = true;
      
      reiniciarListBox(form.setorComercial);
 	  reiniciarListBox(form.quadra);
      
      form.action = "exibirInserirMensagemContaAction.do"
 	  submeterFormPadrao(form);

    }
}
 
 function limpaLocalidade(){
 	var form = document.InserirMensagemContaActionForm;

 	form.localidade.value = "";
 	form.localidadeDescricao.value = "";
 	reiniciarListBox(form.quadra); 	
	form.quadra.disabled = true;
	
	form.setorComercial.value = "";	
	reiniciarListBox(form.setorComercial);
    form.setorComercial.disabled = true;
		
	if (form.gerenciaRegional.value == ""){
	  
	  form.grupoFaturamento.disabled = false;
      form.gerenciaRegional.disabled = false;
      form.localidade.disabled = false;
	  form.setorComercial.disabled = false;
	  form.quadra.disabled = false;
	  
	  habilitarQualidadeAgua();
	} else {
		form.setorComercial.disabled = true;
		form.quadra.disabled = true;
	}
	
	if (!form.localidade.disabled){
 		form.localidade.focus();
 	}
 	
 	if ( form.grupoFaturamento != "" ) {
 		form.setorComercial.disabled = true;
 		form.quadra.disabled = true;
 	}
 
 }

 function limpaLocalidadeSemApagarCodigo(){
 	var form = document.InserirMensagemContaActionForm;

 	form.localidadeDescricao.value = "";
 	reiniciarListBox(form.setorComercial);
 	reiniciarListBox(form.quadra); 	
 	
 	form.setorComercial.value = "";	
	reiniciarListBox(form.setorComercial);
    form.setorComercial.disabled = true;
 
 }
 
 function habilitaMensagem2(){
 	var form = document.InserirMensagemContaActionForm;
 	
 	if (form.mensagemConta01.value != ""){
 	  form.mensagemConta02.disabled = false;
 	} else {
 	  form.mensagemConta02.value = "";
 	  form.mensagemConta02.disabled = true;
 	  form.mensagemConta03.value = "";
 	  form.mensagemConta03.disabled = true;  	
 	}
 }
 
  function habilitaMensagem3(){
 	var form = document.InserirMensagemContaActionForm;
 	
 	if (form.mensagemConta02.value != ""){
 	  form.mensagemConta03.disabled = false;
 	} else {
 	  form.mensagemConta03.value = "";
 	  form.mensagemConta03.disabled = true; 	
 	}
 }
 
 function habilitarIndice(){
 
 	var form = document.forms[0];
 	
 	if (form.qualidadeAgua.value == ""){
 		
 		form.grupoFaturamento.disabled = false;
 		form.gerenciaRegional.disabled = false;
 		form.localidade.disabled = false;
 		
 		form.indiceInicial.value = "";
 		form.indiceInicial.disabled = true;
 		form.indiceFinal.value = "";
 		form.indiceFinal.disabled = true;
 		
 		form.selecionar.disabled = true;
 	}
 	else{
 		
 		form.grupoFaturamento.value = "";
 		form.grupoFaturamento.disabled = true;
 		form.gerenciaRegional.value = "";
 		form.gerenciaRegional.disabled = true;
 		form.localidade.value = "";
 		form.localidade.disabled = true;
 		
 		form.indiceInicial.disabled = false;
 		form.indiceFinal.disabled = false;
 		
 		form.selecionar.disabled = false;
 	}
 }
 
 function carregarSetoresComerciaisPorQualidade(){
 	
 	var form = document.forms[0];
 	
 	if (form.qualidadeAgua.value != "" && form.indiceInicial.value != "" &&
 		form.indiceFinal.value != ""){
 	
 		form.action = "exibirInserirMensagemContaAction.do"
 		submeterFormPadrao(form);
 	}
 	else{
 		reiniciarListBox(form.setorComercial);
 		reiniciarListBox(form.quadra); 		
 	}
 }
 
 function habilitarQualidadeAgua(){
 	
 	var form = document.forms[0];
 	
 	if (form.referenciaFaturamento.value == ""){
 	
 		form.qualidadeAgua.value = ""
 		form.qualidadeAgua.disabled = true;
 		form.indiceInicial.value = "";
 		form.indiceInicial.disabled = true;
 		form.indiceFinal.value = "";
 		form.indiceFinal.disabled = true;
 		
 		form.selecionar.disabled = true;
 	}
 	else{
 	
 		form.qualidadeAgua.disabled = false;
 	}
 }
 
function desabilitaOuHabilitaQuadra(){
 	var form = document.forms[0];
		cont = 0;
	 	for ( i = 0; i < form.setorComercial.length; i++) {
			if ( form.setorComercial[i].selected ) {
				cont += 1;
			}
		}
		if( cont > 1) {	
			 	reiniciarListBox(form.quadra); 				
		} 
		if ( cont == 1 ){
			for ( i = 0; i < form.setorComercial.length; i++) {
				if ( form.setorComercial[i].selected ) {
					if ( form.setorComercial[i].value != "" ) {
						obj = form.setorComercial[i].value;
						form.action = 'exibirInserirMensagemContaAction.do?acao=quadra&id='+obj;
						form.submit();
					} else {
						form.action = 'exibirInserirMensagemContaAction.do';
						form.submit();
					}
				}
			}
		}
}


</script>
</head>

<logic:present name="identificadorPesquisa" scope="request">
	<body leftmargin="5" topmargin="5" >
</logic:present>
<logic:notPresent name="identificadorPesquisa" scope="request">
	<body leftmargin="5" topmargin="5"
		onload="javascript:setarFoco('${requestScope.referenciaFaturamento}');">
</logic:notPresent>

<html:form action="/inserirMensagemContaAction.do"
	name="InserirMensagemContaActionForm"
	type="gcom.gui.faturamento.conta.InserirMensagemContaActionForm"
	method="post" onsubmit="return validateInserirMensagemContaActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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
			<td width="615" valign="top" class="centercoltext">
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
					<td class="parabg">Inserir Mensagem da Conta</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table border="0" width="100%">
				<tr>
					<td>Para adicionar a mensagem da conta, informe os
					dados abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoContaMensagemInserir', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>				
				</tr>
			</table>
			<table border="0" width="100%">	
				<tr>
					<td width="30%">
						<strong>Referência do Faturamento:<font color="#ff0000">*</font></strong>
					</td>
					<td align="right" colspan="3">
						<div align="left">
							<html:text property="referenciaFaturamento"
										size="7" 
										maxlength="7" 
										onkeyup="mascaraAnoMes(this, event); verificaPreenchimentoLocalidade();"
										onkeypress="return isCampoNumerico(event);"/>
							mm/aaaa
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<strong>Mensagem da Conta:<font color="#ff0000">*</font></strong>
					</td>

					<td align="right" colspan="3">
						<div align="left">
							<html:text property="mensagemConta01"
										maxlength="100" 
										size="60" 
										onkeyup="habilitaMensagem2();" />
						</div>
					</td>
				</tr>
				<tr>
					<td><strong></strong></td>

					<td align="right" colspan="3">
					<div align="left"><html:text property="mensagemConta02"
						maxlength="100" size="60" disabled="true" onkeyup="habilitaMensagem3();"/></div>
					</td>
				</tr>
				<tr>
					<td><strong></strong></td>

					<td align="right" colspan="3">
					<div align="left"><html:text property="mensagemConta03"
						maxlength="100" size="60" disabled="true" /></div>
					</td>
				</tr>
				
				<tr>
					<td><strong>Grupo de Faturamento:</strong></td>
					<td align="right" colspan="3">
					<div align="left"><strong> <html:select property="grupoFaturamento"
						style="width: 150px;" onchange="validarGrupoFaturamento(1);">
						<logic:present name="colecaoFaturamentoGrupo">
							<html:option value="">&nbsp;</html:option>
							<html:options collection="colecaoFaturamentoGrupo"
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select> </strong></div>
					</td>
				</tr>

				<tr>
					<td colspan="2">
					
						<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
							<td align="center"><strong>Dados da Qualidade de Água</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" border="0">
								<tr>
									<td width="31%"><strong>Qualidade de Água:</strong></td>
									<td colspan="3"><html:select property="qualidadeAgua"
										style="width: 150px;" onchange="habilitarIndice();">
										<html:option value="">&nbsp;</html:option>
										<html:option value="<%= ""+ConstantesSistema.TURBIDEZ %>">TURBIDEZ</html:option>
										<html:option value="<%= ""+ConstantesSistema.CLORO%>">CLORO</html:option>
										<html:option value="<%= ""+ConstantesSistema.PH%>">PH</html:option>
										<html:option value="<%= ""+ConstantesSistema.COR%>">COR</html:option>
										<html:option value="<%= ""+ConstantesSistema.FLUOR%>">FLUOR</html:option>
										<html:option value="<%= ""+ConstantesSistema.FERRO%>">FERRO</html:option>
										<html:option value="<%= ""+ConstantesSistema.COLIFORMES_TOTAIS%>">COLIFORMES TOTAIS</html:option>
										<html:option value="<%= ""+ConstantesSistema.COLIFORMES_FECAIS%>">COLIFORMES FECAIS</html:option>
										<html:option value="<%= ""+ConstantesSistema.NITRATO%>">NITRATO</html:option>
										</html:select></td>
								</tr>
								<tr>
									<td><strong>Índice Inicial:</strong></td>
									<td><html:text property="indiceInicial" size="10"
										maxlength="8" style="text-align: right;" 
										onkeyup="formataValorMonetario(this, 7);"/></td>
									<td><strong>Índice Final:</strong></td>
									<td><html:text property="indiceFinal" size="10"
										maxlength="8" style="text-align: right;" 
										onkeyup="formataValorMonetario(this, 7);"/>
										
										<input name="selecionar" class="bottonRightCol" value="Selecionar" type="button"
										onclick="carregarSetoresComerciaisPorQualidade();"></td>
								</tr>
								
							</table>

							</td>
						</tr>
						</table>
						
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
					
						<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
							<td align="center"><strong>Dados de Localização Geográfica</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" border="0">
								
								<tr>
									<td width="30%"><strong>Gerência Regional:</strong></td>
									<td><html:select property="gerenciaRegional"
										style="width: 230px;"
										onchange="limpaLocalidade(); desabilitarGrupoFaturamento();">
										<logic:present name="colecaoGerenciaRegional">
											<html:option value="">&nbsp;</html:option>
											<logic:iterate name="colecaoGerenciaRegional" id="gerenciaRegional" >
											<option value="<bean:write property="id" name="gerenciaRegional"/>">
											<bean:write property="nomeAbreviado" name="gerenciaRegional"/> - <bean:write property="nome" name="gerenciaRegional"/>
											</option>
											</logic:iterate>
										</logic:present>
									</html:select></td>
								</tr>
								<tr>
									<td><strong>Localidade:</strong></td>
									<td>
										<html:text property="localidade" size="3"
													maxlength="3" 
													onkeyup="desabilitarGrupoFaturamento2();"
													onkeydown="limpaLocalidadeSemApagarCodigo();"
													onkeypress="validaEnter(event, 'exibirInserirMensagemContaAction.do', 'localidade');return isCampoNumerico(event);" />
									<a href="javascript:chamaPopupLocalidade();"> 
										<img src="imagens/pesquisa.gif" style="" height="21" width="23"
											 border="0" title="Pesquisar Localidade">
									</a> 
									<logic:present name="localidadeInexistente" scope="request">
										<html:text property="localidadeDescricao" 
													size="40" 
													maxlength="40"
													readonly="true"
													style="background-color:#EFEFEF; border:0; color: #ff0000;" />
									</logic:present> 
									<logic:notPresent name="localidadeInexistente" scope="request">
										<html:text property="localidadeDescricao" 
													size="40" 
													maxlength="40"
													readonly="true"
													style="background-color:#EFEFEF; border:0; color: #000000;" />
									</logic:notPresent> 
										<a href="javascript:limpaLocalidade();">
											<img src="imagens/limparcampo.gif" 
												 border="0" title="Apagar">
										</a>
									</td>
								</tr>
								
								<tr>
									<td><strong>Setor Comercial:</strong></td>
									<td><html:select styleId="listSetor" property="setorComercial" style="width: 400px;" multiple="mutiple" size="4" 
													 onchange="javascript:desabilitaOuHabilitaQuadra();" >
											
											<logic:present name="colecaoSetorComercialPorQualidade">
												<html:option value=""/>
												
												<logic:iterate name="colecaoSetorComercialPorQualidade" id="setorComercial" type="SetorComercial" >

													<html:option value="<%="" + setorComercial.getId()%>" >
														<bean:write name="setorComercial" property="localidade.descricao"/> - 		
														<bean:write name="setorComercial" property="descricao"/>
													</html:option>
													
												</logic:iterate>
												
											</logic:present>
											
											<logic:present name="colecaoSetorComercialPorLocalidade">
												<html:option value=""/>
												
												<logic:iterate name="colecaoSetorComercialPorLocalidade" id="setorComercial" type="SetorComercial">
													
													<html:option value="<%="" + setorComercial.getId()%>">
														<bean:write name="setorComercial" property="descricao"/>
													</html:option>
													
												</logic:iterate>
												
											</logic:present>
											
										</html:select>
									</td>
								</tr>
								
								<tr>
									<td><strong>Quadra:</strong></td>
									<td><html:select property="quadra" style="width: 400px;" multiple="mutiple" size="4">
											
											<logic:present name="colecaoQuadraPorLocalidade">
												<html:option value=""/>
												
												<logic:iterate name="colecaoQuadraPorLocalidade" id="quadra" type="Quadra">
													
													<html:option value="<%="" + quadra.getId()%>">
														<bean:write name="quadra" property="numeroQuadra"/>
													</html:option>
													
												</logic:iterate>
												
											</logic:present>
											
										</html:select>
									</td>
								</tr>
								
							</table>

							</td>
						</tr>
						</table>
						
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td align="right">&nbsp;</td>
				</tr>

				<tr>
					<td><strong> <font color="#ff0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#ff0000">*</font></strong>
					Campos obrigatórios</div>
					</td>
				</tr>
				<tr>
					<td><strong> <font color="#ff0000"> <input name="Submit22"
						class="bottonRightCol" value="Desfazer" type="button"
						onclick="window.location.href='/gsan/exibirInserirMensagemContaAction.do?menu=sim';">

					<input name="Submit23" class="bottonRightCol" value="Cancelar"
						type="button"
						onclick="window.location.href='/gsan/telaPrincipal.do'"> </font></strong></td>
					<td align="right"><input name="botao" class="bottonRightCol"
						value="Inserir" onclick="validaForm();" type="button"></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<!-- Fim do Corpo - Roberta Costa --></td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
<script language="JavaScript">
<!-- 
	verificaPreenchimentoLocalidade();
-->
  habilitaMensagem2();
  habilitaMensagem3();
</script>


</html:html>
