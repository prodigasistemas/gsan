<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@page isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>regras_validator.js"></script>

<script language="JavaScript">

    function validarForm() {
    var form = document.forms[0];
	  if(validateAtualizarMaterialActionForm(form) && validateCaracterEspecial(form)) {	     
		  submeterFormPadrao(form);   	
		  
   	  }
  	}
	
	function caracteresespeciais () {
    	this.aa = new Array("descricaoMaterial", "Descrição possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    	this.ab = new Array("abrevMaterial", "Descrição abreviada possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    }  
</script>
</head>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AtualizarMaterialActionForm" />

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">

	<html:form action="/atualizarMaterialAction.do"
		name="AtualizarMaterialActionForm"
		type="gcom.gui.atendimentopublico.ordemservico.AtualizarMaterialActionForm"
		method="post"
		onsubmit="return validateAtualizarMaterialActionForm(this);">

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

						<%@ include file="/jsp/util/mensagens.jsp"%>

						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
					</div></td>


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
								src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
							</td>
							<td class="parabg">Atualizar Material</td>
							<td width="11" valign="top"><img border="0"
								src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
							</td>
						</tr>
					</table>

					<p>&nbsp;</p>
					<table width="100%" border="0">
						<tr>
							<td colspan="2">Para atualizar um material, informe os dados
								gerais abaixo:</td>
						</tr>

						<tr>
							<td><strong>Identificador do Material :</strong>
							</td>
							<td colspan="2"><html:text property="id" size="10"
									maxlength="10" tabindex="1"
									style="background-color:#EFEFEF; border:0; color: #000000;"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<td><strong>Código do Material :</strong>
							</td>
							<td colspan="2"><html:text property="codigo" size="10"
									maxlength="10" tabindex="2" />
							</td>
						</tr>
						<tr>
							<td><strong>Descrição:<font color="#FF0000">*</font>
							</strong>
							</td>
							<td colspan="2"><html:text property="descricaoMaterial"
									size="40" maxlength="40" tabindex="3" />
							</td>
						</tr>

						<tr>
							<td><strong>Descrição Abreviada:</strong>
							</td>
							<td colspan="2"><html:text property="abrevMaterial" size="7"
									maxlength="5" tabindex="4" />
							</td>
						</tr>

						<tr>
							<td><strong>Unidade do Material:<font
									color="#FF0000">*</font>
							</strong>
							</td>
							<td colspan="2"><html:select property="unidadeMaterial"
									tabindex="5" style="width:200px;">
									<html:option value="-1"> &nbsp; </html:option>
									<html:options collection="colecaoMaterialUnidade" property="id"
										labelProperty="descricao" />
								</html:select></td>
						</tr>
						<tr>
							<td><strong>Indicador de Uso:<font color="#FF0000">*</font>
							</strong>
							</td>
							<td><html:radio property="indicadorUso" value="1"
									tabindex="6" /><strong>Ativo <html:radio
										property="indicadorUso" value="2" tabindex="6" />Inativo</strong></td>
						</tr>
						<tr>
						</tr>
						<tr>
						</tr>
						<tr>
						</tr>
						<tr>
							<td width="500" colspan="2">
								<div align="center">
									<font color="#FF0000">*</font> Campos obrigatórios
								</div></td>
						</tr>

					</table>
					<table width="100%">
						<tr>
							<td width="40%" align="left"><input type="button"
								name="ButtonCancelar" class="bottonRightCol" value="Voltar"
								onClick="javascript:window.location.href='${sessionScope.caminhoRetornoVoltar}';">

								<input type="button" name="ButtonReset" class="bottonRightCol"
								value="Desfazer"
								onclick="window.location.href='/gsan/exibirAtualizarMaterialAction.do?desfazer=S&reloadPage=1&idMaterial=<bean:write name="AtualizarMaterialActionForm" property="id" />';">
								<input type="button" name="ButtonCancelar"
								class="bottonRightCol" value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</td>
							<td align="right"><input type="button" name="Button"
								class="bottonRightCol" value="Atualizar"
								onclick="validarForm();" /></td>
						</tr>
					</table>
			</tr>


		</table>
		<%@ include file="/jsp/util/rodape.jsp"%>
	</html:form>
</html:html>
