<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarSubcategoriaActionForm"
	dynamicJavascript="false" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="Javascript">
<!--

var bCancel = false;

function validateFiltrarSubcategoriaActionForm(form){
	if(bCancel){
		return true;
    }else{
       return validateCaracterEspecial(form);
   	}	
}  	
function caracteresespeciais(){
	this.ab = new Array("descricao", "Descrição da Subcategoria possui caracteres especiais.", new Function ("varName", " return this[varName];"));
}

function validarForm(form){
	if(validateFiltrarSubcategoriaActionForm(form)){
	    submeterFormPadrao(form);
	}
}

-->
</script>
</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/filtrarSubcategoriaAction.do" method="post"
	onsubmit="return validateFiltrarSubcategoriaActionForm(this);">

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
			<td valign="top" class="centercoltext">
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Filtrar Subcategoria</td>
					<td width="11" valign="top"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<!-- Início do Corpo - Fernanda Paiva -->
			<p>&nbsp;</p>

			<table bordercolor="#000000" width="100%" cellspacing="3">
				<tr>
					<td colspan="2">
					<p>Para manter a(s) subcategorias(s), informe os dados abaixo:</p>
					<p>&nbsp;</p>
					</td>
					<td><input name="atualizar" type="checkbox"
						checked="checked" value="1"> <strong>Atualizar</strong></td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroSubcategoriaFiltrar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>								
					</logic:notPresent>
					</tr>
				</table>
				<table bordercolor="#000000" width="100%" cellspacing="3">
				<tr>
					<td><strong>Categoria:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><strong> <html:select property="idCategoria">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="collectionImovelCategoria"
							labelProperty="descricao" property="id" />
					</html:select></strong></div>
					</td>
				</tr>
				<tr>
					<td><strong> Descrição da Subcategoria:</strong></td>
					<td><html:text property="descricao" size="50" maxlength="50" /></td>
				</tr>
				<tr>
					<td><strong> Descrição Abreviada:</strong></td>
					<td><html:text property="descricaoAbreviada" size="20" maxlength="20" /></td>
				</tr>
				<tr>
					<td><strong> Código da Tarifa Social:</strong></td>
					<td><html:text property="codigoTarifaSocial" size="1" maxlength="1" /></td>
				</tr>
				<tr>
					<td><strong> Código do Grupo da Subcategoria:</strong></td>
					<td><html:text property="codigoGrupoSubcategoria" size="4" maxlength="4" /></td>
				</tr>
				<tr>
					<td><strong> Fator de Fiscalização:</strong></td>
					<td><html:text property="numeroFatorFiscalizacao" size="2" maxlength="2" /></td>
				</tr>
				<tr>
					<td><strong>Indicador da Tarifa de Consumo:</strong></td>
					<td>
						<input type="radio" name="indicadorTarifaConsumo" value="<%=(ConstantesSistema.INDICADOR_USO_ATIVO.toString())%>"/><strong>Sim</strong>
    					<input type="radio" name="indicadorTarifaConsumo" value="<%=(ConstantesSistema.INDICADOR_USO_DESATIVO.toString())%>"/><strong>Não</strong>
    					<input type="radio" name="indicadorTarifaConsumo" value="" checked><strong>Todos</strong>
    				</td>
				</tr>
				<tr>
					<td><strong>Indicador de Sazonalidade de Abastecimento:</strong></td>
					<td>
						<input type="radio" name="indicadorSazonalidade" value="<%=(ConstantesSistema.INDICADOR_USO_ATIVO.toString())%>"/><strong>Sim</strong>
    					<input type="radio" name="indicadorSazonalidade" value="<%=(ConstantesSistema.INDICADOR_USO_DESATIVO.toString())%>"/><strong>Não</strong>
    					<input type="radio" name="indicadorSazonalidade" value="" checked><strong>Todos</strong>
    				</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Uso:</strong></td>
					<td>
						<input type="radio" name="indicadorUso" value="<%=(ConstantesSistema.INDICADOR_USO_ATIVO.toString())%>"/><strong>Ativo</strong>
    					<input type="radio" name="indicadorUso" value="<%=(ConstantesSistema.INDICADOR_USO_DESATIVO.toString())%>"/><strong>Inativo</strong>
    					<input type="radio" name="indicadorUso" value="" checked><strong>Todos</strong>
    				</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>
						<strong> <font color="#ff0000"> <input name="Submit22"
						class="bottonRightCol" value="Limpar" type="button"
						onclick="window.location.href='/gsan/exibirFiltrarSubcategoriaAction.do?menu=sim';">
						</font></strong>
					</td>
					<td align="right"></td>
					<td align="right">
					<gsan:controleAcessoBotao name="Button" value="Filtrar" 
					onclick="javascript:validarForm(document.forms[0]);" url="filtrarSubcategoriaAction.do" align="left"/><!-- 
						<input type="button" name="Button" class="bottonRightCol" value="Filtrar" onClick="javascript:validarForm(document.forms[0])"/>-->
					</td>
				</tr>
			</table>

			<p>&nbsp;</p>
			<!-- Fim do Corpo - Fernanda Paiva --></td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
