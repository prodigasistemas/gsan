<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.cadastro.empresa.EmpresaCobrancaFaixa" %>


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
	formName="AtualizarEmpresaActionForm" />
	
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
	
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<SCRIPT LANGUAGE="JavaScript">
<!--

function validarForm(formulario){
	if(validateAtualizarEmpresaActionForm(formulario)){
 		if(formulario.indicadorEmpresaCobranca[0].checked){
				if (formulario.dataInicioContratoCobranca.value == "" && formulario.percentualPagamento.value == ""  
					&& (formulario.percentualDaFaixaInformado.value != null
						&& formulario.percentualDaFaixaInformado.value != 'sim'
						&& formulario.percentualDaFaixaInformado.value != 'SIM')){
					alert('Informe Data de Início do Contrato.\nInforme Percentual do Pagamento das Contas Cobradas.');	
				}else if(formulario.dataInicioContratoCobranca.value == ""){
					alert('Informe Data do Início do Contrato.');
				}else if (formulario.percentualPagamento.value == ""
					&& (formulario.percentualDaFaixaInformado.value != null
						&& formulario.percentualDaFaixaInformado.value != 'sim'
						&& formulario.percentualDaFaixaInformado.value != 'SIM')){
					alert('Informe Percentual do Pagamento das Contas Cobradas.');
				} else{
					submeterFormPadrao(formulario);
				}				
			}else{
 			submeterFormPadrao(formulario);
 			}				
			
	}
	
 }

function limparForm() {
		var form = document.AtualizarEmpresaActionForm;
		form.descricao.value = "";
		form.descricaoAbreviada.value = "";
		form.email.value = "";
		form.percentualDaFaixa.value = "";
		form.quantidadeMinimaContas.value = "";
		
		form.action = 'exibirAtualizarEmpresaAction.do?limparFaixa=sim';
		form.submit();
	}
	
