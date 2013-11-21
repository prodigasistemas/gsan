<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@page import="gcom.gui.faturamento.FaturamentoAtividadeAtualizarHelp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FaturamentoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
function validaDataCompleta(data, event){
		if(mascaraData(data, event)){
			return false;
		}
}
</script>

<script language="JavaScript">
function validaData(form){
  var form = document.FaturamentoActionForm;
  var mesAno = form.mesAno.value;
  if(mascaraAnoMes(mesAno)){
     return false;
  }
}

function validarForm(){
	var form = document.FaturamentoActionForm;
	if(validateFaturamentoActionForm(form) && validarCamposDinamicos(form)){
		submeterFormPadrao(form);
	}
}

function validarCamposDinamicos(form){
 
 	var camposValidos = true;
 
 	
 	if(!verificaAnoMes(form.mesAno)){
		camposValidos = false;
	}
 	if(camposValidos == true){
	 	for (i=0; i < form.elements.length; i++) {
	    	
	    	if (form.elements[i].type == "text" && form.elements[i].id.length > 1){
	    		if(form.elements[i].value != ""){	
					if(form.elements[i].id == "data"){
						if(!verificaData(form.elements[i])){
							camposValidos = false;
							break;
						}
					}
				}else{
					camposValidos = true;
				}
			}
		}
	}
		
	return camposValidos;
}

function habilitaCampo(form){
	form.mesAno.disabled = "false";
	return true;
}

function desabilitaCampo(form){
	form.mesAno.disabled = "true";
}

function reload(reload,id,data){
	var form = document.FaturamentoActionForm;

	var indice ='i'+id;
	
	if(reload=='sim'){
		
		document.getElementById(indice).value = data;
	}
}

function restart() {

	document.forms[nomeForm].elements[nomeCampo].value = '' + padout(day) + '/' + padout(month - 0 + 1) + '/' + year;
	document.forms[nomeForm].elements[nomeCampo].focus();
   
	calcularQuantidadeDias(document.forms[nomeForm].elements[nomeCampo].name.substring(1));
}

function enviaId(id) {
	var form = document.FaturamentoActionForm;
	
	form.auxIDHidden.value = id;
}

function calcularQuantidadeDias(idAtividade){
	
	var dataPrevistaDigitada = eval('i' + idAtividade); 
	var qtdDiasAtividade = eval('qtdDias' + idAtividade);
	
	if (dataPrevistaDigitada.value.length == 10){
		
		if (validaDataSemMensagem(dataPrevistaDigitada)){
			
			var dataAtividadeRealizacaoMesAnterior = eval('dataAnterior' + idAtividade); 
			
			
			if (dataAtividadeRealizacaoMesAnterior.value.length == 10 &&
				comparaData(dataPrevistaDigitada.value, ">", dataAtividadeRealizacaoMesAnterior.value)){
				
				qtdDiasAtividade.innerHTML = diasEntreDatas(dataAtividadeRealizacaoMesAnterior.value,
				dataPrevistaDigitada.value);
			}
			else{
			
				qtdDiasAtividade.innerHTML = "0";
			}
		}
	}
	else{
		qtdDiasAtividade.innerHTML = "0";
	}
}

function habilitarCalendario(idAtividade){

	var atividade = eval('i' + idAtividade);
	
	if (!atividade.readOnly){
		abrirCalendario('FaturamentoActionForm', 'n' + idAtividade);
	}
}

</script>
</head>

