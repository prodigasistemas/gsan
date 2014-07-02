<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@page isELIgnored="false"%>
<%@ page import="gcom.cadastro.atualizacaocadastral.bean.DadosTabelaAtualizacaoCadastralHelper"%>
<%@ page import="gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>geral.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
	function confirma() {    
		var form = document.forms[0];
		var valoresNao = "";
		var valoresSim = "";
		// Varre os checkbox que estao desmarcados.
		for (var i = 0;i < document.forms[0].elements.length; i++){
			var elemento = document.forms[0].elements[i];
			if (elemento.type == "checkbox" && elemento.name == "chkRegistrosAlteracao"){
				if (elemento.checked == false) {
					valoresNao += (valoresNao != "" ? "," : "") + elemento.value;
				} else {
					valoresSim += (valoresSim != "" ? "," : "") + elemento.value;
				}
			}
		}
		
		if(valoresNao == "" && valoresSim == ""){
			alert('Informe um registro para atualização.');
		}else{
		
			form.idRegistrosAutorizados.value = valoresSim;
			form.idRegistrosNaoAutorizados.value = valoresNao;
	
			form.submit();
		}
	}
	
	function verificarTipoAlteracao(idTipoAlteracao,objeto){
	  var form = document.forms[0];
	  if(idTipoAlteracao == '' || idTipoAlteracao == '2'){
	    facilitador(objeto);
	  }
	
	}
	
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

	function fiscalizar() {
		var form = document.forms[0];
		form.action = 'fiscalizarImovelAtualizacaoCadastralAction.do';
	  	form.submit();
	}
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/atualizarDadosImovelAtualizacaoCadastralAction"
	name="ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm"
	type="gcom.gui.cadastro.ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm" method="post">
  
