<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.cadastro.tarifasocial.bean.CriterioSelecaoHelper"%>

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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="GerarResumoImoveisExcluidosTarifaSocialActionForm" />
<script>

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
		
		if (tipoConsulta == 'localidade') {

			form.codigoLocalidade.value = codigoRegistro;
	  		form.descricaoLocalidade.value = descricaoRegistro;
	  		form.descricaoLocalidade.style.color = "#000000";
	  		form.codigoLocalidade.focus();
	 	}
	}

function replicaDados(campoOrigem, campoDestino){
	campoDestino.value = campoOrigem.value;
}

function validarForm(){
	var form = document.forms[0];		
	form.action = 'gerarResumoImoveisExcluidosTarifaSocialAction.do';
	submeterFormPadrao(form);
}

function limparLocalidade() {
    var form = document.GerarResumoImoveisExcluidosTarifaSocialActionForm;
     form.codigoLocalidade.value = '';
     form.descricaoLocalidade.value = '';
}

	
function limparTela(){
	var form = document.forms[0];
	limparLocalidade();
	form.action = "/gsan/exibirGerarResumoImoveisExcluidosTarifaSocialAction.do?menu=sim";
	submeterFormPadrao(form);
}

function gerar(){
	var form = document.forms[0];		
	
	if (validateGerarResumoImoveisExcluidosTarifaSocialActionForm(form)){
		form.action = 'gerarResumoImoveisExcluidosTarifaSocialAction.do?acao=2&criterios='+ obterValorCheckboxMarcado();
		submeterFormPadrao(form);
	}
}

</script>


</head>

<body leftmargin="5" topmargin="5" >
<html:form action="/gerarResumoImoveisExcluidosTarifaSocialAction.do"
	name="GerarResumoImoveisExcluidosTarifaSocialActionForm"
	type="gcom.gui.relatorio.cadastro.GerarResumoImoveisExcluidosTarifaSocialActionForm"
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
					<td class="parabg">Resumo dos Imóveis Excluídos da Tarifa Social</td>
					<td width="11" valign="top"><img
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para gerar o resumo, informe os dados abaixo:</td>
				</tr>
			</table>
			
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td width="25%"><strong>Período do Comando:<font color="#FF0000">*</font></strong></td>
					
					<td><strong> 
					    <html:text property="anoMesPesquisaInicial" size="7"
						  onkeyup="mascaraAnoMes(this, event); replicaDados(document.GerarResumoImoveisExcluidosTarifaSocialActionForm.anoMesPesquisaInicial, document.GerarResumoImoveisExcluidosTarifaSocialActionForm.anoMesPesquisaFinal);somente_numero(this);"
						  onkeypress="return isCampoNumerico(event);" maxlength="7" />
						
				  	    a</strong> 
					
					    <html:text property="anoMesPesquisaFinal" size="7"
						  onkeyup="mascaraAnoMes(this, event); somente_numero(this);"
						  onkeypress="return isCampoNumerico(event);" maxlength="7"/> 
						
						(mm/aaaa)
					</td>
				</tr>
				<tr>
					<td><strong>Motivo da Exclusão:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:radio property="motivoExclusao" value="1" /> <strong>Não Comparecimento para Atualizar Cadastro </strong>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<html:radio property="motivoExclusao" value="2" /> <strong>Contas Vencidas </strong>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<html:radio property="motivoExclusao" value="3"/> <strong>Média de Consumo Superior a 10m </strong>
					</td>
				</tr>
				
				
				 <tr>
					<td HEIGHT="5"></td>
				</tr>
			 </table>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td HEIGHT="5"></td>
				</tr>
				<tr>
					<td bgcolor="#000000" height="1"></td>
				</tr>
				<tr>
					<td HEIGHT="5"></td>
				</tr>
			</table>
			
			<table width="100%" border="0">

              	<tr>
					<td><strong>Gerência:</strong></td>
					<td align="left"><strong><strong> 
						<html:select property="gerenciaRegionalId">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoGerenciaRegional" labelProperty="nome" property="id" />
					</html:select> </strong></strong></td>
				</tr>
				
				<tr>
					<td><strong>Unidade de Negócio:</strong></td>
					<td align="left"><strong><strong> 
						<html:select property="unidadeNegocioId">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id" />
					</html:select> </strong></strong></td>
				</tr>
              	
              		
				<tr>
					<td align="left"><strong> Localidade</strong></td>
					<td align="left">
						<html:text value="${requestScope.codigoLocalidade}"	property="codigoLocalidade" size="3" maxlength="3"
						onkeypress="validaEnter(event, 'exibirGerarResumoImoveisExcluidosTarifaSocialAction.do', 'codigoLocalidade');" />
					<img src="<bean:message key="caminho.imagens"/>pesquisa.gif" width="23" height="21" border="0"
						onclick="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do', 400, 800);">
					<input type="text" name="descricaoLocalidade" readonly
							style="background-color:#EFEFEF; border:0" size="40"
							maxlength="40" value="${requestScope.descricaoLocalidade}" />
					<c:if test="${empty requestScope.codigoLocalidade}" var="testeCor">
						<script>document.GerarResumoImoveisExcluidosTarifaSocialActionForm.descricaoLocalidade.style.color = '#FF0000';</script>
					</c:if>
					<c:if test="${!testeCor}">
						<script>document.GerarResumoImoveisExcluidosTarifaSocialActionForm.descricaoLocalidade.style.color = '#000000';</script>
					</c:if>
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						width="23" height="21" onclick="javascript:limparLocalidade();"></td>
				</tr>
              	<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campos Obrigatórios</td>
				</tr>				          					
				
			</table>
			

			<table width="100%" border="0">
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left" onclick="javascript:limparTela();">
					<input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						
						
					</td>

					<td align="right">
						<input name="Button" type="button" class="bottonRightCol"
						value="Gerar" align="left"
						onclick="javascript:gerar()"></td>
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
