<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="gcom.util.ConstantesSistema" %>

<%@ page import="gcom.cadastro.sistemaparametro.SistemaParametro" %>


<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AdicionarCategoriaContaActionForm" dynamicJavascript="false"/>



<%-- Colocado por Raphael Rossiter em 14/03/2007
	Objetivo: Manipulação dos objetos que serão exibidos no formulário de acordo com a empresa --%>
								
<%-- ================================================================================= --%>
        
<logic:notEqual name="indicadorTarifaCategoria" value="<%= "" + SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA %>">
        
<SCRIPT LANGUAGE="JavaScript">
<!--

	var bCancel = false; 

    function validateAdicionarCategoriaContaActionForm(form) {                                                                   
        if (bCancel) 
      return true; 
        else 
       return validateRequired(form) && validateCaracterEspecial(form) && validateLong(form); 
   	} 

    
    function required () { 
     this.aa = new Array("categoriaID", "Informe Categoria.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("qtdEconomia", "Informe Quantidade de Economias.", new Function ("varName", " return this[varName];"));
    } 

    function caracteresespeciais () { 
     this.aa = new Array("qtdEconomia", "Quantidade de Economias possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    } 

    function IntegerValidations () { 
     this.aa = new Array("qtdEconomia", "Quantidade de Economias deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    
//-->
</SCRIPT>

</logic:notEqual>
        
<%-- ================================================================================= --%>
<%-- ================================================================================= --%>


<%-- Colocado por Raphael Rossiter em 14/03/2007
	Objetivo: Manipulação dos objetos que serão exibidos no formulário de acordo com a empresa --%>
								
<%-- ================================================================================= --%>
        
<logic:equal name="indicadorTarifaCategoria" value="<%= "" + SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA %>">
        
<SCRIPT LANGUAGE="JavaScript">
<!--

	var bCancel = false; 

    function validateAdicionarCategoriaContaActionForm(form) {                                                                   
        if (bCancel) 
      return true; 
        else 
       return validateRequired(form) && validateCaracterEspecial(form) && validateLong(form); 
   	} 

    
    function required () { 
     this.aa = new Array("categoriaID", "Informe Categoria.", new Function ("varName", " return this[varName];"));
      this.ab = new Array("subcategoriaID", "Informe Subcategoria.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("qtdEconomia", "Informe Quantidade de Economias.", new Function ("varName", " return this[varName];"));
    } 

    function caracteresespeciais () { 
     this.aa = new Array("qtdEconomia", "Quantidade de Economias possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    } 

    function IntegerValidations () { 
     this.aa = new Array("qtdEconomia", "Quantidade de Economias deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    
    
    function carregarSubcategoria(idCategoria){
    
    	if (idCategoria != null && idCategoria != undefined && idCategoria != "-1"){
    		redirecionarSubmit("/gsan/exibirAdicionarCategoriaContaAction.do?carregarSubcategoria=" + idCategoria);
    	}
    	else{
    	
    		//Limpar o list com as subcategorias
    	}
    	
    }
    
//-->
</SCRIPT>

</logic:equal>
        
<%-- ================================================================================= --%>
<%-- ================================================================================= --%>

<SCRIPT LANGUAGE="JavaScript">
<!--

	function validarForm(form){
	
		if (validateAdicionarCategoriaContaActionForm(form) &&
			testarCampoValorZero(form.qtdEconomia, "Quantidade de Economias")){
			submeterFormPadrao(form);
		}
	}

//-->
</SCRIPT>

</head>

<logic:present name="reloadPage">
	
	<logic:equal name="reloadPageURL" value="INSERIRCONTA">
		<body leftmargin="0" topmargin="0" onload="window.focus(); resizePageSemLink(480, 250); chamarSubmitComUrl('exibirInserirContaAction.do?reloadPage=1'); window.close();">
	</logic:equal>
	
	<logic:equal name="reloadPageURL" value="RETIFICARCONTA">
		<body leftmargin="0" topmargin="0" onload="window.focus(); resizePageSemLink(480, 250); chamarSubmitComUrl('exibirRetificarContaAction.do?reloadPage=1'); window.close();">
	</logic:equal>
	
	<logic:equal name="reloadPageURL" value="SIMULARCALCULOCONTA">
		<body leftmargin="0" topmargin="0" onload="window.focus(); resizePageSemLink(550, 270); chamarSubmitComUrl('exibirSimularCalculoContaAction.do'); window.close();">
	</logic:equal>
	
	
</logic:present>

<logic:notPresent name="reloadPage">
	<body leftmargin="0" topmargin="0" onload="resizePageSemLink(550, 270);">
</logic:notPresent>


<html:form action="/adicionarCategoriaContaAction" method="post">

<table width="522" border="0" cellpadding="0" cellspacing="5">
  <tr>
    <td width="512" valign="top" class="centercoltext"> 
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" /></td>
          <td class="parabg">Adicionar Categoria</td>
          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
        <tr>
          <td colspan="4">Preencha os campos para inserir uma categoria:</td>
          <logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoContaCategoriaAdicionar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
		  </logic:present>
		  <logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
		  </logic:notPresent>  	 
      </tr>
      </table>
      
      <table width="100%" border="0">
        
        <%-- Colocado por Raphael Rossiter em 14/03/2007
			Objetivo: Manipulação dos objetos que serão exibidos no formulário de acordo com a empresa --%>
								
		<%-- ================================================================================= --%>
        
		<logic:notEqual name="indicadorTarifaCategoria" value="<%= "" + SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA %>">
        
        <tr>
          <td height="20"><strong>Categoria:<font color="#FF0000">*</font></strong></td>
          <td>
          
          	<html:select property="categoriaID" style="width:200;">
          		<html:option value="<%= ""+ConstantesSistema.NUMERO_NAO_INFORMADO %>">&nbsp;</html:option>
				<logic:present name="colecaoAdicionarCategoria">
					<html:options collection="colecaoAdicionarCategoria" labelProperty="descricao" property="id"/>
				</logic:present>
			</html:select>
          
          </td>
        </tr>
        
        </logic:notEqual>
        
        <%-- ================================================================================= --%>
        <%-- ================================================================================= --%>
        
        
        
        <%-- Colocado por Raphael Rossiter em 14/03/2007
			Objetivo: Manipulação dos objetos que serão exibidos no formulário de acordo com a empresa --%>
								
		<%-- ================================================================================= --%>
        
		<logic:equal name="indicadorTarifaCategoria" value="<%= "" + SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA %>">
        
        <tr>
          <td height="20"><strong>Categoria:<font color="#FF0000">*</font></strong></td>
          <td>
          
          	<html:select property="categoriaID" style="width:200;" onchange="carregarSubcategoria(this.value);">
          		<html:option value="<%= ""+ConstantesSistema.NUMERO_NAO_INFORMADO %>">&nbsp;</html:option>
				<logic:present name="colecaoAdicionarCategoria">
					<html:options collection="colecaoAdicionarCategoria" labelProperty="descricao" property="id"/>
				</logic:present>
			</html:select>
          
          </td>
        </tr>
        <tr>
          <td height="20"><strong>Subcategoria:<font color="#FF0000">*</font></strong></td>
          <td>
          
          	<html:select property="subcategoriaID" style="width:350;">
          		<html:option value="<%= ""+ConstantesSistema.NUMERO_NAO_INFORMADO %>">&nbsp;</html:option>
				<logic:present name="colecaoAdicionarSubCategoria">
					<html:options collection="colecaoAdicionarSubCategoria" labelProperty="descricao" property="id"/>
				</logic:present>
			</html:select>
          
          </td>
        </tr>
        
        </logic:equal>
        
        <%-- ================================================================================= --%>
        <%-- ================================================================================= --%>
        
        
        
        <tr>
          <td><strong>Quantidade de Economias:<font color="#FF0000">*</font></strong></td>
          <td>
          <html:text property="qtdEconomia" size="6" maxlength="4" style="text-align: right;" tabindex="2"
          onkeypress="javascript:return isCampoNumerico(event);" />
          </td>
        </tr>
        </table>
        
        <table width="100%" border="0">
        <tr>
          <td height="30"> 
          	<div align="right">
              <input type="button" onclick="validarForm(document.forms[0]);" class="bottonRightCol" value="Inserir" style="width: 70px;">&nbsp;
              <input type="button" onclick="window.close();" class="bottonRightCol" value="Fechar" style="width: 70px;">
			</div>
		  </td>
        </tr>
	  </table>
      
      <p>&nbsp;</p>
      </td>
  </tr>
</table>

</html:form>

</body>
</html:html>


