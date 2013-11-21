<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="gcom.util.Pagina, gcom.util.ConstantesSistema"%>

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
<script language="Javascript">
<!--

     var bCancel = false;

    function validatePesquisarActionForm(form) {
        if (bCancel)
      return true;
        else
       return testarCampoValorZero(document.PesquisarActionForm.idMunicipio, 'Município') && validateCaracterEspecial(form) && validateLong(form);
   }

    function caracteresespeciais  () {

     this.aa = new Array("idMunicipio", "Município deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("codigoBairro", "Código do bairro deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("nomeBairro", "Nome bairro possui caracteres especiais.", new Function ("varName", " return this[varName];"));


    }

    function IntegerValidations  () {
     this.ab = new Array("idMunicipio", "Município deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("codigoBairro", "Código do bairro deve somente conter números positivos.", new Function ("varName", " return this[varName];"));

    }




    //Recebe os dados do(s) popup(s)
    function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

      var form = document.PesquisarActionForm;

       if (tipoConsulta == 'municipio') {
        form.idMunicipio.value = codigoRegistro;
        form.nomeMunicipio.value = descricaoRegistro;
        form.nomeMunicipio.style.color = "#000000";
      }



    }


    function limparPesquisa() {
        var form = document.PesquisarActionForm;

        form.idMunicipio.value = "";
        form.nomeMunicipio.value = "";
    }
    
    function limpaMunicipio(){
	form = document.PesquisarActionForm;

	form.nomeMunicipio.value = "";
	form.idMunicipio.value = "";
	form.idMunicipio.focus();
    }

function limpaNomeMunicipio(){
	var form = document.PesquisarActionForm;

	form.nomeMunicipio.value = "";
}
    
    

function validarForm(){
var form = document.PesquisarActionForm;

if(validatePesquisarActionForm(form)){
        form.submit();
}
}
-->
</script>

</head>


<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/filtrarBairroAction.do" method="post"
	onsubmit="return validatePesquisarActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
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

			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Filtrar Bairro</td>
					<td width="11" valign="top"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>
			<table bordercolor="#000000" width="100%" cellspacing="0">
				<tr>
					<td>
					Para manter o(s) bairro(s), informe os dados abaixo:
					</td>
					<td width="84"><input name="atualizar" type="checkbox"
						checked="checked" value="1"> <strong>Atualizar</strong></td>
					<logic:present scope="application" name="urlHelp">
								<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroEnderecoBairroFiltrar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
								<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>						
					</tr>
   			</table>
   			
   			<table width="100%" border="0">
				<tr>
					<td width="130"><strong>Munic&iacute;pio:</strong></td>
					<td width="480"><html:text maxlength="4" property="idMunicipio"
						tabindex="1" size="4" onkeyup="javascript:limpaNomeMunicipio();"
						onkeypress="javascript:return validaEnter(event, 'exibirFiltrarBairroAction.do', 'idMunicipio');" />
					<a
						href="javascript:abrirPopup('exibirPesquisarMunicipioAction.do?indicadorUsoTodos=1');">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Municipio" /></a> <logic:present
						name="idMunicipioNaoEncontrado" scope="request">
						<html:text maxlength="30" property="nomeMunicipio" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" />
					</logic:present> <logic:notPresent name="idMunicipioNaoEncontrado"
						scope="request">
						<html:text maxlength="30" property="nomeMunicipio" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="40" />
					</logic:notPresent> <a href="javascript:limpaMunicipio();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>



				</tr>
				<tr>
					<td><strong>C&oacute;digo do Bairro:</strong></td>
					<td><html:text property="codigoBairro" size="5" tabindex="2"
						maxlength="4" /></td>
				</tr>
				<tr>
					<td><strong> Nome do Bairro:</strong></td>
					<td><html:text property="nomeBairro" size="30" tabindex="3"
						maxlength="30" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><html:radio property="tipoPesquisa"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto<html:radio property="tipoPesquisa"
						tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>Indicador de Uso:</strong></td>
					<td align="right">
					<div align="left"><html:radio property="indicadorUso" tabindex="4"
						value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>" />
					<strong>Ativo</strong> <html:radio property="indicadorUso"
						tabindex="5"
						value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>" />
					<strong>Inativo</strong> <html:radio property="indicadorUso"
						tabindex="5" value="" /> <strong>Todos</strong></div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong> <font color="#ff0000"> <input name="Submit22"
						class="bottonRightCol" value="Limpar" type="button"
						onclick="window.location.href='/gsan/exibirFiltrarBairroAction.do?menu=sim';">
					</font></strong></td>
					<td align="right"></td>
					<td align="right">
					<gsan:controleAcessoBotao name="Button" value="Filtrar"
							  onclick="javascript:validarForm();" url="filtrarBairroAction.do"/>
					<!--
					<input name="botao" class="bottonRightCol"
						value="Filtrar" onclick="javascript:validarForm();" type="button"> --></td>
				</tr>
				<!-- 
				<tr>
					<td><input type="button" name="Button" class="bottonRightCol"
						tabindex="6" value="Filtrar"
						onClick="javascript:validarForm(document.forms[0])" /></td>
					<td>&nbsp;</td>
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
