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

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="CategoriaActionForm"/>

<script language="JavaScript">

</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form action="/inserirMenssagemContaAction.do"
	name="InserirMenssagemContaActionForm"
	type="gcom.gui.faturamento.InserirMenssagemContaActionForm" method="post"
	onsubmit="return validateCategoriaActionForm(this);">

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
		        <tr><td></td></tr>
	      	</table>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	        	<tr>
		          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
		          <td class="parabg">Inserir Menssagem da Conta</td>
		          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
	    	    </tr>
		    </table>
			<p>&nbsp;</p>
			
            <table border="0" width="100%">
			  <tr> 
                <td colspan="2">Para adicionar a mensagem da conta, informe os 
                  dados abaixo:</td>

              </tr>
              <tr> 
                <td><strong>Referência do Faturamento:<font color="#ff0000">*</font></strong></td>
                <td align="right" width="413"><div align="left"> 
                    <html:text property="referenciaFaturamento" size="7" maxlength="7" onclick="mascaraAnoMes(this, event);" /> (mm/aaaa)</div></td>
              </tr>
              <tr> 
                <td><strong>Mensagem da Conta:<font color="#ff0000">*</font></strong></td>

                <td align="right"><div align="left"> 
					<html:textarea property="referenciaFaturamento" cols="45" rows="6"/>
                  </div></td>
              </tr>
              <tr> 
                <td><strong>Grupo de Faturamento:</strong></td>
                <td align="right"><div align="left"> <strong> 
                    <html:select
						property="grupoFaturamento" style="width: 230px;">
						<logic:present name="colecaoFaturamentoGrupo">
							<html:option value="">&nbsp;</html:option>
							<html:options collection="colecaoFaturamentoGrupo"
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select>
                    </strong></div></td>
              </tr>
              <tr> 
                <td><strong>Gerência Regional:</strong></td>
                <td align="right"><div align="left"> <strong> 
                    <html:select
						property="gerenciaRegional" style="width: 230px;">
						<logic:present name="colecaoGerenciaRegional">
							<html:option value="">&nbsp;</html:option>
							<html:options collection="colecaoGerenciaRegional"
								labelProperty="nome" property="id" />
						</logic:present>
					</html:select>
                    </strong></div></td>

              </tr>
              <tr> 
                <td><strong>Localidade:</strong></td>
                <td align="right"><div align="left"> 
                	<html:text property="localidade" size="4" maxlength="4"/>
                    <input name="textfield22232" size="4" maxlength="4" type="text">
                    <img src="imagens/pesquisa.gif" style="" onclick="" height="21" width="23"> 
                    <html:text property="localidadeDescricao" size="30" maxlength="30" readonly="true" style="border: 0pt none ; background-color: rgb(239, 239, 239);"/>
                    <img src="imagens/limparcampo.gif" height="21" width="23"></div></td>
              </tr>
              <tr> 
                <td><strong>Setor Comercial:</strong></td>

                <td align="right"><div align="left"> 
                    <html:text property="setorComercial" size="4" maxlength="4"/>
                    <img src="imagens/pesquisa.gif" style="" onclick="" height="21" width="23">
                    <html:text property="setorComercialDescricao" size="30" maxlength="30" readonly="true" style="border: 0pt none ; background-color: rgb(239, 239, 239);"/> 
                    <img src="imagens/limparcampo.gif" height="21" width="23"></div></td>
              </tr>
              <tr> 
                <td>&nbsp;</td>
                <td align="right">&nbsp;</td>
              </tr>

              <tr> 
                <td><strong> <font color="#ff0000"></font></strong></td>
                <td align="right"> <div align="left"><strong><font color="#ff0000">*</font></strong> 
                    Campos obrigatórios</div></td>
              </tr>
              <tr> 
                <td><strong> <font color="#ff0000"> 
                  <input name="Submit22" class="bottonRightCol" value="Desfazer" type="submit">

                  <input name="Submit23" class="bottonRightCol" value="Cancelar" type="submit">
                  </font></strong></td>
                <td align="right"> <input name="Submit2" class="bottonRightCol" value="Inserir" type="submit"> 
                </td>
              </tr>
            </table>
			<p>&nbsp;</p>
			<!-- Fim do Corpo - Roberta Costa -->
		</td>
	</tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
