<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
function reload() {
	alert("reload");
   	redirecionarSubmit("exibirManterConsumoTarifaSubCategoriaAction.do");
      
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


   
 function novoFiltro(){
	var formRed = "/gsan/exibirFiltrarConsumoTarifaSubCategoriaAction.do";
		redirecionarSubmit(formRed);
 }
  
  	
 	function validarForm(objeto){ 
  	
  	
       var indice;
       var array = new Array(objeto.length);
       var selecionado = "";
	   var formulario = document.forms[0]; 

	   var acumulador = 0;

    	for(indice = 0; indice < formulario.elements.length; indice++){
		   if (formulario.elements[indice].type == 'checkbox' && formulario.elements[indice].checked == true) {
			   acumulador = acumulador + 1;  
		   }
	    }

        var ids = new Array(acumulador);
	    var i = 0;
    	for(indice = 0; indice < formulario.elements.length; indice++){
		   if (formulario.elements[indice].type == 'checkbox' && formulario.elements[indice].checked == true) {
			   selecionado = formulario.elements[indice].value;
			   ids[i]= selecionado;
			   i = i + 1;
		   }
	    }
  		abrirPopup('/gsan/exibirInserirReajusteConsumoTarifaSubCategoriaAction.do?id_r=' + ids, 500, 610);
  		
  	}
  	
	function  remover(){
		if(CheckboxNaoVazio(document.ManutencaoRegistroActionForm.idRegistrosRemocao) && confirm('Confirma remoï¿½ï¿½o ?')){
		    var form = document.forms[0];
    		form.action = "removerConsumoTarifaSubCategoriaAction.do";
	    	form.submit()	
	    }
	}  	
  	
</script>
</head>
<!-- abrirPopup('/gsan/exibirInserirReajusteConsumoTarifaAction.do?id_r=' + document.forms[0].idRegistrosRemocao, 350, 610); -->
<body leftmargin="5" topmargin="5">
<html:form action="/removerConsumoTarifaSubCategoriaAction"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm"
	onsubmit=""
	method="post">

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
					<td class="parabg">Informar Tarifa de Consumo por Subcategoria</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%">
				<tr>
					<td  height="3"><font style="font-size: 10px;"
						color="#000000" face="Verdana, Arial, Helvetica, sans-serif"><strong>Tarifas
					de consumo encontradas:</strong></font></td>
					<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=faturamentoTarifaConsumoManter', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>					    							
					</tr>
					</table>
					
					<table>
						<tr>
							<td></td>
						</tr>
					</table>
					<table border="0" width="100%" bgcolor="#99ccff">
						<tr>
							<td colspan="3" bgcolor="#000000" height="2"></td>
						</tr>
						<tr>
							<td bgcolor="#99ccff" width="10%" align="center"><strong><a
								href="javascript:facilitador(this);">Todos</a></strong></td>
							<td bgcolor="#99ccff" align="center"><strong>Descrição da Tarifa de Consumo</strong></td>
							<td bgcolor="#99ccff" align="center"><strong>Data de Vigência</strong></td>
						</tr>
						<%String cor = "#cbe5fe";%>
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg" />
							<pg:param name="q" />
							<logic:present name="colecaoFiltroConsumoTarifaVigencia"
								scope="session">
								<logic:iterate name="colecaoFiltroConsumoTarifaVigencia"
									id="vigenciaF">
									<pg:item>
										<%if (cor.equalsIgnoreCase("#cbe5fe")) {
				cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF">
											<%} else {
				cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td>
											<div align="center"><strong> <input name="idRegistrosRemocao"
												value="<bean:write name="vigenciaF" property="id" />"
												type="checkbox"> </strong></div>
											</td>
											<td align="left"><a
												href="/gsan/exibirManterConsumoTarifaExistenteSubCategoriaAction.do?idVigencia=<bean:write name="vigenciaF" property="id"/>"><bean:write
												name="vigenciaF" property="consumoTarifa.descricao" /></a></td>
											<td align="center"><bean:write name="vigenciaF" format="dd/MM/yyyy"
												property="dataVigencia" /></td>
										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
					</table>
					<table width="100%" border="0">
				<tr>
					<td>
					<div align="center"><strong><%@ include	file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
					</td>
					</pg:pager>
				</tr>
			
				<tr>
				<td><!--  
					<gsan:controleAcessoBotao name="Button2" value="Reajustar"
							  onclick="javascript:validarForm(idRegistrosRemocao);" url="exibirInserirReajusteConsumoTarifaAction.do"/> -->
							  
					<!--<input name="Button" class="bottonRightCol" value="Reajustar"
						type="button" onclick="validarForm(idRegistrosRemocao);"> -->
					
					<!-- 
					<html:button
						styleClass="bottonRightCol" value="Remover" property="Button" onclick="javascript:remover();" /> -->
					<input type="button" name="Button2" Class="bottonRightCol"
						value="Voltar Filtro" onclick="javascript:novoFiltro();"></td>
						<td valign="top">
					<div align="right"><a href="javascript:toggleBox('demodiv',1);"> <img
						border="0" src="<bean:message key="caminho.imagens"/>print.gif"
						title="Imprimir Tarifas de Consumo" /> </a></div>
					</td>
				</tr>
				</table>
			</table>
		<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioConsumoTarifaManterAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
