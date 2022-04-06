<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.ConstantesSistema"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirRotaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(form){
	var adicionarInformado = document.getElementById("botaoAdicionar");
		if (validateInserirRotaActionForm(form)){
			validarIndicadorFiscalizarCortado();
			validarIndicadorFiscalizarSuprimido();		
			validarIndicadorGerarFalsaFaixa();
			validarIndicadorGerarFiscalizacao();
			validarIndicadorTransmissaoOffline();
			validarIndicadorSequencialLeitura();
			validarTipoLeituraComIndicadorImpressao();
			
			if(validarLimiteImoveisRota()){
				return false;
			}
			
			if(testarCampoDecimalValorZero(document.InserirRotaActionForm.percentualGeracaoFaixaFalsa, 'Percentual de Faixa Falsa')
			&& testarCampoDecimalValorZero(document.InserirRotaActionForm.percentualGeracaoFiscalizacao, 'Percentual de Fiscalizacao de Leitura'))
			{
				if(!adicionarInformado.disabled){
					alert("É necessário informar Critério de Cobrança da Rota para todas as ações de cobrança.");
					botaoAdicionar.focus();
					return false;
				}else{
					submeterFormPadrao(form);
				}
			}
		}	
	}
	
	function pesquisarLeiturista(){
	  var form = document.forms[0];
	  
	  if ( form.empresaLeituristica.value == '-1' ){
	    alert("Para consultar leituristas, informe a empresa de leitura.");	    
	  } else  {
	    abrirPopup('pesquisarLeituristaAction.do?limparForm=sim&empresa=' + form.empresaLeituristica.value, 200, 400 );
	  }
	}

	function limparLocalidade(form) {
    	form.idLocalidade.value = "";
    	form.localidadeDescricao.value = "";
	}
	
	function limparSetorComercial(form) {
    	form.codigoSetorComercial.value = "";
    	form.setorComercialDescricao.value = "";
	}

	function limparDescLocalidade(form) {
    	form.localidadeDescricao.value = "";
    	form.codigoSetorComercial.value = "";
	}
	
	function limparDescSetorComercial(form) {
    	form.setorComercialDescricao.value = "";
	}
	
	function limparLeiturista(form) {
    	form.idLeiturista.value = "";
    	form.nomeLeiturista.value = "";
    	form.idLeiturista.focus();
	}	
	
 	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'localidade') {
      	limparLocalidade(form);
      	form.idLocalidade.value = codigoRegistro;
      	form.localidadeDescricao.value = descricaoRegistro;
      	form.localidadeDescricao.style.color = "#000000";
       	form.codigoSetorComercial.focus();
       	
    }

    if (tipoConsulta == 'setorComercial') {
      limparSetorComercial(form);
      form.codigoSetorComercial.value = codigoRegistro;
      form.setorComercialDescricao.value = descricaoRegistro;
      form.setorComercialDescricao.style.color = "#000000";

	  form.action = 'exibirInserirRotaAction.do';
	  submeterFormPadrao(form);
    }
    
    if (tipoConsulta == 'leiturista') {
      limparLeiturista(form);
      form.idLeiturista.value = codigoRegistro;
      form.nomeLeiturista.value = descricaoRegistro;
      form.nomeLeiturista.style.color = "#000000";
      form.idLeiturista.focus();
    }

   }

   function habilitacaoPercentualGeracaoFaixaFalsa(indicadorGerarFalsaFaixa){

	var form = document.forms[0];
		if (indicadorGerarFalsaFaixa[1].checked){
			form.percentualGeracaoFaixaFalsa.disabled = true;
			form.percentualGeracaoFaixaFalsa.value = ""; 
		}else{
			form.percentualGeracaoFaixaFalsa.disabled = false;
		}

   }
   
   function habilitacaoNumeroDiasConsumoAjuste(indicadorAjusteConsumo){

	var form = document.forms[0];
		if (indicadorAjusteConsumo[1].checked){
			form.numeroDiasConsumoAjuste.disabled = true;
			form.numeroDiasConsumoAjuste.value = ""; 
		}else{
			form.numeroDiasConsumoAjuste.disabled = false;
		}

   }
   
   function habilitacaoPercentualGeracaoFiscalizacao(indicadorGerarFiscalizacao){

	var form = document.forms[0];
		if (indicadorGerarFiscalizacao[1].checked){
			form.percentualGeracaoFiscalizacao.disabled = true;
			form.percentualGeracaoFiscalizacao.value = ""; 
		}else{
			form.percentualGeracaoFiscalizacao.disabled = false;
		}

   }
   
   function validarTipoLeituraComIndicadorImpressao(){
		var form = document.forms[0];
		
		var form = document.forms[0];
		if(form.indicadorImpressaoTermicaFinalGrupo[0].checked && form.leituraTipo.value != '2'){
			alert("Indicador de Impressao termica incopativel com Tipo de Leitura selecionado.");
			form.indicadorImpressaoTermicaFinalGrupo.focus();
		}else if(form.indicadorImpressaoTermicaFinalGrupo[1].checked && form.leituraTipo.value != '3'){
			alert("Indicador de Impressao termica incopativel com Tipo de Leitura selecionado.");
			form.indicadorImpressaoTermicaFinalGrupo.focus();
		}

	}



   function validarIndicadorFiscalizarCortado(){

	    var form = document.forms[0];
	    
	    var indice;
	    var array = new Array(form.indicadorFiscalizarCortado.length);
	    var selecionado = "";
	    var formulario = document.forms[0]; 
	    for(indice = 0; indice < form.elements.length; indice++){
		   if (form.elements[indice].type == 'radio' && form.elements[indice].checked == true && formulario.elements[indice].name == "indicadorFiscalizarCortado") {
		      selecionado = form.elements[indice].value;
		      indice = form.elements.length;
		   }
	    }    
		if(selecionado == ''){
			alert('Informe Fiscaliza Cortados na Leitura.');
			indicadorFiscalizarCortado.focus();
		}	
	}

   function validarIndicadorFiscalizarSuprimido(){

	    var form = document.forms[0];
	    
	    var indice;
	    var array = new Array(form.indicadorFiscalizarSuprimido.length);
	    var selecionado = "";
	    var formulario = document.forms[0]; 
	    for(indice = 0; indice < form.elements.length; indice++){
		   if (form.elements[indice].type == 'radio' && form.elements[indice].checked == true && formulario.elements[indice].name == "indicadorFiscalizarSuprimido") {
		      selecionado = form.elements[indice].value;
		      indice = form.elements.length;
		   }
	    }    
		if(selecionado == ''){
			alert('Informe Fiscaliza Suprimidos na Leitura.');
			indicadorFiscalizarSuprimido.focus();
		}	
	}
	

   function validarIndicadorGerarFalsaFaixa(){

	    var form = document.forms[0];
	    
	    var indice;
	    var array = new Array(form.indicadorGerarFalsaFaixa.length);
	    var selecionado = "";
	    var formulario = document.forms[0]; 
	    for(indice = 0; indice < form.elements.length; indice++){
		   if (form.elements[indice].type == 'radio' && form.elements[indice].checked == true && formulario.elements[indice].name == "indicadorGerarFalsaFaixa") {
		      selecionado = form.elements[indice].value;
		      indice = form.elements.length;
		   }
	    }    
		if(selecionado == ''){
			alert('Informe Gera Faixa Falsa.');
			indicadorGerarFalsaFaixa.focus();
		}	
	}		   

   function validarIndicadorGerarFiscalizacao(){

	    var form = document.forms[0];
	    
	    var indice;
	    var array = new Array(form.indicadorGerarFiscalizacao.length);
	    var selecionado = "";
	    var formulario = document.forms[0]; 
	    for(indice = 0; indice < form.elements.length; indice++){
		   if (form.elements[indice].type == 'radio' && form.elements[indice].checked == true && formulario.elements[indice].name == "indicadorGerarFiscalizacao") {
		      selecionado = form.elements[indice].value;
		      indice = form.elements.length;
		   }
	    }    
		if(selecionado == ''){
			alert('Informe Gera Fiscalização de Leitura.');
			indicadorGerarFiscalizacao.focus();
		}	
	}	

   function validarIndicadorTransmissaoOffline(){

	    var form = document.forms[0];
	    
	    var indice;
	    var array = new Array(form.indicadorTransmissaoOffline.length);
	    var selecionado = "";
	    var formulario = document.forms[0]; 
	    for(indice = 0; indice < form.elements.length; indice++){
		   if (form.elements[indice].type == 'radio' && form.elements[indice].checked == true && formulario.elements[indice].name == "indicadorTransmissaoOffline") {
		      selecionado = form.elements[indice].value;
		      indice = form.elements.length;
		   }
	    }    
		if(selecionado == ''){
			alert('Informe Transmissão Off-line.');
			indicadorGerarFiscalizacao.focus();
		}	
	}
	
	function validarIndicadorSequencialLeitura(){

	    var form = document.forms[0];
	    
	    var indice;
	    var array = new Array(form.indicadorSequencialLeitura.length);
	    var selecionado = "";
	    var formulario = document.forms[0]; 
	    for(indice = 0; indice < form.elements.length; indice++){
		   if (form.elements[indice].type == 'radio' && form.elements[indice].checked == true && formulario.elements[indice].name == "indicadorSequencialLeitura") {
		      selecionado = form.elements[indice].value;
		      indice = form.elements.length;
		   }
	    }    
		if(selecionado == ''){
			alert('Informe Sequencial Leitura.');
			indicadorSequencialLeitura.focus();
		}	
	}
	
	function validarLimiteImoveisRota(){
		var form = document.forms[0];
		
		if(form.numeroLimiteImoveis.value != "" && form.leituraTipo.value != '3'){
			alert("O tipo de leitura informado não permite referenciar valor para a divisão da rota.");
			form.numeroLimiteImoveis.value = "";
			return true;
		}
	}

