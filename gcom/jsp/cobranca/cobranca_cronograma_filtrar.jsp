<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.Pagina, gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript">
<!-- Begin

 var bCancel = false;

    function validateCobrancaActionForm(form) {
        if (bCancel)
      return true;
        else
       return validaGrupo() && validaMesAno(form.mesAnoFiltro);
   }

	function validaMesAno(mesAno){
		if(mesAno.value != ""){
			return verificaAnoMes(mesAno);
		}else{
			return true;
		}
	}
	
	function validaGrupo(){
		//var form = document.forms[0];
		//if(form.idGrupoCobranca.value == "-1" && form.mesAno.value == ""){
		//	alert("Informe Grupo e/ou Mês/Ano.");
		//	return false;
		//}else{
			return true;
		//}
	}
	
function chamarFiltrar(){
  var form = document.forms[0];
  if (validateCobrancaActionForm(form)) {
  	form.submit();
  }
}

function limparForm(){
	var form = document.forms[0];
	
	form.idGrupoCobrancaFiltro.value = "-1";
	form.mesAnoFiltro.value = "";
}
-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form
  action="/filtrarCobrancaCronogramaAction.do"
  method="post"
  name="CobrancaActionForm"
  type="gcom.gui.cobranca.CobrancaActionForm"
  onsubmit="return validateCobrancaActionForm(this);"
>

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

         <%@ include file="/jsp/util/mensagens.jsp"%>

        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
      </div></td>
    <td width="625" valign="top" class="centercoltext">
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>

          <td class="parabg">Filtrar Cronograma de Cobranca</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
	  <table width="100%" border="0">
        <tr>
          <td width="80%" colspan="2">
          <table>
          	<tr>
          		<td width="80%">Para filtrar o(s) cronograma(s) de cobrança mensal(is), informe os dados abaixo:</td>
          		<td align="right">
          			<input  type="checkbox" 
          					name="indicadorAtualizar" 
          					value="1" 
          					checked/>
          				<strong>Atualizar</strong>
						<p>&nbsp;</p>
				</td>
          	</tr>
          </table>
          </td>
        </tr>
        
        <tr>
          <td width="10%"><strong>Grupo:</strong> </td>
          <td width="90%"><html:select property="idGrupoCobrancaFiltro">
          	  <html:option value="-1">&nbsp;</html:option>
              <html:options collection="colecaoCobrancaGrupo" 
              				labelProperty="descricao" 
              				property="id"/>
             </html:select>
          </td>
        </tr>
        <tr>
          <td width="10%"><strong>Mês/Ano:</strong></td>
          <td width="90%">
          	<html:text  property="mesAnoFiltro" 
	          			size="7"  
          			    maxlength="7" 
          				onkeypress="javascript:mascaraAnoMes(this, event);return isCampoNumerico(event);"/>&nbsp;mm/aaaa
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td align="left">
          	<input  type="button" 
          			name="Button" 
          			class="bottonRightCol" 
          			value="Limpar" 
          			onClick="javascript:limparForm();"/>
          </td>
          <td align="right">
            <gsan:controleAcessoBotao   name="Button" 
            							value="Filtrar" 
            							onclick="javascript:chamarFiltrar();" 
            							url="filtrarCobrancaCronogramaAction.do"/>
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