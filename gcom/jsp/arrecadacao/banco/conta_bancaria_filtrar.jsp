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
	formName="FiltrarContaBancariaActionForm" />

<script language="JavaScript">
  
	function validaForm() {
	  	var form = document.forms[0];
		if(validateFiltrarContaBancariaActionForm(form)) {
	     		var atualizar = form.atualizar.value;
				form.action = 'filtrarContaBancariaAction.do?atualizar=' + atualizar;  		
				submeterFormPadrao(form);   		  
   	      	
   	    }
	}
	 
 
	function limparForm() {
		var form = document.FiltrarContaBancariaActionForm;
		form.banco.value = "";
		form.contaBanco.value = "";
			
	}  
	
	function reload() {
		var form = document.FiltrarContaBancariaActionForm;
		form.action = "/gsan/exibirFiltrarContaBancariaAction.do";
		form.submit();
	}  
	
	
	function verificarValorAtualizar() {
	
		var form = document.forms[0];
	
		if (form.atualizar.checked == true) {
			form.atualizar.value = '1';
		} else {
			form.atualizar.value = '';
		}
		
	}
	
	function checkAtualizar(valor) {
	
		var form = document.forms[0];
		
		if (valor == '1') {
			form.atualizar.checked = true;
		} else {
			form.atualizar.checked = false;
		}
		
	}

</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="setarIndicadorTrocaServico('${requestScope.deferimento}', '${servicoTipoReferencia.indicadorExistenciaOsReferencia}');checkAtualizar('${sessionScope.atualizar}');verificarValorAtualizar();">

<html:form action="/filtrarContaBancariaAction"
	name="FiltrarContaBancariaActionForm"
	type="gcom.gui.arrecadacao.banco.FiltrarContaBancariaActionForm"
	method="post"
	onsubmit="return validateFiltrarContaBancariaActionForm(this);">

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

			<td width="625" valign="top" class="centercoltext">

			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Filtrar Conta Bancária</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td height="10" colspan="3">Para filtrar uma conta bancária,
					informe os dados abaixo:</td>
					<td align="right"><html:checkbox property="atualizar" value="1"
						onclick="javascript:verificarValorAtualizar()" /><strong>Atualizar</strong></td>
				</tr>

				<!-- Banco -->

				<tr>
					<td><strong>Banco:</strong></td>
					<td colspan="2" align="left"><html:select property="banco"
						onchange="javascript:reload();">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoBanco" labelProperty="descricao"
							property="id" />
					</html:select></td>
				</tr>

				<!-- Agencia -->

				<logic:notEmpty name="colecaoAgencia">
					<tr>
						<td><strong>Agência:</strong></td>
						<td colspan="2" align="left"><html:select
							property="agenciaBancaria">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoAgencia"
								labelProperty="nomeAgencia" property="id" />
						</html:select></td>
					</tr>
				</logic:notEmpty>

				<logic:empty name="colecaoAgencia">
					<tr>
						<td><strong>Agência:</strong></td>
						<td colspan="2" align="left"><select name="agenciaBancaria">
							<option value="-1">&nbsp;</option>
						</select></td>
					</tr>
				</logic:empty>


				<!-- Conta Bancária -->

				<tr>
					<td><strong>Conta Bancária:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="contaBanco" size="20" maxlength="20" /> </span></td>
				</tr>


				<!-- Botões -->

				<tr>
					<td><strong> <font color="#FF0000"> <input type="button"
						name="Submit22" class="bottonRightCol" value="Limpar"
						onClick="javascript:window.location.href='/gsan/exibirFiltrarContaBancariaAction.do?menu=sim'"><!-- <input type="button"
								name="Submit23" class="bottonRightCol" value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"> -->
					</font> </strong></td>
					<td colspan="3" align="right"><input type="button" name="Submit2"
						class="bottonRightCol" value="Filtrar"
						onclick="validaForm(document.forms[0]);"></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</html:html>
