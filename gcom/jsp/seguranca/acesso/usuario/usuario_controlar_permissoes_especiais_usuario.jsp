<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema"%>

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
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ControlarAcessoUsuarioActionForm" />

<script language="JavaScript">
window.onmousemove = habilitaCheckboxs();

function trocar() {
	document.forms[0].btnSalvar.disabled = false;
}

function salvar() {

	document.forms[0].action = 'inserirGrupoWizardAction.do?action=exibirInserirGrupoAcessosGrupoAction';
	document.forms[0].cadastrarOperacao.value= 'true';
	document.forms[0].submit();
}

function comboBoxChecados(){
   var erro = false;
   var form = document.forms[0];
   var permissoesEspeciais = form.permissoesEspeciais;
   for (var i=0;i<permissoesEspeciais.length;i++){
    if(permissoesEspeciais[i].disabled){
      permissoesEspeciais[i].checked = true;
      erro = true;
     }
   }
   if(!erro){
	 if(permissoesEspeciais.disabled){
      permissoesEspeciais.checked = true;
     } 
   }
}

</script>

</head>

<body leftmargin="5" topmargin="5" onload="comboBoxChecados();">

<html:form action="/controlarPremissoesEspeciaisUsuarioAction"
	method="post"
	onsubmit="return validateControlarAcessoUsuarioActionForm(this);">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=2" />

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

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Controlar Acessos do Usuario - Permissões
					Especiais</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>

			<table border="0" width="100%">
				<tr>
					<td colspan="2">Para controlar o acesso a permissões especiais,
					marque ou desmarque a(s) permissão(ões) especial(is):</td>
				</tr>

				<tr>
					<td colspan="2">
					<table width="100%" cellpadding="0" cellspacing="0">

						<tr>
							<td>
							<input type="hidden" name="numeroPagina" value="2"/>
							<table width="100%" border="0" bordercolor="#000000">
								<tr bordercolor="#90c7fc">
									<td colspan="2"><strong>Permissões especiais para o Usuário</strong></td>
								</tr>
								<tr bordercolor="#90c7fc">
									<td width="7%"><strong>Login:</strong></td>
									<td><html:text property="loginUsuario" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="10" maxlength="10" /></td>
								</tr>
								<tr bordercolor="#90c7fc">
									<td width="7%"><strong>Nome:</strong></td>
									<td><html:text property="nomeUsuario" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="50" maxlength="50" /></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td colspan="2">
							<table border="0" bgcolor="#99CCFF" width="100%" cellpadding="0" cellspacing="0">
								<tr bordercolor="#000000">
									<td width="18%" bgcolor="#90c7fc">
									<div align="center"><strong>Marca/Desmarca</strong></div>
									</td>
									<td width="80%" bgcolor="#90c7fc"><strong>Permissão Especial</strong>
									</td>
								</tr>
							<tr>
							<td colspan="2" height="250">
							<div style="width: 100%; height: 100%; overflow: auto;">
							<table border="0" bgcolor="#99CCFF" width="100%">
								<%int cont = 0;%>
								<logic:present name="colecaoPermissaoEspecial">
									<logic:iterate name="colecaoPermissaoEspecial"
										id="permissaoEspecial">
										<%cont = cont + 1;
							if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {

							%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td width="18%">
											<div align="center"><strong> <html:multibox
												property="permissoesEspeciais"
												value="${permissaoEspecial.id}" /> </strong></div>
											</td>
											<td width="80%"><bean:write name="permissaoEspecial"
												property="descricao" /></td>
										</tr>
									</logic:iterate>
								</logic:present>

								<logic:present name="colecaoPermissaoEspecialDesalibitado">
									<logic:iterate name="colecaoPermissaoEspecialDesalibitado"
										id="permissaoEspecial">
										<%cont = cont + 1;
							if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {

							%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td width="18%">
											<div align="center"><strong> <html:multibox
												property="permissoesEspeciais"
												value="${permissaoEspecial.id}" disabled="true" /> </strong></div>
											</td>
											<td width="80%"><bean:write name="permissaoEspecial"
												property="descricao" /></td>
										</tr>

									</logic:iterate>
								</logic:present>
								</table>
								</div>
								</td>
								</tr>
							</table>
							</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=2" /></div>
					</td>
				</tr>

			</table>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
	<%@ include file="/jsp/util/tooltip.jsp"%>
</body>
</html:form>
</html:html>
