<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="EmissaoHistogramaEsgotoEconomiaDadosInformarActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript">
	
	function validarForm(){
		
		var form = document.forms[0];
		
		if(validateEmissaoHistogramaEsgotoEconomiaDadosInformarActionForm(form) && 
			validaCamposObrigatorios() && 
			validaQtdeConsumoFaixa()){
			
  			form.action='gerarRelatorioHistogramaEsgotoEconomiaDadosInformarAction.do';
			submeterFormPadrao(form);
		}
	}
	
	/* Valida se informou alguma faixa para o consumo faixa por categoria */
	function validaQtdeConsumoFaixa(){
		var form = document.forms[0];
		
		var msg = "";
		
		if(form.tamanhoColecao.value == '0'){
			msg = 'Deve existir ao menos uma faixa para consumo.';
		}
			
		if(msg != ""){
			alert(msg);
			return false;
		}else{		
			return true;
		}
	}
	
	
	function validaCamposObrigatorios(){
	
		var form = document.forms[0];
		
		var opcaoTotalizacao = form.opcaoTotalizacao.value;
		var msg = "";
		
 		if(opcaoTotalizacao >= 6 && opcaoTotalizacao <= 9){
		
			if(form.gerenciaRegional.value == "-1"){
				msg = "Informe Gerência Regional";
				form.gerenciaRegional.focus();
			}
		
		}else if(opcaoTotalizacao >= 10 && opcaoTotalizacao <= 12){


			if(form.gerenciaRegional.value == "-1"){
				msg = "Informe Gerência Regional";
				form.gerenciaRegional.focus();
			}
			
			if(form.unidadeNegocio.value == "-1"){

				if(msg == ""){
					msg = "Informe Unidade de Negócio";
					form.unidadeNegocio.focus();
				}else{
					msg = msg+"\n"+"Informe Unidade de Negócio";
				}				
			}
			
		}else if(opcaoTotalizacao >= 13 && opcaoTotalizacao <= 15){
			
			if(form.eloPolo.value == ""){
				msg = "Informe Elo Pólo";
				form.eloPolo.focus();				
			}

		}else if(opcaoTotalizacao >= 16 && opcaoTotalizacao <= 18){
			
			if(form.localidade.value == ""){
				msg = "Informe Localidade";
				form.localidade.focus();
			}
		
		}else if(opcaoTotalizacao >= 19 && opcaoTotalizacao <= 20){

			if(form.localidade.value == ""){
				msg = "Informe Localidade";
				form.localidade.focus();
			}
			
			if(form.setorComercial.value == ""){
				if(msg == ""){
					msg = "Informe Setor Comercial";
					form.setorComercial.focus();
				}else{
					msg = msg+"\n"+"Informe Setor Comercial";
				}
				
			}			
			
		}else if(opcaoTotalizacao == 21){

			if(form.localidade.value == ""){
				msg = "Informe Localidade";
				form.localidade.focus();
			}
			
			if(form.setorComercial.value == ""){
				if(msg == ""){
					msg = "Informe Setor Comercial";
					form.setorComercial.focus();
				}else{
					msg = msg+"\n"+"Informe Setor Comercial";
				}

			}	
			
			if(form.quadra.value == ""){
				if(msg == ""){
					msg = "Informe Quadra";
					form.quadra.focus();
				}else{
					msg = msg+"\n"+"Informe Quadra";
				}
			}
		}	
		
		if(msg != ""){
			alert(msg);
			return false;
		}else{
			return true;
		}
	
	}
	
	function habilitaDesabilitaFormulario(limpaSessao){
		var form = document.forms[0];
		
		
		var opcaoTotalizacao = form.opcaoTotalizacao.value;
		
		if(opcaoTotalizacao >= 1 && opcaoTotalizacao <= 5){
		
			habilitaDesabilitaGerenciaRegional(true);
			habilitaDesabilitaUnidadeNegocio(true);
			habilitaDesabilitaEloPolo(true);
			habilitaDesabilitaLocalidade(true);
			habilitaDesabilitaSetorComercial(true);
			habilitaDesabilitaQuadra(true);
		
		}else if(opcaoTotalizacao >= 6 && opcaoTotalizacao <= 9){
		
			habilitaDesabilitaGerenciaRegional(false);
			
			habilitaDesabilitaUnidadeNegocio(true);
			habilitaDesabilitaEloPolo(true);
			habilitaDesabilitaLocalidade(true);
			habilitaDesabilitaSetorComercial(true);
			habilitaDesabilitaQuadra(true);
		
		}else if(opcaoTotalizacao >= 10 && opcaoTotalizacao <= 12){

			habilitaDesabilitaGerenciaRegional(false);
			habilitaDesabilitaUnidadeNegocio(false);
			
			habilitaDesabilitaEloPolo(true);
			habilitaDesabilitaLocalidade(true);
			habilitaDesabilitaSetorComercial(true);
			habilitaDesabilitaQuadra(true);
					
		}else if(opcaoTotalizacao >= 13 && opcaoTotalizacao <= 15){
			
			habilitaDesabilitaEloPolo(false);
			
			habilitaDesabilitaGerenciaRegional(true);
			habilitaDesabilitaUnidadeNegocio(true);
			habilitaDesabilitaLocalidade(true);
			habilitaDesabilitaSetorComercial(true);
			habilitaDesabilitaQuadra(true);
			
		}else if(opcaoTotalizacao >= 16 && opcaoTotalizacao <= 18){
			
			habilitaDesabilitaLocalidade(false);
			
			habilitaDesabilitaGerenciaRegional(true);
			habilitaDesabilitaUnidadeNegocio(true);
			habilitaDesabilitaEloPolo(true);
			habilitaDesabilitaSetorComercial(true);
			habilitaDesabilitaQuadra(true);
			
		}else if(opcaoTotalizacao >= 19 && opcaoTotalizacao <= 20){

			habilitaDesabilitaLocalidade(false);
			habilitaDesabilitaSetorComercial(false);
			
			habilitaDesabilitaGerenciaRegional(true);
			habilitaDesabilitaUnidadeNegocio(true);
			habilitaDesabilitaEloPolo(true);
			habilitaDesabilitaQuadra(true);
			
		}else if(opcaoTotalizacao == 21){

			habilitaDesabilitaLocalidade(false);
			habilitaDesabilitaSetorComercial(false);
			habilitaDesabilitaQuadra(false);
			
			habilitaDesabilitaGerenciaRegional(true);
			habilitaDesabilitaUnidadeNegocio(true);
			habilitaDesabilitaEloPolo(true);
		}else{
			habilitaDesabilitaLocalidade(false);
			habilitaDesabilitaSetorComercial(false);
			habilitaDesabilitaQuadra(false);
			
			habilitaDesabilitaGerenciaRegional(false);
			habilitaDesabilitaUnidadeNegocio(false);
			habilitaDesabilitaEloPolo(false);
		
		}
		
		if(limpaSessao){
			limparSemOpcaoTotalizacao();
		}
	}
	
	function limparSemOpcaoTotalizacao(){
  		var form = document.forms[0];
  		
  		limparEloPolo();
  		limparLocalidade();
  		limparSetorComercial();
  		limparQuadra();

  		form.gerenciaRegional.value = "-1";
  		form.unidadeNegocio.value = "-1";

  		form.action='emissaoHistogramaEsgotoEconomiaDadosInformarAction.do?menu=sim';
	    form.submit();
  		
	}
	
	/*
	*	valor = true - desabilita
	*	valor = false - habilita
	*/
	function habilitaDesabilitaGerenciaRegional(valor){
		var form = document.forms[0];
		
		if(valor){
			form.gerenciaRegional.value = "-1";
		}
		
		form.gerenciaRegional.disabled = valor;
	}
	
	function habilitaDesabilitaUnidadeNegocio(valor){
		var form = document.forms[0];
		
		if(valor){			
			form.unidadeNegocio.value = "-1";
		}
		form.unidadeNegocio.disabled = valor;
	}
	
	function habilitaDesabilitaEloPolo(valor){
		var form = document.forms[0];
		
		if(valor){			
			form.eloPolo.value = "";
			form.nomeEloPolo.value = "";
		}
		form.eloPolo.disabled = valor;
	
	}
	
	function habilitaDesabilitaLocalidade(valor){
		var form = document.forms[0];
		if(valor){			
			form.localidade.value = "";
			form.nomeLocalidade.value = "";
		}
		form.localidade.disabled = valor;
	
	}
	
	function habilitaDesabilitaSetorComercial(valor){
		var form = document.forms[0];
		if(valor){			
			form.setorComercial.value = "";
			form.nomeSetorComercial.value = "";
		}
		form.setorComercial.disabled = valor;
	
	}
	
	function habilitaDesabilitaQuadra(valor){
		var form = document.forms[0];
		if(valor){			
			form.quadra.value = "";
		}
		form.quadra.disabled = valor;
	
	}	

	/* Adciona a Faixa no grid */	
	function adcionarConsumo() {
		abrirPopup('emissaoHistogramaEsgotoEconomiaDadosInformarAction.do?acao=I', 300, 600);
	}
	
	/* Remove Faixa da grid */	
	function removerConsumo(id){
		
		var form = document.forms[0];
		
		var where_to = confirm("Deseja realmente remover esta faixa ?");
		
		if (where_to == true) {
		
		    form.action='emissaoHistogramaEsgotoEconomiaDadosInformarAction.do?acao=R&idRemover='+id;
		    form.submit();
 		}
	}

	
	/* Recuperar Popup */
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    
	    var form = document.forms[0];
	    
	    if (tipoConsulta == 'elo') {

			form.eloPolo.value = codigoRegistro;
		    form.nomeEloPolo.value = descricaoRegistro;
		    form.nomeEloPolo.style.color = "#000000";
	    
	    } else if (tipoConsulta == 'localidade') {

			form.localidade.value = codigoRegistro;
		    form.nomeLocalidade.value = descricaoRegistro;
		    form.nomeLocalidade.style.color = "#000000";
	    
	    } else if (tipoConsulta == 'setorComercial') {

	      	form.setorComercial.value = codigoRegistro;
	      	form.nomeSetorComercial.value = descricaoRegistro;
	      	form.nomeSetorComercial.style.color = "#000000";
	    
	    //Consumo Medido e Consumo Não Medido
	    } else if (tipoConsulta == 'consumoFaixa') {

	    	form.limiteInferiorFaixa.value = descricaoRegistro[0];
	    	form.limiteSuperiorFaixa.value = descricaoRegistro[1];

    		form.action='emissaoHistogramaEsgotoEconomiaDadosInformarAction.do?acao=A';
	    	form.submit();

	    }
	}
	
	/* Chama Popup */ 
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		
		if(objetoRelacionado.disabled != true){
			
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			} else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				} else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}
	
	function limparEloPolo() {
    	var form = document.forms[0];

      	form.eloPolo.value = "";
      	form.nomeEloPolo.value = "";
  	}

	function limparLocalidade() {
    	var form = document.forms[0];

      	form.localidade.value = "";
      	form.nomeLocalidade.value = "";
  	}
  	  	
	function limparSetorComercial() {
    	var form = document.forms[0];

      	form.setorComercial.value = "";
      	form.nomeSetorComercial.value = "";
      	
      	limparQuadra();
  	}
  	
  	function limparQuadra() {
      	
    	var form = document.forms[0];

      	form.quadra.value = "";
		var msgQuadra = document.getElementById("msgQuadra");
	
		if (msgQuadra != null){
			msgQuadra.innerHTML = "";
		}
  	}
  	
  	function reloadForm(){
  		var form = document.forms[0];

  		form.action='emissaoHistogramaEsgotoEconomiaDadosInformarAction.do';
	    form.submit();
  	}

  	function mudarTabelaConsumoFaixaCategoria(){
  		var form = document.forms[0];

  		form.action='emissaoHistogramaEsgotoEconomiaDadosInformarAction.do?acao=M';
	    form.submit();
  	}

	function pesquisarSubcategorias(){
  		var form = document.forms[0];

  		form.action='emissaoHistogramaEsgotoEconomiaDadosInformarAction.do?objetoConsulta=5';
	    form.submit();
  	}
  	
  	function limpar(){
  		var form = document.forms[0];
  		
  		limparEloPolo();
  		limparLocalidade();
  		limparSetorComercial();
  		limparQuadra();

  		form.gerenciaRegional.value = "-1";
  		form.unidadeNegocio.value = "-1";
  		form.tipoCategoria.value = "-1";
  		form.opcaoTotalizacao.value = "1";

  		form.action='emissaoHistogramaEsgotoEconomiaDadosInformarAction.do?menu=sim';
	    form.submit();
  	
  	}
  	
  	function trocarIndicadorTarifa(){
  		var form = document.forms[0];

  		form.action='emissaoHistogramaEsgotoEconomiaDadosInformarAction.do';
	    form.submit();	
	} 
  	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:habilitaDesabilitaFormulario(false);setarFoco('${requestScope.mesAnoFaturamento}');">

