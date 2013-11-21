<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript formName="MelhoriasGcomActionForm"/>

<script language="JavaScript">
function limpaCampos(){
	var form = document.forms[0];
	var i=0;
	for(;i<7;i++){
	 form.idAssunto[i].checked = false;
	}
	form.unidadeLotacao.value = "";
	form.telefone.value = "";
	form.email.value = "";  
	form.matirculaImovel.value = "";   
	form.codigoCliente.value = "";
	form.numeroRA.value = "";
	form.numeroOS.value = "";
	form.detalhamento.value = "";
}

</script>


</head>

<body leftmargin="0" topmargin="0" onload="limpaCampos();">


<html:form action="/informarMelhoriasGcomAction" name="MelhoriasGcomActionForm"
	type="gcom.gui.atendimentopublico.MelhoriasGcomActionForm" method="post" onsubmit="return validateMelhoriasGcomActionForm(this);">


<%@ include file="/jsp/util/cabecalho.jsp"%>
<logic:notPresent name="indicadorSenhaPendente" scope="session">
	<%@ include file="/jsp/util/menu.jsp" %>
</logic:notPresent>

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
      	</div>
      	</td>

	<td width="600" valign="top" class="centercoltext">

        <table height="100%">
        <tr>
          <td></td>
        </tr>
      	</table>

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Entre em Contato</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
       <tr>
      	 <td colspan="2">Nesta tela, o usuário pode sugerir melhorias no GSAN, informar erros identificados ou tirar dúvidas:</td>
       </tr>
      
	
	   <tr>
		<td colspan="2">
		
		   <table width="100%" align="center" bgcolor="#90c7fc" border="0">
				<tr>
					<td align="center"><strong> Informe o Assunto</strong></td>
				</tr>
				<tr bgcolor="#cbe5fe">
					<td width="100%" align="center">
						
					 <table width="100%" border="0">
					  <tr>
                        <td width="29%" height="10">
                           <html:radio property="idAssunto" tabindex="1"
						    value="1" /> <strong>Cadastro</strong>
                        </td>
                        <td width="29%">
                         <html:radio property="idAssunto" tabindex="2"
						    value="2"/> <strong>Cobran&ccedil;a</strong>
                        </td>
                        <td width="29%">
                         <html:radio property="idAssunto" tabindex="3"
						    value="3"/> <strong>Gerencial</strong>
                        </td>
                      </tr>
                      <tr>
                        <td height="10">
                          <html:radio property="idAssunto" tabindex="4"
						    value="4"/><strong>Micromedi&ccedil;&atilde;o</strong>
					    </td>
                        <td><html:radio property="idAssunto" tabindex="5"
						    value="5"/> <strong>Arrecada&ccedil;&atilde;o</strong>
						</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td height="10"><html:radio property="idAssunto" tabindex="6"
						    value="6"/><strong>Faturamento</strong>
						</td>
                        <td><html:radio property="idAssunto" tabindex="7"
						    value="7"/> <strong>Atendimento</strong>
						</td>
                        <td>&nbsp;</td>
                      </tr>
                     </table>
				  </td>
		      </tr>
		   </table>
		    <p>&nbsp;</p>
		   <table width="100%" align="center" bgcolor="#90c7fc" border="0">
			  <tr>
				<td align="center"><strong>Informe os Dados do Usu&aacute;rio</strong></td>
			  </tr>
			  <tr bgcolor="#cbe5fe">
				<td width="100%" align="center">
					<table width="100%" border="0">
					  <tr>
					     <td width="22%"><strong>Login: </strong></td>
	                     <td width="78%">
	                       <html:text property="loginUsuario" tabindex="8" style="background-color:#EFEFEF; border:0" 
						     size="14" maxlength="12" readonly="true"/>
	                     </td>
	                  </tr>
	                  <tr>
					     <td width="22%"><strong>Nome: </strong></td>
	                     <td width="78%">
	                       <html:text property="nomeUsuario" tabindex="9" style="background-color:#EFEFEF; border:0" 
						    size="50" maxlength="40" readonly="true"/>
	                     </td>
	                  </tr>
	                  <tr>
					     <td width="22%"><strong>Unidade de Lotação: <font color="#FF0000">*</font></strong></td>
	                     <td width="78%">
	                       <html:text property="unidadeLotacao" tabindex="10"  
						     size="50" maxlength="20"/>
	                     </td>
	                  </tr>
	                  <tr>
					     <td width="22%"><strong>Telefone: <font color="#FF0000">*</font></strong></td>
	                     <td width="78%">
	                       <html:text property="telefone" tabindex="11"
						    size="20" maxlength="20" />
	                     </td>
	                  </tr>
	                  <tr>
					     <td width="22%"><strong>Email: <font color="#FF0000">*</font></strong></td>
	                     <td width="78%">
	                       <html:text property="email" tabindex="12"
						     size="40" maxlength="40" style="text-transform: none;"/>
	                     </td>
	                  </tr>
	                  
	                </table>
	            </td>
	           </tr>
	       </table>
	             <p>&nbsp;</p>
	       <table>
	        <tr>
	          <td>É importante o usuário detalhar o que deseja comunicar, no caso de erros identificados, ao informar dados como matrícula do imóvel ou o código do cliente, facilita o processo identificação e correção do problema.
	          </td>
	        </tr>
	        
	       </table>
	       <table width="100%" align="center" bgcolor="#90c7fc" border="0">
			  <tr>
				<td align="center"><strong>Informe os Dados Específicos</strong></td>
			  </tr>
			  <tr bgcolor="#cbe5fe">
				<td width="100%" align="center">
					<table width="100%" border="0">
					  <tr>
					     <td width="22%"><strong>Matrícula do Imóvel: </strong></td>
	                     <td width="78%">
	                       <html:text property="matirculaImovel" tabindex="13"
						     size="15" maxlength="15"/>
	                     </td>
	                  </tr>
	                  <tr>
					     <td width="22%"><strong>Código do Cliente: </strong></td>
	                     <td width="78%">
	                       <html:text property="codigoCliente" tabindex="14"
						    size="15" maxlength="15"/>
	                     </td>
	                  </tr>
	                  <tr>
					     <td width="22%"><strong>Número da RA: </strong></td>
	                     <td width="78%">
	                       <html:text property="numeroRA" tabindex="15"  
						     size="15" maxlength="15"/>
	                     </td>
	                  </tr>
	                  <tr>
					     <td width="22%"><strong>Número da OS: </strong></td>
	                     <td width="78%">
	                       <html:text property="numeroOS" tabindex="16"
						    size="15" maxlength="15" />
	                     </td>
	                  </tr>
	                  <tr>
					     <td width="22%"><strong>Detalhamento: <font color="#FF0000">*</font></strong></td>
	                     <td width="78%">
	                       <html:textarea property="detalhamento" cols="50" rows="10"/>
	                     </td>
	                  </tr>
	                  
	                </table>
	            </td>
	           </tr>
	       </table>
	     </td>
	   </tr>             
       <tr>
      	<td colspan="2" height="10"></td>
       </tr>
       <tr>
		<td colspan="2">
		  <table width="100%">
		    <tr>
				
				<td colspan="2"><div align="left"><strong><font color="#FF0000">*</font></strong>
				Campos obrigat&oacute;rios</div>
				</td>
			</tr>
			<tr>
      	      <td colspan="2" height="10"></td>
            </tr>
			<tr>
			 <td>				
				<input name="Submit22"
				 class="bottonRightCol" value="Desfazer" type="button"
				 onclick="javascript:limpaCampos();">
				 <logic:present name="indicadorSenhaPendente" scope="session">
				 		<input name="Submit24" class="bottonRightCol" value="Voltar"
						type="button"
						onclick="window.history.go(-1)"> 	
				 </logic:present>
				 <logic:notPresent name="indicadorSenhaPendente" scope="session">
						<input name="Submit23" class="bottonRightCol" value="Cancelar"
						type="button"
						onclick="window.location.href='/gsan/telaPrincipal.do'"> 
				 </logic:notPresent>
			</td>
			<td align="right">	
					<html:submit styleClass="bottonRightCol" value="Enviar" />
			</td>		  
					<!--
					<input name="botao" class="bottonRightCol"
						value="Atualizar" onclick="javascript:validarForm(document.forms[0])" type="button">-->
			</tr>
		  </table>
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

