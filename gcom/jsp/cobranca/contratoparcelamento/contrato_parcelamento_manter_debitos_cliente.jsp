<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="java.util.Collection" isELIgnored="false"%>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="gcom.util.Util" %>
<%@ page import="gcom.faturamento.debito.DebitoACobrar"%>

		
<div style="width: 600px; height: 50%;">
	<table width="100%" align="center" border="0">
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>
	</table>
</div>
		
<div style="width: 600px; height: 100%; max-height: 300px; overflow: auto;">

<table width="100%" align="center" bgcolor="#90c7fc" border="0">

	<tr bordercolor="#79bbfd">
		<td colspan="12" align="center" bgcolor="#79bbfd">
		<strong>Contas</strong>
		</td>
	</tr>
	
	<tr bordercolor="#000000">
		<td width="4%" bgcolor="#90c7fc">
			<div align="center"><strong>Matr.</strong></div>
		</td>
		 
		<td width="9%" bgcolor="#90c7fc" align="center">
		<div class="style9"><font color="#000000" style="font-size:9px"
			face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano</strong>
		</font></div>
		</td>
		<td width="10%" bgcolor="#90c7fc" align="center">
		<div class="style9"><font color="#000000" style="font-size:9px"
			face="Verdana, Arial, Helvetica, sans-serif"> <strong>Venc.</strong>
		</font></div>
		</td>
		<td width="8%" bgcolor="#90c7fc" align="center">
		<div class="style9"><font color="#000000" style="font-size:9px"
			face="Verdana, Arial, Helvetica, sans-serif"> <strong>Val.
		&Aacute;gua </strong> </font></div>
		</td>
		<td width="8%" bgcolor="#90c7fc" align="center">
		<div class="style9"><font color="#000000" style="font-size:9px"
			face="Verdana, Arial, Helvetica, sans-serif"> <strong>Val.
		Esgoto</strong> </font></div>
		</td>
		<td width="8%" bgcolor="#90c7fc" align="center">
		<div class="style9"><font color="#000000" style="font-size:9px"
			face="Verdana, Arial, Helvetica, sans-serif"> <strong>Val.
		D&eacute;bitos</strong> </font></div>
		</td>
		<td width="10%" bgcolor="#90c7fc" align="center">
		<div class="style9"><font color="#000000" style="font-size:9px"
			face="Verdana, Arial, Helvetica, sans-serif"> <strong>Val.
		Creditos</strong> </font></div>
		</td>
		
		<td width="10%" bgcolor="#90c7fc" align="center">
		  <div class="style9">
		    <font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
		      <strong>Val. Impostos</strong> 
		    </font>
		  </div>
		</td>
		
		<td width="8%" bgcolor="#90c7fc" align="center">
		<div class="style9"><font color="#000000" style="font-size:9px"
			face="Verdana, Arial, Helvetica, sans-serif"> <strong>Val.
		Conta</strong> </font></div>
		</td>
		<td width="7%" bgcolor="#90c7fc" align="center">
		<div class="style9"><font color="#000000" style="font-size:9px"
			face="Verdana, Arial, Helvetica, sans-serif"> <strong>Acr&eacute;sc.
		Impont.</strong><strong></strong> </font></div>
		</td>
		<td width="8%" bgcolor="#90c7fc" align="center">
		<div class="style9"><font color="#000000" style="font-size:9px"
			face="Verdana, Arial, Helvetica, sans-serif"> <strong>Sit.</strong>
		</font></div>
		</td>
		<td width="8%" bgcolor="#90c7fc" align="center">
		<div class="style9"><font color="#000000" style="font-size:9px"
			face="Verdana, Arial, Helvetica, sans-serif"> <strong>Desv. Cont.</strong>
		</font></div>
		</td>
	</tr>
	
	<logic:present name="colecaoContaItens">
				<%String cor = "#cbe5fe";%>
			<logic:iterate name="colecaoContaItens"
				id="contaItem">
				<bean:define name="contaItem" property="contaGeral" id="contaGeral" />
				<bean:define name="contaGeral" property="conta" id="conta" />
				<%if (cor.equalsIgnoreCase("#cbe5fe")) {
						cor = "#FFFFFF";%>
					<tr bgcolor="#FFFFFF">
						<%} else {
						cor = "#cbe5fe";%>
					<tr bgcolor="#cbe5fe">
						<%}%>
				 <td width="4%">
			 		<c:out value="${conta.imovel.id}" />
                  </td>		
				<td align="left">
				
						<div align="center">
							<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								<a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<c:out value="${conta.id }"></c:out>&tipoConsulta=conta', 600, 800);">
								<bean:write name="conta" property="formatarAnoMesParaMesAno" /></a> 
							</font>
						</div>
				</td>	
				
				<td align="left">
					<div align="center">
						<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:write name="conta" property="dataVencimentoConta" formatKey="date.format.small" /> 
						</font>
					</div>
				</td>
				<td align="right">
					<div align="right">
						<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:write name="conta" property="valorAgua" formatKey="money.format" />
						</font>
					</div>
				</td>
				<td align="right">
					<div align="right">
						<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:write name="conta" property="valorEsgoto" formatKey="money.format" />
						</font>
					</div>
				</td>
				<td>
					<div align="right">
						<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
							<logic:notEqual name="conta" property="debitos" value="0">
								<a href="javascript:abrirPopup('exibirConsultarDebitoCobradoAction.do?contaID=<c:out value="${conta.id }"></c:out>&tipoConsulta=conta', 600, 800);">
								<font color="#000000"><bean:write name="conta" property="debitos" formatKey="money.format" /></font> </a>
							</logic:notEqual> 
							<logic:equal name="conta" property="debitos" value="0">
								<font color="#000000"><bean:write name="conta" property="debitos" formatKey="money.format" /></font>
							</logic:equal> 
						</font>
					</div>
				</td>
				<td>
					<div align="right">
						<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
							<logic:notEqual name="conta" property="valorCreditos" value="0">	
								<a href="javascript:abrirPopup('exibirConsultarCreditoRealizadoAction.do?contaID=<c:out value="${conta.id }"></c:out>&tipoConsulta=conta', 600, 800);" />
									<bean:write name="conta" property="valorCreditos" formatKey="money.format" />
								</a>
							</logic:notEqual> 
							<logic:equal name="conta" property="valorCreditos" value="0">
								<bean:write name="conta" property="valorCreditos" formatKey="money.format" />
							</logic:equal> 
						</font>
					</div>
				</td>
				<td>
					<div align="right">
						<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
							<c:if test="${conta.valorImposto == null }">0,00</c:if>
							<c:if test="${conta.valorImposto != null }"><bean:write name="conta" property="valorImposto" formatKey="money.format" /></c:if>
						</font>
					</div>
				</td>
				<td>
					<div align="right">
						<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:write name="conta" property="valorTotal" formatKey="money.format" />
						</font>
					</div>
				</td>
				<td>
					<div align="right" class="style9">
					<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
						<logic:notEqual name="contaItem" property="valorTotalContaValores" value="0">
							<a href="javascript:abrirPopup('exibirValorAtualizacaoConsultarPopupAction.do?multa=<bean:write name="contaItem" property="valorMulta" />&juros=<bean:write name="contaItem" property="valorJurosMora" />&atualizacao=<bean:write name="contaItem" property="valorAtualizacaoMonetaria" />', 300, 650);">
								<bean:write name="contaItem" property="valorTotalContaValores" formatKey="money.format" />
							</a>
						</logic:notEqual> 
						<logic:equal name="contaItem" property="valorTotalContaValores" value="0">
							<bean:write name="contaItem" property="valorTotalContaValores" formatKey="money.format" />
						</logic:equal> 
					</font>
				</div>
				</td>
				<td>
					<div align="left" title="${conta.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao}">
						<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:define name="conta" property="debitoCreditoSituacaoAtual" id="debitoCreditoSituacaoAtual" /> 
							<bean:write name="debitoCreditoSituacaoAtual" property="descricaoAbreviada" /> 
						</font>
					</div>
				</td>
				<td>
					<div align="left">
						<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
							<c:if test="${contaItem.indicadorItemCancelado == 1}">Sim</c:if>
							<c:if test="${contaItem.indicadorItemCancelado != 1}">Não</c:if>
						</font>
					</div>
				</td>
				</tr>						
			</logic:iterate>
			
			<tr bgcolor="#cbe5fe">
				<td bgcolor="#90c7fc"></td>
				<td bgcolor="#90c7fc" align="center">
				<div class="style9" align="center"><font color="#000000"
					style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong>
				</font></div>
				</td>
				<td align="left">
				<%=((Collection) session.getAttribute("colecaoContaItens")).size()%>
				&nbsp;
				doc(s)
				</td>
				<td align="right">
				<div align="right"><font color="#000000"
					style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorAgua")%>
				</font></div>
				</td>
				<td align="rigth">
				<div align="right"><font color="#000000"
					style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorEsgoto")%>
				</font></div>
				</td>
				<td align="right">
				<div align="right"><font color="#000000"
					style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorDebito")%>
				</font></div>
				</td>
				<td align="right">
				<div align="right"><font color="#000000"
					style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorCredito")%>
				</font></div>
				</td>
				
				<td align="right">
				  <div align="right">
				    <font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
				      <%=session.getAttribute("valorImposto")%>
				    </font>
				  </div>
				</td>
				
				<td align="right">
				<div align="right"><font color="#000000"
					style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorConta")%>
				</font></div>
				</td>
				<td align="right">
				<div align="right" class="style9"><font color="#000000"
					style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorAcrescimo")%>
				</font></div>
				</td>
				<td align="left" bgcolor="#90c7fc">
				<div align="left"><font color="#000000"
					style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> </font></div>
				</td>
			</tr>
	</logic:present>