function bloqueiaDadosEmpresaCobranca(){
		var form = document.forms[0];
		
		if(form.indicadorEmpresaCobranca[0].checked){
		
			form.dataInicioContratoCobranca.style.color = "#000000";
			form.dataInicioContratoCobranca.readOnly = false;
			form.dataInicioContratoCobranca.style.backgroundColor = '';
			
			form.dataFimContratoCobranca.style.color = "#000000";
			form.dataFimContratoCobranca.readOnly = false;
			form.dataFimContratoCobranca.style.backgroundColor = '';
			
			if (form.percentualDaFaixaInformado.value != null
					&& form.percentualDaFaixaInformado.value != 'sim'
					&& form.percentualDaFaixaInformado.value != 'SIM') {
				form.percentualPagamento.style.color = "#000000";
				form.percentualPagamento.readOnly = false;
				form.percentualPagamento.style.backgroundColor = '';
			}
			
			form.percentualDaFaixa.style.color = "#000000";
			form.percentualDaFaixa.readOnly = false;
			form.percentualDaFaixa.style.backgroundColor = '';
			
			form.quantidadeMinimaContas.style.color = "#000000";
			form.quantidadeMinimaContas.readOnly = false;
			form.quantidadeMinimaContas.style.backgroundColor = '';
			
			form.adicionarFaixa.disabled = false;
			
	 	 }else{
	 	 
	 	 	form.dataInicioContratoCobranca.value = "";
			form.dataInicioContratoCobranca.style.color = "#000000";
			form.dataInicioContratoCobranca.readOnly = true;
			form.dataInicioContratoCobranca.style.backgroundColor = '#EFEFEF';
			
			form.dataFimContratoCobranca.value = "";
			form.dataFimContratoCobranca.style.color = "#000000";
			form.dataFimContratoCobranca.readOnly = true;
			form.dataFimContratoCobranca.style.backgroundColor = '#EFEFEF';
	 	 
			form.percentualPagamento.value = "";
			form.percentualPagamento.style.color = "#000000";
			form.percentualPagamento.readOnly = true;
			form.percentualPagamento.style.backgroundColor = '#EFEFEF';
	 	 
			form.percentualDaFaixa.value = "";
			form.percentualDaFaixa.style.color = "#000000";
			form.percentualDaFaixa.readOnly = true;
			form.percentualDaFaixa.style.backgroundColor = '#EFEFEF';
	 	 
			form.quantidadeMinimaContas.value = "";
			form.quantidadeMinimaContas.style.color = "#000000";
			form.quantidadeMinimaContas.readOnly = true;
			form.quantidadeMinimaContas.style.backgroundColor = '#EFEFEF';
			
			form.adicionarFaixa.disabled = true;
	 	 }
	} 
	
	function adicionarEmpresaContratoCobranca(){
		var form = document.forms[0];
		
		if (form.percentualDaFaixa.value == '' || !validaPercentual(form.percentualDaFaixa)) {
			alert('Valor informado do percentual está inválido.');
		} else {
			form.action = 'exibirAtualizarEmpresaAction.do?adicionarFaixa=sim';
			form.submit();
		}
	}

	function validaPercentual(percentual){
		if (obterNumerosSemVirgulaEPonto(percentual.value) > 10000
			|| obterNumerosSemVirgulaEPonto(percentual.value) <= 0 ) {
			return false;
		}
		
		return true;
	}
	
	
	function removerEmpresaCobrancaFaixa(obj){
		var form = document.forms[0];
		if (confirm('Confirma Remoção?')) {
			form.action = 'exibirAtualizarEmpresaAction.do?removerEmpresaCobrancaFaixa='+obj;
			form.submit();
		}
	}
	
	function bloqueiaCampos(desbloqueia){
		var form = document.forms[0];
		
		if (form.percentualPagamento.value != null && form.percentualPagamento.value != '') {
			
			form.adicionarFaixa.disabled = true;
			form.quantidadeMinimaContas.value = "";
			form.quantidadeMinimaContas.readOnly = true;
			form.quantidadeMinimaContas.style.color = "#000000";
			form.quantidadeMinimaContas.style.backgroundColor = '#EFEFEF';
			form.percentualDaFaixa.value = "";
			form.percentualDaFaixa.readOnly = true;
			form.percentualDaFaixa.style.color = "#000000";
			form.percentualDaFaixa.style.backgroundColor = '#EFEFEF';
			
			form.percentualPagamento.readOnly = false;
			form.percentualPagamento.style.color = "#000000";
			form.percentualPagamento.style.backgroundColor = '';
			
		} else if ((form.quantidadeMinimaContas.value != null && form.quantidadeMinimaContas.value != '')
			|| (form.percentualDaFaixa.value != null && form.percentualDaFaixa.value != '')
			|| (form.percentualDaFaixaInformado.value != null
					&& (form.percentualDaFaixaInformado.value == 'sim'
						|| form.percentualDaFaixaInformado.value == 'SIM'))) {
			
			form.percentualPagamento.value = "";
			form.percentualPagamento.readOnly = true;
			form.percentualPagamento.style.color = "#000000";
			form.percentualPagamento.style.backgroundColor = '#EFEFEF';
			
			form.adicionarFaixa.disabled = false;
			form.quantidadeMinimaContas.readOnly = false;
			form.quantidadeMinimaContas.style.color = "#000000";
			form.quantidadeMinimaContas.style.backgroundColor = '';
			form.percentualDaFaixa.readOnly = false;
			form.percentualDaFaixa.style.color = "#000000";
			form.percentualDaFaixa.style.backgroundColor = '';
			
		} else if (desbloqueia != null && desbloqueia == 'sim') {
			
			form.percentualPagamento.readOnly = false;
			form.percentualPagamento.style.color = "#000000";
			form.percentualPagamento.style.backgroundColor = '';
			
			form.adicionarFaixa.disabled = false;
			form.quantidadeMinimaContas.readOnly = false;
			form.quantidadeMinimaContas.style.color = "#000000";
			form.quantidadeMinimaContas.style.backgroundColor = '';
			form.percentualDaFaixa.readOnly = false;
			form.percentualDaFaixa.style.color = "#000000";
			form.percentualDaFaixa.style.backgroundColor = '';
		}
	}
	
	function limparFaixa(){
		var form = document.forms[0];
		
		form.action = 'exibirAtualizarEmpresaAction.do?limparFaixa=sim';
		form.submit();
	}
	
	function desfazer(){
		var form = document.forms[0];
		
		form.action = 'exibirAtualizarEmpresaAction.do';
		form.submit();
	}
	
