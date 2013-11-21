<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.Util" %>
<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<html:javascript staticJavascript="false"  formName="FiltrarComandoNegativacaoPorCriterioActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script>
<!--

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
	if(objetoRelacionado.readOnly != true){
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
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	var form = document.forms[0];
	if (tipoConsulta == 'usuario') {
    	form.usuarioResponsavelId.value = codigoRegistro;
       	form.action = 'exibirFiltrarComandoNegativacaoPorCriterioAction.do';
        form.submit(); 
    }
}

function habilitarPesquisaUsuarioResponsavel(form) {
	if (form.usuarioResponsavelId.readOnly == false) {
		form.tipoPesquisa.value = 'usuario';
		chamarPopup('exibirUsuarioPesquisar.do', 'usuario', null, null, 275, 500, '',form.usuarioResponsavelId.value);
	}	
}

function validaEnterUsuarioResponsavel(tecla, caminhoActionReload, nomeCampo) {

	var form = document.FiltrarComandoNegativacaoPorCriterioActionForm;
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código do Usuário");
	
	if(form.usuarioResponsavelId.value.length > 0){
		form.tituloComando.readOnly = true;
		form.tipoBuscaTituloComando.readOnly = true;
		form.comandoSimulado.readOnly = true;
		form.dataGeracaoComandoInicial.readOnly = true;
		form.dataGeracaoComandoFinal.readOnly = true;
	} else {
		form.tituloComando.readOnly = false;
		form.tipoBuscaTituloComando.readOnly = false;
		form.comandoSimulado.readOnly = false;
		form.dataGeracaoComandoInicial.readOnly = false;
		form.dataGeracaoComandoFinal.readOnly = false;
	}
}

function limparForm(tipo){
    var form = document.FiltrarComandoNegativacaoPorCriterioActionForm;
    if(tipo == 'imovel')
    {
    	form.codigoCliente.readOnly = false;
		var ObjCodigoImovel = returnObject(form,"codigoImovel");
		var ObjInscricaoImovel = returnObject(form,"inscricaoImovel");
		ObjCodigoImovel.value = "";
		ObjInscricaoImovel.value = "";
		
		form.inscricaoImovel.value = "";
	}
	if(tipo == 'cliente')
    {
    	form.codigoImovel.readOnly = false;
		var ObjCodigoCliente = returnObject(form,"codigoCliente");
		var ObjNomeCliente = returnObject(form,"nomeCliente");
		ObjCodigoCliente.value = "";
		ObjNomeCliente.value = "";
		
		form.nomeCliente.value = "";
	}
	
	if(tipo == 'usuario')
    {
    	form.usuarioResponsavelId.readOnly = false;
		var ObjCodigoUsuario = returnObject(form,"usuarioResponsavelId");
		var ObjNomeUsuario = returnObject(form,"usuarioResponsavelNome");
		ObjCodigoUsuario.value = "";
		ObjNomeUsuario.value = "";
		
		form.nomeUsuario.value = "";
	}
	
	
}
function validaCampos(){
	var form = document.FiltrarComandoNegativacaoPorCriterioActionForm;

	if (form.usuarioOk.value != 'true' && form.usuarioResponsavelId.value != ''){
		alert('Usuário Responsável não foi preenchido corretamente');
		return false;
	}
	
	if (form.dataGeracaoComandoInicial.value.length > 0){
		if (!verificaDataMensagemPersonalizada(form.dataGeracaoComandoInicial, "Data inicial de geração do comando inválida.")){
			return false
		}
	}
	
	if (form.dataGeracaoComandoFinal.value.length > 0){
		if (!verificaDataMensagemPersonalizada(form.dataGeracaoComandoFinal, "Data final de geração do comando inválida.")){
			return false
		}
	}
	
	if (form.dataGeracaoComandoInicial.value.length < 1 && form.dataGeracaoComandoFinal.value.length > 0) {
		form.dataGeracaoComandoFinal.value = "";
	}
	
	if (form.dataGeracaoComandoInicial.value.length > 0 && form.dataGeracaoComandoFinal.value.length < 1) {
		form.dataGeracaoComandoFinal.value = form.dataGeracaoComandoInicial.value;
	}
	
	return true;
}

function validaForm(){
   	var form = document.forms[0];
  	if(validateFiltrarComandoNegativacaoPorCriterioActionForm(form)){
  		if (validaCampos()) {
			submeterFormPadrao(form);
		}
  	}
}
-->
</script>
</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">
<div id="formDiv">
<html:form action="/filtrarComandoNegativacaoPorCriterioAction"   
	name="FiltrarComandoNegativacaoPorCriterioActionForm"
  	type="gcom.gui.cobranca.spcserasa.FiltrarComandoNegativacaoPorCriterioActionForm"
  	method="post">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<input type="hidden" name="tipoPesquisa"/>
<html:hidden property="usuarioOk" />

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="150" valign="top" class="leftcoltext">
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
		<td width="613" valign="top" class="centercoltext">
			<!--Início Tabela Reference a Páginação da Tela de Processo-->
            <table>
              <tr>
                <td></td>
              </tr>
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="11"><img border="0" src="<bean:message key='caminho.imagens'/>parahead_left.gif"/></td>
                <td class="parabg">Filtrar Comando de Negativa&ccedil;&atilde;o </td>
                <td width="11" valign="top"><img border="0" src="<bean:message key='caminho.imagens'/>parahead_right.gif"/></td>
              </tr>
            </table>
            <!--Fim Tabela Reference a Páginação da Tela de Processo-->
            
