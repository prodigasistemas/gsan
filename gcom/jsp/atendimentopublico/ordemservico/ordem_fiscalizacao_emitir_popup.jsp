<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.cadastro.imovel.ImovelSubcategoria"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css"type="text/css">
<html:javascript staticJavascript="false" formName="EmitirOrdemFiscalizacaoForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function validarForm(form){

	form.submit();
      
}

</script>

</head>

<html:html>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
	
<html:form 
	action="/emitirOrdemFiscalizacaoAction.do" 
	name="EmitirOrdemFiscalizacaoForm"
	type="gcom.gui.atendimentopublico.ordemservico.EmitirOrdemFiscalizacaoForm" 
	method="post">


	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>

			<td valign="top" class="centercoltext">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Emitir Ordem de Fiscalização</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<table width="100%" border="0">
			<tr>
				<td>
					<table width="100%" border="0">
						<tr>
							<td>Dados para emitir uma ordem de fiscalização:</td>
						</tr>
					</table>
					<table width="100%" border="0">
						<tr bgcolor="#cbe5fe">
           					<td align="center" colspan="3">
                  			<div id="layerShowLocal">
	                   		<table width="100%" border="0" bgcolor="#99CCFF">
		    					<tr bgcolor="#99CCFF">
	                     			<td align="center">
                    					<span class="style2">
                     					
                     						<b>Dados Gerais</b>
                     
                    					</span>
	                     			</td>
	                    		</tr>
	                    		<tr bgcolor="#cbe5fe">
									<td>
								<table border="0" width="100%">
								<tr>
									<td width="45%">
										<strong>Data de emissão:</strong>
										</td>
										<td><html:text property="dataEmissao"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="15" maxlength="15" />
									</td>									
								</tr>
								<tr>
									<td>
									<strong>Matrícula:</strong>
									</td>
									<td colspan="3"><html:text property="matriculaImovel"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="15" maxlength="15" />&nbsp;
											<html:text property="inscricaoImovel"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="30" maxlength="30" />
									</td>				
								</tr>
								<tr>
									<td>
									<div class="style9"><strong>Perfil do imóvel:</strong></div>
									</td>
									<td colspan="3"><html:text property="descricaoPerfilImovel"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="15" maxlength="15" />&nbsp;
									</td>				
								</tr>
								<tr>
									<td width="45%">
										<div class="style9"><strong>Endereço:</strong></div>
										</td>
										<td colspan="3"><html:textarea property="enderecoImovel"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000; width: 350px"
										rows="3" />
									</td>									
								</tr>
								<tr>
									<td width="45%">
										<div class="style9"><strong>Grupo Faturamento:</strong></div>
										</td>
										<td colspan="3"><html:text property="faturamentoGrupo"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="20" maxlength="20" />
									</td>									
								</tr>
								<tr>
									<td>
										<div class="style9"><strong>Última Alteração:</strong></div>
										</td>
										<td colspan="3"><html:text property="ultimaAlteracao"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="30" maxlength="30" />
									</td>									
								</tr>
								<tr>
									<td>
										<div class="style9"><strong>Situação da ligação de água:</strong></div>
										</td>
										<td colspan="3"><html:text property="situacaoLigacaoAgua"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="30" maxlength="30" />
									</td>									
								</tr>
								<tr>
									<td>
										<div class="style9"><strong>Consumo médio de água:</strong></div>
										</td>
										<td colspan="3"><html:text property="consumoMedioAgua"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="30" maxlength="30" />
									</td>									
								</tr>
								<tr>
									<td>
										<div class="style9"><strong>Data de corte:</strong></div>
										</td>
										<td colspan="3"><html:text property="dataCorte"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="30" maxlength="30" />
									</td>									
								</tr>
								<tr>
									<td>
										<div class="style9"><strong>Data de supressão parcial:</strong></div>
										</td>
										<td colspan="3"><html:text property="dataSupressaoParcial"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="30" maxlength="30" />
									</td>									
								</tr>
								<tr>
									<td>
										<div class="style9"><strong>Data de supressão total:</strong></div>
										</td>
										<td colspan="3"><html:text property="dataSupressaoTotal"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="30" maxlength="30" />
									</td>									
								</tr>
								<tr>
									<td>
										<div class="style9"><strong>Situação da ligação de esgoto:</strong></div>
										</td>
										<td colspan="3"><html:text property="situacaoLigacaoEsgoto"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="30" maxlength="30" />
									</td>									
								</tr>
								<tr>
									<td>
										<div class="style9"><strong>Volume fixo esgoto:</strong></div>
										</td>
										<td colspan="3"><html:text property="volumeFixoEsgoto"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="30" maxlength="30" />
									</td>									
								</tr>
								<tr>
									<td>
										<div class="style9"><strong>Ocorrência:</strong></div>
										</td>
										<td colspan="3"><html:text property="ocorrencia"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="30" maxlength="30" />
									</td>									
								</tr>
								<tr>
									<td>
										<div class="style9"><strong>Valor dos Servições/Atualizações:</strong></div>
										</td>
										<td colspan="3"><html:text property="valorServicos"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="30" maxlength="30" />
									</td>									
								</tr>
								<tr>
									<td>
										<div class="style9"><strong>Valor do débito até a data do vencimento do pagamento:</strong></div>
										</td>
										<td colspan="3"><html:text property="valorDebitosAteDataVencimento"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="30" maxlength="30" />
									</td>									
								</tr>
								<tr>
									<td>
										<div class="style9"><strong>Nome Cliente:</strong></div>
										</td>
										<td colspan="3"><html:text property="nomeCliente"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="50" maxlength="50" />
									</td>									
								</tr>
								<tr>
									<td>
										<div class="style9"><strong>CPF / CNPJ:</strong></div>
										</td>
										<td colspan="3"><html:text property="cpfCnpj"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="30" maxlength="30" />
									</td>									
								</tr>
								<tr>
									<td>
										<div class="style9"><strong>RG:</strong></div>
										</td>
										<td colspan="3"><html:text property="rg"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="30" maxlength="30" />
									</td>									
								</tr>
								<tr>
									<td>
										<div class="style9"><strong>DDD:</strong></div>
										</td>
										<td colspan="3"><html:text property="ddd"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="30" maxlength="30" />
									</td>									
								</tr>
								<tr>
									<td>
										<div class="style9"><strong>Número telefônico:</strong></div>
										</td>
										<td colspan="3"><html:text property="numeroTelefone"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="30" maxlength="30" />
									</td>									
								</tr>
								<tr>
									<td>
										<div class="style9"><strong>Ramal:</strong></div>
										</td>
										<td colspan="3"><html:text property="ramal"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="30" maxlength="30" />
									</td>									
								</tr>
								<tr>
									<td>
										<div class="style9"><strong>Tipo:</strong></div>
										</td>
										<td colspan="3"><html:text property="tipoTelefone"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="30" maxlength="30" />
									</td>									
								</tr>
								
								<%int cont = 0;%>
								<%int quantidadeTotalEconomias = 0;%>
								<tr>
									<td colspan="4">
									<table width="100%" align="center" bgcolor="#90c7fc" border="0">
										<tr bordercolor="#79bbfd">
										<td colspan="3" align="center" bgcolor="#79bbfd"><strong>Categorias,
											Subcategorias e Economias</strong></td>
										</tr>
										<tr bordercolor="#000000">
											<td width="19%" bgcolor="#90c7fc" align="center">
											<div class="style9"><font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <strong>Categoria</strong>
										</font></div>
										</td>
										<td width="56%" bgcolor="#90c7fc" align="center">
										<div class="style9"><font color="#000000" style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <strong>Subcategoria</strong></font>
										</div>
										</td>
										<td width="25%" bgcolor="#90c7fc" align="center">
										<div class="style9"><font color="#000000" style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <strong>Quantidade
											de Economias</strong> </font></div>
										</td>
								</tr>
								<tr>
									<td width="100%" colspan="3">
									<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%" align="left" bgcolor="#99CCFF">
										<!--corpo da segunda tabela-->
										<%cont = 0;%>
										<logic:notEmpty name="imovelSubcategorias">
											<logic:iterate name="imovelSubcategorias"
												id="imovelSubcategoria" type="ImovelSubcategoria">
												<%cont = cont + 1;
												if (cont % 2 == 0) {%>
												<tr bgcolor="#cbe5fe">
													<%} else {%>
												<tr bgcolor="#FFFFFF">
													<%}%>

													<td width="19%" align="left">
													<div align="center"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <logic:present
														name="imovelSubcategoria" property="comp_id">

														<bean:define name="imovelSubcategoria" property="comp_id"
															id="comp_id" />
														<logic:present name="comp_id" property="subcategoria">
															<bean:define name="comp_id" property="subcategoria"
																id="subcategoria" />
															<logic:present name="subcategoria" property="categoria">
																<bean:define name="subcategoria" property="categoria"
																	id="categoria" />
																<bean:write name="categoria" property="descricao" />
															</logic:present>
														</logic:present>
														</logic:present> </font></div>
														</td>
														<td width="56%" align="left">
														<div align="center"><font color="#000000"
															style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> <logic:present
															name="imovelSubcategoria" property="comp_id">	
														<bean:define name="imovelSubcategoria" property="comp_id"
															id="comp_id" />
														<logic:present name="comp_id" property="subcategoria">
															<bean:define name="comp_id" property="subcategoria"
																id="subcategoria" />
															<bean:write name="subcategoria" property="descricao" />
														</logic:present>
													</logic:present> </font></div>
													</td>
													<td width="25%" align="right">
													<div align="right"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <logic:present
														name="imovelSubcategoria" property="quantidadeEconomias">
														<bean:write name="imovelSubcategoria"
															property="quantidadeEconomias" />
													</logic:present> </font></div>
													</td>


													<%quantidadeTotalEconomias = 
														((ImovelSubcategoria) imovelSubcategoria).getQuantidadeEconomias()
															+ quantidadeTotalEconomias;
															%>
														</tr>
													</logic:iterate>
													</logic:notEmpty>
												</table>
											</div>
											</td>
											</tr>
											<tr bgcolor="#FFFFFF">
											<td width="19%" bgcolor="#90c7fc" align="center">
											<div class="style9" align="center"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total de
												Economias</strong></font></div>
											</td>
											<td width="54%" align="left">&nbsp;</td>
											<td width="27%" align="right">
											<div align="right"><font color="#000000" style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=quantidadeTotalEconomias%></font></div>
											</td>
										</tr>
										</table>
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
						<table>
						<tr>	
						<td colspan="2" width="100%">
						<table width="100%">
							<tr>
								<td align="left" colspan="">
									<input type="button" name="Button"
										class="bottonRightCol" value="Fechar"
										onClick="window.close();" />
											
								</td>
						   		<td align="right">
									<div align="right">
									<input type="button" 
										name="Button" 
										class="bottonRightCol"
										value="Emitir"
										onclick="javascript:validarForm(document.forms[0]);"/>
									</div>
								</td>
							</tr>
						</table>
						</td>
						</tr>
						</table>
	</td>
	</tr>
	</table>
	</td>
	</tr>
	</table>


</html:form>
</body>
</html:html>
