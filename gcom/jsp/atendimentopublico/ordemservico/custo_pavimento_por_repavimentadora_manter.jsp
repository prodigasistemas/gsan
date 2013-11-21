<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ page import="gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoRua"%>
<%@ page import="gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoCalcada"%>
<%@ page import="gcom.util.Util"%>

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

function facilitadorRua(objeto){
	if (objeto.id == "0" || objeto.id == undefined){
		objeto.id = "1";
		marcarTodosListBox('idRegistrosRemocao');
	}
	else{
		objeto.id = "0";
		desmarcarTodosListBox('idRegistrosRemocao');
	}
}

function removerRua(){
	var form = document.forms[0];
	
	if(CheckboxNaoVazio(form.idRegistrosRemocao) && confirm('Confirmar remoção?')){
		
		form.action = "/gsan/removerCustoPavimentoPorRepavimentadoraAction.do?acao=removerRua";
		form.submit();
	}
}

function removerCalcada(){
	var form = document.forms[0];
	
	if(CheckboxNaoVazio(form.idRegistrosRemocao_segunda) && confirm('Confirmar remoção?')){
	
		form.action = "/gsan/removerCustoPavimentoPorRepavimentadoraAction.do?acao=removerCalcada";
		form.submit();
	}
}

function facilitadorCalcada(objeto){
	if (objeto.id == "0" || objeto.id == undefined){
		objeto.id = "1";
		marcarTodosListBox('idRegistrosRemocao_segunda');
	}
	else{
		objeto.id = "0";
		desmarcarTodosListBox('idRegistrosRemocao_segunda');
	}
}

