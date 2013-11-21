<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page import="gcom.cadastro.tarifasocial.TarifaSocialCartaoTipo, gcom.util.ConstantesSistema"%>

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<SCRIPT LANGUAGE="JavaScript">

	function validarForm(form) {

		if (validateTarifaSocialCartaoTipoActionForm(form)) {
	
			var numeroMaximoMeses = trim(form.numeroMaximoMeses.value);
	
			if ((numeroMaximoMeses == 0 || numeroMaximoMeses > 99) && numeroMaximoMeses != numeroMaximoMeses.length > 0) {
				alert("No. Máximo de Meses para Adesão fora da faixa permitido.");
			} else {
	    		submeterFormPadrao(form);
			}
		}

 	}
</SCRIPT>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="TarifaSocialCartaoTipoActionForm" />
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/atualizarTarifaSocialCartaoTipoAction.do"
	name="TarifaSocialCartaoTipoActionForm"
	type="gcom.gui.cadastro.tarifasocial.TarifaSocialCartaoTipoActionForm"
	method="post"
	onsubmit="return validateTarifaSocialCartaoTipoActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellpadding="0" cellspacing="5">
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
			<td valign="top" class="centercoltext"><!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><html:img src="imagens/parahead_left.gif" border="0" /></td>
					<td class="parabg">Atualizar Tipo de Cart&atilde;o da Tarifa Social</td>
					<td width="11" valign="top"><html:img
						src="imagens/parahead_right.gif" border="0" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para atualizar o tipo de cart&atilde;o da tarifa
					social, informe os dados abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroTarifaSocialCartaoAtualizar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>	
				</tr>
				<tr>
					<td width="35%"><strong>Código:<font color="#FF0000"></font></strong></td>
					<td><strong><bean:write name="tarifaSocialCartaoTipo" property="id" /></strong></td>
				<tr>
					<td width="35%"><strong>Descri&ccedil;&atilde;o do Tipo de
					Cart&atilde;o:<font color="#FF0000">*</font></strong></td>
					<td><html:text maxlength="20" property="descricao" size="20"
						tabindex="1" /></td>
				</tr>
				<tr>
					<td><strong>Descri&ccedil;&atilde;o Abreviada:<font color="#FF0000">*</font></strong></td>
					<td align="right">
					<div align="left"><html:text maxlength="2"
						property="descricaoAbreviada" size="2" tabindex="2" /></div>
					</td>
				</tr>
				<tr>
					<td><strong>Tipo de Cart&atilde;o tem validade?</strong></td>
					<td align="right">
					<div align="left"><html:radio property="validade" tabindex="3"
						value="<%=(TarifaSocialCartaoTipo.INDICADOR_EXISTENCIA_VALIDADE_SIM).toString()%>" /><strong>Sim
					<html:radio property="validade" tabindex="4"
						value="<%=(TarifaSocialCartaoTipo.INDICADOR_EXISTENCIA_VALIDADE_NAO).toString()%>" />N&atilde;o</strong>
					</div>
					</td>
				</tr>
				<tr>
					<td width="40%"><strong>No. M&aacute;ximo de Meses para Ades&atilde;o:</strong></td>
					<td align="right">
					<div align="left"><html:text maxlength="2"
						property="numeroMaximoMeses" size="2" tabindex="5" /></div>
					</td>
				</tr>
				<tr>
					<td><strong>Indicador de Uso:</strong></td>
					<td align="right">
					<div align="left"><html:radio property="indicadorUso" tabindex="6"
						value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>" /> <strong>Ativo</strong>
					<html:radio property="indicadorUso" tabindex="7"
						value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>" />
					<strong>Inativo</strong></div>
					</td>
				</tr>
				<tr>
					<td><strong><font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>

					<td><input name="Submit222" class="bottonRightCol" value="Voltar"
						type="button"
						onclick="window.location.href='/gsan/filtrarTarifaSocialCartaoTipoAction.do';">


					<input name="Submit22" class="bottonRightCol" value="Desfazer"
						type="button"
						onclick="window.location.href='/gsan/exibirAtualizarTarifaSocialCartaoTipoAction.do?idRegistroAtualizacao=<bean:write  name="tarifaSocialCartaoTipo" property="id" />';">
					<input name="Submit23" class="bottonRightCol" value="Cancelar"
						type="button"
						onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
					</font>
					</strong>
					<td>&nbsp;</td>
					<td align="right">
						<gsan:controleAcessoBotao name="Button" 
							value="Atualizar"
							onclick="javascript:validarForm(document.forms[0]);" 
							url="atualizarTarifaSocialCartaoTipoAction.do"/>
					</td>

				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>

