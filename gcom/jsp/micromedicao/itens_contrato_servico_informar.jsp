<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@page isELIgnored="false"%>

<%@ page import="java.util.Collection,gcom.util.ConstantesSistema"%>
<%@ page import="gcom.util.Util"%>
<%@ page import="java.util.Date"%>
<%@ page import="gcom.micromedicao.ContratoEmpresaServico"%>
<%@ page import="gcom.micromedicao.ContratoEmpresaAditivo"%>
<%@ page import="gcom.micromedicao.ItemServicoContrato"%>
<%@ page import="gcom.micromedicao.InformarItensContratoServicoHelper"%>

<%@ page import="gcom.gui.GcomAction"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
	
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript" 
	src="<bean:message key="caminho.js"/>scroll_horizontal.js"></script>

<script language="JavaScript">

function validarForm(formulario){
	var form = document.forms[0];
	
	if(form.idEmpresa.value != '-1' && form.idNumeroContrato.value != ''){
		if (form.percentualTaxaSucesso.value != '' && !validaPercentual(form.percentualTaxaSucesso)) {
		  		alert('Percentual da taxa de sucesso inválido.');
		  		form.percentualTaxaSucesso.focus();
		} else if( validaData(form.dtInicioContrato) && (form.dtFimContrato.value == '' || validaData(form.dtFimContrato) )){
			if (form.dtFimContrato.value == '' || comparaData(form.dtInicioContrato.value, "<", form.dtFimContrato.value )){
				submeterFormPadrao(formulario);
			}else{
		  		alert('Data início do contrato é anterior à data de fim do contrato.');			
		  	}
		}
	}else if(validateExibirInformarItensContratoServicoActionForm(formulario)){
		
		submeterFormPadrao(formulario);		
	}
}
	
function remover(obj){
	var form = document.forms[0];
	var today = new Date();
	var day   = today.getDate();
	var month = today.getMonth();
	var year  = today.getFullYear();
	var now = padout(day) + '/' + padout(month - 0 + 1) + '/' + year;
	
	
	if (form.dtFimContrato.value != '' && comparaData(form.dtFimContrato.value, "<", now)) {
		alert('Item do contrato não pode ser removido, pois a data de fim de contrato é anterior à data atual.');
	} else if (confirm('Confirma Remoção?')) {

		form.action = 'exibirInformarItensContratoServicoAction.do?acao=remover&id='+obj;
		form.submit();
	}
}
	
function removerAditivo(obj){
	var form = document.forms[0];
	var today = new Date();
	var day   = today.getDate();
	var month = today.getMonth();
	var year  = today.getFullYear();
	var now = padout(day) + '/' + padout(month - 0 + 1) + '/' + year;
	
	if (confirm('Confirma Remoção?')) {
		form.action = 'exibirInformarItensContratoServicoAction.do?acao=removerAditivo&idAditivo='+obj;
		form.submit();
	}
}

function desabilitarCampos(){
	var form = document.forms[0];
			
	if(form.idEmpresa.value == '-1' || form.idEmpresa.value == ''){
		
		form.idContrato.disabled = true;
		form.dtInicioContrato.disabled = true;
		form.dtFimContrato.disabled = true;
		form.valorItemContrato.disabled = true;
		form.idItemContrato.disabled = true;
		form.idEmpresa.disabled = false;
		form.valorGlobalContrato.disabled = true;
		form.percentualTaxaSucesso.disabled = true;
		form.observacao.disabled = true;
		form.idServicoTipo.disabled = true;
		form.descricaoServicoTipo.disabled = true;
	}else {
		habilitarCampos();
	}
}

