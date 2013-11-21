<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.arrecadacao.pagamento.Pagamento" isELIgnored="false"%>
<%@ page import="gcom.util.Util" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta name="generator"
	content="Web page layout created by Xara Webstyle 4 - http://www.xara.com/webstyle/" />
<link rel="stylesheet"
	href="<bean:message key='caminho.css'/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key='caminho.js'/>util.js"></script>

<script language="JavaScript">
  function resizePageSemLink(largura, altura){

	   //Para abrir o popup centralizado
		var height = window.screen.height - 160;
		var width = window.screen.width;
		var top = 50;
		var left = (width - largura)/2;
		resizeNow(largura, altura, top, left);
  }

  function resizeNow(largura, altura, top, left){
       window.resizeTo(largura, altura);
       window.moveTo(left , top);
  }
</script>
</head>

<logic:present name="consultarPagamentos">
	<body leftmargin="0" topmargin="0" onload="resizePageSemLink(740, 620);">
</logic:present>

<logic:notPresent name="consultarPagamentos">
	<body leftmargin="0" topmargin="0" onload="resizePageSemLink(600, 450);">
</logic:notPresent>


<logic:present name="consultarPagamentos">
<table width=692 border="0" cellpadding="0" cellspacing="5">
  <tr>
    <td width="682" valign="top" class="centercoltext"> 
</logic:present>

<logic:notPresent name="consultarPagamentos">
<table width=562 border="0" cellpadding="0" cellspacing="5">
  <tr>
    <td width="552" valign="top" class="centercoltext"> 
