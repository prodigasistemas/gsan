<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarTipoServicoActionForm" />


<script type="text/javascript" language="Javascript1.1"><!-- 
		
	function validaForm(){
		 var form = document.forms[0];
		if(validatePesquisarTipoServicoActionForm(form)){
			//submeterFormPadrao(form);
    		form.action='pesquisarTipoServicoAction.do';
    		form.submit();
		}
	}

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

    	if (tipoConsulta == 'servicoTipoReferencia') {
      		form.tipoServicoReferencia.value = codigoRegistro;
      		form.dsTipoServicoReferencia.value = descricaoRegistro; 
    	} else if (tipoConsulta == 'tipoDebito') { 	
	 	  	form.tipoDebito.value = codigoRegistro;
	 	  	form.dsTipoDebito.value = descricaoRegistro; 
    	} else if (tipoConsulta == 'servicoPerfilTipo') { 	
	 	  	form.perfilServco.value = codigoRegistro;
	 	  	form.dsPerfilServco.value = descricaoRegistro; 
 		} else if (tipoConsulta == 'atividade') { 	
	 	  	form.atividadesTipoServico.value = codigoRegistro;
	 	  	form.dsAtividadesTipoServico.value = descricaoRegistro; 
 		} else if (tipoConsulta == 'material') { 	
	 	  	form.perfilServco.value = codigoRegistro;
	 	  	form.dsPerfilServco.value = descricaoRegistro; 
 		} 
  	}	
  	
  	function recuperarDadosQuatroParametros(codigoRegistro, descricaoRegistro, codigoAuxiliar, tipoConsulta) {
  		if (tipoConsulta == 'atividade') {
	 		insertRowTableAtividades(codigoRegistro, descricaoRegistro, codigoAuxiliar);
  		} else if (tipoConsulta == 'material') {
	 		insertRowTableMateriais(codigoRegistro, descricaoRegistro, codigoAuxiliar);
 		}
  	}
  
	function limpar(tipoConsulta) {
		 var form = document.forms[0];
		if (tipoConsulta == 'servicoTipoReferencia') {
      		form.tipoServicoReferencia.value = "";
      		form.dsTipoServicoReferencia.value = ""; 
    	} else if (tipoConsulta == 'tipoDebito') { 	
	 	  	form.tipoDebito.value = "";
	 	  	form.dsTipoDebito.value = ""; 
    	} else if (tipoConsulta == 'servicoPerfilTipo') { 	
	 	 	form.perfilServco.value = "";
			form.dsPerfilServco.value = "";
 		} else if (tipoConsulta == 'atividade') { 	
	 	  	form.atividadesTipoServico.value = "";
	 	  	form.dsAtividadesTipoServico.value = ""; 
 		} else if (tipoConsulta == 'material') { 	
	 	  	form.tipoServicoMaterial.value = "";
	 	  	form.dsTipoServicoMaterial.value = ""; 
	 	} 
	}  
	
	function reload() {
		var form = document.forms[0];
		form.action = "/gsan/exibirPesquisarTipoServicoAction.do";
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
	 
	function limparForm() {
		var form = document.forms[0];
		form.action = "/gsan/exibirPesquisarTipoServicoAction.do?limpar=S";
		form.submit();
				
	} 
	
	
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];
			if (tipoConsulta == 'tipoDebito') {
    		form.tipoDebito.value = codigoRegistro;
      		form.dsTipoDebito.value = descricaoRegistro;
      		form.dsTipoDebito.style.color = "#000000";
    	}
    }
    
    function controlaDebitoCredito(){
    	var form = document.forms[0];
    	
    	if(form.tipoDebito.value != ""){
    		form.tipoCredito.disabled = true;
    	}else{
    		form.tipoCredito.disabled = false;
    	}
    	
    	if(form.tipoCredito.value != ""){
    		form.tipoDebito.disabled = true;
    	}else{
    		form.tipoDebito.disabled = false;
    	}
    }
	

--></script>

</head>

