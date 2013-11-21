<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.atendimentopublico.ordemservico.OrdemServico"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarOrdemServicoActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript">

	function validarForm(){
   		var form = document.forms[0];

    	if(validatePesquisarOrdemServicoActionForm(form)){
			if(validaCampos()){
				enviarSelectMultiplo('PesquisarOrdemServicoActionForm','tipoServicoSelecionados');
	   			form.submit();
			}
	  	}
    }
    function validaCampos() {

		var form = document.forms[0];

    	if (comparaData(form.periodoAtendimentoInicial.value, '>', form.periodoAtendimentoFinal.value)){

			alert('Data Final do Período de Atendimento é anterior à Data Inicial do Período de Atendimento');
			return false;

		} else if (comparaData(form.periodoGeracaoInicial.value, '>', form.periodoGeracaoFinal.value)){

			alert('Data Final do Período da Geração é anterior à Data Inicial do Período da Geração');
			return false;

		} else if (comparaData(form.periodoEncerramentoInicial.value, '>', form.periodoEncerramentoFinal.value)){

			alert('Data Final do Período de Encerramento é anterior à Data Inicial do Período de Encerramento');
			return false;

		} else if (comparaData(form.periodoProgramacaoInicial.value, '>', form.periodoProgramacaoFinal.value)){

			alert('Data Final do Período de Programação é anterior à Data Inicial do Período de Programação');
			return false;

		} else if(form.bairro.value != '' && form.municipio.value == '')   { 

			alert('Informe o Município');
			return false;
		}

		return true;
    }
    
   	function verificarMunicipio(){

    	var form = document.forms[0];
    
	    if(form.municipio.value == '')   { 
			alert('Informe o Município');
			}else{
			redirecionarSubmit('exibirPesquisarBairroAction.do?caminhoRetornoTelaPesquisaBairro=exibirPesquisarOrdemServicoAction&tipo=1&idMunicipio='+document.forms[0].municipio.value);
			}
    }

	function limparForm(){

		var form = document.forms[0];

		form.periodoAtendimentoInicial.value="";
		form.periodoAtendimentoFinal.value="";
		form.periodoGeracaoInicial.value="";
		form.periodoGeracaoFinal.value="";

		limparPesquisaRA();
		limparDocumentoCobranca();
		limparImovel();
		limparCliente();
		limparUnidadeGeracao();
		limparUnidadeAtual();
		limparUnidadeSuperior();
		limparPeriodoEncerramento();
		limparPeriodoProgramacao();
		limparMunicipio();
		limparBairro();
		limparLogradouro();
		
		form.situacaoOrdemServico.selectedIndex = 0;
		form.areaBairro.selectedIndex = 0;

		/*form.situacaoProgramacao[0].checked = false;
		form.situacaoProgramacao[1].checked = false;
		form.situacaoProgramacao[2].checked = false;*/		

		MoverTodosDadosSelectMenu2PARAMenu1('PesquisarOrdemServicoActionForm', 'tipoServico', 'tipoServicoSelecionados');		
	}

	function limparPesquisaRA() {

    	var form = document.forms[0];

    	form.numeroRA.value = "";
    	form.descricaoRA.value = "";
    	
    	form.documentoCobranca.disabled = false;
  		form.descricaoDocumentoCobranca.disabled = false;
    	
  	}

	function limparDocumentoCobranca() {

    	var form = document.forms[0];

    	form.documentoCobranca.value = "";
    	form.descricaoDocumentoCobranca.value = "";

    	form.numeroRA.disabled = false;
  		form.descricaoRA.disabled = false;

  	}

	function limparImovel() {

    	var form = document.forms[0];

    	form.matriculaImovel.value = "";
    	form.inscricaoImovel.value = "";
  	}

	function limparCliente() {

    	var form = document.forms[0];

    	form.codigoCliente.value = "";
    	form.nomeCliente.value = "";
  	}

    function limparUnidadeGeracao() {
        var form = document.forms[0];

        form.unidadeGeracao.value = "";
    	form.descricaoUnidadeGeracao.value = "";
    }

    function limparUnidadeAtual() {
        var form = document.forms[0];

        form.unidadeAtual.value = "";
    	form.descricaoUnidadeAtual.value = "";
    }

    function limparUnidadeSuperior() {
        var form = document.forms[0];

        form.unidadeSuperior.value = "";
    	form.descricaoUnidadeSuperior.value = "";
    }
  
    function limparMunicipio() {
        var form = document.forms[0];

        form.municipio.value = "";
    	form.descricaoMunicipio.value = "";
    	limparBairro();
    }

    function limparBairro() {
        var form = document.forms[0];

        form.bairro.value = "";
    	form.descricaoBairro.value = "";
    }
    
    function limparLogradouro() {
        var form = document.forms[0];

        form.logradouro.value = "";
    	form.descricaoLogradouro.value = "";
    }

    function limparPeriodoEncerramento() {

        var form = document.forms[0];
        
		form.periodoEncerramentoInicial.disabled = false;
		form.periodoEncerramentoFinal.disabled = false;
		
		form.periodoEncerramentoInicial.value="";
		form.periodoEncerramentoFinal.value="";
  	}
    
    function limparPeriodoProgramacao() {

        var form = document.forms[0];
        
		form.periodoProgramacaoInicial.disabled = false;
		form.periodoProgramacaoFinal.disabled = false;
		
		form.periodoProgramacaoInicial.value="";
		form.periodoProgramacaoFinal.value="";
  	}
    /* Recuperar Popup */
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
	    
	    if (tipoConsulta == 'registroAtendimento') {

	      if(!form.numeroRA.disabled){
		      form.numeroRA.value = codigoRegistro;
		      form.descricaoRA.value = descricaoRegistro;
		      form.descricaoRA.style.color = "#000000";
		      reload();
	      }
	    
	    } else if (tipoConsulta == 'documentoCobranca') {

	      if(!form.documentoCobranca.disabled){
		      form.documentoCobranca.value = codigoRegistro;
		      form.descricaoDocumentoCobranca.value = descricaoRegistro;
		      form.descricaoDocumentoCobranca.style.color = "#000000";
		      reload();
	      }
	    
	    } else if (tipoConsulta == 'imovel') {

	      form.matriculaImovel.value = codigoRegistro;
	      form.inscricaoImovel.value = descricaoRegistro;
	      form.inscricaoImovel.style.color = "#000000";
	    
	    } else if (tipoConsulta == 'cliente') {

		    form.codigoCliente.value = codigoRegistro;
		    form.nomeCliente.value = descricaoRegistro;
	      	form.nomeCliente.style.color = "#000000";
	    
   	    } else if (tipoConsulta == 'unidadeSuperior') {

	      	form.unidadeSuperior.value = codigoRegistro;
	      	form.descricaoUnidadeSuperior.value = descricaoRegistro;
			form.descricaoUnidadeSuperior.style.color = "#000000";

	    } else if (tipoConsulta == 'unidadeOrganizacional') {
	      
	   		if (unidade == 1) {

		    	form.unidadeGeracao.value = codigoRegistro;
		      	form.descricaoUnidadeGeracao.value = descricaoRegistro;
	      		form.descricaoUnidadeGeracao.style.color = "#000000";

	      	} else if (unidade == 2) {

		      	form.unidadeAtual.value = codigoRegistro;
		      	form.descricaoUnidadeAtual.value = descricaoRegistro;
      			form.descricaoUnidadeAtual.style.color = "#000000";

	      	}
	      	unidade = 0;
	    
	    } else if (tipoConsulta == 'municipio') {
		    form.municipio.value = codigoRegistro;
		    form.descricaoMunicipio.value = descricaoRegistro;
   			form.descricaoMunicipio.style.color = "#000000";
	    
	    } else if (tipoConsulta == 'bairro') {
		    form.codigoBairro.value = codigoRegistro;
		    form.descricaoBairro.value = descricaoRegistro;
   			form.descricaoBairro.style.color = "#000000";
	    
	    } else if (tipoConsulta == 'logradouro') {
  	      	form.logradouro.value = codigoRegistro;
	      	form.descricaoLogradouro.value = descricaoRegistro;
   			form.descricaoLogradouro.style.color = "#000000";
	    }
	}
	
    
	//Replica Data de Atendimento
	function replicaDataAtendimento() {
		var form = document.forms[0];
		form.periodoAtendimentoFinal.value = form.periodoAtendimentoInicial.value;
	}

	//Replica Data de Geracao
	function replicaDataGeracao() {
		var form = document.forms[0];
		form.periodoGeracaoFinal.value = form.periodoGeracaoInicial.value;
	}
    
	//Replica Data de Programação
	function replicaDataProgramacao() {
		var form = document.forms[0];
		form.periodoProgramacaoFinal.value = form.periodoProgramacaoInicial.value;
	}

	//Replica Data de Encerramento
	function replicaDataEncerramento() {
		var form = document.forms[0];
		form.periodoEncerramentoFinal.value = form.periodoEncerramentoInicial.value;
	}

	function validaForm(){
		var form = document.forms[0];
		
		if(form.numeroRA.value != null && form.numeroRA.value != ''){

			limparDocumentoCobranca();

   	    	form.documentoCobranca.disabled = true;
    		form.descricaoDocumentoCobranca.disabled = true;

		}else if(form.documentoCobranca.value != null && form.documentoCobranca.value != ''){

			limparPesquisaRA();
			
   	    	form.numeroRA.disabled = true;
    		form.descricaoRA.disabled = true;
   		
		}else{
   	    	form.documentoCobranca.disabled = false;
    		form.descricaoDocumentoCobranca.disabled = false;
   	    	form.numeroRA.disabled = false;
    		form.descricaoRA.disabled = false;
		}
		
		if(	form.situacaoOrdemServico.value == '1' || 
			form.situacaoOrdemServico.value == '3' ||
			form.situacaoOrdemServico.value == '4' ){

			limparPeriodoEncerramento();
			
			form.periodoEncerramentoInicial.disabled = true;
			form.periodoEncerramentoFinal.disabled = true;
		
		}else{

			form.periodoEncerramentoInicial.disabled = false;
			form.periodoEncerramentoFinal.disabled = false;
		
		}

		/*if(form.situacaoProgramacao[2].checked){
			
			limparPeriodoProgramacao();
			
			form.periodoProgramacaoInicial.disabled = true;
			form.periodoProgramacaoFinal.disabled = true;
		}else{

			form.periodoProgramacaoInicial.disabled = false;
			form.periodoProgramacaoFinal.disabled = false;
		}*/
		
		
	}
	
	function chamarForm(caminhoAction,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			var form = document.forms[0];
	   		form.action = caminhoAction;
	   		form.submit();
		}
	}
	
	//So chama a função abrirCalendario caso o campo esteja habilitado
	function chamarCalendario(fieldNameOrigem,objetoRelacionado,fieldNameDestino){
		
		if(objetoRelacionado.disabled != true){
			if(fieldNameDestino != ''){
				abrirCalendarioReplicando('PesquisarOrdemServicoActionForm', fieldNameOrigem,fieldNameDestino);
			}else{
				abrirCalendario('PesquisarOrdemServicoActionForm', fieldNameOrigem);
			}
		}
	}
	
	
	function limparPeriodoGeracao() {
        var form = document.forms[0];
        
        if (form.periodoGeracaoInicial.value != ''){
			form.periodoGeracaoInicial.value="";
		}
		
		if (form.periodoGeracaoFinal.value != ''){
			form.periodoGeracaoFinal.value="";
		}			
  	}
  	
  	function habilitaRA(){
	  	var form = document.forms[0];
	
		form.numeroRA.readOnly = false;
   		form.descricaoRA.readOnly = false;
	}
	
	function desabilitaRA(){
	  	var form = document.forms[0];
	
		form.numeroRA.value = "";
    	form.descricaoRA.value = "";
		form.numeroRA.readOnly = true;
   		form.descricaoRA.readOnly = true;
	}
	
	function habilitaDocumentoCobranca(){
	  	var form = document.forms[0];
	
		form.documentoCobranca.readOnly = false;
   		form.descricaoDocumentoCobranca.readOnly = false;
	}
	
	function desabilitaDocumentoCobranca(){
	  	var form = document.forms[0];
	
		form.documentoCobranca.value = "";
    	form.descricaoDocumentoCobranca.value = "";
		form.documentoCobranca.readOnly = true;
   		form.descricaoDocumentoCobranca.readOnly = true;
	}
	
	function habilitaPeriodoAtendimento(){
	  	var form = document.forms[0];
	
		form.periodoAtendimentoInicial.readOnly = false;
		form.periodoAtendimentoFinal.readOnly = false;
	}
	
	function desabilitaPeriodoAtendimento(){
	  	var form = document.forms[0];
	
		form.periodoAtendimentoInicial.value = "";
    	form.periodoAtendimentoFinal.value = "";
		form.periodoAtendimentoInicial.readOnly = true;
		form.periodoAtendimentoFinal.readOnly= true;

	}
	
	function habilitaAreaBairro(){
	  	var form = document.forms[0];
	
		form.areaBairro.readOnly = false;
	}
	
	function desabilitaAreaBairro(){
	  	var form = document.forms[0];
	  	
		form.areaBairro.selectedIndex = 0;
		form.areaBairro.readOnly = true;
	}
  	
  	function origemOrdemServicoInformado(){
	 	var form = document.forms[0];
	 	
		if (form.origemOrdemServico[0].checked){
			habilitaRA();
			desabilitaDocumentoCobranca();
			habilitaPeriodoAtendimento();
			habilitaAreaBairro();
		}else if (form.origemOrdemServico[1].checked){
			desabilitaRA();
			habilitaDocumentoCobranca();
			desabilitaPeriodoAtendimento();
			desabilitaAreaBairro();
		}else if (form.origemOrdemServico[2].checked){
			desabilitaRA();
			desabilitaDocumentoCobranca();
			desabilitaPeriodoAtendimento();
			desabilitaAreaBairro();
		}else if (form.origemOrdemServico[3].checked){
			habilitaRA();
			habilitaDocumentoCobranca();
			desabilitaPeriodoAtendimento();
			desabilitaAreaBairro();
		}else{
			form.origemOrdemServico[0].checked = true;
			habilitaRA();
			desabilitaDocumentoCobranca();
			habilitaPeriodoAtendimento();
			habilitaAreaBairro();
		}
	}
  	