</logic:notPresent>

    
     <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" /></td>
          <td class="parabg">Consultar Itens do Movimento dos Arrecadadores</td>
          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
        </tr>
      </table>
      <p>&nbsp;</p>

	  <html:form action="/exibirApresentarDadosConteudoRegistroMovimentoArrecadadorAction" method="post">

      <table width="100%" border="0">
        <tr>
          <td colspan="4" width="100%"></td>
        </tr>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="codigoRegistro">
        <tr>
          <td width="35%" height="24"><strong>Código do Registro:</strong></td>
          <td colspan="3"><html:text property="codigoRegistro" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.codigoRegistro}"/></td>
        </tr>
		</logic:present>
		
		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="nomeAgencia">
        <tr>
          <td width="35%" height="24"><strong>Nome do Arrecadador:</strong></td>
          <td colspan="3"><html:text property="nomeAgencia" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.nomeAgencia}"/></td>
        </tr>
		</logic:present>
		
        
		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="identificacaoClienteEmpresa">
        <tr>
          <td width="21%" height="24"><strong>Identificação do cliente na empresa:</strong></td>
          <td colspan="3"><html:text property="identificacaoClienteEmpresa" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.identificacaoClienteEmpresa}"/></td>
        </tr>
		</logic:present>
		
		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="agenciaDebito">
        <tr>
          <td width="21%" height="24"><strong>Agência para débito:</strong></td>
          <td colspan="3"><html:text property="agenciaDebito" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.agenciaDebito}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="identificacaoClienteBanco">
        <tr>
          <td width="21%" height="24"><strong>Identificação do cliente no banco:</strong></td>
          <td colspan="3"><html:text property="identificacaoClienteBanco" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.identificacaoClienteBanco}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dataOpcaoExclusao">
        <tr>
          <td width="21%" height="24"><strong>Data de opção/exclusão:</strong></td>
          <td colspan="3"><html:text property="dataOpcaoExclusao" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dataOpcaoExclusao}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dataVencimentoDebito">
        <tr>
          <td width="21%" height="24"><strong>Data do vencimento/débito:</strong></td>
          <td colspan="3"><html:text property="dataVencimentoDebito" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dataVencimentoDebito}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="valorDebito">
        <tr>
          <td width="21%" height="24"><strong>Valor do débito:</strong></td>
          <td colspan="3"><html:text property="valorDebito" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000; text-align: right;" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.valorDebito}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="codigoMoeda">
        <tr>
          <td width="21%" height="24"><strong>Código da moeda:</strong></td>
          <td colspan="3"><html:text property="codigoMoeda" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.codigoMoeda}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="codigoRetorno">
        <tr>
          <td width="21%" height="24"><strong>Código do retorno:</strong></td>
          <td colspan="3"><html:text property="codigoRetorno" size="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.codigoRetorno}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="mesAnoReferenciaConta">
        <tr>
          <td width="21%" height="24"><strong>Mês/ano de referência da conta:</strong></td>
          <td colspan="3"><html:text property="mesAnoReferenciaConta" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.mesAnoReferenciaConta}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="digitoVerificadorConta">
        <tr>
          <td width="21%" height="24"><strong>Dígito verificador da conta:</strong></td>
          <td colspan="3"><html:text property="digitoVerificadorConta" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.digitoVerificadorConta}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="descricaoMovimento">
        <tr>
          <td width="21%" height="24"><strong>Código do movimento8:</strong></td>
          <td colspan="3"><html:text property="descricaoMovimento" size="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.descricaoMovimento}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="ocorrencia">
        <tr>
          <td width="21%" height="24"><strong>Ocorrência:</strong></td>
          <td colspan="3"><html:text property="ocorrencia" size="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.ocorrencia}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="indicadorAceitacao">
        <tr>
          <td width="21%" height="24"><strong>Indicador de aceitação:</strong></td>
          <td colspan="3"><html:text property="indicadorAceitacao" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.indicadorAceitacao}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="identificacaoAgenciaContaDigitoCreditada">
        <tr>
          <td width="21%" height="24"><strong>Identificação da agência/conta/dígito creditado:</strong></td>
          <td colspan="3"><html:text property="identificacaoAgenciaContaDigitoCreditada" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.identificacaoAgenciaContaDigitoCreditada}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dataPagamento">
        <tr>
          <td width="21%" height="24"><strong>Data de pagamento:</strong></td>
          <td colspan="3"><html:text property="dataPagamento" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dataPagamento}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dataPrevistaCredito">
        <tr>
          <td width="21%" height="24"><strong>Data prevista para o crédito:</strong></td>
          <td colspan="3"><html:text property="dataPrevistaCredito" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dataPrevistaCredito}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="valorRecebido">
        <tr>
          <td width="21%" height="24"><strong>Valor recebido:</strong></td>
          <td colspan="3"><html:text property="valorRecebido" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000; text-align: right;" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.valorRecebido}"/></td>
        </tr>
		</logic:present>
		
		
		
		
	   <logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper">
	   <tr>
      	<td colspan="4" height="10"></td>
       </tr>
       <tr>
      	<td colspan="4">
      		<table width="100%" align="center" bgcolor="#99CCFF" border="0">
				<tr>
					<td><strong>Dados do Conteúdo do Código de Barras:</strong></td>
				</tr>
				<tr bgcolor="#cbe5fe">
					<td width="100%" align="center">
			
						<table width="100%" border="0">
						
						<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.identificacaoProduto">
						<tr>
							<td height="20" width="35%"><strong>Produto:</strong></td>
					        <td>
								<html:text property="identificacaoProduto" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.identificacaoProduto}"/>
							</td>
						</tr>
						</logic:present>
						
						<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.identificacaoSegmento">
						<tr>
							<td height="20" width="21%"><strong>Segmento:</strong></td>
					        <td>
								<html:text property="identificacaoSegmento" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.identificacaoSegmento}"/>
							</td>
						</tr>
						</logic:present>
						
						<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.identificacaoValorRealOUReferencia">
						<tr>
							<td height="20" width="21%"><strong>Valor Real ou Referência:</strong></td>
					        <td>
								<html:text property="identificacaoSegmento" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.identificacaoValorRealOUReferencia}"/>
							</td>
						</tr>
						</logic:present>
						
						<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.digitoVerificadorGeral">
						<tr>
							<td height="20" width="35%"><strong>Dígito Verificador Geral:</strong></td>
					        <td>
								<html:text property="identificacaoSegmento" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.digitoVerificadorGeral}"/>
							</td>
						</tr>
						</logic:present>
						
						<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.valorPagamento">
						<tr>
							<td height="20" width="21%"><strong>Valor do Pagamento:</strong></td>
					        <td>
								<html:text property="identificacaoSegmento" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000; text-align: right;" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.valorPagamento}"/>
							</td>
						</tr>
						</logic:present>
						
						<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.identificacaoEmpresa">
						<tr>
							<td height="20" width="21%"><strong>Empresa:</strong></td>
					        <td>
								<html:text property="identificacaoEmpresa" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.identificacaoEmpresa}"/>
							</td>
						</tr>
						</logic:present>
						
						<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.tipoPagamento">
						<tr>
							<td height="20" width="21%"><strong>Tipo de Pagamento:</strong></td>
					        <td>
								<html:text property="tipoPagamento" size="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.tipoPagamento}"/>
							</td>
						</tr>
						</logic:present>
						
						
						
						
						
						
						
					 	<%-- dados do codigo de barras da ficha de compensacao --%>			
						
						<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.codigoBanco">
						<tr>
							<td height="20" width="21%"><strong>Código do Banco:</strong></td>
					        <td>
								<html:text property="codigoBanco" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.codigoBanco}"/>
							</td>
						</tr>
						</logic:present>
						
						<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.codigoMoeda">
						<tr>
							<td height="20" width="21%"><strong>Código da Moeda:</strong></td>
					        <td>
								<html:text property="codigoMoeda" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.codigoMoeda}"/>
							</td>
						</tr>
						</logic:present>
						
						<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.fatorVencimento">
						<tr>
							<td height="20" width="21%"><strong>Fator de Vencimento:</strong></td>
					        <td>
								<html:text property="fatorVencimento" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.fatorVencimento}"/>
							</td>
						</tr>
						</logic:present>
						
						<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.valorCodigoBarras">
						<tr>
							<td height="20" width="21%"><strong>Valor do Código de Barras:</strong></td>
					        <td>
								<html:text property="valorCodigoBarras" size="20" readonly="true" style="background-color:#EFEFEF; border:0;text-align: right; color: #000000;" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.valorCodigoBarras}"/>
							</td>
						</tr>
						</logic:present>
						
						<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.nossoNumero">
						<tr>
							<td height="20" width="21%"><strong>Nosso Número:</strong></td>
					        <td>
								<html:text property="nossoNumero" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.nossoNumero}"/>
							</td>
						</tr>
						</logic:present>
						
						<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.tipoCarteira">
						<tr>
							<td height="20" width="21%"><strong>Tipo de Carteira:</strong></td>
					        <td>
								<html:text property="tipoCarteira" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.tipoCarteira}"/>
							</td>
						</tr>
						</logic:present>
					 	<%-- final dos dados do codigo de barras da ficha de compensacao --%>			
						
						
						
						
						
						
						
						
						
						
						
						<tr>
				      		<td colspan="2" height="10"></td>
				        </tr>
						<tr>
							<td colspan="2">
							
								<table width="100%" align="center" bgcolor="#99CCFF" border="0">
								<tr>
									<td><strong>Identificação do Pagamento:</strong></td>
								</tr>
								<tr bgcolor="#cbe5fe">
									<td width="100%" align="center">
							
										<table width="100%" border="0">
										
										<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.codigoLocalidade">
										<tr>
											<td height="20" width="35%"><strong>Localidade:</strong></td>
									        <td>
												<html:text property="codigoLocalidade" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.codigoLocalidade}"/>
											</td>
										</tr>
										</logic:present>
										
										<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.matriculaImovel">
										<tr>
											<td height="20" width="35%"><strong>Imóvel:</strong></td>
									        <td>
												<html:text property="matriculaImovel" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.matriculaImovel}"/>
											</td>
										</tr>
										</logic:present>
										
										<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.codigoCliente">
										<tr>
											<td height="20" width="35%"><strong>Cliente:</strong></td>
									        <td>
												<html:text property="codigoCliente" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.codigoCliente}"/>
											</td>
										</tr>
										</logic:present>
										
										<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.mesAnoReferenciaConta">
										<tr>
											<td height="20" width="35%"><strong>Mês/Ano de referência da conta:</strong></td>
									        <td>
												<html:text property="mesAnoReferenciaConta" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.mesAnoReferenciaConta}"/>
											</td>
										</tr>
										</logic:present>
										
										<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.digitoVerificadorContaModulo10">
										<tr>
											<td height="20" width="35%"><strong>Dígito verificador da conta (Módulo 10):</strong></td>
									        <td>
												<html:text property="digitoVerificadorContaModulo10" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.digitoVerificadorContaModulo10}"/>
											</td>
										</tr>
										</logic:present>
										
										<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.codigoTipoDebito">
										<tr>
											<td height="20" width="35%"><strong>Tipo de débito:</strong></td>
									        <td>
												<html:text property="codigoTipoDebito" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.codigoTipoDebito}"/>
											</td>
										</tr>
										</logic:present>
										
										<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.anoEmissaoGuiaPagamento">
										<tr>
											<td height="20" width="35%"><strong>Ano da emissão da guia de pagamento:</strong></td>
									        <td>
												<html:text property="anoEmissaoGuiaPagamento" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.anoEmissaoGuiaPagamento}"/>
											</td>
										</tr>
										</logic:present>
										
										<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.sequencialDocumentoCobranca">
										<tr>
											<td height="20" width="35%"><strong>Sequencial do documento de cobrança:</strong></td>
									        <td>
												<html:text property="sequencialDocumentoCobranca" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.sequencialDocumentoCobranca}"/>
											</td>
										</tr>
										</logic:present>
										
										<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.codigoTipoDocumento">
										<tr>
											<td height="20" width="35%"><strong>Tipo de documento:</strong></td>
									        <td>
												<html:text property="codigoTipoDocumento" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.codigoTipoDocumento}"/>
											</td>
										</tr>
										</logic:present>
										
										<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.sequencialFaturaClienteResponsavel">
										<tr>
											<td height="20" width="35%"><strong>Sequencial da fatura do cliente responsável:</strong></td>
									        <td>
												<html:text property="sequencialFaturaClienteResponsavel" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.sequencialFaturaClienteResponsavel}"/>
											</td>
										</tr>
										</logic:present>
										
										<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.codigoOrigemConta">
										<tr>
											<td height="20" width="35%"><strong>Código Origem da Conta:</strong></td>
									        <td>
												<html:text property="codigoOrigemConta" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.codigoOrigemConta}"/>
											</td>
										</tr>
										</logic:present>
										
										<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.qualificacao">
										<tr>
											<td height="20" width="35%"><strong>Qualificação:</strong></td>
									        <td>
												<html:text property="qualificacao" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.qualificacao}"/>
											</td>
										</tr>
										</logic:present>
										
										<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.mesAno">
										<tr>
											<td height="20" width="35%"><strong>Mês/Ano:</strong></td>
									        <td>
												<html:text property="mesAno" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.mesAno}"/>
											</td>
										</tr>
										</logic:present>
										
										<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.numeroDocumento">
										<tr>
											<td height="20" width="35%"><strong>Número do Documento:</strong></td>
									        <td>
												<html:text property="numeroDocumento" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.numeroDocumento}"/>
											</td>
										</tr>
										</logic:present>
										
										<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dadosConteudoCodigoBarrasHelper.identificacaoDocumento">
										<tr>
											<td height="20" width="35%"><strong>Identificação do Documento:</strong></td>
									        <td>
												<html:text property="identificacaoDocumento" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dadosConteudoCodigoBarrasHelper.identificacaoDocumento}"/>
											</td>
										</tr>
										</logic:present>
										
										
										</table>
				
									</td>
								</tr>
								</table>
							</td>
						</tr>
						
						</table>

					</td>
				</tr>
				</table>
      		</td>
       </tr>
	   <tr>
      	<td colspan="2" height="10"></td>
       </tr>
	   </logic:present>
		
		
		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="valorTarifa">
        <tr>
          <td width="21%" height="24"><strong>Valor da tarifa:</strong></td>
          <td colspan="3"><html:text property="valorTarifa" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000; text-align: right;" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.valorTarifa}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="nsr">
        <tr>
          <td width="21%" height="24"><strong>Número sequencial de registro (NSR):</strong></td>
          <td colspan="3"><html:text property="nsr" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.nsr}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="codigoAgenciaArrecadadora">
        <tr>
          <td width="21%" height="24"><strong>Código da agência arrecadadora:</strong></td>
          <td colspan="3"><html:text property="codigoAgenciaArrecadadora" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.codigoAgenciaArrecadadora}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="formaArrecadacaoCaptura">
        <tr>
          <td width="21%" height="24"><strong>Forma de arrecadação/captura:</strong></td>
          <td colspan="3"><html:text property="formaArrecadacaoCaptura" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.formaArrecadacaoCaptura}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="numeroAutenticacaoCaixaOUCodigoTransacao">
        <tr>
          <td width="21%" height="24"><strong>Número de autenticação caixa ou código de transação:</strong></td>
          <td colspan="3"><html:text property="numeroAutenticacaoCaixaOUCodigoTransacao" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.numeroAutenticacaoCaixaOUCodigoTransacao}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="formaPagamento">
        <tr>
          <td width="21%" height="24"><strong>Forma de pagamento:</strong></td>
          <td colspan="3"><html:text property="formaPagamento" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.formaPagamento}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="codigoAgencia">
        <tr>
          <td width="21%" height="24"><strong>Código da agência:</strong></td>
          <td colspan="3"><html:text property="codigoAgencia" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.codigoAgencia}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="nomeAgencia">
        <tr>
          <td width="21%" height="24"><strong>Nome da agência:</strong></td>
          <td colspan="3"><html:text property="nomeAgencia" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.nomeAgencia}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="nomeLogradouro">
        <tr>
          <td width="21%" height="24"><strong>Nome do logradouro:</strong></td>
          <td colspan="3"><html:text property="nomeLogradouro" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.nomeLogradouro}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="numero">
        <tr>
          <td width="21%" height="24"><strong>Número:</strong></td>
          <td colspan="3"><html:text property="numero" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.numero}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="codigoCep">
        <tr>
          <td width="21%" height="24"><strong>Código do CEP:</strong></td>
          <td colspan="3"><html:text property="codigoCep" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.codigoCep}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="sufixoCep">
        <tr>
          <td width="21%" height="24"><strong>Sufixo do CEP:</strong></td>
          <td colspan="3"><html:text property="sufixoCep" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.sufixoCep}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="nomeCidade">
        <tr>
          <td width="21%" height="24"><strong>Nome da cidade:</strong></td>
          <td colspan="3"><html:text property="nomeCidade" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.nomeCidade}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="siglaUnidadeFederacao">
        <tr>
          <td width="21%" height="24"><strong>Sigla da unidade da federação:</strong></td>
          <td colspan="3"><html:text property="siglaUnidadeFederacao" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.siglaUnidadeFederacao}"/></td>
        </tr>
		</logic:present>

		<logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="situacaoAgencia">
        <tr>
          <td width="21%" height="24"><strong>Situação da agência:</strong></td>
          <td colspan="3"><html:text property="situacaoAgencia" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.situacaoAgencia}"/></td>
        </tr>
		</logic:present>

        
        <logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="colecaoPagamentos">
        <tr>
          <td height="10" colspan="4">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="4"><strong>Pagamentos:</strong></td>
        </tr>
        <tr>
      	<td colspan="4">
      		
      		<table width="100%" cellpadding="0" cellspacing="0">
			<tr> 
                <td> 
					
					<table width="100%" bgcolor="#99CCFF">
                    <tr bgcolor="#99CCFF"> 
						<td width="10%"><div align="center"><strong>Documento</strong></td>
						<td width="13%"><div align="center"><strong>Dt. Pag.</strong></div></td>
                        <td width="10%"><div align="center"><strong>Localidade</strong></div></td>
                        <td width="10%"><div align="center"><strong>Imóvel</strong></div></td>
                        <td width="10%"><div align="center"><strong>Cliente</strong></div></td>
                        <td width="10%"><div align="center"><strong>Ref.Pagto</strong></div></td>
                        <td width="10%"><div align="center"><strong>Débito</strong></div></td>
                        <td width="13%"><div align="center"><strong>Vl. Pag.</strong></div></td>
                        <td width="13%"><div align="center"><strong>Situação</strong></div></td>
                    </tr>
                    </table>
					
				</td>
            </tr>
            </table>

			<div style="width: 100%; height: 50; overflow: auto;">

			<table width="100%" cellpadding="0" cellspacing="0">
            <tr> 
				<td> 
				
					<% String cor = "#FFFFFF";%>
				
					<table width="100%" align="center" bgcolor="#99CCFF">
				
					<logic:iterate name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="colecaoPagamentos" id="pagamento" type="Pagamento">
                            
										
							<%	if (cor.equalsIgnoreCase("#FFFFFF")){
									cor = "#cbe5fe";%>
									<tr bgcolor="#FFFFFF">
							<%} else{
									cor = "#FFFFFF";%>
									
									<tr bgcolor="#cbe5fe">
							<%}%> 
							
								<td align="center" width="10%">
									<logic:present name="pagamento" property="documentoTipo">
										<bean:write name="pagamento" property="documentoTipo.descricaoAbreviado"/>
									</logic:present>
									<logic:notPresent name="pagamento" property="documentoTipo">
										&nbsp;
									</logic:notPresent>
								</td>
								<td width="13%">
									<div align="center">
									<logic:present name="pagamento" property="dataPagamento">
										<bean:write name="pagamento" property="dataPagamento" formatKey="date.format"/>
									</logic:present>
									<logic:notPresent name="pagamento" property="dataPagamento">
										&nbsp;
									</logic:notPresent>
									</div>
								</td>
								<td width="10%">
									<div align="center">
									<logic:present name="pagamento" property="localidade">
										<bean:write name="pagamento" property="localidade.id"/>
									</logic:present>
									<logic:notPresent name="pagamento" property="localidade">
										&nbsp;
									</logic:notPresent>
									</div>
								</td>
								<td width="10%">
									<div align="center">
									<logic:present name="pagamento" property="imovel">
										<bean:write name="pagamento" property="imovel.id"/>
									</logic:present>
									<logic:notPresent name="pagamento" property="imovel">
										&nbsp;
									</logic:notPresent>
									</div>
								</td>
								<td width="10%">
									<div align="center">
									<logic:present name="pagamento" property="cliente">
										<bean:write name="pagamento" property="cliente.id"/>
									</logic:present>
									<logic:notPresent name="pagamento" property="cliente">
										&nbsp;
									</logic:notPresent>
									</div>
								</td>
								<td width="10%">
									<div align="center">
									<logic:present name="pagamento" property="anoMesReferenciaPagamento">
										<logic:notEqual name="pagamento" property="anoMesReferenciaPagamento" value="0">
											<%= Util.formatarAnoMesParaMesAno(pagamento.getAnoMesReferenciaPagamento()) %>
										</logic:notEqual>
										<logic:equal name="pagamento" property="anoMesReferenciaPagamento" value="0">
											&nbsp;
										</logic:equal>
									</logic:present>
									<logic:notPresent name="pagamento" property="anoMesReferenciaPagamento">
										&nbsp;
									</logic:notPresent>	
									</div>
								</td>
								<td width="10%">
									<div align="center">
									<logic:present name="pagamento" property="debitoTipo">
										<bean:write name="pagamento" property="debitoTipo.id"/>
									</logic:present>
									<logic:notPresent name="pagamento" property="debitoTipo">
										&nbsp;
									</logic:notPresent>
									</div>
								</td>
								<td width="13%">
									<div align="right">
									<logic:present name="pagamento" property="valorPagamento">
										<bean:write name="pagamento" property="valorPagamento" formatKey="money.format"/>
									</logic:present>
									<logic:notPresent name="pagamento" property="valorPagamento">
										&nbsp;
									</logic:notPresent>
									</div>
								</td>
								<td width="13%">
									<div align="center">
									<logic:present name="pagamento" property="pagamentoSituacaoAtual">
										<bean:write name="pagamento" property="pagamentoSituacaoAtual.descricaoAbreviada"/>
									</logic:present>
									<logic:notPresent name="pagamento" property="pagamentoSituacaoAtual">
										&nbsp;
									</logic:notPresent>
									</div>
								</td>
							</tr>
							

						</logic:iterate>
						
					</table>
				</td>
            </tr>

			</table>
			</div>

      	</td>
	  </tr> 
	  </logic:present>
	  
	  <logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="numeroCartao">
      <tr>
          <td width="35%" height="24"><strong>Número do Cartão:</strong></td>
          <td colspan="3"><html:text property="numeroCartao" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.numeroCartao}"/></td>
      </tr>
	  </logic:present>
	  
	  <logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="dataTransacao">
      <tr>
          <td width="35%" height="24"><strong>Data da Transação:</strong></td>
          <td colspan="3"><html:text property="dataTransacao" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.dataTransacao}"/></td>
      </tr>
	  </logic:present>
	  
	  <logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="valorTransacao">
      <tr>
          <td width="35%" height="24"><strong>Valor da Transação:</strong></td>
          <td colspan="3"><html:text property="valorTransacao" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.valorTransacao}"/></td>
      </tr>
	  </logic:present>
	  
	  <logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="numeroParcela">
      <tr>
          <td width="35%" height="24"><strong>Parcela:</strong></td>
          <td colspan="3"><html:text property="numeroParcela" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.numeroParcela}"/></td>
      </tr>
	  </logic:present>
	  
	  <logic:present name="dadosConteudoRegistroMovimentoArrecadadorHelper" property="numeroParcelaDebito">
      <tr>
          <td width="35%" height="24"><strong>Quantidade de Parcelas:</strong></td>
          <td colspan="3"><html:text property="numeroParcelaDebito" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.dadosConteudoRegistroMovimentoArrecadadorHelper.numeroParcelaDebito}"/></td>
      </tr>
	  </logic:present>
		
	  <tr>
         <td colspan="4" align="left">
         	<%--<INPUT type="button" onclick="window.close();" name="botaoVoltar" class="bottonRightCol" value="Fechar" tabindex="2" style="width: 70px;">--%>
         	<input type="button" name="ButtonReset" class="bottonRightCol" value="Voltar" onclick="javascript:history.back();">
         </td>
      </tr>
      </table>

	  </html:form>
      <p>&nbsp;</p></td>
  </tr>
</table>
</body>
</html:html>

