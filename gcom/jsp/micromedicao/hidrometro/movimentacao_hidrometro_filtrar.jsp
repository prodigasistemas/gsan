<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="gcom.util.ConstantesSistema" %>

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
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="HidrometroActionForm" dynamicJavascript="false" />
<script type="text/javascript" language="Javascript">
<!-- Begin
var bCancel = false;
var tipoLocal;
function validateHidrometroActionForm(form) {
      if (bCancel)
    return true;
      else
     return  validateCaracterEspecial(form) 
     		 && validateLong(form) 
     		 && testarCampoValorZero(form.localArmazenagemOrigem, 'Local de Armazenagem Origem') 
     		 && testarCampoValorZero(form.localArmazenagemDestino, 'Local de Armazenagem Destino')
     		 && testarCampoValorZero(form.usuario, 'Usuário')
     		 && validateDate(form) ;
 }

function DateValidations () {
	this.aa = new Array("dataMovimentacaoInicial", "Data Movimentação Inicial inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
	this.ab = new Array("dataMovimentacaoFinal", "Data Movimentação Final inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
}

function caracteresespeciais () {
   this.aa = new Array("dataMovimentacaoInicial", "Data Movimentação Inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
   this.ab = new Array("dataMovimentacaoFinal", "Data Movimentação Final possui caracteres especiais.", new Function ("varName", " return this[varName];"));
   this.ac = new Array("horaMovimentacaoInicial", "Hora Movimentação Inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
   this.ad = new Array("horaMovimentacaoFinal", "Hora Movimentação Final possui caracteres especiais.", new Function ("varName", " return this[varName];"));
   this.ae = new Array("localArmazenagemOrigem", "Local de Armazenagem Origem possui caracteres especiais.", new Function ("varName", " return this[varName];"));
   this.af = new Array("localArmazenagemDestino", "Local de Armazenagem Destino possui caracteres especiais.", new Function ("varName", " return this[varName];"));
   this.ag = new Array("usuario", "Usuário possui caracteres especiais.", new Function ("varName", " return this[varName];"));
}

function IntegerValidations () {
   this.aa = new Array("localArmazenagemOrigem", "Local de Armazenagem Origem deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
   this.ab = new Array("localArmazenagemDestino", "Local de Armazenagem Destino deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
   this.ac = new Array("usuario", "Usuário deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.HidrometroActionForm;
    
    if (tipoConsulta == 'hidrometroLocalArmazenagem' && tipoLocal == 'Destino') {
      
      form.localArmazenagemDestino.value = codigoRegistro;
      form.localArmazenagemDescricaoDestino.value = descricaoRegistro;
      form.localArmazenagemDescricaoDestino.style.color = "#000000";
      
    } else if (tipoConsulta == 'hidrometroLocalArmazenagem' && tipoLocal == 'Origem') {
    
      form.localArmazenagemOrigem.value = codigoRegistro;
      form.localArmazenagemDescricaoOrigem.value = descricaoRegistro;
      form.localArmazenagemDescricaoOrigem.style.color = "#000000";
      
    } else if (tipoConsulta == 'usuario') {
    
      form.usuario.value = codigoRegistro;
      form.nomeUsuario.value = descricaoRegistro;
      form.nomeUsuario.style.color = "#000000";
    }
}

function limparPesquisaLocalArmazenagem(tipo) {
	var form = document.HidrometroActionForm;
	if(tipo == 'Origem')
	{
	    form.localArmazenagemOrigem.value = "";
	    form.localArmazenagemDescricaoOrigem.value = "";
	    form.localArmazenagemOrigem.focus();
    }
    else
	{
	    form.localArmazenagemDestino.value = "";
	    form.localArmazenagemDescricaoDestino.value = "";
	    form.localArmazenagemDestino.focus();
    }
}

function limparUsuario() {
    var form = document.HidrometroActionForm;
    form.usuario.value = "";
    form.nomeUsuario.value = "";
    form.usuario.focus();
}

function validarForm(form){
  if (validateHidrometroActionForm(form)){
	var DATA_ATUAL = document.getElementById("DATA_ATUAL").value;
	if (comparaData(form.dataMovimentacaoInicial.value, ">", DATA_ATUAL ))
	{
		alert ("Data da Movimentação Inicial posterior à data corrente.");
		form.dataMovimentacaoInicial.focus();
	}
	else if (comparaData(form.dataMovimentacaoFinal.value, ">", DATA_ATUAL ))
	{
		alert ("Data da Movimentação Final posterior à data corrente.");
		form.dataMovimentacaoFinal.focus();
	}
	else if (comparaData(form.dataMovimentacaoInicial.value, ">", form.dataMovimentacaoFinal.value ))
	{
		alert ("Data da Movimentação Inicial maior que Data da Movimentação Final");
		form.dataMovimentacaoInicial.focus();
	}
	else if (form.horaMovimentacaoInicial.value != "" && !validarHora(form.horaMovimentacaoInicial.value))
	{
		form.horaMovimentacaoInicial.focus();
	}
	else if (form.horaMovimentacaoFinal.value != "" && !validarHora(form.horaMovimentacaoFinal.value))
	{
		form.horaMovimentacaoFinal.focus();
	}else if((form.faixaInicial.value == "" || form.faixaFinal.value == "") && form.fixo.value != ""){
    	alert("O preenchimento dos campos Faixa Inicial e Final da Numeração dos Hidrômetros é obrigatório, caso Fixo seja informado.");
    	form.faixaInicial.focus();
   	}
	else
	{
		submeterFormPadrao(form);
	}
  }
}
function limparForm() {
    var form = document.HidrometroActionForm;
    form.dataMovimentacaoInicial.value = "";
    form.dataMovimentacaoFinal.value = "";
    form.horaMovimentacaoInicial.value = "";
    form.horaMovimentacaoFinal.value = "";
    form.motivoMovimentacao.value = "-1";
    form.localArmazenagemOrigem.value = "";
    form.localArmazenagemDestino.value = "";
    form.localArmazenagemDescricaoOrigem.value = "";
	form.localArmazenagemDescricaoDestino.value = "";
	form.usuario.value = "";
	form.nomeUsuario.value = "";
	form.dataMovimentacaoInicial.focus();
}

function pesquisarLocal(tipo){
 	tipoLocal = tipo;
	abrirPopup('exibirPesquisarLocalArmazenagemHidrometroAction.do', 250, 495);
}
-->
</script>
</head>
<body leftmargin="5" topmargin="5">
<html:form action="/filtrarMovimentacaoHidrometroAction.do"
	name="HidrometroActionForm"
	type="gcom.gui.micromedicao.hidrometro.HidrometroActionForm"
	method="post" onsubmit="return validateHidrometroActionForm(this);"
	focus="dataMovimentacaoInicial">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="115" valign="top" class="leftcoltext">
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
			<td valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img
						src="<bean:message key="caminho.imagens"/>parahead_left.gif"
						border="0" /></td>
					<td class="parabg">Filtrar Movimentação de Hidr&ocirc;metro</td>
					<td width="11" valign="top"><img
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2"> 
						<table width="100%" border="0">
							<tr>
								<td> 
									Para filtrar a(s) movimentação(ões) de hidr&ocirc;metro(s), informe os dados abaixo:
								</td>
								<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=hidrometroMovimentacaoConsultar-1-filtro', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>			
							</tr>
						</table>
					</td>
				</tr>			
				<tr><td><br></td></tr>
				<tr>
					<td colspan="2"><strong>Numeração dos Hidr&ocirc;metros:</strong></td>
				</tr>
				<tr>
					<td><strong>Fixo:</strong></td>
					<td>
						<html:text property="fixo" size="10" maxlength="10"  />
					</td>
				</tr>
				<tr>
					<td><strong>Faixa:</strong></td>
					<td>
						<html:text property="faixaInicial" size="10" maxlength="10"  />
						<html:text property="faixaFinal" size="10" maxlength="10"  />
					</td>
				</tr>
				<tr>
					<td height="24" colspan="2" class="style1">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong>Data Movimenta&ccedil;&atilde;o Inicial:</strong></td>
					<td><html:text property="dataMovimentacaoInicial" size="10" maxlength="10" onkeyup="mascaraData(this, event);" />
					<a href="javascript:abrirCalendario('HidrometroActionForm', 'dataMovimentacaoInicial')">
					<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
					&nbsp;<font size="2">dd/mm/aaaa</font>
					<input type="hidden" id="DATA_ATUAL" value="${requestScope.dataAtual}"/>
					</td>
				</tr>
				<tr>
					<td><strong>Data Movimenta&ccedil;&atilde;o Final:</strong></td>
					<td><html:text property="dataMovimentacaoFinal" size="10" maxlength="10" onkeyup="mascaraData(this, event);" />
					<a href="javascript:abrirCalendario('HidrometroActionForm', 'dataMovimentacaoFinal')">
					<img border="0"	src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
					&nbsp;<font size="2">dd/mm/aaaa</font>
					</td>
				</tr>
				<tr>
					<td><strong>Hora Movimenta&ccedil;&atilde;o Inicial:</strong></td>
					<td><html:text maxlength="5" property="horaMovimentacaoInicial"
						size="5" onkeyup="mascaraHoraMensagem(this, event, 'Hora Movimenta&ccedil;&atilde;o Inicial');" />&nbsp;<font size="2">hh:mm</font></td>
				</tr>
				<tr>
					<td><strong>Hora Movimenta&ccedil;&atilde;o Final:</strong></td>
					<td><html:text maxlength="5" property="horaMovimentacaoFinal"
						size="5" onkeyup="mascaraHoraMensagem(this, event, 'Hora Movimenta&ccedil;&atilde;o Final');" />&nbsp;<font size="2">hh:mm</font></td>
				</tr>
				<tr>
					<td><strong>Motivo Movimentação:</strong></td>
					<td><html:select property="motivoMovimentacao">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoHidrometroMotivoMovimentacao"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
         		<tr>
					<td><strong>Local de Armazenagem Origem:</strong></td>
					<td>
						<html:text property="localArmazenagemOrigem" size="4" maxlength="3"
							onkeypress="validaEnter(event, 'exibirFiltrarMovimentacaoHidrometroAction.do?objetoConsulta=1&tipo=1', 'localArmazenagemOrigem');" />
						<a href="javascript:pesquisarLocal('Origem');">
							<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0"
								width="23" height="21" style="cursor:hand;" alt="Pesquisar"></a>
						<logic:present name="corLocalArmazenagemOrigem">
							<logic:equal name="corLocalArmazenagemOrigem" value="exception">
								<html:text property="localArmazenagemDescricaoOrigem" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="corLocalArmazenagemOrigem" value="exception">
								<html:text property="localArmazenagemDescricaoOrigem" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						<logic:notPresent name="corLocalArmazenagemOrigem">
							<logic:empty name="HidrometroActionForm"
								property="localArmazenagemOrigem">
								<html:text property="localArmazenagemDescricaoOrigem" size="45"
									value="" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="HidrometroActionForm"
								property="localArmazenagemOrigem">
								<html:text property="localArmazenagemDescricaoOrigem" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent> 
						<a href="javascript:limparPesquisaLocalArmazenagem('Origem');"> 
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /></a>
					</td>
				</tr>
				<tr>
					<td><strong>Local de Armazenagem Destino:</strong></td>
					<td>
						<html:text property="localArmazenagemDestino" size="4" maxlength="3"
							onkeypress="validaEnter(event, 'exibirFiltrarMovimentacaoHidrometroAction.do?objetoConsulta=1&tipo=2', 'localArmazenagemDestino');" />
						<a href="javascript:pesquisarLocal('Destino');">
						<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0"
							width="23" height="21" style="cursor:hand;" alt="Pesquisar"></a>
						<logic:present name="corLocalArmazenagemDestino">
							<logic:equal name="corLocalArmazenagemDestino" value="exception">
								<html:text property="localArmazenagemDescricaoDestino" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="corLocalArmazenagemDestino" value="exception">
								<html:text property="localArmazenagemDescricaoDestino" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						<logic:notPresent name="corLocalArmazenagemDestino">
							<logic:empty name="HidrometroActionForm"
								property="localArmazenagemDestino">
								<html:text property="localArmazenagemDescricaoDestino" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="HidrometroActionForm"
								property="localArmazenagemDestino">
								<html:text property="localArmazenagemDescricaoDestino" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent> 
						<a href="javascript:limparPesquisaLocalArmazenagem('Destino');"> 
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /></a>
					</td>
				</tr>
				<tr>
					<td><strong>Usuário:</strong></td>
					<td>
						<html:text property="usuario" size="9" maxlength="9"
							onkeypress="validaEnter(event, 'exibirFiltrarMovimentacaoHidrometroAction.do', 'usuario');" />
						<a href="javascript:abrirPopup('exibirUsuarioPesquisar.do?limpaForm=OK', 300, 350);">
						<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0"
							width="23" height="21" style="cursor:hand;"	alt="Pesquisar"></a> 
						<logic:present name="corUsuario">
							<logic:equal name="corUsuario" value="exception">
								<html:text property="nomeUsuario" size="40"
									readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="corUsuario" value="exception">
								<html:text property="nomeUsuario" size="40"
									readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						<logic:notPresent name="corUsuario">
							<logic:empty name="HidrometroActionForm" property="nomeUsuario">
								<html:text property="nomeUsuario" size="40"
									value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="HidrometroActionForm" property="nomeUsuario">
								<html:text property="nomeUsuario" size="40"
									readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent> 

						<a href="javascript:limparUsuario();"> 
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /></a>
					</td>
				</tr>
				<tr><td><br></td></tr>
				<tr>
					<td>
						<input type="button" class="bottonRightCol" value="Limpar" tabindex="13"
							onclick="limparForm();" />
					</td>
					<td align="right">
					<gsan:controleAcessoBotao name="Button" value="Filtrar"
							  onclick="javascript:validarForm(document.forms[0]);" url="filtrarMovimentacaoHidrometroAction.do"/><!-- 
						<input type="button" class="bottonRightCol" value="Filtrar" tabindex="13"
							onclick="validarForm(document.forms[0]);" /> -->
					</td>
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
