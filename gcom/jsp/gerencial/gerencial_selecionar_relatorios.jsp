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
	
<script>

function relatorioSelecionado(valor) {



	if (valor != "") {
	    //alert('http://192.168.1.220:8080/mondrian/testpage.jsp?query=mondrian&parametro='+valor);
		window.location.href = 'http://aplic.compesa.com.br:8080/mondrian/testpage.jsp?query=mondrian&parametro='+valor;
	}

}

</script>	
	
</head>

<body leftmargin="5" topmargin="5">

<form name="form" method="post">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="625" valign="top" class="centercoltext">
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
				<td class="parabg">GSAN - Gerencial</td>
				<td width="11"><img
					src="<bean:message key="caminho.imagens"/>parahead_right.gif"
					border="0" /></td>
			</tr>
		</table>
		<!-- <p>&nbsp;</p> --> <!--  <p><div align="center"><img name="rImage" width="510" height="294" /></div> -->
		<table width="95%">
			<tr>

				<td align="center"><img
					src="<bean:message key="caminho.imagens"/>gsan.gif" border="0"> <br>
				</td>

			</tr>
			<tr>

				<td align="left">
				<table border=0 width="100%">
					<tr>
						<td align="left">Cadastro<br>
						&nbsp;&nbsp; <select name="selectCadastro" style="width: 280px;" onclick="javascript:relatorioSelecionado(document.form.selectCadastro[document.form.selectCadastro.selectedIndex].value);">
							<option value="">&nbsp;</option>

							<logic:iterate id="cadastro" name="relatoriosCadastro"
								scope="request">
								<option value="${cadastro.value}">${cadastro.key}</option>
							</logic:iterate>
						</select></td>
						
						
						<td align="left">Cobranca<br>
						&nbsp;&nbsp;<select name="selectCobranca" style="width: 280px;" onclick="javascript:relatorioSelecionado(document.form.selectCobranca[document.form.selectCobranca.selectedIndex].value);">
							<option value="">&nbsp;</option>
							<logic:iterate id="cobranca" name="relatoriosCobranca"
								scope="request">
								<option value="${cobranca.value}">${cobranca.key}</option>
							</logic:iterate>

						</select></td>

					</tr>
					<tr>
						<td align="left">Micromedicao<br>
						&nbsp;&nbsp;<select name="selectMicromedicao"
							style="width: 280px;" onclick="javascript:relatorioSelecionado(document.form.selectMicromedicao[document.form.selectMicromedicao.selectedIndex].value);">
							<option value="">&nbsp;</option>
							<logic:iterate id="micromedicao" name="relatoriosMicromedicao"
								scope="request">
								<option value="${micromedicao.value}">${micromedicao.key}</option>
							</logic:iterate>

						</select></td>
						<td align="left">Arrecadacao<br>
						&nbsp;&nbsp;<select name="selectArrecadacao" style="width: 280px;" onclick="javascript:relatorioSelecionado(document.form.selectArrecadacao[document.form.selectArrecadacao.selectedIndex].value);">
							<option value="">&nbsp;</option>
							<logic:iterate id="arrecadacao" name="relatoriosArrecadacao"
								scope="request">
								<option value="${arrecadacao.value}">${arrecadacao.key}</option>
							</logic:iterate>

						</select></td>

					</tr>
					<tr>
						<td align="left">Faturamento<br>
						&nbsp;&nbsp;<select name="selectFaturamento" style="width: 280px;" onclick="javascript:relatorioSelecionado(document.form.selectFaturamento[document.form.selectFaturamento.selectedIndex].value);">
							<option value="">&nbsp;</option>
							<logic:iterate id="faturamento" name="relatoriosFaturamento"
								scope="request">
								<option value="${faturamento.value}">${faturamento.key}</option>
							</logic:iterate>

						</select></td>
						<td align="left">Atendimento ao Publico<br>
						&nbsp;&nbsp;<select name="selectAtendimentoPublico"
							style="width: 280px;" onclick="javascript:relatorioSelecionado(document.form.selectAtendimentoPublico[document.form.selectAtendimentoPublico.selectedIndex].value);">
							<option value="">&nbsp;</option>
							<logic:iterate id="atendimento"
								name="relatoriosAtendimentoPublico" scope="request">
								<option value="${atendimento.value}">${atendimento.key}</option>
							</logic:iterate>

						</select></td>

					</tr>
				</table>
				</td>

			</tr>

		</table>
		</td>
	</tr>
</table>
<%@ include file="/jsp/util/rodape.jsp"%>

</form>
</body>
</html:html>
