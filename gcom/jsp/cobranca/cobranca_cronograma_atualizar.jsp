<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ page import="gcom.cobranca.CobrancaAcaoAtividadeCronograma" %>
<%@ page import="gcom.cobranca.CobrancaAtividade" %>


<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="CobrancaActionForm" dynamicJavascript="false"/>
<script language="JavaScript">

function validarForm(){
	var form = document.CobrancaActionForm;
	botaoDesabilitaSemValidacao(form);
	if (validarCamposDinamicos(form) && validateLong(form)){
		submeterFormPadrao(form);
	}
}

function validaDataCompleta(data, event){
		if(mascaraData(data, event)){
			return false;
		}
}
</script>
<script language="JavaScript">
function validaData(form){
  var mesAno = form.mesAno.value;
  if(mascaraAnoMes(mesAno)){
     return false;
  }
}

function IntegerValidations () {
	this.ab = new Array("quantidadeMaximaDocumentos", "Quantidade máxima de documentos deve somente conter números positivos.", new Function ("varName", " return this[varName];"));     	
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
						if(!verificaDataMensagem(form.elements[i], "Data Prevista inválida.")){
							camposValidos = false;
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

function extendeTabelaAtividade(valor,valor2){

 var valor = document.getElementById(valor);
 var valor2 = document.getElementById(valor2);

 if(valor.style.display == "none"){
	valor2.style.display = "";
	valor.style.display = "";
 }else{
 	valor2.style.display = "none";
 	valor.style.display = "none";
 }
 
}

</script>

</head>

<body leftmargin="5" topmargin="5" >
<html:form
  action="/atualizarCobrancaCronogramaAction"
  name="CobrancaActionForm"
  type="gcom.gui.cobranca.CobrancaActionForm"
  method="post"
  onsubmit="return validarCamposDinamicos(this) && validaMesAnoVazio(this);"
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
                <td class="parabg">Atualizar Cronograma de Cobrança</td>
                <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
              </tr>
            </table>
            <!--Fim Tabela Reference a P&aacute;gina&ccedil;&atilde;o da Tela de Processo-->
            <p>&nbsp;</p>
            <table width="100%" border="0" >

                <tr>
                  <td colspan="2">Para atualizar o(s) cronograma(s) de cobrança,
                    informe os dados abaixo:</td>
                </tr>
                <tr>
                  <td><strong>Grupo:<font color="#FF0000">*</font></strong> </td>
                  <td>
                    <html:select property="idGrupoCobranca" disabled="true">
  		       <html:options collection="colecaoCobrancaGrupo" labelProperty="descricao" property="id"/>
                    </html:select>
		  		</td>
                </tr>
                <tr>
                  <td><strong>Mês/Ano:<font color="#FF0000">*</font></strong></td>
                  <td><html:text maxlength="7" property="mesAno" size="7" onkeyup="mascaraAnoMes(this, event);" disabled="true"/>
                    &nbsp; mm/aaaa</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td align="left">&nbsp;
                  </td>
                </tr>
                <tr>
                  <td><p>&nbsp;</p></td>
                  <td> </td>
                </tr>

            </table>
                
          
            <table width="100%" cellpadding="0" cellspacing="0">
            	<tr> 
                   <td bgcolor="#1888F8"><div align="center" class="style11"><strong>Dados 
                            da A&ccedil;&atilde;o de Cobran&ccedil;a</strong></div></td>
               </tr>
               <tr> 
                 <td height="31">
                  <table width="100%" bgcolor="#99CCFF">
                     <!--header da tabela interna -->
                     <tr bordercolor="#FFFFFF" bgcolor="#99CCFF">
					   <td  width="12%"> <div align="center" class="style9"><strong>Remover</strong></div></td>
                       <td  width="32%"> <div align="center" class="style9"><strong>A&ccedil;&atilde;o 
                           de Cobran&ccedil;a</strong></div></td>
                       <td width="32%"> <div align="center" class="style9"><strong>Predecessora</strong></div></td>
                       <td width="8%"> <div align="center" class="style9"><strong> 
                           Obrigat&oacute;ria</strong></div></td>

               		 </tr>
                 <!-- ______________________  -->  
                   <% String cor = "#cbe5fe";%>
			       <logic:notEmpty name="colecaoCobrancaCronogramaHelper">
			       <logic:iterate name="colecaoCobrancaCronogramaHelper" id="cobrancaCronogramaHelper">
		         <!-- ______________________  -->      
                          <%	if (cor.equalsIgnoreCase("#cbe5fe")){	
								cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF" height="18">	
							<%} else{	
								cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe" height="18">		
							<%}%>
                          
                          <td width="14%" align="center">
                          
                          	<logic:equal name="desabilitaAlteracao" value="1" scope="request">
	                          	&nbsp;
                          	</logic:equal>
                          	
                            <logic:notEqual name="desabilitaAlteracao" value="1" scope="request">
	                          <logic:equal name="cobrancaCronogramaHelper" property="cobrancaAcaoCronograma.cobrancaAcao.indicadorObrigatoriedade" value="2">
		                           <input name="idRegistrosRemocao" type="checkbox" value="<bean:write name="cobrancaCronogramaHelper" property="cobrancaAcaoCronograma.cobrancaAcao.id" />" >
	                           </logic:equal>
	                           
	                          <logic:notEqual name="cobrancaCronogramaHelper" property="cobrancaAcaoCronograma.cobrancaAcao.indicadorObrigatoriedade" value="2">
	                          	&nbsp;
	                          </logic:notEqual>
	                        </logic:notEqual>
	                        
                          </td>
                          
                       	  <td width="35%"> 
                       	  	<div align="left"><a href="javascript:extendeTabelaAtividade('n<bean:write name="cobrancaCronogramaHelper" property="cobrancaAcaoCronograma.cobrancaAcao.id" />','t<bean:write name="cobrancaCronogramaHelper" property="cobrancaAcaoCronograma.cobrancaAcao.id" />');">${cobrancaCronogramaHelper.cobrancaAcaoCronograma.cobrancaAcao.descricaoCobrancaAcao}</a> &nbsp;<br>
                          </div></td>
                          
                          <td width="35%"> 
	                          <div align="left">${cobrancaCronogramaHelper.cobrancaAcaoCronograma.cobrancaAcao.cobrancaAcaoPredecessora.descricaoCobrancaAcao} &nbsp;</div>
                          </td>
                          
	                       <td width="10%"> 
		                      <div align="center">
		                         <logic:equal name="cobrancaCronogramaHelper" property="cobrancaAcaoCronograma.cobrancaAcao.indicadorObrigatoriedade" value="1">
		                           SIM
		                          </logic:equal>
		                         <logic:notEqual name="cobrancaCronogramaHelper" property="cobrancaAcaoCronograma.cobrancaAcao.indicadorObrigatoriedade" value="1">
		                           NÃO
		                         </logic:notEqual>
		                      </div>
	                       </td>
	                       
               		</tr>
               		<tr id="t<bean:write name="cobrancaCronogramaHelper" property="cobrancaAcaoCronograma.cobrancaAcao.id" />" style="display:none;">
				      	<td colspan="5" id="t<bean:write name="cobrancaCronogramaHelper" property="cobrancaAcaoCronograma.cobrancaAcao.id" />" >
				         <!-- _______________________________________________________________________________ -->      	
				      		<table width="100%" cellpadding="0" id="n<bean:write name="cobrancaCronogramaHelper" property="cobrancaAcaoCronograma.cobrancaAcao.id" />" cellspacing="0" style="display:none;">
				              <tr>
				                <td height="0">
				                  <table width="100%" bgcolor="#99CCFF">
				                    <!--header da tabela interna -->
				                    <tr bordercolor="#000000">
					                    <%	if (cor.equalsIgnoreCase("#FFFFFF")){%>
											<td width="3%" bgcolor="#FFFFFF" align="center"><div align="center"><strong>&nbsp;</strong></div></td>
										<%} else{%>
											<td width="3%" bgcolor="#cbe5fe" align="center"><div align="center"><strong>&nbsp;</strong></div></td>
										<%}%>
				                      <td width="5%" bgcolor="#90c7fc" align="center"><div align="center"><strong>Comandar</strong></div></td>
				                      <td width="20%" bgcolor="#90c7fc" align="center"><div align="center"><strong>Atividade</strong></div></td>
				                      <td width="18%" bgcolor="#90c7fc" align="center"><div align="center"><strong>Qtd Máxima Documentos</strong></div></td>				                      
				                      <td width="20%" bgcolor="#90c7fc" align="center"><div align="center"><strong>Predecessora</strong></div></td>
				                      <td width="20%" bgcolor="#90c7fc" align="center"><div align="center"><strong>Data Prevista</strong></div></td>
				                      <td width="12%" bgcolor="#90c7fc" align="center"><div align="center"><strong>Data Realizada</strong></div></td>
				                    </tr>
				                  </table></td>
				              </tr>
				          <logic:present name="cobrancaCronogramaHelper" property="cobrancasAtividadesParaInsercao">
				              <tr>
				                <td height="80">
				                  <div style="width: 100%; height: 100%; overflow: auto;">
				                    <table width="100%" align="center" bgcolor="#99CCFF">
				                      <!--corpo da segunda tabela-->
				                      <logic:notEmpty name="cobrancaCronogramaHelper" property="cobrancasAtividadesParaInsercao">
				                        <logic:iterate name="cobrancaCronogramaHelper" property="cobrancasAtividadesParaInsercao" id="atividadeCobranca">
				                          <%	if (cor.equalsIgnoreCase("#FFFFFF")){%>
												<tr bgcolor="#FFFFFF" height="18">	
											<%} else{%>
												<tr bgcolor="#cbe5fe" height="18">		
											<%}%>
											
											   <td width="3%" align="center"><div align="center"><strong>&nbsp;</strong></div></td>
											   
											   <td width="10%" align="center"> 
											    <logic:notPresent name="atividadeCobranca" property="realizacao">
						                       		<logic:present name="atividadeCobranca" property="comando">
							                           <input name="comandar<bean:write name="cobrancaCronogramaHelper" property="cobrancaAcaoCronograma.cobrancaAcao.id" />atividade<bean:write name="atividadeCobranca" property="cobrancaAtividade.id"/>" type="checkbox" value="1" checked>
							                        </logic:present>
							                  		<logic:notPresent name="atividadeCobranca" property="comando">
							                           <input name="comandar<bean:write name="cobrancaCronogramaHelper" property="cobrancaAcaoCronograma.cobrancaAcao.id" />atividade<bean:write name="atividadeCobranca" property="cobrancaAtividade.id"/>" type="checkbox" value="1">
							                        </logic:notPresent>
							                    </logic:notPresent>
							                    <logic:present name="atividadeCobranca" property="realizacao">    
							                     <logic:present name="atividadeCobranca" property="comando">
							                           <input name="comandar<bean:write name="cobrancaCronogramaHelper" property="cobrancaAcaoCronograma.cobrancaAcao.id" />atividade<bean:write name="atividadeCobranca" property="cobrancaAtividade.id"/>" type="checkbox" value="1" checked disabled="true">
							                        </logic:present>
							                  		<logic:notPresent name="atividadeCobranca" property="comando">
							                           <input name="comandar<bean:write name="cobrancaCronogramaHelper" property="cobrancaAcaoCronograma.cobrancaAcao.id" />atividade<bean:write name="atividadeCobranca" property="cobrancaAtividade.id"/>" type="checkbox" value="1" disabled="true">
							                     </logic:notPresent>
							                    </logic:present>
						                       </td>
						                       
				                              <td width="20%">
				                                <div align="left">
				                                   <bean:write name="atividadeCobranca" property="cobrancaAtividade.descricaoCobrancaAtividade"/>
				                                </div>
				                              </td>
				                              
				                              <td width="20%" align="center">
					                              <bean:define name="cobrancaCronogramaHelper" property="cobrancaAcaoCronograma.cobrancaAcao" id="acaoCobranca" />
					                              <logic:notPresent name="atividadeCobranca" property="realizacao">
					                              <%
					                              if(!((CobrancaAcaoAtividadeCronograma)atividadeCobranca).getCobrancaAtividade().getId()
					                            		  .equals(CobrancaAtividade.ENCERRAR)){
					                              %>
					                              
					                              <logic:equal name="desabilitaAlteracao" value="1" scope="request">
					                              		<input type="text" maxlength="8" name="qtd_a<bean:write name="acaoCobranca" property="id"/>n<bean:write name="atividadeCobranca" property="cobrancaAtividade.id"/>"
	 				                                      	value="<bean:write name="atividadeCobranca" property="quantidadeMaximaDocumentos" />" size="8" id="quantidadeMaximaDocumentos" disabled="disabled" onkeypress="return isCampoNumerico(event)" />
					                              </logic:equal>
					                              
					                              <logic:notEqual name="desabilitaAlteracao" value="1" scope="request">
					                              		<logic:equal name="cobrancaCronogramaHelper" property="cobrancaAcaoCronograma.cobrancaAcao.indicadorMetasCronograma" value="2">
						                              			<input type="text" maxlength="8" name="qtd_a<bean:write name="acaoCobranca" property="id"/>n<bean:write name="atividadeCobranca" property="cobrancaAtividade.id"/>"
	 				                                      			value="<bean:write name="atividadeCobranca" property="quantidadeMaximaDocumentos" />" size="8" id="quantidadeMaximaDocumentos" onkeypress="return isCampoNumerico(event)"/>
						                              	</logic:equal>
						                              	<logic:equal name="cobrancaCronogramaHelper" property="cobrancaAcaoCronograma.cobrancaAcao.indicadorMetasCronograma" value="1">
						                              			<input type="text" maxlength="8" name="qtd_a<bean:write name="acaoCobranca" property="id"/>n<bean:write name="atividadeCobranca" property="cobrancaAtividade.id"/>"
	 				                                      			value="<bean:write name="atividadeCobranca" property="quantidadeMaximaDocumentos" />" size="8" id="quantidadeMaximaDocumentos" disabled="disabled" onkeypress="return isCampoNumerico(event)" />
						                              	</logic:equal>
						                              	<logic:notPresent name="cobrancaCronogramaHelper" property="cobrancaAcaoCronograma.cobrancaAcao.indicadorMetasCronograma">
						                              			<input type="text" maxlength="8" name="qtd_a<bean:write name="acaoCobranca" property="id"/>n<bean:write name="atividadeCobranca" property="cobrancaAtividade.id"/>"
	 				                                      			value="<bean:write name="atividadeCobranca" property="quantidadeMaximaDocumentos" />" size="8" id="quantidadeMaximaDocumentos" onkeypress="return isCampoNumerico(event)"/>
						                              	</logic:notPresent> 
					                              </logic:notEqual>
					                                 
					                              <%
					                              } else {
					                            	  out.println("&nbsp;");
					                              }
					                              %>
					                              </logic:notPresent>
					                              <logic:present name="atividadeCobranca" property="realizacao">
					                              <%
					                              if(!((CobrancaAcaoAtividadeCronograma)atividadeCobranca).getCobrancaAtividade().getId()
					                            		  .equals(CobrancaAtividade.ENCERRAR)){
					                              %>  				
						                              			<input type="text" maxlength="8" name="qtd_a<bean:write name="acaoCobranca" property="id"/>n<bean:write name="atividadeCobranca" property="cobrancaAtividade.id"/>"
	 				                                      			value="<bean:write name="atividadeCobranca" property="quantidadeMaximaDocumentos" />" size="8" id="quantidadeMaximaDocumentos" disabled="disabled" />
					                              <%
					                              } else {
					                            	  out.println("&nbsp;");
					                              }
					                              %>
					                              </logic:present>
				                              </td>
				                              
				                              <td width="20%">
										        <div align="left">
										        
										        <logic:empty name="atividadeCobranca" property="cobrancaAtividade.cobrancaAtividadePredecessora">
										        	${atividadeCobranca.cobrancaAtividade.cobrancaAtividadePredecessora.descricaoCobrancaAtividade}
										        </logic:empty>
										        
												  
				                                   &nbsp;
												</div>
				                              </td>
				                              
				                              <td width="20%">
												<div align="center">
												  <logic:notPresent name="atividadeCobranca" property="realizacao">
												  
													  <logic:equal name="desabilitaAlteracao" value="1" scope="request">
							                              <input type="text" maxlength="10" 
						                                  	name="a<bean:write name="atividadeCobranca" property="cobrancaAcaoCronograma.cobrancaAcao.id"/>n<bean:write name="atividadeCobranca" property="cobrancaAtividade.id"/>" 
						                                  	size="10" id="data" 
						                                  	onkeypress="validaDataCompleta(this, event);return isCampoNumerico(event);" 
						                                  	value="<bean:write name="atividadeCobranca" property="dataPrevista" formatKey="date.format"/>" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					    	                               
						                              </logic:equal>
						                              
						                              <logic:notEqual name="desabilitaAlteracao" value="1" scope="request">
							                              <input type="text" maxlength="10" 
						                                  	name="a<bean:write name="atividadeCobranca" property="cobrancaAcaoCronograma.cobrancaAcao.id"/>n<bean:write name="atividadeCobranca" property="cobrancaAtividade.id"/>" 
						                                  	size="10" id="data" 
						                                  	onkeypress="validaDataCompleta(this, event);return isCampoNumerico(event);" 
						                                  	value="<bean:write name="atividadeCobranca" property="dataPrevista" formatKey="date.format"/>">
					    	                                <a href="javascript:abrirCalendario('CobrancaActionForm', 'a<bean:write name="atividadeCobranca" property="cobrancaAcaoCronograma.cobrancaAcao.id"/>n<bean:write name="atividadeCobranca" property="cobrancaAtividade.id"/>')">
					        	                              <img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" title="Exibir Calendário"/>
					            	                        </a>
						                              </logic:notEqual>
					                                  
				            	                   </logic:notPresent>
				            	                   <logic:present name="atividadeCobranca" property="realizacao">
					            	                   	<input type="text" maxlength="10" 
					                                  	name="a<bean:write name="atividadeCobranca" property="cobrancaAcaoCronograma.cobrancaAcao.id"/>n<bean:write name="atividadeCobranca" property="cobrancaAtividade.id"/>" 
					                                  	size="10" id="data" 
					                                  	onkeypress="validaDataCompleta(this, event);return isCampoNumerico(event);" 
					                                  	value="<bean:write name="atividadeCobranca" property="dataPrevista" formatKey="date.format"/>" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				            	                   </logic:present>
												</div>
				                              </td>
				                              
				                              <td width="12%">
				                                <div align="center">
				                                   <bean:write name="atividadeCobranca" property="realizacao" formatKey="date.format"/>
				                                </div>				                              
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
					<td colspan="2" align="right">
						<input type="button" class="bottonRightCol" value="Adicionar Ação de Cobrança"
					      onClick="javascript:abrirPopup('exibirAdicionarAcaoCobrancaCronogramaPopupAction.do?limpar=sim&reload=S', 400, 800);">
					</td>
				</tr>
				<tr>
				   <td colspan="3"><hr></td>
				</tr>
                 <tr>
				   <td align="left" >
				   	 <logic:present name="voltaFiltro">
					  <input type="button" class="bottonRightCol" value="Voltar" 
					      onClick="javascript:window.location.href='/gsan/exibirFiltrarCobrancaCronogramaAction.do?menu=sim'">
					 </logic:present>
					 <logic:notPresent name="voltaFiltro">
					 	<input type="button" class="bottonRightCol" value="Voltar" 
					      onClick="javascript:history.back();">
					 </logic:notPresent>
				      <input type="button" class="bottonRightCol" value="Desfazer" 
					      onClick="javascript:(document.forms[0]).reset();window.location.href='/gsan/exibirAtualizarCobrancaCronogramaAction.do';">
				      <input type="button" class="bottonRightCol" value="Cancelar" property="cancelar"
					      onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
				   </td>
                   <td align="right">
                     <gsan:controleAcessoBotao name="botaoAtualizar" value="Atualizar" onclick="javascript:validarForm();" url="atualizarCobrancaCronogramaAction.do"/>
                     <%--<html:submit styleClass="bottonRightCol" value="Atualizar" property="adicionar"/>--%>
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
