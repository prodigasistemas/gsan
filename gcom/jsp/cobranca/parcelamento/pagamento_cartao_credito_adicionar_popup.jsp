<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.util.ConstantesSistema"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<html:javascript staticJavascript="false" formName="ParcelamentoCartaoConfirmarForm" />

<script language="JavaScript">

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		
		var form = document.forms[0];
    
    	if (tipoConsulta == 'cliente') {
        	form.idCliente.value = codigoRegistro;
        	form.nomeCliente.value = descricaoRegistro;
    	}
	}

	function validar(){
		
		var form = document.forms[0];	
	 	
	 	if( validateNumeroCartaoCredito(form) && validateValidadeCartao(form) ){
	 	
	 		submeterFormPadrao(form);	
 	 	}
	}

	function validateValidadeCartao(form){
	
		if(form.validadeCartaoInvalida.value=="true"){
			alert("Mês/Ano de validade do cartão de crédito informado está inválido.");
			form.validadeCartao.value="";
			return false;
		}
		else{
			return true;
		}
	}


	function validateNumeroCartaoCredito(form){

		var reDigits = /^\d+$/;
		
		if (reDigits.test(form.numeroCartao.value)) {
			return true;
		} 
		else {
			alert('Número do Cartão de Crédito deve conter somente números.');
			return false;
		}
	}

	function desfazer(){
 		
 		var form = document.forms[0];
 	
 		form.cartaoCredito.value="-1";
		form.idCliente.value="";
		form.nomeCliente.value="";
		form.autorizacaoCartao.value="";
		form.validadeCartao.value="";
		form.documentoCartao.value="";
		form.numeroIdentificacaoTransacao.value="";
		form.numeroCartao.value="";
		form.valorTransacao.value="";
	}

	function validaData(){

		var form = document.forms[0];
		var idParcelamento = '';
	
		for(indice = 0; indice < form.elements.length; indice++){
		
			if (form.elements[indice].type == "radio" && form.elements[indice].checked == true) {
				idParcelamento = form.elements[indice].value;
			}
		}
	
 		form.action='exibirAdicionarPagamentoCartaoCredito.do?validaData=sim';
 		form.submit();
	}

	function chamarReload(){	
	
		chamarSubmitComUrlSemUpperCase('/gsan/exibirParcelamentoCartaoCreditoConfirmarAction.do');
	}

	function limparPesquisaCliente(){
	
		var form = document.forms[0];
		form.nomeCliente.value = "";
		form.idCliente.value = "";	
	}

	function reloadPagina(){
		var form = document.forms[0];
		form.action = 'exibirAdicionarPagamentoCartaoCredito.do?pesquisaSubmit=sim';
		form.submit();
	}

	function verificaPesquisa(nome){
		
 		if(nome=='sim'){ 			
 			redirecionarSubmitSemUpperCase('exibirPesquisarClienteAction.do?indicadorUsoTodos=1&caminhoRetornoTelaPesquisaCliente=exibirAdicionarPagamentoCartaoCredito');
 		}
	
	}

		
	function caracteresespeciais () {
	     this.am = new Array("idCliente", "Código deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }

    function required () {
    	  this.ab = new Array("qtdParcelas", "Informe a quantidade de parcelas.", new Function ("varName", " return this[varName];"));
          this.ac = new Array("valorTransacao", "Informe o valor da transacao.", new Function ("varName", " return this[varName];"));
          this.ad = new Array("cartaoCredito", "Informe Cartão Crédito.", new Function ("varName", " return this[varName];"));
          this.ae = new Array("idCliente", "Informe o Cliente.", new Function ("varName", " return this[varName];"));
          this.af = new Array("numeroCartao", "Informe o Numero do Cartão Crédito.", new Function ("varName", " return this[varName];"));
          this.ag = new Array("validadeCartao", "Informe a Validade do Cartão Crédito.", new Function ("varName", " return this[varName];"));
          this.ah = new Array("documentoCartao", "Informe o Número do Documento Cartão.", new Function ("varName", " return this[varName];"));
          this.ai = new Array("autorizacaoCartao", "Informe Número Autorização Cartão.", new Function ("varName", " return this[varName];"));
          this.aj = new Array("numeroIdentificacaoTransacao", "Informe o Numero de Identificação Transação.", new Function ("varName", " return this[varName];"));
          this.al = new Array("valorTransacao", "Informe o Valor da Transação.", new Function ("varName", " return this[varName];"));
    }

    function DateValidations () {
      this.aa = new Array("dataOperadora", "Data da confirmação na operadora invalida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    }

</script>

</head>

<logic:present name="reload" scope="request">
	<body leftmargin="5" topmargin="5"
		onload="resizePageSemLink(710,535);chamarReload();window.close();">
</logic:present>

<logic:notPresent name="reload">
	<body leftmargin="0" topmargin="0"
		onload="window.focus();resizePageSemLink(710,535);verificaPesquisa('${requestScope.pesquisaSubmit}')">
</logic:notPresent>

<html:form action="/adicionarPagamentoCartaoCredito" 
	name="ParcelamentoCartaoConfirmarForm"
	type="gcom.gui.cobranca.ParcelamentoCartaoConfirmarForm"
	method="post">
	
	<html:hidden property="validadeCartaoInvalida" />
	
	<table width="702" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="100%" valign="top" class="centercoltext">
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
					<td class="parabg">Adicionar Transação por Cartão de Crédito/Débito</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Preencha os campos para adicionar um pagamento com
					cartão de crédito/débito:</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td width="30%"><strong>Cartão de Crédito:<font color="#FF0000">*</font></strong></td>

					<td><strong> <html:select property="cartaoCredito"
						style="width: 230px;" tabindex="1">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>

						<logic:present name="colecaoCartao" scope="session">
							<html:options collection="colecaoCartao"
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select> </strong></td>
				</tr>
				<tr>
					<td><strong>Cliente Titular do Cartão:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="3" height="24"><html:text maxlength="9"
						property="idCliente" tabindex="2" size="9"
						onkeypress="javascript:validaEnter(event,'exibirAdicionarPagamentoCartaoCredito.do?pesquisaCliente=sim','idCliente');" />
					
					
					<a href="JavaScript:reloadPagina();">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Cliente" /></a> <logic:present
						name="idClienteNaoEncontrado">
						<logic:equal name="idClienteNaoEncontrado" value="exception">
							<html:text property="nomeCliente" size="35" maxlength="35"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="idClienteNaoEncontrado" value="exception">
							<html:text property="nomeCliente" size="35" maxlength="35"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="idClienteNaoEncontrado">
						<logic:empty name="ParcelamentoCartaoConfirmarForm"
							property="idCliente">
							<html:text property="nomeCliente" value="" size="35"
								maxlength="35" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="ParcelamentoCartaoConfirmarForm"
							property="idCliente">
							<html:text property="nomeCliente" size="35" maxlength="35"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a href="javascript:limparPesquisaCliente();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Número do Cartão:<font color="#FF0000">*</font></strong></td>
					<td><html:text maxlength="25" property="numeroCartao" size="35"
						tabindex="3"/></td>
				</tr>
				<tr>
					<td><strong>Validade do Cartão:<font color="#FF0000">*</font></strong></td>
					<td><strong> <html:text maxlength="7" property="validadeCartao"
						size="7" onkeyup="mascaraAnoMes(this, event);"
						onblur="validaData()" tabindex="4"/></strong> (mm/aaaa)</td>
				<tr>
					<td><strong>Número Documento Cartão:<font
						color="#FF0000">*</font></strong></td>
					<td><html:text maxlength="11" property="documentoCartao" size="11"
						tabindex="5" /></td>
				</tr>
				<tr>
					<td><strong>Número Autorização Cartão:<font
						color="#FF0000">*</font></strong></td>
					<td><html:text maxlength="11" property="autorizacaoCartao"
						size="11" tabindex="6" /></td>
				</tr>
				<tr>
					<td><strong>Número de Identificação da Transação (NSU):<font
						color="#FF0000">*</font></strong></td>
					<td><html:text maxlength="11"
						property="numeroIdentificacaoTransacao" size="11" tabindex="7" /></td>
				</tr>
				<tr>
					<td><strong>Valor da Transação:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="valorTransacao" size="20" onkeyup="formataValorMonetario(this, 14)" 
					tabindex="8"/></td>
				</tr>
				<tr>
					<td><strong>Data da confirmação na operadora:<font
						color="#FF0000">*</font></strong></td>
					<td><html:text property="dataOperadora" size="11" tabindex="9"
					onkeyup="mascaraData(this, event)" maxlength="10"/>
					<a href="javascript:abrirCalendario('ParcelamentoCartaoConfirmarForm', 'dataOperadora');">
					<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/></a></td>
				</tr>
				<tr>
					<td><strong>Quantidade de Parcelas:<font
						color="#FF0000">*</font></strong></td>
					<td><html:text readonly="true"
					   style="background-color:#EFEFEF; border:0"
					   property="qtdParcelas" size="20" tabindex="1"/></td>
				</tr>
				<tr>
					<td></td>
					<td><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</td>
				</tr>
				<tr>
					<td height="24" colspan="3" width="80%"><input type="button"
						onclick="window.close()" class="bottonRightCol" value="Fechar"
						style="width: 70px;"></td>
					<td align="right"><input name="Adicionar" type="button"
						class="bottonRightCol" value="Adicionar" onclick="validar();"></td>
					<td>&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	
</html:form>

</body>

</html:html>
