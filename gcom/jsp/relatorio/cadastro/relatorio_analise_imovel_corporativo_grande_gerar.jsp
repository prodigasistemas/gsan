<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="GerarRelatorioAnaliseImovelCorporativoGrandeActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">





function limparLocalidadeInicial() {
	var form = document.GerarRelatorioAnaliseImovelCorporativoGrandeActionForm;
	form.idLocalidadeInicial.value = "";
	form.nomeLocalidadeInicial.value = "";	
}

function limparLocalidadeFinal() {
	var form = document.GerarRelatorioAnaliseImovelCorporativoGrandeActionForm;
	form.idLocalidadeFinal.value = "";
	form.nomeLocalidadeFinal.value = "";	
}

function limparSetorComercialInicial() {
	var form = document.forms[0];
	form.codigoSetorComercialInicial.value = "";
	form.nomeSetorComercialInicial.value = "";	
}

function limparSetorComercialFinal() {
	var form = document.forms[0];
	form.codigoSetorComercialFinal.value = "";
	form.nomeSetorComercialFinal.value = "";	
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];
   
     if (tipoConsulta == 'localidade') {
     
     if (form.tipoPesquisa.value == 'inicial') {
      form.idLocalidadeInicial.value = codigoRegistro;
      form.nomeLocalidadeInicial.value = descricaoRegistro;
      form.nomeLocalidadeInicial.style.color = "#000000";
      form.idLocalidadeFinal.value = codigoRegistro;
      form.nomeLocalidadeFinal.value = descricaoRegistro;
      form.nomeLocalidadeFinal.style.color = "#000000";
      form.idLocalidadeFinal.focus();
    } else {
 	  form.idLocalidadeFinal.value = codigoRegistro;
      form.nomeLocalidadeFinal.value = descricaoRegistro;
      form.nomeLocalidadeFinal.style.color = "#000000";
      form.referencia.focus();
    }
    
    }
    
    if (tipoConsulta == 'setorComercial') {
    	if (form.tipoPesquisa.value == 'inicial') {
			form.nomeSetorComercialInicial.style.color = "#000000";
			form.nomeSetorComercialInicial.value = descricaoRegistro;
			form.codigoSetorComercialInicial.value = codigoRegistro;
		}
	
		if (form.tipoPesquisa.value == 'final') {
			form.nomeSetorComercialFinal.style.color = "#000000";
			form.nomeSetorComercialFinal.value = descricaoRegistro;
			form.codigoSetorComercialFinal.value = codigoRegistro;
		}
    }
    
}

function verificarAnoMesReferencia(){
		var form = document.GerarRelatorioAnaliseImovelCorporativoGrandeActionForm;
       	
       	if (form.referencia.value.length > 0){
       		form.action = 'exibirGerarRelatorioAnaliseImovelCorporativoGrandeAction.do';
       		form.submit();
       	}
       	
}

function replicaDados(campoOrigem, campoDestino){
	campoDestino.value = campoOrigem.value;
}

function chamarPesquisaLocalidadeInicial() {
	document.forms[0].tipoPesquisa.value = 'inicial';
	abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade', 400, 800);
}

function chamarPesquisaLocalidadeFinal() {
	document.forms[0].tipoPesquisa.value = 'final';
	abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade', 400, 800);
}

function chamarPesquisaSetorComercialInicial() {
	document.forms[0].tipoPesquisa.value = 'inicial';
	abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidadeInicial.value+'&tipo=setorComercialInicial&indicadorUsoTodos=1',document.forms[0].idLocalidadeInicial.value,'Localidade Inicial', 400, 800);
}

function chamarPesquisaSetorComercialFinal() {
	document.forms[0].tipoPesquisa.value = 'final';
	abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidadeFinal.value+'&tipo=setorComercialFinal&indicadorUsoTodos=1',document.forms[0].idLocalidadeFinal.value,'Localidade Final', 400, 800);
}

