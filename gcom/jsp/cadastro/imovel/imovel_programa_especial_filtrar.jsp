<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css"type="text/css">
<html:javascript staticJavascript="false" formName="FiltrarImovelProgramaEspecialActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'imovel') {
      form.action='exibirFiltrarImovelProgramaEspecialAction.do?pesquisarImovel=sim&idImovel='+codigoRegistro+"&atualizar="+form.atualizar.value+"&situacao="+form.situacao.value;
      form.submit();
    }
}

function limparPesquisaImovel() {
    var form = document.forms[0];
      form.action='exibirFiltrarImovelProgramaEspecialAction.do?menu=sim';
      form.submit();
}

function validarForm(form){
	
   	
   if(validateFiltrarImovelProgramaEspecialActionForm(form)){
           form.submit();
   }
	
     
}

function checkAtualizar(valor) {
	
	var form = document.forms[0];
		
	if (valor == '1') {
		form.atualizar.checked = true;
		form.atualizar.value = '1';
	} else {
		form.atualizar.checked = false;
		form.atualizar.value = '';
	}
		
}
function verificarValorAtualizar() {
	
	var form = document.forms[0];
	
	if (form.atualizar.checked == true) {
		form.atualizar.value = '1';
	} else {
		form.atualizar.value = '';
	}
}

</script>
</head>
<html:html>
<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');verificarValorAtualizar();checkAtualizar('${requestScope.atualizar}');">
<html:form action="/filtrarImovelProgramaEspecialAction.do" name="FiltrarImovelProgramaEspecialActionForm"
	type="gcom.gui.cadastro.imovel.FiltrarImovelProgramaEspecialActionForm" method="post">


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
					<td class="parabg">Filtrar Imóvel em Programa Especial</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
			<tr>
			<td>
				<table width="100%" border="0">
					<tr>
						<td>Para filtrar imóvel(is) no programa especial
						, informe os dados abaixo:</td>
						<td align="right" width="15%">
						<html:checkbox name="FiltrarImovelProgramaEspecialActionForm" 
						property="atualizar" value="1" 
						onclick="javacript:verificarValorAtualizar();"/><strong>Atualizar</strong>
					</tr>
				</table>
				<table width="100%" border="0">
				<tr>
					<td>
					<table width="100%" border="0">
						<tr>
							<td width="30%"><strong>Matrícula:</strong></td>
							<td width="70%"><html:text maxlength="9" property="matriculaImovel"
								size="10"
								onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarImovelProgramaEspecialAction.do?pesquisarImovel=sim&atualizar='+document.forms[0].atualizar.value, 'matriculaImovel','Matrícula');return isCampoNumerico(event);" />
							<a
								href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);">
							<img width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Imóvel" /></a> 
							<logic:present name="inscricaoImovelEncontrada" scope="session">
								<html:text property="inscricaoImovel" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									size="30" maxlength="30" />
							</logic:present> 
							<logic:notPresent name="inscricaoImovelEncontrada"
								scope="session">
								<html:text property="inscricaoImovel" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #FF0000"
									size="30" maxlength="30" />

							</logic:notPresent> <a href="javascript:limparPesquisaImovel();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar Imóvel" /></a></td>
						</tr>
						<tr>
						<td><strong>Data da apresentação dos documentos:</strong></td>
						<td><strong> <html:text maxlength="10"
							property="dataApresentacaoDocumentosInicial" size="10" tabindex="4"
							onkeyup="mascaraData(this, event);replicarCampo(document.forms[0].dataApresentacaoDocumentosFinal,document.forms[0].dataApresentacaoDocumentosInicial);" 
							onkeypress="return isCampoNumerico(event);"/>
						<a href="javascript:abrirCalendarioReplicando('FiltrarImovelProgramaEspecialActionForm', 'dataApresentacaoDocumentosInicial','dataApresentacaoDocumentosFinal');">
						<img border="0"
							src="<bean:message key="caminho.imagens"/>calendario.gif"
							width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
						</strong>a
						
						<strong> <html:text maxlength="10"
							property="dataApresentacaoDocumentosFinal" size="10" tabindex="4"
							onkeyup="mascaraData(this, event);"
							onkeypress="return isCampoNumerico(event);" />
						<a
						href="javascript:abrirCalendario('FiltrarImovelProgramaEspecialActionForm', 'dataApresentacaoDocumentosFinal');">
						<img border="0"
							src="<bean:message key="caminho.imagens"/>calendario.gif"
							width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
						</strong>
						dd/mm/aaaa
						</td>								
						</tr>
						<tr>
						<td><strong>Mês e Ano de entrada do programa:</strong></td>
						<td>
						<html:text property="mesAnoReferenciaEntradaPrograma" size="7" maxlength="7" 
						onkeyup="mascaraAnoMes(this,event);"
						onkeypress="return isCampoNumerico(event);"/>&nbsp;mm/aaaa
						</td>
						</tr>
						<tr>
						<td><strong>Mês e Ano de saída do programa:</strong></td>
						<td>
						<html:text property="mesAnoReferenciaSaidaPrograma" size="7" maxlength="7" 
						onkeyup="mascaraAnoMes(this,event);"
						onkeypress="return isCampoNumerico(event);"/>&nbsp;mm/aaaa
						</td>
						</tr>				
					</table>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" width="100%">
					<table width="100%">
						<tr>
							<td align="left" colspan="">
								<input type="button" name="Button"
									class="bottonRightCol" value="Limpar"
									onClick="window.location.href='/gsan/exibirFiltrarImovelProgramaEspecialAction.do?menu=sim'" />
								<input type="button" name="Button" class="bottonRightCol"
									value="Cancelar"
									onClick="window.location.href='/gsan/telaPrincipal.do'" />
							</td>
						    <td align="right">
								<div align="right">
								<input type="button" name="Button" class="bottonRightCol"
									value="Filtrar"
									onclick="javascript:validarForm(document.forms[0]);"/>
								</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>

	</table>
	</td>
	</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
