<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ page import="gcom.util.ConstantesSistema"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>


<SCRIPT LANGUAGE="JavaScript">
<!--
	function verificarChecado(valor){
		form = document.forms[0];
		if(valor == "1"){
		 	form.indicadorAtualizar.checked = true;
		 }else{
		 	form.indicadorAtualizar.checked = false;
		}
	}
	
	function validarForm(){
		var form = document.FiltrarTipoCorteActionForm;
		
		if(testarCampoValorZero(form.idTipoCorte, 'Código do Tipo de Corte')
			&& testarCampoValorZero(form.descricao, 'Descricao do Tipo de Corte')) {

			if(validateFiltrarTipoCorteActionForm(form)){
				form.action = 'filtrarTipoCorteAction.do?atualizar=' + form.indicadorAtualizar.value;
				submeterFormPadrao(form);
			}
	    }
	}
	
	function verificarValorAtualizar(){
		var form = document.FiltrarTipoCorteActionForm;
       	
       	if (form.indicadorAtualizar.checked == true) {
       		form.indicadorAtualizar.value = '1';
       	} else {
       		form.indicadorAtualizar.value = '';
       	}
       	
	}
	
	
//-->
</SCRIPT>

</head>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarTipoCorteActionForm" />

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');verificarChecado('${sessionScope.atualizar}');verificarValorAtualizar();">

<html:form action="/filtrarTipoCorteAction"
	name="FiltrarTipoCorteActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.FiltrarTipoCorteActionForm"
	method="post"
	onsubmit="return validateFiltrarTipoCorteActionForm(this);">


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

			<td width="625" valign="top" class="centercoltext">

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
					<td class="parabg">Filtrar Tipo de Corte</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td width="100%" colspan="3">
					<table width="100%">
						<tr>
							<td width="80%">Para filtrar o(s) Tipo(s) de Corte(s), informe os dados abaixo:</td>
							<td align="right"><html:checkbox property="indicadorAtualizar" value="1"
								onclick="javascript:verificarValorAtualizar()" /><strong>Atualizar</strong></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td width="120"><strong>C&oacute;digo:</strong></td>
					<td colspan="2"><html:text property="idTipoCorte" size="4" maxlength="2" tabindex="1" /></td>
				</tr>
			
				<tr>
					<td><strong>Descrição:</strong></td>
					<td colspan="2"><html:text property="descricao" size="30" maxlength="25" tabindex="2" /></td>
				</tr>
				
				<tr>
					<td></td>
					<td>
						<html:radio property="tipoPesquisa" tabindex="3" value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" /> Iniciando pelo texto
						<html:radio property="tipoPesquisa" tabindex="4" value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" /> Contendo o texto
					</td>
				</tr>

				<tr>
					<td><strong>Indicador de uso:</strong></td>
					<td>
						<html:radio property="indicadorUso" value="1" tabindex="5" /><strong>Ativo</strong>
						<html:radio property="indicadorUso" value="2" tabindex="6" /><strong>Inativo</strong>
						<html:radio property="indicadorUso" value="3" tabindex="7" /><strong>Todos</strong>
					</td>
				</tr>
				
				<tr>
					<td><strong>Indicador Corte Administrativo:</strong></td>
					<td>
						<html:radio property="indicadorCorteAdministrativo" value="1" tabindex="8" /><strong>Ativo</strong>
						<html:radio property="indicadorCorteAdministrativo" value="2" tabindex="9" /><strong>Inativo</strong>
						<html:radio property="indicadorCorteAdministrativo" value="3" tabindex="10" /><strong>Todos</strong>
					</td>
				</tr>
				
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="window.location.href='/gsan/exibirFiltrarTipoCorteAction.do?menu=sim'" tabindex="7"></td>
					<td align="right" colspan="2"><input name="Button" type="button"
						class="bottonRightCol" value="Filtrar" align="left"
						onclick="javascript:validarForm();" tabindex="8"></td>
					<td align="right"></td>
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

