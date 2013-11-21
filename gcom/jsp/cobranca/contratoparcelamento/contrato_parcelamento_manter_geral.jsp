<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@page isELIgnored="false"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />

<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet"	href="<bean:message key="caminho.css"/>popup.css"	type="text/css">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js">
</script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
	
  <script language="JavaScript">
		  function extendeTabela(tabela){
				var form = document.forms[0];
				if(eval('layerShow'+tabela).style.display == 'none'){
					
					if(tabela == 'DadosGerais'){
						eval('layerShowPagamento').style.display = 'none';
						eval('layerShowDebitos').style.display = 'none';
						eval('layerShowParcelamento').style.display = 'none';
					}else if(tabela == 'Debitos'){
						eval('layerShowPagamento').style.display = 'none';
						eval('layerShowParcelamento').style.display = 'none';
						eval('layerShowDadosGerais').style.display = 'none';
					}else if(tabela == 'Parcelamento'){
						eval('layerShowPagamento').style.display = 'none';
						eval('layerShowDebitos').style.display = 'none';
						eval('layerShowDadosGerais').style.display = 'none';
					}else if(tabela == 'Pagamento'){
						eval('layerShowDebitos').style.display = 'none';
						eval('layerShowDadosGerais').style.display = 'none';
						eval('layerShowParcelamento').style.display = 'none';
					}
					eval('layerShow'+tabela).style.display = 'block';
				}else{
					eval('layerShow'+tabela).style.display = 'none';
				}
			}
  			
         </script>
  <script type="text/javascript">
  
	function validarData(campo){

	  	if (campo.value!="") {
	  		
	  		var erro=0;
	  		var barras = campo.value.split("/");
	  		
	          
	  		if (barras.length == 3){
	  			
	  			dia = barras[0];
	  			mes = barras[1];
	  			ano = barras[2];
	              
	  			resultado = (!isNaN(dia) && (dia > 0) && (dia < 32)) && (!isNaN(mes) && (mes > 0) && (mes < 13)) && (!isNaN(ano) && (ano.length == 4) && (ano >= 1900));
	              
	  			if (!resultado){
	  				
	  				campo.focus();
	  				return false;
	  			}
	  		}
	  		else{
	  			campo.focus();
	  			return false;
	  		}
	  	
	  		return true;
	  	}
	}

  	function voltar(){
  		window.location.href = "/gsan/exibirFiltrarContratoParcelamentoClienteAction.do?menu=sim";
	}

	function clicouAtualizar(numero){
		window.location.href = "/gsan/exibirAtualizarContratoParcelamentoClienteAction.do?contratoAtualizarNumero="+numero+"&consultar=sim";
	}
	 
	function cancelarContrato(numeroCont, idClienteContrato){
		abrirPopup('exibirCancelarContratoParcelamentoClienteAction.do?numeroParcelamento='+numeroCont + '&idClienteContrato=' + idClienteContrato, 200, 350);
	}
  
	function mensagemContratoParcelamentoCancelado(idContratoClienteManter) {
		alert('Contrato de parcelamento por cliente ' + idContratoClienteManter + ' cancelado com sucesso.');
	}

	function clicouEmitirExtrato(numero){
		window.location.href = "/gsan/exibirEmitirExtratoContratoParcelamentoPorClienteAction.do?contratoAtualizarNumero="+numero+"";
	}
	
  </script>
	
</head>
<c:if test="${clienteContrato != null }">
	<bean:define id="tipoCliente" value="cliente"></bean:define>
</c:if>

<logic:present name="fecharPopupCancelarContrato" scope="session">
	<body leftmargin="5" topmargin="5" onload="mensagemContratoParcelamentoCancelado(${contratoClienteManter.cliente.id});" onkeypress="verificaRadioButtons('<c:out value="${contratoManter.indicadorResponsavel}"></c:out>'); selecionouDebitoAcresc();">
</logic:present>
<logic:notPresent name="fecharPopupCancelarContrato" scope="session">
	<body leftmargin="5" topmargin="5" onkeypress="verificaRadioButtons('<c:out value="${contratoManter.indicadorResponsavel}"></c:out>'); selecionouDebitoAcresc();">
</logic:notPresent>