</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/removerCustoPavimentoPorRepavimentadoraAction"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post"
	onsubmit="return (CheckboxNaoVazio(document.ManutencaoRegistroActionForm.idRegistrosRemocao) || CheckboxNaoVazio(document.ManutencaoRegistroActionForm.idRegistrosRemocao_segunda)) && confirm('Confirmar remoção?')">

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

			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Manter Custo do Pavimento por Repavimentadora</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" />
					</td>
				</tr>
			</table>

			<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td colspan="7">
						<table width="100%" border="0">
							<tr>
								<td height="23" width="30%">
									
									<strong>Unidade Repavimentadora:</strong> 
								</td>
								
								<td height="23" align="left" width="70%">
									
									<strong>${sessionScope.descricaoUnidadeRepavimentadora}</strong>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<logic:present name="colecaoCustoPavimentoRua" scope="request">
				
				<tr>
					<td colspan="7" height="23">
						<strong>Pavimento de Rua:</strong>
					</td>
				</tr>
				
				<tr>
					<td colspan="7" bgcolor="#000000" height="2" valign="baseline"></td>
				</tr>
				<tr>
					<td>
					<table width="100%" align="center" bgcolor="#90c7fc" border="0"
						cellpadding="0" cellspacing="0">
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" bgcolor="#90c7fc" border="0">
								<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">

									<td width="7%">
										<div align="center" >
											<strong><a href="javascript:facilitadorRua(this);" id="0">Todos</a></strong>
										</div>
									</td>

									<td width="30%">
										<div align="center"><strong>Pavimento de Rua</strong></div>
									</td>
									
									<td width="12%">
										<div align="center"><strong>Valor</strong></div>
									</td>
									
									<td>
										<table width="100%" >
											<tr>
												<td colspan="2">
													<div align="center"><strong>Vigência</strong></div>
												</td>
											</tr>
											
											<tr>
												<td colspan="1">
													<div align="center" width="25%"><strong>Início</strong></div>
												</td>
												
												<td colspan="1">
													<div align="center" width="26%"><strong>Término</strong></div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								
								<tr>
						            <td colspan="5"><div style="height:100px;overflow:auto">
						            <table width="100%" bgcolor="#99CCFF" border="0" >
						
										<% String cor = "#cbe5fe";%>
										
											<logic:iterate name="colecaoCustoPavimentoRua" id="custoPavimentoRua" type="UnidadeRepavimentadoraCustoPavimentoRua">
							    
												<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
													cor = "#FFFFFF";%>
													<tr bgcolor="#FFFFFF" height="18">	
												<%} else{	
													cor = "#cbe5fe";%>
													<tr bgcolor="#cbe5fe" height="18">		
												<%}%>
														<td width="7%">
															<div align="center">
																<input type="checkbox" name="idRegistrosRemocao"
																	value="<bean:write name="custoPavimentoRua" property="id"/>">
															</div>
														</td>
														
														<% if(!custoPavimentoRua.isPodeAtualizar()) { %>
													   			
													   		<td align="center" width="30%">
																<bean:write name="custoPavimentoRua" property="pavimentoRua.descricao"/>
															</td>	
													   	<%}else { %>
													   		<td align="center" width="30%">
																<a href="javascript:redirecionarSubmit('exibirAtualizarCustoPavimentoPorRepavimentadoraAction.do?acao=atualizarRua&idAtualizacao=<%=""+custoPavimentoRua.getId()%>');" >
																	<div align="center"><bean:write name="custoPavimentoRua" property="pavimentoRua.descricao"/></div>
																</a>
															</td>
													   	<%} %>
														
														<td align="right" width="12%"><bean:write name="custoPavimentoRua" property="valorPavimento" formatKey="money.format"/></td>
														<td align="center" width="25%"><bean:write name="custoPavimentoRua" property="dataVigenciaInicial" format="dd/MM/yyyy"/></td>
														<td align="center" width="26%"><bean:write name="custoPavimentoRua" property="dataVigenciaFinal" format="dd/MM/yyyy"/></td>
											
													</tr>
							
											</logic:iterate>
										
						
									</table>
									</div></td>
								</tr>
								
							</table>
								
							<table bgcolor="cbe5fe" border="0" width="100%" align="left">
								<tr>
									<td align="left">
									
										<input name="button" type="button" class="bottonRightCol"
										value="Remover Custo do Pavimento de Rua" onclick="javascript:removerRua();">
									</td>
								</tr>
							</table>
								
							<tr>
								<td colspan="5" bordercolor="#FFFFFF" bgcolor="#cbe5fe"><hr></td>
							</tr>
								
							</td>
						</tr>

					</table>
					<p>&nbsp;</p>
					</td>
				</tr>
				
				</logic:present>
				
				<logic:present name="colecaoCustoPavimentoCalcada" scope="request">

				<tr>
					<td colspan="7" height="23">
						<strong>Pavimento de Calçada:</strong>
					</td>
				</tr>
				
				<tr>
					<td colspan="7" bgcolor="#000000" height="2" valign="baseline"></td>
				</tr>
				<tr>
					<td>
					<table width="100%" align="center" bgcolor="#90c7fc" border="0"
						cellpadding="0" cellspacing="0">
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" bgcolor="#90c7fc" border="0">
								<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">

									<td width="7%">
										<div align="center" >
											<strong><a href="javascript:facilitadorCalcada(this);" id="0">Todos</a></strong>
										</div>
									</td>

									<td width="30%">
										<div align="center"><strong>Pavimento de Calçada</strong></div>
									</td>
									
									<td width="12%">
										<div align="center"><strong>Valor</strong></div>
									</td>
									
									<td>
										<table width="100%" >
											<tr>
												<td colspan="2">
													<div align="center"><strong>Vigência</strong></div>
												</td>
											</tr>
											
											<tr>
												<td colspan="1">
													<div align="center" width="25%"><strong>Início</strong></div>
												</td>
												
												<td colspan="1">
													<div align="center" width="26%"><strong>Término</strong></div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								
								<tr>
						            <td colspan="5"><div style="height:100px;overflow:auto">
						            <table width="100%" bgcolor="#99CCFF" border="0" >
						
										<% String cor1 = "#cbe5fe";%>
										
											<logic:iterate name="colecaoCustoPavimentoCalcada" id="custoPavimentoCalcada" type="UnidadeRepavimentadoraCustoPavimentoCalcada">
							    
												<%	if (cor1.equalsIgnoreCase("#cbe5fe")){	
													cor1 = "#FFFFFF";%>
													<tr bgcolor="#FFFFFF" height="18">	
												<%} else{	
													cor1 = "#cbe5fe";%>
													<tr bgcolor="#cbe5fe" height="18">		
												<%}%>
														<td width="7%">
															<div align="center">
																<input type="checkbox" name="idRegistrosRemocao_segunda"
																	value="<bean:write name="custoPavimentoCalcada" property="id"/>">
															</div>
														</td>
														
														<% if(!custoPavimentoCalcada.isPodeAtualizar()) { %>
													   			
													   		<td align="center" width="30%">
																<bean:write name="custoPavimentoCalcada" property="pavimentoCalcada.descricao"/>
															</td>
													   	<%}else { %>
													   		<td align="center" width="30%">
																<a href="javascript:redirecionarSubmit('exibirAtualizarCustoPavimentoPorRepavimentadoraAction.do?acao=atualizarCalcada&idAtualizacao=<%=""+custoPavimentoCalcada.getId()%>');" >
																	<div align="center"><bean:write name="custoPavimentoCalcada" property="pavimentoCalcada.descricao"/></div>
																</a>
															</td>
													   	<%} %>
														
														<td align="right" width="12%"><bean:write name="custoPavimentoCalcada" property="valorPavimento" formatKey="money.format"/></td>
														<td align="center" width="25%"><bean:write name="custoPavimentoCalcada" property="dataVigenciaInicial" format="dd/MM/yyyy"/></td>
														<td align="center" width="26%"><bean:write name="custoPavimentoCalcada" property="dataVigenciaFinal" format="dd/MM/yyyy"/></td>
											
													</tr>
							
											</logic:iterate>
										
						
									</table>
									</div></td>
								</tr>
								
							</table>
								
							<table bgcolor="cbe5fe" border="0" width="100%" align="left">
								<tr>
									<td>
										<input name="button" type="button" class="bottonRightCol"
											value="Remover Custo do Pavimento de Calçada" onclick="javascript:removerCalcada();">
									</td>
								</tr>
							</table>
								
							<tr>
								<td colspan="5" bordercolor="#FFFFFF" bgcolor="#cbe5fe"><hr></td>
							</tr>
								
							</td>
						</tr>
						
						</logic:present>
						
						<table width="100%" align="center" bgcolor="#cbe5fe" border="0" 
							   cellpadding="0" cellspacing="0">
							
							<tr>
								<td align="left" bgcolor="#cbe5fe" colspan="1">
									<input name="button" type="button"
										   class="bottonRightCol" value="Voltar Filtro"
										   onclick="window.location.href='<html:rewrite page="/exibirFiltrarCustoPavimentoPorRepavimentadoraAction.do?limpar=S"/>'"
										   align="left" style="width: 80px;">
								</td>
								
								<td align="right" bgcolor="#cbe5fe" valign="top" colspan="6">
	                            	<a href="javascript:toggleBox('demodiv',1);">
	                                    <img align="right" border="0" 
	                                    	 src="<bean:message key='caminho.imagens'/>print.gif"  
	                                    	 title="Imprimir Localidades"/></a>
	                            </td>
							</tr>
						</table>
						
					</table>
					<p>&nbsp;</p>
					</td>
				</tr>

			</table>
			</td>
		</tr>
	</table>
	
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=/gsan/gerarRelatorioCustoPavimentoPorRepavimentadoraManterAction.do" />	
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
