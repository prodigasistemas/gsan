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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="InserirImovelActionForm" dynamicJavascript="false" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">

    var bCancel = false;

    function validateInserirImovelActionForm(form) {
        if (bCancel)
      return true;
        else
       return validateRequired(form)
              && testarCampoValorZero(document.InserirImovelActionForm.idLocalidade, 'Localidade') 
              && testarCampoValorZero(document.InserirImovelActionForm.idSetorComercial, 'Setor Comercial')
              && testarCampoValorZero(document.InserirImovelActionForm.idQuadra, 'Quadra')
              && testarCampoValorZero(document.InserirImovelActionForm.testadaLote, 'Testada do Lote') 
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
			alert('Informe Sequência na Rota.');
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
      limparPesquisaLocalidade()
      form.idLocalidade.value = codigoRegistro;
      form.localidadeDescricao.value = descricaoRegistro;
      form.localidadeDescricao.style.color = "#000000";
      form.idSetorComercial.focus();
    }

    if (tipoConsulta == 'quadra') {
      limparPesquisaQuadra()
      form.idQuadra.value = codigoRegistro;
      form.lote.focus();
    }

    if (tipoConsulta == 'setorComercial') {
      limparPesquisaSetorComercial()
      form.idSetorComercial.value = codigoRegistro;
      form.setorComercialDescricao.value = descricaoRegistro;
      form.setorComercialDescricao.style.color = "#000000";
      form.idQuadra.focus();
    }
  }

	function limparPesquisaDescricaoLocalidade() {
    	var form = document.forms[0];
      	form.localidadeDescricao.value = "";
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
  

	function valorExistenciaLocalidade(){
		
	   var form = document.forms[0];
	   if(form.idLocalidade.value == ''){
	   		form.idSetorComercial.value = "";
	  		alert("Informe Localidade.") ;
	   }
	}  
	function valorExistenciaSetorComercial(){
	   var form = document.forms[0];
	   if(form.idSetorComercial.value == ''){
	   		form.idQuadra.value = "";
	  		alert("Informe Setor Comercial.") ;
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
			
			form.action = "inserirImovelWizardAction.do?action=exibirInserirImovelLocalidadeAction";
			toUpperCase(form);
        	form.submit();
		}
	}	
}

