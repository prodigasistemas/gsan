<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.atendimentopublico.ordemservico.bean.ManterDadosAtividadesOrdemServicoHelper"%>
<%@ page import="gcom.atendimentopublico.ordemservico.bean.OSAtividadePeriodoExecucaoHelper"%>
<%@ page import="gcom.atendimentopublico.ordemservico.bean.OSExecucaoEquipeHelper"%>
<%@ page import="gcom.atendimentopublico.ordemservico.OsExecucaoEquipeComponentes"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="java.util.Date"%>
<%@ page import="gcom.gui.GcomAction"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ManterComponenteExecucaoOSActionForm"/>

<script>

function limpar(){

	var form = document.forms[0];
	
	form.idFuncionario.value = "";
	form.descricaoFuncionario.value = "";
			   
	//Coloca o foco no objeto selecionado
	form.idFuncionario.focus();
}

function limparDescricao(){
	
	var form = document.forms[0];
	form.descricaoFuncionario.value = "";
}

function adicionar(){
	var form = document.forms[0];
	
	if (validateManterComponenteExecucaoOSActionForm(form)){
		
		if (form.idFuncionario.value.length < 1 && 
			form.nomeComponente.value.length < 1){
			alert("Informe Funcionário ou Nome do Componente");
			form.idFuncionario.focus();
		}
		else if (form.idFuncionario.value.length > 0 && 
				 form.nomeComponente.value.length > 0){
			alert("Informe Funcionário ou Nome do Componente");
			form.idFuncionario.focus();
		}
		else{
			form.action = "/gsan/exibirManterComponenteExecucaoOSAction.do?adicionarComponente=OK";
			form.submit();
		}
	}
} 

function remover(objetoRemocao){
  redirecionarSubmit("/gsan/exibirManterComponenteExecucaoOSAction.do?removerComponente=" + objetoRemocao);
}

</script>
</head>

<body leftmargin="0" topmargin="0" onload="window.focus();resizePageSemLink(680, 470);setarFoco('${requestScope.nomeCampo}');">


<html:form action="/exibirManterComponenteExecucaoOSAction" method="post">

<html:hidden property="idAtividade"/>