<html:form action="/gerarRelatorioHistogramaEsgotoEconomiaDadosInformarAction.do"
	name="EmissaoHistogramaEsgotoEconomiaDadosInformarActionForm"
	type="gcom.gui.faturamento.EmissaoHistogramaEsgotoEconomiaDadosInformarActionForm"
	method="post">

	<html:hidden property="limiteInferiorFaixa"/>
	<html:hidden property="limiteSuperiorFaixa"/>
	<html:hidden property="tamanhoColecao"/>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	  <tr>
	    <td width="130" valign="top" class="leftcoltext">
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
			<td width="600" valign="top" class="centercoltext">
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
					<td class="parabg">Informa&ccedil;&atilde;o de Dados para Emiss&atilde;o do Histograma de Esgoto Por Economia</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Para gerar o relat&oacute;rio, informe os dados abaixo:</td>
				</tr>
				
				<tr>
					<td><strong>Tipo Relatorio:</strong><font color="#FF0000">*</font></td>
					
					<td colspan="6">
	                	<strong> 
						  	<html:radio property="indicadorTarifaCategoria" value="1" onchange="trocarIndicadorTarifa();"/>Categoria
						  	<html:radio property="indicadorTarifaCategoria" value="2" onchange="trocarIndicadorTarifa();"/>Subcategoria
 						</strong> 
 					</td>
					
				</tr>
				
              	<tr> 
                	<td><strong>Mês/Ano do Faturamento:</strong><font color="#FF0000">*</font></td>
                	<td colspan="6">
                		<strong>
                  			<html:text property="mesAnoFaturamento" 
                  				size="7" 
                  				maxlength="7" 
                  				onkeyup="mascaraAnoMes(this, event);"/>
                  				&nbsp;mm/aaaa
                  		</strong>
                  	</td>
              	</tr>

				<tr>

					<td>
						<strong>Op&ccedil;&atilde;o de Totaliza&ccedil;&atilde;o:</strong>
						<font color="#FF0000">*</font>
					</td>

					<td>
						<strong> 
						<html:select property="opcaoTotalizacao" 
							style="width: 300px;"
							onchange="javascript:habilitaDesabilitaFormulario(true);" >

		                    <html:option value="1">ESTADO</html:option>
		                    <html:option value="2">ESTADO POR GER&Ecirc;NCIA REGIONAL</html:option>
		                    <html:option value="3">ESTADO POR UNIDADE DE NEG&Oacute;CIO</html:option>
		                    <html:option value="4">ESTADO POR ELO P&Oacute;LO</html:option>
		                    <html:option value="5">ESTADO POR LOCALIDADE</html:option>
		                    <html:option value="6">GER&Ecirc;NCIA REGIONAL</html:option>
		                    <html:option value="7">GER&Ecirc;NCIA POR UNIDADE DE NEG&Oacute;CIO</html:option>
	                     	<html:option value="8">GER&Ecirc;NCIA POR ELO P&Oacute;LO</html:option>
	                     	<html:option value="9">GER&Ecirc;NCIA POR LOCALIDADE</html:option>
		                    <html:option value="10">UNIDADE DE NEG&Oacute;CIO</html:option>
		                    <html:option value="11">UNIDADE DE NEG&Oacute;CIO POR ELO P&Oacute;LO</html:option>
		                    <html:option value="12">UNIDADE DE NEG&Oacute;CIO POR LOCALIDADE</html:option>
		                    <html:option value="13">ELO P&Oacute;LO</html:option>
		                    <html:option value="14">ELO P&Oacute;LO POR LOCALIDADE</html:option>
		                    <html:option value="15">ELO P&Oacute;LO POR SETOR COMERCIAL</html:option>
		                    <html:option value="16">LOCALIDADE</html:option>
		                    <html:option value="17">LOCALIDADE POR SETOR COMERCIAL</html:option>
							<logic:equal name="indicadorTarifaCategoria" value="1" >
			                    <html:option value="18">LOCALIDADE POR QUADRA</html:option>
			                </logic:equal>
		                    <html:option value="19">SETOR COMERCIAL</html:option>
		                    <logic:equal name="indicadorTarifaCategoria" value="1" >
		                    	<html:option value="20">SETOR COMERCIAL POR QUADRA</html:option>
		                    	<html:option value="21">QUADRA</html:option>
		                    </logic:equal>
					
						</html:select> 														
						</strong>
					</td>
				</tr>

				<tr>
					<td>
						<strong>Ger&ecirc;ncia Regional:</strong>
					</td>

					<td>
						<strong> 
						<html:select property="gerenciaRegional" 
							style="width: 230px;" 
							onchange="javascript:reloadForm();">
							
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoGerenciaRegional" scope="request">
								<html:options collection="colecaoGerenciaRegional"
									labelProperty="nome" 
									property="id" />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>Unidade de Neg&oacute;cio:</strong>
					</td>

					<td>
						<strong> 
						<html:select property="unidadeNegocio" style="width: 230px;">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoUnidadeNegocio" scope="request">
								<html:options collection="colecaoUnidadeNegocio"
									labelProperty="nome" 
									property="id" />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>				

				<tr>
					<td><strong>Localidade Pólo:</strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							tabindex="1"
							property="eloPolo" 
							size="3"
							onkeypress="validaEnterComMensagem(event, 'emissaoHistogramaEsgotoEconomiaDadosInformarAction.do?objetoConsulta=1','eloPolo','Elo Pólo');"/>
							
						<a href="javascript:chamarPopup('exibirPesquisarEloAction.do', 'eloPolo', null, null, 275, 480, '', document.forms[0].eloPolo);">
							<img width="23" 
								height="21" 
								border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Elo Pólo" /></a> 

						<logic:present name="eloPoloEncontrado" scope="request">
							<html:text property="nomeEloPolo" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="eloPoloEncontrado" scope="request">
							<html:text property="nomeEloPolo" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						
						<a href="javascript:limparEloPolo();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>

				<tr>
					<td><strong>Localidade:</strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							tabindex="1"
							property="localidade" 
							size="3"
							onkeypress="validaEnterComMensagem(event, 'emissaoHistogramaEsgotoEconomiaDadosInformarAction.do?objetoConsulta=2','localidade','Localidade');"/>
							
						<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade&indicadorUsoTodos=1', 'localidade', null, null, 275, 480, '', document.forms[0].localidade);limparQuadra();limparSetorComercial();">
						
							<img width="23" 
								height="21" 
								border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Localidade" /></a> 

						<logic:present name="localidadeEncontrada" scope="request">
							<html:text property="nomeLocalidade" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="localidadeEncontrada" scope="request">
							<html:text property="nomeLocalidade" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						
						<a href="javascript:limparLocalidade();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>

				<tr>
					<td><strong>Setor Comercial:</strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							tabindex="1"
							property="setorComercial" 
							size="3"
							onkeypress="validaEnterComMensagem(event, 'emissaoHistogramaEsgotoEconomiaDadosInformarAction.do?objetoConsulta=3','setorComercial','Setor Comercial');"/>
							
						<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].localidade.value+'&tipo=SetorComercial&indicadorUsoTodos=1', 'setorComercial', null, null, 275, 480, '', document.forms[0].setorComercial);limparQuadra();">
							<img width="23" 
								height="21" 
								border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Localidade" /></a> 

						<logic:present name="setorComercialEncontrado" scope="request">
							<html:text property="nomeSetorComercial" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="setorComercialEncontrado" scope="request">
							<html:text property="nomeSetorComercial" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
						
						<a href="javascript:limparSetorComercial();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
								
				<logic:equal name="indicadorTarifaCategoria" value="1" >								
					<tr>
						<td><strong>Quadra:</strong></td>
					
					<td>
							
							<html:text maxlength="4"
								property="quadra" 
								tabindex="3" 
								size="4"
								onkeypress="javascript:return validaEnterDependencia(event, 'emissaoHistogramaEsgotoEconomiaDadosInformarAction.do?objetoConsulta=4', this, document.forms[0].setorComercial.value, 'Setor Comercial');" />

							<logic:present name="msgQuadra" scope="request">
								<span style="color:#ff0000" id="msgQuadra">
									<bean:write scope="request" name="msgQuadra" />
								</span>
							</logic:present>
						</td>
					</tr>
				</logic:equal>
				
				<tr>
					<td>
						<strong>Tipo de Categoria:</strong>
					</td>

					<td>
						<strong> 
						<html:select property="tipoCategoria" 
							style="width: 230px;" 
							onchange="javascript:reloadForm();">
							
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoTipoCategoria" scope="request">
								<html:options collection="colecaoTipoCategoria"
									labelProperty="descricao" 
									property="id" />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>			
              	<tr> 
                	<td><strong>Categoria:</strong></td>
                	
                	<logic:equal name="indicadorTarifaCategoria" value="1" >
                		<td colspan="6">
               				<strong>
							<html:select property="categoria" 
								style="width: 350px; height:100px;" 
								multiple="true">
							
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							
								<logic:present name="colecaoCategoria" scope="request">
									<html:options collection="colecaoCategoria" 
										labelProperty="descricao" 
										property="id" />
								</logic:present>
							</html:select>                
                 			</strong>
	                  	</td>
                	</logic:equal>
                	
                	<logic:equal name="indicadorTarifaCategoria" value="2" >
					<td>
						<strong> 
						<html:select property="categoria" 
							style="width: 230px;" 
							onchange="javascript:pesquisarSubcategorias();">
							
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoCategoria" scope="request">
								<html:options collection="colecaoCategoria"
									labelProperty="descricao" 
									property="id" />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
					</logic:equal>
              	</tr>
              	<logic:equal name="indicadorTarifaCategoria" value="2" >
              		<tr> 
					<td><strong>Subcategoria:</strong></td>
						
                	<td colspan="6">
               			<strong>
						<html:select property="subcategoria" 
							style="width: 350px; height:100px;" 
							multiple="true">
							
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							
							<logic:present name="colecaoSubcategoria" scope="request">
								<html:options collection="colecaoSubcategoria" 
									labelProperty="descricao" 
									property="id" />
							</logic:present>
						</html:select>                
               			</strong>                 			
	                 </td>
              		</tr>	
				</logic:equal>
              
              	<tr> 
                	<td><strong>Tarifa:</strong></td>
                	
                	<td colspan="6">
               			<strong>
						<html:select property="tarifa" 
							style="width: 350px; height:100px;" 
							multiple="true">
							
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							
							<logic:present name="colecaoTarifa" scope="request">
								<html:options collection="colecaoTarifa" 
									labelProperty="descricao" 
									property="id" />
							</logic:present>
						</html:select>                
                 		</strong>
                 	 </td>
              	</tr>
              	
              	<tr> 
                	<td><strong>Perfil do Im&oacute;vel:</strong></td>
                	
                	<td colspan="6">
               			<strong>
						<html:select property="perfilImovel" 
							style="width: 350px; height:100px;" 
							multiple="true">
							
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							
							<logic:present name="colecaoPerfilImovel" scope="request">
								<html:options collection="colecaoPerfilImovel" 
									labelProperty="descricao" 
									property="id" />
							</logic:present>
						</html:select>                
                 		</strong>
                 	 </td>
              	</tr> 

              	<tr> 
                	<td><strong>Esfera de Poder:</strong></td>
                	
                	<td colspan="6">
               			<strong>
						<html:select property="esferaPoder" 
							style="width: 350px; height:100px;" 
							multiple="true">
							
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							
							<logic:present name="colecaoEsferaPoder" scope="request">
								<html:options collection="colecaoEsferaPoder" 
									labelProperty="descricao" 
									property="id" />
							</logic:present>
						</html:select>                
                 		</strong>
                 	 </td>
              	</tr> 
              	
              	<tr> 
                	<td><strong>Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o 
                    de Esgoto</strong></td>
                	
                	<td colspan="6">
               			<strong>
						<html:select property="situacaoLigacaoEsgoto" 
							style="width: 350px; height:100px;" 
							multiple="true">
							
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							
							<logic:present name="colecaoSituacaoLigacaoEsgoto" scope="request">
								<html:options collection="colecaoSituacaoLigacaoEsgoto" 
									labelProperty="descricao" 
									property="id" />
							</logic:present>
						</html:select>                
                 		</strong>
                 	 </td>
              	</tr>
              	
              	<tr> 
                	<td><strong>Percentual de Esgoto</strong></td>
                	
                	<td colspan="6">
               			<strong>
						<html:select property="perfilLigacaoEsgoto" 
							style="width: 350px; height:100px;" 
							multiple="true">
							
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							
							<logic:present name="colecaoPerfilLigacaoEsgoto" scope="request">
								<html:options collection="colecaoPerfilLigacaoEsgoto" 
									labelProperty="percentualEsgotoConsumidaColetada" 
									property="percentualEsgotoConsumidaColetada" />
							</logic:present>
						</html:select>                
                 		</strong>
                 	 </td>
              	</tr>               	              	
              	
           		
           		<tr>
             		<td height="10" colspan="3"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>

              	<tr> 
	                <td><strong>Consumo:</strong></td>
	                <td colspan="6">
	                	<strong> 
					  	<html:radio property="consumo" value=""/>Todos
					  	<html:radio property="consumo" value="1"/>Real
   					  	<html:radio property="consumo" value="2"/>Estimado
 						</strong> 
 					</td>
              	</tr>           		

              	<tr> 
	                <td><strong>Po&ccedil;o:</strong></td>
	                <td colspan="6">
	                	<strong> 
					  	<html:radio property="poco" value=""/>Todos
					  	<html:radio property="poco" value="1"/>Com Po&ccedil;o
   					  	<html:radio property="poco" value="2"/>Sem Po&ccedil;o
 						</strong> 
 					</td>
              	</tr>
              	
              	<tr> 
	                <td><strong>Volume Fixo de &Aacute;gua:</strong></td>
	                <td colspan="6">
	                	<strong> 
					  	<html:radio property="volumoFixoEsgoto" value=""/>Todos
					  	<html:radio property="volumoFixoEsgoto" value="C"/>Com Volume Fixo
   					  	<html:radio property="volumoFixoEsgoto" value="S"/>Sem Volume Fixo
 						</strong> 
 					</td>
              	</tr>
              	
           		<tr>
             		<td height="10" colspan="3"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>
           		
				<tr> 
 					<td>
 						<strong>
 							<font color="#000000">Faixas de Consumo por Categoria: </font>
						</strong>
 						
 					</td>
 					<td>
							<html:select property="tipoConsumoCategoria" 
								style="width: 120px;" 
								onchange="javascript:mudarTabelaConsumoFaixaCategoria();">
							
								<html:options collection="colecaoCategoria"
									labelProperty="descricao" 
									property="id" />
							</html:select> 														
 					
 					</td>
 					<td colspan="3" align="right">
 						<div align="right"> 
     						<input type="button" 
     							name="Submit24" 
     							class="bottonRightCol" 
     							value="Adicionar" 
     						    onclick="javascript:adcionarConsumo();">
   						</div>
   					</td>
				</tr>
				<tr> 
 					<td width="100%" colspan="4">
 						<div align="center">
 							<strong>
 								<font color="#FF0000"></font>
 							</strong> 
     						
     						<table width="100%" align="center" bgcolor="#99CCFF">
       						
								<tr bordercolor="#FFFFFF" bgcolor="#99CCFF">
                      				<td  width="39%" rowspan="2">
                      					<div align="center" class="style9"><strong>Remover</strong></div>
                      				</td>
                      				<td colspan="2">
                      					<div align="center" class="style9">
                      						<strong>Faixa de Consumo</strong>
                      					</div>
                        			</td>
                    			</tr>
			                    
			                    <tr bordercolor="#FFFFFF" bgcolor="#99CCFF">
			                    	<td><div align="center"><strong>Limite Inferior </strong></div></td>
			                      	<td><div align="center"><strong>Limite Superior </strong></div></td>
			                    </tr>
			                    					            
		                      	<c:set var="count" value="0"/>
                      		  		
								<logic:iterate id="consumoFaixaCategoria" 
									name="EmissaoHistogramaEsgotoEconomiaDadosInformarActionForm" 
									property="colecaoConsumoFaixaCategoria" 
									type="gcom.faturamento.ConsumoFaixaCategoria" 
									scope="session">
                      		  		
                      		  		<c:set var="count" value="${count+1}"/>
	                        		
	                        		<c:choose>
	                        			<c:when test="${count%2 == '1'}">
	                        				<tr bgcolor="#FFFFFF">
	                        			</c:when>
	                        			<c:otherwise>
	                        				<tr bgcolor="#cbe5fe">
	                        			</c:otherwise>
				                  	</c:choose>

			                    	<td bordercolor="#90c7fc">
			                        	<div align="center">
			                        		<img src="<bean:message key='caminho.imagens'/>Error.gif" 
			                        			width="14" 
			                        			height="14" 
										 		style="cursor:hand;" 
										 		name="imagem"	
										 		onclick="removerConsumo('${count}');"
										 		alt="Remover">
										</div>
									</td>

			                        <td bordercolor="#90c7fc">
			                        	<div align="center">
			                        		<c:if test="${consumoFaixaCategoria.numeroFaixaInicio != null}">
			                        			<bean:write name="consumoFaixaCategoria" 
			                        				property="numeroFaixaInicio" />
			                        		</c:if>
			                        	</div>
			                        </td>
			                        
			                        <td bordercolor="#90c7fc">
			                        	<div align="center">
			                        		<c:if test="${consumoFaixaCategoria.numeroFaixaFim != null}">
			                        			<bean:write name="consumoFaixaCategoria" 
			                        				property="numeroFaixaFim" />
			                        		</c:if>
			                        	</div>
			                        </td>			                        
					

					                      		
					            </logic:iterate>	
				            </table>
                		</div>
                	</td>
              	</tr>
		           		
           		<tr>
             		<td height="10" colspan="3"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>
           		
				

				<tr>
					<td height="24" >
			          	<input type="button" 
			          		class="bottonRightCol" 
			          		value="Limpar" 
			          		onclick="javascript:limpar();"/>
					</td>
					
					<td align="right">
						<input type="button" 
							name="Button" 
							class="bottonRightCol" 
							value="Emitir Histograma de Esgoto por Economia" 
							onClick="javascript:validarForm()" />
					</td>
					
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
<%@ include file="/jsp/util/rodape.jsp"%>	
</html:form>
</body>
</html:html>
