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

	function limparFuncionalidade() {
		var form = document.forms[0]; 
	 	form.nomeFuncionalidade.value = '';
	 	form.idFuncionalidade.value = '';
	 	form.funcionalidadeLimpa.value='1';                  	
	 	limparArgumentoPesquisa();
	 	if (form.operacoes != null) {
			form.action = 'ExibirFiltrarOperacaoEfetuadaAction.do';
		 	form.submit();
		}
		form.operacoes = null;
	}
 
	function limparUsuario() {
	 	document.forms[0].nomeUsuario.value = '';
	 	document.forms[0].idUsuario.value = '';
		document.forms[0].usuarioLimpo.value='1';                  
	}
	function limparTabela() {
		document.forms[0].nomeTabela.value = '';
		document.forms[0].idTabela.value = '';
		document.forms[0].action = 'ExibirFiltrarOperacaoEfetuadaAction.do';
		document.forms[0].submit();
	}
 
	
	function limparArgumentoPesquisa(){
		document.forms[0].valorArgumentoPesquisa.value = '';
		document.forms[0].argumentoLimpo.value = '1';
	}

  function limparFiltrar(){
    limparArgumentoPesquisa();
    limparUsuario();
 	document.forms[0].dataInicial.value = '';
 	document.forms[0].dataFinal.value = '';
 	document.forms[0].horaInicial.value = '';
 	document.forms[0].horaFinal.value = '';
 	document.forms[0].dataLimpa.value = '1';
 	document.forms[0].horaLimpa.value = '1';
 	document.forms[0].unidadeNegocio.value= '-1'; 	
    limparFuncionalidade();
  }

    function limparUsuario() {
	  
		var form = document.forms[0];
	
		form.idUsuarioAlteracao.value = "";
		form.loginUsuarioAlteracao.value = "";
	    form.nomeUsuarioAlteracao.value = "";
	}
  
	function limparUsuarioTecla() {
		var form = document.forms[0];
	
		form.idUsuarioAlteracao.value = "";
	    form.nomeUsuarioAlteracao.value = "";
	}
  
	
	function limparCampos() {
	    document.forms[0].idSituacaoPagamento.value = "";
		document.forms[0].referenciaArracadacao.value = "";	
	}
	
	function required () {

	 this.aj = new Array("idSituacaoPagamento", "Informe a situação do pagamento.", new Function ("varName", " return this[varName];"));
	 this.al = new Array("referenciaArracadacao", "Informe a referencia da arrecadação", new Function ("varName", " return this[varName];"));
	}
	
 	function submeterForm(){
		var form = document.forms[0];
	
		return validateRequired(document.forms["PagamentosAClassificarActionForm"]);
	}

</SCRIPT>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">


<html:form name="PagamentosAClassificarActionForm"
	type="gcom.gui.arrecadacao.PagamentosAClassificarActionForm"
	action="/filtrarPagamentosAClassificarAction" method="post"
	onsubmit="return submeterForm();">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<input type="hidden" name="numeroPagina" value="1" />
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
					
					<!-- ano mes - pedido por Aryed data: 12/02/2008 -->
					
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
							
							<html:text name="PagamentosAClassificarActionForm" onkeyup="mascaraAnoMes(this, event);" property="referenciaArrecadacao" 
									size="10" maxlength="10" onkeypress="javascript:return isCampoNumerico(event);"/> 
						</td>
					</tr>
					
				</table>
	
				<table width="100%">
					<tr>
					
						<td>
							<input style="width: 70px" type="button" name="Button2" class="bottonRightCol" 
							value="Limpar"  onclick="limparCampos();" id="desabilita">
						</td>
							
						<td></td>
						<td>
							<div align="right">
							<input style="width: 70px" type="submit" name="Button1	" class="bottonRightCol" 
							value="Filtrar"   id="desabilita">
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
