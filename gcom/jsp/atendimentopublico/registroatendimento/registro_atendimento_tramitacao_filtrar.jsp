<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
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
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
    <html:javascript staticJavascript="false"  formName="FiltrarRegistroAtendimentoTramitacaoActionForm"/>	
<script language="JavaScript">

	function validarForm(form){
		if(validateFiltrarRegistroAtendimentoTramitacaoActionForm(form)){
			submeterFormPadrao(form);
		}
	}	
	function reload() {
		var form = document.forms[0];
		
		if(form.tipoSolicitacao.selectedIndex == 0) {
			limparEspecificacao();
		}
		
		form.action = '/gsan/exibirFiltrarRegistroAtendimentoTramitacaoAction.do';
		form.submit();
	}	
	function habilitaEspecificacao() {
		var form = document.forms[0];
		if (form.selectedTipoSolicitacaoSize.value == '1') {	
			form.especificacao.disabled = false;	
		} else {
			form.especificacao.disabled = true;	
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
	var unidade = 0;
	
	function setUnidade(tipo) {
		unidade = tipo;
	}
	/* Recuperar Popup */
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
		
	  if (tipoConsulta == 'unidadeOrganizacional') {
		  form.unidadeAtualId.value = codigoRegistro;
		  form.unidadeAtualDescricao.value = descricaoRegistro;
		  form.unidadeAtualDescricao.style.color = '#000000';		      
	      unidade = 0;
	   } else if (tipoConsulta == 'unidadeSuperior') {
		  form.unidadeSuperiorId.value = codigoRegistro;
		  form.unidadeSuperiorDescricao.value = descricaoRegistro;
		  form.action = 'exibirFiltrarRegistroAtendimentoTramitacaoAction.do?validaUnidadeOrganizacional=true';
		  form.submit();
	    } else if (tipoConsulta == 'municipio') {
		  form.municipioId.value = codigoRegistro;
		  form.municipioDescricao.value = descricaoRegistro;
		  form.municipioDescricao.style.color = '#000000';		  
	    } else if (tipoConsulta == 'bairro') {
		  form.bairroId.value = codigoRegistro;
		  form.bairroDescricao.value = descricaoRegistro;
		  form.bairroDescricao.style.color = '#000000';
		  form.action = 'exibirFiltrarRegistroAtendimentoTramitacaoAction.do?validaMunicipio=true';	
		  form.submit();	 
	    } else if (tipoConsulta == 'logradouro') {
  	      form.logradouroId.value = codigoRegistro;
	      form.logradouroDescricao.value = descricaoRegistro;
	      form.logradouroDescricao.style.color = '#000000';
	    }
	}
	/* Limpar Form */
	function limparForm(){
		var form = document.forms[0];
		
		form.numeroRA.value = '';
		form.situacao[0].checked = true;
		form.tipoSolicitacao.selectedIndex = 0;	
		form.especificacao.selectedIndex = 0;		
		limparEspecificacao();
		form.periodoAtendimentoInicial.value = '';
		form.periodoAtendimentoFinal.value = '';	
		limparUnidadeAtual();
		limparUnidadeSuperior();
		limparMunicipio();
		limparBairro();
		form.areaBairroId.selectedIndex = 0;
		limparLogradouro();
	}
	/* Clear Especificação */
	function limparEspecificacao() {
		var form = document.forms[0];

		for(i=form.especificacao.length-1; i>0; i--) {
			form.especificacao.options[i] = null;
		}
	}

	/* Limpar Unidade Atual */
	function limparUnidadeAtual() {
		var form = document.forms[0];
		
      	form.unidadeAtualId.value = '';
	    form.unidadeAtualDescricao.value = '';
	}
	/* Limpar Unidade Superior */
	function limparUnidadeSuperior() {
		var form = document.forms[0];
		
      	form.unidadeSuperiorId.value = '';
	    form.unidadeSuperiorDescricao.value = '';
	}
	/* Limpar Município */
	function limparMunicipio() {
		var form = document.forms[0];
		
      	form.municipioId.value = '';
	    form.municipioDescricao.value = '';
	}
	/* Limpar Bairro */
	function limparBairro() {
		var form = document.forms[0];
		
      	form.bairroId.value = '';
	    form.bairroDescricao.value = '';
	    limparAreaBairro();
	}
	/* Limpar Área Bairro */
	function limparAreaBairro() {
		var form = document.forms[0];
		for(i=form.areaBairroId.length-1; i>0; i--) {
			form.areaBairroId.options[i] = null;
		}
	}
	/* Limpar Logradouro */
	function limparLogradouro() {
		var form = document.forms[0];
		
      	form.logradouroId.value = '';
	    form.logradouroDescricao.value = '';
	}
	/* Replica Data de Atendimento */
	function replicaDataAtendimento() {
		var form = document.forms[0];
		form.periodoAtendimentoFinal.value = form.periodoAtendimentoInicial.value
	}
	
	/* Acao do Botao ACQUAGIS */
	function importarMovimentoACQUAGIS() {
		var form = document.forms[0];
		form.action = '/gsan/filtrarRegistroAtendimentoTramitacaoAction.do?importarMovimentoACQUAGIS=sim'
		submeterFormPadrao(form);
		form.action = '/gsan/filtrarRegistroAtendimentoTramitacaoAction.do';
	}
		   
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/filtrarRegistroAtendimentoTramitacaoAction.do"
	name="FiltrarRegistroAtendimentoTramitacaoActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.FiltrarRegistroAtendimentoTramitacaoActionForm"
	method="post"
	onsubmit="return validateFiltrarRegistroAtendimentoTramitacaoActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellpadding="0" cellspacing="5">
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
					<td width="11"><img
						src="<bean:message key="caminho.imagens"/>parahead_left.gif"
						border="0" /></td>
					<td class="parabg">Selecionar Registro de Atendimento para
					Tramitação</td>
					<td width="11" valign="top"><img
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>Para selecionar o(s) registros(s) de atendimento, informe os
					dados abaixo, ou importe o movimento pelo bot&atilde;o ao lado:</td>
					<td>
						<logic:equal name="permissaoTramitarAcquagis" value="true">
						<gsan:controleAcessoBotao
						name="Button" value="ACQUAGIS"
						onclick="importarMovimentoACQUAGIS();"
						url="filtrarRegistroAtendimentoTramitacaoAction.do" />
						</logic:equal>
						<logic:equal name="permissaoTramitarAcquagis" value="false">
						<input type="button"  class="bottonRightCol" name="Button" value="ACQUAGIS" disabled="true" />
						</logic:equal>
					</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td><strong>Número do RA:</strong></td>
					<td width="66%">
						<html:text property="numeroRa" 
								   size="9"						
								   maxlength="9" 
								   onkeypress="return isCampoNumerico(event);"/>
					</td>
				</tr>
				<tr>
					<td><strong>Indicador de Tarifa Social:</strong></td>
					<td width="66%" align="right">
					<div align="left"><html:radio property="indicadorTarifaSocial"
						tabindex="4"
						value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>" />
					<strong>Sim</strong> <html:radio property="indicadorTarifaSocial"
						tabindex="5"
						value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>" />
					<strong>Não</strong> <html:radio property="indicadorTarifaSocial"
						tabindex="5" value="" /> <strong>Todos</strong></div>
					</td>
				</tr>
				<tr>
					<td height="24" colspan="7" bordercolor="#000000" class="style1">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong>Tipo de Solicita&ccedil;&atilde;o:</strong></td>
					<td colspan="6"><span class="style2"><strong> <html:select
						property="tipoSolicitacao" style="width: 230px; height:100px;"
						multiple="true"
						onchange="javascript:reload();habilitaEspecificacao();">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present name="colecaoTipoSolicitacao" scope="session">
							<html:options collection="colecaoTipoSolicitacao"
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select> </strong></span></td>
				</tr>
				<tr>
					<td><strong>Especifica&ccedil;&atilde;o:</strong></td>
					<td colspan="6"><span class="style2"><strong> <html:select
						property="especificacao" style="width: 230px;" multiple="true">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present name="colecaoEspecificacao" scope="session">
							<html:options collection="colecaoEspecificacao"
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select> </strong></span></td>
				</tr>
				<tr>
					<td height="24" colspan="7" bordercolor="#000000" class="style1">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong>Per&iacute;odo de Atendimento:</strong></td>
					<td colspan="6"><span class="style2"><strong> 
						<html:text property="periodoAtendimentoInicial" 
								   size="11" 
								   maxlength="10"
								   tabindex="3"
								   onkeyup="mascaraData(this, event);replicaDataAtendimento();" 
								   onkeypress="return isCampoNumerico(event);"/> 
							<a href="javascript:abrirCalendario('FiltrarRegistroAtendimentoTramitacaoActionForm', 'periodoAtendimentoInicial');">
								<img border="0"
									src="<bean:message key='caminho.imagens'/>calendario.gif"
									width="16" height="15" border="0" alt="Exibir Calendário"
									tabindex="4" />
							</a> a 
						<html:text property="periodoAtendimentoFinal" 
								   size="11" 
								   maxlength="10"
								   tabindex="3" 
								   onkeyup="mascaraData(this, event)" 
								   onkeypress="return isCampoNumerico(event);"/> 
								<a href="javascript:abrirCalendario('FiltrarRegistroAtendimentoTramitacaoActionForm', 'periodoAtendimentoFinal');">
									<img border="0"
										src="<bean:message key='caminho.imagens'/>calendario.gif"
										width="16" height="15" border="0" alt="Exibir Calendário"
										tabindex="4" />
								</a> </strong>(dd/mm/aaaa)<strong> </strong></span>
					</td>
				</tr>
				<tr>
					<td height="24" colspan="7" bordercolor="#000000" class="style1">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong>Munic&iacute;pio:</strong></td>
					<td colspan="6"><span class="style2"><strong> 
						<html:text property="municipioId" 
								   size="4" 
								   maxlength="4"
								   onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarRegistroAtendimentoTramitacaoAction.do?validaMunicipio=true', 'municipioId','Município');return isCampoNumerico(event);"
								   onkeyup="limparLogradouro();" /> 
								   <img src="<bean:message key='caminho.imagens'/>pesquisa.gif" 
								   		width="23"
										height="21" 
										name="imagem"
										onclick="chamarPopup('exibirPesquisarMunicipioAction.do', 'municipio', null, null, 275, 480, '',document.forms[0].municipioId);"
										alt="Pesquisar" title="Pesquisar Município"
										style="cursor: pointer;cursor:hand;"> 
										<logic:present name="municipioEncontrada" scope="session">
											<html:text property="municipioDescricao" 
													   readonly="true"
												       style="background-color:#EFEFEF; border:0; color: #000000"
												       size="40" 
												       maxlength="40" />
										</logic:present> 
										<logic:notPresent name="municipioEncontrada" scope="session">
											<html:text property="municipioDescricao" 
													   readonly="true"
   												       style="background-color:#EFEFEF; border:0; color: #ff0000"
												       size="40" 
												       maxlength="40" />
										</logic:notPresent> 
						<a href="javascript:limparMunicipio();"> 
							<img border="0" 
								 title="Apagar"
						 	     src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
					 	</a>
					</strong></span></td>
				</tr>
				<tr>
					<td><strong>Bairro:</strong></td>
					<td colspan="6"><span class="style2"><strong> 
						<html:text property="bairroId" 
								   size="4" 
								   maxlength="4"
								   onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarRegistroAtendimentoTramitacaoAction.do?validaBairro=true', 'bairroId','Bairro');return isCampoNumerico(event);" />
							<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
								width="23" height="21" name="imagem"
								onclick="chamarPopup('exibirPesquisarBairroAction.do?idMunicipio='+document.forms[0].municipioId.value+'&indicadorUsoTodos=1', 'bairro', null, null, 275, 480, '',document.forms[0].bairroId);"
								alt="Pesquisar"
								style="cursor: pointer;cursor:hand;"
								title="Pesquisar Bairro"> 
								<logic:present name="bairroEncontrada" scope="session">
									<html:text property="bairroDescricao" 
											   readonly="true"
										       style="background-color:#EFEFEF; border:0; color: #000000"
 										       size="40" 
 										       maxlength="40" />
								</logic:present> 
								<logic:notPresent name="bairroEncontrada" scope="session">
									<html:text property="bairroDescricao" 
											   readonly="true"
											   style="background-color:#EFEFEF; border:0; color: #ff0000"
											   size="40" 
											   maxlength="40" />
								</logic:notPresent> 
								<a href="javascript:limparBairro();"> 
									<img border="0" 
										title="Apagar"
										src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
								</a>
					</strong></span>
					</td>
				</tr>
				<tr>
					<td><strong>&Aacute;rea do Bairro:</strong></td>
					<td colspan="6"><span class="style2"><strong> <html:select
						property="areaBairroId" style="width: 230px;">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present name="colecaoBairroArea" scope="session">
							<html:options collection="colecaoBairroArea" labelProperty="nome"
								property="id" />
						</logic:present>
					</html:select> </strong><strong> </strong></span></td>
				</tr>
				<tr>
					<td><strong>Logradouro:</strong></td>
					<td colspan="6"><span class="style2"><strong> 
						<html:text property="logradouroId" 
								   size="9" 
								   maxlength="9"
						           onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarRegistroAtendimentoTramitacaoAction.do?validaLogradouro=true', 'logradouroId','Logradouro');return isCampoNumerico(event);" />
							<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
								width="23" height="21" name="imagem"
								onclick="abrirPopup('exibirPesquisarLogradouroAction.do?codigoMunicipio='+document.forms[0].municipioId.value+'&codigoBairro='+document.forms[0].bairroId.value+'&indicadorUsoTodos=1&primeriaVez=1', 275, 480);"
								alt="Pesquisar" title="Pesquisar Logradouro"
								style="cursor: pointer;cursor:hand;"> 
							<logic:present name="logradouroEncontrada" scope="session">
								<html:text property="logradouroDescricao" 
										   readonly="true"
										   style="background-color:#EFEFEF; border:0; color: #000000"
										   size="35" 
										   maxlength="35" />
							</logic:present> 
							<logic:notPresent name="logradouroEncontrada" scope="session">
								<html:text property="logradouroDescricao" 
										   readonly="true"
									       style="background-color:#EFEFEF; border:0; color: #ff0000"
									       size="35" 
									       maxlength="35" />
							</logic:notPresent> 
								<a href="javascript:limparLogradouro();"> 
									<img border="0" 
										 title="Apagar"
								         src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
								</a>
						</strong></span>
					</td>
				</tr>
				<tr>
					<td height="24" colspan="7" bordercolor="#000000" class="style1">
					<hr>
					</td>
				</tr>
				<tr> 
                <td><strong>Perfil do Imóvel:</strong></td>
                  <td colspan="6"><span class="style2"><strong>
					<html:select property="colecaoPerfilImovel" style="width: 350px; height:100px;" multiple="true">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present name="colecaoPerfilImovel" scope="session">
							<html:options collection="colecaoPerfilImovel" labelProperty="descricao" property="id" />
						</logic:present>
					</html:select>
                  </strong></span></td>
                </tr>
				<tr>
					<td height="24" colspan="7" bordercolor="#000000" class="style1">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong> Unidade Atual:</strong></td>
					<td colspan="6" align="right">
					<div align="left"><span class="style2"><strong> 
						<html:text property="unidadeAtualId" 
								   size="4" 
								   maxlength="4"
								   onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarRegistroAtendimentoTramitacaoAction.do?validaUnidadeAtual=true', 'unidadeAtualId','Unidade Atual');return isCampoNumerico(event);" />
							<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
								 width="23" 
								 height="21" 
								 name="imagem"
								 onclick="setUnidade(2); chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do', 'unidadeOrganizacional', null, null, 275, 480, '',document.forms[0].unidadeAtualId);"
								 alt="Pesquisar" title="Pesquisar Unidade"
								 style="cursor: pointer;cursor:hand;"> 
						<logic:present name="unidadeAtualEncontrada" scope="session">
							<html:text property="unidadeAtualDescricao" 
									   readonly="true"
								       style="background-color:#EFEFEF; border:0; color: #000000"
								       size="40" 
								       maxlength="40" />
						</logic:present> 
						<logic:notPresent name="unidadeAtualEncontrada" scope="session">
							<html:text property="unidadeAtualDescricao" 
									   readonly="true"
								       style="background-color:#EFEFEF; border:0; color: #ff0000"
								       size="40" 
								       maxlength="40" />
						</logic:notPresent> 
							<a href="javascript:limparUnidadeAtual();"> 
								<img border="0" 
									 title="Apagar"
									 src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
							</a>
					</strong></span>
					</div>
					</td>
				</tr>
				<tr>
					<td><strong> Unidade Superior:</strong></td>
					<td colspan="6" align="right">
					<div align="left"><span class="style2"><strong> 
						<html:text property="unidadeSuperiorId" 
								   size="4" 
								   maxlength="4"
								   onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarRegistroAtendimentoTramitacaoAction.do?validaUnidadeSuperior=true', 'unidadeSuperiorId','Unidade Superior');return isCampoNumerico(event);" />
							<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
								width="23" height="21" name="imagem"
								onclick="setUnidade(3); chamarPopup('exibirPesquisarUnidadeSuperiorAction.do', 'unidadeOrganizacional', null, null, 275, 480, '',document.forms[0].unidadeSuperiorId);"
								alt="Pesquisar" title="Pesquisar Unidade"
								style="cursor: pointer;cursor:hand;"> 
						<logic:present name="unidadeSuperiorEncontrada" scope="session">
							<html:text property="unidadeSuperiorDescricao" 
									   readonly="true"
									   style="background-color:#EFEFEF; border:0; color: #000000"
									   size="40" 
									   maxlength="40" />
						</logic:present> 
						<logic:notPresent name="unidadeSuperiorEncontrada" scope="session">
							<html:text property="unidadeSuperiorDescricao" 
									   readonly="true"
								       style="background-color:#EFEFEF; border:0; color: #ff0000"
								       size="40" 
								       maxlength="40" />
						</logic:notPresent> 
						<a href="javascript:limparUnidadeSuperior();">
							<img border="0" title="Apagar"
							src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
						</a>
					</strong></span>
					</div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="6" align="right">&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="6">
					<div align="right"></div>
					</td>
				</tr>
				<tr>
					<td><input type="button" class="bottonRightCol"
						value="Limpar"
						onClick="javascript:window.location.href='/gsan/exibirFiltrarRegistroAtendimentoTramitacaoAction.do?menu=sim'"></td>
					<td width="66%" align="right"><gsan:controleAcessoBotao
						name="Button" value="Selecionar"
						onclick="javascript:validarForm(document.FiltrarRegistroAtendimentoTramitacaoActionForm);"
						url="filtrarRegistroAtendimentoTramitacaoAction.do" /></td>
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
