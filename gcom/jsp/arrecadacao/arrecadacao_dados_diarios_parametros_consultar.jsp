<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ page import="gcom.util.ConstantesSistema,java.util.Collection"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

	
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
<!-- Begin

function verificarExibicaoRelatorio() {
	
	toggleBox('demodiv',1);
	
}

-->
</script>
</head>
<body leftmargin="5" topmargin="5">
<html:form action="/consultarDadosDiariosArrecadacaoWizardAction" 
	name="FiltrarDadosDiariosArrecadacaoActionForm"
	type="gcom.gui.arrecadacao.FiltrarDadosDiariosArrecadacaoActionForm"
	method="post">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_consulta.jsp?numeroPagina=1" />
	
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<input type="hidden" name="numeroPagina" value="1" />
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
					<tr><td></td></tr>
				</table>
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
						</td>
						<td class="parabg">Consultar Dados Diários</td>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
				<!-- Início do Corpo - Fernanda Paiva -->
				<p>&nbsp;</p>
				<table width="100%" border="0">
					<tr>
						<td width="34%"><strong>Per&iacute;odo da Arrecada&ccedil;&atilde;o:<font color="#FF0000">*</font></strong></td>
			            <td width="66%"> 
							<logic:notEmpty	name="FiltrarDadosDiariosArrecadacaoActionForm" property="periodoArrecadacaoInicio">
								<html:text name="FiltrarDadosDiariosArrecadacaoActionForm" property="periodoArrecadacaoInicio" size="10" maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0" />
			                a 
			                    <html:text name="FiltrarDadosDiariosArrecadacaoActionForm" property="periodoArrecadacaoFim" size="10" maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0" />
							</logic:notEmpty>
		                </td>
		            </tr>
		            <tr> 
						<td>
		                    <strong>Ger&ecirc;ncia Regional: </strong>
		                </td>
		                <td>
		                	<html:text name="FiltrarDadosDiariosArrecadacaoActionForm" property="nomeGerenciaRegional" size="30" readonly="true" style="background-color:#EFEFEF; border:0; text-align: left;"/>
		                </td>
		            </tr>
		            <tr> 
	                  	<td><strong> Localidade Pólo:</strong></td>
	                    <td> 
						    <html:text name="FiltrarDadosDiariosArrecadacaoActionForm" property="nomeElo" size="30" readonly="true" style="background-color:#EFEFEF; border:0; text-align: left;"/>
   	                    </td>
		            </tr>
		            <tr> 
		                  <td><strong>Localidade:</strong></td>
		                  <td> 
		                     <html:text name="FiltrarDadosDiariosArrecadacaoActionForm" property="descricaoLocalidade" size="30" readonly="true" style="background-color:#EFEFEF; border:0; text-align: left;"/>
						  </td>
		            </tr>
		            <tr> 
		                  <td>
							<strong>Arrecadador:</strong>
		                  </td>
		                  <td> 
			                  <html:text name="FiltrarDadosDiariosArrecadacaoActionForm" property="nomeArrecadador" size="30" readonly="true" style="background-color:#EFEFEF; border:0; text-align: left;"/>
						  </td>
		            </tr>
		            <%-- <tr> 
		                <td><strong> Perfil do Im&oacute;vel:</strong></td>
		                <td align="left">
			                <logic:present name="colecaoImoveisPerfil">
				                <logic:iterate name="colecaoImoveisPerfil" id="imovelPerfil">
			                   		<html:text name="imovelPerfil" property="descricao" size="30" readonly="true" style="background-color:#EFEFEF; border:0; text-align: left;"/><br>
			                    </logic:iterate>
		                   	</logic:present>
		                    <logic:empty name="colecaoImoveisPerfil">
		                   		<input type="text" name="imovelPerfil" value="" size="30" readonly="true" style="background-color:#EFEFEF; border:0;"/>
		                   	</logic:empty>
	                    </td>
		            </tr>--%>
		            <tr>
			      		<td HEIGHT="30"><strong>Perfil do Imóvel:</strong></td>
			        	<td>
							<html:select property="perfilImovel" style="width: 200px;" tabindex="9">
								<html:options collection="colecaoImovelPerfilResultado" labelProperty="descricao" property="id"/>
							</html:select>
						</td>
			      </tr>
		          <tr>
			      	<td HEIGHT="30"><strong>Ligação de Água:</strong></td>
			        <td>
						<html:select property="situacaoLigacaoAgua" style="width: 200px;" tabindex="11">
							<html:options collection="colecaoLigacaoAguaSituacaoResultado" labelProperty="descricao" property="id"/>
						</html:select>
					</td>
			      </tr>
			      <tr> 
		           <td HEIGHT="30"><strong> Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o 
		                    de Esgoto:</strong></td>
		                  <td align="left">
						<html:select property="situacaoLigacaoEsgoto" style="width: 200px;" tabindex="11">
							<html:options collection="colecaoLigacaoEsgotoSituacaoResultado" labelProperty="descricao" property="id"/>
						</html:select>
		                  </td>
		            </tr>
		
		            <tr> 
		                  <td HEIGHT="30"><strong> Categoria:</strong></td>
		                  <td align="left">
							<html:select property="categoria2" style="width: 200px;" tabindex="11">
								<html:options collection="colecaoCategoriaResultado" labelProperty="descricao" property="id"/>
							</html:select>
		                  </td>
		                 </td>
		            </tr>
		            <tr> 
		                  <td HEIGHT="30"><strong> Esfera de Poder:</strong></td>
		
		                  <td align="left">
						  <html:select property="esferaPoder2" style="width: 200px;" tabindex="11">
								<html:options collection="colecaoEsferaPoderResultado" labelProperty="descricao" property="id"/>
							</html:select>
		                  </td>
		            </tr>
		            <tr> 
		                  <td HEIGHT="30"><strong> Tipo do Documento:</strong></td>
		                  <td align="left">
						  <html:select property="tipoDocumento2" style="width: 200px;" tabindex="11">
								<html:options collection="colecaoDocumentoTipoResultado" labelProperty="descricaoDocumentoTipo" property="id"/>
							</html:select>
		                  </td>
		            </tr>
		        </table>
		        <table width="100%" border="0">
					<tr>
						<td align="right">
							  <div align="right">
							   <a href="javascript:verificarExibicaoRelatorio();">
								<img border="0"
									src="<bean:message key="caminho.imagens"/>print.gif"
									title="Imprimir" /> </a>
							  </div>
						</td>
					</tr>
				</table>
				  <p>&nbsp;</p>
	              <table width="100%" border="0">
	                <tr>
						<td colspan="2">
							<div align="right"><jsp:include
							page="/jsp/util/wizard/navegacao_botoes_wizard_consulta.jsp?numeroPagina=1" /></div>
						</td>
					</tr>
	              </table>
              
				<!-- Fim do Corpo - Fernanda Paiva  15/05/2006  -->
				<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioDadosDiariosArrecadacaoParametrosAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>