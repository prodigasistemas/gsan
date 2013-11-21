<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.cadastro.cliente.ClienteEndereco" %>
<%@ page import="gcom.cadastro.cliente.ClienteFone" %>
<%@ page import="gcom.util.ConstantesSistema" %>
<%@ page import="gcom.gui.GcomAction"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AdicionarSolicitanteRegistroAtendimentoActionForm" dynamicJavascript="false" />

<script>

	var bCancel = false; 

    function validateAdicionarSolicitanteRegistroAtendimentoActionForm(form) {                                                                   
        if (bCancel) 
      return true; 
        else 
       return validateCaracterEspecial(form) && validateInteger(form) && validarRequeridos(form); 
   	} 

    function caracteresespeciais () { 
     this.aa = new Array("idCliente", "Cliente possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("idUnidadeSolicitanteInformar", "Unidade Solicitante possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("idFuncionarioResponsavel", "Funcionário Responsável possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("nomeSolicitanteInformar", "Nome Solicitante possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    } 

    function IntegerValidations () { 
     this.aa = new Array("idCliente", "Cliente deve ser numérico(a).", new Function ("varName", " return this[varName];"));
     this.ab = new Array("idUnidadeSolicitanteInformar", "Unidade Solicitante deve ser numérico(a).", new Function ("varName", " return this[varName];"));
     this.ac = new Array("idFuncionarioResponsavel", "Funcionário Responsável deve ser numérico(a).", new Function ("varName", " return this[varName];"));
    }
	
	
	function validarRequeridos(form){
	
		var retorno = false;
    	var msgAlert = "";
    	 
    	if (dadosSolicitanteInformados()){
    	
    		var clienteObrigatorio = form.indicadorClienteEspecificacao.value;
    		var idCliente = form.idCliente.value;
    		var enderecoInformado = document.getElementById("enderecoInformado");
    		var botaoEndereco = document.getElementById("botaoEndereco");
    			
    		
    		if (clienteObrigatorio == "1" && idCliente.length < 1){
    			msgAlert = "Informe Cliente \n";			
    		}
    		else{
    		
    			if (form.idFuncionarioResponsavel.value.length > 0 && 
    				form.idUnidadeSolicitanteInformar.value.length < 1){
    				msgAlert = "Informe Unidade Solicitante \n";
    			}
    			
    			if (form.idFuncionarioResponsavel.value.length < 1 && 
    				form.idUnidadeSolicitanteInformar.value.length > 0){
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
    	else{
    		form.idCliente.focus();
    		alert("É necessário informar o Cliente ou a Unidade Solicitante ou o Nome do Solicitante.");
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

	function extendeTabela(tabela,display){
  		var form = document.forms[0];

  		if(display){
    		eval('layerHide'+tabela).style.display = 'none';
    		eval('layerShow'+tabela).style.display = 'block';
  		}else{
   			eval('layerHide'+tabela).style.display = 'block';
    		eval('layerShow'+tabela).style.display = 'none';
  		}
 	}
 	
 	
 	function limpar(situacao){
	
		var form = document.forms[0];
	
		switch (situacao){
	      case 1:
			   form.nomeCliente.value = "";
			   
			   form.idUnidadeSolicitanteInformar.value = "";
	           form.descricaoUnidadeSolicitanteInformar.value = "";
			   form.idFuncionarioResponsavel.value = "";
			   form.descricaoFuncionarioResponsavel.value = "";
			   
			   var botaoAtualizarCliente = document.getElementById("botaoAtualizarCliente");
			   
			  
			   if (!botaoAtualizarCliente.disabled){
			     if(form.idCliente.value == ''){
			   	  redirecionarSubmit("exibirAdicionarSolicitanteRegistroAtendimentoAction.do?limparClienteSolicitante=OK");
			   	}
			   }
			   
			   break;
		  case 2:
			   form.idCliente.value = "";
			   form.nomeCliente.value = "";
			   
			   var botaoAtualizarCliente = document.getElementById("botaoAtualizarCliente");
			   
			   redirecionarSubmit("exibirAdicionarSolicitanteRegistroAtendimentoAction.do?limparClienteSolicitante=OK");
			   
			   form.idCliente.focus();
			   
			   break;
		  case 3:
		  	   form.idCliente.value = "";
			   form.nomeCliente.value = "";
			   form.descricaoUnidadeSolicitanteInformar.value = "";
			   form.idFuncionarioResponsavel.value = "";
			   form.descricaoFuncionarioResponsavel.value = "";
			   
			   
			   break;
		  case 4:
			   form.idUnidadeSolicitanteInformar.value = "";
			   form.descricaoUnidadeSolicitanteInformar.value = "";
			   form.idFuncionarioResponsavel.value = "";
			   form.descricaoFuncionarioResponsavel.value = "";
			   
			   redirecionarSubmit("exibirAdicionarSolicitanteRegistroAtendimentoAction.do?limparUnidadeSolicitante=OK");
			   
			   form.idUnidadeSolicitanteInformar.focus();
			   
			   break;
		  case 5:
		       form.idCliente.value = "";
			   form.nomeCliente.value = "";
			   form.descricaoFuncionarioResponsavel.value = "";
			   
			   break;
		  case 6:
			   form.idFuncionarioResponsavel.value = "";
			   form.descricaoFuncionarioResponsavel.value = "";
			   
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
		form.descricaoFuncionarioResponsavel.value = "";
		
		if (nomeSolicitante.value.length > 0 && !form.idCliente.readOnly){
		    redirecionarSubmit("exibirAdicionarSolicitanteRegistroAtendimentoAction.do?informadoNomeSolicitante=OK");
		}
	}
	
	function desabilitaDadosSolicitante(nomeSolicitante){
	
		var form = document.forms[0];
		
		form.idCliente.value = "";
		form.nomeCliente.value = "";
		form.idUnidadeSolicitante.value = "";
		form.descricaoUnidadeSolicitante.value = "";
		form.idFuncionarioResponsavel.value = "";
		form.descricaoFuncionarioResponsavel.value = "";
		
		if (nomeSolicitante.value.length < 1 && form.idCliente.readOnly){
			redirecionarSubmit("exibirAdicionarSolicitanteRegistroAtendimentoAction.do?limparNomeSolicitante=OK");
		}
	}
  
  	//Remover Endereço
	function remover(){
		redirecionarSubmit('exibirAdicionarSolicitanteRegistroAtendimentoAction.do?removerEndereco=OK');
	}
	
	//Remover Fone
	function removerFone(objetoRemocao){
  		redirecionarSubmit('exibirAdicionarSolicitanteRegistroAtendimentoAction.do?removerFone=' + objetoRemocao);
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

<logic:present name="iniciarFuncionalidade">
	<body leftmargin="0" topmargin="0" onload="chamarReload('${requestScope.iniciarFuncionalidade}'); window.close();">
</logic:present>

<logic:notPresent name="iniciarFuncionalidade">
	<body leftmargin="0" topmargin="0" onload="window.focus();resizePageSemLink(710, 560);setarFoco('${requestScope.nomeCampo}');">
</logic:notPresent>



<html:form action="/adicionarSolicitanteRegistroAtendimentoAction" method="post">
	
<html:hidden property="indicadorClienteEspecificacao"/>

<table width="672" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="672" valign="top" class="centercoltext">
    <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
          <td class="parabg">
          <logic:present name="objetoColecao">
           <logic:equal name="objetoColecao" value="SIM">
            Adicionar Novo Solicitante ao Registro de Atendimento
           </logic:equal>
           <logic:notEqual name="objetoColecao" value="SIM">
            Atualizar Solicitante do RA
           </logic:notEqual>
          </logic:present>
          <logic:notPresent name="objetoColecao">
           Adicionar Novo Solicitante ao Registro de Atendimento
          </logic:notPresent>
          </td>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
        </tr>
      </table>
      <table width="100%">
      	<tr>
      	    <td width="90%" colspan="2">&nbsp;</td>
      	    <logic:equal name="objetoColecao" value="SIM">
      			<logic:present scope="application" name="urlHelp">
					<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}atendimentoRegistroSolicitanteAdicionar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
		 		</logic:present>
				<logic:notPresent scope="application" name="urlHelp">
					<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
		   		</logic:notPresent>
		    </logic:equal>
		    <logic:notEqual name="objetoColecao" value="SIM">
		    	<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}atendimentoRegistroSolicitanteAtualizar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
				</logic:present>
				<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
				</logic:notPresent>
		    </logic:notEqual>
		    <logic:notPresent name="objetoColecao">
		    	<logic:present scope="application" name="urlHelp">
					<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}atendimentoRegistroSolicitanteAdicionar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
		 		</logic:present>
				<logic:notPresent scope="application" name="urlHelp">
					<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
		   		</logic:notPresent>
		    </logic:notPresent>
		</tr>			 
      </table>
      <table width="100%" border="0">
        <tr> 	
          <td colspan="4">
				<div id="layerHideRegistroAtendimento" style="display:block">
				<table width="100%" align="center" bgcolor="#90c7fc" border="0">
				<tr>
					<td align="center"><a href="javascript:extendeTabela('RegistroAtendimento',true);"/>
						<strong>Dados Gerais do Registro de Atendimento</strong></a></td>
				</tr>
				</table>
				</div>
				<div id="layerShowRegistroAtendimento" style="display:none">
				<table width="100%" align="center" bgcolor="#90c7fc" border="0">
				<tr>
					<td align="center"><a href="javascript:extendeTabela('RegistroAtendimento',false);"/>
						<strong>Dados Gerais do Registro de Atendimento</strong></a></td>
				</tr>
				<tr bgcolor="#cbe5fe">
					<td width="100%" align="center">
			
						<table width="100%" border="0">
						<tr> 
							<td width="145"><strong>Número:</strong></td>
				          	<td>
								<html:text property="idRA" size="15" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
							</td>
						</tr>
						<tr> 
							<td><strong>Tipo Solicitação:</strong></td>
							<td>
								<html:text property="idSolicitacaoTipo" size="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>&nbsp;
          						<html:text property="descricaoSolicitacaoTipo" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
							</td>
						</tr>
						<tr> 
							<td><strong>Especificação:</strong></td>
							<td>
								<html:text property="idEspecificacao" size="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>&nbsp;
          						<html:text property="descricaoEspecificacao" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
							</td>
						</tr>
						<tr> 
							<td colspan="2">
							
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr> 
									<td width="148"><strong>Data Atendimento</strong></td>
									<td width="105">
										<html:text property="dataAtendimento" size="11" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
									</td>
									<td width="148"><strong>Hora Atendimento</strong></td>
									<td>
										<html:text property="horaAtendimento" size="11" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
									</td>
								</tr>	
								</table>
								
							</td>
						</tr>
						<tr> 
							<td><strong>Meio Solicitação:</strong></td>
							<td>
								<html:text property="idMeioSolicitacao" size="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>&nbsp;
          						<html:text property="descricaoMeioSolicitacao" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
							</td>
						</tr>
						<tr> 
							<td><strong>Unidade Atendimento:</strong></td>
							<td>
								<html:text property="idUnidadeAtendimento" size="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>&nbsp;
          						<html:text property="descricaoUnidadeAtendimento" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
							</td>
						</tr>
						<tr> 
							<td><strong>Data Prevista:</strong></td>
							<td>
								<html:text property="dataPrevista" size="11" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
							</td>
						</tr>
						
						<tr> 
							<td colspan="2" height="15"></td>
						</tr>
						
						<tr> 
							<td><strong>Cliente Solicitante:</strong></td>
							<td>
								<html:text property="idClienteSolcitante" size="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>&nbsp;
          						<html:text property="nomeClienteSolicitante" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
							</td>
						</tr>
						<tr> 
							<td><strong>Unidade Solicitante:</strong></td>
							<td>
								<html:text property="idUnidadeSolicitante" size="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>&nbsp;
          						<html:text property="descricaoUnidadeSolicitante" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
							</td>
						</tr>
						<tr> 
							<td><strong>Nome Solicitante:</strong></td>
							<td>
								<html:text property="nomeSolicitante" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
							</td>
						</tr>
						
						<tr> 
							<td colspan="2" height="15"></td>
						</tr>
						
						<logic:present name="enderecoOcorrenciaRA" scope="session">
						
						<tr>
				        	<td colspan="2" height="50" valign="top">
							<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<table width="100%" border="0" bgcolor="#90c7fc">
									<tr height="18">
										<td align="center"><strong>Endereço da Ocorrência</strong></td>
									</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table width="100%" align="center" bgcolor="#90c7fc">
									<tr bgcolor="#FFFFFF" height="18">
										<td width="10%" align="center">
											<bean:write name="enderecoOcorrenciaRA"/>
										</td>
									</tr>
									</table>
							  	</td>
							</tr>
							</table>
					   	 	</td>
				        </tr>
				        
				        </logic:present>
				        
				        <tr> 
							<td><strong>Referência:</strong></td>
							<td>
								<html:text property="pontoReferencia" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
							</td>
						</tr>
						<tr> 
							<td><strong>Bairro:</strong></td>
							<td>
								<html:text property="idBairro" size="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>&nbsp;
          						<html:text property="descricaoBairro" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
							</td>
						</tr>
						<tr> 
							<td><strong>Área Bairro:</strong></td>
							<td>
								<html:text property="idBairroArea" size="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>&nbsp;
          						<html:text property="descricaoBairroArea" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
							</td>
						</tr>
						<tr> 
							<td><strong>Local/Setor/Quadra:</strong></td>
							<td>
								<html:text property="idLocalidade" size="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>/
								<html:text property="codigoSetorComercial" size="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>/
								<html:text property="numeroQuadra" size="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
							</td>
						</tr>
						<tr> 
							<td><strong>Unidade Atual:</strong></td>
							<td>
								<html:text property="idUnidadeAtual" size="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>&nbsp;
          						<html:text property="descricaoUnidadeAtual" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
							</td>
						</tr>
						</table>
					</td>
				</tr>
				</table>

				</div>
				
				<logic:present name="protocoloAtendimento">
					
					<table width="100%" border="0" align="center">
			      	<tr>
			      		<td colspan="2" HEIGHT="40" align="center">
			      			<span style="font-size: 18px;font-weight: bold;">
			      			Nº Protocolo: ${sessionScope.protocoloAtendimento}</span></td>
			      	</tr>
					</table>
				
				</logic:present>
				
				<logic:notPresent name="protocoloAtendimento">
				
					<table width="100%" align="center" border="0">
					<tr>
						<td height="20"></td>
					</tr>
					</table>
					
				</logic:notPresent>
				
				<table width="100%" align="center" bgcolor="#90c7fc" border="0">
				<tr>
					<td align="center"><strong>Dados do Solicitante</strong></td>
				</tr>
				<tr bgcolor="#cbe5fe">
					<td width="100%" align="center">
						
						<table width="100%" border="0">
						
						<logic:notPresent name="protocoloAtendimento">
							<tr>
								<td><strong>Nº Protocolo:</strong></td>
								<td><html:text property="protocoloAtendimento"
									readonly="true" style="background-color:#EFEFEF; border:0;"
									size="15" maxlength="14" /></td>
							</tr>
						</logic:notPresent>
						
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
					        		<html:text property="idCliente" size="10" maxlength="9" tabindex="1" onkeypress="validaEnterComMensagem(event, 'exibirAdicionarSolicitanteRegistroAtendimentoAction.do?pesquisarCliente=OK', 'idCliente', 'Cliente');" onkeyup="limpar(1);"/>
									
									<a href="javascript:redirecionarSubmit('exibirAdicionarSolicitanteRegistroAtendimentoAction.do?redirecionarPagina=exibirPesquisarCliente');">
									<img width="23" height="21" border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Município" /></a>
								</logic:notPresent>
					        	
					        	
								<logic:present name="corCliente">
						
									<logic:equal name="corCliente" value="exception">
										<html:text property="nomeCliente" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
									</logic:equal>
						
									<logic:notEqual name="corCliente" value="exception">
										<html:text property="nomeCliente" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
									</logic:notEqual>
						
								</logic:present>
						
								<logic:notPresent name="corCliente">
						
									<logic:empty name="AdicionarSolicitanteRegistroAtendimentoActionForm" property="idCliente">
										<html:text property="nomeCliente" size="45" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
									</logic:empty>
									<logic:notEmpty name="AdicionarSolicitanteRegistroAtendimentoActionForm" property="idCliente">
										<html:text property="nomeCliente" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
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
					        		<html:text property="idUnidadeSolicitanteInformar" size="10" maxlength="9" tabindex="2" readonly="true"/>
								
									<img width="23" height="21" border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Unidade Solicitante" />
								</logic:present>
								
								<logic:notPresent name="desabilitarDadosSolicitanteUnidade">
					        		<html:text property="idUnidadeSolicitanteInformar" size="10" maxlength="9" tabindex="2" onkeypress="limpar(3);validaEnterComMensagem(event, 'exibirAdicionarSolicitanteRegistroAtendimentoAction.do?pesquisarUnidade=OK', 'idUnidadeSolicitanteInformar', 'Unidade Solicitante');"/>
								
									<a href="javascript:redirecionarSubmit('exibirAdicionarSolicitanteRegistroAtendimentoAction.do?redirecionarPagina=exibirPesquisarUnidadeOrganizacional');">
									<img width="23" height="21" border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Unidade Solicitante" /></a>
								</logic:notPresent>
								
								
								<logic:present name="corUnidadeSolicitante">
						
									<logic:equal name="corUnidadeSolicitante" value="exception">
										<html:text property="descricaoUnidadeSolicitanteInformar" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
									</logic:equal>
						
									<logic:notEqual name="corUnidadeSolicitante" value="exception">
										<html:text property="descricaoUnidadeSolicitanteInformar" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
									</logic:notEqual>
						
								</logic:present>
						
								<logic:notPresent name="corUnidadeSolicitante">
						
									<logic:empty name="AdicionarSolicitanteRegistroAtendimentoActionForm" property="idUnidadeSolicitanteInformar">
										<html:text property="descricaoUnidadeSolicitanteInformar" size="45" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
									</logic:empty>
									<logic:notEmpty name="AdicionarSolicitanteRegistroAtendimentoActionForm" property="idUnidadeSolicitanteInformar">
										<html:text property="descricaoUnidadeSolicitanteInformar" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
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
					        		<html:text property="idFuncionarioResponsavel" size="10" maxlength="9" tabindex="3" onkeypress="limpar(5);validaEnterDependencia(event, 'exibirAdicionarSolicitanteRegistroAtendimentoAction.do?pesquisarFuncionario=OK', this, document.forms[0].idUnidadeSolicitanteInformar.value, 'Unidade Solicitante');"/>
								
									<a href="javascript:redirecionarSubmit('exibirAdicionarSolicitanteRegistroAtendimentoAction.do?redirecionarPagina=exibirPesquisarFuncionario');">
									<img width="23" height="21" border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Funcionário" /></a>
								</logic:notPresent>	
								
								<logic:present name="corFuncionario">
						
									<logic:equal name="corFuncionario" value="exception">
										<html:text property="descricaoFuncionarioResponsavel" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
									</logic:equal>
						
									<logic:notEqual name="corFuncionario" value="exception">
										<html:text property="descricaoFuncionarioResponsavel" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
									</logic:notEqual>
						
								</logic:present>
						
								<logic:notPresent name="corFuncionario">
						
									<logic:empty name="AdicionarSolicitanteRegistroAtendimentoActionForm" property="idFuncionarioResponsavel">
										<html:text property="descricaoFuncionarioResponsavel" size="45" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
									</logic:empty>
									<logic:notEmpty name="AdicionarSolicitanteRegistroAtendimentoActionForm" property="idFuncionarioResponsavel">
										<html:text property="descricaoFuncionarioResponsavel" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
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
					        		<html:text property="nomeSolicitanteInformar" size="45" tabindex="4" readonly="true"/>
								</logic:present>
								
								<logic:notPresent name="desabilitarDadosSolicitanteNome">
					        		<html:text property="nomeSolicitanteInformar" size="45" tabindex="4" onblur="habilitaDadosSolicitante(this);" onkeyup="desabilitaDadosSolicitante(this);"/>
								</logic:notPresent>
									
							</td>
					      </tr>
					       <c:if test="${habilitarCampoSatisfacaoEmail != null && habilitarCampoSatisfacaoEmail == true}">
						      <tr>
						      	<td>	
						      		<strong>Enviar email para pesquisa de satisfação:</strong>
						      	</td>
						      	<td>	
						      		<input onclick="selecionouEmailSatisfacaoSim();" type="radio" name="enviarEmailSatisfacao" <c:if test="${AdicionarSolicitanteRegistroAtendimentoActionForm.enviarEmailSatisfacao == 1 || AdicionarSolicitanteRegistroAtendimentoActionForm.enviarEmailSatisfacao == null || AdicionarSolicitanteRegistroAtendimentoActionForm.enviarEmailSatisfacao == ''}">checked="checked"</c:if>  value="1" /><strong> Sim </strong>
						      		<input onclick="selecionouEmailSatisfacaoNao();" type="radio" name="enviarEmailSatisfacao" <c:if test="${AdicionarSolicitanteRegistroAtendimentoActionForm.enviarEmailSatisfacao == 2 }">checked="checked"</c:if> value="2" /><strong> Não </strong>
						      	</td>
						      </tr>
						       <tr>
						      	<td><strong>Endereço de email:</strong></td>
						        <td>
					        		<input type="text" value="${AdicionarSolicitanteRegistroAtendimentoActionForm.enderecoEmail }" name="enderecoEmail" size="40" maxlength="40" <c:if test="${AdicionarSolicitanteRegistroAtendimentoActionForm.enviarEmailSatisfacao == 2 }">disabled="disabled"</c:if>  />
								</td>
						      </tr>
					      </c:if>
					      <tr>
					         <td colspan="3">
					
								<table width="100%" border="0">
						      	<tr>
						      		<td><strong>Endereço do Solicitante:</strong></td>
						      		<td align="right">
						      		
						      			<logic:present name="colecaoEnderecosSolicitante">
							 
											<logic:empty name="colecaoEnderecosSolicitante">
												<!--  <input type="button" class="bottonRightCol" value="Atualizar Cliente" tabindex="3" id="botaoAtualizarCliente" disabled> -->
												<input type="button" class="bottonRightCol" value="Adicionar" tabindex="4" id="botaoEndereco" onclick="redirecionarSubmit('exibirAdicionarSolicitanteRegistroAtendimentoAction.do?redirecionarPagina=exibirInserirEncereco');"> 
											</logic:empty>
											<logic:notEmpty name="colecaoEnderecosSolicitante">
												<!-- <input type="button" class="bottonRightCol" value="Atualizar Cliente" tabindex="3" id="botaoAtualizarCliente"> -->
												<input type="button" class="bottonRightCol" value="Adicionar" tabindex="4" id="botaoEndereco" disabled>
											</logic:notEmpty>
									 
									 	</logic:present>
							
									 	<logic:notPresent name="colecaoEnderecosSolicitante">
										
											<logic:present name="habilitarAlteracaoEnderecoSolicitante">
												<logic:equal name="habilitarAlteracaoEnderecoSolicitante" value="SIM">
													<!-- <input type="button" class="bottonRightCol" value="Atualizar Cliente" tabindex="3" id="botaoAtualizarCliente" disabled> -->
													<input type="button" class="bottonRightCol" value="Adicionar" tabindex="4"  id="botaoEndereco" onclick="redirecionarSubmit('exibirAdicionarSolicitanteRegistroAtendimentoAction.do?redirecionarPagina=exibirInserirEncereco');">
											 	</logic:equal>
											 	<logic:notEqual name="habilitarAlteracaoEnderecoSolicitante" value="SIM">
											 		<!-- <input type="button" class="bottonRightCol" value="Atualizar Cliente" tabindex="3" id="botaoAtualizarCliente"> -->
											 		<input type="button" class="bottonRightCol" value="Adicionar" tabindex="4" id="botaoEndereco" disabled>
											 	</logic:notEqual>
											</logic:present>
										
											<logic:notPresent name="habilitarAlteracaoEnderecoSolicitante">
												<!-- <input type="button" class="bottonRightCol" value="Atualizar Cliente" tabindex="3" id="botaoAtualizarCliente" disabled> -->
												<input type="button" class="bottonRightCol" value="Adicionar" tabindex="4"  id="botaoEndereco" onclick="redirecionarSubmit('exibirAdicionarSolicitanteRegistroAtendimentoAction.do?redirecionarPagina=exibirInserirEncereco');">
											</logic:notPresent>
									 	
									 	</logic:notPresent>
						      		
						      		</td>
						      	</tr>
							 	</table>
							 </td>
					     </tr>
					     
					     <% String cor = "";%>
					     
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
					
								<logic:present name="colecaoEnderecosSolicitante">
								
								<INPUT TYPE="hidden" ID="enderecoInformado">
					
								<tr>
									<td>
										<table width="100%" align="center" bgcolor="#99CCFF">
											<!--corpo da segunda tabela-->
					
											<% cor = "#cbe5fe";%>
					
											<logic:iterate name="colecaoEnderecosSolicitante" id="endereco" type="ClienteEndereco">
											
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
																<a href="javascript:if(confirm('Confirma remoção?')){remover();}" alt="Remover"><img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0"></a>
											 				</logic:equal>
											 				<logic:notEqual name="habilitarAlteracaoEnderecoSolicitante" value="SIM">
											 					<img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0">
											 				</logic:notEqual>
														</logic:present>
										
														<logic:notPresent name="habilitarAlteracaoEnderecoSolicitante">
															<a href="javascript:if(confirm('Confirma remoção?')){remover();}" alt="Remover"><img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0"></a>
														</logic:notPresent>
													
													</td>
													
													<td width="20%" align="center">
													    <logic:empty name="AdicionarSolicitanteRegistroAtendimentoActionForm" property="idCliente">
													      <input type="radio" name="clienteEnderecoSelected" value="<%=""+endereco.getUltimaAlteracao().getTime()%>" checked="checked"/>
														</logic:empty>
														<logic:notEmpty name="AdicionarSolicitanteRegistroAtendimentoActionForm" property="idCliente">
														      <logic:empty
																name="endereco"
																property="indicadorEnderecoCorrespondencia">
																<input type="radio" name="clienteEnderecoSelected"
																	value="${endereco.ultimaAlteracao.time}" disabled="true"/>
															 </logic:empty> 
															 <logic:notEmpty
																name="endereco"
																property="indicadorEnderecoCorrespondencia">
																<logic:equal name="endereco"
																property="indicadorEnderecoCorrespondencia" value="<%=""+ConstantesSistema.INDICADOR_ENDERECO_CORRESPONDENCIA%>">
																	<input type="radio" name="clienteEnderecoSelected"
																		value="${endereco.ultimaAlteracao.time}"
																		checked="checked" disabled="true"/>
																</logic:equal>
																<logic:notEqual name="endereco"
																property="indicadorEnderecoCorrespondencia" value="<%=""+ConstantesSistema.INDICADOR_ENDERECO_CORRESPONDENCIA%>">
																	<input type="radio" name="clienteEnderecoSelected"
																		value="${endereco.ultimaAlteracao.time}" disabled="true"/>
																</logic:notEqual>
															 </logic:notEmpty>
													  </logic:notEmpty>
													</td>
																
													<td>
														<logic:equal name="habilitarAlteracaoEnderecoSolicitante" value="SIM">
															<a href="javascript:abrirPopup('exibirInserirEnderecoAction.do?exibirEndereco=OK&tipoPesquisaEndereco=registroAtendimento&operacao=4&mostrarPerimetro=sim', 570, 700)"><bean:write name="endereco" property="enderecoFormatadoAbreviado"/></a>
														</logic:equal>
														
														<logic:notEqual name="habilitarAlteracaoEnderecoSolicitante" value="SIM">
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
					   	
					   	<logic:present name="colecaoEnderecosSolicitante">
					   	
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
							              <input type="button" class="bottonRightCol" value="Adicionar" tabindex="5" id="botaoFone" onclick="redirecionarSubmit('exibirAdicionarSolicitanteRegistroAtendimentoAction.do?redirecionarPagina=exibirAdicionarSolicitanteFone');">
									 	</logic:present>
							
									 	<logic:notPresent name="colecaoFonesSolicitante">
										
											<logic:present name="habilitarAlteracaoEnderecoSolicitante">
												<logic:equal name="habilitarAlteracaoEnderecoSolicitante" value="SIM">
													<input type="button" class="bottonRightCol" value="Adicionar" tabindex="5"  id="botaoFone" onclick="redirecionarSubmit('exibirAdicionarSolicitanteRegistroAtendimentoAction.do?redirecionarPagina=exibirAdicionarSolicitanteFone');">
											 	</logic:equal>
											 	<logic:notEqual name="habilitarAlteracaoEnderecoSolicitante" value="SIM">
											 		<input type="button" class="bottonRightCol" value="Adicionar" tabindex="5" id="botaoFone" disabled>
											 	</logic:notEqual>
											</logic:present>
										
											<logic:notPresent name="habilitarAlteracaoEnderecoSolicitante">
												<input type="button" class="bottonRightCol" value="Adicionar" tabindex="5"  id="botaoFone" onclick="redirecionarSubmit('exibirAdicionarSolicitanteRegistroAtendimentoAction.do?redirecionarPagina=exibirAdicionarSolicitanteFone');">
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
					
								<logic:present name="colecaoFonesSolicitante">
					
								<tr>
									<td>
										<table width="100%" align="center" bgcolor="#99CCFF">
											<!--corpo da segunda tabela-->
					
											<% cor = "#cbe5fe";%>
					
											<logic:iterate name="colecaoFonesSolicitante" id="fone" type="ClienteFone">
											
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
													   	<logic:empty name="AdicionarSolicitanteRegistroAtendimentoActionForm" property="idCliente">
													     	
													     	<logic:empty name="AdicionarSolicitanteRegistroAtendimentoActionForm" property="clienteFoneSelected">
															 
															 <logic:empty
																name="fone"
																property="indicadorTelefonePadrao">
																<input type="radio" name="clienteFoneSelected"
																	value="${fone.ultimaAlteracao.time}" />
															 </logic:empty> 
															 
															 <logic:notEmpty
																name="fone"
																property="indicadorTelefonePadrao">
																<logic:equal name="fone"
																property="indicadorTelefonePadrao" value="<%=""+ClienteFone.INDICADOR_FONE_PADRAO%>">
																	<input type="radio" name="clienteFoneSelected"
																		value="${fone.ultimaAlteracao.time}"
																		checked="checked" />
																</logic:equal>
																<logic:notEqual name="fone"
																property="indicadorTelefonePadrao" value="<%=""+ClienteFone.INDICADOR_FONE_PADRAO%>">
																	<input type="radio" name="clienteFoneSelected"
																		value="${fone.ultimaAlteracao.time}" />
																</logic:notEqual>
															 </logic:notEmpty>
															
															</logic:empty>
														
															<logic:notEmpty name="AdicionarSolicitanteRegistroAtendimentoActionForm" property="clienteFoneSelected">
															  
															  <logic:equal name="fone"
																property="indicadorTelefonePadrao" value="<%=""+ClienteFone.INDICADOR_FONE_PADRAO%>">
																	<input type="radio" name="clienteFoneSelected"
																		value="${fone.ultimaAlteracao.time}"
																		checked="checked" />
																</logic:equal>
																<logic:notEqual name="fone"
																property="indicadorTelefonePadrao" value="<%=""+ClienteFone.INDICADOR_FONE_PADRAO%>">
																	<input type="radio" name="clienteFoneSelected"
																		value="${fone.ultimaAlteracao.time}" />
																</logic:notEqual>
															
															</logic:notEmpty>
														
														</logic:empty>
														
														<logic:notEmpty name="AdicionarSolicitanteRegistroAtendimentoActionForm" property="idCliente">
														      <html:radio property="clienteFoneSelected" value="${fone.ultimaAlteracao.time}" disabled="true"/>
														</logic:notEmpty></td>
													
													
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
      	<td>
	      	<table width="100%" border="0">
	        <tr>
	          <td colspan="4" height="20"></td>
	        </tr>
	        </table>
	        
	        <table width="100%" border="0">
	        <tr>
	          <td>
	          	<input type="button" class="bottonRightCol" value="Fechar" onClick="window.close();" style="width: 70px;">
	          </td>
	          <td colspan="3" align="right">
	          	<input type="button" class="bottonRightCol" value="Concluir" onClick="if (validateAdicionarSolicitanteRegistroAtendimentoActionForm(document.forms[0])){submeterFormPadrao(document.forms[0])};">
	          </td>
	        </tr>
	        </table>
	    </td>
	  </tr>
	  </table>
	</td>
  </tr>
</table>
</body>
</html:form>
</html:html>
