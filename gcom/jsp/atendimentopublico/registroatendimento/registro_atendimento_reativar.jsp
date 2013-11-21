<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

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


<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ReativarRegistroAtendimentoActionForm" />

<script language="JavaScript">

  function validaForm() {
    var form = document.forms[0];
	  if(validateReativarRegistroAtendimentoActionForm(form)){		     
		  submeterFormPadrao(form);   		  
   	  }
  }
  
  function desfazer(){
    var form = document.forms[0];
    form.especificacaoId.value = "";
    form.idUnidadeAtendimento.value = "";  
    form.unidadeAtendimento.value = "";
    form.idUnidadeDestino.value = "";  
    form.unidadeDestino.value = "";    
    form.tipoAtendimento[0].checked = false;
	form.tipoAtendimento[1].checked = false;	
    form.dataAtendimentoReativado.value = "";
    form.horaAtendimentoReativado.value = "";
    form.tempoEsperaInicial.value = "";
    form.tempoEsperaFinal.value = "";
    form.dataPrevistaReativado.value = "";
    form.meioSolicitacao.selectedIndex = 0;
    form.motivoReativacao.selectedIndex = 0;
    form.parecerUnidadeDestino.value = "";
    form.observacao.value = "";
    redirecionarSubmit('exibirReativarRegistroAtendimentoAction.do');
  }
	
  function extendeTabela(tabela,display){
		var form = document.forms[0];

		if(display){
 			eval('layerHide'+tabela).style.display = 'none';
 			eval('layerShow'+tabela).style.display = 'block';
		}else{
			eval('layerHide'+tabela).style.display = 'block';
 			eval('layerShow'+tabela).style.display = 'none';
		}
	}
	
	function limparUnidadeAtendimento() {
		var form = document.forms[0];
        form.idUnidadeAtendimento.value = "";  
        form.unidadeAtendimento.value = "";
	 }  
	 
	 function limparUnidadeDestino() {
		var form = document.forms[0];
        form.idUnidadeDestino.value = "";  
        form.unidadeDestino.value = "";
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

  	var unidade = 0;
	
	function setUnidade(tipo) {
		unidade = tipo;
	}
	
	/* Recuperar Popup */
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];

          if (tipoConsulta == 'unidadeOrganizacional') {
	        if (unidade == 1) {
	          limparUnidadeAtendimento();
		      form.idUnidadeAtendimento.value = codigoRegistro;
		      form.unidadeAtendimento.value = descricaoRegistro;
		      form.unidadeAtendimento.style.color = "#000000";
	        } else if (unidade == 2) {
	          limparUnidadeDestino();
		      form.idUnidadeDestino.value = codigoRegistro;
		      form.unidadeDestino.value = descricaoRegistro;
		      form.unidadeDestino.style.color = "#000000";
	        }
	      unidade = 0;	    
	    }
      }
      
  	function carregarTempoEsperaFinal(){
		
		var form = document.forms[0];
		
		if (form.tempoEsperaInicial.value.length > 0 && form.tempoEsperaFinal.readOnly == true){
			redirecionarSubmit('exibirReativarRegistroAtendimentoAction.do?tempoEsperaFinalDesabilitado=OK');
		}
	}

  	function textAreaMaxLength(maxlength){
		var form = document.forms[0];
		if(form.parecerTramite.value.length >= maxlength){
			window.event.keyCode = '';
		}
	}
	
  function calcularDataPrevista(){
  	var form = document.forms[0];
	
	if (form.dataAtendimentoReativado.value.length > 0){
	  if(verificaDataMensagemPersonalizada(form.dataAtendimentoReativado, "Data de Atendimento Inválida")){
	    redirecionarSubmit('exibirReativarRegistroAtendimentoAction.do?definirDataPrevista=OK');  
	  }
	}
  }
  
  function limpaTempoFinal(){
  	var form = document.forms[0];
  	
  	form.tempoEsperaFinal.value = "";
  }
  
  function gerarURLGerarOrdemServico(idServicoTipo){

	var form = document.forms[0];
	
	var idImovel = form.matriculaImovel.value;
	var idTipoSolicitacao = form.tipoSolicitacaoId.value;
	
	if (idImovel.length > 0){
		abrirPopup('/gsan/exibirGerarOrdemServicoInserirRAAction.do?idServicoTipo=' + idServicoTipo + "&idImovel=" + idImovel+"&idTipoSolicitacao="+idTipoSolicitacao , 420, 760);
	}
	else{
		abrirPopup('/gsan/exibirGerarOrdemServicoInserirRAAction.do?idServicoTipo=' + idServicoTipo+"&idTipoSolicitacao="+idTipoSolicitacao, 420, 760);
	}
	
  }
  
  	/* Consultar Registro de Atendimento */	
	function consultarRA() {
		var form = document.forms[0];
		form.action = 'exibirConsultarRegistroAtendimentoAction.do?numeroRA='+form.numeroRA.value;
		form.submit();
	}
  
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');limitTextArea(document.forms[0].observacao, 200, document.getElementById('utilizado'), document.getElementById('limite'));">

