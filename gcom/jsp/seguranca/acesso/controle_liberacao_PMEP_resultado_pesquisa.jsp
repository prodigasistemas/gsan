<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.seguranca.acesso.ControleLiberacaoPermissaoEspecial"%>
<%@ page import="java.util.Collection"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="ResultadoPesquisaControleLiberacaoPMEPActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript">
	
	function validarForm(){
		
		var form = document.forms[0];

		if(validateExibirFiltrarControleLiberacaoPMEPActionForm(form)){
			 
			form.submit();
		}		
		
	}
	
	
	/* Chama Popup */ 
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		
		if(objetoRelacionado.disabled != true){
			
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			} else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				} else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
		}
	}	
	

  	
	function reloadForm(){
  		var form = document.forms[0];
  	
  		form.action='exibirFiltrarControleLiberacaoPMEPAction.do';
	    form.submit();
  	}
  	
  	
</script>

</head>

<body leftmargin="5" topmargin="5" >

<html:form action="/manterControleLiberacaoPMEPAction.do"
	name="ResultadoPesquisaControleLiberacaoPMEPActionForm"
	type="gcom.gui.seguranca.acesso.ResultadoPesquisaControleLiberacaoPMEPActionForm"
	method="post">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

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
			<td width="600" valign="top" class="centercoltext">
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
					<td class="parabg">Filtrar Controle de Liberação de Permissão Especial</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<table width="100%" align="center" bgcolor="#90c7fc" border="0"
					cellpadding="0" cellspacing="0">
					<tr bgcolor="#cbe5fe">
						<td width="100%" align="center">
						<table width="100%" bgcolor="#90c7fc">
							<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">

								<td width="10%">
								<div align="left"><strong>Código</strong></div>
								</td>
								
								<td width="10%">
								<div align="left"><strong>Cód. Func.</strong></div>
								</td>
								
								<td width="30%">
								<div align="left"><strong>Descrição Funcionalidade</strong></div>
								</td>
								
								<td width="10%">
								<div align="left"><strong>Cód. Perm. Espec.</strong></div>
								</td>
								
								<td width="30%">
								<div align="left"><strong>Descrição Permissão Especial</strong></div>
								</td>
								
								<td width="10%">
								<div align="left"><strong>Ind. Uso</strong></div>
								</td>
								
							</tr>
							<pg:pager maxIndexPages="10"
								export="currentPageNumber=pageNumber" index="center"
								maxPageItems="10">
								<pg:param name="pg" />
								<pg:param name="q" />
								<%int cont = 0;%>
								<logic:iterate name="ResultadoPesquisaControleLiberacaoPMEPActionForm" property="controles" 
								               id="controles" type="ControleLiberacaoPermissaoEspecial">
									<pg:item>
										<%cont = cont + 1;
								if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											
											<td width="10%">
											<a href="exibirManterControleLiberacaoPMEPAction.do?idControle=<bean:write name="controles" property="id"/>">
											<div align="left"><bean:write name="controles" property="id"/></div>
											</a>
											</td>
											
											<td width="10%">
											<a href="exibirManterControleLiberacaoPMEPAction.do?idControle=<bean:write name="controles" property="id"/>">
											 <div align="left"><bean:write name="controles" property="funcionalidade.id"/></div>
											 </a>
											</td>
											
											<td width="30%">
											 <a href="exibirManterControleLiberacaoPMEPAction.do?idControle=<bean:write name="controles" property="id"/>">
											 	<div align="left"> ${controles.funcionalidade.descricao} </div>
											 </a>
											</td>
											
											<td width="10%">
											 <a href="exibirManterControleLiberacaoPMEPAction.do?idControle=<bean:write name="controles" property="id"/>">
											 <div align="left"><bean:write name="controles" property="permissaoEspecial.id"/></div>
											 </a>
											</td>
											
											<td width="30%">
											<a href="exibirManterControleLiberacaoPMEPAction.do?idControle=<bean:write name="controles" property="id"/>">
											<div align="left"> ${controles.permissaoEspecial.descricao} </div>
											</a>
											</td>
											
											<td width="10%">
											    <a href="exibirManterControleLiberacaoPMEPAction.do?idControle=<bean:write name="controles" property="id"/>">
												<bean:write name="controles" property="indicadorUso"/>
												</a>
											</td>				
									
										</td>

										</tr>
									</pg:item>
								</logic:iterate>
						</table>
						<table width="100%">
							<tr>
								<td><input name="button" type="button" class="bottonRightCol"
									value="Voltar Pesquisa"
									onclick="window.location.href='<html:rewrite page="/exibirFiltrarControleLiberacaoPMEPAction.do?limpar=S"/>'"
									align="left"></td>

							</tr>
						</table>

						<table width="100%" border="0">
							<tr>
								<td><strong><%@ include file="/jsp/util/indice_pager.jsp"%></strong>
								</td>
							</tr>
						</table>
						</pg:pager>
				</table>
				<p>&nbsp;</p>

			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
<%@ include file="/jsp/util/rodape.jsp"%>	

</body>	

</html:form>
</html:html>
