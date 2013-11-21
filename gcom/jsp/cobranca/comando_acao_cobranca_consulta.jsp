<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page
	import="gcom.cobranca.CobrancaAcaoAtividadeComando, gcom.cobranca.CobrancaAtividade, gcom.atendimentopublico.ordemservico.OrdemServico, gcom.cobranca.bean.CobrancaAcaoOrdemServicoNaoAceitasHelper"%>
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">

function reloadOrdemServico(obj){
	var form = document.forms[0];
	if (getParameter('page.offset') != '') {
		form.action = 'comandosAcaoCobrancaFiltrarAction.do?acao=selecionar&id='+obj+'&page.offset='+getParameter('page.offset');
	} else {
		form.action = 'comandosAcaoCobrancaFiltrarAction.do?acao=selecionar&id='+obj;
	}
	form.submit();
}

function adicionar(){
	var form = document.forms[0];
	if (form.motivoNaoAceitacao.value != '') {
		if (getParameter('page.offset') != '') {
			form.action = 'comandosAcaoCobrancaFiltrarAction.do?acao=adicionar&page.offset='+getParameter('page.offset');
		} else {
			form.action = 'comandosAcaoCobrancaFiltrarAction.do?acao=adicionar';
		}
		form.submit();
	} else {
		alert("Informe o Motivo de Não Aceitação.");
	}
}

function concluir(){
	var form = document.forms[0];

	if (typeof( form.idOrdemServico ) == 'undefined' && form.motivoNaoAceitacao.value == '') {
		alert("Informe o Motivo de Não Aceitação.");
	} else {
		form.action = 'comandosAcaoCobrancaFiltrarAction.do?acao=concluir';
		form.submit();
	}
}

function getParameter(name) {
	name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
	var regexS = "[\\?&]"+name+"=([^&#]*)";
	var regex = new RegExp( regexS );
	var results = regex.exec( window.location.href );
	
	return (results == null)? "" : results[1];
}


</script>
</head>
<body leftmargin="5" topmargin="5">
<html:form action="/comandosAcaoCobrancaFiltrarAction" method="post"
	name="ComandosAcaoCobrancaFiltrarActionForm"
	type="gcom.gui.cobranca.ComandosAcaoCobrancaFiltrarActionForm">
