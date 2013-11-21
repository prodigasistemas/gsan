<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.fachada.Fachada"%>
<%@ page import="gcom.arrecadacao.pagamento.Pagamento"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="gcom.faturamento.conta.Conta" isELIgnored="false"%>
<%@ page import="gcom.gui.faturamento.conta.ManterContaActionForm" isELIgnored="false"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">


<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ManterContaActionForm" />


<SCRIPT LANGUAGE="JavaScript">
<!--

function retificar(idConta){

 	var form = document.forms[0];
    form.action = "/gsan/exibirRetificarContaAction.do?contaID="+idConta+"&idImovel="+form.idImovel.value;
    form.submit();
}



function cancelarConta(form){

	var mensagem = "Nenhuma Conta foi selecionada para cancelamento.";

	if (validateManterContaActionForm(form)){
		if (CheckboxNaoVazioMensagemGenerico(mensagem, "")){
			var urlCancelamentoConta = "/gsan/exibirCancelarContaAction.do?conta=" + obterValorCheckboxMarcado()+"&idImovel="+form.idImovel.value;  
			abrirPopup(urlCancelamentoConta, 270, 600);
		}
	}
}


function colocarContaEmRevisao(form){

	var mensagem = "Nenhuma Conta foi selecionada para ser colocada em revisão.";

	if (validateManterContaActionForm(form)){
		if (CheckboxNaoVazioMensagemGenerico(mensagem, "")){
			var urlColocarRevisaoConta = "/gsan/exibirColocarRevisaoContaAction.do?conta=" + obterValorCheckboxMarcado()+"&idImovel="+form.idImovel.value;  
			abrirPopup(urlColocarRevisaoConta, 280, 480);
		}
	}
}


function retirarContaDeRevisao(form){

	var mensagem = "Nenhuma Conta foi selecionada para ser retirada de revisão.";

	if (validateManterContaActionForm(form)){
		if (CheckboxNaoVazioMensagemGenerico(mensagem, "")){
			var urlColocarRevisaoConta = "/gsan/retirarRevisaoContaAction.do?conta=" + obterValorCheckboxMarcado()+"&idImovel="+form.idImovel.value;  
			form.action = urlColocarRevisaoConta;
			
			if (confirm("Confirma retirada de revisão?")){
				submeterFormPadrao(form);
			}
		}
	}
}


function alterarVencimento(form){

	var mensagem = "Nenhuma Conta foi selecionada para ser alterado o vencimento.";

	if (validateManterContaActionForm(form)){
		if (CheckboxNaoVazioMensagemGenerico(mensagem, "")){
			var urlAlterarVencimentoConta = "/gsan/exibirAlterarVencimentoContaAction.do?conta=" + obterValorCheckboxMarcado()+"&idImovel="+form.idImovel.value;  
			form.action = urlAlterarVencimentoConta;
			abrirPopup(urlAlterarVencimentoConta, 295, 450);
		}
	}
}


function emitirSegundaViaConta(form){
	var mensagem = "Nenhuma Conta foi selecionada para emitir segunda via de conta.";

	if (validateManterContaActionForm(form)){
		if (CheckboxNaoVazioMensagemGenerico(mensagem, "")){
			var urlEmitirSegundaViaConta = "/gsan/gerarRelatorio2ViaContaAction.do?cobrarTaxaEmissaoConta=N&conta=" + obterValorCheckboxMarcado();  
			form.action = urlEmitirSegundaViaConta;
			redirecionarSubmit(urlEmitirSegundaViaConta);
			
			/*if (confirm('A impressão da 2ª Via de Conta irá gerar taxa de cobranca. Confirma?')){
				redirecionarSubmit(urlEmitirSegundaViaConta);
			}*/
		}
	}

}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'imovel') {
      form.idImovel.value = codigoRegistro;
      form.submit();
    }
 }
 
 
 function limparForm(){
 	var form = document.forms[0];
 	
 	for (var i=0;i < form.elements.length;i++){
		var elemento = form.elements[i];
		if (elemento.type == "text"){
			elemento.value = "";
		}
	}
	
	redirecionarSubmit("exibirManterContaAction.do?limparForm=OK");
 }
 
 function facilitador(objeto){
	if (objeto.id == "0" || objeto.id == undefined){
		objeto.id = "1";
		marcarTodos();
	}
	else{
		objeto.id = "0";
		desmarcarTodos();
	}
}
 
