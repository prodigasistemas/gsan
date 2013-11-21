<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
	<html:javascript staticJavascript="false"  formName="FiltrarFeriadoActionForm" />
	<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<SCRIPT LANGUAGE="JavaScript">
<!--
	
	 // Bloqueia Municipio
	 function bloquearMunicipio(){
		var form = document.FiltrarFeriadoActionForm;
		
		if(form.indicadorTipoFeriado[0].checked){
			form.idMunicipio.value="";
			form.descricaoMunicipio.value = "";
			form.idMunicipio.disabled = true;
	  		form.descricaoMunicipio.disabled = true;
		} else {
			form.idMunicipio.disabled = false;
	  		form.descricaoMunicipio.disabled = false;
		}
	}
	
	
	//Recupera Dados Popup
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
	    if (tipoConsulta == 'municipio') {
	    	form.idMunicipio.value = codigoRegistro;
	    	form.descricaoMunicipio.value = descricaoRegistro;
	    	form.descricaoMunicipio.style.color = "#000000";
	      	form.action='exibirFiltrarFeriadoAction.do';
	      	form.submit();
	    }
	}	
	
	// Chama Popup
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
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisaMunicipio=" + tipo, altura, largura);
				}
			}
		}
	}
	
	
	function limparMunicipio(){
		var form = document.forms[0];
		form.idMunicipio.value = "";
		form.descricaoMunicipio.value = "";
	} 

	function verificarChecado(valor){
		form = document.forms[0];
		if(valor == "1"){
		 	form.indicadorAtualizar.checked = true;
		 }else{
		 	form.indicadorAtualizar.checked = false;
		}
	}
	
	 function validarForm() {
      var form = document.forms[0];
      form.action = 'filtrarFeriadoAction.do?indicadorAtualizar=' + form.indicadorAtualizar.value;
      
	  if(validateFiltrarFeriadoActionForm(form)){	
		  if ( form.idMunicipio.disabled == false && form.indicadorTipoFeriado[1].checked 
				&& (form.idMunicipio.value == null || form.idMunicipio.value == "")){
		  	alert ('Informe Município');
		  } else if (comparaData(form.dataFeriadoInicio.value,'>',form.dataFeriadoFim.value)){
		  	alert ('Data Inicial do Feriado deve ser anterior ou igual à Data Final do Feriado');
		  } else {
		  	submeterFormPadrao(form); 
		  }
		}
   	  }  		  
   	  
   	  
	function espelharFeriados() {
      var form = document.forms[0];
      form.action = 'exibirEspelharFeriadosAction.do';
      form.submit();    
	}

	function verificarValorAtualizar(){
		var form = document.FiltrarFeriadoActionForm;
       	
       	if (form.indicadorAtualizar.checked == true) {
       		form.indicadorAtualizar.value = '1';
       	} else {
       		form.indicadorAtualizar.value = '';
       	}
       	
	}
	
	function setaFocus(){
		var form = document.FiltrarFeriadoActionForm;
		form.idMunicipio.focus();
	}
	
	
//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5" onload="verificarChecado('${indicadorAtualizar}');setaFocus();">

