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
	if (validateSituacaoEspecialFaturamentoActionForm(form)){
	    
	    if(confirm("Confirma a alteração da situação especial de faturamento para " + form.quantidadeImoveisAtualizados.value + " imóvel(is) ?")){
	    	submeterFormPadrao(form);
		}	
	}
}

//-->
</SCRIPT>
<script>
<!-- Begin 
    function validateSituacaoEspecialFaturamentoActionForm(form) {
       return validateRequired(form) && validateCaracterEspecial(form) && validateMesAno(form) && validateLong(form) && validateMesAnoReferenciaFinalMaiorInicial(form);
   } 

	function MesAnoValidations() {
     	this.aa = new Array("mesAnoReferenciaFaturamentoInicial", "Mês e Ano de Referência do Faturamento Inicial inválido.", new Function ("varName", " return this[varName];"));
    	this.ab = new Array("mesAnoReferenciaFaturamentoFinal", "Mês e Ano de Referência do Faturamento Final inválido.", new Function ("varName", " return this[varName];"));
    }
               
	function validateMesAnoReferenciaFinalMaiorInicial(form) {
		var mesAnoInicial = form.mesAnoReferenciaFaturamentoInicial.value; 
		var mesAnoFinal = form.mesAnoReferenciaFaturamentoFinal.value;

		if(mesAnoFinal == ""){
			return false;
		}

		var mesInicial = mesAnoInicial.split("/")[0];
		var anoInicial = mesAnoInicial.split("/").length > 1 ? mesAnoInicial.split("/")[1] : 0;
		var mesFinal = mesAnoFinal.split("/")[0];
		var anoFinal = mesAnoFinal.split("/").length > 1 ? mesAnoFinal.split("/")[1] : 0;

		if(anoFinal < anoInicial){
			alert("Mês e Ano de Referência do Faturamento Final é menor que o Inicial.");
			return false;
		}
		if((mesFinal < mesInicial) && (anoFinal == anoInicial) ){
			alert("Mês e Ano de Referência do Faturamento Final é menor que o Inicial.");
			return false;
		}
	
		return true;
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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AtualizarSituacaoEspecialFaturamentoActionForm"
	dynamicJavascript="false" />

</head>

<body leftmargin="5" topmargin="5" onload="limitTextArea(document.forms[0].observacaoInforma, 100, document.getElementById('utilizado'), document.getElementById('limite'));">


<html:form
	action="/atualizarSituacaoEspecialFaturamentoAction"
	type="gcom.gui.faturamento.AtualizarSituacaoEspecialFaturamentoActionForm"
	name="AtualizarSituacaoEspecialFaturamentoActionForm" method="post">

	<html:hidden property="idFaturamentoSituacaoComando" />

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
					<td class="parabg">Manter Situação Especial de Faturamento</td>
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
									
			<table width="100%" align="center" bgcolor="#99CCFF" border="0">
			<tr>
				<td align="center">
					<strong>Par&acirc;metros Informados</strong>
				</td>
			</tr>
			<tr bgcolor="#cbe5fe">
				<td width="100%" align="center">

				<table width="100%" border="0">
				<tr>
					<td width="30%"><strong>Matr&iacute;cula:</strong></td>
					<td colspan="5"><html:text property="idImovel" size="8"
						maxlength="8" readonly="true" style="background-color:#EFEFEF; border:0;"/></td>
				</tr>
				<tr>
					<td colspan="6" height="5"></td>
				</tr>

				<tr>
					<td width="30%">&nbsp;</td>
					<td width="7%" align="left"><strong>Loc.</strong></td>
					<td width="7%" align="left"><strong>Setor</strong></td>
					<td width="7%" align="left"><strong>Quadra</strong></td>
					<td width="7%" align="left"><strong>Lote</strong></td>
					<td width="42%" align="left"><strong>Sublote</strong></td>
				</tr>
				
				<!-- INCRIÇÃO INICIAL -->
				<tr>
					<td><strong>Inscri&ccedil;&atilde;o Inicial:</strong></td>
					<td width="7%" align="left">
						<html:text property="localidadeOrigemID" size="3" maxlength="3"
							readonly="true" style="background-color:#EFEFEF; border:0;"/>
					</td>
					<td width="7%" align="left">
						<html:text property="setorComercialOrigemCD" size="3"
							maxlength="3" readonly="true" style="background-color:#EFEFEF; border:0;"/> 
						<html:hidden property="setorComercialOrigemID" />
					</td>
					<td width="7%" align="left">
						<html:text property="quadraOrigemNM" size="4" maxlength="4"
							readonly="true" style="background-color:#EFEFEF; border:0;"/> 
						<html:hidden property="quadraOrigemID" />
					</td>
					<td width="7%" align="left">
						<html:text property="loteOrigem" size="3" maxlength="3"
							readonly="true" style="background-color:#EFEFEF; border:0;"/>
					</td>
					<td width="7%" align="left">
						<html:text property="subloteOrigem" size="3" maxlength="3"
							readonly="true" style="background-color:#EFEFEF; border:0;"/>
					</td>
				</tr>
				
				<!-- INCRIÇÃO FINAL -->
				<tr>
					<td><strong>Inscri&ccedil;&atilde;o Final:</strong></td>
					<td>
						<html:text property="localidadeDestinoID" size="3"
							maxlength="3" readonly="true" style="background-color:#EFEFEF; border:0;"/>
					</td>
					<td>
						<html:text property="setorComercialDestinoCD" size="3"
							maxlength="3" readonly="true" style="background-color:#EFEFEF; border:0;"/> <html:hidden
							property="setorComercialDestinoID" />
					</td>
					<td>
						<html:text property="quadraDestinoNM" size="4" maxlength="4"
							readonly="true" style="background-color:#EFEFEF; border:0;"/> 
							<html:hidden property="quadraDestinoID" /></td>
					<td>
						<html:text property="loteDestino" size="3" maxlength="3"
							readonly="true" style="background-color:#EFEFEF; border:0;"/>
					</td>
					<td>
						<html:text property="subloteDestino" size="3" maxlength="3"
							readonly="true" style="background-color:#EFEFEF; border:0;"/>
					</td>
				</tr>
				</table>

				<table width="100%" border="0">				
				<!-- ROTA INICIAL -->
				<tr>
					<td width="30%"></td>
					<td width="8%"><strong>Código</strong></td>
					<td width="62%"><strong>Seq. Rota</strong></td>
				</tr>
				<tr>
					<td><strong>Rota Inicial:</strong></td>
					<td><html:text property="cdRotaInicial" size="4"
						maxlength="3" readonly="true" style="background-color:#EFEFEF; border:0;"/>
					</td>
					<td ><html:text property="sequencialRotaInicial" size="7"
						maxlength="6" readonly="true" style="background-color:#EFEFEF; border:0;"/>
					</td>
				</tr>
				<!-- FIM ROTA INICIAL -->
				
				<!-- ROTA FINAL -->
				<tr>
					<td><strong>Rota Final:</strong></td>
					<td><html:text property="cdRotaFinal" size="4"
						maxlength="3" readonly="true" style="background-color:#EFEFEF; border:0;"/></td>
					<td ><html:text property="sequencialRotaFinal" size="7"
						maxlength="6" readonly="true" style="background-color:#EFEFEF; border:0;"/></td>
				</tr>
				<!-- FIM ROTA FINAL -->
				</table>

				<table width="100%" border="0">
				<!-- CATEGORIA -->
				<tr>
					<td width="30%"><strong>Categoria:</strong></td>
					<td width="7%" align="left">
						<html:text property="categoria1ID" size="3"
								maxlength="3" readonly="true" style="background-color:#EFEFEF; border:0;"/>
					</td>
					<td width="7%" align="left">
						<html:text property="categoria2ID" size="3"
								maxlength="3" readonly="true" style="background-color:#EFEFEF; border:0;"/>
					</td>
					<td width="7%" align="left">
						<html:text property="categoria3ID" size="3"
								maxlength="3" readonly="true" style="background-color:#EFEFEF; border:0;"/>
					</td>
					<td width="59%" align="left">
						<html:text property="categoria4ID" size="3"
								maxlength="3" readonly="true" style="background-color:#EFEFEF; border:0;"/>
					</td>
				</tr>
				<!-- CATEGORIA -->
				</table>

				<table width="100%" border="0">
				<!-- CONSUMO -->
				<tr>
					<td width="30%" align="left"><strong>Consumo do Imóvel:</strong></td>					
					<td width="15%" align="left">
						<html:radio property="indicadorConsumoImovel"  value="1" disabled="true"/><strong>Medido</strong>
					</td>
					<td width="20%" align="left">
						<html:radio	property="indicadorConsumoImovel"  value="2" disabled="true"/><strong>Não-Medido</strong>
					</td>
					<td width="35%" align="left" >
						<html:radio	property="indicadorConsumoImovel"  value="3" disabled="true"/><strong>Todos</strong>
					</td>
				</tr>
				<!-- CONSUMO -->
				</table>

				<table width="100%" border="0">
				<!-- QTD IMOVEIS -->
				<tr>
					<td align="left"><strong>Quantidade de im&oacute;veis que ser&atilde;o atualizados:</strong></td>
					<td>
						<html:text property="quantidadeImoveisAtualizados"
							size="8" maxlength="8" readonly="true" style="background-color:#EFEFEF; border:0;"/>
					</td>
				</tr>
				<!-- QTD IMOVEIS -->
				<tr>
					<td colspan="6" height="5"></td>
				</tr>
				<!-- TIPO  -->

				<tr>
					<td width="30%" align="left"><strong>Tipo da Situa&ccedil;&auml;o Especial de Faturamento:</strong></td>
					<td>
						<html:text property="tipoSituacaoEspecialFaturamento"
							size="50" maxlength="300" readonly="true" style="background-color:#EFEFEF; border:0;"/>
					</td>
				</tr>
				<!-- TIPO  -->
								
				</table>

				</td>
			</tr>
			</table>
			
			<p>&nbsp;</p>
			
			<table width="100%" border="0">
				<tr>
					<td width="30%"><strong>Motivo da Situa&ccedil;&auml;o Especial de Faturamento:</strong></td>
						<td>
							<html:text property="motivoSituacaoEspecialFaturamento"
								size="50" maxlength="300" readonly="true" style="background-color:#EFEFEF; border:0;"/>
						</td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td width="30%"><strong>Consumo:</strong></td>
					<td width="13%" align="right"><strong>N&atilde;o Medido:</strong></td>
					<td width="20%" align="left"><html:text property="consumoFixoNaoMedido" size="8" maxlength="8" style="background-color:#EFEFEF; border:0;" readonly="true"/>m<sup>3</sup></td>
					<td width="7%" align="right"><strong>Medido:</strong></td>
					<td width="30%" align="left"><html:text property="consumoFixoMedido" size="8" maxlength="8" style="background-color:#EFEFEF; border:0;" readonly="true"/>m<sup>3</sup></td>
				</tr>
				
				<tr>
					<td width="30%"><strong>Volume:</strong></td>
					<td width="13%" align="right"><strong>N&atilde;o Medido:</strong></td>
					<td width="20%" align="left"><html:text property="volumeFixoNaoMedido" size="8" maxlength="8" style="background-color:#EFEFEF; border:0;" readonly="true"/>m<sup>3</sup></td>
					<td width="7%" align="right"><strong>Medido:</strong></td>
					<td width="30%" align="left"><html:text property="volumeFixoMedido" size="8" maxlength="8" style="background-color:#EFEFEF; border:0;" readonly="true"/>m<sup>3</sup></td>
				</tr>
			</table>
			
			<table width="100%" border="0">
			<tr>
				<td width="30%"><strong>M&ecirc;s e Ano de Refer&ecirc;ia do Faturamento Inicial</strong><strong>:</strong></td>
				<td><html:text property="mesAnoReferenciaFaturamentoInicial"
					size="7" maxlength="7" readonly="true" style="background-color:#EFEFEF; border:0;" onkeypress="mascaraAnoMes(this, event);" />mm/aaaa
				</td>
			</tr>
			<tr>
				<td width="30%"><strong>M&ecirc;s e Ano de Refer&ecirc;ncia do Faturamento Final</strong><strong>:<font color="#FF0000">*</font></strong></td>
				<td align="left"><html:text property="mesAnoReferenciaFaturamentoFinal" size="7"
					maxlength="7" onkeypress="mascaraAnoMes(this, event);" />mm/aaaa
				</td>
			</tr>
			<tr>
      			<td width="30%"><strong>Observação:</strong></td>
        		<td >
					<html:textarea property="observacao" cols="40" rows="4" onkeyup="limitTextArea(document.forms[0].observacao, 100, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
					<strong><span id="utilizado">0</span>/<span id="limite">100</span></strong>
				</td>
      		</tr>
			
			<tr>
				<td width="30%">&nbsp;</td>
				<td ><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios
				</td>
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
