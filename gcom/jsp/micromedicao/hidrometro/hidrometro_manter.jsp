<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script type="text/javascript" language="JavaScript">
<!-- Begin
	function facilitador(objeto){
		if (objeto.value == "0" || objeto.value == undefined){
			objeto.value = "1";
			marcarTodos();
		} else{
			objeto.value = "0";
			desmarcarTodos();
		}
	}
	
	function atualizarConjuntoHidrometro(){
 		var form = document.forms[0];
 		form.action='exibirAtualizarConjuntoHidrometroAction.do';
 		form.submit();
	}

	// Verifica se há item selecionado
	function verficarSelecao(objeto){

		if (CheckboxNaoVazio(objeto)){
			if (confirm ("Confirma remoção?")) {
				document.forms[0].action = "/gsan/removerHidrometroAction.do"
				document.forms[0].submit();
			 }
		}
	}
-->
</script>

</head>

<body leftmargin="5" topmargin="5">

<html:form
  action="/removerHidrometroAction"
  name="ManutencaoRegistroActionForm"
  type="gcom.gui.ManutencaoRegistroActionForm"
  method="post"
  onsubmit="return CheckboxNaoVazio(document.ManutencaoRegistroActionForm.idRegistrosRemocao) && confirm('Confirma Remoção?')"
