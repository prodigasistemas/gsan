<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.List" %>
<%@ page import="gcom.util.Util" %>
<%@ page import="gcom.cobranca.bean.ContaValoresHelper" %>
<%@ page import="gcom.faturamento.conta.Conta" %>
<%@ page import="gcom.gui.cobranca.contratoparcelamento.DebitosClienteHelper" %>
<%@ page import="gcom.faturamento.debito.DebitoACobrar"%>
<script language="javascript">
	var todasContasMarcadas = false;
	var todasDebitosMarcados = false;
	
	function selecionarTodas(){
		
		var form = document.forms[0];
		var checkBoxs = form.contasSelecao;
		 
		if(checkBoxs != null && checkBoxs.length == null){
			checkBoxs.checked = !todasContasMarcadas;
	  }else{
	      	for(var i = 0; i < checkBoxs.length; i++){
	      		
	      		checkBoxs[i].checked = !todasContasMarcadas;
	      		
	      	}
	  }
		
      	
      	todasContasMarcadas = !todasContasMarcadas;
	}
	
	function selecionarContaDebitos(){
		
		var form = document.forms[0];
		var checkBoxs = form.contasSelecao;
		var contasSelecionadas = form.contasSelecionadas.value;
		myString = new String(contasSelecionadas);
		splitString = myString.split(",");
		
		if (checkBoxs != null && checkBoxs.length == null) {
			if(contaSelecionada(checkBoxs.value, splitString)){
	   			checkBoxs.checked = true;
	   		} else {
	   			checkBoxs.checked = false;
	   		}
		} else {
		   	for(var i = 0; i < checkBoxs.length; i++){
		   		if(contaSelecionada(checkBoxs[i].value, splitString)){
		   			checkBoxs[i].checked = true;
		   		} else {
		   			checkBoxs[i].checked = false;
		   		}
		   	}
	   	}
	}
	
	function contaSelecionada(conta, splitString){
		for(var j = 0; j < splitString.length; j++){
	   		if(conta.trim() == splitString[j].trim()){
	   			return true;
	   		}
	   	}
	   	
	   	return false;
	}
	
	function selecionarDebitosACobrar(){
		
		var form = document.forms[0];
		var checkBoxs = form.debitosSelecao;
		var debitosACobrarSelecionados = form.debitosACobrarSelecionados.value;
		myString = new String(debitosACobrarSelecionados);
		splitString = myString.split(",");
		
		if (checkBoxs != null && checkBoxs.length == null) {
			if(debitoSelecionado(checkBoxs.value, splitString)){
	   			checkBoxs.checked = true;
	   		} else {
	   			checkBoxs.checked = false;
	   		}
		} else {
		   	for(var i = 0; i < checkBoxs.length; i++){
		   		if(debitoSelecionado(checkBoxs[i].value, splitString)){
		   			checkBoxs[i].checked = true;
		   		} else {
		   			checkBoxs[i].checked = false;
		   		}
		   	}
	   	}
	}
	
	function debitoSelecionado(debito, splitString){
		for(var j = 0; j < splitString.length; j++){
	   		if(debito.trim() == splitString[j].trim()){
	   			return true;
	   		}
	   	}
	   	
	   	return false;
	}
	
	function selecionarTodosDebitos(){
		
		var form = document.forms[0];
		var checkBoxs = form.debitosSelecao;
		
		if (checkBoxs != null && checkBoxs.length == null) {
		
			checkBoxs.checked = !todasDebitosMarcados;
			
	    } else {
	      	for (var i = 0; i < checkBoxs.length; i++) {
	      		
	      		checkBoxs[i].checked = !todasDebitosMarcados;
	      		
	      	}
	    }
      	
      	todasDebitosMarcados = !todasDebitosMarcados;
	}
	
