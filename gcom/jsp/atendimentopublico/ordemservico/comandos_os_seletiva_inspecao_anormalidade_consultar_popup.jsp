<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.atendimentopublico.ordemservico.ComandoOrdemSeletiva"%>
<%@ page import="gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao"%>
<%@ page import="gcom.micromedicao.leitura.LeituraAnormalidade"%>
<%@ page import="gcom.micromedicao.hidrometro.HidrometroCapacidade"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript">
	function fechar(){
  		window.close();
	}
</script>
</head>

<body leftmargin="5" topmargin="5">
<table width="590" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="570" valign="top" class="centercoltext">

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
				<td class="parabg">Consultar Comandos de OS Seletiva de Inspeção de Anormalidade</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>

		<p>&nbsp;</p>

		
		<logic:present name="comandoOrdemSeletiva" scope="session">
			<table width="100%" border="0">
				<tr>
					<td><strong>Descrição do Comando de Geração OS Seletiva:</strong></td>
					<td colspan="2">
						<html:text name="comandoOrdemSeletiva" property="descricaoComando"
						size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
					</td>
				</tr>
				<tr>
					<td><strong>Data de Geração:</strong></td>
					<td colspan="2">
						<input type="text" name="comandoOrdemSeletiva" 
							value=<%="" +Util.formatarData(((ComandoOrdemSeletiva)session.getAttribute("comandoOrdemSeletiva")).getDataGeracao())%>
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
					</td>
				</tr>
				<tr>
					<td><strong>Data de Encerramento:</strong></td>
					<td colspan="2">
						<logic:notEmpty name="comandoOrdemSeletiva" property="dataEncerramento">
							<input type="text" name="comandoOrdemSeletiva" 
							value=<%="" +Util.formatarData(((ComandoOrdemSeletiva)session.getAttribute("comandoOrdemSeletiva")).getDataEncerramento())%>
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
						<logic:empty name="comandoOrdemSeletiva" property="dataEncerramento">
							<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:empty>
					</td>
				</tr>
				<tr>
					<td><strong>Firma:</strong></td>
					<td colspan="2">
						<logic:notEmpty name="comandoOrdemSeletiva" property="empresa">
							<logic:notEmpty name="comandoOrdemSeletiva" property="empresa.descricao">
								<html:text name="comandoOrdemSeletiva" property="empresa.descricao"
								size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
							</logic:notEmpty>
							<logic:empty name="comandoOrdemSeletiva" property="empresa.descricao">
								<input type="text" size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
							</logic:empty>
						</logic:notEmpty>
						<logic:empty name="comandoOrdemSeletiva" property="empresa">
							<input type="text" size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:empty>
					</td>
				</tr>
				<tr>
					<td><strong>Quantidade de OS geradas:</strong></td>
					<td colspan="2">
						<html:text name="comandoOrdemSeletiva" property="quantidadeOrdemServico"
						size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
					</td>
				</tr>
				<tr>
					<td><strong>Quantidade Máxima de OS informada:</strong></td>
					<td colspan="2">
						<logic:notEmpty name="comandoOrdemSeletiva" property="quantidadeMaximaOrdemServico">
							<html:text name="comandoOrdemSeletiva" property="quantidadeMaximaOrdemServico"
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
						<logic:empty name="comandoOrdemSeletiva" property="quantidadeMaximaOrdemServico">
							<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:empty>
					</td>
				</tr>
				<tr>
					<td><strong>Imóvel:</strong></td>
					<td colspan="2">
						<logic:notEmpty name="comandoOrdemSeletiva" property="imovel">
							<html:text name="comandoOrdemSeletiva" property="imovel.id"
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
						<logic:empty name="comandoOrdemSeletiva" property="imovel">
							<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:empty>
					</td>
				</tr>
				<tr>
					<td><strong>Gerência Regional:</strong></td>
					<td colspan="2">
						<logic:notEmpty name="comandoOrdemSeletiva" property="gerenciaRegional">
							<html:text name="comandoOrdemSeletiva" property="gerenciaRegional.nome"
							size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
						<logic:empty name="comandoOrdemSeletiva" property="gerenciaRegional">
							<input type="text" size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:empty>
					</td>
				</tr>
				<tr>
					<td><strong>Unidade de Negócio:</strong></td>
					<td colspan="2">
						<logic:notEmpty name="comandoOrdemSeletiva" property="unidadeNegocio">
							<html:text name="comandoOrdemSeletiva" property="unidadeNegocio.nome"
							size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
						<logic:empty name="comandoOrdemSeletiva" property="unidadeNegocio">
							<input type="text" size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:empty>
					</td>
				</tr>
				<tr>
					<td><strong>Localidade Polo:</strong></td>
					
					<logic:notEmpty name="comandoOrdemSeletiva" property="localidadePolo">
						<td>
							<html:text name="comandoOrdemSeletiva" property="localidadePolo.id"
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
						<td>
							<html:text name="comandoOrdemSeletiva" property="localidadePolo.descricao"
							size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
					</logic:notEmpty>
					<logic:empty name="comandoOrdemSeletiva" property="localidadePolo">
						<td>
							<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
						<td>
							<input type="text" size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
					</logic:empty>
				</tr>
				<tr>
					<td><strong>Logradouro:</strong></td>
					
					<logic:notEmpty name="comandoOrdemSeletiva" property="logradouro">
						<td>
							<html:text name="comandoOrdemSeletiva" property="logradouro.id"
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
						<td>
							<html:text name="comandoOrdemSeletiva" property="logradouro.nome"
							size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
					</logic:notEmpty>
					<logic:empty name="comandoOrdemSeletiva" property="logradouro">
						<td>
							<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
						<td>
							<input type="text" size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
					</logic:empty>
				</tr>
				<tr>
					<td><strong>Localidade Inicial:</strong></td>
					
					<logic:notEmpty name="comandoOrdemSeletiva" property="localidadeInicial">
						<td>
							<html:text name="comandoOrdemSeletiva" property="localidadeInicial.id"
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
						<td>
							<html:text name="comandoOrdemSeletiva" property="localidadeInicial.descricao"
							size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
					</logic:notEmpty>
					<logic:empty name="comandoOrdemSeletiva" property="localidadeInicial">
						<td>
							<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
						<td>
							<input type="text" size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
					</logic:empty>
				</tr>
				<tr>
					<td><strong>Setor Comercial Inicial:</strong></td>
					
					<logic:notEmpty name="comandoOrdemSeletiva" property="setorComercialInicial">
						<td>
							<html:text name="comandoOrdemSeletiva" property="setorComercialInicial.codigo"
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
						<td>
							<html:text name="comandoOrdemSeletiva" property="setorComercialInicial.descricao"
							size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
					</logic:notEmpty>
					<logic:empty name="comandoOrdemSeletiva" property="setorComercialInicial">
						<td>
							<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
						<td>
							<input type="text" size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
					</logic:empty>
				</tr>
				<tr>
					<td><strong>Quadra Inicial:</strong></td>
					
					<logic:notEmpty name="comandoOrdemSeletiva" property="quadraInicial">
						<td colspan="2">
							<html:text name="comandoOrdemSeletiva" property="quadraInicial.numeroQuadra"
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
					</logic:notEmpty>
					<logic:empty name="comandoOrdemSeletiva" property="quadraInicial">
						<td colspan="2">
							<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
					</logic:empty>
				</tr>
				<tr>
					<td><strong>Rota Inicial:</strong></td>
					
					<logic:notEmpty name="comandoOrdemSeletiva" property="rotaInicial">
						<td>
							<html:text name="comandoOrdemSeletiva" property="rotaInicial.id"
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
						<logic:notEmpty name="comandoOrdemSeletiva" property="sequencialRotaInicial">
							<td>
								<html:text name="comandoOrdemSeletiva" property="sequencialRotaInicial"
								size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
							</td>
						</logic:notEmpty>
						<logic:empty name="comandoOrdemSeletiva" property="sequencialRotaInicial">
							<td>
								<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
							</td>
						</logic:empty>
					</logic:notEmpty>
					<logic:empty name="comandoOrdemSeletiva" property="rotaInicial">
						<td>
							<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
						<td>
							<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
					</logic:empty>
				</tr>
				<tr>
					<td><strong>Localidade Final:</strong></td>
					
					<logic:notEmpty name="comandoOrdemSeletiva" property="localidadeFinal">
						<td>
							<html:text name="comandoOrdemSeletiva" property="localidadeFinal.id"
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
						<td>
							<html:text name="comandoOrdemSeletiva" property="localidadeFinal.descricao"
							size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
					</logic:notEmpty>
					<logic:empty name="comandoOrdemSeletiva" property="localidadeFinal">
						<td>
							<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
						<td>
							<input type="text" size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
					</logic:empty>
				</tr>
				<tr>
					<td><strong>Setor Comercial Final:</strong></td>
					
					<logic:notEmpty name="comandoOrdemSeletiva" property="setorComercialFinal">
						<td>
							<html:text name="comandoOrdemSeletiva" property="setorComercialFinal.codigo"
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
						<td>
							<html:text name="comandoOrdemSeletiva" property="setorComercialFinal.descricao"
							size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
					</logic:notEmpty>
					<logic:empty name="comandoOrdemSeletiva" property="setorComercialFinal">
						<td>
							<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
						<td>
							<input type="text" size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
					</logic:empty>
				</tr>
				<tr>
					<td><strong>Quadra Final:</strong></td>
					
					<logic:notEmpty name="comandoOrdemSeletiva" property="quadraFinal">
						<td colspan="2">
							<html:text name="comandoOrdemSeletiva" property="quadraFinal.numeroQuadra"
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
					</logic:notEmpty>
					<logic:empty name="comandoOrdemSeletiva" property="quadraFinal">
						<td colspan="2">
							<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
					</logic:empty>
				</tr>
				<tr>
					<td><strong>Rota Final:</strong></td>
					
					<logic:notEmpty name="comandoOrdemSeletiva" property="rotaFinal">
						<td>
							<html:text name="comandoOrdemSeletiva" property="rotaFinal.id"
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
						<logic:notEmpty name="comandoOrdemSeletiva" property="sequencialRotaFinal">
							<td>
								<html:text name="comandoOrdemSeletiva" property="sequencialRotaFinal"
								size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
							</td>
						</logic:notEmpty>
						<logic:empty name="comandoOrdemSeletiva" property="sequencialRotaFinal">
							<td>
								<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
							</td>
						</logic:empty>
					</logic:notEmpty>
					<logic:empty name="comandoOrdemSeletiva" property="rotaFinal">
						<td>
							<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
						<td>
							<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
					</logic:empty>
				</tr>
				<logic:notPresent name="colecaoCapacidHidrComandoOSS" scope="session">
					<tr>
						<td><strong>Capacidade:</strong></td>
						<td colspan="2">
						<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						<input type="text" size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
					</tr>
				</logic:notPresent>
				<logic:present name="colecaoCapacidHidrComandoOSS" scope="session">
				
					<logic:iterate name="colecaoCapacidHidrComandoOSS"
							id="hidrometroCapacidade" type="HidrometroCapacidade">
						<tr>
							<c:set var="count1" value="${count1+1}" />
							<c:choose>
								<c:when test="${count1 == '1'}">
									<td><strong>Capacidade:</strong></td>
								</c:when>
								<c:otherwise>
									<td><strong></strong></td>
								</c:otherwise>
							</c:choose>
											
							<td colspan="2">
							<input type="text" value="<bean:write name='hidrometroCapacidade' property='descricao' />" size="50" 
								readonly="true" style="background-color:#EFEFEF; border:0; font-color: #000000" />
							</td>
						</tr>
					</logic:iterate>
				</logic:present>
				<tr>
					<td><strong>Marca:</strong></td>
					<td colspan="2">
						<logic:notEmpty name="comandoOrdemSeletiva" property="hidrometroMarca">
							<html:text name="comandoOrdemSeletiva" property="hidrometroMarca.descricao"
							size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
						<logic:empty name="comandoOrdemSeletiva" property="hidrometroMarca">
							<input type="text" size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:empty>
					</td>
				</tr>
				<tr>
					<td><strong>Local de Instalação :</strong></td>
					<td colspan="2">
						<logic:notEmpty name="comandoOrdemSeletiva" property="hidrometroLocalInstalacao">
							<html:text name="comandoOrdemSeletiva" property="hidrometroLocalInstalacao.descricao"
							size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
						<logic:empty name="comandoOrdemSeletiva" property="hidrometroLocalInstalacao">
							<input type="text" size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:empty>
					</td>
				</tr>
				
				<logic:notPresent name="colecaoAnormalidadeComandoOSS" scope="session">
					<tr>
						<td><strong>Anormalidade de Leitura:</strong></td>
						<td colspan="2">
						<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						<input type="text" size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
					</tr>
				</logic:notPresent>
				<logic:present name="colecaoAnormalidadeComandoOSS" scope="session">
				
					<logic:iterate name="colecaoAnormalidadeComandoOSS"
							id="leituraAnormalidade" type="LeituraAnormalidade">
						<tr>
							<c:set var="count2" value="${count2+1}" />
							<c:choose>
								<c:when test="${count2 == '1'}">
									<td><strong>Anormalidade de Leitura:</strong></td>
								</c:when>
								<c:otherwise>
									<td><strong></strong></td>
								</c:otherwise>
							</c:choose>
											
							<td colspan="2">
							<input type="text" value="<bean:write name='leituraAnormalidade' property='descricao' />" size="50" 
								readonly="true" style="background-color:#EFEFEF; border:0; font-color: #000000" />
							</td>
						</tr>
					</logic:iterate>
					
				
				</logic:present>
				
	
				
				<tr>
					<td><strong>Número de Ocorrências Consecutivas:</strong></td>
					<td colspan="2">
						<logic:notEmpty name="comandoOrdemSeletiva" property="quantidadeConsecutivaAnormalidade">
							<html:text name="comandoOrdemSeletiva" property="quantidadeConsecutivaAnormalidade"
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
						<logic:empty name="comandoOrdemSeletiva" property="quantidadeConsecutivaAnormalidade">
							<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:empty>
					</td>
				</tr>
				<tr>
					<td><strong>Mês/Ano de Instalação:</strong></td>
					<td>
						<logic:notEmpty name="comandoOrdemSeletiva" property="anoMesHidrometroInstInicial">
							<input type="text" name="comandoOrdemSeletiva" 
							value=<%="" +Util.formatarMesAnoReferencia(((ComandoOrdemSeletiva)session.getAttribute("comandoOrdemSeletiva")).getAnoMesHidrometroInstInicial())%>
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
						<logic:empty name="comandoOrdemSeletiva" property="anoMesHidrometroInstInicial">
							<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:empty>
					</td>
					<td>
						<logic:notEmpty name="comandoOrdemSeletiva" property="anoMesHidrometroInstFinal">
							a <input type="text" name="comandoOrdemSeletiva" 
							value=<%="" +Util.formatarMesAnoReferencia(((ComandoOrdemSeletiva)session.getAttribute("comandoOrdemSeletiva")).getAnoMesHidrometroInstFinal())%>
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
						<logic:empty name="comandoOrdemSeletiva" property="anoMesHidrometroInstFinal">
							a <input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:empty>
					</td>
				</tr>
				<tr>
					<td><strong>Perfil do Imóvel:</strong></td>
					<td colspan="2">
						<logic:notEmpty name="comandoOrdemSeletiva" property="imovelPerfil">
							<html:text name="comandoOrdemSeletiva" property="imovelPerfil.descricao"
							size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
						<logic:empty name="comandoOrdemSeletiva" property="imovelPerfil">
							<input type="text" size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:empty>
					</td>
				</tr>
				<tr>
					<td><strong>Categoria:</strong></td>
					<td colspan="2">
						<logic:notEmpty name="comandoOrdemSeletiva" property="categoria">
							<html:text name="comandoOrdemSeletiva" property="categoria.descricao"
							size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
						<logic:empty name="comandoOrdemSeletiva" property="categoria">
							<input type="text" size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:empty>
					</td>
				</tr>
				<tr>
					<td><strong>Subcategoria:</strong></td>
					<td colspan="2">
						<logic:notEmpty name="comandoOrdemSeletiva" property="subcategoria">
							<html:text name="comandoOrdemSeletiva" property="subcategoria.descricao"
							size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
						<logic:empty name="comandoOrdemSeletiva" property="subcategoria">
							<input type="text" size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:empty>
					</td>
				</tr>
				<tr>
					<td><strong>Intervalo de Quantidade de Economias:</strong></td>
					<td>
						<logic:notEmpty name="comandoOrdemSeletiva" property="quantidadeEconomiaInicial">
							<html:text name="comandoOrdemSeletiva" property="quantidadeEconomiaInicial"
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
						<logic:empty name="comandoOrdemSeletiva" property="quantidadeEconomiaInicial">
							<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:empty>
					</td>
					<td>
						<logic:notEmpty name="comandoOrdemSeletiva" property="quantidadeEconomiaFinal">
							a <html:text name="comandoOrdemSeletiva" property="quantidadeEconomiaFinal"
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
						<logic:empty name="comandoOrdemSeletiva" property="quantidadeEconomiaFinal">
							a <input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:empty>
					</td>
				</tr>
				<tr>
					<td><strong>Intervalo de Quantidade de Documentos:</strong></td>
					<td>
						<logic:notEmpty name="comandoOrdemSeletiva" property="quantidadeDocumentoInicial">
							<html:text name="comandoOrdemSeletiva" property="quantidadeDocumentoInicial"
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
						<logic:empty name="comandoOrdemSeletiva" property="quantidadeDocumentoInicial">
							<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:empty>
					</td>
					<td>
						<logic:notEmpty name="comandoOrdemSeletiva" property="quantidadeDocumentoFinal">
							a <html:text name="comandoOrdemSeletiva" property="quantidadeDocumentoFinal"
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
						<logic:empty name="comandoOrdemSeletiva" property="quantidadeDocumentoFinal">
							a <input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:empty>
					</td>
				</tr>
				<tr>
					<td><strong>Intervalo de Número de Moradores:</strong></td>
					<td>
						<logic:notEmpty name="comandoOrdemSeletiva" property="quantidadeMoradoresInicial">
							<html:text name="comandoOrdemSeletiva" property="quantidadeMoradoresInicial"
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
						<logic:empty name="comandoOrdemSeletiva" property="quantidadeMoradoresInicial">
							<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:empty>
					</td>
					<td>
						<logic:notEmpty name="comandoOrdemSeletiva" property="quantidadeMoradoresFinal">
							a <html:text name="comandoOrdemSeletiva" property="quantidadeMoradoresFinal"
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
						<logic:empty name="comandoOrdemSeletiva" property="quantidadeMoradoresFinal">
							a <input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:empty>
					</td>
				</tr>
				<tr>
					<td><strong>Intervalo de Área Construída:</strong></td>
					<td>
						<logic:notEmpty name="comandoOrdemSeletiva" property="areaConstruidaInicial">
							<html:text name="comandoOrdemSeletiva" property="areaConstruidaInicial"
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
						<logic:empty name="comandoOrdemSeletiva" property="areaConstruidaInicial">
							<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:empty>
					</td>
					<td>
						<logic:notEmpty name="comandoOrdemSeletiva" property="areaConstruidaFinal">
							a <html:text name="comandoOrdemSeletiva" property="areaConstruidaFinal"
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
						<logic:empty name="comandoOrdemSeletiva" property="areaConstruidaFinal">
							a <input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:empty>
					</td>
				</tr>
				<tr>
					<td><strong>Imóvel Condomínio:</strong></td>
					<td colspan="2">
						<logic:notEmpty name="comandoOrdemSeletiva" property="indicadorImovelCondominio">
							<logic:equal name="comandoOrdemSeletiva" property="indicadorImovelCondominio" value="1" >
								<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" value="SIM"/>
							</logic:equal>
							<logic:equal name="comandoOrdemSeletiva" property="indicadorImovelCondominio" value="2">
								<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" value="NÃO"/>
							</logic:equal>
						</logic:notEmpty>
					</td>
				</tr>
				<tr>
					<td><strong>Média do Imóvel:</strong></td>
					<td colspan="2">
						<logic:notEmpty name="comandoOrdemSeletiva" property="mediaConsumo">
							<html:text name="comandoOrdemSeletiva" property="mediaConsumo"
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
						<logic:empty name="comandoOrdemSeletiva" property="mediaConsumo">
							<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:empty>
					</td>
				</tr>
				<tr>
					<td><strong>Intervalo de Consumo por Economia:</strong></td>
					<td>
						<logic:notEmpty name="comandoOrdemSeletiva" property="consumoEconomiaInicial">
							<html:text name="comandoOrdemSeletiva" property="consumoEconomiaInicial"
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
						<logic:empty name="comandoOrdemSeletiva" property="consumoEconomiaInicial">
							<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:empty>
					</td>
					<td>
						<logic:notEmpty name="comandoOrdemSeletiva" property="consumoEconomiaFinal">
							a <html:text name="comandoOrdemSeletiva" property="consumoEconomiaFinal"
							size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
						<logic:empty name="comandoOrdemSeletiva" property="consumoEconomiaFinal">
							a <input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:empty>
					</td>
				</tr>
				
				<logic:notPresent name="colecaoLigacaoSitComandoOSS" scope="session">
					<tr>
						<td><strong>Unidade de Negócio:</strong></td>
						<td colspan="2">
						<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" />
						<input type="text" size="50" readonly="true" style="background-color:#EFEFEF; border:0" />
						</td>
					</tr>
				</logic:notPresent>
				<logic:present name="colecaoLigacaoSitComandoOSS" scope="session">
				
					<logic:iterate name="colecaoLigacaoSitComandoOSS"
							id="ligacaoAguaSituacao" type="LigacaoAguaSituacao">
						<tr>
							<c:set var="count3" value="${count3+1}" />
							<c:choose>
								<c:when test="${count3 == '1'}">
									<td><strong>Situação de Ligação de Água:</strong></td>
								</c:when>
								<c:otherwise>
									<td><strong></strong></td>
								</c:otherwise>
							</c:choose>
											
							<td colspan="2">
							<input type="text" value="<bean:write name='ligacaoAguaSituacao' property='descricao' />" size="50" 
								readonly="true" style="background-color:#EFEFEF; border:0; font-color: #000000" />
							</td>
						</tr>
					</logic:iterate>
				</logic:present>
				
				<tr>
					<td><strong>Gerou Txt:</strong></td>
					<td colspan="2">
						<logic:notEmpty name="comandoOrdemSeletiva" property="indicadorGeracaoTxt">
							<logic:equal name="comandoOrdemSeletiva" property="indicadorGeracaoTxt" value="1" >
								<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" value="SIM"/>
							</logic:equal>
							<logic:equal name="comandoOrdemSeletiva" property="indicadorGeracaoTxt" value="2">
								<input type="text" size="10" readonly="true" style="background-color:#EFEFEF; border:0" value="NÃO"/>
							</logic:equal>
						</logic:notEmpty>
					</td>
				</tr>
				
			</table>
		</logic:present> 
		
		<%--FIM --%> 
		

		<table width="100%">
			<tr>
				<td colspan="1" align="right">
					<input name="Button" 
						type="button" 
						class="bottonRightCol" 
						value="Fechar"
						onClick="javascript:fechar();">
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html:html>