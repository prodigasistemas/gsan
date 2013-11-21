<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="gcom.cadastro.geografico.Bairro" %>
<%@ page import="gcom.cadastro.endereco.Cep" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="LogradouroActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
<!-- Begin

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.LogradouroActionForm;

    if (tipoConsulta == 'municipio') {
      form.idMunicipio.value = codigoRegistro;
      form.nomeMunicipio.value = descricaoRegistro;
      form.nomeMunicipio.style.color = "#000000";
      form.codigoBairro.value = "";
      form.nomeBairro.value = "";
      
      //Código responsável pela atualização automática da coleção
      form.action = "exibirAtualizarLogradouroAction.do?removerColecaoBairro=OK&removerColecaoCep=OK";
	  submeterFormPadrao(form);
    }

    if (tipoConsulta == 'bairro') {
      form.codigoBairro.value = codigoRegistro;
      form.nomeBairro.value = descricaoRegistro;
      form.nomeBairro.style.color = "#000000";
      
      //Código responsável pela atualização automática da coleção
      form.action = "exibirAtualizarLogradouroAction.do";
	  submeterFormPadrao(form);

    }
    
    if (tipoConsulta == 'cep') {
      /*form.codigoCEP.value = codigoRegistro;
      form.descricaoCEP.value = descricaoRegistro;
      form.descricaoCEP.style.color = "#000000";*/
      
      //Código responsável pela atualização automática da coleção
      /*form.action = "exibirAtualizarLogradouroAction.do";
	  submeterFormPadrao(form);*/
	  
	  var cdCepAtual = document.getElementById("idObjetoAlteracao").value;
      
      form.action = 'exibirAtualizarLogradouroAction.do?cdCepAtual=' + cdCepAtual + '&cdCepNovo=' + codigoRegistro;
	  submeterFormPadrao(form);

    }
  }
 
 function limparPesquisaBairro() {
    var form = document.LogradouroActionForm;

    form.codigoBairro.value = "";
    form.nomeBairro.value = "";
    
    form.action = "exibirAtualizarLogradouroAction.do?removerColecaoBairro=OK";
	submeterFormPadrao(form);

  }
  
  function limparPesquisaCep(){
  
  	var form = document.LogradouroActionForm;
  	
  	form.codigoCEP.value = "";
    form.descricaoCEP.value = "";
    
    form.action = "exibirAtualizarLogradouroAction.do?removerColecaoCep=OK";
	submeterFormPadrao(form);
  }
  
  function limparPesquisaMunicipio() {
    
    var form = document.LogradouroActionForm;

    form.idMunicipio.value = "";
    form.nomeMunicipio.value = "";
    form.codigoBairro.value = "";
    form.nomeBairro.value = "";
    form.codigoCEP.value = "";
    form.descricaoCEP.value = "";
    
    form.action = "exibirAtualizarLogradouroAction.do?removerColecaoBairro=OK&removerColecaoCep=OK";
	submeterFormPadrao(form);
	
  }

function validarForm(form){

	if(validateLogradouroActionForm(form)){
    	
    	if (!testarCampoValorZero(document.forms[0].idMunicipio, "Município")){
    		document.forms[0].idMunicipio.focus();
    	}
    	/*else if (document.getElementById("bairroCarregado") == null){
    		alert("Informe Bairro(s)");
    		document.forms[0].codigoBairro.focus();
    	}
    	else if (document.getElementById("cepCarregado") == null){
    		alert("Informe CEP(s)");
    		document.forms[0].codigoCEP.focus();
    	}*/
    	else{
    		form.action = 'atualizarLogradouroAction.do';
    		submeterFormPadrao(form);
    	}
    }
}