//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('descricao');bloqueiaDadosEmpresaCobranca();bloqueiaCampos('nao');">

<html:form action="/atualizarEmpresaAction.do" method="post" >

	<INPUT TYPE="hidden" name="removerEmpresa">
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

			<html:hidden property="percentualDaFaixaInformado"/>
			
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
					<td class="parabg">Atualizar Empresa</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para atualizar a empresa informe os
					dados abaixo:</td>
				<tr>
					<td><strong>C&oacute;digo:</strong></td>
					<td><html:hidden property="id" /> 
					<bean:write	name="AtualizarEmpresaActionForm" property="id" />
					</td>
				</tr>

				<tr>
					<td><strong>Nome: <font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricao" size="50" maxlength="50" tabindex="1"/> </span></td>
				</tr>
				
				<tr>
					<td><strong>Nome Abreviado:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricaoAbreviada" size="10" maxlength="10" tabindex="2"/> </span></td>
				</tr>
				<tr>
					<td><strong>E-mail:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="email" size="60" maxlength="80" tabindex="3"/> </span></td>
				</tr>
				<tr>
					<td><strong>Empresa Principal?<font color="#FF0000">*</font></strong></td>
					<td><html:radio property="indicadorEmpresaPrincipal" tabindex="4" value="1" ><strong>Sim</strong></html:radio>
					<html:radio property="indicadorEmpresaPrincipal" tabindex="5" value="2" ><strong>Não</strong></html:radio>
				</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Indicador Empresa Cobrança:</strong></td>
					<td><html:radio property="indicadorEmpresaCobranca" tabindex="6"
						value="1" onclick="javascript:bloqueiaDadosEmpresaCobranca();" /><strong>Sim</strong> <html:radio
						property="indicadorEmpresaCobranca" tabindex="7" value="2"
						onclick="javascript:bloqueiaDadosEmpresaCobranca();limparFaixa();" /><strong>Não</strong></td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td><strong>Indicador Leitura? <font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorLeitura" 
						value="1" tabindex="8" /><strong>Sim</strong> <html:radio
						property="indicadorLeitura" value="2" tabindex="9" /><strong>Não</strong>
					</td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td><strong> Data do Início do Contrato: </strong></td>

					<td colspan="2"><strong> <html:text
						property="dataInicioContratoCobranca" size="11" maxlength="10"
						tabindex="8" onkeyup="mascaraData(this, event)" onchange="bloqueiaCampos('sim');"
						onkeypress="return isCampoNumerico(event);"/> <a
						href="javascript:abrirCalendario('AtualizarEmpresaActionForm', 'dataInicioContratoCobranca');">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="16" height="15" border="0" alt="Exibir Calendário"
						tabindex="4" /></a> (dd/mm/aaaa) </strong></td>
				</tr>
				
				
				<tr>
					<td><strong> Data do Final do Contrato:  </strong></td>

					<td colspan="2"><strong> <html:text
						property="dataFimContratoCobranca" size="11" maxlength="10"
						tabindex="9" onkeyup="mascaraData(this, event)"  onchange="bloqueiaCampos('sim');"
						onkeypress="return isCampoNumerico(event);"/> <a
						href="javascript:abrirCalendario('AtualizarEmpresaActionForm', 'dataFimContratoCobranca');">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="16" height="15" border="0" alt="Exibir Calendário"
						tabindex="4" /></a> (dd/mm/aaaa) </strong></td>
				</tr>
				
				<logic:present name="colecaoEmpresaCobrancaFaixa">
					<tr>
						<td><strong>Percentual do Pagamento das Contas Cobradas:</strong></td>
						<td colspan="2"><span class="style2"> <html:text 
							property="percentualPagamento" size="7" maxlength="7"
							onkeyup="formataValorMonetario(this, 5);"  onchange="bloqueiaCampos('sim');"
							onkeypress="return isCampoNumerico(event);"
							tabindex="10" readonly="true" style="background-color:#EFEFEF;" /> </span></td>
						
					</tr>
				</logic:present>
				
				<logic:notPresent name="colecaoEmpresaCobrancaFaixa">
					<tr>
						<td><strong>Percentual do Pagamento das Contas Cobradas:</strong></td>
						<td colspan="2"><span class="style2"> <html:text
							property="percentualPagamento" size="7" maxlength="7"
							onkeyup="formataValorMonetario(this, 5);"  onchange="bloqueiaCampos('sim');"
							onkeypress="return isCampoNumerico(event);"
							tabindex="10"/> </span></td>
						
					</tr>
				</logic:notPresent>
				
				<tr>
					<td><strong>Indicador de Uso<font color="#FF0000">*</font></strong></td>
					<td><html:radio property="indicadorUso" tabindex="11" value="1"><strong>Ativo</strong></html:radio>
					<html:radio property="indicadorUso" tabindex="12" value="2" ><strong>Inativo</strong></html:radio>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>Quantidade mínima de contas:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="quantidadeMinimaContas" size="8" maxlength="8" onchange="bloqueiaCampos('sim');"
						onkeypress="return isCampoNumerico(event);" tabindex="13"/> </span></td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td><strong>Percentual da Faixa:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="percentualDaFaixa" size="7" maxlength="7"
						onkeypress="return isCampoNumerico(event);" onchange="bloqueiaCampos('sim');"
						onkeyup="formataValorMonetario(this, 5);" tabindex="14"/> </span></td>
					<td>&nbsp;</td>
					<td>
					
						<input type="button" name="adicionarFaixa"
							class="bottonRightCol" value="Adicionar"
							onClick="javascript:adicionarEmpresaContratoCobranca();"
							tabindex="7">
							
					</td>
				</tr>
			</table>
			<table width="100%" cellpadding="0" cellspacing="0">
			
							<tr bordercolor="#000000">
								<td bgcolor="#90c7fc" align="center" width="10%">
								<div align="center"><strong>Remover</strong></div>
								</td>
								<td bgcolor="#90c7fc" width="45%"><strong>Quantidade mínima de contas</strong></td>
								<td bgcolor="#90c7fc" width="45%"><strong>Percentual da Faixa</strong></td>
							</tr>
							<logic:present name="colecaoEmpresaCobrancaFaixa">
								<tr>
									<td colspan="3">
			
									<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%" bgcolor="#99CCFF">
										<tr>
										<logic:iterate name="colecaoEmpresaCobrancaFaixa"
											id="empresaCobrancaFaixa" type="EmpresaCobrancaFaixa">
											<c:set var="count2" value="${count2+1}" />
											<c:choose>
												<c:when test="${count2%2 == '1'}">
													<tr bgcolor="#FFFFFF">
												</c:when>
												<c:otherwise>
													<tr bgcolor="#cbe5fe">
												</c:otherwise>
											</c:choose>
						
											<td width="10%">
												<div align="center"><font color="#333333"> <img width="14"
													height="14" border="0"
													src="<bean:message key="caminho.imagens"/>Error.gif"
												 	onclick="removerEmpresaCobrancaFaixa('${count2}')" />
												</font></div>
											</td>
											<td width="45%">
												<bean:write name="empresaCobrancaFaixa"
													property="numeroMinimoContasFaixa" />
											</td>
											<td width="45%">
												<bean:write name="empresaCobrancaFaixa"
													property="percentualFaixa" formatKey="money.format"/>
											</td>
										</logic:iterate>
										</tr>
			
									</table>
									</div>
									</td>
								</tr>
							</logic:present>
					</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">

				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>

				<tr>
					<td width="40%" align="left"><input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Voltar"
						onClick="window.history.go(-1)"> <input type="button"
						name="ButtonReset" class="bottonRightCol" value="Desfazer"
						onClick="javascript:desfazer();"> <input type="button"
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

