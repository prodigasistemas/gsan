<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.util.ConstantesSistema"%>


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
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AtualizarCobrancaSituacaoActionForm" />

<SCRIPT LANGUAGE="JavaScript">
<!--

function validarForm(formulario){
	
	if(validateAtualizarCobrancaSituacaoActionForm(formulario)){
 		
 		submeterFormPadrao(formulario);
	}
 }

function limparForm() {
		var form = document.AtualizarCobrancaSituacaoActionForm;
		form.descricao.value = "";
		form.quantidadeMesesSituacaoEspecial.value = "";
		
	}
//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/atualizarCobrancaSituacaoAction.do" method="post">

	<INPUT TYPE="hidden" name="removerCobrancaSituacao">
	<INPUT TYPE="hidden" name="limparCampos" id="limparCampos">


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
					<td class="parabg">Atualizar Situação de Cobrança</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para atualizar a situação de cobrança, informe os
					dados abaixo:</td>
				<tr>
					<td width="60%"><strong>C&oacute;digo:</strong></td>
					<td><html:hidden property="codigo" /> 
					<bean:write	name="AtualizarCobrancaSituacaoActionForm" property="codigo" />
					</td>
				</tr>
				
				<tr>
					<td><strong>Descrição: <font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricao" size="50" maxlength="50" /> </span></td>
				</tr>

				
				<tr>
					<td><strong>Motivo de Revisão da Conta:</strong></td>
					<td><html:select property="contaMotivoRevisao">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoContaMotivoRevisao"
							labelProperty="descricaoMotivoRevisaoConta" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				
				<tr>
					<td><strong>Profissão:</strong></td>
					<td><html:select property="profissao">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoProfissao"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				
				<tr>
					<td><strong>Ramo de Atividade:</strong></td>
					<td><html:select property="ramoAtividade">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoRamoAtividade"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Exigência de Advogado:</strong></td>
					<td><html:radio property="indicadorExigenciaAdvogado" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorExigenciaAdvogado"  value="2" /><strong>Não</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Bloqueio de Parcelamento:<font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorBloqueioParcelamento" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorBloqueioParcelamento" value="2" /><strong>Não</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Bloqueio de Inclusão de Imóvel na Situação: <font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorBloqueioRetirada"  value="1" /><strong>Sim</strong>
					<html:radio property="indicadorBloqueioRetirada" value="2" /><strong>Não</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Bloqueio de Retirada de Imóvel da Situação:<font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorBloqueioInclusao"  value="1" /><strong>Sim</strong>
					<html:radio property="indicadorBloqueioInclusao"  value="2" /><strong>Não</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Seleção da Situação Apenas pelos Usuários com Permissão Especial :<font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorSelecaoApenasComPermissao" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorSelecaoApenasComPermissao"  value="2" /><strong>Não</strong>
					</td>
					<td>&nbsp;</td>
				</tr>	
				
				
				<tr>
					<td><strong>Indicador de Prescrição para Imóveis Particulares :<font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorPrescricaoImoveisParticulares" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorPrescricaoImoveisParticulares"  value="2" /><strong>Não</strong>
					</td>
					<td>&nbsp;</td>
				</tr>	
				
				<tr>
					<td><strong>Indicador de não inclusão da cobrança por resultado :<font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorNaoIncluirImoveisEmCobrancaResultado" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorNaoIncluirImoveisEmCobrancaResultado"  value="2" /><strong>Não</strong>
					</td>
					<td>&nbsp;</td>
				</tr>	
							
				
				<tr>
					<td><strong>Indicador de Uso:</strong></td>
					<td><html:radio property="indicadorUso"  value="1"><strong>Ativo</strong></html:radio>
					<html:radio property="indicadorUso"  value="2" ><strong>Inativo</strong></html:radio>
					</td>
					<td>&nbsp;</td>
				</tr>
				

				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>

				<tr>
					<td width="40%" align="left"><input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Voltar"
						onClick="window.history.go(-1)"> <input type="button"
						name="ButtonReset" class="bottonRightCol" value="Desfazer"
						onClick="(document.forms[0]).reset()"> <input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right"><input type="button"
						onClick="javascript:validarForm(document.forms[0]);"
						name="botaoAtualizar" class="bottonRightCol" value="Atualizar"></td>
				</tr>
				</table>
			
			
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>

