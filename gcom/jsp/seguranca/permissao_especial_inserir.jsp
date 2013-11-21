<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
 
<%@ page import="gcom.cadastro.funcionario.Funcionario"%>
<%@ page import="gcom.seguranca.acesso.usuario.Usuario"%>
<%@ page import="gcom.seguranca.acesso.PermissaoEspecial"%>

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
	
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
	
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AcaoCobrancaActionForm" dynamicJavascript="false" />

<script language="JavaScript">

	function limparUnidade() {
		var form = document.forms[0];
		form.idUnidade.value = "";
		form.nomeUnidade.value = "";
		
	    form.action='exibirIncluirPermissaoEspecialAction.do';
	    form.submit();
	}
	
	function limparNomeUnidade() {
		var form = document.forms[0];
		form.nomeUnidade.value = "";
	}
	
	/* Recupera Dados Popup */	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
	    
	    form.action='exibirIncluirPermissaoEspecialAction.do';
	    if (tipoConsulta == 'unidadeOrganizacional') {
			form.idUnidade.value = codigoRegistro;
			form.nomeUnidade.value = descricaoRegistro;
			form.nomeUnidade.style.color = "#000000";
	   }
	    submeterFormPadrao(form);
	}
	
	function validarForm(){
		var form = document.forms[0];

		if (form.idUnidade.value == '') {
			alert('Informe a Unidade Organizacional.');
		} else if (!campoSelecionado(form.idUsuario)) {
			alert('Informe pelo menos um Usuário.');
		} else if (!campoSelecionado(form.idPermissao)) {
			alert('Informe pelo menos uma Permissão Especial.');
		} else {
			form.action = '/gsan/incluirPermissaoEspecialAction.do';
			form.submit();
		}
	}
	
	function campoSelecionado(idCampo) {
		var i;
		
		if (typeof(idCampo) == 'undefined') {
			return false;
		}
		
		for (i = 0; i < idCampo.length; i++) {
			if (idCampo[i].checked == true) {
				return true;
			}
		}
		
		return false;
	}
	
	function desfazer() {
		var form = document.forms[0];
	    
	    form.action='exibirIncluirPermissaoEspecialAction.do';
	    form.submit();
	}

	function selecionarUsuario(){
		var form = document.forms[0];
		var objeto = form.idUsuario;
		
		if (form.idUs == "0" || form.idUs == undefined) {
			form.idUs = "1";
			marca(objeto);
		} else {
			form.idUs = "0";
			desmarca(objeto);
		}
	}
	
	function selecionarPermissao(){
		var form = document.forms[0];
		var objeto = form.idPermissao;
		
		if (form.idPerm == "0" || form.idPerm == undefined) {
			form.idPerm = "1";
			marca(objeto);
		} else {
			form.idPerm = "0";
			desmarca(objeto);
		}
	}
	
	function marca(objeto) {
		for (var i=0;i < objeto.length;i++){
			var elemento = objeto[i];
			if (elemento.type == "checkbox" && elemento.disabled == false){
				elemento.checked = true;
			}
		}
	}
	function desmarca(objeto) {
		for (var i=0;i < objeto.length;i++){
			var elemento = objeto[i];
			if (elemento.type == "checkbox"){
				elemento.checked = false;
			}
		}
	}
</script>

</head>

<body leftmargin="5" topmargin="5" onload="">

