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


<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
	
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="ManterContaConjuntoImovelActionForm" />

<script language="JavaScript">
function validaMesAnoVazio(form){
  if(form.mesAnoConta.value == ""){
		alert('Informe Mês/Ano');
		return false;
  }	
  return true;
}

function limparContas(form){
  form.quatidadeConta.value = "";
}

function validaForm(form){

	var retorno = false;
	
	if (validateManterContaConjuntoImovelActionForm(form) &&
		verificaAnoMesMensagemPersonalizada(form.mesAnoConta, "Mês e Ano da Conta inicial inválido") &&
		verificaAnoMesMensagemPersonalizada(form.mesAnoContaFinal, "Mês e Ano da Conta final inválido")){
	
		if (comparaMesAno(form.mesAnoConta.value, '>', form.mesAnoContaFinal.value)){
			form.mesAnoConta.focus();
			alert("Mês e Ano da Conta inicial maior que o Mês e Ano da Conta final");		
		}
		else if (form.dataVencimentoInicial.value.length > 0 &&
				 form.dataVencimentoFinal.value.length > 0 &&
				 comparaData(form.dataVencimentoInicial.value, '>', form.dataVencimentoFinal.value)){
			form.dataVencimentoInicial.focus();
			alert("Data Vencimento da Conta inicial maior que a Data Vencimento da Conta final");		
		}
		else{
			retorno = true;
		}
	}
	
	return retorno;
}

function pesquisarContas(form){
  
  if(validaForm(form)){
    form.action = 'exibirManterContaConjuntoImovelAction.do?quantidadeConta=ok'
  	form.submit();
  }
  else{
  	return false;
  }
  
}

function cancelarConta(form){
  var indicadorContaPaga;
  
  if(form.indicadorContaPaga[0].checked){
    indicadorContaPaga = form.indicadorContaPaga[0].value;
  }else if(form.indicadorContaPaga[1].checked){
    indicadorContaPaga = form.indicadorContaPaga[1].value;
  }else{
    indicadorContaPaga = form.indicadorContaPaga[2].value;
  }
    
  if(validaForm(form)){
  
  	if(form.quatidadeConta.value == "" || form.quatidadeConta.value == 0){
    	alert('Selecione as contas');  
  	}
  	else{
    	
    	var urlCancelamentoConta = "/gsan/exibirCancelarConjuntoContaAction.do?mesAno="+ form.mesAnoConta.value;
    	urlCancelamentoConta = urlCancelamentoConta + "&mesAnoFim="+ form.mesAnoContaFinal.value;
    	urlCancelamentoConta = urlCancelamentoConta + "&indicadorContaPaga="+ indicadorContaPaga;       	  
    	
    	if (form.idGrupoFaturamento != null && form.idGrupoFaturamento.value.length > 0){
    		urlCancelamentoConta = urlCancelamentoConta + "&idGrupoFaturamento=" + form.idGrupoFaturamento.value;
    	}
    	
    	if (form.dataVencimentoInicial.value.length > 0){
    		urlCancelamentoConta = urlCancelamentoConta + "&dataVencimentoContaInicial=" + form.dataVencimentoInicial.value;
    		
    		if (form.dataVencimentoFinal.value.length > 0){
    			urlCancelamentoConta = urlCancelamentoConta + "&dataVencimentoContaFinal=" + form.dataVencimentoFinal.value;
    		}
    		else{
    			urlCancelamentoConta = urlCancelamentoConta + "&dataVencimentoContaFinal=" + form.dataVencimentoInicial.value;
    		}  
    	}
    	
    	abrirPopup(urlCancelamentoConta, 295, 450);  
  	
  	}
  }
}

