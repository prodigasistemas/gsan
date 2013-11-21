<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.arrecadacao.PagamentoDocumentosNaoAceitosHelper"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script>

    var bCancel = false;

    function validateInformarAcertoDocumentosNaoAceitosActionForm(form) {
       return  validaRequiredAvisoBancario() ;
   }

	function validaRequiredAvisoBancario () {
		var form = document.forms[0];
		var msg = '';

		if(form.periodoPagamentoInicial.value == null || form.periodoPagamentoInicial.value == "") {
			msg = 'Informe Período de Pagamento Inicial.\n';
		} else if (!verificaData(form.periodoPagamentoInicial)) {
			msg = msg + 'Data Inicial do Período Inválida.\n';
		}
		
		if(form.periodoPagamentoFinal.value == null || form.periodoPagamentoFinal.value == "") {
			msg = msg + 'Informe Período de Pagamento Final.\n';
		} else if (!verificaData(form.periodoPagamentoFinal)) {
			msg = msg + 'Data Final do Período Inválida.\n';
		}
		
		if(form.periodoPagamentoInicial.value != null && form.periodoPagamentoInicial.value != ""
			&& form.periodoPagamentoFinal.value != null && form.periodoPagamentoFinal.value != ""
			&& comparaData(form.periodoPagamentoInicial.value, ">", form.periodoPagamentoFinal.value)) {
			msg = msg + 'Data Final do Período é anterior à Data Inicial do Período.\n';
		}
		
		if(!selecionouPagamento()) {
			msg = msg + 'Informe Pagamento.\n';
		}
		
		if( msg != '' ){
			alert(msg);
			return false;
		}else{
			return true;
		}
	}

	function selecionouPagamento(){

		var form = document.forms[0];
	    var selecionados = form.elements['idPagamentoSelecionado'];
		var retorno = false;
		
		if(selecionados != null){
			if (selecionados.checked) {
				
				retorno = true;
				
			} else {
			
				for (i=0; i< selecionados.length; i++) {
					if(selecionados[i].checked){
						retorno = true;
					}
				}
				
			}
		}
		
		return retorno;
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
	
    	if (tipoConsulta == 'arrecadador') {
	        form.idArrecadador.value = codigoRegistro;
	        form.nomeArrecadador.value = descricaoRegistro;
	        form.nomeArrecadador.style.color = "#000000";
	    }

	 }

	 function recuperarDadosCincoParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, descricaoRegistro3, tipoConsulta) { 
		var form = document.forms[0];
		
	 	if (tipoConsulta == 'avisoBancario') {
			form.codigoAgenteArrecadador.value = descricaoRegistro1;
			form.dataLancamentoAviso.value = descricaoRegistro2;
			form.dataLancamentoAviso.style.color = "#000000";
			form.numeroSequencialAviso.value = descricaoRegistro3;
			form.idAvisoBancario.value = codigoRegistro;
		}
	}
	 
	function recuperarDadosSeisParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, descricaoRegistro3, descricaoRegistro4, tipoConsulta) { 
		var form = document.forms[0];
	 	
	 	if (tipoConsulta == 'movimentoArrecadador') {
			form.idArrecadadorMov.value = codigoRegistro;
			form.codigoBanco.value = descricaoRegistro1;
			form.codigoRemessa.value = descricaoRegistro2;
			form.identificacaoServico.value = descricaoRegistro3;
			form.numeroSequencial.value = descricaoRegistro4;
		}
	}
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.readOnly != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			}else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
		}
	}
	
	function limparPesquisaArrecadador(){
		var form = document.forms[0];
		
		form.idArrecadador.value = "";
		form.nomeArrecadador.value = "";
	}
	
	function limparAvisoBancario() {
		var form = document.forms[0];
		
		form.codigoAgenteArrecadador.value = "";
		form.dataLancamentoAviso.value = "";
		form.numeroSequencialAviso.value = "";
		form.idAvisoBancario.value = "";
	}
	
	function limparAvisoBancarioTecla() {
		var form = document.forms[0];
		
		form.codigoAgenteArrecadador.value = "";
		form.dataLancamentoAviso.value = "";
		form.numeroSequencialAviso.value = "";
	}
	
	function limparMovimento() {
		var form = document.forms[0];
		if(form.idArrecadador.value != ''){
			form.idArrecadadorMov.value = "";
			form.codigoBanco.value = "";
			form.codigoRemessa.value = "";
			form.identificacaoServico.value = "";
			form.numeroSequencial.value = "";
		}	
	}
	
	function verificaDataPagamento(form){
	  var DATA_ATUAL = document.getElementById("DATA_ATUAL").value;
	  
	  if (comparaData(form.dataPagamento.value, ">", DATA_ATUAL)){
		alert('Data do Pagamento posterior à data corrente ' + DATA_ATUAL);
	  } else{
	    return true;
	  }
	}
	
	function filtrarPagamentos(){
		var form =  document.forms[0];	
		dataInicial = form.periodoPagamentoInicial;
		dataFinal = form.periodoPagamentoFinal;
		arrecadador = form.idArrecadador;
		var msg = '';
		
		if(dataInicial.value == null || dataInicial.value == "") {
			msg = 'Informe Período de Pagamento Inicial.\n';
		} else if (!verificaData(dataInicial)) {
			msg = msg + 'Data Inicial do Período Inválida.\n';
		}
		
		if(dataFinal.value == null || dataFinal.value == "") {
			msg = msg + 'Informe Período de Pagamento Final.\n';
		} else if (!verificaData(dataFinal)) {
			msg = msg + 'Data Final do Período Inválida.\n';
		}
		
		if(dataInicial.value != null && dataInicial.value != ""
			&& dataFinal.value != null && dataFinal.value != ""
			&& comparaData(dataInicial.value, ">", dataFinal.value)) {
			msg = msg + 'Data Final do Período é anterior à Data Inicial do Período.\n';
		}
		
		if( msg != '' ){
			alert(msg);			
		}else{
			form.action = 'informarAcertoDocumentosNaoAceitosWizardAction.do?action=exibirInformarAcertoDocumentosNaoAceitosPagamentoAction&filtrarPagamentos=sim';
    		form.submit();
		}
			
	}
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form
    action="/informarAcertoDocumentosNaoAceitosWizardAction"
    method="post"
