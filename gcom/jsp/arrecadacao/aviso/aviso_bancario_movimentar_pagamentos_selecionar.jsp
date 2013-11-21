<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="gcom.util.ConstantesSistema" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="SelecionarPagamentosAvisoBancarioActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
<!--
var aviso = 0;
	
function setAvisoBancario(tipo) {
  aviso = tipo;
}

function recuperarDadosCincoParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, descricaoRegistro3, tipoConsulta) { 
	var form = document.SelecionarPagamentosAvisoBancarioActionForm;
 	if (tipoConsulta == 'avisoBancario') {
		  if (aviso == 1) {
			form.codigoAgenteArrecadadorO.value = descricaoRegistro1;
			form.dataLancamentoAvisoO.value = descricaoRegistro2;
			form.numeroSequencialAvisoO.value = descricaoRegistro3;
			form.avisoBancarioO.value = codigoRegistro;
	      } else if (aviso == 2) {
			form.codigoAgenteArrecadadorD.value = descricaoRegistro1;
			form.dataLancamentoAvisoD.value = descricaoRegistro2;
			form.numeroSequencialAvisoD.value = descricaoRegistro3;
			form.avisoBancarioD.value = codigoRegistro;	      
	      }
	      aviso = 0;
	}
}

function limparAvisoBancarioOrigem() {
	var form = document.SelecionarPagamentosAvisoBancarioActionForm;
		form.codigoAgenteArrecadadorO.value = "";
		form.dataLancamentoAvisoO.value = "";
		form.numeroSequencialAvisoO.value = "";
		form.avisoBancarioO.value = "";
}

function limparAvisoBancarioDestino() {
	var form = document.SelecionarPagamentosAvisoBancarioActionForm;
		form.codigoAgenteArrecadadorD.value = "";
		form.dataLancamentoAvisoD.value = "";
		form.numeroSequencialAvisoD.value = "";
		form.avisoBancarioD.value = "";
}
 
function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
	if(objetoRelacionado.disabled != true){
		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		}
		else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}
			else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
			}
		}
	}
}

	
function validarForm(form){ 
  if(form.dataDevolucao.value == "" && form.dataPagamento.value == "" && form.idArrecadacaoForma.selectedIndex == 0){
    alert("Informe pelo menos um critério de seleção: Data de Devolução ou Data de Pagamento ou Forma de Arrecadação");
  } else if(validateSelecionarPagamentosAvisoBancarioActionForm(form)){
    form.submit();
  }
}

//-->
</script>
</head>
<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/selecionarPagamentosAvisoBancarioAction.do" name="SelecionarPagamentosAvisoBancarioActionForm" 
	type="gcom.gui.arrecadacao.aviso.SelecionarPagamentosAvisoBancarioActionForm" method="post"
	onsubmit="return validateSelecionarPagamentosAvisoBancarioActionForm(this);">	
	
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="755" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="550" valign="top" class="leftcoltext">
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

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Movimentar Pagamentos/Devoluções entre Avisos Bancários</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>Para selecionar os avisos bancários, informe os dados abaixo:</td>
					<td colspan="3">&nbsp;</td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td height="10"><strong>Aviso Banc&aacute;rio Origem:<font color="#FF0000">*</font></strong>
					</td>
					<td width="390"><html:text property="codigoAgenteArrecadadorO"
						size="3" maxlength="3" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /> <html:text
						property="dataLancamentoAvisoO" size="10" maxlength="10"
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /> <html:text
						property="numeroSequencialAvisoO" size="3" maxlength="3"
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /> <html:hidden
						property="avisoBancarioO" /> 
						<a href="javascript:setAvisoBancario(1); abrirPopup('exibirPesquisarAvisoBancarioAction.do?menu=sim', 475, 765);">
							<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Aviso Bancário Origem"/></a>
						<a href="javascript:limparAvisoBancarioOrigem();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Limpar Aviso Bancário Origem" /></a></td>
				</tr>
				<tr>
					<td height="10"><strong>Aviso Banc&aacute;rio Destino:<font color="#FF0000">*</font></strong>
					</td>
					<td width="390"><html:text property="codigoAgenteArrecadadorD"
						size="3" maxlength="3" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /> <html:text
						property="dataLancamentoAvisoD" size="10" maxlength="10"
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /> <html:text
						property="numeroSequencialAvisoD" size="3" maxlength="3"
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /> <html:hidden
						property="avisoBancarioD" /> 
						<a href="javascript:setAvisoBancario(2); abrirPopup('exibirPesquisarAvisoBancarioAction.do?menu=sim', 475, 765);">
							<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Aviso Bancário Destino"/></a>
						<a href="javascript:limparAvisoBancarioDestino();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Limpar Aviso Bancário Destino" /></a></td>
				</tr>
				<tr>
					<td height="24" colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td colspan="2">Para selecionar os pagamentos/devoluções a serem transferidos, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td height="10"><strong>Data da Devolu&ccedil;&atilde;o:</strong></td>
					<td width="390"><html:text tabindex="8" property="dataDevolucao" maxlength="10"
						size="10" onkeyup="mascaraData(this, event);" /> <a
						href="javascript:abrirCalendario('SelecionarPagamentosAvisoBancarioActionForm', 'dataDevolucao')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>&nbsp;dd/mm/aaaa
					</td>
				</tr>
				<tr>
					<td height="10"><strong>Data do Pagamento:</strong></td>
					<td width="390"><html:text tabindex="8" property="dataPagamento" maxlength="10"
						size="10" onkeyup="mascaraData(this, event);" /> <a
						href="javascript:abrirCalendario('SelecionarPagamentosAvisoBancarioActionForm', 'dataDevolucao')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>&nbsp;dd/mm/aaaa
					</td>
				</tr>
			    <tr> 
                  <td width="70"><strong>Forma de Arrecadação:</strong></td>
                  <td><span class="style2"><strong> 
					<html:select property="idArrecadacaoForma" style="width: 480px;">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoArrecadacaoForma" labelProperty="descricao" property="id" />
					</html:select>
                    </strong><strong> </strong></span></td>
                </tr>
				<tr>
					<td colspan="3" height="10"></td>
				</tr>
				<tr>
					<td height="10">&nbsp;</td>
					<td><strong><font color="#FF0000">*</font></strong> Campos obrigat&oacute;rios</td>
					<td>&nbsp;</td>
				</tr>
			</table>
			<table border="0" width="100%">
				<tr>
					<td colspan="2">
					<input tabindex="9" name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirSelecionarPagamentosAvisoBancarioAction.do?menu=sim"/>'">
					<input tabindex="10" name="Button" type="button" class="bottonRightCol" value="Cancelar" align="left"
						onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
					<td colspan="2" align="right">
					 <gsan:controleAcessoBotao name="Button" value="Selecionar" onclick="javascript:validarForm(document.forms[0]);" url="inserirDevolucoesAction.do"/>
						<%-- <input tabindex="11" type="button" name="Button" class="bottonRightCol" value="Inserir" onClick="javascript:validarForm(document.forms[0]);" /> --%>
					</td>
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
