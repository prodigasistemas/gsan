<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<html:javascript staticJavascript="false" formName="InformarDadosConsultaNegativacaoActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script>
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg, objetoRelacionado) {
		if (objetoRelacionado.readOnly != true) {
			if (objeto == null || codigoObjeto == null) {
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			} else {
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)) {
					alert(msg);
				} else {
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}

	function habilitarPesquisaEloPolo(form) {
		form.tipoPesquisa.value = 'eloPolo';
		chamarPopup('exibirPesquisarLocalidadeAction.do', 'localidade', null, null, 275, 480, '', form.idEloPolo.value);
	}

	function habilitarPesquisaLocalidade(form) {
		form.tipoPesquisa.value = 'localidade';
		chamarPopup('exibirPesquisarLocalidadeAction.do', 'localidade', null, null, 275, 480, '', form.idLocalidade.value);
	}

	function habilitarPesquisaQuadra(form) {
		if (form.idSetorComercial.value != '') {
			chamarPopup('exibirPesquisarQuadraAction.do', 'quadra', null, null,	275, 480, '', form.idQuadra.value);
		} else {
			alert('Informar Setor Comercial');
		}
	}

	function habilitarPesquisaSetorComercial(form) {
		if (form.idLocalidade.value != '') {
			abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade=' + form.idLocalidade.value + '&tipo=SetorComercial&indicadorUsoTodos=1', form.idLocalidade.value, 'Localidade', 400, 800);
		} else {
			alert("Informar a localidade!");
		}

	}

	function habilitarPesquisaTituloComando(form) {
		chamarPopup('exibirPesquisarComandoNegativacaoAction.do?menu=ok', 'comandoNegativacao', null, null, 420, 650, '', form.idSetorComercial.value);
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
		if (tipoConsulta == 'localidade') {
			if (form.tipoPesquisa.value == 'eloPolo') {
				form.idEloPolo.value = codigoRegistro;
				form.descricaoEloPolo.value = descricaoRegistro;
			}
			
			if (form.tipoPesquisa.value == 'localidade') {
				form.idLocalidade.value = codigoRegistro;
				form.descricaoLocalidade.value = descricaoRegistro;
			}
		}
		
		if (tipoConsulta == 'quadra') {
			form.idQuadra.value = codigoRegistro;
			form.descricaoQuadra.value = descricaoRegistro;

		}
		
		if (tipoConsulta == 'setorComercial') {
			form.idSetorComercial.value = codigoRegistro;
			form.descricaoSetorComercial.value = descricaoRegistro;
		}
		
		if (tipoConsulta == 'comandoNegativacao') {
			form.idNegativacaoComando.value = codigoRegistro;
			form.tituloComando.value = descricaoRegistro;
		}
	}

	function validaEnterEloPolo(tecla, caminhoActionReload, nomeCampo) {
		var form = document.forms[0];
		validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código do Elo Pólo");
	}

	function validaEnterLocalidade(tecla, caminhoActionReload, nomeCampo) {
		var form = document.forms[0];
		validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código da Localidade");
	}

	function validaEnterQuadra(tecla, caminhoActionReload, nomeCampo) {
		var form = document.forms[0];
		validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código da Quadra");
	}

	function validaEnterSetorComercial(tecla, caminhoActionReload, nomeCampo) {
		var form = document.forms[0];
		validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código da Setor Comercial");
	}

	function limparForm(tipo) {
		var form = document.forms[0];
		if (tipo == 'eloPolo') {
			form.idEloPolo.value = "";
			form.descricaoEloPolo.value = "";
			form.setOkEloPolo.value = "true";
		}
		
		if (tipo == 'localidade') {
			form.idLocalidade.value = "";
			form.descricaoLocalidade.value = "";
			verificaLocalidade();
			form.setOkLocalidade.value = "true";

		}
		
		if (tipo == 'quadra') {
			form.idQuadra.value = "";
			form.descricaoQuadra.value = "";
			form.setOkQuadra.value = "true";
		}
		
		if (tipo == 'setorComercial') {
			form.idSetorComercial.value = "";
			form.descricaoSetorComercial.value = "";
			verificaSetorComercial();
			form.setOkSetorComercial.value = "true";

		}
		
		if (tipo == 'tituloComando') {
			form.tituloComando.value = "";
			form.idComandoNegativacao.value = "";

		}
	}

	function validateInformarDadosConsultaNegativacaoActionForm() {
		var form = document.forms[0];

		var algumNegativadorSelecionado = false;

		if (validateDate(form)) {
			for (var x = 0; x < form.arrayNegativador.length && algumNegativadorSelecionado == false; x++) {
				if (form.arrayNegativador[x].selected == true && form.arrayNegativador[x].value != '-1') {
					algumNegativadorSelecionado = true;
				}
			}

			var tipoConsultaSelecionado = false;
			if (form.tipoConsulta.value == 1 || form.tipoConsulta.value == 2 || form.tipoConsulta.value == 3) {
				tipoConsultaSelecionado = true;
			}

			var tipoRelatorioSelecionado = false;
			if (form.tipoRelatorioNegativacao.value == 1 || form.tipoRelatorioNegativacao.value == 2 || form.tipoRelatorioNegativacao.value == 3) {
				tipoRelatorioSelecionado = true;
			}

			if (algumNegativadorSelecionado && tipoConsultaSelecionado && tipoRelatorioSelecionado) {
				form.action = 'informarDadosConsultaNegativacaoAction.do';
				form.submit();
			} else {
				if (!algumNegativadorSelecionado) {
					alert("Selecione um ou mais Negativadores.");
				} else if (!tipoConsultaSelecionado) {
					alert("Selecione o Tipo de Consulta.");
				} else if (!tipoRelatorioSelecionado) {
					alert("Selecione o Tipo de Relatório.");
				}

				return false;
			}
		}
	}

	function DateValidations() {
		this.aa = new Array("periodoEnvioNegativacaoInicio", "Data Inicial do Período de Negativação inválida.", new Function("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
		this.ab = new Array("periodoEnvioNegativacaoFim", "Período deEnvio da Negativação Final não é uma data válida.", new Function("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
	}

	function required() {
		this.aa = new Array("idNegativador", "Informe o Negativador.", new Function("varName", " return this[varName];"));
	}

	function verificaLocalidade() {
		var form = document.forms[0];

		if (form.idLocalidade.value == '') {
			form.idSetorComercial.value = "";
			form.descricaoSetorComercial.value = "";
			form.idQuadra.value = "";
			form.idSetorComercial.disabled = true;
			form.idQuadra.disabled = true;
		} else {
			form.idSetorComercial.disabled = false;
		}
	}

	function verificaSetorComercial() {
		var form = document.forms[0];

		if (form.idSetorComercial.value == '') {
			form.idQuadra.value = "";
			form.idQuadra.disabled = true;
		} else {
			form.idQuadra.disabled = false;
		}
	}

	function submeter() {
		var form = document.forms[0];
		form.action = 'exibirInformarDadosConsultaNegativacaoAction.do';
		form.submit();
	}
</script>
</head>

<body leftmargin="5" topmargin="5" onload="verificaLocalidade(); verificaSetorComercial();">
	<div id="formDiv">
		<html:form action="/informarDadosConsultaNegativacaoAction" name="InformarDadosConsultaNegativacaoActionForm" type="gcom.gui.cobranca.spcserasa.InformarDadosConsultaNegativacaoActionForm" method="post">

			<%@ include file="/jsp/util/cabecalho.jsp"%>
			<%@ include file="/jsp/util/menu.jsp"%>

			<input type="hidden" name="tipoPesquisa" />
			<html:hidden property="idNegativacaoComando" />
			<html:hidden property="okEloPolo" />
			<html:hidden property="okLocalidade" />
			<html:hidden property="okSetorComercial" />
			<html:hidden property="okQuadra" />



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
					
					<td width="655" valign="top" class="centercoltext">
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td width="11"><img border="0" src="<bean:message key='caminho.imagens'/>parahead_left.gif" /></td>
								<td class="parabg">Consulta/Relatório do Resumo da Negativação</td>
								<td width="11" valign="top"><img border="0" src="<bean:message key='caminho.imagens'/>parahead_right.gif" /></td>
							</tr>
						</table>
						
						<p>&nbsp;</p>
						
						<table width="100%" border="0">
							<tr>
								<td colspan="2">Para gerar o Relat&oacute;rio/Consulta do Resumo da Negativa&ccedil;&atilde;o, informe os dados abaixo:</td>
							</tr>

							<tr><td><p>&nbsp;</p></td></tr>

							<tr>
								<td width="45%"><strong>Tipo de Relatório:<font	color="#FF0000">*</font></strong></td>
								<td width="55%">
									<html:radio property="tipoRelatorioNegativacao"	tabindex="1" value="<%=ConstantesSistema.SIM.toString()%>" />Analítico
									<html:radio property="tipoRelatorioNegativacao" tabindex="2" value="<%=ConstantesSistema.NAO.toString()%>" />Sintético
								</td>
							</tr>
							
							<tr><td><p>&nbsp;</p></td></tr>
							
							<tr>
								<td width="45%"><strong>Tipo de Consulta:<font color="#FF0000">*</font></strong></td>
								<td width="55%">
									<html:radio property="tipoConsulta" tabindex="4" value="1" onchange="submeter();" />Relatório de Acompanhamento de Negativações<p>&nbsp;</p>
									<html:radio property="tipoConsulta" tabindex="5" value="2" onchange="submeter();" />Relatório de Exclusão de Negativações<p>&nbsp;</p>
									<html:radio property="tipoConsulta" tabindex="6" value="3" onchange="submeter();" />Resumo da Negativação<p>&nbsp;</p>
								</td>
							</tr>

							<tr><td><p>&nbsp;</p></td></tr>
							
							<tr><td colspan="5"><hr></td></tr>

							<tr>
								<td width="45%"><strong>Negativador:<font color="#FF0000">*</font></strong></td>
								<td width="55%">
									<logic:present name="collNegativador">
										<html:select property="arrayNegativador" multiple="true" tabindex="3" onmouseup="submeter();">
											<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
											<logic:present name="collNegativador">
												<html:options collection="collNegativador" labelProperty="cliente.nome" property="id" />
											</logic:present>
										</html:select>
									</logic:present>
								</td>
							</tr>

							<tr><td><p>&nbsp;</p></td></tr>

							<tr>
								<td width="45%"><strong>Per&iacute;odo do Envio da Negativa&ccedil;&atilde;o:<font color="#FF0000"></font></strong></td>
								<td width="55%">
									<html:text property="periodoEnvioNegativacaoInicio" size="10" maxlength="10" tabindex="7" onkeyup="mascaraData(this, event);" />
									<a href="javascript:abrirCalendarioReplicando('InformarDadosConsultaNegativacaoActionForm', 'periodoEnvioNegativacaoInicio', 'periodoEnvioNegativacaoFim');">
										<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário" />
									</a> a 
									<html:text property="periodoEnvioNegativacaoFim" size="10" maxlength="10" tabindex="8" onkeyup="mascaraData(this, event);" />
									<a href="javascript:abrirCalendario('InformarDadosConsultaNegativacaoActionForm', 'periodoEnvioNegativacaoFim')">
										<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário" />
									</a> dd/mm/aaaa
								</td>
							</tr>

							<logic:equal name="InformarDadosConsultaNegativacaoActionForm" property="indicadorRelExclusao" value="sim">
								<tr>
									<td width="45%"><strong>Período da Exclusão da Negativação:<font color="#FF0000"></font></strong></td>
									<td width="55%">
										<html:text property="periodoExclusaoNegativacaoInicio" size="10" maxlength="10" tabindex="9" onkeyup="mascaraData(this, event);" />
										<a href="javascript:abrirCalendarioReplicando('InformarDadosConsultaNegativacaoActionForm', 'periodoExclusaoNegativacaoInicio', 'periodoExclusaoNegativacaoFim');">
											<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário" />
										</a> a 
										<html:text property="periodoExclusaoNegativacaoFim" size="10" maxlength="10" tabindex="3" onkeyup="mascaraData(this, event);" />
										<a href="javascript:abrirCalendario('InformarDadosConsultaNegativacaoActionForm', 'periodoExclusaoNegativacaoFim')">
											<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário" />
										</a> dd/mm/aaaa
									</td>
								</tr>
	
								<tr>
									<td width="45%"><strong>Motivo da Exclusão da Negativação:</strong></td>
									<td width="55%"><html:select property="idNegativadorExclusaoMotivo" tabindex="11">
											<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
											<logic:present name="collNegativadorExclusaoMotivo">
												<html:options collection="collNegativadorExclusaoMotivo" labelProperty="descricaoExclusaoMotivo" property="id" />
											</logic:present>
										</html:select></td>
								</tr>
							</logic:equal>

							<tr>
								<td width="45%"><strong>T&iacute;tulo do Comando:</strong></td>
								<td width="55%">
									<strong><b><span class="style2">
									<html:textarea property="tituloComando" cols="32" rows="2" readonly="true" tabindex="12"></html:textarea>
									</span></b></strong>
									<a href="javascript:habilitarPesquisaTituloComando(document.forms[0]);" alt="Pesquisar Titulo Comando">
										<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" border="0">
									</a>
									<a href="javascript:limparForm('tituloComando');">
										<img src="<bean:message key='caminho.imagens'/>limparcampo.gif" width="23" height="21" border="0">
									</a>
								</td>
							</tr>

							<tr><td colspan="5"><hr></td></tr>

							<tr>
								<td width="45%"><strong>Grupo de Cobran&ccedil;a:</strong></td>
								<td width="55%">
									<logic:present name="collCobrancaGrupo">
										<html:select property="arrayCobrancaGrupo" tabindex="13" multiple="true" size="4">
											<html:option value="">&nbsp;</html:option>
											<logic:iterate name="collCobrancaGrupo" id="cobrancaGrupo">
												<option value="<bean:write name="cobrancaGrupo" property="id" />">
													<bean:write name="cobrancaGrupo" property="descricao" />
												</option>
											</logic:iterate>
										</html:select>
									</logic:present>
								</td>
							</tr>

							<tr><td colspan="5"><hr></td></tr>

							<tr>
								<td width="45%"><strong>Ger&ecirc;ncia Regional: </strong></td>
								<td width="55%">
									<logic:present name="collGerenciaRegional">
										<html:select property="arrayGerenciaRegional" tabindex="14" multiple="true" size="4">
											<html:option value="">&nbsp;</html:option>
											<logic:iterate name="collGerenciaRegional" id="gerenciaRegional">
												<option value="<bean:write name="gerenciaRegional" property="id" />">
													<bean:write name="gerenciaRegional" property="nome" />
												</option>
											</logic:iterate>
										</html:select>
									</logic:present>
								</td>
							</tr>

							<tr><td colspan="5"><hr></td></tr>

							<tr>
								<td width="45%"><strong>Unidade de Neg&oacute;cio:</strong></td>
								<td width="55%">
									<logic:present name="collUnidadeNegocio">
										<html:select property="arrayUnidadeNegocio" tabindex="15" multiple="true" size="4">
											<html:option value="">&nbsp;</html:option>
											<logic:iterate name="collUnidadeNegocio" id="unidadeNegocio">
												<option value="<bean:write name="unidadeNegocio" property="id" />">
													<bean:write name="unidadeNegocio" property="nome" />
												</option>
											</logic:iterate>
										</html:select>
									</logic:present>
								</td>
							</tr>

							<tr><td colspan="5"><hr></td></tr>

							<tr>
								<td width="45%"><strong>Localidade Pólo:</strong></td>
								<td width="55%">
									<html:text property="idEloPolo" maxlength="3" size="3" tabindex="16" onkeyup="return validaEnterEloPolo(event, 'exibirInformarDadosConsultaNegativacaoAction.do', 'idEloPolo');"/>
									<a href="javascript:habilitarPesquisaEloPolo(document.forms[0]);" alt="Pesquisar Elo Polo">
										<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" />
									</a>
									
									<logic:present name="corEloPolo">
										<logic:equal name="corEloPolo" value="exception">
											<html:text property="descricaoEloPolo" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:equal>
										<logic:notEqual name="corEloPolo" value="exception">
											<html:text property="descricaoEloPolo" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEqual>
									</logic:present>
									
									<logic:notPresent name="corEloPolo">
										<logic:empty name="InformarDadosConsultaNegativacaoActionForm" property="idEloPolo">
											<html:text property="descricaoEloPolo" size="30" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:empty>
										<logic:notEmpty name="InformarDadosConsultaNegativacaoActionForm" property="idEloPolo">
											<html:text property="descricaoEloPolo" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEmpty>
									</logic:notPresent>
									
									<a href="javascript:limparForm('eloPolo');">
										<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" />
									</a>
								</td>
							</tr>
							
							<tr>
								<td width="45%"><strong>Localidade:</strong></td>
								<td width="55%">
									<html:text property="idLocalidade" maxlength="3" tabindex="17" size="3" onkeyup="return validaEnterLocalidade(event, 'exibirInformarDadosConsultaNegativacaoAction.do', 'idLocalidade'); " />
									<a href="javascript:habilitarPesquisaLocalidade(document.forms[0]);" alt="Pesquisar Localidade">
										<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" />
									</a>
									
									<logic:present name="corLocalidade">
										<logic:equal name="corLocalidade" value="exception">
											<html:text property="descricaoLocalidade" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:equal>
										<logic:notEqual name="corLocalidade" value="exception">
											<html:text property="descricaoLocalidade" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEqual>
									</logic:present>
									
									<logic:notPresent name="corLocalidade">
										<logic:empty name="InformarDadosConsultaNegativacaoActionForm" property="idLocalidade">
											<html:text property="descricaoLocalidade" size="30" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:empty>
										<logic:notEmpty name="InformarDadosConsultaNegativacaoActionForm" property="idLocalidade">
											<html:text property="descricaoLocalidade" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEmpty>
									</logic:notPresent>
									
									<a href="javascript:limparForm('localidade');">
										<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" />
									</a>
								</td>
							</tr>
							
							<tr>
								<td width="45%"><strong>Setor Comercial:</strong></td>
								<td width="55%">
									<html:text property="idSetorComercial" maxlength="3" tabindex="18" size="3" onkeyup="return validaEnterSetorComercial(event, 'exibirInformarDadosConsultaNegativacaoAction.do', 'idSetorComercial'); " />
									<a href="javascript:habilitarPesquisaSetorComercial(document.forms[0]);" alt="Pesquisar Setor Comercial">
										<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" />
									</a>
									
									<logic:present name="corSetorComercial">
										<logic:equal name="corSetorComercial" value="exception">
											<html:text property="descricaoSetorComercial" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:equal>
										<logic:notEqual name="corSetorComercial" value="exception">
											<html:text property="descricaoSetorComercial" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEqual>
									</logic:present>
									
									<logic:notPresent name="corSetorComercial">
										<logic:empty name="InformarDadosConsultaNegativacaoActionForm" property="idSetorComercial">
											<html:text property="descricaoSetorComercial" size="30" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:empty>
										<logic:notEmpty name="InformarDadosConsultaNegativacaoActionForm" property="idSetorComercial">
											<html:text property="descricaoSetorComercial" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEmpty>
									</logic:notPresent>
									
									<a href="javascript:limparForm('setorComercial');">
										<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" />
									</a>
								</td>
							</tr>

							<tr>
								<td width="45%"><strong>Quadra:</strong></td>
								<td width="55%">
									<html:text property="idQuadra" maxlength="4" size="4" tabindex="19" onkeyup="return validaEnterQuadra(event, 'exibirInformarDadosConsultaNegativacaoAction.do', 'idQuadra'); " />
									<logic:present name="codigoQuadraNaoEncontrada" scope="request">
										<span style="color: #ff0000" id="msgQuadra">
											<bean:write scope="request" name="msgQuadra" />
										</span>
									</logic:present>
									
									<logic:notPresent name="codigoQuadraNaoEncontrada" scope="request"> </logic:notPresent>
								</td>
							</tr>

							<tr><td colspan="5"><hr></td></tr>

							<tr>
								<td width="45%"><strong>Perfil do Im&oacute;vel:</strong></td>
								<td width="55%">
									<logic:present name="collImovelPerfil">
										<html:select property="arrayImovelPerfil" tabindex="20" multiple="true" size="4">
											<html:option value="">&nbsp;</html:option>
											<logic:iterate name="collImovelPerfil" id="imovelPerfil">
												<option value="<bean:write name="imovelPerfil" property="id"/>">
													<bean:write name="imovelPerfil" property="descricao"/>
												</option>
											</logic:iterate>
										</html:select>
									</logic:present>
								</td>
							</tr>

							<tr><td colspan="5"><hr></td></tr>

							<tr>
								<td width="45%"><strong>Categoria:</strong></td>
								<td width="55%">
									<logic:present name="collCategoria">
										<html:select property="arrayCategoria" tabindex="21" multiple="true" size="4">
											<html:option value="">&nbsp;</html:option>
											<logic:iterate name="collCategoria" id="categoria">
												<option value="<bean:write name="categoria" property="id"/>">
													<bean:write name="categoria" property="descricao"/>
												</option>
											</logic:iterate>
										</html:select>
									</logic:present>
								</td>
							</tr>

							<tr><td colspan="5"><hr></td></tr>

							<tr>
								<td width="45%"><strong>Tipo de Cliente:</strong></td>
								<td width="55%">
									<logic:present name="collClienteTipo">
										<html:select property="arrayTipoCliente" tabindex="22" multiple="true" size="4">
											<html:option value="">&nbsp;</html:option>
											<logic:iterate name="collClienteTipo" id="clienteTipo">
												<option value="<bean:write name="clienteTipo" property="id" />">
													<bean:write name="clienteTipo" property="descricao" />
												</option>
											</logic:iterate>
										</html:select>
									</logic:present>
								</td>
							</tr>

							<tr><td colspan="5"><hr></td></tr>

							<tr>
								<td width="45%"><strong>Esfera de Poder:</strong></td>
								<td width="55%">
									<logic:present name="collEsferaPoder">
										<html:select property="arrayEsferaPoder" tabindex="23" multiple="true" size="4">
											<html:option value="">&nbsp;</html:option>
											<logic:iterate name="collEsferaPoder" id="esferaPoder">
												<option value="<bean:write name="esferaPoder" property="id" />">
													<bean:write name="esferaPoder" property="descricao" />
												</option>
											</logic:iterate>
										</html:select>
									</logic:present>
								</td>
							</tr>

							<tr><td colspan="5"><hr></td></tr>

							<tr>
								<td width="45%"><strong>Situa&ccedil;&atilde;o Liga&ccedil;&atilde;o de &Aacute;gua:</strong></td>
								<td width="55%" align="left">
									<logic:present name="collLigacaoAguaSituacao">
										<html:select property="arrayLigacaoAguaSituacao" tabindex="24" multiple="true" size="4">
											<html:option value="">&nbsp;</html:option>
											<logic:iterate name="collLigacaoAguaSituacao" id="ligacaoAguaSituacao">
												<option value="<bean:write name="ligacaoAguaSituacao" property="id" />">
													<bean:write name="ligacaoAguaSituacao" property="descricao" />
												</option>
											</logic:iterate>
										</html:select>
									</logic:present>
								</td>
							</tr>

							<tr><td colspan="5"><hr></td></tr>

							<tr>
								<td width="45%"><strong>Situa&ccedil;&atilde;o Liga&ccedil;&atilde;o de Esgoto:</strong></td>
								<td width="55%" align="left">
									<logic:present name="collLigacaoEsgotoSituacao">
										<html:select property="arrayLigacaoEsgotoSituacao" tabindex="25" multiple="true" size="4">
											<html:option value="">&nbsp;</html:option>
											<logic:iterate name="collLigacaoEsgotoSituacao" id="ligacaoEsgotoSituacao">
												<option value="<bean:write name="ligacaoEsgotoSituacao" property="id" />">
													<bean:write name="ligacaoEsgotoSituacao" property="descricao" />
												</option>
											</logic:iterate>
										</html:select>
									</logic:present>
								</td>
							</tr>

							<tr><td colspan="5"><hr></td></tr>

							<logic:equal name="InformarDadosConsultaNegativacaoActionForm" property="indicadorRelAcompanhamentoClientesNegativados" value="sim">
								<tr>
									<td width="45%"><strong>Apenas Negativações Rejeitadas?</strong></td>
									<td width="55%">
										<html:radio property="indicadorApenasNegativacoesRejeitadas" tabindex="26" value="<%=ConstantesSistema.SIM.toString()%>" />Sim
										<html:radio property="indicadorApenasNegativacoesRejeitadas" tabindex="27" value="<%=ConstantesSistema.NAO.toString()%>" />Não
									</td>
									<td>&nbsp;</td>
								</tr>
								
								<logic:present name="collMotivoRejeicao">
									<tr>
										<td width="45%"><strong>Motivos de Rejeição:</strong></td>
										<td width="55%">
											<html:select property="arrayMotivoRejeicao" tabindex="28" multiple="true" size="4" style="width:350px;">
												<html:option value="">&nbsp;</html:option>
												<logic:iterate name="collMotivoRejeicao" id="motivoRejeicao">
													<option value="<bean:write name="motivoRejeicao" property="id" />">
														<bean:write name="motivoRejeicao" property="descricaoRetornocodigo" />
													</option>
												</logic:iterate>
											</html:select>
										</td>
									</tr>
								</logic:present>
							</logic:equal>

							<tr>
								<td><p>&nbsp;</p></td>
							</tr>

							<tr>
								<td align="left"><font color="#FF0000">*</font> Campo Obrigat&oacute;rio</td>
							</tr>

							<tr>
								<td><p>&nbsp;</p></td>
							</tr>
						</table>

						<table width="100%">
							<tr>
								<td>&nbsp;</td>
								<td align="right">
									<input type="button" name="botaoInserir" class="bottonRightCol" value="Gerar Consulta" onclick="javascript:validateInformarDadosConsultaNegativacaoActionForm();" />
								</td>
							</tr>
						</table>

						<p>&nbsp;</p>
				</tr>
				<tr>
					<td colspan="3"></td>
				</tr>
			</table>

			<%@ include file="/jsp/util/rodape.jsp"%>
		</html:form>
	</div>
</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

</html:html>