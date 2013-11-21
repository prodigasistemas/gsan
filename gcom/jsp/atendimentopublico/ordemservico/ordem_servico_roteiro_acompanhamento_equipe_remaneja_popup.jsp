<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%-- Carrega javascripts da biblioteca --%>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<%-- Novos Javascripts --%>

<script language="JavaScript">
   	function limpar() {

		var form = document.forms[0];
		
		desabilitaEquipePrincipal();
   	}
   	
   	function desabilitaEquipePrincipal(){
		var form = document.forms[0];
	    var selecionados = form.elements['idEquipePrincipal'];
		
		for (i=0; i< selecionados.length; i++) {
			selecionados[i].checked = false;
		}
   	}
   	
   	function concluir(actionTipo) {
		var form = document.forms[0];
		var principal = selecionouEquipePrincipal();

		if(principal != false){
			if (actionTipo == 'true'){
				form.action='acompanhamentoArquivosRoteiroRemanejarOrdemServicoAction.do';
			    form.submit();
			}else{	    	
				var lista = new Array();
		    	lista[0] = principal;
		    	lista[1] = form.idEquipeAtual.value;
		    	lista[2] = form.idOrdemServico.value;
		
				window.opener.recuperarDadosPopup('', lista, 'remanejaEquipe');
				window.close();
			}
		}
   	}
   	
	function selecionouEquipePrincipal(){

		var form = document.forms[0];
	    var selecionados = form.elements['idEquipePrincipal'];
		var retorno = false;
		
		for (i=0; i< selecionados.length; i++) {
			if(selecionados[i].checked){
				retorno = selecionados[i].value;
			}
		}

		if(retorno == false){
			alert('É necessário selecionar a equipe para o remanejamento da ordem de serviço');
		}
		
		return retorno;
	}
   	
</script>
</head>

<logic:notPresent name="fecharPopup">
	<body leftmargin="0" topmargin="0" onload="window.focus();resizePageSemLink(600, 400);">
</logic:notPresent>

<logic:present name="fecharPopup">
	<body leftmargin="0" topmargin="0"
		onload="javascript:chamarReload('selecionarAcompanhamentoArquivosRoteiroAction.do');window.close();">
</logic:present>

