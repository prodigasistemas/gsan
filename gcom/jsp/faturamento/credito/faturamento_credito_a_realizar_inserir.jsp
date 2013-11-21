<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.Pagina, gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<%@ include file="/jsp/util/telaespera.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="InserirCreditoARealizarActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
<!--
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];

    if(tipoConsulta == 'imovel'){
      limparForm();      
      form.matriculaImovel.value = codigoRegistro;
      form.action = 'exibirInserirCreditoARealizarAction.do'
      form.submit();
    }
    
    if (tipoConsulta == 'ordemServico') {
      form.ordemServico.value = codigoRegistro;
      form.nomeOrdemServico.value = descricaoRegistro;
      form.nomeOrdemServico.style.color = "#000000";
      form.action = 'exibirInserirCreditoARealizarAction.do?objetoConsulta=3'
      form.submit();
    }
    
    if (tipoConsulta == 'registroAtendimento') {
      form.registroAtendimento.value = codigoRegistro;
      form.nomeRegistroAtendimento.value = descricaoRegistro;
      form.nomeRegistroAtendimento.style.color = "#000000";
      form.action = 'exibirInserirCreditoARealizarAction.do?objetoConsulta=2'
      form.submit();
    }    
    
}
 
function limparForm(tipo){
	var form = document.forms[0];
	if(tipo == 'imovel')
	{
	    var ObjMatriculaImovel = returnObject(form,"matriculaImovel");
	 	var ObjInscricaoImovel = returnObject(form,"inscricaoImovel");
	 	var ObjNomeCliente = returnObject(form,"nomeCliente");
	 	var ObjSituacaoAgua = returnObject(form,"situacaoAgua");
	 	var ObjSituacaoEsgoto = returnObject(form,"situacaoEsgoto");
	 	
		ObjMatriculaImovel.value = "";
		ObjInscricaoImovel.value = "";
		ObjNomeCliente.value = "";
		ObjSituacaoAgua.value = "";
		ObjSituacaoEsgoto.value = "";
	}
	if(tipo == 'registroAtendimento'){
	 	var ObjRegistroAtendimento = returnObject(form,"registroAtendimento");
		ObjRegistroAtendimento.value = "";
	}
	if(tipo == 'ordemServico'){
	 	var ObjOrdemServico = returnObject(form,"ordemServico");
		ObjOrdemServico.value = "";
	}
}
 
function validarForm(form){
	if(validateInserirCreditoARealizarActionForm(form)){
		/*if(form.registroAtendimento.value.length < 1){
			alert("Informe o Registro de Atencimento");
			codigoImovel.focus();
		}else */
		if(form.numeroPrestacoes.value.length < 1){
			alert("Informe Número de Prestações.");
			numeroPrestacoes.focus();
		}else if(form.valorCredito.value.length < 1){
			alert("Informe Valor do Crédito.");
			numeroPrestacoes.focus();	
		}
		else if(form.tipoCredito.value.length < 1){
			alert("Informe Tipo do Crédito.");
			numeroPrestacoes.focus();	
		}
		else if(form.origemCredito.value.length < 1){
			alert("Informe Origem do Crédito.");
			numeroPrestacoes.focus();	
	    }else if(form.matriculaImovel.value.length < 1){
			if(form.ordemServico.value == "" && form.registroAtendimento.value != ''){
				alert("Dados do Registro de Atendimento não pesquisados. Deseja pesquisar agora?")
				validaEnterSemEvento('exibirInserirCreditoARealizarAction.do?objetoConsulta=2', 'registroAtendimento');
			}
			if(form.ordemServico.value != "" && form.registroAtendimento.value == ''){
				alert("Dados da Ordem de Servico não pesquisados. Deseja pesquisar agora?")
				validaEnterSemEvento('exibirInserirCreditoARealizarAction.do?objetoConsulta=3', 'ordemServico');
			}
		}else{		
			botaoAvancarTelaEspera('/gsan/inserirCreditoARealizarAction.do');
		}
	}
}

function limparRegistroAtendimento(){
	var form = document.forms[0];
///	alert("form.registroAtendimento.disabled="+form.registroAtendimento.disabled);
	if(form.registroAtendimento.disabled == false){
		form.registroAtendimento.value = "";
		form.nomeRegistroAtendimento.value = "";
		form.ordemServico.disabled = false;
		form.ordemServico.value = "";
		form.nomeOrdemServico.value = "";
		limparImovel();
	}
}

function pesquisarRegistroAtendimento(){
	var form = document.forms[0];
	if(form.registroAtendimento.disabled == false){
		abrirPopup('exibirPesquisarRegistroAtendimentoAction.do', 400, 800);
	}
}


