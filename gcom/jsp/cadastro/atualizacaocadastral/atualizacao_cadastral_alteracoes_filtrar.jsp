<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="FiltrarAlteracaoAtualizacaoCadastralActionForm" />

<script LANGUAGE="JavaScript">
 	
	//So chama a função abrirCalendario caso o campo esteja habilitado
	function chamarCalendario(fieldNameOrigem,objetoRelacionado,fieldNameDestino){
		abrirCalendarioReplicando('FiltrarAlteracaoAtualizacaoCadastralActionForm', fieldNameOrigem,fieldNameDestino);

	}
	
	function validarForm(form){
	    if(form.idEmpresa.value == '-1'){
	    	alert('Informe a Empresa');
	    }else{
	    	form.action = "/gsan/filtrarAlteracaoAtualizacaoCadastralAction.do";
			submeterFormPadrao(form);	
	    }
	}
	
	function listarLeiturista(){
		 var form = document.forms[0];
		 form.action = 'exibirFiltrarAlteracaoAtualizacaoCadastralAction.do';
	  	 form.submit();
	
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	   var form = document.forms[0];
	   if (tipoConsulta == 'arquivoTextoAtualizacaoCadastral') {
	      form.idArquivo.value = codigoRegistro;
	      form.descricaoArquivo.value = descricaoRegistro;
	    }
	    
   }
   
   	function chamarPopup(url,altura, largura,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			abrirPopup(url, altura, largura);
		}
   	}
   	
   function limparLeiturista(){
		var form = document.forms[0];
	    form.idArquivo.value = '';
	    form.descricaoArquivo.value = '';
	}
	
	
	
</script>


</head>


<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">

