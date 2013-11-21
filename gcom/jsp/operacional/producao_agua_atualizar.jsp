<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>


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
	formName="AtualizarProducaoAguaActionForm" />

<SCRIPT LANGUAGE="JavaScript">
<!--

	function validarForm(formulario){
		if(validateAtualizarProducaoAguaActionForm(formulario)){
			submeterFormPadrao(formulario);
		}
  	}
  	
	function limpar(tipo){
		var form = document.forms[0];

		switch (tipo){
        	case 1:
			   form.localidadeID.value = "";
			   form.localidadeDescricao.value = "";
        	   form.localidadeDescricao.style.color = "#000000";

 				//Coloca o foco no objeto selecionado
			   form.localidadeID.focus();
			   break;
		case 2:
		   form.localidadeDescricao.value = "";
           form.localidadeDescricao.style.color = "#000000";

		   //Coloca o foco no objeto selecionado
		   form.localidadeID.focus();
		   break;
	   default:
          break;
		}
	}
	
	
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
	
		if (tipoConsulta == 'localidade') {
    	  form.localidadeID.value = codigoRegistro;
    	  form.localidadeDescricao.value = descricaoRegistro;
    	  form.localidadeDescricao.style.color = "#000000";
		}	
	}
	
//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/atualizarProducaoAguaAction.do" method="post">

	<INPUT TYPE="hidden" name="removerProducaoAgua">
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
					<td class="parabg">Atualizar Produ&ccedil;&atilde;o de &Aacute;gua</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para Atualizar uma produ&ccedil;&atilde;o de &aacute;gua, informe os
					dados abaixo:</td>
				<tr>
					<td width="40"><strong>C&oacute;digo:</strong></td>
					<td><html:hidden property="id" /> <bean:write
						name="AtualizarProducaoAguaActionForm" property="id" /></td>
				</tr>
				<tr>
					<td><strong>M&ecirc;s/Ano de Refer&ecirc;ncia: <font color="#FF0000">*</font></strong></td>
					<td><html:text maxlength="7" property="anoMesReferencia" size="7" onkeyup="mascaraAnoMes(this, event);" /> &nbsp; mm/aaaa
						</td>
				</tr>
				
				<tr>
			        <td><strong>Localidade: <font color="#FF0000">*</font></strong></td>
			        <td colspan="3">
						<html:text property="localidadeID" size="4" maxlength="3" tabindex="15" 
							onkeypress="validaEnterComMensagem(event, 'exibirAtualizarProducaoAguaAction.do?objetoConsulta=1', 'localidadeID','Localidade');"
							onkeyup="limpar(2);"/>
			
						<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do?menu=sim', 250, 495);">
						<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Localidade" /></a>
				
						<logic:present name="corLocalidade">

							<logic:equal name="corLocalidade" value="exception">
								<html:text property="localidadeDescricao" size="42" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
							</logic:equal>

							<logic:notEqual name="corLocalidade" value="exception">
								<html:text property="localidadeDescricao" size="42" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
							</logic:notEqual>

						</logic:present>

						<logic:notPresent name="corLocalidade">

							<logic:empty name="AtualizarProducaoAguaActionForm" property="localidadeID">
								<html:text property="localidadeDescricao" value="" size="42" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
							</logic:empty>
			
							<logic:notEmpty name="AtualizarProducaoAguaActionForm" property="localidadeID">
								<html:text property="localidadeDescricao" size="42" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
							</logic:notEmpty>
				
						</logic:notPresent>

						<a	href="javascript:limpar(1);">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar" /> </a>
					</td>
		    	</tr>
				<tr>
					<td width="30%" height="24"><strong>Volume Produzido: <font
						color="#FF0000">*</font></strong></td>
					<td width="85%"><input type"text" name="volumeProduzido"
						value="<bean:write name="AtualizarProducaoAguaActionForm" property="volumeProduzido" scope="session"/>"
						maxlength="15" size="16" onkeyup="formataValorMonetario(this, 13);"/></td>
				</tr>


				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
					
				</tr>

				<tr>
					<td width="35%" align="left" colspan="2"><input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Voltar"
						onClick="window.history.go(-1)"> <input type="button"
						name="ButtonReset" class="bottonRightCol" value="Desfazer"
						onClick="(document.forms[0]).reset()"> <input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td>&nbsp;</td>
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

