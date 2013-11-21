<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirImovelSituacaoCobrancaActionForm" />

<script language="JavaScript">

 
 	var unidade = 0;
	
	function setCliente(tipo) {
		unidade = tipo;
	}
	
	/* Recuperar Popup */
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    
	    var form = document.forms[0];
		
	    if (tipoConsulta == 'cliente') {
	     if (unidade == 1) {
	     	form.idEscritorio.value = codigoRegistro;
        	form.nomeEscritorio.value = descricaoRegistro;
	  		form.action = 'exibirInserirImovelSituacaoCobrancaAction.do?objetoConsulta=1&idImovel='+form.codigoImovel.value;      
	     }else if(unidade == 2){
	     	form.idAdvogado.value = codigoRegistro;
       	    form.nomeAdvogado.value = descricaoRegistro;
	  		form.action = 'exibirInserirImovelSituacaoCobrancaAction.do?objetoConsulta=1&idImovel='+form.codigoImovel.value;      
	     }else if (unidade == 3) {
		    form.idClienteAlvo.value = codigoRegistro;
		    form.nomeClienteAlvo.value = descricaoRegistro;
	  		form.action = 'exibirInserirImovelSituacaoCobrancaAction.do?objetoConsulta=1&idImovel='+form.codigoImovel.value;      
	      }
	     unidade = 0;
	    }
	}
 
 function validaForm(){
 	var form = document.forms[0];
 	if (validateInserirImovelSituacaoCobrancaActionForm(form) && 
	 	verificaAnoMesInicio(form.anoMesReferenciaInicio) && 
	 	verificaAnoMesFim(form.anoMesReferenciaFim)){
		 		form.submit();
 	}
 }
  function limpaCliente(){
 	var form = document.forms[0];
 	form.idClienteAlvo.value = "";
 	form.nomeClienteAlvo.value = "";
 	form.idClienteAlvo.focus();
 }
 
 function limpaEscritorio(){
 	var form = document.forms[0];
 	form.idEscritorio.value = "";
 	form.nomeEscritorio.value = "";
 	form.idEscritorio.focus();
 }
 
 function limpaAdvogado(){
 	var form = document.forms[0];
 	form.idAdvogado.value = "";
 	form.nomeAdvogado.value = "";
 	form.idAdvogado.focus();
 }
 
 function verificaAnoMesInicio(mydata) {
	
	var situacao = true;
	
	if (mydata.value.length == 7) {
	
		dia = 1;
    	mes = mydata.value.substring(0,2); 
    	ano = mydata.value.substring(3,7); 

    	if ((!isNaN(mes) || !isNaN(ano)) && (mes.indexOf('.') == -1 && mes.indexOf(',') == -1 && mes.indexOf('/') == -1) &&
			(ano.indexOf('.') == -1 && ano.indexOf(',') == -1 && ano.indexOf('/') == -1)) {
    	
    		// verifica se o mes e valido 
	    	if ((mes * 1) >= 1 && (mes * 1) <= 12 ) { 
	        
	        	// verifica se o ano e valido
	        	if ((ano * 1) != 0 && (ano * 1) >= 1980) {
	        	
	        		// verifica se e ano bissexto 
	    			if ((mes * 1) == 2 && ((dia * 1) < 1 || (dia * 1) > 29 
	    				|| ((dia * 1) > 28 && (((ano * 1) / 4) != (ano * 1) / 4)))) { 
	       				situacao = false; 
	    			}
	        	}
	        	else{
	        		situacao = false;
	        	}
	    	}
	    	else{
	    		situacao = false;
	    	} 
		}
		else{
			situacao = false;
		}
    }
    else if (mydata.value.length > 0){
    	situacao = false;
    }
    
    if (!situacao) { 
	   alert("Mês e Ano de Referência da Cobrança Inicial inválida.");
	   mydata.value = "";
	   mydata.focus(); 
	}
	
	return situacao;
}

