<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>


	<script language="JavaScript">

function todos() {
	marcarTodos();
}

function remover(){
  var form = document.forms[0];
   if(CheckboxNaoVazio(form.ids) && confirm('Confirma remoção?')){
     form.action = "removerUsuarioAction.do";
     submeterFormPadrao(form);
 }
}

function controlarAcessos(){
   var achou = false;
   var erro = false;
   var form = document.forms[0];
   var ids = form.ids;
   var idSelecionado = '';
   for (var i=0;i<ids.length;i++){
    if(ids[i].checked){
     if(achou){
      alert('Só é possivel selecionar um usuário para efetuar o controle de acessos.');
      erro = true;
      break;
     }else{
      achou = true;
      idSelecionado = ids[i].value;
     }
    }
   }
   if(!erro){
	   if(!achou){
	    if(ids.checked){
	     form.action = "exibirControleAcessoUsuarioAction.do?idRegistroControleAcesso="+form.ids.value;
	     submeterFormPadrao(form);
	    }else{
	      alert('Selecione um para efetuar o controle de acessos.');
	    }
	   }else{
	    form.action = "exibirControleAcessoUsuarioAction.do?idRegistroControleAcesso="+idSelecionado;
	    submeterFormPadrao(form);
	   }  
   }
}

 
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/removerUsuarioAction"
	name="ExcluirUsuarioActionForm"
	type="gcom.gui.seguranca.acesso.usuario.ExcluirUsuarioActionForm"
	method="post"
	onsubmit="return CheckboxNaoVazio(document.ExcluirUsuarioAction.ids) && confirm('Confirma remoção?')">


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

					<td class="parabg">Manter Usuario</td>
					<td valign="top" width="11"><img src="imagens/parahead_right.gif"
						border="0"></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a P&aacute;gina&ccedil;&atilde;o da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="2" height="23"><font color="#000000"
						style="font-size:10px"
						face="Verdana, Arial, Helvetica, sans-serif"><strong>Usuários
					Encontrados:</strong></font></td>
				</tr>
				<tr>
					<td colspan="2" bgcolor="#000000" height="2"></td>
				</tr>
				<tr>
					<td colspan="2">
					<table border="0" bgcolor="#99CCFF" width="100%">
						<tr bordercolor="#000000">
							<td width="6%" bgcolor="#90c7fc">
							<div align="center"><strong>Todos</strong></div>
							</td>
							<td width="20%" bgcolor="#90c7fc">
							<div align="center"><strong>Nome do Usu&aacute;rio</strong></div>
							</td>

							<td width="10%" bgcolor="#90c7fc">
							<div align="center"><strong>Tipo de Usu&aacute;rio</strong></div>
							</td>
							<td width="8%" bgcolor="#90c7fc">
							<div align="center"><strong>Unidade Organizacional</strong></div>
							</td>
							<td width="9%" bgcolor="#90c7fc">
							<div align="center"><strong>Situa&ccedil;&atilde;o do
							Usu&aacute;rio</strong></div>
							</td>
							<td width="12%" bgcolor="#90c7fc">
							<div align="center"><strong>Abrang&ecirc;ncia do Acesso</strong></div>
							</td>
							<td width="16%" bgcolor="#90c7fc">
							<div align="center"><strong>Data de Cadastro do Acesso</strong></div>
							</td>
							<td width="19%" bgcolor="#90c7fc">
							<div align="center"><strong>Data Final de Cadastramento</strong></div>
							</td>

						</tr>

						<%--Esquema de paginação--%>
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="q" />

							<%int cont = 0;%>
							<logic:present name="collectionUsuariosGrupos">
								<logic:iterate name="collectionUsuariosGrupos" id="usuarioGrupo">
									<pg:item>
										<%cont = cont + 1;
									if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {

									%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td width="6%">
											<div align="center"><strong> <input type="checkbox"
												name="ids"
												value="<bean:write name="usuarioGrupo" property="id"/>" /> </strong></div>
											</td>
											<td width="20%">
											<div align="center"><html:link
												page="/exibirAtualizarUsuarioAction.do" paramName="usuarioGrupo"
												paramProperty="id" paramId="idRegistroAtualizacao">
												<bean:write name="usuarioGrupo" property="nomeUsuario" />
											</html:link></div>
											</td>
											<td width="10%">
											<div align="center"><bean:write name="usuarioGrupo"
												property="usuarioTipo.descricao" /></div>
											</td>
											<td width="8%">
											<div align="center">
												<logic:notEmpty name="usuarioGrupo" property="funcionario">
													<logic:notEmpty name="usuarioGrupo"
														property="funcionario.unidadeOrganizacional">
														<bean:write name="usuarioGrupo"
															property="funcionario.unidadeOrganizacional.sigla" />
													</logic:notEmpty>
												</logic:notEmpty>
												<logic:empty name="usuarioGrupo" property="funcionario">
													<logic:notEmpty name="usuarioGrupo"
														property="unidadeOrganizacional">
														<bean:write name="usuarioGrupo"
															property="unidadeOrganizacional.sigla" />
													</logic:notEmpty>
												</logic:empty>
											</div>
											</td>
											<td width="9%">
											<div align="center"><bean:write name="usuarioGrupo"
												property="usuarioSituacao.descricaoUsuarioSituacao" /></div>
											</td>
											<td width="12%">
											<div align="center"><bean:write name="usuarioGrupo"
												property="usuarioAbrangencia.descricaoAbreviada" /></div>
											</td>
											<td width="16%">
											<div align="center"><bean:write name="usuarioGrupo"
												property="dataCadastroAcesso" formatKey="date.format" /></div>
											</td>
											<td width="19%">
											<div align="center"><bean:write name="usuarioGrupo"
												property="dataCadastroFim" formatKey="date.format" /></div>
											</td>
										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
					</table>
					<table width="100%" border="0">
						<tr>
							<td valign="top">
							<gsan:controleAcessoBotao name="Botao" value="Remover" onclick="remover();" url="removerUsuarioAction.do"/>
							&nbsp;
							<%--<input name="button" type="button"
								class="bottonRightCol" value="Remover" onclick="remover();" />
							 <input name="button" type="button" class="bottonRightCol"
								value="Controlar Acessos" onclick="controlarAcessos();" />--%>
								<gsan:controleAcessoBotao name="Botao" value="Controlar Acessos" onclick="controlarAcessos();" url="concluirControlarAcessosUsuarioAction.do"/> &nbsp;
							<input name="button" type="button" class="bottonRightCol"
								value="Voltar Filtro"
								onclick="window.location.href='/gsan/exibirFiltrarUsuarioAction.do'"
								align="left" style="width: 80px;"></td>
							<td valign="top">
							<div align="right"><a
								href="javascript:toggleBox('demodiv',1);/*javascript:abrirPopupRelatorio('gerarRelatorioUsuarioManterAction.do');*/">
							<img border="0"
								src="<bean:message key="caminho.imagens"/>print.gif"
								title="Imprimir Usuarios" /> </a></div>
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
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioUsuarioManterAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
