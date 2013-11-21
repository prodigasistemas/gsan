<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.ConstantesSistema" %>

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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="FiltrarRoteiroEmpresaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

	function validarForm(form){
		if (validateFiltrarRoteiroEmpresaActionForm(form)){
				submeterFormPadrao(form);
		}	
	}

	function limparEmpresa(form) {
    	form.empresa.value = "";
	}
	
	function limparLocalidade(form) {
    	form.idLocalidade.value = "";
    	form.descricaoLocalidade.value = "";
	}
	
	function limparLeiturista(form) {
    	form.idLeiturista.value = "";
    	form.nomeLeiturista.value = "";
	}

 	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	    var form = document.forms[0];

    	if (tipoConsulta == 'localidade') {
      		limparLocalidade(form);
	      	form.idLocalidade.value = codigoRegistro;
    	  	form.descricaoLocalidade.value = descricaoRegistro;
      		form.descricaoLocalidade.style.color = "#000000";
	       	form.idLeiturista.focus();
			form.action = 'exibirFiltrarRoteiroEmpresaAction.do?localidadeAlterada=Sim';
			submeterFormPadrao(form);       	
    	} else 
	    if (tipoConsulta == 'setorComercial') {
	      	limparSetorComercial(form);
	      	form.codigoSetorComercial.value = codigoRegistro;
	      	form.setorComercialDescricao.value = descricaoRegistro;
	    	form.setorComercialDescricao.style.color = "#000000";

			form.action = 'exibirFiltrarRoteiroEmpresaAction.do';
			submeterFormPadrao(form);
    	} else 
		if (tipoConsulta == 'leiturista') {
	    	form.idLeiturista.value = codigoRegistro;
	    	form.nomeLeiturista.value = descricaoRegistro;
	    	form.nomeLeiturista.style.color = "#000000";
	    } 
   }
   
	function limparForm(form) {
		form.idEmpresa.value = "";
		form.idLocalidade.value = "";
		form.descricaoLocalidade.value = "";
		form.idLeiturista.value = "";
		form.nomeLeiturista.value = "";
		MoverTodosDadosSelectMenu2PARAMenu1('FiltrarRoteiroEmpresaActionForm', 'idSetorComercial', 'idSetorComercialSelecionados');		
	}   
	
	function verificarValorAtualizar() {
	
		var form = document.forms[0];
	
		if (form.atualizar.checked == true) {
			form.atualizar.value = '1';
		} else {
			form.atualizar.value = '';
		}
		
	}
	
	function checkAtualizar(valor) {
	
		var form = document.forms[0];
		
		if (valor == '1') {
			form.atualizar.checked = true;
		} else {
			form.atualizar.checked = false;
		}
		
	}

</script>
</head>

<body leftmargin="5" topmargin="5"
  onload="javascript:setarFoco('${requestScope.nomeCampo}');checkAtualizar('${sessionScope.atualizar}');verificarValorAtualizar();">
  
<html:form action="/filtrarRoteiroEmpresaAction"
	name="FiltrarRoteiroEmpresaActionForm"
	type="gcom.gui.micromedicao.FiltrarRoteiroEmpresaActionForm"
	method="post"
	onsubmit="return validateFiltrarRoteiroEmpresaActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<input type="hidden" name="quadrasSelecionadas">

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="120" valign="top" class="leftcoltext">
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
			<table>
				<tr>
					<td></td>
				</tr>

			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Filtrar Roteiro Empresa</td>
					<td width="11" valign="top"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
