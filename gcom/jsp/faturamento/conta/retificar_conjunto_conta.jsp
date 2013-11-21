<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.ConstantesSistema" %>
<%@ page import="gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao" %>
<%@ page import="gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao" %>


<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="RetificarConjuntoContaActionForm"/>

<SCRIPT LANGUAGE="JavaScript">
<!--

function validarForm(form){

	if (validateRetificarConjuntoContaActionForm(form)){
	
		var msgDataVencimento = "Data de Vencimento anterior à data corrente.";
		var msgDataVencimento60 = "Data de Vencimento posterior a data corrente mais 60 dias.";
		var msgDataVencimentoUltimoDiaMes = "Data de Vencimento foi alterada para o último dia do mês corrente.Confirma?";
		var DATA_ATUAL = document.getElementById("DATA_ATUAL").value;
		var DATA_ATUAL_60 = document.getElementById("DATA_ATUAL_60").value;
		var ANO_LIMITE = document.getElementById("ANO_LIMITE").value;
		var ID_CONDOMINIO = document.getElementById("ID_CONDOMINIO").value;
		
		var ULTIMA_DATA_MES = document.getElementById("ULTIMA_DATA_MES").value;
		var INDICADOR_CALCULA_VENCIMENTO = document.getElementById("INDICADOR_CALCULA_VENCIMENTO").value;
		
	
		var listBoxAgua = form.ligacaoAguaSituacaoID;
		var listBoxEsgoto = form.ligacaoEsgotoSituacaoID;
		
		var ATIVO = document.getElementById("ATIVO").value;
 		var indicadorFaturamentoAgua = listBoxAgua.options[listBoxAgua.options.selectedIndex].id;
 		var indicadorFaturamentoEsgoto = listBoxEsgoto.options[listBoxEsgoto.options.selectedIndex].id;
 		
 		
 	   if(INDICADOR_CALCULA_VENCIMENTO != 2){ 
 	     if(form.indicadorDataAlterada == "sim"){  	  
 	  	   if (comparaData(form.vencimentoConta.value, ">", ULTIMA_DATA_MES )){					
 				if (confirm(msgDataVencimentoUltimoDiaMes)){ 
 				    form.vencimentoConta.value = ULTIMA_DATA_MES;	 			   
					form.vencimentoConta.focus();				
				}else{					
					form.vencimentoConta.focus();
				}	
			}
		  }
		}
			
		if ((form.vencimentoConta.value.substring(6, 10) * 1) < (ANO_LIMITE * 1)){
			alert("Ano do vencimento da conta não deve ser menor que " + ANO_LIMITE + ".");
			form.vencimentoConta.focus();
		}
		else if (comparaData(form.vencimentoConta.value, "<", DATA_ATUAL )){
			
			if (confirm(msgDataVencimento)){
				
				if ((indicadorFaturamentoAgua.length > 0 && indicadorFaturamentoAgua == ATIVO)
				 	&& form.consumoAgua.value.length < 1){
					
					alert("O preenchimento do campo Consumo de Água é obrigatório.");
					form.consumoAgua.focus();
				}
				
				/*else if (form.consumoAgua.value.length > 0 && form.consumoAgua.value != 0 && 
					form.consumoAgua.value <= listBoxAgua.options[listBoxAgua.selectedIndex].name){
				
					alert("Consumo informado menor que consumo mínimo para situação da ligação de água, valor tem que ser maior que " + listBoxAgua.options[listBoxAgua.selectedIndex].name);
					form.consumoAgua.focus();
				}*/
				
				
				else if (form.consumoAgua.value.length > 0 && form.consumoAgua.value != 0 && 
					form.consumoAgua.value <= listBoxAgua.options[listBoxAgua.selectedIndex].name){
				
					alert("Consumo informado menor que consumo mínimo para situação da ligação de água, valor tem que ser maior que " + listBoxAgua.options[listBoxAgua.selectedIndex].name);
					form.consumoAgua.focus();
				}
				else if ((indicadorFaturamentoEsgoto.length > 0 && indicadorFaturamentoEsgoto == ATIVO) 
					&& form.consumoEsgoto.value.length < 1){
					
					alert("O preenchimento do campo Consumo de Esgoto é obrigatório.");
					form.consumoEsgoto.focus();
				}
				
				
				/*else if (form.consumoEsgoto.value.length > 0 && form.consumoEsgoto.value != 0 && 
					form.consumoEsgoto.value <= listBoxEsgoto.options[listBoxEsgoto.selectedIndex].name){
				
					alert("Volume informado menor que volume mínimo para situação da ligação de esgoto, valor tem que ser maior que " + listBoxesgoto.name);
					form.consumoEsgoto.focus();
				}*/
				
				
				else if (form.consumoEsgoto.value.length > 0 && form.consumoEsgoto.value != 0 && 
					form.consumoEsgoto.value <= listBoxEsgoto.options[listBoxEsgoto.selectedIndex].name){
				
					alert("Volume informado menor que volume mínimo para situação da ligação de esgoto, valor tem que ser maior que " + listBoxesgoto.name);
					form.consumoEsgoto.focus();
				}
				else if (form.consumoAgua.value.length > 0 && form.consumoEsgoto.value.length > 0 &&
						 (form.consumoAgua.value * 1) > (form.consumoEsgoto.value * 1) && 
						 (ID_CONDOMINIO == null || ID_CONDOMINIO == '')){
					
					alert("Consumo de Esgoto não deve ser menor que o Consumo de Água");
					form.consumoEsgoto.value = form.consumoAgua.value;
					form.consumoEsgoto.focus();
				}
				else {
					submeterFormPadrao(form);
				}
				
			}
		}
		else if (comparaData(form.vencimentoConta.value, ">", DATA_ATUAL_60 )){
			
			if (confirm(msgDataVencimento60)){
				
				if ((indicadorFaturamentoAgua.length > 0 && indicadorFaturamentoAgua == ATIVO)
				 	&& form.consumoAgua.value.length < 1){
					
					alert("O preenchimento do campo Consumo de Água é obrigatório.");
					form.consumoAgua.focus();
				}
				
				
				/*else if (form.consumoAgua.value.length > 0 && form.consumoAgua.value != 0 && 
					form.consumoAgua.value <= listBoxAgua.options[listBoxAgua.selectedIndex].name){
				
					alert("Consumo informado menor que consumo mínimo para situação da ligação de água, valor tem que ser maior que " + listBoxAgua.options[listBoxAgua.selectedIndex].name);
					form.consumoAgua.focus();
				}*/
				
				
				else if (form.consumoAgua.value.length > 0 && form.consumoAgua.value != 0 && 
					form.consumoAgua.value <= listBoxAgua.options[listBoxAgua.selectedIndex].name){
				
					alert("Consumo informado menor que consumo mínimo para situação da ligação de água, valor tem que ser maior que " + listBoxAgua.options[listBoxAgua.selectedIndex].name);
					form.consumoAgua.focus();
				}
				else if ((indicadorFaturamentoEsgoto.length > 0 && indicadorFaturamentoEsgoto == ATIVO) 
					&& form.consumoEsgoto.value.length < 1){
					
					alert("O preenchimento do campo Consumo de Esgoto é obrigatório.");
					form.consumoEsgoto.focus();
				}
				
				
				/*else if (form.consumoEsgoto.value.length > 0 && form.consumoEsgoto.value != 0 && 
					form.consumoEsgoto.value <= listBoxEsgoto.options[listBoxEsgoto.selectedIndex].name){
				
					alert("Volume informado menor que volume mínimo para situação da ligação de esgoto, valor tem que ser maior que " + listBoxesgoto.name);
					form.consumoEsgoto.focus();
				}*/
				
				
				else if (form.consumoEsgoto.value.length > 0 && form.consumoEsgoto.value != 0 && 
					form.consumoEsgoto.value <= listBoxEsgoto.options[listBoxEsgoto.selectedIndex].name){
				
					alert("Volume informado menor que volume mínimo para situação da ligação de esgoto, valor tem que ser maior que " + listBoxesgoto.name);
					form.consumoEsgoto.focus();
				}
				else if (form.consumoAgua.value.length > 0 && form.consumoEsgoto.value.length > 0 &&
						 (form.consumoAgua.value * 1) > (form.consumoEsgoto.value * 1) && 
						 (ID_CONDOMINIO == null || ID_CONDOMINIO == '')){
					
					alert("Consumo de Esgoto não deve ser menor que Consumo de Água.");
					form.consumoEsgoto.value = form.consumoAgua.value;
					form.consumoEsgoto.focus();
				}
				else {
					submeterFormPadrao(form);
				}
				
			}
		}
		else if ((indicadorFaturamentoAgua.length > 0 && indicadorFaturamentoAgua == ATIVO)
			&& form.consumoAgua.value.length < 1){
					
			alert("O preenchimento do campo Consumo de Água é obrigatório.");
			form.consumoAgua.focus();
		}
		
		
		/*else if (form.consumoAgua.value.length > 0 && form.consumoAgua.value != 0 && 
				form.consumoAgua.value <= listBoxAgua.options[listBoxAgua.selectedIndex].name){
				
			alert("Consumo informado menor que consumo mínimo para situação da ligação de água, valor tem que ser maior que " + listBoxAgua.options[listBoxAgua.selectedIndex].name);
			form.consumoAgua.focus();
		}*/
		
		
		else if (form.consumoAgua.value.length > 0 && form.consumoAgua.value != 0 && 
				form.consumoAgua.value <= listBoxAgua.options[listBoxAgua.selectedIndex].name){
				
			alert("Consumo informado menor que consumo mínimo para situação da ligação de água, valor tem que ser maior que " + listBoxAgua.options[listBoxAgua.selectedIndex].name);
			form.consumoAgua.focus();
		}
		else if ((indicadorFaturamentoEsgoto.length > 0 && indicadorFaturamentoEsgoto == ATIVO) 
			&& form.consumoEsgoto.value.length < 1){
					
			alert("O preenchimento do campo Consumo de Esgoto é obrigatório.");
			form.consumoEsgoto.focus();
		}
		
		
		/*else if (form.consumoEsgoto.value.length > 0 && form.consumoEsgoto.value != 0 && 
				form.consumoEsgoto.value <= listBoxEsgoto.options[listBoxEsgoto.selectedIndex].name){
				
			alert("Volume informado menor que volume mínimo para situação da ligação de esgoto, valor tem que ser maior que " + listBoxesgoto.name);
			form.consumoEsgoto.focus();
		}*/
		
		
		else if (form.consumoEsgoto.value.length > 0 && form.consumoEsgoto.value != 0 && 
				form.consumoEsgoto.value <= listBoxEsgoto.options[listBoxEsgoto.selectedIndex].name){
				
			alert("Volume informado menor que volume mínimo para situação da ligação de esgoto, valor tem que ser maior que " + listBoxesgoto.name);
			form.consumoEsgoto.focus();
		}
		else if (form.consumoAgua.value.length > 0 && form.consumoEsgoto.value.length > 0 &&
				(form.consumoAgua.value * 1) > (form.consumoEsgoto.value * 1) && 
				(ID_CONDOMINIO == null || ID_CONDOMINIO == '')){
			
			alert("Consumo de Esgoto não deve ser menor que Consumo de Água.");
			form.consumoEsgoto.value = form.consumoAgua.value;
			form.consumoEsgoto.focus();
		}
		else {
			submeterFormPadrao(form);
		}
	}
}
		
	
function habilitacaoCamposAgua(listBoxAgua, consumoAgua){
 	
 	var ATIVO = document.getElementById("ATIVO").value;
 	var indicadorFaturamento = listBoxAgua.options[listBoxAgua.options.selectedIndex].id;
 	
 	if (indicadorFaturamento.length > 0 && indicadorFaturamento != ATIVO){
 		consumoAgua.value = "";
 		consumoAgua.disabled = true;
 	}
 	else{
 		consumoAgua.disabled = false;
 	}
}

