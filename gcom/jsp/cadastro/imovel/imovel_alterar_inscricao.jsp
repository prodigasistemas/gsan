<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>


<SCRIPT LANGUAGE="JavaScript">
<!--
function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

	var form = document.AlterarImovelInscricaoActionForm;

	if (tipoConsulta == 'setorComercialOrigem') {
      form.setorComercialOrigemCD.value = codigoRegistro;
      form.setorComercialOrigemID.value = idRegistro;
	  form.nomeSetorComercialOrigem.value = descricaoRegistro;
	  if(form.setorComercialDestinoCD.value == "")
	  {
	  	form.setorComercialDestinoCD.value = codigoRegistro;
	  }
	  if(form.nomeSetorComercialDestino.value == "")
	  {
	  	form.nomeSetorComercialDestino.value = descricaoRegistro;
	  	form.nomeSetorComercialDestino.style.color = "#000000";	  
	  }
	  form.nomeSetorComercialOrigem.style.color = "#000000";
	  form.quadraOrigemNM.focus();
	}

	if (tipoConsulta == 'setorComercialDestino') {
      form.setorComercialDestinoCD.value = codigoRegistro;
      form.setorComercialDestinoID.value = idRegistro;
	  form.nomeSetorComercialDestino.value = descricaoRegistro;
	  form.nomeSetorComercialDestino.style.color = "#000000"; 
	  if(form.setorComercialOrigemCD.value == "")
	  {
	  	form.setorComercialOrigemCD.value = codigoRegistro;
	  }
	  if(form.nomeSetorComercialOrigem.value == "")
	  {
	  	form.nomeSetorComercialOrigem.value = descricaoRegistro;
	  	form.nomeSetorComercialOrigem.style.color = "#000000";	  
	  }
	  form.quadraDestinoNM.focus();
	}

}
function replicaDados(campoOrigem, campoDestino)
{
	campoDestino.value = campoOrigem.value;
}

