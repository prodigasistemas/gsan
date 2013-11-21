<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.Util" %>
<%@ page import="gcom.cobranca.CobrancaCriterioLinha" %>

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
	
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="CriterioCobrancaActionForm" dynamicJavascript="false" />

<script language="JavaScript">

 var bCancel = false;

    function validateCriterioCobrancaActionForm(form) {
        if (bCancel)
      return true;
        else
      return validateRequired(form) && validateCaracterEspecial(form)&& testarCampoValorZero(form.numeroAnoContaAntiga, 'Número de Anos para Determinar Conta Antiga') && validateLong(form) 
		&& validateDate(form) && validaTodosRadioButton()&& validateDecimal(form); 
   }

    function caracteresespeciais () {
     this.aa = new Array("descricaoCriterio", "Descrição do Critério de Cobrança possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     
    }

    function IntegerValidations () {
     this.ac = new Array("numeroAnoContaAntiga", "Número de Anos para Determinar Conta Antiga deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    function required () { 
	this.aa = new Array("descricaoCriterio", "Informe Descrição do Critério de Cobrança.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("dataInicioVigencia", "Informe Data de Início de Vigência do Critério.", new Function ("varName", " return this[varName];"));
	this.ac = new Array("numeroAnoContaAntiga", "Informe Número de Anos para Determinar Conta Antiga.", new Function ("varName", " return this[varName];"));
	this.af = new Array("valorLimitePrioridade", "Informe Valor Limite para Prioridade.", new Function ("varName", " return this[varName];"));
	this.ad = new Array("percentualValorMinimoPagoParceladoCancelado", "Informe Percentual Valor.", new Function ("varName", " return this[varName];"));
	this.ae = new Array("percentualQuantidadeMinimoPagoParceladoCancelado", "Informe  Percentual Quantidade de Itens.", new Function ("varName", " return this[varName];"));

	} 
    function DateValidations () { 
	  this.aa = new Array("dataInicioVigencia", "Data de Início de Vigência do Critério inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    } 
    
function validaRadioButton(nomeCampo,mensagem){
	var alerta = "";
	if(!nomeCampo[0].checked && !nomeCampo[1].checked){
		alerta = mensagem +" deve ser selecionado.";
	}
	return alerta;
}
function validaTodosRadioButton(){
	var form = document.forms[0];
	var mensagem = "";
	if(validaRadioButton(form.opcaoAcaoImovelSitEspecial,"Emissão da Ação para Imóvel com Sit. Especial de Cobrança") != ""){
			mensagem = mensagem + validaRadioButton(form.opcaoAcaoImovelSitEspecial,"Emissão da Ação para Imóvel com Situação Especial de Cobrança")+"\n";
	}
	if(validaRadioButton(form.opcaoAcaoImovelSit,"Emissão da Ação para Imóvel com Sit. de Cobrança") != ""){
		mensagem = mensagem + validaRadioButton(form.opcaoAcaoImovelSit,"Emissão da Ação para Imóvel com Situação de Cobrança")+"\n";
	}
	if(validaRadioButton(form.opcaoContasRevisao,"Considerar Contas em Revisão") != ""){
		mensagem = mensagem + validaRadioButton(form.opcaoContasRevisao,"Considerar Contas em Revisão")+"\n";
	}
	if(validaRadioButton(form.opcaoAcaoImovelDebitoMesConta,"Emissão da Ação para Imóvel com Débito só da Conta do Mês") != ""){
		mensagem = mensagem + validaRadioButton(form.opcaoAcaoImovelDebitoMesConta,"Emissão da Ação para Imóvel com Débito só da Conta do Mês")+"\n";
	}
	if(validaRadioButton(form.opcaoAcaoInquilinoDebitoMesConta,"Emissão da Ação para Inquilino Com Débito só da Conta do Mês Independentemente do Valor da Conta") != ""){
		mensagem = mensagem + validaRadioButton(form.opcaoAcaoInquilinoDebitoMesConta,"Emissão da Ação para Inquilino Com Débito só da Conta do Mês Independentemente do Valor da Conta")+"\n";
	}
	if(validaRadioButton(form.opcaoAcaoImovelDebitoContasAntigas,"Emissão da Ação para Imóvel com Débito só de Contas Antigas") != ""){
		mensagem = mensagem + validaRadioButton(form.opcaoAcaoImovelDebitoContasAntigas,"Emissão da Ação para Imóvel com Débito só de Contas Antigas")+"\n";
	}
 
	if(mensagem != ""){
		alert(mensagem);
		return false;
	}else{
		return true;
	}
}
	function FloatValidations () {
	 this.ap = new Array("valorLimitePrioridade", "Valor Limite para Prioridade deve somente conter números decimais positivos.",new Function ("varName", " return this[varName];"));
	 this.an = new Array("percentualValorMinimoPagoParceladoCancelado", "Percentual Valor deve somente conter números decimais positivos.",new Function ("varName", " return this[varName];"));
	 this.ao = new Array("percentualQuantidadeMinimoPagoParceladoCancelado", "Percentual Quantidade de Itens deve somente conter números decimais positivos.",new Function ("varName", " return this[varName];"));
	
	} 

function validarForm(form){

if(validateCriterioCobrancaActionForm(form)){
    form.target='';
    form.action="atualizarCriterioCobrancaAction.do";
    submeterFormPadrao(form);
}
}

function abrirPopupComSubmit(form,altura, largura){
 var height = window.screen.height - 160;
 var width = window.screen.width;
 var top = (height - altura)/2;
 var left = (width - largura)/2;
 window.open('', 'Pesquisar','top=' + top + ',left='+ left +',location=no,screenY=0,screenX=0,menubar=no,status=no,toolbar=no,scrollbars=yes,resizable=no,width=' + largura + ',height=' + altura);
 form.target='Pesquisar';
 form.action = 'exibirAdicionarCriterioCobrancaLinhaAction.do?limparPopup=SIM&retornarTela=exibirAtualizarCriterioCobrancaAction.do?chamarReload=1';
 submeterFormPadrao(form);
}

function verificaValorMaior(minimo,maximo){
 if(minimo!= "" && maximo!= ""){
  minimo = minimo * 1;
  maximo = maximo * 1;
  if(minimo > maximo){
   alert('Valor máximo de débito é menor do que o valor mínimo do débito.');
  }
 }
}

function verificaQuantidadeMaior(minimo,maximo){
 if(minimo!= "" && maximo!= ""){
  minimo = minimo * 1;
  maximo = maximo * 1;
  if(minimo > maximo){
   alert('Quantidade máxima de contas é menor do que a quantidade mínima de contas.');
  }
 }
}
function desfazer(){
 form = document.forms[0];
 form.target="";
 form.action='exibirAtualizarCriterioCobrancaAction.do?limpaSessao=1';
 form.submit();


}

function habilitarSelect(radio, select){
   var valorRd = radio.value;
   if (valorRd == '1'){
     select.disabled = false;
   } else {
   	 select.disabled = true;
 	 select.selectedIndex = -1;
   }
}

</script>

<style>
.fontePequena {
font-size: 11px;
face: Verdana, Arial, Helvetica, sans-serif;
}

.fonteMenor {
font-size: 9px;
face: Verdana, Arial, Helvetica, sans-serif;
}

</style>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/atualizarCriterioCobrancaAction"
	name="CriterioCobrancaActionForm"
	type="gcom.gui.cobranca.CriterioCobrancaActionForm"
	method="post">

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
			<td width="600" valign="top" bgcolor="#003399" class="centercoltext">
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
					<td class="parabg">Atualizar Critério de Cobrança</td>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">
					<p>Para atualizar um critério de cobrança, informe os dados abaixo:</p>
					<p>&nbsp;</p>
					</td>
				</tr>
				<tr>
					<td width="40%"><strong>Descrição do Critério de Cobrança:<font color="#FF0000">*</font><strong></td>
					<td>
					<html:text property="descricaoCriterio"	size="30" maxlength="30" tabindex="1"/>
					</td>
				</tr>
				
				<tr> 
                  <td><strong>Data de In&iacute;cio de Vig&ecirc;ncia do Crit&eacute;rio:<font color="#FF0000">*</font></strong></td>
				     <td width="40%">
				     <logic:present name="desabilita">
				      <html:text maxlength="10" property="dataInicioVigencia" size="10" disabled="true"/>(dd/mm/aaaa)
				     </logic:present>
				     <logic:notPresent name="desabilita">
				       <html:text maxlength="10" property="dataInicioVigencia" size="10" onkeyup="javascript:mascaraData(this,event);" tabindex="2"/>
                       <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" onclick="abrirCalendario('CriterioCobrancaActionForm', 'dataInicioVigencia')" width="20" border="0" align="absmiddle" alt="Exibir Calendário" /> dd/mm/aaaa
                     </logic:notPresent>
                  </td>
              </tr>
              <tr> 
                <td><p><strong>N&uacute;mero de Anos para Determinar Conta Antiga</strong><strong>:<font color="#FF0000">*</font></strong></p></td>
                <td width="40%">
                  <logic:present name="desabilita">
                   <html:text property="numeroAnoContaAntiga" size="2" maxlength="2" tabindex="3" disabled="true"/>
                  </logic:present>
                  <logic:notPresent name="desabilita">
                   <html:text property="numeroAnoContaAntiga" size="2" maxlength="2" tabindex="3"/>
                  </logic:notPresent>  
                </td>

              </tr>
              
              
              
              
              <tr> 
	                  <td><strong>Valor Limite para Prioridade:<font color="#FF0000">*</font></strong></td>
               		 <logic:present name="desabilita">
               		 <td>
	                  		<html:text property="valorLimitePrioridade" size="14"
							maxlength="14" tabindex="4" 
							onkeyup="formataValorMonetario(this, 14)" 
							style="text-align:right;" disabled="true" />
				  	  </td>
					 </logic:present>
					 
					 <logic:notPresent name="desabilita">
					 <td>
	                  		<html:text property="valorLimitePrioridade" size="14"
							maxlength="14" tabindex="4" 
							onkeyup="formataValorMonetario(this, 14)" 
							style="text-align:right;" />
				  	  </td>
					 </logic:notPresent>
                  </tr>
                 
                 
                 <tr> 
	                  <td><strong>Documento Pago/Parcelado/Cancelado:</strong></td>
	             </tr>     
                 <tr>
	                 <td colspan="2">
		                 <table width="100%">
		                 
		                 
		             <tr> 
		             <td width="2%">&nbsp;</td>
	                  <td width="48%"><strong>  Percentual Valor:<font color="#FF0000">*</font></strong></td>
					 
					 <logic:present name="desabilita">
					 	<td>
	                  		<html:text property="percentualValorMinimoPagoParceladoCancelado" size="6"
							maxlength="6" tabindex="5" 
							onkeyup="formataValorMonetario(this, 6)" 
							style="text-align:right;" disabled="true" />
				  	   </td>
					 </logic:present>
					 
					 <logic:notPresent name="desabilita">
					 	<td>
	                  		<html:text property="percentualValorMinimoPagoParceladoCancelado" size="6"
							maxlength="6" tabindex="5" 
							onkeyup="formataValorMonetario(this, 6)" 
							style="text-align:right;" />
				  	    </td>
					 </logic:notPresent>
					  
                  </tr>
                 
                  <tr> 
                 	 <td width="2%">&nbsp;</td>
	                  <td width="48%"><strong>  Percentual Quantidade de Itens:<font color="#FF0000">*</font></strong></td>
					 <logic:present name="desabilita">
					 <td>
	                  		<html:text property="percentualQuantidadeMinimoPagoParceladoCancelado" size="6"
							maxlength="6" tabindex="6" 
							onkeyup="formataValorMonetario(this, 6)" 
							style="text-align:right;" disabled="true"/>
				  	  </td>
					 </logic:present>
					 
					 <logic:notPresent name="desabilita">
					 <td>
	                  		<html:text property="percentualQuantidadeMinimoPagoParceladoCancelado" size="6"
							maxlength="6" tabindex="6" 
							onkeyup="formataValorMonetario(this, 6)" 
							style="text-align:right;" />
				  	  </td>
					 </logic:notPresent>
                  </tr>
		                 
		                 
		                 </table>
	                 </td>
                 </tr>
                 
                   
                  
                          
              <tr> 
                <td height="24" colspan="2"></td>
              </tr>
              <tr> 
                <td colspan="2">
                <table width="100%">
                 <tr>
                  <td width="65%"><strong>Emiss&atilde;o da A&ccedil;&atilde;o para Im&oacute;vel 
                        com Situação Especial de Cobran&ccedil;a:<font color="#FF0000">*</font></strong></td>
                  <logic:present name="desabilita">
                    <td><html:radio property="opcaoAcaoImovelSitEspecial" value="1" tabindex="7" disabled="true"/> 
                      <strong>Sim</strong></td>
                    <td><html:radio property="opcaoAcaoImovelSitEspecial" value="2" tabindex="8" disabled="true"/> 
                      <strong>N&atilde;o</strong></td>
                  </logic:present>
                  <logic:notPresent name="desabilita">
                     <td><html:radio property="opcaoAcaoImovelSitEspecial" value="1" tabindex="7"/> 
                        <strong>Sim</strong></td>
                     <td><html:radio property="opcaoAcaoImovelSitEspecial" value="2" tabindex="8"/> 
                       <strong>N&atilde;o</strong></td>
                  </logic:notPresent>
                 </tr>
                 <tr> 
                  <td width="65%"><strong>Emiss&atilde;o da A&ccedil;&atilde;o para Im&oacute;vel 
                       com Situação de Cobran&ccedil;a:<font color="#FF0000"></font><font color="#FF0000">*</font></strong></td>

                  <logic:present name="desabilita">
					<td><html:radio property="opcaoAcaoImovelSit" value="1" tabindex="9" disabled="true"/> 
                      <strong>Sim</strong></td>
                    <td><html:radio property="opcaoAcaoImovelSit" value="2" tabindex="10" disabled="true"/> 
                      <strong>N&atilde;o</strong></td>
                  </logic:present>
                  <logic:notPresent name="desabilita">
                     <td><html:radio property="opcaoAcaoImovelSit" value="1" tabindex="9" onclick="javascript:habilitarSelect(this, document.forms[0].idsCobrancaSituacao)" /> 
                      <strong>Sim</strong></td>
                  <td><html:radio property="opcaoAcaoImovelSit" value="2" tabindex="10" onclick="javascript:habilitarSelect(this, document.forms[0].idsCobrancaSituacao)"/> 
                      <strong>N&atilde;o</strong></td>
                  </logic:notPresent>                  
                 </tr>
                 <tr>
					<td width="65%"><strong>Situa&ccedil;&atilde;o de cobran&ccedil;a:<font color="#FF0000"></font><font color="#FF0000">*</font></strong></td>                 
                 	<td colspan="2">
                 	<logic:present name="desabilita">
                 		<html:select property="idsCobrancaSituacao" multiple="multiple" disabled="true"
                 		  name="CriterioCobrancaActionForm">
              				<html:options collection="colecaoCobrancaSituacao" labelProperty="descricao" property="id"/>
             			</html:select>
             		</logic:present>
                 	<logic:notPresent name="desabilita">
	             		<logic:equal name="CriterioCobrancaActionForm" property="opcaoAcaoImovelSit" value="1">
	                 		<html:select property="idsCobrancaSituacao" multiple="multiple">
	              				<html:options collection="colecaoCobrancaSituacao" labelProperty="descricao" property="id"/>
	             			</html:select>	             		
	             		</logic:equal>
	             		<logic:equal name="CriterioCobrancaActionForm" property="opcaoAcaoImovelSit" value="2">                 	
	                 		<html:select property="idsCobrancaSituacao" multiple="multiple" disabled="true">
	              				<html:options collection="colecaoCobrancaSituacao" labelProperty="descricao" property="id"/>
	             			</html:select>	             		
	             		</logic:equal>	             		
             		</logic:notPresent>             		
                 	</td>
                 </tr>                 
                 <tr> 
                  <td width="65%"><strong>Considerar Contas em Revis&atilde;o:<font color="#FF0000"></font><font color="#FF0000">*</font></strong></td>
                  
                  <logic:present name="desabilita">
					<td><html:radio property="opcaoContasRevisao" value="1" tabindex="11" disabled="true"/> 
                      <strong>Sim</strong></td>
                    <td><html:radio property="opcaoContasRevisao" value="2" tabindex="12" disabled="true"/> 
                      <strong>N&atilde;o</strong></td>
                  </logic:present>
                  <logic:notPresent name="desabilita">
                     <td><html:radio property="opcaoContasRevisao" value="1" tabindex="11"/> 
                      <strong>Sim</strong></td>
                  <td><html:radio property="opcaoContasRevisao" value="2" tabindex="12"/> 
                      <strong>N&atilde;o</strong></td>
                  </logic:notPresent>                  
                 </tr>
                 
                             
                 
                 
                 <tr> 
                  <td width="65%"><strong>Emiss&atilde;o da A&ccedil;&atilde;o para Im&oacute;vel 
                      com D&eacute;bito s&oacute; da Conta do M&ecirc;s:<font color="#FF0000"></font><font color="#FF0000">*</font></strong></td>
                  
                  <logic:present name="desabilita">
					<td><html:radio property="opcaoAcaoImovelDebitoMesConta" value="1" tabindex="13" disabled="true"/> 
                      <strong>Sim</strong></td>
                    <td><html:radio property="opcaoAcaoImovelDebitoMesConta" value="2" tabindex="14" disabled="true"/> 
                      <strong>N&atilde;o</strong></td>
                  </logic:present>
                  <logic:notPresent name="desabilita">
                     <td><html:radio property="opcaoAcaoImovelDebitoMesConta" value="1" tabindex="13"/> 
                      <strong>Sim</strong></td>
                  <td><html:radio property="opcaoAcaoImovelDebitoMesConta" value="2" tabindex="14"/> 
                      <strong>N&atilde;o</strong></td>
                  </logic:notPresent>                  
                 </tr>
                 <tr> 
                  <td width="65%"><strong>Emiss&atilde;o da A&ccedil;&atilde;o para Inquilino 
                      Com D&eacute;bito s&oacute; da Conta do M&ecirc;s Independentemente 
                      do Valor da Conta:<font color="#FF0000">*</font></strong></td>

                  <logic:present name="desabilita">
					<td><html:radio property="opcaoAcaoInquilinoDebitoMesConta" value="1" tabindex="15" disabled="true"/> 
                      <strong>Sim</strong></td>
                    <td><html:radio property="opcaoAcaoInquilinoDebitoMesConta" value="2" tabindex="16" disabled="true"/> 
                      <strong>N&atilde;o</strong></td>
                  </logic:present>
                  <logic:notPresent name="desabilita">
                     <td><html:radio property="opcaoAcaoInquilinoDebitoMesConta" value="1" tabindex="15"/> 
                      <strong>Sim</strong></td>
                  <td><html:radio property="opcaoAcaoInquilinoDebitoMesConta" value="2" tabindex="16"/> 
                      <strong>N&atilde;o</strong></td>
                  </logic:notPresent>                  
                 </tr>
                 <tr> 
                  <td width="65%"><strong>Emiss&atilde;o da A&ccedil;&atilde;o para Im&oacute;vel 
                      com D&eacute;bito s&oacute; de Contas Antigas:<font color="#FF0000">*</font></strong></td>
                      
                  <logic:present name="desabilita">
					<td><html:radio property="opcaoAcaoImovelDebitoContasAntigas" value="1" tabindex="17" disabled="true"/> 
                      <strong>Sim</strong></td>
                    <td><html:radio property="opcaoAcaoImovelDebitoContasAntigas" value="2" tabindex="18" disabled="true"/> 
                      <strong>N&atilde;o</strong></td>
                  </logic:present>
                  <logic:notPresent name="desabilita">
                     <td><html:radio property="opcaoAcaoImovelDebitoContasAntigas" value="1" tabindex="17"/> 
                      <strong>Sim</strong></td>
                  <td><html:radio property="opcaoAcaoImovelDebitoContasAntigas" value="2" tabindex="18"/> 
                      <strong>N&atilde;o</strong></td>
                  </logic:notPresent>                  
               </tr>
               
               <tr>
					<td width="65%"><strong>Situa&ccedil;&atilde;o de Liga&ccedil;&atilde;o de &Aacute;gua:<font color="#FF0000"></font><font color="#FF0000">*</font></strong></td>                 
                 	<td colspan="2">
                 	<logic:present name="desabilita">
                 		<html:select property="idsSituacaoLigacaoAgua" multiple="multiple" disabled="true">
              				<html:options collection="colecaoSituacaoLigacaoAgua" labelProperty="descricao" property="id"/>
             			</html:select>
           			</logic:present>
                 	<logic:notPresent name="desabilita">
                 		<html:select property="idsSituacaoLigacaoAgua" multiple="multiple">
              				<html:options collection="colecaoSituacaoLigacaoAgua" labelProperty="descricao" property="id"/>
             			</html:select>
           			</logic:notPresent>
                 	</td>
                 </tr> 
               
               <tr>
					<td width="65%"><strong>Situa&ccedil;&atilde;o de Liga&ccedil;&atilde;o de Esgoto:<font color="#FF0000"></font><font color="#FF0000">*</font></strong></td>                 
                 	<td colspan="2">
                 	<logic:present name="desabilita">                 	
                 		<html:select property="idsSituacaoLigacaoEsgoto" multiple="multiple" disabled="true">
              				<html:options collection="colecaoSituacaoLigacaoEsgoto" labelProperty="descricao" property="id"/>
             			</html:select>
             		</logic:present>
                 	<logic:notPresent name="desabilita">                 	
                 		<html:select property="idsSituacaoLigacaoEsgoto" multiple="multiple">
              				<html:options collection="colecaoSituacaoLigacaoEsgoto" labelProperty="descricao" property="id"/>
             			</html:select>
             		</logic:notPresent>             		
                 	</td>
                 </tr> 
                 
               
               
               
                 </table>
                </td>
               </tr>  
			   <tr> 
                <td height="24" colspan="2"></td>
               </tr>
               <tr> 
                <td><strong>Linhas do Crit&eacute;rio<font color="#FF0000">*</font></strong></td>
                <td align="right"> 
                <logic:present name="desabilita">
                  <input type="button" name="Submit24" class="bottonRightCol" value="Adicionar" disabled="disabled"> 
                </logic:present>
                <logic:notPresent name="desabilita">
                <input type="button" name="Submit24" class="bottonRightCol" value="Adicionar" onClick="abrirPopupComSubmit(document.forms[0],'','');" tabindex="19"> 
                </logic:notPresent>  
                </td>
               </tr>

			   <tr>
				<td colspan="2">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr bordercolor="#000000"> 
					      <td bgcolor="#90c7fc" align="center" width="15%" height="20"><div align="center"><strong>Remover</strong></div></td>
					      <td bgcolor="#90c7fc" width="40%" height="20"><strong>Perfil do Imóvel</strong></td>
					      <td bgcolor="#90c7fc" width="40%" height="20"><strong>Categoria</strong></td>
					   </tr>
						<logic:present name="colecaoCobrancaCriterioLinha">
							<tr>
								<td colspan="3">

								<div style="width: 100%; height: 100%; overflow: auto;">
								<table width="100%" bgcolor="#99CCFF">

									<%int cont = 1;%>
									<logic:iterate name="colecaoCobrancaCriterioLinha"
										id="cobrancaCriterioLinha" type="CobrancaCriterioLinha">
										<%cont = cont + 1;
							if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

							%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="15%">
													<logic:present name="desabilita">
														<div align="center"><font color="#333333"> <img width="14"
															height="14" border="0"
															src="<bean:message key="caminho.imagens"/>Error.gif" />
														</font></div>
													</logic:present>
													
													<logic:notPresent name="desabilita">
														<div align="center"><font color="#333333"> <img width="14"
														height="14" border="0"
														src="<bean:message key="caminho.imagens"/>Error.gif"
														onclick="javascript:document.forms[0].target='';if(confirm('Confirma remoção?')){redirecionarSubmit('removerCriterioCobrancaLinhaAction.do?codigoCobrancaCriterioLinha=<bean:write name="cobrancaCriterioLinha" property="imovelPerfil.id"/>,<bean:write name="cobrancaCriterioLinha" property="categoria.id"/>&tipoRetorno=atualizar');}" />
														</font></div>
													</logic:notPresent>
											
											
											
												
											</td>
											<td width="40%"><bean:write name="cobrancaCriterioLinha" property="imovelPerfil.descricao"/></td>
											<td width="40%">
												<logic:present name="desabilita">
													<a href="javascript:abrirPopup('exibirAtualizarCriterioCobrancaLinhaAction.do?parmsImovelPerfilCobranca=<bean:write name="cobrancaCriterioLinha" property="imovelPerfil.id"/>,<bean:write name="cobrancaCriterioLinha" property="categoria.id"/>&retornarTela=exibirAtualizarCriterioCobrancaAction.do?chamarReload=1','','')"><bean:write name="cobrancaCriterioLinha" property="categoria.descricao"/></a>
												</logic:present>
												
												<logic:notPresent name="desabilita">
													<a href="javascript:abrirPopup('exibirAtualizarCriterioCobrancaLinhaAction.do?parmsImovelPerfilCobranca=<bean:write name="cobrancaCriterioLinha" property="imovelPerfil.id"/>,<bean:write name="cobrancaCriterioLinha" property="categoria.id"/>&retornarTela=exibirAtualizarCriterioCobrancaAction.do?chamarReload=1','','')"><bean:write name="cobrancaCriterioLinha" property="categoria.descricao"/></a>		
												</logic:notPresent>
											</td>
										</tr>										
											
									</logic:iterate>

								</table>
								</div>
								</td>
							</tr>
						</logic:present>
					</table>
					</td>
				</tr>				<tr>
					<td colspan="2">
					</td>
				</tr>
				<tr>
					<td colspan="2"><strong>Indicador de Uso:</strong>&nbsp;
					<html:radio
						property="indicadorUso" value="1" tabindex="20" /> <strong>Ativo</strong>
					<html:radio property="indicadorUso" value="2" /> <strong>Inativo </strong>
					</td>
					
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td valign="top">
						<logic:present name="voltar">
						<logic:equal name="voltar" value="filtrar">
							<input name="Button" type="button" class="bottonRightCol"
							value="Voltar" align="left"
							onclick="document.forms[0].target='';window.location.href='<html:rewrite page="/exibirFiltrarCriterioCobrancaAction.do"/>'">
						</logic:equal>
						<logic:equal name="voltar" value="manter">
							<input name="Button" type="button" class="bottonRightCol"
							value="Voltar" align="left"
							onclick="document.forms[0].target='';window.location.href='<html:rewrite page="/exibirManterCriterioCobrancaAction.do"/>'">
						</logic:equal>
					</logic:present>
					<logic:notPresent name="voltar">
						<input name="Button" type="button" class="bottonRightCol"
						value="Voltar" align="left"
						onclick="document.forms[0].target='';window.location.href='<html:rewrite page="/exibirManterCriterioCobrancaAction.do"/>'">
					</logic:notPresent>
					&nbsp;
					<input name="button" type="button"
						class="bottonRightCol" value="Desfazer" onclick="desfazer('${requestScope.voltar}');">
						&nbsp;<input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:document.forms[0].target='';window.location.href='/gsan/telaPrincipal.do'"></td>
					<td valign="top">
					  <div align="right">
					    <gsan:controleAcessoBotao name="botaoInserir" value="Atualizar" onclick="validarForm(document.forms[0]);" url="atualizarCriterioCobrancaAction.do" tabindex="17"/>
					    <%-- <input name="botaoInserir" type="button" class="bottonRightCol" value="Atualizar" onclick="validarForm(document.forms[0]);" tabindex="17"> --%>
					  </div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
