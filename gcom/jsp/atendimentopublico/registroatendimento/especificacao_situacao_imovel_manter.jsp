<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@page import="gcom.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

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

	function remover(objeto){
	
		if (CheckboxNaoVazio(objeto)){
			if (confirm ("Confirma remoção?")) {
				document.forms[0].action = "/gsan/removerEspecificacaoSituacaoImovelAction.do"
				document.forms[0].submit();
			}
		}
	}

</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/removerEspecificacaoSituacaoImovelAction" 
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post"
	onsubmit="return CheckboxNaoVazio(document.ManutencaoRegistroActionForm.idRegistrosRemocao) && confirm('Confirma remoção?')">

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
		<td width="625" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11">
						<img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
					</td>
					
					<td class="parabg">
						Manter Especifica&ccedil;&atilde;o da Situa&ccedil;&atilde;o do Im&oacute;vel
					</td>
					
					<td width="11">
						<img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>

				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>
	
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4" height="23">
						<font color="#000000" style="font-size:10px" face="Verdana, Arial, Helvetica, sans-serif">
						<strong>Especifica&ccedil;&atilde;o (&otilde;es) Cadastrada(s):</strong>
						</font>
					</td>
					<td align="right"><a href="javascript: abrirPopup('/gsan/help/help.jsp?mapIDHelpSet=clienteManter', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
				</tr>
	  		</table>
			
	  		<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4" bgcolor="#000000" height="2"></td>
				</tr>
				<tr>
					<td>
						<table width="100%" bgcolor="#99CCFF">
							<!--header da tabela interna -->
							<tr bgcolor="#99CCFF">
								<td width="50" align="center">
									<strong>
									<a onclick="this.focus();" id="0" href="javascript:facilitador(this);">Todos</a>
									</strong>
								</td>
								<td width="100" align="center">
									<strong>Especifica&ccedil;&atilde;o</strong>
								</td>
								<td align="center">
									<strong>Descrição</strong>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" bgcolor="#99CCFF">

						<%--Esquema de paginação--%>
						<pg:pager isOffset="true" 
							index="half-full" 
							maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"				
							maxPageItems="10" items="${sessionScope.totalRegistros}">

							<pg:param name="pg"/>
							<pg:param name="q"/>

							<logic:present name="colecaoEspecificacaoSituacaoImovel">
								<%int cont = 0;%>
								<logic:iterate name="colecaoEspecificacaoSituacaoImovel" id="especificacaoSituacaoImovel" type="EspecificacaoImovelSituacao">
									<pg:item>
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
										<%} else {%>
										<tr bgcolor="#FFFFFF">
										<%}%>
											<td width="50" align="center">
												<input type="checkbox" 
													name="idRegistrosRemocao" 
													value="<bean:write name="especificacaoSituacaoImovel" property="id"/>"/>
											</td>
											
											<td width="100" align="center">
												<bean:write name="especificacaoSituacaoImovel" property="id"/>
											</td>
											<td align="center">
												<a href="/gsan/exibirAtualizarEspecificacaoSituacaoImovelAction.do?idEspecificacao=<%=""+especificacaoSituacaoImovel.getId()%>">
													<bean:write name="especificacaoSituacaoImovel" property="descricao"/>
												</a>
											</td>

										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
						<%-- Fim do esquema de paginação --%>
						</table>

						<table width="100%">
							<tr>
								<td>

									<gsan:controleAcessoBotao name="Botao" 
										value="Remover" 
										onclick="remover(document.ManutencaoRegistroActionForm.idRegistrosRemocao);" 
										tabindex="1"
										url="removerEspecificacaoSituacaoImovelAction.do"/>
										

									<input name="button" 
										type="button" 
										class="bottonRightCol" 
										tabindex="2" 
										value="Voltar Filtro"
										onclick="window.location.href='<html:rewrite page="/exibirFiltrarEspecificacaoSituacaoImovelAction.do"/>'">
								</td>
								
								<td align="right" valign="top">
                                	<a href="javascript:toggleBox('demodiv',1);">
                                    	<img align="right" 
                                    		border="0" 
                                    		src="<bean:message key='caminho.imagens'/>print.gif"  
                                    		title="Imprimir Especificação"/></a>
                                </td>
								
							</tr>
						</table>

						<table width="100%" border="0">
							<tr>
								<td align="center">
									<strong><%@ include file="/jsp/util/indice_pager_novo.jsp"%></strong>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>		
		</pg:pager>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>

 <%@ include file="/jsp/util/rodape.jsp"%> 

</body>
</html:form>
</html:html>