function limparOrdemServico(){
	var form = document.forms[0];
	
	if(form.ordemServico.disabled == false){
		form.ordemServico.value = "";
		form.nomeOrdemServico.value = "";
		form.registroAtendimento.disabled = false;
		form.registroAtendimento.value = "";
		form.nomeRegistroAtendimento.value = "";
		form.tipoCredito.disabled = false;
		form.tipoCredito.value = "-1";
		limparImovel();
	}
}
function pesquisarOrdemServico(){
	var form = document.forms[0];
	if(form.ordemServico.disabled == false){
		abrirPopup('exibirPesquisarOrdemServicoAction.do', 400, 800);
	}
}

function bloquearOrdemServico(){
 	var form = document.forms[0];
	if(form.registroAtendimento.value != ""){
		form.ordemServico.disabled = true;
	} else{
		form.ordemServico.disabled = false;
    	limparOrdemServico();
	}
}

function bloquearRegistroAtendimento(){
	 	var form = document.forms[0];
	if(form.ordemServico.value != ""){
		form.registroAtendimento.disabled = true;
	}else{
		form.registroAtendimento.disabled = false;		
		form.tipoCredito.disabled = false;		
		limparRegistroAtendimento();
	}
	 
}

 function limparImovel(){
 	var form = document.forms[0];
	form.matriculaImovel.value = '';
	form.inscricaoImovel.value = '';
	form.nomeCliente.value = '';
	form.situacaoAgua.value = '';
	form.situacaoEsgoto.value = '';
 }

