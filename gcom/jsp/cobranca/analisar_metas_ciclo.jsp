<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.Pagina, gcom.util.ConstantesSistema" %>
<%@ page import="java.util.Collection, java.util.Iterator, gcom.cobranca.InformarCicloMetaGrupoHelper" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript">
<!-- Begin

 var bCancel = false;
 
	function extendeTabela(tabela,display){
		var form = document.forms[0];

		if(display){
 			eval('layerHide'+tabela).style.display = 'none';
 			eval('layerShow'+tabela).style.display = 'block';
		}else{
			eval('layerHide'+tabela).style.display = 'block';
 			eval('layerShow'+tabela).style.display = 'none';
		}
	} 

    function validateAnalisarMetasCicloActionForm(form) {
        if (bCancel)
      return true;
        else
       return validaMesAno(form.anoMesCobranca);
   }

	function validaMesAno(anoMes){
		if(anoMes.value != ""){
			return verificaAnoMes(anoMes);
		}else{
			return true;
		}
	}
	
	function IntegerValidations () { 
       this.aa = new Array("metaTotal", "Meta total deve conter somente números positivos.", new Function ("varName", " return this[varName];"));
    }
	
	function voltarConsulta(){
		var form = document.forms[0];
		form.action = 'exibirAnalisarMetasCicloAction.do?voltar=1';
	  	form.submit();
	}
	
	function chamarFiltrar(){
	  var form = document.forms[0];
	  if (validateAnalisarMetasCicloActionForm(form)) {
	  	form.submit();
	  }
	}
	
	function gerarRelatorio() {

		var form = document.forms[0];
		if (form.idCobrancaAcao.value != "-1" && form.anoMesCobranca.value != "") {
			if(validateAnalisarMetasCicloActionForm(form)){
				form.action = 'gerarRelatorioAnalisarMetasCicloAction.do?idCobrancaAcao='+form.idCobrancaAcao.value+'&&' ;
				form.submit();
			}
			
		} else {
			alert("Todos os campos devem ser preenchidos");
		}
	}
	
	function limparForm(){
		var form = document.forms[0];
		
		form.idCobrancaAcao.value = "-1";
		form.anoMesCobranca.value = "";
	}
