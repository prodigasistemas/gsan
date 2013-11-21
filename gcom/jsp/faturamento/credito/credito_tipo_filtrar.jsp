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
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="FiltrarTipoCreditoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">


function validaIndicadores(){
	
			var form = document.FiltrarTipoCreditoActionForm;
    			
    			if(form.indicadorGeracaoCreditoAutomatica[0].checked == false &&	form.indicadorGeracaoCreditoAutomatica[1].checked == false){
    			alert('Indicador de Geração do Débito Automática');
    			}
    			if(form.indicadorGeracaoCreditoConta[0].checked == false &&	form.indicadorGeracaoCreditoConta[1].checked == false){
    			alert('Indicador de Geração do Débito em Conta');
    			}
	}
	


function replicarLimiteCreditoInicial(){
	var formulario = document.forms[0]; 
	formulario.valorLimiteCreditoFinal.value = formulario.valorLimiteCreditoInicial.value ;
	formulario.indicadorGeracaoCreditoAutomatica.focus;


}	

function replicarLimiteCreditoFinal(){
	var formulario = document.forms[0]; 
	formulario.valorLimiteCreditoInicial.value = formulario.valorLimiteCreditoFinal.value ;
	formulario.indicadorGeracaoCreditoAutomatica.focus;


}	

	
/*function validaIndicadorGeracaoCreditoAutomatica(){
	
			var form = document.InserirTipoCreditoActionForm;
    			if(form.indicadorGeracaoCreditoConta[0].checked == false &&	form.indicadorGeracaoCreditoConta[1].checked == false){
    			alert('Indicador de Geração do Débito em Conta');
    			}
	}
*/
	


function validarForm(form){

	/*if(//testarCampoValorZero(document.InserirTipoCreditoActionForm.descricao, 'Descrição do Tipo de Débito') && 
	//testarCampoValorZero(document.InserirTipoCreditoActionForm.descricaoAbreviada, 'Descrição do Tipo de Débito Abreviada') && 
	//testarCampoValorZero(document.InserirTipoCreditoActionForm.lancamentoItemContabil, 'Tipo do Lançamento do Item Contábil') && 
	//testarCampoValorZero(document.InserirTipoCreditoActionForm.financiamentoTipo, 'Tipo de Financiamento') && 
	//testarCampoValorZero(document.InserirTipoCreditoActionForm.indicadorGeracaoCreditoAutomatica, 'Indicador de Geração do Débito Automática') && 
	//testarCampoValorZero(document.InserirTipoCreditoActionForm.indicadorGeracaoCreditoConta, 'Indicador de Geração do Débito em Conta') && 
	testarCampoValorZero(document.InserirTipoCreditoActionForm.valorLimiteCredito, 'Valor Limite do Débito')) {*/

		if(validateFiltrarTipoCreditoActionForm(form)){
			/*if(form.indicadorGeracaoCreditoAutomatica[0].checked == false &&	form.indicadorGeracaoCreditoAutomatica[1].checked == false){
    			alert('Indicador de Geração do Débito Automática');
    			}else
    			if(form.indicadorGeracaoCreditoConta[0].checked == false &&	form.indicadorGeracaoCreditoConta[1].checked == false){
    			alert('Indicador de Geração do Débito em Conta');
    			}else{*/
			//form.descricao.value = form.descricao.value.toUpperCase();
			//form.descricaoAbreviada.value = form.descricaoAbreviada.value.toUpperCase();
			//form.caminhoMenu.value = form.caminhoMenu.value.toUpperCase();
    		form.submit();
    		//}
		}
	//}
}



</script>


</head>

<body leftmargin="5" topmargin="5"
	onload="verificarChecado('${sessionScope.indicadorAtualizar}'); setarFoco('${requestScope.nomeCampo}');">
<html:form action="/filtrarTipoCreditoAction.do" method="post"
	name="FiltrarTipoCreditoActionForm"
	type="gcom.gui.faturamento.credito.FiltrarTipoCreditoActionForm"
	onsubmit="return validateFiltrarTipoCreditoActionForm(this);">
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
					<td class="parabg">Filtrar Tipo de Crédito</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td width="60%">Para filtrar o tipo de crédito, informe os dados abaixo:</td>
					<td width="40%" align="right"><html:checkbox property="atualizar" value="1" /><strong>Atualizar</strong></td>
				</tr>
				<tr>
					<td><strong>Descrição do Tipo de Crédito:</strong></td>
					<td><strong> <html:text property="descricao" size="20"
						maxlength="20"/> </strong></td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td><html:radio property="tipoPesquisa"	tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
						Iniciando pelo texto
						<html:radio	property="tipoPesquisa" tabindex="6"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
						Contendo o texto
					</td>
				</tr>				
				
				<tr>
					<td width="162"><strong>Descrição do Tipo de Crédito Abreviada:</strong></td>
					<td><strong> <html:text property="abreviatura" size="18"
						maxlength="18"/> </strong></td>
				</tr>

				<tr>
					<td><strong>Valor Limite do Crédito:</strong></td>
					<td><strong> <html:text property="valorLimiteCreditoInicial"
						size="15" maxlength="15"
						onkeyup="javascript:formataValorMonetario(this,13);"
						onblur="javascript:replicarLimiteCreditoInicial();"/>a
					<html:text property="valorLimiteCreditoFinal" size="15"
						maxlength="15"
						onkeyup="javascript:formataValorMonetario(this,13);" />
					</strong></td>
				</tr>
				<tr>
					<td><strong>Indicador de Geração do Crédito Automática:</strong></td>
					<td><strong><html:radio property="indicadorgeracaoAutomaica"
						value="1" />Sim<html:radio
						property="indicadorgeracaoAutomaica" value="2" />
					N&atilde;o<html:radio property="indicadorgeracaoAutomaica"
						value="3" />Todas</strong></td>
				</tr>

				<tr>
					<td><strong>Tipo do Lançamento do Item Contábil:</strong></td>
					<td><html:select property="tipoLancamentoContabil">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoTipoLancamentoContabil"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>

				<tr>
					<td><strong>Indicador de Uso:</strong></td>
					<td><strong> <html:radio
						property="indicativoUso" value="1" />Ativo <html:radio
						property="indicativoUso" value="2" /> Inativo<html:radio
						property="indicativoUso" value="3" /> Todas</strong></td>
				</tr>
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirFiltrarTipoCreditoAction.do?menu=sim"/>'">
					<!--  <input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="window.location.href='/gsan/telaPrincipal.do'">--></td>
					<td align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Filtrar" align="right"
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

