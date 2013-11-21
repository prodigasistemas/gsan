<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" 
	formName="GerarRelatorioGestaoServicosUPAActionForm" dynamicJavascript="true"/>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

	function validarForm() {
		var form = document.forms[0];
	
		if(validateGerarRelatorioGestaoServicosUPAActionForm(form)){
			if (validacaoForm()) {
				enviarSelectMultiplo('GerarRelatorioGestaoServicosUPAActionForm','tipoServicoSelecionados');
				toggleBox('demodiv', 1);
			}
		}
	}
	
	function validacaoForm() {
		var form = document.forms[0];
		
		var dataGeracaoInicial = trim(form.periodoGeracaoInicial.value);
		var dataGeracaoFinal = trim(form.periodoGeracaoFinal.value);
		
		if (dataGeracaoInicial == null || dataGeracaoInicial == '') {
    		alert('Informe Data Inicial do Período de Geração.');
    		return false;
    	}
		if (dataGeracaoFinal == null || dataGeracaoFinal == '') {
    		alert('Informe Data Final do Período de Geração');
       		return false;
    	}
    	
    	var unidadeOrganizacional = trim(form.idUnidadeOrganizacional.value);
    	var unidadeSuperior = trim(form.idUnidadeSuperior.value);
    	var empresa = trim(form.idEmpresa.value);
    	
    	if ((unidadeOrganizacional == null || unidadeOrganizacional == '') &&
    	    (unidadeSuperior == null || unidadeSuperior == '') &&
    	    (empresa == null || empresa == '')) {
    	    alert('Informe Unidade Organizacional OU Unidade Superior OU Empresa.');
    	    return false;
    	}
    	
    	return true;
	}

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
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
		
		if (tipoConsulta == 'unidadeOrganizacional') {
			form.descricaoUnidadeOrganizacional.style.color = "#000000";
			form.idUnidadeOrganizacional.value = codigoRegistro;
			form.descricaoUnidadeOrganizacional.value = descricaoRegistro;
			
			limparUnidadeSuperior();
			limparEmpresa();
		}
		else if (tipoConsulta == 'unidadeSuperior') {
			form.descricaoUnidadeSuperior.style.color = "#000000";
			form.idUnidadeSuperior.value = codigoRegistro;
			form.descricaoUnidadeSuperior.value = descricaoRegistro;
			
			limparUnidadeOrganizacional();
			limparEmpresa();
		}
		else if (tipoConsulta == 'empresa') {
			form.descricaoEmpresa.style.color = "#000000";
			form.idEmpresa.value = codigoRegistro;
			form.descricaoEmpresa.value = descricaoRegistro;
			
			limparUnidadeOrganizacional();
			limparUnidadeSuperior();
		}
	}
	
	function replicaDataGeracao() {
		var form = document.forms[0];
		form.periodoGeracaoFinal.value = form.periodoGeracaoInicial.value;
	}
	
	function limparUnidadeOrganizacional() {
		var form = document.forms[0];
		
		form.idUnidadeOrganizacional.value = "";
		form.descricaoUnidadeOrganizacional.value = "";
	}
	
	function limparUnidadeSuperior() {
		var form = document.forms[0];
		
		form.idUnidadeSuperior.value = "";
		form.descricaoUnidadeSuperior.value = "";
	}
	
	function limparEmpresa() {
		var form = document.forms[0];
		
		form.idEmpresa.value = "";
		form.descricaoEmpresa.value = "";
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="window.focus();javascript:setarFoco('${requestScope.nomeCampo}');" >

<html:form action="/gerarRelatorioGestaoServicosUPAAction" method="post"
	name="GerarRelatorioGestaoServicosUPAActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.GerarRelatorioGestaoServicosUPAActionForm">

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
			<td width="600" valign="top" class="centercoltext">
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
					<td class="parabg">Filtrar Relatório de Gestão de Serviços UPA</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">

				<tr>
					<td colspan="2">Para selecionar ordens de serviço para geração do
					relatório de gestão, informe os dados abaixo:</td>
				</tr>
				
				<tr>
					<td><strong>Situa&ccedil;&atilde;o da Ordem de Servi&ccedil;o:<font color="#FF0000">*</font></strong>
					</td>
					<td><html:radio property="situacaoOrdemServico"
						value="<%=""+ConstantesSistema.SITUACAO_REFERENCIA_ENCERRADA%>" /> <strong>Encerrados</strong>
					<html:radio property="situacaoOrdemServico"
						value="<%=""+ConstantesSistema.SITUACAO_REFERENCIA_PENDENTE%>" /> <strong>Pendentes</strong>
					<html:radio property="situacaoOrdemServico" value=""  /> <strong>Ambos</strong>
					</td>
				</tr>
				
				<tr>
					<td height="10" colspan="3">
					<div align="right">
					<hr>
					</div>
					</td>
				</tr>
				
				<tr>
					<td width="120"><strong>Tipo de Servi&ccedil;o:</strong></td>
					<td colspan="2">
					<table width="100%" border=0 cellpadding=0 cellspacing=0
						align="left">
						<tr>
							<td width="175">

							<div align="left"><strong>Disponíveis</strong></div>

							<html:select property="tipoServico" size="6" multiple="true"
								style="width:190px">

								<html:options collection="colecaoTipoServico"
									labelProperty="descricao" property="id" />
							</html:select></td>

							<td width="5" valign="center"><br>
							<table width="50" align="center">
								<tr>
									<td align="center"><input type="button" class="bottonRightCol"
										onclick="javascript:MoverTodosDadosSelectMenu1PARAMenu2('GerarRelatorioGestaoServicosUPAActionForm', 'tipoServico', 'tipoServicoSelecionados');"
										value=" &gt;&gt; "></td>
								</tr>

								<tr>
									<td align="center"><input type="button" class="bottonRightCol"
										onclick="javascript:MoverDadosSelectMenu1PARAMenu2('GerarRelatorioGestaoServicosUPAActionForm', 'tipoServico', 'tipoServicoSelecionados');"
										value=" &nbsp;&gt;  "></td>
								</tr>

								<tr>
									<td align="center"><input type="button" class="bottonRightCol"
										onclick="javascript:MoverDadosSelectMenu2PARAMenu1('GerarRelatorioGestaoServicosUPAActionForm', 'tipoServico', 'tipoServicoSelecionados');"
										value=" &nbsp;&lt;  "></td>
								</tr>

								<tr>
									<td align="center"><input type="button" class="bottonRightCol"
										onclick="javascript:MoverTodosDadosSelectMenu2PARAMenu1('GerarRelatorioGestaoServicosUPAActionForm', 'tipoServico', 'tipoServicoSelecionados');"
										value=" &lt;&lt; "></td>
								</tr>
							</table>
							</td>

							<td>
							<div align="left"><strong>Selecionados</strong></div>

							<html:select property="tipoServicoSelecionados" size="6"
								multiple="true" style="width:190px">

							</html:select></td>
						</tr>
					</table>
					</td>
				</tr>
				
				<tr>
					<td height="10" colspan="3">
					<div align="right">
					<hr>
					</div>
					</td>
				</tr>
				
				<tr>
					<td><strong>Período de Geração dos Serviços:<font color="#FF0000">*</font></strong></td>

					<td colspan="6"><span class="style2"> <strong> <html:text
						property="periodoGeracaoInicial" size="11" maxlength="10"
						tabindex="3"
						onkeyup="mascaraData(this, event);replicaDataGeracao();" /> <a
						href="javascript:abrirCalendarioReplicando('GerarRelatorioGestaoServicosUPAActionForm', 'periodoGeracaoInicial','periodoGeracaoFinal');">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="16" height="15" border="0" alt="Exibir Calendário"
						tabindex="4" /></a> a <html:text
						property="periodoGeracaoFinal" size="11" maxlength="10"
						tabindex="3" onkeyup="mascaraData(this, event)" /> <a
						href="javascript:abrirCalendario('GerarRelatorioGestaoServicosUPAActionForm', 'periodoGeracaoFinal');">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="16" height="15" border="0" alt="Exibir Calendário"
						tabindex="4" /></a> </strong>(dd/mm/aaaa)<strong> </strong> </span>
					</td>
				</tr>
				
				<tr>
					<td height="10" colspan="3">
					<div align="right">
					<hr>
					</div>
					</td>
				</tr>
				
				<tr>
					<td><strong>Unidade Organizacional:</strong></td>
					<td>
						<html:text property="idUnidadeOrganizacional" maxlength="4" size="4"
							onkeypress="limparUnidadeSuperior(); limparEmpresa(); validaEnterComMensagem(event, 'exibirGerarRelatorioGestaoServicosUPAAction.do?objetoConsulta=1','idUnidadeOrganizacional','Unidade Organizacional');" />
						
						<a href="javascript: chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do', 'idUnidadeOrganizacional', null, null, 275, 480, '', document.forms[0].idUnidadeSuperior);">
						<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Unidade Organizacional" /></a> 
						
						<logic:present name="unidadeOrganizacionalEncontrada" scope="request">
							<html:text property="descricaoUnidadeOrganizacional" size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present>
						
						<logic:notPresent name="unidadeOrganizacionalEncontrada" scope="request">
							<html:text property="descricaoUnidadeOrganizacional" size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
						
						<a href="javascript:limparUnidadeOrganizacional();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /> 
						</a> 
					</td>
				</tr>
				
				<tr>
					<td><strong>Unidade Superior:</strong></td>
					<td>
						<html:text property="idUnidadeSuperior" maxlength="4" size="4"
							onkeypress="limparUnidadeOrganizacional(); limparEmpresa(); validaEnterComMensagem(event, 'exibirGerarRelatorioGestaoServicosUPAAction.do?objetoConsulta=2','idUnidadeSuperior','Unidade Superior');" />
						
						<a href="javascript: chamarPopup('exibirPesquisarUnidadeSuperiorAction.do', 'idUnidadeSuperior', null, null, 275, 480, '', document.forms[0].idUnidadeSuperior);">
						<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Unidade Superior" /></a> 
						
						<logic:present name="unidadeSuperiorEncontrada" scope="request">
							<html:text property="descricaoUnidadeSuperior" size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present>
						
						<logic:notPresent name="unidadeSuperiorEncontrada" scope="request">
							<html:text property="descricaoUnidadeSuperior" size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
						
						<a href="javascript:limparUnidadeSuperior();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /> 
						</a> 
					</td>
				</tr>
				
				<tr>
					<td><strong>Empresa:</strong></td>
					<td>
						<html:text property="idEmpresa" maxlength="4" size="4"
							onkeypress="limparUnidadeOrganizacional(); limparUnidadeSuperior(); validaEnterComMensagem(event, 'exibirGerarRelatorioGestaoServicosUPAAction.do?objetoConsulta=3','idEmpresa','Empresa');" />
						
						<a href="javascript: chamarPopup('exibirPesquisarEmpresaAction.do', 'idEmpresa', null, null, 275, 480, '', document.forms[0].idEmpresa);">
						<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Empresa" /></a> 
						
						<logic:present name="empresaEncontrada" scope="request">
							<html:text property="descricaoEmpresa" size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present>
						
						<logic:notPresent name="empresaEncontrada" scope="request">
							<html:text property="descricaoEmpresa" size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
						
						<a href="javascript:limparEmpresa();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /> 
						</a> 
					</td>
				</tr>
				
				<tr>
					<td height="10" colspan="3">
					<div align="right">
					<hr>
					</div>
					<div align="right"></div>
					</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>
					<td height="24"><input type="button" class="bottonRightCol"
						value="Limpar"
						onclick="window.location.href='/gsan/exibirGerarRelatorioGestaoServicosUPAAction.do?menu=sim';" />
					</td>

					<td align="right"><input type="button" name="Button"
						class="bottonRightCol" value="Filtrar"
						onClick="javascript:validarForm()" /></td>

				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioGestaoServicosUPAAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