/*
 	function redistribuirAlteracao(obj){
		var nomeAlterado = obj.name;
		var form = document.forms[0];
		
		var textos = document.getElementsByTagName("INPUT");

		var iHid = nomeAlterado.indexOf('_Hide');
		
		var valorAnteriorObjetoAlterado = '';
		var valorObjetoAlterado = obj.value;
		
		if (iHid == -1){
			var objHidden = eval('document.forms[0].' + nomeAlterado + '_Hide');
		} else {
			nomeAlterado = nomeAlterado.substr(0,iHid);
			var objHidden = eval('document.forms[0].' + nomeAlterado);
		}
		if (objHidden != null) {
			valorAnteriorObjetoAlterado = objHidden.value;
			//objHidden.value = obj.value;
		}
		var diferenca = parseInt(obj.value) - parseInt(valorAnteriorObjetoAlterado);
		// caso não tenha L => objeto alterado não foi uma localidade, dai.. 
		// vamos voltar o valor antes da alteracao, pois o novo valor sera calculado
		if(nomeAlterado.indexOf('L') == -1){
			obj.value = valorAnteriorObjetoAlterado;  			
		}
		
		var diferencaAcumulada = 0;
		var ultimoIndiceAlterado = -1;
		
		if (textos != null) {
			for(i = 0; i < textos.length; i++){
			    var nomeElemento = textos[i].name;
				if (textos[i].type == 'text' 
					&& nomeElemento.indexOf(nomeAlterado) != -1 
					&& nomeElemento != nomeAlterado    // nao atualizar o objeto alterado
					&& nomeElemento != (nomeAlterado + '_Hide') // nem o oculto do alterado
					&& nomeElemento.indexOf('L') != -1 // alterar apenas as localidades
					&& nomeElemento.indexOf('_Hide') == -1 // nao atualizar os hidden
					&& textos[i].disabled == false){
					textos[i].style.color = "red";
					
					// novameta_localidade = antigametalocalidade * novameta da unidade (ou gerencia) 
					// / antiga meta da unidade (ou gerencia)
					
					var valorMetaOrig = eval('document.forms[0].' + nomeAlterado + '_Org.value');
					var novaMeta = parseFloat(textos[i].value) * parseFloat(valorObjetoAlterado)
						/ parseFloat(valorAnteriorObjetoAlterado);

					diferencaAcumulada += parseInt(novaMeta) - parseInt(textos[i].value);
										
					textos[i].value = parseInt(novaMeta);
		
					atualizarUnidade(textos[i]);
					
					ultimoIndiceAlterado = i;
										
				}
		
			}	
			
			if (diferenca != diferencaAcumulada){
			   
			   var ajuste = diferenca - diferencaAcumulada;
			   textos[ultimoIndiceAlterado].value = parseInt(textos[ultimoIndiceAlterado].value) + ajuste;
			   atualizarUnidade(textos[ultimoIndiceAlterado]);
			   
			   //var objRespectivo = obterObjetoRespectivo(textos[ultimoIndiceAlterado]);
			   //objRespectivo.value = textos[ultimoIndiceAlterado].value;
			}
			
		}
		
	}
*/	

	
	// cada caixa de texto, tem o seu hidden respectivo para guardar o valor anterior
	// este metodo vai retornar o hidden caso seja passado o normal, ou vice-versa.
	function obterObjetoRespectivo(obj){
		var nome = obj.name;
		var objHidden;
		var iHid = nome.indexOf('_Hide');
		if (iHid == -1){
			objHidden = eval('document.forms[0].' + nome + '_Hide');
		} else {
			objHidden = eval('document.forms[0].' + nome);
		}
		return objHidden;
	}
	
	function atualizarUnidade(obj){
	    var nomeUnidade = obj.name.substr(0, obj.name.indexOf('L'));
	    var objUnid = eval('document.forms[0].' + nomeUnidade);
	    var valorAntigoUnid = objUnid.value;
	    var objLocHid = eval('document.forms[0].' + obj.name + '_Hide');
	    var valorAntigo = objLocHid.value;
	    var diferenca = parseInt(obj.value) - parseInt(valorAntigo);
	    objUnid.value =  parseInt(objUnid.value) + diferenca;
	    var objUnidHid = eval('document.forms[0].' + objUnid.name + '_Hide');	    
	    objUnidHid.value = objUnid.value;
	    objLocHid.value = obj.value;
	    atualizarGerencia(objUnid, diferenca);
	}
	
	function atualizarGerencia(obj, diferenca){
	    var nomeGerencia = obj.name.substr(0, obj.name.indexOf('U'));
	    var objGer = eval('document.forms[0].' + nomeGerencia);
	    var valorAntigoGer = objGer.value;
	    objGer.value =  parseInt(objGer.value) + diferenca;
	    var objGerHid = eval('document.forms[0].' + objGer.name + '_Hide');	    
	    objGerHid.value = objGer.value;	    
		
		var metaTotal = document.getElementById("metaTotalCalculada");
		metaTotal.value = parseInt(metaTotal.value) + diferenca;
	}	
-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form
  action="/exibirAnalisarMetasCicloAction.do"
  method="post"
  name="AnalisarMetasCicloActionForm"
  type="gcom.gui.cobranca.AnalisarMetasCicloActionForm"
  onsubmit="return validateAnalisarMetasCicloActionForm(this);"
