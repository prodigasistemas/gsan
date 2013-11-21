<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="RegistrarLeiturasAnormalidadesRelatorioActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">


	function validarForm(form){
	
		if(validateRegistrarLeiturasAnormalidadesRelatorioActionForm(form) 
			&& verificarValorFinal() ){
			
			if (validarCampos()){
				toggleBox('demodiv',1);	
			}
		}
	}
	
	function listarLeiturista(){
		 var form = document.forms[0];
		 form.action = 'exibirRegistrarLeiturasAnormalidadesRelatorioAction.do?objetoConsulta=3';
	  	 form.submit();	
	}
	
	
	function validarCampos(){
		
		var form = document.forms[0];
		
		msg = verificarAtributosInicialFinal(form.localidadeOrigemID,form.localidadeDestinoID,"Localidade Final");
		if( msg != ""){
			alert(msg);
			return false;
		}else{
			msg = verificarAtributosInicialFinal(form.setorComercialOrigemCD,form.setorComercialDestinoCD,"Setor Comercial Final");
			if( msg != ""){
				alert(msg);
				return false;
			}else{
				msg = verificarAtributosInicialFinal(form.rotaInicial,form.rotaFinal,"Rota Final");
				if( msg != ""){
					alert(msg);
					return false;
			}
		}
		
		return true;
		}
	}
	
	function duplicarLocalidade(){
	  var formulario = document.forms[0]; 
	  formulario.localidadeDestinoID.value = formulario.localidadeOrigemID.value;
    }

  function validarLocalidade(){
  		var form = document.forms[0];
	
		if( form.localidadeOrigemID.value == form.localidadeDestinoID.value ){
			form.setorComercialOrigemID.disabled = false;
			form.setorComercialDestinoID.disabled = false;
		}
		else if( form.localidadeOrigemID.value != form.localidadeDestinoID.value ){
			form.setorComercialOrigemID.disabled = true;
			form.setorComercialDestinoID.disabled = true;
			form.setorComercialOrigemID.value = '';
			form.setorComercialDestinoID.value = '';
		}
  }
  
	function limparOrigem(tipo){
      var form =  document.forms[0];
	
			
		switch(tipo){
			case 1: //De localidade pra baixo
	
				form.nomeLocalidadeOrigem.value = "";
				form.localidadeDestinoID.value = "";
				form.nomeLocalidadeDestino.value = "";
				form.setorComercialOrigemCD.value = "";
			    form.setorComercialOrigemID.value = "";
				habilitaSQlS();
				
			case 2: //De setor para baixo
	
			   form.nomeSetorComercialOrigem.value = "";
			   form.setorComercialDestinoCD.value = "";
			   form.setorComercialDestinoID.value = "";		   
			   form.nomeSetorComercialDestino.value = "";
				   
		}
	}
	
	function habilitaSQlS(){
		var form = document.forms[0];
		if (form.localidadeOrigemID.value.length < 1){
		 	form.setorComercialOrigemCD.disabled = false;
		 	form.setorComercialDestinoCD.disabled = false;
	   }    	
	}
	  
   function limparDestino(tipo){
		var form = document.forms[0];
		
		switch(tipo){
			case 1: //De localidade pra baixo
				if(form.localidadeOrigemID.value != form.localidadeDestinoID.value){
					form.nomeLocalidadeDestino.value = "";					
					form.setorComercialDestinoCD.value = "";
					 form.setorComercialDestinoID.value = ""; 
			    }
			case 2: //De setor para baixo		 
				if(form.setorComercialOrigemCD.value != form.setorComercialDestinoCD.value){  
				   form.nomeSetorComercialDestino.value = "";   
				}   		   		    
		}
	
	}
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,campo){
		if(!campo.disabled){
	  		if (objeto == null || codigoObjeto == null){
	     		if(tipo == "" ){
	      			abrirPopup(url,altura, largura);
	     		}else{
		  			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		 		}
	 		}else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
  		}
	}
	
	function limparBorrachaOrigem(tipo){
		var form = document.forms[0];
	
			
		switch(tipo){
			case 1: //De localidara pra baixo
			    if(!form.localidadeOrigemID.disabled){
				form.localidadeOrigemID.value = "";
				form.nomeLocalidadeOrigem.value = "";
				form.localidadeDestinoID.value = "";
				form.nomeLocalidadeDestino.value = "";
	
				desabilitaIntervaloDiferente(1);
				}else{
				break;
				}
				
			case 2: //De setor para baixo
			    if(!form.setorComercialOrigemCD.disabled){
			     form.setorComercialOrigemCD.value = "";
			     form.setorComercialOrigemID.value = "";
			     form.nomeSetorComercialOrigem.value = "";
		   
			     form.setorComercialDestinoCD.value = "";
			     form.setorComercialDestinoID.value = "";		   
			     form.nomeSetorComercialDestino.value = "";
			     
			     form.rotaInicial.value = "";
			     form.rotaFinal.value = "";
			     
			    }else{
			     break;
			    }
		}
	
	}

	function limparBorrachaDestino(tipo){
		var form = document.forms[0];
	
		switch(tipo){
			case 1: //De localidade pra baixo
			     form.localidadeDestinoID.value = "";
				 form.nomeLocalidadeDestino.value = "";					
				 form.setorComercialDestinoCD.value = "";
				
			case 2: //De setor para baixo		   
			   form.setorComercialDestinoID.value = ""; 
			   form.nomeSetorComercialDestino.value = "";		   
	   		   form.setorComercialDestinoCD.value = "";
			   
	     }
	}
	
	function desabilitaIntervaloDiferente(tipo){
		var form = document.forms[0];
		
		switch(tipo){
			case 1: //De localidade 
			    if(form.localidadeOrigemID.value != form.localidadeDestinoID.value){
			        limparBorrachaOrigem(2);
			        form.setorComercialOrigemCD.disabled = true;
				 	form.setorComercialDestinoCD.disabled = true;
				 	form.rotaInicial.disabled = true;
				 	form.rotaFinal.disabled = true;
	
				  }else{
				 	form.setorComercialOrigemCD.disabled = false;
				 	form.setorComercialDestinoCD.disabled = false;
				 	form.rotaInicial.disabled = false;
				 	form.rotaFinal.disabled = false;
				
				  }
				
				break;
				
			case 2: //De Setor Comercial
				if(form.setorComercialOrigemCD.value != form.setorComercialDestinoCD.value){
					form.rotaInicial.disabled = true;
				 	form.rotaFinal.disabled = true;
				 	
				}else{
					form.rotaInicial.disabled = false;
				 	form.rotaFinal.disabled = false;
				}
				
				break;					   	         
			}
	}	
	
	function duplicarSetorComercial(){
		var formulario = document.forms[0]; 
		formulario.setorComercialDestinoCD.value = formulario.setorComercialOrigemCD.value;
	}  

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0]; 
		
		if (tipoConsulta == 'localidadeOrigem') {
	      form.localidadeOrigemID.value = codigoRegistro;
		  form.nomeLocalidadeOrigem.value = descricaoRegistro;
		  form.nomeLocalidadeOrigem.style.color = "#000000";
		  
		  form.localidadeDestinoID.value = codigoRegistro;
	      form.nomeLocalidadeDestino.value = descricaoRegistro;
	      form.nomeLocalidadeDestino.style.color = "#000000";
	      form.setorComercialOrigemCD.focus();
		}
	
		if (tipoConsulta == 'localidadeDestino') {
	      form.localidadeDestinoID.value = codigoRegistro;
	      form.nomeLocalidadeDestino.value = descricaoRegistro;
	   	  form.setorComercialDestinoCD.focus();
		 		  
		}	
		if (tipoConsulta == 'leiturista') {
        	limparLeiturista(form);
      		form.idLeiturista.value = codigoRegistro;
      		form.nomeLeiturista.value = descricaoRegistro;
      		form.nomeLeiturista.style.color = "#000000";
      		form.idLeiturista.focus();
    	}

	}
	
	function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'setorComercialOrigem') {
		  	form.setorComercialOrigemCD.value = codigoRegistro;
		  	form.nomeSetorComercialOrigem.value = descricaoRegistro;
		  	form.nomeSetorComercialOrigem.style.color = "#000000"; 
		  	
		  	form.setorComercialDestinoCD.value = codigoRegistro;
		  	form.nomeSetorComercialDestino.value = descricaoRegistro;
		  	form.nomeSetorComercialDestino.style.color = "#000000";
		  	
		}

		if (tipoConsulta == 'setorComercialDestino') {
		  	form.setorComercialDestinoCD.value = codigoRegistro;
		  	form.nomeSetorComercialDestino.value = descricaoRegistro;
		  	form.nomeSetorComercialDestino.style.color = "#000000";
		}
	}
	
	function reloadForm(){
  		var form = document.forms[0];
  	
  		form.action='exibirRegistrarLeiturasAnormalidadesRelatorioAction.do';
	    form.submit();
  	}
  	
  	function limparForm(){
  		var form = document.forms[0];
  		
  		form.idFaturamentoGrupo.value = "-1";
  		form.mesAno.value = "";
  		form.idFirma.value = "-1";
  		form.gerenciaRegional.value = "-1";
  		form.unidadeNegocio.value = "-1";
  		
  		form.localidadeOrigemID.value = "";
  		form.nomeLocalidadeOrigem.value = "";
  		form.localidadeDestinoID.value = "";  		
  		form.nomeLocalidadeDestino.value = "";  	
  		
  		form.setorComercialOrigemCD.value = "";	
  		form.nomeSetorComercialOrigem.value = "";
  		form.setorComercialDestinoCD.value = "";
  		form.nomeSetorComercialDestino.value = "";
  		form.setorComercialOrigemCD.disabled = false;
	 	form.setorComercialDestinoCD.disabled = false;

  		form.idLeiturista.value = "";
    	form.nomeLeiturista.value = "";
    	form.idLeiturista.disabled = "true";
    	
    	form.rotaInicial.value = "";
    	form.rotaFinal.value = "";
  		
  	}
  	
  	function pesquisarLeiturista(){
		var form = document.forms[0];
	  
		if ( form.idFirma.value == '-1' ){
			alert("Para consultar leituristas, informe a empresa de leitura.");	    
		} else  {
			abrirPopup('pesquisarLeituristaAction.do?empresa=' + form.idFirma.value, 200, 400 );
		}
	}
	
	function limparLeiturista() {
    	var form = document.forms[0];
    	
    	form.idLeiturista.value = "";
    	form.nomeLeiturista.value = "";
    	form.idLeiturista.focus();
	}
	
	function limparRota(){
		var form = document.forms[0];
	
		form.rotaInicial.value = "";
    	form.rotaFinal.value = "";
	}
	
	function validarEmpresa(){
		var form = document.forms[0];
		
		if(form.idFirma.value == '-1'){
			alert("Informe a Firma.");
			form.idFirma.focus();
			return false;
		}
		
		return true;
	}
	
	function validarRota(){
		var form = document.forms[0];
		
		if(form.localidadeOrigemID.value == ""){
			alert("Informe a Localidade.");
			limparRota();
			form.localidadeOrigemID.focus();
			return false;
		}
		
		if(form.setorComercialOrigemCD.value == ""){
			alert("Informe o Setor Comercial.");
			limparRota();
			form.setorComercialOrigemCD.focus();
			return false;
		}
		
		return true;
	}
	
	function replicarRota(){
		var formulario = document.forms[0]; 
		formulario.rotaFinal.value = formulario.rotaInicial.value;
	}
	
	function verificarValorFinal(){
		var form = document.forms[0];
		
		if(form.rotaFinal.value < form.rotaInicial.value){
			alert("Rota Final menor que Rota Inicial.");
			return false;
		}

		return true;
	}
	
	function habilitarLeiturista(){
		var form = document.forms[0];
		
		if (form.idFirma.value != null && parseInt(form.idFirma.value) != -1 ) {

			form.idLeiturista.disabled = false;		
		} else {
			
			form.idLeiturista.value = "";
			form.nomeLeiturista.value = "";
			form.idLeiturista.disabled = true;		
		}
	}
	
	function habilitaCampoNumOcorrencias(object){
		var form = document.forms[0];
		
		var count = 0;
		for (var i = 0; i < object.options.length; i++) {
		    if (object.options[i].selected) {
		    	count++;
	    	}
    	}
		    	
		if(count === 1 &&  object.options[object.selectedIndex].value == '-1') {
			form.numOcorrenciasConsecutivas.value = '';
			form.numOcorrenciasConsecutivas.disabled = true;	
		} else {
			form.numOcorrenciasConsecutivas.disabled = false;
		}
	}
	
	function populaCampoNumOcorrencias(object){
		var form = document.forms[0];
		
		for (var i = 0; i < object.options.length; i++) {
		    if (object.options[i].selected) {
				if(form.numOcorrenciasConsecutivas.value == '' 
					|| parseInt(form.numOcorrenciasConsecutivas.value) <= 0){
					form.numOcorrenciasConsecutivas.value = 1;
					break;
				}
		    }
		}
	}
	
	function menorIgual10(){
		var form = document.forms[0];
		
		if(form.numOcorrenciasConsecutivas.value <= 0 || form.numOcorrenciasConsecutivas.value > 10) {
			alert('O campo "Núm. Ocorrências Consecutivas" deve ser maior que 0 e menor ou igual a 10');
			form.numOcorrenciasConsecutivas.value = (form.numOcorrenciasConsecutivas.value <= 0)? 1 : 10;
			form.numOcorrenciasConsecutivas.focus();
		} 
	}
  	
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/gerarRelatorioRegistrarLeiturasAnormalidadesAction"
	name="RegistrarLeiturasAnormalidadesRelatorioActionForm"
	type="gcom.gui.micromedicao.RegistrarLeiturasAnormalidadesRelatorioActionForm"
	method="post">


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
					<td class="parabg">Gerar Relatório Leituras e Anormalidades
					Informadas</td>
					<td width="11" valign="top"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para gerar o relatório das leituras e
					anormalidades, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="150"><strong>Grupo de Faturamento:</strong></td>
					<td align="left"><html:select property="idFaturamentoGrupo"
						tabindex="1">
						<html:option value="-1"> &nbsp; </html:option>
						<html:options collection="faturamentosGrupos" property="id"
							labelProperty="descricao" />
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Informadas em:<font color="#FF0000">*</font></strong></td>
					<td align="left"><html:text property="mesAno" size="10"
						tabindex="2" maxlength="7"
						onkeyup="mascaraAnoMes(this, event);somente_numero(this);"
						onkeypress="return isCampoNumerico(event);" />&nbsp;MM/AAAA</td>
				</tr>
				<tr>
					<td><strong>Empresa:<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><html:select property="idFirma"
						tabindex="3" onchange="javascript:listarLeiturista()">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoEmpresas"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>

				<tr>
					<td><strong>Leiturista:</strong></td>
					<td colspan="2" align="left"><html:select property="idLeiturista"
						tabindex="4">
						<html:option value="-1">&nbsp;</html:option>
						<logic:present name="colecaoLeiturista">
							<html:options collection="colecaoLeiturista"
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select></td>

				</tr>

				<tr>
					<td><strong>Gerência Regional:</strong></td>
					<td><html:select property="gerenciaRegional"
						name="RegistrarLeiturasAnormalidadesRelatorioActionForm"
						tabindex="4" onchange="javascript:reloadForm();">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
			            </html:option>
						<logic:iterate name="colecaoGerenciaRegional"
							id="colecaoGerenciaRegional">
							<html:option value="${colecaoGerenciaRegional.id}">
								<bean:write name="colecaoGerenciaRegional"
									property="nomeAbreviado" /> 
					           - <bean:write name="colecaoGerenciaRegional"
									property="nome" />
							</html:option>
						</logic:iterate>

					</html:select></td>
				</tr>

				<tr>
					<td><strong>Unidade Negócio:</strong></td>
					<td><html:select property="unidadeNegocio"
						name="RegistrarLeiturasAnormalidadesRelatorioActionForm"
						tabindex="5">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
			            </html:option>
						<logic:present name="colecaoUnidadeNegocio">
							<logic:iterate name="colecaoUnidadeNegocio"
								id="colecaoUnidadeNegocios">
								<html:option value="${colecaoUnidadeNegocios.id}">
									<bean:write name="colecaoUnidadeNegocios"
										property="nomeAbreviado" />
									<bean:write name="colecaoUnidadeNegocios" property="nome" />
								</html:option>
							</logic:iterate>
						</logic:present>
					</html:select></td>
				</tr>

				<tr>
					<td><strong>Perfil do Imóvel:</strong></td>
					<td><html:select property="perfilImovel" style="width: 350px;">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoPerfisImovel"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>

				<tr>
					<td><strong>Anormalidade de Leitura:</strong></td>
					<td><html:select property="anormalidadesLeituras"
						style="width: 250px;" multiple="mutiple" size="4"
						onchange="javascript:habilitaCampoNumOcorrencias(this);"
						onblur="javascript:populaCampoNumOcorrencias(this);">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoAnormalidadesLeituras"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>

				<tr>
					<td><strong>Núm. Ocorrências Consecutivas:</strong></td>
					<td><html:text property="numOcorrenciasConsecutivas" size="10"
						disabled="true" maxlength="2" onkeyup="somente_numero(this);"
						onkeypress="return isCampoNumerico(event);" onblur="menorIgual10();" /></td>
				</tr>

				<tr bgcolor="#cbe5fe">
					<html:hidden property="inscricaoTipo" />
					<td width="20%"><strong>Localidade inicial:</strong></td>
					<td><html:text tabindex="7" maxlength="3"
						property="localidadeOrigemID" size="5"
						onkeypress="javascript:limparDestino(1);validaEnter(event, 'exibirRegistrarLeiturasAnormalidadesRelatorioAction.do?objetoConsulta=1&inscricaoTipo=origem', 'localidadeOrigemID');return isCampoNumerico(event);"
						onclick="javascript:validarLocalidade();"
						onblur="javascript:duplicarLocalidade();"
						onkeyup="javascript:somente_numero(this);" /> <a
						href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '',document.forms[0].localidadeOrigemID);limparOrigem(1);"><img
						width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Localidade" /></a> <logic:present
						name="corLocalidadeOrigem">

						<logic:equal name="corLocalidadeOrigem" value="exception">
							<html:text property="nomeLocalidadeOrigem" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corLocalidadeOrigem" value="exception">
							<html:text property="nomeLocalidadeOrigem" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> <logic:notPresent name="corLocalidadeOrigem">

						<logic:empty
							name="RegistrarLeiturasAnormalidadesRelatorioActionForm"
							property="localidadeOrigemID">
							<html:text property="nomeLocalidadeOrigem" value="" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty
							name="RegistrarLeiturasAnormalidadesRelatorioActionForm"
							property="localidadeOrigemID">
							<html:text property="nomeLocalidadeOrigem" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: 	#000000" />
						</logic:notEmpty>
					</logic:notPresent> <a href="javascript:limparBorrachaOrigem(1);">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Setor Comercial inicial:</strong></td>
					<td><html:text tabindex="8" maxlength="3"
						property="setorComercialOrigemCD" size="5"
						onkeyup="limparOrigem(2);somente_numero(this);"
						onkeypress="form.target='';limparDestino(2);validaEnterDependencia(event, 'exibirRegistrarLeiturasAnormalidadesRelatorioAction.do?objetoConsulta=2&inscricaoTipo=origem', document.forms[0].setorComercialOrigemCD, document.forms[0].localidadeOrigemID.value, 'Localidade Inicial.');return isCampoNumerico(event);"
						onblur="javascript:duplicarSetorComercial();" /> <a
						href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem', 'idLocalidade', document.forms[0].localidadeOrigemID.value , 275, 480, 'Informe Localidade Inicial',document.forms[0].setorComercialOrigemCD);
						         "><img
						width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Setor Comercial" /></a> <logic:present
						name="corSetorComercialOrigem">

						<logic:equal name="corSetorComercialOrigem" value="exception">
							<html:text property="nomeSetorComercialOrigem" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corSetorComercialOrigem" value="exception">
							<html:text property="nomeSetorComercialOrigem" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> <logic:notPresent name="corSetorComercialOrigem">

						<logic:empty
							name="RegistrarLeiturasAnormalidadesRelatorioActionForm"
							property="setorComercialOrigemCD">
							<html:text property="nomeSetorComercialOrigem" value="" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty
							name="RegistrarLeiturasAnormalidadesRelatorioActionForm"
							property="setorComercialOrigemCD">
							<html:text property="nomeSetorComercialOrigem" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent> <a href="javascript:limparBorrachaOrigem(2);">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a> <html:hidden
						property="setorComercialOrigemID" /></td>
				</tr>

				<tr>
					<td><strong>Rota Inicial:</strong></td>

					<td><html:text maxlength="4" tabindex="9" property="rotaInicial"
						onkeypress="return isCampoNumerico(event);"
						onkeyup="javascript:validarRota();replicarRota();" size="4" /></td>
				</tr>

				<tr bgcolor="#cbe5fe">
					<td width="20%"><strong>Localidade final:</strong></td>
					<td><html:text maxlength="3" property="localidadeDestinoID"
						size="5"
						onkeyup="desabilitaIntervaloDiferente(1);somente_numero(this);"
						onkeypress="limparDestino(1);
							validaEnter(event,'exibirRegistrarLeiturasAnormalidadesRelatorioAction.do?&objetoConsulta=1&inscricaoTipo=destino', 'localidadeDestinoID');return isCampoNumerico(event);"
						tabindex="10" /> <a
						href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', null, null, 275, 480, '',document.forms[0].localidadeDestinoID);limparDestino(1);"><img
						width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Localidade" /></a> <logic:present
						name="corLocalidadeDestino">

						<logic:equal name="corLocalidadeDestino" value="exception">
							<html:text property="nomeLocalidadeDestino" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corLocalidadeDestino" value="exception">
							<html:text property="nomeLocalidadeDestino" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> <logic:notPresent name="corLocalidadeDestino">

						<logic:empty
							name="RegistrarLeiturasAnormalidadesRelatorioActionForm"
							property="localidadeDestinoID">
							<html:text property="nomeLocalidadeDestino" value="" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty
							name="RegistrarLeiturasAnormalidadesRelatorioActionForm"
							property="localidadeDestinoID">
							<html:text property="nomeLocalidadeDestino" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: 	#000000" />
						</logic:notEmpty>
					</logic:notPresent> <a href="javascript:limparBorrachaDestino(1);">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Setor Comercial final:</strong></td>
					<td><html:text maxlength="3" property="setorComercialDestinoCD"
						size="5"
						onkeyup="limparDestino(2);desabilitaIntervaloDiferente(2);somente_numero(this);"
						onkeypress="form.target='';validaEnterDependencia(event, 'exibirRegistrarLeiturasAnormalidadesRelatorioAction.do?objetoConsulta=2&inscricaoTipo=destino', document.forms[0].setorComercialDestinoCD, document.forms[0].localidadeDestinoID.value, 'Localidade Final.');return isCampoNumerico(event);"
						tabindex="11" /> <a
						href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialDestino', 'idLocalidade', document.forms[0].localidadeDestinoID.value, 275, 480, 'Informe Localidade Final',document.forms[0].setorComercialDestinoCD);
							        limparDestino(2);"><img
						width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Setor Comercial" /></a> <logic:present
						name="corSetorComercialDestino">

						<logic:equal name="corSetorComercialDestino" value="exception">
							<html:text property="nomeSetorComercialDestino" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corSetorComercialDestino" value="exception">
							<html:text property="nomeSetorComercialDestino" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> <logic:notPresent name="corSetorComercialDestino">

						<logic:empty
							name="RegistrarLeiturasAnormalidadesRelatorioActionForm"
							property="setorComercialDestinoCD">
							<html:text property="nomeSetorComercialDestino" value=""
								size="45" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty
							name="RegistrarLeiturasAnormalidadesRelatorioActionForm"
							property="setorComercialDestinoCD">
							<html:text property="nomeSetorComercialDestino" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent> <a href="javascript:limparBorrachaDestino(2);">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a> <html:hidden
						property="setorComercialDestinoID" /></td>
				</tr>

				<tr>
					<td><strong>Rota Final:</strong></td>

					<td><html:text maxlength="4" tabindex="12" property="rotaFinal"
						onkeypress="return isCampoNumerico(event);"
						onkeyup="javascript:desabilitaIntervaloDiferente(2);verificarValorFinal();"
						size="4" /></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campos
					Obrigat&oacute;rios</td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>

					<td align="right"><input type="button" name="Button"
						class="bottonRightCol" value="Cancelar"
						onclick="javascript:window.location.href='/gsan/telaPrincipal.do'" />
					</td>

					<td align="right"><input type="button" name="Button"
						class="bottonRightCol" value="Limpar" onclick="limparForm();" />
					</td>

					<td width="500" align="right">&nbsp;</td>

					<td align="right"><input type="button" name="Button"
						class="bottonRightCol" value="Gerar"
						onclick="validarForm(document.forms[0]);" /></td>

				</tr>
			</table>
			</td>
		</tr>
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioRegistrarLeiturasAnormalidadesAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
