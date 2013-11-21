<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirRegistroAtendimentoActionForm" dynamicJavascript="false" />

<SCRIPT LANGUAGE="JavaScript">

	var bCancel = false; 

    function validateInserirRegistroAtendimentoActionForm(form) {                                                                   
        if (bCancel) 
      return true; 
        else 
       //return validateCaracterEspecial(form) && validateRequired(form) && validateDate(form) && validateHoraMinuto(form) && validateInteger(form) && validarFormatacaoNumeracaoRAManual(form.numeroAtendimentoManual, "Número Manual"); 
       return validateCaracterEspecial(form) && validateRequired(form) && validateDate(form) && validateHoraMinuto(form) && validateInteger(form);
   } 
   
   function caracteresespeciais () { 
     this.aa = new Array("numeroAtendimentoManual", "Número de Atendimento Manual possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("dataAtendimento", "Data do Atendimento possui caracteres especiais.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     this.ac = new Array("hora", "Hora possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("tempoEsperaInicial", "Tempo de Espera Inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("tempoEsperaFinal", "Tempo de Espera Final possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.af = new Array("unidade", "Unidade de Atendimento possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ag = new Array("observacao", "Observação possui caracteres especiais.", new Function ("varName", " return this[varName];"));
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

    function HoraMinutoValidations () { 
     this.aa = new Array("hora", "Hora inválida. ", new Function ("varName", " return this[varName];"));
     this.ab = new Array("tempoEsperaInicial", "Tempo de Espera Inicial inválida.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("tempoEsperaFinal", "Tempo de Espera Final inválida.", new Function ("varName", " return this[varName];"));
    } 

    function IntegerValidations () { 
     this.aa = new Array("numeroAtendimentoManual", "Número de Atendimento Manual deve ser numérico(a).", new Function ("varName", " return this[varName];"));
     this.ab = new Array("unidade", "Unidade de Atendimento deve ser numérico(a).", new Function ("varName", " return this[varName];"));
    }
	


	function verificarDataAtendimento(){
		
		var form = document.forms[0];
		
		if (form.especificacao.value > 0){
			if (form.dataAtendimento.value.length < 1){
				alert("Informe Data do Atendimento");
				form.dataAtendimento.focus();
			}
			else if (validateDate(form)){
				redirecionarSubmit('inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosGeraisAction&definirDataPrevista=OK');
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
			redirecionarSubmit('inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosGeraisAction&tempoEsperaFinalDesabilitado=OK');
		}
	}
	
	function carregarEspecificacao(){
		
		var form = document.forms[0];
		
		if (form.tipoSolicitacao.value > 0){
			redirecionarSubmit('inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosGeraisAction&pesquisarEspecificacao=OK');
		}
	}

	function limparDataPrevista(dataAtendimento){
		
		var form = document.forms[0];
		
		if (dataAtendimento.value.length < 1){
			form.dataPrevista.value = "";
		}
	}
	
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.forms[0];

    	if (tipoConsulta == 'unidadeOrganizacional') {
      		form.unidade.value = codigoRegistro;
      		form.descricaoUnidade.value = descricaoRegistro;
      		form.descricaoUnidade.style.color = "#000000";
      
      		redirecionarSubmit("inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosGeraisAction&pesquisarUnidade=OK");
    	}
  	}

	//Função criada especialmente para o firefox
	function validarDataAtendimento(form){
		if (form.dataAtendimento.value.length > 0){
			if (verificaDataMensagemPersonalizada(form.dataAtendimento, "Data do Atendimento inválida.")){
				redirecionarSubmit('inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosGeraisAction&definirDataPrevista=OK');
			}
		}
	}

</SCRIPT>


</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}'); limitTextArea(document.forms[0].observacao, 400, document.getElementById('utilizado'), document.getElementById('limite'));">

<div id="formDiv">
<html:form action="/inserirRegistroAtendimentoWizardAction" method="post">

<html:hidden property="idImovel"/>

<html:hidden property="idMunicipio"/>
<html:hidden property="cdBairro"/>
<html:hidden property="idBairroArea"/>

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_ra.jsp?numeroPagina=1"/>

<logic:notPresent scope="session" name="origemGIS">
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp" %>
</logic:notPresent>


<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
  
<logic:notPresent scope="session" name="origemGIS">
  		<td width="140" valign="top" class="leftcoltext">
</logic:notPresent>
<logic:present scope="session" name="origemGIS">
	<td width="140" valign="top" class="leftcoltext" style="display:none;">
</logic:present>
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
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Inserir Registro de Atendimento</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      <tr>
      	<td colspan="3" HEIGHT="40" align="center">
      		<span style="font-size: 18px;font-weight: bold;">
      		Nº Protocolo: ${sessionScope.protocoloAtendimento}</span></td>
      </tr>
      
      <tr>
      	<td colspan="2">Para inserir o registro de atendimento, informe os dados gerais abaixo:</td>
		<logic:present scope="application" name="urlHelp">
								<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}atendimentoRegistroInserirAbaDadosGerais', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
		</logic:present>
		<logic:notPresent scope="application" name="urlHelp">
								<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
		</logic:notPresent>       
      </tr>
      
      <tr>
      	<td HEIGHT="30" WIDTH="140"><strong>Tipo do Atendimento:<font color="#FF0000">*</font></strong></td>
        <td>
			<html:radio property="tipo" value="1" tabindex="1" onclick="redirecionarSubmit('inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosGeraisAction&mudarTipo=OK');"/><strong>on-line
			<html:radio property="tipo" value="2" tabindex="2" onclick="redirecionarSubmit('inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosGeraisAction&mudarTipo=OK');"/>manual</strong>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Número Manual:</strong></td>
        <td>
			<logic:equal name="InserirRegistroAtendimentoActionForm" property="tipo" value="1">
				<html:text property="numeroAtendimentoManual" size="12" maxlength="11" tabindex="3" readonly="true"/>
			</logic:equal>
			
			<logic:notEqual name="InserirRegistroAtendimentoActionForm" property="tipo" value="1">
				<html:text property="numeroAtendimentoManual" size="12" maxlength="11" tabindex="3" onkeypress="return isCampoNumerico(event);"/>
			</logic:notEqual>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Data do Atendimento:<font color="#FF0000">*</font></strong></td>
        <td>
			<logic:equal name="InserirRegistroAtendimentoActionForm" property="tipo" value="1">
				<html:text property="dataAtendimento" size="11" maxlength="10" tabindex="4" onkeyup="mascaraData(this, event)" readonly="true"/>
				<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="20" border="0" alt="Exibir Calendário" tabindex="4"/>
			</logic:equal>
			
			<logic:notEqual name="InserirRegistroAtendimentoActionForm" property="tipo" value="1">
				<html:text property="dataAtendimento" size="11" maxlength="10" tabindex="4" onkeyup="mascaraData(this, event);limparDataPrevista(this);" onblur="validarDataAtendimento(document.forms[0]);" onkeypress="return isCampoNumerico(event);"/>
				<a href="javascript:abrirCalendario('InserirRegistroAtendimentoActionForm', 'dataAtendimento');">
				<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="20" border="0" alt="Exibir Calendário" tabindex="4" /></a>
			</logic:notEqual>
			
			<strong>&nbsp;(dd/mm/aaaa)</strong>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Hora do Atendimento:<font color="#FF0000">*</font></strong></td>
        <td>
        	<logic:equal name="InserirRegistroAtendimentoActionForm" property="tipo" value="1">
				<html:text property="hora" size="10" maxlength="5" tabindex="5" onkeyup="mascaraHoraSemMensagem(this, event)" readonly="true"/>
			</logic:equal>
			
			<logic:notEqual name="InserirRegistroAtendimentoActionForm" property="tipo" value="1">
				<html:text property="hora" size="10" maxlength="5" tabindex="5" onkeyup="mascaraHoraSemMensagem(this, event)" onkeypress="return isCampoNumerico(event);"/>
			</logic:notEqual>
			
			<strong>&nbsp;(hh:mm)</strong>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Tempo de Espera:</strong></td>
        <td>
			<html:text property="tempoEsperaInicial" size="10" maxlength="5" tabindex="6" onkeyup="if (mascaraHoraSemMensagem(this, event)){carregarTempoEsperaFinal();}" onkeypress="return isCampoNumerico(event);"/>
			<strong>&nbsp;(hh:mm)</strong>
			
			&nbsp;&nbsp;&nbsp;
			
			<logic:equal name="InserirRegistroAtendimentoActionForm" property="tipo" value="1">
				<html:text property="tempoEsperaFinal" size="10" maxlength="5" tabindex="7" onkeyup="mascaraHoraSemMensagem(this, event)" readonly="true"/>
			</logic:equal>
			
			<logic:notEqual name="InserirRegistroAtendimentoActionForm" property="tipo" value="1">
				<html:text property="tempoEsperaFinal" size="10" maxlength="5" tabindex="7" onkeyup="mascaraHoraSemMensagem(this, event)" onkeypress="return isCampoNumerico(event);" />
			</logic:notEqual>
			
			<strong>&nbsp;(hh:mm)</strong>
			
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Unidade de Atendimento:<font color="#FF0000">*</font></strong></td>
        <td colspan="2">
        	
        	<html:text property="unidade" size="5" maxlength="4" tabindex="8" onkeypress="validaEnterComMensagem(event, 'inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosGeraisAction&pesquisarUnidade=OK', 'unidade', 'Unidade');return isCampoNumerico(event);"/>
			<a href="javascript:abrirPopup('exibirPesquisarUnidadeOrganizacionalAction.do', 410, 790);">
			<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" alt="Pesquisar" border="0" title="Pesquisar Unidade de Atendimento"></a>

			<logic:present name="corUnidade">

				<logic:equal name="corUnidade" value="exception">
					<html:text property="descricaoUnidade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:equal>

				<logic:notEqual name="corUnidade" value="exception">
					<html:text property="descricaoUnidade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEqual>

			</logic:present>

			<logic:notPresent name="corUnidade">

				<logic:empty name="InserirRegistroAtendimentoActionForm" property="unidade">
					<html:text property="descricaoUnidade" value="" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:empty>
				<logic:notEmpty name="InserirRegistroAtendimentoActionForm" property="unidade">
					<html:text property="descricaoUnidade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEmpty>
				

			</logic:notPresent>
        	
        	<a href="javascript:limpar(1);">
        	<img src="<bean:message key='caminho.imagens'/>limparcampo.gif" alt="Apagar" border="0" title="Apagar"></a>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Meio de Solicitação:<font color="#FF0000">*</font></strong></td>
        <td>
			<html:select property="meioSolicitacao" style="width: 200px;font-size:11px;" tabindex="9">
				<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
				<html:options collection="colecaoMeioSolicitacao" labelProperty="descricao" property="id"/>
			</html:select>
		</td>
      </tr>
      <tr>
        <td HEIGHT="30"><strong>Tipo de Solicitação:<font color="#FF0000">*</font></strong></td>
        <td>
			<logic:present name="generalizada">
				<html:select property="tipoSolicitacao" style="width: 350px;font-size:11px;" tabindex="10" disabled="true">
					<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
					<html:options collection="colecaoSolicitacaoTipo" labelProperty="descricao" property="id"/>
				</html:select>
			</logic:present>
			
			<logic:notPresent name="generalizada">
				<html:select property="tipoSolicitacao" style="width: 350px;font-size:11px;" tabindex="10" onchange="carregarEspecificacao()">
					<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
					<html:options collection="colecaoSolicitacaoTipo" labelProperty="descricao" property="id"/>
				</html:select>
			</logic:notPresent>
		</td>
      </tr>
      <tr>
        <td HEIGHT="30"><strong>Especificação:<font color="#FF0000">*</font></strong></td>
        <td>
        	<logic:present name="generalizada">
				<html:select property="especificacao" style="width: 350px;font-size:11px;" tabindex="11" disabled="true">
					<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
					<logic:present name="colecaoSolicitacaoTipoEspecificacao">
						<html:options collection="colecaoSolicitacaoTipoEspecificacao" labelProperty="descricao" property="id"/>
					</logic:present>
				</html:select>
			</logic:present>
			
			<logic:notPresent name="generalizada">
				<html:select property="especificacao" style="width: 350px;font-size:11px;" tabindex="11" onchange="verificarDataAtendimento()">
					<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
					<logic:present name="colecaoSolicitacaoTipoEspecificacao">
						<html:options collection="colecaoSolicitacaoTipoEspecificacao" labelProperty="descricao" property="id"/>
					</logic:present>
				</html:select>
			</logic:notPresent>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Data Prevista:</strong></td>
        <td>
			<html:text property="dataPrevista" size="11" maxlength="10" tabindex="12" onkeyup="mascaraData(this, event)" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
		</td>
      </tr>
      
      
      <tr>
      	<td HEIGHT="30"><strong>Valor Sugerido:</strong></td>
        <td>
			<html:text property="valorSugerido" size="11" maxlength="10" tabindex="12" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
		</td>
      </tr>
      
      
      
      <tr>
      	<td HEIGHT="30"><strong>Observação:</strong></td>
        <td>
			<html:textarea property="observacao" cols="40" rows="4" onkeyup="limitTextArea(document.forms[0].observacao, 400, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
			<strong><span id="utilizado">0</span>/<span id="limite">400</span></strong>
		</td>
      </tr>
      <tr>
      	<td colspan="3" height="10"></td>
      </tr>
      <tr>
        <td colspan="3">
			<div align="right">
				<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_ra.jsp?numeroPagina=1"/>
			</div>
		</td>
      </tr>
      </table>
      <p>&nbsp;</p>
	</td>
  </tr>
</table>


<logic:notPresent scope="session" name="origemGIS">
	<%@ include file="/jsp/util/rodape.jsp"%>
</logic:notPresent>
</html:form>
</div>

<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/inserirRegistroAtendimentoWizardAction.do?concluir=true&action=inserirRegistroAtendimentoDadosGeraisAction'); }
</script>
<logic:present scope="session" name="origemGIS">
	<script>
		document.getElementById("Layer1").style.top = "5";
	</script>
</logic:present>


</body>
</html:html>

