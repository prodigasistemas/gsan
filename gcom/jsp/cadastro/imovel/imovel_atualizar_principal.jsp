<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="gcom.cadastro.imovel.Imovel" %>
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ManterImovelActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>

<script>
<!-- Begin

     var bCancel = false;

    function validateManterImovelActionForm(form) {
        if (bCancel)
      return true;
        else
       return testarCampoValorZero(document.ManterImovelActionForm.idImovel, 'Imóvel') 
       validateCaracterEspecial(form) && validateRequired(form) && validateLong(form);
   }

    function caracteresespeciais () {
     this.aa = new Array("idImovel", "Imóvel possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    }

    function required () {}

    function IntegerValidations () {
     this.am = new Array("idImovel", "Imóvel deve somente conter dígitos.", new Function ("varName", " return this[varName];"));
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
      form.action='atualizarImovelWizardAction.do?action=exibirAtualizarImovelPrincipalAction&pesquisar=SIM';
      form.submit();
    }
  }

function limparPesquisaImovel() {
    var form = document.forms[0];

      form.idImovel.value = "";
      //form.descricaoImovel.value = "";


  }

  function limpaImovelPrincipal(){
    var form = document.ManterImovelActionForm;
    var id = form.idImovel.value;

    form.idImovel.value= "";
    window.location.href = "removerAtualizarImovelPrincipalAction.do?idRemocaoPrincipalImovel=" + id;
  }
  
  function limparId(){
      var form = document.ManterImovelActionForm;
      if(form.quantidadeElemento.value == 0){
    		form.idImovel.value= "";
      }
  }
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form
    action="/atualizarImovelWizardAction"
    method="post"
    onsubmit="return validateManterImovelActionForm(this);"
>

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=3"/>


<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

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
      </div></td>
    <td width="660" valign="top" class="centercoltext">
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
            <table width="100%" border="0">
<!-- ****************************************************************************************** -->










<!-- ****************************************************************************************** -->
                 <tr>
                  <td width="10%"><strong>Imóvel:</strong></td>
                  <td width="90%"><html:text property="idImovel" maxlength="9" size="9" 
                  onkeypress="javascript:return validaEnter(event, 'atualizarImovelWizardAction.do?action=exibirAtualizarImovelPrincipalAction&pesquisar=SIM', 'idImovel');"/>
                      <a href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);"><img width="23" height="21" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"/></a><img border="0" src="<bean:message key="caminho.imagens"/>limparcampo.gif" onclick="javascript:limpaImovelPrincipal()"/>
                        <logic:present name="idImovelNaoEncontrado" scope="request">

                              <font color="#FF0000">
                                      <bean:message key="pesquisa.registro.inexistente"/>
                              </font>

                        </logic:present>
                   </td>
               </tr>
	    <tr>
<!-- *************** -->
                        <table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<table width="100%"  border="0" cellpadding="1" cellspacing="0" bgcolor="#99CCFF" bordercolor="#99CCFF">
					<!--header da tabela interna -->
					<tr>
						<td align="center"><strong>Endere&ccedil;o</td>
					</tr>
					</table>
				</td>
			</tr>

			<logic:present name="imoveisPrincipal">

			<tr>
				<td height="40">
					<div style="width: 100%; height: 100%; overflow: auto;">
							<table width="100%" align="center" bgcolor="#99CCFF">
							<!--corpo da segunda tabela-->

						<% String cor = "#FFFFFF";%>
						<logic:present name="imoveisPrincipal">
						<logic:iterate name="imoveisPrincipal" id="imovel" type="Imovel">

							<%	if (cor.equalsIgnoreCase("#FFFFFF")){
									cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
							<%} else{
									cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
							<%}%>
								<td align="center">

									<bean:write name="imovel" property="enderecoFormatado"/>
								</td>
							</tr>
						</logic:iterate>
						</logic:present>
						</table>
					</div>
				</td>
			</tr>

			</logic:present>
			</table>
<!-- **** -->
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

<!-- *************** -->
            
	
            

      <p>&nbsp;</p>
    </td>
  </tr>
</table>
<input type="hidden" name="numeroPagina" value="3"/>
<%@ include file="/jsp/util/rodape.jsp"%>
<%@ include file="/jsp/util/tooltip.jsp" %>

<script>
<!-- Begin
///	limparId();

//End -->
</script>

</html:form>

</body>
<script>
    var form = document.forms[0];
     form.idImovel.focus();

</script>

</html:html>
