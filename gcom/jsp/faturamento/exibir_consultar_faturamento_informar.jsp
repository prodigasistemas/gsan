<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@page isELIgnored="false"%>
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
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
<!-- Begin
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
   
    if (tipoConsulta == 'imovel') {
      form.codigoImovel.value = codigoRegistro;
      //Faz o refresh da página para mostrar o endereço
      form.action = 'exibirConsultarHistoricoInstalacaoHidrometroInformarAction.do'
      form.submit();
    }
    
    if (tipoConsulta == 'hidrometro') {
      form.codigoHidrometro.value = codigoRegistro;
      form.descricaoHidrometro.value = descricaoRegistro;
      form.descricaoHidrometro.style.color = '#000000';
    }
    
  }
function validarForm(form){

/*	if(testarCampoValorZero(document.form.codigoImovel, 'Imovel') && 
	   testarCampoValorZero(document.form.codigoHidrometro, 'Hidrômetro')){
	   */
	   
	   //Verifica se os dois codigo não estão preenchidos
	   if (trim(document.form.codigoImovel.value) != '' && trim(document.form.codigoHidrometro.value) != '') {
			alert('Deve ser informado somente o imóvel ou o hidrômetro para consulta.');
			return false;
	   } else if (trim(document.form.codigoImovel.value) == '' && trim(document.form.codigoHidrometro.value) == ''){
   			alert('Deve ser informado o imóvel ou o hidrômetro para consulta.');
			return false;
	   }else {
	   		form.submit();	   
	   }
 //	}
}

	function limparImovel() {
        form.codigoImovel.value = "";        
        document.getElementById("endereco").innerHTML = '&nbsp;';
    }
    
    function limparHidrometro() {
        form.codigoHidrometro.value = "";
        form.descricaoHidrometro.value = "";
    }
    
