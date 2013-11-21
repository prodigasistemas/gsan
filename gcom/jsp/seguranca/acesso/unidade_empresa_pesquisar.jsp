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
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="UnidadeEmpresaActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

<!--

	//Recebe os dados do(s) popup(s)
    function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.UnidadeEmpresaActionForm;

       	if (tipoConsulta == 'unidadeSuperior') {
        	form.idImovel.value = codigoRegistro;
        	form.action = 'exibirPesquisarUnidadeSuperiorAction.do'
        	form.submit();
      	}

	}

    function limparUnidadeSuperior() {
        var form = document.UnidadeEmpresaActionForm;

        form.idUnidadeSuperior.value = "";
		form.idUnidadeSuperior.focus();
    }
    
    function validarForm(form){      
      
    	if(validateUnidadeEmpresaActionForm(form)){
     		form.submit();
	  	}
    }
    
    function limpaTudo(){
        var form = document.UnidadeEmpresaActionForm;

        form.codigoUnidade.value = "";
        form.nomeUnidade.value = "";
        form.siglaUnidade.value = "";
        form.nivelHiearquia.value = "-1";
        form.idUnidadeSuperior.value = "";
		form.codigoUnidade.focus();
		
    }
    
    function submitForm() {
    	var form = document.forms[0];
    	form.action = 'exibirPesquisarUnidadeEmpresaAction.do'
    	form.submit();
    }
-->
</script>


</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(475, 370);setarFoco('${requestScope.nomeCampo}');">

<html:form action="/pesquisarUnidadeEmpresaAction" method="post"
	name="UnidadeEmpresaActionForm"
	type="gcom.gui.seguranca.acesso.UnidadeEmpresaActionForm"
	onsubmit="return validateUnidadeEmpresaActionForm(this);">

	<table width="452" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="452" valign="top" class="centercoltext">

			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Pesquisar Unidade Empresa</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="4">Preencha os campos para pesquisar uma unidade:</td>
					<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=unidadeLotacaoPesquisar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td><strong>C&oacute;digo da Unidade:</strong></td>
					<td>
						<html:text property="codigoUnidade" 
							size="10" 
							maxlength="9" />
					</td>
				</tr>

				<tr>
					<td><strong>Nome da Unidade:</strong></td>
					<td>
						<html:text property="nomeUnidade" 
							size="45" 
							maxlength="40" />
					</td>
				</tr>

				<tr>

				<tr>
					<td><strong>Sigla da Unidade:</strong></td>
					<td>
						<html:text property="siglaUnidade" 
							size="7" 
							maxlength="6" />
					</td>
				</tr>

				<tr>
					<td><strong>N&iacute;vel na Hierarquia:</strong></td>
					<td>
						<html:select property="nivelHiearquia">

							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoUnidadesNiveis"
								labelProperty="descricaoUnidadeNivel" 
								property="id" />
						</html:select>
					</td>
				</tr>

				<tr>
					<td><strong>Unidade Superior<font color="#000000">:</font></strong></td>
					<td>
						<html:text property="idUnidadeSuperior" 
						size="4" 
						maxlength="4" />
						
						<img width="23" 
							height="21"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							alt="Pesquisar" />  
						
						<html:text property="descricaoUnidadeSuperior" 
							size="30"
							maxlength="30" 
							readonly="true" 
							value="PESQUISA NÂO DISPONÍVEL"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
						
						<a href="javascript:limparUnidadeSuperior();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>

				</tr>

				<tr>
					<td>&nbsp;</td>
					<td colspan="3">&nbsp;</td>
				</tr>
				
				<tr>
					<td height="24" align="left">
						
						<input type="button" 
							name="ButtonLimpar" 
							class="bottonRightCol"
							value="Limpar" 
							onClick="javascript:limpaTudo();" />
						
						<logic:present name="popup">
							<input type="button" 
								name="ButtonVoltar" 
								class="bottonRightCol"
								value="voltar" 
								onClick="javascript:window.href='/gsan/${sessionScope.popup}'" />
						</logic:present>
					</td>
					
					<td colspan="3" align="right">
						<input type="button" 
							name="Button" 
							class="bottonRightCol"
							value="Pesquisar" 
							onClick="javascript:validarForm(document.forms[0])" />
					</td>
				</tr>
			</table>

			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</body>

</html:form>
</html:html>
