<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.gui.*,java.util.*" isELIgnored="false"%>

<%
String numeroPagina = request.getParameter("numeroPagina");
String voltarFiltro = request.getParameter("voltarFiltro");
String numeroPaginaAnterior = (Integer.parseInt(numeroPagina) - 1) + "";
String numeroPaginaPosterior = (Integer.parseInt(numeroPagina) + 1) + "";
StatusWizard statusWizard = (StatusWizard) session.getAttribute("statusWizard");
StatusWizard.StatusWizardItem itemWizard = statusWizard.retornarItemWizard(Integer.parseInt(numeroPagina));
pageContext.setAttribute("voltarFiltro", voltarFiltro);
pageContext.setAttribute("numeroPagina", numeroPagina);
pageContext.setAttribute("itemWizard", itemWizard);
pageContext.setAttribute("numeroPaginaAnterior", numeroPaginaAnterior);
pageContext.setAttribute("numeroPaginaPosterior", numeroPaginaPosterior);
%>

<logic:notEmpty name="voltarFiltro" scope="page">
	<logic:equal name="numeroPagina" value="1">
		<input type="hidden" name="indicadorVoltar" />
	</logic:equal>
</logic:notEmpty>

<table width="100%" border="0" align="center" cellpadding="0" cellspacing="3">
	<tr>
		<!-- Apresenta na tela o botão Voltar -->
		<logic:equal name="numeroPagina" 
			value="<%=""+((StatusWizard)session.getAttribute("statusWizard")).getRelacaoNumeroPaginaCaminho().size()%>">
			<%StatusWizard.StatusWizardItem item = statusWizard
					.retornarItemWizard(Integer.parseInt(numeroPaginaAnterior));%>
			<td align="right"  width="53%">
				<a href="javascript:botaoAvancarTelaEspera('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?action=<%=item.getCaminhoActionInicial()%>');document.forms[0].submit();">
					<img src="imagens/voltar.gif" border="0"></a>
			</td>
			<td align="left" width="47%">
				<input name="voltar" type="button" class="buttonAbaRodape" value="  Voltar  "
					onClick="javascript:botaoAvancarTelaEspera('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?action=<%=item.getCaminhoActionInicial()%>');document.forms[0].submit();" />
			</td>
   			&nbsp;&nbsp;
   		</logic:equal>

		<!-- Apresenta na tela o botão Avançar -->
		<logic:equal name="numeroPagina" value="1">
			<td align="right" width="75%">
				<input name="avancar" type="button" class="buttonAbaRodape" value="Avan&ccedil;ar"
					onClick="javascript:botaoAvancarTelaEspera('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?destino=<bean:write name="numeroPaginaPosterior"/>&action=<bean:write name="itemWizard" property="caminhoActionFinal"/>');" />
			</td>
			<td align="left" width="25%">
				<a href="javascript:botaoAvancarTelaEspera('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?destino=<bean:write name="numeroPaginaPosterior"/>&action=<bean:write name="itemWizard" property="caminhoActionFinal"/>');">
					<img src="imagens/avancar.gif" border="0" /></a>
			</td>
		</logic:equal>
		
		<!-- Apresenta na tela os dois botões, Voltar e Avançar -->
		<logic:notEqual name="numeroPagina"
			value="<%=""+((StatusWizard)session.getAttribute("statusWizard")).getRelacaoNumeroPaginaCaminho().size()%>">
			<logic:notEqual name="numeroPagina" value="1">
				<%StatusWizard.StatusWizardItem item2 = statusWizard
						.retornarItemWizard(Integer.parseInt(numeroPaginaAnterior));%>
				<td align="right"  width="45%"></td>
				<td align="right"  width="10%">
					<a href="javascript:botaoAvancarTelaEspera('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?action=<%=item2.getCaminhoActionInicial()%>');document.forms[0].submit();">
						<img src="imagens/voltar.gif" border="0"></a>
				</td>
				<td align="left" width="10%">
					<input name="voltar" type="button" class="buttonAbaRodape" value="  Voltar  "
						onClick="javascript:botaoAvancarTelaEspera('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?action=<%=item2.getCaminhoActionInicial()%>');document.forms[0].submit();" />
				</td>
	   			&nbsp;&nbsp;
				<td align="right" width="10%">
					<input name="avancar" type="button" class="buttonAbaRodape" value="Avan&ccedil;ar"
						onClick="javascript:botaoAvancarTelaEspera('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?destino=<bean:write name="numeroPaginaPosterior"/>&action=<bean:write name="itemWizard" property="caminhoActionFinal"/>');" />
				</td>
				<td align="left" width="10%">
					<a href="javascript:botaoAvancarTelaEspera('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?destino=<bean:write name="numeroPaginaPosterior"/>&action=<bean:write name="itemWizard" property="caminhoActionFinal"/>');">
						<img src="imagens/avancar.gif" border="0" /></a>
				</td>
				<td align="right" width="15%"></td>
			</logic:notEqual>
		</logic:notEqual>
	</tr>
