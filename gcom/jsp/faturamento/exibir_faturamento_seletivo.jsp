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

function ehAnormalidadeValida(anormalidade, matricula){
		
	var valor = anormalidade.value;
		
	var valorLeitura = document.getElementById("leituras_" + matricula).value;
	
	if(valor != null && valor != ""){
		
		var anor = document.getElementById("anormalidadesBanco").value;
		var anormalidades = new Array();
		var anormalidadesIndicadores =  new Array();
		
		anormalidades = anor.split('/');	
					
		valor = parseInt(valor);			
		var passou = 0;	
		var possui = 0;
		
		for(i in anormalidades){
			
			anormalidadesIndicadores =  anormalidades[i].split(';'); 
			
			if(valorLeitura == 0 || valorLeitura ==  null || valorLeitura == ""){
	
						
				if(anormalidadesIndicadores[0] == valor){	
							
					possui = 1;
								
					if(anormalidadesIndicadores[1] == 1){
					
						alert("A anormalidade deve conter leitura!");
						
						document.getElementById("leituras_" + matricula).focus();
						break;
					}else{
						passou = 1;
						break;
					}			
		
				}
	
			}
			else{
						
				if(anormalidadesIndicadores[0] == valor){	
					
					possui = 1;	
					
					if(anormalidadesIndicadores[1] != 2){		
						passou = 1;
						break;
					}else{
						break;
					}
						
				}
				
			}
		}
		
		if(passou == 0){

			if(anormalidadesIndicadores[1] == 2 && valorLeitura != 0 && valorLeitura != null && valorLeitura != ""){
				alert("Anormalidade não pode ter leitura.");
				anormalidade.value = "";
				anormalidade.focus();
												
			}
			else{
				if(possui == 0){
					alert("Anormalidade Inexistente ou Inativa.");
					anormalidade.value = "";
					anormalidade.focus();
				}
			}	
		}	
	}

}

var nomeForm;
var nomeCampo;
var indice; 
var indiceReplicado;

function buscarImoveisPorRota(event, form) {
		if (document.all) {
			var codigo = event.keyCode;
	    }
		else {
	       var codigo = tecla.which;
	    }
	    if(codigo ==13){
	    	form.rota.value = "";
	    	form.action = 'exibirFaturamentoSeletivo.do';
	    	form.submit();	
    	}
	}
function abrirCalendario(formName, fieldName, index) {
    nomeForm = formName;
    nomeCampo = fieldName;
    indice = index;    
   	centerpopup('./jsp/util/calendario.jsp','calendario',225,268);
}

function abrirCalendarioReplicando(formName, fieldName, index, indexReplicado) {
   	nomeForm = formName;
    nomeCampo = fieldName;
    indice = index;    
	indiceReplicado = indexReplicado;
    centerpopup('./jsp/util/calendario.jsp','calendario',225,268);
}
	
	
function restart() {
	var total = document.getElementById("qnt").value;
	var obData = document.forms[nomeForm].elements[nomeCampo];
    obData[indice].value = '' + padout(day) + '/' + padout(month - 0 + 1) + '/' + year;
    if(indiceReplicado != null){
    	for(var i =0; i < total; i++){
    		obData[i].value = '' + padout(day) + '/' + padout(month - 0 + 1) + '/' + year;
    	}
       indiceReplicado=null;
    }       
}

function validarForm(tipo){
	   var total = document.getElementById("qnt").value;
	   var form = document.forms[0];
	   var obData = document.forms['FaturamentoSeletivoActionForm'].elements['datas'];
	   var obLeitura = document.forms['FaturamentoSeletivoActionForm'].elements['leituras'];
	   var obAnormalidade = document.forms['FaturamentoSeletivoActionForm'].elements['anormalidades'];
	   var ok = 1;
	   var temPreenchido = 0;
	   for(var i =0; i <total && total != 1; i++){
			if ( obData[i] != undefined ) {
		   		if(obData[i].value != null && obData[i].value != ""){
		   			temPreenchido = 1;
		   			 if((obLeitura[i].value == null || obLeitura[i].value == "") && (obAnormalidade[i].value == null || obAnormalidade[i].value == "")){
		   				ok =0;
		   				break;
		   			} 
		   		}else{
		   			 if((obLeitura[i].value != null && obLeitura[i].value != "") 
		   			 	|| (obAnormalidade[i].value != null && obAnormalidade[i].value != "")){
		   			 		temPreenchido = 1;
		   			 }
		   			ok =0;
		   			break;
		   		}
		   	}	
	   }
	   
	   if(total== 1){
	   		if(obData.value != null && obData.value != ""){
	   			temPreenchido = 1;
	   			 if((obLeitura.value == null || obLeitura.value == "") 
	   			 	&& (obAnormalidade.value == null || obAnormalidade.value == "")){
	   				ok =0;	   				
	   			}
	   		}else{
	   			 if((obLeitura.value != null && obLeitura.value != "") 
	   			 	|| (obAnormalidade.value != null && obAnormalidade.value != "")){
	   			 		temPreenchido = 1;
	   			 }
	   			ok =0;
	   		}

	    }
	  
	   if(ok ==1 || temPreenchido == 0){
		   if(tipo == 1){
		   		form.action = 'faturamentoSeletivo.do?action=avancar&temPreenchido='+temPreenchido;
		   }else if(tipo == 2){
		   		form.action = 'faturamentoSeletivo.do?action=voltar&temPreenchido='+temPreenchido;
		   }else if(tipo == 3){
		   		form.action = 'faturamentoSeletivo.do?action=concluir&temPreenchido='+temPreenchido;
		   }
		submeterFormPadrao(form);
	   }  
	   
	   
}

