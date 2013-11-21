<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirCategoriaConsumoTarifaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<%int quantidade = 0;%>
<script>

    function required () {
     	this.aa = new Array("consumoMinimo", "Informe Consumo Mínimo.", new Function ("varName", " return this[varName];"));
    	this.ab = new Array("valorTarifaMinima", "Informe Valor da Tarifa Mínima.", new Function ("varName", " return this[varName];"));
    	this.ah = new Array("slcCategoria", "Informe Categoria.", new Function ("varName", " return this[varName];"));
    	this.aj = new Array("slcSubCategoria", "Informe SubCategoria.", new Function ("varName", " return this[varName];"));
    }
    
    function caracteresespeciais () {
     	this.ac = new Array("consumoMinimo", "Consumo Mínimo deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     	this.ad = new Array("valorTarifaMinima", "Valor da Tarifa Mínima deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	}
    function FloatValidations () {
     	this.ae = new Array("valorTarifaMinima", "Valor da Tarifa Mínima deve somente conter números decimais positivos ou zero.", new Function ("varName", " return this[varName];"));
    }
    function IntegerValidations () {
     	this.ag = new Array("consumoMinimo", "Consumo Mínimo deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	}

	function redirecionaForm(){
		var formRed = "/gsan/manterConsumoTarifaExistenteActionSubCategoria.do";
		var form = document.InserirCategoriaConsumoTarifaActionForm;
		if(validateCaracterEspecial(form) && validateRequired(form) && validateInteger(form) && validateDecimal(form)){
			redirecionarSubmit(formRed);
		}
	}
	
	function validaForm(){
		var form = document.InserirCategoriaConsumoTarifaActionForm;
		if(validateRequeridos(form) && validateCaracterEspecial(form) && validateInteger(form) && validateDecimalComZero(form)){
			window.location.href='/gsan/exibirManterCategoriaFaixaConsumoTarifaSubCategoriaAction.do?limpaFaixa=1&parametro1='+document.forms[0].slcCategoria.value+'&parametro2='+document.forms[0].consumoMinimo.value+'&parametro3='+document.forms[0].valorTarifaMinima.value+'&parametro4='+document.forms[0].slcSubCategoria.value;
		}
	}
	
	function validateRequeridos(){
		var form = document.InserirCategoriaConsumoTarifaActionForm;
		if (form.slcCategoria.selectedIndex == 0){
			alert ('Informe Categoria');
			return false;
		}else if (form.slcSubCategoria.selectedIndex == 0){
			alert ('Informe Subcategoria');
			return false;
		}else if (form.consumoMinimo.value.length <= 0){
			alert ('Informe o Consumo Mínimo');
			form.consumoMinimo.focus();
			return false;
		} else if (form.valorTarifaMinima.value.length <= 0){
			alert ('Informe o Valor da Tarifa Mínima');
			form.valorTarifaMinima.focus();
			return false;
		}
		return true;	
	}
	
	function preencherSubCategoria(){
		var form = document.InserirCategoriaConsumoTarifaActionForm;		
			redirecionarSubmit('exibirManterCategoriaConsumoTarifaSubCategoriaAction.do?idCategoria='+form.slcCategoria.value);
	}
	
	function validandoGeralForm(){
		/*var form = document.InserirCategoriaConsumoTarifaActionForm;
		if (form.consumoMinimo.value.length < 1){
			alert ('Informe o Consumo Mínimo');
			form.consumoMinimo.focus();
		} else if (form.valorTarifaMinima.value.length < 1){
			alert ('Informe o Valor da Tarifa Mínima');
			form.valorTarifaMinima.focus();
		} */
	}
</script>
</head>

<logic:equal name="testeInserir" value="false" scope="request">
	<body leftmargin="0" topmargin="0"
		onload=" resizePageSemLink(640,600);">
</logic:equal>
<logic:equal name="testeInserir" value="true" scope="request">
	<body leftmargin="0" topmargin="0"
		onload="chamarReload('exibirManterConsumoTarifaExistenteSubCategoriaAction.do');window.close();">
</logic:equal>
<body leftmargin="0" topmargin="0"
	onload="resizePageSemLink(640,400); setarFoco('${requestScope.slcCategoria}');">

<html:form action="/manterCategoriaConsumoTarifaSubCategoriaAction"
	name="InserirCategoriaConsumoTarifaActionForm"
	onsubmit="return validateInserirCategoriaConsumoTarifaActionForm(this);"
	type="gcom.gui.faturamento.consumotarifa.InserirCategoriaConsumoTarifaActionForm"
	method="post">

	<table width="600" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="600" valign="top" class="centercoltext">
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
					<td class="parabg">Informar Categoria e Subcategoria</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="4">Preencha os campos para inserir uma subcategoria na
					tarifa de consumo</td>
				</tr>
				<tr>
					<td width="27%" height="24"><strong>Categoria:<font color="#FF0000">*</font></strong></td>
					<td colspan="3"><c:if
						test="${empty InserirCategoriaConsumoTarifaActionForm.slcCategoria}"
						var="formVazio">
						<logic:equal name="travar" value="true">
							<html:hidden property="slcCategoria" value="${sessionScope.categoriaSelected}" />
							<html:select style="width: 230px;" property="slcCategoria" onchange="preencherSubCategoria();" value="${sessionScope.categoriaSelected}" disabled="true">
							 <html:option value="-1">&nbsp;</html:option>
								<html:options collection="colecaoCategoriaLista"
									labelProperty="descricao" property="id" />
							</html:select>
						</logic:equal>
						<logic:notEqual name="travar" value="true">
							<html:select property="slcCategoria"
								value="${sessionScope.categoriaSelected}" style="width: 230px;" onchange="preencherSubCategoria();">
								 <html:option value="-1">&nbsp;</html:option>
					
								<html:options collection="colecaoCategoriaLista"
									labelProperty="descricao" property="id" />
							</html:select>
						</logic:notEqual>
					</c:if> <c:if test="${!formVazio}">
						<logic:equal name="travar" value="true">
							<!--<html:hidden property="slcCategoria" value="${sessionScope.categoriaSelected}" />-->
							<html:select property="slcCategoria" style="width: 230px;" onchange="preencherSubCategoria();">
							 <html:option value="-1">&nbsp;</html:option>
                
							<html:options collection="colecaoCategoriaLista"
								labelProperty="descricao" property="id" />
						</html:select>
						</logic:equal>
						<logic:notEqual name="travar" value="true">
							<html:select property="slcCategoria" style="width: 230px;" onchange="preencherSubCategoria();">
							 <html:option value="-1">&nbsp;</html:option>

							<html:options collection="colecaoCategoriaLista"
								labelProperty="descricao" property="id" />
						</html:select>
						</logic:notEqual>
						
						
					</c:if></td>
				</tr>
				<tr>
					<td width="27%" height="24"><strong>Subcategoria:<font color="#FF0000">*</font></strong></td>
					<td colspan="3">
					<logic:present name="colecaoSubCategoriaLista" scope="session">
						<c:if
							test="${empty InserirCategoriaConsumoTarifaActionForm.slcSubCategoria}"
							var="formVazio">
							<logic:equal name="travar" value="true">
								<html:hidden property="slcSubCategoria" value="${sessionScope.subcategoriaSelected}" />
								<html:select style="width: 230px;" property="slcSubCategoria" value="${sessionScope.subcategoriaSelected}" disabled="true">
								 <html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoSubCategoriaLista"
										labelProperty="descricao" property="id" />
								</html:select>
							</logic:equal>
							<logic:notEqual name="travar" value="true">
								<html:select property="slcSubCategoria"
									value="${sessionScope.subcategoriaSelected}" style="width: 230px;">
									 <html:option value="-1">&nbsp;</html:option>
						
									<html:options collection="colecaoSubCategoriaLista"
										labelProperty="descricao" property="id" />
								</html:select>
							</logic:notEqual>
						</c:if> <c:if test="${!formVazio}">
							<logic:equal name="travar" value="true">
								<!--<html:hidden property="slcSubCategoria" value="${sessionScope.subcategoriaSelected}" />-->
								<html:select property="slcSubCategoria" style="width: 230px;">
								 <html:option value="-1">&nbsp;</html:option>
	                
								<html:options collection="colecaoSubCategoriaLista"
									labelProperty="descricao" property="id" />
							</html:select>
							</logic:equal>
							<logic:notEqual name="travar" value="true">
								<html:select property="slcSubCategoria" style="width: 230px;">
								 <html:option value="-1">&nbsp;</html:option>
	
								<html:options collection="colecaoSubCategoriaLista"
									labelProperty="descricao" property="id" />
							</html:select>
							</logic:notEqual>
						</c:if>
					</logic:present>
					
					<logic:notPresent name="colecaoSubCategoriaLista" scope="session">
					<html:select property="slcSubCategoria" style="width: 230px;">
							 <html:option value="-1">&nbsp;</html:option>
						</html:select>
					</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td height="24"><strong>Consumo M&iacute;nimo:<font color="#FF0000">*</font></strong></td>
					<td colspan="3"><html:text maxlength="6" property="consumoMinimo"
						size="6" /></td>
				</tr>
				<tr>
					<td height="24"><strong>Valor da Tarifa M&iacute;nima:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="3"><html:text property="valorTarifaMinima"
						style="text-align:right;" maxlength="17"
						onkeyup="formataValorMonetario(this, 17)" size="17" /></td>
				</tr>
				<tr>
					<td height="25" colspan="3"><strong>Faixas de Consumo:</strong></td>

					<td align="right" width="13%" height="25"><input type="button"
						name="adicionar2" class="bottonRightCol" value="Adicionar"
						onClick="validaForm();"></td>
				</tr>
				<tr>
					<td height="24" colspan="4">
					<table width="100%" bgcolor="#99CCFF">
						<tr>
							<td>
							<div align="center" class="style9"><strong>Remover</strong></div>
							</td>
							<td>
							<div align="center" class="style9"><strong>Limite Superior</strong></div>
							</td>
							<td>
							<div align="center" class="style9"><strong>Valor da Tarifa na
							Faixa</strong></div>
							</td>
						</tr>
						<%String cor = "#cbe5fe";%>

						<logic:present name="colecaoFaixa" scope="session">
							<logic:iterate indexId="posicao" name="colecaoFaixa" id="faixa">
								<%if (cor.equalsIgnoreCase("#cbe5fe")) {
									quantidade++;
				cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
									quantidade++;
				cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>
									<td>
									<div align="center" class="style9"><img
										src="<bean:message key='caminho.imagens'/>Error.gif"
										width="14" height="14" style="cursor:hand;"
										onclick="redirecionarSubmit('excluirCategoriaFaixaConsumoTarifaSubCategoriaAction.do?manter=1&posicao=<bean:write name="faixa" property="ultimaAlteracao.time" />');"></div>

									</td>
									<td>
									<div align="center" class="style9"><bean:write name="faixa"
										property="numeroConsumoFaixaFim" /></div>
									</td>
									<td>
									<div align="center" class="style9"><input type="text"
										style="text-align:right;" maxlength="17"
										onkeyup="formataValorMonetario(this, 17)" size="17"
										name="valorConsumoTarifa<bean:write name="faixa" property="ultimaAlteracao.time" />"
										value="<bean:write name="faixa" formatKey="money.format" property="valorConsumoTarifa" />">
									</div>
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
					</table>
					</td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				
				<tr>
					<td height="27" colspan="4">
					<div align="right"><input type="hidden" name="testeInserir"> 
					<input
						name="Button" type="button" class="bottonRightCol" value="Inserir"
						onClick="document.forms[0].testeInserir.value='true';validarForm();">
					<input name="Button2" type="button" class="bottonRightCol"
						value="Fechar" onClick="javascript:window.close();"></div>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</html:form>
</body>
<script>
	function validarForm(){
		var form = document.InserirCategoriaConsumoTarifaActionForm;
		var qtd = <%=""+quantidade%>
		if(validateRequeridos()){
		if(qtd > 0){
			form.submit();          
		}else{
			alert("Informe Faixas de Consumo.");
		}
		}
	}

</script>
</html:html>
