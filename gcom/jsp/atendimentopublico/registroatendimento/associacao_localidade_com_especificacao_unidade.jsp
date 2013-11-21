<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ include file="/jsp/util/telaespera.jsp"%>
<%@ page import="gcom.atendimentopublico.registroatendimento.LocalidadeEspecificacaoUnidade"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GCOM - Sistema de Gest&atilde;o Comercial</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="ExibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeActionForm"/>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">
	
	function validarForm(){
		var form = document.forms[0];
		
		if(validateExibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeActionForm(form)){
			submeterFormPadrao(form);
		}
	}
  	
  	function limparPesquisaLocalidade(){
		var form = document.forms[0];
		
		form.idLocalidade.value = "";
		form.nomeLocalidade.value = "";
		
		form.action = 'exibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeAction.do?acao=limpar';
		form.submit();
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
		
		if (tipoConsulta == 'localidade') {

			form.idLocalidade.value = codigoRegistro;
	  		form.nomeLocalidade.value = descricaoRegistro;
	  		form.nomeLocalidade.style.color = "#000000";
	  		form.idLocalidade.focus();
	 	}
	}
   
   function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg, campo){
		if(!campo.disabled){
	  		if (objeto == null || codigoObjeto == null){
	     		if(tipo == "" ){
	      			abrirPopup(url,altura, largura);
	     		}else{
		  			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		 		}
	 		}else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
  		}
	}
	
	function associarLocalidade(){
		var form = document.forms[0];
		
		if(form.idLocalidade.value != "" ){
			
			abrirPopup('exibirAssociarLocalidadeComEspecificacaoUnidadePopUpAction.do?idLocalidade='+form.idLocalidade.value, 360, 620);
		}else{
			alert('Informe a Localidade.');
			form.idLocalidade.focus();
		}
	}
	
	function remover(obj){
		var form = document.forms[0];
		
		if (confirm('Confirma Remoção?')) {
	
			form.action = 'exibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeAction.do?acao=remover&id='+obj;
			form.submit();
		}
	}
	
	function desfazer(){
		var form = document.forms[0];
		
		if(form.idLocalidade.value != ''){
			form.action = 'exibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeAction.do?acao=desfazer&id='+form.idLocalidade.value;
			form.submit();
		}
	}
  	 	
</script>

</head>

<body leftmargin="5" topmargin="5" onload ="" >

<div id="formDiv"><html:form action="/inserirAssociacaoLocalidadeComEspecificacaoUnidadeAction.do"
	name="ExibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.ExibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeActionForm"
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
					<td class="parabg">Informar Associação de Localidade, Especificação e Unidade</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="5">Para informar a associação, informe os dados abaixo:</td>
				</tr>
				
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				
				<tr>	
					<td colspan="1">
				   		<strong>Localidade:<font color="#FF0000">*</font></strong>
				   </td>
                   <td height="24" colspan="4">
                   		<html:text maxlength="3" 
                   				   property="idLocalidade" 
                   				   size="3"  
                   				   tabindex="1" 
                   				   onkeypress="javascript:validaEnterComMensagem(event, 'exibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeAction.do?objetoConsulta=1', 'idLocalidade','Localidade'); return isCampoNumerico(event);"
                   		/>
                      	<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do', 380, 620);">

                        	<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Localidade"/></a>
					
   		      			<logic:present name="localidadeInexistente" 
   		      						   scope="request">
							<input type="text" 
								   name="nomeLocalidade" 
								   size="30" 
								   maxlength="30" 
								   readonly="true" 
								   style="background-color:#EFEFEF; border:0; color: #ff0000" 
								   value="<bean:message key="pesquisa.localidade.inexistente"/>"/>
                      	</logic:present>

                        <logic:notPresent name="localidadeInexistente" 
                      					scope="request">
	                       	<html:text property="nomeLocalidade" 
	                       			   size="30" 
	                       			   maxlength="30" 
	                       			   readonly="true" 
	                       			   style="background-color:#EFEFEF; border:0; color: #000000" />
                        </logic:notPresent>
                        
						<a href="javascript:limparPesquisaLocalidade();document.forms[0].idLocalidade.focus();"> 
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>                   
				   </td>
				</tr>
				
				<tr>
					<td colspan="5"><hr></td>
				</tr>
				
				<tr>
					<td colspan="3"><strong>Especificações e Unidades:<font color="#FF0000">*</font></strong></td>
					
					<td align="right" colspan="2">
						<input type="button" 
							   name="Button" 
							   class="bottonRightCol"
							   value="Associar"
							   onclick="javascript:associarLocalidade();">
					</td>
				</tr>
				
				<logic:present name="colecaoLocalidadeEspecificacaoUnidade" scope="session">
					<tr>
						<td colspan="5">
							<table width="100%" cellpadding="0" cellspacing="0" border="0">
								<tr>
									<td>
										<table width="100%" bgcolor="#99CCFF" border="0">
											<tr bgcolor="#99CCFF">
												<td width="10%" align="center">
													<strong>Remover</strong>
												</td>
												<td width="50%" align="center">
													<strong>Especificação</strong>
												</td>
												<td width="40%" align="center">
													<strong>Unidade</strong>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								
								<tr>
									<td>
										<%String cor2 = "#FFFFFF";%> 
										<%--scroll --%>
										<div style="width: 100%; height: 150; overflow: auto;">
											<table width="100%" cellpadding="0" cellspacing="0" border="0">
												<tr>
													<td> <%cor2 = "#cbe5fe";%>
														<table width="100%" bgcolor="#99CCFF" border="0">
															<logic:iterate name="colecaoLocalidadeEspecificacaoUnidade" 
														   				   id="localidadeEspecificacaoUnidade"
														   				   type="LocalidadeEspecificacaoUnidade">
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
																		 onclick="remover('${count2}')"
																		 title="Remover" > 
																</td>
															
																<td width="50%">
																	<div align="left">
																		<bean:write name="localidadeEspecificacaoUnidade" property="comp_id.solicitacaoTipoEspecificacao.descricao" />
																	</div>
																</td>
															
																<td width="40%">
																	<div align="left">
																		<bean:write name="localidadeEspecificacaoUnidade" property="comp_id.unidadeOrganizacional.descricao" />
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
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td colspan="5"><hr></td>
				</tr>
				
				<tr>
					<td colspan="2">&nbsp;</td>
					<td align="left" colspan="3"><font color="#FF0000">*</font> Campo Obrigatório</td>
				</tr>
				
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
						
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
							          	
				<tr>
					<td align="left" colspan="4">
			          	<input type="button" 
			          		   class="bottonRightCol" 
			          		   value="Desfazer" 
			          		   onClick="javascript:desfazer();" />
			          		   
			          	<input type="button" 
							   name="ButtonCancelar" 
							   class="bottonRightCol" 
							   value="Cancelar"
							   onClick="javascript:document.forms[0].target='';window.location.href='/gsan/telaPrincipal.do'">
					</td>
					
					<td align="right" colspan="1">
						<input type="button" 
							   name="Button" 
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