</table>
</div>
		<div style="width: 600px; height: 50%;">
			<table width="100%" align="center" border="0">
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
			</table>
		</div>
		
		<div style="width: 600px; height: 100%; max-height: 300px; overflow: auto;">
			<table width="100%" align="center" bgcolor="#90c7fc" border="0">
					<tr>
						<td colspan="4">
						<table width="100%" align="center" bgcolor="#90c7fc" border="0">
							<tr bordercolor="#79bbfd">
								<td colspan="7" bgcolor="#79bbfd" align="center"><strong>D&eacute;bitos
								A Cobrar</strong></td>
							</tr>
							<tr bordercolor="#000000">
								<td width="4%" bgcolor="#90c7fc">
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Matr.
									</strong> </font></div>
								</td>
								<td width="42%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo do
								D&eacute;bito</strong> </font></div>
								</td>
								<td width="13%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano
								Refer&ecirc;ncia</strong> </font></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano
								Cobran&ccedil;a</strong> </font></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Parcelas a
								cobrar</strong> </font></div>
								</td>
								<td width="17%" bgcolor="#90c7fc" height="20">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor a
								cobrar</strong> </font></div>
								</td>
							</tr>
							<%String cor1 = "#cbe5fe";%>
							<logic:present name="colecaoItensDebitoACobrar">
								<logic:iterate name="colecaoItensDebitoACobrar" id="debitoItem">
									<bean:define name="debitoItem" property="debitoACobrarGeral" id="debitoACobrarGeral" />
									<bean:define name="debitoACobrarGeral" property="debitoACobrar" id="debitoacobrar" />
									<%if (cor1.equalsIgnoreCase("#cbe5fe")) {
					cor1 = "#FFFFFF";%>
									<tr bgcolor="#FFFFFF">
										<%} else {
					cor1 = "#cbe5fe";%>
									<tr bgcolor="#cbe5fe">
										<%}%>
	                                    <td width="4%">
										 	<div align="center"><c:out value="${debitoacobrar.imovel.id}" /> </div>
	                                    </td>
										<td>
											<div align="left" class="style9">
												<font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> 
													<logic:notEmpty name="debitoacobrar" property="imovel">
														<a href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:define name="debitoacobrar" property="imovel" id="imovel" /><bean:write name="imovel" property="id" />&debitoID=<bean:write name="debitoacobrar" property="id" />', 570, 720);">
															<bean:define name="debitoacobrar" property="debitoTipo"
																id="debitoTipo" /> 
															<logic:notEmpty name="debitoTipo" property="descricao">
																<bean:write name="debitoTipo" property="descricao" />
															</logic:notEmpty> 
														</a>
													</logic:notEmpty> 
												</font>
											</div>
										</td>
										<td>
											<div align="center" class="style9"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
												name="debitoacobrar" property="anoMesReferenciaDebito">
												<%= Util.formatarAnoMesParaMesAno(((DebitoACobrar)debitoacobrar).getAnoMesReferenciaDebito().toString()) %>
											</logic:notEmpty> </font></div>
										</td>
										<td>
											<div align="center" class="style9"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
												name="debitoacobrar" property="anoMesCobrancaDebito">
												<%= Util.formatarAnoMesParaMesAno(((DebitoACobrar)debitoacobrar).getAnoMesCobrancaDebito().toString()) %>
											</logic:notEmpty> </font></div>
										</td>
										<td>
											<div align="center" class="style9"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
												name="debitoacobrar" property="parcelasRestanteComBonus">
												<bean:write name="debitoacobrar" property="parcelasRestanteComBonus" />
											</logic:notEmpty> </font></div>
										</td>
										<td>
											<div align="right" class="style9"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
												name="debitoacobrar" property="valorTotalComBonus">
												<bean:write name="debitoacobrar" property="valorTotalComBonus"
													formatKey="money.format" />
											</logic:notEmpty> </font></div>
										</td>
									</tr>
								</logic:iterate>
								<%if (cor1.equalsIgnoreCase("#cbe5fe")) {
								cor1 = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
								cor1 = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>
								<td bgcolor="#90c7fc" colspan="2">
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong>
									</font></div>
								</td>
								<td>
									<%=((Collection) session.getAttribute("colecaoItensDebitoACobrar")).size() %>
										&nbsp;
										doc(s)
								</td>
								<td width="15%" ></td>
								<td width="15%" ></td>
								<td width="15%" >
									<div align="right" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorDebitoACobrar")%>
									</font></div>
								</td>
		
							</tr>
							</logic:present>
						</table>
						</td>
					</tr>
			</table>
		</div>
		
		<div style="width: 600px; height: 50%;">
			<table width="100%" align="center" border="0">
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
			</table>
		</div>
		
		<hr>
	<div align="left">
	
		<table>
			<tr>
				<td>
					<b>Total do Débido:</b>
				</td>
				<td>
					<font color="#000000"	style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
						<input type="text" disabled="disabled"  size="9" value="<%=session.getAttribute("valorTotalDebito")%>" />
					</font>
				</td>
			</tr>
			<tr>
				<td>
					<b>Total do Débito Atualizado:</b> 
				</td>
				<td>
					<font color="#000000"	style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
						<input type="text" disabled="disabled"  size="9" value="<%=session.getAttribute("valorTotalDebitoAtualizado")%>" />
					</font>
				</td>
			</tr>
		</table>
		
										
	</div>	