<body leftmargin="0" topmargin="0" onload="resizePageSemLink(670,720);javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/pesquisarTipoServicoAction.do"
	name="PesquisarTipoServicoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.PesquisarTipoServicoActionForm"
	method="post">

	<table width="100%" border="0" cellspacing="5" cellpadding="5">
		<tr>
			<td width="100%" valign="" class="centercoltext">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Pesquisar Tipo de Serviço</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<html:hidden property="method" />
			<html:hidden property="tipoServicoMaterialId" />
			<html:hidden property="tipoServicoAtividadeId" />
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Preencha os campos para pesquisar tipo de serviço:</td>
				</tr>
				<tr>
					<td width="40%" height="0"><strong>Descrição do Tipo de Serviço:</strong></td>
					<td colspan="3"><html:text maxlength="50" property="dsTipoServico"
						size="50" /></td>
				</tr>
				<tr>
					<td width="40%">&nbsp;</td>
					<td colspan="3">
					<html:radio property="tipoPesquisa"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />Iniciando
					pelo texto <html:radio property="tipoPesquisa" tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />Contendo
					o texto</td>
				</tr>
				<tr>
					<td width="40%" height="0"><strong>Descrição Abreviada do Tipo de
					Serviço:</strong></td>
					<td colspan="3"><html:text maxlength="30"
						property="dsAbreviadaTipoServico" size="50" /></td>
				</tr>
				<tr>
					<td width="40%">&nbsp;</td>
					<td colspan="3"><html:radio property="tipoPesquisaAbreviada"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />Iniciando
					pelo texto <html:radio property="tipoPesquisaAbreviada"
						tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />Contendo
					o texto</td>
				</tr>
				<tr>
					<td class="style3"><strong>Subgrupo do tipo de Serviço:<span
						class="style2"><strong><strong><strong><font color="#FF0000"></font></strong></strong></strong></span></strong></td>
					<td colspan="2"><strong> <html:select
						property="subgrupoTipoServico" style="width: 150px;">
						<logic:present name="colecaoServicoTipoSubgrupo" scope="session">
							<html:option value="">&nbsp;</html:option>
							<logic:iterate name="colecaoServicoTipoSubgrupo"
								id="subgrupoTipoServico">
								<option
									value="<bean:write property="id" name="subgrupoTipoServico"/>">
								<bean:write property="descricao" name="subgrupoTipoServico" /></option>
							</logic:iterate>
						</logic:present>
					</html:select> </strong></td>
				</tr>
				
				
			<%-- 	<tr>
					<td><strong>Indicador de Pavimento:<font color="#FF0000"></font></strong></td>
					<td><strong> <html:radio property="indicadorPavimento" value="1" /> <strong>Sim <html:radio
						property="indicadorPavimento" value="2" /> <strong>Não <html:radio
						property="indicadorPavimento" value="3" /> Todas</strong></strong></strong></td>

				</tr>
				--%>
				
				<%-- Indicador de Pavimento de Rua --%>
				<tr>
					<td><strong>Indicador de Pavimento de Rua:<font color="#FF0000"></font></strong></td>
					<td>
						<strong>
							<html:radio property="indicadorPavimentoRua" value="1" />
						<strong>Sim 
							<html:radio	property="indicadorPavimentoRua" value="2" /> 
						<strong>Não 
							<html:radio	property="indicadorPavimentoRua" value="3" /> 
								Todas
						</strong>
						</strong>
						</strong>
					</td>
				</tr>
				
				<%-- Indicador de Pavimento de Calçada --%>
				<tr>
					<td><strong>Indicador de Pavimento de Calçada:<font color="#FF0000"></font></strong></td>
					<td>
						<strong>
							<html:radio property="indicadorPavimentoCalcada" value="1" />
						<strong>Sim 
							<html:radio	property="indicadorPavimentoCalcada" value="2" /> 
						<strong>Não 
							<html:radio	property="indicadorPavimentoCalcada" value="3" /> 
								Todas
						</strong>
						</strong>
						</strong>
					</td>
				</tr>
				
				
				<tr>
					<td height="24"><strong>Valor do Serviço:</strong></td>

					<td height="24"><span class="style2"><strong><strong> <html:text
						property="valorServicoInicial" size="15" maxlength="20" onkeyup="formataValorMonetario(this, 8)" style="text-align: right;"/></span>a<span
						class="style2"><strong><strong> <html:text
						property="valorServicoFinal" size="15" maxlength="20" onkeyup="formataValorMonetario(this, 8)" style="text-align: right;"/></span></td>
				</tr>
				<tr>
					<td class="style3"><strong>Indicador Atualização do Comercial:<span
						class="style2"><strong><strong><strong><font color="#FF0000"></font></strong></strong></strong></span></strong></td>
					<td colspan="2"><strong>
					<html:select property="indicadorAtualizacaoComercio" style="width: 150px;">
							<html:option value="0">                  &nbsp;</html:option>
							<html:option value="1">SIM - TODOS &nbsp;</html:option>
							<html:option value="2">SIM - MOMENTO DA EXECUÇÃO&nbsp;</html:option>
							<html:option value="3">SIM - MOMENTO POSTERIOR&nbsp;</html:option>
							<html:option value="4">TODOS&nbsp;</html:option>
							<html:option value="5">NÃO&nbsp;</html:option>
					</html:select> </strong></td>
				</tr>
				<tr>
					<td><strong>Indicador de Serviço Terceirizado:<font color="#FF0000"></font></strong></td>
					<td>
						<html:radio property="indicadorServicoTerceirizado" value="1" /> 
						<strong>Sim </strong>
						<html:radio property="indicadorServicoTerceirizado" value="2" /> 
						<strong>Não </strong>
						<html:radio property="indicadorServicoTerceirizado" value="3" /><strong> Todas </strong>
					</td>

				</tr>

				<tr>
					<td><strong>Código do Serviço:<font color="#FF0000"></font></strong></td>
					<td><strong> <html:radio property="codigoServico" value="1" /> <strong>Operacional <html:radio
						property="codigoServico" value="2" /> <strong>Comercial <html:radio
						property="codigoServico" value="3" /> Todos </strong> </strong></strong></td>
				</tr>
				<tr>
					<td height="24"><strong>Tempo Médio de Execução:</strong></td>

					<td height="24"><span class="style2"><strong><strong> <html:text
						property="tempoMedioExecucaoInicial" size="6" maxlength="4" />
					</strong></strong></span>a<span class="style2"><strong><strong> <html:text
						property="tempoMedioExecucaoFinal" size="6" maxlength="4" /> </strong></strong></span></td>
				</tr>
				<!-- Tipo de Débito -->
				<tr>
					<td><strong>Tipo de Débito:<font color="#FF0000"></font></strong></td>
					<td colspan="3"><span class="style2"> <html:text
						property="tipoDebito" size="4" maxlength="4"
						onkeyup="validaEnterComMensagem(event, 'exibirPesquisarTipoServicoAction.do', 'tipoDebito', 'Tipo do Débito');controlaDebitoCredito();" />
					<img src="imagens/pesquisa.gif" width="23" height="21"
						style="cursor: hand;"
						onclick="javascript:redirecionarSubmit('recuperarDadosPesquisarTipoServico.do?caminhoRetornoTelaPesquisaTipoDebito=exibirPesquisarTipoServicoAction');"
						alt="Pesquisar"><html:text property="dsTipoDebito" readonly="true"
						style="background-color:#EFEFEF; border:0" size="40"
						maxlength="40" /> <a href="javascript:limpar('tipoDebito');controlaDebitoCredito();"> <img
								border="0" title="Apagar"
								src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a></span></td>
				</tr>
				<!-- Tipo de Crédito -->
				<tr>
					<td width="200"><strong><span class="style2">Tipo de Crédito:</span></strong></td>
					<td colspan="3" align="left"><html:select property="tipoCredito"
						onchange="controlaDebitoCredito();">
						<html:option value="">&nbsp;</html:option>
						<html:options collection="colecaoTipoCredito"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<!-- Prioridade do Serviço -->
				<tr>
					<td width="200"><strong><span class="style2">Prioridade do
					Servi&ccedil;o:</span><font color="#FF0000"></font></strong></td>
					<td colspan="3" align="left"><html:select
						property="prioridadeServico" onchange="">
						<html:option value="">&nbsp;</html:option>
						<html:options collection="colecaoPrioridadeServico"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<!-- Perfil do Tipo de Serviço -->
				<tr>
					<td><strong>Perfil do Tipo de Servi&ccedil;o:<font color="#FF0000"></font></strong>
					</td>
					<td colspan="3"><span class="style2"> <html:text
						property="perfilServco" size="4" maxlength="4"
						onkeyup="validaEnterComMensagem(event, 'exibirPesquisarTipoServicoAction.do', 'perfilServco', 'Tipo do Perfil');" />
					<img src="imagens/pesquisa.gif" width="23" height="21"
						style="cursor: hand;"
						onclick="javascript:redirecionarSubmit('recuperarDadosPesquisarTipoServico.do?caminhoRetornoTelaPesquisaTipoPerfil=exibirPesquisarTipoServicoAction');"
						alt="Pesquisar"> <html:text property="dsPerfilServco"
						readonly="true" style="background-color:#EFEFEF; border:0"
						size="40" maxlength="40" /> <a href="javascript:limpar('servicoPerfilTipo');"> <img
								border="0" title="Apagar"
								src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a></span></td>
				</tr>
				<!-- Tipo do Serviço Referência -->
				<tr>
					<td><strong>Tipo de Servi&ccedil;o de Refer&ecirc;ncia: </strong></td>
					<td colspan="3"><span class="style2"> <html:text
						property="tipoServicoReferencia" size="4" maxlength="4"
						onkeyup="validaEnterComMensagem(event, 'exibirPesquisarTipoServicoAction.do', 'tipoServicoReferencia', 'Tipo Servico Referência');" />
					<img src="imagens/pesquisa.gif" width="23" height="21"
						style="cursor: hand;"
						onclick="javascript:redirecionarSubmit('recuperarDadosPesquisarTipoServico.do?caminhoRetornoTelaPesquisaTipoReferencia=exibirPesquisarTipoServicoAction');"
						alt="Pesquisar"> <html:text property="dsTipoServicoReferencia"
						readonly="true" style="background-color:#EFEFEF; border:0"
						size="40" maxlength="40" /> <a href="javascript:limpar('servicoTipoReferencia');"> <img
								border="0" title="Apagar"
								src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a></span></td>
				</tr>
				<!-- Atividades do Tipo de Serviço -->
				<tr>
					<td><strong>Atividade do Tipo de Serviço: </strong></td>
					<td colspan="3"><span class="style2"> <html:text
						property="atividadesTipoServico" size="4" maxlength="4"
						onkeyup="validaEnterComMensagem(event, 'exibirPesquisarTipoServicoAction.do', 'atividadesTipoServico', 'Atividade Tipo Servico');"/>
						<img src="imagens/pesquisa.gif" width="23" height="21"
						style="cursor: hand;" onclick="javascript:redirecionarSubmit('recuperarDadosPesquisarTipoServico.do?caminhoRetornoTelaPesquisaAtividade=exibirPesquisarTipoServicoAction');"
						alt="Pesquisar">
						
						<html:text property="dsAtividadesTipoServico"
						readonly="true" style="background-color:#EFEFEF; border:0; color:${PesquisarTipoServicoActionForm.dsAtividadeCor}"
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
							items="${PesquisarTipoServicoActionForm.arrayAtividade}">
							<tr bgcolor="#FFFFFF">
								<td>
								<div align="center"><a
									href="javascript:removeRowTableAtividades('${atividade.id}');">
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
				<tr>
					<td><strong>Materiais do Tipo de Serviço: </strong></td>
					<td colspan="3"><span class="style2"> <html:text
						property="tipoServicoMaterial" size="4" maxlength="4"
						onkeyup="validaEnterComMensagem(event, 'exibirPesquisarTipoServicoAction.do', 'tipoServicoMaterial', 'Materiais do Tipo de Serviço');" />
					<img src="imagens/pesquisa.gif" width="23" height="21"
						style="cursor: hand;"
						onclick="javascript:redirecionarSubmit('recuperarDadosPesquisarTipoServico.do?caminhoRetornoTelaPesquisaMaterial=exibirPesquisarTipoServicoAction');"
						alt="Pesquisar"> <html:text property="dsTipoServicoMaterial"
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
								items="${PesquisarTipoServicoActionForm.arrayMateriais}">
								<tr bgcolor="#FFFFFF">
									<td>
									<div align="center"><a
										href="javascript:removeRowTableMateriais('${material.id}');">
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
					<td colspan="2">&nbsp;</td>
				</tr>
				<!-- Botões -->
				<tr>
					<td align="left">
						<logic:present name="caminhoRetornoTelaPesquisaTipoServico">
							<INPUT TYPE="button" class="bottonRightCol" value="Voltar" onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaTipoServico}.do')"/>
						</logic:present>
						<input type="button" name="ButtonReset"
							class="bottonRightCol" value="Limpar"
						onClick="javascript:limparForm();"></td>
					<td colspan="3" align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Pesquisar" onclick="validaForm();">
					</td>
				</tr>
				<tr>
					<td height="24">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

</html:form>
</body>
</html:html>
