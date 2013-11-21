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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarQuadraActionForm"/>

<SCRIPT LANGUAGE="JavaScript">
<!--


function validarForm(formulario){
	var objLocalidadeID = returnObject(formulario, "localidadeID");
	var objSetorComercialCD = returnObject(formulario, "setorComercialCD");
	var objQuadraNM = returnObject(formulario, "quadraNM");

	if (objLocalidadeID.value.length > 0 && !testarCampoValorZero(objLocalidadeID, "Localidade")){
		objLocalidadeID.focus();
	}
	else if (objSetorComercialCD.value.length > 0 && !testarCampoValorZero(objSetorComercialCD, "Setor Comercial")){
		objSetorComercialCD.focus();
	}
	else if (objQuadraNM.value.length > 0 && !testarCampoValorZero(objQuadraNM, "Quadra")){
		objQuadraNM.focus();
	}
	else if (validateFiltrarQuadraActionForm(formulario)){
		submeterFormPadrao(formulario);
	}
}

function limpar(tipo){

	var form = document.forms[0];

 	switch (tipo){
       case 1:
		   form.localidadeID.value = "";
		   form.localidadeNome.value = "";
		   form.setorComercialCD.value = "";
		   form.setorComercialID.value = "";
		   form.setorComercialNome.value = "";
		   form.quadraNM.value = "";
		   
		   //Coloca o foco no objeto selecionado
		   form.localidadeID.focus();
		   break;
	   case 2:
		   form.setorComercialCD.value = "";
		   form.setorComercialNome.value = "";
		   form.quadraNM.value = "";
		   
		   //Coloca o foco no objeto selecionado
		   form.setorComercialCD.focus();
		   break;
	   case 3:
		   form.quadraNM.value = "";
		   
		   //Coloca o foco no objeto selecionado
		   form.quadraNM.focus();
		   break;
	   case 4:
		   form.setorComercialID.value = "";
		   form.setorComercialCD.value = "";
		   form.setorComercialNome.value = "";
		   form.quadraNM.value = "";
		   
		   //Coloca o foco no objeto selecionado
		   form.localidadeID.focus();
		   break;
	   case 5:
		   form.quadraNM.value = "";
		   form.setorComercialID.value = "";
		   
		   //Coloca o foco no objeto selecionado
		   form.setorComercialCD.focus();
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
	  form.setorComercialNome.style.color = "#000000";	  
	  form.quadraNM.focus();
	}
}


function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	var form = document.forms[0];
	if (tipoConsulta == 'localidade') {
      form.localidadeID.value = codigoRegistro;
      form.localidadeNome.value = descricaoRegistro;
	  form.localidadeNome.style.color = "#000000";
	
	  form.setorComercialCD.focus();
	}
	
	
}


function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg){

//if (objeto == null || codigoObjeto == null){
		abrirPopup(url + "?indicadorUsoTodos=1&" + "tipo=" + tipo, altura, largura);
	//}
	//else{
	//	if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
	//		alert(msg);
	//	}
	//	else{
	//		abrirPopup(url + "?indicadorUsoTodos=1&" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
	//	}
	//}
}



	function verificarChecado(valor){
		form = document.forms[0];
		if(valor == "1"){
		 	form.indicadorAtualizar.checked = true;
		 }else{
		 	form.indicadorAtualizar.checked = false;
		}
	}
	
	function setaFocusPrimeiroCampo(){
		var form = document.FiltrarQuadraActionForm;
		
		form.localidadeID.focus();
	}
	
	function receberRota(codigoRota,destino) {
 	  var form = document.forms[0];
 	  
 	  form.action = 'exibirFiltrarQuadraAction.do';
 	  form.idRota.value = codigoRota;
 	  submeterFormPadrao(form);
	}
	
	function limparRota() {
		var form = document.forms[0];
		
		form.idRota.value = '';
		form.codigoRota.value = '';
	}
	
	function pesquisarRota() {
		var form = document.forms[0];
		
		if (form.setorComercialID.value != null && form.setorComercialID.value != '') {
			abrirPopup('exibirPesquisarInformarRotaLeituraAction.do?idSetorComercial=' + form.setorComercialID.value + '&limparForm=sim', 400, 800);
		} else {
			alert('Informe Setor Comercial');
		}
		
	}
//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');verificarChecado('${sessionScope.indicadorAtualizar}');setaFocusPrimeiroCampo();">

