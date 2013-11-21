<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="java.math.BigDecimal" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">


<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">
	
	function extendeTabela(tabela,display){
		var form = document.forms[0];

		if(display){
 			eval('layerHide'+tabela).style.display = 'none';
 			eval('layerShow'+tabela).style.display = 'block';
		}else{
			eval('layerHide'+tabela).style.display = 'block';
 			eval('layerShow'+tabela).style.display = 'none';
		}
	}
	
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/consultaNegativacaoWizardAction" 
	name="InformarDadosConsultaNegativacaoActionForm"
	type="gcom.gui.cobranca.spcserasa.InformarDadosConsultaNegativacaoActionForm"
	method="post">
	
		<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_consulta.jsp?numeroPagina=2" />
	
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

				<table height="100%" border="0">
					<tr>
						<td></td>
					</tr>
				</table>
	
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
						</td>
						<td class="parabg">Consultar Resumo da Negativação</td>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
			
				<p>&nbsp;</p>

				<table width="100%" border="0">
                  <tr>
				    <td>
				      <strong>Negativador:</strong>
                    </td>
				    <td>
				      <html:text property="nomeNegativador" size="45" readonly="true" disabled="true" 
				                 style="background-color:#EFEFEF; border:0;"/>
			        </td>	
                  </tr>	
                  <tr>
                    <td><strong>Período do envio da Negativação:</strong></td>
                    <td>
                      <html:text property="periodoEnvioNegativacaoInicio" size="8" readonly="true" disabled="true"
                                 style="background-color:#EFEFEF; border:0; " />
                      <strong>a</strong>
                      <html:text property="periodoEnvioNegativacaoFim" size="8" readonly="true" disabled="true"
                                 style="background-color:#EFEFEF; border:0; " />
                    </td>
                  </tr>		
                  
                  <tr>
                    <td><strong>Informações Atualizadas em:</strong></td>
                    <td>
                      <html:text property="ultimaAtualizacao" size="20" readonly="true" disabled="true" style="background-color:#EFEFEF; border:0; " />                    
                    </td>
                  </tr>	                  
                  
                  <tr> 
                    <td colspan="2">
                      <div align="center">
                        <hr>
                      </div>
                    </td>
                  </tr>
                </table>
                <table width="100%" cellspacing="0" border="0">
				  <tr width="100%">
				    <td width="100%">
				  	  <div id='layerHide1' style="display:block">
           		   	    <table width="100%" cellspacing="0" bgcolor="#79bbfd">
	    		  	   	  <tr bgcolor="#79bbfd">
                      	    <td>             
                      	   	  <a href="javascript:extendeTabela('1',true);"/>
                      	   	  	<strong>
                      	   	      NEGATIVAÇÕES INCLUÍDAS
                      	   	    </strong> 
                      	      </a>
                      	    </td>
                       	  </tr>
                       	</table>
                      </div>
         			  <div id='layerShow1' style="display:none">
					    <table width="100%" bgcolor="#79bbfd" border="0">						
					   	  <tr bgcolor="#79bbfd">
					   	    <td bgcolor="#79bbfd">
					   	      <a href="javascript:extendeTabela('1',false);"/>
					   	        <strong>
					              NEGATIVAÇÕES INCLUÍDAS
					   	        </strong> 
					   	      </a>
					   	    </td>
					      </tr>
					      <tr>
					        <td>
					          <table width="100%" bgcolor="#90c7fc" border="0"> 
					   	        
					   	          <tr bgcolor="#cbe5fe">
					   	            <td bgcolor="#cbe5fe" rowspan="2" width="32%">
					   	            </td>
					   	            <td bgcolor="#cbe5fe" align="right"><strong> Quantidade</strong> </td>
					   	            <td bgcolor="#cbe5fe" align="right"><strong> Percentual</strong> </td>
					   		        <td bgcolor="#cbe5fe" align="right"><strong> Valor     </strong> </td>
					   		        <td bgcolor="#cbe5fe" align="right"><strong> Percentual</strong> </td>
					   		      </tr>
					   	          <tr>
					   		        <td bgcolor="#FFFFFF" width="17%" align="right">
					   			   	  <bean:write scope="session"  
					   			   	              name="totalQuantidadeInclusoesNeg"
					   			   	              formatKey="number.format"/>
					   		        </td>
					   		        <td bgcolor="#FFFFFF" width="17%" align="right">
					   			   	  100,00
					   		        </td>
					   		        <td bgcolor="#FFFFFF" width="17%" align="right">
					   			      <bean:write scope="session"  
					   			                  name="valorTotalInclusoesNeg"
					   			                  formatKey="money.format"/>
					   		        </td>
					   		        <td bgcolor="#FFFFFF" width="17%" align="right">
					   			   	  100,00
					   		        </td>										    
					   	          </tr>		
 				   	            
				   	   	      </table>
				  	        </td>
				          </tr>
					   	  <tr bgcolor="#79bbfd">
					   	    <td bgcolor="#79bbfd" align="center">
					   	      <strong>
					            Situação do Débito da Negativação
					   	      </strong> 
					   	    </td>
					      </tr>
					      <tr>
					        <td>
					          <table width="100%" bgcolor="#90c7fc" border="0"> 
				                <logic:iterate name="colecaoResumoNegativacao" id="resumoNegativacao">
						          <tr>
					   	            <td rowspan="2" bgcolor="#cbe5fe" width="32%">
					   		          <a href="javascript:abrirPopup('exibirConsultarResumoNegativacaoPopupAction.do?idSituacaoCobranca=<bean:write name="resumoNegativacao" property="idSituacaoCobranca"/>&stNegociacao=1&valorTotal=<bean:write name="resumoNegativacao" property="somatorioValorDebito"/>&percentualValor=<bean:write name="resumoNegativacao" property="percentualValor"/>&descricao=<bean:write name="resumoNegativacao"property="descricao"/>&valorPendente=<bean:write name="resumoNegativacao"property="somatorioValorPendente"/>&valorPago=<bean:write name="resumoNegativacao"property="somatorioValorPago"/>&valorParcelado=<bean:write name="resumoNegativacao"property="somatorioValorParcelado"/>&valorCancelado=<bean:write name="resumoNegativacao"property="somatorioValorCancelado"/>', 400, 600);">
					   			        <strong><bean:write name="resumoNegativacao"property="descricao"/></strong>
					   		          </a>
					   	            </td>
					   	          </tr>
					   	          <tr>
					   		        <td bgcolor="#FFFFFF" width="17%" align="right">
					   		          <bean:write name="resumoNegativacao"
					   		                      property="somatorioQuantidadeInclusoes" formatKey="number.format"/>
					   		        </td>
					   		        <!-- [UC0676 - 4.2 ABA 2 - 4.2.2.7.3 ] -->
					   		        <td bgcolor="#FFFFFF" width="17%" align="right">
					   		          <bean:write name="resumoNegativacao"
					   		                      property="percentualQtd" formatKey="money.format"/>
					   		        </td>
					   		        <td bgcolor="#FFFFFF" width="17%" align="right">
					   		             <bean:write name="resumoNegativacao"
					   	                             property="somatorioValorDebito" formatKey="money.format"/>
					   		        </td>
					   		        <td bgcolor="#FFFFFF" width="17%" align="right">
					   	              <bean:write name="resumoNegativacao"
					   	                          property="percentualValor" formatKey="money.format"/>
					   		        </td>										    
					   	          </tr>											
				                </logic:iterate>  
				             </table> 
				           </td>
				         </tr>
				        </table>
				      </div>
				    </td>  
                  </tr>  
                  <tr>
                    <td>  
				  	  <div id='layerHide2' style="display:block">
           		   	    <table width="100%" cellspacing="0" bgcolor="#79bbfd">
	    		  	   	  <tr bgcolor="#79bbfd">
                      	    <td>             
                      	   	  <a href="javascript:extendeTabela('2',true);"/>
                      	   	  	<strong>
                      	   	      NEGATIVAÇÕES INCLUÍDAS E CONFIRMADAS
                      	   	    </strong> 
                      	      </a>
                      	    </td>
                       	  </tr>
                       	</table>
                      </div>
         			  <div id='layerShow2' style="display:none">
					    <table width="100%" bgcolor="#79bbfd" border="0">						
					   	  <tr bgcolor="#79bbfd">
					   	    <td bgcolor="#79bbfd">
					   	      <a href="javascript:extendeTabela('2',false);"/>
					   	        <strong>
					              NEGATIVAÇÕES INCLUÍDAS E CONFIRMADAS
					   	        </strong> 
					   	      </a>
					   	    </td>
					      </tr>
					      <tr>
					        <td>
					          <table width="100%" bgcolor="#90c7fc" border="0"> 
					   	          <tr bgcolor="#cbe5fe">
					   	            <td bgcolor="#cbe5fe" rowspan="2" width="32%">
					   	            </td>
					   	            <td bgcolor="#cbe5fe" align="right"><strong> Quantidade</strong> </td>
					   	            <td bgcolor="#cbe5fe" align="right"><strong> Percentual</strong> </td>
					   		        <td bgcolor="#cbe5fe" align="right"><strong> Valor     </strong> </td>
					   		        <td bgcolor="#cbe5fe" align="right"><strong> Percentual</strong> </td>
					   		      </tr>
					   	          <tr>
					   		        <td bgcolor="#FFFFFF" width="17%" align="right">
					   			   	  <bean:write scope="session"  
					   			   	              name="totalQuantidadeInclusoesConfirmadas"
					   			   	              formatKey="number.format"/>
					   		        </td>
					   		        <td bgcolor="#FFFFFF" width="17%" align="right">
					   			   	  100,00
					   		        </td>
					   		        <td bgcolor="#FFFFFF" width="17%" align="right">
					   			      <bean:write scope="session"  
					   			                  name="valorTotalInclusoesConfirmadas"
					   			                  formatKey="money.format"/>
					   		        </td>
					   		        <td bgcolor="#FFFFFF" width="17%" align="right">
					   			   	  100,00
					   		        </td>										    
					   	          </tr>		
				   	   	      </table>
				  	        </td>
				          </tr>
					   	  <tr bgcolor="#79bbfd">
					   	    <td bgcolor="#79bbfd" align="center">
					   	      <strong>
					            Situação do Débito da Negativação
					   	      </strong> 
					   	    </td>
					      </tr>
					      <tr>
					        <td>
					          <table width="100%" bgcolor="#90c7fc" border="0"> 
				                <logic:iterate name="colecaoResumoNegativacaoIncluidasConfirmadas" id="resumoNegativacaoConfirmadas">
						          <tr>
					   	            <td rowspan="2" bgcolor="#cbe5fe" width="32%">
					   		          <a href="javascript:abrirPopup('exibirConsultarResumoNegativacaoPopupAction.do?idSituacaoCobranca=<bean:write name="resumoNegativacaoConfirmadas" property="idSituacaoCobranca"/>&stNegociacao=2&valorTotal=<bean:write name="resumoNegativacaoConfirmadas" property="somatorioValorDebito"/>&percentualValor=<bean:write name="resumoNegativacaoConfirmadas" property="percentualValor"/>&descricao=<bean:write name="resumoNegativacaoConfirmadas"property="descricao"/>&valorPendente=<bean:write name="resumoNegativacaoConfirmadas"property="somatorioValorPendente"/>&valorPago=<bean:write name="resumoNegativacaoConfirmadas"property="somatorioValorPago"/>&valorParcelado=<bean:write name="resumoNegativacaoConfirmadas"property="somatorioValorParcelado"/>&valorCancelado=<bean:write name="resumoNegativacaoConfirmadas"property="somatorioValorCancelado"/>', 400, 600);">
					   			        <strong><bean:write name="resumoNegativacaoConfirmadas"property="descricao"/></strong>
					   		          </a>
					   	            </td>
					   	          </tr>
					   	          <tr>
					   		        <td bgcolor="#FFFFFF" width="17%" align="right">
					   		          <bean:write name="resumoNegativacaoConfirmadas"
					   		                      property="somatorioQuantidadeInclusoes" formatKey="number.format"/>
					   		        </td>
					   		        <!-- [UC0676 - 4.2 ABA 2 - 4.2.2.7.3 ] -->
					   		        <td bgcolor="#FFFFFF" width="17%" align="right">
					   		          <bean:write name="resumoNegativacaoConfirmadas"
					   		                      property="percentualQtd" formatKey="money.format"/>
					   		        </td>
					   		        <td bgcolor="#FFFFFF" width="17%" align="right">
					   		             <bean:write name="resumoNegativacaoConfirmadas"
					   	                             property="somatorioValorDebito" formatKey="money.format"/>
					   		        </td>
					   		        <td bgcolor="#FFFFFF" width="17%" align="right">
					   	              <bean:write name="resumoNegativacaoConfirmadas"
					   	                          property="percentualValor" formatKey="money.format"/>
					   		        </td>										    
					   	          </tr>											
				                </logic:iterate>  
				              </table>
				            </td>
				          </tr> 
				        </table>
				      </div>
				    </td>  
                  </tr>    
                  <tr>
                    <td>
				  	  <div id='layerHide3' style="display:block">
           		   	    <table width="100%" cellspacing="0" bgcolor="#79bbfd">
	    		  	   	  <tr bgcolor="#79bbfd">
                      	    <td>             
                      	   	  <a href="javascript:extendeTabela('3',true);"/>
                      	   	  	<strong>
                      	   	      NEGATIVAÇÕES INCLUÍDAS E NÃO CONFIRMADAS
                      	   	    </strong> 
                      	      </a>
                      	    </td>
                       	  </tr>
                       	</table>
                      </div>                      
         			  <div id='layerShow3' style="display:none">
					    <table width="100%" bgcolor="#79bbfd" border="0">						
					   	  <tr bgcolor="#79bbfd">
					   	    <td bgcolor="#79bbfd">
					   	      <a href="javascript:extendeTabela('3',false);"/>
					   	        <strong>
					              NEGATIVAÇÕES INCLUÍDAS E NÃO CONFIRMADAS
					   	        </strong> 
					   	      </a>
					   	    </td>
					      </tr>
					      <tr>
					        <td>
					          <table width="100%" bgcolor="#90c7fc" border="0"> 
					   	          <tr bgcolor="#cbe5fe">
					   	            <td bgcolor="#cbe5fe" rowspan="2" width="32%">
					   	            </td>
					   	            <td bgcolor="#cbe5fe" align="right"><strong> Quantidade</strong> </td>
					   	            <td bgcolor="#cbe5fe" align="right"><strong> Percentual</strong> </td>
					   		        <td bgcolor="#cbe5fe" align="right"><strong> Valor     </strong> </td>
					   		        <td bgcolor="#cbe5fe" align="right"><strong> Percentual</strong> </td>
					   		      </tr>
					   	          <tr>
					   		        <td bgcolor="#FFFFFF" width="17%" align="right">
					   			   	  <bean:write scope="session"  
					   			   	              name="totalQuantidadeInclusoesNaoConfirmadas"
					   			   	              formatKey="number.format"/>
					   		        </td>
					   		        <td bgcolor="#FFFFFF" width="17%" align="right">
					   			   	  100,00
					   		        </td>
					   		        <td bgcolor="#FFFFFF" width="17%" align="right">
					   			      <bean:write scope="session"  
					   			                  name="valorTotalInclusoesNaoConfirmadas"
					   			                  formatKey="money.format"/>
					   		        </td>
					   		        <td bgcolor="#FFFFFF" width="17%" align="right">
					   			   	  100,00
					   		        </td>										    
					   	          </tr>		
				   	   	      </table>
				  	        </td>
				          </tr>
					   	  <tr bgcolor="#79bbfd">
					   	    <td bgcolor="#79bbfd" align="center">
					   	      <strong>
					            Situação do Débito da Negativação
					   	      </strong> 
					   	    </td>
					      </tr>
					      <tr>
					        <td>
					          <table width="100%" bgcolor="#90c7fc" border="0"> 
				                <logic:iterate name="colecaoResumoNegativacaoIncluidasNaoConfirmadas" id="resumoNegativacaoNaoConfirmadas">
						          <tr>
					   	            <td rowspan="2" bgcolor="#cbe5fe" width="32%">
					   		          <a href="javascript:abrirPopup('exibirConsultarResumoNegativacaoPopupAction.do?idSituacaoCobranca=<bean:write name="resumoNegativacaoNaoConfirmadas" property="idSituacaoCobranca"/>&stNegociacao=3&valorTotal=<bean:write name="resumoNegativacaoNaoConfirmadas" property="somatorioValorDebito"/>&percentualValor=<bean:write name="resumoNegativacaoNaoConfirmadas" property="percentualValor"/>&descricao=<bean:write name="resumoNegativacaoNaoConfirmadas"property="descricao"/>&valorPendente=<bean:write name="resumoNegativacaoNaoConfirmadas"property="somatorioValorPendente"/>&valorPago=<bean:write name="resumoNegativacaoNaoConfirmadas"property="somatorioValorPago"/>&valorParcelado=<bean:write name="resumoNegativacaoNaoConfirmadas"property="somatorioValorParcelado"/>&valorCancelado=<bean:write name="resumoNegativacaoNaoConfirmadas"property="somatorioValorCancelado"/>', 400, 600);">
					   			        <strong><bean:write name="resumoNegativacaoNaoConfirmadas"property="descricao"/></strong>
					   		          </a>
					   	            </td>
					   	          </tr>
					   	          <tr>
					   		        <td bgcolor="#FFFFFF" width="17%" align="right">
					   		          <bean:write name="resumoNegativacaoNaoConfirmadas"
					   		                      property="somatorioQuantidadeInclusoes" formatKey="number.format"/>
					   		        </td>
					   		        <!-- [UC0676 - 4.2 ABA 2 - 4.2.2.7.3 ] -->
					   		        <td bgcolor="#FFFFFF" width="17%" align="right">
					   		          <bean:write name="resumoNegativacaoNaoConfirmadas"
					   		                      property="percentualQtd" formatKey="money.format"/>
					   		        </td>
					   		        <td bgcolor="#FFFFFF" width="17%" align="right">
					   		             <bean:write name="resumoNegativacaoNaoConfirmadas"
					   	                             property="somatorioValorDebito" formatKey="money.format"/>
					   		        </td>
					   		        <td bgcolor="#FFFFFF" width="17%" align="right">
					   	              <bean:write name="resumoNegativacaoNaoConfirmadas"
					   	                          property="percentualValor" formatKey="money.format"/>
					   		        </td>										    
					   	          </tr>											
				                </logic:iterate>  
				              </table> 
				            </td>
				          </tr>
				        </table>
				      </div>
			        </td>
			      </tr>					
			    </table>
			  <table width="100%" border="0">
			    <tr>
			  	  <td colspan="2">
			   	    <div align="right"><jsp:include
			   		     page="/jsp/util/wizard/navegacao_botoes_wizard_consulta.jsp?numeroPagina=2" /></div>
			   	  </td>
			    </tr>	
	          </table>
	        </td>
		  </tr>												
	    </table>
	    <%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>