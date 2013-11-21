<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.cadastro.sistemaparametro.SistemaParametro"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript">

	function limparForm(){
		var form = document.forms[0];
		form.idEmpresa.value = -1;
		form.idContrato.value = -1;
		form.dataReferencia.value = '';
	}
	
	function carregarContratos(){
		var form = document.forms[0];
		
		form.action = 'exibirGerarRelatorioAcompanhamentoBoletimMedicaoAction.do?empresaSelecionada=Sim';
		form.submit();
	}
	
	function validarForm(){
		var form = document.forms[0];
		if(validateGerarRelatorioAcompanhamentoBoletimMedicaoActionForm(form)){
			toggleBox('demodiv', 1);
		}
	}
</script>

</head>

<html:javascript staticJavascript="false" formName="GerarRelatorioAcompanhamentoBoletimMedicaoActionForm" />

<body leftmargin="5" topmargin="5" onload="">

	<div id="formDiv">
		<html:form action="/exibirGerarRelatorioAcompanhamentoBoletimMedicaoAction" method="post" name="GerarRelatorioAcompanhamentoBoletimMedicaoActionForm"
			type="gcom.gui.relatorio.atendimentopublico.GerarRelatorioAcompanhamentoBoletimMedicaoActionForm">


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

						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td width="11">
									<img border="0"	src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
								</td>
								<td class="parabg">
									Gerar Relatório Acompanhamento Boletim Medição
								</td>
								<td width="11">
									<img border="0"	src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
								</td>
							</tr>
						</table>

						<p>&nbsp;</p>

						<table width="100%" border="0">
							<tr>
								<td colspan="2">
									Para filtrar a(s) OS, informe os dados abaixo:
								</td>
							</tr>

							<tr>
								<td>&nbsp;</td>
								<td colspan="2" align="right">
									<div align="left">
										<strong> </strong>
									</div>
								</td>
							</tr>

							<tr>
								<td>
									<strong>Empresa:<font color="#FF0000">*</font></strong>
								</td>
								<td colspan="2">
									<html:select property="idEmpresa" tabindex="1" onchange="javascript:carregarContratos();">
										<html:option value="-1">&nbsp;</html:option>
										<html:options collection="colecaoEmpresa" labelProperty="descricao" property="id" />
									</html:select> <font size="1">&nbsp; </font>
								</td>
							</tr>

							<logic:present name="contratoEmpresa" scope="request">
								<tr>
									<td>
										<strong>Contrato:<font color="#FF0000">*</font></strong>
									</td>
									<td colspan="2">
										<html:select property="idContrato" tabindex="2">
											<html:option value="-1">&nbsp;</html:option>
											<html:options collection="colecaoContrato" labelProperty="descricaoContrato" property="id" />
										</html:select> 
										<font size="1">&nbsp; </font>
									</td>
								</tr>
							</logic:present>

							<logic:notPresent name="contratoEmpresa" scope="request">
								<tr>
									<td>
										<strong>Contrato: <font color="#FF0000">*</font></strong>
									</td>
									<td colspan="2">
										<html:select property="idContrato" tabindex="2" disabled="true" style="background-color:#EFEFEF; border:0">
											<html:option value="-1">&nbsp;</html:option>
										</html:select> 
										<font size="1">&nbsp; </font>
									</td>
								</tr>
							</logic:notPresent>

							<tr>
								<td>
									<strong>Mês/Ano Referência:<font color="#FF0000">*</font></strong>
								</td>
								<td colspan="2">
									<html:text property="dataReferencia" size="7" maxlength="7" tabindex="3"
										onkeyup="mascaraAnoMes(this, event);" onkeypress="return isCampoNumerico(event);"/>
									mm/aaaa
								</td>
							</tr>

							<tr>
								<td>&nbsp;</td>
								<td colspan="2" align="right">
								<div align="left"><strong> </strong></div>
							</td>
							
							</tr>

							<tr>
								<td>
									<div align="left">
										<input type="button" name="limpar" class="bottonRightCol" value="Limpar" tabindex="4" 
											onclick="javascript:limparForm();"> 
										<input type="button" name="adicionar" class="bottonRightCol" value="Cancelar" tabindex="5"
											onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
									</div>
								</td>

								<td colspan="2">
									<div align="right">
										<input type="button" name="botaoConcluir" class="bottonRightCol" value="Gerar" tabindex="6"
											onclick="javascript:validarForm();">
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>

				<%@ include file="/jsp/util/rodape.jsp"%>
			</table>
		<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioAcompanhamentoBoletimMedicaoAction.do" />
		</html:form>
	</div>

	<%@ include file="/jsp/util/telaespera.jsp"%>
</body>

</html:html>