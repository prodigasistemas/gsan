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
	formName="FiltrarTipoDebitoActionForm" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">


function validaIndicadores(){
	
			var form = document.FiltrarTipoDebitoActionForm;
    			
    			if(form.indicadorGeracaoDebitoAutomatica[0].checked == false &&	form.indicadorGeracaoDebitoAutomatica[1].checked == false){
    			alert('Indicador de Geração do Débito Automática');
    			}
    			if(form.indicadorGeracaoDebitoConta[0].checked == false &&	form.indicadorGeracaoDebitoConta[1].checked == false){
    			alert('Indicador de Geração do Débito em Conta');
    			}
	}
	


function replicarLimiteDebitoInicial(){
	var formulario = document.forms[0]; 
	formulario.valorLimiteDebitoFinal.value = formulario.valorLimiteDebitoInicial.value ;
	formulario.indicadorGeracaoDebitoAutomatica.focus;


}	

function replicarLimiteDebitoFinal(){
	var formulario = document.forms[0]; 
	formulario.valorLimiteDebitoInicial.value = formulario.valorLimiteDebitoFinal.value ;
	formulario.indicadorGeracaoDebitoAutomatica.focus;


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

		if(validateFiltrarTipoDebitoActionForm(form)){
			/*if(form.indicadorGeracaoDebitoAutomatica[0].checked == false &&	form.indicadorGeracaoDebitoAutomatica[1].checked == false){
    			alert('Indicador de Geração do Débito Automática');
    			}else
    			if(form.indicadorGeracaoDebitoConta[0].checked == false &&	form.indicadorGeracaoDebitoConta[1].checked == false){
    			alert('Indicador de Geração do Débito em Conta');
    			}else{*/
			//form.descricao.value = form.descricao.value.toUpperCase();
			//form.descricaoAbreviada.value = form.descricaoAbreviada.value.toUpperCase();
			//form.caminhoMenu.value = form.caminhoMenu.value.toUpperCase();
    		submeterFormPadrao(form);
    		//}
		}
	//}
}


</script>





</head>



<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/filtrarTipoDebitoAction.do" method="post"
	name="FiltrarTipoDebitoActionForm"
	type="gcom.gui.faturamento.debito.FiltrarTipoDebitoActionForm"
	onsubmit="return validateFiltrarTipoDebitoActionForm(this);">

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

					<td class="parabg">Filtrar Tipo de Débito</td>

					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>

				</tr>



			</table>

			<!--Fim Tabela Reference a Páginação da Tela de Processo-->

			<p>&nbsp;</p>

			<table width="100%" border="0">

				<tr>

					<td>Para filtrar o tipo de débito, informe os dados abaixo:</td>

					<td width="85" align="right"><html:checkbox property="atualizar"
						value="1" /><strong>Atualizar</strong></td>

				</tr>
			</table>
			<table width="100%" border="0">

				<tr>

					<td><strong>Descrição do Tipo de Débito:</strong></td>

					<td><strong> <html:text property="descricao" size="30"
						maxlength="30" /> </strong></td>

				</tr>

				<tr>
					<td>&nbsp;</td>
					<td><html:radio property="tipoPesquisa" tabindex="3"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto <html:radio property="tipoPesquisa"
						tabindex="4"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
				</tr>



				<tr>

					<td width="162"><strong>Descrição do Tipo de Débito Abreviada:</strong></td>

					<td><strong> <html:text property="descricaoAbreviada" size="18"
						maxlength="18" /> </strong></td>

				</tr>



				<tr>

					<td><strong>Valor Limite do Débito:</strong></td>

					<td><strong> <html:text property="valorLimiteDebitoInicial"
						size="17" maxlength="17"
						onblur="javascript:replicarLimiteDebitoInicial();" />a <html:text
						property="valorLimiteDebitoFinal" size="17" maxlength="17" />

					</strong></td>

				</tr>

				<tr>

					<td><strong>Indicador de Geração do Débito Automática:</strong></td>

					<td><strong><html:radio property="indicadorGeracaoDebitoAutomatica"
						value="1" />Sim<html:radio
						property="indicadorGeracaoDebitoAutomatica" value="2" />

					N&atilde;o<html:radio property="indicadorGeracaoDebitoAutomatica"
						value="3" />Todas</strong></td>

				</tr>

				<tr>

					<td><strong>Indicador de Geração do Débito em Conta:</strong></td>

					<td><strong><html:radio property="indicadorGeracaoDebitoConta"
						value="1" />Sim<html:radio property="indicadorGeracaoDebitoConta"
						value="2" />N&atilde;o<html:radio
						property="indicadorGeracaoDebitoConta" value="3" />Todas</strong></td>

				</tr>



				<tr>

					<td><strong>Tipo do Lançamento do Item Contábil:</strong></td>

					<td><html:select property="lancamentoItemContabil">

						<html:option value="-1">&nbsp;</html:option>

						<html:options collection="colecaoLancamentoItemContabil"
							labelProperty="descricao" property="id" />

					</html:select> <font size="1">&nbsp; </font></td>

				</tr>



				<tr>

					<td><strong>Tipo de Financiamento:</strong></td>

					<td><html:select property="financiamentoTipo">

						<html:option value="-1">&nbsp;</html:option>

						<html:options collection="colecaoFinanciamentoTipo"
							labelProperty="descricao" property="id" />

					</html:select> <font size="1">&nbsp; </font></td>

				</tr>

				<tr>
					<td><strong>Valor Sugerido?</strong></td>
					
					<td><strong> <html:radio property="valorSugerido" value="1" />Sim
					<html:radio property="valorSugerido" value="2" /> Não<html:radio
						property="valorSugerido" value="3" /> Todos</strong></td>
						
					<!--<td><strong> <html:text property="valorSugerido" size="14"
						maxlength="13"
						onkeyup="javascript:formataValorMonetario(this,13);"
						style="text-transform: none;" /> </strong></td>
				--></tr>



				<tr>

					<td><strong>Indicador de Uso:</strong></td>

					<td><strong> <html:radio property="indicadorUso" value="1" />Ativo
					<html:radio property="indicadorUso" value="2" /> Inativo<html:radio
						property="indicadorUso" value="3" /> Todas</strong></td>

				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>


				<tr>

					<td><input name="Button" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirFiltrarTipoDebitoAction.do?menu=sim"/>'">

					<!--  <input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="window.location.href='/gsan/telaPrincipal.do'">--></td>

					<td align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Filtrar" align="right"
						onClick="javascript:validarForm(document.forms[0]);"></td>

				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
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



