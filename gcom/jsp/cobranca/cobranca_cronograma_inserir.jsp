<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ page import="gcom.cobranca.CobrancaAtividade" %>



<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript">

</script>
<script language="JavaScript">

    function IntegerValidations () {
    	this.ab = new Array("quantidadeMaximaDocumentos", "Quantidade máxima de documentos deve somente conter números positivos.", new Function ("varName", " return this[varName];"));     	
    }


function validarForm(form){
	if(validaGrupo() && validaMesAnoVazio(form) && validarCamposDinamicos(form) && validateLong(form)){
		form.submit();
	}
}

function validaDataCompleta(data, event){
		if(mascaraData(data, event)){
			return false;
		}
}

function validaMesAnoVazio(form){
	if(form.mesAno.value == ""){
		alert('Informe Mês/Ano.');
		return false;
	}else if(!verificaAnoMes(form.mesAno)){
		return false;
	}else{
		return true;
	}
}

function validaGrupo(){
		var form = document.forms[0];
		if(form.idGrupoCobranca.value == "-1"){
			if(form.mesAno.value == ""){
				alert("Informe Grupo. \n Informe Mês/Ano.");
				return false;
			}else{
				alert("Informe Grupo.");
				return false;
			}
		}else{
			return true;
		}
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
 //habilitaQuantidadeMaximaDocumentos(valorTres);
 
}

function validarCamposDinamicos(form){

 	for (i=0; i < form.elements.length; i++) {
	    	
    	if (form.elements[i].type == "text" && form.elements[i].id.length > 1){
    		if(form.elements[i].value != ""){	
				if(form.elements[i].id == "data"){
					if(!verificaDataMensagem(form.elements[i], "Data Prevista inválida.")){
						return false;
						break;
					} 
				} else if (form.elements[i].id == 'quantidadeMaximaDocumentos'){
				    var nomeCampoQtd = form.elements[i].name;
				    var valorQtd = trim(form.elements[i].value);
				    form.elements[i].value = valorQtd;
                    if (valorQtd != null && valorQtd.length > 0 && (isNaN(valorQtd) || !(valorQtd >= 0))) {
                    	alert ("Quantidade máxima de documentos deve somente conter números positivos.");
                    	form.elements[i].focus();
                    	return false;
                    }
				    
				    var nomeCampoData = nomeCampoQtd.substr(4,nomeCampoQtd.length);
				    var valorData = eval("document.forms[0]." + nomeCampoData + ".value");
				    if (valorData == ''){
				       alert('Data prevista obrigatória quando a quantidade máxima de documentos é informada');
				       eval("document.forms[0]." + nomeCampoData + ".focus()");
				       return false;
				    }
				}
			}
		}
	}
	return true;
}


	function reload(){
	 	var form = document.forms[0];
	 	form.action = "/gsan/exibirInserirCronogramaCobrancaAction.do?grupo=1";
		form.submit();
	}

	function calcularDataPrevista(){
		var form = document.forms[0];
	 	
	 	if(form.dataInicio.value == ''){
	 		alert('Informe Data de Início.');
	 	}else{
		 	form.action = "/gsan/exibirInserirCronogramaCobrancaAction.do?calcularDataPrevista=1";
			form.submit();
	 	}
	 	
 	}
 	
 	function limparForm(){
	 	var form = document.forms[0];
	 	
	 	form.action = "/gsan/exibirInserirCronogramaCobrancaAction.do?limparForm=1";
		form.submit();
	}

</script>

</head>

<body leftmargin="5" topmargin="5" >
<html:form
  action="/inserirCobrancaCronogramaAction"
  name="CobrancaActionForm"
  type="gcom.gui.cobranca.CobrancaActionForm"
  method="post"  
