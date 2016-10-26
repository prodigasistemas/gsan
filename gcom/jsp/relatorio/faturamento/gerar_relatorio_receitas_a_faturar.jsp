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
	formName="RelatorioReceitasAFaturarActionForm" dynamicJavascript="true" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>
	util.js">
</script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>
	Calendario.js">
</script>

<script language="JavaScript">
	
function gerar(){
	var form = document.forms[0];
	if(form.mesAno.value == '') {
		alert('Informe Mês/Ano');
	} else if(form.relatorioTipo.value == 2 && form.grupoFaturamentoID.value == -1) {
		alert('É necessário informar um grupo para o relatório analítico.');
	} else {
		form.submit();
	}
}

function validaSelecaoRelatorioOrdenacao(relatorioTipo){
		var form = document.forms[0];	
		var obj = null;
		if(relatorioTipo == null){
			obj = form.relatorioTipo.value;
		}else{
			obj = relatorioTipo.value;
		}
		
		if(obj == 2){
			document.getElementById("relatorioOrdenacao").style.visibility = "visible" ;
			document.getElementById("usarCategoria").style.visibility = "hidden" ;
		}else{
			document.getElementById("relatorioOrdenacao").style.visibility = "hidden" ;
			document.getElementById("usarCategoria").style.visibility = "visible" ;
		}
		
}
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');bloquearGrupo();validaSelecaoRelatorioOrdenacao(this);">

<html:form action="/gerarRelatorioReceitasAFaturarAction.do"
	name="RelatorioReceitasAFaturarActionForm"
	type="gcom.gui.relatorio.faturamento.RelatorioReceitasAFaturarActionForm" method="post">

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
					<td class="parabg">Relat&oacute;rio de Receitas a Faturar</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para gerar o relat&oacute;rio de Receitas a Faturar, informe os
					dados abaixo:</td>
				</tr>

				<tr>
					<td width="30%">
						<strong>M&ecirc;s/Ano:<font color="#FF0000">*</font></strong>
					</td>
					<td width="70%">
						<html:text property="mesAno" size="7" maxlength="7" onkeypress="javascript:mascaraAnoMes(this, event);" />&nbsp;mm/aaaa
					</td>
				</tr>
				<tr>
					<td width="30%">
						<strong>Tipo de Relatório:<font color="#FF0000">*</font></strong>
					</td>
					<td width="70%">
						<html:select property="relatorioTipo" tabindex="1" style="width:200px;" onchange="validaSelecaoRelatorioOrdenacao(this)">
							<html:option value="1"> SINTETICO </html:option>
							<html:option value="2"> ANALITICO </html:option>				    
						</html:select>
					</td>
				</tr>
				<tr id="relatorioOrdenacao" style="visibility:hidden;">
					<td width="30%">
						<strong>Grupo de Faturamento:</strong>
					</td>
					<td width="70%">
						<html:select property="grupoFaturamentoID" tabindex="2">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoFaturamentoGrupo" labelProperty="descricao" property="id" />
						</html:select>
					</td>
				</tr>
				<tr id="usarCategoria" style="visibility:visible;">
					<td width="30%">
						<strong>Agrupar por categoria:</strong>
					</td>
					<td width="70%">
						<strong> 
						<html:radio property="indicadorCategoria" value="1" /> Sim 
						<html:radio property="indicadorCategoria" value="2" /> N&atilde;o
						</strong>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td><input type="button" name="Button" class="bottonRightCol"
						value="Limpar"
						onclick="window.location.href='<html:rewrite page="/exibirGerarRelatorioReceitasAFaturar.do?menu=sim"/>'">
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