>
<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
    
    <td width="115" valign="top" class="leftcoltext">
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
      	</div>
   	</td>
    
    <td valign="top" class="centercoltext">
    	<table height="100%">
	        <tr>
	          <td></td>
	        </tr>
      	</table>
      	
      	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        	<tr>
          		<td width="11">
          			<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/>
          		</td>
          		<td class="parabg">Manter Hidr&ocirc;metro </td>
          		<td width="11">
          			<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/>
          		</td>
        	</tr>
      	</table>
		<table width="100%">
					<tr>
						<td width="90%" colspan="2">&nbsp;</td>
						<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}micromedicaoHidrometroManter', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
						</logic:present>
						<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
						</logic:notPresent>
					</tr>
		</table>
      	<table width="100%" cellpadding="0" cellspacing="0">
        	<tr>
          		<td colspan="7">
          			<font color="#000000" 
          				style="font-size:10px" 
          				face="Verdana, Arial, Helvetica, sans-serif">
          				
          				<strong>Hidrometros Encontrados:</strong>
          			</font>
          		</td>
        	</tr>
        
        	<tr>
            	<td colspan="7" bgcolor="#000000" height="2"></td>
        	</tr>
       			
   			<tr bordercolor="#90c7fc">
   				<td colspan="7">

     			<table width="100%" bgcolor="#99CCFF">
					<tr bordercolor="#90c7fc" bgcolor="#90c7fc" >
		           		<td bgcolor="#90c7fc" width="7%" align="center">
		           			<strong>
		           			<a href="javascript:facilitador(this);">Todos</a></strong>
		           		</td>
		           		<td bgcolor="#90c7fc" width="20%" align="center">
		           			<strong>N&uacute;mero</strong>
		           		</td>
		           		<td bgcolor="#90c7fc" width="13%" align="center">
		           			<strong>Data de Aquisi&ccedil;&atilde;o</strong>
		           		</td>
		           		<td bgcolor="#90c7fc" width="13%" align="center">
		           			<strong>Ano de Fabrica&ccedil;&atilde;o</strong>
		           		</td>
		           		<td bgcolor="#90c7fc" width="13%" align="center">
		           			<strong>Marca</strong>
		           		</td>
		           		<td bgcolor="#90c7fc" width="13%" align="center">
		           			<strong>Capacidade</strong>
		           		</td>
		           		<td bgcolor="#90c7fc" width="20%" align="center">
		           			<strong>Situa&ccedil;&atilde;o</strong>
		           		</td>
					</tr>
					
	    			<pg:pager isOffset="true" 
	    				index="half-full" 
	    				maxIndexPages="10" 
	    				export="currentPageNumber=pageNumber;pageOffset" 
	    				maxPageItems="10" 
	    				items="${sessionScope.totalRegistros}">
       				
			           	<pg:param name="pg"/>
			           	<pg:param name="q"/>
			           	
		         		<logic:present name="hidrometros">
	           		<%	int cont = 0;	%>
	         			<logic:iterate name="hidrometros" id="hidrometro">
	            			<pg:item>
	            		<%	cont = cont+1;
	             		if (cont%2==0){	%>
	               			<tr bgcolor="#cbe5fe">
	             	<%	}else{	%>
	                			<tr bgcolor="#FFFFFF">
	             	<%	}	%>
	                   
	                   	<td width="7%">
	                     		<div align="center">
	                       		<input type="checkbox" 
	                       			name="idRegistrosRemocao" 
	                       			value="<bean:write name="hidrometro" property="id"/>"/>
	                     		</div>
	                   	</td>
	                   	
	                   	<td width="20%" align="center">
	                      		<html:link page="/exibirAtualizarHidrometroAction.do"
	                                  paramName="hidrometro"
	                                  paramProperty="id"
	                                  paramId="idRegistroAtualizacao">
	                          		<bean:write name="hidrometro" 
	                          			property="numero"/>
	                       	</html:link>
	                   	</td>
	                   	
	                   	<td width="13%" align="center">
	                    		<bean:write name="hidrometro" 
	                    			property="dataAquisicao" 
	                    			formatKey="date.format"/>
	
	                   	</td>
	                   	
	                   	<td width="13%" align="center">
	                      		<bean:write name="hidrometro" 
	                      			property="anoFabricacao"/>
	                   	</td>
	                   	
	                   	<td width="13%" align="center">
	                      		<bean:write name="hidrometro" 
	                      			property="hidrometroMarca.descricaoAbreviada"/>
	                   	</td>
	
	                   	<td width="13%" align="center">
	                      		<bean:write name="hidrometro" 
	                      			property="hidrometroCapacidade.descricaoAbreviada"/>
	                   	</td>
	
	                   	<td width="20%" align="left">
	                      		<bean:write name="hidrometro" 
	                      			property="hidrometroSituacao.descricao"/>
	                   	</td>
	
	               		</tr>
	           			</pg:item>
	           			</logic:iterate>
          				</logic:present>

            	</table>

				<table align="center">
					<tr align="center">
						<td align="center">
							<div align="center">
								<strong><%@ include	file="/jsp/util/indice_pager_novo.jsp"%>
								</strong>
							</div>
						</td>
					</tr>
				</table>
      			</pg:pager>
             			
       			<table width="100%" border="0">
           			<tr>
            			<td valign="top">
            				<gsan:controleAcessoBotao name="Button" 
            					value="Remover"
	  							onclick="javascript:verficarSelecao(idRegistrosRemocao);" 
	  							url="removerHidrometroAction.do"/>
			
							<input name="button" 
								type="button" 
								class="bottonRightCol" 
								value="Voltar Filtro" 
								onclick="window.location.href='<html:rewrite page="/exibirManterHidrometroAction.do?voltarFiltro=1"/>'" 
								align="left" 
								style="width: 80px;">
						</td>
            				
            			<td valign="top">
           		 			<div align="right">
           		 				<a href="javascript:toggleBox('demodiv',1);">
								<img border="0"
									src="<bean:message key="caminho.imagens"/>print.gif"
									title="Imprimir Hidrômetros" /></a>
							</div>
						</td>
           			</tr>
      			</table>
         		</td>
   			</tr>
        			
      	</table>
      			
   		<p>&nbsp;</p>
   			
   		<table width="100%" border="0">
       		<tr>
         		<td>Clique nesse bot&atilde;o para atualizar um conjunto de hidr&ocirc;metros</td>
       		</tr>
     			
     		<tr>
         		<td>
           		<div align="left">
             		<logic:present name="conjuntoHidrometro">
              			<input name="button" 
              				type="button" 
              				class="bottonRightCol" 
              				value="Atualizar Conjunto" 
              				onclick="atualizarConjuntoHidrometro();">
             		</logic:present>
             	
             		<logic:notPresent name="conjuntoHidrometro">
              			<input name="button" 
              				type="button" 
              				class="bottonRightCol" 
              				value="Atualizar Conjunto" disabled />
             		</logic:notPresent>
             	</div>
           		</td>
     		</tr>
   		</table>

		<jsp:include
			page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioHidrometroManterAction.do" />
		
		</td>
	</tr>
	<%@ include file="/jsp/util/rodape.jsp"%>
	
</table>
</html:form>
</body>
</html:html>