function verificaAnoMesFim(mydata) {
	
	var situacao = true;
	
	if (mydata.value.length == 7) {
	
		dia = 1;
    	mes = mydata.value.substring(0,2); 
    	ano = mydata.value.substring(3,7); 

    	if ((!isNaN(mes) || !isNaN(ano)) && (mes.indexOf('.') == -1 && mes.indexOf(',') == -1 && mes.indexOf('/') == -1) &&
			(ano.indexOf('.') == -1 && ano.indexOf(',') == -1 && ano.indexOf('/') == -1)) {
    	
    		// verifica se o mes e valido 
	    	if ((mes * 1) >= 1 && (mes * 1) <= 12 ) { 
	        
	        	// verifica se o ano e valido
	        	if ((ano * 1) != 0 && (ano * 1) >= 1980) {
	        	
	        		// verifica se e ano bissexto 
	    			if ((mes * 1) == 2 && ((dia * 1) < 1 || (dia * 1) > 29 
	    				|| ((dia * 1) > 28 && (((ano * 1) / 4) != (ano * 1) / 4)))) { 
	       				situacao = false; 
	    			}
	        	}
	        	else{
	        		situacao = false;
	        	}
	    	}
	    	else{
	    		situacao = false;
	    	} 
		}
		else{
			situacao = false;
		}
    }
    else if (mydata.value.length > 0){
    	situacao = false;
    }
    
    if (!situacao) { 
	   alert("Mês e Ano de Referência da Cobrança Final.");
	   mydata.value = "";
	   mydata.focus(); 
	}
	
	return situacao;
}

 function submeterCombobox(){
 	var form = document.forms[0];
 	if (form.situacaoCobranca.value != ""){
 	  form.action = 'exibirInserirImovelSituacaoCobrancaAction.do?objetoConsulta=1&situacaoCobranca='+form.situacaoCobranca.value;      
	  form.submit();
 	}
 }
 

 

</script>
</head>
<body leftmargin="5" topmargin="5">