function replicaLote(campoOrigem, campoDestino)
{
	campoDestino.value = campoOrigem.value;
}
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	var form = document.AlterarImovelInscricaoActionForm;

	if (tipoConsulta == 'localidadeOrigem') {
      form.localidadeOrigemID.value = codigoRegistro;
	  form.nomeLocalidadeOrigem.value = descricaoRegistro;
	  if(form.localidadeDestinoID.value == "")
	  {
	  	form.localidadeDestinoID.value = codigoRegistro;
	  }
	  if(form.nomeLocalidadeDestino.value == "")
	  {
	  	form.nomeLocalidadeDestino.value = descricaoRegistro;
	  }
	  form.nomeLocalidadeOrigem.style.color = "#000000";
	  form.setorComercialOrigemCD.focus();
	}

	if (tipoConsulta == 'localidade') {
      form.localidadeDestinoID.value = codigoRegistro;
      form.nomeLocalidadeDestino.value = descricaoRegistro;
	  form.nomeLocalidadeDestino.style.color = "#000000";	  
	  if(form.localidadeOrigemID.value == "")
	  {
	  	form.localidadeOrigemID.value = codigoRegistro;
	  }
	  if(form.nomeLocalidadeOrigem.value == "")
	  {
	  	form.nomeLocalidadeOrigem.value = descricaoRegistro;
	  }
	  form.setorComercialDestinoCD.focus();
	}

}
function limparLocalidade(tipo) {
    var form = document.AlterarImovelInscricaoActionForm;
   	switch (tipo){
   		case 1:
	    	form.localidadeDestinoID.value = "";
		    form.nomeLocalidadeDestino.value = "";
	    	form.setorComercialDestinoCD.value = "";
		    form.setorComercialDestinoID.value = "";
		    form.nomeSetorComercialDestino.value = "";
		    form.quadraDestinoNM.value = "";
		    form.quadraDestinoID.value = "";
	    	form.loteDestino.value = "";
		    form.localidadeOrigemID.value = "";
		    form.nomeLocalidadeOrigem.value = "";
		    form.setorComercialOrigemCD.value = "";
		    form.setorComercialOrigemID.value = "";
		    form.nomeSetorComercialOrigem.value = "";
		    form.quadraOrigemNM.value = "";
		    form.quadraOrigemID.value = "";
		    form.loteOrigem.value = "";
  	    break;   
		case 2:
   			form.localidadeDestinoID.value = "";
		    form.nomeLocalidadeDestino.value = "";
		    form.setorComercialDestinoCD.value = "";
		    form.setorComercialDestinoID.value = "";
		    form.nomeSetorComercialDestino.value = "";
		    form.quadraDestinoNM.value = "";
		    form.quadraDestinoID.value = "";
		    form.loteDestino.value = "";
  	    break;   
   }
}
function limpar(tipo){

	var form = document.AlterarImovelInscricaoActionForm;

	switch (tipo){
	   case 2:
	       form.setorComercialDestinoCD.value = "";
		   form.setorComercialDestinoID.value = "";
		   form.nomeSetorComercialDestino.value = "";
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";
	       form.loteDestino.value = "";
		   form.setorComercialOrigemCD.value = "";
		   form.setorComercialOrigemID.value = "";
		   form.nomeSetorComercialOrigem.value = "";
		   form.quadraOrigemNM.value = "";
		   form.quadraOrigemID.value = "";
	       form.loteOrigem.value = "";
		   //Coloca o foco no objeto selecionado
		   form.setorComercialOrigemCD.focus();
		   break;
	   case 4:
		   form.setorComercialDestinoCD.value = "";
		   form.setorComercialDestinoID.value = "";
		   form.nomeSetorComercialDestino.value = "";
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";
	       form.loteDestino.value = "";
		   
		   //Coloca o foco no objeto selecionado
		   form.setorComercialDestinoCD.focus();
		   break;
		/*case 5:
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";
		   form.loteDestino.value = "";
		   form.quadraOrigemNM.value = "";
		   form.quadraOrigemID.value = "";
		   form.loteOrigem.value = "";
		   //Coloca o foco no objeto selecionado
		   form.quadraOrigemNM.focus();
		   break;
		case 6:
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";
		   form.loteDestino.value = "";
		   //Coloca o foco no objeto selecionado
		   form.quadraDestinoNM.focus();
		   break;
		*/
		case 7:
		   form.nomeLocalidadeOrigem.value = "";
		   form.nomeLocalidadeDestino.value = "";
		   form.setorComercialOrigemCD.value = "";
		   form.setorComercialOrigemID.value = "";
		   form.nomeSetorComercialOrigem.value = "";
		   form.quadraOrigemNM.value = "";
		   form.quadraOrigemID.value = "";
		   form.loteOrigem.value = "";
		   form.setorComercialDestinoCD.value = "";
		   form.setorComercialDestinoID.value = "";
		   form.nomeSetorComercialDestino.value = "";
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";
		   form.loteDestino.value = "";
		   //Coloca o foco no objeto selecionado
		   form.localidadeOrigemID.focus();
		   break;
		case 8:
		   form.nomeSetorComercialOrigem.value = "";
		   form.quadraOrigemNM.value = "";
		   form.quadraOrigemID.value = "";
		   form.loteOrigem.value = "";
		   /*form.setorComercialDestinoCD.value = "";
		   form.setorComercialDestinoID.value = "";
		   form.nomeSetorComercialDestino.value = "";*/
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";
		   form.loteDestino.value = "";
		   //Coloca o foco no objeto selecionado
		   form.setorComercialOrigemCD.focus();
		   break;
		case 9:
		   form.nomeLocalidadeDestino.value = "";
		   form.setorComercialDestinoCD.value = "";
		   form.setorComercialDestinoID.value = "";
		   form.nomeSetorComercialDestino.value = "";
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";
		   form.loteDestino.value = "";		   
		   //Coloca o foco no objeto selecionado
		   form.localidadeDestinoID.focus();
		   break;
		 case 10:
   		   form.nomeSetorComercialDestino.value = "";
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";
   		   form.loteDestino.value = "";
		   //Coloca o foco no objeto selecionado
		   form.setorComercialDestinoCD.focus();
		   break;
		case 11:
		   form.loteOrigem.value = "";
		   /*form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";*/
		   form.loteDestino.value = "";
		   //Coloca o foco no objeto selecionado
		   form.quadraOrigemNM.focus();
		   break;   
	    case 12:
		   form.loteDestino.value = "";
		   //Coloca o foco no objeto selecionado
		   form.quadraDestinoNM.focus();
		   break;   
	   default:
          break;
	}
}

