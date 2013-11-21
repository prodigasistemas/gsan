<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="gcom.util.ConstantesSistema"%>

<html:html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AtualizarAutoInfracaoActionForm" dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(){
			var form = document.forms[0];	

			if(validateAtualizarAutoInfracaoActionForm(form) && validarDataInicioeTerminoRecurso()){    		
		  			submeterFormPadrao(form);
			}
	}
	
	function validarDataInicioeTerminoRecurso(){
		var form = document.forms[0];
		
		situacaoInicio = true;
		situacaoTermino = true;
		
		
		if(form.dataInicioRecurso != undefined){
			if(form.dataInicioRecurso.value != ''){
				situacaoInicio = verificaDataMensagemPersonalizada(form.dataInicioRecurso, "Data de Início do Recurso inválida");
			}
		}
		
		if(form.dataTerminoRecurso != undefined){
			
			if(form.dataTerminoRecurso.value != ''){
				situacaoTermino = verificaDataMensagemPersonalizada(form.dataTerminoRecurso, "Data de Termino do Recurso inválida");
			}
		}
		
		if(situacaoInicio && situacaoTermino){
			return true;
		}
	
	}
	
	function limparFuncionario() {
		var form = document.forms[0];
		form.idFuncionario.value = "";
		form.descricaoFuncionario.value = "";
	
	}
	
	function reload(){
		var form = document.forms[0];
		form.action = 'exibirAtualizarAutoInfracaoAction.do?reload=sim';
		form.submit();
	}
	
	//Recupera Dados Popup
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
	    
	    if (tipoConsulta == 'funcionario') {
	    	form.idFuncionario.value = codigoRegistro;
	      	reload();
	    }
	}
		
</script>

</head>

<body leftmargin="5" topmargin="5">
<html:form action="/atualizarAutoInfracaoAction"
	name="AtualizarAutoInfracaoActionForm"
	type="gcom.gui.faturamento.autoinfracao.AtualizarAutoInfracaoActionForm"
	method="post"
	onsubmit="return validateAtualizarAutoInfracaoActionForm(this);">

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
			<td width="615" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Atualizar Autos de Infração</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para atualizar o auto de infração, informe o dado
					abaixo:</td>
				</tr>
				<tr>
					<td width="120"><strong>Identificador do Auto de Infração:</strong></td>
					<td colspan="2"><html:text property="idAutoInfracao" size="7"
						maxlength="7" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" />
				</tr>
				<tr>
					<td width="120"><strong>Matrícula do Imóvel:</strong></td>
					<td colspan="2"><html:text property="idImovel" size="9"
						maxlength="9" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /> <html:text
						property="descricaoImovel" size="35" maxlength="35"
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /></td>
				</tr>
				<tr>
					<td width="120"><strong>Cliente Usuário:</strong></td>
					<td colspan="2"><html:text property="nomeCliente" size="35"
						maxlength="35" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /></td>
				</tr>
				<tr>
					<td colspan="3">
					<hr>
					</td>

				</tr>
				<tr>
					<td width="120"><strong>Funcionário do Responsável:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="idFuncionario" size="9"
						maxlength="9"
						onkeyup="validaEnterComMensagem(event, 'exibirAtualizarAutoInfracaoAction.do?reload=sim', 'idFuncionario');" />
					<a
						href="javascript:abrirPopup('exibirFuncionarioPesquisar.do?menu=sim', 495, 300);"><img
						width="23" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						style="border:0;" alt="Pesquisar" /></a><logic:present
						name="funcionarioInexistente" scope="request">
						<html:text property="descricaoFuncionario" size="35"
							maxlength="35" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="funcionarioInexistente"
						scope="request">
						<html:text property="descricaoFuncionario" size="35"
							maxlength="35" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparFuncionario();"><img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>

				<tr>
					<td width="120"><strong>Irregularidade Constatada:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:select property="idFiscalizacaoSituacao">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoFiscalizacaoSituacao"
							labelProperty="descricaoFiscalizacaoSituacao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>

				<tr>
					<td width="120"><strong>Situação do Auto:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:select property="idAutoInfracaoSituacao"
						onchange="javascript:reload();">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoAutoInfracaoSituacao"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>

				<tr>
					<td width="120"><strong>Quantidade Parcelas Débito:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="quantidadeParcelas" size="7"
						maxlength="7" />
				</tr>

				<tr>
					<td width="120"><strong>Data de Emissão:<font color="#FF0000">*</font></strong></td>

					<td colspan="2"><strong> <html:text maxlength="10"
						property="dataEmissao" size="10"
						onkeyup="mascaraData(this, event); " /> <a
						href="javascript:abrirCalendario('AtualizarAutoInfracaoActionForm', 'dataEmissao');">

					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>

					dd/mm/aaaa</td>
				</tr>



				<logic:present name="dataInicialRecurso" scope="session">
					<tr>
						<td width="120"><strong>Data de Início do Recurso:</strong></td>

						<td colspan="2"><strong> <html:text maxlength="10"
							property="dataInicioRecurso" size="10"
							onkeyup="mascaraData(this, event);" /> <a
							href="javascript:abrirCalendario('AtualizarAutoInfracaoActionForm', 'dataInicioRecurso');">
						<img border="0"
							src="<bean:message key="caminho.imagens"/>calendario.gif"
							width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
						dd/mm/aaaa</td>
					</tr>
				</logic:present>

				<logic:present name="dataTerminoRecurso" scope="session">
					<tr>
						<td width="120"><strong>Data de Término do Recurso:</strong></td>

						<td colspan="2"><strong> <html:text maxlength="10"
							property="dataTerminoRecurso" size="10"
							onkeyup="mascaraData(this, event);" /> <a
							href="javascript:abrirCalendario('AtualizarAutoInfracaoActionForm', 'dataTerminoRecurso');">
						<img border="0"
							src="<bean:message key="caminho.imagens"/>calendario.gif"
							width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
						dd/mm/aaaa</td>
					</tr>
				</logic:present>

				<tr>
					<td><strong>Observação:</strong></td>
					<td><html:textarea property="observacao" cols="50"
						onkeyup=" validarTamanhoMaximoTextArea(this,400);" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td colspan="2"><font color="#FF0000"> <logic:present name="manter"
						scope="session">

						<input type="button" name="ButtonReset" class="bottonRightCol"
							value="Voltar"
							onClick="javascript:window.location.href='/gsan/exibirManterAutoInfracaoAction.do'">

					</logic:present> <logic:notPresent name="manter" scope="session">
						<input type="button" name="ButtonReset" class="bottonRightCol"
							value="Voltar"
							onClick="javascript:window.location.href='/gsan/exibirFiltrarAutoInfracaoAction.do'">
					</logic:notPresent> <input type="button" name="ButtonReset"
						class="bottonRightCol" value="Desfazer"
						onClick="window.location.href='<html:rewrite page="/exibirAtualizarAutoInfracaoAction.do?desfazer=S&idAutoInfracao=${requestScope.idAutoInfracao}"/>'">

					<input type="button" name="ButtonCancelar" class="bottonRightCol"
						value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</font></td>
					<td align="right"><input type="button" name="Button"
						class="bottonRightCol" value="Atualizar"
						onClick="javascript:validarForm(document.forms[0]);" /></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>



			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
