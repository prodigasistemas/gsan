<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.ConstantesSistema" %>
<%@ page import="gcom.cadastro.localidade.GerenciaRegional" %>
<%@ page import="gcom.cadastro.localidade.UnidadeNegocio" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InformarDadosGeracaoRelatorioConsultaActionForm"/>

<SCRIPT LANGUAGE="JavaScript">
<!--


function validarForm(form){
	if (validateInformarDadosGeracaoRelatorioConsultaActionForm(form)){
	
		if (verificaAnoMes(form.mesAnoFaturamento)){
		    if(form.grupoCobranca == null && form.grupoFaturamento.disabled == false && form.grupoFaturamento.value.length < 1){
		      	  alert("Informe Grupo de Faturamento");
				  form.grupoFaturamento.focus();
			}else if(form.grupoCobranca != null && form.grupoCobranca.disabled == false && form.grupoCobranca.value.length < 1){
		      	  alert("Informe Grupo de Cobrança");
				  form.grupoCobranca.focus();			  
			}
			else if (form.gerencialRegional.disabled == false && form.gerencialRegional.value.length < 1){
				alert("Informe Gerência Regional");
				form.gerencialRegional.focus();
			}
			else if (form.unidadeNegocio.disabled == false && form.unidadeNegocio.value.length < 1){
				alert("Informe Unidade Negocio");
				form.unidadeNegocio.focus();
			}
			else if (form.localidade.disabled == false && form.localidade.value.length < 1 && form.opcaoTotalizacao.value != "28"){
				alert("Informe Localidade");
				form.localidade.focus();
			}
			else if (form.localidade.disabled == false && !testarCampoValorZero(form.localidade, "Localidade")){
				form.localidade.focus();
			}
			else if (form.setorComercial.disabled == false && form.setorComercial.value.length < 1 && form.opcaoTotalizacao.value != "28"){
				alert("Informe Setor Comercial");
				form.setorComercial.focus();
			}
			else if (form.setorComercial.disabled == false && !testarCampoValorZero(form.setorComercial, "Setor Comercial")){
				form.setorComercial.focus();
			}
			else if (form.quadra.disabled == false && form.quadra.value.length < 1){
				alert("Informe Quadra");
				form.quadra.focus();
			}
			else if (form.quadra.disabled == false && !testarCampoValorZero(form.quadra, "Quadra")){
				form.quadra.focus();
			}
			else if (form.tipoAnaliseFaturamento == undefined){
				
				if (direcionarJSP(document.forms[0].opcaoTotalizacao.value)){
					submeterFormPadrao(form);
				}
				else{
				    var tipoResumo = document.getElementById("tipoResumo").value;
				    //caso seja para chamar o caso de uso Consultar Resumodas Ações de Cobrança 
				    //então não chama a tela para escolher o relatório
				    
				    if(tipoResumo == 'ACAOCOBRANCA'){
				      submeterFormPadrao(form);
				    }else{
				     toggleBox('demodiv',1);
				    }
					
				}
				
			}
			else if (form.tipoAnaliseFaturamento[0].checked == false &&
					 form.tipoAnaliseFaturamento[1].checked == false){
				alert("Informe Tipo de Análise");
			}
			else{
				if (direcionarJSP(document.forms[0].opcaoTotalizacao.value)){
					submeterFormPadrao(form);
				}
				else{
				    var tipoResumo = document.getElementById("tipoResumo").value;
					//caso seja para chamar o caso de uso Consultar Resumodas Ações de Cobrança 
				    //então não chama a tela para escolher o relatório
				    if(tipoResumo == 'ACAOCOBRANCA'){
				      submeterFormPadrao(form);
				    }else{
				     toggleBox('demodiv',1);
				    }
				}
			}
		}
	}	
}


