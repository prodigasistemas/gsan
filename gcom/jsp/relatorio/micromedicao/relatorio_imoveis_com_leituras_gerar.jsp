<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false" formName="GerarRelatorioImoveisComLeiturasActionForm" dynamicJavascript="true" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript">
	
	function validarForm(){
		
		var form = document.forms[0];
		if(validateGerarRelatorioImoveisComLeiturasActionForm(form)  
			&& validarCampos() ){
			
			// toggleBox('demodiv', 1);
			submeterFormPadrao(form);
		}
	}
	
	function validarCampos(){
		
		var form = document.forms[0];
		
		msg = verificarAtributosInicialFinal(form.localidadeInicial, form.localidadeFinal, "Localidade");
		if (msg != "") {
			alert(msg);
			return false;
		} else {
			msg = verificarAtributosInicialFinal(form.setorComercialInicial, form.setorComercialFinal, "Setor Comercial");
			if (msg != "") {
				alert(msg);
				return false;
			} else {
				msg = verificarAtributosInicialFinal(form.rotaInicial, form.rotaFinal, "Rota");
				if (msg != "") {
					alert(msg);
					return false;
				}
			}
		}
		
		return true;
	}
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,campo){
		if (!campo.disabled) {
	  		if (objeto == null || codigoObjeto == null) {
	     		if (tipo == "") {
	      			abrirPopup(url, altura, largura);
	     		} else {
		  			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		 		}
	 		} else {
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)) {
					alert(msg);
				} else {
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
  		}
	}
	
  	function reloadForm(){
  		var form = document.forms[0];
  		form.action='exibirGerarRelatorioImoveisComLeiturasAction.do';
	    form.submit();
  	}
	
  	function limpar(){

  		var form = document.forms[0];

		form.localidadeInicial.value = "";
		form.nomeLocalidadeInicial.value = "";
		form.localidadeFinal.value = "";
		form.nomeLocalidadeFinal.value = "";
		form.nomeLocalidadeInicial.style.color = "#000000";
	  	form.nomeLocalidadeFinal.style.color = "#000000";

		form.setorComercialInicial.value = "";
		form.nomeSetorComercialInicial.value = "";
		form.setorComercialFinal.value = "";
		form.nomeSetorComercialFinal.value = "";
		form.nomeSetorComercialInicial.style.color = "#000000";
	  	form.nomeSetorComercialFinal.style.color = "#000000";

		form.rotaInicial.value = "";
		form.rotaFinal.value = "";

  		form.action='exibirGerarRelatorioImoveisComLeiturasAction.do?menu=sim';
	    form.submit();
  	}
  	
  	function replicarLocalidade(){
		var formulario = document.forms[0]; 
		
		formulario.localidadeFinal.value = formulario.localidadeInicial.value;
		formulario.setorComercialInicial.focus;
	}
	
	function replicarSetorComercial(){
		var formulario = document.forms[0]; 
		formulario.setorComercialFinal.value = formulario.setorComercialInicial.value;
		formulario.rotaInicial.focus;
	} 
	
	function replicarRota(){
		var formulario = document.forms[0]; 
		formulario.rotaFinal.value = formulario.rotaInicial.value;
	} 
  	
	function limparOrigem(tipo){
		var form = document.forms[0];
		
		switch(tipo){
		
		case 1: //De localidade pra baixo

			form.nomeLocalidadeInicial.value = "";
			form.localidadeFinal.value = "";
			form.nomeLocalidadeFinal.value = "";
			form.setorComercialInicial.value = "";
		    form.setorComercialFinal.value = "";
		  	form.rotaInicial.value = "";
		    
		case 2: //De setor para baixo

		   form.nomeSetorComercialInicial.value = "";
		   form.setorComercialFinal.value = "";
		   form.nomeSetorComercialFinal.value = "";
		   form.rotaFinal.value = "";
		}
	}
	
	function limparBorrachaOrigem(tipo){
		var form = document.forms[0];
		
		switch(tipo){
			case 1: //De localidara pra baixo

				form.localidadeInicial.value = "";
				form.nomeLocalidadeInicial.value = "";
				form.localidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";
				break;
			case 2: //De setor para baixo
		     	
		     	form.setorComercialInicial.value = "";
		     	form.nomeSetorComercialInicial.value = "";
		     	form.setorComercialFinal.value = "";
		     	form.nomeSetorComercialFinal.value = "";
		}
	}
	
	function limparBorrachaDestino(tipo){
		var form = document.forms[0];

		switch(tipo){
			case 1: //De localidade pra baixo
				form.localidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";
				form.setorComercialFinal.value = "";
				
			case 2: //De setor para baixo		   
		   		form.setorComercialFinal.value = ""; 
		   		form.nomeSetorComercialFinal.value = "";		   
		}
	}
	
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'localidadeOrigem') {
      		
      		form.localidadeInicial.value = codigoRegistro;
	  		form.nomeLocalidadeInicial.value = descricaoRegistro;
	  		
	  		form.localidadeFinal.value = codigoRegistro;
      		form.nomeLocalidadeFinal.value = descricaoRegistro;
      		
	  		form.nomeLocalidadeInicial.style.color = "#000000";
	  		form.nomeLocalidadeFinal.style.color = "#000000";
	  		
	  		form.setorComercialInicial.focus();
		}

		if (tipoConsulta == 'localidadeDestino') {
		
      		form.localidadeFinal.value = codigoRegistro;
      		form.nomeLocalidadeFinal.value = descricaoRegistro;
	  		form.nomeLocalidadeFinal.style.color = "#000000";

	  		form.setorComercialFinal.focus();
		}
	}
	
	
	function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'setorComercialOrigem') {
		  	form.setorComercialInicial.value = codigoRegistro;
		  	form.nomeSetorComercialInicial.value = descricaoRegistro;
		  	form.nomeSetorComercialInicial.style.color = "#000000"; 
		  	
		  	form.setorComercialFinal.value = codigoRegistro;
		  	form.nomeSetorComercialFinal.value = descricaoRegistro;
		  	form.nomeSetorComercialFinal.style.color = "#000000";
		  	
		}

		if (tipoConsulta == 'setorComercialDestino') {
		  	form.setorComercialFinal.value = codigoRegistro;
		  	form.nomeSetorComercialFinal.value = descricaoRegistro;
		  	form.nomeSetorComercialFinal.style.color = "#000000";
		}
	}	
  	function desabilitaIntervaloDiferente(){
  		/*
		var form = document.GerarRelatorioImoveisComLeiturasActionForm;
	    if(form.localidadeInicial.value != form.localidadeFinal.value){	
	    	desabilitaCampo(form.setorComercialInicial);
 			form.nomeSetorComercialInicial.value = '';
 			
		  	desabilitaCampo(form.setorComercialFinal);
		 	form.nomeSetorComercialFinal.value = '';
		 	
		  	desabilitaCampo(form.rotaInicial);
		  	desabilitaCampo(form.rotaFinal);
		 	
		 	form.rotaInicial.value = '';
		 	form.rotaFinal.value = '';
		 	
		  } else {
		  	habilitaCampo(form.setorComercialInicial);
		  	habilitaCampo(form.setorComercialFinal);
		  	habilitaCampo(form.rotaInicial);
		  	habilitaCampo(form.rotaFinal);
		 }		
		 */
	}
			
	function desabilitaIntervaloDiferenteSetor(){
		/*
		var form = document.GerarRelatorioImoveisComLeiturasActionForm;	
	    if(form.setorComercialInicial.value != form.setorComercialFinal.value 
	    	|| form.setorComercialInicial.readOnly == true){
			desabilitaCampo(form.rotaInicial);
			desabilitaCampo(form.rotaFinal);
		} else {
			habilitaCampo(form.rotaInicial);
			habilitaCampo(form.rotaFinal);
		}
	    */
	}
	
	/*
	function desabilitaCampo(campo){
		campo.value = '';
		campo.style.color = '#000000';
		campo.readOnly = true;
		campo.style.backgroundColor = '#EFEFEF';
	}
	
	function habilitaCampo(campo){
		campo.style.color = '#000000';
		campo.readOnly = false;
		campo.style.backgroundColor = '';
	}
	*/
	function listarLeiturista(){
		 var form = document.forms[0];
		 form.action = 'exibirGerarRelatorioImoveisComLeiturasAction.do';
	  	 form.submit();
	
	}
