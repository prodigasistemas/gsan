<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="GerarArquivoTextoPagamentosContasCobrancaEmpresaActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<SCRIPT language="JavaScript">

	function limparEmpresa() {
		var form = document.forms[0];
		form.idEmpresa.value = "";
		form.nomeEmpresa.value = "";	
		limparPeriodo();
	}
	
	function limparEmpresaTecla() {
		var form = document.forms[0];
		form.nomeEmpresa.value = "";	
	}
	
	function pesquisarEmpresa() {
		var form = document.forms[0];

			abrirPopup('exibirPesquisarEmpresaAction.do?limpaForm=S', 495, 300);
	}
	

	function limparPeriodo() {
		var form = document.forms[0];
		
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.forms[0];
   
	    if (tipoConsulta == 'empresa') {
			form.idEmpresa.value = codigoRegistro;
			form.nomeEmpresa.value = descricaoRegistro;
			form.nomeEmpresa.style.color = "#000000";
			form.action = 'exibirGerarArquivoTextoPagamentosContasCobrancaEmpresaAction.do?limpar=sim';
    		submeterFormPadrao(form);
    	}
    
    }
	
	function validaForm() {
		var form =  document.forms[0];
			if (validateGerarArquivoTextoPagamentosContasCobrancaEmpresaActionForm(form)){
					submeterFormPadrao(form);
			}	
	}
	
	

</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form
	action="/gerarArquivoTextoPagamentosContasCobrancaEmpresa"
	name="GerarArquivoTextoPagamentosContasCobrancaEmpresaActionForm"
	type="gcom.gui.cobranca.GerarArquivoTextoPagamentosContasCobrancaEmpresaActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>


	<table width="780" border="0" cellspacing="5" cellpadding="0">
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
					<td class="parabg">Gerar Arquivo Texto de Pagamentos de Contas em
					Cobrança por Empresa</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para gerar o arquivo TXT, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="30%"><strong>Empresa:<font color="#FF0000">*</font></strong></td>
					<td><html:text maxlength="9" property="idEmpresa" size="9"
						tabindex="14"
						onkeypress="validaEnterComMensagem(event, 'exibirGerarArquivoTextoPagamentosContasCobrancaEmpresaAction.do', 'idEmpresa', 'Empresa');" />
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
					<td><strong>Referência do Pagamento:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><strong> <html:text maxlength="7"
						property="referenciaInicial" size="7" tabindex="8"
						onkeyup="mascaraAnoMes(this, event); replicarCampo(document.forms[0].referenciaFinal, document.forms[0].referenciaInicial);" />
					<strong> a</strong> <html:text maxlength="7"
						property="referenciaFinal" size="7" tabindex="9"
						onkeyup="mascaraAnoMes(this, event);" /> </strong> (mm/aaaa)</td>
				</tr>



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
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirGerarArquivoTextoPagamentosContasCobrancaEmpresaAction.do?menu=sim"/>'">
					<input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="javascript:window.location.href='/gsan/telaPrincipal.do'"></td>
					<td colspan="2" align="right"><input type="button" name="gerar"
						class="bottonRightCol" value="Gerar"
						onClick="javascript:validaForm();" /></td>
				</tr>

			</table>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
