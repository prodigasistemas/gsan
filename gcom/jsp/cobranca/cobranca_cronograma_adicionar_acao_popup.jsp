<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><head>
<html:html> 
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script>
function validaData(form){
  var mesAno = form.mesAno.value;
  if(mascaraAnoMes(mesAno)){
     return false;
  }
}

function validaMesAnoVazio(form){
	if(form.mesAno.value == ""){
		alert('Informe Mês/Ano');
		return false;
	}	
}

function validarCamposDinamicos(form){
 
 	var camposValidos = true;
 
 	
 	if(!verificaAnoMes(form.mesAno)){
		camposValidos = false;
	}
 	if(camposValidos == true){
	 	for (i=0; i < form.elements.length; i++) {
	    	
	    	if (form.elements[i].type == "text" && form.elements[i].id.length > 1){
	    		if(form.elements[i].value != ""){	
					if(form.elements[i].id == "data"){
						if(!verificaData(form.elements[i])){
							camposValidos = false;
							break;
						}
					}
				}else{
					camposValidos = true;
				}
			}
		}
	}
		
	return camposValidos;
}

function extendeTabelaAtividade(valorUm,valorDois){

 var valor = document.getElementById(valorUm);
 var valor2 = document.getElementById(valorDois);

 if(valor.style.display == "none"){
	valor2.style.display = "";
	valor.style.display = "";
 }else{
 	valor2.style.display = "none";
 	valor.style.display = "none";
 }
 
}
</script>

<script language="JavaScript">
function validaDataCompleta(data, event){
		if(mascaraData(data, event)){
			return false;
		}
}
</script>
</head>
<logic:present name="adicionar">
	<body leftmargin="0" topmargin="0" onload="resizePageSemLink(710, 500); chamarSubmitComUrl('exibirAtualizarCobrancaCronogramaAction.do?reloadPage=1');">
</logic:present>
<logic:notPresent name="adicionar">
	<body leftmargin="0" topmargin="0" onload="resizePageSemLink(710, 500);setarFoco('${requestScope.nomeCampo}');">
</logic:notPresent>

<html:form
  action="/exibirAdicionarAcaoCobrancaCronogramaPopupAction.do?adicionar=adicionar"
  name="CobrancaActionForm"
  type="gcom.gui.cobranca.CobrancaActionForm"
  method="post"
  onsubmit="return validarCamposDinamicos(this) && validaMesAnoVazio(this);"
