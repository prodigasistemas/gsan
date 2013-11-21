<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/menus-taglib.tld" prefix="menu" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="gcom.fachada.Fachada"%><html:html>
<head>

    <script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
      <%--Este script exibe várias imagens na página--%>

      <script language="JavaScript">
     
          var interval = 3; // delay between rotating images (in seconds)
          var random_display = 1; // 0 = sequential, 1 = random
          var empresa;
          interval *= 1000;


          var image_index = 0;
         
          image_list = new Array();
         
          <%    if(getServletContext().getAttribute("nomeEmpresa").equals("CAER")){    %>
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>caer_foto01.jpg");
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>caer_foto02.jpg");
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>caer_foto03.jpg");
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>caer_foto04.jpg");

          <%    }else if(getServletContext().getAttribute("nomeEmpresa").equals("COMPESA")){    %>

                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>compesa_foto01.jpg");
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>compesa_foto02.jpg");
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>compesa_foto03.jpg");
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>compesa_foto04.jpg");

                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>compesa_foto05.jpg");
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>compesa_foto06.jpg");
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>compesa_foto07.jpg");
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>compesa_foto08.jpg");
                 
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>compesa_foto09.jpg");
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>compesa_foto10.jpg");
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>compesa_foto11.jpg");
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>compesa_foto12.jpg");
                 
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>compesa_foto13.jpg");
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>compesa_foto14.jpg");
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>compesa_foto15.jpg");
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>compesa_foto16.jpg");
                 
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>compesa_foto17.jpg");
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>compesa_foto18.jpg");
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>compesa_foto19.jpg");
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>compesa_foto20.jpg");
                 
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>compesa_foto21.jpg");
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>compesa_foto22.jpg");
                 
          <%    }else if(getServletContext().getAttribute("nomeEmpresa").equals("SAAE-JUAZEIRO")){    %>
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>juazeiro_foto01.jpg");
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>juazeiro_foto02.jpg");
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>juazeiro_foto03.jpg");
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>juazeiro_foto04.jpg");
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>juazeiro_foto05.jpg");
        <%    }else{    %>
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>caern_foto01.jpg");
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>caern_foto03.jpg");
                  image_list[image_index++] = new imageItem("<bean:message key="caminho.imagens"/>caern_foto04.jpg");
        <%    }    %>

          var number_of_image = image_list.length;
         
          function imageItem(image_location) {
              this.image_item = new Image();
              this.image_item.src = image_location;
          }
         
          function get_ImageItemLocation(imageObj) {
              return(imageObj.image_item.src)
          }
         
          function generate(x, y) {
              var range = y - x + 1;
              return Math.floor(Math.random() * range) + x;
          }
         
          function getNextImage() {
            if (random_display) {
                  image_index = generate(0, number_of_image-1);
              } else {
                  image_index = (image_index+1) % number_of_image;
              }
              var new_image = get_ImageItemLocation(image_list[image_index]);
            return(new_image);
        }
         
          function rotateImage(place) {
              var new_image = getNextImage();
              document[place].src = new_image;
              var recur_call = "rotateImage('"+place+"')";
              setTimeout(recur_call, interval);
          }

          function mensagemVisualizacaoRA(form){              
              alert('ok1');	
              alert(form);
        	  alert('ok2'); 
              	          
          }
    </script>


<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
</head>

