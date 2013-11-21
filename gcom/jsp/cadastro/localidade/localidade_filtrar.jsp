<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema"%>

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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarLocalidadeActionForm"
	dynamicJavascript="false" />

<SCRIPT LANGUAGE="JavaScript">
<!--

// Funções utilizadas pelo validate ==================================================================================================

var bCancel = false; 

function validateFiltrarLocalidadeActionForm(form) {                                                                   
   if (bCancel) 
      return true; 
   else 
      return validateCaracterEspecial(form) && validateLong(form); 
} 

 
function caracteresespeciais () { 
     this.aa = new Array("localidadeID", "Código possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.aa = new Array("localidadeNome", "Nome possui caracteres especiais.", new Function ("varName", " return this[varName];"));
} 

function IntegerValidations () { 
     this.aa = new Array("localidadeID", "Código deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
} 


function carregarGerenciaRegional(){
	form = document.forms[0];
	form.action = "/gsan/exibirFiltrarLocalidadeAction.do?objetoConsulta=2";
	form.submit();
}


function carregarUnidadeNegocio(){
	form = document.forms[0];
	form.action = "/gsan/exibirFiltrarLocalidadeAction.do?objetoConsulta=1";
	form.submit();
}


//==================================================================================================================================

/*
function validarForm(formulario){
	var preenchido = false;

	for(indice = 0; indice < formulario.elements.length; indice++){
		
		if (formulario.elements[indice].type == "text" && formulario.elements[indice].value.length > 0) {
			preenchido = true;
			break;
		}
		else if (formulario.elements[indice].type == "select-one" && formulario.elements[indice].value != "-1"){
			preenchido = true;
			break;
		}
		else if (formulario.elements[indice].type == "radio" && formulario.elements[indice].checked){
			preenchido = true;
			break;
		}
	}

	if (preenchido){
		formulario.submit();
	}
	else{
		formulario.elements[0].focus();
		alert("Informe pelo menos uma opção de seleção");
	}
	
}

*/

function validarForm(formulario){
	var objLocalidade = returnObject(formulario, "localidadeID");

	if (objLocalidade.value.length > 0 && !testarCampoValorZero(objLocalidade, "Código")){
		objLocalidade.focus();
	}
	else if (validateFiltrarLocalidadeActionForm(formulario)){
		submeterFormPadrao(formulario);
	}
}

	function verificarChecado(valor){
		form = document.forms[0];
		if(valor == "1"){
		 	form.indicadorAtualizar.checked = true;
		 }else{
		 	form.indicadorAtualizar.checked = false;
		}
	}
	
	function setaFocus(){
		var form = document.FiltrarLocalidadeActionForm;
		
		form.localidadeID.focus();
	}

//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5"
	onload="verificarChecado('${sessionScope.indicadorAtualizar}'); setaFocus();">
<html:form action="/filtrarLocalidadeAction"
	name="FiltrarLocalidadeActionForm"
	type="gcom.gui.cadastro.localidade.FiltrarLocalidadeActionForm"
	method="post"
	onsubmit="return validateFiltrarLocalidadeActionForm(this);">


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
					<td class="parabg">Filtrar Localidade</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">



				<tr>
					<td width="100%" colspan=2>
					<table width="100%" border="0">
						<tr>
							<td>Para filtrar uma localidade no sistema, informe
							os dados abaixo:</td>
							<td align="right"><input type="checkbox"
								name="indicadorAtualizar" value="1" /><strong>Atualizar</strong>
							</td>
							<logic:present scope="application" name="urlHelp">
								<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroLocalizacaoLocalidadeFiltrar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
							</logic:present>
							<logic:notPresent scope="application" name="urlHelp">
								<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
							</logic:notPresent>
						</tr>
					</table>
					</td>
				</tr>


				<tr>
					<td width="145"><strong>C&oacute;digo:</strong></td>
					<td width="470"><html:text property="localidadeID" size="5"
						maxlength="3" tabindex="0" /></td>
				</tr>
				<tr>
					<td><strong>Nome:</strong></td>
					<td><html:text property="localidadeNome" size="40" maxlength="30"
						tabindex="2" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><html:radio property="tipoPesquisa"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto<html:radio
						property="tipoPesquisa" tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
				</tr>
				<tr>
					<td><strong>Ger&ecirc;ncia Regional:</strong></td>
					<td><html:select property="gerenciaID" tabindex="3" onchange="carregarUnidadeNegocio();">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoGerenciaRegional"
							labelProperty="nome" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Unidade Negócio:</strong></td>
					<td><html:select property="idUnidadeNegocio" tabindex="3" onchange="carregarGerenciaRegional();">
					
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present name="colecaoUnidadeNegocio"> 
								<html:options collection="colecaoUnidadeNegocio"
									labelProperty="nome" property="id" />
						</logic:present>	
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Indicador de uso:</strong></td>
					<td><html:radio property="indicadorUso" value="1" /><strong>Ativo <html:radio
						property="indicadorUso" value="2" />Inativo <html:radio
						property="indicadorUso" value="3" />Todos</strong></td>
				</tr>
				<tr>
					<td><strong>Ordernar Resultado Por:</strong></td>
					<td><html:radio property="ordernarPor" value="<%=ConstantesSistema.ORDENAR_POR_DESCRICAO.toString()%>" /><strong>Nome <html:radio
						property="ordernarPor" value="<%=ConstantesSistema.ORDENAR_POR_CODIGO.toString()%>" />C&oacute;digo</td>
				</tr>
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirFiltrarLocalidadeAction.do?desfazer=S"/>'">
						<input type="button" name="Button" class="bottonRightCol"
								value="Cancelar"
								onClick="window.location.href='/gsan/telaPrincipal.do'" />
					</td>
					<td align="right"><INPUT type="button"
						onclick="validarForm(document.forms[0]);" class="bottonRightCol"
						value="Filtrar" tabindex="3" style="width: 70px;"></td>
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

