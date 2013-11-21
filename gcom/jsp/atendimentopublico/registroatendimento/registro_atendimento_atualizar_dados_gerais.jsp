<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/telaespera.jsp"%>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AtualizarRegistroAtendimentoActionForm"
	dynamicJavascript="false" />

<SCRIPT LANGUAGE="JavaScript">
<!--

	var bCancel = false; 

    function validateAtualizarRegistroAtendimentoActionForm(form) {                                                                   
        if (bCancel) 
      return true; 
        else 
       return validateCaracterEspecial(form) && validateRequired(form) && validateDate(form) && validateInteger(form); 
   } 

    function caracteresespeciais () { 
     this.aa = new Array("dataAtendimento", "Data do Atendimento possui caracteres especiais.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     this.ab = new Array("hora", "Hora possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("tempoEsperaInicial", "Tempo de Espera Inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("tempoEsperaFinal", "Tempo de Espera Final possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("unidade", "Unidade de Atendimento possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.af = new Array("observacao", "Observação possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    } 

    function required () { 
     this.aa = new Array("dataAtendimento", "Informe Data do Atendimento.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     this.ab = new Array("hora", "Informe Hora.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("unidade", "Informe Unidade de Atendimento.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("meioSolicitacao", "Informe Meio de Solicitação.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("tipoSolicitacao", "Informe Tipo de Solicitação.", new Function ("varName", " return this[varName];"));
     this.af = new Array("especificacao", "Informe Especificação.", new Function ("varName", " return this[varName];"));
    } 

    function DateValidations () { 
     this.aa = new Array("dataAtendimento", "Data do Atendimento inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    } 

    function IntegerValidations () { 
     this.aa = new Array("unidade", "Unidade de Atendimento deve ser numérico(a).", new Function ("varName", " return this[varName];"));
    }


	function verificarDataAtendimento(){
		
		var form = document.forms[0];
		
		if (form.especificacao.value > 0){
			if (form.dataAtendimento.value.length < 1){
				alert("Informe Data do Atendimento");
				form.dataAtendimento.focus();
			}
			else if (validateDate(form)){
				redirecionarSubmit('atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosGeraisAction&definirDataPrevista=OK');
			}
		}
	}
	
	function limpar(situacao){
	
		var form = document.forms[0];
	
		switch (situacao){
	       case 1:
			   form.unidade.value = "";
			   form.descricaoUnidade.value = "";
			   
			   //Coloca o foco no objeto selecionado
			   form.unidade.focus();
			   break;
			   
		   default:
	          break;
		}
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
	
	    if (tipoConsulta == 'arrecadador') {
	      form.banco.value = codigoRegistro;
	      form.descricaoBanco.value = descricaoRegistro;
	      form.descricaoBanco.style.color = "#000000";
	    }
	}
	
	function carregarTempoEsperaFinal(){
		
		var form = document.forms[0];
		
		if (form.tempoEsperaInicial.value.length > 0 && form.tempoEsperaFinal.readOnly == true){
			redirecionarSubmit('atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosGeraisAction&tempoEsperaFinalDesabilitado=OK');
		}
	}
	
	function carregarEspecificacao(){
		
		var form = document.forms[0];
		
		if (form.tipoSolicitacao.value > 0){
			redirecionarSubmit('atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosGeraisAction&pesquisarEspecificacao=OK');
		}
	}
	
	function contasAssociadasTipo() {
		alert('Tipo de Solicitação não pode ser alterada, pois, existem contas associadas no RA.');
	}
	
	function contasAssociadasEspecificacao() {
		alert('Especificação não pode ser alterada, pois, existem contas associadas no RA.');
	}

//-->
</SCRIPT>


</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}'); limitTextArea(document.forms[0].observacao, 400, document.getElementById('utilizado'), document.getElementById('limite'));">

<div id="formDiv">
<html:form action="/atualizarRegistroAtendimentoWizardAction"
	method="post">

	<html:hidden property="idImovel"/>

	<html:hidden property="idMunicipio"/>
	<html:hidden property="cdBairro"/>
	<html:hidden property="idBairroArea"/>

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_ra.jsp?numeroPagina=1" />


	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="115" valign="top" class="leftcoltext">
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
					<td class="parabg">Atualizar Registro de Atendimento</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para atualizar o registro de atendimento, informe
					os dados gerais abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}atendimentoRegistroAtualizarAbaDadosGerais', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
				<tr>
					<td HEIGHT="30" WIDTH="80"><strong>Numero do RA:<font
						color="#FF0000">*</font></strong></td>
					<td><html:text property="numeroRA" 
								size="11" 
								maxlength="10"
								readonly="true" 
					/></td>
				</tr>
				<tr>
					<td HEIGHT="30" WIDTH="80"><strong>Tipo:<font color="#FF0000">*</font></strong></td>
					<td><html:radio property="tipo" value="1" tabindex="1"
						disabled="true" /><strong>on-line <html:radio property="tipo"
						value="2" tabindex="2" disabled="true" />manual</strong></td>
				</tr>
				<tr>
		      		<td HEIGHT="30"><strong>Número Manual:</strong></td>
		        	<td>
						<html:text property="numeroAtendimentoManual" size="12" maxlength="11" readonly="true"/>
					</td>
		        </tr>
				<tr>
					<td HEIGHT="30"><strong>Data do Atendimento:<font color="#FF0000">*</font></strong></td>
					<td><logic:equal name="AtualizarRegistroAtendimentoActionForm"
						property="tipo" value="1">
						<html:text property="dataAtendimento" size="11" maxlength="10"
							tabindex="3" onkeyup="mascaraData(this, event)" readonly="true" />
						<img border="0"
							src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" alt="Exibir Calendário" tabindex="4" />
					</logic:equal> <logic:notEqual
						name="AtualizarRegistroAtendimentoActionForm" property="tipo"
						value="1">
						<html:text property="dataAtendimento" size="11" maxlength="10"
							tabindex="3" onkeyup="mascaraData(this, event)" />
						<a
							href="javascript:abrirCalendario('AtualizarRegistroAtendimentoActionForm', 'dataAtendimento');">
						<img border="0"
							src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" alt="Exibir Calendário" tabindex="4" /></a>
					</logic:notEqual> <strong>&nbsp;(dd/mm/aaaa)</strong></td>
				</tr>
				<tr>
					<td HEIGHT="30"><strong>Hora:<font color="#FF0000">*</font></strong></td>
					<td><logic:equal name="AtualizarRegistroAtendimentoActionForm"
						property="tipo" value="1">
						<html:text property="hora" size="10" maxlength="5" tabindex="5"
							onkeyup="mascaraHora(this, event)" readonly="true" />
					</logic:equal> <logic:notEqual
						name="AtualizarRegistroAtendimentoActionForm" property="tipo"
						value="1">
						<html:text property="hora" size="10" maxlength="5" tabindex="5"
							onkeyup="mascaraHora(this, event)" />
					</logic:notEqual> <strong>&nbsp;(hh:mm)</strong></td>
				</tr>
				<tr>
					<td HEIGHT="30"><strong>Tempo de Espera:</strong></td>
					<td><html:text property="tempoEsperaInicial" size="10"
						maxlength="5" tabindex="6"
						onkeyup="if (mascaraHora(this, event)){carregarTempoEsperaFinal();}" 
						onkeypress="return isCampoNumerico(event);"/>
					<strong>&nbsp;(hh:mm)</strong> &nbsp;&nbsp;&nbsp; <logic:equal
						name="AtualizarRegistroAtendimentoActionForm" property="tipo"
						value="1">
						<html:text property="tempoEsperaFinal" 
								size="10" 
								maxlength="5"
								tabindex="7" 
								onkeyup="mascaraHora(this, event)" 
								readonly="true" 
								onkeypress="return isCampoNumerico(event);"/>
					</logic:equal> <logic:notEqual
						name="AtualizarRegistroAtendimentoActionForm" property="tipo"
						value="1">
						<html:text property="tempoEsperaFinal" 
								size="10" 
								maxlength="5"
								tabindex="7" 
								onkeypress="return isCampoNumerico(event);"
								onkeyup="mascaraHora(this, event)" />
					</logic:notEqual> <strong>&nbsp;(hh:mm)</strong></td>
				</tr>
				<tr>
					<td HEIGHT="30"><strong>Unidade de Atendimento:<font
						color="#FF0000">*</font></strong></td>
					<td><html:text property="unidade" size="5" maxlength="4"
						tabindex="8"
						onkeypress="validaEnterComMensagem(event, 'atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosGeraisAction&pesquisarUnidade=OK', 'unidade', 'Unidade');return isCampoNumerico(evet);"
						onkeyup="document.forms[0].descricaoUnidade.value = '';" /> <a
						href="javascript:abrirPopup('exibirPesquisar...Action.do', 320, 810);">
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
						width="23" height="21" alt="Pesquisar" border="0"></a> <logic:present
						name="corUnidade">

						<logic:equal name="corUnidade" value="exception">
							<html:text property="descricaoUnidade" size="45" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corUnidade" value="exception">
							<html:text property="descricaoUnidade" size="45" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> <logic:notPresent name="corUnidade">

						<logic:empty name="AtualizarRegistroAtendimentoActionForm"
							property="unidade">
							<html:text property="descricaoUnidade" value="" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="AtualizarRegistroAtendimentoActionForm"
							property="unidade">
							<html:text property="descricaoUnidade" size="45" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>


					</logic:notPresent> <a href="javascript:limpar(1);"> <img
						src="<bean:message key='caminho.imagens'/>limparcampo.gif"
						alt="Apagar" border="0"></a></td>
				</tr>
				<tr>
					<td HEIGHT="30"><strong>Meio de Solicitação:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="meioSolicitacao" style="width: 200px;font-size:11px;"
						tabindex="9">
						<html:option value="">&nbsp;</html:option>
						<html:options collection="colecaoMeioSolicitacao"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td HEIGHT="30"><strong>Tipo de Solicitação:<font color="#FF0000">*</font></strong></td>
					<td>
					<logic:empty name="AtualizarRegistroAtendimentoActionForm"
						property="idOrdemServico">

						<logic:empty name="contasAssociadas" scope="session">
							<html:select property="tipoSolicitacao" style="width: 350px;font-size:11px;"
								tabindex="10" onchange="carregarEspecificacao();">
								<html:option value="">&nbsp;</html:option>
								<html:options collection="colecaoSolicitacaoTipo"
									labelProperty="descricao" property="id" />
							</html:select>
						</logic:empty>
						
						<logic:notEmpty name="contasAssociadas" scope="session">
							<html:select property="tipoSolicitacao" style="width: 350px;font-size:11px;"
									tabindex="10" onchange="contasAssociadasTipo();" >
									<html:option value="">&nbsp;</html:option>
									<html:options collection="colecaoSolicitacaoTipo"
										labelProperty="descricao" property="id" />
							</html:select>
						</logic:notEmpty>

					</logic:empty> <logic:notEmpty
						name="AtualizarRegistroAtendimentoActionForm"
						property="idOrdemServico">
						<html:select property="tipoSolicitacao" style="width: 350px;font-size:11px;"
							tabindex="10" disabled="true">
							<html:option value="">&nbsp;</html:option>
							<html:options collection="colecaoSolicitacaoTipo"
								labelProperty="descricao" property="id" />
						</html:select>
					</logic:notEmpty></td>
				</tr>
				<tr>
					<td HEIGHT="30"><strong>Especificação:<font color="#FF0000">*</font></strong></td>
					<td><logic:present name="colecaoSolicitacaoTipoEspecificacao">
						<logic:empty name="AtualizarRegistroAtendimentoActionForm"
							property="idOrdemServico">
							<logic:empty name="contasAssociadas" scope="session">
								<html:select property="especificacao" style="width: 350px;font-size:11px;"
									tabindex="11" onchange="verificarDataAtendimento()">
									<html:option value="">&nbsp;</html:option>
									<html:options collection="colecaoSolicitacaoTipoEspecificacao"
										labelProperty="descricao" property="id" />
								</html:select>
							</logic:empty>
							
							<logic:notEmpty name="contasAssociadas" scope="session">
								<html:select property="especificacao" style="width: 350px;font-size:11px;"
									tabindex="11" onchange="contasAssociadasEspecificacao()">
									<html:option value="">&nbsp;</html:option>
									<html:options collection="colecaoSolicitacaoTipoEspecificacao"
										labelProperty="descricao" property="id" />
								</html:select>
							</logic:notEmpty>
							
						</logic:empty>
						<logic:notEmpty name="AtualizarRegistroAtendimentoActionForm"
							property="idOrdemServico">
							<html:select property="especificacao" style="width: 350px;font-size:11px;"
								tabindex="11" disabled="true">
								<html:option value="">&nbsp;</html:option>
								<html:options collection="colecaoSolicitacaoTipoEspecificacao"
									labelProperty="descricao" property="id" />
							</html:select>
						</logic:notEmpty>
					</logic:present> <logic:notPresent
						name="colecaoSolicitacaoTipoEspecificacao">
						<html:select property="especificacao" style="width: 350px;"
							tabindex="11" disabled="true">
							<html:option value="">&nbsp;</html:option>
						</html:select>
					</logic:notPresent></td>
				</tr>
				<tr>
					<td HEIGHT="30"><strong>Data Prevista:</strong></td>
					<td><html:text property="dataPrevista" size="11" maxlength="10"
						tabindex="12" onkeyup="mascaraData(this, event)" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</td>
				</tr>
				
				<tr>
					<td HEIGHT="30"><strong>Valor Sugerido:</strong></td>
					<td><html:text property="valorSugerido" size="11" maxlength="10"
						tabindex="12" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</td>
				</tr>
				<tr>
					<td HEIGHT="30"><strong>Observacao:</strong></td>
					<td><html:textarea property="observacao" cols="40" rows="4" onkeyup="limitTextArea(document.forms[0].observacao, 400, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
					<strong><span id="utilizado">0</span>/<span id="limite">400</span></strong></td>
				</tr>
				<tr>
					<td colspan="2" height="10"></td>
				</tr>
				<tr>
					<td colspan="2">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_ra.jsp?numeroPagina=1" />
					</div>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</div>

</body>
</html:html>