function retirarDebitoCobradoConta(form){
  var indicadorContaPaga;
  
  if(form.indicadorContaPaga[0].checked){
    indicadorContaPaga = form.indicadorContaPaga[0].value;
  }else if(form.indicadorContaPaga[1].checked){
    indicadorContaPaga = form.indicadorContaPaga[1].value;
  }else{
    indicadorContaPaga = form.indicadorContaPaga[2].value;
  }
  if(validaForm(form)){
  
  	if(form.quatidadeConta.value == "" || form.quatidadeConta.value == 0){
    	alert('Selecione as contas');  
  	}
  	else{
    	
    	var urlCancelamentoConta = "/gsan/exibirRetirarDebitoCobradoConjuntoContaAction.do?mesAno="+ form.mesAnoConta.value;  
    	urlCancelamentoConta = urlCancelamentoConta + "&mesAnoFim="+ form.mesAnoContaFinal.value;
    	urlCancelamentoConta = urlCancelamentoConta + "&indicadorContaPaga="+ indicadorContaPaga;   
    	    	
    	if (form.idGrupoFaturamento != null && form.idGrupoFaturamento.value.length > 0){
    		urlCancelamentoConta = urlCancelamentoConta + "&idGrupoFaturamento=" + form.idGrupoFaturamento.value;
    	}
    	
    	if (form.dataVencimentoInicial.value.length > 0){
    		urlCancelamentoConta = urlCancelamentoConta + "&dataVencimentoContaInicial=" + form.dataVencimentoInicial.value;
    		
    		if (form.dataVencimentoFinal.value.length > 0){
    			urlCancelamentoConta = urlCancelamentoConta + "&dataVencimentoContaFinal=" + form.dataVencimentoFinal.value;
    		}
    		else{
    			urlCancelamentoConta = urlCancelamentoConta + "&dataVencimentoContaFinal=" + form.dataVencimentoInicial.value;
    		}  
    	}
    	
    	abrirPopup(urlCancelamentoConta, 295, 450);  
  	
  	}
  }
}

function alterarVencimento(){
  var form = document.forms[0];
  var indicadorContaPaga;
  
  if(form.indicadorContaPaga[0].checked){
    indicadorContaPaga = form.indicadorContaPaga[0].value;
  }else if(form.indicadorContaPaga[1].checked){
    indicadorContaPaga = form.indicadorContaPaga[1].value;
  }else{
    indicadorContaPaga = form.indicadorContaPaga[2].value;
  }
  
  if(validaForm(form)){
  
  	if(form.quatidadeConta.value == "" || form.quatidadeConta.value == 0){
    	alert('Selecione as contas');  
  	}
  	else{
    	
    	var urlAlterarVencimentoConta = "/gsan/exibirAlterarVencimentoConjuntoContaAction.do?mesAno="+ form.mesAnoConta.value;
    	urlAlterarVencimentoConta = urlAlterarVencimentoConta + "&mesAnoFim="+ form.mesAnoContaFinal.value; 
    	urlAlterarVencimentoConta = urlAlterarVencimentoConta + "&indicadorContaPaga="+ indicadorContaPaga;   
    	
    	if (form.idGrupoFaturamento != null && form.idGrupoFaturamento.value.length > 0){
    		urlAlterarVencimentoConta = urlAlterarVencimentoConta + "&idGrupoFaturamento=" + form.idGrupoFaturamento.value;
    	}
    	
    	if (form.codigoClienteSuperior != null && form.codigoClienteSuperior.value.length > 0){
    		urlAlterarVencimentoConta = urlAlterarVencimentoConta + "&codigoCliente=" + form.codigoClienteSuperior.value;
    	}
    	else if (form.codigoCliente != null && form.codigoCliente.value.length > 0){
    		urlAlterarVencimentoConta = urlAlterarVencimentoConta + "&codigoCliente=" + form.codigoCliente.value;
    	}
    	
    	if (form.dataVencimentoInicial.value.length > 0){
    		urlAlterarVencimentoConta = urlAlterarVencimentoConta + "&dataVencimentoContaInicial=" + form.dataVencimentoInicial.value;
    		
    		if (form.dataVencimentoFinal.value.length > 0){
    			urlAlterarVencimentoConta = urlAlterarVencimentoConta + "&dataVencimentoContaFinal=" + form.dataVencimentoFinal.value;
    		}
    		else{
    			urlAlterarVencimentoConta = urlAlterarVencimentoConta + "&dataVencimentoContaFinal=" + form.dataVencimentoInicial.value;
    		}  
    	}
    	
    	abrirPopup(urlAlterarVencimentoConta, 295, 450);  
  	
  	}
  }
}

/* Replica Data de Vencimento */
function replicaDataVencimento() {
	var form = document.forms[0];
	form.dataVencimentoFinal.value = form.dataVencimentoInicial.value
}

function replicaAnoMes() {
	var form = document.forms[0];
	form.mesAnoContaFinal.value = form.mesAnoConta.value
}

