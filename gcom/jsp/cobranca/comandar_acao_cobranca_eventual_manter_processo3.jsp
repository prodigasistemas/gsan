<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.cobranca.CobrancaAcaoAtividadeComando"%>
<%@ page import="gcom.cobranca.CobrancaCriterio"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false" 
	formName="InserirComandoAcaoCobrancaEventualCriterioComandoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
<!-- Begin

function executarcomando(objeto, tipoObjeto){
       var indice;
       var array = new Array(objeto.length);
       var selecionado = "";
	   var formulario = document.forms[0]; 
  	   
	   for(indice = 0; indice < formulario.elements.length; indice++){
		 if (formulario.elements[indice].type == tipoObjeto && formulario.elements[indice].checked == true) {
			selecionado = formulario.elements[indice].value;
			break;
		 }
	   }

       if (selecionado.length < 1) {
         alert('Seleciona o critério de cobrança');
       }else {
		    formulario.action =  '/gsan/manterComandoAcaoCobrancaEventualCriterioComandoExecutarComandoAction.do?idComando='+selecionado
   			formulario.submit();
       }
}

function concluircomando(objeto, tipoObjeto){
       var indice;
       var array = new Array(objeto.length);
       var selecionado = "";
	   var formulario = document.forms[0]; 
  	   
	   for(indice = 0; indice < formulario.elements.length; indice++){
		 if (formulario.elements[indice].type == tipoObjeto && formulario.elements[indice].checked == true) {
			selecionado = formulario.elements[indice].value;
			break;
		 }
	   }

       if (selecionado.length < 1) {
          alert('Nenhum critério selecionado.');
       }else {
	   	  formulario.action =  '/gsan/manterComandoAcaoCobrancaEventualCriterioComandoConcluirAction.do?idComando='+selecionado
    	  formulario.submit();
       }
}

function atualizar(valor){
	   var formulario = document.forms[0]; 
   	  formulario.action =  '/gsan/exibirManterComandoAcaoCobrancaEventualCriterioComandoAction.do?idComandoSelecionado='+valor
   	  formulario.submit();
}

function voltar(idCobrancaAcaoAtividadeComando){
   var formulario = document.forms[0]; 
   formulario.action =  '/gsan/exibirManterComandoAcaoCobrancaDetalhesAction.do?idCobrancaAcaoAtividadeComando='+idCobrancaAcaoAtividadeComando;
   formulario.submit();
}

