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
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirFeriadoMunicipioPopupActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"> </script>	
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
	
<script language="JavaScript">
  
    function validarForm() {
    var form = document.forms[0];
    
	  if(validateInserirFeriadoMunicipioPopupActionForm(form)){	     
	 	  submeterFormPadrao(form);   		  
   	    }  		  
   	  }
   	  
   	  function limparForm() {
		var form = document.forms[0];
		form.data.value = "";
	    form.descricao.value = "";
	 }
   	  
 </script>  

</head>


<body leftmargin="0" topmargin="0"	onload="window.focus();">


<logic:notPresent name="fecharPopup">
	<body leftmargin="5" topmargin="5"
		onload="javascript:setarFoco('${requestScope.nomeCampo}');
		desabilitaCampos();desabilitaCamposDebitoCredito();desabilitaCamposMatriculaImovelOnClick();">
</logic:notPresent>
<logic:present name="fecharPopup">
	<body leftmargin="0" topmargin="0"
		onload="javascript:chamarReload('${sessionScope.retornarTela}');limparForm();window.close();">
</logic:present>



<html:form action="/inserirFeriadoMunicipioPopupAction.do" 
	name="InserirFeriadoMunicipioPopupActionForm"
	type="gcom.gui.cadastro.geografico.InserirFeriadoMunicipioPopupActionForm"
	method="post">

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
						<td class="parabg">Inserir Feriado</td>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
			
				<p>&nbsp;</p>

				<table width="100%" border="0" align="center" >
				
				<tr>Para inserir um feriado, informe os dados abaixo:</tr>
				
				<tr>
					<td><strong>Data :</strong><font color="#FF0000">*</font></td>
					<td colspan="2">
						<html:text property="data" size="10" maxlength="10" tabindex="1" onkeyup="mascaraData(this, event);" /> 
		            	<a href="javascript:abrirCalendario('InserirFeriadoMunicipioPopupActionForm', 'data')">
						<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle"
						alt="Exibir Calendário" /></a> dd/mm/aaaa
					</td>
				</tr>

				<tr>
				  <td><strong>Descrição:<font color="#FF0000">*</font></strong></td>
				  <td><strong><b><span class="style2"> <html:text
					  property="descricao" size="25" maxlength="20" tabindex="2"/> </span></b></strong></td>
				</tr>
				
				<tr>
					<td height="19"><strong> <font color="#FF0000"></font></strong></td>
					
					<td align="right"><div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<table width="100%">
			              	<tr>
			            		<td >
		              				<div align="left">
	                  					<input name="ButtonFechar" type="button" class="bottonRightCol" 
	                  					value="Fechar" onclick="javascript:window.close();">
		              				</div>
			              		</td>
			              		
			              		<td align="right">
				              			<input type="button" name="Button" class="bottonRightCol"	
				              			value="Adicionar" onclick="validarForm();" />
    							</td>
				          	</tr>
				          	
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</html:form>
</body>
</html:html>