function emitir2ViaConta(contaRevisao){
   
   var form = document.forms[0];
   var indicadorContaPaga;
  
  if(form.indicadorContaPaga[0].checked){
    indicadorContaPaga = form.indicadorContaPaga[0].value;
  }else if(form.indicadorContaPaga[1].checked){
    indicadorContaPaga = form.indicadorContaPaga[1].value;
  }else{
    indicadorContaPaga = form.indicadorContaPaga[2].value;
  }
  
  if(validaForm(form)){
  
  	if(form.quatidadeConta.value == "" || form.quatidadeConta.value == 0){
    	alert('Selecione as contas');    	
  	}else{
    	
    	if(contaRevisao == 1){
    	  alert('Existe(m) conta(s) em revisão no intervalo selecionado.'); 
    	}
    	
    	var urlCancelamentoConta = "gerarRelatorio2ViaContaAction.do?cobrarTaxaEmissaoConta=N&mesAno="+ form.mesAnoConta.value+"&qtdeContas="+form.quatidadeConta.value;  
    	urlCancelamentoConta = urlCancelamentoConta + "&mesAnoFim="+ form.mesAnoContaFinal.value;  
    		urlCancelamentoConta = urlCancelamentoConta + "&indicadorContaPaga="+ indicadorContaPaga;   
    	    	
    	if (form.idGrupoFaturamento != null && form.idGrupoFaturamento.value.length > 0){
    		urlCancelamentoConta = urlCancelamentoConta + "&idGrupoFaturamento=" + form.idGrupoFaturamento.value;
    	}
    	
    	if (form.dataVencimentoInicial.value.length > 0){
    		urlCancelamentoConta = urlCancelamentoConta + "&dataVencimentoContaInicial=" + form.dataVencimentoInicial.value;
    		
    		if (form.dataVencimentoFinal.value.length > 0){
    			urlCancelamentoConta = urlCancelamentoConta + "&dataVencimentoContaFinal=" + form.dataVencimentoFinal.value;
    		}
    		else{
    			urlCancelamentoConta = urlCancelamentoConta + "&dataVencimentoContaFinal=" + form.dataVencimentoInicial.value;
    		}  
    	}
    	
    	form.action = urlCancelamentoConta;
  	 	form.submit();  
  	
  	}
  }
}

function voltar(form){
  form.action = 'exibirFiltrarImovelInserirManterContaAction.do?menu=sim'
  form.submit();
}


function habilitarBotoes(){

	var form = document.forms[0];
	var idGrupoFaturamento = form.idGrupoFaturamento;
	
	if (idGrupoFaturamento != null && idGrupoFaturamento.value.length > 0){
		
		form.botaoCancelar.disabled = true;
		form.botaoRetirarDebitoCobrado.disabled = true;
		form.botaoEmitirSegundaVia.disabled = true;
	}
	else{
		
		form.botaoCancelar.disabled = false;
		form.botaoRetirarDebitoCobrado.disabled = false;
		form.botaoEmitirSegundaVia.disabled = false;
	}
	
}

function habilitarIntervalo(){

	var form = document.forms[0];
	var codigoClienteSuperior = form.codigoClienteSuperior;
	var codigoCliente = form.codigoCliente;
	
	if (codigoClienteSuperior != null && codigoClienteSuperior.value.length > 0){
		
		form.dataVencimentoInicial.value = "";
		form.dataVencimentoFinal.value = "";
		form.dataVencimentoInicial.disabled = true;
		form.dataVencimentoFinal.disabled = true;
		
		return false;
	}
	else if (codigoCliente != null && codigoCliente.value.length > 0){
		
		form.dataVencimentoInicial.value = "";
		form.dataVencimentoFinal.value = "";
		form.dataVencimentoInicial.disabled = true;
		form.dataVencimentoFinal.disabled = true;
		
		return false;
	}
	else{
		
		form.dataVencimentoInicial.disabled = false;
		form.dataVencimentoFinal.disabled = false;
		
		return true;
	}
	
}

