<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="gcom.util.ConstantesSistema"%>
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

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="GerarRelatorioContasEmRevisaoActionForm" />

<script language="JavaScript">

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];  
    if (tipoConsulta == 'funcionario') {
		form.idFuncionario.value = codigoRegistro;
		form.descricaoFuncionario.value = descricaoRegistro;
		form.descricaoFuncionario.style.color = "#000000";
   }
}


function limparForm(){
	limparFuncionario();
	document.forms[0].grupo[0].selected = "1";
}

function limparFuncionario(){
	var form = document.forms[0]; 
	form.idFuncionario.value = "";
	form.descricaoFuncionario.value = "";
	form.descricaoFuncionario.style.color = "#000000";
}

function validarForm(){
	 var form = document.forms[0]; 
	 form.submit();
}

function validateGerarRelatorioMultasAutosInfracaoPendentesActionForm(form){
	return true;
}

</script>

</head>

<body leftmargin="5" topmargin="5">
<div id="formDiv">
<html:form action="/gerarRelatorioMultasAutosInfracaoPendentesAction"
	name="GerarRelatorioMultasAutosInfracaoPendentesActionForm"
	type="gcom.gui.relatorio.faturamento.GerarRelatorioMultasAutosInfracaoPendentesActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
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
			<td width="600" valign="top" bgcolor="#003399" class="centercoltext">
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
					<td class="parabg">Gerar Relatórios das Multas de Auto de Infração Pendentes</td>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para gerar o relatório de multas de auto de infração, informe
					os dados abaixo: </td>
				</tr>
				<tr>
					<td width="100"><strong>Grupo:</strong></td>
	            	<td colspan="2">
			            <div align="left">
			                <strong>
				                <html:select property="grupo">
					                <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
   					                <logic:present name="colecaoGrupos" scope="session">
						            	<html:options collection="colecaoGrupos" labelProperty="descricao" property="id" />
					            	</logic:present>
								</html:select>
			                </strong>
			            </div>
	           		 </td>
				</tr>
				
				 <tr>
	                <td><strong>Funcionário:</strong></td>
	                <td colspan="2"><strong>
	                  <html:text maxlength="10"
	                  			 size="10"
	                  			 tabindex="1"
	                  			 property="idFuncionario"
	                  			 onkeypress="javascript:validaEnterComMensagem(event, 'exibirGerarRelatorioMultasAutosInfracaoPendentesAction.do?pesquisarFuncionario=OK', 'idFuncionario','Funcionário');return isCampoNumerico(event);" />
	                <a href="javascript:abrirPopup('exibirFuncionarioPesquisar.do', 330, 600);">
						<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						     title="Pesquisar Funcionário" /></a>
					<logic:notPresent name="funcionarioException" scope="request">	     
		     			<html:text
		     					   maxlength="30"
		     					   size="30"
		     					   readonly="true"
		     					   property="descricaoFuncionario"
   		     					   style="background-color:#EFEFEF; border:0; color: #000000"/>
	     			</logic:notPresent>
	     			<logic:present name="funcionarioException" scope="request">
		     			<html:text
		     					   maxlength="30"
		     					   size="30"
		     					   readonly="true"
		     					   property="descricaoFuncionario"
		     					   style="background-color:#EFEFEF; border:0; color: #ff0000"/>	
	     			</logic:present>	     
	     			</strong>
	     			<a href="javascript:limparFuncionario();">
						<img border="0" title="Apagar" src="/gsan/imagens/limparcampo.gif">
					</a>
	     		</td>
              </tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td align="left"><font color="#FF0000">*</font> Campos Obrigatórios</td>
			</tr>
			<tr>
				<td colspan="3">
	                	<input type="button" name="adicionar2" 
	                			class="bottonRightCol" 
	                			value="Limpar" 
	                			onclick="javascript:limparForm();">
	                	<input type="button" name="adicionar" 
				                class="bottonRightCol" 
				                value="Cancelar" 
				                onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
				               
                </td>
				<td align="right"><input type="button" name="Submit"
				class="bottonRightCol" value="Gerar" id="botaoGerar" onclick="javascript:validarForm();"></td>
			 </tr>
			
				
			</table>	
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
	<%@ include file="/jsp/util/telaespera.jsp"%>
	<script>
		document.getElementById('botaoGerar').onclick = function() { botaoAvancarTelaEspera('/gsan/gerarRelatorioMultasAutosInfracaoPendentesAction.do'); }
	</script>
</body>

</html:html>
