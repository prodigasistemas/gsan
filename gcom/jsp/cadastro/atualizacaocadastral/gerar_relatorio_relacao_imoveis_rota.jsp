<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>
	validacao/regras_validator.js">
</script>

<html:javascript staticJavascript="false" formName="RelatorioRelacaoImoveisRotaActionForm" dynamicJavascript="true" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>	util.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>	Calendario.js"></script>

<script language="JavaScript">
	
function gerar(){
	var form = document.forms[0];
	if(form.idLocalidadeInicial.value == '' || form.cdSetorComercialInicial.value == '' || form.cdRotaInicial.value == '') { 
		alert('Preencha todos os campos.');
	} else {
		form.submit();
	}
}

</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');bloquearGrupo();">

<html:form action="/gerarRelatorioRelacaoImoveisRotaAction.do"
	name="RelatorioRelacaoImoveisRotaActionForm"
	type="gcom.gui.relatorio.cadastro.atualizacaocadastral.RelatorioRelacaoImoveisRotaActionForm" method="post">

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
					<td class="parabg">Relat&oacute;rio de Rela&ccedil;&atilde;o de Im&oacute;veis por Rota</td>
					<td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para gerar o relat&oacute;rio de rela&ccedil;&atilde;o de im&oacute;veis por rota, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="170"><strong>Localidade: </strong></td>
					<td>
						<html:text tabindex="5" maxlength="3" property="idLocalidadeInicial" size="5"
						onkeypress="somente_numero(this);form.target='';validaEnter(event, 'exibirGerarRelatorioRelacaoImoveisRota.do?filterClass=FiltroLocalidade&fieldLocalidade=LocalidadeInicial', 'idLocalidadeInicial');"
						onkeyup="javascript:somente_numero(this);" />
						
						<a href="javascript:limparCampos('idLocalidadeInicial', 'nomeLocalidadeInicial', 'cdSetorComercialInicial', 'nomeSetorComercialInicial', 'cdRotaInicial'); abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=origem', 400, 800);">
							<img border="0"	src="<bean:message key="caminho.imagens"/>pesquisa.gif" />
						</a>
						
						<html:text property="nomeLocalidadeInicial" size="35" readonly="true"
						style="background-color: #EFEFEF; border: 0; color: ${requestScope.corLocalidadeInicial}" />
						
						<a href="javascript:limparCampos('idLocalidadeInicial', 'nomeLocalidadeInicial', 'cdSetorComercialInicial', 'nomeSetorComercialInicial', 'cdRotaInicial');">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
						</a>
					</td>
				</tr>
				
				<tr>
					<td width="170"><strong>Setor Comercial:</strong></td>
					<td>
						<html:text maxlength="3" property="cdSetorComercialInicial" size="5" onkeyup="javascript:somente_numero(this);"
						onkeypress="validaEnterDependencia(event, 
	           			'exibirGerarRelatorioRelacaoImoveisRota.do?filterClass=FiltroSetorComercial&fieldLocalidade=LocalidadeInicial&fieldSetorComercial=SetorComercialInicial', 
	         			this, document.forms[0].idLocalidadeInicial.value, 'Localidade.');" tabindex="6" />
						
						<a href="javascript:abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidadeInicial.value+'&tipo=setorComercialOrigem',document.forms[0].idLocalidadeInicial.value,'Localidade Inicial', 400, 800);">
							<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar" />
						</a>
						
						<html:text property="nomeSetorComercialInicial" size="35" readonly="true"
						style="background-color: #EFEFEF; border: 0; color: ${requestScope.corSetorComercialInicial}" />
						
						<a href="javascript:limparCampos('cdSetorComercialInicial', 'nomeSetorComercialInicial');">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
						</a>
					</td>
				</tr>
				
				<tr>
					<td width="170"><strong>Rota: </strong></td>
					<td>
						<html:text maxlength="3" property="cdRotaInicial" size="5" tabindex="7" onkeyup="javascript:somente_numero(this);" />
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td>
						<input type="button" name="Button" class="bottonRightCol" value="Limpar"
						onclick="window.location.href='<html:rewrite page="/exibirGerarRelatorioRelacaoImoveisRota.do?menu=sim"/>'">
					</td>
					<td>
						<div align="right"><input type="Button" value="Gerar"
						onclick="javascript:gerar();" class="bottonRightCol" /></div>
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