function retificarConjuntoConta(){
  var form = document.forms[0];
  var indicadorContaPaga;
  
  if(form.indicadorContaPaga[0].checked){
    indicadorContaPaga = form.indicadorContaPaga[0].value;
  }else if(form.indicadorContaPaga[1].checked){
    indicadorContaPaga = form.indicadorContaPaga[1].value;
  }else{
    indicadorContaPaga = form.indicadorContaPaga[2].value;
  }
  
  if(validaForm(form)){
  
  	if(form.quatidadeConta.value == "" || form.quatidadeConta.value == 0){
    	alert('Selecione as contas');  
  	}
  	else{
    	
    	var urlRetificarConjuntoConta = "/gsan/exibirRetificarConjuntoContaAction.do?mesAno="+ form.mesAnoConta.value;
    	urlRetificarConjuntoConta = urlRetificarConjuntoConta + "&mesAnoFim="+ form.mesAnoContaFinal.value; 
    	urlRetificarConjuntoConta = urlRetificarConjuntoConta + "&indicadorContaPaga="+ indicadorContaPaga;   
    	
    	if (form.idGrupoFaturamento != null && form.idGrupoFaturamento.value.length > 0){
    		urlRetificarConjuntoConta = urlRetificarConjuntoConta + "&idGrupoFaturamento=" + form.idGrupoFaturamento.value;
    	}
    	
    	if (form.codigoClienteSuperior != null && form.codigoClienteSuperior.value.length > 0){
    		urlRetificarConjuntoConta = urlRetificarConjuntoConta + "&codigoCliente=" + form.codigoClienteSuperior.value;
    	}
    	else if (form.codigoCliente != null && form.codigoCliente.value.length > 0){
    		urlRetificarConjuntoConta = urlRetificarConjuntoConta + "&codigoCliente=" + form.codigoCliente.value;
    	}
    	
    	if (form.dataVencimentoInicial.value.length > 0){
    		urlRetificarConjuntoConta = urlRetificarConjuntoConta + "&dataVencimentoContaInicial=" + form.dataVencimentoInicial.value;
    		
    		if (form.dataVencimentoFinal.value.length > 0){
    			urlRetificarConjuntoConta = urlRetificarConjuntoConta + "&dataVencimentoContaFinal=" + form.dataVencimentoFinal.value;
    		}
    		else{
    			urlRetificarConjuntoConta = urlRetificarConjuntoConta + "&dataVencimentoContaFinal=" + form.dataVencimentoInicial.value;
    		}  
    	}
    	
    	abrirPopup(urlRetificarConjuntoConta, 460, 630);  
  	
  	}
  }
}

</script>
</head>

<logic:present name="mensagemSuceso">
	<body leftmargin="5" topmargin="5" onload="javascript:alert('${requestScope.mensagemSuceso}');">
</logic:present>

<logic:notPresent name="mensagemSuceso">
 <logic:present name="reloadPage">
	<body leftmargin="5" topmargin="5" onload="pesquisarContas(document.forms[0]);habilitarBotoes();habilitarIntervalo();">
 </logic:present>

 <logic:notPresent name="reloadPage">
	<body leftmargin="5" topmargin="5" onload="habilitarBotoes();habilitarIntervalo();">
 </logic:notPresent>
</logic:notPresent>

