<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="gcom.cadastro.cliente.ClienteFone"%>
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ConsultarClienteActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
<!-- Begin
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.ConsultarClienteActionForm;

    if (tipoConsulta == 'cliente') {
      form.idMunicipio.value = codigoRegistro;
      form.nomeMunicipio.value = descricaoRegistro;
      form.nomeMunicipio.style.color = "#000000";

    }
}

function limparForm(){
   	var form = document.forms[0];
	form.action = 'exibirConsultarClienteAction.do?limparForm=OK'
	form.submit();
}
-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form action="/exibirConsultarClienteAction.do"
	name="ConsultarClienteActionForm"
	type="gcom.gui.cadastro.cliente.ConsultarClienteActionForm"
	method="post"
	onsubmit="return validateConsultarClienteActionForm(this);">

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td class="centercoltext" valign="top"><!--Início Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table align="center" border="0" cellpadding="0" cellspacing="0"
				width="100%">

				<tr>
					<td width="11"><img src="imagens/parahead_left.gif" border="0"></td>
					<td class="parabg">Consultar Dados do Cliente</td>
					<td valign="top" width="11"><img src="imagens/parahead_right.gif"
						border="0"></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table border="0" width="100%">

				<tr>
					<td colspan="4">
					<table align="center" bgcolor="#99ccff" border="0" width="100%">

						<tr>
							<td align="center"><strong>Dados do Cliente</strong></td>
						</tr>

						<logic:notPresent name="desabilitarPesquisaCliente">
							<tr bgcolor="#cbe5fe">
								<td align="center" width="100%">
								<table border="0" width="100%">
	
									<tr>
										<td bordercolor="#000000" width="25%"><strong>Cliente:<font
											color="#FF0000">*</font></strong></td>
										<td width="75%" colspan="3"><html:text property="codigoCliente"
											maxlength="9" size="9"
											onkeypress="javascript:return validaEnter(event, 'exibirConsultarClienteAction.do', 'codigoCliente');" />
										<a
											href="javascript:redirecionarSubmit('exibirPesquisarClienteAction.do?caminhoRetornoTelaPesquisaCliente=exibirConsultarClienteAction');">
										<img width="23" height="21"
											src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											border="0" /></a> <logic:present
											name="codigoClienteNaoEncontrado" scope="request">
											<html:text property="nomeCliente" size="40" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
	
										</logic:present> <logic:notPresent
											name="codigoClienteNaoEncontrado" scope="request">
											<logic:present name="valornomeCliente" scope="request">
												<html:text property="nomeCliente" size="40" readonly="true"
													style="background-color:#EFEFEF; border:0; color: #000000" />
											</logic:present>
											<logic:notPresent name="valornomeCliente" scope="request">
												<html:text property="nomeCliente" size="40" readonly="true"
													style="background-color:#EFEFEF; border:0; color: #000000" />
											</logic:notPresent>
										</logic:notPresent> <a href="javascript:limparForm();"> <img
											src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" title="Apagar" /></a></td>
	
									</tr>
									<tr>
										<td height="10">
										<div class="style9"><strong>Nome Abreviado:</strong></div>
										</td>
										<td><html:text property="nomeAbreviado" size="25"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
										</td>
										<td width="146"><strong>Dia&nbsp;Vencimento&nbsp;Contas:</strong></td>
										<td width="123"><html:text property="dataVencimentoContas"
											size="40" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" /></td>
									</tr>
	
								</table>
								</td>
							</tr>
						</logic:notPresent>
						<logic:present name="desabilitarPesquisaCliente">
							<tr bgcolor="#cbe5fe">
								<td align="center" width="100%">
								<table border="0" width="100%">
									<tr>
										<td width="100">
										<div class="style9"><strong>Código do Cliente:</strong></div>
										</td>
										<td><html:text property="codigoCliente" disabled="true"
											maxlength="9" size="9" style="background-color:#EFEFEF; border:0; color: #000000" />
										</td>
										<td width="146"><strong>Nome:</strong></td>
										<td width="123"><html:text style="background-color:#EFEFEF; border:0; color: #000000" property="nomeCliente" size="40" readonly="true" disabled="true"
												style="background-color:#EFEFEF; border:0; color: #000000" /></td>
									</tr>
									<tr>
										<td width="100">
										<div class="style9"><strong>Nome Abreviado:</strong></div>
										</td>
										<td><html:text property="nomeAbreviado" size="25"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
										</td>
										<td width="146"><strong>Dia&nbsp;Vencimento&nbsp;Contas:</strong></td>
										<td width="123"><html:text property="dataVencimentoContas"
											size="40" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" /></td>
									</tr>
								</table>
								</td>
							</tr>

						
						</logic:present>


					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<table align="center" bgcolor="#99ccff" border="0" width="100%">

						<tr>
							<td align="center">
							<div class="style9"><strong>Tipo de Cliente </strong></div>
							</td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td align="center"><html:text property="tipoCliente" size="100"
								readonly="true"
								style="background-color:#cbe5fe; border:0; color: #000000" /></td>
						</tr>
						<tr>
							<td>
							<table border="0" width="100%">

								<tr>
									<td height="10">
									<div class="style9"><strong>E-mail:</strong></div>
									</td>
									<td><html:text property="email" size="40" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" /></td>
								</tr>
								<tr>
									<td colspan="2" rowspan="1" height="10"><strong>Executa
									a&ccedil;&otilde;es de cobran&ccedil;a para os im&oacute;veis
									relacionados como respons&aacute;vel: <html:text
										property="indicaorExecucao" size="10" readonly="true"
										style="background-color:#cbe5fe; border:0; color: #000000" /></strong></td>
								</tr>

							</table>
							</td>
						</tr>

					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<table style="width: 100%;" border="0">

						<logic:equal name="indicadorTipoCliente" value="1" scope="request">
							<tr>
								<td align="left" height="10" width="20%">
								<div class="style9"><strong>CPF:</strong></div>
								</td>
								<td align="left" width="30%">
								<div class="style9"><html:text property="cpfCliente" size="20"
									readonly="true"
									style="background-color:#cbe5fe; border:0; color: #000000" />
								</div>
								</td>
								<td align="left" width="24%"><strong>RG:</strong></td>
								<td align="left" width="26%"><html:text property="rgCliente"
									size="20" readonly="true"
									style="background-color:#cbe5fe; border:0; color: #000000" />
								</td>
							</tr>
							<tr>
								<td align="left"><strong>Data Emiss&atilde;o do RG:</strong></td>
								<td align="left"><html:text property="dataEmissaoRGCliente"
									size="20" readonly="true"
									style="background-color:#cbe5fe; border:0; color: #000000" /></td>
								<td align="left"><strong>&Oacute;rg&atilde;o Emissor do RG/UF:</strong></td>
								<td align="left"><html:text property="orgaoEmissorRGCliente"
									size="20" readonly="true"
									style="background-color:#cbe5fe; border:0; color: #000000" /></td>
							</tr>
							<tr>
								<td height="10">
								<div class="style9"><strong>Data de Nascimento: </strong></div>
								</td>
								<td><html:text property="dataNascimentoCliente" size="20"
									readonly="true"
									style="background-color:#cbe5fe; border:0; color: #000000" /></td>
								<td align="left" height="10">
								<div class="style9"><strong>Sexo:</strong></div>
								</td>
								<td align="left">
								<div class="style9"><html:text property="sexoCliente" size="20"
									readonly="true"
									style="background-color:#cbe5fe; border:0; color: #000000" /></div>
								</td>
							</tr>
							<tr>
								<td align="left" height="10">
								<div class="style9"><strong>Profiss&atilde;o:&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp; &nbsp;</strong></div>
								</td>
								<td colspan="3" rowspan="1">
								<div class="style9"><html:text property="profissaoCliente"
									size="20" readonly="true"
									style="background-color:#cbe5fe; border:0; color: #000000" /></div>
								</td>
							</tr>
							<tr>
								<td align="left" height="10">
								<div class="style9"><strong>N&uacute;mero NIS:&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp; &nbsp;</strong></div>
								</td>
								<td colspan="3" rowspan="1">
								<div class="style9"><html:text property="clienteNumeroNIS"
									size="20" readonly="true"
									style="background-color:#cbe5fe; border:0; color: #000000" /></div>
								</td>
							</tr>
						</logic:equal>
						<logic:notEqual name="indicadorTipoCliente" value="1"
							scope="request">
							<tr>
								<td align="left" width="18%"><strong>CNPJ:</strong></td>
								<td colspan="2" align="left"><html:text property="cnpjCliente"
									size="20" readonly="true"
									style="background-color:#cbe5fe; border:0; color: #000000" /></td>
							</tr>
							<tr>
								<td align="left" height="10" width="18%">
								<div class="style9"><strong>Ramo de Atividade:</strong></div>
								</td>
								<td colspan="2" align="left"><html:text
									property="ramoAtividadeCliente" size="20" readonly="true"
									style="background-color:#cbe5fe; border:0; color: #000000" />
								</td>
							</tr>
						</logic:notEqual>

						<tr>
							<td style="text-align: justify;" colspan="4">&nbsp;<br>
							<table style="width: 100%;" bgcolor="#90c7fc" border="0"
								cellpadding="0" cellspacing="0">

								<tr>
									<td colspan="3" align="center" bgcolor="#79bbfd"><strong>Endere&ccedil;os
									do Cliente </strong></td>
								</tr>
								<tr bgcolor="#90c7fc">
									<td width="15%" align="center">
									<div class="style9"><font style="font-size: 9px;"
										color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
									<strong>Tipo</strong></font></div>
									</td>
									<td align="center" width="15%"><font style="font-size: 9px;"
										color="#000000" face="Verdana, Arial, Helvetica, sans-serif"><strong>Correspond&ecirc;ncia</strong></font></td>
									<td width="70%" align="center"><font style="font-size: 9px;"
										color="#000000" face="Verdana, Arial, Helvetica, sans-serif"><strong>Endere&ccedil;o</strong></font></td>
								</tr>


								<tr>
									<td colspan="3">
									<div style="width: 100%; height: 100%; overflow: auto;"><!--corpo da segunda tabela-->
									<%int cont = 0;%> <logic:notEmpty
										name="colecaoClienteEnderecosHelper">
										<table width="100%" border="0">

											<logic:iterate name="colecaoClienteEnderecosHelper"
												id="clienteEnderecosHelper">
												<%cont = cont + 1;
			if (cont % 2 == 0) {%>
												<tr bgcolor="#cbe5fe">
													<%} else {%>
												<tr bgcolor="#FFFFFF">
													<%}%>

													<td width="15%" align="left">${clienteEnderecosHelper.tipoEndereco}</td>
													<td width="15%" align="left">${clienteEnderecosHelper.indicadorEndereco}</td>
													<td width="70%" align="left">${clienteEnderecosHelper.enderecoClliente}</td>

												</tr>
											</logic:iterate>

										</table>
									</logic:notEmpty></div>
									</td>
								</tr>
							</table>
							<br>
							<table style="text-align: left; width: 100%;" border="0"
								bgcolor="#90c7fc" cellpadding="0" cellspacing="0">

								<tr>
									<td colspan="3" align="center" bgcolor="#79bbfd"><strong>Telefones
									do Cliente </strong></td>
								</tr>
								<tr bgcolor="#90c7fc">
									<td width="15%" align="center">
									<div class="style9"><font style="font-size: 9px;"
										color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
									<strong>Tipo</strong></font></div>
									</td>
									<td align="center" width="15%"><font style="font-size: 9px;"
										color="#000000" face="Verdana, Arial, Helvetica, sans-serif"><strong>Principal</strong></font></td>
									<td width="70%" align="center"><font style="font-size: 9px;"
										color="#000000" face="Verdana, Arial, Helvetica, sans-serif"><strong>Telefone</strong></font></td>
								</tr>


								<tr>
									<td colspan="3" width="100%">
									<div style="width: 100%; height: 100%; overflow: auto;"><!--corpo da segunda tabela-->
									<%cont = 0;%> <logic:notEmpty name="colecaoTelefones">
										<table width="100%" border="0">

											<logic:iterate name="colecaoTelefones" id="telefone">
												<%cont = cont + 1;
			if (cont % 2 == 0) {%>
												<tr bgcolor="#cbe5fe">
													<%} else {%>
												<tr bgcolor="#FFFFFF">
													<%}%>

													<td width="15%" align="left"><bean:write name="telefone"
														property="foneTipo.descricao" /></td>
													<td width="15%" align="left"><%=(((ClienteFone) telefone).getIndicadorTelefonePadrao()
							.shortValue() == 1) ? "SIM" : "NÃO"%></td>
													<td width="70%" align="center"><bean:write name="telefone"
														property="dddTelefone" /></td>
												</tr>
											</logic:iterate>

										</table>
									</logic:notEmpty></div>
									</td>
								</tr>



							</table>
							<br>
							</td>
						</tr>
					      <tr>
					      <td colspan="4" align="left">
					      <input name="Submit23" class="bottonRightCol" value="Fechar"
											type="button"
											onclick="javascript:window.close();">
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
</html:html>
