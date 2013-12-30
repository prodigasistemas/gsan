<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="FiltrarAlteracaoAtualizacaoCadastralActionForm" />

<script language="JavaScript">
    function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
        var form = document.forms[0];
        if (tipoConsulta == 'localidadeOrigem') {
            form.idLocalidadeInicial.value = codigoRegistro;
            form.nomeLocalidadeInicial.value = descricaoRegistro;
            document.getElementsByName('nomeLocalidadeInicial')[0].style.color = '#000000';
         }
    }
    
    function recuperarDadosQuatroParametros(codigoRegistro, descricaoRegistro, codigoAuxiliar, tipoConsulta) {
        var form = document.forms[0];
        if (tipoConsulta == 'setorComercialOrigem') {
            form.cdSetorComercialInicial.value = codigoAuxiliar;
            form.nomeSetorComercialInicial.value = descricaoRegistro;
            document.getElementsByName('nomeSetorComercialInicial')[0].style.color = '#000000';
            
         }
    }

    //So chama a função abrirCalendario caso o campo esteja habilitado
	function chamarCalendario(fieldNameOrigem,objetoRelacionado,fieldNameDestino){
		abrirCalendarioReplicando('FiltrarAlteracaoAtualizacaoCadastralActionForm', fieldNameOrigem,fieldNameDestino);

	}
	
	function validarForm(form){
	    if(form.idEmpresa.value == '-1'){
	    	alert('Informe a Empresa');
	    }else{
	    	form.action = "/gsan/filtrarAlteracaoAtualizacaoCadastralAction.do";
			submeterFormPadrao(form);	
	    }
	}
	
	function listarLeiturista(){
		 var form = document.forms[0];
		 form.action = 'exibirFiltrarAlteracaoAtualizacaoCadastralAction.do';
	  	 form.submit();
	
	}
	
   function limparLeiturista(){
		var form = document.forms[0];
	    form.idArquivo.value = '';
	    form.descricaoArquivo.value = '';
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
	  <tr>
		<td width="150"><strong>Empresa:<font color="#FF0000">*</font></strong></td>
		<td colspan="2" align="left">
			<html:select property="idEmpresa" tabindex="3" onchange="javascript:listarLeiturista()">
				<html:option value="-1">&nbsp;</html:option>
				<html:options collection="colecaoEmpresa"
					labelProperty="descricao" property="id" />
			</html:select>
		</td>
	  </tr>
	  <tr>
		<td height="10" width="145"><strong>Agente Comercial:</strong></td>
		<td colspan="2" align="left"><html:select property="idLeiturista"
			tabindex="4">
			<html:option value="-1">&nbsp;</html:option>
			<html:options collection="colecaoLeiturista"
				labelProperty="descricao" property="id" />
		</html:select></td>
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
      <td><strong>Localidade:</strong>
      </td>
      <td><html:text tabindex="5" maxlength="3" property="idLocalidadeInicial" size="5"
        onkeypress="somente_numero(this);form.target='';
        javascript:limparCampos('cdSetorComercialInicial', 'nomeSetorComercialInicial'); 
        validaEnter(event,
        'exibirFiltrarAlteracaoAtualizacaoCadastralAction.do?filterClass=FiltroLocalidade&fieldLocalidade=LocalidadeInicial', 'idLocalidadeInicial');"
        onkeyup="javascript:somente_numero(this);" />
         
        <a href="javascript:limparCampos('idLocalidadeInicial', 'nomeLocalidadeInicial', 'cdSetorComercialInicial', 'nomeSetorComercialInicial'); 
           abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=origem', 400, 800);"
        id="btPesqLocalidadeInicial"> 
          <img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"/> 
        </a>
         
        <html:text property="nomeLocalidadeInicial" size="35" readonly="true"
            style="background-color: #EFEFEF; border: 0; color: ${requestScope.corLocalidadeInicial}" />
          
        <a href="javascript:limparCampos('idLocalidadeInicial', 'nomeLocalidadeInicial');">
            <img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
        </a>
      </td>          
    </tr>
    
    <tr>
      <td><strong>Setor Comercial :</strong></td>
      <td><html:text maxlength="3" property="cdSetorComercialInicial" size="5" onkeyup="javascript:somente_numero(this);"
           onkeypress="validaEnterDependencia(event, 
           'exibirFiltrarAlteracaoAtualizacaoCadastralAction.do?filterClass=FiltroSetorComercial&fieldLocalidade=LocalidadeInicial&fieldSetorComercial=SetorComercialInicial', 
           this, document.forms[0].idLocalidadeInicial.value, 'Localidade Final.');"
           tabindex="6" />
           <a href="javascript:abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidadeInicial.value+'&tipo=setorComercialOrigem',document.forms[0].idLocalidadeInicial.value,'Localidade Inicial', 400, 800);">
           <img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar" />
           </a>
          <html:text property="nomeSetorComercialInicial" size="35" readonly="true"
            style="background-color: #EFEFEF; border: 0; color: ${requestScope.corSetorComercialInicial}" />
          
          <a href="javascript:limparCampos('cdSetorComercialInicial', 'nomeSetorComercialInicial');">
              <img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
          </a>
      </td>
    </tr>
    
    <tr>
      <td><strong>Exibir Campos:</strong></td>
      <td><strong> <html:radio property="exibirCampos" value="1" /> <strong>Pendentes de Alteração <html:radio
              property="exibirCampos" value="2" /> Alterados/Resolvidos</strong> <html:radio property="exibirCampos" value="3" /> Todos</strong>
      </td>
    </tr>

    <tr>
      <td width="120"><strong>Colunas dos imóveis e clientes:</strong>
      </td>
      <td colspan="2">
        <table width="100%" border=0 cellpadding=0 cellspacing=0 align="left">
          <tr>
            <td width="175">

              <div align="left">
                <strong>Disponíveis</strong>
              </div> <html:select property="colunaImoveis" size="6" multiple="true" style="width:190px">

                <html:options collection="colecaoColunaImoveis" labelProperty="descricaoColuna" property="id" />
              </html:select>
            </td>

            <td width="5" valign="center"><br>
              <table width="50" align="center">
                <tr>
                  <td align="center"><input type="button" class="bottonRightCol"
                    onclick="javascript:MoverTodosDadosSelectMenu1PARAMenu2('FiltrarAlteracaoAtualizacaoCadastralActionForm', 'colunaImoveis', 'colunaImoveisSelecionados'); enviarSelectMultiplo('FiltrarAlteracaoAtualizacaoCadastralActionForm','colunaImoveisSelecionados');"
                    value=" &gt;&gt; ">
                  </td>
                </tr>

                <tr>
                  <td align="center"><input type="button" class="brottonRightCol"
                    onclick="javascript:MoverDadosSelectMenu1PARAMenu2('FiltrarAlteracaoAtualizacaoCadastralActionForm', 'colunaImoveis', 'colunaImoveisSelecionados'); enviarSelectMultiplo('FiltrarAlteracaoAtualizacaoCadastralActionForm','colunaImoveisSelecionados');"
                    value=" &nbsp;&gt;  ">
                  </td>
                </tr>

                <tr>
                  <td align="center"><input type="button" class="bottonRightCol"
                    onclick="javascript:MoverDadosSelectMenu2PARAMenu1('FiltrarAlteracaoAtualizacaoCadastralActionForm', 'colunaImoveis', 'colunaImoveisSelecionados'); enviarSelectMultiplo('FiltrarAlteracaoAtualizacaoCadastralActionForm','colunaImoveisSelecionados');"
                    value=" &nbsp;&lt;  ">
                  </td>
                </tr>

                <tr>
                  <td align="center"><input type="button" class="bottonRightCol"
                    onclick="javascript:MoverTodosDadosSelectMenu2PARAMenu1('FiltrarAlteracaoAtualizacaoCadastralActionForm', 'colunaImoveis', 'colunaImoveisSelecionados'); enviarSelectMultiplo('FiltrarAlteracaoAtualizacaoCadastralActionForm','colunaImoveisSelecionados');"
                    value=" &lt;&lt; ">
                  </td>
                </tr>
              </table></td>

            <td>
              <div align="left">
                <strong>Selecionados</strong>
              </div> <html:select property="colunaImoveisSelecionados" size="6" multiple="true" style="width:190px">

                <logic:present name="existeColecaoColunaImoveisSelecionados">
                  <html:options collection="colecaoColunaImoveisSelecionados" labelProperty="descricaoColuna" property="id" />
                </logic:present>


              </html:select>
            </td>
          </tr>
        </table></td>
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
