<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.cadastro.tarifasocial.bean.CriterioSelecaoHelper"%>

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
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="GerarComandoCartasTarifaSocialActionForm" />
<script>

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
		
		if (tipoConsulta == 'localidade') {

			form.codigoLocalidade.value = codigoRegistro;
	  		form.descricaoLocalidade.value = descricaoRegistro;
	  		form.descricaoLocalidade.style.color = "#000000";
	  		form.codigoLocalidade.focus();
	 	}
	}

function replicaDados(campoOrigem, campoDestino){
	campoDestino.value = campoOrigem.value;
}

function validarForm(){
	var form = document.forms[0];		
	form.action = 'gerarComandoCartasTarifaSocialAction.do';
	submeterFormPadrao(form);
}

function limparLocalidade() {
    var form = document.GerarComandoCartasTarifaSocialActionForm;
     form.codigoLocalidade.value = '';
     form.descricaoLocalidade.value = '';
}

	
function limparTela(){
	var form = document.forms[0];
	limparLocalidade();
	form.action = "/gsan/exibirGerarComandoCartasTarifaSocialAction.do?menu=sim";
	submeterFormPadrao(form);
}

function simular(){
	var form = document.forms[0];		

	if (validateGerarComandoCartasTarifaSocialActionForm(form)){
		form.action = 'gerarComandoCartasTarifaSocialAction.do?acao=1&criterios='+ obterValorCheckboxMarcado();
		submeterFormPadrao(form);
	}
}

function gerar(){
	var form = document.forms[0];		
	
	if (validateGerarComandoCartasTarifaSocialActionForm(form)){
		form.action = 'gerarComandoCartasTarifaSocialAction.do?acao=2&criterios='+ obterValorCheckboxMarcado();
		submeterFormPadrao(form);
	}
	

}

	function controleTipoCarta(tipoCarta){
		var form = document.forms[0];		
		
		if(tipoCarta == 1){
			form.anoMesPesquisaInicial.disabled = false;
			form.anoMesPesquisaFinal.disabled = false;
			form.qtdeDiasAtraso.value = '';
			form.qtdeDiasAtraso.disabled = true;
			habilitaCriterios();				
		}else if(tipoCarta == 2){
			form.anoMesPesquisaInicial.value = '';
			form.anoMesPesquisaFinal.value = '';
			form.anoMesPesquisaInicial.disabled = true;
			form.anoMesPesquisaFinal.disabled = true;
			form.qtdeDiasAtraso.disabled = false;
			desabilitaCriterios();
		}
		
	}


	function desabilitaCriterios() {
			
		for (var i=0;i < document.forms[0].elements.length;i++){
			var elemento = document.forms[0].elements[i];
			if (elemento.type == "checkbox" && elemento.name == 'criterio'){
				
				if (elemento.checked == true){
					elemento.checked = false;
				}
				elemento.disabled = true;
			}
		}
	}
	
	function habilitaCriterios() {
			
		for (var i=0;i < document.forms[0].elements.length;i++){
			var elemento = document.forms[0].elements[i];
			if (elemento.type == "checkbox" && elemento.name == 'criterio'){
				
				elemento.disabled = false;
			}
		}
	}

</script>


</head>

