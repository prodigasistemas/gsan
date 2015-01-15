<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

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
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirServicoTipoActionForm" />

<script language="JavaScript">

	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado) {
		if(objetoRelacionado.disabled != true) {
			if (objeto == null || codigoObjeto == null) {
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			} else {
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)) {
					alert(msg);
				} else {
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}	 

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
  		var form=document.forms[0];
    	if (tipoConsulta == 'hidrometro') {
      		form.numeroHidrometro.value = codigoRegistro;
    	} else if (tipoConsulta == 'tipoDebito') { 	
	 	  	form.idTipoDebito.value = codigoRegistro;
	 	  	form.descricaoTipoDebito.value = descricaoRegistro; 
      		form.descricaoTipoDebito.style.color = "#000000";		 	  	
    	} else if (tipoConsulta == 'servicoPerfilTipo') { 	
	 	  	form.idPerfilServico.value = codigoRegistro;
	 	  	form.descricaoPerfilServico.value = descricaoRegistro; 
      		form.descricaoPerfilServico.style.color = "#000000";	 	  	
		} else if (tipoConsulta == 'servicoTipoReferencia') {
			form.idTipoServicoReferencia.value = codigoRegistro;
			form.descricaoTipoServicoReferencia.value = descricaoRegistro; 
      		form.descricaoTipoServicoReferencia	.style.color = "#000000";				
			controlaTipoServicoReferenciaOnKeyUp();
		} else if (tipoConsulta == 'operacao') {
			form.idOperacao.value = codigoRegistro;
			form.descricaoOperacao.value = descricaoRegistro; 
	 	  	form.descricaoOperacao.style.color = "#000000";
		}
  	}	
  	
  	function recuperarDadosQuatroParametros(codigoRegistro, descricaoRegistro, codigoAuxiliar, tipoConsulta) {
  		if (tipoConsulta == 'atividade') {
	 		insertRowTableAtividades(codigoRegistro, descricaoRegistro, codigoAuxiliar);
  		} else if (tipoConsulta == 'material') {
	 		insertRowTableMateriais(codigoRegistro, descricaoRegistro, codigoAuxiliar);
 		}
  	}
  
	function limpar(tipo) {
		var form = document.InserirServicoTipoActionForm;
		if (tipo == 'tipoDebito') {
			form.idTipoDebito.value = "";
			form.descricaoTipoDebito.value = "";		
			reload();
		} else if (tipo == 'tipoCrebito') {
			form.tipoCredito.disabled = "true";		
			form.tipoCredito.selectedIndex = -1;
			reload();		
		} else if (tipo == 'perfil') {
			form.idPerfilServico.value = "";
			form.descricaoPerfilServico.value = ""; 
		} else if (tipo == 'referencia') {
			form.idTipoServicoReferencia.value = "";
			form.descricaoTipoServicoReferencia.value = ""; 
			form.idTipoServicoReferencia.disabled = false;
			form.lupaServicoTipoReferencia.disabled = false;
		} else if (tipo == 'operacao') {
			form.idOperacao.value = "";
			form.descricaoOperacao.value = ""; 
		}
	}  
	
	function reload() {
		var form = document.forms[0];
		form.action = "/gsan/exibirInserirServicoTipoAction.do";
		form.submit();
	}  
	
	function pesquisarTipoCredito() {
		var form = document.InserirServicoTipoActionForm;	
		form.idTipoDebito.value = "";
		form.descricaoTipoDebito.value = "";		
		reload();
	}  
	
	function popupTipoDebito() {
		chamarPopup('exibirPesquisarTipoDebitoAction.do', 'tipoDebito', null, null, 550, 760, '',document.forms[0].idTipoDebito);	
	}
	
	function popupPerfilServico() {
		chamarPopup('exibirPesquisarTipoPerfilServicoAction.do?limparCampos=ok', 'perfilServico', null, null, 550, 760, '',document.forms[0].idPerfilServico);
	}

	function popupOperacao() {
		chamarPopup('exibirPesquisarOperacaoAction.do', 'operacao', null, null, 550, 760, '',document.forms[0].idOperacao);
	}
	
	function popupServicoTipoReferencia() {
		chamarPopup('exibirPesquisarTipoServicoReferenciaAction.do?menu=sim', 'servicoTipoReferencia', null, null, 550, 760, '',document.forms[0].idTipoServicoReferencia);
	}
	
	function pesquisarTipoDebito() {
		var form = document.InserirServicoTipoActionForm;	
		form.tipoCredito.disabled = "true";		
		form.tipoCredito.selectedIndex = -1;
		reload();		
	}  
	
	function insertRowTableAtividades(codigoRegistro, descricaoRegistro, codigoAuxiliar) {
		var form = document.forms[0];	
		form.atividadeId.value = codigoRegistro;
		form.servicoTipoAtividadeOrdemExecucao.value = codigoAuxiliar;
		form.servicoTipoAtividadeDescricao.value = descricaoRegistro;
		form.servicoTipoAtividadeId.value = "$" + codigoRegistro + "$" + codigoAuxiliar + "$";
		form.method.value = "addServicoTipoAtividade";
		reload();
	}
	
	function insertRowTableMateriais(codigoRegistro, descricaoRegistro, codigoAuxiliar) {
		var form = document.forms[0];	
		form.materialId.value = codigoRegistro;
		form.servicoTipoMaterialQuantidadePadrao.value = codigoAuxiliar;
		form.servicoTipoMaterialDescricao.value = descricaoRegistro;
		form.servicoTipoMaterialId.value = "$" + codigoRegistro + "$" + codigoAuxiliar + "$";
		form.method.value = "addServicoTipoMaterial";
		reload();
	}	
	

	function removeRowTableServicoTipoReferencia(id) {
		var form = document.forms[0];	
		form.method.value = "removeRowTableServicoTipoReferencia";
		reload();
	}
	
	
	function removeRowTableAtividades(id) {
		var form = document.forms[0];	
		form.servicoTipoAtividadeId.value = id;
		form.method.value = "removeServicoTipoAtividade";
		reload();
	}

	function removeAllRowsTableAtividades() {
		var form = document.forms[0];	
		form.method.value = "removeAllServicoTipoAtividade";
		reload();
	}
	
	
	function removeRowTableMateriais(id) {
		var form = document.forms[0];	
		form.servicoTipoMaterialId.value = id;
		form.method.value = "removeServicoTipoMaterial";
		reload();
	}
	
	function removeAllRowsTableMateriais() {
		var form = document.forms[0];	
		form.method.value = "removeAllServicoTipoMaterial";
		reload();
	}
	
	function removeRowTableMotivoEncerramento(id) {
		var form = document.forms[0];	
		form.motivoEncerramentoId.value = id;
		form.method.value = "removeServicoTipoMotivoEncerramento";
		reload();
	}
	
	function removeAllRowsTableMotivoEncerramento() {
		var form = document.forms[0];	
		form.method.value = "removeAllServicoTipoMotivoEncerramento";
		reload();
	}
	
	function validaForm() {
	  	var form = document.forms[0];
	  	form.action = "/gsan/inserirServicoTipoAction.do";
		if(validateInserirServicoTipoActionForm(form) && validateCaracterEspecial(form)) {
		  	if(validaTodosRadioButton()) {		     		  		
				submeterFormPadrao(form);   		  
   	      	}
   	    }
	}
	
	function caracteresespeciais () {
    	this.aa = new Array("descricao", "Descri��o possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    }
	 
  	function validaTodosRadioButton() {
		var form = document.forms[0];
		
		if (!form.atualizacaoComercial[0].checked
				&& !form.atualizacaoComercial[1].checked
				&& !form.atualizacaoComercial[2].checked) {
			alert("Informe Indicador Atualiza��o Comercial.");		
			return false;
		}		
		<%--if (!form.pavimento[0].checked
				&& !form.pavimento[1].checked) {
			alert("Informe Indicador Pavimento.");		
			return false;
		}--%>
		
		
		if (!form.indicadorPavimentoRua[0].checked && !form.indicadorPavimentoRua[1].checked) {
			alert("Informe Indicador Pavimento de Rua.");		
			return false;
		}		
		
		if (!form.indicadorPavimentoCalcada[0].checked && !form.indicadorPavimentoCalcada[1].checked) {
			alert("Informe Indicador Pavimento de Cal�ada.");		
			return false;
		}		
		
		if (!form.indicativoTipoSevicoEconomias[0].checked && !form.indicativoTipoSevicoEconomias[1].checked) {
			alert("Informe Indicador de Quantidade de Economias.");		
			return false;
		}
				
		if (!form.servicoTerceirizado[0].checked
				&& !form.servicoTerceirizado[1].checked) {
			alert("Informe Indicador Servi�o Terceirizado.");		
			return false;
		}		
		if (!form.codigoServico[0].checked
				&& !form.codigoServico[1].checked) {
			alert("Informe C�digo do Tipo de Servi�o.");		
			return false;
		}		
		if (!form.atividadeUnica[0].checked
				&& !form.atividadeUnica[1].checked) {
			alert("Informe Atividade �nica.");		
			return false;
		}
		
		if (!form.indicadorVistoria[0].checked
				&& !form.indicadorVistoria[1].checked) {
			alert("Informe Indicador de Vistoria.");		
			return false;
		}		
		if (!form.indicadorFiscalizacaoInfracao[0].checked
				&& !form.indicadorFiscalizacaoInfracao[1].checked) {
			alert("Informe Indicador de Fiscaliza��o de Infra��o.");		
			return false;
		}			
		
		if (!form.indicadorInformacoesBoletimMedicao[0].checked &&	!form.indicadorInformacoesBoletimMedicao[1].checked){
   			alert("Informe Indicador de Informa��es de Boletim de Medi��o.");		
			return false;
   		}
		
	   	if (form.indicadorInformacoesBoletimMedicao[0].checked) {
   		
	   		if (!form.indicativoPavimento[0].checked &&	!form.indicativoPavimento[1].checked){
	   			alert("Informe Indicativo de Pavimento.");		
				return false;
	   		} 			

			if (!form.indicativoReposicaoAsfalto[0].checked && !form.indicativoReposicaoAsfalto[1].checked){
	   			alert("Informe Indicativo de Reposi��o de Asfalto.");		
				return false;
	   		} 			 			
			
			if (!form.indicativoReposicaoParalelo[0].checked && !form.indicativoReposicaoParalelo[1].checked){
	   			alert("Informe Indicativo de Reposi��o de Paralelo.");		
				return false;
	   		} 			 			
			
			if (!form.indicativoReposicaoCalcada[0].checked && !form.indicativoReposicaoCalcada[1].checked){
	   			alert("Informe Indicativo de Reposi��o de Cal�ada.");		
				return false;
	   		} 			
	   		
	   	}
	   		
	   	if(!form.indicadorOrdemSeletiva[0].checked && !form.indicadorOrdemSeletiva[1].checked){
	   		alert("Informe Indicador de ordem seletiva.");
	   		return false;
	   	}
	   	
	   	if (!form.indicadorEmpresaCobranca[0].checked && !form.indicadorEmpresaCobranca[1].checked){
	   		alert("Informe Indicador de libera��o para empresa de cobran�a gerar OS.");
	   		return false;
	   	}
	   	
	   	if (!form.indicadorInspecaoAnormalidade[0].checked && !form.indicadorInspecaoAnormalidade[1].checked){
	   		alert("Informe Indicador para gerar OS de inspe��o por anormalidade. ");
	   		return false;
	   	}
	   	
	   	if(!form.indicadorProgramacaoAutomatica[0].checked && !form.indicadorProgramacaoAutomatica[1].checked){
	   		alert("Informe Indicador de Programa��o autom�tica. ");
	   		return false;
	   	}
   		
   		if (!form.indicativoObrigatoriedadeAtividade[0].checked &&	!form.indicativoObrigatoriedadeAtividade[1].checked){
   			alert("Informe Indicador de Obrigatoriedade da Atividade.");		
			return false;
   		}
   		
   		if (!form.indicadorEncAutomaticoRAQndEncOS[0].checked && !form.indicadorEncAutomaticoRAQndEncOS[1].checked){
   			alert("Informe Indicador de Encerramento Autom�tico do RA.");		
			return false;
   		}
		
		return true;
   	}
   	
   	function inserirAtividade() {
		var form = document.forms[0];		
		if (form.atividadeUnica[0].checked) {
			alert('N�o � permitido informar atividades para Atividade �nica');
			return false;
		} else {
			if(form.atividadeUnica[1].checked){
	   			chamarPopup('exibirPesquisarServicoTipoAtividadeAction.do?limparCampos=ok&atividadeUnica=S&', 'servicoTipoAtividade', null, null, 300, 620, '','');
	   		}else{
	   			chamarPopup('exibirPesquisarServicoTipoAtividadeAction.do?limparCampos=ok&', 'servicoTipoAtividade', null, null, 300, 620, '','');
	   		}
   		}
   	}
	 
	function desfazer() {
		var form = document.forms[0];
		form.action = "/gsan/exibirInserirServicoTipoAction.do?action=reset";
		form.submit();
	}  
	
	function pesquisarTipoOSReferencia() {
		var form = document.InserirTipoRetornoOrdemServicoReferidaActionForm;
		if (form.servicoTipoReferencia.selectedIndex != 0) {
			reload();
		} else {
			form.trocaServico[0].checked = false;
			form.trocaServico[1].checked = false;			
			form.trocaServico[0].disabled = false;
			form.trocaServico[1].disabled = false;			
			form.situacao[0].checked = false;
			form.situacao[1].checked = false;			
			form.situacao[0].disabled = false;
			form.situacao[1].disabled = false;			
			form.atendimentoMotivoEncerramento.disabled = false;
			form.atendimentoMotivoEncerramento.selectedIndex = 0;			
		}
	}  

	function pesquisarAtendimentoMotivoEncerramento() {
		var form = document.InserirTipoRetornoOrdemServicoReferidaActionForm;
		if (form.atendimentoMotivoEncerramento.selectedIndex != 0) {
			if (form.deferimento[0].checked == true
					|| form.deferimento[1].checked == true) {
				reload();
			} else {
				alert("Informe Indicador de Deferimento.");		
				form.atendimentoMotivoEncerramento.selectedIndex = 0;
			}
		}
	}
	
	function controlaTipoServicoReferencia(valor){
		var form = document.forms[0];
		
		if(valor=='B'){
			form.idTipoServicoReferencia.disabled = true;
			form.lupaServicoTipoReferencia.disabled = true;
		}else{
			form.idTipoServicoReferencia.disabled = false;
			form.lupaServicoTipoReferencia.disabled = false;
		}
	}
	
	function controlaTipoServicoReferenciaOnKeyUp() {
		var form = document.forms[0];
		
		if(form.idTipoServicoReferencia.value == ''){
			form.submitServicoTipoReferencia.disabled = false;
		}else{
			form.submitServicoTipoReferencia.disabled = true;
		}
	}
	
	function  habilitaIndicativos() {
		var form = document.forms[0];	
	
		form.indicadorOrdemSeletiva[1].checked = true;
		
		if (form.indicadorInformacoesBoletimMedicao[0].checked) {
			//habilita

			//form.indicativoPavimento[0].checked = false;
			//form.indicativoPavimento[1].checked = false;			
			form.indicativoPavimento[0].disabled = false;
			form.indicativoPavimento[1].disabled = false;	

			//form.indicativoReposicaoAsfalto[0].checked = false;
			//form.indicativoReposicaoAsfalto[1].checked = false;			
			form.indicativoReposicaoAsfalto[0].disabled = false;
			form.indicativoReposicaoAsfalto[1].disabled = false;
			
			//form.indicativoReposicaoParalelo[0].checked = false;
			//form.indicativoReposicaoParalelo[1].checked = false;			
			form.indicativoReposicaoParalelo[0].disabled = false;
			form.indicativoReposicaoParalelo[1].disabled = false;
			
			//form.indicativoReposicaoCalcada[0].checked = false;
			//form.indicativoReposicaoCalcada[1].checked = false;			
			form.indicativoReposicaoCalcada[0].disabled = false;
			form.indicativoReposicaoCalcada[1].disabled = false;

			return false;
		} else {
			if(form.indicadorInformacoesBoletimMedicao[1].checked){
			//desabilita
			form.indicativoPavimento[0].checked = false;
			form.indicativoPavimento[1].checked = false;			
			form.indicativoPavimento[0].disabled = true;
			form.indicativoPavimento[1].disabled = true;	

			form.indicativoReposicaoAsfalto[0].checked = false;
			form.indicativoReposicaoAsfalto[1].checked = false;			
			form.indicativoReposicaoAsfalto[0].disabled = true;
			form.indicativoReposicaoAsfalto[1].disabled = true;
			
			form.indicativoReposicaoParalelo[0].checked = false;
			form.indicativoReposicaoParalelo[1].checked = false;			
			form.indicativoReposicaoParalelo[0].disabled = true;
			form.indicativoReposicaoParalelo[1].disabled = true;
			
			form.indicativoReposicaoCalcada[0].checked = false;
			form.indicativoReposicaoCalcada[1].checked = false;			
			form.indicativoReposicaoCalcada[0].disabled = true;
			form.indicativoReposicaoCalcada[1].disabled = true;
			
			return false;
	   		}
	
   		}
	
	}
	
	function  habilitaIndicativoProgramacao() {
		var form = document.forms[0];	
	
		if (form.indicadorProgramacaoAutomatica[0].checked) {
		
			form.indicadorProgramacaoAutomaticaValor.value = 1;
			
			//desabilita
			form.indicativoObrigatoriedadeAtividade[0].checked = true;
			form.indicativoObrigatoriedadeAtividade[1].checked = false;			
			form.indicativoObrigatoriedadeAtividade[0].disabled = true;
			form.indicativoObrigatoriedadeAtividade[1].disabled = true;	
			form.indicativoObrigatoriedadeAtividadeValor.value = 1;
			

			return false;
		} else {
			if(form.indicadorProgramacaoAutomatica[1].checked){
				
			form.indicadorProgramacaoAutomaticaValor.value = 2;
			
			form.indicativoObrigatoriedadeAtividade[0].checked = false;
			form.indicativoObrigatoriedadeAtividade[1].checked = false;			
			form.indicativoObrigatoriedadeAtividade[0].disabled = false;
			form.indicativoObrigatoriedadeAtividade[1].disabled = false;
			form.indicativoObrigatoriedadeAtividadeValor.value = '';	
				
			return false;
	   		}
	
   		}
	
	}
	
	 function atualizaValorIndicativoObrigatoriedadeAtividade (valor){
			var form = document.forms[0];	
	  
			form.indicativoObrigatoriedadeAtividadeValor.value = valor;	
	  }
	  
	  function verificaValorIndicativoObrigatoriedadeAtividade(){
			var form = document.forms[0];	
	  
			if (form.indicadorProgramacaoAutomaticaValor.value != null
				&& form.indicadorProgramacaoAutomaticaValor.value == '1') {
				
				form.indicativoObrigatoriedadeAtividade[0].disabled = true;
				form.indicativoObrigatoriedadeAtividade[1].disabled = true;	
				
			} else  if(form.indicadorProgramacaoAutomaticaValor.value != null
				&& form.indicadorProgramacaoAutomaticaValor.value == '2'){ 
				
				form.indicativoObrigatoriedadeAtividade[0].disabled = false;
				form.indicativoObrigatoriedadeAtividade[1].disabled = false;
				
			}
	  }
	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('descricao');controlaTipoServicoReferenciaOnKeyUp();habilitaIndicativos();verificaValorIndicativoObrigatoriedadeAtividade();">

<html:form action="/inserirServicoTipoAction.do"
	name="InserirServicoTipoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.InserirServicoTipoActionForm"
	method="post"
	onsubmit="return validateInserirServicoTipoActionForm(this);">

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

			<!-- centercoltext -->

			<html:hidden property="method" />

			<html:hidden property="atividadeId" />
			<html:hidden property="servicoTipoAtividadeId" />
			<html:hidden property="servicoTipoAtividadeDescricao" />
			<html:hidden property="servicoTipoAtividadeOrdemExecucao" />
			<html:hidden property="indicadorEnvioPesquisaSatisfacao" value="2"/>

			<html:hidden property="materialId" />
			<html:hidden property="servicoTipoMaterialId" />
			<html:hidden property="servicoTipoMaterialDescricao" />
			<html:hidden property="servicoTipoMaterialQuantidadePadrao" />
			<html:hidden property="motivoEncerramentoId" />
			
			<html:hidden property="indicadorProgramacaoAutomaticaValor" />
			<html:hidden property="indicativoObrigatoriedadeAtividadeValor" />

			<td width="625" valign="top" class="centercoltext">

			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Inserir Tipo de Servi&ccedil;o</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<table width="100%" height="311" border="0">
				<tr>
					<td height="10" colspan="4">Para adicionar um tipo de
					servi&ccedil;o, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td height="10" colspan="4">
					<hr>
					</td>
				</tr>

				<!-- Descricao do Tipo do Servi�o -->

				<tr>
					<td><strong>Descri&ccedil;&atilde;o do Tipo de Servi&ccedil;o:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="3"><span class="style2"> <html:text
						property="descricao" size="58" maxlength="50" /> </span></td>
				</tr>

				<!-- Descri��o do Tipo de Abreviado -->

				<tr>
					<td><strong>Descri&ccedil;&atilde;o do Tipo de Servi&ccedil;o Abreviado:</strong></td>
					<td colspan="3"><span class="style2"> <html:text
						property="abreviatura" size="30" maxlength="20" /> </span></td>
				</tr>

				<!-- Subgrupo -->

				<tr>
					<td><strong>Subgrupo:<font color="#FF0000">*</font></strong></td>
					<td colspan="3" align="left"><html:select property="subgrupo">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoSubgrupo"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>

				<!-- Indicador de Atualiza��o Comercial -->

				<tr>
					<td><strong><span class="style2">Indicador
					Atualiza&ccedil;&atilde;o Comercial:<font color="#FF0000">*</font></span></strong></td>
					<td align="left"><label> <html:radio
						property="atualizacaoComercial"
						value="<%=gcom.util.ConstantesSistema.ATUALIZACAO_AUTOMATICA + ""%>" />
					<strong>Autom�tica</strong></label></td>
					<td align="left"><label> <html:radio
						property="atualizacaoComercial"
						value="<%=gcom.util.ConstantesSistema.ATUALIZACAO_NENHUMA + ""%>" />
					<strong>N�o Atualiza</strong></label></td>
					<td align="left"><label> <html:radio
						property="atualizacaoComercial"
						value="<%=gcom.util.ConstantesSistema.ATUALIZACAO_POSTERIOR + ""%>" />
					<strong>Posterior</strong></label></td>
				</tr>

				<!-- Indicador de Pavimento -->

				<%-- <tr>
					<td><strong><span class="style2">Indicador de Pavimento:<font color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%">
						<label> 
							<html:radio	property="pavimento" value="1" /> <strong>Sim</strong>
						</label>
					</td>
					<td align="left">
						<label> 
							<html:radio property="pavimento" value="2" /><strong>N�o</strong>
						</label>
					</td>
				</tr> --%>
				
				<!-- Indicador de Pavimento de Rua -->
				
				<tr>
					<td><strong><span class="style2">Indicador de Pavimento de Rua:<font color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%">
						<label> 
							<html:radio	property="indicadorPavimentoRua" value="1" /> <strong>Sim</strong>
						</label>
					</td>
					<td align="left">
						<label> 
							<html:radio property="indicadorPavimentoRua" value="2" /><strong>N�o</strong>
						</label>
					</td>
				</tr>


				<!-- Indicador de Pavimento de Cal�ada -->
				
				<tr>
					<td><strong><span class="style2">Indicador de Pavimento de Cal�ada:<font color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%">
						<label> 
							<html:radio	property="indicadorPavimentoCalcada" value="1" /> <strong>Sim</strong>
						</label>
					</td>
					<td align="left">
						<label> 
							<html:radio property="indicadorPavimentoCalcada" value="2" /><strong>N�o</strong>
						</label>
					</td>
				</tr>

				<!-- Indicador de Quantidade de Economias -->
				<tr>
					<td><strong><span class="style2">Indicador de Quantidade de Economias:<font color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%">
						<label> 
							<html:radio	property="indicativoTipoSevicoEconomias" value="1" /><strong>Sim</strong>
						</label>
					</td>
					<td align="left">
						<label>
							<html:radio property="indicativoTipoSevicoEconomias" value="2" /> <strong>N�o</strong>
						</label>
					</td>
				</tr>


				<!-- Indicador de Servi�o Terceirizado -->

				<tr>
					<td><strong><span class="style2">Indicador Servi&ccedil;o
					Terceirizado:<font color="#FF0000">*</font></span></strong></td>
					<td align="left"><label> <html:radio property="servicoTerceirizado"
						value="1" /> <strong>Sim</strong></label></td>
					<td align="left"><label> <html:radio property="servicoTerceirizado"
						value="2" /> <strong>N�o</strong></label></td>
				</tr>

				<!-- C�digo do Tipo de Servi�o -->

				<tr>
					<td><strong><span class="style2">C&oacute;digo do Tipo de
					Servi&ccedil;o:<font color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%"><label> <html:radio
						property="codigoServico" value="O" /> <strong>Operacional</strong></label>
					</td>
					<td align="left"><label> <html:radio property="codigoServico"
						value="C" /> <strong>Comercial</strong></label></td>
				</tr>

				<!-- Valor do Servi�o -->

				<tr>
					<td><strong>Valor do Servi&ccedil;o:<font color="#FF0000">*</font></strong></td>
					<td colspan="3"><span class="style2"> <html:text property="valor"
						size="14" maxlength="14" style="text-align: right;" onkeyup="javaScript:formataValorMonetario(this, 8);"/>
					</span></td>
				</tr>

				<!-- Tempo M�dio de Execu��o -->

				<tr>
					<td><strong>Tempo M&eacute;dio de Execu&ccedil;&atilde;o:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="3"><span class="style2"> <html:text
						property="tempoMedioExecucao" size="5" maxlength="3" /> </span></td>
				</tr>

				<!-- Tipo de D�bito -->
				<logic:present	name="desabilitaCredito">
				<tr>
					<td><strong>Tipo de D&eacute;bito: </strong></td>
					<td colspan="3"><span class="style2"> <html:text
						property="idTipoDebito" size="4" maxlength="4"
						readonly="true"
						style="background-color:#EFEFEF; border:0; color:#000000" />
					<a> <img
						src="imagens/pesquisa.gif" width="23" height="21" border="0"
						title="Pesquisar"></a>
						<html:text property="descricaoTipoDebito" readonly="true"
							style="background-color:#EFEFEF; border:0; color:#000000"
							size="40" maxlength="40" />
						<a> <img
						src="imagens/limparcampo.gif" width="23" height="21" border="0"
						title="Apagar"></a> </span></td>
				</tr>
				</logic:present>			
				<logic:notPresent name="desabilitaCredito">
				<tr>
					<td><strong>Tipo de D&eacute;bito: </strong></td>
					<td colspan="3"><span class="style2"> <html:text
						property="idTipoDebito" size="4" maxlength="4" 
						onkeyup="validaEnterComMensagem(event, 'exibirInserirServicoTipoAction.do', 'idTipoDebito', 'Tipo do D�bito');" />
					<a href="javascript:popupTipoDebito()"> <img
						src="imagens/pesquisa.gif" width="23" height="21" border="0"
						title="Pesquisar"></a> <c:if
						test="${not empty servicoTipo.debitoTipo}">
						<html:text property="descricaoTipoDebito" readonly="true"
							style="background-color:#EFEFEF; border:0; color:#000000"
							size="40" maxlength="40" />
					</c:if> <c:if test="${empty servicoTipo.debitoTipo}">
						<html:text property="descricaoTipoDebito" readonly="true"
							style="background-color:#EFEFEF; border:0; color:#ff0000"
							size="40" maxlength="40" />
					</c:if> <a href="javascript:limpar('tipoDebito');"> <img
						src="imagens/limparcampo.gif" width="23" height="21" border="0"
						title="Apagar"></a> </span></td>
				</tr>				
				</logic:notPresent>
				<!-- Tipo de Cr�dito -->

				<tr>
					<td width="200"><strong><span class="style2">Tipo de Cr�dito:</span></strong></td>
					<td colspan="3" align="left"><c:if
						test="${not empty servicoTipo.debitoTipo}">
						<html:select property="tipoCredito" disabled="true">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoTipoCredito"
								labelProperty="descricao" property="id" />
						</html:select>
					</c:if> <c:if test="${empty servicoTipo.debitoTipo}">
						<html:select property="tipoCredito"
							onchange="javascript:pesquisarTipoCredito();">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoTipoCredito"
								labelProperty="descricao" property="id" />
						</html:select>
					</c:if></td>
				</tr>

				<!-- Prioridade do Servi�o -->

				<tr>
					<td width="200"><strong><span class="style2">Prioridade do
					Servi&ccedil;o:</span><font color="#FF0000">*</font></strong></td>
					<td colspan="3" align="left"><html:select
						property="prioridadeServico">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoPrioridadeServico"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>

				<!-- Perfil do Tipo de Servi�o -->

				<tr>
					<td><strong>Perfil do Tipo de Servi&ccedil;o:<font color="#FF0000">*</font></strong>
					</td>
					<td colspan="3"><span class="style2"> <html:text
						property="idPerfilServico" size="4" maxlength="4"
						onkeyup="validaEnterComMensagem(event, 'exibirInserirServicoTipoAction.do', 'idPerfilServico', 'Tipo do Perfil');" />
					<a href="javascript:popupPerfilServico();"> <img
						src="imagens/pesquisa.gif" width="23" height="21" border="0"
						title="Pesquisar"></a> <c:if
						test="${not empty servicoTipo.servicoPerfilTipo}">
						<html:text property="descricaoPerfilServico" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="40" maxlength="40" />
					</c:if> <c:if test="${empty servicoTipo.servicoPerfilTipo}">
						<html:text property="descricaoPerfilServico" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" maxlength="40" />
					</c:if> <a href="javascript:limpar('perfil');"> <img
						src="imagens/limparcampo.gif" width="23" height="21" border="0"
						title="Apagar"></a> </span></td>
				</tr>
				
				
				<!-- Operacao -->
				
				<tr>
					<td><strong>Opera&ccedil;&atilde;o:<font color="#FF0000">*</font></strong>
					</td>
					<td colspan="3"><span class="style2"> 
					
					<html:text
						property="idOperacao" size="4" maxlength="4"
						onkeyup="validaEnterComMensagem(event, 'exibirInserirServicoTipoAction.do', 'idOperacao', 'Operacao');" />
					<a href="javascript:popupOperacao();"> <img
						src="imagens/pesquisa.gif" width="23" height="21" border="0"
						title="Pesquisar"></a> 
						<c:if test="${not empty idOperacao}">
							<html:text property="descricaoOperacao" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="40" maxlength="40" />
						</c:if> 
						
						<c:if test="${empty idOperacao}">
							<html:text property="descricaoOperacao" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								size="40" maxlength="40" />
						</c:if> 
						<a href="javascript:limpar('operacao');"> 
							<img src="imagens/limparcampo.gif" width="23" height="21" border="0" title="Apagar">
						</a> 
						</span>
					</td>
				</tr>
				
				
				<!-- Tipo do Servi�o Refer�ncia -->

				<tr>
					<td><strong>Tipo de Servi&ccedil;o de Refer&ecirc;ncia: </strong></td>
					<td colspan="3"><span class="style2">
					<logic:notPresent name="servicoTipoReferencia">
						 <html:text
							property="idTipoServicoReferencia" size="4" maxlength="4"
							onkeyup="validaEnterComMensagem(event, 'exibirInserirServicoTipoAction.do', 'idTipoServicoReferencia', 'Tipo de Servi�o de Refer�ncia');controlaTipoServicoReferenciaOnKeyUp();" />
						<a href="javascript:popupServicoTipoReferencia();"><img
							src="imagens/pesquisa.gif" width="23" height="21" border="0"
							title="Pesquisar" id="lupaServicoTipoReferencia"></a> <c:if
							test="${not empty servicoTipo.servicoTipoReferencia}">
							<html:text property="descricaoTipoServicoReferencia"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color:#000000"
								size="30" maxlength="30" />
						</c:if> <c:if test="${empty servicoTipo.servicoTipoReferencia}">
							<html:text property="descricaoTipoServicoReferencia"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color:#ff0000"
								size="30" maxlength="40" />
						</c:if> <a href="javascript:limpar('referencia');controlaTipoServicoReferenciaOnKeyUp();"><img
							src="imagens/limparcampo.gif" width="23" height="21" border="0"
							title="Apagar"></a>
							
							<!-- adicionar servi�o tipo referencia -->
							<input type="button" name="submitServicoTipoReferencia"
								class="bottonRightCol" value="Adicionar"
								onclick="chamarPopup('exibirInserirTipoServicoReferenciaAction.do?semMenu=S', 'servicoTipoReferecia', null, null, 400, 660, '','');">
							
					</logic:notPresent> 
					<logic:present name="servicoTipoReferencia">
					 <html:text
							property="idTipoServicoReferencia" size="4" maxlength="4" disabled="true" />
							<img
							src="imagens/pesquisa.gif" width="23" height="21" border="0"
							title="Pesquisar" id="lupaServicoTipoReferencia"> 
							<html:text property="descricaoTipoServicoReferencia"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color:#000000"
								size="30" maxlength="40" value="${servicoTipoReferencia.descricao}"/>
							<a href="javascript:limpar('referencia');controlaTipoServicoReferenciaOnKeyUp();"><img
							src="imagens/limparcampo.gif" width="23" height="21" border="0"
							title="Apagar"></a>
							<!-- adicionar servi�o tipo referencia -->
							<input disabled="true" type="button" name="submitServicoTipoReferencia"
								class="bottonRightCol" value="Adicionar"
								onclick="chamarPopup('exibirInserirTipoServicoReferenciaAction.do?semMenu=S', 'servicoTipoReferecia', null, null, 400, 660, '','');">
					</logic:present>
					</span>
					
					</td>
				</tr>
				
				<!-- Indicador de Vistoria -->

				<tr>
					<td><strong><span class="style2">Indicador de Vistoria:<font
						color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%"><label> <html:radio
						property="indicadorVistoria" value="1"/> 
						<strong>Sim</strong></label>
					</td>
					<td align="left"><label> <html:radio property="indicadorVistoria"
						value="2" /> <strong>N�o</strong></label></td>
				</tr>
				
				<!-- Indicador de Fiscalizacao de Infra��o -->

				<tr>
					<td><strong><span class="style2">Indicador de Fiscaliza��o de Infra��o:<font
						color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%"><label> <html:radio
						property="indicadorFiscalizacaoInfracao" value="1" /> <strong>Sim</strong></label>
					</td>
					<td align="left"><label> <html:radio property="indicadorFiscalizacaoInfracao"
						value="2" /> <strong>N�o</strong></label></td>
				</tr>
				
				<!-- Indicador Permitir Alter��o valor -->

				<tr>
					<td><strong><span class="style2">Indicador Permitir Altera&ccedil;&atilde;o Valor:<font
						color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%"><label> <html:radio
						property="indicadorPermiteAlterarValor" value="1" /> <strong>Sim</strong></label>
					</td>
					<td align="left"><label> <html:radio property="indicadorPermiteAlterarValor"
						value="2" /> <strong>N�o</strong></label></td>
				</tr>
				
				<!-- Indicador de Informa��es de Boletim de Medi��o -->
				<tr>
					<td><strong><span class="style2">Indicador de Informa��es de Boletim de Medi��o:<font color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%"><label> 
						<html:radio	property="indicadorInformacoesBoletimMedicao" value="1" onclick ="habilitaIndicativos();"/> <strong>Sim</strong></label>	</td>
					<td align="left"><label> 
						<html:radio property="indicadorInformacoesBoletimMedicao" value="2" onclick="habilitaIndicativos();"/> <strong>N�o</strong></label></td>
				</tr>
				
				<!-- Indicador de Ordem Seletiva  -->
				<tr>
					<td><strong><span class="style2">Indicador de Ordem Seletiva:<font color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%"><label> 
						<html:radio	property="indicadorOrdemSeletiva" value="1" /> <strong>Sim</strong></label></td>
					<td align="left"><label> 
						<html:radio property="indicadorOrdemSeletiva" value="2" /> <strong>N�o</strong></label></td>
				</tr>
				
				<!-- Indicativo de Pavimento  -->
				<tr>
					<td><strong><span class="style2">Indicativo de Pavimento:</span></strong></td>
					<td align="left" width="20%"><label> 
						<html:radio	property="indicativoPavimento" value="1" /> <strong>Sim</strong></label>	</td>
					<td align="left"><label> 
						<html:radio property="indicativoPavimento" value="2" /> <strong>N�o</strong></label></td>
				</tr>
				
				<!-- Indicativo de Reposi��o de Asfalto  -->
				<tr>
					<td><strong><span class="style2">Indicativo de Reposi��o de Asfalto:</span></strong></td>
					<td align="left" width="20%"><label> 
						<html:radio	property="indicativoReposicaoAsfalto" value="1" /> <strong>Sim</strong></label>	</td>
					<td align="left"><label> 
						<html:radio property="indicativoReposicaoAsfalto" value="2" /> <strong>N�o</strong></label></td>
				</tr>
				
				<!-- Indicativo de Reposi��o de Paralelo  -->
				<tr>
					<td><strong><span class="style2">Indicativo de Reposi��o de Paralelo:</span></strong></td>
					<td align="left" width="20%"><label> 
						<html:radio	property="indicativoReposicaoParalelo" value="1" /> <strong>Sim</strong></label>	</td>
					<td align="left"><label> 
						<html:radio property="indicativoReposicaoParalelo" value="2" /> <strong>N�o</strong></label></td>
				</tr>
				
				<!-- Indicativo de Reposi��o de Cal�ada  -->
				<tr>
					<td><strong><span class="style2">Indicativo de Reposi��o de Cal�ada:</span></strong></td>
					<td align="left" width="20%"><label> 
						<html:radio	property="indicativoReposicaoCalcada" value="1" /> <strong>Sim</strong></label>	</td>
					<td align="left"><label> 
						<html:radio property="indicativoReposicaoCalcada" value="2" /> <strong>N�o</strong></label></td>
				</tr>
				
				<!-- Indicador de libera��o para empresa de cobran�a gerar OS -->
				<tr>
					<td><strong><span class="style2">Indicador de Libera��o para Empresa de Cobran�a Gerar OS:<font color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%"><label> 
						<html:radio	property="indicadorEmpresaCobranca" value="1" /> <strong>Sim</strong></label></td>
					<td align="left"><label> 
						<html:radio property="indicadorEmpresaCobranca" value="2" /> <strong>N�o</strong></label></td>
				</tr>
				
				<!-- Indicador para gerar OS de inspe��o por anormalidade -->
				<tr>
					<td><strong><span class="style2">Indicador para Gerar OS de Inspe��o por Anormalidade:<font color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%"><label> 
						<html:radio	property="indicadorInspecaoAnormalidade" value="1" /> <strong>Sim</strong></label></td>
					<td align="left"><label> 
						<html:radio property="indicadorInspecaoAnormalidade" value="2" /> <strong>N�o</strong></label></td>
				</tr>
				
				<!-- Indicador de programa��o autom�tica -->
				<tr>
					<td><strong><span class="style2">Indicador de Programa��o Autom�tica:<font color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%"><label>
						<html:radio property="indicadorProgramacaoAutomatica" value="1" onclick ="habilitaIndicativoProgramacao();"/> <strong>Sim</strong></label></td>
					<td align="left"><label>
						<html:radio property="indicadorProgramacaoAutomatica" value="2" onclick ="habilitaIndicativoProgramacao();"/> <strong>N�o</strong></label></td>
				</tr>
				
				<!-- Indicativo de Obrigatoriedade de informa��o da atividade  -->
				<tr>
					<td><strong><span class="style2">Indicador de Obrigatoriedade de Informa��o da Atividade:<font color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%"><label> 
						<html:radio	property="indicativoObrigatoriedadeAtividade" value="1" onclick="atualizaValorIndicativoObrigatoriedadeAtividade('1');"/> <strong>Sim</strong></label>	</td>
					<td align="left"><label> 
						<html:radio property="indicativoObrigatoriedadeAtividade" value="2" onclick="atualizaValorIndicativoObrigatoriedadeAtividade('2');"/> <strong>N�o</strong></label></td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>Indicador de encerramento autom�tico do RA no encerramento de sua �ltima/�nica OS pendente :<font color="#FF0000">*</font></strong>
					</td>
					
					<td align="left"><strong> 
							<html:radio property="indicadorEncAutomaticoRAQndEncOS" value="1" /> Sim 
						</strong>
					</td>
					<td align="left">
						<strong>
							<html:radio property="indicadorEncAutomaticoRAQndEncOS" value="2" /> N&atilde;o 
						</strong>
					</td>
				</tr>
				
				<!-- Atividade �nica -->

				<tr>
					<td><strong><span class="style2">Atividade &Uacute;nica:<font
						color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%"><label> <html:radio
						property="atividadeUnica" value="1"
						onclick="javascript:removeAllRowsTableAtividades();" /> <strong>Sim</strong></label>
					</td>
					<td align="left"><label> <html:radio property="atividadeUnica"
						value="2" /> <strong>N�o</strong></label></td>
				</tr>
				
				<!-- Atividades do Tipo de Servi�o -->

				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3"><strong> <font color="#000000">Atividades do Tipo
					de Servi&ccedil;o </font><font color="#FF0000">*</font></strong></td>
					<td align="right">
					<div align="right"><input type="button" name="Submit24"
						class="bottonRightCol" value="Adicionar"
						onclick="inserirAtividade();"></div>
					</td>
				</tr>
				<tr>
					<td colspan="4"><strong> <font color="#FF0000"></font></strong>
					<div align="left">
					<table width="100%" id="tableAtividades" align="center"
						bgcolor="#99CCFF">
						<!--corpo da segunda tabela-->
						<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
							<td width="14%">
							<div align="center"><strong>Remover</strong></div>
							</td>
							<td>
							<div align="center"><strong>Descri&ccedil;&atilde;o dos
							Atividades </strong></div>
							</td>
							<td>
							<div align="center"><strong>Ordem de Execu&ccedil;&atilde;o </strong></div>
							</td>
						</tr>
						<tbody>
						<tr>

							<c:forEach var="servicoTipoAtividade"
								items="${InserirServicoTipoActionForm.servicoTipoAtividades}"
								varStatus="i">

								<c:if test="${i.count%2 == '1'}">
									<tr bgcolor="#FFFFFF">
								</c:if>
								<c:if test="${i.count%2 == '0'}">
									<tr bgcolor="#cbe5fe">
								</c:if>
								<td>
								<div align="center"><a
									href="javascript:if(confirm('Confirma remo��o?')){removeRowTableAtividades('$${servicoTipoAtividade.atividade.id}$${servicoTipoAtividade.numeroExecucao}$');}">
								<img src="imagens/Error.gif" width="14" height="14" border="0"
									title="Remover"> </a></div>
								</td>
								<td>
								<div align="left">${servicoTipoAtividade.atividade.descricao}</div>
								</td>
								<td>
								<div align="center">${servicoTipoAtividade.numeroExecucao}</div>
								</td>
							</c:forEach>
							</tr>
						</tbody>
					</table>
					</div>
					</td>
				</tr>

				<!-- Materiais do Tipo de Servi�o -->

				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3"><strong> <font color="#FF0000"></font></strong>
					<div align="left"><strong><font color="#000000">Materiais </font><font
						color="#000000"> do Tipo de Servi&ccedil;o</font></strong></div>
					</td>
					<td>
					<div align="right"><input type="button" name="Submit242"
						class="bottonRightCol" value="Adicionar"
						onclick="chamarPopup('exibirPesquisarServicoTipoMaterialAction.do?limpar=S', 'servicoTipoMaterial', null, null, 300, 620, '','');">
					</div>
					</td>
				</tr>
				<tr>
					<td colspan="4"><strong> <font color="#FF0000"></font></strong>
					<div align="left">
					<table width="100%" id="tableMateriais" align="center"
						bgcolor="#99CCFF">
						<!--corpo da segunda tabela-->
						<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
							<td width="14%">
							<div align="center"><strong>Remover</strong></div>
							</td>
							<td width="55%">
							<div align="center"><strong>Descri&ccedil;&atilde;o dos Materiais
							</strong></div>
							</td>
							<td width="31%">
							<div align="center"><strong>Quantidade Padr&atilde;o </strong></div>
							</td>
						</tr>
						<tbody>

							<c:forEach var="servicoTipoMaterial"
								items="${InserirServicoTipoActionForm.servicoTipoMateriais}"
								varStatus="i">
								<c:if test="${i.count%2 == '1'}">
									<tr bgcolor="#FFFFFF">
								</c:if>
								<c:if test="${i.count%2 == '0'}">
									<tr bgcolor="#cbe5fe">
								</c:if>
								<td>
								<div align="center"><a
									href="javascript:if(confirm('Confirma remo��o?')){removeRowTableMateriais('$${servicoTipoMaterial.material.id}$');}">
								<img src="imagens/Error.gif" width="14" height="14" border="0"
									title="Remover"> </a></div>
								</td>
								<td>
								<div align="left">${servicoTipoMaterial.material.descricao}</div>
								</td>
								<td>
								<div align="center">${servicoTipoMaterial.quantidadePadrao}</div>
								</td>
								
							</c:forEach>

						</tbody>
					</table>
					</div>
					</td>
				</tr>
				
				<!-- Motivo de Encerramento -->

				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3"><strong> <font color="#FF0000"></font></strong>
					<div align="left"><strong><font color="#000000">Descri&ccedil;&atilde;o
					do Motivo do Encerramento:</font></strong></div>
					</td>
						
					<td>
					<div align="right"><input type="button" name="Submit242"
						class="bottonRightCol" value="Adicionar"
						onclick="chamarPopup('exibirPesquisarServicoTipoMotivoEncerramentoAction.do?limpar=S', 'servicoTipoMotivoEncerramento', null, null, 300, 620, '','');">
					</div>
					</td>
				</tr>
				<tr>
					<td colspan="4"><strong> <font color="#FF0000"></font></strong>
					<div align="left">
					<table width="100%" id="tableMotivoEncerramento" align="center"
						bgcolor="#99CCFF">
						<!--corpo da segunda tabela-->
						<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
							<td width="16%">
							<div align="center"><strong>Remover</strong></div>
							</td>
							<td width="73%">
							<div align="center"><strong>Descri&ccedil;&atilde;o do Motivo de Encerramento</strong></div>
							</td>
						</tr>
						<tbody>
							<tr>
								<logic:present name="motivosEncerramentoEscolhidos">
									<c:forEach var="motivoEncerramento"
										items="${motivosEncerramentoEscolhidos}"
										varStatus="i">
										<c:if test="${i.count%2 == '1'}">
											<tr bgcolor="#FFFFFF">
										</c:if>
										<c:if test="${i.count%2 == '0'}">
											<tr bgcolor="#cbe5fe">
										</c:if>
										<td>
										<div align="center"><a
											href="javascript:if(confirm('Confirma remo��o?')){removeRowTableMotivoEncerramento('${motivoEncerramento.id}');}">
										<img src="imagens/Error.gif" width="14" height="14" border="0"
											title="Remover"> </a></div>
										</td>
										<td>
										<div align="left">${motivoEncerramento.descricao}</div>
										</td>
									</c:forEach>
								</logic:present>
							</tbody>
							
					</table>
					</div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>

				<!-- Bot�es -->

				<tr>
					<td align="left"><input type="button" name="ButtonReset"
						class="bottonRightCol" value="Desfazer"
						onClick="javascript:desfazer();"> <input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td colspan="3" align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Inserir" onclick="validaForm();"></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</html:html>
