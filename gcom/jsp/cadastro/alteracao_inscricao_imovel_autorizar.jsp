<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.cadastro.ImovelInscricaoAlteradaHelper"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<html:javascript staticJavascript="false"  formName="FiltrarAlteracaoInscricaoImovelActionForm"/>

<script>
	function autorizar(form, urlAutorizar) {
	
		var idsQuadra = obterValorCheckboxMarcadoPorNome("indicadorAutorizar");
	
		if (idsQuadra != null && idsQuadra.length > 0){
			urlAutorizar = urlAutorizar + "indicadorAutorizar=" + idsQuadra;
				
		}else{
			alert('Informe a quadra que deverá ser autorizada');
			return false;
		}
		
	
		form.action = urlAutorizar;
  		form.submit();		  
	} 
	
	function verificarSelecionados(){
		var form = document.forms[0];
	
		autorizar(form,'/gsan/autorizarAlteracaoInscricaoImovelAction.do?');
	}

	function selecionarTodosCheckBox(){
	
		var form = document.forms[0];
	
		if (form.selecionarTodos.checked == true){
			for (i=0; i<form.indicadorAutorizar.length; i++){
				form.idsRegistros[i].checked = true;
			}
		} else {
			for (i=0; i<form.indicadorAutorizar.length; i++){
				form.idsRegistros[i].checked = false;
			}
		}
	}
	
	function facilitador(objeto){
		if (objeto.id == "0" || objeto.id == undefined){
			objeto.id = "1";
			marcarTodos();
		} else{
			objeto.id = "0";
			desmarcarTodos();
		}
	}
	
	
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/exibirAutorizarAlteracaoInscricaoImovelAction" method="post">
	

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
			<td width="602" valign="top" class="centercoltext">
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


						<td class="parabg">Atualizar Imoveis</td>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>


			<table width="100%" border="0">

				<tr>
					<td width="35"><strong>Localidade:</strong></td>
					<td colspan="1" width="30">
						<span class="style2"> 
						<html:text property="idLocalidade" 
							size="8" 
							maxlength="7"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"/> 
						</span>
					</td>
					
					<td colspan="1">
						<span class="style2"> 
						<html:text property="desLocalidade" 
							size="45" 
							maxlength="44"
							readonly="true"
							style="background-color:#EFEFEF; 
							border:0; color: #000000"/> 
						</span>
					</td>
				</tr>
				
				<tr>
					<td width="35"><strong>Setor:</strong></td>
					<td colspan="1" width="30"><span class="style2"> <html:text
						property="codigoSetorComercial" 
						size="8" 
						maxlength="7"
						readonly="true"
						style="background-color:#EFEFEF; 
						border:0; color: #000000"/> </span></td>
					<td colspan="1"><span class="style2"> <html:text
						property="desSetorComercial" 
						size="35" 
						maxlength="34" 
						readonly="true"
						style="background-color:#EFEFEF; 
						border:0; color: #000000"/> </span></td>
				</tr>
				
				<tr>
					<td colspan="2" align="left">
						<strong>Imoveis Alterados:</strong>
					</td>
				</tr>		
			</table>
			
			
			<table width="100%" cellpadding="0" cellspacing="0">
				

				
				<tr>
					<td colspan="4" bgcolor="#000000" height="2"></td>
				</tr>
				
				<tr>
					
					<td width="8%" bgcolor="#90c7fc" align="center">
						<strong>
						<a href="javascript:facilitador(this);">Todos</a>
						</strong>
					</td>					
					
					<td bgcolor="#90c7fc" width="10%" align="center">
						<strong>Quadra</strong>
					</td>
					
					<td bgcolor="#90c7fc" width="12%" align="center">
						<strong>Quantidade</strong>
					</td>
					
				</tr>
					
				<tr>
					<td height="100" colspan="6">
					<table width="100%" bgcolor="#99CCFF">
						<logic:present name="colecaoImoveisAgrupadosQuadra">
							<%	int cont = 0;	%>
							<logic:iterate name="colecaoImoveisAgrupadosQuadra" 
								id="imovelInscricaoAlterada" 
								type="ImovelInscricaoAlteradaHelper"
								indexId="i">
								
								<%	cont = cont + 1;
									if (cont % 2 == 0) {	%>
										<tr bgcolor="#cbe5fe">
								<%	} else {	%>
										</tr>
										<tr bgcolor="#FFFFFF"> 
								<%	}	%>								
								
									<td width="8%" align="center">
										<html:checkbox name="imovelInscricaoAlterada" 
											property="indicadorAutorizar" 
											value="<%="" + imovelInscricaoAlterada.getIdQuadra() %>"/>
									</td>
									
									<td width="10%" align="center">
										<bean:write name="imovelInscricaoAlterada" property="numeroQuadra" />
									</td>
									
									<td width="12%" align="center">
										<bean:write name="imovelInscricaoAlterada" property="totalImoveis" />
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
					</table>
					</td>
				</tr>
					
			</table>


			<table width="100%">
				<tr>
					<td valign="top">
						<input name="Submit222" 
							class="bottonRightCol" 
							value="Voltar Filtro" 
							type="button"
							onclick="window.location.href='/gsan/exibirFiltrarAlteracaoInscricaoImovelAction.do';">
					</td>
					
					<td colspan="2" align="right">
						<input class="bottonRightCol" 
							value="Autorizar" 
							type="button"
							onclick="javascript:verificarSelecionados();" 
							url="autorizarAlteracaoInscricaoImovelAction.do"/>
					</td>
				</tr>
			</table>
			
			
			</td>
		</tr>
	</table>

</body>
</html:form>
</html:html>