function validarForm(form){
		
	if(validateGerarRelatorioAnaliseImovelCorporativoGrandeActionForm(form)){	    
		if(form.idLocalidadeInicial.value.length > 0 && form.idLocalidadeFinal.value.length == 0) {
			alert('Informe Localidade Final');
		} else if(form.idLocalidadeFinal.value.length > 0 && form.idLocalidadeInicial.value.length == 0) {
			alert('Informe Localidade Inicial');
		} else{
	    	submeterFormPadrao(form);
	    }
	}
}
</script>


</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form  action="/gerarRelatorioAnaliseImovelCorporativoGrandeAction" method="post" 
			name="GerarRelatorioAnaliseImovelCorporativoGrandeActionForm"
			type="gcom.gui.relatorio.cadastro.imovel.GerarRelatorioAnaliseImovelCorporativoGrandeActionForm"
			onsubmit="return validateGerarRelatorioAnaliseImovelCorporativoGrandeActionForm(this);">
	
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<input type="hidden" name="tipoPesquisa" />

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

			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Relatório</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>

					<td colspan="2">Para gerar Relatório, informe os dados gerais
					abaixo:</td>
				</tr>
				<tr>
					<td width="200">
						<strong>Referência:<font color="#FF0000">*</font></strong>
					</td>

					<td colspan="3">
						<html:text  property="referencia" size="7" maxlength="7" tabindex="8"
									onkeyup="mascaraAnoMes(this, event);" /> (mm/aaaa)
					</td>
				</tr>
				<tr>
					<td width="200">
						<strong>Gerência Regional:</strong>
					</td>
					<td colspan="3">
						<html:select property="regional" tabindex="1" style="width:200px;">
							<html:option value="-1"> &nbsp; </html:option>
							<html:options collection="colecaoGerenciaRegional" property="id" labelProperty="nome" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td width="200">
						<strong>Unidade de Negócio:</strong>
					</td>
					<td colspan="3">
						<html:select property="unidadeNegocio" tabindex="2" style="width:200px;">
							<html:option value="-1"> &nbsp; </html:option>
							<html:options collection="colecaoUnidadeNegocio" property="id" 
							labelProperty="nome" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td width="200">
						<strong>Localidade Inicial:</strong>
					</td>
					<td colspan="3">
						<strong> 
						<html:text property="idLocalidadeInicial" size="5" maxlength="3" tabindex="6"
						onkeyup="replicaDados(document.forms[0].idLocalidadeInicial, document.forms[0].idLocalidadeFinal);"
						onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioAnaliseImovelCorporativoGrandeAction.do', 'idLocalidadeInicial', 'Localidade Inicial');" />
						
						<a href="javascript:chamarPesquisaLocalidadeInicial();"><img
							width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar" /></a>
					
					<logic:present name="localidadeInicialInexistente" scope="request">
						<html:text property="nomeLocalidadeInicial" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" maxlength="40" />
					</logic:present> 
					
					<logic:notPresent name="localidadeInicialInexistente" scope="request">
						<html:text property="nomeLocalidadeInicial" readonly="true"
							style="background-color:#EFEFEF; border:0" size="40"
							maxlength="40" />
					</logic:notPresent> 
					
						<a href="javascript:limparLocalidadeInicial();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /> 
						</a> 
						</strong>
					</td>
				</tr>

				<tr>
					<td width="200">
						<strong>Localidade Final:</strong>
					</td>
					<td colspan="3">
						<strong> 
						<html:text property="idLocalidadeFinal" size="5" maxlength="3" tabindex="7"
						onkeypress= "validaEnterComMensagem(event, 'exibirGerarRelatorioAnaliseImovelCorporativoGrandeAction.do', 'idLocalidadeFinal', 'Localidade Final');"/>
						 
						<a href="javascript:chamarPesquisaLocalidadeFinal();">
							<img border="0" src="imagens/pesquisa.gif" height="21" width="23"></a>
					
					<logic:present name="localidadeFinalInexistente" scope="request">
						<html:text property="nomeLocalidadeFinal" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" maxlength="40" />
					</logic:present> 
					
					<logic:notPresent name="localidadeFinalInexistente" scope="request">
						<html:text property="nomeLocalidadeFinal" readonly="true"
							style="background-color:#EFEFEF; border:0" size="40"
							maxlength="40" />
					</logic:notPresent> 
					
						<a href="javascript:limparLocalidadeFinal();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /> 
						</a> 
					</strong>
					</td>
				</tr>
				
				<tr>
					<td width="200">
						<strong>Setor Comercial Inicial:</strong>
					</td>
					<td colspan="3">
						<strong> 
						<html:text property="codigoSetorComercialInicial" size="5" maxlength="4" tabindex="6"
						onkeyup="replicaDados(document.forms[0].codigoSetorComercialInicial, document.forms[0].codigoSetorComercialFinal);"
						onkeypress="return validaEnterDependencia(event, 'exibirGerarRelatorioAnaliseImovelCorporativoGrandeAction.do', this, document.forms[0].idLocalidadeInicial.value, 'Localidade Inicial');" />
						
						<a href="javascript:chamarPesquisaSetorComercialInicial();">
							<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar" /></a>
						
						<logic:present name="setorComercialInicialInexistente" scope="request">
							<html:text property="nomeSetorComercialInicial" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" size="40" maxlength="40" />
						</logic:present> 
					
						<logic:notPresent name="setorComercialInicialInexistente" scope="request">
							<html:text property="nomeSetorComercialInicial" readonly="true" style="background-color:#EFEFEF; border:0" size="40" maxlength="40" />
						</logic:notPresent> 
					
						<a href="javascript:limparSetorComercialInicial();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /> 
						</a> 
						</strong>
					</td>
				</tr>
                 
                <tr>
					<td width="200">
						<strong>Setor Comercial Final:</strong>
					</td>
					<td colspan="3">
						<strong> 
						<html:text property="codigoSetorComercialFinal" size="5" maxlength="4" tabindex="6"
						onkeypress="return validaEnterDependencia(event, 'exibirGerarRelatorioAnaliseImovelCorporativoGrandeAction.do', this, document.forms[0].idLocalidadeFinal.value, 'Localidade Final');" />
						
						<a href="javascript:chamarPesquisaSetorComercialFinal();">
							<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar" /></a>
						
						<logic:present name="setorComercialFinalInexistente" scope="request">
							<html:text property="nomeSetorComercialFinal" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" size="40" maxlength="40" />
						</logic:present> 
					
						<logic:notPresent name="setorComercialFinalInexistente" scope="request">
							<html:text property="nomeSetorComercialFinal" readonly="true" style="background-color:#EFEFEF; border:0" size="40" maxlength="40" />
						</logic:notPresent> 
					
						<a href="javascript:limparSetorComercialFinal();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /> 
						</a> 
						
						</strong>
					</td>
				</tr>          				
				<tr>
					<td width="200">
						<strong>Perfil do Imóvel:<font color="#FF0000">*</font></strong>
					</td>
					<td >
						<html:select property="idImovelPerfil" tabindex="9" style="width:200px;">
							<html:option value="-1">&nbsp;</html:option>
							<html:option value="1">GRANDE</html:option>
							<html:option value="3">CORPORATIVO</html:option>							
						</html:select>
					</td>
				</tr>
				<tr>
					<td width="200">
						<strong>Selecionar:</strong>
					</td>
					<td >
						<html:select property="selecionar" tabindex="9" style="width:200px;">
							<html:option value="-1">&nbsp;</html:option>
							<html:option value="1">CANDIDATOS</html:option>
							<html:option value="2">DEIXARÃO DE SER</html:option>							
						</html:select>
					</td>
				</tr>
			
				<tr>
					<td height="19">
						<strong> <font color="#FF0000"></font></strong>
					</td>
					<td align="right">
						<div align="left">
							<strong><font color="#FF0000">*</font></strong> Campos obrigat&oacute;rios
						</div>
					</td>
				</tr>
				
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="window.location.href='/gsan/exibirGerarRelatorioAnaliseImovelCorporativoGrandeAction.do?menu=sim'" tabindex="17"></td>
					<td align="right" colspan="2">
						<input name="Button" type="button" class="bottonRightCol" value="Gerar" align="left" tabindex="16"
						onclick="validarForm(document.forms[0]);"></td>
					<td align="right"></td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>
</html:form>
</body>
</html:html>

