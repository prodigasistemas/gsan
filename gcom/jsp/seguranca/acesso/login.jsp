<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.Pagina, gcom.util.ConstantesSistema" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">
	function lembrarSenha() {
		var form = document.forms[0];
	  	if(form.login.value == null || trim(form.login.value) == ""){
	    	alert('Informe o login');
	    	form.login.focus();
	  	}else{
	    	abrirPopup('exibirLembrarSenhaAction.do?login='+form.login.value, 270, 580);
	  	}  
	}
	
	function setarFoco(){
		var form = document.forms[0];
		
		form.login.focus();	
	}
	
</script>

</head>


<body leftmargin="5" topmargin="5" onLoad="javascript:setarFoco();">
	
	<%@ include file="/jsp/util/cabecalho.jsp" %>
	<table width="770" border="0" cellspacing="5" cellpadding="0">
  		<tr>
    		<td width="115" valign="top" class="leftcoltext">
      			<div align="center">
        			<form action="efetuarLoginAction.do" 
        		   		method="post" autocomplete="off">
        	
	        		<table cellpadding="3" class="tableinlayer">
	          			<tr>
			            <td>
				        	<table width="100" border="0" align="center" >
		                    	<tr>
		                      		<td width="94">Login</td>
		                    	</tr>
			                    
			                    <tr>
			                    	<td>
			                        	<input type="text" maxlength="11" 
			                        		name="login" 
			                        		size="14"  
			                        		style="text-transform: none;"/>
			                      	</td>
			                    </tr>
			                    
			                    <tr>
			                      <td>Senha</td>
			                    </tr>
			                    
			                     <%	if (getServletContext().getAttribute("indicadorSenhaForte").equals(ConstantesSistema.SIM.toString())){	
			                     %>
			                    <tr>
			                    	<td>
			                        	<html:password maxlength="8" 
			                        		property="senha" 
			                        		size="14" 
			                        		redisplay="false"  
			                        		style="text-transform: none;"/>
			                      	</td>
			                    </tr>
			                    <%}else{ %>
                    			<tr>
			                    	<td>
			                        	<html:password maxlength="6" 
			                        		property="senha" 
			                        		size="14" 
			                        		redisplay="false"  
			                        		style="text-transform: none;"/>
			                      	</td>
			                    </tr>
			                    <%}%>
                    			
                    			<tr>
                      				<td align="right">
                        				<input type="submit" class="buttonLeftCol" 
                        					value="Login" 
                        					name="buttonLogin"/>
                      				</td>
                    			</tr>
                  			</table>
		        		</td>
		     		 	</tr>
		    		</table>
	     			</form>
	    		
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

				<%	if (!getServletContext().getAttribute("nomeEmpresa").equals("IPAD")){ %>
	    	   			<p align="justify">
          					<a href="/gsan/exibirLembrarSenhaAction.do">Esqueceu a senha ?</a>
 	   					</p>	  
	    		<%	}	%>
        
			       <%@ include file="/jsp/util/mensagens.jsp" %>
			
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
			       <p align="left">&nbsp;</p>
			       <p align="left">&nbsp;</p>
			       <p align="left">&nbsp;</p>
			       <p align="left">&nbsp;</p>
     			</div>
   			</td>
		   	
		   	<td class="centercoltext" valign="top">
		     
		    	<table height="100%" border="0">
		       		<tr>
		         		<td></td>
		       		</tr>
		     	</table>
		     	
		     	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		       		<tr>
		         		<td width="11">
		         			<img border="0" 
		         				src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
		         		</td>
		         		<td class="parabg">GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</td>
		         		<td width="11">
		         			<img border="0" 
		         			src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
		         		</td>
		       		</tr>
		      	</table>
      
      		<%	if (getServletContext().getAttribute("nomeEmpresa").equals("COMPESA")){	%>
      
		      		<table  width="100%" height="34" border="0" align="center" cellpadding="0" cellspacing="0">
		       			<tr>
		         		<td>
		         			<br><div align="justify">
		     				<br><br><br><br><br>
		     				</div>
		         		</td>
		       			</tr>
		      		</table>
      
			      	<table  width="100%" height="34" border="0" align="center" cellpadding="0" cellspacing="0">
			       		<tr>
			         	<td>
			         		<br><div align="center">
			         		<img border="0" 
			         			src="<bean:message key="caminho.imagens"/>gsan.gif" /> </div>
			         	</td>
			       		</tr>
			      	</table>
      		<%	}	
      			
      			if (getServletContext().getAttribute("nomeEmpresa").equals("CAERN")){	%>
      	
			      	<table  width="100%" height="34" border="0" align="center" cellpadding="0" cellspacing="0">
			       		<tr>
			         	<td>
			         		<br><br><br><br><br><br>
			         		<div align="center">
			         			<img border="0" src="<bean:message key="caminho.imagens"/>gsan.gif" /> </div>
			         	</td>
			       		</tr>
			      	</table>
      		<%	}
      			if (getServletContext().getAttribute("nomeEmpresa").equals("CAER")){	%>
      	
			      	<table  width="100%" height="34" border="0" align="center" cellpadding="0" cellspacing="0">
			       		<tr>
			         	<td>
			         		<br><br><br><br><br><br>
			         		<div align="center">
			         			<img border="0" 
			         			src="<bean:message key="caminho.imagens"/>gsan.gif" /> </div>
			         	</td>
			       		</tr>
			      	</table>
      
      		<%	}
      			if (getServletContext().getAttribute("nomeEmpresa").equals("IPAD")){ %>
      	
			      	<table  width="100%" height="34" border="0" align="center" cellpadding="0" cellspacing="0">
			       		<tr>
			         	<td>
			         		<br><br>
       						
       						<strong>
   							<span style="font-size: 13px; font-family: verdana">
			         			O GSAN, por ser um software livre, pode ter seus códigos fontes ou outros artefatos do 
			         			sistema baixados por qualquer pessoa física ou jurídica através portal do software público.
			         		<br><br>
								Conheça o portal do Software Público e faça parte da Comunidade Gsan, acessando o link abaixo:
							</span>
							</strong>
							<br><br>
							<br><br>
			         		<div align="center">
								<a href="http://www.softwarepublico.gov.br/" target="_blank">
			         			<img border="0" 
			         				src="<bean:message key="caminho.imagens"/>softwarepublico.jpg" /> 
			      				</a>
			         		</div>
			         	</td>
			       		</tr>
			      	</table>
      
      		<%	}	%>
      
	  		</td>
  		</tr>
	</table>
 	<%@ include file="/jsp/util/rodape.jsp" %>
</body>
</html:html>