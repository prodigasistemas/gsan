<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page import="gcom.util.ConstantesSistema" %>

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
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="GerarRelatorioResumoFaturamentoActionForm" />
<script>

function validaMesAno(form){
  var form = document.GerarRelatorioContasEmitidasActionForm;
  var mesAno = form.mesAno.value;
  if(mesAno.length < 7 || mesAno.substring(2,3) != "/" ||
     mesAno.substring(0,2) < "01" || mesAno.substring(0,2) > "12"){
     alert("Mês/Ano inválido.");
     return false;
  }
  return true;
}

</script>


</head>

<body leftmargin="5" topmargin="5">
<html:form action="/gerarRelatorioContasEmitidasAction.do"
	name="GerarRelatorioContasEmitidasActionForm"
	type="gcom.gui.relatorio.faturamento.conta.GerarRelatorioContasEmitidasActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="115" valign="top" class="leftcoltext">
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
			<td valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img
						src="<bean:message key="caminho.imagens"/>parahead_left.gif"
						border="0" /></td>
					<td class="parabg">Relátorio Contas Emitidas</td>
					<td width="11" valign="top"><img
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para gerar o relat&oacute;rio de contas emitidas, informe os dados abaixo:</td>
				</tr>
				
				<tr>
					<td width="35%"><strong>Grupo de Faturamento <font color="#FF0000">*</font></strong></td>
					<td><strong><strong> 
						<html:select property="grupoFaturamento">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoGrupoFaturamento" labelProperty="descricao" property="id"/>
						</html:select> </strong></strong>
					</td>
				</tr>
				<tr>
					<td><strong>Mês/Ano Referência:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:text property="mesAno" size="7" maxlength="7" 
						onkeyup="mascaraAnoMes(this, event);"/><strong>&nbsp; </strong>mm/aaaa
					</td>
				</tr>
				
				<tr>
					<td><strong>Esfera Poder:<font color="#FF0000">*</font></strong></td>
					<td>
						<strong> 
						<html:select property="esferaPoder" style="width: 180px;" multiple="mutiple" size="4">
							<logic:present name="colecaoEsferaPoder">
								<html:option value="" />
								<html:options collection="colecaoEsferaPoder" labelProperty="descricao" property="id" />
							</logic:present>
						</html:select>
						</strong>
					</td>
				</tr>	
				
				<tr>
					<td><strong>
						<span class="style2">Tipo de Impressão:<font color="#FF0000">*</font></span>
						</strong>
					</td>
					<td align="left" colspan="2">
						<label> 
							<html:radio	property="tipoImpressao" value="1"/><strong>Normal</strong>
						</label>
						<label>
							<html:radio property="tipoImpressao" value="2"/> <strong>Impressão Simultânea</strong>
						</label>	
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
					<td>
					<p>&nbsp;</p>
					</td>
					<td colspan="2"></td>
				</tr>
			</table>
			<table width="100%">
				<tr>
					<td colspan="2" align="right">
						<input type="button" class="bottonRightCol"
							value="Gerar Relatório" onclick="toggleBox('demodiv',1);">
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioContasEmitidasAction.do"/> 
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
