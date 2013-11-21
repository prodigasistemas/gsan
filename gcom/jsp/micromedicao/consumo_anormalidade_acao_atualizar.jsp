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
	formName="AtualizarConsumoAnormalidadeAcaoActionForm" />

<SCRIPT LANGUAGE="JavaScript">
<!--

function validaForm(form) {
	
	if ( validateAtualizarConsumoAnormalidadeAcaoActionForm( form ) ){
	  	
	  	form.submit();
		
	}   		  

} 

function limparForm() {
	var form = document.AtualizarConsumoAnormalidadeAcaoActionForm;
	form.consumoAnormalidade.value = "-1";
	form.categoria.value = "-1";
    form.imovelPerfil.value = "-1";
    form.leituraAnormalidadeConsumoMes1.value = "-1";
	form.leituraAnormalidadeConsumoMes2.value = "-1";
    form.leituraAnormalidadeConsumoMes3.value = "-1";
    form.numerofatorConsumoMes1.value = "";
	form.numerofatorConsumoMes2.value = "";
    form.numerofatorConsumoMes3.value = "";
    form.indicadorGeracaoCartaMes1.value = "";
	form.indicadorGeracaoCartaMes2.value = "";
    form.indicadorGeracaoCartaMes3.value = "";
    form.idServicoTipoMes1.value = "";
    form.idServicoTipoMes2.value = "";
    form.idServicoTipoMes3.value = "";
    form.desServicoTipoMes1.value = "";
    form.desServicoTipoMes2.value = "";
    form.desServicoTipoMes3.value = "";
    form.solicitacaoTipoMes1 = "-1";
    form.solicitacaoTipoMes2 = "-1";
    form.solicitacaoTipoMes3 = "-1";
    form.solicitacaoTipoEspecificacaoMes1.value = "";
    form.solicitacaoTipoEspecificacaoMes2.value = "";
    form.solicitacaoTipoEspecificacaoMes3.value = "";
    form.descricaoContaMensagemMes1.value = "";
    form.descricaoContaMensagemMes2.value = "";
    form.descricaoContaMensagemMes3.value = "";
    form.indicadorUso.value = "";
		
}

function desabilitaCombo(){
		var form = document.forms[0];
		
		if(form.idServicoTipoMes1.value == null || form.idServicoTipoMes1.value == '' ){
			form.solicitacaoTipoMes1.disabled = true;
			form.solicitacaoTipoMes1.values == '-1';
			form.solicitacaoTipoEspecificacaoMes1.disabled = true;
		}
		if(form.idServicoTipoMes2.value == null || form.idServicoTipoMes2.value == '' ){
			form.solicitacaoTipoMes2.disabled = true;
			form.solicitacaoTipoMes2.values == '-1';
			form.solicitacaoTipoEspecificacaoMes2.disabled = true;
		}
		if(form.idServicoTipoMes3.value == null || form.idServicoTipoMes3.value == '' ){
			form.solicitacaoTipoMes3.disabled = true;
			form.solicitacaoTipoMes3.values == '-1';
			form.solicitacaoTipoEspecificacaoMes3.disabled = true;
		}
		if(form.solicitacaoTipoMes1.value == '-1') {
			form.solicitacaoTipoEspecificacaoMes1.disabled = true;
		}
		if(form.solicitacaoTipoMes2.value == '-1') {
			form.solicitacaoTipoEspecificacaoMes2.disabled = true;
		}
		if(form.solicitacaoTipoMes3.value == '-1') {
			form.solicitacaoTipoEspecificacaoMes3.disabled = true;
		}
		
}  

function reload() {
	var form = document.AtualizarConsumoAnormalidadeAcaoActionForm;
	form.action = "/gsan/exibirAtualizarConsumoAnormalidadeAcaoAction.do";
	form.submit();
}  


function limparBorrachaOrigem(tipo){
	var form = document.forms[0];
	
	switch(tipo){
		case 1: //Tipo de Servico Mes 1

			form.idServicoTipoMes1.value = "";
			form.desServicoTipoMes1.value = "";
			form.solicitacaoTipoMes1.value = "-1";
			form.solicitacaoTipoEspecificacaoMes1.value = "-1";
			break;

		case 2: //Tipo de Servico Mes 2

			form.idServicoTipoMes2.value = "";
			form.desServicoTipoMes2.value = "";
			form.solicitacaoTipoMes2.value = "-1";
			form.solicitacaoTipoEspecificacaoMes2.value = "-1";
			break;

		case 3: //Tipo de Servico Mes 3

			form.idServicoTipoMes3.value = "";
			form.desServicoTipoMes3.value = "";
			form.solicitacaoTipoMes3.value = "-1";
			form.solicitacaoTipoEspecificacaoMes3.value = "-1";
			break;
	}
}


