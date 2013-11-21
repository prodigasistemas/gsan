<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
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
		    var form = document.forms[0];
		    
		    form.action = "atualizarResolucaoDiretoriaContratoParcelamentoAction.do";
	        document.forms[0].submit();
		}
			
		function cancelar(){
		    document.forms[0].reset();
			window.location.href = "/gsan/telaPrincipal.do";
		}
			
		function verificarData(){
			var form = document.forms[0];
			
			if(form.dataVigenciaFinal.disabled == false){
				
				abrirCalendario('AtualizarResolucaoDiretoriaContratoParcelamentoActionForm', 'dataVigenciaFinal');
			}
		}
		
		function limpaDataFinal(){
		var form = document.forms[0];
		  if(form.dataVigenciaFinal.value == ''){
		   form.dataVigenciaFinal.value = '';
		  }
		
		}
		 
		
		function adicionarParcela(){
			var form = document.forms[0];
            var parcela = form.pacerlaAdd.value;
            var taxaJuros = form.taxaJurosAdd.value;
            
             if(form.qtdFaturasParceladas == null || form.qtdFaturasParceladas.value == "" || form.qtdFaturasParceladas.value == "0"){
           		alert("Informe: \nNúmero Máximo de Parcelas");
            }else{
                var parcelInt = parseInt(parcela);
                var qtdInt = parseInt(form.qtdFaturasParceladas.value);
            	if(parcelInt > qtdInt){
            		alert("Parcela informada excede o número máximo de parcelas da RD");
            	}else{
					form.action = "atualizarResolucaoDiretoriaContratoParcelamentoAction.do?method=inserirParcela&parcela=" + parcela + "&taxaJuros="+taxaJuros;
		            document.forms[0].submit();
            	}
            }

		}
		
		function removerParcela(parcela){
			var form = document.forms[0];
			form.action = form.action + "?method=removerParcela&parcela=" + parcela;
            document.forms[0].submit();
		}
		
		function atualizar(){
			var validar = validarCampos();
			
			if(validar){
				var form = document.forms[0];
				form.action = form.action + "?method=concluirAtualizarRD";
	            document.forms[0].submit();
			}
			
		}
		
		function verificaRadioButtons(indicadorDebitoAcrescimo, indicadorParcelamentoJuros, indicadorInformarParcela){
			
			var form = document.forms[0];
			
			if(indicadorDebitoAcrescimo != null && indicadorDebitoAcrescimo != ''){
				if(indicadorDebitoAcrescimo == 1){
					form.indicadorDebitoAcrescimo[0].checked = true;
				}else{
					form.indicadorDebitoAcrescimo[1].checked = true;
				}
			}else{
				form.indicadorDebitoAcrescimo[0].checked = false;
				form.indicadorDebitoAcrescimo[1].checked = false;
			}
			
			if(indicadorParcelamentoJuros != null && indicadorParcelamentoJuros != ''){
				if(indicadorParcelamentoJuros == 1){
					form.indicadorParcelamentoJuros[0].checked = true;
					
					form.indicadorInformarParcela[0].disabled = true;
					form.indicadorInformarParcela[1].disabled = true;
				}else{
					form.indicadorParcelamentoJuros[1].checked = true;
					
					form.indicadorInformarParcela[0].disabled = false;
					form.indicadorInformarParcela[1].disabled = false;
				}
			}else{
				form.indicadorParcelamentoJuros[0].checked = false;
				form.indicadorParcelamentoJuros[1].checked = false;
				
				form.indicadorInformarParcela[0].disabled = true;
				form.indicadorInformarParcela[1].disabled = true;
			}
			
			if(indicadorInformarParcela != null && indicadorInformarParcela != ''){
				if(indicadorInformarParcela == 1){
					form.indicadorInformarParcela[0].checked = true;
				}else{
					form.indicadorInformarParcela[1].checked = true;
				}
			}else{
				form.indicadorInformarParcela[0].checked = false;
				form.indicadorInformarParcela[1].checked = false;
			}
		}
		
		
		function validarCampos(){
		    var form = document.forms[0];
			var campos = "Informe: ";
			var retorno = true;
			
			if(form.numero == null || form.numero.value == ""){
				campos += "\nNúmero";
			}
			if(form.assunto == null || form.assunto.value == ""){
				campos += "\nAssunto";
			}
			if(form.dataVigenciaInicial == null || form.dataVigenciaInicial.value == ""){
				campos += "\nPeríodo de Vigência Inicial";
			}else{
				if(!verifica_data(form.dataVigenciaInicial)){
					alert("Período de Vigência Inicial Inválido");
					retorno = false;
				}
			}
			
			if(form.dataVigenciaFinal == null || form.dataVigenciaFinal.value == "" ){
				campos += "\nPeríodo de Vigência Final";
			}else{
				if(!verifica_data(form.dataVigenciaFinal)){
					alert("Período de Vigência Final Inválido");
					retorno = false;
				}
			}
			if(form.qtdFaturasParceladas == null || form.qtdFaturasParceladas.value == "" || form.qtdFaturasParceladas.value == "0"){
				campos += "\nNúmero Máximo de Parcelas ";
			}
			if(form.formaPgto.selectedIndex == 0){
				campos += "\nForma de Pagamento";
			}
			
			if(campos != "Informe: " ){
				alert(campos);
				retorno = false;
			}
			
			return retorno;
		
		}
		
		function verifica_data (data) { 

            dia = (data.value.substring(0,2)); 
            mes = (data.value.substring(3,5)); 
            ano = (data.value.substring(6,10)); 
 
            // verifica o dia valido para cada mes 
            if ((dia < 01)||(dia < 01 || dia > 30) && (  mes == 04 || mes == 06 || mes == 09 || mes == 11 ) || dia > 31) { 
                return false;
            } 

            // verifica se o mes e valido 
            if (mes < 01 || mes > 12 ) { 
                return false;
            } 

            // verifica se e ano bissexto 
            if (mes == 2 && ( dia < 01 || dia > 29 || ( dia > 28 && (parseInt(ano / 4) != ano / 4)))) { 
                return false;
            } 
    
            if (data.value == "") { 
                return false;
            } 
    
            return true;
          } 
		
		
		function verificaVirgula(event){
          	var char = null;
          		if (event.which == null){
				     char= String.fromCharCode(event.keyCode);    // IE
				}else if (event.which != 0 && event.charCode != 0){
				     char= String.fromCharCode(event.which);
				 }   
				 
				if(char != ','){
					return isCampoNumerico(event);
				}
			
          }
          
	 
		 function selecionouParcelJurosSim(){
			 var form = document.forms[0];
			 
			 form.taxaJurosAdd.value = "";
			 form.taxaJurosAdd.style.color = "#000000";
			 form.taxaJurosAdd.readOnly = false;
			 form.taxaJurosAdd.style.backgroundColor = '';
			 
		 	 form.indicadorInformarParcela.value = "2";
		 	 form.indicadorInformarParcela[0].checked = false;
		 	 form.indicadorInformarParcela[1].checked = true;
		 	 form.indicadorInformarParcela[0].disabled = true;
		 	 form.indicadorInformarParcela[1].disabled = true;
		 	 
		 }
	 
		 function selecionouParcelJurosNao(){
			 var form = document.forms[0];
			 
		 	 form.taxaJurosAdd.value = "";
			 form.taxaJurosAdd.style.color = "#000000";
			 form.taxaJurosAdd.readOnly = true;
			 form.taxaJurosAdd.style.backgroundColor = '#EFEFEF';
	 
		 	 form.indicadorInformarParcela[0].disabled = false;
		 	 form.indicadorInformarParcela[1].disabled = false;
		 	 
		 }
		
	</script>
