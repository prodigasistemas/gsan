<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.micromedicao.InformarItensContratoServicoHelper"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="InserirCobrancaGrupoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">

	function validarForm(){
		var form = document.forms[0];
		if (validateInserirCobrancaGrupoActionForm(form)){
				submeterFormPadrao(form);
			}	
	}
	
	function limparForm() {
		var form = document.InserirCobrancaGrupoActionForm;
		form.descricao.value = "";	
		form.descricaoAbreviada.value = "";	
		form.anoMesReferencia.value = "";
		form.emailResponsavel.value = "";
		form.indicadorExecucaoAutomatica[0].checked = false;
		form.indicadorExecucaoAutomatica[1].checked = true;
	}
		
	function selecionarContratos(){
	      var form = document.InserirCobrancaGrupoActionForm;
	      form.action = 'exibirInserirCobrancaGrupoAction.do';
	      form.submit();
	}

	function verificaContrato() {
		 var form = document.InserirCobrancaGrupoActionForm;
		 if (form.indicadorExecucaoAutomatica[0].checked == true && (form.empresa.value == '-1'
		 		|| form.idNumeroContrato == null || !campoSelecionado(form.idNumeroContrato))) {
		 	alert("Execução automática só ocorrerá com contrato de cobrança.");
		 }
	}
	
	function setarRadioPadrao() {
		var form = document.InserirCobrancaGrupoActionForm;
		if (form.indicadorExecucaoAutomatica[1].checked == ''
			&& form.indicadorExecucaoAutomatica[0].checked == '') {
			form.indicadorExecucaoAutomatica[1].checked = true;
		}
	}
	
	function campoSelecionado(idNumeroContrato) {
		var i;
		
		for (i = 0; i < idNumeroContrato.length; i++){
			if (idNumeroContrato[i].checked == true) {
				return true;
			}
		}
		
		return false;
	}

</script>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('descricao');setarRadioPadrao();">

<html:form action="/inserirCobrancaGrupoAction.do"
	name="InserirCobrancaGrupoActionForm"
	type="gcom.gui.cobranca.InserirCobrancaGrupoActionForm"
	method="post"
	onsubmit="return validateInserirCobrancaGrupoActionForm(this);">

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
			<td width="625" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Inserir Grupo de Cobrança</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->

			<table width="100%" border="0">
				<tr>

					<td colspan="2">Para adicionar um grupo de cobrança, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="30%"><strong>Descrição: <font color="#FF0000">*</font></strong></td>
					<td colspan="2" width="70%"><html:text property="descricao" maxlength="25"
						size="27" />
						<br>
					</td>
				</tr>
				
				<tr>
					<td width="30%"><strong>Descrição Abreviada: <font color="#FF0000">*</font></strong></td>
					<td colspan="2" width="70%"><html:text property="descricaoAbreviada" maxlength="3"
						size="3" />
						<br>
					</td>
				</tr>
				
				<tr>
					<td width="30%"><strong>Mês/Ano de Refer&ecirc;ncia: <font color="#FF0000">*</font></strong></td>
					<td width="70%"><html:text maxlength="7" property="anoMesReferencia" size="7"
						onkeyup="mascaraAnoMes(this, event);" 
					    onkeypress="return isCampoNumerico(event);"/> &nbsp; mm/aaaa</td>
				</tr>

				<tr>
					<td colspan="2"><hr></td>
				</tr>
				
				<tr>
				
				<tr>
					<td width="30%"><strong>E-mail do Funcionário Responsável:</strong></td>
					<td colspan="2" width="70%">
						<html:text property="emailResponsavel" maxlength="50" size="27" style="text-transform: none;"/>
					</td>
				</tr>
				
				<tr>
					<td width="30%"><strong>Execução Automática: <font color="#FF0000">*</font></strong></td>
					<td colspan="2" width="70%">
						<strong> <html:radio property="indicadorExecucaoAutomatica" value="1" onchange="javascript:verificaContrato();"/>
						Sim <html:radio property="indicadorExecucaoAutomatica" value="2" onchange="javascript:verificaContrato();"/>
						N&atilde;o </strong>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>Empresa: </strong></td>
					<html:hidden property="idContratoCobranca"/>
					<td width="70%">
					<logic:notPresent name="ContratoCobrancaPesquisarActionForm" property="idEmpresaRecebido">
					<html:select property="empresa" size="1" onchange="selecionarContratos();">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="collectionEmpresa" labelProperty="descricaoAbreviada" property="id" />
					</html:select>
					</logic:notPresent>
					
					<logic:present name="ContratoCobrancaPesquisarActionForm" property="idEmpresaRecebido">
					<html:select property="empresa" size="1" disabled="true">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="collectionEmpresa" labelProperty="descricaoAbreviada" property="id" />
					</html:select>
					</logic:present>
					
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>Contrato de Cobran&ccedil;a: </strong></td>
					<td width="70%">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td>
							<table width="60%" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td width="10%">
										<strong>Selecionar</strong>
									</td>
									<td width="50%">
										<strong>Número do Contrato</strong>
									</td>
								</tr>
							</table>
							</td>
						</tr>
						
						<tr>
							<td>
								<%String cor = "#FFFFFF";%> 
								<logic:present name="collectionContrato" scope="session">
			
									<div style="width: 60%; height: 80; overflow: auto;">
			
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td><%cor = "#cbe5fe";%>
			
											<table width="100%" align="center" bgcolor="#99CCFF">
												<logic:iterate name="collectionContrato" 
													id="collectionContrato"
													type="InformarItensContratoServicoHelper">
													<c:set var="count" value="${count+1}" />
													<c:choose>
														<c:when test="${count%2 == '1'}">
															<tr bgcolor="#FFFFFF">
														</c:when>
														<c:otherwise>
															<tr bgcolor="#cbe5fe">
														</c:otherwise>
													</c:choose>
														
													<td width="10%">
														<div align="center">
															<label > 
																<html:radio
																	value="${count}"
																	property="idNumeroContrato"
																	onclick="javascript:reloadContrato('${count}');" /> 
															</label> 
														</div>
													</td>
													<td width="50%">
														<div align="left">
															<div align="left">
																<bean:write name="collectionContrato" property="contratoEmpresaServico.descricaoContrato" />
															</div>
														</div>
													</td>
												</logic:iterate>
											</table>
											</td>
										</tr>
									</table>
									</div>
								</logic:present>
							</td>
						</tr>
					</table>
					
					</td>
				</tr>

				<tr>
					<td align="center" colspan="2"><font color="#FF0000">*</font> Campo Obrigatório</td>
				</tr>
				
				<tr>
					<td colspan="2"><hr></td>
				</tr>
				<tr>
					<td colspan="2"><input name="Button" type="button"
						class="bottonRightCol" value="Limpar" align="left"
						
						onClick="window.location.href='/gsan/exibirInserirCobrancaGrupoAction.do?menu=sim'" >
					<input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
					<td width="53" height="24" align="right"><input type="button"
						name="Button2" class="bottonRightCol" value="Inserir"
						onClick="javascript:validarForm(document.forms[0])" /></td>
					<td width="12">&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>
</html:form>
</body>
</html:html>
