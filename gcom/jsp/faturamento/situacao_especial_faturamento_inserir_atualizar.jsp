<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.faturamento.FaturamentoSituacaoTipo"%>

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


<SCRIPT LANGUAGE="JavaScript">
<!--

function validarForm(form){
	if (validateSituacaoEspecialFaturamentoActionForm(form) && validarConsumoVolume(form)){
	    
	    if(confirm("Confirma inserção da situação especial de faturamento para " + form.quantidadeImoveisSEMSituacaoEspecialFaturamento.value + " imóvel(is) ?")){
	    	submeterFormPadrao(form);
		}	
	}
}

function validarConsumoVolume(form){

	retorno = true;
	
	var msg = "";
	
	var listBoxSituacao = form.idFaturamentoSituacaoTipo;
	
	var indicadorConsumo = (listBoxSituacao.options[listBoxSituacao.options.selectedIndex].id.split(","))[0];
 	var indicadorVolume = (listBoxSituacao.options[listBoxSituacao.options.selectedIndex].id.split(","))[1];
 	
	var consumoMedido = form.consumoFixoMedido;
	var consumoNaoMedido = form.consumoFixoNaoMedido; 	
	
	var volumeMedido = form.volumeFixoMedido;
	var volumeNaoMedido = form.volumeFixoNaoMedido; 	
	
 	if ( indicadorConsumo == 1 && indicadorVolume == 1 ){
 	
 		if ( consumoNaoMedido.value.length == 0 &&
 		 	 consumoMedido.value.length == 0 && 
 		 	 volumeNaoMedido.value.length == 0 && 
 		 	 volumeMedido.value.length == 0 ){
 		 	 msg = "Informe um dos seguintes campos: \n  - Consumo Não Medido \n  - Consumo Medido \n  - Volume Não Medido \n  - Volume Medido"; 		 	 
 		 	 
 		 	 retorno = false;
 		}	
 	} else if ( indicadorConsumo == 1 ) {
 		if ( consumoNaoMedido.value.length == 0 &&
 		 	 consumoMedido.value.length == 0 ) {
 		 	 msg = "Informe um dos seguintes campos: \n  - Consumo Não Medido \n  - Consumo Medido"; 		 	 
 		 	 
 		 	 retorno = false;
 		}
 	} else if ( indicadorVolume == 1 ){
 		if ( volumeNaoMedido.value.length == 0 && 
 		 	 volumeMedido.value.length == 0 ){
 		 	 msg = "Informe um dos seguintes campos: \n  - Volume Não Medido \n  - Volume Medido"; 		 	 
 		 	 
 		 	 retorno = false;
 		}
 	}
 	 	
 	if (!retorno){
 		alert(msg);
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

function habilitacaoCamposConsumoVolume(listBoxSituacao){
 	
 	var form = document.forms[0];
 	
 	var consumoMedido = form.consumoFixoMedido;
 	var consumoNaoMedido = form.consumoFixoNaoMedido;
 	var volumeMedido = form.volumeFixoMedido;
 	var volumeNaoMedido = form.volumeFixoNaoMedido;
 	
 	var indicadorConsumo = (listBoxSituacao.options[listBoxSituacao.options.selectedIndex].id.split(","))[0];
 	var indicadorVolume = (listBoxSituacao.options[listBoxSituacao.options.selectedIndex].id.split(","))[1];
 	
 	if (indicadorConsumo > 1){
 		consumoMedido.value = "";
 		consumoNaoMedido.value = "";
 		consumoMedido.disabled = true;
 		consumoNaoMedido.disabled = true;
 	}
 	else{
 		consumoMedido.disabled = false;
 		consumoNaoMedido.disabled = false;
 	}
 	
 	if (indicadorVolume > 1){
 		volumeMedido.value = "";
 		volumeNaoMedido.value = "";
 		volumeMedido.disabled = true;
 		volumeNaoMedido.disabled = true;
 	}
 	else{
 		volumeMedido.disabled = false;
 		volumeNaoMedido.disabled = false;
 	}
}

//-->
</SCRIPT>
<script>
<!-- Begin 

     var bCancel = false; 

    function validateSituacaoEspecialFaturamentoActionForm(form) {                                                                   
        if (bCancel) 
      return true; 
        else 
       return validateRequired(form) && validateCaracterEspecial(form) && validateMesAno(form) && validateLong(form);
       ; 
   } 

	function MesAnoValidations() {
     	this.aa = new Array("mesAnoReferenciaFaturamentoInicial", "Mês e Ano de Referência do Faturamento Inicial inválido.", new Function ("varName", " return this[varName];"));
    	this.ab = new Array("mesAnoReferenciaFaturamentoFinal", "Mês e Ano de Referência do Faturamento Final inválido.", new Function ("varName", " return this[varName];"));
    }
               


    function caracteresespeciais () { 
     this.aa = new Array("consumoFixoNaoMedido", "Consumo Não Medido possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("consumoFixoMedido", "Consumo Medido possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("volumeFixoNaoMedido", "Volume Não Medido possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("volumeFixoMedido", "Volume Medido possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("mesAnoReferenciaFaturamentoInicial", "Mês e Ano de Referência do Faturamento Inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.af = new Array("mesAnoReferenciaFaturamentoFinal", "Mês e Ano de Referência do Faturamento Final possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     
    } 
    
    function required () { 
     this.aa = new Array("idFaturamentoSituacaoTipo", "Informe Tipo da Situação Especial de Faturamento.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("idFaturamentoSituacaoMotivo", "Informe Motivo da Situação Especial de Faturamento.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("mesAnoReferenciaFaturamentoInicial", "Informe Mês e Ano de Referência do Faturamento Inicial.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("mesAnoReferenciaFaturamentoFinal", "Informe Mês e Ano de Referência do Faturamento Final.", new Function ("varName", " return this[varName];"));
    } 
    
    function IntegerValidations () { 
     this.aa = new Array("consumoFixoNaoMedido", "Consumo Não Medido deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("consumoFixoMedido", "Consumo Medido deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("volumeFixoNaoMedido", "Volume Não Medido deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("volumeFixoMedido", "Volume Medido deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    
-->    
</script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="SituacaoEspecialFaturamentoActionForm"
	dynamicJavascript="false" />

</head>

<body leftmargin="5" topmargin="5" onload="limitTextArea(document.forms[0].observacaoInforma, 100, document.getElementById('utilizado'), document.getElementById('limite'));">


<html:form
	action="/validarSituacaoEspecialFaturamentoInserirAtualizarAction"
	type="gcom.gui.faturamento.SituacaoEspecialFaturamentoActionForm"
	name="SituacaoEspecialFaturamentoActionForm" method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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

			<td width="615" valign="top" class="centercoltext">

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
					<td class="parabg">Inserir Situação Especial de Faturamento</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			
			<p>&nbsp;</p>
			
			<table border="0" width="100%">
			<tr>
				<td>Para inserir a situa&ccedil;&atilde;o especial de faturamento do(s) im&oacute;vel(is), 
				informe os dados abaixo:</td>
				<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoSituacaoEspecialInserir', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
				</logic:present>
				<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
				</logic:notPresent>
					
			</tr>
			</table>
			
			<div id="layerHideParametros" style="display:block">
				<table width="100%" align="center" bgcolor="#99CCFF" border="0">
				<tr>
					<td align="center">
						<a href="javascript:extendeTabela('Parametros',true);" />
							<strong>Par&acirc;metros Informados</strong></a>
					</td>
				</tr>
				</table>
			</div>
			
			<div id="layerShowParametros" style="display:none">
			
			<table width="100%" align="center" bgcolor="#99CCFF" border="0">
			<tr>
				<td align="center">
					<a href="javascript:extendeTabela('Parametros',false);" />
					<strong>Par&acirc;metros Informados</strong></a>
				</td>
			</tr>
			<tr bgcolor="#cbe5fe">
				<td width="100%" align="center">

				<table width="100%" border="0">
				<tr>
					<td width="20%"><strong>Matr&iacute;cula:</strong></td>
					<td colspan="5"><html:text property="idImovel" size="8"
						maxlength="8" readonly="true" style="background-color:#EFEFEF; border:0;"/></td>
				</tr>
				<tr>
					<td colspan="6" height="5"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td width="15%"><strong>Localidade </strong></td>
					<td width="15%"><strong>Setor</strong></td>
					<td width="7%"><strong>Quadra</strong></td>
					<td width="7%"><strong>Lote</strong></td>
					<td width="36%"><strong>Sublote</strong></td>
				</tr>
				
				<!-- INCRIÇÃO INICIAL -->
				<tr>
					<td><strong>Inscri&ccedil;&atilde;o Inicial:</strong></td>
					<td><html:text property="localidadeOrigemID" size="3" maxlength="3"
						readonly="true" style="background-color:#EFEFEF; border:0;"/></td>
					<td><html:text property="setorComercialOrigemCD" size="3"
						maxlength="3" readonly="true" style="background-color:#EFEFEF; border:0;"/> <html:hidden
						property="setorComercialOrigemID" /></td>
					<td><html:text property="quadraOrigemNM" size="4" maxlength="4"
						readonly="true" style="background-color:#EFEFEF; border:0;"/> <html:hidden property="quadraOrigemID" /></td>
					<td><html:text property="loteOrigem" size="3" maxlength="3"
						readonly="true" style="background-color:#EFEFEF; border:0;"/></td>
					<td><html:text property="subloteOrigem" size="3" maxlength="3"
						readonly="true" style="background-color:#EFEFEF; border:0;"/></td>
				</tr>
				
				<!-- INCRIÇÃO FINAL -->
				<tr>
					<td><strong>Inscri&ccedil;&atilde;o Final:</strong></td>
					<td><html:text property="localidadeDestinoID" size="3"
						maxlength="3" readonly="true" style="background-color:#EFEFEF; border:0;"/></td>
					<td><html:text property="setorComercialDestinoCD" size="3"
						maxlength="3" readonly="true" style="background-color:#EFEFEF; border:0;"/> <html:hidden
						property="setorComercialDestinoID" /></td>
					<td><html:text property="quadraDestinoNM" size="4" maxlength="4"
						readonly="true" style="background-color:#EFEFEF; border:0;"/> <html:hidden property="quadraDestinoID" /></td>
					<td><html:text property="loteDestino" size="3" maxlength="3"
						readonly="true" style="background-color:#EFEFEF; border:0;"/></td>
					<td><html:text property="subloteDestino" size="3" maxlength="3"
						readonly="true" style="background-color:#EFEFEF; border:0;"/></td>
				</tr>
				
				<tr>
					<td colspan="6" height="5"></td>
				</tr>
				
				<!-- ROTA INICIAL -->
				<tr>
					<td>&nbsp;</td>
					<td width="15%"><strong>Código</strong></td>
					<td width="15%"><strong>Sequencial</strong></td>
					<td width="7%">&nbsp;</td>
					<td width="7%">&nbsp;</td>
					<td width="36%">&nbsp;</td>
				</tr>
				<tr>
					<td><strong>Rota Inicial:</strong></td>
					<td><html:text property="cdRotaInicial" size="7"
						maxlength="4" readonly="true" style="background-color:#EFEFEF; border:0;"/></td>
					<td colspan="4"><html:text property="sequencialRotaInicial" size="7"
						maxlength="6" readonly="true" style="background-color:#EFEFEF; border:0;"/></td>
				</tr>
				<!-- FIM ROTA INICIAL -->
				
				<!-- ROTA FINAL -->
				<tr>
					<td><strong>Rota Final:</strong></td>
					<td><html:text property="cdRotaFinal" size="7"
						maxlength="4" readonly="true" style="background-color:#EFEFEF; border:0;"/></td>
					<td colspan="4"><html:text property="sequencialRotaFinal" size="7"
						maxlength="6" readonly="true" style="background-color:#EFEFEF; border:0;"/></td>
				</tr>
				<!-- FIM ROTA FINAL -->
								
				</table>

				</td>
			</tr>
			</table>
			
			</div>
			
			<p>&nbsp;</p>
			
			<table width="100%" border="0">
			<tr>
				<td><strong>Quantidade de im&oacute;veis que ser&atilde;o
				atualizados:</strong></td>
				<td colspan="5" align="right">
				<div align="right"><html:text property="quantidadeImoveisSEMSituacaoEspecialFaturamento"
					size="8" maxlength="8" readonly="true" style="background-color:#EFEFEF; border:0; text-align:right"/></div>
				</td>
			</tr>
			</table>
			
			<p>&nbsp;</p>
			
			<table width="100%" border="0">
			<tr>
				<td width="35%"><strong>Tipo da Sit. Especial de Fat.:<font color="#FF0000">*</font></strong></td>
				<td colspan="3">
				
				<html:select property="idFaturamentoSituacaoTipo"
				onchange="habilitacaoCamposConsumoVolume(this);">
				
				<html:option value="">&nbsp;</html:option>
				
				<logic:present name="collectionFaturamentoSituacaoTipo">
																				    	
					<logic:iterate name="collectionFaturamentoSituacaoTipo" id="tipoSituacao" type="FaturamentoSituacaoTipo">
													      				
						<option value="<%="" + tipoSituacao.getId() %>" 
						id="<%= tipoSituacao.getIndicadorInformarConsumo().toString()
						+ "," + tipoSituacao.getIndicadorInformarVolume().toString()%>">
						<%="" + tipoSituacao.getDescricao() %></option>
						
					</logic:iterate>
											
				</logic:present>
				
				</html:select>
				
				</td>
			</tr>
			
			<tr>
				<td width="35%"><strong>Consumo Não Medido</strong></td>
				<td width="20%"><html:text property="consumoFixoNaoMedido" size="8" maxlength="8" disabled="true"/>m<sup>3</sup></td>
				<td width="30%"><strong>Consumo Medido</strong></td>
				<td><html:text property="consumoFixoMedido" size="8" maxlength="8" disabled="true"/>m<sup>3</sup></td>
			</tr>
			
			<tr>
				<td width="35%"><strong>Volume Não Medido</strong></td>
				<td width="20%"><html:text property="volumeFixoNaoMedido" size="8" maxlength="8" disabled="true"/>m<sup>3</sup></td>
				<td width="30%"><strong>Volume Medido</strong></td>
				<td><html:text property="volumeFixoMedido" size="8" maxlength="8" disabled="true"/>m<sup>3</sup></td>
			</tr>
			<tr>
				<td width="35%"><strong>Motivo da Sit. Especial de Fat.:<font color="#FF0000">*</font></strong></td>
				<td colspan="3"><html:select property="idFaturamentoSituacaoMotivo">
						<html:option value="">&nbsp;</html:option>
						<html:options collection="collectionFaturamentoSituacaoMotivo"
						labelProperty="descricao" property="id" />
					</html:select>
				</td>
			</tr>
			
			
			<tr>
				<td width="35%"><strong>Refer&ecirc;ncia do Fat. Inicial</strong><strong>:<font color="#FF0000">*</font></strong></td>
				<td colspan="3"><html:text property="mesAnoReferenciaFaturamentoInicial"
					size="7" maxlength="7" onkeypress="mascaraAnoMes(this, event);" />mm/aaaa
				</td>
			</tr>
			<tr>
				<td width="35%"><strong>Refer&ecirc;ncia do Fat. Final</strong><strong>:<font color="#FF0000">*</font></strong></td>
				<td colspan="3"><html:text property="mesAnoReferenciaFaturamentoFinal" size="7"
					maxlength="7" onkeypress="mascaraAnoMes(this, event);" />mm/aaaa
				</td>
			</tr>
			<tr>
      			<td width="35%"><strong>Observação:</strong></td>
        		<td colspan="3">
					<html:textarea property="observacaoInforma" cols="40" rows="4" onkeyup="limitTextArea(document.forms[0].observacaoInforma, 100, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
					<strong><span id="utilizado">0</span>/<span id="limite">100</span></strong>
				</td>
      		</tr>
			
			<tr>
				<td width="35%">&nbsp;</td>
				<td colspan="3"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</td>
			</tr>
			</table>
			
			<table width="100%" border="0">
			<tr>
				<td align="left">
					<input type="button" class="bottonRightCol" value="Voltar"
					onclick="javascript:history.back();" />
				</td>
							
				<td align="right">
					<input type="button" class="bottonRightCol"
					value="Concluir" onclick="validarForm(form);" /></td>
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
