<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="org.apache.struts.taglib.bean.IncludeTag"%>
<%@page import="gcom.util.ConstantesInterfaceGSAN"%><html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<gsan:i18n key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<gsan:i18n key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<gsan:i18n key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<gsan:i18n key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript">
<!-- Begin
function validarIntervalos(form) {

	if(comparaData(form.mesAnoReferenciaFinal.value,"<",form.mesAnoReferenciaInicial.value)){
		alert('<gsan:i18n key="<%=ConstantesInterfaceGSAN.ATENCAO_GSAN_CAMPO_FINAL_MENOR_CAMPO_INICIAL%>"
				arg0="<%=ConstantesInterfaceGSAN.LABEL_GSAN_DATA_LEITURA%>"/>');
				
		return false;
	}

	return validarIntervalosLocalizacao();
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    recuperarDadosPopupLocalizacao(codigoRegistro, descricaoRegistro, tipoConsulta);
} 

function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {
	 recuperarDadosQuatroParametrosSetor(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta);
}

function validarForm(){

	var form = document.forms[0];			

	if(!validateGerarRelatorioAnormalidadeLeituraPeriodoActionForm(form)){
		return;
	}

	if(!isCampoComboboxInformado(form.idAnormalidadeLeitura.value)){
		alert('<gsan:i18n key="<%=ConstantesInterfaceGSAN.ATENCAO_GSAN_INFORME_O_CAMPO%>"
				arg0="<%=ConstantesInterfaceGSAN.LABEL_GSAN_ANORMALIDADE_LEITURA%>"/>');

		return;
	}
	
	if(!validarIntervalos(form)){
		return;
	}

	toggleBox('demodiv',1);
}
-->
</script>

<html:javascript staticJavascript="false"
	formName="GerarRelatorioAnormalidadeLeituraPeriodoActionForm" />


</head>

<body leftmargin="5" topmargin="5">

<html:form action="/gerarRelatorioAnormalidadeLeituraPeriodoAction.do"
	name="GerarRelatorioAnormalidadeLeituraPeriodoActionForm"
	type="gcom.gui.micromedicao.GerarRelatorioAnormalidadeLeituraPeriodoActionForm"
	method="post">

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
					<td class="parabg">
							<gsan:i18n key="<%=ConstantesInterfaceGSAN.TITULO_RELATORIO_ANORMALIDADE_LEITURA_PERIODO%>"/>
					</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>
			
			<table width="100%" border="0">
				<tr>
					<td colspan="3">
						<gsan:i18n key="<%=ConstantesInterfaceGSAN.MSG_GSAN_RELATORIO_INFORMAR_DADOS_ABAIXO%>"/>:
					</td>
				</tr>

				<tr>
					<td width="200"><strong>
						<gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_RELATORIO_ANORMALIDADE_LEITURA_PERIODO_PERIODO_LEITURA%>"/>:<font color="#FF0000">*</font>
					</strong></td>
					<td colspan="3">
						<div align="left">
							<html:text property="mesAnoReferenciaInicial" size="8" maxlength="7" 
								onkeyup="mascaraAnoMes(this,event);replicaDados(document.forms[0].mesAnoReferenciaInicial, document.forms[0].mesAnoReferenciaFinal);"
								onkeypress="return isCampoNumerico(event);"/> 
	
							<strong> a </strong>
	
							<html:text property="mesAnoReferenciaFinal" size="8" maxlength="7"
								onkeyup="mascaraAnoMes(this,event)"  
								onkeypress="return isCampoNumerico(event);"/>(mm/aaaa)
						</div>
					</td>
				</tr>

				<tr>
					<td width="200">
						<strong><gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_ANORMALIDADE_LEITURA%>"/>:<font color="#FF0000">*</font></strong>
					</td>
					<td colspan="3"><html:select property="idAnormalidadeLeitura" style="width: 230px;">
						<logic:notEmpty name="colecaoAnormalidadesLeitura">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoAnormalidadesLeitura" labelProperty="descricao" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>

				<tr>
					<td colspan="3">
						<hr/>
					</td>
				</tr>

				<tr>
					<td width="200">
						<strong><gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_GRUPO_FATURAMENTO%>"/>:</strong>
					</td>
					<td colspan="3"><html:select property="idGrupoFaturamento" style="width: 230px;">
						<logic:notEmpty name="colecaoGruposFaturamento">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoGruposFaturamento" labelProperty="descricao" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>

				<jsp:include page="/jsp/util/imovel/imovel_filtros_de_gerencia_a_sequencial_rota.jsp">
					<jsp:param name="action" value="exibirGerarRelatorioAnormalidadeLeituraPeriodoAction.do"/>
					<jsp:param name="isExibirGerenciaRegional" value="false"/>
				</jsp:include>

				<tr>
					<td colspan="3">
						<hr/>
					</td>
				</tr>

				<tr>
					<td align="left">
						<input type="button" name="ButtonReset"
							class="bottonRightCol" value='<gsan:i18n key="<%=ConstantesInterfaceGSAN.BOTAO_GSAN_LIMPAR%>"/>'
							onclick="window.location.href='/gsan/exibirGerarRelatorioAnormalidadeLeituraPeriodoAction.do?menu=sim';"/>

						<input type="button" name="botaoCancelar"
							class="bottonRightCol" value="<gsan:i18n key="<%=ConstantesInterfaceGSAN.BOTAO_GSAN_CANCELAR%>"/>"
							onclick="window.location.href='/gsan/telaPrincipal.do';"/>

					</td>
					<td align="right" valign="top">
						<a href="javascript:validarForm();">
                        	<img align="right" border="0" src="<gsan:i18n key='caminho.imagens'/>print.gif"  title='<gsan:i18n key="<%=ConstantesInterfaceGSAN.BOTAO_GSAN_IMPRIMIR%>"/>'/>
						</a>
					</td>														

				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp">
		<jsp:param name="relatorio" value="gerarRelatorioAnormalidadeLeituraPeriodoAction.do"/>
	</jsp:include>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
