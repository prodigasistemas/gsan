<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gcom" %>

<%@page import="gcom.util.ConstantesSistema"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="TransferirRotasGruposEmpresasActionForm" />
	<%@ include file="/jsp/util/titulo.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
	<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<SCRIPT LANGUAGE="JavaScript">
		
		function validarFormSubmit(){
			var form = document.TransferirRotasGruposEmpresasActionForm;
    		form.action = 'transferirRotaGrupoEmpresaAction.do';
    		form.submit();
		}
	
   		
	</SCRIPT>
</head>
<html:form
  action="/transferirRotaGrupoEmpresaConfirmarAction.do"
  method="post"
  name="TransferirRotasGruposEmpresasActionForm"
  type="gcom.gui.cobranca.TransferirRotasGruposEmpresasActionForm"
>
<body leftmargin="5" topmargin="5">

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
			<td width="600" valign="top" bgcolor="#003399" class="centercoltext">
				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
						<td class="parabg">Confirma a Transferência das Rotas Selecionadas?</td>
	
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
					<tr>
						<td height="5" colspan="3"></td>
					</tr>
				</table>
				<table width="100%" border="0">
		
					<tr>
						<td colspan="6">
							<table width="100%" border="0">
								<tr>
									<td>
										<div style="width: 100%; height: 100%; overflow: auto;">
											<table width="100%" align="center" bgcolor="#99CCFF">
												<tr>
													<td><strong> Atual:</strong></td>
												</tr>
												<tr>
													<td>
														<table>	
															<tr bgcolor="#cbe5fe">
																<td height="53"><strong> Quantidade de Rotas na Sele&ccedil;&atilde;o:</strong></td>
											                	<td>
											                		<html:text property="quantidadeRotas" size="8" maxlength="8"
																	readonly="true" />
											                  	</td>
										                  	</tr>
										                  	<tr bgcolor="#cbe5fe">
																<td height="53"><strong>Grupo de Faturamento:</strong></td>
																
																<td>
																	<strong> 
																		<html:select
																		property="idGrupoFaturamentoSelecao" 
																		style="width: 230px;" 
																		multiple="mutiple"
																		disabled="true"
																		size="3">
																			<logic:present name="colecaoGrupoFaturamentoSelecao">
																			<html:option
																				value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
																			</html:option>
																			<html:options 
																				collection="colecaoGrupoFaturamentoSelecao"
																				labelProperty="descricao" 
																				property="id" />
																			</logic:present>
																		</html:select>
																	</strong>
																</td>
															</tr>
															<tr bgcolor="#cbe5fe">
																<td height="53"><strong>Grupo de Cobran&ccedil;a:</strong></td>
																<td>
																	<strong> 
																		<html:select
																		property="idGrupoCobrancaSelecao" 
																		style="width: 230px;" 
																		multiple="mutiple"
																		disabled="true"
																		size="3">
																			<logic:present name="colecaoGrupoCobrancaSelecao">
																			<html:option
																				value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
																			</html:option>
																			<html:options 
																				collection="colecaoGrupoCobrancaSelecao"
																				labelProperty="descricao" 
																				property="id" />
																			</logic:present>
																		</html:select>
																	</strong>
																</td>
															</tr>
															<tr bgcolor="#cbe5fe">
																<td height="53"><strong>Empresa de Faturamento:</strong></td>
																<td>
																	<strong> 
																		<html:select
																		property="idEmpresaFaturamentoSelecao" 
																		style="width: 230px;" 
																		multiple="mutiple"
																		disabled="true"
																		size="3">
																			<logic:present name="colecaoEmpresaFaturamentoSelecao">
																			<html:option
																				value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
																			</html:option>
																			<html:options 
																				collection="colecaoEmpresaFaturamentoSelecao"
																				labelProperty="descricao" 
																				property="id" />
																			</logic:present>
																		</html:select>
																	</strong>
																</td>
															</tr>
															<tr bgcolor="#cbe5fe">
																<td height="53"><strong>Empresa de Cobran&ccedil;a:</strong></td>
																<td>
																	<strong> 
																		<html:select
																		property="idEmpresaCobrancaSelecao" 
																		style="width: 230px;" 
																		multiple="mutiple"
																		disabled="true"
																		size="3">
																			<logic:present name="colecaoEmpresaCobrancaSelecao">
																			<html:option
																				value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
																			</html:option>
																			<html:options 
																				collection="colecaoEmpresaCobrancaSelecao"
																				labelProperty="descricao" 
																				property="id" />
																			</logic:present>
																		</html:select>
																	</strong>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td></td>
												</tr>
												
											</table>
										</div>
									</td>
									<td>
										<div style="width: 100%; height: 100%; overflow: auto;">
											<table width="100%" align="center" bgcolor="#99CCFF">
												<tr>
													<td><strong> Destino:</strong></td>
												</tr>
												<tr>
													<td>
														<table>	
															<tr bgcolor="#cbe5fe">
																<td colspan="2" height="53"><strong>Grupo(s) e/ou Empresa(s) para onde será(ão) transferida(s) a(s) Rota(s):</strong></td>
										                  	</tr>
										                  	
										                  	<logic:present name="colecaoFaturamentoGrupoDestino">
										                  	<tr bgcolor="#cbe5fe">
										                  		<td height="53"><strong>Grupo de Faturamento:</strong></td>
																<td>
																	<strong> 
																		<html:select
																			property="idGrupoFaturamentoDestino" 
																			style="width: 170px;"
																			disabled="true">
																			
																			<html:option
																				value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
																			</html:option>
																			<html:options 
																				collection="colecaoFaturamentoGrupoDestino"
																				labelProperty="descricao" 
																				property="id" />
																			
																		</html:select>
																	</strong>
																</td>
										                  	</tr>
										                  	</logic:present>
										                  	<logic:notPresent name="colecaoFaturamentoGrupoDestino">
									                  		<tr bgcolor="#cbe5fe">
										                  		<td colspan="2" height="53">
										                  		</td>
										                  	</tr>
										                  	</logic:notPresent>
										                  	
										                  	<logic:present name="colecaoCobrancaGrupoDestino">
										                  	<tr bgcolor="#cbe5fe">
										                  		<td height="53"><strong>Grupo de Cobran&ccedil;a:</strong></td>
																<td>
																	<strong>
																		<html:select
																			property="idGrupoCobrancaDestino" 
																			style="width: 170px;"
																			disabled="true">
																		
																		<html:option
																			value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
																		</html:option>
																		<html:options 
																			collection="colecaoCobrancaGrupoDestino"
																			labelProperty="descricao" 
																			property="id" />
																		
																		</html:select>
																	</strong>
																</td>
										                  	</tr>
										                  	</logic:present>
										                  	<logic:notPresent name="colecaoCobrancaGrupoDestino">
									                  		<tr bgcolor="#cbe5fe">
										                  		<td colspan="2" height="53">
										                  		</td>
										                  	</tr>
										                  	</logic:notPresent>
										                  	
										                  	<logic:present name="colecaoEmpresaFaturamentoDestino">
										                  	<tr bgcolor="#cbe5fe">
										                  		<td height="53"><strong>Empresa de Faturamento:</strong></td>
										                  		<td>
																	<strong> 
																		<html:select
																		property="idEmpresaFaturamentoDestino" 
																		style="width: 170px;"
																		disabled="true">
																			
																			<html:option
																				value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
																			</html:option>
																			<html:options 
																				collection="colecaoEmpresaFaturamentoDestino"
																				labelProperty="descricao" 
																				property="id" />
																			
																		</html:select>
																	</strong>
																</td>
										                  	</tr>
										                  	</logic:present>
										                  	<logic:notPresent name="colecaoEmpresaFaturamentoDestino">
									                  		<tr bgcolor="#cbe5fe">
										                  		<td colspan="2" height="53">
										                  		</td>
										                  	</tr>
										                  	</logic:notPresent>
										                  	
										                  	<logic:present name="colecaoEmpresaCobrancaDestino">
										                  	<tr bgcolor="#cbe5fe">
										                  		<td height="53"><strong>Empresa de Cobran&ccedil;a:</strong></td>
																<td>
																	<strong> 
																		<html:select
																		property="idEmpresaCobrancaDestino" 
																		style="width: 170px;"
																		disabled="true">
																			
																			<html:option
																				value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
																			</html:option>
																			<html:options 
																				collection="colecaoEmpresaCobrancaDestino"
																				labelProperty="descricao" 
																				property="id" />
																			
																		</html:select>
																	</strong>
																</td>
										                  	</tr>
										                  	</logic:present>
									                  		<logic:notPresent name="colecaoEmpresaCobrancaDestino">
									                  		<tr bgcolor="#cbe5fe">
										                  		<td colspan="2" height="53">
										                  		</td>
										                  	</tr>
									                  		</logic:notPresent>
										                  	
										                  
															
														</table>
													</td>
												</tr>
												<tr>
													<td></td>
												</tr>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr>
						<td>
						</td>
					</tr>
					
					<tr>
						<td width="40">
						</td>
						<td width="40">
						</td>
						<td width="40">
						</td>
						<td>
							<div align="right">
							<input name="Button" type="button" class="bottonRightCol"
								value="Sim" align="left"
								onClick="validarFormSubmit()">
							<input name="Button" type="button" class="bottonRightCol"
								value="Não" align="left"
								onClick="window.location.href='/gsan/telaPrincipal.do'">
							</div>
						</td>
					</tr>
					
				</table>
			</td>
		</tr>
	</table>
</html:form>
</body>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:html>