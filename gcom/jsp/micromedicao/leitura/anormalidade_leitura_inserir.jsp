<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

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
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="InserirAnormalidadeLeituraActionForm" />

<script language="JavaScript">
  
	function validaForm() {
	  	var form = document.forms[0];
	  	form.action = "/gsan/inserirAnormalidadeLeituraAction.do";
		if(validateInserirAnormalidadeLeituraActionForm(form)) {
	     	if(form.numeroFatorSemLeitura.value>=10 || form.numeroFatorComLeitura.value>=10){
				alert("Valor do Fator dever ser menor 10(dez).")
			}else{  		
				submeterFormPadrao(form);   		  
			}
   	    }
	}
	 
 
	function limparForm() {
		var form = document.InserirAnormalidadeLeituraActionForm;
		form.descricao.value = "";
		form.abreviatura.value = "";
	    form.indicadorRelativoHidrometro.value = "";
	    form.indicadorImovelSemHidrometro.value = "";
		form.usoRestritoSistema.value = "";
	    form.perdaTarifaSocial.value = "";
	    form.osAutomatico.value = "";
		form.tipoServico.value = "";
	    form.consumoLeituraNaoInformado.value = "";
	    form.consumoLeituraInformado.value = "";
		form.leituraLeituraNaoturaInformado.value = "";
	    form.leituraLeituraInformado.value = "";
			
	}
	
	function desabiltaCombo(){
			var form = document.forms[0];
			
			if(form.osAutomatico[1].checked){
			form.tipoServico.value="-1";
			form.tipoServico.disabled = true;
			}else{ if (form.osAutomatico[0].checked){
			form.tipoServico.value="-1";
			form.tipoServico.disabled = false;
			}
	}
	
	}  
	
	function reload() {
		var form = document.InserirAnormalidadeLeituraActionForm;
		form.action = "/gsan/exibirInserirAnormalidadeLeituraAction.do";
		form.submit();
	}  
	

</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:desabiltaCombo();">