</table>
<table width="100%" border="0" align="center" cellpadding="0"cellspacing="3">
	<tr>
		<td colspan="9" width="100%" height="1px" bgcolor="#000000"></td>
	</tr>
	<tr>
		<logic:notEmpty name="statusWizard" property="caminhoActionVoltarFiltro">
			<td colspan="2" align="left" width="17%">
			<input name="voltar Filtro" type="button" class="bottonRightCol" value="Voltar" style="width: 80px"
				onClick="javascript:window.location.href='/gsan/<bean:write name="statusWizard" property="caminhoActionVoltarFiltro"/>';" />
			</td>	
		</logic:notEmpty>
		
		<logic:empty name="statusWizard" property="caminhoActionVoltarFiltro">
			<td colspan="5" align="left" width="66%">
				<input name="desfazer" type="button" class="bottonRightCol" value="Desfazer" style="width: 80px"
					onClick="javascript:window.location.href='${statusWizard.caminhoActionDesfazerInserir}';" />&nbsp;&nbsp;
				<logic:notPresent scope="session" name="origemGIS">
					<input name="cancelar" type="button" class="bottonRightCol" value="Cancelar" style="width: 80px"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'" />						
				</logic:notPresent>
				<logic:present scope="session" name="origemGIS">
					<input name="cancelar" type="button" class="bottonRightCol" value="Cancelar" style="width: 80px"
						onClick="javascript:self.close()" />
				</logic:present>

			</td>
		</logic:empty>
		
		<logic:notEmpty name="statusWizard" property="caminhoActionVoltarFiltro">
			<td colspan="5" align="left" width="66%">
				<input name="desfazer" type="button" class="bottonRightCol" value="Desfazer" style="width: 80px"
					onClick="javascript:window.location.href='${statusWizard.caminhoActionDesfazer}';" />&nbsp;&nbsp;
				<logic:notPresent scope="session" name="origemGIS">
					<input name="cancelar" type="button" class="bottonRightCol" value="Cancelar" style="width: 80px"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'" />						
				</logic:notPresent>
				<logic:present scope="session" name="origemGIS">
					<input name="cancelar" type="button" class="bottonRightCol" value="Cancelar" style="width: 80px"
						onClick="javascript:self.close()" />
				</logic:present>
			</td>
		</logic:notEmpty>
		
		<td colspan="2" align="right" width="17%">
			<input name="concluir" type="button"  id="botaoConcluir" class="bottonRightCol" style="width: 80px" value="Concluir"
				onClick="javascript:botaoAvancarTelaEspera('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?concluir=true&action=<bean:write name="itemWizard" property="caminhoActionFinal"/>');" />
		</td>
	</tr>
	<tr>
		<td colspan="9" width="100%">
			<input name="consultarProgramacao" type="button" class="bottonRightCol" style="width: 350px" value="Consultar Programa&ccedil;&atilde;o de Abastecimento/Manuten&ccedil;&atilde;o"
				onClick="gerarURLConsultarProgramacao();"/>
			
			<%-- <logic:present name="gerarOSAutomativa"> 
			
				<input name="gerarOS" type="button" class="bottonRightCol" style="width: 100px" value="Gerar OS"
				onClick="gerarURLGerarOrdemServico(${sessionScope.servicoTipo});"/>
			</logic:present>

			<logic:notPresent name="gerarOSAutomativa">
				<input name="gerarOS" type="button" class="bottonRightCol" style="width: 100px" value="Gerar OS"
				onClick="" disabled/>
			</logic:notPresent> --%> 
		</td>
	</tr>
</table>

<script>

function gerarURLConsultarProgramacao(){

	var form = document.forms[0];
	
	var idMunicipio = form.idMunicipio.value;
	var cdBairro = form.cdBairro.value;
	var idAreaBairro = form.idBairroArea.value;
	
	if (idMunicipio.length > 0 && cdBairro.length > 0 && (idAreaBairro.length > 0 && idAreaBairro != "-1")){
		abrirPopup('/gsan/exibirConsultarProgramacaoAbastecimentoManutencaoAction.do?idMunicipio=' + 
		idMunicipio + "&codigoBairro=" + cdBairro + "&areaBairro=" + idAreaBairro + "&tela=suja", 310, 640);
	}
	else{
		abrirPopup('/gsan/exibirConsultarProgramacaoAbastecimentoManutencaoAction.do?tela=limpa', 310, 640);
	}
	
}

 /*function gerarURLGerarOrdemServico(idServicoTipo){

	var form = document.forms[0];
	
	var idImovel = form.idImovel.value;
	var idTipoSolicitacao = form.tipoSolicitacao.value;
	
	if (idImovel.length > 0){
		abrirPopup('/gsan/exibirGerarOrdemServicoInserirRAAction.do?idServicoTipo=' + idServicoTipo + "&idImovel=" + idImovel+"&idTipoSolicitacao="+idTipoSolicitacao , 420, 760);
	}
	else{
		abrirPopup('/gsan/exibirGerarOrdemServicoInserirRAAction.do?idServicoTipo=' + idServicoTipo+"&idTipoSolicitacao="+idTipoSolicitacao, 420, 760);
	}
	
} */

</script>