<p>&nbsp;</p>
            <table align="center" width="100%" border="0" >
              <tr>
                <td width="30%">Para filtrar o(s) comando(s),</td>
                <td width="55%">informe os dados abaixo:</td>
                <td width="15%"><html:checkbox property="checkAtualizar" value="1"/><strong>Atualizar</strong></td>
              </tr>
              <tr> 
                 <td width="158"><strong>T&iacute;tulo do Comando:</strong></td>
                 <td colspan="3"><p>
                   <html:textarea property="tituloComando" cols="50" rows="2"/>
                 	</p>
                 	<p>
                    	<html:radio property="tipoBuscaTituloComando" value="1" disabled="false" />
                     		Iniciando pelo texto
					  	<html:radio property="tipoBuscaTituloComando" value="2" disabled="false" />
                 			Contendo o texto 
                 	</p>
                 </td>
              </tr>
              <tr>
                <td width="148" ><strong>Negativador:</strong></td>
                <td width="369" ><strong>
                <logic:present name="colecaoNegativador">  
                   	   <html:select property="idNegativador" tabindex="7">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:present name="colecaoNegativador">
								<html:options collection="colecaoNegativador" labelProperty="cliente.nome" property="id"/>
							</logic:present>
						</html:select>
                		</logic:present>  
               </strong> </td>		
              </tr>
             <tr> 
                <td width="158"><strong>Comando Simulado: <font color="#FF0000">*</font></strong></td>
                <td colspan="3"><strong>
                  <html:radio property="comandoSimulado" value="3" disabled="false" />
                  <strong> Todos </strong>
                  <html:radio property="comandoSimulado" value="1" disabled="false" />
                  <strong> Sim
                  <html:radio property="comandoSimulado" value="2" disabled="false" />
                  <strong>N&atilde;o<strong></strong></strong></strong></strong></td>
              </tr>
              <tr> 
                <td><strong>Per&iacute;odo de Gera&ccedil;&atilde;o do Comando:</strong></td>
                <td align="right" colspan="3"> <div align="left"><strong>
                  	<html:text property="dataGeracaoComandoInicial" size="10" maxlength="10"
						tabindex="8" onkeyup="mascaraData(this, event);" /> 
						<a	href="javascript:abrirCalendarioReplicando('FiltrarComandoNegativacaoPorCriterioActionForm', 'dataGeracaoComandoInicial','dataGeracaoComandoFinal')">
							<img border="0"	src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a> a </strong>
                   
                   <html:text property="dataGeracaoComandoFinal" size="10" maxlength="10"
						tabindex="8" onkeyup="mascaraData(this, event);" />
							<a href="javascript:abrirCalendario('FiltrarComandoNegativacaoPorCriterioActionForm', 'dataGeracaoComandoFinal')">
								<img border="0"	src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a> (dd/mm/aaaa) </div></td>
              </tr>
              <tr>
                <td height="28"><strong>Usu&aacute;rio Respons&aacute;vel:</strong></td>
                <td colspan="3">
					<html:text property="usuarioResponsavelId" maxlength="9" size="9" 
						onkeyup="return validaEnterUsuarioResponsavel(event, 'exibirFiltrarComandoNegativacaoPorCriterioAction.do', 'usuarioResponsavelId'); " />
						<a href="javascript:habilitarPesquisaUsuarioResponsavel(document.forms[0]);" alt="Pesquisar Usuário Responsável">
							<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
					
					<logic:present name="corUsuario">
						<logic:equal name="corUsuario" value="exception">
							<html:text property="usuarioResponsavelNome" size="38"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corUsuario" value="exception">
							<html:text property="usuarioResponsavelNome" size="38"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> 
					<logic:notPresent name="corUsuario">
						<logic:empty name="FiltrarComandoNegativacaoPorCriterioActionForm"	property="usuarioResponsavelId">
							<html:text property="usuarioResponsavelNome" size="38" value="" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarComandoNegativacaoPorCriterioActionForm" property="usuarioResponsavelId">
							<html:text property="usuarioResponsavelNome" size="38"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent>
					<a href="javascript:limparForm('usuario');"> 
						<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" /> 
					</a>
				</td>
              </tr>
			  <tr> 
                <td><strong> <font color="#FF0000"></font></strong></td>
                <td align="right"> <div align="left"><strong><font color="#FF0000">*</font></strong> 
                    Campos obrigat&oacute;rios</div></td>
              </tr>
              <tr> 
                <td><div align="left"><strong><font color="#FF0000">
                    <input name="Submit22"
						class="bottonRightCol" value="Limpar" type="button"
						onclick="window.location.href='/gsan/exibirFiltrarComandoNegativacaoPorCriterioAction.do?menu=sim';">
                </font></strong></div></td>
                <td colspan="3"><div align="right">
                  <input type="button" onClick="javascript:validaForm();" name="botaoFiltrar" class="bottonRightCol" value="Filtrar">                
                </div></td>
              </tr>
            </table>
            
          <p>&nbsp;</p></tr>
		<tr>
		  <td colspan="3">
		</td>
	</tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>
</div>
</body>
</html:form>

<%@ include file="/jsp/util/telaespera.jsp"%>

<script>

</script>



</html:html>