function habilitacaoCamposEsgoto(listBoxEsgoto, consumoEsgoto){
 
 	var ATIVO = document.getElementById("ATIVO").value;
 	var indicadorFaturamento = listBoxEsgoto.options[listBoxEsgoto.options.selectedIndex].id;
 	
 	if (indicadorFaturamento.length > 0 && indicadorFaturamento != ATIVO){
 		consumoEsgoto.value = "";
 		consumoEsgoto.disabled = true;
 	}
 	else{
 		consumoEsgoto.disabled = false;
 	}
}

function copiarDadosLigacaoEsgoto(listBoxEsgoto, consumoEsgoto){
 	
 	var ATIVO = document.getElementById("ATIVO").value;
 	var indicadorFaturamento = listBoxEsgoto.options[listBoxEsgoto.options.selectedIndex].id;
 	
 	if (indicadorFaturamento.length > 0 && indicadorFaturamento == ATIVO){
 		
 		var form = document.forms[0];
 		
 		form.consumoEsgoto.value = form.consumoAgua.value;
 	}
}

function checkDataVencimento(){
	var form = document.forms[0];	
	form.indicadorDataAlterada = "sim";
}

//-->
</SCRIPT>

</head>

<logic:present name="reloadPageManterConjuntoConta">
	<body leftmargin="0" topmargin="0" onload="chamarSubmitComUrl('exibirManterContaConjuntoImovelAction.do?mensagemSucesoRetificarConjuntoConta=sim'); window.close();">
