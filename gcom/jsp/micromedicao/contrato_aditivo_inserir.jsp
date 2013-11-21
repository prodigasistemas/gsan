<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<html:javascript staticJavascript="false"  formName="ExibirInformarItensContratoServicoActionForm" />

<script>
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
   		var form = document.forms[0];

	   if (tipoConsulta == 'processo') {
	      form.idProcesso.value = codigoRegistro;
	      form.descricaoProcesso.value = descricaoRegistro;
	    }
   }
	
	function validateIncluirAditivoActionForm(){
		var form = document.forms[0];
		if (form.percentualTaxaSucessoAditivo.value == '' && !validaPercentual(form.percentualTaxaSucessoAditivo)) {
		  		alert('Percentual taxa de sucesso inválido.');
		  		form.percentualTaxaSucesso.focus();
		} else if ((form.dataInicioAditivo.value != null && form.dataInicioAditivo.value != "")
				&& (form.valorAditivoContrato.value != null && form.valorAditivoContrato.value != "")) {
				
				if (verificaData(form.dataInicioAditivo) && (form.dataFimAditivo.value == '' || verificaData(form.dataFimAditivo) )) {
					if (comparaData(form.dtInicioContrato.value, ">", form.dataInicioAditivo.value )) {
						alert('Data Início do Aditivo é anterior à Data Início do Contrato.');	
					} else if (comparaData(form.dataInicioAditivo.value, ">", form.dataFimAditivo.value )) {
		  				alert('Data Fim do Aditivo é anterior à Data Início do Aditivo.');	
	  				} else {
	  			 		submeterFormPadrao(form);
  			 		}
	  			}
   		}else {
	    	alert("Campos obrigatórios não informados");
	  	}  
		
	}

	function validaPercentual(percentualTaxaSucesso){
		if (obterNumerosSemVirgulaEPonto(percentualTaxaSucesso.value) > 10000
			|| obterNumerosSemVirgulaEPonto(percentualTaxaSucesso.value) <= 0 ) {
			return false;
		}
		
		return true;
	}
	
	function numeroPositivo() { 
		var form = document.forms[0];
	
		if (( form.valorAditivoContrato.value <= '0,00' && form.valorAditivoContrato.value != '' ) ||
			( form.valorAditivoContrato.value == '00' && form.valorAditivoContrato.value != '' ) ) {
		
			alert("Valor do aditivo deve somente conter números positivos.");
	 		form.valorAditivoContrato.value = "";
		} 
	}
	
	function limpar(){
		var form = document.forms[0];
		form.dataInicioAditivo.value = "";
		form.dataFimAditivo.value = "";
		form.valorAditivoContrato.value = "";
		form.percentualTaxaSucessoAditivo.value = "";
	}

</script>

</head>

<logic:notPresent name="fecharPopup" scope="request">
	<body leftmargin="5" topmargin="5"
			onload="javascript:resizePageSemLink(570, 370);">
</logic:notPresent>
<logic:present name="fecharPopup" scope="request">
	<body leftmargin="0" topmargin="0"
		onload="chamarReload('exibirInformarItensContratoServicoAction.do?acao=adicionarAditivo');window.close()">
</logic:present>

<html:form action="/incluirAditivoAction.do"
	name="ExibirInformarItensContratoServicoActionForm"
	type="gcom.gui.micromedicao.ExibirInformarItensContratoServicoActionForm"
	method="post">

	<table width="550" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="520" valign="top" class="centercoltext">
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
					<td class="parabg">Incluir Aditivo</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
	
      <p>&nbsp;</p>
      
      <table width="100%" border="0">
        <tr> 
          <td colspan="2">Preencha os campos para incluir um aditivo:</td>
        </tr>
       </table>
        
       <table width="100%" border="0">
       		<tr>
				<td><strong>Data Início do Aditivo:<font color="#FF0000">*</font></strong></td>
				<td>
					<html:hidden property="dtInicioContrato" />
					<html:text property="dataInicioAditivo" 
									   size="10" 
									   maxlength="10"
									   tabindex="4"
									   onkeypress="return isCampoNumerico(event);"
									   onkeyup="mascaraData(this, event);" />
					<a href="javascript:abrirCalendario('ExibirInformarItensContratoServicoActionForm', 'dataInicioAditivo');">
						<img border="0"
							 src="<bean:message key="caminho.imagens"/>calendario.gif"
							 width="20" 
							 border="0" 
							 align="absmiddle" 
							 alt="Exibir Calendário" title="Exibir Calendário" /></a>dd/mm/aaaa
				</td>
			</tr>
       		<tr>
				<td><strong>Data Fim do Aditivo:</strong></td>
				<td>
					<html:text property="dataFimAditivo" 
									   size="10" 
									   maxlength="10"
									   tabindex="4"
									   onkeypress="return isCampoNumerico(event);"
									   onkeyup="mascaraData(this, event);" />
					<a href="javascript:abrirCalendario('ExibirInformarItensContratoServicoActionForm', 'dataFimAditivo');">
						<img border="0"
							 src="<bean:message key="caminho.imagens"/>calendario.gif"
							 width="20" 
							 border="0" 
							 align="absmiddle" 
							 alt="Exibir Calendário" title="Exibir Calendário" /></a>dd/mm/aaaa
				</td>
			</tr>
			<tr>
				<td><strong>Valor do Aditivo do Contrato:<font color="#FF0000">*</font></strong></td>
				<td width="68%" colspan="2"><strong><b><span class="style2"> 
				  <html:text property="valorAditivoContrato" 
				  			 size="14" 
				  			 maxlength="16" 
				  			 tabindex="7"
				  			 onblur="javascript:numeroPositivo();"
				  			 onkeypress="formataValorMonetario( this, 14);return isCampoNumerico(event);" 
							 onkeyup="formataValorMonetario( this, 14);"
							 disabled="false" style="text-align: right;" />
					</span></b></strong>
				</td>
			</tr>
			<tr>
				<td><strong>Percentual Taxa de Sucesso Aditivo:<font color="#FF0000"></font></strong></td>
				<td width="68%" colspan="2"><strong><b><span class="style2"> 
				  <html:text property="percentualTaxaSucessoAditivo" 
				  			 size="14" 
				  			 maxlength="6" 
				  			 tabindex="7"
				  			 onkeypress="formataValorMonetario( this, 6);return isCampoNumerico(event);" 
							 onkeyup="formataValorMonetario( this, 6);"
							 style="text-align: right;" 
							 disabled="false"/>
					</span></b></strong>
				</td>
			</tr>
			
	        <tr> 
	        
	          <td>&nbsp;</td>
	          <td colspan="3">&nbsp;</td>
	        </tr>
	
			<tr>
				<td>

					<input type="button" name="Button1"
						class="bottonRightCol" value="Voltar" 
						onclick="window.close();">
	
					<input name="Button2" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="javascript:limpar();" >
						
	            </td>
	            <td  align="right">
	            
					<input type="button" name="Button3"
					class="bottonRightCol" value="Adicionar" tabindex="4"
					onclick="javascript:validateIncluirAditivoActionForm();"/>
					
				</td>
			</tr>

      </table>

			<p>&nbsp;</p>
			<p>&nbsp;</p>
	</table>
</html:form>
</body>
</html:html>