function habilitarCampos(){
	var form = document.forms[0];
	
	if(form.idEmpresa.value != '-1'){
		
		if(obterValorRadioMarcadoPorNome("idNumeroContrato") != ''){
			
			if(form.dtFimContrato.value == ''){
				form.dtFimContrato.disabled = false;
			}else{
				form.dtFimContrato.disabled = true;
			}
					
		}
		
		form.idEmpresa.disabled = true;
		form.valorItemContrato.disabled = false;
		form.idItemContrato.disabled = false;
		form.observacao.disabled = false;
		form.idServicoTipo.disabled = false;
		form.descricaoServicoTipo.disabled = false;
		form.percentualTaxaSucesso.disabled = false;
		form.valorGlobalContrato.disabled = false;
	}else{
		form.idEmpresa.disabled = false;
	}
}

function habilitarEmpresa(){
	var form = document.forms[0];
	
	form.idEmpresa.disabled = false;
}

function numeroPositivo() { 
	var form = document.forms[0];

	if (( form.valorItemContrato.value <= '0,00' && form.valorItemContrato.value != '' ) ||
		( form.valorItemContrato.value == '00' && form.valorItemContrato.value != '' ) ) {
	
		alert("Valor do item deve somente conter números positivos.");
 		form.valorItemContrato.value = "";
	} 
}

function reload(){
	var form = document.forms[0];
	
	form.action = 'exibirInformarItensContratoServicoAction.do';
	form.submit();
}



function reloadContrato(obj){
	var form = document.forms[0];
	
	form.action = 'exibirInformarItensContratoServicoAction.do?acao=adicionar&id='+obj;
	form.submit();
}

function adicionarItem(){
	var form = document.forms[0];
	
	if(form.idContrato.value != "" && form.dtInicioContrato.value != "" 
		&& form.idItemContrato.value != "-1" && form.valorItemContrato.value != "" 
		&& validaData(form.dtInicioContrato) ){
		if (form.percentualTaxaSucesso.value != '' && !validaPercentual(form.percentualTaxaSucesso)) {
		  		alert('Percentual da taxa de sucesso inválido.');
		  		form.percentualTaxaSucesso.focus();
		} else if( form.dtFimContrato.value == "" || validaData(form.dtFimContrato) ){
		
			form.action = 'exibirInformarItensContratoServicoAction.do?acao=adicionar&id='+obterValorRadioMarcadoPorNome("idNumeroContrato");
			form.submit();
		}
	}else if(form.idContrato.value == ""){
		alert("Informe o Número do Contrato.");
	}else if (form.dtInicioContrato.value == ""){
		alert("Informe a Data de Início do Contrato.");
	}else if (form.idItemContrato.value == "-1"){
		alert("Informe o Item do Contrato.");
	}else if(form.valorItemContrato.value == ""){
		alert("Informe o valor do Item.");
	}
}

function validaPercentual(percentualTaxaSucesso){
	if (obterNumerosSemVirgulaEPonto(percentualTaxaSucesso.value) > 10000
		|| obterNumerosSemVirgulaEPonto(percentualTaxaSucesso.value) <= 0 ) {
		return false;
	}
	
	return true;
}

function abrirPopupAditivo() {
	var form = document.forms[0];
	var url = 'exibirIncluirAditivoAction.do?idContrato=';
	
	if(form.idEmpresa.value != '-1' && form.idContrato.value != '' && (campoSelecionado(form.idNumeroContrato))){
		url = url + form.idNumeroContrato.value;
		abrirPopup(url, 370, 570);
	} else {
		alert('Aditivo não pode ser incluído, pois nenhum contrato foi selecionado.');
	}
}

function campoSelecionado(idNumeroContrato) {
	var i;
	
	if (idNumeroContrato.size != null
		&& idNumeroContrato.size == 0
		&& idNumeroContrato.checked == true)
		return true;
		
	for (i = 0; i < idNumeroContrato.length; i++){
		if (idNumeroContrato[i].checked == true) {
			return true;
		}
	}
	
	return false;
}

function valorCampo(idNumeroContrato, idContEmp) {
	var i;
	
	for (i = 0; i < idNumeroContrato.length; i++){
		if (idNumeroContrato[i].checked == true) {
			return idContEmp[i].value;
		}
	}
	
	return "";
}