<html:form action="/exibirIncluirPermissaoEspecialAction"
	name="IncluirPermissaoEspecialActionForm"
	type="gcom.gui.seguranca.acesso.usuario.IncluirPermissaoEspecialActionForm"
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
			<td width="600" valign="top" bgcolor="#003399" class="centercoltext">
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
					<td class="parabg">Incluir Permissão Especial por Unidade Organizacional</td>

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
					<p>&nbsp;</p>
					<p>Para controlar o acesso a permissões especiais, marque ou desmarque a(s) permissão(ões) especial(is):</p>
					<p>&nbsp;</p>
					<p><strong>Permissões especiais para a Unidade Organizacional</strong></p>
					<p>&nbsp;</p>
					</td>
				</tr>
				<tr>
					<td><strong>Unidade Organizacional: <font color="#FF0000">*</font></strong></td>
					<td>
						<html:text maxlength="4" 
							property="idUnidade"
							size="4" 
							onkeyup="javascript:limparNomeUnidade();javascript:verificaNumeroInteiro(this);"
							onkeypress="validaEnterComMensagem(event, 'exibirIncluirPermissaoEspecialAction.do', 'idUnidade','Unidade Organizacional');return isCampoNumerico(event);"/>
						
						<a href="javascript:abrirPopup('exibirPesquisarUnidadeOrganizacionalAction.do');">
							<img width="23" 
								height="21" 
								border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Unidade Organizacional" /></a> 
								
						<logic:equal name="idUnidadeEmpresaNaoEncontrada" value="exception">
							<html:text property="nomeUnidade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
						</logic:equal>
		
						<logic:notEqual name="idUnidadeEmpresaNaoEncontrada" value="exception">
							<html:text property="nomeUnidade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
						</logic:notEqual>
						
						<a href="javascript:limparUnidade();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				<tr>
					<td height="15" colspan="3"></td>
				</tr>
			</table>
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td>
						<table width="100%" bgcolor="#99CCFF">
							<tr bgcolor="#99CCFF">
								<td width="20%" align="center"><a href="javascript:selecionarUsuario();"><strong>Todos</strong></a></td>
								<td width="80%"><FONT COLOR="#000000"><strong>Usuário</strong></FONT></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<logic:present name="colecaoUsuario" scope="session">
							<div style="width: 100%; height: 100; overflow: auto;">
								<table width="100%" cellpadding="0" cellspacing="0">
									<tr>
										<td>
											<% String cor = "#cbe5fe";%>
											<table width="100%" bgcolor="#99CCFF" border="0">
												<logic:iterate name="colecaoUsuario" id="usuario" type="Usuario">
									
													<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
														cor = "#FFFFFF";%>
														<tr bgcolor="#FFFFFF" height="18">	
													<%} else{	
														cor = "#cbe5fe";%>
														<tr bgcolor="#cbe5fe" height="18">		
													<%}%>
													<bean:define name="usuario" property="funcionario" id="funcionario" type="Funcionario"></bean:define>
									
													<td align="center" width="20%"><html:checkbox property="idUsuario" value="<%="" + usuario.getId()%>"/></td>
													<td width="80%"><bean:write name="funcionario" property="nome"/></td>
									
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
				<logic:notPresent name="colecaoUsuario" scope="session">
					<tr>
						<td height="20" colspan="3"></td>
					</tr>
				</logic:notPresent>
			</table>
		  
		  
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td height="15" colspan="3"></td>
				</tr>
				<tr>
					<td>
						<table width="100%" bgcolor="#99CCFF">
							<tr bgcolor="#99CCFF">
								<td width="20%" align="center"><a href="javascript:selecionarPermissao();"><strong>Todos</strong></a></td>
								<td width="80%"><strong>Permissão Especial</strong></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
					<logic:present name="colecaoPermissaoEspecial" scope="session">
						<div style="width: 100%; height: 100; overflow: auto;">
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td>
										<% String cor2 = "#cbe5fe";%>
									
										<table width="100%" bgcolor="#99CCFF" border="0">
											<logic:iterate name="colecaoPermissaoEspecial" id="permissaoEspecial" type="PermissaoEspecial">
								
												<%	if (cor2.equalsIgnoreCase("#cbe5fe")){	
													cor2 = "#FFFFFF";%>
													<tr bgcolor="#FFFFFF">	
												<%} else{	
													cor2 = "#cbe5fe";%>
													<tr bgcolor="#cbe5fe">		
												<%}%>
								
												<td align="center" width="20%"><html:checkbox property="idPermissao" value="<%="" + permissaoEspecial.getId()%>"/></td>
												<td  width="80%"><bean:write name="permissaoEspecial" property="descricao"/></td>
								
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
				  <logic:notPresent name="colecaoPermissaoEspecial" scope="session">
					<tr>
						<td height="80" colspan="2">
						</td>
					</tr>
				  </logic:notPresent>
			</table>
			
				
			<table width="100%" border="0">
				<tr>
					<td height="10" colspan="2">
					</td>
				</tr>
				<tr>
					<td height="10" colspan="2">
						<hr>
					</td>
				</tr>
				<tr>
					<td align="left">
					  <input name="button" type="button"
							class="bottonRightCol" value="Desfazer" onclick="desfazer();">
					  &nbsp;<input name="button" type="button"
							class="bottonRightCol" value="Cancelar" 
						   	onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right">
						<input type="button" name="Button"
							class="bottonRightCol" value="Concluir"
							onclick="validarForm();" tabindex="13" />
					</td>
				</tr>
			</table>
			
		   </td>
	  </tr>
 </table>


	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
