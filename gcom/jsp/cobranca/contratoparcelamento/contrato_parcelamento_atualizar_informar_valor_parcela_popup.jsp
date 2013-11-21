<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="gcom.cobranca.contratoparcelamento.PrestacaoContratoParcelamento" %>

<div id="popupContact">
			<a id="popupContactClose"><img src="imagens/Error.gif" style="border: 0px; cursor: pointer;" alt="" width="14" height="14"></a>
				
 <script type="text/javascript">
 function verificaVirgula(event, campo){
    	var char = null;
    		if (event.which == null){
				     char= String.fromCharCode(event.keyCode);    // IE
				}else if (event.which != 0 && event.charCode != 0){
				     char= String.fromCharCode(event.which);
				 }   
				 
				if(char != ','){
					return isCampoNumerico(event);
				}else{
					var valorDaParcelaAdd = document.getElementById("valorDaParcelaAdd").value;
						if(valorDaParcelaAdd.indexOf(",") != -1){
							return isCampoNumerico(event);
					}
				}
			
    }
 
 function clicouInformar(valorParcelTotal,totalParcelasPopUp){
	 
	 var form = document.forms[0];
	 var valorContrato = document.getElementById("valorParceladoPopUp").value;

	 if(parseFloat(valorContrato) != parseFloat(valorParcelTotal)){
		 alert("O valor total das parcelas informadas " +valorParcelTotal+ " não corresponde ao valor parcelado do contrato " + valorContrato+ ". Faça o ajuste necessário para que o valor total das parcelas informadas corresponda ao valor parcelado do contrato.");
	 }else{
		 var numeroParcelasPopUp = document.getElementById("numeroParcelasPopUp");

		if(totalParcelasPopUp < numeroParcelasPopUp.value){
			 alert("O número de parcelas informados " + numeroParcelasPopUp.value + " não corresponde a quantidade de parcelas adicionas " + totalParcelasPopUp);
		}else{
			 var form = document.forms[0];
			 form.action = "exibirAtualizarContratoParcelamentoClienteAction.do?method=informarParcela";
		     document.forms[0].submit();
		}
		 
	 }
	 
 }
 
