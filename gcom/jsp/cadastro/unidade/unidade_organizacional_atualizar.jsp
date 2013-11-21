<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@page import="gcom.cadastro.geografico.Municipio"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript"src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(form){
		if(validateAtualizarUnidadeOrganizacionalActionForm(form)){
			if(validaTodosRadioButton()){		     
				form.action = 'atualizarUnidadeOrganizacionalAction.do';
		  		submeterFormPadrao(form);
		  			
   	    	}
		}
	}
  function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
     var form = document.AtualizarUnidadeOrganizacionalActionForm;
       
       if (tipoConsulta == 'idUnidadeSuperior'){
	      limparUnidadeSuperior();
	      form.idUnidadeSuperior.value = codigoRegistro;
	      form.descricaoUnidadeSuperior.value = descricaoRegistro;
	      form.descricaoUnidadeSuperior.style.color = "#000000"
	 
	   } else  if (tipoConsulta == 'municipio') {
		     
		      form.idMunicipio.value = codigoRegistro;
		      form.descricaoMunicipio.value = descricaoRegistro;
		      form.descricaoMunicipio.style.color = "#000000";
  		}else if(tipoConsulta == 'unidadeOrganizacional'){
  		  limparUnidadeSuperior();
	      form.idUnidadeSuperior.value = codigoRegistro;
	      form.descricaoUnidadeSuperior.value = descricaoRegistro;
  		}
	}
	function limparUnidadeSuperior() {
	 var form = document.AtualizarUnidadeOrganizacionalActionForm;
    	form.idUnidadeSuperior.value = "";
    	form.descricaoUnidadeSuperior.value = "";
	}	
	
	 function validaRadioButton(nomeCampo,mensagem){
		
		var alerta = "";
		if(!nomeCampo[0].checked && !nomeCampo[1].checked){
			alerta = "Informe " + mensagem +".";
		}
		return alerta;
   	}
   
  	function validaTodosRadioButton(){
		
		var form = document.forms[0];
		var mensagem = "";
		
		if(validaRadioButton(form.unidadeEsgoto,"Unidade de Esgoto") != ""){
			mensagem = mensagem + validaRadioButton(form.unidadeEsgoto,"Unidade de Esgoto")+"\n";
		}
		
		if(validaRadioButton(form.unidadeAbreRegistro,"Unidade Abre Registro de Atendimento") != ""){
			mensagem = mensagem + validaRadioButton(form.unidadeAbreRegistro,"Unidade Abre Registro de Atendimento")+"\n";
		}
		
		if(validaRadioButton(form.unidadeAceita,"Unidade Aceita Tramitação") != ""){
			mensagem = mensagem + validaRadioButton(form.unidadeAceita,"Unidade Aceita Tramitação")+"\n";
		}
		
		if(mensagem != ""){
			alert(mensagem);
			return false;
		}else{
			return true;
		}
   	}
   	
   	 function pesquisarMunicipio(){
		var form = document.forms[0];
		if(form.idMunicipio.disabled == false){
			abrirPopup('exibirPesquisarMunicipioAction.do?indicadorUsoTodos=1', 400, 800);
		}
	}
	
	function limparMunicipio() {
		var form = document.forms[0];

		form.idMunicipio.value = "";
		form.descricaoMunicipio.value = "";

	}
	/* Adiciona componente da Colecao Municipio*/
	function incluirObjeto(){
		var form = document.forms[0];

		if ( form.idMunicipio.value != null && form.idMunicipio.value != "" ) {

			form.action = 'exibirAtualizarUnidadeOrganizacionalAction.do?acao=adicionar';
			form.submit();
		} else {
			alert("Informe o Município");
		}
	}
	
	/* Remove componente da Colecao Municipio*/
	function remover(obj){
		var form = document.forms[0];
		
		if (confirm('Confirma Remoção?')) {
			form.action = 'exibirAtualizarUnidadeOrganizacionalAction.do?acao=remover&id='+obj;
			form.submit();
		}
	} 