<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>
  
	<html:hidden property="idRegistrosNaoAutorizados" />
	<html:hidden property="idRegistrosAutorizados" />

	<table width="1000" border="0" cellpadding="0" cellspacing="5">
		<tr>
      <td width="160" valign="top" class="leftcoltext">
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
    
			<td width="1000" valign="top" class="centercoltext">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Consultar dados do Im&oacute;vel para Atualiza&ccedil;&atilde;o Cadastral</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>Dados do Im&oacute;vel:</td>
					<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=imovelInformarEconomia-Inserir', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>											
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td width="100"><strong>Im&oacute;vel:</strong></td>
					<td align="left"><html:text property="descricaoImovel" readonly="true" size="30"
						styleClass="texto-exibicao" /></td>
				</tr>
				<tr>
					<td><strong>Localidade:</strong></td>
					<td align="left"><html:text property="descricaoLocalidade" readonly="true" size="60" styleClass="texto-exibicao" /></td>
				</tr>
				<tr>
					<td><strong>Setor Comercial:</strong></td>
					<td align="left"><html:text property="descricaoSetorComercial" readonly="true" size="60" styleClass="texto-exibicao"/></td>
				</tr>
				<tr>
					<td><strong>Quadra:</strong></td>
					<td align="left"><html:text property="numeroQuadra" readonly="true" size="8" styleClass="texto-exibicao" /></td>
				</tr>
				
				<logic:equal name="fiscalizado" value="false" scope="session">
					<tr>
						<td>&nbsp;</td>
						<td align="right"><input name="Button" type="button" class="bottonRightCol" value="Fiscalizar" onClick="fiscalizar();"></td>
					</tr>
				</logic:equal>
			</table>
			
			
			
			<table width="100%" border="0">
			
				<logic:present name="colecaoImagens">

					<tr><td><hr></td></tr>
					
					<tr>
						<td colspan="2">Imagens Carregadas:</td>
					</tr>
				
					<tr>
						<td colspan="2">
							<table bgcolor="#90c7fc" border=0>
								<tr bordercolor="#90c7fc" bgcolor="#90c7fc">
									<td align="center"><strong>Nome da Imagem</strong></td>
									<td align="center"><strong>Download</strong></td>
								</tr>
								<%int i = 1;%>
								<logic:iterate name="colecaoImagens" id="item">
									<tr>
										<%i = i + 1;
										if (i % 2 == 0) {%>
											<tr bgcolor="#FFFFFF">
										<%} else {%>
											<tr bgcolor="#cbe5fe">
										<%}%>
										<td align="center">
											<bean:write name="item" property="nomeImagem" />
										</td>
										<td align="center">
											<a href="javascript:abrirPopup('exibirImagemRetornoAtualizacaoCadastralAction.do?id=<bean:write name="item" property="id"/>', 400, 800);">
												<img width="18" height="18" src="<bean:message key="caminho.imagens"/>imgfolder.gif" border="0" />
											</a> 
										</td>
									</tr>
								</logic:iterate>
							</table>
						</td>
					</tr>
				</logic:present>
				
				<tr><td><hr></td></tr>
			
				<tr>
					<td colspan="2">Tabelas Atualizadas:</td>
				</tr>
				<tr>
					<td colspan="2">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td height="0">

									<table width="100%" bgcolor="#90c7fc" border=0>
										<tr bordercolor="#90c7fc" bgcolor="#90c7fc">
											<td width="150"><strong>Tabela</strong></td>
											<td width="150"><strong>Campo</strong></td>
    										<td width="200" align="center"><strong>Anterior</strong></td>
    										<td width="200" align="center"><strong>Atual</strong></td>
											<td width="200" align="center" ><strong>Data/Hora Valida&ccedil;&atilde;o</strong></td>
											<td width="100" align="center"><strong>Usu&aacute;rio</strong></td>
											<td width="50"><strong><a href="javascript:facilitador(this);">Todos</a></strong></td>
										</tr>
									</table>

								</td>
							</tr>
							<tr>
								<td>

									<table width="100%" align="center" bgcolor="#99CCFF" border=0>
									<%int cont = 1;%>
									<logic:iterate name="colecaoDadosTabelaAtualizacaoCadastral" id="item">
                                        <logic:equal name="item" property="informativo" value="true">
                                            <tr bgcolor="#e9e0e2">
                                        </logic:equal>
                                        <logic:notEqual name="item" property="informativo" value="true">
    										<%cont = cont + 1;
    										if (cont % 2 == 0) {%>
    											<tr bgcolor="#FFFFFF">
    										<%} else {%>
    											<tr bgcolor="#cbe5fe">
    										<%}%>
                                        </logic:notEqual>
    										<td width="150">
    											<bean:write name="item" property="descricaoTabela" />
    										</td>
    										<td width="150">
    											<bean:write name="item" property="descricaoColuna" />
    										</td>
    										<td width="200">
    											<bean:write name="item" property="colunaValorAnterior" />
    										</td>
    										<td width="200">
    											<bean:write name="item" property="colunaValorAtual" />
    										</td>
    										<td width="200">
    											<bean:write name="item" property="dataValidacao" />
    										</td>
                                            <td width="100">
                                                <bean:write name="item" property="nomeUsuario" />
                                            </td>
    										<td width="50">
    										<div align="center">
                                                  <logic:equal name="item" property="habilitaAlteracao" value="true">
    											<input type="checkbox" name="chkRegistrosAlteracao" id="chkRegistrosAlteracao" 
    											value="<%=""+((DadosTabelaAtualizacaoCadastralHelper) item).getIdTabelaColunaAtualizacaoCadastral()%>" />
    										  </logic:equal>
    										</div>
    										</td>
    									</tr>
									</logic:iterate>
									</table>

								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td height="24">
					<div align="right"><input type="hidden" name="testeInserir"> 
					<input
						name="Button" type="button" class="bottonRightCol" value="Confirmar"
						onClick="confirma();">
					<input name="Button2" type="button" class="bottonRightCol"
						value="Fechar" onClick="window.location.href='/gsan/filtrarAlteracaoAtualizacaoCadastralAction.do'"></div>
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
