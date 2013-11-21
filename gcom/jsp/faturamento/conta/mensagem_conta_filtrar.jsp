<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarMensagemContaActionForm" />

<script language="JavaScript">
 
 
function validarGrupoFaturamento(visulizar){

	var form = document.forms[0];
	
		var ok = true;
		if(form.gerenciaRegional.value != ""){
			ok = false;
		}
		if(form.localidade.value != ""){
			ok = false;
		}

		if(ok == true){
			if(form.grupoFaturamento.value != ""){
				form.gerenciaRegional.value = "";	
				form.gerenciaRegional.disabled = true;
		    	form.localidade.value = "";
		    	form.localidade.readOnly = true;
				form.setorComercial.value = "";		        
		        form.setorComercial.readOnly = true;
		        form.quadra.value = "";
		        form.quadra.readOnly = true;
			}else{
				form.gerenciaRegional.disabled = false;
		    	form.localidade.readOnly = false;
		        form.setorComercial.readOnly = false;
		        form.quadra.readOnly = false;
			}
		}else{
			if(visulizar == 1){
				alert("Gerência Regional/Localidade/Setor Comercial");
				form.grupoFaturamento.value = "";
			}
		}
}

  function desabilitarGrupoFaturamento(){
    var form = document.forms[0];
    if (form.gerenciaRegional.value != ""){
      form.grupoFaturamento.value = "";
      form.grupoFaturamento.disabled = true;
      form.localidade.value = "";
      form.localidade.readOnly = true;
      form.setorComercial.value = "";
	  form.setorComercial.readOnly = true;
	  form.quadra.value = "";
	  form.quadra.readOnly = true;
    }else{
      form.grupoFaturamento.disabled = false;
      form.localidade.readOnly = false;
	  form.setorComercial.readOnly = false;
	  form.quadra.readOnly = false;
    }
  }
  
  function desabilitarGrupoFaturamento2(){
    var form = document.forms[0];
    if (form.localidade.value != "" || form.localidadeDescricao.value != "" ){
      form.grupoFaturamento.value = "";
 	  form.grupoFaturamentoHidden.value = form.grupoFaturamento.value;
      form.grupoFaturamento.disabled = true;
      form.gerenciaRegional.value = "";
      form.gerenciaRegionalHidden.value = form.gerenciaRegional.value;
      form.gerenciaRegional.disabled = true;
    }else{
      form.grupoFaturamento.disabled = false;
      form.gerenciaRegional.disabled = false;
    }
  }
  
  function verificaPreenchimentoLocalidade(){
    var form = document.forms[0];
    if (form.localidadeDescricao.value == ""){
      form.grupoFaturamento.disabled = false;
      form.gerenciaRegional.disabled = false;
    }else{
      fomr.grupoFaturamento.value = "";
      form.grupoFaturamento.disabled = true;
      form.gerenciaRegional.value = "";
      form.gerenciaRegional.disabled = true;
    }
  
  }
  
  function habilitarGrupoFaturamento(){
    var form = document.forms[0];
    form.grupoFaturamento.disabled = false;
  }

 function chamaPopupLocalidade(){
 	var form = document.FiltrarMensagemContaActionForm;
 	
 	if ((form.gerenciaRegional.disabled == true || form.gerenciaRegional.value == "") && (form.grupoFaturamento.disabled == true || form.grupoFaturamento.value == "")){
 		abrirPopup('exibirPesquisarLocalidadeAction.do');
 		limpaLocalidadeSemApagarCodigo();
 	}
 }
 
 function chamaPopupSetor(){
 	var form = document.FiltrarMensagemContaActionForm;
 	
 	if ((form.gerenciaRegional.disabled == true || form.gerenciaRegional.value == "") && (form.grupoFaturamento.disabled == true || form.grupoFaturamento.value == "")){
 		abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].localidade.value+'&tipo=SetorComercial',document.forms[0].localidade.value,'Localidade', 400, 800);
 	}
 }
 
 function validaForm(){
	var form = document.FiltrarMensagemContaActionForm;
	if (verificaAnoMesReferenciaFaturamento(form.referenciaFaturamento)){
		if (validateFiltrarMensagemContaActionForm(form)){
			form.grupoFaturamentoHidden.value = form.grupoFaturamento.value;
			form.gerenciaRegionalHidden.value = form.gerenciaRegional.value;
			submeterFormPadrao(form);
		}
	}
 }
 
  function verificaAnoMesReferenciaFaturamento(mydata) {
	
	var situacao = true;
	
	if (mydata.value.length == 7) {
	
		dia = 1;
    	mes = mydata.value.substring(0,2); 
    	ano = mydata.value.substring(3,7); 

    	if ((!isNaN(mes) || !isNaN(ano)) && (mes.indexOf('.') == -1 && mes.indexOf(',') == -1 && mes.indexOf('/') == -1) &&
			(ano.indexOf('.') == -1 && ano.indexOf(',') == -1 && ano.indexOf('/') == -1)) {
    	
    		// verifica se o mes e valido 
	    	if ((mes * 1) >= 1 && (mes * 1) <= 12 ) { 
	        
	        	// verifica se o ano e valido
	        	if ((ano * 1) != 0 && (ano * 1) >= 1980) {
	        	
	        		// verifica se e ano bissexto 
	    			if ((mes * 1) == 2 && ((dia * 1) < 1 || (dia * 1) > 29 
	    				|| ((dia * 1) > 28 && (((ano * 1) / 4) != (ano * 1) / 4)))) { 
	       				situacao = false; 
	    			}
	        	}
	        	else{
	        		situacao = false;
	        	}
	    	}
	    	else{
	    		situacao = false;
	    	} 
		}
		else{
			situacao = false;
		}
    }
    else if (mydata.value.length > 0){
    	situacao = false;
    }
    
    if (!situacao) { 
	   alert("Referência do Faturamento inválida.");
	   mydata.value = "";
	   mydata.focus(); 
	}
	
	return situacao;
}
 
 
 function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	
	var form = document.FiltrarMensagemContaActionForm;
	
	if (tipoConsulta == 'localidade') {
      limpaLocalidade()
      form.localidade.value = codigoRegistro;
      form.localidadeDescricao.value = descricaoRegistro;
      form.localidadeDescricao.style.color = "#000000";
      form.setorComercial.focus();
      form.grupoFaturamento.value = "";
      form.grupoFaturamento.disabled = true;
      form.gerenciaRegional.value = "";
      form.gerenciaRegional.disabled = true;

    }
    
    if (tipoConsulta == 'setorComercial') {
      limpaSetorComercial()
      form.setorComercial.value = codigoRegistro;
      form.setorComercialDescricao.value = descricaoRegistro;
      form.setorComercialDescricao.style.color = "#000000";
      form.grupoFaturamento.value = "";
      form.grupoFaturamento.disabled = true;
      form.gerenciaRegional.value = "";
      form.gerenciaRegional.disabled = true;

    }
}
 
 function limpaLocalidade(){
 	var form = document.FiltrarMensagemContaActionForm;
 	
 	form.localidade.value = "";
 	form.localidadeDescricao.value = "";
 	form.setorComercial.value = "";
 	form.setorComercialDescricao.value = "";
 	form.quadra.value = "";
	
	if (form.gerenciaRegional.value == ""){
	  form.grupoFaturamento.disabled = false;
	  form.gerenciaRegional.disabled = false;
	}
	
 	form.localidade.focus();
 
 }
 
  function limpaSetorComercial(){
 	var form = document.FiltrarMensagemContaActionForm;

 	form.setorComercial.value = "";
 	form.setorComercialDescricao.value = "";
	form.quadra.value = "";

 	form.setorComercial.focus();
 
 }

  function limpaSetorComercialSemApagarCodigo(){
 	var form = document.FiltrarMensagemContaActionForm;

 	form.setorComercialDescricao.value = "";
	form.quadra.value = "";
 
 }

 function limpaLocalidadeSemApagarCodigo(){
 	var form = document.FiltrarMensagemContaActionForm;

 	form.localidadeDescricao.value = "";
 	form.setorComercial.value = "";
 	form.setorComercialDescricao.value = "";
 	form.quadra.value = "";
 
 }
 
  function barraDigitacaoSemLocalidade(){
 	var form = document.FiltrarMensagemContaActionForm;
 	
 	if (form.localidade.value == "" || form.localidadeDescricao.value == "LOCALIDADE INEXISTENTE") {
 		alert ('Informe Localidade');
		form.setorComercial.value = "";
		form.setorComercialDescricao.value = "";
 		form.localidadeDescricao.value = "";
 		form.localidade.value = "";
 		form.localidade.focus();
 		form.localidade.value = "";
 		form.quadra.value = "";
 		
 	}
 }
 
	 function barraDigitacaoSemSetorComercial(){
	 	var form = document.FiltrarMensagemContaActionForm;
		if (form.localidade.value == "" || form.localidadeDescricao.value == "LOCALIDADE INEXISTENTE") {
	 		if (form.setorComercial.value == "" || form.setorComercialDescricao.value == "SETOR COMERCIAL INEXISTENTE") {
		 		alert('Informe Localidade e Setor Comercial');
		 		form.localidade.focus();
	 		}
	 	} else if (form.setorComercial.value == "" || form.setorComercialDescricao.value == "SETOR COMERCIAL INEXISTENTE") {
	 			alert('Informe Setor Comercial');	
	 			form.localidade.focus();
	 		
	 	}
	 }
 

