<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirGuiaDevolucaoActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
	var tipoFuncionario = 1;
	//Recebe os dados do(s) popup(s)
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.InserirGuiaDevolucaoActionForm;

      	if (tipoConsulta == 'localidade') {
        	form.idLocalidade.value = codigoRegistro;
        	form.idLocalidadeHidden.value = codigoRegistro;
        	form.descricaoLocalidade.value = descricaoRegistro;
      	}
	
	  	if (tipoConsulta == 'tipoDebito') { 	
 	  		form.idTipoDebito.value = codigoRegistro;
 	  		form.idTipoDebitoHidden.value = codigoRegistro;
 	  		form.descricaoTipoDebito.value = descricaoRegistro; 
 	  	}
 	  	
       	if (tipoConsulta == 'registroAtendimento') {

        	form.idRegistroAtendimento.value = codigoRegistro;
			form.nomeRegistroAtendimento.value = descricaoRegistro;
			
			form.action='exibirInserirGuiaDevolucaoAction.do';
	      	form.submit();
     	}
       	
       	if (tipoConsulta == 'ordemServico') {

        	form.idOrdemServico.value = codigoRegistro;
        	form.descricaoOrdemServico.value = descricaoRegistro;

			form.action='exibirInserirGuiaDevolucaoAction.do';
	      	form.submit();
      	}

      	if (tipoConsulta == 'funcionario') {

			if(tipoFuncionario == 1){

			 	form.idFuncionarioAnalista.value = codigoRegistro;
			 	form.nomeFuncionarioAnalista.value = descricaoRegistro;
		      	form.nomeFuncionarioAnalista.style.color = "#000000";

			}else if(tipoFuncionario == 2){

			 	form.idFuncionarioAutorizador.value = codigoRegistro;
			 	form.nomeFuncionarioAutorizador.value = descricaoRegistro;
		      	form.nomeFuncionarioAutorizador.style.color = "#000000";
			}
      	}
 	  	
    }
    
    function recuperarDadosQuatroParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, tipoConsulta) {
        
    	var form = document.InserirGuiaDevolucaoActionForm;
        
    	if (tipoConsulta == 'conta') {
 	    	form.referenciaConta.value = descricaoRegistro1;
	 		form.action = "/gsan/exibirInserirGuiaDevolucaoAction.do";
 	    	form.submit();
		}   
    }
    
    function recuperarDadosCincoParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, descricaoRegistro3, tipoConsulta) {
        
        var form = document.InserirGuiaDevolucaoActionForm;
        
    	if (tipoConsulta == 'guiaPagamento') {
    		form.idGuiaPagamento.value = codigoRegistro;
 	    	form.action = "/gsan/exibirInserirGuiaDevolucaoAction.do";
 	    	form.submit();
		}
		
		if (tipoConsulta == 'debitoACobrar') {
    		form.idDebitoACobrar.value = codigoRegistro;
 	    	form.action = "/gsan/exibirInserirGuiaDevolucaoAction.do";
 	    	form.submit();
		}
    }
   
   function limparLocalidade() {
		var form = document.InserirGuiaDevolucaoActionForm;

		form.idLocalidade.value = "";
		form.idLocalidadeHidden.value = "";
		form.descricaoLocalidade.value = "";
   }
   
   function limparRegistroAtendimentoTecla() {

		var form = document.InserirGuiaDevolucaoActionForm;

		form.nomeRegistroAtendimento.value = "";
		form.idOrdemServico.value = "";
		form.nomeOrdemServico.value = "";
		form.documentoTipo.selectedIndex = 0;
		form.documentoTipoHidden.value = form.documentoTipo.value;
		form.idImovel.value = "";
		form.descricaoImovel.value = "";
		form.idLocalidade.value = "";
		form.idLocalidadeHidden.value = "";
		form.descricaoLocalidade.value = "";
		form.codigoCliente.value = "";
		form.nomeCliente.value = "";
		form.referenciaConta.value = "";
		form.descricaoConta.value = "";
		form.idGuiaPagamento.value = "";
		form.descricaoGuiaPagamento.value = "";
		form.valorGuiaPagamento.value = "";
		form.idDebitoACobrar.value = "";
		form.descricaoDebitoACobrar.value = "";
		form.valorDebitoACobrar.value = "";
		form.idTipoDebito.value = "";
		form.idTipoDebitoHidden.value = "";
		form.descricaoTipoDebito.value = "";
		form.valorDevolucao.value = "";
   }
   
   function limparLocalidadeTecla() {
		var form = document.InserirGuiaDevolucaoActionForm;

		form.idLocalidadeHidden.value = form.idLocalidade.value;
		form.descricaoLocalidade.value = "";
	}
	
	function limparOrdemServicoTecla() {

		var form = document.InserirGuiaDevolucaoActionForm;
		
		form.nomeOrdemServico.value = "";
		form.idTipoDebito.value = "";
		form.descricaoTipoDebito.value = "";
		
		if (form.documentoTipo.disabled == true) {
			form.documentoTipo.selectedIndex = 0;
		}
		
		form.documentoTipo.disabled = false;
		form.documentoTipoHidden.value = form.documentoTipo.value;
	}
	
	function limparContaTecla() {
		var form = document.InserirGuiaDevolucaoActionForm;
		form.descricaoConta.value = "";
		form.valorDevolucao.value = "";
	}
	
	function limparGuiaPagamentoTecla() {
		var form = document.InserirGuiaDevolucaoActionForm;
		form.descricaoGuiaPagamento.value = "";
		form.valorGuiaPagamento.value = "";
		form.valorDevolucao.value = "";
	}
	
	function limparDebitoACobrarTecla() {
		var form = document.InserirGuiaDevolucaoActionForm;
		form.descricaoDebitoACobrar.value = "";
		form.valorDebitoACobrar.value = "";
		form.valorDevolucao.value = "";
	}
	
	function limparTipoDebitoTecla() {
		var form = document.InserirGuiaDevolucaoActionForm;
		form.idTipoDebitoHidden.value = form.idTipoDebito.value;
		form.descricaoTipoDebito.value = "";
	}

	function limparForm() {
		var form = document.InserirGuiaDevolucaoActionForm;

		form.idRegistroAtendimento.disabled = false;
		form.idRegistroAtendimento.value = "";
		form.nomeRegistroAtendimento.value = "";

		form.idOrdemServico.disabled = false;
		form.idOrdemServico.value = "";
		form.nomeOrdemServico.value = "";
		form.documentoTipo.selectedIndex = 0;
		form.documentoTipoHidden.value = form.documentoTipo.value;
		form.idImovel.value = "";
		form.descricaoImovel.value = "";
		form.idLocalidade.value = "";
		form.idLocalidadeHidden.value = "";
		form.descricaoLocalidade.value = "";
		form.codigoCliente.value = "";
		form.nomeCliente.value = "";
		form.referenciaConta.value = "";
		form.descricaoConta.value = "";
		form.idGuiaPagamento.value = "";
		form.descricaoGuiaPagamento.value = "";
		form.valorGuiaPagamento.value = "";
		form.idDebitoACobrar.value = "";
		form.descricaoDebitoACobrar.value = "";
		form.valorDebitoACobrar.value = "";
		form.idTipoDebito.value = "";
		form.idTipoDebitoHidden.value = "";
		form.descricaoTipoDebito.value = "";
		form.valorDevolucao.value = "";
		
		limparFuncionarioAnalista();
		limparFuncionarioAutorizador();
   }
   
   function limparOrdemServico() {

		var form = document.InserirGuiaDevolucaoActionForm;

		form.idOrdemServico.value = "";
		form.nomeOrdemServico.value = "";

		form.idTipoDebito.disabled = false;
		form.idTipoDebito.value = "";
		form.idTipoDebitoHidden.value = "";

		form.descricaoTipoDebito.value = "";
		
		if(form.idRegistroAtendimento.disabled){

			form.idRegistroAtendimento.disabled = false;
			form.idRegistroAtendimento.value = "";
			form.nomeRegistroAtendimento.value = "";
		}
		
		if(form.idLocalidade.disabled){
			form.idLocalidade.disabled = false;
			
			form.idLocalidade.value ="";
			form.idLocalidadeHidden.value = "";
			form.descricaoLocalidade.value = "";
		}

		form.idImovel.value = "";
		form.descricaoImovel.value = "";

		form.codigoCliente.value = "";
		form.nomeCliente.value = "";

		form.action = 'exibirInserirGuiaDevolucaoAction.do';
		form.submit();
   }
   
   function limparConta() {
		var form = document.InserirGuiaDevolucaoActionForm;
		form.referenciaConta.value = "";
		form.descricaoConta.value = "";
   }
   
   function limparGuia() {
		var form = document.InserirGuiaDevolucaoActionForm;
		form.idGuiaPagamento.value = "";
		form.descricaoGuiaPagamento.value = "";
		form.valorGuiaPagamento.value = "";
   }
   
   function limparDebitoACobrar() {
		var form = document.InserirGuiaDevolucaoActionForm;
		form.idDebitoACobrar.value = "";
		form.descricaoDebitoACobrar.value = "";
		form.valorDebitoACobrar.value = "";
   }
   
   function limparTipoDebito() {
		var form = document.InserirGuiaDevolucaoActionForm;
		form.idTipoDebito.value = "";
		form.idTipoDebitoHidden.value = "";
		form.descricaoTipoDebito.value = "";
   }
   
   function pesquisarConta(idImovel){
 
   	var form = document.forms[0];

     	if(idImovel.value == ""){
       		alert('Informe o imóvel para pesquisar as contas.');
     	}else{
     		abrirPopup('exibirPesquisarContaAction.do?idImovel='+idImovel.value , 400, 800);
     	}
 	}
 	
	function pesquisarGuiaPagamento(form){
   
    	if(form.idImovel.value != ""){
       		abrirPopup('exibirPesquisarGuiaPagamentoAction.do?idImovel='+form.idImovel.value , 500, 775);
     	}else if(form.codigoCliente.value != ""){
       		abrirPopup('exibirPesquisarGuiaPagamentoAction.do?idCliente='+form.codigoCliente.value , 500, 775);
     	}else{
       		alert('Informe o imóvel ou o cliente para pesquisar as guias de pagamento.');
     	}
   	}
   
	function pesquisarDebitoACobrar(idImovel){
 
		var form = document.forms[0];

     	if(idImovel.value == ""){
       		alert('Informe o imóvel para pesquisar os débitos a cobrar.');
     	}else{
     		abrirPopup('exibirPesquisarDebitoACobrarAction.do?idImovel='+idImovel.value , 400, 800);
     	}
 	}
   
   	function verificarTipoDocumentoAssociadoAImovel(form) {
      
      	form.idRegistroAtendimento.focus();
   		alert('Não há Imóvel associado ao Registro de Atendimento ' + form.idRegistroAtendimento.value + '. Não é possível selecionar este tipo de documento.');
   
   	}
   
   	function setarDocumentoTipoHidden(form) {
   		form.documentoTipoHidden.value = form.documentoTipo.value;
   	}
   
   	function validarForm(form) {
   
    	if(validateInserirGuiaDevolucaoActionForm(form)) {
     		
     		if (testarCampoValorZero(document.InserirGuiaDevolucaoActionForm.idRegistroAtendimento, 'Registro de Atendimento') &&
		     	testarCampoValorZero(document.InserirGuiaDevolucaoActionForm.idOrdemServico, 'Ordem de Serviço') &&
		     	testarCampoValorZero(document.InserirGuiaDevolucaoActionForm.idLocalidade, 'Localidade') &&
		     	testarCampoValorZero(document.InserirGuiaDevolucaoActionForm.referenciaConta, 'Referência da Conta') &&
		     	testarCampoValorZero(document.InserirGuiaDevolucaoActionForm.idGuiaPagamento, 'Guia de Pagamento') &&
		     	testarCampoValorZero(document.InserirGuiaDevolucaoActionForm.idDebitoACobrar, 'Débito a Cobrar') &&
		     	testarCampoValorZero(document.InserirGuiaDevolucaoActionForm.idTipoDebito, 'Tipo de Débito') &&
		     	testarCampoValorZero(document.InserirGuiaDevolucaoActionForm.valorDevolucao, 'Valor da Devolução') &&
     			verificaAnoMes(document.InserirGuiaDevolucaoActionForm.referenciaConta)) {
     			
     			submeterFormPadrao(form);
     		}
   		}
   	}
   
	function IntegerValidations () {
    	this.am = new Array("idRegistroAtendimento", "Registro de Atendimento deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     	this.an = new Array("idOrdemServico", "Ordem de Serviço deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
   

	function habilitarPesquisa(objeto,action) {
		if (objeto.disabled == false) {
			abrirPopup(action, 600, 500);
		}	
	}
   
  	function habilitaCampo(){
  		var form = document.forms[0];
  		
  		if((form.idOrdemServico.value != null && form.idOrdemServico.value != "") && 
  			(form.idRegistroAtendimento.value != null && form.idRegistroAtendimento.value != "" ) ){
  		
  			form.idRegistroAtendimento.disabled = true;
  		}
  	}
	
	function limparFuncionarioAnalista(){
  		var form = document.forms[0];
	  		
		form.idFuncionarioAnalista.value = "";
		form.nomeFuncionarioAnalista.value = "";
	  		
	}
	
	function limparFuncionarioAutorizador(){
  		var form = document.forms[0];
	  		
		form.idFuncionarioAutorizador.value = "";
		form.nomeFuncionarioAutorizador.value = "";
	}
	
	function bloquearOrdemServico(){
 		var form = document.forms[0];
		if(form.idRegistroAtendimento.value != ""){
			form.idOrdemServico.disabled = true;
		} else{
			form.idOrdemServico.disabled = false;

			form.idOrdemServico.value = "";
			form.nomeOrdemServico.value = "";
	
			//form.idTipoDebito.disabled = false;
			form.idTipoDebito.value = "";
			form.idTipoDebitoHidden.value = "";
			form.descricaoTipoDebito.value = "";

		}
	}
	
	function bloquearRegistroAtendimento(){
	 	var form = document.forms[0];
	
		if(form.idOrdemServico.value != ""){
			form.idRegistroAtendimento.disabled = true;
		}else{
			form.idRegistroAtendimento.disabled = false;
			form.idRegistroAtendimento.value = "";
			form.nomeRegistroAtendimento.value = "";
		}
	 
	}
	
	function limparTipoDocumento(){
	   	limparConta();
		limparGuia();
		limparDebitoACobrar();
		limparTipoDebito();
	}
   
</script>
</head>
<body>

<logic:present name="RegistroAtendimentoSemImovel" scope="request">
	<body leftmargin="5" topmargin="5" onload="javascript:habilitaCampo();verificarTipoDocumentoAssociadoAImovel(document.forms[0]);">
</logic:present>

<logic:notPresent name="RegistroAtendimentoSemImovel" scope="request">
	<body leftmargin="5" topmargin="5" onload="javascript:habilitaCampo();setarFoco('${requestScope.nomeCampo}');">
</logic:notPresent>

<html:form action="/inserirGuiaDevolucaoAction"
	name="InserirGuiaDevolucaoActionForm" method="post"
	type="gcom.gui.arrecadacao.InserirGuiaDevolucaoActionForm">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<html:hidden property="idTipoDebitoHidden" />
	<html:hidden property="idLocalidadeHidden" />
	<html:hidden property="documentoTipoHidden" />

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
			<td valign="top" class="centercoltext"><!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Inserir Guia de Devolu&ccedil;&atilde;o</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="4">Para inserir a guia de devolu&ccedil;&atilde;o,
					informe os dados abaixo:</td>
				</tr>
				
				<tr>
					<td height="18" colspan="4">
					<hr>
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>Registro de Atendimento:<font color="#FF0000">*</font></strong>
					</td>
					
					<td colspan="2">
						<div>
						<html:text property="idRegistroAtendimento" 
							size="9"
							maxlength="9"
							onfocus="javascript:bloquearOrdemServico();"
							onkeyup="javascript:bloquearOrdemServico();"
							onkeypress="javascript:limparRegistroAtendimentoTecla();validaEnter(event, 'exibirInserirGuiaDevolucaoAction.do', 'idRegistroAtendimento')" />
							
							<a href="javascript:javascript:habilitarPesquisa(document.forms[0].idRegistroAtendimento,'exibirPesquisarRegistroAtendimentoAction.do');">
								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar RA" /></a> 

							<logic:present name="numeroRAEncontrada" scope="request">
								<html:text property="nomeRegistroAtendimento" 
									size="43"
									maxlength="43" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:present> 

							<logic:notPresent name="numeroRAEncontrada" scope="request">
								<html:text property="nomeRegistroAtendimento" 
									size="43"
									maxlength="43" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />
							</logic:notPresent>
						
							<a href="javascript:limparForm();">
								<img border="0" 
									title="Apagar"
									src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<strong>Ordem de Servi&ccedil;o:</strong>
					</td>
					
					<td colspan="2">
						<div>
							<html:text property="idOrdemServico" 
								size="9" 
								maxlength="9"
								onfocus="javascript:bloquearRegistroAtendimento();"
								onkeyup="javascript:bloquearRegistroAtendimento();"
								onkeypress="javascript:limparOrdemServicoTecla();validaEnter(event, 'exibirInserirGuiaDevolucaoAction.do', 'idOrdemServico')" />
							
							<a href="javascript:javascript:habilitarPesquisa(document.forms[0].idOrdemServico,'exibirPesquisarOrdemServicoAction.do');">
								<img width="23" 
									height="21"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									alt="Pesquisar" 
									border="0" /></a>
							
							<logic:present name="ordemServicoEncontrada" scope="request">
								<html:text property="nomeOrdemServico" 
									size="43"
									maxlength="43" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:present> 

							<logic:notPresent name="ordemServicoEncontrada" scope="request">
								<html:text property="nomeOrdemServico" 
									size="43"
									maxlength="43" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />
							</logic:notPresent>

							<a href="javascript:limparOrdemServico();">
								<img border="0" 
									title="Apagar"
									src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
						</div>
					</td>
				</tr>
				<tr>
					<td height="18" colspan="4">
					<hr>
					</td>
				</tr>
				<tr>
					<td>
						<strong>Tipo do Documento:<font color="#FF0000">*</font></strong>
					</td>
					
					<td>
						<strong>

						<logic:present name="bloqueioDocumentoTipo" scope="request">
							<html:select property="documentoTipo"
								onchange="javascript:if (validateInteger(document.InserirGuiaDevolucaoActionForm) && testarCampoValorZero(document.InserirGuiaDevolucaoActionForm.idRegistroAtendimento, 'Registro de Atendimento') && testarCampoValorZero(document.InserirGuiaDevolucaoActionForm.idOrdemServico, 'Ordem de Serviço')){setarDocumentoTipoHidden(document.forms[0]);limparTipoDocumento();redirecionarSubmit('exibirInserirGuiaDevolucaoAction.do');}"
								disabled="true">
								<html:option value="-1">&nbsp;</html:option>
								<html:options collection="colecaoDocumentoTipo" labelProperty="descricaoDocumentoTipo" property="id" />
							</html:select>
						</logic:present>
	
						<logic:notPresent name="bloqueioDocumentoTipo" scope="request">
							<html:select property="documentoTipo"
								onchange="if (validateInteger(document.InserirGuiaDevolucaoActionForm) && testarCampoValorZero(document.InserirGuiaDevolucaoActionForm.idRegistroAtendimento, 'Registro de Atendimento') && testarCampoValorZero(document.InserirGuiaDevolucaoActionForm.idOrdemServico, 'Ordem de Serviço')){setarDocumentoTipoHidden(document.forms[0]);limparTipoDocumento();redirecionarSubmit('exibirInserirGuiaDevolucaoAction.do');}">
								
								<html:option value="-1">&nbsp;</html:option>
								<html:options collection="colecaoDocumentoTipo" labelProperty="descricaoDocumentoTipo" property="id" />
							</html:select>
						</logic:notPresent>
						</strong>
					</td>
				</tr>
				
				<tr>
					<td height="18" colspan="4">
					<hr>
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>Localidade:</strong>
					</td>
					
					<td colspan="2">
						<logic:present name="bloqueioLocalidade" scope="request">
							<html:text property="idLocalidade" 
								maxlength="9" 
								size="9"
								disabled="true" />
							
							<img width="23" 
								height="21"
								src="<bean:message key='caminho.imagens'/>pesquisa.gif"
								border="0" />
						</logic:present> 

						<logic:notPresent name="bloqueioLocalidade" scope="request">
							<html:text property="idLocalidade" 
								maxlength="9" 
								size="9"
								onkeyup="limparLocalidadeTecla();validaEnter(event, 'exibirInserirGuiaDevolucaoAction.do', 'idLocalidade');" />
							<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do', 400, 800);">
								<img width="23" 
									height="21"
									src="<bean:message key='caminho.imagens'/>pesquisa.gif"
									border="0" /></a>
						</logic:notPresent>
						
						<logic:present name="localidadeInexistente" scope="request">

							<html:text property="descricaoLocalidade" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								size="40" 
								maxlength="40" />
						</logic:present>

						<logic:notPresent name="localidadeInexistente" scope="request">
							<html:text property="descricaoLocalidade" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" 
								size="40"
								maxlength="40" />
						</logic:notPresent> 

						<logic:present name="bloqueioLocalidade" scope="request">
							<img border="0"
								src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
						</logic:present> 

						<logic:notPresent name="bloqueioLocalidade" scope="request">
							<a href="javascript:limparLocalidade();"> 
								<img border="0" 
									title="Apagar"
								src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
						</logic:notPresent>
					</td>
				</tr>

				<tr>
					<td>
						<strong>Matrícula do Imóvel:</strong>
					</td>
					
					<td colspan="2">
						<html:text property="idImovel" 
							maxlength="9"
							size="9" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" /> 
						
						<html:text property="descricaoImovel" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" 
							size="50"
							maxlength="50" />
					</td>
				</tr>

				<tr>
					<td>
						<strong>Código do Cliente:</strong>
					</td>
					<td colspan="2">
						<html:text property="codigoCliente" 
							maxlength="9"
							size="9" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" /> 
						
						<html:text property="nomeCliente" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" 
							size="50"
							maxlength="50" />
					</td>
				</tr>

				<tr>
					<td height="18" colspan="4">
					<hr>
					</td>
				</tr>

				<tr>
					<td>
						<strong>Refer&ecirc;ncia da Conta:</strong>
					</td>
					
					<td colspan="2">

						<logic:present name="bloqueioConta" scope="request">

							<html:text property="referenciaConta" 
								size="7" 
								maxlength="7"
								disabled="true" />

							<img width="23" 
								height="21"
								src="<bean:message key='caminho.imagens'/>pesquisa.gif"
								border="0" 
								readonly="true" />
								
						</logic:present> 
						
						<logic:notPresent name="bloqueioConta" scope="request">
							<html:text property="referenciaConta" 
								size="7" 
								maxlength="7"
								onkeyup="javascript:mascaraAnoMes(this,event);limparContaTecla();validaEnterString(event, 'exibirInserirGuiaDevolucaoAction.do', 'referenciaConta');" />
							
							<a href="javascript:pesquisarConta(document.forms[0].idImovel);">
								<img width="23" 
									height="21"
									src="<bean:message key='caminho.imagens'/>pesquisa.gif"
									border="0" /></a>
						</logic:notPresent>mm/aaaa
						
						<logic:equal name="contaInexistente" value="true" scope="request">
							<html:text property="descricaoConta" 
								readonly="true" 
								size="35"
								maxlength="35"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal> 
						
						<logic:notEqual name="contaInexistente" value="true" scope="request">
							<html:text property="descricaoConta"
								style="background-color:#EFEFEF; border:0; color: #000000; text-align:right;"
								readonly="true" 
								size="35" 
								maxlength="35" />
						</logic:notEqual>
						
						<a href="javascript:limparConta();">
							<img border="0" 
								title="Apagar"
								src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
					</td>
				</tr>
				
				<tr>
					<td>
						<strong> Guia de Pagamento:</strong>
					</td>
					
					<td colspan="2">
						<logic:present name="bloqueioGuia" scope="request">
							<html:text property="idGuiaPagamento" 
								disabled="true" 
								size="9"
								maxlength="9" />
							
							<img width="23" 
								height="21"
								src="<bean:message key='caminho.imagens'/>pesquisa.gif"
								border="0" />
						</logic:present> 
						
						<logic:notPresent name="bloqueioGuia" scope="request">
							<html:text property="idGuiaPagamento" 
								size="9" 
								maxlength="9"
								onkeyup="javascript:limparGuiaPagamentoTecla();validaEnter(event, 'exibirInserirGuiaDevolucaoAction.do', 'idGuiaPagamento');" />
							
							<a href="javascript:pesquisarGuiaPagamento(document.forms[0]);">
								<img width="23" 
									height="21"
									src="<bean:message key='caminho.imagens'/>pesquisa.gif"
									border="0" /></a>
						</logic:notPresent> 
						
						<logic:equal name="guiaPagamentoInexistente" value="true" scope="request">
							<html:text property="descricaoGuiaPagamento" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color:#ff0000"
								size="25" 
								maxlength="30" />
						</logic:equal>
						
						<logic:notEqual name="guiaPagamentoInexistente" value="true" scope="request">
							<html:text property="descricaoGuiaPagamento" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color:#000000"
								size="25" 
								maxlength="30" />
						</logic:notEqual> 
						
						<html:text property="valorGuiaPagamento"
							style="background-color:#EFEFEF; border:0; text-align:right;"
							readonly="true" 
							size="14" 
							maxlength="14" />
						
						<a href="javascript:limparGuia();">
							<img border="0" 
								title="Apagar"
								src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
					</td>
				</tr>
				<tr>
					<td>
						<strong> D&eacute;bito A Cobrar:</strong>
					</td>
					
					<td colspan="2">
						<logic:present name="bloqueioDebitoACobrar" scope="request">
							<html:text property="idDebitoACobrar" 
								disabled="true" 
								size="9"
								maxlength="9" />
								
							<img width="23" 
								height="21"
								src="<bean:message key='caminho.imagens'/>pesquisa.gif"
								border="0" />
								
						</logic:present> 
						
						<logic:notPresent name="bloqueioDebitoACobrar" scope="request">
							<html:text property="idDebitoACobrar" 
								size="9" 
								maxlength="9"
								onkeyup="javascript:limparDebitoACobrarTecla();validaEnter(event, 'exibirInserirGuiaDevolucaoAction.do', 'idDebitoACobrar');" />
							<a href="javascript:pesquisarDebitoACobrar(document.forms[0].idImovel);">
								<img width="23" 
									height="21"
									src="<bean:message key='caminho.imagens'/>pesquisa.gif"
									border="0" /></a>
						</logic:notPresent>
						
						<logic:equal name="debitoACobrarInexistente" value="true" scope="request">
							<html:text property="descricaoDebitoACobrar" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								size="25" 
								maxlength="30" />
						</logic:equal>
						
						<logic:notEqual name="debitoACobrarInexistente" value="true" scope="request">
							<html:text property="descricaoDebitoACobrar" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="25" 
								maxlength="30" />
						</logic:notEqual> 
						
						<html:text property="valorDebitoACobrar"
							style="background-color:#EFEFEF; border:0; text-align:right;"
							readonly="true" 
							size="14" 
							maxlength="14" />
						
						<a href="javascript:limparDebitoACobrar();">
							<img border="0" 
								title="Apagar"
								src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
					</td>
				</tr>
				<tr>
					<td>
						<strong>Tipo do D&eacute;bito:<font color="#FF0000">*</font></strong>
					</td>
					
					<td>
						<logic:present name="bloqueioDebitoTipo" scope="request">
							<html:text property="idTipoDebito" 
								disabled="true"
								size="4"
								maxlength="4" />
							<img width="23" 
								height="21"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								alt="Pesquisar" />
						</logic:present> 
						
						<logic:notPresent name="bloqueioDebitoTipo" scope="request">
							<html:text property="idTipoDebito" 
								size="4" 
								maxlength="4"
								onkeyup="javascript:limparTipoDebitoTecla();validaEnter(event, 'exibirInserirGuiaDevolucaoAction.do', 'idTipoDebito');" />
							<a href="javascript:abrirPopup('exibirPesquisarTipoDebitoAction.do', 400, 800);">
								<img width="23" 
									height="21"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									alt="Pesquisar" 
									border="0" /></a>
						</logic:notPresent> 
						
						<logic:present name="tipoDebitoInexistente" scope="request">
							<html:text property="descricaoTipoDebito" 
								size="40" 
								maxlength="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:present> 
						
						<logic:notPresent name="tipoDebitoInexistente" scope="request">
							<html:text property="descricaoTipoDebito" 
								size="40" 
								maxlength="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent> 
						
						<logic:present name="bloqueioDebitoTipo" scope="request">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" />
						</logic:present>
						
						<logic:notPresent name="bloqueioDebitoTipo" scope="request">
							<a href="javascript:limparTipoDebito();">
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" />
							</a>
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td height="18" colspan="4">
					<hr>
					</td>
				</tr>

				<tr>
					<td>
						<strong>Valor da Devolu&ccedil;&atilde;o:<font color="#FF0000">*</font></strong>
					</td>
					
					<td><strong> 
						<html:text property="valorDevolucao" 
							size="14"
							maxlength="14" 
							onkeyup="formataValorMonetario(this, 14)"
							style="text-align:right;" /></strong>
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>Funcion&aacute;rio Analista<font color="#FF0000">*</font></strong>
					</td>
					
					<td colspan="2">
						<div>
							<html:text property="idFuncionarioAnalista" 
								size="9" 
								maxlength="9"
								onkeyup="javascript:validaEnterComMensagem(event, 'exibirInserirGuiaDevolucaoAction.do', 'idFuncionarioAnalista','Funcionário Analista')" />
							
							<a href="javascript:javascript:tipoFuncionario=1;habilitarPesquisa(document.forms[0].idFuncionarioAnalista,'exibirFuncionarioPesquisar.do');">
								<img width="23" 
									height="21"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									alt="Pesquisar" 
									border="0" /></a>
							
							<logic:present name="funcionarioAnalistaEncontrado" scope="request">
								<html:text property="nomeFuncionarioAnalista" 
									size="43"
									maxlength="43" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:present> 

							<logic:notPresent name="funcionarioAnalistaEncontrado" scope="request">
								<html:text property="nomeFuncionarioAnalista" 
									size="43"
									maxlength="43" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />
							</logic:notPresent>

							<a href="javascript:limparFuncionarioAnalista();">
								<img border="0" 
									title="Apagar"
									src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
						</div>
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>Funcion&aacute;rio Autorizador<font color="#FF0000">*</font></strong>
					</td>
					
					<td colspan="2">
						<div>
							<html:text property="idFuncionarioAutorizador" 
								size="9" 
								maxlength="9"
								onkeyup="javascript:validaEnterComMensagem(event, 'exibirInserirGuiaDevolucaoAction.do', 'idFuncionarioAutorizador','Funcionário Autorizador')" />
							
							<a href="javascript:javascript:tipoFuncionario=2;habilitarPesquisa(document.forms[0].idFuncionarioAnalista,'exibirFuncionarioPesquisar.do');">
								<img width="23" 
									height="21"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									alt="Pesquisar" 
									border="0" /></a>
							
							<logic:present name="funcionarioAutorizadorEncontrado" scope="request">
								<html:text property="nomeFuncionarioAutorizador" 
									size="43"
									maxlength="43" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:present> 

							<logic:notPresent name="funcionarioAutorizadorEncontrado" scope="request">
								<html:text property="nomeFuncionarioAutorizador" 
									size="43"
									maxlength="43" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />
							</logic:notPresent>

							<a href="javascript:limparFuncionarioAutorizador();">
								<img border="0" 
									title="Apagar"
									src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
						</div>
					</td>
				</tr>

				<tr>
					<td><strong></strong></td>
					<td colspan="3"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios <strong> </strong></td>
				</tr>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" 
							name="ButtonReset"
							class="bottonRightCol" 
							value="Desfazer" 
							onClick="limparForm();"> 
						
						<input type="button" 
							name="ButtonCancelar" 
							class="bottonRightCol"
							value="Cancelar"
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>

					<td>
					  	<gsan:controleAcessoBotao name="Button" 
						  	value="Inserir" 
						  	onclick="javascript:validarForm(document.forms[0]);" 
						  	url="inserirGuiaDevolucaoAction.do"/>
					  	
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
	<p>&nbsp;</p>
</html:form>
</body>
</html:html>
