<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ include file="/jsp/util/telaespera.jsp"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@page import="gcom.gui.relatorio.cobranca.FaixaHelper"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<gsan:i18n key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<html:javascript staticJavascript="false"  formName="RelatorioDocumentosAReceberForm"/>
<script language="JavaScript">
<!-- Begin

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];

    if (tipoConsulta == 'localidade') {        
   		form.nomeLocalidade.style.color = "#000000";
   		form.idLocalidade.value = codigoRegistro;
   		form.nomeLocalidade.value = descricaoRegistro;

   		return;
    }
} 

function validarForm(){
	var form = document.forms[0];

	if( validateRelatorioDocumentosAReceberForm(form) && validateFaixaRequired(form)){
		toggleBox('demodiv',1);
		<!-- botaoAvancarTelaEspera('/gsan/gerarRelatorioDocumentosAReceberAction.do'); -->
	}

	
}

document.onreadystatechange = function () {
	 var state = document.readyState
	 if (state == 'complete') {
		 var radios = document.getElementsByName( 'indicadorInclusaoValorSemParcelas' );
			for (i = 0; i < radios.length; i++) {
		        if (radios[i].type == 'radio' && radios[i].value == 1) {
		            radios[i].checked = 'checked';
		            break;
		        }
		    }
	 }
}

function validateFaixaRequired(form){
	var retorno = true;
	
	if(form.icInformouFaixa.value.toUpperCase()!='SIM'){
		alert("Informe faixa de dias vencidos para a geração do relatório.")
		return false;
	}
	return retorno;
	
}

function limparLocalidade() {
	var form = document.forms[0];
	form.idLocalidade.value = "";
	form.nomeLocalidade.value = "";	
}

function abrirPopupValidando(caminho, altura, largura){
	var form = document.forms[0];
	
	if(form.idLocalidade.disabled==false){
		abrirPopupDeNome(caminho, altura, largura, 'Pesquisar', 'yes');
	}
}

function bloquearCampos(){
	var form = document.forms[0];
	
	if(parseInt(form.idOpcaoTotalizacao.value) == 6 || parseInt(form.idOpcaoTotalizacao.value) == 7){
		form.idGerencia.disabled = false;
		form.idUnidade.disabled = true;
		form.idLocalidade.disabled = true;
		form.nomeLocalidade.disabled = true;
		limparLocalidade();
	}else if(parseInt(form.idOpcaoTotalizacao.value) == 8 || parseInt(form.idOpcaoTotalizacao.value) == 9){
		form.idGerencia.disabled = true;
		form.idUnidade.disabled = false;
		form.idLocalidade.disabled = true;
		form.nomeLocalidade.disabled = true;
		limparLocalidade();
	} else if(parseInt(form.idOpcaoTotalizacao.value) == 10){
		form.idGerencia.disabled = true;
		form.idUnidade.disabled = true;
		form.idLocalidade.disabled = false;
		form.nomeLocalidade.disabled = false;
	}else{
		form.idGerencia.disabled = true;
		form.idUnidade.disabled = true;
		form.idLocalidade.disabled = true;
		form.nomeLocalidade.disabled = true;
		limparLocalidade();
	}
}

function verificarFaixa(aux){
	var form = document.forms[0];
	if(aux!=undefined){
		if(aux.toUpperCase()=='SIM'){
			form.icInformouFaixa.value = 'SIM';
		}
	}
}
-->
</script>

