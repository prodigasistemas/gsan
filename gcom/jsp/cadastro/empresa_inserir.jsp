<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
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
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="InserirEmpresaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">

	function validarForm(){
		var form = document.forms[0];
		if (validateInserirEmpresaActionForm(form)){
		
			if(form.indicadorEmpresaCobranca[0].checked){
				
				if (form.dataInicioContratoCobranca.value == "" && form.percentualPagamento.value == ""
					&& (form.percentualDaFaixaInformado.value != null
						&& form.percentualDaFaixaInformado.value != 'sim'
						&& form.percentualDaFaixaInformado.value != 'SIM')){
				alert('Informe Data de Início do Contrato.\nInforme Percentual do Pagamento das Contas Cobradas.');	
				
				}else if(form.dataInicioContratoCobranca.value == ""){
					alert('Informe Data de Início do Contrato.');
				}
				else if (form.percentualPagamento.value == ""
					&& (form.percentualDaFaixaInformado.value != null
						&& form.percentualDaFaixaInformado.value != 'sim'
						&& form.percentualDaFaixaInformado.value != 'SIM')){
					alert('Informe Percentual do Pagamento das Contas Cobradas.');
				} else{
					submeterFormPadrao(form);
				}				
			}else if(!form.indicadorLeitura[0].checked){
				
				alert('Informe Indicador Leitura.');
			
			}else{
				submeterFormPadrao(form);
			}
		}	
	}
	
	//function required () {
	//	this.aa = new Array("dataInicioContratoCobranca", "Informe Data do Início do Contrato.", new Function ("varName", " return this[varName];"));
		//this.ab = new Array("percentualPagamento", "Informe Percentual do Pagamento das Contas Cobradas.", new Function ("varName", " return this[varName];"));
	//}

	function limparForm() {
		var form = document.InserirEmpresaActionForm;
		form.descricao.value = "";
		form.descricaoAbreviada.value = "";
		form.email.value = "";
		form.indicadorEmpresaPrincipal[1].checked = "2" ;
		form.indicadorEmpresaCobranca[1].checked = "2" ;
		form.dataInicioContratoCobranca.value = "";
		form.dataInicioContratoCobranca.style.color = "#000000";
		form.dataInicioContratoCobranca.style.background = "#EFEFEF";
		form.dataInicioContratoCobranca.disabled = true;
		form.percentualPagamento.value = "";
		form.percentualPagamento.style.color = "#000000";
		form.percentualPagamento.style.background = "#EFEFEF";
		form.percentualPagamento.disabled = true;
		form.indicadorLeitura[1].checked = "1" ;
		form.percentualDaFaixa.value = "";
		form.quantidadeMinimaContas.value = "";
		
		form.action = 'exibirInserirEmpresaAction.do?limparFaixa=sim';
		form.submit();
	}
	
	function bloqueiaDadosEmpresaCobranca(){
		var form = document.forms[0];
		
		if(form.indicadorEmpresaCobranca[0].checked){
		
			form.dataInicioContratoCobranca.style.color = "#000000";
			form.dataInicioContratoCobranca.style.background = "white";
			form.dataInicioContratoCobranca.disabled = false;
			
			if (form.percentualDaFaixaInformado.value != null
					&& form.percentualDaFaixaInformado.value != 'sim'
					&& form.percentualDaFaixaInformado.value != 'SIM') {
				form.percentualPagamento.style.color = "#000000";
				form.percentualPagamento.style.background = "white";
				form.percentualPagamento.disabled = false;
			}
			
			form.percentualDaFaixa.style.color = "#000000";
			form.percentualDaFaixa.style.background = "white";
			form.percentualDaFaixa.disabled = false;
			
			form.quantidadeMinimaContas.style.color = "#000000";
			form.quantidadeMinimaContas.style.background = "white";
			form.quantidadeMinimaContas.disabled = false;
			
			form.adicionarFaixa.disabled = false;
			
	 	 }else{
	 	 
	 	 	form.dataInicioContratoCobranca.value = "";
			form.dataInicioContratoCobranca.style.color = "#000000";
			form.dataInicioContratoCobranca.style.background = "#EFEFEF";
			form.dataInicioContratoCobranca.disabled = true;
	 	 
			form.percentualPagamento.value = "";
			form.percentualPagamento.style.color = "#000000";
			form.percentualPagamento.style.background = "#EFEFEF";
			form.percentualPagamento.disabled = true;
	 	 
			form.percentualDaFaixa.value = "";
			form.percentualDaFaixa.style.color = "#000000";
			form.percentualDaFaixa.style.background = "#EFEFEF";
			form.percentualDaFaixa.disabled = true;
	 	 
			form.quantidadeMinimaContas.value = "";
			form.quantidadeMinimaContas.style.color = "#000000";
			form.quantidadeMinimaContas.style.background = "#EFEFEF";
			form.quantidadeMinimaContas.disabled = true;
			
			form.adicionarFaixa.disabled = true;
	 	 }
	} 

	function adicionarEmpresaContratoCobranca(){
		var form = document.forms[0];
		
		if (form.percentualDaFaixa.value == '' || !validaPercentual(form.percentualDaFaixa)) {
			alert('Valor informado do percentual está inválido.');
		} else {
			form.action = 'exibirInserirEmpresaAction.do?adicionarFaixa=sim';
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
			form.action = 'exibirInserirEmpresaAction.do?removerEmpresaCobrancaFaixa='+obj;
			form.submit();
		}
	}
	
	function bloqueiaCampos(){
		var form = document.forms[0];
		
		
		if (form.percentualPagamento.value != null && form.percentualPagamento.value != '') {
			
			form.adicionarFaixa.disabled = true;
			form.quantidadeMinimaContas.value = "";
			form.quantidadeMinimaContas.disabled = true;
			form.quantidadeMinimaContas.style.color = "#000000";
			form.quantidadeMinimaContas.style.background = "#EFEFEF";
			form.percentualDaFaixa.value = "";
			form.percentualDaFaixa.disabled = true;
			form.percentualDaFaixa.style.color = "#000000";
			form.percentualDaFaixa.style.background = "#EFEFEF";
			
		} else if ((form.quantidadeMinimaContas.value != null && form.quantidadeMinimaContas.value != '')
			|| (form.percentualDaFaixa.value != null && form.percentualDaFaixa.value != '')
			|| (form.percentualDaFaixaInformado.value != null
					&& (form.percentualDaFaixaInformado.value == 'sim'
						|| form.percentualDaFaixaInformado.value == 'SIM'))) {
			
			form.percentualPagamento.value = "";
			form.percentualPagamento.disabled = true;
			form.percentualPagamento.style.color = "#000000";
			form.percentualPagamento.style.background = "#EFEFEF";
		} else {
			
			form.percentualPagamento.disabled = false;
			form.percentualPagamento.style.color = "#000000";
			form.percentualPagamento.style.background = "white";
			
			form.adicionarFaixa.disabled = false;
			form.quantidadeMinimaContas.disabled = false;
			form.quantidadeMinimaContas.style.color = "#000000";
			form.quantidadeMinimaContas.style.background = "white";
			form.percentualDaFaixa.disabled = false;
			form.percentualDaFaixa.style.color = "#000000";
			form.percentualDaFaixa.style.background = "white";
		}
	}
	
	function limparFaixa(){
		var form = document.forms[0];
		
		form.action = 'exibirInserirEmpresaAction.do?limparFaixa=sim';
		form.submit();
	}

