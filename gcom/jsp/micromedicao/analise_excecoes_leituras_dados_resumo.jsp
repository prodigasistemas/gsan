<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="gcom.util.Util"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page
	import="gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao"%>
<%@ page
	import="gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao"%>
<%@ page import="gcom.cadastro.imovel.ImovelPerfil"%>
<%@ page import="gcom.faturamento.FaturamentoSituacaoHistorico"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="LeituraConsumoActionForm" dynamicJavascript="false" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<style>
.styleFontePequena{font-size:9px;
                   color: #000000;
				   font:Verdana, Arial, Helvetica, sans-serif}
.styleFontePeqNegrito{font-size:11px;
                   color: #000000;
				   font-weight: bold}
.styleFonteTabelaPrincipal{font-size:12px;
                   color: #FFFFFF;
				   background-color: #5782E6;
				   font-weight: bold}
.styleFonteMenorNegrito{font-size:10px;
                   color: #000000;
				   font-weight: bold}
.buttonAbaRodaPe {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 13px;
	width:100px;
	background-color: #55A8FB;
	border-top: 1px outset #FFFFFF;
	border-right: 1px outset #000099;
	border-bottom: 1px outset #000099;
	border-left: 1px outset #FFFFFF;
	text-transform: none;

</style>
<script language="JavaScript">
	function limparPesquisaAnormalidade(){
		var form = document.forms[0];
	
		form.idAnormalidade.value = "";
		form.descricaoAnormalidade.value = "";
	}
	
	function desabilitaCampos(habilitaCampos,desabilitaAtualizarImovel) {
		var form = document.forms[0];
		var totalFields = form.elements.length;
		
			
  		if(habilitaCampos == 'true'){
  			form.calculoConsumo.disabled = false;
			form.analisado.disabled = false;
			form.motivoInterferenciaTipo.disabled = false;
	  		form.gerarAviso = false;
	  		form.gerarRelatorio = false;
	  		form.gerarOS = false;

	  
	  		for (i = 0; i < totalFields; i++) {
				if(form.elements[i].type != 'button'){
	    			if(form.elements[i].type == 'text' && form.elements[i].id != 'habilita'){
	    				form.elements[i].readOnly = false;
	    			}
	    		}else if(form.elements[i].id == 'desabilita'){
    				form.elements[i].disabled = false;
	    		}
	  		}
  		}
  		
  		if(desabilitaAtualizarImovel == 'true'){
  			form.calculoConsumo.disabled = true;
			form.analisado.disabled = true;
			form.motivoInterferenciaTipo.disabled = true;
	  
	  		for (i = 0; i < totalFields; i++) {
				if(form.elements[i].type != 'button'){
	    			if(form.elements[i].type == 'text' && form.elements[i].id != 'habilita'){
	    				form.elements[i].readOnly = true;
	    			}
	    		}else if(form.elements[i].id == 'desabilita'){
    				form.elements[i].disabled = true;
	    		}
	  		}
  		}
	}
</script>
<script language="JavaScript">
var bCancel = false;

    function validateLeituraConsumoActionForm(form) {
        if (bCancel)
      return true;
        else
       return validateCaracterEspecial(form) && validateLong(form);
   }
   

    function caracteresespeciais () {
     this.aa = new Array("dataLeituraAnteriorFaturamento", "Data Anterior possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("leituraAnteriorFaturamento", "Leitura Anterior possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("dataLeituraAtualInformada", "Data Atual possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("leituraAtualInformada", "Leitura Atual possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("consumoInformado", "Consumo Inf. possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.af = new Array("codigoImovel", "Imóvel possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ag = new Array("idAnormalidade", "Anorm. possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     
    }


    function IntegerValidations () {
    var form = document.forms[0];
     	if ( parseInt(form.leituraAnteriorFaturamentoHidden.value) == parseInt(2) ){
	    	this.aa = new Array("leituraAnteriorFaturamento", "Leitura Anterior deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	    }
	    this.ab = new Array("leituraAtualInformada", "Leitura Atual deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	    this.ac = new Array("consumoInformado", "Consumo Inf deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	    this.ad = new Array("codigoImovel", "Imóvel deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	    this.ae = new Array("idAnormalidade", "Anorm. deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    
    function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,campo){
	if(!campo.disabled){
	  if (objeto == null || codigoObjeto == null){
	     if(tipo == "" ){
	      abrirPopup(url,altura, largura);
	     }else{
		  abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		 }
	 }else{
		if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
			alert(msg);
		}
		else{
			abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisaAnormalidadeLeitura=" + tipo, altura, largura);
		}
	}
  }
}
</script>
<script language="JavaScript">


function submeterForm(){

	var form = document.forms[0];

	if(validateLeituraConsumoActionForm(form)){
		if(form.dataLeituraAnteriorFaturamento.value != null && form.dataLeituraAnteriorFaturamento.value != '' && !verificaDataMensagemSemApagar(form.dataLeituraAnteriorFaturamento,'Data Anterior Inválida')){
		}else if(form.dataLeituraAtualInformada.value != null && form.dataLeituraAtualInformada.value != '' && !verificaDataMensagemSemApagar(form.dataLeituraAtualInformada, 'Data Atual Inválida')){
		}else
		if(form.dataLeituraAtualInformada.value != '' && form.leituraAtualInformada.value =='' && form.indicadorLeitura.value == '1' ){
			alert('Informe Leitura Atual');
		}else
		if (form.dataLeituraAtualInformada.value == '' && form.leituraAtualInformada.value !=''){
			alert('Informe Data Atual');
		}else if ( form.indicadorLeitura.value != null && form.indicadorLeitura.value == '1' && form.leituraAtualInformada.value == '' ) {
			alert('Informe Leitura Atual');
		}else if(form.leituraAtualInformada.value != null && !form.leituraAtualInformada.value == '' && form.indicadorLeitura.value == '2'
					&& !form.idAnormalidade.value == '' && form.idAnormalidade.value != null ) {
			alert('A anormalidade não aceita leitura');
		}
		else{
			if(form.dataLeituraAnteriorFaturamento.value != '' && form.leituraAnteriorFaturamento.value ==''){
				alert('Informe Leitura Anterior');
			}else
			if (form.dataLeituraAnteriorFaturamento.value == '' && form.leituraAnteriorFaturamento.value !=''){
				alert('Informe Data Anterior');
			}else{
			
				if (form.leituraAtualInformada.value == '' && form.idAnormalidade.value == '') {
					alert('Informe Anormalidade ou Leitura Atual');
				} else {
					form.auxHidden.value = '';
					form.action="atualizarConsumoResumoAction.do";
					submeterFormPadrao(document.forms[0]);
				}
			}
		}
	}
}

function imovelAnterior(){
 var form = document.forms[0];
 var analisado = form.analisado.value;
 var gerarAviso = form.gerarAviso.value;
 var gerarOS = form.gerarOS.value;
 var gerarRelatorio = form.gerarRelatorio.value;
 var observacao = form.observacao.value;
 
 form.action = "exibirDadosAnaliseMedicaoConsumoResumoAction.do?imovelAnterior=1";
 
 if (analisado != "") {
 	form.action = form.action + "&analisado=" + analisado;
 }
 
 if (gerarAviso != "") {
	form.action = form.action + "&gerarAviso=" + gerarAviso;
 }
 
 if (gerarOS != "") {
	 form.action = form.action + "&gerarOS=" + gerarOS;
 }
 
 if (gerarRelatorio != "") {
	 form.action = form.action + "&gerarRelatorio=" + gerarRelatorio;
 }

 if(observacao != ""){
 	form.action = form.action + "&observacao=" + observacao;
 }

 form.carregaSomentePrimeiraVezHidden.value = '';

 submeterFormPadrao(form);
}
function proximoImovel(){
 var form = document.forms[0];
 var analisado = form.analisado.value;
 var gerarAviso = form.gerarAviso.value;
 var gerarOS = form.gerarOS.value;
 var gerarRelatorio = form.gerarRelatorio.value;
 
 var observacao = form.observacao.value;
 
 form.action = "exibirDadosAnaliseMedicaoConsumoResumoAction.do?proximoImovel=1";
 
 if (analisado != "") {
 	form.action = form.action + "&analisado=" + analisado;
 }
 
 if (gerarAviso != "") {
	form.action = form.action + "&gerarAviso=" + gerarAviso;
 }
 
 if (gerarOS != "") {
	 form.action = form.action + "&gerarOS=" + gerarOS;
 }
 
 if (gerarRelatorio != "") {
	 form.action = form.action + "&gerarRelatorio=" + gerarRelatorio;
 }

 if(observacao != ""){
 	form.action = form.action + "&observacao=" + observacao;
 }
 
 form.carregaSomentePrimeiraVezHidden.value = '';
 
 submeterFormPadrao(form);
}

function pesquisaImovelNaColecao(imovel, tipoMedicao){
	var form = document.forms[0];
	if(validaCampoInteiro(form.codigoImovel)){
		form.action = "exibirDadosAnaliseMedicaoConsumoResumoAction.do?idRegistroAtualizacao="+imovel+"&medicaoTipo="+tipoMedicao+"&consultaImovelLista=Sim";
		submeterFormPadrao(form);
	}else{
		alert("Imóvel deve somente conter números positivos.");
	}
}

//Recebe os dados do(s) popup(s)
    function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

      var form = document.LeituraConsumoActionForm;

       if (tipoConsulta == 'leituraAnormalidade') {
        form.idAnormalidade.value = codigoRegistro;
        form.descricaoAnormalidade.value = descricaoRegistro;
        form.descricaoAnormalidade.style.color = "#000000";
      }

    }
    
    function validaDataCompleta(data, event){
		if(mascaraData(data, event)){
			return false;
		}
	}

	function verificarValorConfirmado() {
		
		var form = document.forms[0];
	
		if (form.confirmacao.checked == true) {
			form.confirmacao.value = '1';
		} else {
			form.confirmacao.value = '';
		}
		
	}
	
	function checkConfirmado(valor) {
		var form = document.forms[0];
		
		if (valor == '1') {
			form.confirmacao.checked = true;
			form.confirmacao.disabled = true;
		} else {
			form.confirmacao.checked = false;
			form.confirmacao.disabled = false;
		}
	}
	
	function verificarValorAnalisado() {
	
		var form = document.forms[0];
	
		if (form.analisado.checked == true) {
			form.analisado.value = '3';
		} else {
			form.analisado.value = '';
		}
		
	}

	function checkAnalisado(valor) {
	
		var form = document.forms[0];
		
		if (valor == '1' || valor == '3') {
			form.analisado.checked = true;
			form.analisado.disabled = true;
		} else {
			form.analisado.checked = false;
			form.analisado.disabled = false;
		}
		
	}
	
	function setarLeituraAtual() {
		var form = document.forms[0];
		if (form.calculoConsumo.value != '-1') {
			form.consumoInformado.value = form.calculoConsumo.value;
		}
	}
	
	function verificarValorGerarAviso() {
	
		var form = document.forms[0];
	
		if (form.gerarAviso.checked == true) {
			form.gerarAviso.value = '1';
		} else {
			form.gerarAviso.value = '';
		}
		
	}
	
	function checkGerarAviso(valor) {
	
		var form = document.forms[0];
		
		if (valor == '1') {
			form.gerarAviso.checked = true;
		} else {
			form.gerarAviso.checked = false;
		}
		
	}
	
	function verificarValorGerarOS() {
	
		var form = document.forms[0];
	
		if (form.gerarOS.checked == true) {
			form.gerarOS.value = '1';
		} else {
			form.gerarOS.value = '';
		}
		
		
		if (form.gerarOS.checked == true) {
			form.observacao.disabled = false;
		} else {
			form.observacao.disabled = true;
			form.observacao.value = "";
		}
	}
	
	function checkGerarOS(valor) {
	
		var form = document.forms[0];
		
		if (valor == '1') {
			form.gerarOS.checked = true;
		} else {
			form.gerarOS.checked = false;
		}
		
	}
	
	function verificarValorGerarRelatorio() {
	
		var form = document.forms[0];
	
		if (form.gerarRelatorio.checked == true) {
			form.gerarRelatorio.value = '1';
		} else {
			form.gerarRelatorio.value = '';
		}
		
	}
	
	function checkGerarRelatorio(valor) {
	
		var form = document.forms[0];
		
		if (valor == '1') {
			form.gerarRelatorio.checked = true;
		} else {
			form.gerarRelatorio.checked = false;
		}
		
	}
	
	function gerarRelatorioAnaliseConsumo(){
		var form = document.forms[0];

		var gerarRelatorioForm = form.gerarRelatorio.value;
		
		if (gerarRelatorioForm != "") {
			toggleBoxCaminho('demodiv',1,"gerarRelatorioAnaliseConsumoAction.do?gerarRelatorio=" + gerarRelatorioForm);
		} else {
			toggleBoxCaminho('demodiv',1,'gerarRelatorioAnaliseConsumoAction.do');
		}
	
	}
	
	function gerarRelatorioMedicaoIndividualizada(){
		var form = document.forms[0];

		toggleBoxCaminho('demodiv',1,'gerarRelatorioFaturamentoLigacoesMedicaoIndividualizadaAction.do');
	
	}
	
	function gerarRelatorioAvisoAnormalidade(){
		var form = document.forms[0];

		var gerarAvisoForm = form.gerarAviso.value;
		
		if (gerarAvisoForm != "") {
			toggleBoxCaminho('demodiv',1,"gerarRelatorioAvisoAnormalidadeAction.do?gerarAviso=" + gerarAvisoForm);
		} else {
			toggleBoxCaminho('demodiv',1,'gerarRelatorioAvisoAnormalidadeAction.do');
		}
	
	}
	
	function gerarOSFiscalizacao(){
		var form = document.forms[0];

		var gerarOSForm = form.gerarOS.value;
		
			if (gerarOSForm != "") {
				 //form.action = "gerarRAImoveisAnormalidadeAction.do?gerarOS=" + gerarOSForm;
				 abrirPopup('exibirPesquisarGeracaoRAImoveisAnormalidadeAction.do?limparForm=sim&gerarOS=' + gerarOSForm+'&observacao='+ form.observacao.value);
			} else {
				//form.action = "gerarRAImoveisAnormalidadeAction.do";
				alert('Gerar OS Fiscalização não selecionado.');
				
			}
	
	}
	
	
	function verificaLeituraAnteriorPositiva() {
		var form = document.forms[0];
		
		if (form.carregaSomentePrimeiraVezHidden.value == '' && 
			form.auxHidden.value != 1 ){
		
			if( parseInt(form.leituraAnteriorFaturamento.value) == parseInt(0) ) {
		
				form.leituraAnteriorFaturamentoHidden.value = 1;
				form.auxHidden.value = 1
			} else {
		
				form.leituraAnteriorFaturamentoHidden.value = 2;
				form.carregaSomentePrimeiraVezHidden.value = false;
			}			
		}			
		
	}
	
	function limpaHidden(){
		var form = document.forms[0];
		form.carregaSomentePrimeiraVezHidden.value = '';
	}
	
	function solicitarReleitura( imovel, tipoMedicao ){
		var form = document.forms[0];
		
		form.action = "exibirDadosAnaliseMedicaoConsumoResumoAction.do?idRegistroAtualizacao="+imovel+"&medicaoTipo="+tipoMedicao+"&solicitarReleitura=Sim";
		submeterFormPadrao(form);
	}
	
	function exibirMensagemReleituraRealizada(){
		var mensagem = "${requestScope.mensagemReleitura}";
		
		if ( mensagem != null && mensagem != "null" && mensagem != "" ){
		  alert( mensagem );
		}
	}
	
	function alterarDadosFaturamento( imovel, tipoMedicao){
		var form = document.forms[0];
		
		form.action = "exibirDadosFaturamentoAction.do?idRegistroAtualizacao="+imovel+"&medicaoTipo="+tipoMedicao;
		submeterFormPadrao(form);
	}
	
	
	
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');checkConfirmado('${sessionScope.confirmacao}');verificarValorConfirmado();checkAnalisado('${sessionScope.analisado}');verificarValorAnalisado();checkGerarAviso('${sessionScope.gerarAviso}');verificarValorGerarAviso();;checkGerarOS('${sessionScope.gerarOS}');verificarValorGerarOS();;checkGerarRelatorio('${sessionScope.gerarRelatorio}');verificarValorGerarRelatorio();desabilitaCampos('${requestScope.habilitaCampos}','${requestScope.desabilitaAtualizarImovel}');verificaLeituraAnteriorPositiva();exibirMensagemReleituraRealizada();">
<html:form action="/atualizarConsumoResumoAction.do"
	name="LeituraConsumoActionForm"
	type="gcom.gui.micromedicao.LeituraConsumoActionForm" method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	<html:hidden property="motivoInterferenciaTipo"
		name="LeituraConsumoActionForm" />
	<html:hidden property="leituraAnteriorFaturamentoHidden" />
	<html:hidden property="carregaSomentePrimeiraVezHidden" />
	<html:hidden property="auxHidden" />
	<html:hidden property="indicadorLeitura" />	

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
					<td class="parabg">Resumo da An&aacute;lise da
					Medi&ccedil;&atilde;o e Consumo do M&ecirc;s</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>

				</tr>
			</table>

			<p>&nbsp;</p>
			<table bordercolor="#000000" width="100%" cellspacing="0">
				<tr>
					<table width="100%" border="0">
						<tr>
							<td colspan="6">
							<table cellspacing="0" cellpadding="0" border="0" width="100%">
								<tr>
									<logic:present name="sucesso">
										<tr>
											<td colspan="7"><img height="20" width="21"
												src="<bean:message key="caminho.imagens"/>sucesso.gif"><strong>Leituras
											e Consumo atualizados com sucesso</strong></td>
										</tr>
									</logic:present>
								<tr>
									<td align="right">
									<table width="100%" bgcolor="#99CCFF">
										<tr class="styleFonteTabelaPrincipal">
											<td colspan="7" class="styleFonteTabelaPrincipal"
												align="center" bgcolor="#79bbfd">Dados do Imóvel</td>
										</tr>
										<!--header da tabela interna -->
										<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
											<td width="24%" align="center">Inscri&ccedil;&atilde;o</td>
											<td width="13%" align="center">Matr&iacute;cula Im&oacute;vel</td>
											<td width="13%" align="center">Im&oacute;vel Condom&iacute;no</td>
											<td width="14%" align="center">Hidr&ocirc;metro</td>
											<td width="13%" align="center">Hidr&ocirc;metro
											Instala&ccedil;&atilde;o</td>
											<td width="12%" align="center">Indicador Po&ccedil;o</td>
											<td width="11%" align="center">Capacidade</td>
										</tr>
										<%if (session.getAttribute("imovelMicromedicaoDadosResumo") != null) {

						%>
										<tr bgcolor="#FFFFFF" class="styleFontePequena">
											<td align="center"><a
												href="javascript:abrirPopup('exibirAnaliseExcecaoConsumoResumoPopupAction.do?codigoImovel=${idImovel}', 300, 700);">
											${inscricaoFormatada}</a> &nbsp;</td>
											<logic:present name="imovelMicromedicaoDadosResumo"
												property="imovel">
												<logic:equal name="imovelMicromedicaoDadosResumo"
													property="imovel.indicadorImovelCondominio" value="2">
													<td align="center"><a
														href="javascript:abrirPopup('exibirConsultarRegistroAtendimentoPendentesImovelAction.do?idImovel=${idImovel}', 300, 700);">
													${idImovel}</a></td>
													<td align="center">Não</td>
												</logic:equal>
												<logic:equal name="imovelMicromedicaoDadosResumo"
													property="imovel.indicadorImovelCondominio" value="1">
													<td align="center"><a
														href="javascript:abrirPopup('ligacoesMedicaoIndividualizadasAction.do?codigoImovel=${idImovel}', 300, 800);"">
													${idImovel}</a></td>
													<td align="center">Sim</td>
												</logic:equal>
											</logic:present>
											<logic:notPresent name="imovelMicromedicaoDadosResumo"
												property="imovel">
												<td align="center">${idImovel}</td>
												<td align="center">Não</td>
											</logic:notPresent>
											<td align="center">${imovelMicromedicaoDadosResumo.imovel.hidrometroInstalacaoHistorico.hidrometro.numero}</td>
											<td align="center"><bean:write
												name="LeituraConsumoActionForm"
												property="instalacaoHidrometro" /></td>
											<logic:present name="poco">
												<td align="center">Sim</td>
											</logic:present>
											<logic:notPresent name="poco">
												<td align="center">Não</td>
											</logic:notPresent>
											<td align="center">${imovelMicromedicaoDadosResumo.imovel.hidrometroInstalacaoHistorico.hidrometro.hidrometroCapacidade.descricao}</td>
										</tr>
										<tr>
											<td height="38" colspan="7">
											<table width="100%" bgcolor="#99CCFF" cellpadding="0"
												border="0">
												<!--header da tabela interna -->
												<tr bgcolor="#99CCFF" class="styleFontePeqNegrito">
													<td width="17%" bgcolor="#99CCFF" align="center">
													Situa&ccedil;&atilde;o de &Aacute;gua</td>
													<td width="17%" align="center">Situa&ccedil;&atilde;o de
													Esgoto</td>
													<td width="9%" align="center">Área</td>
													<td width="8%" align="center">Cat.</td>
													<td width="9%" align="center">Econ.</td>
													<td width="12%" align="center">Rateio</td>
													<td width="21%" align="center">Perfil do Imóvel</td>
													<td width="22%" align="center">Rota</td>
													<td width="21%" align="center">Seq.Rota</td>
												</tr>
												<tr bgcolor="#FFFFFF" class="styleFontePequena">
													<td align="center"><logic:equal
														name="imovelMicromedicaoDadosResumo"
														property="imovel.ligacaoAguaSituacao.id"
														value="<%=LigacaoAguaSituacao.CORTADO.toString()%>">
														<font color="#FF0000">
														${imovelMicromedicaoDadosResumo.imovel.ligacaoAguaSituacao.descricao}
														</font>
													</logic:equal> <logic:notEqual
														name="imovelMicromedicaoDadosResumo"
														property="imovel.ligacaoAguaSituacao.id"
														value="<%=LigacaoAguaSituacao.CORTADO.toString()%>">
															${imovelMicromedicaoDadosResumo.imovel.ligacaoAguaSituacao.descricao}
														</logic:notEqual></td>
													<td align="center"><logic:equal
														name="imovelMicromedicaoDadosResumo"
														property="imovel.ligacaoEsgotoSituacao.id"
														value="<%=LigacaoEsgotoSituacao.LIG_FORA_DE_USO.toString()%>">
														<font color="#FF0000">
														${imovelMicromedicaoDadosResumo.imovel.ligacaoEsgotoSituacao.descricao}
														</font>
													</logic:equal> <logic:notEqual
														name="imovelMicromedicaoDadosResumo"
														property="imovel.ligacaoEsgotoSituacao.id"
														value="<%=LigacaoEsgotoSituacao.LIG_FORA_DE_USO.toString()%>">
															${imovelMicromedicaoDadosResumo.imovel.ligacaoEsgotoSituacao.descricao}
														</logic:notEqual></td>
													<td align="center"><logic:notEmpty
														name="imovelMicromedicaoDadosResumo"
														property="imovel.areaConstruida">
														<bean:write name="imovelMicromedicaoDadosResumo"
															property="imovel.areaConstruida" formatKey="money.format" />
													</logic:notEmpty> <logic:empty
														name="imovelMicromedicaoDadosResumo"
														property="imovel.areaConstruida">
													&nbsp;
													</logic:empty></td>
													<td align="center">${categoria.descricaoAbreviada}</td>
													<td align="center">${imovelMicromedicaoDadosResumo.imovel.quantidadeEconomias}</td>
													<td align="center">${LeituraConsumoActionForm.rateio}</td>
													<td align="center"><logic:equal
														name="imovelMicromedicaoDadosResumo"
														property="imovel.imovelPerfil.id"
														value="<%=ImovelPerfil.GRANDE.toString()%>">
														<font color="#FF0000">
														${imovelMicromedicaoDadosResumo.imovel.imovelPerfil.descricao}
														</font>
													</logic:equal> <logic:notEqual
														name="imovelMicromedicaoDadosResumo"
														property="imovel.imovelPerfil.id"
														value="<%=ImovelPerfil.GRANDE.toString()%>">
															${imovelMicromedicaoDadosResumo.imovel.imovelPerfil.descricao}
														</logic:notEqual></td>
													<td align="center">${sessionScope.leituraConsumoActionForm.rota}</td>
													<td align="center">${sessionScope.leituraConsumoActionForm.seqRota}</td>
												</tr>
											</table>
											</td>
										</tr>
										<%}

					%>
									</table>
									</td>
								</tr>
								<tr>
									<td height="5"></td>
								</tr>
								<tr>
									<td align="right">
									<table width="100%" bgcolor="#99CCFF">
										<tr bordercolor="#79bbfd" class="styleFontePeqNegrito">
											<td width="526" height="20" colspan="2" bgcolor="#79bbfd"
												class="styleFonteTabelaPrincipal">
											<div align="center"><strong>Dados do Faturamento</strong></div>
											</td>
										</tr>
										<tr bordercolor="#99CCFF" class="styleFontePequena"
											bgcolor="#cbe5fe">
											<td height="140">
											<table width="100%" align="center" bgcolor="#cbe5fe"
												class="styleFontePequena">
												<!--corpo da segunda tabela-->
												<tr bgcolor="#cbe5fe">
													<td width="69" height="24"></td>
													<td width="80" class="styleFontePeqNegrito"><strong> Data</strong></td>
													<td width="50" class="styleFontePeqNegrito"><strong>Leitura
													</strong></td>
													<td width="94" class="styleFontePeqNegrito"><strong>Cons.
													Fat. : </strong></td>
													<td width="83"><html:text property="consumo"
														readonly="true"
														style="background-color:#EFEFEF; border:0;font-size:xx-small"
														size="5" /></td>
													<td colspan="2" class="styleFontePeqNegrito">
													Cons.Medido:&nbsp;&nbsp;&nbsp;&nbsp; <html:text
														property="medido" readonly="true"
														style="background-color:#EFEFEF; border:0;font-size:xx-small"
														size="4" /> <html:checkbox property="confirmacao"
														value="1" onclick="verificarValorConfirmado();" /><strong> Conf.</strong></td>
												</tr>
												<tr bgcolor="#cbe5fe">
													<td width="67" height="22" class="styleFontePeqNegrito"><strong>Anterior
													:</strong></td>
													<td width="80"><html:text
														property="dataLeituraAnteriorFaturamento"
														readonly="true"
														style="background-color:#EFEFEF; border:0;font-size:xx-small"
														size="10" /></td>

													<td width="50"><html:text
														property="leituraAnteriorFaturamento"
														style="background-color:#EFEFEF; border:0;font-size:xx-small"
														size="5" readonly="true"/></td>
													<td width="83" class="styleFontePeqNegrito"><strong>&nbsp;M&eacute;dia
													: </strong></td>
													<td width="83"><html:text property="consumoMedioImovel"
														readonly="true"
														style="background-color:#EFEFEF; border:0;font-size:xx-small"
														size="5" /></td>
													<td colspan="2" class="styleFontePeqNegrito"><strong>Cons.
													Inf. : &nbsp;</strong> <html:text
														property="consumoInformado" style="font-size:xx-small"
														size="5" />&nbsp;<html:select property="calculoConsumo"
														onchange="setarLeituraAtual();">
														<logic:notEmpty name="colecaoCalculoConsumo">
															<option
																value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
															<html:options collection="colecaoCalculoConsumo"
																labelProperty="descricao" property="consumo" />
														</logic:notEmpty>
													</html:select></td>

												</tr>
												<tr bgcolor="#cbe5fe">
													<td width="67" height="22" class="styleFontePeqNegrito"><strong>Atual:</strong></td>

													<td width="80"><html:text
														property="dataLeituraAtualInformada" maxlength="10"
														onkeypress="validaDataCompleta(this, event);return isCampoNumerico(event);"
														style="font-size:xx-small" size="10" /></td>

													<td width="50"><html:text property="leituraAtualInformada"
														style="font-size:xx-small" size="8"
														onkeypress="return isCampoNumerico(event);" /></td>
													<td width="94" class="styleFontePeqNegrito"><strong>% Var.
													Cons :</strong></td>
													<td width="83"><html:text property="varConsumo"
														readonly="true"
														style="background-color:#EFEFEF; border:0;font-size:xx-small"
														size="5" /></td>
													<td width="118" class="styleFontePeqNegrito"><strong>Med.
													Hidrom. :</strong></td>
													<td width="94"><html:text property="consumoMedioHidrometro"
														readonly="true"
														style="background-color:#EFEFEF; border:0;font-size:xx-small"
														size="5" /></td>
												</tr>
												<tr bgcolor="#cbe5fe">
													<td width="67" height="22" class="styleFontePeqNegrito"><strong>Campo:</strong></td>

													<td width="80"><html:text property="dataLeituraCampo"
														readonly="true"
														style="background-color:#EFEFEF; border:0;font-size:xx-small"
														size="10" /></td>

													<td width="50"><html:text property="leituraCampo"
														style="background-color:#EFEFEF; border:0;font-size:xx-small"
														size="9" readonly="true" maxlength="8" /></td>
													<td class="styleFontePeqNegrito">
													<div align="left">&nbsp;<strong>Leiturista : </strong></div>
													</td>
													<td colspan="3">
													<div align="left"><html:text property="nomeLeiturista"
														style="background-color:#EFEFEF; border:0;font-size:xx-small"
														size="50" readonly="true" /> &nbsp;</div>
													</td>
												</tr>
												<tr bgcolor="#cbe5fe">
													<td height="22" class="styleFontePeqNegrito">
													<div align="left"><strong>Ajustada</strong>&nbsp;:</div>
													</td>

													<td><html:text property="dataLeituraAtualFaturamento"
														readonly="true"
														style="background-color:#EFEFEF; border:0;font-size:xx-small"
														size="10" /></td>

													<td><html:text property="leituraAtualFaturada"
														style="background-color:#EFEFEF; border:0;font-size:xx-small"
														size="9" maxlength="8" readonly="true" /></td>
													<td class="styleFontePeqNegrito">
													<div align="left"><strong>Dias Cons. </strong>:</div>
													</td>
													<td>
													<div align="left"><html:text property="diasConsumo"
														readonly="true"
														style="background-color:#EFEFEF; border:0;font-size:xx-small"
														size="5" /></div>
													</td>
													<td width="118" class="styleFontePeqNegrito"><strong>Tipo
													Consumo&nbsp;:</strong></td>
													<td width="94"><html:text property="tipoConsumo"
														readonly="true"
														style="background-color:#EFEFEF; border:0;font-size:xx-small"
														value="${imovelMicromedicaoConsumo.consumoHistorico.consumoTipo.descricaoAbreviada}"
														size="5"
														title="${imovelMicromedicaoConsumo.consumoHistorico.consumoTipo.descricao}" /></td>
												</tr>
												<tr bgcolor="#cbe5fe">
													<td height="28" class="styleFontePeqNegrito">Anorm. :</td>
													<td colspan="4"><html:text property="idAnormalidade"
														style="font-size:xx-small" size="8"
														onkeypress="validaEnter(event, 'exibirDadosAnaliseMedicaoConsumoResumoAction.do?pesquisarAnormalidade=sim', 'idAnormalidade');return isCampoNumerico(event);" />

													<a
														href="javascript:chamarPopup('exibirPesquisarLeituraAnormalidadeAction.do', 'leituraAnormalidade', null, null, 600, 500, '',document.forms[0].idAnormalidade);">
													<img width="23" height="21" border="0"
														src="<bean:message key='caminho.imagens'/>pesquisa.gif"
														title="Pesquisar Anormalidade" /></a> <html:text
														property="descricaoAnormalidade" readonly="true"
														style="background-color:#EFEFEF; border:0;font-size:xx-small"
														size="30" /> <a
														href="javascript:limparPesquisaAnormalidade();"> <img
														src="<bean:message key="caminho.imagens"/>limparcampo.gif"
														border="0" title="Apagar" /></a>
													<div align="left"></div>
													</td>
													<td class="styleFontePeqNegrito">
													<div align="left">&nbsp;<strong>Anor. Cons. : </strong></div>
													</td>
													<td>
													<div align="left"><html:text
														property="consumoAnormalidadeAbreviada"
														style="background-color:#EFEFEF; border:0;font-size:xx-small"
														size="7" readonly="true"
														title="${LeituraConsumoActionForm.anormalidadeConsumo}" />
													&nbsp;</div>
													</td>
												</tr>
												<tr>
													<td class="styleFontePeqNegrito"><strong>Usuário:</strong></td>
													<td colspan="5"><html:text property="loginUsuario"
														readonly="true"
														style="background-color:#EFEFEF; border:0;font-size:xx-small"
														size="12" /> <html:text property="nomeUsuario"
														readonly="true"
														style="background-color:#EFEFEF; border:0;font-size:xx-small"
														size="50" /></td>
												</tr>
												<logic:notEmpty name="colecaoMotivoInterferenciaTipo"
													scope="session">
													<tr>
														<td class="styleFontePeqNegrito" align="left" colspan="2"><strong>Motivo
														Interferencia:</strong></td>
														<td colspan="5"><html:select
															property="motivoInterferenciaTipo">
															<option
																value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
															<html:options collection="colecaoMotivoInterferenciaTipo"
																labelProperty="descricao" property="id" />
														</html:select></td>
													</tr>
												</logic:notEmpty>
												<tr>
													<td colspan="2"><html:checkbox property="gerarAviso"
														value="" onclick="verificarValorGerarAviso();" /><strong>Gerar
													Aviso</strong></td>
													<td colspan="2"><html:checkbox property="gerarRelatorio"
														value="" onclick="verificarValorGerarRelatorio();" /><strong>Gerar
													Relatório Análise</strong></td>
													<td colspan="2"><html:checkbox property="gerarOS" value=""
														onclick="verificarValorGerarOS();" /><strong>Gerar OS
													Fiscalização</strong></td>
													<td align="right"><html:checkbox property="analisado"
														value="" onclick="verificarValorAnalisado();" /><strong>
													Analisado</strong></td>
												</tr>

												<!-- Aqui -->

												<tr>
													<td ><strong>Observação:</strong></td>
													<td colspan="5"><span class="style2"> <strong> <html:textarea
														property="observacao" cols="40" rows="4" 
														onkeyup="limitTextArea(document.forms[0].observacao, 600, document.getElementById('utilizado'), document.getElementById('limite'));" /><br>

													<strong> <span id="utilizado">0</span>/<span id="limite">600</span>
													</strong> </strong> </span></td>
												</tr>
												
											</table>
											</td>
										</tr>
									</table>
									</td>
								</tr>

								<logic:notEmpty name="colecaoFaturamentoSituacaoEspecial">
									<tr>
										<td height="5"></td>
									</tr>
									<tr>
										<td>
										<table bgcolor="#90c7fc" width="100%" border="0">
											<tr>
												<td colspan="5" bordercolor="#79bbfd" align="center"
													bgcolor="#79bbfd"><strong>Situa&ccedil;&otilde;es Especiais
												de Faturamento </strong></td>
											</tr>
											<tr bgcolor="#90c7fc">
												<td align="center" bgcolor="#90c7fc" width="28%">
												<div class="style9"><font style="font-size: 9px;"
													color="#000000"
													face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo</strong></font></div>
												</td>
												<td align="center" bgcolor="#90c7fc" width="28%"><font
													style="font-size: 9px;" color="#000000"
													face="Verdana, Arial, Helvetica, sans-serif"><strong>Motivo</strong></font></td>
												<td align="center" bgcolor="#90c7fc" width="15%">
												<div class="style9"><font style="font-size: 9px;"
													color="#000000"
													face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano
												In&iacute;cio&nbsp;</strong> </font></div>
												</td>
												<td align="center" bgcolor="#90c7fc" width="15%"><font
													style="font-size: 9px;" color="#000000"
													face="Verdana, Arial, Helvetica, sans-serif"><strong>M&ecirc;s/Ano
												Fim</strong></font></td>
												<td align="center" bgcolor="#90c7fc">
												<div class="style9"><font style="font-size: 9px;"
													color="#000000"
													face="Verdana, Arial, Helvetica, sans-serif"> <strong>Usu&aacute;rio</strong></font></div>
												</td>
											</tr>

											<tr>
												<td colspan="5">
												<div style="width: 100%; height: 100%; overflow: auto;">
												<table width="100%" align="left" bgcolor="#99CCFF">
													<!--corpo da segunda tabela-->
													<logic:iterate name="colecaoFaturamentoSituacaoEspecial"
														id="faturamentosSituacaoHistorico"
														type="FaturamentoSituacaoHistorico">
														<tr bgcolor="#FFFFFF">

															<td align="left" width="28%">
															<div class="style9"><font style="font-size: 9px;"
																color="#000000"
																face="Verdana, Arial, Helvetica, sans-serif"> <logic:present
																name="faturamentosSituacaoHistorico"
																property="faturamentoSituacaoTipo">

																<div style="font-size: 9px;" color="#000000"><bean:write
																	name="faturamentosSituacaoHistorico"
																	property="faturamentoSituacaoTipo.descricao" />
															</logic:present> </font></div>
															</td>
															<td align="left" width="28%"><font
																style="font-size: 9px;" color="#000000"
																face="Verdana, Arial, Helvetica, sans-serif"> <logic:present
																name="faturamentosSituacaoHistorico"
																property="faturamentoSituacaoMotivo">

																<bean:write name="faturamentosSituacaoHistorico"
																	property="faturamentoSituacaoMotivo.descricao" />

															</logic:present> </font></td>


															<td align="center" width="15%">
															<div class="style9"><font style="font-size: 9px;"
																color="#000000"
																face="Verdana, Arial, Helvetica, sans-serif"> <%=""
											+ Util
													.formatarMesAnoReferencia(faturamentosSituacaoHistorico
															.getAnoMesFaturamentoSituacaoInicio())%> </font></div>
															</td>
															<td align="center" width="15%"><font
																style="font-size: 9px;" color="#000000"
																face="Verdana, Arial, Helvetica, sans-serif"> <%=""
											+ Util
													.formatarMesAnoReferencia(faturamentosSituacaoHistorico
															.getAnoMesFaturamentoSituacaoFim())%> </font></td>

															<td align="center">
															<div class="style9"><font style="font-size: 9px;"
																color="#000000"
																face="Verdana, Arial, Helvetica, sans-serif"> <logic:present
																name="faturamentosSituacaoHistorico" property="usuario">
																<bean:write name="faturamentosSituacaoHistorico"
																	property="usuario.nomeUsuario" />
															</logic:present></font></div>
															</td>


														</tr>
													</logic:iterate>

												</table>
												</div>
												</td>
											</tr>

										</table>
										</td>
									</tr>
								</logic:notEmpty>
								<tr>
									<td height="5"></td>
								</tr>
								<tr>
									<td align="right">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td colspan="10" class="styleFonteTabelaPrincipal"
												align="center" bgcolor="#79bbfd">Hist&oacute;rico de
											Medi&ccedil;&atilde;o</td>
										</tr>
										<tr bgcolor="#90c7fc" class="styleFontePeqNegrito">
											<td width="8%" align="center">M&ecirc;s e Ano</td>
											<td width="10%" align="center">Dt. Leit. Inform.</td>
											<td width="6%" align="center">Leit. Inform.</td>
											<td width="10%" align="center">Dt. Leit. Faturada</td>
											<td width="8%" align="center">Leit. Faturada</td>
											<td width="8%" align="center">Cons.</td>
											<td width="9%" align="center">Cons. Calc. Média</td>
											<td width="10%" align="center">Anorm. Cons.</td>
											<td width="10%" align="left">Anorm. Leit.</td>
											<td width="14%" align="left">Sit. Leit. Atual</td>
										</tr>
										<logic:present name="imoveisMicromedicao">
											<tr bordercolor="#90c7fc">
												<td colspan="10" height="100">
												<div style="width: 100%; height: 100%; overflow: auto;">
												<table width="100%" align="left" bgcolor="#90c7fc">
													<%int cont = 0;%>
													<logic:iterate name="imoveisMicromedicao"
														id="imovelMicromedicaoIterator">
														<!--corpo da segunda tabela-->
														<%cont = cont + 1;
							if (cont % 2 == 0) {%>
														<tr bgcolor="#cbe5fe" class="styleFontePequena">
															<%} else {

							%>
														<tr bgcolor="#FFFFFF" class="styleFontePequena">
															<%}%>
															<td width="8%" align="center"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif">
															${imovelMicromedicaoIterator.medicaoHistorico.mesAno}</font></td>
															<td width="10%" align="center"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif"> <%=Util
													.formatarData(((gcom.cadastro.imovel.bean.ImovelMicromedicao) imovelMicromedicaoIterator)
															.getMedicaoHistorico()
															.getDataLeituraAtualInformada())%> </font></td>
															<td width="6%" align="center"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif">
															${imovelMicromedicaoIterator.medicaoHistorico.leituraAtualInformada}</font></td>
															<td width="10%" align="center"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif"> <%=Util
													.formatarData(((gcom.cadastro.imovel.bean.ImovelMicromedicao) imovelMicromedicaoIterator)
															.getMedicaoHistorico()
															.getDataLeituraAtualFaturamento())%> </font></td>
															<td width="8%" align="center"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif">
															${imovelMicromedicaoIterator.medicaoHistorico.leituraAtualFaturamento}</font></td>
															<td width="8%" align="center"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif">
															${imovelMicromedicaoIterator.consumoHistorico.numeroConsumoFaturadoMes}</font></td>

															<td width="9%" align="center">${imovelMicromedicaoIterator.consumoHistorico.numeroConsumoCalculoMedia}</td>

															<td width="10%" align="center"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif"
																title="${imovelMicromedicaoIterator.consumoHistorico.consumoAnormalidade.descricao}">
															${imovelMicromedicaoIterator.consumoHistorico.consumoAnormalidade.descricaoAbreviada}</font></td>
															<td width="10%" align="center"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif"
																title="${imovelMicromedicaoIterator.medicaoHistorico.leituraAnormalidadeFaturamento.descricao}">
															${imovelMicromedicaoIterator.medicaoHistorico.leituraAnormalidadeFaturamento.id}
															</font></td>
															<td width="14%"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif">
															${imovelMicromedicaoIterator.medicaoHistorico.leituraSituacaoAtual.descricao}</font>
															</td>
														</tr>
													</logic:iterate>
												</table>
												</div>
												</td>
											</tr>
										</logic:present>
									</table>
									</td>
								</tr>
								<tr>
									<td align="right">
									<table width="100%" border="0" cellspacing="0">
										<tr>
											<td width="15%"><input type="button" name="Button3"
												class="bottonRightCol" value="Voltar" id="habilita"
												onclick="javascript:limpaHidden();redirecionarSubmit('/gsan/exibirFiltrarExcecoesLeiturasConsumosAction.do?nomeCaminhoMapping=efetuarAnaliseExcecoesLeiturasConsumos')"></td>
											<td width="70%" align="center"><strong><font
												style="styleFontePeqNegrito"> Im&oacute;vel:</font></strong>
											<INPUT name="codigoImovel" TYPE="text"
												style="font-size:xx-small" size="9" maxlength="9"
												id="habilita" onkeypress="return isCampoNumerico(event);"> <input
												type="button" name="pesquisaImovel" class="bottonRightCol"
												value="Pesquisar"
												onclick="pesquisaImovelNaColecao(document.forms[0].codigoImovel.value, '${tipoMedicao}');">

											&nbsp;&nbsp;&nbsp;&nbsp;<strong><font
												style="styleFontePeqNegrito">Posição: ${indiceImovel} /
											${totalRegistros}</font></strong></td>
											<td width="15%">
											<div align="right"><input style="width: 70px" type="button"
												name="Button2" class="bottonRightCol" value="Atualizar"
												onclick="submeterForm();" id="desabilita"></div>
											</td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td colspan="7">
							<table cellspacing="0" cellpadding="0" width="100%" border="0">
								<tr>
									<td align="left" valign="bottom" width="21%">
										<logic:present name="solicitarReleitura">
											<input type="button" disabled="disabled"
												name="btnSolicitarReleitura" class="bottonRightCol"
												value="Solicitar Releitura" onclick="solicitarReleitura( '${idImovel}', '${tipoMedicao}' );" />										
										</logic:present>
										
										<logic:notPresent name="solicitarReleitura">
											<input type="button"
												name="btnSolicitarReleitura" class="bottonRightCol"
												value="Solicitar Releitura" onclick="solicitarReleitura( '${idImovel}', '${tipoMedicao}' );" />										
										</logic:notPresent>
									</td>
									
									<td align="left" valign="bottom" width="21%">	
										<logic:present name="releituraJaRealizada">
											<font color="red">A releitura já foi realizada.</font>
										</logic:present>
										<logic:present name="rotaFinalizada">
											<font color="red">Rota finalizada.</font>
										</logic:present>
									</td>
									
									<logic:present name="habilitaBotaoAlterarDadosFaturamento" scope="session">
										<td align="left" valign="bottom" >
											<input type="button"
												   name="btnAlterarDadosFaturamento" class="bottonRightCol"
												   value="Alterar dados para Faturamento" 
												   onclick="alterarDadosFaturamento( '${idImovel}', '${tipoMedicao}' );" />
										</td>
									</logic:present>
									
									<td width="50%">&nbsp;
									<table cellspacing="0" cellpadding="0" width="100%" border="0">
										<tr>
											<td>
											<table cellspacing="0" cellpadding="0" width="100%"
												border="0">
												<tr>
													<td width="100%">&nbsp; <logic:notPresent
														name="desabilitaBotaoAnterior">
														<table cellspacing="0" cellpadding="0" width="100%"
															border="0">
															<tr>
																<td align="right" width="83%"><img
																	src="<bean:message key="caminho.imagens"/>voltar.gif"
																	onclick="imovelAnterior();"></td>
																<td align="left" width="15%"><input type="button"
																	name="Button" class="buttonAbaRodaPe"
																	value="Imóvel Anterior" onclick="imovelAnterior();" /></td>
															</tr>
														</table>
													</logic:notPresent></td>

													<td width="100%">&nbsp; <logic:notPresent
														name="desabilitaBotaoProximo">
														<table cellspacing="0" cellpadding="0" width="100%"
															border="0">
															<tr>
																<td align="left" width="15%"><input type="button"
																	name="Button" class="buttonAbaRodaPe"
																	value="Próximo Imóvel" onclick="proximoImovel();" /></td>
																<td align="left" width="83%"><img
																	src="<bean:message key="caminho.imagens"/>avancar.gif"
																	onclick="proximoImovel()"></td>
															</tr>
														</table>
													</logic:notPresent></td>
												</tr>
											</table>
											</td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td colspan="6"><input type="button" name="Button2"
								class="bottonRightCol" value="Gerar Avisos"
								onclick="javascript:gerarRelatorioAvisoAnormalidade();" /> <input
								type="button" name="Button2" class="bottonRightCol"
								value="Gerar Relatório Análise"
								onclick="javascript:gerarRelatorioAnaliseConsumo();" /> <input
								type="button" name="Button2" class="bottonRightCol"
								value="Gerar OS Fiscalização"
								onclick="javascript:gerarOSFiscalizacao();" /> <logic:equal
								name="imovelMicromedicaoDadosResumo"
								property="imovel.indicadorImovelCondominio" value="1"
								scope="session">

								<input type="button" name="Button2" class="bottonRightCol"
									value="Gerar Med. Individualizada"
									onclick="javascript:gerarRelatorioMedicaoIndividualizada();" />
							</logic:equal></td>
						</tr>
					</table>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=" />
	<%@ include file="/jsp/util/rodape.jsp"%>


</html:form>
</body>
</html:html>
