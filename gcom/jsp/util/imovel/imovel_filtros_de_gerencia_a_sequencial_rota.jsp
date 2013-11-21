<!--  
Essa jsp funciona como um componente o qual implementa tudo que for necessário para a inclusão dos filtros: 
gerencia regional, unidade de negocio, localidade, setor comercial,rota e sequencial rota(os 4 ultimos com inicial e final).

Para que sua utilização aconteça com sucesso algumas regras devem ser observadas:

1 - O form deverá conter as seguintes propriedades:
idLocalidadeInicial, nomeLocalidadeInicial, idLocalidadeFinal, nomeLocalidadeFinal,
codigoSetorComercialInicial, nomeSetorComercialInicial, codigoSetorComercialFinal, 
nomeSetorComercialFinal, idGerenciaRegional, idUnidadeNegocio.

2 - Deverá ser colocado em algum escopo (sessao, request)os atributos: colecaoGerenciaRegional e colecaoUnidadeNegocio. 
Representando as coleções de gerencias e unidades de negocios respectivamente.

3 - A página deverá ser incluída na jsp que o utilizará da seguinte forma:
<%-- 
	<jsp:include page="/jsp/util/imovel/imovel_filtros_de_gerencia_a_sequencial_rota.jsp">
		<jsp:param name="action" value="exibirGerarRelatorioClientesEspeciaisAction.do"/>
		<jsp:param name="isExibirSequencialRota" value="false"/>
	</jsp:include>
 --%>
O parametro action será usado para quando o usuário teclar enter nos campos onde tem pesquisa(localidade e setor).

Os parametros isExibirSequencialRota e isExibirGerenciaRegional são opcionais. 
Caso passados como false os campos de sequencial rota e gerencia regional não serão exibidos e também não será
necessário que os mesmos existam no form.

4 - A jsp que for utilizar o componente deverá conter as seguintes funções javascript:
recuperarDadosQuatroParametros(par1,par2,par3,par4) e recuperarDadosPopup(par1,par2,par3).

5 - As funções javascript citadas acima, além das lógicas da própria tela, deverão fazer uma chamada
a uma função javascript contida no componente.
   - recuperarDadosQuatroParametros(par1,par2,par3,par4) deverá chamar
   		recuperarDadosQuatroParametrosSetor(par1,par2,par3,par4).
   - recuperarDadosPopup(par1,par2,par3) deverá chamar
		recuperarDadosPopupLocalizacao(par1,par2,par3).

6 - Existe uma função javascript para fazer a validação dos intervalos (validarIntervalosLocalizacao())
e uma outra para validar se os campos contém apenas o esperado(somento números ou somente letras, etc)
(validarConteudoCamposLocalizacao). Caso necessite dessas validações, basta chamá-las.

7 - como o layout é feito com tabela, problemas podem ocorrer quanto ao posicionamento dos campos,
assim tentar ajustar sem mexer diretamente no componente pois o mesmo é usado em outras telas.				
-->

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.util.ConstantesSistema"%>


<%@page import="gcom.util.ConstantesInterfaceGSAN"%><script>
function limparLocalidadeInicial() {
	var form = document.forms[0];
	form.idLocalidadeInicial.value = "";
	form.nomeLocalidadeInicial.value = "";	
}

function limparLocalidadeFinal() {
	var form = document.forms[0];
	form.idLocalidadeFinal.value = "";
	form.nomeLocalidadeFinal.value = "";	
}

function limparSetorComercialInicial() {
	var form = document.forms[0];
	form.idSetorComercialInicial.value = "";
	form.codigoSetorComercialInicial.value = "";
	form.nomeSetorComercialInicial.value = "";	
}

function limparSetorComercialFinal() {
	var form = document.forms[0];
	form.idSetorComercialFinal.value = "";
	form.codigoSetorComercialFinal.value = "";
	form.nomeSetorComercialFinal.value = "";	
}

function replicaDados(campoOrigem, campoDestino){
	campoDestino.value = campoOrigem.value;
}

function chamarPesquisaLocalidadeInicial() {
	document.forms[0].tipoPesquisa.value = 'inicial';
	abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade', 400, 800);
}

function chamarPesquisaLocalidadeFinal() {
	document.forms[0].tipoPesquisa.value = 'final';
	abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade', 400, 800);
}

