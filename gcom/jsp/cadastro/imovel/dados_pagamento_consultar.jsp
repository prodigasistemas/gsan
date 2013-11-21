<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"	href="<bean:message key="caminho.css"/>EstilosCompesa.css"	type="text/css">

<html:javascript staticJavascript="false"  formName="ConsultarDadosPagamentoActionForm" dynamicJavascript="true" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">


	

</script>
</head>



<body leftmargin="5" topmargin="5" onload="resizePageSemLink(800,600);">


<html:form action="/exibirConsultarDadosPagamentoAction" 
	name="ConsultarDadosPagamentoActionForm" 
	type="gcom.gui.cadastro.imovel.ConsultarDadosPagamentoActionForm"
	method="post" onsubmit="return validateConsultarDadosPagamentoActionForm(this);">

	<table width="100%" border="0" cellspacing="5" cellpadding="0">

		<tr>

			<td width="100%" valign="top" class="centercoltext">

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
						<td class="parabg">Detalhamento dos Dados de um Pagamento</td>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
			
				<p>&nbsp;</p>

				<table width="100%" border="0" align="center" >
				
				<tr>
					<td class="style3"><strong>Localidade:</strong></td>
					<td colspan="3">
						<html:text property="descricaoLocalidade" readonly="true" maxlength="30"
						style="background-color:#EFEFEF; border:0;" size="30" /></td>
				</tr>
				
				<tr>
					<td class="style3"><strong>Tipo de Documento:</strong></td>
					<td colspan="3">
						<html:text property="tipoDocumento" readonly="true" maxlength="30"
						style="background-color:#EFEFEF; border:0;" size="30" /></td>
				</tr>
				
				<tr>
					<td class="style3"><strong>Matrícula do Imóvel:</strong></td>
					<td colspan="3">
						<html:text property="matriculaImovel" readonly="true" size="10" maxlength="10" 
						style="background-color:#EFEFEF; border:0;" /> 
				</tr>
				
				<tr>
					<td class="style3"><strong>Inscrição do Imóvel:</strong></td>
					<td colspan="3">
						<html:text property="inscricaoImovel" readonly="true" size="20" maxlength="20" 
						style="background-color:#EFEFEF; border:0;" /> 
						
					</td>
				</tr>
				
				<tr>
					<td class="style3"><strong>Cliente:</strong></td>
					<td colspan="3">
						<html:text property="clienteId" readonly="true" size="9" maxlength="9" 
						style="background-color:#EFEFEF; border:0;" /> 
						<html:text property="clienteNome" readonly="true" maxlength="50"
						style="background-color:#EFEFEF; border:0;" size="50" /></td>
				</tr>
				
				<tr>
					<td class="style3"><strong>Mês/Ano:</strong></td>
					<td colspan="3">
						<html:text property="mesAno" readonly="true" size="7" maxlength="7" 
						style="background-color:#EFEFEF; border:0;" /> 
						
				</tr>
				
				<tr>
					<td class="style3"><strong>Tipo de Débito:</strong></td>
					<td colspan="3">
						<html:text property="debitoId" readonly="true" size="4" maxlength="4" 
						style="background-color:#EFEFEF; border:0;" /> 
						<html:text property="debitoDescricao" readonly="true" maxlength="30"
						style="background-color:#EFEFEF; border:0;" size="30" /></td>
				</tr>
				
				<tr>
					<td class="style3"><strong>Data do Pagamento:</strong></td>
					<td colspan="3">
						<html:text property="dataPagamento" readonly="true" size="10" maxlength="10" 
						style="background-color:#EFEFEF; border:0;" /> 
					</td>
				</tr>
				
				<tr>
					<td class="style3"><strong>Valor do Pagamento:</strong></td>
					<td colspan="3">
						<html:text property="valorPagamento" readonly="true" size="15" maxlength="15" 
						style="background-color:#EFEFEF; border:0;" /> 
					</td>
				</tr>
				
				<tr>
					<td class="style3"><strong>Mês/Ano de Ref. da Arrecadação do Pag.:</strong></td>
					<td colspan="3">
						<html:text property="mesAnoRefPagamento" readonly="true" size="7" maxlength="7" 
						style="background-color:#EFEFEF; border:0;" /> 
					</td>
				</tr>
				
				<tr>
					<td class="style3"><strong>Data/Hora Processamento:</strong></td>
					<td colspan="3">
						<html:text property="dataProcessamento" readonly="true" size="10" maxlength="10" 
						style="background-color:#EFEFEF; border:0;" /> 
						<html:text property="horaProcessamento" readonly="true" maxlength="7"
						style="background-color:#EFEFEF; border:0;" size="7" />
					</td>
				</tr>
				
				<tr>
					<td class="style3"><strong>Situação Atual:</strong></td>
					<td colspan="3">
						<html:text property="descricaoSituacaoAtual" readonly="true" size="30" maxlength="30" 
						style="background-color:#EFEFEF; border:0;" /> 
					</td>
				</tr>
				
				<tr>
					<td class="style3"><strong>Situação Anterior:</strong></td>
					<td colspan="3">
						<html:text property="descricaoSituacaoAnterior" readonly="true" size="30" maxlength="30" 
						style="background-color:#EFEFEF; border:0;" /> 
					</td>
				</tr>
				
				<tr>
					<td class="style3"><strong>Valor Excedente:</strong></td>
					<td colspan="3">
						<html:text property="valorExcedente" readonly="true" size="15" maxlength="15" 
						style="background-color:#EFEFEF; border:0;" /> 
					</td>
				</tr>
				
				<td align="right">
					<logic:present name="avisoBancarioPreenchido" scope="request">
						<tr>
							<td class="style3"><strong>Código/Nome do Arrecadador:</strong></td>
							<td colspan="3">
								<html:text property="codigoArrecadador" readonly="true" size="10" maxlength="10" 
								style="background-color:#EFEFEF; border:0;" /> 
								<html:text property="nomeArrecadador" readonly="true" maxlength="50"
								style="background-color:#EFEFEF; border:0;" size="50" />
							</td>
						</tr>
						
						<tr>	
							<td class="style3"><strong>Data Lançamento:</strong></td>
							<td colspan="3">
								<html:text property="dataLancamento" readonly="true" size="10" maxlength="10" 
								style="background-color:#EFEFEF; border:0;" /> 
							</td>
						</tr>
					</logic:present>
				
					<logic:notPresent name="avisoBancarioPreenchido" scope="request">
						<tr>
							<td class="style3"><strong>Código/Nome do Agente Arrecadador:</strong></td>
							<td colspan="3">
								<html:text property="codigoAgenteArrecadador" readonly="true" size="10" maxlength="10" 
								style="background-color:#EFEFEF; border:0;" /> 
								<html:text property="nomeAgenteArrecadador" readonly="true" maxlength="50"
								style="background-color:#EFEFEF; border:0;" size="50" />
							</td>
						</tr>
					</logic:notPresent>
				</td>
				
				<td align="right">
					<logic:present name="arrecadadorMovimentoItem" scope="request">
						<tr>
							<td class="style3"><strong>Descrição do Código do Registro:</strong></td>
							<td colspan="3">
								<html:text property="descricaoCodigoRegistro" readonly="true" size="80" maxlength="80" 
								style="background-color:#EFEFEF; border:0;" /> 
							</td>
						</tr>
						<tr>	
							<td class="style3"><strong>Data/Hora do Processamento:</strong></td>
							<td colspan="3">
								<html:text property="dataProcessamentoMovimento" readonly="true" size="10" maxlength="10" 
								style="background-color:#EFEFEF; border:0;" /> 
								<html:text property="horaProcessamentoMovimento" readonly="true" size="10" maxlength="10" 
								style="background-color:#EFEFEF; border:0;" /> 
							</td>
						</tr>
						<tr>
							<td class="style3"><strong>Descrição da Ocorrência do Movimento:</strong></td>
							<td colspan="3">
								<html:text property="descricaoOcorrenciaMovimento" readonly="true" size="50" maxlength="50" 
								style="background-color:#EFEFEF; border:0;" /> 
							</td>
						</tr>
						
						<tr>
							<td class="style3"><strong>Sequencial do Arquivo de Movimento:</strong></td>
							<td colspan="3">
								<html:text property="sequencialArquivoMovimento" readonly="true" size="4" maxlength="4" 
								style="background-color:#EFEFEF; border:0;" /> 
							</td>
						</tr>
						
						<tr>
							<td class="style3"><strong>Código/Nome do Arrecadador:</strong></td>
							<td colspan="3">
								<html:text property="codigoArrecadadorMovimento" readonly="true" size="4" maxlength="4" 
								style="background-color:#EFEFEF; border:0;" /> 
								<html:text property="nomeArrecadadorMovimento" readonly="true" maxlength="40"
								style="background-color:#EFEFEF; border:0;" size="40" />
							</td>
						</tr>
						
						<tr>
							<td class="style3"><strong>Identificação do Serviço:</strong></td>
							<td colspan="3">
								<html:text property="servicoManutencao" readonly="true" size="20" maxlength="20" 
								style="background-color:#EFEFEF; border:0;" /> 
							</td>
						</tr>
						
						<tr>
							<td class="style3"><strong>Forma de Arrecadação:</strong></td>
							<td colspan="3">
								<html:text property="formaArrecadacao" readonly="true" size="80" maxlength="80" 
								style="background-color:#EFEFEF; border:0;" /> 
							</td>
						</tr>
						
						
					</logic:present>
				
				</td>
				
				<tr>
					<table width="100%">
						<tr>
							<td align="right">
								<input type="button" name="ButtonFechar" class="bottonRightCol"
								value="Fechar" tabindex="8"	onclick="window.close();" />
							</td>
						</tr>
					</table>		
				</tr>
			</table>
		</td>
	</tr>
</table>
</html:form>
</body>
</html:html>