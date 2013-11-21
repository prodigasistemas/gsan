<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.gui.GcomAction"%>
<%@ page import="gcom.atendimentopublico.registroatendimento.RegistroAtendimentoAnexo"%>

<%@page isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="InserirRegistroAtendimentoActionForm" dynamicJavascript="false" />

<SCRIPT LANGUAGE="JavaScript">

function validateInserirRegistroAtendimentoActionForm(form) {
	form.target = "";
	return true;
}

function adicionarArquivo(){
	
	var form = document.forms[0];
	form.target = "";
	form.action = "exibirInserirRegistroAtendimentoAnexosAction.do?adicionar=OK";
	
	retorno = true;
	
	if(form.arquivoAnexo.value.length == 0){
		alert("Informe o Arquivo");
		form.arquivoAnexo.focus();
		retorno = false;
	}
	else if (!validateCaracterEspecial(form)){
		retorno = false;
	}
	
	if (retorno){
		form.submit();
	}	
}

function caracteresespeciais () { 
     this.aa = new Array("observacaoAnexo", "Observação possui caracteres especiais.", new Function ("varName", " return this[varName];"));
}

function removerArquivo(identificacao){
	
	var form = document.forms[0];
	form.target = "";
	form.action = "exibirInserirRegistroAtendimentoAnexosAction.do?remover=" + identificacao;
	
	form.submit();	
}

function visualizarArquivo(identificacao){
	
	var form = document.forms[0];
	form.target = "_new";
	form.action = "exibirInserirRegistroAtendimentoAnexosAction.do?visualizar=" + identificacao;
	
	form.submit();	
}

function atualizarObservacao(identificacao){

	abrirPopup('exibirAtualizarRegistroAtendimentoObservacaoArquivoAnexoAction.do?telaRetorno=exibirInserirRegistroAtendimentoAnexosAction&idRegistroAtendimentoAnexo=' + identificacao, 350, 620);
	
}

</SCRIPT>



</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}'); limitTextArea(document.forms[0].observacao, 200, document.getElementById('utilizado'), document.getElementById('limite'));">

<div id="formDiv">

<form ACTION="" name="InserirRegistroAtendimentoActionForm" method="post" enctype="multipart/form-data">

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_ra.jsp?numeroPagina=4"/>


