<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ include file="/jsp/util/telaespera.jsp"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.util.ConstantesInterfaceGSAN"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="org.apache.struts.taglib.bean.IncludeTag"%>
<%@page import="java.util.Locale"%><html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<gsan:i18n key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript"
	src="<gsan:i18n key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<gsan:i18n key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<gsan:i18n key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="GerarRelatorioJurosMultasDebitosCanceladosActionForm"/>
<script language="JavaScript">
<!-- Begin

function limparClienteResponsavel() {
	var form = document.GerarRelatorioJurosMultasDebitosCanceladosActionForm;
	form.idClienteResponsavel.value = "";
	form.nomeClienteResponsavel.value = "";	
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];

    if (tipoConsulta == 'localidade') {        
   		form.nomeLocalidade.style.color = "#000000";
   		form.idLocalidade.value = codigoRegistro;
   		form.nomeLocalidade.value = descricaoRegistro;

   		return;
    }

    if (tipoConsulta == 'usuario') {
   		form.nomeUsuarioCancelamento.style.color = "#000000";
	 	document.forms[0].idUsuarioCancelamento.value = codigoRegistro;
	 	document.forms[0].nomeUsuarioCancelamento.value = descricaoRegistro;
 	} 
} 

function validarForm(){
	var form = document.forms[0];

	if( !validateGerarRelatorioJurosMultasDebitosCanceladosActionForm(form)){
		return;
	}

	//submeterFormPadrao(form);
	botaoAvancarTelaEspera('/gsan/gerarRelatorioJurosMultasDebitosCanceladosAction.do');
}

function limparLocalidade() {
	var form = document.forms[0];
	form.idLocalidade.value = "";
	form.nomeLocalidade.value = "";	
}

function limparResponsavelCancelemanto() {
	var form = document.forms[0];
	form.idUsuarioCancelamento.value = "";
	form.nomeUsuarioCancelamento.value = "";	
}

/*necessário esse javascript visto que não se pode usar script's jsp
 * em algumas tags do struts, como por exemplo, html:text.
 */
function validaEnterMensagemInternacionalizada(evento,campo){

	if(campo == "localidade"){
		validaEnterComMensagem(evento, 'exibirGerarRelatorioJurosMultasDebitosCanceladosAction.do', 'idLocalidade', 
			'<gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE%>"/>');

		return;
	}

	if(campo == "responsavel"){
		validaEnterComMensagem(evento, 'exibirGerarRelatorioJurosMultasDebitosCanceladosAction.do', 'idUsuarioCancelamento', 
			'<gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_RELATORIO_JUROS_MULTAS_DEBITOS_CANCELADOS_RESPONSAVEL_CANCELAMENTO%>"/>');

		return;
	}
	
}


-->
</script>

