<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarRegistroAtendimentoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(form){
		/*if(validatePesquisarRegistroAtendimentoActionForm(form) && validarFormatacaoNumeracaoRAManual(form.numeroRAManual, "Número Manual")){
    		submeterFormPadrao(form);
		}*/
		
		if(validatePesquisarRegistroAtendimentoActionForm(form)){
    		if (validaQtdeRAReiteradas(form)) {
    			form.submit();
    		}
		}
	}
	
	function validaQtdeRAReiteradas(form){
		
		if (form.quantidadeRAReiteradasInicial.value != null && form.quantidadeRAReiteradasInicial.value != '' && 
			(form.quantidadeRAReiteradasFinal.value == null || form.quantidadeRAReiteradasFinal.value == '')) {
			alert('Informe Quantidade de RA Reiteradas Final');
			return false;
		} else if (form.quantidadeRAReiteradasFinal.value != null && form.quantidadeRAReiteradasFinal.value != '' && 
			(form.quantidadeRAReiteradasInicial.value == null || form.quantidadeRAReiteradasInicial.value == '')) {
			alert('Informe Quantidade de RA Reiteradas Inicial');
			return false;
		} else if (form.quantidadeRAReiteradasInicial.value != null && form.quantidadeRAReiteradasInicial.value != '' && 
			form.quantidadeRAReiteradasFinal.value != null && form.quantidadeRAReiteradasFinal.value != '') {
			if (form.quantidadeRAReiteradasInicial.value > form.quantidadeRAReiteradasFinal.value) {
				alert('Quantidade de RA Reiteradas Inicial não pode ser maior que Quantidade de RA Reiteradas Final');
				return false;
			}	
		}
		
		return true;
	}

	function reload() {
		var form = document.forms[0];

		form.action = '/gsan/exibirPesquisarRegistroAtendimentoAction.do?validaEspecificacao=true';
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

	    if (tipoConsulta == 'imovel') {
	      form.matriculaImovel.value = codigoRegistro;
	      form.inscricaoImovel.value = descricaoRegistro;
		  form.inscricaoImovel.style.color = '#000000';	      
	    } else if (tipoConsulta == 'unidadeOrganizacional') {
	      if (unidade == 1) {
		      form.unidadeAtendimentoId.value = codigoRegistro;
		      form.unidadeAtendimentoDescricao.value = descricaoRegistro;
			  form.unidadeAtendimentoDescricao.style.color = '#000000';
	      } else if (unidade == 2) {
		      form.unidadeAtualId.value = codigoRegistro;
		      form.unidadeAtualDescricao.value = descricaoRegistro;
			  form.unidadeAtualDescricao.style.color = '#000000';		      
	      }
	      unidade = 0;
	    } else if (tipoConsulta == 'unidadeSuperior') {
		      form.unidadeSuperiorId.value = codigoRegistro;
		      form.unidadeSuperiorDescricao.value = descricaoRegistro;
		      form.action = 'exibirFiltrarRegistroAtendimentoAction.do?validaUnidadeOrganizacional=true';
		      form.submit();
	    } else if (tipoConsulta == 'municipio') {
		    form.municipioId.value = codigoRegistro;
		    form.municipioDescricao.value = descricaoRegistro;
		    form.municipioDescricao.style.color = '#000000';
	    } else if (tipoConsulta == 'bairro') {
	    	form.bairroId.value = "";
		    form.bairroCodigo.value = codigoRegistro;
		    form.bairroDescricao.value = descricaoRegistro;
		    form.bairroDescricao.style.color = '#000000';
	    } else if (tipoConsulta == 'logradouro') {
  	      	form.logradouroId.value = codigoRegistro;
	      	form.logradouroDescricao.value = descricaoRegistro;
	      	form.logradouroDescricao.style.color = '#000000';
	    } else if (tipoConsulta == 'usuario') {
  	      	form.loginUsuario.value = codigoRegistro;
	      	form.nomeUsuario.value = descricaoRegistro;
	      	form.nomeUsuario.style.color = '#000000';
	    }
	}
	
	/* Clear Especificação */
	function limparEspecificacao() {
		var form = document.forms[0];

		for(i=form.especificacao.length-1; i>0; i--) {
			form.especificacao.options[i] = null;
		}
	}
	
	/* Limpar Imóvel */
	function limparFormImovel() {
		var form = document.forms[0];
		
      	form.matriculaImovel.value = '';
	    form.inscricaoImovel.value = '';
	    
	    form.matriculaImovel.focus();
	}
	/* Limpar Unidade Atendimento */
	function limparUnidadeAtendimento() {
		var form = document.forms[0];
		
      	form.unidadeAtendimentoId.value = '';
	    form.unidadeAtendimentoDescricao.value = '';
	
		form.unidadeAtendimentoId.focus();
	}
	/* Limpar Usuário */
	function limparUsuario() {
		var form = document.forms[0];
		
      	form.loginUsuario.value = '';
	    form.nomeUsuario.value = '';
	
		form.loginUsuario.focus();
	}	
	/* Limpar Unidade Atual */
	function limparUnidadeAtual() {
		var form = document.forms[0];
		
      	form.unidadeAtualId.value = '';
	    form.unidadeAtualDescricao.value = '';
	
		form.unidadeAtualId.focus();
	}
	/* Limpar Unidade Superior */
	function limparUnidadeSuperior() {
		var form = document.forms[0];
		
      	form.unidadeSuperiorId.value = '';
	    form.unidadeSuperiorDescricao.value = '';
	
		form.unidadeSuperiorId.focus();
	}
	/* Limpar Município */
	function limparMunicipio() {
		var form = document.forms[0];
		
      	form.municipioId.value = '';
	    form.municipioDescricao.value = '';
	    limparBairro();
	
		form.municipioId.focus();
	}
	/* Limpar Município */
	function limparMunicipioDigitacao() {
		var form = document.forms[0];
		
      	form.municipioDescricao.value = '';
	    limparBairro();
	}
	/* Limpar Bairro */
	function limparBairro() {
		var form = document.forms[0];
		
      	form.bairroId.value = '';
	    form.bairroCodigo.value = '';
	    form.bairroDescricao.value = '';
	    limparAreaBairro();
	    
	    form.bairroCodigo.focus();
	}
	/* Limpar Bairro */
	function limparBairroDigitacao() {
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
	
		form.logradouroId.focus();
	}
	
	/* Replica Data de Atendimento */
	function replicaDataAtendimento() {
		var form = document.forms[0];
		form.periodoAtendimentoFinal.value = form.periodoAtendimentoInicial.value
	}
	/* Replica Data de Encerramento */	
	function replicaDataEncerramento() {
		var form = document.forms[0];
		form.periodoEncerramentoFinal.value = form.periodoEncerramentoInicial.value
	}
	
		/* Limpar Form */
	function limparForm(){
		var form = document.forms[0];
		
		form.numeroRA.value = '';
		limparFormImovel();
		form.situacao[0].checked = true;
		form.tipoSolicitacao.selectedIndex = 0;	
		form.especificacao.selectedIndex = 0;		
		limparEspecificacao();
		form.periodoAtendimentoInicial.value = '';
		form.periodoAtendimentoFinal.value = '';
		form.periodoEncerramentoInicial.value = '';
		form.periodoEncerramentoFinal.value = '';		
		limparUnidadeAtendimento();
		limparUnidadeAtual();
//		limparUnidadeSuperior();
		limparMunicipio();
		limparBairro();
		form.areaBairroId.selectedIndex = 0;
		limparLogradouro();
		limparUsuario();
		
	}	
