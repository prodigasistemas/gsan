<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.seguranca.acesso.usuario.UsuarioTipo"%>
<%@ page import="gcom.seguranca.acesso.usuario.UsuarioAbrangencia,gcom.cadastro.empresa.Empresa"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarUsuarioActionForm" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
window.onmousemove = verificarAcesso;verificarAcessoAbrangencia;

function submeterFormPadrao(form){
	 form.submit();
}

function botaoDesabilita(form){
  if (eval('validate' + form.name + '(form);')) {
    for (i=0; i < document.forms[0].elements.length; i++) {
      if (document.forms[0].elements[i].disabled ) {
        document.forms[0].elements[i].disabled = false;
      }
    }
    document.forms[0].submit();
  }
}

	function limparLotacao() {
		 	document.forms[0].idLotacao.value = '';
		 	document.forms[0].nomeLotacao.value = '';
	}

	function limparFuncionario() {
		 	document.forms[0].idFuncionario.value = '';
		 	document.forms[0].nomeFuncionario.value = '';
            document.forms[0].nome.value = '';
            document.forms[0].idLotacao.value = '';
            document.forms[0].nomeLotacao.value = '';
            document.forms[0].dataCadastramentoInicial.value = '';
            document.forms[0].dataCadastramentoFinal.value = '';
			document.forms[0].dataNascimento.value = '';
	        document.forms[0].cpf.value = '';
	        
	        verificarAcesso();
	}
	function limparElo () {
		 	document.forms[0].idElo.value = '';
		 	document.forms[0].nomeElo.value = '';
	}

	function limparLocalidade() {
		 	document.forms[0].idLocalidade.value = '';
		 	document.forms[0].nomeLocalidade.value = '';
	}
	

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		if ('funcionario' == tipoConsulta) {
		 	document.forms[0].idFuncionario.value = codigoRegistro;
		 	document.forms[0].action = 'exibirFiltrarUsuarioAction.do';
		 	submeterFormPadrao(document.forms[0]);
	 	} else if ('unidadeEmpresa' == tipoConsulta) { 
		 	document.forms[0].idLotacao.value = codigoRegistro;
		 	document.forms[0].action = 'exibirFiltrarUsuarioAction.do';
		 	submeterFormPadrao(document.forms[0]);
	 	}if ('elo' == tipoConsulta) {
		 	document.forms[0].idElo.value = codigoRegistro;
		 	document.forms[0].nomeElo.value = descricaoRegistro;
	 	} else if ('localidadeElo' == tipoConsulta) { 
		 	document.forms[0].idElo.value = codigoRegistro;
		 	document.forms[0].nomeElo.value = descricaoRegistro;
	 	} else if ('localidade' == tipoConsulta) { 
		 	document.forms[0].idLocalidade.value = codigoRegistro;
		 	document.forms[0].nomeLocalidade.value = descricaoRegistro;
	 	}
	}

function limpar(form){
form.action="exibirFiltrarUsuarioAction.do?limpar=sim";
submeterFormPadrao(form);
}


function trocaTipUsuario() {

var form = document.forms[0];
if(form.parmsUsuarioTipo.value != ''){
var parmsUsuarioTipo = form.parmsUsuarioTipo.value.split(';');
form.usuarioTipo.value = parmsUsuarioTipo[0];
form.indicadorFuncionario.value = parmsUsuarioTipo[1];
}else{
form.usuarioTipo.value = '';
form.indicadorFuncionario.value = '';
}

if(form.usuarioTipo.value != ''){
 form.nome.value = '';
 form.idFuncionario.value = '';
 form.nomeFuncionario.value = '';
 form.idLotacao.value = '';
 form.empresa.value = '';
 form.nomeLotacao.value = '';
 }
	verificarAcesso();

}

