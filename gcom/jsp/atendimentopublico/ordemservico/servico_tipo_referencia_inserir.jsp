<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.util.ConstantesSistema"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirTipoServicoReferenciaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

      var form = document.InserirTipoServicoReferenciaActionForm;

      if (tipoConsulta == 'tipoServico') {
        form.tipoServico.value = codigoRegistro;
        form.nomeTipoServico.value = descricaoRegistro;
        form.nomeTipoServico.style.color = "#000000";
      }
      
    }

function validarForm(form){

	if((testarCampoValorZero(document.InserirTipoServicoReferenciaActionForm.descricao, 'Descrição do Tipo de Serviço de Referência')) && 
	   testarCampoValorZero(document.InserirTipoServicoReferenciaActionForm.abreviatura, 'Abreviatura do Tipo de Serviço de Referência')&&
	   testarCampoValorZero(document.InserirTipoServicoReferenciaActionForm.tipoServico, 'Descrição do Tipo de Serviço')) {
 
			validarIndicadorExistenciaOsReferencia();
 
			if(validateInserirTipoServicoReferenciaActionForm(form)){
    		submeterFormPadrao(form);
			
		
		}
	}
}

function validarIndicadorExistenciaOsReferencia(){

    var form = document.forms[0];
    
    var indice;
    var array = new Array(form.indicadorExistenciaOsReferencia.length);
    var selecionado = "";
    var formulario = document.forms[0]; 
    for(indice = 0; indice < form.elements.length; indice++){
	   if (form.elements[indice].type == 'radio' && form.elements[indice].checked == true && formulario.elements[indice].name == "indicadorExistenciaOsReferencia") {
	      selecionado = form.elements[indice].value;
	      indice = form.elements.length;
	   }
    }    
	if(selecionado == ''){
		alert('Informe Indicador de existência da OS de Referência.');
		indicadorExistenciaOsReferencia.focus();
	}	
}



	function limparForm() {
	var form = document.InserirTipoServicoReferenciaActionForm;
		form.descricao.value = "";
		form.abreviatura.value = "";
		form.indicadorExistenciaOsReferencia[0].checked = false;
		form.indicadorExistenciaOsReferencia[1].checked = false;
		form.situacaoOSAntes.value = "-1"; 
		form.situacaoOSApos.value = "-1";
		form.indicadorFiscalizacao[0].checked = false;
		form.indicadorFiscalizacao[1].checked = false;
		form.indicadorDiagnostico[0].checked = false;
		form.indicadorDiagnostico[1].checked = false;		
		limparTipoServico();
	}

	function limparTipoServico() {
	var form = document.InserirTipoServicoReferenciaActionForm;
		form.tipoServico.value = "";
		form.nomeTipoServico.value = "";
		form.tipoServico.focus();
	}
	function limparNomeTipoServico() {
	var form = document.InserirTipoServicoReferenciaActionForm;
		form.nomeTipoServico.value = "";
	}
	
	function dasabilitaSituacaoReferencia(situacao) {
		var form = document.InserirTipoServicoReferenciaActionForm;
		
		if (situacao == '2') {
			form.situacaoOSAntes.selectedIndex = 0;
			form.situacaoOSAntes.disabled = true;
			form.situacaoOSApos.selectedIndex = 0;
			form.situacaoOSApos.disabled = true;
		} else {
			form.situacaoOSAntes.disabled = false;
			form.situacaoOSApos.disabled = false;
		}
	}
	
	function chamarOpener(){
		window.opener.reload();
		window.close();	
	}
	
</script>


</head>

<logic:notPresent name="fecharPopup">
<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');dasabilitaSituacaoReferencia('${requestScope.situacao}');">
</logic:notPresent>
<logic:present name="fecharPopup">
<body leftmargin="5" topmargin="5"
	onload="javascript:chamarOpener();">
