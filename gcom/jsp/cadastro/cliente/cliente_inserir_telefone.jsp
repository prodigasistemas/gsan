<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.gui.GcomAction"%>
<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<head>
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ClienteActionForm" dynamicJavascript="false"	/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script>
<!--
var bCancel = false;
function validateClienteActionForm(form) {
	if (bCancel)
		return true;
	else
   		return validateLong(form) 
   		&& validateCaracterEspecial(form)
   		&& verificarTamanhoTelefone(); 
}

function validaTelefone() {
	var form = document.forms[0];
	var msg = '';
	if( form.idTipoTelefone.value  == -1 ) {
		msg = 'Informe Tipo Telefone.\n';
	}
	if( form.ddd.value == '' ) {
		msg = msg + 'Informe DDD.\n';
	}
	if( form.telefone.value == '' ) {
		msg = msg + 'Informe Número do Telefone.';
	}
	if( msg != '' ){
		alert(msg);
		return false;
	}else{
		var tamanho = document.forms[0].idTipoTelefone[document.forms[0].idTipoTelefone.selectedIndex].text.indexOf('-');
		var descricao = document.forms[0].idTipoTelefone[document.forms[0].idTipoTelefone.selectedIndex].text;
		
		document.forms[0].textoSelecionado.value = descricao.substring(tamanho+2);document.forms[0].botaoClicado.value = 'Adicionar';return redirecionarSubmitComValidacao('inserirClienteWizardAction.do?action=exibirInserirClienteTelefoneAction');
		return true;
	}
}

