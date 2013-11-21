<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper"%>
<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>
<%@ page import="gcom.cadastro.cliente.ClienteImovel"%>


<%@ page import="gcom.gui.GcomAction"%>


<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%-- Carrega validações do validator --%>
<%--html:javascript staticJavascript="false"  formName="InserirEquipeActionForm" /--%>
<%-- Carrega javascripts da biblioteca --%>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<%-- Novos Javascripts --%>
<script language="JavaScript">
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
	    
	    if (tipoConsulta == 'imovel') {
	      form.idImovelDebitos.value = codigoRegistro;
	      form.matriculaImovelDebitos.value = descricaoRegistro;
		  form.matriculaImovelDebitos.style.color = '#000000';	      
	    } 
	}
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
	if(objetoRelacionado.readOnly != true){
		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		}
		else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}
			else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
			}
		}
	}
	}
	
   	/* Fecha Popup */
   	function fechar() {
   		limparForm();
   		window.close();
   	}
   	
   	function limparForm(){
   	  var form = document.forms[0];
	  form.action = 'exibirAdicionarMatriculaImovelNegativacaoAction.do?limparForm=OK'
	  form.submit();
	}
	
	function validarForm(form){
	  if(form.idImovelDebitos.value == null || form.idImovelDebitos.value  == ""){
	    alert("Informe o imóvel a ser inserido");
	  }else{
	    submeterFormPadrao(form);
	  }
	}
	
   function validarAtualizarForm(form){
   	  var form = document.forms[0];
	  form.action = 'adicionarMatriculaImovelNegativacaoAction.do?atualizar=OK'
	  form.submit();
	}

</script>
</head>

<logic:present name="reloadPage">
	<body leftmargin="0" topmargin="0" onload="chamarSubmitComUrl('exibirInserirComandoNegativacaoMatriculaImovelAction.do'); window.close();">
</logic:present>

<logic:notPresent name="reloadPage">
	<body leftmargin="0" topmargin="0" onload="resizePageSemLink(660, 610)">
</logic:notPresent>

