<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<%@ page import="gcom.util.Pagina, gcom.util.ConstantesSistema"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de
Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="PesquisarRotaActionForm"  />

<script>

function limparLocalidade(form) {
    	form.idLocalidade.value = "";
    	form.localidadeDescricao.value = "";
	}
	
	function limparSetorComercial(form) {
    	form.codigoSetorComercial.value = "";
    	form.setorComercialDescricao.value = "";
	}

	function limparDescLocalidade(form) {
    	form.localidadeDescricao.value = "";
    	form.codigoSetorComercial.value = "";
	}
	
	function limparDescSetorComercial(form) {
    	form.setorComercialDescricao.value = "";
	}

    function limparForm(){
    	var form = document.forms[0];
    	form.empresaLeituristica.value = "-1";
    	form.idGrupoFaturamento.value = "-1";   
    	form.codigoRota.value = ""; 	
    }
    function validarForm(form){
    	if (validatePesquisarRotaActionForm(form)) {
			form.submit();
		}
    }
    
    
</script>
</head>

<body leftmargin="0" topmargin="0"
	onload="window.focus();resizePageSemLink(575, 415);setarFoco('${requestScope.nomeCampo}');">
<html:form action="/pesquisarRotaAction" method="post">
	<table width="545" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="545" valign="top" class="centercoltext">
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
					<td class="parabg">Pesquisar Rota</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="4">Preencha os campos para pesquisar uma rota:</td>
					<td align="right"><a
						href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=localidadePesquisar', 500, 700);"><span
						style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
				</tr>
			</table>

			<table width="100%" border="0">

				<tr>
					<td width="25%"><strong>Localidade:</strong></td>
					<td>
					
					<logic:empty name="PesquisarRotaActionForm" property="bloquearCampos">
					<html:text maxlength="3" tabindex="1" property="idLocalidade"
						size="3"
						onkeypress="javascript:limparDescLocalidade(document.PesquisarRotaActionForm);
						limparDescSetorComercial(document.PesquisarRotaActionForm);
						validaEnterComMensagem(event, 'exibirPesquisarInformarRotaLeituraAction.do', 'idLocalidade','Localidade');" />
					<a
						href="javascript:redirecionarSubmit('exibirPesquisarLocalidadeAction.do?caminhoRetornoTelaPesquisa=exibirPesquisarInformarRotaLeituraAction');">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Localidade" /></a> <logic:present
						name="idLocalidadeNaoEncontrada">
						<logic:equal name="idLocalidadeNaoEncontrada" value="exception">
							<html:text property="localidadeDescricao" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="idLocalidadeNaoEncontrada" value="exception">
							<html:text property="localidadeDescricao" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="idLocalidadeNaoEncontrada">
						<logic:empty name="PesquisarRotaActionForm"
							property="idLocalidade">
							<html:text property="localidadeDescricao" value="" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>

						<logic:notEmpty name="PesquisarRotaActionForm"
							property="idLocalidade">
							<html:text property="localidadeDescricao" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent> <a
						href="javascript:limparLocalidade(document.PesquisarRotaActionForm);
						limparSetorComercial(document.PesquisarRotaActionForm);">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a>
						</logic:empty>
						<logic:notEmpty name="PesquisarRotaActionForm" property="bloquearCampos">
							<html:text maxlength="3" tabindex="1" property="idLocalidade"
						size="3" readonly="true" /> 
						<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Localidade" /> 
						<html:text property="localidadeDescricao" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" /> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" />
						</logic:notEmpty>
						
						</td>
				</tr>


				<tr>
					<td><strong>Setor Comercial:</strong></td>
					<td height="24">
					<logic:empty name="PesquisarRotaActionForm" property="bloquearCampos">
					<html:text maxlength="3"
						property="codigoSetorComercial" tabindex="2" size="3"
						onkeypress="javascript:return validaEnterDependenciaComMensagem(event, 'exibirPesquisarInformarRotaLeituraAction.do', this, document.forms[0].idLocalidade.value, 'Localidade','Setor Comercial');" />

					<a
						href="javascript:limparDescSetorComercial(document.PesquisarRotaActionForm);redirecionarSubmitDependencia('exibirPesquisarSetorComercialAction.do?caminhoRetornoTelaPesquisa=exibirPesquisarInformarRotaLeituraAction&idLocalidade='+document.forms[0].idLocalidade.value+'&tipo=SetorComercial',document.forms[0].idLocalidade.value,'Localidade', 400, 800);">

					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Setor Comercial" /></a> <logic:present
						name="idSetorComercialNaoEncontrada">
						<logic:equal name="idSetorComercialNaoEncontrada"
							value="exception">
							<html:text property="setorComercialDescricao" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="idSetorComercialNaoEncontrada"
							value="exception">
							<html:text property="setorComercialDescricao" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent
						name="idSetorComercialNaoEncontrada">
						<logic:empty name="PesquisarRotaActionForm"
							property="codigoSetorComercial">
							<html:text property="setorComercialDescricao" value="" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="PesquisarRotaActionForm"
							property="codigoSetorComercial">
							<html:text property="setorComercialDescricao" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent> <a
						href="javascript:limparSetorComercial(document.PesquisarRotaActionForm);">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a>
						</logic:empty>
						<logic:notEmpty name="PesquisarRotaActionForm" property="bloquearCampos">
							<html:text maxlength="3" property="codigoSetorComercial" tabindex="2" size="3" readonly="true" /> 
							<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Setor Comercial" /> 
						<html:text property="setorComercialDescricao" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" /> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> 
						</logic:notEmpty>
						</td>
				</tr>
				<tr>
					<td><strong> Código da Rota:</strong></td>
					<td><html:text property="codigoRota" size="4" maxlength="4" tabindex="3"/></td>
				</tr>
				<tr>
					<td width="14%"><strong>Grupo de Faturamento:</strong></td>
					<td width="86%" colspan="3">
						
						<logic:notPresent name="idFaturamentoGrupo">	
							<html:select
							property="idGrupoFaturamento" tabindex="4">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="collectionFaturamentoGrupo"
								labelProperty="descricao" property="id" />
							</html:select>
						</logic:notPresent>
						<logic:present name="idFaturamentoGrupo">	
							<html:select
							property="idGrupoFaturamento" tabindex="4" disabled="true">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="collectionFaturamentoGrupo"
								labelProperty="descricao" property="id" />
							</html:select>
						</logic:present>
					</td>
				</tr>				
				<tr>
					<td><strong>Indicador de Rota Alternativa:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorRotaAlternativa" value="<%=ConstantesSistema.SIM.toString()%>"
						tabindex="13" /> Sim <html:radio
						property="indicadorRotaAlternativa" value="<%=ConstantesSistema.NAO.toString()%>"/>
					Não </span></strong></td>
				</tr>	
				<tr>
					<td><strong>Empresa de Leitura:<font color="#FF0000">*</font></strong></td>
					<td>
						<c:if test="${idEmpresaLeituristicaRecebida == null}">
						<html:select property="empresaLeituristica" tabindex="5">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="collectionEmpresa" labelProperty="descricao" property="id" />
						</html:select>
						</c:if>
						
						<c:if test="${idEmpresaLeituristicaRecebida != null}">
						<html:select property="empresaLeituristica" tabindex="5" disabled="true">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="collectionEmpresa" labelProperty="descricao" property="id" />
						</html:select>
						</c:if>
					</td>
				</tr>

				<tr>
					<td><strong>Indicador de Uso:</strong></td>
					<td><strong> <span class="style1"><strong> <html:radio
						property="indicadorUso" value="1" /> Ativo <html:radio
						property="indicadorUso" value="2" /> Inativo <html:radio
						property="indicadorUso" value="3"  /> Todos </strong></td>
				</tr>
				</table>
					
				<table width="100%" border="0">
				<tr>
					<td height="24"><INPUT TYPE="button"
						class="bottonRightCol" value="Limpar"
						onclick="document.forms[0].reset();limparLocalidade(document.PesquisarRotaActionForm);limparDescSetorComercial(document.PesquisarRotaActionForm);limparForm();" />
					&nbsp;&nbsp; <logic:present name="caminhoRetornoTelaPesquisaRota">
						<INPUT TYPE="button" class="bottonRightCol" value="Voltar"
							onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaRota}.do')" />
					</logic:present></td>
					<td align="right"><INPUT TYPE="button" onclick="javascript:validarForm(document.forms[0]);" class="bottonRightCol"
						value="Pesquisar" /></td>
				</tr>
				</table>
				
			</td>
		</tr>
	</table>
</body>
</html:form>
</html:html>
