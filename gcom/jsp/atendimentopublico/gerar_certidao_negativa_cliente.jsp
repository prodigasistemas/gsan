<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<%@page import="gcom.util.ConstantesSistema"%>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<%
	Boolean semPermissao = (Boolean) session.getAttribute("semPermissao");
%>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="GerarCertidaoNegativaClienteActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'cliente') {
	      form.idCliente.value = codigoRegistro;
		  form.action = 'exibirGerarCertidaoNegativaClienteAction.do?objetoConsulta=1';
		  form.submit();
		}
	}

	function validarForm(){

		var form = document.forms[0];
		if(validateGerarCertidaoNegativaClienteActionForm(form) &&
			validarCampos() ){

			submeterFormPadrao(form);
		}
	}

	function validarCampos(){

		var form = document.forms[0];

		if ( form.idCliente.value == null || form.idCliente.value == "" ){
			alert("Informe o cliente cuja certidão negativa será gerada.");
			return false;
		}

		return true;
	}

	function limparClienteTecla(){

		var form = document.forms[0];

		form.nomeCliente.value = "";
		form.cpfCnpj.value = "";
	}

	function limparForm(){

		var form = document.forms[0];

		form.idCliente.value = "";
		form.nomeCliente.value = "";
		form.cpfCnpj.value = "";
	}

	function validaEnterCliente(tecla, caminhoActionReload, nomeCampo) {
	var form = document.forms[0];

	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código do Cliente");

	if(form.idCliente.value.length > 0) {
		form.tipoRelacao.value = <%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>;
		form.responsavel[1].checked = true;
		form.responsavel[0].disabled = true;
		form.responsavel[2].disabled = true;
    } else {
	    form.idCliente.value = "";
		form.nomeCliente.value = "";
		form.cpfCnpj.value = "";
		form.tipoRelacao.disabled = false;

		<% if (semPermissao == null || !semPermissao.equals(new Boolean(true))) {	%>
			form.responsavel[0].disabled = false;
			form.responsavel[2].disabled = false;
		<% } %>
	}
}






</script>



</head>



<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.idCliente}');">



<html:form action="/gerarCertidaoNegativaClienteAction.do"
	name="GerarCertidaoNegativaClienteActionForm"
	type="gcom.gui.atendimentopublico.GerarCertidaoNegativaClienteActionForm"
	method="post">



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

			<td width="600" valign="top" class="centercoltext">
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
					<td class="parabg">Gerar Certid&atilde;o Negativa</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">

				<tr>
					<td colspan="2">Para gerar a Certid&atilde;o Negativa, informe os
					dados abaixo:</td>
				</tr>

				<tr>
					<td bordercolor="#000000"><strong>Cliente:<font color="#FF0000">*</font></strong>
					</td>
					<td><html:text property="idCliente" maxlength="9" size="9"
						onkeypress="validaEnterComMensagem(event, 'exibirGerarCertidaoNegativaClienteAction.do?objetoConsulta=1','idCliente', 'Cliente');return isCampoNumerico(event);"
						onkeyup="javascript:limparClienteTecla();"
						/> <a
						href="javascript:abrirPopup('exibirPesquisarClienteAction.do?limparForm=sim', 400, 800);">
					<img width="23" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" /></a>

					<a href="javascript:limparForm();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a></td>
				</tr>
				<tr>
					<td><strong>Cliente Usu&aacute;rio: </strong></td>
					<td><logic:present name="clienteInexistente" scope="request">
						<html:text property="nomeCliente" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" maxlength="40" />
					</logic:present> <logic:notPresent name="clienteInexistente"
						scope="request">
						<html:text property="nomeCliente" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="40" maxlength="40" />
					</logic:notPresent></td>
				</tr>

				<tr>
					<td><strong>CPF ou CNPJ: </strong></td>
					<td><strong> <html:text property="cpfCnpj" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000"
						size="40" maxlength="40" /> </strong></td>
				</tr>

				<tr>
                <td><strong>Responsável:</strong></td>
                <td colspan="6"><span class="style2"><strong>
                  <label>
                  <logic:present name="semPermissao" scope="session">
                  <html:radio property="responsavel" value="0" disabled="true" />
                  </logic:present>
                  <logic:notPresent name="semPermissao" scope="session">
				  <html:radio property="responsavel" value="0" />
				  </logic:notPresent>
 				  Indicado na Conta</label>
                  <label>
                   <logic:present name="semPermissao" scope="session">
                  <html:radio property="responsavel" value="1" disabled="true" />
                  </logic:present>
                  <logic:notPresent name="semPermissao" scope="session">
				  <html:radio property="responsavel" value="1" />
				  </logic:notPresent>
 				  Atual do Imóvel</label>
                  <label>
				  <html:radio property="responsavel" value="2" />
 				  Todos</label>
                  </strong></span>
				</td>
              </tr>

				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campo Obrigatório</td>
				</tr>

				<tr>
					<td height="24"><input type="button" class="bottonRightCol"
						value="Limpar" onclick="javascript:limparForm();" /></td>

					<td align="right"><input type="button" name="Button"
						class="bottonRightCol" value="Gerar Certidão"
						onClick="javascript:validarForm()" /></td>

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