>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>
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

         <%@ include file="/jsp/util/mensagens.jsp"%>

        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
      </div></td>
    <td width="625" valign="top" class="centercoltext">
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>

          <td class="parabg">Analisar Metas do Ciclo </td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
	  <table width="100%" border="0">
        <tr>
          <td width="70%" colspan="2">
          	Para filtrar as metas de cobrança do ciclo, informe os dados abaixo:
          </td>
        </tr>
        
        <tr>
          <td width="20%"><strong>Ação de cobrança:<font color="#FF0000">*</font></strong> </td>
          <td width="80%"><html:select property="idCobrancaAcao">
          	  <html:option value="-1">&nbsp;</html:option>
              <html:options collection="colecaoCobrancaAcao" labelProperty="descricaoCobrancaAcao" property="id"/>
             </html:select>
          </td>
        </tr>
        <tr>
          <td width="20%"><strong>Mês/Ano:<font color="#FF0000">*</font></strong></td>
          <td width="80%">
          	<html:text property="anoMesCobranca" size="7" maxlength="7" onkeypress="javascript:mascaraAnoMes(this, event);somente_numero(this);"/>&nbsp;mm/aaaa
          </td>
        </tr>
        <tr>
          <td height="24">&nbsp;</td>
          <td colspan="2"> <strong><font color="#FF0000">*</font></strong> Campos obrigat&oacute;rios</td>
        </tr>
        <tr>
          <td align="left">&nbsp;</td>
          <td align="right">
          	<input type="button" name="btnGerar" onclick="gerarRelatorio();" class="bottonRightCol" value="Gerar" style="width: 70px;">
          </td>
        </tr>
        <logic:notEmpty name="AnalisarMetasCicloActionForm" property="idCicloMeta">
		<tr>
          <td width="20%"><strong>Meta Total:</strong></td>
          <td width="80%">
          	<html:text property="metaTotal" size="7"  maxlength="7" style="text-align:right" onkeypress="javascript:blockNumbers(event);"/>&nbsp;
          </td>
        </tr>
		<tr>
          <td width="20%"><strong>Valor Limite</strong></td>
          <td width="80%">
          	<html:text property="valorLimite" size="14"  maxlength="11" style="text-align:right" onkeyup="javascript:formataValorMonetario(this, 11);"/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>                        
		</logic:notEmpty>        
      </table>
      <p>&nbsp;</p>

<%
	int widthDescricaoItem = 370;
%>
  <logic:present name="helpers">

   	<table width="100%">
		<tr bgcolor="#79bbfd">
		  <td width="<%=widthDescricaoItem%>" >
		  	<strong>Gerência Regional<BR>&nbsp;&nbsp;&nbsp;&nbsp;> Unidade de Negócio<BR>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;> Localidade</strong>
		  </td>
		  <td width="80"align="right">
		  	<strong>Qtd Imóveis</strong>
		  </td>
		  <td width="60"align="right">
		  	<strong>Meta inicial</strong>
		  </td>
		  <td width="60"align="right">
		  	<strong>Meta ajustada</strong>
		  </td>	
		  <td width="60"align="right">
		  	<strong>Qtd realizada</strong>
		  </td>	  
		  <td width="70"align="right">
		  	<strong>Qtd Restante</strong>
		  </td>
		  <td width="100"align="right">
		  	<strong>Valor Restante</strong>
		  </td>
		</tr>
<!--  
		<tr bgcolor="#79bbfd">
		  <td colspan=2 align="right">
		  	<strong>Total:</strong>
		  </td>
		  <td>
		  	<input type="text" size=7 style="text-align:right" disabled="disabled" value="<%=request.getAttribute("metaTotalCalculada")%>">
		  </td>
		  <td>
		  	<input type="text" size=7 style="text-align:right" name="metaTotalCalculada" id="metaTotalCalculada" value="<%=request.getAttribute("metaTotalCalculada")%>">
		  </td>		  
		</tr>
