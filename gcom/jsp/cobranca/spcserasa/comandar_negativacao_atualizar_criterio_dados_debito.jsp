<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AtualizarComandoNegativacaoPorCriterioActionForm" dynamicJavascript="false" />

<SCRIPT LANGUAGE="JavaScript">

	var bCancel = false;
	
	function validateAtualizarComandoNegativacaoPorCriterioActionForm(form) {
		var DATA_ATUAL = document.getElementById("DATA_ATUAL").value;
		var REFERENCIA_DI = form.referenciaInicial.value.substring(3,7) + form.referenciaInicial.value.substring(0,2);
		var REFERENCIA_DF = form.referenciaFinal.value.substring(3,7) + form.referenciaFinal.value.substring(0,2);
		var REFERENCIA_A = DATA_ATUAL.substring(6,10) + DATA_ATUAL.substring(3,5);
		var MES_ANO_C = DATA_ATUAL.substring(3,5) + DATA_ATUAL.substring(5,6) + DATA_ATUAL.substring(6,10);		
		
		if(validaMesAno(form.referenciaInicial) && validaMesAno(form.referenciaFinal) && 
				validaParcelamentoAtraso() && validaCartaPrcelamentoAtraso(form)){
			if (comparaData("01/"+form.referenciaInicial.value, ">", "01/" + form.referenciaFinal.value )){
			  	alert('Referência Final do Período é anterior à Referência Inicial do Período.');		
			  	return false;	
			}else if (comparaData(form.dataVencimentoInicial.value, ">", form.dataVencimentoFinal.value)){
			  alert('Data Final do Período é anterior à Data Inicial do Período.');			
			  	return false;				  
			}else if (REFERENCIA_A < REFERENCIA_DI){
			    alert("Mês/Ano Inicial do Período de Referência posterior ao Mês e Ano corrente " + MES_ANO_C + ".");
			    return false;	
		    } else if (REFERENCIA_A < REFERENCIA_DF){
			    alert("Mês/Ano Final do Período de Referência posterior ao Mês e Ano corrente " + MES_ANO_C + ".");
			  	return false;				    
		    }else if (comparaData(form.dataVencimentoInicial.value, ">", DATA_ATUAL )){
				alert("Data Inicial do Período de Vencimento Débito posterior à Data corrente " + DATA_ATUAL + ".");
			  	return false;					
			}else if (comparaData(form.dataVencimentoFinal.value, ">", DATA_ATUAL )){
				alert("Data Final do Período de Vencimento Débito posterior à Data corrente " + DATA_ATUAL + ".");
			  	return false;					
			}else if(parseInt(obterNumerosSemVirgulaEPonto(form.valorDebitoInicial.value)) > parseInt(obterNumerosSemVirgulaEPonto(form.valorDebitoFinal.value))){
			    alert("Valor do Débito Final é menor que o Valor do Débito Inicial");
			  	return false;				    
			}else if(form.numeroContasInicial.value > form.numeroContasFinal.value){
			    alert("Número de Conta Final é menor que o Número de Contas Inicial");
			  	return false;				    
			}else{
				return true;
			}
	    }
	}
	
	function validaMesAno(mesAno){	
	  if(mesAno.value != ""){
		return verificaAnoMesMensagemPersonalizada(mesAno,"Referência inválida");
	  }else{
		return true;
	  }
    }
    
	function validarPeriodoVencimento(data){
		if (data.value.length > 0){
			return verificaDataMensagemPersonalizada(data, "Período de Vencimentodo Débito inválido.");				
		}else{
			return true;		
		}
	}
	
	function validaParcelamentoAtraso(){
	var form = document.forms[0];
	  if(form.parcelaAtraso[0].checked && (form.diasAtrasoParcelamento.value == null || form.diasAtrasoParcelamento.value == "")){
		alert("Informe Dias em Atraso de Parcelamento");
		return false;			
	  }else{
		return true;
	  }
    }	
    
    function validaCartaPrcelamentoAtraso(form){		
	  if(form.cartaParcelamentoAtraso[0].checked && (form.diasAtrasoRecebimentoCarta.value == null || form.diasAtrasoRecebimentoCarta.value == "")){
		alert("Informe Dias em Atraso após Recebimento da Carta");
		return false;			
	  }else{
		return true;
	  }
    }
    
    function habilitarParcelamentoAtraso(){
	  var form = document.forms[0];       
	  if(form.parcelaAtraso[1].checked){
        form.diasAtrasoParcelamento.value = "";
	    form.diasAtrasoParcelamento.disabled = true;
	  }else{
	    form.diasAtrasoParcelamento.disabled = false;	    
	  }
    }

    function habilitarCartaPrcelamentoAtraso(){
	  var form = document.forms[0];      
	  if(form.cartaParcelamentoAtraso[1].checked){
	    form.diasAtrasoRecebimentoCarta.value = "";	  
	    form.diasAtrasoRecebimentoCarta.disabled = true;
	  }else{
	    form.diasAtrasoRecebimentoCarta.disabled = false;	    
	  }
    }
