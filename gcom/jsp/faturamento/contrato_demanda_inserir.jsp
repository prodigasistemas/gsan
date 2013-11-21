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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="InserirContratoDemandaActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
	
<script language="JavaScript">

function validarForm(form){

		if(validateInserirContratoDemandaActionForm(form)){
			if (comparaData(form.dataInicioContrato.value, ">", form.dataFimContrato.value)) {
				alert('Data de Fim do Contrato tem que ser superior a Data de Início');
			} else {
	    		submeterFormPadrao(form);
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

<html:form action="/inserirContratoDemandaAction" method="post"
	name="InserirContratoDemandaActionForm"
	type="gcom.gui.faturamento.InserirContratoDemandaActionForm"
	onsubmit="return validateInserirContratoDemandaActionForm(this);">
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
					<td class="parabg">Inserir Contrato de Demanda</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>

					<td colspan="2">Para inserir o contrato de demanda, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="139" height="22"><strong>Número do Contrato:<font
						color="#FF0000">*</font></strong></td>
					<td width="388"><strong> 
				       <html:text property="numeroContrato" size="11"
						maxlength="10" onkeyup="javascript:verificaNumeroInteiro(this);" /> </strong></td>
				</tr>
				<tr>
					<td><strong>Imóvel:<font color="#FF0000">*</font></strong></td>
					<td><strong><html:text property="idImovel" size="10" maxlength="9" onkeyup="javascript:verificaNumeroInteiro(this);limparImovelTecla();"
					onkeypress="javascript:return validaEnter(event, 'exibirInserirContratoDemandaAction.do?objetoConsulta=2', 'idImovel');"/>  
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
								<logic:empty name="InserirContratoDemandaActionForm" property="idImovel">
									<html:text property="inscricaoImovel" size="35" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:empty>
								<logic:notEmpty name="InserirContratoDemandaActionForm" property="idImovel">
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
					<td><strong> <html:text property="dataInicioContrato" size="10"
						maxlength="10" onkeyup="mascaraData(this, event);"/> </strong> <img
						border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário"
						onclick="javascript:abrirCalendario('InserirContratoDemandaActionForm', 'dataInicioContrato')" />
					dd/mm/aaaa </td>
				</tr>
				<tr>
					<td><strong>Data de Fim do Contrato:<font color="#FF0000">*</font></strong></td>
					<td><strong> <html:text property="dataFimContrato" size="10"
						maxlength="10" onkeyup="mascaraData(this, event);" /> </strong> <img
						border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário"
						onclick="javascript:abrirCalendario('InserirContratoDemandaActionForm', 'dataFimContrato')" />
					dd/mm/aaaa</td>
				</tr>				
				<tr>
					<td height="19"><strong> <font color="#FF0000"></font></strong></td>
					<td align="right"><div align="left"><strong><font color="#FF0000">*</font></strong>
					   Campos obrigat&oacute;rios</div>					</td>
				</tr>
				<tr>
					<td colspan="2"><input name="Button" type="button"
						class="bottonRightCol" value="Desfazer" align="left"
						onclick="window.location.href='/gsan/exibirInserirContratoDemandaAction.do?menu=sim'"> <input
						name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
				  <td width="53" height="24" align="right"><input type="button" name="Button2"
						class="bottonRightCol" value="Inserir"
						onClick="javascript:validarForm(document.forms[0])" /></td>
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