>
<table width="680" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="670" valign="top" class="centercoltext">
    <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
          <td class="parabg">Adicionar Ações de Cobrança ao Cronograma</td>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0" border="1">
        <tr>
          <td colspan="4">Para adicionar a(s) ação(ões) no cronograma de cobrança, informe os dados abaixo:</td>
        </tr>
        <tr>
          <td width="25%"><strong>Grupo de Cobrança:</strong></td>
          <td width="75%" colspan="3"><html:text maxlength="50" property="descricaoGrupoCobranca" size="50" tabindex="1" disabled="true"/></td>
        </tr>
        <tr>
          <td height="0"><strong>Mês/Ano do Cronograma:</strong></td>
          <td colspan="3"><html:text maxlength="10" property="mesAno" size="10" tabindex="2" disabled="true"/>
          </td>
        </tr>
        <tr>
          <td height="0"><strong>Ação de Cobrança:</strong></td>
          <td colspan="3">
             <html:select property="idCobrancaAcao" tabindex="3" onchange="redirecionarSubmit('exibirAdicionarAcaoCobrancaCronogramaPopupAction.do?reload=N');">
	       <html:option value="-1">&nbsp;</html:option>
               <html:options collection="colecaoCobrancaAcaoNovo" labelProperty="descricaoCobrancaAcao" property="id"/>
             </html:select>
          </td>
        </tr>
        <tr>
        	<td colspan="4"><hr></td>
        </tr>
       <logic:present name="cobrancaAcaoEscolhida">
        <tr>
          <td colspan="4">
          <logic:notEmpty name="CobrancaActionForm" property="idCobrancaAcao">
	        <table width="100%" bgcolor="#99CCFF">
                     <!--header da tabela interna -->
                     <tr bordercolor="#FFFFFF" bgcolor="#99CCFF">
                       <td  width="33%"> <div align="center" class="style9"><strong>A&ccedil;&atilde;o 
                           de Cobran&ccedil;a</strong></div></td>
                       <td width="35%"> <div align="center" class="style9"><strong>Predecessora</strong></div></td>
                       <td width="12%"> <div align="center" class="style9"><strong> 
                           Obrigat&oacute;ria</strong></div></td>

               		 </tr>
                     <tr bgcolor="#FFFFFF">
                       <td width="35%"> <div align="center"><a href="javascript:extendeTabelaAtividade('n<bean:write name="cobrancaAcaoEscolhida" property="id" />','t<bean:write name="cobrancaAcaoEscolhida" property="id" />');">${cobrancaAcaoEscolhida.descricaoCobrancaAcao}</a> &nbsp;<br>

                         </div></td>
                       <td width="35%"> <div align="center">${cobrancaAcaoEscolhida.cobrancaAcaoPredecessora.descricaoCobrancaAcao} &nbsp;</div></td>
                       <td width="10%"> 
	                      <div align="center">
	                         <logic:equal name="cobrancaAcaoEscolhida" property="indicadorObrigatoriedade" value="1">
	                           SIM
	                          </logic:equal>
	                         <logic:notEqual name="cobrancaAcaoEscolhida" property="indicadorObrigatoriedade" value="1">
	                           NÃO
	                         </logic:notEqual>
	                      </div>
                       </td>
               		</tr>
           <tr id="t<bean:write name="cobrancaAcaoEscolhida" property="id" />" style="display:none;">
        	<td colspan="4">
	          <logic:present name="colecaoCobrancaAtiviade">
        		<table width="100%" cellpadding="0" id="n<bean:write name="cobrancaAcaoEscolhida" property="id" />" cellspacing="0" style="display:none;">
				              <tr>
				                <td height="0">
				                  <table width="100%" bgcolor="#99CCFF">
				                    <!--header da tabela interna -->
				                    <tr bordercolor="#000000">
				                      <td width="5%" bgcolor="#ffffff" align="center"><div align="center"><strong>&nbsp;</strong></div></td>
									  <td  width="10%"> <div align="center" class="style9"><strong>Comandar</strong></div></td>
				                      <td width="33%" bgcolor="#90c7fc" align="center"><div align="center"><strong>Atividade</strong></div></td>
				                      <td width="33%" bgcolor="#90c7fc" align="center"><div align="center"><strong>Predecessora</strong></div></td>
				                      <td width="24%" bgcolor="#90c7fc" align="center"><div align="center"><strong>Data Prevista</strong></div></td>
				                    </tr>
				                  </table></td>
				              </tr>
				              <tr>
				                <td height="83">
				                  <div style="width: 100%; height: 100%; overflow: auto;">
				                    <table width="100%" align="center" bgcolor="#99CCFF">
				                      <!--corpo da segunda tabela-->
				                      <logic:notEmpty name="colecaoCobrancaAtiviade">
				                        <logic:iterate name="colecaoCobrancaAtiviade" id="atividadeCobranca">
				                          <tr bgcolor="#FFFFFF">
				                          	  <td width="5%" bgcolor="#ffffff" align="center"><div align="center"><strong>&nbsp;</strong></div></td>
						                      <td width="10%"> <div align="center"> 
						                         <input name="comandar<bean:write name="cobrancaAcaoEscolhida" property="id" />atividade<bean:write name="atividadeCobranca" property="id"/>" type="checkbox" value="1" checked>
						                      </div></td>
				                              <td width="33%">
				                                <div align="left">
				                                   <bean:write name="atividadeCobranca" property="descricaoCobrancaAtividade"/>
				                                </div>
				                              </td>
				                              <td width="33%">
										        <div align="left">
												  <logic:present name="atividadeCobranca" property="atividadeCobrancaPredecessora">
				                                   <bean:write name="atividadeCobranca" property="cobrancaAtividadePredecessora.descricaoCobrancaAtividade"/>
				                                  </logic:present>
								  				  <logic:notPresent name="atividadeCobranca" property="cobrancaAtividadePredecessora">
				                                   &nbsp;
				                                  </logic:notPresent>
												</div>
				                              </td>
				                              <td width="19%">
												<div align="center">
				                                  <input type="text" maxlength="10" name="a<bean:write name="CobrancaActionForm" property="idCobrancaAcao"/>n<bean:write name="atividadeCobranca" property="id"/>" size="10" id="data" onkeypress="validaDataCompleta(this, event)" />
				                                    <a href="javascript:abrirCalendario('CobrancaActionForm', 'a<bean:write name="CobrancaActionForm" property="idCobrancaAcao"/>n<bean:write name="atividadeCobranca" property="id"/>')">
				                                      <img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" title="Exibir Calendário"/>
				                                    </a>
													</div>
				                              </td>
				                            </tr>
				                        </logic:iterate>
				                      </logic:notEmpty>
				                    </table>
				                </div>
				    	       </td>
				              </tr>
				            </table>
		          </logic:present>
        		</td>
    	    </tr>
	      </table>
          </logic:notEmpty>
         </logic:present>    	    
	        <tr>
    	      <td>&nbsp;</td>
	          <td>&nbsp;</td>
        	</tr>
    	    <tr>
	          <td height="24">
	            <INPUT TYPE="button" class="bottonRightCol" value="Limpar" 
	            onclick="redirecionarSubmit('exibirAdicionarAcaoCobrancaCronogramaPopupAction.do?limpar=sim');"/>
	          </td>
        	  <td colspan="4" align="right">
        	  	<INPUT TYPE="submit" class="bottonRightCol" value="Adicionar"/>
        	  	<INPUT TYPE="button" class="bottonRightCol" value="Fechar" onclick="window.close();"/>
        	  </td>
	  	</tr>
	  </table>
	</td>
	</tr>
	</table>
</body>
</html:form>
</html:html>
