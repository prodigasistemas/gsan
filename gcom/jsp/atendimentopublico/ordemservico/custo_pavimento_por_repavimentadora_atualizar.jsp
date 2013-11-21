<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ include file="/jsp/util/telaespera.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GCOM - Sistema de Gest&atilde;o Comercial</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="AtualizarCustoPavimentoPorRepavimentadoraActionForm"/>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
	
	function validarFormRua(){
		var form = document.forms[0];

		if(validateAtualizarCustoPavimentoPorRepavimentadoraActionForm(form)){
			
			if(form.valorPavimentoRua.value != ''){
				
				if (form.dataVigenciaInicialPavimentoRua.value != '' && form.dataVigenciaFinalPavimentoRua.value != ''){
					
					if(verificaData(form.dataVigenciaInicialPavimentoRua) && verificaData(form.dataVigenciaFinalPavimentoRua)){
						
						if (comparaData(form.dataVigenciaInicialPavimentoRua.value, "<", form.dataVigenciaFinalPavimentoRua.value )){
			  				
			  				form.action = "/gsan/atualizarCustoPavimentoPorRepavimentadoraAction.do?acao=atualizarRua";
							form.submit();
			  				
			  			}else{
			  				alert('Informe uma data de vigência final superior a inicial.');			
			  			}
			  		}
			  	}else if(form.dataVigenciaInicialPavimentoRua.value != ''){
			  		
			  		form.action = "/gsan/atualizarCustoPavimentoPorRepavimentadoraAction.do?acao=atualizarRua";
					form.submit();
			  	}else{
			  		alert('Informe a data de vigência inicial.');
			  	}
			}else{
				alert('Informe o Valor do Pavimento de Rua(m2).');
			}
		}
	}
	
	function validarFormCalcada(){
		var form = document.forms[0];

		if(validateAtualizarCustoPavimentoPorRepavimentadoraActionForm(form)){
			
			if(form.valorPavimentoCalcada.value != ''){
				
				if (form.dataVigenciaInicialPavimentoCalcada.value != '' && form.dataVigenciaFinalPavimentoCalcada.value != ''){
					
					if(verificaData(form.dataVigenciaInicialPavimentoCalcada) && verificaData(form.dataVigenciaFinalPavimentoCalcada)){
						
						if (comparaData(form.dataVigenciaInicialPavimentoCalcada.value, "<", form.dataVigenciaFinalPavimentoCalcada.value )){
			  				
			  				form.action = "/gsan/atualizarCustoPavimentoPorRepavimentadoraAction.do?acao=atualizarCalcada";
							form.submit();
			  				
			  			}else{
			  				alert('Informe uma data de vigência final superior a inicial.');			
			  			}
			  		}
			  	}else if(form.dataVigenciaInicialPavimentoCalcada.value != ''){
			  		form.action = "/gsan/atualizarCustoPavimentoPorRepavimentadoraAction.do?acao=atualizarCalcada";
					form.submit();
			  	}else{
			  		alert('Informe a data de vigência inicial.');
			  	}
			}else{
				alert('Informe o Valor do Pavimento de Calçada(m2).');
			}
		}
	}
  	 	
</script>

</head>

