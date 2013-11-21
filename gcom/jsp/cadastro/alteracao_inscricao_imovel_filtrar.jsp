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

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">


<html:javascript staticJavascript="false"  formName="FiltrarAlteracaoInscricaoImovelActionForm"  />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

function validarForm() {
  	var form = document.forms[0];
  	if ( validateFiltrarAlteracaoInscricaoImovelActionForm( form ) ){
		  	form.action = "/gsan/filtrarAlteracaoInscricaoImovelAction.do";
			
			submeterFormPadrao(form);   		  
  		}
	}
  
function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg){

	if (objeto == null || codigoObjeto == null){
		abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
	}
	else{
		if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
			alert(msg);
		}
		else{
			abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
		}
	}
}

/*
recupera os dados vindos pelo metodo enviarDadosQuatroParametros 
*/

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	var form = document.FiltrarAlteracaoInscricaoImovelActionForm;
	
	if (tipoConsulta == 'localidade') {
      form.idLocalidade.value = codigoRegistro;
	  form.desLocalidade.value = descricaoRegistro;
	  form.desLocalidade.style.color = "#000000";
	 
	}

}

/*
recupera os dados vindos pelo metodo enviarDadosQuatroParametros 
*/

function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {
	var form = document.FiltrarAlteracaoInscricaoImovelActionForm;

	if (tipoConsulta == 'setorComercialOrigem') {
      form.codigoSetorComercial.value = codigoRegistro;
      form.idSetorComercial.value = idRegistro;
	  form.desSetorComercial.value = descricaoRegistro;
	  form.desSetorComercial.style.color = "#000000";
	  
	  
	}

}

  
function limparBorracha(tipo){
	
	var form = document.forms[0];
	
	switch(tipo){
		case 1: //Localidade
		    
				form.idLocalidade.value = "";
				form.desLocalidade.value = "";
				break;
		
			
			
			
		case 2: //Setor Comercial
		    
				form.codigoSetorComercial.value = "";
				form.idSetorComercial.value = "";
				form.desSetorComercial.value = "";
				break;

		    
		    
	}
}

function habilitarPesquisaLocalidade(form) {
	chamarPopup('exibirPesquisarLocalidadeAction.do', 'localidade', null, null, 275, 480, '',form.idLocalidade.value);
}
	

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
	if(objetoRelacionado.readOnly != true){
		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		} else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			} else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
			}
		}
	}
}

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg){

	if (objeto == null || codigoObjeto == null){
		abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
	}
	else{
		if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
			alert(msg);
		}
		else{
			abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
		}
	}
}
		 
</script>
</head>

<body leftmargin="5" topmargin="5" >

	<html:form action="/filtrarAlteracaoInscricaoImovelAction"
		name="FiltrarAlteracaoInscricaoImovelActionForm"
		type="gcom.gui.cadastro.FiltrarAlteracaoInscricaoImovelActionForm"
		method="post"
		onsubmit="return validateFiltrarAlteracaoInscricaoImovelActionForm(this);">
		
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
							<td class="parabg">Filtrar Imovel(s) com Inscrição(s) alterada(s)</td>
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
									<td width="80%">Para filtrar o(s) imóvel(s), informe os dados abaixo:</td>
									<td align="right">
									</td>
									<logic:present scope="application" name="urlHelp">
										<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroGeograficoMunicipioFiltrar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
									</logic:present>
									<logic:notPresent scope="application" name="urlHelp">
										<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
									</logic:notPresent>
								</tr>
							</table>
							</td>
						</tr>
					</table>
		
					<table width="100%" border="0">
					
						<tr>
										<td width="30%"><strong>Localidade:</strong></td>
										<td>
											<html:text tabindex="8" maxlength="3" property="idLocalidade" size="5"
											onkeypress="validaEnter(event, 'exibirFiltrarAlteracaoInscricaoImovelAction.do?objetoConsulta=1', 'idLocalidade');return isCampoNumerico(event);"
											/>
											
											<a href="javascript:habilitarPesquisaLocalidade(document.forms[0]);">
											<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											title="Pesquisar" /></a>
											
											<logic:present name="corLocalidadeOrigem">
												<logic:equal name="corLocalidadeOrigem" value="exception">
													<html:text property="desLocalidade" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:equal>
						
												<logic:notEqual name="corLocalidadeOrigem" value="exception">
													<html:text property="desLocalidade" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEqual>
											</logic:present>
											
											<logic:notPresent name="corLocalidadeOrigem">
												<logic:empty name="FiltrarAlteracaoInscricaoImovelActionForm"
													property="idLocalidade">
													<html:text property="desLocalidade" value="" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:empty>
				
												<logic:notEmpty name="FiltrarAlteracaoInscricaoImovelActionForm"
													property="idLocalidade">
													<html:text property="desLocalidade" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: 	#000000" />
												</logic:notEmpty>
											</logic:notPresent>
											
											<a href="javascript:limparBorracha(1);">
											<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" title="Apagar" /></a>
										</td>
									</tr>
									
									<tr>
										<td><strong>Setor Comercial:</strong></td>
										<td>
											<html:text tabindex="9" maxlength="3"
											property="codigoSetorComercial" size="5"
											onkeypress="validaEnterDependencia(event, 'exibirFiltrarAlteracaoInscricaoImovelAction.do?objetoConsulta=2', document.forms[0].codigoSetorComercial, document.forms[0].idLocalidade.value, 'Localidade.');return isCampoNumerico(event);"
											/>
											
											<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem','idLocalidade', document.FiltrarAlteracaoInscricaoImovelActionForm.idLocalidade.value, 275, 480, 'Informe Localidade.');" id="btPesqSetorComercialInicial">
											<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar" /></a>
											
											<logic:present name="corSetorComercialOrigem">
												<logic:equal name="corSetorComercialOrigem" value="exception">
													<html:text property="desSetorComercial" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:equal>
					
												<logic:notEqual name="corSetorComercialOrigem" value="exception">
													<html:text property="desSetorComercial" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEqual>
					
											</logic:present>
											
											<logic:notPresent name="corSetorComercialOrigem">
												<logic:empty name="FiltrarAlteracaoInscricaoImovelActionForm" property="codigoSetorComercial">
													<html:text property="desSetorComercial" value="" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:empty>
												<logic:notEmpty name="FiltrarAlteracaoInscricaoImovelActionForm" property="codigoSetorComercial">
													<html:text property="desSetorComercial" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEmpty>
											</logic:notPresent>
											<a href="javascript:limparBorracha(2);">
												<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
											</a>
											<html:hidden property="idSetorComercial"/>
										</td>
									</tr>
									
									<tr>
							<td>
								<input name="Button" 
									type="button" 
									class="bottonRightCol"
									value="Limpar" 
									align="left"
									onclick="window.location.href='/gsan/exibirFiltrarAlteracaoInscricaoImovelAction.do?menu=sim'">
									<input name="Submit23" 
									class="bottonRightCol" 
									value="Cancelar" 
									align="right"
									type="button" tabindex="15" 
									onclick="window.location.href='/gsan/telaPrincipal.do'"> 
							</td>
							<td align="right">
								<input type="button"
									onclick="validarForm(document.forms[0]);" 
									class="bottonRightCol"
									value="Filtrar" 
									tabindex="12" 
									style="width: 70px;">
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