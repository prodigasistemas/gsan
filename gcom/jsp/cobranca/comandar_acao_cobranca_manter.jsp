<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page
	import="gcom.util.Util,gcom.cobranca.CobrancaGrupoCronogramaMes,gcom.cobranca.CobrancaAcaoAtividadeComando"%>
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ManterComandoAcaoCobrancaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
<!-- Begin

	function excluirComandoAtividadeCronogramaAcaoCobranca(objeto, tipoObjeto){
       var indice;
       var array = new Array(objeto.length);
       var selecionado = "";
	   var formulario = document.forms[0]; 
	   for(indice = 0; indice < formulario.elements.length; indice++){
		 	if (formulario.elements[indice].type == tipoObjeto && formulario.elements[indice].checked == true
		 		&& formulario.elements[indice].name == "idCobrancaAcaoAtividadeCronograma") {
				selecionado = formulario.elements[indice].value;
			 }
	   }

       if (selecionado.length < 1) {
	         alert('Nenhum registro selecionado.');
       }else { 
		 	if (confirm ("Confirma remoção?")) {
				redirecionarSubmit("/gsan/excluirComandoAtividadeCronogramaAcaoCobrancaAction.do");
			}else{
				window.close();
	   	    }
  	   }
	}

	function excluirComandoAtividadeEventualCobranca(objeto, tipoObjeto){
       var indice;
       var array = new Array(objeto.length);
       var selecionado = "";
	   var formulario = document.forms[0]; 
	   for(indice = 0; indice < formulario.elements.length; indice++){
		 if (formulario.elements[indice].type == tipoObjeto && formulario.elements[indice].checked == true
		 && formulario.elements[indice].name == "idCobrancaAcaoAtividadeComando") {
			selecionado = formulario.elements[indice].value;
		 }
	   }

       if (selecionado.length < 1) {
	         alert('Nenhum registro selecionado.');
       }else { 
		 	if (confirm ("Confirma remoção?")) {
				redirecionarSubmit("/gsan/excluirComandoAtividadeEventualAcaoCobrancaAction.do");
			}else{
				window.close();
	   	    }
	   }
	
	}

	function atualizarComandoAtividadeEventualCobranca(){
	
	}

	function visualizarComandoAcaoCobranca(idCobrancaAcaoAtividadeComando){
		redirecionarSubmit("/gsan/exibirManterComandoAcaoCobrancaDetalhesAction.do?idCobrancaAcaoAtividadeComando="+idCobrancaAcaoAtividadeComando);					
	}

function facilitador(objeto){
	if (objeto.id == "0" || objeto.id == undefined){
		objeto.id = "1";
		marcarTodosPrimeiro();
	}
	else{
		objeto.id = "0";
		desmarcarTodosPrimeiro();
	}
}

function facilitadorSegundo(objeto){
	if (objeto.id == "2" || objeto.id == undefined){
		objeto.id = "3";
		marcarTodosSegundo();
	}
	else{
		objeto.id = "2";
		desmarcarTodosSegundo();
	}
}

//Ativa todos os elementos do tipo checkbox do formul?rio existente
function marcarTodosPrimeiro(){

	for (var i=0;i < document.forms[0].elements.length;i++){
		var elemento = document.forms[0].elements[i];
		if (elemento.type == "checkbox" && elemento.id == "1" ){
			elemento.checked = true;
		}
	}
}


//Ativa todos os elementos do tipo checkbox do formul?rio existente
function marcarTodosSegundo(){

	for (var i=0;i < document.forms[0].elements.length;i++){
		var elemento = document.forms[0].elements[i];
		if (elemento.type == "checkbox" && elemento.id == "2" ){
			elemento.checked = true;
		}
	}
}

//Desativa todos os elementos do tipo checkbox do formul?rio existente
function desmarcarTodosPrimeiro() {

	for (var i=0;i < document.forms[0].elements.length;i++){
		var elemento = document.forms[0].elements[i];
		if (elemento.type == "checkbox" && elemento.id == "1" ){
			elemento.checked = false;
		}
	}
}