function IntegerValidations () {
	this.ab = new Array("idMunicipio", "Município deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.ac = new Array("ddd", "DDD deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.ad = new Array("telefone", "Número do Telefone deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.ae = new Array("ramal", "Ramal deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
}

function caracteresespeciais () {
	this.aa = new Array("ddd", "DDD possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("telefone", "Número do Telefone possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ac = new Array("ramal", "Ramal possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ad = new Array("contato", "Número do contato possui caracteres especiais.", new Function ("varName", "return this[varName];"));
}

function limparMunicipio() {
	var form = document.ClienteActionForm;
	
	form.idMunicipio.value = "";
	form.descricaoMunicipio.value = "";
	form.ddd.value = "";
}

function recuperarDadosMunicipioDdd(codigoRegistro,descricaoRegistro,codigoDdd,tipoConsulta) {
	var form = document.ClienteActionForm;

	if( tipoConsulta == 'municipio' ){
		form.idMunicipio.value = codigoRegistro;
		form.descricaoMunicipio.value = descricaoRegistro;
		form.ddd.value = codigoDdd;
		form.descricaoMunicipio.style.color = "#000000";
	      
		setarFoco('idMunicipio');
	}
}

function controleTelefoneExistente()
{
	alert('Telefone já informado.');
}
    
function verificarTamanhoTelefone() {
	var form = document.forms[0];
	if (!(form.telefone.value.length >= 8 && form.telefone.value.length <= 9)) {
		alert('Número do Telefone deve conter entre 8 e 9 dígitos.');
		return false;
	} else {
		return true;
	}
}
function remover(objeto){
	if (confirm ("Confirma remoção?")) {
		redirecionarSubmit('removerClienteTelefoneAction.do?tela=inserir');
	}
}
-->
</script>
<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">
<div id="formDiv">
<html:form action="/inserirClienteWizardAction" method="post">
	
	<!-- CASO POPUP: Reposiciona as Abas -->
	<logic:equal name="POPUP" value="true" scope="session">
	<div id='Layer1PopUp' style='position:absolute; left:; top:-67px; width:300px; height:12px; z-index:1'>
	</logic:equal>
	<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=4" />
	<logic:equal name="POPUP" value="true" scope="session">
	</div>
	</logic:equal>
	
	<!-- CASO POPUP: Retira o Cabecalho e o Menu -->
	<logic:equal name="POPUP" value="false" scope="session">
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	</logic:equal>

	<input type="hidden" name="cancelarValidacao" value="true" />
	<input type="hidden" name="numeroPagina" value="4" />
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<!-- CASO POPUP: Retira a coluna de Informacoes do Usuario -->
			<logic:equal name="POPUP" value="false" scope="session">
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
			</logic:equal>
			<td valign="top" class="centercoltext">
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
						<td class="parabg">Inserir Cliente</td>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
				</table>
				<p>&nbsp;</p>
	
				<table width="100%" border="0">
					<tr>
						<td>Para adicionar o(s) telefone(s) do cliente, informe os dados abaixo:</td>
						<logic:present scope="application" name="urlHelp">
							<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroClienteInserirAbaTelefone', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
						</logic:present>
						<logic:notPresent scope="application" name="urlHelp">
							<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
						</logic:notPresent>
					</tr>
    			</table>
    
    			<table width="100%" border="0">
					<tr>
						<td width="18%">
							<strong>Tipo Telefone:<font color="#FF0000">*</font></strong>
						</td>
						<td width="82%">
							<html:select property="idTipoTelefone" tabindex="1">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="foneTipos" labelProperty="descricaoComId" property="id" />
							</html:select>
							<html:hidden property="textoSelecionado" />
						</td>
					</tr>
					<tr>
						<td height="24"><strong>Munic&iacute;pio:</strong></td>
						<td width="76%">
							<html:text maxlength="4" property="idMunicipio" size="4" tabindex="2"
								onkeypress="javascript: validaEnter(event,'inserirClienteWizardAction.do?action=exibirInserirClienteTelefoneAction','idMunicipio');return isCampoNumerico(event);" />
							<a href="javascript:abrirPopup('exibirPesquisarMunicipioAction.do?caminhoRetornoTelaPesquisaMunicipio=exibirInserirClienteTelefoneAction',400,800);">
								<img width="23" height="21" border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Municipio" /></a>
							<logic:present name="municipioNaoEncontrado" scope="request">
								<html:text maxlength="50" property="descricaoMunicipio"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									size="50" />
							</logic:present> 
							<logic:notPresent name="municipioNaoEncontrado" scope="request">
								<html:text maxlength="50" property="descricaoMunicipio"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									size="50" />
							</logic:notPresent> 
							<a href="javascript:limparMunicipio();">
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar"></a>
						</td>
					</tr>
					<tr>
						<td height="24">
							<strong>DDD:<font color="#FF0000">*</font></strong>
						</td>
						<td><html:text maxlength="2" property="ddd" size="2" tabindex="3" onkeypress="return isCampoNumerico(event);"/></td>
					</tr>
					<tr>
						<td height="24">
							<strong>N&uacute;mero do Telefone:<font color="#FF0000">*</font></strong>
						</td>
						<td>
							<html:text maxlength="9" property="telefone" size="9" tabindex="4" onkeypress="return isCampoNumerico(event);"/>
						</td>
					</tr>
					<tr>
			     <td height="30"><p><strong>Autorização de Envio de SMS:</strong></p></td>
			      <td>
					<strong><html:radio property="indicadorAutorizacaoEnvioSMS" value="1" />Sim</strong> 
			    	<strong><html:radio property="indicadorAutorizacaoEnvioSMS" value="2" />Não</strong>
			      </td>
			    </tr>
					<tr>
						<td height="24">
							<strong>Ramal:</strong>
						</td>
						<td>
							<html:text maxlength="4" property="ramal" size="4" tabindex="5" onkeypress="return isCampoNumerico(event);"/>
						</td>
					</tr>
					<tr>
						<td height="24">
							<strong>Nome do Contato:</strong>
						</td>
						<td>
							<html:text maxlength="50" property="contato" size="50" tabindex="5"/>
						</td>
					</tr>
					<tr>
						<td height="24"><strong></strong></td>
						<td><strong><font color="#FF0000">*</font></strong> Campo obrigat&oacute;rio</td>
					</tr>
					<tr>
						<td colspan="3">
						<table width="100%" border="0">
							<tr>
								<td width="183">
									<strong>Telefone(s) do Cliente</strong>
								</td>
								<td width="432" align="right">
									<input type="button" class="bottonRightCol" value="Adicionar" name="botaoAdicionar" tabindex="6"
										onclick="javascript:validaTelefone();" />
									<input type="hidden" name="botaoClicado" value=""/>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td height="0">
												<table width="100%" bgcolor="#90c7fc">
													<!--header da tabela interna -->
													<tr bordercolor="#FFFFFF" bgcolor="#90c7fc">
														<td width="10%" align="center">
															<strong>Remover</strong>
														</td>
														<td width="10%" align="center">
															<strong>Principal</strong>
														</td>
														<td width="40%" align="center">
															<strong>Telefone</strong>
														</td>
														<td width="30%" align="center">
															<strong>Tipo</strong>
														</td>
														<td width="40%" align="center">
															<strong>Nome do Contato</strong>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td height="100">
												<div style="width: 100%; height: 100%; overflow: auto;">
												<input type="hidden" name="idRegistrosRemocao" value="" />
												<table width="100%" align="center" bgcolor="#99CCFF">
													<%int cont = 0;%>
													<%--Campo que vai guardar o valor do telefone a ser removido--%>
													<logic:iterate name="colecaoClienteFone" id="clienteFone">
														<%cont = cont + 1;
														if (cont % 2 == 0) {%>
														<tr bgcolor="#cbe5fe">
														<%} else { %>
														<tr bgcolor="#FFFFFF">
														<%}%>
															<td width="10%" align="center">
																<strong> 
																<img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" style="cursor:hand;" alt="Remover"
																	onclick="javascript:remover(document.forms[0].idRegistrosRemocao.value='${clienteFone.dddTelefone}');">
																</strong>
															</td>
															<td width="10%" align="center">
																<strong> 
																<html:radio property="indicadorTelefonePadrao"
																	value="<%=""+ GcomAction.obterTimestampIdObjeto(clienteFone)%>"/>
																</strong>
															</td>
															<td width="40%" align="center">
																<strong>
																<bean:write name="clienteFone" property="dddTelefone"/> 
																<logic:notEmpty name="clienteFone" property="ramal">
																	&nbsp;Ramal&nbsp;<bean:write name="clienteFone" property="ramal" />
																</logic:notEmpty> 
																</strong>
															</td>
															<td width="30%" align="center">
																<strong><bean:write name="clienteFone" property="foneTipo.descricao" /></strong>
															</td>
															<td width="40%" align="center">
																<strong><bean:write name="clienteFone" property="contato" /></strong>
															</td>
														</tr>
													</logic:iterate>
												</table>
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="right">
									<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=4"/>
								</td>
							</tr>
						</table>
						<p>&nbsp;</p>
						</td>
					</tr>
				</table>
			</td>	
		</tr>	
	</table>
	
	<!-- CASO POPUP: Retira o Rodape -->
	<logic:equal name="POPUP" value="false" scope="session">
	<%@ include file="/jsp/util/rodape.jsp"%>
	</logic:equal>
	
	<logic:present name="telefoneJaExistente" scope="request">
		<script language="JavaScript">
		<!--
			controleTelefoneExistente();
		-->
		</script>
	</logic:present>
	</html:form>
	</div>
</body>
<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/inserirClienteWizardAction.do?concluir=true&action=inserirClienteTelefoneAction'); }

<logic:equal name="POPUP" value="true" scope="session">
//Altera o onclick do Cancelar caso seja chamado pelo popup
document.getElementById('botaoCancelar').onclick = function() { window.close(); }

//Altera o onclick do Desfazer caso seja chamado pelo popup
var botao = new String(document.getElementById('botaoDesfazer').onclick);
var acao = botao.substring( (botao.indexOf('"') + 1) , botao.lastIndexOf('"'));
acao = acao.replace('menu=sim', 'POPUP=true&desfazer=true');
document.getElementById('botaoDesfazer').onclick = function() { window.location.href = acao; }
</logic:equal>
</script>

</html:html>