<html:form action="/informarImovelSituacaoCobrancaAction.do"
	name="InserirImovelSituacaoCobrancaActionForm"
	type="gcom.gui.cadastro.imovel.InserirImovelSituacaoCobrancaActionForm"
	method="post"
	onsubmit="return validateInserirImovelSituacaoCobrancaActionForm(this);">

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
			<td width="615" valign="top" class="centercoltext"><!--Início Tabela Reference a Páginação da Tela de Processo-->
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
					<td class="parabg">Inserir Situação de Cobrança</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<table border="0" width="100%">
				<tbody>
					<tr>
						<td colspan="4">Parâmetros Informados:</td>
					</tr>
					<tr>
						<td width="30%" ><strong>Matrícula:</strong></td>
						<td width="20%" ><html:text property="codigoImovel" size="8"
							maxlength="8" readonly="readonly"
							style="border: 0pt none ; background-color: rgb(239, 239, 239); color: rgb(0, 0, 0);" /></td>
						<td width="20%" ><strong>Inscrição:</strong></td>
						<td width="30%" ><html:text property="matriculaImovel"
							maxlength="25" size="25" readonly="readonly"
							style="border: 0pt none ; background-color: rgb(239, 239, 239); color: rgb(0, 0, 0);" /></td>
					</tr>
					<tr>
						<td colspan="4">
						<hr>
						</td>
					</tr>
					<tr>
						<td colspan="4">Para inserir a situação de cobrança do imóvel,
						informe os dados abaixo:</td>
					</tr>
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
					<tr>
						<td>
							<strong> Situação de Cobrança:<font color="#ff0000">*</font></strong></td>
						<td colspan="3" align="right">
							<div align="left">
								<html:select property="situacaoCobranca" style="width: 350px;" tabindex="1" onchange="submeterCombobox();">
									<html:option value=""></html:option>
									<html:options collection="colecaoSituacaoCobranca" labelProperty="descricao" property="id" />
							</html:select>
							</div>
						</td>
					</tr>
					
					
					
					<tr>
						<td><strong>Escritório de Advocacia:</strong></td>
						<td colspan="3" align="right">
							<logic:present name="ativo" scope="request">		
								<html:text  property="idEscritorio" 
											size="10" 
											maxlength="10" 
											tabindex="2"
											onkeypress="javascript:validaEnterComMensagem(event, 'exibirInserirImovelSituacaoCobrancaAction.do?objetoConsulta=1&idImovel='+form.codigoImovel.value, 'idEscritorio','Escritório de Advocacia');return isCampoNumerico(event);" />
									<a href="javascript:setCliente(1);javascript:abrirPopup('exibirPesquisarClienteAction.do');">
										<img src="/gsan/imagens/pesquisa.gif" 
											 alt="Pesquisar" 
											 border="0" 
											 height="21" 
											 width="23"
											 title="Pesquisar Escritório de Advocacia"
											 style="cursor: pointer;cursor:hand;">
									</a> 
									
								<logic:present name="escritorioEncontrado">
									<logic:equal name="escritorioEncontrado" value="exception">
										<html:text  property="nomeEscritorio" 
													maxlength="40" 
													size="40" 
													readonly="true"
													style="background-color:#EFEFEF; border:0; color: #ff0000" />
									</logic:equal>
									<logic:notEqual name="escritorioEncontrado" value="exception">
										<html:text  property="nomeEscritorio" 
													maxlength="25" 
													size="40" 
													readonly="true"
													style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:notEqual>
								</logic:present> 
									
								<logic:notPresent name="escritorioEncontrado">
									<logic:empty name="InserirImovelSituacaoCobrancaActionForm" property="idEscritorio">
										<html:text  property="nomeEscritorio" 
													maxlength="40" 
													size="40" 
													readonly="true"
													style="background-color:#EFEFEF; border:0; color: #ff0000" />
									</logic:empty>
									<logic:notEmpty name="InserirImovelSituacaoCobrancaActionForm" property="idEscritorio">
										<html:text  property="nomeEscritorio" 
													maxlength="40" 
													size="40" 
													readonly="true"
													style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:notEmpty>
								</logic:notPresent> 
									
								<a href="javascript:limpaEscritorio()">  
									<img src="imagens/limparcampo.gif" 
										 border="0" 
										 height="21" 
										 width="23"
										 title="Apagar"
										 style="cursor: pointer;cursor:hand;">
								</a>
							</logic:present>	
								
							<logic:notPresent name="ativo" scope="request">
								<div align="left">
									<html:text  property="idEscritorio" 
												size="10" 
												maxlength="10" 
												disabled="true" /> 
										<img src="/gsan/imagens/pesquisa.gif" 
											 alt="Pesquisar" 
											 border="0" 
											 height="21" 
											 width="23"
											 style="cursor: pointer;cursor:hand;"
											 title="Pesquisar Escritório de Advocacia"> 
									<html:text  property="nomeEscritorio" 
												maxlength="40" 
												size="40" 
												disabled="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										<img src="imagens/limparcampo.gif" 
											 border="0" 
											 height="21" 
											 width="23"
											 style="cursor: pointer;cursor:hand;"
											 title="Apagar">
								</div>
							</logic:notPresent>
					   </td>
					</tr>
					
					
					
					<tr>
						<td><strong>Advogado:</strong></td>
						<td colspan="3" align="right">
							<logic:present name="ativo" scope="request">		
								<html:text  property="idAdvogado" 
											size="10" 
											maxlength="10" 
											tabindex="3"
											onkeypress="javascript:validaEnterComMensagem(event, 'exibirInserirImovelSituacaoCobrancaAction.do?objetoConsulta=1&idImovel='+form.codigoImovel.value, 'idAdvogado','Advogado');return isCampoNumerico(event);" />
									<a href="javascript:setCliente(2);javascript:abrirPopup('exibirPesquisarClienteAction.do');">
										<img src="/gsan/imagens/pesquisa.gif" 
											 alt="Pesquisar" 
											 border="0" 
											 height="21" 
											 width="23"
											 title="Pesquisar Advogado"
											 style="cursor:pointer;cursor:hand;">
									</a> 
									
									<logic:present name="advogadoEncontrado">
										<logic:equal name="advogadoEncontrado" value="exception">
											<html:text  property="nomeAdvogado" 
														maxlength="40" 
														size="40" 
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:equal>
										<logic:notEqual name="advogadoEncontrado" value="exception">
											<html:text  property="nomeAdvogado" 
														maxlength="40" 
														size="40" 
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEqual>
									</logic:present> 
									
									<logic:notPresent name="advogadoEncontrado">
										<logic:empty name="InserirImovelSituacaoCobrancaActionForm" property="idAdvogado">
											<html:text  property="nomeAdvogado" 
														maxlength="40" 
														size="40" 
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:empty>
										<logic:notEmpty name="InserirImovelSituacaoCobrancaActionForm" property="idAdvogado">
											<html:text  property="nomeAdvogado" 
														maxlength="40" 
														size="40" 
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEmpty>
									</logic:notPresent> 
									
									<a href="javascript:limpaAdvogado()">  
										<img src="imagens/limparcampo.gif" 
											 border="0" 
											 height="21" 
											 width="23" 
											 title="Apagar">
									</a>
							</logic:present>
									
							<logic:notPresent name="ativo" scope="request">
								<div align="left">
									<html:text  property="idAdvogado" 
												size="10" 
												maxlength="10" 
												disabled="true" /> 
										<img src="/gsan/imagens/pesquisa.gif" 
											 alt="Pesquisar" 
											 border="0" 
											 height="21" 
											 width="23"
											 title="Pesquisar Advogado"
											 style="cursor: pointer;cursor:hand;">
									<html:text  property="nomeAdvogado" 
												maxlength="40" 
												size="40" 
												disabled="true"
												style="background-color:#EFEFEF; border:0; color: #000000"/>
										<img src="imagens/limparcampo.gif" 
											 border="0" 
											 height="21" 
											 width="23"
											 title="Apagar"
											 style="cursor: pointer;cursor:hand;">
								</div>
							</logic:notPresent>
					   </td>
					</tr>
					
					
					<tr>
						<td>
							<strong>Data da Implantação:<font color="#ff0000">*</font></strong>
						</td>
						<td colspan="3" align="right">
							<div align="left">
								<html:text 	property="dataImplantacao" 
											size="10"
											maxlength="10" 
											onkeypress="return isCampoNumerico(event);"
											style="border: 0pt none ; background-color: rgb(239, 239, 239); color: rgb(0, 0, 0);" 
								/>
							</div>
						</td>
					</tr>
					<tr>
						<td><strong>Cliente Alvo:<font color="#ff0000">*</font> </strong></td>
						<td colspan="3" align="right">
						<div align="left">
							<html:text 	property="idClienteAlvo" 
										size="10" 
										maxlength="10" 
										onkeypress="javascript:validaEnterComMensagem(event, 'exibirInserirImovelSituacaoCobrancaAction.do?objetoConsulta=1&idImovel='+form.codigoImovel.value, 'idClienteAlvo','Cliente Alvo');return isCampoNumerico(event);" /> 
								<a href="javascript:setCliente(3);javascript:abrirPopup('exibirPesquisarClienteAction.do');">
									<img src="imagens/pesquisa.gif" 
										 title="Pesquisar Cliente" 
										 border="0" 
										 height="21" 
										 width="23"></a> 
							<html:text  property="nomeClienteAlvo" 
									    maxlength="40" 
									    size="40"
										readonly="readonly" 
										onkeypress="javascript:validaEnterComMensagem(event, 'exibirInserirImovelSituacaoCobrancaAction.do?objetoConsulta=1', 'idClienteAlvo','Cliente Alvo');"
										style="border: 0pt none ; background-color: rgb(239, 239, 239); color: rgb(0, 0, 0);" />
								<a href="javascript:limpaCliente();">
									<img src="imagens/limparcampo.gif" 
										 border="0" 
										 height="21" 
										 width="23"
										 title="Apagar">
								</a>
						</div>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<strong>Intervalo de Referência dos Débitos vinculados à Situação de Cobrança:</strong>
						</td>
					</tr>
					<tr>
						<td>
							<strong>Mês e Ano de Referência da Cobrança Inicial:<font color="#ff0000">*</font></strong>
						</td>
						<td colspan="3" align="right">
							<div align="left">
								<logic:present name="bloqueiaAnoMes" scope="request">
									<html:text  property="anoMesReferenciaInicio" 
												tabindex="4"
												size="7" 
												maxlength="7" 
												value="" 
												disabled="true" 
												onkeypress="return isCampoNumerico(event);"
												onkeyup="mascaraAnoMes(this, event);" />
								</logic:present>
								
								<logic:notPresent name="bloqueiaAnoMes" scope="request">
									<html:text  property="anoMesReferenciaInicio" 
												tabindex="4"
												size="7" 
												maxlength="7" 
												onkeypress="return isCampoNumerico(event);"
												onkeyup="mascaraAnoMes(this, event);" />
								</logic:notPresent>mm/aaaa
							 </div>
						</td>
					</tr>
					<tr>
						<td>
							<strong>Mês e Ano de Referência da Cobrança Final:<font color="#ff0000">*</font></strong>
						</td>
						<td colspan="3" align="right">
							<div align="left">
								<logic:present name="bloqueiaAnoMes" scope="request">
									<html:text  property="anoMesReferenciaFim" 
												tabindex="5"
												size="7" 
												maxlength="7" 
												value="" 
												disabled="true" 
												onkeyup="mascaraAnoMes(this, event);"
												onkeypress="return isCampoNumerico(event);"/>
								</logic:present>
							
								<logic:notPresent name="bloqueiaAnoMes" scope="request">
									<html:text  property="anoMesReferenciaFim" 
												tabindex="5"
												size="7" 
												maxlength="7" 
												onkeypress="return isCampoNumerico(event);"
												onkeyup="mascaraAnoMes(this, event);"/>
								</logic:notPresent>mm/aaaa
							</div>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td colspan="3" align="right">
						<div align="left"><strong><font color="#ff0000">*</font></strong>
						Campos obrigatórios</div>
						</td>
					</tr>
					<tr>
						<td>
							<input name="Submit2" 
								   class="bottonRightCol" 
								   value="Voltar"
								   type="button" 
								   onClick="javascript:window.location.href='exibirInformarImovelSituacaoCobrancaAction.do?menu=sim';">
						</td>
						<td colspan="3" align="right">
							<input  name="Submit2"
									class="bottonRightCol" 
									value="Concluir" 
									type="button"
									onclick="validaForm();">
						</td>
					</tr>
					<tr>
						<td colspan="4" height="9">&nbsp;</td>
					</tr>
				</tbody>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
