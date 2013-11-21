<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.cadastro.cliente.ClienteEndereco" %>
<%@ page import="gcom.cadastro.cliente.ClienteFone" %>
<%@ page import="gcom.gui.GcomAction"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirRegistroAtendimentoActionForm" dynamicJavascript="false" />

<script>

	var bCancel = false; 

    function validateInserirRegistroAtendimentoActionForm(form) {                                                                   
        if (bCancel) 
      return true; 
        else 
       return validateCaracterEspecial(form) && validateInteger(form) && validarRequeridos(form) && validarEmail(form); 
   } 
   
   function validarEmail(form){
   
   if (form.enderecoEmail == undefined || form.enderecoEmail == null) {
   	
   	return true;
   } else {
   	
   	return validateEmail(form);
   }
   
   }

    function caracteresespeciais () { 
     this.ar = new Array("idCliente", "Cliente possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.as = new Array("idUnidadeSolicitante", "Unidade Solicitante possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.at = new Array("idFuncionarioResponsavel", "Funcionário Responsável possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.au = new Array("nomeSolicitante", "Nome Solicitante possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    } 

    function IntegerValidations () { 
     this.ai = new Array("idCliente", "Cliente deve ser numérico(a).", new Function ("varName", " return this[varName];"));
     this.aj = new Array("idUnidadeSolicitante", "Unidade Solicitante deve ser numérico(a).", new Function ("varName", " return this[varName];"));
     this.al = new Array("idFuncionarioResponsavel", "Funcionário Responsável deve ser numérico(a).", new Function ("varName", " return this[varName];"));
    }
    
    function email () {
	this.aa = new Array("enderecoEmail", "E-Mail inválido.", new Function ("varName", " return this[varName];"));
	}
	
	
	function validarRequeridos(form){
	
		var retorno = false;
    	var msgAlert = "";
    	
    	if (dadosSolicitanteInformados()){
    	
    		var clienteObrigatorio = form.indicadorClienteEspecificacao.value;
    		var idCliente = form.idCliente.value;
    		var enderecoInformado = document.getElementById("enderecoInformado");
    		var botaoEndereco = document.getElementById("botaoEndereco");
    			
    		
    		if (clienteObrigatorio == "1" && idCliente.value.length < 1){
    			msgAlert = "Informe Cliente \n";			
    		}
    		else{
    		
    			if (form.idFuncionarioResponsavel.value.length > 0 && 
    				form.idUnidadeSolicitante.value.length < 1){
    				msgAlert = "Informe Unidade Solicitante \n";
    			}
    			
    			if (form.idFuncionarioResponsavel.value.length < 1 && 
    				form.idUnidadeSolicitante.value.length > 0){
    				msgAlert = "Informe Funcionário Responsável \n";
    			}
    		}
    		
    		if (enderecoInformado == null && !botaoEndereco.disabled){
    			msgAlert = msgAlert + "Informe Endereço \n";			
    		}
    		
    		if (msgAlert.length < 1){
    			retorno = true;
    		}
    		else{
    			alert(msgAlert);
    		}
    	}
    	else if (form.clienteObrigatorio.value == "1"){
    		form.idCliente.focus();
    		alert("É necessário informar o Cliente ou a Unidade Solicitante ou o Nome do Solicitante.");

    	}else{
   			retorno = true;
    	}

    	if(retorno == true){
	    	if( (form.enviarEmailSatisfacao != null ) && (form.enviarEmailSatisfacao[0].checked == true)
					&& (form.enderecoEmail.value == "" || form.enderecoEmail == null)){
	    		form.enderecoEmail.focus();
    			retorno = true;
	    		var selecao = confirm("Campo de e-mail para envio da pesquisa de satisfação não preenchido ou inválido. Caso deseje adicioná-lo agora, selecione a opção OK. Caso contrário, selecione Cancel.");
	    		if(selecao == true){
	    			selecionouEmailSim();
	    			retorno = false;
		    	}else{
		    		selecionouEmailNao();
		    	}
				
			}else{
				if((form.enviarEmailSatisfacao != null ) && (form.enviarEmailSatisfacao[0].checked == true)){
					var emailValido = checkMail(''+form.enderecoEmail.value);
					if(emailValido == false){
						form.enderecoEmail.focus();
		    			retorno = true;
			    		var selecao = confirm("Campo de e-mail para envio da pesquisa de satisfação não preenchido ou inválido. Caso deseje adicioná-lo agora, selecione a opção OK. Caso contrário, selecione Cancel.");
			    		if(selecao == true){
			    			selecionouEmailSim();
			    			retorno = false;
				    	}else{
				    		selecionouEmailNao();
				    	}
					}
				}
			}
    	}
		
    	return retorno;
	}
	
	function dadosSolicitanteInformados(){
		
		var retorno = false;
		var form = document.forms[0];
		
		for(indice = 0; indice < form.elements.length; indice++){
			
			if (form.elements[indice].type == "text" && form.elements[indice].value.length > 0 &&
				!form.elements[indice].readOnly) {
				retorno = true;
				break;
			}
		}

		return retorno;	
	}

	
	function limpar(situacao){
	
		var form = document.forms[0];
	
		switch (situacao){
	      case 1:
			   form.nomeCliente.value = "";
			   
			   form.idUnidadeSolicitante.value = "";
	           form.descricaoUnidadeSolicitante.value = "";
			   form.idFuncionarioResponsavel.value = "";
			   form.nomeFuncionarioResponsavel.value = "";
			   
			   var botaoAtualizarCliente = document.getElementById("botaoAtualizarCliente");
			   var enderecoPertenceCliente = document.getElementById("enderecoPertenceCliente");
			   
			   if (!botaoAtualizarCliente.disabled && enderecoPertenceCliente.value.length > 0){
			   	redirecionarSubmit("inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosSolicitanteAction&limparClienteSolicitante=OK");
			   }
			   
			   break;
		  case 2:
			   form.idCliente.value = "";
			   form.nomeCliente.value = "";
			   
			   var botaoAtualizarCliente = document.getElementById("botaoAtualizarCliente");
			   
			   redirecionarSubmit("inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosSolicitanteAction&limparClienteSolicitante=OK");
			   
			   form.idCliente.focus();
			   
			   break;
		  case 3:
		  	   form.idCliente.value = "";
			   form.nomeCliente.value = "";
			   form.descricaoUnidadeSolicitante.value = "";
			   form.idFuncionarioResponsavel.value = "";
			   form.nomeFuncionarioResponsavel.value = "";
			   
			   
			   break;
		  case 4:
			   form.idUnidadeSolicitante.value = "";
			   form.descricaoUnidadeSolicitante.value = "";
			   form.idFuncionarioResponsavel.value = "";
			   form.nomeFuncionarioResponsavel.value = "";
			   
			   redirecionarSubmit("inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosSolicitanteAction&limparUnidadeSolicitante=OK");
			   
			   form.idUnidadeSolicitante.focus();
			   
			   break;
		  case 5:
		       form.idCliente.value = "";
			   form.nomeCliente.value = "";
			   form.nomeFuncionarioResponsavel.value = "";
			   
			   break;
		  case 6:
			   form.idFuncionarioResponsavel.value = "";
			   form.nomeFuncionarioResponsavel.value = "";
			   
			   form.idFuncionarioResponsavel.focus();
			   
			   break;
		  default:
	          break;
		}
	}
	
	function habilitaDadosSolicitante(nomeSolicitante){
	
		var form = document.forms[0];
		
		form.idCliente.value = "";
		form.nomeCliente.value = "";
		form.idUnidadeSolicitante.value = "";
		form.descricaoUnidadeSolicitante.value = "";
		form.idFuncionarioResponsavel.value = "";
		form.nomeFuncionarioResponsavel.value = "";
		
		if (nomeSolicitante.value.length > 0 && !form.idCliente.readOnly){
			redirecionarSubmit("inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosSolicitanteAction&informadoNomeSolicitante=OK");
		}
	}
	
	function desabilitaDadosSolicitante(nomeSolicitante){
	
		var form = document.forms[0];
		
		form.idCliente.value = "";
		form.nomeCliente.value = "";
		form.idUnidadeSolicitante.value = "";
		form.descricaoUnidadeSolicitante.value = "";
		form.idFuncionarioResponsavel.value = "";
		form.nomeFuncionarioResponsavel.value = "";
		
		if (nomeSolicitante.value.length < 1 && form.idCliente.readOnly){
			redirecionarSubmit("inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosSolicitanteAction&limparNomeSolicitante=OK");
		}
	}
	
	
	//Recebe os dados do(s) popup(s)

  function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'cliente') {
      form.idCliente.value = codigoRegistro;
      form.nomeCliente.value = descricaoRegistro;
      form.nomeCliente.style.color = "#000000";
      
      redirecionarSubmit("inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosSolicitanteAction&pesquisarCliente=OK");
    }
    
    if (tipoConsulta == 'unidadeOrganizacional') {
      	form.idUnidadeSolicitante.value = codigoRegistro;
      	form.descricaoUnidadeSolicitante.value = descricaoRegistro;
      	form.descricaoUnidadeSolicitante.style.color = "#000000";
      
      	redirecionarSubmit("inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosSolicitanteAction&pesquisarUnidade=OK");
    }
    
    if (tipoConsulta == 'funcionario') {
      	form.idFuncionarioResponsavel.value = codigoRegistro;
      	form.nomeFuncionarioResponsavel.value = descricaoRegistro;
      	form.nomeFuncionarioResponsavel.style.color = "#000000";
      
      	redirecionarSubmit("inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosSolicitanteAction&pesquisarFuncionario=OK");
    }
  }
  
  
  function removerFone(objetoRemocao){
  
  	redirecionarSubmit("inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosSolicitanteAction&removerFone=" + objetoRemocao);
  }
  
  function removerEndereco(){
  
  	redirecionarSubmit("inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosSolicitanteAction&removerEndereco=OK");
  }
  
  
  function controladorHabilitacaoCampos(){
  
  	var form = document.forms[0];
  	
  	if (form.idCliente.value.length > 0){
  		form.idUnidadeSolicitante.readOnly = true;
	    form.idFuncionarioResponsavel.readOnly = true;
		form.nomeSolicitante.readOnly = true;
  	}
  	else if (form.idUnidadeSolicitante.value.length > 0 
  			 || form.idFuncionarioResponsavel.value.length > 0){
  		form.idCliente.readOnly = true;
  		form.nomeSolicitante.readOnly = true;
  	}
  	else if (form.nomeSolicitante.value.length > 0) {
  		form.idCliente.readOnly = true;
  		form.idUnidadeSolicitante.readOnly = true;
	    form.idFuncionarioResponsavel.readOnly = true;
  	}
 }

  function selecionouEmailSatisfacaoSim(){
	  var form = document.forms[0];
	  form.enderecoEmail.disabled = false;
	}

  function selecionouEmailSatisfacaoNao(){
	  var form = document.forms[0];
	  form.enderecoEmail.disabled = true;
	  form.enderecoEmail.value = "";
	}  

  function checkMail(mail){
	  var parte1 = mail.indexOf("@");
      var parte2 = mail.lastIndexOf(".");
	  var parte3 = mail.length;
	  if (!(parte1 >= 3 && parte2 >= parte1 && parte3 >= 9)) {
		return false;
	  }

	  return true;
	}

  function selecionouEmailSim(){
		 var form = document.forms[0];
		 form.enderecoEmail.focus();	
	}

	function selecionouEmailNao(){
		var form = document.forms[0];
		form.enviarEmailSatisfacao[0].checked = false;
		form.enviarEmailSatisfacao[1].checked = true;
		form.enderecoEmail.disabled = true;
	    form.enderecoEmail.value = ""; 
		form.submit();
	
	}
