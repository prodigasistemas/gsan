<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.util.ConstantesSistema" %>
<%@page import="gcom.gui.atendimentopublico.ordemservico.OSSeletivaInspecaoAnormalidadeGeradaHelper" %>
<%@ page import="java.util.Collection" isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirGrupoActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script>
	function facilitador(objeto){
	
		if (objeto.id == "0" || objeto.id == undefined){
			objeto.id = "1";
			marcarTodos();
		}
		else{
			objeto.id = "0";
			desmarcarTodos();
		}
	}


	function bloqueiaCampos(){
		var form = document.forms[0];
		if((form.numeroOSInicialEmitir.value != null && form.numeroOSInicialEmitir.value != '')
			|| (form.numeroOSFinalEmitir.value != null && form.numeroOSFinalEmitir.value != '')) {
			
			document.getElementById('numerosOSEmitir[0]').value = "";
			document.getElementById('numerosOSEmitir[0]').style.color = "#000000";
			document.getElementById('numerosOSEmitir[0]').readOnly = true;
			document.getElementById('numerosOSEmitir[0]').style.backgroundColor = '#EFEFEF';
			
			document.getElementById('numerosOSEmitir[1]').value = "";
			document.getElementById('numerosOSEmitir[1]').style.color = "#000000";
			document.getElementById('numerosOSEmitir[1]').readOnly = true;
			document.getElementById('numerosOSEmitir[1]').style.backgroundColor = '#EFEFEF';
			
			document.getElementById('numerosOSEmitir[2]').value = "";
			document.getElementById('numerosOSEmitir[2]').style.color = "#000000";
			document.getElementById('numerosOSEmitir[2]').readOnly = true;
			document.getElementById('numerosOSEmitir[2]').style.backgroundColor = '#EFEFEF';
			
			document.getElementById('numerosOSEmitir[3]').value = "";
			document.getElementById('numerosOSEmitir[3]').style.color = "#000000";
			document.getElementById('numerosOSEmitir[3]').readOnly = true;
			document.getElementById('numerosOSEmitir[3]').style.backgroundColor = '#EFEFEF';
			
			document.getElementById('numerosOSEmitir[4]').value = "";
			document.getElementById('numerosOSEmitir[4]').style.color = "#000000";
			document.getElementById('numerosOSEmitir[4]').readOnly = true;
			document.getElementById('numerosOSEmitir[4]').style.backgroundColor = '#EFEFEF';
			
			document.getElementById('numerosOSEmitir[5]').value = "";
			document.getElementById('numerosOSEmitir[5]').style.color = "#000000";
			document.getElementById('numerosOSEmitir[5]').readOnly = true;
			document.getElementById('numerosOSEmitir[5]').style.backgroundColor = '#EFEFEF';
			
			document.getElementById('numerosOSEmitir[6]').value = "";
			document.getElementById('numerosOSEmitir[6]').style.color = "#000000";
			document.getElementById('numerosOSEmitir[6]').readOnly = true;
			document.getElementById('numerosOSEmitir[6]').style.backgroundColor = '#EFEFEF';
			
			document.getElementById('numerosOSEmitir[7]').value = "";
			document.getElementById('numerosOSEmitir[7]').style.color = "#000000";
			document.getElementById('numerosOSEmitir[7]').readOnly = true;
			document.getElementById('numerosOSEmitir[7]').style.backgroundColor = '#EFEFEF';
			
			document.getElementById('numerosOSEmitir[8]').value = "";
			document.getElementById('numerosOSEmitir[8]').style.color = "#000000";
			document.getElementById('numerosOSEmitir[8]').readOnly = true;
			document.getElementById('numerosOSEmitir[8]').style.backgroundColor = '#EFEFEF';
			
			document.getElementById('numerosOSEmitir[9]').value = "";
			document.getElementById('numerosOSEmitir[9]').style.color = "#000000";
			document.getElementById('numerosOSEmitir[9]').readOnly = true;
			document.getElementById('numerosOSEmitir[9]').style.backgroundColor = '#EFEFEF';
			
			
		} else if ((document.getElementById('numerosOSEmitir[0]').value != null && document.getElementById('numerosOSEmitir[0]').value != '')
				|| (document.getElementById('numerosOSEmitir[1]').value != null && document.getElementById('numerosOSEmitir[1]').value != '')
				|| (document.getElementById('numerosOSEmitir[2]').value != null && document.getElementById('numerosOSEmitir[2]').value != '')
				|| (document.getElementById('numerosOSEmitir[3]').value != null && document.getElementById('numerosOSEmitir[3]').value != '')
				|| (document.getElementById('numerosOSEmitir[4]').value != null && document.getElementById('numerosOSEmitir[4]').value != '')
				|| (document.getElementById('numerosOSEmitir[5]').value != null && document.getElementById('numerosOSEmitir[5]').value != '')
				|| (document.getElementById('numerosOSEmitir[6]').value != null && document.getElementById('numerosOSEmitir[6]').value != '')
				|| (document.getElementById('numerosOSEmitir[7]').value != null && document.getElementById('numerosOSEmitir[7]').value != '')
				|| (document.getElementById('numerosOSEmitir[8]').value != null && document.getElementById('numerosOSEmitir[8]').value != '')
				|| (document.getElementById('numerosOSEmitir[9]').value != null && document.getElementById('numerosOSEmitir[9]').value != '')){
						
			form.numeroOSInicialEmitir.value = "";
			form.numeroOSInicialEmitir.style.color = "#000000";
			form.numeroOSInicialEmitir.readOnly = true;
			form.numeroOSInicialEmitir.style.backgroundColor = '#EFEFEF';
			
			form.numeroOSFinalEmitir.value = "";
			form.numeroOSFinalEmitir.style.color = "#000000";
			form.numeroOSFinalEmitir.readOnly = true;
			form.numeroOSFinalEmitir.style.backgroundColor = '#EFEFEF';
					
		}
		
		if ((form.numeroOSInicialEmitir.value == null || form.numeroOSInicialEmitir.value == '')
			&& (form.numeroOSFinalEmitir.value == null || form.numeroOSFinalEmitir.value == '')
			&& (document.getElementById('numerosOSEmitir[0]').value == null || document.getElementById('numerosOSEmitir[0]').value == '')
			&& (document.getElementById('numerosOSEmitir[1]').value == null || document.getElementById('numerosOSEmitir[1]').value == '')
			&& (document.getElementById('numerosOSEmitir[2]').value == null || document.getElementById('numerosOSEmitir[2]').value == '')
			&& (document.getElementById('numerosOSEmitir[3]').value == null || document.getElementById('numerosOSEmitir[3]').value == '')
			&& (document.getElementById('numerosOSEmitir[4]').value == null || document.getElementById('numerosOSEmitir[4]').value == '')
			&& (document.getElementById('numerosOSEmitir[5]').value == null || document.getElementById('numerosOSEmitir[5]').value == '')
			&& (document.getElementById('numerosOSEmitir[6]').value == null || document.getElementById('numerosOSEmitir[6]').value == '')
			&& (document.getElementById('numerosOSEmitir[7]').value == null || document.getElementById('numerosOSEmitir[7]').value == '')
			&& (document.getElementById('numerosOSEmitir[8]').value == null || document.getElementById('numerosOSEmitir[8]').value == '')
			&& (document.getElementById('numerosOSEmitir[9]').value == null || document.getElementById('numerosOSEmitir[9]').value == '')) {
			
			form.numeroOSInicialEmitir.style.color = "#000000";
			form.numeroOSInicialEmitir.readOnly = false;
			form.numeroOSInicialEmitir.style.backgroundColor = '';
			
			form.numeroOSFinalEmitir.style.color = "#000000";
			form.numeroOSFinalEmitir.readOnly = false;
			form.numeroOSFinalEmitir.style.backgroundColor = '';
			
			document.getElementById('numerosOSEmitir[0]').style.color = "#000000";
			document.getElementById('numerosOSEmitir[0]').readOnly = false;
			document.getElementById('numerosOSEmitir[0]').style.backgroundColor = '';
			
			document.getElementById('numerosOSEmitir[1]').style.color = "#000000";
			document.getElementById('numerosOSEmitir[1]').readOnly = false;
			document.getElementById('numerosOSEmitir[1]').style.backgroundColor = '';
			
			document.getElementById('numerosOSEmitir[2]').style.color = "#000000";
			document.getElementById('numerosOSEmitir[2]').readOnly = false;
			document.getElementById('numerosOSEmitir[2]').style.backgroundColor = '';
			
			document.getElementById('numerosOSEmitir[3]').style.color = "#000000";
			document.getElementById('numerosOSEmitir[3]').readOnly = false;
			document.getElementById('numerosOSEmitir[3]').style.backgroundColor = '';
			
			document.getElementById('numerosOSEmitir[4]').style.color = "#000000";
			document.getElementById('numerosOSEmitir[4]').readOnly = false;
			document.getElementById('numerosOSEmitir[4]').style.backgroundColor = '';
			
			document.getElementById('numerosOSEmitir[5]').style.color = "#000000";
			document.getElementById('numerosOSEmitir[5]').readOnly = false;
			document.getElementById('numerosOSEmitir[5]').style.backgroundColor = '';
			
			document.getElementById('numerosOSEmitir[6]').style.color = "#000000";
			document.getElementById('numerosOSEmitir[6]').readOnly = false;
			document.getElementById('numerosOSEmitir[6]').style.backgroundColor = '';
			
			document.getElementById('numerosOSEmitir[7]').style.color = "#000000";
			document.getElementById('numerosOSEmitir[7]').readOnly = false;
			document.getElementById('numerosOSEmitir[7]').style.backgroundColor = '';
			
			document.getElementById('numerosOSEmitir[8]').style.color = "#000000";
			document.getElementById('numerosOSEmitir[8]').readOnly = false;
			document.getElementById('numerosOSEmitir[8]').style.backgroundColor = '';
			
			document.getElementById('numerosOSEmitir[9]').style.color = "#000000";
			document.getElementById('numerosOSEmitir[9]').readOnly = false;
			document.getElementById('numerosOSEmitir[9]').style.backgroundColor = '';
		}
	}
	

	function validateMovimentarOSSeletivaInspecaoAnormalidadeActionForm(form) {
		var form =  document.forms[0];
		
		return true;
    }
    
  
    
    function pesquisarOSRA(){
    
    	var form =  document.forms[0];
    	
    	if ((form.numeroOSInicialEmitir.value == null || form.numeroOSInicialEmitir.value == '')
			&& (form.numeroOSFinalEmitir.value == null || form.numeroOSFinalEmitir.value == '')){
			alert('Informe Intervalo de OS.');
    	}else{
	    	form.action = 'movimentarOSSeletivaInspecaoAnormalidadeWizardAction.do?action=exibirMovimentarOSSeletivaInspecaoAnormalidadeEmitirOSAction&pesquisarOSRA=sim';
	    	form.submit();
    	}
    }
    
