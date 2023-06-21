<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="FaturamentoSeletivoActionForm" dynamicJavascript="true" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
<!-- 

-->
</script>
</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">
<html:form action="/dadosRecadastramentoAguaParaAction.do" enctype="multipart/form-data" name="DadosRecadastramentoAguaParaActionForm" type="gcom.gui.cadastro.DadosRecadastramentoAguaParaActionForm" method="post">

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
			
			<td width="615" valign="top" class="centercoltext">
				<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
				<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
					<tr>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
						</td>
						<td class="parabg">Dados Recadastramento Água Pará</td>
						<td width="11" valign="top">
							<img border="0" src="imagens/parahead_right.gif" />
						</td>
					</tr>
					</table>
			<p>&nbsp;</p>
			<table width="100%" cellspacing="0">
                                    <tr>

										<td bgcolor="#90c7fc" align="center" width="20%">
											<div align="center">
												<strong>Matricula Imovel</strong>
											</div>
										</td>

										<td bgcolor="#90c7fc" width="60%">
											<div align="center">
												<strong>Nome Cliente</strong>
											</div>
										</td>
										
										<td bgcolor="#90c7fc" width="20%">
											<div align="center">
												<strong>RG</strong>
											</div>
										</td>

									</tr>
							<tr bordercolor="#000000">

								<logic:present
											name="consultarRecadastramentoAguaParaHelper">
									<tr bgcolor="#FFFFFF">
										<td align="center">${consultarRecadastramentoAguaParaHelper.idImovel}</td>
										<td align="center">${consultarRecadastramentoAguaParaHelper.nome}</td>
										<td align="center">${consultarRecadastramentoAguaParaHelper.rg}</td>
									</tr>									
    							</logic:present>
							</tr>
							
							   <tr>

										<td bgcolor="#90c7fc" align="center" width="20%">
											<div align="center">
												<strong>CPF</strong>
											</div>
										</td>

										<td bgcolor="#90c7fc" width="60%">
											<div align="center">
												<strong>Numero Nis</strong>
											</div>
										</td>
										
										<td bgcolor="#90c7fc" width="20%">
											<div align="center">
												<strong>Telefone</strong>
											</div>
										</td>

									</tr>
							<tr bordercolor="#000000">

								<logic:present
											name="consultarRecadastramentoAguaParaHelper">
									<tr bgcolor="#FFFFFF">
										<td align="center">${consultarRecadastramentoAguaParaHelper.cpf}</td>
										<td align="center">${consultarRecadastramentoAguaParaHelper.numeroNIS}</td>
										<td align="center">${consultarRecadastramentoAguaParaHelper.telefone}</td>
									</tr>									
    							</logic:present>
							</tr>
					<tr>
						<td colspan="2">
							<table bgcolor="#90c7fc" border=0>
								<tr bordercolor="#90c7fc" bgcolor="#90c7fc">
									<td align="center"><strong>Download</strong></td>
								</tr>
								<%int i = 1;%>
								<logic:iterate name="colecaoImagens" id="item">
									<tr>
										<%i = i + 1;
										if (i % 2 == 0) {%>
											<tr bgcolor="#FFFFFF" colspan="2">
										<%} else {%>
											<tr bgcolor="#cbe5fe" colspan="2">
										<%}%>
										<td align="center">
											<a href="javascript:abrirPopup('exibirImagemRecadastramentoAguaParaAction.do?path=${item.path}', 400, 800);">
												<img width="18" height="18" src="<bean:message key="caminho.imagens"/>imgfolder.gif" border="0" />
											</a> 
										</td>
									</tr>
								</logic:iterate>
							</table>
						</td>						
					</tr>	 				
					</table>
			<table width="100%" border="0">
				<tr>
				<td height="24">
					<div align="right">
					<logic:equal name="jaAnalisado" value="false">
					<input name="Aprovar" type="button" class="bottonRightCol"
						value="Aprovar" onClick="window.location.href='/gsan/dadosRecadastramentoAguaParaAction.do?cpf=${consultarRecadastramentoAguaParaHelper.cpf}&action=aprovar'">
					<input name="Negar" type="button" class="bottonRightCol"
						value="Negar" onClick="window.location.href='/gsan/dadosRecadastramentoAguaParaAction.do?cpf=${consultarRecadastramentoAguaParaHelper.cpf}&action=negar'">					
				  </logic:equal>
					<input name="Button" type="button" class="bottonRightCol"
						value="Voltar" onClick="window.location.href='/gsan/filtrarRecadastramentoAguaParaAction.do'">
					</div>
					</td>
				</tr>
			</table>
	</td>
				<p>&nbsp;</p>
			
			
			
			<p>&nbsp;</p>
		</tr>
		<%@ include file="/jsp/util/rodape.jsp"%>
	</table>
	<p>&nbsp;</p>
</html:form>
</body>
</html:html>

