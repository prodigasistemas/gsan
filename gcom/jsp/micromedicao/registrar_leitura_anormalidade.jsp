<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@page isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript">
function validarForm(){
  if(confirm('Confirma registrar leituras e anormalidades?')){
        document.forms[0].submit();
    
  }
}

function desabilitaCampos(){
if(document.forms[0].movimentoCelular[0].checked){
document.forms[0].uploadPicture.disabled = true;
}else{
document.forms[0].uploadPicture.disabled = false;
}
}
</script>

</head>
<body leftmargin="5" topmargin="5" onload="desabilitaCampos();">

<form action="/gsan/registrarLeiturasAnormalidadesAction.do"
	method="post" enctype="multipart/form-data"><%@ include
	file="/jsp/util/cabecalho.jsp"%> <%@ include file="/jsp/util/menu.jsp"%>

<table width="770" border="0" cellspacing="5" cellpadding="0">
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
		<table>
			<tr>
				<td></td>
			</tr>
		</table>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
				<td class="parabg">Registrar Leituras e Anormalidades</td>
				<td width="11" valign="top"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>

		<p>&nbsp;</p>
		<table width="100%" border="0">
			<tr>
				<td colspan="2">Para registrar as leituras e anormalidades, informe
				os dados abaixo:</td>
			</tr>
			<tr>
				<td><strong>Grupo de Faturamento:</strong></td>
				<td><select name="idFaturamentoGrupo">
					<c:forEach items="${requestScope.faturamentosGrupos}"
						var="faturamentoGrupo">
						<option value="${faturamentoGrupo.id}">${faturamentoGrupo.descricao}</option>
					</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td><strong>Movimento Celular:<font color="#FF0000">*</font></strong></td>
				<td><div align="left"><input type="radio" name="movimentoCelular" tabindex="3"
						value="1" onclick="desabilitaCampos();"/><strong>Sim</strong>
					<input type="radio" name="movimentoCelular" tabindex="4"
						value="2" checked="checked" onclick="desabilitaCampos();"/>
					<strong>Não</strong></div>
				</td>
			</tr>
			<tr>
				<td><strong>Nome Arquivo:<font color="#FF0000">*</font></strong></td>
				<td><input type="file" style="textbox" name="uploadPicture"
					size="50" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td align="left"><font color="#FF0000">*</font> Campo
				Obrigat&oacute;rio</td>
			</tr>
		</table>

		<table>
			<tr>
				<td width="600" align="right">&nbsp;</td>
				<td><input type="button" name="Button" class="bottonRightCol"
					value="Registrar" onclick="validarForm();" /></td>
			</tr>
		</table>
	</tr>


</table>
<%@ include file="/jsp/util/rodape.jsp"%></form>
</body>
</html:html>
