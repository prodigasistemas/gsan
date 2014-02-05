<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<html:javascript staticJavascript="false" formName="FiltrarAlteracaoAtualizacaoCadastralActionForm" />	
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<%@ page
	import="gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper"%>

	<script language="JavaScript">

 
</script>
<script language="JavaScript">
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

	function confirma() {
		var form = document.forms[0];
		var valoresNao = "";
		var valoresSim = "";
		// Varre os checkbox que estao desmarcados.
		for (var i = 0;i < form.elements.length; i++){
			var elemento = form.elements[i];
			if (elemento.type == "checkbox" && elemento.name == "chkRegistrosAlteracao"){
				if (elemento.checked == false) {
					valoresNao += (valoresNao != "" ? "," : "") + elemento.value;
				} else {
					valoresSim += (valoresSim != "" ? "," : "") + elemento.value;
				}
			}
		}
		
		form.idRegistrosAutorizados.value = valoresSim;
		form.idRegistrosNaoAutorizados.value = valoresNao;

		form.submit();
		window.close();
	}
 
</script>
</head>

<body leftmargin="5" topmargin="5" onload="facilitador(0);">

<html:form action="/atualizarDadosCadastraisViaMovimentoAction"
	name="FiltrarAlteracaoAtualizacaoCadastralActionForm"
	type="gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionForm"
	method="post">
	<html:hidden property="idRegistrosNaoAutorizados" />
	<html:hidden property="idRegistrosAutorizados" />

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
			<td width="600" valign="top" class="centercoltext">
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
	<table align="center" border="0" cellpadding="0" cellspacing="0"
		width="100%">
		<tr>
			<td width="11"><img src="imagens/parahead_left.gif" border="0"></td>

			<td class="parabg">Consultar Movimento de Atualizações Cadastrais</td>
			<td valign="top" width="11"><img src="imagens/parahead_right.gif"
				border="0"></td>
		</tr>
	</table>
	<!--Fim Tabela Reference a P&aacute;gina&ccedil;&atilde;o da Tela de Processo-->
	<p>&nbsp;</p>
	<table>
	  <tr>
		<td width="100"><strong>Empresa:</strong></td>
		<td width="432"><html:text tabindex="10" maxlength="40"
			property="nomeEmpresa" size="40" disabled="true"/> 
		</td>
	  </tr>
	</table>
	<table>		
	 <tr>
		<td colspan="2">
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="2" height="23"><font color="#000000"
						style="font-size:10px"
						face="Verdana, Arial, Helvetica, sans-serif"><strong>Movimento de Atualização Cadastral:</strong></font></td>
				</tr>
				<tr>
					<td colspan="2" bgcolor="#000000" height="2"></td>
				</tr>
		
				<tr>
					<td>
					<table width="590" bgcolor="#99CCFF" border="0">
						<tr bgcolor="#99CCFF">
							<td width="40" bgcolor="#90c7fc">
							<div align="center"><strong><a
								href="javascript:facilitador(this);">Todos</a></strong></div>
							</td>
							<td width="70" bgcolor="#90c7fc">
							<div align="center"><strong>Imóvel</strong></div>
							</td>
							<td width="90" bgcolor="#90c7fc">
							<div align="center"><strong>Qtde. Campos Alterados</strong></div>
							</td>
							<td width="70" bgcolor="#90c7fc">
							<div align="center"><strong>Cliente</strong></div>
							</td>
							<td width="90" bgcolor="#90c7fc">
							<div align="center"><strong>Qtde. Campos Alterados</strong></div>
							</td>
							<td width="200" bgcolor="#90c7fc">
							<div align="center"><strong>Agente Cadastral</strong></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<%int cont = 0;%>
				<logic:present name="colecaoConsultarMovimentoAtualizacaoCadastralHelper">
				<tr>
					<td width="590" colspan="5">
					<div style="width: 100%; height: 300; overflow: auto;">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<logic:iterate name="colecaoConsultarMovimentoAtualizacaoCadastralHelper" id="consultarMovimentoAtualizacaoCadastralHelper"
							type="ConsultarMovimentoAtualizacaoCadastralHelper">
							<%cont = cont + 1;
							if (cont % 2 == 0) {%>
							<tr bgcolor="#cbe5fe">
							<%} else {%>
							<tr bgcolor="#FFFFFF">
							<%}%>
						        <td width="40" align="center">
 						        <%		
						                String checado = "";
										if (consultarMovimentoAtualizacaoCadastralHelper.getIcAutorizado() == 1) {
												checado = "checked";
										}
								%>						    
						         <input type="checkbox" name="chkRegistrosAlteracao" id="chkRegistrosAlteracao" <%=checado%>
 									value="<bean:write name="consultarMovimentoAtualizacaoCadastralHelper" property="icAutorizado"/>" /> 								 

								</td>
								
								<logic:equal name="consultarMovimentoAtualizacaoCadastralHelper" property="inscricao" value="">
								  <td width="70">
									<logic:equal name="consultarMovimentoAtualizacaoCadastralHelper" property="qtdAlteracaoImovel" value="0">
											<div align="center"><bean:write name="consultarMovimentoAtualizacaoCadastralHelper"
												property="idImovel" /></div>
									</logic:equal> 
									<logic:notEqual name="consultarMovimentoAtualizacaoCadastralHelper" property="qtdAlteracaoImovel" value="0">
										<div align="center"><a href="exibirAtualizarDadosImovelAtualizacaoCadastralPopupAction.do?idImovel=<bean:write name="consultarMovimentoAtualizacaoCadastralHelper" property="idImovel"/>&idArquivo=<bean:write name="consultarMovimentoAtualizacaoCadastralHelper" property="idArquivo"/>&idRegistroAlterado=<bean:write name="consultarMovimentoAtualizacaoCadastralHelper" property="idRegistroAlterado"/>&idTipoAlteracao=<bean:write name="consultarMovimentoAtualizacaoCadastralHelper" property="idTipoAlteracao"/>">
										<bean:write	name="consultarMovimentoAtualizacaoCadastralHelper" property="idImovel"/></a></div>
									</logic:notEqual>																		
								  </td>
								</logic:equal> 
								<logic:notEqual name="consultarMovimentoAtualizacaoCadastralHelper" property="inscricao" value="">
									<td width="70">
										<div align="center"><a href="exibirAtualizarDadosImovelAtualizacaoCadastralPopupAction.do?idImovel=<bean:write name="consultarMovimentoAtualizacaoCadastralHelper" property="idImovel"/>&idArquivo=<bean:write name="consultarMovimentoAtualizacaoCadastralHelper" property="idArquivo"/>&idRegistroAlterado=<bean:write name="consultarMovimentoAtualizacaoCadastralHelper" property="idRegistroAlterado"/>&idTipoAlteracao=<bean:write name="consultarMovimentoAtualizacaoCadastralHelper" property="idTipoAlteracao"/>">
										<bean:write	name="consultarMovimentoAtualizacaoCadastralHelper" property="inscricao"/></a></div>
									</td>
								</logic:notEqual>
								
								<td width="90">
								<div align="center"><bean:write name="consultarMovimentoAtualizacaoCadastralHelper"
									property="qtdAlteracaoImovel" /></div>
								</td>
								<td width="70">
  								<div align="center">
                                  <a href="exibirAtualizarDadosImovelAtualizacaoCadastralPopupAction.do?idImovel=<bean:write name="consultarMovimentoAtualizacaoCadastralHelper" property="idImovel"/>&idCliente=<bean:write name="consultarMovimentoAtualizacaoCadastralHelper" property="idCliente"/>&idArquivo=<bean:write name="consultarMovimentoAtualizacaoCadastralHelper" property="idArquivo"/>&idRegistroAlterado=<bean:write name="consultarMovimentoAtualizacaoCadastralHelper" property="idRegistroAlterado"/>&idTipoAlteracao=<bean:write name="consultarMovimentoAtualizacaoCadastralHelper" property="idTipoAlteracao"/>">
    								<logic:notEqual name="consultarMovimentoAtualizacaoCadastralHelper" property="clienteNovo" value="NOVO">
    								  <bean:write	name="consultarMovimentoAtualizacaoCadastralHelper" property="idCliente"/>
    								</logic:notEqual>
    								<logic:equal name="consultarMovimentoAtualizacaoCadastralHelper" property="clienteNovo" value="NOVO">
    								  <bean:write	name="consultarMovimentoAtualizacaoCadastralHelper" property="clienteNovo"/>
    								</logic:equal>
                                  </a>
                                </div>														
								</td>
								<td width="90">
								<div align="center"><bean:write name="consultarMovimentoAtualizacaoCadastralHelper"
									property="qtdAlteracaoCliente" /></div>
								</td>
								<td width="180">
									<logic:equal name="consultarMovimentoAtualizacaoCadastralHelper"
										property="nomeCliente" value="">
											<div align="center"><bean:write name="consultarMovimentoAtualizacaoCadastralHelper"
												property="nomeFuncionario" /></div>
									</logic:equal> 
									<logic:equal name="consultarMovimentoAtualizacaoCadastralHelper"
										property="nomeFuncionario" value="">
										<div align="center"><bean:write name="consultarMovimentoAtualizacaoCadastralHelper"
											property="nomeCliente" /></div>
									</logic:equal>													
								</td>
							</tr>
						</logic:iterate>
					</table>
					</div>
					</td>
				</tr>
				</logic:present>
			</table>
		</td>
	 </tr>
						
	 <tr>
	 <td align="left">
	     <table border="0" width="100%">
		   <tr>
			<td align="left" width="50%">					
			<input type="button" name="Button" class="bottonRightCol"
				value="Voltar" onclick="window.location.href='/gsan/exibirFiltrarAlteracaoAtualizacaoCadastralAction.do?menu=sim';">	
			</td>
		  </tr>
		</table>
	   </td>
	  <!-- <td align="right">
	     <table border="0" width="100%">
		   <tr>
			<td align="right" width="50%">					
			 <input name="Button322222" type="button"
				class="bottonRightCol" value="Atualizar"
				onClick="confirma();" />
			</td>
		  </tr>
		</table>
	   </td>-->
	 </tr>
	</table>	 
	 
</td>
</tr>
</table>



<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
<%@ include file="/jsp/util/telaespera.jsp"%>

</body>
</html:html>