</script>

</head>

<body leftmargin="0" topmargin="0" onload="setarFoco('${requestScope.nomeCampo}');controladorHabilitacaoCampos();">

<div id="formDiv">
<html:form action="/inserirRegistroAtendimentoWizardAction" method="post">

<INPUT TYPE="hidden" ID="enderecoPertenceCliente" value="${sessionScope.enderecoPertenceCliente}">

<html:hidden property="clienteObrigatorio"/>

<html:hidden property="idImovelAssociacaoCliente"/>

<html:hidden property="idImovel"/>
	
<html:hidden property="idMunicipio"/>
<html:hidden property="cdBairro"/>
<html:hidden property="idBairroArea"/>
<html:hidden property="tipoSolicitacao"/>
	
<html:hidden property="indicadorClienteEspecificacao"/>

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_ra.jsp?numeroPagina=3"/>


<logic:notPresent scope="session" name="origemGIS">
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp" %>
</logic:notPresent>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <logic:notPresent scope="session" name="origemGIS">
  		<td width="140" valign="top" class="leftcoltext">
</logic:notPresent>
<logic:present scope="session" name="origemGIS">
	<td width="140" valign="top" class="leftcoltext" style="display:none;">
</logic:present>
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

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Inserir Registro de Atendimento</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      <tr>
      	<td colspan="2" HEIGHT="40" align="center">
      		<span style="font-size: 18px;font-weight: bold;">
      		Nº Protocolo: ${sessionScope.protocoloAtendimento}</span></td>
      </tr>
      
      <tr>
      	<td colspan="2">Para inserir o registro de atendimento, informe os dados do solicitante:</td>
		<logic:present scope="application" name="urlHelp">
								<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}atendimentoRegistroInserirAbaSolicitante', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
		</logic:present>
		<logic:notPresent scope="application" name="urlHelp">
								<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
		</logic:notPresent>
      </tr>
	  
	  <tr>
		<td colspan="3">
		
				<table width="100%" align="center" bgcolor="#90c7fc" border="0">
				<tr>
					<td align="center"><strong>Dados do Solicitante</strong></td>
				</tr>
				<tr bgcolor="#cbe5fe">
					<td width="100%" align="center">
						
						<table width="100%" border="0">
						<tr>
					        <td><strong>Cliente:</strong></td>
					        <td>
					        
					        	<logic:present name="desabilitarDadosSolicitanteCliente">
					        		<html:text property="idCliente" size="10" maxlength="9" tabindex="1" readonly="true"/>
									
									<img width="23" height="21" border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Cliente" />
								</logic:present>
								
								<logic:notPresent name="desabilitarDadosSolicitanteCliente">
					        		<html:text property="idCliente" size="10" maxlength="9" tabindex="1" onkeypress="limpar(1);validaEnterComMensagem(event, 'inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosSolicitanteAction&pesquisarCliente=OK', 'idCliente', 'Cliente');return isCampoNumerico(event);"/>
									
									<a href="javascript:abrirPopup('exibirPesquisarClienteAction.do', 475, 800);">
									<img width="23" height="21" border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Cliente" /></a>
								</logic:notPresent>
					        	
					        	
								<logic:present name="corCliente">
						
									<logic:equal name="corCliente" value="exception">
										<html:text property="nomeCliente" size="42" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
									</logic:equal>
						
									<logic:notEqual name="corCliente" value="exception">
										<html:text property="nomeCliente" size="42" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
									</logic:notEqual>
						
								</logic:present>
						
								<logic:notPresent name="corCliente">
						
									<logic:empty name="InserirRegistroAtendimentoActionForm" property="idCliente">
										<html:text property="nomeCliente" size="42" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
									</logic:empty>
									<logic:notEmpty name="InserirRegistroAtendimentoActionForm" property="idCliente">
										<html:text property="nomeCliente" size="42" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
									</logic:notEmpty>
										
								</logic:notPresent>
								
								<logic:present name="desabilitarDadosSolicitanteCliente">
					        			<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" />
								</logic:present>
								
								<logic:notPresent name="desabilitarDadosSolicitanteCliente">
					        		<a	href="javascript:limpar(2);">
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" title="Apagar" /> </a>
								</logic:notPresent>
								
							</td>
					      </tr>
					     
					      <tr>
					        <td><strong>Unidade Solicitante:</strong></td>
					        <td>
					        
					        	<logic:present name="desabilitarDadosSolicitanteUnidade">
					        		<html:text property="idUnidadeSolicitante" size="10" maxlength="9" tabindex="2" readonly="true"/>
								
									<img width="23" height="21" border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Unidade Solicitante" />
								</logic:present>
								
								<logic:notPresent name="desabilitarDadosSolicitanteUnidade">
					        		<html:text property="idUnidadeSolicitante" size="10" maxlength="9" tabindex="2" onkeypress="limpar(3);validaEnterComMensagem(event, 'inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosSolicitanteAction&pesquisarUnidade=OK', 'idUnidadeSolicitante', 'Unidade Solicitante');return isCampoNumerico(event);"/>
								
									<a href="javascript:abrirPopup('exibirPesquisarUnidadeOrganizacionalAction.do', 410, 790);">
									<img width="23" height="21" border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Unidade Solicitante" /></a>
								</logic:notPresent>
								
								
								<logic:present name="corUnidadeSolicitante">
						
									<logic:equal name="corUnidadeSolicitante" value="exception">
										<html:text property="descricaoUnidadeSolicitante" size="42" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
									</logic:equal>
						
									<logic:notEqual name="corUnidadeSolicitante" value="exception">
										<html:text property="descricaoUnidadeSolicitante" size="42" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
									</logic:notEqual>
						
								</logic:present>
						
								<logic:notPresent name="corUnidadeSolicitante">
						
									<logic:empty name="InserirRegistroAtendimentoActionForm" property="idUnidadeSolicitante">
										<html:text property="descricaoUnidadeSolicitante" size="42" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
									</logic:empty>
									<logic:notEmpty name="InserirRegistroAtendimentoActionForm" property="idUnidadeSolicitante">
										<html:text property="descricaoUnidadeSolicitante" size="42" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
									</logic:notEmpty>
									
								</logic:notPresent>
										
								<logic:present name="desabilitarDadosSolicitanteUnidade">
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" />
								</logic:present>
								
								<logic:notPresent name="desabilitarDadosSolicitanteUnidade">
									<a	href="javascript:limpar(4);">
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" /></a>
								</logic:notPresent>
										
							</td>
					      </tr>
					      
					      <tr>
					        <td><strong>Funcionário Responsável:</strong></td>
					        <td>
					        
					        	<logic:present name="desabilitarDadosSolicitanteFuncionario">
					        		<html:text property="idFuncionarioResponsavel" size="10" maxlength="9" tabindex="3" readonly="true"/>
								
									<img width="23" height="21" border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Funcionário" />
								</logic:present>
								
								<logic:notPresent name="desabilitarDadosSolicitanteFuncionario">
					        		<html:text property="idFuncionarioResponsavel" size="10" maxlength="9" tabindex="3" onkeypress="limpar(5);validaEnterDependencia(event, 'inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosSolicitanteAction&pesquisarFuncionario=OK', this, document.forms[0].idUnidadeSolicitante.value, 'Unidade Solicitante');return isCampoNumerico(event);"/>
								
									<a href="javascript:javascript:abrirPopupDependencia('exibirFuncionarioPesquisar.do',document.forms[0].idUnidadeSolicitante.value,'Unidade Solicitante', 330, 600);">
									<img width="23" height="21" border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Funcionário" /></a>
								</logic:notPresent>	
								
								<logic:present name="corFuncionario">
						
									<logic:equal name="corFuncionario" value="exception">
										<html:text property="nomeFuncionarioResponsavel" size="42" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
									</logic:equal>
						
									<logic:notEqual name="corFuncionario" value="exception">
										<html:text property="nomeFuncionarioResponsavel" size="42" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
									</logic:notEqual>
						
								</logic:present>
						
								<logic:notPresent name="corFuncionario">
						
									<logic:empty name="InserirRegistroAtendimentoActionForm" property="idFuncionarioResponsavel">
										<html:text property="nomeFuncionarioResponsavel" size="42" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
									</logic:empty>
									<logic:notEmpty name="InserirRegistroAtendimentoActionForm" property="idFuncionarioResponsavel">
										<html:text property="nomeFuncionarioResponsavel" size="42" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
									</logic:notEmpty>
									
								</logic:notPresent>
										
								<logic:present name="desabilitarDadosSolicitanteFuncionario">
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" /> 
								</logic:present>
								
								<logic:notPresent name="desabilitarDadosSolicitanteFuncionario">
										<a	href="javascript:limpar(6);">
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" /></a> 
								</logic:notPresent>
										
							</td>
					      </tr>
					      
					      <tr>
					        <td><strong>Nome Solicitante:</strong></td>
					        <td>
					        
					        	<logic:present name="desabilitarDadosSolicitanteNome">
					        		<html:text property="nomeSolicitante" size="45" tabindex="4" readonly="true"/>
								</logic:present>
								
								<logic:notPresent name="desabilitarDadosSolicitanteNome">
					        		<html:text property="nomeSolicitante" size="45" tabindex="4" onblur="habilitaDadosSolicitante(this);" onkeyup="desabilitaDadosSolicitante(this);"/>
								</logic:notPresent>
									
							</td>
					      </tr>
					      <c:if test="${habilitarCampoSatisfacaoEmail != null && habilitarCampoSatisfacaoEmail == true}">
						      <tr>
						      	<td>	
						      		<strong>Enviar email para pesquisa de satisfação:</strong>
						      	</td>
						      	<td>	
						      		<input onclick="selecionouEmailSatisfacaoSim();" type="radio" name="enviarEmailSatisfacao" <c:if test="${InserirRegistroAtendimentoActionForm.enviarEmailSatisfacao == 1 || InserirRegistroAtendimentoActionForm.enviarEmailSatisfacao == null || InserirRegistroAtendimentoActionForm.enviarEmailSatisfacao == ''}">checked="checked"</c:if> value="1" /><strong> Sim </strong>
						      		<input onclick="selecionouEmailSatisfacaoNao();" type="radio" name="enviarEmailSatisfacao" <c:if test="${InserirRegistroAtendimentoActionForm.enviarEmailSatisfacao == 2 }">checked="checked"</c:if> value="2" /><strong> Não </strong>
						      	</td>
						      </tr>
						       <tr>
						      	<td><strong>Endereço de email:</strong></td>
						        <td>
					        		<input type="text" value="${InserirRegistroAtendimentoActionForm.enderecoEmail }" name="enderecoEmail" size="40" maxlength="40" <c:if test="${InserirRegistroAtendimentoActionForm.enviarEmailSatisfacao == 2 }">disabled="disabled"</c:if>  />
								</td>
						      </tr>
					      </c:if>
					      <tr>
					         <td colspan="3">
					
								<table width="100%" border="0">
						      	<tr>
						      		<td><strong>Endereço do Solicitante:</strong></td>
						      		<td align="right">
						      		
						      			<logic:present name="colecaoEnderecosAbaSolicitante">
							 
											<logic:empty name="colecaoEnderecosAbaSolicitante">
												<input type="button" class="bottonRightCol" value="Atualizar Cliente" tabindex="3" id="botaoAtualizarCliente" disabled style="display: none;"> 
												<input type="button" class="bottonRightCol" value="Adicionar" tabindex="4" id="botaoEndereco" onclick="abrirPopupComSubmit('inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosSolicitanteAction&tipoPesquisaEndereco=registroAtendimento&operacao=2', 570, 700, 'Endereco');">
											</logic:empty>
											<logic:notEmpty name="colecaoEnderecosAbaSolicitante">
												<input type="button" class="bottonRightCol" value="Atualizar Cliente" tabindex="3" id="botaoAtualizarCliente" style="display: none;">
												<input type="button" class="bottonRightCol" value="Adicionar" tabindex="4" id="botaoEndereco" disabled>
											</logic:notEmpty>
									 
									 	</logic:present>
							
									 	<logic:notPresent name="colecaoEnderecosAbaSolicitante">
											<logic:present name="habilitarAlteracaoEnderecoAbaSolicitante">
												<logic:equal name="habilitarAlteracaoEnderecoAbaSolicitante" value="SIM">
													 <input type="button" class="bottonRightCol" value="Atualizar Cliente" tabindex="3" id="botaoAtualizarCliente" disabled style="display: none;">
													<input type="button" class="bottonRightCol" value="Adicionar" tabindex="4"  id="botaoEndereco" onclick="abrirPopupComSubmit('inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosSolicitanteAction&tipoPesquisaEndereco=registroAtendimento&operacao=2', 570, 700, 'Endereco');">
											 	</logic:equal>
											 	<logic:notEqual name="habilitarAlteracaoEnderecoAbaSolicitante" value="SIM">
											 		<input type="button" class="bottonRightCol" value="Atualizar Cliente" tabindex="3" id="botaoAtualizarCliente" style="display: none;">
											 		<input type="button" class="bottonRightCol" value="Adicionar" tabindex="4" id="botaoEndereco" disabled>
											 	</logic:notEqual>
											</logic:present>
										
											<logic:notPresent name="habilitarAlteracaoEnderecoAbaSolicitante">
												<input type="button" class="bottonRightCol" value="Atualizar Cliente" tabindex="3" id="botaoAtualizarCliente" disabled style="display: none;"> 
												<input type="button" class="bottonRightCol" value="Adicionar" tabindex="4"  id="botaoEndereco" onclick="abrirPopupComSubmit('inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosSolicitanteAction&tipoPesquisaEndereco=registroAtendimento&operacao=2', 570, 700, 'Endereco');">
											</logic:notPresent>
									 	
									 	</logic:notPresent>
						      		
						      		</td>
						      	</tr>
							 	</table>
							 </td>
					     </tr>
					     <tr>
					         <td colspan="3" height="50" valign="top">
								<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td>
										<table width="100%" border="0" bgcolor="#99CCFF">
										<tr bgcolor="#99CCFF" height="18">
											<td width="10%" align="center"><strong>Remover</strong></td>
											<td width="20%" align="center"><strong>End. Correspondência</strong></td>
											<td align="center"><strong>Endereço do Solicitante</strong></td>
										</tr>
										</table>
									</td>
								</tr>
					
								<logic:present name="colecaoEnderecosAbaSolicitante">
								
								<INPUT TYPE="hidden" ID="enderecoInformado">
					
								<tr>
									<td>
										<table width="100%" align="center" bgcolor="#99CCFF">
											<!--corpo da segunda tabela-->
					
											<% String cor = "#cbe5fe";%>
					
											<logic:iterate name="colecaoEnderecosAbaSolicitante" id="endereco" type="ClienteEndereco">
											
												<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
													cor = "#FFFFFF";%>
													<tr bgcolor="#FFFFFF" height="18">	
												<%} else{	
													cor = "#cbe5fe";%>
													<tr bgcolor="#cbe5fe" height="18">		
												<%}%>
											
													<td width="10%" align="center">
														
														<logic:present name="habilitarAlteracaoEnderecoSolicitante">
															<logic:equal name="habilitarAlteracaoEnderecoSolicitante" value="SIM">
																<a href="javascript:if(confirm('Confirma remoção?')){removerEndereco();}" alt="Remover"><img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0"></a>
											 				</logic:equal>
											 				<logic:notEqual name="habilitarAlteracaoEnderecoSolicitante" value="SIM">
											 					<img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0">
											 				</logic:notEqual>
														</logic:present>
										
														<logic:notPresent name="habilitarAlteracaoEnderecoSolicitante">
															<a href="javascript:if(confirm('Confirma remoção?')){removerEndereco();}" alt="Remover"><img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0"></a>
														</logic:notPresent>
														
													</td>
													
													<td width="20%" align="center">
														<html:radio property="clienteEnderecoSelected" value="<%="" + GcomAction.obterTimestampIdObjeto(endereco) %>" disabled="true"/>
													</td>
													
													<td>
														<logic:equal name="habilitarAlteracaoEnderecoAbaSolicitante" value="SIM">
															<a href="javascript:abrirPopup('exibirInserirEnderecoAction.do?exibirEndereco=OK&tipoPesquisaEndereco=registroAtendimento&operacao=1&mostrarPerimetro=sim', 570, 700)"><bean:write name="endereco" property="enderecoFormatadoAbreviado"/></a>
														</logic:equal>
														
														<logic:notEqual name="habilitarAlteracaoEnderecoAbaSolicitante" value="SIM">
															<bean:write name="endereco" property="enderecoFormatadoAbreviado"/>
														</logic:notEqual>
													</td>
												</tr>
											</logic:iterate>
					
										</table>
								  	</td>
								</tr>
								
								</logic:present>
					
								</table>
						   </td>
					   	</tr>
					   	
					   	<logic:present name="colecaoEnderecosAbaSolicitante">
					   	
						   	<tr> 
								<td><strong>Ponto de Referência:</strong></td>
								<td colspan="2">
									<html:text property="pontoReferenciaSolicitante" size="45" maxlength="60" />
								</td>
							</tr>
						
						</logic:present>
						
					   	<tr>
					         <td colspan="3">
					
								<table width="100%" border="0">
						      	<tr>
						      		<td><strong>Fones do Solicitante:</strong></td>
						      		<td align="right">
						      		
						      			<logic:present name="colecaoFonesSolicitante">
							              <input type="button" class="bottonRightCol" value="Adicionar" tabindex="5" id="botaoFone" onclick="abrirPopupComSubmit('inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosSolicitanteAction&telaRetorno=inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosSolicitanteAction', 310, 600, 'Fone');">
									 	</logic:present>
							
									 	<logic:notPresent name="colecaoFonesSolicitante">
										
											<logic:present name="habilitarAlteracaoEnderecoSolicitante">
												<logic:equal name="habilitarAlteracaoEnderecoSolicitante" value="SIM">
													<input type="button" class="bottonRightCol" value="Adicionar" tabindex="5"  id="botaoFone" onclick="abrirPopupComSubmit('inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosSolicitanteAction&telaRetorno=inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosSolicitanteAction', 310, 600, 'Fone');">
											 	</logic:equal>
											 	<logic:notEqual name="habilitarAlteracaoEnderecoSolicitante" value="SIM">
											 		<input type="button" class="bottonRightCol" value="Adicionar" tabindex="5" id="botaoFone" disabled>
											 	</logic:notEqual>
											</logic:present>
										
											<logic:notPresent name="habilitarAlteracaoEnderecoSolicitante">
												<input type="button" class="bottonRightCol" value="Adicionar" tabindex="5"  id="botaoFone" onclick="abrirPopupComSubmit('inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosSolicitanteAction&telaRetorno=inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosSolicitanteAction', 310, 600, 'Fone');">
											</logic:notPresent>
									 	
									 	</logic:notPresent>
						      		
						      		</td>
						      	</tr>
							 	</table>
							 </td>
					     </tr>
					     <tr>
					         <td colspan="3" height="50" valign="top">
								<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td>
										<table width="100%" border="0" bgcolor="#99CCFF">
										<tr bgcolor="#99CCFF" height="18">
											<td width="10%" align="center"><strong>Remover</strong></td>
											<td width="20%" align="center"><strong>Principal</strong></td>
											<td width="35%" align="center"><strong>Telefone</strong></td>
											<td width="35%" align="center"><strong>Tipo</strong></td>
										</tr>
										</table>
									</td>
								</tr>
					
								<logic:present name="colecaoFonesAbaSolicitante">
					
								<tr>
									<td>
										<table width="100%" align="center" bgcolor="#99CCFF">
											<!--corpo da segunda tabela-->
					
											<% String cor1 = "#cbe5fe";%>
					
											<logic:iterate name="colecaoFonesAbaSolicitante" id="fone" type="ClienteFone">
											
												<%	if (cor1.equalsIgnoreCase("#cbe5fe")){	
													cor1 = "#FFFFFF";%>
													<tr bgcolor="#FFFFFF" height="18">	
												<%} else{	
													cor1 = "#cbe5fe";%>
													<tr bgcolor="#cbe5fe" height="18">		
												<%}%>
											
													<td width="10%" align="center">
														
														<logic:present name="habilitarAlteracaoEnderecoSolicitante">
															<logic:equal name="habilitarAlteracaoEnderecoSolicitante" value="SIM">
																<a href="javascript:if(confirm('Confirma remoção?')){removerFone('<%="" + GcomAction.obterTimestampIdObjeto(fone) %>');}" alt="Remover"><img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0"></a>
											 				</logic:equal>
											 				<logic:notEqual name="habilitarAlteracaoEnderecoSolicitante" value="SIM">
											 					<img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0">
											 				</logic:notEqual>
														</logic:present>
										
														<logic:notPresent name="habilitarAlteracaoEnderecoSolicitante">
															<a href="javascript:if(confirm('Confirma remoção?')){removerFone('<%="" + GcomAction.obterTimestampIdObjeto(fone) %>');}" alt="Remover"><img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0"></a>
														</logic:notPresent>
														
													</td>
													
													<td width="20%" align="center">
														
														<logic:equal name="fone" property="indicadorTelefonePadrao" value="1">
															<input type="radio" name="clienteFoneSelected" value="<%="" + GcomAction.obterTimestampIdObjeto(fone) %>" checked/>
														</logic:equal>
														
														<logic:notEqual name="fone" property="indicadorTelefonePadrao" value="1">
															<input type="radio" name="clienteFoneSelected" value="<%="" + GcomAction.obterTimestampIdObjeto(fone) %>"/>
														</logic:notEqual>
														
													</td>
													
													<td width="35%" align="center">
														<bean:write name="fone" property="dddTelefone"/>
													</td>
													
													<td width="35%" align="center">
														<bean:write name="fone" property="foneTipo.descricao"/>
													</td>
												</tr>
											</logic:iterate>
					
										</table>
								  	</td>
								</tr>
					
								</logic:present>
					
								</table>
						   </td>
					   	</tr>
						</table>
						
					</td>
				</tr>
				</table>
		</td>
	  </tr>




      <tr>
      	<td colspan="2" height="10"></td>
      </tr>
      <tr>
        <td colspan="3">
			<div align="right">
				<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_ra.jsp?numeroPagina=3"/>
			</div>
		</td>
      </tr>
      </table>
      <p>&nbsp;</p>
	</td>
  </tr>
</table>

<logic:notPresent scope="session" name="origemGIS">
	<%@ include file="/jsp/util/rodape.jsp"%>
</logic:notPresent>

</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/inserirRegistroAtendimentoWizardAction.do?concluir=true&action=inserirRegistroAtendimentoDadosSolicitanteAction'); }
</script>
<logic:present scope="session" name="origemGIS">
	<script>
		document.getElementById("Layer1").style.top = "5";
	</script>
</logic:present>

</body>
</html:html>