<%    if (getServletContext().getAttribute("nomeEmpresa").equals("CAERN")){    %>
         <body leftmargin="5" topmargin="5" onLoad="rotateImage('rImage')">
<%    }else if(getServletContext().getAttribute("nomeEmpresa").equals("CAER")){    %>
         <body leftmargin="5" topmargin="5" onLoad="rotateImage('imagemCaer')">
<%    }else if(getServletContext().getAttribute("nomeEmpresa").equals("COMPESA")){    %>
         <body leftmargin="5" topmargin="5" onLoad="rotateImage('imagemCompesa')">
<%    }else if(getServletContext().getAttribute("nomeEmpresa").equals("SAAE-JUAZEIRO")){    %>
         <body leftmargin="5" topmargin="5" onLoad="rotateImage('imagemJuazeiro')">
<%    }else{    %>
        <body leftmargin="5" topmargin="5">
<%    }    %>

    <%@ include file="/jsp/util/cabecalho.jsp" %>
    <%@ include file="/jsp/util/menu.jsp" %>

    <table width="770" border="0" cellspacing="5" cellpadding="0">
        <tr>
   
            <td width="150"  valign="top" class="leftcoltext">
                  <%@ include file="/jsp/util/informacoes_usuario.jsp" %>     
            </td>
           
            <td width="625" valign="top" class="centercoltext">
                  <table height="100%">
                    <tr>
                          <td></td>
                    </tr>
                  </table>
                 
                  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                          <td width="11">
                              <img src="<bean:message key="caminho.imagens"/>parahead_left.gif" border="0" />
                          </td>
                         
                          <td class="parabg">GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</td>
                          <td width="11">
                              <img src="<bean:message key="caminho.imagens"/>parahead_right.gif" border="0" />
                          </td>
                    </tr>
                  </table>
                 
                 

                 
                 <logic:equal name="RAUrgencia" value="true" scope="session">
                 
	                 <logic:present name="msgUrgencia1" scope="session">
	              
		               	<logic:present name="msgUrgencia2" scope="session">
		              		 <span style="color:red; font:18px"  >
		                         <bean:write name="msgUrgencia1" scope="session" /> 
		                         <input type="button" value="OK"
		                                onclick="window.location='/gsan/efetuarLoginAction.do?visualizacaoRAUrgencia=sim'" />
		                         <br />
		                         <bean:write name="msgUrgencia2" scope="session" />
	                      	</span>   
		                </logic:present>
		                        
		                <logic:notPresent name="msgUrgencia2" scope="session">
			                <span style="color:red; font:18px"  >
			          			<bean:write name="msgUrgencia1" scope="session" /> 
			                         <input type="button" value="OK"
			                                onclick="window.location='/gsan/efetuarLoginAction.do?visualizacaoRAUrgencia=sim'" />
			                </span> 
		                </logic:notPresent>
	              
	                </logic:present>
	                        
	                <logic:notPresent name="msgUrgencia1" scope="session">
	          			<logic:present name="msgUrgencia2" scope="session">
		              		 <span style="color:red; font:18px"  >
		                         <bean:write name="msgUrgencia2" scope="session" /> 
		                         <input type="button" value="OK"
		                                onclick="window.location='/gsan/efetuarLoginAction.do?visualizacaoRAUrgencia=sim'" />
	                      	</span>   
		                </logic:present>
	                </logic:notPresent>               
                 </logic:equal>     
                 
                  
              <%    if (getServletContext().getAttribute("nomeEmpresa").equals("COMPESA")){    %>
                     
                      <table width="95%">
                        <tr>
                             <td>
                                  <p>&nbsp;</p>
                                  <p><div align="center"><img name="imagemCompesa" width="520" height="294" /></div>
                                  <p>&nbsp;</p>
                             <td>
                        </tr>
                     
                      </table>
             
     
                      <table width="95%">
                          <tr>
                              <td>
                                  <strong>Unidade do usuário:</strong>
                                  <bean:write name="usuarioLogado"
                                      scope="session"
                                      property="unidadeOrganizacional.id"/> -
   
                                  <bean:write name="usuarioLogado"
                                      scope="session"
                                      property="unidadeOrganizacional.descricao"/>
     
                                  <br><br>
   
                                  <strong>
                                      <span style="font-size: 11px; font-family: verdana">Telefones para suporte ao GSAN:</span>
                                  </strong>
                                 
                                  <br><br>
                                <strong>
                                      <span style="font-size: 11px; font-family: verdana">Tira dúvidas comercial:</span>
                                  </strong>
                                  <br>
                                  <span style="font-size: 11px; font-family: verdana">
                                  &nbsp;&nbsp;(81) 3232-2000/3232-2900<br>
                                  </span>            
                              </td>
                        </tr>
                       
                        <tr>
                            <td width="50%">

                                  <strong>
                                      <span style="font-size: 11px; font-family: verdana">Parcelamento:</span>
                                  </strong>
                                  <br>
                                  <span style="font-size: 11px; font-family: verdana">
                                  &nbsp;&nbsp;Gerência de Relacionamento com Cliente - GRC: (81) 3412-9552<br>
                                  </span>            
                       
                            </td>
                           
                            <td>
                                   <strong>
                                       <span style="font-size: 11px; font-family: verdana">Cadastro:</span>
                                   </strong>
                                  <br>
                                  <span style="font-size: 11px; font-family: verdana">
                                  &nbsp;&nbsp;Marcos (81) 3412-9649<br>
                                  </span>

                                  <strong>
                                      <span style="font-size: 11px; font-family: verdana">Micromedição:</span>
                                  </strong>
                                  <br>
                                  <span style="font-size: 11px; font-family: verdana">
                                  &nbsp;&nbsp;Daniel: (81) 3412-9663<br>
                                  </span>

                                   <strong>
                                       <span style="font-size: 11px; font-family: verdana">Segurança/Acesso:</span>
                                   </strong>
                                  <br>
                                  <span style="font-size: 11px; font-family: verdana">
                                  &nbsp;&nbsp;Maristela (81) 3412-9636<br>
                                  </span>
                       
                            </td>
                        </tr>                       
                       
                    </table>
     
              <%    }   
                 
                  if (getServletContext().getAttribute("nomeEmpresa").equals("CAERN")){    %>
     
                      <table width="95%">
                        <tr>
                             <td>
                                  <p>&nbsp;</p>
                                  <p><div align="center"><img name="rImage" width="450" height="294" /></div>
                                  <p>&nbsp;</p>
                                  <logic:notEmpty name="mensagemGrupo" scope="session">		
								  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
								  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
								  <p>&nbsp;</p>
                                  <p>&nbsp;</p>		
								  </logic:notEmpty>	
                             <td>
                        </tr>
                      </table>
                     
            <%    }   
                 
                  if (getServletContext().getAttribute("nomeEmpresa").equals("SAAE-JUAZEIRO")){    %>
     
                      <table width="95%">
                        <tr>
                             <td>
                                  <p>&nbsp;</p>
                                  <p><div align="center"><img name="imagemJuazeiro" width="450" height="294" /></div>
                                  <p>&nbsp;</p>
 								   <logic:notEmpty name="mensagemGrupo" scope="session">		
								  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
								  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
								  <p>&nbsp;</p>
                                  <p>&nbsp;</p>		
								  </logic:notEmpty>		
                             <td>
                        </tr>
                      </table>

     
              <%    }   
                 
                  if (getServletContext().getAttribute("nomeEmpresa").equals("CAER")){    %>
     
                      <table width="95%">
                        <tr>
                             <td>
                                  <p>&nbsp;</p>
                                  <p><div align="center"><img name="imagemCaer" width="520" height="294" /></div>
                                  <p>&nbsp;</p>
                             <td>
                        </tr>
                     
                      </table>
     
                      <table width="95%">
                          <tr>
                              <td width="50%">

                                   <strong>
                                       <span style="font-size: 11px; font-family: verdana">Gerência Comercial: Emídio</span>
                                   </strong>
                                  <br>
                                  <span style="font-size: 11px; font-family: verdana">
                                  &nbsp;&nbsp;Contato (95) 2121.2215 - GEC<br>
                                  </span>
             
                                  <br>
                                  <strong>
                                      <span style="font-size: 11px; font-family: verdana">Divisão de Cadastro: Rafael</span>
                                  </strong>
                                  <br>
                                  <span style="font-size: 11px; font-family: verdana">
                                  &nbsp;&nbsp;Contato (95) 2121.2218<br>
                                  </span>
             
                                  <br>
                                  <strong>
                                  <span style="font-size: 11px; font-family: verdana">Divisão de Leitura e Corte: Leocádio</span>
                                  </strong>
                                  <br>
                                  <span style="font-size: 11px; font-family: verdana">
                                  &nbsp;&nbsp;Contato (95) 2121.2227  -  DLC<br>
                                  </span>
                                  
                                  <br>
                                  <strong>
                                  <span style="font-size: 11px; font-family: verdana">Divisão de Atendimento ao Público: Elcina</span>
                                  </strong>
                                  <br>
                                  <span style="font-size: 11px; font-family: verdana">
                                  &nbsp;&nbsp;Contato (95) 2121.2222<br>
                                  </span>
             
                           
                            </td>
                             
                              <td width="50%" valign="top">
                             
     
                                  <br>
                                  <strong>
                                  <span style="font-size: 11px; font-family: verdana">Gerência de Serviços: Dioclecio</span>
                                  </strong>
                                  <br>
                                  <span style="font-size: 11px; font-family: verdana">
                                  &nbsp;&nbsp;Contato (95) 2121.2224 - GES<br>
                                  </span>

                                  <br>
                                  <strong>
                                  <span style="font-size: 11px; font-family: verdana">Divisão de Faturamento e Cobrança: Genival</span>
                                  </strong>
                                  <br>
                                  <span style="font-size: 11px; font-family: verdana">
                                  &nbsp;&nbsp;Contato (95) 2121.2219  -  DFC<br>
                                  </span>
                                <br>
                                  <strong>
                                  <span style="font-size: 11px; font-family: verdana">Divisão de Arrecadação: Synara</span>
                                  </strong>
                                <br>
                                  <span style="font-size: 11px; font-family: verdana">
                                  &nbsp;&nbsp;Contato (95) 2121.2203  -  DCF<br>
                                  </span>
     
                              </td>
     
                          </tr>
                      </table>
                     
                      <br>
              <%    }   
                   if (getServletContext().getAttribute("nomeEmpresa").equals("CAEMA")){    %>
                
                     <table width="95%">
                        <tr>
                             <td>
                                 <p>&nbsp;</p>
                                  <p>&nbsp;</p>
                            
                                  <p><div align="center">
                                      <img src="<bean:message key="caminho.imagens"/>logoCaema.gif"
                                          name="rImage"
                                          width="400"
                                          height="250"
                                          /></div>
                                  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
								  <logic:notEmpty name="mensagemGrupo" scope="session">		
								  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
								  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
								  <p>&nbsp;</p>
                                  <p>&nbsp;</p>		
								  </logic:notEmpty>	
                             <td>
                        </tr>
                      </table>
              <%    }    %>
              <%   
                   if (getServletContext().getAttribute("nomeEmpresa").equals("COSANPA")){    %>
                
                     <table width="95%">
                        <tr>
                             <td>
                            	  <p>&nbsp;</p>
                                  <p>
                                  <div align="center">
                                      <img src="<bean:message key="caminho.imagens"/>logo_cosanpa.jpg"
                                          name="rImage"
                                          width="450"
                                          height="294" />
                                  </div>
                                  <logic:notEmpty name="mensagemGrupo" scope="session">		
								  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
								  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
								  <p>&nbsp;</p>
                                  <p>&nbsp;</p>		
								  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
                                  <p>&nbsp;</p>	
                                  <p>&nbsp;</p>
                                  <p>&nbsp;</p>	
								  </logic:notEmpty>	
                             <td>
                        </tr>

                      </table>
              <%    }    %>
             
              <%    if (getServletContext().getAttribute("nomeEmpresa").equals("IPAD")){    %>
                
                     <table width="95%">
                        <tr>
                             <td>
                                <p>&nbsp;</p>
                                 <p>&nbsp;</p>
                                  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
                                  <p><div align="center">
                                      <a href="http://www.gsan.ipad.com.br/" target="_blank">
                                      <img src="<bean:message key="caminho.imagens"/>gsan.gif"
                                          name="rImage"
                                          width="160"
                                          border="0"
                                          height="160" /></a>
                                  </div>
                                  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
								   <logic:notEmpty name="mensagemGrupo" scope="session">		
								  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
								  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
								  <p>&nbsp;</p>
                                  <p>&nbsp;</p>		
								  </logic:notEmpty>		 
                             <td>
                        </tr>
                      </table>
              <%    }    %>
              
              <%    if (getServletContext().getAttribute("nomeEmpresa").equals("COSAMA")){    %>
                
                     <table width="95%">
                        <tr>
                             <td>
                                <p>&nbsp;</p>
                                 <p>&nbsp;</p>
                                  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
                                  <p><div align="center">
                                      
                                      <img src="<bean:message key="caminho.imagens"/>cosamaPrincipal.jpg"
                                          name="rImage"
                                          width="400"
                                          border="0"
                                          height="242" />
                                  </div>
                                  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
								   <logic:notEmpty name="mensagemGrupo" scope="session">		
								  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
								  <p>&nbsp;</p>
                                  <p>&nbsp;</p>
								  <p>&nbsp;</p>
                                  <p>&nbsp;</p>		
								  </logic:notEmpty>		 
                             <td>
                        </tr>
                      </table>
              <%    }    %>
             
        </td>
      </tr>
    </table>
<%@ include file="/jsp/util/rodape.jsp" %>

</body>
</html:html>