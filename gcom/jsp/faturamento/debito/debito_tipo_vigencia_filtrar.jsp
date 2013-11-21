<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%-- Carrega validações do validator --%>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarDebitoTipoVigenciaActionForm" />
<%-- Carrega javascripts da biblioteca --%>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<%-- Novos Javascripts --%>
<script language="JavaScript">
	/* Valida Form */
	function validaForm() {
		var form = document.forms[0];
		/* Validate */
	if (validateFiltrarDebitoTipoVigenciaActionForm(form)) {
			submeterFormPadrao(form);
		}
	}
	
	/* Limpa Form */	 
	function limparForm() {
		var form = document.forms[0];

		form.valorDebitoInicial.value = "";
		form.valorDebitoFinal.value = "";
		limparDebitoTipo();
	}
	
	/* Limpa Tipo Perfil Servico do Form */	 
	function limparDebitoTipo() {
		var form = document.forms[0];
		form.debitoTipo.value = "";
		form.nomeDebitoTipo.value = "";
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

      if (tipoConsulta == 'tipoDebito') {
        form.debitoTipo.value = codigoRegistro;
        form.nomeDebitoTipo.value = descricaoRegistro;
        form.nomeDebitoTipo.style.color = "#000000";
      }
      
    }
	
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="">

<html:form action="/filtrarDebitoTipoVigenciaAction.do"
	name="FiltrarDebitoTipoVigenciaActionForm"
	type="gcom.gui.faturamento.debito.FiltrarDebitoTipoVigenciaActionForm"
	method="post" >

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
					<td class="parabg">Filtrar Debito Tipo Vigencia</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<!--Inicio da Tabela Ligação de Esgoto -->
			<table width="100%" border="0">
				<tr>
					<td height="31">
					<table width="100%" border="0" align="center">
						<tr>
							<td height="10" colspan="2">Para manter o(s) valor(res), informe
							os dados abaixo:</td>
							<td width="80" align="right"><html:checkbox property="atualizar"
								value="1" /><strong>Atualizar</strong></td>
						</tr>
						<tr>
							<td height="10" colspan="3">
							<hr>
							</td>
						</tr>
						<tr>
							<td height="10"><strong> Débito Tipo:<font color="#FF0000">*</font></strong></td>
							<td colspan="2" width="330"><span class="style2"> <html:text
								property="debitoTipo" size="5" maxlength="4"
								onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarDebitoTipoVigenciaAction.do', 'debitoTipo','Débito Tipo');return isCampoNumerico(event);" />
							<a
								href="javascript:abrirPopup('exibirPesquisarTipoDebitoAction.do', 400, 800);">
							<img src="/gsan/imagens/pesquisa.gif" alt="Pesquisar" border="0"
								height="21" width="23"></a> <logic:present
								name="debitoTipoEncontrada" scope="session">
								<html:text property="nomeDebitoTipo" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									size="38" />
							</logic:present> <logic:notPresent name="debitoTipoEncontrada"
								scope="session">
								<html:text property="nomeDebitoTipo" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									size="38" />
							</logic:notPresent> <a href="javascript:limparDebitoTipo();"> <img
								border="0" title="Apagar"
								src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>

							</span></td>
						</tr>
						
						<tr>
							<td><strong> Valor do Débito:</strong></td>
							<td colspan="2" width="330" align="right">
							<div align="left"><html:text property="valorDebitoInicial"
								size="12" maxlength="9"
								onkeyup="javascript:formataValorMonetario(this, 11)"
								style="text-align:right;" /><strong> até</strong> <html:text
								property="valorDebitoFinal" size="12" maxlength="9"
								onkeyup="javascript:formataValorMonetario(this, 11)"
								style="text-align:right;" /></div>
						</tr>
						<tr>
							<td><strong> <font color="#FF0000"></font> </strong></td>
							<td width="330" align="right">
							<div align="left"><strong> <font color="#FF0000">*</font> </strong>
							Campos obrigat&oacute;rios</div>
							</td>
						</tr>
						<tr>
							<td><strong> <font color="#FF0000"> 
							<input  type="button"
									name="Submit22" 
									class="bottonRightCol" 
									value="Limpar"
									onClick="javascript:window.location.href='/gsan/exibirFiltrarDebitoTipoVigenciaAction.do?menu=sim'"> 
							<input  type="button"
									name="Submit23" 
									class="bottonRightCol" 
									value="Cancelar"
									onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</font> </strong></td>
							<td colspan="2" align="right"><input type="button" name="Submit2"
								class="bottonRightCol" value="Filtrar" onclick="validaForm();">
							</td>
						</tr>
					</table>
					<p>&nbsp;</p>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<!-- Fim do Corpo - Rômulo Aurélio -->

	<!-- Rodapé -->
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