</script>
</head>
<body leftmargin="0" topmargin="0"
	onload="setarFoco('${requestScope.nomeCampo}');">
<html:form action="/inserirRotaAction" name="InserirRotaActionForm"
	type="gcom.gui.micromedicao.InserirRotaActionForm" method="post"
	onsubmit="return validateInserirRotaActionForm(this);">


	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="120" valign="top" class="leftcoltext">
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
			<table>
				<tr>
					<td></td>
				</tr>

			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Inserir Rota</td>
					<td width="11" valign="top"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>


			<p>&nbsp;</p>
			<table width="100%" border="0">

				<tr>
					<td>Para adicionar a rota, informe os dados abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroRotaInserir', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td><strong>Localidade:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:text maxlength="3" 
								   tabindex="1" 
								   property="idLocalidade"
								   size="3"
								   onkeypress="javascript:limparDescLocalidade(document.InserirRotaActionForm);
										limparDescSetorComercial(document.InserirRotaActionForm);
										validaEnterComMensagem(event, 'exibirInserirRotaAction.do', 'idLocalidade','Localidade');
										return isCampoNumerico(event);" />
						<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do');">
							<img width="23" height="21" border="0"
								 src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								 title="Pesquisar Localidade" />
						</a> 
						<logic:present name="idLocalidadeNaoEncontrada">
							<logic:equal name="idLocalidadeNaoEncontrada" value="exception">
								<html:text property="localidadeDescricao" 
										   size="28"
										   maxlength="30" 
										   readonly="true"
										   style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
	
							<logic:notEqual name="idLocalidadeNaoEncontrada" value="exception">
								<html:text property="localidadeDescricao" 
										   size="28"
									       maxlength="30" 
									       readonly="true"
										   style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						<logic:notPresent name="idLocalidadeNaoEncontrada">
							<logic:empty name="InserirRotaActionForm" property="idLocalidade">
								<html:text property="localidadeDescricao" 
										   value="" 
										   size="28"
										   maxlength="30" 
										   readonly="true"
										   style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
	
							<logic:notEmpty name="InserirRotaActionForm" property="idLocalidade">
								<html:text property="localidadeDescricao" 
										   size="28"
										   maxlength="30" 
										   readonly="true"
										   style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent> 
						<a href="javascript:limparLocalidade(document.InserirRotaActionForm);
							limparSetorComercial(document.InserirRotaActionForm);">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /> 
						</a>
					</td>
				</tr>


				<tr>
					<td><strong>Setor Comercial:<font color="#FF0000">*</font></strong></td>
					<td height="24">
						<html:text maxlength="3"
								   property="codigoSetorComercial" 
								   tabindex="2" 
								   size="3"
								   onkeypress="javascript:validaEnterDependenciaComMensagem(event, 'exibirInserirRotaAction.do', this, document.forms[0].idLocalidade.value, 'Localidade','Setor Comercial');return isCampoNumerico(event);" />

						<a href="javascript:limparDescSetorComercial(document.InserirRotaActionForm);abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidade.value+'&tipo=SetorComercial',document.forms[0].idLocalidade.value,'Localidade', 400, 800);">
							<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Setor Comercial" />
						</a> 
						<logic:present name="idSetorComercialNaoEncontrada">
							<logic:equal name="idSetorComercialNaoEncontrada" value="exception">
								<html:text property="setorComercialDescricao" 
										   size="28"
										   maxlength="30" 
										   readonly="true"
										   style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="idSetorComercialNaoEncontrada" value="exception">
								<html:text property="setorComercialDescricao" 
										   size="28"
										   maxlength="30" 
										   readonly="true"
										   style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						<logic:notPresent name="idSetorComercialNaoEncontrada">
							<logic:empty name="InserirRotaActionForm" property="codigoSetorComercial">
								<html:text property="setorComercialDescricao" 
										   value="" 
										   size="28"
										   maxlength="30" 
										   readonly="true"
										   style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="InserirRotaActionForm" property="codigoSetorComercial">
								<html:text property="setorComercialDescricao" 
										   size="28"
										   maxlength="30" 
										   readonly="true"
										   style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent> 
						<a href="javascript:limparSetorComercial(document.InserirRotaActionForm);">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								 border="0" 
								 title="Apagar" /> 
						</a>
					</td>
				</tr>


				<tr>
					<td><strong> Código da Rota:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:text property="codigoRota" 
								   size="4" 
								   maxlength="4"
								   tabindex="3"
								   onkeypress="return isCampoNumerico(event);" />
					</td>
				</tr>

				<tr>
					<td><strong>Grupo de Faturamento:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="faturamentoGrupo" tabindex="4">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="collectionFaturamentoGrupo"
									      labelProperty="descricao" 
									      property="id" />
						</html:select> <font size="1">&nbsp; </font>
					</td>
				</tr>

				<tr>
					<td width="35%"><strong>Grupo de Cobran&ccedil;a:<font
						color="#FF0000">*</font></strong></td>
					<td width="65%">
						<html:select property="cobrancaGrupo" tabindex="5">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="collectionCobrancaGrupo"
										  labelProperty="descricao" 
										  property="id" />
						</html:select> <font size="1">&nbsp; </font>
					</td>
				</tr>

				<tr>
					<td><strong>Tipo de Leitura:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="leituraTipo" tabindex="6">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="collectionLeituraTipo"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
	
				<tr>
					<td><strong>Limite de Imóveis por Rota:<font color="#FF0000"></font></strong></td>
					<td>
						<html:text property="numeroLimiteImoveis" 
								   size="3" 
								   maxlength="3"
								   tabindex="6"
								   onkeypress="return isCampoNumerico(event);" />
					</td>
				</tr>

				<tr>
					<td><strong>Empresa de Leitura:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="empresaLeituristica" tabindex="7">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="collectionEmpresa"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				
				<tr>
					<td><strong>Empresa de Cobranca:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="empresaCobranca" tabindex="8">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="collectionEmpresa"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				<tr>
					<td><strong>Empresa de Entrega das Contas:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="empresaEntregaContas" tabindex="7">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="collectionEmpresa"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>

				<tr>
					<td><strong>Fiscaliza Cortados na Leitura:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"><strong> <html:radio
						property="indicadorFiscalizarCortado" value="1" tabindex="11" /> <strong>Sim
					<html:radio property="indicadorFiscalizarCortado" value="2" /> Não
					</strong></strong></span></strong></td>
				</tr>


				<tr>
					<td><strong>Fiscaliza Suprimidos na Leitura:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"><strong> <html:radio
						property="indicadorFiscalizarSuprimido" value="1" tabindex="12" />
					<strong>Sim <html:radio property="indicadorFiscalizarSuprimido"
						value="2" /> Não </strong></strong></span></strong></td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Ajuste de Consumo:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorAjusteConsumo" value="<%=ConstantesSistema.SIM.toString()%>"
						onclick="habilitacaoNumeroDiasConsumoAjuste(document.forms[0].indicadorAjusteConsumo)"
						tabindex="13" /> Sim <html:radio
						property="indicadorAjusteConsumo" value="<%=ConstantesSistema.NAO.toString()%>"
						onclick="habilitacaoNumeroDiasConsumoAjuste(document.forms[0].indicadorAjusteConsumo)"
						/>
					Não </span></strong></td>
				</tr>
				
				<tr>
					<td><strong> Quantidade de Dias de Consumo:</strong></td>
					<td><html:text property="numeroDiasConsumoAjuste" 
								   size="4" 
								   maxlength="2" 
								   tabindex="14"
								   style="text-align: right;" 
								   onkeypress="return isCampoNumerico(event);"/>
					</td>
				</tr>	
				
				<tr>
					<td><strong>Indicador de Rota Alternativa:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorRotaAlternativa" value="<%=ConstantesSistema.SIM.toString()%>"
						tabindex="13" /> Sim <html:radio
						property="indicadorRotaAlternativa" value="<%=ConstantesSistema.NAO.toString()%>"/>
					Não </span></strong></td>
				</tr>
				
				
				
				<tr>
					<td><strong>Indicador de Transmissão Off-line:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorTransmissaoOffline" value="<%=ConstantesSistema.SIM.toString()%>"
						tabindex="13" /> Sim <html:radio
						property="indicadorTransmissaoOffline" value="<%=ConstantesSistema.NAO.toString()%>"/>
					Não </span></strong></td>
				</tr>	
				<tr>
					<td><strong>Indicador Sequencial Leitura:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorSequencialLeitura" value="<%=ConstantesSistema.SIM.toString()%>"
						tabindex="13" /> Sim <html:radio
						property="indicadorSequencialLeitura" value="<%=ConstantesSistema.NAO.toString()%>"/>
					Não </span></strong></td>
				</tr>
				
				<tr>
					<td><strong>Indicador Impressao Térmica:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorImpressaoTermicaFinalGrupo" value="<%=ConstantesSistema.SIM.toString()%>"
						tabindex="13" /> Sim <html:radio
						property="indicadorImpressaoTermicaFinalGrupo" value="<%=ConstantesSistema.NAO.toString()%>"/>
					Não </span></strong></td>
				</tr>
				
				<tr>
					<td><strong>Gera Faixa Falsa:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorGerarFalsaFaixa" value="1"
						onclick="habilitacaoPercentualGeracaoFaixaFalsa(document.forms[0].indicadorGerarFalsaFaixa)"
						tabindex="13" /> Sim <html:radio
						property="indicadorGerarFalsaFaixa" value="2"
						onclick="habilitacaoPercentualGeracaoFaixaFalsa(document.forms[0].indicadorGerarFalsaFaixa)" />
					Não </span></strong></td>
				</tr>
				
				<tr>
					<td><strong> Percentual de Faixa Falsa:</strong></td>
					<td><html:text property="percentualGeracaoFaixaFalsa" 
								   size="6" 
								   maxlength="6" 
								   tabindex="14"
								   onkeyup="formataValorMonetario(this, 6)"
								   style="text-align: right;" 
								   onkeypress="return isCampoNumerico(event);"/>
					</td>
				</tr>


				<tr>
					<td><strong>Gera Fiscaliza&ccedil;&atilde;o de Leitura:<font
						color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorGerarFiscalizacao" value="1"
						onclick="habilitacaoPercentualGeracaoFiscalizacao(document.forms[0].indicadorGerarFiscalizacao)"
						tabindex="15" /> Sim <html:radio
						property="indicadorGerarFiscalizacao" value="2"
						onclick="habilitacaoPercentualGeracaoFiscalizacao(document.forms[0].indicadorGerarFiscalizacao)" />
					Não </span></strong></td>
				</tr>


				<tr>
					<td><strong> Percentual de Fiscaliza&ccedil;&atilde;o de Leitura:</strong></td>
					<td>
						<html:text property="percentualGeracaoFiscalizacao" 
								   size="6"
								   maxlength="6" 
								   tabindex="16"
								   onkeyup="formataValorMonetario(this, 6)"
								   style="text-align: right;" 
								   onkeypress="return isCampoNumerico(event);"/>
					</td>
				</tr>

				<tr>
					<td width="130"><strong>Leiturista:</strong></td>
					<td colspan="2">
						<html:text maxlength="6" 
								   property="idLeiturista"
								   tabindex="17" 
								   size="6"
								   onkeypress="javascript:validaEnterComMensagem(event, 'exibirInserirRotaAction.do', 'idLeiturista', 'Leiturista');return isCampoNumerico(event);" />
						<a href="javascript:pesquisarLeiturista();">
							<img width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Leiturista" />
						</a> 
						<logic:present name="idLeituristaEncontrado" scope="request">
							<html:text maxlength="30" property="nomeLeiturista"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="28" />
						</logic:present> 
						<logic:notPresent name="idLeituristaEncontrado" scope="request">
							<html:text maxlength="30" property="nomeLeiturista"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000"
										size="28" />
						</logic:notPresent> 
						<a href="javascript:limparLeiturista(document.InserirRotaActionForm);"> 
						  <img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" />
						</a>
					</td>
				</tr>

				<%-- início da tabela de Critérios de Cobrança da Rota --%>
				<tr>
					<td colspan="2">
					&nbsp;
					</td>
				</tr>

				<tr>
					<td><strong> Critérios de Cobrança da Rota<font color="#FF0000">*</font></strong>
					</td>
					<logic:present name="adicionar">
						<logic:equal name="adicionar" value="habilitado">
							<td align="right"><input name="Button" type="button"
								class="bottonRightCol" value="Adicionar" align="right" id="botaoAdicionar"
								onclick="javascript:abrirPopup('exibirAdicionarCriterioCobrancaRotaAction.do?UseCase=INSERIRROTA');" />
							</td>
						</logic:equal>
						<logic:equal name="adicionar" value="desabilitado">
							<td align="right"><input name="Button" type="button" id="botaoAdicionar"
								class="bottonRightCol" value="Adicionar" align="right" disabled />
							</td>
						</logic:equal>
					</logic:present>
					<logic:notPresent name="adicionar">
						<td align="right"><input name="Button" type="button"
							class="bottonRightCol" value="Adicionar" align="right" id="botaoAdicionar"
							onclick="javascript:abrirPopup('exibirAdicionarCriterioCobrancaRotaAction.do?UseCase=INSERIRROTA');" />
						</td>
					</logic:notPresent>
				</tr>


				<tr>
					<td colspan="2">
					<table width="100%" border="0" bgcolor="#90c7fc">
						<tr bgcolor="#90c7fc">
							<td align="center" width="10%" bgcolor="#90c7fc"><strong>Remover</strong></td>
							<td align="center" width="40%" bgcolor="#90c7fc"><strong>Ação de Cobrança</strong></td>
							<td align="center" width="50%" bgcolor="#90c7fc"><strong>Critério de Cobrança</strong></td>
						</tr>

						<%--Esquema de paginação--%>
						<logic:present name="collectionRotaAcaoCriterio">
							<% String cor = "#cbe5fe";%>
							<logic:iterate name="collectionRotaAcaoCriterio"
							id="rotaAcaoCriterio">
								<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
									cor = "#FFFFFF";%>
									<tr bgcolor="#FFFFFF" height="18">	
								<%} else{	
									cor = "#cbe5fe";%>
									<tr bgcolor="#cbe5fe" height="18">		
								<%}%>

									<td width="10%">
									<div align="center"><font color="#333333"> 
										<img width="14"
											style="cursor: pointer;cursor:hand;"
											height="14" border="0"
											src="<bean:message key="caminho.imagens"/>Error.gif"
											onclick="javascript:if(confirm('Confirma remoção?')){redirecionarSubmit('removerAdicionarCriterioCobrancaRotaAction.do?idCobrancaAcaoExcluir=<bean:write name="rotaAcaoCriterio" property="cobrancaAcao.id"/>');}" />
									</font></div>
									</td>
									<td width="40%">
									<div>${rotaAcaoCriterio.cobrancaAcao.descricaoCobrancaAcao}
									&nbsp;</div>
									</td>

									<td width="50%">
									<div>${rotaAcaoCriterio.cobrancaCriterio.descricaoCobrancaCriterio}
									&nbsp;</div>
									</td>

								</tr>
							</logic:iterate>
						</logic:present>
					</table>
					</td>
				</tr>

				<%-- final da tabela de Critérios de Cobrança da Rota --%>

				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>

				<tr>
					<td>
						<input name="Button" type="button" class="bottonRightCol"
							value="Limpar" align="left"
							onclick="window.location.href='<html:rewrite page="/exibirInserirRotaAction.do?desfazer=S"/>'">
						<input name="Button" type="button" class="bottonRightCol"
							value="Cancelar" align="left"
							onclick="window.location.href='<html:rewrite page="/telaPrincipal.do"/>'">
					</td>
					<td align="right">
						<gsan:controleAcessoBotao name="Button" value="Inserir"
								  onclick="javascript:validarForm(document.InserirRotaActionForm);" url="inserirRotaAction.do"/>
					</td>
				</tr>

			</table>
			<p>&nbsp;</p>
			</td>

		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
<script language="JavaScript">
	<!--
	habilitacaoPercentualGeracaoFaixaFalsa(document.forms[0].indicadorGerarFalsaFaixa);
	habilitacaoPercentualGeracaoFiscalizacao(document.forms[0].indicadorGerarFiscalizacao);
	habilitacaoNumeroDiasConsumoAjuste(document.forms[0].indicadorAjusteConsumo);
	//-->
	</script>
</body>
</html:html>