</script>
<div style="width: 600px; height: 100%; max-height: 300px;  overflow: auto;">
	<table width="100%" align="center" bgcolor="#90c7fc" border="0">
			<%String cor = "#cbe5fe";%>
			<%cor = "#cbe5fe";%>
			<tr bordercolor="#79bbfd">
				<td colspan="12" align="center" bgcolor="#79bbfd">
				<strong>Contas</strong>
				</td>
			</tr>

			<logic:notEmpty name="colecaoContaValores" scope="session">
				<tr bordercolor="#000000">
					
					<td width="4%" bgcolor="#90c7fc">
						<div align="center"><strong><a href="#" onclick="selecionarTodas();">Todas</a></strong></div>
					</td>
				
					<td width="4%" bgcolor="#90c7fc">
						<div align="center"><strong>Matr.</strong></div>
					</td>
				
					<td width="9%" bgcolor="#90c7fc">
					<div align="center" class="style9"><font color="#000000"
						style="font-size:9px"
						face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano</strong>
					</font></div>
					</td>
					<td width="10%" bgcolor="#90c7fc">
					<div align="center" class="style9"><font color="#000000"
						style="font-size:9px"
						face="Verdana, Arial, Helvetica, sans-serif"> <strong>Venc.</strong>
					</font></div>
					</td>
					<td width="8%" bgcolor="#90c7fc">
					<div align="center" class="style9"><font color="#000000"
						style="font-size:9px"
						face="Verdana, Arial, Helvetica, sans-serif"> <strong>Val.
					&Aacute;gua </strong> </font></div>
					</td>
					<td width="8%" bgcolor="#90c7fc">
					<div align="center" class="style9"><font color="#000000"
						style="font-size:9px"
						face="Verdana, Arial, Helvetica, sans-serif"> <strong>Val.
					Esgoto</strong> </font></div>
					</td>
					<td width="8%" bgcolor="#90c7fc">
					<div align="center" class="style9"><font color="#000000"
						style="font-size:9px"
						face="Verdana, Arial, Helvetica, sans-serif"> <strong>Val.
					<br>
					D&eacute;bitos</strong> </font></div>
					</td>
					<td width="10%" bgcolor="#90c7fc">
					<div align="center" class="style9"><font color="#000000"
						style="font-size:9px"
						face="Verdana, Arial, Helvetica, sans-serif"> <strong>Val.
					Creditos</strong> </font></div>
					</td>
					
					
					<td width="10%" bgcolor="#90c7fc">
					  <div align="center" class="style9">
					    <font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
					      <strong>Val.	Impostos</strong> 
					    </font>
					  </div>
					</td>
					
					
					<td width="8%" bgcolor="#90c7fc">
					<div align="center" class="style9"><font color="#000000"
						style="font-size:9px"
						face="Verdana, Arial, Helvetica, sans-serif"> <strong>Val.
					Conta</strong> </font></div>
					</td>
					<td width="7%" bgcolor="#90c7fc">
					<div align="center" class="style9"><font color="#000000"
						style="font-size:9px"
						face="Verdana, Arial, Helvetica, sans-serif"> <strong>Acr&eacute;sc.
					Impont.</strong><strong></strong> </font></div>
					</td>
					<td width="8%" bgcolor="#90c7fc">
					<div align="center" class="style9"><font color="#000000"
						style="font-size:9px"
						face="Verdana, Arial, Helvetica, sans-serif"> <strong>Sit.</strong>
					</font></div>
					</td>
				</tr>
				
				<tr>
					<td height="100%" colspan="12">
					<html:hidden property="contasSelecionadas" /> 
					
					<table width="100%">
						<logic:present name="colecaoContaValores">
							<logic:iterate name="colecaoContaValores"
								id="contavaloreshelper">
								<%if (cor.equalsIgnoreCase("#cbe5fe")) {
									cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
									cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>
									<bean:define name="contavaloreshelper" property="conta"	id="conta" /> 
									
									
								 <td width="4%">
								 	<div align="center">
								 		<html:checkbox property="contasSelecao" value="${conta.id}"/>
                                    </div>
                                   </td>
						
									
                                    <td width="4%">
									 	<div align="center">
										 	<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
												<font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> 
											 		<c:out value="${conta.imovel.id}" /> 
											 	</font>
										 	</logic:empty>
										 	<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
												<font color="#ff0000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif">
													<c:out value="${conta.imovel.id}" /> 
											 	</font>
										 	</logic:notEmpty>
									 	</div>
                                    </td>
									
									<td width="9%" align="left">
										<div align="left" class="style9">
										
										<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
											<font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> 
											<logic:notEmpty
												name="contavaloreshelper" property="conta">
												<a
													href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
												
												<bean:write name="conta"
													property="formatarAnoMesParaMesAno" /></a>
											</logic:notEmpty> </font>
										</logic:empty>

										<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
											<font color="#ff0000"
											style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif">
											 <logic:notEmpty name="contavaloreshelper" property="conta">
											<a
												href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
											<font color="#ff0000"><bean:write name="conta"
												property="formatarAnoMesParaMesAno" /></font> </a>
											</logic:notEmpty> </font>
										</logic:notEmpty>
										</div>
									</td>
									
									<td width="10%" align="left">
										<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
											<div align="left" class="style9"><font color="#000000"
											style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> 
											<logic:notEmpty
												name="contavaloreshelper" property="conta">
												<bean:define name="contavaloreshelper" property="conta"
													id="conta" />
												<bean:write name="conta" property="dataVencimentoConta"
													formatKey="date.format.small" />
											</logic:notEmpty> </font></div>
										</logic:empty>

										<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
											<div align="left" class="style9"><font color="#ff0000"
											style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> 
										<logic:notEmpty
											name="contavaloreshelper" property="conta">
											<bean:define name="contavaloreshelper" property="conta"
												id="conta" />
											<bean:write name="conta" property="dataVencimentoConta"
												formatKey="date.format.small" />
											</logic:notEmpty> </font></div>
										</logic:notEmpty>
									</td>
									
									<td width="8%" align="right">
																					
										<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
											<div align="right" class="style9"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
													name="contavaloreshelper" property="conta">
													<bean:define name="contavaloreshelper" property="conta"
														id="conta" />
													<bean:write name="conta" property="valorAgua"
														formatKey="money.format" />
												</logic:notEmpty> </font></div>
										</logic:empty>

										<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
											<div align="right" class="style9"><font color="#ff0000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
												name="contavaloreshelper" property="conta">
												<bean:define name="contavaloreshelper" property="conta"
													id="conta" />
												<bean:write name="conta" property="valorAgua"
													formatKey="money.format" />
											</logic:notEmpty> </font></div>
										</logic:notEmpty>
									</td>
									
									<td width="8%" align="right">
										<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
											<div align="right" class="style9"><font color="#000000"
											style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
											name="contavaloreshelper" property="conta">
											<bean:define name="contavaloreshelper" property="conta"
												id="conta" />
											<bean:write name="conta" property="valorEsgoto"
												formatKey="money.format" />
										</logic:notEmpty> </font></div>
										</logic:empty>

										<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
											<div align="right" class="style9"><font color="#ff0000"
											style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
											name="contavaloreshelper" property="conta">
											<bean:define name="contavaloreshelper" property="conta"
												id="conta" />
											<bean:write name="conta" property="valorEsgoto"
												formatKey="money.format" />
										</logic:notEmpty> </font></div>
										</logic:notEmpty>
									</td>
									
									<td width="8%" align="right">
																					
									<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
									<div align="right" class="style9"><font color="#000000"
											style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
											name="contavaloreshelper" property="conta">
											<logic:notEqual name="contavaloreshelper"
												property="conta.debitos" value="0">
												<a
													href="javascript:abrirPopup('exibirConsultarDebitoCobradoAction.do?contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
												<bean:write name="contavaloreshelper"
													property="conta.debitos" formatKey="money.format" /> </a>
											</logic:notEqual>
											<logic:equal name="contavaloreshelper"
												property="conta.debitos" value="0">
												<bean:write name="contavaloreshelper"
													property="conta.debitos" formatKey="money.format" />
											</logic:equal>
										</logic:notEmpty> </font></div>
									</logic:empty>

									<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
										<div align="right" class="style9"><font color="#ff0000"
											style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
											name="contavaloreshelper" property="conta">
											<logic:notEqual name="contavaloreshelper"
												property="conta.debitos" value="0">
												<a
													href="javascript:abrirPopup('exibirConsultarDebitoCobradoAction.do?contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
												<font color="#ff0000"><bean:write name="contavaloreshelper"
													property="conta.debitos" formatKey="money.format" /> </font></a>
											</logic:notEqual>
											<logic:equal name="contavaloreshelper"
												property="conta.debitos" value="0">
												<bean:write name="contavaloreshelper"
													property="conta.debitos" formatKey="money.format" />
											</logic:equal>
										</logic:notEmpty> </font></div>
									</logic:notEmpty>
									
									</td>
									
									
									<td width="10%" align="right">
																					
									<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
									<div align="right" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="contavaloreshelper" property="conta">
										<logic:notEqual name="contavaloreshelper"
											property="conta.valorCreditos" value="0">
											<a
												href="javascript:abrirPopup('exibirConsultarCreditoRealizadoAction.do?contaID=<bean:define name="contavaloreshelper"	property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
											<bean:write name="contavaloreshelper"
												property="conta.valorCreditos" formatKey="money.format" />
											</a>
										</logic:notEqual>
										<logic:equal name="contavaloreshelper"
											property="conta.valorCreditos" value="0">
											<bean:write name="contavaloreshelper"
												property="conta.valorCreditos" formatKey="money.format" />
										</logic:equal>
									</logic:notEmpty> </font></div>
									</logic:empty>

									<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
										<div align="right" class="style9"><font color="#ff0000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="contavaloreshelper" property="conta">
										<logic:notEqual name="contavaloreshelper"
											property="conta.valorCreditos" value="0">
											<a
												href="javascript:abrirPopup('exibirConsultarCreditoRealizadoAction.do?contaID=<bean:define name="contavaloreshelper"	property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
											<font color="#ff0000"><bean:write name="contavaloreshelper"
												property="conta.valorCreditos" formatKey="money.format" /></font>
											</a>
										</logic:notEqual>
										<logic:equal name="contavaloreshelper"
											property="conta.valorCreditos" value="0">
											<bean:write name="contavaloreshelper"
												property="conta.valorCreditos" formatKey="money.format" />
										</logic:equal>
									</logic:notEmpty> </font></div>
									</logic:notEmpty>
									
									</td>
									
									
									<td width="10%" align="right">
																					
										<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
											<div align="right" class="style9">
											  <font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											    <logic:notEmpty	name="contavaloreshelper" property="conta">
													<bean:define name="contavaloreshelper" property="conta"	id="conta" />
													<c:if test="${conta.valorImposto == null }">
														0,00
													</c:if>
													<c:if test="${conta.valorImposto != null }">
														<bean:write name="conta" property="valorImposto" formatKey="money.format" />
													</c:if>
												</logic:notEmpty> 
											  </font>
											</div>
										</logic:empty>

										<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
											<div align="right" class="style9">
											  <font color="#ff0000"	style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											    <logic:notEmpty	name="contavaloreshelper" property="conta">
												  <bean:define name="contavaloreshelper" property="conta"	id="conta" />
												  <bean:write name="conta" property="valorImposto" formatKey="money.format" />
											    </logic:notEmpty> 
											  </font>
											</div>
										</logic:notEmpty>
										
									</td>
									
									
									
									<td width="8%" align="right">
																					
										<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
										<div align="right" class="style9"><font color="#000000"
											style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
											name="contavaloreshelper" property="conta">
											<bean:define name="contavaloreshelper" property="conta"
												id="conta" />
											<bean:write name="conta" property="valorTotal"
												formatKey="money.format" />
										</logic:notEmpty> </font></div>
										</logic:empty>

										<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
											<div align="right" class="style9"><font color="#ff0000"
											style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
											name="contavaloreshelper" property="conta">
											<bean:define name="contavaloreshelper" property="conta"
												id="conta" />
											<bean:write name="conta" property="valorTotal"
												formatKey="money.format" />
										</logic:notEmpty> </font></div>
										</logic:notEmpty>
									</td>
									
									<td width="8%" align="right">
								
																					
									<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
							<div align="right" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEqual
										name="contavaloreshelper" property="valorTotalContaValores"
										value="0">
										<a
											href="javascript:abrirPopup('exibirValorAtualizacaoConsultarPopupAction.do?multa=<bean:write name="contavaloreshelper" property="valorMulta" />&juros=<bean:write name="contavaloreshelper" property="valorJurosMora" />&atualizacao=<bean:write name="contavaloreshelper" property="valorAtualizacaoMonetaria" />', 300, 650);">
										<bean:write name="contavaloreshelper"
											property="valorTotalContaValores" formatKey="money.format" />
										</a>
									</logic:notEqual> <logic:equal name="contavaloreshelper"
										property="valorTotalContaValores" value="0">
										<bean:write name="contavaloreshelper"
											property="valorTotalContaValores" formatKey="money.format" />
									</logic:equal> </font></div>
									</logic:empty>

									<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
										<div align="right" class="style9"><font color="#ff0000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEqual
										name="contavaloreshelper" property="valorTotalContaValores"
										value="0">
										<a
											href="javascript:abrirPopup('exibirValorAtualizacaoConsultarPopupAction.do?multa=<bean:write name="contavaloreshelper" property="valorMulta" />&juros=<bean:write name="contavaloreshelper" property="valorJurosMora" />&atualizacao=<bean:write name="contavaloreshelper" property="valorAtualizacaoMonetaria" />', 300, 650);">
										<font color="#ff0000"><bean:write name="contavaloreshelper"
											property="valorTotalContaValores" formatKey="money.format" /></font>
										</a>
									</logic:notEqual> <logic:equal name="contavaloreshelper"
										property="valorTotalContaValores" value="0">
										<bean:write name="contavaloreshelper"
											property="valorTotalContaValores" formatKey="money.format" />
									</logic:equal> </font></div>
									</logic:notEmpty>
									
									
									
									</td>
									<td width="7%" align="left">
																					
									<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
							<div align="left" class="style9" title="${contavaloreshelper.conta.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao}"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="contavaloreshelper" property="conta">
										<bean:define name="contavaloreshelper" property="conta"
											id="conta" />
										<bean:define name="conta"
											property="debitoCreditoSituacaoAtual"
											id="debitoCreditoSituacaoAtual" />
										<bean:write name="debitoCreditoSituacaoAtual"
											property="descricaoAbreviada" />
									</logic:notEmpty> </font></div>
									</logic:empty>

									<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
										<div align="left" class="style9"><font color="#ff0000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif" title="${contavaloreshelper.conta.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao}" > <logic:notEmpty
										name="contavaloreshelper" property="conta">
										<bean:define name="contavaloreshelper" property="conta"
											id="conta" />
										<bean:define name="conta"
											property="debitoCreditoSituacaoAtual"
											id="debitoCreditoSituacaoAtual" />
										<bean:write name="debitoCreditoSituacaoAtual"
											property="descricaoAbreviada" />
									</logic:notEmpty> </font></div>
									</logic:notEmpty>
									
									
									
									</td>
								</tr>
							</logic:iterate>
							<logic:notEmpty name="colecaoContaValores">
								<%if (cor.equalsIgnoreCase("#cbe5fe")) {
										cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
										cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>
									<td bgcolor="#90c7fc"></td>
									<td bgcolor="#90c7fc"></td>
									<td bgcolor="#90c7fc" align="center">
									<div class="style9" align="center"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong>
									</font></div>
									</td>
									<td align="left">
									<%=((Collection) session.getAttribute("colecaoContaValores")).size()%>
									&nbsp;
									doc(s)
									</td>
									<td align="right">
									<div align="right"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorAgua")%>
									</font></div>
									</td>
									<td align="rigth">
									<div align="right"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorEsgoto")%>
									</font></div>
									</td>
									<td align="right">
									<div align="right"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorDebito")%>
									</font></div>
									</td>
									<td align="right">
									<div align="right"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorCredito")%>
									</font></div>
									</td>
									
									
									
									
									
									<td align="right">
									  <div align="right">
									    <font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
									      <%=session.getAttribute("valorImposto")%>
									    </font>
									  </div>
									</td>
									
									
									
									<td align="right">
									<div align="right"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorConta")%>
									</font></div>
									</td>
									<td align="right">
									<div align="right" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorAcrescimo")%>
									</font></div>
									</td>
									<td align="left" bgcolor="#90c7fc">
									<div align="left"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> </font></div>
									</td>
								</tr>
							</logic:notEmpty>
						</logic:present>
					</table>
					
					</td>
				</tr>
			</logic:notEmpty>
			</table>
		</div>
		<div style="width: 600px; height: 50%;">
			<table width="100%" align="center" border="0">
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
			</table>
		</div>
		
		<div style="width: 600px; height: 100%; max-height: 300px; overflow: auto;">
			<table width="100%" align="center" bgcolor="#90c7fc" border="0">
					<tr>
						<td colspan="4">
						<table width="100%" align="center" bgcolor="#90c7fc" border="0">
							<tr bordercolor="#79bbfd">
								<td colspan="7" bgcolor="#79bbfd" align="center"><strong>D&eacute;bitos
								A Cobrar</strong></td>
							</tr>
							<tr bordercolor="#000000">
								<td width="4%" bgcolor="#90c7fc">
									<div align="center"><strong><a href="#" onclick="selecionarTodosDebitos();">Todos</a></strong></div>
								</td>
								<td width="4%" bgcolor="#90c7fc">
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Matr.
									</strong> </font></div>
								</td>
								<td width="42%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo do
								D&eacute;bito</strong> </font></div>
								</td>
								<td width="13%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano
								Refer&ecirc;ncia</strong> </font></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano
								Cobran&ccedil;a</strong> </font></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Parcelas a
								cobrar</strong> </font></div>
								</td>
								<td width="17%" bgcolor="#90c7fc" height="20">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor a
								cobrar</strong> </font></div>
								</td>
							</tr>
							<%String cor1 = "#cbe5fe";%>
							<html:hidden property="debitosACobrarSelecionados" /> 	
							
							<logic:present name="colecaoDebitoACobrar">
								<logic:iterate name="colecaoDebitoACobrar" id="debitoacobrar">
									<%if (cor1.equalsIgnoreCase("#cbe5fe")) {
					cor1 = "#FFFFFF";%>
									<tr bgcolor="#FFFFFF">
										<%} else {
					cor1 = "#cbe5fe";%>
									<tr bgcolor="#cbe5fe">
										<%}%>
									 	<td>
									 		<div align="center">
								 				<html:checkbox property="debitosSelecao" value="${debitoacobrar.id}"/>
										 	</div>
	                                    </td>
	                                    <td width="4%">
										 	<div align="center">
										 	<logic:empty name="debitoacobrar" property="contaMotivoRevisao">
												<font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> 
											 		<c:out value="${debitoacobrar.imovel.id}" /> 
												</font>
											</logic:empty>
							
											<logic:notEmpty name="debitoacobrar" property="contaMotivoRevisao">
												<font color="#ff0000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif">
					 								<c:out value="${debitoacobrar.imovel.id}" /> 
												</font>
											</logic:notEmpty>
										 	</div>
	                                    </td>
										<td>
											<div align="left" class="style9">
												<font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> 
													<logic:notEmpty name="debitoacobrar" property="imovel">
														<a href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:define name="debitoacobrar" property="imovel" id="imovel" /><bean:write name="imovel" property="id" />&debitoID=<bean:write name="debitoacobrar" property="id" />', 570, 720);">
															<bean:define name="debitoacobrar" property="debitoTipo"
																id="debitoTipo" /> 
															<logic:notEmpty name="debitoTipo" property="descricao">
																<logic:empty name="debitoacobrar" property="contaMotivoRevisao">
																	<font color="#000000"
																		style="font-size:9px"
																		face="Verdana, Arial, Helvetica, sans-serif"> 
																<bean:write name="debitoTipo" property="descricao" />
																	</font>
																</logic:empty>
							
																<logic:notEmpty name="debitoacobrar" property="contaMotivoRevisao">
																	<font color="#ff0000"
																	style="font-size:9px"
																	face="Verdana, Arial, Helvetica, sans-serif">
																<bean:write name="debitoTipo" property="descricao" />
																	</font>
																</logic:notEmpty>
															</logic:notEmpty> 
														</a>
													</logic:notEmpty> 
												</font>
											</div>
										</td>
										<td>
											<div align="center" class="style9"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
												name="debitoacobrar" property="anoMesReferenciaDebito">
													<logic:empty name="debitoacobrar" property="contaMotivoRevisao">
														<font color="#000000"
															style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> 
															<%= Util.formatarAnoMesParaMesAno(((DebitoACobrar)debitoacobrar).getAnoMesReferenciaDebito().toString()) %>
														</font>
													</logic:empty>
				
													<logic:notEmpty name="debitoacobrar" property="contaMotivoRevisao">
														<font color="#ff0000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
															<%= Util.formatarAnoMesParaMesAno(((DebitoACobrar)debitoacobrar).getAnoMesReferenciaDebito().toString()) %>
														</font>
													</logic:notEmpty>
											</logic:notEmpty> </font></div>
										</td>
										<td>
											<div align="center" class="style9"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
												name="debitoacobrar" property="anoMesCobrancaDebito">
													<logic:empty name="debitoacobrar" property="contaMotivoRevisao">
														<font color="#000000"
															style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> 
													<%= Util.formatarAnoMesParaMesAno(((DebitoACobrar)debitoacobrar).getAnoMesCobrancaDebito().toString()) %>
														</font>
													</logic:empty>
				
													<logic:notEmpty name="debitoacobrar" property="contaMotivoRevisao">
														<font color="#ff0000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
													<%= Util.formatarAnoMesParaMesAno(((DebitoACobrar)debitoacobrar).getAnoMesCobrancaDebito().toString()) %>
														</font>
													</logic:notEmpty>
											</logic:notEmpty> </font></div>
										</td>
										<td>
											<div align="center" class="style9"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> 
												<logic:notEmpty
												name="debitoacobrar" property="parcelasRestanteComBonus">
													<logic:empty name="debitoacobrar" property="contaMotivoRevisao">
														<font color="#000000"
															style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> 
															<bean:write name="debitoacobrar" property="parcelasRestanteComBonus" />
														</font>
													</logic:empty>
				
													<logic:notEmpty name="debitoacobrar" property="contaMotivoRevisao">
														<font color="#ff0000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
															<bean:write name="debitoacobrar" property="parcelasRestanteComBonus" />
														</font>
													</logic:notEmpty>
												</logic:notEmpty> 
												</font>
											</div>
										</td>
										<td>
											<div align="right" class="style9"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> 
												<logic:notEmpty
													name="debitoacobrar" property="valorTotalComBonus">
													<logic:empty name="debitoacobrar" property="contaMotivoRevisao">
														<font color="#000000"
															style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> 
															<bean:write name="debitoacobrar" property="valorTotalComBonus"
																formatKey="money.format" />
														</font>
													</logic:empty>
				
													<logic:notEmpty name="debitoacobrar" property="contaMotivoRevisao">
														<font color="#ff0000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif">
															<bean:write name="debitoacobrar" property="valorTotalComBonus"
																formatKey="money.format" />
														</font>
													</logic:notEmpty>
												</logic:notEmpty> 
												</font>
											</div>
										</td>
									</tr>
								</logic:iterate>
								<%if (cor1.equalsIgnoreCase("#cbe5fe")) {
								cor1 = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
								cor1 = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>
								<td bgcolor="#90c7fc" colspan="3">
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong>
									</font></div>
								</td>
								<td>
									<%=((Collection) session.getAttribute("colecaoDebitoACobrar")).size() %>
										&nbsp;
										doc(s)
								</td>
								<td width="15%" ></td>
								<td width="15%" ></td>
								<td width="15%" >
									<div align="right" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorDebitoACobrar")%>
									</font></div>
								</td>
		
							</tr>
							</logic:present>
						</table>
						</td>
					</tr>
			</table>
		</div>
		
		<div style="width: 600px; height: 50%;">
			<table width="100%" align="center" border="0">
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
			</table>
		</div>
		
		<div align="left">
			<table>
				<tr>
					<td>
						<b>Total do Débido:</b>
					</td>
					<td>
						<font color="#000000"	style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
							<input type="text" disabled="disabled"  size="9" value="<%=session.getAttribute("valorTotalSemAcrescimo")%>" />
						</font>
					</td>
				</tr>
				<tr>
					<td>
						<b>Total do Débito Atualizado:</b> 
					</td>
					<td>
						<font color="#000000"	style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
							<input type="text" disabled="disabled"  size="9" value="<%=session.getAttribute("valorTotalComAcrescimo")%>" />
						</font>
					</td>
				</tr>
			</table>
		</div>
<c:if test="${finalizou != null}">
<script type="text/javascript">
	<%
	List<DebitosClienteHelper> listaDebitos = (List<DebitosClienteHelper>) session.getAttribute("listaDebitos");
	List<DebitosClienteHelper> listaDebitosACobrar = (List<DebitosClienteHelper>) session.getAttribute("listaDebitosACobrar");
	
	String contasSelecionadas = (String) session.getAttribute("contasSelecionadas");
	String debitosACobrarSelecionados = (String) session.getAttribute("debitosACobrarSelecionados");
	
	if (listaDebitos != null && !listaDebitos.isEmpty()
			&& contasSelecionadas != null && !contasSelecionadas.trim().equals("")) {
		out.println("selecionarContaDebitos();");
	}
	if (listaDebitosACobrar != null && !listaDebitosACobrar.isEmpty()
			&& debitosACobrarSelecionados != null && !debitosACobrarSelecionados.trim().equals("")) {
		out.println("selecionarDebitosACobrar();");
	}
	
	%>		
</script>
</c:if>
