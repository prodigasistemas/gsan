<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.atendimentopublico.ordemservico.bean.ManterDadosAtividadesOrdemServicoHelper"%>
<%@ page import="gcom.atendimentopublico.ordemservico.bean.OSAtividadePeriodoExecucaoHelper"%>
<%@ page import="gcom.atendimentopublico.ordemservico.bean.OSExecucaoEquipeHelper"%>

<%@ page import="gcom.gui.GcomAction"%>
<%@ page import="gcom.util.Util"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ManterHorasExecucaoEquipeOSActionForm"/>

<script>

function limpar(){

	var form = document.forms[0];
	
	form.idEquipeNaoProgramada.value = "";
	form.descricaoEquipeNaoProgramada.value = "";
			   
	//Coloca o foco no objeto selecionado
	form.idEquipeNaoProgramada.focus();
}

function limparDescricao(){
	
	var form = document.forms[0];
	form.descricaoEquipeNaoProgramada.value = "";
}

function remover(idAtividade){
 	var form = document.forms[0];
 	
	form.action = "/gsan/exibirManterDadosAtividadesOrdemServicoAction.do?removerAtividade=" + idAtividade;
	if (confirm("Confirma remoção?")){
		form.submit();
	}
}

function adicionar(){
	var form = document.forms[0];
	
	if (validateManterHorasExecucaoEquipeOSActionForm(form)){
		
		if (form.horaInicioExecucao.value.length < 5){
			alert("Hora Início de Execução inválida.");
			form.horaInicioExecucao.focus();
		}
		else if (form.horaFimExecucao.value.length < 5){
			alert("Hora Fim de Execução inválida.");
			form.horaFimExecucao.focus();
		}
		else if (form.idEquipeProgramada.value.length < 1 && 
			form.idEquipeNaoProgramada == undefined){
			alert("Informe Equipe Programada");
			form.idEquipeNaoProgramada.focus();
		}
		else if (form.idEquipeProgramada.value.length < 1 && 
			(form.idEquipeNaoProgramada != undefined && form.idEquipeNaoProgramada.value.length < 1)){
			alert("Informe Equipe Programada ou Outra Equipe");
			form.idEquipeNaoProgramada.focus();
		}
		else if (form.idEquipeProgramada.value.length > 0 && 
			(form.idEquipeNaoProgramada != undefined && form.idEquipeNaoProgramada.value.length > 0)){
			alert("Informe Equipe Programada ou Outra Equipe");
			form.idEquipeNaoProgramada.focus();
		}
		else{
			form.action = "/gsan/exibirManterHorasExecucaoEquipeOSAction.do?adicionarPeriodoEquipe=OK";
			form.submit();
		}
	}
}


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


function remover(objetoRemocao){
  redirecionarSubmit("/gsan/exibirManterHorasExecucaoEquipeOSAction.do?removerPeriodoEquipe=" + objetoRemocao);
}


function componentes(idEquipe, dataExecucao, horaInicio, horaFim){
	
	var form = document.forms[0];
  	
  	window.location.href="/gsan/exibirManterComponenteExecucaoOSAction.do?numeroOS=" + form.numeroOS.value 
  	+ "&idAtividade=" + form.idAtividade.value
  	+ "&descricaoAtividade=" + form.descricaoAtividade.value + "&idEquipe=" + idEquipe 
  	+ "&dataExecucaoEquipeComponente=" + dataExecucao + "&horaInicioExecucaoEquipeComponente=" + horaInicio 
  	+ "&horaFimExecucaoEquipeComponente=" + horaFim;
  	
  	//redirecionarSubmit("/gsan/exibirManterComponenteExecucaoOSAction.do");
  	
  	/*redirecionarSubmit("/gsan/exibirManterComponenteExecucaoOSAction.do?idAtividade=" + form.idAtividade.value
  	+ "&descricaoAtividade=" + form.descricaoAtividade.value + "&idEquipe=" + idEquipe 
  	+ "&dataExecucaoEquipeComponente=" + dataExecucao + "&horaInicioExecucaoEquipeComponente=" + horaInicio 
  	+ "&horaFimExecucaoEquipeComponente=" + horaFim);*/
}
 