//-->
</SCRIPT>

<logic:present name="retificarConjuntoConta">

<SCRIPT LANGUAGE="JavaScript">
<!--

function retificarConjuntoConta(form){

	var mensagem = "Nenhuma Conta foi selecionada para ser retificada.";
	
	if (validateManterContaActionForm(form)){
		if (CheckboxNaoVazioMensagemGenerico(mensagem, "")){
			
			var urlRetificarConjuntoConta = "exibirRetificarConjuntoContaAction.do?idImovel="+form.idImovel.value;
			form.contaRetificarConjunto.value = obterValorCheckboxMarcado();
			
			form.action = urlRetificarConjuntoConta;
			
			abrirPopupComSubmit(urlRetificarConjuntoConta, 295, 450, 'RetificarConjuntoConta');
		}
	}
}

//-->
</SCRIPT>

</logic:present>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/exibirManterContaAction" method="post">
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<input type="hidden" name="contaRetificarConjunto" />
	
	<input type="hidden" name="usuarioPodeAtualizarRetificarContasPagas" value="<%=request.getAttribute("usuarioPodeAtualizarRetificarContasPagas")%>" />

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext">
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

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Manter Conta</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td>Para manter a(s) conta(s), informe os dados abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoContaManter', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td height="10" width="160"><strong>Matrícula do Imóvel:<font
						color="#FF0000">*</font></strong></td>
					<td width="403"><html:text property="idImovel" maxlength="9"
						size="9"
						onkeypress="validaEnterComMensagem(event, 'exibirManterContaAction.do', 'idImovel', 'Matrícula do Imóvel');return isCampoNumerico(event);" />
					<a href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);">
					<img width="23" height="21"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Imóvel" /></a>
					
					<logic:present name="corInscricao">
	
						<logic:equal name="corInscricao" value="exception">
							<html:text property="inscricaoImovel" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
						</logic:equal>
		
						<logic:notEqual name="corInscricao" value="exception">
							<html:text property="inscricaoImovel" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
						</logic:notEqual>
		
					</logic:present>
		
					<logic:notPresent name="corInscricao">
		
						<logic:empty name="ManterContaActionForm" property="idImovel">
							<html:text property="inscricaoImovel" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
						</logic:empty>
						<logic:notEmpty name="ManterContaActionForm" property="idImovel">
							<html:text property="inscricaoImovel" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
						</logic:notEmpty>
						
		
					</logic:notPresent>
					
					<a href="javascript:limparForm();" tabindex="1"> 
						<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" 
							 style="cursor: hand;"  
							 title="Apagar" /> 
					</a>	
					</td>
					<td width="43">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3">

					<table width="100%" align="center" bgcolor="#90c7fc" border="0">
						<tr>
							<td><strong>Dados do Imóvel</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" border="0">
								<tr>
									<td height="10"><strong>Nome do Cliente Usuário:</strong></td>
									<td><html:text property="nomeClienteUsuario" size="45"
										readonly="true" style="background-color:#EFEFEF; border:0" />
									</td>
								</tr>
								<tr>
									<td height="10"><strong>Situação de Água:</strong></td>
									<td><html:text property="situacaoAguaImovel" size="45"
										readonly="true" style="background-color:#EFEFEF; border:0" />
									</td>
								</tr>
								<tr>
									<td height="10"><strong>Situação de Esgoto:</strong></td>
									<td><html:text property="situacaoEsgotoImovel" size="45"
										readonly="true" style="background-color:#EFEFEF; border:0" />
									</td>
								</tr>
							</table>

							</td>
						</tr>
					</table>

					</td>
				</tr>
				<tr>
					<td colspan="3" height="10"></td>
				</tr>
				<tr>
					<td height="10">&nbsp;</td>
					<td><strong><font color="#FF0000">*</font></strong> Campos
					obrigat&oacute;rios</td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td colspan="3" height="5"></td>
				</tr>
				<tr>
					<td colspan="3">

					<table width="100%" border="0">
						<tr>
							<td colspan="4">
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td>
									<table width="100%" border="0" bgcolor="#90c7fc">
										<tr>
											<td bgcolor="#79bbfd" colspan="9" height="20">
												<strong>Contas do Imóvel</strong>
											</td>
										</tr>
										<tr bgcolor="#90c7fc">

											<td align="center"><A HREF="javascript:facilitador(this);" id="0"><strong>Todos</strong></A></td>
											<td width="10%">
											<div align="center"><strong>Refe.</strong></div>
											</td>
											<td width="13%">
											<div align="center"><strong>Venc.</strong></div>
											</td>
											<td width="10%">
											<div align="center"><strong>Valor</strong></div>
											</td>
											<td width="10%">
											<div align="center"><strong>Água</strong></div>
											</td>
											<td width="10%">
											<div align="center"><strong>Esgoto</strong></div>
											</td>
											<td width="13%">
											<div align="center"><strong>Validade</strong></div>
											</td>
											<td width="13%">
											<div align="center"><strong>Revisão</strong></div>
											</td>
											<td width="16%">
											<div align="center"><strong>Situação</strong></div>
											</td>

										</tr>
									</table>

									</td>
								</tr>

								<logic:present name="colecaoContaImovel">

									<tr>
										<td>
										
										<% String cor = "#cbe5fe";%>

										<div style="width: 100%; height: 100; overflow: auto;">
										
										<table width="100%" align="center" bgcolor="#90c7fc">
										
										<logic:iterate name="colecaoContaImovel" id="conta" type="Conta">

								
												<%	if (cor.equalsIgnoreCase("#cbe5fe")){
													cor = "#FFFFFF";%>
													<tr bgcolor="#FFFFFF">
												<%} else{
													cor = "#cbe5fe";%>
													<tr bgcolor="#cbe5fe">
												<%}%>
												
												<%
	  											 String data = "";
													 if(((Conta)conta).getUltimaAlteracao() != null){
														data = new Long(((Conta)conta).getUltimaAlteracao().getTime()).toString();	 
													 }	
												%>
												
													<td align="center" width="5%" valign="middle"><INPUT
														TYPE="checkbox" NAME="conta"
														value="<%="" + conta.getId().intValue()%>-<%=data%>"></td>
													<td width="10%" align="center">
														<!--  
															Bruno Barros
															05 de Janeiro de 2009
																												
															[FS0026] - Verificar a permissão especial para exibir as contas pagas.
																. Verificar se as contas selecionadas para o imóvel se encontram pagas, 
															através do identificador da conta CNTA_ID nas tabelas ARRECADACAO.PAGAMENTO. 
															Caso o usuário possua permissão especial, as contas identificadas nas 
															condições serão apresentadas e poderão ser retificadas e atualizadas, 
															de acordo com processos já existentes. Caso contrário, serão apenas apresentadas 
															as contas sem possibilitar que o usuário atualize ou retifique as contas pagas.												
														-->				
														
														<%
															Fachada fachada = Fachada.getInstancia();																										
															Pagamento pagamento = fachada.pesquisarPagamentoDeConta( conta.getId() );

															String idRA = (String)request.getAttribute("idRA");
															String habilitaRetificacaoContaRA = (String)request.getAttribute("habilitaRetificacaoContaRA");
															
															if(habilitaRetificacaoContaRA == "3"){
																
																boolean existeRegistroAtendimentoConta = fachada.
																	existeRegistroAtendimentoConta(conta.getId(),new Integer(idRA));
																
																if(existeRegistroAtendimentoConta){
																	habilitaRetificacaoContaRA = "1";
																}else{
																	habilitaRetificacaoContaRA = "2";
																}
															}
														%>
																								
														<logic:equal name="usuarioPodeAtualizarRetificarContasPagas" value="true" >
															<%
																if (habilitaRetificacaoContaRA == "2"){
																    
															%>
																<%=""+ Util.formatarMesAnoReferencia(conta.getReferencia())%>													
															<%
																} else if(habilitaRetificacaoContaRA == "1"){			
															%>
																<a href="javascript:retificar('<%="" + conta.getId().intValue()%>')">
																<%=""+ Util.formatarMesAnoReferencia(conta.getReferencia())%> </a>													
															<%  } %>
														</logic:equal>
														
														<logic:equal name="usuarioPodeAtualizarRetificarContasPagas" value="false" >
															<%
																if ( pagamento != null || habilitaRetificacaoContaRA == "2"){
																    
															%>
																<%=""+ Util.formatarMesAnoReferencia(conta.getReferencia())%>													
															<%
																} else if(habilitaRetificacaoContaRA == "1"){			
															%>
																<a href="javascript:retificar('<%="" + conta.getId().intValue()%>')">
																<%=""+ Util.formatarMesAnoReferencia(conta.getReferencia())%> </a>													
															<%  } %>
														</logic:equal>														
														<!-- //////////////////////////////////////////////////////////////////////////// -->
													</td>
													<td width="13%">
													<div align="center"><logic:present name="conta"
														property="dataVencimentoConta">
														<span style="color: #000000;"><%=""
					+ Util.formatarData(conta.getDataVencimentoConta())%></span>
													</logic:present> <logic:notPresent name="conta"
														property="dataVencimentoConta">
												&nbsp;
											</logic:notPresent></div>
													</td>
													<td width="10%"><div align="right">
														<span style="color: #000000;"><%="" + Util.formatarMoedaReal( new BigDecimal( conta.getValorTotalConta() )).trim()%></span></div></td>
													<td width="10%">
													<div align="center"><logic:present name="conta"
														property="consumoAgua">
														<bean:write name="conta" property="consumoAgua" />
													</logic:present> <logic:notPresent name="conta"
														property="consumoAgua">
												&nbsp;
											</logic:notPresent></div>
													</td>
													<td width="10%">
													<div align="center"><logic:present name="conta"
														property="consumoEsgoto">
														<bean:write name="conta" property="consumoEsgoto" />
													</logic:present> <logic:notPresent name="conta"
														property="consumoEsgoto">
												&nbsp;
											</logic:notPresent></div>
													</td>
													<td width="13%">
													<div align="center"><logic:present name="conta"
														property="dataValidadeConta">
														<span style="color: #000000;"><%=""
							+ Util.formatarData(conta.getDataValidadeConta())%></span>
													</logic:present> <logic:notPresent name="conta"
														property="dataValidadeConta">
												&nbsp;
											</logic:notPresent></div>
													</td>
													<td width="13%">
													<div align="center"><logic:present name="conta"
														property="dataRevisao">
														<span style="color: #000000;"><%="" + Util.formatarData(conta.getDataRevisao())%></span>
													</logic:present> <logic:notPresent name="conta"
														property="dataRevisao">
												&nbsp;
											</logic:notPresent></div>
													</td>
													<td width="16%">
													<div align="center">
													
													<logic:present name="conta" property="debitoCreditoSituacaoAtual">
														<bean:write name="conta" property="debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao" />
													</logic:present> 
													
													<logic:notPresent name="conta" property="debitoCreditoSituacaoAtual">
														&nbsp;
													</logic:notPresent>
													
													</div>
													
													</td>
												
												</tr>
											

										</logic:iterate>
										
										</table>
										
										</div>
										</td>
									</tr>

								</logic:present>

							</table>
							</td>
						</tr>

					</table>

					</td>
				</tr>
				<tr>
					<td colspan="3" height="5">
						<gsan:controleAcessoBotao name="Button"
						value="Cancelar Conta" 
						onclick="cancelarConta(document.forms[0]);" url="cancelarContaAction.do" style="width: 103px"/> 
						<gsan:controleAcessoBotao 
						name="Button" value="Colocar Revisão"
						
						onclick="colocarContaEmRevisao(document.forms[0]);" url="colocarRevisaoContaAction.do" style="width: 103px"/> 
						<gsan:controleAcessoBotao 
						name="Button" value="Retirar Revisão"
						 onclick="retirarContaDeRevisao(document.forms[0]);" url="exibirManterContaAction.do" style="width: 100px"/>
						<gsan:controleAcessoBotao
						name="Button" value="Alterar Vencimento"
						onclick="alterarVencimento(document.forms[0]);" url="alterarVencimentoContaAction.do" style="width: 120px"/>
						
						 <gsan:controleAcessoBotao
						name="Button" value="Emitir 2ª Via de Conta"
						onclick="emitirSegundaViaConta(document.forms[0]);" url="gerarRelatorio2ViaContaAction.do" style="width: 140px"/>
						
						<logic:present name="retificarConjuntoConta">
						
							<br><br>
							 
							 <gsan:controleAcessoBotao
							name="Button" value="Retificar Conjunto de Conta"
							onclick="retificarConjuntoConta(document.forms[0]);" url="retificarConjuntoContaAction.do" style="width: 170px"/>
							
						</logic:present>
						
						</td>
				</tr>
				<tr>
					<td valign="top">
						<input type="button" name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:document.forms[0].target='';window.location.href='/gsan/telaPrincipal.do'">
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>


</body>
</html:html>
