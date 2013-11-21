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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirReajusteConsumoTarifaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script>

    function DateValidations () {
      this.aa = new Array("dataVigencia", "Data de Vigência inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    }

    function required () {
     this.ab = new Array("dataVigencia", "Informe Data de Vigência.", new Function ("varName", " return this[varName];"));
    }


	function validarForm(){
	    var form = document.InserirReajusteConsumoTarifaActionForm;

		if(validateRequired(form) && validateDate(form)){
	      	if (confirm('Confirma reajustar a(s) tarifa(s) de consumo?')){ 
	    		document.forms[0].submit();
	        }
  	    }
	}
	
	function replicaDados(obj){
		for (i=0; i < document.forms[0].elements.length; i++) {
			if (document.forms[0].elements[i].id == "clone") {
				document.forms[0].elements[i].value = obj.value;
			}
		}
	}
	
	
	
</script>
</head>
<body>
<html:form action="/inserirReajusteConsumoTarifaAction"
	name="InserirReajusteConsumoTarifaActionForm"
	onsubmit="return validateInserirReajusteConsumoTarifaActionForm(this);"
	type="gcom.gui.faturamento.consumotarifa.InserirReajusteConsumoTarifaActionForm"
	method="post">
	<table width="550" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td valign="top" class="centercoltext">
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
					<td class="parabg">Reajustar Tarifa de Consumo</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table border="0" width="100%">
				<tr>
					<td colspan="4">Preencha os campos para aplicar um reajuste na
					tarifa de consumo</td>
					<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=faturamentoTarifaConsumoReajustar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
				</tr>
			</table>
				
			<table border="0" width="100%">
				<tr>
					<td height="24" width="27%"><strong>Data de Vigência:<strong><font
						color="#ff0000">*</font></strong></strong></td>
					<td colspan="3"><html:text maxlength="10" property="dataVigencia"
						size="10" onkeypress="mascaraData(this, event);" /> <a
						href="javascript:abrirCalendario('InserirReajusteConsumoTarifaActionForm', 'dataVigencia')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a> &nbsp; dd/mm/aaaa</td>
				</tr>
				<tr>
					<td colspan="3" height="25"><strong>Percentual de Reajuste por
					Categoria:</strong></td>
				</tr>
				<tr>
					<td colspan="2">
					<table border="0" width="100%" bgcolor="#99ccff">
						<tr bgcolor="#99ccff">
							<td height="50%">
							<div class="style9" align="center"><strong>Categoria</strong></div>
							</td>
							<td height="50%">
							<div class="style9" align="center"><strong>Percentual de Reajuste
							</strong></div>
							</td>

						</tr>

						<%
						int cont = 0;
						String cor = "#cbe5fe";%>
						<tr>
							<logic:present name="colecaoCategoria" scope="session">
								<logic:iterate name="colecaoCategoria" id="categoria">

									<%
									if (cor.equalsIgnoreCase("#cbe5fe")) {
				cor = "#FFFFFF";%>
									<tr bgcolor="#FFFFFF">
										<%} else {
				cor = "#cbe5fe";%>
									<tr bgcolor="#cbe5fe">
										<%}%>
										<td height="50%">
										<div align="center"><bean:write name="categoria"
											property="descricao" /></div>
										</td>

										<td height="50%">
										<div align="center"><span class="style1">
										
										
										<%if (cont == 0){ %>
										<INPUT type="text" style="text-align: right;" onkeyup="javaScript:formataValorMonetario(this, 6);replicaDados(this);"
											maxlength="6" size="6"
											name="<bean:write name="categoria"
												property="id" />"
											value="0">
										<%} else { %>
											<INPUT type="text" style="text-align: right;" onkeyup="javaScript:formataValorMonetario(this, 6);"
											maxlength="6" size="6" id="clone"
											name="<bean:write name="categoria"
												property="id" />"
											value="0">	
											
										<%}
										cont = cont + 1;
										%>	
											<b>%</b></span></div>
										</td>
									</tr>
								</logic:iterate>
							</logic:present>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="24"></td>
				</tr>
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>&nbsp;</tr>
				
				<tr>
					<td colspan="4" height="27">
					<div align="right"><input name="Button" class="bottonRightCol"
						value="Aplicar" type="button" onclick="validarForm();"> <input
						name="Button2" class="bottonRightCol" value="Fechar"
						onclick="javascript:window.close();" type="button"></div>
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
	    var form = document.InserirReajusteConsumoTarifaActionForm;
		form.dataVigencia.value = "";
</script>

</html:html>
