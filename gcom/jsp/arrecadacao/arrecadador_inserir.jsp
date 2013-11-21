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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirArrecadadorActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
	
<script language="JavaScript">

function validarForm(form){

		if(validateInserirArrecadadorActionForm(form)){
			if(validateInteger(form)){
    		submeterFormPadrao(form);
    		}
		}
}

	
	function IntegerValidations () {
		this.aa = new Array("idAgente", "O campo Código do Agente contém caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("idCliente", "O campo Cliente contém caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ac = new Array("idImovel", "O campo Imóvel contém caracteres especiais.", new Function ("varName", " return this[varName];"));

	}




function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];
	if (tipoConsulta == 'cliente') {
        form.idCliente.value = codigoRegistro;
        form.nomeCliente.value = descricaoRegistro;
     }
     if(tipoConsulta == 'imovel'){
        form.idImovel.value = codigoRegistro;
        form.inscricaoImovel.value = descricaoRegistro;
    }
}

function habilitarPesquisaCliente(form) {
	if (form.idCliente.disabled == false) {
		chamarPopup('exibirPesquisarClienteAction.do', 'cliente', null, null, 275, 480, '',form.idCliente.value);
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
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
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
	if(tipo == 'cliente')
    { 
        form.idCliente.value = "";
        form.nomeCliente.value = "";
	}
}
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/inserirArrecadadorAction" method="post"
	name="InserirArrecadadorActionForm"
	type="gcom.gui.arrecadacao.InserirArrecadadorActionForm"
	onsubmit="return validateInserirArrecadadorActionForm(this);">
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
					<td class="parabg">Inserir Arrecadador</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>

					<td colspan="2">Para inserir o arrecadador, informe os dados gerais
					abaixo:</td>
				</tr>
				<tr>
					<td width="139" height="22"><strong>Código do Agente:<font
						color="#FF0000">*</font></strong></td>

					<td width="388"><strong> 
				  <html:text property="idAgente" size="4"
						maxlength="3" /> </strong></td>
				</tr>
				<tr>
					<td><strong>Cliente:<font color="#FF0000">*</font></strong></td>
					<td><strong> <html:text property="idCliente" size="10"	maxlength="10"  
					onkeypress="javascript:return validaEnter(event, 'exibirInserirArrecadadorAction.do?objetoConsulta=1', 'idCliente', 'Cliente');"/> </strong>
						<a href="javascript:habilitarPesquisaCliente(document.forms[0]);" alt="Pesquisar Cliente">
						<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
						<logic:present name="existeCliente">	
                      		<logic:equal name="existeCliente" value="exception">							 							  
					        	<html:text property="nomeCliente" size="35" maxlength="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
			 		        </logic:equal>
					        <logic:notEqual name="existeCliente"	value="exception">
			 			        <html:text property="nomeCliente" size="35" maxlength="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>											    
					        </logic:notEqual>
					    </logic:present>

						<logic:notPresent name="existeCliente">
							<logic:empty name="InserirArrecadadorActionForm" property="idCliente">
								<html:text property="nomeCliente" size="35" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:empty>
							<logic:notEmpty name="InserirArrecadadorActionForm" property="idCliente">
								<html:text property="nomeCliente" size="35"	readonly="true"	style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent>
						<a href="javascript:limparForm('cliente');"> 
							<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" />						</a>					</td>
				</tr>
				<tr>
					<td><strong>Imóvel:</strong></td>
					<td><strong><html:text property="idImovel" size="10" maxlength="10" 
					onkeypress="javascript:return validaEnter(event, 'exibirInserirArrecadadorAction.do?objetoConsulta=2', 'idImovel');"/>  
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
								<logic:empty name="InserirArrecadadorActionForm" property="idImovel">
									<html:text property="inscricaoImovel" size="35" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:empty>
								<logic:notEmpty name="InserirArrecadadorActionForm" property="idImovel">
									<html:text property="inscricaoImovel" size="35"	readonly="true"	style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>
							</logic:notPresent>
						<a href="javascript:limparForm('imovel');"> 
							<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" />						
						</a>
					</td>
				</tr>

				<tr>
					<td><strong>Inscrição Estadual:</strong></td>
					<td><strong> <html:text property="inscricaoEstadual" size="20"
						maxlength="20" /> </strong></td>
				</tr>
				<tr>
					<td height="19"><strong> <font color="#FF0000"></font></strong></td>
					<td align="right"><div align="left"><strong><font color="#FF0000">*</font></strong>
					   Campos obrigat&oacute;rios</div>					</td>
				</tr>
				<tr>
					<td colspan="2"><input name="Button" type="button"
						class="bottonRightCol" value="Desfazer" align="left"
						onclick="window.location.href='/gsan/exibirInserirArrecadadorAction.do?menu=sim'"> <input
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

