<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<%@ page import="java.util.Collection,gcom.util.ConstantesSistema"%>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ManterVinculosImoveisRateioConsumoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
<!-- Begin
function verficarSelecao(objeto, tipoObjeto,botao){

       var indice;
       var array = new Array(objeto.length);
       var selecionado = "";
	   var formulario = document.forms[0]; 
  	   
	   for(indice = 0; indice < formulario.elements.length; indice++){
		 if (formulario.elements[indice].type == tipoObjeto && formulario.elements[indice].checked == true) {
			selecionado = formulario.elements[indice].value;
			break;
		 }
	   }

       if (selecionado.length < 1) {
         alert('Nenhum registro selecionado.');
       }else {
			if(botao == 1){
				javascript:abrirPopup('exibirAtualizarTipoRateioPopupAction.do?MatriculaImovel='+selecionado+'&limpar=ok', 250, 600);
			}else if(botao == 2){
				javascript:abrirPopup('exibirEstabelecerVinculoPopupAction.do?MatriculaImovel='+selecionado+'&acao=EXIBIR', 400, 650);		
			}else if(botao == 3){
				javascript:abrirPopup('exibirDesfazerVinculoPopupAction.do?MatriculaImovel='+selecionado, 400, 700);			
			}
		}
   }
-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form action="/exibirManterVinculosImoveisRateioConsumoAction.do"
	name="ManterVinculosImoveisRateioConsumoActionForm"
	type="gcom.gui.micromedicao.ManterVinculosImoveisRateioConsumoActionForm"
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


			<td valign="top" class="centercoltext"><!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Manter V&iacute;nculos para Rateio de Consumo</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<table width="590">
				<tr>
					<td height="23"><strong>Im&oacute;veis:</strong></td>
					<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=imovelManterVinculoParaRateioConsumo', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>								
				</tr>				
			</table>
			<table width="590" bgcolor="#99CCFF">
				<tr bgcolor="#99CCFF">
					<td width="5%" bgcolor="#90c7fc">
					<div align="center"></div>
					</td>
					<td width="10%" bgcolor="#90c7fc"><strong>Matr&iacute;cula</strong></td>
					<td bgcolor="#90c7fc">
					<div align="center"><strong>Inscri&ccedil;&atilde;o</strong></div>
					</td>
					<td bgcolor="#90c7fc">
					<div align="center"><strong>Nome do Cliente Usu&aacute;rio </strong></div>
					</td>
					<td bgcolor="#90c7fc">
					<div align="center"><strong>Endere&ccedil;o</strong></div>
					</td>
				</tr>

				<%String cor = "#cbe5fe";%>

				<!--Fim Tabela Reference a Páginação da Tela de Processo-->
				<%--Esquema de paginação--%>
				<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
					export="currentPageNumber=pageNumber;pageOffset"
					maxPageItems="10" items="${sessionScope.totalRegistros}">
					<pg:param name="pg" />
					<pg:param name="q" />

					<logic:iterate name="colecaoImoveisVinculos" id="clienteimovel">
						<pg:item>
							<%if (cor.equalsIgnoreCase("#cbe5fe")) {
				cor = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
								<%} else {
				cor = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
								<%}%>
								<td>
								<div align="center"><input type="radio" name="idMatriculaImovel"
									value="<bean:define
								name="clienteimovel" property="imovel" id="imovel" /> <bean:write
								name="imovel" property="id" />" /></div>
								</td>
								<td width="20%">
								<div align="center" class="style9"><bean:define
									name="clienteimovel" property="imovel" id="imovel" /> <bean:write
									name="imovel" property="id" /></div>
								</td>
								<td>
								<div align="center" class="style9"><bean:define
									name="clienteimovel" property="imovel" id="imovel" /><%=((gcom.cadastro.imovel.Imovel) imovel)
							.getInscricaoFormatada()%></div>
								</td>
								<td width="34%">
								<div align="left" class="style9"><bean:define
									name="clienteimovel" property="cliente" id="cliente" /> <bean:write
									name="cliente" property="nome" /></div>
								</td>
								<td width="31%">
								<div align="left" class="style9"><bean:define
									name="clienteimovel" property="imovel" id="imovel" /><%=(((gcom.cadastro.imovel.Imovel) imovel)
							.getEnderecoFormatado() != null)?((gcom.cadastro.imovel.Imovel) imovel)
							.getEnderecoFormatado(): ""%></div>
								</td>
							</tr>
						</pg:item>
					</logic:iterate>
			</table>
			<table width="100%" border="0">
				<tr>
					<td align="center"><strong><%@ include
								file="/jsp/util/indice_pager_novo.jsp"%></strong>
					</td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr bordercolor="#90c7fc">
					<td colspan="5">
						<div align="left">
						<input name="Button" type="button"
							class="bottonRightCol" value="Atualizar Tipo Rateio"
							onClick="verficarSelecao(idMatriculaImovel, 'radio',1)"> 
						</div>	
					</td>	
					<td colspan="5">
						<div align="center">
							<input
							name="Button2" type="button" class="bottonRightCol"
							value="Estabelecer V&iacute;nculo"
							onClick="verficarSelecao(idMatriculaImovel, 'radio',2)"> 
						</div>
					</td>
					<td colspan="5">
						<div align="right">	
							<input
							name="Button3" type="button" class="bottonRightCol"
							value="Desfazer V&iacute;nculo"
							onClick="verficarSelecao(idMatriculaImovel, 'radio',3)">
						</div>
					</td>
				</tr>
				<tr bordercolor="#90c7fc">	
					<td colspan="5">
					<div align="left"><input
								name="button" type="button" class="bottonRightCol"
								value="Voltar Filtro"
								onclick="window.location.href='<html:rewrite page="/exibirFiltrarImovelAction.do?redirecionar=ManterVinculoImoveisRateioConsumo"/>'">				
						</div>
					</td>	
				</tr>
			</table>
			</pg:pager> <%-- Fim do esquema de paginação --%></td>
		</tr>

	</table>
	<p>&nbsp;</p>

	<%@ include file="/jsp/util/rodape.jsp"%>


</html:form>
</body>
</html:html>
