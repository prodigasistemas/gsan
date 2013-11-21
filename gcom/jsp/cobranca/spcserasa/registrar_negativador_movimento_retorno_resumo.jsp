<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page
	import="gcom.util.Pagina,gcom.util.ConstantesSistema,java.util.Collection"%>

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
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

</head>

<body leftmargin="5" topmargin="5">

<form action="/gsan/exibirRegistrarNegativadorMovimentoRetornoAction.do"
	method="post" enctype="multipart/form-data"><%@ include
	file="/jsp/util/cabecalho.jsp"%> <%@ include file="/jsp/util/menu.jsp"%>


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

			<%@ include file="/jsp/util/mensagens.jsp"%>
		
			</div>
			</td>
			<td width="600" align="top" bgcolor="#003399" class="centercoltext">
		
			     <!--Início Tabela Reference a Páginação da Tela de Processo-->
          
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="11"><img border="0" src="imagens/parahead_left.gif"/></td>
                <td class="parabg">Resumo do Movimento de Retorno do Negativador</td>
                <td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif"/></td>
              </tr>
            </table>
            <!--Fim Tabela Reference a Páginação da Tela de Processo-->
            <table width="100%" border="0">
              <tr>
                <td colspan="3">&nbsp;</td>
              </tr>
              <tr>
                <td colspan="2"><strong>Negativador:</strong></td>
                <td><strong><b><span class="style4">              
                 <input  type="text" 
                 		 name="nomeNegativador" 
                 		 value="<bean:write name="nomeNegativador" scope="session" />" 
                 		 disabled="disabled"
                 		 readonly="readonly" 
                 		 size="50" 
                 		 maxlength="50" >
              
                </span></b></strong></td>
              </tr>
              <tr>
                <td colspan="2"><strong>Data do Procesamento: </strong></td>
                <td><strong><b><span class="style4">
                <input  type="text" 
                		name="dataProcessamento" 
                		value="<bean:write name="dataProcessamento" scope="session" />" 
                		disabled="disabled"
                		readonly="readonly" 
                		size="10" 
                		maxlength="10">
                 </span></b></strong></td>
              </tr>
              <tr>
                <td colspan="2"><strong>Hora do Procesamento: </strong></td>
                <td><strong><b><span class="style4">                
                  <input type="text" 
                  		 name="horaProcessamento" 
                  		 value="<bean:write name="horaProcessamento" scope="session" />" 
                  		 disabled="disabled"
                  		 readonly="readonly" 
                  		 size="8" 
                  		 maxlength="8">
                </span></b></strong></td>
              </tr>
              <tr>
                <td colspan="2"><strong>N&uacute;mero Seq&uuml;encial do Arquivo: </strong></td>
                <td><strong><b><span class="style4">               
                   <input type="text" 
                   		  name="numeroSequencialArquivo" 
                   		  value="<bean:write name="numeroSequencialArquivo" scope="session" />" 
                   		  readonly="readonly" 
                   		  disabled="disabled"
                   		  size="10" 
                   		  maxlength="10">
                </span></b></strong></td>
              </tr>
              <tr>
                <td colspan="2"><strong>Total de Registros do Arquivo: </strong></td>
                <td><strong><b><span class="style4">    

                 <input type="text" 
                 		name="numeroRegistros" 
                 		value="<bean:write name="numeroRegistros" scope="session" />" 
                 		readonly="readonly" 
                 		disabled="disabled"
                 		size="10" 
                 		maxlength="10">
                </span></b></strong></td>
              </tr>
              <tr> 
                <td height="0" colspan="3">&nbsp;</td>
              </tr>
              <tr> 
                <td colspan="2">&nbsp;</td>
                <td width="368" align="right">&nbsp;</td>
              </tr>
              <tr>
              	<td>
              	&nbsp;
              	</td>
              </tr>
              <tr>
              	<td>
              	&nbsp;
              	</td>
              </tr>
              <tr>
              	<td>
              	&nbsp;
              	</td>
              </tr>
              <tr>
              	<td>
              	&nbsp;
              	</td>
              </tr>
              <tr>
              	<td>
              	&nbsp;
              	</td>
              </tr>
              <tr>
              	<td>
              	&nbsp;
              	</td>
              </tr>
              <tr>
              	<td>
              	&nbsp;
              	</td>
              </tr>
              
              
              <tr> 
                <td width="67"> 
                	<font color="#FF0000"> 
                		<input type="submit" 
                			   name="Submit232" 
                			   class="bottonRightCol" 
                			   disabled="disabled"
                			   readonly="readonly"
                			   value="Imprimir">                
                
                  <!--      <input name="Submit22"
						class="bottonRightCol" value="Imprimir" type="button"
						onclick="window.location.href='/gsan/registrarNegativadorMovimentoRetornoAction.do?pdf=sim'">  --> 
                
                </font></td>
                <td width="172"><font color="#FF0000">
                  <input type="submit" name="Submit2322" class="bottonRightCol" value="Registrar Outro Movimento" onclick="window.location.href='<html:rewrite page="/exibirRegistrarNegativadorMovimentoRetornoAction.do"/>'">
                </font></td>
                <td align="right">
					<input name="Button" 
						   type="button" 
						   class="bottonRightCol" 	
						   value="Encerrar" 
						   align="left"
						   onclick="window.location.href='/gsan/telaPrincipal.do'">
				</td>
              </tr>
            </table>
			
			</td>
			</tr>
			
			
	</table>
    <jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=registrarNegativadorMovimentoRetornoAction.do"/>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</form>
</html:html>
