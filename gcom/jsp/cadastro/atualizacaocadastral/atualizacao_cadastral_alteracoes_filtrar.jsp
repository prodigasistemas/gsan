<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<%@ page import="gcom.cadastro.SituacaoAtualizacaoCadastral"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" dynamicJavascript="true" formName="FiltrarAlteracaoAtualizacaoCadastralActionForm" />

<script language="JavaScript">

    function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
        var form = document.forms[0];
        if (tipoConsulta == 'localidadeOrigem') {
            form.idLocalidadeInicial.value = codigoRegistro;
            form.nomeLocalidadeInicial.value = descricaoRegistro;
            document.getElementsByName('nomeLocalidadeInicial')[0].style.color = '#000000';
        }
        else if (tipoConsulta == 'localidadeDestino') {
            form.idLocalidadeFinal.value = codigoRegistro;
            form.nomeLocalidadeFinal.value = descricaoRegistro;
            document.getElementsByName('nomeLocalidadeFinal')[0].style.color = '#000000';
        }
    }
    
    function recuperarDadosQuatroParametros(codigoRegistro, descricaoRegistro, codigoAuxiliar, tipoConsulta) {
        var form = document.forms[0];
        if (tipoConsulta == 'setorComercialOrigem') {
            form.cdSetorComercialInicial.value = codigoAuxiliar;
            form.nomeSetorComercialInicial.value = descricaoRegistro;
            document.getElementsByName('nomeSetorComercialInicial')[0].style.color = '#000000';
            
        }
        else if (tipoConsulta == 'setorComercialDestino') {
            form.cdSetorComercialFinal.value = codigoAuxiliar;
            form.nomeSetorComercialFinal.value = descricaoRegistro;
            document.getElementsByName('nomeSetorComercialFinal')[0].style.color = '#000000';
        }
    }

	function validarForm(form) {
		var periodoInicial = trim(form.periodoInicial.value);
		var periodoFinal = trim(form.periodoFinal.value);
		
	    if (form.idEmpresa.value == '-1') {
	    	alert('Informe a Empresa.');
	    } else if (periodoInicial == null || periodoInicial == '') {
    		alert('Informe o Período Inicial de Pré Aprovação.');
    		return false;
    	} else  if (periodoFinal == null || periodoFinal == '') {
    		alert('Informe o Período Final de Pré Aprovação.');
       		return false;
    	} else {
        	if (validateFiltrarAlteracaoAtualizacaoCadastralActionForm(form)) {
		    	form.action = "/gsan/filtrarAlteracaoAtualizacaoCadastralAction.do";
				submeterFormPadrao(form);	
            }
	    }
	}
	
	function recarregar() {
		 var form = document.forms[0];
		 form.action = 'exibirFiltrarAlteracaoAtualizacaoCadastralAction.do';
	  	 form.submit();
	}
	
   function limparLeiturista() {
		var form = document.forms[0];
	    form.idArquivo.value = '';
	    form.descricaoArquivo.value = '';
   }

   function replicarLocalidade() {
		var form = document.forms[0];
		form.idLocalidadeFinal.value = form.idLocalidadeInicial.value;
		form.nomeLocalidadeFinal.value = form.nomeLocalidadeInicial.value;
	}

   function replicarSetorComercial() {
		var form = document.forms[0];
		form.cdSetorComercialFinal.value = form.cdSetorComercialInicial.value;
		form.nomeSetorComercialFinal.value = form.nomeSetorComercialInicial.value;
	}

   function replicarRota() {
		var form = document.forms[0];
		form.cdRotaFinal.value = form.cdRotaInicial.value;
	}

   function replicarPeriodo() {
		var form = document.forms[0];
		form.periodoFinal.value = form.periodoInicial.value;
	}
</script>


</head>


<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">

