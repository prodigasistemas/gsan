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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AdicionarFuncionalidadeDependenciaActionForm" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>



<script language="JavaScript">


	function validarForm(form) {

		if(testarCampoValorZero(form.comp_id, 'Funcionalidade')) {

			if(validateAdicionarFuncionalidadeDependenciaActionForm(form)){
    			submeterFormPadrao(form);
    			
			}
		}
	}

	function limparPesquisarFuncionalidadeDependencia(form) {
    	form.comp_id.value = "";
    	form.descricaoFuncionalidade.value = "";
	}
	
	function chamarReload(){
		
		chamarSubmitComUrlSemUpperCase('/gsan/exibirAtualizarFuncionalidadeAction.do?idFuncionalidade=${requestScope.idFuncionalidade}');
	}

</script>

</head>

<body>

<logic:present name="reload">
	<body leftmargin="5" topmargin="5"
		onload="resizePageSemLink(400, 400); chamarReload();window.close();">
</logic:present>

<logic:notPresent name="reload">
	<body leftmargin="5" topmargin="5"
		onload="resizePageSemLink(600, 350);">
</logic:notPresent>





<html:form action="/adicionarFuncionalidadeDependenciaAction"
	name="AdicionarFuncionalidadeDependenciaActionForm"
	type="gcom.gui.seguranca.acesso.AdicionarFuncionalidadeDependenciaActionForm"
	method="post">
	<table width="530" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="452" valign="top" class="centercoltext">
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
					<td class="parabg">Adicionar Dependência da Funcionalidade</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="4">Preencha os campos para inserir uma dependência da
					funcionalidade:</td>
				</tr>
				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
				</tr>

				<tr>
					<td><strong>Funcionalidade<font color="#FF0000">*</font></strong></td>
					<td colspan="3"><html:text property="comp_id" size="4"
						maxlength="9" tabindex="2"
						onkeypress="return validaEnter(event, 'exibirAdicionarFuncionalidadeDependenciaAction.do?objetoConsulta=1&idFuncionalidade=${requestScope.idFuncionalidade}', 'comp_id');" />

					<a
						href="javascript:window.location.href='exibirPesquisarFuncionalidadeAction.do?caminhoRetornoTelaPesquisaFuncionalidade=exibirAdicionarFuncionalidadeDependenciaAction';">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Funcionalidade" /></a> <logic:present
						name="funcionalidadeDependenciaNaoEncontrada">
						<logic:equal name="funcionalidadeDependenciaNaoEncontrada"
							value="exception">
							<html:text property="descricaoFuncionalidade" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="funcionalidadeDependenciaNaoEncontrada"
							value="exception">
							<html:text property="descricaoFuncionalidade" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent
						name="funcionalidadeDependenciaNaoEncontrada">
						<logic:empty name="AdicionarFuncionalidadeDependenciaActionForm"
							property="comp_id">
							<html:text property="descricaoFuncionalidade" value="" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty
							name="AdicionarFuncionalidadeDependenciaActionForm"
							property="comp_id">
							<html:text property="descricaoFuncionalidade" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a
						href="javascript:limparPesquisarFuncionalidadeDependencia(document.forms[0]);">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>
				<input type="hidden" property="funcionalidadeID" value="${requestScope.idFuncionalidade}"/>
				
				</tr>

				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>


				<tr>
					<td height="0">&nbsp;</td>
					<td colspan="3">&nbsp;</td>
				</tr>

				<tr>
					<td height="24" colspan="4">
					<div align="right"><input type="button" name="Button"
						class="bottonRightCol" value="Inserir" tabindex="3"
						onClick="validarForm(document.forms[0]);" /> <input type="button"
						name="Button" class="bottonRightCol" value="Fechar" tabindex="4"
						onClick="window.close()" /></div>
					</td>
					<td colspan="3">&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
	</table>
</html:form>
</html:html>
