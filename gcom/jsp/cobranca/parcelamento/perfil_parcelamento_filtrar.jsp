<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
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
	src="<bean:message key="caminho.js"/>util.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarPerfilParcelamentoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(form){
		if(validateFiltrarPerfilParcelamentoActionForm(form)){
			submeterFormPadrao(form);
		}
	}

	function verificarChecado(valor){
		form = document.forms[0];
		if(valor == "1"){
		 	form.indicadorAtualizar.checked = true;
		 }else{
		 	form.indicadorAtualizar.checked = false;
		}
	}

</script>
</head>
<body leftmargin="0" topmargin="0"
onload="setarFoco('${requestScope.nomeCampo}');verificarChecado('${sessionScope.indicadorAtualizar}');">
<html:form action="/filtrarPerfilParcelamentoAction"
	name="FiltrarPerfilParcelamentoActionForm"
	type="gcom.gui.cobranca.parcelamento.FiltrarPerfilParcelamentoActionForm"
	method="post"
	onsubmit="return validateFiltrarPerfilParcelamentoActionForm(this);">


<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

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
              <tr><td></td></tr>
              
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
                <td class="parabg">Filtrar Perfil de Parcelamento</td>
                <td width="11" valign="top"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
              </tr>
            </table>


			<p>&nbsp;</p>
			<table width="100%" border="0">

				 <tr>
			          <td width="100%" colspan=2>
				          <table>
				          	<tr>
				          		<td width="510px">Para filtrar um  perfil de parcelamento no sistema, informe os dados abaixo:</td>
				          		<td align="right"><input type="checkbox" name="indicadorAtualizar" value="1" /><strong>Atualizar</strong>
									<p>&nbsp;</p>
								</td>
				          	</tr>
				          </table>
			          </td>
			     </tr>

				<tr>
					<td><strong>Número da RD:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="resolucaoDiretoria" tabindex="3">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="collectionResolucaoDiretoria"
							labelProperty="numeroResolucaoDiretoria" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>	
							
              	<tr>
					<td><strong>Tipo da Situação do Imóvel:</strong></td>
					<td><html:select property="imovelSituacaoTipo" tabindex="2">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="collectionImovelSituacaoTipo"
							labelProperty="descricaoImovelSituacaoTipo" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
					
					
              	<tr>
					<td><strong>Perfil do Imóvel:</strong></td>
					<td><html:select property="imovelPerfil" tabindex="3">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="collectionImovelPerfil"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
								
				<tr>
					<td><strong>Subcategoria:</strong></td>
					<td><html:select property="subcategoria" tabindex="4">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="collectionSubcategoria"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>	
             			  			
				<tr>
					<td >
						<input name="Button" type="button" class="bottonRightCol" value="Limpar" align="left" onclick="window.location.href='<html:rewrite page="/exibirFiltrarPerfilParcelamentoAction.do?desfazer=S"/>'" >
                   	</td>
                   	<td align="right">
                   	  <gsan:controleAcessoBotao name="Button" value="Filtrar" onclick="validarForm(document.FiltrarPerfilParcelamentoActionForm)" url="filtrarPerfilParcelamentoAction.do" align="right"/>
					  <%-- <input name="Button" type="button" class="bottonRightCol" value="Filtrar" align="right" onClick="validarForm(document.FiltrarPerfilParcelamentoActionForm)"> --%>
					</td>
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
