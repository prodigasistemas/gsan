<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirGrupoActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
 
<script language="JavaScript" >
 	function validateMovimentarOSSeletivaInspecaoAnormalidadeActionForm(form) {
      return true;
    }
    
    
    function bloqueiaCampos() {
    	var form = document.forms[0];
    	
    	if((form.idsCategoria.value != null && form.idsCategoria.value != '' && form.idsCategoria.value != '-1')
			|| (form.idsImovelPerfil.value != null && form.idsImovelPerfil.value != '' && form.idsImovelPerfil.value != '-1')
			|| (form.idsLigacaoAguaSituacao.value != null && form.idsLigacaoAguaSituacao.value != '' && form.idsLigacaoAguaSituacao.value != '-1')
			|| (form.valorMinimo.value != null && form.valorMinimo.value != '')
			|| (form.valorMaximo.value != null && form.valorMaximo.value != '')) {
			
			document.getElementById('matriculasImoveis[0]').value = "";
			document.getElementById('matriculasImoveis[0]').style.color = "#000000";
			document.getElementById('matriculasImoveis[0]').readOnly = true;
			document.getElementById('matriculasImoveis[0]').style.backgroundColor = '#EFEFEF';
			
			document.getElementById('matriculasImoveis[1]').value = "";
			document.getElementById('matriculasImoveis[1]').style.color = "#000000";
			document.getElementById('matriculasImoveis[1]').readOnly = true;
			document.getElementById('matriculasImoveis[1]').style.backgroundColor = '#EFEFEF';
			
			document.getElementById('matriculasImoveis[2]').value = "";
			document.getElementById('matriculasImoveis[2]').style.color = "#000000";
			document.getElementById('matriculasImoveis[2]').readOnly = true;
			document.getElementById('matriculasImoveis[2]').style.backgroundColor = '#EFEFEF';
			
			document.getElementById('matriculasImoveis[3]').value = "";
			document.getElementById('matriculasImoveis[3]').style.color = "#000000";
			document.getElementById('matriculasImoveis[3]').readOnly = true;
			document.getElementById('matriculasImoveis[3]').style.backgroundColor = '#EFEFEF';
			
			document.getElementById('matriculasImoveis[4]').value = "";
			document.getElementById('matriculasImoveis[4]').style.color = "#000000";
			document.getElementById('matriculasImoveis[4]').readOnly = true;
			document.getElementById('matriculasImoveis[4]').style.backgroundColor = '#EFEFEF';
			
			document.getElementById('matriculasImoveis[5]').value = "";
			document.getElementById('matriculasImoveis[5]').style.color = "#000000";
			document.getElementById('matriculasImoveis[5]').readOnly = true;
			document.getElementById('matriculasImoveis[5]').style.backgroundColor = '#EFEFEF';
			
			document.getElementById('matriculasImoveis[6]').value = "";
			document.getElementById('matriculasImoveis[6]').style.color = "#000000";
			document.getElementById('matriculasImoveis[6]').readOnly = true;
			document.getElementById('matriculasImoveis[6]').style.backgroundColor = '#EFEFEF';
			
			document.getElementById('matriculasImoveis[7]').value = "";
			document.getElementById('matriculasImoveis[7]').style.color = "#000000";
			document.getElementById('matriculasImoveis[7]').readOnly = true;
			document.getElementById('matriculasImoveis[7]').style.backgroundColor = '#EFEFEF';
			
			document.getElementById('matriculasImoveis[8]').value = "";
			document.getElementById('matriculasImoveis[8]').style.color = "#000000";
			document.getElementById('matriculasImoveis[8]').readOnly = true;
			document.getElementById('matriculasImoveis[8]').style.backgroundColor = '#EFEFEF';
			
			document.getElementById('matriculasImoveis[9]').value = "";
			document.getElementById('matriculasImoveis[9]').style.color = "#000000";
			document.getElementById('matriculasImoveis[9]').readOnly = true;
			document.getElementById('matriculasImoveis[9]').style.backgroundColor = '#EFEFEF';
			
			
		} else if ((document.getElementById('matriculasImoveis[0]').value != null && document.getElementById('matriculasImoveis[0]').value != '')
				|| (document.getElementById('matriculasImoveis[1]').value != null && document.getElementById('matriculasImoveis[1]').value != '')
				|| (document.getElementById('matriculasImoveis[2]').value != null && document.getElementById('matriculasImoveis[2]').value != '')
				|| (document.getElementById('matriculasImoveis[3]').value != null && document.getElementById('matriculasImoveis[3]').value != '')
				|| (document.getElementById('matriculasImoveis[4]').value != null && document.getElementById('matriculasImoveis[4]').value != '')
				|| (document.getElementById('matriculasImoveis[5]').value != null && document.getElementById('matriculasImoveis[5]').value != '')
				|| (document.getElementById('matriculasImoveis[6]').value != null && document.getElementById('matriculasImoveis[6]').value != '')
				|| (document.getElementById('matriculasImoveis[7]').value != null && document.getElementById('matriculasImoveis[7]').value != '')
				|| (document.getElementById('matriculasImoveis[8]').value != null && document.getElementById('matriculasImoveis[8]').value != '')
				|| (document.getElementById('matriculasImoveis[9]').value != null && document.getElementById('matriculasImoveis[9]').value != '')){
						

					
		}
		
		if ((form.idsCategoria.value == null || form.idsCategoria.value == '' || form.idsCategoria.value == '-1')
			&& (form.idsImovelPerfil.value == null || form.idsImovelPerfil.value == '' || form.idsImovelPerfil.value == '-1')
			&& (form.idsLigacaoAguaSituacao.value == null || form.idsLigacaoAguaSituacao.value == '' || form.idsLigacaoAguaSituacao.value == '-1')
			&& (form.valorMinimo.value == null || form.valorMinimo.value == '')
			&& (form.valorMaximo.value == null || form.valorMaximo.value == '')
			&& (document.getElementById('matriculasImoveis[0]').value == null || document.getElementById('matriculasImoveis[0]').value == '')
			&& (document.getElementById('matriculasImoveis[1]').value == null || document.getElementById('matriculasImoveis[1]').value == '')
			&& (document.getElementById('matriculasImoveis[2]').value == null || document.getElementById('matriculasImoveis[2]').value == '')
			&& (document.getElementById('matriculasImoveis[3]').value == null || document.getElementById('matriculasImoveis[3]').value == '')
			&& (document.getElementById('matriculasImoveis[4]').value == null || document.getElementById('matriculasImoveis[4]').value == '')
			&& (document.getElementById('matriculasImoveis[5]').value == null || document.getElementById('matriculasImoveis[5]').value == '')
			&& (document.getElementById('matriculasImoveis[6]').value == null || document.getElementById('matriculasImoveis[6]').value == '')
			&& (document.getElementById('matriculasImoveis[7]').value == null || document.getElementById('matriculasImoveis[7]').value == '')
			&& (document.getElementById('matriculasImoveis[8]').value == null || document.getElementById('matriculasImoveis[8]').value == '')
			&& (document.getElementById('matriculasImoveis[9]').value == null || document.getElementById('matriculasImoveis[9]').value == '')) {
			
			
			document.getElementById('matriculasImoveis[0]').style.color = "#000000";
			document.getElementById('matriculasImoveis[0]').readOnly = false;
			document.getElementById('matriculasImoveis[0]').style.backgroundColor = '';
			
			document.getElementById('matriculasImoveis[1]').style.color = "#000000";
			document.getElementById('matriculasImoveis[1]').readOnly = false;
			document.getElementById('matriculasImoveis[1]').style.backgroundColor = '';
			
			document.getElementById('matriculasImoveis[2]').style.color = "#000000";
			document.getElementById('matriculasImoveis[2]').readOnly = false;
			document.getElementById('matriculasImoveis[2]').style.backgroundColor = '';
			
			document.getElementById('matriculasImoveis[3]').style.color = "#000000";
			document.getElementById('matriculasImoveis[3]').readOnly = false;
			document.getElementById('matriculasImoveis[3]').style.backgroundColor = '';
			
			document.getElementById('matriculasImoveis[4]').style.color = "#000000";
			document.getElementById('matriculasImoveis[4]').readOnly = false;
			document.getElementById('matriculasImoveis[4]').style.backgroundColor = '';
			
			document.getElementById('matriculasImoveis[5]').style.color = "#000000";
			document.getElementById('matriculasImoveis[5]').readOnly = false;
			document.getElementById('matriculasImoveis[5]').style.backgroundColor = '';
			
			document.getElementById('matriculasImoveis[6]').style.color = "#000000";
			document.getElementById('matriculasImoveis[6]').readOnly = false;
			document.getElementById('matriculasImoveis[6]').style.backgroundColor = '';
			
			document.getElementById('matriculasImoveis[7]').style.color = "#000000";
			document.getElementById('matriculasImoveis[7]').readOnly = false;
			document.getElementById('matriculasImoveis[7]').style.backgroundColor = '';
			
			document.getElementById('matriculasImoveis[8]').style.color = "#000000";
			document.getElementById('matriculasImoveis[8]').readOnly = false;
			document.getElementById('matriculasImoveis[8]').style.backgroundColor = '';
			
			document.getElementById('matriculasImoveis[9]').style.color = "#000000";
			document.getElementById('matriculasImoveis[9]').readOnly = false;
			document.getElementById('matriculasImoveis[9]').style.backgroundColor = '';
		}
		
    }
