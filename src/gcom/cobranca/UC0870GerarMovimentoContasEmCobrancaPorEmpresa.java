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
package gcom.cobranca;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.batch.ControladorBatchLocal;
import gcom.batch.ControladorBatchLocalHome;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.empresa.EmpresaCobranca;
import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.cadastro.empresa.FiltroEmpresaCobranca;
import gcom.cadastro.empresa.FiltroEmpresaCobrancaFaixa;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCobrancaSituacao;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.faturamento.conta.ContaGeral;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ErroRepositorioException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;

/**
 * Esta classe tem como finalidade encapsular o caso de uso [UC0745] - GerarArquivoTextoFaturamento, gerando
 * maior facilidade na manutenção do mesmo.  
 *
 * @author Raphael Rossiter
 * @date 30/04/2008
 */
public class UC0870GerarMovimentoContasEmCobrancaPorEmpresa {

	private static UC0870GerarMovimentoContasEmCobrancaPorEmpresa instancia;
	
	private IRepositorioCobranca repositorioCobranca;

	
	private UC0870GerarMovimentoContasEmCobrancaPorEmpresa(IRepositorioCobranca repositorioCobranca) {

		this.repositorioCobranca = repositorioCobranca;
	}

	public static UC0870GerarMovimentoContasEmCobrancaPorEmpresa getInstancia(IRepositorioCobranca repositorioCobranca) {
		
		if (instancia == null) {
			instancia = new UC0870GerarMovimentoContasEmCobrancaPorEmpresa(repositorioCobranca);
		}
		return instancia;
	}
	
	
	/**
	 * Controlador Util 
	 *
	 * @author Raphael Rossiter
	 * @date 30/04/2008
	 *
	 * @return ControladorUtilLocal
	 */
	private ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
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
	
	/**
	 * Controlador Imovel 
	 *
	 * @author Raphael Rossiter
	 * @date 30/04/2008
	 *
	 * @return ControladorImovelLocal
	 */
	protected ControladorImovelLocal getControladorImovel() {
		ControladorImovelLocalHome localHome = null;
		ControladorImovelLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorImovelLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_IMOVEL_SEJB);
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
	
	/**
	 * Controlador Faturamento 
	 *
	 * @author Raphael Rossiter
	 * @date 30/04/2008
	 *
	 * @return ControladorFaturamentoLocal
	 */
	private ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

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
	
