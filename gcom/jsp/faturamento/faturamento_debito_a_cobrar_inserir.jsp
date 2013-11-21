<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="InserirDebitoACobrarActionForm" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>



<SCRIPT LANGUAGE="JavaScript">
<!--


function limparRegistroAtendimento(){
	var form = document.forms[0];
///	alert("form.registroAtendimento.disabled="+form.registroAtendimento.disabled);
	if(form.registroAtendimento.disabled == false){
		form.registroAtendimento.value = "";
		form.nomeRegistroAtendimento.value = "";
		form.ordemServico.disabled = false;
		form.ordemServico.value = "";
		form.nomeOrdemServico.value = "";
		if(form.idImovel != null){
		  form.idImovel.disabled = false;
		  form.idImovel.value = "";
		  form.inscricaoImovelInformada.value = ""; 
		}
		limparImovel();
	}
}

function pesquisarRegistroAtendimento(){
	var form = document.forms[0];
	if(form.registroAtendimento.disabled == false){
		abrirPopup('exibirPesquisarRegistroAtendimentoAction.do', 400, 800);
	}
}


function limparOrdemServico(){
	var form = document.forms[0];
	
	if(form.ordemServico.disabled == false){
		form.ordemServico.value = "";
		form.nomeOrdemServico.value = "";
		form.registroAtendimento.disabled = false;
		form.registroAtendimento.value = "";
		form.nomeRegistroAtendimento.value = "";
		form.idTipoDebito.disabled = false;
		form.idTipoDebito.value = "";
		form.descricaoTipoDebito.value = "";
	    if(form.idImovel != null){
		  form.idImovel.disabled = false;
		  form.idImovel.value = "";
		  form.inscricaoImovelInformada.value = ""; 
		}
		limparImovel();
	}
}
function pesquisarOrdemServico(){
	var form = document.forms[0];
	if(form.ordemServico.disabled == false){
		abrirPopup('exibirPesquisarOrdemServicoAction.do', 400, 800);
	}
}
	
  
function limparCalculoPrestacao(){
	var form = document.forms[0];
	form.valorPrestacao.value = "";
	form.valorTotalServicoAParcelar.value = "";
	form.valorJuros.value = "";
}

function pesquisarTipoDebito(){
	var form = document.forms[0];
	if(form.idTipoDebito.disabled == false){
		limparCalculoPrestacao();abrirPopup('exibirPesquisarTipoDebitoAction.do?limparForm=1&tipoFinanciamentoServico=SIM', 500, 600);	
	}
}

function limparTipoDebito(){
	var form = document.forms[0];
	if(form.idTipoDebito.disabled == false){
		form.idTipoDebito.value = "";
		form.descricaoTipoDebito.value = "";
		form.valorTotalServico.value = "";
		
		limparCalculoPrestacao();
	}
}

function limparIdImovel(){
	var form = document.forms[0];
	
	if(form.idImovel.disabled == false){
		form.idImovel.value = "";
		form.inscricaoImovelInformada.value = "";
		form.ordemServico.value = "";
		form.nomeOrdemServico.value = "";
		form.ordemServico.disabled = false;
		form.registroAtendimento.disabled = false;
		form.registroAtendimento.value = "";
		form.nomeRegistroAtendimento.value = "";
		form.idTipoDebito.disabled = false;
		form.idTipoDebito.value = "";
		form.descricaoTipoDebito.value = "";
		limparImovel();
	}
}

	  	
-->    
</script>

<script language="JavaScript">
<!-- Begin
function DecimalValidations () {
     this.ae = new Array("taxaJurosFinanciamento", "Taxa de Juros do Financiamento possui caracteres especiais.", new Function ("varName", " return this[varName];"));
}

