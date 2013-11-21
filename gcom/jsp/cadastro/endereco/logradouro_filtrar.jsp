<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarActionForm"
	dynamicJavascript="false" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript">
<!-- Begin

 var bCancel = false;

    function validatePesquisarActionForm(form) {
        if (bCancel)
      return true;
        else
       return testarCampoValorZero(document.PesquisarActionForm.idMunicipioFiltro, 'Município') && validateLong(form) && validateCaracterEspecial(form);
    }

	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg){
	
		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo+"&indicadorUsoTodos=1", altura, largura);
		}
		else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}
			else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo+"&indicadorUsoTodos=1", altura, largura);
			}
		}
	}

    function IntegerValidations  () {
     this.ab = new Array("idMunicipioFiltro", "Município deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("idBairro", "Bairro deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("idLogradouro", "Código deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("cep", "Cep deve somente conter números positivos.", new Function ("varName", " return this[varName];"));

    }



    function caracteresespeciais  () {

     this.aa = new Array("idMunicipioFiltro", "Município possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("idLogradouro", "Código possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("nomeLogradouro", "Nome possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("nomePopularLogradouro", "Nome Popular possui caracteres especiais.", new Function ("varName", " return this[varName];"));

    }



function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

	var form = document.forms[0];

	if (tipoConsulta == 'bairro') {
      form.idBairro.value = codigoRegistro;
	  form.nomeBairro.value = descricaoRegistro;
	  form.nomeBairro.style.color = "#000000";
	}
}


function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.PesquisarActionForm;

    if (tipoConsulta == 'municipio') {
      form.idMunicipioFiltro.value = codigoRegistro;
      form.nomeMunicipio.value = descricaoRegistro;
      form.nomeMunicipio.style.color = "#000000";

    }
    
    if (tipoConsulta == 'cep') {
      form.cep.value = codigoRegistro;
      form.retornoCep.value = descricaoRegistro;
      form.retornoCep.style.color = "#000000";

    }
    
    if (tipoConsulta == 'bairro') {
      form.idBairro.value = codigoRegistro;
      form.nomeBairro.value = descricaoRegistro;
      form.nomeBairro.style.color = "#000000";

    }
  }
  
  function verificaMunicipio(){
    var form = document.PesquisarActionForm;
    
    if (form.nomeMunicipio.value == "" || form.nomeMunicipio.value == "Município inexistente"){
    	alert ('Informe Municipio');
    	form.idBairro.value = "";
    	form.idMunicipioFiltro.focus();
    	form.idMunicipioFiltro.value = "";
    	
    }
  }
  
 function limparPesquisaMunicipio() {
    var form = document.PesquisarActionForm;

    form.idMunicipioFiltro.value = "";
    form.nomeMunicipio.value = "";
    form.idBairro.value = "";
    form.nomeBairro.value = "";
    
    limparPesquisaCep();
    form.idMunicipioFiltro.focus();

 }
 
 function limparPesquisaBairro() {
    var form = document.PesquisarActionForm;

    form.idBairro.value = "";
    form.nomeBairro.value = "";
    
	form.idBairro.focus();

 }
 
 function limparPesquisaCep() {
    var form = document.PesquisarActionForm;

    form.cep.value = "";
    form.retornoCep.value = "";

 }

function limpaNomeCEP(){
	var form = document.PesquisarActionForm;

	form.retornoCep.value = "";
}

function limpaNomeMunicipio(){
	var form = document.PesquisarActionForm;

	form.nomeMunicipio.value = "";
    form.idBairro.value = "";
    form.nomeBairro.value = "";
}

function limpaNomeBairro(){
	var form = document.PesquisarActionForm;

	form.nomeBairro.value = "";
}


function validarForm(form){

	var municipio = form.idMunicipioFiltro;
	var bairro = form.idBairro;
	
	if(validatePesquisarActionForm(form)){
    	
    	if (bairro.value.length > 0 && municipio.value.length < 1){
    		alert("Informe Município");
    		municipio.focus();
    	}
    	else if (performanceJSP()){
    		submeterFormPadrao(form);
    	}
    }
}


//Colocado por Raphael Rossiter em 01/02/2007
function performanceJSP(){

	form = document.forms[0];
	
	/**
	 * Objetos em dupla de acordo com as dependências que vc queira gerar
	 *
	 * EX: var camposDependencia = [[form.campo1, form.campo2],[form.campo1, form.campo3],[form.campo3, form.campo4]];
	 */
	var camposDependencia = [[]];
		
	/**
	 * Mensagens sincronizadas com as duplas informadas no objeto camposDependencia
	 *
	 * EX: var camposDependencia = [[form.campo1, form.campo2],[form.campo1, form.campo3],[form.campo3, form.campo4]];
	 *     var msgDependecia = ["nomeCampo2", "nomeCampo3", "nomeCampo4"];
	 */
	var msgDependecia = [];
	
	/**
	 * Objetos que poderão já vir informados ou serem informados pelo usuário mas que não serão
	 * considerados como parâmetros informados (INDICADOR_USO).
	 *
	 * EX: var camposExclusao = [form.indicadorUso];
	 */
	var camposExclusao = [form.ultimoacesso, form.atualizar, form.tipoPesquisa[0], form.tipoPesquisaPopular[0], form.indicadorUso[0]];
	
		
	/**
	 * Caso o objeto camposDependencia seja informado, o objeto msgDependecia será obrigatório e visse-versa.
	 * Caso o objeto camposExclusao seja informado, o formulário será obrigatório e visse-versa.
	 */
	retorno = dependenciaPerformance(null, null, camposExclusao, form);
	
	
	return retorno;
}
function verificarImpLogradouro(){
	var form = document.PesquisarActionForm;
	
	if(form.indicadorImportanciaLogradouro.value=="1") {
		form.atualizar.checked = false;
		form.atualizar.disabled = true;
	}

}
	

-->
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:verificarImpLogradouro();setarFoco('${requestScope.nomeCampo}'); ">
<html:form action="/filtrarLogradouroAction.do" method="post"
	onsubmit="return validatePesquisarActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<html:hidden property="indicadorImportanciaLogradouro" />
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
					
						<td class="parabg">
							<logic:present name="indicadorImportanciaLogradouro" >
								Filtrar Importância Logradouro
							</logic:present>
							<logic:notPresent name="indicadorImportanciaLogradouro" >
								Filtrar Logradouro
							</logic:notPresent>
						</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">
					<table width="100%">
						<tr>
							<td>Para manter o(s) logradouro(s), informe os dados
							abaixo:</td>
							<td width="74">
								<input name="atualizar" type="checkbox" checked="checked"
									value="1">
							<strong>Atualizar</strong></td>
							
							
						</tr>
					</table>
					</td>
				</tr>

	
				<tr>
					<td><strong>Munic&iacute;pio:</strong></td>
					<td>
						<html:text  maxlength="4" 
						  			property="idMunicipioFiltro" 
						  			size="4"
									tabindex="1" 
									onkeyup="javascript:limpaNomeMunicipio();"
									onkeypress="javascript:validaEnter(event, 'exibirFiltrarLogradouroAction.do', 'idMunicipioFiltro');return isCampoNumerico(event);" />
							<a href="javascript:abrirPopup('exibirPesquisarMunicipioAction.do?indicadorUsoTodos=1');">
								<img width="23" 
									 height="21" 
									 border="0"
									 src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									 title="Pesquisar Municipio" />
							</a> 
							<logic:present name="idMunicipioNaoEncontrado" scope="request">
								<html:text  maxlength="40" 
											property="nomeMunicipio" 
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											size="40" />
							</logic:present> 
							<logic:notPresent name="idMunicipioNaoEncontrado" scope="request">
								<html:text maxlength="40" property="nomeMunicipio" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									size="40" />
							</logic:notPresent> 
							<a href="javascript:limparPesquisaMunicipio();">
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" title="Apagar" />
							</a>
					</td>
				</tr>
				<TR>
					<TD><strong>Bairro:</strong></TD>
					<TD>
						<html:text property="idBairro" 
								   size="5" 
								   maxlength="4"
								   tabindex="2" 
								   onkeyup="limpaNomeBairro();" 
								   onkeypress="validaEnterDependencia(event, 'exibirFiltrarLogradouroAction.do', document.forms[0].idBairro, document.forms[0].idMunicipioFiltro.value, 'Município');return isCampoNumerico(event);" />
						<a href="javascript:chamarPopup('exibirPesquisarBairroAction.do?idMunicipio='+document.forms[0].idMunicipioFiltro.value+'&indicadorUsoTodos=1', 'bairro', 'idMunicipioFiltro', document.forms[0].idMunicipioFiltro.value, 275, 480, 'Informe o Município.');">
							<img width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Bairro" />
						</a> 
						<logic:present name="corBairro">
							<logic:equal name="corBairro" value="exception">
								<html:text  property="nomeBairro" 
											size="45" 
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
	
							<logic:notEqual name="corBairro" value="exception">
								<html:text  property="nomeBairro" 
											size="45" 
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						
						<logic:notPresent name="corBairro">
	
							<logic:empty name="PesquisarActionForm" property="idBairro">
								<html:text  property="nomeBairro" 
											size="45" 
											value=""
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="PesquisarActionForm" property="idBairro">
								<html:text  property="nomeBairro" 
											size="45" 
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
	
						</logic:notPresent> 
						<a href="javascript:limparPesquisaBairro();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								 border="0" 
								 title="Apagar" /> 
						</a>
					</TD>
				</TR>
				<tr>
					<td width="20%"><strong>C&oacute;digo:</strong></td>
					<td width="80%">
						<html:text  maxlength="9" 
									property="idLogradouro"
									size="10" tabindex="3" 
									onkeypress="return isCampoNumerico(event);"/>
					</td>
				</tr>
				<tr>
					<td height="0"><strong>Tipo:</strong></td>
					<td><html:select property="idTipo" tabindex="4"
						style="width:200px;">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="logradouroTipos"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Título:</strong></td>
					<td>
						<html:select property="idTitulo" 
									 tabindex="5"
									 style="width:200px;">

						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="logradouroTitulos"
									  labelProperty="descricao" 
									  property="id" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td width="20%"><strong>Nome:</strong></td>
					<td width="80%">
						<html:text  maxlength="40" 
									property="nomeLogradouro"
									size="50" 
									tabindex="6" />
					</td>
				</tr>
				<tr>
				<td>&nbsp;</td>
				<td valign="top"><html:radio property="tipoPesquisa"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto<html:radio property="tipoPesquisa"
						tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
				</tr>
				<tr>
					<td width="20%"><strong>Nome Popular:</strong></td>
					<td width="80%">
						<html:text  maxlength="30"
									property="nomePopularLogradouro" 
									size="40" 
									tabindex="7" />
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<html:radio property="tipoPesquisaPopular"
									value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
						Iniciando pelo texto
						<html:radio property="tipoPesquisaPopular"
									tabindex="5"
									value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
						Contendo o texto
					</td>
				</tr>
				<tr>
					<td><strong>CEP:</strong></td>
					<td>
						<html:text  maxlength="8" 
									property="cep" 
									size="9" 
									tabindex="8"
									onkeyup="javascript:limpaNomeCEP();"
									onkeypress="javascript:validaEnter(event, 'exibirFiltrarLogradouroAction.do', 'cep');return isCampoNumerico(event);" />
							<a href="javascript:abrirPopup('exibirPesquisarCepAction.do?indicadorUsoTodos=1');"> <img
								width="23" 
								height="21" 
								border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar CEP" />
							</a> 
							<logic:present name="cepNaoEncontrado" scope="request">
								<html:text  maxlength="40" 
											property="retornoCep" 
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											size="40" />
							</logic:present> 
							<logic:notPresent name="cepNaoEncontrado" scope="request">
								<html:text maxlength="40" property="retornoCep" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									size="40" />
							</logic:notPresent> 
							<a href="javascript:limparPesquisaCep();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									 border="0" 
									 title="Apagar" />
							</a>
					</td>
				</tr>
				<tr>
					<td><strong>Indicador de Uso:</strong></td>
					<td align="right">
					<div align="left"><html:radio property="indicadorUso" tabindex="9"
						value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>" />
					<strong>Ativo</strong> <html:radio property="indicadorUso"
						tabindex="9"
						value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>" />
					<strong>Inativo</strong> <html:radio property="indicadorUso"
						tabindex="9" value="" /> <strong>Todos</strong></div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong> <font color="#ff0000"> 
						<logic:present name="indicadorImportanciaLogradouro" >
							<input name="Submit22"
								class="bottonRightCol" value="Limpar" type="button" tabindex="11"
								onclick="window.location.href='/gsan/exibirManterLogradouroAction.do?menu=sim&implog=sim';">
						</logic:present>
						<logic:notPresent name="indicadorImportanciaLogradouro" >
							<input name="Submit22"
								class="bottonRightCol" value="Limpar" type="button" tabindex="11"
								onclick="window.location.href='/gsan/exibirManterLogradouroAction.do?menu=sim';">
						</logic:notPresent>
					</font></strong></td>
					<td align="right"></td>
					<td align="right">
					<gsan:controleAcessoBotao name="Button" value="Filtrar"
							  onclick="javascript:validarForm(document.forms[0]);" url="filtrarLogradouroAction.do"/>
					<!--
					<input name="botao" class="bottonRightCol"
						value="Filtrar" tabindex="10"
						onclick="javascript:validarForm(document.forms[0]);" type="button">--></td>
				</tr>



				<!-- 
				<tr>
					<td><input type="button" name="Button" tabindex="8" style="width: 80px"
						class="bottonRightCol" value="Filtrar"
						onClick="javascript:validarForm(document.forms[0])" /></td>
					<td>
					<div align="right"></div>
					</td>
				</tr> -->
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>


</html:form>
</body>
</html:html>

