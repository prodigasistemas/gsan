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
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>	
<html:javascript staticJavascript="false"  formName="AtualizarTipoServicoActionForm" dynamicJavascript="true" />

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
    	} else if (tipoConsulta == 'servicoPerfilTipo') { 	
	 	  	form.idPerfilServico.value = codigoRegistro;
	 	  	form.descricaoPerfilServico.value = descricaoRegistro; 
		} else if (tipoConsulta == 'servicoTipoReferencia') {
			form.idTipoServicoReferencia.value = codigoRegistro;
			form.descricaoTipoServicoReferencia.value = descricaoRegistro; 
			controlaTipoServicoReferenciaOnKeyUp();
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
		var form = document.forms[0];
		if (tipo == 'tipoDebito') {
			form.idTipoDebito.value = "";
			form.descricaoTipoDebito.value = "";		
			reload();
		} else if (tipo == 'tipoCrebito') {
			form.tipoCredito.disabled = "true";		
			form.tipoCredito.selectedIndex = -1;
			reload();		
		} else if (tipo == 'perfil') {
			form.perfilServico.value = "";
			form.descricaoPerfilServico.value = ""; 
		} else if (tipo == 'referencia') {
			form.idTipoServicoReferencia.value = "";
			form.descricaoTipoServicoReferencia.value = ""; 
			form.idTipoServicoReferencia.disabled = false;
			form.lupaServicoTipoReferencia.disabled = false;
		}
	}  
	
	function reload() {
		var form = document.forms[0];
		form.action = "/gsan/exibirAtualizarTipoServicoAction.do";
		form.submit();
	}  
	
	function pesquisarTipoCredito() {
		var form = document.forms[0];	
		form.idTipoDebito.value = "";
		form.descricaoTipoDebito.value = "";		
		reload();
	}  
	
	function popupTipoDebito() {
		chamarPopup('exibirPesquisarTipoDebitoAction.do', 'tipoDebito', null, null, 550, 760, '',document.forms[0].idTipoDebito);	
	}
	
	function popupPerfilServico() {
		chamarPopup('exibirPesquisarTipoPerfilServicoAction.do', 'perfilServico', null, null, 550, 760, '',document.forms[0].perfilServico);
	}
	
	function popupServicoTipoReferencia() {
		chamarPopup('exibirPesquisarTipoServicoReferenciaAction.do?menu=sim', 'referencia', null, null, 550, 760, '',document.forms[0].idTipoServicoReferencia);
	}
	
	function pesquisarTipoDebito() {
		var form = document.forms[0];	
		form.tipoCredito.disabled = "true";		
		form.tipoCredito.selectedIndex = -1;
		reload();		
	}  
	
	function insertRowTableAtividades(codigoRegistro, descricaoRegistro, codigoAuxiliar) {
		var form = document.forms[0];	
		form.idAtividade.value = codigoRegistro;
		form.servicoTipoAtividadeOrdemExecucao.value = codigoAuxiliar;
		form.descricaoAtividadeTipoServico.value = descricaoRegistro;
		form.idAtividadeTipoServico.value = "$" + codigoRegistro + "$" + codigoAuxiliar + "$";
		form.method.value = "addServicoTipoAtividade";
		reload();
	}
	
	function insertRowTableMateriais(codigoRegistro, descricaoRegistro, codigoAuxiliar) {
		var form = document.forms[0];	
		form.idMaterial.value = codigoRegistro;
		form.servicoTipoMaterialQuantidadePadrao.value = codigoAuxiliar;
		form.descricaoMaterialTipoServico.value = descricaoRegistro;
		form.idMaterialTipoServico.value = "$" + codigoRegistro + "$" + codigoAuxiliar + "$";
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
		form.idAtividadeTipoServico.value = id;
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
		form.idMaterialTipoServico.value = id;
		form.method.value = "removeServicoTipoMaterial";
		reload();
	}
	function removeAllRowsTableMateriais() {
		var form = document.forms[0];	
		form.method.value = "removeAllServicoTipoMaterial";
		reload();
	}
	
	function removeRowTableMotivos(id){
		var form = document.forms[0];
		form.action = "/gsan/exibirPesquisarMotivoDeEncerramentoAction.do?idRemover="+id;
		form.submit();
	}
	
	function validaForm() {
	  	var form = document.forms[0];
	  	form.action = "/gsan/atualizarTipoServicoAction.do";
		if(validateAtualizarTipoServicoActionForm(form)) {
		  	if(validaTodosRadioButton()) {		     		  		
				submeterFormPadrao(form);   		  
   	      	}
   	    }
	}
	 
  	function validaTodosRadioButton() {
		var form = document.forms[0];
		
		if (!form.atualizacaoComercial[0].checked
				&& !form.atualizacaoComercial[1].checked
				&& !form.atualizacaoComercial[2].checked) {
			alert("Informe Indicador Atualização Comercial.");		
			return false;
		}		
<%--		if (!form.pavimento[0].checked
				&& !form.pavimento[1].checked) {
			alert("Informe Indicador Pavimento.");		
			return false;
		}		
--%>		
		if (!form.indicadorPavimentoRua[0].checked && !form.indicadorPavimentoRua[1].checked) {
			alert("Informe Indicador Pavimento de Rua.");		
			return false;
		}		
	
		if (!form.indicadorPavimentoCalcada[0].checked && !form.indicadorPavimentoCalcada[1].checked) {
			alert("Informe Indicador Pavimento de Calçada.");		
			return false;
		}		

		if (!form.servicoTerceirizado[0].checked
				&& !form.servicoTerceirizado[1].checked) {
			alert("Informe Indicador Serviço Terceirizado.");		
			return false;
		}		
		if (!form.codigoServico[0].checked
				&& !form.codigoServico[1].checked) {
			alert("Informe Código do Tipo de Serviço.");		
			return false;
		}		
		if (!form.indicadorAtividadeUnica[0].checked
				&& !form.indicadorAtividadeUnica[1].checked) {
			alert("Informe Atividade Única.");		
			return false;
		}
		
		if (!form.indicadorVistoria[0].checked
				&& !form.indicadorVistoria[1].checked) {
			alert("Informe Indicador de Vistoria.");		
			return false;
		}		
		if (!form.indicadorFiscalizacaoInfracao[0].checked
				&& !form.indicadorFiscalizacaoInfracao[1].checked) {
			alert("Informe Indicador de Fiscalização de Infração.");		
			return false;
		}
		
		if (!form.indicativoObrigatoriedadeAtividade[0].checked &&	!form.indicativoObrigatoriedadeAtividade[1].checked){
   			alert("Informe Indicador de Obrigatoriedade da Atividade.");		
			return false;
   		}
   		
   		if(!form.indicadorInspecaoAnormalidade[0].checked && !form.indicadorInspecaoAnormalidade[1].checked){
   			alert("Informe Indicador de Inspeção de Anormalidade");
   			return false;
   		}
   		
   		if(!form.indicadorProgramacaoAutomatica[0].checked && !form.indicadorProgramacaoAutomatica[1].checked){
   			alert("Informe Indicador de Programação automática");
   			return false;
   		}
   					
		return true;
   	}
   	
   	function inserirAtividade() {
		var form = document.forms[0];		
		if (form.indicadorAtividadeUnica[0].checked) {
			alert('Não é permitido informar atividades para Atividade Única');
			return false;
		} else {
   			chamarPopup('exibirPesquisarServicoTipoAtividadeAction.do?limparCampos=ok', 'servicoTipoAtividade', null, null, 300, 620, '','');
   		}
   	}
	 
	function desfazer() {
		var form = document.forms[0];
		form.action = "/gsan/exibirFiltrarTipoServicoAction.do?menu=sim";
		form.submit();
	}  
	
	function pesquisarTipoOSReferencia() {
		var form = document.forms[0];
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
		var form = document.forms[0];
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
		
			//desabilita
			form.indicativoObrigatoriedadeAtividade[0].checked = true;
			form.indicativoObrigatoriedadeAtividade[1].checked = false;			
			form.indicativoObrigatoriedadeAtividade[0].disabled = true;
			form.indicativoObrigatoriedadeAtividade[1].disabled = true;	
			form.indicativoObrigatoriedadeAtividadeValor.value = 1;
			

			return false;
		} else {
			if(form.indicadorProgramacaoAutomatica[1].checked){
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

<body leftmargin="5" topmargin="5" onload="setarFoco('descricao');controlaTipoServicoReferenciaOnKeyUp();verificaValorIndicativoObrigatoriedadeAtividade();">

<html:form action="/atualizarTipoServicoAction.do"
	name="AtualizarTipoServicoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.AtualizarTipoServicoActionForm"
	method="post"
	onsubmit="return validateAtualizarTipoServicoActionForm(this);">

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

			<html:hidden property="idAtividade" />
			<html:hidden property="idAtividadeTipoServico" />
			<html:hidden property="descricaoAtividadeTipoServico" />
			<html:hidden property="servicoTipoAtividadeOrdemExecucao" />

			<html:hidden property="idMaterial" />
			<html:hidden property="idMaterialTipoServico" />
			<html:hidden property="indicadorServicoOrdemSeletiva"/>
			<html:hidden property="indicadorEnvioPesquisaSatisfacao"/>
			<html:hidden property="descricaoMaterialTipoServico" />
			<html:hidden property="servicoTipoMaterialQuantidadePadrao" />
			
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
					<td class="parabg">Atualizar Tipo de Servi&ccedil;o</td>
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
						&nbsp;
					</td>
				</tr>

				<!-- Descricao do Tipo do Serviço -->

				<tr>
					<td><strong>Descri&ccedil;&atilde;o do Tipo de Servi&ccedil;o:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="3"><span class="style2"> <html:text
						property="descricao" size="58" maxlength="50" /> </span></td>
				</tr>

				<!-- Descrição do Tipo de Abreviado -->

				<tr>
					<td><strong>Descri&ccedil;&atilde;o do Tipo de Abreviado:</strong></td>
					<td colspan="3"><span class="style2"> <html:text
						property="abreviada" size="30" maxlength="20" /> </span></td>
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

				<!-- Indicador de Atualização Comercial -->

				<tr>
					<td><strong><span class="style2">Indicador
					Atualiza&ccedil;&atilde;o Comercial:<font color="#FF0000">*</font></span></strong></td>
					<td align="left"><label> <html:radio
						property="atualizacaoComercial"
						value="<%=gcom.util.ConstantesSistema.ATUALIZACAO_AUTOMATICA + ""%>" />
					<strong>Automática</strong></label></td>
					<td align="left"><label> <html:radio
						property="atualizacaoComercial"
						value="<%=gcom.util.ConstantesSistema.ATUALIZACAO_NENHUMA + ""%>" />
					<strong>Não Atualiza</strong></label></td>
					<td align="left"><label> <html:radio
						property="atualizacaoComercial"
						value="<%=gcom.util.ConstantesSistema.ATUALIZACAO_POSTERIOR + ""%>" />
					<strong>Posterior</strong></label></td>
				</tr>

<%-- 				<!-- Indicador de Pavimento -->

				<tr>
					<td><strong><span class="style2">Indicador de Pavimento:<font
						color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%"><label> <html:radio
						property="pavimento" value="1" /> <strong>Sim</strong></label></td>
					<td align="left"><label> <html:radio property="pavimento" value="2" />
					<strong>Não</strong></label></td>
				</tr>
--%>



				<!-- Indicador de Pavimento de rua -->

				<tr>
					<td><strong><span class="style2">Indicador de Pavimento de Rua:<font color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%">
						<label> 
							<html:radio	property="indicadorPavimentoRua" value="1" /><strong>Sim</strong>
						</label>
					</td>
					<td align="left">
						<label> 
							<html:radio property="indicadorPavimentoRua" value="2" /><strong>Não</strong>
						</label>
					</td>
				</tr>


				<!-- Indicador de Pavimento de calçada -->

				<tr>
					<td><strong><span class="style2">Indicador de Pavimento de Calçada:<font color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%">
						<label> 
							<html:radio	property="indicadorPavimentoCalcada" value="1" /><strong>Sim</strong>
						</label>
					</td>
					<td align="left">
						<label> 
							<html:radio property="indicadorPavimentoCalcada" value="2" /><strong>Não</strong>
						</label>
					</td>
				</tr>

				<!-- Indicativo de Tipo Serviço por Economias -->
				<tr>
					<td><strong><span class="style2">Indicador de Quantidade de Economias:<font color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%">
						<label> 
							<html:radio	property="indicativoTipoSevicoEconomias" value="1" /><strong>Sim</strong>
						</label>
					</td>
					<td align="left">
						<label>
							<html:radio property="indicativoTipoSevicoEconomias" value="2" /> <strong>Não</strong>
						</label>
					</td>
				</tr>

				<!-- Indicador de Serviço Terceirizado -->

				<tr>
					<td><strong><span class="style2">Indicador Servi&ccedil;o
					Terceirizado:<font color="#FF0000">*</font></span></strong></td>
					<td align="left"><label> <html:radio property="servicoTerceirizado"
						value="1" /> <strong>Sim</strong></label></td>
					<td align="left"><label> <html:radio property="servicoTerceirizado"
						value="2" /> <strong>Não</strong></label></td>
				</tr>

				<!-- Código do Tipo de Serviço -->

				<tr>
					<td><strong><span class="style2">C&oacute;digo do Tipo de
					Servi&ccedil;o:<font color="#FF0000">*</font></span></strong></td>
					<td align="left" width="25%"><label> <html:radio
						property="codigoServico" value="O" /> <strong>Operacional</strong></label>
					</td>
					<td align="left" width="25%"><label> <html:radio property="codigoServico"
						value="C" /> <strong>Comercial</strong></label></td>
				</tr>

				<!-- Valor do Serviço -->

				<tr>
					<td><strong>Valor do Servi&ccedil;o:<font color="#FF0000">*</font></strong></td>
					<td colspan="3"><span class="style2"> <html:text property="valorServico"
						size="14" maxlength="14" style="text-align: right;" onkeyup="javaScript:formataValorMonetario(this, 8);"/>
					</span></td>
				</tr>

				<!-- Tempo Médio de Execução -->

				<tr>
					<td><strong>Tempo M&eacute;dio de Execu&ccedil;&atilde;o:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="3"><span class="style2"> <html:text
						property="tempoMedioExecucao" size="8" maxlength="8" /> </span></td>
				</tr>

				<!-- Tipo de Débito -->

				<tr>
					<td><strong>Tipo de D&eacute;bito: </strong></td>
					<td colspan="3"><span class="style2"> <html:text
						property="idTipoDebito" size="4" maxlength="4"
						onkeyup="validaEnterComMensagem(event, 'exibirAtualizarTipoServicoAction.do', 'idTipoDebito', 'Tipo do Débito');" />
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

				<!-- Tipo de Crédito -->

				<tr>
					<td width="200"><strong><span class="style2">Tipo de Crédito:</span></strong></td>
					<td colspan="3" align="left"><c:if
						test="${not empty servicoTipo.creditoTipo}">
						<html:select property="idTipoCredito" disabled="true">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoCreditoTipo"
								labelProperty="descricao" property="id" />
						</html:select>
					</c:if> <c:if test="${empty servicoTipo.creditoTipo}">
						<html:select property="idTipoCredito"
							onchange="javascript:pesquisarTipoCredito();">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoCreditoTipo"
								labelProperty="descricao" property="id" />
						</html:select>
					</c:if></td>
				</tr>

				<!-- Prioridade do Serviço -->

				<tr>
					<td width="200"><strong><span class="style2">Prioridade do
					Servi&ccedil;o:</span><font color="#FF0000">*</font></strong></td>
					<td colspan="3" align="left"><html:select
						property="idPrioridadeServico">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoPrioridadeServico"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>

				<!-- Perfil do Tipo de Serviço -->

				<tr>
					<td><strong>Perfil do Tipo de Servi&ccedil;o:<font color="#FF0000">*</font></strong>
					</td>
					<td colspan="3"><span class="style2"> <html:text
						property="perfilServico" size="4" maxlength="4"
						onkeyup="validaEnterComMensagem(event, 'exibirAtualizarTipoServicoAction.do', 'perfilServico', 'Tipo do Perfil');" />
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

				<!-- Tipo do Serviço Referência -->

				<tr>
					<td><strong>Tipo de Servi&ccedil;o de Refer&ecirc;ncia: </strong></td>
					<td colspan="3"><span class="style2">
					<logic:notPresent name="servicoTipoReferencia">
						 <html:text
							property="idTipoServicoReferencia" size="4" maxlength="4"
							onkeyup="validaEnterComMensagem(event, 'exibirAtualizarTipoServicoAction.do', 'idTipoServicoReferencia', 'Tipo de Serviço de Referência');controlaTipoServicoReferenciaOnKeyUp();" />
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
							
							<!-- adicionar serviço tipo referencia -->
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
							<!-- adicionar serviço tipo referencia -->
							<input disabled="true" type="button" name="submitServicoTipoReferencia"
								class="bottonRightCol" value="Adicionar"
								onclick="chamarPopup('exibirInserirTipoServicoReferenciaAction.do?semMenu=S', 'servicoTipoReferecia', null, null, 400, 660, '','');">
					</logic:present>
					</span>
					
					</td>
				</tr>
				
				<!-- Indicador Permitir Alterção valor -->

				<tr>
					<td><strong><span class="style2">Indicador Permitir Altera&ccedil;&atilde;o Valor:<font
						color="#FF0000">*</font></span></strong></td>
					<td align="left" width="25%"><label> <html:radio
						property="indicadorPermiteAlterarValor" value="1" /> <strong>Sim</strong></label>
					</td>
					<td align="left"><label> <html:radio property="indicadorPermiteAlterarValor"
						value="2" /> <strong>Não</strong></label></td>
				</tr>
				
				<!-- Indicador de Informações de Boletim de Medição -->
				<tr>
					<td><strong><span class="style2">Indicador de Informações de Boletim de Medição:<font color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%"><label> 
						<html:radio	property="indicadorInformacoesBoletimMedicao" value="1" onclick ="habilitaIndicativos();"/> <strong>Sim</strong></label>	</td>
					<td align="left"><label> 
						<html:radio property="indicadorInformacoesBoletimMedicao" value="2" onclick="habilitaIndicativos();"/> <strong>Não</strong></label></td>
				</tr>
				
				<!-- Indicativo de Pavimento  -->
				<tr>
					<td><strong><span class="style2">Indicativo de Pavimento:</span></strong></td>
					<td align="left" width="20%"><label> 
						<html:radio	property="indicativoPavimento" value="1" /> <strong>Sim</strong></label>	</td>
					<td align="left"><label> 
						<html:radio property="indicativoPavimento" value="2" /> <strong>Não</strong></label></td>
				</tr>
				
				<!-- Indicativo de Reposição de Asfalto  -->
				<tr>
					<td><strong><span class="style2">Indicativo de Reposição de Asfalto:</span></strong></td>
					<td align="left" width="20%"><label> 
						<html:radio	property="indicativoReposicaoAsfalto" value="1" /> <strong>Sim</strong></label>	</td>
					<td align="left"><label> 
						<html:radio property="indicativoReposicaoAsfalto" value="2" /> <strong>Não</strong></label></td>
				</tr>
				
				<!-- Indicativo de Reposição de Paralelo  -->
				<tr>
					<td><strong><span class="style2">Indicativo de Reposição de Paralelo:</span></strong></td>
					<td align="left" width="20%"><label> 
						<html:radio	property="indicativoReposicaoParalelo" value="1" /> <strong>Sim</strong></label>	</td>
					<td align="left"><label> 
						<html:radio property="indicativoReposicaoParalelo" value="2" /> <strong>Não</strong></label></td>
				</tr>
				
				<!-- Indicativo de Reposição de Calçada  -->
				<tr>
					<td><strong><span class="style2">Indicativo de Reposição de Calçada:</span></strong></td>
					<td align="left" width="20%"><label> 
						<html:radio	property="indicativoReposicaoCalcada" value="1" /> <strong>Sim</strong></label>	</td>
					<td align="left"><label> 
						<html:radio property="indicativoReposicaoCalcada" value="2" /> <strong>Não</strong></label></td>
				</tr>
			
				
				<!-- Indicador de liberação para empresa de cobrança gerar OS -->
				<tr>
					<td><strong><span class="style2">Indicador de Liberação para Empresa de Cobrança Gerar OS:<font color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%"><label> 
						<html:radio	property="indicadorEmpresaCobranca" value="1" /> <strong>Sim</strong></label>	</td>
					<td align="left"><label> 
						<html:radio property="indicadorEmpresaCobranca" value="2" /> <strong>Não</strong></label></td>
				</tr>
				
				<!-- Atividade Única -->

				<tr>
					<td><strong><span class="style2">Atividade &Uacute;nica:<font
						color="#FF0000">*</font></span></strong></td>
					<td align="left" width="30%"><label> <html:radio
						property="indicadorAtividadeUnica" value="1"
						onclick="javascript:removeAllRowsTableAtividades();" /> <strong>Sim</strong></label>
					</td>
					<td align="left" width="30%"><label> <html:radio property="indicadorAtividadeUnica"
						value="2" /> <strong>Não</strong></label></td>
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
						value="2" /> <strong>Não</strong></label></td>
				</tr>
				
				<!-- Indicador de Fiscalizacao de Infração -->

				<tr>
					<td><strong><span class="style2">Indicador de Fiscalização de Infração:<font
						color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%"><label> <html:radio
						property="indicadorFiscalizacaoInfracao" value="1" /> <strong>Sim</strong></label>
					</td>
					<td align="left"><label> <html:radio property="indicadorFiscalizacaoInfracao"
						value="2" /> <strong>Não</strong></label></td>
				</tr>
				
				<!-- Indicador Programação Automática -->

				<tr>
					<td><strong><span class="style2">Indicador Programação Automática:<font
						color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%"><label> 
					<html:radio property="indicadorProgramacaoAutomatica" value="1" onclick ="habilitaIndicativoProgramacao();"/> <strong>Sim</strong></label></td>
					<td align="left"><label> 
					<html:radio property="indicadorProgramacaoAutomatica" value="2" onclick ="habilitaIndicativoProgramacao();"/> <strong>Não</strong></label></td>
				</tr>
				
				<!-- Indicativo de Obrigatoriedade de informação da atividade  -->
				<tr>
					<td><strong><span class="style2">Indicador de Obrigatoriedade de Informação da Atividade:<font
						color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%"><label> 
						<html:radio	property="indicativoObrigatoriedadeAtividade" value="1" onclick="atualizaValorIndicativoObrigatoriedadeAtividade('1');" /> <strong>Sim</strong></label>	</td>
					<td align="left"><label> 
						<html:radio property="indicativoObrigatoriedadeAtividade" value="2" onclick="atualizaValorIndicativoObrigatoriedadeAtividade('2');" /> <strong>Não</strong></label></td>
				</tr>
				
				<!-- Indicador Gerar OS Inspecao Anormalidade -->

				<tr>
					<td><strong><span class="style2">Indicador Gerar OS Inspeção Anormalidade:<font
						color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%"><label> <html:radio
						property="indicadorInspecaoAnormalidade" value="1" /> <strong>Sim</strong></label>
					</td>
					<td align="left"><label> <html:radio property="indicadorInspecaoAnormalidade"
						value="2" /> <strong>Não</strong></label></td>
				</tr>
				
				<!-- Indicador de encerramento automático do RA no encerramento de sua última/única OS pendente: -->

				<tr>
					<td><strong>
							<span class="style2">Indicador de encerramento automático do RA no encerramento de sua última/única OS pendente:<font color="#FF0000">*</font></span>
						</strong>
					</td>
					<td align="left" width="20%">
						<label>
							<html:radio	property="indicadorEncAutomaticoRAQndEncOS" value="1" />
							<strong>Sim</strong>
						</label>
					</td>
					<td align="left">
						<label>
							<html:radio property="indicadorEncAutomaticoRAQndEncOS"	value="2" />
							<strong>Não</strong>
						</label>
					</td>
				</tr>
				

				<!-- Atividades do Tipo de Serviço -->

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
					<td colspan="4">
						<div align="left">
						<table width="100%" id="tableAtividades" align="center" bgcolor="#99CCFF">

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

							<c:forEach var="servicoTipoAtividade"
								items="${colecaoServicoTipoAtividade}"
								varStatus="i">

								<c:if test="${i.count%2 == '1'}">
									<tr bgcolor="#FFFFFF">
								</c:if>
								
								<c:if test="${i.count%2 == '0'}">
									<tr bgcolor="#cbe5fe">
								</c:if>
								
								<td>
									<div align="center">
										<a href="javascript:if(confirm('Confirma remoção?')){removeRowTableAtividades('$${servicoTipoAtividade.atividade.id}$${servicoTipoAtividade.numeroExecucao}$');}">
											<img src="imagens/Error.gif" 
												width="14" 
												height="14" 
												border="0"
												title="Remover"></a>
									</div>
								</td>
								
								<td>
									<div align="left">${servicoTipoAtividade.atividade.descricao}</div>
								</td>
								
								<td>
									<div align="center">${servicoTipoAtividade.numeroExecucao}</div>
								</td>
							</c:forEach>

							</tbody>
						</table>
					</div>
					</td>
				</tr>

				<!-- Materiais do Tipo de Serviço -->

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
					<td colspan="4">
						<div align="left">
						<table width="100%" id="tableMateriais" align="center" bgcolor="#99CCFF">

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
								items="${colecaoServicoTipoMaterial}"
								varStatus="i">
						
								<c:if test="${i.count%2 == '1'}">
									<tr bgcolor="#FFFFFF">
								</c:if>
						
								<c:if test="${i.count%2 == '0'}">
									<tr bgcolor="#cbe5fe">
								</c:if>
						
								<td>
									<div align="center">
										<a href="javascript:if(confirm('Confirma remoção?')){removeRowTableMateriais('$${servicoTipoMaterial.material.id}$');}">
											<img src="imagens/Error.gif" 
												width="14" 
												height="14" 
												border="0"
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
					<div align="left"><strong><font color="#000000">Motivo </font><font
						color="#000000"> de Encerramento</font></strong></div>
					</td>
					<td>
					<div align="right"><input type="button" name="Submit243"
						class="bottonRightCol" value="Adicionar"
						onclick="chamarPopup('exibirPesquisarMotivoDeEncerramentoAction.do?limpar=S', 'servicoTipoMaterial', null, null, 400, 640, '','');">
					</div>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div align="left">
						<table width="100%" id="tableMotivoEncerramento" align="center" bgcolor="#99CCFF">

						<!--corpo da segunda tabela-->
						<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
							<td width="14%">
								<div align="center"><strong>Remover</strong></div>
							</td>							
							<td width="55%">
								<div align="center"><strong>Descri&ccedil;&atilde;o dos Motivos de Encerramento
							</strong></div>
							</td>
						</tr>
						
						<tbody>

							<c:forEach var="motivoEncerramento"
								items="${colecaoAtendimentoMotivosEncerramentoInseridos}"
								varStatus="i">
						
								<c:if test="${i.count%2 == '1'}">
									<tr bgcolor="#FFFFFF">
								</c:if>
						
								<c:if test="${i.count%2 == '0'}">
									<tr bgcolor="#cbe5fe">
								</c:if>
						
								<td>
									<div align="center">
										<a href="javascript:if(confirm('Confirma remoção?')){removeRowTableMotivos('${motivoEncerramento.id}');}">
											<img src="imagens/Error.gif" 
												width="14" 
												height="14" 
												border="0"
												title="Remover"> </a></div>
								</td>
								
								<td>
									<div align="left">${motivoEncerramento.descricao}</div>
								</td>
								
							</c:forEach>
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

				<!-- Botões -->

				<tr>
					<td align="left" colspan="3" width="100%">
						<input type="button"
							name="ButtonCancelar" class="bottonRightCol" value="Voltar"
							onClick="window.history.go(-1)">
						<input type="button" 
							name="ButtonReset"
							class="bottonRightCol" 
							value="Desfazer"
							onClick="window.location.href='<html:rewrite page="/exibirAtualizarTipoServicoAction.do?desfazer=S&idServico=${sessionScope.idServico}"/>'">
						<input type="button"
							name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td width="100%" align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Atualizar" onclick="validaForm();"></td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</html:html>
