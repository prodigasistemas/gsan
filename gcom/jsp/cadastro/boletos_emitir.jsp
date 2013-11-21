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
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="EmitirBoletosActionForm" />
<script>

function replicaDados(campoOrigem, campoDestino){
	campoDestino.value = campoOrigem.value;
}

function validarForm(){
	var form = document.forms[0];		
		form.action = 'emitirBoletosAction.do';
		submeterFormPadrao(form);
}

</script>


</head>

<body leftmargin="5" topmargin="5">
<html:form action="/emitirBoletosAction.do"
	name="EmitirBoletosActionForm"
	type="gcom.gui.cadastro.EmitirBoletosActionForm"
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
					<td class="parabg">Emitir Boletos </td>
					<td width="11" valign="top"><img
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para emitir boletos, informe os dados abaixo:</td>
				</tr>
	
				<tr> 
	                <td width="25%"><strong>Grupo de Faturamento:<font	color="#FF0000">*</font></strong></td>
	                <td align="left" colspan="2"><div align="left"> <strong> 
	                    <html:select property="grupoFaturamento" >
							<logic:present name="colecaoFaturamentoGrupo">
								<html:option value="">&nbsp;</html:option>
								<html:options collection="colecaoFaturamentoGrupo" labelProperty="descricao" property="id" />
							</logic:present>
						</html:select>
	                    </strong></div>
	                </td>
              	</tr>
              	              		
				<tr>
					<td>&nbsp;</td>
					<td colspan="2" align="left"><font color="#FF0000">*</font> Campo Obrigat&oacute;rio</td>
				</tr>
				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
					<td colspan="2"></td>
				</tr>
			</table>
			<table>
				<tr>
					<td width="100%">
					<table width="30%" border="0" align="right" cellpadding="0"
						cellspacing="2">
						<tr>
							<td width="61">&nbsp;</td>
							<td width="416">&nbsp;</td>
							<td width="12"></td>
							<td width="61">		
							
					
								<input type="button" class="bottonRightCol"
								value="Iniciar"
								onclick="validarForm()"
								>
								
							
							<%--
							onclick="toggleBox('demodiv',1);"
							
							
							<a
									href="javascript:toggleBox('demodiv',1);/*javascript:abrirPopupRelatorio('/gsan/gerarRelatorioResumoArrecadacaoAction.do');*/"
									style="text-decoration:none">
									
									<input type="button" class="bottonRightCol"
									value="Gerar Relat&oacute;rio"
									>
									
								 </a>
							 --%>
												
								
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
	
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
