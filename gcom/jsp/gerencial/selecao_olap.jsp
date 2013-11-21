<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script>
	/*
	function enviarParaOlap(){
		var form = document.forms[0];
		var ipServidor = form.ipServidor.value;
		var tipoRelatorio = form.tipoRelatorio.value;
		var empresa = form.empresa.value;
		var logoMarcaEmpresa = form.logoMarcaEmpresa.value;
		var selecionaAno = form.selecionaAno.value;
				
		// Monta a url para chamada do OLAP
		var objSelecao = document.getElementById('selectAno');
		if (selecionaAno.toUpperCase() == 'SIM') {
			if (objSelecao.selectedIndex > 0) {
				tipoRelatorio += objSelecao.value;
				objSelecao.selectedIndex = 0;
			}
		}
		ipServidor = 'localhost:8080';
		var url = "http://" + ipServidor + "/mondrian/testpage.jsp?query=mondrian" +
				  "&parametro=" + tipoRelatorio +
				  "&empresa=" + empresa +
				  "&logomarca=" + logoMarcaEmpresa;
		
		//window.open('http://'+ document.forms[0].ipServidor.value +'/mondrian/testpage.jsp?query=mondrian&parametro='+document.forms[0].tipoRelatorio.value, '_blank' );
		var strWindowFeatures = "menubar=yes,location=no,resizable=yes,scrollbars=yes,status=yes";
		var pop = window.open(url, 'new', strWindowFeatures);
		
		if (pop != null) {
			if (selecionaAno.toUpperCase() != 'SIM') {
				window.location.href = "/gsan/telaPrincipal.do";
			}
		}else {
			document.getElementById('divAntipopup').style.display = '';
		}		
	}
	*/
	
	function enviarParaOlap() {
		var form = document.forms[0];
		var ipServidor = form.ipServidor.value;
		var tipoRelatorio = form.tipoRelatorio.value;
		var selecionaAno = form.selecionaAno.value;
				
		// Monta a url para chamada do OLAP
		var objSelecao = document.getElementById('selectAno');
		if (selecionaAno.toUpperCase() == 'S') {
			if (objSelecao.selectedIndex > 0) {
				tipoRelatorio += objSelecao.value;
				//form.tipoRelatorio.value = tipoRelatorio;
				objSelecao.selectedIndex = 0;
			}
		}
		
		//ipServidor = 'localhost:8080';
		var url = "http://" + ipServidor + "/mondrian/testpage.jsp?query=mondrian" +
				  "&parametro=" + tipoRelatorio;
				  
				  
		form.action = url;

		try {
			form.submit();
		} catch(e) {
			document.getElementById('divAntipopup').style.display = '';
			return false;
		}
		
		if (selecionaAno.toUpperCase() != 'S') {
			window.location.href = "/gsan/telaPrincipal.do";
		}
	}
	
	/*
	function enviarParaOlap() {
		var form = document.forms[0];
		var ipServidor = form.ipServidor.value;
		var tipoRelatorio = form.tipoRelatorio.value;
		var selecionaAno = form.selecionaAno.value;
				
		// Monta a url para chamada do OLAP
		var objSelecao = document.getElementById('selectAno');
		if (selecionaAno.toUpperCase() == 'S') {
			if (objSelecao.selectedIndex > 0) {
				tipoRelatorio += objSelecao.value;
				form.tipoRelatorio.value = tipoRelatorio;
				objSelecao.selectedIndex = 0;
			}
		}
		
		ipServidor = 'localhost:8080';
		var url = "http://" + ipServidor + "/mondrian/testpage.jsp?query=mondrian" +
				  "&parametro=" + tipoRelatorio;
		
		with (document.formOlap) {
			method = "POST";
			var strWindowFeatures = "menubar=yes,location=no,resizable=yes,scrollbars=yes,status=yes";
			alert(url);
			pop = window.open(url, 'destino', strWindowFeatures);
			
			if (pop == null) {
				document.getElementById('divAntipopup').style.display = '';
				return false;
			}
			
			target = 'destino';
			submit();
		}
		
		return true;
	
		if (pop != null) {
			if (selecionaAno.toUpperCase() != 'SIM') {
				window.location.href = "/gsan/telaPrincipal.do";
			}
		}else {
			document.getElementById('divAntipopup').style.display = '';
		}
	
	}
	*/
</script>
	
	
</head>

<body leftmargin="5" topmargin="5">
<form name="formOlap" method="post" target="_blank">

<input type="hidden" name="ipServidor" value="<%=request.getAttribute("ipServidor")%>" />
<input type="hidden" name="tipoRelatorio" value="<%=request.getAttribute("tipoRelatorio")%>" /> 
<input type="hidden" name="selecionaAno" value="<%=request.getAttribute("selecionaAno")%>" /> 
<input type="hidden" name="empresa" value="<%=request.getAttribute("empresa")%>" />
<input type="hidden" name="logoMarcaEmpresa" value="<%=request.getAttribute("logoMarcaEmpresa")%>" />
	
<%@ include	file="/jsp/util/cabecalho.jsp"%> 
<%@ include file="/jsp/util/menu.jsp"%>

<table width="770" border="0" cellspacing="5" cellpadding="0">
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
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
				<td class="parabg">Gerencial OLAP</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>

			</tr>
		</table>

		<p>&nbsp;</p>
			<div id="divSelecionaAno" style="display: none;">
				<table width="100%" cellspacing="0">
					<tr>
						<td colspan="2">Escolha um ano para a exibição do relatório <b><%=request.getParameter("tipoRelatorio")%></b>:
					</tr>
					<tr>
						<td><strong>Ano:</strong></td>
						<td width="93%"><select id="selectAno" onchange="javascript:enviarParaOlap();">
							<option value="" selected="selected">&nbsp;</option>
							<option value="2007">2007</option>
							<option value="2008">2008</option>
							<option value="2009">2009</option>
							<option value="2010">2010</option>
							<option value="2011">2011</option>
						</select>
					</tr>
				</table>
			</div>
			
			<div id="divAntipopup" style="display: none;">
				Seu navegador pode estar com o <b>Anti-popup</b> habilitado. Por favor, desbloqueie o Anti-popup para o GSAN ou clique no botão abaixo:<br>
				<input type="button" value="Abrir OLAP" onclick="javascript:enviarParaOlap();">
			</div>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>


<%@ include file="/jsp/util/rodape.jsp"%></form>
</body>
</html:html>

<script>
	var form = document.forms[0];

	if (form.selecionaAno.value.toUpperCase() == 'S') {
		document.getElementById('divSelecionaAno').style.display = '';
	}else {
		enviarParaOlap();
	}
</script>
