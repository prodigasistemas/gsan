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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AdicionarOperacaoTabelaActionForm" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">
<!-- Begin
	function validarForm(form){

		if(validateAdicionarOperacaoTabelaActionForm(form)){
    		submeterFormPadrao(form);
		}
	}

	function limparPesquisarOperacaoTabela(form) {
    	form.idTabela.value = "";
    	form.descricaoTabela.value = "";
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
   		var form = document.forms[0];

	   if (tipoConsulta == 'tabela') {
	      form.idTabela.value = codigoRegistro;
	      form.descricaoTabela.value = descricaoRegistro;
	    }
   }
   
    function recuperarDadosQuatroParametros(codigoRegistro, descricaoRegistro, codigoAuxiliar, tipoConsulta) {
  		if (tipoConsulta == 'tabela') {
	 		insertRowTableTabela(codigoRegistro, descricaoRegistro, codigoAuxiliar);
 		}
  	}
-->
</script>
<!-- javascript:;abrirPopup('exibirPesquisarTabelaAction.do', 400, 800); // Pesquisar Tabela.-->
</head>

<logic:equal name="fechaPopup" value="false" scope="request">
	<body leftmargin="5" topmargin="5"
		onload="javascript:resizePageSemLink(580, 300);javascript:setarFoco('${requestScope.nomeCampo}');">
</logic:equal>
<logic:equal name="fechaPopup" value="true" scope="request">
	<body leftmargin="0" topmargin="0"
		onload="chamarReload('${sessionScope.retornarTela}');window.close()">
</logic:equal>


<html:form action="/adicionarOperacaoTabelaAction"
	name="AdicionarOperacaoTabelaActionForm"
	type="gcom.gui.seguranca.acesso.AdicionarOperacaoTabelaActionForm"
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
					<td class="parabg">Adicionar Tabela Atualizada pela Operação</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>

			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="4">Preencha os campos para inserir uma tabela
					atualizada pela operação:</td>
				</tr>

				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
				</tr>

				<tr>
					<td><strong>Tabela:<font color="#FF0000">*</font></strong></td>
					<td colspan="3"><html:text property="idTabela" size="9"
						maxlength="9"
						onkeypress="return validaEnter(event, 'exibirAdicionarOperacaoTabelaAction.do','idTabela');" />
					<a href="javascript:redirecionarSubmit('exibirPesquisarTabelaAction.do?caminhoRetornoTelaPesquisaTabela=exibirAdicionarOperacaoTabelaAction');">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Tabela"></a> 
					
					<logic:present name="corTabela">	
						<logic:equal name="corTabela" value="exception">
							<html:text property="descricaoTabela" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:equal>
						<logic:notEqual name="corTabela" value="exception">
							<html:text property="descricaoTabela" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> 
					
					<logic:notPresent name="corTabela">
						<logic:empty name="AdicionarOperacaoTabelaActionForm"
							property="idTabela">
							<html:text property="descricaoTabela"
								value="" size="45" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:empty>
						<logic:notEmpty name="AdicionarOperacaoTabelaActionForm"
							property="idTabela">
							<html:text property="descricaoTabela" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a href="javascript:limpar(4);"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a></td>
				</tr>

				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>Campos
					obrigat&oacute;rios</div>
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
						onclick="validarForm(document.forms[0]);" /> <input type="button"
						name="Button" class="bottonRightCol" value="Fechar" tabindex="4"
						onclick="window.close()" /></div>
					</td>
					<td colspan="3">&nbsp;</td>
				</tr>

			</table>
			</td>
		</tr>
	</table>
</html:form>

</html:html>
