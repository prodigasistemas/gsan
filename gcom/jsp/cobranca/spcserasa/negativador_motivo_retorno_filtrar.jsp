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

<html:javascript staticJavascript="false"  formName="FiltrarNegativadorRetornoMotivoActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script>
<!--

function validaEnterCodigoMotivo(tecla, caminhoActionReload, nomeCampo) {
	var form = document.FiltrarNegativadorRetornoMotivoActionForm;
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código do Motivo");
}

function validaCampos(){
	var form = document.FiltrarNegativadorRetornoMotivoActionForm;
	if (form.idNegativador.value == '-1'){
		alert('Informe Negativador');
		return false;
	}
	return true;
}

function validaForm(){
	var form = document.FiltrarNegativadorRetornoMotivoActionForm;
	if(validateFiltrarNegativadorRetornoMotivoActionForm(form)){
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
<html:form action="/filtrarNegativadorRetornoMotivoAction"   
	name="FiltrarNegativadorRetornoMotivoActionForm"
  	type="gcom.gui.cobranca.spcserasa.FiltrarNegativadorRetornoMotivoActionForm"
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
		<td width="655" valign="top" class="centercoltext">
			<!--Início Tabela Reference a Páginação da Tela de Processo-->
            <table>
              <tr>
                <td></td>
              </tr>
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="11"><img border="0" src="<bean:message key='caminho.imagens'/>parahead_left.gif"/></td>
                <td class="parabg">Filtrar Motivo de Retorno do Registro do Negativador</td>
                <td width="11" valign="top"><img border="0" src="<bean:message key='caminho.imagens'/>parahead_right.gif"/></td>
              </tr>
            </table>
            <!--Fim Tabela Reference a Páginação da Tela de Processo-->
<p>&nbsp;</p>
            <table width="100%" border="0">
              <tr> 
                <td colspan="2">Para filtrar o(s) motivo(s) de retorno do registro do negativador, informe 
                  os dados abaixo:</td>
                <td width="90"><html:checkbox property="checkAtualizar" value="1" /> 
                <strong>Atualizar</strong></td>
              </tr>
			  <tr>
                <td><strong>Negativador:<font color="#FF0000">*</font></strong></td>
                <td>
                	<logic:present name="colecaoNegativador">  
                   	   <html:select property="idNegativador" tabindex="7">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:present name="colecaoNegativador">
								<html:options collection="colecaoNegativador" labelProperty="cliente.nome" property="id"/>
							</logic:present>
						</html:select>
               		</logic:present>
                </td>
              </tr><tr> 
                <td width="190"><strong>Código do Motivo:</strong></td>
                <td>
                	<html:text property="codigoMotivo" size="4" maxlength="3" onkeyup="return validaEnterCodigoMotivo(event, 'exibirFiltrarNegativadorRetornoMotivoAction.do', 'codigoMotivo'); "/>
                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  	<logic:present name="corCodigoMotivo">
						<logic:equal name="corCodigoMotivo" value="exception">
							<html:text property="mensagemCodigoMotivo" size="38"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corCodigoMotivo" value="exception">
							<html:text property="mensagemCodigoMotivo" size="38"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present>
                </td>
              </tr>
              <tr>
                <td><span class="MsoNormal"><strong><span style='font-size:9.0pt;font-family:Arial;
      color:black'>Motivo de Retorno do Registro:</span></strong><strong><span
      style='font-size:9.0pt;font-family:Arial;color:red'></span></strong><span
      style='font-size:9.0pt;font-family:Arial;color:black'>
                <o:p></o:p>
                </span></span></td>
                <td colspan="2" align="right"><div align="left"><strong>
                	<html:text property="descricaoRetornoMotivo" size="45" maxlength="40"/>
                </strong></div></td>
              </tr>
             <tr>
                <td><strong>Indicador de Registro Aceito:</strong></td>
                <td colspan="2" align="right"><div align="left">
                  	<html:radio property="indicadorRegistroAceito" value="1" disabled="false" />
                  <strong>Aceito
					<html:radio property="indicadorRegistroAceito" value="2" disabled="false" />
                  Não aceito
                  	<html:radio property="indicadorRegistroAceito" value="3" disabled="false" />
                  Todos
                  </strong></div></td>
              <tr>
              <tr>
                <td><strong>Indicador de Uso:</strong></td>
                <td colspan="2" align="right"><div align="left">
                  	<html:radio property="indicadorUso" value="1" disabled="false" />
                  <strong>Ativo
					<html:radio property="indicadorUso" value="2" disabled="false" />
                  Inativo
                  	<html:radio property="indicadorUso" value="3" disabled="false" />
                  Todos
                  </strong></div></td>
              <tr>  
                <td><strong> <font color="#FF0000"></font></strong></td>
                <td colspan="2" align="right"> <div align="left"><strong><font color="#FF0000">*</font></strong> 
                    Campos obrigat&oacute;rios</div></td>
              </tr>
              <tr> 
                <td><strong> <font color="#FF0000">
                  <input name="Submit22"
						class="bottonRightCol" value="Limpar" type="button"
						onclick="window.location.href='/gsan/exibirFiltrarNegativadorRetornoMotivoAction.do?menu=sim';">
                </font></strong></td>
                <td width="352" align="right">&nbsp;</td>
                <td width="53" align="right">
					<input type="button" onClick="javascript:validaForm();" name="botaoFiltrar" class="bottonRightCol" value="Filtrar">
				</td>
			  </tr>
            </table>
          <p>&nbsp;</p></tr>
		<tr>
		  <td colspan="3">	
			
			
			
			

<%@ include file="/jsp/util/rodape.jsp"%>
</div>
</body>
</html:form>

<%@ include file="/jsp/util/telaespera.jsp"%>

<script>

</script>



</html:html>