</head>



<body leftmargin="5" topmargin="5" onload="javascript: verificaRadioButtons('<c:out value="${contratoAtualizar.indicadorDebitoAcrescimo}"></c:out>','<c:out value="${contratoAtualizar.indicadorParcelamentoJuros}"></c:out>','<c:out value="${contratoAtualizar.indicadorInformarParcela}"></c:out>')">


	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<html:form action="/atualizarResolucaoDiretoriaContratoParcelamentoAction.do"
		name="AtualizarResolucaoDiretoriaContratoParcelamentoActionForm"
		type="gcom.gui.cobranca.contratoparcelamento.AtualizarResolucaoDiretoriaContratoParcelamentoActionForm"
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


			<td valign="top" class="centercoltext">
				<table>
					<tr>
						<td></td>
					</tr>
				</table>
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
						<td class="parabg">Atualizar Resolu&ccedil;&atilde;o de Diretoria
						para Contrato de Parcelamento por Cliente</td>
						<td width="11" valign="top"><img border="0"
							src="imagens/parahead_right.gif" /></td>
					</tr>
				</table>
				<!--Fim Tabela Reference a Páginação da Tela de Processo-->
				<p>&nbsp;</p>
				<table width="100%" border="0">
					<tr>
						<td colspan="2">Para atualizar a resolu&ccedil;&atilde;o de
						diretoria, informe os dados abaixo:</td>
					</tr>
					<tr>
						<td width="230"><strong>N&uacute;mero da RD:</strong></td>
						<td width="383">
							<html:text property="numero" disabled="true" readonly="true" size="15" value="${contratoAtualizar.numero}"
							maxlength="15" />
						</td>
					</tr>
					<tr>
						<td><strong>Assunto da RD:<font color="#FF0000">*</font></strong></td>
						<td><html:text property="assunto" size="50" value="${contratoAtualizar.assunto}"
							maxlength="50" /></td>
					</tr>
					<tr>
						<td height="25"><strong>Per&iacute;odo de Vig&ecirc;ncia da RD:<font
							color="#FF0000">*</font></strong></td>
						<td align="right">
						<div align="left">
							<html:text value="${contratoAtualizar.dataVigenciaInicioFormatada}"
							onkeyup="mascaraData(this, event);limpaDataFinal();"
							property="dataVigenciaInicial" size="10" maxlength="10" /> <a
							href="javascript:abrirCalendario('AtualizarResolucaoDiretoriaContratoParcelamentoActionForm', 'dataVigenciaInicial')">
						<img border="0"
							src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
						a
						&nbsp; <html:text onkeyup="mascaraData(this, event);"
							property="dataVigenciaFinal" size="10" maxlength="10" value="${contratoAtualizar.dataVigenciaFinalFormatada}" /> <a
							href="javascript:abrirCalendario('AtualizarResolucaoDiretoriaContratoParcelamentoActionForm', 'dataVigenciaFinal')"><img
							border="0"
							src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
						dd/mm/aaaa
						
						</div>
						</td>
					</tr>
					<tr>
						<td height="12" colspan="2">
						<div align="left">
						<div align="left">
						<div align="left">
						<hr>
						</div>
						</div>
						</div>
						</td>
					</tr>
					<tr>
						<td height="25"><strong>D&eacute;bito com Acr&eacute;scimo:</strong><strong><span
							class="style2"><strong><font color="#FF0000">*</font></strong></span></strong></td>
						<td align="right">
							<div align="left">
								<html:radio property="indicadorDebitoAcrescimo" value="1"  ></html:radio>
								<strong>Sim</strong> 
								<html:radio property="indicadorDebitoAcrescimo" value="2"  ></html:radio>
								 <strong>N&atilde;o</strong>
							</div>
						</td>
					</tr>
					<tr>
						<td height="25"><strong>Parcelamento com Juros:</strong><strong><span
							class="style2"><strong><font color="#FF0000">*</font></strong></span></strong></td>
						<td align="right">
							<div align="left">
								<html:radio property="indicadorParcelamentoJuros" value="1" onclick="selecionouParcelJurosSim();" />
								<strong>Sim</strong> 
								<html:radio property="indicadorParcelamentoJuros" value="2" onclick="selecionouParcelJurosNao();" />
								 <strong>N&atilde;o</strong>
							</div>
						</td>
					</tr>
					<tr>
						<td height="25"><strong>Permite Informar o Valor da Parcela:</strong><strong><span
							class="style2"><strong><font color="#FF0000">*</font></strong></span></strong></td>
						<td align="right">
							<div align="left">
								<html:radio property="indicadorInformarParcela" value="1"  />
								<strong>Sim</strong> 
								<html:radio property="indicadorInformarParcela" value="2" />
								 <strong>N&atilde;o</strong>
							</div>
						</td>
					</tr>
					<tr>
						<td height="25"><strong>Número Máximo de Parcelas:</strong><strong><span
							class="style2"><strong><font color="#FF0000">*</font></strong></span></strong></td>
						<td align="right">
						<div align="left">
							<strong> 
								<html:text  property="qtdFaturasParceladas" size="3"  onkeyup="return verificaNumeroInteiro(this)"
									maxlength="3" value="${contratoAtualizar.qtdFaturasParceladas}"  ></html:text>
							</strong>
						</div>
						</td>
					</tr>
					<tr>
						<td height="25"><strong>Forma de Pagamento:</strong><strong><span
							class="style2"><strong><font color="#FF0000">* </font></strong></span></strong></td>
						<td align="right">
						<div align="left"><strong> 
						<html:select property="formaPgto">
							<option value=""></option>
							
							<logic:notEmpty name="collFormaPgto">
								<logic:iterate name="collFormaPgto" id="formaPgto">
									<option value="${formaPgto.id}" 
										<c:if test="${contratoAtualizar.cobrancaForma != null && formaPgto.id == contratoAtualizar.cobrancaForma.id}">selected="selected"</c:if>>
										<c:out value="${formaPgto.descricao}"></c:out>
									</option>
								</logic:iterate>
							</logic:notEmpty>
							
						</html:select>
						
						</strong>
						
						</div>
						</td>
					</tr>
					<tr>
						<td height="14" colspan="2">
						<div align="left">
						<div align="left">
						<div align="left">
						<hr>
						</div>
						</div>
						</div>
						</td>
					</tr>
					<tr>
						<td height="25"><strong>Parcelas:</strong></td>
						<td align="right">
							<div align="left">
								<strong> 
									<input 
										name="pacerlaAdd"  
										onkeyup="return verificaNumeroInteiro(this)"
										type="text" 
										size="3" 
										maxlength="3" 
										style="text-align:right;"
									/> 
									
								</strong>
							</div>
						</td>
					</tr>
					<tr>
						<td height="25"><strong>Taxa de Juros:</strong></td>
						<td align="right">
							<div align="left">
								<strong> 
									<input 
										name="taxaJurosAdd" 
										type="text" 
										size="6" 
										maxlength="6"  
										onkeypress="return verificaVirgula(event);"
										onkeyup="formataValorMonetario(this, 6);"
										style="text-align:right;"
										/>
									
								 </strong>
							 </div>
						</td>
					</tr>
					<tr>
						<td height="25">&nbsp;</td>
						<td align="right">
						<div align="right">
							<input name="btnAddParcela" type="button"
									class="bottonRightCol" value="Adicionar" 
							onClick="adicionarParcela();" />
						</div>
						</td>
					</tr>
					<tr>
						<td height="25" colspan="2">
						<div align="left">
						<table width="580" border="0" bgcolor="#90c7fc" id="tbParcelas">
							<tr>
								<td width="100" bgcolor="#90c7fc">
									<div align="center"><strong>Remover</strong></div>
									<div align="center"></div>
								</td>
								<td width="263" bgcolor="#90c7fc">
									<div align="center"><strong>Parcelas</strong><strong></strong></div>
								</td>
								<td width="232" bgcolor="#90c7fc">
									<div align="center">
									<div align="center"><strong>Taxa de Juros (%)</strong></div>
									</div>
								</td>
							</tr>
							
							<% String corTabelaParcelas = "#FFFFFF"; %>
							<logic:notEmpty name="parcelas">
								<logic:iterate name="parcelas" id="parcela">
									<tr bgcolor="<%=corTabelaParcelas %>">
										<td width="100" align="center"> 
											<a onclick="javascript: removerParcela('<c:out value="${parcela.qtdFaturasParceladas}"></c:out>');" href="#"><img src="imagens/Error.gif" style="border: 0px;" alt="" width="14" height="14"></a>
										</td>
										<td width="263" align="center">
											<c:out value="${parcela.qtdFaturasParceladas}"></c:out>
										</td>
										<td width="232" align="center">
											<fmt:formatNumber value="${parcela.taxaJuros}" pattern="0.00" />
										</td>
									</tr>
									<%
										if(corTabelaParcelas.equals("#FFFFFF")){
											corTabelaParcelas = "#cbe5fe";
										}else{
											corTabelaParcelas = "#FFFFFF";
										}
									%>
								</logic:iterate>
							</logic:notEmpty>
						</table>
						</div>
						</td>
					</tr>
					<tr>
						<td><strong> <font color="#FF0000"></font></strong></td>
						<td align="right">
						<div align="left"><strong><font color="#FF0000">*</font></strong>
						Campos obrigat&oacute;rios</div>
						</td>
					</tr>
					<tr>
						<td><strong> <font color="#FF0000"> 
						<input type="button" onclick="javascritp:desfazer();" class="bottonRightCol" value="Desfazer"> 
						<input type="button" onclick="javascritp:cancelar();" class="bottonRightCol" value="Cancelar"> </font></strong></td>
						<td align="right"><input type="button" name="Submit"
							class="bottonRightCol" value="Atualizar" onclick="javascript: atualizar();"></td>
					</tr>
				</table>
				
		
			</td>
			
	</table>
	
	</html:form>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:html>

