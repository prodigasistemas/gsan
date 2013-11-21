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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AtualizarGrupoActionForm" dynamicJavascript="false" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script>
<!--
    var bCancel = false;

    function validateAtualizarGrupoActionForm(form) {
        if (bCancel)
      return true;
        else
       return true;
   }
-->
</script>

<script language="JavaScript" >

function trocar() {
	document.forms[0].btnSalvar.disabled = false;
}

function salvar() {
	document.forms[0].action = 'atualizarGrupoWizardAction.do?action=exibirAtualizarGrupoAcessosGrupoAction&cadastrarOperacao=true';
	document.forms[0].submit();
}

function facilitador(objeto){
	if (objeto.id == "0" || objeto.id == undefined){
		objeto.id = "1";
		marcarTodos();
		trocar();
	}
	else{
		objeto.id = "0";
		desmarcarTodos();
		document.forms[0].btnSalvar.disabled = true;
	}
}
</script>
</head>

<body >

<html:form
    action="/atualizarGrupoWizardAction"
    method="post"
    onsubmit="return validateAtualizarGrupoActionForm(this);"
>

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=2"/>

<logic:present name="idFuncionalidade">
	<input type="hidden" name="codigoFuncionalidade" value="<bean:write name="idFuncionalidade" />" >
</logic:present>

<logic:notPresent name="idFuncionalidade">
	<input type="hidden" name="codigoFuncionalidade" value="" >
</logic:notPresent>

<input type="hidden" name="cadastrarOperacao" value="false" >

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="120" valign="top" class="leftcoltext">
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
          <td class="parabg">Atualizar Grupo - Acessos Grupo</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      
      <p>&nbsp;</p>
      
      <table border="0" width="100%">
        <tr>
          <td colspan="2">Para controlar o(s) acesso(s) do grupo 
          <bean:write name="AtualizarGrupoActionForm" property="descricao"/>, marque ou desmarque a(s) funcionalidade(s):</td>
        </tr>

		<tr>
		  <td colspan="2">
		    <table width="100%" border="0">
			  <tr>
				<td valign="top" width="70">
				  <table width="99%" border="1" bordercolor="#000000">
				    <tr bordercolor="#90c7fc">
				      <td>
				        <bean:write name="arvoreFuncionalidades" scope="request" filter="false"/>
				      </td>
				    </tr>
				  </table>
				</td>
				
				<td valign="top" width="30">
				  <table width="99%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000">
					<tr bordercolor="#90c7fc">
					  <logic:notEmpty name="descricaoFuncionalidade">
					    <td height="19" colspan="4" bgcolor="#90c7fc">Opera&ccedil;&otilde;es de <strong><bean:write name="descricaoFuncionalidade" scope="request"/></strong></td>
					  </logic:notEmpty>
					
					  <logic:empty name="descricaoFuncionalidade">
					    <td height="19" colspan="4" bgcolor="#90c7fc">Opera&ccedil;&otilde;es:</td>
					  </logic:empty>
					</tr>
					
					<logic:notEmpty name="descricaoFuncionalidade">
						<tr>
						  <td>
							<div align="center">
							  <strong>
							    <a href="javascript:facilitador(this);" id="0">Todos</a>
							  </strong>
							</div>
						  </td>
						  <td>
							<div align="center">
							  <strong>Operação</strong>
							</div>
						  </td>
						</tr>
					</logic:notEmpty>
					
					<logic:notEmpty name="operacoesCadastradas">
					<%--Esquema de paginação das operações cadastradas--%>
					  <logic:iterate name="operacoesCadastradas" id="operacao">
					    <tr bordercolor="#90c7fc">
					      <td>
					        <strong>
					          <input onchange="javascript:trocar()" type="checkbox" name="operacoes" value="<bean:write name="operacao" property="id"/>" checked="true">
					        </strong>
					      </td>
						  <td>
					        <strong>
					          <bean:write name="operacao" property="descricao"/>
							</strong>
						  </td>
					    </tr>
					  </logic:iterate>
					<%--Fim do Esquema de paginação das operações cadastradas--%>
					</logic:notEmpty>
					
					<logic:notEmpty name="operacoesNaoCadastradas">
					<%--Esquema de paginação das operações Não cadastradas--%>
					  <logic:iterate name="operacoesNaoCadastradas" id="operacao">
					    <tr bordercolor="#90c7fc">
					      <td>
					        <strong>
					          <input onchange="javascript:trocar()" type="checkbox" name="operacoes" value="<bean:write name="operacao" property="id"/>"/>
					        </strong>
					      </td>
						  <td>
					        <strong>
					          <bean:write name="operacao" property="descricao"/>
							</strong>
						  </td>
					    </tr>
					  </logic:iterate>
	                <%--Fim do Esquema de paginação das operações Não cadastradas--%>
					</logic:notEmpty>
					
					<%--Esquema para exibir a menssagem de operações--%>
					<logic:empty name="operacoesCadastradas">
					  <logic:empty name="operacoesNaoCadastradas">
					 	<tr>
					      <td>Click no link da funcionalidade para exibir as operações
					        <bean:define id="naoTemOperacoes" value="1" />
					      </td>
					    </tr>
					  </logic:empty>
					</logic:empty>
					
					<logic:notPresent name="naoTemOperacoes" >
					  <tr>
					    <td colspan="4">
					      <input name="btnSalvar" class="bottonRightCol" value="Salvar" disabled="disabled" type="button" onclick="javascritp:salvar();">
					    </td>
					  </tr>
					</logic:notPresent> 
	              </table>
				</td>					  
			  </tr>
			</table>
		  </td>
		</tr>  
		<tr>
          <td colspan="2"><div align="right"><jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=2"/></div></td>
        </tr>
      </table>
    </td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</body>
</html:form>
</html:html>