//Desativa todos os elementos do tipo checkbox do formul?rio existente
function desmarcarTodosSegundo() {

	for (var i=0;i < document.forms[0].elements.length;i++){
		var elemento = document.forms[0].elements[i];
		if (elemento.type == "checkbox" && elemento.id == "2" ){
			elemento.checked = false;
		}
	}
}




-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form action="/exibirManterComandoAcaoCobrancaAction.do"
	name="ManterComandoAcaoCobrancaActionForm"
	type="gcom.gui.cobranca.ManterComandoAcaoCobrancaActionForm"
	method="post"
	onsubmit="return validateManterComandoAcaoCobrancaActionForm(this);">

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

			<td width="595" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Manter Comando de A&ccedil;&atilde;o de
					Cobran&ccedil;a</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a P&aacute;gina&ccedil;&atilde;o da Tela de Processo-->
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">
					<table width="100%" bgcolor="#99CCFF">
						<tr bordercolor="#90c7fc">
							<td colspan="8" bgcolor="#90c7fc"><strong>Atividades de
							A&ccedil;&atilde;o de Cobran&ccedil;a do Cronograma Comandadas</strong></td>
						</tr>
						<tr bordercolor="#90c7fc">
							<td width="4%" rowspan="2" bgcolor="#90c7fc">
							<div align="center"><strong><A HREF="javascript:facilitador(this);" id="0"><strong>Todos</strong></A></strong></div>
							</td>
							<td width="7%" rowspan="2" bgcolor="#90c7fc">
							<div align="center"><strong>Grupo</strong></div>
							</td>
							<td width="10%" rowspan="2" bgcolor="#90c7fc">
							<div align="center"><strong>Refer&ecirc;ncia</strong></div>
							</td>
							<td width="20%" rowspan="2" bgcolor="#90c7fc">
							<div align="center"><strong>A&ccedil;&atilde;o</strong></div>
							</td>
							<td width="20%" rowspan="2" bgcolor="#90c7fc">
							<div align="center"><strong>Atividade</strong></div>
							</td>
							<td width="13%" colspan="2" bgcolor="#90c7fc">
							<div align="center"><strong> Comando</strong></div>
							</td>
							<td width="17%" rowspan="2" bgcolor="#90c7fc">
							<div align="center"><strong>Data Prevista do Cronograma</strong></div>
							</td>
						</tr>
						<tr bordercolor="#90c7fc">
							<td bgcolor="#cbe5fe">
							<div align="center"><strong>Data </strong></div>
							</td>
							<td bgcolor="#cbe5fe">
							<div align="center"><strong>Hora</strong></div>
							</td>
						</tr>
					</table>

					<div style="width: 100%; height: 100; overflow: auto;">

					<table width="100%" bgcolor="#99CCFF">
						<%String cor = "#cbe5fe";%>
						<logic:present
							name="colecaoAtividadeCronogramaAcaoCobrancaComandadas"
							scope="session">
							<logic:iterate
								name="colecaoAtividadeCronogramaAcaoCobrancaComandadas"
								id="cobrancaAcaoAtividadeCronograma">
								<%if (cor.equalsIgnoreCase("#cbe5fe")) {
				cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
				cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>

									<td width="7%">
									<div align="center"><strong> <input type="checkbox"
										name="idCobrancaAcaoAtividadeCronograma" id="1"
										value="<bean:write	name="cobrancaAcaoAtividadeCronograma" property="id" />" />
									</strong></div>
									</td>
									<td width="7%">
									<div align="center"><bean:define
										name="cobrancaAcaoAtividadeCronograma"
										property="cobrancaAcaoCronograma" id="cobrancaAcaoCronograma" />
									<bean:define name="cobrancaAcaoCronograma"
										property="cobrancaGrupoCronogramaMes"
										id="cobrancaGrupoCronogramaMes" /> 
									<bean:define name="cobrancaGrupoCronogramaMes"
										property="cobrancaGrupo"
										id="cobrancaGrupo" /> 
										<bean:write
										name="cobrancaGrupo" property="descricaoAbreviada" /></div>
									</td>
									<td width="12%">
									<div align="center"><bean:define
										name="cobrancaAcaoAtividadeCronograma"
										property="cobrancaAcaoCronograma" id="cobrancaAcaoCronograma" />
									<bean:define name="cobrancaAcaoCronograma"
										property="cobrancaGrupoCronogramaMes"
										id="cobrancaGrupoCronogramaMes" /> <%=""
							+ Util
									.formatarAnoMesParaMesAno(((CobrancaGrupoCronogramaMes) cobrancaGrupoCronogramaMes)
											.getAnoMesReferencia())%></div>
									</td>
									<td width="20%">
									<div align="center"><bean:define
										name="cobrancaAcaoAtividadeCronograma"
										property="cobrancaAcaoCronograma" id="cobrancaAcaoCronograma" />
									<bean:define name="cobrancaAcaoCronograma"
										property="cobrancaAcao" id="cobrancaAcao" /> <bean:write
										name="cobrancaAcao" property="descricaoCobrancaAcao" /></div>
									</td>
									<td width="20%">
									<div align="center"><bean:define
										name="cobrancaAcaoAtividadeCronograma"
										property="cobrancaAtividade" id="cobrancaAtividade" /> <bean:write
										name="cobrancaAtividade" property="descricaoCobrancaAtividade" />
									</div>
									</td>
									<td width="7%">
									<div align="center"><bean:write
										name="cobrancaAcaoAtividadeCronograma" format="dd/MM/yyyy"
										property="comando" /></div>
									</td>
									<td width="6%"><bean:write name="cobrancaAcaoAtividadeCronograma"
										format="HH:mm:ss" property="comando" /></td>
									<td width="17%">
									<div align="center"><bean:write
										name="cobrancaAcaoAtividadeCronograma" format="dd/MM/yyyy"
										property="dataPrevista" /></div>
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
					</table>
					</td>
				</tr>
				<tr bordercolor="#cbe5fe">
					<td colspan="8" bgcolor="#cbe5fe">&nbsp;</td>
				</tr>
				<tr bordercolor="#cbe5fe">
					<td colspan="8" bgcolor="#cbe5fe">
					  <div align="left">
					    <gsan:controleAcessoBotao name="Button2" value="Remover" onclick="javascript:excluirComandoAtividadeCronogramaAcaoCobranca(idCobrancaAcaoAtividadeCronograma, 'checkbox');" url="excluirComandoAtividadeCronogramaAcaoCobrancaAction.do"/>
					    <%-- <input name="Button22" type="button" onclick="javascript:excluirComandoAtividadeCronogramaAcaoCobranca(idCobrancaAcaoAtividadeCronograma, 'checkbox');" class="bottonRightCol" value="Remover"> --%>
					  </div>
					</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2">
					<table width="100%" bgcolor="#99CCFF">
						<tr bordercolor="#90c7fc">
							<td colspan="7" bgcolor="#90c7fc"><strong>Atividades de
							A&ccedil;&atilde;o de Cobran&ccedil;a Eventuais Comandadas</strong></td>
						</tr>
						<tr bordercolor="#90c7fc">
							<td width="3%" rowspan="2" bgcolor="#90c7fc">
							<div align="center"><strong><A HREF="javascript:facilitadorSegundo(this);" id="2"><strong>Todos</strong></A></strong></div>
							</td>
							<td width="20%" rowspan="2" bgcolor="#90c7fc">
							<div align="center"><strong>A&ccedil;&atilde;o</strong></div>
							</td>
							<td width="13%" rowspan="2" bgcolor="#90c7fc">
							<div align="center"><strong>Atividade</strong></div>
							</td>
							<td width="15%" rowspan="2" bgcolor="#90c7fc">
							 <div align="center"><strong>Título</strong></div>
							</td>
							<td colspan="22%" bgcolor="#90c7fc">
							<div align="center"><strong> Comando</strong></div>
							</td>
							<td width="12%" rowspan="2" bgcolor="#90c7fc">
							<div align="center"><strong> Crit&eacute;rio Utilizado</strong></div>
							</td>
							<td width="15%" rowspan="2" bgcolor="#90c7fc">
							<div align="center"><strong>Crit&eacute;rio</strong></div>
							</td>
						</tr>
						<tr bordercolor="#90c7fc">
							<td width="11%" bgcolor="#90c7fc">
							<div align="center"><strong>Data </strong></div>
							</td>
							<td width="11%" bgcolor="#90c7fc">
							<div align="center"><strong>Hora</strong></div>
							</td>
						</tr>
					</table>

					<div style="width: 100%; height: 100; overflow: auto;">

					<table width="100%" bgcolor="#99CCFF">

						<%cor = "#cbe5fe";%>
						<logic:present
							name="colecaoAtividadesEventuaisAcaoCobrancaComandadas"
							scope="session">
							<logic:iterate
								name="colecaoAtividadesEventuaisAcaoCobrancaComandadas"
								id="cobrancaAcaoAtividadeComando">
								<%if (cor.equalsIgnoreCase("#cbe5fe")) {
				cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
				cor = "#cbe5fe";%>
								<tr bgcolor="cbe5fe">
									<%}%>
									<td width="3%">
									<div align="center"><strong> <input type="checkbox"
										name="idCobrancaAcaoAtividadeComando" id="2"
										value="<bean:write	name="cobrancaAcaoAtividadeComando" property="id" />" />
									</strong></div>
									</td>

									<td width="20%">
									<div align="center"><a
										href="javascript:visualizarComandoAcaoCobranca(<bean:write	name="cobrancaAcaoAtividadeComando" property="id" />)">
										<bean:define
										name="cobrancaAcaoAtividadeComando" property="cobrancaAcao"
										id="cobrancaAcao" /> <bean:write name="cobrancaAcao"
										property="descricaoCobrancaAcao" /></a></div>
									</td>

									<td width="15%">
									<div align="center"><bean:define
										name="cobrancaAcaoAtividadeComando"
										property="cobrancaAtividade" id="cobrancaAtividade" /> <bean:write
										name="cobrancaAtividade" property="descricaoCobrancaAtividade" /></div>
									</td>
									<td width="25%">
									<div align="center"><bean:write name="cobrancaAcaoAtividadeComando"
										property="descricaoTitulo" /></div>
									</td>

									<td width="11%">
									<div align="center"><bean:write
										name="cobrancaAcaoAtividadeComando" format="dd/MM/yyyy"
										property="comando" /></div>
									</td>
									<td width="11%">
									<div align="center"><bean:write
										name="cobrancaAcaoAtividadeComando" format="HH:mm:ss"
										property="comando" /></div>
									</td>

									<td width="15%">
									<div align="center"><%if (((CobrancaAcaoAtividadeComando) cobrancaAcaoAtividadeComando)
					.getIndicadorCriterio() != null) {
				if (((CobrancaAcaoAtividadeComando) cobrancaAcaoAtividadeComando)
						.getIndicadorCriterio().shortValue() == (short) 1) {

				%>Rota <%} else {

				%>Comando<%}
			}

			%></div>
									<logic:present name="cobrancaAcaoAtividadeComando"
										property="cobrancaCriterio">
										<td width="15%">
										<div align="center"><bean:define
											name="cobrancaAcaoAtividadeComando"
											property="cobrancaCriterio" id="cobrancaCriterio" /> <bean:write
											name="cobrancaCriterio" property="descricaoCobrancaCriterio" />
										</div>

										</td>

									</logic:present> <logic:notPresent
										name="cobrancaAcaoAtividadeComando"
										property="cobrancaCriterio">
										<td width="15%">
										<div align="center"></div>

										</td>

									</logic:notPresent>
								</tr>
							</logic:iterate>
						</logic:present>
					</table>
					</td>
				</tr>

				<tr bordercolor="#cbe5fe">
					<td colspan="7" bgcolor="#cbe5fe">&nbsp;</td>
				</tr>
				<tr bordercolor="#cbe5fe">
					<td colspan="7" bgcolor="#cbe5fe">
					<div align="left">
					  <gsan:controleAcessoBotao name="Button2" value="Remover" onclick="javascript:excluirComandoAtividadeEventualCobranca(idCobrancaAcaoAtividadeComando, 'checkbox');" url="excluirComandoAtividadeEventualAcaoCobrancaAction.do"/>
					  <%-- <input name="Button2" type="button" onclick="javascript:excluirComandoAtividadeEventualCobranca(idCobrancaAcaoAtividadeComando, 'checkbox');" class="bottonRightCol" value="Remover"> --%>
					</div>
					</td>
				</tr>
			</table>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>


</html:form>
</body>
</html:html>
