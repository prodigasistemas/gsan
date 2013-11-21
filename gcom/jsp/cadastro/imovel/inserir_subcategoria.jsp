<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="gcom.util.ConstantesSistema" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>
	EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="InserirSubcategoriaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
<!-- Begin
var bCancel = false;

function validateInserirSubcategoriaActionForm(form){
	if(bCancel){
		return true;
    }else{
       return testarCampoValorZero(document.InserirSubcategoriaActionForm.codigoSubcategoria, 'Código da Subcategoria') 
       && validateCaracterEspecial(form) 
       && validateRequired(form) 
       && validateLong(form);
   	}	
}  	
function caracteresespeciais(){
	this.aa = new Array("codigoSubcategoria", "Código da Subcategoria possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("descricaoSubcategoria", "Descrição da Subcategoria possui caracteres especiais.", new Function ("varName", " return this[varName];"));
}

function IntegerValidations(){
	this.ab = new Array("codigoSubcategoria", "Código da Subcategoria deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
}

function required () {
	this.aa = new Array("categoria", "Informe Categoria.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("codigoSubcategoria", "Informe Código da Subcategoria.", new Function ("varName", " return this[varName];"));
	this.ac = new Array("descricaoSubcategoria", "Informe Descrição da Subcategoria.", new Function ("varName", " return this[varName];"));
}

function validarForm(form){
    if (validateInserirSubcategoriaActionForm(form)){
      urlRedirect = "/gsan/inserirSubcategoriaAction.do";
      redirecionarSubmit(urlRedirect);
    }
}


function verificarChecado(){
var form = document.InserirSubcategoriaActionForm;

	if(form.indicadorSazonalidade[0].checked == false && form.indicadorSazonalidade[1].checked == false){
		form.indicadorSazonalidade[1].checked = true;
	}
}


-->
</script>
</head>

<body leftmargin="5" topmargin="5" onload="javascript:verificarChecado();">
<html:form action="/exibirInserirSubcategoriaAction.do" name="InserirSubcategoriaActionForm" 
	type="gcom.gui.cadastro.imovel.InserirSubcategoriaActionForm" method="post">

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
					<td class="parabg">Inserir Subcategoria</td>
					<td width="11" valign="top"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Início do Corpo Fernanda Paiva-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para adicionar a subcategoria, informe os dados
					abaixo:
					</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroSubcategoriaInserir', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>								
					</logic:notPresent>
					</tr>
			</table>	
			<table width="100%" border="0">				
				<tr>
					<td><strong>Categoria:<font color="#FF0000">*</font></strong></td>
					<td colspan="3" align="right">
						<div align="left">
							<strong>	
								<html:select property="categoria">
									<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="collectionImovelCategoria" labelProperty="descricao" property="id" />
								</html:select>
							</strong>
						</div>
					</td>
				</tr>
				<tr>
					<td><strong>C&oacute;digo da Subcategoria:<font color="#FF0000">*</font></strong></td>
					<td align="right">
						<div align="left">
							<html:text property="codigoSubcategoria" size="4" maxlength="4"/>
						</div>
					</td>
				</tr>
				<tr>
					<td><strong>Descri&ccedil;&atilde;o da Subcategoria:<font
						color="#FF0000">*</font></strong></td>
					<td align="right">
					<div align="left"><html:text property="descricaoSubcategoria" size="50"
						maxlength="50"/></div>
					</td>
				</tr>
				<tr>
					<td><strong>Descri&ccedil;&atilde;o Abreviada:</strong></td>
					<td align="right">
					<div align="left"><html:text property="descricaoAbreviada" size="20"
						maxlength="20"/></div>
					</td>
				</tr>
				<tr>
					<td><strong>Código da Tarifa Social:</strong></td>
					<td align="right">
					<div align="left"><html:text property="codigoTarifaSocial" size="1"
						maxlength="1"/></div>
					</td>
				</tr>
				<tr>
					<td><strong>Código do Grupo da Subcategoria:</strong></td>
					<td align="right">
					<div align="left"><html:text property="codigoGrupoSubcategoria" size="4"
						maxlength="4"/></div>
					</td>
				</tr>
				<tr>
					<td><strong>Fator de Fiscalização:</strong> <font color="FF0000">*</font></td>
					<td align="right">
					<div align="left"><html:text property="numeroFatorFiscalizacao" size="2"
						maxlength="2" value ="10" /></div>
					</td>
				</tr>
				<tr>
					<td><strong>Ind. de Tarifa de Consumo: </strong></td>
					<td>
						<html:radio property="indicadorTarifaConsumo" 
							value="<%=""+ConstantesSistema.INDICADOR_USO_ATIVO%>" 
							tabindex="4" /><strong>Sim</strong> 

						<html:radio property="indicadorTarifaConsumo"  
							value="<%=""+ConstantesSistema.INDICADOR_USO_DESATIVO%>"
							tabindex="5"/><strong>Não</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>Ind. de Sazonalidade de Abastecimento  <font color="FF0000">*</font></strong></td>
					<td>
						<html:radio property="indicadorSazonalidade" 
							value="1" 
							tabindex="4" /><strong>Sim</strong> 

						<html:radio property="indicadorSazonalidade"  
							value="2"
							tabindex="5"/><strong>Não</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>
					<td align="left">
						
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirInserirSubcategoriaAction.do?menu=sim"/>'">
					<input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
					<td align="right">
						<gsan:controleAcessoBotao name="Button" value="Inserir"
							  onclick="javascript:validarForm(document.forms[0]);" url="inserirSubcategoriaAction.do"/>
						<!--
						<input type="button" name="Button" class="bottonRightCol" value="Inserir" onClick="javascript:validarForm(document.forms[0])"/>-->
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
