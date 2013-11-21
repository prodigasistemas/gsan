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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="GerarContratoPrestacaoServicoJuridicoActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
<!-- Begin

	function limparForm() {
	
		var form = document.forms[0];
		
		form.idImovel.value = "";
		form.idCliente.value = "";
		form.nomeCliente.value = "";
		form.inscricao.value = "";
	}
	
	function limparImovelForm() {
	
		var form = document.forms[0];
		
		form.idImovel.value = "";
		form.inscricao.value = "";
	}
	
	function limparClienteForm() {
	
		var form = document.forms[0];
		
		form.idCliente.value = "";
		form.nomeCliente.value = "";
	}
	
	function limparImovelTecla() {
		var form = document.forms[0];
	
		form.inscricao.value = "";
	}
	
	function limparClienteTecla() {
		var form = document.forms[0];
	
		form.nomeCliente.value = "";
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
		if (tipoConsulta == 'imovel') {
			form.inscricao.style.color = "#000000";
			form.idImovel.value = codigoRegistro;
			form.inscricao.value = descricaoRegistro;
		}
		if (tipoConsulta == 'cliente') {
			form.nomeCliente.style.color = "#000000";
			form.idCliente.value = codigoRegistro;
			form.nomeCliente.value = descricaoRegistro;
		}
	}

	function validarForm(form){
				
		if(testarCampoValorZero(document.GerarContratoPrestacaoServicoJuridicoActionForm.idImovel, 'Matrícula')) { 		
			if(validateGerarContratoPrestacaoServicoJuridicoActionForm(form)){			
   				form.submit();			
			}
		}	
	}

-->
</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/gerarRelatorioContratoPrestacaoServicoJuridicoAction"
	name="GerarContratoPrestacaoServicoJuridicoActionForm"
	type="gcom.gui.atendimentopublico.GerarContratoPrestacaoServicoJuridicoActionForm"
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
					<td class="parabg">Gerar Contrato de Prestação de Serviço</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para gerar o contrato
					de prestação de serviço, informe os dados
					abaixo:</td>
				</tr>
				<tr>
								<td width="30%"><strong>Matrícula do Imóvel:<font color="#FF0000">*</font></strong></td>
								<td>
									<html:text property="idImovel" size="9" maxlength="9" 
										onkeyup="validaEnter(event, 'exibirGerarContratoPrestacaoServicoJuridicoAction.do', 'idImovel');"
										onkeypress="limparImovelTecla();" />
									<a href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 275, 480, '',document.forms[0].idImovel);">	
										<img width="23" height="21" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											style="cursor:hand;" alt="Pesquisar" border="0"/></a> 
									<logic:present name="matriculaInexistente" scope="request">
										<html:text property="inscricao" size="35" maxlength="35"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" />
									</logic:present>
									<logic:notPresent name="matriculaInexistente" scope="request">
										<html:text property="inscricao" size="35" maxlength="35"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:notPresent> 
									<a href="javascript:limparImovelForm();">
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"border="0" title="Apagar" />
									</a>
								</td>
							</tr>
				<tr>
				<tr>
								<td width="30%"><strong>Cliente Responsável:<font color="#FF0000">*</font></strong></td>
								<td>
									<html:text property="idCliente" size="9" maxlength="9" 
										onkeyup="validaEnter(event, 'exibirGerarContratoPrestacaoServicoJuridicoAction.do', 'idImovel');"
										onkeypress="limparClienteTecla();" />
									<a href="javascript:abrirPopup('exibirPesquisarClienteAction.do', 'cliente', null, null, 275, 480, '',document.forms[0].idCliente);">	
										<img width="23" height="21" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											style="cursor:hand;" alt="Pesquisar" border="0"/></a> 
									<logic:present name="clienteInexistente" scope="request">
										<html:text property="nomeCliente" size="35" maxlength="35"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" />
									</logic:present>
									<logic:notPresent name="clienteInexistente" scope="request">
										<html:text property="nomeCliente" size="35" maxlength="35"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:notPresent> 
									<a href="javascript:limparClienteForm();">
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"border="0" title="Apagar" />
									</a>
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
					<td><input type="button" name="ButtonReset" class="bottonRightCol"
						value="Limpar" onclick="javascript:limparForm();">
						
					<td colspan="2" align="right">
 					  <input type="button" name="Button" value="Gerar" onclick="javascript:validarForm(document.forms[0]);" class="bottonRightCol"/>
					  <%-- <input type="button" name="Button" class="bottonRightCol" value="Filtrar" onClick="javascript:validarForm(document.forms[0]);" /> --%>
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
