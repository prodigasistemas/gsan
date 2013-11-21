<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<%@page import="gcom.cadastro.tarifasocial.TarifaSocialCartaoTipo"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="true"  formName="AtualizarDadosTarifaSocialClienteActionForm" dynamicJavascript="false" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<SCRIPT LANGUAGE="JavaScript">
<!--

function fechar(){
		window.close();
}
function confirmar(){

	var form = document.forms[0];

	if (form.clienteImovelFimRelacaoMotivo.value == -1) {
		alert('Informe Motivo');
	} else {
		if (validateDate(form)) {
			form.submit();			
		}
	}
	
}

function DateValidations () {
      this.aa = new Array("dataFimRelacao", "Data Término Relação inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
}

function voltar() {
	window.history.back();
}

</SCRIPT>
</head>

<body leftmargin="0" topmargin="0">
<table width="550" border="0" cellpadding="0" cellspacing="5">
	<html:form action="/removerClienteImovelTarifaSocialAction"
		method="post">
		<html:hidden property="acao" />

		<tr>
			<td width="550" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Fim de relação cliente imóvel tarifa social</td>
					<td width="11"><img border="0" src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2">Clientes selecionados para remoção.</td>
				</tr>

				<tr>
					<td colspan="2">
					<table width="100%" bgcolor="#99ccff" width="100%">
						<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
							<td width="50%">
							<div align="center">Nome</div>
							</td>
							<td width="18%">Tipo</td>
							<td width="25%">CPF/CNPJ
							<div align="center"></div>
							</td>
						</tr>
						<%int cont = 0;
%>
						<logic:present name="colecaoClienteImovelEconomiaRemover">
							<logic:iterate indexId="i" name="colecaoClienteImovelEconomiaRemover"
								id="clienteImovelEconomia">
								<bean:define id="cliente" name="clienteImovelEconomia"
									property="cliente" />
								<bean:define id="clienteRelacaoTipo"
									name="clienteImovelEconomia" property="clienteRelacaoTipo" />
								<%cont = cont + 1;
			if (cont % 2 == 0) {%>
								<tr bgcolor="#cbe5fe">
									<%} else {

			%>
								<tr bgcolor="#FFFFFF">
									<%}%>
									<td width="50%">
									<div><bean:write name="cliente" property="nome" /></div>
									</td>
									<td width="18%"><bean:write name="clienteRelacaoTipo"
										property="descricao" /></td>
									<td width="25%"><logic:present name="cliente" property="cpf">
										<bean:define name="cliente" property="cpf" id="cpf" />
          		CPF - <%=gcom.util.Util.formatarCPFApresentacao((cpf + ""))%>
									</logic:present> <logic:notPresent name="cliente"
										property="cpf">
										<logic:present name="cliente" property="rg">
											<bean:define name="cliente" property="unidadeFederacao"
												id="unidadeFederacao" />
											<bean:define name="cliente" property="orgaoExpedidorRg"
												id="orgaoExpedidorRg" />
											<bean:define name="cliente" property="rg" id="rg" />
	          		RG - <%=gcom.util.Util.formatarRGApresentacao((rg + ""),
							(orgaoExpedidorRg + ""), (unidadeFederacao + ""))%>
										</logic:present>
									</logic:notPresent></td>
								</tr>
							</logic:iterate>
						</logic:present>
						<logic:present name="colecaoClienteImovelRemover">
							<logic:iterate name="colecaoClienteImovelRemover"
								id="clienteImovel">
								<bean:define id="cliente" name="clienteImovel"
									property="cliente" />
								<bean:define id="clienteRelacaoTipo" name="clienteImovel"
									property="clienteRelacaoTipo" />
								<%cont = cont + 1;
			if (cont % 2 == 0) {%>
								<tr bgcolor="#cbe5fe">
									<%} else {

			%>
								<tr bgcolor="#FFFFFF">
									<%}%>
									<td width="50%">
									<div><bean:write name="cliente" property="nome" /></div>
									</td>
									<td width="18%"><bean:write name="clienteRelacaoTipo"
										property="descricao" /></td>
									<td width="25%"><logic:present name="cliente" property="cpf">
										<bean:define name="cliente" property="cpf" id="cpf" />
          		CPF - <%=gcom.util.Util.formatarCPFApresentacao((cpf + ""))%>
									</logic:present> <logic:notPresent name="cliente"
										property="cpf">
										<logic:present name="cliente" property="rg">
											<bean:define name="cliente" property="unidadeFederacao"
												id="unidadeFederacao" />
											<bean:define name="cliente" property="orgaoExpedidorRg"
												id="orgaoExpedidorRg" />
											<bean:define name="cliente" property="rg" id="rg" />
	          		RG - <%=gcom.util.Util.formatarRGApresentacao((rg + ""),
							(orgaoExpedidorRg + ""), (unidadeFederacao + ""))%>
										</logic:present>
									</logic:notPresent></td>
								</tr>
							</logic:iterate>
							</logic:present>
					</table>
					</td>
				</tr>

				<tr>

					<td colspan="2">
					<table width="100%">
						<tr>
							<td width="10%"><strong>Data Término Relação<font color="#FF0000">*</font></strong></td>
							<td width="14%"><html:text onkeyup="mascaraData(this, event);"
								property="dataFimRelacao" maxlength="10" size="11" /> <a
								href="javascript:abrirCalendario('AtualizarDadosTarifaSocialClienteActionForm', 'dataFimRelacao')">
							<img border="0"
								src="<bean:message key='caminho.imagens'/>calendario.gif"
								width="20" border="0" align="middle" alt="Exibir Calendário" />
							</a></td>
						</tr>
						<tr>
							<td width="10%"><strong>Motivo<font color="#FF0000">*</font></strong></td>
							<td><html:select property="clienteImovelFimRelacaoMotivo">
								<html:option value="-1">&nbsp;</html:option>
								<html:options collection="colecaoFimRelacaoMotivo"
									labelProperty="descricao" property="id" />
							</html:select></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><strong><font color="#FF0000">*</font></strong> Campos
							obrigat&oacute;rios</td>
						</tr>
						<tr>

							<td align="right" colspan="2">
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td align="right" colspan="3">
									<input name="Button3222" type="button"
										class="bottonRightCol" value="Voltar"
										onClick="javascript:voltar();" />
									<input name="Button" type="button"
										class="bottonRightCol" value="Confirmar"
										onClick="javascript:confirmar();" />
									</td>
								<tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
</table>
</html:form>
</body>
</html>
