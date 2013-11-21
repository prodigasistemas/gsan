<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.ConstantesSistema" %>

<%@ page import="gcom.cadastro.sistemaparametro.SistemaParametro"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ManterImovelActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">

     var bCancel = false;

    function validateManterImovelActionForm(form) {
        if (bCancel)
      return true;
        else
       return validateRequired(form)
              && testarCampoValorZero(document.ManterImovelActionForm.idLocalidade, 'Localidade') 
              && testarCampoValorZero(document.ManterImovelActionForm.idSetorComercial, 'Setor Comercial')
              && testarCampoValorZero(document.ManterImovelActionForm.idQuadra, 'Quadra')
		 	  && testarCampoValorZero(document.ManterImovelActionForm.testadaLote, 'Testada do Lote') 
  	          && validateCaracterEspecial(form) 
  	          && validateLong(form) && validateInteiroZeroPositivo(form) && validarSequencialRota();
   }

    function caracteresespeciais () {
     this.aa = new Array("idLocalidade", "Localidade deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("idSetorComercial", "Setor Comercial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("idQuadra", "Quadra deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("lote", "Lote deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("subLote", "Sublote deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.af = new Array("testadaLote", "Testada do Lote deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ag = new Array("sequencialRota", "Sequência na Rota deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }

    function IntegerValidations () {
     this.aa = new Array("idLocalidade", "Localidade deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("idSetorComercial", "Setor Comercial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("idQuadra", "Quadra deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("testadaLote", "Testada do Lote deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }

    function InteiroZeroPositivoValidations () {
     this.aa = new Array("subLote", "SubLote deve somente conter números positivos ou zero.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("lote", "Lote deve somente conter números positivos ou zero.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("sequencialRota", "Sequência na Rota deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    
</script>

<logic:present name="exibirQuadraFace" scope="request">

	<script language="JavaScript">

    	function required () {
    	 	this.aa = new Array("idLocalidade", "Informe Localidade.", new Function ("varName", " return this[varName];"));
     		this.ab = new Array("idSetorComercial", "Informe Setor Comercial.", new Function ("varName", " return this[varName];"));
     		this.ac = new Array("idQuadra", "Informe Quadra.", new Function ("varName", " return this[varName];"));
     		this.ad = new Array("idQuadraFace", "Informe Face.", new Function ("varName", " return this[varName];"));
     		this.ae = new Array("lote", "Informe Lote.", new Function ("varName", " return this[varName];"));
     		this.af = new Array("subLote", "Informe SubLote.", new Function ("varName", " return this[varName];"));
    	}

    </script>
    
</logic:present>

<logic:notPresent name="exibirQuadraFace" scope="request">

	<script language="JavaScript">

    	function required () {
    	 	this.aa = new Array("idLocalidade", "Informe Localidade.", new Function ("varName", " return this[varName];"));
     		this.ab = new Array("idSetorComercial", "Informe Setor Comercial.", new Function ("varName", " return this[varName];"));
     		this.ac = new Array("idQuadra", "Informe Quadra.", new Function ("varName", " return this[varName];"));
     		this.ad = new Array("lote", "Informe Lote.", new Function ("varName", " return this[varName];"));
     		this.ae = new Array("subLote", "Informe SubLote.", new Function ("varName", " return this[varName];"));
    	}

    </script>
    
</logic:notPresent>

<script language="JavaScript"> 
    
    function validarSequencialRota() {
		var form = document.forms[0];
		
		if (form.usaRota.value == '1' && (form.sequencialRota.value == null || form.sequencialRota.value == '')) {
			alert('Informe Sequencial Rota');
			return false;
		} else {
			return true;
		}
	}
    
    
function checa_proximo(campo){
	
    var form = document.forms[0];
	switch (campo){
		case "idLocalidade" : {
			if(form.idLocalidade.value.length == 3){
				form.idSetorComercial.focus();
			}	
			break;
		}
		case "idSetorComercial" : {
			if(form.idSetorComercial.value.length == 3){
				form.idQuadra.focus();
			}	
			break;
		}
		case "idQuadra" : {
			
			if(form.idQuadra.value.length == 4){
					
				if (form.idQuadraFace == null || form.idQuadraFace == 'undefined'){
					form.lote.focus();
				}
				else{
					form.idQuadraFace.focus();
				}
			}
				
			break;
		}
		case "lote" : {
			if(form.lote.value.length == 4){
				form.subLote.focus();
			}	
			break;
		}
		case "subLote" : {
			if(form.subLote.value.length == 3){
				form.testadaLote.focus();
			}	
			break;
		}
	}	
}
    
</script>


<script language="JavaScript">

  //Recebe os dados do(s) popup(s)

  function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'localidade') {
      if(form.idLocalidade.value != codigoRegistro){
        limparSetorComercialQuadra()
      }
      limparPesquisaLocalidade();
      form.idLocalidade.value = codigoRegistro;
      form.localidadeDescricao.value = descricaoRegistro;
      form.localidadeDescricao.style.color = "#000000";
      
      form.idSetorComercial.focus();
    }

    if (tipoConsulta == 'quadra') {
      limparPesquisaQuadra();
      form.idQuadra.value = codigoRegistro;
      
      form.lote.focus();
    }

    if (tipoConsulta == 'setorComercial') {
      if(form.idSetorComercial.value != codigoRegistro){
	limparPesquisaQuadra();
      }
      limparPesquisaSetorComercial();
      form.idSetorComercial.value = codigoRegistro;
      form.setorComercialDescricao.value = descricaoRegistro;
      form.setorComercialDescricao.style.color = "#000000";
      
      form.idQuadra.focus();
    }
  }

function limparPesquisaLocalidade() {
    var form = document.forms[0];

      form.idLocalidade.value = "";
      form.localidadeDescricao.value = "";


  }

function limparPesquisaQuadra() {
    var form = document.forms[0];

  	form.idQuadra.value = "";
	var msgQuadra = document.getElementById("msgQuadra");
	
	if (msgQuadra != null){
		msgQuadra.innerHTML = "";
	}
	
	if (form.idQuadraFace != null && form.idQuadraFace != 'undefined'){
		reiniciarListBox(form.idQuadraFace);
		
		var msgQuadraFace = document.getElementById("msgQuadraFace");
	
		if (msgQuadraFace != null){
			msgQuadraFace.innerHTML = "";
		}
	}
	
  }

function limparPesquisaSetorComercial() {
    var form = document.forms[0];

      form.idSetorComercial.value = "";
      form.setorComercialDescricao.value = "";
}

function limparSetorComercialQuadra(){
  	var form = document.forms[0];

  	form.idSetorComercial.value = "";
  	form.setorComercialDescricao.value = "";

  	form.idQuadra.value = "";
	var msgQuadra = document.getElementById("msgQuadra");
	
	if (msgQuadra != null){
		msgQuadra.innerHTML = "";
	}
	
	if (form.idQuadraFace != null && form.idQuadraFace != 'undefined'){
		reiniciarListBox(form.idQuadraFace);
		
		var msgQuadraFace = document.getElementById("msgQuadraFace");
	
		if (msgQuadraFace != null){
			msgQuadraFace.innerHTML = "";
		}
	}
}

function limparDescricaoQuadra(){
	
	var form = document.forms[0];
	var msgQuadra = document.getElementById("msgQuadra");
	
	if (msgQuadra != null){
		msgQuadra.innerHTML = "";
	}
	
	if (form.idQuadraFace != null && form.idQuadraFace != 'undefined'){
		reiniciarListBox(form.idQuadraFace);
		
		var msgQuadraFace = document.getElementById("msgQuadraFace");
	
		if (msgQuadraFace != null){
			msgQuadraFace.innerHTML = "";
		}
	}
}

function limparDescricaoLocalidade(){
    var form = document.forms[0];
    form.localidadeDescricao.value = "";

}
function limparDescricaoSetorComercial(){
    var form = document.forms[0];
    form.setorComercialDescricao.value = "";


}

function valorExistenciaLocalidade(){
	
   var form = document.forms[0];
   if(form.idLocalidade.value == ''){
   		form.idSetorComercial.value = "";
  		alert("Informe Localidade") ;
   }
}  
function valorExistenciaSetorComercial(){
   var form = document.forms[0];
   if(form.idSetorComercial.value == ''){
   		form.idQuadra.value = "";
  		alert("Informe Setor Comercial") ;
   }
}  


function limparPesquisaLocalidade() {
    var form = document.forms[0];

      form.idLocalidade.value = "";
      form.localidadeDescricao.value = "";


  }
function limparPesquisaSetorComercial() {
    var form = document.forms[0];

      form.idSetorComercial.value = "";
      form.setorComercialDescricao.value = "";


  }
function limparPesquisaQuadra() {
    var form = document.forms[0];

    form.idQuadra.value = "";

	var msgQuadra = document.getElementById("msgQuadra");
	
	if (msgQuadra != null){
		msgQuadra.innerHTML = "";
	}

	if (form.idQuadraFace != null && form.idQuadraFace != 'undefined'){
		reiniciarListBox(form.idQuadraFace);
		
		var msgQuadraFace = document.getElementById("msgQuadraFace");
	
		if (msgQuadraFace != null){
			msgQuadraFace.innerHTML = "";
		}
	}
  }

function limparPesquisaDescricaoLocalidade() {
    var form = document.forms[0];
      form.localidadeDescricao.value = "";
  }

function validarQuadra(nomeCampo, idDependencia, nomeDependencia){
      
	var form = document.forms[0];
	
	var objetoCampo = nomeCampo;
	var valorCampo = nomeCampo.value;

	if(idDependencia.length < 1 || isNaN(idDependencia) || ((idDependencia * 1) == 0) ||
	   idDependencia.indexOf(',') != -1 || idDependencia.indexOf('.') != -1 || ((idDependencia * 1) == 0)){
		
		alert('Informe ' + nomeDependencia);
		form.idSetorComercial.focus();
		
	}
	else if (!isNaN(valorCampo) && valorCampo.length > 0 && valorCampo.indexOf(',') == -1 &&
			 valorCampo.indexOf('.') == -1 && ((valorCampo * 1) > 0)){
      	
		objetoCampo.value = trim(valorCampo);
		
		if (objetoCampo.value.length > 0){
			
			form.action = "atualizarImovelWizardAction.do?action=exibirAtualizarImovelLocalidadeAction";
			toUpperCase(form);
        	form.submit();
		}
	}	
}

</script>
</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<div id="formDiv">
<html:form action="/atualizarImovelWizardAction" method="post" onsubmit="return validateManterImovelActionForm(this);setarFoco('${requestScope.nomeCampo}');">

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_tela_espera.jsp?numeroPagina=1"/>


<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
<input type="hidden" name="numeroPagina" value="1"/>
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
      </div></td>
    <td width="660" valign="top" class="centercoltext">
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Atualizar</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
            <table width="100%" border="0">
              <tr>
                <td colspan="2">Para atualizar um im&oacute;vel, informe os dados abaixo:</td>
              	<logic:present scope="application" name="urlHelp">
							<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroImovelAtualizarAbaLocalidade', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
				</logic:present>
				<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
				</logic:notPresent>
              </tr>
              </table>
<!-- **************************************************** -->
<table width="100%" border="0">
<html:hidden property="usaRota" />
				<tr>
				  <td><strong>Matrícula:</strong></td>	
				  <td>
					<html:hidden property="matricula"/>
					<bean:write name="ManterImovelActionForm" property="matricula"/>
		  		  </td>				
				</tr>
                 <tr>
		   			<td width="19%"><strong>Localidade:<font color="#FF0000">*</font></strong></td>
                   	<td width="81%" height="24">
                   	
                   		<html:text maxlength="3" property="idLocalidade" tabindex="1" size="3" 
					 	onkeypress="javascript:limparPesquisaDescricaoLocalidade();limparPesquisaQuadra();limparPesquisaSetorComercial(); 
					 	 validaEnter(event, 'atualizarImovelWizardAction.do?action=exibirAtualizarImovelLocalidadeAction', 'idLocalidade');
					 	 return isCampoNumerico(event);"/>
                      
                      	<!--  onkeyup="checa_proximo(this.name);" -->
                      	
                      	<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade', 400, 800);limparPesquisaSetorComercialQuadra();">
                        <img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"/></a>

   				      	<logic:present name="codigoLocalidadeNaoEncontrada" scope="request">
							
							<html:text property="localidadeDescricao" size="50" readonly="true" 
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
							
                      	</logic:present>

						<logic:notPresent name="codigoLocalidadeNaoEncontrada" scope="request">
                        	
                        	<html:text property="localidadeDescricao" size="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
                      	
                      	</logic:notPresent>
						
						<a href="javascript:limparPesquisaLocalidade();limparPesquisaSetorComercial();limparPesquisaQuadra();document.forms[0].idLocalidade.focus();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>                   
						
                   </td>
                 </tr>
                 <tr>
                   <td><strong>Setor Comercial:<font color="#FF0000">*</font></strong></td>
                   <td height="24">
                   
                   		<html:text maxlength="3" property="idSetorComercial" size="3" tabindex="2" 
                    	onkeypress="javascript:valorExistenciaLocalidade();limparDescricaoSetorComercial();limparPesquisaQuadra(); 
                    	validaEnterDependencia(event, 'atualizarImovelWizardAction.do?action=exibirAtualizarImovelLocalidadeAction', this, document.forms[0].idLocalidade.value, 'Localidade');
                    	return isCampoNumerico(event);"/>
                      
                      	<!--  onkeyup="checa_proximo(this.name);" -->
                      	
                      	<a href="javascript:abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidade.value+'&tipo=SetorComercial',document.forms[0].idLocalidade.value,'Localidade', 400, 800);">
						<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"/></a>
   		      
   		      			<logic:present name="codigoSetorComercialNaoEncontrada" scope="request">
			
							<html:text property="setorComercialDescricao" size="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
                      
                      	</logic:present>

                      	<logic:notPresent name="codigoSetorComercialNaoEncontrada" scope="request">
                        	
                        	<html:text property="setorComercialDescricao" size="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
                      
                      	</logic:notPresent>
						
						<a href="javascript:limparPesquisaSetorComercial();limparPesquisaQuadra();document.forms[0].idSetorComercial.focus();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>
                   </td>
                 </tr>
                 <tr>
                   <td><strong>Quadra:<font color="#FF0000">*</font></strong></td>
                   <td height="24">
                   
                   		<html:text maxlength="<%=""+SistemaParametro.NUMERO_DIGITOS_QUADRA%>" property="idQuadra" size="3" tabindex="3" 
                    	onkeypress="javascript:valorExistenciaSetorComercial();limparDescricaoQuadra();
                    	validaEnterDependencia(event, 'atualizarImovelWizardAction.do?action=exibirAtualizarImovelLocalidadeAction', this, document.forms[0].idSetorComercial.value, 'Setor Comercial');
                    	return isCampoNumerico(event);"
                   		onblur="validarQuadra(this, document.forms[0].idSetorComercial.value, 'Setor Comercial');"/>
					 	
					 	<!--  onkeyup="checa_proximo(this.name);" -->
					 	
					 	<logic:present name="msgQuadra" scope="request">
					 
					 		<logic:present name="codigoQuadraNaoEncontrada" scope="request">
								
								<span style="color:#ff0000" id="msgQuadra"><bean:write scope="request" name="msgQuadra"/></span>
                      
                      		</logic:present>
                      
                      		<logic:notPresent name="codigoQuadraNaoEncontrada" scope="request">
								
								<span style="color:#000000" id="msgQuadra"><bean:write scope="request" name="msgQuadra"/></span>
                      
                      		</logic:notPresent>                   
                      
                      	</logic:present>
                   </td>
                 </tr>
				
				<logic:present name="exibirQuadraFace" scope="request">
              
              	<tr>
                	<td height="24"><strong>Face:<font color="#FF0000">*</font></strong></td>
                	<td colspan="2">
                
                		<html:select property="idQuadraFace" style="width: 100px;" tabindex="4">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:present name="facesQuadra">
								<html:options collection="facesQuadra" labelProperty="numeroQuadraFace" property="id"/>
							</logic:present>
						</html:select>
						
						<logic:present name="msgQuadraFace" scope="request">
							<span style="color:#FF0000" id="msgQuadraFace"><bean:write scope="request" name="msgQuadraFace"/></span>
						</logic:present>
					
                	</td>
              	</tr>
              
              	</logic:present>

<!-- **************************************************** -->
              <tr>
                <td height="24"><strong>Lote:<font color="#FF0000">*</font></strong></td>
                <td><html:text maxlength="4" property="lote" tabindex="5" size="4"
                onkeypress="return isCampoNumerico(event);" /> <!--  onkeyup="checa_proximo(this.name);" --> </td>
              </tr>
              <tr>
                <td height="24"><strong>Sublote:<font color="#FF0000">*</font></strong></td>
                <td><html:text maxlength="3" property="subLote" tabindex="6" size="3"
                onkeypress="return isCampoNumerico(event);" /> <!--  onkeyup="checa_proximo(this.name);" --> </td>
              </tr>
              <tr>
                <td height="24"><strong>Testada do Lote:</strong></td>
                <td><html:text maxlength="4" property="testadaLote" tabindex="7" size="4"
                onkeypress="return isCampoNumerico(event);" /></td>
              </tr>
             
              <tr>
					<td height="24">
						<strong>Sequência na Rota:<logic:present name="usaRota" ><font color="#FF0000">*</font></logic:present>							 	
						 	</strong>
					</td>
					<td>
						<html:text maxlength="4" 
								   size="4" 
								   property="sequencialRota" 
								   tabindex="8"
								   onkeypress="return isCampoNumerico(event);" />
					</td>
			  </tr>

              <tr>
                <td height="24">&nbsp;</td>
                <td><strong><font color="#FF0000">*</font></strong> Campo obrigat&oacute;rio</td>
              </tr>
              <tr>
                <td height="0" colspan="2">
                  <div align="right"> </div></td>
              </tr>
               <tr>
                 <td colspan="2">
					<div align="right">
						<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_tela_espera.jsp?numeroPagina=1"/>
					</div>
				</td>
               </tr>
            </table>
      <p>&nbsp;</p>
    </td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>
<%@ include file="/jsp/util/tooltip.jsp" %>

</html:form>
</div>
</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/atualizarImovelWizardAction.do?concluir=true&action=atualizarImovelLocalidadeAction'); }
</script>

</html:html>
