<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<%@ page import="gcom.cadastro.imovel.Imovel" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<%--<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirImovelActionForm" dynamicJavascript="false" />--%>
<script>
function validateManterImovelActionForm(form) {
	var endereco = document.getElementById("validarEndereco").value;
	var retorno = false;

	if (endereco == "1"){
		retorno = true;
	}
	else{
		alert("Informe o endereço.");
	}

	return retorno;
}

function removerEndereco(url){

	if(confirm('Confirma remoção ?')){
       var form = document.forms[0];
    	form.action = url;
	    form.submit()	
	}
	

}

</script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>

</head>

<body leftmargin="5" topmargin="5">
<div id="formDiv">
<html:form action="/atualizarImovelWizardAction" method="post"

>
<%--onsubmit="return validateManterImovelActionForm(this);"--%>

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_tela_espera.jsp?numeroPagina=2"/>



<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
<input type="hidden" name="numeroPagina" value="2"/>
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
      </div></td>
    <td width="645" valign="top" class="centercoltext">
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Atualizar</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
            <table width="100%" border="0" >
              <tr>
                <td colspan="2"><p>Clique em adicionar para informar o endere&ccedil;o
                    abaixo:</p></td>
                <logic:present scope="application" name="urlHelp">
							<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroImovelAtualizarAbaEndereco', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
				</logic:present>
				<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
				</logic:notPresent>    
              </tr>
              </table>
            <table width="100%" border="0" >
              <tr>
                <td width="183"><strong>Endere&ccedil;o do Imóvel<font color="#FF0000">*</font></strong></td>
                <td width="432" align="right">

		 <logic:present name="colecaoEnderecos">
			<logic:empty name="colecaoEnderecos">
				<input type="button" class="bottonRightCol" value="Adicionar" id="botaoEndereco" onclick="javascript:abrirPopup('exibirInserirEnderecoAction.do?tipoPesquisaEndereco=imovel&operacao=2&mostrarPerimetro=sim', 570, 700);">
				<INPUT TYPE="hidden" id="validarEndereco" value="0">
			</logic:empty>
			<logic:notEmpty name="colecaoEnderecos">
				<input type="button" class="bottonRightCol" value="Adicionar" id="botaoEndereco" onclick="javascript:abrirPopup('exibirInserirEnderecoAction.do?tipoPesquisaEndereco=imovel&operacao=2&mostrarPerimetro=sim', 570, 700);" disabled>
				<INPUT TYPE="hidden" id="validarEndereco" value="1">
			</logic:notEmpty>
		 </logic:present>

		 <logic:notPresent name="colecaoEnderecos">
			<input type="button" class="bottonRightCol" value="Adicionar" id="botaoEndereco" onclick="javascript:abrirPopup('exibirInserirEnderecoAction.do?tipoPesquisaEndereco=imovel&operacao=2&mostrarPerimetro=sim', 570, 700);">
			<INPUT TYPE="hidden" id="validarEndereco" value="0">
		 </logic:notPresent>

                </td>
              </tr>
              <tr>
                <table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<table width="100%"  border="0" cellpadding="1" cellspacing="0" bgcolor="#99CCFF" bordercolor="#99CCFF">
					<!--header da tabela interna -->
					<tr>
						<td width="50" align="center"><strong>Remover</strong></td>
						<td align="center"><strong>Endere&ccedil;o</strong></td>
					</tr>
					</table>
				</td>
			</tr>

			<logic:present name="colecaoEnderecos">

			<tr>
				<td height="40">
					<div style="width: 100%; height: 100%; overflow: auto;">
							<table width="100%" align="center" bgcolor="#99CCFF">
							<!--corpo da segunda tabela-->

						<% String cor = "#cbe5fe";%>

						<logic:iterate name="colecaoEnderecos" id="endereco" type="Imovel">

							<%	if (cor.equalsIgnoreCase("#cbe5fe")){
									cor = "#cbe5fe";%>
								<tr bgcolor="FFFFFF">
							<%} else{
									cor = "#FFFFFF";%>
								<tr bgcolor="#cbe5fe">
							<%}%>

								<td width="50" align="center">
								 <a href="javascript:removerEndereco('removerAtualizarImovelColecaoEnderecoAction.do?enderecoRemoverSelecao=<%=""+endereco.getUltimaAlteracao().getTime()%>');"><img border="0" src="/gsan/imagens/Error.gif"/></a>
								</td>
								<td>
                                    <a href="javascript:abrirPopup('exibirInserirEnderecoAction.do?exibirEndereco=OK&tipoPesquisaEndereco=imovel&operacao=2&mostrarPerimetro=sim', 570, 700)"><bean:write name="endereco" property="enderecoFormatado"/></a>
								</td>

						</logic:iterate>

							</tr>
						</table>
					</div>
				</td>
			</tr>

			</logic:present>

                        </div></td>
                    </tr>
                     <tr>
                       <td colspan="2">
						<div align="right">
							<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_tela_espera.jsp?numeroPagina=2"/>
						</div>
					  </td>
                     </tr>
                  </table></td>
              </tr>


            </table>
	   </table>
    </td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>
<%@ include file="/jsp/util/tooltip.jsp" %>


</html:form>
</div>
</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/atualizarImovelWizardAction.do?concluir=true&action=atualizarImovelEnderecoAction'); }
</script>

</html:html>