</script>

</head>

<body>

<html:form
    action="/movimentarOSSeletivaInspecaoAnormalidadeWizardAction"
    method="post"
    onsubmit="return validateMovimentarOSSeletivaInspecaoAnormalidadeActionForm(this);"
>

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=2"/>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="123" valign="top" class="leftcoltext">
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
          <td class="parabg">Movimentar Ordem de Serviço - Gerar OS</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      
      <p>&nbsp;</p>
      
      <table border="0" width="100%">
        <tr>
          <td colspan="2">Para gerar OS para comandos de inspeção de anormalidade, informe os dados abaixo:</td>
        </tr>

		<tr>
          <td colspan="2">
          		<table border="0" width="100%">
	        		<tr>
					  <td width="45%"><strong>Comando para Inspeção de Anormalidade:<font color="#ff0000"></font></strong></td>
			          <td width="55%">
						<html:text maxlength="40" property="idComando" size="20" readonly="true"
									style="background-color:#EFEFEF; border:0; font-color: #000000" />
			          </td>        		
	        		</tr>
	        	</table>
          </td>
        </tr>
        
        <tr>
			<td colspan="2" width="100%">
			<hr>
			</td>
		</tr>
		
		<tr>
			<td><strong>Tipo de Serviço:<font color="#ff0000">*</font></strong></td>
			<td><html:select property="idTipoServico" tabindex="3" >
				<logic:notEmpty name="colecaoServicoTipo">
					<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
					<html:options collection="colecaoServicoTipo"
						labelProperty="descricao" property="id" />
				</logic:notEmpty>
			</html:select></td>
		</tr>
        <tr>
			<td colspan="2" width="100%">
			<hr>
			</td>
		</tr>
        <tr>
          <td width="15%"><strong>Matrícula do Imóvel:<font color="#ff0000"></font></strong></td>
          <td width="85%">
            <table border="0">
            	<tr>
          			<td width="100%">
						<html:text maxlength="10" property="matriculasImoveis[0]" styleId="matriculasImoveis[0]" size="10" onchange="bloqueiaCampos();" onkeypress="return isCampoNumerico(event);"/>
