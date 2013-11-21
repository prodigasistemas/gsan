<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AtualizarLeituraAnormalidadeCelularAction" />
<script language="JavaScript">
<!--

	function validarForm(){
	   var form = document.forms[0];
	   var mensagem = '';
	   
	   if(form.arquivo.value == '') {
		  mensagem = 'Informe o Arquivo Texto';
		  alert(mensagem);
	   }else{
	   	 form.submit();		
	   	 //submitForm(form);
	   }
	   
	}


-->
</script>

</head>

<body leftmargin="5" topmargin="5">
<form action="/gsan/atualizarLeituraAnormalidadeCelularAction.do"
	method="post" enctype="multipart/form-data">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
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
			<td width="615" valign="top" class="centercoltext">
			<table height="100%">
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
                <td width="11"><img border="0" src="imagens/parahead_left.gif"/></td>
                <td class="parabg">Atualizar Leituras e Anormalidades</td>

                <td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif"/></td>
              </tr>
            </table>
            <!--Fim Tabela Reference a Páginação da Tela de Processo-->
            <p>&nbsp;</p>
            <table width="100%" border="0" >
               <tr> 
                  <td colspan="3">Para atualizar as leituras e anormalidades
                    do celular, informe os dados abaixo:</td>
                    <tr> 
                  <td width="27%" class="style1">Nome do Arquivo:<strong><font color="#FF0000">*</font></strong></td>
                  <td width="73%" colspan="2" class="style1">
                  <input type="file" style="textbox" name="arquivo"
					size="50" tabindex="1"/> 
                  </td>
                </tr>
                <tr> 
                  <td class="style1">&nbsp;</td>
                  <td colspan="2" class="style1">&nbsp;</td>

                </tr>
                <tr> 
                  <td class="style1"><p>&nbsp;</p></td>
                  <td colspan="2" class="style1">
                   <table>
                      <tr> 
                        <td width="500" align="right">
                        	<div align="left">
                        		<strong>
                        			<font color="#FF0000">*</font>
                        		</strong> 
                            	Campos obrigat&oacute;rios
                            </div>
                        </td>
                        <td>&nbsp;</td>

                      </tr>
             	   </table>
                 </td>
                </tr>
                <tr> 
                  <td class="style1"><p>&nbsp;</p></td>
                  <td colspan="2" class="style1"> 
                   <table>
                      <tr> 
                        <td width="500" align="right"> 
                        	<gsan:controleAcessoBotao name="Button" onclick="javascript:validarForm();" url="atualizarLeituraAnormalidadeCelularAction.do" value="Atualizar" tabindex="2"/>
                        </td>
                        <td>&nbsp;</td>

                      </tr>
                   </table>
                  </td>
               </tr>
            </table>
          	<p class="style1">&nbsp;</p>
          </td>
		</tr>
		<!-- Rodapé -->
		<%@ include file="/jsp/util/rodape.jsp"%>
	</table>
	
</form>
</body>
</html:html>
