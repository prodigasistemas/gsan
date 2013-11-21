<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.cadastro.geografico.BairroArea"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="BairroActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
<!-- Begin
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.BairroActionForm;

    if (tipoConsulta == 'municipio') {
      form.idMunicipio.value = codigoRegistro;
      form.nomeMunicipio.value = descricaoRegistro;
      form.nomeMunicipio.style.color = "#000000";
      form.action = "/gsan/exibirInserirBairroAction.do";
      form.submit();

    }
  }
function validarForm(form){

if(testarCampoValorZero(document.BairroActionForm.idMunicipio, 'Município') && testarCampoValorZero(document.BairroActionForm.codigoBairro, 'Código do Bairro') && testarCampoValorZero(document.BairroActionForm.codigoBairroPrefeitura, 'Código do Bairro na Prefeitura')){

if(validateBairroActionForm(form)){
	form.action = 'inserirBairroAction.do';
    submeterFormPadrao(form);
}
}
}

function limpaMunicipio(){
	var form = document.BairroActionForm;
	
		form.nomeMunicipio.value = "";
		form.idMunicipio.value = "";
		form.codigoBairro.value = "";
		form.idMunicipio.focus();
}

function limpaNomeMunicipio(evt){
	var form = document.BairroActionForm;

	// os comentários seriam se caso usuario queira q nao apague ao navegar nas caixinhas de texto.
	
//	var keyCode = document.layers ? evt.which : document.all ? event.keyCode : document.getElementById ? evt.keyCode : 0; 
	
//	if (keyCode != 37 && keyCode != 38 && keyCode != 39 && keyCode != 40){
		form.nomeMunicipio.value = "";
		form.codigoBairro.value = "";
//	}
}

function redirecionarSubmitAtualizar(idBairro) {
	urlRedirect = "/gsan/exibirAtualizarBairroAction.do?idRegistroAtualizacao=" + idBairro;
	redirecionarSubmit(urlRedirect);
}

-->
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/inserirBairroAction.do" name="BairroActionForm"
	type="gcom.gui.cadastro.geografico.BairroActionForm" method="post"
	onsubmit="return validateBairroActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
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
            <!--Início Tabela Reference a Páginação da Tela de Processo-->
            <table>
              <tr>
                <td></td>

              </tr>
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="11"><img border="0" src="imagens/parahead_left.gif"/></td>
                <td class="parabg">Resumo dos Movimentos de Exclus&atilde;o de Negativa&ccedil;&atilde;o Gerados </td>
                <td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif"/></td>

              </tr>
            </table>
            <!--Fim Tabela Reference a Páginação da Tela de Processo-->
            <table width="100%" border="1">
              <tr> 
                <td width="50%"><strong>Data do Processamento: 
				<logic:present name="GerarMovimentoExclusaoNegativacaoActionForm" property="collMovimento">
                  <logic:iterate name="GerarMovimentoExclusaoNegativacaoActionForm" property="collMovimento" id="collMovimento" indexId="i">
                    <logic:equal name="i" value="0">
                      <logic:present name="collMovimento" property="dataProcessamento" >
                        <bean:write name="collMovimento" property="dataProcessamento" format="dd/MM/yyyy" />
                      </logic:present>  
                    </logic:equal>
                  </logic:iterate>
                </logic:present></strong></td>
                <td width="50%"><strong>Hora do Processamento:

                <logic:present name="GerarMovimentoExclusaoNegativacaoActionForm" property="collMovimento">
                  <logic:iterate name="GerarMovimentoExclusaoNegativacaoActionForm" property="collMovimento" id="collMovimento" indexId="i">
                    <logic:equal name="i" value="0">
                      <logic:present name="collMovimento" property="horaProcessamento" >
                        <bean:write name="collMovimento" property="horaProcessamento" format="HH:mm:ss" />
                      </logic:present>  
                    </logic:equal>
                  </logic:iterate>
                </logic:present></strong></td>
              </tr>

            </table>
            <table width="100%" border="0">
              <tr> 
                <td height="0" colspan="2"><table width="100%" border="1">
                    <tr> 
                      <td width="19%"><div align="center"><strong> Negativador </strong></div></td>
                      <td width="13%"><div align="center"><strong>NSA</strong></div></td>
                      <td width="17%"><div align="center"><strong>Qtd. Registros</strong></div></td>

                      <td width="20%"><div align="center"><strong>Valor do D&eacute;bito </strong></div></td>
                    </tr>
 <logic:present name="GerarMovimentoExclusaoNegativacaoActionForm" property="collMovimento">
                  <logic:iterate name="GerarMovimentoExclusaoNegativacaoActionForm" property="collMovimento" id="collMovimento" indexId="i">
                    <logic:equal name="i" value="0">
                    <tr> 
                      <td><logic:present name="collMovimento" property="descricaoNegativador" ><bean:write name="collMovimento" property="descricaoNegativador" /></logic:present>&nbsp;</td>
                      <td><div align="center"><logic:present name="collMovimento" property="descricaoNegativador" ><bean:write name="collMovimento" property="nsa" /></logic:present>&nbsp;</div></td>
                      <td><div align="center"><logic:present name="collMovimento" property="quantidadeRegistros" ><bean:write name="collMovimento" property="quantidadeRegistros" /></logic:present>&nbsp;</div></td>
                      <td><div align="center"><logic:present name="collMovimento" property="valorDebito" ><bean:write name="collMovimento" property="valorDebito" /></logic:present>&nbsp;</div></td>
                    </tr>
                    </logic:equal>
                  </logic:iterate>
                </logic:present>
                                    
                  </table>

               </td>
              </tr>
              <tr> 
                <td width="158">&nbsp;</td>
                <td width="453" align="right">&nbsp;</td>
              </tr>
              <tr> 
                <td> <font color="#FF0000"> 
                  <input type="submit" name="Submit232" class="bottonRightCol" value="Imprimir">
                  </font></td>

                <td align="right"> &nbsp;</td>
              </tr>
            </table>
          <p>&nbsp;</p></tr>
		<tr>
		  <td colspan="3">
















			</td>
		</tr>
	</table>


	<%@ include file="/jsp/util/rodape.jsp"%>


</html:form>
</body>
</html:html>