<logic:notPresent scope="session" name="origemGIS">
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp" %>
</logic:notPresent>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <logic:notPresent scope="session" name="origemGIS">
  		<td width="140" valign="top" class="leftcoltext">
	</logic:notPresent>
	<logic:present scope="session" name="origemGIS">
		<td width="140" valign="top" class="leftcoltext" style="display:none;">
	</logic:present>
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

	<td width="610" valign="top" class="centercoltext">

        <table height="100%">
        <tr>
          <td></td>
        </tr>
      	</table>

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Inserir Registro de Atendimento</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      <tr>
      	<td colspan="2" HEIGHT="40" align="center">
      		<span style="font-size: 18px;font-weight: bold;">
      		Nº Protocolo: ${sessionScope.protocoloAtendimento}</span></td>
      </tr>
      
      <tr>
      	<td colspan="2">Para anexar um ou vários arquivos, informe os dados abaixo:</td>
      </tr>
      
	  <tr>
      	<td HEIGHT="30"><strong>Arquivo:</strong></td>
        <td>
			<input TYPE="file" NAME="arquivoAnexo" size="54" />
		</td>
      </tr>

	  <tr>
      	<td HEIGHT="30"><strong>Observação:</strong></td>
        <td>
			
			<TEXTAREA NAME="observacaoAnexo" ROWS="3" COLS="50" 
			onkeyup="limitTextArea(document.forms[0].observacaoAnexo, 200, document.getElementById('utilizado'), document.getElementById('limite'));"></TEXTAREA><br>
			<strong><span id="utilizado">0</span>/<span id="limite">200</span></strong>
			
		</td>
      </tr>
      
     <tr>
      	<td colspan="2" height="10"></td>
      </tr>
      <tr>
		<td height="10"></td>
		<td><font color="#FF0000">*</font>&nbsp; Serão aceitos os arquivos nos formatos: JPG, DOC ou PDF</td>
	  </tr>
      
      <%int cont = 0;%>
      

		<tr>
			<td colspan="2" height="5" width="625"></td>
		</tr>
		<tr>
			<td colspan="2">
							
				<table width="100%" border="0">
				<tr>
					<td height="17" colspan="3"><strong>Arquivo(s) informado(s):</strong></td>
					<td align="right"><input type="button" class="bottonRightCol"
					value="Adicionar" tabindex="11" style="width: 80px"
					onclick="javascript:adicionarArquivo();"></td>
				</tr>

				<logic:present name="colecaoRegistroAtendimentoAnexo">
						
					<logic:notEmpty name="colecaoRegistroAtendimentoAnexo">
						
					<tr>
						<td colspan="4">

						<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td>

								<table width="100%" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">

									<td align="center" width="10%"><strong>Remover</strong></td>
									<td width="10%">
										<div align="center"><strong>Arquivo</strong></div>
									</td>
									<td style="width:80%">
										<div align="center"><strong>Observação</strong></div>
									</td>
								</tr>
								</table>

							</td>
						</tr>

						<tr>
							<td>

								<div style="width: 100%; height: 100; overflow: auto;">
								<%cont = 0;%>
									
								<table width="100%" align="center" bgcolor="#99CCFF">

									<logic:iterate name="colecaoRegistroAtendimentoAnexo" id="registroAtendimentoAnexo" type="RegistroAtendimentoAnexo">
										
										<%cont = cont + 1;
										
										if (cont % 2 == 0) {%>
											<tr bgcolor="#cbe5fe">
										<%} else {%>
											<tr bgcolor="#FFFFFF">
										<%}%>

												<td align="center" width="10%" valign="middle">
													<a href="javascript:removerArquivo('<%="" + GcomAction.obterTimestampIdObjeto(registroAtendimentoAnexo) %>')" title="Remover">
														<img src="<bean:message key='caminho.imagens'/>Error.gif" border="0" >
													</a>
												</td>
												<td align="center" width="10%" valign="middle">
														<a href="javascript:visualizarArquivo('<%="" + GcomAction.obterTimestampIdObjeto(registroAtendimentoAnexo) %>')" title="Visualizar Arquivo">
														<IMG SRC="<bean:message key="caminho.imagens"/><%="" + registroAtendimentoAnexo.getNomeExtensaoDocumento() %>.gif" width="35" height="35" BORDER="0" ALT=""></a>	
												</td>
												<td width="80%">
													
													<% if (cont % 2 == 0) {%>
														
														<logic:present name="registroAtendimentoAnexo" property="descricaoDocumento">
															
															<a href="javascript:atualizarObservacao('<%="" + GcomAction.obterTimestampIdObjeto(registroAtendimentoAnexo) %>')">
															<TEXTAREA ROWS="2" COLS="55" style="cursor:hand; border:0px solid; background-color: #cbe5fe" readonly><bean:write name="registroAtendimentoAnexo" property="descricaoDocumento" /></TEXTAREA></a>
															
														</logic:present>
																		
														<logic:notPresent name="registroAtendimentoAnexo" property="descricaoDocumento">
															
															<a href="javascript:atualizarObservacao('<%="" + GcomAction.obterTimestampIdObjeto(registroAtendimentoAnexo) %>')">
															<TEXTAREA ROWS="2" COLS="55" style="border:0px solid; background-color: #cbe5fe" readonly></TEXTAREA></a>
															
														</logic:notPresent>
														
													<%} else {%>
														
														<logic:present name="registroAtendimentoAnexo" property="descricaoDocumento">
															
															<a href="javascript:atualizarObservacao('<%="" + GcomAction.obterTimestampIdObjeto(registroAtendimentoAnexo) %>')">
															<TEXTAREA ROWS="2" COLS="55" style="cursor:hand; border:0px solid" readonly><bean:write name="registroAtendimentoAnexo" property="descricaoDocumento" /></TEXTAREA></a>
															
														</logic:present>
																		
														<logic:notPresent name="registroAtendimentoAnexo" property="descricaoDocumento">
															
															<a href="javascript:atualizarObservacao('<%="" + GcomAction.obterTimestampIdObjeto(registroAtendimentoAnexo) %>')">
															<TEXTAREA ROWS="2" COLS="55" style="border:0px solid" readonly></TEXTAREA></a>
															
														</logic:notPresent>
														
													<%}%>
													
															
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
						
					</logic:notEmpty>
						
				</logic:present>
						
				</table>
												
			</td>
		</tr>
      
	  <tr>
      	<td colspan="2" height="10"></td>
      </tr>
		
      <tr>
        <td colspan="2">
			<div align="right">
				<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_ra.jsp?numeroPagina=4"/>
			</div>
		</td>
      </tr>
      </table>
      <p>&nbsp;</p>
	</td>
  </tr>
</table>


<logic:notPresent scope="session" name="origemGIS">
	<%@ include file="/jsp/util/rodape.jsp"%>
</logic:notPresent>
</form>
</div>

<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/inserirRegistroAtendimentoWizardAction.do?concluir=true&action=inserirRegistroAtendimentoAnexosAction'); }
</script>

<logic:present scope="session" name="origemGIS">
	<script>
		document.getElementById("Layer1").style.top = "5";
	</script>
</logic:present>

</body>
</html:html>

