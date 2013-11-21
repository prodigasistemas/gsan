<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

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
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
		<html:javascript staticJavascript="false"  formName="AssociarConjuntoRotasCriterioCobrancaActionForm"/>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarSelecaoRotas(form){
		
		var retorno = false;
		
		var valorGrupoCobranca = form.idGrupoCobranca.options[form.idGrupoCobranca.options.selectedIndex].value;
		
		var valorGerenciaRegional = form.idGerenciaRegional.options[form.idGerenciaRegional.options.selectedIndex].value;
    	var valorUnidadeNegocio = form.idUnidadeNegocio.options[form.idUnidadeNegocio.options.selectedIndex].value;
    	var valorLocalidadeInicial = form.idLocalidadeInicial.value;
		var valorLocalidadeFinal = form.idLocalidadeFinal.value;
    	var valorSetorComercialInicial = form.codigoSetorComercialInicial.value;
    	var valorSetorComercialFinal = form.codigoSetorComercialFinal.value;
    	var valorRotaInicial = form.numeroRotaInicial.value;
		var valorRotaFinal = form.numeroRotaFinal.value;
		var idAcaoCobranca = form.idAcaoCobranca.value;
		
		if (idAcaoCobranca == -1){    		
    		alert("Informe a ação de cobrança");
    		form.idAcaoCobranca.focus();
    	} else {
	    	retorno = true;
    	}
		
/*    	if (valorGrupoCobranca != "-1" || valorGerenciaRegional != "-1" || valorUnidadeNegocio != "-1" 
    		|| valorLocalidadeInicial.length > 0){
    		
    		retorno = true;
    	}
    	else{
    		alert("Informe Grupo de Cobrança OU Dados de Localização Geográfica");
    		form.idGrupoCobranca.focus();
    	}
*/    	
    	return retorno; 
	}
	
	function validarCriterioCobranca(form){
		
		var retorno = true;
		
		var valorCriterioCobranca = form.idCriterioCobranca.value;
		
		if (valorCriterioCobranca.length < 1){
    		alert("Informe Critério de Cobrança");
    		form.idCriterioCobranca.focus();
    	
    		retorno = false;
    	}
		
		return retorno;
	}
	
	function selecionar(){
		var form = document.forms[0];
		if(validateAssociarConjuntoRotasCriterioCobrancaActionForm(form) && validarSelecaoRotas(form)){
			form.action = "selecionarAssociarConjuntoRotasCriterioCobrancaAction.do";
			form.submit();
		}
	}
	
	function adicionar(){
		var form = document.forms[0];
		if(validateAssociarConjuntoRotasCriterioCobrancaActionForm(form) && validarCriterioCobranca(form) && validarSelecaoRotas(form)){
			form.action = "associarConjuntoRotasCriterioCobrancaAction.do";
			form.submit();
		}
	}
	
	function remover(){
		var form = document.forms[0];
		if(validateAssociarConjuntoRotasCriterioCobrancaActionForm(form) && validarSelecaoRotas(form)){
			form.action = "desassociarConjuntoRotasCriterioCobrancaAction.do";
			form.submit();
		}
	}

	function limparLocalidadeInicial(form) {
		form.idLocalidadeInicial.value = "";
		form.idLocalidadeFinal.value = "";
    	form.descricaoLocalidadeInicial.value = "";
    	form.codigoSetorComercialInicial.value = "";
    	form.descricaoSetorComercialInicial.value = "";
    	form.descricaoLocalidadeFinal.value = "";
    	form.codigoSetorComercialFinal.value = "";
    	form.descricaoSetorComercialFinal.value = "";
    	form.numeroRotaInicial.value = "-1";
		form.numeroRotaFinal.value = "-1"
		
		reiniciarListBox(form.numeroRotaInicial);
		reiniciarListBox(form.numeroRotaFinal);
		
		limparQtdRotas();
		
		habilitacaoGrupoCobranca();
    	habilitacaoLocalizacaoGeografica();
	}
	
	function limparDescLocalidadeInicial(form){
		form.idLocalidadeFinal.value = "";
    	form.descricaoLocalidadeInicial.value = "";
    	form.codigoSetorComercialInicial.value = "";
    	form.descricaoSetorComercialInicial.value = "";
    	form.descricaoLocalidadeFinal.value = "";
    	form.codigoSetorComercialFinal.value = "";
    	form.descricaoSetorComercialFinal.value = "";
    	form.numeroRotaInicial.value = "-1";
		form.numeroRotaFinal.value = "-1"
		
		reiniciarListBox(form.numeroRotaInicial);
		reiniciarListBox(form.numeroRotaFinal);
		
		limparQtdRotas();
	}
	
	function limparSetorComercial(form) {
    	form.codigoSetorComercialInicial.value = "";
    	form.descricaoSetorComercialInicial.value = "";
    	form.codigoSetorComercialFinal.value = "";
    	form.descricaoSetorComercialFinal.value = "";
    	form.numeroRotaInicial.value = "-1";
		form.numeroRotaFinal.value = "-1"
		
		reiniciarListBox(form.numeroRotaInicial);
		reiniciarListBox(form.numeroRotaFinal);
    	
    	habilitacaoGrupoCobranca();
    	habilitacaoLocalizacaoGeografica();
    	
    	limparQtdRotas();
	}
	
	function limparLocalidadeFinal(form){
		form.idLocalidadeFinal.value = "";
		form.descricaoLocalidadeFinal.value = "";
		form.codigoSetorComercialInicial.value = "";
    	form.descricaoSetorComercialInicial.value = "";
    	form.codigoSetorComercialFinal.value = "";
    	form.descricaoSetorComercialFinal.value = "";
    	form.numeroRotaInicial.value = "-1";
		form.numeroRotaFinal.value = "-1"
		
		reiniciarListBox(form.numeroRotaInicial);
		reiniciarListBox(form.numeroRotaFinal);
		
		limparQtdRotas();
		
		habilitacaoGrupoCobranca();
    	habilitacaoLocalizacaoGeografica();
	}

	function limparDescLocalidadeFinal(form) {
    	form.codigoSetorComercialInicial.value = "";
    	form.descricaoSetorComercialInicial.value = "";
    	form.descricaoLocalidadeFinal.value = "";
    	form.codigoSetorComercialFinal.value = "";
    	form.descricaoSetorComercialFinal.value = "";
    	form.numeroRotaInicial.value = "-1";
		form.numeroRotaFinal.value = "-1"
		
		reiniciarListBox(form.numeroRotaInicial);
		reiniciarListBox(form.numeroRotaFinal);
		
		limparQtdRotas();
	}
	
	function limparDescSetorComercial(form) {
    	
    	form.descricaoSetorComercialInicial.value = "";
    	
    	limparSetorComercialFinal(form);
    	
    	limparQtdRotas();
	}
	
	function limparDescSetorComercialFinal(form) {
    	form.descricaoSetorComercialFinal.value = "";
    	form.numeroRotaInicial.value = "-1";
		form.numeroRotaFinal.value = "-1"
		
		reiniciarListBox(form.numeroRotaInicial);
		reiniciarListBox(form.numeroRotaFinal);
		
		limparQtdRotas();
	}
	
	function limparSetorComercialFinal(form) {
    	form.codigoSetorComercialFinal.value = "";
    	form.descricaoSetorComercialFinal.value = "";
    	form.numeroRotaInicial.value = "-1";
		form.numeroRotaFinal.value = "-1"
		
		reiniciarListBox(form.numeroRotaInicial);
		reiniciarListBox(form.numeroRotaFinal);
		
		limparQtdRotas();
		
		habilitacaoGrupoCobranca();
    	habilitacaoLocalizacaoGeografica();
	}
	
	function limparDescCriterio(form){
		form.descricaoCriterioCobranca.value = "";
		
	}
	
	function limparCriterio(form){
		form.idCriterioCobranca.value = "";
		form.descricaoCriterioCobranca.value = "";
	}
	
 	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	    var form = document.forms[0];
	
	    if (tipoConsulta == 'localidadeOrigem') {
	      	limparLocalidadeInicial(form);
	      	form.idLocalidadeInicial.value = codigoRegistro;
	    	form.descricaoLocalidadeInicial.value = descricaoRegistro;
	    	form.descricaoLocalidadeInicial.style.color = "#000000";
	      	form.idLocalidadeFinal.focus();
	    }
	    
	     if (tipoConsulta == 'localidadeDestino') {
	      	limparLocalidadeFinal(form);
	      	form.idLocalidadeFinal.value = codigoRegistro;
	    	form.descricaoLocalidadeFinal.value = descricaoRegistro;
	    	form.descricaoLocalidadeFinal.style.color = "#000000";
	      	form.idLocalidadeFinal.focus();
	    }
	
	    if (tipoConsulta == 'criterioCobranca') {
	      limparCriterio(form);
	      form.idCriterioCobranca.value = codigoRegistro;
	      form.descricaoCriterioCobranca.value = descricaoRegistro;
	      form.descricaoCriterioCobranca.style.color = "#000000";
	    }
	    
   }
   
   function habilitarPesquisaLocalidade(form, tipo) {
		if (form.idLocalidadeInicial.disabled == false) 
		{
		    if(tipo == 'origem')
		    {
				chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', 'indicadorUsoTodos', '1', 275, 480, '',form.idLocalidadeInicial.value);
			}
			else if(tipo == 'destino')
		    {
				chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', 'indicadorUsoTodos', '1', 275, 480, '',form.idLocalidadeFinal.value);
			}
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
	
	function recuperarDadosQuatroParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, tipoConsulta) {
        
        var form = document.forms[0];
        
    	if (tipoConsulta == 'setorComercialOrigem') {
	      limparSetorComercial(form);
	      form.codigoSetorComercialInicial.value = codigoRegistro;
	      form.descricaoSetorComercialInicial.value = descricaoRegistro1;
	      form.descricaoSetorComercialInicial.style.color = "#000000"
	    }
	    
	    if (tipoConsulta == 'setorComercialDestino') {
	      limparSetorComercialFinal(form);
	      form.codigoSetorComercialFinal.value = codigoRegistro;
	      form.descricaoSetorComercialFinal.value = descricaoRegistro1;
	      form.descricaoSetorComercialFinal.style.color = "#000000"
	    }  
    }
    
    
    
    function habilitacaoGrupoCobranca(){
    
    	var form = document.forms[0];
    	var valorGrupoCobranca = form.idGrupoCobranca.options[form.idGrupoCobranca.options.selectedIndex].value;
    	
    	if (valorGrupoCobranca != "-1"){
    		
    		form.idGerenciaRegional.value = "-1";
    		form.idUnidadeNegocio.value = "-1";
    		
    		form.idLocalidadeInicial.value = "";
			form.idLocalidadeFinal.value = "";
	    	form.descricaoLocalidadeInicial.value = "";
	    	form.codigoSetorComercialInicial.value = "";
	    	form.descricaoSetorComercialInicial.value = "";
	    	form.descricaoLocalidadeFinal.value = "";
	    	form.codigoSetorComercialFinal.value = "";
	    	form.descricaoSetorComercialFinal.value = "";
	    	form.numeroRotaInicial.value = "-1";
			form.numeroRotaFinal.value = "-1"
			
			reiniciarListBox(form.numeroRotaInicial);
			reiniciarListBox(form.numeroRotaFinal);
		
    		habilitacaoCamposLocalizacaoGeografica(true);
    	}
    	else{
    		habilitacaoCamposLocalizacaoGeografica(false);
    	}
    }
    
    function habilitacaoLocalizacaoGeografica(){
    
    	var form = document.forms[0];
    	
    	var valorGerenciaRegional = form.idGerenciaRegional.options[form.idGerenciaRegional.options.selectedIndex].value;
    	var valorUnidadeNegocio = form.idUnidadeNegocio.options[form.idUnidadeNegocio.options.selectedIndex].value;
    	var valorLocalidadeInicial = form.idLocalidadeInicial.value;
		var valorLocalidadeFinal = form.idLocalidadeFinal.value;
    	var valorSetorComercialInicial = form.codigoSetorComercialInicial.value;
    	var valorSetorComercialFinal = form.codigoSetorComercialFinal.value;
    	var valorRotaInicial = form.numeroRotaInicial.value;
		var valorRotaFinal = form.numeroRotaFinal.value;
		
    	if (valorGerenciaRegional != "-1" || valorUnidadeNegocio != "-1" || valorLocalidadeInicial.length > 0
    		|| valorLocalidadeFinal.length > 0 || valorSetorComercialInicial.length > 0 
    		|| valorSetorComercialFinal.length > 0 || valorRotaInicial != "-1" || valorRotaFinal != "-1"){
    		
    		form.idGrupoCobranca.value = "-1";
	    	form.idGrupoCobranca.disabled = true;
    	}
    	else{
    		form.idGrupoCobranca.disabled = false;
    	}
    	
    }
    
    function habilitacaoCamposLocalizacaoGeografica(boleano){
    	
    	var form = document.forms[0];
    	
    	form.idGerenciaRegional.disabled = boleano;
    	form.idUnidadeNegocio.disabled = boleano;
    	form.idLocalidadeInicial.disabled = boleano;
		form.idLocalidadeFinal.disabled = boleano;
    	form.descricaoLocalidadeInicial.disabled = boleano;
    	form.codigoSetorComercialInicial.disabled = boleano;
    	form.descricaoSetorComercialInicial.disabled = boleano;
    	form.descricaoLocalidadeFinal.disabled = boleano;
    	form.codigoSetorComercialFinal.disabled = boleano;
    	form.descricaoSetorComercialFinal.disabled = boleano;
    	form.numeroRotaInicial.disabled = boleano;
		form.numeroRotaFinal.disabled = boleano;
	
    }
    
    function limparQtdRotas(){
    	
    	var form = document.forms[0];
    	
    	form.qtdRotasSelecionadas.value = "";
    	form.qtdRotasComCriterio.value = "";
    	form.qtdRotasSemCriterio.value = "";
    	
    	form.associar.disabled = true;
    	form.desassociar.disabled = true;
    }
    
    function habilitacaoBotoes(){
    	
    	var form = document.forms[0];
    	
    	var qtdRotasCOM = form.qtdRotasComCriterio.value;
    	var qtdRotasSEM = form.qtdRotasSemCriterio.value;
    	
    	if (qtdRotasCOM.length > 0 && (qtdRotasCOM * 1) > 0){
    		form.desassociar.disabled = false;
    	}
    	else{
    		form.desassociar.disabled = true;
    	}
    	
    	if (qtdRotasSEM.length > 0 && (qtdRotasSEM * 1) > 0){
    		form.associar.disabled = false;
    	}
    	else{
    		form.associar.disabled = true;
    	}
    }

</script>
</head>
<body leftmargin="0" topmargin="0"
onload="setarFoco('${requestScope.nomeCampo}'); habilitacaoGrupoCobranca(); habilitacaoLocalizacaoGeografica();
habilitacaoBotoes();">

<html:form action="/selecionarAssociarConjuntoRotasCriterioCobrancaAction"
	name="AssociarConjuntoRotasCriterioCobrancaActionForm"
	type="gcom.gui.cobranca.AssociarConjuntoRotasCriterioCobrancaActionForm"
	method="post"
	onsubmit="return validateAssociarConjuntoRotasCriterioCobrancaActionForm(this);">


<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="120" valign="top" class="leftcoltext">
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
		<td valign="top" class="centercoltext">
            <table>
              <tr><td></td></tr>
              
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
                <td class="parabg">Associar Conjunto de Rotas a Critério de Cobrança</td>
                <td width="11" valign="top"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
              </tr>
            </table>


			<p>&nbsp;</p>
			<table width="100%" border="0">


				 <tr>
			          <td width="100%" colspan=2>
				          <table width="100%" border="0">
				          	<tr>
				          		<td>Para associar um conjunto de rotas a critério de cobrança, informe os dados abaixo:</td>
				          	</tr>
				          </table>
			          </td>
			     </tr>
				
				<tr> 
                	<td><strong>Ação de Cobrança:</strong></td>
                	<td height="24"><html:select property="idAcaoCobranca" tabindex="1" style="width: 240px;">
                    	<html:option value="-1">&nbsp;</html:option>
                    	<html:options collection="colecaoAcaoCobranca"
							labelProperty="descricaoCobrancaAcao" property="id" />
                    </html:select> <font size="1">&nbsp; </font></td>
              	</tr>
              	
              	<tr>
					<td><strong>Critério de Cobrança:</strong></td>
					<td height="24"><html:text maxlength="3" tabindex="2"
						property="idCriterioCobranca" size="3"
						onkeypress="javascript:limparDescCriterio(document.forms[0]);
						validaEnterComMensagem(event, 'exibirAssociarRotasCriterioCobrancaAction.do?objetoConsulta=1', 'idCriterioCobranca','CriterioCobranca');" />
					<a
						href="javascript:abrirPopup('exibirPesquisarCriterioCobrancaAction.do');">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Criterio Cobranca" /></a> 
						
						<logic:present
							name="idCriterioCobrancaNaoEncontrado">
						<logic:equal name="idCriterioCobrancaNaoEncontrado" value="exception">
							<html:text property="descricaoCriterioCobranca" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						
						<logic:notEqual name="idCriterioCobrancaNaoEncontrado" value="exception">
							<html:text property="descricaoCriterioCobranca" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
						</logic:present> 
						
						<logic:notPresent name="idCriterioCobrancaNaoEncontrado">
							<logic:empty name="AssociarConjuntoRotasCriterioCobrancaActionForm" property="idCriterioCobranca">
								<html:text property="descricaoCriterioCobranca" value="" size="40"
									maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							
							<logic:notEmpty name="AssociarConjuntoRotasCriterioCobrancaActionForm" property="idCriterioCobranca">
								<html:text property="descricaoCriterioCobranca" size="40"
									maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>

					</logic:notPresent> 					<a
						href="javascript:limparCriterio(document.forms[0]);">
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" />
					</a></td>
				</tr>
              	
              	<tr> 
                	<td><strong>Grupo de Cobrança:</strong></td>
                	<td height="24"><html:select property="idGrupoCobranca" tabindex="3" style="width: 170px;"
                		onchange="habilitacaoGrupoCobranca();">
                    	<html:option value="-1">&nbsp;</html:option>
                    	<html:options collection="colecaoGrupoCobranca"
							labelProperty="descricao" property="id" />
                    </html:select> <font size="1">&nbsp; </font></td>
              	</tr>
              	<tr>
					<td height="10" colspan="2"></td>
				</tr>
              	</table>
              	
              	<table width="100%" align="center" bgcolor="#99CCFF" border="0">
				<tr>
					<td align="center"><strong>Dados de Localização Geográfica</strong></td>
				</tr>
              	
              	<tr bgcolor="#cbe5fe">
					<td width="100%" align="center">

					<table width="100%" border="0">
	              	<tr> 
	                	<td><strong>Gerência Regional:</strong></td>
	                	<td height="24"><html:select property="idGerenciaRegional" tabindex="4" style="width: 170px;"
	                					onchange="habilitacaoLocalizacaoGeografica();limparQtdRotas();">
	                    	<html:option value="-1">&nbsp;</html:option>
	                    	<html:options collection="colecaoGerenciaRegional"
								labelProperty="nome" property="id" />
	                    </html:select> <font size="1">&nbsp; </font></td>
	              	</tr>
	              	
	              	<tr> 
	                	<td><strong>Unidade de Negócio:</strong></td>
	                	<td height="24"><html:select property="idUnidadeNegocio" tabindex="5" style="width: 170px;"
	                					onchange="habilitacaoLocalizacaoGeografica();limparQtdRotas();">
	                    	<html:option value="-1">&nbsp;</html:option>
	                    	<html:options collection="colecaoUnidadeNegocio"
								labelProperty="nome" property="id" />
	                    </html:select> <font size="1">&nbsp; </font></td>
	              	</tr>
	              	
	             	<tr>
						<td><strong>Localidade Inicial:</strong></td>
						<td height="24"><html:text maxlength="3" tabindex="6"
							property="idLocalidadeInicial" size="3"
							onkeypress="javascript:limparDescLocalidadeInicial(document.forms[0]);
							validaEnterComMensagem(event, 'exibirAssociarRotasCriterioCobrancaAction.do?objetoConsulta=1', 'idLocalidadeInicial','Localidade');"
							onkeyup="habilitacaoLocalizacaoGeografica();" />
						<a href="javascript:habilitarPesquisaLocalidade(document.forms[0],'origem');" >
						<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Localidade" /></a> 
							
							<logic:present
								name="idLocalidadeInicialNaoEncontrado">
							<logic:equal name="idLocalidadeInicialNaoEncontrado" value="exception">
								<html:text property="descricaoLocalidadeInicial" size="40"
									maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							
							<logic:notEqual name="idLocalidadeInicialNaoEncontrado" value="exception">
								<html:text property="descricaoLocalidadeInicial" size="40"
									maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
							</logic:present> 
							
							<logic:notPresent name="idLocalidadeInicialNaoEncontrado">
								<logic:empty name="AssociarConjuntoRotasCriterioCobrancaActionForm" property="idLocalidadeInicial">
									<html:text property="descricaoLocalidadeInicial" value="" size="40"
										maxlength="30" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:empty>
								
								<logic:notEmpty name="AssociarConjuntoRotasCriterioCobrancaActionForm" property="idLocalidadeInicial">
									<html:text property="descricaoLocalidadeInicial" size="40"
										maxlength="30" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>
	
						</logic:notPresent> 					<a
							href="javascript:limparLocalidadeInicial(document.forms[0]);
							limparSetorComercial(document.forms[0]);">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" />
						</a></td>
					</tr>
					
					<tr>
						<td><strong>Localidade Final:</strong></td>
						<td height="24"><html:text maxlength="3" tabindex="7"
							property="idLocalidadeFinal" size="3"
							onkeypress="javascript:limparDescLocalidadeFinal(document.forms[0]);
							limparDescSetorComercial(document.forms[0]);
							validaEnterComMensagem(event, 'exibirAssociarRotasCriterioCobrancaAction.do?objetoConsulta=1', 'idLocalidadeFinal','Localidade');"
							onkeyup="habilitacaoLocalizacaoGeografica();" />
						<a href="javascript:habilitarPesquisaLocalidade(document.forms[0],'destino');" >
						<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Localidade" /></a> 
							
							<logic:present
								name="idLocalidadeFinalNaoEncontrado">
							<logic:equal name="idLocalidadeFinalNaoEncontrado" value="exception">
								<html:text property="descricaoLocalidadeFinal" size="40"
									maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							
							<logic:notEqual name="idLocalidadeFinalNaoEncontrado" value="exception">
								<html:text property="descricaoLocalidadeFinal" size="40"
									maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
							</logic:present> 
							
							<logic:notPresent name="idLocalidadeFinalNaoEncontrado">
								<logic:empty name="AssociarConjuntoRotasCriterioCobrancaActionForm" property="idLocalidadeFinal">
									<html:text property="descricaoLocalidadeFinal" value="" size="40"
										maxlength="30" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:empty>
								
								<logic:notEmpty name="AssociarConjuntoRotasCriterioCobrancaActionForm" property="idLocalidadeFinal">
									<html:text property="descricaoLocalidadeFinal" size="40"
										maxlength="30" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>
	
						</logic:notPresent> 					<a
							href="javascript:limparLocalidadeFinal(document.forms[0]);
							limparSetorComercial(document.forms[0]);">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" />
						</a></td>
					</tr>
					
	
					<tr>
						<td><strong>Setor Comercial Inicial:</strong></td>
						<td height="24"><html:text maxlength="3"
							property="codigoSetorComercialInicial" tabindex="8" size="3"
							onkeypress="javascript:limparDescSetorComercial(document.forms[0]);
							validaEnterDependenciaComMensagem(event, 'exibirAssociarRotasCriterioCobrancaAction.do?objetoConsulta=1', this, document.forms[0].idLocalidadeInicial.value, 'Localidade','Setor Comercial');"
							onkeyup="habilitacaoLocalizacaoGeografica();" />
							
							<a
							href="javascript:abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidadeInicial.value+'&tipo=setorComercialOrigem',document.forms[0].idLocalidadeInicial.value,'Localidade', 400, 800);">
							
							<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Setor Comercial" /></a> <logic:present
							name="codigoSetorComercialInicialNaoEncontrado">
							<logic:equal name="codigoSetorComercialInicialNaoEncontrado"
								value="exception">
								<html:text property="descricaoSetorComercialInicial" size="40"
									maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="codigoSetorComercialInicialNaoEncontrado"
								value="exception">
								<html:text property="descricaoSetorComercialInicial" size="40"
									maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> <logic:notPresent
							name="codigoSetorComercialInicialNaoEncontrado">
							<logic:empty name="AssociarConjuntoRotasCriterioCobrancaActionForm"
								property="codigoSetorComercialInicial">
								<html:text property="descricaoSetorComercialInicial" value="" size="40"
									maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="AssociarConjuntoRotasCriterioCobrancaActionForm"
								property="codigoSetorComercialInicial">
								<html:text property="descricaoSetorComercialInicial" size="40"
									maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
	
						</logic:notPresent> 
							<a
							href="javascript:limparSetorComercial(document.forms[0]);">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" />
						</a>
					</td>
					</tr>
	             	
	             	<tr>
						<td><strong>Setor Comercial Final:</strong></td>
						<td height="24"><html:text maxlength="3"
							property="codigoSetorComercialFinal" tabindex="9" size="3"
							onkeypress="javascript:limparDescSetorComercialFinal(document.forms[0]);
							validaEnterDependenciaComMensagem(event, 'exibirAssociarRotasCriterioCobrancaAction.do?objetoConsulta=1', this, document.forms[0].idLocalidadeFinal.value, 'Localidade','Setor Comercial');"
							onkeyup="habilitacaoLocalizacaoGeografica();" />
							
							<a
							href="javascript:abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidadeFinal.value+'&tipo=setorComercialDestino',document.forms[0].idLocalidadeFinal.value,'Localidade', 400, 800);">
							
							<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Setor Comercial" /></a> <logic:present
							name="codigoSetorComercialFinalNaoEncontrado">
							<logic:equal name="codigoSetorComercialFinalNaoEncontrado"
								value="exception">
								<html:text property="descricaoSetorComercialFinal" size="40"
									maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="codigoSetorComercialFinalNaoEncontrado"
								value="exception">
								<html:text property="descricaoSetorComercialFinal" size="40"
									maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> <logic:notPresent
							name="codigoSetorComercialFinalNaoEncontrado">
							<logic:empty name="AssociarConjuntoRotasCriterioCobrancaActionForm"
								property="codigoSetorComercialFinal">
								<html:text property="descricaoSetorComercialFinal" value="" size="40"
									maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="AssociarConjuntoRotasCriterioCobrancaActionForm"
								property="codigoSetorComercialFinal">
								<html:text property="descricaoSetorComercialFinal" size="40"
									maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
	
						</logic:notPresent> 
							<a
							href="javascript:limparSetorComercialFinal(document.forms[0]);">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" />
						</a>
					</td>
					</tr>
					
		         	<tr> 
	                	<td><strong>Rota Inicial:</strong></td>
	                	<td height="24"><html:select property="numeroRotaInicial" tabindex="10"
	                					onchange="habilitacaoLocalizacaoGeografica();">
	                    	<html:option value="-1">&nbsp;</html:option>
	                    	<html:options collection="colecaoRotaInicial"
								labelProperty="codigo" property="codigo" />
	                    </html:select> <font size="1">&nbsp; </font></td>
	              	</tr>
	              	
	              	<tr> 
	                	<td><strong>Rota Final:</strong></td>
	                	<td height="24"><html:select property="numeroRotaFinal" tabindex="11"
	                					onchange="habilitacaoLocalizacaoGeografica();">
	                    	<html:option value="-1">&nbsp;</html:option>
	                    	<html:options collection="colecaoRotaFinal"
								labelProperty="codigo" property="codigo" />
	                    </html:select> <font size="1">&nbsp; </font></td>
	              	</tr>
	              	</table>
	              	</td>
              	</tr>
              	</table>
              	
              	<table width="100%" border="0">
              	<tr> 
                	<td colspan="2" align="right">
                	<input type="button" name="Selecionar" value="Selecionar"  class="bottonRightCol" onclick="javascript:selecionar();"/>
                	</td>
              	</tr>
              	<tr>
					<td height="20"></td>
				</tr>
              	</table>
              	
              	<table width="100%" border="0">
              	<tr> 
                	<td><strong>Quantidade de rotas selecionadas:</strong></td>
                	<td width="25%" align="right"><html:text property="qtdRotasSelecionadas" size="10" 
						 					style="background-color:#EFEFEF; border:0; color: #000000; text-align: right;"/></td>
              	</tr>
              	<tr> 
                	<td><strong>Quantidade de rotas COM critério informado para ESTA Ação de Cobrança:</strong></td>
                	<td align="right"><html:text property="qtdRotasComCriterio" size="10" 
						 					style="background-color:#EFEFEF; border:0; color: #000000; text-align: right;"/></td>
              	</tr>
              	<tr> 
                	<td><strong>Quantidade de rotas SEM critério informado para ESTA Ação de Cobrança:</strong></td>
                	<td align="right"><html:text property="qtdRotasSemCriterio" size="10" 
						 					style="background-color:#EFEFEF; border:0; color: #000000; text-align: right;"/></td>
              	</tr>
             	</table>
             	
             	<table width="100%" border="0">
				<tr>
					<td height="50">
						<input name="Button" type="button" class="bottonRightCol" value="Limpar" align="left" onclick="window.location.href='<html:rewrite page="/exibirAssociarRotasCriterioCobrancaAction.do?menu=sim"/>'" />
                    </td>
                    <td  align="right">
                    	<input type="button" name="associar" value="Associar" class="bottonRightCol" onclick="javascript:adicionar();" />
                    	<input type="button" name="desassociar" value="Desassociar" class="bottonRightCol" onclick="remover();" />
					</td>
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
