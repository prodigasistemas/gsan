<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirTipoDebitoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">


function validaIndicadores(){
	
			var form = document.InserirTipoDebitoActionForm;
    			
    			if(form.indicadorGeracaoDebitoAutomatica[0].checked == false &&	form.indicadorGeracaoDebitoAutomatica[1].checked == false){
    			alert('Indicador de Geração do Débito Automática');
    			}
    			if(form.indicadorGeracaoDebitoConta[0].checked == false &&	form.indicadorGeracaoDebitoConta[1].checked == false){
    			alert('Indicador de Geração do Débito em Conta');
    			}
    			
    			if(form.indicadorDebitoCartaoCredito[0].checked == false &&	form.indicadorDebitoCartaoCredito[1].checked == false){

    			alert('Indicador de Cartão de Crédito');

    			}
    			
    			if(form.indicadorJurosParCliente[0].checked == false &&	form.indicadorJurosParCliente[1].checked == false){

    			alert('Juros de Contrato de Parcelamento por Cliente');

    			}
    			
	}
	
/*function validaIndicadorGeracaoDebitoAutomatica(){
	
			var form = document.InserirTipoDebitoActionForm;
    			if(form.indicadorGeracaoDebitoConta[0].checked == false &&	form.indicadorGeracaoDebitoConta[1].checked == false){
    			alert('Indicador de Geração do Débito em Conta');
    			}
	}
*/
	


function validarForm(form){

	/*if(//testarCampoValorZero(document.InserirTipoDebitoActionForm.descricao, 'Descrição do Tipo de Débito') && 
	//testarCampoValorZero(document.InserirTipoDebitoActionForm.descricaoAbreviada, 'Descrição do Tipo de Débito Abreviada') && 
	//testarCampoValorZero(document.InserirTipoDebitoActionForm.lancamentoItemContabil, 'Tipo do Lançamento do Item Contábil') && 
	//testarCampoValorZero(document.InserirTipoDebitoActionForm.financiamentoTipo, 'Tipo de Financiamento') && 
	//testarCampoValorZero(document.InserirTipoDebitoActionForm.indicadorGeracaoDebitoAutomatica, 'Indicador de Geração do Débito Automática') && 
	//testarCampoValorZero(document.InserirTipoDebitoActionForm.indicadorGeracaoDebitoConta, 'Indicador de Geração do Débito em Conta') && 
	testarCampoValorZero(document.InserirTipoDebitoActionForm.valorLimiteDebito, 'Valor Limite do Débito')) {*/

		if(validateInserirTipoDebitoActionForm(form)){
			if(form.indicadorGeracaoDebitoAutomatica[0].checked == false &&	form.indicadorGeracaoDebitoAutomatica[1].checked == false){
    			alert('Indicador de Geração do Débito Automática');
    			}else
    			if(form.indicadorGeracaoDebitoConta[0].checked == false &&	form.indicadorGeracaoDebitoConta[1].checked == false){
    			alert('Indicador de Geração do Débito em Conta');
    			}else

    			if(form.indicadorDebitoCartaoCredito[0].checked == false &&	form.indicadorDebitoCartaoCredito[1].checked == false){

    			alert('Indicador Cartão de Crédito');

    			}else{
			//form.descricao.value = form.descricao.value.toUpperCase();
			//form.descricaoAbreviada.value = form.descricaoAbreviada.value.toUpperCase();
			//form.caminhoMenu.value = form.caminhoMenu.value.toUpperCase();
    		submeterFormPadrao(form);
    		}
		}
	//}
}
</script>
</head>
<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/inserirTipoDebitoAction.do" method="post"
	name="InserirTipoDebitoActionForm"
	type="gcom.gui.faturamento.debito.InserirTipoDebitoActionForm"
	onsubmit="return validateInserirTipoDebitoActionForm(this);">
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

			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Inserir Tipo de Débito</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para adicionar um tipo de débito, informe os dados abaixo:</td>
				</tr>
				<tr>
				  <td><strong>Descrição do Tipo de Débito:<font color="#FF0000">*</font></strong></td>
				  <td>
					<strong> 
					  <html:text property="descricao" size="50" maxlength="30" /> 
					</strong>
				  </td>
				</tr>
				<tr>
					<td width="162"><strong>Descrição do Tipo de Débito Abreviada:</strong></td>
					<td>
					<strong> 
					<html:text property="descricaoAbreviada" size="28" maxlength="18" /> 
					</strong>
					</td>
				</tr>
				
				<tr>
					<td><strong>Tipo do Lançamento do Item Contábil:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="lancamentoItemContabil">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoLancamentoItemContabil"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				
				<tr>
					<td><strong>Tipo de Financiamento:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="financiamentoTipo">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoFinanciamentoTipo"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				<tr>
					<td><strong>Indicador de Geração do Débito Automática:<font color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorGeracaoDebitoAutomatica" value="1" />
					<strong>Sim <html:radio property="indicadorGeracaoDebitoAutomatica" value="2" />
					N&atilde;o</strong> </strong></td>
				</tr>
				<tr>
					<td><strong>Indicador de Geração do Débito em Conta:<font color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorGeracaoDebitoConta" value="1" />
					<strong>Sim <html:radio property="indicadorGeracaoDebitoConta" value="2" />
					N&atilde;o</strong> </strong></td>
				</tr>
				<tr>
					<td><strong>Valor Limite do Débito:<font color="#FF0000">*</font></strong></td>
					<td>
					<strong> 
					<html:text property="valorLimiteDebito" size="17" maxlength="17" onkeyup="javascript:formataValorMonetario(this,15);" style="text-transform: none;"/> 
					</strong>
					</td>
				</tr>
				
				<tr>
					<td><strong>Valor Sugerido:</strong></td>
					<td><strong> <html:text property="valorSugerido" size="17"
						maxlength="17"
						onkeyup="javascript:formataValorMonetario(this,15);"
						style="text-transform: none;" /> </strong></td>
				</tr>
				<tr>

					<td><strong>Indicador de Cartão de Crédito:<font color="#FF0000">*</font></strong></td>

					<td><strong> <html:radio property="indicadorDebitoCartaoCredito" value="1" />

					<strong>Sim <html:radio property="indicadorDebitoCartaoCredito" value="2" />

					N&atilde;o</strong> </strong></td>

				</tr>
				
				<tr>

					<td><strong>Juros de Contrato de Parcelamento por Cliente:<font color="#FF0000">*</font></strong></td>

					<td><strong> <html:radio property="indicadorJurosParCliente" value="1" />

					<strong>Sim <html:radio property="indicadorJurosParCliente" value="2"  />

					N&atilde;o</strong> </strong></td>

				</tr>
				
				<tr>
					<td height="19"><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left" onclick="window.location.href='<html:rewrite page="/exibirInserirTipoDebitoAction.do?menu=sim"/>'" > <input name="Button" type="button"
						class="bottonRightCol" value="Cancelar" align="left"
						onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
					<td align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Inserir" align="right"
						onClick="javascript:validarForm(document.forms[0]);"></td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>
</html:form>
</body>
</html:html>

