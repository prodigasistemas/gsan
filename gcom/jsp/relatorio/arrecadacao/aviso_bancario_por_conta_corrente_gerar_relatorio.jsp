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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="GerarRelatorioAvisoBancarioPorContaCorrenteActionForm" />
<script>
	function validaMesAno(form){
		var mesAno = form.mesAno.value;
		
		if (mesAno.length < 7 || mesAno.substring(2,3) != "/" ||
		    mesAno.substring(0,2) < "01" || mesAno.substring(0,2) > "12") {
			alert("Mês/Ano inválido.");
			return false;
		}
		
		return true;
	}

	function validarForm() {
		var form = document.forms[0];
		
		if (validaMesAno(form)) {
			return true;
		} else {
			return false;
		}
	}
	
	function limparContaBancaria() {
		var form = document.forms[0];
		
		form.idContaBancaria.value = "";
		form.idBancoDaConta.value = "";
		form.codigoAgencia.value = "";
		form.numeroConta.value = "";
	}
	
	function recuperarDadosCincoParametros(codigoRegistro, idBanco, codigoAgencia, numeroConta, tipoConsulta) {
		var form = document.forms[0];
		
	    if (tipoConsulta == 'contaBancaria') {
			form.idContaBancaria.value = codigoRegistro;
			form.idBanco.value = idBanco;
			form.idBancoDaConta.value = idBanco;
			form.codigoAgencia.value = codigoAgencia;
			form.numeroConta.value = numeroConta;
		}
	}
	
</script>


</head>

<body leftmargin="5" topmargin="5">
<html:form 
	action="/gerarRelatorioAvisoBancarioPorContaCorrenteAction.do"
	name="GerarRelatorioAvisoBancarioPorContaCorrenteActionForm"
	type="gcom.gui.relatorio.arrecadacao.GerarRelatorioAvisoBancarioPorContaCorrenteActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="115" valign="top" class="leftcoltext">
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
			<td valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img
						src="<bean:message key="caminho.imagens"/>parahead_left.gif"
						border="0" /></td>
					<td class="parabg">Relatório de Avisos Bancários por Conta Corrente</td>
					<td width="11" valign="top"><img
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para gerar o Relatório de Aviso Bancários por Conta Corrente, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="26%"><strong>Mês/Ano da Arrecadação:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="mesAno" size="7" maxlength="7" onkeyup="mascaraAnoMes(this, event);"/>
					<strong>&nbsp; </strong>mm/aaaa</td>
				</tr>
				<tr>
					<td><strong>Banco</strong></td>
					<td width="38%" align="left">
						<html:select property="idBanco" onchange="javascript:limparContaBancaria();" >
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="collectionBanco" labelProperty="descricao" property="id" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td><strong>Conta Bancária</strong></td>
					<td width="70%">
						<html:hidden property="idContaBancaria"/>
						<html:text maxlength="4" property="idBancoDaConta" size="4" readonly="true" style="background-color:#EFEFEF; border:0; font-color: #000000" />
						<html:text maxlength="9" property="codigoAgencia" size="9" readonly="true" style="background-color:#EFEFEF; border:0; font-color: #000000" />
						<html:text maxlength="20" property="numeroConta" size="20" readonly="true" style="background-color:#EFEFEF; border:0; font-color: #000000" />
						<a href="javascript:abrirPopup('contaBancariaPesquisarAction.do?tipo=contaBancaria&idBancoRecebido=' + document.forms[0].idBanco.value);" >
						<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Conta Bancária" /></a>
						<a href="javascript:limparContaBancaria();">
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"	border="0" title="Limpar Conta Bancária" /></a>
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td colspan="2" align="left"><font color="#FF0000">*</font> Campo
					Obrigat&oacute;rio</td>
				</tr>
				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
					<td colspan="2"></td>
				</tr>
			</table>
			<table>
				<tr>
					<td width="100%">
					<table width="30%" border="0" align="right" cellpadding="0"
						cellspacing="2">
						<tr>
							<td width="61">&nbsp;</td>
							<td width="416">&nbsp;</td>
							<td width="12"></td>
							<td width="61">
							
							<input type="button" class="bottonRightCol"
								value="Gerar Relat&oacute;rio"
								onclick="if (validarForm()) { toggleBox('demodiv',1); }">
							
							<%-- <input type="button" class="bottonRightCol"
								value="Gerar Relat&oacute;rio"
								onclick="javascript:validarForm();">--%>
								</td>
							<td width="82">&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioAvisoBancarioPorContaCorrenteAction.do"/> 
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
