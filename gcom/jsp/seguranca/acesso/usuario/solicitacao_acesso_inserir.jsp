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

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="ExibirInserirSolicitacaoAcessoActionForm"/>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
	
	function validarForm(){
		var form = document.forms[0];
		
		if(validateExibirInserirSolicitacaoAcessoActionForm(form)){
			
			if (form.dataInicial.value != '' && form.dataFinal.value != ''){
						
				if( validaData(form.dataInicial) && validaData(form.dataFinal)){
					
					if ( comparaData(form.dataInicial.value, "<", form.dataFinal.value )
							|| comparaData(form.periodoInicial.value, "=", form.periodoFinal.value) ){
		  				
		  				if(validarIdadeUsuario()){
			  				if(verificarUsuarioTipo()){
								if(form.idsGrupo.selectedIndex > 0){
									submeterFormPadrao(form);			
								}else{
									alert('Informe o Grupo.');
								}
							}
						}
		  			}else{
		  				alert('Data final do período é anterior à data inicial.');			
		  			}
		  		}
			}
		}
	}
  	
  	function limparPesquisaFuncionario(tipo){
		var form = document.forms[0];
		
		switch(tipo){
			case 1: // Funcionario Superior

				form.idFuncionarioSuperior.value = "";
				form.nomeFuncionarioSuperior.value = "";
				break;

			case 2: // Funcionario

				form.idFuncionario.value = "";
				form.nomeFuncionario.value = "";
				break;
		}
	}
	
	function limparLotacao(){
		var form = document.forms[0];
		
		form.idLotacao.value = "";
		form.nomeLotacao.value = "";
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
		
		if (tipoConsulta == 'funcionario') {

			form.idFuncionario.value = codigoRegistro;
	  		form.nomeFuncionario.value = descricaoRegistro;
	  		form.nomeFuncionario.style.color = "#000000";
	  		form.action = 'exibirInserirSolicitacaoAcessoAction.do';
 			form.submit();
	 	}
	 	else if ('unidadeOrganizacional' == tipoConsulta) { 
		 	
		 	form.idLotacao.value = codigoRegistro;
		 	form.nomeLotacao.value = descricaoRegistro;
		 	form.action = 'exibirInserirSolicitacaoAcessoAction.do';
		 	form.submit();
	 	}
	 	else if (tipoConsulta == 'idFuncionarioSuperior') {

			form.idFuncionarioSuperior.value = codigoRegistro;
	  		form.nomeFuncionarioSuperior.value = descricaoRegistro;
	  		form.nomeFuncionarioSuperior.style.color = "#000000";
	  		form.action = 'exibirInserirSolicitacaoAcessoAction.do';
 			form.submit();
	 	}
	}
   
   function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg, campo){
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
	
	function limpaDataFinal(){
		var form = document.forms[0];
  		
  		if(form.dataInicial.value == ''){
   			form.dataFinal.value = '';
  		}
	}
	
	function validaFuncionario() {

   		var form = document.forms[0];
   		var retorno = true;

   		if(form.cpf.value == null || form.cpf.value == ''){
   		 	alert("Informe o CPF");
   		 	retorno = false;
   		}

   		if(form.dataNascimento.value == null || form.dataNascimento.value == ''){
   		 	alert("Informe a Data de Nascimento ");
   		 	retorno = false;
   		}

   		return retorno;
   	}
   	
   	function desabilitaCampos(){
   		var form = document.forms[0];
   		
   		var tipoUsuario = returnObject(form, "idTipoUsuario");
   		var indicadorPrestadorServico = tipoUsuario.options[tipoUsuario.options.selectedIndex].value;

   		if(indicadorPrestadorServico.length > 0 && indicadorPrestadorServico == '8'){
   			
   			form.idFuncionario.disabled = true;
   			form.idFuncionario.value = '';
   			form.idEmpresa.disabled = false;
   			form.nomeUsuario.disabled = false;
   		}else if(indicadorPrestadorServico > 0){
   			form.idFuncionario.disabled = false;
   			form.idEmpresa.disabled = true;
   			form.nomeUsuario.disabled = true;
   			form.idEmpresa.value = '';
   		}else{
   			form.idFuncionario.disabled = false;
   			form.idEmpresa.disabled = false;
   			form.nomeUsuario.disabled = false;
   			form.nomeUsuario.value = '';
   		}
   	}
   	
   	function verificarUsuarioTipo(){
   		var form = document.forms[0];
   		var retorno = true;
   		
   		var tipoUsuario = returnObject(form, "idTipoUsuario");
   		var indicadorPrestadorServico = tipoUsuario.options[tipoUsuario.options.selectedIndex].value;
   		
   		var empresa = returnObject(form, "idEmpresa");
   		var indicadorEmpresa = empresa.options[empresa.options.selectedIndex].value;
   		
   		if(indicadorPrestadorServico.length > 0 && indicadorPrestadorServico == '8'){
   			
   			if(indicadorEmpresa < 0 || indicadorEmpresa == ''){
   				
   				alert("Informe a Empresa.");
   				form.idEmpresa.focus();
   		 		retorno = false;
   			}
   			
   			if(form.nomeUsuario.value == ''){
   				
   				alert("Informe o Nome do Usuário.");
   				form.nomeUsuario.focus();
   		 		retorno = false;
   			}
   		}else if(indicadorPrestadorServico > 0){
   			
   			if(form.idFuncionario.value == ''){
   				
   				alert("Informe a Matrícula do Funcionário.");
   				form.idFuncionario.focus();
   		 		retorno = false;
   		 	}
   		}else{
   			
   			alert("Informe o Tipo de Usuário.");
   			form.idTipoUsuario.focus();
   		 	retorno = false;
   		}
   		
   		return retorno;
   	}
   	
   	function replicarLogin(){
   		var form = document.forms[0];
   		
   		var tipoUsuario = returnObject(form, "idTipoUsuario");
   		var indicadorPrestadorServico = tipoUsuario.options[tipoUsuario.options.selectedIndex].value;
   		
   		if(indicadorPrestadorServico.length > 0 && indicadorPrestadorServico == '8'){
   			form.login.value = form.cpf.value;
   		}else{
   			form.login.value = form.idFuncionario.value;
   		}
   	}
   	
   	function validarIdadeUsuario(){
		var form = document.forms[0];
		var retorno = true;
			
		if (form.dataNascimento.disabled == false && form.dataNascimento.value.length > 0){
					
			var dataAtual = document.getElementById("DATA_ATUAL").value;
			var idadeMinimaUsuario = document.getElementById("IDADE_MINIMA_USUARIO").value;
			
			var idadeUsuario = anosEntreDatas(form.dataNascimento.value, dataAtual);
			
			if (parseInt(idadeUsuario) < parseInt(idadeMinimaUsuario)){
				
				alert("O usuário terá que possuir, no mínimo, 18 anos de idade.");
				form.dataNascimento.focus();
				retorno = false;
			}
		}
			
		return retorno;
	}
	
	function reloadTipoUsuario() {
		var form = document.forms[0];
		
		if (form.idTipoUsuario != null || form.idTipoUsuario.value != ''){
			
			form.action = "/gsan/exibirInserirSolicitacaoAcessoAction.do?usuarioTipo="+form.idTipoUsuario.value;
			form.submit();
		}
	}
	
	function preencherDadosFuncionario(){
		var form = document.forms[0];
		
		if(form.idFuncionario.value != ''){
			
			if(form.login.value == ''){
				form.login.value = form.idFuncionario.value;
			}
		}
	}
  	 	
</script>

</head>

<body leftmargin="5" topmargin="5" onload ="desabilitaCampos();preencherDadosFuncionario();" >

<div id="formDiv"><html:form action="/inserirSolicitacaoAcessoAction.do"
	name="ExibirInserirSolicitacaoAcessoActionForm"
	type="gcom.gui.seguranca.acesso.usuario.ExibirInserirSolicitacaoAcessoActionForm"
	method="post">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<INPUT TYPE="hidden" ID="DATA_ATUAL" value="${requestScope.dataAtual}" />
<INPUT TYPE="hidden" ID="IDADE_MINIMA_USUARIO" value="${requestScope.idadeMinimaUsuario}" />


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
					<td class="parabg">Inserir Solicitação de Acesso</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="5">Funcionário Solicitante </td>
				</tr>
				
				<tr>
					<td width="26%" colspan="2">
				   		<strong>Matrícula do Funcionário:<font color="#FF0000">*</font></strong>
				   	</td>
				   	
				   	<td width="74%" height="24" colspan="3">
				   		<html:text maxlength="8" 
                   				   property="idFuncionarioSolicitante" 
                   				   size="8"  
                   				   tabindex="1" 
                   				   style="background-color:#EFEFEF; border:0; color: #000000" />
                   				   
                   		<html:text property="nomeFuncionarioSolicitante" 
	                       			   size="40" 
	                       			   readonly="true" 
	                       			   style="background-color:#EFEFEF; border:0; color: #000000" />
				   	</td>
				</tr>
				
				<tr>
					<td colspan="5"><hr></td>
				</tr>
				
				<tr>
					<td colspan="5">Responsável pela Autorização - Superior Hierárquico</td>
				</tr>
				
				<tr>	
					<td width="26%" colspan="2">
				   		<strong>Matrícula do Funcionário:<font color="#FF0000">*</font></strong>
				   </td>
                   <td width="74%" height="24" colspan="3">
                   		<html:text maxlength="8" 
                   				   property="idFuncionarioSuperior" 
                   				   size="8"  
                   				   tabindex="1" 
                   				   onkeypress="javascript:validaEnter(event, 'exibirInserirSolicitacaoAcessoAction.do?objetoConsulta=1', 'idFuncionarioSuperior'); return isCampoNumerico(event);"
                   		/>
                      	<a href="javascript:chamarPopup('exibirFuncionarioPesquisar.do', 'idFuncionarioSuperior', null, null, 495, 300, '',document.forms[0].idFuncionarioSuperior);">
                        	<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Funcionário"/></a>
					
   		      			<logic:present name="funcionarioInexistente1" 
   		      						   scope="request">
							<input type="text" 
								   name="nomeFuncionarioSuperior" 
								   size="40" 
								   readonly="true" 
								   style="background-color:#EFEFEF; border:0; color: #ff0000" 
								   value="<bean:message key="pesquisa.funcionario.inexistente"/>"/>
                      	</logic:present>

                        <logic:notPresent name="funcionarioInexistente1" 
                      					scope="request">
	                       	<html:text property="nomeFuncionarioSuperior" 
	                       			   size="40" 
	                       			   readonly="true" 
	                       			   style="background-color:#EFEFEF; border:0; color: #000000" />
                        </logic:notPresent>
                        
						<a href="javascript:limparPesquisaFuncionario(1);document.forms[0].idFuncionarioSuperior.focus();"> 
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>                   
				   </td>
				</tr>
				
				<tr> 
              		<td colspan="2"><strong>Notificar Responsável por E-mail:<font color="#FF0000"></font></strong></td>
                	<td colspan="3">
                		<span class="style2">
		                  <strong> 
							  <html:radio property="icNotificar" value="0" onclick=""/>
			 				  Sim
							  <html:radio property="icNotificar" value="1" onclick=""/>
							  Não
						  </strong>
						</span>
			    	</td>
              	</tr>
				
				<tr>
					<td colspan="5"><hr></td>
				</tr>
				
				<tr>
					<td colspan="5">Dados do Funcionário</td>
				</tr>
				
				<tr>
					<td width="22%" colspan="2"><strong>Tipo de Usuário:<font color="#FF0000">*</font></strong></td>
					<td colspan="3">
						<html:select property="idTipoUsuario"
									 style="width: 230px;"
									 size="1" 
									 tabindex="1" 
									 onchange="desabilitaCampos();reloadTipoUsuario();">
							
							<logic:notEmpty name="colecaoUsuarioTipo" scope="session">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoUsuarioTipo"
											  labelProperty="descricao" 
											  property="id" />
							</logic:notEmpty>

							<font size="1">&nbsp; </font>
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td width="22%" colspan="2"><strong>Empresa:<font color="#FF0000"></font></strong></td>
					<td colspan="3">
						<html:select property="idEmpresa"
									 style="width: 230px;"
									 size="1" 
									 tabindex="1" >
							
							<logic:notEmpty name="colecaoEmpresa" scope="session">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoEmpresa"
											  labelProperty="descricao" 
											  property="id" />
							</logic:notEmpty>

							<font size="1">&nbsp; </font>
						</html:select>
					</td>
				</tr>
				
				<tr>
				   <td width="26%" colspan="2">
				   		<strong>Matrícula do Funcionário:<font color="#FF0000"></font></strong>
				   </td>
                   <td width="74%" height="24" colspan="3">
                   		<html:text maxlength="8" 
                   				   property="idFuncionario" 
                   				   size="8"  
                   				   tabindex="1" 
                   				   onkeypress="javascript:validaEnter(event, 'exibirInserirSolicitacaoAcessoAction.do?objetoConsulta=2', 'idFuncionario'); return isCampoNumerico(event); reloadTipoUsuario(); "
                   		/>
                      	<a href="javascript:javascript:chamarPopup('exibirFuncionarioPesquisar.do?', 'idFuncionario', null, null, 495, 300, '',document.forms[0].idFuncionario); reloadTipoUsuario(); ">
                        	<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Funcionário"/></a>
					
   		      			<logic:present name="funcionarioInexistente" 
   		      						   scope="request">
							<input type="text" 
								   name="nomeFuncionario" 
								   size="40" 
								   readonly="true" 
								   style="background-color:#EFEFEF; border:0; color: #ff0000" 
								   value="<bean:message key="pesquisa.funcionario.inexistente"/>"/>
                      	</logic:present>

                        <logic:notPresent name="funcionarioInexistente" 
                      					scope="request">
	                       	<html:text property="nomeFuncionario" 
	                       			   size="40" 
	                       			   readonly="true" 
	                       			   style="background-color:#EFEFEF; border:0; color: #000000" />
                        </logic:notPresent>
                        
						<a href="javascript:limparPesquisaFuncionario(2);document.forms[0].idFuncionario.focus();"> 
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>                   
				   </td>
                </tr>
                
                <tr>
                	<td width="26%" colspan="2"><strong>Nome do Usuario:<font color="#FF0000"></font></strong></td>
					<td width="74%" height="24" colspan="3">
					   	<html:text property="nomeUsuario" 
		                       	   size="50"
		                       	   maxlength="50" />
				   	</td>
                </tr>
                
                <tr>
					<td width="26%" colspan="2"><strong>Número do CPF:<font color="#ff0000">*</font></strong></td>
					<td width="74%" colspan="3">
						<html:text property="cpf" 
								   size="12"
								   maxlength="11" 
								   onkeypress="return isCampoNumerico(event);"
								   onkeyup="javascript:replicarLogin();"
								   onclick="javascript:replicarLogin();" />
					</td>
				</tr>
				
				<tr>
					<td height="10" colspan="2"><strong>Data de Nascimento:<font color="#FF0000">*</font></strong></td>
					<td colspan="3">
					<html:text property="dataNascimento" 
							   size="11" 
							   maxlength="10" 
							   tabindex="4" 
							   onkeyup="mascaraData(this, event);" /> 
					<a href="javascript:abrirCalendario('ExibirInserirSolicitacaoAcessoActionForm', 'dataNascimento')">
						<img border="0"
							 src="<bean:message 
							 key='caminho.imagens'/>calendario.gif"
							 width="20" 
							 border="0" 
							 align="absmiddle" 
							 alt="Exibir Calendário" /></a> dd/mm/aaaa
					</td>
				</tr>
				
				<tr>	
					<td width="26%" colspan="2">
				   		<strong>Unidade de Lotação:<font color="#FF0000">*</font></strong>
				   </td>
                   <td width="74%" height="24" colspan="3">
                   		<html:text maxlength="9" 
                   				   property="idLotacao" 
                   				   size="9"  
                   				   tabindex="1" 
                   				   onkeypress="javascript:validaEnter(event, 'exibirInserirSolicitacaoAcessoAction.do', 'idLotacao'); return isCampoNumerico(event);"
                   		/>
                      	<a href="javascript:chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do?limpaForm=S', null, null, null, 495, 300, '', document.forms[0].nomeLotacao);">
                        	<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Lotação"/></a>
					
   		      			<logic:present name="lotacaoInexistente" 
   		      						   scope="request">
							<input type="text" 
								   name="nomeLotacao" 
								   size="40" 
								   readonly="true" 
								   style="background-color:#EFEFEF; border:0; color: #ff0000" 
								   value="<bean:message key="pesquisa.lotacao.inexistente"/>"/>
                      	</logic:present>

                        <logic:notPresent name="lotacaoInexistente" 
                      					scope="request">
	                       	<html:text property="nomeLotacao" 
	                       			   size="40" 
	                       			   readonly="true" 
	                       			   style="background-color:#EFEFEF; border:0; color: #000000" />
                        </logic:notPresent>
                        
						<a href="javascript:limparLotacao();document.forms[0].idLotacao.focus();"> 
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>                   
				   </td>
				</tr>
				
				<tr>
					<td colspan="2"><strong>Login:<font color="#ff0000">*</font></strong></td>
					<td colspan="1">
						<html:text property="login" 
								   size="11" 
								   maxlength="11" 
								   style="text-transform: none;"/>
					</td>
					<td colspan="1"><strong>E-Mail:<font color="#ff0000">*</font></strong></td>
					<td colspan="2">
						<html:text property="email" 
								   size="40" 
								   maxlength="70" 
								   style="text-transform: none;"/>
					</td>
				</tr>
				
				<tr>
					<td width="26%" colspan="2"><strong>Período de Cadastramento:<font color="#ff0000">*</font></strong></td>
					<td width="74%" colspan="3">
						<html:text property="dataInicial" 
								   size="10" 
								   maxlength="10" 
								   onkeyup="mascaraData(this, event);limpaDataFinal();" /> 
						<a href="javascript:abrirCalendario('ExibirInserirSolicitacaoAcessoActionForm', 'dataInicial')">
						<img border="0"
							 src="<bean:message key='caminho.imagens'/>calendario.gif"
							 width="20" border="0" align="middle" alt="Exibir Calendário" /></a>&nbsp; 
						
						<html:text property="dataFinal" 
								   size="10" 
								   maxlength="10" 
								   onkeyup="mascaraData(this, event);" /> 
						<a href="javascript:abrirCalendario('ExibirInserirSolicitacaoAcessoActionForm', 'dataFinal')"> 
						<img border="0"
							 src="<bean:message key='caminho.imagens'/>calendario.gif"
							 width="20" border="0" align="middle" alt="Exibir Calendário" /></a> dd/mm/aaaa
					</td>
				</tr>
                
                
				
				<tr>
					<td colspan="5"><hr></td>
				</tr>
				
				<tr>
					<td width="22%" colspan="1"><strong>Grupo: <font color="#FF0000">*</font></strong></td>
					<td colspan="4">
						<html:select property="idsGrupo" 
									 multiple="mutiple" 
									 size="4" 
									 tabindex="2"
									 style="width: 320px;">
							 
							<logic:notEmpty name="colecaoGrupo">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoGrupo"
											  labelProperty="descricao" 
											  property="id" />
							</logic:notEmpty>
							
						</html:select>
					</td>
				</tr>
			
				<tr>
					<td colspan="5"><hr></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td colspan="2">&nbsp;</td>
					<td align="left" colspan="3"><font color="#FF0000">*</font> Campo Obrigatório</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
								          	
				<tr>
					<td align="left" colspan="4">
			          	<input type="button" 
			          		   class="bottonRightCol" 
			          		   value="Limpar" 
			          		   onClick="window.location.href='/gsan/exibirInserirSolicitacaoAcessoAction.do?menu=sim'" />
			          		   
			          	<input type="button" 
							   name="ButtonCancelar" 
							   class="bottonRightCol" 
							   value="Cancelar"
							   onClick="javascript:document.forms[0].target='';window.location.href='/gsan/telaPrincipal.do'">
					</td>
					
					<td align="right">
						<input type="button" 
							   name="Button" 
							   class="bottonRightCol" 
							   value="Concluir" 
							   onClick="javascript:validarForm()" />
					</td>
					
				</tr>							
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
		
<%@ include file="/jsp/util/rodape.jsp"%>	
</html:form></div>
</body>
</html:html>
