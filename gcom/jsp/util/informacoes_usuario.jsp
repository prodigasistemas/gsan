<div align="center">
  <p align="left">&nbsp;</p>
  <p align="left">&nbsp;</p>
  <p align="left">&nbsp;</p>
 
  
<table width="100%" border="0" class="tableinlayerultimosacessos">
  <script language="JavaScript">
  <!-- Begin
  function abrirUltimoAcesso(form){
    var ultimoAcesso = document.getElementById('ultimosacessos');
    if(ultimoAcesso.value != -1){
    
    flagInterrogacao = false;
    for (i = 0; i <= ultimoAcesso.value.length; i++){
    	if ( ultimoAcesso.value.substring(i, i+1) == '?' ){
    		flagInterrogacao = true;
    		break;
    	}
    }
   	if ( flagInterrogacao ) {
   		window.location.href = ultimoAcesso.value + "&menu=sim"
   	}
   	else {
   		window.location.href = ultimoAcesso.value + "?menu=sim";
   	}
    }
  }
//  End -->
  </script>  
  <tr>
    <td>
    </td>
  </tr> 
  <tr>
	   <logic:notPresent name="indicadorSenhaPendente" scope="session">
		    <td>
		      <bean:write name="ultimosAcessos" scope="session" filter="false"/>
		    </td>
	   </logic:notPresent>
  </tr>

</table>
  
 
  
<table width="100%" border="0" class="tableinlayerusuario">
  <tr>
    <td><strong><a href="${sessionScope.urlPortal}">Portal Gsan</a></strong><br></td>
  </tr>
  <tr>
    <td><strong><a href="/gsan/treinamentos.do">Treinamentos</a></strong><br></td>
  </tr>
  <tr>
    <td><strong><a href="/gsan/exibirInformarMelhoriasGcomAction.do">Entre em Contato</a></strong><br></td>
  </tr>
  <tr>
   	<logic:notPresent name="indicadorSenhaPendente" scope="session">
    	<td><strong><a href="/gsan/exibirEfetuarAlteracaoSenhaSimplificadaAction.do">Alterar Senha</a></strong><br></td>
   	</logic:notPresent>
  </tr>
  
  <tr>
    <td><strong>Data Atual:</strong><br>
      <bean:write name="dataAtual" scope="session"/>
    </td>
  </tr>
  <tr>
    <td><strong>Usu&aacute;rio:</strong><br>
      <bean:write name="usuarioLogado" scope="session" property="login"/>
    </td>
  </tr>
  <tr>
    <td><strong>Grupo:</strong><br>
      <logic:notEmpty name="colecaoGruposUsuario" scope="session">
	    <logic:iterate name="colecaoGruposUsuario" id="grupo" scope="session">
	  	<bean:write name="grupo" property="descricao"/><br>
	    </logic:iterate>
      </logic:notEmpty>
    </td>
  </tr>
  <tr>
    <td><strong>Nº Acesso:</strong><br>
      <bean:write name="usuarioLogado" scope="session" property="numeroAcessos"/>
    </td>
  </tr>
  <tr>
    <td><strong>Data Ult. Acesso:</strong><br>
      <bean:write name="dataUltimoAcesso" scope="session"/>
    </td>
  </tr>
  <logic:notEmpty name="mensagemGrupo" scope="session">	
  <tr>
      	<td><strong>Mensagem para usuários do Grupo:</strong><br>
		<font size="1">
	    <bean:write name="mensagemGrupo" scope="session"/>	
		</font>
        </td>
  </tr>	
  </logic:notEmpty>	
  <tr>
    <td>
       <logic:notEmpty name="mensagemExpiracao" scope="session">	
		    <bean:write name="mensagemExpiracao" scope="session"/>
       </logic:notEmpty>
	  
    </td>
  </tr>
    
  
  <tr>
    <td><strong><a href="/gsan/efetuarLogoffAction.do">Sair</a></strong><br></td>
  </tr>
</table>

</div>