&nbsp;
						<html:text maxlength="10" property="matriculasImoveis[1]" styleId="matriculasImoveis[1]" size="10" onchange="bloqueiaCampos();" onkeypress="return isCampoNumerico(event);"/>
&nbsp;
						<html:text maxlength="10" property="matriculasImoveis[2]" styleId="matriculasImoveis[2]" size="10"  onchange="bloqueiaCampos();" onkeypress="return isCampoNumerico(event);"/>
&nbsp;
						<html:text maxlength="10" property="matriculasImoveis[3]" styleId="matriculasImoveis[3]" size="10" onchange="bloqueiaCampos();" onkeypress="return isCampoNumerico(event);"/>
&nbsp;
						<html:text maxlength="10" property="matriculasImoveis[4]" styleId="matriculasImoveis[4]" size="10" onchange="bloqueiaCampos();" onkeypress="return isCampoNumerico(event);"/>
					</td>
				</tr>
            	<tr>
          			<td width="100%">
						<html:text maxlength="10" property="matriculasImoveis[5]" styleId="matriculasImoveis[5]" size="10" onchange="bloqueiaCampos();" onkeypress="return isCampoNumerico(event);"/>
&nbsp;
						<html:text maxlength="10" property="matriculasImoveis[6]" styleId="matriculasImoveis[6]" size="10" onchange="bloqueiaCampos();" onkeypress="return isCampoNumerico(event);"/>
&nbsp;
						<html:text maxlength="10" property="matriculasImoveis[7]" styleId="matriculasImoveis[7]" size="10" onchange="bloqueiaCampos();" onkeypress="return isCampoNumerico(event);"/>
&nbsp;
						<html:text maxlength="10" property="matriculasImoveis[8]" styleId="matriculasImoveis[8]" size="10" onchange="bloqueiaCampos();" onkeypress="return isCampoNumerico(event);"/>
&nbsp;
						<html:text maxlength="10" property="matriculasImoveis[9]" styleId="matriculasImoveis[9]" size="10" onchange="bloqueiaCampos();" onkeypress="return isCampoNumerico(event);"/>
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
