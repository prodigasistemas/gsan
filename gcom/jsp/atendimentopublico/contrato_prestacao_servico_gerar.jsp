<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.cadastro.cliente.ClienteImovel"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="GerarContratoPrestacaoServicoActionForm"  />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
<!-- Begin



	function limparForm() {
	
		var form = document.forms[0];
		
		form.idImovel.value = "";
		form.idCliente.value = "";
		form.inscricao.value = "";
		form.action = 'exibirGerarContratoPrestacaoServicoAction.do?menu=sim';
	
		form.submit();
	}
	
	function limparImovelTecla() {
		var form = document.forms[0];
	
		form.inscricao.value = "";
	}
	
	
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
		if (tipoConsulta == 'imovel') {
			form.inscricao.style.color = "#000000";
			form.idImovel.value = codigoRegistro;
			form.inscricao.value = descricaoRegistro;
		}
		
	}

	function validarForm(form){
				
		if(testarCampoValorZero(document.GerarContratoPrestacaoServicoActionForm.idImovel, 'Matrícula')
			&&(CheckboxNaoVazio(document.forms[0].idCliente))) {
			if(validateGerarContratoPrestacaoServicoActionForm(form)){			
   				form.submit();			
			}
		}	
	}
	
	function CheckboxNaoVazio(campo){
	  form = document.forms[0];
	  retorno = false;
	  count = 0;
		
	  for(indice = 0; indice < form.elements.length; indice++){
		if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
			count = count + 1;
			retorno = true;
			//break;
		}
	  }
		
	  if (!retorno){
		alert('Informe o cliente desejado.');
	  }else if(count > 1){
	    alert('Selecione apenas um cliente para geração do contrato.');
	    retorno = false;
	  }
	  return retorno;
	} 

