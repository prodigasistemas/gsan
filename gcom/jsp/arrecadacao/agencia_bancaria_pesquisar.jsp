<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.Pagina, gcom.util.ConstantesSistema"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

 <script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarAgenciaBancariaActionForm"/>
<script>

 var bCancel = false;

	function validarForm(form){
		if(validatePesquisarAgenciaBancariaActionForm(form)){
    		submeterFormPadrao(form);
		}
	}

	/* Limpa Form */	 
	function limparForm() {
		var form = document.forms[0];
		form.codigo.value = "";	
		form.banco.value = "";
	    form.bancoID.value = "";
	    form.nomeAgencia.value = "";
	}  
	
	function limparBanco() {
	 	var form = document.forms[0];
		form.banco.value = "";
	    form.bancoID.value = ""; 	 
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
   		var form = document.forms[0];

	   if (tipoConsulta == 'banco') {
	      form.bancoID.value = codigoRegistro;
	      form.banco.value = descricaoRegistro;
	    }
	    
   }
</script>
</head>

<body leftmargin="0" topmargin="0" onload="window.focus();resizePageSemLink(580, 340);setarFoco('${requestScope.nomeCampo}');">
<html:form
  action="/pesquisarAgenciaBancariaAction"
  method="post"
  onsubmit="return validatePesquisarAgenciaBancariaActionForm(this);"
>
<table width="570" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="540" valign="top" class="centercoltext">
    <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
          <td class="parabg">Pesquisar Ag&ecirc;ncia Banc&aacute;ria</td>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      
      <table width="100%" border="0">
        <tr>
          <td colspan="2">Preencha os campos para pesquisar uma ag&ecirc;ncia banc&aacute;ria:</td>
        </tr>
        <tr>
        	<td width="15%"><strong>Código:</strong></td>
	        <td width="85%"><html:text maxlength="5" property="codigo" size="5"/></td>
        </tr>
        
        <tr>
			<td><strong>Banco: </strong></td>
			<td colspan="3"><html:text property="bancoID" size="9"
				maxlength="9"
				onkeypress="return validaEnter(event, 'exibirPesquisarAgenciaBancariaAction.do','bancoID');"/>
				<a href="javascript:redirecionarSubmit('exibirPesquisarTabelaAuxiliarAbreviadaAction.do?tela=banco&caminhoRetorno=exibirPesquisarAgenciaBancariaAction');">
				<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
					title="Pesquisar Banco"></a> 
					
				<logic:present name="corBanco">	
					<logic:equal name="corBanco" value="exception">
						<html:text property="banco" size="45"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:equal>
					<logic:notEqual name="corBanco" value="exception">
						<html:text property="banco" size="45"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notEqual>
				</logic:present> 
					
				<logic:notPresent name="corBanco">
					<logic:empty name="PesquisarAgenciaBancariaActionForm"
						property="bancoID">
						<html:text property="banco"
							value="" size="45" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:empty>
					<logic:notEmpty name="PesquisarAgenciaBancariaActionForm"
						property="bancoID">
						<html:text property="banco" size="45"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notEmpty>
				</logic:notPresent> <a href="javascript:limparBanco();"> 
				
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
					border="0" title="Apagar" /> </a></td>
			</tr>
        
        	<tr>
	          <td width="15%">
	          	<strong>Nome: </strong>
	          </td>
    	      <td width="85%">
    	      	<html:text maxlength="30" property="nomeAgencia" size="35"/>
        	  </td>
        	</tr>
	        <tr>
			  <td width="15%">&nbsp;</td>
			  <td width="85%">
		    	<html:radio property="tipoPesquisa"
				   value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />Iniciando pelo texto
				<html:radio property="tipoPesquisa" tabindex="5"
				   value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />Contendo o texto
			  </td>
			</tr>        
        	<tr>
	          <td width="15%">&nbsp;</td>
    	      <td width="85%">&nbsp;</td>
        	</tr>
	        <tr>
    	      <td>
        	  	<INPUT TYPE="button" class="bottonRightCol" value="Limpar" onclick="javascript:limparForm(document.forms[0]);"/>
	         </td>
    	     <td>
        	  	<table width="100%" border="0">
          		<tr>
          			<td>
          				<logic:present name="caminhoRetornoTelaPesquisaAgenciaBancaria">
          					<INPUT TYPE="button" class="bottonRightCol" value="Voltar" onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaAgenciaBancaria}.do')"/>
	          			</logic:present>
    	      		</td>
        	  		<td align="right">
        	  			<INPUT TYPE="submit" class="bottonRightCol" value="Pesquisar"/>
        	  		</td>
          		</tr>
          	</table>
          </tr>
      </table>
	</td>
  </tr>
</table>
</body>
</html:form>
</html:html>