	/**
	 * Controlador Cobranca 
	 *
	 * @author Raphael Rossiter
	 * @date 30/04/2008
	 *
	 * @return ControladorCobrancaLocal
	 */
	protected ControladorCobrancaLocal getControladorCobranca() {
		ControladorCobrancaLocalHome localHome = null;
		ControladorCobrancaLocal local = null;

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
	
	protected ControladorBatchLocal getControladorBatch() {
		ControladorBatchLocalHome localHome = null;
		ControladorBatchLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorBatchLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_BATCH_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
	
	/**
	 * [UC0870] - Gerar Movimento de Contas em Cobrança por Empresa
	 *
	 * @author Rafael Corrêa
	 * @date 17/04/2008
	 *
	 * @param idRota
	 * @param anoMesReferencia
	 * @return boolean
	 * @throws ControladorException
	 */
	@SuppressWarnings("unchecked")
	public void gerarMovimentoContasEmCobranca(ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta) 
		throws ControladorException {
		
		try {
			boolean percentualInformado = false;
			EmpresaCobranca empresaCobranca = null;

			SistemaParametro sistemaParametros = getControladorUtil().pesquisarParametrosDoSistema();
			Integer anoMesArrecadacaoInicio = sistemaParametros.getAnoMesArrecadacao();
			Integer anoMesArrecadacaoFim = Util.somaMesAnoMesReferencia(sistemaParametros.getAnoMesArrecadacao(),36);
			
			FiltroEmpresaCobranca filtroEmpresaCobranca = new FiltroEmpresaCobranca();
			filtroEmpresaCobranca.adicionarParametro(new ParametroSimples(FiltroEmpresaCobranca.EMPRESA_ID, comandoEmpresaCobrancaConta.getEmpresa().getId()));
			filtroEmpresaCobranca.adicionarParametro(new ParametroNulo(FiltroEmpresaCobranca.DATA_FIM_CONTRATO));

			Collection colecaoEmpresaCobranca = getControladorUtil().pesquisar(filtroEmpresaCobranca, EmpresaCobranca.class.getName());
			
			if (colecaoEmpresaCobranca != null && !colecaoEmpresaCobranca.isEmpty()) {
				empresaCobranca = (EmpresaCobranca) Util.retonarObjetoDeColecao(colecaoEmpresaCobranca);
				
				if (empresaCobranca.getPercentualContratoCobranca() != null){
					percentualInformado = true;
				}
			}
			
			
			boolean continuarPesquisa = true;
			
			int numeroPagina = 0;
			
			Collection<Integer> idsImoveis = null;
			
			Collection<Integer> idsImoveisAtualizar = new ArrayList();
			Collection collCobrancaSituacaoHistorico = new ArrayList();
			Collection collImovelCobrancaSituacao = new ArrayList();
			Collection<GerenciaRegional> collGerenciaRegional = new ArrayList();
			Collection<UnidadeNegocio> collUnidadeNegocio = new ArrayList();
			Collection<ImovelPerfil> collImovelPerfil = new ArrayList();
			Collection<LigacaoAguaSituacao> collLigacaoAguaSituacao = new ArrayList();
			ServicoTipo servicoTipo = null;
			ComandoEmpresaCobrancaContaHelper helper = new ComandoEmpresaCobrancaContaHelper();
			helper.setComandoEmpresaCobrancaConta(comandoEmpresaCobrancaConta);
			
			FiltroComandoEmpresaCobrancaContaGerencia filtroComandoEmpresaCobrancaContaGerencia = new FiltroComandoEmpresaCobrancaContaGerencia();
			filtroComandoEmpresaCobrancaContaGerencia.adicionarParametro(new ParametroSimples(
					FiltroComandoEmpresaCobrancaContaGerencia.ID_COMANDO_EMPRESA_COBRANCA_CONTA, comandoEmpresaCobrancaConta.getId()));
			Collection colecaoComandoEmpresaCobrancaContaGerencia = Fachada.getInstancia().pesquisar(
					filtroComandoEmpresaCobrancaContaGerencia, ComandoEmpresaCobrancaContaGerencia.class.getName());
			
			if (colecaoComandoEmpresaCobrancaContaGerencia != null && !colecaoComandoEmpresaCobrancaContaGerencia.isEmpty()) {
				Iterator iterator = colecaoComandoEmpresaCobrancaContaGerencia.iterator();
				while (iterator.hasNext()) {
					GerenciaRegional gerenciaRegional = (GerenciaRegional) iterator.next();
					collGerenciaRegional.add(gerenciaRegional);
				}
				helper.setColecaoGerenciaRegional(collGerenciaRegional);
			}

			FiltroComandoEmpresaCobrancaContaUnidadeNegocio filtroComandoEmpresaCobrancaContaUnidadeNegocio = new FiltroComandoEmpresaCobrancaContaUnidadeNegocio();
			filtroComandoEmpresaCobrancaContaUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroComandoEmpresaCobrancaContaUnidadeNegocio.ID_COMANDO_EMPRESA_COBRANCA_CONTA, comandoEmpresaCobrancaConta.getId()));
			Collection colecaoComandoEmpresaCobrancaContaUnidadeNegocio = Fachada.getInstancia().pesquisar(
					filtroComandoEmpresaCobrancaContaUnidadeNegocio, ComandoEmpresaCobrancaContaUnidadeNegocio.class.getName());
			
			if (colecaoComandoEmpresaCobrancaContaUnidadeNegocio != null && !colecaoComandoEmpresaCobrancaContaUnidadeNegocio.isEmpty()) {
				Iterator iterator = colecaoComandoEmpresaCobrancaContaUnidadeNegocio.iterator();
				while (iterator.hasNext()) {
					UnidadeNegocio unidadeNegocio = (UnidadeNegocio) iterator.next();
					collUnidadeNegocio.add(unidadeNegocio);
				}
				helper.setColecaoUnidadeNegocio(collUnidadeNegocio);
			}

			FiltroComandoEmpresaCobrancaContaImovelPerfil filtroComandoEmpresaCobrancaContaImovelPerfil = new FiltroComandoEmpresaCobrancaContaImovelPerfil();
			filtroComandoEmpresaCobrancaContaImovelPerfil.adicionarParametro(new ParametroSimples(
					FiltroComandoEmpresaCobrancaContaImovelPerfil.ID_COMANDO_EMPRESA_COBRANCA_CONTA, comandoEmpresaCobrancaConta.getId()));
			filtroComandoEmpresaCobrancaContaImovelPerfil.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
			Collection colecaoComandoEmpresaCobrancaContaImovelPerfil = Fachada.getInstancia().pesquisar(
					filtroComandoEmpresaCobrancaContaImovelPerfil, ComandoEmpresaCobrancaContaImovelPerfil.class.getName());
			
			if (colecaoComandoEmpresaCobrancaContaImovelPerfil != null && !colecaoComandoEmpresaCobrancaContaImovelPerfil.isEmpty()) {
				Iterator iterator = colecaoComandoEmpresaCobrancaContaImovelPerfil.iterator();
				while (iterator.hasNext()) {
					ComandoEmpresaCobrancaContaImovelPerfil comandoEmpresaCobrancaContaImovelPerfil = 
						(ComandoEmpresaCobrancaContaImovelPerfil) iterator.next();
					ImovelPerfil imovelPerfil = comandoEmpresaCobrancaContaImovelPerfil.getImovelPerfil();
					
					collImovelPerfil.add(imovelPerfil);
				}
				helper.setColecaoImovelPerfil(collImovelPerfil);
			}

			FiltroCmdEmpresaCobrancaContaLigacaoAguaSituacao filtroCmdEmpresaCobrancaContaLigacaoAguaSituacao = new FiltroCmdEmpresaCobrancaContaLigacaoAguaSituacao();
			filtroCmdEmpresaCobrancaContaLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
					FiltroCmdEmpresaCobrancaContaLigacaoAguaSituacao.ID_COMANDO_EMPRESA_COBRANCA_CONTA, comandoEmpresaCobrancaConta.getId()));
			filtroCmdEmpresaCobrancaContaLigacaoAguaSituacao.adicionarCaminhoParaCarregamentoEntidade(FiltroCmdEmpresaCobrancaContaLigacaoAguaSituacao.LIGACAO_AGUA_SITUACAO);
			Collection colecaoCmdEmpresaCobrancaContaLigacaoAguaSituacao = Fachada.getInstancia().pesquisar(
					filtroCmdEmpresaCobrancaContaLigacaoAguaSituacao, CmdEmpresaCobrancaContaLigacaoAguaSituacao.class.getName());
			
