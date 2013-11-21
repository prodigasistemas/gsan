<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@page isELIgnored="false"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
	<html:javascript staticJavascript="false"  formName="InserirHidrometroMarcaActionForm"
	dynamicJavascript="true" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js">
</script>	

	
<script language="JavaScript">
  
    function validarForm() {
    var form = document.forms[0];
	  if(validateInserirHidrometroMarcaActionForm(form)){	     
		   	submeterFormPadrao(form); 
		  }
   	  }   
       
  	function limparForm() {
		var form = document.forms[0];
		
		form.descricaoMarcaHidrometro.value = '';
		form.descricaoAbreviada.value = '';
		form.validadeRevisao.value = '';
		form.marcaHidrometro.value = '';
    }	 
	 
</script>
</head>



<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/inserirHidrometroMarcaAction.do"
	name="InserirHidrometroMarcaActionForm"
	type="gcom.gui.micromedicao.hidrometro.InserirHidrometroMarcaActionForm"
	method="post"
	onsubmit="return InserirHidrometroMarcaActionForm(this);">
	
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
				<td class="parabg">Inserir Marca do Hidrômetro</td>
				<td width="11" valign="top"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>

		<p>&nbsp;</p>
		<table width="100%" border="0">
			<tr>
				<td colspan="2">Para inserir a marca do(s) hidrômetro(s), informe os dados abaixo:</td>
			</tr>
			<tr>
			  <td  width="30%" class="style3"><strong>Descrição:<font color="#FF0000">*</font></strong></td>
			  <td  width="70%" colspan="2"><strong><b><span class="style2"> 
			  		<html:text  property="descricaoMarcaHidrometro" size="30" maxlength="30" tabindex="1"/> </span></b></strong></td>
			</tr>	
			
			<tr>
			  <td  width="30%" class="style3"><strong>Descrição Abreviada:</strong></td>
			  <td  width="70%" colspan="2"><strong><b><span class="style2"> 
			  		<html:text  property="descricaoAbreviada" size="3" maxlength="3" tabindex="2"/> </span></b></strong></td>
			</tr>
			
			<tr>
			  <td  width="30%"class="style3"><strong>Validade da revisão:<font color="#FF0000">*</font></strong></td>
			  <td  width="70%" colspan="2"><strong><b><span class="style2"> 
			  		<html:text  property="validadeRevisao" size="3" maxlength="3" tabindex="3" onkeyup="javascript:verificaNumeroInteiro(this);"/> </span></b></strong>&nbsp;dias</td>
			</tr>					
			
			<tr>
			  <td  width="30%"class="style3"><strong>Código da Marca:<font color="#FF0000">*</font></strong></td>
			  <td  width="70%" colspan="2"><strong><b><span class="style2"> 
			  		<html:text  property="marcaHidrometro" size="1" maxlength="1" tabindex="4"/> </span></b></strong></td>			  
			</tr>				
			<tr>
				<td colspan=2>														
					<table width="100%">
						<tr>
								<td height="19" width="30%"><strong> <font color="#FF0000"></font></strong></td>
								
								<td width="70%"><div align="left"><strong><font color="#FF0000">*</font></strong>
								Campos obrigat&oacute;rios</div>
								</td>
						</tr>
						<tr>
							  <td align="left">				
								<input type="button" name="ButtonReset" class="bottonRightCol"
								value="Desfazer" onClick="limparForm();"> 
								
								<input type="button"
								name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							  </td>
							  
							  <td align="right"><input type="button" name="Button" class="bottonRightCol"
									value="Inserir" onclick="validarForm();" tabindex="5"/>
							  </td>
						</tr>
					</table>
				</td>
			</tr>				
			<tr>
				<td>&nbsp;</td>		
			</tr>			
			<tr>
				<td>&nbsp;</td>		
			</tr>					
			<tr>
				<td>&nbsp;</td>		
			</tr>			
			<tr>
				<td>&nbsp;</td>		
			</tr>								
			<tr>
				<td>&nbsp;</td>		
			</tr>			
			<tr>
				<td>&nbsp;</td>		
			</tr>		
			<tr>
				<td>&nbsp;</td>		
			</tr>			
			<tr>
				<td>&nbsp;</td>		
			</tr>			
			<tr>
				<td>&nbsp;</td>		
			</tr>		
   </table>          
</table>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</html:html>
