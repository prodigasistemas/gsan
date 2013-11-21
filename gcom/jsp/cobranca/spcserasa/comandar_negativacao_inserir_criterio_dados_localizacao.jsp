<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirComandoNegativacaoActionForm" dynamicJavascript="false" />

<SCRIPT LANGUAGE="JavaScript">

	function desabilitarLocalizacaoGeografica(){
	  var formulario = document.forms[0]; 
	  if(formulario.cobrancaGrupo.selectedIndex != -1){
		  if(formulario.cobrancaGrupo.value != -1){
		  	 formulario.gerenciaRegional.selectedIndex = 0;
		     formulario.gerenciaRegional.disabled = true;	
		     formulario.unidadeNegocio.selectedIndex = 0;     
		     formulario.unidadeNegocio.disabled = true;	 
		     formulario.eloPolo.selectedIndex = 0;   
		     formulario.eloPolo.disabled = true;	    
		     formulario.idLocalidadeInicial.disabled = true;	   
		     formulario.idLocalidadeFinal.disabled = true; 	   
		     formulario.codigoSetorComercialInicial.disabled = true;	   
		     formulario.codigoSetorComercialFinal.disabled = true; 	  	       	     	     	     
		  }else{
		     formulario.gerenciaRegional.disabled = false;	     
		     formulario.unidadeNegocio.disabled = false;	    
		     formulario.eloPolo.disabled = false;	    
		     formulario.idLocalidadeInicial.disabled = false;	   
		     formulario.idLocalidadeFinal.disabled = false; 	   		  
		  }
	   }
	  }
	
	function desabilitarGrupoCobranca(){
	  var form = document.forms[0];	  	  	
	  if(form.gerenciaRegional.selectedIndex != -1 || form.unidadeNegocio.selectedIndex != -1 || form.eloPolo.selectedIndex != -1){  	  
		  if((form.gerenciaRegional.value != -1 && form.gerenciaRegional.value != "") || 
		     (form.unidadeNegocio.value != -1 && form.unidadeNegocio.value != "") || 
		     (form.eloPolo.value != -1  && form.eloPolo.value != "") ||
		  	 (form.idLocalidadeInicial.value != null && form.idLocalidadeInicial.value != "")||
		     (form.codigoSetorComercialInicial.value != null && form.codigoSetorComercialInicial.value != "")||
		     (form.idLocalidadeFinal.value != null && form.idLocalidadeFinal.value != "")||
		     (form.codigoSetorComercialFinal.value != null && form.codigoSetorComercialFinal.value != "")){
		        form.cobrancaGrupo.selectedIndex = 0;	
		  	 	form.cobrancaGrupo.disabled = true; 
		  }
	 
	 	  if((form.gerenciaRegional.value == -1 || form.gerenciaRegional.value == "") &&
	 	    (form.unidadeNegocio.value == -1 || form.unidadeNegocio.value == "")&&
	 	    (form.eloPolo.value == -1 || form.eloPolo.value == "")&&
	 	  	(form.idLocalidadeInicial.value == null || form.idLocalidadeInicial.value == "")&&
	 	  	(form.codigoSetorComercialInicial.value == null || form.codigoSetorComercialInicial.value == "")&&
	 	  	(form.idLocalidadeFinal.value == null || form.idLocalidadeFinal.value == "")&&
	 	  	(form.codigoSetorComercialFinal.value == null || form.codigoSetorComercialFinal.value == "")){
		     	form.cobrancaGrupo.disabled = false; 
		  }
	  }
	}

	function validateInserirComandoNegativacaoActionForm(form) {
		return true;
	}
	
	function limparPesquisaLocalidadeInicial() {
		var form = document.forms[0];		
		
	    form.idLocalidadeInicial.value = '';
		form.localidadeDescricaoInicial.value = '';		
		form.idLocalidadeInicial.focus();
		limparPesquisaLocalidadeFinal();
		desabilitarGrupoCobranca();
		verificarSetorComercial();
	}
	  
	function limparPesquisaLocalidadeFinal() {
		var form = document.forms[0];		
		
	    form.idLocalidadeFinal.value = '';
		form.localidadeDescricaoFinal.value = '';
		desabilitarGrupoCobranca();		
		verificarSetorComercial();
	}	  
	  
	function limparSetorComercialInicial() {
		var form = document.forms[0];		
		
	    form.codigoSetorComercialInicial.value = '';
		form.setorComercialDescricaoInicial.value = '';		
		form.codigoSetorComercialInicial.focus();
		limparSetorComercialFinal();
		desabilitarGrupoCobranca();
	}	
	function limparSetorComercialFinal() {
		var form = document.forms[0];		
		
	    form.codigoSetorComercialFinal.value = '';
		form.setorComercialDescricaoFinal.value = '';	
		desabilitarGrupoCobranca();	
	}
	
	function verificarSetorComercial(){
		var form = document.forms[0];		
		if((form.idLocalidadeInicial.value != null && form.idLocalidadeInicial.value != "") &&
		   (form.idLocalidadeFinal.value != null && form.idLocalidadeFinal.value != "")){
		  if(form.idLocalidadeInicial.value == form.idLocalidadeFinal.value){
		    form.codigoSetorComercialInicial.disabled = false;
		    form.codigoSetorComercialFinal.disabled = false;		    
		  }else{
			limparSetorComercialInicial();
			limparSetorComercialFinal();
		    form.codigoSetorComercialInicial.disabled = true;
		    form.codigoSetorComercialFinal.disabled = true;			  
		  }
		}else{
			limparSetorComercialInicial();
			limparSetorComercialFinal();		
		    form.codigoSetorComercialInicial.disabled = true;
		    form.codigoSetorComercialFinal.disabled = true;			  
		}	
	}
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
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
   }

 	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	    var form = document.forms[0];
	
		if (tipoConsulta == 'localidade') {
			
			if(form.tipoLocalidade.value == 'I'){
	      		
	      		form.idLocalidadeInicial.value = codigoRegistro;
		  		form.localidadeDescricaoInicial.value = descricaoRegistro;
		  		form.localidadeDescricaoInicial.style.color = "#000000";

		  		if(form.idLocalidadeFinal.value == ""){
	      		  form.idLocalidadeFinal.value = codigoRegistro;
		  		  form.localidadeDescricaoFinal.value = descricaoRegistro;
		  		  form.localidadeDescricaoFinal.style.color = "#000000";
		  	    }				
		    }else{

	      	  form.idLocalidadeFinal.value = codigoRegistro;
		  	  form.localidadeDescricaoFinal.value = descricaoRegistro;
		  	  form.localidadeDescricaoFinal.style.color = "#000000";	
	    		
 		    }
 		    verificarSetorComercial();
	    }

    }
   
   function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

	var form = document.forms[0];

	  if (tipoConsulta == 'setorComercialOrigem') {
	  
	      form.codigoSetorComercialInicial.value = codigoRegistro;	      
		  form.setorComercialDescricaoInicial.value = descricaoRegistro;
		  form.setorComercialDescricaoInicial.style.color = "#000000";
		  
		  if(form.codigoSetorComercialFinal.value == ""){
	      form.codigoSetorComercialFinal.value = codigoRegistro;	      
		  form.setorComercialDescricaoFinal.value = descricaoRegistro;
		  form.setorComercialDescricaoFinal.style.color = "#000000";
		  }
	  
	  }

	  if (tipoConsulta == 'setorComercialDestino') {
	
	      form.codigoSetorComercialFinal.value = codigoRegistro;	      
		  form.setorComercialDescricaoFinal.value = descricaoRegistro;
		  form.setorComercialDescricaoFinal.style.color = "#000000";

	  }

   }   	  

