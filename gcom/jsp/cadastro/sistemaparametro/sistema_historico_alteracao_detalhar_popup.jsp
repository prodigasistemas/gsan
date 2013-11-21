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
</head>

<body leftmargin="0" topmargin="0"
	onload="window.focus();">

<html:form action="/exibirSistemaHistAlteracaoDetalharPopupAction" 
	name="SistemaHistoricoAlteracaoDetalharPopupActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.SistemaHistoricoAlteracaoDetalharPopupActionForm"
	method="post">

	<table width="100%" border="0" cellspacing="5" cellpadding="0">

		<tr>

			<td width="100%" valign="top" class="centercoltext">

				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
	
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
						</td>
						<td class="parabg">Detalhamento do Histórico de Alterações do Sistema</td>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
			
				<p>&nbsp;</p>

				<table width="100%" border="0" align="center" >
				<tr>
					<td><strong>Funcionalidade:</strong></td>
					<td><html:text property="idFuncionalidade" size="4"
						maxlength="4" style="background-color:#EFEFEF; border:0; color: #000000;" readonly="true"/>&nbsp;
						<html:text property="descricaoFuncionalidade" size="50"
						maxlength="50" style="background-color:#EFEFEF; border:0; color: #000000;" readonly="true"/></td>
				</tr>
				<tr>
					<td><strong>Data de Alteração:</strong></td>
					<td colspan="2"><html:text property="dataAlteracao"  size="10" maxlength="10"  
					style="background-color:#EFEFEF; border:0; color: #000000;" readonly="true"/></td>
				</tr>

				<tr>
					<td><strong>Título da alteração:</strong></td>
					<td><html:text property="tituloAlteracao" size="40" maxlength="40" 
					style="background-color:#EFEFEF; border:0; color: #000000;" readonly="true"/></td>

				</tr>
				
				<tr>
					<td colspan="2"><strong>Descrição da alteração:</strong></td>
				</tr>
				<tr>
					<td colspan="2"><html:textarea property="descricaoAlteracao"
					style="background-color:#EFEFEF; border:0; color: #000000;" readonly="true" cols="59" rows="4" /></td>
				</tr>							      			

				<tr>
					<td colspan="2">
						<table width="100%">
			              	<tr>
			            		<td >
		              				<div align="right">
	                  					<input name="ButtonFechar" type="button" class="bottonRightCol" value="Fechar" onclick="javascript:window.close();">
		              				</div>
			              		</td>
				          	</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</html:form>
</body>
</html:html>