>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
   <td width="135"  valign="top" class="leftcoltext">

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
 <html:hidden property="quantidadeMaximaDocumentos" />
 
  <td width="625" valign="top" class="centercoltext">
            <!--In&iacute;cio Tabela Reference a P&aacute;gina&ccedil;&atilde;o da Tela de Processo-->
            <table>
              <tr>
                <td></td>
              </tr>
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
                <td class="parabg">Inserir Cronograma de Cobrança</td>
                <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
              </tr>
            </table>
            <!--Fim Tabela Reference a P&aacute;gina&ccedil;&atilde;o da Tela de Processo-->
            <p>&nbsp;</p>
            <table width="100%" border="0" >

                <tr>
                  <td colspan="2">Para adicionar o(s) cronograma(s) de cobrança,
                    informe os dados abaixo:</td>
                </tr>
                <tr>
                  <td><strong>Grupo:<font color="#FF0000">*</font></strong> </td>
                  <td>
                    <html:select property="idGrupoCobranca" onchange="reload();">
                    <html:option value="-1">&nbsp;</html:option>
  		       <html:options collection="gruposCobranca" labelProperty="descricao" property="id" />
                    </html:select>
		  		</td>
                </tr>
                <tr>
                  <td><strong>Mês/Ano:<font color="#FF0000">*</font></strong></td>
                  <td><html:text maxlength="7" 
                  				 property="mesAno" 
                  				 size="7" 
                  				 onkeyup="mascaraAnoMes(this, event);"
                  				 onkeypress="return isCampoNumerico(event);"/>
                    &nbsp; mm/aaaa</td>
                </tr>
                
                <logic:equal name="exibirDataInicio" value="1" scope="request">
	                <tr>
	                	<td><strong>Data de Início:<font color="#FF0000">*</font></strong></td>
						<td><html:text  property="dataInicio" maxlength="10"
							size="10" onkeyup="mascaraData(this, event);" /> <a
							href="javascript:abrirCalendario('CobrancaActionForm', 'dataInicio')">
						<img border="0"
							src="<bean:message key="caminho.imagens"/>calendario.gif"
							width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>&nbsp;dd/mm/aaaa
						</td>
						<td align="right">
						  <input name="Button" type="button" class="bottonRightCol" value="Calcular Data Prevista" align="right" 
						  onclick="calcularDataPrevista();" />
					    </td>
	                </tr>
                </logic:equal>
               
                <tr>
                  <td>&nbsp;</td>
                  <td align="left"><font color="#FF0000">*</font> Campo Obrigat&oacute;rio
                  </td>
                </tr>
                <tr>
                  <td><p>&nbsp;</p></td>
                  <td> </td>
                </tr>

            </table>
                
          
            <table width="100%" cellpadding="0" cellspacing="0">
            	<tr> 
                   <td bgcolor="#5782E6"><div align="center" class="style11"><strong>Dados 
                            da A&ccedil;&atilde;o de Cobran&ccedil;a</strong></div></td>
               </tr>
               <tr> 
                 <td height="31">
                  <table width="100%" bgcolor="#99CCFF">
                     <!--header da tabela interna -->
                     <tr bordercolor="#FFFFFF" bgcolor="#99CCFF">
                       <td  width="35%"> <div align="center" class="style9"><strong>A&ccedil;&atilde;o 
                           de Cobran&ccedil;a</strong></div></td>
                       <td width="35%"> <div align="center" class="style9"><strong>Predecessora</strong></div></td>
                       <td width="10%"> <div align="center" class="style9"><strong> 
                           Obrigat&oacute;ria</strong></div></td>

               		 </tr>
                 <!-- ______________________  -->  
                   <% String cor = "#cbe5fe";%>
			       <logic:notEmpty name="colecaoAcaoEAtividadeCobranca">
			       <logic:iterate name="colecaoAcaoEAtividadeCobranca" id="acaoEAtividadeCobrancaHelper">
			       <bean:define name="acaoEAtividadeCobrancaHelper" property="acaoCobranca" id="acaoCobranca" /> 
			       <bean:define name="acaoEAtividadeCobrancaHelper" property="atividadesCobranca" id="atividadesCobranca" />
		         <!-- ______________________  -->      
                         <%	if (cor.equalsIgnoreCase("#cbe5fe")){	
								cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF" height="18">	
							<%} else{	
								cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe" height="18">		
							<%}%>
                       
                       <td width="35%"> <a href="javascript:extendeTabelaAtividade('n<bean:write name="acaoCobranca" property="id" />','t<bean:write name="acaoCobranca" property="id" />');">${acaoCobranca.descricaoCobrancaAcao}</a> &nbsp;<br>

                       </td>
                       <td width="35%"> ${acaoCobranca.cobrancaAcaoPredecessora.descricaoCobrancaAcao} &nbsp;</td>
                       <td width="10%"> 
	                      <div align="center">
	                         <logic:equal name="acaoCobranca" property="indicadorObrigatoriedade" value="1">
	                           SIM
	                          </logic:equal>
	                         <logic:notEqual name="acaoCobranca" property="indicadorObrigatoriedade" value="1">
	                           NÃO
	                         </logic:notEqual>
	                      </div>
                       </td>
               		</tr>
               		<tr id="t<bean:write name="acaoCobranca" property="id" />" style="display:none;">
				      	<td colspan="4" id="t<bean:write name="acaoCobranca" property="id" />" >
				         <!-- _______________________________________________________________________________ -->      	
				      		<table width="100%" cellpadding="0" id="n<bean:write name="acaoCobranca" property="id" />" cellspacing="0" style="display:none;">
				              <tr>
				                <td height="0">
				                  <table width="100%" bgcolor="#99CCFF">
				                    <!--header da tabela interna -->
				                    <tr bordercolor="#000000">
					                     <%	if (cor.equalsIgnoreCase("#FFFFFF")){	%>
														<td bgcolor="#FFFFFF" width="5%">
											<%} else{%>
													<td bgcolor="#cbe5fe" width="5%">	
											<%}%>
				                      	&nbsp;</td>
                                      <td  width="10%"> <div align="center" class="style9"><strong>Comandar</strong></div></td>
				                      <td width="25%" bgcolor="#90c7fc" align="center"><div align="center"><strong>Atividade</strong></div></td>
				                      <td width="18%" bgcolor="#90c7fc" align="center"><div align="center"><strong>Qtd Máxima Documentos</strong></div></td>
				                      <td width="20%" bgcolor="#90c7fc" align="center"><div align="center"><strong>Predecessora</strong></div></td>
				                      <td width="25%" bgcolor="#90c7fc" align="center"><div align="center"><strong>Data Prevista</strong></div></td>
				                    </tr>
				                  </table></td>
				              </tr>
				          <logic:present name="atividadesCobranca">
				              <tr>
				                <td height="80">
				                  <div style="width: 100%; height: 100%; overflow: auto;">
				                    <table width="100%" align="center" bgcolor="#99CCFF">
				                      <!--corpo da segunda tabela-->
				                      <logic:notEmpty name="atividadesCobranca">
				                        <logic:iterate name="atividadesCobranca" id="atividadeCobranca">
				                        
				      
				                        
				                        
				                          <%	if (cor.equalsIgnoreCase("#FFFFFF")){	%>
													<tr bgcolor="#FFFFFF" height="18">	
														<td bgcolor="#FFFFFF" width="5%">
											<%} else{%>
												<tr bgcolor="#cbe5fe" height="18">	
													<td bgcolor="#cbe5fe" width="5%">	
											<%}%>
												&nbsp;</td>
 									         <td width="10%"> <div align="center"> 
					                      		<input name="comandar<bean:write name="acaoCobranca" property="id" />atividade<bean:write name="atividadeCobranca" property="id" />" type="checkbox" value="1" checked>
					                         </div></td>
				                              <td width="25%">
				                                <div align="left">
				                                   <bean:write name="atividadeCobranca" property="descricaoCobrancaAtividade"/>
				                                </div>
				                              </td>
				                              <td width="20%" align="center">
				                              <%
				                              if(!((CobrancaAtividade)atividadeCobranca).getId().equals(CobrancaAtividade.ENCERRAR) && ((CobrancaAtividade)atividadeCobranca).getId().equals(CobrancaAtividade.EMITIR)){
				                              %>				             
					                               		<logic:equal name="acaoCobranca" property="indicadorMetasCronograma" value="2">
					                              			<input type="text" maxlength="8" name="qtd_a<bean:write name="acaoCobranca" property="id"/>n<bean:write name="atividadeCobranca" property="id"/>" size="8" id="quantidadeMaximaDocumentos" onkeypress="return isCampoNumerico(event);" />				                     					                            				                                  	
					                              		</logic:equal>
					                              		<logic:equal  name="acaoCobranca" property="indicadorMetasCronograma" value="1">
					                                		<input type="text" maxlength="8" name="qtd_a<bean:write name="acaoCobranca" property="id"/>n<bean:write name="atividadeCobranca" property="id"/>" size="8" id="quantidadeMaximaDocumentos" disabled="disabled" onkeypress="return isCampoNumerico(event);"/>				                     					                            				                                  	  
					                                	</logic:equal>
					                              		<logic:notPresent name="acaoCobranca" property="indicadorMetasCronograma">
					                              			<input type="text" maxlength="8" name="qtd_a<bean:write name="acaoCobranca" property="id"/>n<bean:write name="atividadeCobranca" property="id"/>" size="8" id="quantidadeMaximaDocumentos" onkeypress="return isCampoNumerico(event);" />				                     					                            				                                  	
					                              		</logic:notPresent>
				                              <%
				                              } else if(!((CobrancaAtividade)atividadeCobranca).getId().equals(CobrancaAtividade.ENCERRAR) && ((CobrancaAtividade)atividadeCobranca).getId().equals(CobrancaAtividade.SIMULAR)) {
				                              %>
				                            	  <input type="text" maxlength="8" name="qtd_a<bean:write name="acaoCobranca" property="id"/>n<bean:write name="atividadeCobranca" property="id"/>" size="8" id="quantidadeMaximaDocumentos" onkeypress="return isCampoNumerico(event);"/>				                     					                            				                                  					                      				                 			                     					                            				                                  					                      
				                              <%
				                              } else{
				                            	  out.println("&nbsp;");
				                              }
				                              %>
				                              				                  
				                              </td>
				                              <td width="20%">
										        <div align="left">
												  <logic:present name="atividadeCobranca" property="cobrancaAtividadePredecessora">
				                                   <bean:write name="atividadeCobranca" property="cobrancaAtividadePredecessora.descricaoCobrancaAtividade"/>
				                                  </logic:present>
								  				  <logic:notPresent name="atividadeCobranca" property="cobrancaAtividadePredecessora">
				                                   &nbsp;
				                                  </logic:notPresent>
												</div>
				                              </td>
				                              <td width="25%" align="center">
				                                  <input type="text" maxlength="10" 
				                                  name="a<bean:write name="acaoCobranca" property="id"/>n<bean:write name="atividadeCobranca" property="id"/>" 
				                                  	value="<bean:write name="atividadeCobranca" property="dataPrevistaFormatada"/>" 
				                                  size="10" id="data" onkeypress="validaDataCompleta(this, event);return isCampoNumerico(event);" />
				                                    <a href="javascript:abrirCalendario('CobrancaActionForm', 'a<bean:write name="acaoCobranca" property="id"/>n<bean:write name="atividadeCobranca" property="id"/>')">
				                                      <img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" title="Exibir Calendário"/>
				                                    </a>
				                              </td>
				                            </tr>
				                        </logic:iterate>
				                      </logic:notEmpty>
				                    </table>
				                </div>
				    	       </td>
				              </tr>
				            </logic:present>
				            </table>
				         <!-- _______________________________________________________________________________ -->
				      	</td>
				    </tr>
                 </logic:iterate>
			     </logic:notEmpty> 
          </table>
        </td>
      </tr>
   </table>
			<table width="100%">
				<tr height="3">
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
                 <tr>
				   <td align="left" >
				      <input type="button" class="bottonRightCol" value="Limpar" 
					      onClick="limparForm();">
				      <input type="button" class="bottonRightCol" value="Cancelar" property="cancelar"
					      onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
				   </td>
                   <td align="right">
                     <gsan:controleAcessoBotao name="adicionar" value="Inserir" onclick="javascript:validarForm(document.forms[0]);" url="inserirCobrancaCronogramaAction.do"/>
                     <%--<html:submit styleClass="bottonRightCol" value="Inserir" property="adicionar" />--%>
                   </td>
                 </tr>
              </table>
    </td>
</tr>
</table>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