<html:form action="/atualizarContratoParcelamentoClienteAction.do"
	name="AtualizarContratoParcelamentoPorClienteActionForm" 
	type="gcom.gui.cobranca.contratoparcelamento.AtualizarContratoParcelamentoPorClienteActionForm"
	method="post">
	
	<input type="hidden" name="controleCliente" value="" />

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
					<!--In&iacute;cio Tabela Reference a P&aacute;gina&ccedil;&atilde;o da Tela de Processo-->
		            <table>
		              <tr>
		                <td></td>
		              </tr>
		            </table>
		            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		              <tr>
		                <td width="11"><img border="0" src="imagens/parahead_left.gif"/></td>
		                <td class="parabg"> Consultar Contrato de Parcelamento por Cliente<strong></strong></td>
		                <td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif"/></td>
		              </tr>
		            </table>
		            <!--Fim Tabela Reference a P&aacute;gina&ccedil;&atilde;o da Tela de Processo-->
					 <table width="100%" border="0">
			              <tr> 
			                <td colspan="2"> 
			                <table width="100%">
			                  	
			                  <tr bgcolor="#99CCFF" align="center">
			                    <td height="18" colspan="2" bgcolor="#99CCFF">
			                    	<div align="center">
			                    		<span class="style2"><a href="javascript:extendeTabela('DadosGerais');"><b>Dados  Gerais do Contrato</b> </a></span><strong></strong>
		                    		</div>
	                    		</td>
			                  </tr>
		                      <tr align="center">
                  				<td colspan="2">
			                  		<div id="layerShowDadosGerais" style="display:none;">
			                  			<jsp:include page="/jsp/cobranca/contratoparcelamento/contrato_parcelamento_manter_dados_gerais.jsp"/>
	                            	</div>
			                  	</td>
			                  </tr>
			                  
			                  
			                   <tr bgcolor="#99CCFF" align="center">
			                    <td height="18" colspan="2" bgcolor="#99CCFF">
			                    	<div align="center">
			                    		<span class="style2"><a href="#" onclick="extendeTabela('Debitos');"><b>D&eacute;bitos do Cliente</b> </a></span><strong></strong>
		                    		</div>
	                    		</td>
			                  </tr>
		                      <tr align="center">
                  				<td colspan="2">
			                  		<div id="layerShowDebitos" style="display:none;">
			                  			<jsp:include page="/jsp/cobranca/contratoparcelamento/contrato_parcelamento_manter_debitos_cliente.jsp"/>
			                  		</div>
			                  	</td>
			                  </tr>
			                  <tr bgcolor="#99CCFF" align="center">
			                    <td height="18" colspan="2" bgcolor="#99CCFF">
			                    	<div align="center">
			                    		<span class="style2"><a href="#" onclick="extendeTabela('Parcelamento');"><b>Dados das Condições do Parcelamento</b></a></span><strong></strong>
		                    		</div>
	                    		</td>
			                  </tr>
		                    <tr align="center">
			                  	<td colspan="2">
			                  		<div id="layerShowParcelamento" style="display:none">
				                  			<jsp:include page="/jsp/cobranca/contratoparcelamento/contrato_parcelamento_manter_dados_parcelamento.jsp"/>
			                  		</div>
			                  	</td>
			                  </tr>
			                  <tr bgcolor="#99CCFF" align="center">
			                    <td height="18" colspan="2" bgcolor="#99CCFF">
			                    	<div align="center">
			                    		<span class="style2"><a href="#" onclick="extendeTabela('Pagamento');"><b>Dados do Pagamento</b></a></span><strong></strong>
		                    		</div>
	                    		</td>
			                  </tr>
		                    <tr align="center">
			                  	<td colspan="2">
			                  		<div id="layerShowPagamento" style="display:none">
				                  			<jsp:include page="/jsp/cobranca/contratoparcelamento/contrato_parcelamento_manter_dados_pagamento.jsp"/>
			                  		</div>
			                  	</td>
			                  </tr>
			               </table>
			               <table border="0">    
			                   
			                    <tr> 
			                      <td align="left" colspan="3"> 
			                      	<input name="btnVoltar" onclick="voltar();" type="button" class="bottonRightCol" value="Voltar"> 
			                        <input name="btnAtualizar" type="button" onclick="clicouAtualizar('<c:out value="${contratoManter.numero}"></c:out>');" class="bottonRightCol"  value="Atualizar" <c:if test="${contratoManter.parcelamentoSituacao.id != 1 || desabilitaAtualizar == true}">disabled="disabled"</c:if> /> 
			                        <input name="btnCancelarContrato" <c:if test="${contratoManter.parcelamentoSituacao.id != 1 }">disabled="disabled"</c:if> type="button" onclick="cancelarContrato('<c:out value="${contratoManter.numero}"></c:out>', ${contratoClienteManter.id} );" class="bottonRightCol"  value="Cancelar Contrato" />
			                        <input name="btnEmitirContrato" type="button" onclick="toggleBox('demodiv',1);" class="bottonRightCol"  value="Emitir Contrato" />
			                        <input name="btnEmitirComprovanteContrato" type="button" onclick="javascript:abrirPopup('exibirEmitirComprovantePagamentoContratoParcelamentoClienteAction.do?numeroContrato=<c:out value="${contratoManter.numero}"></c:out>',500,700)" class="bottonRightCol"  value="Emitir Comprovante de Pagamento" />
			                        <input name="btnEmitirExtratoContratoParcelamento" type="button" onclick="clicouEmitirExtrato('<c:out value="${contratoManter.numero}"></c:out>');" class="bottonRightCol"  value="Emitir Extrato de Contrato de Parcelamento" />
			                      </td>
			                    
			                    </tr>
			                  </table></td>
			              </tr>
			             
			            </table>
					
			</td>
			
	</table>
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioEmitirContratoParcelamentoPorClienteAction.do"/> 
	</html:form>
	<%@ include file="/jsp/util/rodape.jsp"%>
				
</body>
</html:html>

