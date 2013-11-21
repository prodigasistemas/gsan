<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page import="gcom.util.ConstantesSistema"%>

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%-- Carrega validações do validator --%>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AtualizarDebitoTipoVigenciaActionForm" />
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
		if (validateAtualizarDebitoTipoVigenciaActionForm(form)) {
			if (form.dataVigenciaInicial.value != '' && form.dataVigenciaFinal.value != ''){
				if(validaData(form.dataVigenciaInicial) && validaData(form.dataVigenciaFinal)){
					if (comparaData(form.dataVigenciaInicial.value, "<", form.dataVigenciaFinal.value )){
		  				submeterFormPadrao(form);
		  			}else{
		  				alert('Informe uma data de vigência final superior a inicial');			
		  			}
		  		}
		  	}else{
		  		submeterFormPadrao(form);
		  	}
		}
	}
	/* Limpa Form */	 
	function limparForm() {
		var form = document.forms[0];

		form.valorServico.value = "";
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
        if (tipoConsulta == 'debitoTipo') {
        	form.debitoTipo.value = codigoRegistro;
        	form.nomeDebitoTipo.value = descricaoRegistro;
        	form.nomeDebitoTipo.style.color = "#000000";
      	}
    }
		
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="">

<html:form action="/atualizarDebitoTipoVigenciaAction.do"
	name="AtualizarDebitoTipoVigenciaActionForm"
	type="gcom.gui.faturamento.debito.AtualizarDebitoTipoVigenciaActionForm"
	method="post" focus="debitoTipo">

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
					<td class="parabg">Atualizar Debito Tipo Vigencia</td>
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
							<td height="10" colspan="3">Para atualizar um  debito tipo vigencia,
							informe os dados abaixo:</td>
						</tr>
						<tr>
							<td height="10" colspan="3">
							<hr>
							</td>
						</tr>
						<tr>
							<td height="10"><strong> Débito Tipo: </strong></td>
							<td width="330"><html:text property="debitoTipo" readonly="true" size="5"
								maxlength="4"
								onkeypress="return isCampoNumerico(event);"
								style="background-color:#EFEFEF; border:0; color:0000" /> <html:text
								property="nomeDebitoTipo" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="38" /></td>
						</tr>
						
						<tr>
							<td><strong> Valor do Débito: <font color="#FF0000">*</font> </strong>
							</td>
							<td colspan="3" align="right" width="330">
								<div align="left">
									<html:text  property="valorDebito" 
												size="15"
												maxlength="15"
												onkeyup="javascript:formataValorMonetario(this, 13)"
												style="text-align:right;" 
												/>
								</div>
							</td>
						</tr>
						<tr> 
				        	<td><strong>Vigência do valor do débito:<font color="#FF0000">*</font> </strong></td>
				        	<td valign="middle">
					            <html:text maxlength="10" property="dataVigenciaInicial" size="10" 
					            	onkeypress="return isCampoNumerico(event);"
					            	onkeyup="javascript:mascaraData(this,event);"/>
					            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" 
					            	onclick="javascript:abrirCalendario('AtualizarDebitoTipoVigenciaActionForm', 'dataVigenciaInicial');" 
					            	width="20" border="0" align="middle" alt="Exibir Calendário" /><strong> a</strong> 
					            <html:text maxlength="10" property="dataVigenciaFinal" size="10" 
					            onkeypress="javascript:mascaraData(this,event);return isCampoNumerico(event);"/>
					            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" 
					            onclick="javascript:abrirCalendario('AtualizarDebitoTipoVigenciaActionForm', 'dataVigenciaFinal')"
					            width="20" border="0" align="middle" alt="Exibir Calendário" /> (dd/mm/aaaa) 
				        	</td>
				        </tr>
				        				        
						<tr>
							<td><strong> <font color="#FF0000"></font> </strong></td>
							<td colspan="3" align="right">
							<div align="left"><strong> <font color="#FF0000">*</font> </strong>
							Campos obrigat&oacute;rios</div>
							</td>
						</tr>
						<tr>
							<td colspan="2"><font color="#FF0000">
							<logic:present name="manter" scope="session">
								<input type="button" 
									   name="ButtonReset" 
									   class="bottonRightCol"
									   value="Voltar"
									   onClick="javascript:window.location.href='/gsan/filtrarDebitoTipoVigenciaAction.do'">
							</logic:present>
							<logic:notPresent name="manter" scope="session">
								<input type="button" 
									   name="ButtonReset" 
									   class="bottonRightCol"
									   value="Voltar"
									   onClick="javascript:window.location.href='/gsan/exibirFiltrarDebitoTipoVigenciaAction.do'">
							</logic:notPresent> 
							<input type="button" 
								   name="ButtonReset"
								   class="bottonRightCol" 
								   value="Desfazer"
								   onClick="window.location.href='<html:rewrite page="/exibirAtualizarDebitoTipoVigenciaAction.do?idRegistroAtualizar=${requestScope.idDebitoTipoVigencia}"/>'">
							<input type="button" 
							       name="ButtonCancelar" 
							       class="bottonRightCol"
								   value="Cancelar"
								   onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</font></td>
							<td align="right">
								<input type="button" 
									   name="Button"
									   class="bottonRightCol" 
									   value="Atualizar"
									   onClick="javascript:validaForm(document.forms[0]);" />
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
