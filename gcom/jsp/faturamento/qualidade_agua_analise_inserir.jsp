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
<%--<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InformarParametrosSistemaActionForm" dynamicJavascript="false" />--%>
<html:javascript formName="InserirQualidadeAguaActionForm"
	dynamicJavascript="false" staticJavascript="true" />
	
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script>

	var bCancel = false;

	function validateInserirQualidadeAguaActionForm(form) {

		if (bCancel){
			return true;
		}else{
			
			return validateCaracterEspecial(form) && 
				//validateRequired(form) && 
				validateLong(form) ;
		}
	}
	
 
	
	function IntegerValidations () {
		this.aa = new Array("quantidadeTurbidezExigidas", "Quantidade Turbidez Exigidas deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("quantidadeTurbidezAnalisadas", "Quantidade Turbidez Analisadas deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ac = new Array("quantidadeTurbidezConforme", "Quantidade Turbidez Conforme deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ad = new Array("quantidadeCorExigidas", "Quantidade Cor Exigidas deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ae = new Array("quantidadeCorAnalisadas", "Quantidade Cor Analisadas deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.af = new Array("quantidadeCorConforme", "Quantidade Cor Conforme deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ag = new Array("quantidadeCloroExigidas", "Quantidade Cloro Exigidas deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ah = new Array("quantidadeCloroAnalisadas", "Quantidade Cloro Analisadas deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ai = new Array("quantidadeCloroConforme", "Quantidade Cloro Conforme deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.aj = new Array("quantidadeFluorExigidas", "Quantidade Fluor Exigidas deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.al = new Array("quantidadeFluorAnalisadas", "Quantidade Fluor Analisadas deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.am = new Array("quantidadeFluorConforme", "Quantidade Fluor Conforme deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.an = new Array("quantidadeColiformesTotaisExigidas", "Quantidade Coliformes Totais Exigidas deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ao = new Array("quantidadeColiformesTotaisAnalisadas", "Quantidade Coliformes Totais Analisadas deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ap = new Array("quantidadeColiformesTotaisConforme", "Quantidade Coliformes Totais Conforme deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.aq = new Array("quantidadeColiformesFecaisExigidas", "Quantidade Coliformes Fecais Exigidas deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ar = new Array("quantidadeColiformesFecaisAnalisadas", "Quantidade Coliformes Fecais Analisadas deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.as = new Array("quantidadeColiformesFecaisConforme", "Quantidade Coliformes Fecais Conforme deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.at = new Array("quantidadeColiformesTermotolerantesExigidas", "Quantidade Coliformes Termotolerantes Exigidas deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.au = new Array("quantidadeColiformesTermotolerantesAnalisadas", "Quantidade Coliformes Termotolerantes Analisadas deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.av = new Array("quantidadeColiformesTermotolerantesConforme", "Quantidade Coliformes Termotolerantes Conforme deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ax = new Array("quantidadeAlcalinidadeExigidas", "Quantidade Alcalinidade Exigidas deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ay = new Array("quantidadeAlcalinidadeAnalisadas", "Quantidade Alcalinidade Analisadas deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.az = new Array("quantidadeAlcalinidadeConforme", "Quantidade Alcalinidade Conforme deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ba = new Array("quantidadeEColiExigidas", "Quantidade EColi Exigidas deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.bb = new Array("quantidadeEColiAnalisadas", "Quantidade EColi Analisadas deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.bc = new Array("quantidadeEColiConforme", "Quantidade EColi Conforme deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		
	}

	

	function caracteresespeciais () {
		this.aa = new Array("quantidadeTurbidezExigidas", "Quantidade Turbidez Exigidas possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("quantidadeTurbidezAnalisadas", "Quantidade Turbidez Analisadas possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ac = new Array("quantidadeTurbidezConforme", "Quantidade Turbidez Conforme possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ad = new Array("quantidadeCorExigidas", "Quantidade Cor Exigidas possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ae = new Array("quantidadeCorAnalisadas", "Quantidade Cor Analisadas possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.af = new Array("quantidadeCorConforme", "Quantidade Cor Conforme possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ag = new Array("quantidadeCloroExigidas", "Quantidade Cloro Exigidas possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ah = new Array("quantidadeCloroAnalisadas", "Quantidade Cloro Analisadas possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ai = new Array("quantidadeCloroConforme", "Quantidade Cloro Conforme possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.aj = new Array("quantidadeFluorExigidas", "Quantidade Fluor Exigidas possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.al = new Array("quantidadeFluorAnalisadas", "Quantidade Fluor Analisadas possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.am = new Array("quantidadeFluorConforme", "Quantidade Fluor Conforme possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.an = new Array("quantidadeColiformesTotaisExigidas", "Quantidade Coliformes Totais Exigidas possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ao = new Array("quantidadeColiformesTotaisAnalisadas", "Quantidade Coliformes Totais Analisadas possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ap = new Array("quantidadeColiformesTotaisConforme", "Quantidade Coliformes Totais Conforme possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.aq = new Array("quantidadeColiformesFecaisExigidas", "Quantidade Coliformes Fecais Exigidas possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ar = new Array("quantidadeColiformesFecaisAnalisadas", "Quantidade Coliformes Fecais Analisadas possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.as = new Array("quantidadeColiformesFecaisConforme", "Quantidade Coliformes Fecais Conforme possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.at = new Array("quantidadeColiformesTermotolerantesExigidas", "Quantidade Coliformes Termotolerantes Exigidas possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.au = new Array("quantidadeColiformesTermotolerantesAnalisadas", "Quantidade Coliformes Termotolerantes Analisadas possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.av = new Array("quantidadeColiformesTermotolerantesConforme", "Quantidade Coliformes Termotolerantes Conforme possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ax = new Array("quantidadeAlcalinidadeExigidas", "Quantidade Alcalinidade Exigidas possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ay = new Array("quantidadeAlcalinidadeAnalisadas", "Quantidade Alcalinidade Analisadas possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.az = new Array("quantidadeAlcalinidadeConforme", "Quantidade Alcalinidade Conforme possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ba = new Array("quantidadeEColiExigidas", "Quantidade EColi Exigidas possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.bb = new Array("quantidadeEColiAnalisadas", "Quantidade EColi Analisadas possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.bc = new Array("quantidadeEColiConforme", "Quantidade EColi Conforme possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		
	}

	//function required () {
		
	//	this.aa = new Array("referencia", "Informe Referência.", new Function ("varName", " return this[varName];"));
		
//	}


	

	
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/inserirQualidadeAguaWizardAction" method="post"
	onsubmit="return validateInserirQualidadeAguaActionForm(this);">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=2" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<input type="hidden" name="numeroPagina" value="1" />
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext">
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
			<td width="655" valign="top" class="centercoltext">
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
					<td class="parabg">Inserir Qualidade da Água</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0" dwcopytype="CopyTableRow">
				<tr>
					<td>Para adicionar a qualidade da água, informe os dados abaixo:
					<td align="right"></td>
				</tr>
			</table>

			<table width="100%" border="0">

				

				<tr>
					<td>&nbsp;</td>
				</tr>


				<logic:present name="inserirTodosAtivado" scope="request">
					<tr>
						<td colspan="6">
						<table width="100%" align="center" bgcolor="#99CCFF">
							<tr>
								<td width="25%" align="center">&nbsp;</td>
								<td width="25%" align="center"><strong>Exigidas:</strong></td>
								<td width="25%" align="center"><strong>Analisadas:</strong></td>
								<td width="25%" align="center"><strong>Conforme:</strong></td>
							</tr>

							<tr bgcolor="#cbe5fe">
								<td width="25%"><strong>Quantidade Turbidez:</strong></td>
								<td width="25%" align="center"><html:text
									property="quantidadeTurbidezExigidas" size="4" maxlength="4"
									tabindex="1" readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeTurbidezAnalisadas" size="4" maxlength="4"
									tabindex="1" readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeTurbidezConforme" size="4" maxlength="4"
									tabindex="1" readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									onkeypress="return isCampoNumerico(event);"/></td>
							</tr>

							<tr bgcolor="#cbe5fe">
								<td width="25%"><strong>Quantidade Cor:</strong></td>
								<td width="25%" align="center"><html:text
									property="quantidadeCorExigidas" size="4" maxlength="4"
									tabindex="1" readonly="true"
									style="background-color:#EFEFEF; border:0;" /></td>
								<td width="25%" align="center"><html:text
									property="quantidadeCorAnalisadas" size="4" maxlength="4"
									tabindex="1" readonly="true"
									style="background-color:#EFEFEF; border:0;" /></td>
								<td width="25%" align="center"><html:text
									property="quantidadeCorConforme" size="4" maxlength="4"
									tabindex="1" readonly="true"
									style="background-color:#EFEFEF; border:0;" /></td>
							</tr>

							<tr bgcolor="#cbe5fe">
								<td width="25%"><strong>Quantidade Cloro:</strong></td>
								<td width="25%" align="center"><html:text
									property="quantidadeCloroExigidas" size="4" maxlength="4"
									tabindex="1" readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeCloroAnalisadas" size="4" maxlength="4"
									tabindex="1" readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeCloroConforme" size="4" maxlength="4"
									tabindex="1" readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									onkeypress="return isCampoNumerico(event);"/></td>
							</tr>

							<tr bgcolor="#cbe5fe">
								<td width="25%"><strong>Quantidade Fluor:</strong></td>
								<td width="25%" align="center"><html:text
									property="quantidadeFluorExigidas" size="4" maxlength="4"
									tabindex="1" readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeFluorAnalisadas" size="4" maxlength="4"
									tabindex="1" readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeFluorConforme" size="4" maxlength="4"
									tabindex="1" readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									onkeypress="return isCampoNumerico(event);"/></td>
							</tr>
							
							<tr bgcolor="#cbe5fe">
								<td width="25%"><strong>Quantidade EColi:</strong></td>
								<td width="25%" align="center"><html:text
									property="quantidadeEColiExigidas" size="4" maxlength="4"
									tabindex="1" readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeEColiAnalisadas" size="4" maxlength="4"
									tabindex="1" readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeEColiConforme" size="4" maxlength="4"
									tabindex="1" readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									onkeypress="return isCampoNumerico(event);"/></td>
							</tr>

							<tr bgcolor="#cbe5fe">
								<td width="25%"><strong>Quantidade Coliformes Totais :</strong></td>
								<td width="25%" align="center"><html:text
									property="quantidadeColiformesTotaisExigidas" size="4"
									maxlength="4" tabindex="1" readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeColiformesTotaisAnalisadas" size="4"
									maxlength="4" tabindex="1" readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeColiformesTotaisConforme" size="4"
									maxlength="4" tabindex="1" readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									onkeypress="return isCampoNumerico(event);"/></td>
							</tr>

							<tr bgcolor="#cbe5fe">
								<td width="25%"><strong>Quantidade Coliformes Fecais :</strong></td>
								<td width="25%" align="center"><html:text
									property="quantidadeColiformesFecaisExigidas" size="4"
									maxlength="4" tabindex="1" readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeColiformesFecaisAnalisadas" size="4"
									maxlength="4" tabindex="1" readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeColiformesFecaisConforme" size="4"
									maxlength="4" tabindex="1" readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									onkeypress="return isCampoNumerico(event);"/></td>
							</tr>

							<tr bgcolor="#cbe5fe">
								<td width="25%"><strong>Quantidade Coliformes Termotolerantes :</strong></td>
								<td width="25%" align="center"><html:text
									property="quantidadeColiformesTermotolerantesExigidas" size="4"
									maxlength="4" tabindex="1" readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeColiformesTermotolerantesAnalisadas"
									size="4" maxlength="4" tabindex="1" readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeColiformesTermotolerantesConforme" size="4"
									maxlength="4" tabindex="1" readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									onkeypress="return isCampoNumerico(event);"/></td>
							</tr>
							
							<tr bgcolor="#cbe5fe">
								<td width="25%"><strong>Quantidade Alcalinidade :</strong></td>
								<td width="25%" align="center"><html:text
									property="quantidadeAlcalinidadeExigidas" size="4"
									maxlength="4" tabindex="1" readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeAlcalinidadeAnalisadas"
									size="4" maxlength="4" tabindex="1" readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeAlcalinidadeConforme" size="4"
									maxlength="4" tabindex="1" readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									onkeypress="return isCampoNumerico(event);"/></td>
							</tr>
							
						</table>
						</td>
					</tr>



					<tr>
						<td>&nbsp;</td>
					</tr>
				</logic:present>

				<logic:notPresent name="inserirTodosAtivado" scope="request">
					<tr>
						<td colspan="6">
						<table width="100%" align="center" bgcolor="#99CCFF">
							<tr>
								<td width="25%" align="center">&nbsp;</td>
								<td width="25%" align="center"><strong>Exigidas:</strong></td>
								<td width="25%" align="center"><strong>Analisadas:</strong></td>
								<td width="25%" align="center"><strong>Conforme:</strong></td>
							</tr>

							<tr bgcolor="#cbe5fe">
								<td width="25%"><strong>Quantidade Turbidez:</strong></td>
								<td width="25%" align="center"><html:text
									property="quantidadeTurbidezExigidas" size="5" maxlength="5"
									tabindex="1" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeTurbidezAnalisadas" size="5" maxlength="5"
									tabindex="1" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeTurbidezConforme" size="5" maxlength="5"
									tabindex="1" 
									onkeypress="return isCampoNumerico(event);"/></td>
							</tr>

							<tr bgcolor="#cbe5fe">
								<td width="25%"><strong>Quantidade Cor:</strong></td>
								<td width="25%" align="center"><html:text
									property="quantidadeCorExigidas" size="5" maxlength="5"
									tabindex="1" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeCorAnalisadas" size="5" maxlength="5"
									tabindex="1" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeCorConforme" size="5" maxlength="5"
									tabindex="1" 
									onkeypress="return isCampoNumerico(event);"/></td>
							</tr>

							<tr bgcolor="#cbe5fe">
								<td width="25%"><strong>Quantidade Cloro:</strong></td>
								<td width="25%" align="center"><html:text
									property="quantidadeCloroExigidas" size="5" maxlength="5"
									tabindex="1" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeCloroAnalisadas" size="5" maxlength="5"
									tabindex="1" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeCloroConforme" size="5" maxlength="5"
									tabindex="1" 
									onkeypress="return isCampoNumerico(event);"/></td>
							</tr>

							<tr bgcolor="#cbe5fe">
								<td width="25%"><strong>Quantidade Fluor:</strong></td>
								<td width="25%" align="center"><html:text
									property="quantidadeFluorExigidas" size="5" maxlength="5"
									tabindex="1" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeFluorAnalisadas" size="5" maxlength="5"
									tabindex="1" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeFluorConforme" size="5" maxlength="5"
									tabindex="1" 
									onkeypress="return isCampoNumerico(event);"/></td>
							</tr>
							
							<tr bgcolor="#cbe5fe">
								<td width="25%"><strong>Quantidade EColi:</strong></td>
								<td width="25%" align="center"><html:text
									property="quantidadeEColiExigidas" size="5" maxlength="5"
									tabindex="1" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeEColiAnalisadas" size="5" maxlength="5"
									tabindex="1" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeEColiConforme" size="5" maxlength="5"
									tabindex="1" 
									onkeypress="return isCampoNumerico(event);"/></td>
							</tr>

							<tr bgcolor="#cbe5fe">
								<td width="25%"><strong>Quantidade Coliformes Totais :</strong></td>
								<td width="25%" align="center"><html:text
									property="quantidadeColiformesTotaisExigidas" size="5"
									maxlength="5" tabindex="1" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeColiformesTotaisAnalisadas" size="5"
									maxlength="5" tabindex="1" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeColiformesTotaisConforme" size="5"
									maxlength="5" tabindex="1" 
									onkeypress="return isCampoNumerico(event);"/></td>
							</tr>

							<tr bgcolor="#cbe5fe">
								<td width="25%"><strong>Quantidade Coliformes Fecais :</strong></td>
								<td width="25%" align="center"><html:text
									property="quantidadeColiformesFecaisExigidas" size="5"
									maxlength="5" tabindex="1" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeColiformesFecaisAnalisadas" size="5"
									maxlength="5" tabindex="1"
									onkeypress="return isCampoNumerico(event);" /></td>
								<td width="25%" align="center"><html:text
									property="quantidadeColiformesFecaisConforme" size="5"
									maxlength="5" tabindex="1" 
									onkeypress="return isCampoNumerico(event);"/></td>
							</tr>

							<tr bgcolor="#cbe5fe">
								<td width="25%"><strong>Quantidade Coliformes Termotolerantes :</strong></td>
								<td width="25%" align="center"><html:text
									property="quantidadeColiformesTermotolerantesExigidas" size="5"
									maxlength="5" tabindex="1" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeColiformesTermotolerantesAnalisadas"
									size="5" maxlength="5" tabindex="1" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeColiformesTermotolerantesConforme" size="5"
									maxlength="5" tabindex="1" 
									onkeypress="return isCampoNumerico(event);"/></td>
							</tr>
							
							<tr bgcolor="#cbe5fe">
								<td width="25%"><strong>Quantidade Alcalinidade:</strong></td>
								<td width="25%" align="center"><html:text
									property="quantidadeAlcalinidadeExigidas" size="5"
									maxlength="5" tabindex="1" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeAlcalinidadeAnalisadas"
									size="5" maxlength="5" tabindex="1" 
									onkeypress="return isCampoNumerico(event);"/></td>
								<td width="25%" align="center"><html:text
									property="quantidadeAlcalinidadeConforme" size="5"
									maxlength="5" tabindex="1" 
									onkeypress="return isCampoNumerico(event);"/></td>
							</tr>
							
						</table>
						</td>
					</tr>



					<tr>
						<td>&nbsp;</td>
					</tr>
				</logic:notPresent>
				<tr>
					<td></td>
					<td colspan="4" align="center"><strong><font color="#FF0000">*</font></strong>Campo
					obrigat&oacute;rio</td>
				</tr>

				<tr>
					<td colspan="6">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=2" />
					</div>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
