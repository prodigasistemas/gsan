<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="gcom.util.ConstantesSistema" isELIgnored="false"%>

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarOperacaoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
<!--

    //Recebe os dados do(s) popup(s)
    function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

      var form = document.PesquisarOperacaoActionForm;

       if (tipoConsulta == 'funcionalidade') {
        form.idImovel.value = codigoRegistro;
        form.action = 'exibirPesquisarOperacaoAction.do'
        form.submit();
      }

    }

    function limparFuncionalidade() {
        var form = document.PesquisarOperacaoActionForm;

        form.idFuncionalidade.value = "";
        form.descricaoFuncionalidade.value = "";
    }
    
    function validarForm(form){      
      
      if(validatePesquisarOpearacaoActionForm(form)){
     		form.submit();
	  }
    }

-->
</script>


</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(500, 420);">
<html:form action="/pesquisarOperacaoAction" method="post"
	name="PesquisarOperacaoActionForm"
	type="gcom.gui.seguranca.acesso.PesquisarOperacaoActionForm"
	onsubmit="return validatePesquisarOperacaoActionForm(this);">
	<table width="480" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="480" valign="top" class="centercoltext">
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
					<td class="parabg">Pesquisar Operação </td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<!-- ============================================================================================================================== -->

			<table width="100%" border="0">
				<tr>
					<td colspan="4">Preencha os campos para pesquisar uma operação:</td>
				</tr>
				<tr>
					<td><strong>C&oacute;digo da Operação:</strong></td>
					<td><html:text property="codigoOperacao" size="10" maxlength="9" />
					(Somente n&uacute;mero)</td>
				</tr>
				<tr>
					<td><strong>Nome da Operacao:</strong></td>
					<td><html:text property="nomeOperacao" size="45" maxlength="60" /></td>
				</tr>

				<tr>

				<tr>
					<td><strong>Tipo da Operação<font color="#000000"></font>:</strong></td>
					<td><html:select property="tipoOperacao">

						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoOperacaoTipo"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Funcionalidade<font color="#000000">:</font></strong></td>
					<td><html:text property="idFuncionalidade" size="4" maxlength="4"
						onkeypress="javascript:return validaEnter(event, 'exibirPesquisarOperacaoAction.do', 'idFuncionalidade');" />
					<img width="23" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						style="cursor:hand;"
						onclick="abrirPopup('exibirPesquisarFuncionalidadeAction.do', 400, 800);"
						alt="Pesquisar" /> <logic:present
						name="funcionalidadeInexistente" scope="request">
						<html:text property="descricaoFuncionalidade" size="35"
							maxlength="35" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent
						name="funcionalidadeInexistente" scope="request">
						<html:text property="descricaoFuncionalidade" size="35"
							maxlength="35" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparFuncionalidade();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>

				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td height="24"><input type="submit" name="Button" class="bottonRightCol"
						value="Pesquisar" onClick="javascript:validarForm(document.forms[0])" /></td>
					<td colspan="3">&nbsp;</td>
				</tr>
			</table>

			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</body>

</html:form>
</html:html>
