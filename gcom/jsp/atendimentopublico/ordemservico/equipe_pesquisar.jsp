<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarEquipeActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(form){
		if(validatePesquisarEquipeActionForm(form)){
    		submeterFormPadrao(form);
		}
	}

	/* Limpa Form */	 
	function limparForm() {
		var form = document.PesquisarEquipeActionForm;
		form.equipeId.value = "";
		form.nomeEquipe.value = "";
		form.placaVeiculo.value = "";
		form.cargaTrabalhoDia.value = "";
		limparUnidadeOrganizacional();
		limparTipoPerfilServico();
		form.action = 'exibirPesquisarEquipeAction.do';
		form.submit();
	}
	
	/* Limpa Unidade Organizacional do Form */
	function limparUnidadeOrganizacional() {
		var form = document.PesquisarEquipeActionForm;
		form.unidadeOrganizacionalId.value = "";
		form.unidadeOrganizacionalDescricao.value = "";
	}
	/* Limpa Tipo Perfil Servico do Form */	 
	function limparTipoPerfilServico() {
		var form = document.PesquisarEquipeActionForm;
		form.tipoPerfilServicoId.value = "";
		form.tipoPerfilServicoDescricao.value = "";		
	}
	/* Chama Popup */ 
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			} else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				} else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}
	/* Recupera Dados Popup */	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];

	    if (tipoConsulta == 'unidadeOrganizacional') {
	      form.unidadeOrganizacionalId.value = codigoRegistro;
	      form.unidadeOrganizacionalDescricao.value = descricaoRegistro;
	    } else if (tipoConsulta == 'servicoPerfilTipo') {
	    	form.tipoPerfilServicoId.value = codigoRegistro;
	    	form.tipoPerfilServicoDescricao.value = descricaoRegistro;
	    }
	    form.action='exibirPesquisarEquipeAction.do';
	    form.submit();
	}
		
</script>


</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(680, 430);">
<html:form action="/pesquisarEquipeAction" method="post" onsubmit="window.focus();return validatePesquisarEquipeActionForm(this);javascript:setarFoco('${requestScope.nomeCampo}');">
	<table width="635" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="625" valign="top" class="centercoltext">
				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11">
							<img border="0"	src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
						</td>
						<td class="parabg">Pesquisar Equipe</td>
						<td width="11">
							<img border="0"	src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
				<p>&nbsp;</p>
				<table width="100%" border="0">
					<tr>
						<td colspan="4">Preencha o campo para pesquisar uma equipe:</td>
					</tr>
			        <tr>
			          <td height="0">
			          	<strong>C&oacute;digo da Equipe:</strong>
			          </td>
			          <td colspan="3">
			          	<html:text property="equipeId" size="3" maxlength="5" />
			          </td>
			        </tr>
        			<tr> 
          				<td height="0">
          					<strong>Nome da Equipe:</strong>
          				</td>
          				<td colspan="3">
          					<html:text property="nomeEquipe" size="20" maxlength="20" />
          				</td>
        			</tr>
        			<tr> 
          				<td height="0">
          					<strong>Placa do Ve&iacute;culo:</strong>
          				</td>
          				<td colspan="3">
          					<html:text property="placaVeiculo" size="7" maxlength="7" />
          				</td>
        			</tr>
        			<tr> 
          				<td height="0">
          					<strong>
          						Carga de Trabalho Di&aacute;ria	<font color="#000000"></font> (hora):
            				</strong>
            			</td>
          				<td colspan="3">
          					<strong>
          						<html:text property="cargaTrabalhoDia" size="2" maxlength="2" />
            				</strong>
            			</td>
        			</tr>
        			<tr> 
          				<td height="0">
          					<strong>Unidade Organizacional:</strong>
          				</td>
          				<td colspan="3">
          					<strong>
								<html:text property="unidadeOrganizacionalId" size="4" maxlength="4"
										   onkeypress="javascript:validaEnterComMensagem(event, 'exibirPesquisarEquipeAction.do?validaUnidadeOrganizacional=true', 'unidadeOrganizacionalId','Unidade Organizacional');"/>
								<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" 
									 style="cursor:hand;" name="imagem"	onclick="chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do', 'unidadeOrganizacional', null, null, 275, 480, '',document.forms[0].unidadeOrganizacionalId);"
									 alt="Pesquisar">
								<html:text property="unidadeOrganizacionalDescricao" readonly="true"
										   style="background-color:#EFEFEF; border:0; color: #ff0000" size="45" maxlength="45" />
								<a href="javascript:limparUnidadeOrganizacional();">
									<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
            				</strong>
            			</td>
        			</tr>
        			<tr> 
          				<td height="0">
          					<strong>Tipo Perfil Servi&ccedil;o<font color="#000000">:</font></strong>
          				</td>
          				<td colspan="3">
          					<strong> 
								<html:text property="tipoPerfilServicoId"	size="3" maxlength="3"
										   onkeypress="javascript:validaEnterComMensagem(event, 'exibirPesquisarEquipeAction.do?validaTipoPerfilServico=true', 'tipoPerfilServicoId','Tipo Perfil Serviço');"/>
								<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" 
									 style="cursor:hand;" name="imagem"	onclick="chamarPopup('exibirPesquisarTipoPerfilServicoAction.do', 'servicoPerfilTipo', null, null, 275, 480, '',document.forms[0].tipoPerfilServicoId);"
									 alt="Pesquisar">
								<html:text property="tipoPerfilServicoDescricao" readonly="true"
										   style="background-color:#EFEFEF; border:0; color: #ff0000" size="45" maxlength="45" />
								<a href="javascript:limparTipoPerfilServico();">
									<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
            				</strong> 
          				</td>
        			</tr>
					<tr>
						<td colspan="2">
							<%--<input name="Button" type="button" class="bottonRightCol" value="Voltar" align="left"
									onclick="window.location.href='<html:rewrite page="/exibirPesquisarEquipeAction.do"/>'">--%>
							<logic:present name="caminhoRetornoTelaPesquisaEquipe">
	          					<INPUT TYPE="button" class="bottonRightCol" value="Voltar" onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaEquipe}.do')"/>
	          				</logic:present>
							<input name="Button" type="button" class="bottonRightCol" value="Limpar" align="left"
								   onclick="javascript:limparForm(document.forms[0]);">
						</td>
						<td align="right" height="24">
							<input type="button" name="Button"	class="bottonRightCol" value="Pesquisar"
								   onClick="javascript:validarForm(document.forms[0])" />
						</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<p>&nbsp;</p>
</html:form>
</body>
</html:html>

