<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AtualizarLocalidadeActionForm"/>

<SCRIPT LANGUAGE="JavaScript">
<!--

function validarForm(formulario){

	
	if (validateAtualizarLocalidadeActionForm(formulario)){
	
		var objLocalidadeID = returnObject(formulario, "localidadeID");
		var objLocalidadeNome = returnObject(formulario, "localidadeNome");
		var enderecoInformado = document.getElementById("botaoEndereco");
		var objTelefone = returnObject(formulario, "telefone");
		var objRamal = returnObject(formulario, "ramal");
		var objFax = returnObject(formulario, "fax");
		var objMenorConsumo = returnObject(formulario, "menorConsumo");
		
////	var objGerenciaID = returnObject(formulario, "gerenciaID");
		var objUnidadeNegocioID = returnObject(formulario, "idUnidadeNegocio");		
		var objClasseID = returnObject(formulario, "classeID");
		var objPorteID = returnObject(formulario, "porteID");
	
		if (!testarCampoValorZero(objLocalidadeID, "Código")){
			objLocalidadeID.focus();
		}
		/*else if(!enderecoInformado.disabled){
			alert("Informe Endereço Localidade.");
			enderecoInformado.focus();
		}*/
		else if(objRamal.value.length > 0){
			if (!testarCampoValorZero(objRamal, "Ramal")){
				objRamal.focus();
			}
			else if(objTelefone.value.length < 1){
				alert("Informe Telefone.");
				objTelefone.focus();
			}
			else if (objTelefone.value.length > 0 && !testarCampoValorZero(objTelefone, "Telefone")){
				objTelefone.focus();
			}
			else if (objTelefone.value.length > 0 && objTelefone.value.length < 7){
				alert("Telefone deve conter no mínimo 7 dígitos");
				objTelefone.focus();
			}
			else if (objFax.value.length > 0 && !testarCampoValorZero(objFax, "Fax")){
				objFax.focus();
			}
			else if (objFax.value.length > 0 && objFax.value.length < 7){
				alert("Fax deve conter no mínimo 7 dígitos");
				objFax.focus();
			}
			else if (objMenorConsumo.value.length > 0 && !testarCampoValorZero(objMenorConsumo, "Menor consumo")){
				objMenorConsumo.focus();
			}
			else {
				formulario.action = "/gsan/atualizarLocalidadeAction.do";
				submeterFormPadrao(formulario);	
			}
		}
		else if (objTelefone.value.length > 0 && !testarCampoValorZero(objTelefone, "Telefone")){
			objTelefone.focus();
		}
		else if (objTelefone.value.length > 0 && objTelefone.value.length < 7){
			alert("Telefone deve conter no mínimo 7 dígitos");
			objTelefone.focus();
		}
		else if (objFax.value.length > 0 && !testarCampoValorZero(objFax, "Fax")){
			objFax.focus();
		}
		else if (objFax.value.length > 0 && objFax.value.length < 7){
			alert("Fax deve conter no mínimo 7 dígitos");
			objFax.focus();
		}
		else if (objMenorConsumo.value.length > 0 && !testarCampoValorZero(objMenorConsumo, "Menor consumo")){
			objMenorConsumo.focus();
		}
		else if(objUnidadeNegocioID.value == -1){
			alert('Informe Unidade Negócio.');
			objUnidadeNegocioID.focus();
		}
		else if(objClasseID.value == -1){
			alert('Informe Classe.');
			classeID.focus();
		}
		else if(objPorteID.value == -1){
			alert('Informe Porte.');
			porteID.focus();
		}
		else {
			formulario.action = "/gsan/atualizarLocalidadeAction.do";
			submeterFormPadrao(formulario);
		}
	}
}


function remover(){
	var form = document.forms[0];
	form.action = "/gsan/exibirAtualizarLocalidadeAction.do?removerEndereco=1";
	form.submit();
}


function carregarElos(){
	form = document.forms[0];
	document.getElementById("limparCampos").value = "1";
	form.action = "/gsan/exibirAtualizarLocalidadeAction.do?objetoConsulta=1";
	form.submit();
}

	function limparGerente() {

    	var form = document.forms[0];

    	form.gerenteLocalidade.value = "";
    	form.nomeGerente.value = "";
  	}
	
	/* Recuperar Popup */
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
	    
	    if (tipoConsulta == 'cliente') {

		    form.gerenteLocalidade.value = codigoRegistro;
		    form.nomeGerente.value = descricaoRegistro;
	      	form.nomeGerente.style.color = "#000000";
	    
	    }else if(tipoConsulta == 'municipio'){
	    	form.municipio.value = codigoRegistro;
		    form.descricaoMunicipio.value = descricaoRegistro;
	      	form.descricaoMunicipio.style.color = "#000000";
	    }
	}
	
	function pesquisarMunicipio(event) {
		validaEnterComMensagem(event, 'exibirAtualizarLocalidadeAction.do?objetoConsulta=3&limparCampos=1', 'municipio','Município Principal');
	}
	
	function limparMunicipio(){
		var form = document.forms[0];
		form.municipio.value = "";
    	form.descricaoMunicipio.value = "";
	}
	
	function verificarLocalidadeSede(){
	
		var form = document.forms[0];
		
		form.action = 'exibirAtualizarLocalidadeAction.do?verificarLocalidadeSede=sim';
		form.submit();
	}

