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

<html:javascript staticJavascript="false"  formName="FiltrarComandoNegativacaoTipoMatriculaActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script>
<!--
function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado, campo){
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
    	form.idUsuarioResponsavel.value = codigoRegistro;
       	form.action = 'exibirFiltrarComandoNegativacaoTipoMatriculaAction.do';
        form.submit(); 
    }
}

function habilitarPesquisaUsuarioResponsavel(form) {
	if (form.idUsuarioResponsavel.readOnly == false) {
		form.tipoPesquisa.value = 'usuario';
		chamarPopup('exibirUsuarioPesquisar.do', 'usuario', null, null, 275, 500, '',form.idUsuarioResponsavel.value);
	}	
}

function validaEnterUsuarioResponsavel(tecla, caminhoActionReload, nomeCampo) {

	var form = document.FiltrarComandoNegativacaoTipoMatriculaActionForm;
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código do Usuário");
	
}

function enviar(){
	var form = document.forms[0];
	form.submit();		
}
-->
</script>
</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">
<div id="formDiv">
<html:form
	action="filtrarComandoNegativacaoTipoMatriculaAction"    
	name="FiltrarComandoNegativacaoTipoMatriculaActionForm"
  	type="gcom.gui.cobranca.spcserasa.FiltrarComandoNegativacaoTipoMatriculaActionForm"
  	method="post">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<input type="hidden" name="tipoPesquisa"/>

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
                  <td class="parabg">Filtrar Comandos de Negativa&ccedil;&atilde;o - Por  Matr&iacute;cula de Im&oacute;veis</td>

                  <td width="11" valign="top"><img border="0" src="<bean:message key='caminho.imagens'/>parahead_right.gif"/></td>
                </tr>
              </table> 
              <!--Fim Tabela Reference a Páginação da Tela de Processo-->
              <p>&nbsp;</p>
              <table width="100%" border="0" dwcopytype="CopyTableRow">
                <tr> 
                  <td colspan="4">Para filtrar o(s) comando(s) de negativa&ccedil;&atilde;o,  informe os dados abaixo:</td>
                </tr>

                <tr>
                  <td><strong>Negativador<span class="style5">:</span></strong></td>
                  <td colspan="2"><strong>
                    <logic:present name="colecaoNegativador">  
                   	   <html:select property="idNegativador" tabindex="7">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:present name="colecaoNegativador">
								<html:options collection="colecaoNegativador" labelProperty="cliente.nome" property="id"/>
							</logic:present>
						</html:select>
                	</logic:present>  
                  <b> </b> <b> </b> </strong></td>
                </tr>
                <tr>
                  <td colspan="4"><hr></td>
                </tr>
                <tr> 
                  <td><strong>Identifica&ccedil;&atilde;o da CI:</strong></td>
                  <td colspan="2"><p>
                    <textarea name="identificacaoCI" cols="50" rows="5"></textarea>
                  </p>
                  <p>
                  	<html:radio property="tipoPesquisaIdentificacaoCI" value="1" disabled="false" />
					Iniciando pelo texto
                  	<html:radio property="tipoPesquisaIdentificacaoCI" value="2" disabled="false" />
					Contendo o texto </p></td>
                </tr>
                <tr>
                  <td colspan="4"><hr></td>

                </tr>
                <tr> 
                  	<td height="28"><strong>Usu&aacute;rio Respons&aacute;vel:</strong></td>
	                <td colspan="3">
						<html:text property="idUsuarioResponsavel" maxlength="9" size="9" 
							onkeyup="return validaEnterUsuarioResponsavel(event, 'exibirFiltrarComandoNegativacaoTipoMatricula.do', 'idUsuarioResponsavel'); " />
							<a href="javascript:habilitarPesquisaUsuarioResponsavel(document.forms[0]);" alt="Pesquisar Usuário Responsável">
								<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
						
						<logic:present name="corUsuario">
							<logic:equal name="corUsuario" value="exception">
								<html:text property="nomeUsuarioResponsavel" size="38"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="corUsuario" value="exception">
								<html:text property="nomeUsuarioResponsavel" size="38"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						<logic:notPresent name="corUsuario">
							<logic:empty name="FiltrarComandoNegativacaoTipoMatriculaActionForm" property="idUsuarioResponsavel">
								<html:text property="nomeUsuarioResponsavel" size="38" value="" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="FiltrarComandoNegativacaoTipoMatriculaActionForm" property="idUsuarioResponsavel">
								<html:text property="nomeUsuarioResponsavel" size="38"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent>
						<a href="javascript:limparForm('usuario');"> 
							<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" /> 
						</a>
					</td>
                </tr>
                <tr> 
                  <td colspan="4"><hr></td>
                </tr>
                <tr>
                  <td height="17">&nbsp;</td>
                  <td colspan="3">&nbsp;</td>
                </tr>
                <tr>

                  <td height="17"><strong><font color="#FF0000">
                    <input name="Submit22"
						class="bottonRightCol" value="Limpar" type="button"
						onclick="window.location.href='/gsan/exibirFiltrarComandoNegativacaoTipoMatriculaAction.do?menu=sim';">
                  </font></strong></td>
                  <td colspan="3"><div align="right"><img src="<bean:message key='caminho.imagens'/>voltar.gif" width="15" height="24">
                      <input name="Button32222" type="button" class="bottonRightCol" value="Voltar" onClick="javascript:window.location.href='/gsan/exibirFiltrarComandoNegativacaoAction.do'"/>
                      <input name="Button3222" type="button" class="bottonRightCol" value="Filtrar" onClick="return enviar();" />
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