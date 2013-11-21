<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="gcom.util.ConstantesSistema"%>

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
	<html:javascript staticJavascript="false"  formName="GerarRelatorioFaturasAgrupadasActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
<!-- Begin

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'cliente') {      		
	      form.idCliente.value = codigoRegistro;
	      form.nomeCliente.value = descricaoRegistro;
	      form.nomeCliente.style.color = "#000000";	  
		} else if (tipoConsulta == 'responsavelSuperior') {      		
	      form.idClienteSuperior.value = codigoRegistro;
	      form.nomeClienteSuperior.value = descricaoRegistro;
	      form.nomeClienteSuperior.style.color = "#000000";	  
		}
	}

	function limparClienteTecla(){
		var form = document.forms[0];
		
		if (form.idCliente.value != "") {
			bloquearClienteSuperior(form);
			bloquearEsferaPoder(form);
		} else {
			if (form.idEsferaPoder.selectedIndex == 0 && form.idClienteSuperior.value == "") {
				desbloquearClienteSuperior(form);
				desbloquearEsferaPoder(form);
			}
		}
		
		form.nomeCliente.value = "";
	}
	
	function limparCliente(){
		var form = document.forms[0];
		
		if (form.idEsferaPoder.selectedIndex == 0 && form.idClienteSuperior.value == "") {
			desbloquearClienteSuperior(form);
			desbloquearEsferaPoder(form);
		}
		
		form.idCliente.value = "";
		form.nomeCliente.value = "";
	}
	
	function limparClienteSuperiorTecla(){
		var form = document.forms[0];

		if (form.idClienteSuperior.value != "") {
			bloquearCliente(form);
			bloquearEsferaPoder(form);
		} else {
			if (form.idEsferaPoder.selectedIndex == 0 && form.idCliente.value == "") {
				desbloquearCliente(form);
				desbloquearEsferaPoder(form);
			}
		}
		
		form.nomeClienteSuperior.value = "";
	}
	
	function limparClienteSuperior(){
		var form = document.forms[0];
		
		form.idClienteSuperior.value = "";
		form.nomeClienteSuperior.value = "";
		
		if (form.idEsferaPoder.selectedIndex == 0 && form.idCliente.value == "") {
			desbloquearCliente(form);
			desbloquearEsferaPoder(form);
		}
	}

	function validarForm(form){
		if(validateGerarRelatorioFaturasAgrupadasActionForm(form)) {
			form.action = 'gerarRelatorioFaturasAgrupadasAction.do';
			submeterFormPadrao(form);
		}
	}
	
	function gerarProtocoloEntrega(form){
		if(validateGerarRelatorioFaturasAgrupadasActionForm(form)) {
			form.action = 'gerarRelatorioProtocoloEntregaFaturaAction.do';
			submeterFormPadrao(form);
		}
	}
	
	function carregarClientesAssociados() {
		var form = document.forms[0];
		
		
		if (form.idEsferaPoder.selectedIndex != 0) {
			bloquearCliente(form);
			bloquearClienteSuperior(form);
		} else {
			if (form.idCliente.value == "" && form.idClienteSuperior.value == "") {
				desbloquearCliente(form);
				desbloquearClienteSuperior(form);
			}
		}
		
		form.action = 'exibirGerarRelatorioFaturasAgrupadasAction.do'
		submeterFormPadrao(form);
	
	}
	
	function bloquearCliente(form) {
		form.idCliente.value = "";
		form.idCliente.readOnly = true;
	}
	
	function bloquearClienteSuperior(form) {
		form.idClienteSuperior.value = "";
		form.idClienteSuperior.readOnly = true;
	}
	
	function bloquearEsferaPoder(form) {
		form.idEsferaPoder.selectedIndex = 0;
		form.idEsferaPoder.disabled = true;
	}
	
	function desbloquearCliente(form) {
		form.idCliente.value = "";
		form.idCliente.readOnly = false;
	}
	
	function desbloquearClienteSuperior(form) {
		form.idClienteSuperior.value = "";
		form.idClienteSuperior.readOnly = false;
	}
	
	function desbloquearEsferaPoder(form) {
		form.idEsferaPoder.selectedIndex = 0;
		form.idEsferaPoder.disabled = false;
	}
	
	function bloquearCampos() {
		var form = document.forms[0];
		
		if (form.idCliente.value != "") {
			bloquearClienteSuperior(form);
			bloquearEsferaPoder(form);
		} else if (form.idClienteSuperior.value != "") {
			bloquearCliente(form);
			bloquearEsferaPoder(form);
		} else if (form.idEsferaPoder.selectedIndex != 0) {
			bloquearCliente(form);
			bloquearClienteSuperior(form);
		}
	}
	
