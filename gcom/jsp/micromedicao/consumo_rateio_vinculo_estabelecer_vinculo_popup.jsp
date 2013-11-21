<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

	<head>
	
	<title></title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="EstabelecerVinculoPopupActionForm" />
	<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
	<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
	<script type="text/javascript">
		$(document).ready(function(){
			$('#icAreaComum').attr('checked', true);
			
			$('#list tr:odd').attr('bgcolor', '#FFFFFF');
			
			indicadorImovelAreaComum();
			$('[name=rateioTipoAgua]').change(function(){
				indicadorImovelAreaComum();
			});
			
			$('#estabelecerVinculo').click(function(){
				if ($('[name=indicadorImovelAreaComum]').is(':visible')
						&& $('[name=rateioTipoAgua] option:selected').val() == '5') {
					alert('Você deve adicionar o Imóvel de Área Comum.');
				} else {
					estabelecerVinculo();
				}
			});
			
		});
		
		function indicadorImovelAreaComum() {
			if ($('[name=rateioTipoAgua] option:selected').val() == '5') {
				$('#indicadorImovelAreaComum').show();
			} else {
				$('#indicadorImovelAreaComum').hide();
				$('#icAreaComum').attr('checked', true);
			}
		}
		
		<!-- Begin -->
		function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    	var form = document.forms[0];
		    if (tipoConsulta == 'imovel') {
	    	  	form.co11digoImovel.value = codigoRegistro;
	 	    	form.matriculaImovel.value = descricaoRegistro;
	            form.matriculaImovel.style.color = "#000000";
	 	    	  	      		//form.action = 'exibirManterDebitoACobrarAction.do'
				//form.submit();
		    }
		 }
	
	    function caracteresespeciais () {
	        this.aa = new Array("codigoImovel", "Matrícula do Imóvel deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     }
	     
	    function IntegerValidations () {
	     this.am = new Array("codigoImovel", "Matrícula do Imóvel deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	    }
	     
		function estabelecerVinculo(){
	        var form = document.forms[0];
	      	form.action = 'estabelecerVinculoPopupAction.do';
	        form.submit();		
		}
		
		function adicionarImovel(){
	        var form = document.forms[0];
			if(form.codigoImovel.value == ""){
				alert("Informa Matrícula do Imóvel");
				form.codigoImovel.focus();
		    } else {
		        if(validateCaracterEspecial(form) && validateInteger(form)){
			      	form.action = 'exibirEstabelecerVinculoPopupAction.do?acao=ADICIONAR';
			        form.submit();		
			    }
		    }
		}
		
		function limparPesquisaMatricula(){
		   var form = document.forms[0];
	  	   form.codigoImovel.value = "";
	  	   form.matriculaImovel.value = "";
		}
		
		function limparPesquisaMatriculaImovel(){
		   var form = document.forms[0];
	  	   form.matriculaImovel.value = "";
		}
		
	</script>
	</head>

<body leftmargin="0" topmargin="0">
<html:form action="/estabelecerVinculoPopupAction.do"
	name="EstabelecerVinculoPopupActionForm"
	type="gcom.gui.micromedicao.EstabelecerVinculoPopupActionForm"
	method="post">

	<table width="650" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="650" valign="top" class="centercoltext">
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
					<td class="parabg">Estabelecer V&iacute;nculo</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>Preencha os campos para estabelecer v&iacute;nculo
					para rateio de consumo:</td>
					<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=imovelManterVinculo-EstabelecerVinculo', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>								
				</tr>
				</table>
				<table width="100%" border="0">
				<logic:present name="EstabelecerVinculoPopupActionForm"
					property="rateioTipoAgua">

					<tr>
						<td width="35%" height="27"><strong>Tipo de Rateio da
						Liga&ccedil;&atilde;o de &Aacute;gua:<font color="#FF0000">*</font><strong></strong></strong></td>
						<td width="65%" colspan="3">
						<div align="left"><strong> <html:select
							name="EstabelecerVinculoPopupActionForm"
							property="rateioTipoAgua">
							<html:options name="request" collection="colecaoRateioTipo"
								labelProperty="descricao" property="id" />
						</html:select></strong></div>
						</td>
					</tr>
				</logic:present>
				<logic:present name="EstabelecerVinculoPopupActionForm"
					property="rateioTipoPoco">
					<tr>
						<td height="35"><strong>Tipo de Rateio do Po&ccedil;o:<strong></strong></strong></td>
						<td width="65%" colspan="3">
						<div align="left"><strong> <html:select
							name="EstabelecerVinculoPopupActionForm"
							property="rateioTipoPoco">
							<html:options name="request" collection="colecaoRateioTipo"
								labelProperty="descricao" property="id" />
						</html:select></strong></div>
						</td>
					</tr>
				</logic:present>
				<tr>
					<td height="24">
						<strong>Matr&iacute;cula do Im&oacute;vel:<strong>
						<font color="#FF0000">*</font>
					</td>
					<td colspan="2"><html:text property="codigoImovel" size="9"
						maxlength="9"  
						onkeypress="limparPesquisaMatriculaImovel();validaEnter(event, 'exibirEstabelecerVinculoPopupAction.do?pesquisaImovel=OK', 'codigoImovel');" 
						/> <a
						href="javascript:redirecionarSubmit('exibirPesquisarImovelAction.do?caminhoRetornoTelaPesquisaImovel=exibirEstabelecerVinculoPopupAction');">
						<img width="23" height="21"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
					<logic:present name="matriculaInexistente" scope="request">
						<html:text property="matriculaImovel" size="25" maxlength="25"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="matriculaInexistente"
						scope="request">
						<html:text property="matriculaImovel" size="25" maxlength="25"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent>
						<a
						href="javascript:limparPesquisaMatricula();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>
						<logic:equal name="EstabelecerVinculoPopupActionForm" property="possuiImovelAreaComum" value="false">
							<span id="indicadorImovelAreaComum" style="display:none">
								<br />
								Imóvel Área Comum?
								<html:radio property="indicadorImovelAreaComum" value="1"> Sim</html:radio>	
								<html:radio property="indicadorImovelAreaComum" value="2" styleId="icAreaComum"> Não</html:radio>	
							</span>
						</logic:equal>
					</td>

					<td width="12%">
					<div align="right"><input name="Button3" type="button"
						class="bottonRightCol" value="Adicionar"
						onClick="javascript:adicionarImovel();"></div>
					</td>
				</tr>
				<tr>
					<td height="24" colspan="4">
					<div style="width: 100%; height: 100; overflow: auto;">
					<table width="100%" bgcolor="#99CCFF" id="list">
						<tr bgcolor="#99CCFF">
							<td>
							<div align="center" class="style9"><strong>Remover</strong></div>
							</td>
							<td>
							<div align="center" class="style9"><strong>Matr&iacute;cula do
							Im&oacute;vel</strong></div>
							</td>
						</tr>
						<% String styleImovelAreaComum = ""; %>
						<logic:iterate name="colecaoImoveisASerVinculados" id="imovel">
							<% styleImovelAreaComum = ""; %>
							<logic:equal name="imovel" property="indicadorImovelAreaComum" value="1">
								<% styleImovelAreaComum = "color: #F00;"; %>
							</logic:equal>
							<tr bgcolor="#CBE5FE" style="<%=styleImovelAreaComum %>">
								<td width="10%">
								<div align="center"><img
									src="<bean:message key='caminho.imagens'/>Error.gif" width="14"
									height="14" style="cursor:hand;"
									onclick="redirecionarSubmit('exibirEstabelecerVinculoPopupAction.do?acao=REMOVER&posicao=<bean:write 
										name="imovel" property="id" />');">
								</div>
								</td>
								<td width="40%">
								<div align="center" class="style9">
									<bean:write name="imovel" property="id" />
								</div>
								</td>
							</tr>
						</logic:iterate>
					</table>
					</div>
					</td>
				</tr>
				<tr>
					<td height="27" colspan="4">
					<div align="right">
						<input name="Button" type="button" class="bottonRightCol" value="Estabelecer V&iacute;nculo" id="estabelecerVinculo" /> 
						<input name="Button2" type="button" class="bottonRightCol" value="Fechar" onClick="javascript:window.close();"></div>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</html:form>
<body>
</html:html>
