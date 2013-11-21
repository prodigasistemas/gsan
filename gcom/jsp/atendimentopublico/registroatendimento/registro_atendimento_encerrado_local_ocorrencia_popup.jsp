<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.atendimentopublico.registroatendimento.RegistroAtendimento"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>

<script>

 // Verifica se há item selecionado
function verficarSelecao(objeto){

	if (RadioNaoVazioMensagem("Registro de Atendimento", objeto)){
		var idRegistro = obterValorRadioMarcado();
		opener.window.location.href = "/gsan/exibirReativarRegistroAtendimentoAction.do?numeroRA=" + idRegistro;
		window.close();
	}
 }
 
</script>

</head>

<body leftmargin="0" topmargin="0" onload="window.focus();resizePageSemLink(685, 480);">

<html:form action="/pesquisarRegistrosAtendimentoEncerradosLocalOcorrenciaAction" method="post">

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
          <td class="parabg">Registros de Atendimento Encerrados do Local da Ocorrência</td>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0">
        <tr>
          <td colspan="4">
          	<table width="100%" border="0" bgcolor="#79bbfd">
			<tr height="18">
				<td width="10%" align="center"><strong>Dados Comuns</strong></td>
			</tr>
			</table>
          </td>
        </tr>
        <tr>
          <td width="110"><strong>Tipo de Solicitação:</strong></td>
          <td colspan="3">
          	<html:text property="idSolicitacaoTipo" size="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>&nbsp;
          	<html:text property="descricaoSolicitacaoTipo" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
          </td>
        </tr>
        <tr>
          <td><strong>Especificação: </strong></td>
          <td colspan="3">
          	<html:text property="idSolicitacaoTipoEspecificacao" size="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>&nbsp;
          	<html:text property="descricaoSolicitacaoTipoEspecificacao" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
          </td>
        </tr>
        <tr>
          <td width="110"><strong>Imóvel:</strong></td>
          <td colspan="3">
          	<html:text property="idImovel" size="10" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>&nbsp;
          	<html:text property="inscricaoImovel" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
          </td>
        </tr>
        
        <tr>
         <td colspan="4" height="50" valign="top">
			<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<table width="100%" border="0" bgcolor="#90c7fc">
					<tr height="18">
						<td align="center"><strong>Endereço da Ocorrência</strong></td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" align="center" bgcolor="#90c7fc">
					<tr bgcolor="#FFFFFF" height="18">
						<td width="10%" align="center">
							<logic:present name="enderecoRAEncerrado">
								<bean:write name="enderecoRAEncerrado"/>
							</logic:present>
						</td>
					</tr>
					</table>
			  	</td>
			</tr>
			</table>
	   	 </td>
        </tr>
        
        <tr>
          <td colspan="4" height="20"></td>
        </tr>
        <tr>
          <td colspan="4">
          	<table width="100%" border="0" bgcolor="#79bbfd">
			<tr>
				<td width="10%" align="center"><strong>Dados dos Registros de Atendimento</strong></td>
			</tr>
			</table>
          </td>
        </tr>
        <tr>
      	<td colspan="4">
      	
      		<table width="100%" border="0">
	  		<tr> 
          		<td colspan="5">
		  
					<table width="100%" cellpadding="0" cellspacing="0">
					<tr> 
                		<td> 
					
							<table width="100%" bgcolor="#90c7fc">
                    		<tr bgcolor="#90c7fc"> 

								<td align="center" width="5%"></td>
								<td align="center" width="10%"><FONT COLOR="#000000"><strong>R.A.</strong></FONT></td>
								<td align="center" width="25%"><FONT COLOR="#000000"><strong>Compl. Endereço</strong></FONT></td>
								<td align="center" width="20%"><FONT COLOR="#000000"><strong>Ponto Referência</strong></FONT></td>
								<td align="center" width="10%"><FONT COLOR="#000000"><strong>Atend.</strong></FONT></td>
								<td align="center" width="10%"><FONT COLOR="#000000"><strong>Encerra.</strong></FONT></td>
								<td align="center" width="20%"><FONT COLOR="#000000"><strong>Motivo</strong></FONT></td>
					
							</tr>
                    		</table>
					
						</td>
            		</tr>
            		
            		<tr> 
						<td> 
					
							<div style="width: 100%; height: 100; overflow: auto;">	
							
							<% String cor = "#cbe5fe";%>
							
							<table width="100%" align="center" bgcolor="#90c7fc">

								<logic:iterate name="colecaoRegistroAtendimento" id="ra" type="RegistroAtendimento">
                            
									
									
									<%	if (cor.equalsIgnoreCase("#FFFFFF")){
											cor = "#cbe5fe";%>
											<tr bgcolor="#cbe5fe">
									<%} else{
											cor = "#FFFFFF";%>
											<tr bgcolor="#FFFFFF">
									<%}%> 
									
									
									<td align="center" width="5%">
										<html:radio property="raSelected" value="<%="" + ra.getId() %>"/>
									</td>
									
									<td align="center" width="10%"><a href="/gsan/exibirConsultarRegistroAtendimentoPopupAction.do?numeroRA=<%="" + ra.getId() %>"><bean:write name="ra" property="id"/></a></td>
									
									<logic:present name="ra" property="complementoEndereco">
										<td align="center" width="25%"><bean:write name="ra" property="complementoEndereco"/></td>
									</logic:present>
									<logic:notPresent name="ra" property="complementoEndereco">
										<td align="center" width="25%">&nbsp;</td>
									</logic:notPresent>
									
									<logic:present name="ra" property="pontoReferencia">
										<td width="20%"><bean:write name="ra" property="pontoReferencia"/></td>
									</logic:present>
									<logic:notPresent name="ra" property="imovel">
										<td width="20%"><bean:write name="ra" property="pontoReferencia"/></td>
									</logic:notPresent>

									<logic:present name="ra" property="registroAtendimento">
										<td align="center" width="10%"><bean:write name="ra" property="registroAtendimento" formatKey="date.format"/></td>
									</logic:present>
									<logic:notPresent name="ra" property="registroAtendimento">
										<td align="center" width="10%">&nbsp;</td>
									</logic:notPresent>

									<logic:present name="ra" property="dataEncerramento">
										<td align="center" width="10%"><bean:write name="ra" property="dataEncerramento" formatKey="date.format"/></td>
									</logic:present>
									<logic:notPresent name="ra" property="dataEncerramento">
										<td align="center" width="10%">&nbsp;</td>
									</logic:notPresent>
									
									<logic:present name="ra" property="atendimentoMotivoEncerramento">
										<td align="center" width="20%"><bean:write name="ra" property="atendimentoMotivoEncerramento.descricao"/></td>
									</logic:present>
									<logic:notPresent name="ra" property="atendimentoMotivoEncerramento">
										<td align="center" width="20%">&nbsp;</td>
									</logic:notPresent>
										
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
          <td colspan="4" height="20"></td>
        </tr>
        </table>
        <table width="100%" border="0">
        <tr>
        	<td>
          		<input type="button" class="bottonRightCol" value="Fechar" onClick="window.close();" style="width: 70px;">
          	</td>
          	<td colspan="4" align="right">
          		<input type="button" class="bottonRightCol" value="Reativar RA" onClick="verficarSelecao(raSelected);">
          	</td>
        </tr>
        </table>
	<p>&nbsp;</p>
	</td>
  </tr>
   
</table>
</body>
</html:form>
</html:html>