<html:form action="/filtrarQuadraAction"
	name="FiltrarQuadraActionForm"
	type="gcom.gui.cadastro.localidade.FiltrarQuadraActionForm"
	method="post"
	onsubmit="return validateFiltrarQuadraActionForm(this);">


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
          <td class="parabg">Filtrar Quadra</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      
       <tr>
          <td width="100%" colspan=2>
	          <table width="100%">
	          	<tr>
	          		<td>Para filtrar uma quadra no sistema, informe os dados abaixo:</td>
	          		<td width="74" align="right"><input type="checkbox" name="indicadorAtualizar" value="1" /><strong>Atualizar</strong>
						
					</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroLocalizacaoQuadraFiltrar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
	          	</tr>
	          </table>
          </td>
        </tr>
      
      <tr>
        <td><strong>Localidade:</strong></td>
        <td>
			<html:text property="localidadeID" size="5" maxlength="3" tabindex="1" 
			onkeypress="limpar(4);validaEnterComMensagem(event, 'exibirFiltrarQuadraAction.do?objetoConsulta=1', 'localidadeID','Localidade');return isCampoNumerico(event);"/>
			<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do?indicadorUsoTodos=1', 250, 495);">
				<img width="23" height="21" border="0"
				src="<bean:message key="caminho.imagens"/>pesquisa.gif"
				title="Pesquisar Localidade" /></a>

			<logic:present name="corLocalidade">

				<logic:equal name="corLocalidade" value="exception">
					<html:text property="localidadeNome" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:equal>

				<logic:notEqual name="corLocalidade" value="exception">
					<html:text property="localidadeNome" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEqual>

			</logic:present>

			<logic:notPresent name="corLocalidade">

				<logic:empty name="FiltrarQuadraActionForm" property="localidadeID">
					<html:text property="localidadeNome" size="45" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:empty>
				<logic:notEmpty name="FiltrarQuadraActionForm" property="localidadeID">
					<html:text property="localidadeNome" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEmpty>
				

			</logic:notPresent>

			<a	href="javascript:limpar(1);">
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" title="Apagar" /> </a>
		</td>
    </tr>
    <tr>
        <td><strong>Setor Comercial:</strong></td>
        <td>
			<html:text property="setorComercialCD" size="5" maxlength="3" tabindex="2" 
			onkeypress="limpar(5);validaEnterDependenciaComMensagem(event, 'exibirFiltrarQuadraAction.do?objetoConsulta=2', document.forms[0].setorComercialCD, document.forms[0].localidadeID.value, 'Localidade','Setor Comercial');return isCampoNumerico(event);"/>
			
			<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem','idLocalidade', document.forms[0].localidadeID.value, 275, 480, 'Informe Localidade.');">
				<img width="23" height="21" border="0"
				src="<bean:message key="caminho.imagens"/>pesquisa.gif"
				title="Pesquisar Setor Comercial" /></a>

			<logic:present name="corSetorComercial">

				<logic:equal name="corSetorComercial" value="exception">
					<html:text property="setorComercialNome" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:equal>

				<logic:notEqual name="corSetorComercial" value="exception">
					<html:text property="setorComercialNome" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEqual>

			</logic:present>

			<logic:notPresent name="corSetorComercial">

				<logic:empty name="FiltrarQuadraActionForm" property="setorComercialCD">
					<html:text property="setorComercialNome" value="" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:empty>
				<logic:notEmpty name="FiltrarQuadraActionForm" property="setorComercialCD">
					<html:text property="setorComercialNome" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEmpty>
				
			</logic:notPresent>

			<a	href="javascript:limpar(2);">
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" title="Apagar" /> </a>

			<html:hidden property="setorComercialID"/>

	   </td>
	 </tr>
	 <tr>
        <td><strong>Quadra:</strong></td>
        <td>
			<html:text property="quadraNM" size="5" maxlength="4" tabindex="3" onkeypress="return isCampoNumerico(event);"/>
		</td>
	 </tr>
	<tr>
		<td height="24"><strong>Rota:</strong></td>
					<td>
					<html:hidden property="idRota"/>
					<html:text maxlength="4" size="4" 
							property="codigoRota" 
							readonly="true" 
							style="background-color:#EFEFEF; border:0; color: #000000" 
							onkeypress="return isCampoNumerico(event);"/>
					<a href="javascript:pesquisarRota();">
			<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Rota"/></a> 
			<a href="javascript:limparRota()"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>
		</td>
	</tr>
	 <tr>
        <td height="30"><strong>Indicador de uso:</strong></td>
        <td>
			<html:radio property="indicadorUso" value="1" tabindex="4"/><strong>Ativo
			<html:radio property="indicadorUso" value="2" tabindex="5"/>Inativo
			<html:radio property="indicadorUso" value="3" tabindex="6"/>Todos</strong>
		</td>
      </tr>
      
      <tr>
		<td>
			<input name="Button" type="button" class="bottonRightCol" value="Limpar" align="left" onclick="window.location.href='<html:rewrite page="/exibirFiltrarQuadraAction.do?desfazer=S"/>'" >
        </td>
        <td  align="right">
        <gsan:controleAcessoBotao name="Button" value="Filtrar"
							  onclick="javascript:validarForm(document.forms[0]);" url="filtrarQuadraAction.do"/>
			<!-- 							  
      		<INPUT type="button" onclick="validarForm(document.forms[0]);" class="bottonRightCol" value="Filtrar" tabindex="7" style="width: 70px;"> -->
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

