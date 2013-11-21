<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AtualizarResolucaoDiretoriaActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
<!-- Begin

	function validarForm(form){
				
		if(testarCampoValorZero(document.AtualizarResolucaoDiretoriaActionForm.numero, 'Número da RD') 
		&& testarCampoValorZero(document.AtualizarResolucaoDiretoriaActionForm.assunto, 'Assunto da RD')) { 		
			if(validateAtualizarResolucaoDiretoriaActionForm(form)){			
				if(validaTodosRadioButton(form)){
	   				submeterFormPadrao(form);			
				}
			}
		}	
	}
	
	
	function validaRadioButton(nomeCampo,mensagem){
		
		var alerta = "";
		if(!nomeCampo[0].checked && !nomeCampo[1].checked){
			alerta = "Informe " + mensagem +".";
		}
		return alerta;
   	}
   
  	function validaTodosRadioButton(){
		
		var form = document.forms[0];
		var mensagem = "";
		
		if(validaRadioButton(form.indicadorParcelamentoUnico,"Indicador de Parcelamento Único.") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorParcelamentoUnico,"Indicador de Parcelamento Único.")+"\n";
		}
		
		if(validaRadioButton(form.indicadorUtilizacaoLivre,"Indicador de Utilização Livre.") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorUtilizacaoLivre,"Indicador de Utilização Livre.")+"\n";
		}
		
		if(validaRadioButton(form.indicadorDescontoSancoes,"Indicador de Descontos e Sanções.") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorDescontoSancoes,"Indicador de Descontos e Sanções.")+"\n";
		}
		
		if(mensagem != ""){
			alert(mensagem);
			return false;
		}else{
			return true;
		}
   	} 
	
	function habilitacaoIdParcelasEmAtraso(indicadorParcelasEmAtraso){

		var form = document.forms[0];
			if (indicadorParcelasEmAtraso[1].checked){
				form.idParcelasEmAtraso.disabled = true;
				form.idParcelasEmAtraso.value = ""; 
			}else{
				form.idParcelasEmAtraso.disabled = false;
			}
   	}
   	
   	function habilitacaoIdParcelamentoEmAndamento(indicadorParcelamentoEmAndamento){

		var form = document.forms[0];
			if (indicadorParcelamentoEmAndamento[1].checked){
				form.idParcelamentoEmAndamento.disabled = true;
				form.idParcelamentoEmAndamento.value = ""; 
			}else{
				form.idParcelamentoEmAndamento.disabled = false;
			}
   	}
	