</script>

</head>

<body onload="bloqueiaCampos();">

<html:form
    action="/movimentarOSSeletivaInspecaoAnormalidadeWizardAction"
    method="post"
    onsubmit="return validateMovimentarOSSeletivaInspecaoAnormalidadeActionForm(this);"
>

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=1"/>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">

<input type="hidden" name="numeroPagina" value="1"/>

<html:hidden property="tipoPesquisa"/>

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
          <td class="parabg">Movimentar Ordem de Serviço - Emitir OS</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
    
      <p>&nbsp;</p>
    
      <table border="0" width="100%">
        <tr>
          <td colspan="2">Para emitir OS de inspeção de anormalidade, informe os dados abaixo:</td>
        </tr>
        <tr>
        	<td colspan="2">
	        	<table border="0" width="100%">
	        		<tr>
					  <td width="45%"><strong>Comando para Inspeção de Anormalidade:<font color="#ff0000"></font></strong></td>
			          <td width="55%">
						<html:text maxlength="40" property="idComando" size="20" readonly="true"
									style="background-color:#EFEFEF; border:0; font-color: #000000" />
			          </td>        		
	        		</tr>
	        	</table>
        	</td>
        </tr>
					
		<tr>
			<td colspan="2">
			<hr>
			</td>
		</tr>
		
        <tr>
	        <td colspan="2">
		        <table border="0" width="100%">
		         <tr>
		          <td width="15%"><strong>Números de Ordens de Serviço:<font color="#ff0000"></font></strong></td>
		          <td>
		            <table border="0" width="100%">
		            	<tr>
		          			<td width="100%">
								<html:text maxlength="10" property="numerosOSEmitir[0]" styleId="numerosOSEmitir[0]" size="10" onchange="bloqueiaCampos();" onkeypress="return isCampoNumerico(event);" />
