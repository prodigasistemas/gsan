<table width="100%" border="0" bgcolor="#99ccff">
	<tr heigth="20">
		<td align="center"><strong>Dados da Geração da Consulta</strong></td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="0" bgcolor="#cbe5fe">
			<tr>
				<td>
				  <strong>Negativador:</strong>
				</td>
				<td>
				   <html:text property="nomeNegativador" size="45" readonly="true" disabled="true" 
				              style="background-color:#EFEFEF; border:0;"/>
			    </td>	
			</tr>	
			<tr>
				<td><strong>Período do envio da Negativação:</strong></td>
				<td>
				<html:text property="periodoEnvioNegativacaoInicio" size="8" readonly="true" disabled="true"
				           style="background-color:#EFEFEF; border:0; " />
				<strong>a</strong>
				<html:text property="periodoEnvioNegativacaoFim" size="8" readonly="true" disabled="true"
				           style="background-color:#EFEFEF; border:0; " />
				</td>
			</tr>
			<tr>
				<td>
				  <strong>Título do Comando:</strong>
				</td>
				<td>
				   <html:textarea property="tituloComando" cols="45" rows="2" readonly="true" disabled="true"
				                  style="background-color:#EFEFEF; border:0; "/><br>
			    </td>	
			</tr>	
			<tr>
				<td><strong>Localidade Pólo:</strong></td>
				<td>
				<html:text property="idEloPolo" size="5" readonly="true" disabled="true" 
				           style="background-color:#EFEFEF; border:0; "/>
				<html:text property="descricaoEloPolo" size="36" readonly="true" disabled="true"
				           style="background-color:#EFEFEF; border:0; " />
				</td>
			</tr>
			<tr>
				<td><strong>Localidade:</strong></td>
				<td>
				<html:text property="idLocalidade" size="5" readonly="true" disabled="true" 
				           style="background-color:#EFEFEF; border:0; "/>
				<html:text property="descricaoLocalidade" size="36" readonly="true" disabled="true" 
				           style="background-color:#EFEFEF; border:0; "/>
				</td>
			</tr>
			<tr>
				<td><strong>Setor Comercial:</strong></td>
				<td>
				<html:text property="idSetorComercial" size="5" readonly="true" disabled="true" 
				           style="background-color:#EFEFEF; border:0; " />
				<html:text property="descricaoSetorComercial" size="36" readonly="true" disabled="true"
				           style="background-color:#EFEFEF; border:0; " />
				</td>
			</tr>
			<tr>
				<td><strong>Quadra:</strong></td>
				<td>
				<html:text property="idQuadra" size="5" readonly="true" disabled="true"
				           style="background-color:#EFEFEF; border:0; " />
				<html:text property="descricaoQuadra" size="36" readonly="true" disabled="true"
				           style="background-color:#EFEFEF; border:0; " />
				</td>
			</tr>

			<tr>
      	      <td><strong>Grupo de Cobrança:</strong></td>
                <td>
			      <html:select property="idGrupoCobranca" style="width: 200px;" tabindex="9">
			        <html:option value="">&nbsp;</html:option>
				    <html:options collection="colecaoCobrancaGrupoResultado" labelProperty="descricao" property="id"/>
			      </html:select>
		        </td>
              </tr>
            <tr>

            <tr>
      	      <td><strong>Gerência Regional:</strong></td>
                <td>
			      <html:select property="idGerenciaRegional" style="width: 200px;" tabindex="9">
			        <html:option value="">&nbsp;</html:option>
				    <html:options collection="colecaoGerenciaRegionalResultado" labelProperty="nomeAbreviado" property="id"/>
			      </html:select>
		        </td>
              </tr>
            <tr>
			 
			
            <tr>
      	      <td><strong>Unidade de Negócio:</strong></td>
                <td>
			      <html:select property="idUnidadeNegocio" style="width: 200px;" tabindex="9">
			        <html:option value="">&nbsp;</html:option>
				    <html:options collection="colecaoUnidadeNegocioResultado" labelProperty="nomeAbreviado" property="id"/>
			      </html:select>
		        </td>
              </tr>
            <tr>

            <tr>
      	      <td><strong>Perfil do Imóvel:</strong></td>
                <td>
			      <html:select property="idImovelPerfil" style="width: 200px;" tabindex="9">
			        <html:option value="">&nbsp;</html:option>
				    <html:options collection="colecaoImovelPerfilResultado" labelProperty="descricao" property="id"/>
			      </html:select>
		        </td>
              </tr>
            <tr>

            <tr>
      	      <td><strong>Categoria:</strong></td>
                <td>
			      <html:select property="idCategoria" style="width: 200px;" tabindex="9">
			        <html:option value="">&nbsp;</html:option>
				    <html:options collection="colecaoCategoriaResultado" labelProperty="descricao" property="id"/>
			      </html:select>
		        </td>
              </tr>
            <tr>

            <tr>
      	      <td><strong>Esfera de Poder:</strong></td>
                <td>
			      <html:select property="idEsferaPoder" style="width: 200px;" tabindex="9">
			        <html:option value="">&nbsp;</html:option>
				    <html:options collection="colecaoEsferaPoderResultado" labelProperty="descricao" property="id"/>
			      </html:select>
		        </td>
              </tr>
            <tr>

            <tr>
      	      <td><strong>Tipo de Cliente:</strong></td>
                <td>
			      <html:select property="tipoCliente" style="width: 200px;" tabindex="9">
			        <html:option value="">&nbsp;</html:option>
				    <html:options collection="colecaoClienteTipoResultado" labelProperty="descricao" property="id"/>
			      </html:select>
		        </td>
              </tr>
            <tr>


		</table>
		</td>
	</tr>
</table>
<br>
