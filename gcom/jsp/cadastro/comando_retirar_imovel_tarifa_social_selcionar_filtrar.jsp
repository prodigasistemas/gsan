<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="SelecionarComandoRetirarImovelTarifaSocialActionForm" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

	
	function limparForm(form) {
		
	}
   
	function validarForm(form) {
   
		urlRedirect = "/gsan/selecionarComandoRetirarImovelTarifaSocialAction.do";
	
		if(validateSelecionarComandoRetirarImovelTarifaSocialActionForm(form)) {
				form.action = urlRedirect;
				submeterFormPadrao(form);
		}
	}
   
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');controle(document.forms[0]);">
<html:form action="/exibirSelecionarComandoRetirarImovelTarifaSocialAction"
	name="SelecionarComandoRetirarImovelTarifaSocialActionForm" method="post"
	type="gcom.gui.cadastro.SelecionarComandoRetirarImovelTarifaSocialActionForm">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="140" valign="top" class="leftcoltext">

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
			<td width="615" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Filtrar Comando de Carta para Clientes Pertencentes a Tarifa Social</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table bordercolor="#000000" width="100%" cellspacing="0">
				<tr>
					<td colspan="3">Para filtrar Comando(s) de Cartas da Tarifa Social no
					sistema, informe os dados abaixo:</td>
				</tr>

				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
				</tr>

				<tr> 
                  <td width="15%"><strong>Situação:<font color="#FF0000">*</font></strong></td>
                  <td><html:radio property="indicadorSituacao" value="1"  /> <strong>Simulado</strong></td>
               	</tr>
                 <tr> 
	                 <td></td>
	                 <td><html:radio property="indicadorSituacao" value="2"  /> <strong>Gerado</strong></td>
                 </tr>
                 <tr> 
	                 <td></td>
	                 <td><html:radio property="indicadorSituacao" value="3" /> <strong>Executado</strong></td>
                 </tr>
			
               
			
				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
				</tr>
				
				<tr> 
                  <td><strong>Tipo de Carta:</strong></td>

                  <td><html:radio property="indicadorTipoCarta" value="1" /> <strong>Recadastramento</strong></td>
                </tr>
                  <tr> 
	                 <td></td>
	                 <td><html:radio property="indicadorTipoCarta" value="2"  /> <strong>Cobrança</strong></td>
                 </tr>
                 <tr> 
	                 <td></td>
	                <td><html:radio property="indicadorTipoCarta" value="3" /> <strong>Todos</strong></td>
                 </tr>
                  
                  

				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
				</tr>
				
				<tr>
				<td colspan="2">
				<table width="100%" border="0">
				<tr>
					<td><input type="button" name="ButtonReset" class="bottonRightCol"
						value="Limpar" onclick="javascript:limparForm(document.forms[0]);">
						<input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>	
					<td colspan="2" align="right">
					  <gsan:controleAcessoBotao name="Button" value="Filtrar" onclick="javascript:validarForm(document.forms[0]);" url="selecionarComandoRetirarImovelTarifaSocialAction.do"/>
					</td>
					
				</tr>
				
				</table>				
				</td>
				</tr>
				
				
				
				
				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
				</tr>
				
			</table>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
	<p>&nbsp;</p>
</html:form>
</body>
</html:html>
