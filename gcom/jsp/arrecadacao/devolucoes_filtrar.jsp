<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarDevolucaoActionForm" />

<script language="JavaScript"><!--
    //Recebe os dados do(s) popup(s)
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	var form = document.FiltrarDevolucaoActionForm;
	if (tipoConsulta == 'imovel') {
		form.idImovel.value = codigoRegistro;
		form.descricaoImovel.value = descricaoRegistro;
		form.idCliente.disabled = true;
		form.clienteRelacaoTipo.disabled = true;
		form.clienteRelacaoTipo.selectedIndex = 0;
		form.idLocalidadeInicial.disabled = true;
		form.idLocalidadeFinal.disabled = true;
	}
	if (tipoConsulta == 'cliente') {
		form.idCliente.value = codigoRegistro;
		form.nomeCliente.value = descricaoRegistro;
		form.idImovel.disabled = true;
		form.idLocalidadeInicial.disabled = true;
		form.idLocalidadeFinal.disabled = true;
	}
	if (tipoConsulta == 'localidadeOrigem') {
       form.idLocalidadeInicial.value = codigoRegistro;
	   form.descricaoLocalidadeInicial.value = descricaoRegistro;
	   form.idImovel.disabled = true;
	   form.idCliente.disabled = true;
	   form.clienteRelacaoTipo.disabled = true;
	   form.clienteRelacaoTipo.selectedIndex = 0;
	   form.descricaoLocalidadeInicial.style.color = "#000000";
	   if(form.idLocalidadeFinal.value == ""){
	      form.idLocalidadeFinal.value = codigoRegistro;
		  form.descricaoLocalidadeFinal.value = descricaoRegistro;
		  form.descricaoLocalidadeFinal.style.color = "#000000";
	   }
	}

	if (tipoConsulta == 'localidadeDestino') {
    	form.idLocalidadeFinal.value = codigoRegistro;
    	form.descricaoLocalidadeFinal.value = descricaoRegistro;
 		form.descricaoLocalidadeFinal.style.color = "#000000";    		
	    form.idImovel.disabled = true;
	    form.idCliente.disabled = true;
	    form.clienteRelacaoTipo.disabled = true;
	}
	
}
 
function recuperarDadosCincoParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, descricaoRegistro3, tipoConsulta) { 
	var form = document.FiltrarDevolucaoActionForm;
	
	if (tipoConsulta == 'avisoBancario') {
 		form.idAvisoBancario.value = codigoRegistro;
		form.codigoAgenteArrecadador.value = descricaoRegistro1;
		form.dataLancamentoAviso.value = descricaoRegistro2;
		form.numeroSequencialAviso.value = descricaoRegistro3;
		form.idImovel.disabled = true;
		form.idCliente.disabled = true;
		form.clienteRelacaoTipo.disabled = true;
		form.clienteRelacaoTipo.selectedIndex = 0;
		form.idLocalidadeInicial.disabled = true;
		form.idLocalidadeFinal.disabled = true;
	}
}

function controleImovel(form){
	form.idCliente.disabled = true;
}
	
function controleCliente(form){
	form.idImovel.disabled = true;
}
	
function controleAvisoBancario(form){
	form.idImovel.disabled = true;
	form.idCliente.disabled = true;
}
	
function controle(form){
	if (form.idAvisoBancario.value != null && form.idAvisoBancario.value != "") {
		form.idImovel.disabled = true;
		form.idCliente.disabled = true;
		form.clienteRelacaoTipo.disabled = true;
		form.clienteRelacaoTipo.selectedIndex = 0;
		form.idLocalidadeInicial.disabled = true;
		form.idLocalidadeFinal.disabled = true;
	} else if (form.idImovel.value != null && trim(form.idImovel.value) != "") {
		form.idCliente.disabled = true;
		form.clienteRelacaoTipo.disabled = true;
		form.clienteRelacaoTipo.selectedIndex = 0;
		form.idLocalidadeInicial.disabled = true;
		form.idLocalidadeFinal.disabled = true;
	} else if (form.idCliente.value != null && trim(form.idCliente.value) != "") {
		form.idImovel.disabled = true;
		form.idLocalidadeInicial.disabled = true;
		form.idLocalidadeFinal.disabled = true;
	} else if (form.idLocalidadeInicial.value != null && trim(form.idLocalidadeInicial.value) != "") {
		form.idImovel.disabled = true;
		form.idCliente.disabled = true;
		form.clienteRelacaoTipo.disabled = true;
		form.clienteRelacaoTipo.selectedIndex = 0;
	}	
}		