//-->
</script>
</head>
<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('registroAtendimento')">
<div id="formDiv"><html:form action="/exibirInserirCreditoARealizarAction" method="post">
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

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Inserir Crédito a Realizar</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<!-- Início do Corpo - Roberta Costa  11/01/2006  -->

			<table width="100%" border="0">
				<tr>
					<td>Para inserir crédito a realizar, informe os dados abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoCreditoRealizarInserir', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td width="180"><strong>Registro de Atendimento:<font
						color="#FF0000"></font></strong></td>
					<td colspan="3" align="right">
					<div align="left"><logic:present name="travarRegistroAtendimento">
						<html:text property="registroAtendimento" maxlength="9" size="9"
							tabindex="1" onfocus="javascript:bloquearOrdemServico();"
							onkeyup="javascript:bloquearOrdemServico();"
							onkeypress="validaEnterComMensagem(event, 'exibirInserirCreditoARealizarAction.do?objetoConsulta=2', 
							'registroAtendimento','Registro de Atendimento');return isCampoNumerico(event);" />
					</logic:present> <logic:notPresent name="travarRegistroAtendimento">
						<html:text property="registroAtendimento" disabled="true"
							maxlength="9" size="9" tabindex="1"
							onfocus="javascript:bloquearOrdemServico();"
							onkeyup="javascript:bloquearOrdemServico();"
							onkeypress="validaEnterComMensagem(event, 'exibirInserirCreditoARealizarAction.do?objetoConsulta=2', 
							'registroAtendimento','Registro de Atendimento');" />
					</logic:notPresent> <a
						href="javascript:pesquisarRegistroAtendimento();"> <img width="23"
						height="21"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Registro de Atendimento"/></a>
					<logic:present name="corRegistroAtendimento">
						<logic:equal name="corRegistroAtendimento" value="exception">
							<html:text property="nomeRegistroAtendimento" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								size="40" maxlength="40" />
						</logic:equal>
						<logic:notEqual name="corRegistroAtendimento" value="exception">
							<html:text property="nomeRegistroAtendimento" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="corRegistroAtendimento">
						<logic:empty name="InserirCreditoARealizarActionForm"
							property="registroAtendimento">
							<html:text property="nomeRegistroAtendimento" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="InserirCreditoARealizarActionForm"
							property="registroAtendimento">
							<html:text property="nomeRegistroAtendimento" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a
						href="javascript:limparRegistroAtendimento();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Registro de Atendimento" /> </a></div>
					</td>
				</tr>
				<tr>
					<td width="180"><strong>Ordem de Servi&ccedil;o:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><logic:present name="travarOrdemServico">
						<html:text property="ordemServico" maxlength="9" size="9"
							tabindex="1" onfocus="javascript:bloquearRegistroAtendimento();"
							onkeyup="javascript:bloquearRegistroAtendimento();"
							onkeypress="validaEnterComMensagem(event, 'exibirInserirCreditoARealizarAction.do?objetoConsulta=3', 
							'ordemServico','Ordem de Serviço');return isCampoNumerico(event);" />
					</logic:present> <logic:notPresent name="travarOrdemServico">
						<html:text property="ordemServico" maxlength="9" disabled="true"
							size="9" tabindex="1"
							onfocus="javascript:bloquearRegistroAtendimento();"
							onkeyup="javascript:bloquearRegistroAtendimento();"
							onkeypress="validaEnterComMensagem(event, 'exibirInserirCreditoARealizarAction.do?objetoConsulta=3', 
							'ordemServico','Ordem de Serviço');return isCampoNumerico(event);" />
					</logic:notPresent> <a href="javascript:pesquisarOrdemServico();">
					<img width="23" height="21"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Ordem de Serviço"/></a>
					<logic:present name="corNomeOrdemServico">
						<logic:equal name="corNomeOrdemServico" value="exception">
							<html:text property="nomeOrdemServico" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								size="40" maxlength="40" />
						</logic:equal>
						<logic:notEqual name="corNomeOrdemServico" value="exception">
							<html:text property="nomeOrdemServico" size="40" maxlength="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="corNomeOrdemServico">
						<logic:empty name="InserirCreditoARealizarActionForm"
							property="ordemServico">
							<html:text property="nomeOrdemServico" size="40" maxlength="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="InserirCreditoARealizarActionForm"
							property="ordemServico">
							<html:text property="nomeOrdemServico" size="40" maxlength="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a href="javascript:limparOrdemServico();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Ordem de Serviço" /> </a></div>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
							<td><strong>Dados do Imóvel:</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%">
							<table width="100%" border="0">
								<tr>
									<td width="30.7%"><strong>Matrícula:</strong></td>
									<td><html:text property="matriculaImovel" size="45"
										readonly="true" style="background-color:#EFEFEF; border:0" />
									</td>
								</tr>
								<tr>
									<td><strong>Inscrição:</strong></td>
									<td><html:text property="inscricaoImovel" size="45"
										readonly="true" style="background-color:#EFEFEF; border:0" />
									</td>
								</tr>

								<tr>
									<td><strong>Nome do Cliente Usuário:</strong></td>
									<td><html:text property="nomeCliente" size="45" readonly="true"
										style="background-color:#EFEFEF; border:0" /></td>
								</tr>
								<tr>
									<td><strong>Situação de Água:</strong></td>
									<td><html:text property="situacaoAgua" size="45"
										readonly="true" style="background-color:#EFEFEF; border:0" />
									</td>
								</tr>
								<tr>
									<td><strong>Situação de Esgoto:</strong></td>
									<td><html:text property="situacaoEsgoto" size="45"
										readonly="true" style="background-color:#EFEFEF; border:0" />
									</td>
								</tr>
							</table>
							</td>
						</tr>

					</table>
					</td>
				</tr>
				<tr>
					<td><strong>Tipo de Cr&eacute;dito:<font color="#FF0000">*</font></strong></td>
					<td colspan="3" align="right">
					<div align="left"><strong> <logic:present name="travarTipoCredito">
						<html:select property="tipoCredito">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="collectionCreditoTipo"
								labelProperty="descricao" property="id" />
						</html:select>
					</logic:present> <logic:notPresent name="travarTipoCredito">
						<html:select property="tipoCredito" disabled="true">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="collectionCreditoTipo"
								labelProperty="descricao" property="id" />
						</html:select>
					</logic:notPresent> </strong></div>
					</td>
				</tr>
				<tr>
					<td><strong>Origem do Cr&eacute;dito:<font color="#FF0000">*</font></strong></td>
					<td colspan="3" align="right">
					<div align="left"><strong> <html:select property="origemCredito">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="collectionCreditoOrigem"
							labelProperty="descricaoCreditoOrigem" property="id" />
					</html:select></strong></div>
					</td>
				</tr>
				<tr>
					<td><strong>N&uacute;mero de Presta&ccedil;&otilde;es:<strong><font
						color="#FF0000">*</font></strong></strong></td>
					<td colspan="3" align="right">
					<div align="left">
						<html:text property="numeroPrestacoes"
								maxlength="2" 
								size="3" 
								onkeypress="return isCampoNumerico(event);"/></div>
					<div align="left"><strong></strong></div>
					<div align="left"></div>
					</td>
				</tr>
				<tr>
					<td><strong>Valor do Cr&eacute;dito: <font color="#FF0000">*</font></strong></td>
					<td colspan="3" align="right">
					<div align="left">
						<html:text property="valorCredito" 
								maxlength="13"
								size="14" 
								onkeyup="formataValorMonetario(this, 13)"
								onkeypress="return isCampoNumerico(event);"
								style="text-align: right;" />
					</div>
					<div align="left"><strong></strong></div>
					<div align="left"></div>
					</td>
				</tr>
				<tr>
					<td><strong>Referência do Crédito:<font color="#FF0000">*</font></strong></td>
					<td colspan="3">
						<html:text property="referencia" 
							size="8" 
							maxlength="7"
							onkeyup="mascaraAnoMes(this, event);" 
							onkeypress="return isCampoNumerico(event);"/>
					</td>
				</tr>
				<tr>
					<td colspan="3" height="10"></td>
				</tr>
				<tr>
					<td height="10">&nbsp;</td>
					<td><strong><font color="#FF0000">*</font></strong> Campos
					obrigat&oacute;rios</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirInserirCreditoARealizarAction.do?menu=sim"/>'">
					<input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td colspan="2" align="right"><input type="button" name="inserir"
						class="bottonRightCol" value="Inserir"
						onClick="javascript:validarForm(document.forms[0])" /></td>
				</tr>

				<!-- Fim do Corpo - Roberta Costa  11/01/2006  -->

			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
</body>
</html:html>
