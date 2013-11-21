<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.gui.atendimentopublico.ordemservico.CadastrarMensagemAcompanhamentoServicoPopupAction"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
	<head>
		
		<%@ include file="/jsp/util/titulo.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
		<script type="text/javascript" src="<bean:message key="caminho.js"/>util.js"></script>
		<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
		
		<script type="text/javascript">
			$(document).ready(function() {
				var limitsize = 60;
			
				$('#checkall').change(function() {
				    if($(this).is(':checked')) {
				         $('.cb-element').attr('checked', true);
				         $(this).title('Remover seleção');
				    } else {
				         $('.cb-element').attr('checked', false);
				         $(this).title('Selecionar todos');
				    }
				});
				
				$('#cadastrar').click(function(){
					var text = $.trim($('#mensagem').val());
					if(text.length === 0) {
						alert('Uma mensagem deve ser digitada.');
						$('#mensagem').focus();
						return false;
					} else {
						$('#form').submit();
					}
				});
				
				$('#fechar').click(function(){
					window.close();
				});
				
				limitChars('mensagem', limitsize, 'limitinfo');
				$('#mensagem').keyup(function(){
					limitChars('mensagem', limitsize, 'limitinfo');
				});
				
				$('#zebrado tr:odd').css('background-color', '#CBE5FE');
				
				$('#remover').click(function() {
					if ($('.cb-element:checked').size() > 0) {
						if (confirm ("Confirma remoção?")) {
							$('#form')
									.attr('action', 'removerMensagemAcompanhamentoServicoAction.do')
									.submit();
						 }
					} else {
						alert('Nenhuma mensagem foi selecionada para remoção.');
					}
				});
			});
			
			function limitChars(textid, limit, infodiv) {
				var text = $('#' + textid).val(),	
					textlength = text.length;
				
				if(textlength > limit) {
					$('#' + textid).val(text.substr(0, limit));
					return false;
				} else {
					$('#' + infodiv).html(textlength + '/' + limit);
					return true;
				}
 			}
		</script>
		
		<style type="text/css">
			td .header { color:#000; font-size: 10px; font-family: Verdana, Arial, Helvetica, sans-serif; }
			#zebrado { font-size: 11px; }
		</style>
	</head>
	<body leftmargin="5" topmargin="5">
		<html:form action="/cadastrarMensagemAcompanhamentoServicoAction.do"
				   name="CadastrarMensagemAcompanhamentoServicoPopupActionForm"
				   type="gcom.gui.atendimentopublico.ordemservico.CadastrarMensagemAcompanhamentoServicoPopupActionForm"
				   method="post"
				   styleId="form">
		    
		    <table width="650" border="0" cellspacing="5" cellpadding="0">
		    	<tr>
					<td width="650" valign="top" class="centercoltext">
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
								<td class="parabg" style="text-align:center">Acompanhamento de Arquivos de Roteiro</td>
								<td width="11">
									<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
								</td>
							</tr>
							<tr>
								<td height="5" colspan="3"></td>
							</tr>
						</table>
	
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td width="45%" class="header">
									<strong>Equipe:</strong>&nbsp;
									<logic:present name="equipe" scope="request">
										<bean:write name="equipe" scope="request" />
									</logic:present>
								</td>
								<td class="header">
									<strong>Situação do Arquivo:</strong>&nbsp;
									<logic:present name="situacaoTransmissaoLeitura" scope="request">
										<bean:write name="situacaoTransmissaoLeitura" scope="request" />
									</logic:present>
								</td>
							</tr>
							<tr>
								<td colspan="5" height="23">
									<font color="#000000" style="font-size:10px" face="Verdana, Arial, Helvetica, sans-serif">
										<strong>Mensagens Cadastradas:</strong>
									</font>
								</td>
							</tr>
				  		</table>
				
				  		<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td colspan="5" bgcolor="#000000" height="2"></td>
							</tr>
							<tr height="30px" bgcolor="#99CCFF" align="center">
								<td width="26px">
									<input type="checkbox" id="checkall" title="Selecionar todos" />
								</td>
								<td width="93px">
									<strong>Usuário</strong>
								</td>
								<td width="65px">
									<strong>Data</strong>
								</td>
								<td width="328px">
									<strong>Texto da Mensagem</strong>
								</td>
								<td width="85px">
									<strong>Situação</strong>
								</td>
							</tr>
							<tr>
								<td colspan="5">
									<div style="width: 100%; min-height: 150px; height: 150px; overflow-y: scroll;">
										<table width="100%" bgcolor="#90c7fc" id="zebrado">
											<logic:present name="colecaoMensagens" scope="request">
												<logic:iterate name="colecaoMensagens" id="mensagem">
													<tr style="background-color:#FFF;" height="25px;" align="center">
														<td width="20px">
															<logic:equal name="mensagem" property="indicadorSituacao" value="1">
																<input type="checkbox" disabled="disabled"/>
															</logic:equal>
															<logic:equal name="mensagem" property="indicadorSituacao" value="2">
																<input type="checkbox" name="ids" value="<bean:write name="mensagem" property="id" />" class="cb-element" />
															</logic:equal>
																
														</td>
														<td width="90px" align="center">
															<bean:define name="mensagem" property="usuario" id="usuario" />
															<bean:write name="usuario" property="nomeUsuario" />
														</td>
														<td width="60px" align="center">
															<bean:write name="mensagem" property="ultimaAlteracao" formatKey="date.format"/>
														</td>
														<td width="340px" align="left">
															<bean:write name="mensagem" property="descricaoMensagem" />
														</td>
														<td width="65px" align="center">
															<logic:equal name="mensagem" property="indicadorSituacao" value="1">
																Recebida
															</logic:equal>
															<logic:equal name="mensagem" property="indicadorSituacao" value="2">
																Cadastrada
															</logic:equal>
														</td>
													</tr>
												</logic:iterate>
											</logic:present>
										</table>
									</div>
								</td>
							</tr>
						</table>
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td colspan="5" height="2" style="padding-top: 10px; font-size: 11px;">
									<strong>Nova Mensagem:</strong>
								</td>
							</tr>
							<tr>
								<td colspan="5" bgcolor="#000000" height="2"></td>
							</tr>
							<tr>
								<td colspan="5" height="2">
									<html:textarea property="mensagem" styleId="mensagem" cols="45" rows="2" style="margin-top: 5px;"/><br>
									<strong style="padding-left: 350px;"><span id="limitinfo"></span></strong>
								</td>
							</tr>
							<tr>
								<td colspan="5">
									<input type="button" id="fechar" class="bottonRightCol" value="Fechar"/>
									<input type="button" id="cadastrar" class="bottonRightCol" value="Cadastrar" />
									<input type="button" id="remover" class="bottonRightCol" value="Remover" />
									
									<input type="hidden" name="<%=CadastrarMensagemAcompanhamentoServicoPopupAction.TXAC_ID_PARAMETER%>" value="<%=request.getParameter(CadastrarMensagemAcompanhamentoServicoPopupAction.TXAC_ID_PARAMETER)%>" />
									<p>&nbsp;</p>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>		
		</html:form>
	</body>
</html:html>