</script>
</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(730, 700);">
<html:form action="/pesquisarRegistroAtendimentoAction" method="post" onsubmit="window.focus(); return validatePesquisarRegistroAtendimentoActionForm(this); javascript:setarFoco('${requestScope.nomeCampo}');">

<html:hidden property="selectedTipoSolicitacaoSize" />
<html:hidden property="situacaoId"/>

	<table width="685" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="675" valign="top" class="centercoltext">
				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			        <tr>
			          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
			          <td class="parabg">Pesquisar Registro de Atendimento</td>
			          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
			        </tr>
				</table>
				<p>&nbsp;</p>
				<table width="100%" border="0">
					<tr>
						<td colspan="4">Preencha os campos para pesquisar registros de atendimento:</td>
						<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}atendimentoRegistroPesquisar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
						</logic:present>
						<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
						</logic:notPresent>
					</tr>
				</table>
					
			<table width="100%" border="0">
              <tr> 
                <td><strong>N&uacute;mero do RA:</strong></td>
                <td colspan="6"><span class="style2"><strong>
                  <html:text property="numeroRA" size="9" maxlength="9"/>
                  </strong></span></td>
              </tr>
              <tr> 
                <td><strong>N&uacute;mero do Protocolo:</strong></td>
                <td colspan="6"><span class="style2"><strong>
                  <html:text property="numeroProtocoloAtendimento" size="15" maxlength="14"/>
                  </strong></span></td>
              </tr>
              <tr> 
                <td><strong>Número Manual:</strong></td>
                <td colspan="6">
                
                	<%-- <html:text property="numeroRAManual" size="12" maxlength="11" onkeyup="mascaraNumeracaoManual(event, this);"/><strong>&nbsp;(000000000-0)</strong> --%>
                	
                	<html:text property="numeroRAManual" size="12" maxlength="11"/>
                </td>
              </tr>
              <tr> 
                <td><strong>RA Reiteradas:</strong></td>
                <td colspan="6"><span class="style2">
						<html:text property="quantidadeRAReiteradasInicial" size="5" maxlength="3" />
						<strong> a </strong><html:text property="quantidadeRAReiteradasFinal" size="5" maxlength="3" />
					</span></td></tr>
              <tr> 
                <td><strong><span class="style2">Matr&iacute;cula do Im&oacute;vel</span>:</strong></td>
                <td colspan="6"><span class="style2"><strong>
					<html:text property="matriculaImovel" size="9" maxlength="9" 
							   onkeypress="javascript:validaEnterComMensagem(event, 'exibirPesquisarRegistroAtendimentoAction.do?validaImovel=true', 'matriculaImovel','Matrícula do Imovel');"/>
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" 
						 style="cursor:hand;" name="imagem"	onclick="redirecionarSubmit('exibirPesquisarRegistroAtendimentoAction.do?pesquisarImovel=OK', 275, 480);"
						 alt="Pesquisar">
					
					<logic:present name="inscricaoImovelEncontrada" scope="session">
						<html:text property="inscricaoImovel" readonly="true" 
								   style="background-color:#EFEFEF; border:0; color: #000000" size="40" maxlength="40" />
					</logic:present> 

					<logic:notPresent name="inscricaoImovelEncontrada" scope="session">
						<html:text property="inscricaoImovel" readonly="true" 
								   style="background-color:#EFEFEF; border:0; color: #ff0000" size="40" maxlength="40" />
					</logic:notPresent>  						 

					
					
						<a href="javascript:limparFormImovel();">
							<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
                  </strong></span></td>
              </tr>
              
              <tr> 
                <td><strong>Login do usuário:</strong></td>
                <td colspan="6"><span class="style2"><strong>
					<html:text property="loginUsuario" 
						size="12" 
						maxlength="11"
						style="text-transform: none;"
						onkeypress="javascript:pesquisaEnterSemUpperCase(event, 'exibirPesquisarRegistroAtendimentoAction.do', 'loginUsuario');"/>
						
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" 
						width="23"	
						height="21" 
					 	style="cursor:hand;" 
					 	name="imagem"	
					 	onclick="redirecionarSubmit('exibirUsuarioPesquisar.do?mostrarLogin=S&caminhoRetornoTelaPesquisaUsuario=exibirPesquisarRegistroAtendimentoAction', 275, 480);"
					 	alt="Pesquisar">						
						 
					<logic:present name="usuarioEncontrado" scope="session">
						<html:text property="nomeUsuario" 
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" 
							size="40" 
							maxlength="40" />
							
					</logic:present> 

					<logic:notPresent name="usuarioEncontrado" scope="session">
						<html:text property="nomeUsuario" 
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" 
							size="40" 
							maxlength="40" />
					</logic:notPresent>
					
					<a href="javascript:limparUsuario();">
						<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
					</a>
                  </strong></span></td>
              </tr>              
              <tr> 
                <td><strong>Situa&ccedil;&atilde;o:</strong></td>
                <td colspan="6"><span class="style2"><strong> 
                  <label> </label>
				  <html:radio property="situacao" value="0" onclick="javascript: reload();"/>
 				  Todos
                  <label> 
				  <html:radio property="situacao" value="1" onclick="javascript: reload();"/>
 				  Pendentes</label>
                  <label> 
				  <html:radio property="situacao" value="2" onclick="javascript: reload();"/>
				  Encerrados</label>
				  <html:radio property="situacao" value="3" onclick="javascript: reload();"/>
				  Sem Local de Ocorr&ecirc;ncia</strong></span></td>
              </tr>
              <tr> 
                <td height="24" colspan="7" bordercolor="#000000" class="style1"> 
                  <hr></td>
              </tr>
              <tr> 
                <td><strong>Tipo de Solicita&ccedil;&atilde;o:</strong></td>
                <td colspan="6"><span class="style2"><strong>
					<html:select property="tipoSolicitacao" style="width: 350px; height:100px;" multiple="true" onchange="javascript:reload();habilitaEspecificacao();">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present name="colecaoTipoSolicitacao" scope="session">
							<html:options collection="colecaoTipoSolicitacao" labelProperty="descricao" property="id" />
						</logic:present>
					</html:select>                
                  </strong></span></td>
              </tr>
              <tr> 
                <td><strong>Especifica&ccedil;&atilde;o:</strong></td>
                <td colspan="6"><span class="style2"><strong> 
					<html:select property="especificacao" style="width: 350px;" multiple="true">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present name="colecaoEspecificacao" scope="session">
							<html:options collection="colecaoEspecificacao" labelProperty="descricao" property="id" />
						</logic:present>
					</html:select>                
                  </strong></span></td>
              </tr>
              <tr> 
                <td height="24" colspan="7" bordercolor="#000000" class="style1"> 
                  <hr></td>
              </tr>
              <tr> 
                <td><strong>Per&iacute;odo de Atendimento:</strong></td>
                <td colspan="6"><span class="style2"><strong> 
					<logic:equal name="PesquisarRegistroAtendimentoActionForm" property="situacao" value="2">
						<html:text property="periodoAtendimentoInicial" size="11" maxlength="10" tabindex="3" onkeyup="mascaraData(this, event)" readonly="true" value=""/>
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/>
						a <html:text property="periodoAtendimentoFinal" size="11" maxlength="10" tabindex="3" onkeyup="mascaraData(this, event)"  readonly="true" value=""/>
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/>
					</logic:equal>
					
					<logic:notEqual name="PesquisarRegistroAtendimentoActionForm" property="situacao" value="2">
						<html:text property="periodoAtendimentoInicial" size="11" maxlength="10" tabindex="3" onkeyup="mascaraData(this, event);replicaDataAtendimento();"/>
						<a href="javascript:abrirCalendario('PesquisarRegistroAtendimentoActionForm', 'periodoAtendimentoInicial');">
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/></a>
						a <html:text property="periodoAtendimentoFinal" size="11" maxlength="10" tabindex="3" onkeyup="mascaraData(this, event)"/>
						<a href="javascript:abrirCalendario('PesquisarRegistroAtendimentoActionForm', 'periodoAtendimentoFinal');">
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/></a>
					</logic:notEqual>
					</strong>(dd/mm/aaaa)<strong> 
                  </strong></span></td>
              </tr>
              <tr> 
                <td><strong>Per&iacute;odo de Encerramento:</strong></td>
                <td colspan="6"><span class="style2"><strong> 
					<logic:equal name="PesquisarRegistroAtendimentoActionForm" property="situacao" value="1">
						<html:text property="periodoEncerramentoInicial" size="11" maxlength="10" tabindex="3" onkeyup="mascaraData(this, event)"  readonly="true" value=""/>
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/>
						a <html:text property="periodoEncerramentoFinal" size="11" maxlength="10" tabindex="3" onkeyup="mascaraData(this, event)"  readonly="true" value=""/>
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/>
					</logic:equal>
					
					<logic:notEqual name="PesquisarRegistroAtendimentoActionForm" property="situacao" value="1">
						<html:text property="periodoEncerramentoInicial" size="11" maxlength="10" tabindex="3" onkeyup="mascaraData(this, event);replicaDataEncerramento();"/>
						<a href="javascript:abrirCalendario('PesquisarRegistroAtendimentoActionForm', 'periodoEncerramentoInicial');">
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/></a>
						a <html:text property="periodoEncerramentoFinal" size="11" maxlength="10" tabindex="3" onkeyup="mascaraData(this, event)"/>
						<a href="javascript:abrirCalendario('PesquisarRegistroAtendimentoActionForm', 'periodoEncerramentoFinal');">
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/></a>
					</logic:notEqual>
					</strong>(dd/mm/aaaa)<strong> 
                  </strong></span></td>
              </tr>
              <tr> 
                <td height="24" colspan="7" bordercolor="#000000" class="style1"> 
                  <hr></td>
              </tr>
              <tr> 
                <td><strong>Unidade de Atendimento:</strong></td>
                <td colspan="6"><span class="style2"><strong>
					<html:text property="unidadeAtendimentoId" size="4" maxlength="4"
							   onkeypress="javascript:validaEnterComMensagem(event, 'exibirPesquisarRegistroAtendimentoAction.do?validaUnidadeAtendimento=true', 'unidadeAtendimentoId','Unidade Atendimento');"/>
					
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" 
						 style="cursor:hand;" name="imagem"	onclick="setUnidade(1); redirecionarSubmit('exibirPesquisarRegistroAtendimentoAction.do?pesquisarUnidadeAtendimento=OK', 275, 500);"
						 alt="Pesquisar">
					
					<logic:present name="unidadeAtendimentoEncontrada" scope="session">
						<html:text property="unidadeAtendimentoDescricao" readonly="true"
							   style="background-color:#EFEFEF; border:0; color: #000000" size="40" maxlength="40" />
					</logic:present> 

					<logic:notPresent name="unidadeAtendimentoEncontrada" scope="session">
						<html:text property="unidadeAtendimentoDescricao" readonly="true"
							   style="background-color:#EFEFEF; border:0; color: #ff0000" size="40" maxlength="40" />
					</logic:notPresent>
					
										
					<a href="javascript:limparUnidadeAtendimento();">
						<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
                  </strong></span>
                  
                  </td>
              </tr>
              <tr> 
                <td><strong> Unidade Atual:</strong></td>
                <td colspan="6" align="right"><div align="left"><span class="style2"><strong> 
					<html:text property="unidadeAtualId" size="4" maxlength="4"
							   onkeypress="javascript:validaEnterComMensagem(event, 'exibirPesquisarRegistroAtendimentoAction.do?validaUnidadeAtual=true', 'unidadeAtualId','Unidade Atual');"/>
					
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" 
						 style="cursor:hand;" name="imagem"	onclick="setUnidade(2); redirecionarSubmit('exibirPesquisarRegistroAtendimentoAction.do?pesquisarUnidadeAtual=OK', 275, 500);"
						 alt="Pesquisar">
					
					<logic:present name="unidadeAtualEncontrada" scope="session">
						<html:text property="unidadeAtualDescricao" readonly="true"
							   style="background-color:#EFEFEF; border:0; color: #000000" size="40" maxlength="40" />
					</logic:present> 

					<logic:notPresent name="unidadeAtualEncontrada" scope="session">
						<html:text property="unidadeAtualDescricao" readonly="true"
							   style="background-color:#EFEFEF; border:0; color: #ff0000" size="40" maxlength="40" />
					</logic:notPresent>
					
					
					<a href="javascript:limparUnidadeAtual();">
						<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
                    </strong></span></div>
                    
                    </td>
              </tr>
              <%-- <tr> 
                <td><strong> Unidade Superior:</strong></td>
                <td colspan="6" align="right"><div align="left"><span class="style2"><strong> 
					<html:text property="unidadeSuperiorId" size="4" maxlength="4"
							   onkeypress="javascript:validaEnterComMensagem(event, 'exibirPesquisarRegistroAtendimentoAction.do?validaUnidadeSuperior=true', 'unidadeSuperiorId','Unidade Superior');"/>
					
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" 
						 style="cursor:hand;" name="imagem"	onclick="setUnidade(3); chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do', 'unidadeOrganizacional', null, null, 275, 480, '',document.forms[0].unidadeSuperiorId);"
						 alt="Pesquisar">
					
					<html:text property="unidadeSuperiorDescricao" readonly="true"
							   style="background-color:#EFEFEF; border:0; color: #ff0000" size="40" maxlength="40" />
					
					<a href="javascript:limparUnidadeSuperior();">
						<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
                    </strong></span></div>
                    
                    </td>
              </tr> --%>
              <tr> 
                <td height="24" colspan="7" bordercolor="#000000" class="style1"> 
                  <hr></td>
              </tr>
              <tr> 
                <td><strong>Munic&iacute;pio:</strong></td>
                <td colspan="6"><span class="style2"><strong> 
					<html:text property="municipioId" size="4" maxlength="4"
							   onkeypress="javascript:limparBairroDigitacao();validaEnterComMensagem(event, 'exibirPesquisarRegistroAtendimentoAction.do?validaMunicipio=true', 'municipioId','Município');"/>
					
					<a href="javascript:redirecionarSubmit('exibirPesquisarRegistroAtendimentoAction.do?pesquisarMunicipio=OK', 370, 485);">
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" name="imagem" alt="Pesquisar" border="0"></a>

					<logic:present name="municipioEncontrada" scope="session">
						<html:text property="municipioDescricao" readonly="true"
								   style="background-color:#EFEFEF; border:0; color: #000000" size="40" maxlength="40" />
					</logic:present> 

					<logic:notPresent name="municipioEncontrada" scope="session">
						<html:text property="municipioDescricao" readonly="true"
								   style="background-color:#EFEFEF; border:0; color: #ff0000" size="40" maxlength="40" />
					</logic:notPresent>

					<a href="javascript:limparMunicipio();">
						<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
                  </strong></span></td>
              </tr>
              <tr> 
                <td><strong>Bairro:</strong></td>
                <td colspan="6"><span class="style2"><strong> 
					<html:text property="bairroCodigo" size="4" maxlength="4"
							   onkeypress="javascript:limparBairroDigitacao();validaEnterDependencia(event, 'exibirPesquisarRegistroAtendimentoAction.do?validaBairro=true', this, document.forms[0].municipioId.value, 'Município');"/>
					
					<a href="javascript:abrirPopupDependencia('exibirPesquisarBairroAction.do?idMunicipio='+document.forms[0].municipioId.value+'&indicadorUsoTodos=1&caminhoRetornoTelaPesquisaBairro=exibirPesquisarRegistroAtendimentoAction&tipo=semBairro', document.forms[0].municipioId.value, 'Município', 275, 480);">
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" name="imagem" alt="Pesquisar" border="0"></a>

					<logic:present name="bairroEncontrada" scope="session">
						<html:text property="bairroDescricao" readonly="true"
									   style="background-color:#EFEFEF; border:0; color: #000000" size="40" maxlength="40" />
					</logic:present> 

					<logic:notPresent name="bairroEncontrada" scope="session">
						<html:text property="bairroDescricao" readonly="true"
								   style="background-color:#EFEFEF; border:0; color: #ff0000" size="40" maxlength="40" />
					</logic:notPresent>

					<html:hidden property="bairroId"/>
					
					<a href="javascript:limparBairro();">
						<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
                  </strong></span></td>
              </tr>
              <tr> 
                <td><strong>&Aacute;rea do Bairro:</strong></td>
                <td colspan="6"><span class="style2"><strong> 
					<html:select property="areaBairroId" style="width: 230px;">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present name="colecaoBairroArea" scope="session">
							<html:options collection="colecaoBairroArea" labelProperty="nome" property="id" />
						</logic:present>
					</html:select>
                  </strong><strong> </strong></span></td>
              </tr>
              <tr> 
                <td><strong>Logradouro:</strong></td>
                <td colspan="6"><span class="style2"><strong> 
					<html:text property="logradouroId" size="9" maxlength="9"
							   onkeypress="javascript:validaEnterComMensagem(event, 'exibirPesquisarRegistroAtendimentoAction.do?validaLogradouro=true', 'logradouroId','Logradouro');"/>
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" 
						 style="cursor:hand;" name="imagem"	onclick="redirecionarSubmit('exibirPesquisarLogradouroAction.do?caminhoRetornoTelaPesquisaLogradouro=exibirPesquisarRegistroAtendimentoAction&bloquearMunicipio=S&primeriaVez=1&codigoMunicipio='+document.forms[0].municipioId.value+'&codigoBairro='+document.forms[0].bairroCodigo.value, 485, 520);"
						 alt="Pesquisar">
					
					
					<logic:present name="logradouroEncontrada" scope="session">
						<html:text property="logradouroDescricao" readonly="true"
							   style="background-color:#EFEFEF; border:0; color: #000000" size="40" maxlength="40" />
					</logic:present> 

					<logic:notPresent name="logradouroEncontrada" scope="session">
						<html:text property="logradouroDescricao" readonly="true"
							   style="background-color:#EFEFEF; border:0; color: #ff0000" size="40" maxlength="40" />
					</logic:notPresent>
					
					<a href="javascript:limparLogradouro();">
						<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
                  
                  </strong></span></td>
              </tr>
              <tr> 
                <td>&nbsp;</td>
                <td colspan="6" align="right">&nbsp;</td>
              </tr>
              <tr> 
                <td>&nbsp;</td>
                <td colspan="6"> <div align="right"> </div></td>
              </tr>
              </table>
              
              <table width="100%" border="0">
  			  <tr>
				<td>
			          	<div align="left">
			          	
			          	<logic:present name="caminhoRetornoTelaPesquisaRegistroAtendimento">
			          		<input type="button" 
			          			class="bottonRightCol" 
			          			value="Voltar" 
			          			onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaRegistroAtendimento}.do')" style="width: 70px;"/>
			          	</logic:present>
				
						<input name="Button" type="button" class="bottonRightCol" value="Limpar" 
							   onclick="javascript:limparForm();" style="width: 70px;">
				
						</div>
					</td>
					<td>	
						<div align="right">
							<input type="button" name="Button"	class="bottonRightCol" value="Pesquisar"
							   onClick="javascript:validarForm(document.forms[0])" />
						</div>
				</td>
				</tr>
				</table>
			</td>
		</tr>
	</table>
	<p>&nbsp;</p>
</html:form>
</body>
</html:html>