-->
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/gerarRelatorioContratoPrestacaoServicoAction"
	name="GerarContratoPrestacaoServicoActionForm"
	type="gcom.gui.atendimentopublico.GerarContratoPrestacaoServicoActionForm"
	method="post" onsubmit="return validateGerarContratoPrestacaoServicoActionForm(this);">

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
					<td class="parabg">Gerar Contrato de Prestação de Serviço</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para gerar o contrato de prestação de serviço,
					informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="30%"><strong>Matrícula do Imóvel:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="idImovel" size="9" maxlength="9"
						onkeyup="validaEnter(event, 'exibirGerarContratoPrestacaoServicoAction.do', 'idImovel');"
						onkeypress="limparImovelTecla();" /> <a
						href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 275, 480, '',document.forms[0].idImovel);">
					<img width="23" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						style="cursor:hand;" alt="Pesquisar" border="0" /></a> <logic:present
						name="matriculaInexistente" scope="request">
						<html:text property="inscricao" size="35" maxlength="35"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="matriculaInexistente"
						scope="request">
						<html:text property="inscricao" size="35" maxlength="35"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparForm();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a></td>
				</tr>

				<!--<tr>
					<td width="30%"><strong>Cliente Responsável:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="idCliente" size="9" maxlength="9"
						onkeyup="validaEnter(event, 'exibirGerarContratoPrestacaoServicoAction.do', 'idCliente');"
						onkeypress="limparClienteTecla();" /> <a
						href="javascript:abrirPopup('exibirPesquisarClienteAction.do', 'cliente', null, null, 275, 480, '',document.forms[0].idCliente);">
					<img width="23" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						style="cursor:hand;" alt="Pesquisar" border="0" /></a> <logic:present
						name="clienteInexistente" scope="request">
						<html:text property="nomeCliente" size="35" maxlength="35"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="clienteInexistente"
						scope="request">
						<html:text property="nomeCliente" size="35" maxlength="35"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparClienteForm();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a></td>
				</tr>-->
				
				
				
				<tr>
					<td colspan="5">
					<table width="100%" border="0">
						<tr>
							<td colspan="5">
							<table width="100%" align="center" bgcolor="#90c7fc" border="0">
								<tr bordercolor="#79bbfd">
									<td colspan="5" align="center" bgcolor="#79bbfd"><strong>Clientes</strong></td>
								</tr>
								<tr bordercolor="#000000">
									<td width="12%" bgcolor="#90c7fc" align="center">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Selecionar</strong></font></div>
									</td>
									<td width="28%" bgcolor="#90c7fc" align="center">
									<div class="style9"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Nome do
									Cliente</strong> </font></div>
									</td>
									<td width="17%" bgcolor="#90c7fc" align="center">
									<div class="style9"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo da
									Rela&ccedil;&atilde;o</strong> </font></div>
									</td>
									<td width="19%" bgcolor="#90c7fc" align="center">
									<div class="style9"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>RG</strong></font></div>
									</td>
									
									<td width="20%" bgcolor="#90c7fc" align="center"><font
										color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>CPF/CNPJ</strong>
									</font></td>
								</tr>
								<tr>
									<td width="100%" colspan="5">
									<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%" align="left" bgcolor="#99CCFF">
										<!--corpo da segunda tabela-->
										<%int cont = 0;%>
										<logic:notEmpty name="imovelClientes">
											<logic:iterate name="imovelClientes" id="imovelCliente"
												type="ClienteImovel">
												<%cont = cont + 1;
												if (cont % 2 == 0) {%>
												<tr bgcolor="#cbe5fe">
													<%} else {%>
												<tr bgcolor="#FFFFFF">
													<%}%>
													<td width="12%" bordercolor="#90c7fc" align="left">
													<div align="center"><input type="checkbox"
													name="idCliente"
													value="<bean:write name="imovelCliente" property="cliente.id" />"></div>
													</td>
													<td width="28%" bordercolor="#90c7fc" align="left">
													<div class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <logic:present
														name="imovelCliente" property="cliente">
														<a
															href="javascript:abrirPopup('exibirConsultarClienteAction.do?desabilitarPesquisaCliente=SIM&codigoCliente='+<bean:write name="imovelCliente" property="cliente.id" />, 500, 800);">
														<bean:write name="imovelCliente" property="cliente.nome" />
														</a>
													</logic:present> </font></div>
													</td>
													<td width="17%" align="left">
													<div class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <logic:present
														name="imovelCliente" property="clienteRelacaoTipo">
														<bean:write name="imovelCliente"
															property="clienteRelacaoTipo.descricao" />
													</logic:present> </font></div>
													</td>
													<td width="20%" align="left"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
														name="imovelCliente" property="cliente.rg">
														<bean:write name="imovelCliente"
															property="cliente.rg" />
													</logic:notEmpty> <logic:empty name="imovelCliente"
														property="cliente.rg">
														&nbsp;
													</logic:empty> </font></td>

													<td width="20%" align="left"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
														name="imovelCliente" property="cliente.cpf">
														<bean:write name="imovelCliente"
															property="cliente.cpfFormatado" />
													</logic:notEmpty> <logic:notEmpty name="imovelCliente"
														property="cliente.cnpj">
														<bean:write name="imovelCliente"
															property="cliente.cnpjFormatado" />
													</logic:notEmpty> </font></td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
									</table>
									</div>
									</td>
								</tr>
							</table>
							</td>
						</tr>
				</table>
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>
					<td><input type="button" name="ButtonReset" class="bottonRightCol"
						value="Limpar" onclick="javascript:limparForm();">
					<td colspan="2" align="right"><gsan:controleAcessoBotao
						name="Button" value="Gerar"
						onclick="javascript:validarForm(document.forms[0]);"
						url="gerarRelatorioContratoPrestacaoServicoAction.do" /> <%-- <input type="button" name="Button" class="bottonRightCol" value="Filtrar" onClick="javascript:validarForm(document.forms[0]);" /> --%>
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