function IntegerValidations  () {
///    this.am = new Array("codigoImovel", "Matrícula do Imóvel deve somente conter números positivos.", new Function ("varName", " return this[varName];"));

    this.am = new Array("ordemServico", "Ordem de Serviço deve somente conter números positivos.", new Function ("varName", " return this[varName];"));        
    this.an = new Array("registroAtendimento", "Registro de Atendimento deve somente conter números positivos.", new Function ("varName", " return this[varName];"));        
    this.aq = new Array("idTipoDebito", "Tipo de Débito deve somente conter números positivos.", new Function ("varName", " return this[varName];"));        
    this.an = new Array("numeroPrestacoes", "Número de Prestações deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
}
	
	
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'imovel') {
      limparForm();
      form.idImovel.value = codigoRegistro;
      form.action = 'exibirInserirDebitoACobrarAction.do?objetoConsulta=4'
      form.submit();
    }
    if (tipoConsulta == 'tipoDebito') {
      form.idTipoDebito.value = codigoRegistro;
      form.descricaoTipoDebito.value = descricaoRegistro;
      form.descricaoTipoDebito.style.color = "#000000";
	  form.action = 'exibirInserirDebitoACobrarAction.do?objetoConsulta=1'
      form.submit();
    }

    if (tipoConsulta == 'ordemServico') {
      form.ordemServico.value = codigoRegistro;
      form.nomeOrdemServico.value = descricaoRegistro;
      form.nomeOrdemServico.style.color = "#000000";
      form.action = 'exibirInserirDebitoACobrarAction.do?objetoConsulta=3'
      form.submit();

    }
    if (tipoConsulta == 'registroAtendimento') {
      form.registroAtendimento.value = codigoRegistro;
      form.nomeRegistroAtendimento.value = descricaoRegistro;
      form.nomeRegistroAtendimento.style.color = "#000000";
      form.action = 'exibirInserirDebitoACobrarAction.do?objetoConsulta=2'
      form.submit();
    }

   
}


function limparForm(){
 	var form = document.forms[0];
 	
 	for (var i=0;i < form.elements.length;i++){
		var elemento = form.elements[i];
		if (elemento.type == "text"){
			elemento.value = "";
		}
	}
	
	redirecionarSubmit("exibirInserirDebitoACobrarAction.do?menu=sim");
}
 
 
 function limparImovel(){
 	var form = document.forms[0];
	form.codigoImovel.value = '';
	form.inscricaoImovel.value = '';
	form.nomeCliente.value = '';
	form.situacaoAgua.value = '';
	form.situacaoEsgoto.value = '';
 }
 
function  limparValorEntrada(){
 	var form = document.forms[0];
	form.valorEntrada.value = '';
 }