</script>
</head>
<logic:present name="identificadorPesquisa" scope="request">
	<body leftmargin="5" topmargin="5" onload="javascript:desabilitarGrupoFaturamento2();">
</logic:present>
<logic:notPresent name="identificadorPesquisa" scope="request">
	<body leftmargin="5" topmargin="5"
		onload="javascript:setarFoco('referenciaFaturamento'); desabilitarGrupoFaturamento2();">
</logic:notPresent>
<html:form action="/filtrarMensagemContaAction.do"
	name="FiltrarMensagemContaActionForm"
	type="gcom.gui.faturamento.conta.FiltrarMensagemContaActionForm"
	method="post" onsubmit="return validateFiltrarMensagemContaActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
		<html:hidden property="grupoFaturamentoHidden" />
		<html:hidden property="gerenciaRegionalHidden" />		
	
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
			<td width="615" valign="top" class="centercoltext">
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
					<td class="parabg">Filtrar Mensagem da Conta</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table border="0" width="100%">
				<tr>
					<td colspan="2">Para manter a(s) mensagem(ns) da conta, informe os
					dados abaixo:</td>
					<td width="84">
					<input name="atualizar" type="checkbox"
						checked="checked" value="1"> <strong>Atualizar</strong></td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoContaMensagemFiltrar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>	
				</tr>
				<tr>
					<td><strong>Referência do Faturamento:</strong></td>
					<td align="right">
					<div align="left"><html:text tabindex="0" property="referenciaFaturamento"
						size="7" maxlength="7" onkeyup="mascaraAnoMes(this, event);" />
					mm/aaaa</div>
					</td>
				</tr>
								<tr>
					<td><strong>Mensagem da Conta:<font color="#ff0000"></font></strong></td>

					<td align="right">
					<div align="left"><html:text property="mensagemConta01"
						maxlength="100" size="40" /></div>
					</td>
				</tr>
				<tr>
					<td><strong>Grupo de Faturamento:</strong></td>
					<td align="right">
					<div align="left"><strong> <html:select property="grupoFaturamento"
						style="width: 150px;" onchange="validarGrupoFaturamento(1);">
						<logic:present name="colecaoFaturamentoGrupo">
							<html:option value="">&nbsp;</html:option>
							<html:options collection="colecaoFaturamentoGrupo"
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select> </strong></div>
					</td>
				</tr>
				<tr>
					<td><strong>Gerência Regional:</strong></td>
					<td align="right">
					<div align="left"><strong> <html:select property="gerenciaRegional"
						style="width: 230px;"
						onchange="desabilitarGrupoFaturamento();">
						<logic:present name="colecaoGerenciaRegional">
							<html:option value="">&nbsp;</html:option>
							<logic:iterate name="colecaoGerenciaRegional" id="gerenciaRegional" >
							<!--<html:options collection="colecaoGerenciaRegional"
								labelProperty="nome" property="id" />
							-->
							<option value="<bean:write property="id" name="gerenciaRegional"/>">
							<bean:write property="nomeAbreviado" name="gerenciaRegional"/> - <bean:write property="nome" name="gerenciaRegional"/>
							</option>
							</logic:iterate>
						</logic:present>
					</html:select> </strong></div>
					</td>

				</tr>
				<tr>
					<td><strong>Localidade:</strong></td>
					<td align="right">
					<div align="left"><html:text property="localidade" size="3"
						maxlength="3" 
						onchange="javascript:desabilitarGrupoFaturamento2();"
						onkeydown="limpaLocalidadeSemApagarCodigo();"
						onkeypress="validaEnter(event, 'exibirFiltrarMensagemContaAction.do', 'localidade');" />
					<a href="javascript:chamaPopupLocalidade();"> <img
						src="imagens/pesquisa.gif" style="" height="21" width="23"
						border="0"></a> <logic:present name="localidadeInexistente"
						scope="request">
						<html:text property="localidadeDescricao" size="30" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000;" />
					</logic:present> <logic:notPresent name="localidadeInexistente"
						scope="request">
						<html:text property="localidadeDescricao" size="30" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000;" />
					</logic:notPresent> <a href="javascript:limpaLocalidade();"><img src="imagens/limparcampo.gif" border="0" height="21"
						width="23" ></a></div>
					</td>
				</tr>
				<tr>
					<td><strong>Setor Comercial:</strong></td>

					<td align="right">
					<div align="left"><html:text property="setorComercial" size="3"
						maxlength="3" onkeydown="limpaSetorComercialSemApagarCodigo();" 
						onchange="barraDigitacaoSemLocalidade();"
						onkeypress="validaEnter(event, 'exibirFiltrarMensagemContaAction.do', 'setorComercial');" />
					<a href="javascript:chamaPopupSetor();"> <img
						src="imagens/pesquisa.gif" style="" border="0" onclick=""
						height="21" width="23"></a> <logic:present
						name="setorComercialInexistente" scope="request">
						<html:text property="setorComercialDescricao" size="30"
							maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000;" />
					</logic:present> <logic:notPresent name="setorComercialInexistente"
						scope="request">
						<html:text property="setorComercialDescricao" size="30"
							maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000;" />
					</logic:notPresent>
					<a href="javascript:limpaSetorComercial();"><img src="imagens/limparcampo.gif" border="0" height="21"
						width="23">
					</a>
					</div>
					</td>
				</tr>
				
				<tr>
					<td><strong>Quadra:</strong></td>
					<td colspan="2" height="24"><html:text maxlength="4"
						property="quadra" tabindex="3" size="3"
						onchange="barraDigitacaoSemSetorComercial();"
						onkeypress="javascript: return validaEnterDependencia(event, 'exibirFiltrarMensagemContaAction.do', this, document.forms[0].localidade.value, 'Setor Comercial');" />
					<logic:present name="codigoQuadraNaoEncontrada" scope="request">
						<span style="color:#ff0000" id="msgQuadra"><bean:write
							scope="request" name="msgQuadra" /></span>
					</logic:present> <logic:notPresent name="codigoQuadraNaoEncontrada"
						scope="request">
					</logic:notPresent></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td align="right">&nbsp;</td>
				</tr>
				<tr>
					<td><strong> <font color="#ff0000"> <input name="Submit22"
						class="bottonRightCol" value="Limpar" type="button"
						onclick="window.location.href='/gsan/exibirFiltrarMensagemContaAction.do?menu=sim';">
					</font></strong></td>
					<td align="right"></td>
					<td align="right"><input name="botao" class="bottonRightCol"
						value="Filtrar" onclick="validaForm();"
						type="button"></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<!-- Fim do Corpo - Roberta Costa --></td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
<script language="JavaScript">
<!-- Begin
	verificaPreenchimentoLocalidade();
 -->
</script>
</html:html>
