<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ include file="/jsp/util/telaespera.jsp"%>
<%@ page import="gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoRua"%>
<%@ page import="gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoCalcada"%>
<%@ page import="gcom.util.Util" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GCOM - Sistema de Gest&atilde;o Comercial</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<html:javascript staticJavascript="false"  formName="ExibirInserirCustoPavimentoPorRepavimentadoraActionForm"/>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">
	
	function validarForm(){
		var form = document.forms[0];
		
		if(validateExibirInserirCustoPavimentoPorRepavimentadoraActionForm(form)){
			submeterFormPadrao(form);
		}
	}
	
	function validarDadosPavimentoRua(){
		var form = document.forms[0];
		var retorno = false;
		
		if(form.idUnidadeRepavimentadora.value != '' && form.idUnidadeRepavimentadora.value != '-1'){
			if(form.idTipoPavimentoRua.value != '' && form.idTipoPavimentoRua.value != '-1'){
				if(form.valorPavimentoRua.value != ''){
					if (form.dataVigenciaInicialPavimentoRua.value != ''){
						if(verificaData(form.dataVigenciaInicialPavimentoRua)){
							retorno = true;
				  		}
				  	}else{
				  		alert('Informe o Início da Vigência.');
				  	}
				}else{
					alert('Informe o Valor do Pavimento de Rua(m2).');
				}
			}else{
				alert('Informe o Tipo de Pavimento Rua.');
			}
		}else{
			alert('Informe a Unidade Repavimentadora.');
		}
		
		return retorno;
	}
	
	function validarDadosPavimentoCalcada(){
		var form = document.forms[0];
		var retorno = false;
		
		if(form.idUnidadeRepavimentadora.value != '' && form.idUnidadeRepavimentadora.value != '-1'){
			if(form.idTipoPavimentoCalcada.value != '' && form.idTipoPavimentoCalcada.value != '-1'){
				if(form.valorPavimentoCalcada.value != ''){
					if (form.dataVigenciaInicialPavimentoCalcada.value != ''){
						if(verificaData(form.dataVigenciaInicialPavimentoCalcada)){
							retorno = true;
				  		}
				  	}else{
				  		alert('Informe o Início da Vigência.');
				  	}
				}else{
					alert('Informe o Valor do Pavimento de Calcada(m2).');
				}
			}else{
				alert('Informe o Tipo de Pavimento Calcada.');
			}
		}else{
			alert('Informe a Unidade Repavimentadora.');
		}
		
		return retorno;
	}
	
	function adicionarRua(){
		var form = document.forms[0];
		
		if(validarDadosPavimentoRua()){
			
			form.action = 'exibirInserirCustoPavimentoPorRepavimentadoraAction.do?acao=adicionar&pavimento=rua';
			form.submit();
		}
	}
	
	function adicionarCalcada(){
		var form = document.forms[0];
		
		if(validarDadosPavimentoCalcada()){
			
			form.action = 'exibirInserirCustoPavimentoPorRepavimentadoraAction.do?acao=adicionar&pavimento=calcada';
			form.submit();
		}
	}
	
	function removerRua(obj){
		var form = document.forms[0];
		
		if (confirm('Confirma Remoção?')) {
	
			form.action = 'exibirInserirCustoPavimentoPorRepavimentadoraAction.do?acao=remover&pavimento=rua&id='+obj;
			form.submit();
		}
	}
	
	function removerCalcada(obj){
		var form = document.forms[0];
		
		if (confirm('Confirma Remoção?')) {
	
			form.action = 'exibirInserirCustoPavimentoPorRepavimentadoraAction.do?acao=remover&pavimento=calcada&id='+obj;
			form.submit();
		}
	}
	
	function desfazer(){
		var form = document.forms[0];

		form.action = 'exibirInserirCustoPavimentoPorRepavimentadoraAction.do?acao=desfazer';
		form.submit();
	}
  	 	
</script>

</head>

<body leftmargin="5" topmargin="5" onload ="" >

