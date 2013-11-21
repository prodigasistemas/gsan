<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
    <html:javascript staticJavascript="false"  formName="ConjuntoTramitacaoRaActionForm"/>	
<script>	
<!--
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
function informarTramite(form){
	if (CheckboxNaoVazioMensagemGenerico("Deverá marcar pelo menos um RA para tramitação. Trâmite não pode ser realizado",form.idRegistrosTramitacao)){
		abrirPopupComSubmit('exibirRegistroAtendimentoTramitacaoPopupAction.do?primeiraVez=sim', 410, 650);
	}
}

function limparTramite(form){
	if (CheckboxNaoVazioMensagemGenerico("Deverá marcar pelo menos um RA para limpar a tramitação.", form.idRegistrosTramitacao)){
		form.action = 'limparRegistroAtendimentoTramitacaoAction.do';
		form.submit();
	}	
}

function exibirTelaPavimento(){
	var EXIBIR = document.getElementById("INDICADOR_PAVIMENTO").value;

	if (EXIBIR == "sim"){
		abrirPopup('exibirInserirDadosPavimentoOrdemServicoPopupAction.do', 300, 700);
	}	
}
-->
</script>

</head>
<body leftmargin="5" topmargin="5" onload="javascript:exibirTelaPavimento();">

<html:form action="/registroAtendimentoTramitacaoAction" method="post"
	name="ConjuntoTramitacaoRaActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.ConjuntoTramitacaoRaActionForm">