function gerenciadorHabilitacaoCampos(opcaoSelected){

	var form = document.forms[0];

	if (opcaoSelected == "6" || opcaoSelected == "20"){
	
	   form.gerencialRegional.value = "";
       limparUnidadeNegocio();
       limparLocalidade();
       limparMunicipio()
       
       if(form.grupoCobranca == null){
	     form.grupoFaturamento.disabled = false;
	   }else{
	     form.grupoCobranca.disabled = false;
	   }
       form.gerencialRegional.disabled = true;
       form.unidadeNegocio.disabled = true;
       form.localidade.disabled = true;
       form.setorComercial.disabled = true;
       form.quadra.disabled = true;
	   form.municipio.disabled = true;
		      
	}
	else if (opcaoSelected == "7" || opcaoSelected == "23" || opcaoSelected == "9"){
	
	   if(form.grupoCobranca == null){
	     form.grupoFaturamento.value = "";
	   }else{
	     form.grupoCobranca.value = "";
	   }
       limparUnidadeNegocio();
       limparLocalidade();
       limparMunicipio()
       
       if(form.grupoCobranca == null){
	     form.grupoFaturamento.disabled = true;
	   }else{
	     form.grupoCobranca.disabled = true;
	   }
       form.gerencialRegional.disabled = false;
       form.unidadeNegocio.disabled = true;
       form.localidade.disabled = true;
       form.setorComercial.disabled = true;
       form.quadra.disabled = true;
       form.municipio.disabled = true;
       
    }
	else if (opcaoSelected == "21" || opcaoSelected == "24" || opcaoSelected == "25"){
	
	   if(form.grupoCobranca == null){
	     form.grupoFaturamento.value = "";
	   }else{
	     form.grupoCobranca.value = "";
	   }
       form.gerencialRegional.value = "";
       limparLocalidade();
       limparMunicipio()
       
       if(form.grupoCobranca == null){
	     form.grupoFaturamento.disabled = true;
	   }else{
	     form.grupoCobranca.disabled = true;
	   }
       form.gerencialRegional.disabled = true;
       form.unidadeNegocio.disabled = false;
       form.localidade.disabled = true;
       form.setorComercial.disabled = true;
       form.quadra.disabled = true;
       form.municipio.disabled = true;
 
 	}
	else if (opcaoSelected == "13" || opcaoSelected == "14" || opcaoSelected == "15"){
	
	   if(form.grupoCobranca == null){
	     form.grupoFaturamento.value = "";
	   }else{
	     form.grupoCobranca.value = "";
	   }
       form.gerencialRegional.value = "";
       limparUnidadeNegocio();
       limparSetorComercial();
       limparMunicipio()
       
       if(form.grupoCobranca == null){
	     form.grupoFaturamento.disabled = true;
	   }else{
	     form.grupoCobranca.disabled = true;
	   }
       form.gerencialRegional.disabled = true;
       form.unidadeNegocio.disabled = true;
       form.localidade.disabled = false;
       form.setorComercial.disabled = true;
       form.quadra.disabled = true;
       form.municipio.disabled = true;
       
    }
	else if (opcaoSelected == "16" || opcaoSelected == "17"){
	
	   if(form.grupoCobranca == null){
	     form.grupoFaturamento.value = "";
	   }else{
	     form.grupoCobranca.value = "";
	   }
       form.gerencialRegional.value = "";
       limparUnidadeNegocio();
       limparQuadra();
	   limparMunicipio()
	   
       if(form.grupoCobranca == null){
	     form.grupoFaturamento.disabled = true;
	   }else{
	     form.grupoCobranca.disabled = true;
	   }
       form.gerencialRegional.disabled = true;
       form.unidadeNegocio.disabled = true;
       form.localidade.disabled = false;
       form.setorComercial.disabled = false;
       form.quadra.disabled = true;
       form.municipio.disabled = true;
    }
	else if (opcaoSelected == "18"){
	
	   if(form.grupoCobranca == null){
	     form.grupoFaturamento.value = "";
	   }else{
	     form.grupoCobranca.value = "";
	   }
       form.gerencialRegional.value = "";
       limparUnidadeNegocio();
       limparMunicipio()
       
       if(form.grupoCobranca == null){
	     form.grupoFaturamento.disabled = true;
	   }else{
	     form.grupoCobranca.disabled = true;
	   }
       form.gerencialRegional.disabled = true;
       form.unidadeNegocio.disabled = true;
       form.localidade.disabled = false;
       form.setorComercial.disabled = false;
       form.quadra.disabled = false;
       form.municipio.disabled = true;
       
    }
    else if(opcaoSelected == "27"){
    
    	if(form.grupoCobranca == null){
		  form.grupoFaturamento.value = "";
		}else{
		  form.grupoCobranca.value = "";
		}
       	form.gerencialRegional.value = "";
       	limparUnidadeNegocio();
       	limparLocalidade();
       	
       	if(form.grupoCobranca == null){
		  form.grupoFaturamento.disabled = true;
		}else{
		  form.grupoCobranca.disabled = true;
		}
        form.gerencialRegional.disabled = true;
        form.unidadeNegocio.disabled = true;
        form.localidade.disabled = true;
        form.setorComercial.disabled = true;
        form.quadra.disabled = true;
        form.municipio.disabled = false;
        
    }else if(opcaoSelected == "28"){
    
 	   if(form.grupoCobranca == null){
	     form.grupoFaturamento.value = "";
	   }else{
	     form.grupoCobranca.value = "";
	   }
       form.gerencialRegional.value = "";
       limparUnidadeNegocio();
       limparQuadra();
	   limparMunicipio()
	   
       if(form.grupoCobranca == null){
	     form.grupoFaturamento.disabled = true;
	   }else{
	     form.grupoCobranca.disabled = true;
	   }
       form.gerencialRegional.disabled = true;
       form.unidadeNegocio.disabled = true;

       if(form.idRota.value != null && form.idRota.value != ''){
    	   form.localidade.disabled = true;
           form.setorComercial.disabled = true;
       }else{
    	   form.localidade.disabled = false;
           form.setorComercial.disabled = false;
       }
       
       form.quadra.disabled = true;
       form.municipio.disabled = true;
    }
	else{
		if(form.grupoCobranca == null){
		  form.grupoFaturamento.value = "";
		}else{
		  form.grupoCobranca.value = "";
		}
       	form.gerencialRegional.value = "";
       	limparUnidadeNegocio();
       	limparLocalidade();
       	limparMunicipio()
       	
       	if(form.grupoCobranca == null){
		  form.grupoFaturamento.disabled = true;
		}else{
		  form.grupoCobranca.disabled = true;
		}
        form.gerencialRegional.disabled = true;
        form.unidadeNegocio.disabled = true;
        form.localidade.disabled = true;
        form.setorComercial.disabled = true;
        form.quadra.disabled = true;
        form.municipio.disabled = true;
	}
}