<html:form action="/inserirAnormalidadeLeituraAction"
	name="InserirAnormalidadeLeituraActionForm"
	type="gcom.gui.micromedicao.leitura.InserirAnormalidadeLeituraActionForm"
	method="post"
	onsubmit="return validateInserirAnormalidadeLeituraActionForm(this);">

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

			<!-- centercoltext -->

			<td width="600" valign="top" class="centercoltext">

			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Inserir Anormalidade de Leitura</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para adicionar a anormalidade de
					leitura, informe os dados abaixo:</td>
				</tr>

				<!-- Descricao -->
				<tr>
					<td height="15"><strong>Descri&ccedil;&atilde;o:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricao" size="25" maxlength="25" /> </span></td>
				</tr>

				<!-- Abreviatura -->
				<tr>
					<td><strong>Abreviatura:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="abreviatura" size="5" maxlength="5" /> </span></td>
				</tr>
			</table>
			<table>
				<tr>
					<td><strong>Anormalidade Relativa a Hidrômetro:<font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorRelativoHidrometro"
						value="1" /> <strong>Sim <html:radio
						property="indicadorRelativoHidrometro" value="2" /> N&atilde;o</strong>
					</strong></td>

				</tr>
				<tr>
					<td><strong>Anormalidade Aceita para Ligação sem Hidrômetro:<font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorImovelSemHidrometro"
						value="1" /> <strong>Sim <html:radio
						property="indicadorImovelSemHidrometro" value="2" /> N&atilde;o</strong>
					</strong></td>

				</tr>
				<tr>
					<td><strong>Anormalidade de Uso Restrito do Sistema:<font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="usoRestritoSistema" value="1" />
					<strong>Sim <html:radio property="usoRestritoSistema" value="2" />
					N&atilde;o</strong> </strong></td>

				</tr>
				<tr>
					<td><strong>Anormalidade Acarreta Perda Tarifa Social:<font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="perdaTarifaSocial" value="1" />
					<strong>Sim <html:radio property="perdaTarifaSocial" value="2" />
					N&atilde;o</strong> </strong></td>

				</tr>

				<tr>
					<td><strong>Anormalidade Emite OS Automática:<font color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="osAutomatico" value="1"
						onchange="javascript:desabiltaCombo();" /> <strong>Sim <html:radio
						property="osAutomatico" value="2"
						onchange="javascript:desabiltaCombo();" /> N&atilde;o</strong> </strong></td>

				</tr>
			</table>
			<table>	
				<!-- Tipo de Serviço -->
				<tr>
					<td><strong>Tipo de Serviço:<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><html:select property="tipoServico">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoTipoServico"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Anormalidade referente ao IS:<font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorImpressaoSimultanea" value="1" />
					<strong>Sim <html:radio property="indicadorImpressaoSimultanea" value="2" />
					N&atilde;o</strong> </strong></td>

				</tr>
			</table>		
			<table>
				<!--Consumo a Ser Cobrado (anormalidade informada e leitura não informada)-->

				<tr>
					<td width="50%"><strong>Consumo a Ser Cobrado (anormalidade informada e leitura
					não informada):<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><html:select
						property="consumoLeituraNaoInformado">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoLeituraAnormalidadeConsumo"
							labelProperty="descricaoConsumoACobrar" property="id" />
					</html:select></td>
				</tr>

				<tr>
					<td><strong>Consumo a Ser Cobrado (anormalidade informada e leitura
					informada):<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><html:select
						property="consumoLeituraInformado">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoLeituraAnormalidadeConsumo"
							labelProperty="descricaoConsumoACobrar" property="id" />
					</html:select></td>
				</tr>
	
				<tr>
					<td><strong>Leitura para faturamento (anormalidade informada e
					leitura não informada):<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><html:select
						property="leituraLeituraNaoturaInformado">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoLeituraAnormalidadeLeitura"
							labelProperty="descricaoFaturamento" property="id" />
					</html:select></td>
				</tr>
							
				<tr>
					<td><strong>Leitura para faturamento (anormalidade informada e
					leitura informada):<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><html:select
						property="leituraLeituraInformado">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoLeituraAnormalidadeLeitura"
							labelProperty="descricaoFaturamento" property="id" />
					</html:select></td>
				</tr>			
					
				<tr>
					<td><strong>Fator que deverá atualizar o consumo de imóveis com anormalidade (Sem leitura):<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="numeroFatorSemLeitura" size="5" maxlength="4" onkeyup="javascript:formataValorMonetario(this,3);"/> </span></td>
				</tr>
				<tr>
					<td><strong>Fator que deverá atualizar o consumo de imóveis com anormalidade (Com leitura):<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="numeroFatorComLeitura" size="5" maxlength="4" onkeyup="javascript:formataValorMonetario(this,3);"/> </span></td>
				</tr>
				<tr>
					<td><strong>Indicador de obrigatoriedade da anormalidade de leitura:
						<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><html:select
						property="indicadorLeitura">
						<html:option value="0">Leitura Opcional</html:option>
						<html:option value="1">Leitura Obrigatória</html:option>
						<html:option value="2">Não aceita Leitura</html:option>
					</html:select></td>
				</tr>
				<tr>
					<td>
						<strong>Número de vezes para suspender leitura:</strong>
					</td>
					<td colspan="2" align="left">
						<html:text property="numeroVezesSuspendeLeitura" size="5" maxlength="2" onkeyup="javascript: return isCampoNumerico(event);" />
					</td>
				</tr>
				<tr>
					<td>
						<strong>Número de meses para manter leitura suspensa:</strong>
					</td>
					<td colspan="2" align="left">
						<html:text property="numeroMesesLeituraSuspensa" size="5" maxlength="2" onkeyup="javascript: return isCampoNumerico(event);" />
					</td>
				</tr>

				<!-- Botões -->

				<tr>
					<td align="left"><input name="Button" type="button"
						class="bottonRightCol" value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirInserirAnormalidadeLeituraAction.do?desfazer=S"/>'">
					<input type="button" name="ButtonCancelar" class="bottonRightCol"
						value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td colspan="2" align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Inserir" onclick="validaForm();"></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</html:html>