<body leftmargin="5" topmargin="5" onload="desabilitaCampo(document.forms[0]);">
<html:form action="/atualizarFaturamentoCronogramaAction"
	name="FaturamentoActionForm"
	type="gcom.gui.faturamento.FaturamentoActionForm" method="post"
	onsubmit="return habilitaCampo(this) && validarCamposDinamicos(this) && validateFaturamentoActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<html:hidden property="auxIDHidden" />

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="135" valign="top" class="leftcoltext">

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


			<td width="600" valign="top" class="centercoltext"><!--In&iacute;cio Tabela Reference a P&aacute;gina&ccedil;&atilde;o da Tela de Processo-->
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
			       <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
                   <td class="parabg">Atualizar Cronograma de Faturamento</td>
                   <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
                </tr>
			</table>
			<!--Fim Tabela Reference a P&aacute;gina&ccedil;&atilde;o da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">

				<tr>
					<td colspan="2">Para atualizar o(s) cronograma(s) de faturamento, informe os dados abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right">
							<a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoCronogramaAtualizar', 500, 700);">
								<span style="font-weight: bold">
									<font color="#3165CE">Ajuda</font>
								</span>
							</a>
						</td>									
					</logic:present>
					
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right">
							<span style="font-weight: bold">
								<font color=#696969><u>Ajuda</u></font>
							</span>
						</td>									
					</logic:notPresent>
				</tr>
				</table>
				<table width="100%" border="0">
				<tr>
					<td><strong>Grupo:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="idGrupoFaturamento" disabled="true">
						<html:options collection="faturamentoGrupos" 
									  labelProperty="descricao" 
									  property="id" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td><strong>Mês/Ano:<font color="#FF0000">*</font></strong></td>
					<td><html:text maxlength="7" 
								   property="mesAno" 
								   onkeypress="return isCampoNumerico(event);"
								   size="7"/> &nbsp; mm/aaaa
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campo Obrigat&oacute;rio</td>
				</tr>
				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
					<td></td>
				</tr>

			</table>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td height="0">
					<table width="100%" bgcolor="#99CCFF">
						<!--header da tabela interna -->
						<tr bordercolor="#FFFFFF">
							<td width="8%" bgcolor="#90c7fc" align="center">
							<div align="center">
								<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
								<strong>
									Comandar
								</strong>
								</font>
							</div>
							</td>
							<td width="16%" bgcolor="#90c7fc" align="center">
							<div align="center">
								<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
								<strong>
									Atividade
								</strong>
								</font>
							</div>
							</td>
							<td width="18%" bgcolor="#90c7fc" align="center">
							<div align="center">
								<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
								<strong>
									Predecessora
								</strong>
								</font>
							</div>
							</td>
							<td width="10%" bgcolor="#90c7fc" align="center">
							<div align="center">
								<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
									<strong>Obrigat&oacute;ria</strong>
								</font>		
							</div>
							</td>
							<td width="17%" bgcolor="#90c7fc" align="center">
							<div align="center">
								<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
								<strong>
									Data Prevista
								</strong>
								</font>
							</div>
							</td>
							<td width="17%" bgcolor="#90c7fc" align="center">
							<div align="center">
								<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
								<strong>
									Data Realização
								</strong>
								</font>
							</div>
							</td>
							
							<td width="20%" bgcolor="#90c7fc" align="center">
							<div align="center">
							<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
							<strong>Qtd. Dias</strong>
							</font>
							</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<logic:present name="colecaoFaturamentoAtividadeCronograma">
					<tr>
						<td height="126">
						<div style="width: 100%; height: 100%; overflow: auto;">
						<table width="100%" align="center" bgcolor="#99CCFF">
							<!--corpo da segunda tabela-->
							<%int cont = 0;%>
							<logic:notEmpty name="colecaoFaturamentoAtividadeCronograma">
								<logic:iterate name="colecaoFaturamentoAtividadeCronograma"
									id="faturamentoAtividadeCronogramas" type="FaturamentoAtividadeAtualizarHelp" >
									<%cont = cont + 1;
			if (cont % 2 == 0) {%>
									<tr bgcolor="#cbe5fe">									
										<%} else {

			%>
									<tr bgcolor="#FFFFFF">

										<%}%>
										
										<td width="9%">
											<logic:notPresent name="desabilitaCheck" scope="session">
												<logic:empty name="faturamentoAtividadeCronogramas" property="faturamentoAtividadeCronograma.dataRealizacao">
													<div align="center">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
															<logic:present name="faturamentoAtividadeCronogramas" property="faturamentoAtividadeCronograma.faturamentoAtividade.indicadorPossibilidadeComandoAtividade">
							                          	  		<logic:equal name="faturamentoAtividadeCronogramas" property="faturamentoAtividadeCronograma.faturamentoAtividade.indicadorPossibilidadeComandoAtividade" value="1">
							                                   		<logic:present name="faturamentoAtividadeCronogramas" property="faturamentoAtividadeCronograma.comando">
																		<input type="checkbox" name="c${faturamentoAtividadeCronogramas.faturamentoAtividadeCronograma.id}" value="1" checked="checked"/>
																	</logic:present>
																	<logic:notPresent name="faturamentoAtividadeCronogramas" property="faturamentoAtividadeCronograma.comando">
																		<input type="checkbox" name="c${faturamentoAtividadeCronogramas.faturamentoAtividadeCronograma.id}" value="1"/>
																	</logic:notPresent>
			            					                	</logic:equal>
			            					                	<logic:equal name="faturamentoAtividadeCronogramas" property="faturamentoAtividadeCronograma.faturamentoAtividade.indicadorPossibilidadeComandoAtividade" value="3">
			            					                		<input type="checkbox" name="c${faturamentoAtividadeCronogramas.faturamentoAtividadeCronograma.id}" value="1" checked="checked"/>
			            					                	</logic:equal>
		                            						</logic:present>
														</font>
													</div>
												</logic:empty>
												<logic:notEmpty name="faturamentoAtividadeCronogramas" property="faturamentoAtividadeCronograma.dataRealizacao">
													<div align="center">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
															<logic:present name="faturamentoAtividadeCronogramas" property="faturamentoAtividadeCronograma.faturamentoAtividade.indicadorPossibilidadeComandoAtividade">
							                          	  		<logic:equal name="faturamentoAtividadeCronogramas" property="faturamentoAtividadeCronograma.faturamentoAtividade.indicadorPossibilidadeComandoAtividade" value="1">
							                                   		<logic:present name="faturamentoAtividadeCronogramas" property="faturamentoAtividadeCronograma.comando">
																		<input type="checkbox" name="c${faturamentoAtividadeCronogramas.faturamentoAtividadeCronograma.id}" value="1" checked="checked"/>
																	</logic:present>
																	<logic:notPresent name="faturamentoAtividadeCronogramas" property="faturamentoAtividadeCronograma.comando">
																		<input type="checkbox" name="c${faturamentoAtividadeCronogramas.faturamentoAtividadeCronograma.id}" value="1"/>
																	</logic:notPresent>
			            					                	</logic:equal>
		                            						</logic:present>
														</font>
													</div>
												</logic:notEmpty>
											</logic:notPresent>
										</td>
										
										<td width="18%">
										<div align="left">
											<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
												${faturamentoAtividadeCronogramas.faturamentoAtividadeCronograma.faturamentoAtividade.descricao}&nbsp;
											</font>
											<input type="hidden" id="dataAnterior${faturamentoAtividadeCronogramas.idFaturamentoAtividade}" value="${faturamentoAtividadeCronogramas.dataRealizacaoMesAnterior}">
										</div>
										</td>
										<td width="18%">
										<div align="left"><logic:present name="faturamentoAtividadeCronogramas"
											property="faturamentoAtividadeCronograma.faturamentoAtividade.faturamentoAtividadePrecedente">
											<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
											<logic:present name="faturamentoAtividadeCronogramas"
													property="faturamentoAtividadeCronograma.faturamentoAtividadePrecedente.descricao">
												<bean:write name="faturamentoAtividadeCronogramas"
													property="faturamentoAtividadeCronograma.faturamentoAtividadePrecedente.descricao" />
											</logic:present>
											</font>
										</logic:present> <logic:notPresent name="faturamentoAtividadeCronogramas"
											property="faturamentoAtividadeCronograma.faturamentoAtividade.faturamentoAtividadePrecedente">
                                   &nbsp;
                                  </logic:notPresent></div>
										</td>
										<td width="8%">
										<div align="center"><logic:equal name="faturamentoAtividadeCronogramas"
											property="faturamentoAtividadeCronograma.faturamentoAtividade.indicadorObrigatoriedadeAtividade"
											value="1">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
                                      SIM
                                      	</font>
                                    </logic:equal> <logic:notEqual
											name="faturamentoAtividadeCronogramas"
											property="faturamentoAtividadeCronograma.faturamentoAtividade.indicadorObrigatoriedadeAtividade"
											value="1">
											<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
                                      NÃO
                                      		</font>
                                    </logic:notEqual></div>
										</td>
										<td width="24%">
										<div align="center">
										<logic:empty name="faturamentoAtividadeCronogramas" property="faturamentoAtividadeCronograma.dataRealizacao">
											 <input type="text" maxlength="10" name="n<bean:write name="faturamentoAtividadeCronogramas" property="idFaturamentoAtividade"/>" size="10" id="i<bean:write name="faturamentoAtividadeCronogramas" property="idFaturamentoAtividade"/>" 
											 value="<bean:write name="faturamentoAtividadeCronogramas" property="faturamentoAtividadeCronograma.dataPrevista" formatKey="date.format"/>"
			                                  onkeypress="validaDataCompleta(this, event);return isCampoNumerico(event);"
			                                  onkeyup="calcularQuantidadeDias('<bean:write name="faturamentoAtividadeCronogramas" property="idFaturamentoAtividade"/>');"
		                  					  onblur="calcularQuantidadeDias('<bean:write name="faturamentoAtividadeCronogramas" property="idFaturamentoAtividade"/>');"
		                  					  />
			                                  
			                                  <a href="javascript:habilitarCalendario('<bean:write name="faturamentoAtividadeCronogramas" property="idFaturamentoAtividade"/>')">
			                                      <img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="middle" title="Exibir Calendário"/>
			                                  </a>
										</logic:empty>
										<logic:notEmpty name="faturamentoAtividadeCronogramas" property="faturamentoAtividadeCronograma.dataRealizacao">
											
											
											<input type="text" maxlength="10" name="n<bean:write name="faturamentoAtividadeCronogramas" property="idFaturamentoAtividade"/>" size="10" id="i<bean:write name="faturamentoAtividadeCronogramas" property="idFaturamentoAtividade"/>" 
			                                 readonly="true" value="<bean:write name="faturamentoAtividadeCronogramas" property="faturamentoAtividadeCronograma.dataPrevista" formatKey="date.format"/>"/>
			                                  
			                                  <a href="javascript:habilitarCalendario('<bean:write name="faturamentoAtividadeCronogramas" property="idFaturamentoAtividade"/>')">
			                                      <img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="middle" title="Exibir Calendário"/>
			                                  </a>
											
											
										</logic:notEmpty>
										</div>
										</td>
										<td width="18%" align="center">
										   <logic:present name="faturamentoAtividadeCronogramas" property="faturamentoAtividadeCronograma.dataRealizacao">
											<logic:notEmpty name="faturamentoAtividadeCronogramas" property="faturamentoAtividadeCronograma.dataRealizacao">
												<bean:write name="faturamentoAtividadeCronogramas" property="faturamentoAtividadeCronograma.dataRealizacao" formatKey="date.format"/>
											</logic:notEmpty>
											<logic:empty name="faturamentoAtividadeCronogramas" property="faturamentoAtividadeCronograma.dataRealizacao">
												&nbsp;
											</logic:empty>
										   </logic:present>
										   <logic:notPresent name="faturamentoAtividadeCronogramas" property="faturamentoAtividadeCronograma.dataRealizacao">
												&nbsp;
										   </logic:notPresent>
										</td>
										
						      <td width="10%">
                              <div align="center" >
                             		<strong><span id="qtdDias${faturamentoAtividadeCronogramas.idFaturamentoAtividade}">${faturamentoAtividadeCronogramas.quantidadeDias}</span></strong>
                             </div>
                             </td>
						
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</table>
						</div>
						</td>
					</tr>
				</logic:present>
			</table>
			<p>
			<table width="100%">
				<tr>
					<td>
						<logic:present name="voltar">
							<logic:equal name="voltar" value="filtrar">
								<input name="button" type="button" class="bottonRightCol" value="Voltar" onclick="window.location.href='<html:rewrite page="/exibirFiltrarFaturamentoCronogramaAction.do"/>'" align="left" style="width: 80px;">
							</logic:equal>
							<logic:equal name="voltar" value="manter">
								<input name="button" type="button" class="bottonRightCol" value="Voltar" onclick="window.location.href='<html:rewrite page="/exibirManterFaturamentoCronogramaAction.do"/>'" align="left" style="width: 80px;">
							</logic:equal>
						</logic:present>
						<logic:notPresent name="voltar">
							<input name="button" type="button" class="bottonRightCol" value="Voltar" onclick="window.location.href='<html:rewrite page="/exibirManterFaturamentoCronogramaAction.do"/>'" align="left" style="width: 80px;">
						</logic:notPresent>
						<input name="button" 
							   type="button" 
							   class="bottonRightCol" 
							   value="Desfazer" 
							   onclick="window.location.href='<html:rewrite page="/exibirAtualizarFaturamentoCronogramaAction.do"/>'" 
							   align="left" 
							   style="width: 80px;">
					</td>
					<td width="30%" align="right">
					<gsan:controleAcessoBotao name="Button" value="Atualizar"
							  onclick="javascript:validarForm();" url="atualizarFaturamentoCronogramaAction.do"/>
                   	<%--
					<html:submit
						styleClass="bottonRightCol" value="Atualizar" property="adicionar" />--%>
						</td>
				</tr>
			</table>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