<div id="formDiv">
<html:form action="/filtrarAlteracaoAtualizacaoCadastralAction"
	name="FiltrarAlteracaoAtualizacaoCadastralActionForm"
	type="gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionForm"
	onsubmit="return validateFiltrarAlteracaoAtualizacaoCadastralActionForm(this);">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<input type="hidden" id="existeColecaoImovel" value="${requestScope.existeColecaoImovel}"/>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="140" valign="top" class="leftcoltext">
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
          <td class="parabg">Filtrar Atualizações Cadastrais</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      <tr>
      	<td colspan="3">Para filtrar a(s) atualização(ões) cadastral(is), informe os dados abaixo:</td>
      </tr>
	  <tr>
		<td width="170"><strong>Empresa:<font color="#FF0000">*</font></strong></td>
		<td colspan="2" align="left">
			<html:select property="idEmpresa" tabindex="1" onchange="javascript:recarregar()" >
				<html:option value="-1">&nbsp;</html:option>
				<html:options collection="colecaoEmpresa" labelProperty="descricao" property="id" />
			</html:select>
		</td>
	  </tr>
	  <tr>
		<td width="170"><strong>Agente Cadastral:</strong></td>
		<td colspan="2" align="left">
			<html:select property="idLeiturista" tabindex="2">
				<html:option value="-1">&nbsp;</html:option>
				<html:options collection="colecaoLeiturista" labelProperty="descricao" property="id" />
			</html:select>
		</td>
	 </tr>
	 
	 <tr>
		<td><strong>Período de Pré Aprovação:<font color="#FF0000">*</font></strong></td>

		<td colspan="6">
		
			<span class="style2">
			
				<strong>
					<html:text property="periodoInicial" size="11" maxlength="10" tabindex="3" onkeyup="mascaraData(this, event);replicarPeriodo();" />
					<a href="javascript:abrirCalendarioReplicando('FiltrarAlteracaoAtualizacaoCadastralActionForm', 'periodoInicial','periodoFinal');">
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" />
					</a> a 
					<html:text property="periodoFinal" size="11" maxlength="10" tabindex="4" onkeyup="mascaraData(this, event)" /> 
					<a href="javascript:abrirCalendario('FiltrarAlteracaoAtualizacaoCadastralActionForm', 'periodoFinal');">
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" />
					</a> 
				</strong>(dd/mm/aaaa)
				<strong></strong> 
			</span>
		</td>
	</tr>
	
    <tr>
		<td colspan="2"><hr></td>
    </tr>
	
	<tr>
		<td><strong>Lote de Fiscalização:</strong></td>
		<td>
			<html:text property="lote" maxlength="5" size="5" onkeypress="somente_numero(this);" onkeyup="javascript:somente_numero(this);" tabindex="5" />
		</td>
	</tr>

    <tr>
      	<td colspan="2"><hr></td>
    </tr>
    
    <tr>
      	<td width="170"><strong>Matrícula:</strong></td>
      	<td>
			<html:text property="matricula" maxlength="9"  size="9" tabindex="5"
				   	   onkeypress="somente_numero(this);form.target=''; validaEnter(event, 'exibirFiltrarAlteracaoAtualizacaoCadastralAction.do?filtroMatricula=SIM', 'matricula');"
				   	   onkeyup="javascript:somente_numero(this);" />
         
	        <a href="javascript:limparCampos('matricula', 'inscricao'); abrirPopup('exibirPesquisarImovelAction.do?tipo=origem', 400, 800);" > 
	        	<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"/> 
	        </a>
         
        	<html:text property="inscricao" size="31" readonly="true" style="background-color: #EFEFEF; border: 0; color: ${requestScope.corInscricao}" />
          
	        <a href="javascript:limparCampos('matricula', 'inscricao');">
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
      	<td width="170"><strong>Localidade:</strong></td>
      	<td>
			<html:text tabindex="5" maxlength="3" property="idLocalidadeInicial" size="5"
				   	   onkeypress="somente_numero(this);form.target=''; validaEnter(event, 'exibirFiltrarAlteracaoAtualizacaoCadastralAction.do?filterClass=FiltroLocalidade&fieldLocalidade=LocalidadeInicial', 'idLocalidadeInicial');"
				   	   onkeyup="javascript:somente_numero(this);replicarLocalidade();" 
				   	   onblur="javascript:replicarLocalidade();"/>
         
	        <a href="javascript:limparCampos('idLocalidadeInicial', 'nomeLocalidadeInicial', 'cdSetorComercialInicial', 'nomeSetorComercialInicial', 'idLocalidadeFinal', 'nomeLocalidadeFinal', 'cdSetorComercialFinal', 'nomeSetorComercialFinal', 'cdRotaInicial', 'cdRotaFinal'); abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=origem', 400, 800);" > 
	        	<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"/> 
	        </a>
         
        	<html:text property="nomeLocalidadeInicial" size="35" readonly="true" style="background-color: #EFEFEF; border: 0; color: ${requestScope.corLocalidadeInicial}" />
          
	        <a href="javascript:limparCampos('idLocalidadeInicial', 'nomeLocalidadeInicial', 'cdSetorComercialInicial', 'nomeSetorComercialInicial', 'idLocalidadeFinal', 'nomeLocalidadeFinal', 'cdSetorComercialFinal', 'nomeSetorComercialFinal', 'cdRotaInicial', 'cdRotaFinal');">
	            <img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
	        </a>
      	</td>          
    </tr>
    
    <tr>
      <td width="170"><strong>Setor Comercial :</strong></td>
      <td><html:text maxlength="3" property="cdSetorComercialInicial" size="5" onkeyup="javascript:somente_numero(this);replicarLocalidade();"
           onkeypress="validaEnterDependencia(event, 
           'exibirFiltrarAlteracaoAtualizacaoCadastralAction.do?filterClass=FiltroSetorComercial&fieldLocalidade=LocalidadeInicial&fieldSetorComercial=SetorComercialInicial', 
           this, document.forms[0].idLocalidadeInicial.value, 'Localidade Final.');" onblur="javascript:replicarSetorComercial();" tabindex="6" />
           <a href="javascript:abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidadeInicial.value+'&tipo=setorComercialOrigem',document.forms[0].idLocalidadeInicial.value,'Localidade Inicial', 400, 800);">
           <img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar" />
           </a>
          <html:text property="nomeSetorComercialInicial" size="35" readonly="true"
            style="background-color: #EFEFEF; border: 0; color: ${requestScope.corSetorComercialInicial}" />
          
          <a href="javascript:limparCampos('cdSetorComercialInicial', 'nomeSetorComercialInicial', 'cdSetorComercialFinal', 'nomeSetorComercialFinal', 'cdRotaInicial', 'cdRotaFinal');">
              <img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
          </a>
      </td>
    </tr>
    
    <tr>
      <td width="170"><strong>Rota :</strong></td>
      <td><html:text maxlength="3" property="cdRotaInicial" size="5" tabindex="7"
      			onkeyup="javascript:somente_numero(this);replicarRota();" onblur="javascript:replicarRota();"/>
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
      <td width="170"><strong>Localidade:</strong>
      </td>
      <td><html:text tabindex="8" maxlength="3" property="idLocalidadeFinal" size="5"
        onkeypress="somente_numero(this);form.target='';
        validaEnter(event,
        'exibirFiltrarAlteracaoAtualizacaoCadastralAction.do?filterClass=FiltroLocalidade&fieldLocalidade=LocalidadeFinal', 'idLocalidadeFinal');"
        onkeyup="javascript:somente_numero(this);" />
         
        <a href="javascript:limparCampos('idLocalidadeFinal', 'nomeLocalidadeFinal', 'cdSetorComercialFinal', 'nomeSetorComercialFinal'); 
           abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=destino', 400, 800);"> 
          <img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"/> 
        </a>
         
        <html:text property="nomeLocalidadeFinal" size="35" readonly="true"
            style="background-color: #EFEFEF; border: 0; color: ${requestScope.corLocalidadeFinal}" />
          
        <a href="javascript:limparCampos('idLocalidadeFinal', 'nomeLocalidadeFinal', 'cdSetorComercialFinal', 'nomeSetorComercialFinal', 'cdRotaFinal');">
            <img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
        </a>
      </td>          
    </tr>
    
    <tr>
      <td width="170"><strong>Setor Comercial :</strong></td>
      <td><html:text maxlength="3" property="cdSetorComercialFinal" size="5" onkeyup="javascript:somente_numero(this);"
           onkeypress="validaEnterDependencia(event, 
           'exibirFiltrarAlteracaoAtualizacaoCadastralAction.do?filterClass=FiltroSetorComercial&fieldLocalidade=LocalidadeFinal&fieldSetorComercial=SetorComercialFinal', 
           this, document.forms[0].idLocalidadeFinal.value, 'Localidade Final.');"
           tabindex="9" />
           <a href="javascript:abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidadeFinal.value+'&tipo=setorComercialDestino',document.forms[0].idLocalidadeFinal.value,'Localidade Final', 400, 800);">
           <img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar" />
           </a>
          <html:text property="nomeSetorComercialFinal" size="35" readonly="true"
            style="background-color: #EFEFEF; border: 0; color: ${requestScope.corSetorComercialFinal}" />
          
          <a href="javascript:limparCampos('cdSetorComercialFinal', 'nomeSetorComercialFinal', 'cdRotaFinal');">
              <img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
          </a>
      </td>
    </tr>
    
    <tr>
      <td width="170"><strong>Rota :</strong></td>
      <td><html:text maxlength="3" property="cdRotaFinal" size="5" onkeyup="javascript:somente_numero(this);" tabindex="10" />
      </td>
    </tr>
    
    <tr>
      <td colspan="2"><hr></td>
    </tr>
    
     <tr>
		<td width="170"><strong>Situação dos Imóveis:<font color="#FF0000">*</font></strong></td>
		<td>
			<html:select property="situacaoImoveis">
				<html:option value="-1">PENDENTES</html:option>
				<html:option value="3">TRANSMITIDOS</html:option>
				<html:option value="8">EM REVISÃO</html:option>
				<html:option value="9">REVISADOS</html:option>
				<html:option value="11">EM REVISITA</html:option>
				<html:option value="7">PRÉ APROVADOS</html:option>
				<html:option value="5">EM FISCALIZAÇÃO</html:option>
				<html:option value="10">FISCALIZADOS</html:option>
				<html:option value="4">APROVADOS</html:option>
				<html:option value="-2">PARA APROVAÇÃO EM LOTE</html:option>
				<html:option value="-3">TODOS</html:option>
			</html:select>
      </td>
    </tr>
    
    <tr>
      <td colspan="2"><hr></td>
    </tr>

	<tr>
      <td width="170"><strong>Quantidade de Visitas:</strong></td>
      <td>
		<strong> 
			<html:radio property="quantidadeVisitas" value="1" /> 1 
			<html:radio property="quantidadeVisitas" value="2" /> 2
			<html:radio property="quantidadeVisitas" value="3" /> 3
			<html:radio property="quantidadeVisitas" value="-1" /> Todos
		</strong>
      </td>
    </tr>
    <tr>
      <td colspan="2"><hr></td>
    </tr>

	<tr>
      <td width="170"><strong>Ocorr&ecirc;ncia de Cadastro:</strong></td>
      <td>
        <strong> 
          <html:radio property="ocorrenciaCadastro" value="1"  onclick="javascript: recarregar();" /> N&atilde;o 
          <html:radio property="ocorrenciaCadastro" value="2"  onclick="javascript: recarregar();" /> Sim  
          <html:radio property="ocorrenciaCadastro" value="-1" onclick="javascript: recarregar()" /> Todos
        </strong>
      </td>
    </tr>
    
    <logic:present name="colecaoCadastroOcorrencia" >
	    <tr>
	      <td width="170"></td>
	      <td>
	      	<html:select property="ocorrenciaCadastroSelecionada">
				<html:option value="-1">TODAS</html:option>
				<html:options collection="colecaoCadastroOcorrencia" labelProperty="descricao" property="id" />
			</html:select>
	      </td>
	    </tr>
    </logic:present>
    
    <tr>
      <td colspan="2"><hr></td>
    </tr>

    <tr>
      <td width="170"><strong>Com CPF Cadastrado:</strong></td>
      <td>
        <strong> 
          <html:radio property="cpfCnpjCadastrado" value="1" /> Sim  
          <html:radio property="cpfCnpjCadastrado" value="2" /> N&atilde;o 
          <html:radio property="cpfCnpjCadastrado" value="-1" /> Todos
        </strong>
      </td>
    </tr>
    
    <tr>
      <td width="170"><strong>Com CPF Transmitido:</strong></td>
      <td>
        <strong> 
          <html:radio property="cpfCnpjTransmitido" value="1" /> Sim  
          <html:radio property="cpfCnpjTransmitido" value="2" /> N&atilde;o 
          <html:radio property="cpfCnpjTransmitido" value="-1" /> Todos
        </strong>
      </td>
    </tr>

	<tr>
      <td colspan="2"><hr></td>
    </tr>

    <tr>
      <td width="170"><strong>Altera&ccedil;&atilde;o de Hidr&ocirc;metro:</strong></td>
      <td>
        <strong> 
          <html:radio property="alteracaoHidrometro" value="1" /> Sim  
          <html:radio property="alteracaoHidrometro" value="2" /> N&atilde;o 
          <html:radio property="alteracaoHidrometro" value="-1" /> Todos
        </strong>
      </td>
    </tr>

    <tr>
      <td width="170"><strong>Altera&ccedil;&atilde;o de Situa&ccedil;&atilde;o de &Aacute;gua:</strong></td>
      <td>
        <strong> 
          <html:radio property="alteracaoSituacaoAgua" value="1" /> Sim  
          <html:radio property="alteracaoSituacaoAgua" value="2" /> N&atilde;o 
          <html:radio property="alteracaoSituacaoAgua" value="-1" /> Todos
        </strong>
      </td>
    </tr>

    <tr>
      <td width="170"><strong>Altera&ccedil;&atilde;o de Situa&ccedil;&atilde;o de Esgoto:</strong></td>
      <td>
        <strong> 
          <html:radio property="alteracaoSituacaoEsgoto" value="1" /> Sim  
          <html:radio property="alteracaoSituacaoEsgoto" value="2" /> N&atilde;o 
          <html:radio property="alteracaoSituacaoEsgoto" value="-1" /> Todos
        </strong>
      </td>
    </tr>

    <tr>
      <td width="170"><strong>Altera&ccedil&atilde;o de Categoria, Subcategoria e Qtd. de Economias:</strong></td>
      <td>
        <strong> 
          <html:radio property="alteracaoCategoria" value="1" /> Sim  
          <html:radio property="alteracaoCategoria" value="2" /> N&atilde;o 
          <html:radio property="alteracaoCategoria" value="-1" /> Todos
        </strong>
      </td>
    </tr>

    <tr>
		<td colspan="3">&nbsp;</td>
	 </tr>
	 <tr>
	   <td colspan="3" align="right">
	     <table border="0" width="100%">
		   <tr>
		   <td>
				<input name="Submit22"
					class="bottonRightCol" 
					value="Limpar" 
					type="button"
					onclick="window.location.href='/gsan/exibirFiltrarAlteracaoAtualizacaoCadastralAction.do?menu=sim';"> 			
			</td>
					
			<td align="right" width="50%">					
			 <input name="Button322222" type="button"
				class="bottonRightCol" value="Filtrar"
				onClick="javascript:validarForm(document.forms[0]);" />
			</td>
		  </tr>
		</table>
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
<%@ include file="/jsp/util/telaespera.jsp"%>

</body>

</html:html>
