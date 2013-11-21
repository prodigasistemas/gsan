<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="java.util.Collection,gcom.util.ConstantesSistema"%>
<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.seguranca.acesso.usuario.UsuarioAlteracao"%>
<%@ page import="gcom.seguranca.transacao.TabelaLinhaColunaAlteracao"%>
<%@ page import="gcom.seguranca.transacao.TabelaLinhaAlteracao"%>

<%@ page import="gcom.seguranca.acesso.OperacaoEfetuada"%>

<%@ page import="java.util.Date"%>





<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script>
<!--
-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<bean:define name="operacaoEfetuada" id="operacaoEfetuada" />
<html:form action="/FiltrarOperacaoEfetuadaAction" method="post"
	onsubmit="return validateFiltrarOperacaoEfetuadaActionForm(this);">

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
			<td width="625" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table align="center" border="0" cellpadding="0" cellspacing="0"
				width="100%">
				<tbody>
					<tr>
						<td width="11"><img src="imagens/parahead_left.gif" border="0"></td>

						<td class="parabg">Consultar Dados da Operação</td>
						<td valign="top" width="11"><img src="imagens/parahead_right.gif"
							border="0"></td>
					</tr>
				</tbody>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>



			<table border="0" width="100%">
				<tbody>
					<tr>
						<td width="132"><strong>Nome da Operação:</strong></td>

						<td width="483"><input
							value="<logic:present name="operacaoEfetuada" ><bean:write name="operacaoEfetuada" property="operacao.descricao" /></logic:present>"
							name="textfield223" size="50" maxlength="50" readonly="readonly"
							style="background-color:#EFEFEF; border:0; color: #000000"
							type="text" ></td>
					</tr>
					<tr>
						<td><strong>Data da Realização:</strong></td>
						<td><input
							value="<logic:present name="operacaoEfetuada" ><%= Util.formatarData((Date)((OperacaoEfetuada)operacaoEfetuada).getUltimaAlteracao()) %>
							</logic:present>"
							name="textfield223" size="10" maxlength="10" readonly="readonly"
							style="background-color:#EFEFEF; border:0; color: #000000"
							type="text"></td>
					</tr>
					<tr>
						<td colspan="4"><strong>Usuários:</strong></td>
					</tr>
					<tr>
						<td colspan="4">
						<table bgcolor="#99ccff" border="0" width="100%">
							<!--header da tabela interna -->
							<tr bgcolor="#99ccff">
								<td width="45%">
									<div class="style9" align="center"><strong>Nome</strong></div>
								</td>
								<td width="20%">
									<div align="center"><strong>Login</strong></div>
								</td>
								<td width="20%">
									<div class="style9" align="center"><strong>Tipo</strong></div>
								</td>
								<td width="15%">
									<div class="style9" align="center"><strong>IP</strong></div>
								</td>
							</tr>
							<%int cont = 0;%>

							<logic:present name="usuarioAlteracao">
								<logic:iterate name="usuarioAlteracao" id="usuarioAlteracao">

									<%cont = cont + 1;
			if (cont % 2 == 0) {%>
									<tr bgcolor="#cbe5fe">			
										<%} else {

			%>
									<tr bgcolor="#FFFFFF">
			

										<%}%>

										<td height="17">
										<div align="center"><bean:write name="usuarioAlteracao"
											property="usuario.nomeUsuario" /></div>
										</td>
										<td height="17">
										<div align="center">
										<logic:present name="usuarioAlteracao" property="usuario.login">
											<bean:write name="usuarioAlteracao"
												property="usuario.login" />
										</logic:present>
										</div>
										</td>

										<td>
											<div align="center"><bean:write name="usuarioAlteracao"
												property="usuario.usuarioTipo.descricao" /></div>
										</td>

										<td>
											<div align="center"><bean:write name="usuarioAlteracao"
												property="ipAlteracao" /></div>
										</td>
									</tr>
								</logic:iterate>
							</logic:present>
						</table>
						</td>
					</tr>
					<tr>
						<td colspan="4">					        
							<table width="75%" bgcolor="#99CCFF">
								<tr bordercolor="#FFFFFF" bgcolor="#79BBFD"> 
									<td colspan=2><strong>Informações do item analisado</strong></td>
								</tr>
								<tr bgcolor="#FFFFFF" bordercolor="#90c7fc">
									<td width="40%"><bean:write name="descricaoArgumento" /></td>
									<td><%=((OperacaoEfetuada)operacaoEfetuada).getArgumentoValor()%></td>
								</tr>