function adicionarObjeto(tipoSelecao){

	var form = document.forms[0];
	var objMunicipio = form.idMunicipio;
	var objBairro = form.codigoBairro; 
	var objCEP = form.codigoCEP;
	
	if (objMunicipio.value.length > 0){
		
		//Bairro
		if (tipoSelecao == "1"){
			
			abrirPopup('exibirSelecionarBairroAction.do?idMunicipio=' + objMunicipio.value + '&limparForm=OK&tipoPesquisaEndereco=logradouro&operacao=2', 450, 700);
			
		}
		//CEP
		else{
			
			abrirPopup('exibirSelecionarCepAction.do?idMunicipio=' + objMunicipio.value + '&limparForm=OK&tipoPesquisaEndereco=logradouro&operacao=2', 450, 700);
			
		}
	}
	else {
		alert("Informe Município");
		objMunicipio.focus();
	}
}


function adicionarObjetoEnter(tipoSelecao){

	var form = document.forms[0];
	var objMunicipio = form.idMunicipio;
	var objBairro = form.codigoBairro; 
	var objCEP = form.codigoCEP;


	if (objMunicipio.value.length < 1){
		alert("Informe Município");
		objMunicipio.focus();
	}
	else if (objMunicipio.value.length > 0 && (isNaN(objMunicipio.value) || objMunicipio.value.indexOf(',') != -1 ||
			 objMunicipio.value.indexOf('.') != -1)){

		alert("Município deve somente conter números positivos.");
		objMunicipio.focus();
	}
	else if (!testarCampoValorZero(document.forms[0].idMunicipio, "Município")){
    	document.forms[0].idMunicipio.focus();
    }
    else {
    
    	//Bairro
		if (tipoSelecao == "1" && objBairro.value.length > 0){
		
			if (!testarCampoValorZero(objBairro, "Bairro(s)")){
				objBairro.focus();
			}
			else if (objBairro.value.length > 0 && (isNaN(objBairro.value) || objBairro.value.indexOf(',') != -1 ||
					objBairro.value.indexOf('.') != -1)){

				alert("Bairro(s) deve somente conter números positivos.");
				objBairro.focus();
			}
			else{
				form.action = "exibirAtualizarLogradouroAction.do?adicionarBairroColecao=OK";
			submeterFormPadrao(form);
			}
		}
		
		//CEP
		else if (tipoSelecao == "2" && objCEP.value.length > 0){
			
			if (!testarCampoValorZero(objCEP, "CEP(s)")){
				objCEP.focus();
			}
			else if (objCEP.value.length > 0 && (isNaN(objCEP.value) || objCEP.value.indexOf(',') != -1 ||
					objCEP.value.indexOf('.') != -1)){

				alert("CEP(s) deve somente conter números positivos.");
				objCEP.focus();
			}
			else{
				form.action = "exibirAtualizarLogradouroAction.do?adicionarCepColecao=OK";
				submeterFormPadrao(form);
			}
			
		}
    }
}



function remover(idObj, idLog,tipoRemocao){

	var form = document.forms[0];
	
	if (confirm('Confirma Remoção?')){
		if (tipoRemocao == "1"){
			form.action = "exibirAtualizarLogradouroAction.do?idBairro=" + idObj +"&idLogradouro="+ idLog ;
		}
		else{
			form.action = "exibirAtualizarLogradouroAction.do?idCep=" + idObj +"&idLogradouro="+ idLog ;
		}
		submeterFormPadrao(form);
	}
}


function selecionarNovoObjetoBairro(idBairroAtual){

	document.getElementById("idObjetoAlteracao").value = idBairroAtual;
	abrirPopup('exibirPesquisarBairroAction.do?tipo=bairro', 410, 790);
}

function selecionarNovoObjetoCep(cdCepAtual){

	document.getElementById("idObjetoAlteracao").value = cdCepAtual;
	abrirPopup('exibirPesquisarCepAction.do', 250, 600);
}