function ehForaFaixa(leitura, posicao){
		var valor = leitura.value;
		
		if(valor!=null && valor!= ""){
			var f = document.getElementById("faixa").value;
			var faixas = new Array()
			faixas = f.split('/');		
			valor = parseInt(valor);			
			var passou = 0;	 	
			var faixaAux = faixas[posicao];
			var faixa = new Array();
			faixa = faixaAux.split(';');
			if(valor< parseInt(faixa[0]) || valor >  parseInt(faixa[1])){
				alert("Leitura Fora de Faixa");
			}
				
		}  

}

function replicarData(data){
		   var total = document.getElementById("qnt").value;
	   	   var obData = document.forms['FaturamentoSeletivoActionForm'].elements['datas'];
		   for(var i =0; i< total; i++){
		   		obData[i].value = data.value;
		   }
	   
}

function limparRota(form){
	form.rota.value = "";
	form.descricaoRota.value = "";
}

function receberRota(idRota, descricao, codigoRota) {
 	  var form = document.forms[0];
	 form.rota.value = codigoRota;
	 form.descricaoRota.value = descricao;
	 form.action = 'exibirFaturamentoSeletivo.do?idRota='+ idRota;	
	 form.submit();
	  
}

function passarComEnter(tecla, nomeCampoForm, posicao) {

	var form = document.forms[0];
	
	var codigo;
	if (document.all) {
		codigo = tecla.keyCode;
    }
	else {
       codigo = tecla.which;
    }	
	
	if (codigo == 13) {
		var total = document.getElementById("qnt").value;
		
		var objetoCampo = eval("form." + nomeCampoForm);
		
		var campo;	
			
		if( parseInt(posicao) < parseInt(total)){
			campo = objetoCampo[posicao];			
		}else{
			campo = objetoCampo[0];
		}
		
		campo.focus();	  
	}
	
}
</script>
</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">
<html:form action="/faturamentoSeletivo" name="FaturamentoSeletivoActionForm" type="gcom.gui.faturamento.FaturamentoSeletivoActionForm" method="post">
	
	<input type="hidden" id="anormalidadesBanco" value="${requestScope.anormalidadesBanco}"/>
	<input type="hidden" id="faixa" value="${requestScope.faixa}"/>
	<input type="hidden" id="qnt" value="${requestScope.qnt}"/>	

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
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
						<td class="parabg">Informar Leitura por Imóvel para Faturamento Seletivo</td>
						<td width="11" valign="top">
							<img border="0" src="imagens/parahead_right.gif" />
						</td>
					</tr>
	
				</table>
	
				<p>&nbsp;</p>
			
				<table width="100%" border="0">
					<tr>
						<td><strong>Rota:</strong></td>
						<td>
							<html:text property="descricaoRota" size="40" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
						</td>
					</tr>
					
					<table width="100%" align="center" bgcolor="#90c7fc" border="0" cellpadding="0" cellspacing="0"> 
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
								<table width="100%" bgcolor="#99CCFF">
									<tr bordercolor="#000000" bgcolor="#90c7fc">
										<td width="20%" bgcolor="#90c7fc">
											<div align="center"></div>
										</td>
										<td width="20%" bgcolor="#90c7fc">
											<div align="center"><strong>Inscrição</strong></div>
										</td>
										
										<td width="15%" bgcolor="#90c7fc">
											<div align="center"><strong>Matrícula</strong></div>
										</td>
										
										<td width="7%" bgcolor="#90c7fc">
											<div align="center"><strong>Número</strong></div>
										</td>
										
										<td width="16%" bgcolor="#90c7fc">
											<div align="center"><strong>Leitura Informada</strong></div>
										</td>
										
										<td width="10%" bgcolor="#90c7fc">
											<div align="center"><strong>Anormalidade</strong></div>
										</td>
										
										<td width="30%" bgcolor="#90c7fc">
											<div align="center"><strong>Data da Leitura</strong></div>
										</td>
									</tr>
									
									<logic:present name="colecaoLeituras">
										<%int cont = 0;%>
										<logic:iterate name="colecaoLeituras" id="dado">
											<% cont = cont + 1; 
											   if (cont % 2 == 0) {%>
												<tr bgcolor="#cbe5fe">
											<%} else {%>
												<tr bgcolor="#FFFFFF">
											<%}%>
													<td width="1%">
															<input type="checkbox" name="idImoveisSelecionados" value="<bean:write name="dado" property="matriculaImovel"/>"/>
													</td>
													
													<td width="20%">
														<bean:write name="dado" property="inscricao" />
													</td>
		
													<td width="15%" align="center">
														<bean:write name="dado" property="matriculaImovel" />
													</td>
													
													<td width="7%" align="center">
														<bean:write name="dado" property="endereco" />
													</td>
												
													<c:choose >
														<c:when test='${ (dado.msgImovelSuprimidoOuHidrometroRetirado != null && dado.msgImovelSuprimidoOuHidrometroRetirado != "") }'>
															<td width="56%" align="center" colspan="3">${dado.msgImovelSuprimidoOuHidrometroRetirado}</td>
														</c:when>
		
														<c:otherwise>
															<td width="16%">
																<div align="center">
																	<c:choose >		
																		<c:when test='${dado.naoPermitirAlterar == true}'>
																			<input type="text" maxlength="6" tabindex="2" name="leituras" size="6" readonly="readonly" style="background-color:#EFEFEF; 
																				border:0; text-align:left; color: #000000;" value="${dado.leituraHidrometro}" id="leituras_${dado.matriculaImovel}"/>
																		</c:when>
																
																		<c:otherwise>
																			<input type="text" maxlength="6" tabindex="2" name="leituras" size="6"  onblur="javascript:ehForaFaixa(this,'<%=cont-1%>');" 
																				onkeypress="passarComEnter(event, 'anormalidades', '<%=cont-1%>');return isCampoNumerico(event);" 
																				value="${dado.leituraHidrometro}" id="leituras_${dado.matriculaImovel}"/>
																		</c:otherwise>
																	</c:choose>
																
																</div>
															</td>
															
															<td width="10%">
																<div align="center">
																	<c:choose >		
																		<c:when test='${dado.naoPermitirAlterar == true}'>
																			<input type="text" maxlength="2" tabindex="2"
																			name="anormalidades" size="2" 
																			readonly="readonly" style="background-color:#EFEFEF; border:0; text-align:left; color: #000000;" 
																			value="${dado.codigoAnormalidade}"/>
																		</c:when>
																		<c:otherwise>
																			<input type="text" maxlength="2" tabindex="2"
																			name="anormalidades" size="2" onblur="javascript:ehAnormalidadeValida(this, '${dado.matriculaImovel}')" 
																			onkeypress="passarComEnter(event, 'datas', '<%=cont-1%>');return isCampoNumerico(event);" 
																			value="${dado.codigoAnormalidade}"/>
																		</c:otherwise>
																	</c:choose>
																</div>
															</td>
		
															<td width="30%">
																<div align="center">
																<%if (cont == 1) {%>
																
																	<c:choose >		
																			<c:when test='${dado.naoPermitirAlterar == true}'>
																				<input type="text" maxlength="10" tabindex="2"
																				name="datas" size="10" 
																				readonly="readonly" style="background-color:#EFEFEF; border:0; text-align:left; color: #000000;" 
																				value="<fmt:formatDate pattern="dd/MM/yyyy" value="${dado.dataLeituraCampo}"/>"/>
																				<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
																					width="20" border="0" align="absmiddle" alt="Exibir Calendário" />							
																			</c:when>
																			<c:otherwise>
																				<input type="text" maxlength="10" tabindex="2"
																				name="datas" size="10" onkeyup="mascaraData(this,event);replicarData(this);" 
																				onkeypress="passarComEnter(event, 'leituras', '<%=cont%>');return isCampoNumerico(event);"
																				 value="<fmt:formatDate pattern="dd/MM/yyyy" value="${dado.dataLeituraCampo}"/>"
																				onblur="javascript:verificaDataMensagemPersonalizada(this,'Data Inválida.')"/>
																				<a href="javascript:abrirCalendarioReplicando('FaturamentoSeletivoActionForm','datas','<%=cont-1%>',1)">
																				<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
																					width="20" border="0" align="absmiddle" alt="Exibir Calendário" />
																				</a>
																			</c:otherwise>
																	</c:choose>							
																	<%} else {%>
																	<c:choose >	
																			<c:when test='${dado.naoPermitirAlterar == true}'>
																				<input type="text" maxlength="10" tabindex="2"
																				name="datas" size="10" 
																				readonly="readonly" style="background-color:#EFEFEF; border:0; text-align:left; color: #000000;" 
																				value="<fmt:formatDate pattern="dd/MM/yyyy" value="${dado.dataLeituraCampo}"/>"/>
																				<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
																					width="20" border="0" align="absmiddle" alt="Exibir Calendário" />
																			</c:when>
																			<c:otherwise>
																				<input type="text" maxlength="10" tabindex="2"
																				name="datas" size="10" onkeyup="mascaraData(this,event)" 
																				onkeypress="passarComEnter(event, 'leituras', '<%=cont%>');return isCampoNumerico(event);"
																				value="<fmt:formatDate pattern="dd/MM/yyyy" value="${dado.dataLeituraCampo}"/>"
																				onblur="javascript:verificaDataMensagemPersonalizada(this,'Data Inválida.')"/>
																				<a href="javascript:abrirCalendario('FaturamentoSeletivoActionForm','datas','<%=cont-1%>')">
																				<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
																					width="20" border="0" align="absmiddle" alt="Exibir Calendário" />
																				</a>
																			</c:otherwise>
																	</c:choose>
																<%}%>
																</div>
															</td>
														</c:otherwise>
													</c:choose>
												</tr>
												
										</logic:iterate>
									</logic:present>
								</table>
							</td>
						</tr>
						
						
						
						<logic:present name="colecaoLeituras">
							<table align="center">				
								<tr class="centercoltext">
									<td align="center">
										<strong>Posição: ${FaturamentoSeletivoActionForm.indice}/${FaturamentoSeletivoActionForm.total}</strong>
									</td>
								</tr>
							</table>	
							
							<!--  BOTÕES DA PAGINAÇÃO -->				
							<table>				
								<tr class="centercoltext">
									<!-- IMAGEM BOTÃO VOLTAR  -->
									<td align="right"  width="10%">
										<c:choose>
											<c:when test='${FaturamentoSeletivoActionForm.indice != 1}'>
												<a href="javascript:validarForm(2);">
													<img src="imagens/voltar.gif" border="0"></a>
											</c:when>
										</c:choose>
									</td>
									
									<!-- BOTÃO VOLTAR  -->
									<td align="left" width="10%">
										<c:choose>
											<c:when test='${FaturamentoSeletivoActionForm.indice != 1}'>
												<gsan:controleAcessoBotao name="Button" url="faturamentoSeletivo.do" value="  Voltar  " tabindex="6" onclick="javascript:validarForm(2);"/>
											</c:when>
										</c:choose>
									</td>
									
									<!-- BOTÃO AVANÇAR  -->				   			
									<td align="right" width="10%">
										<c:choose>
											<c:when test='${FaturamentoSeletivoActionForm.indice != FaturamentoSeletivoActionForm.total}'>
												<gsan:controleAcessoBotao name="Button" url="faturamentoSeletivo.do" value="Avançar" tabindex="7" onclick="javascript:validarForm(1);"/>
											</c:when>
										</c:choose>
									</td>
									
									<!-- IMAGEM BOTÃO AVANÇAR  -->
									<td align="left" width="10%">
										<c:choose>
											<c:when test='${FaturamentoSeletivoActionForm.indice != FaturamentoSeletivoActionForm.total}'>
												<a href="javascript:validarForm(1);">
													<img src="imagens/avancar.gif" border="0" /></a>
											</c:when>
										</c:choose>
									</td>
									
								</tr>
								
								
								<!--  LINHA PRETA-->
								<tr>
									<td colspan="9" width="100%" height="1px" bgcolor="#000000"></td>
								</tr>
								
								<!--  BOTÕES FINAIS -->
								<tr class="rigthcoltext">
									<td width="100%">
										<table width="100%">
											<tr>
												<td align="left" colspan="2">
													<input type="button" name="Button" class="bottonRightCol" value="Desfazer" onClick="window.location.href='/gsan/exibirFaturamentoSeletivo.do?menu=sim'" />
													<input type="button" name="Button" class="bottonRightCol" value="Cancelar" onClick="window.location.href='/gsan/telaPrincipal.do'" />
												</td>
												<td align="right">
													<div align="right">
														<gsan:controleAcessoBotao name="Button" url="faturamentoSeletivo.do" value="Concluir" tabindex="8" onclick="javascript:validarForm(3);"/>
													</div>
												</td>
											</tr>
										</table>										 
									</td>
								</tr>
							</table>
							
						</logic:present>
						
						<tr class="centercoltext">
							<td>&nbsp;</td>
						</tr>
					</table>
				</table>
			
			<p>&nbsp;</p>
		</tr>
		<%@ include file="/jsp/util/rodape.jsp"%>
	</table>
	<p>&nbsp;</p>
</html:form>
</body>
</html:html>

