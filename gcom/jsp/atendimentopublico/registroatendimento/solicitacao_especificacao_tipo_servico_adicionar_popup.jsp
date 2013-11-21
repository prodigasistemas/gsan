<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AdicionarSolicitacaoEspecificacaoActionForm" />

<script language="JavaScript">
	window.onmousemove = resizePageSemLink(642, 320);
	var bCancel = false;

    function validateCriterioCobrancaActionForm(form) {
    	if (bCancel)
      		return true;
        else
      		return validateRequired(form) && validateCaracterEspecial(form) && testarCampoValorZero(form.idTipoServico, 'Tipo Serviço') && validateLong(form);
   }

    function caracteresespeciais () {
    	this.aa = new Array("idTipoServico", "Tipo Serviço possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    }

    function IntegerValidations () {
    	this.ac = new Array("idTipoServico", "Tipo Serviço deve ser numérico(a).", new Function ("varName", " return this[varName];"));
     	this.ac = new Array("ordemExecucao", "Ordem Execução deve ser numérico(a).", new Function ("varName", " return this[varName];"));
    }
    
    function required () { 
		this.aa = new Array("idTipoServico", "Informe Tipo Serviço.", new Function ("varName", " return this[varName];"));
	} 
    
  
	function validarForm(form){

	 	if(validateCriterioCobrancaActionForm(form)){
	    	form.action="adicionarSolicitacaoEspecificacaoTipoServicoAction.do";
	    	submeterFormPadrao(form);
	 	}
	}

	function desfazer(){
 		form = document.forms[0];
 		form.action ="exibirAdicionarSolicitacaoEspecificacaoTipoServicoAction.do?limpaSessao=1";
 		submeterFormPadrao(form);
	}

	function voltar(){
 		form = document.forms[0];
 		form.action ="exibirAdicionarSolicitacaoEspecificacaoAction.do";
 		submeterFormPadrao(form);
	}

	function limparPesquisaTipoServico(){

		var form = document.forms[0];
		form.idTipoServico.value= '';
		form.descricaoTipoServico.value= '';
	}


</script>

</head>


<body leftmargin="5" topmargin="5"
	onload="javascript:resizePageSemLink(642, 320);javascript:setarFoco('${requestScope.nomeCampo}');">


<html:form action="/adicionarSolicitacaoEspecificacaoTipoServicoAction"
	name="AdicionarSolicitacaoEspecificacaoActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.AdicionarSolicitacaoEspecificacaoActionForm"
	method="post">

	<table width="615" border="0" cellspacing="5" cellpadding="0">
		<tr>

			<td width="100%" valign="top" class="centercoltext">
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
					<td class="parabg">Adicionar Tipo de Serviço na Especificação da
					Solicitação</td>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">
					<p>Preencha os campos para inserir um tipo de serviço na
					especificação da solicitação:</p>
					<p>&nbsp;</p>
					</td>
				</tr>
				<tr>
					<td><strong>Tipo do Serviço:<font color="#FF0000">*</font></strong></td>
					<td><html:text maxlength="4" property="idTipoServico" size="4"
						tabindex="20"
						onkeypress="javascript:validaEnterComMensagem(event, 'exibirAdicionarSolicitacaoEspecificacaoTipoServicoAction.do?objetoConsulta=1', 'idTipoServico', 'Tipo do Serviço');"
						onkeyup="javascript:document.forms[0].descricaoTipoServico.value = '';"/>
					<a
						href="javascript:redirecionarSubmit('recuperarDadosPesquisarAdicionarSolicitacaoEspecificacaoTipoServicoAction.do?caminhoRetornoTelaPesquisaServicoTipo=exibirAdicionarSolicitacaoEspecificacaoTipoServicoAction&limpar=1');">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Tipo Serviço" /></a> <logic:present
						name="idTipoServicoNaoEncontrado">
						<logic:equal name="idTipoServicoNaoEncontrado" value="exception">
							<html:text property="descricaoTipoServico" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="idTipoServicoNaoEncontrado"
							value="exception">
							<html:text property="descricaoTipoServico" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent
						name="idTipoServicoNaoEncontrado">
						<logic:empty name="AdicionarSolicitacaoEspecificacaoActionForm"
							property="idTipoServico">
							<html:text property="descricaoTipoServico" value="" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="AdicionarSolicitacaoEspecificacaoActionForm"
							property="idTipoServico">
							<html:text property="descricaoTipoServico" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a
						href="javascript:limparPesquisaTipoServico();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Ordem de Execução:</strong></td>
					<td><html:text property="ordemExecucao" /></td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td valign="top"><input type="button" name="ButtonCancelar"
						class="bottonRightCol" value="Voltar" onClick="voltar();"> &nbsp;
					<input name="button" type="button" class="bottonRightCol"
						value="Desfazer" onclick="desfazer();"></td>
					<td valign="top">
					<div align="right"><input name="botaoInserir" type="button"
						class="bottonRightCol" value="Inserir"
						onclick="validarForm(document.forms[0]);" tabindex="17"></div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
	</table>
</body>
</html:form>
</html:html>