<%
							  String[][] outrosDados = ((OperacaoEfetuada) operacaoEfetuada).formatarDadosAdicionais();
							  if (outrosDados != null){
	                              for(int i = 0; i < outrosDados.length; i++){                            	  
%>
								<tr bgcolor="#FFFFFF" bordercolor="#90c7fc">
									<td width="40%"><%=outrosDados[i][0]%></td>
									<td><%= outrosDados[i][1]%>&nbsp;</td>
 							    </tr>
<%                            	  
	                              }
							  }
%>							
									

								
								
								<logic:present name="resumoDados">
									<logic:iterate name="resumoDados" id="dado">
									</logic:iterate>				
								</logic:present>	
							</table>
						</td>
					</tr>
					<logic:present name="tabelaLinhaAlteracaoIncluidas">
						<tr>
							<td colspan="4"><strong>Informações Analisadas</strong></td>
						</tr>
						<tr>
							<td colspan="4" height="130">
							<div style="width: 100%; height: 31%; overflow: auto;">
							<table bgcolor="#99ccff" width="100%">
								<!--header da tabela interna -->
								<tbody>
									<tr bgcolor="#79BBFD">
										<td rowspan="2" width="22%">
										<div align="center"><strong>Campo</strong></div>
										</td>
										<td colspan="2">
										<div class="style9" align="center"><strong>Conteúdo</strong></div>
										</td>
										<td rowspan="2" width="18%">
										<div align="center"><strong>Data/Hora Atualização </strong></div>
										</td>
									</tr>
									<tr bgcollor="#cbe5fe">
										<td bordercolor="#cbe5fe" bgcolor="#cbe5fe" width="22%">
										<div align="center"><strong>Anterior</strong></div>
										</td>
										<td bordercolor="#cbe5fe" bgcolor="#cbe5fe" width="22%">
										<div align="center"><strong>Atual</strong></div>
										</td>
									</tr>
									</tbody>
									</table>
									</div>
							<div style="width: 100%; height: 69%; overflow: auto;">
							<table bgcolor="#99ccff" width="100%">
								<tbody>
									<%cont = 0;
									int identificador = 0;
									String cor = "";									
									%>
									<logic:present name="tabelaLinhaAlteracaoIncluidas">
										<logic:iterate name="tabelaLinhaAlteracaoIncluidas"
											id="incluidas">
											<%											
										TabelaLinhaAlteracao tla = ((TabelaLinhaColunaAlteracao)incluidas).getTabelaLinhaAlteracao();
										int identifAtual = tla.getId();
										if (identifAtual != identificador){		
											identificador = identifAtual;
											cont = 0;
				%>
								<tr bgcolor="#99ccff">
									<td colspan="4" ><strong><font size="1,5">
										<%=tla.getTabela().getDescricao() +
										((tla.getAlteracaoTipo() != null && tla.getAlteracaoTipo().getDescricao() != null) ?
											" (" + tla.getAlteracaoTipo().getDescricao() + ")" : "") %>
										</font></strong>
									</td>
								</tr>
				<%
											
										} 
										cont = cont + 1;
										if (cont % 2 == 0) {
											cor="#cbe5fe";											
										} else {
											cor = "#FFFFFF";											
										}											
										
				%>
								<tr bgcolor="<%=cor%>">
												<td width="24%">
												<div align="center"><font size="1,5"><bean:write
													name="incluidas" property="tabelaColuna.descricaoColuna" /></font></div>
												</td>
												<td width="24%">
												<div align="center"><font size="1,5"><bean:write
													name="incluidas" property="conteudoColunaAnterior" /></font></div>
												</td>
												<td width="24%">
												<div align="center"><font size="1,5"><bean:write
													name="incluidas" property="conteudoColunaAtual" /></font></div>
												</td>
												<td height="17" width="17%"><font size="1,5">
												<div align="center"><%=Util
									.formatarDataComHora((Date) ((TabelaLinhaColunaAlteracao) incluidas)
											.getUltimaAlteracao())%></div>
												</font></td>
											</tr>
										</logic:iterate>
									</logic:present>
								</tbody>
							</table>
							</div>
							</td>
						</tr>
					</logic:present>
					<logic:present name="tabelaLinhaColunaAlteracao">
						<tr>
							<td colspan="4">
							<hr>
							</td>
						</tr>
						<tr>
							<td colspan="4"><strong>Itens Atualizados</strong></td>
						</tr>
						<tr>
							<td colspan="4" height="100">
							<div style="width: 100%; height: 100%; overflow: auto;">
							<table bgcolor="#99ccff" width="100%">
								<!--header da tabela interna -->

								<tbody>
									<tr bgcolor="#99ccff">
										<td rowspan="2" width="18%">
										<div class="style9" align="center"><strong>Nome do item</strong></div>
										</td>
										<td rowspan="2" width="18%">
										<div align="center"><strong>Identificador</strong></div>
										</td>
										<td rowspan="2" width="23%">
										<div align="center"><strong>Campo</strong></div>
										</td>
										<td colspan="2">
										<div class="style9" align="center"><strong>Conteúdo</strong></div>
										</td>
										<td rowspan="2" width="23%">
										<div align="center"><strong>Data/Hora Atualização </strong></div>
										</td>

									</tr>
									<tr bgcollor="#cbe5fe">
										<td bordercolor="#cbe5fe" bgcolor="#cbe5fe" width="19%">
										<div align="center"><strong>Anterior</strong></div>
										</td>
										<td bordercolor="#cbe5fe" bgcolor="#cbe5fe" width="22%">
										<div align="center"><strong>Atual</strong></div>
										</td>
									</tr>
									<%cont = 0;%>
									<logic:present name="tabelaLinhaColunaAlteracao">
										<logic:iterate name="tabelaLinhaColunaAlteracao"
											id="tabelaLinhaColunaAlteracao">
											<%cont = cont + 1;
			if (cont % 2 == 0) {%>
											<tr bgcolor="#cbe5fe">
												<%} else {

			%>
											<tr bgcolor="#FFFFFF">
											
												<%}%>
												<td height="17">
												<div align="center"><font size="1,5"><bean:write
													name="tabelaLinhaColunaAlteracao"
													property="tabelaLinhaAlteracao.tabela.descricao" /></font></div>
												</td>
												<td height="17">
												<div align="center"><font size="1,5"> <logic:present
													name="tabelaLinhaColunaAlteracao"
													property="tabelaLinhaAlteracao.id1">
													<bean:write name="tabelaLinhaColunaAlteracao"
														property="tabelaLinhaAlteracao.id1" />
												</logic:present> <logic:present
													name="tabelaLinhaColunaAlteracao"
													property="tabelaLinhaAlteracao.id2">-<bean:write
														name="tabelaLinhaColunaAlteracao"
														property="tabelaLinhaAlteracao.id2" />
												</logic:present></div>
												</td>
												<td>
												<div align="center"><font size="1,5"><bean:write
													name="tabelaLinhaColunaAlteracao"
													property="tabelaColuna.descricaoColuna" /></font></div>
												</td>
												<td>
												<div align="center"><font size="1,5"><bean:write
													name="tabelaLinhaColunaAlteracao"
													property="conteudoColunaAnterior" /></div>
												</td>
												<td>
												<div align="center"><font size="1,5"><bean:write
													name="tabelaLinhaColunaAlteracao"
													property="conteudoColunaAtual" /></div>
												</td>
												<td height="17"><font size="1,5">
												<div align="center"><%=Util
									.formatarDataComHora((Date) ((TabelaLinhaColunaAlteracao) tabelaLinhaColunaAlteracao)
											.getUltimaAlteracao())%></div></td>
											</tr>
										</logic:iterate>
									</logic:present>
								</tbody>
							</table>
							</div>
							</td>
						</tr>
					</logic:present>
					<logic:present name="tabelaLinhaAlteracaoExcluida">
						<tr>
							<td colspan="4">
							<hr>
							</td>
						</tr>
						<tr>
							<td colspan="4"><strong>Tabelas Excluídas</strong></td>
						</tr>
						<tr>
							<td colspan="4" height="100">
							<div style="width: 100%; height: 100%; overflow: auto;">
							<table bgcolor="#99ccff" width="100%">
								<!--header da tabela interna -->

								<tbody>
									<tr bgcolor="#99ccff">
										<td rowspan="2" width="18%">
										<div class="style9" align="center"><strong>Nome da Tabela</strong></div>
										</td>
										<td rowspan="2" width="18%">
										<div align="center"><strong>Identificador da Linha</strong></div>
										</td>
										<td rowspan="2" width="23%">
										<div align="center"><strong>Campo da Tabela</strong></div>
										</td>
										<td colspan="2">
										<div class="style9" align="center"><strong>Conteúdo</strong></div>
										</td>
										<td rowspan="2" width="23%">
										<div align="center"><strong>Data/Hora Atualização </strong></div>
										</td>

									</tr>
									<tr bgcollor="#cbe5fe">
										<td bordercolor="#cbe5fe" bgcolor="#cbe5fe" width="19%">
										<div align="center"><strong>Anterior</strong></div>
										</td>
										<td bordercolor="#cbe5fe" bgcolor="#cbe5fe" width="22%">
										<div align="center"><strong>Atual</strong></div>
										</td>
									</tr>
									<%cont = 0;%>
									<logic:present name="tabelaLinhaAlteracaoExcluida">
										<logic:iterate name="tabelaLinhaAlteracaoExcluida"
											id="excluidas">
											<%cont = cont + 1;
			if (cont % 2 == 0) {%>
											<tr bgcolor="#cbe5fe">

												<%} else {

			%>
											<tr bgcolor="#FFFFFF">			
												<%}%>
												<td height="17">
												<div align="center"><font size="1,5"><bean:write
													name="excluidas"
													property="tabelaLinhaAlteracao.tabela.descricao" /></font></div>
												</td>
												<td height="17">
												<div align="center"><font size="1,5"> <logic:present
													name="excluidas" property="tabelaLinhaAlteracao.id1">
													<bean:write name="excluidas"
														property="tabelaLinhaAlteracao.id1" />
												</logic:present> <logic:present name="excluidas"
													property="tabelaLinhaAlteracao.id2">-<bean:write
														name="excluidas" property="tabelaLinhaAlteracao.id2" />
												</logic:present></font></div>
												</td>
												<td>
												<div align="center"><font size="1,5"><bean:write
													name="excluidas" property="tabelaColuna.descricaoColuna" /></font></div>
												</td>
												<td>
												<div align="center"><font size="1,5"><bean:write
													name="excluidas" property="conteudoColunaAnterior" /></font></div>
												</td>
												<td>
												<div align="center"><font size="1,5"><bean:write
													name="excluidas" property="conteudoColunaAtual" /></font></div>
												</td>
												<td height="17"><font size="1,5">
												<div align="center"><%=Util
									.formatarDataComHora((Date) ((TabelaLinhaColunaAlteracao) excluidas)
											.getUltimaAlteracao())%></div>
												</font></td>
											</tr>
										</logic:iterate>
									</logic:present>
								</tbody>
							</table>
							</div>
							</td>
						</tr>
					</logic:present>

				</tbody>
			</table>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td><input name="adicionar" class="bottonRightCol" value="Voltar"
						onclick="window.history.back();" type="button"></td>
					<td>&nbsp;</td>
				</tr>
			</table>
	</table>


	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