</head>
<html:html>
<body onload="bloquearCampos();verificarFaixa('${requestScope.icInformouFaixa}')">
<div id="formDiv"><html:form action="/gerarRelatorioDocumentosAReceberAction.do"
	name="RelatorioDocumentosAReceberForm"
	type="gcom.gui.relatorio.cobranca.RelatorioDocumentosAReceberForm"
	method="post">
	
	<html:hidden property="icInformouFaixa"/>

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
					<td class="parabg">Gerar Relatório Resumo de Documentos a Receber</td>
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
						Para gerar o relatório, Informe os dados abaixo:
					</td>
				</tr>

				<tr>
					<td width="25%">
						<strong>
							Mês/Ano de Referência:<font color="#FF0000">*</font>
						</strong>
					</td>					
					<td>
						<html:text maxlength="7"
							tabindex="1"
							property="mesAno" 
							size="7" onkeyup="mascaraAnoMes(this, event);"
							onkeypress="return isCampoNumerico(event);"/>mm/aaaa
					</td>
				</tr>


				<tr>
					<td width="20%">
						<strong>
							Tipo de Categoria:
						</strong>
					</td>					
					<td>
						<html:select property="idCategoriaTipo">
							<logic:notEmpty name="RelatorioDocumentosAReceberForm" property="colecaoCategoriasTipo">
								<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
								<html:optionsCollection name="RelatorioDocumentosAReceberForm" 
									property="colecaoCategoriasTipo" label="descricao" value="id"/>
							</logic:notEmpty>
						</html:select>
					</td>
				</tr>

				<tr>
					<td width="15%">
						<strong>
							Categoria:
						</strong>
					</td>					
					<td>
						<html:select property="idsCategoria" multiple="multiple" size="4" style="width: 230px;">
							<logic:notEmpty name="RelatorioDocumentosAReceberForm" property="colecaoCategorias">
								<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
								<html:optionsCollection name="RelatorioDocumentosAReceberForm" 
									property="colecaoCategorias" label="descricao" value="id"/>
							</logic:notEmpty>
						</html:select>
					</td>
				</tr>

				<tr>
					<td width="20%">
						<strong>
							Perfil do Imóvel:
						</strong>
					</td>					
					<td>
						<html:select property="idsPerfilImovel" multiple="multiple" size="4" style="width: 230px;">
							<logic:notEmpty name="RelatorioDocumentosAReceberForm" property="colecaoPerfisImovel">
								<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
								<html:optionsCollection name="RelatorioDocumentosAReceberForm" 
									property="colecaoPerfisImovel" label="descricao" value="id"/>
							</logic:notEmpty>
						</html:select>
					</td>
				</tr>

				<tr>
					<td width="20%">
						<strong>
							Esfera de Poder:
						</strong>
					</td>					
					<td>
						<html:select property="idsEsferaPoder" multiple="multiple" size="4" style="width: 230px;">
							<logic:notEmpty name="RelatorioDocumentosAReceberForm" property="colecaoEsferasPoder">
								<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
								<html:optionsCollection name="RelatorioDocumentosAReceberForm" 
									property="colecaoEsferasPoder" label="descricao" value="id"/>
							</logic:notEmpty>
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				
				<tr>
					<td width="30%"><strong> Faixas de Dias Vencidos:<font color="#FF0000">*</font></strong></td>
					<td align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Adicionar" align="right"
						onclick="javascript:abrirPopup('exibirAdicionarFaixaAction.do?limpar=sim');" />
					</td>
				</tr>


				<tr>
					<td colspan="2">
						<table width="100%" bgcolor="#99CCFF">
							<tr bordercolor="#000000">
								<td align="center" width="10%" bgcolor="#90c7fc" rowspan="2"><strong>Remover</strong></td>
								<td align="center" width="40%" bgcolor="#90c7fc" rowspan="2"><strong>Descrição da Faixa</strong></td>
								<td align="center" width="50%" bgcolor="#90c7fc" colspan="2"><strong>Valores da Faixa</strong></td>
							</tr>
							<tr bordercolor="#000000">
								<td align="center" width="25%" bgcolor="#90c7fc"><strong>Inicial</strong></td>
								<td align="center" width="25%" bgcolor="#90c7fc"><strong>Final</strong></td>
							</tr>
							
							<%--Esquema de paginação--%>

							<logic:notEmpty name="RelatorioDocumentosAReceberForm" property="colecaoFaixas">
								<%int cont = 0;%>
								<logic:iterate  id="faixas" name="RelatorioDocumentosAReceberForm" property="colecaoFaixas" type="FaixaHelper">
								<%cont = cont + 1;
									if (cont % 2 == 0) {%>
								<tr bgcolor="#cbe5fe">						
									<%} else {%>
								<tr bgcolor="#FFFFFF">
									<%}%>
									<td width="10%">
										<div align="center"><font color="#333333"> <img width="14"
											height="14" border="0"
											src="<bean:message key="caminho.imagens"/>Error.gif"
											onclick="javascript:if(confirm('Confirma remoção?')){redirecionarSubmitSemUpperCase('removerFaixaAction.do?descricao=<bean:write name="faixas" property="descricao"/>&inicial=<bean:write name="faixas" property="valorInicial"/>&final=<bean:write name="faixas" property="valorFinal"/>');}" />
										</font></div>
									</td>
									<td width="40%">
										<div align="center"><bean:write name="faixas"
											property="descricao" /></div>
									</td>
									<td width="25%">
										<div align="center"><bean:write name="faixas"
											property="valorInicial" /></div>
									</td>
									<td width="25%">
										<div align="center"><bean:write name="faixas"
											property="valorFinal" /></div>
									</td>
								</tr>
								</logic:iterate>
							</logic:notEmpty>					
						</table>
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				
				<tr>
					<td width="20%">
						<strong>
							Opção de Totalização:<font color="#FF0000">*</font>
						</strong>
					</td>					
					<td>
						<html:select property="idOpcaoTotalizacao" onchange="bloquearCampos();">
							<logic:notEmpty name="RelatorioDocumentosAReceberForm" property="colecaoOpcoesTotalizacoes">
								<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
								<html:optionsCollection name="RelatorioDocumentosAReceberForm" 
									property="colecaoOpcoesTotalizacoes" label="descricao" value="id"/>
							</logic:notEmpty>
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td width="20%">
						<strong>
							Ger. Regional:
						</strong>
					</td>					
					<td>
						<html:select property="idGerencia">
							<logic:notEmpty name="RelatorioDocumentosAReceberForm" property="colecaoGerencias">
								<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
								<html:optionsCollection name="RelatorioDocumentosAReceberForm" 
									property="colecaoGerencias" label="nome" value="id"/>
							</logic:notEmpty>
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td width="20%">
						<strong>
							Unidade de Negócio:
						</strong>
					</td>					
					<td>
						<html:select property="idUnidade">
							<logic:notEmpty name="RelatorioDocumentosAReceberForm" property="colecaoUnidades">
								<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
								<html:optionsCollection name="RelatorioDocumentosAReceberForm" 
									property="colecaoUnidades" label="nome" value="id"/>
							</logic:notEmpty>
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td width="15%">
						<strong>
							Localidade:
						</strong>
					</td>					
					<td>
						<html:text property="idLocalidade"
							size="5" maxlength="3" tabindex="6"
							onkeypress="validaEnter(event, 'exibirGerarRelatorioDocumentosAReceberAction.do', 'idLocalidade'); return isCampoNumerico(event);" />
				
						<a href="javascript:abrirPopupValidando('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade', 400, 800);">
							<img width="23" height="21" border="0" 
								src="<gsan:i18n key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Localidade" />
						</a> 
						
						<logic:present name="localidadeEncontrada" scope="session">
							<html:text property="nomeLocalidade" readonly="true"
								style="background-color:#EFEFEF; border:0" size="40"
								maxlength="40" />
						</logic:present>
	
						<logic:notPresent name="localidadeEncontrada" scope="session">
							<html:text property="nomeLocalidade" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								size="40" maxlength="40" />
						</logic:notPresent>
										
						<a href="javascript:limparLocalidade();">
							<img src="<gsan:i18n key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Limpar Localidade" /> 
						</a> 
					</td>
				</tr>
				
				<tr>
					<td width="20%">
						<strong>
							Com Guia de Pagamento?
						</strong>
					</td>					
					<td>
						<strong>
							<html:radio property="indicadorGuiaPagamento" value="1"/>Sim
							<html:radio property="indicadorGuiaPagamento" value="2"/>N&atilde;o
						</strong>
					</td>
				</tr>
				
				<tr>
					<td width="20%">
						<strong>
							Com Valores de Debito e Credito Sem A Parcela Atual?
						</strong>
					</td>					
					<td>
						<strong>
							<html:radio property="indicadorInclusaoValorSemParcelas" value="1"/>Sim
							<html:radio property="indicadorInclusaoValorSemParcelas" value="2"/>N&atilde;o
						</strong>
					</td>
				</tr>
				
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
								
				<tr>
					<td><p>&nbsp;</p></td>
					<td align="right">
						<div align="right"><strong><font color="#FF0000">*</font></strong>
							Campos obrigat&oacute;rios</div>
					</td>
				</tr>

				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>

				<tr>
					<td>
						<input type="button" name="botaoCancelar"
							class="bottonRightCol" value="Cancelar"
							onclick="window.location.href='/gsan/telaPrincipal.do';"/>
					</td>
					<td align="right">
						<input type="button" 
							name="Button" 
							class="bottonRightCol"
							value="Gerar Resumo"
							onclick="javascript:validarForm();"/>
					</td>	
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioDocumentosAReceberAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form></div>
</body>
</html:html>