<body leftmargin="5" topmargin="5" onload="javascript:controleTipoCarta('${requestScope.tipoCarta}');">
<html:form action="/gerarComandoCartasTarifaSocialAction.do"
	name="GerarComandoCartasTarifaSocialActionForm"
	type="gcom.gui.cadastro.GerarComandoCartasTarifaSocialActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellpadding="0" cellspacing="5">
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
					<td width="11"><img
						src="<bean:message key="caminho.imagens"/>parahead_left.gif"
						border="0" /></td>
					<td class="parabg">Gerar Cartas para Recadastramento Tarifa Social</td>
					<td width="11" valign="top"><img
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para gerar as cartas, informe os dados abaixo:</td>
				</tr>
			</table>
			
			
			<table width="100%" border="0">

              	<tr>
					<td width="27%"><strong>Gerência:</strong></td>
					<td align="left"><strong><strong> 
						<html:select property="gerenciaRegionalId">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoGerenciaRegional" labelProperty="nome" property="id" />
					</html:select> </strong></strong></td>
				</tr>
				
				<tr>
					<td><strong>Unidade de Negócio:</strong></td>
					<td align="left"><strong><strong> 
						<html:select property="unidadeNegocioId">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id" />
					</html:select> </strong></strong></td>
				</tr>
              	
              		
				<tr>
					<td align="left"><strong> Localidade</strong></td>
					<td align="left">
						<html:text value="${requestScope.codigoLocalidade}"	
						property="codigoLocalidade" 
						size="3" 
						maxlength="3"
					 	onkeyup="somente_numero_zero_a_nove(this);"
						onkeypress="validaEnter(event, 'exibirGerarComandoCartasTarifaSocialAction.do', 'codigoLocalidade');" />
						
					<img src="<bean:message key="caminho.imagens"/>pesquisa.gif" width="23" height="21" border="0"
						onclick="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do', 400, 800);">
					<input type="text" name="descricaoLocalidade" readonly
							style="background-color:#EFEFEF; border:0" size="30"
							maxlength="30" value="${requestScope.descricaoLocalidade}" />
					<c:if test="${empty requestScope.codigoLocalidade}" var="testeCor">
						<script>document.GerarComandoCartasTarifaSocialActionForm.descricaoLocalidade.style.color = '#FF0000';</script>
					</c:if>
					<c:if test="${!testeCor}">
						<script>document.GerarComandoCartasTarifaSocialActionForm.descricaoLocalidade.style.color = '#000000';</script>
					</c:if>
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						width="23" height="21" onclick="javascript:limparLocalidade();"></td>
				</tr>
              	
              	<tr>
					<td><strong>Tipo da Carta:<font	color="#FF0000">*</font></strong></td>
					<td>
						<html:radio property="tipoCarta" value="1" onclick="javascript:controleTipoCarta(this.value);"/> <strong>Recadastramento </strong>
						<html:radio property="tipoCarta" value="2" onclick="javascript:controleTipoCarta(this.value);"/> <strong>Cobrança de Débito </strong>
					</td>
				</tr>
	
				<tr>
					<td><strong>Cobrar Débito com mais de</strong></td>
					<td><html:text property="qtdeDiasAtraso" size="4" maxlength="4" onkeypress="return isCampoNumerico(event);"/>
					<strong>&nbsp; dias de atraso </strong></td>
				</tr>
			</table>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td HEIGHT="5"></td>
				</tr>
				<tr>
					<td bgcolor="#000000" height="1"></td>
				</tr>
				<tr>
					<td HEIGHT="5"></td>
				</tr>
			</table>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td width="28%" ><strong>Período a ser pesquisado:</strong></td>
					
					<td><strong> 
					    <html:text property="anoMesPesquisaInicial" size="7"
						  onkeyup="mascaraAnoMes(this, event); replicaDados(document.GerarComandoCartasTarifaSocialActionForm.anoMesPesquisaInicial, document.GerarComandoCartasTarifaSocialActionForm.anoMesPesquisaFinal);somente_numero(this);"
						  onkeypress="return isCampoNumerico(event);" maxlength="7" />
						
				  	    a</strong> 
					
					    <html:text property="anoMesPesquisaFinal" size="7"
						  onkeyup="mascaraAnoMes(this, event); somente_numero(this);"
						  onkeypress="return isCampoNumerico(event);" maxlength="7"/> 
						
						(mm/aaaa)
					</td>
				</tr>
				 <tr>
					<td HEIGHT="5"></td>
				</tr>
			 </table>
			
			 <!-- tabela de parâmentros para recadastramento dos clientes -->
			<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td>
				
					<table width="100%" cellpadding="0" cellspacing="0">
					<tr bgcolor="#90c7fc">
						<td width="10%">
							<div align="center"><strong>Marca</strong></div>
						</td>
						<td width="90%">
							<div align="center"><strong>Parâmetros para Recadastramento dos Clientes</strong></div>
						</td>
					</tr>
					</table>

				</td>
			</tr>
			
			<logic:present name="colecaoCriterios">
			
			<logic:notEmpty name="colecaoCriterios">

			<tr>
				<td>
										
					<% String cor2 = "#cbe5fe";%>

					<div style="width: 100%; height: 100; overflow: auto;">
										
					<table width="100%" align="center" bgcolor="#90c7fc">
										
					<logic:iterate name="colecaoCriterios" id="criterio" type="CriterioSelecaoHelper">

						
						<%	if (cor2.equalsIgnoreCase("#cbe5fe")){
							cor2 = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
						<%} else{
							cor2 = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
						<%}%>

						<td width="10%" align="center">
							<INPUT TYPE="checkbox" NAME="criterio"
							value="<%="" + criterio.getCodigoCriterio() %>"
							alt="<%="" + criterio.getCodigoCriterio()%>">
						</td>
						
						<td width="90%" align="left">
							<bean:write name="criterio" property="descricaoCriterio" />
						</td>
	
					</tr>
			
					</logic:iterate>
										
					</table>
										
					</div>
		
				</td>
			</tr>

			</logic:notEmpty>
			
			</logic:present>

			</table>
			<!-- tabela de parâmentros para recadastramento dos clientes-->
			 <table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td HEIGHT="5"></td>
				</tr>
				<tr>
					<td bgcolor="#000000" height="1"></td>
				</tr>
				<tr>
					<td HEIGHT="5"></td>
				</tr>
			</table>
			 <table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td  width="37%" ><strong>Prazo para comparecer na Companhia de Saneamento <font color="#FF0000">*</font></strong></td>
					<td><html:text property="prazoComparecerCompesa" size="4" maxlength="4" onkeypress="return isCampoNumerico(event);"/>
					<strong>&nbsp; </strong>(dias)</td>
				</tr>
	
				<tr>
					<td>&nbsp;</td>
					<td colspan="2" align="left"><font color="#FF0000">*</font> Campo Obrigat&oacute;rio</td>
				</tr>
			  </table>

			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td HEIGHT="5"></td>
				</tr>
				<tr>
					<td bgcolor="#000000" height="1"></td>
				</tr>
				<tr>
					<td HEIGHT="5"></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left" onclick="javascript:limparTela();">
					<input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						
						
					</td>

					<td align="right"><input type="button" name="" value="Simular"
						class="bottonRightCol"
						onclick="javascript:simular();"/>
						<input name="Button" type="button" class="bottonRightCol"
						value="Gerar" align="left"
						onclick="javascript:gerar()"></td>
				</tr>

			</table>
			
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
