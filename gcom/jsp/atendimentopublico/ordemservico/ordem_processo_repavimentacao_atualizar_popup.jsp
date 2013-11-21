<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ page import="gcom.gui.atendimentopublico.ordemservico.AtualizarOrdemProcessoRepavimentacaoPopUpActionForm"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
    <html:javascript staticJavascript="false"  formName="ConjuntoTramitacaoRaActionForm"/>		
<script language="JavaScript">

 
function desfazer(){
var form = document.forms[0];
    form.dataExecucao.value = ""; 
    form.idPavimentoRuaRet.value = "";
	form.areaPavimentoRuaRet.value = "";		
}
  	
function DateValidations () { 
     this.aa = new Array("dataExecucao", "Data da Execução não é uma data válida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
} 	
	
 function validateConfirmarOrdemProcessoRepavimentacaoPopUpActionForm() { 
     var form = document.forms[0];       
       
       if ( testarCampoValorZeroDecimal(form.areaPavimentoRuaRet, 'Área Pavimento de Retono') ) { 
	        
	        if ( form.dataExecucao.value != null && form.dataExecucao.value != "" &&
	         	 form.idPavimentoRuaRet.value != null && form.idPavimentoRuaRet.value != "" &&
	         	 form.areaPavimentoRuaRet.value != null && form.areaPavimentoRuaRet.value != "" ) {
	         	 
		       	if(validateDate(form)){
		       		
		       		form.action = 'atualizarOrdemProcessoRepavimentacaoPopUpAction.do'; 
		    		submeterFormPadrao(form);
				}
	       
	       } else {
	       		alert("Todos os campos são obrigatórios");
		   }
	       		
	   }
  } 	
	
function validateAlterarOrdemProcessoRepavimentacaoPopUpActionForm(){
	var form = document.forms[0];       
    
    var empresa = form.nomeEmpresa.value;
    var msg1 = "Informar apenas a área e tipo de pavimento que foi oriundo de manutenção da ";
	var msg2 = "\n As informações divergentes precisarão ser confirmadas pela ";
	var msg3 = " antes da liberação \n para pagamento.";
	
	var msg = msg1.concat(empresa, msg2, empresa, msg3);
	
	if (confirm(msg)){
		form.action = 'exibirAtualizarOrdemProcessoRepavimentacaoPopupAction.do?habilitaConfirmar=true&acao=bloquear';
		form.submit();
	}
}
 		
</script>

</head>

<logic:notPresent name="fecharPopup">
	<body leftmargin="5" topmargin="5"
			onload="javascript:setarFoco('${requestScope.nomeCampo}');resizePageSemLink(650, 500);">
</logic:notPresent>
<logic:present name="fecharPopup">
	<body leftmargin="0" topmargin="0"
		onload="chamarReload('exibirManterOrdemProcessoRepavimentacaoAction.do?retornaDoPopup=1&page.offset='+ document.forms[0].manterPaginaAux.value);window.close()">
</logic:present>

<html:form action="/atualizarOrdemProcessoRepavimentacaoPopUpAction.do"
	name="AtualizarOrdemProcessoRepavimentacaoPopUpActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.AtualizarOrdemProcessoRepavimentacaoPopUpActionForm"
	method="post">
	
		<html:hidden property="manterPaginaAux" />
		<html:hidden property="nomeEmpresa" />

	<table width="600" border="0" cellpadding="0" cellspacing="5">
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
					<td width="11"><img
						src="<bean:message key="caminho.imagens"/>parahead_left.gif"
						border="0" /></td>
					<td class="parabg">Atualizar Processo Repavimentação </td>
					<td width="11" valign="top"><img
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>Para atualizar o processo de repavimentação, informe os dados abaixo:</td>
				</tr>							
			</table>
			<table width="100%" border="0">
				<tr>		
					<td width="25%"><strong>Data do Retorno:</strong><font color="#ff0000">*</font></td>
					<td width="30%">
					 <html:text property="dataExecucao" 	tabindex="11" size="10" maxlength="10" onkeyup="mascaraData(this, event)" />
					  <a	href="javascript:abrirCalendario('AtualizarOrdemProcessoRepavimentacaoPopUpActionForm', 'dataExecucao');" >
					    <img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/>
					  </a>  
					
					</td>
				</tr>
				
				<logic:present name="habilitaAtualizar" scope="session">
					<logic:equal name="habilitaAtualizar" value="true">
						<tr>
							<td width="25%"><strong>Pvt. Rua T (ret):</strong><font color="#ff0000">*</font></td>
							<td width="28%"> 
								<logic:present name="colecaoPavimentoRua">  
			                   	   	<html:select property="idPavimentoRuaRet" >						   								
									<logic:present name="colecaoPavimentoRua">
										    <html:option value="">&nbsp;</html:option>
											<html:options collection="colecaoPavimentoRua" labelProperty="descricao" property="id" />
										</logic:present>
									</html:select>
			                	</logic:present> 
	                		</td>
	                		<td width="40%">
	                			<strong>
	                				<font color="#ff0000">Apenas o referente ao conserto</font>
	                			</strong>
	                		</td>
	                		<td width="5%" align="left">
	                			<strong><font color="#ff0000"><bean:write name="AtualizarOrdemProcessoRepavimentacaoPopUpActionForm" property="nomeEmpresa"/></font></strong>
	                		</td>
						</tr>	
						<tr>
					  		<td class="style3" width="25%"><strong>Pvt.Rua m2 (ret):</strong><font color="#ff0000">*</font></td>
					  		<td width="30%">
							  <html:text property="areaPavimentoRuaRet" 
							  			 size="10" 
							  			 maxlength="10" 
							  			 onkeyup="formataValorMonetario(this, 6)"/> 
					  		</td>
						</tr>
						<tr>
							<td width="15%"><strong>Observação:<font color="#FF0000">*</font></strong></td>
							<td width="85%" colspan="2">
								<html:textarea property="observacao" cols="40" rows="5"/><br>		
							</td>
						</tr>
					</logic:equal>
				</logic:present>
				
				<logic:notPresent name="habilitaAtualizar" scope="session">
					<tr>
						<td width="25%"><strong>Pvt. Rua T (ret):</strong><font color="#ff0000">*</font></td>
						<td width="28%" > 
							<logic:present name="colecaoPavimentoRua">  
		                   	   	<html:select property="idPavimentoRuaRet" disabled="true">						   								
								<logic:present name="colecaoPavimentoRua">
									    <html:option value="">&nbsp;</html:option>
										<html:options collection="colecaoPavimentoRua" labelProperty="descricao" property="id" />
									</logic:present>
								</html:select>
		                	</logic:present> 
                		</td>
                		<td width="40%" >
                			<strong>
                				<font color="#ff0000">Apenas o referente ao conserto</font>
                			</strong>
	                	</td>
	                	<td width="5%" align="left">
                			<strong>
                				<font color="#ff0000"><bean:write name="AtualizarOrdemProcessoRepavimentacaoPopUpActionForm" property="nomeEmpresa"/> </font>
                			</strong>
	                	</td>
					</tr>	
					<tr>
				  		<td class="style3" width="25%"><strong>Pvt.Rua m2 (ret):</strong><font color="#ff0000">*</font></td>
				  		<td width="30%">
						  <html:text property="areaPavimentoRuaRet" 
						  			 size="10" 
						  			 maxlength="10" 
						  			 onkeyup="formataValorMonetario(this, 6)" 
						  			 disabled="true"/> 
				  		</td>
					</tr>
					<tr>
						<td width="15%"><strong>Observação:<font color="#FF0000">*</font></strong></td>
						<td width="85%" colspan="2">
							<html:textarea property="observacao" cols="40" rows="5" disabled="true"/><br>		
						</td>
					</tr>
				</logic:notPresent>
			

				<tr>
					<td width="25%">&nbsp;</td>
				</tr>
			</table>
			
			<table width="100%" border="0">
				<tr>
					<td>
						<input name="Button" type="button" 
							   class="bottonRightCol" value="Voltar" 
							   align="left" onclick="window.close();" >
                    </td>

                 	<td width="100%" align="right">						
						<input  type="button" 
								class="bottonRightCol" 
							    value="Confirmar" 
							    onClick="javascript:validateConfirmarOrdemProcessoRepavimentacaoPopUpActionForm();">
					</td>
                    	
                    <logic:equal name="desabilitaBotaoAlterar" scope="session" value="false">
                    	<td width="66%" align="right">						
							<input  type="button" 
									class="bottonRightCol" 
									value="Alterar"
									onClick="javascript:validateAlterarOrdemProcessoRepavimentacaoPopUpActionForm();">	
						</td>
                    </logic:equal>
                    
				</tr>
				
				<tr>
					<td colspan="3"><hr></td>
				</tr>
				
				<tr>
					 <logic:equal name="desabilitaBotaoAlterar" scope="session" value="false">
						<td colspan="3" align="right">
							<input  type="button" 
									class="bottonRightCol" 
								    value="Rejeitar" 
								    onClick="javascript:redirecionarSubmit('exibirRejeitarOrdemProcessoRepavimentacaoPopupAction.do?caminhoTelaPesquisaRetorno=exibirAtualizarOrdemProcessoRepavimentacaoPopupAction');">
						</td>
					</logic:equal>
					
					<logic:notPresent name="desabilitaBotaoAlterar" scope="session" >
						<td colspan="3" align="right">
							<input  type="button" 
									class="bottonRightCol" 
								    value="Rejeitar" 
								    onClick="javascript:redirecionarSubmit('exibirRejeitarOrdemProcessoRepavimentacaoPopupAction.do?caminhoTelaPesquisaRetorno=exibirAtualizarOrdemProcessoRepavimentacaoPopupAction');">
						</td>
					</</logic:notPresent>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>