function chamarPesquisaSetorComercialInicial() {
	if( trim(document.forms[0].idLocalidadeInicial.value) == ""){
		alert('<gsan:i18n key="<%=ConstantesInterfaceGSAN.ATENCAO_GSAN_INFORME_A_CAMPO%>"
				arg0="<%=ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE_INICIAL%>"/>');

		return;
	}

	abrirPopup('exibirPesquisarSetorComercialAction.do?tipo=setorComercialOrigem&idLocalidade=' + document.forms[0].idLocalidadeInicial.value, 275, 480);

}

function chamarPesquisaSetorComercialFinal() {
	if( trim(document.forms[0].idLocalidadeFinal.value) == ""){
		alert('<gsan:i18n key="<%=ConstantesInterfaceGSAN.ATENCAO_GSAN_INFORME_A_CAMPO%>"
				arg0="<%=ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE_FINAL%>"/>');
		return;
	}

	abrirPopup('exibirPesquisarSetorComercialAction.do?tipo=setorComercialDestino&idLocalidade=' + document.forms[0].idLocalidadeFinal.value, 275, 480);
}

function recuperarDadosQuatroParametrosSetor(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

	var form = document.forms[0];

	if (tipoConsulta == 'setorComercialOrigem') {
		form.nomeSetorComercialInicial.style.color = "#000000";
		form.nomeSetorComercialFinal.style.color = "#000000"; 
		
		form.idSetorComercialInicial.value = idRegistro;
		form.codigoSetorComercialInicial.value = codigoRegistro;
		form.nomeSetorComercialInicial.value = descricaoRegistro;		
		
		form.idSetorComercialFinal.value = idRegistro;
		form.codigoSetorComercialFinal.value = codigoRegistro;
		form.nomeSetorComercialFinal.value = descricaoRegistro;

	}

	if (tipoConsulta == 'setorComercialDestino') {
		form.nomeSetorComercialFinal.style.color = "#000000"; 

		form.idSetorComercialFinal.value = idRegistro;
		form.codigoSetorComercialFinal.value = codigoRegistro;
		form.nomeSetorComercialFinal.value = descricaoRegistro;		
	}

	bloquearCampos();
	desbloquearCampos();
}

function recuperarDadosPopupLocalizacao(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'localidade') {        
    	if (form.tipoPesquisa.value == 'inicial') {
    		form.nomeLocalidadeFinal.style.color = "#000000";
    		form.nomeLocalidadeInicial.style.color = "#000000";

    		form.idLocalidadeInicial.value = codigoRegistro;
    		form.nomeLocalidadeInicial.value = descricaoRegistro;

    		form.idLocalidadeFinal.value = codigoRegistro;
    		form.nomeLocalidadeFinal.value = descricaoRegistro;

    	} else {
    		form.nomeLocalidadeFinal.style.color = "#000000";

    		form.idLocalidadeFinal.value = codigoRegistro;
		    form.nomeLocalidadeFinal.value = descricaoRegistro;

	    }    
    }

    bloquearCampos();
    desbloquearCampos();
}

function bloquearCampos() {

    var form = document.forms[0];

    if( (isBrancoOuNulo(form.idLocalidadeInicial.value) || isBrancoOuNulo(form.idLocalidadeFinal.value) || form.idLocalidadeInicial.value != form.idLocalidadeFinal.value)
    	  	&& !form.codigoSetorComercialInicial.readOnly){ 

		form.idSetorComercialInicial.value = "";
		form.codigoSetorComercialInicial.value = "";
		form.nomeSetorComercialInicial.value = "";		
		
    	simularCampoTextoDisabled(form.codigoSetorComercialInicial);
    }

    if( (isBrancoOuNulo(form.idLocalidadeFinal.value) || form.idLocalidadeInicial.value != form.idLocalidadeFinal.value)
    	    && !form.codigoSetorComercialFinal.readOnly){

		form.idSetorComercialFinal.value = "";
		form.codigoSetorComercialFinal.value = "";
		form.nomeSetorComercialFinal.value = "";		
		
    	simularCampoTextoDisabled(form.codigoSetorComercialFinal);
    }
    
    if( (isBrancoOuNulo(form.codigoSetorComercialInicial.value) || isBrancoOuNulo(form.codigoSetorComercialFinal.value) || form.codigoSetorComercialInicial.value != form.codigoSetorComercialFinal.value)
    		&& !form.codigoRotaInicial.readOnly){

    	form.codigoRotaInicial.value = "";
    	simularCampoTextoDisabled(form.codigoRotaInicial);
    }

    if( isBrancoOuNulo(form.codigoSetorComercialFinal.value) 
    	    || form.codigoSetorComercialInicial.value != form.codigoSetorComercialFinal.value){

    	form.codigoRotaFinal.value = "";
    	simularCampoTextoDisabled(form.codigoRotaFinal);    	
    }    

	if(form.sequencialRotaInicial){
	    if( (isBrancoOuNulo(form.codigoRotaInicial.value) || isBrancoOuNulo(form.codigoRotaFinal.value) || form.codigoRotaInicial.value != form.codigoRotaFinal.value)
	    		&& !form.sequencialRotaInicial.readOnly){

	    	form.sequencialRotaInicial.value = "";
	    	simularCampoTextoDisabled(form.sequencialRotaInicial);
	    }

	    if( isBrancoOuNulo(form.codigoRotaFinal.value) 
	    	    || form.codigoRotaInicial.value != form.codigoRotaFinal.value){

	    	form.sequencialRotaFinal.value = "";
	    	simularCampoTextoDisabled(form.sequencialRotaFinal);    	
	    }
	}
}

function desbloquearCampos() {

	var form = document.forms[0];

	if( (trim(form.idLocalidadeInicial.value) == trim(form.idLocalidadeFinal.value))
			 && !isBrancoOuNulo(form.idLocalidadeInicial.value)){

	    if(form.codigoSetorComercialInicial.readOnly){    		
	    	reverterSimularCampoTextoDisabled(form.codigoSetorComercialInicial);
	    }

	    if(form.codigoSetorComercialFinal.readOnly){		    
	    	reverterSimularCampoTextoDisabled(form.codigoSetorComercialFinal);
	    }
	    
	}

	if((trim(form.codigoSetorComercialInicial.value) == trim(form.codigoSetorComercialFinal.value))
			&& !isBrancoOuNulo(form.codigoSetorComercialInicial.value) ){

		if( form.codigoRotaInicial.readOnly){
	    	reverterSimularCampoTextoDisabled(form.codigoRotaInicial);
	    }

	    if( form.codigoRotaFinal.readOnly){
	    	reverterSimularCampoTextoDisabled(form.codigoRotaFinal);    	
	    }
		    
	}

	if(form.sequencialRotaInicial){
		if((trim(form.codigoRotaInicial.value) == trim(form.codigoRotaFinal.value))
				&& !isBrancoOuNulo(form.codigoRotaInicial.value) ){

			if( form.sequencialRotaInicial.readOnly){
		    	reverterSimularCampoTextoDisabled(form.sequencialRotaInicial);
		    }

		    if( form.sequencialRotaFinal.readOnly){
		    	reverterSimularCampoTextoDisabled(form.sequencialRotaFinal);    	
		    }
			    
		}
	}
}

function validarIntervalosLocalizacao() {

	var form = document.forms[0];
	
	if (isBrancoOuNulo(form.idLocalidadeInicial.value) && !isBrancoOuNulo(form.idLocalidadeFinal.value) ) {
		alert('<gsan:i18n key="<%=ConstantesInterfaceGSAN.ATENCAO_GSAN_INFORME_A_CAMPO%>"
				arg0="<%=ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE_INICIAL%>"/>');

			return false;
	}
	if (!isBrancoOuNulo(form.idLocalidadeInicial.value) && isBrancoOuNulo(form.idLocalidadeFinal.value)) {
		alert('<gsan:i18n key="<%=ConstantesInterfaceGSAN.ATENCAO_GSAN_INFORME_A_CAMPO%>"
				arg0="<%=ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE_FINAL%>"/>');
			return false;
	}
	if(parseInt(form.idLocalidadeFinal.value) < parseInt(form.idLocalidadeInicial.value)){
		alert('<gsan:i18n key="<%=ConstantesInterfaceGSAN.ATENCAO_GSAN_CAMPO_FINAL_MENOR_CAMPO_INICIAL%>"
				arg0="<%=ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE%>"/>');

		return false;
	}


	if (isBrancoOuNulo(form.codigoSetorComercialInicial.value) && !isBrancoOuNulo(form.codigoSetorComercialFinal.value) ) {
		alert('<gsan:i18n key="<%=ConstantesInterfaceGSAN.ATENCAO_GSAN_INFORME_O_CAMPO%>"
				arg0="<%=ConstantesInterfaceGSAN.LABEL_GSAN_SETOR_COMERCIAL_INICIAL%>"/>');

		return false;
	}
	if (!isBrancoOuNulo(form.codigoSetorComercialInicial.value) && isBrancoOuNulo(form.codigoSetorComercialFinal.value)) {
		alert('<gsan:i18n key="<%=ConstantesInterfaceGSAN.ATENCAO_GSAN_INFORME_O_CAMPO%>"
				arg0="<%=ConstantesInterfaceGSAN.LABEL_GSAN_SETOR_COMERCIAL_FINAL%>"/>');

			return false;
	}
	if(parseInt(form.codigoSetorComercialFinal.value) < parseInt(form.codigoSetorComercialInicial.value)){
		alert('<gsan:i18n key="<%=ConstantesInterfaceGSAN.ATENCAO_GSAN_CAMPO_FINAL_MENOR_CAMPO_INICIAL%>"
				arg0="<%=ConstantesInterfaceGSAN.LABEL_GSAN_SETOR_COMERCIAL%>"/>');
				
		return false;
	}
	
	if (isBrancoOuNulo(form.codigoRotaInicial.value) && !isBrancoOuNulo(form.codigoRotaFinal.value) ) {
		alert('<gsan:i18n key="<%=ConstantesInterfaceGSAN.ATENCAO_GSAN_INFORME_A_CAMPO%>"
				arg0="<%=ConstantesInterfaceGSAN.LABEL_GSAN_ROTA_INICIAL%>"/>');
			return false;
	}
	if (!isBrancoOuNulo(form.codigoRotaInicial.value) && isBrancoOuNulo(form.codigoRotaFinal.value)) {
		alert('<gsan:i18n key="<%=ConstantesInterfaceGSAN.ATENCAO_GSAN_INFORME_A_CAMPO%>"
				arg0="<%=ConstantesInterfaceGSAN.LABEL_GSAN_ROTA_FINAL%>"/>');
			return false;
	}

	if(parseInt(form.codigoRotaFinal.value) < parseInt(form.codigoRotaInicial.value)){
		alert('<gsan:i18n key="<%=ConstantesInterfaceGSAN.ATENCAO_GSAN_CAMPO_FINAL_MENOR_CAMPO_INICIAL%>"
				arg0="<%=ConstantesInterfaceGSAN.LABEL_GSAN_ROTA%>"/>');
		return false;
	}

	if(form.sequencialRotaInicial){
		if (isBrancoOuNulo(form.sequencialRotaInicial.value) && !isBrancoOuNulo(form.sequencialRotaFinal.value) ) {
			alert('<gsan:i18n key="<%=ConstantesInterfaceGSAN.ATENCAO_GSAN_INFORME_O_CAMPO%>"
					arg0="<%=ConstantesInterfaceGSAN.LABEL_GSAN_SEQUENCIAL_ROTA_INICIAL%>"/>');
			return false;
		}
		if (!isBrancoOuNulo(form.sequencialRotaInicial.value) && isBrancoOuNulo(form.sequencialRotaFinal.value)) {
			alert('<gsan:i18n key="<%=ConstantesInterfaceGSAN.ATENCAO_GSAN_INFORME_O_CAMPO%>"
					arg0="<%=ConstantesInterfaceGSAN.LABEL_GSAN_SEQUENCIAL_ROTA_FINAL%>"/>');
				return false;
		}
		
		if(parseInt(form.sequencialRotaFinal.value) < parseInt(form.sequencialRotaInicial.value)){
			alert('<gsan:i18n key="<%=ConstantesInterfaceGSAN.ATENCAO_GSAN_CAMPO_FINAL_MENOR_CAMPO_INICIAL%>"
					arg0="<%=ConstantesInterfaceGSAN.LABEL_GSAN_SEQUENCIAL_ROTA%>"/>');
			return false;
		}
	}

	return true;
}

function validarConteudoCamposLocalizacao() {

	var form = document.forms[0];

	var resultado = validarCampoNumericoComMensagem(form.idLocalidadeInicial,
						'<gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE_INICIAL%>"/>');

	if(!resultado){
		return false;
	}

	resultado = validarCampoNumericoComMensagem(form.idLocalidadeFinal,
						'<gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE_FINAL%>"/>');
	

	if(!resultado){
		return false;
	}
	
	resultado = validarCampoNumericoComMensagem(form.codigoSetorComercialInicial,
						'<gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_SETOR_COMERCIAL_INICIAL%>"/>');

	if(!resultado){
		return false;
	}
	
	resultado = validarCampoNumericoComMensagem(form.codigoSetorComercialFinal,
						'<gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_SETOR_COMERCIAL_FINAL%>"/>');

	if(!resultado){
		return false;
	}
	
	resultado = validarCampoNumericoComMensagem(form.codigoRotaInicial,
						'<gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_ROTA_INICIAL%>"/>');

	if(!resultado){
		return false;
	}
	
	resultado = validarCampoNumericoComMensagem(form.codigoRotaFinal,
						'<gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_ROTA_FINAL%>"/>');

	if(!resultado){
		return false;
	}

	if(form.sequencialRotaInicial){
		resultado = validarCampoNumericoComMensagem(form.sequencialRotaInicial,
				'<gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_SEQUENCIAL_ROTA_INICIAL%>"/>');
		
		
		if(!resultado){
			return false;
		}
		
		resultado = validarCampoNumericoComMensagem(form.sequencialRotaFinal,
				'<gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_SEQUENCIAL_ROTA_FINAL%>"/>');

	}

	return resultado;
}

/*necessário esse javascript visto que não se pode usar script's jsp/chamar outra tag
 * em algumas tags do struts, como por exemplo, html:text.
 */
function validaEnterMensagemInternacionalizada(evento,campo){

	if(campo == "idLocalidadeInicial"){
		validaEnterComMensagem(evento, '${param.action}', 'idLocalidadeInicial', 
				'<gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE_INICIAL%>"/>');

		return;
	}

	if(campo == "idLocalidadeFinal"){
		validaEnterComMensagem(evento, '${param.action}', 'idLocalidadeFinal', 
				'<gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE_FINAL%>"/>');

		return;
	}

	if(campo == "codigoSetorComercialInicial"){
		validaEnterComMensagem(evento, '${param.action}', 'codigoSetorComercialInicial', 
				'<gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_SETOR_COMERCIAL_INICIAL%>"/>');

		return;
	}

	if(campo == "codigoSetorComercialFinal"){
		validaEnterComMensagem(evento, '${param.action}', 'codigoSetorComercialFinal', 
				'<gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_SETOR_COMERCIAL_FINAL%>"/>');

		return;
	}	
}

</script>

<input type="hidden" name="tipoPesquisa" />
<input type="hidden" name="idSetorComercialInicial" />
<input type="hidden" name="idSetorComercialFinal" />

<logic:notEqual parameter="isExibirGerenciaRegional" value="false">
<tr>

	<td><strong><gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_GERENCIA_REGIONAL%>"/>:</strong></td>
	<td>
		<html:select property="idGerenciaRegional" >
			<logic:notEmpty name="colecaoGerenciaRegional">
				<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
				<html:options collection="colecaoGerenciaRegional" labelProperty="nome" property="id" />
			</logic:notEmpty>
		</html:select>
	</td>
</tr>
</logic:notEqual>

<tr>
	<td><strong><gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_UNIDADE_NEGOCIO%>"/>:</strong></td>
	<td>
		<html:select property="idUnidadeNegocio" >
			<logic:notEmpty name="colecaoUnidadeNegocio">
				<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
				<html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id" />
			</logic:notEmpty>
		</html:select>
	</td>
</tr>

<tr height="10"></tr>

<tr>
	<td><strong><gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE_INICIAL%>"/>:</strong></td>
	<td>
		<html:text property="idLocalidadeInicial"
			size="7" maxlength="3"
			onblur="replicaDados(document.forms[0].idLocalidadeInicial, document.forms[0].idLocalidadeFinal);bloquearCampos();desbloquearCampos();" 
			onkeyup="replicaDados(document.forms[0].idLocalidadeInicial, document.forms[0].idLocalidadeFinal);bloquearCampos();desbloquearCampos();"
			onkeypress="validaEnterMensagemInternacionalizada(event, 'idLocalidadeInicial');return isCampoNumerico(event);" />

		<a href="javascript:chamarPesquisaLocalidadeInicial();">
			<img width="23" height="21" border="0" 
				src='<gsan:i18n key="caminho.imagens"/>pesquisa.gif' title='<gsan:i18n key="<%=ConstantesInterfaceGSAN.HINT_GSAN_PESQUISAR%>"/>' />
		</a> 
			
		<logic:present name="localidadeInicialInexistente" scope="request">
			<html:text property="nomeLocalidadeInicial" readonly="true"
				style="background-color:#EFEFEF; border:0; color: #ff0000"
				size="38" maxlength="40" />
		</logic:present> 
		
		<logic:notPresent name="localidadeInicialInexistente" scope="request">
			<html:text property="nomeLocalidadeInicial" readonly="true"
				style="background-color:#EFEFEF; border:0" size="38"
				maxlength="40" />
		</logic:notPresent>

		<a href="javascript:limparLocalidadeInicial();limparLocalidadeFinal();bloquearCampos();">
			<img src="<gsan:i18n key="caminho.imagens"/>limparcampo.gif"
				border="0" title="<gsan:i18n key="<%=ConstantesInterfaceGSAN.HINT_GSAN_APAGAR%>"/>" /> 
		</a> 
	</td>
</tr>

<tr>
	<td><strong><gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_SETOR_COMERCIAL_INICIAL%>"/>:</strong></td>
	<td>
		<html:text maxlength="3" property="codigoSetorComercialInicial" size="7"
			onblur="replicaDados(document.forms[0].codigoSetorComercialInicial, document.forms[0].codigoSetorComercialFinal);bloquearCampos();desbloquearCampos();"
			onkeyup="replicaDados(document.forms[0].codigoSetorComercialInicial, document.forms[0].codigoSetorComercialFinal);bloquearCampos();desbloquearCampos();"	
			onkeypress="validaEnterMensagemInternacionalizada(event,'codigoSetorComercialInicial');return isCampoNumerico(event);"/>
			
		<a href="javascript:chamarPesquisaSetorComercialInicial();">
			<img width="23" height="21" border="0" style="cursor:hand;"
				src="<gsan:i18n key="caminho.imagens"/>pesquisa.gif"
				title='<gsan:i18n key="<%=ConstantesInterfaceGSAN.HINT_GSAN_PESQUISAR%>"/>'/>
		</a>

		<logic:present name="setorComercialInicialInexistente" scope="request">
			<html:text property="nomeSetorComercialInicial" size="30" maxlength="30" 
				readonly="true" style="background-color:#EFEFEF; border:0; color: red" />
		</logic:present> 

		<logic:notPresent name="setorComercialInicialInexistente" scope="request">
			<html:text property="nomeSetorComercialInicial" size="30"
				maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
		</logic:notPresent>
		
		<a href="javascript:limparSetorComercialInicial();limparSetorComercialFinal();bloquearCampos();"> 
			<img src="<gsan:i18n key="caminho.imagens"/>limparcampo.gif"
				border="0" title='<gsan:i18n key="<%=ConstantesInterfaceGSAN.HINT_GSAN_APAGAR%>"/>' />
		</a>
	</td>
</tr>

<tr>
	<td><strong><gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_ROTA_INICIAL%>"/>:</strong></td>
	<td>
		<html:text maxlength="4"  property="codigoRotaInicial" size="7"
			onblur="replicaDados(document.forms[0].codigoRotaInicial, document.forms[0].codigoRotaFinal);bloquearCampos();desbloquearCampos();"
			onkeyup="replicaDados(document.forms[0].codigoRotaInicial, document.forms[0].codigoRotaFinal);bloquearCampos();desbloquearCampos();" 
			onkeypress="return isCampoNumerico(event);"/>
	</td>
</tr>

<logic:notEqual parameter="isExibirSequencialRota" value="false">
<tr>
	<td><strong><gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_SEQUENCIAL_ROTA_INICIAL%>"/>:</strong></td>
	<td>
		<html:text maxlength="5" property="sequencialRotaInicial" size="7" 
			onblur="replicaDados(document.forms[0].sequencialRotaInicial, document.forms[0].sequencialRotaFinal);"
			onkeyup="replicaDados(document.forms[0].sequencialRotaInicial, document.forms[0].sequencialRotaFinal);" 
			onkeypress="return isCampoNumerico(event);"/>
	</td>
</tr>
</logic:notEqual>

<tr height="10"></tr>

<tr>
	<td><strong><gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE_FINAL%>"/>:</strong></td>
	<td>
		<html:text property="idLocalidadeFinal" size="7" maxlength="3" 
			onkeyup="bloquearCampos();desbloquearCampos();"
			onkeypress="validaEnterMensagemInternacionalizada(event,'idLocalidadeFinal');return isCampoNumerico(event);" />

		<a href="javascript:chamarPesquisaLocalidadeFinal();"> 
			<img border="0" src="imagens/pesquisa.gif" height="21" width="23" title='<gsan:i18n key="<%=ConstantesInterfaceGSAN.HINT_GSAN_PESQUISAR%>"/>'>
		</a>
	
		<logic:present name="localidadeFinalInexistente" scope="request">
			<html:text property="nomeLocalidadeFinal" readonly="true"
				style="background-color:#EFEFEF; border:0; color: #ff0000"
				size="38" maxlength="40" />
		</logic:present> 
		
		<logic:notPresent name="localidadeFinalInexistente" scope="request">
			<html:text property="nomeLocalidadeFinal" readonly="true"
				style="background-color:#EFEFEF; border:0" size="38"
				maxlength="40" />
		</logic:notPresent>

		<a href="javascript:limparLocalidadeFinal();bloquearCampos();">
			<img src="<gsan:i18n key="caminho.imagens"/>limparcampo.gif"
				border="0" title="<gsan:i18n key="<%=ConstantesInterfaceGSAN.HINT_GSAN_APAGAR%>"/>" />
		</a>
	</td>
</tr>

<tr>
	<td><strong><gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_SETOR_COMERCIAL_FINAL%>"/>:</strong></td>
	<td>
		<html:text maxlength="3" property="codigoSetorComercialFinal" size="7"
			onkeyup="bloquearCampos();desbloquearCampos();"
			onkeypress="validaEnterMensagemInternacionalizada(event, 'codigoSetorComercialFinal');return isCampoNumerico(event);"/>

		<a href="javascript:chamarPesquisaSetorComercialFinal();">
			<img width="23" height="21" border="0" 
				src="<gsan:i18n key="caminho.imagens"/>pesquisa.gif"
					title="<gsan:i18n key="<%=ConstantesInterfaceGSAN.HINT_GSAN_PESQUISAR%>"/>" />
		</a>

		<logic:present name="setorComercialFinalInexistente" scope="request">
			<html:text property="nomeSetorComercialFinal" size="30" maxlength="30" readonly="true"
				style="background-color:#EFEFEF; border:0; color: red" />
		</logic:present> 

		<logic:notPresent name="setorComercialFinalInexistente" scope="request">
			<html:text property="nomeSetorComercialFinal" size="30" maxlength="30" readonly="true"
				style="background-color:#EFEFEF; border:0; color: #000000" />
		</logic:notPresent>
		
		<a href="javascript:limparSetorComercialFinal();bloquearCampos();"> 
			<img src="<gsan:i18n key="caminho.imagens"/>limparcampo.gif"
				border="0" title="<gsan:i18n key="<%=ConstantesInterfaceGSAN.HINT_GSAN_APAGAR%>"/>" />
		</a>
	</td>
</tr>

<tr>
	<td><strong><gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_ROTA_FINAL%>"/>:</strong></td>
	<td>
		<html:text maxlength="4" property="codigoRotaFinal" size="7" 
			onkeyup="bloquearCampos();desbloquearCampos();"
			onkeypress="return isCampoNumerico(event);"/>
	</td>
</tr>

<logic:notEqual parameter="isExibirSequencialRota" value="false">
<tr>
	<td><strong><gsan:i18n key="<%=ConstantesInterfaceGSAN.LABEL_GSAN_SEQUENCIAL_ROTA_FINAL%>"/>:</strong></td>
	<td>
		<html:text maxlength="5" property="sequencialRotaFinal" size="7" onkeypress="return isCampoNumerico(event);"/>
	</td>
</tr>
</logic:notEqual>

<script>
bloquearCampos();
</script>