>

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=1"/>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="165" valign="top" class="leftcoltext">
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
          <td class="parabg">Informar Acerto Documentos Não Aceitos</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0" dwcopytype="CopyTableRow">
        <tr>
          <td colspan="2">
            Para gerar o acerto dos documentos não aceitos, informe os dados abaixo:
          </td>
        </tr>
        <tr>
			<td><strong>Período de Pagamento:<font color="#FF0000">*</font></strong></td>
			<td><strong> <html:text maxlength="10"
				property="periodoPagamentoInicial" size="10" tabindex="10"
				onkeyup="mascaraData(this, event);  replicarCampo(document.forms[0].periodoPagamentoFinal, document.forms[0].periodoPagamentoInicial);limparPeriodoFinal();"
				onchange="limparComandos();" />
			<a
				href="javascript:abrirCalendarioReplicando('InformarAcertoDocumentosNaoAceitosActionForm', 'periodoPagamentoInicial', 'periodoPagamentoFinal');">
			<img border="0"
				src="<bean:message key="caminho.imagens"/>calendario.gif"
				width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
			</strong> &nbsp;a&nbsp; <html:text maxlength="10" property="periodoPagamentoFinal"
				tabindex="11" size="10" onkeyup="mascaraData(this, event);"
				onchange="limparComandos();" /> <a
				href="javascript:abrirCalendario('InformarAcertoDocumentosNaoAceitosActionForm', 'periodoPagamentoFinal')">
			<img border="0"
				src="<bean:message key="caminho.imagens"/>calendario.gif"
				width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
			(dd/mm/aaaa)</td>
		</tr>
        <tr>
			<td><strong>Arrecadador:</strong></td>
			<td colspan="2">
				<html:text maxlength="3" property="idArrecadador" size="4"
					onkeypress="validaEnterComMensagem(event, 'informarAcertoDocumentosNaoAceitosWizardAction.do?action=exibirInformarAcertoDocumentosNaoAceitosPagamentoAction', 'idArrecadador','Arrecadador');" /> 
				
				<a 	href="javascript:abrirPopup('exibirPesquisarArrecadadorAction.do');"><img
					src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
					height="21" alt="Pesquisar Arrecadador" border="0"></a>
				
				<logic:present name="idArrecadadorNaoEncontrado">
					<logic:equal name="idArrecadadorNaoEncontrado" value="exception">
						<html:text property="nomeArrecadador" size="40" maxlength="30"
							readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					</logic:equal>
					<logic:notEqual name="idArrecadadorNaoEncontrado" value="exception">
						<html:text property="nomeArrecadador" size="40" maxlength="30"
							readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEqual>
				</logic:present> 
				<logic:notPresent name="idArrecadadorNaoEncontrado">
					<html:text property="nomeArrecadador" size="40" maxlength="30"
						readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:notPresent>
				
				<a href="javascript:limparPesquisaArrecadador(document.forms[0]);"> <img
				   src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				   border="0" title="Apagar" /></a>
			</td>

		</tr>
        <tr>
          <td width="18%">
            <strong>Aviso Bancário:</strong>
          </td>
                         
          <td colspan="3">
            <strong>
              <html:text property="idAvisoBancario" size="9" maxlength="9" onkeyup="validaEnter(event, 'informarAcertoDocumentosNaoAceitosWizardAction.do?action=exibirInformarAcertoDocumentosNaoAceitosPagamentoAction', 'idAvisoBancario');limparAvisoBancarioTecla();" /> 
              <a href="javascript:abrirPopup('exibirPesquisarAvisoBancarioAction.do?limparForm=1', 475, 765);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Aviso Bancário" /></a>
			  <html:text property="codigoAgenteArrecadador" size="3" maxlength="3" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  <logic:present name="avisoInexistente" scope="request">
				  <html:text property="dataLancamentoAviso" size="20" maxlength="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:present> 
			  <logic:notPresent name="avisoInexistente"	scope="request">
   				  <html:text property="dataLancamentoAviso" size="20" maxlength="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notPresent>
			  <html:text property="numeroSequencialAviso" size="3" maxlength="3" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			 <a href="javascript:limparAvisoBancario();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a>
			</strong>
			
		  </td>
        </tr>
		<tr>
			<td height="29"><strong>Movimento Arrecadador:</strong></td>
			<td colspan="3">
				<html:hidden property="idArrecadadorMov"/>
				<html:text property="codigoBanco" size="3" maxlength="3" 
					readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
				<html:text property="codigoRemessa" size="3" maxlength="3" 
					readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
				<html:text property="identificacaoServico" size="30" maxlength="30" 
					readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
				<html:text property="numeroSequencial" size="9" maxlength="9" 
					readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
				<strong>
				<a href="javascript:chamarPopup('exibirPesquisarMovimentoArrecadadorAction.do?', 'arrecadador', null, null, 475, 765, '',document.forms[0].idArrecadador);"> 	
					<img width="23" height="21" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						 style="cursor:hand;" alt="Pesquisar" border="0" title="Pesquisar Movimento Arrecadador"/></a> 
				<a href="javascript:limparMovimento();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /></a>
				</strong>
			</td>
		</tr>
        <tr>
          <td>
            <strong>Forma de Arrecadação:</strong>
          </td>
          <td colspan="3">
            <html:select property="idFormaArrecadacao" style="width: 450px">
              <html:option value="-1">&nbsp;</html:option> 
			  <html:options collection="colecaoFormaArrecadacao" labelProperty="descricao" property="id"/>
            </html:select>
          </td>
        </tr>
        <tr>
          <td></td>
          
          <td align="left">
            <strong> <font color="#FF0000">*</font> </strong> Campos obrigat&oacute;rios
          </td>
          
		  <td align="right">
		  	<input type="button"
				name="selecionar" class="bottonRightCol"
				value="Filtrar"
				onClick="javascript:filtrarPagamentos();" />
		  </td>
          <td></td>
        </tr>
      
		<tr><td colspan="4" height="24"><hr></td></tr>
		
        <tr>
          <td colspan="4">
            <strong>Guias de pagamento encontradas:</strong>
          </td>
        </tr>
		<tr>
			<td colspan="4" height="2">&nbsp;</td>
		</tr>
		<tr bordercolor="#79bbfd">
			<td colspan="4" align="left" bgcolor="#79bbfd">
			<strong>&nbsp;Pagamentos</strong>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" bgcolor="#99CCFF" border="0">
				<tr bordercolor="#000000">
					<td width="10%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>
						Selecionar</strong>
					</td>
					<td width="30%" bgcolor="#90c7fc" align="center" rowspan="2">
						<strong>Forma Arrec.</strong>
					</td>
					<td width="12%" bgcolor="#90c7fc" align="center" rowspan="2">
						<strong>Dt. Pagto</strong>
					</td>
					<td width="9%" bgcolor="#90c7fc" align="center" rowspan="2">
						<strong>Valor</strong>
					</td>
					<td width="9%" bgcolor="#90c7fc" align="center" rowspan="2">
						<strong>NSA</strong>
					</td>
					<td width="20%" bgcolor="#90c7fc" align="center" rowspan="2">
						<strong>Arrecadador</strong>
					</td>
					
	
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<div style="height: 100%; max-height: 300px; overflow: auto;">
					<table width="100%" bgcolor="#99CCFF">
							<logic:present
								name="colecaoPagamentoDocumentosNaoAceitosHelper">
								<%int cont = 0;%>
								<logic:iterate
									name="colecaoPagamentoDocumentosNaoAceitosHelper"
									id="pagamentoDocumentosNaoAceitosHelper"
									type="PagamentoDocumentosNaoAceitosHelper"
									scope="session">
										<%cont = cont + 1;
								if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {%>
										</tr>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td width="10%">
											<div align="center"><html:radio property="idPagamentoSelecionado"
												value="${pagamentoDocumentosNaoAceitosHelper.idPagamento}" />
											</div>
											</td>
		
											<td align="center" width="30%">
												<bean:write name="pagamentoDocumentosNaoAceitosHelper" property="formaArrecadacao"  /> 
											</td>
		
											<td align="center" width="12%">
												<bean:write name="pagamentoDocumentosNaoAceitosHelper" property="dataPagamento"  /> 
											</td>
		
											<td align="center" width="9%">
												<bean:write name="pagamentoDocumentosNaoAceitosHelper" property="valorPagamento"  /> 
											</td>
											
											<td align="center" width="9%">
												<bean:write name="pagamentoDocumentosNaoAceitosHelper" property="numeroNsa" /> 
											</td>
											
											<td align="center" width="20%">
												<bean:write name="pagamentoDocumentosNaoAceitosHelper" property="arrecadador" /> 
											</td>
		
										</tr>
								</logic:iterate>
							</logic:present>
					</table>
				</div>
			</td>
		</tr>
        <tr>
          <td colspan="4"><div align="right"><jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=1"/></div></td>
        </tr>
      </table>
      <p>&nbsp;</p>
    </td>
  </tr>

</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
