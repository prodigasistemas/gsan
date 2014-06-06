<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">


<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script>

	function limparCampos() {
	    document.forms[0].idSituacaoPagamento.value = "";
		document.forms[0].referenciaArrecadacao.value = "";
		document.forms[0].dataPagamento.value = "";
		document.forms[0].matriculaImovel.value = "";
	}
	
	function required() {
	 this.aj = new Array("idSituacaoPagamento", "Informe a situação do pagamento.", new Function ("varName", " return this[varName];"));
	 this.al = new Array("referenciaArrecadacao", "Informe a referencia da arrecadação", new Function ("varName", " return this[varName];"));
	}
	
 	function submeterForm(){
		if (validateRequired(document.forms[0])) {
			document.forms[0].submit()
		}
	}

</SCRIPT>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">


<html:form name="PagamentosAClassificarActionForm"
	type="gcom.gui.arrecadacao.PagamentosAClassificarActionForm"
	action="/filtrarPagamentosRecuperacaoDeCreditoAction.do" method="post">

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

				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
						<td class="parabg">Filtrar Pagamentos a classificar</td>
						<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
				</table>
				<p>&nbsp;</p>
				
				<table width="100%" border="0" >
					<tr>
					</tr>
				</table>

				<table width="100%" border="0">
					
					<tr>
						<td><strong>Situação pagamento:<font color="#FF0000">*</font></strong></td>
						<td>
							<html:select property="idSituacaoPagamento" tabindex="3">
								<html:option value="">&nbsp;</html:option>
								<html:options collection="listaSituacoesPagamento" labelProperty="descricao"  property="id" />
							</html:select>
						</td>
					</tr>
					
					<tr>
						<td><strong>Referência arrecadação:<font color="#FF0000">*</font></strong></td>
						<td>
							<html:text property="referenciaArrecadacao" name="PagamentosAClassificarActionForm" onkeyup="mascaraAnoMes(this, event);"  
									size="7" maxlength="7" onkeypress="javascript:return isCampoNumerico(event);"/> 
						</td>
					</tr>
					
					<tr>
						<td><strong>Data do Pagamento:</strong></td>
						<td width="189">
							<html:text onkeyup="mascaraData(this, event);somente_numero(this);" property="dataPagamento"
								name="PagamentosAClassificarActionForm" size="10" maxlength="10"
								onkeypress="javascript:return isCampoNumerico(event);" /> 
							<a href="javascript:abrirCalendario('PagamentosAClassificarActionForm', 'dataPagamento')">
								<img border="0" width="16" height="15" src="<bean:message key="caminho.imagens"/>calendario.gif"
									width="20" border="0" align="absmiddle" alt="Exibir Calendário" />
							</a> (dd/mm/aaaa)
						</td>
					</tr>
					
					<tr>
						<td><strong>Matrícula:</strong></td>
						<td>
							<html:text name="PagamentosAClassificarActionForm" property="matriculaImovel" 
									size="10" maxlength="10" onkeypress="javascript:return isCampoNumerico(event);"/> 
						</td>
					</tr>
					
				</table>
	
				<table width="100%">
					<tr>
						<td>
							<input style="width: 70px" type="button" name="Button2" class="bottonRightCol" value="Limpar"  onclick="limparCampos();" id="desabilita">
						</td>
						<td></td>
						<td>
							<div align="right">
							<input style="width: 70px" type="button" name="Button1" class="bottonRightCol" value="Filtrar" onclick="submeterForm();"  id="desabilita">
							</div>
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
