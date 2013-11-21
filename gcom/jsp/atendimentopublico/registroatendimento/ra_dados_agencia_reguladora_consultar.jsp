<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.atendimentopublico.registroatendimento.RaDadosAgenciaReguladoraFone"%>
<%@ page import="gcom.atendimentopublico.registroatendimento.RegistroAtendimento"%>
<%@ page import="gcom.gui.GcomAction"%>
<%@page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"	formName="ConsultarRaDadosAgenciaReguladoraActionForm" />

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
   
	
  	function limparForm(){
	
		var form = document.forms[0];
			window.location.href = "exibirConsultarRaDadosAgenciaReguladoraAction.do?menu=sim";
	}
	
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/informarRaDadosAgenciaReguladoraAction.do" name="ConsultarRaDadosAgenciaReguladoraActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.ConsultarRaDadosAgenciaReguladoraActionForm">

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

			<td width="605" valign="top" class="centercoltext">

			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
					</td>
					<td class="parabg">
						Consultar Dados do Registro de Atendimento pela Agência Reguladora
					</td>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>

			<p>&nbsp;</p>
			<!--Inicio da Tabela Dados Gerais do Registro de Atendimento -->
			<table width="100%" border="0">
				

				<tr>
					<td height="31">
					<table width="100%" border="0" align="center">
						<tr bgcolor="#cbe5fe">
							<td align="center">

							<div id="layerHideRegistroAtendimento" style="display:block">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td align="center"><span class="style2"> 
										<a href="javascript:extendeTabela('RegistroAtendimento',true);"/><b>Dados do Registro de Atendimento</b></a></span>
									</td>
								</tr>
							</table>
							</div>

							<div id="layerShowRegistroAtendimento" style="display:none">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td align="center"><span class="style2"> 
										<a href="javascript:extendeTabela('RegistroAtendimento',false);" /><b>Dados do Registro de Atendimento</b></a></span>
									</td>
								</tr>

								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">

										<tr>
											<td colspan="2">
											<table width="100%">
												<tr>
													<td height="10" width="27%">
														<strong>N&uacute;mero do RA:</strong></td>
													<td>
														<html:text property="numeroRADados" readonly="true"
														style="background-color:#EFEFEF; border:0;" size="9"
														maxlength="9" />
														
														<strong>Situação do RA:</strong> 
														
														<html:text property="situacaoRA" readonly="true"
														style="background-color:#EFEFEF; border:0;" size="15"
														maxlength="15" />
													</td>
												</tr>
											</table>
											</td>
										</tr>
										<c:if
											test="${InformarRaDadosAgenciaReguladoraActionForm.numeroRaAssociado != null}">
											<tr>
												<td height="10">
													<strong>N&uacute;mero do RA Associado:</strong></td>
												<td>
													<html:text property="numeroRaAssociado" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="9"
													maxlength="9" /> 
													
													<strong>Situa&ccedil;&atilde;o do RA Associado:</strong> 
													
													<html:text property="situacaoRaAssociado" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="12"
													maxlength="9" />
												</td>
											</tr>
										</c:if>

										<tr>
											<td class="style3">
												<strong>Tipo de Solicita&ccedil;&atilde;o:</strong>
											</td>
											<td colspan="3">
												<html:text property="tipoSolicitacaoId" readonly="true" 
												style="background-color:#EFEFEF; border:0;"size="4" maxlength="4" /> 
												
												<html:text property="tipoSolicitacaoDescricao" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="50"
												maxlength="50" /></td>
										</tr>

										<tr>
											<td height="10">
												<strong>Especifica&ccedil;&atilde;o:</strong>
											</td>
											<td colspan="3">
												<html:text property="especificacaoId" readonly="true" 
												style="background-color:#EFEFEF; border:0;" size="4" maxlength="4" /> 
												
												<html:text property="especificacaoDescricao" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="50" maxlength="50" />
											</td>
										</tr>

										<tr>
											<td class="style3">
												<strong>Meio de Solicita&ccedil;&atilde;o:</strong>
											</td>
											<td colspan="3">
												<html:text property="meioSolicitacaoDescricao" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="50"maxlength="50" />
											</td>
										</tr>

										<tr>
											<td>
												<strong>Matr&iacute;cula do Im&oacute;vel:</strong>
											</td>
											<td colspan="3">
												<html:text property="matriculaImovel" readonly="true" 
												style="background-color:#EFEFEF; border:0;"size="8" maxlength="8" />
												
												<html:text property="inscricaoImovel" readonly="true" maxlength="21" 
												style="background-color:#EFEFEF; border:0;" size="21"/>
											</td>
										</tr>

										<tr>
											<td width="31%" height="10">
												<strong>Data e Hora do Atendimento:</strong></td>
											<td colspan="3">
												<html:text property="dataAtendimento" readonly="true" 
												style="background-color:#EFEFEF; border:0;" size="10" maxlength="10" /> 
												
												<html:text property="horaAtendimento" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="5" maxlength="5" />
											</td>
										</tr>

										<tr>
											<td class="style3">
												<strong>Data Prevista:</strong>
											</td>
											<td colspan="3">
												<html:text property="dataPrevista" readonly="true" 
												style="background-color:#EFEFEF; border:0;" size="10" maxlength="10" />
											</td>
										</tr>

										<tr>
											<td class="style3">
												<strong>Data de Encerramento:</strong>
											</td>

											<td>
												<html:text property="dataEncerramento" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="10"
												maxlength="10" />
											</td>
										</tr>

										<tr>
											<td class="style3">
												<strong>Motivo do Encerramento:</strong>
											</td>
											<td>
												<html:text property="idMotivoEncerramento" readonly="true" 
												style="background-color:#EFEFEF; border:0;" size="5" maxlength="5" /> 
												
												<html:text property="motivoEncerramento" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="50" maxlength="50" />
											</td>
										<tr>
											<td height="10" colspan="4">
											<div align="right">
											<hr>
											</div>
											<div align="right"></div>
											</td>
										</tr>

										<tr>
											<td class="style3">
												<strong>Cliente Solicitante:</strong>
											</td>
											<td colspan="3">
												<html:text property="idClienteSolicitante" readonly="true" 
												style="background-color:#EFEFEF; border:0;" size="4" maxlength="4" /> 
												
												<html:text property="clienteSolicitante" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="40" maxlength="40" />
											</td>
										</tr>

										<tr>
											<td class="style3">
												<strong>Unidade Solicitante:</strong>
											</td>
											<td colspan="3">
												<html:text property="idUnidadeSolicitante"readonly="true" 
												style="background-color:#EFEFEF; border:0;" size="4" maxlength="4" /> 
												
												<html:text property="unidadeSolicitante" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="40" maxlength="40" />
											</td>
										</tr>

										<tr>
											<td class="style3">
												<strong>Nome do Solicitante:</strong>
											</td>
											<td colspan="3">
												<html:text property="nomeSolicitante" readonly="true" 
												style="background-color:#EFEFEF; border:0;" size="40" maxlength="40" />
											</td>
										</tr>

										<tr>
											<td height="10" colspan="4">
											<div align="right">
											<hr>
											</div>
											<div align="right"></div>
											</td>
										</tr>

										<tr>
											<td class="style3">
												<strong><span class="style2">Endere&ccedil;o da Ocorr&ecirc;ncia:</span></strong>
											</td>
											<td>
												<html:textarea property="enderecoOcorrencia" readonly="true" 
												style="background-color:#EFEFEF; border:0;" cols="50" />
											</td>
										</tr>

										<tr>
											<td class="style3">
												<strong>Ponto de Refer&ecirc;ncia:</strong>
											</td>

											<td>
												<html:text property="pontoReferencia" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="60"
												maxlength="60" />
											</td>
										</tr>

										<tr>
											<td class="style3">
												<strong>Bairro:</strong>
											</td>
											<td>
												<html:text property="bairroId" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="4" maxlength="4" /> 
												
												<html:text property="bairroDescricao" readonly="true" 
												style="background-color:#EFEFEF; border:0;" size="40" maxlength="40" />
											</td>
										</tr>

										<tr>
											<td class="style3">
												<strong>&Aacute;rea do Bairro:</strong>
											</td>
											<td>
												<html:text property="areaBairroId" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="4"
												maxlength="4" /> 
												
												<html:text property="areaBairroDescricao" readonly="true" 
												style="background-color:#EFEFEF; border:0;" size="40" maxlength="40" />
											</td>
										</tr>

										<tr>
											<td class="style3">
												<strong>Local/Setor/Quadra:</strong>
											</td>
											<td>
												<html:text property="localidadeId" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="4"
												maxlength="4" /> 
												
												<strong>/</strong> 
												
												<html:text property="setorComercialCodigo" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="4"
												maxlength="4" />
												
												<strong>/</strong> 
												
												<html:text property="quadraNumero" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="4" maxlength="4" />
											</td>
										</tr>

										<tr>
											<td class="style3">
												<strong>Divis&atilde;o de Esgoto:</strong>
											</td>
											<td colspan="3">
												<html:text property="divisaoEsgotoId" readonly="true" 
												style="background-color:#EFEFEF; border:0;" size="4" maxlength="4" /> 
												
												<html:text property="divisaoEsgotoDescricao" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="40" maxlength="40" />
											</td>
										</tr>

										<tr>
											<td height="10" colspan="4">
											<div align="right">
											<hr>
											</div>
											<div align="right"></div>
											</td>
										</tr>


										<tr>
											<td class="style3">
												<strong>Unidade de Atendimento:</strong>
											</td>
											<td colspan="3">
												<html:text property="unidadeAtendimentoId" readonly="true" 
												style="background-color:#EFEFEF; border:0;" size="4" maxlength="4" /> 
												
												<html:text property="unidadeAtendimentoDescricao" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="40" maxlength="40" />
											</td>
										</tr>

										<tr>
											<td class="style3">
												<strong>Unidade Atual:</strong>
											</td>
											<td colspan="3">
												<html:text property="unidadeAtualId" readonly="true" 
												style="background-color:#EFEFEF; border:0;" size="4" maxlength="4" /> 
												
												<html:text property="unidadeAtualDescricao" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="40" maxlength="40" />
											</td>
										</tr>

									</table>
									</td>
								</tr>
							</table>
							</div>

							</td>
						</tr>
					</table>
					</td>
				</tr>
				
				<tr>
					<td height="31">
					<table width="100%" border="0" align="center">

						<tr bgcolor="#cbe5fe">

							<td align="center">
							<table width="100%" border="0" bgcolor="#99CCFF">

								<tr bgcolor="#99CCFF">
									<td height="18" colspan="2">
										<div align="center"><span class="style2"><b>Dados da Reclamação na Agência Reguladora </b></span></div>
									</td>
								</tr>

								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">
									
										<tr>
											<td class="style3">
												<strong>Número da Reclamação:</strong>
											</td>
											<td colspan="3">
												<html:text property="numReclamacao" readonly="true" 
												style="background-color:#EFEFEF; border:0;" size="4" maxlength="4" /> 
											</td>
										</tr>
										
										<tr>
											<td class="style3">
												<strong>Situação da Agência Reguladora:</strong>
											</td>
											<td colspan="3">
												<html:text property="sitAgReguladora" readonly="true" 
												style="background-color:#EFEFEF; border:0;" size="15" maxlength="15" /> 
											</td>
										</tr>

										<tr>
											<td class="style3">
												<strong>Data Prevista para Agência Reguladora Original:</strong>
											</td>
											<td colspan="3">
												<html:text property="dtPrevAgRegOriginal" readonly="true" 
												style="background-color:#EFEFEF; border:0;" size="10" maxlength="10" /> 
											</td>
										</tr>

										<tr>
											<td class="style3">
												<strong>Data Prevista para Agência Reguladora Atual:</strong>
											</td>
											<td colspan="3">
												<html:text property="dtPrevAgRegAtual" readonly="true" 
												style="background-color:#EFEFEF; border:0;" size="10" maxlength="10" /> 
											</td>
										</tr>
										
										<tr>
											<td class="style3">
												<strong>Motivo da Reclamação:</strong>
											</td>
											<td colspan="3">
												<html:text property="motivoReclamacaoId" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="4" maxlength="4" /> 
												
												<html:text property="motivoReclamacaoDescricao" readonly="true" 
												style="background-color:#EFEFEF; border:0;" size="40" maxlength="40" />
											</td>
										</tr>
										
										<tr>
											<td  class="style3">
												<strong>Data e Hora da Reclamação:</strong></td>
											<td colspan="3">
												<html:text property="dataReclamacao" readonly="true" 
												style="background-color:#EFEFEF; border:0;" size="10" maxlength="10" /> 
												
												<html:text property="horaReclamacao" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="5" maxlength="5" />
											</td>
										</tr>
										
										<tr>
											<td  class="style3">
												<strong>Descrição da Reclamação:</strong></td>
											<td colspan="3">
												<html:textarea  property="descricaoReclamacao" readonly="true" cols="45" rows="4"
												style="background-color:#EFEFEF; border:0;" />
											</td>
										</tr>
										
										<tr>
											<td  class="style3">
												<strong>Motivo do Encerramento:</strong></td>
											<td colspan="3">
												<html:text property="motEncerramentoId" readonly="true" 
												style="background-color:#EFEFEF; border:0;" size="5" maxlength="5" /> 
												
												<html:text property="descricaoMotEncerramento" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="50" maxlength="50" />
											</td>
										</tr>
										
										
										<tr bgcolor="#99CCFF">
											<td height="18" colspan="2">
												<div align="center"><span class="style2"><b>Dados do Retorno para Agência</b></span></div>
											</td>
										</tr>
										
										<tr>
											<td class="style3">
												<strong>Motivo do Retorno:</strong>
											</td>
											<td colspan="3">
												<html:text property="motivoRetornoId" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="4" maxlength="4" /> 
												
												<html:text property="motivoRetornoDescricao" readonly="true" 
												style="background-color:#EFEFEF; border:0;" size="50" maxlength="50" />
											</td>
										</tr>
										
										<tr>
											<td  class="style3">
												<strong>Data e Hora da Retorno:</strong></td>
											<td colspan="3">
												<html:text property="dataRetorno" readonly="true" 
												style="background-color:#EFEFEF; border:0;" size="10" maxlength="10" /> 
												
												<html:text property="horaRetorno" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="5" maxlength="5" />
											</td>
										</tr>
										
									     <tr>
											<td  class="style3">
												<strong>Descrição do Retorno:</strong></td>
											<td colspan="3">
												<html:textarea  property="descricaoRetorno" readonly="true" cols="45" rows="4"
												style="background-color:#EFEFEF; border:0;"/>
											</td>
										</tr>
						</table>
						
					</td>
				</tr>
			</table>
		</td>
	  </tr>
	  
	  <tr>
	  	<td height="31">
	  	<table width="100%" border="0" bgcolor="#99CCFF">

				<tr bgcolor="#99CCFF">
					<td height="18" colspan="2">
						<div align="center"><span class="style2"><b>Dados do Contato para Agência</b></span></div>
					</td>
				</tr>

				<tr bgcolor="#cbe5fe">
					<td>
					<table border="0" width="100%">
					
						<tr>
							<td class="style3">
								<strong>Nome:</strong>
							</td>
							<td colspan="3">
								<html:text property="nomeContato" readonly="true" size="50" maxlength="50"
								style="background-color:#EFEFEF; border:0;"/> 
							</td>
						</tr>
						
						<tr>
							<td class="style3">
								<strong>E-mail:</strong>
							</td>
							<td colspan="3">
								<html:text property="mailContato" readonly="true" size="40" maxlength="40"
								style="background-color:#EFEFEF; border:0;"/> 
							</td>
						</tr>
						
						<tr>
							<td class="style3">
								<strong>Nome do Órgão:</strong>
							</td>
							<td colspan="3">
								<html:text property="nomeOrgaoContato" readonly="true" size="40" maxlength="40"
								style="background-color:#EFEFEF; border:0;"/> 
							</td>
						</tr>
						
						<tr>
							<td class="style3">
								<strong>DDD:</strong>
							</td>
							<td colspan="3">
								<html:text property="dddContato" readonly="true" size="3" maxlength="3"
								style="background-color:#EFEFEF; border:0;"/> 
							</td>
						</tr>
						
						<tr>
							<td class="style3">
								<strong>Telefone:</strong>
							</td>
							<td colspan="3">
								<html:text property="telefoneContato" readonly="true" size="10" maxlength="10"
								style="background-color:#EFEFEF; border:0;"/> 
							</td>
						</tr>
						
						<tr>
							<td class="style3">
								<strong>Ramal:</strong>
							</td>
							<td colspan="3">
								<html:text property="ramalContato" readonly="true" size="4" maxlength="4"
								style="background-color:#EFEFEF; border:0;"/> 
							</td>
						</tr>
						
						<tr>
							<td class="style3">
								<strong>Fax:</strong>
							</td>
							<td colspan="3">
								<html:text property="faxContato" readonly="true" size="10" maxlength="10"
								style="background-color:#EFEFEF; border:0;"/> 
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
		</td>
	  </tr>
	  
	  <tr>
			<td height="31">
		  	<table width="100%" border="0" bgcolor="#99CCFF">
	
					<tr bgcolor="#99CCFF">
						<td height="18" colspan="2">
							<div align="center"><span class="style2"><b>Telefones do Reclamante</b></span></div>
						</td>
					</tr>
					
					<tr bgcolor="#cbe5fe">
					<table width="100%" cellpadding="0" cellspacing="0">
						
						<tr bgcolor="#cbe5fe">
							<td>
								<table border="0" width="100%">
									<tr>
										<td>
											<%String cor = "#cbe5fe";%>
										<div style="width: 100%; height: 80; overflow: auto;">
										<table width="100%" align="center" bgcolor="#90c7fc">
											
											<tr bgcolor="#99CCFF">
												<td width="15%" align="center">
													<strong>DDD</strong>
												</td>
												<td width="30%" align="center">
													<strong>Telefone</strong>
												</td>
												<td width="20%" align="center">
													<strong>Ramal</strong>
												</td>
												<td width="35%" align="center">
													<strong>Tipo de Telefone</strong>
												</td>
											</tr>
								
											<logic:iterate name="colecaoRaDadosAgenciaReguladoraFone" id="raDadosAgenciaReguladoraFone" type="RaDadosAgenciaReguladoraFone">
												<%if (cor.equalsIgnoreCase("#cbe5fe")) {
														cor = "#FFFFFF";%>
												<tr bgcolor="#FFFFFF">
													<%} else {
														cor = "#cbe5fe";%>
												<tr bgcolor="#cbe5fe">
													<%}%>
													<td width="15%" align="center">
														(<bean:write name="raDadosAgenciaReguladoraFone" property="ddd"/>)
													</td>
													<td width="30%" align="center">
														<bean:write name="raDadosAgenciaReguladoraFone" property="fone"/>
													</td>
													<td width="20%" align="center">
														<logic:empty name="raDadosAgenciaReguladoraFone" property="ramal">
															SEM RAMAL
														</logic:empty>
														<logic:notEmpty name="raDadosAgenciaReguladoraFone" property="ramal">
															<bean:write name="raDadosAgenciaReguladoraFone" property="ramal"/>
														</logic:notEmpty>
													</td>
													<td width="35%" align="center">
														<bean:write name="raDadosAgenciaReguladoraFone" property="foneTipo.descricao"/>
													</td>
												</tr>
											</logic:iterate>
										</table>
										</div>
									</td>
								  </tr>
								</table>
							</td>
					</tr>
				</table>
					
			 </table>
			</td>
		  </tr>
		  
		
		
		<tr>
			<table width="100%">
				<tr>
					<td width="40%" align="left">
						
						<logic:present name="Listar" scope="request">
							<input type="button" name="ButtonReset" class="bottonRightCol" value="Voltar" tabindex="1"
							onclick="javascript:window.location.href='/gsan/exibirListarRaDadosAgenciaReguladoraAction.do'">
						</logic:present>
						
						<logic:notPresent name="Listar" scope="request">
							<input type="button" name="ButtonReset" class="bottonRightCol" value="Voltar" tabindex="1"
							onclick="javascript:window.location.href='/gsan/exibirFiltrarRaDadosAgenciaReguladoraAction.do'">
						</logic:notPresent>
					
						<input type="button" name="ButtonCancelar" class="bottonRightCol" tabindex="2"
							value="Imprimir" onClick="">
					</td>
					
					
				</tr>
			</table>
		</tr>

			</table>
			</td>
		</tr>
	</table>
	<!-- Fim do Corpo -->

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