<%@ include file="/jsp/util/cabecalho.jsp"%> <%@ include
	file="/jsp/util/menu.jsp"%>

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
		<td valign="top" class="centercoltext"><!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
                <tbody><tr> 
                  <td></td>
                </tr>
              </tbody></table>
              <table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
                <tbody><tr> 
                  <td width="11"><img src="imagens/parahead_left.gif" border="0"></td>

                  <td class="parabg">Informar Não Aceitação de Encerramento de OS</td>
                  <td valign="top" width="11"><img src="imagens/parahead_right.gif" border="0"></td>
                </tr>
              </tbody></table> 
			<table width="100%" border="0">
			
            <tr> 
                <td height="12" colspan="2"></td>
            </tr>
			<logic:present name="objetoCobrancaAcao" scope="session">
            	<tr>
					<td width="28%"><strong>Ação de Cobrança:</strong></td>
					<td><html:text name="objetoCobrancaAcao" property="descricaoCobrancaAcao"
						size="30" maxlength="30" style="background-color:#EFEFEF; border:0"
						readonly="true" /></td>
				</tr>
			</logic:present>
			<logic:present name="objetoCobrancaGrupo" scope="session">
            	<tr>
					<td width="28%"><strong>Grupo de Cobrança:</strong></td>
					<td><html:text name="objetoCobrancaGrupo" property="descricao"
						size="30" maxlength="30" style="background-color:#EFEFEF; border:0"
						readonly="true" /></td>
				</tr>
			</logic:present>
	         <logic:present name="ordemServicoConsulta" scope="session">
			 	
					<bean:define name="ordemServicoConsulta"
						property="servicoTipo" id="servicoTipo" />
						
					<bean:define name="ordemServicoConsulta"
						property="atendimentoMotivoEncerramento" id="atendimentoMotivoEncerramento" />
						
	            	<tr>
						<td width="28%"><strong>Descri&ccedil;&atilde;o do Servi&ccedil;o:</strong></td>
						<td><html:text name="servicoTipo" property="descricao"
							size="30" maxlength="30" style="background-color:#EFEFEF; border:0"
							readonly="true" /></td>
					</tr>
					<tr>
						<td width="28%"><strong>Data do Encerramento:</strong></td>
						<td><html:text name="ordemServicoConsulta" property="dataEncerramento"
							size="30" maxlength="30" style="background-color:#EFEFEF; border:0"
							readonly="true" /></td>
					</tr>
					<tr>
						<td width="28%"><strong>Motivo de Encerramento:</strong></td>
						<td><html:text name="atendimentoMotivoEncerramento" property="descricao"
							size="30" maxlength="30" style="background-color:#EFEFEF; border:0"
							readonly="true" /></td>
					</tr>
			 	</logic:present>
			 	
			<logic:notPresent name="ordemServicoConsulta" scope="session">
	            <tr> 
	                <td height="24" colspan="2"></td>
	            </tr>
	            <tr>
	                <td bgcolor="#5782E6" colspan="6">
	                	<div align="center" class="style11">
	                		<strong>Ordens de Servi&ccedil;o</strong>
	               		</div>
	            	</td>
				</tr>
				<tr>
					<td colspan="2">
					<div align="left">
					<table width="100%" align="center" bgcolor="#99CCFF">
						<!--corpo da segunda tabela-->
						<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
							<td width="3%">
								<div align="center"><strong></strong></div>
							</td>
							<td width="10%">
								<div align="center"><strong>OS</strong></div>
							</td>
							<td width="10%">
								<div align="center"><strong>Matr&iacute;cula</strong></div>
							</td>
							<td width="35%">
								<div align="center"><strong>Descri&ccedil;&atilde;o do Servi&ccedil;o</strong></div>
							</td>
							<td width="22%">
								<div align="center"><strong>Data do Encerramento</strong></div>
							</td>
							<td width="30%">
								<div align="center"><strong>Motivo de Encerramento</strong></div>
							</td>
						</tr>
	
						<%String cor = "#FFFFFF";%>
	
						<!--Fim Tabela Reference a Páginação da Tela de Processo-->
						<%--Esquema de paginação--%>
	
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"				
							maxPageItems="10" items="${sessionScope.totalRegistros}">
	
							<pg:param name="pg" />
							<pg:param name="q" />
							<logic:present name="colecaoCobrancaAcaoOrdemServicoNaoAceitasHelper"
								scope="session">
								<logic:iterate name="colecaoCobrancaAcaoOrdemServicoNaoAceitasHelper"
									id="cobrancaAcaoOrdemServicoNaoAceitasHelper" type="CobrancaAcaoOrdemServicoNaoAceitasHelper">
									<pg:item>
										<%if (cor.equalsIgnoreCase("#cbe5fe")) {
											cor = "##FFFFFF";%>
										<tr bgcolor="#cbe5fe">
											<%} else {
											cor = "#cbe5fe";%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<c:set var="count" value="${count+1}" />
											<c:choose>
												<c:when test="${count%2 == '1'}">
													<tr bgcolor="#FFFFFF">
												</c:when>
												<c:otherwise>
													<tr bgcolor="#cbe5fe">
												</c:otherwise>
											</c:choose>
											<td>
												<div align="center">
													<label > 
														<html:radio 
															value="${count}"
															property="idOrdemServico"
															onclick="javascript:reloadOrdemServico('${count}');" /> 
															
														<html:hidden property="idOS" name="idOS" value="${cobrancaAcaoOrdemServicoNaoAceitasHelper.ordemServico.id}" />
													</label> 
												</div>
											</td>
											<td bordercolor="#90c7fc">
												<div align="center">
													${cobrancaAcaoOrdemServicoNaoAceitasHelper.ordemServico.id}
												</div>
											</td>
											<td bordercolor="#90c7fc">
												<div align="center">
													${cobrancaAcaoOrdemServicoNaoAceitasHelper.ordemServico.imovel.id}
												</div>
											</td>
											<td bordercolor="#90c7fc">
											<div align="center">
											<bean:define name="cobrancaAcaoOrdemServicoNaoAceitasHelper"
												property="ordemServico" id="ordemServico" />
											<bean:define name="cobrancaAcaoOrdemServicoNaoAceitasHelper"
												property="cobrancaAcaoOrdemServicoNaoAceitas" id="cobrancaAcaoOrdemServicoNaoAceitas" />
												
											<logic:present name="ordemServico"
												property="servicoTipo">
												<bean:define name="ordemServico"
													property="servicoTipo" id="servicoTipo" />
												<bean:write name="servicoTipo"
													property="descricao" />
											</logic:present></div>
											</td>
											
											<td bordercolor="#90c7fc">
											<div align="center"><logic:present
												name="ordemServico"
												property="dataEncerramento">
	
												<bean:write name="ordemServico"
													property="dataEncerramento" format="dd/MM/yyyy HH:mm:ss"/>
	
											</logic:present></div>
											</td>
											
											<td bordercolor="#90c7fc">
											<div align="center"><logic:present
												name="ordemServico"
												property="atendimentoMotivoEncerramento">
	
												<bean:define name="ordemServico"
													property="atendimentoMotivoEncerramento" id="atendimentoMotivoEncerramento" />
	
												<bean:write name="atendimentoMotivoEncerramento"
													property="descricao" />
	
											</logic:present></div>
											</td>
											
										</tr>
									</pg:item>
									
								</logic:iterate>
							</logic:present>
					</table>
					<table width="100%" border="0">
						<tr>
							<td><div align="center"><strong><%@ include	file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
							</td>
						</tr>
					</table>
					</div>
					</td>
					</tr>
					
			</pg:pager>
				</logic:notPresent>
				
				<logic:present name="ordemServicoSelecionada" scope="session">
						<tr>
							<td colspan="2">
							<hr>
							</td>
						</tr>
						<tr>
							<td width="15%">
								<div align="left">
									<strong>Motivo de Não Aceitação:<font color="#FF0000">*</font></strong>
								</div>
							</td>
							<td>
								<div align="left">
									<html:select property="motivoNaoAceitacao" >
										<html:option value="">&nbsp;</html:option>
										<logic:present name="colecaoMotivosNaoAceitacao" scope="session">
											<html:options collection="colecaoMotivosNaoAceitacao" labelProperty="descricaoMotivoNaoAceitacaoEncerramentoOS" property="id" />
										</logic:present>
									</html:select>
								</div>
							</td>
						</tr>
						<tr>
							<td width="15%" >
								<div align="left">
									<strong>Observação:</strong>
								</div>
							</td>
							<td>
								<div align="left">
									<html:text property="observacao" size="20" maxlength="55" style="text-transform: none;" /> 
									&nbsp;&nbsp;
									<logic:notPresent name="ordemServicoConsulta" scope="session">
										<input name="button" type="button" class="bottonRightCol"
											value="Atualizar"
											onclick="javascript:adicionar();"
											align="left" style="width: 80px;">
									</logic:notPresent>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
							<hr>
							</td>
						</tr>
				</logic:present>
			<tr>
				<td colspan="2"><strong></strong>
				<div align="left"><strong><font color="#FF0000"> </font></strong></div>
				</td>
			</tr>
			<tr>
				<td><input name="button" type="button" class="bottonRightCol"
						value="Voltar Filtro"
						onclick="window.location.href='<html:rewrite page="/exibirFiltrarComandosAcaoCobrancaAction.do?carregando=SIM"/>'"
						align="left" style="width: 80px;"></td>
				<td valign="top" align="right">
					<input name="button" type="button" class="bottonRightCol"
						value="Concluir"
						onclick="javascript:concluir();"
						align="left" style="width: 80px;">
				</td>
			</tr>
		</table>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>

<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioFiltrarComandoAcaoCobranca.do" />
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