function limparPesquisaServicoTipo(){
	 var form = document.forms[0];
	 
	 form.idServicoTipo.value = '';
	 form.descricaoServicoTipo.value = '';
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	var form = document.forms[0];
	
	if (tipoConsulta == 'tipoServico') {
		form.idServicoTipo.value = codigoRegistro;
		form.descricaoServicoTipo.value = descricaoRegistro;
		form.descricaoServicoTipo.style.color = "#000000";
  	}
}
</script>
</head>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js">
</script>
<html:javascript staticJavascript="false"
	formName="ExibirInformarItensContratoServicoActionForm" />

<body leftmargin="5" topmargin="5" onload="javascript:desabilitarCampos(); ">

<html:form action="/informarItensContratoServicoAction.do"
	name="ExibirInformarItensContratoServicoActionForm"
	type="gcom.gui.micromedicao.ExibirInformarItensContratoServicoActionForm"
	method="post">

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
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Informar Itens de Contrato de Serviço</td>
					<td width="11" valign="top"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Pesquisar uma empresa para inserir um contrato:</td>
				</tr>
				
				<logic:present name="contratoSelecionado" scope="session">
					<tr>
						<td width="60"><strong>Empresa:<font color="#FF0000">*</font></strong></td>
						<td >
							<html:select property="idEmpresa" 
								 		 tabindex="1" 
								 		 onclick="javascript:habilitarCampos();"
								 		 onchange="reload();" 
								 		 disabled="true" 
								 		 style="background-color:#EFEFEF; border:0" >
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoEmpresa"
										  labelProperty="descricao" 
										  property="id" />
							</html:select> 
							<font size="1">&nbsp; </font>
						</td>
					</tr>
				</logic:present>
				
				<logic:notPresent name="contratoSelecionado" scope="session" >
					<tr>
						<td width="60"><strong>Empresa:<font color="#FF0000">*</font></strong></td>
						<td >
							<html:select property="idEmpresa" 
								 		 tabindex="1" 
								 		 onclick="javascript:habilitarCampos();"
								 		 onchange="reload();"
								 		 disabled="false">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoEmpresa"
										  labelProperty="descricao" 
										  property="id" />
							</html:select> 
							<font size="1">&nbsp; </font>
						</td>
					</tr>
				</logic:notPresent>
				
				<tr>
					<td colspan="2"><hr></td>
				</tr>

				<tr>
					<td colspan="2">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td>
							<table width="60%" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td width="10%">
										<strong>Selecionar</strong>
									</td>
									<td width="50%">
										<strong>Número do Contrato</strong>
									</td>
								</tr>
							</table>
							</td>
						</tr>
						
						<tr>
							<td>
								<%String cor = "#FFFFFF";%> 
								<logic:present name="colecaoHelper" scope="session">
			
									<div style="width: 60%; height: 80; overflow: auto;">
			
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td><%cor = "#cbe5fe";%>
			
											<table width="100%" align="center" bgcolor="#99CCFF">
												<logic:iterate name="colecaoHelper" 
													id="helper"
													type="InformarItensContratoServicoHelper">
													<c:set var="count" value="${count+1}" />
													<c:choose>
														<c:when test="${count%2 == '1'}">
															<tr bgcolor="#FFFFFF">
														</c:when>
														<c:otherwise>
															<tr bgcolor="#cbe5fe">
														</c:otherwise>
													</c:choose>
														
													<td width="10%">
														<div align="center">
															<label > 
																<html:radio 
																	value="${count}"
																	property="idNumeroContrato"
																	onclick="javascript:reloadContrato('${count}');" /> 
																	
																<html:hidden property="idContEmp" name="idContEmp" value="${helper.contratoEmpresaServico.id}" />
															</label> 
														</div>
													</td>
													<td width="50%">
														<div align="left">
															<div align="left">
																<bean:write name="helper" property="contratoEmpresaServico.descricaoContrato" />
															</div>
														</div>
													</td>
												</logic:iterate>
											</table>
											</td>
										</tr>
									</table>
									</div>
								</logic:present>
							</td>
						</tr>
					</table>
					
					</td>
				</tr>
				<tr>
					<td colspan="2"></td>
				</tr>
				<tr>
					<td colspan="2"></td>
				</tr>
				<logic:present name="contratoSelecionado" scope="session">
					<tr>
						<td width="23%" class="style3">
							<strong>Número do Contrato:<font color="#FF0000">*</font></strong>
						</td>
						<td width="68%" colspan="2">
							<strong><b><span class="style2"> 
								<html:text property="idContrato" 
										   size="15" 
										   maxlength="15" 
										   tabindex="3" 
										   disabled="true" style="background-color:#EFEFEF; border:0" />
							</span></b></strong>
						</td>
					</tr>
				</logic:present>
				
				<logic:notPresent name="contratoSelecionado" scope="session">
					<tr>
						<td width="23%" class="style3">
							<strong>Número do Contrato:<font color="#FF0000">*</font></strong>
						</td>
						<td width="68%" colspan="2">
							<strong><b><span class="style2"> 
								<html:text property="idContrato" 
										   size="15" 
										   maxlength="15" 
										   tabindex="3" 
										   disabled="false" />
							</span></b></strong>
						</td>
					</tr>
				</logic:notPresent>
				
				<logic:present name="contratoSelecionado" scope="session">
					<tr>
						<td><strong>Data de Início do contrato:<font color="#FF0000">*</font></strong></td>
						<td>
							<html:text property="dtInicioContrato" 
									   size="10" 
									   maxlength="10"
									   tabindex="4"
									   disabled="true" style="background-color:#EFEFEF; border:0" />
						</td>
					</tr>
				</logic:present>
				
				<logic:notPresent name="contratoSelecionado" scope="session">
					<tr>
						<td><strong>Data Início do contrato:<font color="#FF0000">*</font></strong></td>
						<td>
							<html:text property="dtInicioContrato" 
									   size="10" 
									   maxlength="10"
									   tabindex="4"
									   onkeypress="return isCampoNumerico(event);"
									   onkeyup="mascaraData(this, event);" />
							<a href="javascript:abrirCalendario('ExibirInformarItensContratoServicoActionForm', 'dtInicioContrato');">
								<img border="0"
									 src="<bean:message key="caminho.imagens"/>calendario.gif"
									 width="20" 
									 border="0" 
									 align="absmiddle" 
									 alt="Exibir Calendário" title="Exibir Calendário" /></a>dd/mm/aaaa
						</td>
					</tr>
				</logic:notPresent>
				
				<logic:present name="dataFimContrato" scope="session">
					<tr>
						<td><strong>Data de Fim do Contrato:</strong></td>
						<td>
							<html:text property="dtFimContrato" 
									   size="10" 
									   maxlength="10" 
									   tabindex="5" 
									   disabled="true" style="background-color:#EFEFEF; border:0" /> 	
						</td>
					</tr>
				</logic:present>
				
				<logic:notPresent name="dataFimContrato" scope="session">
					<tr>
						<td><strong>Data de Fim do Contrato:</strong></td>
						<td>
							<html:text property="dtFimContrato" 
									   size="10" 
									   maxlength="10" 
									   tabindex="5" 
									   onkeypress="return isCampoNumerico(event);"
									   onkeyup="mascaraData(this, event);" /> 
							<a href="javascript:abrirCalendario('ExibirInformarItensContratoServicoActionForm', 'dtFimContrato')">
								<img border="0" 
									 src="<bean:message key="caminho.imagens"/>calendario.gif"
									 width="20" 
									 border="0" 
									 align="absmiddle" 
									 alt="Exibir Calendário" title="Exibir Calendário" /></a> dd/mm/aaaa
						</td>
					</tr>
				</logic:notPresent>
				
				<tr>
					<td><strong>Valor Global do Contrato:</strong></td>
					<td width="68%" colspan="2"><strong><b><span class="style2"> 
					  <html:text property="valorGlobalContrato" 
					  			 size="14" 
					  			 maxlength="16" 
					  			 tabindex="7"
					  			 onblur="javascript:numeroPositivo();"
					  			 onkeypress="formataValorMonetario( this, 14);return isCampoNumerico(event);" 
								 onkeyup="formataValorMonetario( this, 14);"
								 disabled="false" style="text-align: right;" />
						</span></b></strong>
					</td>
				</tr>
				
				<tr>
					<td><strong>Percentual Taxa de Sucesso:</strong></td>
					<td width="68%" colspan="2"><strong><b><span class="style2"> 
					  <html:text property="percentualTaxaSucesso" 
					  			 size="14" 
					  			 maxlength="6" 
					  			 tabindex="7"
					  			 onblur="javascript:numeroPositivo();"
					  			 onkeypress="formataValorMonetario( this, 6);return isCampoNumerico(event);" 
								 onkeyup="formataValorMonetario( this, 6);"
								 style="text-align: right;" 
								 disabled="false"/>
						</span></b></strong>
					</td>
				</tr>

                <tr> 
                   	<td><strong>Observa&ccedil;&atilde;o:</strong></td>
                  	<td colspan="2">
						<html:textarea property="observacao" cols="40"
							rows="4" readonly="false" onkeyup="limitTextArea(this, 400, document.getElementById('utilizadoLocalOcorrencia'), document.getElementById('limiteLocalOcorrencia'));"/><br>
						<strong><span id="utilizadoLocalOcorrencia">0</span>/<span id="limiteLocalOcorrencia">400</span></strong>
					</td>
                </tr>
                
				<tr>
					<td valign="middle"><strong>Item de Contrato:<font color="#FF0000">*</font></strong></td>
					
					<logic:present name="colecaoItemServico" scope="session">
						<td colspan="3" valign="top">
							<html:select property="idItemContrato"
										 tabindex="6"
										 style="WIDTH: 300px;">
								<html:option value="-1">&nbsp;</html:option>
								<html:options collection="colecaoItemServico"
											  labelProperty="descricao" 
											  property="id" />
							</html:select>
							<font size="1">&nbsp; </font>
						</td>
					</logic:present>
				</tr>
				
				<tr>
					<td><strong>Valor do Item:<font color="#FF0000">*</font></strong></td>
					<td width="68%" colspan="2"><strong><b><span class="style2"> 
					  <html:text property="valorItemContrato" 
					  			 size="13" 
					  			 maxlength="15" 
					  			 tabindex="7"
					  			 onblur="javascript:numeroPositivo();"
					  			 onkeypress="formataValorMonetario( this, 13);return isCampoNumerico(event);" 
								 onkeyup="formataValorMonetario( this, 13);"
								 style="text-align: right;" />
						</span></b></strong>
					</td>
				</tr>	
				
       			 <tr>
	                <td width="30%"><strong>Tipo de Serviço:<font color="#ff0000"></font></strong>
	               </td>
	           	  <td width="69%">
		              	<html:text maxlength="4" property="idServicoTipo" size="4" tabindex="7"
							onkeypress="javascript:validaEnterComMensagem(event, 'exibirInformarItensContratoServicoAction.do?pesquisaServicoTipo=1', 'idServicoTipo', 'Serviço Tipo');" onkeyup="document.forms[0].descricaoServicoTipo.value='';"/>
						<a href="javascript:abrirPopup('exibirPesquisarTipoServicoAction.do');">
							<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Serviço Tipo" />
						</a>
						<logic:present name="idServicoTipoNaoEncontrado">
							<logic:equal name="idServicoTipoNaoEncontrado" value="exception">
								<html:text property="descricaoServicoTipo" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="idServicoTipoNaoEncontrado" value="exception">
								<html:text property="descricaoServicoTipo" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						<logic:notPresent name="idServicoTipoNaoEncontrado">
							<logic:empty name="ExibirInformarItensContratoServicoActionForm" property="idServicoTipo">
								<html:text property="descricaoServicoTipo" value="" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="ExibirInformarItensContratoServicoActionForm" property="idServicoTipo">
								<html:text property="descricaoServicoTipo" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent>
						<a href="javascript:limparPesquisaServicoTipo();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" />
						</a>
	             </td>
												                  
												                    
                </tr>
				
				<tr>
					<td>
						<strong>Percentual de serviços não executados / não aceitos para aplicação de multa:</strong>
					</td>
					<td width="68%" colspan="2"><strong><b><span class="style2"> 
					  <html:text property="percentualServicosNaoExecutados" size="13" maxlength="5" 
					  			 onblur="javascript:numeroPositivo();"
					  			 onkeypress="formataValorMonetario( this, 13);return isCampoNumerico(event);" 
								 onkeyup="formataValorMonetario( this, 13);" style="text-align: right;" />
						</span></b></strong>
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>Percentual da multa a ser aplicada quando o percentual acima for atingido:</strong>
					</td>
					<td width="68%" colspan="2"><strong><b><span class="style2"> 
					  <html:text property="percentualMultaAplicar" size="13" maxlength="5" 
					  			 onblur="javascript:numeroPositivo();"
					  			 onkeypress="formataValorMonetario( this, 13);return isCampoNumerico(event);" 
								 onkeyup="formataValorMonetario( this, 13);" style="text-align: right;" />
						</span></b></strong>
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>Quantidade orçada para o item de contrato:</strong>
					</td>
					<td width="68%" colspan="2"><strong><b><span class="style2"> 
					  <html:text property="quantidadeOrcadaItemContrato" size="13" maxlength="10" 
					  			 onblur="javascript:numeroPositivo();"
					  			 onkeypress="formataValorMonetario( this, 13);return isCampoNumerico(event);" 
								 onkeyup="formataValorMonetario( this, 13);" style="text-align: right;" />
						</span></b></strong>
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>Valor orçado para o item de contrato:</strong>
					</td>
					<td width="68%" colspan="2"><strong><b><span class="style2"> 
					  <html:text property="valorOrcadoItemContrato" size="13" maxlength="13" 
					  			 onblur="javascript:numeroPositivo();"
					  			 onkeypress="formataValorMonetario( this, 13);return isCampoNumerico(event);" 
								 onkeyup="formataValorMonetario( this, 13);" style="text-align: right;" />
						</span></b></strong>
					</td>
				</tr>
				
				<tr>
					<td width="500" colspan="2" align="center">
						<div align="center"><font color="#FF0000">*</font> Campos obrigatórios </div>
					</td>
				</tr>
				<tr>
					<td width="" align="right" colspan="2">
						<input type="button" 
							   name="ButtonReset" 
							   class="bottonRightCol"
							   value="Incluir Aditivo"
							   onclick="javascript:abrirPopupAditivo();">
						
						<input type="button" 
							   name="ButtonReset" 
							   class="bottonRightCol"
							   value="Adicionar"
							   onclick="javascript:adicionarItem();">
					</td>
					
					
				</tr>
				
				<tr>
					<td colspan="2"><hr></td>
				</tr>
				
				<tr>
					<td colspan="2"><strong>Itens do Contrato:</strong></td>
				</tr>
				<tr>
					<td colspan="2">
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td>
								<table width="100%" bgcolor="#99CCFF">
									<tr bgcolor="#99CCFF">
										<td width="10%">
											<strong>Remover</strong>
										</td>
										<td width="25%">
											<strong>Item do Contrato</strong>
										</td>
										<td width="15%">
											<strong>Valor do Item</strong>
										</td>
										
										<td width="10%">
											<strong>Perc. 1</strong>
										</td>
										
										<td width="10%">
											<strong>Perc. 2</strong>
										</td>
										
										<td width="15%">
											<strong>Qtde Orçada</strong>
										</td>
										
										<td width="15%">
											<strong>Valor Orçado</strong>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<%String cor2 = "#FFFFFF";%> 
								<%--scroll --%>
								<logic:present name="colecaoItemServicoContratoSelecionados" scope="session">
									<div style="width: 100%; height: 80; overflow: auto;">
										<table width="100%" cellpadding="0" cellspacing="0" border="0">
											<tr>
												<td> <%cor2 = "#cbe5fe";%>
												<table width="100%" bgcolor="#99CCFF" border="0">
													<logic:iterate name="colecaoItemServicoContratoSelecionados" 
												   				   id="itemServicoContrato"
												   				   type="ItemServicoContrato">
														<c:set var="count2" value="${count2+1}" />
														<c:choose>
															<c:when test="${count2%2 == '1'}">
																<tr bgcolor="#FFFFFF">
															</c:when>
															<c:otherwise>
																<tr bgcolor="#cbe5fe">
															</c:otherwise>
														</c:choose>
		
														<td align="center" width="10%">
															<img src="<bean:message key='caminho.imagens'/>Error.gif" 
																 style="cursor: hand;"
																 onclick="remover('${count2}')"
																 title="Remover" >
														</td>
													
														<td width="25%">
															<div align="left">
																<bean:write name="itemServicoContrato" property="itemServico.descricao" />
															</div>
														</td>
													
														<td width="15%">
															<div align="left">
																<logic:present name="itemServicoContrato" property="valor">
																	<%= Util.formatarMoedaReal(itemServicoContrato.getValor())%>
																	</logic:present> 
																<logic:notPresent name="itemServicoContrato" property="valor">&nbsp;</logic:notPresent>
															</div>
														</td>
														
														<td width="10%">
															<div align="left">
																<logic:present name="itemServicoContrato" property="percentualServicosNaoExecutados">
																	<%= Util.formatarMoedaReal(itemServicoContrato.getPercentualServicosNaoExecutados())%>
																	</logic:present> 
																<logic:notPresent name="itemServicoContrato" property="valor">&nbsp;</logic:notPresent>
															</div>
														</td>
														
														<td width="10%">
															<div align="left">
																<logic:present name="itemServicoContrato" property="percentualMultaSerAplicada">
																	<%= Util.formatarMoedaReal(itemServicoContrato.getPercentualMultaSerAplicada())%>
																	</logic:present> 
																<logic:notPresent name="itemServicoContrato" property="valor">&nbsp;</logic:notPresent>
															</div>
														</td>
														
														<td width="15%">
															<div align="left">
																<logic:present name="itemServicoContrato" property="quantidadeOrcadaItemContrato">
																	<%= Util.formatarMoedaReal(itemServicoContrato.getQuantidadeOrcadaItemContrato())%>
																	</logic:present> 
																<logic:notPresent name="itemServicoContrato" property="valor">&nbsp;</logic:notPresent>
															</div>
														</td>
														
														<td width="15%">
															<div align="left">
																<logic:present name="itemServicoContrato" property="valorOrcadoItemContrato">
																	<%= Util.formatarMoedaReal(itemServicoContrato.getValorOrcadoItemContrato())%>
																	</logic:present> 
																<logic:notPresent name="itemServicoContrato" property="valor">&nbsp;</logic:notPresent>
															</div>
														</td>
													</logic:iterate>
												</table>
												</td>
											</tr>
										</table>
									</div>
								</logic:present>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="2"><hr></td>
				</tr>
				<tr>
					<td colspan="2"><strong>Aditivos:</strong></td>
				</tr>
				<tr>
					<td colspan="2">
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td>
								<table width="75%" bgcolor="#99CCFF">
									<tr bgcolor="#99CCFF">
										<td width="10%">
											<strong>Remover</strong>
										</td>
										<td width="20%">
											<strong>Data Início do Aditivo</strong>
										</td>
										<td width="20%">
											<strong>Data Fim do Aditivo</strong>
										</td>
										<td width="15%">
											<strong>Valor do Aditivo</strong>
										</td>
										<td width="20%">
											<strong>Percentual de Taxa de Sucesso</strong>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<%--scroll --%>
								<logic:present name="colecaoAditivo" scope="session">
									<div style="width: 75%; height: 80; overflow: auto;">
										<table width="100%" cellpadding="0" cellspacing="0" border="0">
											<tr>
												<td> <%cor2 = "#cbe5fe";%>
												<table width="100%" bgcolor="#99CCFF" border="0">
													<logic:iterate name="colecaoAditivo" 
												   				   id="contratoEmpresaAditivo"
												   				   type="ContratoEmpresaAditivo">
														<c:set var="count3" value="${count3+1}" />
														<c:choose>
															<c:when test="${count3%2 == '1'}">
																<tr bgcolor="#FFFFFF">
															</c:when>
															<c:otherwise>
																<tr bgcolor="#cbe5fe">
															</c:otherwise>
														</c:choose>
		
														<td align="center" width="10%">
															<img src="<bean:message key='caminho.imagens'/>Error.gif" 
																 style="cursor: hand;"
																 onclick="removerAditivo('${count3}')"
																 title="Remover" >
														</td>
													
														<td width="20%">
															<div align="left">
																<logic:present name="contratoEmpresaAditivo" property="dataInicioContrato">
																	<%= Util.formatarData(contratoEmpresaAditivo.getDataInicioContrato())%>
																	</logic:present> 
																<logic:notPresent name="contratoEmpresaAditivo" property="dataInicioContrato">&nbsp;</logic:notPresent>
															</div>
														</td>
													
														<td width="20%">
															<div align="left">
																<logic:present name="contratoEmpresaAditivo" property="dataFimContrato">
																	<%= Util.formatarData(contratoEmpresaAditivo.getDataFimContrato())%>
																	</logic:present> 
																<logic:notPresent name="contratoEmpresaAditivo" property="dataFimContrato">&nbsp;</logic:notPresent>
															</div>
														</td>
													
														<td width="15%">
															<div align="left">
																<logic:present name="contratoEmpresaAditivo" property="valorAditivoContrato">
																	<%= Util.formatarMoedaReal(contratoEmpresaAditivo.getValorAditivoContrato())%>
																	</logic:present> 
																<logic:notPresent name="contratoEmpresaAditivo" property="valorAditivoContrato">&nbsp;</logic:notPresent>
															</div>
														</td>
													
														<td width="20%">
															<div align="left">
																<logic:present name="contratoEmpresaAditivo" property="percentualTaxaSucesso">
																	<%=Util.formataBigDecimal(contratoEmpresaAditivo.getPercentualTaxaSucesso(),2,true) %>
																	
																	</logic:present> 
																<logic:notPresent name="contratoEmpresaAditivo" property="percentualTaxaSucesso">&nbsp;</logic:notPresent>
															</div>
														</td>
														</tr>
													</logic:iterate>
												</table>
												</td>
											</tr>
										</table>
									</div>
								</logic:present>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			
			<table width="100%">
				<tr>
					<td height="10" colspan="2">
						<hr>
					</td>
				</tr>
				<tr>
					<td width="40%" align="left"><input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Limpar"
						onClick="window.location.href='/gsan/exibirInformarItensContratoServicoAction.do?menu=sim'" />
					<!--  
					<input type="button" name="ButtonReset" class="bottonRightCol"
						   value="Desfazer"
						   onclick="window.location.href='/gsan/exibirInformarItensContratoServicoAction.do?desfazer=S&id=${count}';">
					-->	   
					<input type="button" name="ButtonCancelar" class="bottonRightCol"
						   value="Cancelar"
						   onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right"><input type="button" name="Button"
						class="bottonRightCol" value="Concluir"
						onclick="validarForm(document.forms[0]);" tabindex="13" />
					</td>
				</tr>
			</table>
		</tr>			
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</html:html>

