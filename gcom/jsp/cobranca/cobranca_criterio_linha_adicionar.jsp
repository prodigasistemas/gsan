<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
	
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="CriterioCobrancaActionForm" dynamicJavascript="false" />


<script language="JavaScript">

 var bCancel = false;

    function validateCriterioCobrancaActionForm(form) {
        if (bCancel)
      return true;
        else
      return validateRequired(form) &&
      testarCampoValorDecimalComZero(CriterioCobrancaActionForm.valorDebitoMinimo,'Valor do Débito Mínimo') && 
      testarCampoValorDecimalComZero(CriterioCobrancaActionForm.valorDebitoMaximo,'Valor do Débito Máximo') && 
      testarCampoValorInteiroComZero(CriterioCobrancaActionForm.qtdContasMinima,'Quantidade de Contas Mínima') && 
      testarCampoValorInteiroComZero(CriterioCobrancaActionForm.qtdContasMaxima,'Quantidade de Contas Máxima') &&
      testarCampoValorDecimalComZero(CriterioCobrancaActionForm.vlMinimoDebitoCliente,'Valor Mínimo do Débito para Cliente com Débito Automático') &&
      testarCampoValorInteiroComZero(CriterioCobrancaActionForm.qtdMinContasCliente,'Quantidade Mínima de Contas para Cliente com Débito Automático') &&
      testarCampoValorDecimalComZero(CriterioCobrancaActionForm.vlMinimoContasMes,'Valor Mínimo da Conta do Mês')&& 
      testarCampoValorInteiroComZero(CriterioCobrancaActionForm.quantidadeMinimaParcelasAtraso,'Quantidade Mínima de Parcelas em Atraso');
   }

   
    function required () { 
	this.aa = new Array("parImovelPerfil", "Informe Perfil do Imóvel.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("parCategoria", "Informe Categoria.", new Function ("varName", " return this[varName];"));
    } 
    

    
function desfazer(){
 form = document.forms[0];
 form.reset();
 
}

 
function validarForm(form){
if(validateCriterioCobrancaActionForm(form)){
    submeterFormPadrao(form);
}
}

</script>
</head>

<logic:equal name="fechaPopup" value="false" scope="request">
<body leftmargin="5" topmargin="5"
	onload="javascript:resizePageSemLink(600, 480);javascript:setarFoco('${requestScope.nomeCampo}');">
</logic:equal>
<logic:equal name="fechaPopup" value="true" scope="request">
	<body leftmargin="0" topmargin="0"
		onload="chamarReload('${sessionScope.retornarTela}');window.close()">
</logic:equal>


<html:form action="/adicionarCriterioCobrancaLinhaAction" 
           name="CriterioCobrancaActionForm"
	       type="gcom.gui.cobranca.CriterioCobrancaActionForm"
           method="post"
	       onsubmit="return validateCriterioCobrancaActionForm(this);">
	<table width="570" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="500" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Adicionar Linha do Critério de Cobrança</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Preencha os campos para inserir uma linha no critério de cobrança:</td>
				</tr>
				<tr>
					<td width="50%"><strong>Perfil do Imóvel:<font color="#FF0000">*</font></strong></td>
					<td width="50%"><html:select multiple="true" property="parImovelPerfil" tabindex="1" style="width: 200px;font-size:11px;">
						<html:option value="">&nbsp;</html:option>
                        <logic:iterate name="colecaoImovelPerfil" id="imovelPerfil" >
							<option value="<bean:write name="imovelPerfil" property="id" />;<bean:write name="imovelPerfil" property="descricao" />" ><bean:write name="imovelPerfil" property="descricao" /></option>
                        </logic:iterate>
			
					</html:select></td>
				</tr>
				<tr>
					<td width="50%" he><strong>Categoria:<font color="#FF0000">*</font></strong></td>
					<td width="50%"><html:select multiple="true" property="parCategoria" tabindex="2" style="width: 200px;font-size:11px;">
						<html:option value="">&nbsp;</html:option>
						<logic:iterate name="colecaoCategoria" id="categoria" >
							<option value="<bean:write name="categoria" property="id" />;<bean:write name="categoria" property="descricao" />" ><bean:write name="categoria" property="descricao" /></option>
                        </logic:iterate>
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Intervalo de Valor do Débito:</strong></td>
					<td><html:text maxlength="17" property="valorDebitoMinimo" size="17"
						tabindex="3" style="text-align: right;" onkeyup="formataValorMonetario(this, 13)"/>a
						<html:text maxlength="17" property="valorDebitoMaximo" size="17"
						tabindex="4" style="text-align: right;" onkeyup="formataValorMonetario(this, 13)"/>
					</td>
				</tr>
				<tr>
					<td><strong>Intervalo de Quantidade de Contas:</strong></td>
					<td><html:text maxlength="4" property="qtdContasMinima" size="5"
						tabindex="5" />a
						<html:text maxlength="4" property="qtdContasMaxima" size="5"
						tabindex="6" />
					</td>
				</tr>

				<tr>
					<td><strong>Valor M&iacute;nimo do D&eacute;bito para Cliente 
                         com D&eacute;bito Autom&aacute;tico:</strong></td>
					<td><html:text maxlength="17" property="vlMinimoDebitoCliente" size="17" tabindex="7" style="text-align: right;" onkeyup="formataValorMonetario(this, 13)"/></td>
				</tr>
				<tr>
					<td><strong>Quantidade M&iacute;nima de Contas para Cliente 
                         com D&eacute;bito Autom&aacute;tico:</strong></td>
					<td><html:text maxlength="4" property="qtdMinContasCliente" size="5" tabindex="8" /></td>
				</tr>
				<tr> 
          		    <td><strong>Valor M&iacute;nimo da Conta do M&ecirc;s:</strong></td>
                    <td><html:text property="vlMinimoContasMes" size="17" maxlength="17" style="text-align: right;" onkeyup="formataValorMonetario(this, 13)"/></td>
                </tr>
                <tr> 
          		    <td><strong>Quantidade Mínima de Contas Parceladas em Atraso:</strong></td>
                    <td><html:text property="quantidadeMinimaParcelasAtraso" size="2" maxlength="2" style="text-align: right;"/></td>
                </tr>
                <tr> 
                   <td>&nbsp;</td>
                   <td><strong><font color="#FF0000">*</font></strong> Campos 
                     obrigat&oacute;rios</td>
               </tr>
               <tr> 
                  <td> 
                  <input name="button" type="button"
						class="bottonRightCol" value="Desfazer" onclick="desfazer();">
				  </td>	
                  <td colspan="3">
                  <div align="right"> 
                   <input name="Button" type="button" class="bottonRightCol" value="Inserir" onclick="validarForm(document.forms[0]);">
                   <input name="Button2" type="button" class="bottonRightCol" value="Fechar" onClick="javascript:window.close();">
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
