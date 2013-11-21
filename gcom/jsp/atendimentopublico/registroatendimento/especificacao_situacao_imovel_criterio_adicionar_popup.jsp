<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<%-- Carrega javascripts da biblioteca --%>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<%-- Novos Javascripts --%>

<script language="JavaScript">

	/* Valida Form */
	function validaForm() {
		
		if(validaCombo()){
			var form = document.InserirEspecificacaoSituacaoImovelActionForm;
			var lista = new Array();
			
	    	lista[0] = form.situacaoLigacaoAgua.value;
	    	lista[1] = form.indicadorHidrometroLigacaoAgua.value;
	    	lista[2] = form.situacaoLigacaoEsgoto.value;
	    	lista[3] = form.indicadorHidrometroPoco.value;
	
			window.opener.recuperarDadosPopup('', lista, 'especificaoSituacaoCriterio');
			window.close();
		}
	}

	function validaCombo(){
		var form = document.InserirEspecificacaoSituacaoImovelActionForm;
		
		if(	form.situacaoLigacaoAgua.selectedIndex == 0 &&
			form.situacaoLigacaoEsgoto.selectedIndex == 0 &&
			form.indicadorHidrometroLigacaoAgua.selectedIndex == 0 &&
			form.indicadorHidrometroPoco.selectedIndex == 0 ){
			
			alert('Informe pelo menos um campo');
			return false;
		
		}
		return true;
	}
	
	
	/* Limpa Form */	 
	function limparForm() {

		var form = document.InserirEspecificacaoSituacaoImovelActionForm;

		form.situacaoLigacaoAgua.selectedIndex = 0;
		form.indicadorHidrometroLigacaoAgua.selectedIndex = 0;
		form.situacaoLigacaoEsgoto.selectedIndex = 0;
		form.indicadorHidrometroPoco.selectedIndex = 0;

	}

   	/* Fecha Popup */
   	function fechar() {
   		limparForm();
   		window.close();
   	}
</script>
</head>

<body leftmargin="0" topmargin="0">	
<html:form action="/inserirEspecificacaoSituacaoImovelAction.do"
	name="InserirEspecificacaoSituacaoImovelActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.InserirEspecificacaoSituacaoImovelActionForm"
	method="post">
	
	<table width="570" border="0" cellpadding="0" cellspacing="5">
		<tr> 
	    	<td width="560" valign="top" class="centercoltext">
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
	          			<td class="parabg">Adicionar Crit&eacute;rios da Especifica&ccedil;&atilde;o
            			da Situa&ccedil;&atilde;o do Im&oacute;vel</td>
	          			<td width="11">
	          				<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
	          			</td>
	        		</tr>
	      		</table>
	      		<p>&nbsp;</p>
	      		<table width="100%" border="0">
	        		<tr> 
	          			<td colspan="5">Preencha os campos para inserir um crit&eacute;rio de 
            				especifica&ccedil;&atilde;o:</td>
	        		</tr>

					<tr>
						<td>
							<strong> Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o
           	 						da &Aacute;gua:
							</strong>
						</td>

						<td colspan="2">
							<strong> 
							<html:select property="situacaoLigacaoAgua" style="width: 230px;">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
								</html:option>
						
								<logic:present name="colecaoSituacaoLigacaoAgua"
									scope="session">
									<html:options collection="colecaoSituacaoLigacaoAgua"
									labelProperty="descricao" property="id" />
								</logic:present>
							</html:select> 														
							</strong>
						</td>
					</tr>

					<tr>
						<td>
							<strong> Exist&ecirc;ncia do Hidr&ocirc;metro
            						na Liga&ccedil;&atilde;o de &Aacute;gua:
							</strong>
						</td>

						<td>
							<strong> 
							<html:select property="indicadorHidrometroLigacaoAgua" style="width: 60px;">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
								</html:option>
								<html:option
									value="<%=""+ConstantesSistema.SIM%>">SIM
								</html:option>
								<html:option
									value="<%=""+ConstantesSistema.NAO%>">N&Atilde;O
								</html:option>
						
							</html:select> 														
							</strong>
						</td>
					</tr>

					<tr>
						<td>
							<strong> Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o
            						do Esgoto:
							</strong>
						</td>

						<td colspan="2">
							<strong> 
							<html:select property="situacaoLigacaoEsgoto" style="width: 230px;">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
								</html:option>
						
								<logic:present name="colecaoSituacaoLigacaoEsgoto"
									scope="session">
									<html:options collection="colecaoSituacaoLigacaoEsgoto"
									labelProperty="descricao" property="id" />
								</logic:present>
							</html:select> 														
							</strong>
						</td>
					</tr>

					<tr>
						<td>
							<strong> Exist&ecirc;ncia do Hidr&ocirc;metro no Po&ccedil;o:
							</strong>
						</td>

						<td colspan="2">
							<strong> 
							<html:select property="indicadorHidrometroPoco" style="width: 60px;">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
								</html:option>
								<html:option
									value="<%=""+ConstantesSistema.SIM%>">SIM
								</html:option>
								<html:option
									value="<%=""+ConstantesSistema.NAO%>">N&Atilde;O
								</html:option>
						
							</html:select> 														
							</strong>
						</td>
					</tr>

	        		<tr> 
	          			<td height="27" colspan="5"> 
	          				<div align="right"> 
	              				<input name="Button" type="button" class="bottonRightCol" value="Inserir" onclick="javascript:validaForm();">
	              				<input name="Button2" type="button" class="bottonRightCol" value="Fechar" onClick="javascript:fechar();">
	            			</div>
	            		</td>
	        		</tr>
	      		</table>
	      		<p>&nbsp;</p>
	      	</td>
	  	</tr>
	</table>
</html:form>
</body>
</html:html>