function verificarAcesso(){

var form = document.forms[0];

var radioBatch = form.indicadorUsuarioBatch[1].checked;
var radioInternet = form.indicadorUsuarioInternet[1].checked;

if(radioBatch == true && radioInternet == true){

	//caso a abrangência não tenha valor
	if(form.usuarioTipo.value == ''){
	 form.nome.disabled = false;
	 form.cpf.disabled = false;
	 form.dataNascimento.disabled = false;
	 form.idFuncionario.disabled = false;
	 form.idLotacao.disabled = false;
	 form.empresa.disabled = false;
	 
	}else{
		if(form.usuarioTipo.value == form.administrador.value){
		   form.nome.disabled = false;
		   form.cpf.disabled = false;
		   form.dataNascimento.disabled = false;
		   form.idFuncionario.disabled = true;
		   form.idLotacao.disabled = false;
		   form.empresa.disabled = false;
		   form.idFuncionario.value = '';
		   form.nomeFuncionario.value = '';
		}else{
			 if(form.indicadorFuncionario.value == form.indicadorFuncionarioSIM.value){
				  form.nome.disabled = true;
				  form.cpf.disabled = true;
				  form.dataNascimento.disabled = true;
				 form.idFuncionario.disabled = false;
				 form.idLotacao.disabled = true;
				 form.empresa.value = form.idEmpresaFuncionario.value;
				 form.empresa.disabled = true;
			 }else{
				  if(form.indicadorFuncionario.value != form.indicadorFuncionarioSIM.value){
					form.nome.disabled = false;
					form.cpf.disabled = false;
					form.dataNascimento.disabled = false;
					form.idFuncionario.disabled = true;
					form.idLotacao.disabled = false;
					form.empresa.disabled = false;
					form.idFuncionario.value = '';
					form.nomeFuncionario.value = '';
				  }
			  
			 }
		}
	}

	 if(form.idFuncionario.value != ''){
	 
	  form.nome.disabled = true;
	  form.cpf.disabled = true;
	  form.dataNascimento.disabled = true;
	 
	 }
}

}

function verificarData(){
	var form = document.forms[0];
	
	if(form.dataNascimento.disabled == false){
		
		abrirCalendario('FiltrarUsuarioActionForm', 'dataNascimento');
	}
}

function verificarAcessoAbrangencia(){
var form = document.forms[0];
//caso a abrangência não tenha valor
if(form.abrangencia.value == '' || form.abrangencia.value == form.estado.value){
 form.gerenciaRegional.disabled = true;
 form.unidadeNegocio.disabled = true;
 form.idElo.disabled = true;
 form.idLocalidade.disabled = true;
 form.gerenciaRegional.value = '';
 form.unidadeNegocio.value = '';
 form.idElo.value = '';
 form.nomeElo.value = '';
 form.idLocalidade.value = '';
 form.nomeLocalidade.value = '';
}else{
 if(form.abrangencia.value == form.gerenciaRegionalConstante.value){
 form.gerenciaRegional.disabled = false;
 form.unidadeNegocio.disabled = true;
 form.idElo.disabled = true;
 form.idLocalidade.disabled = true;
 form.unidadeNegocio.value = '';
 form.idElo.value = '';
 form.nomeElo.value = '';
 form.idLocalidade.value = '';
 form.nomeLocalidade.value = '';
 
 }
 if(form.abrangencia.value == form.unidadeNegocioConstante.value){
 form.unidadeNegocio.disabled = false;
 form.gerenciaRegional.disabled = true;
 form.idElo.disabled = true;
 form.idLocalidade.disabled = true;
 form.gerenciaRegional.value = '';
 form.idElo.value = '';
 form.nomeElo.value = '';
 form.idLocalidade.value = '';
 form.nomeLocalidade.value = '';
 
 }
 if(form.abrangencia.value == form.eloPolo.value){
 form.gerenciaRegional.disabled = true;
 form.unidadeNegocio.disabled = true;
 form.idElo.disabled = false;
 form.idLocalidade.disabled = true;
 form.unidadeNegocio.value = '';
 form.idLocalidade.value = '';
 form.nomeLocalidade.value = '';
 form.gerenciaRegional.value = '';
 }
 if(form.abrangencia.value == form.localidade.value){
 form.gerenciaRegional.disabled = true;
 form.unidadeNegocio.disabled = true;
 form.idElo.disabled = true;
 form.idLocalidade.disabled = false;
 form.unidadeNegocio.value = '';
 form.idElo.value = '';
 form.nomeElo.value = '';
 form.gerenciaRegional.value = '';
 }
}
}


