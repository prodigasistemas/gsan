<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

	<head>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
		<html:javascript staticJavascript="false" formName="AtualizarTipoRateioPopupActionForm" />
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
		<script type="text/javascript">
			$(document).ready(function() {
				$('#substituirMatricula').click(function() {
					if ($('[name=matriculaImovelAreaComum]').val() == '') {
						alert('Informe a matrícula do imóvel para área comum');
						$('[name=matriculaImovelAreaComum]').focus();
					} else {
						atualizarTipoRateio();
					}
				});
				
				$('#atualizarTipoRateio').click(function() {
					var tipoLigacao = $('[name=rateioTipoAgua]') || $('[name=rateioTipoPoco]');
					// 5 = Rateio por Área Comum
					if (tipoLigacao.val() == '5' 
						&& ($('#matriculaAreaComumAtual').val() == '' && $('[name=matriculaImovelAreaComum]').val() == '')) {
						
						alert('Informe a matrícula do imóvel para área comum');
						$('[name=matriculaImovelAreaComum]').focus();
					} else {
						atualizarTipoRateio();
					}
				});
			});
		
			function atualizarTipoRateio(){
		    	var form = document.forms[0];
		      	form.action = 'atualizarTipoRateioPopupAction.do';
		        form.submit();		
			}
			
			function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

			    var form = document.forms[0];
				
				if (tipoConsulta == 'imovel') {
			        form.matriculaImovelAreaComum.value = codigoRegistro;
			        form.inscricaoImovelAreaComum.value = descricaoRegistro;
		            form.inscricaoImovelAreaComum.style.color = "#000000";
			    }
			}
			
			function limparImovel(){
				var form = document.forms[0];
		        form.matriculaImovelAreaComum.value = "";
		        form.inscricaoImovelAreaComum.value = "";
		    }
		    
		    /*
	     	function pesquisarImovel(){
		   		var form = document.forms[0];
				
				if (form.matriculaImovelAreaComum.value != null && form.matriculaImovelAreaComum.value == "") {
					abrirPopup('exibirPesquisarImovelAction.do', 400, 800);
				}
		    }*/
						
		</script>
	</head>
	<body>
		<html:form action="/atualizarTipoRateioPopupAction.do"
				   name="AtualizarTipoRateioPopupActionForm"
				   type="gcom.gui.micromedicao.AtualizarTipoRateioPopupActionForm"
				   method="post">
			
			<table width="570" border="0" cellpadding="0" cellspacing="5">
				<tr>
					<td width="560" valign="top" class="centercoltext">
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
								<td class="parabg">Atualizar Tipo de Rateio</td>
								<td width="11">
									<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
								</td>
							</tr>
						</table>
						
						<p>&nbsp;</p>
						
						<table width="100%" border="0">
							<logic:notEqual name="fechar" value="true" scope="request">
								
								<logic:present name="AtualizarTipoRateioPopupActionForm" property="botao">
									<table width="100%" border="0">
										<tr>
											<td>Preencha os campos para atualizar o tipo de rateio:</td>
										</tr>
									</table>
								</logic:present>
								
								<logic:notPresent name="AtualizarTipoRateioPopupActionForm" property="botao">
									<table width="100%" border="0">
										<tr>
											<td>Não existe Rateio Tipo para o Imóvel</td>
										</tr>
									</table>
								</logic:notPresent>

								<logic:present name="AtualizarTipoRateioPopupActionForm" property="botao">
									<logic:present scope="session" name="colecaoRateioTipo">
										<table width="100%" border="0">
											<logic:present name="AtualizarTipoRateioPopupActionForm" property="rateioTipoAgua">
												<tr>
													<td width="20%" height="27">
														<strong>Tipo de Rateio da Liga&ccedil;&atilde;o de &Aacute;gua:<font color="#FF0000">*</font><strong></strong></strong>
													</td>
												
													<td width="60%">
														<div align="left">
															<strong> 
																<html:select name="AtualizarTipoRateioPopupActionForm" property="rateioTipoAgua">
																	<html:options name="request" collection="colecaoRateioTipo" labelProperty="descricao" property="id" />
																</html:select>
															</strong>
															<html:text property="matriculaImovelAreaComumAtual" readonly="readonly" style="background-color:#EFEFEF; border:0;" size="10px" styleId="matriculaAreaComumAtual" />
														</div>
													</td>
												</tr>
											</logic:present>
											<logic:present name="AtualizarTipoRateioPopupActionForm" property="rateioTipoPoco">
												<tr>
													<td width="20%" height="27">
														<strong>Tipo de Rateio do Po&ccedil;o:<font color="#FF0000">*</font></strong>
													</td>
													<td width="60%">
														<div align="left">
															<strong> 
																<html:select name="AtualizarTipoRateioPopupActionForm" property="rateioTipoPoco">
																	<html:options name="request" collection="colecaoRateioTipo" labelProperty="descricao" property="id" />
																</html:select>
															</strong>
														</div>
													</td>
												</tr>
											</logic:present>
											<tr>
												<td class="style1"><strong>Matr&iacute;cula &aacute;rea comum:</strong></td>
												<td>
													<html:text property="matriculaImovelAreaComum" maxlength="9" size="9"
															   onkeypress="validaEnterComMensagem(event, 'exibirAtualizarTipoRateioPopupAction.do?pesquisarInscricao=sim', 'matriculaImovelAreaComum','Matrícula Área Comum');return isCampoNumerico(event);" 
															   onkeyup="verificaLimparForm()"
														/>
														<a href="javascript:redirecionarSubmit('exibirPesquisarImovelAction.do?caminhoRetornoTelaPesquisaImovel=exibirAtualizarTipoRateioPopupAction');">
															<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>	
															<logic:present name="idImovelNaoEncontrado">
											                  <logic:equal name="idImovelNaoEncontrado" value="exception">
												                    <html:text property="inscricaoImovelAreaComum" size="30"
													                   maxlength="30" readonly="true"
													                   style="background-color:#EFEFEF; border:0; color: #ff0000" />
											                  </logic:equal>
											                  <logic:notEqual name="idImovelNaoEncontrado" value="exception">
												                   <html:text property="inscricaoImovelAreaComum" size="30"
													                maxlength="30" readonly="true"
													                style="background-color:#EFEFEF; border:0; color: #000000" />
											                  </logic:notEqual>
										                   </logic:present> 
										                   <logic:notPresent name="idImovelNaoEncontrado">
											               		<logic:empty name="AtualizarTipoRateioPopupActionForm" property="inscricaoImovelAreaComum">
												                    <html:text property="inscricaoImovelAreaComum" size="30" value = ""
													                  maxlength="30" readonly="true"
													                  style="background-color:#EFEFEF; border:0; color: #ff0000" />
											                  </logic:empty>
												              <logic:notEmpty name="AtualizarTipoRateioPopupActionForm" property="inscricaoImovelAreaComum">
													                  <html:text property="inscricaoImovelAreaComum" size="30"
														                 maxlength="30" readonly="true"
														                 style="background-color:#EFEFEF; border:0; color: #000000" />
												              </logic:notEmpty>
															</logic:notPresent>
														
													<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" onclick="limparImovel();" style="cursor: hand;" />
												</td>
												
											</tr>
										</table>
									</logic:present>
								</logic:present>
								
								<table width="100%" border="0">
									<tr>
										<td height="27" >
											<div align="center">
												<logic:present name="AtualizarTipoRateioPopupActionForm" property="matriculaImovelAreaComumAtual">
													<input type="button" class="bottonRightCol" value="Substituir imóvel área comum" id="substituirMatricula" />
												</logic:present>
												<logic:notPresent name="AtualizarTipoRateioPopupActionForm" property="matriculaImovelAreaComumAtual">
													<input type="button" class="bottonRightCol" value="Substituir imóvel área comum" disabled="disabled" />
												</logic:notPresent>
												<logic:present name="AtualizarTipoRateioPopupActionForm" property="botao">
													<input type="button" class="bottonRightCol" value="Atualizar tipo de rateio" id="atualizarTipoRateio" />
												</logic:present>
												<logic:notPresent name="AtualizarTipoRateioPopupActionForm" property="botao">
													<input type="button" class="bottonRightCol" value="Atualizar tipo de rateio" disabled="disabled" />
												</logic:notPresent>
												<input type="button" class="bottonRightCol" value="Fechar" onClick="javascript:window.close();" />
											</div>
										</td>
									</tr>
								</table>
							</logic:notEqual>
							
							<logic:equal name="fechar" value="true" scope="request">
								<table width="100%" border="0">
									<tr>
										<td colspan="4">
											Atualização do Tipo de Rateio efetuada com sucesso
										</td>
									</tr>
									<tr>
										<td>
											<div>
												<input type="button" class="bottonRightCol" value="Fechar" onClick="javascript:window.close();" />
											</div>
										</td>
									</tr>
								</table>
							</logic:equal>
			
						</table>
						<p>&nbsp;</p>
					</td>
				</tr>
			</table>
		</html:form>
	<body>
</html:html>