function gerenciadorHabilitacaoImagemPesquisa(opcaoSelected, tipoPesquisa){

	switch (tipoPesquisa) { 
    case "Localidade": 
        
        if (opcaoSelected == "13" || opcaoSelected == "14" || opcaoSelected == "15" ||
        	opcaoSelected == "16" || opcaoSelected == "17" || opcaoSelected == "18"){
        	
			abrirPopup('exibirPesquisarLocalidadeAction.do', 320, 810);
			limparSetorComercial();
		}
		
       break; 
    case "SetorComercial": 
        
        if (opcaoSelected == "16" || opcaoSelected == "17" || opcaoSelected == "18"){
        	
			abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].localidade.value+'&tipo=SetorComercial',document.forms[0].localidade.value,'Localidade', 400, 800);
			limparQuadra();
		}
		
       break;
    case "Quadra": 
        
        if (opcaoSelected == "18"){
        	
			abrirPopupDependencia('exibirPesquisarQuadraAction.do?idSetorComercial='+document.forms[0].idSetorComercial.value+'&tipo=Quadra', document.forms[0].setorComercial.value, 'Setor Comercial',400, 800);
		}
		
       break;
    case "Município":
    	if(opcaoSelected == "27"){   
			abrirPopup('exibirPesquisarMunicipioAction.do', 320, 810);
    	}
    default:
    
    }
}


function gerenciadorHabilitacaoImagemLimpar(opcaoSelected, tipoPesquisa){

	switch (tipoPesquisa) { 
    case "Localidade": 
        
        if (opcaoSelected == "13" || opcaoSelected == "14" || opcaoSelected == "15" ||
        	opcaoSelected == "16" || opcaoSelected == "17" || opcaoSelected == "18" || opcaoSelected == "28"){
        	
			limparLocalidade();
		}
		
       break; 
    case "SetorComercial": 
        
        if (opcaoSelected == "16" || opcaoSelected == "17" || opcaoSelected == "18" || opcaoSelected == "28"){
        	
			limparSetorComercial();
		}
		
       break;
    case "Quadra": 
        
        if (opcaoSelected == "18"){
        	
			limparQuadra();
		}
		
       break;
    case "Município":
	    	if(opcaoSelected == "27"){
    		limparMunicipio();
    	}
    default:
    
    }
}


function limparUnidadeNegocio(){
	var form = document.forms[0];
	
	form.unidadeNegocio.disabled = false;
	
	form.unidadeNegocio.value = "";

}

function limparLocalidade(){
	var form = document.forms[0];
	
	form.localidade.disabled = false;
	
	form.localidade.value = "";
	form.descricaoLocalidade.value = "";
	
	var opcaoTotalizacao = form.opcaoTotalizacao.value;
	
	if (opcaoTotalizacao != "13" && opcaoTotalizacao != "14" && opcaoTotalizacao != "15"){
		limparSetorComercial();
	}
	
}

function limparSetorComercial(){
	var form = document.forms[0];
	
	form.setorComercial.disabled = false;
	
	form.setorComercial.value = "";
	form.idSetorComercial.value = "";
	form.descricaoSetorComercial.value = "";
	
	var opcaoTotalizacao = form.opcaoTotalizacao.value;
	
	if (opcaoTotalizacao != "16" && opcaoTotalizacao != "17"){
		limparQuadra();
	}
	
}

function limparQuadra(){
	var form = document.forms[0];

	if(form.opcaoTotalizacao.value != "28"){
		form.quadra.disabled = false;
	}
	
	
	form.quadra.value = "";
	var msgQuadra = document.getElementById("msgQuadra");
	
	if (msgQuadra != null){
		msgQuadra.innerHTML = "";
	}
	
}

