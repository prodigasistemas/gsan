<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="CriterioCobrancaFiltrarActionForm"
	dynamicJavascript="false" />

<script language="JavaScript">

 var bCancel = false;

    function validateCriterioCobrancaFiltrarActionForm(form) {
        if (bCancel)
      return true;
        else
      return validateCaracterEspecial(form) && testarCampoValorZero(form.numeroAnoContaAntiga, 'Número de Anos para Determinar Conta Antiga') && validateLong(form) 
		&& validateDate(form);
   }

    function caracteresespeciais () {
     this.aa = new Array("descricaoCriterio", "Descrição do Critério de Cobrança possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     
    }

    function IntegerValidations () {
     this.ac = new Array("numeroAnoContaAntiga", "Número de Anos para Determinar Conta Antiga deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    function DateValidations () { 
	  this.aa = new Array("dataInicioVigencia", "Data de Início de Vigência do Critério inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    } 
     

 
function validarForm(form){

if(validateCriterioCobrancaFiltrarActionForm(form)){
    submeterFormPadrao(form);
}
}
function limpar(form){
form.action="exibirFiltrarCriterioCobrancaAction.do?limpar=1";
submeterFormPadrao(form);
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

<html:form action="/filtrarCriterioCobrancaAction"
	name="CriterioCobrancaFiltrarActionForm"
	type="gcom.gui.cobranca.CriterioCobrancaFiltrarActionForm"
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
					<td class="parabg">Filtrar Critério de Cobrança</td>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="2"></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">
					<table width="100%">
						<tr>
							<td width="80%">Para manter o(s) critério(s) de Cobrança, informe
							os dados abaixo:</td>
							<td align="right"><input type="checkbox"
								name="indicadorAtualizar" value="1" /><strong>Atualizar</strong>
							<p>&nbsp;</p>
							</td>
						</tr>
					</table>
					</td>


				</tr>
				<tr>
					<td width="40%"><strong>Descrição do Critério de Cobrança:<strong></td>
					<td><html:text property="descricaoCriterio" size="30"
						maxlength="30" tabindex="1" /></td>
				</tr>
				<tr>
					<td><strong>Data de In&iacute;cio de Vig&ecirc;ncia do
					Crit&eacute;rio:</strong></td>
					<td width="40%"><html:text maxlength="10"
						property="dataInicioVigencia" size="10"
						onkeyup="javascript:mascaraData(this,event);" tabindex="2" /> <img
						border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						onclick="abrirCalendario('CriterioCobrancaFiltrarActionForm', 'dataInicioVigencia')"
						width="20" border="0" align="middle" alt="Exibir Calendário" />
					 dd/mm/aaaa</td>
				</tr>
				<tr>
					<td>
					<p><strong>N&uacute;mero de Anos para Determinar Conta Antiga:</strong></p>
					</td>
					<td width="40%"><html:text property="numeroAnoContaAntiga" size="2"
						maxlength="2" tabindex="3" /></td>

				</tr>
				<tr>
					<td height="24" colspan="2"></td>
				</tr>
				<tr>
					<td colspan="2">
					<table width="100%">
						<tr>
							<td width="65%"><strong>Emiss&atilde;o da A&ccedil;&atilde;o para
							Im&oacute;vel com Situação Especial de Cobran&ccedil;a:</strong></td>
							<td><html:radio property="opcaoAcaoImovelSitEspecial" value="1"
								tabindex="4" /> <strong>Sim</strong></td>
							<td><html:radio property="opcaoAcaoImovelSitEspecial" value="2"
								tabindex="5" /> <strong>N&atilde;o</strong></td>
							<td><html:radio property="opcaoAcaoImovelSitEspecial" value="3"
								tabindex="6" /> <strong>Todos</strong></td>
						</tr>
						<tr>
							<td width="65%"><strong>Emiss&atilde;o da A&ccedil;&atilde;o para
							Im&oacute;vel com Situação de Cobran&ccedil;a:</strong></td>
							<td><html:radio property="opcaoAcaoImovelSit" value="1"
								tabindex="7" /> <strong>Sim</strong></td>
							<td><html:radio property="opcaoAcaoImovelSit" value="2"
								tabindex="8" /> <strong>N&atilde;o</strong></td>
							<td><html:radio property="opcaoAcaoImovelSit" value="3"
								tabindex="9" /> <strong>Todos</strong></td>
						</tr>
						<tr>
							<td width="65%"><strong>Considerar Contas em Revis&atilde;o:</strong></td>
							<td><html:radio property="opcaoContasRevisao" value="1"
								tabindex="10" /> <strong>Sim</strong></td>
							<td><html:radio property="opcaoContasRevisao" value="2"
								tabindex="11" /> <strong>N&atilde;o</strong></td>
							<td><html:radio property="opcaoContasRevisao" value="3"
								tabindex="12" /> <strong>Todos</strong></td>
						</tr>
						<tr>
							<td width="65%"><strong>Emiss&atilde;o da A&ccedil;&atilde;o para
							Im&oacute;vel com D&eacute;bito s&oacute; da Conta do M&ecirc;s:</strong></td>
							<td><html:radio property="opcaoAcaoImovelDebitoMesConta"
								value="1" tabindex="13" /> <strong>Sim</strong></td>
							<td><html:radio property="opcaoAcaoImovelDebitoMesConta"
								value="2" tabindex="14" /> <strong>N&atilde;o</strong></td>
							<td><html:radio property="opcaoAcaoImovelDebitoMesConta" value="3"
								tabindex="15" /> <strong>Todos</strong></td>
						</tr>
						<tr>
							<td width="65%"><strong>Emiss&atilde;o da A&ccedil;&atilde;o para
							Inquilino Com D&eacute;bito s&oacute; da Conta do M&ecirc;s
							Independentemente do Valor da Conta:</strong></td>
							<td><html:radio property="opcaoAcaoInquilinoDebitoMesConta"
								value="1" tabindex="16" /> <strong>Sim</strong></td>
							<td><html:radio property="opcaoAcaoInquilinoDebitoMesConta"
								value="2" tabindex="17" /> <strong>N&atilde;o</strong></td>
							<td><html:radio property="opcaoAcaoInquilinoDebitoMesConta" value="3"
								tabindex="18" /> <strong>Todos</strong></td>
						</tr>
						<tr>
							<td width="65%"><strong>Emiss&atilde;o da A&ccedil;&atilde;o para
							Im&oacute;vel com D&eacute;bito s&oacute; de Contas Antigas:</strong></td>

							<td><html:radio property="opcaoAcaoImovelDebitoContasAntigas"
								value="1" tabindex="19" /> <strong>Sim</strong></td>
							<td><html:radio property="opcaoAcaoImovelDebitoContasAntigas"
								value="2" tabindex="20" /> <strong>N&atilde;o</strong></td>
							<td><html:radio property="opcaoAcaoImovelDebitoContasAntigas" value="3"
								tabindex="21" /> <strong>Todos</strong></td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td height="24" colspan="2"></td>
				</tr>

				<tr>
					<td colspan="2"><strong>Indicador de Uso:</strong>&nbsp; <html:radio
						property="indicadorUso" value="1" tabindex="22" /> <strong>Ativo</strong>
					<html:radio property="indicadorUso" value="2" tabindex="23" /> <strong>Inativo</strong>
					<html:radio property="indicadorUso" value="3" tabindex="24" /> <strong>Todos</strong>

					</td>


				</tr>
				<tr>
					<td height="24" colspan="2"></td>
				</tr>

			</table>
			<table width="100%" border="0">
				<tr>
					<td valign="top"><input name="button" type="button"
						class="bottonRightCol" value="Limpar"
						onclick="limpar(document.forms[0]);">&nbsp;</td>
					<td valign="top">
					
					<div align="right">
					  <gsan:controleAcessoBotao name="botaoInserir" value="Filtrar" onclick="validarForm(document.forms[0]);" url="filtrarCriterioCobrancaAction.do" tabindex="25"/>
					  <%-- <input name="botaoInserir" type="button" class="bottonRightCol" value="Filtrar" onclick="validarForm(document.forms[0]);" tabindex="25"> --%>
					</div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
