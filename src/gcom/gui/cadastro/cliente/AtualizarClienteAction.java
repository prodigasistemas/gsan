/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroClienteEndereco;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.cliente.OrgaoExpedidorRg;
import gcom.cadastro.cliente.PessoaSexo;
import gcom.cadastro.cliente.Profissao;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.cadastro.descricaogenerica.DescricaoGenerica;
import gcom.cadastro.descricaogenerica.FiltroDescricaoGenerica;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.integracao.webservice.spc.ConsultaWebServiceTest;
import gcom.seguranca.AtributoGrupo;
import gcom.seguranca.ConsultaCdl;
import gcom.seguranca.FiltroConsultaCadastroCdl;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroUsuarioPemissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecial;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Description of the Class
 * 
 * @author Rodrigo
 */
public class AtualizarClienteAction extends GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = httpServletRequest.getSession(false);

		// Pega o form do cliente
		DynaValidatorForm clienteActionForm = (DynaValidatorForm) actionForm;

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		       
		Fachada fachada = Fachada.getInstancia();

		Short tipoPessoa = (Short) clienteActionForm.get("tipoPessoa");
		
		String tipoPessoaForm = tipoPessoa.toString();

		FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();

		filtroClienteTipo.adicionarParametro(new ParametroSimples(
				FiltroClienteTipo.ID, tipoPessoaForm));
		tipoPessoa = ((ClienteTipo) fachada.pesquisar(filtroClienteTipo,
				ClienteTipo.class.getName()).iterator().next())
				.getIndicadorPessoaFisicaJuridica();

		Short indicadorUsoNomeFantasiaConta = ConstantesSistema.NAO;

		if (clienteActionForm.get("indicadorExibicaoNomeConta") != null) {
			
			String indicadorExibicaoNomeConta = null;
			indicadorExibicaoNomeConta = (String) clienteActionForm.get(
					"indicadorExibicaoNomeConta").toString();

			if (indicadorExibicaoNomeConta
					.equals(Cliente.INDICADOR_NOME_FANTASIA.toString())) {

				indicadorUsoNomeFantasiaConta = ConstantesSistema.SIM;
			}
		}

		// Verifica o destino porque se o usuário tentar concluir o processo
		// nesta página, não é necessário verificar a tela de confirmação
		// if (destinoPagina != null && !destinoPagina.trim().equals("")) {
		if (tipoPessoa != null
				&& tipoPessoa.equals(ClienteTipo.INDICADOR_PESSOA_JURIDICA)) {
			// Vai para Pessoa Juridica mas tem dados existentes em pessoa fisica
			String cpf = (String) clienteActionForm.get("cpf");
			String rg = (String) clienteActionForm.get("rg");
			String dataEmissao = (String) clienteActionForm.get("dataEmissao");
			Integer idOrgaoExpedidor = (Integer) clienteActionForm.get("idOrgaoExpedidor");
			Integer idUnidadeFederacao = (Integer) clienteActionForm.get("idUnidadeFederacao");
			String dataNascimento = (String) clienteActionForm.get("dataNascimento");
			Integer idProfissao = (Integer) clienteActionForm.get("idProfissao");
			Integer idPessoaSexo = (Integer) clienteActionForm.get("idPessoaSexo");

			if( ( idPessoaSexo != null && idPessoaSexo != ConstantesSistema.NUMERO_NAO_INFORMADO )
				|| ( cpf != null && !cpf.trim().equalsIgnoreCase("") )
					|| ( rg != null && !rg.trim().equalsIgnoreCase("") )
						|| ( dataEmissao != null && !dataEmissao.trim().equalsIgnoreCase("") )
							|| ( dataNascimento != null && !dataNascimento.trim().equalsIgnoreCase("") )
								|| ( idOrgaoExpedidor != null && idOrgaoExpedidor != ConstantesSistema.NUMERO_NAO_INFORMADO )
									|| ( idUnidadeFederacao != null && idUnidadeFederacao != ConstantesSistema.NUMERO_NAO_INFORMADO )
										|| ( idProfissao != null && idProfissao != ConstantesSistema.NUMERO_NAO_INFORMADO ) ){

				// Limpar todo o conteúdo da página de pessoa física
				clienteActionForm.set("cpf", "");
				clienteActionForm.set("rg", "");
				clienteActionForm.set("dataEmissao", "");
				clienteActionForm.set("idOrgaoExpedidor", new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO));
				clienteActionForm.set("idUnidadeFederacao", new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO));
				clienteActionForm.set("dataNascimento", "");
				clienteActionForm.set("idProfissao", new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO));
				clienteActionForm.set("idPessoaSexo", new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO));
			}
		}else if (tipoPessoa != null
			&& tipoPessoa.equals(ClienteTipo.INDICADOR_PESSOA_FISICA)) {
			// Vai para Pessoa Fisica mas tem dados existentes em pessoa juridica

			String cnpj = (String) clienteActionForm.get("cnpj");
			Integer idRamoAtividade = (Integer) clienteActionForm.get("idRamoAtividade");
			String codigoClienteResponsavel = (String) clienteActionForm.get("codigoClienteResponsavel");

			if( (cnpj != null && !cnpj.trim().equalsIgnoreCase("") )
					|| (idRamoAtividade != null && idRamoAtividade != ConstantesSistema.NUMERO_NAO_INFORMADO)
						|| (codigoClienteResponsavel != null && !codigoClienteResponsavel.trim().equalsIgnoreCase(""))) {
				// Limpa os dados da página de pessoa jurídica
				clienteActionForm.set("cnpj", "");
				clienteActionForm.set("idRamoAtividade", new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO));
				clienteActionForm.set("codigoClienteResponsavel", "");
				clienteActionForm.set("nomeClienteResponsavel", "");
			}
		}

		// Pega o cliente que foi selecionado para atualização
		Cliente clienteAtualizacao = (Cliente) sessao
				.getAttribute("clienteAtualizacao");

		// Pega a coleção de endereços do cliente
		Collection colecaoEnderecos = (Collection) sessao
				.getAttribute("colecaoEnderecos");

		// Pega a coleção de telefones do cliente
		Collection colecaoFones = (Collection) sessao
				.getAttribute("colecaoClienteFone");

		// Cria o objeto do cliente para ser inserido
		String nome = ((String) clienteActionForm.get("nome")).toUpperCase();
		
		/**
		 * Autor: Paulo Diniz
		 * Data: 11/07/2011
		 * [RR2011061059]
		 * [UC0009]
		 */
		if(clienteAtualizacao.getIndicadorUso() != null && clienteAtualizacao.getIndicadorUso().intValue() == 2){
			//[FS0013] Verificar permissÃ£o especial alterar cliente inativo
			FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, PermissaoEspecial.ALTERAR_CLIENTE_INATIVO));
			
			Collection colecaoUsuarioPermisao = fachada.pesquisar(filtroUsuarioPemissaoEspecial, UsuarioPermissaoEspecial.class.getName());
			if (colecaoUsuarioPermisao == null || colecaoUsuarioPermisao.isEmpty()) {
				throw new ActionServletException(
					"atencao.usuario.sem.permissao.para.alteracao.cliente.inativo");
			}
			
		}
		

		/**
		 * Autor: Mariana Victor
		 * Data:  28/12/2010
		 * RM_3320 - [FS0010] Verificar Duplicidade de cliente
		 */
		if (this.getSistemaParametro().getIndicadorDuplicidadeCliente().toString()
				.equals(ConstantesSistema.SIM.toString())) {
			
			FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, PermissaoEspecial.INSERIR_CLIENTE_COM_MESMO_NOME_E_ENDERECO));
									
			Collection colecaoUsuarioPermisao = fachada.pesquisar(filtroUsuarioPemissaoEspecial, UsuarioPermissaoEspecial.class.getName());
			
			if (colecaoUsuarioPermisao == null || colecaoUsuarioPermisao.isEmpty()) {
				FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
				filtroClienteEndereco.adicionarParametro(new ParametroSimples(FiltroClienteEndereco.NOME, nome.toUpperCase()));

				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoTipo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("cliente");
				
				Collection<ClienteEndereco> colecaoClienteEndereco = fachada.pesquisar(filtroClienteEndereco, ClienteEndereco.class.getName());
				
				if (colecaoClienteEndereco != null && !colecaoClienteEndereco.isEmpty()){
					Iterator iterator = colecaoClienteEndereco.iterator();
					
					while (iterator.hasNext()) {
						ClienteEndereco clienteEnderecoIterator = (ClienteEndereco) iterator.next();
						
						Iterator iteratorEnderecos = colecaoEnderecos.iterator();
						while (iteratorEnderecos.hasNext()) {
							ClienteEndereco clienteEndereco = (ClienteEndereco) iteratorEnderecos
									.next();
							
							if (clienteEndereco.getEnderecoFormatado().equals(
									clienteEnderecoIterator.getEnderecoFormatado())
									&& !clienteAtualizacao.getId().equals(
											clienteEnderecoIterator.getCliente().getId())) {
								throw new ActionServletException("atencao.duplicidade.cliente", null,
									"Cliente");
							}
						}
					}
				}	
				
			}
			
		}
		
		/**
		 * Autor: Mariana Victor
		 * Data:  28/12/2010
		 * RM_3320 - [FS0011] Verificar Nome de Cliente com menos de 10 posições
		 */
		if (this.getSistemaParametro().getIndicadorNomeMenorDez().toString()
				.equals(ConstantesSistema.NAO.toString())) {
			
			FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, PermissaoEspecial.INSERIR_NOMES_COM_MENOS_DE_10_CARACTERES));
									
			Collection colecaoUsuarioPermisao = fachada.pesquisar(filtroUsuarioPemissaoEspecial, UsuarioPermissaoEspecial.class.getName());
			
			if (colecaoUsuarioPermisao == null || colecaoUsuarioPermisao.isEmpty()) {
				String nomeFormatado= nome.replaceAll(" ", "");
				if (nomeFormatado.length() < 10) {
					throw new ActionServletException("atencao.nome.sobrenome.cliente.menos.dez.posicoes",
							null, nome);
				}
			}
			
		}

		/**
		 * Autor: Mariana Victor
		 * Data:  28/12/2010
		 * RM_3320 - [FS0012] Verificar Nome de Cliente com Descrição Genérica
		 */
		if (this.getSistemaParametro().getIndicadorNomeClienteGenerico().toString()
				.equals(ConstantesSistema.NAO.toString())) {
			
			FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, PermissaoEspecial.INSERIR_NOME_CLIENTE_GENERICO));
									
			Collection colecaoUsuarioPermisao = fachada.pesquisar(filtroUsuarioPemissaoEspecial, UsuarioPermissaoEspecial.class.getName());
			
			if (colecaoUsuarioPermisao == null || colecaoUsuarioPermisao.isEmpty()) {
				FiltroDescricaoGenerica filtroDescricaoGenerica = new FiltroDescricaoGenerica();
				Collection colecaoDescricaoGenerica = fachada.pesquisar(filtroDescricaoGenerica, DescricaoGenerica.class.getName());
				
				if (colecaoDescricaoGenerica != null || !colecaoDescricaoGenerica.isEmpty()) {
					String nomeFormatado= nome.replaceAll(" ", "");
					Iterator iteratorDescricaoGenerica = colecaoDescricaoGenerica.iterator();
					
					while (iteratorDescricaoGenerica.hasNext()) {
						DescricaoGenerica descricaoGenerica = (DescricaoGenerica) iteratorDescricaoGenerica.next();
						String nomeGenerico = descricaoGenerica.getNomeGenerico();
						String nomeGenericoFormatado = nomeGenerico.replaceAll(" ", "");
						
						if (nomeGenerico.equalsIgnoreCase(nome)
								|| nomeGenericoFormatado.equalsIgnoreCase(nome)
								|| nomeGenerico.equalsIgnoreCase(nomeFormatado)
								|| nomeGenericoFormatado.equalsIgnoreCase(nomeFormatado)) {
							throw new ActionServletException("atencao.nome.cliente.descricao.generica",
									null, "Nome do Cliente");		
						}
					}
					
				}
				
			}
			
		}
		
		String nomeAbreviado = ((String) clienteActionForm.get("nomeAbreviado")).toUpperCase();
		String rg = (String) clienteActionForm.get("rg");
		String cpf = (String) clienteActionForm.get("cpf");
		if(cpf != null && cpf.trim().equals("")){
			cpf = null;
		}
		String dataEmissao = (String) clienteActionForm.get("dataEmissao");
		String dataNascimento = (String) clienteActionForm.get("dataNascimento");
		String cnpj = (String) clienteActionForm.get("cnpj");
		if(cnpj != null && cnpj.trim().equals("")){
			cnpj = null;
		}
		String indicadorAcaoCobranca =  (String)clienteActionForm.get("indicadorAcaoCobranca");

		String email = (String) clienteActionForm.get("email");
		
		Short indicadorUso = null;
		
		if(clienteActionForm.get("indicadorUso") != null){
			indicadorUso = new Short((String) clienteActionForm
					.get("indicadorUso"));
		}else{
			indicadorUso = new Short("1");	
		}
		
		Short indicadorAcrescimos = null;
		if(clienteActionForm.get("indicadorAcrescimos") != null){
			indicadorAcrescimos = new Short((String)clienteActionForm
					.get("indicadorAcrescimos"));
		} else {
			indicadorAcrescimos = new Short("1");
		}

		// Verificar se o usuário digitou os 4 campos relacionados com o RG de
		// pessoa física ou se ele não digitou nenhum

		Integer idOrgaoExpedidor = (Integer) clienteActionForm
				.get("idOrgaoExpedidor");
		Integer idUnidadeFederacao = (Integer) clienteActionForm
				.get("idUnidadeFederacao");

		if( ! ( ( (rg != null && !rg.trim().equalsIgnoreCase(""))
					&& (idOrgaoExpedidor != null && !idOrgaoExpedidor.equals(ConstantesSistema.NUMERO_NAO_INFORMADO))) 
						&& (idUnidadeFederacao != null && !idUnidadeFederacao.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) 
							|| ((rg != null && rg.trim().equalsIgnoreCase(""))
									&& (idOrgaoExpedidor != null && idOrgaoExpedidor.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) 
										&& (idUnidadeFederacao != null && idUnidadeFederacao.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)))) ){
			throw new ActionServletException(
					"atencao.rg_campos_relacionados.nao_preenchidos");
		}

		OrgaoExpedidorRg orgaoExpedidorRg = null;
		if (clienteActionForm.get("idOrgaoExpedidor") != null
				&& ((Integer) clienteActionForm.get("idOrgaoExpedidor")).intValue() > 0) {
			orgaoExpedidorRg = new OrgaoExpedidorRg();
			orgaoExpedidorRg.setId((Integer) clienteActionForm
					.get("idOrgaoExpedidor"));
		}

		PessoaSexo pessoaSexo = null;
		if (clienteActionForm.get("idPessoaSexo") != null
				&& ((Integer) clienteActionForm.get("idPessoaSexo")).intValue() > 0) {
			pessoaSexo = new PessoaSexo();
			pessoaSexo.setId((Integer) clienteActionForm.get("idPessoaSexo"));
		}

		Profissao profissao = null;
		if (clienteActionForm.get("idProfissao") != null
				&& ((Integer) clienteActionForm.get("idProfissao")).intValue() > 0) {
			profissao = new Profissao();
			profissao.setId((Integer) clienteActionForm.get("idProfissao"));
		}

		UnidadeFederacao unidadeFederacao = null;
		if (clienteActionForm.get("idUnidadeFederacao") != null
				&& ((Integer) clienteActionForm.get("idUnidadeFederacao")).intValue() > 0) {
			unidadeFederacao = new UnidadeFederacao();
			unidadeFederacao.setId((Integer) clienteActionForm.get("idUnidadeFederacao"));
		}

		ClienteTipo clienteTipo = new ClienteTipo();
		clienteTipo.setId(new Integer(((Short) clienteActionForm.get("tipoPessoa")).intValue()));

		RamoAtividade ramoAtividade = null;
		if (clienteActionForm.get("idRamoAtividade") != null
				&& ((Integer) clienteActionForm.get("idRamoAtividade")).intValue() > 0) {
			ramoAtividade = new RamoAtividade();
			ramoAtividade.setId((Integer) clienteActionForm
					.get("idRamoAtividade"));
		}
 
		Cliente clienteResponsavel = null;
		if (clienteActionForm.get("codigoClienteResponsavel") != null
				&& !((String) clienteActionForm.get("codigoClienteResponsavel")).trim().equalsIgnoreCase("")) {
			// Cria o objeto do cliente responsável
			clienteResponsavel = new Cliente();
			clienteResponsavel.setId(new Integer((String) clienteActionForm
					.get("codigoClienteResponsavel")));
		}

		// Verifica se o usuário adicionou um endereço de correspondência
		Long enderecoCorrespondenciaSelecao = (Long) clienteActionForm
				.get("enderecoCorrespondenciaSelecao");

		if (enderecoCorrespondenciaSelecao == null
				|| enderecoCorrespondenciaSelecao == 0) {
			throw new ActionServletException(
					"atencao.endereco_correspondencia.nao_selecionado");
		}

		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

		// Verifica se o nome do Cliente é o mesmo encontrado na R. Federal de acordo com o CPF digitado
		ConsultaCdl clienteCadastradoNaReceita = new ConsultaCdl();
		//String mensagemRetornoReceita = null;
		try {
			
			if(cpf != null && cpf.equals("")){
				cpf = null;
			}

			if(cnpj != null && cnpj.equals("")){
				cnpj = null;
			}
			
			Cliente cliente = new Cliente(
					// Nome
					nome,
					
					// Nome Abreviado
					nomeAbreviado,
					
					// CPF
					cpf,
					
					// RG
					rg,
					
					// Data de Emissão do RG
					dataEmissao != null && !dataEmissao.trim().equalsIgnoreCase("") 
						? formatoData.parse(dataEmissao): null,
								
					// Data de Nascimento
					dataNascimento != null && !dataNascimento.trim().equalsIgnoreCase("") 
						? formatoData.parse(dataNascimento) : null, 
					
					// CNPJ
					cnpj, 
					
					// Email
					email, 
					
					// Indicador Uso
					indicadorUso,
					
					// Indicador Acrescimos
					indicadorAcrescimos,
					
					// Data da Última Alteração
					clienteAtualizacao.getUltimaAlteracao(),
					
					// Órgão Expedidor RG
					orgaoExpedidorRg,
					
					// Cliente Responsável
					clienteResponsavel, 
					
					// Sexo
					pessoaSexo,
					
					// Profissão
					profissao,
					
					// Unidade Federação
					unidadeFederacao, 
					
					// Tipo do Cliente
					clienteTipo,
					
					// Ramo de Atividade
					ramoAtividade,
					indicadorUsoNomeFantasiaConta);

			// Seta o id do cliente atualizado para ser identificado no BD na atualização
			cliente.setId(clienteAtualizacao.getId());
			
			
			cliente.setIndicadorAcaoCobranca(new Integer (indicadorAcaoCobranca).shortValue());
			
			
			cliente.setIndicadorGeraArquivoTexto(clienteAtualizacao.getIndicadorGeraArquivoTexto());
			
			cliente.setDiaVencimento(clienteAtualizacao.getDiaVencimento());

			
//			 Permissao Especial Validar Acrescimos Impontualidade

			boolean validarAcrescimoImpontualidade = Fachada.getInstancia()
			.verificarPermissaoValAcrescimosImpontualidade(usuario);
			
			httpServletRequest.setAttribute("validarAcrescimoImpontualidade",validarAcrescimoImpontualidade);

            
            if (clienteActionForm.get("diaVencimento") != null
                    && !(clienteActionForm.get("diaVencimento").equals(""))){
                String diaVencimento = (String)clienteActionForm.get("diaVencimento"); 
                cliente.setDataVencimento( new Short(diaVencimento));
            }else{
                cliente.setDataVencimento(null);
            }
        
        	//Nome da Mãe	
            if (clienteActionForm.get("nomeMae") != null
                        && (!(clienteActionForm.get("nomeMae").equals("")))) {
            	cliente.setNomeMae(((String)clienteActionForm.get("nomeMae")).toUpperCase());
             }
            
			if (clienteActionForm.get("indicadorGeraFaturaAntecipada") != null && !clienteActionForm.get("indicadorGeraFaturaAntecipada").equals("")) {
				cliente.setIndicadorGeraFaturaAntecipada(new Short((String) clienteActionForm.get("indicadorGeraFaturaAntecipada")));
			} else {
				cliente.setIndicadorGeraFaturaAntecipada(ConstantesSistema.NAO);
			}
			
			if (clienteActionForm.get("diaVencimento") != null && !(clienteActionForm.get("diaVencimento").equals("")) && 
			   (clienteActionForm.get("indicadorVencimentoMesSeguinte") != null && !clienteActionForm.get("indicadorVencimentoMesSeguinte").equals(""))) {
				cliente.setIndicadorVencimentoMesSeguinte(new Short((String) clienteActionForm.get("indicadorVencimentoMesSeguinte")));
			} else {
				cliente.setIndicadorVencimentoMesSeguinte(ConstantesSistema.NAO);
			}
			
			 if (clienteActionForm.get("indicadorAcaoCobranca") != null
	                    && !(clienteActionForm.get("indicadorAcaoCobranca").equals(""))){
				 cliente.setIndicadorAcaoCobranca(new Integer ((String)clienteActionForm.get("indicadorAcaoCobranca")).shortValue());
			 }

			 if (clienteActionForm.get("indicadorPermiteNegativacao") != null
						&& clienteActionForm.get("indicadorPermiteNegativacao").equals(ConstantesSistema.SIM.toString())){
					
					cliente.setIndicadorPermiteNegativacao(ConstantesSistema.NAO);
				} else {
					cliente.setIndicadorPermiteNegativacao(ConstantesSistema.SIM);
				}
			
			//*************************************************************************
			// Autor: Ivan Sergio
			// Data: 06/08/2009
			// CRC2103
			// Verifica se a funcionalidade esta sendo executada dentro de um popup
			//*************************************************************************
			if (sessao.getAttribute("POPUP") != null) {
				if (sessao.getAttribute("POPUP").equals("true")) {
					Integer idImovel = null;
					if (sessao.getAttribute("idImovel") != null && 
							!sessao.getAttribute("idImovel").equals("")) {
						idImovel = new Integer(sessao.getAttribute("idImovel").toString());
					}else if (sessao.getAttribute("imovelAtualizacao") != null) {
						Imovel imovel = (Imovel) sessao.getAttribute("imovelAtualizacao");
						idImovel = new Integer(imovel.getId());
					}
					
					if (idImovel == null) {
						cliente.setId2(-1);
						colecaoEnderecos = this.setaId2ClienteEnderecos(colecaoEnderecos, -1);
						colecaoFones = this.setaId2ClienteFones(colecaoFones, -1);
					} else {
						//Integer idImovel = new Integer(sessao.getAttribute("idImovel").toString());
						cliente.setId2(idImovel);
						colecaoEnderecos = this.setaId2ClienteEnderecos(colecaoEnderecos, idImovel);
						colecaoFones = this.setaId2ClienteFones(colecaoFones, idImovel);
						
						// Recupera o Tipo de Relacao do Cliente
						FiltroClienteImovel filtro = new FiltroClienteImovel();
						filtro.adicionarCaminhoParaCarregamentoEntidade(
								FiltroClienteImovel.CLIENTE_RELACAO_TIPO);
						filtro.adicionarParametro(new ParametroSimples(
								FiltroClienteImovel.CLIENTE_ID, cliente.getId()));
						filtro.adicionarParametro(new ParametroSimples(
								FiltroClienteImovel.IMOVEL_ID, idImovel));
						
						ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(
								fachada.pesquisar(filtro, ClienteImovel.class.getName()));
						
						if (clienteImovel != null) {
							if (clienteImovel.getClienteRelacaoTipo() != null) {
								Integer idAtributoGrupo = null;
								switch (clienteImovel.getClienteRelacaoTipo().getId()) {
								case 1:
									idAtributoGrupo = AtributoGrupo.ATRIBUTOS_DO_PROPRIETARIO;
									break;
								case 2:
									idAtributoGrupo = AtributoGrupo.ATRIBUTOS_DO_USUARIO;
									break;
								}
								
								if (idAtributoGrupo != null) {
									AtributoGrupo atributoGrupo = new AtributoGrupo();
									atributoGrupo.setId(idAtributoGrupo);
									
									OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
									operacaoEfetuada.setAtributoGrupo(atributoGrupo);
									
									cliente.setOperacaoEfetuada(operacaoEfetuada);
								}
							}
						}
					}
				}
			}
			
			/**
			 * Autor: Rodrigo Cabral
			 * Data: 20/10/2010
			 * CRC4476
			 */
			String confirmado = null;
			if ( httpServletRequest.getParameter("confirmado") != null  ) {
				confirmado = httpServletRequest.getParameter("confirmado");
			}
			
			ConsultaCdl consultaCdl = null;
			FiltroConsultaCadastroCdl filtroConsultaCadastroCdl = new FiltroConsultaCadastroCdl();
			
			Short indicadorConsultaDocumentoReceita = this.getSistemaParametro().getIndicadorConsultaDocumentoReceita();
			
			if(cpf != null || cnpj != null){
				
				if (cpf != null){
					filtroConsultaCadastroCdl.adicionarParametro(
						new ParametroSimples(FiltroConsultaCadastroCdl.CPF_CLIENTE, cpf));
				}
				
				if (cnpj != null){
					filtroConsultaCadastroCdl.adicionarParametro(
						new ParametroSimples(FiltroConsultaCadastroCdl.CNPJ_CLIENTE, cnpj));
				}
				
				Collection colecaoConsultaCadastroCdl = 
					fachada.pesquisar(filtroConsultaCadastroCdl, ConsultaCdl.class.getName());
				
				consultaCdl = (ConsultaCdl)Util.retonarObjetoDeColecao(colecaoConsultaCadastroCdl);
			}else{
				indicadorConsultaDocumentoReceita = ConstantesSistema.NAO;
			}
			
			if (confirmado == null && 
				consultaCdl == null &&
				indicadorConsultaDocumentoReceita.toString().equals(ConstantesSistema.SIM.toString())){
				
				ConsultaWebServiceTest consultaWebService = new ConsultaWebServiceTest();
				
				try {
					if (cpf != null){
						clienteCadastradoNaReceita = consultaWebService.consultarPessoaFisica(nome,cpf);
						System.out.println("CONSULTA SPC ATUALIZAR CLIENTE CPF: "+cpf);
					}else if (cnpj != null){
						clienteCadastradoNaReceita = consultaWebService.consultaPessoaJuridica(nome,cnpj);
						System.out.println("CONSULTA SPC ATUALIZAR CLIENTE CNPJ: "+cnpj);
					}
				} catch (Exception e) {
					e.printStackTrace();
					clienteCadastradoNaReceita.setMensagemRetorno("Erro ao consultar o CDL.");
				}
					
				if(clienteCadastradoNaReceita.getNomeCliente() != null && 
					!clienteCadastradoNaReceita.getNomeCliente().equals("NOME NAO CADASTRADO") &&
					!clienteCadastradoNaReceita.getNomeCliente().equals("EMPRESA NAO CADASTRADA") ){
					
					System.out.println("NOME RETORNADO CDL "+cpf+":"+clienteCadastradoNaReceita.getNomeCliente());
					
					clienteActionForm.set("nomeClienteReceitaFederal" , clienteCadastradoNaReceita.getNomeCliente());
				
				}else{
					clienteCadastradoNaReceita.setNomeCliente(null);
					clienteCadastradoNaReceita.setMensagemRetorno("Erro ao consultar o CDL.");
				}
				
				sessao.setAttribute("clienteCadastradoNaReceita", clienteCadastradoNaReceita);
			
			}else if (confirmado == null && 
					consultaCdl != null && 
					indicadorConsultaDocumentoReceita.toString().equals(ConstantesSistema.SIM.toString()) ){
				
				clienteCadastradoNaReceita.setNomeCliente(consultaCdl.getNomeCliente());
				
				sessao.setAttribute("clienteCadastradoNaReceita", clienteCadastradoNaReceita);
			}

			short codigoAcao = ConstantesSistema.NUMERO_NAO_INFORMADO;
			boolean atualizaImovel = true;
			
			//Caso o spc esteja fora, não realizar acao de atualizacao do cliente e dos dados do spc
			if(clienteCadastradoNaReceita != null &&
				clienteCadastradoNaReceita.getMensagemRetorno() != null){
				
				atualizaImovel = false;
				retorno = this.montaTelaAtencao(actionMapping,
						httpServletRequest,
						"atencao.cliente_nao_foi_atualizado_spc_fora",
						false);
				
			}
			
			if ( confirmado == null && 
				clienteCadastradoNaReceita.getNomeCliente() != null &&
				!clienteCadastradoNaReceita.getNomeCliente().equals(nome) ) {
				
				httpServletRequest.setAttribute("nomeBotao1", "Aceitar");
				httpServletRequest.setAttribute("nomeBotao3", "Rejeitar");
		
				
				return montarPaginaConfirmacaoWizard("atencao.confirmacao_nome_receita_federal",
							httpServletRequest, 
							actionMapping, 
							nome, 
							clienteCadastradoNaReceita.getNomeCliente());
					
			}else if(confirmado == null && 
				clienteCadastradoNaReceita.getNomeCliente() != null &&
				clienteCadastradoNaReceita.getNomeCliente().equals(nome)){
				
				
				clienteCadastradoNaReceita = 
					(ConsultaCdl) sessao.getAttribute("clienteCadastradoNaReceita");

				codigoAcao = 3;
				
				clienteCadastradoNaReceita.setCodigoAcaoOperador(codigoAcao);
				
			
			}else if ( confirmado != null && confirmado.trim().equalsIgnoreCase("ok") ) {
				
				clienteCadastradoNaReceita = 
					(ConsultaCdl) sessao.getAttribute("clienteCadastradoNaReceita");
				
				cliente.setNome(clienteCadastradoNaReceita.getNomeCliente());
				
				if (clienteCadastradoNaReceita.getNomeMae() != null){
					cliente.setNomeMae(clienteCadastradoNaReceita.getNomeMae());
				}
				
				if (clienteCadastradoNaReceita.getDataNascimento() != null){
					cliente.setDataNascimento(clienteCadastradoNaReceita.getDataNascimento());
				}
				
				codigoAcao = 1;
				clienteCadastradoNaReceita.setCodigoAcaoOperador(codigoAcao);
				
			} else if((clienteCadastradoNaReceita.getMensagemRetorno() == null || 
				clienteCadastradoNaReceita.getMensagemRetorno().equals("")) && 
				(confirmado != null)){
				
				clienteCadastradoNaReceita = 
					(ConsultaCdl) sessao.getAttribute("clienteCadastradoNaReceita");
				
				codigoAcao = 2;
				clienteCadastradoNaReceita.setCodigoAcaoOperador(codigoAcao);
				
				atualizaImovel = false;
				
				retorno = this.montaTelaAtencao(actionMapping,
					httpServletRequest,
					"atencao.cliente_nao_foi_atualizado",
					true);
			} 

			/**
			 * fim
			 */
			
			if(atualizaImovel){
				// Atualiza o cliente
				this.getFachada().atualizarCliente(cliente, 
					colecaoFones,
					colecaoEnderecos, 
					usuario);
			}
			
			

			
			if ((confirmado != null) ||
					(clienteCadastradoNaReceita.getCodigoAcaoOperador() != null &&
							clienteCadastradoNaReceita.getCodigoAcaoOperador() == 3)){
				
				if(consultaCdl == null){
					ConsultaCdl clienteCadastradoNaReceitaAtualiza = 
						(ConsultaCdl) sessao.getAttribute("clienteCadastradoNaReceita");
					
					clienteCadastradoNaReceitaAtualiza.setCodigoAcaoOperador(codigoAcao);
					clienteCadastradoNaReceitaAtualiza.setCodigoCliente(cliente);
					clienteCadastradoNaReceitaAtualiza.setUsuario(usuario);
					clienteCadastradoNaReceitaAtualiza.setCpfUsuario(usuario.getCpf());
					clienteCadastradoNaReceitaAtualiza.setLoginUsuario(usuario.getLogin());
					clienteCadastradoNaReceitaAtualiza.setUltimaAlteracao(new Date());

					this.getFachada().inserir(clienteCadastradoNaReceitaAtualiza);
				}
			}

			// limpa a sessão
			sessao.removeAttribute("clienteCadastradoNaReceita");
			sessao.removeAttribute("colecaoClienteFone");
			sessao.removeAttribute("colecaoEnderecos");
			sessao.removeAttribute("foneTipos");
			sessao.removeAttribute("municipios");
			sessao.removeAttribute("colecaoResponsavelSuperiores");
			sessao.removeAttribute("InserirEnderecoActionForm");
			sessao.removeAttribute("ClienteActionForm");
			sessao.removeAttribute("tipoPesquisaRetorno");
			sessao.removeAttribute("clienteAtualizacao");

		} catch (ParseException ex) {
			// Erro no hibernate
			reportarErros(httpServletRequest, "erro.sistema", ex);
			// Atribui o mapeamento de retorno para a tela de erro
			retorno = actionMapping.findForward("telaErro");
		}

		// Verifica se a funcionalidade esta sendo executada dentro de um popup
		boolean exibirTelaSucesso = true;
		if (sessao.getAttribute("POPUP") != null) {
			if (sessao.getAttribute("POPUP").equals("true")) {
				// Verifica o action de retorno
				// action = inserirClienteNomeTipo
				retorno = actionMapping.findForward("atualizarClientePopUp");
				sessao.setAttribute("codigoCliente", clienteAtualizacao.getId());
				sessao.setAttribute("nomeCliente", nome);
				if (cpf != null) {
					sessao.setAttribute("cpfCnpjCliente", Util.formatarCpf(cpf));
				}else if (cnpj != null) {
					sessao.setAttribute("cpfCnpjCliente", Util.formatarCnpj(cnpj));
				}
				
				httpServletRequest.setAttribute("colecaoTipoPessoa", null);
				exibirTelaSucesso = false;
			}
		}
		
		if (exibirTelaSucesso) {
			
			// Monta a página de sucesso
			if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
				
				String linkSucesso = (String)sessao.getAttribute("linkSucesso");
				String mensagemSucesso = "Cliente de código " + clienteAtualizacao.getId() + " atualizado com sucesso.";	
				
//				if(mensagemRetornoReceita != null && !mensagemRetornoReceita.equals("")){
//					mensagemSucesso = mensagemSucesso +"\n"+ mensagemRetornoReceita;
//				}
				
				if(linkSucesso != null && !linkSucesso.equals("")){
					
					montarPaginaSucesso(httpServletRequest, 
						mensagemSucesso,
						"Realizar outra Manutenção de Cliente", "exibirManterClienteAction.do?menu=sim",
						linkSucesso,
						"Retornar ao Consultar Imóvel.");
					
					sessao.removeAttribute("linkSucesso");
					
				}else if(sessao.getAttribute("caminhoVoltarPromais")!=null){
					
					montarPaginaSucesso(httpServletRequest, 
						mensagemSucesso,
						"Realizar outra Manutenção de Cliente", "exibirManterClienteAction.do?menu=sim",
						(String)sessao.getAttribute("caminhoVoltarPromais")+".do?promais=nao","Retornar ao Consultar Imóvel.");
					
					sessao.setAttribute("promaisExecutado", "sim");
					sessao.setAttribute("idImovelPromaisExecutado", Integer.parseInt((String)sessao.getAttribute("idImovel")));
					sessao.removeAttribute("idImovel");
					
					sessao.removeAttribute("caminhoVoltarPromais");
					
				}else{
					montarPaginaSucesso(httpServletRequest, 
						mensagemSucesso,
						"Realizar outra Manutenção de Cliente", 
						"exibirManterClienteAction.do?menu=sim");
				}
			}
		}

		return retorno;
	}
	
	/***
	 * @author Ivan Sergio
	 * @date: 11/08/2009
	 * 
	 * @param colecaoEnderecos
	 * @param id2
	 * @return
	 */
	private Collection setaId2ClienteEnderecos(Collection colecaoEnderecos, Integer id2) {
		Collection retorno = null;
		
		if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()) {
			retorno = new ArrayList();
			Iterator iColecaoEnderecos = colecaoEnderecos.iterator();
			
			while (iColecaoEnderecos.hasNext()) {
				ClienteEndereco endereco = (ClienteEndereco) iColecaoEnderecos.next();
				endereco.setId2(id2);
				retorno.add(endereco);
			}
		}else {
			retorno = colecaoEnderecos;
		}
		
		return retorno;
	}
	
	/**
	 * @author Ivan Sergio
	 * @date: 11/08/2009
	 * 
	 * @param colecaoFones
	 * @param id2
	 * @return
	 */
	private Collection setaId2ClienteFones(Collection colecaoFones, Integer id2) {
		Collection retorno = null;
		
		if (colecaoFones != null && !colecaoFones.isEmpty()) {
			retorno = new ArrayList();
			Iterator iColecaoFones = colecaoFones.iterator();
			
			while (iColecaoFones.hasNext()) {
				ClienteFone fone = (ClienteFone) iColecaoFones.next();
				fone.setId2(id2);
				retorno.add(fone);
			}
		}else {
			retorno = colecaoFones;
		}
		
		return retorno;
	}
	
	/**
	 * @author Rafael Pinto
	 * @date: 09/01/2011
	 * 
	 * @param actionMapping ActionMapping
	 * @param httpServletRequest httpServletRequest
	 * @return ActionForward
	 */
	private ActionForward montaTelaAtencao(ActionMapping actionMapping, 
		HttpServletRequest httpServletRequest,String chave,
		boolean naoExibirBotaoVoltarTelaAtencao){
				
		httpServletRequest.setAttribute("naoExibirBotaoVoltarTelaAtencao",naoExibirBotaoVoltarTelaAtencao);
		reportarErros(httpServletRequest, chave);
		
		return actionMapping.findForward("telaAtencao");
	} 
	

}
