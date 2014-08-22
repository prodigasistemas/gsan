<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>
	validacao/regras_validator.js">
</script>

<html:javascript staticJavascript="false"
	formName="RelatorioImoveisSituacaoPeriodoActionForm" dynamicJavascript="true" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>
	util.js">
</script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>
	Calendario.js">
</script>

<script language="JavaScript">
	
	function gerar() {
		var form = document.forms[0];
		if(form.idSituacaoCadastral.value == '') {
			alert('Informe a Situação');
		} else {
			form.submit();
		}
	}
	
	function desabilitarDatas() {
		var form = document.forms[0];
		if(form.idSituacaoCadastral.value == '1'
			|| form.idSituacaoCadastral.value == '2'
			|| form.idSituacaoCadastral.value == '5') {

			form.dataInicial.disabled=true;
			form.dataInicial.value = '';
			form.dataFinal.disabled=true;
			form.dataFinal.value = '';
		} else {
			form.dataInicial.disabled=false;
			form.dataFinal.disabled=false;
		}
	}
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');bloquearGrupo();">

<html:form action="/gerarRelatorioImoveisSituacaoPeriodoAction.do"
	name="RelatorioImoveisSituacaoPeriodoActionForm"
	type="gcom.gui.relatorio.cadastro.atualizacaocadastral.RelatorioImoveisSituacaoPeriodoActionForm" method="post">

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
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Relat&oacute;rio de Im&oacute;veis por Situa&ccedil;&atilde;o e Per&iacute;odo</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para gerar o relat&oacute;rio, informe os dados abaixo:</td>
				</tr>
				
				<tr>
          			<td width="30%"><strong>Situa&ccedil;&atilde;o:<font color="#FF0000">*</font></strong> </td>
          			<td width="70%">
          				<html:select property="idSituacaoCadastral" onchange="javascript:desabilitarDatas();">
          					<html:option value="-1">&nbsp;</html:option>
             	 			<html:options collection="colecaoSituacoes" labelProperty="descricao" property="id" />
             			</html:select>
          			</td>
        		</tr>

				<tr>
					<td width="30%"><strong>Per&iacute;odo:<font color="#FF0000">*</font></strong></td>
                  <td> 
                    <input type="hidden" name="dataLimpa" value="0">
                  	<html:text name="RelatorioImoveisSituacaoPeriodoActionForm" onkeyup="mascaraData(this, event);/*replicarCampo(document.forms[0].dataFinal,this);*/"
                  	 onblur="javascript:dataEstahLimpa()"  property="dataInicial" size="10" maxlength="10"
                  	 onkeypress="javascript:return isCampoNumerico(event);"/> 
						<a href="javascript:abrirCalendario('RelatorioImoveisSituacaoPeriodoActionForm', 'dataInicial')"><img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
                    <strong>a</strong> 
                    <html:text name="RelatorioImoveisSituacaoPeriodoActionForm" onkeyup="mascaraData(this, event);" property="dataFinal" size="10" maxlength="10" 
                     onkeypress="javascript:return isCampoNumerico(event);"/> 
						<a href="javascript:abrirCalendario('RelatorioImoveisSituacaoPeriodoActionForm', 'dataFinal')"><img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" align="middle" alt="Exibir Calendário" /></a> dd/mm/aaaa</td>
				
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td><input type="button" name="Button" class="bottonRightCol"
						value="Limpar"
						onclick="window.location.href='<html:rewrite page="/exibirGerarRelatorioImoveisSituacaoPeriodo.do?menu=sim"/>'">
					</td>
					<td>
					<div align="right"><input type="Button" value="Gerar"
						onclick="javascript:gerar();" class="bottonRightCol" /></div>
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
