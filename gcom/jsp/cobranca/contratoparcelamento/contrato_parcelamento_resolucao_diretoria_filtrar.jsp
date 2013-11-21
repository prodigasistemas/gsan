<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@page isELIgnored="false"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js">
</script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
	
	
	<script language="JavaScript">
			
		function desfazer(){
		    document.forms[0].reset();
		}
		
		function limpaDataFinal(){
		var form = document.forms[0];
		  if(form.dataVigenciaFinal.value == ''){
		   form.dataVigenciaFinal.value = '';
		  }
		
		}
	
		function marcaAtualizacao(){
			document.forms[0].indicadorAtualizar.checked = true;
		}
		
		
		
		function replicarRetorno(){
			 var form = document.forms[0]; 
			 
			 form.dataVigenciaFinal.value = form.dataVigenciaInicial.value;
		}
	</script>
	
	
<body leftmargin="5" topmargin="5" onload="marcaAtualizacao();">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp"%>

<html:form action="/filtrarResolucaoDiretoriaContratoParcelamentoAction.do"
		name="FiltrarResolucaoDiretoriaContratoParcelamentoActionForm"
		type="gcom.gui.cobranca.contratoparcelamento.FiltrarResolucaoDiretoriaContratoParcelamentoActionForm"
		method="post">

<table width="770" border="0" cellspacing="5" cellpadding="0">
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


		<td valign="top" class="centercoltext"><!--Início Tabela Reference a Páginação da Tela de Processo-->
		<table>
			<tr>
				<td></td>
			</tr>
		</table>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img border="0" src="imagens/parahead_left.gif"
					alt="" /></td>
				<td class="parabg">Filtrar Resolu&ccedil;&atilde;o de Diretoria para
				Contrato de Parcelamento por Cliente</td>
				<td width="11" valign="top"><img border="0"
					src="imagens/parahead_right.gif" alt="" /></td>
			</tr>
		</table>
		<!--Fim Tabela Reference a Páginação da Tela de Processo-->
		<p>&nbsp;</p>
		<table width="100%" border="0">
			<tr>
				<td colspan="2">Para manter a(s) resolu&ccedil;&atilde;o(&otilde;es)
				de diretoria, informe os dados abaixo:</td>
				<td width="90">
				<html:checkbox property="indicadorAtualizar" ></html:checkbox>
				<strong>Atualizar</strong></td>
			</tr>
			<tr>
				<td width="150"><strong>N&uacute;mero da RD:</strong></td>
				<td colspan="2"><html:text property="numero" size="15"
					value="${contratoCadastrar.numero}" maxlength="15" /></td>
			</tr>
			<tr>
				<td height="25"><strong>Assunto da RD<font color="#000000">:</font></strong></td>
				<td height="25" colspan="2"><html:text property="assunto" size="50" value="${contratoCadastrar.assunto}"
							maxlength="50" />
				<div align="left"></div>
				</td>
			</tr>
			<tr>
						<td height="25"><strong>Per&iacute;odo de Vig&ecirc;ncia da RD:</strong></td>
						<td align="right">
						<div align="left">
							<html:text 
							onkeyup="mascaraData(this, event); replicarRetorno(); "
							property="dataVigenciaInicial" size="10" maxlength="10" /> <a
							href="javascript:abrirCalendario('FiltrarResolucaoDiretoriaContratoParcelamentoActionForm', 'dataVigenciaInicial')">
						<img border="0"
							src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
						a
						&nbsp; <html:text onkeyup="mascaraData(this, event); "
							property="dataVigenciaFinal" size="10" maxlength="10" /> <a
							href="javascript:abrirCalendario('FiltrarResolucaoDiretoriaContratoParcelamentoActionForm', 'dataVigenciaFinal')"><img
							border="0"
							src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
						dd/mm/aaaa
						
						</div>
						</td>
			</tr>
			<tr>
				<td height="25">&nbsp;</td>
				<td width="308" height="25">&nbsp;</td>
				<td align="right">
				<div align="left"></div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
				<p>&nbsp;</p>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<div align="left"><input type="button" name="limpar" onclick="javascritp:desfazer();"
					class="bottonRightCol" value="Limpar"></div>
				</td>
				<td>
				<div align="right"><input type="submit" name="Submit"
					class="bottonRightCol" value="Filtrar"></div>
				</td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		</td>
	</tr>

</table>

</html:form>
<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:html>

