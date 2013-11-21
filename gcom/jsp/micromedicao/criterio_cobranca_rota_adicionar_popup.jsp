<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="gcom.util.ConstantesSistema" %>

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AdicionarCriterioCobrancaRotaActionForm" />
<script language="JavaScript">


<!-- Begin

   function recuperarDados(codigoRegistro, descricaoRegistro, codigoAuxiliar,tipoConsulta) {
   var form = document.forms[0];

	   if (tipoConsulta == 'criterioCobranca') {
	       	form.idCobrancaCriterio.value = codigoRegistro;
    		form.descricaoCobrancaCriterio.value= descricaoRegistro;

	    }
   }


	function validarForm(form){
		if (testarCampoValorZero(document.AdicionarCriterioCobrancaRotaActionForm.idCobrancaCriterio, 'Critério de Cobrança')){
			if(validateAdicionarCriterioCobrancaRotaActionForm(form)){
	    		form.submit();
			}
		}
	}

	function limparPesquisaCriterioCobranca(form) {
    	form.idCobrancaCriterio.value = "";
    	form.descricaoCobrancaCriterio.value = "";
	}

function chamarSubmitComUrl(pagina){
	toUpperCase(opener.window.document.forms[0]);
	opener.window.document.forms[0].action = pagina; 
	opener.window.document.forms[0].submit();
}


-->
</script>

</head>

<logic:present name="reloadPage">
	
	<logic:equal name="reloadPageURL" value="INSERIRROTA">
		<body leftmargin="5" topmargin="5" onload="resizePageSemLink(580, 350); chamarSubmitComUrl('exibirInserirRotaAction.do?reloadPage=1');">
	</logic:equal>
	
	<logic:equal name="reloadPageURL" value="ATUALIZARROTA">
		<body leftmargin="5" topmargin="5" onload="resizePageSemLink(580, 350);
		toUpperCase(opener.document.forms[0]);
		opener.reloadPagina();">
	</logic:equal>
	
	
</logic:present>

<logic:notPresent name="reloadPage">
	<body leftmargin="5" topmargin="5" onload="resizePageSemLink(580, 350)">
</logic:notPresent>





<html:form action="/adicionarCriterioCobrancaRotaAction"
	name="AdicionarCriterioCobrancaRotaActionForm"
	type="gcom.gui.micromedicao.AdicionarCriterioCobrancaRotaActionForm"
	method="post"
	onsubmit="return validateAdicionarCriterioCobrancaRotaActionForm(this);">
	<table width="550" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="500" valign="top" class="centercoltext">
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
					<td class="parabg">Adicionar Critério de Cobrança da Rota</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="4">Preencha os campos para inserir um critério de cobrança da rota:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroRotaCriterioCobrancaAdicionar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
				</table>
				<table width="100%" border="0">
				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
				</tr>
				
				<tr>
					<td><strong>Ação de Cobrança:<font color="#FF0000">*</font></strong></td>
					<td colspan="3">
					<html:select property="cobrancaAcao" >
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="collectionCobrancaAcao" labelProperty="descricaoCobrancaAcao"
							property="id" />
					</html:select>
					</td>
				</tr>
				
				<tr>
					<td><strong>Critério de Cobrança:<font color="#FF0000">*</font></strong></td>
					<td colspan="3"><html:text property="idCobrancaCriterio" size="4"
						maxlength="4"  tabindex="2"
						onkeypress="javascript:return validaEnter(event, 'exibirAdicionarCriterioCobrancaRotaAction.do?objetoConsulta=1&teste=cobrancaAcao', 'idCobrancaCriterio');" />

					<a
						href="javascript:redirecionarSubmit('exibirPesquisarCriterioCobrancaAction.do?caminhoRetornoTelaPesquisaCriterioCobranca=exibirAdicionarCriterioCobrancaRotaAction&limpaForm=1&popup=true&idCobrancaAcao='+document.forms[0].cobrancaAcao.value);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Criterio de Cobrança" /></a> 
						
					<logic:present
						name="idCobrancaCriterioNaoEncontrado">
						<logic:equal name="idCobrancaCriterioNaoEncontrado" value="exception">
							<html:text property="descricaoCobrancaCriterio" size="40" maxlength="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="idCobrancaCriterioNaoEncontrado"
							value="exception">
							<html:text property="descricaoCobrancaCriterio" size="40" maxlength="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent
						name="idCobrancaCriterioNaoEncontrado">
						<logic:empty name="AdicionarCriterioCobrancaRotaActionForm"
							property="idCobrancaCriterio">
							<html:text property="descricaoCobrancaCriterio" value="" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="AdicionarCriterioCobrancaRotaActionForm"
							property="idCobrancaCriterio">
							<html:text property="descricaoCobrancaCriterio" size="40" maxlength="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a
						href="javascript:limparPesquisaCriterioCobranca(document.AdicionarCriterioCobrancaRotaActionForm);">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>
				</tr>

              <tr> 
                <td><strong> <font color="#FF0000"></font></strong></td>
                <td align="right"> <div align="left"><strong><font color="#FF0000">*</font></strong> 
                    Campos obrigat&oacute;rios</div></td>
              </tr> 
				
				
				<tr>
					<td height="0">&nbsp;</td>
					<td colspan="3">&nbsp;</td>
				</tr>
				
				<tr>
					<td>
						<input type="button" name="Button" class="bottonRightCol" value="Fechar" tabindex="4"
						onClick="window.close()" />	
                    </td>
                    <td  align="right">
                    	<input type="button" name="Button" class="bottonRightCol" value="Inserir" tabindex="3"
						onClick="validarForm(document.forms[0]);" />
                    </td>
				</tr>
				
				
			</table>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
	</table>
</html:form>
</body>

</html:html>
