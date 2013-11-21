<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css"type="text/css">
<html:javascript staticJavascript="false" formName="EmitirOrdemFiscalizacaoForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'imovel') {
      form.action='exibirEmitirOrdemFiscalizacaoAction.do?pesquisarImovel=sim&idImovel='+codigoRegistro;
      form.submit();
    }
}

function limparPesquisaImovel() {
    var form = document.forms[0];
      form.situacaoAguaDebitos.value = "";
      form.situacaoEsgotoDebitos.value = "";
      
      form.action='exibirEmitirOrdemFiscalizacaoAction.do?menu=sim';
      form.submit();
}

function validarForm(form){   
	 
   if(validarEmitirOrdemFiscalizacaoForm(form)){
           form.submit();
   }    
}

function validarEmitirOrdemFiscalizacaoForm(form){
	var msg = '';
    
	if(form.matriculaImovel.value != undefined
					&& form.matriculaImovel.value != ''){
		return true;	   
	}else{

		if(form.matriculaImovel.value == undefined
				|| form.matriculaImovel.value == ''){
			   msg = msg + 'Informe Imóvel. \n';
		}
		alert(msg);
		return false;
	}	
}

function abrirPopupComValidacao(caminho, altura, largura,form) {
		
	if(validarEmitirOrdemFiscalizacaoForm(form)){
		abrirPopupDeNome(caminho+document.forms[0].tipoHidden.value, altura, largura, 'Pesquisar', 'yes');
	}
}


/* funcao que abre um popup com o caminho informado
   EX: abrirPopup(String , int, int)
   OBS - O popup sera aberto no centro da tela
*/
function abrirPopupDeNome(caminho, altura, largura, nome, status) {

   //Para abrir o popup centralizado ======
	var height = window.screen.height - 160;
	var width = window.screen.width;
	var top = (height - altura)/2;
	var left = (width - largura)/2;
   //======================================

   window.open(caminho,nome,'top=' + top + ',left='+ left +',location=no,screenY=0,screenX=0,menubar=no,status=' + status + ',toolbar=no,scrollbars=yes,resizable=no,width=' + largura + ',height=' + altura);
}

function alterarValorHidden(form,aux){

	form.tipoHidden.value = aux;
	
}


</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
	
