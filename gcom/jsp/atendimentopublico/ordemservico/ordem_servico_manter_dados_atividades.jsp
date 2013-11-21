<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.atendimentopublico.ordemservico.Atividade"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ManterDadosAtividadesOrdemServicoActionForm"/>

<script>

function limpar(){

	var form = document.forms[0];
	
	form.idAtividade.value = "";
	form.descricaoAtividade.value = "";
			   
	//Coloca o foco no objeto selecionado
	form.idAtividade.focus();
}

function limparDescricao(){
	
	var form = document.forms[0];
	form.descricaoAtividade.value = "";
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
	
	if (validateManterDadosAtividadesOrdemServicoActionForm(form)){
		form.action = "/gsan/exibirManterDadosAtividadesOrdemServicoAction.do?adicionarAtividade=OK";
		form.submit();
	}
}


function apropriarHoras(idAtividade, descricaoAtividade){

	var form = document.forms[0];
	
	numeroOS = form.numeroOSForm.value;
	roteiro = form.dataRoteiroForm.value;
	encerramento = form.dataEncerramentoForm.value;
	
	caminhoAction = "/gsan/exibirManterHorasExecucaoEquipeOSAction.do?caminhoRetorno=/gsan/exibirManterDadosAtividadesOrdemServicoAction.do&ordemServico=" + 
	numeroOS + "&idAtividade=" + idAtividade + "&descricaoAtividade=" + descricaoAtividade;
	
	if (roteiro.length > 0){
		caminhoAction = caminhoAction + "&dataRoteiro=" + roteiro;
	}
	
	if (encerramento.length > 0){
		caminhoAction = caminhoAction + "&dataEncerramento=" + encerramento;
	}
	
	redirecionarSubmit(caminhoAction);
}


function apropriarMaterial(idAtividade, descricaoAtividade){

	var form = document.forms[0];
	
	numeroOS = form.numeroOSForm.value;
	
	caminhoAction = "/gsan/exibirManterMaterialExecucaoOSAction.do?caminhoRetorno=/gsan/exibirManterDadosAtividadesOrdemServicoAction.do&ordemServico=" + 
	numeroOS + "&idAtividade=" + idAtividade + "&descricaoAtividade=" + descricaoAtividade;
	
	redirecionarSubmit(caminhoAction);
}
 
</script>
</head>

<logic:present name="fecharPopup" scope="request">
	<logic:present name="caminhoRetornoDadosAtividadesOS">
	 <body leftmargin="0" topmargin="0" onload="redirecionarSubmit('${sessionScope.caminhoRetornoDadosAtividadesOS}')">
	</logic:present>
	<logic:notPresent name="caminhoRetornoDadosAtividadesOS">
		<logic:present name="caminhoRetorno" scope="request">
			<body leftmargin="0" topmargin="0" onload="chamarReload('${requestScope.caminhoRetorno}');window.close();">
		</logic:present>
		
		<logic:notPresent name="caminhoRetorno" scope="request">
			<body leftmargin="0" topmargin="0" onload="window.close();">
		</logic:notPresent>
	</logic:notPresent>
	
</logic:present>

<logic:notPresent name="fecharPopup" scope="request">
	<body leftmargin="0" topmargin="0" onload="window.focus();resizePageSemLink(680, 500);setarFoco('${requestScope.nomeCampo}');">
</logic:notPresent>


