<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.cadastro.geografico.Bairro" %>
<%@ page import="gcom.util.ConstantesSistema" %>

<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key='caminho.css'/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="SelecionarBairroActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<SCRIPT LANGUAGE="JavaScript">
<!--


function limpar(tipo){

	var form = document.forms[0];
}



function validarForm(form){

	var objMunicipio = returnObject(form, "idMunicipio");
	var voltarSituacao = false;
	
	if (objMunicipio.disabled){
		objMunicipio.disabled = false;
		voltarSituacao = true;
	}
	
	var objNomeBairro = returnObject(form, "nomeBairro");
	
	if (validateSelecionarBairroActionForm(form)){
	
		if (objMunicipio.value.length > 0 &&
			!testarCampoValorZero(objMunicipio, "Município ")){
			objMunicipio.focus();
		}
		else{
			redirecionarSubmit('pesquisarSelecionarBairroAction.do');
		} 
	}
	else if (voltarSituacao){
		objMunicipio.disabled = true;
	}
}


function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg){

	if (objeto == null || codigoObjeto == null){
		abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
	}
	else{
		if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
			alert(msg);
		}
		else{
			abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
		}
	}
}


function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	var form = document.forms[0];

	if (tipoConsulta == 'municipio') {
    	form.idMunicipio.value = codigoRegistro;
      	form.nomeMunicipio.value = descricaoRegistro;
      	form.nomeMunicipio.style.color = "#000000";
      	form.nomeBairro.value = "";
    }	
}

function facilitador(objeto){
	if (objeto.id == "0" || objeto.id == undefined){
		objeto.id = "1";
		marcarTodos();
	}
	else{
		objeto.id = "0";
		desmarcarTodos();
	}
}

// Verifica se há item selecionado
function verificarSelecao(objeto){

	if (CheckboxNaoVazioMensagemGenerico("Nenhum registro foi selecionado.", objeto)){
		redirecionarSubmit('inserirSelecaoBairroAction.do');
	}
}

// Verifica se há item selecionado
function verificarSelecaoParaRemocao(objeto){

	if (CheckboxNaoVazio(objeto)){
		if (confirm ("Confirma exclusão ?")) {
			redirecionarSubmit('removerSelecaoBairroAction.do');
		 }
	}
}

//-->
</SCRIPT>

</head>

<logic:present name="retornarUseCase">

	<logic:equal name="operacao" value="1">	
		<BODY TOPMARGIN="5" LEFTMARGIN="5" onload="chamarSubmitComUrl('exibirInserirLogradouroAction.do'),window.close();">
	</logic:equal>
		
	<logic:equal name="operacao" value="2">	
		<BODY TOPMARGIN="5" LEFTMARGIN="5" onload="chamarSubmitComUrl('exibirAtualizarLogradouroAction.do'),window.close();">
	</logic:equal>
	
	<logic:equal name="operacao" value="3">	
		<BODY TOPMARGIN="5" LEFTMARGIN="5" onload="chamarSubmitComUrl('exibirGerarRelatorioLogradourosPorMunicipioAction.do'),window.close();">
	</logic:equal>
	
</logic:present>

<logic:notPresent name="retornarUseCase">
	<BODY TOPMARGIN="5" LEFTMARGIN="5" onload="resizePageSemLink(700, 370); setarFoco('${requestScope.nomeCampo}');">
</logic:notPresent>

<html:form action="/exibirSelecionarBairroAction" method="post">

