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
package gcom.gerencial.cadastro.imovel;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 * @created 7 de Junho de 2004
 */
public class ControladorGerencialImovelSEJB implements SessionBean {
	private static final long serialVersionUID = 1L;
	SessionContext sessionContext;

	//private IRepositorioGerencialImovel gRepositorioImovel;

	//private IRepositorioCategoria repositorioCategoria;


	/**
	 * Retorna o valor de controladorCliente
	 * 
	 * @return O valor de controladorCliente
	 */
	/*
	private ControladorClienteLocal getControladorCliente() {

		ControladorClienteLocalHome localHome = null;
		ControladorClienteLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorClienteLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_CLIENTE_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	*/

	/**
	 * Retorna o valor de controladorAcesso
	 * 
	 * @return O valor de controladorAcesso
	 */
	/*
	private ControladorAcessoLocal getControladorAcesso() {
		ControladorAcessoLocalHome localHome = null;
		ControladorAcessoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorAcessoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ACESSO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	*/

	/**
	 * Retorna o valor de controladorCobranca
	 * 
	 * @return O valor de controladorCobranca
	 */
	/*
	private ControladorCobrancaLocal getControladorCobranca() {

		ControladorCobrancaLocalHome localHome = null;
		ControladorCobrancaLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorCobrancaLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_COBRANCA_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	*/

	/**
	 * Retorna o valor de controladorCobranca
	 * 
	 * @return O valor de controladorCobranca
	 */
	/*
	private ControladorFaturamentoLocal getControladorFaturamento() {

		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFaturamentoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	*/

	/**
	 * Retorna o valor de controladorEndereco
	 * 
	 * @return O valor de controladorEndereco
	 */
	/*
	private ControladorEnderecoLocal getControladorEndereco() {

		ControladorEnderecoLocalHome localHome = null;
		ControladorEnderecoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorEnderecoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ENDERECO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	*/

	/**
	 * < <Descrição do método>>
	 * 
	 * @exception CreateException
	 *                Descrição da exceção
	 */
	public void ejbCreate() throws CreateException {

		//gRepositorioImovel = RepositorioGerencialImovelHBM.getInstancia();

		//repositorioCategoria = RepositorioCategoriaHBM.getInstancia();

	}

	/**
	 * Método chamado no momento que o bean é removido do contexto do container
	 */
	public void ejbRemove() {
	}

	/**
	 * Método chamado no momento que o bean é ativado no container
	 */
	public void ejbActivate() {
	}

	/**
	 * Método chamado no momento que o bean é passivado no container
	 */
	public void ejbPassivate() {
	}

