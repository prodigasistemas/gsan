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
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="Javascript">
<!--

var bCancel = false;

function validatePesquisarActionForm(form){
	if(bCancel){
		return true;
    }else{
       return testarCampoValorZero(document.PesquisarActionForm.id, 'Código da Categoria') && validateCaracterEspecial(form) && validateLong(form);
   	}	
}   	

function caracteresespeciais(){
	this.aa = new Array("id", "Código da Categoria possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("descricao", "Descrição da Categoria possui caracteres especiais.", new Function ("varName", " return this[varName];"));
}

function IntegerValidations(){
	this.ab = new Array("id", "Código da Categoria deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
}

function validarForm(form){
	if(validatePesquisarActionForm(form)){
	    form.submit();
	}
}

function limparForm(){
	var form = document.PesquisarActionForm;
	    
	form.id.value = "";
	form.descricao.value = "";
    form.indicadorUso.value = "";
    form.atualizar.value = "1";
}
-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form
  action="/filtrarCategoriaAction.do"
  method="post"
  onsubmit="return validatePesquisarActionForm(this);"
>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

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
              <tr><td></td></tr>
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
                <td class="parabg">Filtrar Categoria</td>
                <td width="11" valign="top"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
              </tr>
            </table>
	        <!-- Início do Corpo - Roberta Costa -->
	        <p>&nbsp;</p>
	        
            <table bordercolor="#000000" width="100%" cellspacing="0" border="0"> 
				<tr>
					<td width="60%">
						<p>Para manter a(s) categorias(s), informe os dados abaixo:</p><p>&nbsp;</p>
					</td>
					<td align="right">
						<input name="atualizar" type="checkbox" checked="checked" value="1"> <strong>Atualizar</strong>
					</td>
					<logic:present scope="application" name="urlHelp">
								<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroCategoriaFiltrar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
								<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
                </tr>
                </table>
           <table bordercolor="#000000" width="100%" cellspacing="0" border="0">       
                <tr>
                	<td colspan="3">
                		<table border="0" width="100%">
							<tr>
								<td width="30%"><strong>C&oacute;digo da Categoria:</strong></td>
								<td><html:text property="id" size="2" maxlength="2"/></td>
							</tr>
							<tr>
								<td><strong> Descrição da Categoria:</strong></td>
								<td><html:text property="descricao" size="15" maxlength="15"/></td>
							</tr>
							<tr>
								<td>
									<strong>Tipo da Categoria:</strong>
								</td>
								<td>
									<html:select property="tipoCategoria" tabindex="3">
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<html:options collection="colecaoTipoCategoria" labelProperty="descricao" property="id" />
				                   	</html:select>
				                </td>
							</tr>
							<tr>
				                <td><strong>Indicador de Uso:</strong></td>
								<td>
									<html:radio property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>"/>
									<strong>Ativo</strong>
				           			<html:radio property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>"/>
			                   		<strong>Inativo</strong>
			                   		<input type="radio" name="indicadorUso" value="" checked><strong>Todos</strong>
								</td>
							</tr>
						</table>
					</td>
				</tr>			
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="left" width="50%">
						<input type="button" class="bottonRightCol" value="Limpar" 
							onclick="javascript:limparForm();">
					</td>
					<td align="right" colspan="2">
						<gsan:controleAcessoBotao name="Button" 
							value="Filtrar" 
							onclick="javascript:validarForm(document.forms[0]);" 
						 	url="filtrarCategoriaAction.do"
						 	align="left"/>
						<!-- 
						<input type="button" class="bottonRightCol" value="Filtrar"
							onClick="javascript:validarForm(document.forms[0])"/> -->
					</td>
				</tr>	
            </table>
          	
          	<p>&nbsp;</p>
			<!-- Fim do Corpo - Roberta Costa -->          	
		</td>
	</tr>
</table>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>