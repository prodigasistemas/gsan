<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.ConstantesSistema"%>

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%-- Carrega validações do validator --%>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarEspecificacaoSituacaoImovelActionForm" />

<%-- Carrega javascripts da biblioteca --%>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<%-- Novos Javascripts --%>
<script language="JavaScript">

	function validaForm() {
		var form = document.forms[0];
		
		if (validateFiltrarEspecificacaoSituacaoImovelActionForm(form)) {
			submeterFormPadrao(form);
		}
	}


	function limparForm() {
		var form = document.forms[0];

		form.idEspecificacao.value = "";
		form.descricaoEspecificacao.value = "";
	}

	function verificarChecado(valor){
		form = document.forms[0];
		if(valor == "1"){
		 	form.indicadorAtualizar.checked = true;
		 }else{
		 	form.indicadorAtualizar.checked = false;
		}
	}

</script>
</head>

<body leftmargin="5" topmargin="5" 
	onload="setarFoco('${requestScope.nomeCampo}');verificarChecado('${sessionScope.indicadorAtualizar}');">

<html:form action="/filtrarEspecificacaoSituacaoImovelAction.do"
	name="FiltrarEspecificacaoSituacaoImovelActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.FiltrarEspecificacaoSituacaoImovelActionForm"
	method="post" 
	focus="idEspecificacao">

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
			<td width="615" valign="top" class="centercoltext">

			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11">
						<img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
					</td>

					<td class="parabg">
						Filtrar Especifica&ccedil;&atilde;o da Situa&ccedil;&atilde;o do Im&oacute;vel
					</td>
						
					<td width="11">
						<img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>

			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td height="31">
					<table width="100%" border="0" align="center">
						<tr>
							<td colspan="3">Para filtrar a especifica&ccedil;&atilde;o 
                 	 		da situa&ccedil;&atilde;o do im&oacute;vel , informe os dados 
                  			abaixo:</td>
							
							<td align="right">
								<html:checkbox property="indicadorAtualizar" value="1" /><strong>Atualizar</strong>
							</td>
						</tr>
						
						<tr>
							<td height="10" colspan="3">
							<hr>
							</td>
						</tr>
						
						<tr>
							<td colspan="2">
								<strong> Identificador da Especifica&ccedil;&atilde;o:</strong></td>
							<td>
								<html:text property="idEspecificacao" 
									size="4" 
									maxlength="3"/>
							</td>
						</tr>
						
						<tr>
							<td colspan="2">
								<strong>Descri&ccedil;&atilde;o da Especifica&ccedil;&atilde;o: </strong>
							</td>
							
							<td>
								<html:text property="descricaoEspecificacao" 
									size="30" 
									maxlength="30"/>							
							</td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
							<td>
								<html:radio property="tipoPesquisa"
									value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
								
								Iniciando pelo texto
								
								<html:radio property="tipoPesquisa"
									value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
								Contendo o texto
							</td>
						</tr>

						<tr>
							<td>
								<input type="button"
									name="Submit22" 
									class="bottonRightCol" 
									value="Limpar"
									onClick="javascript:limparForm();">
							</td>

							<td colspan="2" align="right">
								<gsan:controleAcessoBotao name="Botao" 
									value="Filtrar" 
									onclick="validaForm();" 
									url="filtrarEspecificacaoSituacaoImovelAction.do"/>
							</td>
						</tr>
					</table>
					<p>&nbsp;</p>
				</tr>
			</table>
			</td>
		</tr>
	</table>


	<!-- Rodapé -->
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