	/**
	 * * Seta o valor de sessionContext
	 * 
	 * @param sessionContext
	 *            O novo valor de sessionContext
	 */

	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}

	/*
	 * --- INICIO DOS METODOS DE NEGOCIO DO SESSION BEAN ---
	 * 
	 */

	/**
	 * Obtém a principal categoria do imóvel
	 * 
	 * [UC0306] Obter Principal Categoria do Imóvel
	 * 
	 * @author Pedro Alexandre
	 * @date 18/04/2006
	 * 
	 * @param idImovel
	 * @return
	 * @throws ControladorException
	 */
	/*public GCategoria obterPrincipalCategoriaImovel(Integer idImovel) throws ControladorException {
		// Cria a variável que vai armazenar a categoria principal do imóvel
		GCategoria categoriaPrincipal = null;

		// Cria a coleção que vai armazenar as categorias com maiorquantidade de
		// economias
		Collection<GCategoria> colecaoCategoriasComMaiorQtdEconomias = new ArrayList();

		// [UC0108] Obtém a quantidade de economias por categoria
		Collection<GCategoria> colecaoCategoriasImovel = this.obterQuantidadeEconomiasCategoria(idImovel);

		// Inicializa a quantidade de categoria
		int quantidadeCategoria = -1;

		// Caso a coleção de categorias do imóvel não esteja nula
		if (colecaoCategoriasImovel != null) {
			
			// Laço para verificar qual a categoria com maior quantidade de economia
			for (GCategoria categoriaImovel : colecaoCategoriasImovel) {
				
				if (quantidadeCategoria < categoriaImovel.getQuantidadeEconomiasCategoria().intValue()) {
					
					quantidadeCategoria = categoriaImovel.getQuantidadeEconomiasCategoria();

					colecaoCategoriasComMaiorQtdEconomias = new ArrayList();
					colecaoCategoriasComMaiorQtdEconomias.add(categoriaImovel);
				} else if (quantidadeCategoria == categoriaImovel.getQuantidadeEconomiasCategoria().intValue()) {
					colecaoCategoriasComMaiorQtdEconomias.add(categoriaImovel);
				}
			}
		}

		// [FS0001]Verificar mais de uma categoria com a maior quantidade de economias
		// Caso só exista um objeto na coleção, recupera a categoria E atribui a
		// categoria principal
		// Caso contrário recupera a categoria com o menor id
		if (colecaoCategoriasComMaiorQtdEconomias.size() == 1) {
			
			categoriaPrincipal = colecaoCategoriasComMaiorQtdEconomias.iterator().next();
			
		} else if (colecaoCategoriasComMaiorQtdEconomias.size() > 1) {
			
			for (GCategoria categoriaImovel : colecaoCategoriasComMaiorQtdEconomias) {
				int idTemp = -1;
				if (idTemp < categoriaImovel.getId().intValue()) {
					idTemp = categoriaImovel.getId().intValue();
					categoriaPrincipal = categoriaImovel;
				}
			}
			
		}

		// Retorna a categoria principal
		return categoriaPrincipal;
	}*/

	
	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * <Breve descrição sobre o subfluxo>
	 *
	 * <Identificador e nome do subfluxo>	
	 *
	 * <Breve descrição sobre o fluxo secundário>
	 *
	 * <Identificador e nome do fluxo secundário> 
	 *
	 * @author Administrador
	 * @date 27/04/2007
	 *
	 * @param idCategoria
	 * @return
	 * @throws ControladorException
	 *//*
	public ImovelSubcategoria obterPrincipalSubcategoria(Integer idCategoria) throws ControladorException {

		try {
			Collection<ImovelSubcategoria> colSubCategorias = repositorioImovel
					.obterSubCategoriasPorCategoria(idCategoria);

			ImovelSubcategoria subcategoriaPrincipal = null;

			// Selecionamos o de maior quantidade de economias
			if (colSubCategorias != null && colSubCategorias.size() > 0) {
				for (ImovelSubcategoria sub : colSubCategorias) {
					if (subcategoriaPrincipal == null
							|| subcategoriaPrincipal.getQuantidadeEconomias() < sub
									.getQuantidadeEconomias())
						subcategoriaPrincipal = sub;
				}
			}

			return subcategoriaPrincipal;
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}
*/
	
	
	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * @author Pedro Alexandre
	 * @date 27/04/2007
	 *
	 * @param imovel
	 * @return
	 * @throws ControladorException
	 */
	/*public Collection obterQuantidadeEconomiasCategoria(Integer imovel)	throws ControladorException {

		Collection colecaoCategoria = new ArrayList();
		Collection colecaoImovelSubCategoriaArray = null;

		try {
			colecaoImovelSubCategoriaArray = gRepositorioImovel.pesquisarObterQuantidadeEconomiasCategoria(imovel);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoImovelSubCategoriaArray != null && !colecaoImovelSubCategoriaArray.isEmpty()) {

			Iterator colecaoImovelSubCategoriaArrayIterator = colecaoImovelSubCategoriaArray.iterator();

			while (colecaoImovelSubCategoriaArrayIterator.hasNext()) {

				// Obtém o imóvel subcategoria
				Object[] imovelSubcategoriaArray = (Object[]) colecaoImovelSubCategoriaArrayIterator.next();

				// Cria os objetos categoria
				Categoria categoria = new Categoria();

				// Seta a categoria
				categoria.setId((Integer) imovelSubcategoriaArray[0]);

				// Seta a descrição
				categoria.setDescricao(String.valueOf(imovelSubcategoriaArray[1]));

				// Seta o consumo estouro
				categoria.setConsumoEstouro((Integer) imovelSubcategoriaArray[2]);
				
				// Seta número de vezes média estouro
				categoria.setVezesMediaEstouro((BigDecimal) imovelSubcategoriaArray[3]);
				
				// Seta a quantidade de economias por categoria
				categoria.setQuantidadeEconomiasCategoria(((Short) imovelSubcategoriaArray[4]).intValue());
				
				// Seta o consumo alto
				categoria.setConsumoAlto((Integer) imovelSubcategoriaArray[6]);

				// Seta a média baixo consumo
				categoria.setMediaBaixoConsumo((Integer) imovelSubcategoriaArray[7]);
				
				// Seta o número de vezes média consumo alto
				categoria.setVezesMediaAltoConsumo((BigDecimal) imovelSubcategoriaArray[8]);
				
				// Seta o percentual da média baixo consumo
				categoria.setPorcentagemMediaBaixoConsumo((BigDecimal) imovelSubcategoriaArray[9]);

				// Seta a descricao abreviada
				if ((String) imovelSubcategoriaArray[10] != null) {
					categoria.setDescricaoAbreviada((String) imovelSubcategoriaArray[10]);
				}
				categoria.setNumeroConsumoMaximoEc((Integer) imovelSubcategoriaArray[11]);

				categoria.setIndicadorCobrancaAcrescimos((Short) imovelSubcategoriaArray[12]);

				colecaoCategoria.add(categoria);
			}

		} else {
			// Caso a coleção não tenha retornado objetos
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.nao_cadastrado.imovel_subcategoria", null);
		}

		return colecaoCategoria;
	}*/

}