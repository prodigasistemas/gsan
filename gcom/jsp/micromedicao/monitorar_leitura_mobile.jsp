<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>




<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="MonitorarLeituraMobileActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

var idRotaEscolhida;

function validaForm(form){

	if(testarCampoValorZero(document.MonitorarLeituraMobileActionForm.rota, 'Rota')  && 
	testarCampoValorZero(document.MonitorarLeituraMobileActionForm.mesAno, 'Mês de Referência')) {


		if(validateMonitorarLeituraMobileActionForm(form)){
				
				form.action = 'monitorarLeituraMobileAction.do?idRota='+idRotaEscolhida ;
    			submeterFormPadrao(form);
			

     }
  }
}
  
  function limparForm(form) {
		form.rota.value = "";
		form.descricaoRota.value = "";
		form.mesAno.value = "";
		form.leiturista.value = "";
		
	}

	 

function receberRota(idRota, descricao, codigoRota) {
 	  var form = document.forms[0];
	 form.rota.value = codigoRota;
	 form.descricaoRota.value = descricao;
	 idRotaEscolhida =idRota;	 
	  
}

function limparRota(form){
	form.rota.value = "";
	form.descricaoRota.value = "";
}




</script>


</head>

<body leftmargin="5" topmargin="5">
<html:form action="/monitorarLeituraMobileAction"
	name="MonitorarLeituraMobileActionForm"
	type="gcom.gui.micromedicao.MonitorarLeituraMobileActionForm"
	method="post"
	onsubmit="return validateMonitorarLeituraMobileActionForm(this);">
	
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="130" valign="top" class="leftcoltext">
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
			<td width="615" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>


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
					<td class="parabg">Monitorar Leituras Transmitidas</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td height="10" colspan="3">Para listar as leituras já realizadas, 
					informe os dados abaixo:</td>
					<td align="right"></td>
				</tr>
				
				<tr>

					<td height="10" width="145"><strong>Mês de Referência :<font
						color="#FF0000">*</font></strong></td>
					<td><html:text property="mesAno" size="8" maxlength="7"
						tabindex="1" onkeyup="mascaraAnoMes(this, event);" /></td>

				</tr>
				
				<tr>
					<td><strong>Código da Rota:</strong></td>
					<td>
						<html:text maxlength="4" tabindex="1"
							property="rota" size="4"
							readonly="true" />
						<a
							href="javascript:abrirPopup('exibirPesquisarInformarRotaLeituraAction.do?caminhoRetornoTelaPesquisaRota=exibirPesquisarInformarRotaLeituraAction');">
						<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Rota" /></a> 
					
										
							<html:text property="descricaoRota" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />						
						
					<a
						href="javascript:limparRota(document.MonitorarLeituraMobileActionForm);">
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" />
					</a>
						
					</td>
					
				</tr>						
				
				<tr>

					<td height="10" width="145"><strong>Leiturista :</strong></td>
					<td colspan="2" align="left">
						<html:text property="leiturista" size="50" maxlength="50" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"/>
					</td>

				</tr>
				
				
								
				<tr>
					<td></td>
				</tr>
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirMonitorarLeituraMobileAction.do?menu=sim"/>'">
					<input type="button" name="ButtonCancelar" class="bottonRightCol"
						value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"></td>
					<td align="right"><%--<INPUT type="button" class="bottonRightCol" value="Inserir" tabindex="13" style="width: 70px;" onclick="validarForm(document.forms[0]);">--%>
			
			
					 <gsan:controleAcessoBotao name="Botao" value="Listar"
								onclick="validaForm(document.forms[0]);"
								url="monitorarLeituraMobileAction.do" tabindex="13" />
					</td>
				</tr>
				
				<tr>
					<!--<td colspan="4" bgcolor="#3399FF"> -->
					<td colspan="5" bgcolor="#000000" height="2" valign="baseline"></td>
				</tr>

				
				<table width="100%" align="center" bgcolor="#90c7fc" border="0"
					cellpadding="0" cellspacing="0">
					<tr bgcolor="#cbe5fe">
						<td width="100%" align="center">
						<table width="100%" bgcolor="#99CCFF">
							<tr bordercolor="#000000" bgcolor="#90c7fc">
								<td width="25%" bgcolor="#90c7fc">
								<div align="center"><strong>Inscrição</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Matrícula</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Sequencial de Rota</strong></div>
								</td>
								<td width="11%" bgcolor="#90c7fc">
								<div align="center"><strong>Leitura Anterior</strong></div>
								</td>
								<td width="11%" bgcolor="#90c7fc">
								<div align="center"><strong>Leitura Atual</strong></div>
								</td>
								<td width="7%" bgcolor="#90c7fc">
								<div align="center"><strong>Anormalidade</strong></div>
								</td>
								<td width="20%" bgcolor="#90c7fc">
								<div align="center"><strong>Data e Hora da Leitura</strong></div>
								</td>
								<td width="20%" bgcolor="#90c7fc">
								<div align="center"><strong>Data e Hora de Recebimento</strong></div>
								</td>
								
							</tr>
							
							<!--<logic:present name="colecao"> -->
								
								<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
									export="currentPageNumber=pageNumber;pageOffset" maxPageItems="10"
									items="${sessionScope.totalRegistros}">
									<pg:param name="q" />
							
								<%int cont = 0;%>
								<logic:iterate name="colecao"
									id="movimento">
									<pg:item>
									<%cont = cont + 1;
							if (cont % 2 == 0) {%>
									<tr bgcolor="#cbe5fe">
										<%} else {%>
									<tr bgcolor="#FFFFFF">
										<%}%>
										<td width="7%">
											<div align="center">
										 		${movimento.inscricao}
											</div>
										</td>
										<td width="15%" align="center">
											${movimento.imovel.id}</td>
										<td width="15%">
											<div align="center">${movimento.imovel.numeroSequencialRota}</div>
										</td>
										<td width="15%">
											<div align="center">${movimento.numeroLeituraAnterior}</div>
										</td>
										<td width="18%" align="center">
										${movimento.numeroLeituraHidrometro}
										</td>
										<td width="15%" align="center">
										<c:choose>
										 <c:when test='${movimento.leituraAnormalidade != null && movimento.leituraAnormalidade.id != null }'>
											${movimento.leituraAnormalidade.id}
										 </c:when>
										 <c:otherwise>
										 	0									 
										 </c:otherwise>									
										</c:choose>
										
										</td>
										
										<td width="27%">
										<div align="center"><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${movimento.tempoLeitura}"/> </div>
										</td>
										
										<td width="27%">
										<div align="center"><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${movimento.ultimaAlteracao}"/> </div>
										</td>

																				

									</tr>
									</pg:item>
								</logic:iterate>
								</table>
							<table width="100%" border="0">
							<tr>
								<td>
								<div align="center"><strong><%@ include
									file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
								</td>
								</pg:pager>
								<%-- Fim do esquema de paginação --%>
							</tr>
						</table>	
								
							<!--</logic:present> -->
						
						
											
					</td>
				  </tr>
				</table>
			</table>
			<p>&nbsp;</p>
		</tr>
		<!-- Rodapé -->
		<%@ include file="/jsp/util/rodape.jsp"%>
	</table>
	<p>&nbsp;</p>

	<tr>

	</tr>

</html:form>
</body>
</html:html>