function validarForm(form){
	// Campos relacionados a inscrição de origem
	var localidadeOrigem = form.localidadeOrigemID;
	var setorComercialOrigem = form.setorComercialOrigemCD;
	var quadraOrigem = form.quadraOrigemNM;
	var loteOrigem = form.loteOrigem;

	// Campos relacionados a inscrição de destino
	var localidadeDestino = form.localidadeDestinoID;
	var setorComercialDestino = form.setorComercialDestinoCD;
	var quadraDestino = form.quadraDestinoNM;
	var loteDestino = form.loteDestino;

	var obrigatorio = true;

	obrigatorio = campoObrigatorio(setorComercialOrigem, quadraOrigem, "Informe Setor Comercial da inscrição de origem");
	if (!obrigatorio){
		obrigatorio = campoObrigatorio(quadraOrigem, loteOrigem, "Informe Quadra da inscrição de origem");
	}

	//Destino
	if (!obrigatorio){
		obrigatorio = campoObrigatorio(setorComercialDestino, quadraDestino, "Informe Setor Comercial da inscrição de destino");
		if (!obrigatorio){
			obrigatorio = campoObrigatorio(quadraDestino, loteDestino, "Informe Quadra da inscrição de destino");
		}
	}

	//Origem - Destino
	if (!obrigatorio){
		obrigatorio = campoObrigatorio(setorComercialDestino, setorComercialOrigem, "Informe Setor Comercial da inscrição de destino");
		if (!obrigatorio){
			obrigatorio = campoObrigatorio(quadraDestino, quadraOrigem, "Informe Quadra da inscrição de destino");
			if (!obrigatorio){
				obrigatorio = campoObrigatorio(loteDestino, loteOrigem, "Informe Lote da inscrição de destino");
			}
		}
	}
	// Confirma a validação do formulário
	if (!obrigatorio && validateAlterarImovelInscricaoActionForm(form)){
	
		form.submit();
	}

}

function campoObrigatorio(campoDependencia, dependente, msg){
	if (dependente.value.length < 1){
		return false
	}
	else if (campoDependencia.value.length < 1){
		alert(msg);
		campoDependencia.focus();
		return true
	}
}

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg){

	if (objeto == null || codigoObjeto == null){
		abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
	}
	else{
		if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
			alert(msg);
		}
		else{
			abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
		}
	}
}

//-->
</SCRIPT>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AlterarImovelInscricaoActionForm" />

</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">