</script>
</head>

<body leftmargin="0" topmargin="0" onload="window.focus();resizePageSemLink(680, 600);setarFoco('${requestScope.nomeCampo}');">

<html:form action="/exibirManterHorasExecucaoEquipeOSAction" method="post">

<html:hidden property="idAtividade"/>
<html:hidden property="dataEncerramentoOS"/>

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
          <td class="parabg">Manter Horas de Execução da Equipe da Ordem de Serviço</td>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0">
        <tr>
          <td colspan="4">
          	Apropriar as horas de execução da equipe para a atividade da Ordem de Serviço:
          </td>
        </tr>
        <tr>
          <td width="25%"><strong>Número da OS: </strong></td>
          <td width="75%" colspan="3">
          	<html:text property="numeroOS" size="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
          </td>
        </tr>
        <tr>
          <td width="25%"><strong>Atividade: </strong></td>
          <td width="75%" colspan="3">
          	<html:text property="descricaoAtividade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
          </td>
        </tr>
        <tr>
          <td colspan="4" height="10"></td>
        </tr>
        <tr>
          <td width="25%"><strong>Data de Execução:<span style="color:#FF0000">*</span></strong></td>
          <td width="75%" colspan="3">
          	
          	<logic:present name="desabilitarDataExecucao">
	          	<html:text property="dataExecucao" size="11" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
	          	<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif"
				width="20" border="0" align="absmiddle" alt="Exibir Calendário" />&nbsp;<strong>(dd/mm/aaaa)</strong>
          	</logic:present>
          	
          	<logic:notPresent name="desabilitarDataExecucao">
	          	<html:text property="dataExecucao" size="11" maxlength="10" onkeyup="mascaraData(this, event)"/>
	          	<a href="javascript:abrirCalendario('ManterHorasExecucaoEquipeOSActionForm', 'dataExecucao')">
				<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif"
				width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>&nbsp;<strong>(dd/mm/aaaa)</strong>
          	</logic:notPresent>
          	
          </td>
        </tr>
        <tr>
          <td width="25%"><strong>Intervalo de Execução:<span style="color:#FF0000">*</span></strong></td>
          <td width="75%" colspan="3">
          	<html:text property="horaInicioExecucao" size="6" tabindex="1" maxlength="5" onkeyup="mascaraHora(this, event)"/><strong>&nbsp;&nbsp;(hh:mm) às&nbsp;&nbsp;</strong> 
          	<html:text property="horaFimExecucao" size="6" tabindex="2" maxlength="5" onkeyup="mascaraHora(this, event)"/><strong>&nbsp;&nbsp;(hh:mm)</strong>
          </td>
        </tr>
        <tr>
          <td colspan="4" height="10"></td>
        </tr>
        <tr>
          <td width="25%"><strong>Equipe Programada:</strong></td>
          <td width="75%" colspan="3">
          	<html:select property="idEquipeProgramada" style="width: 200px;" tabindex="3">
				<html:option value="">&nbsp;</html:option>
				<logic:present name="colecaoEquipe">
					<html:options collection="colecaoEquipe" labelProperty="nome" property="id"/>
				</logic:present>
			</html:select>
          </td>
        </tr>
        <tr>
          <td height="10"></td>
          <td colspan="3"><strong>OU</strong></td>
        </tr>
        
        <logic:present name="documentoCobranca">
        
        <tr>
      		<td WIDTH="25%"><strong>Outra Equipe:</strong></td>
        	<td width="75%" colspan="3">
        	
        		<html:text property="idEquipeNaoProgramada" size="4" maxlength="5" tabindex="4" onkeypress="limparDescricao();validaEnterComMensagem(event, 'exibirManterHorasExecucaoEquipeOSAction.do', 'idEquipeNaoProgramada', 'Equipe não Programada');"/>
				<a href="javascript:redirecionarSubmit('exibirManterHorasExecucaoEquipeOSAction.do?pesquisarEquipe=OK');">
				<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" alt="Pesquisar" border="0"></a>
	
				<logic:present name="corEquipe">
	
					<logic:equal name="corEquipe" value="exception">
						<html:text property="descricaoEquipeNaoProgramada" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					</logic:equal>
	
					<logic:notEqual name="corEquipe" value="exception">
						<html:text property="descricaoEquipeNaoProgramada" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEqual>
	
				</logic:present>
	
				<logic:notPresent name="corEquipe">
	
					<logic:empty name="ManterHorasExecucaoEquipeOSActionForm" property="idEquipeNaoProgramada">
						<html:text property="descricaoEquipeNaoProgramada" value="" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					</logic:empty>
					<logic:notEmpty name="ManterHorasExecucaoEquipeOSActionForm" property="idEquipeNaoProgramada">
						<html:text property="descricaoEquipeNaoProgramada" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEmpty>
					
	
				</logic:notPresent>
	        	
	        	<a href="javascript:limpar();">
	        	<img src="<bean:message key='caminho.imagens'/>limparcampo.gif" alt="Apagar" border="0"></a>
	        
	       </td>
	  	</tr>
	  	
	  	</logic:present>
	  	
	  	<logic:present name="registroAtendimento">
	  	
	  	<tr>
      		<td WIDTH="25%"><strong>Outra Equipe:</strong></td>
        	<td width="75%" colspan="3">
        	
        		<html:select property="idEquipeNaoProgramada" style="width: 200px;" tabindex="4">
					<html:option value="">&nbsp;</html:option>
					<logic:present name="colecaoEquipesPorUnidade">
						<html:options collection="colecaoEquipesPorUnidade" labelProperty="nome" property="id"/>
					</logic:present>
				</html:select>
				
        	</td>
	  	</tr>
	  	
        </logic:present>
       	
       	<tr>
          <td colspan="4" height="10"></td>
        </tr>
        <tr>
          <td><strong>Horas de execução:</strong></td>
          <td colspan="3" align="right"><input type="button" class="bottonRightCol" value="Adicionar"
			tabindex="5" style="width: 80px" onclick="adicionar();"></td>
        </tr>
        <tr>
      		<td colspan="4">
      		
      			<div style="width: 100%; height: 200; overflow: auto;">	
      		
      			<logic:present name="colecaoManterDadosAtividadesOrdemServicoHelper">
      			
      				<logic:notEmpty name="colecaoManterDadosAtividadesOrdemServicoHelper">
      				
      				
      				<logic:iterate name="colecaoManterDadosAtividadesOrdemServicoHelper" id="dadosAtividadesOrdemServico" type="ManterDadosAtividadesOrdemServicoHelper">
      				
      				<logic:equal name="dadosAtividadesOrdemServico" property="ordemServicoAtividade.ordemServico.id" value="${requestScope.numeroOS}">
      				
      				<logic:equal name="dadosAtividadesOrdemServico" property="ordemServicoAtividade.atividade.id" value="${requestScope.idAtividade}">
      				
      					<%java.util.Date dataExecucaoAnterior = null; %>
      					
      					<logic:present name="dadosAtividadesOrdemServico" property="colecaoOSAtividadePeriodoExecucaoHelper">
      					
      					<bean:define name="dadosAtividadesOrdemServico" property="colecaoOSAtividadePeriodoExecucaoHelper" type="java.util.Collection" id="colecaoOSAtividadePeriodoExecucaoHelper" />
      					
      					<logic:iterate name="colecaoOSAtividadePeriodoExecucaoHelper" id="dadosOsAtividadePeriodoExecucao" type="OSAtividadePeriodoExecucaoHelper">
	      					
	      				<bean:define name="dadosOsAtividadePeriodoExecucao" property="colecaoOSExecucaoEquipeHelper" type="java.util.Collection" id="colecaoOSExecucaoEquipeHelper" />
	      				
	      				
	      				<%if (dataExecucaoAnterior == null){ %>
	      				
	      					<div id="layerHideDataExecucao<%=""+ (Util.formatarData(dadosOsAtividadePeriodoExecucao.getDataExecucaoParaQuebra())).replace("/", "_")%>" style="display:block">
							
								<table width="100%" align="center" bgcolor="#79bbfd" border="0">
								<tr>
									<td align="center"><a href="javascript:extendeTabela('DataExecucao<%=""+ (Util.formatarData(dadosOsAtividadePeriodoExecucao.getDataExecucaoParaQuebra())).replace("/", "_")%>',true);"/>
										<strong>Data de Execução: <bean:write name="dadosOsAtividadePeriodoExecucao" property="osAtividadePeriodoExecucao.dataInicio" formatKey="date.format"/></strong></a></td>
								</tr>
								</table>
						
							</div>
						
							<div id="layerShowDataExecucao<%=""+ (Util.formatarData(dadosOsAtividadePeriodoExecucao.getDataExecucaoParaQuebra())).replace("/", "_")%>" style="display:none">
							
								<table width="100%" align="center" bgcolor="#79bbfd" border="0">
								<tr>
									<td align="center"><a href="javascript:extendeTabela('DataExecucao<%=""+ (Util.formatarData(dadosOsAtividadePeriodoExecucao.getDataExecucaoParaQuebra())).replace("/", "_")%>',false);"/>
										<strong>Data de Execução: <bean:write name="dadosOsAtividadePeriodoExecucao" property="osAtividadePeriodoExecucao.dataInicio" formatKey="date.format"/></strong></a></td>
								</tr>
								</table>
							
									<div id="layerHidePeriodoExecucao<%=""+ (Util.formatarData(dadosOsAtividadePeriodoExecucao.getDataExecucaoParaQuebra())).replace("/", "_") + dadosOsAtividadePeriodoExecucao.getHoraMinutoInicioParaQuebra().replace(":", "_") + dadosOsAtividadePeriodoExecucao.getHoraMinutoFimParaQuebra().replace(":","_")%>" style="display:block">
											
										<table width="100%" align="center" bgcolor="#90c7fc" border="0">
										<tr>
											<td align="left"><a href="javascript:extendeTabela('PeriodoExecucao<%=""+ (Util.formatarData(dadosOsAtividadePeriodoExecucao.getDataExecucaoParaQuebra())).replace("/", "_") + dadosOsAtividadePeriodoExecucao.getHoraMinutoInicioParaQuebra().replace(":", "_") + dadosOsAtividadePeriodoExecucao.getHoraMinutoFimParaQuebra().replace(":", "_")%>',true);"/>
												<strong>Período: <bean:write name="dadosOsAtividadePeriodoExecucao" property="osAtividadePeriodoExecucao.dataInicio" formatKey="hour.format"/> às <bean:write name="dadosOsAtividadePeriodoExecucao" property="osAtividadePeriodoExecucao.dataFim" formatKey="hour.format"/></strong></td>
										</tr>
										</table>
											
									</div>
								
									<div id="layerShowPeriodoExecucao<%=""+ (Util.formatarData(dadosOsAtividadePeriodoExecucao.getDataExecucaoParaQuebra())).replace("/", "_") + dadosOsAtividadePeriodoExecucao.getHoraMinutoInicioParaQuebra().replace(":", "_") + dadosOsAtividadePeriodoExecucao.getHoraMinutoFimParaQuebra().replace(":", "_")%>" style="display:none">
											
										<table width="100%" align="center" bgcolor="#90c7fc" border="0">
										<tr>
											<td align="left"><a href="javascript:extendeTabela('PeriodoExecucao<%=""+ (Util.formatarData(dadosOsAtividadePeriodoExecucao.getDataExecucaoParaQuebra())).replace("/", "_") + dadosOsAtividadePeriodoExecucao.getHoraMinutoInicioParaQuebra().replace(":", "_") + dadosOsAtividadePeriodoExecucao.getHoraMinutoFimParaQuebra().replace(":", "_")%>',false);"/>
												<strong>Período: <bean:write name="dadosOsAtividadePeriodoExecucao" property="osAtividadePeriodoExecucao.dataInicio" formatKey="hour.format"/> às <bean:write name="dadosOsAtividadePeriodoExecucao" property="osAtividadePeriodoExecucao.dataFim" formatKey="hour.format"/></strong></a></td>
										</tr>
										</table>
										
										<!-- Coleção Equipes -->
										
      									
      									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td>
												<table width="100%" border="0" bgcolor="#99CCFF">
												<tr bgcolor="#99CCFF" height="18">
													<td width="10%" align="center"><strong>Remover</strong></td>
													<td width="90%" align="center"><strong>Equipe</strong></td>
																
												</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												
												<table width="100%" align="center" bgcolor="#99CCFF">
												<!--corpo da segunda tabela-->
									
												<% String cor = "#cbe5fe";%>
									
												<logic:iterate name="colecaoOSExecucaoEquipeHelper" id="dadosOsExecucaoEquipeHelper" type="OSExecucaoEquipeHelper">
															
													<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
														cor = "#FFFFFF";%>
														<tr bgcolor="#FFFFFF" height="18">	
													<%} else{	
														cor = "#cbe5fe";%>
														<tr bgcolor="#cbe5fe" height="18">		
													<%}%>
															
														<td width="10%" align="center">
																			
															<a href="javascript:if(confirm('Confirma remoção?')){remover(<%="" + GcomAction.obterTimestampIdObjeto(dadosOsExecucaoEquipeHelper.getOsExecucaoEquipe().getEquipe())%>);}" alt="Remover"><img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0"></a>
																		
														</td>
																	
														<td width="90%" align="left">
															<a href="javascript:componentes(<%=""+ dadosOsExecucaoEquipeHelper.getOsExecucaoEquipe().getEquipe().getId()%>, '<%=""+ (Util.formatarData(dadosOsAtividadePeriodoExecucao.getDataExecucaoParaQuebra())).replace("/", "_")%>', '<%=""+ dadosOsAtividadePeriodoExecucao.getHoraMinutoInicioParaQuebra()%>', '<%=""+ dadosOsAtividadePeriodoExecucao.getHoraMinutoFimParaQuebra()%>')">
															<bean:write name="dadosOsExecucaoEquipeHelper" property="osExecucaoEquipe.equipe.nome"/></a>
														</td>
																	
													</tr>
															
												</logic:iterate>
									
												</table>
									
											</td>
										</tr>
										</table>
												
										<!-- FIM Coleção Equipes -->
											
									</div>
										
							
						<%}else if (dataExecucaoAnterior != null && !dataExecucaoAnterior.equals(dadosOsAtividadePeriodoExecucao.getDataExecucaoParaQuebra())){ %>
	      					
		      				<!-- Fechamento do DIV em caso de repetição de datas de execução, porém com períodos diferentes -->
		      				</div>
		      				
		      				<br>
		      				
		      				<div id="layerHideDataExecucao<%=""+ (Util.formatarData(dadosOsAtividadePeriodoExecucao.getDataExecucaoParaQuebra())).replace("/", "_")%>" style="display:block">
							
								<table width="100%" align="center" bgcolor="#79bbfd" border="0">
								<tr>
									<td align="center"><a href="javascript:extendeTabela('DataExecucao<%=""+ (Util.formatarData(dadosOsAtividadePeriodoExecucao.getDataExecucaoParaQuebra())).replace("/", "_")%>',true);"/>
										<strong>Data de Execução: <bean:write name="dadosOsAtividadePeriodoExecucao" property="osAtividadePeriodoExecucao.dataInicio" formatKey="date.format"/></strong></a></td>
								</tr>
								</table>
						
							</div>
						
							<div id="layerShowDataExecucao<%=""+ (Util.formatarData(dadosOsAtividadePeriodoExecucao.getDataExecucaoParaQuebra())).replace("/", "_")%>" style="display:none">
							
								<table width="100%" align="center" bgcolor="#79bbfd" border="0">
								<tr>
									<td align="center"><a href="javascript:extendeTabela('DataExecucao<%=""+ (Util.formatarData(dadosOsAtividadePeriodoExecucao.getDataExecucaoParaQuebra())).replace("/", "_")%>',false);"/>
										<strong>Data de Execução: <bean:write name="dadosOsAtividadePeriodoExecucao" property="osAtividadePeriodoExecucao.dataInicio" formatKey="date.format"/></strong></a></td>
								</tr>
								</table>
							
								
								  	<div id="layerHidePeriodoExecucao<%=""+ (Util.formatarData(dadosOsAtividadePeriodoExecucao.getDataExecucaoParaQuebra())).replace("/", "_") + dadosOsAtividadePeriodoExecucao.getHoraMinutoInicioParaQuebra().replace(":", "_") + dadosOsAtividadePeriodoExecucao.getHoraMinutoFimParaQuebra().replace(":","_")%>" style="display:block">
											
										<table width="100%" align="center" bgcolor="#90c7fc" border="0">
										<tr>
											<td align="left"><a href="javascript:extendeTabela('PeriodoExecucao<%=""+ (Util.formatarData(dadosOsAtividadePeriodoExecucao.getDataExecucaoParaQuebra())).replace("/", "_") + dadosOsAtividadePeriodoExecucao.getHoraMinutoInicioParaQuebra().replace(":", "_") + dadosOsAtividadePeriodoExecucao.getHoraMinutoFimParaQuebra().replace(":", "_")%>',true);"/>
												<strong>Período: <bean:write name="dadosOsAtividadePeriodoExecucao" property="osAtividadePeriodoExecucao.dataInicio" formatKey="hour.format"/> às <bean:write name="dadosOsAtividadePeriodoExecucao" property="osAtividadePeriodoExecucao.dataFim" formatKey="hour.format"/></strong></td>
										</tr>
										</table>
											
									</div>
								
									<div id="layerShowPeriodoExecucao<%=""+ (Util.formatarData(dadosOsAtividadePeriodoExecucao.getDataExecucaoParaQuebra())).replace("/", "_") + dadosOsAtividadePeriodoExecucao.getHoraMinutoInicioParaQuebra().replace(":", "_") + dadosOsAtividadePeriodoExecucao.getHoraMinutoFimParaQuebra().replace(":", "_")%>" style="display:none">
											
										<table width="100%" align="center" bgcolor="#90c7fc" border="0">
										<tr>
											<td align="left"><a href="javascript:extendeTabela('PeriodoExecucao<%=""+ (Util.formatarData(dadosOsAtividadePeriodoExecucao.getDataExecucaoParaQuebra())).replace("/", "_") + dadosOsAtividadePeriodoExecucao.getHoraMinutoInicioParaQuebra().replace(":", "_") + dadosOsAtividadePeriodoExecucao.getHoraMinutoFimParaQuebra().replace(":", "_")%>',false);"/>
												<strong>Período: <bean:write name="dadosOsAtividadePeriodoExecucao" property="osAtividadePeriodoExecucao.dataInicio" formatKey="hour.format"/> às <bean:write name="dadosOsAtividadePeriodoExecucao" property="osAtividadePeriodoExecucao.dataFim" formatKey="hour.format"/></strong></a></td>
										</tr>
										</table>
										
										<!-- Coleção Equipes -->
										
      									
      									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td>
												<table width="100%" border="0" bgcolor="#99CCFF">
												<tr bgcolor="#99CCFF" height="18">
													<td width="10%" align="center"><strong>Remover</strong></td>
													<td width="90%" align="center"><strong>Equipe</strong></td>
																
												</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												
												<table width="100%" align="center" bgcolor="#99CCFF">
												<!--corpo da segunda tabela-->
									
												<% String cor = "#cbe5fe";%>
									
												<logic:iterate name="colecaoOSExecucaoEquipeHelper" id="dadosOsExecucaoEquipeHelper" type="OSExecucaoEquipeHelper">
															
													<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
														cor = "#FFFFFF";%>
														<tr bgcolor="#FFFFFF" height="18">	
													<%} else{	
														cor = "#cbe5fe";%>
														<tr bgcolor="#cbe5fe" height="18">		
													<%}%>
															
														<td width="10%" align="center">
																			
															<a href="javascript:if(confirm('Confirma remoção?')){remover(<%="" + GcomAction.obterTimestampIdObjeto(dadosOsExecucaoEquipeHelper.getOsExecucaoEquipe().getEquipe()) %>);}" alt="Remover"><img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0"></a>
																		
														</td>
																	
														<td width="90%" align="left">
															<a href="javascript:componentes(<%=""+ dadosOsExecucaoEquipeHelper.getOsExecucaoEquipe().getEquipe().getId()%>, '<%=""+ (Util.formatarData(dadosOsAtividadePeriodoExecucao.getDataExecucaoParaQuebra())).replace("/", "_")%>', '<%=""+ dadosOsAtividadePeriodoExecucao.getHoraMinutoInicioParaQuebra()%>', '<%=""+ dadosOsAtividadePeriodoExecucao.getHoraMinutoFimParaQuebra()%>')">
															<bean:write name="dadosOsExecucaoEquipeHelper" property="osExecucaoEquipe.equipe.nome"/></a>
														</td>
																	
													</tr>
															
												</logic:iterate>
									
												</table>
									
											</td>
										</tr>
										</table>
												
										<!-- FIM Coleção Equipes -->
											
									</div>
										
							
						<%} else if (dataExecucaoAnterior != null && dataExecucaoAnterior.equals(dadosOsAtividadePeriodoExecucao.getDataExecucaoParaQuebra())){ %>
						
								
									<div id="layerHidePeriodoExecucao<%=""+ (Util.formatarData(dadosOsAtividadePeriodoExecucao.getDataExecucaoParaQuebra())).replace("/", "_") + dadosOsAtividadePeriodoExecucao.getHoraMinutoInicioParaQuebra().replace(":", "_") + dadosOsAtividadePeriodoExecucao.getHoraMinutoFimParaQuebra().replace(":","_")%>" style="display:block">
											
										<table width="100%" align="center" bgcolor="#90c7fc" border="0">
										<tr>
											<td align="left"><a href="javascript:extendeTabela('PeriodoExecucao<%=""+ (Util.formatarData(dadosOsAtividadePeriodoExecucao.getDataExecucaoParaQuebra())).replace("/", "_") + dadosOsAtividadePeriodoExecucao.getHoraMinutoInicioParaQuebra().replace(":", "_") + dadosOsAtividadePeriodoExecucao.getHoraMinutoFimParaQuebra().replace(":", "_")%>',true);"/>
												<strong>Período: <bean:write name="dadosOsAtividadePeriodoExecucao" property="osAtividadePeriodoExecucao.dataInicio" formatKey="hour.format"/> às <bean:write name="dadosOsAtividadePeriodoExecucao" property="osAtividadePeriodoExecucao.dataFim" formatKey="hour.format"/></strong></td>
										</tr>
										</table>
											
									</div>
								
									<div id="layerShowPeriodoExecucao<%=""+ (Util.formatarData(dadosOsAtividadePeriodoExecucao.getDataExecucaoParaQuebra())).replace("/", "_") + dadosOsAtividadePeriodoExecucao.getHoraMinutoInicioParaQuebra().replace(":", "_") + dadosOsAtividadePeriodoExecucao.getHoraMinutoFimParaQuebra().replace(":", "_")%>" style="display:none">
											
										<table width="100%" align="center" bgcolor="#90c7fc" border="0">
										<tr>
											<td align="left"><a href="javascript:extendeTabela('PeriodoExecucao<%=""+ (Util.formatarData(dadosOsAtividadePeriodoExecucao.getDataExecucaoParaQuebra())).replace("/", "_") + dadosOsAtividadePeriodoExecucao.getHoraMinutoInicioParaQuebra().replace(":", "_") + dadosOsAtividadePeriodoExecucao.getHoraMinutoFimParaQuebra().replace(":", "_")%>',false);"/>
												<strong>Período: <bean:write name="dadosOsAtividadePeriodoExecucao" property="osAtividadePeriodoExecucao.dataInicio" formatKey="hour.format"/> às <bean:write name="dadosOsAtividadePeriodoExecucao" property="osAtividadePeriodoExecucao.dataFim" formatKey="hour.format"/></strong></a></td>
										</tr>
										</table>
										
										<!-- Coleção Equipes -->
										
      									
      									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td>
												<table width="100%" border="0" bgcolor="#99CCFF">
												<tr bgcolor="#99CCFF" height="18">
													<td width="10%" align="center"><strong>Remover</strong></td>
													<td width="90%" align="center"><strong>Equipe</strong></td>
																
												</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												
												<table width="100%" align="center" bgcolor="#99CCFF">
												<!--corpo da segunda tabela-->
									
												<% String cor = "#cbe5fe";%>
									
												<logic:iterate name="colecaoOSExecucaoEquipeHelper" id="dadosOsExecucaoEquipeHelper" type="OSExecucaoEquipeHelper">
															
													<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
														cor = "#FFFFFF";%>
														<tr bgcolor="#FFFFFF" height="18">	
													<%} else{	
														cor = "#cbe5fe";%>
														<tr bgcolor="#cbe5fe" height="18">		
													<%}%>
															
														<td width="10%" align="center">
																			
															<a href="javascript:if(confirm('Confirma remoção?')){remover(<%="" + GcomAction.obterTimestampIdObjeto(dadosOsExecucaoEquipeHelper.getOsExecucaoEquipe().getEquipe()) %>);}" alt="Remover"><img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0"></a>
																		
														</td>
																	
														<td width="90%" align="left">
															<a href="javascript:componentes(<%=""+ dadosOsExecucaoEquipeHelper.getOsExecucaoEquipe().getEquipe().getId()%>, '<%=""+ (Util.formatarData(dadosOsAtividadePeriodoExecucao.getDataExecucaoParaQuebra())).replace("/", "_")%>', '<%=""+ dadosOsAtividadePeriodoExecucao.getHoraMinutoInicioParaQuebra()%>', '<%=""+ dadosOsAtividadePeriodoExecucao.getHoraMinutoFimParaQuebra()%>')">
															<bean:write name="dadosOsExecucaoEquipeHelper" property="osExecucaoEquipe.equipe.nome"/></a>
														</td>
																	
													</tr>
															
												</logic:iterate>
									
												</table>
									
											</td>
										</tr>
										</table>
												
										<!-- FIM Coleção Equipes -->
											
									</div>
						<%}%>
						
						<% dataExecucaoAnterior = dadosOsAtividadePeriodoExecucao.getDataExecucaoParaQuebra(); %>
						
						</logic:iterate>
						
						</logic:present>
      				
      				</logic:equal>
      				
      				</logic:equal>
      				
      				</logic:iterate>
					
					</logic:notEmpty>
				
				
				</logic:present>
				
				</div>
							
      		</td>
      	</tr>
        
        <tr>
        
          <logic:present name="caminhoRetornoManterHoras">
          		<td colspan="4" height="20"><input type="button" class="bottonRightCol" value="Voltar"
				style="width: 80px" onclick="window.location.href='${sessionScope.caminhoRetornoManterHoras}'"></td>
          </logic:present>
          
          <logic:notPresent name="caminhoRetornoManterHoras">
          		<td colspan="4" height="20" align="right"><input type="button" class="bottonRightCol" value="Inserir"
				style="width: 80px" onclick="window.location.href='/gsan/exibirManterHorasExecucaoEquipeOSAction.do'"></td>
          </logic:notPresent>
        
        </tr>
      </table>
	</td>
  </tr>
</table>
</body>
</html:form>
</html:html>