&nbsp;
								<html:text maxlength="10" property="numerosOSEmitir[1]" styleId="numerosOSEmitir[1]" size="10" onchange="bloqueiaCampos();" onkeypress="return isCampoNumerico(event);" />
&nbsp;
								<html:text maxlength="10" property="numerosOSEmitir[2]" styleId="numerosOSEmitir[2]" size="10" onchange="bloqueiaCampos();" onkeypress="return isCampoNumerico(event);" />
&nbsp;
								<html:text maxlength="10" property="numerosOSEmitir[3]" styleId="numerosOSEmitir[3]" size="10" onchange="bloqueiaCampos();" onkeypress="return isCampoNumerico(event);" />
&nbsp;
								<html:text maxlength="10" property="numerosOSEmitir[4]" styleId="numerosOSEmitir[4]" size="10" onchange="bloqueiaCampos();" onkeypress="return isCampoNumerico(event);" />
							</td>
						</tr>
		            	<tr>
		          			<td width="100%">
								<html:text maxlength="10" property="numerosOSEmitir[5]" styleId="numerosOSEmitir[5]" size="10" onchange="bloqueiaCampos();" onkeypress="return isCampoNumerico(event);" />
&nbsp;
								<html:text maxlength="10" property="numerosOSEmitir[6]" styleId="numerosOSEmitir[6]" size="10" onchange="bloqueiaCampos();" onkeypress="return isCampoNumerico(event);" />
