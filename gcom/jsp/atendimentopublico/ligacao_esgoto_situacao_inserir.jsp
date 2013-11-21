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
	formName="InserirLigacaoEsgotoSituacaoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">

	function validarForm(){
			var form = document.forms[0];
	 	if (validateInserirLigacaoEsgotoSituacaoActionForm(form)){			
			if(validaTodosRadioButton(form)){	
				if(validateInteger(form)){	     
		  			submeterFormPadrao(form);
		  		}
   	    	}
		}
	}
	
	function validaRadioButton(nomeCampo,mensagem){
		
		var alerta = "";
		if(!nomeCampo[0].checked && !nomeCampo[1].checked){
			alerta = "Informe " + mensagem +".";
		}
		return alerta;
   	}
   
  	function validaTodosRadioButton(){
		
		var form = document.forms[0];
		var mensagem = "";
		
		if(validaRadioButton(form.indicadorFaturamentoSituacao,"Indicador de Faturamento.") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorFaturamentoSituacao,"Indicador de Faturamento.")+"\n";
		}
		
		if(validaRadioButton(form.indicadorExistenciaLigacao,"Indicador de Existência de Ligação.") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorExistenciaLigacao,"Indicador de Existência de Ligação.")+"\n";
		}
		
		if(validaRadioButton(form.indicadorExistenciaRede,"Indicador de Existência de Rede.") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorExistenciaRede,"Indicador de Existência de Rede.")+"\n";
		}
		
		if(mensagem != ""){
			alert(mensagem);
			return false;
		}else{
			return true;
		}
   	} 
	
	function IntegerValidations () {
		this.aa = new Array("volumeMinimoFaturamento", "O campo Volume Mínimo deve conter apenas números.", new Function ("varName", " return this[varName];"));
	}	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('descricao');">
	<html:form action="/inserirLigacaoEsgotoSituacaoAction.do"
		name="InserirLigacaoEsgotoSituacaoActionForm"
		type="gcom.gui.atendimentopublico.InserirLigacaoEsgotoSituacaoActionForm"
		method="post"
		onsubmit="return validateInserirLigacaoEsgotoSituacaoActionForm(this);">
	
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

<table>
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
					<td width="11"><img border="0" src="imagens/parahead_left.gif" />
					</td>
					<td class="parabg">Inserir Situa&ccedil;&atilde;o de
					Liga&ccedil;&atilde;o de Esgoto</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para inserir a situa&ccedil;&atilde;o de
					liga&ccedil;&atilde;o de esgoto, informe a descrição abaixo:</td>
				</tr>
				<tr>
					<td height="30" width="100"><strong>Descri&ccedil;&atilde;o: <font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="descricao" maxlength="20"
						size="20" /><br>
					</td>
				</tr>
				<tr>
					<td><strong>Descri&ccedil;&atilde;o Abreviada: <font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:text property="descricaoAbreviado" size="3"
						maxlength="3" /> </strong></td>
				</tr>
				<tr>
					<td><strong>Volume M&iacute;nimo: <font color="#FF0000">*</font></strong></td>
					<td><strong> <html:text property="volumeMinimoFaturamento"
						size="10" maxlength="10" /> </strong></td>
				</tr>
				<tr>
					<td><strong>Indicador de Faturamento: <font color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorFaturamentoSituacao"
						value="<%=""+ConstantesSistema.SIM%>" /> <strong>Sim <html:radio
						property="indicadorFaturamentoSituacao"
						value="<%=""+ConstantesSistema.NAO%>" /> N&atilde;o</strong> </strong></td>
				</tr>
				<tr>
					<td><strong>Indicador de Exist&ecirc;ncia de Rede: <font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorExistenciaRede"
						value="<%=""+ConstantesSistema.SIM%>" /> <strong>Sim <html:radio
						property="indicadorExistenciaRede"
						value="<%=""+ConstantesSistema.NAO%>" /> N&atilde;o</strong> </strong></td>
				</tr>
				<tr>
					<td><strong>Indicador de Exist&ecirc;ncia de Ligação: <font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorExistenciaLigacao"
						value="<%=""+ConstantesSistema.SIM%>" /> <strong>Sim <html:radio
						property="indicadorExistenciaLigacao"
						value="<%=""+ConstantesSistema.NAO%>" /> N&atilde;o</strong> </strong></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campos Obrigatórios</td>
				</tr>
				<tr>
					<td colspan="2"><input name="Button" type="button"
						class="bottonRightCol" value="Limpar" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirInserirLigacaoEsgotoSituacaoAction.do?menu=sim"/>'">
					<input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
					<td width="170" height="24" align="right"><input type="button"
						name="Button2" class="bottonRightCol" value="Inserir"
						onClick="javascript:validarForm();" /></td>

				</tr>
			</table>
			<p>&nbsp;</p>

	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>
			</td>
		</tr>
	</table>

	</html:form>
</body>
</html:html>
