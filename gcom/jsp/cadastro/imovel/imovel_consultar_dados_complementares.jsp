<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.cobranca.CobrancaSituacaoHistorico"%>
<%@ page import="gcom.faturamento.FaturamentoSituacaoHistorico"%>
<%@ page import="gcom.cadastro.imovel.ImovelRamoAtividade"%>
<%@ page import="gcom.cadastro.imovel.ContratoHelper" isELIgnored="false"%>
<%@ page import="gcom.util.Util"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>
<html:html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	
	
	<%@ include file="/jsp/util/titulo.jsp"%>
	<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
	<html:javascript staticJavascript="false"  formName="ConsultarImovelActionForm" />

	<script language="JavaScript">
		function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		
		   	var form = document.forms[0];
		
		    if (tipoConsulta == 'imovel') {
		      form.idImovelDadosComplementares.value = codigoRegistro;
		      form.matriculaImovelDadosComplementares.value = descricaoRegistro;
		      form.matriculaImovelDadosComplementares.style.color = "#000000";
			  form.action = 'consultarImovelWizardAction.do?action=exibirConsultarImovelDadosComplementaresAction&indicadorNovo=OK'
			  form.submit();
		    }
		}
	
		function limparForm(){
		   	var form = document.forms[0];
			form.action = 'consultarImovelWizardAction.do?action=exibirConsultarImovelDadosComplementaresAction&limparForm=OK'
			form.submit();
		}

		function teste2(id){
			alert('teste');
			abrirPopup('exibirFotoOcorrenciaCadastroConsultarImovelAction.do?id=' + id, 600, 800);
		}		
		
		function consultarMatricula(matricula){
		   	var form = document.forms[0];
			form.action = 'consultarImovelWizardAction.do?destino=5&action=exibirConsultarImovelDebitosAction&matriculaAssociada='+matricula;
			form.submit();
		}
		
		
		
		function verificaExibicaoRelatorioDadosComplementares(){
			var form = document.forms[0];
			
			if (form.idImovelDadosComplementares.value.length > 0 && form.matriculaImovelDadosComplementares.value.length > 0) {
				toggleBoxCaminho('demodiv',1,'gerarRelatorioDadosComplementaresImovelAction.do');
			} else {
				alert('Informe Imóvel');
			}
		
		}
		
		function habilitaMatricula() {
			var form = document.forms[0];
			
			if (form.idImovelDadosComplementares.value != null && form.matriculaImovelDadosComplementares.value != null && 
				form.matriculaImovelDadosComplementares.value != "" && form.matriculaImovelDadosComplementares.value != "IMÓVEL INEXISTENTE"){
			
				form.idImovelDadosComplementares.disabled = true;
			} else {
				form.idImovelDadosComplementares.disabled = false;
			}
		}
		
		function pesquisarImovel() {
			var form = document.forms[0];
		 	
		 	if (form.idImovelDadosComplementares.disabled ) {
		 		alert("Para realizar uma pesquisa de imóvel é necessário apagar o imóvel atual.")
			} else {
				abrirPopup('exibirPesquisarImovelAction.do', 400, 800)
			}
		}

		function gerarContratoAdesao(){
		   	var form = document.forms[0];

		   	if (form.idImovelDadosComplementares.value != null && form.matriculaImovelDadosComplementares.value != null && 
					form.matriculaImovelDadosComplementares.value != "" && form.matriculaImovelDadosComplementares.value != "IMÓVEL INEXISTENTE") {
			   	form.action = 'gerarRelatorioContratoAdesaoImovelAction.do?matricula='+form.matriculaImovelDadosComplementares.value;
				form.submit();
			} else {
				alert("Para gerar um contrato de adesão, é necessário pesquisar um imóvel.")
			}
			
		}

		</script>
</head>

<body leftmargin="5" topmargin="5" onload="javascript:habilitaMatricula();setarFoco('idImovelDadosComplementares');">