</SCRIPT>


</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');verificarSetorComercial();desabilitarLocalizacaoGeografica();desabilitarGrupoCobranca();verificarSetorComercial();">

<div id="formDiv">
<html:form action="/inserirComandoNegativacaPorCriterioWizardAction" method="post">

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=4"/>


<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<input type="hidden" name="tipoLocalidade" />

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

	<td width="610" valign="top" class="centercoltext">

        <table height="100%">
        <tr>
          <td></td>
        </tr>
      	</table>

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">ICN - Por Critério - Dados da Localidade</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      <tr>
      	<td colspan="2">Para determinar a negativação a ser comandada, informe os dados abaixo:</td>
      </tr>
      <tr>
      	<td width="35%"><strong>Negativador:</strong></td>
       	<td>
			<html:text property="nomeNegativador" size="50" maxlength="60" tabindex="4" readonly="true"
				style="background-color:#EFEFEF; border:0; color: #000000"/>
		</td>
	  </tr>
	  <tr>
         <td colspan="2"><hr></td>
      </tr>
      <tr> 
        <td width="35%"><strong>Grupo de Cobran&ccedil;a:</strong></td>
        <td colspan="6"><span class="style2"><strong>
          <logic:present name="desabilitar">
		  <html:select property="cobrancaGrupo" style="width: 350px; height:100px;" multiple="true" disabled="true"> 
			<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
			<logic:present name="colcaoCobrancaGrupo" scope="session">
				<html:options collection="colcaoCobrancaGrupo" labelProperty="descricao" property="id" />
			</logic:present>
		  </html:select>  
		  </logic:present>
		  <logic:notPresent name="desabilitar"> 
		    <html:select property="cobrancaGrupo" style="width: 350px; height:100px;" multiple="true" onchange="javascript:desabilitarLocalizacaoGeografica();"> 
			<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
			<logic:present name="colcaoCobrancaGrupo" scope="session">
				<html:options collection="colcaoCobrancaGrupo" labelProperty="descricao" property="id" />
			</logic:present>
		  </html:select>       
		  </logic:notPresent>        
          </strong></span></td>
      </tr>     
	  <tr>
         <td colspan="2"><hr></td>
      </tr>      
      <tr> 
        <td width="35%"><strong>Gerência Regional:</strong></td>
        <td colspan="6"><span class="style2"><strong>
          <logic:present name="desabilitar">
		  <html:select property="gerenciaRegional" style="width: 350px; height:100px;" multiple="true" disabled="true">
			<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
			<logic:present name="colecaoGerenciaRegional" scope="session">
				<html:options collection="colecaoGerenciaRegional" labelProperty="nome" property="id" />
			</logic:present>
		  </html:select>  
		  </logic:present>
		  <logic:notPresent name="desabilitar">   
		  <html:select property="gerenciaRegional" style="width: 350px; height:100px;" multiple="true" onchange="javascript:desabilitarGrupoCobranca();">
			<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
			<logic:present name="colecaoGerenciaRegional" scope="session">
				<html:options collection="colecaoGerenciaRegional" labelProperty="nome" property="id" />
			</logic:present>
		  </html:select>  		     
		  </logic:notPresent>        
          </strong></span></td>
      </tr>       
      <tr> 
        <td width="35%"><strong>Unidade Negócio:</strong></td>
        <td colspan="6"><span class="style2"><strong>
          <logic:present name="desabilitar">        
		  <html:select property="unidadeNegocio" style="width: 350px; height:100px;" multiple="true" disabled="true">
			<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
			<logic:present name="colecaoUnidadeNegocio" scope="session">
				<html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id" />
			</logic:present>
		  </html:select> 
		  </logic:present>
		  <logic:notPresent name="desabilitar">
		  <html:select property="unidadeNegocio" style="width: 350px; height:100px;" multiple="true" onchange="javascript:desabilitarGrupoCobranca();">
			<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
			<logic:present name="colecaoUnidadeNegocio" scope="session">
				<html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id" />
			</logic:present>
		  </html:select> 		  
		  </logic:notPresent>               
          </strong></span></td>
      </tr>      
      <tr> 
        <td width="35%"><strong>Localidade Pólo:</strong></td>
        <td colspan="6"><span class="style2"><strong>
          <logic:present name="desabilitar">          
		  <html:select property="eloPolo" style="width: 350px; height:100px;" multiple="true" disabled="true">
			<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
			<logic:present name="colecaoEloPolo" scope="session">
				<html:options collection="colecaoEloPolo" labelProperty="descricao" property="id" />
			</logic:present>
		  </html:select>  
		  </logic:present>
		  <logic:notPresent name="desabilitar">  
		  <html:select property="eloPolo" style="width: 350px; height:100px;" multiple="true" onchange="javascript:desabilitarGrupoCobranca();">
			<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
			<logic:present name="colecaoEloPolo" scope="session">
				<html:options collection="colecaoEloPolo" labelProperty="descricao" property="id" />
			</logic:present>
		  </html:select>  		       
		  </logic:notPresent>        
          </strong></span></td>
      </tr> 
      <tr>
         <td colspan="2"><hr></td>
      </tr>      
	  <tr>
		<td width="35%"><strong>Localidade Inicial:</strong></td>
		<td colspan="2" width="81%" height="24">
		<logic:present name="desabilitar">         
		<html:text maxlength="3"
			tabindex="1" property="idLocalidadeInicial" size="3" disabled="true"/>		
		   <img width="23" height="21" border="0"
			src="<bean:message key="caminho.imagens"/>pesquisa.gif"
			title="Pesquisar Localidade" />
		</logic:present>
		<logic:notPresent name="desabilitar">         
		<html:text maxlength="3"
			tabindex="1" property="idLocalidadeInicial" size="3"
			onkeypress="javascript:validaEnter(event, 'inserirComandoNegativacaPorCriterioWizardAction.do?action=exibirInserirComandoNegativacaoLocalizacaoAction', 'idLocalidadeInicial');return isCampoNumerico(event);" 
			onkeyup="replicarCampo(document.forms[0].idLocalidadeFinal,this);desabilitarGrupoCobranca();verificarSetorComercial();"/>
		<a href="javascript:document.forms[0].tipoLocalidade.value='I';chamarPopup('exibirPesquisarLocalidadeAction.do?indicadorUsoTodos=1', 'origem', null, null, 275, 480, '',document.forms[0].idLocalidadeInicial);limparSetorComercialInicial();">
		<img width="23" height="21" border="0"
			src="<bean:message key="caminho.imagens"/>pesquisa.gif"
			title="Pesquisar Localidade" /></a>
	    </logic:notPresent>			
			<logic:present name="idLocalidadeNaoEncontrada">
			<logic:equal name="idLocalidadeNaoEncontrada" value="exception">
				<html:text property="localidadeDescricaoInicial" size="40"
					readonly="true"
					style="background-color:#EFEFEF; border:0; color: #ff0000" />
			</logic:equal>
			<logic:notEqual name="idLocalidadeNaoEncontrada" value="exception">
				<html:text property="localidadeDescricaoInicial" size="40"
					readonly="true"
					style="background-color:#EFEFEF; border:0; color: #000000" />
			</logic:notEqual>
		</logic:present> <logic:notPresent name="idLocalidadeNaoEncontrada">
			<logic:empty name="InserirComandoNegativacaoActionForm"
				property="idLocalidadeInicial">
				<html:text property="localidadeDescricaoInicial" value=""
					size="40" readonly="true"
					style="background-color:#EFEFEF; border:0; color: #ff0000" />
			</logic:empty>
			<logic:notEmpty name="InserirComandoNegativacaoActionForm"
				property="idLocalidadeInicial">
				<html:text property="localidadeDescricaoInicial" size="40"
					readonly="true"
					style="background-color:#EFEFEF; border:0; color: #000000" />
			</logic:notEmpty>
		</logic:notPresent> <a
			href="javascript:limparPesquisaLocalidadeInicial();">
		<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
			border="0" title="Apagar" /></a></td>
	  </tr>
	  <tr>
		<td width="35%"><strong>Setor Comercial Inicial:</strong></td>
		<td colspan="2" height="24">
		<logic:present name="desabilitar">		
		<html:text maxlength="3"
			property="codigoSetorComercialInicial" tabindex="2" size="3" disabled="true"/>	
		  <img width="23" height="21" border="0"
			src="<bean:message key="caminho.imagens"/>pesquisa.gif"
			title="Pesquisar Setor Comercial" />				
	    </logic:present>  
		<logic:notPresent name="desabilitar">		
		<html:text maxlength="3"
			property="codigoSetorComercialInicial" tabindex="2" size="3"
			onkeypress="javascript:validaEnterDependencia(event, 'inserirComandoNegativacaPorCriterioWizardAction.do?action=exibirInserirComandoNegativacaoLocalizacaoAction', this, document.forms[0].idLocalidadeInicial.value, 'Localidade');return isCampoNumerico(event);"
			
			onkeyup="javascript:replicarCampo(document.forms[0].codigoSetorComercialFinal,this);desabilitarGrupoCobranca();" /> 
			<a href="javascript:abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidadeInicial.value+'&tipo=setorComercialOrigem&indicadorUsoTodos=1',document.forms[0].idLocalidadeInicial.value,'Localidade Inicial', 400, 800);"> 
		
		<img width="23" height="21" border="0"
			src="<bean:message key="caminho.imagens"/>pesquisa.gif"
			title="Pesquisar Setor Comercial" /></a>
	    </logic:notPresent> 	    
	    <logic:present name="idSetorComercialNaoEncontrada">
			<logic:equal name="idSetorComercialNaoEncontrada"
				value="exception">
				<html:text property="setorComercialDescricaoInicial" size="40"
					readonly="true"
					style="background-color:#EFEFEF; border:0; color: #ff0000" />
			</logic:equal>
			<logic:notEqual name="idSetorComercialNaoEncontrada"
				value="exception">
				<html:text property="setorComercialDescricaoInicial" size="40"
					readonly="true"
					style="background-color:#EFEFEF; border:0; color: #000000" />
			</logic:notEqual>
		</logic:present> <logic:notPresent
			name="idSetorComercialNaoEncontrada">
			<logic:empty name="InserirComandoNegativacaoActionForm"
				property="codigoSetorComercialInicial">
				<html:text property="setorComercialDescricaoInicial" value=""
					size="40" readonly="true"
					style="background-color:#EFEFEF; border:0; color: #ff0000" />
			</logic:empty>
			<logic:notEmpty name="InserirComandoNegativacaoActionForm"
				property="codigoSetorComercialInicial">
				<html:text property="setorComercialDescricaoInicial" size="40"
					readonly="true"
					style="background-color:#EFEFEF; border:0; color: #000000" />
			</logic:notEmpty>

		</logic:notPresent> <a
			href="javascript:limparSetorComercialInicial();">
		<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
			border="0" title="Apagar" /></a></td>
	  </tr>          
      <tr>
         <td colspan="2"><hr></td>
      </tr>      
	  <tr>
		<td width="35%"><strong>Localidade Final:</strong></td>
		<td colspan="2" width="81%" height="24">
		<logic:present name="desabilitar">		
		<html:text maxlength="3"
			property="idLocalidadeFinal" tabindex="2" size="3" disabled="true"/>
			<img width="23" height="21" border="0"
			src="<bean:message key="caminho.imagens"/>pesquisa.gif"
			title="Pesquisar Localidade" />					
	    </logic:present>  
		<logic:notPresent name="desabilitar">	    
		<html:text maxlength="3"
			tabindex="1" property="idLocalidadeFinal" size="3"
			onkeypress="javascript:validaEnter(event, 'inserirComandoNegativacaPorCriterioWizardAction.do?action=exibirInserirComandoNegativacaoLocalizacaoAction', 'idLocalidadeFinal');return isCampoNumerico(event);" 
			onkeyup="javascript:desabilitarGrupoCobranca();verificarSetorComercial();"/>
		<a href="javascript:document.forms[0].tipoLocalidade.value='F';chamarPopup('exibirPesquisarLocalidadeAction.do?indicadorUsoTodos=1', 'destino', null, null, 275, 480, '',document.forms[0].idLocalidadeFinal);">
		<img width="23" height="21" border="0"
			src="<bean:message key="caminho.imagens"/>pesquisa.gif"
			title="Pesquisar Localidade" /></a>
	   </logic:notPresent>
	   <logic:present
			name="idLocalidadeFinalNaoEncontrada">
			<logic:equal name="idLocalidadeNaoEncontrada" value="exception">
				<html:text property="localidadeDescricaoFinal" size="40"
					readonly="true"
					style="background-color:#EFEFEF; border:0; color: #ff0000" />
			</logic:equal>
			<logic:notEqual name="idLocalidadeFinalNaoEncontrada" value="exception">
				<html:text property="localidadeDescricaoFinal" size="40"
					readonly="true"
					style="background-color:#EFEFEF; border:0; color: #000000" />
			</logic:notEqual>
		</logic:present> <logic:notPresent name="idLocalidadeFinalNaoEncontrada">
			<logic:empty name="InserirComandoNegativacaoActionForm"
				property="idLocalidadeFinal">
				<html:text property="localidadeDescricaoFinal" value=""
					size="40" readonly="true"
					style="background-color:#EFEFEF; border:0; color: #ff0000" />
			</logic:empty>
			<logic:notEmpty name="InserirComandoNegativacaoActionForm"
				property="idLocalidadeFinal">
				<html:text property="localidadeDescricaoFinal" size="40"
					readonly="true"
					style="background-color:#EFEFEF; border:0; color: #000000" />
			</logic:notEmpty>
		</logic:notPresent> <a
			href="javascript:limparPesquisaLocalidadeFinal();">
		<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
			border="0" title="Apagar" /></a></td>
	  </tr>
	  <tr>
		<td width="35%"><strong>Setor Comercial Final:</strong></td>
		<td colspan="2" height="24">
		<logic:present name="desabilitar">		
		<html:text maxlength="3"
			property="codigoSetorComercialFinal" tabindex="2" size="3" disabled="true"/>
		  <img width="23" height="21" border="0"
			src="<bean:message key="caminho.imagens"/>pesquisa.gif"
			title="Pesquisar Setor Comercial" />									
	    </logic:present>
		<logic:notPresent name="desabilitar">		    
		<html:text maxlength="3"
			property="codigoSetorComercialFinal" tabindex="2" size="3"
			onkeypress="javascript: validaEnterDependencia(event, 'inserirComandoNegativacaPorCriterioWizardAction.do?action=exibirInserirComandoNegativacaoLocalizacaoAction', this, document.forms[0].idLocalidadeFinal.value, 'Localidade');return isCampoNumerico(event);"
			
			onkeyup="javascript:desabilitarGrupoCobranca();verificarDigitosSetorComercial();" /> 
			<a href="javascript:abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidadeFinal.value+'&tipo=setorComercialDestino&indicadorUsoTodos=1',document.forms[0].idLocalidadeFinal.value,'Localidade Final', 400, 800);">
		<img width="23" height="21" border="0"
			src="<bean:message key="caminho.imagens"/>pesquisa.gif"
			title="Pesquisar Setor Comercial" /></a> 
	   </logic:notPresent>
	   <logic:present
			name="idSetorComercialNaoEncontrada">
			<logic:equal name="idSetorComercialNaoEncontrada"
				value="exception">
				<html:text property="setorComercialDescricaoFinal" size="40"
					readonly="true"
					style="background-color:#EFEFEF; border:0; color: #ff0000" />
			</logic:equal>
			<logic:notEqual name="idSetorComercialNaoEncontrada"
				value="exception">
				<html:text property="setorComercialDescricaoFinal" size="40"
					readonly="true"
					style="background-color:#EFEFEF; border:0; color: #000000" />
			</logic:notEqual>
		</logic:present> <logic:notPresent
			name="idSetorComercialNaoEncontrada">
			<logic:empty name="InserirComandoNegativacaoActionForm"
				property="codigoSetorComercialFinal">
				<html:text property="setorComercialDescricaoFinal" value=""
					size="40" readonly="true"
					style="background-color:#EFEFEF; border:0; color: #ff0000" />
			</logic:empty>
			<logic:notEmpty name="InserirComandoNegativacaoActionForm"
				property="codigoSetorComercialFinal">
				<html:text property="setorComercialDescricaoFinal" size="40"
					readonly="true"
					style="background-color:#EFEFEF; border:0; color: #000000" />
			</logic:notEmpty>

		</logic:notPresent> <a
			href="javascript:limparSetorComercialFinal();">
		<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
			border="0" title="Apagar" /></a></td>
	  </tr> 	  
	    
      <tr>
      	<td colspan="2" height="10"></td>
      </tr>
      <tr>
        <td colspan="2">
			<div align="right">
				<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=4" />
			</div>
		</td>
      </tr>
      </table>
      <p>&nbsp;</p>
	</td>
  </tr>
</table>



<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>

<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/inserirComandoNegativacaPorCriterioWizardAction.do?concluir=true&action=inserirComandoNegativacaoLocalizacaoAction'); }
</script>

</body>
</html:html>