<div id="formDiv"><html:form action="/atualizarCustoPavimentoPorRepavimentadoraAction.do"
	name="AtualizarCustoPavimentoPorRepavimentadoraActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.AtualizarCustoPavimentoPorRepavimentadoraActionForm"
	method="post">
	
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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
		
		<td width="615" valign="top" class="centercoltext">
	    	<table height="100%">
		    	<tr>
		        	<td></td>
		        </tr>
	      	</table>
	     
	      	<logic:present name="idUnidadeRepavimentadoraCustoPavimentoRua" scope="request">

				<html:hidden property="valorPavimentoCalcada" />
	      		<html:hidden property="dataVigenciaInicialPavimentoCalcada" />
	      		<html:hidden property="dataVigenciaFinalPavimentoCalcada" />
				
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
						<td class="parabg">Atualizar Custo do Pavimento de Rua por Repavimentadora</td>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
				</table>
				<p>&nbsp;</p>
				<table width="100%" border="0">
					
					<tr>
						<td colspan="5">Para atualizar o custo do pavimento de rua, informe os dados abaixo:</td>
					</tr>
					
					<tr>	
						<td colspan="1">
					   		<strong>Unidade Repavimentadora:<font color="#FF0000"></font></strong>
					   </td>
	                   <td height="24" colspan="4">
	                   		   
	                       	<html:text property="descricaoUnidadeRepavimentadora" 
	                       			   size="30" 
	                       			   maxlength="30" 
	                       			   readonly="true" 
	                       			   style="background-color:#EFEFEF; border:0; color: #000000" />                  
					   </td>
					</tr>
					
					<tr>	
						<td colspan="1">
					   		<strong>Tipo de Pavimento de Rua:<font color="#FF0000"></font></strong>
					   	</td>
	                   	<td height="24" colspan="4">
	                   		   
	                       	<html:text property="descricaoTipoPavimentoRua" 
	                       			   size="20" 
	                       			   maxlength="20" 
	                       			   readonly="true" 
	                       			   style="background-color:#EFEFEF; border:0; color: #000000" />                  
						</td>
					</tr>
					
					<tr>
						<td colspan="5" bordercolor="#FFFFFF" bgcolor="#cbe5fe"><hr></td>
					</tr>
					
					<tr>
						<td colspan="2"><strong>Valor do Pavimento de Rua(m2): <font color="#FF0000">*</font> </strong>
						</td>
						<td colspan="3" align="right" width="330">
							<div align="left">
								<html:text property="valorPavimentoRua" 
										   size="15"
										   maxlength="15"
										   onkeyup="javascript:formataValorMonetario(this, 13)"
										   style="text-align:right;" 
								/>
							</div>
						</td>
					</tr>
					
					<tr> 
			        	<td colspan="2"><strong>Período de Vigência do Custo do Pav. Rua:<font color="#FF0000">*</font></strong></td>
			        	<td valign="middle" colspan="3">
				            <html:text maxlength="10" property="dataVigenciaInicialPavimentoRua" 
				            		   size="10" onkeypress="return isCampoNumerico(event);"
				            		   onkeyup="javascript:mascaraData(this,event);"/>
				            		   
				            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" 
				            	 onclick="javascript:abrirCalendario('AtualizarCustoPavimentoPorRepavimentadoraActionForm', 'dataVigenciaInicialPavimentoRua');" 
				            	 width="20" border="0" align="middle" alt="Exibir Calendário" /><strong> a</strong> 
				            
				            <html:text maxlength="10" property="dataVigenciaFinalPavimentoRua" 
				            		   size="10" onkeypress="javascript:mascaraData(this,event);return isCampoNumerico(event);"/>
				            
				            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" 
				            	 onclick="javascript:abrirCalendario('AtualizarCustoPavimentoPorRepavimentadoraActionForm', 'dataVigenciaFinalPavimentoRua')"
				           		 width="20" border="0" align="middle" alt="Exibir Calendário" /> (dd/mm/aaaa) 
			        	</td>
			        </tr>
					
					<tr>
						<td colspan="5" bordercolor="#FFFFFF" bgcolor="#cbe5fe"><hr></td>
					</tr>
					
					<tr>
						<td colspan="2">&nbsp;</td>
						<td align="left" colspan="3">
							<font color="#FF0000">*</font> Campo Obrigatório
						</td>
					</tr>
					
					<tr>
						<td>&nbsp;</td>
					</tr>
									          	
					<tr>
						<td align="left" colspan="4">
				          	
				          	<input type="button" 
								   name="ButtonReset" 
								   class="bottonRightCol"
								   value="Voltar"
								   onClick="javascript:window.location.href='/gsan/filtrarCustoPavimentoPorRepavimentadoraAction.do'">
				          	
				          	<input type="button" 
								   name="ButtonReset"
								   class="bottonRightCol" 
								   value="Desfazer"
								   onClick="window.location.href='<html:rewrite page="/exibirAtualizarCustoPavimentoPorRepavimentadoraAction.do?acao=atualizarRua&idAtualizacao=${requestScope.idUnidadeRepavimentadoraCustoPavimentoRua}"/>'">
								   
							<input type="button" 
							       name="ButtonCancelar" 
							       class="bottonRightCol"
								   value="Cancelar"
								   onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</td>
						
						<td align="right">
							<input type="button" 
								   name="Button" 
								   class="bottonRightCol" 
								   value="Atualizar" 
								   onClick="javascript:validarFormRua()" />
						</td>
						
					</tr>							
				</table>
			</logic:present>
			
			<logic:present name="idUnidadeRepavimentadoraCustoPavimentoCalcada" scope="request">
				
				<html:hidden property="valorPavimentoRua" />
	      		<html:hidden property="dataVigenciaInicialPavimentoRua" />
	      		<html:hidden property="dataVigenciaFinalPavimentoRua" />
				
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
						<td class="parabg">Atualizar Custo do Pavimento de Calçada por Repavimentadora</td>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
				</table>
				<p>&nbsp;</p>
				<table width="100%" border="0">
					
					<tr>
						<td colspan="5">Para atualizar o custo do pavimento de Calçada, informe os dados abaixo:</td>
					</tr>
					
					<tr>	
						<td colspan="1">
					   		<strong>Unidade Repavimentadora:<font color="#FF0000"></font></strong>
					   </td>
	                   <td height="24" colspan="4">
	                   		   
	                       	<html:text property="descricaoUnidadeRepavimentadora" 
	                       			   size="30" 
	                       			   maxlength="30" 
	                       			   readonly="true" 
	                       			   style="background-color:#EFEFEF; border:0; color: #000000" />                  
					   </td>
					</tr>
					
					<tr>	
						<td colspan="1">
					   		<strong>Tipo de Pavimento de Calçada:<font color="#FF0000"></font></strong>
					   	</td>
	                   	<td height="24" colspan="4">
	                   		   
	                       	<html:text property="descricaoTipoPavimentoCalcada" 
	                       			   size="20" 
	                       			   maxlength="20" 
	                       			   readonly="true" 
	                       			   style="background-color:#EFEFEF; border:0; color: #000000" />                  
						</td>
					</tr>
					
					<tr>
						<td colspan="5" bordercolor="#FFFFFF" bgcolor="#cbe5fe"><hr></td>
					</tr>
					
					<tr>
						<td colspan="2"><strong>Valor do Pavimento de Calçada(m2): <font color="#FF0000">*</font> </strong>
						</td>
						<td colspan="3" align="right" width="330">
							<div align="left">
								<html:text property="valorPavimentoCalcada" 
										   size="15"
										   maxlength="15"
										   onkeyup="javascript:formataValorMonetario(this, 13)"
										   style="text-align:right;" 
								/>
							</div>
						</td>
					</tr>
					
					<tr> 
			        	<td colspan="2"><strong>Período de Vigência do Custo do Pav. Calçada:<font color="#FF0000">*</font></strong></td>
			        	<td valign="middle" colspan="3">
				            <html:text maxlength="10" property="dataVigenciaInicialPavimentoCalcada" 
				            		   size="10" onkeypress="return isCampoNumerico(event);"
				            		   onkeyup="javascript:mascaraData(this,event);"/>
				            		   
				            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" 
				            	 onclick="javascript:abrirCalendario('AtualizarCustoPavimentoPorRepavimentadoraActionForm', 'dataVigenciaInicialPavimentoCalcada');" 
				            	 width="20" border="0" align="middle" alt="Exibir Calendário" /><strong> a</strong> 
				            
				            <html:text maxlength="10" property="dataVigenciaFinalPavimentoCalcada" 
				            		   size="10" onkeypress="javascript:mascaraData(this,event);return isCampoNumerico(event);"/>
				            
				            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" 
				            	 onclick="javascript:abrirCalendario('AtualizarCustoPavimentoPorRepavimentadoraActionForm', 'dataVigenciaFinalPavimentoCalcada')"
				           		 width="20" border="0" align="middle" alt="Exibir Calendário" /> (dd/mm/aaaa) 
			        	</td>
			        </tr>
					
					<tr>
						<td colspan="5" bordercolor="#FFFFFF" bgcolor="#cbe5fe"><hr></td>
					</tr>
					
					<tr>
						<td colspan="2">&nbsp;</td>
						<td align="left" colspan="3">
							<font color="#FF0000">*</font> Campo Obrigatório
						</td>
					</tr>
					
					<tr>
						<td>&nbsp;</td>
					</tr>
									          	
					<tr>
						<td align="left" colspan="4">
				          	
				          	<input type="button" 
								   name="ButtonReset" 
								   class="bottonRightCol"
								   value="Voltar"
								   onClick="javascript:window.location.href='/gsan/filtrarCustoPavimentoPorRepavimentadoraAction.do'">
				          	
				          	<input type="button" 
								   name="ButtonReset"
								   class="bottonRightCol" 
								   value="Desfazer"
								   onClick="window.location.href='<html:rewrite page="/exibirAtualizarCustoPavimentoPorRepavimentadoraAction.do?acao=atualizarCalcada&idAtualizacao=${requestScope.idUnidadeRepavimentadoraCustoPavimentoCalcada}"/>'">
								   
							<input type="button" 
							       name="ButtonCancelar" 
							       class="bottonRightCol"
								   value="Cancelar"
								   onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</td>
						
						<td align="right">
							<input type="button" 
								   name="Button" 
								   class="bottonRightCol" 
								   value="Atualizar" 
								   onClick="javascript:validarFormCalcada()" />
						</td>
						
					</tr>							
				</table>
			</logic:present>
			
			<p>&nbsp;</p>
		</td>
	</tr>
</table>
	<!-- Rodapé -->
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form></div>
</body>
</html:html>
