<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.util.ConstantesSistema,java.util.Collection"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
</head>

<body leftmargin="5" topmargin="5">
<html:form action="/exibirConsultarMovimentacaoHidrometroAction.do"
	name="HidrometroActionForm"
	type="gcom.gui.micromedicao.hidrometro.HidrometroActionForm"
	method="post" onsubmit="return validateHidrometroActionForm(this);" >


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
		<td width="615" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
	
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="/gsan/imagens/parahead_left.gif" /></td>
					<td class="parabg">Consultar Movimentação de Hidrômetro</td>
					<td width="11"><img border="0"
						src="/gsan/imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
			<tr>
			
			</tr>
			</table>
			<logic:present name="colecaoMovimentacaoHidrometro">
			<logic:iterate name="colecaoMovimentacaoHidrometro" id="hidrometroMovimentacao">
			<table width="100%" border="0">
				<tr>
					<td>Dados da Movimentação:</td>
					<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=hidrometroMovimentacaoConsultar-3-dados', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
				</tr>
				<tr bgcolor="#cbe5fe">
					<td width="100%" align="center">
						<table width="100%" border="0">
							<tr>
								<td height="10" width="35%"><strong>Data Movimenta&ccedil;&atilde;o:</strong></td>
								<td align="left"><input type="text" size="12" maxlength="12"
									readonly="true" style="background-color:#EFEFEF; border:0"
									value="<bean:write name="hidrometroMovimentacao"  property="data" formatKey="date.format" />"></td>
							</tr>
							<tr>
								<td height="10"><strong>Hora Movimenta&ccedil;&atilde;o:</strong></td>
								<td align="left"><input type="text" size="12" maxlength="12"
									readonly="true" style="background-color:#EFEFEF; border:0"
									value="<bean:write name="hidrometroMovimentacao"  property="hora" formatKey="hour.format" />"></td>
							</tr>
							<tr>
								<td height="10"><strong>Motivo Movimenta&ccedil;&atilde;o:</strong></td>
								<td>
									<logic:empty name="hidrometroMovimentacao" property="hidrometroMotivoMovimentacao">
										<input name="hidrometroMovimentacao" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:empty>
									<logic:notEmpty name="hidrometroMovimentacao" property="hidrometroMotivoMovimentacao">
										<html:text name="hidrometroMovimentacao" property="hidrometroMotivoMovimentacao.descricao" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:notEmpty>	
								</td>
							</tr>
							<tr>
								<td height="10"><strong>Parecer:</strong></td>
								<td><html:text name="hidrometroMovimentacao" property="parecer"
									size="60" maxlength="60" readonly="true"
									style="background-color:#EFEFEF; border:0" /></td>
							</tr>
							<tr>
								<td height="10"><strong>Local de Armazenagem Origem:</strong></td>
								<td>
									<logic:empty name="hidrometroMovimentacao" property="hidrometroLocalArmazenagemOrigem">
										<input name="hidrometroMovimentacao" size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:empty>
									<logic:notEmpty name="hidrometroMovimentacao" property="hidrometroLocalArmazenagemOrigem">
										<html:text name="hidrometroMovimentacao" property="hidrometroLocalArmazenagemOrigem.descricao"	size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:notEmpty>
								</td>
							<tr>
								<td height="10"><strong>Local de Armazenagem Destino:</strong></td>
								<td>
									<logic:empty name="hidrometroMovimentacao" property="hidrometroLocalArmazenagemDestino">
										<input name="hidrometroMovimentacao" size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:empty>
									<logic:notEmpty name="hidrometroMovimentacao" property="hidrometroLocalArmazenagemDestino">
										<html:text name="hidrometroMovimentacao" property="hidrometroLocalArmazenagemDestino.descricao"	size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:notEmpty>	
								</td>
							</tr>
							<tr>
								<td height="10"><strong>Usu&aacute;rio:</strong></td>
								<td>
									<logic:empty name="hidrometroMovimentacao" property="usuario">
										<input name="hidrometroMovimentacao" size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:empty>
									<logic:notEmpty name="hidrometroMovimentacao" property="usuario">
										<html:text name="hidrometroMovimentacao" property="usuario.nomeUsuario" size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:notEmpty>
								</td>
							</tr>
							<tr>
								<td height="10"><strong>Total de Hidrômetros:</strong></td>
								<td align="left"><input type="text" size="8" maxlength="8"
								readonly="true" style="background-color:#EFEFEF; border:0; text-align: right;"
								value="<bean:write name="hidrometroMovimentacao"  property="quantidade" formatKey="hour.format" />"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			</logic:iterate>
			</logic:present>
			<table width="100%" border="0" bgcolor="#90c7fc">
				<%if (((Collection) session.getAttribute("colecaoObterHidrometro")).size() <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PARA_SCROLL) {%>
				<tr  bordercolor="#000000">
		       	   <td width="20%" align="center"><strong>&nbsp; N&uacute;mero</strong></td>
		           <td width="13%" align="center"><strong>&nbsp; Data de &nbsp;Aquisi&ccedil;&atilde;o</strong></td>
	    	       <td width="13%" align="center"><strong>&nbsp; Ano de &nbsp;Fabrica&ccedil;&atilde;o</strong></td>
	        	   <td width="13%" align="center"><strong>&nbsp; Marca</strong></td>
		           <td width="13%" align="center"><strong>&nbsp; Capacidade</strong></td>
	    	       <td width="20%" align="center"><strong>&nbsp; Situa&ccedil;&atilde;o</strong></td>
	        	</tr>
              	   <logic:present name="colecaoObterHidrometro">
                   <%int cont=0;%>
                   <logic:iterate name="colecaoObterHidrometro" id="hidrometro">
                      <%
                       cont = cont+1;
                       if (cont%2==0){%>
                         <tr bgcolor="#cbe5fe">
                       <%}else{ %>
                          <tr bgcolor="#FFFFFF">
                    <%}%>
                   	<td width="20%" align="center">
                       	<bean:write name="hidrometro" property="numero"/>
                       </td>
                       <td width="13%" align="center">
                       	<bean:write name="hidrometro" property="dataAquisicao" formatKey="date.format"/>
                       </td>
                       <td width="13%" align="center">
                        <bean:write name="hidrometro" property="anoFabricacao"/>
                       </td>
                       <td width="13%">
   	                    <bean:write name="hidrometro" property="hidrometroMarca.descricaoAbreviada"/>
                       </td>
					<td width="13%">
                       	<bean:write name="hidrometro" property="hidrometroCapacidade.descricaoAbreviada"/>
                       </td>
                       <td width="20%">
                          <bean:write name="hidrometro" property="hidrometroSituacao.descricao"/>
                       </td>
                   </tr>
                   </logic:iterate>
                  	</logic:present>
	          <%} else {%>
	          <tr bordercolor="#90c7fc">
		       	   <td width="20%" align="center"><strong>&nbsp; N&uacute;mero</strong></td>
		           <td width="12%" align="center"><strong>&nbsp; Data de &nbsp;Aquisi&ccedil;&atilde;o</strong></td>
	    	       <td width="13%" align="center"><strong>&nbsp; Ano de &nbsp;Fabrica&ccedil;&atilde;o</strong></td>
	        	   <td width="12%" align="center"><strong>&nbsp; Marca</strong></td>
		           <td width="13%" align="center"><strong>&nbsp; Capacidade</strong></td>
	    	       <td width="22%" align="center"><strong>&nbsp; Situa&ccedil;&atilde;o</strong></td>
	        	</tr>
		        <tr bordercolor="#90c7fc">
	    	        <td height="100" colspan="6">
						<div style="width: 100%; height: 100%; overflow: auto;">
						<table width="100%" bgcolor="#99CCFF">
	    	                <logic:present name="colecaoObterHidrometro">
	        	            <%int cont=0;%>
	            	        <logic:iterate name="colecaoObterHidrometro" id="hidrometro">
	                       	<%
	                        cont = cont+1;
	                        if (cont%2==0){%>
	                          <tr bgcolor="#cbe5fe">
	                        <%}else{ %>
	                           <tr bgcolor="#FFFFFF">
	                    	<%}%>
		                    	<td width="20%">
		                        	<bean:write name="hidrometro" property="numero"/>
		                        </td>
		                        <td width="13%">
		                        	<bean:write name="hidrometro" property="dataAquisicao" formatKey="date.format"/>
		                        </td>
		                        <td width="13%">
			                        <bean:write name="hidrometro" property="anoFabricacao"/>
		                        </td>
		                        <td width="13%">
		    	                    <bean:write name="hidrometro" property="hidrometroMarca.descricaoAbreviada"/>
		                        </td>
								<td width="13%">
		                        	<bean:write name="hidrometro" property="hidrometroCapacidade.descricaoAbreviada"/>
		                        </td>
		                        <td width="20%">
		                           <bean:write name="hidrometro" property="hidrometroSituacao.descricao"/>
		                        </td>
		                    </tr>
	                    	</logic:iterate>
	                   		</logic:present>
	                 	</table>
	                 </div>
	              </td>
	           </tr>
	           <%} %>
	        </table>
	        <p>&nbsp;</p>
	        <table width="100%" border="0">
				<tr>
					<td align="left">
						<input type="button" name="Voltar" class="bottonRightCol" value="Voltar"
							onClick="javascript:window.history.back();">
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