function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,campo){
	if(!campo.disabled){
  		if (objeto == null || codigoObjeto == null){
     		if(tipo == "" ){
      			abrirPopup(url,altura, largura);
     		}else{
	  			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
	 		}
 		}else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
			}
		}
		}
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	var form = document.forms[0];

	if (tipoConsulta == 'idServicoTipoMes1') {
  		
  		form.idServicoTipoMes1.value = codigoRegistro;
  		form.desServicoTipoMes1.value = descricaoRegistro;
  		form.idServicoTipoMes1.style.color = "#000000";
		form.desServicoTipoMes1.style.color = "#000000";
		habilitaSolicitacao();
	}

	if (tipoConsulta == 'idServicoTipoMes2') {
	
  		form.idServicoTipoMes2.value = codigoRegistro;
  		form.desServicoTipoMes2.value = descricaoRegistro;
  		form.idServicoTipoMes2.style.color = "#000000";
		form.desServicoTipoMes2.style.color = "#000000";
		habilitaSolicitacao();
	}

	if (tipoConsulta == 'idServicoTipoMes3') {
		
  		form.idServicoTipoMes3.value = codigoRegistro;
  		form.desServicoTipoMes3.value = descricaoRegistro;
		form.idServicoTipoMes3.style.color = "#000000";
		form.desServicoTipoMes3.style.color = "#000000";
		habilitaSolicitacao();
	}
	
}



function habilitaSolicitacao(){
	var form = document.forms[0];
	
	
	if(form.idServicoTipoMes1.value != '') {
		form.solicitacaoTipoMes1.disabled = false;
	}
	if(form.idServicoTipoMes2.value != '') {
		form.solicitacaoTipoMes2.disabled = false;
	}
	if(form.idServicoTipoMes3.value != '') {
		form.solicitacaoTipoMes3.disabled = false;
	}	
	if(form.solicitacaoTipoMes1.value != '-1') {
		form.solicitacaoTipoEspecificacaoMes1.disabled = false;
	}
	if(form.solicitacaoTipoMes2.value != '-1') {
		form.solicitacaoTipoEspecificacaoMes2.disabled = false;
	}
	if(form.solicitacaoTipoMes3.value != '-1') {
		form.solicitacaoTipoEspecificacaoMes3.disabled = false;
	}
}

//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:desabilitaCombo();habilitaSolicitacao();">