			if (colecaoCmdEmpresaCobrancaContaLigacaoAguaSituacao != null && !colecaoCmdEmpresaCobrancaContaLigacaoAguaSituacao.isEmpty()) {
				Iterator iterator = colecaoCmdEmpresaCobrancaContaLigacaoAguaSituacao.iterator();
				while (iterator.hasNext()) {
					CmdEmpresaCobrancaContaLigacaoAguaSituacao cmdEmpresaCobrancaContaLigacaoAguaSituacao = 
						(CmdEmpresaCobrancaContaLigacaoAguaSituacao) iterator.next();
					LigacaoAguaSituacao ligacaoAguaSituacao = cmdEmpresaCobrancaContaLigacaoAguaSituacao.getLigacaoAguaSituacao();
					
					collLigacaoAguaSituacao.add(ligacaoAguaSituacao);
				}
				helper.setColecaoLigacaoAguaSituacao(collLigacaoAguaSituacao);
			}
			
			if (comandoEmpresaCobrancaConta.getServicoTipo() != null && comandoEmpresaCobrancaConta.getServicoTipo().getId() != null) {
				FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
				filtroServicoTipo.adicionarParametro(new ParametroSimples(
						FiltroServicoTipo.ID,comandoEmpresaCobrancaConta.getServicoTipo().getId() ));
				filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade(
						FiltroServicoTipo.SERVICO_TIPO_REFERENCIA);
				Collection colecaoServTipo = Fachada.getInstancia().pesquisar(filtroServicoTipo, ServicoTipo.class.getName());
				
				if (colecaoServTipo != null && !colecaoServTipo.isEmpty()) {
					servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(colecaoServTipo); 
				}
			}
			
			
			while (continuarPesquisa) {
				
				idsImoveis = getControladorFaturamento()
						.pesquisarImoveisInformarContasEmCobranca(
								helper, numeroPagina, percentualInformado);
				
				if (idsImoveis != null && !idsImoveis.isEmpty()) {

					System.out.println("Cobrança por Resultado - Quantidade de Imóveis do Comando "
							+ comandoEmpresaCobrancaConta.getId() + ": " + idsImoveis.size());

					if (idsImoveis.size() < 1000) {
						continuarPesquisa = false;
					} else {
						numeroPagina = numeroPagina + idsImoveis.size();
					}

					Collection colecaoEmpresaCobrancaConta = new ArrayList();

					Collection<Object[]> colecaoDadosEmpresaCobrancaConta = repositorioCobranca
							.pesquisarContasInformarContasEmCobranca(
									comandoEmpresaCobrancaConta, idsImoveis, sistemaParametros);

					if (colecaoDadosEmpresaCobrancaConta != null
							&& !colecaoDadosEmpresaCobrancaConta.isEmpty()) {

						Map<Integer, Integer> imovelOS = new HashMap<Integer,Integer>();
						
						for (Object[] dadosEmpresaCobrancaConta : colecaoDadosEmpresaCobrancaConta) {

							if (!percentualInformado
								&& dadosEmpresaCobrancaConta != null
								&& dadosEmpresaCobrancaConta[2] != null
								&& ((Short)dadosEmpresaCobrancaConta[2]).equals(ConstantesSistema.NAO)) {
								continue;
							}
							
							EmpresaCobrancaConta empresaCobrancaConta = criarEmpresaCobrancaConta(
									dadosEmpresaCobrancaConta,
									comandoEmpresaCobrancaConta,
									empresaCobranca);

							if (dadosEmpresaCobrancaConta != null) {
								// imovel
								if (dadosEmpresaCobrancaConta[3] != null) {
									Integer idImovel = (Integer) dadosEmpresaCobrancaConta[3];
									if (!idsImoveisAtualizar.contains(idImovel)) {
										CobrancaSituacaoHistorico cobrancaSituacaoHistorico = criarCobrancaSituacaoHistorico(
												idImovel,
												anoMesArrecadacaoInicio,
												anoMesArrecadacaoFim);
										collCobrancaSituacaoHistorico
												.add(cobrancaSituacaoHistorico);

										ImovelCobrancaSituacao imovelCobrancaSituacao = criarImovelCobrancaSituacao(
												idImovel,
												comandoEmpresaCobrancaConta);
										collImovelCobrancaSituacao
												.add(imovelCobrancaSituacao);

										idsImoveisAtualizar
												.add(empresaCobrancaConta
														.getImovel().getId());
										
										// 2.1.19. Caso o comando tenha tipo de serviço informado
										if (servicoTipo != null && servicoTipo.getId() != null) {
											
											Imovel imovel = new Imovel();
											imovel.setId(new Integer(idImovel));
											
											if (servicoTipo != null && servicoTipo.getId() != null) {
												UnidadeOrganizacional unidadeOrganizacional = Fachada.getInstancia().
													pesquisarUnidadeOrganizacionalPorImovel(imovel.getId());
												
												OrdemServico ordemServico = new OrdemServico();
												ordemServico.setServicoTipo(servicoTipo);
												ordemServico.setImovel(imovel);
												ordemServico.setUnidadeAtual(unidadeOrganizacional);
												
												Integer idOS = Fachada.getInstancia().gerarOrdemServico(
														ordemServico, Usuario.USUARIO_BATCH, Funcionalidade.GERAR_MOVIMENTO_CONTAS_COBRANCA_POR_EMPRESA);
												
												imovelOS.put(idImovel, idOS);
											}
											
										}
										
									}
								}
								
							}

							if (imovelOS != null && !imovelOS.isEmpty() && imovelOS.get(empresaCobrancaConta.getImovel().getId()) != null) {
								OrdemServico ordemServico = new OrdemServico();
								ordemServico.setId(imovelOS.get(empresaCobrancaConta.getImovel().getId()));
								
								empresaCobrancaConta.setOrdemServico(ordemServico);
							}
							
							colecaoEmpresaCobrancaConta
									.add(empresaCobrancaConta);

						}

						getControladorBatch().inserirColecaoObjetoParaBatch(
								colecaoEmpresaCobrancaConta);

						idsImoveis.clear();
						idsImoveis = null;
						colecaoEmpresaCobrancaConta.clear();
						colecaoEmpresaCobrancaConta = null;

					} else {
						continuarPesquisa = false;
					}
				}else{
					continuarPesquisa = false;
				}

			}

