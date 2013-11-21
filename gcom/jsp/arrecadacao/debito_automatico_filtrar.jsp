<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="gcom.util.ConstantesSistema"%>

<html:html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="FiltrarDebitoAutomaticoActionForm" dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(formulario){
		if(validateFiltrarDebitoAutomaticoActionForm(formulario)){
    		submeterFormPadrao(formulario);
		}
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];

		if (tipoConsulta == 'banco') {
	      form.bancoID.value = codigoRegistro;
		  form.bancoDescricao.value = descricaoRegistro;
		  form.bancoDescricao.style.color = "#000000";
		  
	      form.agenciaCodigo.value = "";
		  form.agenciaDescricao.value = "";
		  
		  habilitarDesabilitarAgencia();
		}
		if (tipoConsulta == 'agenciaBancaria') {
	      form.agenciaCodigo.value = codigoRegistro;
		  form.agenciaDescricao.value = descricaoRegistro;
		  form.agenciaDescricao.style.color = "#000000";		
		  
		  form.action = 'exibirFiltrarDebitoAutomaticoAction.do?objetoConsulta=2';
		  form.submit();
		}
	}
	
	function habilitarDesabilitarAgencia(){
  	  var form = document.forms[0];
  	  
  	  form.agenciaCodigo.disabled = form.bancoID.value == null || form.bancoID.value == '';
  	  form.imgLimparAgencia.disabled = form.bancoID.value == null || form.bancoID.value == '';
  	  form.imgPesquisarAgencia.disabled = form.bancoID.value == null || form.bancoID.value == '';
  	  
  	  
  	  
	}
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg){

		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		}
		else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}
			else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisaBairro=" + "/exibirInserirQuadra.do", altura, largura);
			}
		}
	}
	
	function limpar(tipo){
		var form = document.forms[0];

		switch (tipo){
			case 1:
				form.bancoID.value = "";
			  	form.bancoDescricao.value = "";
			  	form.bancoDescricao.style.color = "#000000";
				form.agenciaCodigo.value = "";
			  	form.agenciaDescricao.value = "";			  	
			    //Coloca o foco no objeto selecionado
			  	form.bancoID.focus();
			  	habilitarDesabilitarAgencia();
			  	break;
			case 2:
				form.agenciaCodigo.value = "";
			  	form.agenciaDescricao.value = "";
			  	form.agenciaDescricao.style.color = "#000000";
			   //Coloca o foco no objeto selecionado
			  	form.agenciaCodigo.focus();
			  	break;
		}
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5" onLoad="habilitarDesabilitarAgencia();">
<html:form action="/filtrarDebitoAutomaticoAction"
	name="FiltrarDebitoAutomaticoActionForm"
	type="gcom.gui.arrecadacao.FiltrarDebitoAutomaticoActionForm"
	method="post"
	onsubmit="return validateFiltrarDebitoAutomaticoActionForm(this);">

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


			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Filtrar D&eacute;bito Autom&aacute;tico</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para filtrar o(s) D&eacute;bito(s) Autom&aacute;tico(s), informe os dados
					abaixo:</td>					
				</tr>
				<tr>
					<td><strong>Matr&iacute;cula:</strong></td>
					<td><html:text property="matricula" size="9" maxlength="9" /></td>
					<td>&nbsp;</td>
				</tr>
				
				
				<tr>
					<td><strong>Banco:</strong></td>
					<td><html:text property="bancoID" size="5" maxlength="3"
						tabindex="16"
						onkeypress="validaEnterComMensagem(event, 'exibirFiltrarDebitoAutomaticoAction.do?objetoConsulta=1', 'bancoID','Banco');" />

					<a
						href="javascript:abrirPopup('exibirPesquisarTabelaAuxiliarAbreviadaAction.do?tela=banco',275,480);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Banco" /></a> <logic:present
						name="corBanco">

						<logic:equal name="corBanco" value="exception">
							<html:text property="bancoDescricao" size="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corBanco" value="exception">
							<html:text property="bancoDescricao" size="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> <logic:notPresent name="corBanco">

						<logic:empty name="FiltrarDebitoAutomaticoActionForm"
							property="bancoID">
							<html:text property="bancoDescricao" size="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarDebitoAutomaticoActionForm"
							property="bancoID">
							<html:text property="bancoDescricao" size="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent> <a href="javascript:limpar(1);"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a></td>
				</tr>
				
				<tr>
					<td><strong>Ag&ecirc;ncia: </strong></td>
					<td><html:text property="agenciaCodigo" size="5" maxlength="5"
						tabindex="16"
						onkeypress="validaEnterComMensagemAceitaZERO(event, 'exibirFiltrarDebitoAutomaticoAction.do?objetoConsulta=2', 'agenciaCodigo', 'Agência');" />

					<a
						href="javascript:chamarPopup('exibirPesquisarAgenciaBancariaAction.do?limparForm=sim', 'agenciaCodigo', 'bancoID', document.forms[0].bancoID.value, 275, 480, 'Informe o Banco');">
					
					<img width="23" height="21" border="0" name="imgPesquisarAgencia"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Agência" /></a> <logic:present
						name="corAgencia">

						<logic:equal name="corAgencia" value="exception">
							<html:text property="agenciaDescricao" size="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corAgencia" value="exception">
							<html:text property="agenciaDescricao" size="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> <logic:notPresent name="corAgencia">

						<logic:empty name="FiltrarDebitoAutomaticoActionForm"
							property="agenciaCodigo">
							<html:text property="agenciaDescricao" size="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarDebitoAutomaticoActionForm"
							property="agenciaCodigo">
							<html:text property="agenciaDescricao" size="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent> <a href="javascript:limpar(2);"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" name="imgLimparAgencia"/> </a></td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3"><input name="limpar" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="window.location.href='/gsan/exibirFiltrarDebitoAutomaticoAction.do?menu=sim'">
					<input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
					<td align="right" colspan="2"><input type="button" name="filtrar"
						class="bottonRightCol" value="Filtrar"
						onClick="javascript:validarForm(document.forms[0])" /></td>
				</tr>
			</table>

			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