function validarForm(form, inserir){
	
		var condicao = true;
	 	var form = document.forms[0];
		
		if(form.idImovel != null){
		  if(form.ordemServico.value == "" && form.registroAtendimento.value == '' && form.idImovel.value == ""){		      
			  alert("Informe Registro de Atendimento, Ordem de Serviço ou Imóvel");
			  form.btnInserir.disabled = false;		  
			  form.registroAtendimento.focus();
			  condicao = false;
		  }
		}else{
		  if(form.ordemServico.value == "" && form.registroAtendimento.value == ''){
			  alert("Informe Registro de Atendimento ou Ordem de Serviço");
			  form.btnInserir.disabled = false;		  
			  form.registroAtendimento.focus();
			  condicao = false;
		  }
		}
		
		if(form.idImovel != null){
		  if(condicao){		
			if(form.ordemServico.value == "" && form.registroAtendimento.value != '' && form.idTipoDebito.value == ""){
				alert("Informe Débito Tipo");
				form.btnInserir.disabled = false;		  
				form.idTipoDebito.focus();
				condicao = false;
			}
			if(form.ordemServico.value == "" && form.registroAtendimento.value == "" && form.idImovel.value != "" && form.idTipoDebito.value == ""){			    
				alert("Informe Débito Tipo");
				form.btnInserir.disabled = false;		  
				form.idTipoDebito.focus();
				condicao = false;
			}
		  }
		}else{
		  if(condicao){		
			if(form.ordemServico.value == "" && form.registroAtendimento.value != '' && form.idTipoDebito.value == ""){
				alert("Informe Débito Tipo");
				form.btnInserir.disabled = false;		  
				form.idTipoDebito.focus();
				condicao = false;
			}
		  }		
		}
		
	if(condicao){
		if (validateInserirDebitoACobrarActionForm(form)){
			if (inserir){
				urlRedirect = "/gsan/inserirDebitoACobrarAction.do";
			}else{
				urlRedirect = "/gsan/calcularPrestacaoDebitoACobrarAction.do";
			}
			var vlEntrada = returnObject(form,"valorEntrada");
			var vlTotalServico = returnObject(form,"valorTotalServico");
	
			var vlEntr = vlEntrada.value;
			vlEntr = vlEntr.replace('.','');
			vlEntr = vlEntr.replace(',','.');
			
			var vlTotServ = vlTotalServico.value;
			vlTotServ = vlTotServ.replace('.','');
			vlTotServ = vlTotServ.replace(',','.');
			
			
			if( (vlEntr*1) > (vlTotServ*1) ){			
				alert("Valor de Entrada deve ser inferior a Valor Total do Serviço.");
				form.btnInserir.disabled = false;		  
				form.vlEntrada.focus();
			}else if( (vlEntr*1) == (vlTotServ*1) ){
				alert("Valor de Entrada deve ser inferior a Valor Total do Serviço.");
				form.btnInserir.disabled = false;		  
				form.vlEntrada.focus();
			}else if(form.codigoImovel.value.length < 1){
				if(form.ordemServico.value == "" && form.registroAtendimento.value != ''){
					alert("Dados do Registro de Atendimento não pesquisados. Deseja pesquisar agora?")
					form.btnInserir.disabled = false;		  
					validaEnterSemEvento('exibirInserirDebitoACobrarAction.do?objetoConsulta=2', 'registroAtendimento');
				}
				if(form.ordemServico.value != "" && form.registroAtendimento.value == ''){
					alert("Dados da Ordem de Servico não pesquisados. Deseja pesquisar agora?")
					form.btnInserir.disabled = false;		  
					validaEnterSemEvento('exibirInserirDebitoACobrarAction.do?objetoConsulta=3', 'ordemServico');
				}
			}else if(form.inscricaoImovel.value.length < 1){
				exibirDadosImovel(form);
			}else if(form.valorTotalServico.value.length < 1){
				alert("Informe Valor Total Servico.");
				form.btnInserir.disabled = false;		  
				form.codigoImovel.focus();
			}else if(form.idTipoDebito.value.length < 1){
				alert("Informe Tipo de Débito.");
				form.btnInserir.disabled = false;		  
				form.idTipoDebito.focus();
			}else if(form.idTipoDebito.value.length < 1){
				alert("Realize Calculo da Prestação.");
				form.btnInserir.disabled = false;		  
				form.calcularPrestacao.focus();
			}else if((inserir) && (form.valorPrestacao.value.length < 1)){	
				alert("Realize Calculo da Prestação.");
				form.btnInserir.disabled = false;		  
				form.calcularPrestacao.focus();
			}else{
				redirecionarSubmit(urlRedirect);
			}
		}
	}	
}
 

function exibirDadosImovel(form){

	codigoImovel = form.codigoImovel.value;
	inscricaoImovel = form.inscricaoImovel.value;

	if (codigoImovel.length > 0 && inscricaoImovel.length < 1){
		
		alert("Dados do Imóvel não pesquisados. Deseja pesquisar agora?")
		validaEnterSemEvento('exibirInserirDebitoACobrarAction.do', 'codigoImovel');	
		
	}
	
}
 
function bloquearOrdemServico(){
 	var form = document.forms[0];
	if(form.registroAtendimento.value != ""){
		form.ordemServico.disabled = true;
		if(form.idImovel != null){
		  form.idImovel.disabled = true;
		}
	} else{
		form.ordemServico.disabled = false;		
    	limparOrdemServico();
	}
}