			if (percentualInformado) {
				getControladorBatch().inserirColecaoObjetoParaBatch(
						collCobrancaSituacaoHistorico);
			}
			
			getControladorBatch().inserirColecaoObjetoParaBatch(
					collImovelCobrancaSituacao);
			
			Integer idCobSituacao = repositorioCobranca.pesquisarCobrancaSituacao(
					CobrancaSituacao.COBRANCA_EMPRESA_TERCEIRIZADA);
			
			if (idCobSituacao != null
					&& idsImoveisAtualizar != null
					&& !idsImoveisAtualizar.isEmpty()) {
				getControladorImovel().atualizarSituacaoCobrancaETipoIdsImoveis(
					idCobSituacao,
					CobrancaSituacaoTipo.COBRANCA_EMPRESA_TERCEIRIZADA,
					idsImoveisAtualizar);
			}
			
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		
	}
	
	private EmpresaCobrancaConta criarEmpresaCobrancaConta(Object[] dadosEmpresaCobrancaConta, ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta, EmpresaCobranca empresaCobranca) throws ControladorException, ErroRepositorioException {
		
		EmpresaCobrancaConta retorno = new EmpresaCobrancaConta();
		Integer idImovel = null;
		
		if (dadosEmpresaCobrancaConta != null) {
			
			// Id da Conta
			if (dadosEmpresaCobrancaConta[0] != null) {
				ContaGeral contaGeral = new ContaGeral();
				contaGeral.setId((Integer) dadosEmpresaCobrancaConta[0]);
				retorno.setContaGeral(contaGeral);
			}
			
			// Valor Original da Conta
			if (dadosEmpresaCobrancaConta[1] != null) {
				retorno.setValorOriginalConta((BigDecimal) dadosEmpresaCobrancaConta[1]);
			}
			
			// Indicador Pagamento Válido
			if (dadosEmpresaCobrancaConta[2] != null) {
				retorno.setIndicadorPagamentoValido((Short) dadosEmpresaCobrancaConta[2]);
			}
			// imovel
			if(dadosEmpresaCobrancaConta[3] != null) {
				Imovel imovel = new Imovel();
				imovel.setId((Integer) dadosEmpresaCobrancaConta[3]);
				retorno.setImovel(imovel);
				
				idImovel = imovel.getId();
			}
			
			
		}
		
		if (empresaCobranca != null) {
			
			if (empresaCobranca.getPercentualContratoCobranca() != null) {
				
				retorno.setPercentualEmpresaConta(empresaCobranca.getPercentualContratoCobranca());
				
			} else {
				SistemaParametro sistemaParametros = getControladorUtil().pesquisarParametrosDoSistema();
				Integer quantidadeContas = repositorioCobranca.pesquisarQuantidadeContasEmCobrancaPorImovel(
							comandoEmpresaCobrancaConta, idImovel, sistemaParametros);
				
				FiltroEmpresaCobrancaFaixa filtroEmpresaCobrancaFaixa = new FiltroEmpresaCobrancaFaixa();
				filtroEmpresaCobrancaFaixa.adicionarParametro(new ParametroSimples(
						FiltroEmpresaCobrancaFaixa.EMPRESA_CONTRATO_COBRANCA_ID, empresaCobranca.getId()));
				
				List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa = (List<EmpresaCobrancaFaixa>)Fachada.getInstancia().pesquisar(
						filtroEmpresaCobrancaFaixa, EmpresaCobrancaFaixa.class.getName());
				
				if (colecaoEmpresaCobrancaFaixa != null && !colecaoEmpresaCobrancaFaixa.isEmpty()) {
					
					for (int i = 0; i < colecaoEmpresaCobrancaFaixa.size(); i++) {
						
						EmpresaCobrancaFaixa empresaCobrancaFaixa = (EmpresaCobrancaFaixa) colecaoEmpresaCobrancaFaixa.get(i);
						
						Integer numeroMinimoContas = empresaCobrancaFaixa.getNumeroMinimoContasFaixa();
						
						Integer numeroMaximoContas = null;
						
						if (i < (colecaoEmpresaCobrancaFaixa.size() - 1)) {
							numeroMaximoContas = ((EmpresaCobrancaFaixa) colecaoEmpresaCobrancaFaixa.get(i + 1)).getNumeroMinimoContasFaixa() - 1;
						}
						
						if (quantidadeContas >= numeroMinimoContas
								&& (numeroMaximoContas == null || quantidadeContas <= numeroMaximoContas)) {
							
							retorno.setPercentualEmpresaConta(empresaCobrancaFaixa.getPercentualFaixa());
							break;
						}
						
					}
				}
			}
			
		}
		

		retorno.setEmpresa(comandoEmpresaCobrancaConta.getEmpresa());
		retorno.setComandoEmpresaCobrancaConta(comandoEmpresaCobrancaConta);
		retorno.setUltimaAlteracao(new Date());
		
		return retorno;
	}
	
	private CobrancaSituacaoHistorico criarCobrancaSituacaoHistorico(Integer idImovel,Integer anoMesArrecadacaoInicio,Integer anoMesArrecadacaoFim){
		
		
		CobrancaSituacaoHistorico cobrancaSituacaoHistorico = new CobrancaSituacaoHistorico();

		Imovel imovel = new Imovel();
		imovel.setId(idImovel);
		cobrancaSituacaoHistorico.setImovel(imovel);

		CobrancaSituacaoMotivo cobrancaSituacaoMotivo = new CobrancaSituacaoMotivo();
		cobrancaSituacaoMotivo
				.setId(CobrancaSituacaoMotivo.IMOVEIS_ENVIADOS_EMPRESA_TERCEIRIZADA);
		cobrancaSituacaoHistorico
				.setCobrancaSituacaoMotivo(cobrancaSituacaoMotivo);

		CobrancaSituacaoTipo cobrancaSituacaoTipo = new CobrancaSituacaoTipo();
		cobrancaSituacaoTipo
				.setId(CobrancaSituacaoTipo.COBRANCA_EMPRESA_TERCEIRIZADA);
		cobrancaSituacaoHistorico.setCobrancaSituacaoTipo(cobrancaSituacaoTipo);

		cobrancaSituacaoHistorico.setUltimaAlteracao(new Date());

		cobrancaSituacaoHistorico
				.setAnoMesCobrancaSituacaoInicio(anoMesArrecadacaoInicio);
		cobrancaSituacaoHistorico
				.setAnoMesCobrancaSituacaoFim(anoMesArrecadacaoFim);

		cobrancaSituacaoHistorico.setUsuario(Usuario.USUARIO_BATCH);
		cobrancaSituacaoHistorico.setUsuarioInforma(Usuario.USUARIO_BATCH);

		
		
		
	
		return cobrancaSituacaoHistorico;
	}
	

	private ImovelCobrancaSituacao criarImovelCobrancaSituacao(
			Integer idImovel, ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta) throws ControladorException, ErroRepositorioException{

		ImovelCobrancaSituacao imovelCobrancaSituacao = new ImovelCobrancaSituacao();

		Imovel imovel = new Imovel();
		imovel.setId(idImovel);
		imovelCobrancaSituacao.setImovel(imovel);

		imovelCobrancaSituacao.setDataImplantacaoCobranca(new Date());
		imovelCobrancaSituacao.setUltimaAlteracao(new Date());

		Integer idCobSituacao = repositorioCobranca.pesquisarCobrancaSituacao(
				CobrancaSituacao.COBRANCA_EMPRESA_TERCEIRIZADA);
		
		CobrancaSituacao cobrancaSituacao = new CobrancaSituacao();
		cobrancaSituacao.setId(idCobSituacao);
		imovelCobrancaSituacao.setCobrancaSituacao(cobrancaSituacao);

		Cliente cliente = getControladorImovel().pesquisarClienteUsuarioImovel(
				imovelCobrancaSituacao.getImovel().getId());

		imovelCobrancaSituacao.setCliente(cliente);

		
		
				
		return imovelCobrancaSituacao;
	}
	
}
