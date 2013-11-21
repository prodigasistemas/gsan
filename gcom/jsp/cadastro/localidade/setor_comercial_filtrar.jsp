<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.ConstantesSistema"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarFiltrarSetorComercialActionForm"/>

<SCRIPT LANGUAGE="JavaScript">
<!--
function limpar(tipo){
	var form = document.forms[0];

	switch (tipo){
       case 1:
		   form.localidadeID.value = "";
		   form.localidadeNome.value = "";
		   form.setorComercialCD.value = "";
		   form.setorComercialNome.value = "";
		   //Coloca o foco no objeto selecionado
		   form.localidadeID.focus();
		   break;
	   case 2:
		   form.setorComercialCD.value = "";
		   form.setorComercialID.value = "";
		   form.setorComercialNome.value = "";
		   //Coloca o foco no objeto selecionado
		   form.setorComercialCD.focus();
		   break;
	   case 3:
		   form.municipioID.value = "";
		   form.municipioNome.value = "";
		   //Coloca o foco no objeto selecionado
		   form.municipioID.focus();
		   break;

	   case 4:
		   form.setorComercialCD.value = "";
		   //Coloca o foco no objeto selecionado
		   form.localidadeID.focus();
		   break;
	   case 5:
		   form.localidadeNome.value = "";
		   //Coloca o foco no objeto selecionado
		   form.localidadeID.focus();
		   break;
	   case 6:
		   form.municipioNome.value = "";
		   //Coloca o foco no objeto selecionado
		   form.municipioID.focus();
		   break;
	   default:
          break;
	}
}

function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

	var form = document.forms[0];

	if (tipoConsulta == 'setorComercialOrigem') {
      form.setorComercialCD.value = codigoRegistro;
      form.setorComercialID.value = idRegistro;
	  form.setorComercialNome.value = descricaoRegistro;
	}
}


function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	var form = document.forms[0];

	if (tipoConsulta == 'municipio') {
      form.municipioID.value = codigoRegistro;
	  form.municipioNome.value = descricaoRegistro;
	  form.municipioNome.style.color = "#000000";
	}

	if (tipoConsulta == 'localidade') {
      form.localidadeID.value = codigoRegistro;
      form.localidadeNome.value = descricaoRegistro;
	  form.localidadeNome.style.color = "#000000";
	}

}

function validarForm(formulario){
	if (validatePesquisarFiltrarSetorComercialActionForm(formulario)){
		submeterFormPadrao(formulario);
	}
}


