<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false" 
	formName="FiltrarComandosAcaoCobrancaCronogramaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>	
<script language="JavaScript">
<!-- Begin

    function caracteresespeciais () {
     this.aa = new Array("intervaloQuantidadeDocumentosFinal", "Intervalo de Quantidade dos Documentos Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("intervaloQuantidadeDocumentosInicial", "Intervalo de Quantidade dos Documentos Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("intervaloValorDocumentosFinal", "Intervalo de Valor dos Documentos Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.af = new Array("intervaloValorDocumentosInicial", "Intervalo de Valor dos Documentos Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));     
     this.ac = new Array("intervaloQuantidadeItensDocumentosFinal", "Intervalo de Quantidade de Itens dos Documentos Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
  	  this.ad = new Array("intervaloQuantidadeItensDocumentosInicial", "Intervalo de Quantidade de Itens dos Documentos Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }

    function IntegerValidations () {
     this.am = new Array("intervaloQuantidadeDocumentosFinal", "Intervalo de Quantidade dos Documentos Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.an = new Array("intervaloQuantidadeDocumentosInicial", "Intervalo de Quantidade dos Documentos Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ao = new Array("intervaloQuantidadeItensDocumentosFinal", "Intervalo de Quantidade de Itens dos Documentos Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ap = new Array("intervaloQuantidadeItensDocumentosInicial", "Intervalo de Quantidade de Itens dos Documentos Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }



function filtrar(){
	var form = document.forms[0];
	
	if(verificaAnoMesMensagemPersonalizada(form.periodoReferenciaCobrancaInicial,"Período de Referência da Cobrança inicial inválido")
	&& verificaAnoMesMensagemPersonalizada(form.periodoReferenciaCobrancaFinal,"Período de Referência da Cobrança final inválido")
	&& verificaDataMensagemPersonalizada(form.periodoPrevisaoComandoInicial,"Período de Previsão do Comando Inicial inválido")
	&& verificaDataMensagemPersonalizada(form.periodoPrevisaoComandoFinal,"Período de Previsão do Comando Final inválido")
	&& verificaDataMensagemPersonalizada(form.periodoComandoInicial,"Período do Comando Inicial inválido")
	&& verificaDataMensagemPersonalizada(form.periodoComandoFinal,"Período do Comando Final inválido")
	&& verificaDataMensagemPersonalizada(form.periodoRealizacaoComandoInicial,"Período de Realização do Comando Inicial inválido")
	&& verificaDataMensagemPersonalizada(form.periodoRealizacaoComandoFinal,"Período de Realização do Comando Final inválido")
	&& testarCampoValorZero(document.FiltrarComandosAcaoCobrancaCronogramaActionForm.intervaloQuantidadeDocumentosFinal, 'Intervalo de Quantidade dos Documentos Final')
	&& testarCampoValorZero(document.FiltrarComandosAcaoCobrancaCronogramaActionForm.intervaloQuantidadeDocumentosInicial, 'Intervalo de Quantidade dos Documentos Inicial')
	&& testarCampoValorZero(document.FiltrarComandosAcaoCobrancaCronogramaActionForm.intervaloQuantidadeItensDocumentosFinal, 'Intervalo de Quantidade de Itens dos Documentos Final')
	&& testarCampoValorZero(document.FiltrarComandosAcaoCobrancaCronogramaActionForm.intervaloQuantidadeItensDocumentosInicial, 'Intervalo de Quantidade de Itens dos Documentos Inicial')
	&& testarCampoValorZeroDecimal(document.FiltrarComandosAcaoCobrancaCronogramaActionForm.intervaloValorDocumentosFinal, 'Intervalo de Valor dos Documentos Final')
	&& testarCampoValorZeroDecimal(document.FiltrarComandosAcaoCobrancaCronogramaActionForm.intervaloValorDocumentosInicial, 'Intervalo de Valor dos Documentos Inicial')
    && validateCaracterEspecial(form) 
   	&& validateLong(form) ){	
		if (comparaData(document.FiltrarComandosAcaoCobrancaCronogramaActionForm.periodoPrevisaoComandoInicial.value, ">", document.FiltrarComandosAcaoCobrancaCronogramaActionForm.periodoPrevisaoComandoFinal.value )){
			alert('Período de Previsão do Comando Final anterior ao Período de Previsão do Comando Inicial');
			return false;
		}
		if (comparaData(document.FiltrarComandosAcaoCobrancaCronogramaActionForm.periodoComandoInicial.value, ">", document.FiltrarComandosAcaoCobrancaCronogramaActionForm.periodoComandoFinal.value )){
			alert('Período do Comando Final anterior ao Período do Comando Inicial');
			return false;			
		}
		if (comparaData(document.FiltrarComandosAcaoCobrancaCronogramaActionForm.periodoRealizacaoComandoInicial.value, ">", document.FiltrarComandosAcaoCobrancaCronogramaActionForm.periodoRealizacaoComandoFinal.value )){
			alert('Período de Realização do Comando Final anterior ao Período de Realização do Comando Inicial');
			return false;			
		}
		if(document.FiltrarComandosAcaoCobrancaCronogramaActionForm.intervaloQuantidadeDocumentosFinal.value  < 
			document.FiltrarComandosAcaoCobrancaCronogramaActionForm.intervaloQuantidadeDocumentosInicial.value){
			alert('Quantidade dos Documentos Final menor que a Quantidade dos Documentos Inicial');
			return false;			
		}
		if(document.FiltrarComandosAcaoCobrancaCronogramaActionForm.intervaloQuantidadeItensDocumentosFinal.value <
		   document.FiltrarComandosAcaoCobrancaCronogramaActionForm.intervaloQuantidadeItensDocumentosInicial.value){
			alert('Quantidade dos Itens de Documentos Final menor que a Quantidade dos Itens de Documentos Inicial');
			return false;			
		}
		if(document.FiltrarComandosAcaoCobrancaCronogramaActionForm.intervaloValorDocumentosFinal.value <
		 document.FiltrarComandosAcaoCobrancaCronogramaActionForm.intervaloValorDocumentosInicial.value){
			alert('Valor dos Documentos Final menor que o Valor dos Documentos Inicial');
			return false;			
		 }

	    form.action =  '/gsan/filtrarComandosAcaoCobrancaCronogramaAction.do'
		form.submit();
	}
}

function duplicaPeriodoReferenciaCobranca(){
	var formulario = document.forms[0]; 
	formulario.periodoReferenciaCobrancaFinal.value = formulario.periodoReferenciaCobrancaInicial.value;
}  
  
function duplicaPeriodoPrevisaoComando(){
	var formulario = document.forms[0]; 
	formulario.periodoPrevisaoComandoFinal.value = formulario.periodoPrevisaoComandoInicial.value;
}  


function duplicaPeriodoComando(){
	var formulario = document.forms[0]; 
	formulario.periodoComandoFinal.value = formulario.periodoComandoInicial.value;
}  
  
function duplicaPeriodoRealizacaoComando(){
	var formulario = document.forms[0]; 
	formulario.periodoRealizacaoComandoFinal.value = formulario.periodoRealizacaoComandoInicial.value;
}  
  
function duplicaIntervaloQuantidadeDocumentos(){
	var formulario = document.forms[0]; 
	formulario.intervaloQuantidadeDocumentosFinal.value = formulario.intervaloQuantidadeDocumentosInicial.value;
}  
  
function duplicaIntervaloValorDocumentos(){
	var formulario = document.forms[0]; 
	formulario.intervaloValorDocumentosFinal.value = formulario.intervaloValorDocumentosInicial.value;
}  

function duplicaIntervaloQuantidadeItensDocumentos(){
	var formulario = document.forms[0]; 
	formulario.intervaloQuantidadeItensDocumentosFinal.value = formulario.intervaloQuantidadeItensDocumentosInicial.value;
}  
  
function cancelar(){
		var formulario = document.forms[0]; 
   		formulario.action =  '/gsan/telaPrincipal.do'
   		formulario.submit();
}
  

function voltar(){
		var formulario = document.forms[0]; 
   		formulario.action =  '/gsan/exibirConsultarComandosAcaoCobrancaAction.do?carregando=SIM'
   		formulario.submit();
}


function limpar(){
		var formulario = document.forms[0]; 
		formulario.periodoReferenciaCobrancaInicial.value = "";
		formulario.periodoReferenciaCobrancaFinal.value = "";
		formulario.grupoCobranca.selectedIndex = 0;
		formulario.acaoCobranca.selectedIndex = 0;
		formulario.atividadeCobranca.selectedIndex = 0;
		formulario.periodoPrevisaoComandoInicial.value = "";
		formulario.periodoPrevisaoComandoFinal.value = "";
		formulario.periodoComandoInicial.value = "";
		formulario.periodoComandoFinal.value = "";
		formulario.periodoRealizacaoComandoInicial.value = "";
		formulario.periodoRealizacaoComandoFinal.value = "";
		formulario.intervaloValorDocumentosInicial.value = "";
		formulario.intervaloValorDocumentosFinal.value = "";
		formulario.intervaloQuantidadeDocumentosInicial.value = "";
		formulario.intervaloQuantidadeDocumentosFinal.value = "";
		formulario.intervaloQuantidadeItensDocumentosInicial.value = "";
		formulario.intervaloQuantidadeItensDocumentosFinal.value = "";
		formulario.situacaoCronograma[0].checked = true;
		formulario.situacaoComando[0].checked = true;
		
}
  
-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<form name="FiltrarComandosAcaoCobrancaCronogramaActionForm"><%@ include file="/jsp/util/cabecalho.jsp"%> <%@ include
	file="/jsp/util/menu.jsp"%>

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
		<td valign="top" class="centercoltext"><!--Início Tabela Reference a Páginação da Tela de Processo-->
		<table height="100%">
			<tr>
				<td></td>
			</tr>
		</table>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
				<td class="parabg">Filtrar Comandos de A&ccedil;&atilde;o de
				Cobran&ccedil;a - Comandos do Cronograma</td>
				<td width="11" valign="top"><img border="0"
					src="imagens/parahead_right.gif" /></td>
			</tr>
		</table>
		<!--Fim Tabela Reference a Páginação da Tela de Processo-->
		<p>&nbsp;</p>
		<table width="100%" border="0" dwcopytype="CopyTableRow">
			<tr>
				<td width="29%"><strong>Per&iacute;odo de Refer&ecirc;ncia da
				Cobran&ccedil;a:</strong></td>
				<td colspan="4"><strong><strong> <html:text maxlength="7"
					name="FiltrarComandosAcaoCobrancaCronogramaActionForm"
					property="periodoReferenciaCobrancaInicial" size="7"
					onkeyup="javascript:mascaraAnoMes(this, event);duplicaPeriodoReferenciaCobranca();" />
				</strong> </strong>(mm/aaaa)<strong><strong><strong> a</strong> <html:text
					maxlength="7"
					name="FiltrarComandosAcaoCobrancaCronogramaActionForm"
					property="periodoReferenciaCobrancaFinal" size="7"
					onkeyup="javascript:mascaraAnoMes(this, event);" /> </strong> </strong>(mm/aaaa)<strong>
				</strong></td>
			</tr>
			<tr>
				<td><strong>Grupo de Cobran&ccedil;a:</strong></td>
				<td colspan="4"><html:select property="grupoCobranca"
					name="FiltrarComandosAcaoCobrancaCronogramaActionForm"
					style="width: 230px;" multiple="mutiple" size="2">
					<logic:present name="colecaoGrupoCobranca">
						<html:option value="" />
						<html:options collection="colecaoGrupoCobranca"
							labelProperty="descricao" property="id" />
					</logic:present>
				</html:select></td>
			</tr>
			<tr>
				<td><strong>A&ccedil;&atilde;o de Cobran&ccedil;a:</strong></td>
				<td colspan="4"><html:select property="acaoCobranca"
					name="FiltrarComandosAcaoCobrancaCronogramaActionForm"
					style="width: 230px;" multiple="mutiple" size="2">
					<logic:present name="colecaoAcaoCobranca">
						<html:option value="" />
						<html:options collection="colecaoAcaoCobranca"
							labelProperty="descricaoCobrancaAcao" property="id" />
					</logic:present>
				</html:select></td>
			</tr>
			<tr>
				<td><strong>Atividade de Cobran&ccedil;a:</strong></td>
				<td colspan="4"><html:select property="atividadeCobranca"
					name="FiltrarComandosAcaoCobrancaCronogramaActionForm"
					style="width: 230px;" multiple="mutiple" size="2">
					<logic:present name="colecaoAtividadeCobranca">
						<html:option value="" />
						<html:options collection="colecaoAtividadeCobranca"
							labelProperty="descricaoCobrancaAtividade" property="id" />
					</logic:present>
				</html:select></td>
			</tr>
			<tr>
				<td colspan="5">
				<hr>
				</td>
			</tr>
			<tr>
				<td height="17"><strong>Per&iacute;odo de Previs&atilde;o do Comando</strong><strong>:</strong></td>
				<td colspan="4"><strong> <html:text maxlength="10"
					name="FiltrarComandosAcaoCobrancaCronogramaActionForm"
					property="periodoPrevisaoComandoInicial" size="10"
					onkeyup="javascript:mascaraData(this, event);duplicaPeriodoPrevisaoComando();" />
				<a
					href="javascript:abrirCalendarioReplicando('FiltrarComandosAcaoCobrancaCronogramaActionForm', 
					'periodoPrevisaoComandoInicial','periodoPrevisaoComandoFinal')">
				<img border="0"
					src="<bean:message key='caminho.imagens'/>calendario.gif"
					width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
				<strong> a</strong> <html:text maxlength="10"
					name="FiltrarComandosAcaoCobrancaCronogramaActionForm"
					property="periodoPrevisaoComandoFinal" size="10"
					onkeyup="javascript:mascaraData(this, event);" /> <a
					href="javascript:abrirCalendario('FiltrarComandosAcaoCobrancaCronogramaActionForm', 'periodoPrevisaoComandoFinal')">
				<img border="0"
					src="<bean:message key='caminho.imagens'/>calendario.gif"
					width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
				</strong></td>
			</tr>
			<tr>
				<td height="17"><strong>Per&iacute;odo do Comando</strong><strong>:</strong></td>
				<td colspan="4"><strong> <html:text maxlength="10"
					name="FiltrarComandosAcaoCobrancaCronogramaActionForm"
					property="periodoComandoInicial" size="10"
					onkeyup="javascript:mascaraData(this, event);duplicaPeriodoComando();" />
				<a
					href="javascript:abrirCalendarioReplicando('FiltrarComandosAcaoCobrancaCronogramaActionForm', 'periodoComandoInicial',
					'periodoComandoFinal')">
				<img border="0"
					src="<bean:message key='caminho.imagens'/>calendario.gif"
					width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
				<strong> a</strong> <html:text maxlength="10"
					name="FiltrarComandosAcaoCobrancaCronogramaActionForm"
					property="periodoComandoFinal" size="10"
					onkeyup="javascript:mascaraData(this, event);" /> <a
					href="javascript:abrirCalendario('FiltrarComandosAcaoCobrancaCronogramaActionForm', 'periodoComandoFinal')">
				<img border="0"
					src="<bean:message key='caminho.imagens'/>calendario.gif"
					width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
				</strong></td>
			</tr>
			<tr>
				<td height="17"><strong>Per&iacute;odo de Realiza&ccedil;&atilde;o
				do Comando</strong><strong>:</strong></td>
				<td colspan="4"><strong> <html:text maxlength="10"
					name="FiltrarComandosAcaoCobrancaCronogramaActionForm"
					property="periodoRealizacaoComandoInicial" size="10"
					onkeyup="javascript:mascaraData(this, event);duplicaPeriodoRealizacaoComando();" />
				<a
					href="javascript:abrirCalendarioReplicando('FiltrarComandosAcaoCobrancaCronogramaActionForm', 'periodoRealizacaoComandoInicial',
					'periodoRealizacaoComandoFinal')">
				<img border="0"
					src="<bean:message key='caminho.imagens'/>calendario.gif"
					width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
				<strong> a</strong> <html:text maxlength="10"
					name="FiltrarComandosAcaoCobrancaCronogramaActionForm"
					property="periodoRealizacaoComandoFinal" size="10"
					onkeyup="javascript:mascaraData(this, event);" /> <a
					href="javascript:abrirCalendario('FiltrarComandosAcaoCobrancaCronogramaActionForm', 'periodoRealizacaoComandoFinal')">
				<img border="0"
					src="<bean:message key='caminho.imagens'/>calendario.gif"
					width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
				</strong></td>
			</tr>
			<tr>
				<td colspan="5">
				<hr>
				</td>
			</tr>
			<tr>
				<td height="17"><strong>Intervalo de Valor dos Documentos:</strong></td>
				<td colspan="4"><strong> <html:text maxlength="17" style="text-align: right;"
					name="FiltrarComandosAcaoCobrancaCronogramaActionForm"
					property="intervaloValorDocumentosInicial" size="17"
					onkeyup="javascript:formataValorMonetario(this, 13);duplicaIntervaloValorDocumentos();" />
				a <html:text maxlength="17" style="text-align: right;"
					name="FiltrarComandosAcaoCobrancaCronogramaActionForm"
					property="intervaloValorDocumentosFinal" size="17"
					onkeyup="javascript:formataValorMonetario(this, 13)" /> </strong></td>
			</tr>
			<tr>
				<td height="17"><strong>Intervalo de Quantidade dos Documentos:</strong></td>
				<td colspan="4"><strong> <html:text maxlength="9"
					name="FiltrarComandosAcaoCobrancaCronogramaActionForm"
					property="intervaloQuantidadeDocumentosInicial" size="9"
					onkeyup="javascript:duplicaIntervaloQuantidadeDocumentos();" /> a
				<html:text maxlength="9"
					name="FiltrarComandosAcaoCobrancaCronogramaActionForm"
					property="intervaloQuantidadeDocumentosFinal" size="9" /> </strong></td>
			</tr>
			<tr>
				<td height="17"><strong>Intervalo de Quantidade de Itens dos
				Documentos:</strong></td>
				<td colspan="4"><strong> <html:text maxlength="9"
					name="FiltrarComandosAcaoCobrancaCronogramaActionForm"
					property="intervaloQuantidadeItensDocumentosInicial" size="9"
					onkeyup="javascript:duplicaIntervaloQuantidadeItensDocumentos();" />
				a <html:text maxlength="9"
					name="FiltrarComandosAcaoCobrancaCronogramaActionForm"
					property="intervaloQuantidadeItensDocumentosFinal" size="9" /> </strong></td>
			</tr>
			<tr>
				<td colspan="5">
				<hr>
				</td>
			</tr>
			<tr>
				<td height="17"><strong>Situa&ccedil;&atilde;o do Cronograma:</strong></td>
				<td width="11%"><strong> <html:radio property="situacaoCronograma"
					name="FiltrarComandosAcaoCobrancaCronogramaActionForm"
					value="Todos" /> Todos </strong></td>
				<td><strong> <html:radio property="situacaoCronograma"
					name="FiltrarComandosAcaoCobrancaCronogramaActionForm"
					value="Comandados" /> Comandados</strong></td>
				<td><strong> <html:radio property="situacaoCronograma"
					name="FiltrarComandosAcaoCobrancaCronogramaActionForm"
					value="NaoComandados" /> N&atilde;o Comandados</strong></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td height="17"><strong>Situa&ccedil;&atilde;o do Comando:</strong></td>
				<td><strong> <html:radio property="situacaoComando"
					name="FiltrarComandosAcaoCobrancaCronogramaActionForm"
					value="Todos" /> Todos </strong></td>
				<td><strong> <html:radio property="situacaoComando"
					name="FiltrarComandosAcaoCobrancaCronogramaActionForm"
					value="Realizados" /> Realizados </strong></td>
				<td><strong> <html:radio property="situacaoComando"
					name="FiltrarComandosAcaoCobrancaCronogramaActionForm"
					value="NaoRealizados" /> N&atilde;o Realizados</strong></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="5">
				<hr>
				</td>
			</tr>
			<tr>
				<td colspan="5">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="5">&nbsp;</td>
			</tr>

			<tr>
				<td colspan="3" align="left">
				<table border="0" align="left">
					<tr>
						<td align="left"><input name="Button32222" type="button"
							class="bottonRightCol" value="Limpar"
							onClick="javascript:limpar()" /></td>
						<td align="left">&nbsp;</td>							
					</tr>
				</table>
				</td>
				<td>
				<table border="0" align="right">
					<tr>
						<td align="right"><img src="imagens/voltar.gif" width="15"
							height="24" border="0" /></td>
						<td align="right">
						  <input name="Button32222" type="button" class="bottonRightCol" value="Voltar" onClick="javascript:voltar()" />
						</td>
						
						
						
						<td align="right">
						  <gsan:controleAcessoBotao name="concluir" value="Filtrar" onclick="javascript:filtrar();" url="filtrarComandosAcaoCobrancaCronogramaAction.do"/>
						  <%-- <input name="concluir" type="button" class="bottonRightCol" onClick="javascript:filtrar();" value="Filtrar" />--%>
						</td>
						
						
						
					</tr>
				</table>
				</td>
			</tr>

		</table>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>


<%@ include file="/jsp/util/rodape.jsp"%></form>
</body>
</html:html>