//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5" onload="document.forms[0].removerEndereco.value=''; setarFoco('${requestScope.nomeCampo}');">

<html:form action="/exibirAtualizarLocalidadeAction" method="post">

<INPUT TYPE="hidden" name="removerEndereco">
<INPUT TYPE="hidden" name="limparCampos" id="limparCampos">

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
          <td class="parabg">Atualizar Localidade</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      <tr>
      	<td colspan="2">Para alterar a localidade, informe os dados abaixo:</td>
      	<logic:present scope="application" name="urlHelp">
				<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroLocalizacaoLocalidadeAtualizar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
		</logic:present>
		<logic:notPresent scope="application" name="urlHelp">
				<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
		</logic:notPresent>
      </tr>
      </table>
      <table width="100%" border="0">
	  <tr> 
          <td><strong>Código:</strong></td>
          <td colspan="2">
				<html:hidden property="localidadeID"/>
				<bean:write name="AtualizarLocalidadeActionForm" property="localidadeID"/>
		  </td>
      </tr>
	  <tr> 
          <td><strong>Nome:<font color="#FF0000">*</font></strong></td>
          <td colspan="2"><html:text property="localidadeNome" size="45" maxlength="30" tabindex="2"/></td>
      </tr>
      <tr>
         <td><strong>Endere&ccedil;o Localidade:</strong></td>
         <td align="right">

		 <logic:present name="colecaoEnderecos">
			<logic:empty name="colecaoEnderecos">
				<input type="button" class="bottonRightCol" value="Adicionar" tabindex="3" id="botaoEndereco" onclick="javascript:abrirPopup('exibirInserirEnderecoAction.do?tipoPesquisaEndereco=localidade&operacao=2', 560, 450);">
			</logic:empty>
			<logic:notEmpty name="colecaoEnderecos">
				<input type="button" class="bottonRightCol" value="Adicionar" tabindex="3" id="botaoEndereco" onclick="javascript:abrirPopup('exibirInserirEnderecoAction.do?tipoPesquisaEndereco=localidade&operacao=2', 560, 450);" disabled>
			</logic:notEmpty>
		 </logic:present>

		 <logic:notPresent name="colecaoEnderecos">
			<input type="button" class="bottonRightCol" value="Adicionar" tabindex="3"  id="botaoEndereco" onclick="javascript:abrirPopup('exibirInserirEnderecoAction.do?tipoPesquisaEndereco=localidade&operacao=2', 560, 450);">
		 </logic:notPresent>
		
		</td>
     </tr>
     <tr>
         <td colspan="2" height="70" valign="top">
			<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<table width="100%" border="0" bgcolor="#90c7fc">
					<tr bgcolor="#90c7fc" height="18">
						<td width="10%" align="center"><strong>Remover</strong></td>
						<td align="center"><strong>Endere&ccedil;o</strong></td>
					</tr>
					</table>
				</td>
			</tr>

			<logic:present name="colecaoEnderecos">

			<tr>
				<td>
					<table width="100%" align="center" bgcolor="#99CCFF">
						<!--corpo da segunda tabela-->

						<% String cor = "#cbe5fe";%>

						<logic:iterate name="colecaoEnderecos" id="endereco">
						
							<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
								cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF" height="18">	
							<%} else{	
								cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe" height="18">		
							<%}%>
																            
								<td width="10%" align="center"><img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" style="cursor:hand;" alt="Remover" 
								onclick="javascript:if(confirm('Confirma remoção?')){remover();}"></td>
								<td>
									<a href="javascript:abrirPopup('exibirInserirEnderecoAction.do?exibirEndereco=OK&tipoPesquisaEndereco=localidade&operacao=2', 570, 700)"><bean:write name="endereco" property="enderecoFormatado"/></a>
								</td>
							</tr>
						</logic:iterate>

					</table>
			  	</td>
			</tr>

			</logic:present>

			</table>
	   </td>
   </tr>
   
   <logic:equal name="pemissaoIndicadorBloqueio" value="1">
	   	<tr>
	        <td><strong>Indicador de Bloqueio:</strong></td>
	        <td>
				<html:radio property="indicadorBloqueio" value="1"/><strong>Sim
				<html:radio property="indicadorBloqueio" value="2"/>Não</strong>
			</td>
	    </tr>
    </logic:equal>

    <logic:equal name="pemissaoIndicadorBloqueio" value="2">
	   	<tr>
	        <td><strong>Indicador de Bloqueio:</strong></td>
	        <td>
	        	<logic:equal name="bloqueio" value="true">
					<input type="radio" name="indicadorBloqueio" value="1" disabled checked><strong>Sim</strong>
					<input type="radio" name="indicadorBloqueio" value="2" disabled><strong>Não</strong>
				</logic:equal>
				<logic:equal name="bloqueio" value="false">
					<input type="radio" name="indicadorBloqueio" value="1" disabled><strong>Sim</strong>
					<input type="radio" name="indicadorBloqueio" value="2" disabled checked><strong>Não</strong>
				</logic:equal>
			</td>
	    </tr>
    </logic:equal>
    
   <tr>
       <td><strong>Telefone:</strong></td>
       <td><html:text property="telefone" size="10" maxlength="9" tabindex="4"
       onkeypress="return isCampoNumerico(event);"/></td>
   </tr>
   <tr> 
      <td><strong>Ramal:</strong></td>
	  <td><html:text property="ramal" size="5" maxlength="4" tabindex="5"
	  onkeypress="return isCampoNumerico(event);"/></td>
   </tr>
   <tr>
	  <td><strong>Fax:</strong></td>
	  <td><html:text property="fax" size="10" maxlength="9" tabindex="6"
	  onkeypress="return isCampoNumerico(event);"/></td>
   </tr>    
   <tr>
	  <td><strong>E-mail:</strong></td>
	  <td><html:text property="email" size="58" maxlength="40" tabindex="7"/></td>
   </tr>
   <tr>
	  <td><strong>Menor Consumo:</strong></td>
	  <td><html:text property="menorConsumo" size="10" maxlength="6" tabindex="8"/></td>
   </tr>
   <tr>
	   <td><strong>Unidade Negócio:<font color="#FF0000">*</font></strong></td>
	   <td>
			<html:select property="idUnidadeNegocio" tabindex="9" onchange="carregarElos();">
			<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
				<html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id"/>
			</html:select>
	   </td>
   </tr>
   <tr>
	  <td><strong>Localidade Pólo:</strong></td>
	  <td>
			<html:select property="eloID" style="width: 200px;" tabindex="10">
				<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
				<logic:present name="colecaoElo">
					<html:options collection="colecaoElo" labelProperty="descricao" property="id"/>
				</logic:present>
			</html:select>
			
	  </td>
   </tr>
   <tr>
	   <td><strong>Classe:<font color="#FF0000">*</font></strong></td>
	   <td>
			<html:select property="classeID" style="width: 200px;" tabindex="11">
			<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
				<html:options collection="colecaoClasse" labelProperty="descricao" property="id"/>
			</html:select>
	   </td>
   </tr>
   <tr>
	   <td><strong>Porte:<font color="#FF0000">*</font></strong></td>
	   <td>
			<html:select property="porteID" style="width: 200px;" tabindex="12">
			<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
				<html:options collection="colecaoPorte" labelProperty="descricao" property="id"/>
			</html:select>
	   </td>
   </tr>
   <tr>
	   <td><strong>Local de Armazenagem do Hidrômetro: </strong></td>
	   <td>
			<html:select property="hidrometroLocalArmazenagem" style="width: 200px;" tabindex="12">
			<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
				<html:options collection="colecaoHidrometroLocalArmazenagem" labelProperty="descricao" property="id"/>
			</html:select>
	   </td>
   </tr>
   <tr>
      <td><strong>ICMS:</strong></td>
	  <td><html:text property="icms" size="15" maxlength="10" tabindex="13"/></td>
   
   </tr>
   <tr>
      <td><strong>Centro de Custo:</strong></td>
	  <td><html:text property="centroCusto" size="15" maxlength="10" tabindex="14"/></td>
   
   </tr>
   
   <tr>
      <td><strong>Centro de Custo de Esgoto:</strong></td>
	  <td><html:text property="centroCustoEsgoto" size="15" maxlength="10" tabindex="14"/></td>
   
   </tr>

	<tr>
		<td><strong>Informatizada?</strong><font color="#FF0000">*</font></td>
		<td>
			<html:radio property="informatizada"
				value="<%=""+ConstantesSistema.SIM%>" /> <strong>Sim</strong>
			<html:radio property="informatizada"
				value="<%=""+ConstantesSistema.NAO%>" /> <strong>N&atilde;o</strong>
		</td>
	</tr>
	
	<tr>
		<td width="160"><strong>Gerente da Localidade:</strong></td>
		
		<td>
			
			<html:text maxlength="9" 
				tabindex="1"
				property="gerenteLocalidade" 
				size="9"
				onkeyup="somente_numero(this);"
				onkeypress="validaEnterComMensagem(event, 'exibirAtualizarLocalidadeAction.do?objetoConsulta=2','gerenteLocalidade','Gerente da Localidade');"/>
				
				<a href="javascript:abrirPopup('exibirPesquisarClienteAction.do', 570, 700)">
				
					<img width="23" 
						height="21" 
						border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Gerente" /></a> 

				<logic:present name="gerenteLocalidadeEncontrado" scope="request">
					<html:text property="nomeGerente" 
						size="35"
						maxlength="35" 
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" />
				</logic:present> 

				<logic:notPresent name="gerenteLocalidadeEncontrado" scope="request">
					<html:text property="nomeGerente" 
						size="35"
						maxlength="35" 
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: red" />
				</logic:notPresent>

				
				<a href="javascript:limparGerente();"> 
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" 
						title="Apagar" />
				</a>					
			</td>
	</tr>
	
	<tr>
		<td><strong>Sede?</strong><font color="#FF0000">*</font></td>
		<td>
			<html:radio property="sede" onclick="javascript:verificarLocalidadeSede();"
				value="<%=""+ConstantesSistema.SIM%>" /> <strong>Sim</strong>
			<html:radio property="sede"
				value="<%=""+ConstantesSistema.NAO%>" /> <strong>N&atilde;o</strong>
		</td>
	</tr>

	<tr>
      	<td width="160"><strong>Município Principal:</strong></td>
        <td>
        	<html:text property="municipio" size="4" maxlength="4" 
        		onkeypress="pesquisarMunicipio(event)" onkeyup="somente_numero_zero_a_nove(this);" />
			<a href="javascript:abrirPopup('exibirPesquisarMunicipioAction.do', 570, 700);">
			<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" alt="Pesquisar" border="0" title="Pesquisar Município Principal"></a>

			<logic:present name="corMunicipio" scope="request">
				<html:text property="descricaoMunicipio" 
					size="35"
					maxlength="35" 
					readonly="true"
					style="background-color:#EFEFEF; border:0; color: #000000" />
			</logic:present> 

			<logic:notPresent name="corMunicipio" scope="request">
				<html:text property="descricaoMunicipio" 
					size="35"
					maxlength="35" 
					readonly="true"
					style="background-color:#EFEFEF; border:0; color: red" />
			</logic:notPresent>
        	
        	<a href="javascript:limparMunicipio();">
        	<img src="<bean:message key='caminho.imagens'/>limparcampo.gif" alt="Apagar" border="0" title="Apagar"></a>
		</td>
   	</tr>	

   <tr>
        <td><strong>Indicador de uso:</strong></td>
        <td>
			<html:radio property="indicadorUso" value="1"/><strong>Ativo
			<html:radio property="indicadorUso" value="2"/>Inativo</strong>
		</td>
      </tr>
   
   <tr>



       <td></td>
       <td><font color="#FF0000">*</font>&nbsp;Campos obrigat&oacute;rios</td>
   </tr>
   <tr>
   		<td>
		<logic:present name="voltar">
			<logic:equal name="voltar" value="filtrar">
				<input name="Button" type="button" class="bottonRightCol"
				value="Voltar" align="left"
				onclick="window.location.href='<html:rewrite page="/exibirFiltrarLocalidadeAction.do?desfazer=N"/>'">
			</logic:equal>
			<logic:equal name="voltar" value="manter">
				<input name="Button" type="button" class="bottonRightCol"
				value="Voltar" align="left"
				onclick="window.location.href='<html:rewrite page="/exibirManterLocalidadeAction.do"/>'">
			</logic:equal>
		</logic:present>
		<logic:notPresent name="voltar">
			<input name="Button" type="button" class="bottonRightCol"
			value="Voltar" align="left"
			onclick="window.location.href='<html:rewrite page="/exibirManterLocalidadeAction.do"/>'">
		</logic:notPresent>
   		
   		<input name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirAtualizarLocalidadeAction.do?desfazer=S"/>'">
		
		<input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onClick="window.location.href='/gsan/telaPrincipal.do'"></td>						
   
       <td align="right">
       <%-- <INPUT type="button" class="bottonRightCol" value="Atualizar" tabindex="13" style="width: 70px;" onclick="validarForm(document.forms[0]);"> --%>
        <gsan:controleAcessoBotao name="Botao" value="Atualizar" onclick="validarForm(document.forms[0]);" url="atualizarLocalidadeAction.do" tabindex="13"/>
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


