<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarNegativadorMovimentoActionForm" dynamicJavascript="false" />
</head>


<script language="JavaScript">

  
  function abrirPopUPRegistroConta(idConta,tipoConta){     
  		abrirPopup('exibirConsultarContaAction.do?contaID='+idConta+'&'+'tipoConsulta='+tipoConta, 410, 790);
  }
  function abrirPopUPRegistroContaHistorico(idContaHistorico,tipoConta){     
  		abrirPopup('exibirConsultarContaAction.do?contaID='+idContaHistorico+'&'+'tipoConsulta='+tipoConta, 410, 790);
  }
  
  function abrirPopUPRegistroGuiaPagamento(idGuiaPagamento){  
		abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId='+idGuiaPagamento, 410, 790);
  }
  
  

</script>



<body leftmargin="5" topmargin="5" onload="resizePageSemLink(790, 410);">
<html:form action="/exibirConsultarNegativadorMovimentoRegItemPopupAction"
	name="ConsultarNegativadorMovimentoRegItemPopupActionForm"
	type="gcom.gui.cobranca.spcserasa.ConsultarNegativadorMovimentoRegItemPopupActionForm"
	method="post">
	
	<table width="750" border="0" cellspacing="5" cellpadding="0" border="0">
	<tr>
		<td width="750" valign="top" class="centercoltext" border="0">
		<table height="100%">
			<tr>
				<td></td>
			</tr>
		</table>
		
		<table width="730" border="0" cellpadding="0" cellspacing="5">
  		<tr> 
    		<td width="730" valign="top" class="centercoltext"> <table height="100%">
	        <tr> 
	          <td></td>
	        </tr>
      	</table>
      
      	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr> 
          <td width="11"><img src="imagens/parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" /></td>
          <td class="parabg">Consultar<strong> </strong>Itens do D&eacute;bito Negativado</td>
          <td width="11"><img src="imagens/parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
        </tr>
      	</table>
      	
      	<table width="100%" border="0">
        <tr> 
          <td height="364"> 
            <table width="100%" border="0" dwcopytype="CopyTableRow">
              <tr> 
                <td colspan="4">&nbsp;</td>
              </tr>
              <tr>
                <td width="25%"><strong>Negativador:</strong></td>
                <td colspan="3"><strong><b><span class="style4">
                 <html:text property="negativador" size="50" maxlength="50" readonly="true"/>
                </span></b></strong></td>
              </tr>
              <tr>
                <td colspan="4"><hr></td>
              </tr>
              <tr>
                <td><strong>Matr&iacute;cula do Im&oacute;vel:</strong></td>
                <td colspan="3"><span class="style1">
                  <input name="codigoImovel2" type="text" size="9" maxlength="9" disabled style="background-color:#EFEFEF; border:0">
                </span></td>
              </tr>
              <tr>
                <td><strong>Inscri&ccedil;&atilde;o:</strong></td>
                <td colspan="3"><input name="codigoImovel227" type="text" disabled style="background-color:#EFEFEF; border:0" value="999.999.999.9999.999" size="20" maxlength="20"></td>
              </tr>
              <tr>
                <td><strong>Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o de 
                &Aacute;gua:</strong></td>
                <td width="19%"><input name="codigoImovel2222" type="text" disabled style="background-color:#EFEFEF; border:0" size="20" maxlength="20"></td>
                <td width="26%"><strong>Situa&ccedil;&atilde;o 
                da Liga&ccedil;&atilde;o de Esgoto:</strong></td>
                <td><input name="codigoImovel22222" type="text" disabled style="background-color:#EFEFEF; border:0" size="20" maxlength="20"></td>
              </tr>
              <tr>
                <td colspan="4"><hr></td>
              </tr>
              <tr>
                <td colspan="4"><div align="center">
                  <table width="100%" border="0">
                    
                    <%double valorCorrente=0; %>
                    <%double valorAgua=0; %>
                    <%double valorEsgoto=0; %>
                    <%double valorDebitos=0; %>
                    <%double valorCreditos=0; %>
                    
                    <% String cor = "#cbe5fe";%>
						
					<tr>
						<td colspan="10">
					
						<logic:present name="collNegativadorMovimentoRegItem">
							
							<% cor = "#cbe5fe";%>
								
							<div style="width: 100%; height: 300; overflow: auto;">
							
							<table width="100%" border="0">
							
							<tr bordercolor="#90c7fc">
		                      <td colspan="10" bgcolor="#90c7fc"><strong>Contas</strong></td>
		                    </tr>
		                    <tr bordercolor="#000000">
		                      <td width="8%" rowspan="2" bgcolor="#90c7fc"><div align="center"><strong>M&ecirc;s/Ano</strong></div></td>
		                      <td width="11%" rowspan="2" bgcolor="#90c7fc"><div align="center"><strong>Vencimento</strong></div></td>
		                      <td width="10%" rowspan="2" bgcolor="#90c7fc"><div align="center"><strong>Valor 
		                        da Conta</strong></div></td>
		                      <td width="9%" rowspan="2" bgcolor="#90c7fc"><div align="center"><strong>Situa&ccedil;&atilde;o</strong></div></td>
		                      <td width="10%" rowspan="2" bgcolor="#90c7fc"><div align="center"><strong>Valor 
		                        Negativado</strong></div></td>
		                      <td colspan="2" bgcolor="#90c7fc"><div align="center"><strong>At&eacute;  Exclus&atilde;o </strong></div></td>
		                      <td colspan="2" bgcolor="#90c7fc"><div align="center"><strong>Ap&oacute;s Exclus&atilde;o </strong></div></td>
		                      <td width="12%" rowspan="2" bgcolor="#90c7fc"><div align="center"><strong>Sit.Definitiva
		                      </strong></div>
		                        <div align="center"></div>                        <div align="center"></div>                        <div align="center"></div></td>
		                      </tr>
		                    <tr bordercolor="#000000">
		                      <td width="9%" bgcolor="#90c7fc"><div align="center"><strong>Sit.Cobr.
		                      </strong></div></td>
		                      <td width="11%" bgcolor="#90c7fc"><div align="center"><strong>Dt.Sit.Cobr.</strong></div></td>
		                      <td width="9%" bgcolor="#90c7fc"><div align="center"><strong>Sit.Cobr.</strong> </div></td>
		                      <td width="11%" bgcolor="#90c7fc"><div align="center"><strong>Dt.Sit.Cobr.</strong></div></td>
		                    </tr>
							
							<logic:iterate name="collNegativadorMovimentoRegItem" id="negativadorMovimentoRegItem">
									
								<%	if (cor.equalsIgnoreCase("#FFFFFF")){
										cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
								<%} else{
										cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF">
								<%}%>
	
										<td width="8%">
											<div align="center"><strong>
																						 
												<logic:present name="negativadorMovimentoRegItem" property="contaGeral" >
													<logic:present name="negativadorMovimentoRegItem" property="contaGeral.conta" >
												 		<logic:present name="negativadorMovimentoRegItem" property="contaGeral.conta.referencia" >
												  			
												  			<a href="javascript:abrirPopUPRegistroConta('<bean:write name="negativadorMovimentoRegItem" property="contaGeral.conta.id"/>','conta');">
																<bean:write name="negativadorMovimentoRegItem" property="contaGeral.conta.referencia" />
												 			</a>
												
														</logic:present>
													</logic:present>
												</logic:present>
												
												<logic:present name="negativadorMovimentoRegItem" property="contaGeral" >
													<logic:present name="negativadorMovimentoRegItem" property="contaGeral.contaHistorico" >
														<logic:present name="negativadorMovimentoRegItem" property="contaGeral.contaHistorico.anoMesReferenciaConta" >
												 
												 			<a href="javascript:abrirPopUPRegistroContaHistorico('<bean:write name="negativadorMovimentoRegItem" property="contaGeral.contaHistorico.id"/>','contaHistorico');">
																<bean:write name="negativadorMovimentoRegItem" property="contaGeral.contaHistorico.anoMesReferenciaConta"  />
															</a>
														
														</logic:present>
													</logic:present>
												</logic:present>												 
												
												 &nbsp;</strong>
												 
											</div>
										</td>
												 
												 
												 
	                      				<td width="11%">
	                      					<div align="center">
	                      						
	                      						<logic:present name="negativadorMovimentoRegItem" property="contaGeral" >
													<logic:present name="negativadorMovimentoRegItem" property="contaGeral.conta" >
														<logic:present name="negativadorMovimentoRegItem" property="contaGeral.conta.dataVencimentoConta" >
															
															<bean:write name="negativadorMovimentoRegItem" property="contaGeral.conta.dataVencimentoConta" format="dd/MM/yyyy"/>
														
														</logic:present>
													</logic:present>
												</logic:present>
												
												<logic:present name="negativadorMovimentoRegItem" property="contaGeral" >
													<logic:present name="negativadorMovimentoRegItem" property="contaGeral.contaHistorico" >
														<logic:present name="negativadorMovimentoRegItem" property="contaGeral.contaHistorico.dataVencimentoConta" >
															
															<bean:write name="negativadorMovimentoRegItem" property="contaGeral.contaHistorico.dataVencimentoConta" format="dd/MM/yyyy" />
														</logic:present>
													</logic:present>
												</logic:present>                      						 
	                      						
	                      					</div>
	                      				</td>
	                      						
	     
	                      						
	                      				<td width="10%">
	                      					<div align="center">                						 
	                      						 
	                      						<logic:present name="negativadorMovimentoRegItem" property="contaGeral" >
													
													<logic:present name="negativadorMovimentoRegItem" property="contaGeral.conta" >											 
												 		
												 		<logic:present name="negativadorMovimentoRegItem" property="contaGeral.conta.valorAgua" >
												 
															 <%
															 valorAgua = ((gcom.cobranca.NegativadorMovimentoRegItem)negativadorMovimentoRegItem).getContaGeral().getConta().getValorAgua().doubleValue();
															 %>	
												 		
												 		</logic:present>	
												 										 
												 		<logic:present name="negativadorMovimentoRegItem" property="contaGeral.conta.valorEsgoto" >
															 
															 <%
															  valorEsgoto = ((gcom.cobranca.NegativadorMovimentoRegItem)negativadorMovimentoRegItem).getContaGeral().getConta().getValorEsgoto().doubleValue();
															 %>
												 
												 		</logic:present>
												 
														<logic:present name="negativadorMovimentoRegItem" property="contaGeral.conta.debitos" >											 
														
															<%
															 valorDebitos = ((gcom.cobranca.NegativadorMovimentoRegItem)negativadorMovimentoRegItem).getContaGeral().getConta().getDebitos().doubleValue();
															%>	
														
														</logic:present>	
														 				 
														<logic:present name="negativadorMovimentoRegItem" property="contaGeral.conta.valorCreditos" >
														
															<%
															 valorCreditos = ((gcom.cobranca.NegativadorMovimentoRegItem)negativadorMovimentoRegItem).getContaGeral().getConta().getValorCreditos().doubleValue();
															%>	
														 
														</logic:present>
												
													</logic:present>
												
												</logic:present>							
												
												
												<logic:present name="negativadorMovimentoRegItem" property="contaGeral" >
												
													<logic:present name="negativadorMovimentoRegItem" property="contaGeral.contaHistorico" >											 
												 
														<logic:present name="negativadorMovimentoRegItem" property="contaGeral.contaHistorico.valorAgua" >
														
															<%
															valorAgua = ((gcom.cobranca.NegativadorMovimentoRegItem)negativadorMovimentoRegItem).getContaGeral().getContaHistorico().getValorAgua().doubleValue();
															%>	
														
														</logic:present>	
														 										 
														<logic:present name="negativadorMovimentoRegItem" property="contaGeral.contaHistorico.valorEsgoto" >
														
															 <%
															 valorEsgoto = ((gcom.cobranca.NegativadorMovimentoRegItem)negativadorMovimentoRegItem).getContaGeral().getContaHistorico().getValorEsgoto().doubleValue();
															 %>
														
														</logic:present>
														 
														<logic:present name="negativadorMovimentoRegItem" property="contaGeral.contaHistorico.debitos" >											 
														
															 <%
															 valorDebitos = ((gcom.cobranca.NegativadorMovimentoRegItem)negativadorMovimentoRegItem).getContaGeral().getContaHistorico().getValorDebitos().doubleValue();
															 %>	
														
														</logic:present>	
														 				 
														<logic:present name="negativadorMovimentoRegItem" property="contaGeral.contaHistorico.valorCreditos" >
														
															 <%
															 valorCreditos = ((gcom.cobranca.NegativadorMovimentoRegItem)negativadorMovimentoRegItem).getContaGeral().getContaHistorico().getValorCreditos().doubleValue();
															 %>	
														
														</logic:present>
									
													</logic:present>
												
												</logic:present>
												
	
												<%
												 valorCorrente = valorAgua + valorEsgoto + valorDebitos - valorCreditos;
												%>
												
												<%=gcom.util.Util.formataBigDecimal(new java.math.BigDecimal(valorCorrente),2,false) %>
					       					
					       					</div>
					       					
					       				</td>
	                      						 
	                      						 
	                      				<td width="9%">
	                      					<div align="center">
	                      					
	                      						<logic:present name="negativadorMovimentoRegItem" property="debitoACobrarGeral" >
													<logic:present name="negativadorMovimentoRegItem" property="debitoACobrarGeral.debitoACobrar" >
														<logic:present name="negativadorMovimentoRegItem" property="debitoACobrarGeral.debitoACobrar.debitoCreditoSituacaoAtual" >											
															
															<bean:write name="negativadorMovimentoRegItem" property="debitoACobrarGeral.debitoACobrar.debitoCreditoSituacaoAtual.descricaoAbreviada" />
												
														</logic:present>
													</logic:present>
												</logic:present> 
												
												<logic:present name="negativadorMovimentoRegItem" property="debitoACobrarGeral" >
													<logic:present name="negativadorMovimentoRegItem" property="debitoACobrarGeral.debitoACobrarHistorico" >
														<logic:present name="negativadorMovimentoRegItem" property="debitoACobrarGeral.debitoACobrarHistorico.debitoCreditoSituacaoAtual" >											
															<bean:write name="negativadorMovimentoRegItem" property="debitoACobrarGeral.debitoACobrarHistorico.debitoCreditoSituacaoAtual.descricaoAbreviada" />
														</logic:present>
													</logic:present>   
												</logic:present>                    						 
	                      						
	                      					</div>
	                      				</td>    
	                      						 
	                      						 
	                      						 
	                      						                   						
	                      				<td width="10%">
	                      					<div align="center">                      						
												<bean:write name="negativadorMovimentoRegItem" property="valorDebito"/>
											</div>
										</td>
											     
											     
											     
											     
	                      				<td width="12%">
	                      					<div align="center"> 
	                      					
	                      						<logic:present name="negativadorMovimentoRegItem" property="cobrancaDebitoSituacao" >
													<logic:present name="negativadorMovimentoRegItem" property="cobrancaDebitoSituacao.descricao" >											
													
														<bean:write name="negativadorMovimentoRegItem" property="cobrancaDebitoSituacao.descricao"/>
												
													</logic:present>
												</logic:present>								
												
											</div>
										</td>
												
													
	                      				<td>
	                      					<div align="center">
	                      						
	                      						<logic:present name="negativadorMovimentoRegItem" property="dataSituacaoDebito" >                      						 
	                      							<bean:write name="negativadorMovimentoRegItem" property="dataSituacaoDebito" format="dd/MM/yyyy"/>
	                      						</logic:present>                      					
	                      					</div>
	                      				</td>
	                      						
	                      						
	                      						
	                      				<td>
	                      					<div align="center">
	                      					
	                      						<logic:present name="negativadorMovimentoRegItem" property="cobrancaDebitoSituacaoAposExclusao" >
													<logic:present name="negativadorMovimentoRegItem" property="cobrancaDebitoSituacaoAposExclusao.descricao" >
														<bean:write name="negativadorMovimentoRegItem" property="cobrancaDebitoSituacaoAposExclusao.descricao" />
													</logic:present>									
												</logic:present>
											</div>
										</td>
												 
												 
										<td>
	                      					<div align="center">
	                      						
	                      						<logic:present name="negativadorMovimentoRegItem" property="dataSituacaoDebitoAposExclusao" >                      						 
	                   								<bean:write name="negativadorMovimentoRegItem" property="dataSituacaoDebitoAposExclusao" format="dd/MM/yyyy"/>
	                      						</logic:present>                      					
	                      					</div>
	                      				</td>
												 
												 
	                      				<td>
	                      					<div align="center">
	                      					
	                      						<logic:present name="negativadorMovimentoRegItem" property="indicadorSituacaoDefinitiva" >
	                      							
	                      							<logic:equal  name="negativadorMovimentoRegItem"  property="indicadorSituacaoDefinitiva"  value="1">				
	                      								SIM
	                  								</logic:equal>
	                  						
	                  								<logic:equal  name="negativadorMovimentoRegItem"  property="indicadorSituacaoDefinitiva"  value="2">				
	                									NÃO
	                  								</logic:equal> 
	                  							 
	                  							 </logic:present>                  						                        						
												
											</div>
										</td>                    						 
	                    								
												
									</tr>
												
												
							</logic:iterate>
							
							</table>
							</div>
							
						</logic:present>
                 
                		</div>
                		</td>
              		</tr>
                	</table>
                  
        		</td>
              </tr>
              
              <tr>
                <td colspan="4"><hr></td>
              </tr>
              
              
              <!-- ######################################################################################################################### -->
              
              <tr>
                <td colspan="4"><div align="center">
                  <table width="100%" border="0">
                    
                    <% cor = "#cbe5fe";%>
						
					<tr>
						<td colspan="10">
					
						<logic:present name="collNegativadorMovimentoRegItem2">
							
							<% cor = "#cbe5fe";%>
								
							<div style="width: 100%; height: 300; overflow: auto;">
							
							<table width="100%" border="0">
		                  	<tr bordercolor="#90c7fc">
		                    	<td colspan="11" bgcolor="#90c7fc"><strong>Guias de Pagamento</strong></td>
		                  	</tr>
		                  	<tr bordercolor="#000000">
		                    	<td width="26%" rowspan="2" bgcolor="#90c7fc"><div align="center"><strong>Tipo 
		                      	do D&eacute;bito</strong></div></td>
		                    	<td width="13%" rowspan="2" bgcolor="#90c7fc"><div align="center"><strong>Data 
		                      	de Emiss&atilde;o</strong></div></td>
		                    	<td width="12%" rowspan="2" bgcolor="#90c7fc"><div align="center"><strong>Data 
		                      	de Vencimento</strong></div></td>
		                    	<td width="14%" rowspan="2" bgcolor="#90c7fc"><div align="center"><strong>Valor 
		                      	da Guia de Pagamento</strong></div></td>
		                     	<td width="14%" rowspan="2" bgcolor="#90c7fc"><div align="center"><strong>Situação
		                     	</strong></div></td>
		                    	<td width="17%" rowspan="2" bgcolor="#90c7fc"><div align="center"><strong>Valor 
		                      	Negativado</strong></div></td>
		                    	<td width="18%" colspan="2" bgcolor="#90c7fc"><div align="center"><strong>At&eacute;  Exclus&atilde;o </strong></div></td>
		                    	<td width="35%" colspan="2" bgcolor="#90c7fc"><div align="center"><strong>Ap&oacute;s Exclus&atilde;o </strong></div></td>
		                   		<td width="35%" rowspan="2" bgcolor="#90c7fc"><div align="center"><strong>Sit.Definitiva</strong></div></td>
		                  	</tr>
		                  	<tr bordercolor="#000000">
		                    	<td bgcolor="#90c7fc"><div align="center"><strong>Sit.Cobr. </strong></div></td>
		                    	<td bgcolor="#90c7fc"><div align="center"><strong>Dt.Sit.Cobr.</strong></div></td>
		                    	<td bgcolor="#90c7fc"><div align="center"><strong>Sit.Cobr.</strong> </div></td>
		                    	<td bgcolor="#90c7fc"><div align="center"><strong>Dt.Sit.Cobr.</strong></div></td>
		                  	</tr>
							
							<logic:iterate name="collNegativadorMovimentoRegItem2" id="negativadorMovimentoRegItem">
									
								<%	if (cor.equalsIgnoreCase("#FFFFFF")){
										cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
								<%} else{
										cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF">
								<%}%>
	
										<td>
											<div align="center"><strong>
																			 
											 <logic:present name="negativadorMovimentoRegItem" property="debitoACobrarGeral" >
											 <logic:present name="negativadorMovimentoRegItem" property="debitoACobrarGeral.debitoACobrar" >
											 <logic:present name="negativadorMovimentoRegItem" property="debitoACobrarGeral.debitoACobrar.debitoTipo" >
											  <a href="javascript:abrirPopUPRegistroGuiaPagamento('<bean:write name="negativadorMovimentoRegItem" property="guiaPagamentoGeral.guiaPagamento.id"/>');
												">	
												<bean:write name="negativadorMovimentoRegItem" property="debitoACobrarGeral.debitoACobrar.debitoTipo.descricaoAbreviada" />
												 </a>
											</logic:present>
											</logic:present>
											</logic:present>
											
											<logic:present name="negativadorMovimentoRegItem" property="debitoACobrarGeral" >
											 <logic:present name="negativadorMovimentoRegItem" property="debitoACobrarGeral.debitoACobrarHistorico" >
											 <logic:present name="negativadorMovimentoRegItem" property="debitoACobrarGeral.debitoACobrarHistorico.debitoTipo" >
											  <a href="javascript:abrirPopUPRegistroGuiaPagamento('<bean:write name="negativadorMovimentoRegItem" property="guiaPagamentoGeral.guiaPagamentoHistorico.id"/>');
												">	
												<bean:write name="negativadorMovimentoRegItem" property="debitoACobrarGeral.debitoACobrarHistorico.debitoTipo.descricaoAbreviada"  />
											  </a>
											</logic:present>
											</logic:present>
											</logic:present>												 
											 </strong></div>
											 </td>
											 
											 
											 
			
                   							 <td>
                   							 <div align="center">
                   							 <logic:present name="negativadorMovimentoRegItem" property="guiaPagamentoGeral" >
											 <logic:present name="negativadorMovimentoRegItem" property="guiaPagamentoGeral.guiaPagamento" >
											 <logic:present name="negativadorMovimentoRegItem" property="guiaPagamentoGeral.guiaPagamento.dataEmissao" >
												<bean:write name="negativadorMovimentoRegItem" property="guiaPagamentoGeral.guiaPagamento.dataEmissao" format="dd/MM/yyyy" />
											</logic:present>
											</logic:present>
											</logic:present>
											
											<logic:present name="negativadorMovimentoRegItem" property="guiaPagamentoGeral" >
											 <logic:present name="negativadorMovimentoRegItem" property="guiaPagamentoGeral.guiaPagamentoHistorico" >
											 <logic:present name="negativadorMovimentoRegItem" property="guiaPagamentoGeral.guiaPagamentoHistorico.dataEmissao" >
												<bean:write name="negativadorMovimentoRegItem" property="guiaPagamentoGeral.guiaPagamentoHistorico.dataEmissao" format="dd/MM/yyyy" />
											</logic:present>
											</logic:present>
											</logic:present>	
                   							</div>
                   							</td>
                   							
                   							
                   							
                   							
                   							
                   							 <td>
                   							 <div align="center">                   							 
                   							 <logic:present name="negativadorMovimentoRegItem" property="guiaPagamentoGeral" >
											 <logic:present name="negativadorMovimentoRegItem" property="guiaPagamentoGeral.guiaPagamento" >
											 <logic:present name="negativadorMovimentoRegItem" property="guiaPagamentoGeral.guiaPagamento.dataVencimento" >
												<bean:write name="negativadorMovimentoRegItem" property="guiaPagamentoGeral.guiaPagamento.dataVencimento" format="dd/MM/yyyy"/>
											</logic:present>
											</logic:present>
											</logic:present>
											
											<logic:present name="negativadorMovimentoRegItem" property="guiaPagamentoGeral" >
											 <logic:present name="negativadorMovimentoRegItem" property="guiaPagamentoGeral.guiaPagamentoHistorico" >
											 <logic:present name="negativadorMovimentoRegItem" property="guiaPagamentoGeral.guiaPagamentoHistorico.dataVencimento" >
												<bean:write name="negativadorMovimentoRegItem" property="guiaPagamentoGeral.guiaPagamentoHistorico.dataVencimento" format="dd/MM/yyyy" />
											</logic:present>
											</logic:present>
											</logic:present>               							 
                   							 
                   							 </div>
                   							 </td>
                   							 
                   							 
                   							 
                   							 <td>
                   							 <div align="center">
                   							 
                   							  <logic:present name="negativadorMovimentoRegItem" property="guiaPagamentoGeral" >
											 <logic:present name="negativadorMovimentoRegItem" property="guiaPagamentoGeral.guiaPagamento" >
											 <logic:present name="negativadorMovimentoRegItem" property="guiaPagamentoGeral.guiaPagamento.valorDebito" >
												<bean:write name="negativadorMovimentoRegItem" property="guiaPagamentoGeral.guiaPagamento.valorDebito"/>
											</logic:present>
											</logic:present>
											</logic:present>
											
											<logic:present name="negativadorMovimentoRegItem" property="guiaPagamentoGeral" >
											 <logic:present name="negativadorMovimentoRegItem" property="guiaPagamentoGeral.guiaPagamentoHistorico" >
											 <logic:present name="negativadorMovimentoRegItem" property="guiaPagamentoGeral.guiaPagamentoHistorico.valorDebito" >
												<bean:write name="negativadorMovimentoRegItem" property="guiaPagamentoGeral.guiaPagamentoHistorico.valorDebito" />
											</logic:present>
											</logic:present>
											</logic:present>            							 
                   							 
                   							 </div>
                   							 </td>
                   							 
                   							 
                   							 
                   							 <td>
                   							 <div align="center">                   							 
                   							 <logic:present name="negativadorMovimentoRegItem" property="guiaPagamentoGeral" >
											 <logic:present name="negativadorMovimentoRegItem" property="guiaPagamentoGeral.guiaPagamento" >
											 <logic:present name="negativadorMovimentoRegItem" property="guiaPagamentoGeral.guiaPagamento.debitoCreditoSituacaoAtual" >
												<bean:write name="negativadorMovimentoRegItem" property="guiaPagamentoGeral.guiaPagamento.debitoCreditoSituacaoAtual.descricaoAbreviada"/>
											</logic:present>
											</logic:present>
											</logic:present>
											
											<logic:present name="negativadorMovimentoRegItem" property="guiaPagamentoGeral" >
											 <logic:present name="negativadorMovimentoRegItem" property="guiaPagamentoGeral.guiaPagamentoHistorico" >
											 <logic:present name="negativadorMovimentoRegItem" property="guiaPagamentoGeral.guiaPagamentoHistorico.debitoCreditoSituacaoAtual" >
												<bean:write name="negativadorMovimentoRegItem" property="guiaPagamentoGeral.guiaPagamentoHistorico.debitoCreditoSituacaoAtual.descricaoAbreviada" />
											</logic:present>
											</logic:present>
											</logic:present>
											</div>
											</td>
                   							 
                   							 
                   							 
                    						 <td>
                    						 <div align="center">                    						 
                    						 <bean:write name="negativadorMovimentoRegItem" property="valorDebito"/>                    						 
                    						 </div>
                    						 </td>
                    						 
                    						 
                    						 
                   							 <td>
                   							 <div align="center">
                   							  <logic:present name="negativadorMovimentoRegItem" property="cobrancaDebitoSituacao" >   
                   							   <logic:present name="negativadorMovimentoRegItem" property="cobrancaDebitoSituacao.descricao" >                          						 
                      						        <bean:write name="negativadorMovimentoRegItem" property="cobrancaDebitoSituacao.descricao"/>
                      						 </logic:present> 
                      						  </logic:present>  
                   							 </div>
                   							 </td>
                   							 
                   							 
                   							 
                   							 
                   							 
                    						<td>
                    						<div align="center"> 
                    						  <logic:present name="negativadorMovimentoRegItem" property="dataSituacaoDebito" >                      						 
                      						  <bean:write name="negativadorMovimentoRegItem" property="dataSituacaoDebito" format="dd/MM/yyyy"/>
                      						 </logic:present>  </div>
                      						 </td>
                      						 
                      						 
                      						 
                   							 <td>
                   							 <div align="center">
											 <logic:present name="negativadorMovimentoRegItem" property="cobrancaDebitoSituacaoAposExclusao" >   
                   							   <logic:present name="negativadorMovimentoRegItem" property="cobrancaDebitoSituacaoAposExclusao.descricao" >                          						 
                      						        <bean:write name="negativadorMovimentoRegItem" property="cobrancaDebitoSituacaoAposExclusao.descricao"/>
                      						 </logic:present> 
                      						  </logic:present> 
                      						  </div>
                      						  </td>
                      						 
                      						 
                    						 <td><div align="center">	 
                    						 <logic:present name="negativadorMovimentoRegItem" property="dataSituacaoDebitoAposExclusao" >                      						 
                   								<bean:write name="negativadorMovimentoRegItem" property="dataSituacaoDebitoAposExclusao" format="dd/MM/yyyy"/>
                      						 </logic:present>  
                      						  </div></td>
             
											 
											  
                      						  <td><div align="center">
                      						   <logic:present name="negativadorMovimentoRegItem" property="indicadorSituacaoDefinitiva" >
                      						    <logic:equal  name="negativadorMovimentoRegItem"  property="indicadorSituacaoDefinitiva"  value="1">				
                      								SIM
                  								</logic:equal>
                  						
                  								
                  								<logic:equal  name="negativadorMovimentoRegItem"  property="indicadorSituacaoDefinitiva"  value="2">				
                									NÃO
                  								</logic:equal> 
                  							 </logic:present>                  						                        						
											
										     </div></td>               						 
	                    								
												
									</tr>
												
												
							</logic:iterate>
							
							</table>
							</div>
							
						</logic:present>
                 
                		</div>
                		</td>
              		</tr>
                	</table>
                  
        		</td>
              </tr>
              
              <!-- ######################################################################################################################### -->
              
              <tr>
                <td colspan="4"><hr></td>
              </tr>
              <tr> 
                <td height="26" colspan="3">&nbsp;</td>
                <td width="30%" colspan="-1"><div align="right">
                  <input name="button" type="button"
						class="bottonRightCol" value="Fechar"
						onclick="window.close();"
						align="left">
                </div></td>
              </tr>
              <!-- <tr>
          <td height="15">&nbsp;</td>
          <td>&nbsp;</td>
        </tr> -->
            </table>
          </td>
        </tr>
      </table>
    <p>&nbsp;</p></td>
  </tr>
</table>
		
	
		
		
      </td>
	</tr>

</table>



</body>
</html:form>
</html:html>
