<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@page
	import="gcom.atendimentopublico.ordemservico.ConsultarComandosOSSeletivaInspecaoAnormalidadeHelper"%>
<%@page import="gcom.util.Util" isELIgnored="false"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="ConsultarComandosOSSeletivaInspecaoAnormalidadeActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<SCRIPT LANGUAGE="JavaScript">

	
	function limparEmpresa() {
		var form = document.forms[0];
		
		form.idEmpresa.value = "";
		form.nomeEmpresa.value = "";	
		form.periodoExecucaoInicial.value = '';
		form.periodoExecucaoFinal.value = '';
		
		form.action = 'exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction.do?limpar=sim';
		
		submeterFormPadrao(form);
	}
	
	function limparEmpresaTecla() {
		var form = document.forms[0];
		form.nomeEmpresa.value = "";	
	}
	
	function pesquisarEmpresa() {
		var form = document.forms[0];

		abrirPopup('exibirPesquisarEmpresaAction.do?limpaForm=S', 495, 300);
	}

	function limparPeriodoFinal() {
		var form = document.forms[0];
		
		form.periodoExecucaoFinal.value = '';
		
	}
	
	function limparComandos() {
		var form = document.forms[0];
		
		form.action = 'exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction.do?limpar=sim';
		
		submeterFormPadrao(form);
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.forms[0];
   
	    if (tipoConsulta == 'empresa') {
			form.idEmpresa.value = codigoRegistro;
			form.nomeEmpresa.value = descricaoRegistro;
			form.nomeEmpresa.style.color = "#000000";
			form.action = 'exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction.do?limpar=sim';
    		submeterFormPadrao(form);
    	}
    
    }
	
	function validaForm() {
		var form =  document.forms[0];
		if(CheckboxNaoVazio(document.forms[0].idRegistros)){
			if (validateGerarArquivoTextoContasCobrancaEmpresaActionForm(form)){
					submeterFormPadrao(form);
				}
			
		}
	
	}
	
	function selecionarComandos(){
		var form =  document.forms[0];
		
		form.action = 'exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction.do?selecionarComandos=sim';
	    form.submit();
		
	}
	
	function CheckboxNaoVazio(campo){
	  form = document.forms[0];
	  retorno = false;
		
	  for(indice = 0; indice < form.elements.length; indice++){
		if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
			retorno = true;
			break;
		}
	  }
		
	  if (!retorno){
		alert('Informe o(s) comando(s) desejado(s).');
	  }
		
	  return retorno;
	} 
	
	function facilitador(objeto){
		if (objeto.id == "0" || objeto.id == undefined){
			objeto.id = "1";
			marcarTodos();
		}
		else{
			objeto.id = "0";
			desmarcarTodos();
		}
	}
	
	function movimentarOS() {
		form = document.forms[0];
	  	
	  	if (selecionouComando()) {
	  		form.action = 'exibirMovimentarOSSeletivaInspecaoAnormalidadeAction.do?limparTotalizacao=SIM&comando=' + comandoSelecionado();
	    	form.submit();
	  	}
	}
	
	function encerrarComando() {
	  	form = document.forms[0];
	  	
	  	if (selecionouComando()) {
	  	
		  	if(confirm("Confirma o encerramento do comando?")){
		  		form.action = 'encerrarComandoOSSeletivaInspecaoAnormalidadeAction.do?acao=encerrarComando';
		    	form.submit();
	    	}
	  	}
	}
	
	
	function gerarTxtComando() {
	  	form = document.forms[0];
	  	
	  	if (selecionouComando()) {
	  		form.action = 'exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction.do?comando=' + comandoSelecionado()+ '&acao=gerarTxtComando';
	    	form.submit();
	  	}
	}
	
	function selecionouComando(){

		var form = document.forms[0];
	    var selecionados = form.elements['idRegistro'];
		var retorno = false;
		
		if (selecionados.value != null && selecionados.value != '') {
			
			retorno = true;
			
		} else {
		
			for (i=0; i< selecionados.length; i++) {
				if(selecionados[i].checked){
					retorno = true;
				}
			}
			
		}

		if(retorno == false){
			alert('Selecione um Comando.');
		}
		
		return retorno;
	}
	
	function comandoSelecionado(){

		var form = document.forms[0];
	    var selecionados = form.elements['idRegistro'];
		var retorno = false;
		
		if (selecionados.value != null && selecionados.value != '') {
			
			return selecionados.value;
			
		} else {
			for (i=0; i< selecionados.length; i++) {
				if(selecionados[i].checked){
					return selecionados[i].value;
				}
			}
		}
	}
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/encerrarComandoOSSeletivaInspecaoAnormalidadeAction"
	name="ConsultarComandosOSSeletivaInspecaoAnormalidadeActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.ConsultarComandosOSSeletivaInspecaoAnormalidadeActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>


	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext">
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
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Consultar Comandos de OS Seletiva de Inspeção de Anormalidade</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para selecionar os comandos, informar os dados abaixo:</td>
				</tr>
				<tr>
					<td width="25%"><strong>Empresa:<font color="#FF0000">*</font></strong></td>
					<td><html:text maxlength="9" property="idEmpresa" size="9"
						tabindex="14" 
						onkeypress="validaEnterComMensagem(event, 'exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction.do', 'idEmpresa', 'Empresa');limparEmpresaTecla();"
						onchange="limparComandos();" />
					<a href="javascript:pesquisarEmpresa();"><img
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
						height="21" alt="Pesquisar" border="0"></a> <logic:present
						name="empresaInexistente" scope="request">
						<html:text property="nomeEmpresa" size="40" maxlength="40"
							readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent name="empresaInexistente"
						scope="request">
						<html:text property="nomeEmpresa" size="40" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparEmpresa();"> <img
						border="0" src="imagens/limparcampo.gif" height="21" width="23"> </a>
					</td>
				</tr>
				<tr>
					<td><strong>Período de Execução do Comando:</strong></td>
					<td><strong> <html:text maxlength="10"
						property="periodoExecucaoInicial" size="10" tabindex="10"
						onkeyup="mascaraData(this, event);replicarCampo(document.forms[0].periodoExecucaoFinal, this);"
						onchange="limparComandos();" />
					<a
						href="javascript:abrirCalendarioReplicando('ConsultarComandosOSSeletivaInspecaoAnormalidadeActionForm', 'periodoExecucaoInicial', 'periodoExecucaoFinal');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					</strong> <html:text maxlength="10" property="periodoExecucaoFinal"
						tabindex="11" size="10" onkeyup="mascaraData(this, event);"
						onchange="limparComandos();" /> <a
						href="javascript:abrirCalendario('ConsultarComandosOSSeletivaInspecaoAnormalidadeActionForm', 'periodoExecucaoFinal')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					(dd/mm/aaaa)</td>
				</tr>


				<tr>
					<td colspan="2" align="right"><input type="button"
						name="selecionar" class="bottonRightCol"
						value="Selecionar Comandos"
						onClick="javascript:selecionarComandos();" /></td>
				</tr>

				<tr>
					<td colspan="2" align="left"><strong>Comandos de OS Seletiva:</strong></td>
				</tr>


				<tr>
					<td colspan="2">
									<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td colspan="4" bgcolor="#000000" height="2"></td>
					</tr>
					<tr>
						<td>
						<table width="100%" bgcolor="#99CCFF" border="0">
							<tr bordercolor="#000000">
								<td width="8%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Marca</strong></td>
								<td width="50%" bordercolor="#000000" bgcolor="#90c7fc" align="center" rowspan="2">
								<div align="center"><strong>Comando</strong></div></td>
								<td width="12%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Data de Execução</strong></td>
								<td width="15%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Situação</strong></td>
								<td width="15%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Data de Encerramento</strong></td>
								
				
						</table>
						</td>
					</tr>
					<tr>
						<td>
						<table width="100%" bgcolor="#99CCFF">
							<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
								export="currentPageNumber=pageNumber;pageOffset"
								maxPageItems="10" items="${sessionScope.totalRegistros}">
								<pg:param name="pg" />
								<pg:param name="q" />
								<logic:present
									name="colecaoConsultarComandosOSHelper">
									<%int cont = 0;%>
									<logic:iterate
										name="colecaoConsultarComandosOSHelper"
										id="helper"
										type="ConsultarComandosOSSeletivaInspecaoAnormalidadeHelper"
										scope="session">
										<pg:item>
											<%cont = cont + 1;
									if (cont % 2 == 0) {%>
											<tr bgcolor="#cbe5fe">
												<%} else {%>
											</tr>
											<tr bgcolor="#FFFFFF">
												<%}%>
												<td width="8%">
												<div align="center"><html:radio property="idRegistro"
													value="${helper.idComando}" />
												</div>
												</td>

												<td align="left" width="50%"><a
													href="javascript:abrirPopup('exibirConsultarComandosOSSeletivaInspecaoAnormalidadePopupAction.do?pesquisa=nao&idComando=<%= helper.getIdComando()%>', 600, 700);">
												<%=helper.getDescComando()%> </a></td>

												<td align="center" width="12%">
													<%=Util.formatarData(helper.getDataExecucao())%>
												</td>
												
												<td align="center" width="15%">
													<%=helper.getSituacao()%>
												</td>
												
												<td align="center" width="15%">
													<logic:notEmpty name="helper" property="dataEncerramento">
														<%=Util.formatarData(helper.getDataEncerramento())%>
													</logic:notEmpty> 
													<logic:empty name="helper" property="dataEncerramento">
														<font color="red"><%=Util.formatarData(helper.getDataEncerramentoPrevista())%></font>
													</logic:empty> 
												</td>

											</tr>
										</pg:item>
									</logic:iterate>
								</logic:present>
						</table>
						</td>
					</tr>
				</table>
					</td>
				</tr>



				
				
				<table width="100%" border="0">
					<tr>
						<td colspan="2">
						<div align="center"><strong><%@ include
							file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
						</td>
					</tr>
					</pg:pager>

					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>

					<tr>
						<td><strong> <font color="#FF0000"></font></strong></td>
						<td align="right">
						<div align="left"><strong><font color="#FF0000">*</font></strong>
						Campos obrigat&oacute;rios</div>
						</td>
					</tr>

					<tr>
						<td>
							<input name="Button" type="button" class="bottonRightCol"
								value="Desfazer" align="left"
								onclick="window.location.href='<html:rewrite page="/exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction.do?menu=sim"/>'">
							<input name="Button" type="button" class="bottonRightCol"
								value="Cancelar" align="left"
								onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</td>
						<td align="right">
							<input type="button" name="encerrar"
								class="bottonRightCol" value="Gerar Txt Comando"
								onClick="javascript:gerarTxtComando();" />
							<input type="button" name="encerrar"
								class="bottonRightCol" value="Encerrar Comando"
								onClick="javascript:encerrarComando();" />
			
							<input type="button" name="movimentar"
								class="bottonRightCol" value="Movimentar OS"
								onClick="javascript:movimentarOS();" />
						</td>
					</tr>

				</table>

			</table>

			<%@ include file="/jsp/util/rodape.jsp"%> </html:form>
</body>
</html:html>
