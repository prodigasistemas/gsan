<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript">
<!-- Begin

 var bCancel = false;

    function validateFiltrarMapaControleContaRelatorioActionForm(form) {
        if (bCancel)
      return true;
        else
       return validateRequired(form) && validaMesAno(form.mesAno);
   }
   
    function required () {
     this.aa = new Array("idGrupoFaturamento", "Informe Grupo.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("mesAno", "Informe Mês/Ano.", new Function ("varName", " return this[varName];"));
    }

	function validaMesAno(mesAno){
		if(mesAno.value != ""){
			return verificaAnoMes(mesAno);
		}else{
			return true;
		}
	}


	function limparForm(){
		var form = document.forms[0];

		form.idGrupoFaturamento.value="-1";
		form.mesAno.value="";
		form.firma.value="-1";
	}
	
function chamarFiltrar(){
  var form = document.forms[0];
  //if(form.idGrupoFaturamento.value == '-1'){
  	//alert('Informe Grupo');
//  }else{
	  if (validateFiltrarMapaControleContaRelatorioActionForm(form)) {
	  	toggleBox('demodiv',1);
	  }
  //}
}
-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form
  action="/filtrarResumoContaLocalidadeRelatorioAction.do"
  method="post"
  name="FiltrarMapaControleContaRelatorioActionForm"
  type="gcom.gui.faturamento.conta.FiltrarMapaControleContaRelatorioActionForm"
  onsubmit="return validateFiltrarMapaControleContaRelatorioActionForm(this);"
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

          <td class="parabg">Filtrar Resumo de Contas Emitidas por Localidade no Grupo</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
	  <table width="100%" border="0">
        <tr>
        	<td width="100%" colspan=2>
	        	<table width="100%" border="0">
		          	<tr>
		          		<td>Para filtrar a(s) conta(s) emitida(s), informe os dados abaixo:</td>
		          	</tr>
		          </table>
        	</td>
        </tr>
        <tr>
          <td width="10%"><strong>Grupo:</strong><font color="#FF0000">*</font> </td>
          <td width="90%"><html:select property="idGrupoFaturamento">
          		<html:option value="-1">&nbsp;</html:option>
              <html:options collection="faturamentoGrupos" labelProperty="descricao" property="id"/>
             </html:select>
          </td>
        </tr>
        <tr>
          <td width="10%"><strong>Mês/Ano:</strong><font color="#FF0000">*</font></td>
          <td width="90%">
          	<html:text property="mesAno" size="7"  maxlength="7" onkeypress="javascript:mascaraAnoMes(this, event);"/>
          	&nbsp;mm/aaaa
          </td>
        </tr>
        <tr>
					 <td  width="40%"class="style3"><strong>Firma:</strong></td>
					 <td  width="60%" colspan="2">
					  			<html:select property="firma" tabindex="5" style="width:200px;">
									<html:option value="-1"> TODOS </html:option>
									<html:options collection="colecaoEmpresas" property="id" labelProperty="descricao"/>
								</html:select></td>
		</tr>
		<tr>
					<td><strong>
						<span class="style2">Tipo de Impressão:<font color="#FF0000">*</font></span>
						</strong>
					</td>
					<td align="left" colspan="2">
						<label> 
							<html:radio	property="tipoImpressao" value="1"/><strong>Normal</strong>
						</label>
						<label>
							<html:radio property="tipoImpressao" value="2"/> <strong>Impressão Simultânea</strong>
						</label>	
					</td>		
		</tr>			
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>
          	<input type="button" name="Button" class="bottonRightCol" value="Limpar" 
          		onclick="document.forms[0].reset();limparForm();"/>									
          </td>
          <td><div align="right">
   	         <gsan:controleAcessoBotao  name="Button" value="Gerar"
							  onclick="javascript:chamarFiltrar();" url="filtrarResumoContaLocalidadeRelatorioAction.do"/>
		                    	<%--
          <input type="button" name="Button" class="bottonRightCol" value="Filtrar" onClick="javascript:chamarFiltrar();"/>--%></div></td>
        </tr>
      </table>
      <p>&nbsp;</p>
	</td>
  </tr>
</table>
<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=filtrarResumoContaLocalidadeRelatorioAction.do" />
<%@ include file="/jsp/util/rodape.jsp"%>


</html:form>
</body>
</html:html>