<html:form action="/exibirConsultarImovelAction.do" name="ConsultarImovelActionForm"
	type="gcom.gui.cadastro.imovel.ConsultarImovelActionForm" method="post"
	onsubmit="return validateConsultarImovelActionForm(this);">

	<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_consulta.jsp?numeroPagina=2" />
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
			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<td class="centercoltext" valign="top">
				<p>&nbsp;</p>
				<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="11"><img src="imagens/parahead_left.gif" border="0"></td>
						<td class="parabg">&nbsp;</td>
						<td valign="top" width="11"><img src="imagens/parahead_right.gif" border="0"></td>
					</tr>
				</table>	
				<!--Fim Tabela Reference a Páginação da Tela de Processo-->	
				<p>&nbsp;</p>

				<table border="0" width="100%">
					<tr>
						<td colspan="4">
							<table align="center" bgcolor="#99ccff" border="0" width="100%">
								<tr>
								    <td>
									    <table width="100%" align="center" bgcolor="#99CCFF" border="0">
										    <tr>
											    <td align="left" width="4%">
													<img border="0" width="25" height="25"
														src="<bean:message key="caminho.imagens"/>informacao.gif"
														onmouseover="this.T_BGCOLOR='whitesmoke';this.T_LEFT=true;return escape( '${ConsultarImovelActionForm.hint2}' ); "/>
											    </td>						    						
												<td align="center" width="96%"><strong>Dados do Imóvel<logic:present name="imovelExcluido" scope="request"><font color="#ff0000"> (Excluído)</font></logic:present></strong></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr bgcolor="#cbe5fe">
									<td align="center" width="100%">
										<table border="0" width="100%">
											<tr>
												<td bordercolor="#000000" width="25%">
													<strong>
														Im&oacute;vel:
														<font color="#FF0000">*</font>
													</strong>
												</td>
												<td width="75%" colspan="3">
													<html:text property="idImovelDadosComplementares" maxlength="9" size="9"
														onkeypress="validaEnterComMensagem(event, 'consultarImovelWizardAction.do?action=exibirConsultarImovelDadosComplementaresAction&indicadorNovo=OK','idImovelDadosComplementares','Im&oacute;vel');return isCampoNumerico(event);"
														>
													</html:text>
													
																							<a
														href="javascript:pesquisarImovel();">
													<img width="23" height="21"
														src="<bean:message key="caminho.imagens"/>pesquisa.gif"
														border="0" title="Pesquisar Imóvel"/></a>
													<logic:present name="idImovelDadosComplementaresNaoEncontrado" scope="request">
														<html:text property="matriculaImovelDadosComplementares"
															size="40" readonly="true"
															style="background-color:#EFEFEF; border:0; color: #ff0000" 
															title="Localidade.Setor.Quadra.Lote.Sublote"/>
													</logic:present> 
													<logic:notPresent name="idImovelDadosComplementaresNaoEncontrado" scope="request">
														<logic:present name="valorMatriculaImovelDadosComplementares" scope="request">
															<html:text property="matriculaImovelDadosComplementares"
																size="40" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" 
																title="Localidade.Setor.Quadra.Lote.Sublote"/>
														</logic:present>
														<logic:notPresent name="valorMatriculaImovelDadosComplementares" scope="request">
															<html:text property="matriculaImovelDadosComplementares"
																size="40" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" 
																title="Localidade.Setor.Quadra.Lote.Sublote"/>
														</logic:notPresent>
													</logic:notPresent> 
													<a href="javascript:limparForm();"> 
														<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
															border="0" title="Apagar" />
													</a>
												</td>
											</tr>
											<tr>
												<td height="10">
													<div class="style9">
														<strong>Situa&ccedil;&atilde;o de &Aacute;gua:</strong>
													</div>
												</td>
			
												<td>
													<html:text property="situacaoAguaDadosComplementares"
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000"
														size="15" maxlength="15" />
												</td>
												
												<td width="96"><strong>Situação de Esgoto:</strong></td>
												
												<td width="120">
													<html:text property="situacaoEsgotoDadosComplementares" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000"
														size="15" maxlength="15" />
												</td>			
											</tr>
										</table>
								    </td>
 							    </tr>
						    </table>
						</td>
					</tr>
					<tr>
						<td align="left" height="10" width="28%">
						<div class="style9"><strong>Tarifa de Consumo:</strong></div>
						</td>
	
						<td align="left" width="22%">
	
						<div class="style9"><html:text
							property="tarifaConsumoDadosComplementares" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="20" maxlength="20" /></div>
						</td>
	
						<td align="left" width="32%"><strong>Quantidade de
						Retifica&ccedil;&otilde;es:</strong></td>
	
						<td align="left" width="18%"><html:text
							property="quantidadeRetificacoesDadosComplementares"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="20" maxlength="20" /></td>
	
					</tr>
	
					<tr>
	
						<td align="left"><strong>Qtd. Parcelamentos:</strong></td>
	
						<td align="left"><html:text
							property="quantidadeParcelamentosDadosComplementares"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="20" maxlength="20" /></td>
	
						<td align="left"><strong>Qtd.Reparcelamentos:</strong></td>
	
						<td align="left"><html:text
							property="quantidadeReparcelamentoDadosComplementares"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="20" maxlength="20" /></td>
	
					</tr>
	
					<tr>
	
						<td colspan="2" height="10">
						<div class="style9"><strong>Qtd. Reparcelamentos Consecutivos:</strong></div>
						</td>
	
						<td><html:text
							property="quantidadeReparcelamentoConsecutivosDadosComplementares"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="20" maxlength="20" /></td>
	
						<td>&nbsp;</td>
	
					</tr>
	
					<!-- <tr>
	
						<td align="left"><strong>Situa&ccedil;&atilde;o de Cobran&ccedil;a:</strong></td>
	
						<td colspan="3" align="left"><html:text
							property="situacaoCobrancaDadosComplementares" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="50" maxlength="50" /></td>
	
					</tr> -->
					
	                <tr> 
	                  	<td align="left"><strong>Funcionário Resp.:</strong></td>
	                  	<td colspan="3">
							<html:text property="idFuncionario" readonly="true"
								style="background-color:#EFEFEF; border:0;" size="4"
								maxlength="4" />
							
							<html:text property="nomeFuncionario" readonly="true"
								style="background-color:#EFEFEF; border:0;" size="60"
								maxlength="40" />
						</td>
	             	</tr>
	             	<tr> 
	                  	<td align="left"><strong>Informações Complementares:</strong></td>
	                  	<td colspan="3">
							<html:textarea property="informacoesComplementares" readonly="true"
								style="background-color:#EFEFEF; border:0;" cols="50" rows="4"/>
						</td>
	             	</tr>
	             		
	             		
	             		
           		  <!-- Inicio da tabela de Situação de Cobranca - Vivianne Sousa -->
				  <tr>
					<td colspan="7">
					<table width="100%" align="center" bgcolor="#90c7fc" border="0">
					
						<tr bordercolor="#79bbfd">
							<td colspan="7" bgcolor="#79bbfd" align="center"><strong>Situações de Cobrança</strong></td>
						</tr>
						
						<tr bordercolor="#000000">
						
							<td width="20%" bgcolor="#90c7fc">
							<div align="left" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								<strong>Descrição</strong> </font></div>
							</td>
							<td width="10%" bgcolor="#90c7fc">
							<div align="left" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								<strong>Referência</strong> </font></div>
							</td>
							<td width="10%" bgcolor="#90c7fc">
							<div align="left" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								<strong>Data de Implantação</strong> </font></div>
							</td>
							<td width="10%" bgcolor="#90c7fc">
							<div align="left" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								<strong>Data da Retirada</strong> </font></div>
							</td>
							<td width="10%" bgcolor="#90c7fc">
							<div align="left" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								<strong>Cliente Alvo</strong> </font></div>
							</td>
							<td width="20%" bgcolor="#90c7fc">
							<div align="left" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								<strong>Escritório Cobrança</strong> </font></div>
							</td>
							<td width="20%" bgcolor="#90c7fc">
							<div align="left" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								<strong>Advog. Resp. Cobrança</strong> </font></div>
							</td>
						</tr>
						
						<logic:notEmpty name="colecaoDadosImovelCobrancaSituacao" scope="session">
						<%if (((Collection) session.getAttribute("colecaoDadosImovelCobrancaSituacao")).size() <= 3) {%>
					
						<%String cor = "#cbe5fe";%>
						<logic:present name="colecaoDadosImovelCobrancaSituacao">
							<logic:iterate name="colecaoDadosImovelCobrancaSituacao" id="imovelCobrancaSituacaoHelper">
								<%if (cor.equalsIgnoreCase("#cbe5fe")) {
									cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
									cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>

									<td width="20%">
										<div align="left" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<bean:write name="imovelCobrancaSituacaoHelper" property="descricaoSituacaoCobranca" />
										</font></div>
									</td>
									<td width="10%">
										<div align="left" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																									
										<logic:notEqual name="imovelCobrancaSituacaoHelper" property="anoMesReferenciaInicio" value="0">
											<bean:write name="imovelCobrancaSituacaoHelper" property="anoMesReferenciaInicio" />
											a
											<bean:write name="imovelCobrancaSituacaoHelper" property="anoMesReferenciaFinal" />
										</logic:notEqual>
										</font></div>
									</td>
									<td width="10%">
										<div align="left" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<bean:write name="imovelCobrancaSituacaoHelper" property="dataImplantacaoCobranca" formatKey="date.format"/>
										</font></div>
									</td>
									<td width="10%">
										<div align="left" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<bean:write name="imovelCobrancaSituacaoHelper" property="dataRetiradaCobranca" formatKey="date.format"/>
										</font></div>
									</td>
									<td width="10%">
										<div align="left" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
											<a
												href="javascript:abrirPopup('exibirConsultarClienteAction.do?desabilitarPesquisaCliente=SIM&codigoCliente='+<bean:write name="imovelCobrancaSituacaoHelper" property="idClienteAlvo" />, 500, 800);">
											<bean:write name="imovelCobrancaSituacaoHelper" property="idClienteAlvo" />
											</a>
										</font></div>
									</td>
									<td width="20%">
										<div align="left" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<bean:write name="imovelCobrancaSituacaoHelper" property="escritorioCobranca" />
										</font></div>
									</td>
									<td width="20%">
										<div align="left" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<bean:write name="imovelCobrancaSituacaoHelper" property="advogadoResponsavelCobranca" />
										</font></div>
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
						
						
						<%} else {%>
						<tr>
							<td height="75" colspan="7">
								<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%">
												
										<%String cor = "#cbe5fe";%>
										<logic:present name="colecaoDadosImovelCobrancaSituacao">
											<logic:iterate name="colecaoDadosImovelCobrancaSituacao" id="imovelCobrancaSituacaoHelper">
												<%if (cor.equalsIgnoreCase("#cbe5fe")) {
													cor = "#FFFFFF";%>
												<tr bgcolor="#FFFFFF">
													<%} else {
													cor = "#cbe5fe";%>
												<tr bgcolor="#cbe5fe">
													<%}%>
													<td width="20%">
														<div align="left" class="style9">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<bean:write name="imovelCobrancaSituacaoHelper" property="descricaoSituacaoCobranca" />
														</font></div>
													</td>
													<td width="10%">
														<div align="left" class="style9">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														
														<logic:notEqual name="imovelCobrancaSituacaoHelper" property="anoMesReferenciaInicio" value="0">
															<bean:write name="imovelCobrancaSituacaoHelper" property="anoMesReferenciaInicio" />
															a
															<bean:write name="imovelCobrancaSituacaoHelper" property="anoMesReferenciaFinal" />
														</logic:notEqual>
															
														</font></div>
													</td>
													<td width="10%">
														<div align="left" class="style9">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<bean:write name="imovelCobrancaSituacaoHelper" property="dataImplantacaoCobranca" formatKey="date.format"/>
														</font></div>
													</td>
													<td width="10%">
														<div align="left" class="style9">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<bean:write name="imovelCobrancaSituacaoHelper" property="dataRetiradaCobranca" formatKey="date.format"/>
														</font></div>
													</td>
													<td width="10%">
														<div align="left" class="style9">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
															<a
																href="javascript:abrirPopup('exibirConsultarClienteAction.do?desabilitarPesquisaCliente=SIM&codigoCliente='+<bean:write name="imovelCobrancaSituacaoHelper" property="idClienteAlvo" />, 500, 800);">
															<bean:write name="imovelCobrancaSituacaoHelper" property="idClienteAlvo" />
															</a>
														</font></div>
													</td>
													<td width="20%">
														<div align="left" class="style9">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<bean:write name="imovelCobrancaSituacaoHelper" property="escritorioCobranca" />
														</font></div>
													</td>
													<td width="20%">
														<div align="left" class="style9">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<bean:write name="imovelCobrancaSituacaoHelper" property="advogadoResponsavelCobranca" />
														</font></div>
													</td>
													
												</tr>
											</logic:iterate>
										</logic:present>		 							
			
			
									</table>
								</div>
							</td>						
						</tr>						
						<%}%>
						
						</logic:notEmpty>
							
					</table>
					</td>
				</tr>
				<!-- Fim da tabela de Situação de Cobranca - Vivianne Sousa -->
				
				
				
				  <!-- Inicio da tabela de Negativações - Vivianne Sousa -->
				  <tr>
					<td colspan="7">
					<table width="100%" align="center" bgcolor="#90c7fc" border="0">
					
						<tr bordercolor="#79bbfd">
							<td colspan="4" bgcolor="#79bbfd" align="center"><strong>Negativações</strong></td>
						</tr>
						
						
						<tr bordercolor="#000000">
							<td width="40%" rowspan="2" bgcolor="#90c7fc">
							<div align="left" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								<strong>Negativador</strong> </font></div>
							</td>
							
							<td>
							<td width="60%" colspan="3" bgcolor="#90c7fc">
							<div align="left" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								<strong>Inclusões Aceitas</strong> </font></div>
							</td>
						</tr>
						<tr bordercolor="#FFFFFF" bgcolor="#cbe5fe">
							<td width="20%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
							<div align="center"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"><strong>Total</strong></font></div>
							</td>
							
							<td width="20%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
							<div align="center"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"><strong>Não Confirmadas</strong></font></div>
							</td>
							
							
							<td width="20%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
							<div align="center"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"><strong>Confirmadas</strong></font></div>
							</td>
						</tr>

						
						<logic:notEmpty name="colecaoDadosNegativadorMovimentoReg" scope="session">
						<%String cor = "#cbe5fe";%>
						<logic:present name="colecaoDadosNegativadorMovimentoReg">
							<logic:iterate name="colecaoDadosNegativadorMovimentoReg" id="negaticacaoAceitaHelper">
								<%if (cor.equalsIgnoreCase("#cbe5fe")) {
									cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
									cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>

									<td width="40%">
										<div align="left" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<bean:write name="negaticacaoAceitaHelper" property="nomeNegativador" />
										</font></div>
									</td>
									<td width="20%">
										<div align="center" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<bean:write name="negaticacaoAceitaHelper" property="totalInclusoes" />
										</font></div>
									</td>
									<td width="20%">
										<div align="center" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<bean:write name="negaticacaoAceitaHelper" property="inclusoesNaoConfirmadas" />
										</font></div>
									</td>
									<td width="20%">
										<div align="center" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<bean:write name="negaticacaoAceitaHelper" property="inclusoesConfirmadas" />
										</font></div>
									</td>

								</tr>
							</logic:iterate>
						</logic:present>
						
						</logic:notEmpty>
							
					</table>
					</td>
				</tr>
				<!-- Fim da tabela de Negativações - Vivianne Sousa -->
				
	             		
	             		
	             		
					<tr>
	
						<td colspan="4" align="left">
						<table bgcolor="#90c7fc" width="100%" border="0">
							<tr>
	
								<td colspan="6" align="center" bgcolor="#79bbfd"><strong>Vencimentos
								Alternativos </strong></td>
	
							</tr>
	
							<tr bgcolor="#90c7fc">
	
								<td colspan="2" align="center" bgcolor="#90c7fc">
								<div class="style9"><font style="font-size: 9px;" color="#000000"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Dia do
								Vencimento</strong></font></div>
								</td>
	
								<td colspan="3" align="center" bgcolor="#90c7fc">
								<div class="style9"><font style="font-size: 9px;" color="#000000"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Data
								Implanta&ccedil;&atilde;o</strong> </font></div>
								</td>
	
								<td align="center" bgcolor="#90c7fc">
								<div class="style9"><font style="font-size: 9px;" color="#000000"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Data
								Exclus&atilde;o</strong></font></div>
								</td>
	
							</tr>
							<tr>
								<td colspan="6" width="100%">
								<div style="width: 100%; height: 100%; overflow: auto;"><!--corpo da segunda tabela-->
								<%int cont = 0;%> <logic:notEmpty
									name="colecaoVencimentosAlternativos" scope="session">
									<table width="100%" align="left" border="0">
	
										<logic:iterate name="colecaoVencimentosAlternativos"
											id="vencimentosAlternativos">
											<%cont = cont + 1;
				if (cont % 2 == 0) {%>
											<tr bgcolor="#cbe5fe">
												<%} else {%>
											<tr bgcolor="#FFFFFF">
												<%}%>
												<td colspan="2" align="center" width="36%">
												<div class="style9"><font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:write name="vencimentosAlternativos"
													property="dateVencimento" /></font></div>
												</td>
	
												<td colspan="3" align="center" width="37%">
												<div class="style9"><font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:write name="vencimentosAlternativos"
													property="dataImplantacao" formatKey="date.format" /></font></div>
												</td>
	
												<td align="center" width="29%">
												<div class="style9"><font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:write name="vencimentosAlternativos"
													property="dateExclusao" formatKey="date.format" /></font></div>
												</td>
											</tr>
										</logic:iterate>
	
									</table>
								</logic:notEmpty></div>
								</td>
							</tr>
	
	
						</table>
						<br>
						<table bgcolor="#90c7fc" width="100%"
							border="0">
							<tr>
	
								<td colspan="6" bordercolor="#79bbfd" align="center"
									bgcolor="#79bbfd"><strong>D&eacute;bito Autom&aacute;tico</strong></td>
	
							</tr>
	
							<tr bgcolor="#90c7fc">
	
								<td align="center" bgcolor="#90c7fc" width="26%">
								<div class="style9"><font style="font-size: 9px;" color="#000000"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Banco</strong></font></div>
								</td>
	
								<td align="center" bgcolor="#90c7fc" width="8%"><font
									style="font-size: 9px;" color="#000000"
									face="Verdana, Arial, Helvetica, sans-serif"><strong>Ag&ecirc;ncia</strong></font></td>
	
								<td align="center" bgcolor="#90c7fc" width="22%">
								<div class="style9"><font style="font-size: 9px;" color="#000000"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Ident.Cliente
								no Banco</strong> </font></div>
								</td>
	
								<td align="center" bgcolor="#90c7fc" width="12%"><font
									style="font-size: 9px;" color="#000000"
									face="Verdana, Arial, Helvetica, sans-serif"><strong>Data
								Op&ccedil;&atilde;o</strong></font></td>
	
								<td align="center" bgcolor="#90c7fc" width="18%"><font
									style="font-size: 9px;" color="#000000"
									face="Verdana, Arial, Helvetica, sans-serif"><strong>Data
								Implanta&ccedil;&atilde;o</strong></font></td>
	
								<td align="center" bgcolor="#90c7fc">
								<div class="style9"><font style="font-size: 9px;" color="#000000"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Data
								Exclus&atilde;o</strong></font></div>
								</td>
	
							</tr>
	
	
							<tr>
								<td colspan="6">
								<div style="width: 100%; height: 100%; overflow: auto;">
								<table width="100%" align="left" bgcolor="#99CCFF">
									<!--corpo da segunda tabela-->
									<%cont = 0;%>
									<logic:notEmpty name="colecaoDebitosAutomaticos" scope="session">
										<logic:iterate name="colecaoDebitosAutomaticos"
											id="debitosAutomaticos">
											<%cont = cont + 1;
				if (cont % 2 == 0) {%>
											<tr bgcolor="#cbe5fe">
												<%} else {%>
											<tr bgcolor="#FFFFFF">
												<%}%>
	
	
												<td align="center" width="26%">
												<div class="style9"><font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif"><logic:present name="debitosAutomaticos"
													property="agencia">
													<bean:define name="debitosAutomaticos" property="agencia" id="agencia" />
	
													<logic:present name="agencia" property="banco">
	
														<bean:define name="agencia" property="banco" id="banco" />
														
														<bean:write name="banco" property="descricaoAbreviada" />
													</logic:present>	
														
	
												</logic:present></font></div>
												</td>
	
												<td align="center" width="8%"><font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
												<logic:present name="debitosAutomaticos" property="agencia">
													<bean:write name="debitosAutomaticos"
														property="agencia.codigoAgencia" />
												</logic:present></font></td>
	
												<td align="center" width="22%">
												<div class="style9"><font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:write name="debitosAutomaticos"
													property="identificacaoClienteBanco" /> </font></div>
												</td>
	
												<td align="center" width="12%"><font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:write name="debitosAutomaticos"
													property="dataOpcaoDebitoContaCorrente"
													formatKey="date.format" /></font></td>
	
												<td align="center" width="18%"><font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:write name="debitosAutomaticos"
													property="dataInclusaoNovoDebitoAutomatico"
													formatKey="date.format" /></font></td>
	
												<td align="center">
												<div class="style9"><font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:write name="debitosAutomaticos" property="dataExclusao"
													formatKey="date.format" /></font></div>
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
								</div>
								</td>
							</tr>
	
						</table>
						<br/>
						
						    <table width="100%" align="center" bgcolor="#90c7fc" border="0">
								<tr bordercolor="#79bbfd">
									<td colspan="3" align="center" bgcolor="#79bbfd">
										<strong>Ocorr&ecirc;ncias de Cadastro</strong>
									</td>
								</tr>
								<tr bordercolor="#000000">
									<td width="65%" bgcolor="#90c7fc" align="center">
										<div class="style9">
											<font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> 
													<strong>Ocorr&ecirc;ncia</strong></font>
										</div>
									</td>
	
									<td width="17%" align="center" bgcolor="#90c7fc">
										<div class="style9">
											<font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> 
													<strong>Data </strong></font>
										</div>
										<div class="style9">
											<font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"></font>
										</div>
									</td>
									<td width="18%" align="center" bgcolor="#90c7fc">
										<span class="style9">
											<font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif">
													<strong>Foto</strong>
											</font>
										</span>
									</td>
								</tr>
								<%String cor1 = "#FFFFFF";%>
								
								<logic:present name="colecaoImovelCadastroOcorrencia" scope="session">
									<logic:iterate name="colecaoImovelCadastroOcorrencia" id="imovelCadastroOcorrencia">
										<%if (cor1.equalsIgnoreCase("#FFFFFF")) {
											cor1 = "#cbe5fe";%>
										<tr bgcolor="#FFFFFF">
											<%} else {
												cor1 = "#FFFFFF";%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td bordercolor="#90c7fc" align="left">
												<font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
														<bean:write name="imovelCadastroOcorrencia" property="cadastroOcorrencia.descricao"/>
												</font>
											</td>
											<td align="center">
												<font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
														<bean:write name="imovelCadastroOcorrencia" property="dataOcorrencia" formatKey="date.format"/>
												</font>
											</td>
											<td align="center">
												<logic:notEmpty name="imovelCadastroOcorrencia" property="fotoOcorrencia">
													<a href="javascript:abrirPopup('exibirFotoOcorrenciaCadastroConsultarImovelAction.do?id=<bean:write name="imovelCadastroOcorrencia" property="id"/>', 400, 800);">
														<img width="18" height="18"
															src="<bean:message key="caminho.imagens"/>imgfolder.gif" border="0" />
													</a> 
												</logic:notEmpty>
											</td>
									    </tr>
									</logic:iterate>
								</logic:present>
							</table>
							
							<br/>
							
							<table width="100%" align="center" bgcolor="#90c7fc" border="0">
								<tr bordercolor="#79bbfd">
									<td colspan="3" align="center" bgcolor="#79bbfd">
										<strong>Fotos</strong>
									</td>
								</tr>
								<tr bordercolor="#000000">
									<td width="75%" bgcolor="#90c7fc" align="center">
										<div class="style9">
											<font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> 
													<strong>Nome da foto</strong></font>
										</div>
									</td>
	
									<td width="25%" align="center" bgcolor="#90c7fc">
										<span class="style9">
											<font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif">
													<strong>Foto</strong>
											</font>
										</span>
									</td>
								</tr>
								<%String cor10 = "#FFFFFF";%>
								
								<logic:present name="colecaoImagens" scope="session">
									<logic:iterate name="colecaoImagens" id="imagem">
										<%if (cor10.equalsIgnoreCase("#FFFFFF")) {
											cor10 = "#cbe5fe";%>
										<tr bgcolor="#FFFFFF">
											<%} else {
												cor10 = "#FFFFFF";%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td bordercolor="#90c7fc" align="left">
												<font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
														<bean:write name="imagem" property="nomeImagem"/>
												</font>
											</td>
											<td align="center">
												<logic:notEmpty name="imagem" property="caminhoImagem">
													<a href="javascript:abrirPopup('exibirImovelImagemAction.do?id=<bean:write name="imagem" property="id"/>', 400, 800);">
														<img width="18" height="18"
															src="<bean:message key="caminho.imagens"/>imgfolder.gif" border="0" />
													</a> 
												</logic:notEmpty>
											</td>
									    </tr>
									</logic:iterate>
								</logic:present>
							</table>
						
						<br>
						<table width="100%" align="center" bgcolor="#90c7fc" border="0">
						<tr bordercolor="#79bbfd">
							<td colspan="3" align="center" bgcolor="#79bbfd"><strong>Anormalidades de Localidade Pólo: </strong></td>
						</tr>
						
								<tr bordercolor="#000000">
									<td width="65%" bgcolor="#90c7fc" align="center">
									<div class="style9"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Anormalidade</strong></font></div>
									</td>
	
									<td width="17%" bgcolor="#90c7fc" align="center">
									<div class="style9"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong> Data </strong>
									</font></div>
									</td>
									<td width="18%" bgcolor="#90c7fc" align="center"><span
										class="style9"> <font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong> Foto </strong>
									</font> </span></td>
								</tr>
								<%String cor2 = "#FFFFFF";%>
								<logic:present name="colecaoImovelEloAnormalidade" scope="session">
									<logic:iterate name="colecaoImovelEloAnormalidade" id="imovelEloAnormalidade">
											<%if (cor2.equalsIgnoreCase("#FFFFFF")) {
					cor2 = "#cbe5fe";%>
											<tr bgcolor="#FFFFFF">
											
												<%} else {
					cor2 = "#FFFFFF";%>
											<tr bgcolor="#cbe5fe">
												<%}%>
									<td align="left"><font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="imovelEloAnormalidade" property="eloAnormalidade.descricao"/></font></td>
									<td align="center">
									<font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="imovelEloAnormalidade" property="dataAnormalidade" formatKey="date.format"/></font>
									</td>
									<td align="center">
									<logic:notEmpty name="imovelEloAnormalidade" property="fotoAnormalidade">
										<a href="javascript:abrirPopup('exibirFotoAnormalidadeEloAction.do?id=<bean:write name="imovelEloAnormalidade" property="id"/>', 600, 800);">
											<img width="18" height="18"
												src="<bean:message key="caminho.imagens"/>imgfolder.gif" border="0" />
										</a>
									</logic:notEmpty>
									</td>
								</tr>
								</logic:iterate>
								</logic:present>
							</table>
						
						
						
						<br>
						<table bgcolor="#90c7fc" width="100%"
	
							border="0">
							<tr>
								<td colspan="7" bordercolor="#79bbfd" align="center"
									bgcolor="#79bbfd"><strong>Situa&ccedil;&otilde;es Especiais de
								Faturamento </strong></td>
							</tr>
							<tr bgcolor="#90c7fc">
								<td align="center" bgcolor="#90c7fc" width="24%">
								<div class="style9"><font style="font-size: 9px;" color="#000000"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo</strong></font></div>
								</td>
								
								<td align="center" bgcolor="#90c7fc" width="18%"><font
									style="font-size: 9px;" color="#000000"
									face="Verdana, Arial, Helvetica, sans-serif"><strong>Motivo</strong></font></td>
								
								<td align="center" bgcolor="#90c7fc" width="14%">
								<div class="style9"><font style="font-size: 9px;" color="#000000"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano
								In&iacute;cio&nbsp;</strong> </font></div>
								</td>
								
								<td align="center" bgcolor="#90c7fc" width="11%">
								<div class="style9"><font style="font-size: 9px;" color="#000000"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano
								Fim</strong> </font></div>
								</td>
								
								<td align="center" bgcolor="#90c7fc" width="14%">
								<div class="style9"><font style="font-size: 9px;" color="#000000"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano
								Retirada</strong> </font></div>
								</td>
								
								<td align="center" bgcolor="#90c7fc" width="6%">
								<div class="style9"><font style="font-size: 9px;" color="#000000"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Data inclusão</strong></font></div>
								</td>
								<td align="center" bgcolor="#90c7fc">
								<div class="style9"><font style="font-size: 9px;" color="#000000"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Usu&aacute;rio</strong></font></div>
								</td>
							</tr>
	
							<tr>
								<td colspan="7">
								<div style="width: 100%; height: 100%; overflow: auto;">
								<table width="100%" align="left" bgcolor="#99CCFF">
									<!--corpo da segunda tabela-->
									<%cont = 0;%>
									<logic:notEmpty name="colecaoFaturamentosSituacaoHistorico" scope="session">
									
										<logic:iterate name="colecaoFaturamentosSituacaoHistorico"
											id="faturamentosSituacaoHistorico"
											type="FaturamentoSituacaoHistorico">
											<%cont = cont + 1;
				if (cont % 2 == 0) {%>
											<tr bgcolor="#cbe5fe">
												<%} else {%>
											<tr bgcolor="#FFFFFF">
												<%}%>
	
												<td align="left" width="26%">
												<div class="style9"><font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
	
	
												<logic:present name="faturamentosSituacaoHistorico"
													property="faturamentoSituacaoTipo">
													
													<a href="javascript:abrirPopup('exibirConsultarSituacaoEspecialFaturamentoPopupAction.do?idFaturamentoSituacaoHistorico=<bean:write name="faturamentosSituacaoHistorico" property="id"/>', 600, 800)">
															<bean:write name="faturamentosSituacaoHistorico"
																	property="faturamentoSituacaoTipo.descricao"/>
													</a>
	
												</logic:present> </font></div>
												</td>
												<td align="center" width="18%">
												<font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
												<logic:present name="faturamentosSituacaoHistorico"
													property="faturamentoSituacaoMotivo">
	
													<%if (cont % 2 == 0) {%>
													<bean:write
																name="faturamentosSituacaoHistorico"
																property="faturamentoSituacaoMotivo.descricao"/>
													<%} else {%>
													<bean:write
																name="faturamentosSituacaoHistorico"
																property="faturamentoSituacaoMotivo.descricao"/>
													<%}%>
	
												</logic:present> </font></td>
	
	
												<td align="center" width="12%">
												<div class="style9"><font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
												<%=""
								+ Util
										.formatarMesAnoReferencia(faturamentosSituacaoHistorico
												.getAnoMesFaturamentoSituacaoInicio())%>
												</font></div>
												</td>
												<td align="center" width="12%">
												<font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
												<%=""
								+ Util
										.formatarMesAnoReferencia(faturamentosSituacaoHistorico
												.getAnoMesFaturamentoSituacaoFim())%>
												</font></td>
												
												<td align="center" width="12%">
												<font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
												<% if(faturamentosSituacaoHistorico.getAnoMesFaturamentoRetirada() != null){ %>
												<%=""
								+ Util
										.formatarMesAnoReferencia(faturamentosSituacaoHistorico
												.getAnoMesFaturamentoRetirada())%>
												<%} %>
												
												</font></td>
												
												<td align="center" width="10%">
												<div class="style9"><font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
												<logic:present name="faturamentosSituacaoHistorico"
													property="dataInclusao">
													<bean:write name="faturamentosSituacaoHistorico"
														property="dataInclusao" formatKey="date.format"/>
												</logic:present></font></div>
												</td>
												
												<td align="center">
												<div class="style9"><font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
												<logic:present name="faturamentosSituacaoHistorico"
													property="usuario">
													<bean:write name="faturamentosSituacaoHistorico"
														property="usuario.nomeUsuario" />
												</logic:present></font></div>
												</td>
	
	
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
								</div>
								</td>
							</tr>
	
						</table>
						<br>
						<table bgcolor="#90c7fc" width="100%"
							border="0">
							<tr>
								<td colspan="6" align="center" bgcolor="#79bbfd"><strong>Situa&ccedil;&otilde;es
								Especiais de Cobran&ccedil;a </strong></td>
							</tr>
							<tr bordercolor="#000000" bgcolor="#90c7fc">
								<td align="center" bgcolor="#90c7fc" width="26%">
								<div class="style9"><font style="font-size: 9px;" color="#000000"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo</strong></font></div>
								</td>
								<td align="center" bgcolor="#90c7fc" width="18%"><font
									style="font-size: 9px;" color="#000000"
									face="Verdana, Arial, Helvetica, sans-serif"><strong>Motivo</strong></font></td>
								<td align="center" bgcolor="#90c7fc" width="12%">
								<div class="style9"><font style="font-size: 9px;" color="#000000"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano
								In&iacute;cio&nbsp;</strong> </font></div>
								</td>
								<td align="center" bgcolor="#90c7fc" width="12%"><font
									style="font-size: 9px;" color="#000000"
									face="Verdana, Arial, Helvetica, sans-serif"><strong>M&ecirc;s/Ano
								Fim</strong></font></td>
								<td align="center" bgcolor="#90c7fc" width="18%"><font
									style="font-size: 9px;" color="#000000"
									face="Verdana, Arial, Helvetica, sans-serif"><strong>M&ecirc;s/Ano
								Retirada</strong></font></td>
								<td align="center" bgcolor="#90c7fc">
								<div class="style9"><font style="font-size: 9px;" color="#000000"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Usu&aacute;rio</strong></font></div>
								</td>
							</tr>
	
	
	
							<tr>
								<td colspan="6">
								<div style="width: 100%; height: 100%; overflow: auto;">
								<table width="100%" align="left" bgcolor="#99CCFF">
									<!--corpo da segunda tabela-->
									<%cont = 0;%>
									<logic:notEmpty name="colecaoCobrancasSituacaoHistorico" scope="session">
										<logic:iterate name="colecaoCobrancasSituacaoHistorico"
											id="cobrancasSituacaoHistorico"
											type="CobrancaSituacaoHistorico">
											<%cont = cont + 1;
				if (cont % 2 == 0) {%>
											<tr bgcolor="#cbe5fe">
												<%} else {%>
											<tr bgcolor="#FFFFFF">
												<%}%>
												<td align="center" width="26%">
												<div class="style9"><font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
	
												<logic:present name="cobrancasSituacaoHistorico"
													property="cobrancaSituacaoTipo">
													<a href="javascript:abrirPopup('exibirConsultarSituacaoEspecialCobrancaPopupAction.do?idCobrancaSituacaoHistorico=<bean:write name="cobrancasSituacaoHistorico" property="id"/>', 600, 800)">
														<bean:write name="cobrancasSituacaoHistorico"
															property="cobrancaSituacaoTipo.descricao" />
													</a>
												</logic:present> </font></div>
												</td>
												<td align="center" width="18%"><font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
												<logic:present name="cobrancasSituacaoHistorico"
													property="cobrancaSituacaoMotivo">
	
													<bean:write name="cobrancasSituacaoHistorico"
														property="cobrancaSituacaoMotivo.descricao" />
												</logic:present> </font></td>
	
	
												<td align="center" width="12%">
												<div class="style9"><font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
												<%=""
						+ Util.formatarMesAnoReferencia(cobrancasSituacaoHistorico
								.getAnoMesCobrancaSituacaoInicio())%>
												</font></div>
												</td>
												<td align="center" width="12%"><font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
	
												<%=""
						+ Util.formatarMesAnoReferencia(cobrancasSituacaoHistorico
								.getAnoMesCobrancaSituacaoFim())%>
												</font></td>
												<td align="center" width="18%"><font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
												<%if (cobrancasSituacaoHistorico.getAnoMesCobrancaRetirada() != null) {%>
												<%=""
									+ Util
											.formatarMesAnoReferencia(cobrancasSituacaoHistorico
													.getAnoMesCobrancaRetirada())%>
												<%}%> </font></td>
	
	
												<td align="center">
												<div class="style9"><font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
												<logic:present name="cobrancasSituacaoHistorico"
													property="usuario">
													<bean:write name="cobrancasSituacaoHistorico"
														property="usuario.nomeUsuario" />
												</logic:present></font></div>
												</td>
	
	
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
				<p>&nbsp;</p>

			<table bgcolor="#90c7fc" width="100%"
						border="0">
			  <tr>
					<td colspan="6" align="center" bgcolor="#79bbfd">
					   <strong>Ramos de Atividades do Imóvel</strong>
					</td>
			  </tr>
			  <tr bordercolor="#000000" bgcolor="#90c7fc">
							<td align="center" bgcolor="#90c7fc" width="15%">
							<div class="style9"><font style="font-size: 9px;" color="#000000"
								face="Verdana, Arial, Helvetica, sans-serif"><strong>Código</strong></font></div>
							</td>
							<td align="center" bgcolor="#90c7fc">
								<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, 
												Helvetica, sans-serif">
												<strong>Descrição</strong>
								</font>
							</td>
				</tr>
				<tr>
							<td colspan="6">
							<div style="width: 100%; height: 100%; overflow: auto;">
							<table width="100%" align="left" bgcolor="#99CCFF">
								<!--corpo da segunda tabela-->
								<%int contx = 0;%>
								<logic:notEmpty name="colecaoImovelRamosAtividade" scope="session">
									<logic:iterate name="colecaoImovelRamosAtividade"
										id="idRamoAtividade"
										type="ImovelRamoAtividade">
										<%contx = contx + 1;
										if (contx % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td align="center" width="15%">
												<div class="style9">
													<font style="font-size: 9px;"
														color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
	
																											
														<bean:write name="idRamoAtividade"
																	property="comp_id.ramo_atividade.id" />												
														
													 </font>
												</div>
											</td>
											<td align="left">
												<font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:write name="idRamoAtividade"
															property="comp_id.ramo_atividade.descricao" />													
												</font>
											</td>
								    	</tr>
									</logic:iterate>
								</logic:notEmpty>
							</table>
							</div>
							</td>
						</tr>
				
			</table>
			<p>&nbsp;</p>
						
			<table bgcolor="#90c7fc" width="100%"
						border="0">
			  <tr>
					<td colspan="6" align="center" bgcolor="#79bbfd">
					   <strong>Dados do Contrato</strong>
					</td>
			  </tr>
			  	<tr bordercolor="#000000" bgcolor="#90c7fc">
					<td align="center" bgcolor="#90c7fc" width="20%">
						<div class="style9">
							<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
								<strong>Número do Contrato</strong>
							</font>
						</div>
					</td>
					
					<td align="center" bgcolor="#90c7fc" width="20%">
						<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
							<strong>Tipo Contrato</strong>
						</font>
					</td>
					
					<td align="center" bgcolor="#90c7fc" width="20%">
						<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
							<strong>Data Início</strong>
						</font>
					</td>
							
					<td align="center" bgcolor="#90c7fc" width="20%">
						<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
							<strong>Data término</strong>
						</font>
					</td>
					<td align="center" bgcolor="#90c7fc" width="20%">
						<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
							<strong>Valor da Tarifa</strong>
						</font>
					</td>
					<td align="center" bgcolor="#90c7fc" width="20%">
						<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
							<strong>Consumo Contratado</strong>
						</font>
					</td>
				</tr>
				
				<tr>
					<td colspan="6">
					<div style="width: 100%; height: 100%; overflow: auto;">
						<table width="100%" align="left" bgcolor="#99CCFF">
							<!--corpo da segunda tabela-->
							<logic:notEmpty name="contratosHelper" scope="session">
							<logic:iterate name="contratosHelper" id="contratoHelper" type="ContratoHelper">
							
								<tr bgcolor="#FFFFFF">
									
									<td align="center" width="20%">
										<div class="style9">
											<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:write name="contratoHelper" property="contrato.numeroContrato" />												
											 </font>
										</div>
									</td>
									<td align="center" width="20%">
										<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:write name="contratoHelper" property="contrato.contratoTipo.descricao" formatKey="date.format"/>													
										</font>
									</td>
									<td align="center" width="20%">
										<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:write name="contratoHelper" property="contrato.dataContratoInicio" formatKey="date.format"/>													
										</font>
									</td>
									<td align="center" width="20%">
										<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:write name="contratoHelper" property="contrato.dataContratoFim" formatKey="date.format"/>													
										</font>
									</td>
									<td align="center" width="20%">
										<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:write name="contratoHelper" property="valorTarifa" />													
										</font>
									</td>
									<td align="center" width="20%">
										<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:write name="contratoHelper" property="consumoContratado" />													
										</font>
									</td>
						    	</tr>
						    </logic:iterate>
							</logic:notEmpty>
						</table>
					</div>
					</td>
				</tr>
				
			</table>	
			<p>&nbsp;</p>
						
			<table bgcolor="#90c7fc" width="100%"
						border="0">
			  <tr>
					<td colspan="6" align="center" bgcolor="#79bbfd">
					   <strong>Matrículas Associadas</strong>
					</td>
			  </tr>
			  <tr>
							<td colspan="6">
							<div style="width: 100%; height: 100%; overflow: auto;">
							<table width="100%" align="left" bgcolor="#99CCFF">
								<!--corpo da segunda tabela-->
								<%int contx2 = 0;%>
								<logic:notEmpty name="colecaoMatriculasAssociadas" scope="session">
									<tr bgcolor="#FFFFFF">
									<logic:iterate name="colecaoMatriculasAssociadas" id="matriculasAssociadas"
										type="Integer">
										<%contx2 = contx2 + 1;
										if (contx2 % 6 == 0) {%>
										</tr><tr bgcolor="#99CCFF">
											<%}%>
											<td align="center">
												<font style="font-size: 9px;"
													color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
													<a href="javascript:consultarMatricula(<bean:write name="matriculasAssociadas" />);">													
													<bean:write name="matriculasAssociadas" />												
													</a>
												 </font>
											</td>
							    	</logic:iterate>
							    	</tr>
								</logic:notEmpty>
							</table>
							</div>
							</td>
						</tr>
				
			</table>	

				<table width="100%" border="0">
					<tr>
						<td align="left" width="100%" colspan="2">
							  <div align="right">
								   <a href="javascript:verificaExibicaoRelatorioDadosComplementares();">
										<img border="0" src="<bean:message key="caminho.imagens"/>print.gif"
											title="Imprimir Dados Adicionais" /> 
									</a>
							  </div>
						</td>
					</tr>
					
					<tr>
						<td>
							<input type="button" name="ButtonImprimir" class="bottonRightCol" value="Imprimir Contrato Adesão" onClick="gerarContratoAdesao();">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="right">
								<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_consulta.jsp?numeroPagina=2" />
							</div>
						</td>
					</tr>
				</table>	
			</td>
		</tr>
	</table>


	<p>&nbsp;</p>

	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioDadosComplementaresImovelAction.do"/>
	<%@ include file="/jsp/util/rodape.jsp"%>
	<%@ include file="/jsp/util/tooltip.jsp"%>

</html:form>
</body>
</html:html>
