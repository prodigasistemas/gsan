<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script><script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InformarExpurgoPagamentoActionForm" />

<script language="JavaScript">
<!-- Begin 


function consultarPagamentos(){
var form = document.forms[0];
 if(validateInformarExpurgoPagamentoActionForm(form)){
    form.action = 'exibirInformarExpurgoPagamentoAction.do?botaoConsultar=OK';
   submeterFormPadrao(form);
   
 }
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'cliente') {
      form.idCliente.value = codigoRegistro;
      form.nomeCliente.value = descricaoRegistro;
      form.nomeCliente.style.color = "#000000";
    }
  }

function concluir(){
var form = document.forms[0];
 if(form.idCliente.value != '' && form.dataPagamento.value != ''){
   submeterFormPadrao(form);
 }else{
   if(form.idCliente.value == ''){
    alert('Informe o Cliente.');
   }else{
    alert('Informe a Data de Pagamento.');
   }
   
 }

    
}

function limpar(campo){
var form = document.forms[0];
  if(campo == 'dataPagamento' && form.dataPagamento.value == ''){
   form.action = 'exibirInformarExpurgoPagamentoAction.do?limpar='+campo;
   submeterFormPadrao(form);
  }
  
  if(campo != 'dataPagamento'){
   form.action = 'exibirInformarExpurgoPagamentoAction.do?limpar='+campo;
   submeterFormPadrao(form);
  }    
}

//Ativa todos os elementos do tipo checkbox do formul?rio existente
function marcarTodosRadioSim(){

	for (var i=0;i < document.forms[0].elements.length;i++){
		var elemento = document.forms[0].elements[i];
		if (elemento.type == "radio"){
		    if(elemento.value == 1){
			 elemento.checked = true;
			}
		}
	}
}

//Ativa todos os elementos do tipo checkbox do formul?rio existente
function marcarTodosRadioNao(){

	for (var i=0;i < document.forms[0].elements.length;i++){
		var elemento = document.forms[0].elements[i];
		if (elemento.type == "radio"){
			if(elemento.value == 2){
			 elemento.checked = true;
			}
		}
	}
}


