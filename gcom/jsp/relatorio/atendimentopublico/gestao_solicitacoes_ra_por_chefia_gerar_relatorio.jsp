<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ include file="/jsp/util/telaespera.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false" 
	formName="GerarRelatorioGestaoSolicitacoesRAPorChefiaActionForm" staticJavascript="false" dynamicJavascript="true"/>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

	function reload() {
		var form = document.forms[0];
		
		if (form.solicitacaoTipo.selectedIndex == 0) {
			limparEspecificacao();
		}
		
		form.action = '/gsan/exibirGerarRelatorioGestaoSolicitacoesRAPorChefiaAction.do?onchange=true';
		form.submit();
	}

	function validarForm() {
		var form = document.forms[0];
	
		if(validateGerarRelatorioGestaoSolicitacoesRAPorChefiaActionForm(form)){
			if (validacaoForm()) {
				toggleBox('demodiv', 1);
			}
		}
	}
	
	function validacaoForm() {
		var form = document.forms[0];
		
		var dataAtendimentoInicial = trim(form.dataAtendimentoInicial.value);
		var dataAtendimentoFinal = trim(form.dataAtendimentoFinal.value);
		
		if (dataAtendimentoInicial == null || dataAtendimentoInicial == '') {
    		alert('Informe Data Inicial do Período de Atendimento.');
    		return false;
    	}
		if (dataAtendimentoFinal == null || dataAtendimentoFinal == '') {
    		alert('Informe Data Final do Período de Atendimento');
       		return false;
    	}    	
    	/*if ( parseInt(dataAtendimentoFinal) < parseInt(dataAtendimentoInicial) ) {
    		alert(parseInt(dataAtendimentoFinal));
    		alert(parseInt(dataAtendimentoInicial));
    		alert('A data inicial deve ser anterior à data final');
       		return false;
    	}*/
    	if ( !comparaData(dataAtendimentoInicial, "<=", dataAtendimentoFinal ) ) {
    		alert('A data inicial deve ser anterior à data final');
       		return false;
    	}
    	
    	var unidade = trim(form.idUnidade.value);
    	var unidadeSuperior = trim(form.idUnidadeSuperior.value);
    	var municipio = trim(form.idMunicipio.value);
    	var bairro = trim(form.idBairro.value);
    	
    	if ((unidade == null || unidade == '') &&
    	    (unidadeSuperior == null || unidadeSuperior == '')) {
    	    alert('Informe Unidade Organizacional OU Unidade Superior.');
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
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
		}
	}
	
	function recuperarDadosPopup(codigoRegistro, nomeRegistro, tipoConsulta) {
		var form = document.forms[0];
		
		if (tipoConsulta == 'unidade') {
			form.nomeUnidade.style.color = "#000000";
			form.idUnidade.value = codigoRegistro;
			form.nomeUnidade.value = nomeRegistro;
			
			limparUnidadeSuperior();
		}
		else if (tipoConsulta == 'unidadeSuperior') {
			form.nomeUnidadeSuperior.style.color = "#000000";
			form.idUnidadeSuperior.value = codigoRegistro;
			form.nomeUnidadeSuperior.value = nomeRegistro;
			
			limparUnidade();
		}
		
		else if (tipoConsulta == 'municipio') {
			form.nomeMunicipio.style.color = "#000000";
			form.idMunicipio.value = codigoRegistro;
			form.nomeMunicipio.value = nomeRegistro;
		}
		else if (tipoConsulta == 'bairro') {
			form.nomeBairro.style.color = "#000000";
			form.idBairro.value = codigoRegistro;
			form.nomeBairro.value = nomeRegistro;
		}
		else if (tipoConsulta == 'unidadeOrganizacional') {
			form.nomeUnidade.style.color = "#000000";
			form.idUnidade.value = codigoRegistro;
			form.nomeUnidade.value = nomeRegistro;
			
			limparUnidadeSuperior();
		}
	}
	
	
	
	function replicaDataAtendimento() {
		var form = document.forms[0];
		form.dataAtendimentoFinal.value = form.dataAtendimentoInicial.value;
	}
	
	function habilitaEspecificacao() {
		var form = document.forms[0];
		if (form.selectedSolicitacaoTipoSize.value == '1') {
			form.especificacao.disabled = false;	
		} else {
			form.especificacao.disabled = true;	
		}
	}
	
	function limparEspecificacao() {
		var form = document.forms[0];

		for(i=form.especificacao.length-1; i>0; i--) {
			form.especificacao.options[i] = null;
		}
	}
	
	function limparUnidade() {
		var form = document.forms[0];
		
		form.idUnidade.value = "";
		form.nomeUnidade.value = "";
	}
	
	function limparUnidadeSuperior() {
		var form = document.forms[0];
		
		form.idUnidadeSuperior.value = "";
		form.nomeUnidadeSuperior.value = "";
	}
	
	function limparMunicipio() {
		var form = document.forms[0];
		
		form.idMunicipio.value = "";
		form.nomeMunicipio.value = "";
	}
	
	function limparBairro() {
		var form = document.forms[0];
		
		form.idBairro.value = "";
		form.nomeBairro.value = "";
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="window.focus();javascript:setarFoco('${requestScope.nomeCampo}');" >
    <div id="formDiv">   
<html:form 
	action="/gerarRelatorioGestaoSolicitacoesRAPorChefiaAction" method="post"
	name="GerarRelatorioGestaoSolicitacoesRAPorChefiaActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.GerarRelatorioGestaoSolicitacoesRAPorChefiaActionForm">

	<html:hidden property="selectedSolicitacaoTipoSize" />

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
					<td class="parabg">Filtrar Relatório Gestão de Registros de Atendimento</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">

				<tr>
					<td colspan="2">Para selecionar registros de atendimento para geração do relatório de gestão, informe os dados abaixo:</td>
				</tr>
				
				<tr>
					<td><strong>Situação dos Registros de Atendimento<font color="#FF0000">*</font></strong>
					</td>
					<td style="width: 440px;">
					<html:radio property="situacaoRA" value="" /> <strong>Todos</strong>
					<html:radio property="situacaoRA" value="1" /> <strong>Pendentes</strong>
					<html:radio property="situacaoRA" value="2" /> <strong>Encerrados</strong>
					<html:radio property="situacaoRA" value="3" /> <strong>Sem Local de Ocorrência</strong>
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
                	<td><strong>Tipo de Solicita&ccedil;&atilde;o:</strong></td>
	                <td colspan="6"><span class="style2"><strong>
						<html:select property="solicitacaoTipo" 
									 style="width: 350px; height:100px;" 
									 multiple="true" 
									 onchange="javascript:reload();habilitaEspecificacao();">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:present name="colecaoSolicitacaoTipo" scope="session">
								<html:options collection="colecaoSolicitacaoTipo" 
											  labelProperty="descricao" 
											  property="id" />
							</logic:present>
						</html:select>                
	                  </strong></span>
	                </td>
                </tr>
              <tr> 
                <td><strong>Especifica&ccedil;&atilde;o:</strong></td>
                <td colspan="6"><span class="style2"><strong> 
					<html:select property="especificacao" 
								 style="width: 350px;" 
								 multiple="true">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present name="colecaoEspecificacao" scope="session">
							<html:options collection="colecaoEspecificacao" 
										  labelProperty="descricao" 
										  property="id" />
						</logic:present>
					</html:select>                
                  </strong></span></td>
              </tr>

				
				<tr>
					<td height="10" colspan="3">
					<div align="right">
					<hr>
					</div>
					</td>
				</tr>
				
				<tr>
					<td><strong>Período de Atendimento:<font color="#FF0000">*</font></strong></td>
					<td colspan="6"><span class="style2"> <strong> 
					<html:text property="dataAtendimentoInicial" 
							   size="11" 
							   maxlength="10"
							   tabindex="3"
							   onkeyup="mascaraData(this, event);replicaDataAtendimento();" 
							   onkeypress="return isCampoNumerico(event);"/> 
						<a href="javascript:abrirCalendarioReplicando('GerarRelatorioGestaoSolicitacoesRAPorChefiaActionForm', 'dataAtendimentoInicial','dataAtendimentoFinal');">
							<img border="0"
								src="<bean:message key='caminho.imagens'/>calendario.gif"
								width="16" 
								height="15" 
								border="0" 
								title="Exibir Calendário"
								tabindex="4" />
						</a> a 
					<html:text property="dataAtendimentoFinal" 
							   size="11" 
							   maxlength="10"
							   tabindex="3" 
							   onkeypress="return isCampoNumerico(event);"
							   onkeyup="mascaraData(this, event)" /> 
						<a href="javascript:abrirCalendario('GerarRelatorioGestaoSolicitacoesRAPorChefiaActionForm', 'dataAtendimentoFinal');">
							<img border="0"
								 src="<bean:message key='caminho.imagens'/>calendario.gif"
								 width="16" 
								 height="15" 
								 border="0" 
								 title="Exibir Calendário"
								 tabindex="4" />
						</a> </strong>(dd/mm/aaaa)<strong> </strong> </span>
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
					<td><strong>Unidade Atual:</strong></td>
					<td>
						<html:text property="idUnidade" 
								   maxlength="4" 
								   size="4"
								   onkeypress="limparUnidadeSuperior(); validaEnterComMensagem(event, 'exibirGerarRelatorioGestaoSolicitacoesRAPorChefiaAction.do?objetoConsulta=1','idUnidade','Unidade Organizacional');return isCampoNumerico(event);" />
							<a href="javascript: chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do', 'idUnidade', null, null, 275, 480, '', document.forms[0].idUnidadeSuperior);">
								<img width="23" 
									 height="21" 
									 border="0" 
									 src="<bean:message key="caminho.imagens"/>pesquisa.gif" 
									 title="Pesquisar Unidade Organizacional" />
							</a> 
						
						<logic:present name="unidadeEncontrada" scope="session">
							<html:text  property="nomeUnidade" 
										size="40" 
										maxlength="40" 
										readonly="true" 
										style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present>
						
						<logic:notPresent name="unidadeEncontrada" scope="session">
							<html:text  property="nomeUnidade" 
										size="40" 
										maxlength="40" 
										readonly="true" 
										style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
						
						<a href="javascript:limparUnidade();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" 
								 border="0" 
								 title="Apagar" /> 
						</a> 
					</td>
				</tr>
				
				<tr>
					<td><strong>Unidade Superior:</strong></td>
					<td>
						<html:text property="idUnidadeSuperior" 
								   maxlength="4" 
								   size="4"
								   onkeypress="limparUnidade(); validaEnterComMensagem(event, 'exibirGerarRelatorioGestaoSolicitacoesRAPorChefiaAction.do?objetoConsulta=2','idUnidadeSuperior','Unidade Superior');return isCampoNumerico(event);" />
							<a href="javascript: chamarPopup('exibirPesquisarUnidadeSuperiorAction.do', 'idUnidadeSuperior', null, null, 275, 480, '', document.forms[0].idUnidadeSuperior);">
								<img width="23" 
									 height="21" 
									 border="0" 
									 src="<bean:message key="caminho.imagens"/>pesquisa.gif" 
									 title="Pesquisar Unidade Superior" />
							</a> 
						
							<logic:present name="unidadeSuperiorEncontrada" scope="session">
								<html:text property="nomeUnidadeSuperior" 
										   size="40" 
										   maxlength="40" 
										   readonly="true" 
										   style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:present>
							
							<logic:notPresent name="unidadeSuperiorEncontrada" scope="session">
								<html:text property="nomeUnidadeSuperior" 
										   size="40" 
										   maxlength="40" 
										   readonly="true" 
										   style="background-color:#EFEFEF; border:0; color: red" />
							</logic:notPresent>
						
						<a href="javascript:limparUnidadeSuperior();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" 
								 border="0" 
								 title="Apagar" /> 
						</a> 
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
					<td><strong>Município:</strong></td>
					<td>
						<html:text property="idMunicipio" 
								   maxlength="4" 
								   size="4"
								   onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioGestaoSolicitacoesRAPorChefiaAction.do?objetoConsulta=3','idMunicipio','Municipio');return isCampoNumerico(event);" />
							<a href="javascript: chamarPopup('exibirPesquisarMunicipioAction.do', 'idMunicipio', null, null, 275, 480, '', document.forms[0].idMunicipio);">
								<img width="23" 
									 height="21" 
									 border="0" 
									 src="<bean:message key="caminho.imagens"/>pesquisa.gif" 
									 title="Pesquisar Municipio" />
							</a> 
						
						<logic:present name="municipioEncontrado" scope="session">
							<html:text property="nomeMunicipio" 
									   size="40" 
									   maxlength="40" 
									   readonly="true" 
									   style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present>
						
						<logic:notPresent name="municipioEncontrado" scope="session">
							<html:text  property="nomeMunicipio" 
										size="40" 
										maxlength="40" 
										readonly="true" 
										style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
						
						<a href="javascript:limparMunicipio();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" 
								 border="0" 
								 title="Apagar" /> 
						</a> 
					</td>
				</tr>
				
				<tr>
					<td><strong>Bairro:</strong></td>
					<td>
						<html:text  property="idBairro" 
									maxlength="4" 
									size="4"
									onkeypress="validaEnterDependencia(event, 'exibirGerarRelatorioGestaoSolicitacoesRAPorChefiaAction.do?objetoConsulta=4', this, document.forms[0].idMunicipio.value, 'Município');return isCampoNumerico(event);" />
							<a href="javascript: abrirPopup('exibirPesquisarBairroAction.do?idMunicipio='+document.forms[0].idMunicipio.value+'&indicadorUsoTodos=1', 400, 800);">
								<img width="23" 
									 height="21" 
									 border="0" 
									 src="<bean:message key="caminho.imagens"/>pesquisa.gif" 
									 title="Pesquisar Bairro" />
							</a> 
						
						<logic:present name="bairroEncontrado" scope="session">
							<html:text property="nomeBairro" size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present>
						
						<logic:notPresent name="bairroEncontrado" scope="session">
							<html:text property="nomeBairro" size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
						
						<a href="javascript:limparBairro();">
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
						onclick="window.location.href='/gsan/exibirGerarRelatorioGestaoSolicitacoesRAPorChefiaAction.do?menu=sim';" />
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
		page="/jsp/relatorio/escolher_tipo_relatorio_tela_espera.jsp?relatorio=gerarRelatorioGestaoSolicitacoesRAPorChefiaAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
</body>
</html:html>
