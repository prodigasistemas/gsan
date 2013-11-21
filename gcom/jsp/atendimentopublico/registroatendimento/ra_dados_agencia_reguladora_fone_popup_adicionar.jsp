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

<link rel="stylesheet"	href="<bean:message key="caminho.css"/>EstilosCompesa.css"	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="AdicionarRaDadosAgenciaReguladoraFonePopupActionForm" dynamicJavascript="true" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">


	function validarForm() {
	   var form = document.forms[0];
	   
	  if(validateAdicionarRaDadosAgenciaReguladoraFonePopupActionForm(form)){	     
			form.submit();
		}
	 }	
	
	
	function chamarReload(){
		
		chamarSubmitComUrlSemUpperCase('/gsan/exibirInformarRaDadosAgenciaReguladoraAction.do');
	}

</script>
</head>


<logic:present name="reload" scope="request">
	<body leftmargin="5" topmargin="5" onload="resizePageSemLink(630,330); chamarReload();setarFoco('${requestScope.nomeCampo}');window.close();">
</logic:present>
<logic:notPresent name="reload" scope="request">
	<body leftmargin="5" topmargin="5" onload="resizePageSemLink(630,330);setarFoco('${requestScope.nomeCampo}');">
</logic:notPresent>

<html:form action="/adicionarRaDadosAgenciaReguladoraFonePopupAction" 
	name="AdicionarRaDadosAgenciaReguladoraFonePopupActionForm" 
	type="gcom.gui.atendimentopublico.registroatendimento.AdicionarRaDadosAgenciaReguladoraFonePopupActionForm"
	method="post" onsubmit="return validateAdicionarRaDadosAgenciaReguladoraFonePopupActionForm(this);">

	<table width="100%" border="0" cellspacing="5" cellpadding="0">

		<tr>

			<td width="100%" valign="top" class="centercoltext">

				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
	
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
						</td>
						<td class="parabg">Fones do Reclamante</td>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
			
				<p>&nbsp;</p>

				<table width="100%" border="0" align="center" >
				
				<tr>
					<td height="10" colspan="2">Para adicionar um fone, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td height="10"><strong>Tipo de Telefone:<font color="#FF0000">*</font></strong></td>
					<td  width="70%" colspan="2">
			  			<html:select property="foneTipo" tabindex="1" style="width:200px;">
							<html:option value="-1"> &nbsp; </html:option>
							<html:options collection="colecaoFoneTipo" property="id" labelProperty="descricao"/>
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Fone Padrão:<font color="#FF0000">*</font> </strong></td>
					<td>
						<html:radio property="indicadorFonePadrao" value="1" tabindex="2"/><strong>Sim
						<html:radio property="indicadorFonePadrao" value="2" tabindex="3"/>Não</strong>
					</td>
				</tr>
				
				<tr>
					<td><strong>DDD:<font color="#FF0000">*</font> </strong></td>
					<td colspan="2"><html:text property="ddd"  size="2" maxlength="2" tabindex="4"/></td>
				</tr>
				
				<tr>
					<td><strong>Número do Telefone:<font color="#FF0000">*</font> </strong></td>
					<td colspan="2"><html:text property="numeroTelefone"  size="8" maxlength="8" tabindex="5"/></td>
				</tr>
				
				<tr>
					<td><strong>Ramal:</strong></td>
					<td colspan="2"><html:text property="ramal"  size="4" maxlength="4" tabindex="6"/></td>
				</tr>							      			
				
				<tr>
					<td width="500" colspan="2">
					<div align="center" ><font color="#FF0000">*</font> Campos obrigatórios</div>
					</td>
				</tr>
				<tr>
					<table width="100%">
						<tr>
							<td align="right">
								<input type="button"name="ButtonInserir" class="bottonRightCol" value="Inserir" 
								onClick="validarForm(document.forms[0]);" tabindex="7">
								
								<input type="button" name="ButtonFechar" class="bottonRightCol"
								value="Fechar" tabindex="8"	onclick="window.close();" />
							</td>
						</tr>
					</table>		
				</tr>
			</table>
		</td>
	</tr>
</table>
</html:form>
</body>
</html:html>