&nbsp;
								<html:text maxlength="10" property="numerosOSEmitir[7]" styleId="numerosOSEmitir[7]" size="10" onchange="bloqueiaCampos();" onkeypress="return isCampoNumerico(event);" />
&nbsp;
								<html:text maxlength="10" property="numerosOSEmitir[8]" styleId="numerosOSEmitir[8]" size="10" onchange="bloqueiaCampos();" onkeypress="return isCampoNumerico(event);" />
&nbsp;
								<html:text maxlength="10" property="numerosOSEmitir[9]" styleId="numerosOSEmitir[9]" size="10" onchange="bloqueiaCampos();" onkeypress="return isCampoNumerico(event);" />
							</td>
						</tr>
					</table>
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
        <tr>
			<td colspan="2">
		      
			      <table border="0" width="100%">
					<tr>
						<td colspan="3">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td width="30%"><strong>Informe Intervalo de OS:<font color="#ff0000"></font></strong></td>
						<td width="45%" align="center">&nbsp;&nbsp;<strong>
			          		<html:text property="numeroOSInicialEmitir" size="14"
								maxlength="14" tabindex="12"
								onkeyup="replicarCampo(document.forms[0].numeroOSFinalEmitir, document.forms[0].numeroOSInicialEmitir);"
								onchange="javascript:bloqueiaCampos();" 
								 /> a 
							<html:text property="numeroOSFinalEmitir"
								size="14" maxlength="14" tabindex="13"
								onchange="javascript:bloqueiaCampos();" 
								 /> </strong>
							
			          	</td>
						<td width="25%" align="right"><input type="button"
							name="pesquisarOSEmpCob" class="bottonRightCol" value="Pesquisar"
							onClick="javascript:pesquisarOSRA();" />
						</td>
					</tr>

					<tr>
						<td colspan="3">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3">
						<table width="100%" bgcolor="#99CCFF" border="0">
							<tr bordercolor="#000000">
								<td width="10%" bgcolor="#90c7fc" align="center"><strong><a
									href="javascript:facilitador(this);">Todos</a></strong></td>
								<td width="15%" bordercolor="#000000" bgcolor="#90c7fc" align="center">
									<div align="center"><strong>Número O.S.</strong></div>
								</td>
								<td width="25%" bgcolor="#90c7fc" align="center"><strong>Tipo Serviço</strong></td>
								<td width="25%" bgcolor="#90c7fc" align="center"><strong>Matrícula</strong></td>
								<td width="25%" bgcolor="#90c7fc" align="center"><strong>Cliente</strong></td>
				
						</table>
						</td>
					</tr>
					<tr>
						<td colspan="3"><%if (session.getAttribute("colecaoOSEmitir") == null 
								|| session.getAttribute("colecaoOSEmitir").equals("")
								|| ((Collection) session.getAttribute("colecaoOSEmitir"))
										.size() <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_EMITIR_OS_COBRANCA_POR_RESULTADO) {%>
							<div style="width: 100%;">
							<%} else { %>
							<div style="width: 100%; height: 200; overflow: auto;">
							<%} %>
							<table width="100%" bgcolor="#99CCFF">
									<logic:present name="colecaoOSEmitir">
										<%int cont1 = 0;%>
										<logic:iterate name="colecaoOSEmitir" id="helper"
											type="OSSeletivaInspecaoAnormalidadeGeradaHelper" scope="session">
												<%cont1 = cont1 + 1;
										if (cont1 % 2 == 0) {%>
												<tr bgcolor="#cbe5fe">
													<%} else {%>
												</tr>
												<tr bgcolor="#FFFFFF">
													<%}%>
													<td width="10%">
													<div align="center"><html:checkbox property="numerosOSRegistroAtendimentoEmitir"
														value="${helper.numeroOS}"  />
													</div>
													</td>
		
													<td align="center" width="15%">
													<a href="javascript:abrirPopup('exibirConsultarDadosOrdemServicoPopupAction.do?numeroOS=${helper.numeroOS}&botaoEncerraOs=NAO', 475, 600);">
														<bean:write name="helper" property="numeroOS" /> 
													</a>	
													</td>
		
													<td align="center" width="25%"><bean:write name="helper" property="tipoServico"  /> </td>
		
													<td align="center" width="25%"><bean:write name="helper" property="matriculaImovel"  /> </td>
		
													<td align="center" width="25%"><bean:write name="helper" property="cliente"  /> </td>
		
												</tr>
										</logic:iterate>
									</logic:present>

							</table>
						</div>
					</td>
				</tr>
							
					<tr>
						<td colspan="2" rowspan="3">
							&nbsp;
						</td>
					</tr>
					</table>
		
	        </td>
        </tr>
        
        <tr>
          <td colspan="2"><div align="right"><jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=1"/></div></td>
        </tr>
        
     </table>
   </td>
 </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