function habilitarPesquisaCliente(form) {
	if (form.idCliente.disabled == false) {
		abrirPopup('exibirPesquisarClienteAction.do?indicadorUsoTodos=1', 400, 800);
	}	
}
	
function habilitarPesquisaImovel(form) {
	if (form.idImovel.disabled == false) {
		abrirPopup('exibirPesquisarImovelAction.do?indicadorUsoTodos=1', 400, 800);
	}	
}
	
function habilitarPesquisaLocalidade(form, tipo) {
	if (form.idLocalidadeInicial.disabled == false) 
	{
	    if(tipo == 'origem')
	    {
			chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', 'indicadorUsoTodos', '1', 275, 480, '',form.idLocalidadeInicial.value);
		}
		else if(tipo == 'destino')
	    {
			chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', 'indicadorUsoTodos', '1', 275, 480, '',form.idLocalidadeFinal.value);
		}
	}	
}

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){

	if(objetoRelacionado.disabled != true){
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
}

function habilitarPesquisaAvisoBancario(form) {
	if ((form.idImovel.disabled == false && 
		form.idCliente.disabled == false && 
		form.idLocalidadeInicial.disabled == false) || 
		(form.idImovel.disabled == true && form.idCliente.disabled == true && 
		form.idLocalidadeInicial.disabled == true)) {
			abrirPopup('exibirPesquisarAvisoBancarioAction.do?limparForm=1', 400, 800);
	}	
}
	
function duplicaPeriodoArrecadacao(form) {
	form.periodoArrecadacaoFim.value = form.periodoArrecadacaoInicio.value;
}
	
function duplicaDataDevolucao(form) {
	form.dataDevolucaoFim.value = form.dataDevolucaoInicio.value;
}
	
function duplicaLocalidade(form) {
	form.idLocalidadeFinal.value = form.idLocalidadeInicial.value;
}
 
function validaEnterImovel(event, caminho, campo) {
	var form = document.FiltrarDevolucaoActionForm;
	validaEnterComMensagem(event, caminho, campo,'Matrícula do Imóvel');
	
	if(form.idImovel.value.length > 0) {
		form.idCliente.disabled = true;
		form.clienteRelacaoTipo.disabled = true;
		form.clienteRelacaoTipo.selectedIndex = 0;
		form.idLocalidadeInicial.disabled = true;
		form.idLocalidadeFinal.disabled = true;
	} else {	
		if ((form.idAvisoBancario.value == "" || form.idAvisoBancario.value.lenght == 0) &&
		(form.idLocalidadeInicial.disabled == true || form.idLocalidadeInicial.value == "" || form.idLocalidadeInicial.value.lenght == 0) && 
		(form.idLocalidadeFinal.disabled == true || form.idLocalidadeFinal.value == "" || form.idLocalidadeFinal.value.lenght == 0) &&
		(form.idCliente.disable == true || form.idCliente.value == "" || form.idCliente.value.lenght == 0)) {
			form.idCliente.disabled = false;
			form.clienteRelacaoTipo.disabled = false;
			form.idLocalidadeInicial.disabled = false;
			form.idLocalidadeFinal.disabled = false;
		}
		form.idImovel.value = "";
		form.descricaoImovel.value = "";
	}
}
	
function validaEnterCliente(event, caminho, campo) {
	var form = document.FiltrarDevolucaoActionForm;
	validaEnterComMensagem(event, caminho, campo,'Código do Cliente');
		
	if(form.idCliente.value.length > 0) {
		form.idImovel.disabled = true;
		form.idLocalidadeInicial.disabled = true;
		form.idLocalidadeFinal.disabled = true;
		
	} else {	
		if ((form.idAvisoBancario.value == "" || form.idAvisoBancario.value.lenght == 0) && 
		(form.idLocalidadeInicial.disabled == true || form.idLocalidadeInicial.value == "" || form.idLocalidadeInicial.value.lenght == 0) && 
		(form.idLocalidadeFinal.disabled == true || form.idLocalidadeFinal.value == "" || form.idLocalidadeFinal.value.lenght == 0) &&
		(form.idImovel.disabled == true || form.idImovel.value == "" || form.idImovel.value.lenght == 0)) {
			form.idImovel.disabled = false;
			form.idLocalidadeInicial.disabled = false;
			form.idLocalidadeFinal.disabled = false;
		}
		form.idCliente.value = "";
		form.nomeCliente.value = "";
	}
}
	
function validaEnterLocalidade(event, caminho, campo,msgCampo) {
	var form = document.FiltrarDevolucaoActionForm;
	validaEnterComMensagem(event, caminho, campo,msgCampo);
		
	if(form.idLocalidadeInicial.value.length > 0 || form.idLocalidadeFinal.value.length > 0) {
		form.idImovel.disabled = true;
		form.idCliente.disabled = true;
		form.clienteRelacaoTipo.disabled = true;
		form.clienteRelacaoTipo.selectedIndex = 0;
			
	} else {	
		if ((form.idAvisoBancario.value == "" || form.idAvisoBancario.value.lenght == 0) && 
		(form.idCliente.disable == true || form.idCliente.value == "" || form.idCliente.value.lenght == 0) && 
		(form.idImovel.disabled == true || form.idImovel.value == "" || form.idImovel.value.lenght == 0)) {
			form.idImovel.disabled = false;
			form.idCliente.disabled = false;
			form.clienteRelacaoTipo.disabled = false;
		}
		form.idLocalidadeInicial.value = "";
		form.descricaoLocalidadeInicial.value = "";
		form.idLocalidadeFinal.value = "";
		form.descricaoLocalidadeFinal.value = "";
	}
}  
 
function limparImovel() {
	var form = document.FiltrarDevolucaoActionForm;
	if (form.idImovel.disabled == false) {
		form.idImovel.value = "";
		form.descricaoImovel.value = "";
		form.idCliente.disabled = false;
		form.clienteRelacaoTipo.disabled = false;
		form.idLocalidadeInicial.disabled = false;
		form.idLocalidadeFinal.disabled = false;
	}
}

function limparAvisoBancario() {
	var form = document.FiltrarDevolucaoActionForm;
	form.idAvisoBancario.value = "";
	form.codigoAgenteArrecadador.value = "";
	form.dataLancamentoAviso.value = "";
	form.numeroSequencialAviso.value = "";
	
	if (form.idCliente.value.lenght == 0 && form.idLocalidadeInicial.lenght == 0) {	
		form.idImovel.disabled = false;
	}
	if (form.idImovel.value.lenght == 0 && form.idLocalidadeInicial.lenght == 0) {
		form.idCliente.disabled = false;
		form.clienteRelacaoTipo.disabled = false;
	}
	if (form.idImovel.value.lenght == 0 && form.idCliente.lenght == 0) {
		form.idLocalidadeInicial.disabled = false;
		form.idLocalidadeFinal.disabled = false;
	}
	if (form.idImovel.disabled == true && form.idCliente.disabled == true && form.idLocalidadeInicial.disabled == true) {
		form.idImovel.disabled = false;
		form.idCliente.disabled = false;
		form.clienteRelacaoTipo.disabled = false;
		form.idLocalidadeInicial.disabled = false;
		form.idLocalidadeFinal.disabled = false;
	}
}

function limparCliente() {
	var form = document.FiltrarDevolucaoActionForm;
	if (form.idCliente.disabled == false) {
		form.idCliente.value = "";
		form.nomeCliente.value = "";
		form.idImovel.disabled = false;
		form.idLocalidadeInicial.disabled = false;
		form.idLocalidadeFinal.disabled = false;
	}
}

function limparLocalidadeInicial() {
	var form = document.FiltrarDevolucaoActionForm;
	if (form.idLocalidadeInicial.disabled == false) {
		form.idLocalidadeInicial.value = "";
		form.descricaoLocalidadeInicial.value = "";
		form.idLocalidadeFinal.value = "";
		form.descricaoLocalidadeFinal.value = "";
		form.idImovel.disabled = false;
		form.idCliente.disabled = false;
		form.clienteRelacaoTipo.disabled = false;
	}
}

function limparLocalidadeFinal() {
	var form = document.FiltrarDevolucaoActionForm;
	form.idLocalidadeFinal.value = "";
	form.descricaoLocalidadeFinal.value = "";
}

function validarForm(form) 
{
var TELA = document.getElementById("TELA").value;

	if(validateFiltrarDevolucaoActionForm(form)){   					

		if (document.FiltrarDevolucaoActionForm.idLocalidadeInicial.value != null 
			&& document.FiltrarDevolucaoActionForm.idLocalidadeInicial.value != ''
			&& (TELA == null || TELA == '')){
					toggleBox('demodiv',1);			
		} else 
		{
			
			if(document.FiltrarDevolucaoActionForm.idCliente.value == '' && 
				document.FiltrarDevolucaoActionForm.idImovel.value == '' && 
				document.FiltrarDevolucaoActionForm.codigoAgenteArrecadador.value == '' &&
				(document.FiltrarDevolucaoActionForm.idLocalidadeInicial.value == '' || document.FiltrarDevolucaoActionForm.idLocalidadeFinal.value == ''))
			{
				alert('Informe Matrícula do Imóvel ou Código do Cliente ou Aviso Bancário ou o intervalo da Localidade.');
			} else 
			{				
				if(testarCampoValorZero(document.FiltrarDevolucaoActionForm.idCliente, 'Cliente') 
		   			&& testarCampoValorZero(document.FiltrarDevolucaoActionForm.idImovel, 'Imóvel')
		   			&& testarCampoValorZero(document.FiltrarDevolucaoActionForm.idLocalidadeInicial, 'Localidade Inicial')
		   			&& testarCampoValorZero(document.FiltrarDevolucaoActionForm.idLocalidadeFinal, 'Localidade Final')
		   			&& testarCampoValorZero(document.FiltrarDevolucaoActionForm.periodoArrecadacaoInicio, 'Período de Arrecadação Inicial') 
		   			&& testarCampoValorZero(document.FiltrarDevolucaoActionForm.periodoArrecadacaoFim, 'Período de Arrecadação Final')
		   			&& testarCampoValorZero(document.FiltrarDevolucaoActionForm.dataDevolucaoInicio, 'Data da Devolução Inicial')
		 			&& testarCampoValorZero(document.FiltrarDevolucaoActionForm.dataDevolucaoFim, 'Data da Devolução Final')) 
		 		{
		  						
					if (document.FiltrarDevolucaoActionForm.periodoArrecadacaoInicio.value != "" && document.FiltrarDevolucaoActionForm.periodoArrecadacaoInicio.value != '') 
			 		{
		 				if (verificaAnoMes(document.FiltrarDevolucaoActionForm.periodoArrecadacaoInicio)) 
		 				{
		 					if  (document.FiltrarDevolucaoActionForm.periodoArrecadacaoFim.value != "" && document.FiltrarDevolucaoActionForm.periodoArrecadacaoFim.value != '') 
		 					{
		 						if (verificaAnoMes(document.FiltrarDevolucaoActionForm.periodoArrecadacaoFim)) 
		 						{
									if (comparaMesAno(document.FiltrarDevolucaoActionForm.periodoArrecadacaoInicio.value, ">", document.FiltrarDevolucaoActionForm.periodoArrecadacaoFim.value )) 
									{
						 				alert('Mês/Ano Final do Período de Referência da Arrecadação é anterior ao Mês/Ano Inicial do Período de Referência da Arrecadação.');
					 				} 
					 				else 
					 				{	
						 				if (comparaData(document.FiltrarDevolucaoActionForm.dataDevolucaoInicio.value, ">", document.FiltrarDevolucaoActionForm.dataDevolucaoFim.value ))
										{
											alert('Data Final do Período de Devolução é anterior a Data Inicial do Período de Devolução.');
										}else{
											submeterFormPadrao(form);				
							 			}
							 		}
						 		}
						 	} else if (comparaData(document.FiltrarDevolucaoActionForm.dataDevolucaoInicio.value, ">", document.FiltrarDevolucaoActionForm.dataDevolucaoFim.value ))
							{
								alert('Data Final do Período de Devolução é anterior a Data Inicial do Período de Devolução.');
							}else{
								submeterFormPadrao(form);				
							}
						}
					} else 
					{
						if  (form.periodoArrecadacaoFim.value != "" && form.periodoArrecadacaoFim.value != '') 
						{
		 					if (verificaAnoMes(form.periodoArrecadacaoFim)) 
		 					{
								if (comparaMesAno(form.periodoArrecadacaoInicio.value, ">", form.periodoArrecadacaoFim.value )) 
								{
									alert('Mês/Ano Final do Período de Referência da Arrecadação é anterior ao Mês/Ano Inicial do Período de Referência da Arrecadação.');
					 			} else if (comparaData(document.FiltrarDevolucaoActionForm.dataDevolucaoInicio.value, ">", document.FiltrarDevolucaoActionForm.dataDevolucaoFim.value ))
								{
									alert('Data Final do Período de Devolução é anterior a Data Inicial do Período de Devolução.');
								}else{
									submeterFormPadrao(form);				
							 	}
					 		}
						} else if (comparaData(document.FiltrarDevolucaoActionForm.dataDevolucaoInicio.value, ">", document.FiltrarDevolucaoActionForm.dataDevolucaoFim.value ))
						{
							alert('Data Final do Período de Devolução é anterior a Data Inicial do Período de Devolução.');
						}else{
							submeterFormPadrao(form);				
						}
					}
				}
			}
		}
	}
}

--></script>
</head>
<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');controle(document.forms[0]);">
<html:form action="/filtrarDevolucaoAction"
	name="FiltrarDevolucaoActionForm"
	type="gcom.gui.arrecadacao.FiltrarDevolucaoActionForm" method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	<INPUT TYPE="hidden" ID="TELA" value="${sessionScope.tela}">
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
			<td valign="top" class="centercoltext">
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
					<td class="parabg">Filtrar Devoluções</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%">
				<tr>
					<td colspan="4">
					<p>Para filtrar a(s) devoluções no sistema, informe os dados
					abaixo:</p>
					</td>
					<td width="84">
					<%if(request.getAttribute("botaoAtualizar") != null){ %>
						<input name="atualizar" type="checkbox"	checked="checked" value="1"> <strong>Atualizar</strong>
					<%} %>
					</td>
				</tr>
			</table>
			<table width="100%">
				<tr>
					<td><strong>Matrícula do Imóvel:</strong></td>
					<td><html:text property="idImovel" size="9" maxlength="9"
						onkeyup="javascript:return validaEnterImovel(event, 'exibirFiltrarDevolucaoAction.do', 'idImovel');"
						onkeypress="document.forms[0].descricaoImovel.value='';" />
					<a href="javascript:habilitarPesquisaImovel(document.forms[0]);" >
							<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Matrícula do Imóvel"/></a>	
					 <logic:present name="matriculaInexistente"
						scope="request">
						<html:text property="descricaoImovel" size="35" maxlength="35"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="matriculaInexistente"
						scope="request">
						<html:text property="descricaoImovel" size="35" maxlength="35"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparImovel();"><img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>

				<tr>
					<td><strong>Código do Cliente:</strong></td>
					<td><html:text property="idCliente" size="9" maxlength="9"
						onkeyup="javascript:return validaEnterCliente(event, 'exibirFiltrarDevolucaoAction.do', 'idCliente');"
						onkeypress="document.forms[0].nomeCliente.value='';" />
					<a href="javascript:habilitarPesquisaCliente(document.forms[0]);" >
						<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Código do Cliente"/></a>		
					<logic:present name="clienteInexistente" scope="request">
						<html:text property="nomeCliente" size="35" maxlength="35"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="clienteInexistente"
						scope="request">
						<html:text property="nomeCliente" size="35" maxlength="35"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent><a href="javascript:limparCliente();"><img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td class="style1" width="49"><strong>Tipo da Relação:</strong></td>
					<td class="style1" width="133"><html:select
						property="clienteRelacaoTipo" style="width: 230px;">
						<logic:present name="colecaoClienteRelacaoTipo">
							<html:option value="" />
							<html:options collection="colecaoClienteRelacaoTipo"
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Localidade Inicial:</strong></td>
					<td><html:text property="idLocalidadeInicial" size="4"
						maxlength="4"
						onkeyup="javascript:duplicaLocalidade(document.forms[0]); return validaEnterLocalidade(event, 'exibirFiltrarDevolucaoAction.do', 'idLocalidadeInicial','Localidade Inicial');" 
						onkeypress="document.forms[0].descricaoLocalidadeInicial.value='';document.forms[0].descricaoLocalidadeFinal.value='';"/>
					<a href="javascript:habilitarPesquisaLocalidade(document.forms[0],'origem');" >
						<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Localidade Inicial"/></a>		
					<logic:present
						name="localidadeInicialInexistente" scope="request">
						<html:text property="descricaoLocalidadeInicial" size="40"
							maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent
						name="localidadeInicialInexistente" scope="request">
						<html:text property="descricaoLocalidadeInicial" size="40"
							maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent><a href="javascript:limparLocalidadeInicial();"><img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Localidade Final:</strong></td>
					<td><html:text property="idLocalidadeFinal" size="4" maxlength="4"
						onkeyup="javascript:return validaEnterLocalidade(event, 'exibirFiltrarDevolucaoAction.do', 'idLocalidadeFinal','Localidade Final');"
						onkeypress="document.forms[0].descricaoLocalidadeFinal.value='';" />
					<a href="javascript:habilitarPesquisaLocalidade(document.forms[0],'destino');" >
						<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Localidade Final"/></a>		
					 <logic:present
						name="localidadeFinalInexistente" scope="request">
						<html:text property="descricaoLocalidadeFinal" size="40"
							maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent
						name="localidadeFinalInexistente" scope="request">
						<html:text property="descricaoLocalidadeFinal" size="40"
							maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent><a href="javascript:limparLocalidadeInicial();"><img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Aviso Bancário:</strong></td>

					<td colspan="3" class="style1"><html:text
						property="codigoAgenteArrecadador" size="3" maxlength="3"
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /> <html:text
						property="dataLancamentoAviso" size="10" maxlength="10"
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /> <html:text
						property="numeroSequencialAviso" size="3" maxlength="3"
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /> 
						<a href="javascript:habilitarPesquisaAvisoBancario(document.forms[0]);" >
							<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Aviso Bancário"/></a>		
						<a href="javascript:limparAvisoBancario();"><img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a><html:hidden
						property="idAvisoBancario" /></td>
				</tr>
				
				
				<%if(request.getAttribute("tela") != null){ 
				%>					
				<%}else{ %>
					<tr>
					<td><strong>Opção de Devolução:</strong></td>
					<td colspan="3"><strong> <span class="style1"><strong> 
					<html:radio property="indicadorOpcaoDevolucao" value="1" /> <strong> Atual 
					<html:radio property="indicadorOpcaoDevolucao" value="2" /> Histórico
					<html:radio property="indicadorOpcaoDevolucao" value="3" /> Ambos
					</strong></strong></span></strong></td>
				</tr>
				<%} %>
				
				
				
				<tr>
					<td colspan="4" class="style1" height="24">
					<hr>
					</td>
				</tr>
				<tr>
					<td class="style1"><strong>Período de Refer. Arrecadação:</strong></td>

					<td colspan="3" class="style1"><strong> <html:text maxlength="7"
						property="periodoArrecadacaoInicio" size="7"
						onkeyup="mascaraAnoMes(this, event); duplicaPeriodoArrecadacao(document.forms[0]);" />
					<strong> a</strong> <html:text maxlength="7"
						property="periodoArrecadacaoFim" size="7"
						onkeyup="mascaraAnoMes(this, event);" /> </strong> mm/aaaa</td>
				</tr>
				<tr>
					<td class="style1"><strong>Período de Data Devolução:</strong></td>

					<td colspan="3" class="style1"><strong> <html:text maxlength="10"
						property="dataDevolucaoInicio" size="10"
						onkeyup="mascaraData(this, event); duplicaDataDevolucao(document.forms[0]);"
						onfocus="duplicaDataDevolucao(document.forms[0]);" /> <a
						href="javascript:abrirCalendario('FiltrarDevolucaoActionForm', 'dataDevolucaoInicio'); document.forms[0].dataDevolucaoInicio.focus();">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					a</strong> <html:text maxlength="10" property="dataDevolucaoFim"
						size="10" onkeyup="mascaraData(this, event);" /> <a
						href="javascript:abrirCalendario('FiltrarDevolucaoActionForm', 'dataDevolucaoFim');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa</td>
				</tr>
				<tr>
					<td colspan="4" class="style1" height="24">
					<hr>
					</td>

				</tr>
				<tr>
					<td class="style1"><strong>Situação da Devolução:</strong></td>
					<td colspan="4" class="style1"><strong> <html:select
						property="devolucaoSituacao" style="width: 230px;"
						multiple="mutiple" size="4">
						<logic:present name="colecaoDevolucaoSituacao">
							<html:option value="" />
							<html:options collection="colecaoDevolucaoSituacao"
								labelProperty="descricaoDevolucaoSituacao" property="id" />
						</logic:present>
					</html:select> </strong></td>
				</tr>
				<tr>
					<td class="style1"><strong>Tipo de Crédito:</strong></td>
					<td colspan="4" class="style1"><strong> <html:select
						property="creditoTipo" style="width: 230px;" multiple="mutiple"
						size="4">
						<logic:present name="colecaoCreditoTipo">
							<html:option value="" />
							<html:options collection="colecaoCreditoTipo"
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select> </strong></td>
				</tr>
				<tr>
					<td class="style1"><strong>Tipo do Documento:</strong></td>
					<td colspan="4" class="style1"><strong> <html:select
						property="documentoTipo" style="width: 230px;" multiple="mutiple"
						size="4">
						<logic:present name="colecaoDocumentoTipo">
							<html:option value="" />
							<html:options collection="colecaoDocumentoTipo"
								labelProperty="descricaoDocumentoTipo" property="id" />
						</logic:present>
					</html:select></strong></td>
				</tr>
				<tr>
					<td colspan="4" class="style1" height="24">
					<hr>
					</td>

				</tr>
				<tr>
					<td align="left"><font color="#ff0000"> 
						<%if(request.getAttribute("tela") != null){ 
							session.removeAttribute("telaManter");
						%>					
							<input name="Submit22" class="bottonRightCol" value="Limpar" type="button" onclick="window.location.href='/gsan/exibirFiltrarDevolucaoAction.do?menu=sim&tela=<%= request.getAttribute("tela") %>';">
						<%}else{ %>
							<input name="Submit22" class="bottonRightCol" value="Limpar" type="button" onclick="window.location.href='/gsan/exibirFiltrarDevolucaoAction.do?menu=sim';">
						<%} %>
						</font></td>

					<td align="right">
					  <gsan:controleAcessoBotao name="Button" value="Filtrar" onclick="javascript:validarForm(document.forms[0]);" url="filtrarDevolucaoAction.do" tabindex="16" />
					  <%-- <INPUT type="button" onClick="javascript:validarForm(document.forms[0])" name="botaoFiltrar" class="bottonRightCol" value="Filtrar" tabindex="16" style="width: 70px;"> --%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=filtrarDevolucaoAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