<html:form action="/exibirManterDadosAtividadesOrdemServicoAction" method="post">

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
          <td class="parabg">Manter Dados das Atividades da Ordem de Serviço</td>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0">
        <tr>
          <td colspan="4">
          	Apropriar as horas e o material de execução das atividades da Ordem de Serviço:
          </td>
        </tr>
        <tr>
          <td width="20%"><strong>Número da OS: </strong></td>
          <td width="80%" colspan="3">
          	<html:text property="numeroOSForm" size="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
          </td>
        </tr>
        <tr>
          <td width="20%"><strong>Roteiro: </strong></td>
          <td width="80%" colspan="3">
          	<html:text property="dataRoteiroForm" size="11" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
          </td>
        </tr>
        <tr>
          <td width="20%"><strong>Encerramento: </strong></td>
          <td width="80%" colspan="3">
          	<html:text property="dataEncerramentoForm" size="11" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
          </td>
        </tr>
        <tr>
          <td colspan="4" height="10"></td>
        </tr>
        <tr>
      		<td WIDTH="20%"><strong>Atividade:</strong></td>
        	<td width="80%" colspan="3">
			<html:text property="idAtividade" size="7" maxlength="6" tabindex="1" onkeypress="limparDescricao();validaEnterComMensagem(event, 'exibirManterDadosAtividadesOrdemServicoAction.do', 'idAtividade', 'Atividade');"/>
			<a href="javascript:redirecionarSubmit('exibirManterDadosAtividadesOrdemServicoAction.do?pesquisarAtividade=OK');">
			<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" alt="Pesquisar" border="0"></a>

			<logic:present name="corAtividade">

				<logic:equal name="corAtividade" value="exception">
					<html:text property="descricaoAtividade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:equal>

				<logic:notEqual name="corAtividade" value="exception">
					<html:text property="descricaoAtividade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEqual>

			</logic:present>

			<logic:notPresent name="corAtividade">

				<logic:empty name="ManterDadosAtividadesOrdemServicoActionForm" property="idAtividade">
					<html:text property="descricaoAtividade" value="" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:empty>
				<logic:notEmpty name="ManterDadosAtividadesOrdemServicoActionForm" property="idAtividade">
					<html:text property="descricaoAtividade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEmpty>
				

			</logic:notPresent>
        	
        	<a href="javascript:limpar();">
        	<img src="<bean:message key='caminho.imagens'/>limparcampo.gif" alt="Apagar" border="0"></a>
       	</td>
	  	</tr>
       	<tr>
          <td colspan="4" height="10"></td>
        </tr>
        <tr>
      	<td colspan="4">
      	
      		<table width="98%">
			<tr>
				<td><strong>Execução das Atividades:</strong></td>
				<td align="right"><input type="button" class="bottonRightCol"
				value="Adicionar" tabindex="11" style="width: 80px" onclick="adicionar();"></td>
			</tr>
			</table>
      		<table width="98%" border="0">
	  		<tr> 
          		<td colspan="4">
		  
					<table width="100%" cellpadding="0" cellspacing="0">
					<tr> 
                		<td> 
							
							<table width="100%" bgcolor="#90c7fc">
							<tr bgcolor="#90c7fc"> 

								<td align="center" width="10%"><FONT COLOR="#000000"><strong>Remover</strong></FONT></td>
								<td align="center" width="15%"><FONT COLOR="#000000"><strong>Código</strong></FONT></td>
								<td align="center" width="45%"><FONT COLOR="#000000"><strong>Atividade</strong></FONT></td>
								<td align="center" width="15%"><FONT COLOR="#000000"><strong>Horas</strong></FONT></td>
								<td align="center" width="15%"><FONT COLOR="#000000"><strong>Material</strong></FONT></td>
					
							</tr>
                    		</table>
					
						</td>
            		</tr>
            		
            		<tr> 
						<td> 
					
							<div style="width: 100%; height: 100; overflow: auto;">	
							
							<% String cor = "#cbe5fe";%>
							
							<table width="100%" align="center" bgcolor="#90c7fc">

								<logic:iterate name="colecaoAtividade" id="atividade" type="Atividade">
                            
									
									
									<%	if (cor.equalsIgnoreCase("#FFFFFF")){
											cor = "#cbe5fe";%>
											<tr bgcolor="#cbe5fe">
									<%} else{
											cor = "#FFFFFF";%>
											<tr bgcolor="#FFFFFF">
									<%}%> 
									
									<td align="center" width="10%">
										<a href="javascript:remover(<%="" + atividade.getId().intValue()%>)">
											<img src="<bean:message key='caminho.imagens'/>Error.gif" border="0" >
										</a>
									</td>
									
									<td align="center" width="15%"><bean:write name="atividade" property="id"/></td>
									
									<td width="45%"><bean:write name="atividade" property="descricao"/></td>
									
									<td align="center" valign="middle" width="15%">
										<a href="javascript:apropriarHoras('<%="" + atividade.getId().intValue()%>', '<%="" + atividade.getDescricao()%>');">
											<img src="<bean:message key='caminho.imagens'/>relogioTransparente.gif" border="0">
										</a>
									</td>
									
									<td align="center" valign="middle" width="15%">
										<a href="javascript:apropriarMaterial('<%="" + atividade.getId().intValue()%>', '<%="" + atividade.getDescricao()%>');">
											<img src="<bean:message key='caminho.imagens'/>marteloTransparente3.gif" border="0">
										</a>
									</td>
									
									</tr>
									

								</logic:iterate>
								
								</table>

							</div>
						</td>
            		</tr>
            		
					</table>
				</td>
			</tr>
			
			</table>
			
      		</td>
      	</tr>
        
        <tr>
          <td colspan="3" height="20" align="left"><input type="button" class="bottonRightCol" value="Fechar"
			style="width: 80px" onclick="window.close();"></td>
		  <td align="right"><input type="button" class="bottonRightCol" value="Inserir"
			style="width: 80px" onclick="window.location.href='/gsan/exibirManterDadosAtividadesOrdemServicoAction.do?inserir=OK'"></td>
        </tr>
      </table>
	</td>
  </tr>
</table>
</body>
</html:form>
</html:html>