function limparMunicipio(){
	var form = document.forms[0];
	form.municipio.disabled = false;
	form.municipio.value = "";
	form.descricaoMunicipio.value = "";
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];
    
    if (tipoConsulta == 'localidade') {
    	limparLocalidade();
      	form.localidade.value = codigoRegistro;
      	form.descricaoLocalidade.value = descricaoRegistro;
      	form.descricaoLocalidade.style.color = "#000000";
    
    	if (form.setorComercial.disabled){
	  		setarFoco('perfilImovel');
	  	}
	  	else{
	  		form.setorComercial.focus();
	  	}
    }

    if (tipoConsulta == 'setorComercial') {
      	limparSetorComercial();
      	form.setorComercial.value = codigoRegistro;
      	form.descricaoSetorComercial.value = descricaoRegistro;
      	form.descricaoSetorComercial.style.color = "#000000";
    
    	if (form.quadra.disabled){
	  		setarFoco('perfilImovel');
	  	}
	  	else{
	  		form.quadra.focus();
	  	}
    }
    
    if (tipoConsulta == 'quadra') {
      	limparQuadra();
      	form.quadra.value = codigoRegistro;
      	form.descricaoQuadra.value = descricaoRegistro;
      	form.descricaoQuadra.style.color = "#000000";
    
    	setarFoco('perfilImovel');
    }
    
    if(tipoConsulta == 'municipio'){
    	form.municipio.value = codigoRegistro;
      	form.descricaoMunicipio.value = descricaoRegistro;
      	form.descricaoMunicipio.style.color = "#000000";
    }
    
    gerenciadorHabilitacaoCampos(form.opcaoTotalizacao.value);

}


function direcionarJSP(opcaoTotalizacao){

	retorno = false;
	var codigosJSP = document.getElementById("jsp").value;
	var codigosJSPArray = codigosJSP.split(",");
	
	for (index = 0; index < codigosJSPArray.length; index++){
		
		if (trim(codigosJSPArray[index]) == trim(opcaoTotalizacao)){
			retorno = true;
			break;	
		}
	}
	
	return retorno;
	
}

function limparRota() {

	var form = document.forms[0];
  	form.idRota.value = "";
  	form.codigoRota.value = "";
  	form.localidade.value = "";
  	form.setorComercial.value = "";
  	form.localidade.disabled = false;
    form.setorComercial.disabled = false;
}

function pesquisarRota() {
	var form = document.forms[0];

	if((form.localidade.value == null || form.localidade.value == "") && (form.setorComercial.value ==null || form.setorComercial.value =="") ){
		abrirPopup('exibirPesquisarInformarRotaLeituraAction.do?limparForm=sim&destinoRota=Inicial&idFaturamentoGrupo='+form.grupoFaturamento.value, 400, 800);
	}
	
}

function receberRota(codigoRota,descricao, codigo,destino) {
	  var form = document.forms[0];
	  if(destino == 'Inicial'){
	 	  form.idRota.value = codigoRota;
	  }

	  form.action = 'exibirInformarDadosGeracaoRelatorioConsultaAction.do?menu=sim&analiseFaturamento=ok&tipoResumo=ANALISE&id=464';
	  form.submit();
} 

//-->
</SCRIPT>


</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}'); ">

<INPUT TYPE="hidden" NAME="jsp" VALUE="${requestScope.jsp}" ID="jsp">
<INPUT TYPE="hidden" VALUE="${sessionScope.tipoResumo}" ID="tipoResumo">

<%
String tipoResumoSessao = (String)session.getAttribute("tipoResumo");
pageContext.setAttribute("tipoResumoSessao", tipoResumoSessao);
%>

<html:form action="/informarDadosGeracaoRelatorioConsultaAction" method="post">