-->
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');bloquearCampos();">

<html:form action="/gerarRelatorioFaturasAgrupadasAction"
	name="GerarRelatorioFaturasAgrupadasActionForm"
	type="gcom.gui.relatorio.faturamento.GerarRelatorioFaturasAgrupadasActionForm" method="post"
	onsubmit="return validateGerarRelatorioFaturasAgrupadasActionForm(this);" >

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
					<td class="parabg">Gerar Relatório de Faturas Agrupadas</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para gerar o relatório, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="150"><strong>Mês/Ano de Faturamento:<font
						color="#FF0000">*</font></strong></td>
					<td>
					<html:text property="mesAno" tabindex="1" onkeypress="javascript:mascaraAnoMes(this,event);return isCampoNumerico(event);" size="10" maxlength="7" />
					mm/aaaa
					</td>
				</tr>
				<tr>
					<td><strong>Cliente Superior:</strong></td>
					<td>
						<html:text property="idClienteSuperior" maxlength="9" size="9" onkeyup="validaEnterComMensagem(event, 'exibirGerarRelatorioFaturasAgrupadasAction.do?objetoConsulta=1', 'idClienteSuperior');limparClienteSuperiorTecla();"
						onkeypress="return isCampoNumerico(event);" />
						<a href="javascript:abrirPopup('exibirPesquisarResponsavelSuperiorAction.do?pesquisaSuperior=sim', 400, 800);" alt="Pesquisar Cliente Superior">
						<img width="23" height="21" title="Pesquisar Cliente Superior" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
						<logic:present name="clienteSuperiorInexistente" scope="request">
							<html:text property="nomeClienteSuperior"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								size="40" maxlength="40" />
						</logic:present>
						<logic:notPresent name="clienteSuperiorInexistente" scope="request">
							<html:text property="nomeClienteSuperior"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="40" maxlength="40" />
						</logic:notPresent>	
						<a href="javascript:limparClienteSuperior();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar Cliente Superior" />
						</a>
					</td>
				</tr>
				<tr>
					<td bordercolor="#000000"><strong>Cliente:</strong>
					</td>
					<td>
						<html:text
							property="idCliente" maxlength="9" size="9"
							onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioFaturasAgrupadasAction.do?objetoConsulta=2','idCliente', 'Cliente');return isCampoNumerico(event);"
							onkeyup="limparClienteTecla();"/>
						<a
							href="javascript:abrirPopup('exibirPesquisarClienteAction.do?limparForm=sim', 400, 800);">
						<img width="23" height="21"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							border="0" title="Pesquisar Cliente" /></a> 
						<logic:present name="clienteInexistente" scope="request">
							<html:text property="nomeCliente"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								size="40" maxlength="40" />
						</logic:present>
						<logic:notPresent name="clienteInexistente" scope="request">
							<html:text property="nomeCliente"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="40" maxlength="40" />
						</logic:notPresent>	
						<a href="javascript:limparCliente();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar Cliente" />
						</a>
					</td>	
				</tr>
				<tr>
					<td><strong>Esfera Poder:</strong></td>
					<td align="left"><html:select property="idEsferaPoder" onchange="carregarClientesAssociados();">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoEsferaPoder"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
            		<td><strong>Clientes Associados:</strong></td>
		            <td><html:select property="idsClientesAssociados" style="width: 350px;" 
										multiple="mutiple" size="20">
			 				<html:option value="-1">&nbsp;</html:option>
		                	<html:options collection="clientesAssociados" labelProperty="codigoNome" property="id"/>
                		</html:select>
		            </td>
        		</tr>
        		<tr>
					<td><strong>Tipo:</strong></td>
					<td>
						<strong><label><html:radio property="indicadorTotalizador"
										value="<%=ConstantesSistema.SIM.toString()%>" /> Analítica</label>
								<label><html:radio property="indicadorTotalizador"
										value="<%=ConstantesSistema.NAO.toString()%>" /> Sintética</label>
						</strong>
					</td>
				<tr>
				<tr>
					<td><font color="#FF0000"> <input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</font></td>
					<td align="right">
					<input type="button" name="ButtonGerarProtocolo" class="bottonRightCol"
						value="Gerar Protocolo de Entrega" onclick="javascript:gerarProtocoloEntrega(document.forms[0]);" />
					
					<input type="button" name="ButtonGerar" class="bottonRightCol"
						value="Gerar Faturas" onclick="javascript:validarForm(document.forms[0]);" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