</SCRIPT>


</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');habilitarParcelamentoAtraso();habilitarCartaPrcelamentoAtraso();">

<div id="formDiv">
<html:form action="/atualizarComandoNegativacaPorCriterioWizardAction" method="post">

<INPUT TYPE="hidden" ID="DATA_ATUAL" value="${requestScope.dataAtual}" />

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=2"/>


<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

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

	<td width="625" valign="top" class="centercoltext">

        <table height="100%">
        <tr>
          <td></td>
        </tr>
      	</table>

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Atualizar Comando de Negativação Por Critério</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      <tr>
      	<td colspan="3">Para determinar a negativação a ser comandada, informe os dados abaixo:</td>
      </tr>
      <tr>
      	<td width="35%"><strong>Negativador:</strong></td>
       	<td colspan="2">
			<html:text property="nomeNegativador" size="50" maxlength="60" tabindex="4" readonly="true"
			style="background-color:#EFEFEF; border:0; color: #000000"/>
		</td>
	  </tr>
	  <tr>
         <td colspan="3"><hr></td>
      </tr>
	  <tr>
		<td height="10" width="35%">
			<div class="style9"><strong>Período de Referência do Débito:</strong></div>
			</td>
		<td><html:text property="referenciaInicial" 
				size="8" maxlength="7" onkeyup="mascaraAnoMes(this, event);" onkeypress="return isCampoNumerico(event);" onblur="validaMesAno(document.forms[0].referenciaInicial);"/>a <html:text property="referenciaFinal" 
				size="8" maxlength="7" onkeyup="mascaraAnoMes(this, event);" onkeypress="return isCampoNumerico(event);" onblur="validaMesAno(document.forms[0].referenciaFinal);"/> 
				mm/aaaa</td>
	  </tr>
	  <tr>
		<td width="35%"><strong>Período de Vencimento do Débito:</strong></td>

		<td><html:text property="dataVencimentoInicial" size="10"
			maxlength="10" tabindex="2"
			onkeyup="mascaraData(this, event);" onkeypress="return isCampoNumerico(event);"
			onblur="validarPeriodoVencimento(document.forms[0].dataVencimentoInicial);"/>
		<a href="javascript:abrirCalendarioReplicando('AtualizarComandoNegativacaoPorCriterioActionForm', 'dataVencimentoInicial', 'dataVencimentoFinal');" >
		<img border="0"
			src="<bean:message key="caminho.imagens"/>calendario.gif"
			width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
		a <html:text property="dataVencimentoFinal" size="10"
			maxlength="10" tabindex="3" onkeyup="mascaraData(this, event);" onkeypress="return isCampoNumerico(event);" onblur="validarPeriodoVencimento(document.forms[0].dataVencimentoFinal);"/> <a
			href="javascript:abrirCalendario('AtualizarComandoNegativacaoPorCriterioActionForm', 'dataVencimentoFinal')">
		<img border="0"
			src="<bean:message key="caminho.imagens"/>calendario.gif"
			width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
		dd/mm/aaaa</td>
	  </tr>	  
	  <tr>
		<td height="10" width="35%">
			<div class="style9"><strong>Valor do Débito:</strong></div>
			</td>
		<td><html:text property="valorDebitoInicial" 
				size="15" maxlength="17" onkeyup="formataValorMonetario(this, 13)"/>a <html:text property="valorDebitoFinal" 
				size="15" maxlength="17" onkeyup="formataValorMonetario(this, 13)"/></td>
	  </tr>
	  <tr>
		<td height="10" width="35%">
			<div class="style9"><strong>Número de Contas:</strong></div>
			</td>
		<td><html:text property="numeroContasInicial" 
				size="10" maxlength="10" onkeypress="return isCampoNumerico(event);" />a <html:text property="numeroContasFinal"
				size="10" maxlength="10" onkeypress="return isCampoNumerico(event);" /></td>
	  </tr>      
      <tr>
      	<td HEIGHT="30" width="35%"><strong>Considerar Contas em Revisão:<font color="#FF0000">*</font></strong></td>
        <td colspan="2">
			<html:radio property="contasRevisao" value="1" tabindex="1" /><strong>Sim
			<html:radio property="contasRevisao" value="2" tabindex="2" />Não</strong>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30" width="35%"><strong>Considerar Guias de Pagamento:<font color="#FF0000">*</font></strong></td>
        <td colspan="2">
			<html:radio property="guiasPagamento" value="1" tabindex="1" /><strong>Sim
			<html:radio property="guiasPagamento" value="2" tabindex="2" />Não</strong>
		</td>
      </tr>
       <tr>
      	<td HEIGHT="30" width="35%"><strong>Exigir ao Menos uma Conta em Nome do Cliente Negativado:<font color="#FF0000">*</font></strong></td>
        <td colspan="2">
			<html:radio property="indicadorContaNomeCliente" value="1" tabindex="1" /><strong>Sim
			<html:radio property="indicadorContaNomeCliente" value="2" tabindex="2" />Não</strong>
		</td>
      </tr>
      
	  <tr>
         <td colspan="3"><hr></td>
      </tr>
      <tr>
      	<td HEIGHT="30" width="35%"><strong>Parcela em Atraso:<font color="#FF0000">*</font></strong></td>
        <td colspan="2">
			<html:radio property="parcelaAtraso" value="1" tabindex="1" onclick="javascript:habilitarParcelamentoAtraso();"/><strong>Sim
			<html:radio property="parcelaAtraso" value="2" tabindex="2" onclick="javascript:habilitarParcelamentoAtraso();"/>Não</strong>
		</td>
      </tr>            
	  <tr>
		<td height="10" width="35%">
			<div class="style9"><strong>Dias em Atraso de Parcelamento:</strong></div>
			</td>
		<td><html:text property="diasAtrasoParcelamento" size="10" maxlength="10" onkeypress="return isCampoNumerico(event);"/></td>
	  </tr>         
       <tr>
      	<td HEIGHT="30" width="35%"><strong>Recebeu Carta de Parcelamento em Atraso:<font color="#FF0000">*</font></strong></td>
        <td colspan="2">
			<html:radio property="cartaParcelamentoAtraso" value="1" tabindex="1" onclick="javascript:habilitarCartaPrcelamentoAtraso();"/><strong>Sim
			<html:radio property="cartaParcelamentoAtraso" value="2" tabindex="2" onclick="javascript:habilitarCartaPrcelamentoAtraso();"/>Não</strong>
		</td>
      </tr>      
 	  <tr>
		<td height="10" width="35%">
			<div class="style9"><strong>Dias em Atraso após Recebimento da carta:</strong></div>
			</td>
		<td><html:text property="diasAtrasoRecebimentoCarta" size="10" maxlength="10" onkeypress="return isCampoNumerico(event);" /></td>
	  </tr>                    
      <tr>
         <td colspan="3"><hr></td>
      </tr>
      <tr>
      	<td colspan="3" height="10"></td>
      </tr>
      <tr>
        <td colspan="3">
			<div align="right">
				<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=2" />
			</div>
		</td>
      </tr>
      </table>
      <p>&nbsp;</p>
	</td>
  </tr>
</table>



<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>

<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan//atualizarComandoNegativacaPorCriterioWizardAction.do?concluir=true&action=atualizarComandoNegativacaoDadosDebitoAction'); }
</script>

</body>
</html:html>

