<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="java.util.Collection,gcom.util.ConstantesSistema"%>
<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.seguranca.acesso.usuario.UsuarioAlteracao"%>
<%@ page import="gcom.seguranca.transacao.TabelaLinhaColunaAlteracao"%>
<%@ page import="gcom.seguranca.acesso.OperacaoEfetuada"%>
<%@ page import="java.util.Date"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AtualizarAvisoBancarioAction" />
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
	
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script>
<!--

var bCancel = false; 

   function validateAtualizarAvisoBancarioAction(form) {
		
     if (bCancel) {
     	return true; 
     }else 
     {
        return validateRequired(form) && validateCaracterEspecial(form) && validateLong(form) && validateDate(form) && validateDecimalZeroPositivo(form);
  	 }
  }
  
  function caracteresespeciais () { 
    this.aa = new Array("numeroDocumento", "Número do Documento possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    this.ab = new Array("dataRealizacao", "Data da Realização do Aviso possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    this.ac = new Array("valorArrecadacao", "Valor da Arrecadacao possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    this.ad = new Array("valorDevolucao", "Valor da Devolucao possui caracteres especiais.", new Function ("varName", " return this[varName];"));
}

   function required () {
 	this.aa = new Array("numeroDocumento", "Informe Número do Documento.", new Function ("varName", " return this[varName];"));
 	this.ab = new Array("idConta", "Informe Conta Bancaria.", new Function ("varName", " return this[varName];"));
    this.ac = new Array("dataRealizacao", "Informe Data da Realização do Aviso.", new Function ("varName", " return this[varName];"));
    this.ac = new Array("dataLancamento", "Informe Data de Lançamento do Aviso.", new Function ("varName", " return this[varName];"));
   }
   
   function DateValidations () {
     this.aa = new Array("dataRealizacao", "Data da Realização do Aviso inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     this.aa = new Array("dataLancamento", "Data de Lançamento do Aviso inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
   }
   
   function IntegerValidations () {
    this.aa = new Array("numeroDocumento", "Número do Documento deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
   }
   
   function DecimalZeroPositivoValidations () {
    this.aa = new Array("valorArrecadacao", "Valor da Arrecadação deve somente conter números decimais.", new Function ("varName", " return this[varName];"));
    this.ab = new Array("valorDevolucao", "Valor da Devolução deve somente conter números decimais.", new Function ("varName", " return this[varName];"));     
}

function calcular(form){
	if (validateAtualizarAvisoBancarioAction(form)){
		if(form.valorArrecadacao.value == '' && form.valorDevolucao.value == '' ){
			alert('Informe Valor da Arrecadação ou Valor da Devolução.'); 
		}else{
			document.forms[0].acao.value = 'calcular';
			document.forms[0].action = 'ExibirAtualizarAvisoBancarioAction.do';
			document.forms[0].submit();
		}
	}
}
function controleBotaoDeducao(form){
	var valor = form.valorArrecadacao.value.replace(",","");
	valor = valor.replace(".","");
	if(valor <= 0) 
	{
		form.adicionarDeducoes.disabled = true;
	}else{
		form.adicionarDeducoes.disabled = false;
	}
}

function atualizar(form) {
	var DATA_ATUAL = document.getElementById("DATA_ATUAL").value;
	if (validateAtualizarAvisoBancarioAction(form)){
  	
		if ((trim(form.valorArrecadacao.value) == '') && (trim(form.valorDevolucao.value) == ''))  {
			alert('Informe Valor da Arrecadação ou Valor da Devolução.');
			return;
		}
		else if (comparaData(form.dataRealizacao.value, ">", DATA_ATUAL )){
			alert("Data da Realização do Aviso posterior à Data corrente " + DATA_ATUAL + ".");
			return;
		}
<logic:present name="avisoDeducoes" >
<logic:iterate name="avisoDeducoes" id="aDeducoes" indexId="i">
	else if (trim(form.posicaoAvisoDeducao_<bean:write name="i"/>.value) == '') { 
		alert('Informe Valor da Dedução <bean:write name="aDeducoes" property="deducaoTipo.descricaoAbreviado" />.');
		return;
	}
</logic:iterate>
</logic:present>


<logic:present name="avisoAcerto">
<logic:iterate name="avisoAcerto" id="aAcerto" indexId="i">
	else if (trim(form.posicaoAvisoAcerto_<bean:write name="i"/>.value) == '') { 
		var acerto = 	'<bean:write name="aAcerto"  property="contaBancaria.agencia.banco.id" /> - ' +
						'<bean:write name="aAcerto"  property="contaBancaria.agencia.id" /> - ' +
						'<bean:write name="aAcerto"  property="contaBancaria.numeroConta" />';
		alert('Informe Valor do Acerto ' + acerto + '.');
		return;
	}
</logic:iterate>
</logic:present>
	else{
		form.submit();
	}
	}
}

function limparContaBancaria() {
	document.forms[0].idConta.value = '';
	document.forms[0].nomeBanco.value = '';
	document.forms[0].nomeAgencia.value = '';
	document.forms[0].numeroConta.value = '';
}

function recuperarDadosCincoParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, descricaoRegistro3 ,tipoConsulta) {
    if (tipoConsulta == 'contaBancaria') {
      document.forms[0].nomeBanco.value = descricaoRegistro1;
      document.forms[0].nomeAgencia.value = descricaoRegistro2;
      document.forms[0].numeroConta.value = descricaoRegistro3;
      document.forms[0].idConta.value = codigoRegistro;
      document.forms[0].idContaRequest.value = codigoRegistro;
    }
}

function recuperarDadosDeducao(idTipoDeducao,valorDeducao) {
	adicionarDeducao(idTipoDeducao,valorDeducao);
}

function recuperarDadosAcertos(idContaBancaria,tipoAcesso,acerto,dataAcerto,valorAcerto) {
	adicionarAcerto(idContaBancaria,tipoAcesso,acerto,dataAcerto,valorAcerto);
}

function adicionarDeducao(idTipoDeducao,valorDeducao) {
	document.forms[0].idTipoDeducao.value = idTipoDeducao;
	document.forms[0].valorDeducao.value = valorDeducao;
	document.forms[0].acao.value = 'adicionarDeducao';
	document.forms[0].action = 'ExibirAtualizarAvisoBancarioAction.do';
	document.forms[0].submit();
}

function removerDeducao(posicaoDeducao) {
	document.forms[0].acao.value = 'removerDeducao';
	document.forms[0].action = 'ExibirAtualizarAvisoBancarioAction.do?posicao=' + posicaoDeducao;
	if (confirm("Confirma remoção?")){
		document.forms[0].submit();
	}
}

function adicionarAcerto(idContaBancaria,tipoAcesso,acerto,dataAcerto,valorAcerto) {
	document.forms[0].idContaBancaria.value = idContaBancaria;
	document.forms[0].tipoAcesso.value = tipoAcesso;
	document.forms[0].acerto.value = acerto;
	document.forms[0].dataAcerto.value = dataAcerto;
	document.forms[0].valorAcerto.value = valorAcerto;
	document.forms[0].acao.value = 'adicionarAcerto';
	document.forms[0].action = 'ExibirAtualizarAvisoBancarioAction.do';
	document.forms[0].submit();
}

function removerAcerto(posicaoAcerto) {
	document.forms[0].acao.value = 'removerAcerto';
	document.forms[0].action = 'ExibirAtualizarAvisoBancarioAction.do?posicao=' + posicaoAcerto;
	if (confirm("Confirma remoção?")){
		document.forms[0].submit();
	}
}

-->
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/AtualizarAvisoBancarioAction" method="post"
	onsubmit="return validateAtualizarAvisoBancarioAction(this);">

	<input type="hidden" name="acao">

	<input type="hidden" name="idContaBancaria">
	<input type="hidden" name="tipoAcesso">
	<input type="hidden" name="acerto">
	<input type="hidden" name="dataAcerto">
	<input type="hidden" name="valorAcerto">
	<input type="hidden" name="idTipoDeducao">
	<input type="hidden" name="valorDeducao">

	<bean:define name="avisoBancario" id="avisoBancario" />
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="135" valign="top" class="leftcoltext">
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
			<table align="center" border="0" cellpadding="0" cellspacing="0"
				width="100%">
				<tbody>
					<tr>
						<td width="11"><img src="imagens/parahead_left.gif" border="0"></td>

						<td class="parabg">Atualizar Aviso Bancário</td>
						<td valign="top" width="11"><img src="imagens/parahead_right.gif"
							border="0"></td>
					</tr>
				</tbody>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>

			<table border="0" width="100%">

				<tbody>
					<tr>
						<td class="style1" width="156"><strong>Arrecadador:</strong></td>
						<td colspan="3" class="style1"><html:hidden name="avisoBancario"
							property="arrecadador.id" /> <html:text size="50" maxlength="50"
							name="avisoBancario" disabled="true"
							property="arrecadador.cliente.nome"
							style="border: 0pt none ; background-color: rgb(239, 239, 239);" />
						</td>
					</tr>
					<tr>
						<td class="style1"><strong>Data do Lançamento:</strong></td>

						<td colspan="3" class="style1"><INPUT TYPE="hidden" ID="DATA_ATUAL"
						value="${requestScope.dataAtual}" /><html:text
							value="<%= Util.formatarData(((gcom.arrecadacao.aviso.AvisoBancario)avisoBancario).getDataLancamento()) %>"
							size="10" maxlength="10" property="dataLancamento"
							onkeypress="return isCampoNumerico(event);"
							onkeyup="mascaraData(this, event)" />
						<a
							href="javascript:abrirCalendario('AvisoBancarioActionForm', 'dataLancamento');">
						<img border="0"
							src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" alt="Exibir Calendário" tabindex="4" /></a>
							 <strong>&nbsp;(dd/mm/aaaa)</strong>
						</td>
					</tr>
					<tr>
						<td><strong>Sequencial do Aviso:</strong></td>
						<td colspan="3"><span class="style1"> <strong> <html:text size="3"
							maxlength="3" name="avisoBancario" disabled="true"
							property="numeroSequencial"
							style="border: 0pt none ; background-color: rgb(239, 239, 239);" />
						</strong></span></td>
					</tr>

					<tr>
						<td><strong>Tipo do Aviso:</strong></td>
						<td colspan="3"><span class="style1"> <strong> <logic:equal
							name="avisoBancario" property="indicadorCreditoDebito" value="1">
							<input value="Crédito" type="text" size="7" maxlength="7"
								name="avisoBancario" disabled="disabled"
								name="IndicadorCreditoDebito"
								style="border: 0pt none ; background-color: rgb(239, 239, 239);" />
						</logic:equal> <logic:equal name="avisoBancario"
							property="indicadorCreditoDebito" value="2">
							<input value="Débito" type="text" size="7" maxlength="7"
								name="avisoBancario" disabled="disabled"
								name="IndicadorCreditoDebito"
								style="border: 0pt none ; background-color: rgb(239, 239, 239);" />
						</logic:equal> </strong></span></td>
					</tr>
					<tr>
						<td class="style1" width="156"><strong>Forma de Arrecadação:</strong></td>
						<td colspan="3" class="style1">
						<html:select property="idFormaArrecadacao" tabindex="10" style="width:200px;">
							<html:option value="-1"> &nbsp; </html:option>
							<html:options collection="collectionArrecadacaoForma" property="id" labelProperty="descricao"/>
						</html:select></td>
							
						</td>
					</tr>
					<tr>
						<td colspan="4" class="style1" height="24">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="4">Para atualizar o aviso bancário, informe
						os dados abaixo:</td>
					</tr>
					<tr>
						<td class="style1"><strong>Número do Documento:<font
							color="#ff0000">*</font></strong></td>
						<td colspan="3" class="style1"><html:text size="9" maxlength="9"
							onkeypress="return isCampoNumerico(event);"
							name="avisoBancario" property="numeroDocumento" /></td>
					</tr>
					<tr>
						<td class="style1"><strong>Conta Bancária:<font color="#ff0000">*</font></strong></td>

						<td colspan="3" class="style1"><input type="hidden" name="idConta"
							value="<bean:write name="avisoBancario"  property="contaBancaria.id" />">
						<input type="hidden" name="idContaRequest"
							value="<bean:write name="avisoBancario"  property="contaBancaria.id" />">
						<input type="text" size="3" maxlength="3" name="nomeBanco"
							value="<bean:write name="avisoBancario"  property="contaBancaria.agencia.banco.id" />"
							disabled="true"
							style="border: 0pt none ; background-color: rgb(239, 239, 239);" />
						<input type="text" size="9" maxlength="9" name="nomeAgencia"
							value="<bean:write name="avisoBancario"  property="contaBancaria.agencia.codigoAgencia" />"
							disabled="true"
							style="border: 0pt none ; background-color: rgb(239, 239, 239);" />
						<input type="text" size="20" maxlength="20" name="numeroConta"
							value="<bean:write name="avisoBancario"  property="contaBancaria.numeroConta" />"
							disabled="true"
							style="border: 0pt none ; background-color: rgb(239, 239, 239);" />
						<a
							href="javascript:abrirPopup('contaBancariaPesquisarAction.do?limpar=sim', 285, 565);"><img
							border="0" src="imagens/pesquisa.gif" width="23" height="21" title="Pesquisar Conta Bancária"></a><a
							href="javascript:limparContaBancaria();"><img border="0"
							src="imagens/limparcampo.gif" width="23" height="21" title="Limpar Conta Bancária"></a>
					</tr>
					<tr>
						<td class="style1"><strong>Data da Realização do Aviso:<font
							color="#ff0000">*</font></strong></td>
						<td colspan="3" class="style1"><logic:present name="avisoBancario"
							property="dataRealizada">
							<input type="text" size="10" maxlength="10"
								onkeypress="return isCampoNumerico(event);"
								onkeyup="javascript:mascaraData(this, event);"
								name="dataRealizacao"
								value="<%= Util.formatarData(((gcom.arrecadacao.aviso.AvisoBancario)avisoBancario).getDataRealizada())%>">
						</logic:present> <logic:notPresent name="avisoBancario"
							property="dataRealizada">
							<input type="text" size="10" maxlength="10"
								onkeyup="javascript:mascaraData(this, event);"
								name="dataRealizacao" value="">
						</logic:notPresent> 
						<a href="javascript:abrirCalendario('AvisoBancarioActionForm', 'dataRealizacao')">
							<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
							width="20" border="0" align="absmiddle" alt="Exibir Calendário" />
						</a>
						 <strong>&nbsp;(dd/mm/aaaa)</strong>
					</tr>

					<tr>
						<td class="style1"><strong>Valor da Arrecadação:</strong></td>
						<td colspan="3" class="style1"><input type="text " size="14"
							maxlength="14" name="valorArrecadacao" onkeyup="formataValorMonetario(this, 14);controleBotaoDeducao(document.forms[0]);" style="text-align:right;"
							value="<%= Util.formatarMoedaReal(((gcom.arrecadacao.aviso.AvisoBancario) avisoBancario).getValorArrecadacaoInformado()) %>">
						</td>
					</tr>
					<tr>
						<td class="style1"><strong>Valor da Devolução:</strong></td>
						<td colspan="3" class="style1"><input type="text " size="14"
							maxlength="14" name="valorDevolucao" onkeyup="formataValorMonetario(this, 14)" style="text-align:right;"
							value="<%= Util.formatarMoedaReal(((gcom.arrecadacao.aviso.AvisoBancario) avisoBancario).getValorDevolucaoInformado()) %>">
						</td>
					</tr>
					<tr>
						<td><strong>Valor das Dedu&ccedil;&otilde;es:</strong></td>
							<td colspan="3"><span class="style1"> <strong> <html:text
								property="valorDeducoes" size="14" maxlength="14"
								readonly="true"
								style="background-color:#EFEFEF; border:0; font-color: #000000; text-align:right;" />
						</strong></span></td>
					</tr>
					<tr>
						<td><strong>Valor do Aviso:</strong></td>
						<td colspan="3"><html:text property="valorAviso" size="14" maxlength="14"
							readonly="true"
							style="background-color:#EFEFEF; border:0; font-color: #000000; text-align:right;" />
						</td>
					</tr>
					<tr>
						<td class="style1">&nbsp;</td>
						<td colspan="3" class="style1"><font color="#ff0000">*</font>
						Campo Obrigatório</td>
					</tr>
					<tr>
						<td colspan="4">
							<div align="right">
								<input type="button" class="bottonRightCol" value="Calcular" tabindex="10"
									onclick="calcular(document.forms[0]);" style="width: 80px">
							</div>
						</td>														
					</tr>
					<tr>
						<td colspan="4" class="style1" height="24">
						<hr>
						</td>
					</tr>
					<tr>
						<td class="style1"><strong>Deduções do Aviso</strong></td>

						<td colspan="3" class="style1">
						<div align="right"><input name="adicionarDeducoes"
							class="bottonRightCol" value="Adicionar"
							onclick="javascript:abrirPopup('exibirPesquisarAvisoDeducoesAction.do', 285, 565);"
							type="button"></div>
						</td>
					</tr>
					<tr>
						<td colspan="4" height="31">
						<table bgcolor="#99ccff" width="100%">
							<!--header da tabela interna -->
							<tbody>
								<tr bordercolor="#FFFFFF" bgcolor="#99ccff">
									<td width="10%">
									<div class="style9" align="center"><strong>Remover</strong></div>
									</td>

									<td width="21%">
									<div class="style9" align="center"><strong>Tipo de Dedução</strong></div>
									</td>
									<td width="21%">
									<div class="style9" align="center"><strong>Valor da Dedução</strong></div>
									</td>
								</tr>
								<%int cont = 0;%>
								<logic:iterate name="avisoDeducoes" id="avisoDeducoes"
									indexId="i">
									<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {%>
										<tr bgcolor="#FFFFFF">
											<%}%>
										<td height="17">
										<div align="center"><a
											href="javascript:removerDeducao('<%= i %>');"><img border="0"
											src="imagens/Error.gif" height="14" width="14"></a></div>
										</td>
										<td>
										<div align="center"><bean:write name="avisoDeducoes"
											property="deducaoTipo.descricaoAbreviado" /></div>
										</td>
										<td>
										<div align="center"><span class="style1"> <input
											name="posicaoAvisoDeducao_<bean:write name="i"/>" onkeyup="formataValorMonetario(this, 14)" style="text-align:right;"
											value="<%= Util.formatarMoedaReal(((gcom.arrecadacao.aviso.AvisoDeducoes) avisoDeducoes).getValorDeducao()) %>"
											size="14" maxlength="14" type="text"> </span></div>
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
						</td>
					</tr>
					<tr>
						<td colspan="4" class="style1">
						<hr>
						</td>
					</tr>
					<tr>
						<td class="style1"><strong>Acertos do Aviso</strong></td>

						<td colspan="3" class="style1">
						<div align="right"><input name="adicionarAcertos"
							class="bottonRightCol" value="Adicionar"
							onclick="javascript:abrirPopup('ExibirPesquisarAvisoAcertoAction.do', 285, 565);"
							type="button"></div>
						</td>
					</tr>
					<tr>
						<td colspan="4" height="31">
						<table bgcolor="#99ccff" width="100%">
							<!--header da tabela interna -->
							<tbody>
								<tr bordercolor="#FFFFFF" bgcolor="#99ccff">
									<td width="13%">
									<div class="style9" align="center"><strong>Remover</strong></div>
									</td>

									<td width="33%">
									<div align="center"><strong>Conta Bancária</strong></div>
									</td>
									<td width="9%">
									<div align="center"><strong>Acertar</strong></div>
									</td>
									<td width="9%">
									<div align="center"><strong>Tipo do Acerto</strong></div>
									</td>
									<td width="12%">
									<div align="center"><strong>Data do Acerto</strong></div>
									</td>
									<td width="24%">
									<div class="style9" align="center"><strong>Valor do Acerto</strong></div>
									</td>
								</tr>
								<%cont = 0;%>
								<logic:iterate name="avisoAcerto" id="avisoAcerto" indexId="i">
										<%cont = cont + 1;
											if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {%>
										<tr bgcolor="#FFFFFF">
											<%}%>								
										<td height="17">
										<div align="center"><a
											href="javascript:removerAcerto('<%= i %>');"><img border="0"
											src="imagens/Error.gif" height="14" width="14"></a></div>
										</td>

										<td>
										<div align="center"><bean:write name="avisoAcerto"  property="contaBancaria.agencia.banco.id" />-
															<bean:write name="avisoAcerto"  property="contaBancaria.agencia.codigoAgencia" />-
															<bean:write name="avisoAcerto"  property="contaBancaria.numeroConta" />
										</div>
										</td>
										<td>
										<div align="center"><logic:equal name="avisoAcerto"
											property="indicadorArrecadacaoDevolucao" value="1">Arrecadação</logic:equal>
										<logic:equal name="avisoAcerto"
											property="indicadorArrecadacaoDevolucao" value="2">Devolução</logic:equal></div>
										</td>
										<td>
										<div align="center"><logic:equal name="avisoAcerto"
											property="indicadorCreditoDebito" value="1">Crédito</logic:equal>
										<logic:equal name="avisoAcerto"
											property="indicadorCreditoDebito" value="2">Débito</logic:equal></div>
										</td>
											<td>
										<div align="center"><%=Util
									.formatarData(((gcom.arrecadacao.aviso.AvisoAcerto) avisoAcerto)
											.getDataAcerto())%></div>
										</td>
										<td>
										<div align="center"><span class="style1"> <input
											name="posicaoAvisoAcerto_<bean:write name="i"/>" onkeyup="formataValorMonetario(this, 14)" style="text-align:right;"
											value="<%= Util.formatarMoedaReal(((gcom.arrecadacao.aviso.AvisoAcerto) avisoAcerto).getValorAcerto()) %>"
											size="14" maxlength="14" type="text"> </span></div>
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
						</td>
					</tr>
					<tr>
						<td colspan="4" class="style1">
						<hr>
						</td>
					</tr>
				</table>
				<table border="0" width="100%">
					<tr>
						<td>
							<logic:present scope="session" name="manter">
								<input name="Submit222"
									class="bottonRightCol" value="Voltar" type="button"
									onclick="window.location.href='/gsan/ExibirManterAvisoBancarioAction.do';">
							</logic:present>
							<logic:notPresent scope="session" name="manter">
								<logic:present scope="session" name="filtrar_manter">
									<input name="Submit222"
									class="bottonRightCol" value="Voltar" type="button"
									onclick="javascript:history.back();">
								</logic:present>
								<logic:notPresent scope="session" name="filtrar_manter">
									<input name="Submit222"
										class="bottonRightCol" value="Voltar" type="button"
										onclick="window.location.href='/gsan/exibirFiltrarAvisoBancarioAction.do';">
								</logic:notPresent>
							</logic:notPresent>
						
							<input name="Submit22"
								class="bottonRightCol" value="Desfazer" type="button"
								onclick="window.location.href='/gsan/ExibirAtualizarAvisoBancarioAction.do';">	
							<input name="Submit23" class="bottonRightCol" value="Cancelar"
								type="button"
								onclick="window.location.href='/gsan/telaPrincipal.do'"> 
						</td>

						<td align="right" width="313">
						  <gsan:controleAcessoBotao name="Atualizar" value="Atualizar" onclick="javascript:atualizar(document.forms[0]);" url="atualizarAvisoBancarioAction.do"/>
						  <%-- <input name="Atualizar" onclick="javascript:atualizar(document.forms[0]);" class="bottonRightCol"	value="Atualizar" type="button"> --%>
						</td>
					</tr>


				</tbody>
			</table>
			</td>
		</tr>


		<%@ include file="/jsp/util/rodape.jsp"%>
		<script language="JavaScript">
		<!--
			controleBotaoDeducao(document.forms[0]);
		-->
		</script>
</body>
</html:form>
</html:html>