<html:form action="/reativarRegistroAtendimentoAction.do"
	name="ReativarRegistroAtendimentoActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.ReativarRegistroAtendimentoActionForm"
	method="post"
	onsubmit="return validateReativarRegistroAtendimentoActionForm(this);">

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

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Reativar Registro de Atendimento</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>

			<table width="100%">
					<tr>
							<td width="90%" colspan="2">&nbsp;</td>
						<logic:present scope="application" name="urlHelp">
							<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}atendimentoRegistroReativar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
						</logic:present>
						<logic:notPresent scope="application" name="urlHelp">
							<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
						</logic:notPresent>
					</tr>
		  </table>
			
			<!--Inicio da Tabela Dados Gerais do Registro de Atendimento -->
			<table width="100%" border="0">

				<tr>
					<td height="31">
					<table width="100%" border="0" align="center">
						<tr bgcolor="#cbe5fe">
							<td align="center">

							<div id="layerHideRegistroAtendimento" style="display:block">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td align="center"><span class="style2"> <a
										href="javascript:extendeTabela('RegistroAtendimento',true);" />
									<b>Dados Gerais do Registro de Atendimento</b> </a> </span></td>
								</tr>
							</table>
							</div>

							<div id="layerShowRegistroAtendimento" style="display:none">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td align="center"><span class="style2"> <a
										href="javascript:extendeTabela('RegistroAtendimento',false);" />
									<b>Dados do Registro de Atendimento</b> </a> </span></td>
								</tr>

								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">
										<tr>
											<td colspan="2">
											<table width="100%">
												<tr>
													<td height="10" width="27%"><strong>N&uacute;mero do RA:</strong></td>
													<td><html:text property="numeroRA" readonly="true"
														style="background-color:#EFEFEF; border:0;" size="9"
														maxlength="9" /> <strong>Situação do RA:</strong> <html:text
														property="situacaoRA" readonly="true"
														style="background-color:#EFEFEF; border:0;" size="15"
														maxlength="15" /></td>
												</tr>
											</table>
											</td>
										</tr>
										<c:if
											test="${ReativarRegistroAtendimentoActionForm.numeroRaAssociado != null}">
											<tr>
												<td height="10"><strong>N&uacute;mero do RA Associado:</strong></td>
												<td><html:text property="numeroRaAssociado" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="9"
													maxlength="9" /> <strong>Situa&ccedil;&atilde;o do RA
												Associado:</strong> <html:text
													property="situacaoRaAssociado" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="12"
													maxlength="9" /></td>
											</tr>
										</c:if>

										<tr>
											<td class="style3"><strong>Tipo de Solicita&ccedil;&atilde;o:</strong></td>
											<td colspan="3"><html:text property="tipoSolicitacaoId"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="4" maxlength="4" /> <html:text
												property="tipoSolicitacaoDescricao" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="50"
												maxlength="50" /></td>
										</tr>

										<tr>
											<td height="10"><strong>Especifica&ccedil;&atilde;o:</strong></td>
											<td colspan="3"><html:text property="especificacaoId"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="4" maxlength="4" /> <html:text
												property="especificacaoDescricao" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="50"
												maxlength="50" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>Meio de Solicita&ccedil;&atilde;o:</strong></td>
											<td colspan="3"><html:text
												property="meioSolicitacaoDescricao" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="50"
												maxlength="50" /></td>
										</tr>

										<tr>
											<td><strong>Matr&iacute;cula do Im&oacute;vel:</strong></td>
											<td colspan="3"><html:text property="matriculaImovel"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="8" maxlength="8" /> <html:text
												property="inscricaoImovel" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="21"
												maxlength="21" /></td>
										</tr>

										<tr>
											<td width="31%" height="10"><strong>Data e Hora do
											Atendimento:</strong></td>
											<td colspan="3"><html:text property="dataAtendimento"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="10" maxlength="10" /> <html:text
												property="horaAtendimento" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="5"
												maxlength="5" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>Data Prevista:</strong></td>
											<td colspan="3"><html:text property="dataPrevista"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="10" maxlength="10" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>Data da Encerramento:</strong></td>

											<td><html:text property="dataEncerramento" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="10"
												maxlength="10" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>Motivo do Encerramento:</strong></td>
											<td><html:text property="idMotivoEncerramento"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="5" maxlength="5" /> <html:text
												property="motivoEncerramento" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="50"
												maxlength="50" /></td>

										</tr>

										<tr>
											<td height="10" colspan="4">
											<div align="right">
											<hr>
											</div>
											<div align="right"></div>
											</td>
										</tr>
										
										<tr>
											<td class="style3"><strong>Nº Protocolo:</strong></td>
											<td colspan="3"><html:text property="protocoloAtendimento"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="15" maxlength="14" /></td>
										</tr>
										
										<tr>
											<td class="style3"><strong>Cliente Solicitante:</strong></td>
											<td colspan="3"><html:text property="idClienteSolicitante"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="4" maxlength="4" /> <html:text
												property="clienteSolicitante" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="40"
												maxlength="40" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>Unidade Solicitante:</strong></td>
											<td colspan="3"><html:text property="idUnidadeSolicitante"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="4" maxlength="4" /> <html:text
												property="unidadeSolicitante" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="40"
												maxlength="40" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>Nome do Solicitante:</strong></td>
											<td colspan="3"><html:text property="nomeSolicitante"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="40" maxlength="40" /></td>
										</tr>

										<tr>
											<td height="10" colspan="4">
											<div align="right">
											<hr>
											</div>
											<div align="right"></div>
											</td>
										</tr>

										<tr>
											<td class="style3"><strong><span class="style2">Endere&ccedil;o
											da Ocorr&ecirc;ncia:</span></strong></td>

											<td><html:textarea property="enderecoOcorrencia"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												cols="50" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>Ponto de Refer&ecirc;ncia:</strong></td>

											<td><html:text property="pontoReferencia" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="60"
												maxlength="60" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>Bairro:</strong></td>
											<td><html:text property="bairroId" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="4"
												maxlength="4" /> <html:text property="bairroDescricao"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="40" maxlength="40" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>&Aacute;rea do Bairro:</strong></td>
											<td><html:text property="areaBairroId" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="4"
												maxlength="4" /> <html:text property="areaBairroDescricao"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="40" maxlength="40" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>Local/Setor/Quadra:</strong></td>
											<td><html:text property="localidadeId" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="4"
												maxlength="4" /> <strong>/</strong> <html:text
												property="setorComercialCodigo" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="4"
												maxlength="4" /> <strong>/</strong> <html:text
												property="quadraNumero" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="4"
												maxlength="4" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>Divis&atilde;o de Esgoto:</strong>
											</td>
											<td colspan="3"><html:text property="divisaoEsgotoId"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="4" maxlength="4" /> <html:text
												property="divisaoEsgotoDescricao" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="40"
												maxlength="40" /></td>
										</tr>

										<tr>
											<td height="10" colspan="4">
											<div align="right">
											<hr>
											</div>
											<div align="right"></div>
											</td>
										</tr>


										<tr>
											<td class="style3"><strong>Unidade de Atendimento:</strong></td>
											<td colspan="3"><html:text property="unidadeAtendimentoId"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="4" maxlength="4" /> <html:text
												property="unidadeAtendimentoDescricao" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="40"
												maxlength="40" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>Unidade Atual:</strong></td>
											<td colspan="3"><html:text property="unidadeAtualId"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="4" maxlength="4" /> <html:text
												property="unidadeAtualDescricao" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="40"
												maxlength="40" /></td>
										</tr>

									</table>
									</td>
								</tr>
							</table>
							</div>

							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="10" colspan="2">Para reativar o registro de
					atendimento, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td height="31">
					<table width="100%" border="0" align="center">

						<tr bgcolor="#cbe5fe">

							<td align="center">
							<table width="100%" border="0" bgcolor="#99CCFF">

								<tr bgcolor="#99CCFF">
									<td height="18" colspan="2">
									<div align="center"><span class="style2"><b>Dados do Novo
									Registro de Atendimento </b></span></div>
									</td>
								</tr>

								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">
										<tr>
											<td height="10"><strong>Tipo de Atendimento:<font
												color="#FF0000">*</font></strong></td>
											<td><html:radio property="tipoAtendimento" value="1"
												onclick="redirecionarSubmit('exibirReativarRegistroAtendimentoAction.do')" />
											<strong>on-line</strong> <html:radio
												property="tipoAtendimento" value="2"
												onclick="redirecionarSubmit('exibirReativarRegistroAtendimentoAction.do')" />
											<strong>manual</strong></td>
										</tr>
										<tr>
											<td height="10"><strong>Data do Atendimento:<font
												color="#FF0000">*</font></strong></td>
											<td><logic:equal name="ReativarRegistroAtendimentoActionForm"
												property="tipoAtendimento" value="1">
												<html:text property="dataAtendimentoReativado" size="11"
													maxlength="10" tabindex="3"
													onkeyup="mascaraData(this, event)" readonly="true"
													style="background-color:#EFEFEF; border:0;" />
												<img border="0"
													src="<bean:message key='caminho.imagens'/>calendario.gif"
													width="20" border="0" alt="Exibir Calendário" tabindex="4" />
											</logic:equal> <logic:notEqual
												name="ReativarRegistroAtendimentoActionForm"
												property="tipoAtendimento" value="1">
												<html:text property="dataAtendimentoReativado" size="11"
													maxlength="10" tabindex="3"
													onkeyup="mascaraData(this, event)"
													onblur="calcularDataPrevista();" />
												<a
													href="javascript:abrirCalendario('ReativarRegistroAtendimentoActionForm', 'dataAtendimentoReativado');">
												<img border="0"
													src="<bean:message key='caminho.imagens'/>calendario.gif"
													width="20" border="0" alt="Exibir Calendário" tabindex="4"
													onclick="document.forms[0].dataAtendimentoReativado.value = '';"
													onblur="calcularDataPrevista();" /></a>
											</logic:notEqual> <strong>&nbsp;(dd/mm/aaaa)</strong></td>
										</tr>
										<tr>
											<td height="10"><strong>Hora:<font color="#FF0000">*</font></strong></td>
											<td><logic:equal name="ReativarRegistroAtendimentoActionForm"
												property="tipoAtendimento" value="1">
												<html:text property="horaAtendimentoReativado" size="10"
													maxlength="5" tabindex="5"
													onkeyup="mascaraHora(this, event)" readonly="true"
													style="background-color:#EFEFEF; border:0;" />
											</logic:equal> <logic:notEqual
												name="ReativarRegistroAtendimentoActionForm"
												property="tipoAtendimento" value="1">
												<html:text property="horaAtendimentoReativado" size="10"
													maxlength="5" tabindex="5"
													onkeyup="mascaraHora(this, event)" />
											</logic:notEqual> <strong>&nbsp;(hh:mm)</strong></td>
										</tr>
										<tr>
											<td height="10"><strong>Tempo de Espera:</strong></td>
											<td><html:text property="tempoEsperaInicial" size="10"
												maxlength="5" tabindex="6"
												onkeyup="limpaTempoFinal();if (mascaraHora(this, event)){carregarTempoEsperaFinal();}" />
											<strong>&nbsp;(hh:mm)</strong> &nbsp;&nbsp;&nbsp; <logic:equal
												name="ReativarRegistroAtendimentoActionForm"
												property="tipoAtendimento" value="1">
												<html:text property="tempoEsperaFinal" size="10"
													maxlength="5" tabindex="7"
													onkeyup="mascaraHora(this, event)" readonly="true"
													style="background-color:#EFEFEF; border:0;" />
											</logic:equal> <logic:notEqual
												name="ReativarRegistroAtendimentoActionForm"
												property="tipoAtendimento" value="1">
												<html:text property="tempoEsperaFinal" size="10"
													maxlength="5" tabindex="7"
													onkeyup="mascaraHora(this, event)" />
											</logic:notEqual> <strong>&nbsp;(hh:mm)</strong></td>
										</tr>
										<tr>
											<td class="style3"><strong>Data Prevista:</strong></td>
											<td colspan="3"><html:text property="dataPrevistaReativado"
												size="10" maxlength="10" readonly="true"
												style="background-color:#EFEFEF; border:0;" /></td>
										</tr>
										<tr>
											<td><strong>Meio de Solicita&ccedil;&atilde;o:<font
												color="#FF0000">*</font></strong></td>
											<td><html:select property="meioSolicitacao" tabindex="4">
												<html:option value="">&nbsp;</html:option>
												<html:options collection="colecaoMeioSolicitacao"
													labelProperty="descricao" property="id" />
											</html:select> <font size="1">&nbsp; </font></td>
										</tr>
										<tr>
											<td class="style3"><strong>Unidade de Atendimento:<font
												color="#FF0000">*</font></strong></td>
											<td colspan="3"><html:text property="idUnidadeAtendimento"
												size="4" maxlength="4"
												onkeypress="validaEnterComMensagem(event, 'exibirReativarRegistroAtendimentoAction.do?validaUnidadeAtendimento=true', 'idUnidadeAtendimento','Unidade Atendimento');" />
											<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
												width="23" height="21" style="cursor:hand;" name="imagem"
												onclick="setUnidade(1); chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do', 'unidadeOrganizacional', null, null, 275, 480, '',document.forms[0].idUnidadeAtendimento);"
												alt="Pesquisar"> <logic:present
												name="unidadeAtendimentoEncontrado">
												<logic:equal name="unidadeAtendimentoEncontrado"
													value="exception">
													<html:text property="unidadeAtendimento" size="40"
														maxlength="40" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:equal>
												<logic:notEqual name="unidadeAtendimentoEncontrado"
													value="exception">
													<html:text property="unidadeAtendimento" size="40"
														maxlength="40" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEqual>
											</logic:present> <logic:notPresent
												name="unidadeAtendimentoEncontrado">
												<logic:empty name="ReativarRegistroAtendimentoActionForm"
													property="idUnidadeAtendimento">
													<html:text property="unidadeAtendimento" size="40"
														maxlength="40" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:empty>
												<logic:notEmpty name="ReativarRegistroAtendimentoActionForm"
													property="idUnidadeAtendimento">
													<html:text property="unidadeAtendimento" size="40"
														maxlength="40" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEmpty>
											</logic:notPresent> <a
												href="javascript:limparUnidadeAtendimento()"> <img
												border="0" title="Apagar"
												src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
											</a></td>
										</tr>
										<tr>
											<td><strong>Motivo da Reativação:<font color="#FF0000">*</font></strong></td>
											<td><html:select property="motivoReativacao" tabindex="4">
												<html:option value="-1">&nbsp;</html:option>
												<html:options collection="colecaoMotivoReativacao"
													labelProperty="descricao" property="id" />
											</html:select> <font size="1">&nbsp; </font></td>
										</tr>
										<tr>
											<td class="style3"><strong>Unidade Destino:</strong></td>
											<td colspan="3"><html:text property="idUnidadeDestino"
												size="4" maxlength="4"
												onkeypress="validaEnterComMensagem(event, 'exibirReativarRegistroAtendimentoAction.do?validaUnidadeDestino=true', 'idUnidadeDestino','Unidade Destino');" />
											<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
												width="23" height="21" style="cursor:hand;" name="imagem"
												onclick="setUnidade(2); chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do', 'unidadeOrganizacional', null, null, 275, 480, '',document.forms[0].idUnidadeDestino);"
												alt="Pesquisar"> <logic:present
												name="unidadeDestinoEncontrado">
												<logic:equal name="unidadeDestinoEncontrado"
													value="exception">
													<html:text property="unidadeDestino" size="40"
														maxlength="40" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:equal>
												<logic:notEqual name="unidadeDestinoEncontrado"
													value="exception">
													<html:text property="unidadeDestino" size="40"
														maxlength="40" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEqual>
											</logic:present> <logic:notPresent
												name="unidadeDestinoEncontrado">
												<logic:empty name="ReativarRegistroAtendimentoActionForm"
													property="idUnidadeDestino">
													<html:text property="unidadeDestino" size="40"
														maxlength="40" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:empty>
												<logic:notEmpty name="ReativarRegistroAtendimentoActionForm"
													property="idUnidadeDestino">
													<html:text property="unidadeDestino" size="40"
														maxlength="40" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEmpty>
											</logic:notPresent> <a
												href="javascript:limparUnidadeDestino()"> <img border="0"
												title="Apagar"
												src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
											</a></td>
										</tr>
										<tr>
											<td class="style3"><strong>Parecer para a Unidade Destino:</strong></td>
											<td colspan="3"><html:textarea
												property="parecerUnidadeDestino" cols="50" rows="4"
												onkeypress="javascript:textAreaMaxLength(200);" /></td>
										</tr>
										<tr>
											<td class="style3"><strong>Observa&ccedil;&atilde;o:</strong></td>
											<td colspan="3">
												<html:textarea property="observacao" cols="50" rows="4" onkeyup="limitTextArea(document.forms[0].observacao, 200, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
												<strong><span id="utilizado">0</span>/<span id="limite">200</span></strong>
											</td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>

					<table width="100%">
						<tr>
							<td><input name="ButtonVoltar" type="button"
								class="bottonRightCol" value="Voltar" onclick="javascript:consultarRA();">
							<input name="ButtonDesfazer" type="button" class="bottonRightCol"
								value="Desfazer" onclick="javascript:desfazer();"> <input
								name="ButtonCancelar" type="button" class="bottonRightCol"
								value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							
							<%-- <logic:present name="gerarOSAutomativa">
								<input name="gerarOS" type="button" class="bottonRightCol"
									style="width: 100px" value="Gerar OS"
									onClick="gerarURLGerarOrdemServico(${sessionScope.servicoTipo});" />
							</logic:present> <logic:notPresent name="gerarOSAutomativa">
								<input name="gerarOS" type="button" class="bottonRightCol"
									style="width: 100px" value="Gerar OS" onClick="" disabled />
							</logic:notPresent> --%> </td>
							
							<td align="right"><input name="ButtonInserir" type="button"
								class="bottonRightCol" onClick="javascript:validaForm()"
								value="Reativar"></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

	</table>
	<!-- Fim do Corpo -->

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