</script>

</head>

<body leftmargin="0" topmargin="0"
	onload="javascript:validaForm();window.focus();resizePageSemLink(650, 710);setarFoco('${requestScope.nomeCampo}');origemOrdemServicoInformado();">

<html:form action="/pesquisarOrdemServicoAction" method="post">
	
	<table width="600" border="0" cellspacing="5" cellpadding="0">
		<tr>
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
					<td class="parabg">Pesquisar Ordem de Servi&ccedil;o</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Preencha os campos para pesquisar ordens de servi&ccedil;o:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}atendimentoOrdemServicoPesquisar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>	
				</tr>
			</table>
				
			<table width="100%" border="0">
			
				<tr>
		          <td>
		            <strong>Origem da OS:</strong>
		          </td>
		          <td>
		            <html:radio property="origemOrdemServico" value="<%= OrdemServico.SOLICITADAS %>" onclick ="origemOrdemServicoInformado();"><strong>Solicitada</strong></html:radio>
		            <html:radio property="origemOrdemServico" value="<%= OrdemServico.SELETIVAS_COBRANCA %>" onclick ="origemOrdemServicoInformado();"><strong>Seletiva de Cobrança</strong></html:radio>
		            <html:radio property="origemOrdemServico" value="<%= OrdemServico.SELETIVAS_HIDROMETRO %>" onclick ="origemOrdemServicoInformado();"><strong>Seletiva de Hidrômetro</strong></html:radio>
		            <html:radio property="origemOrdemServico" value="<%= OrdemServico.TODAS %>" onclick ="origemOrdemServicoInformado();"><strong>Todas</strong></html:radio>
		          </td>
		        </tr>

				<tr>
					<td><strong>N&uacute;mero do RA:</strong></td>
					
					<td>
						
						<html:text maxlength="9" 
							tabindex="1"
							property="numeroRA" 
							size="9"
							onkeyup="validaForm(); limparPeriodoGeracao();" 
							onkeydown="validaForm();"
							onkeypress="javascript:validaEnterComMensagem(event, 'exibirPesquisarOrdemServicoAction.do?objetoConsultaOs=1','numeroRA','Número do RA');return isCampoNumerico(event);"/>
							
							<a href="javascript:chamarForm('exibirPesquisarRegistroAtendimentoAction.do?menu=sim&caminhoRetornoTelaPesquisaRegistroAtendimento=exibirPesquisarOrdemServicoAction',document.forms[0].numeroRA);">

								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar RA" /></a> 

							<logic:present name="numeroRAEncontrada" scope="request">
								
								<html:text property="descricaoRA" 
									size="30"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
								
							</logic:present> 

							<logic:notPresent name="numeroRAEncontrada" scope="request">
								
								<html:text property="descricaoRA" 
									size="30"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />

							</logic:notPresent>

							
							<a href="javascript:limparPesquisaRA();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" />
							</a>					
						</td>
				</tr>

				<tr>
					<td><strong>Documento de Cobran&ccedil;a:</strong></td>
					
					<td>
						
						<html:text maxlength="9" 
							tabindex="1"
							property="documentoCobranca" 
							size="9"
							onkeyup="validaForm();" 
							onkeydown="validaForm();"
							onkeypress="javascript:validaEnterComMensagem(event, 'exibirPesquisarOrdemServicoAction.do?objetoConsultaOs=2','documentoCobranca','Documento de Cobrança');return isCampoNumerico(event);"/>
							
							<a href="javascript:chamarForm('exibirFiltrarDocumentosCobrancaAction.do?menu=sim&ehPopup=true&caminhoRetornoTelaPesquisaDocumentoCobranca=exibirPesquisarOrdemServicoAction',document.forms[0].documentoCobranca);">
								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Documento Cobrança" /></a>

							<logic:present name="documentoCobrancaEncontrada" scope="request">
								
								<html:text property="descricaoDocumentoCobranca" 
									size="30"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
								
							</logic:present> 

							<logic:notPresent name="documentoCobrancaEncontrada" scope="request">
								
								<html:text property="descricaoDocumentoCobranca" 
									size="30"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />

							</logic:notPresent>

							<a href="javascript:limparDocumentoCobranca();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" />
							</a>					
							
						</td>
				</tr>

           		<tr>
             		<td height="10" colspan="4"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>

				<tr>

					<td>
						<strong>Situa&ccedil;&atilde;o da Ordem de Servi&ccedil;o:</strong>
					</td>

					<td>
						<strong> 
						<html:select property="situacaoOrdemServico" style="width: 300px;" onchange="javascript:validaForm();">

							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>

							<html:option
								value="<%=""+OrdemServico.SITUACAO_PENDENTE%>">PENDENTES
							</html:option>

							<html:option
								value="<%=""+OrdemServico.SITUACAO_ENCERRADO%>">ENCERRADOS
							</html:option>

							<html:option
								value="<%=""+OrdemServico.SITUACAO_EXECUCAO_EM_ANDAMENTO%>">EXECU&Ccedil;&Atilde;O EM ANDAMENTO
							</html:option>

							<html:option
								value="<%=""+OrdemServico.SITUACAO_AGUARDANDO_LIBERACAO%>">AGUARDANDO LIBERA&Ccedil;&Atilde;O PARA EXECU&Ccedil;&Atilde;O
							</html:option>
						
						</html:select> 														
						</strong>
					</td>
				</tr>

				<tr>
					<td width="120">
						<strong>Tipo de Servi&ccedil;o:</strong>
					</td>
					<td colspan="2">
					<table width="100%" border=0 cellpadding=0 cellspacing=0 align="left">
						<tr>
							<td width="200">
							
								<div align="left"><strong>Disponíveis</strong></div>

								<html:select property="tipoServico" size="6" multiple="true" style="width:230px">
									<html:options collection="colecaoTipoServico" labelProperty="descricao" property="id" />
								</html:select>
							</td>

							<td width="5" valign="center"><br>
								<table width="50" align="center">
									<tr>
										<td align="center"><input type="button" class="bottonRightCol"
											onclick="javascript:MoverTodosDadosSelectMenu1PARAMenu2('PesquisarOrdemServicoActionForm', 'tipoServico', 'tipoServicoSelecionados');"
											value=" &gt;&gt; "></td>
									</tr>
	
									<tr>
										<td align="center"><input type="button" class="bottonRightCol"
											onclick="javascript:MoverDadosSelectMenu1PARAMenu2('PesquisarOrdemServicoActionForm', 'tipoServico', 'tipoServicoSelecionados');"
											value=" &nbsp;&gt;  "></td>
									</tr>
	
									<tr>
										<td align="center"><input type="button" class="bottonRightCol"
											onclick="javascript:MoverDadosSelectMenu2PARAMenu1('PesquisarOrdemServicoActionForm', 'tipoServico', 'tipoServicoSelecionados');"
											value=" &nbsp;&lt;  "></td>
									</tr>
	
									<tr>
										<td align="center"><input type="button" class="bottonRightCol"
											onclick="javascript:MoverTodosDadosSelectMenu2PARAMenu1('PesquisarOrdemServicoActionForm', 'tipoServico', 'tipoServicoSelecionados');"
											value=" &lt;&lt; "></td>
									</tr>
								</table>
							</td>

							<td>
								<div align="left"><strong>Selecionados</strong></div>
								<html:select property="tipoServicoSelecionados" size="6" multiple="true" style="width:200px">
								</html:select>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				
				<logic:present name="colecaoProjeto">
				
					<tr>
		          		<td>
		            		<strong>Projeto:</strong>
		          		</td>
		          		<td>
		            
		            		<html:select property="projeto" style="width: 230px;">
							
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
								</html:option>
					
								<html:options collection="colecaoProjeto" labelProperty="nome"  property="id" />
							
							</html:select> 														
						
		          		</td>
		        	</tr>
		        
				</logic:present>

           		<tr>
             		<td height="10" colspan="4"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>

				<tr>
					<td><strong>Matr&iacute;cula do Im&oacute;vel:</strong></td>
					
					<td>
						
						<html:text maxlength="9" 
							tabindex="1"
							property="matriculaImovel" 
							size="9"
							onkeypress="validaEnterComMensagem(event, 'exibirPesquisarOrdemServicoAction.do?objetoConsultaOs=3','matriculaImovel','Matrícula do Imóvel');return isCampoNumerico(event);"/>
							
							<a href="javascript:redirecionarSubmit('exibirPesquisarImovelAction.do?caminhoRetornoTelaPesquisaImovel=exibirPesquisarOrdemServicoAction');">

								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Matricula Imóvel" /></a> 

							<logic:present name="matriculaImovelEncontrada" scope="request">
								
								<html:text property="inscricaoImovel" 
									size="30"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
								
							</logic:present> 

							<logic:notPresent name="matriculaImovelEncontrada" scope="request">
								
								<html:text property="inscricaoImovel" 
									size="30"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />

							</logic:notPresent>

							
							<a href="javascript:limparImovel();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" />
							</a>					
						</td>
				</tr>
				
				<tr>
					<td><strong>C&oacute;digo do Cliente:</strong></td>
					
					<td>
						
						<html:text maxlength="9" 
							tabindex="1"
							property="codigoCliente" 
							size="9"
							onkeypress="validaEnterComMensagem(event, 'exibirPesquisarOrdemServicoAction.do?objetoConsultaOs=4','codigoCliente','Código do Cliente');return isCampoNumerico(event);"/>
							
							<a href="javascript:redirecionarSubmit('exibirPesquisarClienteAction.do?caminhoRetornoTelaPesquisaCliente=exibirPesquisarOrdemServicoAction');">

								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Código do Cliente" /></a> 

							<logic:present name="codigoClienteEncontrada" scope="request">
								
								<html:text property="nomeCliente" 
									size="45"
									maxlength="45" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
								
							</logic:present> 

							<logic:notPresent name="codigoClienteEncontrada" scope="request">
								
								<html:text property="nomeCliente" 
									size="45"
									maxlength="45" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />

							</logic:notPresent>

							
							<a href="javascript:limparCliente();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" />
							</a>					
						</td>
				</tr>

           		<tr>
             		<td height="10" colspan="4"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>

				<tr>
					<td><strong>Unidade de Gera&ccedil;&atilde;o:</strong></td>
					<td>
						<html:text maxlength="4" 
							tabindex="1"
							property="unidadeGeracao" 
							size="4"
							onkeypress="validaEnterComMensagem(event, 'exibirPesquisarOrdemServicoAction.do?objetoConsultaOs=5','unidadeGeracao','Unidade de Geração');return isCampoNumerico(event);"/> 
							
							<a href="javascript:redirecionarSubmit('exibirPesquisarUnidadeOrganizacionalAction.do?caminhoRetornoTelaPesquisaUnidadeOrganizacional=exibirPesquisarOrdemServicoAction&tipoUnidade=unidadeGeracao');">

								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Unidade Geração" /></a> 

						<logic:present name="unidadeGeracaoEncontrada" scope="request">
							
							<html:text property="descricaoUnidadeGeracao" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
							
						</logic:present> 

						<logic:notPresent name="unidadeGeracaoEncontrada" scope="request">
							
							<html:text property="descricaoUnidadeGeracao" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />

						</logic:notPresent>

						<a href="javascript:limparUnidadeGeracao();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" />
						</a>
					
					</td>
				</tr>

				<tr>
					<td><strong>Unidade Atual:</strong></td>
					<td>
						<html:text maxlength="4" 
							tabindex="1"
							property="unidadeAtual" 
							size="4"
							onkeypress="validaEnterComMensagem(event, 'exibirPesquisarOrdemServicoAction.do?objetoConsultaOs=6','unidadeAtual','Unidade Atual');return isCampoNumerico(event);"/> 
							
							<a href="javascript:redirecionarSubmit('exibirPesquisarUnidadeOrganizacionalAction.do?caminhoRetornoTelaPesquisaUnidadeOrganizacional=exibirPesquisarOrdemServicoAction&tipoUnidade=unidadeAtual');">

								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Unidade Atual" /></a> 

						<logic:present name="unidadeAtualEncontrada" scope="request">
							
							<html:text property="descricaoUnidadeAtual" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
							
						</logic:present> 

						<logic:notPresent name="unidadeAtualEncontrada" scope="request">
							
							<html:text property="descricaoUnidadeAtual" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />

						</logic:notPresent>

						<a href="javascript:limparUnidadeAtual();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" />
						</a>
					
					</td>
				</tr>

           		<tr>
             		<td height="10" colspan="4"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>

              	<tr>
	                <td>
	                	<strong>Per&iacute;odo de Atendimento:</strong>
	                </td>
                
	                <td colspan="6">
	                	<span class="style2">
	                	
	                	<strong> 
							
							<html:text property="periodoAtendimentoInicial" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event);replicaDataAtendimento();"
								onkeypress="return isCampoNumerico(event);"/>
							
							<a href="javascript:abrirCalendarioReplicando('PesquisarOrdemServicoActionForm', 'periodoAtendimentoInicial','periodoAtendimentoFinal');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15" 
									border="0" 
									alt="Exibir Calendário" 
									tabindex="4"/></a> a 
							
							<html:text property="periodoAtendimentoFinal" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event)"
								onkeypress="return isCampoNumerico(event);"/>
								
							<a href="javascript:abrirCalendario('PesquisarOrdemServicoActionForm', 'periodoAtendimentoFinal');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15" 
									border="0" 
									alt="Exibir Calendário" 
									tabindex="4"/></a>
							
							</strong>(dd/mm/aaaa)<strong> 
	                  	</strong>
	                  	</span>
	              	</td>
              	</tr>

              	<tr>
	                <td>
	                	<strong>Per&iacute;odo de Gera&ccedil;&atilde;o:</strong>
	                </td>
                
	                <td colspan="6">
	                	<span class="style2">
	                	
	                	<strong> 
							
							<html:text property="periodoGeracaoInicial" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event);replicaDataGeracao();"
								onkeypress="return isCampoNumerico(event);"/>
							
							<a href="javascript:abrirCalendarioReplicando('PesquisarOrdemServicoActionForm', 'periodoGeracaoInicial','periodoGeracaoFinal');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15" 
									border="0" 
									alt="Exibir Calendário" 
									tabindex="4"/></a> a 
							
							<html:text property="periodoGeracaoFinal" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event)"
								onkeypress="return isCampoNumerico(event);"/>
							
							<a href="javascript:abrirCalendario('PesquisarOrdemServicoActionForm', 'periodoGeracaoFinal');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15" 
									border="0" 
									alt="Exibir Calendário" 
									tabindex="4"/></a>
							
							</strong>(dd/mm/aaaa)<strong> 
	                  	</strong>
	                  	</span>
	              	</td>
              	</tr>

              	<tr>
	                <td>
	                	<strong>Per&iacute;odo de Programa&ccedil;&atilde;o:</strong>
	                </td>
                
	                <td colspan="6">
	                	<span class="style2">
	                	
	                	<strong> 
							
							<html:text property="periodoProgramacaoInicial" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event);replicaDataProgramacao();"
								onkeypress="return isCampoNumerico(event);"/>
							
							<a href="javascript:chamarCalendario('periodoProgramacaoInicial',document.forms[0].periodoProgramacaoInicial,'periodoProgramacaoFinal');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15" 
									border="0" 
									alt="Exibir Calendário" 
									tabindex="4"/></a> a 
							
							<html:text property="periodoProgramacaoFinal" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event)"
								onkeypress="return isCampoNumerico(event);"/>
								
							<a href="javascript:chamarCalendario('periodoProgramacaoFinal',document.forms[0].periodoProgramacaoFinal,'');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15" 
									border="0" 
									alt="Exibir Calendário" 
									tabindex="4"/></a>
							
							</strong>(dd/mm/aaaa)<strong> 
	                  	</strong>
	                  	</span>
	              	</td>
              	</tr>

              	<tr>
	                <td>
	                	<strong>Per&iacute;odo de Encerramento:</strong>
	                </td>
                
	                <td colspan="6">
	                	<span class="style2">
	                	
	                	<strong> 
							
							<html:text property="periodoEncerramentoInicial" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event);replicaDataEncerramento();"
								onkeypress="return isCampoNumerico(event);"/>
							
							<a href="javascript:chamarCalendario('periodoEncerramentoInicial',document.forms[0].periodoEncerramentoInicial,'periodoEncerramentoFinal');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15" 
									border="0" 
									alt="Exibir Calendário" 
									tabindex="4"/></a> a 
							
							<html:text property="periodoEncerramentoFinal" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event)"
								onkeypress="return isCampoNumerico(event);"/>
							
							<a href="javascript:chamarCalendario('periodoEncerramentoFinal',document.forms[0].periodoEncerramentoFinal,'');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15" 
									border="0" 
									alt="Exibir Calendário" 
									tabindex="4"/></a>
							
							</strong>(dd/mm/aaaa)<strong> 
	                  	</strong>
	                  	</span>
	              	</td>
              	</tr>

           		<tr>
             		<td height="10" colspan="4"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>

				<tr>
					<td><strong>Munic&iacute;pio:</strong></td>
					<td>
						<html:text maxlength="4" 
							tabindex="1"
							property="municipio" 
							size="4"
							onkeypress="validaEnterComMensagem(event, 'exibirPesquisarOrdemServicoAction.do?objetoConsultaOs=8','municipio','Município');return isCampoNumerico(event);"/> 
							
						<a href="javascript:redirecionarSubmit('exibirPesquisarMunicipioAction.do?caminhoRetornoTelaPesquisaMunicipio=exibirPesquisarOrdemServicoAction');">

							<img width="23" 
								height="21" 
								border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Município" /></a> 

						<logic:present name="municipioEncontrada" scope="request">
						
							<html:text property="descricaoMunicipio" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						
						</logic:present> 

						<logic:notPresent name="municipioEncontrada" scope="request">
						
							<html:text property="descricaoMunicipio" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />

						</logic:notPresent>

						<a href="javascript:limparMunicipio();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" />
						</a>
					
					</td>
				</tr>

				<tr>
					<td><strong>Bairro:</strong></td>
					<td>
						<html:text maxlength="4" 
							tabindex="1"
							property="bairro" 
							size="4"
							onkeypress="validaEnterComMensagem(event, 'exibirPesquisarOrdemServicoAction.do?objetoConsultaOs=9','bairro','Bairro');return isCampoNumerico(event);"/> 
							
						<a href="javascript:verificarMunicipio();">

							<img width="23" 
								height="21" 
								border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Bairro" /></a> 

						<logic:present name="bairroEncontrada" scope="request">
							
							<html:text property="descricaoBairro" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
							
						</logic:present> 

						<logic:notPresent name="bairroEncontrada" scope="request">
							
							<html:text property="descricaoBairro" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />

						</logic:notPresent>

						<a href="javascript:limparBairro();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" />
						</a>
					
					</td>
				</tr>
		        
				<tr>
					<td>
						<strong>&Aacute;rea do Bairro:</strong>
					</td>

					<td>
						<strong> 
						<html:select property="areaBairro" style="width: 230px;">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoAreaBairro" scope="request">
								<html:options collection="colecaoAreaBairro"
								labelProperty="nome" property="id" />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>

				<tr>
					<td><strong>Logradouro:</strong></td>
					<td>
						<html:text maxlength="9" 
							tabindex="1"
							property="logradouro" 
							size="9"
							onkeypress="validaEnterComMensagem(event, 'exibirPesquisarOrdemServicoAction.do?objetoConsultaOs=10','logradouro','Logradouro');return isCampoNumerico(event);"/> 
							
						<a href="javascript:redirecionarSubmit('exibirPesquisarLogradouroAction.do?caminhoRetornoTelaPesquisaLogradouro=exibirPesquisarOrdemServicoAction&codigoMunicipio='+document.forms[0].municipio.value+'&codigoBairro='+document.forms[0].bairro.value);">

							<img width="23" 
								height="21" 
								border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Logradouro" /></a> 

						<logic:present name="logradouroEncontrado" scope="request">
							
							<html:text property="descricaoLogradouro" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
							
						</logic:present> 

						<logic:notPresent name="logradouroEncontrado" scope="request">
							
							<html:text property="descricaoLogradouro" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />

						</logic:notPresent>

						<a href="javascript:limparLogradouro();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" />
						</a>
					
					</td>
				</tr>
				</table>

				<p>&nbsp;</p>

				<table width="100%" border="0">
				<tr>

					<td height="24">
			          	
			          	<logic:present name="caminhoRetornoTelaPesquisaOrdemServico">
			          		<input type="button" 
			          			class="bottonRightCol" 
			          			value="Voltar" style="width: 70px;"
			          			onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaOrdemServico}.do')"/>
			          	</logic:present>
						
			          	<input type="button" 
			          		class="bottonRightCol" 
			          		value="Limpar" style="width: 70px;"
			          		onclick="javascript:limparForm();"/>
							&nbsp;&nbsp;
					</td>
					
					<td align="right">
						<input type="button" 
							name="Button" 
							class="bottonRightCol"
							value="Pesquisar" 
							onClick="javascript:validarForm()" />
							
					</td>
				</tr>
				</table>
			
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>