function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

	var form = document.forms[0];

	if (tipoConsulta == 'bairro') {
      
      var idBairroAtual = document.getElementById("idObjetoAlteracao").value;
      
      form.action = 'exibirAtualizarLogradouroAction.do?idBairroAtual=' + idBairroAtual + '&idBairroNovo=' + idRegistro;
	  submeterFormPadrao(form);
	}

}

-->
</script>
</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<INPUT TYPE="hidden" ID="idObjetoAlteracao" NAME="idObjetoAlteracao">

<html:form action="/atualizarLogradouroAction.do"
	name="LogradouroActionForm"
	type="gcom.gui.cadastro.endereco.LogradouroActionForm" method="post"
	onsubmit="return validateLogradouroActionForm(this);">
	
	

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	<!-- Hiddens pra informar se o bairro ou cep esta preenchido -->
	<html:hidden property="colecaoBairro" />
	<html:hidden property="colecaoCep" />
	
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

					<td class="parabg">Atualizar Logradouro</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para atualizar um logradouro, informe os dados
					abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroEnderecoLogradouroAtualizar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
			</table>
			
			<table width="100%" border="0">
				<tr>
					<td height="0"><strong>Código:</strong></td>
					<td colspan="2"><strong><bean:write name="logradouro" property="id"/></strong></td>
				</tr>
				<tr>
					<td height="0"><strong>Tipo:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:select property="idTipo" tabindex="1" style="width: 150px;">
						<html:option value="">&nbsp;</html:option>
						<html:options collection="logradouroTipos"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Título:</strong></td>
					<td colspan="2"><html:select property="idTitulo" tabindex="2" style="width: 150px;">
						<html:option value="">&nbsp;</html:option>
						<html:options collection="logradouroTitulos"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td width="15%"><strong>Nome:<font color="#FF0000">*</font></strong></td>
					<td width="85%" colspan="2"><html:text maxlength="40" property="nome" size="50"
						tabindex="3" /></td>
				</tr>
				<tr>
					<td width="15%"><strong>Nome Popular:</strong></td>
					<td width="85%" colspan="2"><html:text maxlength="30" property="nomePopular" size="40"
						tabindex="4" /></td>
				</tr>
				<tr>
					<td colspan="3" height="10"></td>
				</tr>
				<tr>
					<td><strong>Munic&iacute;pio:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text maxlength="4" property="idMunicipio" size="4"
						tabindex="5"
						onkeypress="return validaEnter(event, 'exibirAtualizarLogradouroAction.do?removerColecaoBairro=OK&removerColecaoCep=OK', 'idMunicipio');return isCampoNumerico(event);"/>
					<a
						href="javascript:abrirPopup('exibirPesquisarMunicipioAction.do');">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Municipio" /></a> <logic:present
						name="idMunicipioNaoEncontrado">
						<logic:equal name="idMunicipioNaoEncontrado" value="exception">
							<html:text property="nomeMunicipio" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="idMunicipioNaoEncontrado" value="exception">
							<html:text property="nomeMunicipio" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="idMunicipioNaoEncontrado">
						<logic:empty name="LogradouroActionForm" property="idMunicipio">
							<html:text property="nomeMunicipio" value="" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="LogradouroActionForm" property="idMunicipio">
							<html:text property="nomeMunicipio" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent>
					<a href="javascript:limparPesquisaMunicipio();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Bairro(s):<font color="#FF0000">*</font></strong></td>
					<td><html:text maxlength="4" property="codigoBairro" size="4"
						tabindex="6"
						onkeypress="javascript:return validaEnterDependencia(event, 'exibirAtualizarLogradouroAction.do', this,document.forms[0].idMunicipio.value,'Município');return isCampoNumerico(event);" />
					
					
						<a
							href="javascript:adicionarObjeto('1');">
						<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Selecionar Bairro" /></a> 
							
					<logic:present
							name="idBairroNaoEncontrado">
						<logic:equal name="idBairroNaoEncontrado" value="exception">
							<html:text property="nomeBairro" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="idBairroNaoEncontrado" value="exception">
							<html:text property="nomeBairro" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="idBairroNaoEncontrado">
						<logic:empty name="LogradouroActionForm" property="idMunicipio">
							<html:text property="nomeBairro" value="" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="LogradouroActionForm" property="idMunicipio">
							<html:text property="nomeBairro" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> 
					
						<logic:notPresent name="bloquearLinkBairro" scope="request">
							<a href="javascript:limparPesquisaBairro();"> <img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /></a>
						</logic:notPresent>
						
						<logic:present name="bloquearLinkBairro" scope="request">
							<a href="javascript:alert('Usuário não tem permissão especial para alterar o bairro.');"> <img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /></a>
						</logic:present>
						
						</td>
						
						<td align="right">
						
						
							<input type="button" class="bottonRightCol" value="Adicionar" tabindex="7" style="width: 80px; align: right;" onclick="adicionarObjetoEnter('1');" name="botaoAdicionarBairro">
					
						</td>
				</tr>
				<tr>
					<td colspan="3">
					
						<table width="100%" cellpadding="0" cellspacing="0">
						<tr> 
			                <td> 
								
								<table width="100%" bgcolor="#99CCFF">
			                    <tr bgcolor="#99CCFF"> 
									<td align="center" width="10%">&nbsp;</td>
									<td width="90%"><div align="center"><strong>Bairro</strong></div></td>
			                    </tr>
			                    </table>
							</td>
			            </tr>
			            </table>
			            
			            <% String cor = "#cbe5fe";%>
			            
			            <logic:present name="colecaoBairrosSelecionadosUsuario" scope="session">

						<div style="width: 100%; height: 100; overflow: auto;">
					
								<table width="100%" cellpadding="0" cellspacing="0" id="bairroCarregado">
					            <tr> 
									<td> 
										
											<% cor = "#cbe5fe";%>
										
											<table width="100%" align="center" bgcolor="#99CCFF">
					
											<logic:iterate name="colecaoBairrosSelecionadosUsuario" id="bairro" type="Bairro">
					                            
												
												<%	if (cor.equalsIgnoreCase("#FFFFFF")){
														cor = "#cbe5fe";%>
														<tr bgcolor="#cbe5fe">
												<%} else{
														cor = "#FFFFFF";%>
														<tr bgcolor="#FFFFFF">
												<%}%> 
												
													<td align="center" width="10%">
														
														<logic:notPresent name="bloquearLinkBairro" scope="request">
															<img src="<bean:message key='caminho.imagens'/>Error.gif" onclick="remover(<%="" + bairro.getId().intValue()%>, <bean:write name="logradouro" property="id"/>, '1')" title="Remover" style="cursor: hand;">
														</logic:notPresent>
														
														<logic:present name="bloquearLinkBairro" scope="request">
															<img src="<bean:message key='caminho.imagens'/>Error.gif" onclick="alert('Usuário não tem permissão especial para alterar o bairro.');" title="Remover" style="cursor: hand;">
														</logic:present>
													</td>
													<td width="90%">
														<div align="left">
														
														<logic:present name="bairro" property="nome">
																
																<logic:notPresent name="bloquearLinkBairro" scope="request">
																	<a href="javaScript:selecionarNovoObjetoBairro(<%="" + bairro.getId()%>);" title="<%="" + bairro.getNome()%>"><bean:write name="bairro" property="nome"/></a>
																</logic:notPresent>
																
																<logic:present name="bloquearLinkBairro" scope="request">
																	<a href="javaScript:alert('Usuário não tem permissão especial para alterar o bairro.');" title="<%="" + bairro.getNome()%>"><bean:write name="bairro" property="nome"/></a>
																</logic:present>
																
														</logic:present>
														<logic:notPresent name="bairro" property="nome">&nbsp;</logic:notPresent>
														
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
				<tr>
					<td colspan="3" height="20"></td>
				</tr>
				<tr>
					<td><strong>CEP(s):<font color="#FF0000">*</font></strong></td>
					<td>
						<logic:present name="cepUnico" scope="request">
							<html:text maxlength="8" property="codigoCEP" size="10" tabindex="8" disabled="true" 
							onkeypress="return isCampoNumerico(event);"/>
						</logic:present>
						
						<logic:notPresent name="cepUnico" scope="request">
							<html:text maxlength="8" property="codigoCEP" size="10" tabindex="8"
								onkeypress="javascript:return validaEnterDependencia(event, 'exibirAtualizarLogradouroAction.do', this,document.forms[0].idMunicipio.value,'Município');return isCampoNumerico(event);" />
						</logic:notPresent>
						
						
						<logic:present name="cepUnico" scope="request">
							<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Selecionar CEP"/>
						</logic:present>
						<logic:notPresent name="cepUnico" scope="request">
							<a href="javascript:adicionarObjeto('2');" alt="Selecionar CEP">
							<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Selecionar CEP"/></a>
						</logic:notPresent>
						
						
						<logic:present name="idCEPNaoEncontrado">
						<logic:equal name="idCEPNaoEncontrado" value="exception">
							<html:text property="descricaoCEP" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="idCEPNaoEncontrado" value="exception">
							<html:text property="descricaoCEP" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
						</logic:present> <logic:notPresent name="idCEPNaoEncontrado">
						<logic:empty name="LogradouroActionForm" property="idMunicipio">
							<html:text property="descricaoCEP" value="" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="LogradouroActionForm" property="idMunicipio">
							<html:text property="descricaoCEP" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
						</logic:notPresent> 
					
						
						<logic:present name="cepUnico" scope="request">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" />
						</logic:present>
							
						<logic:notPresent name="cepUnico" scope="request">
							<a href="javascript:limparPesquisaCep();"> <img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /></a>
						</logic:notPresent>
						
						</td>
						
						<td align="right">
						
							<logic:present name="cepUnico" scope="request">
								<input type="button" class="bottonRightCol" value="Adicionar" tabindex="9" style="width: 80px; align: right;" disabled="true">
							</logic:present>
							
							<logic:notPresent name="cepUnico" scope="request">
								<input type="button" class="bottonRightCol" value="Adicionar" tabindex="9" style="width: 80px; align: right;" onclick="adicionarObjetoEnter('2');" name="botaoAdicionarCep">
							</logic:notPresent>
						
						</td>
				</tr>
				<tr>
					<td colspan="3">
					
						<table width="100%" cellpadding="0" cellspacing="0">
						<tr> 
			                <td> 
								
								<table width="100%" bgcolor="#99CCFF">
			                    <tr bgcolor="#99CCFF"> 
									<td align="center" width="10%"></td>
									<td width="25%"><div align="center"><strong>Logradouro</strong></div></td>
                        			<td width="20%"><div align="center"><strong>Bairro</strong></div></td>
                        			<td width="20%"><div align="center"><strong>Município</strong></div></td>
									<td width="10%"><div align="center"><strong>UF</strong></div></td>
									<td width="15%"><div align="center"><strong>CEP</strong></div></td>
			                    </tr>
			                    </table>
								
							</td>
			            </tr>
			            </table>
			            
			            
			            <logic:present name="colecaoCepSelecionadosUsuario" scope="session">

						<div style="width: 100%; height: 100; overflow: auto;">
			
						<table width="100%" cellpadding="0" cellspacing="0" id="cepCarregado">
			            <tr> 
							<td> 
								
									<% cor = "#cbe5fe";%>
								
									<table width="100%" align="center" bgcolor="#99CCFF">
			
									<logic:iterate name="colecaoCepSelecionadosUsuario" id="cep" type="Cep">
			                            
										
										<%	if (cor.equalsIgnoreCase("#FFFFFF")){
												cor = "#cbe5fe";%>
												<tr bgcolor="#cbe5fe">
										<%} else{
												cor = "#FFFFFF";%>
												<tr bgcolor="#FFFFFF">
										<%}%> 
										
											<td align="center" width="10%">
												<img src="<bean:message key='caminho.imagens'/>Error.gif" onclick="remover(<%="" + cep.getCepId().intValue()%>, <bean:write name="logradouro" property="id"/>, '2')" title="Remover" style="cursor: hand;">
											<td width="25%">
												<div align="left">
			
												<logic:present name="cep" property="descricaoLogradouroFormatada">
													<a href="javaScript:selecionarNovoObjetoCep(<%="" + cep.getCodigo() %>);" title="<%="" + cep.getDescricaoLogradouroFormatada() %>"><bean:write name="cep" property="descricaoLogradouroFormatada"/></a>
												</logic:present>
												<logic:notPresent name="cep" property="descricaoLogradouroFormatada">&nbsp;</logic:notPresent>
			
												</div>
											</td>
											<td width="20%">
												<div align="left">
												
												<logic:present name="cep" property="bairro">
														<bean:write name="cep" property="bairro"/>
												</logic:present>
												<logic:notPresent name="cep" property="bairro">&nbsp;</logic:notPresent>
												
												</div>
											</td>
											
											<td width="20%">
												<div align="left">
												
												<logic:present name="cep" property="municipio">
														<bean:write name="cep" property="municipio"/>
												</logic:present>
												<logic:notPresent name="cep" property="municipio">&nbsp;</logic:notPresent>	
												
												</div>
											</td>
			
											<td width="10%">
												<div align="left">
												
												<logic:present name="cep" property="sigla">
														<bean:write name="cep" property="sigla"/>
												</logic:present>
												<logic:notPresent name="cep" property="sigla">&nbsp;</logic:notPresent>	
												
												</div>
											</td>
			
											<td width="15%">
												<div align="center">
												
												<logic:present name="cep" property="codigo">
														<bean:write name="cep" property="cepFormatado"/>
												</logic:present>
												<logic:notPresent name="cep" property="codigo">&nbsp;</logic:notPresent>	
												
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
				<tr>
					<td>&nbsp;</td>
					<td colspan="2"><strong><font color="#FF0000">*</font></strong> Campos
					obrigat&oacute;rios</td>
				</tr>
				<tr>
					<td colspan="3" height="20"></td>
				</tr>
				<tr>
					<td colspan="2">
						<logic:present scope="session" name="manter">
						<input name="Submit222"
						class="bottonRightCol" value="Voltar" type="button"
						onclick="window.location.href='/gsan/filtrarLogradouroAction.do';">
						</logic:present>
						<logic:notPresent scope="session" name="manter">
						<input name="Submit222"
						class="bottonRightCol" value="Voltar" type="button"
						onclick="window.location.href='/gsan/exibirFiltrarLogradouroAction.do';">
						</logic:notPresent>
						
						<input type="button" name="Button" class="bottonRightCol"
						value="Desfazer" tabindex="10"
						onClick="javascript:window.location.href='/gsan/exibirAtualizarLogradouroAction.do?menu=sim&idRegistroAtualizacao=${sessionScope.logradouro.id}'" style="width: 80px" />&nbsp;
						<input type="button" name="Button" class="bottonRightCol"
						value="Cancelar" tabindex="11"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'" style="width: 80px" />
					</td>
					<td align="right">
					<gsan:controleAcessoBotao name="Button" value="Atualizar"
							  onclick="javascript:validarForm(document.forms[0]);" url="atualizarLogradouroAction.do"/>
					
					<%--	<input type="button" name="Button" class="bottonRightCol"
						value="Atualizar" tabindex="12" style="width: 80px"
						onClick="javascript:validarForm(document.forms[0])" /> --%>
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