function chamarPopup(url, tipo,altura, largura, objetoRelacionado,nomeDependencia,valorDependencia){
		if(objetoRelacionado.disabled != true){
			abrirPopup(url + "?" + "tipo=" + tipo + "&" + nomeDependencia + "=" + valorDependencia, altura, largura);
		}
			
} 
function limparCamposFuncionario(){
var form = document.forms[0];
 if(form.idFuncionario == ''){
  form.nomeFuncionario.value = '';
  form.nome.value = '';
  form.idLotacao.value = '';
  form.nomeLotacao.value = '';
  form.dataCadastramentoInicial.value = '';
  form.dataCadastramentoFinal.value = '';
 }
}
function limparNomeLotacao(){
var form = document.forms[0];
 if(form.idLotacao == ''){
  form.nomeLotacao.value = '';
 }
}

function replicaData(campoInicial,campoFinal){
     campoFinal.value = campoInicial.value;
}

function limpaCampoPesquisa(campoDescricao){
 campoDescricao.value = '';
}

function verificarChecado(valor){
form = document.forms[0];
if(valor == "1"){
 form.indicadorAtualizar.checked = true;
 }else{
 form.indicadorAtualizar.checked = false;

}

form.indicadorUsuarioBatch[1].checked = true;
form.indicadorUsuarioInternet[1].checked = true;

}

