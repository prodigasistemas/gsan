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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirImovelActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>

<script>
<!-- Begin

     var bCancel = false;

    function validateInserirImovelActionForm(form) {
        if (bCancel)
      return true;
        else
       return validateCaracterEspecial(form) && testarCampoValorZero(document.InserirImovelActionForm.idImovel, 'Matrícula')
              && validateRequired(form) && validateLong(form);
   }

    function caracteresespeciais () {
     this.aa = new Array("idImovel", "Imóvel deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }

    function required () {}

    function IntegerValidations () {
     this.am = new Array("idImovel", "Imóvel deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
//End -->
</script>


<script language="javaScript">
/*function pesquisaEnter(tecla) {

  var form = document.forms[0];

  if (document.all) {
    var codigo = event.keyCode;
  } else {
    var codigo = tecla.which;
  }
  if(form.idImovel.value != ''){
    if (codigo == 13) {
      form.action = "exibirInserirImovelPrincipalAction.do";
      form.submit();
    } else {
      return true;
    }
  }
}*/

  //Recebe os dados do(s) popup(s)

  function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'imovel') {
      limparPesquisaImovel();
      form.idImovel.value = codigoRegistro;
      //form.descricaoImovel.value = descricaoRegistro;
      form.action='inserirImovelWizardAction.do?action=exibirInserirImovelPrincipalAction&pesquisar=SIM';
      form.submit();
    }
  }

function limparPesquisaImovel() {
    var form = document.forms[0];

      form.idImovel.value = "";
      //form.descricaoImovel.value = "";


  }

  function limpaImovelPrincipal(){
    var form = document.InserirImovelActionForm;
    var id = form.idImovel.value;

    form.idImovel.value= "";
    window.location.href = "removerInserirImovelPrincipalAction.do?idRemocaoPrincipalImovel=" + id;
  }
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/inserirImovelWizardAction" method="post" onsubmit="return validateInserirImovelActionForm(this);">


<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=3"/>



<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
<input type="hidden" name="numeroPagina" value="3"/>
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
      </div></td>
    <td width="720" valign="top" class="centercoltext">
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Inserir Imóvel</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>

      <p>&nbsp;</p>
            <table width="100%" border="0">
<!-- ****************************************************************************************** -->










<!-- ****************************************************************************************** -->
               <tr>
                  <td width="10%"><strong>Imóvel:</strong></td>
                  <td width="90%"><html:text property="idImovel" maxlength="9" size="9" onkeypress="javascript:return validaEnter(event, 'inserirImovelWizardAction.do?action=exibirInserirImovelPrincipalAction&pesquisar=SIM', 'idImovel');"/>
                      <a href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);">
                       <img width="23" height="21" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"/></a><a href="javascript:limpaImovelPrincipal();"><img border="0" src="<bean:message key="caminho.imagens"/>limparcampo.gif"/></a>
                        <logic:present name="idImovelNaoEncontrado" scope="request">

                              <font color="#FF0000">
                                      <bean:message key="pesquisa.registro.inexistente"/>
                              </font>

                        </logic:present>
                   </td>

                 </tr>
	    <tr>
<!-- ************************************************************************ -->


               <table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<table width="100%"  border="0" cellpadding="1" cellspacing="0" bgcolor="#90c7fc" bordercolor="#90c7fc">
					<!--header da tabela interna -->
					<tr>
						<td align="center"><strong>Endere&ccedil;o</strong></td>
					</tr>
					</table>
				</td>
			</tr>

			<logic:present name="imoveisPrincipal">

			<tr>
				<td height="40">
					<div style="width: 100%; height: 100%; overflow: auto;">
							<table width="100%" align="left" bgcolor="#99CCFF">
							<!--corpo da segunda tabela-->

						<% String cor = "#FFFFFF";%>

						<logic:iterate name="imoveisPrincipal" id="endereco">

							<%	if (cor.equalsIgnoreCase("#FFFFFF")){
									cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
							<%} else{
									cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
							<%}%>
								<td align="center">
									<bean:write name="endereco" property="enderecoFormatado"/>
								</td>

						</logic:iterate>

							</tr>
						</table>
					</div>
				</td>
			</tr>

			</logic:present>

			</table>




<!-- ************************************************************************ -->
                      <table width="100%">
                        <tr>
        	          <td colspan="2">
						<div align="right">
							<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=3"/>
						</div>
						</td>
                        </tr>
                     </table>
                  </tr>
                  </table>
<!-- **** -->

              </tr>

	     </table>
	</tr>
<!-- *************** -->
            </tr>


            </table>

      <p>&nbsp;</p>
    </td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>



<script>
<!--
	/*document.InserirImovelActionForm.descricaoImovel.disabled = true;
	document.InserirImovelActionForm.tipoLogradouro.disabled = true;
	document.InserirImovelActionForm.tituloLogradouro.disabled = true;
	document.InserirImovelActionForm.logradouro.disabled = true;
	document.InserirImovelActionForm.bairro.disabled = true;
	document.InserirImovelActionForm.municipio.disabled = true;
	document.InserirImovelActionForm.cep.disabled = true; */
-->
</script>
</html:form>

</body>
<script>
	  var form = document.InserirImovelActionForm;
	  form.idImovel.focus();

</script>

</html:html>