<html:form action="/exibirAcompanharRoteiroProgramacaoOrdemServicoAction.do"
	name="AcompanharRoteiroProgramacaoOrdemServicoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.AcompanharRoteiroProgramacaoOrdemServicoActionForm"
	method="post">
	
	<table width="570" border="0" cellpadding="0" cellspacing="5">
		<tr> 
	    	<td width="560" valign="top" class="centercoltext">
	    		<table height="100%">
	        		<tr> 
	          			<td></td>
	        		</tr>
	      		</table>
	      		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	        		<tr> 
	          			<td width="11">
	          				<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
	          			</td>
	          			<td class="parabg">Remanejar Ordem de Servi&ccedil;o</td>
	          			<td width="11">
	          				<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
	          			</td>
	        		</tr>
	      		</table>
	      		<p>&nbsp;</p>
	      		<table width="100%" border="0">

			        <tr> 
			          <td colspan="3">Marque a equipe para remanejar a ordem de servi&ccedil;o:</td>
			        </tr>

					<tr>
						<td>
							<strong> Ordem de Servi&ccedil;o:</strong>
						</td>

						<td colspan="2">
							<strong> 
								<html:text property="idOrdemServico" 
									readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									size="9"
									maxlength="9" />

								<html:text property="descricaoOrdemServico" 
									readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									size="50"
									maxlength="50" />

							</strong>
						</td>
					</tr>

			        <tr>
			        	<td>
			        		<strong>Equipe Atual:</strong>
			        	</td>
			          	
			          	<td colspan="2">
			          		<strong> 
								<html:text property="idEquipeAtual" 
									readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									size="9"
									maxlength="9" />

								<html:text property="nomeEquipeAtual" 
									readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									size="50"
									maxlength="50" />
							</strong>			          		
						</td>
			        </tr>
					
			        <tr>
		          		<td height="24" colspan="3">
		          		<table width="100%" align="center" bgcolor="#99CCFF">

			            	<!--corpo da segunda tabela-->
			              	<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
                				<td width="8%"><div align="center"><strong><u>Nova Equipe</u></strong></div></td>
			                	<td width="10%"><div align="center"><strong>Equipe</strong></div></td>
			                	<td width="28%"><div align="center"><strong>Nome da Equipe</strong></div></td>
			                	<td width="19%"><div align="center"><strong>Placa do Ve&iacute;culo</strong></div></td>
			                	<td width="17%"><div align="center"><strong>Unidade</strong></div></td>
			                	<td width="18%"><div align="center"><strong>Tipo Perfil</strong></div></td>
			              	</tr>

							<logic:present name="AcompanharRoteiroProgramacaoOrdemServicoActionForm" 
								property="equipes"
								scope="session">
					                    
			                    <c:set var="count" value="0"/>
	
	                      		<logic:iterate id="equipe"
	                      			name="AcompanharRoteiroProgramacaoOrdemServicoActionForm" 
	                      			property="equipes" 
	                      			type="gcom.atendimentopublico.ordemservico.Equipe" 
	                      			scope="session">

									<bean:define id="idEquipe" 
										name="equipe" 
										property="id"
										type="Integer"/>
	  			                      			
	                      			<c:set var="count" value="${count+1}"/>
						                        	
		                        	<c:choose>
		                        		<c:when test="${count%2 == '1'}">
		                        			<tr bgcolor="#FFFFFF">
		                        		</c:when>
		                        		<c:otherwise>
		                        			<tr bgcolor="#cbe5fe">
		                        		</c:otherwise>
		                        	</c:choose>
					                        	
                           			<td bordercolor="#90c7fc">
                           				<div align="center">
											<html:radio property="idEquipePrincipal" value="<%=idEquipe.toString()%>"/>
                               			</div>
                               		</td>

                           			<td bordercolor="#90c7fc">
                           				<div align="center">
                        					<bean:write name="equipe" property="id" />
                        				</div>
                               		</td>

                           			<td bordercolor="#90c7fc">
                           				<div align="center">
                        					<bean:write name="equipe" property="nome" />
                        				</div>
                               		</td>

                           			<td bordercolor="#90c7fc">
                           				<div align="center">
                        					<bean:write name="equipe" property="placaVeiculo" />
                        				</div>
                               		</td>

	
			                        <td bordercolor="#90c7fc">
			                        	<div align="center">
			                        		<c:if test="${equipe.unidadeOrganizacional != null}">
			                        			<bean:write name="equipe" property="unidadeOrganizacional.id" />
			                        		</c:if>
			                        	</div>
			                        </td>
	
			                        <td bordercolor="#90c7fc">
			                        	<div align="center">
			                        		<c:if test="${equipe.servicoPerfilTipo != null}">
			                        			<bean:write name="equipe" property="servicoPerfilTipo.id" />
			                        		</c:if>
			                        	</div>
			                        </td>
	
			                    </logic:iterate>	
                          	</logic:present>
		              	</table>
		              	</td>
	              	</tr>

			        <tr> 
			        	<td height="27" colspan="2"> 
			        		<div align="left">

	                        	<input name="ButtonFechar" 
	                        		type="button" 
	                        		class="bottonRightCol" 
	                        		value="Fechar"
	                        		onClick="javascript:window.close();">

	              				<input name="Button2" 
	              					type="button" 
	              					class="bottonRightCol" 
	              					value="Limpar" 
	              					onClick="javascript:limpar();">
			            	</div>
			            </td>
			          	
			          	<td height="27" colspan="5">
	          				<div align="right">
	          					<%if(request.getParameter("veioAcompanhamentoRoteiro") != null && request.getParameter("veioAcompanhamentoRoteiro").equals("true")){%>
		              				<input name="Button2" 
	              					type="button" 
	              					class="bottonRightCol" 
	              					value="Concluir" 
	              					onClick="javascript:concluir('true');">
		              			<%}else{%>
		              				<input name="Button2" 
	              					type="button" 
	              					class="bottonRightCol" 
	              					value="Concluir" 
	              					onClick="javascript:concluir('false');">
		              			<%}%>
			            	</div>
			            </td>
			        </tr>
	      		</table>
	      		<p>&nbsp;</p>
	      	</td>
	  	</tr>
	</table>
</html:form>
</body>
</html:html>