<div id="formDiv">
<html:form action="/filtrarAlteracaoAtualizacaoCadastralAction"
	name="FiltrarAlteracaoAtualizacaoCadastralActionForm"
	type="gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionForm"
	onsubmit="return validateFiltrarAlteracaoAtualizacaoCadastralActionForm(this);">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<input type="hidden" id="existeColecaoImovel" value="${requestScope.existeColecaoImovel}"/>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="140" valign="top" class="leftcoltext">
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

	<td width="625" valign="top" class="centercoltext">

        <table height="100%">
        <tr>
          <td></td>
        </tr>
      	</table>

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Filtrar Atualizações Cadastrais</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      <tr>
      	<td colspan="3">Para filtrar a(s) atualização(ões) cadastral(is), informe os dados abaixo:</td>
      </tr>
	  <tr>
	  <tr>
		<td width="150"><strong>Empresa:<font color="#FF0000">*</font></strong></td>
		<td colspan="2" align="left">
			<html:select property="idEmpresa" tabindex="3" onchange="javascript:listarLeiturista()">
				<html:option value="-1">&nbsp;</html:option>
				<html:options collection="colecaoEmpresa"
					labelProperty="descricao" property="id" />
			</html:select>
		</td>
	  </tr>
	  <tr>
		<td height="10" width="145"><strong>Agente Comercial:</strong></td>
		<td colspan="2" align="left"><html:select property="idLeiturista"
			tabindex="4">
			<html:option value="-1">&nbsp;</html:option>
			<html:options collection="colecaoLeiturista"
				labelProperty="descricao" property="id" />
		</html:select></td>
	 </tr>
     <tr>
		<td width="130"><strong>Identificador do Arquivo:</strong></td>
		<td colspan="2"><html:text maxlength="9" property="idArquivo"
			tabindex="1" size="9"
			onkeypress="validaEnterComMensagem(event, 'exibirFiltrarAlteracaoAtualizacaoCadastralAction.do', 'idArquivo', 'Arquivo');" />
		<a href="javascript:chamarPopup('exibirPesquisarArquivoTextoAtualizacaoCadastralAction.do?limpaForm=S', 495, 300,document.forms[0].idArquivo);">
		<img width="23" height="21" border="0"
			src="<bean:message key="caminho.imagens"/>pesquisa.gif"
			title="Pesquisar Agente Cadastral" /></a> 
		<logic:present
			name="idArquivoEncontrado" scope="request">
			<html:text maxlength="50" property="descricaoArquivo"
				readonly="true"
				style="background-color:#EFEFEF; border:0; color: #000000"
				size="45" />
		</logic:present> 
		<logic:notPresent
			name="idArquivoEncontrado" scope="request">
			<html:text maxlength="50" property="descricaoArquivo"
				readonly="true"
				style="background-color:#EFEFEF; border:0; color: #ff0000"
				size="45" />
		</logic:notPresent> 
		<a href="javascript:limparLeiturista();"> 
		<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
			border="0" title="Apagar" /></a></td>
	</tr>  
		
		
		<tr>
		<td><strong>Exibir Campos:</strong></td>
					<td><strong> <html:radio property="exibirCampos"
						value="1" /> <strong>Pendentes de Alteração <html:radio
						property="exibirCampos" value="2" /> Alterados/Resolvidos</strong><html:radio
						property="exibirCampos" value="3" /> Todos</strong>
						</td>
		</tr>
		
		<tr>
					<td width="120"><strong>Colunas dos imóveis e clientes:</strong></td>
					<td colspan="2">
					<table width="100%" border=0 cellpadding=0 cellspacing=0
						align="left">
						<tr>
							<td width="175">

							<div align="left"><strong>Disponíveis</strong></div>

							<html:select property="colunaImoveis" size="6" multiple="true"
								style="width:190px" >

								<html:options collection="colecaoColunaImoveis" 
									labelProperty="descricaoColuna" property="id" />
							</html:select></td>

							<td width="5" valign="center"><br>
							<table width="50" align="center">
								<tr>
									<td align="center"><input type="button" class="bottonRightCol"
										onclick="javascript:MoverTodosDadosSelectMenu1PARAMenu2('FiltrarAlteracaoAtualizacaoCadastralActionForm', 'colunaImoveis', 'colunaImoveisSelecionados'); enviarSelectMultiplo('FiltrarAlteracaoAtualizacaoCadastralActionForm','colunaImoveisSelecionados');"
										value=" &gt;&gt; "></td>
								</tr>

								<tr>
									<td align="center"><input type="button" class="brottonRightCol"
										onclick="javascript:MoverDadosSelectMenu1PARAMenu2('FiltrarAlteracaoAtualizacaoCadastralActionForm', 'colunaImoveis', 'colunaImoveisSelecionados'); enviarSelectMultiplo('FiltrarAlteracaoAtualizacaoCadastralActionForm','colunaImoveisSelecionados');"
										value=" &nbsp;&gt;  "></td>
								</tr>

								<tr>
									<td align="center"><input type="button" class="bottonRightCol"
										onclick="javascript:MoverDadosSelectMenu2PARAMenu1('FiltrarAlteracaoAtualizacaoCadastralActionForm', 'colunaImoveis', 'colunaImoveisSelecionados'); enviarSelectMultiplo('FiltrarAlteracaoAtualizacaoCadastralActionForm','colunaImoveisSelecionados');"
										value=" &nbsp;&lt;  "></td>
								</tr>

								<tr>
									<td align="center"><input type="button" class="bottonRightCol"
										onclick="javascript:MoverTodosDadosSelectMenu2PARAMenu1('FiltrarAlteracaoAtualizacaoCadastralActionForm', 'colunaImoveis', 'colunaImoveisSelecionados'); enviarSelectMultiplo('FiltrarAlteracaoAtualizacaoCadastralActionForm','colunaImoveisSelecionados');"
										value=" &lt;&lt; "></td>
								</tr>
							</table>
							</td>

							<td>
							<div align="left"><strong>Selecionados</strong></div>

							<html:select property="colunaImoveisSelecionados" size="6"
								multiple="true" style="width:190px">
								
								<logic:present name="existeColecaoColunaImoveisSelecionados">
									<html:options collection="colecaoColunaImoveisSelecionados"
									labelProperty="descricaoColuna" property="id" />
								</logic:present>
								

							</html:select></td>
						</tr>
					</table>
					</td>
				</tr>
		
		
     <!--<tr>
         <td>
         	<strong>Período de Realização:</strong>
         </td>        
        <td colspan="2">
         	<span class="style2">
         	<strong> 
				<html:text property="periodoRealizacaoInicial" 
					size="11" 
					maxlength="10" 
					tabindex="3" 
					onkeyup="mascaraData(this, event);replicaDataEncerramento();"/>
		
				<a href="javascript:chamarCalendario('periodoRealizacaoInicial',document.forms[0].periodoRealizacaoInicial,'periodoRealizacaoFinal');">
				<img border="0" 
					src="<bean:message key='caminho.imagens'/>calendario.gif" 
					width="16" 
					height="15" 
					border="0" 
					alt="Exibir Calendário" 
					tabindex="4"/></a>
					a 
		
				<html:text property="periodoRealizacaoFinal" 
					size="11" 
					maxlength="10" 
					tabindex="3" 
					onkeyup="mascaraData(this, event)"/>
		
				<a href="javascript:chamarCalendario('periodoRealizacaoFinal',document.forms[0].periodoRealizacaoFinal,'');">
					<img border="0" 
						src="<bean:message key='caminho.imagens'/>calendario.gif" 
						width="16" 
						height="15" 
						border="0" 
						alt="Exibir Calendário" 
						tabindex="4"/></a>
		
		        </strong>(dd/mm/aaaa)<strong> 
		    </strong>
           	</span>
       	</td>
      </tr>-->
      
      
  	  <tr>
		<td colspan="3">&nbsp;</td>
	 </tr>
	 <tr>
	   <td colspan="3" align="right">
	     <table border="0" width="100%">
		   <tr>
		   <td>
				<input name="Submit22"
					class="bottonRightCol" 
					value="Limpar" 
					type="button"
					onclick="window.location.href='/gsan/exibirFiltrarAlteracaoAtualizacaoCadastralAction.do?menu=sim';"> 			
			</td>
					
			<td align="right" width="50%">					
			 <input name="Button322222" type="button"
				class="bottonRightCol" value="Filtrar"
				onClick="javascript:validarForm(document.forms[0]);" />
			</td>
		  </tr>
		</table>
	   </td>
	 </tr>
	 
      </table>
      <p>&nbsp;</p>
	</td>
  </tr>
</table>



<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>

</body>
</html:html>