-->
</script>
</head>
<body leftmargin="5" topmargin="5">
<form action="consultarHistoricoInstalacaoHidrometroAction.do"
	name="form" method="post"><%@ include file="/jsp/util/cabecalho.jsp"%>
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
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><html:img src="imagens/parahead_left.gif" border="0" /></td>

					<td class="parabg">Atualizar Dados do Faturamento</td>
					<td width="11" valign="top">
					<td></td>


				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">

				<tr>
					<td colspan="2">Pesquisar um im&oacute;vel para atualizar os dados
					do faturamento:</td>
				</tr>
				<tr>
					<td width="36%"><strong>Im&oacute;vel:</strong></td>
					<td><input type="text" maxlength="9" name="Imovel" size="9"
						value="${requestScope.Imovel}"
						onkeypress="javascript:return validaEnter(event, 'exibirDadosFaturamentoAction.do', 'idImovel');" /><img
						onclick="javascript:abrirPopup('exibirPesquisarImovelAction.do',500,800);"
						width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif" /></td>

				</tr>
				<!-- submit para toda recarga de pagina -->
				<tr>
					<td><strong>Tipo de Medi&ccedil;&atilde;o:</strong></td>
					<td><html:select property="tipoMedicao">
						<html:options collection="medicoesTipos" labelProperty="descricao"
							property="id" />

					</html:select> <span class="style1"> </span></td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong>Im&oacute;vel Selecionado:</strong></td>
					<td><input type="text" maxlength="9" name="imovelSelecionado"
						readonly="true" style="background-color:#EFEFEF; border:0"
						size="9" value="${AlterarFaturamentoDadosActionForm.idImovel}" /></td>

				</tr>
				<tr>
					<td><strong>Tipo de Medi&ccedil;&atilde;o:</strong></td>
					<td><input type="text" maxlength="25" name="tipoMedicaoLeitura"
						readonly="true"
						value="${AlterarFaturamentoDadosActionForm.tipoMedicao}"
						style="background-color:#EFEFEF; border:0" size="25" /></td>
				</tr>
				<tr>
					<td colspan="2">
					<table width="100%" bgcolor="#99CCFF" div align="center">
						<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
							<td><strong> Endere&ccedil;o </strong></td>
						</tr>
						<tr>
							<td bgcolor="#cbe5fe"><logic:present name="imovel"
								scope="session">
								<div align="center"><bean:write name="imovel"
									property="enderecoFormatado" scope="session" /></div>
							</logic:present> <logic:notPresent name="imovel" scope="session">
						&nbsp;
					</logic:notPresent></td>
						</tr>
					</table>
				</tr>


				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" bordercolor="#FFFFFF" bgcolor="#79BBFD"><strong>
					Dados do Faturamento</strong></td>
				</tr>
				<tr>
					<td><strong>Localidade:<font color="#FF0000"> </font></strong></td>
					<td><input type="text" maxlength="4" name="codigoLocalidade"
						readonly="true"
						value="${AlterarFaturamentoDadosActionForm.codigoLocalidade}"> <span
						class="style1" style="background-color:#EFEFEF; border:0" size="4" />
					<input type="text" maxlength="30" name="nomeLocalidade"
						readonly="true"
						value="${AlterarFaturamentoDadosActionForm.nomeLocalidade}"></span>

					<span class="style1"> </span></td>
				</tr>
				<tr>
					<td><strong>Setor Comercial:<font color="#FF0000"> </font></strong></td>
					<td><html:text maxlength="4" property="codigoSetorComercial"
						disabled="disabled" style="background-color:#EFEFEF; border:0"
						size="4" /> <span class="style1"> <html:text maxlength="30"
						property="nomeSetorComercial" disabled="disabled"
						style="background-color:#EFEFEF; border:0" size="30" /> </span> <span
						class="style1"> </span></td>
				</tr>

				<tr>
					<td><strong>Leitura Anterior:<font color="#FF0000"> *</font></strong></td>
					<td><html:text maxlength="8" property="leituraAnteriorFaturamento"
						size="8" /> <span class="style1"> </span></td>
				</tr>
				<tr>
					<td><strong>Data Leitura Anterior:<font color="#FF0000"> *</font></strong></td>

					<td><html:text maxlength="10"
						property="dataLeituraAnteriorFaturamento" size="10" /> <a
						href="javascript:abrirCalendario('InserirComandoAtividadeFaturamentoActionForm', 'vencimentoGrupo')">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /> </a>(dd/mm/aaaa)</td>
				</tr>
				<tr>
					<td><strong>Leiturista:<font color="#FF0000"> </font></strong></td>
					<td><html:text maxlength="9" property="codigoLeiturista"
						disabled="disabled" style="background-color:#EFEFEF; border:0"
						size="9" /> <span class="style1"> <html:text maxlength="40"
						property="nomeLeiturista" disabled="disabled"
						style="background-color:#EFEFEF; border:0" size="40" /> </span> <span
						class="style1"> </span></td>
				</tr>
				<tr>
					<td><strong>Leitura Atual Informada:<font color="#FF0000"> *</font></strong></td>
					<td><html:text maxlength="8" property="leituraAtualInformada"
						size="8" /> <span class="style1"> </span></td>
				</tr>

				<tr>
					<td><strong>Data Leitura Atual Informada:<font color="#FF0000"> * </font></strong></td>
					<td><html:text maxlength="10" property="dataLeituraAtualInformada"
						size="10" /> <a
						href="javascript:abrirCalendario('InserirComandoAtividadeFaturamentoActionForm', 'vencimentoGrupo')">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /> </a>(dd/mm/aaaa)</td>
				</tr>
				<tr>
					<td><strong>Indicador de Confirma&ccedil;&atilde;o Leitura Atual
					Informada: <font color="#FF0000">*</font></strong></td>

					<td align="right">
					<div align="left"><html:radio value="rádio"
						property="indicadorConfirmacaoLeitura" /> <strong>Confirmada <html:radio
						value="rádio" property="indicadorConfirmacaoLeitura" />
					N&atilde;o Confirmada</strong></div>
					</td>
				</tr>
				<tr>
					<td><strong>Situa&ccedil;&atilde;o de Leitura Atual:<font
						color="#FF0000"> * </font></strong></td>

					<td><html:text maxlength="2" property="codigoSituacaoLeituraAtual"
						disabled="disabled" style="background-color:#EFEFEF; border:0"
						size="2" /> <span class="style1"> <html:text maxlength="30"
						property="nomeSituacaoLeituraAtual" disabled="disabled"
						style="background-color:#EFEFEF; border:0" size="30" /> </span> <span
						class="style1"> </span></td>
				</tr>
				<tr>
					<td><strong>Anormalidade de Leitura Informada:<font color="#FF0000">
					* </font></strong></td>

					<td><input type="text" maxlength="4"
						name="codigoAnormalidadeLeituraInformada" size="4" readonly="true"
						value="${AlterarFaturamentoDadosActionForm.codigoAnormalidadeLeituraInformada}">
					<a><img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Anormalidade" /></a> <input type="text"
						maxlength="30" name="nomeAnormalidadeLeituraInformada"
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000"
						size="40"
						value="${AlterarFaturamentoDadosActionForm.nomeAnormalidadeLeituraInformada }" />
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></td>
				</tr>
				<tr>
					<td><strong>Consumo Medido do M&ecirc;s:<font color="#FF0000"> </font></strong></td>

					<td><html:text maxlength="8" property="consumoMedidoMes"
						disabled="disabled" style="background-color:#EFEFEF; border:0"
						size="8" /> <span class="style1"> </span></td>
				</tr>
				<tr>
					<td><strong>Consumo Informado do M&ecirc;s:<font color="#FF0000"> *
					</font></strong></td>
					<td><html:text maxlength="8" property="consumoInformadoMes"
						size="8" /> <span class="style1"> </span></td>

				</tr>
				<tr>
					<td class="style1">
					<p>&nbsp;</p>
					</td>
					<td class="style1">
					<table>
						<tr>
							<td width="500" align="right">
							<div align="left"><font color="#FF0000">*</font> Dados que podem
							ser alterados</div>
							</td>
							<td>&nbsp;</td>
						</tr>

					</table>
					</td>
				</tr>
				<tr>
					<td class="style1">
					<p>&nbsp;</p>
					</td>
					<td class="style1">
					<table>
						<tr>
							<td><input type="button" name="Button" class="bottonRightCol"
								value="Atualizar" /></td>
							<td>&nbsp;</td>
						</tr>

					</table>
					</td>
				</tr>
			</table>
			<p class="style1">&nbsp;</p>
		</tr>
		<tr>
			<td colspan="3" class="style1">
			<table width="770" cellspacing="0" cellpadding="0">
				<tr>
					<td height="4" colspan="3"></td>

				</tr>
				<tr>
					<td>
					<table width="100%" cellpadding="0" cellspacing="0" class="footer">
						<tr>
							<td height="20" align="left">&nbsp;Compesa</td>
							<td align="right">Powered by GPD/DDM&nbsp;</td>

						</tr>
					</table>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</body>
</html:form>
</html:html>
