<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="AtualizarContratoDemandaActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
	
<script language="JavaScript">

function validarForm(form){

		if(validateAtualizarContratoDemandaActionForm(form)){
			if (comparaData(form.dataInicioContrato.value, ">", form.dataFimContrato.value)) {
				alert('Data de Fim do Contrato tem que ser superior a Data de Início');
			} else if (form.dataEncerramento.value != '' 
			&& comparaData(form.dataInicioContrato.value, ">", form.dataEncerramento.value)) {
				alert('Data de Encerramento do Contrato tem que ser superior a Data de Início');
			} else { 
				if (form.idMotivoCancelamento.value != '-1' && form.dataEncerramento.value == '') {
					alert('Informe Data de Encerramento');
				} else if (form.dataEncerramento.value != '' && form.idMotivoCancelamento.value == '-1') {
					alert('Informe Motivo do Cancelamento');
				} else {
	    			submeterFormPadrao(form);
	    		}
	    	}
		}
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];
     if(tipoConsulta == 'imovel'){
        form.idImovel.value = codigoRegistro;
        form.inscricaoImovel.value = descricaoRegistro;
        form.inscricaoImovel.style.color = "#000000";
    }
}

function habilitarPesquisaImovel(form) {
	if (form.idImovel.disabled == false) {
		chamarPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 275, 480, '',form.idImovel.value);
	}	
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
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
			}
		}
	}
}

function limparForm(tipo){
     var form = document.forms[0];
    if(tipo == 'imovel')
    {
        form.idImovel.value = "";
        form.inscricaoImovel.value = "";
	}
}

function limparImovelTecla() {
     var form = document.forms[0];
     form.inscricaoImovel.value = "";
}
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/atualizarContratoDemandaAction" method="post"
	name="AtualizarContratoDemandaActionForm"
	type="gcom.gui.faturamento.AtualizarContratoDemandaActionForm"
	onsubmit="return validateAtualizarContratoDemandaActionForm(this);">
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
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Atualizar Contrato de Demanda</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>

					<td colspan="3">Para atualizar o contrato de demanda, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="150"><strong>Número do Contrato:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><strong> 
				       <html:text property="numeroContrato" size="11"
						maxlength="10" /> </strong></td>
				</tr>
				<tr>
					<td><strong>Imóvel:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><strong><html:text property="idImovel" size="10" maxlength="9" onkeyup="javascript:verificaNumeroInteiro(this);limparImovelTecla();"
					onkeypress="javascript:return validaEnter(event, 'exibirAtualzarContratoDemandaAction.do?objetoConsulta=2', 'idImovel');"/>  
							<a href="javascript:habilitarPesquisaImovel(document.forms[0]);" alt="Pesquisar Imóvel">
							<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a></strong>
							<logic:present name="existeImovel">	
    	                  		<logic:equal name="existeImovel" value="exception">							 							  
						        	<html:text property="inscricaoImovel" size="35" maxlength="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				 		        </logic:equal>
							    <logic:notEqual name="existeImovel"	value="exception">
			 					    <html:text property="inscricaoImovel" size="35" maxlength="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>											    
						        </logic:notEqual>
						    </logic:present>

							<logic:notPresent name="existeImovel">
								<logic:empty name="AtualizarContratoDemandaActionForm" property="idImovel">
									<html:text property="inscricaoImovel" size="35" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:empty>
								<logic:notEmpty name="AtualizarContratoDemandaActionForm" property="idImovel">
									<html:text property="inscricaoImovel" size="35"	readonly="true"	style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>
							</logic:notPresent>
						<a href="javascript:limparForm('imovel');"> 
							<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" />						
						</a>
					</td>
				</tr>
				<tr>
					<td><strong>Data de Inicio do Contrato:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><strong> <html:text property="dataInicioContrato" size="10"
						maxlength="10" onkeyup="javascript:mascaraData(this, event);"/> </strong> <img
						border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário"
						onclick="javascript:abrirCalendario('AtualizarContratoDemandaActionForm', 'dataInicioContrato')" />
					dd/mm/aaaa </td>
				</tr>
				<tr>
					<td><strong>Data de Fim do Contrato:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><strong> <html:text property="dataFimContrato" size="10"
						maxlength="10" onkeyup="javascript:mascaraData(this, event);" /> </strong> <img
						border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário"
						onclick="javascript:abrirCalendario('AtualizarContratoDemandaActionForm', 'dataFimContrato')" />
					dd/mm/aaaa </td>
				</tr>
				<tr>
					<td><strong>Data de Encerramento do Contrato:</strong></td>
					<td colspan="2"><strong> <html:text property="dataEncerramento" size="10"
						maxlength="10" onkeyup="javascript:mascaraData(this, event);" /> </strong> <img
						border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário"
						onclick="javascript:abrirCalendario('AtualizarContratoDemandaActionForm', 'dataEncerramento')" />
					dd/mm/aaaa </td>
				</tr>
				<tr>
			  		<td class="style3"><strong>Motivo de Cancelamento:</strong></td>
			  		<td colspan="2">
			  			<html:select property="idMotivoCancelamento" tabindex="4" style="width:200px;">
							<html:option value="-1"> &nbsp; </html:option>
							<html:options collection="colecaoMotivoCancelamento" property="id" labelProperty="descricaoMotivoCancelContrato"/>
						</html:select></td>
			    </tr>				
				<tr>
					<td height="19"><strong> <font color="#FF0000"></font></strong></td>
					<td align="right"><div align="left"><strong><font color="#FF0000">*</font></strong>
					   Campos obrigat&oacute;rios</div>					</td>
				</tr>
				<tr>
							<td colspan="2"><strong><logic:present name="filtrar">
								<logic:present name="inserir">
									<input type="button" name="ButtonReset" class="bottonRightCol"
										value="Voltar"
										onClick="javascript:window.location.href='/gsan/exibirFiltrarContratoDemandaAction.do?menu=sim'">
								</logic:present>
								<logic:notPresent name="inserir">
									<input type="button" name="ButtonReset" class="bottonRightCol"
										value="Voltar"
										onClick="javascript:window.location.href='/gsan/exibirFiltrarContratoDemandaAction.do'">
								</logic:notPresent>
							</logic:present><logic:notPresent name="filtrar">
								<input type="button" name="ButtonReset" class="bottonRightCol"
									value="Voltar"
									onClick="javascript:window.location.href='/gsan/exibirManterContratoDemandaAction.do'">
							</logic:notPresent><input type="button" name="Submit22"
								class="bottonRightCol" value="Desfazer"
								onClick="javascript:window.location.href='/gsan/exibirAtualizarContratoDemandaAction.do?desfazer=sim'">
							<input type="button" name="Submit23" class="bottonRightCol"
								value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</strong></td>
							<td align="right"><gsan:controleAcessoBotao
								name="Button" value="Atualizar"
								onclick="javascript:validarForm(document.forms[0]);"
								url="atualizarContratoDemandaAction.do" /></td>
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

