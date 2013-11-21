<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="gcom.util.ConstantesSistema" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@page import="gcom.gui.faturamento.FaturamentoCronogramaAtividadeHelp"%>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FaturamentoActionForm"/>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript"></script>
<script language="JavaScript">
function validaDataCompleta(data, event){
		if(mascaraData(data, event)){
			return false;
		}
}
</script>
<script language="JavaScript">
function validaData(form){
  var form = document.FaturamentoActionForm;
  var mesAno = form.mesAno.value;
  if(mascaraAnoMes(mesAno)){
     return false;
  }
}

function validarForm(){
	var form = document.FaturamentoActionForm;
	var idsAtividadesComandadas = "";
	if(validateFaturamentoActionForm(form) && validarCamposDinamicos(form)){
	
		for (i=0; i < form.elements.length; i++) {
    		
	    	if (form.elements[i].type == "checkbox"){
	    		if(form.elements[i].checked){	
					idsAtividadesComandadas += form.elements[i].name + "-";
				}
			}
		}
		
		form.action = "/gsan/inserirFaturamentoCronogramaAction.do?idsAtividadesComandadas="+idsAtividadesComandadas;
		submeterFormPadrao(form);
	}
}

function validarCamposDinamicos(form){
 
 	var camposValidos = true;
 	
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

function reload(reload,id,data){
	var form = document.FaturamentoActionForm;

	var indice ='i'+id;
	
	if(reload=='sim'){
		
		document.getElementById(indice).value = data;
	}
}

function restart() {

	document.forms[nomeForm].elements[nomeCampo].value = '' + padout(day) + '/' + padout(month - 0 + 1) + '/' + year;
	document.forms[nomeForm].elements[nomeCampo].focus();
   
	calcularQuantidadeDias(document.forms[nomeForm].elements[nomeCampo].name.substring(1));
}

function habilitarCamposDatasPrevistas(carregar){
	
	var form = document.FaturamentoActionForm;
	
	if(form.idGrupoFaturamento.value != '-1' &&
	   form.mesAno.value.length == 7){
		
		for(indice = 0; indice < form.elements.length; indice++){
		
			if (form.elements[indice].type == "text" &&
				form.elements[indice].name.substr(0, 1) == 'n') {
				
				form.elements[indice].readOnly = false;
				
				calcularQuantidadeDias(form.elements[indice].name.substring(1));
			}
		}
		
		if (carregar){
			
			form.action = "/gsan/exibirInserirFaturamentoCronogramaAction.do?obterDataRealizacaoAnterior=OK";
	  		form.submit();
		}
		
	}
	else if (form.idGrupoFaturamento.value == '-1' ||
		form.mesAno.value.length != 7){
		
		for(indice = 0; indice < form.elements.length; indice++){
		
			if (form.elements[indice].type == "text" &&
				form.elements[indice].name.substr(0, 1) == 'n') {
				
				form.elements[indice].readOnly = true;
				form.elements[indice].value = "";
				
				calcularQuantidadeDias(form.elements[indice].name.substring(1));
			}
		}
	}
}

function habilitarCalendario(idAtividade){

	var atividade = eval('i' + idAtividade);
	
	if (!atividade.readOnly){
		abrirCalendario('FaturamentoActionForm', 'n' + idAtividade);
	}
	else{
		
		var form = document.FaturamentoActionForm;
		
		var msg = "";
		
		if (form.idGrupoFaturamento.value == '-1'){
			msg = "Informe Grupo \n"
		}
		
		if (form.mesAno.value.length < 7){
			msg = msg + "Informe Mês/Ano";
		}
		
		alert(msg);
	}
}

function calcularQuantidadeDias(idAtividade){
	
	var dataPrevistaDigitada = eval('i' + idAtividade); 
	var qtdDiasAtividade = eval('qtdDias' + idAtividade);
	
	if (dataPrevistaDigitada.value.length == 10){
		
		if (validaDataSemMensagem(dataPrevistaDigitada)){
			
			var dataAtividadeRealizacaoMesAnterior = eval('dataAnterior' + idAtividade); 
			
			
			if (dataAtividadeRealizacaoMesAnterior.value.length == 10 &&
				comparaData(dataPrevistaDigitada.value, ">", dataAtividadeRealizacaoMesAnterior.value)){
				
				qtdDiasAtividade.innerHTML = diasEntreDatas(dataAtividadeRealizacaoMesAnterior.value,
				dataPrevistaDigitada.value);
			}
			else{
			
				qtdDiasAtividade.innerHTML = "0";
			}
		}
	}
	else{
		qtdDiasAtividade.innerHTML = "0";
	}
}
</script>

</head>

<body leftmargin="5" topmargin="5" >
<html:form
  action="/inserirFaturamentoCronogramaAction"
  name="FaturamentoActionForm"
  type="gcom.gui.faturamento.FaturamentoActionForm"
  method="post"
  onsubmit="return validateFaturamentoActionForm(this) && validarCamposDinamicos(this);"
>

<input type="hidden" id="dataAtual" value="${requestScope.dataAtual}">

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
                <td class="parabg">Inserir Cronograma de Faturamento</td>
                <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
              </tr>
            </table>
            <!--Fim Tabela Reference a P&aacute;gina&ccedil;&atilde;o da Tela de Processo-->
            <p>&nbsp;</p>
            <table width="100%" border="0" >

                <tr>
                  	<td>Para adicionar o(s) cronograma(s) de faturamento,
                    informe primeiramente o grupo e a referência do mesmo para que os campos correspondentes a 
                    data prevista de execução para cada atividade fique disponível:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoCronogramaInserir', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
                </table>
                <table width="100%" border="0" >
                <tr>
                  <td><strong>Grupo:<font color="#FF0000">*</font></strong> </td>
                  <td>
                    <html:select property="idGrupoFaturamento" onchange="habilitarCamposDatasPrevistas(true);">
                    <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
  		       		<html:options collection="faturamentoGrupos" labelProperty="descricao" property="id"/>
                    </html:select>
		  		 </td>
                </tr>
                
                <tr>
                  <td><strong>Mês/Ano:<font color="#FF0000">*</font></strong></td>
                  <td><html:text maxlength="7" property="mesAno" size="7" 
                  onkeypress="javascript:return isCampoNumerico(event);"
                  onkeyup="mascaraAnoMes(this, event); habilitarCamposDatasPrevistas(true);"
                  onblur="habilitarCamposDatasPrevistas(true);"/>
                    &nbsp; mm/aaaa</td>
                </tr>
                
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
                <td height="0">
                  <table width="100%" bgcolor="#99CCFF">
                    <!--header da tabela interna -->
                    <tr bordercolor="#000000">
                      <td width="10%" bgcolor="#90c7fc" align="center"><div align="center"><strong>Comandar</strong></div></td>
                      <td width="20%" bgcolor="#90c7fc" align="center"><div align="center"><strong>Atividade</strong></div></td>
                      <td width="20%" bgcolor="#90c7fc" align="center"><div align="center"><strong>Predecessora</strong></div></td>
                      <td width="15%" bgcolor="#90c7fc" align="center"><div align="center"><strong>Obrigat&oacute;ria</strong></div></td>
                      <td width="22%" bgcolor="#90c7fc" align="center"><div align="center"><strong>Data Prevista</strong></div></td>
                      <td width="20%" bgcolor="#90c7fc" align="center"><div align="center"><strong>Qtd. Dias</strong></div></td>	 
                    </tr>
                  </table></td>
              </tr>
          	
          	<logic:present name="faturamentoAtividades">
            
              <tr>
                <td height="126">
                  <div style="width: 100%; height: 100%; overflow: auto;">
                    <table width="100%" align="center" bgcolor="#99CCFF">
                      <!--corpo da segunda tabela-->
                      <%int cont=0;%>
                      <logic:notEmpty name="faturamentoAtividades">
                        <logic:iterate name="faturamentoAtividades" type="FaturamentoCronogramaAtividadeHelp" id="faturamentoAtividade">
                          <%
                               cont = cont+1;
                            if (cont%2==0){%>
                              <tr bgcolor="#cbe5fe">
                            <%}else{ %>
                              <tr bgcolor="#FFFFFF">                              
                          <%}%>
                          	  <td width="10%">
                          	  	<logic:notPresent name="desabilitaCheck" scope="session">
	                          	  	<logic:present name="faturamentoAtividade" property="faturamentoAtividade.indicadorPossibilidadeComandoAtividade">
	                          	  		<logic:equal name="faturamentoAtividade" property="faturamentoAtividade.indicadorPossibilidadeComandoAtividade" value="1">
			                                <logic:present name="faturamentoAtividade" property="comandar">
	                          	  				<logic:equal name="faturamentoAtividade" property="comandar" value="1">
					                                <div align="center">
					                                   <input type="checkbox" name="c${faturamentoAtividade.faturamentoAtividade.id}" />
					                                </div>
					                            </logic:equal>
					                            <logic:equal name="faturamentoAtividade" property="comandar" value="2">
					                                <div align="center"></div>
					                            </logic:equal>
					                            <logic:equal name="faturamentoAtividade" property="comandar" value="3">
					                                <div align="center">
					                                   <input type="checkbox" name="c${faturamentoAtividade.faturamentoAtividade.id}" checked="checked"/>
					                                </div>
					                            </logic:equal>
					                        </logic:present>
			                            </logic:equal>
			                            <logic:equal name="faturamentoAtividade" property="faturamentoAtividade.indicadorPossibilidadeComandoAtividade" value="3">
			                            	<logic:equal name="faturamentoAtividade" property="comandar" value="3">
				                                <div align="center">
				                                   <input type="checkbox" name="c${faturamentoAtividade.faturamentoAtividade.id}" checked="checked"/>
				                                </div>
					                    	</logic:equal>
			                            </logic:equal>
		                            </logic:present>
	                            </logic:notPresent>
                              </td>
                              
                              <td width="20%">
                                <div align="left">
                                   <bean:write name="faturamentoAtividade" property="faturamentoAtividade.descricao"/>
                                   
                                   <input type="hidden" id="dataAnterior${faturamentoAtividade.faturamentoAtividade.id}" value="${faturamentoAtividade.dataRealizacaoMesAnterior}">
                                </div>
                              </td>
                              
                              <td width="20%">
			        			<div align="left">
				  					
				  					<logic:present name="faturamentoAtividade" property="faturamentoAtividade.faturamentoAtividadePrecedente">
                                   		<bean:write name="faturamentoAtividade" property="faturamentoAtividade.faturamentoAtividadePrecedente.descricao"/>
                                  	</logic:present>
				  					
				  					<logic:notPresent name="faturamentoAtividade" property="faturamentoAtividade.faturamentoAtividadePrecedente">
                                   	&nbsp;
                                  	</logic:notPresent>
								</div>
                              </td>
                              
                              <td width="15%">
                                 <div align="center">
                                    <logic:equal name="faturamentoAtividade" property="faturamentoAtividade.indicadorObrigatoriedadeAtividade" value="1">
                                      SIM
                                    </logic:equal>
                                    <logic:notEqual name="faturamentoAtividade" property="faturamentoAtividade.indicadorObrigatoriedadeAtividade" value="1">
                                      NÃO
                                    </logic:notEqual>
                                 </div>
                              </td>
                              
                              <logic:empty name="faturamentoAtividade" property="dataPrevista"> 
                              
	                              <td width="24%">
									<div align="center">
	                                  
	                                  <input type="text" maxlength="10" name="n<bean:write name="faturamentoAtividade" property="faturamentoAtividade.id"/>" size="10" id="i<bean:write name="faturamentoAtividade" property="faturamentoAtividade.id"/>" 
	                                  onkeypress="validaDataCompleta(this, event);return isCampoNumerico(event);" readonly="true"
	                                  onkeyup="calcularQuantidadeDias('<bean:write name="faturamentoAtividade" property="faturamentoAtividade.id"/>');"
                  					  onblur="calcularQuantidadeDias('<bean:write name="faturamentoAtividade" property="faturamentoAtividade.id"/>');"/>
	                                  
	                                  <a href="javascript:habilitarCalendario('<bean:write name="faturamentoAtividade" property="faturamentoAtividade.id"/>')">
	                                      <img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="middle" title="Exibir Calendário"/>
	                                  </a>
									
									</div>
	                              </td>
                              
                              </logic:empty>
                              
                              <logic:notEmpty name="faturamentoAtividade" property="dataPrevista"> 
                              
	                              <td width="24%">
									<div align="center">
	                                  
	                                  <input type="text" maxlength="10" name="n<bean:write name="faturamentoAtividade" property="faturamentoAtividade.id"/>" size="10" id="i<bean:write name="faturamentoAtividade" property="faturamentoAtividade.id"/>" 
	                                  onkeypress="validaDataCompleta(this, event);return isCampoNumerico(event);" value="<bean:write name="faturamentoAtividade" property="dataPrevista"/>"
	                                  readonly="true"
	                                  onkeyup="calcularQuantidadeDias('<bean:write name="faturamentoAtividade" property="faturamentoAtividade.id"/>');"
                  					  onblur="calcularQuantidadeDias('<bean:write name="faturamentoAtividade" property="faturamentoAtividade.id"/>');"/>
	                                  
	                                  <a href="javascript:habilitarCalendario('<bean:write name="faturamentoAtividade" property="faturamentoAtividade.id"/>')">
	                                      <img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="middle" title="Exibir Calendário"/>
	                                  </a>
									
									</div>
	                              </td>                        
                              
                              </logic:notEmpty>
                              
                              
                              <td width="10%">
                              <div align="center" >
                             		<strong><span id="qtdDias${faturamentoAtividade.faturamentoAtividade.id}">0</span></strong>
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
            <p>
              <table width="100%">
                 <tr>
                   <td width="50%">
                   		<input name="Button" 
                   			type="button" 
                   			class="bottonRightCol" 
                   			value="Desfazer" 
                   			align="left"
							onclick="window.location.href='<html:rewrite page="/exibirInserirFaturamentoCronogramaAction.do?menu=sim"/>'">
						&nbsp;
						<input name="Button" 
							type="button" 
							class="bottonRightCol"
							value="Cancelar" 
							align="left"
							onclick="window.location.href='<html:rewrite page="/telaPrincipal.do"/>'">
				   	</td>
                   <td align="right">
                   	<gsan:controleAcessoBotao name="Button" value="Inserir"
							  onclick="javascript:validarForm();" url="inserirFaturamentoCronogramaAction.do"/>
                   	<%--<html:submit styleClass="bottonRightCol" value="Inserir" property="adicionar"/>--%>
                   	</td>
                 </tr>
              </table>
        </tr>
   </table>
<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>

<script> habilitarCamposDatasPrevistas(false); </script>

</html:html>
