<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<!-- <title>GCOM - Sistema de Gest&atilde;o Comercial</title> -->
<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false" formName="ImovelEmissaoOrdensSeletivasActionForm" dynamicJavascript="false" />
<script language="JavaScript">

 var bCancel = false;

function validarForm(form){

  if(validatePesquisarActionForm(form)){
    form.submit();
  }
}

</script>

<script>
<!-- Begin 

     var bCancel = false; 

    function validateImovelEmissaoOrdensSeletivasActionForm(form) {                                                                   
		if ( (validateInteger(form)) && (validateMesAno(form)) ) {
			
			if (form.mesAnoInstalacaoInicial.value == ''  &&
				form.mesAnoInstalacaoFinal.value != ''){
				alert('Informe o Mês/Ano Inicial de Instalação.');
				form.mesAnoInstalacaoInicial.focus();
				return false;
			}
			
			if (parseInt(form.numeroOcorrenciasConsecutivas.value) > 10) {
				alert('O Número de Ocorrências Consecutivas deve ser Menor ou Igual a 10.');
				form.numeroOcorrenciasConsecutivas.focus();
				return false;
			}
			
			
			form.marcaDescricao.value = form.marca.options[form.marca.selectedIndex].text;
			form.localInstalacaoDescricao.value = form.localInstalacao[form.localInstalacao.selectedIndex].text;
			
			return true;
		}else {
			return false;
		}
	}
	
	function IntegerValidations () { 
		this.aa = new Array("numeroOcorrenciasConsecutivas", "O número de Ocorrências Consecutivas deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	}
	
	function MesAnoValidations () { 
		this.aa = new Array("mesAnoInstalacaoInicial", "Mês/Ano de Instalação inválido. ", new Function ("varName", " return this[varName];"));
		this.aa = new Array("mesAnoInstalacaoFinal", "Mês/Ano de Instalação inválido. ", new Function ("varName", " return this[varName];"));
	}
    
    function ControleAnormalidade() {
    	var form = document.forms[0];
		
    	if (form.anormalidadeHidrometro.selectedIndex != 0 && form.anormalidadeHidrometro.selectedIndex != -1) {
    		form.numeroOcorrenciasConsecutivas.disabled = false;
    	}else {
    		form.numeroOcorrenciasConsecutivas.disabled = true;
    		form.numeroOcorrenciasConsecutivas.value = '';
    	}
    }
    
    function habilitarDesabilitarDemaisCampos(){
		var form = document.ImovelEmissaoOrdensSeletivasActionForm;

		if ('${requestScope.desabilitarCampos}' == 'ok'){
			//desabilita campos
			form.capacidade.disabled= true;
			form.marca.disabled= true;
			form.anormalidadeHidrometro.disabled= true;
			form.mesAnoInstalacaoInicial.disabled= true;
			form.mesAnoInstalacaoFinal.disabled= true;
			//limpa campos
			form.capacidade.selectedIndex = 0;
			form.marca.selectedIndex = 0;
			form.anormalidadeHidrometro.selectedIndex = 0;
			form.mesAnoInstalacaoInicial.value = "";
			form.mesAnoInstalacaoFinal.value = "";
		}
	}
	
	function preencherDataInstalacaoFinal(){
		
		var form = document.ImovelEmissaoOrdensSeletivasActionForm;
		
		form.mesAnoInstalacaoFinal.value = form.mesAnoInstalacaoInicial.value;
		
	}
    
-->    
</script>

</head>

<body leftmargin="5" topmargin="5" onload="ControleAnormalidade();">

<html:form action="/filtrarImovelEmissaoOrdensSeletivasWizardAction"
	name="ImovelEmissaoOrdensSeletivasActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.ImovelEmissaoOrdensSeletivasActionForm"
	method="post"
	onsubmit="return validateImovelEmissaoOrdensSeletivasActionForm(this);">

	<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=2" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<input type="hidden" name="numeroPagina" value="4" />	
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
			<td width="617" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><html:img src="imagens/parahead_left.gif" border="0" /></td>
					<td class="parabg">Filtrar Imóvel</td>
					<td width="11" valign="top"><html:img
						src="imagens/parahead_right.gif" border="0" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para filtrar o(s) im&oacute;vel(is) pelas
					caracter&iacute;sticas gerais, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="200"><strong>Capacidade:</strong></td>
					<td align="left">
						<table width="100%" border="0">
							<tr>
								<td>
									<strong>
										<html:select property="capacidade" style="width: 160px;" multiple="true" size="4">
											<html:option value="-1">&nbsp;</html:option>
											<html:options collection="collectionHidrometroCapacidade"
												labelProperty="descricao" property="id" />
										</html:select>
									</strong>
									<html:hidden property="capacidadeDescricao"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<td><strong>Marca:</strong></td>
					<td align="left">
						<table width="100%" border="0">
							<tr>
								<td>
									<strong>
										<html:select property="marca" style="width: 230px;">
											<html:option value="-1">&nbsp;</html:option>
											<html:options collection="collectionHidrometroMarca"
												labelProperty="descricao" property="id" />
										</html:select>
									</strong>
									<html:hidden property="marcaDescricao"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>

				<tr>
					<td width="200"><strong>Local de Instalação:</strong></td>
					<td align="left">
						<table width="100%" border="0">
							<tr>
								<td>
									<strong>
										<html:select property="localInstalacao" style="width: 160px;">
											<html:option value="-1">&nbsp;</html:option>
											<html:options collection="collectionHidrometroLocalInstalacao"
												labelProperty="descricao" property="id" />
										</html:select>
									</strong>
									<html:hidden property="localInstalacaoDescricao"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>

				<tr>
					<td><strong>Anormalidade de Leitura:</strong></td>
					<td align="left">
						<table width="100%" border="0">
							<tr>
								<td>
									<strong>
										<html:select property="anormalidadeHidrometro" style="width: 230px;"
											onchange="ControleAnormalidade();" multiple="true" size="4">
											<html:option value="-1">&nbsp;</html:option>
											<html:options collection="collectionHidrometroAnormalidade"
												labelProperty="descricao" property="id" />
										</html:select>
									</strong>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<td width="200"><strong>Num. Ocorr&ecirc;ncias Consecutivas:</strong></td>
					<td align="left">
						<table width="100%" border="0">
							<tr>
								<td><html:text property="numeroOcorrenciasConsecutivas" size="3" maxlength="2" disabled="true"
									onkeypress="return isCampoNumerico(event);"></html:text></td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<td width="200"><strong>Mês/Ano de Instala&ccedil;&atilde;o:</strong></td>
					<td align="left">
						<table width="100%" border="0">
							<tr>
								<td>
									<html:text property="mesAnoInstalacaoInicial" 
											   size="6" 
											   maxlength="7" 
											   onkeyup="mascaraAnoMes(this, event);preencherDataInstalacaoFinal();"
											   onblur ="preencherDataInstalacaoFinal();" 
											   onkeypress="return isCampoNumerico(event);">
									</html:text>&nbsp;à<!-- <strong>&nbsp;à</strong> -->
									
									<html:text property="mesAnoInstalacaoFinal" 
											   size="6" 
											   maxlength="7" 
											   onkeyup="mascaraAnoMes(this, event);" 
											   onkeypress="return isCampoNumerico(event);">
									</html:text>&nbsp;mm/aaaa <!-- <strong>&nbsp;MM/AAAA</strong> -->
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td align="left">&nbsp;</td>
				</tr>
				
				<tr>
					<td colspan="2">
						<div align="right">
							<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=2" />
						</div>
					</td>
				</tr>
			</table>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
	
	<logic:present name="tipoOrdem">
		<logic:equal name="tipoOrdem" value="INSTALACAO">
			<script>document.getElementById('2').style.display = 'none';</script>
		</logic:equal>
		<logic:notEqual name="tipoOrdem" value="INSTALACAO">
			<script>document.getElementById('2').style.display = '';</script>
		</logic:notEqual>
	</logic:present>
	<logic:notPresent name="tipoOrdem">
		<script>document.getElementById('2').style.display = 'none';</script>
	</logic:notPresent>
	
</body>
<script>
	var form = document.forms[0];
	if (form.mesAnoInstalacaoInicial.value != '') {
		if (form.mesAnoInstalacaoInicial.value.indexOf('/') < 0) {
			form.mesAnoInstalacaoInicial.value = form.mesAnoInstalacaoInicial.value.substr(4, 2) + '/' + form.mesAnoInstalacaoInicial.value.substr(0, 4);
		}
		//form.mesAnoInstalacaoFinal.value = '';
	}
	if (form.mesAnoInstalacaoFinal.value != '') {
		if (form.mesAnoInstalacaoFinal.value.indexOf('/') < 0) {
			form.mesAnoInstalacaoFinal.value = form.mesAnoInstalacaoFinal.value.substr(4, 2) + '/' + form.mesAnoInstalacaoFinal.value.substr(0, 4);
		}
	}
	
	habilitarDesabilitarDemaisCampos();
	
</script>
</html:form>
</html:html>
