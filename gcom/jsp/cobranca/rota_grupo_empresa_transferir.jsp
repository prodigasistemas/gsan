<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@page import="gcom.util.ConstantesSistema"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="TransferirRotasGruposEmpresasActionForm" />
	<%@ include file="/jsp/util/titulo.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
	<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<SCRIPT LANGUAGE="JavaScript">
	
		function selecionarRotas(){
			var form = document.TransferirRotasGruposEmpresasActionForm;
    		form.action = 'exibirTransferirRotaGrupoEmpresaAction.do?selecionar=1';
    		form.submit();
		}
	
		function validarFormSubmit(){
    		var form = document.TransferirRotasGruposEmpresasActionForm;
    		form.action = 'transferirRotaGrupoEmpresaConfirmarAction.do';
    		form.submit();
		}
		
		function limpar(campo){
			 var form = document.forms[0];
			if (campo == 1){
				form.idLocalidadeInicial.value = '';
				form.descricaoLocalidadeInicial.value = '';
				form.idSetorComercialInicial.value = '';
				form.descricaoSetorComercialInicial.value = '';
				form.descricaoLocalidadeInicial.style.color = "#000000";
				form.descricaoSetorComercialInicial.style.color = "#000000";
				form.idRotaInicial.value = '';
				form.idLocalidadeFinal.value = '';
				form.descricaoLocalidadeFinal.value = '';
				form.idSetorComercialFinal.value = '';
				form.descricaoSetorComercialFinal.value = '';
				form.descricaoLocalidadeFinal.style.color = "#000000";
				form.descricaoSetorComercialFinal.style.color = "#000000";
				form.idRotaFinal.value = '';
				
			}else if (campo == 2){
				
				form.idSetorComercialInicial.value = '';
				form.descricaoSetorComercialInicial.value = '';
				form.descricaoSetorComercialInicial.style.color = "#000000";
				form.idRotaInicial.value = '';
				form.idSetorComercialFinal.value = '';
				form.descricaoSetorComercialFinal.value = '';
				form.descricaoSetorComercialFinal.style.color = "#000000";
				form.idRotaFinal.value = '';
			}else if (campo == 3){
				form.idSetorComercialFinal.value = '';
				form.descricaoSetorComercialInicial.value = '';
				form.descricaoSetorComercialInicial.style.color = "#000000";
				form.descricaoSetorComercialFinal.value = '';
				form.descricaoSetorComercialFinal.style.color = "#000000";
				form.idRotaInicial.value = '';
				form.idRotaFinal.value = '';
			}else if (campo == 4){
				form.idLocalidadeFinal.value = '';
				form.descricaoLocalidadeFinal.value = '';
				form.idSetorComercialFinal.value = '';
				form.descricaoSetorComercialFinal.value = '';
				form.descricaoLocalidadeFinal.style.color = "#000000";
				form.descricaoSetorComercialFinal.style.color = "#000000";
				form.idRotaFinal.value = '';
			}else if (campo == 5){
				form.idSetorComercialFinal.value = '';
				form.descricaoSetorComercialFinal.value = '';
				form.descricaoSetorComercialFinal.style.color = "#000000";
				form.idRotaFinal.value = '';
			}else if (campo == 6){
				form.idRotaFinal.value = '';
			}else if (campo == 7){
				form.idLocalidadeFinal.value = '';
				form.descricaoLocalidadeFinal.value = '';
				form.descricaoLocalidadeFinal.style.color = "#000000";
				form.descricaoLocalidadeInicial.value = '';
				form.descricaoLocalidadeInicial.style.color = "#000000";
				form.idSetorComercialInicial.value = '';
				form.descricaoSetorComercialInicial.value = '';
				form.descricaoSetorComercialInicial.style.color = "#000000";
				form.idRotaInicial.value = '';
				form.idSetorComercialFinal.value = '';
				form.descricaoSetorComercialFinal.value = '';
				form.descricaoSetorComercialFinal.style.color = "#000000";
				form.idRotaFinal.value = '';
			}	
		}
		
		function pesquisarLocalidadeInicial(){
			chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '',document.forms[0].idLocalidadeInicial);
		}
		
		function pesquisarSetorInicial(){
			chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem', 'idLocalidade', document.forms[0].idLocalidadeInicial.value , 275, 480, 'Informe Localidade Inicial',document.forms[0].idSetorComercialInicial);
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
		
		function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

			var form = document.forms[0];
			
			if (tipoConsulta == 'setorComercialOrigem'){
		    	limpar(2);
		      	form.idSetorComercialInicial.value = codigoRegistro;
		      	form.descricaoSetorComercialInicial.value = descricaoRegistro;
		      	
		      	if (form.idLocalidadeInicial.value == form.idLocalidadeFinal.value 
		      		&& (form.idSetorComercialFinal.value == null || form.idSetorComercialFinal.value == "")){
		      		limpar(5);
			      	form.idSetorComercialFinal.value = codigoRegistro;
			      	form.descricaoSetorComercialFinal.value = descricaoRegistro;
			    }
			    
		      	form.descricaoSetorComercialInicial.style.color = "#000000";
		    }
		}
		
		function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		    var form = document.forms[0];
		
		    if (tipoConsulta == 'localidadeOrigem') {
		    	limpar(1);
	      		form.idLocalidadeInicial.value = codigoRegistro;
	      		form.descricaoLocalidadeInicial.value = descricaoRegistro;
	      		form.descricaoLocalidadeInicial.style.color = "#000000";
	      		form.idLocalidadeFinal.value = codigoRegistro;
		      	form.descricaoLocalidadeFinal.value = descricaoRegistro;
		      	form.descricaoLocalidadeFinal.style.color = "#000000";
	      		form.idSetorComercialInicial.focus();
		    }
		    
		     if (tipoConsulta == 'localidade') {
	     		limpar(4);
		     	form.idLocalidadeFinal.value = codigoRegistro;
		      	form.descricaoLocalidadeFinal.value = descricaoRegistro;
		      	form.descricaoLocalidadeFinal.style.color = "#000000";
		     }
		
		    if (tipoConsulta == 'setorComercial') {
		      	limpar(5);
		      	form.idSetorComercialFinal.value = codigoRegistro;
		      	form.descricaoSetorComercialFinal.value = descricaoRegistro;
		      	form.descricaoSetorComercialInicial.style.color = "#000000";
		    }

   		}
   		
   		function replicarRota(){
   			var form = document.forms[0];
   			if (form.idSetorComercialInicial.value != null &&  form.idSetorComercialInicial.value != ""
   				&& form.idSetorComercialInicial.value == form.idSetorComercialFinal.value ){
   				form.idRotaFinal.value = form.idRotaInicial.value;
 			}
   		}
   		
   		function verificarDependencia(campo, linkPesquisa){
   			var form = document.forms[0];
   			var dependencia = false;
   			
   			if (campo == 1 && form.idLocalidadeInicial.value == ""){
   				form.idLocalidadeInicial.value = "";
   				form.idSetorComercialInicial.value = "";
   				form.idSetorComercialFinal.value = "";
   				form.idLocalidadeFinal.value = "";
   				form.idRotaInicial.value = "";
   				form.idRotaFinal.value = "";
   				alert("Informe Localidade Inicial");
   				dependencia = true;
   			}else if (campo == 2 && form.idSetorComercialInicial.value == ""){
   				form.idSetorComercialInicial.value = "";
   				form.idSetorComercialFinal.value = "";
   				form.idRotaInicial.value = "";
   				form.idRotaFinal.value = "";
   				alert("Informe Setor Comercial Inicial");
   				dependencia = true;
   			}else if (campo == 3 && form.idRotaInicial.value == ""){
   				form.idRotaInicial.value = "";
   				form.idRotaFinal.value = "";
   				alert("Informe Rota Inicial");
   			}
   			
   			if (linkPesquisa == 1 && !dependencia){
   				//localidade final
   				abrirPopup('exibirPesquisarLocalidadeAction.do');
   			}else if (linkPesquisa == 2 && !dependencia){
   				//setor comercial final
   				abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidadeFinal.value+'&tipo=SetorComercial',document.forms[0].idLocalidadeFinal.value,'Localidade Final', 400, 800);
   			}else if (linkPesquisa == 3 && !dependencia){
   				//setor comercial inicial
   				pesquisarSetorInicial();
   			}
   		}
   		
   		function replicaDados(campoOrigem, campoDestino){
			campoDestino.value = campoOrigem.value;
		}

   		function pesquisarLeiturista(){
   		 var form = document.forms[0];

		 var empresas = '';	

   		 for ( i=0; i<document.forms[0].idEmpresaFaturamentoFiltro.length ; i++){
             if (document.forms[0].idEmpresaFaturamentoFiltro.options[i].selected == true 
                     && document.forms[0].idEmpresaFaturamentoFiltro.options[i].value != '-1') {
                     empresas += document.forms[0].idEmpresaFaturamentoFiltro.options[i].value+',';
             }
   		 }
   		 for ( i=0; i<document.forms[0].idEmpresaCobrancaFiltro.length ; i++){
            if (document.forms[0].idEmpresaCobrancaFiltro.options[i].selected == true 
                    && document.forms[0].idEmpresaCobrancaFiltro.options[i].value != '-1') {
                    empresas += document.forms[0].idEmpresaCobrancaFiltro.options[i].value+',';
            }
  		 }         	

   		  
		 abrirPopup('pesquisarLeituristaAction.do?empresas='+empresas, 200, 400 );
   		  
   		}

   		function limparLeiturista(form) {
   	    	form.idLeiturista.value = "";
   	    	form.nomeLeiturista.value = "";
   	    	form.idLeiturista.focus();
   		}	
   		
   		function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

   		    var form = document.forms[0];
   		    
   		    if (tipoConsulta == 'leiturista') {
   		      limparLeiturista(form);
   		      form.idLeiturista.value = codigoRegistro;
   		      form.nomeLeiturista.value = descricaoRegistro;
   		      form.nomeLeiturista.style.color = "#000000";
   		      form.idLeiturista.focus();
   		    }

    	}
	</SCRIPT>