</head>
<!-- leftmargin="5" topmargin="5" -->
<body>
<div id="formDiv"><html:form action="/gerarRelatorioJurosMultasDebitosCanceladosAction.do"
	name="GerarRelatorioJurosMultasDebitosCanceladosActionForm"
	type="gcom.gui.relatorio.faturamento.GerarRelatorioJurosMultasDebitosCanceladosActionForm"
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
					<td class="parabg"><gsan:i18n key="<%=ConstantesInterfaceGSAN.TITULO_RELATORIO_JUROS_MULTAS_DEBITOS_CANCELADOS%>"/></td>
					<td width="11" valign="top">
						<img border="0"
							src="imagens/parahead_right.gif" />
					</td>
				</tr>
			</table>

			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">
						<gsan:i18n key="<%=ConstantesInterfaceGSAN.MSG_GSAN_RELATORIO_INFORMAR_DADOS_ABAIXO%>"/>:
					</td>
				</tr>

				<tr>
					<td width="25%">
						<strong>
							<gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_MES_ANO_FATURAMENTO%>"/>:
						</strong>
					</td>					
					<td>
						<html:text maxlength="7"
							tabindex="1"
							property="mesAnoFaturamento" 
							size="7" onkeyup="mascaraAnoMes(this, event);"
							onkeypress="return isCampoNumerico(event);"/>
					</td>
				</tr>

				<tr>
					<td width="25%">
						<strong>
							<gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_RELATORIO_JUROS_MULTAS_DEBITOS_CANCELADOS_PERIODO_CANCELAMENTO%>"/>:
							<font color="#FF0000">*</font>
						</strong>
					</td>
					
					<td>
						<html:text
							property="dataCancelamentoInicial" size="10"
							maxlength="10"
							onkeyup="mascaraData(this,event);replicarCampo(document.forms[0].dataCancelamentoFinal,document.forms[0].dataCancelamentoInicial);"
							onkeypress="return isCampoNumerico(event);"/> 

						<a href="javascript:abrirCalendarioReplicando('GerarRelatorioJurosMultasDebitosCanceladosActionForm', 'dataCancelamentoInicial','dataCancelamentoFinal');"><img 
							border="0" src='<gsan:i18n key="caminho.imagens"/>calendario.gif'
							width="20" border="0" align="middle" 
							title='<gsan:i18n key="<%=ConstantesInterfaceGSAN.HINT_GSAN_EXIBIR_CALENDARIO%>"/>' />
						</a>

						<strong> a </strong>
						<html:text
							property="dataCancelamentoFinal" size="10"
							maxlength="10"
							onkeyup="mascaraData(this,event);"
							onkeypress="return isCampoNumerico(event);"/> 
	
						<a href="javascript:abrirCalendario('GerarRelatorioJurosMultasDebitosCanceladosActionForm', 'dataCancelamentoFinal')">
							<img border="0" src="<gsan:i18n key="caminho.imagens"/>calendario.gif"
								width="20" border="0" align="middle" title='<gsan:i18n key="<%=ConstantesInterfaceGSAN.HINT_GSAN_EXIBIR_CALENDARIO%>"/>' /></a>(dd/mm/aaaa)
					</td>

				</tr>

				<tr>
					<td colspan="2">
					<hr/>
					</td>
				</tr>

				<tr>
					<td width="20%">
						<strong>
							<gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_UNIDADE_NEGOCIO%>"/>:
						</strong>
					</td>					
					<td>
						<html:select property="idUnidadeNegocio">
							<logic:notEmpty name="GerarRelatorioJurosMultasDebitosCanceladosActionForm" property="colecaoUnidadesNegocios">
								<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
								<html:optionsCollection name="GerarRelatorioJurosMultasDebitosCanceladosActionForm" 
									property="colecaoUnidadesNegocios" label="nome" value="id"/>
							</logic:notEmpty>
						</html:select>
					</td>
				</tr>

				<tr>
					<td width="15%">
						<strong>
							<gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE%>"/>:
						</strong>
					</td>					
					<td>
						<html:text property="idLocalidade"
							size="5" maxlength="3" tabindex="6"
							onkeypress="validaEnterMensagemInternacionalizada(event, 'localidade'); return isCampoNumerico(event);" />
				
						<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade', 400, 800);">
							<img width="23" height="21" border="0" 
								src="<gsan:i18n key="caminho.imagens"/>pesquisa.gif" title="<gsan:i18n key="<%=ConstantesInterfaceGSAN.HINT_GSAN_PESQUISAR%>"/>" />
						</a> 
						
						<logic:equal name="GerarRelatorioJurosMultasDebitosCanceladosActionForm" property="localidadeExistente" value="true">
							<html:text property="nomeLocalidade" readonly="true"
								style="background-color:#EFEFEF; border:0" size="40"
								maxlength="40" />
						</logic:equal>
	
						<logic:equal name="GerarRelatorioJurosMultasDebitosCanceladosActionForm" property="localidadeExistente" value="false">
							<html:text property="nomeLocalidade" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								size="40" maxlength="40" />
						</logic:equal> 
										
						<a href="javascript:limparLocalidade();">
							<img src="<gsan:i18n key="caminho.imagens"/>limparcampo.gif"
								border="0" title='<gsan:i18n key="<%=ConstantesInterfaceGSAN.HINT_GSAN_APAGAR%>"/>' /> 
						</a> 
					</td>
				</tr>

				<tr>
					<td width="20%">
						<strong>
							<gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_TIPO_DEBITO%>"/>:
						</strong>
					</td>					
					<td>
						<html:select property="idsTiposDebito" multiple="multiple" size="4" style="width: 230px;">
							<logic:notEmpty name="GerarRelatorioJurosMultasDebitosCanceladosActionForm" property="colecaoTiposDebito">
								<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
								<html:optionsCollection name="GerarRelatorioJurosMultasDebitosCanceladosActionForm" 
									property="colecaoTiposDebito" label="descricao" value="id"/>
							</logic:notEmpty>
						</html:select>
					</td>
				</tr>

				<tr>
					<td width="15%">
						<strong>
							<gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_CATEGORIA%>"/>:
						</strong>
					</td>					
					<td>
						<html:select property="idsCategoria" multiple="multiple" size="4" style="width: 230px;">
							<logic:notEmpty name="GerarRelatorioJurosMultasDebitosCanceladosActionForm" property="colecaoCategorias">
								<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
								<html:optionsCollection name="GerarRelatorioJurosMultasDebitosCanceladosActionForm" 
									property="colecaoCategorias" label="descricao" value="id"/>
							</logic:notEmpty>
						</html:select>
					</td>
				</tr>

				<tr>
					<td width="20%">
						<strong>
							<gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_PERFIL_IMOVEL%>"/>:
						</strong>
					</td>					
					<td>
						<html:select property="idsPerfilImovel" multiple="multiple" size="4" style="width: 230px;">
							<logic:notEmpty name="GerarRelatorioJurosMultasDebitosCanceladosActionForm" property="colecaoPerfisImovel">
								<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
								<html:optionsCollection name="GerarRelatorioJurosMultasDebitosCanceladosActionForm" 
									property="colecaoPerfisImovel" label="descricao" value="id"/>
							</logic:notEmpty>
						</html:select>
					</td>
				</tr>

				<tr>
					<td width="20%">
						<strong>
							<gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_ESFERA_PODER%>"/>:
						</strong>
					</td>					
					<td>
						<html:select property="idsEsferaPoder" multiple="multiple" size="4" style="width: 230px;">
							<logic:notEmpty name="GerarRelatorioJurosMultasDebitosCanceladosActionForm" property="colecaoEsferasPoder">
								<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
								<html:optionsCollection name="GerarRelatorioJurosMultasDebitosCanceladosActionForm" 
									property="colecaoEsferasPoder" label="descricao" value="id"/>
							</logic:notEmpty>
						</html:select>
					</td>
				</tr>

				<tr>
					<td width="35%">
						<strong>
							<gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_RELATORIO_JUROS_MULTAS_DEBITOS_CANCELADOS_RESPONSAVEL_CANCELAMENTO%>"/>:
						</strong>
					</td>
					<td>
						<html:text property="idUsuarioCancelamento"
							size="7" maxlength="6"
							onkeypress="validaEnterMensagemInternacionalizada(event, 'responsavel'); return isCampoNumerico(event);" />
				
						<a href="javascript:abrirPopup('exibirUsuarioPesquisar.do', 250, 495);">
							<img width="23" height="21" border="0" 
								src="<gsan:i18n key="caminho.imagens"/>pesquisa.gif" title='<gsan:i18n key="<%=ConstantesInterfaceGSAN.HINT_GSAN_PESQUISAR%>"/>' />
						</a> 
							
						<logic:equal name="GerarRelatorioJurosMultasDebitosCanceladosActionForm" property="usuarioExistente" value="true">
							<html:text property="nomeUsuarioCancelamento" readonly="true"
								style="background-color:#EFEFEF; border:0" size="35"
								maxlength="40" />
						</logic:equal>

						<logic:equal name="GerarRelatorioJurosMultasDebitosCanceladosActionForm" property="usuarioExistente" value="false">
							<html:text property="nomeUsuarioCancelamento" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								size="35" maxlength="40" />
						</logic:equal> 
										
						<a href="javascript:limparResponsavelCancelemanto();">
							<img src="<gsan:i18n key="caminho.imagens"/>limparcampo.gif"
								border="0" title='<gsan:i18n key="<%=ConstantesInterfaceGSAN.HINT_GSAN_APAGAR%>"/>'/> 
						</a> 
					</td>
				</tr>

				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>

				<tr>
					<td>
						<input type="button" name="botaoLimpar"
							class="bottonRightCol" value="<gsan:i18n key="<%=ConstantesInterfaceGSAN.BOTAO_GSAN_LIMPAR%>"/>"
							onclick="window.location.href='/gsan/exibirGerarRelatorioJurosMultasDebitosCanceladosAction.do?menu=sim';"/>

						<input type="button" name="botaoCancelar"
							class="bottonRightCol" value="<gsan:i18n key="<%=ConstantesInterfaceGSAN.BOTAO_GSAN_CANCELAR%>"/>"
							onclick="window.location.href='/gsan/telaPrincipal.do';"/>
					</td>
					<td align="right">
						<gsan:controleAcessoBotao name="Button" value="<%=ConstantesInterfaceGSAN.BOTAO_GSAN_GERAR%>"
							onclick="validarForm();"
							url="gerarRelatorioJurosMultasDebitosCanceladosAction.do"/>
					</td>
					
					</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form></div>
</body>
</html:html>
