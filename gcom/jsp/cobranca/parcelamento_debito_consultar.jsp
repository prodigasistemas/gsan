<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.Util"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@page isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ParcelamentoDebitoActionForm"/>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">

	function voltar(){
		window.location.href="exibirConsultarListaParcelamentoDebitoAction.do?menu=sim";
	}
	 
	
	function desfazer(codigoParcelamento, codigoImovel, motivo) {
		if (confirm('Confirma a��o para Desfazer o parcelamento do im�vel ' + codigoImovel + '?')) {
			window.location.href='exibirDesfazerParcelamentoDebitoAction.do?codigoParcelamento='+codigoParcelamento+'&motivo='+motivo;
		}
	}

	function cancelar(codigoParcelamento, codigoImovel, motivo) {
		if (confirm('Confirma a��o para Cancelar o parcelamento do im�vel ' + codigoImovel + '?')) {
			window.location.href='cancelarParcelamentoAction.do?codigoParcelamento='+codigoParcelamento+'&codigoImovel='+codigoImovel+'&motivo='+motivo;
		}
	}

</script>

</head>

<logic:notPresent name="codigoImovel">
	<body leftmargin="5" topmargin="5">
</logic:notPresent>

<html:form action="/exibirConsultarParcelamentoDebitoAction.do"
	name="ParcelamentoDebitoActionForm"
	type="gcom.gui.cobranca.ParcelamentoDebitoActionForm" method="post">
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

		<td width="625" valign="top" class="centercoltext">
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
				<td class="parabg">Consultar Parcelamento de D�bitos</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>
		<p>&nbsp;</p>
		
			<table width="100%" border="0">
				<tr>
					<td colspan="3">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
							<td><strong>Dados do Im�vel:</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" border="0">
								<tr>
									<td height="10" width="150"><strong>Matr�cula do Im�vel:</strong></td>
									<td><html:text name="ParcelamentoDebitoActionForm"
										property="codigoImovel" size="25" readonly="true"
										style="background-color:#EFEFEF; border:0" /></td>
								</tr>
								<tr>
									<td height="10" width="150"><strong>Inscri��o:</strong></td>
									<td><html:text name="ParcelamentoDebitoActionForm"
										property="inscricao" size="35" readonly="true"
										style="background-color:#EFEFEF; border:0" /></td>
								</tr>
								<tr>
									<td height="10"><strong>Nome do Cliente Usu�rio:</strong></td>
									<td><html:text name="ParcelamentoDebitoActionForm"
										property="nomeCliente" size="45" readonly="true"
										style="background-color:#EFEFEF; border:0" /></td>
								</tr>
								<tr>
									<td height="10"><strong>Nome do Cliente Respons�vel:</strong></td>
									<td><html:text name="ParcelamentoDebitoActionForm"
										property="nomeClienteResponsavel" size="45" readonly="true"
										style="background-color:#EFEFEF; border:0" /></td>
								</tr>
								
								<tr>
									<td height="10"><strong>CPF ou CNPJ:</strong></td>
									<td>
										<html:text name="ParcelamentoDebitoActionForm" property="cpfCnpj" 
											size="35" readonly="true" style="background-color:#EFEFEF; border:0" />
									</td>
								</tr>
								<tr>
									<td height="10"><strong>Situa��o de �gua:</strong></td>
									<td><html:text name="ParcelamentoDebitoActionForm"
										property="situacaoAgua" size="35" readonly="true"
										style="background-color:#EFEFEF; border:0" /></td>
								</tr>
								<tr>
									<td height="10"><strong>Situa��o de Esgoto:</strong></td>
									<td><html:text name="ParcelamentoDebitoActionForm"
										property="situacaoEsgoto" size="35" readonly="true"
										style="background-color:#EFEFEF; border:0" /></td>
								</tr>
								<tr>
									<td height="10"><strong>Perfil do Im�vel:</strong></td>
									<td><html:text name="ParcelamentoDebitoActionForm"
										property="imovelPerfil" size="35" readonly="true"
										style="background-color:#EFEFEF; border:0" /></td>
								</tr>
								
								<tr><td>&nbsp;</td></tr>
								
								<tr>
									<td height="10"><strong>Situa��o do Parcelamento:</strong></td>
									<td><html:text name="ParcelamentoDebitoActionForm" property="situacao" size="35" readonly="true" style="background-color:#EFEFEF; border:0"/></td>
								</tr>
								
								<tr><td>&nbsp;</td></tr>
							</table>
							</td>
						</tr>
						<tr>
							<td align="center"><strong>Endere&ccedil;o de
							Correspond&ecirc;ncia</strong></td>
						</tr>
						<tr bgcolor="#FFFFFF">
							<td width="100%" align="center">
							<table width="100%" border="0">
								<tr>
									<td>
									<div align="center">
									<span id="enderecoFormatado"> 
										<logic:present name="enderecoFormatado" scope="request">
										<bean:write name="enderecoFormatado" scope="request" />
										</logic:present> 
									</span>
									</div>
									<logic:notPresent name="enderecoFormatado" scope="request">
									&nbsp;
									</logic:notPresent>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" height="10"></td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
							<td><strong>Quantidades de Parcelamentos / Reparcelamentos :</strong></td>
						</tr>
					</table>
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr bordercolor="#90c7fc">
							<td align="center" width="30%" bgcolor="#90c7fc" ><strong>Parcelamentos</strong></td>
							<td align="center" width="32%" bgcolor="#90c7fc" ><strong>Reparcelamentos</strong></td>
							<td align="center" width="38%" bgcolor="#90c7fc" ><strong>Reparcelamentos Consecutivos</strong></td>
						</tr>
						<tr bordercolor="#90c7fc">
							<td align="center" width="30%" bgcolor="#FFFFFF">
								<span id="parcelamento"> 
									<logic:notEmpty name="ParcelamentoDebitoActionForm" property="parcelamento">
										<bean:write	name="ParcelamentoDebitoActionForm" property="parcelamento"	scope="request" />
									</logic:notEmpty>
									<logic:empty name="ParcelamentoDebitoActionForm" property="parcelamento">
										0
									</logic:empty>
								</span>
							</td>
							<td align="center" width="32%" bgcolor="#FFFFFF">
								<span id="reparcelamento"> 
									<logic:notEmpty name="ParcelamentoDebitoActionForm" property="reparcelamento">
										<bean:write	name="ParcelamentoDebitoActionForm" property="reparcelamento" scope="request" />
									</logic:notEmpty>
									<logic:empty name="ParcelamentoDebitoActionForm" property="reparcelamento">
										0
									</logic:empty>
								</span>
							</td>
							<td align="center" width="38%" bgcolor="#FFFFFF">
								<span id="reparcelamentoConsecutivo"> 
									<logic:notEmpty name="ParcelamentoDebitoActionForm" property="reparcelamentoConsecutivo">
										<bean:write	name="ParcelamentoDebitoActionForm" property="reparcelamentoConsecutivo" scope="request" />
									</logic:notEmpty>
									<logic:empty name="ParcelamentoDebitoActionForm" property="reparcelamentoConsecutivo">
										0
									</logic:empty>
								</span>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" height="10"></td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr bordercolor="#90c7fc">
							<td width="50%" align="center" bgcolor="#90c7fc"><strong>Data e Hora do Parcelamento</strong></td>
							<td width="50%" align="center" bgcolor="#90c7fc"><strong>Data que foi Desfeito o Parcelamento</strong></td>
						</tr>
						<%String cor = "#cbe5fe";%>	
						<logic:present name="colecaoParcelamento">
						<logic:iterate name="colecaoParcelamento" id="parcelamento">
						<%if (cor.equalsIgnoreCase("#FFFFFF")) {
							cor = "#cbe5fe";%>
						<tr bgcolor="#cbe5fe">
							<%} else {
							cor = "#FFFFFF";%>
						<tr bgcolor="#FFFFFF">
							<%}%>
							<td width="50%" align="center"><bean:write name="parcelamento" property="parcelamento" formatKey="date.format"/> - <bean:write name="parcelamento" property="parcelamento" formatKey="hour.format"/></td>
							<td width="50%" align="center"><bean:write name="ParcelamentoDebitoActionForm" property="dataParcelamentoDesfeito" scope="request" /> </td>
						</tr>
						</logic:iterate>
						</logic:present>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" height="10"></td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr bordercolor="#90c7fc">
							<td bgcolor="#90c7fc"><strong>Valor dos D�bitos do Im�vel:</strong></td>
						</tr>
					</table>
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr bordercolor="#90c7fc">
							<td align="center" bgcolor="#90c7fc" rowspan="2"><strong>Contas</strong></td>
							<td rowspan="2" align="center" bgcolor="#90c7fc"><strong>Guias de Pagamento</strong></td>
							<td rowspan="2" align="center" bgcolor="#90c7fc"><strong>Acr&eacute;scimos
							Impontualidade</strong></td>
							<td colspan="2" align="center" bgcolor="#90c7fc"><strong>D&eacute;bitos a Cobrar</strong></td>
							<td rowspan="2" align="center" bgcolor="#90c7fc"><strong>Cr&eacute;ditos a Realizar</strong></td>
							<td rowspan="2" align="center" bgcolor="#90c7fc"><strong>D&eacute;bito Total
							Atualizado</strong></td>
						</tr>
						<tr bordercolor="#90c7fc">
							<td align="center" bgcolor="#cbe5fe"><strong>Servi&ccedil;o</strong></td>
							<td align="center" bgcolor="#cbe5fe"><strong>Parcelamento</strong></td>
						</tr>
						<% cor = "#cbe5fe";%>	
						<logic:present name="colecaoParcelamento">
						<logic:iterate name="colecaoParcelamento" id="parcelamento">
						<%if (cor.equalsIgnoreCase("#FFFFFF")) {
							cor = "#cbe5fe";%>
						<tr bgcolor="#cbe5fe">
							<%} else {
							cor = "#FFFFFF";%>
						<tr bgcolor="#FFFFFF">
							<%}%>
							<td align="right">
								<logic:notEqual name="parcelamento" property="valorConta" value="0">
									<a href="javascript:abrirPopup('exibirParcelamentoContasParceladasPopupAction.do?codigoParcelamento=<bean:write name="parcelamento"	property="id" />', 600, 800);">
										<bean:write name="parcelamento" property="valorConta" formatKey="money.format" />
									</a>
								</logic:notEqual>
								<logic:equal name="parcelamento" property="valorConta" value="0">
									<bean:write	name="parcelamento" property="valorConta" formatKey="money.format" />
								</logic:equal>
							</td>
							<td align="right">
								<logic:notEqual name="parcelamento" property="valorGuiaPapagamento" value="0">
									<a href="javascript:abrirPopup('exibirParcelamentoGuiasParceladasPopupAction.do?codigoParcelamento=<bean:write name="parcelamento" property="id" />', 600, 800);">
										<bean:write name="parcelamento" property="valorGuiaPapagamento" formatKey="money.format" />
									</a>
								</logic:notEqual>
								<logic:equal name="parcelamento" property="valorGuiaPapagamento" value="0">
									<bean:write	name="parcelamento" property="valorGuiaPapagamento" formatKey="money.format" />
								</logic:equal>
							</td>
							<td align="right">
								<bean:write name="parcelamento" property="valorAcrescimoImpontualidade" formatKey="money.format"/>
							</td>
							<td align="right">
								<logic:notEqual name="parcelamento" property="valorServicosACobrar" value="0">
									<a href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:define name="parcelamento"	property="imovel" id="imovel" /><bean:write name="imovel" property="id" />&parcelamentoID=<bean:write name="parcelamento" property="id" />', 600, 800);">
										<bean:write name="parcelamento" property="valorServicosACobrar"  formatKey="money.format"/>
									</a>
								</logic:notEqual>
								<logic:equal name="parcelamento" property="valorServicosACobrar" value="0">
									<bean:write	name="parcelamento" property="valorServicosACobrar" formatKey="money.format" />
								</logic:equal>
							</td>
							<td align="right">
								<logic:notEqual name="parcelamento" property="valorParcelamentosACobrar" value="0">
									<a href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:define name="parcelamento" property="imovel" id="imovel" /><bean:write name="imovel" property="id" />&parcelamentoID=<bean:write name="parcelamento" property="id" />', 600, 800);">
										<bean:write name="parcelamento" property="valorParcelamentosACobrar" formatKey="money.format"/>
									</a>
								</logic:notEqual>
								<logic:equal name="parcelamento" property="valorParcelamentosACobrar" value="0">
									<bean:write	name="parcelamento" property="valorParcelamentosACobrar" formatKey="money.format" />
								</logic:equal>
							</td>
							<td align="right">
								<logic:notEqual name="parcelamento" property="valorCreditoARealizar" value="0">
									<a href="javascript:abrirPopup('exibirConsultarCreditoARealizarAction.do?imovelID=<bean:define name="parcelamento" property="imovel" id="imovel" /><bean:write name="imovel" property="id" />&parcelamentoID=<bean:write name="parcelamento" property="id" />', 600, 800);">
										<bean:write name="parcelamento" property="valorCreditoARealizar" formatKey="money.format"/>
									</a>
								</logic:notEqual>
								<logic:equal name="parcelamento" property="valorCreditoARealizar" value="0">
									<bean:write	name="parcelamento" property="valorCreditoARealizar" formatKey="money.format" />
								</logic:equal>
							</td>
							<td align="right">
								<bean:write name="parcelamento" property="valorDebitoAtualizado"  formatKey="money.format"/>
							</td>
						</tr>
						</logic:iterate>
						</logic:present>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" height="10"></td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr bordercolor="#90c7fc">
							<td bgcolor="#90c7fc"><strong>Percentual e Valor dos Descontos Concedidos:</strong></td>
						</tr>
					</table>
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr bordercolor="#90c7fc">
							<td bgcolor="#90c7fc" align="center"><strong>Por Faixa de Refer�ncia da Conta</strong></td>
							<td bgcolor="#90c7fc" align="center"><strong>Nos Acr&eacute;scimos Impontualidade</strong></td>
							<td bgcolor="#90c7fc" align="center"><strong>Por Antiguidade do D&eacute;bito</strong></td>
							<td bgcolor="#90c7fc" align="center"><strong>Por Inatividade da Lig. &Aacute;gua</strong></td>
							<td bgcolor="#90c7fc" align="center"><strong>Por San��es Regulamentares</strong></td>
							<td bgcolor="#90c7fc" align="center"><strong>Por Tarifa Social</strong></td>
						</tr>
						<% cor = "#cbe5fe";%>	
						<logic:present name="colecaoParcelamento">
						<logic:iterate name="colecaoParcelamento" id="parcelamento">
						<%if (cor.equalsIgnoreCase("#FFFFFF")) {
								cor = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
								<%} else {
								cor = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
								<%}%>
							
							<td align="right">
								<logic:notEmpty name="parcelamento" property="valorDescontoFaixaReferenciaConta">	
									<bean:write name="parcelamento" property="valorDescontoFaixaReferenciaConta" formatKey="money.format"/>
								</logic:notEmpty>
								<logic:empty name="parcelamento" property="valorDescontoFaixaReferenciaConta">	
								   0,00
								</logic:empty>
							</td>
							
							<td align="right">
								<logic:notEmpty name="parcelamento" property="valorDescontoAcrescimos">	
									<bean:write name="parcelamento" property="valorDescontoAcrescimos" formatKey="money.format"/>
								</logic:notEmpty>
								<logic:empty name="parcelamento" property="valorDescontoAcrescimos">	
									0,00
								</logic:empty>
							</td>
							
							<td align="right">
								<logic:notEmpty name="parcelamento" property="valorDescontoAntiguidade">	
									<bean:write name="parcelamento" property="valorDescontoAntiguidade" formatKey="money.format"/>
								</logic:notEmpty>
								<logic:empty name="parcelamento" property="valorDescontoAntiguidade">	
								   0,00
								</logic:empty>
							</td>
							
							<td align="right">
								<logic:notEmpty name="parcelamento" property="valorDescontoInatividade">	
									<bean:write name="parcelamento" property="valorDescontoInatividade" formatKey="money.format"/>
								</logic:notEmpty>
								<logic:empty name="parcelamento" property="valorDescontoInatividade">	
								   0,00
								</logic:empty>
							</td>
							<td align="right">
								<logic:notEmpty name="parcelamento" property="valorDescontoSancao">	
									<bean:write name="parcelamento" property="valorDescontoSancao" formatKey="money.format"/>
								</logic:notEmpty>
								<logic:empty name="parcelamento" property="valorDescontoSancao">	
								   0,00
								</logic:empty>
							</td>
							<td align="right">
								<logic:notEmpty name="parcelamento" property="valorDescontoTarifaSocial">	
									<bean:write name="parcelamento" property="valorDescontoTarifaSocial" formatKey="money.format"/>
								</logic:notEmpty>
								<logic:empty name="parcelamento" property="valorDescontoTarifaSocial">	
								   0,00
								</logic:empty>
							</td>
						</tr>
						</logic:iterate>
						</logic:present>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" height="10"></td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr bordercolor="#90c7fc">
							<td width="15%" align="center" bgcolor="#90c7fc"><strong>Valor Negociado</strong></td>
							<td width="25%" align="center" bgcolor="#90c7fc"><strong>Forma de Cobran�a</strong></td>
							<td width="30%" align="center" bgcolor="#90c7fc"><strong>Usu�rio que Efetuou</strong></td>
							<td width="30%" align="center" bgcolor="#90c7fc"><strong>Usu�rio que Desfez</strong></td>							
						</tr>
						<% cor = "#cbe5fe";%>	
						<logic:present name="colecaoParcelamento">
						<logic:iterate name="colecaoParcelamento" id="parcelamento">
						<%if (cor.equalsIgnoreCase("#FFFFFF")) {
								cor = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
								<%} else {
								cor = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
								<%}%>
							
							<td width="10%" align="center">
								<logic:present name="parcelamento" property="valorNegociado">
									<bean:write name="parcelamento" property="valorNegociado" formatKey="money.format"/>
								</logic:present>	
							</td>
							
							<td width="30%" align="center"><bean:write name="parcelamento" property="cobrancaForma.descricao" /></td>
							
							<td width="30%" align="center">
								<logic:present name="parcelamento" property="usuario">
									<logic:present name="parcelamento" property="usuario.nomeUsuario">
										<bean:write name="parcelamento" property="usuario.nomeUsuario" />
									</logic:present>	
								</logic:present>
								<logic:notPresent name="parcelamento" property="usuario">
									<logic:present name="parcelamento" property="funcionario">
										<logic:present name="parcelamento" property="funcionario.nome">
											<bean:write name="parcelamento" property="funcionario.nome" />
										</logic:present>	
									</logic:present>
								</logic:notPresent>
							</td>
							
							<td width="30%" align="center">
								<logic:present name="parcelamento" property="usuarioDesfez">
									<logic:present name="parcelamento" property="usuarioDesfez.nomeUsuario">
										<bean:write name="parcelamento" property="usuarioDesfez.nomeUsuario" />
									</logic:present>	
								</logic:present>
								<logic:notPresent name="parcelamento" property="usuarioDesfez">
									<logic:present name="parcelamento" property="funcionario">
										<logic:present name="parcelamento" property="funcionario.nome">
											<bean:write name="parcelamento" property="funcionario.nome" />
										</logic:present>	
									</logic:present>
								</logic:notPresent>
							</td>
							
						</tr>
						</logic:iterate>
						</logic:present>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" height="10"></td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr bordercolor="#90c7fc">
							<td bgcolor="#90c7fc"><strong>Condi��es da Negocia��o:</strong></td>
						</tr>
					</table>
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr bordercolor="#90c7fc">
							<td align="center" bgcolor="#90c7fc"><strong>Valor da Entrada</strong></td>
							<td align="center" bgcolor="#90c7fc"><strong>Valor Parcelado</strong></td>
							<td align="center" bgcolor="#90c7fc"><strong>N�mero de Parcelas</strong></td>
							<td align="center" bgcolor="#90c7fc"><strong>Valor da Parcela</strong></td>
							<td align="center" bgcolor="#90c7fc"><strong>Valor dos Juros</strong></td>
							<td align="center" bgcolor="#90c7fc"><strong>Tx. Juros</strong></td>
						</tr>
						<% cor = "#cbe5fe";%>	
						<logic:present name="colecaoParcelamento">
						<logic:iterate name="colecaoParcelamento" id="parcelamento">
						<%if (cor.equalsIgnoreCase("#FFFFFF")) {
								cor = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
								<%} else {
								cor = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
								<%}%>
							<td align="right"><bean:write name="parcelamento" property="valorEntrada" formatKey="money.format"/></td>
							
							<td align="right">
							<logic:present name="parcelamento" property="valorParcelado">
								<bean:write name="parcelamento" property="valorParcelado" formatKey="money.format"/>
							</logic:present>	
							<logic:notPresent name="parcelamento" property="valorParcelado">
								&nbsp;
							</logic:notPresent>
							</td>
							<td align="right">
							<logic:present name="parcelamento" property="numeroPrestacoes">
								<bean:write name="parcelamento" property="numeroPrestacoes"/>
							</logic:present>	
							<logic:notPresent name="parcelamento" property="numeroPrestacoes">
								&nbsp;
							</logic:notPresent>
							</td>
							<td align="right">
							
							<logic:present name="parcelamento" property="valorPrestacao">
								<bean:write name="parcelamento" property="valorPrestacao" formatKey="money.format"/>
							</logic:present>	
							<logic:notPresent name="parcelamento" property="valorPrestacao">
								&nbsp;
							</logic:notPresent>
							

							</td>
							<td align="right">
								<logic:present name="parcelamento" property="valorJurosParcelamento">
									<bean:write name="parcelamento" property="valorJurosParcelamento" formatKey="money.format"/>
								</logic:present>	
								<logic:notPresent name="parcelamento" property="valorJurosParcelamento">
									&nbsp;
								</logic:notPresent>
							</td>
							<td align="right">
								<logic:notEmpty name="parcelamento" property="taxaJuros">	
									<bean:write name="parcelamento" property="taxaJuros" formatKey="money.format"/>%
								</logic:notEmpty>
								<logic:empty name="parcelamento" property="taxaJuros">	
								   0,00%
								</logic:empty>
							</td>
						</tr>
						</logic:iterate>
						</logic:present>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" height="10"></td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr bordercolor="#90c7fc">
							<td><strong>Indicadores:</strong></td>
						</tr>
					</table>
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr bordercolor="#90c7fc">
							<td align="center" bgcolor="#90c7fc"><strong>Restabelecimento</strong></td>
							<td align="center" bgcolor="#90c7fc"><strong>Contas em Revis�o</strong></td>
							<td align="center" bgcolor="#90c7fc"><strong>Guias de Pagamento</strong></td>
							<td align="center" bgcolor="#90c7fc"><strong>Acr�scimos por Impontualidade</strong></td>
							<td align="center" bgcolor="#90c7fc"><strong>D�bitos a Cobrar</strong></td>
							<td align="center" bgcolor="#90c7fc"><strong>Cr�ditos a Realizar</strong></td>
						</tr>
						<% cor = "#cbe5fe";%>	
						<logic:present name="colecaoParcelamento">
						<logic:iterate name="colecaoParcelamento" id="parcelamento">
						<%if (cor.equalsIgnoreCase("#FFFFFF")) {
								cor = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
								<%} else {
								cor = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
								<%}%>
							<td align="center">
								<logic:equal name="parcelamento" property="indicadorRestabelecimento" value="1">SIM</logic:equal>
								<logic:equal name="parcelamento" property="indicadorRestabelecimento" value="2">N�O</logic:equal>
								<logic:empty name="parcelamento" property="indicadorRestabelecimento">N�O</logic:empty>
							</td>
							<td align="center">
								<logic:equal name="parcelamento" property="indicadorContasRevisao" value="1">SIM</logic:equal>
								<logic:equal name="parcelamento" property="indicadorContasRevisao" value="2">N�O</logic:equal>
								<logic:empty name="parcelamento" property="indicadorContasRevisao">N�O</logic:empty>
							</td>
							<td align="center">
								<logic:equal name="parcelamento" property="indicadorGuiasPagamento" value="1">SIM</logic:equal>
								<logic:equal name="parcelamento" property="indicadorGuiasPagamento" value="2">N�O</logic:equal>
								<logic:empty name="parcelamento" property="indicadorGuiasPagamento">N�O</logic:empty>
							</td>
							<td align="center">
								<logic:equal name="parcelamento" property="indicadorAcrescimosImpontualdade" value="1">SIM</logic:equal>
								<logic:equal name="parcelamento" property="indicadorAcrescimosImpontualdade" value="2">N�O</logic:equal>
								<logic:empty name="parcelamento" property="indicadorAcrescimosImpontualdade">N�O</logic:empty>
							</td>
							<td align="center">
								<logic:equal name="parcelamento" property="indicadorDebitoACobrar" value="1">SIM</logic:equal>
								<logic:equal name="parcelamento" property="indicadorDebitoACobrar" value="2">N�O</logic:equal>
								<logic:empty name="parcelamento" property="indicadorDebitoACobrar">N�O</logic:empty>
							</td>
							<td align="center">
								<logic:equal name="parcelamento" property="indicadorCreditoARealizar" value="1">SIM</logic:equal>
								<logic:equal name="parcelamento" property="indicadorCreditoARealizar" value="2">N�O</logic:equal>
								<logic:empty name="parcelamento" property="indicadorCreditoARealizar">N�O</logic:empty>
							</td>
						</tr>
						</logic:iterate>
						</logic:present>
					</table>
					</td>
				</tr>
				
				<logic:equal name="isParcelamentoCancelado" value="true">
					<tr>
						<td colspan="3" height="10"></td>
					</tr>
					
					<tr>
						<td colspan="3">
						<table width="100%" align="center" bgcolor="#99CCFF" border="0">
							<tr>
								<td><strong>Dados de Cancelamento:</strong></td>
							</tr>
						</table>
						<table width="100%" align="center" bgcolor="#99CCFF" border="0">
							<tr bordercolor="#90c7fc">
								<td align="center" width="22%" bgcolor="#90c7fc" ><strong>Data e Hora</strong></td>
								<td align="center" width="39%" bgcolor="#90c7fc" ><strong>Usu�rio que Cancelou</strong></td>
								<td align="center" width="39%" bgcolor="#90c7fc" ><strong>Motivo de Cancelamento</strong></td>
							</tr>
							
							<tr bordercolor="#90c7fc">
								<td align="center" width="22%" bgcolor="#FFFFFF">
									<bean:write name="parcelamento" property="dataCancelamento" formatKey="date.format"/> - <bean:write name="parcelamento" property="dataCancelamento" formatKey="hour.format"/>
								</td>
								<td align="center" width="39%" bgcolor="#FFFFFF">
									<bean:write name="parcelamento" property="usuarioCancelamento.nomeUsuario" />
								</td>
								<td align="center" width="39%" bgcolor="#FFFFFF">
									<bean:write	name="parcelamento" property="motivoCancelamento.descricao" />
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</logic:equal>
				
				<tr>
					<td colspan="3" height="10"></td>
				</tr>
				<tr>
					<td colspan="3">
					<table border="0" width="100%">
						<tr>
							<td colspan="3" align="right">
								<input type="button" name="ButtonImprimir" class="bottonRightCol" value="Imprimir Termo" onClick="toggleBox('demodiv',1);">
								
								<logic:present name="buttonCartaoCredito" scope="session">
									<input type="button" name="" value="Consultar Dados Cart�o Cr�dito" class="bottonRightCol" 
										onclick="abrirPopup('consultarDadosParcelamentoCartaoCreditoAction.do?carregamentoInicial=ok',210,775);"/>			
				
								</logic:present>
								<logic:empty name="idsContaEP">
											<logic:equal name="entradaPaga" value="false">
												<logic:empty name="boletoParcelamento">
													<logic:equal name="geracaoBoletoBB" value="1"
														scope="request">
														<input type="button" name=""
															value="Imprimir Guia Pagto Entrada"
															class="bottonRightCol"
															onclick="javascript:window.location.href='${requestScope.linkBoletoBB}'"
															style="width: 180px" />
													</logic:equal>
													<logic:notEqual name="geracaoBoletoBB" value="1"
														scope="request">
														<input type="button" name=""
															value="Imprimir Guia Pagto Entrada"
															class="bottonRightCol"
															onclick="window.location.href='<html:rewrite page="/gerarRelatorioEmitirGuiaPagamentoActionParcelamento.do"/>'"
															style="width: 180px" />
													</logic:notEqual>
												</logic:empty>
												<logic:notEmpty name="boletoParcelamento">
													<input type="button" name=""
														value="Imprimir Guia Pagto Entrada" class="bottonRightCol"
														onclick="window.location.href='<html:rewrite page="/gerarRelatorioBoletoParcelamentoAction.do"/>'"
														style="width: 180px" />
												</logic:notEmpty>
											</logic:equal>
								</logic:empty>
								<logic:notEmpty name="idsContaEP">
									<input type="button" name="" value="Imprimir Contas EP" class="bottonRightCol" 
										onclick="window.location.href='<html:rewrite page="/gerarRelatorio2ViaContaAction.do?cobrarTaxaEmissaoConta=N"/>'"
										style="width:150px"/>
								</logic:notEmpty>
									
							</td>
						</tr>
						
						<logic:equal name="permiteDesfazer" value="true">
							<logic:present name="collectionParcelamentoMotivoDesfazer">
							<tr><td colspan="3"><hr></td></tr>
							
							<tr>
								<td width="30%" align="right"><strong>Motivo para Desfazer: </strong></td>
								
								<td width="45%" align="right">
									<html:select property="parcelamentoMotivoDesfazer" style="width: 280px;">
										<html:options collection="collectionParcelamentoMotivoDesfazer" labelProperty="descricaoParcelamentoMotivoDesfazer" property="id" />
									</html:select>
								</td>
								
								<td width="25%" align="right">
									<input type="button" value="Desfazer Parcelamento" onclick="desfazer(${parcelamento.id},${parcelamento.imovel.id},parcelamentoMotivoDesfazer.value);" class="bottonRightCol">
								</td>
							</tr>
							</logic:present>
						</logic:equal>
						
						<logic:equal name="permiteCancelar" value="true">
							<logic:present name="colecaoMotivoCancelamento">
								<tr><td colspan="3"><hr></td></tr>
								
								<tr>
									<td width="30%" align="right"><strong>Motivo para Cancelar: </strong></td>
										
									<td width="45%" align="right">
										<html:select property="parcelamentoMotivoCancelamento" style="width: 280px;">
											<html:options collection="colecaoMotivoCancelamento" labelProperty="descricao" property="id"/>
										</html:select>
									</td>
									
									<td width="25%" align="right">
										<input type="button" value="Cancelar Parcelamento" onclick="cancelar(${parcelamento.id},${parcelamento.imovel.id},parcelamentoMotivoCancelamento.value);" class="bottonRightCol">
									</td>
								</tr>
							</logic:present>
						</logic:equal>
						
						<tr><td colspan="3"><hr></td></tr>
						<tr>
							<td colspan="3">
								<input type="button" name="ButtonReset" class="bottonRightCol" value="Voltar" onClick="javascript:window.location.href='exibirConsultarListaParcelamentoDebitoAction.do?codigoImovel=${parcelamento.imovel.id}'">
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</td>
	</tr>
</table>
<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioParcelamentoAction.do" />
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