function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg){

	if (objeto == null || codigoObjeto == null){
		abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
	}
	else{
		if (codigoObjeto.length < 1){
			alert(msg);
		}
		else{
			abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
		}
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
//-->
</SCRIPT>
</head>

<body leftmargin="5" topmargin="5" onload="javascript:verificarChecado('${sessionScope.indicadorAtualizar}');setarFoco('${requestScope.nomeCampo}');">

<html:form action="/filtrarSetorComercialAction" method="post">


<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

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

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Filtrar Setor Comercial</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      	<tr>
      		<td>Para atualizar um setor comercial, informe os dados 
            	abaixo:</td>
	        <td width="84">
				<input type="checkbox" name="indicadorAtualizar" value="1" /><strong>Atualizar</strong>
			</td>
			<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroLocalizacaoSetorComercialFiltrar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
			</logic:present>
			<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
			</logic:notPresent>
		</tr>
   	</table>
   	<table width="100%" border="0">
      <tr>
      	<td width="183" HEIGHT="30"><strong>Localidade:</td>
        <td width="432">
        	<html:text property="localidadeID" size="5" maxlength="3" onkeyup="limpar(5);"
        		tabindex="1" onkeypress="limpar(4);validaEnterComMensagem(event, 'exibirFiltrarSetorComercialAction.do?objetoConsulta=1', 'localidadeID', 'Localidade');"/>
			<a	href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do?indicadorUsoTodos=1', 250, 495);"><img
						src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"
						width="23" height="21" title="Pesquisar"></a> 
			<logic:present name="corLocalidade">

				<logic:equal name="corLocalidade" value="exception">
					<html:text property="localidadeNome" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:equal>

				<logic:notEqual name="corLocalidade" value="exception">
					<html:text property="localidadeNome" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEqual>

			</logic:present>

			<logic:notPresent name="corLocalidade">

				<logic:empty name="PesquisarFiltrarSetorComercialActionForm" property="localidadeID">
					<html:text property="localidadeNome" value="" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:empty>
				<logic:notEmpty name="PesquisarFiltrarSetorComercialActionForm" property="localidadeID">
					<html:text property="localidadeNome"size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEmpty>
				

			</logic:notPresent>

			<a href="javascript:limpar(1);"> 
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
			</a>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>C&oacute;digo do Setor Comercial:</td>
        <td>
			<html:text property="setorComercialCD" size="5" maxlength="3" tabindex="2"/>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Nome do Setor Comercial:</td>
        <td>
			<html:text maxlength="30" property="setorComercialNome" size="45" tabindex="3"/>
		</td>
      </tr>
      <tr>
				<td>&nbsp;</td>
				<td><html:radio property="tipoPesquisa"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto&nbsp;<html:radio property="tipoPesquisa"
						tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
				</tr>
      <tr>
        <td HEIGHT="30"><strong>Munic&iacute;pio:</td>
        <td>
		
			<html:text property="municipioID" size="5" maxlength="4"  onkeyup="limpar(6);"
				tabindex="4" onkeypress="validaEnterComMensagem(event, 'exibirFiltrarSetorComercialAction.do?objetoConsulta=3', 'municipioID', 'Município');"/>
			<a	href="javascript:abrirPopup('exibirPesquisarMunicipioAction.do?indicadorUsoTodos=1', 250, 495);"><img
						src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"
						width="23" height="21" title="Pesquisar"></a> 

			<logic:present name="corMunicipio">

				<logic:equal name="corMunicipio" value="exception">
					<html:text property="municipioNome" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:equal>

				<logic:notEqual name="corMunicipio" value="exception">
					<html:text property="municipioNome" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEqual>

			</logic:present>

			<logic:notPresent name="corMunicipio">

				<logic:empty name="PesquisarFiltrarSetorComercialActionForm" property="municipioID">
					<html:text property="municipioNome" size="45" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:empty>
				<logic:notEmpty name="PesquisarFiltrarSetorComercialActionForm" property="municipioID">
					<html:text property="municipioNome" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEmpty>
				

			</logic:notPresent>

			<a href="javascript:limpar(3);"> 
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
			</a>

		</td>
      </tr>
	  <tr>
        <td height="30"><strong>Indicador de uso:</strong></td>
        <td>
			<html:radio property="indicadorUso" value="1"/><strong>Ativo
			<html:radio property="indicadorUso" value="2"/>Inativo</strong>
			<html:radio property="indicadorUso"	tabindex="5" value="3" /><strong>Todos</strong>
		</td>
      </tr>
      <tr>
        <td height="30"><strong>Setor Alternativo:</strong></td>
        <td>
			<html:radio property="indicadorSetorAlternativo" value="1"/><strong>Sim
			<html:radio property="indicadorSetorAlternativo" value="2"/>Não</strong>
			<html:radio property="indicadorSetorAlternativo"	tabindex="5" value="3" /><strong>Todos</strong>
		</td>
      </tr>
      <tr>
		<td><strong> <font color="#ff0000"> <input name="Submit22"
			class="bottonRightCol" value="Limpar" type="button"
			onclick="window.location.href='/gsan/exibirFiltrarSetorComercialAction.do?menu=sim';">
		</font></strong></td>
		<td align="right">
		<gsan:controleAcessoBotao name="Button" value="Filtrar"
							  onclick="javascript:validarForm(document.forms[0]);" url="filtrarSetorComercialAction.do"/>
		<!-- 
		<INPUT type="button" onclick="validarForm(document.forms[0]);" name="botaoFiltrar" class="bottonRightCol" value="Filtrar" tabindex="5" style="width: 70px;"> --></td>
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
