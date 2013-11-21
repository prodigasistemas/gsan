<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.Util, gcom.cobranca.CobrancaAcaoAtividadeComando"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript">
<!-- Begin -->
	
</script>

</head>

<body leftmargin="0" topmargin="0">

<form method="post">

<table width="764" border="0" cellpadding="0" cellspacing="5">
	<tr>
		<td width="754" valign="top" class="centercoltext">
		<table height="100%">
			<tr>
				<td></td>
			</tr>
		</table>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img src="imagens/parahead_left.gif"
					editor="Webstyle4"
					moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws"
					border="0" /></td>
				<td class="parabg">Consulta<strong> </strong><font size="-1">Par&acirc;metros
				do Comando da A&ccedil;&atilde;o de Cobran&ccedil;a</font></td>
				<td width="11"><img src="imagens/parahead_right.gif"
					editor="Webstyle4"
					moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws"
					border="0" /></td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<table width="100%" border="0">
			<logic:iterate name="colecaoItemCobrancaAcaoAtividadeComando"
				id="cobrancaAcaoAtividadeComando" type="CobrancaAcaoAtividadeComando">

				<tr>
					<td width="32%" class="style1"><strong>A&ccedil;&atilde;o de
					Cobran&ccedil;a:</strong><strong></strong></td>
					<td width="68" colspan="3" class="style1"><logic:present
						name="cobrancaAcaoAtividadeComando" property="cobrancaAcao">

						<input name="inscricaoImovel2" type="text" disabled
							style="background-color:#EFEFEF; border:0" size="40"
							maxlength="40"
							value="<bean:define name="cobrancaAcaoAtividadeComando"
						property="cobrancaAcao" id="cobrancaAcao" />
						<bean:write name="cobrancaAcao"
						property="descricaoCobrancaAcao" />">
					</logic:present>
					<logic:notPresent name="cobrancaAcaoAtividadeComando" property="cobrancaAcao">
					<input name="inscricaoImovel2" type="text" disabled
							style="background-color:#EFEFEF; border:0" size="40"
							maxlength="40"
							value="">
					</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="style1">
					<hr>
					</td>
				</tr>

				<tr>
					<td class="style1"><strong>Grupo de Cobran&ccedil;a:</strong></td>
					<td colspan="3" class="style1">
						<logic:present name="cobrancaAcaoAtividadeComando"
						property="cobrancaGrupo">					<input name="inscricaoImovel22"
						type="text" disabled style="background-color:#EFEFEF; border:0"
						size="25" maxlength="25" value="<bean:define name="cobrancaAcaoAtividadeComando"
						property="cobrancaGrupo" id="cobrancaGrupo" />
						<bean:write name="cobrancaGrupo"
						property="descricao" />"></logic:present>
						<logic:notPresent name="cobrancaAcaoAtividadeComando"
						property="cobrancaGrupo">					<input name="inscricaoImovel22"
						type="text" disabled style="background-color:#EFEFEF; border:0"
						size="25" maxlength="25" value=""></logic:notPresent>
				</td>
				</tr>
				<tr>
					<td class="style1"><strong>Ger&ecirc;ncia Regional:</strong></td>
					<td colspan="3" class="style1">
					
						<logic:present name="cobrancaAcaoAtividadeComando" property="gerenciaRegional">					
							<input name="inscricaoImovel223"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="25" maxlength="25" value="<bean:define name="cobrancaAcaoAtividadeComando"
						property="gerenciaRegional" id="gerenciaRegional" />
						<bean:write name="gerenciaRegional"
						property="nome" />">						
						</logic:present>
						<logic:notPresent name="cobrancaAcaoAtividadeComando" property="gerenciaRegional">					
							<input name="inscricaoImovel223"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="25" maxlength="25" value="">						
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td class="style1"><strong>Localidade:</strong></td>
					<td colspan="3" class="style1">
					
						<logic:present name="cobrancaAcaoAtividadeComando" property="localidadeInicial">					
							<input name="inscricaoImovel2232"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="30" maxlength="30" value="<bean:define name="cobrancaAcaoAtividadeComando"
						property="localidadeInicial" id="localidadeInicial" />
						<bean:write name="localidadeInicial"
						property="descricao" />">						
						</logic:present>
						<logic:notPresent name="cobrancaAcaoAtividadeComando" property="localidadeInicial">					
							<input name="inscricaoImovel2232"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="30" maxlength="30" value="">						
						</logic:notPresent>
					
						<strong>a</strong>

						<logic:present name="cobrancaAcaoAtividadeComando" property="localidadeFinal">					
							<input name="inscricaoImovel22323"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="30" maxlength="30" value="<bean:define name="cobrancaAcaoAtividadeComando"
						property="localidadeFinal" id="localidadeFinal" />
						<bean:write name="localidadeFinal"
						property="descricao" />">						
						</logic:present>
						<logic:notPresent name="cobrancaAcaoAtividadeComando" property="localidadeFinal">					
							<input name="inscricaoImovel22323"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="30" maxlength="30" value="">						
						</logic:notPresent>
						</td>
				</tr>
				<tr>
					<td class="style1"><strong>Setor Comercial:</strong></td>
					<td colspan="3" class="style1">
					
						<logic:present name="cobrancaAcaoAtividadeComando" property="codigoSetorComercialInicial">					
							<input name="inscricaoImovel22322"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="30" maxlength="30" value="
						<bean:write name="cobrancaAcaoAtividadeComando"
						property="codigoSetorComercialInicial" />">						
						</logic:present>
						<logic:notPresent name="cobrancaAcaoAtividadeComando" property="codigoSetorComercialInicial">					
							<input name="inscricaoImovel22322"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="30" maxlength="30" value="">						
						</logic:notPresent>
					
						<strong>a</strong>

						<logic:present name="cobrancaAcaoAtividadeComando" property="codigoSetorComercialFinal">					
							<input name="inscricaoImovel223232"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="30" maxlength="30" value="
						<bean:write name="cobrancaAcaoAtividadeComando"
						property="codigoSetorComercialFinal" />">						
						</logic:present>
						<logic:notPresent name="cobrancaAcaoAtividadeComando" property="codigoSetorComercialFinal">					
							<input name="inscricaoImovel223232"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="30" maxlength="30" value="">						
						</logic:notPresent>
				</tr>
				<tr>
					<td class="style1"><strong>Rota:</strong></td>
					<td colspan="3" class="style1">
						<logic:present name="cobrancaAcaoAtividadeComando" property="rotaInicial">					
							<input name="inscricaoImovel22322"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="9" maxlength="9" value="
						<bean:define name="cobrancaAcaoAtividadeComando"
						property="rotaInicial" id="rotaInicial" />
						<bean:write name="rotaInicial"
						property="id" />">						
						</logic:present>
						<logic:notPresent name="cobrancaAcaoAtividadeComando" property="rotaInicial">					
							<input name="inscricaoImovel22322"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="9" maxlength="9" value="">						
						</logic:notPresent>
						<strong>a</strong>
						<logic:present name="cobrancaAcaoAtividadeComando" property="rotaFinal">					
							<input name="inscricaoImovel223232"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="9" maxlength="9" value="
						<bean:define name="cobrancaAcaoAtividadeComando"
						property="rotaFinal" id="rotaFinal" />
						<bean:write name="rotaFinal"
						property="id" />">						
						</logic:present>
						<logic:notPresent name="cobrancaAcaoAtividadeComando" property="rotaFinal">					
							<input name="inscricaoImovel223232"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="9" maxlength="9" value="">						
						</logic:notPresent>
				</tr>
				<tr>
					<td colspan="4" class="style1">
					<hr>
					</td>
				</tr>
				<tr>
					<td class="style1"><strong>Cliente:</strong></td>
					<td colspan="3" class="style1">
						<logic:present name="cobrancaAcaoAtividadeComando" property="cliente">					
							<input name="inscricaoImovel22343"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="9" maxlength="9" value="
						<bean:define name="cobrancaAcaoAtividadeComando"
						property="cliente" id="cliente" />
						<bean:write name="cliente"
						property="id" />">						
						</logic:present>
						<logic:notPresent name="cobrancaAcaoAtividadeComando" property="cliente">					
							<input name="inscricaoImovel22343"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="9" maxlength="9" value="">						
						</logic:notPresent>
						<logic:present name="cobrancaAcaoAtividadeComando" property="cliente">					
							<input name="inscricaoImovel223222"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="50" maxlength="50" value="
						<bean:define name="cobrancaAcaoAtividadeComando"
						property="cliente" id="cliente" />
						<bean:write name="cliente"
						property="nome" />">						
						</logic:present>
						<logic:notPresent name="cobrancaAcaoAtividadeComando" property="cliente">					
							<input name="inscricaoImovel223222"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="50" maxlength="50" value="">						
						</logic:notPresent>
						</td>
				</tr>
				<tr>
					<td class="style1"><strong>Tipo da Rela&ccedil;&atilde;o com o
					Cliente:</strong></td>
					<td colspan="3" class="style1">
					<logic:present name="cobrancaAcaoAtividadeComando" property="clienteRelacaoTipo">					
							<input name="inscricaoImovel2232222"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="30" maxlength="30" value="
						<bean:define name="cobrancaAcaoAtividadeComando"
						property="clienteRelacaoTipo" id="clienteRelacaoTipo" />
						<bean:write name="clienteRelacaoTipo"
						property="descricao" />">						
						</logic:present>
						<logic:notPresent name="cobrancaAcaoAtividadeComando" property="clienteRelacaoTipo">					
							<input name="inscricaoImovel2232222"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="30" maxlength="30" value="">						
						</logic:notPresent>
				</tr>
				<tr>
					<td colspan="4" class="style1">
					<hr>
					</td>
				</tr>
				<tr>
					<td class="style1"><strong>Refer&ecirc;ncia das Contas:</strong></td>
					<td colspan="3" class="style1">
						<logic:present name="cobrancaAcaoAtividadeComando" property="anoMesReferenciaContaInicial">					
							<input name="inscricaoImovel22343"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="7" maxlength="7" value="
							<%="" + Util.formatarAnoMesParaMesAno(cobrancaAcaoAtividadeComando.getAnoMesReferenciaContaInicial())%>">												
						</logic:present>
						<logic:notPresent name="cobrancaAcaoAtividadeComando" property="anoMesReferenciaContaInicial">					
							<input name="inscricaoImovel22343"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="7" maxlength="7" value="">						
						</logic:notPresent>
						<strong>a</strong>
						<logic:present name="cobrancaAcaoAtividadeComando" property="anoMesReferenciaContaFinal">					
							<input name="inscricaoImovel223432"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="7" maxlength="7" value="
							<%="" + Util.formatarAnoMesParaMesAno(cobrancaAcaoAtividadeComando.getAnoMesReferenciaContaFinal())%>">						
						</logic:present>
						<logic:notPresent name="cobrancaAcaoAtividadeComando" property="anoMesReferenciaContaFinal">					
							<input name="inscricaoImovel223432"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="7" maxlength="7" value="">						
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td class="style1"><strong>Vencimento das Contas:</strong></td>
					<td colspan="3" class="style1">
						<logic:present name="cobrancaAcaoAtividadeComando" property="dataVencimentoContaInicial">					
							<input name="inscricaoImovel2232222"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="10" maxlength="10" value="
						<bean:write name="cobrancaAcaoAtividadeComando" format="dd/MM/yyyy"
						property="dataVencimentoContaInicial" />">						
						</logic:present>
						<logic:notPresent name="cobrancaAcaoAtividadeComando" property="dataVencimentoContaInicial">					
							<input name="inscricaoImovel2232222"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="10" maxlength="10" value="">						
						</logic:notPresent>
						<strong>a</strong>
						<logic:present name="cobrancaAcaoAtividadeComando" property="dataVencimentoContaFinal">					
							<input name="inscricaoImovel22322222"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="10" maxlength="10" value="
						<bean:write name="cobrancaAcaoAtividadeComando" format="dd/MM/yyyy"
						property="dataVencimentoContaFinal" />">						
						</logic:present>
						<logic:notPresent name="cobrancaAcaoAtividadeComando" property="dataVencimentoContaFinal">					
							<input name="inscricaoImovel22322222"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="10" maxlength="10" value="">						
						</logic:notPresent>
						</td>
				</tr>
				<tr>
					<td colspan="4" class="style1">
					<hr>
					</td>
				</tr>
				<tr>
					<td class="style1"><strong>Quantidade de Documentos:</strong></td>
					<td colspan="3" class="style1">
					
						<logic:present name="cobrancaAcaoAtividadeComando" property="quantidadeDocumentos">					
							<input name="inscricaoImovel22343"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="9" maxlength="9" value="
						<bean:write name="cobrancaAcaoAtividadeComando"
						property="quantidadeDocumentos" />">						
						</logic:present>
						<logic:notPresent name="cobrancaAcaoAtividadeComando" property="quantidadeDocumentos">					
							<input name="inscricaoImovel22343"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="9" maxlength="9" value="">						
						</logic:notPresent>
						</td>
				</tr>
				<tr>
					<td class="style1"><strong>Valor dos Documentos:</strong></td>
					<td colspan="3" class="style1">
						<logic:present name="cobrancaAcaoAtividadeComando" property="valorDocumentos">					
							<input name="inscricaoImovel2232222"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="14" maxlength="14" value="
						<bean:write name="cobrancaAcaoAtividadeComando"
						property="valorDocumentos" />">						
						</logic:present>
						<logic:notPresent name="cobrancaAcaoAtividadeComando" property="valorDocumentos">					
							<input name="inscricaoImovel2232222"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="14" maxlength="14" value="">						
						</logic:notPresent>
						</td>
				</tr>
				<tr>
					<td class="style1"><strong>Quantidade de Itens Cobrados:</strong></td>
					<td colspan="3" class="style1">
					
						<logic:present name="cobrancaAcaoAtividadeComando" property="quantidadeItensCobrados">					
							<input name="inscricaoImovel223434"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="9" maxlength="9" value="
						<bean:write name="cobrancaAcaoAtividadeComando"
						property="quantidadeItensCobrados" />">						
						</logic:present>
						<logic:notPresent name="cobrancaAcaoAtividadeComando" property="quantidadeItensCobrados">					
							<input name="inscricaoImovel223434"
							type="text" disabled style="background-color:#EFEFEF; border:0"
							size="9" maxlength="9" value="">						
						</logic:notPresent>
						</td>
				</tr>
			</logic:iterate>
			<tr>
				<td colspan="4">
				<hr>
				</td>
			</tr>
			<tr>
				<td height="17">
				<div align="left"><input name="Button" type="button"
						class="bottonRightCol" value="Voltar"
					onClick="javascript:history.back();"></div>
				</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>
</form>
</body>
</html:html>
