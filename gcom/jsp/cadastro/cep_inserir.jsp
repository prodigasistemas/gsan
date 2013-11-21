<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.util.Util" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="InserirCepActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">

	function validarForm(){
		var form = document.forms[0];
		if (validateInserirCepActionForm(form)){

			if(form.codigo.lenght > 8){
				alert('O código do CEP deve possuir obrigatoriamente 8 dígitos'); 
			} else {
				submeterFormPadrao(form);
			}
		}
   }
   
	function formataCep(e,src,mask) {
	    if(window.event) { _TXT = e.keyCode; } 
	    else if(e.which) { _TXT = e.which; }
	    if(_TXT > 47 && _TXT < 58) { 
	  var i = src.value.length; var saida = mask.substring(0,1); var texto = mask.substring(i)
	  if (texto.substring(0,1) != saida) { src.value += texto.substring(0,1); } 
	     return true; } else { if (_TXT != 8) { return false; } 
	  else { return true; }
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
	
		   //Coloca o foco no objeto selecionado
		   form.municipioId.focus();
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

</script>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('descricao');">
<html:form action="/inserirCepAction.do"
	name="InserirCepActionForm"
	type="gcom.gui.cadastro.InserirCepActionForm"
	method="post"
	onsubmit="return validateInserirCepActionForm(this);">

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
	
	
				<td valign="top" class="centercoltext">
				<table>
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
						<td width="11"><img border="0" src="imagens/parahead_left.gif" />
						</td>
						<td class="parabg">Inserir CEP</td>
						<td width="11" valign="top"><img border="0"
							src="imagens/parahead_right.gif" /></td>
					</tr>
				</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->

				<table width="100%" border="0">
					<tr>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="2">Para inserir o CEP, informe os
						dados abaixo:</td>
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
						<td height="30"><strong>CEP: <font
							color="#FF0000">*</font></strong></td>
						<td colspan="2">
							<html:text property="codigo" onkeypress="return formataCep(event,this,'##.###-###');" size="10" maxlength="10"/>
						</td>	
					</tr>
							
					<tr>
						<td width="140"><strong>Munic&iacute;pio: <font
						color="#FF0000">*</font></strong></td>
						<td colspan="3"><html:text property="municipioId" size="4"
							maxlength="4" tabindex="15"
							onkeypress="limparForm(3);validaEnterComMensagem(event, 'exibirInserirCepAction.do?objetoConsulta=1', 'municipioId','municipio');"
							onkeyup="javascript:limparForm(2);"/> 
							<a href="javascript:abrirPopup('exibirPesquisarMunicipioAction.do?menu=sim',275,480);">
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

							<logic:empty name="InserirCepActionForm"
								property="municipioId">
								<html:text property="municipio"
									value="" size="45" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:empty>
							<logic:notEmpty name="InserirCepActionForm"
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
						onkeypress="validaEnterDependencia(event, 'exibirInserirCepAction.do?objetoConsulta=2', this, document.forms[0].municipioId.value, 'Município');"
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

						<logic:empty name="InserirCepActionForm"
							property="bairroId">
							<html:text property="bairro"
								value="" size="45" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="InserirCepActionForm"
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
					<td>&nbsp;</td>
				</tr>	

				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campos Obrigatórios</td>
				</tr>
				<tr>
					<td colspan="2"><input name="Button" type="button"
						class="bottonRightCol" value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirInserirCepAction.do?menu=sim"/>'"> <input name="Button"
						type="button" class="bottonRightCol" value="Cancelar" align="left"
						onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
					<td width="12">&nbsp;</td>
					<td width="53" height="24" align="right"><input type="button"
						name="Button2" class="bottonRightCol" value="Inserir"
						onClick="javascript:validarForm();" /></td>
					<td width="12">&nbsp;</td>
				</tr>
				
				</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>

</html:form>

</body>
</html:html>