-->
		<logic:iterate name="helpers" id="item">
				<tr>
				  <td colspan=7>
				  	<div id='layerHide<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/>' style="display:block">
		     			<table width="100%" border="1" bgcolor="#79bbfd">
		   					<tr bgcolor="#79bbfd">
		           				<td width="<%=widthDescricaoItem%>">             
		          					<a href="javascript:extendeTabela('<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/>',true);"/>
		          						<bean:write name="item" property="nomeItem"/>
		           					</a>		           					
		                   		</td>
		                   		<td width="100"align="justify">
		                   			<bean:write name="item" property="qtdImoveisSituacao" />
		                   		</td>
		                   		<td width="70"align="justify">
		                   			<bean:write name="item" property="metaOriginal" />
								</td> 					
								<td width="90"align="justify">
									<bean:write name="item" property="metaAtual"/>
		                   		</td>
		                   		<td width="100"align="justify">
		                   			<bean:write name="item" property="qtdTotalRealizada"/>
		                   		</td>
		                   		<td width="100"align="right">
		                   			<bean:write name="item" property="qtdTotalRestante"/>
		                   		</td>		            
		                   		<td width="200"align="right">
		                   			<bean:write name="item" property="valorTotalRestante"/>
		                   		</td>		                   				                   				                   		
		                  	</tr>
		                 </table>
		         		</div>
		         		<div id='layerShow<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/>' style="display:none">
					    <table width="100%" bgcolor="#79bbfd">						
							<tr bgcolor="#79bbfd">
		           				<td width="<%=widthDescricaoItem%>">             
		          					<a href="javascript:extendeTabela('<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/>',false);"/>
		          						<bean:write name="item" property="nomeItem"/>
		           					</a>		           					
		                   		</td>
		                   		<td>
		                   			<input type="text" size=7 style="text-align:right" disabled="disabled" value="<bean:write name="item" property="qtdImoveisSituacao" />">
		                   		</td>		                   		
		                   		<td >
		                   			<input type="text" size=7 style="text-align:right" name='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/>_Org' 
		                   				value="<bean:write name="item" property="metaOriginal" />" disabled="disabled">
								</td>
								<td>
		                   			<input type="text" size=7 style="text-align:right" name='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/>' 
		                   				id='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/>' 
		                   				value="<bean:write name="item" property="metaAtual"/>"  onchange="javascript:redistribuirAlteracao(this);">		                   				
		                   		</td>
						  	</tr>      
			  <logic:notEmpty name="item" property="subItens">				
				<tr>
					<td colspan="7">
					  		<bean:define name="item" property="subItens" id="helpersUnidades" />
					  		<logic:iterate name="helpersUnidades" id="itemUnegHash">
						  		<bean:define name="itemUnegHash" property="value" id="itemUneg" type="gcom.cobranca.InformarCicloMetaGrupoHelper"/>
                 			<table width="100%" bgcolor="#90c7fc" id='layerHide<bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/>' style="display:block">
								<tr bgcolor="#cbe5fe">
			           				<td width="<%=widthDescricaoItem%>">&nbsp;&nbsp;&nbsp;&nbsp;
			          					<a href="javascript:extendeTabela('<bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/>',true);"/>
			          						<bean:write name="itemUneg" property="nomeItem"/>
			           					</a>		           					
			                   		</td>
			                   		<td>
			                   			<input type="text" size=7 style="text-align:right" disabled="disabled" value="<bean:write name="itemUneg" property="qtdImoveisSituacao" />">
			                   		</td>		                   		
			                   		<td>
			                   			<input type="text" size=7 style="text-align:right" name='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/><bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/>_OrgH' 
			                   				value="<bean:write name="itemUneg" property="metaOriginal" />" disabled="disabled">
									</td>
									<td>
										<input type="text" size=7 style="text-align:right" name='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/><bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/>_Hide' 
										    id='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/><bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/>_Hide' 
			                   				value="<bean:write name="itemUneg" property="metaAtual"/>"  onchange="javascript:redistribuirAlteracao(this);">
			                   		</td>
			                  	</tr>
			                </table>
                 			<table width="100%" bgcolor="#90c7fc" id='layerShow<bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/>' style="display:none">
								<tr bgcolor="#cbe5fe">
			           				<td width="<%=widthDescricaoItem%>">&nbsp;&nbsp;&nbsp;&nbsp;
			          					<a href="javascript:extendeTabela('<bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/>',false);"/>
			          						<bean:write name="itemUneg" property="nomeItem"/>
			           					</a>		           					
			                   		</td>
			                   		<td>
			                   			<input type="text" size=7 style="text-align:right" disabled="disabled" value="<bean:write name="itemUneg" property="qtdImoveisSituacao" />">
			                   		</td>			                   		
			                   		<td >
			                   			<input type="text" size=7 style="text-align:right" name='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/><bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/>_Org' 
			                   				value="<bean:write name="itemUneg" property="metaOriginal" />" disabled="disabled">
									</td>
									<td>
										<input type="text" size=7 style="text-align:right" name='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/><bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/>' 
											id='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/><bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/>'
			                   				value="<bean:write name="itemUneg" property="metaAtual"/>"  onchange="javascript:redistribuirAlteracao(this);">
			                   		</td>
			                  	</tr>

							  <logic:notEmpty name="itemUneg" property="subItens">				
								<tr>
									<td colspan=4>
									    <table width="100%" bgcolor="#90c7fc">
									  		<bean:define name="itemUneg" property="subItens" id="helpersLocalidade" />
									  		<logic:iterate name="helpersLocalidade" id="itemLocHash">
										  		<bean:define name="itemLocHash" property="value" id="itemLoc" type="gcom.cobranca.InformarCicloMetaGrupoHelper"/>
												<tr bgcolor="#ffffff">
							           				<td width="<%=widthDescricaoItem%>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							          					<bean:write name="itemLoc" property="nomeItem"/>
							                   		</td>
							                   		<td>
							                   			<input type="text" size=7 style="text-align:right" disabled="disabled" value="<bean:write name="itemLoc" property="qtdImoveisSituacao" />">
							                   		</td>							                   		
							                   		<td>
							                   			<input type="text" size=7 style="text-align:right" name='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/><bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/><bean:write name="itemLoc" property="tipoItem"/><bean:write name="itemLoc" property="idItem"/>_Org' 
							                   				value="<bean:write name="itemLoc" property="metaOriginal" />" disabled="disabled">
													</td>
													<td>
														<input type="text" size=7 style="text-align:right" name='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/><bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/><bean:write name="itemLoc" property="tipoItem"/><bean:write name="itemLoc" property="idItem"/>' 
															id='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/><bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/><bean:write name="itemLoc" property="tipoItem"/><bean:write name="itemLoc" property="idItem"/>' 
		            					       				value="<bean:write name="itemLoc" property="metaAtual"/>"  onchange="javascript:atualizarUnidade(this);">
														<input type="hidden" size=7 style="text-align:right" name='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/><bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/><bean:write name="itemLoc" property="tipoItem"/><bean:write name="itemLoc" property="idItem"/>_Hide'
		            					       				value="<bean:write name="itemLoc" property="metaAtual"/>">
							                   		</td>
							                  	</tr>
						                  	</logic:iterate>							                  	
					                  	</table>
				                  	</td>
			                  	</tr>
			                  	</logic:notEmpty>
			                 </table>
		                  	</logic:iterate>
	                 </td>
                 </tr>
               </logic:notEmpty>
			</table>
			</div>
		</td>
		</tr>      
      </logic:iterate>
   <tr>
     <td align="right" colspan=3><gsan:controleAcessoBotao name="btVoltar" value="Voltar" onclick="javascript:voltarConsulta()" url="exibirAnalisarMetasCicloAction.do"/></td>
   </tr>                              
	</table>           
 </logic:present>
	</td>
  </tr>

  
</table>
<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioAnalisarMetasCicloAction.do" />
<%@ include file="/jsp/util/rodape.jsp"%>


</html:form>
</body>
</html:html>