</logic:present>

<logic:present name="reloadPage">
	<body leftmargin="0" topmargin="0" onload="chamarSubmitComUrl('exibirManterContaAction.do'); window.close();">
</logic:present>

<logic:notPresent name="reloadPage">
	
	<logic:notPresent name="reloadPageManterConjuntoConta">
		<body leftmargin="0" topmargin="0" onload="resizePageSemLink(630, 460); setarFoco('${requestScope.nomeCampo}');">
	</logic:notPresent>
	
</logic:notPresent>


<INPUT TYPE="hidden" ID="ATIVO" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO%>"/>

<INPUT TYPE="hidden" ID="DATA_ATUAL" value="${requestScope.dataAtual}"/>
<INPUT TYPE="hidden" ID="DATA_ATUAL_60" value="${requestScope.dataAtual60}"/>
<INPUT TYPE="hidden" ID="ANO_LIMITE" value="${requestScope.anoLimite}"/>
<INPUT TYPE="hidden" ID="ID_CONDOMINIO" value="${requestScope.idImovelCondominio}"/>

<INPUT TYPE="hidden" ID="ULTIMA_DATA_MES" value="${requestScope.ultimaDataMes}"/>
<INPUT TYPE="hidden" ID="INDICADOR_CALCULA_VENCIMENTO" value="${requestScope.indicadorCalculaVencimento}"/>


