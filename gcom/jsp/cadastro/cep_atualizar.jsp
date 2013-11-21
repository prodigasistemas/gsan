<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.util.ConstantesSistema"%>


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
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AtualizarCepActionForm" />

<SCRIPT LANGUAGE="JavaScript">
<!--

function validarForm(formulario){
	if(validateAtualizarCepActionForm(formulario)){
		submeterFormPadrao(formulario);
	}
 }
 
 	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];
			if (tipoConsulta == 'municipio') {
			     form.municipioId.value = codigoRegistro;
			 	 form.municipio.value = descricaoRegistro;
		  	     form.municipio.style.color = "#000000";
			}
			if (tipoConsulta == 'bairro') {
			     form.bairroId.value = codigoRegistro;
			 	 form.bairro.value = descricaoRegistro;
		  	     form.bairro.style.color = "#000000";
			}
	}
	
	function limparForm(tipo){
		var form = document.forms[0];
		
		switch (tipo){
			case 1:
			   form.municipioId.value = "";
		  	   form.municipio.value = "";
	           form.municipio.style.color = "#000000";
	
	 		   //Coloca o foco no objeto selecionado
		       form.municipioId.focus();
		       break;
		   case 2:
		   		form.municipio.value = "";
	          form.municipio.style.color = "#000000";
	
		   break;
		   case 3:
			   form.bairroId.value = "";
		  	   form.bairro.value = "";
	           form.bairro.style.color = "#000000";
	
		       break;
		   case 4:
		   form.bairro.value = "";
	       form.bairro.style.color = "#000000";
	
		   break;
		}
}

//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/atualizarCepAction.do" method="post">

	<INPUT TYPE="hidden" name="removerCep">
	<INPUT TYPE="hidden" name="limparCampos" id="limparCampos">


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
					<td class="parabg">Atualizar CEP</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para Atualizar um CEP, informe os
					dados abaixo:</td>

				<tr>
					<td><strong>CEP:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="codigo" size="10" maxlength="10" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" /> </span></td>
				</tr>
				
				<tr>
					<td><strong>Tipo de CEP:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="cepTipo" tabindex="4">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoCepTipo"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>

				<tr>
						<td width="180"><strong>Munic&iacute;pio: <font
						color="#FF0000">*</font></strong></td>
						<td colspan="2"><html:text property="municipioId" size="5"
							maxlength="3" tabindex="15"
							onkeypress="limparForm(3);validaEnterComMensagem(event, 'exibirAtualizarCepAction.do?objetoConsulta=1', 'municipioId','municipio');"
							onkeyup="javascript:limparForm(2);"/> 
							<a href="javascript:abrirPopup('exibirPesquisarMunicipioAction.do',275,480);">
						<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Munic&iacute;pio" /></a> <logic:present
							name="corMunicipio">

							<logic:equal name="corMunicipio" value="exception">
								<html:text property="municipio" size="42"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:equal>
	
							<logic:notEqual name="corMunicipio" value="exception">
								<html:text property="municipio" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>

							</logic:present> <logic:notPresent name="corMunicipio">

							<logic:empty name="AtualizarCepActionForm"
								property="municipioId">
								<html:text property="municipio"
									value="" size="45" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:empty>
							<logic:notEmpty name="AtualizarCepActionForm"
								property="municipioId">
								<html:text property="municipio" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
	
						</logic:notPresent> <a href="javascript:limparForm(1);"> <img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /> </a>
					</td>
				</tr>
				
				<tr>
					
				    <td width="140"><strong>Bairro: <font
						color="#FF0000">*</font></strong></td>
					<td colspan="3"><html:text property="bairroId" size="4"
						maxlength="4" tabindex="15"
						onkeypress="validaEnterDependencia(event, 'exibirAtualizarCepAction.do?objetoConsulta=2', this, document.forms[0].municipioId.value, 'Município');"
						onkeyup="limparForm(4);"/> 
						<a href="javascript:abrirPopup('exibirPesquisarBairroAction.do?idMunicipio='+document.forms[0].municipioId.value,275,480);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Bairro" /></a> <logic:present
						name="corBairro">

						<logic:equal name="corBairro" value="exception">
							<html:text property="bairro" size="42"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:equal>

						<logic:notEqual name="corBairro" value="exception">
							<html:text property="bairro" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> <logic:notPresent name="corBairro">

						<logic:empty name="AtualizarCepActionForm"
							property="bairroId">
							<html:text property="bairro"
								value="" size="45" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="AtualizarCepActionForm"
							property="bairroId">
							<html:text property="bairro" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent> <a href="javascript:limparForm(3);"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a></td>
				</tr>

				<tr>
					<td><strong>Tipo de Logradouro:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="logradouroTipo" tabindex="4">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoLogradouroTipo" property="descricaoTipoLogradouro" labelProperty="descricaoTipoLogradouro" />
					</html:select></td>
				</tr>
				
				<tr>
					<td height="30"><strong>Nome do Logradouro: <font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="logradouro" maxlength="50"
						size="50"/><br>
					</td>
				</tr>

				<tr>
					<td><strong>Indicador de uso: <font	color="#FF0000">*</font></strong></td>
					<td><html:radio property="indicadorUso" value="1" tabindex="5" /><strong>Ativo
					<html:radio property="indicadorUso" value="2" tabindex="6" />Inativo</strong>
					</td>
				</tr>

				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
					
				</tr>

				<tr>
					<td width="40%" align="left" colspan="2"><input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Voltar"
						onClick="window.history.go(-1)"> <input type="button"
						name="ButtonReset" class="bottonRightCol" value="Desfazer"
						onClick="(document.forms[0]).reset()"> <input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right"><input type="button"
						onClick="javascript:validarForm(document.forms[0]);"
						name="botaoAtualizar" class="bottonRightCol" value="Atualizar"></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>

