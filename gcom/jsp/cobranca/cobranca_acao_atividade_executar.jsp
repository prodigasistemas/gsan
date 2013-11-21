<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.cobranca.CobrancaAcaoAtividadeCronograma" isELIgnored="false"%>
<%@ page import="gcom.cobranca.CobrancaAcaoAtividadeComando" isELIgnored="false"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>

<script languagem="JavaScript">
<!--

function validarForm(){
	
	var mensagem = 'Nenhuma atividade de cobrança selecionada';

	if (CheckboxNaoVazioMensagemGenerico(mensagem,document.forms[0].idsAtividadesCobrancaCronograma) && CheckboxNaoVazioMensagemGenerico(mensagem,document.forms[0].idsAtividadesCobrancaEventuais)){
	  submeterFormPadrao(document.forms[0]);
	}
}


//-->
</script>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/executarAtividadeAcaoCobrancaAction" method="post">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="132" valign="top" class="leftcoltext">
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
          <td class="parabg">Executar Atividade de Ação de Cobrança</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <!-- =============================INICIO ATIVIDADES DE COBRANÇA DO CRONOGRAMA====================================================== -->                  
      <table width="100%" border="0">
        <tr>
      	  <td colspan="3">
      	    <table width="98%" border="0">
	  		  <tr> 
          		<td height="17" colspan="3"><strong>Atividades de ação de cobrança do cronograma comandadas para execução :</strong></td>
          		<td align="right"></td>
      		  </tr>
      		
      		  <tr> 
          		<td colspan="7">
		   		  <table width="100%" cellpadding="0" cellspacing="0">
					<tr> 
                	  <td> 
                	  
						<table width="100%" bgcolor="#99CCFF">												
						  <tr bordercolor="#FFFFFF" bgcolor="#99CCFF"> 

                            <td width="9%" rowspan="2" bordercolor="#000000" bgcolor="#90c7fc">
                              <div align="center"><strong>Todas</strong></div>
                            </td>

                            <td width="9%" rowspan="2" bordercolor="#000000" bgcolor="#90c7fc">
                              <div align="center"><strong>Grupo</strong></div>
                            </td>

						    <td width="12%" rowspan="2" bordercolor="#000000" bgcolor="#90c7fc">
                              <div align="center"><strong>Mês/Ano</strong></div>
                            </td>
                            
                            <td width="20%" rowspan="2" bordercolor="#000000" bgcolor="#90c7fc">
                              <div align="center"><strong>Ação</strong></div>
                            </td>
                            
                            <td width="12%" rowspan="2" bordercolor="#000000" bgcolor="#90c7fc">
                              <div align="center"><strong>Atividade</strong></div>
                            </td>
                            
                            <td width="12%" rowspan="2" bordercolor="#000000" bgcolor="#90c7fc">
                              <div align="center"><strong>Data Prevista</strong></div>
                            </td>
                            
                            <td height="19" colspan="2" bordercolor="#000000" bgcolor="#90c7fc">
                              <div align="center"><strong>Comando</strong></div>
                            </td>
                            
                          </tr>
                          
                          <tr bordercolor="#FFFFFF" bgcolor="#cbe5fe"> 
                          
                            <td width="10%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
                              <div align="center"><strong>Data</strong></div>
                            </td>
                            
                            <td width="10%" bordercolor="#cbe5fe" bgcolor="#cbe5fe"><div align="center"></div>
                              <div align="center"><strong>Hora</strong></div>
                            </td>
                            
			              </tr>     
			              
			              </table>
			              
			              <div style="width: 100%; height: 100; overflow: auto;">
			              
				              <table width="100%" bgcolor="#99CCFF">
					              <%--Esquema de paginação para atividades de cobrança do cronograma--%>
						
								  <% String cor = "#cbe5fe";%>
					                            
		      				      <logic:present name="colecaoAtividadesCobrancaCronograma">
							      <logic:iterate name="colecaoAtividadesCobrancaCronograma" id="cobrancaAcaoAtividadeCronograma" type="CobrancaAcaoAtividadeCronograma">      				      		
							      
							      <% if (cor.equalsIgnoreCase("#FFFFFF")){
									 cor = "#cbe5fe";%>
								     <tr bgcolor="#cbe5fe">
								  <%} else{
									 cor = "#FFFFFF";%>
									  <tr bgcolor="#FFFFFF">
								  <%}%>
					                                   		              			              
		                            <td width="9%" align="center">
		                          	  <div align="center">
		                          	    <input type="checkbox" name="idsAtividadesCobrancaCronograma" value="<bean:write name="cobrancaAcaoAtividadeCronograma" property="id"/>">
									  </div>
									</td>
		
									<td width="9%">
									  <div align="center">
									    <bean:write name="cobrancaAcaoAtividadeCronograma" property="cobrancaAcaoCronograma.cobrancaGrupoCronogramaMes.cobrancaGrupo.descricaoAbreviada"/>
									  </div>
									</td>
									
		 						    <td width="9%" align="center">
									  <span style="color: #000000;"><%="" + Util.formatarMesAnoReferencia(cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaGrupoCronogramaMes().getAnoMesReferencia())%></span>
									</td>
		
									<td width="20%">
									  <div align="center">
									    <bean:write name="cobrancaAcaoAtividadeCronograma" property="cobrancaAcaoCronograma.cobrancaAcao.descricaoCobrancaAcao"/>
									  </div>
									</td>
		
									<td width="10%">
									  <div align="center">
									    <bean:write name="cobrancaAcaoAtividadeCronograma" property="cobrancaAtividade.descricaoCobrancaAtividade"/>
									  </div>
									</td>
									
									<td width="12%">
									  <div align="center">						
										<logic:present name="cobrancaAcaoAtividadeCronograma" property="dataPrevista">
										  <bean:write name="cobrancaAcaoAtividadeCronograma" property="dataPrevista" formatKey="date.format"/>
										</logic:present>
										<logic:notPresent name="cobrancaAcaoAtividadeCronograma" property="dataPrevista">
										  &nbsp;
										</logic:notPresent>											
									  </div>
									</td>
													  							  							  							  
									<td width="12%">
									  <div align="center">						
										<logic:present name="cobrancaAcaoAtividadeCronograma" property="comando">
										  <bean:write name="cobrancaAcaoAtividadeCronograma" property="comando" formatKey="date.format"/>
										</logic:present>
										<logic:notPresent name="cobrancaAcaoAtividadeCronograma" property="comando">
										&nbsp;
										</logic:notPresent>											
									  </div>
									</td>				  					  							  							  
									
									<td width="12%">
									  <div align="center">						
										<logic:present name="cobrancaAcaoAtividadeCronograma" property="comando">
										  <bean:write name="cobrancaAcaoAtividadeCronograma" property="comando" formatKey="hour.format"/>
										</logic:present>
										<logic:notPresent name="cobrancaAcaoAtividadeCronograma" property="comando">
										  &nbsp;
										</logic:notPresent>											
									  </div>
									</td>			
									
								  </tr>
								  
								  </logic:iterate>
		      					  </logic:present>      		        					              			              			              			              			              
		      					  <%--Fim do esquema de paginação para atividades de cobrança do cronograma--%>
	                    	</table>
                    	</div>
					  </td>
            	    </tr>		
				  </table>
				</td>
			  </tr>
			</table>	
      	  </td>
        </tr>             
      </table>
      <!-- =============================FIM ATIVIDADES DE COBRANÇA DO CRONOGRAMA========================================================= -->            
      
	  <!-- =============================INICIO DAS ATIVIDADES DE COBRANÇA EVENTUAIS====================================================== -->      
      <table width="100%" border="0">
        <tr>
      	  <td colspan="3">
      	    <table width="98%" border="0">
	  		  <tr> 
          		<td height="17" colspan="3"><strong>Atividades de ação de cobrança eventuais comandadas para execução:</strong></td>
          		<td align="right"></td>
      		  </tr>
      		
      		  <tr> 
          		<td colspan="7">
		   		  <table width="100%" cellpadding="0" cellspacing="0">
					<tr> 
                	  <td> 
						<table width="100%" bgcolor="#99CCFF">												
						  <tr bordercolor="#FFFFFF" bgcolor="#99CCFF"> 

                            <td width="9%" rowspan="2" bordercolor="#000000" bgcolor="#90c7fc">
                              <div align="center"><strong>Todas</strong></div>
                            </td>

						    <td width="12%" rowspan="2" bordercolor="#000000" bgcolor="#90c7fc">
                              <div align="center"><strong>Mês/Ano</strong></div>
                            </td>
                            
                            <td width="9%" rowspan="2" bordercolor="#000000" bgcolor="#90c7fc">
                              <div align="center"><strong>Ação</strong></div>
                            </td>
                            
                            <td width="12%" rowspan="2" bordercolor="#000000" bgcolor="#90c7fc">
                              <div align="center"><strong>Atividade</strong></div>
                            </td>
                            
                            <td height="19" colspan="2" bordercolor="#000000" bgcolor="#90c7fc">
                              <div align="center"><strong>Comando</strong></div>
                            </td>
                            
                            <td width="12%" rowspan="2" bordercolor="#000000" bgcolor="#90c7fc">
                              <div align="center"><strong>Usuário</strong></div>
                            </td>
                            
                          </tr>
                          
                          <tr bordercolor="#FFFFFF" bgcolor="#cbe5fe"> 
                          
                            <td width="10%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
                              <div align="center"><strong>Data</strong></div>
                            </td>
                            
                            <td width="10%" bordercolor="#cbe5fe" bgcolor="#cbe5fe"><div align="center"></div>
                              <div align="center"><strong>Hora</strong></div>
                            </td>
                            
			              </tr>       
			              
			              </table>
			              
			              <div style="width: 100%; height: 100; overflow: auto;">
			              
				              <table width="100%" bgcolor="#99CCFF">
					              <%--Esquema de paginação para atividades de cobrança eventuais--%>
						
								  <% String cor1 = "#FFFFFF";%>
					                            
					              <logic:present name="colecaoAtividadesCobrancaEventuais">
							      <logic:iterate name="colecaoAtividadesCobrancaEventuais" id="cobrancaAcaoAtividadeComando" type="CobrancaAcaoAtividadeComando">      				      		
							      
							      <% if (cor1.equalsIgnoreCase("#FFFFFF")){
									 cor1 = "#cbe5fe";%>
								     <tr bgcolor="#cbe5fe">
								  <%} else{
									 cor1 = "#FFFFFF";%>
									  <tr bgcolor="#FFFFFF">
								  <%}%>
		            		      							                     							  							  
		                            <td width="9%" align="center">
		                          	  <div align="center">
		                          	    <input type="checkbox" name="idsAtividadesCobrancaEventuais" value="<bean:write name="cobrancaAcaoAtividadeComando" property="id"/>">
									  </div>
									</td>
		
		 						    <td width="9%" align="center">
									  <span style="color: #000000;"><%="" + Util.formatarMesAnoReferencia((Integer)request.getAttribute("anoMesCicloCobranca"))%></span>
									</td>
		
									<td width="10%">
									  <div align="center">
									    <bean:write name="cobrancaAcaoAtividadeComando" property="cobrancaAcao.descricaoCobrancaAcao"/>
									  </div>
									</td>
		
									<td width="10%">
									  <div align="center">
									    <bean:write name="cobrancaAcaoAtividadeComando" property="cobrancaAtividade.descricaoCobrancaAtividade"/>
									  </div>
									</td>
									
									<td width="12%">
									  <div align="center">						
										<logic:present name="cobrancaAcaoAtividadeComando" property="comando">
										  <bean:write name="cobrancaAcaoAtividadeComando" property="comando" formatKey="date.format"/>
										</logic:present>
										  <logic:notPresent name="cobrancaAcaoAtividadeComando" property="comando">
										&nbsp;
										</logic:notPresent>											
									  </div>
									</td>				  					  							  							  
									
									<td width="12%">
									  <div align="center">						
										<logic:present name="cobrancaAcaoAtividadeComando" property="comando">
										  <bean:write name="cobrancaAcaoAtividadeComando" property="comando" formatKey="hour.format"/>
										</logic:present>
										<logic:notPresent name="cobrancaAcaoAtividadeComando" property="comando">
										  &nbsp;
										</logic:notPresent>											
									  </div>
									</td>
									
									<td width="12%">
									  <div align="center">						
										<logic:present name="cobrancaAcaoAtividadeComando" property="usuario">
										  <bean:write name="cobrancaAcaoAtividadeComando" property="usuario.login"/>
										</logic:present>
										<logic:notPresent name="cobrancaAcaoAtividadeComando" property="usuario">
										  &nbsp;
										</logic:notPresent>											
									  </div>
									</td>
									
								  </tr>
								  </logic:iterate>
		      					  </logic:present>      
		      					  <%--Fim do esquema de paginação para atividades de cobrança eventuais--%>	        					              			              			              			              			              
	                    	</table>
                    	</div>
					  </td>
            	    </tr>		
				  </table>
				</td>
			  </tr>
			</table>	
      	  </td>
        </tr>             
      </table>
      <!-- =============================FIM DAS ATIVIDADES DE COBRANÇA EVENTUAIS========================================================= -->      
      <div align="right">
        <table>
          <tr>
            <td> 
              <input type="button" onclick="window.location.href='/gsan/telaPrincipal.do'" class="bottonRightCol" value="Cancelar" style="width: 70px;">&nbsp;
              <%-- <input type="button" onclick="validarForm();" class="bottonRightCol" value="Executar" style="width: 70px;"> --%>
              <gsan:controleAcessoBotao name="botaoFiltrar" value="Executar" onclick="validarForm();" url="executarAtividadeAcaoCobrancaAction.do"/>
		    </td>
          </tr>
        </table>
      </div>
      <p>&nbsp;</p>
    </td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>

</body>
</html:html>