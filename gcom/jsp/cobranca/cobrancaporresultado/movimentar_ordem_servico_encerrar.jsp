<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirGrupoActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" >
	function validateMovimentarOrdemServicoActionForm(form) {
        return true;
    }
    
    function liberaNumeroOS(){
    	var form = document.forms[0];
    	
    	form.action = 'movimentarOrdemServicoWizardAction.do?action=exibirMovimentarOrdemServicoEncerrarOSAction&liberaNumeroOS=sim';
    	form.submit();
    }
    
    function bloqueiaCampos() {
    	var form = document.forms[0];
    	
    	if((form.numeroOSInicial.value != null && form.numeroOSInicial.value != '')
			|| (form.numeroOSFinal.value != null && form.numeroOSFinal.value != '')) {
			
			document.getElementById('numerosOS[0]').value = "";
			document.getElementById('numerosOS[0]').style.color = "#000000";
			document.getElementById('numerosOS[0]').readOnly = true;
			document.getElementById('numerosOS[0]').style.backgroundColor = '#EFEFEF';
			
			document.getElementById('numerosOS[1]').value = "";
			document.getElementById('numerosOS[1]').style.color = "#000000";
			document.getElementById('numerosOS[1]').readOnly = true;
			document.getElementById('numerosOS[1]').style.backgroundColor = '#EFEFEF';
			
			document.getElementById('numerosOS[2]').value = "";
			document.getElementById('numerosOS[2]').style.color = "#000000";
			document.getElementById('numerosOS[2]').readOnly = true;
			document.getElementById('numerosOS[2]').style.backgroundColor = '#EFEFEF';
			
			document.getElementById('numerosOS[3]').value = "";
			document.getElementById('numerosOS[3]').style.color = "#000000";
			document.getElementById('numerosOS[3]').readOnly = true;
			document.getElementById('numerosOS[3]').style.backgroundColor = '#EFEFEF';
			
			document.getElementById('numerosOS[4]').value = "";
			document.getElementById('numerosOS[4]').style.color = "#000000";
			document.getElementById('numerosOS[4]').readOnly = true;
			document.getElementById('numerosOS[4]').style.backgroundColor = '#EFEFEF';
			
			document.getElementById('numerosOS[5]').value = "";
			document.getElementById('numerosOS[5]').style.color = "#000000";
			document.getElementById('numerosOS[5]').readOnly = true;
			document.getElementById('numerosOS[5]').style.backgroundColor = '#EFEFEF';
			
			document.getElementById('numerosOS[6]').value = "";
			document.getElementById('numerosOS[6]').style.color = "#000000";
			document.getElementById('numerosOS[6]').readOnly = true;
			document.getElementById('numerosOS[6]').style.backgroundColor = '#EFEFEF';
			
			document.getElementById('numerosOS[7]').value = "";
			document.getElementById('numerosOS[7]').style.color = "#000000";
			document.getElementById('numerosOS[7]').readOnly = true;
			document.getElementById('numerosOS[7]').style.backgroundColor = '#EFEFEF';
			
			document.getElementById('numerosOS[8]').value = "";
			document.getElementById('numerosOS[8]').style.color = "#000000";
			document.getElementById('numerosOS[8]').readOnly = true;
			document.getElementById('numerosOS[8]').style.backgroundColor = '#EFEFEF';
			
			document.getElementById('numerosOS[9]').value = "";
			document.getElementById('numerosOS[9]').style.color = "#000000";
			document.getElementById('numerosOS[9]').readOnly = true;
			document.getElementById('numerosOS[9]').style.backgroundColor = '#EFEFEF';
			
			
		} else if ((document.getElementById('numerosOS[0]').value != null && document.getElementById('numerosOS[0]').value != '')
				|| (document.getElementById('numerosOS[1]').value != null && document.getElementById('numerosOS[1]').value != '')
				|| (document.getElementById('numerosOS[2]').value != null && document.getElementById('numerosOS[2]').value != '')
				|| (document.getElementById('numerosOS[3]').value != null && document.getElementById('numerosOS[3]').value != '')
				|| (document.getElementById('numerosOS[4]').value != null && document.getElementById('numerosOS[4]').value != '')
				|| (document.getElementById('numerosOS[5]').value != null && document.getElementById('numerosOS[5]').value != '')
				|| (document.getElementById('numerosOS[6]').value != null && document.getElementById('numerosOS[6]').value != '')
				|| (document.getElementById('numerosOS[7]').value != null && document.getElementById('numerosOS[7]').value != '')
				|| (document.getElementById('numerosOS[8]').value != null && document.getElementById('numerosOS[8]').value != '')
				|| (document.getElementById('numerosOS[9]').value != null && document.getElementById('numerosOS[9]').value != '')){
						
			form.numeroOSInicial.value = "";
			form.numeroOSInicial.style.color = "#000000";
			form.numeroOSInicial.readOnly = true;
			form.numeroOSInicial.style.backgroundColor = '#EFEFEF';
			
			form.numeroOSFinal.value = "";
			form.numeroOSFinal.style.color = "#000000";
			form.numeroOSFinal.readOnly = true;
			form.numeroOSFinal.style.backgroundColor = '#EFEFEF';
					
		}
		
		if ((form.numeroOSInicial.value == null || form.numeroOSInicial.value == '')
			&& (form.numeroOSFinal.value == null || form.numeroOSFinal.value == '')
			&& (document.getElementById('numerosOS[0]').value == null || document.getElementById('numerosOS[0]').value == '')
			&& (document.getElementById('numerosOS[1]').value == null || document.getElementById('numerosOS[1]').value == '')
			&& (document.getElementById('numerosOS[2]').value == null || document.getElementById('numerosOS[2]').value == '')
			&& (document.getElementById('numerosOS[3]').value == null || document.getElementById('numerosOS[3]').value == '')
			&& (document.getElementById('numerosOS[4]').value == null || document.getElementById('numerosOS[4]').value == '')
			&& (document.getElementById('numerosOS[5]').value == null || document.getElementById('numerosOS[5]').value == '')
			&& (document.getElementById('numerosOS[6]').value == null || document.getElementById('numerosOS[6]').value == '')
			&& (document.getElementById('numerosOS[7]').value == null || document.getElementById('numerosOS[7]').value == '')
			&& (document.getElementById('numerosOS[8]').value == null || document.getElementById('numerosOS[8]').value == '')
			&& (document.getElementById('numerosOS[9]').value == null || document.getElementById('numerosOS[9]').value == '')) {
			
			form.numeroOSInicial.style.color = "#000000";
			form.numeroOSInicial.readOnly = false;
			form.numeroOSInicial.style.backgroundColor = '';
			
			form.numeroOSFinal.style.color = "#000000";
			form.numeroOSFinal.readOnly = false;
			form.numeroOSFinal.style.backgroundColor = '';
			
			document.getElementById('numerosOS[0]').style.color = "#000000";
			document.getElementById('numerosOS[0]').readOnly = false;
			document.getElementById('numerosOS[0]').style.backgroundColor = '';
			
			document.getElementById('numerosOS[1]').style.color = "#000000";
			document.getElementById('numerosOS[1]').readOnly = false;
			document.getElementById('numerosOS[1]').style.backgroundColor = '';
			
			document.getElementById('numerosOS[2]').style.color = "#000000";
			document.getElementById('numerosOS[2]').readOnly = false;
			document.getElementById('numerosOS[2]').style.backgroundColor = '';
			
			document.getElementById('numerosOS[3]').style.color = "#000000";
			document.getElementById('numerosOS[3]').readOnly = false;
			document.getElementById('numerosOS[3]').style.backgroundColor = '';
			
			document.getElementById('numerosOS[4]').style.color = "#000000";
			document.getElementById('numerosOS[4]').readOnly = false;
			document.getElementById('numerosOS[4]').style.backgroundColor = '';
			
			document.getElementById('numerosOS[5]').style.color = "#000000";
			document.getElementById('numerosOS[5]').readOnly = false;
			document.getElementById('numerosOS[5]').style.backgroundColor = '';
			
			document.getElementById('numerosOS[6]').style.color = "#000000";
			document.getElementById('numerosOS[6]').readOnly = false;
			document.getElementById('numerosOS[6]').style.backgroundColor = '';
			
			document.getElementById('numerosOS[7]').style.color = "#000000";
			document.getElementById('numerosOS[7]').readOnly = false;
			document.getElementById('numerosOS[7]').style.backgroundColor = '';
			
			document.getElementById('numerosOS[8]').style.color = "#000000";
			document.getElementById('numerosOS[8]').readOnly = false;
			document.getElementById('numerosOS[8]').style.backgroundColor = '';
			
			document.getElementById('numerosOS[9]').style.color = "#000000";
			document.getElementById('numerosOS[9]').readOnly = false;
			document.getElementById('numerosOS[9]').style.backgroundColor = '';
		}
		
    }
    