<html:form action="/alterarImovelInscricaoAction" method="post">


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
          <td class="parabg">Alterar Inscri&ccedil;&atilde;o de Im&oacute;vel</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0" >
        <tr>
            <td colspan="2">Informe os dados da inscri&ccedil;&atilde;o de origem:</td>
            <logic:present scope="application" name="urlHelp">
					<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroImovelInscricaoAlterar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
			</logic:present>
			<logic:notPresent scope="application" name="urlHelp">
					<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
			</logic:notPresent>
        </tr>
        </table>
        <table width="100%" border="0" >
        <tr>
            <td><strong>Localidade:<font color="#FF0000">*</font></strong></td>
            <td>
				<html:text maxlength="3" property="localidadeOrigemID" size="3" onkeypress="limpar(7);validaEnterComMensagem(event, 'exibirAlterarImovelInscricaoAction.do?objetoConsulta=1&inscricaoTipo=origem','localidadeOrigemID','Código da localidade de origem');return isCampoNumerico(event);" 
					onkeyup="replicaDados(document.forms[0].localidadeOrigemID, document.forms[0].localidadeDestinoID);" tabindex="1" />
                <a	href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do?indicadorUsoTodos=1', 'origem', null, null, 275, 480, '');"><img
						src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"
						width="23" height="21" title="Pesquisar"></a> 
				<logic:present name="corLocalidadeOrigem">

					<logic:equal name="corLocalidadeOrigem" value="exception">
						<html:text property="nomeLocalidadeOrigem" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					</logic:equal>

					<logic:notEqual name="corLocalidadeOrigem" value="exception">
						<html:text property="nomeLocalidadeOrigem" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEqual>

				</logic:present>

				<logic:notPresent name="corLocalidadeOrigem">

					<logic:empty name="AlterarImovelInscricaoActionForm" property="localidadeOrigemID">
						<html:text property="nomeLocalidadeOrigem" value="" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:empty>
					<logic:notEmpty name="AlterarImovelInscricaoActionForm" property="localidadeOrigemID">
						<html:text property="nomeLocalidadeOrigem"size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: 	#000000"/>
					</logic:notEmpty>
				</logic:notPresent>
				<a href="javascript:limparLocalidade(1);"> 
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
				</a>
			</td>
        </tr>
        <tr>
           <td><strong>Setor Comercial :</strong></td>
           <td>
		   
			   <html:text maxlength="3" property="setorComercialOrigemCD" size="3" onkeypress="limpar(8); validaEnterDependenciaComMensagem(event, 'exibirAlterarImovelInscricaoAction.do?objetoConsulta=2&inscricaoTipo=origem', document.forms[0].setorComercialOrigemCD, document.forms[0].localidadeOrigemID.value, 'localidade da inscrição de origem', 'Setor comercial de origem');return isCampoNumerico(event);"
			   onkeyup="replicaDados(document.forms[0].setorComercialOrigemCD, document.forms[0].setorComercialDestinoCD);" tabindex="2" />
			   <a	href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem', 'idLocalidade', document.AlterarImovelInscricaoActionForm.localidadeOrigemID.value, 275, 480, 'Informe a Localidade da inscrição de origem');"><img
						src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"
						width="23" height="21" title="Pesquisar"></a> 
				<logic:present name="corSetorComercialOrigem">

					<logic:equal name="corSetorComercialOrigem" value="exception">
						<html:text property="nomeSetorComercialOrigem" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					</logic:equal>

					<logic:notEqual name="corSetorComercialOrigem" value="exception">
						<html:text property="nomeSetorComercialOrigem" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEqual>

				</logic:present>

				<logic:notPresent name="corSetorComercialOrigem">

					<logic:empty name="AlterarImovelInscricaoActionForm" property="setorComercialOrigemCD">
						<html:text property="nomeSetorComercialOrigem" value="" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:empty>
					<logic:notEmpty name="AlterarImovelInscricaoActionForm" property="setorComercialOrigemCD">
						<html:text property="nomeSetorComercialOrigem" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEmpty>
					
				</logic:notPresent>
			   
				<a href="javascript:limpar(2);"> 
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
				</a>
				<html:hidden property="setorComercialOrigemID"/>

			</td>
        </tr>
        <tr>
            <tr>
            <td><strong>Quadra:</strong></td>
            <td><html:text maxlength="4" property="quadraOrigemNM" size="3" tabindex="3" onkeypress="limpar(11);return isCampoNumerico(event);" onkeyup="replicaDados(document.forms[0].quadraOrigemNM, document.forms[0].quadraDestinoNM);"/>
            <html:hidden property="quadraOrigemID"/></td>
        </tr>
        <tr>
           <td><strong>Lote:</strong></td>
           <td><html:text maxlength="4" property="loteOrigem" size="4" tabindex="4" onkeyup="replicaDados(document.forms[0].loteOrigem, document.forms[0].loteDestino);" onkeypress="return isCampoNumerico(event);"/></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td colspan="2">Informe os dados da inscri&ccedil;&atilde;o de destino:</td>
        </tr>
        <tr>
            <td><strong>Localidade:<font color="#FF0000">*</font></strong></td>
            <td>
			
				<html:text maxlength="3" property="localidadeDestinoID" size="3" onkeypress="limpar(9);validaEnterComMensagem(event, 'exibirAlterarImovelInscricaoAction.do?objetoConsulta=1&inscricaoTipo=destino','localidadeDestinoID','Código da localidade de destino');return isCampoNumerico(event);" tabindex="5"/>
                <a	href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do?indicadorUsoTodos=1', 'destino', null, null, 275, 480, '');"><img
						src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"
						width="23" height="21" title="Pesquisar"></a> 
				
				<logic:present name="corLocalidadeDestino">

					<logic:equal name="corLocalidadeDestino" value="exception">
						<html:text property="nomeLocalidadeDestino" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					</logic:equal>

					<logic:notEqual name="corLocalidadeDestino" value="exception">
						<html:text property="nomeLocalidadeDestino" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEqual>

				</logic:present>

				<logic:notPresent name="corLocalidadeDestino">

					<logic:empty name="AlterarImovelInscricaoActionForm" property="localidadeDestinoID">
						<html:text property="nomeLocalidadeDestino" value="" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:empty>
					<logic:notEmpty name="AlterarImovelInscricaoActionForm" property="localidadeDestinoID">
						<html:text property="nomeLocalidadeDestino" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: 	#000000"/>
					</logic:notEmpty>
				</logic:notPresent>
				<a href="javascript:limparLocalidade(2);"> 
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
				</a>
				
			</td>
        </tr>
        <tr>
            <td><strong>Setor Comercial :</strong></td>
            <td>
			
				<html:text maxlength="3" property="setorComercialDestinoCD" size="3" onkeypress="limpar(10); validaEnterDependenciaComMensagem(event, 'exibirAlterarImovelInscricaoAction.do?objetoConsulta=2&inscricaoTipo=destino', document.forms[0].setorComercialDestinoCD, document.forms[0].localidadeDestinoID.value, 'localidade da inscrição de destino', 'Setor comercial de destino');return isCampoNumerico(event);" tabindex="6"/>
				<a	href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialDestino', 'idLocalidade', document.AlterarImovelInscricaoActionForm.localidadeDestinoID.value, 275, 480, 'Informe a Localidade da inscrição de destino');"><img
						src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"
						width="23" height="21" title="Pesquisar"></a> 
				
				<logic:present name="corSetorComercialDestino">

					<logic:equal name="corSetorComercialDestino" value="exception">
						<html:text property="nomeSetorComercialDestino" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					</logic:equal>

					<logic:notEqual name="corSetorComercialDestino" value="exception">
						<html:text property="nomeSetorComercialDestino" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEqual>

				</logic:present>

				<logic:notPresent name="corSetorComercialDestino">

					<logic:empty name="AlterarImovelInscricaoActionForm" property="setorComercialDestinoCD">
						<html:text property="nomeSetorComercialDestino" value="" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:empty>
					<logic:notEmpty name="AlterarImovelInscricaoActionForm" property="setorComercialDestinoCD">
						<html:text property="nomeSetorComercialDestino" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEmpty>
					
				</logic:notPresent>
				
				<a href="javascript:limpar(4);"> 
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
				</a>

				<html:hidden property="setorComercialDestinoID"/>

			</td>
        </tr>
        <tr>
            <td><strong>Quadra:</strong></td>
            <td><html:text maxlength="4" property="quadraDestinoNM" onkeypress="return isCampoNumerico(event);" size="3" tabindex="7" />
            <html:hidden property="quadraDestinoID"/></td>
        </tr>
        <tr>
            <td><strong>Lote:</strong></td>
            <td><html:text maxlength="4" property="loteDestino" size="4" onkeypress="return isCampoNumerico(event);" tabindex="8" /></td>
        </tr>
		<tr>
			<td></td>
      		<td><strong><font color="#FF0000">*</font></strong>
             Campos obrigat&oacute;rios</td>
		</tr>
        <tr>
			<td><input type="button" name="Button"
				class="bottonRightCol" value="Desfazer" 
				onClick="javascript:window.location.href='/gsan/exibirAlterarImovelInscricaoAction.do?menu=sim'"
				style="width: 80px" tabindex="9"/>&nbsp; <input type="button" name="Button"
				class="bottonRightCol" value="Cancelar" 
				onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"
				style="width: 80px" tabindex="10"/></td>
			<td align="right">
			<gsan:controleAcessoBotao name="Button" value="Alterar"
							  onclick="validarForm(document.AlterarImovelInscricaoActionForm);" url="alterarImovelInscricaoAction.do"/>
			<!-- 
			<INPUT type="button" class="bottonRightCol" value="Alterar Inscrição" onclick="validarForm(document.AlterarImovelInscricaoActionForm);" tabindex="11"> --></td>
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
