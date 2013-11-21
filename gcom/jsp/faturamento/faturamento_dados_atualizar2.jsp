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
    
    function consultar(){
		var form = document.forms[0];
		
		if(form.idImovel.value == '' && form.tipoMedicao.value == ''){
			alert('Informe Imóvel. /n Informe Tipo de Medição.');
		}else if(form.idImovel.value == '' && form.tipoMedicao.value != ''){
			alert('Informe Imóvel.');
		}else if(form.idImovel.value != '' && form.tipoMedicao.value == ''){
			alert('Informe Tipo de Medição.');
		}
		
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
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
				<td class="parabg">Consultar Hist&oacute;rico de
				Instala&ccedil;&atilde;o de Hidr&ocirc;metro</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<table width="100%" border="0">
			<tr>
				<td>Pesquisar um imóvel para atualizar os dados do faturamento:</td>
				<td colspan="3">&nbsp;</td>

			</tr>
		</table>

		<table width="100%" border="0">
			<tr>
				<td colspan="3" height="15"></td>
			</tr>

			<tr>
				<td colspan="3" height="15"></td>
			</tr>
			<tr>
				<td width="20%"><strong>Imovel:</strong></td>
				<td><html:text maxlength="9" property="idImovel" size="9"
					onkeypress="javascript:return validaEnter(event, 'exibirDadosFaturamentoAction.do?consultarImovel=1', 'idImovel');" />
				<img
					onclick="javascript:abrirPopup('exibirPesquisarImovelAction.do',500,800);"
					width="23" height="21" border="0" src="/gsan/imagens/pesquisa.gif" />
				</td>
			</tr>

			<tr>
				<td><strong>Tipo de Medi&ccedil;&atilde;o:</strong> <font color="#FF0000"> *</font></td>
				<td><html:select property="tipoMedicao">
					<html:options collection="medicaoHistorico"
						labelProperty="descricao" property="id" /></td>
			</tr>

			<tr>
				<td><strong>Im&oacute;vel Selecionado:</strong></td>
				<td><html:text maxlength="9" property="imovelSelecionado" size="9"
					value="54093004" readonly="readonly"
					style="background-color:#EFEFEF; border:0" /></td>
			</tr>
			<tr>
				<td height="10" width="200"><strong>Tipo de Medição:<font
					color="#FF0000">*</font></strong></td>
				<td colspan="2"><html:text maxlength="5" property="tipoMedicao"
					size="7" tabindex="3" value="tipoMedicao" readonly="true" /></td>

			</tr>
			<tr>
				<td colspan="2" bordercolor="#FFFFFF" bgcolor="#79BBFD"><strong>
				<div align="center">Endere&ccedil;o</div>
				</strong></td>
			</tr>
			<tr>
				<td>
				<div style="width: 100%; height: 100%; overflow: auto;"><!--corpo da segunda tabela--></div>
				<td>
				<table width="100%" align="center" bgcolor="#99CCFF">
					<tr bgcolor="#cbe5fe">
						<td>
						<div align="center"><span id="endereco"> ${requestScope.enderecoCompleto}
							<bean:write name="enderecoFormatado" scope="request" /></span></div>
						</logic:present> <logic:notPresent name="enderecoFormatado"
							scope="request">
										&nbsp;
										</logic:notPresent></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td colspan="2" bordercolor="#FFFFFF" bgcolor="#79BBFD"><strong>
				<div align="center">Dados do Faturamento</div>
				</strong></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td height="10" width="200"><strong>Localidade:<font color="#FF0000">*</font></strong></td>
				<td colspan="2"><html:text property="codigoLocalidade" maxlength="5"
					size="7" tabindex="3" readonly="readonly" /> <html:text
					property="nomeLocalidade" maxlength="30" size="30" tabindex="3"
					value="" readonly="readonly" /></td>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td height="10" width="200"><strong>Setor Comercial:<font
					color="#FF0000">*</font></strong></td>
				<td colspan="2"><html:text property="codigoSetorComercial"
					maxlength="5" size="7" tabindex="3" value="" readonly="readonly" />
				<html:text property="nomeSetorComercial" maxlength="30" size="30"
					tabindex="3" value="" readonly="readonly" /></td>
				<td colspan="2">&nbsp;</td>
			</tr>

			<tr>
				<td height="10" width="200"><strong>Leitura Anterior:<font
					color="#FF0000">*</font></strong></td>
				<td colspan="2"><html:text property="leituraAtualInformada"
					maxlength="5" size="7" tabindex="3" value="" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td height="10" width="200"><strong>Leiturista:<font color="#FF0000">*</font></strong></td>
				<td colspan="2"><html:text property="codigoLeiturista" maxlength="5"
					size="7" tabindex="3" value="" readonly="readonly" /> <html:text
					property="nomeLeiturista" maxlength="30" size="30" tabindex="3"
					value="" readonly="readonly" /></td>

				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="3"></td>
			</tr>
			<tr>
				<td height="10" width="200"><strong>Data Leitura Atual Informada:<font
					color="#FF0000">*</font></strong></td>
				<td colspan="2"><html:text property="dataLeituraAtualInformada"
					maxlength="10" size="11" tabindex="2" /> <a
					href="javascript:abrirCalendario('AlterarFaturamentoDadosActionForm', 'dataLeituraAtualInformada')">
				<img border="0" src="/gsan/imagens/calendario.gif" width="20"
					border="0" align="middle" alt="Exibir Calendário" /> </a>
				(dd/mm/aaaa)</td>
			</tr>
			<tr>
				<td colspan="3"></td>
			</tr>
			<tr>
				<td height="10" width="200"><strong>Leitura Atual Informada:<font
					color="#FF0000">*</font></strong></td>

				<td colspan="2"><html:text property="leituraAtualInformada"
					maxlength="5" size="7" tabindex="3" value=""></td>
			</tr>
			<tr>
				<td colspan="3"></td>
			</tr>
			<tr>
				<td><strong>Indicador de Confirma&ccedil;&atilde;o Leitura Atual
				Informada: <font color="#FF0000">*</font></strong></td>

				<td align="right">
				<div align="left"><html:radio property="indicadorConfirmacaoLeitura"
					value="rádio" /> <strong>Confirmada <html:radio
					property="indicadorConfirmacaoLeitura" value="rádio" /> N&atilde;o
				Confirmada</strong></div>
				</td>
			</tr>
			<tr>
				<td height="10" width="200"><strong>Situação de Leitura Atual:<font
					color="#FF0000">*</font></strong></td>

				<td colspan="2"><html:text property="leituraAtual" maxlength="5"
					size="7" tabindex="3" /></td>
			</tr>
			<tr>
				<td height="10" width="200"><strong>Consumo Medido do Mes:<font
					color="#FF0000">*</font></strong></td>
				<td colspan="2"><html:text property="consumoMedidoMes" maxlength="5"
					size="7" tabindex="3" /></td>
			</tr>
			<tr>
				<td height="10" width="200"><strong>Consumo Informado do Mes:<font
					color="#FF0000">*</font></strong></td>

				<td colspan="2"><html:text property="leituraAtual" maxlength="5"
					size="7" tabindex="3" /></td>
			</tr>
			<tr>
				<td colspan="3"></td>
			</tr>
			<tr>
				<td height="10">&nbsp;</td>
				<td><strong><font color="#FF0000">*</font></strong> Campos
				obrigat&oacute;rios</td>

				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="3" height="5"></td>
			</tr>
			<tr>
				<td colspan="3" align="right"><INPUT type="button"
					class="bottonRightCol" value="Atualizar" tabindex="6"
					onclick="validarForm(document.forms[0]);" style="width: 90px"></td>
			</tr>

			<!-- ============================================================================================================================== -->

		</table>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>
<%@ include file="/jsp/util/rodape.jsp"%></form>
</body>
</html:html>