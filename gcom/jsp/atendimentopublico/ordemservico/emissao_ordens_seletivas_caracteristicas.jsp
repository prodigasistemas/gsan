<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false" 
	formName="ImovelEmissaoOrdensSeletivasActionForm" 
	dynamicJavascript="false" />



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
		
		if (validateInteiroZeroPositivo(form)) {
			if (!VerificaIntervalosPreenchidos(form.intervaloQuantidadeEconomiasInicial,
					form.intervaloQuantidadeEconomiasFinal,
					'Verifique o preenchimento do Intervalo de Quantidade de Economias')) {
					
				return false;
			}
			
			if (!VerificaIntervalosPreenchidos(form.intervaloQuantidadeDocumentosInicial,
					form.intervaloQuantidadeDocumentosFinal,
					'Verifique o preenchimento do Intervalo de Quantidade de Documentos')) {
					
				return false;
			}
			
			if (!VerificaIntervalosPreenchidos(form.intervaloNumeroMoradoresInicial,
					form.intervaloNumeroMoradoresFinal,
					'Verifique o preenchimento do Intervalo de Número de Moradores')) {
					
				return false;
			}
			
			if (!VerificaIntervalosPreenchidos(form.consumoPorEconomia,
					form.consumoPorEconomiaFinal,
					'Verifique o preenchimento do Intervalo de Consumo por Economia')) {
					
				return false;
			}
			
			
			if ( parseInt(form.intervaloQuantidadeEconomiasFinal.value) <
				 parseInt(form.intervaloQuantidadeEconomiasInicial.value) ) {
	
				alert('O Intervalo de Quantidade de Economia Final deve ser Maior ou Igual a Inicial.');
				form.intervaloQuantidadeEconomiasFinal.focus();
				return false;
			}
			
			if ( parseInt(form.intervaloQuantidadeDocumentosFinal.value) <
				 parseInt(form.intervaloQuantidadeDocumentosInicial.value) ) {
	
				alert('O Intervalo de Quantidade de Documento Final deve ser Maior ou Igual a Inicial.');
				form.intervaloQuantidadeDocumentosFinal.focus();
				return false;
			}
			
			if ( parseInt(form.intervaloNumeroMoradoresFinal.value) <
				 parseInt(form.intervaloNumeroMoradoresInicial.value) ) {
	
				alert('O Intervalo de Número de Moradores Final deve ser Maior ou Igual a Inicial.');
				form.intervaloNumeroMoradoresFinal.focus();
				return false;
			}

			if ( parseInt(form.consumoPorEconomiaFinal.value) <
				 parseInt(form.consumoPorEconomia.value) ) {
	
				alert('O Intervalo de Consumo Por Economia Final deve ser Maior ou Igual a Inicial.');
				form.consumoPorEconomiaFinal.focus();
				return false;
			}
			
			var intervaloAreaConstruidaInicial = form.intervaloAreaConstruidaInicial.value;
			var intervaloAreaConstruidaFinal = form.intervaloAreaConstruidaFinal.value;
			
			intervaloAreaConstruidaInicial = intervaloAreaConstruidaInicial.replace('.', '');
			intervaloAreaConstruidaInicial = intervaloAreaConstruidaInicial.replace(',', '');
			intervaloAreaConstruidaFinal = intervaloAreaConstruidaFinal.replace('.', '');
			intervaloAreaConstruidaFinal = intervaloAreaConstruidaFinal.replace(',', '');
			
			if ( parseInt(intervaloAreaConstruidaFinal) < parseInt(intervaloAreaConstruidaInicial) ) {
				alert('O Intervalo da Área Construida Final deve ser Maior ou Igual a Inicial.');
				form.intervaloAreaConstruidaFinal.focus();
				return false;
			}
	      	
	      	form.perfilImovelDescricao.value = form.perfilImovel.options[form.perfilImovel.selectedIndex].text;
	      	form.categoriaDescricao.value = form.categoria.options[form.categoria.selectedIndex].text;
	      	form.subCategoriaDescricao.value = form.subCategoria.options[form.subCategoria.selectedIndex].text;
	      	form.tipoMedicaoDescricao.value = form.tipoMedicao.options[form.tipoMedicao.selectedIndex].text;
	      	
			return true;
		}else {
			return false;
		}
	}

	function InteiroZeroPositivoValidations () { 
		this.aa = new Array("intervaloQuantidadeEconomiasInicial", "A Quantidade Inicial de Economias deve somente conter números positivos ou zero.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("intervaloQuantidadeEconomiasFinal", "A Quantidade Final de Economias deve somente conter números positivos ou zero.", new Function ("varName", " return this[varName];"));
		this.ac = new Array("intervaloQuantidadeDocumentosInicial", "A Quantidade Inicial de Documentos deve somente conter números positivos ou zero.", new Function ("varName", " return this[varName];"));
		this.ad = new Array("intervaloQuantidadeDocumentosFinal", "A Quantidade Final de Documentos deve somente conter números positivos ou zero.", new Function ("varName", " return this[varName];"));
		this.ae = new Array("intervaloNumeroMoradoresInicial", "A Quantidade Inicial de Moradores deve somente conter números positivos ou zero.", new Function ("varName", " return this[varName];"));
		this.af = new Array("intervaloNumeroMoradoresFinal", "A Quantidade Final de Moradores deve somente conter números positivos ou zero.", new Function ("varName", " return this[varName];"));
		this.ag = new Array("intervaloAreaConstruidaInicial", "O Intervalo Inicial de Área Construida deve somente conter números positivos ou zero.", new Function ("varName", " return this[varName];"));
		this.ah = new Array("intervaloAreaConstruidaFinal", "O Intervalo Final de Área Construida deve somente conter números positivos ou zero.", new Function ("varName", " return this[varName];"));
		this.ai = new Array("mediaImovel", "A Média do Imóvel deve somente conter números positivos ou zero.", new Function ("varName", " return this[varName];"));
		this.aj = new Array("consumoPorEconomia", "O Consumo Por Economia deve somente conter números positivos ou zero.", new Function ("varName", " return this[varName];"));
		this.ak = new Array("consumoPorEconomiaFinal", "O Consumo Por Economia Final deve somente conter números positivos ou zero.", new Function ("varName", " return this[varName];"));
	}
	
	function VerificaIntervalosPreenchidos(interInicial, interFinal, msg) {
		if(interInicial.value != '' && interFinal.value == '') {
			alert(msg);
			interFinal.focus();
			return false;
		}else if (interInicial.value == '' && interFinal.value != '') {
			alert(msg);
			interInicial.focus();
			return false;
		}
		
		return true;
	}
    
    function ControleCategoriaSubCategoria() {
    	var form = document.ImovelEmissaoOrdensSeletivasActionForm;
    	var obj = form.categoria;
    	
    	if (obj.selectedIndex == 0) {
    		form.subCategoria.disabled = true;
    		form.subCategoria[0].selected = true;
    	}else {
    		if (form.categoria.selectedIndex == 0 || form.categoria.selectedIndex == -1) {
    			form.subCategoria.disabled = true;
    			form.subCategoria.value = "-1";
    		}else {
    			form.subCategoria.disabled = false;
    		}
    	}
    }
    
    function ControleCategoria() {
    	var form = document.ImovelEmissaoOrdensSeletivasActionForm;
    	var obj = form.categoria;
    	
    	if (obj.value != '-1') {
    		form.subCategoria.disabled = false;
    		pesquisaColecaoReload('filtrarImovelEmissaoOrdensSeletivasWizardAction.do?action=exibirFiltrarImovelEmissaoOrdensSeletivasCaracteristicas', 'categoria');
    		return;
    	}else {
    		form.subCategoria.value = "-1";
    		form.subCategoria.disabled = true;
    	}
    }
    
    function duplicarIntervaloQuantidadeEconomias(){
		var formulario = document.forms[0]; 
		formulario.intervaloQuantidadeEconomiasFinal.value = formulario.intervaloQuantidadeEconomiasInicial.value;
	}
	
	function duplicarIntervaloQuantidadeDocumentos(){
		var formulario = document.forms[0]; 
		formulario.intervaloQuantidadeDocumentosFinal.value = formulario.intervaloQuantidadeDocumentosInicial.value;
	}
	
	function duplicarIntervaloNumeroMoradores(){
		var formulario = document.forms[0]; 
		formulario.intervaloNumeroMoradoresFinal.value = formulario.intervaloNumeroMoradoresInicial.value;
	}
	
	function duplicarIntervaloAreaConstruida(){
		var formulario = document.forms[0]; 
		formulario.intervaloAreaConstruidaFinal.value = formulario.intervaloAreaConstruidaInicial.value;
	}

	function duplicarIntervaloConsumoEconomia(){
		var formulario = document.forms[0]; 
		formulario.consumoPorEconomiaFinal.value = formulario.consumoPorEconomia.value;
	}

	function limparIntervaloConsumoEconomia() {
		var form = document.forms[0];
		
		form.consumoPorEconomia.value = '';
		form.consumoPorEconomiaFinal.value = '';
	}

	
	function limparIntervaloAreaConstruida() {
		var form = document.forms[0];
		
		form.intervaloAreaConstruidaInicial.value = '';
		form.intervaloAreaConstruidaFinal.value = '';
	}
	
	function controleIntervaloAreaConstruidaPreDefinida() {
		var form = document.forms[0];

		if ( (form.intervaloAreaConstruidaInicial.value != '') ||
			 (form.intervaloAreaConstruidaFinal.value != '') ) {
		
			form.intervaloAreaConstruidaPredefinida.selectedIndex = 0;
		}
	}
	
	function habilitarDesabilitarDemaisCampos(){
		var form = document.ImovelEmissaoOrdensSeletivasActionForm;

		if ('${requestScope.desabilitarCampos}' == 'ok'){
			//desabilita campos
			form.perfilImovel.disabled= true;
			form.categoria.disabled= true;
			form.intervaloQuantidadeEconomiasInicial.disabled= true;
			form.intervaloQuantidadeEconomiasFinal.disabled= true;
			form.intervaloQuantidadeDocumentosInicial.disabled= true;
			form.intervaloQuantidadeDocumentosFinal.disabled= true;
			form.intervaloNumeroMoradoresInicial.disabled= true;
			form.intervaloNumeroMoradoresFinal.disabled= true;
			form.intervaloAreaConstruidaInicial.disabled= true;
			form.intervaloAreaConstruidaFinal.disabled= true;
			form.intervaloAreaConstruidaPredefinida.disabled= true;
			form.mediaImovel.disabled= true;
			
			form.consumoPorEconomia.disabled= true;
			form.consumoPorEconomiaFinal.disabled= true;
			
			form.imovelCondominio.disabled= true;
			
			//limpa campos
			form.perfilImovel.selectedIndex = 0;
			form.categoria.selectedIndex = 0;
			form.intervaloQuantidadeEconomiasInicial.value = "";
			form.intervaloQuantidadeEconomiasFinal.value = "";
			form.intervaloQuantidadeDocumentosInicial.value = "";
			form.intervaloQuantidadeDocumentosFinal.value = "";
			form.intervaloNumeroMoradoresInicial.value = "";
			form.intervaloNumeroMoradoresFinal.value = "";
			form.intervaloAreaConstruidaInicial.value = "";
			form.intervaloAreaConstruidaFinal.value = "";
			form.intervaloAreaConstruidaPredefinida.selectedIndex = 0;
			form.mediaImovel.value = "";
			form.consumoPorEconomia.value = "";
			form.consumoPorEconomiaFinal.value = "";
			form.imovelCondominio.value = "2";
		}
	}
-->    
</script>

</head>

<body leftmargin="5" topmargin="5" onload="ControleCategoriaSubCategoria();">

<html:form action="/filtrarImovelEmissaoOrdensSeletivasWizardAction"
	name="ImovelEmissaoOrdensSeletivasActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.ImovelEmissaoOrdensSeletivasActionForm"
	method="post"
	onsubmit="return validateImovelEmissaoOrdensSeletivasActionForm(this);">

	<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=3" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<input type="hidden" name="numeroPagina" value="4" />				
	
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
			
			<td width="617" valign="top" class="centercoltext">
			
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			
			<table width="100%" 
				border="0" 
				align="center" 
				cellpadding="0"
				cellspacing="0">
				
				<tr>
					<td width="11">
						<html:img src="imagens/parahead_left.gif" border="0" />
					</td>
					<td class="parabg">Filtrar Imóvel</td>
					
					<td width="11" valign="top">
						<html:img src="imagens/parahead_right.gif" border="0" />
					</td>
				</tr>
			</table>

			<p>&nbsp;</p>
			
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para filtrar o(s) im&oacute;vel(is) pelas
					caracter&iacute;sticas gerais, informe os dados abaixo:</td>
				</tr>
				
				<tr>
					<td width="180"><strong>Perfil do Im&oacute;vel:</strong></td>
					<td align="right">
						<div align="left">
							<strong>
								<html:select property="perfilImovel" style="width: 230px;">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="collectionImovelPerfil"
										labelProperty="descricao" 
										property="id" />
								</html:select>
							</strong>
						</div>
						<html:hidden property="perfilImovelDescricao"/>
					</td>
				</tr>
				
				<tr>
					<td><strong>Categoria:</strong></td>
					<td align="right">
						<div align="left">
							<strong>
								<html:select property="categoria" 
									onchange="ControleCategoria();" 
									style="width: 230px;">
									
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="collectionImovelCategoria"
										labelProperty="descricao" 
										property="id" />
								</html:select>
							</strong>
						</div>
						<html:hidden property="categoriaDescricao"/>
					</td>
				</tr>

				<tr>
					<td><strong>Subcategoria:</strong></td>
					<td align="right">
						<div align="left">
							<strong>
								<html:select property="subCategoria" style="width: 230px;">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="collectionImovelSubcategoria"
										labelProperty="descricao" 
										property="id" />
								</html:select>
							</strong>
						</div>
						<html:hidden property="subCategoriaDescricao"/>
					</td>
				</tr>
				
				<tr><td colspan="2"><hr></td></tr>
				
				<tr>
					<td colspan="2">
						<table width="100%" border="0">
							<tr>
								<td width="175"><strong>Intervalo de Quantidade de Economias:</strong></td>
								<td align="left">
									<html:text property="intervaloQuantidadeEconomiasInicial"
										size="5" 
										maxlength="4" 
										onblur="duplicarIntervaloQuantidadeEconomias();"
										onkeypress="return isCampoNumerico(event);"/>
								</td>
								<td>&nbsp;a&nbsp;</td>
								<td align="left">
									<html:text property="intervaloQuantidadeEconomiasFinal" 
										size="5" 
										maxlength="4"
										onkeypress="return isCampoNumerico(event);"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
						<table width="100%" border="0">
							<tr>
								<td width="175"><strong>Intervalo de Quantidade de Documentos:</strong></td>
								<td align="left">
									<html:text property="intervaloQuantidadeDocumentosInicial"
										size="5" 
										maxlength="4" 
										onblur="duplicarIntervaloQuantidadeDocumentos();"
										onkeypress="return isCampoNumerico(event);"/>
								</td>
								<td>&nbsp;a&nbsp;</td>
								
								<td align="left">
									<html:text property="intervaloQuantidadeDocumentosFinal" 
										size="5" 
										maxlength="4"
										onkeypress="return isCampoNumerico(event);"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
						<table width="100%" border="0">
							<tr>
								<td width="175"><strong>Intervalo de N&uacute;mero de Moradores:</strong></td>
								<td align="left">
									<html:text property="intervaloNumeroMoradoresInicial"
										size="5" 
										maxlength="4" 
										onblur="duplicarIntervaloNumeroMoradores();"
										onkeypress="return isCampoNumerico(event);"/>
								</td>
								<td>&nbsp;a&nbsp;</td>
								
								<td align="left">
									<html:text property="intervaloNumeroMoradoresFinal" 
										size="5" 
										maxlength="4"
										onkeypress="return isCampoNumerico(event);"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr><td colspan="2"><hr></td></tr>
				
				<tr>
					<td colspan="2">
						<table width="100%" border="0">
							<tr>
								<td width="175"><strong>Intervalo de &Aacute;rea Construida:</strong></td>
								<td align="left">
									<html:text property="intervaloAreaConstruidaInicial" 
										size="7"
										maxlength="10" 
										onkeyup="formataValorMonetario(this, 10); controleIntervaloAreaConstruidaPreDefinida();"
										onkeypress="return isCampoNumerico(event);"
										onblur="duplicarIntervaloAreaConstruida();"
										style="text-align:right;"/>
								</td>
								<td>&nbsp;a&nbsp;</td>

								<td align="left">
									<html:text property="intervaloAreaConstruidaFinal" 
										size="7"
										maxlength="10" 
										onkeyup="formataValorMonetario(this, 10); controleIntervaloAreaConstruidaPreDefinida();"
										onkeypress="return isCampoNumerico(event);"
										style="text-align:right;"/>
								</td>

								<td>&nbsp;&nbsp;</td>

								<td>
									<html:select property="intervaloAreaConstruidaPredefinida" style="width: 170px;"
										onchange="limparIntervaloAreaConstruida();">
										<html:option value="-1">&nbsp;</html:option>
										<html:options collection="collectionIntervaloAreaConstruidaPredefinida"
											labelProperty="faixaCompleta" 
											property="id" />
									</html:select>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<td><strong>Im&oacute;vel Condom&iacute;nio:</strong></td>
					<td align="left">
						<table width="100%" border="0">
							<tr>
								<logic:present name="imovelCondominio">
									<td width="100">
										<html:radio value="1" property="imovelCondominio"/>&nbsp;Sim
									</td>
									
									<td align="left">
										<html:radio value="2" property="imovelCondominio"/>&nbsp;N&atilde;o
									</td>
								</logic:present>

								<logic:notPresent name="imovelCondominio">
									<td width="100">
										<input type="radio" 
											name="imovelCondominio" 
											value="1">Sim
									</td>
									<td align="left">
										<input type="radio" 
											name="imovelCondominio" 
											value="2" 
											checked="checked">N&atilde;o
									</td>
								</logic:notPresent>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr><td colspan="2"><hr></td></tr>
				
				<tr>
					<td><strong>M&eacute;dia do Im&oacute;vel:</strong></td>
					<td align="left">
						<logic:equal name="tipoOrdem" value="INSTALACAO">
							<html:text property="mediaImovel" 
								size="8" 
								maxlength="6" 
								disabled="true" 
								onkeypress="return isCampoNumerico(event);"/>
						</logic:equal>
						<logic:notEqual name="tipoOrdem" value="INSTALACAO">
							<html:text property="mediaImovel" 
								size="8" 
								maxlength="6" 
								onkeypress="return isCampoNumerico(event);"/>
						</logic:notEqual>
					</td>
				</tr>
				
				
				
				<tr>
					<td colspan="2">
					<table width="100%" border="0">
						<tr>
							<td width="175"><strong>Intervalo de Consumo por Economia:</strong></td>
							
							<logic:equal name="tipoOrdem" value="INSTALACAO">
								<td align="left">
									<html:text property="consumoPorEconomia" 
										size="8" 
										maxlength="6" 
										disabled="true" 
										onkeypress="return isCampoNumerico(event);"/>
	
								</td>
	
								<td>&nbsp;a&nbsp;</td>
	
								<td align="left">
									<html:text 
										property="consumoPorEconomiaFinal" 
										size="8" 
										maxlength="6" 
										disabled="true" 
										onkeypress="return isCampoNumerico(event);"/>
								</td>
							</logic:equal>
					
							<logic:notEqual name="tipoOrdem" value="INSTALACAO">
								<td align="left">
									<html:text 
										property="consumoPorEconomia" 
										size="8" 
										maxlength="6" 
										onblur="duplicarIntervaloConsumoEconomia();"
										onkeypress="return isCampoNumerico(event);"/>
								</td>
		
								<td>&nbsp;a&nbsp;</td>
								
								<td align="left">
									<html:text 
										property="consumoPorEconomiaFinal" 
										size="8" 
										maxlength="6" 
										onkeypress="return isCampoNumerico(event);"/>
								</td>
							</logic:notEqual>
						</tr>
					</table>
					</td>
				</tr>
				
				<tr id="Medicao">
					<td><strong>Tipo de Medi&ccedil;&atilde;o:</strong></td>
					<td align="right">
						<div align="left">
							<strong>
								<html:select property="tipoMedicao" style="width: 180px;">
									<html:options collection="collectionTipoMedicao"
										labelProperty="descricao" 
										property="id" />
								</html:select>
							</strong>
						</div>
						<html:hidden property="tipoMedicaoDescricao"/>
					</td>
				</tr>
				<logic:present name="collectionProjetos" scope="session">
				<tr>
					<td width="180"><strong>Projeto:</strong></td>
					<td align="right">
						<div align="left">
							<strong>
								<html:select property="idProjeto" style="width: 230px;">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="collectionProjetos"
										labelProperty="nome" property="id" />
								</html:select>
							</strong>
						</div>
					</td>
				</tr>
				</logic:present>
				
				<tr>
					<td width="180"><strong>Situação Ligação de Água:</strong></td>
					<td align="right">
						<div align="left">
							<strong>
								<html:select property="situacaoLigacaoAgua" style="width: 160px;" multiple="true" size="4">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="collectionLigacaoAguaSituacao"
										labelProperty="descricao" 
										property="id" />
								</html:select>
							</strong>
						</div>
						<html:hidden property="situacaoLigacaoAguaDescricao"/>
					</td>
				</tr>				
				
				<tr>
					<td>&nbsp;</td>
					<td align="right">&nbsp;</td>
				</tr>
				
				<tr>
					<td colspan="2">
						<div align="right">
							<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=3" />
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
	<script>document.getElementById('Medicao').style.display = 'none';</script>
	<script>habilitarDesabilitarDemaisCampos();</script>
</body>
</html:form>
</html:html>
