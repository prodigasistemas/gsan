<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AtualizarParcelamentoPerfilActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">


function validateAtualizarNegativadorExclusaoMotivoActionForm() {                                                                   
     var form = document.forms[0];       
         
       	if(validateRequired(form)){
    		submeterFormPadrao(form);
		}
       
   } 



 function required () {   
     this.aa = new Array("descricaoExclusaoMotivo", "Informe a descrição do motivo de exclusão.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("idCobrancaDebitoSituacao", "Informe a situação de cobrança do débito.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("indicadorUso", "Informe o indicador de uso.", new Function ("varName", " return this[varName];"));
 } 




  

</script>
</head>
<body leftmargin="0" topmargin="0"
	onload="setarFoco('${requestScope.nomeCampo}');submit();">
<html:form action="/atualizarNegativadorExclusaoMotivoAction"
	name="AtualizarNegativadorExclusaoMotivoActionForm"
	type="gcom.gui.cobranca.spcserasa.AtualizarNegativadorExclusaoMotivoActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<html:hidden property="time"/>


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
			<td valign="top" class="centercoltext">
			<table>
				<tr>
					<td></td>
				</tr>

			</table>
		
		
		
		<!--Início Tabela Reference a Páginação da Tela de Processo-->
            <table>
              <tr>
                <td></td>
              </tr>
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" /></td>
                <td class="parabg">Atualizar Motivo de Exclusao do Negativador</td>
                <td width="11" valign="top"><img src="<bean:message key="caminho.imagens"/>parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
              </tr>
            </table>
            <!--Fim Tabela Reference a Páginação da Tela de Processo-->
<p>&nbsp;</p>
            <table width="100%" border="0">
              <tr> 
                <td colspan="3">Para atualizar o motivo de exclusao do negativador, informe os
                  dados abaixo:</td>
              </tr>
			  <tr>
                <td><strong>Negativador:</strong></td>
                <td><html:text property="negativadorCliente" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0"/> </td>                 
              </tr>
			  
              <tr>
              <tr> 
                <td><strong>Código do Motivo:</strong></td>
                <td><html:text property="codigoMotivo" size="4" maxlength="3" readonly="true" style="background-color:#EFEFEF; border:0"/>
                </td>
              </tr>
              <tr>
              <tr>
                <td><strong>Motivo da Exclusão:<font color="#FF0000">*</font></strong></td>
                <td colspan="2" align="right"><div align="left"><strong>
                    <html:text property="descricaoExclusaoMotivo" size="45" maxlength="40"/>
                </strong></div></td>
              </tr>
              
              <tr>
					<td><strong>Situação de Cobrança do Débito Correspondente:<font color="#FF0000">*</font></strong></td>
					<td>
					<logic:present name="colecaoCobrancaDebitoSituacao">  
                   	   <html:select property="idCobrancaDebitoSituacao" tabindex="7">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:present name="colecaoCobrancaDebitoSituacao">
								<html:options collection="colecaoCobrancaDebitoSituacao" labelProperty="descricao" property="id"/>
							</logic:present>
						</html:select>
                		</logic:present></td>
				</tr>
              
              
              
              <tr>
                <td><strong>Indicador de Uso:<font color="#FF0000">*</font></strong></td>
                <td colspan="2" align="right"><div align="left">
                   <html:radio property="indicadorUso" tabindex="2" value="1"/>
                 	<strong>Ativo
                   <html:radio property="indicadorUso" tabindex="2" value="2"/>
					Inativo</strong> </div></td>
              </tr>
              <tr> 
                <td><strong> <font color="#FF0000"></font></strong></td>
                <td colspan="2" align="right"> <div align="left"><strong><font color="#FF0000">*</font></strong> 
                    Campos obrigat&oacute;rios</div></td>
              </tr>
              <tr> 
                 <td><strong> <font color="#FF0000">
                 <logic:present scope="session" name="manter">
						<input class="bottonRightCol" value="Voltar" type="button"
							onclick="javascript:history.back();">
					</logic:present>
					<logic:notPresent scope="session" name="manter">
						<input class="bottonRightCol" value="Voltar" type="button"
							onclick="window.location.href='/gsan/exibirFiltrarNegativadorExclusaoMotivoAction.do';">
					</logic:notPresent>
					<input class="bottonRightCol" value="Desfazer" type="button"
						onclick="window.location.href='/gsan/exibirAtualizarNegativadorExclusaoMotivoAction.do';">						
                </font></strong></td>
                <td width="373" align="right"><div align="left"><strong><font color="#FF0000">
                 <input class="bottonRightCol" value="Cancelar" type="button"
						onclick="window.location.href='/gsan/telaPrincipal.do'"> </td>
                </font></strong> </div></td>
					<td align="right">					  
						<input type="Button" name="botaoAtualizar" class="bottonRightCol" value="Atualizar" onclick="javascript:validateAtualizarNegativadorExclusaoMotivoActionForm();">					  
						
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
