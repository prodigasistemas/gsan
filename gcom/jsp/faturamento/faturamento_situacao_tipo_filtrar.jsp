<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="gcom.util.ConstantesSistema"%>

<html:html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="FiltrarFaturamentoSituacaoTipoActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(formulario){	
			if(validateFiltrarFaturamentoSituacaoTipoActionForm(formulario)){    		
	    		if(validateInteger(formulario)){	     
		  			submeterFormPadrao(formulario);
		  		}
		}
	}
	

	function IntegerValidations () {
		this.aa = new Array("id", "O campo Código deve conter apenas números.", new Function ("varName", " return this[varName];"));
	}

    function verificarChecado(valor){
		form = document.forms[0];
		if(valor == "1"){
		 	form.indicadorAtualizar.checked = true;
		 }else{
		 	form.indicadorAtualizar.checked = false;
		}
	}
	
	
	function limparForm() {
		var form = document.AtualizarFaturamentoSituacaoTipoActionForm;
		form.descricao.value = "";
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5">
<html:form action="/filtrarFaturamentoSituacaoTipoAction"
	name="FiltrarFaturamentoSituacaoTipoActionForm"
	type="gcom.gui.faturamento.FiltrarFaturamentoSituacaoTipoActionForm"
	method="post"
	onsubmit="return validateFiltrarFaturamentoSituacaoTipoActionForm(this);">


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
					<td class="parabg">Filtrar Tipo de Situação de Faturamento</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>

					<td colspan="2">Para filtrar o(s) tipo(s) da situação de
					faturamento, informe o dado abaixo:</td>
					<td width="100" align="left" colspan="2"><html:checkbox
						property="indicadorAtualizar" value="1" /><strong>Atualizar</strong></td>
				</tr>

				<tr>
					<td><strong>C&oacute;digo:</strong></td>
					<td><html:text property="id" size="5" maxlength="5" /><font
						size="1">&nbsp;(somente números)</font> <br>
					<font color="red"><html:errors property="id" /></font></td>
				</tr>

				<tr>
					<td width="25%"><strong>Descri&ccedil;&atilde;o:</strong></td>
					<td colspan="2"><span class="style2"> 
						<html:text property="descricao" 
							size="50" 
							maxlength="51" /> </span>
					</td>
					<td width="15%">&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<html:radio property="tipoPesquisa" 
							tabindex="5"
							value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
							Iniciando pelo texto 
							
						<html:radio property="tipoPesquisa"
							tabindex="6"
							value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
							Contendo o texto
					</td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td><strong>Paralisa Faturamento?</strong></td>
					<td><html:radio property="indicadorParalisacaoFaturamento"
						tabindex="5" value="1" /><strong>Sim</strong> <html:radio
						property="indicadorParalisacaoFaturamento" tabindex="7" value="2" /><strong>Não</strong>
					<html:radio property="indicadorParalisacaoFaturamento" tabindex="8"
						value="" /><strong>Todos</strong></td>
				</tr>

				<tr>
					<td><strong>Paralisa Leitura?</strong></td>
					<td><html:radio property="indicadorParalisacaoLeitura" tabindex="5"
						value="1" /><strong>Sim</strong> <html:radio
						property="indicadorParalisacaoLeitura" tabindex="7" value="2" /><strong>Não</strong>
					<html:radio property="indicadorParalisacaoLeitura" tabindex="8"
						value="" /><strong>Todos</strong></td>
				</tr>

				<tr>
					<td><strong>Valido para Água?</strong></td>
					<td><html:radio property="indicadorValidoAgua" tabindex="5"
						value="1" /><strong>Sim</strong> <html:radio
						property="indicadorValidoAgua" tabindex="7" value="2" /><strong>Não</strong>
					<html:radio property="indicadorValidoAgua" tabindex="8" value="" /><strong>Todos</strong>
					</td>
				</tr>

				<tr>
					<td><strong>Valido para Esgoto?</strong></td>
					<td><html:radio property="indicadorValidoEsgoto" tabindex="5"
						value="1" /><strong>Sim</strong> <html:radio
						property="indicadorValidoEsgoto" tabindex="7" value="2" /><strong>Não</strong>
					<html:radio property="indicadorValidoEsgoto" tabindex="8" value="" /><strong>Todos</strong>
					</td>
				</tr>

				<tr>
					<td><strong>Informar Consumo Fixo?</strong></td>
					<td><html:radio property="indicadorInformarConsumo" tabindex="5"
						value="1" /><strong>Sim</strong> <html:radio
						property="indicadorInformarConsumo" tabindex="7" value="2" /><strong>Não</strong>
					<html:radio property="indicadorInformarConsumo" tabindex="8"
						value="" /><strong>Todos</strong></td>
				</tr>

				<tr>
					<td><strong>Informar Volume Fixo?</strong></td>
					<td><html:radio property="indicadorInformarVolume" tabindex="5"
						value="1" /><strong>Sim</strong> <html:radio
						property="indicadorInformarVolume" tabindex="7" value="2" /><strong>Não</strong>
					<html:radio property="indicadorInformarVolume" tabindex="8"
						value="" /><strong>Todos</strong></td>
				</tr>

				<tr>
					<td><strong>Anormalidade de Consumo Cobrar Sem Leitura:</strong></td>
					<td><html:select property="leituraAnormalidadeConsumoSemLeitura">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoAnormalidadeConsumoSemLeitura"
							labelProperty="descricaoConsumoACobrar" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>

				<tr>
					<td><strong>Anormalidade de Consumo Cobrar Com Leitura:</strong></td>
					<td><html:select property="leituraAnormalidadeConsumoComLeitura">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoAnormalidadeConsumoComLeitura"
							labelProperty="descricaoConsumoACobrar" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>

				<tr>
					<td><strong>Anormalidade de Leitura Faturar Sem Leitura:</strong></td>
					<td><html:select property="leituraAnormalidadeLeituraSemLeitura">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options
							collection="colecaoAnormalidadeLeituraFaturarSemLeitura"
							labelProperty="descricaoFaturamento" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>

				<tr>
					<td><strong>Anormalidade de Leitura Faturar Com Leitura:</strong></td>
					<td><html:select property="leituraAnormalidadeLeituraComLeitura">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options
							collection="colecaoAnormalidadeLeituraFaturarComLeitura"
							labelProperty="descricaoFaturamento" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>

				<tr>
					<td><strong>Indicador de Uso:</strong></td>
					<td><html:radio property="indicadorUso" tabindex="5" value="1" /><strong>Ativo</strong>
					<html:radio property="indicadorUso" tabindex="7" value="2" /><strong>Inativo</strong>
					<html:radio property="indicadorUso" tabindex="8" value="" /><strong>Todos</strong>
					</td>
				</tr>

				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="window.location.href='/gsan/exibirFiltrarFaturamentoSituacaoTipoAction.do?menu=sim'"
						tabindex="8"></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td width="65" align="right">
						<input name="Button2" 
							type="button"
							class="bottonRightCol" 
							value="Filtrar" 
							align="right"
							onClick="javascript:validarForm(document.forms[0]);" 
							tabindex="9" />
					</td>
				</tr>

			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
