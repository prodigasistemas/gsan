<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarRegistroAtendimentoActionForm"/>

<SCRIPT LANGUAGE="JavaScript">
	function reload() {
		var form = document.forms[0];
		
		if(form.tipoSolicitacao.selectedIndex == 0) {
			limparEspecificacao();
		}
		
		form.action = '/gsan/exibirFiltrarRegistroAtendimentoAction.do';
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
		
		//form.action='exibirFiltrarRegistroAtendimentoAction.do';
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
	      } else if (unidade == 4) {
		      form.unidadeAnteriorId.value = codigoRegistro;
		      form.unidadeAnteriorDescricao.value = descricaoRegistro;
			  form.unidadeAnteriorDescricao.style.color = '#000000';		      
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
	    //form.submit();
	}
	
    function limparPeriodoAtendimento() {
        var form = document.forms[0];
        
        if (form.periodoAtendimentoInicial.value != ''){
			form.periodoAtendimentoInicial.value="";
		}
		
		if (form.periodoAtendimentoFinal.value != ''){
			form.periodoAtendimentoFinal.value="";
		}			
  	}

    function limparPeriodos() {
        var form = document.forms[0];
        
        if (form.periodoAtendimentoInicial.value != ''){
			form.periodoAtendimentoInicial.value="";
		}
		if (form.periodoAtendimentoFinal.value != ''){
			form.periodoAtendimentoFinal.value="";
		}			
        if (form.periodoEncerramentoInicial.value != ''){
			form.periodoEncerramentoInicial.value="";
		}
		if (form.periodoEncerramentoFinal.value != ''){
			form.periodoEncerramentoFinal.value="";
		}			
        if (form.periodoTramitacaoInicial.value != ''){
			form.periodoTramitacaoInicial.value="";
		}
		if (form.periodoTramitacaoFinal.value != ''){
			form.periodoTramitacaoFinal.value="";
		}			
  	}

	
	/* Limpar Form */
	function limparForm(){
		var form = document.forms[0];
		
		form.numeroRA.value = "";
		form.numeroProtocoloAtendimento.value = "";
		form.numeroRAManual.value = "";
		limparFormImovel();
		limparUsuario();
		form.situacao[0].checked = true;
		form.tipoSolicitacao.selectedIndex = 0;	
		form.especificacao.selectedIndex = 0;		
		limparEspecificacao();
		form.periodoAtendimentoInicial.value = "";
		form.periodoAtendimentoFinal.value = "";
		form.periodoEncerramentoInicial.value = "";
		form.periodoEncerramentoFinal.value = "";
		form.periodoTramitacaoInicial.value = "";
		form.periodoTramitacaoFinal.value = "";		
		limparUnidadeAtendimento();
		limparUnidadeAtual();
		limparUnidadeSuperior();
		limparMunicipio();
		limparBairro();
		form.areaBairroId.selectedIndex = 0;
		limparAreaBairro();
		limparLogradouro();
		
		window.location.href = '/gsan/exibirFiltrarRegistroAtendimentoAction.do?menu=sim';
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
		
      	form.matriculaImovel.value = "";
	    form.inscricaoImovel.value = "";
	    
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
		
      	form.loginUsuario.value = "";
	    form.nomeUsuario.value = "";
	
		form.loginUsuario.focus();
	}	
	/* Limpar Unidade Atual */
	function limparUnidadeAtual() {
		var form = document.forms[0];
		
      	form.unidadeAtualId.value = '';
	    form.unidadeAtualDescricao.value = '';
	
		form.unidadeAtualId.focus();
	}
	
	/* Limpar Unidade Anterior */
	function limparUnidadeAnterior() {
		var form = document.forms[0];
		
      	form.unidadeAnteriorId.value = '';
	    form.unidadeAnteriorDescricao.value = '';
	
		form.unidadeAnteriorId.focus();
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
	
	/* Replica Data de Tramitacao */	
	function replicaDataTramitacao() {
		var form = document.forms[0];
		form.periodoTramitacaoFinal.value = form.periodoTramitacaoInicial.value
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
	
	function validaForm(){
		var form = document.forms[0];
		/*if(validateFiltrarRegistroAtendimentoActionForm(form) && validarFormatacaoNumeracaoRAManual(form.numeroRAManual, "Número Manual")){
			submeterFormPadrao(form);
		}*/ 
		
		if(validateFiltrarRegistroAtendimentoActionForm(form)){
			var unidadeAtual = trim(form.unidadeAtualId.value);
    		var unidadeSuperior = trim(form.unidadeSuperiorId.value);
    		if (unidadeAtual != '' && unidadeSuperior != '') {
    			alert('Informe somente Unidade Atual OU Unidade Superior');
    			return false;
    		}
    		
			if (validaQtdeRAReiteradas(form)) {
				//form.submit();
				submitForm(form);
			}
		}
	}
</SCRIPT>


</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">
<div id="formDiv">
<html:form action="/filtrarRegistroAtendimentoAction.do" 
		   name="FiltrarRegistroAtendimentoActionForm" 
		   type="gcom.gui.atendimentopublico.registroatendimento.FiltrarRegistroAtendimentoActionForm"
		   method="post">

<html:hidden property="selectedTipoSolicitacaoSize" />
<html:hidden property="situacaoId"/>

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

	<td width="625" valign="top" class="centercoltext">

        <table height="100%">
        <tr>
          <td></td>
        </tr>
      	</table>

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Filtrar Registro de Atendimento</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
			<tr> 
                <td colspan="5" bordercolor="#000000" class="style1">Para 
                  filtrar o registro de atendimento, informe os dados abaixo:</td>
                  
				<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}atendimentoRegistroFiltrar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
				</logic:present>
				<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
				</logic:notPresent>
              </tr>
              <tr> 
                <td height="24" colspan="7" bordercolor="#000000" class="style1"> 
                  <hr></td>
              </tr>
              <tr> 
                <td><strong>N&uacute;mero do RA:</strong></td>
                <td colspan="6">
                	<span class="style2">
                		<strong>
                  			<html:text property="numeroRA" 
                  					size="9" 
                  					maxlength="9" 
                  					onkeyup="limparPeriodos();"
                  					onkeypress="return isCampoNumerico(event);"/>
                  		</strong>
                  	</span>
                </td>
              </tr>
              <tr> 
                <td><strong>N&uacute;mero do Protocolo:</strong></td>
                <td colspan="6">
                	<span class="style2">
                	<strong>
                  		<html:text property="numeroProtocoloAtendimento" 
                  				size="15" 
                  				maxlength="14" 
                  				onkeyup="limparPeriodos();"
                  				onkeypress="return isCampoNumerico(event);"/>
                  	</strong>
                  	</span>
                </td>
              </tr>
              <tr>
      			<td><strong>Número Manual:</strong></td>
        		<td colspan="6">
					<html:text property="numeroRAManual" 
							size="12" 
							maxlength="11" 
							onkeyup="limparPeriodoAtendimento();"
							onkeypress="return isCampoNumerico(event);"/>
				</td>
      		  </tr>
      		  <tr> 
                <td><strong>RA Reiteradas:</strong></td>
                <td colspan="6">
                	<span class="style2">
						<html:text property="quantidadeRAReiteradasInicial" 
								size="5" maxlength="3" 
								onkeypress="return isCampoNumerico(event);" />
						<strong> a </strong>
						<html:text property="quantidadeRAReiteradasFinal" 
								size="5" maxlength="3" 
								onkeypress="return isCampoNumerico(event);"/>
					</span>
				</td>
			  </tr>
              <tr> 
                <td><strong><span class="style2">Matr&iacute;cula do Im&oacute;vel</span>:</strong></td>
                <td colspan="6"><span class="style2"><strong>
					<html:text property="matriculaImovel" size="9" maxlength="9" 
							   onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarRegistroAtendimentoAction.do?validaImovel=true', 'matriculaImovel','Matrícula do Imovel');return isCampoNumerico(event);"
							   onkeyup="limparPeriodoAtendimento();"/>
					
					<a href="javascript:chamarPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 275, 480, '',document.forms[0].matriculaImovel);">
						<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" name="imagem" alt="Pesquisar" border="0" title="Pesquisar Imóvel"></a>
						 
					<logic:present name="inscricaoImovelEncontrada" scope="session">
						<html:text property="inscricaoImovel" readonly="true" 
								   style="background-color:#EFEFEF; border:0; color: #000000" size="36" maxlength="36" />
					</logic:present> 

					<logic:notPresent name="inscricaoImovelEncontrada" scope="session">
						<html:text property="inscricaoImovel" readonly="true" 
								   style="background-color:#EFEFEF; border:0; color: #ff0000" size="36" maxlength="36" />
					</logic:notPresent>  						 
						 
					<a href="javascript:limparFormImovel();">
						<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
                  </strong></span></td>
              </tr>
              <tr> 
                <td><strong>Login do usuário:</strong></td>
                <td colspan="6"><span class="style2"><strong>
					<html:text property="loginUsuario" 
						size="9" 
						maxlength="11"
						style="text-transform: none;"
						onkeypress="javascript:pesquisaEnterSemUpperCase(event, 'exibirFiltrarRegistroAtendimentoAction.do', 'loginUsuario');"/>
					
					<a href="javascript:chamarPopup('exibirUsuarioPesquisar.do?mostrarLogin=S', 'usuario', null, null, 370, 600, '',document.forms[0].loginUsuario);">
						<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" name="imagem" alt="Pesquisar" border="0" title="Pesquisar Usuário"></a>
						 
					<logic:present name="usuarioEncontrado" scope="session">
						<html:text property="nomeUsuario" 
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" 
							size="36" 
							maxlength="36" />
							
					</logic:present> 

					<logic:notPresent name="usuarioEncontrado" scope="session">
						<html:text property="nomeUsuario" 
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" 
							size="36" 
							maxlength="36" />
					</logic:notPresent>
					
					<a href="javascript:limparUsuario();">
						<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
					</a>
                  </strong></span></td>
              </tr>
              
              <tr> 
                <td><strong>Situa&ccedil;&atilde;o:</strong></td>
                <td colspan="6">
                <span class="style2">
                  <strong> 
					  <html:radio property="situacao" value="0" onclick="javascript: reload();"/>
	 				  Todos
					  <html:radio property="situacao" value="1" onclick="javascript: reload();"/>
	 				  Pendentes
					  <html:radio property="situacao" value="2" onclick="javascript: reload();"/>
					  Encerrados
					  <br>
					  <html:radio property="situacao" value="3" onclick="javascript: reload();"/>
					  Sem Local de Ocorr&ecirc;ncia
				  </strong>
				</span>
			    </td>
              </tr>
              
              <tr> 
                <td><strong>Apenas RAs com<br>coordenadas sem<br>logradouro identificado:</strong></td>
                <td colspan="4"><span class="style2"><strong> 
                  <label> </label>
                  <html:radio property="indicCoordSemLogr" value="1" onclick="javascript: reload();"/>
 				  Sim
                  <label> 
                  <html:radio property="indicCoordSemLogr" value="2" onclick="javascript: reload();"/>
 				  N&atilde;o
				  </label></strong></span></td>
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
                  <hr></td>
              </tr>
              <tr> 
              <tr> 
                <td><strong>Motivo de Encerramento:</strong></td>
                <td colspan="6"><span class="style2"><strong> 
                
                	<logic:equal name="FiltrarRegistroAtendimentoActionForm" property="situacao" value="1">
					<html:select property="colecaoAtendimentoMotivoEncerramento" style="width: 350px; height:100px;" disabled="true" multiple="true">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present name="colecaoAtendimentoMotivoEncerramento" scope="session">
							<html:options collection="colecaoAtendimentoMotivoEncerramento" labelProperty="descricao" property="id" />
						</logic:present>
					</html:select>                
					</logic:equal>
					
					<logic:notEqual name="FiltrarRegistroAtendimentoActionForm" property="situacao" value="1">
					<html:select property="colecaoAtendimentoMotivoEncerramento" style="width: 350px; height:100px;" multiple="true">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present name="colecaoAtendimentoMotivoEncerramento" scope="session">
							<html:options collection="colecaoAtendimentoMotivoEncerramento" labelProperty="descricao" property="id" />
						</logic:present>
					</html:select>             
					</logic:notEqual>
					
                  </strong></span></td>
              </tr>
              <tr> 
                <td height="24" colspan="7" bordercolor="#000000" class="style1"> 
                  <hr></td>
              </tr>
              <tr> 
                <td><strong>Per&iacute;odo de Atendimento:</strong></td>
                <td colspan="6"><span class="style2"><strong> 
					<logic:equal name="FiltrarRegistroAtendimentoActionForm" property="situacao" value="2">
						<html:text property="periodoAtendimentoInicial" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event)" 
								onkeypress="return isCampoNumerico(event);"
								readonly="true" 
								value=""/>
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/>
						a <html:text property="periodoAtendimentoFinal" 
									size="11" 
									maxlength="10" 
									tabindex="3" 
									onkeyup="mascaraData(this, event)"  
									onkeypress="return isCampoNumerico(event);"
									readonly="true" 
									value=""/>
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/>
					</logic:equal>
					
					<logic:notEqual name="FiltrarRegistroAtendimentoActionForm" property="situacao" value="2">
						<html:text property="periodoAtendimentoInicial" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event);replicaDataAtendimento();"
								onkeypress="return isCampoNumerico(event);"/>
						<a href="javascript:abrirCalendario('FiltrarRegistroAtendimentoActionForm', 'periodoAtendimentoInicial');">
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/></a>
						a <html:text property="periodoAtendimentoFinal" 
									size="11" 
									maxlength="10" 
									tabindex="3" 
									onkeypress="return isCampoNumerico(event);"
									onkeyup="mascaraData(this, event)"/>
						<a href="javascript:abrirCalendario('FiltrarRegistroAtendimentoActionForm', 'periodoAtendimentoFinal');">
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/></a>
					</logic:notEqual>
					</strong>(dd/mm/aaaa)<strong> 
                  </strong></span></td>
              </tr>
              <tr> 
                <td><strong>Per&iacute;odo de Encerramento:</strong></td>
                <td colspan="6"><span class="style2"><strong> 
					<logic:equal name="FiltrarRegistroAtendimentoActionForm" property="situacao" value="1">
						<html:text property="periodoEncerramentoInicial" 
									size="11" 
									maxlength="10" 
									tabindex="3" 
									onkeypress="return isCampoNumerico(event);"
									onkeyup="mascaraData(this, event)"  
									readonly="true" 
									value=""/>
							<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/>
						a <html:text property="periodoEncerramentoFinal" 
									size="11" 
									maxlength="10" 
									tabindex="3" 
									onkeypress="return isCampoNumerico(event);"
									onkeyup="mascaraData(this, event)"  
									readonly="true" 
									value=""/>
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/>
					</logic:equal>
					
					<logic:notEqual name="FiltrarRegistroAtendimentoActionForm" property="situacao" value="1">
						<html:text property="periodoEncerramentoInicial" 
									size="11" 
									maxlength="10" 
									tabindex="3" 
									onkeypress="return isCampoNumerico(event);"
									onkeyup="mascaraData(this, event);replicaDataEncerramento();"/>
							<a href="javascript:abrirCalendario('FiltrarRegistroAtendimentoActionForm', 'periodoEncerramentoInicial');">
							<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/></a>
						a <html:text property="periodoEncerramentoFinal" 
									size="11" 
									maxlength="10" 
									tabindex="3" 
									onkeypress="return isCampoNumerico(event);"
									onkeyup="mascaraData(this, event)"/>
						<a href="javascript:abrirCalendario('FiltrarRegistroAtendimentoActionForm', 'periodoEncerramentoFinal');">
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/></a>
					</logic:notEqual>
					</strong>(dd/mm/aaaa)<strong> 
                  </strong></span></td>
              </tr>
               <tr> 
                <td><strong>Per&iacute;odo de Tramitação:</strong></td>
                <td colspan="6"><span class="style2"><strong> 
						<html:text property="periodoTramitacaoInicial" 
									size="11" 
									maxlength="10" 
									tabindex="3" 
									onkeypress="return isCampoNumerico(event);"
									onkeyup="mascaraData(this, event);replicaDataTramitacao();"/>
							<a href="javascript:abrirCalendario('FiltrarRegistroAtendimentoActionForm', 'periodoTramitacaoInicial');">
							<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="5"/></a>
						a <html:text property="periodoTramitacaoFinal" 
									size="11" 
									maxlength="10" 
									tabindex="3" 
									onkeypress="return isCampoNumerico(event);"
									onkeyup="mascaraData(this, event)"/>
						<a href="javascript:abrirCalendario('FiltrarRegistroAtendimentoActionForm', 'periodoTramitacaoFinal');">
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="5"/></a>
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
					<html:text property="unidadeAtendimentoId" 
							   size="4" 
							   maxlength="4"
							   onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarRegistroAtendimentoAction.do?validaUnidadeAtendimento=true', 'unidadeAtendimentoId','Unidade Atendimento');return isCampoNumerico(event);"/>
					
					<a href="javascript:setUnidade(1); chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do?limparForm=sim', 'unidadeOrganizacional', null, null, 275, 480, '',document.forms[0].unidadeAtendimentoId);">
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" name="imagem" alt="Pesquisar" border="0" title="Pesquisar Unidade de Atendimento"></a>
						 
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
                  </strong></span></td>
              </tr>
              <tr> 
                <td><strong> Unidade Atual:</strong></td>
                <td colspan="6" align="right"><div align="left"><span class="style2"><strong> 
					<html:text property="unidadeAtualId" 
					           size="4" 
					           maxlength="4"
							   onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarRegistroAtendimentoAction.do?validaUnidadeAtual=true', 'unidadeAtualId','Unidade Atual');return isCampoNumerico(event);"/>
					
						<a href="javascript:setUnidade(2); chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do?limparForm=sim', 'unidadeOrganizacional', null, null, 275, 480, '',document.forms[0].unidadeAtualId);">
						<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" name="imagem" alt="Pesquisar" border="0" title="Pesquisar Unidade Atual"></a>

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
                    </strong></span></div></td>
              </tr>
             <tr> 
                <td><strong> Unidade Superior:</strong></td>
                <td colspan="6" align="right"><div align="left"><span class="style2"><strong> 
					<html:text property="unidadeSuperiorId" 
							   size="4" 
							   maxlength="4"
							   onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarRegistroAtendimentoAction.do?validaUnidadeSuperior=true', 'unidadeSuperiorId','Unidade Superior');return isCampoNumerico(event);"/>
					
					<a href="javascript:setUnidade(3); chamarPopup('exibirPesquisarUnidadeSuperiorAction.do', 'unidadeOrganizacional', null, null, 275, 480, '',document.forms[0].unidadeSuperiorId);">
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" name="imagem" alt="Pesquisar" border="0" title="Pesquisar Unidade Superior"></a>

					<logic:present name="unidadeSuperiorEncontrada" scope="session">
						<html:text property="unidadeSuperiorDescricao" readonly="true"
								   style="background-color:#EFEFEF; border:0; color: #000000" size="40" maxlength="40" />
					</logic:present> 

					<logic:notPresent name="unidadeSuperiorEncontrada" scope="session">
						<html:text property="unidadeSuperiorDescricao" readonly="true"
								   style="background-color:#EFEFEF; border:0; color: #ff0000" size="40" maxlength="40" />
					</logic:notPresent>

					<a href="javascript:limparUnidadeSuperior();">
						<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
                    </strong></span></div></td>
              </tr>
               <tr> 
                <td><strong> Unidade Anterior:</strong></td>
                <td colspan="6" align="right"><div align="left"><span class="style2"><strong> 
					<html:text property="unidadeAnteriorId" 
					           size="4" 
					           maxlength="4"
							   onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarRegistroAtendimentoAction.do?validaUnidadeAnterior=true', 'unidadeAnteriorId','Unidade Anterior');return isCampoNumerico(event);"/>
					
						<a href="javascript:setUnidade(4); chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do?limparForm=sim', 'unidadeOrganizacional', null, null, 275, 480, '',document.forms[0].unidadeAnteriorId);">
						<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" name="imagem" alt="Pesquisar" border="0" title="Pesquisar Unidade Anterior"></a>

					<logic:present name="unidadeAnteriorEncontrada" scope="session">
						<html:text property="unidadeAnteriorDescricao" readonly="true"
								   style="background-color:#EFEFEF; border:0; color: #000000" size="40" maxlength="40" />
					</logic:present> 

					<logic:notPresent name="unidadeAnteriorEncontrada" scope="session">
						<html:text property="unidadeAnteriorDescricao" readonly="true"
								   style="background-color:#EFEFEF; border:0; color: #ff0000" size="40" maxlength="40" />
					</logic:notPresent>

					<a href="javascript:limparUnidadeAnterior();">
						<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
                    </strong></span></div></td>
              </tr>
              <tr> 
                <td height="24" colspan="7" bordercolor="#000000" class="style1"> 
                  <hr></td>
              </tr>
              <tr> 
                <td><strong>Munic&iacute;pio:</strong></td>
                <td colspan="6"><span class="style2"><strong> 
					<html:text property="municipioId" 
							   size="4" 
							   maxlength="4"
							   onkeypress="javascript:limparBairroDigitacao();validaEnterComMensagem(event, 'exibirFiltrarRegistroAtendimentoAction.do?validaMunicipio=true', 'municipioId','Município');return isCampoNumerico(event);"/>
					
					<a href="javascript:chamarPopup('exibirPesquisarMunicipioAction.do', 'municipio', null, null, 275, 480, '',document.forms[0].municipioId);">
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" name="imagem" alt="Pesquisar" border="0" title="Pesquisar Município"></a>

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
					<html:text property="bairroCodigo" 
							   size="4" 
							   maxlength="4"
							   onkeypress="javascript:limparBairroDigitacao();validaEnterDependencia(event, 'exibirFiltrarRegistroAtendimentoAction.do?validaBairro=true', this, document.forms[0].municipioId.value, 'Município');return isCampoNumerico(event);"/>
					
					<a href="javascript:abrirPopupDependencia('exibirPesquisarBairroAction.do?idMunicipio='+document.forms[0].municipioId.value+'&indicadorUsoTodos=1', document.forms[0].municipioId.value, 'Município', 275, 480);">
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" name="imagem" alt="Pesquisar" border="0" title="Pesquisar Bairro"></a>

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
					<html:text property="logradouroId" 
							   size="9" 
							   maxlength="9"
							   onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarRegistroAtendimentoAction.do?validaLogradouro=true', 'logradouroId','Logradouro');return isCampoNumerico(event);"/>
					
					<a href="javascript:abrirPopup('exibirPesquisarLogradouroAction.do?codigoMunicipio='+document.forms[0].municipioId.value+'&codigoBairro='+document.forms[0].bairroCodigo.value+'&indicadorUsoTodos=1&primeriaVez=1', 275, 480);">
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" name="imagem" alt="Pesquisar" border="0" title="Pesquisar Logradouro"></a>

					<logic:present name="logradouroEncontrada" scope="session">
						<html:text property="logradouroDescricao" readonly="true"
								   style="background-color:#EFEFEF; border:0; color: #000000" size="36" maxlength="36" />
					</logic:present> 

					<logic:notPresent name="logradouroEncontrada" scope="session">
						<html:text property="logradouroDescricao" readonly="true"
								   style="background-color:#EFEFEF; border:0; color: #ff0000" size="36" maxlength="36" />
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
              <tr> 
                <td>
                	<input type="button" name="adicionar2" 
                			class="bottonRightCol" 
                			value="Limpar" 
                			onclick="javascript:limparForm();">
                	<input type="button" name="adicionar" 
			                class="bottonRightCol" 
			                value="Cancelar" 
			                onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
                </td>
                
                <td colspan="6"> 
	                <div align="right"> 
	                    <input type="button" name="Submit" 
	                    		class="bottonRightCol" 
	                    		value="Filtrar" 
	                    		onclick="validaForm();">
	                </div>
                </td>
              </tr>      
      </table>
      <p>&nbsp;</p>
	</td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</div>

</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

</html:html>