<input type="hidden" id="INDICADOR_PAVIMENTO" value="${requestScope.indicadorPavimento}" />
		
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
			
			<td valign="top" bgcolor="#003399" class="centercoltext">
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Tramitar Conjunto de Registro(s) de Atendimento</td>
					<td width="11" valign="top"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<!-- Início do Corpo -->
			<p>&nbsp;</p>
			<table width="100%" cellpadding="0" cellspacing="0" border="0">

				<tr>
					<td colspan="6"><font color="#000000" style="font-size:10px"
						face="Verdana, Arial, Helvetica, sans-serif"> <strong>Registros de
					Atendimento encontrados:</strong> </font></td>
				</tr>
			</table>
			
  			<table width="100%" cellpadding="0" cellspacing="0">

				<tr>
					<td colspan="6">&nbsp;</td>
				</tr>
				
				<tr bordercolor="#000000">
					<td width="7%" bgcolor="#90c7fc">
						<div align="center"><strong><a href="javascript:facilitador(this);" id="0">Todos</a></strong></div>
					</td>
					
					<td width="13%" bgcolor="#90c7fc">
						<div align="center"><strong>N&uacute;mero do RA</strong></div>
					</td>
					
					<td width="20%" bgcolor="#90c7fc">
						<div align="center"><strong>Especifica&ccedil;&atilde;o</strong></div>
					</td>
					
					<td width="15%" bgcolor="#90c7fc">
						<div align="center"><strong>Localidade</strong></div>
					</td>
					
					<td width="15%" bgcolor="#90c7fc">
						<div align="center"><strong>Unidade Atual</strong></div>
					</td>
					
					<td width="15%" bgcolor="#90c7fc">
						<div align="center"><strong>Unidade Destino</strong></div>
					</td>
					
					<td width="15%" bgcolor="#90c7fc">
						<div align="center"><strong>Perfil do Imóvel</strong></div>
					</td>
				<tr>
				<tr>
				  	<td colspan="7">
					<div style="width: 100%; height: 206; overflow: auto;">
					<table width="100%" bgcolor="#99CCFF">
					<%	int cont = 0;	%>
						<logic:iterate name="colecaoRAHelper" id="helper">
					<%	cont = cont + 1;
						if (cont % 2 == 0) {%>
							<tr bgcolor="#cbe5fe">
					<%	} else {	%>
							<tr bgcolor="#FFFFFF">
					<%	}	%>												
								<td width="7%">
									<logic:present parameter="importarMovimentoACQUAGIS">
                              		<div align="center">
                                		<input type="checkbox" disabled="disabled" name="idRegistrosTramitacao" value="<bean:write name="helper" property="registroAtendimento.id"/>;<bean:write name="helper" property="unidadeAtual.id" />"/>
                              		</div>
                              		</logic:present>
                              		<logic:notPresent parameter="importarMovimentoACQUAGIS">
                              		<div align="center">
                                		<input type="checkbox" name="idRegistrosTramitacao" value="<bean:write name="helper" property="registroAtendimento.id"/>;<bean:write name="helper" property="unidadeAtual.id" />"/>
                              		</div>
                              		</logic:notPresent>
                            	</td>								
								
								<logic:present parameter="importarMovimentoACQUAGIS">
								<td width="13%" bordercolor="#90c7fc">
								<div align="center"><a href="/gsan/filtrarOrdemServicoAction.do?idRa=<bean:write name="helper" property="registroAtendimento.id" />"><bean:write name="helper" property="registroAtendimento.id" /></a>
								</div>
								</td>
								</logic:present>
								<logic:notPresent parameter="importarMovimentoACQUAGIS">
								<td width="13%" bordercolor="#90c7fc">
								<div align="center"><bean:write name="helper" property="registroAtendimento.id" />
								</div>
								</td>
								</logic:notPresent>
								
								<td width="20%" bordercolor="#90c7fc">
								<div><bean:write name="helper"
									property="registroAtendimento.solicitacaoTipoEspecificacao.descricao" />
								</div>
								</td>
								
								<td width="15%" bordercolor="#90c7fc">
								<div><bean:write name="helper"
									property="registroAtendimento.localidade.descricao" />
								</div>
								</td>
								
								<td width="15%" bordercolor="#90c7fc">
								<div><logic:notEmpty name="helper" property="unidadeAtual">
									<bean:write name="helper" property="unidadeAtual.descricao" />
								</logic:notEmpty></div>
								</td>
								
								<td width="15%" bordercolor="#90c7fc">
									<logic:notEmpty name="helper" property="unidadeDestino">
										<bean:write name="helper" property="unidadeDestino.descricao" />
									</logic:notEmpty>
								</td>
								
								<td width="15%" bordercolor="#90c7fc">																		
									<logic:notEmpty name="helper" property="perfilImovel">
										<bean:write name="helper" property="perfilImovel.descricao" />
									</logic:notEmpty>
									
								</td>											
							</tr>
						</logic:iterate>
					</table>
					</div>
					<table width="100%" border="0">
						<tr>
						  <td>&nbsp;</td>
						</tr>
						<tr>
							<td>
							  <table>
							    <tr>
								  <td valign="top"><input name="button" type="button"
								    class="bottonRightCol" value="Voltar Filtro"
								    onclick="window.location.href='<html:rewrite page="/exibirFiltrarRegistroAtendimentoTramitacaoAction.do?removerColecao=ok"/>'"
								    align="left" style="width: 80px;">
							      </td>
								  <td valign="top">
								  	<logic:notPresent parameter="importarMovimentoACQUAGIS">
								  	<input name="button" type="button"
								  	class="bottonRightCol" value="Informar Tramite"
									onclick="javascript:informarTramite(document.forms[0]);">
									<input name="button" type="button"
								  	class="bottonRightCol" value="Limpar Tramite"
									onclick="javascript:limparTramite(document.forms[0]);">
									</logic:notPresent>
								  </td>								      
							    </tr>
							  </table>
							</td>
							<td valign="top" align="right"><input name="button" type="button"
								class="bottonRightCol" value="Tramitar"
								onclick="window.location.href='<html:rewrite page="/registroAtendimentoTramitacaoAction.do?primeiraVez=ok"/>'"
								align="right" style="width: 80px;">
							</td>
						</tr>
					</table>
				
					</td>
				</tr>

			</table>
	</table>
<p>&nbsp;</p>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
