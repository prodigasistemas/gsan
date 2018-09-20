<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@page import="java.util.Collection" %>

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
<%@ page import="gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper"%>
<%@ page import="gcom.cadastro.SituacaoAtualizacaoCadastral"%>

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
	
	function aprovarSelecao(){
	  	var form = document.forms[0];
	  	form.action = "/gsan/aprovarImoveisEmLoteAction.do";
	  	form.submit();
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
	<p>&nbsp;</p>
	
	<table width="100%" border="0">
		<tr>
			<td width="150"><strong>Empresa:</strong></td>
			<td colspan="2" align="left">
				<html:text property="nomeEmpresa" size="40" disabled="true" />
			</td>
		</tr>
		
		<tr>
			<td width="150"><strong>Agente Cadastral:</strong></td>
			<td colspan="2" align="left">
				<html:text property="nomeLeiturista" size="40" disabled="true" />
			</td>
		</tr>
	 
	 	<tr>
			<td width="150"><strong>Período de Pré Aprovação:</strong></td>

			<td colspan="2">
				<html:text property="periodoInicial" size="11" disabled="true" /> a 
				<html:text property="periodoFinal" size="11" disabled="true" /> 
			</td>
		</tr>
		
		<tr>
			<td colspan="3"><hr></td>
		</tr>
		
		<tr>
			<td width="150"><strong>Lote de Fiscalização:</strong></td>
			<td colspan="2" align="left">
				<html:text property="lote" size="5" disabled="true" />
			</td>
		</tr>
		
		<tr>
			<td colspan="3"><hr></td>
		</tr>
		
		<tr>
			<td width="150"><strong>Dados da Inscrição Inicial:</strong></td>
		</tr>
	 	<tr>
			<td width="150">Localidade Inicial:</td>
			<td>
				<html:text value="${filtroMovimentoAtualizacaoCadastral.nomeLocalidadeInicial}" property="nomeLocalidadeInicial" size="20" disabled="true"/>
			</td> 
		</tr>
		<tr>
			<td width="150">Setor Comercial Inicial:</td>
			<td>
				<html:text value="${filtroMovimentoAtualizacaoCadastral.cdSetorComercialInicial}" property="cdSetorComercialInicial" size="5" disabled="true"/>
			</td>
		</tr>
		<tr>
			<td width="150">Rota Inicial:</td>
			<td>
				<html:text value="${filtroMovimentoAtualizacaoCadastral.cdRotaInicial}" property="cdRotaInicial" size="5" disabled="true"/>
			</td>
	 	</tr>
	 	<tr>
			<td colspan="3"><hr></td>
		</tr>
		
		<tr>
			<td colspan="3"><strong>Dados da Inscrição Final:</strong></td>
		</tr>
		<tr>
			<td width="150">Localidade Final:</td>
			<td>
			<html:text value="${filtroMovimentoAtualizacaoCadastral.nomeLocalidadeFinal}" property="nomeLocalidadeFinal" size="20" disabled="true"/>
		<tr>
			<td width="150">Setor Comercial Final:</td>
			<td>
				<html:text value="${filtroMovimentoAtualizacaoCadastral.cdSetorComercialFinal}" property="cdSetorComercialFinal" size="5" disabled="true"/>
			</td>
		</tr>
		<tr>	
			<td width="150">Rota Final:</td>
			<td>
				<html:text value="${filtroMovimentoAtualizacaoCadastral.cdRotaFinal}" property="cdRotaFinal" size="5" disabled="true"/>
			</td>
	    </tr>
	    <tr>
			<td colspan="3"><hr></td>
		</tr>
	</table>
	
	<p>&nbsp;</p>
	<table>		
	 <tr>
		<td colspan="2">
			<table width="600" cellpadding="0" cellspacing="0">
				<tr>
					<td width="40%"><strong>Movimento de Atualização Cadastral:</strong></td>
					<td width="20%">
					Total Im&oacute;veis: <strong><bean:write name="filtroMovimentoAtualizacaoCadastral" property="totalImoveis" /></strong>
					</td>
					<td width="40%" align="right">
		  				<input type="button" name="Button" class="bottonRightCol" value="Gerar Fichas de Fiscalização"
		  					onclick="window.location.href='/gsan/imprimirFichaFiscalizacaoCadastralAction.do';">  
					</td>
				</tr>
				<tr>
					<td colspan="3" bgcolor="#000000" height="2"></td>
				</tr>
		
				<tr>
					<td width="600" colspan="5">
					<div style="width: 100%; overflow: auto;">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr bgcolor="#99CCFF">
							<td width="25%" bgcolor="#90c7fc">
							  <div align="center"><strong>Im&oacute;vel</strong></div>
							</td>
							<td width="50%" bgcolor="#90c7fc">
							  <div align="center"><strong>Agente Cadastral</strong></div>
							</td>
							<td width="25%" bgcolor="#90c7fc">
							  <div align="center"><strong>Situa&ccedil;&atilde;o</strong></div>
							</td>
						</tr>
					</table>
					</div>
					</td>
				</tr>
				<%int cont = 0;%>
				<logic:present name="colecaoConsultarMovimentoAtualizacaoCadastralHelper">
				<tr>
					<td width="600" colspan="5">
					<div style="width: 100%; height: 300; overflow: auto;">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<logic:iterate name="colecaoConsultarMovimentoAtualizacaoCadastralHelper" id="helper"
							type="ConsultarMovimentoAtualizacaoCadastralHelper">
							<%cont = cont + 1;
							if (cont % 2 == 0) {%>
							<tr bgcolor="#cbe5fe" height="22">
							<%} else {%>
							<tr bgcolor="#FFFFFF" height="22">
							<%}%>
                                <td width="25%">
                                  <div align="center">
                                    <a href="exibirAtualizarDadosImovelAtualizacaoCadastralPopupAction.do?idImovel=<bean:write name="helper" property="idImovel"/>&idArquivo=<bean:write name="helper" property="idArquivo"/>&idTipoAlteracao=<bean:write name="helper" property="idTipoAlteracao"/>">
	                                    <logic:equal name="helper" property="idTipoAlteracao" value="1">
	                                        <bean:write name="helper" property="idImovel"/>  
	                                    </logic:equal>                                        
	                                    <logic:equal name="helper" property="idTipoAlteracao" value="2">
	                                        NOVO IM&Oacute;VEL 
	                                    </logic:equal>
	                                    <logic:equal name="helper" property="idTipoAlteracao" value="3">
	                                        <bean:write name="helper" property="idImovel"/> (EXCLUS&Atilde;O)
	                                    </logic:equal>                                        
                                    </a>
                                  </div>                                
                                </td>
                
								<td width="50%">
                                  <div align="center">
									<bean:write name="helper" property="nomeFuncionario" />
                                  </div>
								</td>
								
								<td width="25%">
                                  <div align="center">
									<%if (helper.getIdSituacao().equals(SituacaoAtualizacaoCadastral.EM_FISCALIZACAO)) {%>
									<font color="#FF0000">
									<%} else {%>
									<font color="#000000">
									<%}%>
										<bean:write name="helper" property="descricaoSituacao" />
									</font>
                                  </div>
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
				
				<logic:equal name="relatorio" value="true" scope="session">
	                <td align="right" width="50%">         
	                  <input type="button" name="Button" class="bottonRightCol"
	                    value="Imprimir Relat&oacute;rio" onclick="window.location.href='/gsan/imprimirConsultaAtualizacaoCadastralAction.do';">  
	                </td>
				</logic:equal>
				
			  	<logic:equal name="aprovacaoEmLote" value="true" scope="session">
					<td align="right" width="50%">					
						<input type="button" name="Button" class="bottonRightCol" value="Aprovar em Lote" onclick="aprovarSelecao();">	
					</td>
				</logic:equal>
				
				<logic:equal name="loteInformado" value="true" scope="session">
					<td align="right" width="50%">					
						<input type="button" name="Button" class="bottonRightCol" value="Aprovar o Lote" onclick="aprovarSelecao();">	
					</td>
				</logic:equal>
				
		  </tr>
		</table>
	   </td>
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