<table width="650" border="0" cellpadding="0" cellspacing="5">
  <tr>
    <td width="640" valign="top" class="centercoltext"> <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img src="<bean:message key='caminho.imagens'/>parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" /></td>
          <td class="parabg">Selecionar Bairros</td>
          <td width="11"><img src="<bean:message key='caminho.imagens'/>parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      <tr>
		<td>Preencha os campos para selecionar bairros:</td>
      	<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=bairroSelecionar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
      </tr>
      </table> 
    
      
		
	  <!-- FORMULARIO --------------------------------------------------------------------------------------------------------------- -->
	  <table width="100%" border="0"> 
	  <tr>
			<td><strong>Munic&iacute;pio:<font color="#FF0000">*</font></strong></td>
			<td colspan="2">
			
			<logic:empty name="SelecionarBairroActionForm" property="idMunicipio">
				<html:text maxlength="4" property="idMunicipio" size="4" tabindex="1"
				onkeypress="return validaEnter(event, 'exibirSelecionarBairroAction.do', 'idMunicipio');" />
			
				<a href="javascript:abrirPopup('exibirPesquisarMunicipioAction.do');">
				<img width="23" height="21" border="0"
				src="<bean:message key='caminho.imagens'/>pesquisa.gif" title="Pesquisar Município" /></a>
			</logic:empty>

			<logic:notEmpty name="SelecionarBairroActionForm" property="idMunicipio">
				<html:text maxlength="4" property="idMunicipio" size="4" tabindex="1" disabled="true"/>
			
				<img width="23" height="21" border="0" src="<bean:message key='caminho.imagens'/>pesquisa.gif" title="Pesquisar Município" />
			</logic:notEmpty>
			
			<logic:present name="idMunicipioNaoEncontrado">
			<logic:equal name="idMunicipioNaoEncontrado" value="exception">
				<html:text property="nomeMunicipio" size="40" maxlength="30"
						readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
			</logic:equal>
			<logic:notEqual name="idMunicipioNaoEncontrado" value="exception">
					<html:text property="nomeMunicipio" size="40" maxlength="30"
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" />
			</logic:notEqual>
			</logic:present> 
			
			<logic:notPresent name="idMunicipioNaoEncontrado">
			<logic:empty name="SelecionarBairroActionForm" property="idMunicipio">
				<html:text property="nomeMunicipio" value="" size="40"
				maxlength="30" readonly="true"
				style="background-color:#EFEFEF; border:0; color: #ff0000" />
			</logic:empty>
			<logic:notEmpty name="SelecionarBairroActionForm" property="idMunicipio">
				<html:text property="nomeMunicipio" size="40" maxlength="30"
				readonly="true"
				style="background-color:#EFEFEF; border:0; color: #000000" />
			</logic:notEmpty>

			</logic:notPresent>
			
			<logic:empty name="SelecionarBairroActionForm" property="idMunicipio">
				<a href="javascript:limparPesquisaMunicipio();"> <img
				src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" title="Apagar" /></a>
			</logic:empty>

			<logic:notEmpty name="SelecionarBairroActionForm" property="idMunicipio">
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0"/>
			</logic:notEmpty>
			
			</td>
	  </tr>
	  <tr>
			<td width="15%"><strong>Nome:<font color="#FF0000"></font></strong></td>
			<td width="85%" colspan="2"><html:text maxlength="30" property="nomeBairro" size="30"
			tabindex="2" /></td>
	  </tr>
	  <tr> 
			<td colspan="3">
				<input type="button" class="bottonRightCol" value="Selecionar" onclick="validarForm(document.forms[0]);" style="width: 80px;" name="botaoSelecionar" tabindex="3">
			</td>
      </tr>
	  <tr>
         <td height="10" colspan="3"></td>
      </tr>
      <tr> 
			<td colspan="3"><strong>Bairros Selecionados:</strong></td>
      </tr>
      <tr> 
          <td colspan="3">
			
			<table width="100%" cellpadding="0" cellspacing="0">
			<tr> 
                <td> 
					
					<table width="100%" bgcolor="#99CCFF">
                    <tr bgcolor="#99CCFF"> 
						<td align="center" width="10%"><A HREF="javascript:facilitador(this);" id="0"><strong>Todos</strong></A></td>
						<td width="20%"><div align="center"><strong>Código</strong></div></td>
                        <td width="40%"><div align="center"><strong>Nome</strong></div></td>
                        <td width="30%"><div align="center"><strong>Município</strong></div></td>
                    </tr>
                    </table>
					
				</td>
            </tr>
            </table>
            

			<logic:present name="colecaoBairrosSelecionados" scope="session">

			<div style="width: 100%; height: 100; overflow: auto;">

			<table width="100%" cellpadding="0" cellspacing="0">
            <tr> 
				<td> 
					
						<% String cor = "#FFFFFF";%>
					
						<table width="100%" align="center" bgcolor="#99CCFF">

						<logic:iterate name="colecaoBairrosSelecionados" id="bairro" type="Bairro">
                            
							
							<%	if (cor.equalsIgnoreCase("#FFFFFF")){
									cor = "#cbe5fe";%>
									<tr bgcolor="#cbe5fe">
							<%} else{
									cor = "#FFFFFF";%>
									<tr bgcolor="#FFFFFF">
							<%}%> 
							
								<td align="center" width="10%">
									<html:checkbox property="idBairroSelecao" value="<%="" + bairro.getId()%>"/></td>
								<td width="20%">
									<div align="center">

									<logic:present name="bairro" property="codigo">
										<bean:write name="bairro" property="codigo"/>
									</logic:present>
									<logic:notPresent name="bairro" property="codigo">&nbsp;</logic:notPresent>

									</div>
								</td>
								<td width="40%">
									<div align="left">
									
									<logic:present name="bairro" property="nome">
											<bean:write name="bairro" property="nome"/>
									</logic:present>
									<logic:notPresent name="bairro" property="nome">&nbsp;</logic:notPresent>
									
									</div>
								</td>
								
								<td width="30%">
									<div align="left">
									
									<logic:present name="bairro" property="municipio">
											<bean:write name="bairro" property="municipio.nome"/>
									</logic:present>
									<logic:notPresent name="bairro" property="municipio.nome">&nbsp;</logic:notPresent>	
									
									</div>
								</td>
							</tr>
							

						</logic:iterate>
                          
					</table>
					
				</td>
            </tr>

			</table>
			
			</div>
			</logic:present>

            
			</td>
        </tr>
        
	  <!-- FIM DO FORMULÁRIO -------------------------------------------------------------------------------------------------------- -->

      <tr>
         <td align="right" colspan="3"> 
              <INPUT type="button" class="bottonRightCol" value="Inserir" tabindex="5" onclick="verificarSelecao(idBairroSelecao);" style="width: 70px;" name="botaoInserir">
		 </td>
      </tr>
      </table>

      <p>&nbsp;</p></td>
  </tr>
</table>

</html:form>

</body>

</html:html>
