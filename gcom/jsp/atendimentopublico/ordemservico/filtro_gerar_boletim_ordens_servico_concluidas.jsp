<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false" 
	formName="GerarBoletimOrdensServicoConcluidasActionForm" staticJavascript="false" dynamicJavascript="true"/>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">

	function validarFiltro() {
		var form = document.forms[0];
		
		if (form.idFirma.selectedIndex == 0) {
			form.idFirma.focus();
    		alert('Infome a Firma.');
    		return;
    	}else if (form.idLocalidade.value == "") {
			form.idLocalidade.focus();
    		alert('Infome a Localidade.');
    		return;
    	}else if (!validateInteger(form)) {
    		return false;
    	}else if (!validateCaracterEspecial(form)) {
    		return false;
    	}else if (form.anoMesReferenciaEncerramento.value == "") {
    		form.anoMesReferenciaEncerramento.focus();
    		alert('Infome o Mês/Ano de Referência do Encerramento.');
    		return;
		}else {
			if ( validateMesAno(form)) {
				form.nomeFirma.value = form.idFirma.options[form.idFirma.selectedIndex].text;
				submeterFormPadrao(form);
			}else {
				return;
			}
		}
		
	}
	
	function IntegerValidations () { 
		this.aa = new Array("idLocalidade", "A Localidade deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	}
	
	function caracteresespeciais () { 
		this.aa = new Array("idLocalidade", "A Localidade possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    }
	
	function MesAnoValidations () { 
		this.aa = new Array("anoMesReferenciaEncerramento", "Mês/Ano de Referência do Encerramento inválido. ", new Function ("varName", " return this[varName];"));
	}
	
	function verificarDataFiscalizacao() {
		var form = document.forms[0];
		
		if (form.dataFiscalizacao1.value != "" && form.dataFiscalizacao2.value != "" && 
		    form.dataFiscalizacao3.value == "" && (form.codigoFiscalizacao[1].checked ||
		    form.codigoFiscalizacao[2].checked) ) {
		    
			form.dataFiscalizacao3.readOnly = false;
			form.dataFiscalizacao3.style.backgroundColor = '';
			
			form.dataFiscalizacao1.readOnly = true;
			form.dataFiscalizacao1.style.backgroundColor = '#EFEFEF';
			form.dataFiscalizacao2.readOnly = true;
			form.dataFiscalizacao2.style.backgroundColor = '#EFEFEF';
		
		}else if (form.dataFiscalizacao1.value != "" && form.dataFiscalizacao2.value == "" && 
		    form.dataFiscalizacao3.value == "" && (form.codigoFiscalizacao[1].checked ||
		    form.codigoFiscalizacao[2].checked) ) {
		    
			form.dataFiscalizacao2.readOnly = false;
			form.dataFiscalizacao2.style.backgroundColor = '';
			
			form.dataFiscalizacao1.readOnly = true;
			form.dataFiscalizacao1.style.backgroundColor = '#EFEFEF';
			form.dataFiscalizacao3.readOnly = true;
			form.dataFiscalizacao3.style.backgroundColor = '#EFEFEF';
		
		}else if (form.dataFiscalizacao1.value == "" && form.dataFiscalizacao2.value == "" && 
		    form.dataFiscalizacao3.value == "" && (form.codigoFiscalizacao[1].checked ||
		    form.codigoFiscalizacao[2].checked) && form.idOrdemServico.value != "") {
		    
			form.dataFiscalizacao1.readOnly = false;
			form.dataFiscalizacao1.style.backgroundColor = '';
			
			form.dataFiscalizacao2.readOnly = true;
			form.dataFiscalizacao2.style.backgroundColor = '#EFEFEF';
			form.dataFiscalizacao3.readOnly = true;
			form.dataFiscalizacao3.style.backgroundColor = '#EFEFEF';
		
		}else if (form.dataFiscalizacao1.value != "" && form.dataFiscalizacao2.value != "" && 
		    form.dataFiscalizacao3.value != "" && (form.codigoFiscalizacao[1].checked ||
		    form.codigoFiscalizacao[2].checked) && form.idOrdemServico.value != "") {
		    
			form.dataFiscalizacao1.readOnly = true;
			form.dataFiscalizacao1.style.backgroundColor = '#EFEFEF';
			form.dataFiscalizacao2.readOnly = true;
			form.dataFiscalizacao2.style.backgroundColor = '#EFEFEF';
			form.dataFiscalizacao3.readOnly = true;
			form.dataFiscalizacao3.style.backgroundColor = '#EFEFEF';
		}
	}
	
	function habilitaDesabilitaDataFiscalizacao() {
		var form = document.forms[0];
		
		if (!form.codigoFiscalizacao[0].disabled) {
			form.dataFiscalizacao1.readOnly = false;
			form.dataFiscalizacao1.style.backgroundColor = '';
		}
	}
	
	function desabilitaDataFiscalizacao() {
		var form = document.forms[0];
		
		form.dataFiscalizacao1.readOnly = true;
		form.dataFiscalizacao1.style.backgroundColor = '#EFEFEF';
		form.dataFiscalizacao1.value = "";
		form.dataFiscalizacao2.readOnly = true;
		form.dataFiscalizacao2.style.backgroundColor = '#EFEFEF';
		form.dataFiscalizacao2.value = "";
		form.dataFiscalizacao3.readOnly = true;
		form.dataFiscalizacao3.style.backgroundColor = '#EFEFEF';
		form.dataFiscalizacao3.value = "";
		
		form.idUsuario.readOnly = true;
		form.idUsuario.style.backgroundColor = '#EFEFEF';
		form.idUsuario.value = "";
	}
	
	function verificaFuncionario() {
		var form = document.forms[0];
		
		if (form.idOrdemServico.value != "" &&
			(form.codigoFiscalizacao[1].checked || form.codigoFiscalizacao[2].checked ) ) {

			form.idUsuario.readOnly = false;
			form.idUsuario.style.backgroundColor = '';
		}else {
			form.idUsuario.readOnly = true;
			form.idUsuario.style.backgroundColor = '#EFEFEF';
		}
	}
	
	function verificaDataEncerramentoBoletim() {
		var form = document.forms[0];
		var retorno = false;
		
		if (form.dataEncerramentoBoletim.value != "") {
			// Verifica a situacao do Boletim
			if (form.codigoFiscalizacao[1].checked) {
				alert('Esta Ordem já foi Encerrada e Aprovada. Não pode ser Alterada.');
				form.ButtonAtualizar.disabled = true;
			}
		}else if (form.codigoFiscalizacao[1].checked) {
			alert('Esta Ordem já está Aprovada. Não pode ser Alterada.');
			form.ButtonAtualizar.disabled = true;
		}else {
			//form.ButtonAtualizar.disabled = false;
			retorno = true;
		}
		return retorno;
	}
	
	function validarForm(){
   		var form = document.forms[0];
		
		if (form.idOrdemServico.value != "") {
			if (!form.codigoFiscalizacao[0].checked) {
				// Verifica as Datas de Fiscalizacao
				if (!form.dataFiscalizacao1.readOnly &&
					(form.codigoFiscalizacao[1].checked || form.codigoFiscalizacao[2].checked) ) {
					
					if (form.dataFiscalizacao1.value == "") {
						form.dataFiscalizacao1.focus();
			    		alert('Infome a 1a Data de Fiscalização.');
		    			return;
					}
				}else if (form.dataFiscalizacao1.value != "" && form.dataFiscalizacao1.readOnly &&
						  (form.codigoFiscalizacao[1].checked || form.codigoFiscalizacao[2].checked) ) {
						  
					if (form.dataFiscalizacao2.value == "") {
						form.dataFiscalizacao2.focus();
			    		alert('Infome a 2a Data de Fiscalização.');
		    			return;
					}
				}else if (form.dataFiscalizacao2.value != "" && form.dataFiscalizacao2.readOnly &&
						  (form.codigoFiscalizacao[1].checked || form.codigoFiscalizacao[2].checked) ) {
						  
					if (form.dataFiscalizacao3.value == "") {
						form.dataFiscalizacao3.focus();
			    		alert('Infome a 3a Data de Fiscalização.');
		    			return;
					}
				}
				
				if (form.codigoFiscalizacao[1].checked || form.codigoFiscalizacao[2].checked) {
					if (form.idUsuario.value == "") {
						form.idUsuario.focus();
			    		alert('Infome o Funcionário.');
		    			return;
					}
				}
				
				//form.action = "exibirAtualizarOrdemServicoConcluidaAction.do?atualizar=sim";
				//form.submit();
				submeterFormPadrao(form);
			}else {
				alert("Para Atualizar os dados da Fiscalização da Ordem informe a Situação Aprovado ou Reprovado.");
				return;
			}
		}else {
			form.idOrdemServicoPesquisado.focus();
			alert("Para Atualizar os dados da Fiscalização Informe uma Ordem de Servico.");
			return;
		}
    }
    
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="window.focus();javascript:setarFoco('${requestScope.nomeCampo}');" >

	<html:form action="/exibirResumoBoletimOrdensServicoConcluidasAction" method="post"
		name="GerarBoletimOrdensServicoConcluidasActionForm"
		type="gcom.gui.atendimentopublico.ordemservico.GerarBoletimOrdensServicoConcluidasActionForm">
		
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
						<td class="parabg">Filtrar Boletim de Ordens de Servi&ccedil;o Conclu&iacute;das</td>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
				</table>
				<p>&nbsp;</p>
				
				<table width="100%" border="0">
					<tr>
						<td colspan="2">Para selecionar as ordens de servi&ccedil;o para
						 gera&ccedil;&atilde;o do boletim, informe os dados abaixo:</td>
					</tr>
					
					<tr><td colspan="2"><hr></td></tr>
					
					<tr>
						<td width="120" nowrap="nowrap"><strong>Firma:&nbsp;<font color="#FF0000">*</font></strong></td>
						<td align="left" width="100%">
							<html:select property="idFirma" tabindex="0">
								<html:option value="-1">&nbsp;</html:option>
								<html:options collection="colecaoFirma"
									property="id" labelProperty="descricao" />
							</html:select>
							<html:hidden property="nomeFirma"/>
						</td>
					</tr>
					
					<tr>
						<td><strong>Localidade:&nbsp;<font color="#FF0000">*</font></strong></td>
						<td>
							<html:text tabindex="1" maxlength="3" property="idLocalidade" size="5"
								onkeypress="form.target=''; validaEnter(event, 'exibirFiltrarBoletimOrdensServicoConcluidasAction.do?objetoConsulta=1', 'idLocalidade');"/>
							<a href="javascript:chamarPopup1('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '');" id="btPesqLocalidadeInicial">
								<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar" /></a>
							<logic:present name="corLocalidadeOrigem">
								<logic:equal name="corLocalidadeOrigem" value="exception">
									<html:text property="nomeLocalidade" size="35" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:equal>
		
								<logic:notEqual name="corLocalidadeOrigem" value="exception">
									<html:text property="nomeLocalidade" size="35" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEqual>
							</logic:present>
							
							<logic:notPresent name="corLocalidadeOrigem">
								<logic:empty name="GerarBoletimOrdensServicoConcluidasActionForm"
									property="idLocalidade">
									<html:text property="nomeLocalidade" value="" size="35" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:empty>
	
								<logic:notEmpty name="GerarBoletimOrdensServicoConcluidasActionForm"
									property="idLocalidade">
									<html:text property="nomeLocalidade" size="35" readonly="true"
										style="background-color:#EFEFEF; border:0; color: 	#000000" />
								</logic:notEmpty>
							</logic:notPresent>
							
							<a href="javascript:limparBorrachaOrigem(1);">
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
							</a>
						</td>
					</tr>
					
					<tr>
						<td width="200"><strong>Refer&ecirc;ncia do Encerramento:&nbsp;<font color="#FF0000">*</font></strong></td>
						<td align="left">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td><html:text property="anoMesReferenciaEncerramento" size="6" maxlength="7" onkeyup="mascaraAnoMes(this, event);" tabindex="2"></html:text><strong>&nbsp;MM/AAAA</strong></td>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr>
						<td height="10" colspan="3"><hr></td>
					</tr>
					
					<tr>
						<td align="left" colspan="2">
							<strong><font color="#FF0000">*</font></strong>Campos obrigat&oacute;rios
						</td>
					</tr>
	
					<tr>
						<td height="190" valign="top">
							<input type="button"
								name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</td>
						<td align="right" valign="top">
							<input type="button"
								name="ButtonFiltrar" class="bottonRightCol" value="Filtrar"
								onClick="javascript:validarFiltro();" tabindex="3">
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