</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<div id="formDiv">
<html:form action="/inserirImovelWizardAction" method="post" onsubmit="return validateInserirImovelActionForm(this);">

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
          <td class="parabg">Inserir Imóvel</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
            <table width="100%" border="0">
               <tr>
                <td>Para inserir um im&oacute;vel, informe os dados abaixo:</td>
              	<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroImovelInserirAbaLocalidade', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
				</logic:present>
				<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
				</logic:notPresent>
              </tr>
              </table>
              <table width="100%" border="0">
              <html:hidden property="usaRota" />
                 <tr>
		   <td width="22%"><strong>Localidade:<font color="#FF0000">*</font></strong></td>
                   <td width="81%" height="24" colspan="2"><html:text maxlength="3" property="idLocalidade" size="3"  tabindex="1" 
                   onkeypress="javascript:limparPesquisaDescricaoLocalidade();limparPesquisaQuadra();limparPesquisaSetorComercial(); return validaEnter(event, 'inserirImovelWizardAction.do?action=exibirInserirImovelLocalidadeAction', 'idLocalidade');"/>
                      <a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade', 400, 800);limparPesquisaQuadra();limparPesquisaSetorComercial();">
                         <img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"/></a>
					
					<!--  onkeyup="checa_proximo(this.name);" -->
					
   		      <logic:present name="codigoLocalidadeNaoEncontrada" scope="request">
			<input type="text" name="localidadeDescricao" size="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" value="<bean:message key="atencao.localidade.inexistente"/>"/>
                      </logic:present>

                      <logic:notPresent name="codigoLocalidadeNaoEncontrada" scope="request">
                        <html:text property="localidadeDescricao" size="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
                      </logic:notPresent>
						<a href="javascript:limparPesquisaLocalidade();limparPesquisaSetorComercial();limparPesquisaQuadra();document.forms[0].idLocalidade.focus();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>                   
						</td>
                 </tr>
                 <tr>
                   <td><strong>Setor Comercial:<font color="#FF0000">*</font></strong></td>
                   <td height="24" colspan="2"><html:text maxlength="3" property="idSetorComercial" size="3"  
                   tabindex="2" onkeypress="javascript:limparDescricaoSetorComercial();limparPesquisaQuadra(); return validaEnterDependencia(event, 'inserirImovelWizardAction.do?action=exibirInserirImovelLocalidadeAction', this, document.forms[0].idLocalidade.value, 'Localidade');"/>
                      
                      <!--  onkeyup="checa_proximo(this.name);" -->
                      
                      <a href="javascript:abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidade.value+'&tipo=SetorComercial',document.forms[0].idLocalidade.value,'Localidade', 400, 800);limparPesquisaQuadra();">
			<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"/></a>
   		      <logic:present name="codigoSetorComercialNaoEncontrada" scope="request">
			<input type="text" name="setorComercialDescricao" size="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" value="<bean:message key="atencao.setor_comercial.inexistente"/>"/>
                      </logic:present>

                      <logic:notPresent name="codigoSetorComercialNaoEncontrada" scope="request">
                        <html:text property="setorComercialDescricao" size="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
                      </logic:notPresent>
						<a href="javascript:limparPesquisaSetorComercial();limparPesquisaQuadra();document.forms[0].idSetorComercial.focus();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>
                   </td>
                 </tr>
               <tr>
                   <td><strong>Quadra:<font color="#FF0000">*</font></strong></td>
                   <td height="24" colspan="2"><html:text maxlength="<%=""+SistemaParametro.NUMERO_DIGITOS_QUADRA%>" property="idQuadra" size="4"  tabindex="3"
                   onkeypress="javascript:limparDescricaoQuadra(); return validaEnterDependencia(event, 'inserirImovelWizardAction.do?action=exibirInserirImovelLocalidadeAction', this, document.forms[0].idSetorComercial.value, 'Setor Comercial');"
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
              
              <tr>
                <td height="24"><strong>Lote:<font color="#FF0000">*</font></strong></td>
                <td colspan="2"><html:text maxlength="4" property="lote" size="4"  tabindex="5" /> <!--  onkeyup="checa_proximo(this.name);" --> </td>
              </tr>
              <tr>
                <td height="24"><strong>Sublote:<font color="#FF0000">*</font></strong></td>
                <td colspan="2"><html:text maxlength="3" property="subLote" size="4"  tabindex="6"/> <!--  onkeyup="checa_proximo(this.name);" --></td>
              </tr>
              <tr>
                <td height="24"><strong>Testada do Lote:</strong></td>
                <td colspan="2"><html:text maxlength="4" property="testadaLote" size="4"  tabindex="7"/></td>
              </tr>
              <tr>	
              		<logic:equal value="1" property="usaRota" name="InserirImovelActionForm">
              		<td height="24"><strong>Sequência na Rota:<font color="#FF0000">*</font></strong></td>
              		</logic:equal>
              		<logic:notEqual value="1" property="usaRota" name="InserirImovelActionForm">
					<td height="24"><strong>Sequência na Rota:<font
						color="#FF0000"></font></strong></td>
					</logic:notEqual>
					<td><html:text maxlength="4" size="4" property="sequencialRota" tabindex="8"/></td>
			  </tr>
              <tr>
                <td height="24">&nbsp;</td>
                <td colspan="2"> <strong><font color="#FF0000">*</font></strong> Campos obrigat&oacute;rios</td>
              </tr>
              <tr>
                <td height="0" colspan="3">
                  <div align="right"> </div></td>
              </tr>
               <tr>
                 <td colspan="3">
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

</html:form>
</div>
</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/inserirImovelWizardAction.do?concluir=true&action=inserirImovelLocalidadeAction'); }
</script>


</html:html>