-->
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/atualizarResolucaoDiretoriaAction"
	name="AtualizarResolucaoDiretoriaActionForm"
	type="gcom.gui.cobranca.AtualizarResolucaoDiretoriaActionForm"
	method="post">

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
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Atualizar Resolu&ccedil;&atilde;o de Diretoria</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para atualizar uma resolução de diretoria, informe
					os dados abaixo:</td>
				</tr>
				<tr>
					<td width="218"><strong>N&uacute;mero RD:</strong></td>
					<td width="393"><html:text property="numero" size="15"
						maxlength="15" readonly="true"
						style="background-color:#EFEFEF; border:0;" /></td>
				</tr>
				<tr>
					<td><strong>Assunto RD:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="assunto" size="50" maxlength="50" tabindex="1" /></td>
				</tr>
				<tr>
					<td height="25"><strong>Data In&iacute;cio Vig&ecirc;ncia RD:<font
						color="#FF0000">*</font></strong></td>
					<td align="right">
					<div align="left"><html:text property="dataInicio" size="10"
						maxlength="10" onkeyup="mascaraData(this, event);" /> <img
						border="0" tabindex="2"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário"
						onclick="javascript:abrirCalendario('AtualizarResolucaoDiretoriaActionForm', 'dataInicio')" />
					dd/mm/aaaa</div>
					</td>
				</tr>
				<tr>
					<td height="25"><strong>Data T&eacute;rmino Vig&ecirc;ncia RD:</strong></td>
					<td align="right">
					<div align="left"><html:text property="dataFim" size="10" tabindex="3"
						maxlength="10" onkeyup="mascaraData(this, event);" /> <img
						border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário"
						onclick="javascript:abrirCalendario('AtualizarResolucaoDiretoriaActionForm', 'dataFim')" />
					dd/mm/aaaa</div>
					</td>
				</tr>
				<tr>
					<td><strong>Parcelamento &Uacute;nico? <font color="#FF0000">*</font></strong></td>
					<td><strong> 
					<html:radio property="indicadorParcelamentoUnico" value="1" tabindex="4"/> <strong>Sim 
					<html:radio	property="indicadorParcelamentoUnico" value="2" tabindex="5" /> N&atilde;o</strong> </strong></td>
				</tr>
				<tr>
					<td><strong>Utiliza&ccedil;&atilde;o Livre? <font
						color="#FF0000">*</font></strong></td>
					<td><strong> 
						<html:radio property="indicadorUtilizacaoLivre"	value="1" tabindex="6" /> <strong>Sim 
						<html:radio	property="indicadorUtilizacaoLivre" value="2" tabindex="7" /> N&atilde;o</strong> </strong></td>
				</tr>
				<tr>
					<td><strong>Descontos e San&ccedil;&otilde;es? <font
						color="#FF0000">*</font></strong></td>
					<td><strong> 
						<html:radio property="indicadorDescontoSancoes"	value="1" tabindex="8"/> <strong>Sim 
						<html:radio	property="indicadorDescontoSancoes"	value="2" tabindex="9"/> N&atilde;o</strong> </strong></td>
				</tr>
				<tr>
					<td><strong>Indicador de Negociação só a Vista: <font
						color="#FF0000">*</font></strong></td>
					<td><strong> 
						<html:radio property="indicadorNegociacaoSoAVista"	value="1" tabindex="8"/> <strong>Sim 
						<html:radio	property="indicadorNegociacaoSoAVista"	value="2" tabindex="9"/> N&atilde;o</strong> </strong></td>
				</tr>
				<tr>
					<td><strong>Indicador de Desconto só em Conta para Pagamento a Vista: <font
						color="#FF0000">*</font></strong></td>
					<td><strong> 
						<html:radio property="indicadorDescontoSoEmContaAVista"	value="1" tabindex="10"/> <strong>Sim 
						<html:radio	property="indicadorDescontoSoEmContaAVista"	value="2" tabindex="11"/> N&atilde;o</strong> </strong></td>
				</tr>

				<tr>
					<td><strong>Indicador de Parcelamento para Loja Virtual: <font color="#FF0000">*</font></strong></td>
					<td>
						<strong>
							<html:radio property="indicadorParcelamentoLojaVirtual" value="1" /> 
						<strong>Sim 
							<html:radio property="indicadorParcelamentoLojaVirtual" value="2" /> Não
						</strong> 
						</strong>
					</td>
				</tr>

				<tr>
					<td><strong>Indicador de Parcelas em Atraso: <font color="#FF0000">*</font></strong></td>
					<td><strong> 
						<html:radio property="indicadorParcelasEmAtraso" value="1" tabindex="12" onclick="habilitacaoIdParcelasEmAtraso(document.forms[0].indicadorParcelasEmAtraso);"/> <strong>Sim 
						<html:radio property="indicadorParcelasEmAtraso" value="2" tabindex="13" onclick="habilitacaoIdParcelasEmAtraso(document.forms[0].indicadorParcelasEmAtraso);"/> N&atilde;o</strong> 
					</strong></td>
				</tr>
				
				<tr>
					<td width="218"><strong>RD Parcelas em Atraso:</strong></td>
					<td width="393">
						<html:text property="idParcelasEmAtraso" size="10" maxlength="10" tabindex="14" onkeyup="verificaNumeroInteiro(this);" />
					</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Parcelamento em Andamento: <font color="#FF0000">*</font></strong></td>
					<td><strong> 
						<html:radio property="indicadorParcelamentoEmAndamento" value="1" tabindex="15" onclick="habilitacaoIdParcelamentoEmAndamento(document.forms[0].indicadorParcelamentoEmAndamento);"/> <strong>Sim 
						<html:radio property="indicadorParcelamentoEmAndamento" value="2" tabindex="16" onclick="habilitacaoIdParcelamentoEmAndamento(document.forms[0].indicadorParcelamentoEmAndamento);"/> N&atilde;o</strong> 
					</strong></td>
				</tr>
				
				<tr>
					<td width="218"><strong>RD Parcelamento em Andamento:</strong></td>
					<td width="393">
						<html:text property="idParcelamentoEmAndamento" size="10" maxlength="10" tabindex="17" onkeyup="verificaNumeroInteiro(this);" />
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
					<td colspan="2"><font color="#FF0000"> <logic:present
						name="inserir" scope="request">
						<input type="button" name="ButtonReset" class="bottonRightCol"
							value="Voltar"
							onClick="javascript:window.location.href='/gsan/exibirFiltrarResolucaoDiretoriaAction.do?menu=sim'">
					</logic:present><logic:notPresent name="inserir" scope="request">
						<input type="button" name="ButtonReset" class="bottonRightCol"
							value="Voltar" onClick="javascript:history.back();">
					</logic:notPresent><input type="button" name="ButtonReset"
						class="bottonRightCol" value="Desfazer"
						onClick="(document.forms[0]).reset()"> <input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</font></td>
					<td align="right">
					  <gsan:controleAcessoBotao name="Button" value="Atualizar" onclick="javascript:validarForm(document.forms[0]);" url="atualizarResolucaoDiretoriaAction.do"/>
					  <%-- <input type="button" name="Button" class="bottonRightCol" value="Atualizar" onClick="javascript:validarForm(document.forms[0]);" /> --%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
	<script language="JavaScript">
	<!--
	habilitacaoIdParcelasEmAtraso(document.forms[0].indicadorParcelasEmAtraso);
	habilitacaoIdParcelamentoEmAndamento(document.forms[0].indicadorParcelamentoEmAndamento);
	//-->
	</script>
</body>
</html:html>
