<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.cadastro.sistemaparametro.SistemaParametro"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript">
	
	function limparForm(){
		var form = document.forms[0];
		form.action = 'exibirGerarRelatorioOSSituacaoAction.do?limparForm=Sim';
		form.submit();
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.GerarRelatorioOSSituacaoActionForm;

	    if (tipoConsulta == 'localidade') {
	      form.idLocalidade.value = codigoRegistro;
	      form.descricaoLocalidade.value = descricaoRegistro;
	      form.descricaoLocalidade.style.color = "#000000";

	      limparPesquisaQuadra();
	      limparPesquisaSetorComercial();
	      
	    }else if(tipoConsulta == 'setorComercial'){
	    	form.idSetorComercial.value = codigoRegistro;
	    	form.descricaoSetorComercial.value = descricaoRegistro;
	    	form.descricaoSetorComercial.style.color = "#000000";
	    	
	    	limparPesquisaQuadra();
	    }else if(tipoConsulta == 'elo') {
			form.idEloPolo.value = codigoRegistro;
		    form.descricaoEloPolo.value = descricaoRegistro;
		    form.descricaoEloPolo.style.color = "#000000";
	    }else if(tipoConsulta == 'tipoServico') {
			form.idTipoServico.value = codigoRegistro;
		    form.descricaoTipoServico.value = descricaoRegistro;
		    form.descricaoTipoServico.style.color = "#000000";
	    }
	}
	
	function validarForm(){
		var form = document.forms[0];
		if(validateGerarRelatorioOSSituacaoActionForm(form)){
			if(form.opcaoRelatorio[0].checked == true || form.opcaoRelatorio[1].checked == true){
				toggleBox('demodiv', 1);
			}else{
				alert("Informe a opção do relatório");
			}
		}
	}
	
	function changeEmpresa(){
		var form = document.forms[0];
		
		form.action = 'exibirGerarRelatorioOSSituacaoAction.do?empresaSelecionada=Sim';
		form.submit();
	}
	
	function changeGrupoCobranca(){
		var form = document.forms[0];
		
		form.action = 'exibirGerarRelatorioOSSituacaoAction.do?grupoCobrancaSelecionado=Sim';
		form.submit();
	}
	
	function limparLocalidade() {
	    var form = document.GerarRelatorioOSSituacaoActionForm;
	    
      	form.idLocalidade.value = '';
      	form.descricaoLocalidade.value = '';
	}
	
	function limparEloPolo() {
    	var form = document.forms[0];

      	form.idEloPolo.value = "";
      	form.descricaoEloPolo.value = "";
  	}
	
	function limparPesquisaQuadra() {
	    var form = document.forms[0];
	    form.idQuadra.value = "";
	
		var msgQuadra = document.getElementById("msgQuadra");
		
		if (msgQuadra != null){
			msgQuadra.innerHTML = "";
		}
		
		if (form.idQuadraFace != null && form.idQuadraFace != 'undefined'){
			reiniciarListBox(form.idQuadraFace);
			
			var msgQuadraFace = document.getElementById("msgQuadraFace");
		
			if (msgQuadraFace != null){
				msgQuadraFace.innerHTML = "";
			}
		}
	}
	
	function limparPesquisaSetorComercial() {
		var form = document.forms[0];
	    form.idSetorComercial.value = "";
	    form.descricaoSetorComercial.value = "";
	}
	
	function limparTipoServico(){
		var form = document.forms[0];
	    form.idTipoServico.value = "";
	    form.descricaoTipoServico.value = "";
	}
	
	function verificarMesAno(mesAno){
		var form = document.forms[0];
		if(mesAno.value.length == 7){
			if(form.idGrupoCobranca.value != -1){
				changeGrupoCobranca();
			}
		}else{
			form.situacaoOS.disabled = true;
		}
	}
</script>

</head>

<html:javascript staticJavascript="false" formName="GerarRelatorioOSSituacaoActionForm" />

