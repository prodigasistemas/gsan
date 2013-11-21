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
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>	
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarResolucaoDiretoriaActionForm" />	
<script language="JavaScript">

	function validarForm(form){
		if(validatePesquisarResolucaoDiretoriaActionForm(form)){
			if (testarCampoValorZero(document.PesquisarResolucaoDiretoriaActionForm.numeroResolucaoDiretoria, 'Número da RD')){
				if (comparaData(form.dataInicioVigencia.value, ">", form.dataFimVigencia.value )){
			  		alert('O Término de Vigência deve ser maior que o Início da Vigência');
				}else {
	    			form.submit();
	    		}
    		}
		}
	}

</script>

</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(580, 500);">

<html:form action="/pesquisarResolucaoDiretoriaAction"
	name="PesquisarResolucaoDiretoriaActionForm"
	type="gcom.gui.cobranca.PesquisarResolucaoDiretoriaActionForm"
	method="post"
	onsubmit="return validatePesquisarResolucaoDiretoriaActionForm(this);">
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
					<td class="parabg">Pesquisar Resolução de Diretoria</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Preencha os campos para pesquisar uma resolução de diretoria:</td>
				</tr>
				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
				</tr>
								
				<tr>
					<td><strong>Numero da RD:</strong></td>
					<td><html:text property="numeroResolucaoDiretoria" size="15" maxlength="15"  tabindex="1" /></td>
				</tr>
				
				
				<tr>
					<td><strong>Data In&iacute;cio Vig&ecirc;ncia RD:</strong></td>

					<td><strong> 
					<html:text
						property="dataInicioVigencia" size="10"
						maxlength="10" tabindex="2" onkeyup="mascaraData(this, event);"/> <a
						href="javascript:abrirCalendario('PesquisarResolucaoDiretoriaActionForm', 'dataInicioVigencia')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle"
						alt="Exibir Calendário" /></a></strong> 
					(dd/mm/aaaa)</td>
				</tr>
				
				
				<tr>
					<td><strong>Data T&eacute;rmino Vig&ecirc;ncia RD:</strong></td>

					<td><strong> 
					<html:text
						property="dataFimVigencia" size="10"
						maxlength="10" tabindex="3" onkeyup="mascaraData(this, event);"/> <a
						href="javascript:abrirCalendario('PesquisarResolucaoDiretoriaActionForm', 'dataFimVigencia')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle"
						alt="Exibir Calendário" /></a></strong> 
					(dd/mm/aaaa)</td>
				</tr>
				
	
			
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>


				<tr>
					<td>
						<logic:present name="caminhoRetornoTelaPesquisaResolucaoDiretoria">
							<input type="button" name="Button"
							class="bottonRightCol" value="Voltar" 
							onclick="history.back();">
						</logic:present>
					
						<input name="Button" type="button" class="bottonRightCol" value="Limpar" align="left" onclick="window.location.href='<html:rewrite page="/exibirPesquisarResolucaoDiretoriaAction.do?desfazer=S"/>'" >
                    </td>
                    
                    <td  align="right">
						<input type="button" name="Button"
						class="bottonRightCol" value="Pesquisar" tabindex="4"
						onClick="validarForm(document.PesquisarResolucaoDiretoriaActionForm)"
						 />
					</td>
				</tr>


				
			</table>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
	</table>
</html:form>
</body>
</html:html>
