<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript dynamicJavascript="false" staticJavascript="false"  formName="ConjuntoTramitacaoRaActionForm"/>

<script>	
<!--

function validarForm(form){
	if(validarDados(form) && validateDecimalZeroPositivo(form)){
		submeterFormPadrao(form);
		window.close();
	}
}	


function validarDados(form){
	if (form.idPavimentoRua.value != null && trim(form.idPavimentoRua.value) != "-1" ) {
		if (form.metragemPavimentoRua.value == null || trim(form.metragemPavimentoRua.value) == "" ) {
			alert("Informe metragem de pavimento de rua.");
			return false;
		}
	}
	
	if (form.idPavimentoCalcada.value != null && trim(form.idPavimentoCalcada.value) != "-1" ) {
		if (form.metragemPavimentoCalcada.value == null || trim(form.metragemPavimentoCalcada.value) == "" ) {
			alert("Informe metragem de pavimento de calçada.");
			return false;
		}
	}
	
	return true;
	
}

function DecimalZeroPositivoValidations () {
    this.aa = new Array("metragemPavimentoRua", "Metragem de pavimento de rua deve somente conter números decimais.", new Function ("varName", " return this[varName];"));
    this.ab = new Array("metragemPavimentoCalcada", "Metragem de pavimento de calçada deve somente conter números decimais.", new Function ("varName", " return this[varName];"));     
}

-->
</script>


</head>
<body leftmargin="5" topmargin="5" onload="window.focus();setarFoco('${requestScope.nomeCampo}');resizePageSemLink(600, 500);">

<html:form action="/inserirDadosPavimentoOrdemServicoPopupAction" method="post"
	name="ConjuntoTramitacaoRaActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.ConjuntoTramitacaoRaActionForm"
	>


<table cellSpacing=5 cellPadding=0 width=515 border=0>

	<tr>
		<td class=centercoltext valign=top width=505>
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

  			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	      		<tr> 
        			<td width="11"><img src="imagens/parahead_left.gif" border="0" /></td>
        			<td class="parabg">Inserir Dados de Pavimentos na Ordem</td>
        			<td width="11"><img src="imagens/parahead_right.gif" border="0" /></td>
	      		</tr>
   			</table>
			
			
			<table width="100%" border=0>
				<tr>
					<td height=234>
						<table width="100%" border=0 dwcopytype="CopyTableRow">
							<tr>
								<td colSpan=3>Informe os dados de pavimento da ordem:</td>
							</tr>
							
							<tr>
								<td colSpan=3 height=17>&nbsp;</td>
							</tr>
							
							<tr>
								<td width="29%" height=24><strong>Ordem :</strong></td>
								<td width="71%" colSpan=2>
									<html:text maxlength="11" size="11" property="idOrdemServico" readonly="true" style="background-color:#EFEFEF; border:0" />
								</td>
							</tr>
							
							<tr>
								<td width="29%" height=24><strong>Tipo de servi&ccedil;o :</strong></td>
								<td colspan=2>
									<html:text maxlength="50" size="50" property="descricaoTipoServico" readonly="true" style="background-color:#EFEFEF; border:0" />
								</td>
							</tr>

							<tr>
								<td colspan=3?>
								<hr>
								</td>
							</tr>

							<logic:notEmpty name="ConjuntoTramitacaoRaActionForm" property="idPavimentoRua">
								<tr>
									<td width="29%" height=24><strong>Pavimento de rua :</strong></td>
			
									<td colspan=2>
	               						<strong>
				       						<html:select property="idPavimentoRua" disabled="true">
												<html:option value="-1">&nbsp;</html:option>
												<html:options collection="colecaoPavimentoRua" labelProperty="descricao" property="id" />
											</html:select> 
	               						</strong>
									</td>
								</tr>
								
								<tr>
									<td width="29%" height=24><strong>Metragem :</strong></td>
									<td width="71%" colSpan=2>
										<html:text maxlength="11" size="11" property="metragemPavimentoRua"/>&nbsp;m²
									</td>
								</tr>
							</logic:notEmpty>
							
							<logic:empty name="ConjuntoTramitacaoRaActionForm" property="idPavimentoRua">
								<tr>
									<td width="29%" height=24><strong>Pavimento de rua :</strong></td>
			
									<td colspan=2>
	               						<strong>
				       						<html:select property="idPavimentoRua" disabled="true">
												<html:option value="-1">&nbsp;</html:option>
											</html:select> 
	               						</strong>
									</td>
								</tr>
								
								<tr>
									<td width="29%" height=24><strong>Metragem :</strong></td>
									<td width="71%" colSpan=2>
										<html:text disabled="true" maxlength="11" size="11" property="metragemPavimentoRua"/>&nbsp;m²
									</td>
								</tr>
							</logic:empty>
							
							
							<logic:notEmpty name="ConjuntoTramitacaoRaActionForm" property="idPavimentoCalcada">
								<tr>
									<td width="29%" height=24><strong>Pavimento de cal&ccedil;ada :</strong></td>
									<td colSpan=2>
	               						<strong>
				       						<html:select property="idPavimentoCalcada" disabled="true" >
												<html:option value="-1">&nbsp;</html:option>
												<html:options collection="colecaoPavimentoCalcada" labelProperty="descricao" property="id" />
											</html:select> 
	               						</strong>
									</td>
								</tr>
								
								<tr>
									<td width="29%" height=24><strong>Metragem :</strong></td>
									<td width="71%" colspan=2>
										<html:text maxlength="11" size="11" property="metragemPavimentoCalcada"/>&nbsp;m²
									</td>
								</tr>
							</logic:notEmpty>	
							
							<logic:empty name="ConjuntoTramitacaoRaActionForm" property="idPavimentoCalcada">
								<tr>
									<td width="29%" height=24><strong>Pavimento de cal&ccedil;ada :</strong></td>
									<td colSpan=2>
	               						<strong>
				       						<html:select property="idPavimentoCalcada" disabled="true" >
												<html:option value="-1">&nbsp;</html:option>
											</html:select> 
	               						</strong>
									</td>
								</tr>
								
								<tr>
									<td width="29%" height=24><strong>Metragem :</strong></td>
									<td width="71%" colspan=2>
										<html:text disabled="true" maxlength="11" size="11" property="metragemPavimentoCalcada"/>&nbsp;m²
									</td>
								</tr>
							</logic:empty>	
		
						</table>
						<hr>
					</td>
				</tr>
				
				<tr>
					<td height=24>
						<div align=right>
							<input class="bottonRightCol" type="button" value="Inserir" name="ButtonInserir" onclick="javascript:window.opener.name='pedro'; document.forms[0].target='pedro'; validarForm(document.forms[0]);"/> 
							<%-- <a href="javascript:opener.window.location.href = eval(validarForm(document.forms[0]));javascript:window.close();"> tetes</a> --%>
							<%-- <input class="bottonRightCol" onclick="javascript:window.close();" type="button" value="Fechar" name="Button2"/> --%>
						</div>
					</td>
				</tr>
	
			</table>
		<P>&nbsp;</P>
		</td>
	</tr>
</table>
</html:form>
</body>
</html:html>