<html:form action="/filtrarImovelInserirManterContaAction"
	name="ManterContaConjuntoImovelActionForm"
	type="gcom.gui.faturamento.conta.ManterContaConjuntoImovelActionForm"
	method="post"
	onsubmit="return validateManterContaConjuntoImovelActionForm(this);"
	focus="mesAnoConta">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext">
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

			<td width="625" valign="top" class="centercoltext">

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
					<td class="parabg">Manter Contas de um Conjunto de Im&oacute;veis</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td>Para manter a(s) conta(s), informe o mês e o ano abaixo:</td>					
				</tr>
			</table>

			<table width="100%" border="0">
				<logic:notPresent name="cliente">				
				<tr>
					<td height="10" width="160"><strong>Inscrição inicial:</strong></td>
					<td width="403"><html:text property="inscricaoInicial" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/></td>													
					<td width="43">&nbsp;</td>
				</tr>
				<tr>
					<td height="10" width="160"><strong>Inscrição Final:</strong></td>
					<td width="403"><html:text property="inscricaoFinal" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/></td>													
					<td width="43">&nbsp;</td>
				</tr>
				<tr>
					<td height="10" width="160"><strong>Grupo de Faturamento:</strong></td>
					<td width="403"><html:text property="idGrupoFaturamento" size="5" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/></td>													
					<td width="43">&nbsp;</td>
				</tr>
				</logic:notPresent>
				<logic:present name="cliente">
				<tr>
				    <logic:present name="superior">
					    <td width="30%"><strong>Cliente Superior:</strong></td>
						<td colspan="3"><html:text property="codigoClienteSuperior" maxlength="9"
							size="9" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"/> 
									
						<html:text property="nomeCliente" size="38" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</td>
				    </logic:present>				    
				    <logic:notPresent  name="superior">
					    <td width="30%"><strong>Cliente:</strong></td>
						<td colspan="3"><html:text property="codigoCliente" maxlength="9"
							size="9" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"/> 
									
						<html:text property="nomeCliente" size="38" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</td>
				    </logic:notPresent>
								
					
				</tr>				
				</logic:present>
				<tr>
					<td height="10" width="160"><strong>Quantidade de Imóveis:</strong></td>
					<td width="403"><html:text property="quatidadeImovel" size="15" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/></td>													
					<td width="43">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3" height="10"></td>
				</tr>
				<tr>
					<td colspan="3" height="10"></td>
				</tr>
				<tr>
					<td colspan="3" height="10"></td>
				</tr>
				<tr> 
            	  <td colspan="4"> 
            	  <table width="100%" align="center" bgcolor="#99CCFF" border="0">
            	  
            	  <!--                  DADOS DA CONTA                        -->
            	  
                	<tr> 
                      <td><strong>Dados da Conta:</strong></td>
                	</tr>
                	<tr bgcolor="#cbe5fe"> 
                  	  <td width="100%" align="center"> 
                  	    <table width="100%" border="0">
						  <tr>
							<td height="10" width="160">
								<strong>Mês e Ano da Conta:<font color="#FF0000">*</font></strong>
							</td>
							<td width="403">
								<html:text property="mesAnoConta"  
										   onkeyup="mascaraAnoMes(this, event);replicaAnoMes();limparContas(document.forms[0]);"  
										   maxlength="7" 
										   size="7" 
										   onkeypress="return isCampoNumerico(event);"/>
							 	a 
							 	<html:text property="mesAnoContaFinal"  
							 			   onkeyup="mascaraAnoMes(this, event);limparContas(document.forms[0]);"  
							 			   maxlength="7" 
							 			   onkeypress="return isCampoNumerico(event);"
							 			   size="7" />MM/AAAA</td>																								
							<td width="43">&nbsp;</td>
						  </tr>
						  
						  <tr>
							<td height="10" width="160">
								<strong>Data Vencimento da Conta:</strong>
							</td>
							<td width="403">
							
								<html:text property="dataVencimentoInicial" 
										   size="11" 
										   maxlength="10" 
										   onkeypress="return isCampoNumerico(event);"
										   onkeyup="mascaraData(this, event);replicaDataVencimento();limparContas(document.forms[0]);"/>
									<a href="javascript:if (habilitarIntervalo()) abrirCalendario('ManterContaConjuntoImovelActionForm', 'dataVencimentoInicial');">
										<img border="0" 
											 src="<bean:message key='caminho.imagens'/>calendario.gif" 
											 width="16" 
											 height="15" 
											 border="0" 
											 alt="Exibir Calendário"/>
									</a>
						
								a <html:text property="dataVencimentoFinal" 
											 size="11" 
											 maxlength="10" 
											 onkeypress="return isCampoNumerico(event);"
											 onkeyup="mascaraData(this, event);limparContas(document.forms[0]);"/>
									<a href="javascript:if (habilitarIntervalo()) abrirCalendario('ManterContaConjuntoImovelActionForm', 'dataVencimentoFinal');">
										<img border="0" 
											 src="<bean:message key='caminho.imagens'/>calendario.gif" 
											 width="16" 
											 height="15"
											 border="0" 
											 alt="Exibir Calendário"/>
									</a>
							
							</td>																								
							<td width="43">&nbsp;</td>
						  </tr>
						  
						  <tr>
							<!--  
								Bruno Barros
								05 de Janeiro de 2009
																					
								[FS0026] - Verificar a permissão especial para exibir as contas pagas.
									. Verificar se as contas selecionadas para o imóvel se encontram pagas, 
								através do identificador da conta CNTA_ID nas tabelas ARRECADACAO.PAGAMENTO. 
								Caso o usuário possua permissão especial, as contas identificadas nas 
								condições serão apresentadas e poderão ser retificadas e atualizadas, 
								de acordo com processos já existentes. Caso contrário, serão apenas apresentadas 
								as contas sem possibilitar que o usuário atualize ou retifique as contas pagas.												
							-->														
							<logic:equal name="usuarioPodeAtualizarRetificarContasPagas" value="true" >
								<td><strong>Conta Paga:</strong></td>
								<td><strong> 
									<html:radio property="indicadorContaPaga" value="1"/>SIM 
									<html:radio	property="indicadorContaPaga" value="2"/>NÃO
									<html:radio	property="indicadorContaPaga" value="3"/>AMBOS								
								</strong></td>												
							</logic:equal>
							<logic:equal name="usuarioPodeAtualizarRetificarContasPagas" value="false" >
								<td><strong>Conta Paga:</strong></td>
								<td><strong> 
									<html:radio property="indicadorContaPaga" value="1" disabled="true"/>SIM 
									<html:radio	property="indicadorContaPaga" value="2"/>NÃO
									<html:radio	property="indicadorContaPaga" value="3" disabled="true"/>AMBOS								
								</strong></td>
							</logic:equal>														
							<!-- //////////////////////////////////////////////////////////////////////////// -->						  
						  <tr>
							<td height="10" width="160">
								<strong>Quantidade de Contas:</strong>
							</td>
							<td width="403">
								<html:text  property="quatidadeConta" 
											size="7" 
											readonly="true" 
											style="background-color:#EFEFEF; border:0; color: #000000"/></td>													
							<td align="right">
								<gsan:controleAcessoBotao name="Button" value="Selecionar"
									onclick="javascript:pesquisarContas(document.forms[0]);"
									url="consultarDebitoAction.do" /> 
							</td>
						  </tr>
						</table>
					  </td>
                    </tr>
                  </table>
                  </td>
                </tr>						  
				<tr>
					<td align="center" colspan="3"><strong><font color="#FF0000">*</font></strong> Campos obrigat&oacute;rios</td>
				</tr>
				<tr>
					<td colspan="3" height="10"></td>
				</tr>
				<tr>
					<td colspan="3" height="5">
						<logic:present name="bancos">

							<input type="button" 
								   name=myButton 
								   value="Cancelar Conta" 
								   class="bottonRightCol" 
								   disabled>

						</logic:present>
						
						<logic:notPresent name="bancos">
							
							<gsan:controleAcessoBotao   name="botaoCancelar" 
														value="Cancelar Conta" 
														onclick="cancelarConta(document.forms[0]);" 
														url="cancelarConjuntoContaAction.do" 
														style="width: 117px"
							/> 
							
						</logic:notPresent>
						
						<logic:present name="bancos">

							<input type="button" name=myButton value="Retirar Débito Cobrado" class="bottonRightCol" disabled>

						</logic:present>
						<logic:notPresent name="bancos">
							<gsan:controleAcessoBotao 
							name="botaoRetirarDebitoCobrado" value="Retirar Débito Cobrado"
							
							onclick="retirarDebitoCobradoConta(document.forms[0]);" url="colocarRevisaoContaAction.do" style="width: 157px"/> 
						</logic:notPresent>
						
						<gsan:controleAcessoBotao
						name="botaoAlterarVencimento" value="Alterar Vencimento"
						onclick="alterarVencimento();" url="alterarVencimentoContaAction.do" style="width: 137px"/>
						
						
						<logic:present name="bancos">

							<input type="button" name=myButton value="Emitir 2ª Via de Conta" class="bottonRightCol" disabled>

						</logic:present>
						
						<logic:notPresent name="bancos">
							<logic:present name="contaRevisao" scope="session">
							  <gsan:controleAcessoBotao
							  name="botaoEmitirSegundaVia" value="Emitir 2ª Via de Conta"
							  onclick="emitir2ViaConta(1);" url="gerarRelatorio2ViaContaAction.do" style="width: 157px"/>
							</logic:present>
							<logic:notPresent name="contaRevisao" scope="session">
							  <gsan:controleAcessoBotao
							  name="botaoEmitirSegundaVia" value="Emitir 2ª Via de Conta"
							  onclick="emitir2ViaConta(2);" url="gerarRelatorio2ViaContaAction.do" style="width: 157px"/>
							</logic:notPresent>	
						</logic:notPresent>
						
						<gsan:controleAcessoBotao
							name="Button" value="Retificar Conjunto de Conta"
							onclick="retificarConjuntoConta(document.forms[0]);" url="retificarConjuntoContaAction.do" style="width: 170px"/>
						
						</td>
				</tr>
				<tr>
					<td valign="top" colspan="3">
						<input type="button" 
							   name="ButtonCancelar" 
							   class="bottonRightCol" 
							   value="Cancelar"
							   onClick="javascript:document.forms[0].target='';window.location.href='/gsan/telaPrincipal.do'">
						<input type="button" 
							   name="ButtonCancelar" 
							   class="bottonRightCol" 
							   value="Voltar Filtro"
							   onClick="javascript:voltar(document.forms[0]);">
					</td>

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