<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
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
          <td width="11"><img border="0" src="<bean:message key='caminho.imagens'/>parahead_left.gif"/></td>
          <td class="parabg">Informação de Dados para Geração de Relatório ou Consulta</td>
          <td width="11"><img border="0" src="<bean:message key='caminho.imagens'/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      <tr>
      	<td colspan="2">Para gerar o relatório ou a consulta, informe os dados abaixo:</td>
      </tr>
	  <tr>
      	<td HEIGHT="30"><strong>Mês/Ano de Referência:<font color="#FF0000">*</font></strong></td>
        <td>
			<html:text property="mesAnoFaturamento" size="8" maxlength="7" tabindex="1" onkeyup="mascaraAnoMes(this, event);somente_numero(this);"/>&nbsp;MM/AAAA
		</td>
      </tr>
      <logic:notPresent name="cobranca">      
	  <tr>
      	<td HEIGHT="30"><strong>Opção de Totalização:<font color="#FF0000">*</font></strong></td>
        <td>
			<html:select property="opcaoTotalizacao" style="width: 350px;" tabindex="2" onchange="gerenciadorHabilitacaoCampos(this.value);">
				<html:option value="">&nbsp;</html:option>
				<html:option value="<%="" + ConstantesSistema.CODIGO_ESTADO%>"><%=ConstantesSistema.ESTADO%></html:option>
				<html:option value="<%="" + ConstantesSistema.CODIGO_ESTADO_GRUPO_FATURAMENTO%>"><%=ConstantesSistema.ESTADO_GRUPO_FATURAMENTO%></html:option>
				<html:option value="<%="" + ConstantesSistema.CODIGO_ESTADO_GERENCIA_REGIONAL%>"><%=ConstantesSistema.ESTADO_GERENCIA_REGIONAL%></html:option>
				<html:option value="<%="" + ConstantesSistema.CODIGO_ESTADO_UNIDADE_NEGOCIO%>"><%=ConstantesSistema.ESTADO_UNIDADE_NEGOCIO%></html:option>
				<html:option value="<%="" + ConstantesSistema.CODIGO_ESTADO_LOCALIDADE%>"><%=ConstantesSistema.ESTADO_LOCALIDADE%></html:option>
				<html:option value="<%="" + ConstantesSistema.CODIGO_ESTADO_MUNICIPIO%>"><%=ConstantesSistema.ESTADO_MUNICIPIO%></html:option>
				<html:option value="<%="" + ConstantesSistema.CODIGO_GRUPO_FATURAMENTO%>"><%=ConstantesSistema.GRUPO_FATURAMENTO%></html:option>
				<html:option value="<%="" + ConstantesSistema.CODIGO_GERENCIA_REGIONAL%>"><%=ConstantesSistema.GERENCIA_REGIONAL%></html:option>
				<html:option value="<%="" + ConstantesSistema.CODIGO_GERENCIA_REGIONAL_UNIDADE_NEGOCIO%>"><%=ConstantesSistema.GERENCIA_REGIONAL_UNIDADE_NEGOCIO%></html:option>
				<html:option value="<%="" + ConstantesSistema.CODIGO_GERENCIA_REGIONAL_LOCALIDADE%>"><%=ConstantesSistema.GERENCIA_REGIONAL_LOCALIDADE%></html:option>
				<html:option value="<%="" + ConstantesSistema.CODIGO_UNIDADE_NEGOCIO%>"><%=ConstantesSistema.UNIDADE_NEGOCIO%></html:option>
				<html:option value="<%="" + ConstantesSistema.CODIGO_UNIDADE_NEGOCIO_LOCALIDADE%>"><%=ConstantesSistema.UNIDADE_NEGOCIO_LOCALIDADE%></html:option>
				<html:option value="<%="" + ConstantesSistema.CODIGO_UNIDADE_NEGOCIO_SETOR_COMERCIAL%>"><%=ConstantesSistema.UNIDADE_NEGOCIO_SETOR_COMERCIAL%></html:option>
				<html:option value="<%="" + ConstantesSistema.CODIGO_LOCALIDADE%>"><%=ConstantesSistema.LOCALIDADE%></html:option>
				<html:option value="<%="" + ConstantesSistema.CODIGO_MUNICIPIO%>"><%=ConstantesSistema.MUNICIPIO%></html:option>
				<html:option value="<%="" + ConstantesSistema.CODIGO_LOCALIDADE_SETOR_COMERCIAL%>"><%=ConstantesSistema.LOCALIDADE_SETOR_COMERCIAL%></html:option>
				<html:option value="<%="" + ConstantesSistema.CODIGO_LOCALIDADE_QUADRA%>"><%=ConstantesSistema.LOCALIDADE_QUADRA%></html:option>
				<html:option value="<%="" + ConstantesSistema.CODIGO_SETOR_COMERCIAL%>"><%=ConstantesSistema.SETOR_COMERCIAL%></html:option>
				<html:option value="<%="" + ConstantesSistema.CODIGO_SETOR_COMERCIAL_QUADRA%>"><%=ConstantesSistema.SETOR_COMERCIAL_QUADRA%></html:option>
				<html:option value="<%="" + ConstantesSistema.CODIGO_SETOR_COMERCIAL_ROTA%>"><%=ConstantesSistema.SETOR_COMERCIAL_ROTA%></html:option>
				<html:option value="<%="" + ConstantesSistema.CODIGO_QUADRA%>"><%=ConstantesSistema.QUADRA%></html:option>
			</html:select>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Grupo de Faturamento:</strong></td>
        <td>
			<html:select property="grupoFaturamento" style="width: 200px;" tabindex="3">
				<html:option value="">&nbsp;</html:option>
				<html:options collection="colecaoFaturamentoGrupo" labelProperty="descricao" property="id"/>
			</html:select>
		</td>
      </tr>
      </logic:notPresent>  
      <logic:present name="cobranca">
	  <tr>
      	<td HEIGHT="30"><strong>Opção de Totalização:<font color="#FF0000">*</font></strong></td>
        <td>
			<html:select property="opcaoTotalizacao" style="width: 350px;" tabindex="2" onchange="gerenciadorHabilitacaoCampos(this.value);">
				<html:option value="">&nbsp;</html:option>
				<html:option value="<%="" + ConstantesSistema.CODIGO_ESTADO%>"><%=ConstantesSistema.ESTADO%></html:option>
				<logic:notEqual name="tipoResumoSessao" value="ACAOCOBRANCA">
				<html:option value="<%="" + ConstantesSistema.CODIGO_ESTADO_GRUPO_COBRANCA%>"><%=ConstantesSistema.ESTADO_GRUPO_COBRANCA%></html:option>
				 <html:option value="<%="" + ConstantesSistema.CODIGO_ESTADO_GERENCIA_REGIONAL%>"><%=ConstantesSistema.ESTADO_GERENCIA_REGIONAL%></html:option>
				 <html:option value="<%="" + ConstantesSistema.CODIGO_ESTADO_ELO_POLO%>"><%=ConstantesSistema.ESTADO_ELO_POLO%></html:option>
				 <html:option value="<%="" + ConstantesSistema.CODIGO_ESTADO_LOCALIDADE%>"><%=ConstantesSistema.ESTADO_LOCALIDADE%></html:option>
				</logic:notEqual>
				<html:option value="<%="" + ConstantesSistema.CODIGO_GRUPO_COBRANCA%>"><%=ConstantesSistema.GRUPO_COBRANCA%></html:option>
				<html:option value="<%="" + ConstantesSistema.CODIGO_GERENCIA_REGIONAL%>"><%=ConstantesSistema.GERENCIA_REGIONAL%></html:option>
				<logic:notEqual name="tipoResumoSessao" value="ACAOCOBRANCA">
				 <html:option value="<%="" + ConstantesSistema.CODIGO_GERENCIA_REGIONAL_ELO_POLO%>"><%=ConstantesSistema.GERENCIA_REGIONAL_ELO_POLO%></html:option>
				 <html:option value="<%="" + ConstantesSistema.CODIGO_GERENCIA_REGIONAL_LOCALIDADE%>"><%=ConstantesSistema.GERENCIA_REGIONAL_LOCALIDADE%></html:option>
				</logic:notEqual>
				<html:option value="<%="" + ConstantesSistema.CODIGO_ELO_POLO%>"><%=ConstantesSistema.ELO_POLO%></html:option>
				<logic:notEqual name="tipoResumoSessao" value="ACAOCOBRANCA">
				 <html:option value="<%="" + ConstantesSistema.CODIGO_ELO_POLO_LOCALIDADE%>"><%=ConstantesSistema.ELO_POLO_LOCALIDADE%></html:option>
				 <html:option value="<%="" + ConstantesSistema.CODIGO_ELO_POLO_SETOR_COMERCIAL%>"><%=ConstantesSistema.ELO_POLO_SETOR_COMERCIAL%></html:option>
				</logic:notEqual>
				<html:option value="<%="" + ConstantesSistema.CODIGO_LOCALIDADE%>"><%=ConstantesSistema.LOCALIDADE%></html:option>
				<logic:notEqual name="tipoResumoSessao" value="ACAOCOBRANCA">
				 <html:option value="<%="" + ConstantesSistema.CODIGO_LOCALIDADE_SETOR_COMERCIAL%>"><%=ConstantesSistema.LOCALIDADE_SETOR_COMERCIAL%></html:option>
				 <html:option value="<%="" + ConstantesSistema.CODIGO_LOCALIDADE_QUADRA%>"><%=ConstantesSistema.LOCALIDADE_QUADRA%></html:option>
				</logic:notEqual>
				<html:option value="<%="" + ConstantesSistema.CODIGO_SETOR_COMERCIAL%>"><%=ConstantesSistema.SETOR_COMERCIAL%></html:option>
				<logic:notEqual name="tipoResumoSessao" value="ACAOCOBRANCA">
				 <html:option value="<%="" + ConstantesSistema.CODIGO_SETOR_COMERCIAL_QUADRA%>"><%=ConstantesSistema.SETOR_COMERCIAL_QUADRA%></html:option>
				</logic:notEqual>
				<html:option value="<%="" + ConstantesSistema.CODIGO_QUADRA%>"><%=ConstantesSistema.QUADRA%></html:option>
			</html:select>
		</td>
      </tr>      
      <tr>
      	<td HEIGHT="30"><strong>Grupo de Cobraça:</strong></td>
        <td>
			<html:select property="grupoCobranca" style="width: 200px;" tabindex="3">
				<html:option value="">&nbsp;</html:option>
				<html:options collection="colecaoGrupoCobranca" labelProperty="descricao" property="id"/>
			</html:select>
		</td>
      </tr> 
     </logic:present>     
      <tr>
      	<td HEIGHT="30"><strong>Gerência Regional:</strong></td>
        <td>
			<html:select property="gerencialRegional" style="width: 250px;" tabindex="4">
				<html:option value="">&nbsp;</html:option>
				<logic:iterate name="colecaoGerenciaRegional" id="gerenciaRegional" type="GerenciaRegional">
					<html:option value="<%=""+ gerenciaRegional.getId()%>">
					<%= gerenciaRegional.getNomeAbreviado() + " - " + gerenciaRegional.getNome()%></html:option>
				</logic:iterate>
			</html:select>
		</td>
      </tr>
      <tr>
      	<td width="183" HEIGHT="30"><strong>Unidade de Negócio:</strong></td>
        <td>
			<html:select property="unidadeNegocio" style="width: 250px;" tabindex="5">
				<html:option value="">&nbsp;</html:option>
				<logic:iterate name="colecaoUnidadeNegocio" id="unidadeNegocio" type="UnidadeNegocio">
					<html:option value="<%=""+ unidadeNegocio.getId()%>">
					<%= unidadeNegocio.getNome()%></html:option>
				</logic:iterate>
			</html:select>
		</td>
      </tr>
      <tr>
      	<td width="183" HEIGHT="30"><strong>Localidade:</strong></td>
        <td width="432">
        	
        	<html:text property="localidade" size="4" maxlength="3" tabindex="6" onkeypress="validaEnterComMensagem(event, 'exibirInformarDadosGeracaoRelatorioConsultaAction.do?pesquisarLocalidade=OK', 'localidade', 'Localidade');"/>
			<a href="javascript:gerenciadorHabilitacaoImagemPesquisa(document.forms[0].opcaoTotalizacao.value, 'Localidade')" title="Pesquisar"><img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" border="0"></a>

			<logic:present name="corLocalidade">

				<logic:equal name="corLocalidade" value="exception">
					<html:text property="descricaoLocalidade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:equal>

				<logic:notEqual name="corLocalidade" value="exception">
					<html:text property="descricaoLocalidade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEqual>

			</logic:present>

			<logic:notPresent name="corLocalidade">

				<logic:empty name="InformarDadosGeracaoRelatorioConsultaActionForm" property="localidade">
					<html:text property="descricaoLocalidade" value="" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:empty>
				<logic:notEmpty name="InformarDadosGeracaoRelatorioConsultaActionForm" property="localidade">
					<html:text property="descricaoLocalidade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEmpty>
				

			</logic:notPresent>
        	
        	<a href="javascript:gerenciadorHabilitacaoImagemLimpar(document.forms[0].opcaoTotalizacao.value, 'Localidade')" title="Apagar"><img src="<bean:message key='caminho.imagens'/>limparcampo.gif" border="0"></a>
		</td>
      </tr>
      
      <tr>
      	<td width="183" HEIGHT="30"><strong>Município:</strong></td>
        <td width="432">
        	
        	<html:text property="municipio" size="4" maxlength="4" tabindex="6" onkeypress="validaEnterComMensagem(event, 'exibirInformarDadosGeracaoRelatorioConsultaAction.do?pesquisarMunicipio=OK', 'municipio', 'Município');"/>
			<a href="javascript:gerenciadorHabilitacaoImagemPesquisa(document.forms[0].opcaoTotalizacao.value, 'Município')" title="Pesquisar"><img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" border="0"></a>

			<logic:present name="corMunicipio">

				<logic:equal name="corMunicipio" value="exception">
					<html:text property="descricaoMunicipio" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:equal>

				<logic:notEqual name="corMunicipio" value="exception">
					<html:text property="descricaoMunicipio" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEqual>

			</logic:present>

			<logic:notPresent name="corMunicipio">

				<logic:empty name="InformarDadosGeracaoRelatorioConsultaActionForm" property="municipio">
					<html:text property="descricaoMunicipio" value="" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:empty>
				<logic:notEmpty name="InformarDadosGeracaoRelatorioConsultaActionForm" property="municipio">
					<html:text property="descricaoMunicipio" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEmpty>
				

			</logic:notPresent>
        	
        	<a href="javascript:gerenciadorHabilitacaoImagemLimpar(document.forms[0].opcaoTotalizacao.value, 'Município')" title="Apagar"><img src="<bean:message key='caminho.imagens'/>limparcampo.gif" border="0"></a>
		</td>
      </tr>
      
      <tr>
      	<td width="183" HEIGHT="30"><strong>Setor Comercial:</strong></td>
        <td width="432">
        	
        	<html:hidden property="idSetorComercial"/>
        	<html:text property="setorComercial" size="4" maxlength="3" tabindex="7" onkeypress="validaEnterDependenciaComMensagem(event, 'exibirInformarDadosGeracaoRelatorioConsultaAction.do?pesquisarSetorComercial=OK', this, document.forms[0].localidade.value, 'Localidade', 'Setor Comercial');"/>
			<a href="javascript:gerenciadorHabilitacaoImagemPesquisa(document.forms[0].opcaoTotalizacao.value, 'SetorComercial')" title="Pesquisar"><img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" border="0"></a>

			<logic:present name="corSetorComercial">

				<logic:equal name="corSetorComercial" value="exception">
					<html:text property="descricaoSetorComercial" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:equal>

				<logic:notEqual name="corSetorComercial" value="exception">
					<html:text property="descricaoSetorComercial" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEqual>

			</logic:present>

			<logic:notPresent name="corSetorComercial">

				<logic:empty name="InformarDadosGeracaoRelatorioConsultaActionForm" property="setorComercial">
					<html:text property="descricaoSetorComercial" value="" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:empty>
				<logic:notEmpty name="InformarDadosGeracaoRelatorioConsultaActionForm" property="setorComercial">
					<html:text property="descricaoSetorComercial" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEmpty>
				

			</logic:notPresent>
        	
        	<a href="javascript:gerenciadorHabilitacaoImagemLimpar(document.forms[0].opcaoTotalizacao.value, 'SetorComercial')" title="Apagar"><img src="<bean:message key='caminho.imagens'/>limparcampo.gif" border="0"></a>
		</td>
      </tr>
      <tr>
      	<td width="183" HEIGHT="30"><strong>Quadra:</strong></td>
        <td width="432">
        	
        	<html:text property="quadra" size="4" maxlength="4" tabindex="8" onkeypress="validaEnterDependenciaComMensagem(event, 'exibirInformarDadosGeracaoRelatorioConsultaAction.do?pesquisarQuadra=OK', this, document.forms[0].setorComercial.value, 'Setor Comercial', 'Quadra');"/>

			<logic:present name="msgQuadra">
				<span style="color:#ff0000" id="msgQuadra"><bean:write name="msgQuadra"/></span>
			</logic:present>        	
        	
		</td>
      </tr>
      <tr>
		<td height="24"><strong>Rota :<font color="#FF0000"></font></strong></td>
		<td><html:hidden property="idRota" /> <html:text maxlength="4"
			size="4" property="codigoRota" readonly="true"
			style="background-color:#EFEFEF; border:0; color: #000000" /> <a
			href="javascript:pesquisarRota();">

		<img border="0"
			src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" /></a>
		<a href="javascript:limparRota()"> <img
			src="<bean:message key="caminho.imagens"/>limparcampo.gif"
			border="0" title="Apagar" /></a></td>
	</tr>
      <tr>
      	<td HEIGHT="30"><strong>Perfil do Imóvel:</strong></td>
        <td>
			<html:select property="perfilImovel" style="width: 200px;" tabindex="9" multiple="true">
				<html:option value="">&nbsp;</html:option>
				<html:options collection="colecaoImovelPerfil" labelProperty="descricao" property="id"/>
			</html:select>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Ligação de Água:</strong></td>
        <td>
			<html:select property="situacaoLigacaoAgua" style="width: 200px;" tabindex="11" multiple="true">
				<html:option value="">&nbsp;</html:option>
				<html:options collection="colecaoLigacaoAguaSituacao" labelProperty="descricao" property="id"/>
			</html:select>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Ligação de Esgoto:</strong></td>
        <td>
			<html:select property="situacaoLigacaoEsgoto" style="width: 200px;" tabindex="12" multiple="true">
				<html:option value="">&nbsp;</html:option>
				<html:options collection="colecaoLigacaoEsgotoSituacao" labelProperty="descricao" property="id"/>
			</html:select>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Categoria:</strong></td>
        <td>
			<html:select property="categoria" style="width: 200px;" tabindex="13" multiple="true">
				<html:option value="">&nbsp;</html:option>
				<html:options collection="colecaoCategoria" labelProperty="descricao" property="id"/>
			</html:select>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Esfera de Poder:</strong></td>
        <td>
			<html:select property="esferaPoder" style="width: 200px;" tabindex="14" multiple="true">
				<html:option value="">&nbsp;</html:option>
				<html:options collection="colecaoEsferaPoder" labelProperty="descricao" property="id"/>
			</html:select>
		</td>
      </tr>
     
      <logic:present name="analiseFaturamento" scope="session">
      <tr>
      	<td HEIGHT="30"><strong>Tipo de Análise:</strong></td>
        <td>
			
			<html:radio property="tipoAnaliseFaturamento" value="1"/><strong>Real</strong>
			&nbsp;<html:radio property="tipoAnaliseFaturamento" value="2"/><strong>Simulado</strong>
				
		</td>
      </tr>
      </logic:present>
      
      <tr>
      	<td></td>
      	<td><font color="#FF0000">*</font> Campo Obrigat&oacute;rio</td>
      </tr>
      <tr>
      	<td colspan="2">&nbsp;</td>
      </tr>
      <tr>
      	<td align="left"><INPUT type="button" onclick="window.location.href='exibirInformarDadosGeracaoRelatorioConsultaAction.do?limparForm=OK'" style="width: 70px;" name="botaoLimpar" class="bottonRightCol" value="Limpar" tabindex="15"></td>
      	<td align="right"><INPUT type="button" onclick="validarForm(document.forms[0]);" name="botaoGerar" class="bottonRightCol" value="Gerar Relatório/Consulta" tabindex="16"></td>
      </tr>
      </table>
      <p>&nbsp;</p>
	</td>
  </tr>
</table>

<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=informarDadosGeracaoRelatorioConsultaAction.do&left=555&top=570"/> 
<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>


<SCRIPT LANGUAGE="JavaScript">
<!--
	
	gerenciadorHabilitacaoCampos(document.forms[0].opcaoTotalizacao.value);

//-->
</SCRIPT>

</body>
</html:html>
