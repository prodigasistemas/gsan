<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
	
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarTipoServicoActionForm" />

<script language="JavaScript">
<!-- Begin

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
	 	  	form.perfilServico.value = codigoRegistro;
	 	  	form.descricaoPerfilServico.value = descricaoRegistro; 
	 	  	form.descricaoPerfilServico.style.color = "#000000";
		} else if (tipoConsulta == 'servicoTipoReferencia') {
			form.idTipoServicoReferencia.value = codigoRegistro;
			form.descricaoTipoServicoReferencia.value = descricaoRegistro; 
	 	  	form.descricaoTipoServicoReferencia.style.color = "#000000";	 			
		}[]
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
		} else if (tipo == 'atividade') { 	
	 	  	form.atividadesTipoServico.value = "";
	 	  	form.dsAtividadesTipoServico.value = ""; 
 		} else if (tipo == 'material') {
	 	  	form.tipoServicoMaterial.value = "";
	 	  	form.dsTipoServicoMaterial.value = ""; 
	 	} 		
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
	
	function chamarFiltrar() {
	  	var form = document.forms[0];
	  	form.action = "/gsan/filtrarTipoServicoAction.do";
		if(validateFiltrarTipoServicoActionForm(form)) {	     		  		
				submeterFormPadrao(form);   		  
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

	function limparForm() {
		var form = document.forms[0];
		form.action = "/gsan/exibirFiltrarTipoServicoAction.do?limparCampos=ok";
		form.submit();	
	} 
	
	function removeRowTableAtividades(id) {
		var form = document.forms[0];	
		form.atividadesTipoServico.value = id;
		form.tipoServicoAtividadeId.value = id;
		form.method.value = "removeServicoTipoAtividade";
		reload();
	}
	
	function removeRowTableMateriais(id) {
		var form = document.forms[0];	
		form.tipoServicoMaterial.value = id;
		form.tipoServicoMaterialId.value = id;
		form.method.value = "removeServicoTipoMaterial";
		reload();
	}	
	
	function reload() {
		var form = document.forms[0];
		form.indicativoTipoSevicoEconomias[1].checked = true;
		form.action = "/gsan/exibirFiltrarTipoServicoAction.do";
		form.submit();
	} 	
	
	function verificarImportancia(){
	var form = document.forms[0];
	
	if(form.grauImportancia.value=="1") {
		form.indicadorAtualizar.checked = false;
		form.indicadorAtualizar.disabled = true;
	}

}
-->
</script>
</head>

<body leftmargin="5" topmargin="5" onload="javascript:verificarImportancia();">
<html:form action="/filtrarTipoServicoAction.do" method="post"
	name="FiltrarTipoServicoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.FiltrarTipoServicoActionForm"
	onsubmit="">

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

					<td class="parabg">
						<logic:present name="indicadorGrauImportancia" >
							Filtrar Import&acirc;ncia Tipo de Servi&ccedil;o
						</logic:present>
						<logic:notPresent name="indicadorGrauImportancia">
							Filtrar Tipo de Servi&ccedil;o
						</logic:notPresent>
					</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<html:hidden property="method" />
			<html:hidden property="tipoServicoMaterialId" />
			<html:hidden property="grauImportancia" />
			<html:hidden property="tipoServicoAtividadeId" />
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>Para filtrar o(s) tipo(s) de servi&ccedil;o, informe os dados abaixo:</td>
					<td width="84">
					<input type="checkbox" name="indicadorAtualizar"
								value="1" checked /><strong>Atualizar</strong>
						
					</td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td><strong>C&oacute;digo do Tipo de Servi&ccedil;o:</strong></td>
					<td><html:text property="idTipoServico" size="4" maxlength="4"
						onkeypress="" /></td>
				</tr>			
				<tr>
					<td width="40%"><strong>Descri&ccedil;&atilde;o do Tipo de Servi&ccedil;o:</strong></td>
					<td width="60%"><html:text property="descricaoTipoServico" size="50"
						maxlength="50" onkeypress="" /></td>
				</tr>
				<tr>
					<td><strong>Abreviatura do Tipo de Servi&ccedil;o:</strong></td>
					<td><html:text property="abreviada" size="20" maxlength="20"
						onkeypress="" /></td>
				</tr>
				<tr>
					<td><strong>Subgrupo:</strong></td>
					<td><html:select property="subgrupo">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoSubgrupo"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td height="24"><strong>Valor do Servi&ccedil;o:</strong></td>

					<td height="24"><span class="style2"> <html:text
						property="valorInicial" size="15" maxlength="20"
						onkeyup="formataValorMonetario(this, 8)"
						style="text-align: right;" /></span><strong> a </strong><span
						class="style2"><html:text property="valorFinal" size="15"
						maxlength="20" onkeyup="formataValorMonetario(this, 8)"
						style="text-align: right;" /></span></td>
				</tr>
				<!-- Indicador de Pavimento -->

				<%-- <tr>
					<td><strong><span class="style2">Indicador de Pavimento:<font
						color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%"><label> <html:radio
						property="pavimento" value="1" /> <strong>Sim</strong></label><label>
					<html:radio property="pavimento" value="2" /> <strong>Não</strong></label>
					<label> <html:radio property="pavimento" value="" /> <strong>Todos</strong></label>
					</td>
				</tr> --%>
				
				<!-- Indicador de Pavimento de rua -->
				<tr>
					<td><strong><span class="style2">Indicador de Pavimento de Rua:<font color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%">
						<label> 
							<html:radio	property="indicadorPavimentoRua" value="1" /><strong>Sim</strong>
						</label>
						<label>
							<html:radio property="indicadorPavimentoRua" value="2" /> <strong>Não</strong>
						</label>
						<label> 
							<html:radio property="indicadorPavimentoRua" value="" /> <strong>Todos</strong>
						</label>
					</td>
				</tr>
				
				
				<!-- Indicador de Pavimento de calçada -->
				<tr>
					<td><strong><span class="style2">Indicador de Pavimento de Cal&ccedil;ada:<font color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%">
						<label> 
							<html:radio	property="indicadorPavimentoCalcada" value="1" /><strong>Sim</strong>
						</label>
						<label>
							<html:radio property="indicadorPavimentoCalcada" value="2" /> <strong>Não</strong>
						</label>
						<label> 
							<html:radio property="indicadorPavimentoCalcada" value="" /> <strong>Todos</strong>
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
						<label>
							<html:radio property="indicativoTipoSevicoEconomias" value="2" /> <strong>Não</strong>
						</label>
						<label>
							<html:radio property="indicativoTipoSevicoEconomias" value="" /> <strong>Todos</strong>
						</label>
					</td>
				</tr>
				
				
				<tr>
					<td class="style3"><strong>Indicador Atualiza&ccedil;&atilde;o do Comercial:<span
						class="style2"><strong><strong><strong><font color="#FF0000"></font></strong></strong></strong></span></strong></td>
					<td colspan="2"><strong>
					<html:select property="atualizacaoComercial" style="width: 150px;">
							<html:option value="0">                  &nbsp;</html:option>
							<html:option value="1">SIM - TODOS &nbsp;</html:option>
							<html:option value="2">SIM - MOMENTO DA EXECUÇÃO&nbsp;</html:option>
							<html:option value="3">SIM - MOMENTO POSTERIOR&nbsp;</html:option>
							<html:option value="4">TODOS&nbsp;</html:option>
							<html:option value="5">NÃO&nbsp;</html:option>
					</html:select> </strong></td>
				</tr>
				<!-- Indicador de Serviço Terceirizado -->

				<tr>
					<td><strong><span class="style2">Indicador Servi&ccedil;o
					Terceirizado:<font color="#FF0000">*</font></span></strong></td>
					<td align="left"><label> <html:radio property="servicoTerceirizado"
						value="1" /> <strong>Sim</strong></label> <label> <html:radio
						property="servicoTerceirizado" value="2" /> <strong>Não</strong></label>
					<label> <html:radio property="servicoTerceirizado" value="" /> <strong>Todos</strong></label>
					</td>
				</tr>

				<!-- Indicativo de Fiscalizacao de Infração -->

				<tr>
					<td><strong><span class="style2">Indicativo de Fiscalização de
					Infração:<font color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%"><label> <html:radio
						property="indicadorFiscalizacaoInfracao" value="1" /> <strong>Sim</strong></label>
					<label> <html:radio property="indicadorFiscalizacaoInfracao"
						value="2" /> <strong>Não</strong></label> <label> <html:radio
						property="indicadorFiscalizacaoInfracao" value="" /> <strong>Todos</strong></label>
					</td>
				</tr>
				<!-- Indicativo de Vistoria -->

				<tr>
					<td><strong><span class="style2">Indicativo de Vistoria:<font
						color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%"><label> <html:radio
						property="indicadorVistoria" value="1" /> <strong>Sim</strong></label>
					<label> <html:radio property="indicadorVistoria" value="2" /> <strong>Não</strong></label>
					<label> <html:radio property="indicadorVistoria" value="" /> <strong>Todos</strong></label>
					</td>
				</tr>

				<!-- Código do Tipo de Serviço -->

				<tr>
					<td><strong><span class="style2">C&oacute;digo do Tipo de
					Servi&ccedil;o:<font color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%"><label> <html:radio
						property="codigoServico" value="O" /> <strong>Operacional</strong></label>
					<label> <html:radio property="codigoServico" value="C" /> <strong>Comercial</strong></label>
					<label> <html:radio property="codigoServico" value="" /> <strong>Todos</strong></label>
					</td>
				</tr>

				<!-- Tempo Médio de exculçao -->
				<tr>
					<td width="10%"><strong>Tempo Médio de Execução:</strong></td>
					<td width="90%"><html:text property="tempoMedioIncial" size="5"
						maxlength="3" onkeypress="" /><strong> a </strong> <html:text
						property="tempoMedioFinal" size="5" maxlength="3" onkeypress="" />
					</td>
				</tr>

				<!-- tipo de Débito -->
				<tr>
					<td><strong>Tipo de D&eacute;bito:</strong></td>
					<td width="63%"><html:text property="idTipoDebito"
						tabindex="11" size="4" maxlength="4"
						onkeyup="validaEnterComMensagem(event, 'exibirFiltrarTipoServicoAction.do', 'idTipoDebito', 'Tipo do Débito');" />
						<a href="javascript:popupTipoDebito()">
					<img src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						border="0" width="23" height="21" title="Pesquisar"></a> <logic:present
						name="corTipoDebito">
						<logic:equal name="corTipoDebito" value="exception">
							<html:text property="descricaoTipoDebito" size="35"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corTipoDebito" value="exception">
							<html:text property="descricaoTipoDebito" size="35"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="corTipoDebito">
						<logic:empty name="FiltrarTipoServicoActionForm"
							property="idTipoDebito">
							<html:text property="descricaoTipoDebito" size="35" value=""
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarTipoServicoActionForm"
							property="idTipoDebito">
							<html:text property="descricaoTipoDebito" size="35"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a href="javascript:limpar('tipoDebito');">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>

				<!-- Tipo de Credito -->
				<tr>
					<td width="10%"><strong>Tipo de Crédito:</strong></td>
					<td width="90%"><html:select property="idTipoCredito">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoCreditoTipo"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>

				<!-- Prioridade do Serviço -->
				<tr>
					<td width="10%"><strong>Prioridade do Serviço:</strong></td>
					<td width="90%"><html:select property="idPrioridadeServico">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoPrioridade"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>

				<!-- Perfil do Tipo de Serviço -->
				<tr>
					<td><strong>Perfil do Tipo de Servi&ccedil;o:</strong></td>
					<td width="63%"><html:text property="perfilServico"
						tabindex="11" size="4" maxlength="4"
						onkeyup="validaEnterComMensagem(event, 'exibirFiltrarTipoServicoAction.do', 'perfilServico', 'Tipo do Perfil');" />
						<a href="javascript:popupPerfilServico();">
					<img src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						border="0" width="23" height="21" title="Pesquisar"></a> <logic:present
						name="corPerfilTipo">
						<logic:equal name="corPerfilTipo" value="exception">
							<html:text property="descricaoPerfilServico" size="35"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corPerfilTipo" value="exception">
							<html:text property="descricaoPerfilServico" size="35"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="corPerfilTipo">
						<logic:empty name="FiltrarTipoServicoActionForm"
							property="perfilServico">
							<html:text property="descricaoPerfilServico" size="35" value=""
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarTipoServicoActionForm"
							property="perfilServico">
							<html:text property="descricaoPerfilServico" size="35"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a href="javascript:limpar('perfil');">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				
				<!-- Tipo do Serviço Referência -->
				<tr>
					<td><strong>Tipo de Servi&ccedil;o de Refer&ecirc;ncia:</strong></td>
					<td width="63%"><html:text property="idTipoServicoReferencia"
						tabindex="11" size="4" maxlength="4"
						onkeyup="validaEnterComMensagem(event, 'exibirFiltrarTipoServicoAction.do', 'idTipoServicoReferencia', 'Tipo de Serviço de Referência');controlaTipoServicoReferenciaOnKeyUp();" />
						<a href="javascript:popupServicoTipoReferencia();">
					<img src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						border="0" width="23" height="21" title="Pesquisar"></a> <logic:present
						name="corTipoReferencia">
						<logic:equal name="corTipoReferencia" value="exception">
							<html:text property="descricaoTipoServicoReferencia" size="35"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corTipoReferencia" value="exception">
							<html:text property="descricaoTipoServicoReferencia" size="35"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="corTipoReferencia">
						<logic:empty name="FiltrarTipoServicoActionForm"
							property="idTipoServicoReferencia">
							<html:text property="descricaoTipoServicoReferencia" size="35" value=""
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarTipoServicoActionForm"
							property="idTipoServicoReferencia">
							<html:text property="descricaoTipoServicoReferencia" size="35"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a
							href="javascript:limpar('referencia');controlaTipoServicoReferenciaOnKeyUp();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				
				<!-- Indicador de liberação para empresa de cobrança gerar OS -->
				<tr>
					<td><strong><span class="style2">Indicador de Liberação para Empresa de Cobrança Gerar OS:<font color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%"><label> <html:radio	
						property="indicadorEmpresaCobranca" value="1" /> <strong>Sim</strong></label>
					<label> <html:radio 
						property="indicadorEmpresaCobranca" value="2" /> <strong>Não</strong></label>
					</td>
				</tr>
				
				<!-- Indicador para gerar OS de inspeção por anormalidade -->
				<tr>
					<td><strong><span class="style2">Indicador para Gerar OS de Inspeção por Anormalidade:<font color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%"><label> <html:radio	
						property="indicadorInspecaoAnormalidade" value="1" /> <strong>Sim</strong></label>
					<label> <html:radio 
						property="indicadorInspecaoAnormalidade" value="2" /> <strong>Não</strong></label>
					</td>
				</tr>
				
				<!-- Indicador de programação automática -->
				<tr>
					<td><strong><span class="style2">Indicador de Programação Automática:<font color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%"><label> <html:radio	
						property="indicadorProgramacaoAutomatica" value="1" /> <strong>Sim</strong></label>
					<label> <html:radio 
						property="indicadorProgramacaoAutomatica" value="2" /> <strong>Não</strong></label>
					</td>
				</tr>
				
				<!-- Indicador de Atividade Unica -->
				<tr>
					<td><strong><span class="style2">Indicador de Atividade Única:<font
						color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%"><label> <html:radio
						property="indicadorAtividadeUnica" value="1" /> <strong>Sim</strong></label>
					<label> <html:radio property="indicadorAtividadeUnica" value="2" />
					<strong>Não</strong></label> <label> <html:radio
						property="indicadorAtividadeUnica" value="" /> <strong>Todos</strong></label>
					</td>
				</tr>
				
				<!-- Indicador de Encerramento Automático do RA quando encerrar ultima OS -->
				<tr>
					<td><strong><span class="style2">Indicador de encerramento automático do RA no encerramento de sua última/única OS pendente:<font
						color="#FF0000">*</font></span></strong>
					</td>
					<td align="left" width="20%">
						<label>
							<html:radio	property="indicadorEncAutomaticoRAQndEncOS" value="1" />
							<strong>Sim</strong>
						</label>
						<label>
							<html:radio property="indicadorEncAutomaticoRAQndEncOS" value="2" />
							<strong>Não</strong>
						</label>
						<label>
							<html:radio	property="indicadorEncAutomaticoRAQndEncOS" value="" />
							<strong>Todos</strong>
						</label>
					</td>
				</tr>
				
				<!-- Indicador de Uso -->
				<tr>
					<td><strong><span class="style2">Indicador de Uso:<font
						color="#FF0000">*</font></span></strong></td>
					<td align="left" width="20%"><label> <html:radio
						property="indicadorUso" value="1" /> <strong>Ativo</strong></label>
					<label> <html:radio property="indicadorUso" value="2" /> <strong>Inativo</strong></label>
					<label> <html:radio property="indicadorUso" value="" /> <strong>Todos</strong></label>
					</td>
				</tr>
				
				<!-- Atividades do Tipo de Serviço -->
				<tr>
					<td><strong>Atividade do Tipo de Serviço: </strong></td>
					<td colspan="3"><span class="style2"> <html:text
						property="atividadesTipoServico" size="4" maxlength="4"
						onkeyup="validaEnterComMensagem(event, 'exibirFiltrarTipoServicoAction.do', 'atividadesTipoServico', 'Atividade Tipo Servico');"/>
						
						<a href="javascript:abrirPopupComSubmit('exibirFiltrarTipoServicoAction.do?redirecionarPagina=exibirPesquisarAtividade',350,500,'pesquisar');">
						<img src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						border="0" width="23" height="21" title="Pesquisar"></a>
						
						<html:text property="dsAtividadesTipoServico"
						readonly="true" style="background-color:#EFEFEF; border:0; color:${FiltrarTipoServicoActionForm.dsAtividadeCor}"
						size="40" maxlength="40" /><a href="javascript:limpar('atividade');"><img
								border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a></span></td>
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
							<div align="center"><strong>Descri&ccedil;&atilde;o das
							Atividades </strong></div>
							</td>
						</tr>

						<c:forEach var="atividade"
							items="${FiltrarTipoServicoActionForm.arrayAtividade}">
							<tr bgcolor="#FFFFFF">
								<td>
								<div align="center"><a
									href="javascript:if(confirm('Confirma remoção?')){removeRowTableAtividades('${atividade.id}');}">
								<img src="imagens/Error.gif" width="14" height="14" border="0"
									title="Remover"> </a></div>
								</td>
								<td>
								<div align="left">${atividade.descricao}</div>
								</td>
							</tr>
						</c:forEach>
					</table>
					</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
																			
				<!-- Materiais do Tipo de Serviço -->
				<tr>
					<td><strong>Materiais do Tipo de Serviço: </strong></td>
					<td colspan="3"><span class="style2"> <html:text
						property="tipoServicoMaterial" size="4" maxlength="4"
						onkeyup="validaEnterComMensagem(event, 'exibirFiltrarTipoServicoAction.do', 'tipoServicoMaterial', 'Materiais do Tipo de Serviço');" />
					<a href="javascript:abrirPopupComSubmit('exibirFiltrarTipoServicoAction.do?redirecionarPagina=exibirPesquisarMaterial',400,600,'pesquisar');">
					<img src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						border="0" width="23" height="21" title="Pesquisar"></a>
					
					<html:text property="dsTipoServicoMaterial"
						readonly="true" style="background-color:#EFEFEF; border:0"
						size="40" maxlength="40" /><a href="javascript:limpar('material');"><img
								border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a></span></td>
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
							<td>
							<div align="center"><strong>Descri&ccedil;&atilde;o dos Materiais
							</strong></div>
							</td>
						</tr>
						<tbody>
							<c:forEach var="material"
								items="${FiltrarTipoServicoActionForm.arrayMateriais}">
								<tr bgcolor="#FFFFFF">
									<td>
									<div align="center"><a
										href="javascript:if(confirm('Confirma remoção?')){removeRowTableMateriais('${material.id}');}">
									<img src="imagens/Error.gif" width="14" height="14" border="0"
										title="Remover"> </a></div>
									</td>
									<td>
									<div align="left">${material.descricao}</div>
									</td>
								</tr>
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
				<!-- final -->
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="left"><input type="button" name="Button"
						class="bottonRightCol" value="Limpar"
						onClick="javascript:limparForm();" /></td>
					<td align="right"><gsan:controleAcessoBotao name="Button"
						value="Filtrar" onclick="javascript:chamarFiltrar();"
						url="filtrarCobrancaCronogramaAction.do" /> <%-- <input type="button" name="Button" class="bottonRightCol" value="Filtrar" onClick="javascript:chamarFiltrar();"/> --%>
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