</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('descricao');bloqueiaDadosEmpresaCobranca();">

<html:form action="/inserirEmpresaAction.do"
	name="InserirEmpresaActionForm"
	type="gcom.gui.cadastro.InserirEmpresaActionForm" method="post"
	onsubmit="return validateInserirEmpresaActionForm(this);">

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
					<td class="parabg">Inserir Empresa</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->

			<table width="100%" border="0">
				<tr>

					<td colspan="2">Para adicionar uma empresa, informe o dado abaixo:</td>
				</tr>
				<tr>
					<td HEIGHT="30"><strong>Nome: <font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="descricao" maxlength="50"
						size="50" tabindex="1"
						onkeyup="limitTextArea(document.forms[0].descricao, 50, document.getElementById('utilizado'), document.getElementById('limite'));" />
					<br>
					</td>
				</tr>
				<tr>
					<td><strong>Nome Abreviado:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricaoAbreviada" size="10" maxlength="10"  tabindex="2"/> </span></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>E-mail:</strong></td>
					<td colspan="2"><span class="style2"> <html:text property="email"
						size="60" maxlength="80" tabindex="3" /> </span></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>Empresa Principal? <font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorEmpresaPrincipal" 
						value="1" tabindex="4" /><strong>Sim</strong> <html:radio
						property="indicadorEmpresaPrincipal" value="2" tabindex="5" /><strong>Não</strong>
					</td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td><strong>Indicador Empresa Cobrança:</strong></td>
					<td><html:radio property="indicadorEmpresaCobranca" 
						value="1" onclick="javascript:bloqueiaDadosEmpresaCobranca();" tabindex="6"/><strong>Sim</strong> <html:radio
						property="indicadorEmpresaCobranca" value="2"
						onclick="javascript:bloqueiaDadosEmpresaCobranca();limparFaixa();" tabindex="7"/><strong>Não</strong></td>
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
					<td><strong> Data do Início do Contrato:</strong></td>

					<td colspan="2"><strong> <html:text
						property="dataInicioContratoCobranca" size="11" maxlength="10"
						tabindex="10" onkeyup="mascaraData(this, event);"
						onkeypress="mascaraData(this, event);return isCampoNumerico(event);"/> <a
						href="javascript:abrirCalendario('InserirEmpresaActionForm', 'dataInicioContratoCobranca');">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="16" height="15" border="0" alt="Exibir Calendário"
						tabindex="11" /></a> (dd/mm/aaaa) </strong></td>
				</tr>
				
				<logic:present name="colecaoEmpresaCobrancaFaixa">
					<tr>
						<td><strong>Percentual do Pagamento das Contas Cobradas:</strong></td>
						<td colspan="2"><span class="style2"> <html:text
							property="percentualPagamento" size="7" maxlength="7"
							onkeypress="return isCampoNumerico(event);" onchange="bloqueiaCampos();"
							onkeyup="formataValorDecimalTresCasas(this, 6);" tabindex="12"
							disabled="true" /> </span></td>
						<td>&nbsp;</td>
					</tr>
				</logic:present>
				
				<logic:notPresent name="colecaoEmpresaCobrancaFaixa">
					<tr>
						<td><strong>Percentual do Pagamento das Contas Cobradas:</strong></td>
						<td colspan="2"><span class="style2"> <html:text
							property="percentualPagamento" size="7" maxlength="7"
							onkeypress="return isCampoNumerico(event);" onchange="bloqueiaCampos();"
							onkeyup="formataValorDecimalTresCasas(this, 6);" tabindex="12"/> </span></td>
						<td>&nbsp;</td>
					</tr>
				</logic:notPresent>
				<tr>
					<td><strong>Quantidade mínima de contas:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="quantidadeMinimaContas" size="8" maxlength="8" onchange="bloqueiaCampos();"
						onkeypress="return isCampoNumerico(event);" tabindex="13"/> </span></td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td><strong>Percentual da Faixa:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="percentualDaFaixa" size="7" maxlength="7"
						onkeypress="return isCampoNumerico(event);" onchange="bloqueiaCampos();"
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
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campo Obrigatório</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2"><input name="Button" type="button"
						class="bottonRightCol" value="Limpar" align="left"
						onclick="javascript:limparForm();"> <input name="Button"
						type="button" class="bottonRightCol" value="Cancelar" align="left"
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