<html:form action="/retificarConjuntoContaAction" method="post">

<html:hidden property="contaSelected"/>

<table width="600" border="0" cellpadding="0" cellspacing="5">
  <tr>
    <td width="590" valign="top" class="centercoltext"> 
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" /></td>
          <td class="parabg">Retificar Conjunto de Conta</td>
          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
        </tr>
      </table> 
      <p>&nbsp;</p>

      <table width="100%" border="0">
        <tr>
          <td colspan="4">Informe os dados abaixo:</td>
          <td align="right"></td>
        </tr>
        </table>
      <table width="100%" border="0">
      	<tr> 
			<td width="150" height="10"><strong>Motivo da Retificação:<font color="#FF0000">*</font></strong></td>
			<td colspan="3">
				<html:select property="motivoRetificacaoID" style="width: 400px;" tabindex="1">
					<html:option value="<%= ""+ConstantesSistema.NUMERO_NAO_INFORMADO %>">&nbsp;</html:option>
					<logic:present name="colecaoMotivoRetificacaoConta">
						<html:options collection="colecaoMotivoRetificacaoConta" labelProperty="descricao" property="id"/>
					</logic:present>
				</html:select>
			</td>
		</tr>
        <tr>
          <td height="20"><strong>Data de Vencimento<font
						color="#FF0000">*</font></strong></td>
          <td colspan="3">
          
          		<html:text property="vencimentoConta" size="11" maxlength="10" tabindex="2" onclick="checkDataVencimento();"
          		onkeyup="mascaraData(this, event);"/>
				<a href="javascript:abrirCalendario('RetificarConjuntoContaActionForm', 'vencimentoConta');checkDataVencimento();">
                    <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>&nbsp;dd/mm/aaaa          
          </td>
        </tr>

		<tr>
			<td colspan="4" height="10"></td>
		</tr>
		<tr>
			<td colspan="4">
			
			<table width="100%" align="center" bgcolor="#99CCFF">
		   	<tr>
				<td align="center"><strong>Dados de Água</strong></td>
			</tr>
			<tr bgcolor="#cbe5fe">
				<td width="100%" align="center">
					
					<table width="100%" border="0">
					<tr> 
						<td height="10" width="148">
							<strong>Situação de Água:<font
						color="#FF0000">*</font></strong>
						</td>
						<td>
						<html:select property="ligacaoAguaSituacaoID" style="width: 230px;" tabindex="3" 
						onchange="habilitacaoCamposAgua(this, document.forms[0].consumoAgua);">
						
						<logic:present name="colecaoLigacaoAguaSituacao">
							
							<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
															    	
							<logic:iterate name="colecaoLigacaoAguaSituacao" id="ligacaoAguaSituacao" type="LigacaoAguaSituacao">
													      				
								<logic:equal name="RetificarConjuntoContaActionForm" property="ligacaoAguaSituacaoID" value="<%="" + ligacaoAguaSituacao.getId() %>">
								
									<option SELECTED value="<%="" + ligacaoAguaSituacao.getId() %>" id="<%="" + ligacaoAguaSituacao.getIndicadorFaturamentoSituacao() %>" name="<%="" + ligacaoAguaSituacao.getConsumoMinimoFaturamento() %>"><%="" + ligacaoAguaSituacao.getDescricao() %></option>
								
								</logic:equal>
													      				
								<logic:notEqual name="RetificarConjuntoContaActionForm" property="ligacaoAguaSituacaoID" value="<%="" + ligacaoAguaSituacao.getId() %>">
								
									<option value="<%="" + ligacaoAguaSituacao.getId() %>" id="<%="" + ligacaoAguaSituacao.getIndicadorFaturamentoSituacao() %>" name="<%="" + ligacaoAguaSituacao.getConsumoMinimoFaturamento() %>"><%="" + ligacaoAguaSituacao.getDescricao() %></option>
									
								</logic:notEqual>
													      			
							</logic:iterate>
													      			
						</logic:present>
		   				
						</html:select>
						</td>
						<td colspan="2"></td>
					</tr>
					<tr> 
		   				<td height="10"><strong>Consumo de Água:</strong></td>
						<td>
							<html:text property="consumoAgua" size="10" maxlength="6" tabindex="4" style="text-align: right;"/>
						</td>
					</tr>
					</table>
				
				</td>
			</tr>
			</table> 
			</td>
		</tr>	
		<tr>
			<td colspan="4" height="10"></td>
		</tr>
		<tr>
			<td colspan="4">
			
			<table width="100%" align="center" bgcolor="#99CCFF">
		   	<tr>
				<td align="center"><strong>Dados de Esgoto</strong></td>
			</tr>
			<tr bgcolor="#cbe5fe">
				<td width="100%" align="center">
				
				<table width="100%" border="0">
				<tr> 
					<td height="10" width="150">
						<strong>Situação de Esgoto:<font
						color="#FF0000">*</font></strong>
					</td>
					<td>
						<html:select property="ligacaoEsgotoSituacaoID" style="width: 230px;" tabindex="5" 
						onchange="habilitacaoCamposEsgoto(this, document.forms[0].consumoEsgoto);
						copiarDadosLigacaoEsgoto(this, document.forms[0].consumoEsgoto);">
						
						<logic:present name="colecaoLigacaoAguaSituacao">
															   		
							<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
															    	
							<logic:iterate name="colecaoLigacaoEsgotoSituacao" id="ligacaoEsgotoSituacao" type="LigacaoEsgotoSituacao">
													      				
								<logic:equal name="RetificarConjuntoContaActionForm" property="ligacaoEsgotoSituacaoID" value="<%="" + ligacaoEsgotoSituacao.getId() %>">
								
									<option SELECTED value="<%="" + ligacaoEsgotoSituacao.getId() %>" id="<%="" + ligacaoEsgotoSituacao.getIndicadorFaturamentoSituacao() %>" name="<%="" + ligacaoEsgotoSituacao.getVolumeMinimoFaturamento() %>"><%="" + ligacaoEsgotoSituacao.getDescricao() %></option>
									
								</logic:equal>
													      			
								<logic:notEqual name="RetificarConjuntoContaActionForm" property="ligacaoEsgotoSituacaoID" value="<%="" + ligacaoEsgotoSituacao.getId() %>">
								
									<option value="<%="" + ligacaoEsgotoSituacao.getId() %>" id="<%="" + ligacaoEsgotoSituacao.getIndicadorFaturamentoSituacao() %>" name="<%="" + ligacaoEsgotoSituacao.getVolumeMinimoFaturamento() %>"><%="" + ligacaoEsgotoSituacao.getDescricao() %></option>
									
								</logic:notEqual>
													      				
							</logic:iterate>
													      			
						</logic:present>
						
						</html:select>
					
					</td>
						<td colspan="2"></td>
					</tr>
					<tr> 
						<td height="10"><strong>Volume de Esgoto:</strong></td>
						<td>
							<html:text property="consumoEsgoto" size="10" maxlength="6" tabindex="6" style="text-align: right;"/>
						</td>
					</tr>
					</table>
				</td>
			</tr>
			</table> 
			</td>
		</tr>
		<tr>
          	<td height="20"><strong>Usar categoria(s) e economia(s) informada(s) na conta:<font
						color="#FF0000">*</font></strong></td>
			<td>
				<html:radio	property="indicadorCategoriaEconomiaConta"
							value="<%=ConstantesSistema.SIM.toString()%>" /> <strong>Sim</strong>
				<html:radio property="indicadorCategoriaEconomiaConta"
							value="<%=ConstantesSistema.NAO.toString()%>" /> <strong>N&atilde;o</strong>
			</td>
        </tr>
        <tr>
          <td colspan="4" height="5"></td>
        </tr>
        <tr>
          <td height="30" colspan="4"> 
          	<div align="right">
              <input type="button" tabindex="7" onclick="validarForm(document.forms[0]);" class="bottonRightCol" value="Retificar Conjunto Conta">&nbsp;
              <input type="button" tabindex="8" onclick="window.close();" class="bottonRightCol" value="Fechar" style="width: 70px;">
			</div>
		  </td>
        </tr>
	  </table>
      
      <p>&nbsp;</p>
      </td>
  </tr>
</table>

</html:form>

</body>
</html:html>