</logic:present>
<html:form action="/inserirTipoServicoReferenciaAction" method="post"
	name="InserirTipoServicoReferenciaActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.InserirTipoServicoReferenciaActionForm"
	onsubmit="return validateInserirTipoServicoReferenciaActionForm(this);">

	<logic:notPresent name="semMenu">

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
			<td width="615" valign="top" class="centercoltext">
				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
			
	</logic:notPresent>
	<logic:present name="semMenu">
		<table width="650" border="0" cellspacing="5" cellpadding="0">
			<tr>
				<td width="615" valign="top" class="centercoltext">
				<table height="100%">
					<tr>
						<td></td>
					</tr>
			</table>
	</logic:present>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Inserir Tipo de Serviço de Referência</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>

					<td colspan="2">Para adicionar o tipo de serviço de referência,
					informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="162"><strong>Descri&ccedil;&atilde;o:<font
						color="#FF0000">*</font></strong></td>

					<td><strong> <html:text property="descricao" size="50"
						maxlength="50" /> </strong></td>
				</tr>
				<tr>
					<td><strong>Abreviatura:</strong></td>
					<td><strong> <html:text property="abreviatura" size="5"
						maxlength="5" /> </strong></td>
				</tr>
				<tr>
					<td><strong>Indicador de existência da OS de Referência:<font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorExistenciaOsReferencia"
						value="1" onclick="javascript:dasabilitaSituacaoReferencia(1)" />
					<strong>Sim <html:radio property="indicadorExistenciaOsReferencia"
						value="2" onclick="javascript:dasabilitaSituacaoReferencia(2)" />
					N&atilde;o</strong> </strong></td>

				</tr>

				<tr>
					<td><strong>Situação da OS de Referência Antes:</strong></td>
					<td><html:select property="situacaoOSAntes">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:option
							value="<%=""+ConstantesSistema.SITUACAO_REFERENCIA_PENDENTE%>">Pendente</html:option>
						<html:option
							value="<%=""+ConstantesSistema.SITUACAO_REFERENCIA_ENCERRADA%>">Encerrada</html:option>
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				<tr>
					<td><strong>Situação da OS de Referência Após:</strong></td>
					<td><html:select property="situacaoOSApos">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:option
							value="<%=""+ConstantesSistema.SITUACAO_REFERENCIA_ENCERRADA%>">Encerrada</html:option>
						<html:option
							value="<%=""+ConstantesSistema.SITUACAO_REFERENCIA_PENDENTE_AGUARDANDO_RETORNO_OS_REFERENCIA%>">Pendente aguardando retorno da OS de
						refer&ecirc;ncia</html:option>
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>

				<tr>
					<td height="10"><strong>Tipo de Serviço:<span class="style2"></span></strong></td>
					<td><span class="style2"> <html:text property="tipoServico"
						size="4" maxlength="4"
						onkeypress="javascript:limparNomeTipoServico();validaEnter(event, 'exibirInserirTipoServicoReferenciaAction.do?objetoConsulta=1', 'tipoServico');" />
					<a href="javascript:abrirPopup('exibirPesquisarTipoServicoAction.do', 400, 800);"> <img
						src="/gsan/imagens/pesquisa.gif" alt="Pesquisar" border="0"
						height="21" width="23"></a> <logic:present
						name="tipoServicoInexistente" scope="request">
						<html:text property="nomeTipoServico" size="35" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000;" />
					</logic:present> <logic:notPresent name="tipoServicoInexistente"
						scope="request">
						<html:text property="nomeTipoServico" size="35" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000;" />
					</logic:notPresent> <a href="javascript:limparTipoServico();"> <img
						border="0" title="Apagar"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a></span></td>

				</tr>
				<tr>
					<td><strong>Indicativo de Diagnóstico:</strong></td>
					<td><strong> <html:radio property="indicadorDiagnostico"
						value="1" />
					<strong>Sim <html:radio property="indicadorDiagnostico"
						value="2" />
					N&atilde;o</strong> </strong></td>

				</tr>
				<tr>
					<td><strong>Indicativo de Fiscalização:</strong></td>
					<td><strong> <html:radio property="indicadorFiscalizacao"
						value="1" />
					<strong>Sim <html:radio property="indicadorFiscalizacao"
						value="2" />
					N&atilde;o</strong> </strong></td>

				</tr>

				<tr>
					<td height="19"><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<logic:notPresent name="semMenu">
						<input name="Button" type="button"
							class="bottonRightCol" value="Cancelar" align="left"
							onclick="window.location.href='/gsan/telaPrincipal.do'"> 
						<input
							name="Button" type="button" class="bottonRightCol"
							value="Desfazer" align="left"
							onclick="window.location.href='/gsan/exibirInserirTipoServicoReferenciaAction.do?menu=sim'">
						</logic:notPresent>
						<logic:present name="semMenu">
							<input
								name="Button" type="button" class="bottonRightCol"
								value="Desfazer" align="left"
								onclick="javascript:limparForm();">
						</logic:present>			
					</td>
					<td align="right" height="24"><input type="button" name="Button"
						class="bottonRightCol" value="Inserir"
						onClick="javascript:validarForm(document.forms[0]); validarIndicadorExistenciaOsReferencia();" /></td>
					<td>&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<logic:notPresent name="semMenu">
		<tr>
			<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
		</tr>
	</logic:notPresent>
</html:form>
</body>
</html:html>