</head>
<html:form
  action="/exibirTransferirRotaGrupoEmpresaAction.do"
  method="post"
  name="TransferirRotasGruposEmpresasActionForm"
  type="gcom.gui.cobranca.TransferirRotasGruposEmpresasActionForm"
>
<body leftmargin="5" topmargin="5">

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
			<td width="600" valign="top" bgcolor="#003399" class="centercoltext">
				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
						<td class="parabg">Transferir Rotas entre Grupos e/ou Empresas</td>
	
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
					<tr>
						<td height="5" colspan="3"></td>
					</tr>
				</table>
				<table width="100%" border="0">
					<tr>
						<td colspan="2">
							<p>Selecione as Rotas que Deseja Transferir entre Grupos e/ou Empresas:</p>
							<p>&nbsp;</p>
						</td>
					</tr>
					<!-- Se não selecionou rotas-->
					<logic:notPresent name="desabilitaFiltroESelecao">
					
					<tr>
						<td><strong>Grupo de Faturamento:</strong></td>
						<td>
							<strong> 
								<html:select
								property="idGrupoFaturamentoFiltro" 
								style="width: 230px;" 
								multiple="mutiple"
								size="3">
									<logic:present name="colecaoGrupoFaturamentoFiltro">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
									</html:option>
									<html:options 
										collection="colecaoGrupoFaturamentoFiltro"
										labelProperty="descricao" 
										property="id" />
									</logic:present>
								</html:select>
							</strong>
						</td>
					</tr>
					
					<tr>
						<td><strong>Grupo de Cobran&ccedil;a:</strong></td>
						<td>
							<strong> 
								<html:select
								property="idGrupoCobrancaFiltro" 
								style="width: 230px;" 
								multiple="mutiple"
								size="3">
									<logic:present name="colecaoGrupoCobrancaFiltro">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
									</html:option>
									<html:options 
										collection="colecaoGrupoCobrancaFiltro"
										labelProperty="descricao" 
										property="id" />
									</logic:present>
								</html:select>
							</strong>
						</td>
					</tr>
					
					<tr>
						<td><strong>Empresa de Faturamento:</strong></td>
						<td>
							<strong> 
								<html:select
								property="idEmpresaFaturamentoFiltro" 
								style="width: 230px;" 
								multiple="mutiple"
								size="3">
									<logic:present name="colecaoEmpresaFaturamentoFiltro">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
									</html:option>
									<html:options 
										collection="colecaoEmpresaFaturamentoFiltro"
										labelProperty="descricao" 
										property="id" />
									</logic:present>
								</html:select>
							</strong>
						</td>
					</tr>
					
					<tr>
						<td><strong>Empresa de Cobran&ccedil;a:</strong></td>
						<td>
							<strong> 
								<html:select
								property="idEmpresaCobrancaFiltro" 
								style="width: 230px;" 
								multiple="mutiple"
								size="3">
									<logic:present name="colecaoEmpresaFaturamentoFiltro">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
									</html:option>
									<html:options 
										collection="colecaoEmpresaFaturamentoFiltro"
										labelProperty="descricao" 
										property="id" />
									</logic:present>
								</html:select>
							</strong>
						</td>
					</tr>
					
					<tr>
						<td><strong>Gerência Regional:</strong></td>
						<td>
							<strong> 
								<html:select
								property="idGerenciaRegional" 
								style="width: 230px;" 
								multiple="mutiple"
								size="3">
									<logic:present name="colecaoGerenciaRegional">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
									</html:option>
									<html:options 
										collection="colecaoGerenciaRegional"
										labelProperty="nome" 
										property="id" />
									</logic:present>
								</html:select>
							</strong>
						</td>
					</tr>
					
					<tr>
						<td><strong>Unidade de Neg&oacute;cio:</strong></td>
						<td>
							<strong> 
								<html:select
								property="idUnidadeNegocio" 
								style="width: 230px;" 
								multiple="mutiple"
								size="3">
									<logic:present name="colecaoUnidadeNegocio">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
									</html:option>
									<html:options 
										collection="colecaoUnidadeNegocio"
										labelProperty="nome" 
										property="id" />
									</logic:present>
								</html:select>
							</strong>
						</td>
					</tr>
					
					<tr>
						<td colspan="2">Informe os dados da inscri&ccedil;&atilde;o inicial:</td>
					</tr>
					
					<tr>
						<td>
							<strong>Localidade Inicial:</strong>
						</td>
						<td colspan="3">
							<html:text 
								maxlength="3" 
								tabindex="1"
								property="idLocalidadeInicial" 
								size="3"
								onblur="replicaDados(document.TransferirRotasGruposEmpresasActionForm.idLocalidadeInicial, document.TransferirRotasGruposEmpresasActionForm.idLocalidadeFinal);"
								onkeyup="limpar(7);somente_numero_zero_a_nove(this);" 
								onkeypress="javascript:limpar(7);
								validaEnterComMensagem(event, 'exibirTransferirRotaGrupoEmpresaAction.do?objetoConsulta=1', 'idLocalidadeInicial','LocalidadeInicial');return isCampoNumerico(event);" />
							
							<a	href="javascript:pesquisarLocalidadeInicial()">
								<img 
									width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Localidade"/>
							</a> 
							<logic:present name="idLocalidadeInicialNaoEncontrada">
								<logic:equal 
									name="idLocalidadeInicialNaoEncontrada" 
									value="exception">
								<html:text 
									property="descricaoLocalidadeInicial" 
									size="40"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:equal>
								
								<logic:notEqual 
									name="idLocalidadeInicialNaoEncontrada" 
									value="exception">
								<html:text 
									property="descricaoLocalidadeInicial" 
									size="40"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEqual>
							</logic:present> 
								
							<logic:notPresent name="idLocalidadeInicialNaoEncontrada">
								<logic:empty 
									name="TransferirRotasGruposEmpresasActionForm" 
									property="idLocalidadeInicial">
								<html:text 
									property="descricaoLocalidadeInicial" 
									value="" size="40"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:empty>
								
								<logic:notEmpty 
									name="TransferirRotasGruposEmpresasActionForm" 
									property="idLocalidadeInicial">
								<html:text 
									property="descricaoLocalidadeInicial" 
									size="40"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>
							</logic:notPresent> 					
							<a href="javascript:limpar(1)">
								<img 
									src="<bean:message 
									key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar Localidade" />
							</a>
						</td>
					</tr>
					
					<tr>
						<td><strong>Setor Comercial Inicial:</strong></td>
						<td colspan="3">
							<html:text 
								maxlength="3"
								property="idSetorComercialInicial" 
								tabindex="2" 
								size="3"
								onblur="replicaDados(document.TransferirRotasGruposEmpresasActionForm.idSetorComercialInicial, document.TransferirRotasGruposEmpresasActionForm.idSetorComercialFinal);"
								onkeyup="limpar(3);somente_numero_zero_a_nove(this);verificarDependencia(1,0);" 
								onkeypress="javascript:limpar(3);
								return isCampoNumerico(event);
								return validaEnterDependenciaComMensagem(event, 'exibirTransferirRotaGrupoEmpresaAction.do?objetoConsulta=2', this, document.forms[0].idLocalidadeInicial.value, 'Localidade Inicial','Setor Comercial');" />
							<a	href="javascript:verificarDependencia(1,3);">
								<img 
									width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Setor Comercial" /></a> 
							<logic:present name="idSetorComercialInicialNaoEncontrado">
								<logic:equal 
									name="idSetorComercialInicialNaoEncontrado"
									value="exception">
								<html:text 
									property="descricaoSetorComercialInicial" 
									size="40"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:equal>
								<logic:notEqual 
									name="idSetorComercialInicialNaoEncontrado"
									value="exception">
								<html:text 
									property="descricaoSetorComercialInicial" 
									size="40"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEqual>
							</logic:present> 
							<logic:notPresent name="idSetorComercialInicialNaoEncontrado">
								<logic:empty 
									name="TransferirRotasGruposEmpresasActionForm"
									property="idSetorComercialInicial">
									<html:text 
										property="descricaoSetorComercialInicial" 
										value="" 
										size="40"
										maxlength="30" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:empty>
								<logic:notEmpty 
									name="TransferirRotasGruposEmpresasActionForm"
									property="idSetorComercialInicial">
									<html:text 
										property="descricaoSetorComercialInicial" 
										size="40"
										maxlength="30" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>
							</logic:notPresent> 
							<a	href="javascript:limpar(2);">
								<img 
									src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar Setor Comercial" />
							</a>
						</td>
					</tr>
					
					<tr> 
	                	<td><strong> Rota Inicial:</strong></td>
	                	<td>
	                		<html:text 
	                			property="idRotaInicial"
	                			onblur="replicaDados(document.TransferirRotasGruposEmpresasActionForm.idRotaInicial, document.TransferirRotasGruposEmpresasActionForm.idRotaFinal);" 
	                			onkeyup="somente_numero_zero_a_nove(this);verificarDependencia(2,0);replicarRota();"	
	                			onkeypress="return isCampoNumerico(event);"          
	                			size="4" 
	                			maxlength="4" 
	                			tabindex="3" />
	                  	</td>
              		</tr>
              		
              		<tr>
						<td colspan="2">Informe os dados da inscri&ccedil;&atilde;o Final:</td>
					</tr>
					
					<tr>
						<td>
							<strong>Localidade Final:</strong>
						</td>
						<td colspan="3">
							<html:text 
								maxlength="3" 
								tabindex="1"
								property="idLocalidadeFinal" 
								size="3"
								onkeyup="limpar(5);somente_numero_zero_a_nove(this);verificarDependencia(1,0);" 
								onkeypress="javascript:limpar(5);
								validaEnterComMensagem(event, 'exibirTransferirRotaGrupoEmpresaAction.do?objetoConsulta=1', 'idLocalidadeFinal','Localidade');return isCampoNumerico(event);" />
							<a	href="javascript:verificarDependencia(1,1);">
								<img 
									width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Localidade"/>
							</a> 
							<logic:present name="idLocalidadeFinalNaoEncontrada">
								<logic:equal 
									name="idLocalidadeFinalNaoEncontrada" 
									value="exception">
								<html:text 
									property="descricaoLocalidadeFinal" 
									size="40"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:equal>
								
								<logic:notEqual 
									name="idLocalidadeFinalNaoEncontrada" 
									value="exception">
								<html:text 
									property="descricaoLocalidadeFinal" 
									size="40"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEqual>
							</logic:present> 
								
							<logic:notPresent name="idLocalidadeFinalNaoEncontrada">
								<logic:empty 
									name="TransferirRotasGruposEmpresasActionForm" 
									property="idLocalidadeFinal">
								<html:text 
									property="descricaoLocalidadeFinal" 
									value="" size="40"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:empty>
								
								<logic:notEmpty 
									name="TransferirRotasGruposEmpresasActionForm" 
									property="idLocalidadeFinal">
								<html:text 
									property="descricaoLocalidadeFinal" 
									size="40"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>
							</logic:notPresent> 					
							<a href="javascript:limpar(4)">
								<img 
									src="<bean:message 
									key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar Localidade" />
							</a>
						</td>
					</tr>
					
					<tr>
						<td><strong>Setor Comercial Final:</strong></td>
						<td colspan="3">
							<html:text 
								maxlength="3"
								property="idSetorComercialFinal" 
								tabindex="2" 
								size="3"
								onkeyup="limpar(6);somente_numero_zero_a_nove(this);verificarDependencia(2,0);" 
								onkeypress="javascript:limpar(6);
								return isCampoNumerico(event);
								return validaEnterDependenciaComMensagem(event, 'exibirTransferirRotaGrupoEmpresaAction.do?objetoConsulta=1', this, document.forms[0].idLocalidadeFinal.value, 'Localidade Final','Setor Comercial');"
								 />
							<a href="javascript:verificarDependencia(2,2);">
								<img 
									width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Setor Comercial" /></a> 
							<logic:present name="idSetorComercialFinalNaoEncontrado">
								<logic:equal 
									name="idSetorComercialFinalNaoEncontrado"
									value="exception">
								<html:text 
									property="descricaoSetorComercialFinal" 
									size="40"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:equal>
								<logic:notEqual 
									name="idSetorComercialFinalNaoEncontrado"
									value="exception">
								<html:text 
									property="descricaoSetorComercialFinal" 
									size="40"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEqual>
							</logic:present> 
							<logic:notPresent name="idSetorComercialFinalNaoEncontrado">
								<logic:empty 
									name="TransferirRotasGruposEmpresasActionForm"
									property="idSetorComercialFinal">
									<html:text 
										property="descricaoSetorComercialFinal" 
										value="" 
										size="40"
										maxlength="30" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:empty>
								<logic:notEmpty 
									name="TransferirRotasGruposEmpresasActionForm"
									property="idSetorComercialFinal">
									<html:text 
										property="descricaoSetorComercialFinal" 
										size="40"
										maxlength="30" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>
							</logic:notPresent> 
							<a	href="javascript:limpar(5);">
								<img 
									src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar Setor Comercial" />
							</a>
						</td>
					</tr>
					
					<tr> 
	                	<td><strong> Rota Final:</strong></td>
	                	<td>
	                		<html:text 
	                			property="idRotaFinal" 
	                			onkeyup="somente_numero_zero_a_nove(this);verificarDependencia(3);"  
	                			onkeypress="return isCampoNumerico(event);"
	                			size="4" 
	                			maxlength="4" 
	                			tabindex="3" />
	                  	</td>
              		</tr>
              		
              		<tr> 
						<td width="163"><strong>Tipo Leitura:</strong></td>
						<td align="right"><div align="left"> <span class="style2">
				  			<html:select property="idTipoLeitura" tabindex="1" >
							<html:option value="<%= ""+ConstantesSistema.NUMERO_NAO_INFORMADO %>">&nbsp;</html:option>				  
							<html:options collection="colecaoTipoLeitura"
								labelProperty="descricao" property="id" />
				  			</html:select>
				  			</span></div>
						</td>
              		</tr>
              		
              		<tr>
					<td width="130"><strong>Leiturista:</strong></td>
					<td colspan="3"><html:text maxlength="6" property="idLeiturista"
						tabindex="17" size="6"
						onkeypress="javascript:validaEnterComMensagem(event, 'exibirTransferirRotaGrupoEmpresaAction.do?reloadPage=1', 'idLeiturista', 'Leiturista');return isCampoNumerico(event);" />
					<a href="javascript:pesquisarLeiturista();">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Leiturista" />
					</a> 
					<logic:present name="idLeituristaEncontrado" scope="request">
						<html:text maxlength="30" property="nomeLeiturista"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="28" />
					</logic:present> 
					<logic:notPresent name="idLeituristaEncontrado" scope="request">
						<html:text maxlength="30" property="nomeLeiturista"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="28" />
					</logic:notPresent> 
					<a href="javascript:limparLeiturista(document.TransferirRotasGruposEmpresasActionForm);"> 
					  <img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
					</tr>		
					
					<tr>
						<td width="40">
						</td>
						<td width="40">
						</td>
						<td width="40">
						</td>
						<td>
							<div align="right">
								<input name="Button" type="button" class="bottonRightCol" value="Selecionar" align="left" onclick="selecionarRotas();" >
							</div>
						</td>
					</tr>
					</logic:notPresent>
					
					<!-- Se selecionou rotas-->
					<logic:present name="desabilitaFiltroESelecao">
					
					<tr>
						<td><strong>Grupo de Faturamento:</strong></td>
						<td>
							<strong> 
								<html:select
								property="idGrupoFaturamentoFiltro" 
								style="width: 230px;" 
								multiple="mutiple"
								disabled="true" 
								size="3">
									<logic:present name="colecaoGrupoFaturamentoFiltro">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
									</html:option>
									<html:options 
										collection="colecaoGrupoFaturamentoFiltro"
										labelProperty="descricao" 
										property="id" />
									</logic:present>
								</html:select>
							</strong>
						</td>
					</tr>
					
					<tr>
						<td><strong>Grupo de Cobran&ccedil;a:</strong></td>
						<td>
							<strong> 
								<html:select
								property="idGrupoCobrancaFiltro" 
								style="width: 230px;" 
								multiple="mutiple"
								disabled="true" 
								size="3">
									<logic:present name="colecaoGrupoCobrancaFiltro">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
									</html:option>
									<html:options 
										collection="colecaoGrupoCobrancaFiltro"
										labelProperty="descricao" 
										property="id" />
									</logic:present>
								</html:select>
							</strong>
						</td>
					</tr>
					
					<tr>
						<td><strong>Empresa de Faturamento:</strong></td>
						<td>
							<strong> 
								<html:select
								property="idEmpresaFaturamentoFiltro" 
								style="width: 230px;" 
								multiple="mutiple"
								disabled="true" 
								size="3">
									<logic:present name="colecaoEmpresaFaturamentoFiltro">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
									</html:option>
									<html:options 
										collection="colecaoEmpresaFaturamentoFiltro"
										labelProperty="descricao" 
										property="id" />
									</logic:present>
								</html:select>
							</strong>
						</td>
					</tr>
					
					<tr>
						<td><strong>Empresa de Cobran&ccedil;a:</strong></td>
						<td>
							<strong> 
								<html:select
								property="idEmpresaCobrancaFiltro" 
								style="width: 230px;" 
								multiple="mutiple"
								disabled="true" 
								size="3">
									<logic:present name="colecaoEmpresaFaturamentoFiltro">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
									</html:option>
									<html:options 
										collection="colecaoEmpresaFaturamentoFiltro"
										labelProperty="descricao" 
										property="id" />
									</logic:present>
								</html:select>
							</strong>
						</td>
					</tr>
					
					<tr>
						<td><strong>Gerencia Regional:</strong></td>
						<td>
							<strong> 
								<html:select
								property="idGerenciaRegional" 
								style="width: 230px;" 
								multiple="mutiple"
								disabled="true" 
								size="3">
									<logic:present name="colecaoGerenciaRegional">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
									</html:option>
									<html:options 
										collection="colecaoGerenciaRegional"
										labelProperty="nome" 
										property="id" />
									</logic:present>
								</html:select>
							</strong>
						</td>
					</tr>
					
					<tr>
						<td><strong>Unidade de Neg&oacute;cio:</strong></td>
						<td>
							<strong> 
								<html:select
								property="idUnidadeNegocio" 
								style="width: 230px;" 
								multiple="mutiple"
								disabled="true" 
								size="3">
									<logic:present name="colecaoUnidadeNegocio">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
									</html:option>
									<html:options 
										collection="colecaoUnidadeNegocio"
										labelProperty="nome" 
										property="id" />
									</logic:present>
								</html:select>
							</strong>
						</td>
					</tr>
					
					<tr>
						<td colspan="2">Informe os dados da inscri&ccedil;&atilde;o inicial:</td>
					</tr>
					
					<tr>
						<td>
							<strong>Localidade Inicial:</strong>
						</td>
						<td colspan="3">
							<html:text 
								maxlength="3" 
								tabindex="1"
								property="idLocalidadeInicial" 
								size="3"
								onkeyup="limpar(7);somente_numero_zero_a_nove(this);" 
								onkeypress="javascript:limpar(7);
								validaEnterComMensagem(event, 'exibirTransferirRotaGrupoEmpresaAction.do?objetoConsulta=1', 'idLocalidadeInicial','LocalidadeInicial');" 
								disabled="true" />
							
							<a	href="javascript:pesquisarLocalidadeInicial()">
								<img 
									width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Localidade"/>
							</a> 
							<logic:present name="idLocalidadeInicialNaoEncontrada">
								<logic:equal 
									name="idLocalidadeInicialNaoEncontrada" 
									value="exception">
								<html:text 
									property="descricaoLocalidadeInicial" 
									size="40"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:equal>
								
								<logic:notEqual 
									name="idLocalidadeInicialNaoEncontrada" 
									value="exception">
								<html:text 
									property="descricaoLocalidadeInicial" 
									size="40"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEqual>
							</logic:present> 
								
							<logic:notPresent name="idLocalidadeInicialNaoEncontrada">
								<logic:empty 
									name="TransferirRotasGruposEmpresasActionForm" 
									property="idLocalidadeInicial">
								<html:text 
									property="descricaoLocalidadeInicial" 
									value="" size="40"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:empty>
								
								<logic:notEmpty 
									name="TransferirRotasGruposEmpresasActionForm" 
									property="idLocalidadeInicial">
								<html:text 
									property="descricaoLocalidadeInicial" 
									size="40"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>
							</logic:notPresent> 					
							<a href="">
								<img 
									src="<bean:message 
									key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar Localidade" />
							</a>
						</td>
					</tr>
					
					<tr>
						<td><strong>Setor Comercial Inicial:</strong></td>
						<td colspan="3">
							<html:text 
								maxlength="3"
								property="idSetorComercialInicial" 
								tabindex="2" 
								size="3"
								onkeyup="limpar(3);somente_numero_zero_a_nove(this);verificarDependencia(1,0);" 
								onkeypress="javascript:limpar(3);
								return validaEnterDependenciaComMensagem(event, 'exibirTransferirRotaGrupoEmpresaAction.do?objetoConsulta=2', this, document.forms[0].idLocalidadeInicial.value, 'Localidade Inicial','Setor Comercial');" 
								disabled="true" />
							<a	href="javascript:verificarDependencia(1,3);">
								<img 
									width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Setor Comercial" /></a> 
							<logic:present name="idSetorComercialInicialNaoEncontrado">
								<logic:equal 
									name="idSetorComercialInicialNaoEncontrado"
									value="exception">
								<html:text 
									property="descricaoSetorComercialInicial" 
									size="40"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:equal>
								<logic:notEqual 
									name="idSetorComercialInicialNaoEncontrado"
									value="exception">
								<html:text 
									property="descricaoSetorComercialInicial" 
									size="40"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEqual>
							</logic:present> 
							<logic:notPresent name="idSetorComercialInicialNaoEncontrado">
								<logic:empty 
									name="TransferirRotasGruposEmpresasActionForm"
									property="idSetorComercialInicial">
									<html:text 
										property="descricaoSetorComercialInicial" 
										value="" 
										size="40"
										maxlength="30" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:empty>
								<logic:notEmpty 
									name="TransferirRotasGruposEmpresasActionForm"
									property="idSetorComercialInicial">
									<html:text 
										property="descricaoSetorComercialInicial" 
										size="40"
										maxlength="30" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>
							</logic:notPresent> 
							<a	href="">
								<img 
									src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar Setor Comercial" />
							</a>
						</td>
					</tr>
					
					<tr> 
	                	<td><strong> Rota Inicial:</strong></td>
	                	<td>
	                		<html:text 
	                			property="idRotaInicial" 
	                			onkeyup="somente_numero_zero_a_nove(this);verificarDependencia(2,0);replicarRota();"	          
	                			size="4" 
	                			maxlength="4" 
	                			tabindex="3" 
	                			disabled="true" />
	                  	</td>
              		</tr>
              		
              		<tr>
						<td colspan="2">Informe os dados da inscri&ccedil;&atilde;o Final:</td>
					</tr>
					
					<tr>
						<td>
							<strong>Localidade Final:</strong>
						</td>
						<td colspan="3">
							<html:text 
								maxlength="3" 
								tabindex="1"
								property="idLocalidadeFinal" 
								size="3"
								onkeyup="limpar(5);somente_numero_zero_a_nove(this);verificarDependencia(1,0);" 
								onkeypress="javascript:limpar(5);
								validaEnterComMensagem(event, 'exibirTransferirRotaGrupoEmpresaAction.do?objetoConsulta=1', 'idLocalidadeFinal','Localidade');" 
								disabled="true" />
							<a	href="javascript:verificarDependencia(1,1);">
								<img 
									width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Localidade"/>
							</a> 
							<logic:present name="idLocalidadeFinalNaoEncontrada">
								<logic:equal 
									name="idLocalidadeFinalNaoEncontrada" 
									value="exception">
								<html:text 
									property="descricaoLocalidadeFinal" 
									size="40"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:equal>
								
								<logic:notEqual 
									name="idLocalidadeFinalNaoEncontrada" 
									value="exception">
								<html:text 
									property="descricaoLocalidadeFinal" 
									size="40"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEqual>
							</logic:present> 
								
							<logic:notPresent name="idLocalidadeFinalNaoEncontrada">
								<logic:empty 
									name="TransferirRotasGruposEmpresasActionForm" 
									property="idLocalidadeFinal">
								<html:text 
									property="descricaoLocalidadeFinal" 
									value="" size="40"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:empty>
								
								<logic:notEmpty 
									name="TransferirRotasGruposEmpresasActionForm" 
									property="idLocalidadeFinal">
								<html:text 
									property="descricaoLocalidadeFinal" 
									size="40"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>
							</logic:notPresent> 					
							<a href="">
								<img 
									src="<bean:message 
									key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar Localidade" 
									disabled="true"/>
							</a>
						</td>
					</tr>
					
					<tr>
						<td><strong>Setor Comercial Final:</strong></td>
						<td colspan="3">
							<html:text 
								maxlength="3"
								property="idSetorComercialFinal" 
								tabindex="2" 
								size="3"
								onkeyup="limpar(6);somente_numero_zero_a_nove(this);verificarDependencia(2,0);" 
								onkeypress="javascript:limpar(6);
								return validaEnterDependenciaComMensagem(event, 'exibirTransferirRotaGrupoEmpresaAction.do?objetoConsulta=1', this, document.forms[0].idLocalidadeFinal.value, 'Localidade Final','Setor Comercial');" 
								disabled="true" />
							<a href="javascript:verificarDependencia(2,2);">
								<img 
									width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Setor Comercial" /></a> 
							<logic:present name="idSetorComercialFinalNaoEncontrado">
								<logic:equal 
									name="idSetorComercialFinalNaoEncontrado"
									value="exception">
								<html:text 
									property="descricaoSetorComercialFinal" 
									size="40"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:equal>
								<logic:notEqual 
									name="idSetorComercialFinalNaoEncontrado"
									value="exception">
								<html:text 
									property="descricaoSetorComercialFinal" 
									size="40"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEqual>
							</logic:present> 
							<logic:notPresent name="idSetorComercialFinalNaoEncontrado">
								<logic:empty 
									name="TransferirRotasGruposEmpresasActionForm"
									property="idSetorComercialFinal">
									<html:text 
										property="descricaoSetorComercialFinal" 
										value="" 
										size="40"
										maxlength="30" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:empty>
								<logic:notEmpty 
									name="TransferirRotasGruposEmpresasActionForm"
									property="idSetorComercialFinal">
									<html:text 
										property="descricaoSetorComercialFinal" 
										size="40"
										maxlength="30" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>
							</logic:notPresent> 
							<a	href="">
								<img 
									src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar Setor Comercial"/>
							</a>
						</td>
					</tr>
					
					<tr> 
	                	<td><strong> Rota Final:</strong></td>
	                	<td>
	                		<html:text 
	                			property="idRotaFinal" 
	                			onkeyup="somente_numero_zero_a_nove(this);verificarDependencia(3);"  
	                			size="4" 
	                			maxlength="4" 
	                			tabindex="3" 
	                			disabled="true" />
	                  	</td>
              		</tr>
              		
              		<tr> 
						<td width="163"><strong>Tipo Leitura:</strong></td>
						<td align="right"><div align="left"> <span class="style2">
				  			<html:select property="idTipoLeitura" tabindex="1" disabled="true">
							<html:option value="<%= ""+ConstantesSistema.NUMERO_NAO_INFORMADO %>">&nbsp;</html:option>				  
							<html:options collection="colecaoTipoLeitura"
								labelProperty="descricao" property="id" />
				  			</html:select>
				  			</span></div>
						</td>
              		</tr>
              		
              		<tr>
					<td width="130"><strong>Leiturista:</strong></td>
					<td colspan="3"><html:text maxlength="6" property="idLeiturista"
						tabindex="17" size="6" disabled="true"
						onkeypress="javascript:validaEnterComMensagem(event, 'exibirTransferirRotaGrupoEmpresaAction.do?reloadPage=1', 'idLeiturista', 'Leiturista');" />
					<a href="javascript:pesquisarLeiturista();">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Leiturista" />
					</a> 
					<logic:present name="idLeituristaEncontrado" scope="request">
						<html:text maxlength="30" property="nomeLeiturista"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="28" disabled="true" />
					</logic:present> 
					<logic:notPresent name="idLeituristaEncontrado" scope="request">
						<html:text maxlength="30" property="nomeLeiturista"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="28" disabled="true" />
					</logic:notPresent> 
					<a href="javascript:limparLeiturista(document.TransferirRotasGruposEmpresasActionForm);"> 
					  <img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
					</tr>		
					
					
					<tr>
						<td width="40">
						</td>
						<td width="40">
						</td>
						<td width="40">
						</td>
						<td>
							<div align="right">
								<input name="Button" type="button" disabled="true" class="bottonRightCol" value="Selecionar" align="left" onclick="selecionarRotas();" >
							</div>
						</td>
					</tr>
					</logic:present>
					
					<!-- caso existam rotas selecionadas -->
					<logic:present name="rotasSelecionadas">
					<tr>
						<td width="40">
						</td>
					</tr>
					<tr>
						<td width="40">
						</td>
					</tr>
					
					<tr>
						<td colspan="5">
							<table width="100%" border="0">
								<tr>
									<td>
										<div style="width: 100%; height: 100%; overflow: auto;">
											<table width="100%" align="center" bgcolor="#99CCFF">
												<tr>
													<td><strong> Atual:</strong></td>
												</tr>
												<tr>
													<td>
														<table>	
															<tr bgcolor="#cbe5fe">
																<td height="53"><strong> Quantidade de Rotas na Sele&ccedil;&atilde;o:</strong></td>
											                	<td>
											                		<html:text property="quantidadeRotas" size="8" maxlength="8"
																	readonly="true" />
											                  	</td>
										                  	</tr>
										                  	<tr bgcolor="#cbe5fe">
																<td height="53"><strong>Grupo de Faturamento:</strong></td>
																
																<td>
																	<strong> 
																		<html:select
																		property="idGrupoFaturamentoSelecao" 
																		style="width: 230px;" 
																		multiple="mutiple"
																		disabled="true"
																		size="3">
																			<logic:present name="colecaoGrupoFaturamentoSelecao">
																			<html:option
																				value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
																			</html:option>
																			<html:options 
																				collection="colecaoGrupoFaturamentoSelecao"
																				labelProperty="descricao" 
																				property="id" />
																			</logic:present>
																		</html:select>
																	</strong>
																</td>
															</tr>
															<tr bgcolor="#cbe5fe">
																<td height="53"><strong>Grupo de Cobran&ccedil;a:</strong></td>
																<td>
																	<strong> 
																		<html:select
																		property="idGrupoCobrancaSelecao" 
																		style="width: 230px;" 
																		multiple="mutiple"
																		disabled="true"
																		size="3">
																			<logic:present name="colecaoGrupoCobrancaSelecao">
																			<html:option
																				value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
																			</html:option>
																			<html:options 
																				collection="colecaoGrupoCobrancaSelecao"
																				labelProperty="descricao" 
																				property="id" />
																			</logic:present>
																		</html:select>
																	</strong>
																</td>
															</tr>
															<tr bgcolor="#cbe5fe">
																<td height="53"><strong>Empresa de Faturamento:</strong></td>
																<td>
																	<strong> 
																		<html:select
																		property="idEmpresaFaturamentoSelecao" 
																		style="width: 230px;" 
																		multiple="mutiple"
																		disabled="true"
																		size="3">
																			<logic:present name="colecaoEmpresaFaturamentoSelecao">
																			<html:option
																				value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
																			</html:option>
																			<html:options 
																				collection="colecaoEmpresaFaturamentoSelecao"
																				labelProperty="descricao" 
																				property="id" />
																			</logic:present>
																		</html:select>
																	</strong>
																</td>
															</tr>
															<tr bgcolor="#cbe5fe">
																<td height="53"><strong>Empresa de Cobran&ccedil;a:</strong></td>
																<td>
																	<strong> 
																		<html:select
																		property="idEmpresaCobrancaSelecao" 
																		style="width: 230px;" 
																		multiple="mutiple"
																		disabled="true"
																		size="3">
																			<logic:present name="colecaoEmpresaCobrancaSelecao">
																			<html:option
																				value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
																			</html:option>
																			<html:options 
																				collection="colecaoEmpresaCobrancaSelecao"
																				labelProperty="descricao" 
																				property="id" />
																			</logic:present>
																		</html:select>
																	</strong>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td></td>
												</tr>
												
											</table>
										</div>
									</td>
									<td>
										<div style="width: 100%; height: 100%; overflow: auto;">
											<table width="100%" align="center" bgcolor="#99CCFF">
												<tr>
													<td><strong> Destino:</strong></td>
												</tr>
												<tr>
													<td>
														<table>	
															<tr bgcolor="#cbe5fe">
																<td colspan="2" height="53"><strong>Informe os grupos e/ ou empresas para 
	                  															onde ser&atilde;o tranferidas as rotas:</strong>
       															</td>
										                  	</tr>
										                  	
										                  	<logic:present name="colecaoFaturamentoGrupoDestino">
										                  	<tr bgcolor="#cbe5fe">
										                  		<td height="53"><strong>Grupo de Faturamento:</strong></td>
																<td>
																	<strong> 
																		<html:select
																			property="idGrupoFaturamentoDestino" 
																			style="width: 170px;">
																			
																			<html:option
																				value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
																			</html:option>
																			<html:options 
																				collection="colecaoFaturamentoGrupoDestino"
																				labelProperty="descricao" 
																				property="id" />
																			
																		</html:select>
																	</strong>
																</td>
										                  	</tr>
										                  	</logic:present>
										                  	<logic:notPresent name="colecaoFaturamentoGrupoDestino">
									                  		<tr bgcolor="#cbe5fe">
										                  		<td colspan="2" height="53">
										                  		</td>
										                  	</tr>
										                  	</logic:notPresent>
										                  	
										                  	<logic:present name="colecaoCobrancaGrupoDestino">
										                  	<tr bgcolor="#cbe5fe">
										                  		<td height="53"><strong>Grupo de Cobran&ccedil;a:</strong></td>
																<td>
																	<strong>
																		<html:select
																			property="idGrupoCobrancaDestino" 
																			style="width: 170px;">
																		
																		<html:option
																			value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
																		</html:option>
																		<html:options 
																			collection="colecaoCobrancaGrupoDestino"
																			labelProperty="descricao" 
																			property="id" />
																		
																		</html:select>
																	</strong>
																</td>
										                  	</tr>
										                  	</logic:present>
										                  	<logic:notPresent name="colecaoCobrancaGrupoDestino">
									                  		<tr bgcolor="#cbe5fe">
										                  		<td colspan="2" height="53">
										                  		</td>
										                  	</tr>
										                  	</logic:notPresent>
										                  	
										                  	<logic:present name="colecaoEmpresaDestino">
										                  	<tr bgcolor="#cbe5fe">
										                  		<td height="53"><strong>Empresa de Faturamento:</strong></td>
										                  		<td>
																	<strong> 
																		<html:select
																		property="idEmpresaFaturamentoDestino" 
																		style="width: 170px;">
																			
																			<html:option
																				value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
																			</html:option>
																			<html:options 
																				collection="colecaoEmpresaDestino"
																				labelProperty="descricao" 
																				property="id" />
																			
																		</html:select>
																	</strong>
																</td>
										                  	</tr>
										                  	</logic:present>
										                  	<logic:notPresent name="colecaoEmpresaDestino">
									                  		<tr bgcolor="#cbe5fe">
										                  		<td colspan="2" height="53">
										                  		</td>
										                  	</tr>
										                  	</logic:notPresent>
										                  	
										                  	<logic:present name="colecaoEmpresaDestino">
										                  	<tr bgcolor="#cbe5fe">
										                  		<td height="53"><strong>Empresa de Cobran&ccedil;a:</strong></td>
																<td>
																	<strong> 
																		<html:select
																		property="idEmpresaCobrancaDestino" 
																		style="width: 170px;">
																			
																			<html:option
																				value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
																			</html:option>
																			<html:options 
																				collection="colecaoEmpresaDestino"
																				labelProperty="descricao" 
																				property="id" />
																			
																		</html:select>
																	</strong>
																</td>
										                  	</tr>
										                  	</logic:present>
									                  		<logic:notPresent name="colecaoEmpresaDestino">
									                  		<tr bgcolor="#cbe5fe">
										                  		<td colspan="2" height="53">
										                  		</td>
										                  	</tr>
									                  		</logic:notPresent>
														</table>
													</td>
												</tr>
												<tr>
													<td></td>
												</tr>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr>
						<td>
						</td>
					</tr>
					
					</logic:present>
					
					<tr>
						<td width="40">
						</td>
						<td width="40">
						</td>
						<td width="40">
						</td>
						<td>
							<div align="right">
							<!-- caso existam rotas selecionadas -->
							<logic:present name="rotasSelecionadas">
							  <input name="Button" type="button" class="bottonRightCol" value="Limpar" align="left" onclick="window.location.href='<html:rewrite page="/exibirTransferirRotaGrupoEmpresaAction.do?desfazer=S"/>'" >
							  <gsan:controleAcessoBotao name="Submit23" onclick="validarFormSubmit();" value="Transferir" url="transferirRotaGrupoEmpresaConfirmarAction.do"/>
							</logic:present>
							<input name="Button" type="button" class="bottonRightCol"
								value="Cancelar" align="left"
								onClick="window.location.href='/gsan/telaPrincipal.do'">
							</div>
						</td>
					</tr>
					
				</table>
			</td>
		</tr>
	</table>
</html:form>
</body>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:html>