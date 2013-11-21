<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="gcom.arrecadacao.aviso.AvisoDeducoes"%>
<%@page import="gcom.gui.GcomAction"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirAvisoBancarioActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
<!-- Begin

function recuperarDadosCincoParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, descricaoRegistro3, tipoConsulta) {

    var form = document.InserirAvisoBancarioActionForm;
    if (tipoConsulta == 'contaBancaria') {
      form.idContaBancaria.value = codigoRegistro;
      form.numeroBanco.value = descricaoRegistro1;
      form.numeroAgencia.value = descricaoRegistro2;
      form.numeroConta.value = descricaoRegistro3;
    }
}

function recuperarDadosDeducao(tipoDeducao,valorDeducao) {
	document.forms[0].tipoDeducaoInclusao.value = tipoDeducao;
	document.forms[0].valorDeducaoInclusao.value = valorDeducao;
	document.forms[0].action = 'exibirAdicionarAvisoDeducoesAction.do';
	document.forms[0].submit();
}
 
	function limparCampos(){
		var form = document.forms[0];	
	}

      var bCancel = false; 

    function validateInserirAvisoBancarioActionForm(form) {                                                                   
			
      if (bCancel) {
      	return true; 
      } else {
        if(form.tipoAviso[0].checked == false && form.tipoAviso[1].checked == false){
			alert('Informe Tipo do Aviso.'); 
		}else if(document.InserirAvisoBancarioActionForm.valorArrecadacao.value == '' && document.InserirAvisoBancarioActionForm.valorDevolucao.value == '' ){
			alert('Informe Valor da Arrecadação ou Valor da Devolução'); 
		} else {
       		return validateRequired(form) && validateCaracterEspecial(form) && validateLong(form) && validateDate(form) && validateDecimalZeroPositivo(form);
       	}
   	  }
   } 

    function caracteresespeciais () { 
     this.aa = new Array("numeroDocumento", "Número do Documento possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("dataRealizacao", "Data da Realização do Aviso possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("valorArrecadacao", "Valor da Arrecadacao possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("valorDevolucao", "Valor da Devolucao possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	}

    function required () {
	 this.aa = new Array("tipoAviso", "Informe Tipo do Aviso.", new Function ("varName", " return this[varName];"));
	 this.ab = new Array("numeroDocumento", "Informe Número do Documento.", new Function ("varName", " return this[varName];"));
	 this.ac = new Array("numeroBanco", "Informe Conta Bancária.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("dataRealizacao", "Informe Data da Realização do Aviso.", new Function ("varName", " return this[varName];"));
    }
    
    function DateValidations () {
      this.aa = new Array("dataRealizacao", "Data da Realização do Aviso inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    }
    
    function IntegerValidations () {
     this.aa = new Array("numeroDocumento", "Número do Documento deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    
    function DecimalZeroPositivoValidations () {
     this.az = new Array("valorArrecadacao", "Valor da Arrecadação deve somente conter números decimais.", new Function ("varName", " return this[varName];"));
     this.ba = new Array("valorDevolucao", "Valor da Devolução deve somente conter números decimais.", new Function ("varName", " return this[varName];"));     
	}  
    
     function limparArrecadador(){
     	var form = document.forms[0];	
     	form.codigoArrecadador.value = "";
     	form.nomeArrecadador.value = "";
     }


function limparConta(){
	var form = document.forms[0];
	form.numeroConta.value = "";
	form.numeroBanco.value = "";
	form.numeroAgencia.value = "";
}

function bloquearDevolucao(){
	var form = document.forms[0];
	if(form.valorArrecadacao.value != "")
		form.valorDevolucao.disabled = true;
	else
		form.valorDevolucao.disabled = false;
}
function bloquearArrecadacao(){
	var form = document.forms[0];
	if(form.valorDevolucao.value != "")
		form.valorArrecadacao.disabled = true;
	else
		form.valorArrecadacao.disabled = false;

}

function calcular(form){
	if (validateInserirAvisoBancarioActionForm(form)){
		if(form.tipoAviso[0].checked == false && form.tipoAviso[1].checked == false){
			alert('Informe Tipo do Aviso.'); 
		}
		else if(form.valorArrecadacao.value == '' && form.valorDevolucao.value == '' ){
			alert('Informe Valor da Arrecadação ou Valor da Devolução.'); 
		}else{
			urlRedirect = "exibirInserirAvisoBancarioWizardAction.do?action=exibirProcessoTresInserirAvisoBancarioAction";
			redirecionarSubmit(urlRedirect);
		}
	}
}

function removerDeducao(timeDeducao){
 	var form = document.forms[0];
	form.action = "exibirInserirAvisoBancarioWizardAction.do?action=exibirProcessoTresInserirAvisoBancarioAction&timestamp=" + timeDeducao;
	if (confirm("Confirma remoção?")){
		form.submit();
	}
}
-->
</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/exibirInserirAvisoBancarioWizardAction"
	name="InserirAvisoBancarioActionForm"
	type="gcom.gui.arrecadacao.banco.InserirAvisoBancarioActionForm"
	method="post">

	<input type="hidden" name="numeroPagina" value="3" />
	
	<input type="hidden" name="tipoDeducaoInclusao" />
	<input type="hidden" name="valorDeducaoInclusao" />

	<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=3"/>

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
					<td><html:hidden property="idContaBancaria"/></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">

				<tr>
					<td width="11"><img src="imagens/parahead_left.gif" border="0" /></td>
					<td class="parabg">Inserir Aviso Bancário</td>

					<td width="11"><img src="imagens/parahead_right.gif" border="0" /></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td>
					<table width="100%" border="0">
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td width="209" class="style1"><strong>Arrecadador:</strong></td>
							<td width="431" colspan="3" class="style1"><html:text
								property="codigoArrecadador" size="3" maxlength="3"
								readonly="true"
								style="background-color:#EFEFEF; border:0; font-color: #000000" />

							<strong> <html:text property="nomeArrecadador" size="50"
								maxlength="50" readonly="true"
								style="background-color:#EFEFEF; border:0; font-color: #000000" />

							</strong></td>
						</tr>
						<tr>
							<td class="style1"><strong>Data do Lan&ccedil;amento:</strong></td>
							<td colspan="3" class="style1"><html:text
								property="dataLancamento" size="10" maxlength="10"
								readonly="true"
								style="background-color:#EFEFEF; border:0; font-color: #000000" />
							</td>
						</tr>
						<tr>
							<td><strong>Sequencial do Aviso:</strong></td>
							<td colspan="3"><span class="style1"> <strong> <html:text
								property="numeroSequencial" size="3" maxlength="3"
								readonly="true"
								style="background-color:#EFEFEF; border:0; font-color: #000000" />

							</strong></span></td>
						</tr>
						<tr>
							<td colspan="4">Para inserir o aviso banc&aacute;rio, informe os
							dados abaixo:</td>
						</tr>
						<tr>
							<td><strong>Tipo do Aviso:<font color="#FF0000">*</font></strong>
							</td>
							<td colspan="3"><logic:present name="avisoSelecionado">
								<html:radio property="tipoAviso" value="1" disabled="true" />
								<strong>Crédito</strong>
								<html:radio property="tipoAviso" value="2" disabled="true" />
								<strong>Débito</strong>
							</logic:present> <logic:notPresent name="avisoSelecionado">
								<html:radio property="tipoAviso" value="credito" />
								<strong>Crédito</strong>
								<html:radio property="tipoAviso" value="debito" />
								<strong>Débito</strong>
							</logic:notPresent>
							</td>
						</tr>
						<tr>
							<td class="style1"><strong>N&uacute;mero do Documento:<font
								color="#FF0000">*</font></strong></td>
							<td colspan="3" class="style1"><html:text tabindex="3"
								property="numeroDocumento" onkeypress="return isCampoNumerico(event);" size="9" maxlength="9" /></td>
						</tr>
						<tr>
							<td class="style1"><strong>Forma de Arrecadação:<font
								color="#FF0000">*</font></strong></td>
							<td colspan="3" class="style1">
								<html:select property="idFormaArrecadacao" tabindex="10" style="width:200px;">
								<html:option value="-1"> &nbsp; </html:option>
								<html:options collection="collectionArrecadacaoForma" property="id" labelProperty="descricao"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="style1"><strong>Conta Banc&aacute;ria:<font
								color="#FF0000">*</font></strong></td>
							<td colspan="3" class="style1"><html:text property="numeroBanco"
								size="3" maxlength="3" readonly="true"
								style="background-color:#EFEFEF; border:0; font-color: #000000" />
							<html:text property="numeroAgencia" size="7" maxlength="7"
								readonly="true"
								style="background-color:#EFEFEF; border:0; font-color: #000000" />
							<html:text property="numeroConta" size="10" maxlength="10"
								readonly="true"
								style="background-color:#EFEFEF; border:0; font-color: #000000" />
							<a href="javascript:abrirPopup('contaBancariaPesquisarAction.do?tipoPesquisa=avisoBancario');" >
							<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Conta Bancária" /></a>
							<a href="javascript:limparConta();">
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"	border="0" title="Limpar Conta Bancária" /></a>
							</td>
						</tr>
						<tr>
							<td class="style1"><strong>Data da Realiza&ccedil;&atilde;o do
							Aviso:<font color="#FF0000">*</font></strong></td>
							<td colspan="3" class="style1"><html:text tabindex="4"
								property="dataRealizacao" size="10" maxlength="10"
								onkeyup="mascaraData(this, event);" onkeypress="return isCampoNumerico(event);" /> <a
								href="javascript:abrirCalendario('InserirAvisoBancarioActionForm', 'dataRealizacao')">
							<img border="0"
								src="<bean:message key="caminho.imagens"/>calendario.gif"
								width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a> dd/mm/aaaa
							</td>
						</tr>
						<tr>
							<td class="style1"><strong>Valor da Arrecada&ccedil;&atilde;o:</strong></td>
							<td colspan="3" class="style1"><html:text tabindex="5"
								property="valorArrecadacao" size="14" maxlength="14"
								onkeyup="formataValorMonetario(this, 13)"
								onkeypress="return isCampoNumerico(event);"
								style="text-align:right;" /></td>
						</tr>
						<tr>
							<td class="style1"><strong>Valor da Devolu&ccedil;&atilde;o:</strong></td>
							<td colspan="3" class="style1"><html:text
								property="valorDevolucao" size="14" maxlength="14" tabindex="6"
								onkeyup="formataValorMonetario(this, 13)"
								onkeypress="return isCampoNumerico(event);"
								style="text-align:right;" /></td>
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
							<td colspan="3" class="style1"><font color="#FF0000">*</font>
							Campo Obrigat&oacute;rio</td>
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
							<td height="24" colspan="4" class="style1">
							<hr>
							</td>
						</tr>
						<tr>
							<td class="style1"><strong>Dedu&ccedil;&otilde;es do Aviso</strong></td>
							<td colspan="3" class="style1">
							<div align="right"><input type="button" class="bottonRightCol"
								value="Adicionar" id="botaoEndereco"
								onclick="javascript:abrirPopup('exibirPesquisarAvisoDeducoesAction.do', 285, 565);">

							</div>
							</td>
						</tr>
						<tr>
							<td height="31" colspan="4">
							<table width="100%" bgcolor="#99CCFF">
								<!--header da tabela interna -->
								<tr bordercolor="#FFFFFF" bgcolor="#99CCFF">
									<td width="10%">
									<div align="center" class="style9"><strong>Remover</strong></div>
									</td>
									<td width="21%">
									<div align="center" class="style9"><strong>Tipo de
									Dedu&ccedil;&atilde;o</strong></div>
									</td>
									<td width="21%">
									<div align="center" class="style9"><strong>Valor da
									Dedu&ccedil;&atilde;o</strong></div>
									</td>
								</tr>
								<logic:present name="colecaoAvisoDeducao">
								<%int cont = 0;%>
									<logic:iterate id="avisoDeducoes" name="colecaoAvisoDeducao">
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {%>
										<tr bgcolor="#FFFFFF">
											<%}%>

											<td height="17">
											<div align="center">
												<a href="javascript:removerDeducao(<%=""+GcomAction.obterTimestampIdObjeto((AvisoDeducoes)avisoDeducoes)%>)">
													<img src="<bean:message key='caminho.imagens'/>Error.gif" border="0" >
												</a>
											</div>
											</td>

											<td>
											<div align="center"><bean:write name="avisoDeducoes"
												property="deducaoTipo.descricaoAbreviado" /></div>
											</td>

											<td>
											<div align="center"><span class="style1"> <input
												name="valorDeducao" type="text" style="text-align:right;"
												value="<bean:write name="avisoDeducoes"
												property="valorDeducao" formatKey="money.format"/>" size="14"
												maxlength="14" readonly="true"> </span></div>
											</td>
										</tr>
									</logic:iterate>

								</logic:present>

							</table>
							</td>
						</tr>
						<tr>
							<td colspan="4" class="style1">
							<hr>
							</td>
						</tr>
					</table>
					</td>
				</tr>

			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2"><div align="right"><jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=3"/></div></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