function clicouVoltar(){
	disablePopup();
 }
 
 function adicionarParcela(){
	 var form = document.forms[0];
	 var numeroParcelasPopUp = document.getElementById("numeroParcelasPopUp");
	  
	  if(numeroParcelasPopUp.value != null && numeroParcelasPopUp.value != ""){

		 var parcelaInicial = document.getElementById("parcelaInicial");
		 var parcelaFinal = document.getElementById("parcelaFinal");
		 var valorParcela = document.getElementById("valorDaParcelaAdd");
		 
		 if(parcelaInicial.value != null && parcelaInicial.value != ""){
			 
			 if(valorParcela.value != null && valorParcela.value != ""){
	      		if(parcelaFinal == null || parcelaFinal.value == ""){
	      			parcelaFinal = parcelaInicial;
	      		}
	      		
	      		if(parseInt(parcelaFinal.value) < parseInt(parcelaInicial.value)){
	      			alert("Parcela Final do Intervalo é anterior à Parcela Inicial do Intervalo. Informe outro valor para a Parcela Final.");
	      		}else{
		      		var form = document.forms[0];
					form.action = "exibirAtualizarContratoParcelamentoClienteAction.do?method=inserirParcela&parcelaInicial=" + parcelaInicial.value +"&parcelaFinal="+ parcelaFinal.value +"&valorParcela="+valorParcela.value+"&numeroParcelasPopUp="+numeroParcelasPopUp.value;
		            document.forms[0].submit();
	      		}
	      		
			 }else{
				 alert("Informe o valor da parcela.");
			 }
		 }else{
			 alert("Informe a parcela inicial.");
		 }
	  }else{
		  alert("Informe o número de parcelas");
	  }
 }
 </script>
 <script type="text/javascript">
 function clicouCancelar(){
	 var form = document.forms[0];
	 form.action = "exibirAtualizarContratoParcelamentoClienteAction.do?method=cancelarParcela";
     document.forms[0].submit();
 }
 
 function removerParcela(parcelIni,parcelFim, grupoRemover){
	 var form = document.forms[0];
		form.action = "exibirAtualizarContratoParcelamentoClienteAction.do?method=removerParcela&parcelaInicial=" + parcelIni+"&parcelaFinal="+parcelFim +"&grupoValoresRemover="+grupoRemover;
     document.forms[0].submit();
 }
 </script>

	
	<input type="hidden" name="controleCliente" value="" />

	<table width="770" height="450" border="0" cellspacing="5" cellpadding="0" id="contactArea" class="centercoltext">
		<tr>
			
			<td valign="top" >
					<!--In&iacute;cio Tabela Reference a P&aacute;gina&ccedil;&atilde;o da Tela de Processo-->
		            <table>
		              <tr>
		                <td></td>
		              </tr>
		            </table>
		            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		              <tr>
		                <td width="11"><img border="0" src="imagens/parahead_left.gif"/></td>
		                <td class="parabg"> Informar Valor da Parcela do Contrato de Parcelamento por Cliente<strong></strong></td>
		                <td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif"/></td>
		              </tr>
		            </table>
		            <!--Fim Tabela Reference a P&aacute;gina&ccedil;&atilde;o da Tela de Processo-->
					<br />
					<br />
					<table>
						<tr>
							<td width="200">
								<b>Valor do Débito:</b>
							</td>
							<td width="100" style="border: 0pt none; background: none repeat scroll 0% 0% rgb(239, 239, 239);">
								<div align="left">
									<fmt:formatNumber value="${valorContaSelecao}" minFractionDigits="2" maxFractionDigits="2" type="number" />
								 </div>
							</td>
						</tr>
						
						<tr>
							<td>
								<b>Valor dos Acréscimos:</b>
							</td>
							<td style="border: 0pt none; background: none repeat scroll 0% 0% rgb(239, 239, 239);">
								<div align="left">
										<fmt:formatNumber value="${valorAcrescimoSelecao}" minFractionDigits="2" maxFractionDigits="2" type="number" />
								</div>
							</td>
						</tr>	
						<tr>
							<td>
								<b>Valor Parcelado:</b>
							</td>
							
						
							<td style="border: 0pt none; background: none repeat scroll 0% 0% rgb(239, 239, 239);">
								<div align="left">
									<input readonly="readonly" style="background-color:#EFEFEF; border:0; color: #000000" 
										name="valorParceladoPopUp" id="valorParceladoPopUp" 
										type="text" size="13" maxlength="8" value="${valorParceladoPopUp}" />
								</div>
							</td>
						</tr>	
					</table>
					<table>
						<tr>
							<td colspan="10" width="800">
								<div align="left">
								<div align="left">
								<hr>
								</div>
								</div>
							</td>
						</tr>
						<tr>
							<td width="250"><strong>N&uacute;mero de Parcelas:</strong></td>
							<td width="700"><strong> 
								<input 
									name="numeroParcelasPopUp" 
									id="numeroParcelasPopUp" 
									type="text" 
									onkeypress="return isCampoNumerico(event);"
									style="text-align:right;"
									size="4" 
									maxlength="4" 
									value="<c:out value="${numeroParcelasPopUp}"></c:out>"> </strong>
							</td>
						</tr>
					</table>
					<table>
						<tr>
							<td colspan="10" width="800">
								<div align="left">
								<div align="left">
								<hr>
								</div>
								</div>
							</td>
						</tr>
						<tr>
							<td width="300">
								<strong>Parcela:</strong>
							</td>
							<td width="700">
								<input name="parcelaInicial" id="parcelaInicial" type="text" size="4" onkeypress="return isCampoNumerico(event);" maxlength="4" /> 
								a 
								<input name="parcelaFinal" id="parcelaFinal" type="text" size="4" maxlength="4" onkeypress="return isCampoNumerico(event);" />
							</td>
						</tr>
						<tr>
							<td width="300">
								<strong>Valor da Parcela:</strong>
							</td>
							<td width="700">
								<input 
									name="valorDaParcelaAdd" 
									id="valorDaParcelaAdd" 
									type="text" 
									size="15" 
									maxlength="17" 
								    style="text-align:right;"
									onkeypress="return verificaVirgula(event, 'valorDaParcelaAdd');"
								 	onkeyup="formataValorMonetario(this, 17);" /> 
							</td>
							<td>
								 <input name="addParcela" type="button" onclick="adicionarParcela();" class="bottonRightCol" value="Adicionar" /> 
							</td>
						</tr>
						<tr>
							<td colspan="5">
								<div  style="overflow-x:hidden; overflow-y: scroll; max-height: 130px; height: 130px;" >
									<table width="100%" border="0" bgcolor="#90c7fc">
										<tr>
											<td width="50" bgcolor="#90c7fc">
											<div align="center"><strong> Remover</strong></div>
											</td>
											<td width="200" bgcolor="#90c7fc">
											<div align="center"><strong> Parcela</strong></div>
											</td>
											<td width="200" bgcolor="#90c7fc">
											<div align="center"><strong>Valor da Parcela</strong></div>
											</td>
										</tr>
										<%
										String corTabelaPopUp = "#FFFFFF";
										List<PrestacaoContratoParcelamento> listaDeParcelasPopUp = (List<PrestacaoContratoParcelamento>) session.getAttribute("listaDeParcelasPopUp");
										
										if(listaDeParcelasPopUp != null){
												 %>
											
											<%
											
											if(listaDeParcelasPopUp.size() == 1){
												PrestacaoContratoParcelamento parcelaIni = listaDeParcelasPopUp.get(0); 
											%>
												<tr bgcolor="<%=corTabelaPopUp %>"> 
												 <td align="center">
														<a onclick="javascript: removerParcela('<%=parcelaIni.getNumero()%>','<%=parcelaIni.getNumero()%>','0');" href="#"><img src="imagens/Error.gif" style="border: 0px;" alt="" width="14" height="14"></a>
													</td>
													<td>
														<div align="center"><%=parcelaIni.getNumero()%></div>
													</td>
													<td>
														<div align="center">
															<fmt:formatNumber value="<%=parcelaIni.getValor()%>" minFractionDigits="2" maxFractionDigits="2" type="number" />
														</div>
													</td>
												</tr>
												
											<%}else{

											List<Float> listaValoresDistintosPopUp = (List<Float>) session.getAttribute("listaValoresDistintosPopUp");
											
											ArrayList<Integer> idsParcelas = new ArrayList<Integer>();
											
											for(int i = 0; i < listaValoresDistintosPopUp.size(); i++)
											{ 
												
												List<PrestacaoContratoParcelamento> parcelasGrupo = new ArrayList<PrestacaoContratoParcelamento>();
												
												for(int j = 0; j < listaDeParcelasPopUp.size(); j++){
													
													if(listaValoresDistintosPopUp.get(i).compareTo(listaDeParcelasPopUp.get(j).getValor().floatValue()) == 0
															&& !idsParcelas.contains(listaDeParcelasPopUp.get(j).getNumero())){
														
															if (j+1 == listaDeParcelasPopUp.size() || !idsParcelas.contains(listaDeParcelasPopUp.get(j).getId()) ||
																	(j != 0 && listaDeParcelasPopUp.get(j-1).getNumero() + 1 ==  listaDeParcelasPopUp.get(j).getNumero() )){
																
																parcelasGrupo.add(listaDeParcelasPopUp.get(j));
																idsParcelas.add(listaDeParcelasPopUp.get(j).getNumero());
																if(j+1 < listaDeParcelasPopUp.size() &&
																		listaValoresDistintosPopUp.get(i) != listaDeParcelasPopUp.get(j+1).getValor().floatValue()){
																	j = listaDeParcelasPopUp.size();
																}
															}else{
																j = listaDeParcelasPopUp.size();
															}
															
													}
													
												}
												
										%>
												<tr bgcolor="<%=corTabelaPopUp %>">
											
												
											<%	if(parcelasGrupo.size() > 1){
												%>
															 <td align="center">
																	<a onclick="javascript: removerParcela('<%=parcelasGrupo.get(0).getNumero()%>','<%=parcelasGrupo.get(parcelasGrupo.size()-1).getNumero()%>','<%=i %>');" href="#"><img src="imagens/Error.gif" style="border: 0px;" alt="" width="14" height="14"></a>
																</td>
																<td>
																	<div align="center">
																			<%=parcelasGrupo.get(0).getNumero()%>-<%=parcelasGrupo.get(parcelasGrupo.size()-1).getNumero()%>
																	</div>
																</td>
																<td>
																	<div align="center">
																		<fmt:formatNumber value="<%=parcelasGrupo.get(0).getValor()%>" minFractionDigits="2" maxFractionDigits="2" type="number" />
																	</div>
																</td>
															</tr>
													
												<% }else if(parcelasGrupo.size() == 1){
												%>
															 <td align="center">
																<a onclick="javascript: removerParcela('<%=parcelasGrupo.get(0).getNumero()%>','<%=parcelasGrupo.get(0).getNumero()%>','<%=i %>');" href="#"><img src="imagens/Error.gif" style="border: 0px;" alt="" width="14" height="14"></a>
															</td>
															<td>
																<div align="center"><%=parcelasGrupo.get(0).getNumero()%></div>
															</td>
															<td>
																<div align="center">
																	<fmt:formatNumber value="<%=parcelasGrupo.get(0).getValor()%>" minFractionDigits="2" maxFractionDigits="2" type="number" />
																</div>
															</td>
														</tr>
													
													<%}%>
												<%
														if(corTabelaPopUp.equals("#FFFFFF")){
															corTabelaPopUp = "#cbe5fe";
														}else{
															corTabelaPopUp = "#FFFFFF";
														}
												%>
											<% }%> 	
											<% }%> 	
										<% }%> 	
									</table>
								</div>
								<%
									int totalParcelasPopUp = 0;
									
								if(listaDeParcelasPopUp != null){
									totalParcelasPopUp = listaDeParcelasPopUp.size();
								}

								%>
								<c:if test="${valorParcelTotal != null && valorParcelTotal != 0}">
									<div align="right" style="margin-right: 20px;"> 
											<b>Total do Valor das Parcelas:</b> R$ <fmt:formatNumber value="${valorParcelTotal}" minFractionDigits="2" maxFractionDigits="2" type="number" />
									</div>
								</c:if>
								
							</td>
						<tr>
					</table>
					
					<table>
						<tr>
							<td colspan="10" width="800">
								<div align="left">
								<div align="left">
								<hr>
								</div>
								</div>
							</td>
						</tr>
						 <tr> 
	                      <td align="left" colspan="2"> <input name="voltar" type="button" class="bottonRightCol" onclick="clicouVoltar();" value="Voltar"> 
	                        <input name="cancelar" type="button" class="bottonRightCol" onclick="clicouCancelar();"  value="Cancelar " /> 
	                      </td>
	                      <td align="right">
	                      		 <input name="confirmar" type="button" class="bottonRightCol" onclick="javascript: clicouInformar('<fmt:formatNumber value="${valorParcelTotal}" minFractionDigits="2" maxFractionDigits="2" type="number" />','<%=totalParcelasPopUp %>');" value="Informar" /> 
	                      </td>
	                    </tr>
					</table>
			</td>
			
	</table>

	</div>
<div id="backgroundPopup"></div>