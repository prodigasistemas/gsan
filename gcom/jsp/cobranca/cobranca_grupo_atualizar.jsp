<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

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
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AtualizarCobrancaGrupoActionForm" />

<SCRIPT LANGUAGE="JavaScript">
<!--

function validarForm(formulario){
 
	if(validateAtualizarCobrancaGrupoActionForm(formulario)){
 		
 		submeterFormPadrao(formulario);
	}
 }

function limparForm() {
		var form = document.AtualizarCobrancaGrupoActionForm;
		form.descricao.value = "";
		form.descricaoAbreviada.value = "";
		form.anoMesReferencia.value = "";
}

		
function selecionarContratos(){
      var form = document.AtualizarCobrancaGrupoActionForm;
      if (form.empresa.value != '' && form.empresa.value != -1){
	      form.action = 'exibirAtualizarCobrancaGrupoAction.do?carregarContrato=SIM';
      } else {
          form.action = 'exibirAtualizarCobrancaGrupoAction.do?carregarContrato=NAO';
      }
      
      form.submit();
}

function verificaContrato() {
	 var form = document.AtualizarCobrancaGrupoActionForm;
	 if (form.indicadorExecucaoAutomatica[0].checked == true && (form.empresa.value == '-1'
	 		|| form.idNumeroContrato == null || !campoSelecionado(form.idNumeroContrato))) {
	 	alert("Execução automática só ocorrerá com contrato de cobrança.");
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


//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/atualizarCobrancaGrupoAction.do" method="post">

	<INPUT TYPE="hidden" name="removerCobrancaGrupo">
	<INPUT TYPE="hidden" name="limparCampos" id="limparCampos">


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

			<td width="625" valign="top" class="centercoltext">
			
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
					<td class="parabg">Atualizar Grupo de Cobrança</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para atualizar o grupo de cobrança, informe os
					dados abaixo:</td>
				<tr>
					<td><strong>C&oacute;digo:</strong></td>
					<td><html:hidden property="id" /> 
					<bean:write	name="AtualizarCobrancaGrupoActionForm" property="id" />
					</td>
				</tr>
				
				<tr>
					<td><strong>Descrição: <font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricao" size="27" maxlength="25" /> </span></td>
				</tr>
				
				<tr>
					<td><strong>Descri&ccedil;&atilde;o Abreviada: <font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricaoAbreviada" size="3" maxlength="3" /> </span></td>
				</tr>

				<tr>
					<td><strong>Mês/Ano: <font color="#FF0000">*</font></strong></td>
					<td><html:text maxlength="7" property="anoMesReferencia" size="7" 
									onkeyup="mascaraAnoMes(this, event);"
									onkeypress="mascaraAnoMes(this, event);" /> &nbsp; mm/aaaa
					</td>
				</tr>

				<tr>
					<td><strong>Indicador de Uso<font color="#FF0000">*</font></strong></td>
					<td><html:radio property="indicadorUso" tabindex="2" value="1"><strong>Ativo</strong></html:radio>
					<html:radio property="indicadorUso" tabindex="3" value="2" ><strong>Inativo</strong></html:radio>
					</td>
					<td>&nbsp;</td>
				</tr>
				


				<tr>
					<td colspan="2"><hr></td>
				</tr>
				
				<tr>
				
				<tr>
					<td width="30%"><strong>E-mail do Funcionário Responsável:</strong></td>
					<td colspan="2" width="70%"><html:text property="emailResponsavel" maxlength="50"
						size="27" style="text-transform: none;"/>
						<br>
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
																	value="${collectionContrato.contratoEmpresaServico.id}"
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
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				
				<tr>
					<td colspan="2"><hr></td>
				</tr>

				<tr>
					<td width="40%" align="left"><input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Voltar"
						onClick="window.history.go(-1)"> <input type="button"
						name="ButtonReset" class="bottonRightCol" value="Desfazer"
						onClick="(document.forms[0]).reset()"> <input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right"><input type="button"
						onClick="javascript:validarForm(document.forms[0]);"
						name="botaoAtualizar" class="bottonRightCol" value="Atualizar"></td>
				</tr>
				</table>
			
			
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>

