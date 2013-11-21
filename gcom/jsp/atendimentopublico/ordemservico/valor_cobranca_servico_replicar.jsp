<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="gcom.util.Pagina,gcom.util.ConstantesSistema,java.util.Collection"%>

<%@page	import="gcom.atendimentopublico.ordemservico.ServicoCobrancaValor"%>
<%@ page import="gcom.util.Util" %> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%-- Carrega validações do validator --%>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
	<html:javascript staticJavascript="false"  formName="ReplicarValorCobrancaServicoActionForm" />
<%-- Carrega javascripts da biblioteca --%>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<%-- Novos Javascripts --%>
<script>
	function validaForm() {
		var form = document.forms[0];
		/* Validate */
		if (validateReplicarValorCobrancaServicoActionForm(form)) {
			if(form.indiceParaCorrecao.value != ''){
				if(validaData(form.novaDataVigenciaInicial) && validaData(form.novaDataVigenciaFinal)){
					if (comparaData(form.novaDataVigenciaInicial.value, "<", form.novaDataVigenciaFinal.value )){
		  				submeterFormPadrao(form);
		  			}else{
		  				alert('Informe uma data de vigência final superior a inicial.');			
		  			}
		  		}
			}else{
				alert('Informe o valor do índide de reajuste.');
			}	
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
			
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/replicarValorCobrancaServicoAction.do"
	name="ReplicarValorCobrancaServicoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.ReplicarValorCobrancaServicoActionForm"
	method="post" focus="novaDataVigenciaInicial">

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
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Replicar Valor de Cobrança de Serviço</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<!--Inicio da Tabela Ligação de Esgoto -->

			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="7" height="23"><font style="font-size: 10px;"
						color="#000000" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valores
					de Cobrança de Serviço:</strong> </font></td>
				</tr>
				<tr>
					<!--<td colspan="4" bgcolor="#3399FF"> -->
					<td colspan="7" bgcolor="#000000" height="2" valign="baseline"></td>
				</tr>
				<tr>
					<td>
					<table width="100%" align="center" bgcolor="#90c7fc" border="0"
						cellpadding="0" cellspacing="0">
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" bgcolor="#90c7fc">
								<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">

									<td width="7%">
									<div align="center"><strong><a
										href="javascript:facilitador(this);" id="0">Todos</a></strong></div>
									</td>

									<td width="15%">
										<div align="center"><strong>Tipo do Serviço</strong></div>
									</td>
									<td width="15%">
										<div align="center"><strong>Categoria</strong></div>
									</td>
									<td width="15%">
										<div align="center"><strong>SubCategoria</strong></div>
									</td>
									<td width="15%">
										<div align="center"><strong>Vigência inicial</strong></div>
									</td>
									<td width="15%">
										<div align="center"><strong>Vigência final</strong></div>
									</td>
									<td width="18%">
										<div align="center"><strong>Valor do Serviço</strong></div>
									</td>

									
								</tr>
								<%--Esquema de paginação--%>
								<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
									export="currentPageNumber=pageNumber;pageOffset"				
									maxPageItems="10" items="${sessionScope.totalRegistros}">
									<pg:param name="pg"/>
									<pg:param name="q"/>
									<%int cont = 0;%>
									<logic:present name="ReplicarValorCobrancaServicoActionForm" property="collServicoCobrancaValor">
										<logic:iterate name="ReplicarValorCobrancaServicoActionForm" property="collServicoCobrancaValor"
											id="servicoCobrancaValor" type="ServicoCobrancaValor" >
											<pg:item>
												<%cont = cont + 1;
												if (cont % 2 == 0) {%>
												<tr bgcolor="#FFFFFF">
													<%} else {%>
												<tr bgcolor="#cbe5fe">
													<%}%>
	
													<td width="7%">
													<div align="center"><input type="checkbox"
														name="idRegistrosSelecionados"
														value="<bean:write name="servicoCobrancaValor" property="id"/>" checked="checked"></div>
													</td>
													<td width="15%">
														<bean:write name='servicoCobrancaValor' property='servicoTipo.descricao'/>
													</td>
													<td width="15%">
													<logic:notEmpty name="servicoCobrancaValor" property="categoria">
														<div align="center">
															<bean:write name="servicoCobrancaValor" property="categoria.descricao"/>
														</div>
													</logic:notEmpty>
													<logic:empty name="servicoCobrancaValor" property="categoria">
															&nbsp;
													</logic:empty> 
													 
													</td>
													
													<td width="15%">
													<logic:notEmpty name="servicoCobrancaValor" property="subCategoria">
														<div align="center">
															<bean:write name="servicoCobrancaValor" property="subCategoria.descricao"/>
														</div>
														</logic:notEmpty>
														<logic:empty name="servicoCobrancaValor" property="subCategoria">
															&nbsp;
														</logic:empty> 
													 
													</td> 
													
													<td width="12%" align="center">
														<%=gcom.util.Util.formatarData(servicoCobrancaValor.getDataVigenciaInicial())%>
													</td>
														
													<td width="12%" align="center">
														<%=gcom.util.Util.formatarData(servicoCobrancaValor.getDataVigenciaFinal())%>
													</td>											
	
													<td width="18%" align="right">
														<%=gcom.util.Util.formatarMoedaReal(servicoCobrancaValor.getValor())%>
													</td>
	
												</tr>
											</pg:item>
										</logic:iterate>
									</logic:present>
							</table>
							
							<table width="100%" border="0">
								<tr>
									<td height="31">
										<table width="100%" border="0" align="center">
											<tr>
												<td height="10" colspan="2">
													<font style="font-size: 10px;" color="#000000" 
															face="Verdana, Arial, Helvetica, sans-serif"> 
														<strong>Argumentos para réplica dos valores dos serviços </strong> 
													</font>
												</td>
											</tr>
											<tr>
												<td height="10" colspan="2">
												<hr>
												</td>
											</tr>
											<tr>
												<tr> 
										        	<td><strong>Data inicial para a nova vigência:</strong></td>
										        	<td valign="middle">
											            <html:text maxlength="10" 
											            		   property="novaDataVigenciaInicial" 
											            		   size="10" 
											            		   onkeypress="return isCampoNumerico(event);"
											            		   onkeyup="javascript:mascaraData(this,event);"/>
											            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" 
											            	onclick="javascript:abrirCalendario('ReplicarValorCobrancaServicoActionForm', 'novaDataVigenciaInicial');" 
											            	width="20" border="0" align="middle" alt="Exibir Calendário" /><strong> a</strong> 
											            <html:text maxlength="10" 
											            		   property="novaDataVigenciaFinal" 
											            		   size="10" 
											            		   onkeypress="javascript:mascaraData(this,event);return isCampoNumerico(event);"/>
											            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" 
											            onclick="javascript:abrirCalendario('ReplicarValorCobrancaServicoActionForm', 'novaDataVigenciaFinal')"
											            width="20" border="0" align="middle" alt="Exibir Calendário" /> (dd/mm/aaaa) 
										        	</td>										
											</tr>
											<tr> 
									        	<td><strong>Índice para aplicar correção:</strong></td>
									        	<td>
										            <html:text maxlength="9" 
										            		   property="indiceParaCorrecao" 
										            		   size="10" 
										            		   onkeyup="return(formatarValorIndice5CasasDecimais(this, 8))"/>
									        	</td>
									        </tr>
										</table>
									</td>
								</tr>
							</table>	
							<table width="100%" border="0">
								<tr>
									<td colspan="4">
									<div align="center"><strong><%@ include
										file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
									</td>
								</tr>
							</table>	
								</pg:pager>
							
							<table width="100%" border="0">
								<tr>
									<td colspan="2" align="left">
										<input type="button" 
											   name="Submit2"
											   class="bottonRightCol" 
											   value="Voltar" 
											   onclick="window.location.href='<html:rewrite page="/exibirInserirValorCobrancaServicoAction.do?limpar=S"/>'">
									</td>
									<td colspan="2" align="right">
										<input type="button" 
											   name="Submit2"
											   class="bottonRightCol" 
											   value="Atualizar Vigência" 
											   onclick="validaForm();">
									</td>
								</tr>
							</table>
							</td>
						</tr>

					</table>
					<p>&nbsp;</p>
					</td>
				</tr>
			</table>
			
			
			</td>
		</tr>
	</table>
	<!-- Fim do Corpo - Leonardo Regis -->

	<!-- Rodapé -->
	<%@ include file="/jsp/util/rodape.jsp"%>
	</body>
</html:form>
</html:html>
