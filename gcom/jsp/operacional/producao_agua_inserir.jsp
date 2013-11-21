<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

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
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="InserirProducaoAguaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">

	function validarForm(){
		var form = document.forms[0];
		if (validateInserirProducaoAguaActionForm(form)){
			submeterFormPadrao(form);
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
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg){

		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		}
		else {
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}
			else {
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
			}
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
</script>

</head>

<body leftmargin="5" topmargin="5">
<html:form action="/inserirProducaoAguaAction.do"
	name="InserirProducaoAguaActionForm"
	type="gcom.gui.operacional.InserirProducaoAguaActionForm"
	method="post"
	onsubmit="return validateInserirProducaoAguaActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
			<td width="150" valign="top" class="leftcoltext">
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
					<td class="parabg">Inserir Produ&ccedil;&atilde;o de &Aacute;gua</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para inserir a produ&ccedil;&atilde;o de &Aacute;gua, informe os 
					dados abaixo:</td>
				</tr>
				<tr>
					<td><strong>Mês/Ano de Refer&ecirc;ncia: <font color="#FF0000">*</font></strong></td>
					<td><html:text maxlength="7" property="anoMesReferencia" size="7"
						onkeyup="mascaraAnoMes(this, event);" /> &nbsp; mm/aaaa</td>
				</tr>
				<tr>
					
    				<td width="132"><strong>Localidade: <font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="localidadeID" size="5"
						maxlength="3" tabindex="15"
						onkeypress="validaEnterComMensagem(event, 'exibirInserirProducaoAguaAction.do?objetoConsulta=1', 'localidadeID','Localidade');"
						onkeyup="limpar(2);" /> <a
						href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do?menu=sim',275,480);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Localidade" /></a> <logic:present
						name="corLocalidade">

						<logic:equal name="corLocalidade" value="exception">
							<html:text property="localidadeDescricao" size="42"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corLocalidade" value="exception">
							<html:text property="localidadeDescricao" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> <logic:notPresent name="corLocalidade">

						<logic:empty name="InserirProducaoAguaActionForm"
							property="localidadeID">
							<html:text property="localidadeDescricao"
								value="" size="45" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="InserirProducaoAguaActionForm"
							property="localidadeID">
							<html:text property="localidadeDescricao" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:notEmpty>

					</logic:notPresent> <a href="javascript:limpar(1);"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a></td>
				</tr>
				
				<tr>
					<td><strong>Volume Produzido: <font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:text property="volumeProduzido" size="16"
						maxlength="15" onkeyup="formataValorMonetario(this, 13);"/> </strong></td>
				</tr>


				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campos Obrigatórios</td>
				</tr>
				<tr>
					<td colspan="2"><input name="Button" type="button"
						class="bottonRightCol" value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirInserirProducaoAguaAction.do?menu=sim"/>'"> <input name="Button"
						type="button" class="bottonRightCol" value="Cancelar" align="left"
						onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
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
