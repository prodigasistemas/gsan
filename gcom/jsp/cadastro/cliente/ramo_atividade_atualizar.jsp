<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  
	formName="AtualizarRamoAtividadeActionForm" 
	dynamicJavascript="false" />

<SCRIPT LANGUAGE="JavaScript">
	var bCancel = false; 

   	function validateAtualizarRamoAtividadeActionForm(form) {
	   	if (bCancel) 
   			return true; 
       	else 
   	 		return validateCaracterEspecial(form) && validateLong(form); 
	}

    function caracteresespeciais () { 
		this.ab = new Array("codigo", "Código possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    } 

    function IntegerValidations () { 
     this.ab = new Array("codigo", "Código deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    } 

	function validarForm(){
		
		var form = document.forms[0];
		
		if(form.codigo.value == ""){
			alert("Informe Código");
			form.codigo.focus();
		}else if(form.descricao.value == ""){
		
			alert("Informa Descrição");
			form.descricao.focus();
		
		}else if (validateAtualizarRamoAtividadeActionForm(form)){
			submeterFormPadrao(form);
		}
	}
	
</SCRIPT>
</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/atualizarRamoAtividadeAction" method="post">
	
	<html:hidden name="AtualizarRamoAtividadeActionForm" property="codigo" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp" %>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
  		<tr>
    		<td width="130" valign="top" class="leftcoltext">
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

			<td width="625" valign="top" class="centercoltext">

	        <table height="100%">
	        	<tr>
	          		<td></td>
	        	</tr>
	      	</table>

	      	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	        	<tr>
	          		<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
	          		<td class="parabg">Atualizar Ramo de Atividade</td>
	          		<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
	        	</tr>
	      	</table>
	      	
      		<p>&nbsp;</p>

	      	<table width="100%" border="0">
		      	<tr>
		      		<td colspan="2">Para alterar o Ramo de Atividade, informe os dados abaixo:</td>
		      		<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroRamoAtividadeAtualizar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
		      	</tr>
	      	</table>
    
      		<table width="100%" border="0">
		      
		      <tr>
		      	<td height="20"><strong>Código:
		      		<font color="#FF0000">*</font></strong>
		      	</td>
		        
		        <td align="right">
					<div align="left">
					<!-- <html:text maxlength="5" 
						property="codigo" 
						size="5" 
						tabindex="1"/> -->
					<bean:write name="AtualizarRamoAtividadeActionForm" property="codigo"/>

					</div>
				</td>
      		  </tr>
		      
		      <tr>
		      	<td height="20"><strong>Descrição:
		      		<font color="#FF0000">*</font></strong>
		      	</td>
		        
		        <td align="right">
					<div align="left">
					<html:text maxlength="20" 
						property="descricao" 
						size="55" 
						tabindex="2"/>

					</div>
				</td>
      		  </tr>
      			  		
	  		<tr>
        		<td><strong>Indicador de uso:</strong></td>
        		<td>
					<html:radio property="indicadorUso" value="1"/><strong>Ativo
					<html:radio property="indicadorUso" value="2"/>Inativo</strong>
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
      			<td colspan="2" widt="100%">
      			<table width="100%">
					<tr>
						<tr>
							<td width="40%" align="left">
								<input type="button"
									name="ButtonCancelar" 
									class="bottonRightCol" 
									value="Voltar"
									onClick="window.location.href='/gsan/exibirManterRamoAtividadeAction.do'"> 
								<input type="button"
									name="ButtonReset" 
									class="bottonRightCol" 
									value="Desfazer"
									onClick="window.location.href='/gsan/exibirAtualizarRamoAtividadeAction.do?idRegistroAtualizacao=<bean:write name="AtualizarRamoAtividadeActionForm" property="codigo" />'"> 
								<input type="button"
									name="ButtonCancelar" 
									class="bottonRightCol" 
									value="Cancelar"
									onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</td>
							<td align="right">
								<input type="button"
									onClick="javascript:validarForm();"
									name="botaoAtualizar" 
									class="bottonRightCol" 
									value="Atualizar">
							</td>
						</tr>
	      			
      			</table>
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
