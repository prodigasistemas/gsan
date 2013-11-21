<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarTipoPerfilServicoActionForm" />
<script>

 var bCancel = false;

	function validarForm(form){
		if(validatePesquisarTipoPerfilServicoActionForm(form)){
    		submeterFormPadrao(form);
		}
	}

	/* Limpa Form */	 
	function limparForm() {
		var form = document.forms[0];
	    form.descricaoServicoPerfil.value = "";
	    form.abreviaturaServicoPerfil.value = "";
	    form.componenteEquipe.value = "";
        limparPesquisaEquipamentoEspecial();
	    form.veiculoProprio.value = "";	

	    form.action = 'exibirPesquisarTipoPerfilServicoAction.do';
		form.submit();
	}    
	    
    function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
      var form=document.forms[0];
   
      form.equipamentoEspecial.value = codigoRegistro;
      form.descricaoEquipamentoEspecial.value = descricaoRegistro;
  }	
  
  	function limparPesquisaEquipamentoEspecial() {
    	var form = document.forms[0];

	    form.equipamentoEspecial.value = "";
	    form.descricaoEquipamentoEspecial.value = "";

	}
</script>
</head>

<body leftmargin="0" topmargin="0"
	onload="window.focus();resizePageSemLink(670, 410);setarFoco('${requestScope.nomeCampo}');">
<html:form action="/pesquisarTipoPerfilServicoAction" method="post"
	onsubmit="return validatePesquisarTipoPerfilServicoActionForm(this);">
	<table width="650" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="640" valign="top" class="centercoltext">
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
					<td class="parabg">Pesquisar Perfil de Serviço</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="4">Preencha os campos para pesquisar um perfil de
					serviço:</td>
				</tr>
				<!--
        <tr>
          <td width="40%" height="0"><strong>Código:</strong></td>
          <td colspan="3"><html:text maxlength="4" property="idServicoPerfil" size="4"/></td>
        </tr>
        -->
				<tr>
					<td width="40%" height="0"><strong>Descrição do Perfil de Serviço:</strong></td>
					<td colspan="3"><html:text maxlength="30"
						property="descricaoServicoPerfil" size="45" /></td>
				</tr>
				<tr>
					<td width="40%">&nbsp;</td>
					<td colspan="3"><html:radio property="tipoPesquisa"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />Iniciando
					pelo texto <html:radio property="tipoPesquisa" tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />Contendo
					o texto</td>
				</tr>
				<tr>
					<td width="40%" height="0"><strong>Abreviatura do Perfil de
					Serviço:</strong></td>
					<td colspan="3"><html:text maxlength="5"
						property="abreviaturaServicoPerfil" size="10" /></td>
				</tr>
				<tr>
					<td width="40%">&nbsp;</td>
					<td colspan="3"><html:radio property="tipoPesquisaAbreviada"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />Iniciando
					pelo texto <html:radio property="tipoPesquisaAbreviada"
						tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />Contendo
					o texto</td>
				</tr>
				<tr>
					<td width="40%" height="0"><strong>Quantidade de Componentes da
					Equipe:</strong></td>
					<td colspan="3"><html:text maxlength="2"
						property="componenteEquipe" size="4" /></td>
				</tr><!--
				<tr>
					<td width="40%" height="0"><strong>Equipamento Especial:<strong></strong></strong></td>
					<td colspan="3"><html:text property="equipamentoEspecial" size="4"
						maxlength="4"
						onkeypress="validaEnterComMensagem(event, 'exibirPesquisarTipoPerfilServicoAction.do?', 'equipamentoEspecial','Tipo Perfil Serviço');" />
					<a
						href="javascript:redirecionarSubmit('exibirPesquisarTabelaAuxiliarAbreviadaAction.do?tela=equipamentosEspeciais&caminhoRetorno=exibirPesquisarTipoPerfilServicoAction&tipoPesquisa=popup',600,640);">
					<img src="/gsan/imagens/pesquisa.gif" alt="Pesquisar" border="0"
						height="21" width="23"></a> <html:text
						property="descricaoEquipamentoEspecial" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #ff0000"
						size="40" maxlength="40" /> <a
						href="javascript:limparPesquisaEquipamentoEspecial()"> <img
						border="0" title="Apagar"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a></td>
				</tr>
				--><tr>
					<td width="40%"><strong>Equipamento Especial:</strong></td>
					<td colspan="3"><html:text property="equipamentoEspecial"
						tabindex="11" size="4" maxlength="4"
						onkeypress="validaEnterComMensagem(event, 'exibirPesquisarTipoPerfilServicoAction.do?', 'equipamentoEspecial','Tipo Perfil Serviço');" />
					<a
						href="javascript:redirecionarSubmit('exibirPesquisarTabelaAuxiliarAbreviadaAction.do?tela=equipamentosEspeciais&caminhoRetorno=exibirPesquisarTipoPerfilServicoAction&tipoPesquisa=popup',600,640);">
					<img src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						border="0" width="23" height="21" title="Pesquisar"></a> <logic:present
						name="corEquipamentoEspecial">
						<logic:equal name="corEquipamentoEspecial" value="exception">
							<html:text property="descricaoEquipamentoEspecial" size="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corEquipamentoEspecial" value="exception">
							<html:text property="descricaoEquipamentoEspecial" size="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="corEquipamentoEspecial">
						<logic:empty name="PesquisarTipoPerfilServicoActionForm"
							property="equipamentoEspecial">
							<html:text property="descricaoEquipamentoEspecial" size="40" value=""
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="PesquisarTipoPerfilServicoActionForm"
							property="equipamentoEspecial">
							<html:text property="descricaoEquipamentoEspecial" size="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a href="javascript:limparPesquisaEquipamentoEspecial();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td width="40%"><strong>Indicador de Veículo Próprio:</strong></td>
					<td><strong> <html:radio property="veiculoProprio" value="1" />Sim
					</strong> <strong> <html:radio property="veiculoProprio" value="2" />Não
					</strong> <strong> <html:radio property="veiculoProprio" value="" />Todas
					</strong></td>
				</tr>
				<tr>
					<td width="40%" height="0">&nbsp;</td>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td height="24" colspan="3" width="80%"><INPUT TYPE="button"
						class="bottonRightCol" value="Limpar"
						onclick="javascript:limparForm(document.forms[0]);" />
					&nbsp;&nbsp; <logic:present
						name="caminhoRetornoTelaPesquisaTipoPerfil">
						<INPUT TYPE="button" class="bottonRightCol" value="Voltar"
							onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaTipoPerfil}.do')" />
					</logic:present></td>
					<td align="right"><INPUT TYPE="submit" class="bottonRightCol"
						value="Pesquisar" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</body>
</html:form>
</html:html>
