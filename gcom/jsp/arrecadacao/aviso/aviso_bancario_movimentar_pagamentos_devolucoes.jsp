<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
    <html:javascript staticJavascript="false"  formName="MovimentarPagamentosDevolucoesAvisoBancarioActionForm"/>	
<script>	
<!--
function facilitador(objeto){
    if (objeto.id == "0" || objeto.id == undefined){ 
        objeto.id = "1";
        marcarTodos();
    }
    else{
        objeto.id = "0";
        desmarcarTodos();
    }
}

function removerPagamentos(){
    var form = document.forms[0];
  	form.action = 'exibirMovimentarPagamentosDevolucoesAvisoBancarioAction.do?removerPagamentos=ok';
    form.submit();		
}

function removerDevolucoes(){
    var form = document.forms[0];
  	form.action = 'exibirMovimentarPagamentosDevolucoesAvisoBancarioAction.do?removerDevolucoes=ok';
    form.submit();		
}

-->
</script>

</head>
<body leftmargin="5" topmargin="5">

<html:form action="/movimentarPagamentosDevolucoesAvisoBancarioAction" method="post"
	name="MovimentarPagamentosDevolucoesAvisoBancarioActionForm"
	type="gcom.gui.arrecadacao.aviso.MovimentarPagamentosDevolucoesAvisoBancarioActionForm">

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
			
			<td valign="top" bgcolor="#003399" class="centercoltext">
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Movimentar Pagamentos/Devoluções entre Avisos Bancários</td>
					<td width="11" valign="top"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<!-- Início do Corpo -->
			<p>&nbsp;</p>
			<table width="100%" cellpadding="0" cellspacing="0" border="0">

				<tr>					
					<td width="30%"><strong>Aviso Bancário Origem:</strong></td>
					<td width="70%" colspan="5">${sessionScope.descricaoABOrigem}</td>
				</tr>
			</table>			
			<table width="100%" bgcolor="#99CCFF">			
			  <tr bordercolor="#000000" align="center">
				<td bgcolor="#90c7fc" rowspan="2"><strong>Situação</strong></td>
				<td bgcolor="#90c7fc" colspan="2"><strong>Valor da Arrecadação</strong></td>
				<td bgcolor="#90c7fc" colspan="2"><strong>Valor da Devolução</strong></td>										
			  </tr>
			  <tr bordercolor="#000000" align="center">
				<td bgcolor="#90c7fc"><strong>Informado</strong></td>
				<td bgcolor="#90c7fc"><strong>Calculado</strong></td>
				<td bgcolor="#90c7fc"><strong>Informado</strong></td>
				<td bgcolor="#90c7fc"><strong>Calculado</strong></td>										
			  </tr>
			  <tr bordercolor="#99CCFF" align="center">
				<td bgcolor="#FFFFFF">Antes</td>
				<td bgcolor="#FFFFFF"><bean:write name="MovimentarPagamentosDevolucoesAvisoBancarioActionForm" property="arrecadacaoInformadoAntesOrigem"/></td>
				<td bgcolor="#FFFFFF"><bean:write name="MovimentarPagamentosDevolucoesAvisoBancarioActionForm" property="arrecadacaoCalculadoAntesOrigem"/></td>
				<td bgcolor="#FFFFFF"><bean:write name="MovimentarPagamentosDevolucoesAvisoBancarioActionForm" property="devolucaoInformadoAntesOrigem"/></td>			
				<td bgcolor="#FFFFFF"><bean:write name="MovimentarPagamentosDevolucoesAvisoBancarioActionForm" property="devolucaoCalculadoAntesOrigem"/></td>								
			  </tr>
			  <tr bordercolor="#99CCFF" align="center">
				<td bgcolor="#cbe5fe">Depois</td>
				<td bgcolor="#cbe5fe"><bean:write name="MovimentarPagamentosDevolucoesAvisoBancarioActionForm" property="arrecadacaoInformadoDepoisOrigem"/></td>
				<td bgcolor="#cbe5fe"><bean:write name="MovimentarPagamentosDevolucoesAvisoBancarioActionForm" property="arrecadacaoCalculadoDepoisOrigem"/></td>
				<td bgcolor="#cbe5fe"><bean:write name="MovimentarPagamentosDevolucoesAvisoBancarioActionForm" property="devolucaoInformadoDepoisOrigem"/></td>			
				<td bgcolor="#cbe5fe"><bean:write name="MovimentarPagamentosDevolucoesAvisoBancarioActionForm" property="devolucaoCalculadoDepoisOrigem"/></td>								
			  </tr> 
			 
			</table>
			<p>&nbsp;</p>
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td width="30%"><strong>Aviso Bancário Destino:</strong></td>
					<td width="70%" colspan="5">${sessionScope.descricaoABDestino}</td>
				</tr>
			</table>			
			<table width="100%" bgcolor="#99CCFF">			
			  <tr bordercolor="#000000" align="center">
				<td bgcolor="#90c7fc" rowspan="2"><strong>Situação</strong></td>
				<td bgcolor="#90c7fc" colspan="2"><strong>Valor da Arrecadação</strong></td>
				<td bgcolor="#90c7fc" colspan="2"><strong>Valor da Devolução</strong></td>										
			  </tr>
			  <tr bordercolor="#000000" align="center">
				<td bgcolor="#90c7fc"><strong>Informado</strong></td>
				<td bgcolor="#90c7fc"><strong>Calculado</strong></td>
				<td bgcolor="#90c7fc"><strong>Informado</strong></td>
				<td bgcolor="#90c7fc"><strong>Calculado</strong></td>										
			  </tr>
			  <tr bordercolor="#99CCFF" align="center">
				<td bgcolor="#FFFFFF">Antes</td>
				<td bgcolor="#FFFFFF"><bean:write name="MovimentarPagamentosDevolucoesAvisoBancarioActionForm" property="arrecadacaoInformadoAntesDestino"/></td>
				<td bgcolor="#FFFFFF"><bean:write name="MovimentarPagamentosDevolucoesAvisoBancarioActionForm" property="arrecadacaoCalculadoAntesDestino"/></td>
				<td bgcolor="#FFFFFF"><bean:write name="MovimentarPagamentosDevolucoesAvisoBancarioActionForm" property="devolucaoInformadoAntesDestino"/></td>			
				<td bgcolor="#FFFFFF"><bean:write name="MovimentarPagamentosDevolucoesAvisoBancarioActionForm" property="devolucaoCalculadoAntesDestino"/></td>								
			  </tr>
			  <tr bordercolor="#99CCFF" align="center">
				<td bgcolor="#cbe5fe">Depois</td>
				<td bgcolor="#cbe5fe"><bean:write name="MovimentarPagamentosDevolucoesAvisoBancarioActionForm" property="arrecadacaoInformadoDepoisDestino"/></td>
				<td bgcolor="#cbe5fe"><bean:write name="MovimentarPagamentosDevolucoesAvisoBancarioActionForm" property="arrecadacaoCalculadoDepoisDestino"/></td>
				<td bgcolor="#cbe5fe"><bean:write name="MovimentarPagamentosDevolucoesAvisoBancarioActionForm" property="devolucaoInformadoDepoisDestino"/></td>			
				<td bgcolor="#cbe5fe"><bean:write name="MovimentarPagamentosDevolucoesAvisoBancarioActionForm" property="devolucaoCalculadoDepoisDestino"/></td>								
			  </tr> 
			 
			</table>
			<p>&nbsp;</p>
  			<table width="100%" cellpadding="0" cellspacing="0">

				<tr>
					<td colspan="6">&nbsp;</td>
				</tr>
				<tr bordercolor="#000000" align="center">
					<td bordercolor="#90c7fc" bgcolor="#3399FF" colspan="6"><strong>Pagamentos a serem movimentadas</strong></td>
				</tr>	
				<tr bordercolor="#000000">
				  	<td colspan="6">
				  	  <table>
				  	  <tr>
					<td width="5%" bgcolor="#90c7fc">
						<div align="center"><strong><a href="javascript:facilitador(this);" id="0">Rem.</a></strong></div>
					</td>
					
					<td width="7%" bgcolor="#90c7fc">
						<div align="center"><strong>Tipo de Doc.</strong></div>
					</td>
					
					<td width="10%" bgcolor="#90c7fc">
						<div align="center"><strong>Mês/Ano Ref.<br>Pag.</strong></div>
					</td>
					
					<td width="17%" bgcolor="#90c7fc">
						<div align="center"><strong>Valor do Pagamento</strong></div>
					</td>
					
					<td width="13%" bgcolor="#90c7fc">
						<div align="center"><strong>Data do Pagamento</strong></div>
					</td>
					
					<td width="48%" bgcolor="#90c7fc">
						<div align="left"><strong>Tipo de Débito</strong></div>
					</td>
					</tr></table>
				<tr>
				<logic:notEmpty name="pagamentoHelper">
				<tr>
				  	<td colspan="6">
					<div style="width: 100%; height: 114; overflow: auto;">
					<table width="100%" bgcolor="#99CCFF">
					<%	int cont = 0;	%>
						<logic:iterate name="pagamentoHelper" property="colecaoMovimentarPagamentos" id="helper">
					<%	cont = cont + 1;
						if (cont % 2 == 0) {%>
							<tr bgcolor="#cbe5fe">
					<%	} else {	%>
							<tr bgcolor="#FFFFFF">
					<%	}	%>												
								<td width="5%">
                              		<div align="center">
                                		<input type="checkbox" name="idRegistrosRemocaoPagamento" value="<bean:write name="helper" property="id"/>;<bean:write name="helper" property="valor"/>"/>
                              		</div>
                            	</td>								
								
								<td width="7%" bordercolor="#90c7fc">
								<div align="center"><bean:write name="helper" property="tipoDocumento" />
								</div>
								</td>
								
								<td width="10%" bordercolor="#90c7fc">
								<div align="center"><bean:write name="helper"
									property="mesAnoReferencia" />
								</div>
								</td>
								
								<td width="17%" bordercolor="#90c7fc">
								<div align="right"><bean:write name="helper"
									property="valor" />
								</div>
								</td>
								
								<td width="13%" bordercolor="#90c7fc">
								<div align="center"><logic:notEmpty name="helper" property="data">
									<bean:write name="helper" property="data" />
								</logic:notEmpty></div>
								</td>
								
								<td width="48%" bordercolor="#90c7fc">
									<logic:notEmpty name="helper" property="tipoDebito">
										<bean:write name="helper" property="tipoDebito" />
									</logic:notEmpty>
								</td>											
							</tr>
						</logic:iterate>	
					</table>
					<table width="100%" bgcolor="#99CCFF">
					  <tr>
					   <td width="5%" bgcolor="#90c7fc">&nbsp;</td>
					
					   <td width="7%" bgcolor="#90c7fc">
						<div align="center"><strong>Total</strong></div>
					   </td>
					
					   <td width="10%" bgcolor="#90c7fc">
						<div align="right">
						    <bean:write name="pagamentoHelper" property="qtdPagamentos" />
						  </div>
					   </td>
					
					   <td width="17%" bgcolor="#90c7fc">
						  <div align="right">
						    <bean:write name="pagamentoHelper" property="valorTotalPagamentos" />
						  </div>
					   </td>
					
					   <td width="13%" bgcolor="#90c7fc">&nbsp;</td>
					
					   <td width="48%" bgcolor="#90c7fc">&nbsp;</td>
					  </tr>
					</table>
					</div>			
					</td>
				</tr>
				</logic:notEmpty>
				<tr>
				  <td colspan="6">
				  <table width="100%" border="0">
						<tr>
						  <td valign="top"><input name="button" type="button"
							 class="bottonRightCol" value="Remover"
							 onclick="javascript:removerPagamentos();"
							 align="left" style="width: 80px;">
						  </td>							      
						</tr>
					</table>
				  </td>
				</tr>				
				<tr>
					<td colspan="6">&nbsp;</td>
				</tr>
				<tr bordercolor="#000000" align="center">
					<td bordercolor="#90c7fc" bgcolor="#3399FF" colspan="6"><strong>Devoluções a serem movimentadas</strong></td>
				</tr>	
				<tr bordercolor="#000000">
				  	<td colspan="6">
				  	  <table>
				  	  <tr>
					<td width="5%" bgcolor="#90c7fc">
						<div align="center"><strong><a href="javascript:facilitador(this);" id="0">Rem.</a></strong></div>
					</td>
					
					<td width="7%" bgcolor="#90c7fc">
						<div align="center"><strong>Tipo de Doc.</strong></div>
					</td>
					
					<td width="10%" bgcolor="#90c7fc">
						<div align="center"><strong>Mês/Ano Ref.<br>Dev.</strong></div>
					</td>
					
					<td width="17%" bgcolor="#90c7fc">
						<div align="center"><strong>Valor da Devolução</strong></div>
					</td>
					
					<td width="13%" bgcolor="#90c7fc">
						<div align="center"><strong>Data da Devolução</strong></div>
					</td>
					
					<td width="48%" bgcolor="#90c7fc">
						<div align="left"><strong>Tipo de Débito</strong></div>
					</td>
					</tr></table>
				<tr>
				<logic:notEmpty name="devolucaoHelper">
				<tr>
				  	<td colspan="6">
					<div style="width: 100%; height: 114; overflow: auto;">
					<table width="100%" bgcolor="#99CCFF">
					<%	int conte = 0;	%>
						<logic:iterate name="devolucaoHelper"  property="colecaoMovimentarDevolucoes" id="helper">
					<%	conte = conte + 1;
						if (conte % 2 == 0) {%>
							<tr bgcolor="#cbe5fe">
					<%	} else {	%>
							<tr bgcolor="#FFFFFF">
					<%	}	%>												
								<td width="5%">
                              		<div align="center">
                                		<input type="checkbox" name="idRegistrosRemocaoDevolucao" value="<bean:write name="helper" property="id"/>;<bean:write name="helper" property="valor"/>"/>
                              		</div>
                            	</td>								
								
								<td width="7%" bordercolor="#90c7fc">
								<div align="center"><bean:write name="helper" property="tipoDocumento" />
								</div>
								</td>
								
								<td width="10%" bordercolor="#90c7fc">
								<div align="center"><bean:write name="helper"
									property="mesAnoReferencia" />
								</div>
								</td>
								
								<td width="17%" bordercolor="#90c7fc">
								<div align="right"><bean:write name="helper"
									property="valor" />
								</div>
								</td>
								
								<td width="13%" bordercolor="#90c7fc">
								<div align="center"><logic:notEmpty name="helper" property="data">
									<bean:write name="helper" property="data" />
								</logic:notEmpty></div>
								</td>
								
								<td width="48%" bordercolor="#90c7fc">
									<logic:notEmpty name="helper" property="tipoDebito">
										<bean:write name="helper" property="tipoDebito" />
									</logic:notEmpty>
								</td>											
							</tr>
						</logic:iterate>
					</table>
					<table width="100%" bgcolor="#99CCFF">
					  <tr>
					   <td width="5%" bgcolor="#90c7fc">&nbsp;</td>
					
					   <td width="7%" bgcolor="#90c7fc">
						<div align="center"><strong>Total</strong></div>
					   </td>
					
					   <td width="10%" bgcolor="#90c7fc">
					      <div align="right">
						    <bean:write name="devolucaoHelper" property="qtdDevolucoes" />
						  </div>
					   </td>
					
					   <td width="17%" bgcolor="#90c7fc">
						  <div align="right">
						    <bean:write name="devolucaoHelper" property="valorTotalDevolucoes" />
						  </div>
					   </td>
					
					   <td width="13%" bgcolor="#90c7fc">&nbsp;</td>
					
					   <td width="48%" bgcolor="#90c7fc">&nbsp;</td>
					  </tr>
					</table>
					</div>			
					</td>
				</tr>
			</logic:notEmpty>	
			</table>
			<table width="100%" border="0">
			 <tr>
				<td valign="top"><input name="button" type="button"
					class="bottonRightCol" value="Remover"
					 onclick="javascript:removerDevolucoes();"
					align="left" style="width: 80px;">
				</td>							      
			  </tr>
			</table>	
			<table border="0" width="100%">
			    <tr>
			      <td colspan="4">&nbsp;</td>
			    </tr>
				<tr>
					<td colspan="2">
					<input tabindex="9" name="Button" type="button" class="bottonRightCol"
						value="Voltar" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirSelecionarPagamentosAvisoBancarioAction.do?menu=sim"/>'">
					</td>
					<td colspan="2" align="right">
					 <input tabindex="10" name="Button" type="button" class="bottonRightCol" value="Concluir" align="left"
						onclick="window.location.href='<html:rewrite page="/movimentarPagamentosDevolucoesAvisoBancarioAction.do"/>'">
					</td>
				</tr>
			</table>		
	</table>
<p>&nbsp;</p>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