function desabilitaGrupo(){
	var radioBatch = form.indicadorUsuarioBatch[1].checked;
	var radioInternet = form.indicadorUsuarioInternet[1].checked;
	
	var grupo = form.grupo;
	if(radioBatch == false || radioInternet == false){
		grupo.disabled = true;
		grupo.style.backgroundColor = "#EFEFEF"; 
		
		for (var loop=0; loop < grupo.options.length; loop++) {
			grupo.options[loop].selected = false;
		}
		
		form.parmsUsuarioTipo.selectedIndex = 0;
		form.parmsUsuarioTipo.disabled = true;
		form.empresa.selectedIndex = 0;
		form.empresa.disabled = true;
	    form.idFuncionario.value = '';
	    form.idFuncionario.disabled = true;
		form.nome.value = '';
	    form.nome.disabled = true;
		form.cpf.value = '';
	    form.cpf.disabled = true;
	    form.dataNascimento.value = '';
	    form.dataNascimento.disabled = true;
	    form.idLotacao.disabled = true;
	    form.idLotacao.value = '';
	    form.usuarioSituacao.selectedIndex = 0;
		form.usuarioSituacao.disabled = true;
		form.loginUsuario.disabled = true;
	    form.loginUsuario.value = '';
	    form.abrangencia.selectedIndex = 0;
		form.abrangencia.disabled = true;
		form.gerenciaRegional.disabled = true;
		form.unidadeNegocio.disabled = true;
		form.idElo.disabled = true;
		form.idLocalidade.disabled = true;
		form.unidadeNegocio.value = '';
		form.idElo.value = '';
		form.nomeElo.value = '';
		form.gerenciaRegional.value = '';
		
		form.dataCadastramentoInicial.disabled = true;
		form.dataCadastramentoFinal.disabled = true;
		form.dataExpiracaoInicial.disabled = true;
		form.dataExpiracaoFinal.disabled = true;
		
		document.getElementById('dataCadInicial').style.display = "none";
	    document.getElementById('dataCadFinal').style.display = "none";
	    document.getElementById('dataExpFinal').style.display = "none";
	    document.getElementById('dataExpInicial').style.display = "none";
	    
	}else{
		grupo.disabled = false;
		grupo.style.backgroundColor = "#FFFFFF"; 
		
		form.parmsUsuarioTipo.disabled = false;
		form.empresa.disabled = false;
	    form.idFuncionario.disabled = false;
	    form.nome.disabled = false;
	    form.cpf.disabled = false;
	    form.dataNascimento.disabled = false;
	    form.idLotacao.disabled = false;
		form.usuarioSituacao.disabled = false;
		form.loginUsuario.disabled = false;
		form.abrangencia.disabled = false;
		form.dataCadastramentoInicial.disabled = false;
		form.dataCadastramentoFinal.disabled = false;
		form.dataExpiracaoInicial.disabled = false;
		form.dataExpiracaoFinal.disabled = false;
		document.getElementById('dataCadInicial').style.display = "";
	    document.getElementById('dataCadFinal').style.display = "";
	    document.getElementById('dataExpFinal').style.display = "";
	    document.getElementById('dataExpInicial').style.display = "";
	}
	
}
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="verificarAcesso();verificarAcessoAbrangencia();verificarChecado('${sessionScope.indicadorAtualizar}');window.document.forms[0].gerenciaRegional.onclick = null;">
<html:form action="/filtrarUsuarioAction" method="post"
	onsubmit="return validateFiltrarUsuarioActionForm(this);">


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
					<td class="parabg">Filtrar Usuário</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table border="0" width="100%">
				<tr>
					<td colspan="2">
					<table width="100%">
						<tr>
							<td width="80%">Para filtrar o(s) usuário(s), informe os dados
							abaixo:	<input type="hidden" name="indicadorFuncionarioSIM"
							value="<%=UsuarioTipo.INDICADOR_FUNCIONARIO%>" /> 
							<input
							type="hidden" name="administrador"
							value="<%=UsuarioTipo.USUARIO_TIPO_ADMINISTRADOR%>" />
							<input type="hidden"
							name="idEmpresaFuncionario" value="<%=Empresa.INDICADOR_EMPRESA_PRINCIPAL%>" />
							<html:hidden
							property="usuarioTipo" /> 
							<html:hidden
							property="indicadorFuncionario" />
								<input type="hidden"
								name="estado" value="<%=UsuarioAbrangencia.ESTADO%>" /> <input
								type="hidden" name="gerenciaRegionalConstante"
								value="<%=UsuarioAbrangencia.GERENCIA_REGIONAL%>" /> <input
								type="hidden" name="eloPolo"
								value="<%=UsuarioAbrangencia.ELO_POLO%>" /> <input
								type="hidden" name="localidade"
								value="<%=UsuarioAbrangencia.LOCALIDADE%>" />
								<input type="hidden"
									name="unidadeNegocioConstante" value="<%=UsuarioAbrangencia.UNIDADE_NEGOCIO_INT%>" />
						</td>
							<td align="right"><input type="checkbox"
								name="indicadorAtualizar" value="1" /><strong>Atualizar</strong>
							<p>&nbsp;</p>
							</td>
						</tr>
					</table>
				</tr>
				<tr>
					<td width="26%"><strong>Tipo de Usuário:</strong></td>
					<td width="74%"><html:select property="parmsUsuarioTipo"
							onchange="javascript:trocaTipUsuario()">
							<bean:define name="FiltrarUsuarioActionForm" property="parmsUsuarioTipo" id="parmsUsu"/>
							<option value=""></option>
							<logic:notEmpty name="colecaoUsuarioTipo">
								<logic:iterate name="colecaoUsuarioTipo" id="usuarioTipo">
								<option <%=(parmsUsu.toString().equalsIgnoreCase(((UsuarioTipo)usuarioTipo).getId() + ";" + ((UsuarioTipo)usuarioTipo).getIndicadorFuncionario()))?" selected ": "" %> 
								value="<bean:write name="usuarioTipo" property="id"/>;<bean:write name="usuarioTipo" property="indicadorFuncionario"/>">
								<bean:write name="usuarioTipo" property="descricao"/>
								</option>
								</logic:iterate>
							</logic:notEmpty>
						</html:select></td>
				</tr>
				<tr>
					<td width="26%"><strong>Empresa:</strong></td>
					<td width="74%"><html:select property="empresa">
						<option value=""></option>
						<html:options name="request" collection="colecaoEmpresa"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td width="26%"><strong>Matrícula do Funcionário:</strong></td>
					<td width="74%"><html:text maxlength="9" property="idFuncionario"
						size="9"
						onkeypress="javascript:return validaEnter(event, 'exibirFiltrarUsuarioAction.do', 'idFuncionario');"
						onkeyup="limparCamposFuncionario();limpaCampoPesquisa(document.forms[0].nomeFuncionario);" />
					<a
						href="javascript:chamarPopup('exibirFuncionarioPesquisar.do','funcionario', 495, 300,document.forms[0].idFuncionario,'','');"><img
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
						height="21" alt="Pesquisar" border="0"></a> <logic:equal
						property="funcionarioNaoEncontrada"
						name="FiltrarUsuarioActionForm" value="true">
						<html:text property="nomeFuncionario" size="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:equal> <logic:equal property="funcionarioNaoEncontrada"
						name="FiltrarUsuarioActionForm" value="false">
						<html:text property="nomeFuncionario" size="40" maxlength="40"
							readonly="true"
							style="border: 0pt none ; background-color: rgb(239, 239, 239);" />
					</logic:equal> <a href="javascript:limparFuncionario();"> <img
						border="0" src="imagens/limparcampo.gif" height="21" width="23"> </a></td>
				</tr>
				<tr>
					<td width="26%"><strong>Nome do Usuário:</strong></td>
					<td width="74%"><html:text property="nome" size="50" maxlength="50" /></td>
				</tr>
				<tr>
					<td width="26%"><strong>Número do CPF:</strong></td>
					<td width="74%"><html:text 
						property="cpf" 
						size="12"
						maxlength="11" />
					</td>
				</tr>
				<tr>
					<td height="10"><strong>Data de Nascimento:</strong></td>
					<td><html:text property="dataNascimento" 
						size="11"
						maxlength="10" 
						tabindex="4"
						onkeyup="mascaraData(this, event);" /> <a
						href="javascript:verificarData()">
					<img border="0"
						src="<bean:message 
						key='caminho.imagens'/>calendario.gif"
						width="20" 
						border="0" 
						align="absmiddle" 
						alt="Exibir Calendário" /></a>
					dd/mm/aaaa
					</td>
				</tr>
				<tr>
					<td width="26%"><strong>Unidade de Lota&ccedil;&atilde;o:</strong></td>
					<td width="74%"><html:text maxlength="9" property="idLotacao"
						size="9"
						onkeypress="javascript:return validaEnter(event, 'exibirFiltrarUsuarioAction.do', 'idLotacao'); "
						onkeyup="limparNomeLotacao();limpaCampoPesquisa(document.forms[0].nomeLotacao);" />
					<a
						href="javascript:chamarPopup('exibirPesquisarUnidadeEmpresaAction.do','unidadeEmpresa', 495, 300,document.forms[0].idLotacao,'','');"><img
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
						height="21" alt="Pesquisar" border="0"></a> <logic:equal
						property="lotacaoNaoEncontrada" name="FiltrarUsuarioActionForm"
						value="false">
						<html:text name="FiltrarUsuarioActionForm" property="nomeLotacao"
							size="40" maxlength="40" readonly="true"
							style="border: 0pt none ; background-color: rgb(239, 239, 239);" />
					</logic:equal> <logic:equal property="lotacaoNaoEncontrada"
						name="FiltrarUsuarioActionForm" value="true">
						<html:text name="FiltrarUsuarioActionForm" property="nomeLotacao"
							size="40" maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:equal> <a href="javascript:limparLotacao();"> <img
						border="0" src="imagens/limparcampo.gif" height="21" width="23"> </a></td>
				</tr>
				<tr>
					<td width="26%"><strong>Situação do Usuário:</strong></td>
					<td width="74%"><html:select property="usuarioSituacao">
						<option value=""></option>
						<html:options name="request" collection="colecaoUsuarioSituacao"
							labelProperty="descricaoUsuarioSituacao" property="id" />
					</html:select></td>
				</tr>
				
				<tr>
					<td width="26%"><strong>Login:</strong></td>
					<td width="74%"><html:text property="loginUsuario" size="11"
						maxlength="11" style="text-transform: none;"/></td>
				</tr>
				
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td width="26%"><strong>Abrangência do Acesso:</strong></td>
					<td width="74%"><html:select property="abrangencia" 
						onchange="javascript:verificarAcessoAbrangencia();">
						<option value="">&nbsp;</option>
						<html:options name="request" collection="colecaoUsuarioAbrangencia"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td width="26%"><strong>Gerência Regional:</strong></td>
					<td width="74%"><html:select property="gerenciaRegional" onmousedown="teste1(document.forms[0].gerenciaRegional)">
						<option value="">&nbsp;</option>
						<html:options name="request" collection="colecaoGerenciaRegional"
							labelProperty="nome" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td width="26%"><strong>Unidade Negócio:</strong></td>
					<td width="74%"><html:select property="unidadeNegocio">
						<option value="">&nbsp;</option>
						<html:options name="request" collection="colecaoUnidadeNegocio"
							labelProperty="nome" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td width="26%"><strong>Localidade Pólo:</strong></td>
					<td width="74%"><html:text maxlength="9"
						name="FiltrarUsuarioActionForm" property="idElo" size="9"
						onkeypress="javascript:limparLocalidade();validaEnterComMensagem(event, 'exibirFiltrarUsuarioAction.do', 'idElo','Elo Pólo');"
						onkeyup="limpaCampoPesquisa(document.forms[0].nomeElo);" /> <a
						href="javascript:chamarPopup('exibirPesquisarEloAction.do', 'elo',  400, 800, document.forms[0].idElo,'','');">
					<img width="23" border="0" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						style="cursor:hand;" alt="Pesquisar" /></a> <logic:equal
						name="FiltrarUsuarioActionForm" property="eloNaoEncontrada"
						value="false">
						<html:text property="nomeElo" size="40" readonly="true"
							style="background-color:#EFEFEF; border:0" />
					</logic:equal> <logic:equal name="FiltrarUsuarioActionForm"
						property="eloNaoEncontrada" value="true">
						<html:text property="nomeElo" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" maxlength="40" />
					</logic:equal> <a href="javascript:limparElo();"> <img border="0"
						src="imagens/limparcampo.gif" height="21" width="23"> </a></td>
				</tr>
				<tr>
					<td width="26%"><strong>Localidade:</strong></td>
					<td width="74%"><html:text property="idLocalidade" size="5"
						maxlength="3"
						onkeypress="return validaEnterComMensagem(event, 'exibirFiltrarUsuarioAction.do', 'idLocalidade', 'Localidade');"
						onkeyup="limpaCampoPesquisa(document.forms[0].nomeLocalidade);" />
					<a
						href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'localidade',  400, 800,document.forms[0].idLocalidade,'idElo',document.forms[0].idElo.value);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						style="cursor:hand;" alt="Pesquisar" /></a> <logic:equal
						name="FiltrarUsuarioActionForm" property="localidadeNaoEncontrada"
						value="false">
						<html:text property="nomeLocalidade" size="40" readonly="true"
							style="background-color:#EFEFEF; border:0" />
					</logic:equal> <logic:equal name="FiltrarUsuarioActionForm"
						property="localidadeNaoEncontrada" value="true">
						<html:text property="nomeLocalidade" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" maxlength="40" />
					</logic:equal> <a href="javascript:limparLocalidade();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td width="26%"><strong>Grupo:</strong></td>
					<td width="74%"><html:select multiple="true" size="3"
						name="FiltrarUsuarioActionForm" property="grupo">
						<option value="">&nbsp;</option>
						<logic:notEmpty name="colecaoGrupo">
							<html:options name="request" collection="colecaoGrupo"
								labelProperty="descricao" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				
				<tr>
					<td width="26%">
						<strong>Indicador de usuário para rotina batch:</strong>
					</td>
					<td width="74%">
						Sim <html:radio property="indicadorUsuarioBatch" value="1" onchange="javascript:desabilitaGrupo();" />
						Não <html:radio property="indicadorUsuarioBatch" value="2"  onchange="javascript:desabilitaGrupo();"/>
					</td>
				</tr>
					
				<tr>
					<td width="26%">
						<strong>Indicador de usuário para internet:</strong>
					</td>
					<td width="74%">
						Sim <html:radio property="indicadorUsuarioInternet" value="1" onchange="javascript:desabilitaGrupo();" />
						Não <html:radio property="indicadorUsuarioInternet" value="2" onchange="javascript:desabilitaGrupo();"/>
					</td>
				</tr>

				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td width="26%"><strong>Período de Cadastramento de Acesso:</strong></td>
					<td width="74%"><html:text
						onkeyup="mascaraData(this, event);replicaData(document.forms[0].dataCadastramentoInicial,document.forms[0].dataCadastramentoFinal);"
						property="dataCadastramentoInicial" size="10" maxlength="10" /> <a id="dataCadInicial"
						href="javascript:abrirCalendario('FiltrarUsuarioActionForm', 'dataCadastramentoInicial');replicaData(document.forms[0].dataCadastramentoInicial,document.forms[0].dataCadastramentoFinal);">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a>

					&nbsp; <html:text onkeyup="mascaraData(this, event);"
						property="dataCadastramentoFinal" size="10" maxlength="10" /> <a id="dataCadFinal"
						href="javascript:abrirCalendario('FiltrarUsuarioActionForm', 'dataCadastramentoFinal')"><img
						border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa</td>
				</tr>
				<tr>
					<td width="26%"><strong>Período de Expiração de Acesso:</strong></td>
					<td width="74%"><html:text
						onkeyup="mascaraData(this, event);replicaData(document.forms[0].dataExpiracaoInicial,document.forms[0].dataExpiracaoFinal);"
						property="dataExpiracaoInicial" size="10" maxlength="10" /> <a id="dataExpInicial"
						href="javascript:abrirCalendario('FiltrarUsuarioActionForm', 'dataExpiracaoInicial')">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a>

					&nbsp; <html:text onkeyup="mascaraData(this, event);"
						property="dataExpiracaoFinal" size="10" maxlength="10" /> <a id="dataExpFinal"
						href="javascript:abrirCalendario('FiltrarUsuarioActionForm', 'dataExpiracaoFinal');replicaData(document.forms[0].dataExpiracaoInicial,document.forms[0].dataExpiracaoFinal);"><img
						border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa</td>

				</tr>

				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>

				<tr>
					<td colspan="2">
					<table border="0" width="100%">
						<tr>

							<td valign="top"><input name="button" type="button"
								class="bottonRightCol" value="Limpar"
								onclick="limpar(document.forms[0]);">&nbsp;</td>
							<td valign="top">
							<div align="right"><input name="botaoInserir" type="button"
								class="bottonRightCol" value="Filtrar" tabindex="19" onclick="javascript:botaoDesabilita(document.forms[0]);"></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