<html:form 
	action="/emitirOrdemServicoFiscalizacaoAction.do" 
	name="EmitirOrdemFiscalizacaoForm"
	type="gcom.gui.atendimentopublico.ordemservico.EmitirOrdemFiscalizacaoForm" 
	method="post">


	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		

		<tr>
			<td width="115" valign="top" class="leftcoltext">
			<div align="center">
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
		
				
			<html:hidden property="indicadorPermitirGerarOs"/>
			<html:hidden property="indicadorPermitirEmitir"/>
			<html:hidden property="tipoHidden" value="1"/>
			
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
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Emitir Ordem de Fiscalização</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<table width="100%" border="0">
			<tr>
				<td>
					<table width="100%" border="0">
						<tr>
							<td>Para emitir uma ordem de fiscalização informe os dados abaixo:</td>
						</tr>
					</table>
					<table width="100%" border="0">
				
					
						<tr bgcolor="#cbe5fe">
           					<td align="center" colspan="3">
                  		<div id="layerShowLocal">

	                   		<table width="100%" border="0" bgcolor="#99CCFF">
		    					<tr bgcolor="#99CCFF">
	                     			<td align="center">
                    					<span class="style2">
                     					
                     						<b>Dados Gerais</b>
                     			
                    					</span>
	                     			</td>
	                    		</tr>
		
								<tr bgcolor="#cbe5fe">
									<td>
										<table border="0" width="100%">
													<tr>	
														<td>
															<table border="0" width="100%">
																
															
															
																<tr>
																	<td width="20%">
																		<strong>Matrícula:<font color="#FF0000">*</font></strong>
																	</td>
																	<td width="80%" colspan="2">
																		<html:text maxlength="9" property="matriculaImovel"
																				size="10"
																				onkeypress="javascript:validaEnterComMensagem(
																				event, 'exibirEmitirOrdemFiscalizacaoAction.do?pesquisarImovel=sim', 'matriculaImovel','Matrícula');
																				return isCampoNumerico(event);" />
																		<a href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);">
																			<img width="23" height="21" border="0"
																				src="<bean:message key="caminho.imagens"/>pesquisa.gif" 
																				title="Pesquisar Imóvel" /></a> 
																		<logic:present name="inscricaoImovelEncontrada" scope="session">
																			<html:text property="inscricaoImovel" readonly="true"
																				style="background-color:#EFEFEF; border:0; color: #000000"
																				size="30" maxlength="30" />
																		</logic:present> 
																		<logic:notPresent name="inscricaoImovelEncontrada" scope="session">
																			<html:text property="inscricaoImovel" readonly="true"
																				style="background-color:#EFEFEF; border:0; color: #FF0000"
																				size="30" maxlength="30" />
																		</logic:notPresent> 
																			<a href="javascript:limparPesquisaImovel();">
																				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
																				border="0" title="Limpar Imóvel" />
																			</a>
																	</td>			
																</tr>
																
																<tr>
																	<td width="20%">
																	<strong>Situação de Água:</strong>
																	</td>
																	<td>
																	<html:text property="situacaoAguaDebitos" readonly="true"
																		style="background-color:#EFEFEF; border:0; color: #000000"
																		size="15" maxlength="15" /></td>
																	<td><strong>Situação de Esgoto:</strong>
																	&nbsp;&nbsp;&nbsp;
																	<html:text property="situacaoEsgotoDebitos"
																		readonly="true"
																		style="background-color:#EFEFEF; border:0; color: #000000"
																		size="15" maxlength="15" /></td>
																</tr>
																
																<logic:equal name="EmitirOrdemFiscalizacaoForm" property="indicadorPermitirEmitir" value="sim">
																<tr>
																	<td colspan="3"><strong>Tipo de Medição:</strong>
																		&nbsp;&nbsp;&nbsp;&nbsp;
																		<html:radio name="EmitirOrdemFiscalizacaoForm" onchange="alterarValorHidden(document.forms[0],this.value);" property="tipo" value="1"/>Ligação de Água
																		<html:radio name="EmitirOrdemFiscalizacaoForm" onchange="alterarValorHidden(document.forms[0],this.value);" property="tipo" value="2"/>Poço
																		
																	</td>
																	
																</tr>
																</logic:equal>										
															</table>
															</td>
															</tr>
	
										</table>
										</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>							
						<tr>
							<td colspan="2">
								<table width="100%">
									
									<tr>
										<td colspan="3" valign="top">
											<logic:notEmpty name="EmitirOrdemFiscalizacaoForm" property="enderecoImovel">
												<table width="100%" cellpadding="0" cellspacing="0">
													<tr>
														<td>
															<table 
																width="100%" 
																border="0" 
																cellpadding="1" 
																cellspacing="0"
																bgcolor="#99CCFF" 
																bordercolor="#99CCFF">
																<tr>
																	<td align="center">
																		<strong>Endere&ccedil;o</strong>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>	
														<td>
															<table width="100%" align="center" bgcolor="#99CCFF">
																<tr bgcolor="#FFFFFF">
																	<td>
																	<div align="center">
																	<span id="endereco">
																	<bean:write name="EmitirOrdemFiscalizacaoForm" property="enderecoImovel" />
																	</span>
																	</div>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</logic:notEmpty>
										</td>
									</tr>				
							</table>
						</td>
					</tr>
					<tr>
						<td><p>&nbsp;</p></td>
						<td align="right">
							<div align="right"><strong><font color="#FF0000">*</font></strong>
							Campos obrigat&oacute;rios</div>
						</td>
					</tr>
					<tr>
					<td colspan="2" width="100%">
						<table width="100%">
							<tr>
								<td align="left" colspan="">
									<input type="button" name="Button"
										class="bottonRightCol" value="Limpar"
										onClick="window.location.href='/gsan/exibirEmitirOrdemFiscalizacaoAction.do?menu=sim'" />
									<input type="button" name="Button" class="bottonRightCol"
										value="Cancelar"
										onClick="window.location.href='/gsan/telaPrincipal.do'" />
									<logic:notEmpty name="EmitirOrdemFiscalizacaoForm" property="enderecoImovel">	
									<logic:equal name="EmitirOrdemFiscalizacaoForm" property="indicadorPermitirGerarOs" value="sim">
									<input type="button" 
										name="Button" 
										class="bottonRightCol"
										value="Gerar Ordem de Serviço"
										onclick="javascript:validarForm(document.forms[0]);"/>	
									</logic:equal>
									<logic:notEqual name="EmitirOrdemFiscalizacaoForm" property="indicadorPermitirGerarOs" value="sim">
									<input type="button" 
										name="Button" 
										class="bottonRightCol"
										value="Gerar Ordem de Serviço"
										disabled="disabled"
										onclick="javascript:validarForm(document.forms[0]);"/>
									</logic:notEqual>			
									</logic:notEmpty>	
								</td>
						   		<td align="right">
									<div align="right">
									<logic:notEmpty name="EmitirOrdemFiscalizacaoForm" property="enderecoImovel">
									<logic:equal name="EmitirOrdemFiscalizacaoForm" property="indicadorPermitirEmitir" value="sim">
										<input type="button" 
											name="Button" 
											class="bottonRightCol"
											value="Emitir"
											onclick="javascript:abrirPopupComValidacao('exibirEmitirOrdemFiscalizacaoPopupAction.do?tipo=', 765, 790,document.forms[0]);"/>		
									</logic:equal>
									<logic:notEqual name="EmitirOrdemFiscalizacaoForm" property="indicadorPermitirEmitir" value="sim">
										<input type="button" 
											name="Button" 
											class="bottonRightCol"
											value="Emitir"
											disabled="disabled"
											onclick="javascript:abrirPopupComValidacao('exibirEmitirOrdemFiscalizacaoPopupAction.do', 765, 790,document.forms[0]);"/>		
									</logic:notEqual>
									</logic:notEmpty>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
	</table>
	</td>
	</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