</script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AtualizarUnidadeOrganizacionalActionForm" />

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/atualizarUnidadeOrganizacionalAction.do"
	name="AtualizarUnidadeOrganizacionalActionForm"
	type="gcom.gui.cadastro.unidade.AtualizarUnidadeOrganizacionalActionForm"
	method="post"
	onsubmit="return validateAtualizarUnidadeOrganizacionalActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellpadding="0" cellspacing="5">
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
			<td valign="top" class="centercoltext">
			<table width="100%" height="100%" border="0">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img
						src="<bean:message key="caminho.imagens"/>parahead_left.gif"
						border="0" /></td>
					<td class="parabg">Atualizar Unidade Organizacional</td>
					<td width="11" valign="top"><img
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para atualizar uma unidade organizacional, informe
					os dados gerais abaixo:</td>
				</tr>
				<tr bgcolor="#cbe5fe">
					<td align="center">
					<table width="100%" border="0" bgcolor="#99CCFF">
						<tr bgcolor="#99CCFF">
							<td colspan="2">
							<div align="center"><span class="style2"> Dados da Unidade
							Organizacional </span></div>
							</td>
						</tr>

						<tr bgcolor="#cbe5fe">
							<td>
							<table border="0" width="100%">
								<tr>
									<td height="29"><strong>Unidade Organizacional:</strong></td>
									<td><html:text  property="id"
													style="background-color:#EFEFEF; border:0; color: #000000"
													size="5" 
													maxlength="4" 
													readonly="true" 
													onkeypress="return isCampoNumerico(event);"/>
									</td>
								</tr>
								<tr>
									<td><strong> Tipo da Unidade:</strong> <span class="style2"> <strong>
									<font color="#FF0000">*</font> </strong> </span></td>

									<td><strong> 
										<html:select property="unidadeTipo" onchange="javascript:validarUnidadeTipo();" disabled="true" style="background-color:#EFEFEF; border:0; color: #000000">
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<logic:present name="colecaoUnidadeTipo" scope="session">
											<html:options collection="colecaoUnidadeTipo" labelProperty="descricao" property="id" />
										</logic:present>
										</html:select> 
									</strong></td>
								</tr>

								<tr>
									<td><strong>Localidade:</strong></td>

									<td><html:text maxlength="3" tabindex="1"
										property="idLocalidade" size="3" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />

									<html:text property="descricaoLocalidade" maxlength="30"
										size="33" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
									</td>
								</tr>
								<tr>
									<td><strong>Unidade de Negocio:</strong></td>
									<td><html:text maxlength="40" property="unidadeNegocio"
										tabindex="1" size="40" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
									</td>
								</tr>
								<tr>
									<td><strong>Gerência Regional:</strong></td>
									<td><html:text maxlength="40" property="gerenciaRegional"
										tabindex="1" size="40" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
									</td>
								</tr>
								<tr>
									<td><strong>Descri&ccedil;&atilde;o</strong> <span
										class="style2"> <strong> <font color="#FF0000">*</font> </strong>
									</span></td>
									<logic:present name="naomodificar" scope="session">
										<td><html:text maxlength="80" property="descricao"
											tabindex="1" size="40" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
										</td>
									</logic:present>
									<logic:notPresent name="naomodificar" scope="session">
										<td><html:text maxlength="80" property="descricao"
											tabindex="1" size="40" /></td>
									</logic:notPresent>
								</tr>
								<tr>
									<td><strong>Sigla:</strong></td>
									<logic:present name="naomodificar" scope="session">
										<td><html:text maxlength="5" property="sigla" tabindex="1"
											size="5" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
										</td>
									</logic:present>
									<logic:notPresent name="naomodificar" scope="session">
										<td><html:text maxlength="5" property="sigla" tabindex="1"
											size="5" /></td>
									</logic:notPresent>
								</tr>

								<tr>
									<td><strong>Empresa:</strong> <span class="style2"> <strong> <font
										color="#FF0000">*</font> </strong> </span></td>
									<logic:present name="naoModificarEmpresa" scope="session">
										<td><html:hidden property="idEmpresa" />
										<html:text maxlength="40" property="empresa" tabindex="1"
											size="40" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
										</td>
									</logic:present>
									<logic:notPresent name="naoModificarEmpresa" scope="session">
										<td><strong> <html:select property="idEmpresa">
											<html:option
												value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
													</html:option>

											<logic:present name="colecaoEmpresa" scope="session">
												<html:options collection="colecaoEmpresa"
													labelProperty="descricao" property="id" />
											</logic:present>
										</html:select> </strong></td>
									</logic:notPresent>
								</tr>
								<tr>
									<td><strong>Unidade Superior:</strong></td>
									<td width="63%"><html:text property="idUnidadeSuperior"
										tabindex="11" size="4" maxlength="4"
										onkeypress="validaEnterComMensagem(event, 'exibirAtualizarUnidadeOrganizacionalAction.do?consultaUnidadeSuperior=1', 'idUnidadeSuperior', 'Unidade Superior');return isCampoNumerico(event);" />
									<a
										href="javascript:abrirPopup('exibirPesquisarUnidadeOrganizacionalAction.do', 250, 495);">
									<img src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										border="0" width="23" height="21" title="Pesquisar Unidade Superior"></a> <logic:present
										name="corUnidadeSuperior">
										<logic:equal name="corUnidadeSuperior" value="exception">
											<html:text property="descricaoUnidadeSuperior" size="40"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:equal>
										<logic:notEqual name="corUnidadeSuperior" value="exception">
											<html:text property="descricaoUnidadeSuperior" size="40"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEqual>
									</logic:present> 
									<logic:notPresent name="corUnidadeSuperior">
										<logic:empty name="AtualizarUnidadeOrganizacionalActionForm"
											property="idUnidadeSuperior">
											<html:text property="descricaoUnidadeSuperior" size="40"
												value="" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:empty>
										<logic:notEmpty name="AtualizarUnidadeOrganizacionalActionForm"
											property="idUnidadeSuperior">
											<html:text property="descricaoUnidadeSuperior" size="40"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEmpty>
									</logic:notPresent> <a
										href="javascript:limparUnidadeSuperior();"> <img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" /></a></td>
								</tr>
								<tr>
									<td><strong> Unidade Centralizadora:</strong></td>

									<td>
										<strong> 
										<html:select property="idUnidadeCentralizadora" style="width: 260px;" >
											<html:option 
												value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
											</html:option>
	
											<logic:present name="colecaoUnidadeCentralizadora" scope="session">
												<html:options collection="colecaoUnidadeCentralizadora"
															  labelProperty="descricao" 
															  property="id"  />
											</logic:present>
										</html:select> 
										</strong>
									</td>
								</tr>
								
								<tr>
									<td><strong> Unidade Repavimentadora:</strong></td>

									<td><strong> <html:select property="idUnidadeRepavimentadora">
										<html:option
											value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
													</html:option>

										<logic:present name="colecaoUnidadeRepavimentadora"
											scope="session">
											<html:options collection="colecaoUnidadeRepavimentadora"
												labelProperty="descricao" property="id" />
										</logic:present>
									</html:select> </strong></td>
								</tr>
								
								

								<tr>
									<td><strong>Unidade de Esgoto?</strong> <span class="style2"> <strong>
									<font color="#FF0000">*</font> </strong> </span></td>
									<td><html:radio property="unidadeEsgoto"
										value="<%=""+ConstantesSistema.SIM%>" /> <strong>Sim</strong>
									<html:radio property="unidadeEsgoto"
										value="<%=""+ConstantesSistema.NAO%>" /> <strong>N&atilde;o</strong>
									</td>
								</tr>

								<tr>
									<td><strong>Unidade Abre Registro de Atendimento?</strong> <span
										class="style2"> <strong> <font color="#FF0000">*</font> </strong>
									</span></td>
									<td><html:radio property="unidadeAbreRegistro"
										value="<%=""+ConstantesSistema.SIM%>"
										onclick="javascript:verificaIndicadorAbreRegistro();" /> <strong>Sim</strong>
									<html:radio property="unidadeAbreRegistro"
										value="<%=""+ConstantesSistema.NAO%>"
										onclick="javascript:verificaIndicadorAbreRegistro();" /> <strong>N&atilde;o</strong>
									</td>
								</tr>

								<tr>
									<td><strong>Unidade Aceita Tramita&ccedil;&atilde;o?</strong> <span
										class="style2"> <strong> <font color="#FF0000">*</font> </strong>
									</span></td>
									<td><html:radio property="unidadeAceita"
										value="<%=""+ConstantesSistema.SIM%>" /> <strong>Sim</strong>
									<html:radio property="unidadeAceita"
										value="<%=""+ConstantesSistema.NAO%>" /> <strong>N&atilde;o</strong>
									</td>
								</tr>
								<tr>
									<td><strong> Meio de Solicita&ccedil;&atilde;o Padr&atilde;o: <span
										class="style2"> <strong> <font color="#FF0000">*</font> </strong>
									</span> </strong></td>

									<td><strong> <html:select property="meioSolicitacao"
										style="width: 260px;">
										<html:option
											value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
													</html:option>

										<logic:present name="colecaoMeioSolicitacao" scope="session">
											<html:options collection="colecaoMeioSolicitacao"
												labelProperty="descricao" property="id" />
										</logic:present>
									</html:select> </strong></td>
								</tr>
								<tr>
									<td><strong>Indicador de Uso:</strong></td>
									<td align="right">
									<div align="left"><html:radio property="indicadorUso"
										tabindex="3"
										value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>" />
									<strong>Ativo</strong> <html:radio property="indicadorUso"
										tabindex="4"
										value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>" />
									<strong>Inativo</strong></div>
									</td>
								</tr>
								
								<tr>
									<td height="24">
										<strong>Município:</strong>
									</td>
									<td colspan="2">
										<html:text  maxlength="4" 
													tabindex="7"
													property="idMunicipio" 
													size="4"
													onkeypress="javascript:validaEnter(event, 'exibirAtualizarUnidadeOrganizacionalAction.do?objetoConsulta=1', 'idMunicipio');return isCampoNumerico(event);" />
										<a href="javascript:pesquisarMunicipio();">
											<img width="23" height="21" border="0"
												src="<bean:message key="caminho.imagens"/>pesquisa.gif"
												title="Pesquisar Município" />
										</a> 
										<logic:present name="idMunicipio">
											<logic:equal name="idMunicipio" value="exception">
												<html:text  property="descricaoMunicipio" 
															size="35" 
															maxlength="35"
															readonly="true"
															style="background-color:#EFEFEF; border:0; color: #ff0000" />
											</logic:equal>
											<logic:notEqual name="idMunicipio" value="exception">
												<html:text  property="descricaoMunicipio" 
															size="35" 
															maxlength="35"
															readonly="true"
															style="background-color:#EFEFEF; border:0; color: #000000" />
											</logic:notEqual>
										</logic:present> 
										<logic:notPresent name="idMunicipio">
											<logic:empty name="AtualizarUnidadeOrganizacionalActionForm" property="idMunicipio">
												<html:text property="descricaoMunicipio" value="" 
													size="35"
													maxlength="35" readonly="true"
													style="background-color:#EFEFEF; border:0; color: #ff0000" />
											</logic:empty>
											<logic:notEmpty name="AtualizarUnidadeOrganizacionalActionForm" property="idMunicipio">
												<html:text  property="descricaoMunicipio" 
															size="35" 
															maxlength="35"
															readonly="true"
															style="background-color:#EFEFEF; border:0; color: #000000" />
											</logic:notEmpty>
										</logic:notPresent>
										<a href="javascript:limparMunicipio();"> 
											<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
												 border="0" 
												 title="Apagar" />
										</a>
									</td>
								</tr>
								
								<tr>
									<td><strong>Município(s):</strong></td>
									<td colspan="2" align="right">
											<input  type="button" class="bottonRightCol" 
													value="Adicionar"
													tabindex="25" 
													style="width: 80px; align: right;"
													onclick="javascript:incluirObjeto();" 
													name="botaoAdicionarMunicipio">
									</td>
								</tr>
								<tr>
									
									<table width="100%" align="center"   >
										<tr>
											<td>
											<table width="100%" bgcolor="#99CCFF">
												<tr bgcolor="#99CCFF">
													<td width="10%">
														<div align="left"><strong>Remover</strong></div>
													</td>
													<td width="20%">
														<div align="center"><strong>Código</strong></div>
													</td>
													<td width="70%">
														<div align="center"><strong>Descrição</strong></div>
													</td>
													
												</tr>
											</table>
											</td>
										</tr>
				
										<tr>
											<td>
									
											<%String cor = "#FFFFFF";%> 
											<logic:present name="colecaoMunicipioSelecionado" scope="session">
												<div style="width: 100%; height: 100; overflow: auto;">
												<table width="100%" cellpadding="0" cellspacing="0">
													<tr>
														<td><%cor = "#cbe5fe";%>
						
														<table width="100%" align="center" bgcolor="#99CCFF">
															<logic:iterate name="colecaoMunicipioSelecionado" 
																id="municipio"
																type="Municipio">
																<c:set var="count" value="${count+1}" />
																<c:choose>
																	<c:when test="${count%2 == '1'}">
																		<tr bgcolor="#FFFFFF">
																	</c:when>
																	<c:otherwise>
																		<tr bgcolor="#cbe5fe">
																	</c:otherwise>
																</c:choose>
						
																	<td align="center" width="10%">
																		<img
																		src="<bean:message key='caminho.imagens'/>Error.gif"
																		onclick="remover('${count}')"
																		title="Remover" style="cursor: hand;">
																		
																			
																	<td width="20%">
																		<div align="center">
																			<logic:present name="municipio" property="id">
																				<bean:write name="municipio" property="id" />
																			</logic:present> 
						
																			<logic:notPresent name="municipio" property="id">&nbsp;</logic:notPresent>
																		</div>
																	</td>
																	
																	<td width="70%">
																		<div align="center">
																			<logic:present name="municipio" property="nome">
																				<bean:write name="municipio" property="nome" />
																			</logic:present> 
																			<logic:notPresent name="municipio" property="nome">&nbsp;</logic:notPresent>
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
									
								</tr>
					</table>
					</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td align="center" colspan="2">
					<div align="center"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>
					<td><logic:present scope="session" name="manter">
						  <input name="Submit222" class="bottonRightCol" value="Voltar"
							type="button"
							onclick="window.location.href='<html:rewrite page="/exibirManterUnidadeOrganizacionalAction.do"/>'">
					    </logic:present> 
					    <logic:notPresent scope="session" name="manter">
						  <logic:present scope="session" name="filtrar_manter">
							 <input name="Submit222" class="bottonRightCol" value="Voltar"
								type="button" onclick="javascript:history.back();">
						  </logic:present>
						  <logic:notPresent scope="session" name="filtrar_manter">
							<input name="Submit222" class="bottonRightCol" value="Voltar"
								type="button"
								onclick="window.location.href='/gsan/exibirFiltrarUnidadeOrganizacionalAction.do?menu=sim';">
						  </logic:notPresent>
					   </logic:notPresent> 
					<input name="Submit22" class="bottonRightCol"
						value="Desfazer" type="button"
						onclick="window.location.href='/gsan/exibirAtualizarUnidadeOrganizacionalAction.do?idRegistroAtualizacao=<bean:write name="AtualizarUnidadeOrganizacionalActionForm" property="id" />&desfazer=S';">
					<input name="Submit23" class="bottonRightCol" value="Cancelar"
						type="button"
						onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
					<td align="right"><gsan:controleAcessoBotao name="Button"
						value="Atualizar"
						onclick="javascript:validarForm(document.AtualizarUnidadeOrganizacionalActionForm);"
						url="atualizarUnidadeOrganizacionalAction.do" />
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
