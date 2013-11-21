<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.Util" %>
<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>



<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css"> 
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="FiltrarNegativadorResultadoSimulacaoActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validaForm() { 
		var form = document.forms[0];
		var idComando = form.idComando.value;
		if (idComando == "") {
			alert("Pesquise o Comando.");
			return false;
		} else {
			return true;
		}
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    	var form = document.forms[0];
    	
    	form.idComando.value = codigoRegistro;
      	form.descricaoComando.value = descricaoRegistro;  
      	        	
        form.action = 'exibirFiltrarNegativadorResultadoSimulacaoAction.do';
      	form.submit(); 
    }
	
	
	function pesquisarComandoNegativacao(){
	  var formulario = document.forms[0]; 
	  abrirPopup ('exibirPesquisarComandoNegativacaoAction.do?menu=ok', 400, 750);
    }   
	

</script>
</head>
<body leftmargin="0" topmargin="0"
onload="setarFoco('${requestScope.nomeCampo}');">
<html:form action="/filtrarNegativadorResultadoSimulacaoAction"
	name="FiltrarNegativadorResultadoSimulacaoActionForm"
	type="gcom.gui.cobranca.spcserasa.FiltrarNegativadorResultadoSimulacaoActionForm"
	method="post">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

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
              <tr><td></td></tr>
              
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
                <td class="parabg">Filtrar Negativador Resultado Simulação </td>
                <td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif"/></td>
              </tr>
            </table>
            <!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			
			
            <table width="100%" border="0" >       
			  <tr>
      			<td colspan="3">Para consultar o comando da negativação, informe os dados abaixo:</td>
    		  </tr>
    		  
    		   <tr>
      			<td><strong>Comando : </strong></td>
      			<td> <html:text property="idComando" name="FiltrarNegativadorResultadoSimulacaoActionForm" size="10" maxlength="10" readonly="true"	style="background-color:#EFEFEF; border:0;" />       			
       		    </td>
    		  </tr>    		  
    		  
     		  <tr>
      			<td><strong>Título do Comando :</strong></td>
       			<td> <html:textarea property="descricaoComando" name="FiltrarNegativadorResultadoSimulacaoActionForm" readonly="true" cols="50" rows="2" style="background-color:#EFEFEF; border:0;"/>       			
       		    </td>
	          </tr>
	  		
	  	  
	           <tr>
	            <td colspan="3"><div align="right"><input name="Button32232" type="button"
						class="bottonRightCol" value="Pesquisar Comando"
						onClick="javascript:pesquisarComandoNegativacao();" /></div></td>
	           </tr>
	           <tr>
                 <td colspan="3"><hr></td>
              </tr>
                
              <tr>  
               <td>  
				&nbsp;
				</td>              
               <td align="right">      
				<div align="right"><a href="javascript: if (validaForm()) { toggleBox('demodiv',1); }">
				<img border="0" src="<bean:message key="caminho.imagens"/>print.gif" title="Imprimir Resultado da Simulação" /> </a></div>
				</td>    
              </tr>
            </table>
       
       
             <jsp:include
				page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioNegativadorResultadoSimulacaoAction.do" />
			<p>&nbsp;</p>		
			
			</td>

		</tr>
	</table>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