<body leftmargin="5" topmargin="5" onload="">

	<div id="formDiv">
		<html:form action="/exibirGerarRelatorioOSSituacaoAction" method="post" name="GerarRelatorioOSSituacaoActionForm"
			type="gcom.gui.relatorio.atendimentopublico.GerarRelatorioOSSituacaoActionForm">


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
						<table height="100%">
							<tr>
								<td></td>
							</tr>
						</table>

						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td width="11">
									<img border="0"	src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
								</td>
								<td class="parabg">
									Gerar Relatório OS Situação
								</td>
								<td width="11">
									<img border="0"	src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
								</td>
							</tr>
						</table>

						<p>&nbsp;</p>

						<table width="100%" border="0">
							<tr>
								<td colspan="2">
									Para filtrar a(s) OS, informe os dados abaixo:
								</td>
							</tr>

							<tr>
								<td>&nbsp;</td>
								<td colspan="2" align="right">
									<div align="left">
										<strong> </strong>
									</div>
								</td>
							</tr>

							<tr>
								<td>
									<strong>Tipo do Relatório:<font	color="#FF0000">*</font></strong>
								</td>
								<td colspan="2">
									<html:radio property="opcaoRelatorio" value="1"  /> <strong>Analítico</strong>&nbsp;
									<html:radio property="opcaoRelatorio" value="2" /> <strong>Sintético</strong>
								</td>
							</tr>

							<tr>
								<td>
									<strong>Mês/Ano de Referência:<font color="#FF0000">*</font></strong>
								</td>
								<td colspan="2">
									<html:text property="dataReferencia" size="7" maxlength="7" tabindex="3"
										onkeyup="mascaraAnoMes(this, event);verificarMesAno(this, event);" onkeypress="return isCampoNumerico(event);"/>
									mm/aaaa
								</td>
							</tr>

							<tr>
								<td>
									<strong>Empresa:<font color="#FF0000">*</font></strong>
								</td>
								<td colspan="2">
									<html:select property="idEmpresa" tabindex="4" onchange="javascript:changeEmpresa();">
										<html:option value="-1">&nbsp;</html:option>
										<html:options collection="colecaoEmpresa" labelProperty="descricao" property="id" />
									</html:select> <font size="1">&nbsp; </font>
								</td>
							</tr>

							<logic:present name="gruposCobranca" scope="session">
								<tr>
									<td>
										<strong>Grupo de Cobrança:<font color="#FF0000">*</font></strong>
									</td>
									<td colspan="2">
										<html:select property="idGrupoCobranca" tabindex="5" onchange="javascript:changeGrupoCobranca()">
											<html:option value="-1">&nbsp;</html:option>
											<html:options collection="colecaoCobrancaGrupo" labelProperty="descricao" property="id" />
										</html:select> 
										<font size="1">&nbsp; </font>
									</td>
								</tr>
							</logic:present>

							<logic:notPresent name="gruposCobranca" scope="session">
								<tr>
									<td>
										<strong>Grupo de Cobrança:<font	color="#FF0000">*</font></strong>
									</td>
									<td colspan="2">
										<html:select property="idGrupoCobranca" tabindex="5" disabled="true" style="background-color:#EFEFEF; border:0">
											<html:option value="-1">&nbsp;</html:option>
										</html:select> 
										<font size="1">&nbsp; </font>
									</td>
								</tr>
							</logic:notPresent>
							
							<logic:present name="situacaoOSEncontrada" scope="session">
								
								<logic:present name="boletimGerado" scope="session">
									<tr>
										<td>
											<strong>Situação OS:<font color="#FF0000">*</font></strong>
										</td>
										<td colspan="2">
											<html:select property="situacaoOS" tabindex="6">
												<html:option value="-1">&nbsp;</html:option>
												<html:option value="1" >DESCONTADAS</html:option>
												<html:option value="2" >ENCERRADAS</html:option>
												<html:option value="3" >EXECUTADAS</html:option>
												<html:option value="4" >FISCALIZADAS</html:option>
												<html:option value="5" >JUSTIFICADAS</html:option>
												<html:option value="6" >PENALIZADAS POR FISCALIZAÇÃO</html:option>
												<html:option value="7" >PENALIZADAS POR DECURSO DE PRAZO</html:option>
												<html:option value="8" >TODAS</html:option>
											</html:select> 
											<font size="1">&nbsp; </font>
										</td>
									</tr>
								</logic:present>
									
								<logic:notPresent name="boletimGerado" scope="session">
									<tr>
										<td>
											<strong>Situação OS:<font color="#FF0000">*</font></strong>
										</td>
										<td colspan="2">
											<html:select property="situacaoOS" tabindex="6">
												<html:option value="-1">&nbsp;</html:option>
												<html:option value="9" >ENCERRADAS COM EXECUÇÃO</html:option>
												<html:option value="10" >ENCERRADOS POR DECURSO DE PRAZO</html:option>
												<html:option value="12" >FISCALIZADAS</html:option>
												<html:option value="11" >PENDENTES</html:option>
												<html:option value="13" >TODAS</html:option>
											</html:select> 
											<font size="1">&nbsp; </font>
										</td>
									</tr>
								</logic:notPresent>
							</logic:present>

							<logic:notPresent name="situacaoOSEncontrada" scope="session">
								<tr>
									<td>
										<strong>Situação OS:<font	color="#FF0000">*</font></strong>
									</td>
									<td colspan="2">
										<html:select property="situacaoOS" tabindex="6" disabled="true" style="background-color:#EFEFEF; border:0">
											<html:option value="-1">&nbsp;</html:option>
										</html:select> 
										<font size="1">&nbsp; </font>
									</td>
								</tr>
							</logic:notPresent>

							<tr>
								<td>
									<strong>Gerência Regional:</strong>
								</td>
								<td colspan="2">
									<html:select property="idGerenciaRegional" tabindex="7"> 
										<html:option value="-1">&nbsp;</html:option>
										<html:options collection="colecaoGerenciaRegional" labelProperty="nome" property="id" />
									</html:select>
								</td>
							</tr>

							<tr>
								<td>
									<strong>Unidade de Negócio:</strong>
								</td>
								<td colspan="2">
									<html:select property="idUnidadeNegocio" tabindex="8">
										<html:option value="-1">&nbsp;</html:option>
										<html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id" />
									</html:select>
								</td>
							</tr>

							<tr>
								<td>
									<strong>Localidade Pólo:</strong>
								</td>
								
								<td>
									<html:text maxlength="9" tabindex="1" property="idEloPolo" size="4"
										onkeypress="validaEnter(event, 'exibirGerarRelatorioOSSituacaoAction.do?pesquisarEloPolo=OK', 'idEloPolo'); return isCampoNumerico(event);" />
									
									<a href="javascript:abrirPopup('exibirPesquisarEloAction.do', 400, 800);">
										<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Elo Pólo" />
									</a> 
								</td>
								
								<td>
									<logic:present name="eloPoloEncontrado" scope="request">
										<html:text property="descricaoEloPolo" size="40" maxlength="30" 
											readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:present> 
			
									<logic:notPresent name="eloPoloEncontrado" scope="request">
										<html:text property="descricaoEloPolo" size="40" maxlength="30" 
											readonly="true" style="background-color:#EFEFEF; border:0; color: red" />
									</logic:notPresent>
									
									<a href="javascript:limparEloPolo();"> 
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
									</a>
								</td>
							</tr>

							<tr>
								<td>
									<strong>Localidade:</strong>
								</td>

								<td>
									<html:text property="idLocalidade" size="4" maxlength="4" tabindex="10"
										onkeyup="javascript:limparPesquisaQuadra();limparPesquisaSetorComercial();"
										onkeypress="validaEnter(event, 'exibirGerarRelatorioOSSituacaoAction.do?pesquisarLocalidade=OK', 'idLocalidade'); return isCampoNumerico(event);" />
									<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do', 400, 800);">
										<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Localidade"/>
									</a> 
								</td>

								<td>
									<logic:present name="localidadeEncontrada" scope="request">
										<html:text property="descricaoLocalidade" size="40" maxlength="40" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:present> 
									<logic:notPresent name="localidadeEncontrada" scope="request">
										<html:text property="descricaoLocalidade" size="40" maxlength="40" readonly="true"
											style="background-color:#EFEFEF; border:0; color: red" />
									</logic:notPresent>
									
									<a href="javascript:limparLocalidade();">
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
									</a>
								</td>
							</tr>

							<tr>
								<td>
									<strong>Setor Comercial:</strong>
								</td>

								<td height="24" colspan="2">
									<html:text property="idSetorComercial" size="4" maxlength="3" tabindex="11"
										onkeypress="validaEnter(event, 'exibirGerarRelatorioOSSituacaoAction.do?pesquisarSetorComercial=OK', 'idSetorComercial'); return isCampoNumerico(event);" />
									<a href="javascript:abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidade.value+'&tipo=SetorComercial',document.forms[0].idLocalidade.value,'Localidade', 400, 800);">
										<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Setor Comercial" />
									</a> 
									
									<logic:present name="setorComercialEncontrado" scope="request">
										<html:text property="descricaoSetorComercial" size="40" 
											readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:present> 

									<logic:notPresent name="setorComercialEncontrado" scope="request">
										<html:text property="descricaoSetorComercial" size="40" 
											readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
									</logic:notPresent> 
									
									<a href="javascript:limparPesquisaSetorComercial();limparPesquisaQuadra();document.forms[0].idSetorComercial.focus();">
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
									</a>
								</td>
							</tr>

							<tr>
								<td>
									<strong>Quadra:</strong>
								</td>

								<td height="24" colspan="2">
									<html:text maxlength="<%=""+SistemaParametro.NUMERO_DIGITOS_QUADRA%>" property="idQuadra" size="4" tabindex="12"
										onkeypress="return isCampoNumerico(event);" />
									
									<logic:present name="msgQuadra" scope="request">
										<logic:present name="codigoQuadraNaoEncontrada" scope="request">
											<span style="color:#ff0000" id="msgQuadra"><bean:write scope="request" name="msgQuadra"/></span>
										</logic:present>
										<logic:notPresent name="codigoQuadraNaoEncontrada" scope="request">
											<span style="color:#000000" id="msgQuadra"><bean:write scope="request" name="msgQuadra"/></span>
										</logic:notPresent> 
									</logic:present> 
								</td>
							</tr>

							<tr>
								<td>
									<strong>O.S. Não Cobradas Automaticamente:</strong>
								</td>
								<td colspan="2">
									<html:radio property="opcaoOSCobranca" value="todas" tabindex="13" /> 
									<strong>Todas</strong>&nbsp;
									<html:radio property="opcaoOSCobranca" value="naoCobradasAutomaticamente" tabindex="14" /> 
									<strong>Apenas O.S. Não Cobradas Automaticamente</strong>
								</td>
							</tr>

							<tr>
								<td>
									<strong>Tipo de Serviço:</strong>
								</td>

								<td height="24" colspan="2">
									<html:text property="idTipoServico" size="4" maxlength="4" tabindex="11"
										onkeypress="validaEnter(event, 'exibirGerarRelatorioOSSituacaoAction.do?pesquisarTipoServico=OK', 'idTipoServico'); return isCampoNumerico(event);" />
									<a href="javascript:abrirPopup('exibirPesquisarTipoServicoAction.do', 400, 800);">
										<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Setor Comercial" />
									</a> 
									
									<logic:present name="tipoServicoEncontrado" scope="request">
										<html:text property="descricaoTipoServico" size="40" 
											readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:present> 

									<logic:notPresent name="tipoServicoEncontrado" scope="request">
										<html:text property="descricaoTipoServico" size="40" 
											readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
									</logic:notPresent> 
									
									<a href="javascript:limparTipoServico(); document.forms[0].idTipoServico.focus();">
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
									</a>
								</td>
							</tr>

							<tr>
								<td>&nbsp;</td>
								<td colspan="2" align="right">
								<div align="left"><strong> </strong></div>
							</td>
							
							</tr>

							<tr>
								<td>
									<div align="left">
										<input type="button" name="limpar" class="bottonRightCol" value="Limpar" tabindex="15" 
											onclick="javascript:limparForm();"> 
										<input type="button" name="adicionar" class="bottonRightCol" value="Cancelar" tabindex="16"
											onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
									</div>
								</td>

								<td colspan="2">
									<div align="right">
										<input type="button" name="botaoConcluir" class="bottonRightCol" value="Gerar" tabindex="17"
											onclick="javascript:validarForm();">
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>

				<%@ include file="/jsp/util/rodape.jsp"%>
			</table>
		<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioOSSituacaoAction.do" />
		</html:form>
	</div>

	<%@ include file="/jsp/util/telaespera.jsp"%>
</body>

</html:html>