<div id="formDiv"><html:form action="/inserirCustoPavimentoPorRepavimentadoraAction.do"
	name="ExibirInserirCustoPavimentoPorRepavimentadoraActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.ExibirInserirCustoPavimentoPorRepavimentadoraActionForm"
	method="post">

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
			<td width="600" valign="top" class="centercoltext">
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
					<td class="parabg">Inserir Custo do Pavimento por Repavimentadora</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="4">Para adicionar o custo do pavimento por repavimentadora, informe os dados abaixo:</td>
				</tr>
				
				<tr>
					<td colspan="4">
						&nbsp;
					</td>
				</tr>
				
				<tr>	
					<td colspan="1" width="35%">
				   		<strong>Unidade Repavimentadora:<font color="#FF0000"></font></strong>
				   </td>
				   
				   <td colspan="3">
						<html:select property="idUnidadeRepavimentadora" 
							 		 tabindex="1" >
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoUnidadeRepavimentadora"
									  labelProperty="descricao" 
									  property="id" />
						</html:select> 
						<font size="1">&nbsp; </font>
					</td>
                   
				</tr>
				
				<tr>
					<td colspan="4"><hr></td>
				</tr>
				
				<tr>	
					<td colspan="1">
				   		<strong>Tipo de Pavimento Rua:<font color="#FF0000"></font></strong>
				   </td>
				   
				   <td colspan="3">
						<html:select property="idTipoPavimentoRua" 
							 		 tabindex="2" >
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoPavimentoRua"
									  labelProperty="descricao" 
									  property="id" />
						</html:select> 
						<font size="1">&nbsp; </font>
					</td>
				</tr>
				
				<tr>
					<td colspan="1">
						<strong> Valor do Pavimento de Rua(m2): <font color="#FF0000"></font> </strong>
					</td>
					
					<td colspan="3" align="right">
						<div align="left"><html:text property="valorPavimentoRua" size="14"
							 maxlength="14" tabindex="3"
							 onkeyup="javascript:formataValorMonetario(this, 11)"
							 style="text-align:right;" />
						</div>
					</td>
				</tr>
				
				<tr> 
		        	<td colspan="1"><strong>Início da Vigência:<font color="#FF0000"></font> </strong></td>
		        	<td colspan="3" valign="middle">
			            <html:text maxlength="10" 
			            		   property="dataVigenciaInicialPavimentoRua" 
			            		   size="10" tabindex="4"
			            		   onkeypress="return isCampoNumerico(event);"
			            		   onkeyup="javascript:mascaraData(this,event);"/>
			            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" 
			            	 onclick="javascript:abrirCalendario('ExibirInserirCustoPavimentoPorRepavimentadoraActionForm', 'dataVigenciaInicialPavimentoRua');" 
			            	 width="20" border="0" 
			            	 align="middle" alt="Exibir Calendário" /> (dd/mm/aaaa) 
		        	</td>
		        </tr>
				
				<tr>
					
					<td align="right" colspan="5">
						<input type="button" 
							   name="Button" 
							   tabindex="5"
							   class="bottonRightCol"
							   value="Adicionar"
							   onclick="javascript:adicionarRua();">
					</td>
				</tr>
				
				<logic:present name="colecaoUnidadeRepavimentadoraCustoPavimentoRua" scope="session">
					<tr>
						<td colspan="4">
							<table width="100%" cellpadding="0" cellspacing="0" border="0">
								<tr>
									<td>
										<table width="100%" bgcolor="#99CCFF" border="0">
											<tr bgcolor="#99CCFF">
												<td width="10%" align="center">
													<strong>Remover</strong>
												</td>
												<td width="40%" align="center">
													<strong>Tipo de Pavimento de Rua</strong>
												</td>
												<td width="20%" align="center">
													<strong>Valor do Pavimento de Rua(m2)</strong>
												</td>
												<td width="30%" align="center">
													<strong>Início da Vigência</strong>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								
								<tr>
									<td>
										<%String cor2 = "#FFFFFF";%> 
										<%--scroll --%>
										<div style="width: 100%; height: 100; overflow: auto;">
											<table width="100%" cellpadding="0" cellspacing="0" border="0">
												<tr>
													<td> <%cor2 = "#cbe5fe";%>
														<table width="100%" bgcolor="#99CCFF" border="0">
															<logic:iterate name="colecaoUnidadeRepavimentadoraCustoPavimentoRua" 
														   				   id="unidadeRepavimentadoraCustoPavimentoRua"
														   				   type="UnidadeRepavimentadoraCustoPavimentoRua">
														   				   
																<c:set var="count2" value="${count2+1}" />
																<c:choose>
																	<c:when test="${count2%2 == '1'}">
																		<tr bgcolor="#FFFFFF">
																	</c:when>
																	<c:otherwise>
																		<tr bgcolor="#cbe5fe">
																	</c:otherwise>
																</c:choose>
				
																<td align="center" width="10%">
																	<img src="<bean:message key='caminho.imagens'/>Error.gif" 
																		 style="cursor: hand;"
																		 onclick="removerRua('${count2}')"
																		 title="Remover" > 
																</td>
															
																<td width="40%">
																	<div align="left">
																		<bean:write name="unidadeRepavimentadoraCustoPavimentoRua" property="pavimentoRua.descricao" />
																	</div>
																</td>
															
																<td width="20%" >
																	<div align="right">
																		<%=Util.formatarMoedaReal(unidadeRepavimentadoraCustoPavimentoRua.getValorPavimento())%>
																	</div>
																</td>
																
																<td width="30%">
																	<div align="center">
																		<%=gcom.util.Util.formatarData(unidadeRepavimentadoraCustoPavimentoRua.getDataVigenciaInicial())%>
																	</div>
																</td>
																	
															</logic:iterate>
														</table>
													</td>
												</tr>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</logic:present>
				
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				
				<tr>
					<td colspan="4"><hr></td>
				</tr>
				
				<tr>	
					<td colspan="1">
				   		<strong>Tipo de Pavimento Calçada:<font color="#FF0000"></font></strong>
				   </td>
				   
				   <td colspan="3">
						<html:select property="idTipoPavimentoCalcada" 
							 		 tabindex="6" >
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoPavimentoCalcada"
									  labelProperty="descricao" 
									  property="id" />
						</html:select> 
						<font size="1">&nbsp; </font>
					</td>
				</tr>
				
				<tr>
					<td colspan="1">
						<strong> Valor do Pavimento de Calçada(m2): <font color="#FF0000"></font> </strong>
					</td>
					
					<td colspan="3" align="right">
						<div align="left"><html:text property="valorPavimentoCalcada" size="14"
							 maxlength="14" tabindex="7"
							 onkeyup="javascript:formataValorMonetario(this, 11)"
							 style="text-align:right;" />
						</div>
					</td>
				</tr>
				
				<tr> 
		        	<td colspan="1"><strong>Início da Vigência:<font color="#FF0000"></font> </strong></td>
		        	<td colspan="3" valign="middle">
			            <html:text maxlength="10" 
			            		   property="dataVigenciaInicialPavimentoCalcada" 
			            		   size="10" tabindex="8"
			            		   onkeypress="return isCampoNumerico(event);"
			            		   onkeyup="javascript:mascaraData(this,event);"/>
			            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" 
			            	 onclick="javascript:abrirCalendario('ExibirInserirCustoPavimentoPorRepavimentadoraActionForm', 'dataVigenciaInicialPavimentoCalcada');" 
			            	 width="20" border="0" 
			            	 align="middle" alt="Exibir Calendário" /> (dd/mm/aaaa) 
		        	</td>
		        </tr>
				
				<tr>
					
					<td align="right" colspan="5">
						<input type="button" 
							   name="Button" 
							   tabindex="9"
							   class="bottonRightCol"
							   value="Adicionar"
							   onclick="javascript:adicionarCalcada();">
					</td>
				</tr>

				<logic:present name="colecaoUnidadeRepavimentadoraCustoPavimentoCalcada" scope="session">
					<tr>
						<td colspan="4">
							<table width="100%" cellpadding="0" cellspacing="0" border="0">
								<tr>
									<td>
										<table width="100%" bgcolor="#99CCFF" border="0">
											<tr bgcolor="#99CCFF">
												<td width="10%" align="center">
													<strong>Remover</strong>
												</td>
												<td width="40%" align="center">
													<strong>Tipo de Pavimento de Calçada</strong>
												</td>
												<td width="20%" align="center">
													<strong>Valor do Pavimento de Calçada(m2)</strong>
												</td>
												<td width="30%" align="center">
													<strong>Início da Vigência</strong>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								
								<tr>
									<td>
										<%String cor3 = "#FFFFFF";%> 
										<%--scroll --%>
										<div style="width: 100%; height: 100; overflow: auto;">
											<table width="100%" cellpadding="0" cellspacing="0" border="0">
												<tr>
													<td> <%cor3 = "#cbe5fe";%>
														<table width="100%" bgcolor="#99CCFF" border="0">
															<logic:iterate name="colecaoUnidadeRepavimentadoraCustoPavimentoCalcada" 
														   				   id="unidadeRepavimentadoraCustoPavimentoCalcada"
														   				   type="UnidadeRepavimentadoraCustoPavimentoCalcada">
														   				   
																<c:set var="count" value="${count+1}" />
																<c:choose>
																	<c:when test="${count%2 == '1'}">
																		<tr bgcolor="#FFFFFF">
																	</c:when>
																	<c:otherwise>
																		<tr bgcolor="#cbe5fe">
																	</c:otherwise>
																</c:choose>
				
																<td align="center" width="10%">
																	<img src="<bean:message key='caminho.imagens'/>Error.gif" 
																		 style="cursor: hand;"
																		 onclick="removerCalcada('${count}')"
																		 title="Remover" > 
																</td>
															
																<td width="40%">
																	<div align="left">
																		<bean:write name="unidadeRepavimentadoraCustoPavimentoCalcada" property="pavimentoCalcada.descricao" />
																	</div>
																</td>
															
																<td width="20%" >
																	<div align="right">
																		<%=Util.formatarMoedaReal(unidadeRepavimentadoraCustoPavimentoCalcada.getValorPavimento())%>
																	</div>
																</td>
																
																<td width="30%">
																	<div align="center">
																		<%=gcom.util.Util.formatarData(unidadeRepavimentadoraCustoPavimentoCalcada.getDataVigenciaInicial())%>
																	</div>
																</td>
																	
															</logic:iterate>
														</table>
													</td>
												</tr>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</logic:present>
				
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				
				<tr>
					<td colspan="4"><hr></td>
				</tr>
			
				<tr>
					<td colspan="1">&nbsp;</td>
					<td align="left" colspan="3"><font color="#FF0000">*</font> Campo Obrigatório</td>
				</tr>
				
				<tr>
					<td colspan="4">
						&nbsp;
					</td>
				</tr>
						
				<tr>
					<td colspan="4">
						&nbsp;
					</td>
				</tr>
							          	
				<tr>
					<td align="left" colspan="3">
			          	<input type="button" 
			          		   class="bottonRightCol" 
			          		   tabindex="10"
			          		   value="Desfazer" 
			          		   onClick="javascript:desfazer();" />
			          		   
			          	<input type="button" 
							   name="ButtonCancelar" 
							   class="bottonRightCol" 
							   tabindex="11"
							   value="Cancelar"
							   onClick="javascript:document.forms[0].target='';window.location.href='/gsan/telaPrincipal.do'">
					</td>
					
					<td align="right" colspan="1">
						<input type="button" 
							   name="Button" 
							   tabindex="12"
							   class="bottonRightCol" 
							   value="Informar" 
							   onClick="javascript:validarForm()" />
					</td>
				</tr>	
									
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
		
<%@ include file="/jsp/util/rodape.jsp"%>	
</html:form></div>
</body>
</html:html>