-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form
	action="/exibirManterComandoAcaoCobrancaEventualCriterioComandoAction.do"
	name="ManterComandoAcaoCobrancaEventualCriterioComandoActionForm"
	type="gcom.gui.cobranca.ManterComandoAcaoCobrancaEventualCriterioComandoActionForm"
	method="post"
	onsubmit="return validateManterComandoAcaoCobrancaEventualCriterioComandoActionForm(this);">

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


			<td valign="top" class="centercoltext"><!--Início Tabela Reference a Páginação da Tela de Processo-->
			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Manter Comando de A&ccedil;&atilde;o de
					Cobran&ccedil;a - Critério de Cobrança</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0" dwcopytype="CopyTableRow">
				<tr>
					<td colspan="5">Para determinar o crit&eacute;rio do comando de
					a&ccedil;&atilde;o de cobran&ccedil;a, selecione um dos
					crit&eacute;rios abaixo:</td>
				</tr>
				<tr>
					<td colspan="5">
					<table width="590" bgcolor="#99CCFF">
						<tr bgcolor="#99CCFF">
							<td colspan="9" bgcolor="#90c7fc"><strong>Crit&eacute;rios da
							A&ccedil;&atilde;o de Cobran&ccedil;a: <html:text maxlength="30"
								property="descricaoAcaoCobranca" size="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"/></strong></td>
						</tr>
						<tr bgcolor="#99CCFF">
							<td width="4%" rowspan="2" bgcolor="#90c7fc">
							<div align="center"></div>
							</td>
							<td width="27%" rowspan="2" bgcolor="#90c7fc">
							<div align="center"><strong>Descri&ccedil;&atilde;o do
							Crit&eacute;rio</strong></div>
							</td>
							<td width="11%" rowspan="2" bgcolor="#90c7fc">
							<div align="center"><strong>Data de In&iacute;cio de
							Vig&ecirc;ncia</strong></div>
							</td>
							<td colspan="6" bgcolor="#90c7fc">
							<div align="center"><strong>Indicadores de Sele&ccedil;&atilde;o</strong></div>
							</td>
						</tr>
						<tr bordercolor="#000000">
							<td width="12%" bgcolor="#90c7fc">
							<div align="center"><strong>Im&oacute;vel com
							Situa&ccedil;&atilde;o Especial de Cobran&ccedil;a</strong></div>
							</td>
							<td width="9%" bgcolor="#90c7fc">
							<div align="center"><strong>Im&oacute;vel com
							Situa&ccedil;&atilde;o Cobran&ccedil;a</strong></div>
							</td>
							<td width="8%" bgcolor="#90c7fc">
							<div align="center"><strong>Considerar Contas em Revis&atilde;o</strong></div>
							</td>
							<td width="8%" bgcolor="#90c7fc">
							<div align="center"><strong>Im&oacute;vel com D&eacute;bito
							s&oacute; na Conta do M&ecirc;s</strong></div>
							</td>
							<td width="9%" bgcolor="#90c7fc">
							<div align="center"><strong>Inquilino com D&eacute;bito s&oacute;
							na Conta do M&ecirc;s</strong></div>
							</td>
							<td width="12%" bgcolor="#90c7fc">
							<div align="center"><strong>Im&oacute;vel com D&eacute;bito
							s&oacute; de Contas Antigas</strong></div>
							</td>
						</tr>

						<%String cor = "#cbe5fe";%>
						<logic:present name="colecaoCriterioCobranca" scope="session">
							<logic:iterate name="colecaoCriterioCobranca" id="cobrancaCriterio" type="CobrancaCriterio">
								<%if (cor.equalsIgnoreCase("#cbe5fe")) {
				cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
				cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>
									<td>
									<div align="center"><strong> <span class="style1"><strong> 
									<%   CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando = (CobrancaAcaoAtividadeComando)session.getAttribute("cobrancaAcaoAtividadeComando"); %>
									<% if (cobrancaAcaoAtividadeComando.getCobrancaCriterio() != null
											&& cobrancaAcaoAtividadeComando.getCobrancaCriterio().getId().toString().equals(((CobrancaCriterio) cobrancaCriterio).getId().toString())){ %>
									<logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">		
									<input
										type="radio" name="idComando" onclick="javascript:atualizar(<bean:write
											name="cobrancaCriterio" property="id"/>)"
										value="<bean:write
											name="cobrancaCriterio" property="id"/>" checked="true"> 
									</logic:empty>
									<logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">
									  <input type="radio" name="idComando" checked="true" disabled="true" >
									</logic:notEmpty>		
									<%}else{ %>	
									<logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">	
									<input
										type="radio" name="idComando" onclick="javascript:atualizar(<bean:write
											name="cobrancaCriterio" property="id"/>)"
										value="<bean:write
											name="cobrancaCriterio" property="id"/>">
									</logic:empty>											
									<logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">
									  <input type="radio" name="idComando" disabled="true" >
									</logic:notEmpty>
									<%} %>											
											
											</strong></span> </strong></div>
									</td>
									<td>
									<div align="center"><a
										href="javascript:abrirPopup('exibirInserirCriterioCobrancaLinhaConsultarPopupAction.do?idCriterioCobranca='+<bean:write
											name="cobrancaCriterio" property="id"/>, 350, 800);"><bean:write
											name="cobrancaCriterio" property="descricaoCobrancaCriterio" /></a></div>
									</td>
									<td>
									<div align="center"><bean:write
											name="cobrancaCriterio" format="dd/MM/yyyy" property="dataInicioVigencia" /></div>
									</td>
									<td>
									<div align="center"><bean:write
											name="cobrancaCriterio" property="numeroContaAntiga" /></div>
									</td>
									<td>
										<logic:equal name="cobrancaCriterio" property="indicadorEmissaoImovelParalisacao" value="1"> 
											<div align="center">SIM</div>
										</logic:equal>
										<logic:notEqual name="cobrancaCriterio" property="indicadorEmissaoImovelParalisacao" value="1"> 
											<div align="center">NÃO</div>
										</logic:notEqual>
									</td>
									<td>
										<logic:equal name="cobrancaCriterio" property="indicadorEmissaoImovelSituacaoCobranca" value="1"> 
											<div align="center">SIM</div>
										</logic:equal>
										<logic:notEqual name="cobrancaCriterio" property="indicadorEmissaoImovelSituacaoCobranca" value="1"> 
											<div align="center">NÃO</div>
										</logic:notEqual>
									</td>
									<td>
										<logic:equal name="cobrancaCriterio" property="indicadorEmissaoContaRevisao" value="1"> 
											<div align="center">SIM</div>
										</logic:equal>
										<logic:notEqual name="cobrancaCriterio" property="indicadorEmissaoContaRevisao" value="1"> 
											<div align="center">NÃO</div>
										</logic:notEqual>
									</td>
									<td>
										<logic:equal name="cobrancaCriterio" property="indicadorEmissaoDebitoContaMes" value="1"> 
											<div align="center">SIM</div>
										</logic:equal>
										<logic:notEqual name="cobrancaCriterio" property="indicadorEmissaoDebitoContaMes" value="1"> 
											<div align="center">NÃO</div>
										</logic:notEqual>
									</td>
									<td>
										<logic:equal name="cobrancaCriterio" property="indicadorEmissaoInquilinoDebitoContaMes" value="1"> 
											<div align="center">SIM</div>
										</logic:equal>
										<logic:notEqual name="cobrancaCriterio" property="indicadorEmissaoInquilinoDebitoContaMes" value="1"> 
											<div align="center">NÃO</div>
										</logic:notEqual>
									</td>
									<td>
										<logic:equal name="cobrancaCriterio" property="indicadorEmissaoDebitoContaAntiga" value="1"> 
											<div align="center">SIM</div>
										</logic:equal>
										<logic:notEqual name="cobrancaCriterio" property="indicadorEmissaoDebitoContaAntiga" value="1"> 
											<div align="center">NÃO</div>
										</logic:notEqual>
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
					</table>
					</td>
				</tr>
				<tr>
					<td height="17" colspan="5"><strong><font color="#FF0000"> </font></strong></td>
				</tr>
				<tr>
					<td colspan="3" align="right">
						<table border="0">
							<tr>
								<td align="left">	
								<logic:present name="corLocalidade">
									<logic:equal name="habilitarExecutar" value="true">
											<input
											name="Button32232" type="button" class="bottonRightCol"
											value="Executar Comando"
											onclick="executarcomando(idComando, 'radio')" />
									</logic:equal>
									<logic:notEqual name="habilitarExecutar" value="false">
											<input
											name="Button32232" type="button" class="bottonRightCol"
											value="Executar Comando"
											onclick="executarcomando(idComando, 'radio')" disabeld/>
									
									</logic:notEqual>
								</logic:present>
								</td>
								<td align="right">						
									<img src="imagens/voltar.gif" width="15" height="24">
								</td>
								<td align="right">						
									 <input	name="Button32222" type="button" class="bottonRightCol"
										value="Voltar" onClick="javascript:voltar('${requestScope.idCobrancaAcaoAtividadeComando}');" />
								</td>
								<td align="right">						
								  <gsan:controleAcessoBotao name="Button322" value="Concluir" onclick="concluircomando(idComando, 'radio');" url="manterComandoAcaoCobrancaEventualCriterioComandoConcluirAction.do"/>
								  <%-- <input name="Button322" type="button" class="bottonRightCol" value="Concluir" onclick="concluircomando(idComando, 'radio');" /> --%>
								</td>

							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="10%" height="17"><strong><font color="#FF0000"> </font></strong><strong><font
						color="#FF0000"> </font></strong><strong><font color="#FF0000">  </font></strong></td>
					<td width="23" colspan="2" align="right"></td>
					<td width="24%" colspan="2" align="right">
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