<html:form action="/adicionarMatriculaImovelNegativacaoAction"
	name="InserirComandoNegativacaoActionForm"
	type="gcom.gui.spcserasa.InserirComandoNegativacaoActionForm"
	method="post">
	
	<table width="600" border="0" cellpadding="0" cellspacing="5">
		<tr> 
	    	<td width="590" valign="top" class="centercoltext">
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
	          			<td class="parabg">Adicionar Matrícula do Imóvel para Negativação</td>
	          			<td width="11">
	          				<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
	          			</td>
	        		</tr>
	      		</table>
	      		<p>&nbsp;</p>
	      		<table width="100%" border="0">
	      		 <tr>
      			  <td colspan="4">Informe o imóvel </td>
      			 </tr>
   			 	 <tr>
					<td bordercolor="#000000" ><strong>Matrícula do Im&oacute;vel:<font
							color="#FF0000">*</font></strong></td>
					<td colspan="3">
					<logic:present name="atualizar" scope="request">
					    <html:text
							property="idImovelDebitos" maxlength="9" size="9" disabled="true"/>													
						<img width="23" height="21"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							border="0" /> 					
					</logic:present>
					
					<logic:notPresent name="atualizar" scope="request">
					    <html:text
							property="idImovelDebitos" maxlength="9" size="9"
							onkeypress="validaEnterComMensagem(event, 'exibirAdicionarMatriculaImovelNegativacaoAction.do','idImovelDebitos','Im&oacute;vel');" 
							onkeyup="limparImovelTecla();" />
						<a
							href="javascript:redirecionarSubmit('exibirPesquisarImovelAction.do?caminhoRetornoTelaPesquisaImovel=exibirAdicionarMatriculaImovelNegativacaoAction', 275, 480);">
							
						<img width="23" height="21"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							border="0" /></a> 
					</logic:notPresent>
						<logic:present
							name="idImovelDebitosNaoEncontrado" scope="request">
							<html:text property="matriculaImovelDebitos" size="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:present> 
						<logic:notPresent
							name="idImovelDebitosNaoEncontrado" scope="request">
							<logic:present name="valorMatriculaImovelDebitos"
								scope="request">
								<html:text property="matriculaImovelDebitos" size="40"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:present>
							<logic:notPresent name="valorMatriculaImovelDebitos"
								scope="request">
								<html:text property="matriculaImovelDebitos" size="40"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notPresent>
						</logic:notPresent> 
						<logic:present name="atualizar" scope="request">
						    <img
							  src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							  border="0" title="Apagar" />
						</logic:present>
						<logic:notPresent name="atualizar" scope="request">
						    <a href="javascript:limparForm();"> <img
							  src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							  border="0" title="Apagar" /></a>						
						</logic:notPresent>
					</td>
				  </tr>				
				  <tr>
					    <td colspan="4">
					      <table width="100%" align="center" bgcolor="#99CCFF" border="0">
						  <tr>
							<td align="center"><strong>Dados do Imóvel</strong></td>
						  </tr>
						  <tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" border="0">
							<tr>
								<td height="10" width="40%">
									<div class="style9"><strong>Período de Referência do Débito:</strong></div>
									</td>
								<td><html:text property="referenciaInicial" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="10" maxlength="10" />a <html:text property="referenciaFinal" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="10" maxlength="10" /></td>
							  </tr>
							  <tr>
								<td height="10" width="40%">
									<div class="style9"><strong>Período de Vencimento do Débito:</strong></div>
									</td>
								<td><html:text property="dataVencimentoInicial" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="10" maxlength="10" />a <html:text property="dataVencimentoFinal" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="10" maxlength="10" /></td>
							  </tr>
							  <!--<tr>
								<td height="10" width="40%">
									<div class="style9"><strong>Inscrição:</strong></div>
									</td>
								<td><html:text property="matriculaImovelDebitos" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="20" maxlength="20" /></td>
							  </tr>-->
							  <tr>
								<td height="10" width="40%">
									<div class="style9"><strong>Situação da Ligação de Água:</strong></div>
									</td>
								<td><html:text property="situacaoAguaDebitos" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="20" maxlength="20" /></td>
							  </tr>
							  <tr>
							  	<td width="40%"><strong>Situação da Ligação de Esgoto:</strong></td>
								<td><html:text property="situacaoEsgotoDebitos"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="20" maxlength="20" /></td>
							  </tr>
							</table>
						    </td>
						  </tr>
					      </table>
					    </td>
				      </tr>
				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
							<td align="center">
							<div class="style9"><strong>Endere&ccedil;o </strong></div>

							</td>
						</tr>
						<tr bgcolor="#FFFFFF">
							<td align="center">
							<div class="style9">${enderecoFormatado} &nbsp;</div>
							</td>

						</tr>
					</table>
					</td>
				</tr>
				
					  <tr>
					    <td colspan="4">
					      <table width="100%" border="0">
						    <tr>
							  <td colspan="4">
							    <table width="100%" align="center" bgcolor="#90c7fc" border="0">
								  <tr bordercolor="#79bbfd">
									<td colspan="5" align="center" bgcolor="#79bbfd"><strong>Clientes</strong></td>
								  </tr>
								  <tr bordercolor="#000000">
								  	<td width="10%" bgcolor="#90c7fc" align="center">
									<div class="style9"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Negativar
										</strong> </font></div>
									</td>
									<td width="34%" bgcolor="#90c7fc" align="center">
									<div class="style9"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Nome do
									Cliente</strong> </font></div>
									</td>
									<td width="17%" bgcolor="#90c7fc" align="center">
									<div class="style9"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo da
									Rela&ccedil;&atilde;o</strong> </font></div>
									</td>
									<td width="19%" bgcolor="#90c7fc" align="center">
									<div class="style9"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Data
									In&iacute;cio Rela&ccedil;&atilde;o</strong></font></div>
									</td>
									<td width="20%" bgcolor="#90c7fc" align="center"><font
										color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>CPF/CNPJ</strong>
									</font></td>
								  </tr>
								  <tr>
									<td width="100%" colspan="5">
									  <div style="width: 100%; height: 100%; overflow: auto;">
									    <table width="100%" align="left" bgcolor="#99CCFF">
										<!--corpo da segunda tabela-->
										<%int cont = 0;%>
										<logic:notEmpty name="imovelClientes">
											<logic:iterate name="imovelClientes" id="imovelCliente"
												type="ClienteImovel">
												<%cont = cont + 1;
												if (cont % 2 == 0) {%>
												<tr bgcolor="#cbe5fe">
													<%} else {%>
												<tr bgcolor="#FFFFFF">
													<%}%>
													
													
											    <%if (imovelCliente.getCliente().getCpf() == null && imovelCliente.getCliente().getCnpj() == null) {%>
													<td width="10%" align="center">
														<html:radio property="clienteSelecionado"
														value="${imovelCliente.id}" disabled="true" />
													 </td>	
												<%} else {%>
													<td width="10%" align="center">
														<html:radio property="clienteSelecionado"
														value="${imovelCliente.id}" />
													 </td>	
												<%}%>

													<td width="34%" bordercolor="#90c7fc" align="left">
													<div class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> 
													  <logic:present name="imovelCliente" property="cliente">											
														<bean:write name="imovelCliente" property="cliente.nome" />
													  </logic:present> </font>
													</div>
													</td>
													<td width="17%" align="left">
													<div class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> 
														<logic:present name="imovelCliente" property="clienteRelacaoTipo">
														  <bean:write name="imovelCliente"
															property="clienteRelacaoTipo.descricao" />
														</logic:present> </font></div>
													</td>
													<td width="19%" align="center">
													<div class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> </font></div>
													<bean:write name="imovelCliente"
														property="dataInicioRelacao" formatKey="date.format" /></td>
													<td width="20%" align="right"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> 
													<logic:notEmpty name="imovelCliente" property="cliente.cpf">
														<bean:write name="imovelCliente"
															property="cliente.cpfFormatado" />
													</logic:notEmpty>
													<logic:notEmpty name="imovelCliente" property="cliente.cnpj">
														<bean:write name="imovelCliente"
															property="cliente.cnpjFormatado" />
													</logic:notEmpty> </font></td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
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
				      <tr>
					    <td colspan="4">
					      <table width="100%" align="center" bgcolor="#90c7fc" border="0">
							<%String cor = "#cbe5fe";%>
							<%cor = "#cbe5fe";%>
							<tr bordercolor="#79bbfd">
							  <td colspan="11" align="center" bgcolor="#79bbfd">
							    <strong>Contas</strong>
							  </td>
						    </tr>
						    <logic:notEmpty name="colecaoContaValores" scope="session">
							<%if (((Collection) session.getAttribute("colecaoContaValores"))
							.size() <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
							<tr bordercolor="#000000">
								<td width="9%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano</strong>
								</font></div>
								</td>
								<td width="12%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Vencimento</strong>
								</font></div>
								</td>
								<td width="8%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor de
								&Aacute;gua </strong> </font></div>
								</td>
								<td width="8%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor de
								Esgoto</strong> </font></div>
								</td>
								<td width="10%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor
								D&eacute;bitos</strong> </font></div>
								</td>
								<td width="10%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor
								Creditos</strong> </font></div>
								</td>
								
								<td width="10%" bgcolor="#90c7fc" align="center">
								  <div class="style9">
								    <font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								      <strong>Valor dos Impostos</strong> 
								    </font>
								  </div>
								</td>
								
								<td width="9%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor da
								Conta</strong> </font></div>
								</td>
								<td width="8%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Acr&eacute;sc.
								Impont.</strong><strong></strong> </font></div>
								</td>
								<td width="10%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Sit.</strong>
								</font></div>
								</td>
								<td width="6%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Parcel.</strong>
								</font></div>
								</td>
							</tr>
							<logic:present name="colecaoContaValores">
								<logic:iterate name="colecaoContaValores"
									id="contavaloreshelper">
									<%if (cor.equalsIgnoreCase("#cbe5fe")) {
										cor = "#FFFFFF";%>
									<tr bgcolor="#FFFFFF">
										<%} else {
										cor = "#cbe5fe";%>
									<tr bgcolor="#cbe5fe">
										<%}%>
										
										<td align="left">
											<logic:notEmpty name="contavaloreshelper" property="conta">
											
																							
												<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
													<div align="center">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<a href="javascript:redirecionarSubmit('exibirConsultarContaAction.do?caminhoRetornoTelaConsultaConta=exibirAdicionarMatriculaImovelNegativacaoAction&contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">															
															<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
															<bean:write name="conta" property="formatarAnoMesParaMesAno" /></a> 
														</font>
													</div>
												</logic:empty>

												<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
													<div align="center">
														<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<a href="javascript:redirecionarSubmit('exibirConsultarContaAction.do?caminhoRetornoTelaConsultaConta=exibirAdicionarMatriculaImovelNegativacaoAction&contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">															
															<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
															<font color="#ff0000"><bean:write name="conta" property="formatarAnoMesParaMesAno" /></font></a> 
														</font>
													</div>
												</logic:notEmpty>
											
												
											</logic:notEmpty>
										</td>
										<td align="left">
											<logic:notEmpty name="contavaloreshelper" property="conta">
												<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
													<div align="center">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
															<bean:write name="conta" property="dataVencimentoConta" formatKey="date.format" /> 
														</font>
													</div>
												</logic:empty>

												<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
													<div align="center">
														<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
															<bean:write name="conta" property="dataVencimentoConta" formatKey="date.format" /> 
														</font>
													</div>
												</logic:notEmpty>
											
											</logic:notEmpty>
										</td>
										<td align="right">
											<logic:notEmpty name="contavaloreshelper" property="conta">
																							
												<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
													<div align="right">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
															<bean:write name="conta" property="valorAgua" formatKey="money.format" />
														</font>
													</div>
												</logic:empty>

												<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
													<div align="right">
														<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
															<bean:write name="conta" property="valorAgua" formatKey="money.format" />
														</font>
													</div>
												</logic:notEmpty>
											
												
											</logic:notEmpty>
										</td>
										<td align="rigth">
											<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
												<div align="right">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<logic:notEmpty name="contavaloreshelper" property="conta">
															<bean:define name="contavaloreshelper" property="conta" id="conta" />
															<bean:write name="conta" property="valorEsgoto" formatKey="money.format" />
														</logic:notEmpty> 
													</font>
												</div>
											</logic:empty>

											<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
												<div align="right">
													<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<logic:notEmpty name="contavaloreshelper" property="conta">
															<bean:define name="contavaloreshelper" property="conta" id="conta" />
															<bean:write name="conta" property="valorEsgoto" formatKey="money.format" />
														</logic:notEmpty> 
													</font>
												</div>	
											</logic:notEmpty>
										</td>
										
										<td align="right">
											<logic:notEmpty name="contavaloreshelper" property="conta">
																								
												<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
													<div align="right">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<logic:notEqual name="contavaloreshelper" property="conta.debitos" value="0">
																<a href="javascript:redirecionarSubmit('exibirConsultarDebitoCobradoAction.do?caminhoRetornoTelaConsultaDebitos=exibirAdicionarMatriculaImovelNegativacaoAction&contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																<bean:write name="contavaloreshelper" property="conta.debitos" formatKey="money.format" /> </a>
															</logic:notEqual> 
															<logic:equal name="contavaloreshelper" property="conta.debitos" value="0">
																<bean:write name="contavaloreshelper" property="conta.debitos" formatKey="money.format" />
															</logic:equal> 
														</font>
													</div>
												</logic:empty>

												<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
													<div align="right">
														<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<logic:notEqual name="contavaloreshelper" property="conta.debitos" value="0">
																<a href="javascript:redirecionarSubmit('exibirConsultarDebitoCobradoAction.do?caminhoRetornoTelaConsultaDebitos=exibirAdicionarMatriculaImovelNegativacaoAction&contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																<font color="#ff0000"><bean:write name="contavaloreshelper" property="conta.debitos" formatKey="money.format" /></font> </a>
															</logic:notEqual> 
															<logic:equal name="contavaloreshelper" property="conta.debitos" value="0">
																<font color="#ff0000"><bean:write name="contavaloreshelper" property="conta.debitos" formatKey="money.format" /></font>
															</logic:equal> 
														</font>
													</div>
												</logic:notEmpty>
												
												
											</logic:notEmpty>
										</td>
										
										<td align="right">
											<logic:notEmpty name="contavaloreshelper" property="conta">
												<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
													<div align="right">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<logic:notEqual name="contavaloreshelper" property="conta.valorCreditos" value="0">	
																<a href="javascript:redirecionarSubmit('exibirConsultarCreditoRealizadoAction.do?caminhoRetornoTelaConsultaCreditoRealizado=exibirAdicionarMatriculaImovelNegativacaoAction&contaID=<bean:define name="contavaloreshelper"	property="conta" id="conta" />
																	<bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																	<bean:write name="contavaloreshelper" property="conta.valorCreditos" formatKey="money.format" />
																</a>
															</logic:notEqual> 
															<logic:equal name="contavaloreshelper" property="conta.valorCreditos" value="0">
																<bean:write name="contavaloreshelper" property="conta.valorCreditos" formatKey="money.format" />
															</logic:equal> 
														</font>
													</div>
												</logic:empty>

												<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
													<div align="right">
														<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<logic:notEqual name="contavaloreshelper" property="conta.valorCreditos" value="0">	
															   <a href="javascript:redirecionarSubmit('exibirConsultarCreditoRealizadoAction.do?caminhoRetornoTelaConsultaCreditoRealizado=exibirAdicionarMatriculaImovelNegativacaoAction&contaID=<bean:define name="contavaloreshelper"	property="conta" id="conta" />
																	<bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																	<font color="#ff0000"><bean:write name="contavaloreshelper" property="conta.valorCreditos" formatKey="money.format" /></font>
																</a>
															</logic:notEqual> 
															<logic:equal name="contavaloreshelper" property="conta.valorCreditos" value="0">
																<font color="#ff0000"><bean:write name="contavaloreshelper" property="conta.valorCreditos" formatKey="money.format" /></font>
															</logic:equal> 
														</font>
													</div>
												</logic:notEmpty>
											</logic:notEmpty>
										</td>
										
										
										
										
										
										<td align="right">
											<logic:notEmpty name="contavaloreshelper" property="conta">
																							
												<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
													<div align="right">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
															<bean:write name="conta" property="valorImposto" formatKey="money.format" />
														</font>
													</div>
												</logic:empty> 

												<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
													<div align="right">
														<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
															<bean:write name="conta" property="valorImposto" formatKey="money.format" />
														</font>
													</div>
												</logic:notEmpty>
												
											</logic:notEmpty>
										</td>
										
										
										
										
										
										<td align="right">
											<logic:notEmpty name="contavaloreshelper" property="conta">
												<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
													<div align="right">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
															<bean:write name="conta" property="valorTotal" formatKey="money.format" />
														</font>
													</div>
												</logic:empty>

												<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
													<div align="right">
														<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
															<bean:write name="conta" property="valorTotal" formatKey="money.format" />
														</font>
													</div>
												</logic:notEmpty>
											</logic:notEmpty>
										</td>
										<td align="right">
											<logic:notEmpty name="contavaloreshelper" property="conta">
												<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
													<div align="right" class="style9">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<logic:notEqual name="contavaloreshelper" property="valorTotalContaValores" value="0">
																<a href="javascript:redirecionarSubmit('exibirValorAtualizacaoConsultarPopupAction.do?caminhoRetornoTelaConsultaAcrescimos=exibirAdicionarMatriculaImovelNegativacaoAction&multa=<bean:write name="contavaloreshelper" property="valorMulta" />&juros=<bean:write name="contavaloreshelper" property="valorJurosMora" />&atualizacao=<bean:write name="contavaloreshelper" property="valorAtualizacaoMonetaria" />', 300, 650);">
																	<bean:write name="contavaloreshelper" property="valorTotalContaValores" formatKey="money.format" />
																</a>
															</logic:notEqual> 
															<logic:equal name="contavaloreshelper" property="valorTotalContaValores" value="0">
																<bean:write name="contavaloreshelper" property="valorTotalContaValores" formatKey="money.format" />
															</logic:equal> 
														</font>
													</div>
												</logic:empty>

												<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
													<div align="right" class="style9">
														<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<logic:notEqual name="contavaloreshelper" property="valorTotalContaValores" value="0">
																<a href="javascript:redirecionarSubmit('exibirValorAtualizacaoConsultarPopupAction.do?caminhoRetornoTelaConsultaAcrescimos=exibirAdicionarMatriculaImovelNegativacaoAction&multa=<bean:write name="contavaloreshelper" property="valorMulta" />&juros=<bean:write name="contavaloreshelper" property="valorJurosMora" />&atualizacao=<bean:write name="contavaloreshelper" property="valorAtualizacaoMonetaria" />', 300, 650);">
																	<font color="#ff0000"><bean:write name="contavaloreshelper" property="valorTotalContaValores" formatKey="money.format" /></font>
																</a>
															</logic:notEqual> 
															<logic:equal name="contavaloreshelper" property="valorTotalContaValores" value="0">
																<bean:write name="contavaloreshelper" property="valorTotalContaValores" formatKey="money.format" />
															</logic:equal> 
														</font>
													</div>
												</logic:notEmpty>
											</logic:notEmpty>
										</td>
										<td align="left">
											<logic:notEmpty name="contavaloreshelper" property="conta">
												<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
													<div align="left">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
														<bean:define name="conta" property="debitoCreditoSituacaoAtual" id="debitoCreditoSituacaoAtual" /> 
														<bean:write name="debitoCreditoSituacaoAtual" property="descricaoAbreviada" /> 
													</font>
												</div>
												</logic:empty>

												<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
													<div align="left">
													<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
														<bean:define name="conta" property="debitoCreditoSituacaoAtual" id="debitoCreditoSituacaoAtual" /> 
														<bean:write name="debitoCreditoSituacaoAtual" property="descricaoAbreviada" /> 
													</font>
												</div>
												</logic:notEmpty>
											</logic:notEmpty>
										</td>
										<td width="9%" align="left">
																								
										<logic:notEmpty name="contavaloreshelper" property="existeParcelamento">
										  <div align="left" class="style9"><font color="#000000"
										   style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
					 						Sim
										  </font>
										  </div>
										</logic:notEmpty>																																			
										</td>
									</tr>
								</logic:iterate>
								
								<logic:notEmpty name="colecaoContaValores">
									<%if (cor.equalsIgnoreCase("#cbe5fe")) {
										cor = "#FFFFFF";%>
									<tr bgcolor="#FFFFFF">
										<%} else {
									cor = "#cbe5fe";%>
									<tr bgcolor="#cbe5fe">
										<%}%>
										<td bgcolor="#90c7fc" align="center">
											<div class="style9" align="center">
												<font color="#000000" style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong>
										</font></div>
										</td>
										<td align="left">
										
											<%=((Collection) session.getAttribute("colecaoContaValores")).size()%>
												&nbsp;
												doc(s)
										</td>
										<td align="right">
										<div align="right"><font color="#000000" style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorAgua")%>
										</font></div>
										</td>
										<td align="rigth">
										<div align="right"><font color="#000000" style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorEsgoto")%>
										</font></div>
										</td>
										<td align="right">
										<div align="right"><font color="#000000" style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorDebito")%>
										</font></div>
										</td>
										<td align="right">
										<div align="right"><font color="#000000" style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorCredito")%>
										</font></div>
										</td>
										<td align="right">
										<div align="right"><font color="#000000" style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorImposto")%>
										</font></div>
										</td>
										<td align="right">
										<div align="right"><font color="#000000" style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorConta")%>
										</font></div>
										</td>
										<td align="right">
										<div align="right" class="style9"><font color="#000000"
											style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorAcrescimo")%>
										</font></div>
										</td>
										<td align="left">
										<div align="left"><font color="#000000" style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> </font></div>
										</td>
										<td align="left">
										<div align="left"><font color="#000000" style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> </font></div>
										</td>										
									</tr>
								</logic:notEmpty>
							</logic:present>
							<%} else {%>
							<tr bordercolor="#000000">
								<td width="9%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano</strong>
								</font></div>
								</td>
								<td width="12%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Vencimento</strong>
								</font></div>
								</td>
								<td width="8%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor de
								&Aacute;gua </strong> </font></div>
								</td>
								<td width="8%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor de
								Esgoto</strong> </font></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor
								<br>
								D&eacute;bitos</strong> </font></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor
								Creditos</strong> </font></div>
								</td>
								
								
								<td width="10%" bgcolor="#90c7fc">
								  <div align="center" class="style9">
								    <font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								      <strong>Valor dos	Impostos</strong> 
								    </font>
								  </div>
								</td>																
								
								<td width="9%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor da
								Conta</strong> </font></div>
								</td>
								<td width="8%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Acr&eacute;sc.
								Impont.</strong><strong></strong> </font></div>
								</td>
								<td width="8%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Sit.</strong>
								</font></div>
								</td>
								<td width="8%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Parcel.</strong>
								</font></div>
								</td>
							</tr>
							
							<tr>
								<td height="100" colspan="11">
								<div style="width: 100%; height: 100%; overflow: auto;">
								<table width="100%">
									<logic:present name="colecaoContaValores">
										<logic:iterate name="colecaoContaValores"
											id="contavaloreshelper">
											<%if (cor.equalsIgnoreCase("#cbe5fe")) {
					cor = "#FFFFFF";%>
											<tr bgcolor="#FFFFFF">
												<%} else {
					cor = "#cbe5fe";%>
											<tr bgcolor="#cbe5fe">
												<%}%>
												<td width="9%" align="left">
													<div align="left" class="style9">
													
													<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<font color="#000000"
															style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> 
														<logic:notEmpty
															name="contavaloreshelper" property="conta">
															<a href="javascript:redirecionarSubmit('exibirConsultarContaAction.do?caminhoRetornoTelaConsultaConta=exibirAdicionarMatriculaImovelNegativacaoAction&contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">															
															<bean:define name="contavaloreshelper" property="conta"
																id="conta" /> <bean:write name="conta"
																property="formatarAnoMesParaMesAno" /></a>
														</logic:notEmpty> </font>
													</logic:empty>
	
													<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<font color="#ff0000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
														 <logic:notEmpty name="contavaloreshelper" property="conta">
														<a href="javascript:redirecionarSubmit('exibirConsultarContaAction.do?caminhoRetornoTelaConsultaConta=exibirAdicionarMatriculaImovelNegativacaoAction&contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">															
														<bean:define name="contavaloreshelper" property="conta"
															id="conta" /> <font color="#ff0000"><bean:write name="conta"
															property="formatarAnoMesParaMesAno" /></font> </a>
														</logic:notEmpty> </font>
													</logic:notEmpty>
													</div>
												</td>
												
												<td width="12%" align="left">
													<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<div align="left" class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
														name="contavaloreshelper" property="conta">
														<bean:define name="contavaloreshelper" property="conta"
															id="conta" />
														<bean:write name="conta" property="dataVencimentoConta"
															formatKey="date.format" />
													</logic:notEmpty> </font></div>
													</logic:empty>
	
													<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<div align="left" class="style9"><font color="#ff0000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
														name="contavaloreshelper" property="conta">
														<bean:define name="contavaloreshelper" property="conta"
															id="conta" />
														<bean:write name="conta" property="dataVencimentoConta"
															formatKey="date.format" />
														</logic:notEmpty> </font></div>
													</logic:notEmpty>
												</td>
												
												<td width="8%" align="right">
																								
													<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
																name="contavaloreshelper" property="conta">
																<bean:define name="contavaloreshelper" property="conta"
																	id="conta" />
																<bean:write name="conta" property="valorAgua"
																	formatKey="money.format" />
															</logic:notEmpty> </font></div>
													</logic:empty>
	
													<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9"><font color="#ff0000"
															style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
															name="contavaloreshelper" property="conta">
															<bean:define name="contavaloreshelper" property="conta"
																id="conta" />
															<bean:write name="conta" property="valorAgua"
																formatKey="money.format" />
														</logic:notEmpty> </font></div>
													</logic:notEmpty>
												</td>
												
												<td width="8%" align="right">
													<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
														name="contavaloreshelper" property="conta">
														<bean:define name="contavaloreshelper" property="conta"
															id="conta" />
														<bean:write name="conta" property="valorEsgoto"
															formatKey="money.format" />
													</logic:notEmpty> </font></div>
													</logic:empty>
	
													<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9"><font color="#ff0000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
														name="contavaloreshelper" property="conta">
														<bean:define name="contavaloreshelper" property="conta"
															id="conta" />
														<bean:write name="conta" property="valorEsgoto"
															formatKey="money.format" />
													</logic:notEmpty> </font></div>
													</logic:notEmpty>
												</td>
												
												<td width="10%" align="right">
																								
												<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
										<div align="right" class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
														name="contavaloreshelper" property="conta">
														<logic:notEqual name="contavaloreshelper"
															property="conta.debitos" value="0">
														<a href="javascript:redirecionarSubmit('exibirConsultarDebitoCobradoAction.do?caminhoRetornoTelaConsultaDebitos=exibirAdicionarMatriculaImovelNegativacaoAction&contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
															<bean:write name="contavaloreshelper"
																property="conta.debitos" formatKey="money.format" /> </a>
														</logic:notEqual>
														<logic:equal name="contavaloreshelper"
															property="conta.debitos" value="0">
															<bean:write name="contavaloreshelper"
																property="conta.debitos" formatKey="money.format" />
														</logic:equal>
													</logic:notEmpty> </font></div>
												</logic:empty>

												<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
													<div align="right" class="style9"><font color="#ff0000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
														name="contavaloreshelper" property="conta">
														<logic:notEqual name="contavaloreshelper"
															property="conta.debitos" value="0">
															<a href="javascript:redirecionarSubmit('exibirConsultarDebitoCobradoAction.do?caminhoRetornoTelaConsultaDebitos=exibirAdicionarMatriculaImovelNegativacaoAction&contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
															<font color="#ff0000"><bean:write name="contavaloreshelper"
																property="conta.debitos" formatKey="money.format" /> </font></a>
														</logic:notEqual>
														<logic:equal name="contavaloreshelper"
															property="conta.debitos" value="0">
															<bean:write name="contavaloreshelper"
																property="conta.debitos" formatKey="money.format" />
														</logic:equal>
													</logic:notEmpty> </font></div>
												</logic:notEmpty>
												
												
													
												</td>
												<td width="11%" align="right">
																								
												<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
												<div align="right" class="style9"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
													name="contavaloreshelper" property="conta">
													<logic:notEqual name="contavaloreshelper"
														property="conta.valorCreditos" value="0">
														<a href="javascript:redirecionarSubmit('exibirConsultarCreditoRealizadoAction.do?caminhoRetornoTelaConsultaCreditoRealizado=exibirAdicionarMatriculaImovelNegativacaoAction&contaID=<bean:define name="contavaloreshelper"	property="conta" id="conta" />
																	<bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
														<bean:write name="contavaloreshelper"
															property="conta.valorCreditos" formatKey="money.format" />
														</a>
													</logic:notEqual>
													<logic:equal name="contavaloreshelper"
														property="conta.valorCreditos" value="0">
														<bean:write name="contavaloreshelper"
															property="conta.valorCreditos" formatKey="money.format" />
													</logic:equal>
												</logic:notEmpty> </font></div>
												</logic:empty>

												<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
													<div align="right" class="style9"><font color="#ff0000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
													name="contavaloreshelper" property="conta">
													<logic:notEqual name="contavaloreshelper"
														property="conta.valorCreditos" value="0">
													    <a href="javascript:redirecionarSubmit('exibirConsultarCreditoRealizadoAction.do?caminhoRetornoTelaConsultaCreditoRealizado=exibirAdicionarMatriculaImovelNegativacaoAction&contaID=<bean:define name="contavaloreshelper"	property="conta" id="conta" />
																	<bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
														<font color="#ff0000"><bean:write name="contavaloreshelper"
															property="conta.valorCreditos" formatKey="money.format" /></font>
														</a>
													</logic:notEqual>
													<logic:equal name="contavaloreshelper"
														property="conta.valorCreditos" value="0">
														<bean:write name="contavaloreshelper"
															property="conta.valorCreditos" formatKey="money.format" />
													</logic:equal>
												</logic:notEmpty> </font></div>
												</logic:notEmpty>
												
												
												
												
												</td>																																																																																																																											
												
												<td width="11%" align="right">
																								
													<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
														  <font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														    <logic:notEmpty	name="contavaloreshelper" property="conta">
																<bean:define name="contavaloreshelper" property="conta"	id="conta" />
																<bean:write name="conta" property="valorImposto" formatKey="money.format" />
															</logic:notEmpty> 
														  </font>
														</div>
													</logic:empty>
	
													<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
														  <font color="#ff0000"	style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														    <logic:notEmpty	name="contavaloreshelper" property="conta">
															  <bean:define name="contavaloreshelper" property="conta"	id="conta" />
															  <bean:write name="conta" property="valorImposto" formatKey="money.format" />
														    </logic:notEmpty> 
														  </font>
														</div>
													</logic:notEmpty>
													
												</td>
												
												<td width="9%" align="right">
																								
													<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
													<div align="right" class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
														name="contavaloreshelper" property="conta">
														<bean:define name="contavaloreshelper" property="conta"
															id="conta" />
														<bean:write name="conta" property="valorTotal"
															formatKey="money.format" />
													</logic:notEmpty> </font></div>
													</logic:empty>
	
													<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9"><font color="#ff0000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
														name="contavaloreshelper" property="conta">
														<bean:define name="contavaloreshelper" property="conta"
															id="conta" />
														<bean:write name="conta" property="valorTotal"
															formatKey="money.format" />
													</logic:notEmpty> </font></div>
													</logic:notEmpty>
												</td>
												
												<td width="9%" align="right">
												
																								
												<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
										<div align="right" class="style9"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEqual
													name="contavaloreshelper" property="valorTotalContaValores"
													value="0">
													<a href="javascript:redirecionarSubmit('exibirValorAtualizacaoConsultarPopupAction.do?caminhoRetornoTelaConsultaAcrescimos=exibirAdicionarMatriculaImovelNegativacaoAction&multa=<bean:write name="contavaloreshelper" property="valorMulta" />&juros=<bean:write name="contavaloreshelper" property="valorJurosMora" />&atualizacao=<bean:write name="contavaloreshelper" property="valorAtualizacaoMonetaria" />', 300, 650);">
													<bean:write name="contavaloreshelper"
														property="valorTotalContaValores" formatKey="money.format" />
													</a>
												</logic:notEqual> <logic:equal name="contavaloreshelper"
													property="valorTotalContaValores" value="0">
													<bean:write name="contavaloreshelper"
														property="valorTotalContaValores" formatKey="money.format" />
												</logic:equal> </font></div>
												</logic:empty>

												<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
													<div align="right" class="style9"><font color="#ff0000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEqual
													name="contavaloreshelper" property="valorTotalContaValores"
													value="0">
													<a href="javascript:redirecionarSubmit('exibirValorAtualizacaoConsultarPopupAction.do?caminhoRetornoTelaConsultaAcrescimos=exibirAdicionarMatriculaImovelNegativacaoAction&multa=<bean:write name="contavaloreshelper" property="valorMulta" />&juros=<bean:write name="contavaloreshelper" property="valorJurosMora" />&atualizacao=<bean:write name="contavaloreshelper" property="valorAtualizacaoMonetaria" />', 300, 650);">
													<font color="#ff0000"><bean:write name="contavaloreshelper"
														property="valorTotalContaValores" formatKey="money.format" /></font>
													</a>
												</logic:notEqual> <logic:equal name="contavaloreshelper"
													property="valorTotalContaValores" value="0">
													<bean:write name="contavaloreshelper"
														property="valorTotalContaValores" formatKey="money.format" />
												</logic:equal> </font></div>
												</logic:notEmpty>
												
												
												
												</td>
												<td width="7%" align="left">
																								
												<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
										<div align="left" class="style9"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
													name="contavaloreshelper" property="conta">
													<bean:define name="contavaloreshelper" property="conta"
														id="conta" />
													<bean:define name="conta"
														property="debitoCreditoSituacaoAtual"
														id="debitoCreditoSituacaoAtual" />
													<bean:write name="debitoCreditoSituacaoAtual"
														property="descricaoAbreviada" />
												</logic:notEmpty> </font></div>
												</logic:empty>

												<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
													<div align="left" class="style9"><font color="#ff0000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
													name="contavaloreshelper" property="conta">
													<bean:define name="contavaloreshelper" property="conta"
														id="conta" />
													<bean:define name="conta"
														property="debitoCreditoSituacaoAtual"
														id="debitoCreditoSituacaoAtual" />
													<bean:write name="debitoCreditoSituacaoAtual"
														property="descricaoAbreviada" />
												</logic:notEmpty> </font></div>
												</logic:notEmpty>
												
												
												
												</td>
												
											<!--	AQUI-->
												
										<td width="6%" align="left">
																								
										<logic:notEmpty name="contavaloreshelper" property="existeParcelamento">
										  <div align="left" class="style9"><font color="#000000"
										   style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
					 						Sim
										  </font>
										  </div>
										</logic:notEmpty>																																			
										</td>
												
												
												
												
												
											</tr>
										</logic:iterate>
										<logic:notEmpty name="colecaoContaValores">
											<%if (cor.equalsIgnoreCase("#cbe5fe")) {
					cor = "#FFFFFF";%>
											<tr bgcolor="#FFFFFF">
												<%} else {
					cor = "#cbe5fe";%>
											<tr bgcolor="#cbe5fe">
												<%}%>
												<td bgcolor="#90c7fc" align="center">
												<div class="style9" align="center"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong>
												</font></div>
												</td>
												<td align="left">
												<%=((Collection) session.getAttribute("colecaoContaValores")).size()%>
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
												<td align="left">
												<div align="left"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> </font></div>
												</td>
												<td align="left">
												<div align="left"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> </font></div>
												</td>
											</tr>
										</logic:notEmpty>
									</logic:present>
								</table>
								</div>
								</td>
							</tr>
							<%}

			                %>
						</logic:notEmpty>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>				

				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>							
				
				<tr>
					<td colspan="5">
					<table width="100%" align="center" bgcolor="#90c7fc" border="0">
						<tr bordercolor="#79bbfd">
							<td colspan="5" bgcolor="#79bbfd" align="center"><strong>Guias de Pagamento</strong></td>
						</tr>
						<tr bordercolor="#000000">
							<td width="36%" bgcolor="#90c7fc"><div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo do D&eacute;bito</strong> </font></div></td>
							<td width="11%" bgcolor="#90c7fc"><div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Prestação</strong> </font></div></td>
							<td width="13%" bgcolor="#90c7fc"><div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Data de Emiss&atilde;o</strong> </font></div></td>
							<td width="13%" bgcolor="#90c7fc"><div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Data de Vencimento</strong> </font></div></td>
							<td width="27%" bgcolor="#90c7fc"><div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor da Guia de Pagamento</strong> </font></div></td>
						</tr>
						<%cor = "#cbe5fe";%>
						<logic:present name="colecaoGuiaPagamentoValores">
							<logic:iterate name="colecaoGuiaPagamentoValores"
								id="guiapagamentohelper">
								<%if (cor.equalsIgnoreCase("#cbe5fe")) {
							cor = "#FFFFFF";
									%>
								<tr bgcolor="#FFFFFF">
									<%} else {
				cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>
									<td>
									<div align="left" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <a
										href="javascript:redirecionarSubmit('exibirConsultarGuiaPagamentoAction.do?caminhoRetornoTelaConsultaGuiaPagamento=exibirAdicionarMatriculaImovelNegativacaoAction&guiaPagamentoId=<bean:define name="guiapagamentohelper" property="guiaPagamento" id="guiaPagamento" /><bean:write name="guiaPagamento" property="id" />', 600, 800);"><bean:define name="guiaPagamento" property="debitoTipo"	id="debitoTipo" /> <logic:notEmpty name="debitoTipo" property="descricao"><bean:write name="debitoTipo" property="descricao" /></logic:notEmpty> </a> </font></div>
									</td>
									<td>
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="guiapagamentohelper" property="guiaPagamento">
										<bean:define name="guiapagamentohelper"
											property="guiaPagamento" id="guiaPagamento" />
										<bean:write name="guiaPagamento" property="prestacaoFormatada"/>
									</logic:notEmpty> </font></div>
									</td>
									
									
									<td>
									<div align="center" class="style9"><font color="#000000"	style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
										<logic:notEmpty	name="guiapagamentohelper" property="guiaPagamento"> 
										<bean:define name="guiapagamentohelper" property="guiaPagamento" id="guiaPagamento" />
										<bean:write name="guiaPagamento" property="dataEmissao"	formatKey="date.format" />
									</logic:notEmpty> </font></div>
									</td>
									<td>
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="guiapagamentohelper" property="guiaPagamento">
										<bean:define name="guiapagamentohelper"
											property="guiaPagamento" id="guiaPagamento" />
										<bean:write name="guiaPagamento" property="dataVencimento"
											formatKey="date.format" />
									</logic:notEmpty> </font></div>
									</td>
									<td>
									<div align="right" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="guiapagamentohelper" property="guiaPagamento">
										<bean:define name="guiapagamentohelper"
											property="guiaPagamento" id="guiaPagamento" />
										<bean:write name="guiaPagamento" property="valorDebito"
											formatKey="money.format" />
									</logic:notEmpty> </font></div>
									</td>
								</tr>
							</logic:iterate>
							<logic:notEmpty name="colecaoGuiaPagamentoValores">
								<%if (cor.equalsIgnoreCase("#cbe5fe")) {
				cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
				cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>
									<td bgcolor="#90c7fc">
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong>
									</font></div>
									</td>
									<td><%=((Collection) session.getAttribute("colecaoGuiaPagamentoValores")).size()%>
									&nbsp;
									doc(s)</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>
									<div align="right" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorGuiaPagamento")%>
									</font></div>
									</td>
								</tr>
							</logic:notEmpty>
						</logic:present>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>				
				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#90c7fc" border="0">
						<tr bordercolor="#000000">
							<td width="50%" bgcolor="#90c7fc">
							<div align="center" class="style9"><font color="#000000"
								style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor
							Total dos Débitos</strong> </font></div>
							</td>
							<td width="50%" bgcolor="#90c7fc">
							<div align="center" class="style9"><font color="#000000"
								style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor
							Total dos Débitos Atualizado</strong> </font></div>
							</td>
						</tr>
						<%if((session.getAttribute("valorTotalSemAcrescimo")!= null) || (session.getAttribute("valorTotalComAcrescimo") != null)){ %>
							<tr bgcolor="#FFFFFF">
								<td>
								<div align="right" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorTotalSemAcrescimo")!= null?session.getAttribute("valorTotalSemAcrescimo"):""%>
								</font></div>
								</td>
								<td>
								<div align="right" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorTotalComAcrescimo")!=null?session.getAttribute("valorTotalComAcrescimo"):""%>
								</font></div>
								</td>
							</tr>
						<%} %>
					</table>
					</td>
				</tr>
		
	        	<tr> 
	          	  <td height="27" colspan="5"> 
	          		<div align="right"> 
	          		  <logic:present name="atualizar" scope="request">
	              	    <input name="Button" type="button" class="bottonRightCol" value="Atualizar" onclick="validarAtualizarForm();">
	              	  </logic:present>
	          		  <logic:notPresent name="atualizar" scope="request">
	              	    <input name="Button" type="button" class="bottonRightCol" value="Inserir" onclick="validarForm(document.forms[0]);">
	              	  </logic:notPresent>	              	  
	              	  <input name="Button2" type="button" class="bottonRightCol" value="Fechar" onClick="javascript:fechar();">
	            	</div>
	              </td>
	        	</tr>
	      		</table>
	      	</td>
	  	</tr>
	</table>
</html:form>
</body>
</html:html>