-->
</script>
</head>
<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">
<html:form action="/informarExpurgoPagamentoAction" 
	name="InformarExpurgoPagamentoActionForm"
	type="gcom.gui.arrecadacao.pagamento.InformarExpurgoPagamentoActionForm"
	onsubmit="return validateInformarExpurgoPagamentoActionForm(this);"
	method="post">
	
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
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
				<tr><td></td></tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
					</td>
					<td class="parabg">Informar Expurgo de Pagamento</td>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>
			<!-- Início do Corpo - Roberta Costa -->
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="3">
						<p>Para atualizar a informação de expurgo do pagamento, informe o código do cliente e a data de pagamento:</p>
					</td>
				</tr>
				<tr>
					<td width="20%"><strong>Mês/Ano Arrecada&ccedil;&atilde;o:</strong></td>
					<td colspan="2">
						<strong>
						<html:text maxlength="7" property="mesAnoReferencia" size="7"
							onkeyup="mascaraAnoMes(this, event);" /> 
						<strong>(mm/aaaa)
					</td>
				</tr>
				<tr>
					<td width="20%"><strong>Código do Cliente:</strong></td>
					<td colspan="2">
						<strong>
							<html:text property="idCliente" size="9" maxlength="9" 
								onkeyup="validaEnter(event, 'exibirInformarExpurgoPagamentoAction.do', 'idCliente');document.forms[0].nomeCliente.value=''"
								onkeypress="document.forms[0].nomeCliente.value='';" />
							<a href="javascript:abrirPopup('exibirPesquisarClienteAction.do', 400, 800);">	
								<img width="23" height="21" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									style="cursor:hand;" alt="Pesquisar" border="0" /></a> 
							<logic:present name="clienteInexistente" scope="request">
								<html:text property="nomeCliente" size="35" maxlength="35"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:present>
							<logic:notPresent name="clienteInexistente" scope="request">
								<html:text property="nomeCliente" size="35" maxlength="35"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notPresent>
							<a href="javascript:limpar('cliente');"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /></a>
						</strong>
					</td>
				</tr>		
				<tr><td colspan="3" height="24"><hr></td></tr>
				<tr>
					<td><strong>Data do Pagamento:</strong></td>
					<td>
						<html:text maxlength="10" property="dataPagamento" size="10"
							onkeyup="mascaraData(this, event);limpar('dataPagamento')" /> 
						<a href="javascript:abrirCalendario('InformarExpurgoPagamentoActionForm', 'dataPagamento');">
							<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
								width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
						 (dd/mm/aaaa)
					</td>
					<td align="right">
					 <input value="Exibir Pagamentos" class="bottonRightCol" type="button" onclick="consultarPagamentos();">	
					</td>
				</tr>
				<tr><td colspan="3" height="24"><hr></td></tr>
				<c:if test="${(sessionScope.colecaoExpurgado != null && not empty sessionScope.colecaoExpurgado) || (sessionScope.colecaoNaoExpurgado != null && not empty sessionScope.colecaoNaoExpurgado)}">
				
				    
					<tr>
					 <td colspan="3">
					  <table width="100%" bgcolor="#90c7fc">
					   <tr bgcolor="#79bbfd">
						 <td rowspan="2" width="30%" bgcolor="#79bbfd"><strong>Valor Pagamento</strong></td>
						 <td colspan="2" width="60%"><div align="center" class="style9"><strong>Indicador Expurgado</strong></div>
					   </tr>
					   <tr bordercolor="#FFFFFF" bgcolor="#90c7fc">
		                    	<td colspan="2"><strong><a href="javascript:marcarTodosRadioSim(this);">Todos Sim </a></strong> &nbsp;&nbsp;&nbsp;&nbsp;
		                      	<strong><a href="javascript:marcarTodosRadioNao(this);"">Todos Não </a></strong></td>
		               </tr>
					  </table>
					  </td>
					 </tr>
					 <tr> 
					  <td height="200" colspan="3">
					   <div style="width: 100%; height: 100%; overflow: auto;">
					  <table width="100%" bgcolor="#90c7fc">
					   <%String cor = "#FFFFFF";%>
					   <logic:notEmpty name="colecaoExpurgado">
						 <logic:iterate name="colecaoExpurgado" id="pagamento">
                          <%if (cor.equalsIgnoreCase("#FFFFFF")) {
								cor = "#cbe5fe";%>
						    <tr bgcolor="#FFFFFF">
						  <%} else {
								cor = "#FFFFFF";%>
							<tr bgcolor="#cbe5fe">
						  <%}%>
							    <td width="30%">
							     <bean:write name="pagamento" property="valorPagamento" formatKey="money.format"/>
							    </td>
							    
							    <td width="60%">
							     <input type="radio" name="indicadorExpurgado<bean:write name="pagamento" property="id" />" value="1" checked="true"/>SIM &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							     <input type="radio" name="indicadorExpurgado<bean:write name="pagamento" property="id" />" value="2"/>NÃO
							    </td>
						    
						    </tr>
						  </logic:iterate>
					   </logic:notEmpty>
					   <logic:notEmpty name="colecaoNaoExpurgado">
						 <logic:iterate name="colecaoNaoExpurgado" id="pagamento">
                          <%if (cor.equalsIgnoreCase("#FFFFFF")) {
								cor = "#cbe5fe";%>
						    <tr bgcolor="#FFFFFF">
						  <%} else {
								cor = "#FFFFFF";%>
							<tr bgcolor="#cbe5fe">
						  <%}%>
							    <td width="30%">
							     <bean:write name="pagamento" property="valorPagamento" formatKey="money.format"/>
							    </td>
							    
							    <td width="60%">
							     <input type="radio" name="indicadorExpurgado<bean:write name="pagamento" property="id" />" value="1" />SIM &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							     <input type="radio" name="indicadorExpurgado<bean:write name="pagamento" property="id" />" value="2" checked="true"/>NÃO
							    </td>
						    
						    </tr>
						  </logic:iterate>
					   </logic:notEmpty>    
					  
					  </table>
					  </div> 
					 </td> 
					</tr>
				</c:if>
				
				<tr>
				  <td colspan="3">
				  <table>
				   <tr>
					   <td width="45%"><strong>Quantidade de pagamentos expurgados:</strong></td>
					   <td>
					    <html:text maxlength="10" property="quantidadePagamentosExpurgados" size="10" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/> 
					   </td>
				   </tr>
					
				   <tr>
					   <td width="45%"><strong>Quantidade de pagamentos não expurgados:</strong></td>
					   <td>
					    <html:text maxlength="10" property="quantidadePagamentosNaoExpurgados" size="10" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/> 
					   </td>
					</tr>
				  </table> 
				 </td> 
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					 <td colspan="2">
						<input value="Limpar" class="bottonRightCol" type="button" onclick="javascript:limpar('sim')">
					 </td>	
					 <td align="right">
						<gsan:controleAcessoBotao name="botaoFiltrar" value="Concluir"
										  onclick="javascript:concluir();" url="consultarPagamentoAction.do"/>
										
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