<html:form action="/atualizarConsumoAnormalidadeAcaoAction">

	<INPUT TYPE="hidden" name="removerEndereco">
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

			<td width="600" valign="top" class="centercoltext">

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
					<td class="parabg">Atualizar Consumo Anormalidade e Ação</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para Atualizar um Consumo Anormalidade e Ação, informe
					os dados abaixo:</td>
				<tr>
					<td><strong>Consumo Anormalidade:<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><html:select property="consumoAnormalidade">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoConsumoAnormalidade"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>

				<!-- Categoria -->
				<tr>
					<td><strong>Categoria:</strong></td>
					<td colspan="2" align="left"><html:select property="categoria">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoCategoria"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				
				<tr>
					<td><strong>Perfil do Imóvel:</strong></td>
					<td colspan="2" align="left"><html:select property="imovelPerfil">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoImovelPerfil"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				
				<tr>
					<td><strong>Consumo a Cobrar para o 1º Mes:<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><html:select property="leituraAnormalidadeConsumoMes1">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoLeituraAnormalidadeConsumo"
							labelProperty="descricaoConsumoACobrar" property="id" />
					</html:select></td>
				</tr>
				
				<tr>
					<td><strong>Consumo a Cobrar para o 2º Mês:<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><html:select property="leituraAnormalidadeConsumoMes2">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoLeituraAnormalidadeConsumo"
							labelProperty="descricaoConsumoACobrar" property="id" />
					</html:select></td>
				</tr>
				
				<tr>
					<td><strong>Consumo a Cobrar para o 3º Mês:<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><html:select property="leituraAnormalidadeConsumoMes3">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoLeituraAnormalidadeConsumo"
							labelProperty="descricaoConsumoACobrar" property="id" />
					</html:select></td>
				</tr>
				
				<tr>
					<td><strong>Fator de Consumo para cálculo do 1º Mês:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="numerofatorConsumoMes1" 
						size="5" 
						maxlength="4"
						onkeypress="return isCampoNumerico(event);" /> </span></td>
				</tr>
				<tr>
					<td><strong>Fator de Consumo para cálculo do 2º Mês:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="numerofatorConsumoMes2"
						 size="5" 
						 maxlength="4" 
						 onkeypress="return isCampoNumerico(event);"/> </span></td>
				</tr>
				<tr>
					<td><strong>Fator de Consumo para cálculo do 3º Mês:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="numerofatorConsumoMes3" 
						size="5" 
						maxlength="4" 
						onkeypress="return isCampoNumerico(event);"/> </span></td>
				</tr>
				
			</table>
			<table>
				<tr>
					<td><strong>Indicador de Geração de Carta do 1º Mês:<font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorGeracaoCartaMes1"
						value="1" /> <strong>Gerar Carta <html:radio
						property="indicadorGeracaoCartaMes1" value="2" /> Não Gerar Carta</strong>
					</strong></td>

				</tr>
				<tr>
					<td><strong>Indicador de Geração de Carta do 2º Mês:<font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorGeracaoCartaMes2"
						value="1" /> <strong>Gerar Carta <html:radio
						property="indicadorGeracaoCartaMes2" value="2" /> Não Gerar Carta</strong>
					</strong></td>

				</tr>
				<tr>
					<td><strong>Indicador de Geração de Carta do 3º Mês:<font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorGeracaoCartaMes3"
						value="1" /> <strong>Gerar Carta <html:radio
						property="indicadorGeracaoCartaMes3" value="2" /> Não Gerar Carta</strong>
					</strong></td>

				</tr>
				
				<tr>
					<td><strong>Tipo de Serviço para o 1º Mês:</strong></td>
					<td>	
						<html:text maxlength="4" 
							tabindex="1"
							property="idServicoTipoMes1" 
							size="5"
							onkeypress="javascript:validaEnterComMensagem(event, 'exibirAtualizarConsumoAnormalidadeAcaoAction.do?objetoConsulta=1','idServicoTipoMes1','Tipo de Serviço');return isCampoNumerico(event);"/>
							
						<a href="javascript:chamarPopup('exibirPesquisarTipoServicoAction.do', 'idServicoTipoMes1', null, null, 275, 480, '',document.forms[0].idServicoTipoMes1);">
							<img width="23" 
								height="21" 
								border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Tipo de Serviço" /></a>
								
						<logic:notPresent name="servicoTipoInexistente" scope="request">
							<html:text property="desServicoTipoMes1" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent> 

						<logic:present name="servicoTipoInexistente" scope="request">
							<html:text property="desServicoTipoMes1" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:present>

						<a href="javascript:limparBorrachaOrigem(1);desabilitaCombo()"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Tipo de Serviço para o 2º Mês:</strong></td>
					<td>	
						<html:text maxlength="4" 
							tabindex="1"
							property="idServicoTipoMes2" 
							size="5"
							onkeypress="javascript:validaEnterComMensagem(event, 'exibirAtualizarConsumoAnormalidadeAcaoAction.do?objetoConsulta=2','idServicoTipoMes2','Tipo de Serviço');return isCampoNumerico(event);habilitaSolicitacao();"/>
							
						<a href="javascript:chamarPopup('exibirPesquisarTipoServicoAction.do', 'idServicoTipoMes2', null, null, 275, 480, '',document.forms[0].idServicoTipoMes2);">
							<img width="23" 
								height="21" 
								border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Tipo de Serviço" /></a>
								
						<logic:notPresent name="servicoTipoInexistente" scope="request">
							<html:text property="desServicoTipoMes2" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent> 

						<logic:present name="servicoTipoInexistente" scope="request">
							<html:text property="desServicoTipoMes2" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:present>

						<a href="javascript:limparBorrachaOrigem(2);desabilitaCombo();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Tipo de Serviço para o 3º Mês:</strong></td>
					<td>	
						<html:text maxlength="4" 
							tabindex="1"
							property="idServicoTipoMes3" 
							size="5"
							onkeypress="javascript:validaEnterComMensagem(event, 'exibirAtualizarConsumoAnormalidadeAcaoAction.do?objetoConsulta=3','idServicoTipoMes3','Tipo de Serviço');return isCampoNumerico(event);habilitaSolicitacao();"/>
							
						<a href="javascript:chamarPopup('exibirPesquisarTipoServicoAction.do', 'idServicoTipoMes3', null, null, 275, 480, '',document.forms[0].idServicoTipoMes3);">
							<img width="23" 
								height="21" 
								border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Tipo de Serviço" /></a>
								
						<logic:notPresent name="servicoTipoInexistente" scope="request">
							<html:text property="desServicoTipoMes3" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent> 

						<logic:present name="servicoTipoInexistente" scope="request">
							<html:text property="desServicoTipoMes3" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:present>

						<a href="javascript:limparBorrachaOrigem(3);desabilitaCombo()"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Tipo de Solicitação para o 1º Mês:</strong></td>
					<td colspan="2" align="left"><html:select property="solicitacaoTipoMes1" 
						 onchange="javascript:habilitaSolicitacao();desabilitaCombo();reload();">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoSolicitacaoTipo"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				
				<tr>
					<td><strong>Tipo de Solicitação para o 2º Mês:</strong></td>
					<td colspan="2" align="left"><html:select property="solicitacaoTipoMes2"
						 onchange="javascript:habilitaSolicitacao();desabilitaCombo();reload();">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoSolicitacaoTipo"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				
				<tr>
					<td><strong>Tipo de Solicitação para o 3º Mês:</strong></td>
					<td colspan="2" align="left"><html:select property="solicitacaoTipoMes3"
						 onchange="javascript:habilitaSolicitacao();desabilitaCombo();reload();">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoSolicitacaoTipo"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				
				<tr>
					<td><strong>Tipo de Especificação para o 1º Mês:</strong></td>
					<td colspan="2" align="left"><html:select property="solicitacaoTipoEspecificacaoMes1"
						 onchange="javascript:habilitaSolicitacao();desabilitaCombo();">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoSolicitacaoTipoEspecificacaoMes1"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				
				<tr>
					<td><strong>Tipo de Especificação para o 2º Mês:</strong></td>
					<td colspan="2" align="left"><html:select property="solicitacaoTipoEspecificacaoMes2"
						 onchange="javascript:habilitaSolicitacao();desabilitaCombo();">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoSolicitacaoTipoEspecificacaoMes2"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				
				<tr>
					<td><strong>Tipo de Especificação para o 3º Mês:</strong></td>
					<td colspan="2" align="left"><html:select property="solicitacaoTipoEspecificacaoMes3"
						 onchange="javascript:habilitaSolicitacao();desabilitaCombo();">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoSolicitacaoTipoEspecificacaoMes3"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
			</table>		
		 	<table>		
					
				<tr>
					<td><strong>Mensagem da Conta no 1º Mês:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricaoContaMensagemMes1"
						size="52" 
						maxlength="120" 
						/> </span></td>
				</tr>
				
				<tr>
					<td><strong>Mensagem da Conta no 2º Mês:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricaoContaMensagemMes2" 
						size="52" 
						maxlength="120" 
						/> </span></td>
				</tr>
				
				<tr>
					<td><strong>Mensagem da Conta no 3º Mês:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricaoContaMensagemMes3" 
						size="52" 
						maxlength="120" 
						/> </span></td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Uso:<font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorUso" value="1" />
					<strong>Ativo <html:radio property="indicadorUso" value="2" />
					Inativo</strong> </strong></td>

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
							onClick="javascript:window.location.href='/gsan/exibirFiltrarConsumoAnormalidadeAcao.do?menu=sim'">
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
					  <gsan:controleAcessoBotao name="Button" 
											  	value="Atualizar" 
											  	onclick="javascript:validaForm(document.forms[0]);" 
											  	url="atualizarConsumoAnormalidadeAcaoAction.do"/>
					  <%-- <input type="button" name="Button" class="bottonRightCol" value="Atualizar" onClick="javascript:validarForm(document.forms[0]);" /> --%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>

