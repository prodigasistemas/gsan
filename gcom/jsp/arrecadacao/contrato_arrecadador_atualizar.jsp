<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@page isELIgnored="false"%>

<%@ page import="java.util.Collection,gcom.util.ConstantesSistema"%>
<%@ page import="gcom.util.Util"%>
<%@ page import="java.util.Date"%>
<%@ page import="gcom.arrecadacao.ArrecadadorContrato"%>
<%@ page import="gcom.arrecadacao.ArrecadadorContratoTarifa"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>


<script language="JavaScript">

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];
	if (tipoConsulta == 'cliente') {
        form.idCliente.value = codigoRegistro;
        form.nomeCliente.value = descricaoRegistro;
     }
}

function habilitarPesquisaCliente(form) {
	if (form.idCliente.disabled == false) {
		chamarPopup('exibirPesquisarClienteAction.do', 'cliente', null, null, 275, 480, '',form.idCliente.value);
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

function limparForm(tipo){
     var form = document.forms[0];
	if(tipo == 'cliente')
    { 
        form.idCliente.value = "";
        form.nomeCliente.value = "";
	}
}

function validarForm(formulario){
		if(validateAtualizarContratoArrecadadorActionForm(formulario)){
   			if(validateInteger(formulario)){
   				if (validarMotivoCancelamento(formulario)){
   					submeterFormPadrao(formulario);
   				}	
   			}	
		}
}

function validarMotivoCancelamento(form){
	
	retorno = true;
	
	if (form.dataContratoEncerramento.value.length > 0 &&
		form.contratoMotivoCancelamento.value == "-1"){
		
		alert("Informe Motivo Cancelamento");
		form.contratoMotivoCancelamento.focus();
		
		retorno = false;
	}
	else if (form.dataContratoEncerramento.value.length < 1 &&
			form.contratoMotivoCancelamento.value != "-1"){
	
		alert("Informe Data de Encerramento do Contrato");
		form.dataContratoEncerramento.focus();
		
		retorno = false;
	}
	
	return retorno;
}

	function IntegerValidations () {
		this.aa = new Array("numeroContrato", "O campo N�mero do Contrato deve conter apenas n�meros.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("idCliente", "O campo Cliente deve conter apenas n�meros.", new Function ("varName", " return this[varName];"));
		this.ac = new Array("numeroDiaFloat", "O campo Quantidade de dias FLOAT deve conter apenas n�meros.", new Function ("varName", " return this[varName];"));
	}
	
	function caracteresespeciais() {
		
		this.ad = new Array("numeroDiaFloat", "Quantidade de dias FLOAT deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
	}


	function limparContaBancaria(form, contaBancaria) {
		if(contaBancaria == 'arrecadacao'){
			form.idContaBancariaArrecadador.value = "";
	    	form.bcoArrecadadorConta.value = "";
	    	form.agArrecadadorConta.value = "";
	    	form.numeroArrecadadorConta.value = "";
		}else if(contaBancaria == 'tarifa'){
			form.idContaBancariaTarifa.value = "";
	    	form.bcoTarifaConta.value = "";
	    	form.agTarifaConta.value = "";
	    	form.numeroTarifaConta.value = "";
		}
		if(contaBancaria == 'arrecadacao2'){
			form.idContaBancariaArrecadador2.value = "";
	    	form.bcoArrecadadorConta2.value = "";
	    	form.agArrecadadorConta2.value = "";
	    	form.numeroArrecadadorConta2.value = "";
		}
	}

    function recuperarDadosCincoParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, descricaoRegistro3, tipoConsulta) {
    	var form = document.forms[0];
	    if (tipoContaBancaria == 'arrecadacao') {
	    	form.idContaBancariaArrecadador.value = codigoRegistro;
	        form.bcoArrecadadorConta.value = descricaoRegistro1;
	        form.agArrecadadorConta.value = descricaoRegistro2;
	        form.numeroArrecadadorConta.value = descricaoRegistro3;
	      }else{
	         if (tipoContaBancaria == 'tarifa') {
		    	form.idContaBancariaTarifa.value = codigoRegistro;
		        form.bcoTarifaConta.value = descricaoRegistro1;
		        form.agTarifaConta.value = descricaoRegistro2;
		        form.numeroTarifaConta.value = descricaoRegistro3;
	         }abrirPopup('contaBancariaPesquisarAction.do?tipo=contaBancaria');
	      }
	    if (tipoContaBancaria == 'arrecadacao2') {
	    	form.idContaBancariaArrecadador2.value = codigoRegistro;
	        form.bcoArrecadadorConta2.value = descricaoRegistro1;
	        form.agArrecadadorConta2.value = descricaoRegistro2;
	        form.numeroArrecadadorConta2.value = descricaoRegistro3;
	     }
    }
	
    function pesquisaTipoConta(tipo) {
    	tipoContaBancaria = tipo;
    	abrirPopup('contaBancariaPesquisarAction.do?tipo=contaBancaria');
    }  

	function adicionarObjeto(){

		var form = document.forms[0];
		
		var objFormaArrecadacao = form.formaDeArrecadacao;
		var objNmDiasFloat = form.numeroDiaFloat;
		var objValorTarifa = form.valorTarifa;
		var objValorTarifaPercentual = form.valorTarifaPercentual;
		
		if( objFormaArrecadacao.value != '-1' ) {

			if( (objValorTarifa.value != "" || objValorTarifaPercentual.value != "") 
					 && objNmDiasFloat.value != "" ) {
				form.action = 'exibirAtualizarContratoArrecadadorAction.do?acao=adicionar';
				form.submit();
			}else if (objValorTarifa.value == "" && objNmDiasFloat.value != "" 
						&& objValorTarifaPercentual.value == "") {
					alert("Informe Valor Tarifa"); 
			}else if (objValorTarifa.value != "" && objValorTarifaPercentual.value != "" 
						&& objNmDiasFloat.value == "" ) {
					alert("Informe Quantidade de dias FLOAT"); 
			} else {
					alert("Informe Valor Tarifa \nInforme Quantidade de dias FLOAT");
			}
		} else {
			alert("Informe Forma de Arrecada��o");
		}
	}
	
	/* Remove componente da Colecao contrato tarifa */
	function remover(obj){
	
		var form = document.forms[0];
		
		if (confirm('Confirma Remo��o?')) {
	
			form.action = 'exibirAtualizarContratoArrecadadorAction.do?acao=remover&id='+obj;
			form.submit();
		}
	}
	function desabilitaCampo(){
		var form = document.forms[0];
		var objFormaArrecadacao = form.formaDeArrecadacao;
	   
	    if( objFormaArrecadacao.value == '-1' ){	        	        
		 	form.numeroDiaFloat.disabled = true;
		 	form.valorTarifa.disabled = true;
	 		form.numeroDiaFloat.value = "";
		 	form.valorTarifa.value = "";
		 	
		 	form.valorTarifaPercentual.disabled = true;
			form.valorTarifaPercentual.value = "";
			
		  }else{
		 	form.numeroDiaFloat.disabled = false;
		 	form.valorTarifa.disabled = false;
		 	form.valorTarifaPercentual.disabled = false;
		 }		
	}
	 function numeroPositivo() { 
    		
   		var form = document.forms[0];

   		if( form.numeroDiaFloat.value <= '0000' && form.numeroDiaFloat.value != '' ){
   			alert("Quantidade de Dias FLOAT deve somente conter n�meros positivos.");
   			form.numeroDiaFloat.value = "";
   		} 
   		
   		if( ( form.valorTarifa.value <= '0,00' && form.valorTarifa.value != '' ) ||
	   		( form.valorTarifa.value == '00' && form.valorTarifa.value != '' ) ) {
   		
   					alert("Valor da Tarifa deve somente conter n�meros positivos.");
		   			form.valorTarifa.value = "";
   		} 
	}

	function bloqueiaValorTarifa(){
		
		var form = document.forms[0];
		
		if (form.valorTarifa.value != ""){
			
			form.valorTarifaPercentual.disabled = true;
			form.valorTarifaPercentual.value = "";
			
		}else{
			
			form.valorTarifaPercentual.disabled = false;
		
		}
		
		if (form.valorTarifaPercentual.value != ""){
		
			form.valorTarifa.disabled = true;
			form.valorTarifa.value = "";
		
		}else{
		
			form.valorTarifa.disabled = false;
		
		}
	
	}
   	 
</script>
</head>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js">
</script>
<html:javascript staticJavascript="false"
	formName="AtualizarContratoArrecadadorActionForm" />

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}'); desabilitaCampo();">

<html:form action="/atualizarContratoArrecadadorAction.do"
	name="AtualizarContratoArrecadadorActionForm"
	type="gcom.gui.arrecadacao.AtualizarContratoArrecadadorActionForm"
	method="post"
	onsubmit="return validateAtualizarContratoArrecadadorActionForm(this);">

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
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Atualizar Contrato de Arrecadador</td>
					<td width="11" valign="top"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para atualizar o contrato de arrecadador, informe
					os dados abaixo:</td>
				</tr>

				<tr>
					<td width="32%" class="style3">
						<strong>Arrecadador:<font color="#FF0000">*</font></strong>
					</td>
					<td width="68%" colspan="2">
						<html:select property="idClienteCombo" tabindex="1" style="width:200px;">
						<html:option value="-1"> &nbsp; </html:option>
						<html:options collection="colecaoClienteArrecadador" 
									  property="id"
									  labelProperty="nome" />
						</html:select>
					</td>
				</tr>

				<tr>
					<td width="32%" class="style3">
						<strong>N�mero Contrato:<font color="#FF0000">*</font></strong>
					</td>
					<td width="68%" colspan="2"><strong><b><span class="style2"> 
					<html:text property="numeroContrato" size="10" maxlength="10" tabindex="2" 
						onkeypress="return isCampoNumerico(event);"/></span></b></strong>
					</td>
				</tr>
				<tr>
					<td width="32%"><strong>Conta Dep�sito Arrecada��o:<font
						color="#FF0000">*</font></strong></td>
					<html:hidden property="idContaBancariaArrecadador" />
					<td width="68%"><html:text maxlength="4"
						property="bcoArrecadadorConta" size="4" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					<html:text maxlength="9" property="agArrecadadorConta" size="9"
						readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					<html:text maxlength="20" property="numeroArrecadadorConta"
						size="20" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					<a href="javascript:pesquisaTipoConta('arrecadacao');"> <img
						width="23" height="21"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0"
						title="Pesquisar Dep�sito Arrecada��o" /></a> <a
						href="javascript:limparContaBancaria(document.forms[0],'arrecadacao');">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Limpar Dep�sito Arrecada��o" /></a></td>
				</tr>

				<tr>
					<td width="32%"><strong>Conta Dep�sito Tarifa:<font color="#FF0000">*</font></strong></td>
					<html:hidden property="idContaBancariaTarifa" />
					<td width="68%"><html:text maxlength="4" property="bcoTarifaConta"
						size="4" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					<html:text maxlength="9" property="agTarifaConta" size="9"
						readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					<html:text maxlength="20" property="numeroTarifaConta" size="20"
						readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					<a href="javascript:pesquisaTipoConta('tarifa');"> <img width="23"
						height="21"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0"
						title="Pesquisar Dep�sito Tarifa" /></a> <a
						href="javascript:limparContaBancaria(document.forms[0],'tarifa');">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Limpar Dep�sito Tarifa" /></a></td>
				</tr>

				<tr>
					<td><strong>Cliente:<font color="#FF0000">*</font></strong></td>
					<td><strong> 
						<html:text  property="idCliente" 
									size="10"
									maxlength="10" 
									tabindex="3"
									onkeypress="javascript:validaEnter(event, 'exibirAtualizarContratoArrecadadorAction.do?objetoConsulta=1', 'idCliente');return isCampoNumerico(event);" />
						</strong> 
							<a href="javascript:habilitarPesquisaCliente(document.forms[0]);"
								alt="Pesquisar Cliente"> <img width="23" height="21"
								src="<bean:message key='caminho.imagens'/>pesquisa.gif" 
								border="0" 
								title="Pesquisar Cliente"/>
							</a>
						<logic:present name="existeCliente">
							<logic:equal name="existeCliente" value="exception">
								<html:text  property="nomeCliente" 
											size="28" 
											maxlength="28"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="existeCliente" value="exception">
								<html:text  property="nomeCliente" 
											size="28" 
											maxlength="28"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						<logic:notPresent name="existeCliente">
							<logic:empty name="AtualizarContratoArrecadadorActionForm" property="idCliente">
								<html:text  property="nomeCliente" 
											size="28"
											 
											value=""
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:empty>
							<logic:notEmpty name="AtualizarContratoArrecadadorActionForm" property="idCliente">
								<html:text  property="nomeCliente" 
											size="28" 
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent> 
							<a  href="javascript:limparForm('cliente');"> 
								<img border="0"
									 src="<bean:message key='caminho.imagens'/>limparcampo.gif"
									 style="cursor: hand;" /> 
							</a>
					</td>
				</tr>

				<tr>
					<td width="32%" class="style3"><strong>E-Mail:<font color="#FF0000"></font></strong></td>
					<td width="68%" colspan="2"><strong><b><span class="style2"> <html:text
						property="descricaoEmail" size="50" maxlength="40" tabindex="4" />
					</span></b></strong></td>
				</tr>

				<tr>
					<td width="32%" class="style3"><strong>C�digo do Conv�nio:<font
						color="#FF0000"></font></strong></td>
					<td width="68%" colspan="2"><strong><b><span class="style2"> <html:text
						property="idConvenio" size="28" maxlength="20" tabindex="5" 
						onkeypress="return isCampoNumerico(event);"/> </span></b></strong></td>
				</tr>

				<tr>
					<td width="32%"><strong>Indicador de Cobran�a de ISS:</strong></td>
					<td width="68%"><strong> <span class="style1"><strong> <html:radio
						property="indicadorCobranca" value="1" tabindex="6" /> <strong>Cobra
					ISS <html:radio property="indicadorCobranca" value="2" tabindex="7" />N�o
					Cobra ISS </strong></strong></span></strong></td>
				</tr>

				<tr>
					<td><strong>Data In�cio do contrato:</strong></td>
					<td><html:text property="dtInicioContrato" size="10" maxlength="10"
						tabindex="7"
						onkeyup="mascaraData(this, event);replicarCampo(document.forms[0].dtFimContrato,this);"
						onkeypress="return isCampoNumerico(event);"
						onfocus="replicarCampo(document.forms[0].dtFimContrato,this);" />
					<a href="javascript:abrirCalendarioReplicando('AtualizarContratoArrecadadorActionForm', 'dtInicioContrato', 'dtFimContrato');">
						<img border="0"
							src="<bean:message key="caminho.imagens"/>calendario.gif"
							width="20" border="0" align="absmiddle" alt="Exibir Calend�rio" title="Exibir Calend�rio" />
					</a>
					dd/mm/aaaa</td>
				</tr>

				<tr>
					<td><strong>Data Fim do Contrato:</strong></td>
					<td><html:text property="dtFimContrato" size="10" maxlength="10"
						tabindex="8" onkeyup="mascaraData(this, event);" 
						onkeypress="return isCampoNumerico(event);"/> <a
						href="javascript:abrirCalendario('AtualizarContratoArrecadadorActionForm', 'dtFimContrato')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calend�rio" title="Exibir Calend�rio"/></a>
					dd/mm/aaaa</td>
				</tr>

				<tr>
					<td><strong>Data de Encerramento do Contrato:</strong></td>
					<td><html:text property="dataContratoEncerramento" size="10"
						maxlength="10" tabindex="8" onkeyup="mascaraData(this, event);"
						onkeypress="return isCampoNumerico(event);" />
					<a
						href="javascript:abrirCalendario('AtualizarContratoArrecadadorActionForm', 'dataContratoEncerramento')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calend�rio" title="Exibir Calend�rio"/></a>
					dd/mm/aaaa</td>
				</tr>

				<tr>
					<td width="32%" class="style3"><strong>Motivo Cancelamento:</strong></td>
					<td width="68%" colspan="2"><html:select
						property="contratoMotivoCancelamento" tabindex="1"
						style="width:325px;">
						<html:option value="-1"> &nbsp; </html:option>
						<html:options collection="colecaoFiltroMotivoCancelamento"
							property="id" labelProperty="descricaoMotivoCancelContrato" />
					</html:select></td>
				</tr>
				<tr>
					<td width="32%" class="style3"><strong>Tamanho M�ximo para
					Identifica��o do Im�vel:<font color="#FF0000">*</font></strong></td>
					<td width="68%" colspan="2"><html:text
						property="tamanhoMaximoIdentificacaoImovel" size="2" maxlength="2"
						tabindex="9" 
						onkeypress="return isCampoNumerico(event);"/></td>
				</tr>
				
				<tr>
					<td height="10" colspan="3">
						<hr>
					</td>
				</tr>
				
				<tr>
					<td width="32%" class="style3"><strong>Forma:<font color="#FF0000">*</font></strong></td>
					<td width="78%" colspan="2">
						<html:select property="formaDeArrecadacao" 
									tabindex="1" 
									onclick="javascript:desabilitaCampo();"
									style="width:400px;">
						<html:option value="-1"> &nbsp; </html:option>
						<logic:present name="colecaoFormaArrecadacao" scope="session">
							<html:options collection="colecaoFormaArrecadacao" property="id" labelProperty="descricao" />
						</logic:present>
				  		</html:select>
				   </td>
				</tr>
				
				<tr>
					<td><strong>Valor da Tarifa:<font color="#FF0000">*</font></strong></td>
					<td width="68%" colspan="2"><strong><b><span class="style2"> 
					  <html:text property="valorTarifa" 
					  			size="15" 
					  			maxlength="17" 
					  			tabindex="10"
					  			onblur="javascript:desabilitaCampo();numeroPositivo();"
					  			onkeypress="formataValorMonetario( this, 17 );bloqueiaValorTarifa();return isCampoNumerico(event);" 
								onkeyup="formataValorMonetario( this, 17 );bloqueiaValorTarifa();"
								style="text-align: right;" />
						</span></b></strong>
					</td>
				</tr>
				
				<tr>
					<td><strong>Percentual da Tarifa:<font color="#FF0000">*</font></strong></td>
					<td width="68%" colspan="2"><strong><b><span class="style2">
					<html:text property="valorTarifaPercentual" 
					  			size="15" 
					  			maxlength="6" 
					  			tabindex="11"
					  			onblur="javascript:desabilitaCampo();numeroPositivo();"
					  			onkeypress="formataValorMonetario( this, 6 );bloqueiaValorTarifa();return isCampoNumerico(event);" 
								onkeyup="formataValorMonetario( this, 6 );bloqueiaValorTarifa();"
								style="text-align: right;" />
						</span></b></strong>
					</td>
				</tr>
				
				<tr>
					<td><strong>Quantidade de dias FLOAT:<font color="#FF0000">*</font></strong></td>
					<td width="68%" colspan="2"><strong><b><span class="style2"> 
					  <html:text property="numeroDiaFloat" 
					  			 size="4" 
  					  			 onblur="javascript:desabilitaCampo();numeroPositivo();"
					  			 maxlength="4" 
					  			 tabindex="10"
					  			 style="text-align: right;" 
					  			 onkeyup="javascript:verificaNumeroInteiro(this);bloqueiaValorTarifa();"
					  			 onkeypress="bloqueiaValorTarifa();"
					  	/></span></b></strong>
					</td>
				</tr>
				
				<tr>
					<td width="32%">
						<strong>Conta Dep�sito Arrecada��o:<font color="#FF0000">*</font></strong>
					</td>
					<html:hidden property="idContaBancariaArrecadador2"/>
					<td width="68%">
						<html:text maxlength="4" property="bcoArrecadadorConta2" size="4" readonly="true" 
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
						<html:text maxlength="9" property="agArrecadadorConta2" size="9" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
						<html:text maxlength="20" property="numeroArrecadadorConta2" size="20" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
						<a href="javascript:pesquisaTipoConta('arrecadacao2');">
							<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Conta Banc�ria" />
						</a>
						<a href="javascript:limparContaBancaria(document.forms[0],'arrecadacao2');">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"	border="0" title="Limpar Conta Banc�ria"/>
						</a>
				  	</td>
				</tr>
				
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td><strong>Tarifas Contrato:</strong></td>
					<td colspan="3" align="right">
							<input type="button" class="bottonRightCol" value="Adicionar"
							tabindex="25" style="width: 80px; align: right;"
							onclick="javascript:adicionarObjeto();" name="botaoAdicionarArrecadacaoContratoTarifa">
					</td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td>
							<table width="100%" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td align="center" width="5%">&nbsp;</td>
									<td width="30%">
										<div align="left"><strong>Forma</strong></div>
									</td>
									<td width="20%">
										<div align="left"><strong>Valor da Tarifa</strong></div>
									</td>
									<td width="10%">
										<div align="left"><strong>Perc. da Tarifa</strong></div>
									</td>
									<td width="10%">
										<div align="left"><strong>N. de dias FLOAT</strong></div>
									</td>
									<td width="10%">
										<div align="left"><strong>Ag</strong></div>
									</td>
									<td width="15%">
										<div align="left"><strong>Conta</strong></div>
									</td>
								</tr>
							</table>
							</td>
						</tr>
						
						<tr>
							<td>
					
							<%String cor = "#FFFFFF";%> 
							<logic:present name="colecaoArrecadadorContratoTarifaSelecionados" scope="session">
		
								<div style="width: 100%; height: 100; overflow: auto;">
		
								<table width="100%" cellpadding="0" cellspacing="0">
									<tr>
										<td><%cor = "#cbe5fe";%>
		
										<table width="100%" align="center" bgcolor="#99CCFF">
		
											<logic:iterate name="colecaoArrecadadorContratoTarifaSelecionados" 
												id="arrecadadorContratoTarifa"
												type="ArrecadadorContratoTarifa">
												<c:set var="count" value="${count+1}" />
												<c:choose>
													<c:when test="${count%2 == '1'}">
														<tr bgcolor="#FFFFFF">
													</c:when>
													<c:otherwise>
														<tr bgcolor="#cbe5fe">
													</c:otherwise>
												</c:choose>
		
													<td align="center" width="5%">
														<img
														src="<bean:message key='caminho.imagens'/>Error.gif" 
														style="cursor: hand;"
														onclick="remover('${count}')"
														title="Remover" >
														</td>
														
															
													<td width="30%">
														<div align="left">
															<logic:present name="arrecadadorContratoTarifa" property="arrecadacaoForma">
																<bean:write name="arrecadadorContratoTarifa" property="arrecadacaoForma.descricao" />
															</logic:present> 
		
															<logic:notPresent name="arrecadadorContratoTarifa" property="arrecadacaoForma">&nbsp;</logic:notPresent>
														</div>
													</td>
													
													<td width="20%">
														<div align="left">
															<logic:present name="arrecadadorContratoTarifa" property="valorTarifa">
																<%= Util.formatarMoedaReal(arrecadadorContratoTarifa.getValorTarifa())%>
																</logic:present> 
															<logic:notPresent name="arrecadadorContratoTarifa" property="valorTarifa">&nbsp;</logic:notPresent>
														</div>
													</td>
													
													<td width="10%">
														<div align="left">
															<logic:present name="arrecadadorContratoTarifa" property="valorTarifaPercentual">
																<%= Util.formatarMoedaReal(arrecadadorContratoTarifa.getValorTarifaPercentual())%>
																</logic:present> 
															<logic:notPresent name="arrecadadorContratoTarifa" property="valorTarifaPercentual">&nbsp;</logic:notPresent>
														</div>
													</td>
													
													<td width="10%">
														<div align="left">
															<logic:present name="arrecadadorContratoTarifa" property="numeroDiaFloat">
																<bean:write name="arrecadadorContratoTarifa" property="numeroDiaFloat" />
															</logic:present> 
		
															<logic:notPresent name="arrecadadorContratoTarifa" property="numeroDiaFloat">&nbsp;</logic:notPresent>
														</div>
													</td>
													
													<td width="10%">
														<div align="left">
															<logic:present name="arrecadadorContratoTarifa" property="agArrecadadorConta2">
																<bean:write name="arrecadadorContratoTarifa" property="agArrecadadorConta2" />
															</logic:present> 
		
															<logic:notPresent name="arrecadadorContratoTarifa" property="agArrecadadorConta2">&nbsp;</logic:notPresent>
														</div>
													</td>
													
													<td width="15%">
														<div align="left">
															<logic:present name="arrecadadorContratoTarifa" property="numeroArrecadadorConta2">
																<bean:write name="arrecadadorContratoTarifa" property="numeroArrecadadorConta2" />
															</logic:present> 
		
															<logic:notPresent name="arrecadadorContratoTarifa" property="numeroArrecadadorConta2">&nbsp;</logic:notPresent>
														</div>
													</td>
		
											</logic:iterate>
		
										</table>
		
										</td>
									</tr>
		
								</table>
		
								</div>
							</logic:present>
							
							</td>
						</tr>
					</table>
					
					</td>
				</tr>

				<tr>
				</tr>
				<tr>
				</tr>
				<tr>
				</tr>
				<tr>
					<td width="500" colspan="2">
					<div align="center"><font color="#FF0000">*</font> Campos
					obrigat�rios</div>
					</td>
				</tr>

			</table>
			<table width="100%">
				<tr>
					<td width="40%" align="left"><input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Voltar"
						onClick="javascript:window.location.href='${sessionScope.caminhoRetornoVoltar}';">
					<input type="button" name="ButtonReset" class="bottonRightCol"
						value="Desfazer"
						onclick="window.location.href='/gsan/exibirAtualizarContratoArrecadadorAction.do?desfazer=S&reloadPage=1&idArrecadador=<bean:write name="AtualizarContratoArrecadadorActionForm" property="idArrecadador" />';">
					<input type="button" name="ButtonCancelar" class="bottonRightCol"
						value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right"><input type="button" name="Button"
						class="bottonRightCol" value="Atualizar"
						onclick="validarForm(document.forms[0]);" tabindex="13" /></td>
				</tr>
			</table>
		</tr>


	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</html:html>