function bloquearRegistroAtendimento(){
	 	var form = document.forms[0];
	if(form.ordemServico.value != ""){
		form.registroAtendimento.disabled = true;
		if(form.idImovel != null){
		  form.idImovel.disabled = true;
		}
	}else{
		form.registroAtendimento.disabled = false;		
		form.idTipoDebito.disabled = false;	
		limparRegistroAtendimento();
		limparTipoDebito();		
	}
	 
}

function bloquearOrdemServicoRa(){
	var form = document.forms[0];
	if(form.idImovel.value != ""){
		form.registroAtendimento.disabled = true;
		form.ordemServico.disabled = true;
	}else{
		form.registroAtendimento.disabled = false;
		form.ordemServico.disabled = false;	
		limparRegistroAtendimento();
    	limparOrdemServico();
	}	 
}

 
-->
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/exibirInserirDebitoACobrarAction"
	name="InserirDebitoACobrarActionForm" method="post"
	type="gcom.gui.faturamento.InserirDebitoACobrarActionForm"
	onsubmit="document.forms[0].btnInserir.disabled=false; return validateInserirDebitoACobrarActionForm(this);">

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
			<p>&nbsp;</p>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Inserir D&eacute;bito a Cobrar</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>Para inserir o d&eacute;bito a cobrar, informe os dados abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoDebitoCobrarInserir', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td width="180"><strong>Registro de Atendimento:<font
						color="#FF0000"></font></strong></td>
					<td colspan="3" align="right">
					<div align="left"><logic:present name="travarRegistroAtendimento">
						<html:text property="registroAtendimento" maxlength="9" size="9"
							tabindex="1" onfocus="javascript:bloquearOrdemServico();"
							onkeyup="javascript:bloquearOrdemServico();"
							onkeypress="validaEnterComMensagem(event, 'exibirInserirDebitoACobrarAction.do?objetoConsulta=2', 
							'registroAtendimento','Registro de Atendimento');" />
					</logic:present> <logic:notPresent name="travarRegistroAtendimento">
						<html:text property="registroAtendimento" disabled="true"
							maxlength="9" size="9" tabindex="1"
							onfocus="javascript:bloquearOrdemServico();"
							onkeyup="javascript:bloquearOrdemServico();"
							onkeypress="validaEnterComMensagem(event, 'exibirInserirDebitoACobrarAction.do?objetoConsulta=2', 
							'registroAtendimento','Registro de Atendimento');" />
					</logic:notPresent> <a
						href="javascript:pesquisarRegistroAtendimento();"> <img width="23"
						height="21"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
					<logic:present name="corRegistroAtendimento">
						<logic:equal name="corRegistroAtendimento" value="exception">
							<html:text property="nomeRegistroAtendimento" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								size="40" maxlength="40" />
						</logic:equal>
						<logic:notEqual name="corRegistroAtendimento" value="exception">
							<html:text property="nomeRegistroAtendimento" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="corRegistroAtendimento">
						<logic:empty name="InserirDebitoACobrarActionForm"
							property="registroAtendimento">
							<html:text property="nomeRegistroAtendimento" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="InserirDebitoACobrarActionForm"
							property="registroAtendimento">
							<html:text property="nomeRegistroAtendimento" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a
						href="javascript:limparRegistroAtendimento();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Registro de Atendimento" /> </a></div>
					</td>
				</tr>
				<tr>
					<td width="180"><strong>Ordem de Servi&ccedil;o:</strong></td>
					<td colspan="3" align="left">
					<div align="left"><logic:present name="travarOrdemServico">
						<html:text property="ordemServico" maxlength="9" size="9"
							tabindex="1" onfocus="javascript:bloquearRegistroAtendimento();"
							onkeyup="javascript:bloquearRegistroAtendimento();"
							onkeypress="validaEnterComMensagem(event, 'exibirInserirDebitoACobrarAction.do?objetoConsulta=3', 
							'ordemServico','Ordem de Serviço');" />
					</logic:present> <logic:notPresent name="travarOrdemServico">
						<html:text property="ordemServico" maxlength="9" disabled="true"
							size="9" tabindex="1"
							onfocus="javascript:bloquearRegistroAtendimento();"
							onkeyup="javascript:bloquearRegistroAtendimento();"
							onkeypress="validaEnterComMensagem(event, 'exibirInserirDebitoACobrarAction.do?objetoConsulta=3', 
							'ordemServico','Ordem de Serviço');" />
					</logic:notPresent> <a href="javascript:pesquisarOrdemServico();">
					<img width="23" height="21"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
					<logic:present name="corNomeOrdemServico">
						<logic:equal name="corNomeOrdemServico" value="exception">
							<html:text property="nomeOrdemServico" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								size="40" maxlength="40" />
						</logic:equal>
						<logic:notEqual name="corNomeOrdemServico" value="exception">
							<html:text property="nomeOrdemServico" size="40" maxlength="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="corNomeOrdemServico">
						<logic:empty name="InserirDebitoACobrarActionForm"
							property="ordemServico">
							<html:text property="nomeOrdemServico" size="40" maxlength="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="InserirDebitoACobrarActionForm"
							property="ordemServico">
							<html:text property="nomeOrdemServico" size="40" maxlength="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a href="javascript:limparOrdemServico();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Ordem de Serviço" /> </a></div>
					</td>
				</tr>
				<logic:present name="informarImovel">
				<tr>
					<td width="180"><strong>Matrícula do Imóvel:</strong></td>
					<td colspan="3" align="left"><logic:present name="travarImovel">
						<html:text property="idImovel" maxlength="9" size="9"
							tabindex="1" onfocus="javascript:bloquearOrdemServicoRa();"
							onkeyup="javascript:bloquearOrdemServicoRa();"
							onkeypress="validaEnterComMensagem(event, 'exibirInserirDebitoACobrarAction.do?objetoConsulta=4', 
							'idImovel','Imóvel');" />
					</logic:present> 
					<logic:notPresent name="travarImovel">
					<html:text property="idImovel" maxlength="9" size="9" disabled="true"
						onfocus="javascript:bloquearOrdemServicoRa();"
						onkeyup="javascript:bloquearOrdemServicoRa();"
						onkeypress="validaEnterComMensagem(event, 'exibirInserirDebitoACobrarAction.do?objetoConsulta=4', 
							'idImovel','Imóvel');" />
					</logic:notPresent>
					<a
						href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);"
						tabindex="1"> <img width="23" height="21"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0"
						title="Pesquisar Imóvel" /></a> 
					<logic:present name="corInscricao">

						<logic:equal name="corInscricao" value="exception">
							<html:text property="inscricaoImovelInformada" size="40" maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corInscricao" value="exception">
							<html:text property="inscricaoImovelInformada" size="40" maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> 
					<logic:notPresent name="corInscricao">

						<logic:empty name="InserirDebitoACobrarActionForm" property="idImovel">
							<html:text property="inscricaoImovelInformada" size="40" maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="InserirDebitoACobrarActionForm" property="idImovel">
							<html:text property="inscricaoImovelInformada" size="40" maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a href="javascript:limparIdImovel();" tabindex="1">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif"
						style="cursor: hand;" title="Apagar Imóvel" /> </a></td>
				</tr>
				</logic:present>
				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
							<td><strong>Dados do Imóvel:</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" border="0">
								<tr>
									<td height="10"><strong>Matrícula:</strong></td>
									<td><html:text property="codigoImovel" size="45"
										readonly="true" style="background-color:#EFEFEF; border:0" />
									</td>
								</tr>
								<tr>
									<td height="10"><strong>Inscrição:</strong></td>
									<td><html:text property="inscricaoImovel" size="45"
										readonly="true" style="background-color:#EFEFEF; border:0" />
									</td>
								</tr>

								<tr>
									<td height="10"><strong>Nome do Cliente Usuário:</strong></td>
									<td><html:text property="nomeCliente" size="45" readonly="true"
										style="background-color:#EFEFEF; border:0" /></td>
								</tr>
								<tr>
									<td height="10"><strong>Situação de Água:</strong></td>
									<td><html:text property="situacaoAgua" size="45"
										readonly="true" style="background-color:#EFEFEF; border:0" />
									</td>
								</tr>
								<tr>
									<td height="10"><strong>Situação de Esgoto:</strong></td>
									<td><html:text property="situacaoEsgoto" size="45"
										readonly="true" style="background-color:#EFEFEF; border:0" />
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
					<hr>
					</td>
				</tr>

				<tr>
					<td width="180"><strong>Tipo de Débito:</strong><font
						color="#FF0000">*</font></td>
					<td colspan="3" align="right">
					<div align="left"><logic:present name="travarDebitoTipo">
						<html:text property="idTipoDebito" size="9"
							onkeypress="validaEnter(event, 'exibirInserirDebitoACobrarAction.do?objetoConsulta=1', 'idTipoDebito');"
							maxlength="9" tabindex="4" onkeyup="limparCalculoPrestacao();limparValorEntrada();" />
					</logic:present> <logic:notPresent name="travarDebitoTipo">
						<html:text property="idTipoDebito" size="9" disabled="true"
							onkeypress="validaEnter(event, 'exibirInserirDebitoACobrarAction.do?objetoConsulta=1', 'idTipoDebito');"
							maxlength="9" tabindex="4" onkeyup="limparCalculoPrestacao();" />
					</logic:notPresent> <a href="javascript:pesquisarTipoDebito();"> <img
						width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Tipo de Débito" /></a> <logic:present
						name="corDebitoTipo">
						<logic:equal name="corDebitoTipo" value="exception">
							<html:text property="descricaoTipoDebito" value=""
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								size="40" maxlength="45" />

						</logic:equal>
						<logic:notEqual name="corDebitoTipo" value="exception">
							<html:text property="descricaoTipoDebito" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />

						</logic:notEqual>
					</logic:present> <logic:notPresent name="corDebitoTipo">
						<logic:empty name="InserirDebitoACobrarActionForm"
							property="idTipoDebito">
							<html:text property="descricaoTipoDebito" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />

						</logic:empty>
						<logic:notEmpty name="InserirDebitoACobrarActionForm"
							property="idTipoDebito">
							<html:text property="descricaoTipoDebito" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />

						</logic:notEmpty>
					</logic:notPresent> <a href="javascript:limparTipoDebito();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Tipo de Débito" /> </a></div>
					</td>
				</tr>
				<logic:equal name="alterarValorSugeridoEmTipoDebito"
									value="true">
				<tr>
					<td><strong>Valor Total do Servi&ccedil;o:<strong><font
						color="#FF0000">*</font></strong></strong></td>
					<td colspan="3" align="right">
					<div align="left"><html:text property="valorTotalServico"
						style="text-align: right;"
						onkeyup="formataValorMonetario(this, 11);limparCalculoPrestacao();"
						maxlength="14" size="14" tabindex="5" /></div>
					<div align="left"><strong></strong></div>
					<div align="left"></div>
					</td>
				</tr>
				</logic:equal>
				<logic:notEqual name="alterarValorSugeridoEmTipoDebito"
									value="true">
				<tr>
					<td><strong>Valor Total do Servi&ccedil;o:<strong><font
						color="#FF0000">*</font></strong></strong></td>
					<td colspan="3" align="right">
					<div align="left"><html:text property="valorTotalServico"
						readonly="true"  style="text-align: right;background-color:#EFEFEF; border:0; color: #000000"
						onkeyup="formataValorMonetario(this, 11);limparCalculoPrestacao();"
						maxlength="14" size="14" tabindex="5" /></div>
					<div align="left"><strong></strong></div>
					<div align="left"></div>
					</td>
				</tr>
				</logic:notEqual>
				<tr>
					<td><strong>Percentual de Abatimento:</strong></td>
					<td align="right" colspan="3">
					<div align="left"></div>
					<div align="left"><strong></strong></div>
					<div align="left"><html:text property="percentualAbatimento"
						style="text-align: right;" maxlength="6" size="6" tabindex="6"
						onkeyup="formataValorMonetario(this, 6);limparCalculoPrestacao();" /></div>
					</td>
				</tr>
				
				<tr>
					<td><strong>Valor da Entrada:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><html:text property="valorEntrada" maxlength="14"
						style="text-align: right;"
						onkeyup="formataValorMonetario(this, 11);limparCalculoPrestacao();"
						size="14" tabindex="7" /></div>
					<div align="left"><strong></strong></div>
					<div align="left"></div>
					</td>
				</tr>
				
				<tr>
					<td><strong>N&uacute;mero de Presta&ccedil;&otilde;es:<strong><font
						color="#FF0000">*</font></strong></strong></td>
					<td colspan="3" align="right">
					<div align="left"><html:text property="numeroPrestacoes"
						maxlength="6" size="10" onkeyup="limparCalculoPrestacao();"
						tabindex="8" /></div>
					<div align="left"><strong></strong></div>
					<div align="left"></div>
					</td>
				</tr>
				<tr>
					<td><strong>Taxa de Juros do Financiamento:</strong></td>
					<td colspan="2" align="right">
					<div align="left"><html:text property="taxaJurosFinanciamento"
						style="text-align: right;" maxlength="6" tabindex="9"
						onkeyup="formataValorMonetario(this, 6);limparCalculoPrestacao();"
						size="6" /></div>
					<td align="right"><input type="button" name="calcularPrestacao"
						class="bottonRightCol" value="Calcular Presta&ccedil;&atilde;o"
						onclick="validarForm(document.forms[0], false);" tabindex="10"></td>
				</tr>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong>Valor dos Juros:</strong></td>
					<td colspan="3" align="right">
					<div align="left"></div>
					<div align="left"><strong></strong></div>
					<div align="left"><html:text property="valorJuros" maxlength="20"
						style="text-align: right;background-color:#EFEFEF; border:0"
						onkeyup="formataValorMonetario(this, 11);" size="20"
						readonly="true" /></div>
					</td>
				</tr>
				<tr>
					<td><strong>Valor Total do Servi&ccedil;o a Parcelar:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><html:text property="valorTotalServicoAParcelar"
						onkeyup="formataValorMonetario(this, 11);" maxlength="20"
						style="text-align: right;background-color:#EFEFEF; border:0"
						size="20" readonly="true" /></div>
					<div align="left"><strong></strong></div>
					<div align="left"></div>
					</td>
				</tr>
				<tr>
					<td><strong>Valor da Presta&ccedil;&atilde;o:<strong></strong></strong></td>
					<td colspan="3" align="right">
					<div align="left"><html:text property="valorPrestacao"
						onkeyup="formataValorMonetario(this, 11);" maxlength="20"
						size="20"
						style="text-align: right;background-color:#EFEFEF; border:0"
						readonly="true" /></div>
					<div align="left"><strong></strong></div>
					<div align="left"></div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="3" align="right">
					<div align="left"></div>
					<div align="left"><strong></strong></div>
					<div align="left"><font color="#FF0000">*</font> Campo
					Obrigat&oacute;rio</div>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>


				<tr>
					<td colspan="3"><input name="Button" type="button"
						class="bottonRightCol" value="Desfazer" align="left"
						onclick="limparForm();"> <input name="Button" type="button"
						class="bottonRightCol" value="Cancelar" align="left"
						onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right"><gsan:controleAcessoBotao name="btnInserir"
						value="Inserir"
						onclick="javascript:this.disabled=true;validarForm(document.forms[0], true);"
						url="inserirDebitoACobrarAction.do" /> <!--
						<input name="inserir" type="button"
							class="bottonRightCol" value="Inserir" align="right"
							onClick="validarForm(document.forms[0], true);" tabindex="11"> -->
					</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td colspan="3" align="right">&nbsp;</td>
				</tr>
			</table>

			<p>&nbsp;</p>

			</td>
		</tr>
	</table>


	<%@ include file="/jsp/util/rodape.jsp"%>
	

</html:form>
</body>
</html:html>