<p>&nbsp;</p>
   			<table width="100%" border="0">

				<tr>
					<td>Para filtrar o(s) roteiro(s) empresa, informe os dados abaixo:</td>
					<td align="right"><input type="checkbox" name="indicadorAtualizar" value="1" checked="checked" /><strong>Atualizar</strong>
					<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=roteiroEmpresaFiltrar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
				</tr>
			</table>
			<table width="100%" border="0">
              <tr> 
				<td width="163"><strong>Empresa:</strong></td>
				<td align="right"><div align="left"> <span class="style2">
				  <html:select property="empresa" tabindex="1" >
					<html:option value="<%= ""+ConstantesSistema.NUMERO_NAO_INFORMADO %>">&nbsp;</html:option>				  
					<html:options collection="colecaoEmpresa"
						labelProperty="descricao" property="id" />
				  </html:select>
				  </span></div>
				</td>
              </tr>
			  <tr>
					<td><strong>Localidade:</strong></td>
					<td colspan="2"><html:text maxlength="3" tabindex="3" property="idLocalidade"
						size="3"
						onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarRoteiroEmpresaAction.do', 'idLocalidade', 'Localidade');" />
					<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do');">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Localidade" /></a> <logic:present
						name="idLocalidadeNaoEncontrada">
						<logic:equal name="idLocalidadeNaoEncontrada" value="exception">
							<html:text property="descricaoLocalidade" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="idLocalidadeNaoEncontrada" value="exception">
							<html:text property="descricaoLocalidade" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present>
					<logic:notPresent name="idLocalidadeNaoEncontrada">
						<logic:empty name="FiltrarRoteiroEmpresaActionForm" property="idLocalidade">
							<html:text property="descricaoLocalidade" value="" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>

						<logic:notEmpty name="FiltrarRoteiroEmpresaActionForm"
							property="idLocalidade">
							<html:text property="descricaoLocalidade" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent> 
					<a href="javascript:limparLocalidade(document.FiltrarRoteiroEmpresaActionForm);
						limparSetorComercial(document.FiltrarRoteiroEmpresaActionForm);">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a></td>
				</tr>
              <tr> 
				<td width="163"><strong>Setor Comercial:</strong></td>
				<td align="right"><div align="left"> <span class="style2">
				  <html:select property="idSetorComercial" tabindex="1" >
					<html:option value="<%= ""+ConstantesSistema.NUMERO_NAO_INFORMADO %>">&nbsp;</html:option>				  
					<html:options collection="colecaoSetorComercial"
						labelProperty="codigo" property="id" />
				  </html:select>
				  </span></div>
				</td>
              </tr>              
				<tr>
					<td width="130"><strong>Leiturista:</strong></td>
					<td colspan="2" nowrap="nowrap"><html:text maxlength="9" property="idLeiturista"
						tabindex="4" size="9"
						onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarRoteiroEmpresaAction.do', 'idLeiturista', 'Leiturista');" />
					<a href="javascript:abrirPopup('exibirPesquisarLeituristaAction.do');">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Leiturista" /></a> 
					<logic:present name="idLeituristaEncontrado" scope="request">
						<html:text maxlength="30" property="nomeLeiturista"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="40" />
					</logic:present> 
					<logic:notPresent name="idLeituristaEncontrado" scope="request">
						<html:text maxlength="30" property="nomeLeiturista"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" />
					</logic:notPresent> 
					<a href="javascript:limparLeiturista(document.FiltrarRoteiroEmpresaActionForm);"> 
					  <img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Indicador de Uso:</strong></td>
					<td><strong> <span class="style1"><strong> 
					<html:radio property="indicadorUso" value="1" tabindex="6"/> <strong>Ativo 
					<html:radio property="indicadorUso" value="2"/> Inativo
					<html:radio property="indicadorUso" value="3" /> Todos
					</strong></strong></span></strong></td>
				</tr>				
				
              <tr> 
                <td colspan="6"><strong> <font color="#FF0000"></font></strong> 
                  <div align="left"> 
                    <hr>
                  </div></td>
              </tr>
              <tr> 
                <td colspan="2"><strong> <font color="#FF0000"> 
                  <input type="button" name="Submit22" class="bottonRightCol" value="Desfazer"
                      onclick="window.location.href='<html:rewrite page="/exibirFiltrarRoteiroEmpresaAction.do?desfazer=S"/>'">
                  <input type="button" name="Submit23" class="bottonRightCol" value="Cancelar"
                      onclick="window.location.href='/gsan/telaPrincipal.do'">
                  </font></strong>
                  </td>
                  <td colspan="5" align="right">
						<td colspan="2" align="right"><input type="button" name="Submit2"
						class="bottonRightCol" value="Filtrar"
						onclick="validarForm(document.forms[0]);">
                </td>
              </tr>
            </table>
 
		<tr>



		</tr>
	  </table>
 
   	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