<table width="652" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="652" valign="top" class="centercoltext">
    <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
          <td class="parabg">Manter Componentes da Equipe de Execução da Atividade da Ordem de Serviço</td>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0">
        <tr>
          <td colspan="4">
          	Informar os componentes da equipe de execução da atividade da Ordem de Serviço:
          </td>
        </tr>
        <tr>
          <td width="25%"><strong>Número da OS: </strong></td>
          <td width="75%" colspan="3">
          	<html:text property="numeroOSForm" size="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
          </td>
        </tr>
        <tr>
          <td width="25%"><strong>Atividade: </strong></td>
          <td width="75%" colspan="3">
          	<html:text property="descricaoAtividade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
          </td>
        </tr>
		<tr>
          <td width="25%"><strong>Equipe: </strong></td>
          <td width="75%" colspan="3">
          	<html:text property="nomeEquipe" size="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
          </td>
        </tr>
        <tr>
          <td colspan="4" height="10"></td>
        </tr>
		<tr>
          <td width="25%"><strong>Responsável: </strong></td>
          <td width="75%" colspan="3">
          	<html:radio property="responsavel" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString() %>" />Sim
			<html:radio property="responsavel" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString() %>" />Não
          </td>
        </tr>
        <tr>
      		<td WIDTH="25%"><strong>Funcionário:</strong></td>
        	<td width="75%" colspan="3">
        	
        		<html:text property="idFuncionario" size="4" maxlength="5" tabindex="4" onkeypress="limparDescricao();validaEnterComMensagem(event, 'exibirManterComponenteExecucaoOSAction.do', 'idFuncionario', 'Funcionário');"/>
				<a href="javascript:redirecionarSubmit('exibirManterComponenteExecucaoOSAction.do?pesquisarFuncionario=OK');">
				<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" alt="Pesquisar" border="0"></a>
	
				<logic:present name="corFuncionario">
	
					<logic:equal name="corFuncionario" value="exception">
						<html:text property="descricaoFuncionario" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					</logic:equal>
	
					<logic:notEqual name="corFuncionario" value="exception">
						<html:text property="descricaoFuncionario" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEqual>
	
				</logic:present>
	
				<logic:notPresent name="corFuncionario">
	
					<logic:empty name="ManterComponenteExecucaoOSActionForm" property="idFuncionario">
						<html:text property="descricaoFuncionario" value="" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					</logic:empty>
					<logic:notEmpty name="ManterComponenteExecucaoOSActionForm" property="idFuncionario">
						<html:text property="descricaoFuncionario" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEmpty>
					
	
				</logic:notPresent>
	        	
	        	<a href="javascript:limpar();">
	        	<img src="<bean:message key='caminho.imagens'/>limparcampo.gif" alt="Apagar" border="0"></a>
	        
	       </td>
	  	</tr>
		<tr>
          <td width="25%"><strong>Nome do Componente: </strong></td>
          <td width="75%" colspan="3">
          	<html:text property="nomeComponente" size="35"/>
          </td>
        </tr>
	  	<tr>
          <td colspan="4" height="10"></td>
        </tr>
        <tr>
          <td><strong>Componentes:</strong></td>
          <td colspan="3" align="right"><input type="button" class="bottonRightCol" value="Adicionar"
			tabindex="5" style="width: 80px" onclick="adicionar();"></td>
        </tr>
        <tr>
      		<td colspan="4">
      		
      			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<table width="100%" border="0" bgcolor="#99CCFF">
						<tr bgcolor="#99CCFF" height="18">
								<td width="10%" align="center"><strong>Remover</strong></td>
								<td width="10%" align="center"><strong>Resp.</strong></td>
								<td width="30%" align="center"><strong>Func.</strong></td>
								<td width="50%" align="center"><strong>Nome do Componente</strong></td>
						</tr>
						</table>
					</td>
				</tr>
      			</table>
      			
      			<logic:present name="colecaoManterDadosAtividadesOrdemServicoHelper">
      				
      				<logic:notEmpty name="colecaoManterDadosAtividadesOrdemServicoHelper">
      				
      				
      				<logic:iterate name="colecaoManterDadosAtividadesOrdemServicoHelper" id="dadosAtividadesOrdemServico" type="ManterDadosAtividadesOrdemServicoHelper">
      				
      				<logic:equal name="dadosAtividadesOrdemServico" property="ordemServicoAtividade.ordemServico.id" value="${requestScope.numeroOS}">
      				
      				<logic:equal name="dadosAtividadesOrdemServico" property="ordemServicoAtividade.atividade.id" value="${requestScope.idAtividade}">
      				
      					<logic:present name="dadosAtividadesOrdemServico" property="colecaoOSAtividadePeriodoExecucaoHelper">
      					
      					<bean:define name="dadosAtividadesOrdemServico" property="colecaoOSAtividadePeriodoExecucaoHelper" type="java.util.Collection" id="colecaoOSAtividadePeriodoExecucaoHelper" />
      					
      					<% Date dataExecucao = (Date) request.getAttribute("dataExecucaoEquipeComponente"); %>
      					<% String horaInicioExecucao = (String) request.getAttribute("horaInicioExecucaoEquipeComponente"); %>
      					<% String horaFimExecucao = (String) request.getAttribute("horaFimExecucaoEquipeComponente"); %>
      					
      					<logic:iterate name="colecaoOSAtividadePeriodoExecucaoHelper" id="dadosOsAtividadePeriodoExecucao" type="OSAtividadePeriodoExecucaoHelper">
	      					
	      				
	      					<%if (dadosOsAtividadePeriodoExecucao.getDataExecucaoParaQuebra().equals(dataExecucao) &&
	      						dadosOsAtividadePeriodoExecucao.getHoraMinutoInicioParaQuebra()
	      						.equalsIgnoreCase(horaInicioExecucao) &&
	      						dadosOsAtividadePeriodoExecucao.getHoraMinutoFimParaQuebra()
	      						.equalsIgnoreCase(horaFimExecucao)){ %>
	      				
	      					
	      						<bean:define name="dadosOsAtividadePeriodoExecucao" property="colecaoOSExecucaoEquipeHelper" type="java.util.Collection" id="colecaoOSExecucaoEquipeHelper" />		
	      						
	      						<%Integer idEquipe = (Integer) request.getAttribute("idEquipe"); %>
	      						
	      						<logic:iterate name="colecaoOSExecucaoEquipeHelper" id="dadosOsExecucaoEquipeHelper" type="OSExecucaoEquipeHelper">
	      						
	      							<%if (dadosOsExecucaoEquipeHelper.getOsExecucaoEquipe().getEquipe().getId()
	      								  .equals(idEquipe)){ %>
	      								  
	      								 <logic:present name="dadosOsExecucaoEquipeHelper" property="colecaoOsExecucaoEquipeComponentes">
	      								 
	      									<bean:define name="dadosOsExecucaoEquipeHelper" property="colecaoOsExecucaoEquipeComponentes" type="java.util.Collection" id="colecaoOsExecucaoEquipeComponentes" />		 
	      								 
	      								 	
	      								 		<!-- Coleção Componente -->
	      					
	      										<div style="width: 100%; height: 100; overflow: auto;">	
	      				
						      					<table width="100%" cellpadding="0" cellspacing="0">
													<tr>
														<td>
														
									
									
														<table width="100%" align="center" bgcolor="#99CCFF">
									
														<!--corpo da segunda tabela-->
									
														<% String cor = "#cbe5fe";%>
													
														<logic:iterate name="colecaoOsExecucaoEquipeComponentes" id="osExecucaoEquipeComponentes" type="OsExecucaoEquipeComponentes">
																			
															<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
																	cor = "#FFFFFF";%>
																	<tr bgcolor="#FFFFFF" height="18">	
															<%} else{	
																	cor = "#cbe5fe";%>
																	<tr bgcolor="#cbe5fe" height="18">		
															<%}%>
																			
																<td width="10%" align="center">
																							
																	<a href="javascript:if(confirm('Confirma remoção?')){remover(<%="" + GcomAction.obterTimestampIdObjeto(osExecucaoEquipeComponentes) %>);}" alt="Remover"><img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0"></a>
																						
																</td>
																					
																<td width="10%" align="center">
																
																	<logic:equal name="osExecucaoEquipeComponentes" property="indicadorResponsavel" value="1">
																		SIM
																	</logic:equal>
																	
																	<logic:notEqual name="osExecucaoEquipeComponentes" property="indicadorResponsavel" value="1">
																		NÃO
																	</logic:notEqual>
																	
																</td>
																
																<td width="30%" align="center">
																
																	<logic:present name="osExecucaoEquipeComponentes" property="funcionario">
																		<bean:write name="osExecucaoEquipeComponentes" property="funcionario.id"/>
																	</logic:present>
																	
																	<logic:notPresent name="osExecucaoEquipeComponentes" property="funcionario">
																		&nbsp;
																	</logic:notPresent>
																	
																</td>
																
																<td width="50%" align="left">
																
																	<logic:present name="osExecucaoEquipeComponentes" property="funcionario">
																		<bean:write name="osExecucaoEquipeComponentes" property="funcionario.nome"/>
																	</logic:present>
																	
																	<logic:notPresent name="osExecucaoEquipeComponentes" property="funcionario">
																		<bean:write name="osExecucaoEquipeComponentes" property="nomeComponente"/>
																	</logic:notPresent>
																	
																</td>
																													
															</tr>
																			
														</logic:iterate>
									
														</table>
									
									 
									 					</td>
													</tr>
												</table>
							
												</div>
							
														
												<!-- FIM Coleção Material -->
	      								 	
	      								 	
	      								 </logic:present>
	      								 
	      							<%}%>
	      						
	      						</logic:iterate>
	      					
	      					
	      					
	      					<%} %>
	      								
						</logic:iterate>
						
						</logic:present>
      				
      				</logic:equal>
      				
      				</logic:equal>
      				
      				</logic:iterate>
					
					</logic:notEmpty>
				
				
				</logic:present>
				
				
				
			</td>
      	</tr>
        
        <tr>
          	<td colspan="4" height="20"><input type="button" class="bottonRightCol" value="Voltar"
			style="width: 80px" onclick="window.location.href='/gsan/exibirManterHorasExecucaoEquipeOSAction.do';"></td>
        </tr>
      </table>
      
	</td>
  </tr>
</table>

</body>
</html:form>
</html:html>




