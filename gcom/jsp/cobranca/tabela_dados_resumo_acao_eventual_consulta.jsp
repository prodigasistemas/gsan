<!-- Para o uso deste include é necessário manda a colecaoAgrupamento(que é de Object[3]) e o mesAnoReferencia
já formatado ver ExibirResultadoConsultaResumoAnormalidadeAction.java  -->

<table width="100%" border="0" bgcolor="#99ccff">
	<tr heigth="20">
		<td align="center"><strong>Dados da Geração da Consulta</strong></td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="0" bgcolor="#cbe5fe">
			
			<logic:iterate name="colecaoAgrupamento" id="agrupamento"
				scope="Session">
				<tr>
				 <%if(((String)((Object[]) agrupamento)[0]).equals("Período de Emissão")){ %>
					<td><strong> <%=((Object[]) agrupamento)[0]%>:</strong></td>
					<td><input type="text" name="opcao1"
						value="<%= ((Object[])agrupamento)[1] %>" size="10" disabled="true" />
					&nbsp;à&nbsp;<input type="text" name="opcao2"
						value="<%= ((Object[])agrupamento)[2]%>" size="10" disabled="true" />
					</td>
				<%}else{ 
					if(((String)((Object[]) agrupamento)[0]).equals("Comando")){
				%>	
				    <td><strong> <%=((Object[]) agrupamento)[0]%>:</strong></td>
					<td colspan="2"><input type="text" name="opcao1"
						value="<%= ((Object[])agrupamento)[1] %>" size="60" disabled="true" />
					</td>
				<%}else{ %>
				    <td><strong> <%=((Object[]) agrupamento)[0]%>:</strong></td>
					<td><input type="text" name="opcao1"
						value="<%= ((Object[])agrupamento)[1] %>" size="5" disabled="true" />
					&nbsp;&nbsp;<input type="text" name="opcao2"
						value="<%= ((Object[])agrupamento)[2]%>" size="30" disabled="true" />
					</td>
				<%} 
				}%>	
				</tr>
			</logic:iterate>
			<tr>
				<td><strong>Grupo de Cobrança:</strong></td>
				<td><html:select property="idCobrancaGrupo">
					<html:options collection="colecaoCobrancaGrupoResultado"
						labelProperty="descricao" property="id" />
				</html:select></td>
			</tr>
			<tr>
				<td><strong>Gerência Regional:</strong></td>
				<td><html:select property="idGerenciaRegional">
				<logic:iterate name="colecaoGerenciaRegionalResultado" id="gerenciaRegional" type="GerenciaRegional">
			        <logic:empty name="gerenciaRegional" property="nomeAbreviado">
			        <html:option value="<%=""+ gerenciaRegional.getId()%>">
					 <%=gerenciaRegional.getNome()%></html:option>
			        </logic:empty>
			        <logic:notEmpty name="gerenciaRegional" property="nomeAbreviado">
				 	 <html:option value="<%=""+ gerenciaRegional.getId()%>">
					 <%= gerenciaRegional.getNomeAbreviado() + " - " + gerenciaRegional.getNome()%></html:option>
				    </logic:notEmpty>	
				</logic:iterate>
				</html:select></td>
			</tr>
			<tr>
				<td><strong>Unidade de Negócio:</strong></td>
				<td><html:select property="idUnidadeNegocio">
				<logic:iterate name="colecaoUnidadeNegocioResultado" id="unidadeNegocio" type="UnidadeNegocio">
			        <logic:empty name="unidadeNegocio" property="nomeAbreviado">
			        <html:option value="<%=""+ unidadeNegocio.getId()%>">
					 <%=unidadeNegocio.getNome()%></html:option>
			        </logic:empty>
			        <logic:notEmpty name="unidadeNegocio" property="nomeAbreviado">
				 	 <html:option value="<%=""+ unidadeNegocio.getId()%>">
					 <%= unidadeNegocio.getNomeAbreviado() + " - " + unidadeNegocio.getNome()%></html:option>
				    </logic:notEmpty>	
				</logic:iterate>
				</html:select></td>
			</tr>			
			<tr>
				<td><strong>Perfil do Imóvel:</strong></td>
				<td><html:select property="idPerfilImovel">
					<html:options collection="colecaoImovelPerfilResultado"
						labelProperty="descricao" property="id" />
				</html:select></td>
			</tr>
			<tr>
				<td><strong>Situação de Ligação de Água:</strong></td>
				<td><html:select property="idSituacaoLigacaoAgua">
					<html:options collection="colecaoLigacaoAguaSituacaoResultado"
						labelProperty="descricao" property="id" />
				</html:select></td>
			</tr>
			<tr>
				<td><strong>Situação de Ligação de Esgoto:</strong></td>
				<td><html:select property="idSituacaoLigacaoEsgoto">
					<html:options collection="colecaoLigacaoEsgotoSituacaoResultado"
						labelProperty="descricao" property="id" />
				</html:select></td>
			</tr>
			<tr>
				<td><strong>Categoria:</strong></td>
				<td><html:select property="idCategoria">
					<html:options collection="colecaoCategoriaResultado"
						labelProperty="descricao" property="id" />
				</html:select></td>
			</tr>
			<tr>
				<td><strong>Esfera de Poder:</strong></td>
				<td><html:select property="idEsfera">
					<html:options collection="colecaoEsferaPoderResultado"
						labelProperty="descricao" property="id" />
				</html:select></td>
			</tr>
			<tr>
				<td><strong>Empresa:</strong></td>
				<td><html:select property="idEmpresa">
					<html:options collection="colecaoEmpresaResultado"
						labelProperty="descricao" property="id" />
				</html:select></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<br>
