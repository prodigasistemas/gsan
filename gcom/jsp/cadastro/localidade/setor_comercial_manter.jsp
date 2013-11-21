<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="gcom.cadastro.localidade.SetorComercial"%>
<%@ page import="gcom.util.Pagina,gcom.util.ConstantesSistema"%>
<%@ page import="java.util.Collection"%>

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

<SCRIPT LANGUAGE="JavaScript">
function facilitador(objeto){
	if (objeto.id == "0" || objeto.id == undefined){
		objeto.id = "1";
		marcarTodos();
	}
	else{
		objeto.id = "0";
		desmarcarTodos();
	}

var bCancel = false; 

    function validatePesquisarAtualizarSetorComercialActionForm(form) {                                                                   
        if (bCancel) 
      return true; 
        else 
       return validateCaracterEspecial(form) && validateLong(form) && validateRequired(form); 
   } 

    function caracteresespeciais () { 
		this.ab = new Array("setorComercialNome", "Nome do Setor Comercial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ac = new Array("municipioID", "Município possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    } 

    function IntegerValidations () { 
     this.ab = new Array("municipioID", "Município deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    } 
    
    function required () {
     this.aa = new Array("setorComercialNome", "Informe Nome do Setor Comercial.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("municipioID", "Informe Município.", new Function ("varName", " return this[varName];"));
    }
}

function validarForm(formulario)
{
	if (validatePesquisarAtualizarSetorComercialActionForm(formulario)){
		submeterFormPadrao(formulario);
	}
}

// Verifica se há item selecionado
function verficarSelecao(objeto){

	if (CheckboxNaoVazio(objeto)){
		if (confirm ("Confirma remoção?")) {
			document.forms[0].action = "/gsan/removerSetorComercialAction.do"
			document.forms[0].submit();
		 }
	}
}
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) 
{
	var form = document.forms[0];
	if (tipoConsulta == 'municipio') 
	{
	  form.municipioID.value = codigoRegistro;
	  form.municipioNome.value = descricaoRegistro;
	  form.municipioNome.style.color = "#000000";
	}
}
</SCRIPT>


</head>

<body leftmargin="5" topmargin="5">


<html:form action="/removerSetorComercialAction" method="post">

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
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Manter Setor Comercial</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>


				<table width="590" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td colspan="4" height="23"><font color="#000000"
						style="font-size: 10px"
						face="Verdana, Arial, Helvetica, sans-serif"><strong>Setores
						comerciais encontrados:</strong></font></td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroLocalizacaoSetorComercialManter', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>	
				</tr>
				</table>
			
	  			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td bgcolor="#000000" height="2"></td>
				</tr>
				<tr>
					<td>
					<table width="100%" bgcolor="#99CCFF">
						<tr bgcolor="#99CCFF">
							<td align="center" width="50"><A
								HREF="javascript:facilitador(this);" id="0"><strong>Todos</strong></A></td>
							<td align="center" width="153"><FONT COLOR="#000000"><strong>Localidade</strong></FONT></td>
							<td align="center" width="80"><FONT COLOR="#000000"><strong>Código</strong></FONT></td>
							<td align="center" width="153"><FONT COLOR="#000000"><strong>Nome do Setor</strong></FONT></td>
							<td align="center" width="153"><FONT COLOR="#000000"><strong>Munic&iacute;pio</strong></FONT></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td><!-- <div style="width: 100%; height: 100; overflow: auto;"> -->
					<table width="100%" bgcolor="#99CCFF">


						<%String cor = "#cbe5fe";%>

						<%--Esquema de paginação--%>
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">

							<pg:param name="pg" />
							<pg:param name="q" />
							<logic:present name="colecaoSetorComercial">
								<logic:iterate name="colecaoSetorComercial" id="setor"
									type="SetorComercial">
									<pg:item>

										<%if (cor.equalsIgnoreCase("#cbe5fe")) {
											cor = "#FFFFFF";%>
										<tr align="left" bgcolor="#FFFFFF" height="18">
											<%} else {
											cor = "#cbe5fe";%>
										<tr align="left" bgcolor="#cbe5fe" height="18">
											<%}%>

											<logic:equal name="setor" property="indicadorUso"
												value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">

												<td align="center" width="50">
													<html:checkbox property="setorComercialID" value="<%="" + setor.getId()%>" />
												</td>
												
												<td width="153">
													<bean:write name="setor" property="localidade.descricao" />
												</td>
												
												<td align="center" width="80">
													<html:link href="/gsan/exibirAtualizarSetorComercialAction.do" paramId="setorComercialID" 
													paramProperty="id" paramName="setor" title="<%="" + setor.getCodigo()%>">
														<bean:write name="setor" property="codigo" />
													</html:link>
												</td>
												
												<td width="153">
													<bean:write name="setor" property="descricao" />
												</td>
												
												<td width="153">
													<bean:write name="setor" property="municipio.nome" />
												</td>

											</logic:equal>

											<logic:notEqual name="setor" property="indicadorUso"
												value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">

												<td align="center" width="50"><html:checkbox
													property="setorComercialID" value="<%="" + setor.getId()%>" /></td>
											
												<td width="153"><span style="color: #CC0000;"><bean:write
													name="setor" property="localidade.descricao" /></span></td>
												
												<td align="center" width="80">
													<html:link href="/gsan/exibirAtualizarSetorComercialAction.do" paramId="setorComercialID" 
													paramProperty="id" paramName="setor" title="<%="" + setor.getCodigo()%>" style="color: #CC0000;">
														<span style="color: #CC0000;"><bean:write name="setor" property="codigo" /></span>
													</html:link>
												</td>
											
												<td width="153">
													<span style="color: #CC0000;"><bean:write name="setor" property="descricao" /></span>
												</td>
												<td width="153"><span style="color: #CC0000;"><bean:write
													name="setor" property="municipio.nome" /></span></td>

											</logic:notEqual>
										</tr>


									</pg:item>
								</logic:iterate>
							</logic:present>
					</table>
					</td>
				</tr>
				<tr bordercolor="#90c7fc">
					<td>
					<table width="100%">
						<tr>
							<td>
							<gsan:controleAcessoBotao name="Button" value="Remover"
							  onclick="javascript:verficarSelecao(setorComercialID);" url="removerSetorComercialAction.do"/>
					<!-- 
							<input name="Button" type="button" class="bottonRightCol"
								value="Remover" onclick="verficarSelecao(setorComercialID)"
								align="left" style="width: 70px;">
								-->
								<input name="Button"
								type="button" class="bottonRightCol" value="Voltar Filtro"
								onclick="window.location.href='/gsan/exibirFiltrarSetorComercialAction.do?objetoConsulta=1'"
								align="left" style="width: 80px;"></td>
							<td align="right" valign="top">
							<div align="right"><a href="javascript:toggleBox('demodiv',1);">
							<img border="0"
								src="<bean:message key="caminho.imagens"/>print.gif"
								title="Imprimir Setores Comerciais" /></a></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
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
			</td>
		</tr>
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioSetorComercialManterAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
