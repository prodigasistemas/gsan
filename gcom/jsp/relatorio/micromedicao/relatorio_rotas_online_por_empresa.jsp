<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="GerarRelatorioRotasOnlinePorEmpresaActionForm" />
<script>
	function validaMesAno(form){
		var mesAno = form.mesAnoReferencia.value;
		
		if (mesAno == null || mesAno == '') {
			alert("Preencha o campo Mês de Referência.");
			return false;
		}
		
		if (mesAno.length < 7 || mesAno.substring(2,3) != "/" || mesAno.substring(0,2) < "01" || mesAno.substring(0,2) > "12") {
			alert("Mês/Ano inválido.");
			return false;
		}
		
		return true;
	}

	function validarForm() {
		var form = document.forms[0];
		return validaMesAno(form);
	}
</script>


</head>

<body leftmargin="5" topmargin="5">
<html:form 
	action="/gerarRelatorioRotasOnlinePorEmpresaAction.do"
	name="GerarRelatorioRotasOnlinePorEmpresaActionForm"
	type="gcom.gui.relatorio.micromedicao.GerarRelatorioRotasOnlinePorEmpresaActionForm"
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
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_left.gif" border="0" /></td>
					<td class="parabg">Relátorio de Rotas Online por Empresa</td>
					<td width="11" valign="top"><img src="<bean:message key="caminho.imagens"/>parahead_right.gif" border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para gerar o relat&oacute;rio de rotas online por empresa, informe os dados abaixo:</td>
				</tr>
				
				<tr>
					<td><strong>Mês de Referencia:<font color="#FF0000">*</font></strong></td>
					<td align="left">
						<html:text property="mesAnoReferencia" size="7" maxlength="7" onkeyup="mascaraAnoMes(this, event);" />
					</td>
				</tr>
				
				<tr>
					<td><strong>Faturamento Grupo: </strong></td>
					<td align="left">
						<html:select property="idFaturamentoGrupo">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="collectionFaturamentoGrupo" labelProperty="descricao" property="id" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td><strong>Empresa: </strong></td>
					<td align="left">
						<html:select property="idEmpresa">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="collectionEmpresa" labelProperty="descricao" property="id" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td><strong>Leiturista: </strong></td>
					<td align="left">
						<html:select property="idLeiturista">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="collectionLeiturista" labelProperty="cliente.nome" property="id" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td colspan="2" align="left"><font color="#FF0000">*</font> Campo Obrigat&oacute;rio</td>
				</tr>
				<tr>
					<td><p>&nbsp;</p></td>
					<td colspan="2"></td>
				</tr>
			</table>
			<table>
				<tr>
					<td width="100%">
					<table width="30%" border="0" align="right" cellpadding="0" cellspacing="2">
						<tr>
							<td width="61">&nbsp;</td>
							<td width="416">&nbsp;</td>
							<td width="12"></td>
							<td width="61">
								<input type="button" class="bottonRightCol" value="Gerar Relat&oacute;rio" onclick="if (validarForm()) { toggleBox('demodiv',1); }">
							</td>
							<td width="82">&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioRotasOnlinePorEmpresaAction.do"/> 
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
