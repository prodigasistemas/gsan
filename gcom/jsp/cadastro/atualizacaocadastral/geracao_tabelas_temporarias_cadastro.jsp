<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@page import="gcom.cadastro.empresa.Empresa"%>
<%@page import="gcom.gui.micromedicao.DadosLeiturista"%>
<%@page import="gcom.cadastro.imovel.ImovelPerfil"%>
<%@page import="gcom.cadastro.imovel.Categoria"%>
<%@page import="gcom.cadastro.imovel.Subcategoria"%>
<%@page import="gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao"%>
<%@page import="gcom.cadastro.imovel.bean.ImovelGeracaoTabelasTemporariasCadastroHelper"%>

<%@page isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>cadastro/geracao_tabelas_temporarias_cadastro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script>
	var bCancel = false; 

    function validarForm(form){

			if (bCancel) {			
				return true;
			} else {
			
				if ((form.quadraInicial.value != '' && form.quadraFinal.value == '')
	  					||(form.quadraInicial.value == '' && form.quadraFinal.value != '')) {
	  					
					if(form.quadraInicial.value == '') {
						alert('A Quadra Inicial deve ser preenchida.');
						form.quadraInicial.focus();
					}
					else {
						alert('A Quadra Final deve ser preenchida.');
						form.quadraFinal.focus();
					}	
	  					return;
	  			}
				
				if ((form.sugestao[1].checked) && form.firma.selectedIndex == 0) {
					alert('Informe a Empresa.');
					form.firma.focus();
					return;
				}

				if (form.firma.selectedIndex > 0 && form.leiturista.selectedIndex <= 0) {
					alert('Informe o Agente Cadastral.');
					form.leiturista.focus();
					return;
				}
				
				if ( parseInt(form.localidadeFinal.value) < parseInt(form.localidadeInicial.value) ) {
					alert('A Localidade Final deve ser Maior ou Igual a Inicial.');
					form.localidadeFinal.focus();
					form.nomeLocalidadeFinal.value = '';
					return;
				} else {
					if ( parseInt(form.codigoSetorComercialFinal.value) < parseInt(form.codigoSetorComercialInicial.value) ) {
						alert('O Código do Setor Comercial Final deve ser Maior ou Igual ao Inicial.');
						form.codigoSetorComercialFinal.focus();
						form.nomeSetorComercialFinal.value = '';
						return;
					} else {
						if ( parseInt(form.quadraFinal.value) < parseInt(form.quadraInicial.value) ) {
							alert('A Quadra Final deve ser Maior ou Igual a Inicial.');
							form.quadraFinal.focus();
							return;
						}
					
					}
				}
				
				form.action = 'filtrarImovelGeracaoTabelasTemporariasCadastroAction.do';
				form.submit();
			} 	
	}

    function IntegerValidations () { 
         this.aa = new Array("quantidadeMaxima", "Quantidade Máxima deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ab = new Array("elo", "Agencia deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ac = new Array("localidadeInicial", "Localidade Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ad = new Array("localidadeFinal", "Localidade Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ae = new Array("codigoSetorComercialInicial", "Código do Setor Comercial Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.af = new Array("codigoSetorComercialFinal", "Código do Setor Comercial Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ag = new Array("quadraInicial", "Quadra Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ah = new Array("quadraFinal", "Quadra Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
   	     this.aj = new Array("cliente", "Cliente deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    
	function habDesabFirmaEQuantMaxima() {
		var form = document.forms[0];

			if (form.sugestao[0].checked) { 
				form.firma.disabled = true;
				form.firma.selectedIndex = 0;
	    		form.leiturista.disabled = true;
				form.leiturista.selectedIndex = 0;
				form.quantidadeMaxima.disabled = true;
				form.quantidadeMaxima.selectedIndex = 0;
			}
			
			if (form.sugestao[1].checked) {
				form.firma.disabled = false;
	    		form.leiturista.disabled = false;
				form.quantidadeMaxima.disabled = false;
			}
	}
	
	function habilitaDesabilitaInscricao() {
		var form = document.forms[0];
		
		if (form.elo.value != '' || form.elo.lenght > 0) {
			form.localidadeInicial.value = '';
			form.nomeLocalidadeInicial.value = '';
			form.localidadeFinal.value = '';
			form.nomeLocalidadeFinal.value = '';
			
			form.codigoSetorComercialInicial.value = '';
			form.codigoSetorComercialFinal.value = '';
			form.nomeSetorComercialInicial.value = '';
			form.nomeSetorComercialFinal.value = '';
			
			form.quadraInicial.value = '';
			form.quadraFinal.value = '';
			
			form.localidadeInicial.disabled = true;
			form.localidadeFinal.disabled = true;
			form.codigoSetorComercialInicial.disabled = true;
			form.codigoSetorComercialFinal.disabled = true;
			form.quadraInicial.disabled = true;
			form.quadraFinal.disabled = true;
		}else {
			form.localidadeInicial.disabled = false;
			form.localidadeFinal.disabled = false;
			//form.codigoSetorComercialInicial.disabled = false;
			//form.codigoSetorComercialFinal.disabled = false;
			controleSetorComercial();
			//form.quadraInicial.disabled = false;
			//form.quadraFinal.disabled = false;
			controleQuadra();
			
			//form.nomeElo.value = '';
		}
		desabilitaLink();
	}
	
	function habilitaDesabilitaAgenciaInscricao() {
		var form = document.forms[0];
		
		if (form.matricula.value != '' || form.matricula.lenght > 0 || form.cliente.value != '' || form.cliente.lenght > 0) {
			form.elo.value = '';
			form.nomeElo.value = '';
			form.localidadeInicial.value = '';
			form.nomeLocalidadeInicial.value = '';
			form.localidadeFinal.value = '';
			form.nomeLocalidadeFinal.value = '';
			
			form.codigoSetorComercialInicial.value = '';
			form.codigoSetorComercialFinal.value = '';
			form.nomeSetorComercialInicial.value = '';
			form.nomeSetorComercialFinal.value = '';
			
			form.quadraInicial.value = '';
			form.quadraFinal.value = '';
			
			form.elo.disabled = true;
			form.localidadeInicial.disabled = true;
			form.localidadeFinal.disabled = true;
			form.codigoSetorComercialInicial.disabled = true;
			form.codigoSetorComercialFinal.disabled = true;
			form.quadraInicial.disabled = true;
			form.quadraFinal.disabled = true;
		}else {
			form.elo.disabled = false;
			form.localidadeInicial.disabled = false;
			form.localidadeFinal.disabled = false;
			//form.codigoSetorComercialInicial.disabled = false;
			//form.codigoSetorComercialFinal.disabled = false;
			controleSetorComercial();
			//form.quadraInicial.disabled = false;
			//form.quadraFinal.disabled = false;
			controleQuadra();
			
			form.nomeElo.value = '';
		}
		desabilitaLink();
	}
	
	function habilitaDesabilitaMatriculaCliente() {
		var form = document.forms[0];
		
		if (form.matricula.value != '') { 
			form.cliente.disabled = true;
			form.clienteNome.value = '';
		} else {
			form.cliente.disabled = false;
		}
		if (form.cliente.value != '') {
			form.matricula.disabled = true;
		} else {
			form.matricula.disabled = false;
		}	
		desabilitaLink();
	}
	
	function controleSetorComercial() {
		var form = document.forms[0];
		
		if (form.localidadeInicial.value != "") {
			form.codigoSetorComercialInicial.disabled = false;
			form.codigoSetorComercialFinal.disabled = false;
		}else {
			form.codigoSetorComercialInicial.disabled = true;
			form.codigoSetorComercialFinal.disabled = true;
			
			form.codigoSetorComercialInicial.value = "";
			form.codigoSetorComercialFinal.value = "";
			
			form.nomeSetorComercialInicial.value = "";
			form.nomeSetorComercialFinal.value = "";			
			
			
			document.getElementById("btPesqSetorComercialInicial").disabled = true;
			document.getElementById("btPesqSetorComercialFinal").disabled = true;
		}
		desabilitaLink();
	}
	
	function habilitaDesabilitaRota(){
		var form = document.forms[0];
		
		if(form.codigoSetorComercialInicial.value != '' && form.codigoSetorComercialFinal.value != ''){
			if(form.codigoSetorComercialInicial.value == form.codigoSetorComercialFinal.value){
				form.rotaInicial.disabled = false;
				form.rotaFinal.disabled = false;
				form.rotaSequenciaInicial.disabled = false;
				form.rotaSequenciaFinal.disabled = false;
			}
		} else {
			form.rotaInicial.value = "";
			form.rotaFinal.value = "";
			form.rotaSequenciaInicial.value = "";
			form.rotaSequenciaFinal.value = "";
			form.rotaInicial.disabled = true;
			form.rotaFinal.disabled = true;
			form.rotaSequenciaInicial.disabled = true;
			form.rotaSequenciaFinal.disabled = true;
		}
	}
	
	function duplicarRota(){
		var form = document.forms[0]; 
		
			form.rotaFinal.value = form.rotaInicial.value;
			form.rotaSequenciaFinal.value = form.rotaSequenciaInicial.value;
	}
	
   	function ControleCategoriaSubCategoria() {
    	var form = document.forms[0];
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
    
    function bloquearSubCategoria() {
		var form = document.forms[0];
    	var obj = form.categoria;

    	if (obj.value == "-1") {
    		form.subCategoria.value = "-1";
    		form.subCategoria.disabled = true;
    	}
    }
    
    function ControleCategoria() {
		var form = document.forms[0];
    	var obj = form.categoria;

    	if (obj.value != "-1") {
    		form.subCategoria.disabled = false;
    		pesquisaColecaoReload('exibirFiltrarImovelGeracaoTabelasTemporariasCadastro.do', 'categoria');
    		return;
    	}else {
    		form.subCategoria.value = "-1";
    		form.subCategoria.disabled = true;
    	}
    }
    
    
    function limparPesquisaCliente() {
    var form = document.forms[0];

      form.cliente.value = "";
      form.clienteNome.value = "";


  }
  
    function abrirPopupQuadra(url, tipo, objeto, codigoObjeto, altura, largura, msg){
		var form = document.forms[0];
		
		if (form.localidadeInicial.value.length < 1 || isNaN(form.localidadeInicial.value)){
			alert('Informe Localidade.');
		}
		else if (form.codigoSetorComercialInicial.value.length < 1 || isNaN(form.codigoSetorComercialInicial.value)){
			alert('Informe Setor Comercial.');
		}
		else{
			abrirPopup(url + "&codigoSetorComercial=" + form.codigoSetorComercialInicial.value  + "&idLocalidade=" + form.localidadeInicial.value , altura, largura);
		}
	
	}
	
	function redirecionarSubmitAtualizar(idQuadra) {
		var form = document.forms[0];
		
		if (form.quadraInicial.value == '') {
			urlRedirect = "/gsan/exibirFiltrarImovelGeracaoTabelasTemporariasCadastro.do?quadraInicial=" + idQuadra+"&objetoConsulta=3" ;
			duplicarQuadra();
			redirecionarSubmit(urlRedirect);
		} else {
			urlRedirect = "/gsan/exibirFiltrarImovelGeracaoTabelasTemporariasCadastro.do?quadraFinal=" + idQuadra+"&objetoConsulta=3" ;
			redirecionarSubmit(urlRedirect);
		}
		
	}
	
	function abrirPopupQuadra2(url, tipo, objeto, codigoObjeto, altura, largura, msg){
		var form = document.forms[0];
		
		if (form.localidadeInicial.value.length < 1 || isNaN(form.localidadeInicial.value)){
			alert('Informe Localidade.');
		}
		else if (form.codigoSetorComercialInicial.value.length < 1 || isNaN(form.codigoSetorComercialInicial.value)){
			alert('Informe Setor Comercial.');
		}
		else if (form.quadraInicial.value == '') {
			alert('Informe a Quadra Inicial');
		}
		else {
			abrirPopup(url + "&codigoSetorComercial=" + form.codigoSetorComercialInicial.value  + "&idLocalidade=" + form.localidadeInicial.value , altura, largura);
		}
	
	}
	
	function limparPesquisaImovel() {
    	var form = document.forms[0];

      	form.matricula.value = "";
      	form.nomeImovel.value = "";
    }
    
    function validaEnterImovel(tecla, caminhoActionReload, nomeCampo) {
     	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Matrícula do Imóvel");
    }
    
    function controleQuadra() {
		var form = document.forms[0];
		
		if (form.codigoSetorComercialInicial.value != '') {
			form.quadraInicial.disabled = false;
			form.quadraFinal.disabled = false;
		}else {
			form.quadraInicial.disabled = true;
			form.quadraFinal.disabled = true;
			
			form.quadraInicial.value = "";
			form.quadraFinal.value = "";
			
		}
		
		if (form.codigoSetorComercialFinal.value != '') {
			form.quadraInicial.disabled = false;
			form.quadraFinal.disabled = false;
		} else {
			
			form.quadraFinal.disabled = true;
			
			form.quadraFinal.value = "";
			
		}
		
		desabilitaLink();
	}
    
    function desabilitaLink() {
	    var form = document.forms[0];	
	    
	    if(form.matricula.disabled == true)
	             document.getElementById("linkMatricula").href="javascript:alert('Opção desabilitada.')";
	    else
	             document.getElementById("linkMatricula").href="javascript:abrirPopup('exibirPesquisarImovelAction.do');";
	
	    if(form.cliente.disabled == true)
	            document.getElementById("linkCliente").href="javascript:alert('Opção desabilitada.')";
	    else
	            document.getElementById("linkCliente").href="javascript:abrirPopup('exibirPesquisarClienteAction.do?indicadorUsoTodos=1&limparForm=OK', 400, 800);";
	
	    if(form.elo.disabled == true)
	            document.getElementById("btPesqElo").href="javascript:alert('Opção desabilitada.')";
	    else
	            document.getElementById("btPesqElo").href="javascript:chamarPopup1('exibirPesquisarEloAction.do', 'origem', null, null, 275, 480, '');";
	
	    if(form.localidadeInicial.disabled == true)
	            document.getElementById("btPesqLocalidadeInicial").href="javascript:alert('Opção desabilitada.')";
	    else
	            document.getElementById("btPesqLocalidadeInicial").href="javascript:chamarPopup1('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, ''); limparOrigem(1);";
		
	    if(form.localidadeFinal.disabled == true)
	            document.getElementById("btPesqLocalidadeFinal").href="javascript:alert('Opção desabilitada.')";
	    else
	            document.getElementById("btPesqLocalidadeFinal").href="javascript:''exibirPesquisarLocalidadeAction.do', 'destino', null, null, 275, 480, ''); limparDestino(1);";
	
	    if(form.codigoSetorComercialInicial.disabled == true)
	            document.getElementById("btPesqSetorComercialInicial").href="javascript:alert('Opção desabilitada.')";
	    else
	            document.getElementById("btPesqSetorComercialInicial").href="javascript:chamarPopupSetorComercial('exibirPesquisarSetorComercialAction.do?idLocalidade=' + document.forms[0].localidadeInicial.value, 'setorComercialOrigem','idLocalidade', null, 275, 480, 'Informe Localidade Inicial.'); controleQuadra();";
		
	    if(form.codigoSetorComercialFinal.disabled == true)
	            document.getElementById("btPesqSetorComercialFinal").href="javascript:alert('Opção desabilitada.')";
	    else
	            document.getElementById("btPesqSetorComercialFinal").href="javascript:chamarPopupSetorComercial('exibirPesquisarSetorComercialAction.do?idLocalidade=' + document.forms[0].localidadeFinal.value, 'setorComercialDestino', 'idLocalidade', null, 275, 480, 'Informe Localidade Final.'); limparDestino(2);";
		
	    if(form.quadraInicial.disabled == true)
	            document.getElementById("linkQuadraInicial").href="javascript:alert('Opção desabilitada.')";
	    else
	            document.getElementById("linkQuadraInicial").href="javascript:abrirPopupQuadra('exibirPesquisarQuadraAction.do?consulta=sim');";
	
	    if(form.quadraFinal.disabled == true)
	            document.getElementById("linkQuadraFinal").href="javascript:alert('Opção desabilitada.')";
	    else
	            document.getElementById("linkQuadraFinal").href="javascript:abrirPopupQuadra2('exibirPesquisarQuadraAction.do?consulta=sim');";
	}
	
	function desabilitaIntervaloDiferente(tipo){
		var form = document.forms[0];
		
		switch(tipo){
			case 1: //De localidade 
			    if(form.localidadeInicial.value != form.localidadeFinal.value){
			        limparBorrachaOrigem(2);
			        form.codigoSetorComercialInicial.disabled = true;
				 	form.codigoSetorComercialFinal.disabled = true;
				}else {
				 	form.codigoSetorComercialInicial.disabled = false;
				 	form.codigoSetorComercialFinal.disabled = false;
				}
				break;
		}
	}

	function limparBorrachaOrigem(tipo){
		var form = document.forms[0];
		
		switch(tipo){
			case 1: //De localidade pra baixo
			    if(!form.localidadeInicial.disabled){
					form.localidadeInicial.value = "";
					form.nomeLocalidadeInicial.value = "";
					form.localidadeFinal.value = "";
					form.nomeLocalidadeFinal.value = "";
		
					desabilitaIntervaloDiferente(1);
				}else {
					break;
				}
				
			case 2: //De setor para baixo
			    if(!form.codigoSetorComercialInicial.disabled){
					form.codigoSetorComercialInicial.value = "";
					form.nomeSetorComercialInicial.value = "";
					
					form.codigoSetorComercialFinal.value = "";
					form.nomeSetorComercialFinal.value = "";
					controleQuadra();
			    }else {
					break;
			    }
		}
	}

	/*
	Limpa os campos da faixa final pelo click na borracha(conforme parametro passado)
	*/
	function limparBorrachaDestino(tipo){
		var form = document.forms[0];
	
		switch(tipo){
			case 1: //De localidade pra baixo
			     form.localidadeFinal.value = "";
				 form.nomeLocalidadeFinal.value = "";					
				 form.codigoSetorComercialFinal.value = "";
				 form.codigoSetorComercialInicial.value = "";
				 
				 form.codigoSetorComercialInicial.disabled = true;
				 form.codigoSetorComercialFinal.disabled = true;

				 form.nomeSetorComercialInicial.value = "";
				 form.nomeSetorComercialFinal.value = "";
				 
				 controleQuadra();
				 desabilitaLink();
				
			case 2: //De setor para baixo		   
			    if(!form.codigoSetorComercialInicial.disabled){
					
					form.codigoSetorComercialFinal.value = "";
					form.nomeSetorComercialFinal.value = "";
					controleQuadra();
			    }else {
					break;
			    }
			   
	     }
	}

	function habilitarDesabilitarFiltro() {
		var form = document.forms[0];
	
		if ( form.arquivo.value != null && form.arquivo.value != "" ) {
			
			limparBorrachaOrigem(1);
			
			form.matricula.disabled = true;
			form.cliente.disabled = true;
			form.quantidadeMaxima.disabled = true;
			form.elo.disabled = true;
			form.localidadeInicial.disabled = true;
			form.localidadeFinal.disabled = true;
			form.codigoSetorComercialInicial.disabled = true;
			form.codigoSetorComercialFinal.disabled = true;			
			
			form.perfilImovel.value = "-1";
			form.perfilImovel.disabled = true;
			
			form.categoria.value = "-1";
			form.categoria.disabled = true;
			
			form.idSituacaoLigacaoAgua.value = "-1";
			form.idSituacaoLigacaoAgua.disabled = true;		
			

			form.imovelSituacao[0].disabled = true;				
			form.imovelSituacao[1].disabled = true;				
			
			desabilitaLink();

		} 
	}

	function listarLeiturista() {
		var form = document.forms[0];
    	var obj = form.firma;

    	if (obj.value != "-1") {
    		form.leiturista.disabled = false;
			form.action = 'exibirFiltrarImovelGeracaoTabelasTemporariasCadastro.do';
		  	form.submit();
    		return;
    	}else {
    		form.leiturista.value = "-1";
    		form.leiturista.disabled = true;
    	}
	}

	function bloquearLeiturista() {
		var form = document.forms[0];
    	var obj = form.firma;

    	if (obj.value == "-1") {
    		form.leiturista.value = "-1";
    		form.leiturista.disabled = true;
    	}
    }
	
</script>

</head>
<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');habilitaDesabilitaMatriculaCliente();
bloquearSubCategoria();controleQuadra();bloquearLeiturista();">

<form action="/gsan/exibirFiltrarImovelGeracaoTabelasTemporariasCadastro.do"
method="post" enctype="multipart/form-data">
	
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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

			<td width="625" valign="top" class="centercoltext">
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
						<td class="parabg">Gerar Arquivo de Atualiza&ccedil;&atilde;o Cadastral </td>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
				</table>
				<p>&nbsp;</p>
			
				<table width="100%" border="0">
					<tr>
						<td colspan="2">Para gerar o(s) arquivo(s) de Atualiza&ccedil;&atilde;o Cadastral, informe os dados abaixo:</td>
					</tr>

					<tr>
						<td><strong>A&ccedil;&atilde;o:</strong></td>
						<td align="left">
							<table width="100%" border="0">
								<tr>
									<td style="width: 175px;">
										<logic:equal name="helper" property="sugestao" value="1">
											<input type="radio" value="1" name="sugestao" onclick="habDesabFirmaEQuantMaxima();" checked="true"/>&nbsp;SIMULAR QTD. IM&Oacute;VEIS
										</logic:equal>
										
										<logic:notEqual name="helper" property="sugestao" value="1">
											<input type="radio" value="1" name="sugestao" onclick="habDesabFirmaEQuantMaxima();" />&nbsp;VERIFICAR QTD. IM&Oacute;VEIS
										</logic:notEqual>
										
									</td>
									<td align="left">
										<logic:equal name="helper" property="sugestao" value="2">
											<input type="radio" value="2" name="sugestao" onclick="habDesabFirmaEQuantMaxima();" checked="true"/>&nbsp;GERAR DADOS
										</logic:equal>
										
										<logic:notEqual name="helper" property="sugestao" value="2">
											<input type="radio" value="2" name="sugestao" onclick="habDesabFirmaEQuantMaxima();" />&nbsp;GERAR DADOS
										</logic:notEqual>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr><td colspan="2"><hr></td></tr>
					
					<tr>
						<td><strong>Empresa:<font color="#FF0000">*</font></strong></td>
						<td align="right">
							<div align="left">
								<strong>
									<logic:present name="sugestao">
										<logic:equal name="sugestao" value="1">
											<select name="firma" onchange="javascript:listarLeiturista();" style="width: 400px;">
											<option value="-1">&nbsp;</option>
												<logic:iterate id="empresa" type="Empresa" name="colecaoFirma" scope="session">
													<logic:equal name="helper" property="firma" value="${empresa.id}">
														<option selected="selected" value="${empresa.id}">${helper.nomeFirma}</option>
													</logic:equal>
													<logic:notEqual name="helper" property="firma" value="${empresa.id}">
														<option value="${empresa.id}">${empresa.descricao}</option>
													</logic:notEqual>
												</logic:iterate>
											</select>
										</logic:equal>
										<logic:equal name="sugestao" value="2">
											<select name="firma" onchange="javascript:listarLeiturista();" style="width: 400px;">
												<option value="-1">&nbsp;</option>											
												<logic:iterate id="empresa" type="Empresa" name="colecaoFirma" scope="session">
													<logic:equal name="helper" property="firma" value="${empresa.id}">
														<option selected="selected" value="${empresa.id}">${helper.nomeFirma}</option>
													</logic:equal>
													<logic:notEqual name="helper" property="firma" value="${empresa.id}">
														<option value="${empresa.id}">${empresa.descricao}</option>
													</logic:notEqual>
												</logic:iterate>
											</select>
										</logic:equal>
									</logic:present>
									
									<logic:notPresent name="sugestao">
										<select name="firma" onchange="javascript:listarLeiturista();" style="width: 400px;">
											<option value="-1">&nbsp;</option>										
										 	<logic:iterate id="empresa" type="Empresa" name="colecaoFirma" scope="session">
												<logic:equal name="helper" property="firma" value="${empresa.id}">
														<option selected="selected" value="${empresa.id}">${helper.nomeFirma}</option>
													</logic:equal>
													<logic:notEqual name="helper" property="firma" value="${empresa.id}">
														<option value="${empresa.id}">${empresa.descricao}</option>
													</logic:notEqual>
											</logic:iterate>
										</select>
									</logic:notPresent>
								</strong>
							</div>
						</td>
					</tr>
					
					<tr>
						<td><strong>Agente Cadastral:<font color="#FF0000">*</font></strong></td>
						<td align="right">
							<div align="left">
								<select name="leiturista" style="width: 400px;">
									<option value="-1">&nbsp;</option>											
										<logic:iterate id="leiturista" type="DadosLeiturista" name="colecaoLeiturista" scope="session">
											<logic:equal name="helper" property="leiturista" value="${leiturista.id}">
												<option selected="selected" value="${leiturista.id}">${helper.nomeLeiturista}</option>
											</logic:equal>
									
											<logic:notEqual name="helper" property="leiturista" value="${leiturista.id}">
												<option value="${leiturista.id}">${leiturista.descricao}</option>
											</logic:notEqual>
										</logic:iterate>
								</select>
							</div>
						</td>
					</tr>

					<tr>
						<td><strong>Quantidade M&aacute;xima:</strong></td>
						<td align="left">
							<input type="text" name="quantidadeMaxima" size="5" maxlength="4" 
							value="${helper.quantidadeMaxima }"
							onkeypress="javascript:somente_numero(this);"
							onkeyup="javascript:somente_numero(this);"/>
						</td>
					</tr>
					
					<tr><td colspan="2"><hr></td></tr>
					
					<tr>
						<td widht="30%"><strong>Matr&iacute;cula:</strong></td>
						<td widht="70%" align="left">
							<input type="text" name="matricula" size="10" maxlength="9" 
								value="${helper.matricula}"
								onkeyup="somente_numero(this);habilitaDesabilitaMatriculaCliente(); habilitaDesabilitaAgenciaInscricao();"
								onblur="habilitaDesabilitaMatriculaCliente(); habilitaDesabilitaAgenciaInscricao();"
								onkeypress="javascript:somente_numero(this);validaEnter(event, 'exibirFiltrarImovelGeracaoTabelasTemporariasCadastro.do?objetoConsulta=6&inscricaoTipo=origem.do', 'matricula');"
							/>
						
						   <a id="linkMatricula" href="javascript:abrirPopup('exibirPesquisarImovelAction.do');">
						        <img width="23" height="21" border="0" src="
						        <bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Imovel" />							   
						   </a>							  							 
						   
						   <logic:present name="matriculaNaoEncontrada">
							   <logic:equal name="matriculaNaoEncontrada" value="exception">
									<input type="text" name="nomeImovel" value="${helper.nomeMatricula}" 
										size="40" maxlength="40" readonly="true" 
										style="background-color:#EFEFEF; border:0; color: #ff0000" 
									/>
							   </logic:equal>
							   <logic:notEqual name="matriculaNaoEncontrada" value="exception">
									<input type="text" name="nomeImovel" size="40" maxlength="40"
										value="${helper.nomeMatricula}"  readonly="true" 
										style="background-color:#EFEFEF; border:0; color: #000000" />
							   </logic:notEqual>
						   </logic:present> 
						   
						   <logic:notPresent name="matriculaNaoEncontrada">
							   <logic:empty name="ImovelGeracaoTabelasTemporariasCadastroHelper" name="matricula">
									<input type="text" name="nomeImovel" value="" size="40"
										value="${helper.nomeMatricula}"
										maxlength="40" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:empty>
								<logic:notEmpty name="ImovelGeracaoTabelasTemporariasCadastroHelper"
									name="matricula">
									<input type="text" name="nomeImovel" size="40" maxlength="40"
										value="${helper.nomeMatricula}"								
										readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>
							</logic:notPresent> 
							<a href="javascript:limparPesquisaImovel();habilitaDesabilitaMatriculaCliente(); 
								habilitaDesabilitaAgenciaInscricao();">
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" 
								border="0" title="Apagar" />
							</a>						
							         							        
						</td>
					</tr>

					<tr>
						<td><strong>Cliente:</strong></td>
						<td align="left">
							<input type="text" name="cliente" size="10" maxlength="9" 
								value="${helper.cliente}"
								onkeyup="somente_numero(this);habilitaDesabilitaMatriculaCliente(); habilitaDesabilitaAgenciaInscricao();"
								onblur="habilitaDesabilitaMatriculaCliente(); habilitaDesabilitaAgenciaInscricao();"
								onkeypress="javascript:somente_numero(this);validaEnter(event, 'exibirFiltrarImovelGeracaoTabelasTemporariasCadastro.do?objetoConsulta=5&inscricaoTipo=origem', 'cliente');"/>
							<a id="linkCliente" href="javascript:abrirPopup('exibirPesquisarClienteAction.do?indicadorUsoTodos=1&limparForm=OK', 400, 800);">
								<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Cliente" />
							</a>												
							<logic:present name="idClienteNaoEncontrado">
								<logic:equal name="idClienteNaoEncontrado" value="exception">
									<input type="text" name="clienteNome" value="${helper.nomeCliente}" 
										size="40" maxlength="40" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:equal>
								<logic:notEqual name="idClienteNaoEncontrado" value="exception">
									<input type="text" name="clienteNome" value="${helper.nomeCliente}" 
										size="40" maxlength="40" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEqual>
							</logic:present> 
							<logic:notPresent name="idClienteNaoEncontrado">
								<logic:empty name="ImovelGeracaoTabelasTemporariasCadastroHelper"
									name="cliente">
									<input type="text" name="clienteNome" value="${helper.nomeCliente}"
										 size="40" maxlength="40" readonly="true"
										 style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:empty>
								<logic:notEmpty name="ImovelGeracaoTabelasTemporariasCadastroHelper"
									name="cliente">
									<input type="text" name="clienteNome" value="${helper.nomeCliente}" 
										size="40" maxlength="40" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>
							</logic:notPresent> 
							<a href="javascript:limparPesquisaCliente();habilitaDesabilitaMatriculaCliente(); habilitaDesabilitaAgenciaInscricao();">
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"border="0" title="Apagar" />
							</a>
						</td>						
					</tr>
					
					<tr><td colspan="2"><hr></td></tr>
					
					<tr>
						<td><strong>Ag&ecirc;ncia:</strong></td>
						<td>
							<input type="text" tabindex="7" maxlength="3" name="elo" size="5" value ="${helper.elo }"
								onkeypress="somente_numero(this);form.target=''; validaEnter(event,'exibirFiltrarImovelGeracaoTabelasTemporariasCadastro.do?objetoConsulta=4&inscricaoTipo=origem','elo');"
								onblur="habilitaDesabilitaInscricao();" 
								onkeyup="somente_numero(this);habilitaDesabilitaInscricao();habilitaDesabilitaRota();" 
							/>
						 	<!-- 	
						 	<input type="text" text tabindex="7" maxlength="3" property="elo" size="5"
								onkeypress="validaEnter(event,'exibirFiltrarImovelGeracaoTabelasTemporariasCadastro.do?objetoConsulta=4&inscricaoTipo=origem','elo');"
								/>
							-->	
							<a href="javascript:chamarPopup1('exibirPesquisarEloAction.do', 'origem', null, null, 275, 480, '');" id="btPesqElo">
								<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar" />
							</a>
							<logic:present name="corElo">
								<logic:equal name="corElo" value="exception">
									<input type="text" name="nomeElo" value ="${helper.nomeElo}" size="35" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:equal>
								<logic:notEqual name="corElo" value="exception">
									<input type="text" name="nomeElo" value="${helper.nomeElo}" size="35" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEqual>
							</logic:present>
							
							<logic:notPresent name="corElo">
								<logic:empty name="ImovelGeracaoTabelasTemporariasCadastroHelper"
									name="elo">
									<input type="text" name="nomeElo" value="${helper.nomeElo }" size="35" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:empty>
								<logic:notEmpty name="ImovelGeracaoTabelasTemporariasCadastroHelper" name="elo">
									<input type="text" name="nomeElo" size="35" value="${helper.nomeElo }" readonly="true"
										style="background-color:#EFEFEF; border:0; color: 	#000000" />
								</logic:notEmpty>
							</logic:notPresent>
							<a href="javascript:limparBorrachaElo(); habilitaDesabilitaInscricao();">
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
							</a>
						</td>
					</tr>
					
					<tr>
						<td colspan="2"><hr></td>
					</tr>
					
					<tr>
						<td colspan="2">
							<strong>Informe os dados da inscri&ccedil;&atilde;o inicial:</strong>
						</td>
					</tr>
					
					<tr>
						<td><strong>Localidade:</strong></td>
						<td>
							<input type="text" tabindex="8" maxlength="3" name="localidadeInicial" 
								value="${helper.localidadeInicial}" size="5"
								onkeypress="somente_numero(this);form.target=''; validaEnter(event,'exibirFiltrarImovelGeracaoTabelasTemporariasCadastro.do?objetoConsulta=1&inscricaoTipo=origem', 'localidadeInicial'); controleSetorComercial(); limparDestino(1); "
								onclick="javascript:validarLocalidade();" 
								onchange="javascript:duplicarLocalidade();controleSetorComercial();controleQuadra();validarLocalidade();"
								onkeyup="javascript:somente_numero(this);"/>
							<a href="javascript:chamarPopup1('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, ''); limparOrigem(1);" id="btPesqLocalidadeInicial">
								<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar" />
							</a>
							<logic:present name="corLocalidadeOrigem">
								<logic:equal name="corLocalidadeOrigem" value="exception">
									<input type="text" name="nomeLocalidadeInicial" value="${helper.nomeLocalidadeInicial}" 
										size="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:equal>
		
								<logic:notEqual name="corLocalidadeOrigem" value="exception">
									<input type="text" name="nomeLocalidadeInicial" value="${helper.nomeLocalidadeInicial}"
										 size="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEqual>
							</logic:present>
							
							<logic:notPresent name="corLocalidadeOrigem">
								<logic:empty name="ImovelGeracaoTabelasTemporariasCadastroHelper" name="localidadeInicial">
									<input type="text" name="nomeLocalidadeInicial" value="${helper.nomeLocalidadeInicial}" 
										size="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:empty>

								<logic:notEmpty name="ImovelGeracaoTabelasTemporariasCadastroHelper" name="localidadeInicial">
									<input type="text" name="nomeLocalidadeInicial" value="${helper.nomeLocalidadeInicial}"
									  size="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>
							</logic:notPresent>
							
							<a href="javascript:limparBorrachaOrigem(1);controleSetorComercial();controleQuadra();">
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" title="Apagar" />
							</a>
						</td>
					</tr>
					
					<tr>
						<td>
							<strong>Setor Comercial:</strong>
						</td>
						<td>
							<input type="text" tabindex="9" maxlength="3" size="5"
							name="codigoSetorComercialInicial" value="${helper.codigoSetorComercialInicial}"
							onkeyup="somente_numero(this);controleQuadra();//limparOrigem(2);"
							onkeypress="somente_numero(this);form.target='';controleQuadra();  limparDestino(2); validaEnterDependencia(event, 'exibirFiltrarImovelGeracaoTabelasTemporariasCadastro.do?objetoConsulta=2&inscricaoTipo=origem', document.forms[0].codigoSetorComercialInicial, document.forms[0].localidadeInicial.value, 'Localidade Inicial.');"
							onblur="javascript:duplicarSetorComercial();controleQuadra();" 
							onchange="javascript:habilitaDesabilitaRota();controleQuadra();"/>
							
							<a href="javascript:chamarPopupSetorComercial('exibirPesquisarSetorComercialAction.do?idLocalidade=' + document.forms[0].localidadeInicial.value, 'setorComercialOrigem','idLocalidade', document.forms[0].localidadeInicial.value, 275, 480, 'Informe Localidade Inicial.'); controleQuadra();" id="btPesqSetorComercialInicial">
								<img width="23" height="21" border="0" 
									src="<bean:message key="caminho.imagens"/>pesquisa.gif" 
									title="Pesquisar" />
							</a>
									
							<logic:present name="corSetorComercialOrigem">
								<logic:equal name="corSetorComercialOrigem" value="exception">
									<input type="text" name="nomeSetorComercialInicial" value="${helper.nomeSetorComercialInicial}"
										size="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:equal>
	
								<logic:notEqual name="corSetorComercialOrigem" value="exception">
									<input type="text" name="nomeSetorComercialInicial" value="${helper.nomeSetorComercialInicial}" 
											size="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEqual>
							</logic:present>
							
							<logic:notPresent name="corSetorComercialOrigem">
								<logic:empty name="ImovelGeracaoTabelasTemporariasCadastroHelper" name="codigoSetorComercialInicial">
									<input type="text" name="nomeSetorComercialInicial" value="${helper.nomeSetorComercialInicial}" size="35" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:empty>
								<logic:notEmpty name="ImovelGeracaoTabelasTemporariasCadastroHelper" name="codigoSetorComercialInicial">
									<input type="text" name="nomeSetorComercialInicial" value="${helper.nomeSetorComercialInicial}" 
											size="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>
							</logic:notPresent>
							<a href="javascript:limparBorrachaOrigem(2);controleQuadra();">
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
							</a>
						</td>
					</tr>
					
					<tr>
						<td>
							<strong>Quadra:</strong>
						</td>
						<td><input type="text" tabindex="10" maxlength="4"
							name="quadraInicial" value="${helper.quadraInicial}" size="5"
							onkeypress="somente_numero(this);form.target='';limparOrigem(3);"
							onkeyup="somente_numero(this);javascript:duplicarQuadra();"
							onblur="javascript:duplicarQuadra();"/> 
							
							<a id="linkQuadraInicial" href="javascript:abrirPopupQuadra('exibirPesquisarQuadraAction.do?consulta=sim');"><img
									 width="23" height="21" border="0"
									 src="<bean:message key="caminho.imagens"/>pesquisa_verde.gif"
									 title="Pesquisar Quadra" />
							</a>
									 							
							<logic:present name="corQuadraOrigem" scope="request">
								<span style="color:#ff0000" id="msgQuadraInicial"><bean:write
									  scope="request" name="msgQuadraInicial"/>
								</span>
							</logic:present> 
							<logic:notPresent name="corQuadraOrigem" scope="request"> </logic:notPresent> 
						</td>
					</tr>
					
					<tr>
						<td colspan="2"><hr></td>
					</tr>
					
					<tr>
						<td colspan="2">
							<strong>Informe os dados da inscri&ccedil;&atilde;o final:</strong>
						</td>
					</tr>
					
					<tr>
						<td>
							<strong>Localidade:</strong>
						</td>
						<td>
							<input type="text" maxlength="3" name="localidadeFinal" value="${helper.localidadeFinal}" size="5"
								onkeyup="somente_numero(this);desabilitaIntervaloDiferente(1);" 
								onkeypress="somente_numero(this);controleSetorComercial(); limparDestino(1); validaEnter(event,'exibirFiltrarImovelGeracaoTabelasTemporariasCadastro.do?objetoConsulta=1&inscricaoTipo=destino', 'localidadeFinal');"
								tabindex="11" />
							<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', null, null, 275, 480, ''); limparDestino(1);" id="btPesqLocalidadeFinal">
								<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									 title="Pesquisar" /></a>
							<logic:present name="corLocalidadeDestino">
								<logic:equal name="corLocalidadeDestino" value="exception">
									<input type="text" name="nomeLocalidadeFinal" value="${helper.nomeLocalidadeFinal}" 
										size="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:equal>
		
								<logic:notEqual name="corLocalidadeDestino" value="exception">
									<input type="text" name="nomeLocalidadeFinal" value="${helper.nomeLocalidadeFinal}"
										size="35" readonly="true"style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEqual>
							</logic:present>
							
							<logic:notPresent name="corLocalidadeDestino">
								<logic:empty name="ImovelGeracaoTabelasTemporariasCadastroHelper" name="localidadeFinal">
									<input type="text" name="nomeLocalidadeFinal" value="${helper.nomeLocalidadeFinal}"
										 size="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:empty>
								<logic:notEmpty name="ImovelGeracaoTabelasTemporariasCadastroHelper" name="localidadeFinal">
									<input type="text" name="nomeLocalidadeFinal" value="${helper.nomeLocalidadeFinal}"
										size="35" readonly="true" style="background-color:#EFEFEF; border:0; color: 	#000000" />
								</logic:notEmpty>
							</logic:notPresent>
							
							<a href="javascript:limparBorrachaDestino(1);">
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
							</a>
						</td>
					</tr>
					
					<tr>
						<td>
							<strong>Setor Comercial: </strong>
						</td>
						<td>
							<input type="text" maxlength="3" name="codigoSetorComercialFinal" size="5"
								value="${helper.codigoSetorComercialFinal}" 
								onkeyup="somente_numero(this);limparDestino(2); habilitaDesabilitaRota(); //desabilitaIntervaloDiferente(2);"
								onkeypress="somente_numero(this);controleQuadra(); form.target=''; 
									validaEnterDependencia(event, 'exibirFiltrarImovelGeracaoTabelasTemporariasCadastro.do?objetoConsulta=2&inscricaoTipo=destino', document.forms[0].codigoSetorComercialFinal, document.forms[0].localidadeFinal.value, 'Localidade Final.');"
								tabindex="12" />
								<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialDestino', 'idLocalidade', document.ImovelGeracaoTabelasTemporariasCadastroActionForm.localidadeFinal.value, 275, 480, 'Informe Localidade Final.'); limparDestino(2);" 
									id="btPesqSetorComercialFinal">
									<img width="23" height="21" border="0" 
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar" />
								</a>
							
							<logic:present name="corSetorComercialDestino">
								<logic:equal name="corSetorComercialDestino" value="exception">
									<input type="text" name="nomeSetorComercialFinal" value="${helper.nomeSetorComercialFinal}"
										 size="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
								</logic:equal>

								<logic:notEqual name="corSetorComercialDestino" value="exception">
									<input type="text" name="nomeSetorComercialFinal" value="${helper.nomeSetorComercialFinal}"
										 size="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
								</logic:notEqual>
							</logic:present>
							
							<logic:notPresent name="corSetorComercialDestino">
								<logic:empty name="ImovelGeracaoTabelasTemporariasCadastroHelper" name="codigoSetorComercialFinal">
									<input type="text" name="nomeSetorComercialFinal" value="${helper.nomeSetorComercialFinal}"
										 size="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
								</logic:empty>
								<logic:notEmpty name="ImovelGeracaoTabelasTemporariasCadastroHelper" name="codigoSetorComercialFinal">
									<input type="text" name="nomeSetorComercialFinal" value="${helper.nomeSetorComercialFinal}"
										size="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
								</logic:notEmpty>
							</logic:notPresent>
							
							<a href="javascript:limparBorrachaDestino(2);limparPesquisaQuadraFinal();">
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" title="Apagar" />
							</a>
						</td>
					</tr>
					
					<tr>
						<td>
							<strong>Quadra: </strong>
						</td>
						<td>
							<input type="text" maxlength="4" name="quadraFinal" value="${helper.quadraFinal}" size="5"
								onkeyup="somente_numero(this);desabilitaIntervaloDiferente(3);"
								onkeypress="somente_numero(this);form.target=''; limparDestino(3);"
								tabindex="13" /> 
							
							<a id="linkQuadraFinal" href="javascript:abrirPopupQuadra2('exibirPesquisarQuadraAction.do?consulta=sim');"><img
									 width="23" height="21" border="0"
									 src="<bean:message key="caminho.imagens"/>pesquisa_verde.gif"
									 title="Pesquisar Quadra" /></a>
							
							<logic:present name="corQuadraDestino" scope="request">
								<span style="color:#ff0000" id="msgQuadraFinal"><bean:write
									scope="request" name="msgQuadraFinal" /></span>
							</logic:present>
							<logic:notPresent name="corQuadraDestino" scope="request">
							</logic:notPresent> 
						</td>
					</tr>
					
					<tr>
						<td colspan="2"><hr></td>
					</tr>
					<tr>
						<td colspan="2">
							<strong>Informe os dados da Rota:</strong>
						</td>
					</tr>
					
					<tr>
						<td>
						  <strong>Rota inicial:</strong>
						</td>
						<td>
						  <table widht="100%" border="0">
							  <tr>
								<td align="left">
									<input type="text" name="rotaInicial"
									 value="${helper.rotaInicial}"
									 size="5" maxlength="4" disabled="true"
									 onblur="duplicarRota();"
									 onkeyup="somente_numero(this);duplicarRota();"
									 onkeypress="somente_numero(this);"/>
								</td>
								<td>seq.:</td>
								<td align="left">
									<input type="text" name="rotaSequenciaInicial" 
									value="${helper.rotaSequenciaInicial}"
									size="7" maxlength="6" disabled="true" 
									onblur="duplicarRota();"
									onkeyup="somente_numero(this);duplicarRota();"
									onkeypress="somente_numero(this);"/>
								</td>
							  </tr>
							</table>
						</td>
					  </tr>
					  <tr>
						<td>
						  <strong>Rota Final:</strong>
						</td>
						<td>
						  <table widht="100%" border="0">
							  <tr>
								<td align="left">
									<input type="text" name="rotaFinal"
									 	value="${helper.rotaFinal}"	
									 	size="5" maxlength="4" disabled="true"
									 	onkeypress="somente_numero(this);"
									 	onkeyup="somente_numero(this);"/>
								</td>
								<td>seq.:</td>
								<td align="left">
									<input type="text" name="rotaSequenciaFinal" 
										value="${helper.rotaSequenciaFinal}"
										size="7" maxlength="6" disabled="true"
										onkeypress="somente_numero(this);"
										onkeyup="somente_numero(this);"/>
								</td>
							  </tr>
						  </table>
						</td>
					  </tr>						
					<tr>
						<td colspan="2"><hr>
						</td>
					</tr>
					
					<tr>
						<td>
							<strong>Perfil do Im&oacute;vel:</strong>
						</td>

						<td align="right">
							<div align="left">
								<strong>
									<select name="perfilImovel" style="width: 400px;">
										<option value="-1">&nbsp;</option>									
										<logic:iterate id="imovelPerfil" type="ImovelPerfil" name="collectionImovelPerfil" scope="session">
											<logic:equal name="helper" property="perfilImovel" value="${imovelPerfil.id}">
												<option selected="selected" value="${imovelPerfil.id}">${helper.nomePerfilImovel}</option>
											</logic:equal>
											<logic:notEqual name="helper" property="perfilImovel" value="${imovelPerfil.id}">
												<option value="${imovelPerfil.id}">${imovelPerfil.descricao}</option>
											</logic:notEqual>
										</logic:iterate>
									</select>
								</strong>
							</div>
						</td>
					</tr>
				
					<tr>
						<td>
							<strong>Categoria:</strong>
						</td>
						<td align="right">
							<div align="left">
								<strong>
									<select name="categoria" onchange="ControleCategoria();" style="width: 400px;">
										<option value="-1">&nbsp;</option>
										<logic:iterate id="categoria" type="Categoria" name="collectionImovelCategoria" scope="session">
											<logic:equal name="helper" property="categoria" value="${categoria.id}">
												<option selected="selected" value="${categoria.id}">${helper.nomeCategoria}</option>
											</logic:equal>
											<logic:notEqual name="helper" property="categoria" value="${categoria.id}">
												<option value="${categoria.id}">${categoria.descricao}</option>
											</logic:notEqual>
										</logic:iterate>
									</select>
								</strong>
							</div>
						</td>
					</tr>

					<tr>
						<td>
							<strong>Subcategoria:</strong>
						</td>
						<td align="right">
							<div align="left">
								<strong>
									<select name="subCategoria" style="width: 400px;">
										<option value="-1">&nbsp;</option>
										<logic:iterate id="subCategoria" type="Subcategoria" name="collectionImovelSubcategoria" scope="session">
											<logic:equal name="helper" property="subCategoria" value="${subCategoria.id}">
												<option selected="selected" value="${subCategoria.id}">helper.nomeSubCategoria</option>
											</logic:equal>
											<logic:notEqual name="helper" property="subCategoria" value="${subCategoria.id}">
												<option value="${subCategoria.id}">${subCategoria.descricao}</option>
											</logic:notEqual>
										</logic:iterate>			
									</select>
								</strong>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2"><hr>
						</td>
					</tr>
				    <tr> 
			         <td><strong>Situação da Ligação de Água:</strong></td>
			         <td>
			             <select name="idSituacaoLigacaoAgua" tabindex="6">
								<option value="-1">&nbsp;</option>			             
								<logic:iterate id="idSituacaoLigacaoAgua" type="LigacaoAguaSituacao" name="colecaoLigacaoAguaSituacao" scope="session">
									<logic:equal name="helper" property="idSituacaoLigacaoAgua" value="${idSituacaoLigacaoAgua.id}">
										<option selected="selected" value="${idSituacaoLigacaoAgua.id}">${helper.nomeSituacaoLigacaoAgua}</option>
									</logic:equal>
									<logic:notEqual name="helper" property="idSituacaoLigacaoAgua" value="${idSituacaoLigacaoAgua.id}">
										<option value="${idSituacaoLigacaoAgua.id}">${idSituacaoLigacaoAgua.descricao}</option>
									</logic:notEqual>
								</logic:iterate>			
						 </select>
			         </td>
     			   </tr>
  				   <tr>
					<td><strong>Imóvel:</strong></td>
					<td align="left">
					<table width="100%" border="0">
					  <tr>
						<td width="100">
							<logic:equal name="helper" property="imovelSituacao" value="1">
								<input type="radio" value="1" name="imovelSituacao" checked="true"/>&nbsp;TODOS
							</logic:equal>
							
							<logic:notEqual name="helper" property="imovelSituacao" value="1">
								<input type="radio" value="1" name="imovelSituacao" />&nbsp;TODOS
							</logic:notEqual>
						</td>
						<td align="left">
							<logic:equal name="helper" property="imovelSituacao" value="2">
								<input type="radio" value="2" name="imovelSituacao" checked="true"/>&nbsp;COM DÉBITO
							</logic:equal>
							
							<logic:notEqual name="helper" property="imovelSituacao" value="2">
								<input type="radio" value="2" name="imovelSituacao" />&nbsp;COM DÉBITO
							</logic:notEqual>
						</td>
					  </tr>
					</table>
					</td>
					</tr>   
				   <tr>
						<td>&nbsp;</td>
						<td colspan="3" align="right">&nbsp;</td>
					</tr>
					
					<tr>
						<td width="40%" align="left">
							<input type="button" name="ButtonReset" class="bottonRightCol" value="Desfazer" 
								onClick="window.location.href='/gsan/exibirFiltrarImovelGeracaoTabelasTemporariasCadastro.do?menu=sim';">
							<input type="button"name="ButtonCancelar" class="bottonRightCol" value="Cancelar" 
								onclick="window.location.href='/gsan/telaPrincipal.do'">
						</td>
						<td align="right">
	    	            	<input type="button" onClick="javascript:validarForm(document.forms[0]);" name="botaoConcluir" class="bottonRightCol" value="Concluir">
	        	        </td>
					</tr>	
				</table>
				<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</form>
<script>
	habDesabFirmaEQuantMaxima();
	habilitaDesabilitaInscricao();
	habilitaDesabilitaRota();
	controleSetorComercial();
	ControleCategoriaSubCategoria();
	controleQuadra();
</script>

</body>
</html>