<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

	<head>
	
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="DesfazerVinculoPopupActionForm" />
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
		
		<script type="text/javascript">
			function verficarSelecao(objeto, tipoObjeto){
				var indice, 
		       		array = new Array(objeto.length),
		       	   	selecionado = "",
		       	   	formulario = document.forms[0],
		       	   	cont = 0;
			   	for (indice = 0; indice < formulario.elements.length; indice++) {
					if (formulario.elements[indice].type == tipoObjeto && formulario.elements[indice].checked == true) {
						selecionado = formulario.elements[indice].value;
						cont = cont +1;
				 	}
			   	}
			   	
				var qtdCheckbox = 0;
				for (indice = 0; indice < formulario.elements.length; indice++) {
					if (formulario.elements[indice].type == tipoObjeto) {
						qtdCheckbox = qtdCheckbox +1;
				 	}
			   	}
		
				if (selecionado.length < 1) {
		        	alert('Informe Matrícula');
		       	} else {
			     	if(qtdCheckbox == cont){
				    	if (confirm ("Todos os imóvel(is) serão desvinculados. Confirma desvinculação?")) {
							redirecionarSubmit("/gsan/desfazerVinculoPopupAction.do");
						}
			     	} else {
				 		if (confirm (cont+" imóvel(is) será(ão) desvinculado(s). Confirma desvinculação?")) {
							redirecionarSubmit("/gsan/desfazerVinculoPopupAction.do");
						} else {
							window.close();
						}
					}
				}
		   	}
		
			function facilitador(objeto) {
				if (objeto.id == "0" || objeto.id == undefined) {
					objeto.id = "1";
					marcarTodos();
				} else {
					objeto.id = "0";
					desmarcarTodos();
				}
			}
		</script>
	</head>
	<body leftmargin="0" topmargin="0">
		<html:form action="/desfazerVinculoPopupAction.do"
				   name="DesfazerVinculoPopupActionForm"
				   type="gcom.gui.micromedicao.DesfazerVinculoPopupActionForm"
				   method="post">
		
			<table width="672" border="0" cellpadding="0" cellspacing="5">
				<tr>
					<td width="662" valign="top" class="centercoltext">
					<table height="100%">
						<tr>
							<td></td>
						</tr>
					</table>
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td width="11">
								<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
							</td>
							<td class="parabg">Desfazer V&iacute;nculo</td>
							<td width="11">
								<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
							</td>
						</tr>
					</table>
					<p>&nbsp;</p>
					<table width="100%" border="0">
						<tr>
							<td>
								Selecione os im&oacute;veis para desfazer o v&iacute;nculo para rateio de consumo:
							</td>
							<td align="right">
								<a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=imovelManterVinculo-DesfazerVinculo', 500, 700);">
									<span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span>
								</a>
							</td>
						</tr>
						</table>
						<table width="100%" border="0">
							<tr>
								<td bgcolor="#000000" height="2"></td>
							</tr>					
							<tr>
								<td height="24">
									<div style="width: 100%; height: 200; overflow: auto;">
										<table width="100%" bgcolor="#99CCFF">
											<tr bgcolor="#99CCFF">
												<td width="4%">
													<strong>
														<a href="javascript:facilitador(this);" id="0">
															<strong>Todos</strong>
														</a>
													</strong>
												</td>
												<td width="10%">
													<div align="center" class="style9"><strong>Matr&iacute;cula</strong></div>
												</td>
												<td width="20%">
													<div align="center"><strong>Inscri&ccedil;&atilde;o</strong></div>
												</td>
												<td width="21%">
													<div align="center"><strong>Usu&aacute;rio</strong></div>
												</td>
												<td width="45%">
													<div align="center"><strong>Endere&ccedil;o</strong></div>
												</td>
											</tr>
			
											<%
											String cor = "#cbe5fe";
											String styleColor = "";
											%>
											<logic:iterate name="colecaoImoveisVinculados" id="clienteimovel">
												<%
											 	styleColor = "";
												if (cor.equalsIgnoreCase("#cbe5fe")) {
													cor = "#FFFFFF";%>
													<tr bgcolor="#FFFFFF">
												<%} else {
													cor = "#cbe5fe";%>
													<tr bgcolor="#cbe5fe">
												<%}%>
												
												<bean:define name="clienteimovel" property="imovel" id="imovel" />
												<logic:equal name="imovel" property="indicadorImovelAreaComum" value="<%=""+ConstantesSistema.SIM %>">
													<% styleColor = "color:#F00;"; %>
												</logic:equal>
												
												<td>
													<strong>
														<input type="checkbox" name="idImovel"	value='<bean:define name="clienteimovel" property="imovel" id="imovel" /> <bean:write name="imovel" property="id" />' />
													</strong>
												</td>
												<td width="10%">
													<div align="center" class="style9" style="<%=styleColor %>">
														<bean:define name="clienteimovel" property="imovel" id="imovel" /> 
														<bean:write name="imovel" property="id" />
													</div>
												</td>
												<td width="20%">
													<div align="center" style="<%=styleColor %>">
														<bean:define name="clienteimovel" property="imovel" id="imovel" />
														<%=((gcom.cadastro.imovel.Imovel) imovel).getInscricaoFormatada()%>
													</div>
												</td>
												<td width="21%">
													<div align="left" style="<%=styleColor %>">
														<bean:define name="clienteimovel" property="cliente" id="cliente" />
														<bean:write name="cliente" property="nome" />
													</div>
												</td>
												<td width="45%">
													<div align="left" style="<%=styleColor %>">
														<bean:define name="clienteimovel" property="imovel" id="imovel" />
														<%=((gcom.cadastro.imovel.Imovel) imovel).getEnderecoFormatado()%>
													</div>
												</td>
											</logic:iterate>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td height="27">
									<div align="right">
										<input name="Button" type="button" class="bottonRightCol" value="Desfazer V&iacute;nculo" onclick="verficarSelecao(idImovel, 'checkbox')" />
										<input name="Button2" type="button" class="bottonRightCol" value="Fechar" onClick="javascript:window.close();" />
									</div>
								</td>
							</tr>
						</table>
						<p>&nbsp;</p>
					</td>
				</tr>
			</table>
		</html:form>
	<body>
</html:html>