</script>

</head>

<body>

<html:form
    action="/movimentarOrdemServicoWizardAction"
    method="post"
    onsubmit="return validateMovimentarOrdemServicoActionForm(this);"
>

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=3"/>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="123" valign="top" class="leftcoltext">
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
          <td class="parabg">Movimentar Ordem de Serviço - Encerrar OS</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      
      <p>&nbsp;</p>
      
      <table border="0" width="100%">
        <tr>
          <td colspan="2">Para encerrar OS para comandos de contas em cobrança por empresa, informe os dados abaixo:</td>
        </tr>

		<tr>
          <td width="26%"><strong>Comando de Conta de Cobrança:<font color="#ff0000"></font></strong></td>
          <td width="74%">
			<html:text maxlength="40" property="idComandoContaCobranca" size="20" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
          </td>
        </tr>
        
        <tr>
			<td colspan="2" width="100%">
			<hr>
			</td>
		</tr>
		
		<tr>
			<td width="25%"><strong>Motivo de Encerramento:<font color="#ff0000">*</font></strong></td>
			<td width="75%"><html:select property="idMotivoEncerramento" tabindex="3" onchange="liberaNumeroOS();" >
				<logic:notEmpty name="colecaoAtendimentoMotivoEncerramento">
					<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
					<html:options collection="colecaoAtendimentoMotivoEncerramento"
						labelProperty="descricao" property="id" />
				</logic:notEmpty>
			</html:select></td>
		</tr>
		
        <logic:present name="habilitaNumerosOS" scope="session">
	       <tr> 
	         <td width="25%"><strong>Número de OS Inicial:<font color="#ff0000"></font></strong></td>
	         <td width="75%">
				<html:text maxlength="10" property="numeroOSInicial" size="10" onchange="bloqueiaCampos();" />
	         </td>
	       </tr>
	       <tr>
	         <td width="25%"><strong>Número de OS Final:<font color="#ff0000"></font></strong></td>
	         <td width="75%">
				<html:text maxlength="10" property="numeroOSFinal" size="10" onchange="bloqueiaCampos();" />
	         </td>
	       </tr>
        </logic:present>
        <logic:notPresent name="habilitaNumerosOS" scope="session">
	       <tr> 
	         <td width="25%"><strong>Número de OS Inicial:<font color="#ff0000"></font></strong></td>
	         <td width="75%">
				<html:text maxlength="10" property="numeroOSInicial" size="10" style="background-color:#EFEFEF; border:0;" readonly="true"/>
	         </td>
	       </tr>
	       <tr>
	         <td width="25%"><strong>Número de OS Final:<font color="#ff0000"></font></strong></td>
	         <td width="75%">
			<html:text maxlength="10" property="numeroOSFinal" size="10" style="background-color:#EFEFEF; border:0;" readonly="true"/>
	         </td>
	       </tr>
        </logic:notPresent>
        
        <logic:present name="habilitaNumerosOS" scope="session">
	        <tr>
				<td colspan="2" width="100%">
				<hr>
				</td>
			</tr>
	        <tr>
	          <td width="25%"><strong>Números de Ordens de Serviço:<font color="#ff0000"></font></strong></td>
	          <td width="75%">
	            <table border="0">
	            	<tr>
	          			<td width="15%">
							<html:text maxlength="10" property="numerosOS[0]" styleId="numerosOS[0]" size="10" onchange="bloqueiaCampos();" />
						</td>
	          			<td width="15%">
							<html:text maxlength="10" property="numerosOS[1]" styleId="numerosOS[1]" size="10" onchange="bloqueiaCampos();"  />
						</td>
	          			<td width="15%">
							<html:text maxlength="10" property="numerosOS[2]" styleId="numerosOS[2]" size="10" onchange="bloqueiaCampos();"  />
						</td>
	          			<td width="15%">
							<html:text maxlength="10" property="numerosOS[3]" styleId="numerosOS[3]" size="10" onchange="bloqueiaCampos();"  />
						</td>
	          			<td width="15%">
							<html:text maxlength="10" property="numerosOS[4]" styleId="numerosOS[4]" size="10" onchange="bloqueiaCampos();"  />
						</td>
					</tr>
	            	<tr>
	          			<td width="15%">
							<html:text maxlength="10" property="numerosOS[5]" styleId="numerosOS[5]" size="10" onchange="bloqueiaCampos();"  />
						</td>
	          			<td width="15%">
							<html:text maxlength="10" property="numerosOS[6]" styleId="numerosOS[6]" size="10" onchange="bloqueiaCampos();"  />
						</td>
	          			<td width="15%">
							<html:text maxlength="10" property="numerosOS[7]" styleId="numerosOS[7]" size="10" onchange="bloqueiaCampos();"  />
						</td>
	          			<td width="15%">
							<html:text maxlength="10" property="numerosOS[8]" styleId="numerosOS[8]" size="10" onchange="bloqueiaCampos();"  />
						</td>
	          			<td width="15%">
							<html:text maxlength="10" property="numerosOS[9]" styleId="numerosOS[9]" size="10" onchange="bloqueiaCampos();"  />
						</td>
					</tr>
				</table>
	          </td>
	        </tr>
	        <tr>
				<td colspan="2" width="100%">
				<hr>
				</td>
			</tr>
        </logic:present>
        
        <logic:notPresent name="habilitaNumerosOS" scope="session">
	        <logic:present name="motivoInformado" scope="session">
		        <tr>
		          <td width="25%"><strong>Número da Ordem de Serviço:<font color="#ff0000"></font></strong></td>
		          <td width="75%">
					<html:text maxlength="10" property="numerosOS[0]" size="10" />
		          </td>
		        </tr>
	        </logic:present>
	        <logic:notPresent name="motivoInformado" scope="session">
		        <tr>
		          <td width="25%"><strong>Número da Ordem de Serviço:<font color="#ff0000"></font></strong></td>
		          <td width="75%">
					<html:text maxlength="10" property="numerosOS[0]" size="10" style="background-color:#EFEFEF; border:0;" readonly="true"/>
		          </td>
		        </tr>
	        </logic:notPresent>
        </logic:notPresent>
        
       	<tr>
			<td width="25%"><strong>Data do Encerramento:<font color="#ff0000">*</font></strong></td>
			<td width="75%"><strong> <html:text maxlength="10"
				property="dataEncerramento" size="10" tabindex="10"
				onkeyup="mascaraData(this, event);" />
				<a
					href="javascript:abrirCalendario('MovimentarOrdemServicoActionForm', 'dataEncerramento');">
				<img border="0"
					src="<bean:message key="caminho.imagens"/>calendario.gif"
					width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
				</strong>
			</td>
		</tr>
		
		<tr>
			<td width="25%" ><strong>Hora do Encerramento:</strong></td>
			<td width="75%"> <html:text
				property="horaEncerramento" onkeyup="mascaraHoraSemMensagem(this, event)" onkeypress="return isCampoNumerico(event);"
				size="5" maxlength="5" /></td>
		</tr>
		
		<tr>
	      	<td width="25%"><strong>Observação:</strong></td>
	        <td width="75%">
				<html:textarea property="observacaoEncerramento" cols="40" rows="4" onkeyup="limitTextArea(document.forms[0].observacaoEncerramento, 400, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
				<strong><span id="utilizado">0</span>/<span id="limite">400</span></strong>
			</td>
      	</tr>
      	
		<tr>
          <td colspan="2"><div align="right"><jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=3"/></div></td>
        </tr>
        
       </table>
    </td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</body>
</html:form>
</html:html>
