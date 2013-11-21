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

</head>

	<logic:present name="ehPopup">
		<body leftmargin="0" topmargin="0" onload="javascript:resizePageSemLink(600, 400);">
	</logic:present>

	<logic:notPresent name="ehPopup">
		<body leftmargin="5" topmargin="5">
	</logic:notPresent>



<html:form action="/exibirConsultarDadosEquipeAction.do"
	name="ConsultarDadosEquipeActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.ConsultarDadosEquipeActionForm"
	method="post">

	<logic:notPresent name="ehPopup">

		<%@ include file="/jsp/util/cabecalho.jsp"%>
		<%@ include file="/jsp/util/menu.jsp"%>

		<table width="770" border="0" cellspacing="5" cellpadding="0">
			<tr>	

			<td width="115" valign="top" class="leftcoltext">

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
			<td valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table>
				<tr>
					<td></td>
				</tr>
			</table>

      		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        		<tr> 
          			<td width="11">
          				<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
          			</td>
          			<td class="parabg">Consultar Dados da Equipe</td>
          			<td width="11">
          				<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
          			</td>
        		</tr>
      		</table>
			
		</logic:notPresent>

		<logic:present name="ehPopup">
				
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
			          			<td class="parabg">Consultar Dados da Equipe</td>
			          			<td width="11">
			          				<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
			          			</td>
			        		</tr>
			      		</table>
			
			      		<p>&nbsp;</p>
		</logic:present>	      		
	
	      		<table width="100%" border="0">
        			<tr> 
          				<td align="center">

           					<table width="100%" border="0" bgcolor="#cbe5fe">

	    						<tr bgcolor="#99CCFF">

                     				<td colspan="2">
                     				<div align="center">
               							<b>Dados Gerais da Equipe</b>
                   					</div>
                     				</td>

                    			</tr>

								<tr>
									<td>
										<strong>C&oacute;digo da Equipe:</strong>
									</td>
									<td>
										<html:text property="idEquipe" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;" 
											size="5"
											maxlength="5" />
									</td>
								</tr>
	
								<tr>
									<td>
										<strong>Nome da Equipe:</strong>
									</td>
									<td>
										<html:text property="nomeEquipe" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;" 
											size="40"
											maxlength="40" />
									</td>
								</tr>
						
	           					<tr> 
	             					<td>
	             						<strong>Placa do Ve&iacute;culo :</strong>
	             					</td>
	             					<td>
										<html:text property="placaVeiculo" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;" 
											size="8"
											maxlength="8" />
	                 				</td>
	           					</tr>
	
	           					<tr> 
	               					<td>
	               						<strong>Carga de Trabalho Dia (hora):</strong>
	               					</td>
	               					<td>
										<html:text property="cargaTrabalhoDia" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;" 
											size="2"
											maxlength="2" />
	                   				</td>
	          					</tr>
	                           	<tr> 
	                           		<td><strong>Unidade Organizacional:</strong></td>
	                           		<td>
										<html:text property="unidadeOrganizacionalId" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;" 
											size="4"
											maxlength="4" />
	                             		
										<html:text property="unidadeOrganizacionalDescricao" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;" 
											size="40"
											maxlength="40" />
	                               	</td>
	                           	</tr>
	
	                           	<tr> 
	                           		<td><strong>Tipo Perfil Servi&ccedil;o:</strong></td>
	                           		<td>
										<html:text property="tipoPerfilServicoId" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;" 
											size="4"
											maxlength="4" />
	                             		
										<html:text property="tipoPerfilServicoDescricao" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;" 
											size="40"
											maxlength="40" />
	                               	</td>
	                           	</tr>
                    			
                    			<tr> 
                      				<td height="10" colspan="2">
                      					<hr> <span class="style2"><strong>
                        				</strong></span> 
                        			</td>
                    			</tr>
                    			
                    			<tr>
	                      			<td colspan="2">
	                      				<strong>Componentes da Equipe</strong> 
	                      			</td>
                    			</tr>
	                           	
			                    <tr> 
			                   		<td class="style3" colspan="2">
			                   		
			                   		<table width="100%" align="center" bgcolor="#99CCFF">
			                        	
			                        	<!--corpo da segunda tabela-->
			                          	<tr bordercolor="#FFFFFF" bgcolor="#79BBFD"> 
			                            	<td width="15%">
			                            		<div align="center"><strong>Respons&aacute;vel</strong></div>
			                            	</td>
			                            	<td width="16%">
			                            		<div align="center"><strong>Funcion&aacute;rio</strong></div>
			                            	</td>
			                            	<td width="58%">
			                            		<div align="center"><strong>Nome do Componente</strong></div>
			                            	</td>
			                          	</tr>

										<logic:present name="AcompanharRoteiroProgramacaoOrdemServicoActionForm" 
											property="equipeComponentes">
					                    
						                    <c:set var="count" value="0"/>
	
	  			                      		<logic:iterate id="equipeComponente"
	  			                      			name="ConsultarDadosEquipeActionForm" 
	  			                      			property="equipeComponentes" 
	  			                      			type="gcom.atendimentopublico.ordemservico.EquipeComponentes">
	  			                      			
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
						                        		<c:choose>
						                        			<c:when test="${equipeComponente.indicadorResponsavel == '1'}">
						                        				SIM
						                        			</c:when>
						                        			<c:otherwise>
						                        				N&Atilde;O
						                        			</c:otherwise>
						                        		</c:choose>
						                        	</div>
						                        </td>
	
						                        <td bordercolor="#90c7fc">
						                        	<div align="center">
						                        		<c:if test="${equipeComponente.funcionario != null}">
						                        			<bean:write name="equipeComponente" property="funcionario.id" />
						                        		</c:if>
						                        	</div>
						                        </td>
	
						                        <td bordercolor="#90c7fc">
						                        	<div align="center">
						                        		<c:choose>
						                        			<c:when test="${equipeComponente.funcionario == null}">
						                        				<bean:write name="equipeComponente" property="componentes" />
						                        			</c:when>
						                        			<c:otherwise>
						                        				<bean:write name="equipeComponente" property="funcionario.nome" />	
						                        			</c:otherwise>
						                        		</c:choose>
						                        	</div>
						                        </td>
	
						                    </logic:iterate>	
			                          	</logic:present>
		                          	</table>
		                          	</td>
	                          	</tr>
							</table>
						</td>
					</tr>

	        		<tr> 
	          			<td height="27" colspan="5"> 

							<logic:notPresent name="ehPopup">
		          				<div align="left"> 
		              				<input name="Button2" 
		              					type="button" 
		              					class="bottonRightCol" 
		              					value="Voltar" 
		              					onClick="javascript:history.back();">
		            			</div>
							</logic:notPresent>

							<logic:present name="ehPopup">
		          				<div align="left"> 
		              				<input name="Button2" 
		              					type="button" 
		              					class="bottonRightCol" 
		              					value="Fechar" 
		              					onClick="javascript:window.close();">
		            			</div>
							</logic:present>
							

	            		</td>
	        		</tr>
	      		</table>
	      		<p>&nbsp;</p>
	      	</td>
	  	</tr>
	</table>

	<logic:notPresent name="ehPopup">
		<%@ include file="/jsp/util/rodape.jsp"%>
	</logic:notPresent>
	
</html:form>
</html:html>