</script>

</head>

<body leftmargin="5" topmargin="5" onload="desabilitaIntervaloDiferente();desabilitaIntervaloDiferenteSetor();">

<html:form action="/gerarRelatorioImoveisComLeiturasAction.do"
	name="GerarRelatorioImoveisComLeiturasActionForm"
	type="gcom.gui.relatorio.cadastro.GerarRelatorioImoveisComLeiturasActionForm"
	method="post">

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
			<td width="600" valign="top" class="centercoltext">
		        <table height="100%">
			        <tr>
			          <td></td>
			        </tr>
		      	</table>

			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Gerar Relat&oacute;rio  de Im&oacute;veis Com Leituras</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Para gerar o relat&oacute;rio, informe os dados abaixo:</td>
				</tr>

				<tr>
					<td>
						<strong>Tipo do Relat&oacute;rio:<font color="#FF0000">*</font></strong>
					</td>
					<td colspan="2">
						<strong> 
							<html:select property="opcaoTipoRelatorio" style="width: 420px;">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>							
								<html:option value="1">Quantitativo de imóveis com leituras através da WEB</html:option>
								<html:option value="2">Quantitativo de imóveis sem leituras através da ISC e WEB</html:option>
								<html:option value="3">Quantitativo de imóveis que estão na rota mas não foram recebidos através da ISC</html:option>
								<html:option value="4">Relação dos imóveis com leituras não recebidas através da ISC</html:option>
								<html:option value="5">Relação dos imóveis não medidos que não estão na rota de ISC</html:option>
								<html:option value="6">Relação dos imóveis medidos que não estão na rota de ISC</html:option>
								<html:option value="7">Quantitativo de imóveis com leituras (enviados, recebidos, medidos e não medidos)</html:option>
							</html:select>												
						</strong>
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>Mês e Ano de Referência:<font color="#FF0000">*</font></strong>
					</td>
					<td colspan="2">
						<html:text property="mesAnoReferencia" size="7"	maxlength="7" onkeyup="javascript:mascaraAnoMes(this,event);" /> mm/aaaa
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>Grupo de Faturamento:<font color="#FF0000">*</font></strong>
					</td>
					<td colspan="2">
						<html:select property="grupoFaturamento">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoFaturamentoGrupo" labelProperty="descricao" property="id" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td><strong>Empresa: </strong></td>
					<td>
						<html:select property="empresa" style="width: 330px;" onchange="javascript:listarLeiturista()">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoEmpresa" labelProperty="descricao" property="id" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td><strong>Leiturista: </strong></td>
					<td align="left" colspan="2">
						<html:select property="leiturista" style="width: 330px;">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoLeiturista" labelProperty="descricao" property="id" />
						</html:select>
					</td>
				</tr>
				
				<tr>
             		<td height="10" colspan="3"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"></div>
               		</td>
           		</tr>

				<tr>
					<td><strong>Localidade Inicial:</strong></td>
					<td>
						<html:text 
							maxlength="3" 
							property="localidadeInicial" 
							size="3"
							onblur="javascript:replicarLocalidade();desabilitaIntervaloDiferente();" 
							onkeyup="javascript:limparOrigem(1);desabilitaIntervaloDiferente();"
							onkeypress="javascript:limparOrigem(1);validaEnterComMensagem(event, 'exibirGerarRelatorioImoveisComLeiturasAction.do?objetoConsulta=1','localidadeInicial','Localidade Inicial');return isCampoNumerico(event);"/>
							
						<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '',document.forms[0].localidadeInicial);limparOrigem(1);">
							<img width="23" 
								height="21" 
								border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Localidade" /></a>

						<logic:present name="localidadeInicialEncontrada" scope="request">
							<html:text property="nomeLocalidadeInicial" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="localidadeInicialEncontrada" scope="request">
							<html:text property="nomeLocalidadeInicial" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						
						<a href="javascript:limparBorrachaOrigem(1);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Setor Comercial Inicial:</strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							property="setorComercialInicial" 
							size="3"
							onblur="javascript:replicarSetorComercial();desabilitaIntervaloDiferenteSetor();"
							onkeyup="limparOrigem(2);desabilitaIntervaloDiferenteSetor();"
							onkeypress="javascript:limparOrigem(2);validaEnterComMensagem(event, 'exibirGerarRelatorioImoveisComLeiturasAction.do?objetoConsulta=2','setorComercialInicial','Setor Comercial Inicial');return isCampoNumerico(event);"/>
							
						<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem', 'idLocalidade', document.forms[0].localidadeInicial.value , 275, 480, 'Informe Localidade Inicial',document.forms[0].setorComercialInicial);
						         limparOrigem(2);">
							<img width="23" 
								height="21" 
								border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Setor Comercial" /></a>
								

						<logic:present name="setorComercialInicialEncontrado" scope="request">
							<html:text property="nomeSetorComercialInicial" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="setorComercialInicialEncontrado" scope="request">
							<html:text property="nomeSetorComercialInicial" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
						
						<a href="javascript:limparBorrachaOrigem(2);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Rota Inicial:</strong></td>
					
					<td>
						
						<html:text maxlength="4" 
							property="rotaInicial"
							onblur="javascript:replicarRota();"
							onkeypress="return isCampoNumerico(event);"
							size="4"/>
					</td>
				</tr>
				
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>

				<tr>
					<td><strong>Localidade Final:</strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							property="localidadeFinal" 
							size="3"
							onblur="javascript:desabilitaIntervaloDiferente();"
							onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioImoveisComLeiturasAction.do?objetoConsulta=3','localidadeFinal','Localidade Final');return isCampoNumerico(event);"
							onkeyup="javascript:desabilitaIntervaloDiferente();"/>
						<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', null, null, 275, 480, '',document.forms[0].localidadeFinal);limparDestino(1);">
							<img width="23" 
								height="21" 
								border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Localidade" /></a>
								 

						<logic:present name="localidadeFinalEncontrada" scope="request">
							<html:text property="nomeLocalidadeFinal" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="localidadeFinalEncontrada" scope="request">
							<html:text property="nomeLocalidadeFinal" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						
						<a href="javascript:limparBorrachaDestino(1);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Setor Comercial Final:</strong></td>
					
					<td>
						
						<html:text maxlength="3" property="setorComercialFinal"
							size="3"
							onblur="javascript:desabilitaIntervaloDiferenteSetor();"
							onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioImoveisComLeiturasAction.do?objetoConsulta=4','setorComercialFinal','Setor Comercial Final');return isCampoNumerico(event);"
							onkeyup="desabilitaIntervaloDiferenteSetor();"/>
								
						<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialDestino', 'idLocalidade', document.forms[0].localidadeFinal.value, 275, 480, 'Informe Localidade Final',document.forms[0].setorComercialFinal);
							        limparDestino(2);">
							<img width="23" 
								height="21" 
								border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Setor Comercial" /></a>
								

						<logic:present name="setorComercialFinalEncontrado" scope="request">
							<html:text property="nomeSetorComercialFinal" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="setorComercialFinalEncontrado" scope="request">
							<html:text property="nomeSetorComercialFinal" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
						
						<a href="javascript:limparBorrachaDestino(2);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Rota Final:</strong></td>
					
					<td>
						<html:text maxlength="4" 
							property="rotaFinal"
							onkeypress="return isCampoNumerico(event);" 
							size="4"/>
					</td>
				</tr>

				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left" colspan="2"><strong><font color="#FF0000">*</font></strong>

					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
								
				<tr>
					<td height="24" >
			          	<input type="button" 
			          		class="bottonRightCol" 
			          		value="Limpar" 
			          		onclick="javascript:limpar();"/>
			          	<font color="#FF0000"> <input type="button"
							name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</font>
					</td>
				
					<td align="right">
						<input type="button" 
							name="Button" 
							class="bottonRightCol" 
							value="Gerar" 
							onClick="javascript:validarForm()" />
					</td>
					
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
<%@ include file="/jsp/util/rodape.jsp"%>	
</body>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioImoveisAtivosNaoMedidosAction.do" />

</html:form>
</html:html>