<html:form action="/filtrarFeriadoAction"
	name="FiltrarFeriadoActionForm"
	type="gcom.gui.cadastro.sistemaparametro.FiltrarFeriadoActionForm"
	method="post"
	onsubmit="return validateFiltrarFeriadoActionForm(this);">


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
					<td class="parabg">Filtrar Feriado Nacional ou Municipal</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">

				<tr>
					<td width="100%" colspan="3">
					<table width="100%">
						<tr>
							<td width="80%">Para filtrar o(s) feriado(s), informe os dados
							abaixo:</td>
							<td align="right"><input type="checkbox"
								name="indicadorAtualizar" value="1"
								onclick="javascript:verificarValorAtualizar();" /><strong>Atualizar</strong>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td width="30%" class="style3"><strong>Tipo do Feriado:</strong></td>
					<td width="70%" colspan="2" >  
						<html:radio  property="indicadorTipoFeriado" onkeyup="javascript:bloquearMunicipio();" 
									onclick="javascript:bloquearMunicipio();" value="1" tabindex="1" /><strong>Nacional
						<html:radio  property="indicadorTipoFeriado" onkeyup="javascript:bloquearMunicipio();" 
									onclick="javascript:bloquearMunicipio();"value="2" tabindex="2" />Municipal
						<html:radio  property="indicadorTipoFeriado" onkeyup="javascript:bloquearMunicipio();" 
									onclick="javascript:bloquearMunicipio();" value="3" tabindex="3" /><strong>Todos </strong>
					</td>
				</tr>
				
				
				<tr>
					<td width="30%" class="style3"><strong>Município:</strong></td>
					<td width="70%" colspan="2">
					
						<html:text property="idMunicipio" size="4" maxlength="4" tabindex="4"
							onkeypress="validaEnterComMensagem(event,'exibirFiltrarFeriadoAction.do','idMunicipio','Município');" />
					
						<a href="javascript:chamarPopup('exibirPesquisarMunicipioAction.do', 'municipio', null, null, 485, 370, '',document.forms[0].idMunicipio);">
							<img src="/gsan/imagens/pesquisa.gif" alt="Pesquisar Municipio" border="0" height="21" width="23"></a> 
						
						
						<logic:present name="municipioEncontrado">
						
							<logic:equal name="municipioEncontrado" value="exception">
								<html:text property="descricaoMunicipio" size="40" maxlength="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="municipioEncontrado" value="exception">
								<html:text property="descricaoMunicipio" size="40" maxlength="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						
						</logic:present> 
					
						<logic:notPresent name="municipioEncontrado">
							<logic:empty name="FiltrarFeriadoActionForm" property="idMunicipio">
								<html:text property="descricaoMunicipio" size="40" maxlength="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="FiltrarFeriadoActionForm" property="idMunicipio">
								<html:text property="descricaoMunicipio" size="40" maxlength="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent> 
					
						<a 	href="javascript:limparMunicipio()"> <img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" /> </a>
					</td>
				</tr>
				
				<tr> 
	           		<td width="30%" class="style3"><strong>Data do Feriado:</strong></td>
					<td width="70%" colspan="2">

						<html:text	property="dataFeriadoInicio" size="10" maxlength="10" tabindex="5" 
						onkeyup="mascaraData(this, event);replicarCampo(document.forms[0].dataFeriadoFim,this);" 
						onfocus="replicarCampo(document.forms[0].dataFeriadoFim,this);" /> 
						<a href="javascript:abrirCalendarioReplicando('FiltrarFeriadoActionForm', 'dataFeriadoInicio', 'dataFeriadoFim');">
						<img border="0"	src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle"
						alt="Exibir Calendário" /></a>
						a
						<html:text property="dataFeriadoFim" size="10" maxlength="10" tabindex="6" onkeyup="mascaraData(this, event);" /> 
		            	<a href="javascript:abrirCalendario('FiltrarFeriadoActionForm', 'dataFeriadoFim')">
						<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle"
						alt="Exibir Calendário" /></a> dd/mm/aaaa
					</td>
	           	</tr>
			
				<tr>
				   <td width="30%" class="style3"><strong>Descrição do Feriado:</strong></td>
				   <td width="70%" colspan="2"><strong><b><span class="style2"> 
				  		<html:text property="descricaoFeriado" size="20" maxlength="20" tabindex="7"/> </span></b></strong></td>
				</tr>
				
				<tr>
					<td>
						<input name="Button" type="button" class="bottonRightCol" value="Limpar" align="left"
						onclick="window.location.href='/gsan/exibirFiltrarFeriadoAction.do?menu=sim'" tabindex="8">
					</td>
					<td align="right" colspan="2">
						<input name="Button" type="button" class="bottonRightCol" value="Filtrar" align="right" 
						onclick="javascript:validarForm();" tabindex="9">
					</td>
					
					<td align="right" colspan="2">
						<input name="Button" type="button" class="bottonRightCol" value="Espelhar feriados" align="right" 
						onclick="javascript:espelharFeriados();" tabindex="10">
					</td>
					
					<td align="right"></td>
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

