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
package gcom.gui.cobranca;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.cadastro.empresa.EmpresaContratoCobranca;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.empresa.FiltroEmpresaCobrancaFaixa;
import gcom.cadastro.empresa.FiltroEmpresaContratoCobranca;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.ComandoEmpresaCobrancaConta;
import gcom.cobranca.ComandoEmpresaCobrancaContaHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que faz a exibição da tela para o usuário setar os campos e permitir
 * que ele insera uma resolução de diretoria [UC0217] Inserir Resolução de
 * Diretoria
 * 
 * @author Rafael Corrêa
 * @since 30/03/2006
 */
public class ExibirInformarContasEmCobrancaAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirInformarContasEmCobranca");

		InformarContasEmCobrancaActionForm informarContasEmCobrancaActionForm = 
			(InformarContasEmCobrancaActionForm) actionForm;

		if (httpServletRequest.getParameter("menu") != null && 
			!httpServletRequest.getParameter("menu").trim().equals("")) {

			informarContasEmCobrancaActionForm.setQuantidadeDiasVencimento("60");
			
			// Coleção de Unidade de Negócio
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);

			Collection<UnidadeNegocio> colecaoUnidadeNegocio = 
				this.getFachada().pesquisar(
					filtroUnidadeNegocio, 
					UnidadeNegocio.class.getName());

			this.getSessao(httpServletRequest).setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);

			// Coleção de Categoria
			FiltroCategoria filtroCategoria = new FiltroCategoria();
			filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);

			Collection<Categoria> colecaoCategoria = 
				this.getFachada().pesquisar(filtroCategoria, Categoria.class.getName());

			this.getSessao(httpServletRequest).setAttribute("colecaoCategoria", colecaoCategoria);

			// Coleção de Perfil de Imóvel
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
			filtroImovelPerfil.adicionarParametro(new ParametroSimples(
					FiltroImovelPerfil.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);

			Collection<ImovelPerfil> colecaoImovelPerfil = 
				this.getFachada().pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());

			this.getSessao(httpServletRequest).setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);
			
			// Coleção de Gerencia Regional
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);

			Collection<GerenciaRegional> colecaoGerenciaRegional = 
				this.getFachada().pesquisar(
					filtroGerenciaRegional, 
					GerenciaRegional.class.getName());

			this.getSessao(httpServletRequest).setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
			
			// Coleção de Situação de Ligação de Água
			FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
			filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
					FiltroLigacaoAguaSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);

			Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao = 
				this.getFachada().pesquisar(
					filtroLigacaoAguaSituacao, 
					LigacaoAguaSituacao.class.getName());

			this.getSessao(httpServletRequest).setAttribute("colecaoLigacaoAguaSituacao", colecaoLigacaoAguaSituacao);

		}

		pesquisarCamposEnter(httpServletRequest,informarContasEmCobrancaActionForm, this.getFachada());
		
		if (httpServletRequest.getParameter("limparTotalizacao") != null
				&& httpServletRequest.getParameter("limparTotalizacao").equalsIgnoreCase("SIM")) {

			informarContasEmCobrancaActionForm.setQtdContas("");
			informarContasEmCobrancaActionForm.setQtdClientes("");
			informarContasEmCobrancaActionForm.setValorTotalDivida("");

			informarContasEmCobrancaActionForm.setTotalSelecionado(null);
			this.getSessao(httpServletRequest).setAttribute("habilitaCamposCiclo", false);
			
			informarContasEmCobrancaActionForm.setColecaoInformada(null);
			this.getSessao(httpServletRequest).removeAttribute("colecaoQuantidadeContas");
			this.getSessao(httpServletRequest).removeAttribute("colecaoFaixa");
			this.getSessao(httpServletRequest).removeAttribute("colecaoQtdeContas");
			this.getSessao(httpServletRequest).removeAttribute("colecaoQtdeClientes");
			this.getSessao(httpServletRequest).removeAttribute("colecaoValorTotalDivida");
		}
		
		if (httpServletRequest.getParameter("pesquisarQtdContas") != null
				&& informarContasEmCobrancaActionForm.getIdEmpresa() != null
				&& !informarContasEmCobrancaActionForm.getIdEmpresa().equals("")) {
			
			ComandoEmpresaCobrancaContaHelper comandoEmpresaCobrancaContaHelper = this.retornaQtdContas(informarContasEmCobrancaActionForm);
			
			Integer idEmpresaContratoCobranca = new Integer(informarContasEmCobrancaActionForm.getIdEmpresa());
			
			if (comandoEmpresaCobrancaContaHelper != null) {
				
				boolean agruparPorImovel = true;
				
				FiltroEmpresaContratoCobranca filtroEmpresaContratoCobranca = new FiltroEmpresaContratoCobranca();
				filtroEmpresaContratoCobranca.adicionarParametro(new ParametroSimples(
						FiltroEmpresaContratoCobranca.EMPRESA_ID, idEmpresaContratoCobranca));
				
				Collection colecaoEmpresaContratoCobranca = Fachada.getInstancia().pesquisar(
						filtroEmpresaContratoCobranca, EmpresaContratoCobranca.class.getName());
				
				if (colecaoEmpresaContratoCobranca != null && !colecaoEmpresaContratoCobranca.isEmpty()) {
					EmpresaContratoCobranca empresaContratoCobranca = (EmpresaContratoCobranca) 
						Util.retonarObjetoDeColecao(colecaoEmpresaContratoCobranca);
					
					idEmpresaContratoCobranca = empresaContratoCobranca.getId();
					
					if (empresaContratoCobranca.getPercentualContratoCobranca() != null
							&& empresaContratoCobranca.getPercentualContratoCobranca().compareTo(BigDecimal.ZERO) != 0) {
						agruparPorImovel = false;
					}
				}
				
				
				if (agruparPorImovel) {
					Collection<Object[]> colecaoDados = this.getFachada().pesquisarQuantidadeContasAgrupandoPorImovel(comandoEmpresaCobrancaContaHelper);
					
					if (colecaoDados != null && !colecaoDados.isEmpty()) {
						Collection<String> colecaoFaixa = new ArrayList();
						Collection<Integer> colecaoQtdeContas = new ArrayList();
						Collection<Integer> colecaoQtdeClientes = new ArrayList();
						Collection<BigDecimal> colecaoValorTotalDivida= new ArrayList();

						FiltroEmpresaCobrancaFaixa filtroEmpresaCobrancaFaixa = new FiltroEmpresaCobrancaFaixa();
						filtroEmpresaCobrancaFaixa.adicionarParametro(new ParametroSimples(
								FiltroEmpresaCobrancaFaixa.EMPRESA_CONTRATO_COBRANCA_ID, idEmpresaContratoCobranca));
						filtroEmpresaCobrancaFaixa.setCampoOrderBy(FiltroEmpresaCobrancaFaixa.NUMERO_MAXIMO_CONTAS_FAIXA);
						
						List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa = (List<EmpresaCobrancaFaixa>)Fachada.getInstancia().pesquisar(
								filtroEmpresaCobrancaFaixa, EmpresaCobrancaFaixa.class.getName());
						
						if (colecaoEmpresaCobrancaFaixa != null && !colecaoEmpresaCobrancaFaixa.isEmpty()) {

							EmpresaCobrancaFaixa empresaCobrancaFaixa = (EmpresaCobrancaFaixa) colecaoEmpresaCobrancaFaixa.get(0);
							Integer numeroMinimoContas = null;
							Integer numeroMaximoContas = empresaCobrancaFaixa.getNumeroMinimoContasFaixa() - 1;
							
							Integer qtdeContas = 0;
							Integer qtdeClientes = 0;
							
							Iterator iteratorColecaoDados = colecaoDados.iterator();
							
							for (int i = 0; i < colecaoEmpresaCobrancaFaixa.size(); i++) {
								
								empresaCobrancaFaixa = (EmpresaCobrancaFaixa) colecaoEmpresaCobrancaFaixa.get(i);

								numeroMinimoContas = empresaCobrancaFaixa.getNumeroMinimoContasFaixa();
								
								numeroMaximoContas = null;
								
								if (i < (colecaoEmpresaCobrancaFaixa.size() - 1)) {
									numeroMaximoContas = ((EmpresaCobrancaFaixa) colecaoEmpresaCobrancaFaixa.get(i + 1)).getNumeroMinimoContasFaixa() - 1;
								}
								
								qtdeContas = 0;
								
								qtdeClientes = 0;
								
								BigDecimal valorTotalDivida = new BigDecimal(0.0);
								
								iteratorColecaoDados = colecaoDados.iterator();
								
								while (iteratorColecaoDados.hasNext()) {
									Object[] dados = (Object[]) iteratorColecaoDados.next();
									
									if (dados[0] != null){
										Integer qnt = (Integer) dados[0];
										
										if (qnt >= numeroMinimoContas
											&& (numeroMaximoContas == null || qnt <= numeroMaximoContas)) {
										
											qtdeContas += qnt;
										
											if(dados[1] !=null ){
												qtdeClientes += (Integer) dados[1];
											}
											
											if(dados[2] !=null ){
												valorTotalDivida = valorTotalDivida.add((BigDecimal) dados[2]);
											}
										}
									}
									
								}
								
								if (i < (colecaoEmpresaCobrancaFaixa.size() - 1)) {
									colecaoFaixa.add(numeroMinimoContas + " a " + numeroMaximoContas);
								} else {
									colecaoFaixa.add("Mais de " + numeroMinimoContas);
								}
								colecaoQtdeContas.add(qtdeContas);
								colecaoQtdeClientes.add(qtdeClientes);
								colecaoValorTotalDivida.add(valorTotalDivida);
								
							}

							
							if (!colecaoQtdeContas.isEmpty()
									&& !colecaoQtdeClientes.isEmpty()
									&& !colecaoValorTotalDivida.isEmpty()) {
								
								informarContasEmCobrancaActionForm.setColecaoInformada("sim");
								this.getSessao(httpServletRequest).setAttribute("colecaoQuantidadeContas", true);
								this.getSessao(httpServletRequest).setAttribute("tamanho", colecaoFaixa.size());
								this.getSessao(httpServletRequest).setAttribute("colecaoFaixa", colecaoFaixa);
								this.getSessao(httpServletRequest).setAttribute("colecaoQtdeContas", colecaoQtdeContas);
								this.getSessao(httpServletRequest).setAttribute("colecaoQtdeClientes", colecaoQtdeClientes);
								this.getSessao(httpServletRequest).setAttribute("colecaoValorTotalDivida", colecaoValorTotalDivida);
								
							} else {
								
								informarContasEmCobrancaActionForm.setQtdContas("0");
								informarContasEmCobrancaActionForm.setQtdClientes("0");
								informarContasEmCobrancaActionForm.setValorTotalDivida(Util.formatarMoedaReal(BigDecimal.ZERO));

								informarContasEmCobrancaActionForm.setColecaoInformada(null);
								this.getSessao(httpServletRequest).removeAttribute("colecaoQuantidadeContas");
								this.getSessao(httpServletRequest).removeAttribute("colecaoFaixa");
								this.getSessao(httpServletRequest).removeAttribute("colecaoQtdeContas");
								this.getSessao(httpServletRequest).removeAttribute("colecaoQtdeClientes");
								this.getSessao(httpServletRequest).removeAttribute("colecaoValorTotalDivida");
								
							}
							
						} else {
							informarContasEmCobrancaActionForm.setQtdContas("0");
							informarContasEmCobrancaActionForm.setQtdClientes("0");
							informarContasEmCobrancaActionForm.setValorTotalDivida(Util.formatarMoedaReal(BigDecimal.ZERO));

							informarContasEmCobrancaActionForm.setColecaoInformada(null);
							this.getSessao(httpServletRequest).removeAttribute("colecaoQuantidadeContas");
							this.getSessao(httpServletRequest).removeAttribute("colecaoFaixa");
							this.getSessao(httpServletRequest).removeAttribute("colecaoQtdeContas");
							this.getSessao(httpServletRequest).removeAttribute("colecaoQtdeClientes");
							this.getSessao(httpServletRequest).removeAttribute("colecaoValorTotalDivida");
							
						}
						
					} else {
						informarContasEmCobrancaActionForm.setQtdContas("0");
						informarContasEmCobrancaActionForm.setQtdClientes("0");
						informarContasEmCobrancaActionForm.setValorTotalDivida(Util.formatarMoedaReal(BigDecimal.ZERO));

						informarContasEmCobrancaActionForm.setColecaoInformada(null);
						this.getSessao(httpServletRequest).removeAttribute("colecaoQuantidadeContas");
						this.getSessao(httpServletRequest).removeAttribute("colecaoFaixa");
						this.getSessao(httpServletRequest).removeAttribute("colecaoQtdeContas");
						this.getSessao(httpServletRequest).removeAttribute("colecaoQtdeClientes");
						this.getSessao(httpServletRequest).removeAttribute("colecaoValorTotalDivida");
						
					}
					
				} else {
					Collection colecaoDados = (Collection) this.getFachada().pesquisarQuantidadeContas(comandoEmpresaCobrancaContaHelper);

					informarContasEmCobrancaActionForm.setColecaoInformada(null);
					this.getSessao(httpServletRequest).removeAttribute("colecaoQuantidadeContas");
					this.getSessao(httpServletRequest).removeAttribute("colecaoFaixa");
					this.getSessao(httpServletRequest).removeAttribute("colecaoQtdeContas");
					this.getSessao(httpServletRequest).removeAttribute("colecaoQtdeClientes");
					this.getSessao(httpServletRequest).removeAttribute("colecaoValorTotalDivida");

					if (colecaoDados != null && !colecaoDados.isEmpty()) {
						Iterator iteratorColecaoDados = colecaoDados.iterator();

						Integer qtdeContas = 0;

						Integer qtdeClientes = 0;

						BigDecimal valorTotalDivida = new BigDecimal(0.0);

						while (iteratorColecaoDados.hasNext()) {
							Object[] dados = (Object[]) iteratorColecaoDados.next();

							if (dados != null) {

								if (dados[1] != null) {
									qtdeClientes += (Integer) dados[1];
								}

								if (dados[2] != null) {
									valorTotalDivida = valorTotalDivida.add((BigDecimal) dados[2]);
								}

								if (dados[0] != null) {
									qtdeContas += (Integer) dados[0];
								}

								/*
								 * if(dados[1] !=null ){ qtdeClientes =
								 * (Integer) dados[1]; }
								 * 
								 * if(dados[2] !=null ){ valorTotalDivida =
								 * (BigDecimal) dados[2]; }
								 */
							}
						}
						informarContasEmCobrancaActionForm.setQtdContas(qtdeContas.toString());
						informarContasEmCobrancaActionForm.setQtdClientes(qtdeClientes.toString());
						informarContasEmCobrancaActionForm.setValorTotalDivida(Util.formatarMoedaReal(valorTotalDivida));
					}else{
					informarContasEmCobrancaActionForm.setQtdContas("0");
					informarContasEmCobrancaActionForm.setQtdClientes("0");
					informarContasEmCobrancaActionForm.setValorTotalDivida(Util.formatarMoedaReal(BigDecimal.ZERO));
					}
				}
				informarContasEmCobrancaActionForm.setTotalSelecionado("sim");
				this.getSessao(httpServletRequest).setAttribute("habilitaCamposCiclo", true);
			} else {
				this.getSessao(httpServletRequest).removeAttribute("habilitaCamposCiclo");
				throw new ActionServletException(
						"atencao.filtro.nenhum_parametro_informado");
			}
			
		}

		return retorno;

	}

	@SuppressWarnings("unchecked")
	private void pesquisarCamposEnter(
			HttpServletRequest httpServletRequest,
			InformarContasEmCobrancaActionForm informarContasEmCobrancaActionForm,
			Fachada fachada) {
		String idEmpresa = informarContasEmCobrancaActionForm.getIdEmpresa();

		// Pesquisa a empresa
		if (idEmpresa != null && !idEmpresa.trim().equals("")) {

			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.ID, idEmpresa));

			Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
					Empresa.class.getName());

			if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {
				Empresa empresa = (Empresa) Util
						.retonarObjetoDeColecao(colecaoEmpresa);
				informarContasEmCobrancaActionForm.setIdEmpresa(empresa.getId()
						.toString());
				informarContasEmCobrancaActionForm.setNomeEmpresa(empresa
						.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "idEmpresa");
			} else {
				informarContasEmCobrancaActionForm.setIdEmpresa("");
				informarContasEmCobrancaActionForm
						.setNomeEmpresa("EMPRESA INEXISTENTE");

				httpServletRequest.setAttribute("empresaInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idEmpresa");
			}

		} else {
			informarContasEmCobrancaActionForm.setNomeEmpresa("");
		}

		String idImovel = informarContasEmCobrancaActionForm.getIdImovel();

		// Pesquisa o imóvel
		if (idImovel != null && !idImovel.trim().equals("")) {

			Imovel imovel = fachada.pesquisarImovelDigitado(new Integer(
					idImovel));

			if (imovel != null) {
				informarContasEmCobrancaActionForm.setIdImovel(imovel.getId()
						.toString());
				informarContasEmCobrancaActionForm.setInscricaoImovel(imovel
						.getInscricaoFormatada());
				httpServletRequest.setAttribute("nomeCampo",
						"referenciaInicial");
			} else {
				informarContasEmCobrancaActionForm.setIdImovel("");
				informarContasEmCobrancaActionForm
						.setInscricaoImovel("IMÓVEL INEXISTENTE");

				httpServletRequest.setAttribute("imovelInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idImovel");
			}

		} else {
			informarContasEmCobrancaActionForm.setInscricaoImovel("");
		}

		String idCliente = informarContasEmCobrancaActionForm.getIdCliente();

		// Pesquisa o cliente
		if (idCliente != null && !idCliente.trim().equals("")) {

			Cliente cliente = fachada.pesquisarClienteDigitado(new Integer(
					idCliente));

			if (cliente != null) {
				informarContasEmCobrancaActionForm.setIdCliente(cliente.getId()
						.toString());
				informarContasEmCobrancaActionForm.setNomeCliente(cliente
						.getNome());
				httpServletRequest.setAttribute("nomeCampo",
						"referenciaInicial");
			} else {
				informarContasEmCobrancaActionForm.setIdCliente("");
				informarContasEmCobrancaActionForm
						.setNomeCliente("CLIENTE INEXISTENTE");

				httpServletRequest.setAttribute("clienteInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idCliente");
			}

		} else {
			informarContasEmCobrancaActionForm.setNomeCliente("");
		}


		String idServicoTipo = informarContasEmCobrancaActionForm.getIdServicoTipo();

		// Pesquisa o Tipo de Serviço
		if (idServicoTipo != null && !idServicoTipo.trim().equals("")) {

			FiltroServicoTipo filtroServicoTipo  = new FiltroServicoTipo();
			filtroServicoTipo.adicionarParametro(new ParametroSimples(
					FiltroServicoTipo.ID, idServicoTipo));
			
			Collection colecaoServicoTipo = fachada.pesquisar(filtroServicoTipo, ServicoTipo.class.getName());
			
			if (colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty()) {
				ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(colecaoServicoTipo);
				
				informarContasEmCobrancaActionForm.setIdServicoTipo(servicoTipo.getId()
						.toString());
				informarContasEmCobrancaActionForm.setDescricaoServicoTipo(servicoTipo
						.getDescricao());
				httpServletRequest.setAttribute("nomeCampo",
						"idLocalidadeOrigem");
				httpServletRequest.setAttribute("idServicoTipoEncontrada", true);
			} else {
				informarContasEmCobrancaActionForm.setIdServicoTipo("");
				informarContasEmCobrancaActionForm
						.setDescricaoServicoTipo("TIPO DE SERVICO INEXISTENTE");

				httpServletRequest.removeAttribute("idServicoTipoEncontrada");
				httpServletRequest.setAttribute("nomeCampo", "idServicoTipo");
			}

		} else {
			informarContasEmCobrancaActionForm.setNomeCliente("");
		}
		
		String idLocalidadeOrigem = informarContasEmCobrancaActionForm
				.getIdLocalidadeOrigem();

		// Pesquisa a localidade inicial
		if (idLocalidadeOrigem != null
		    && !idLocalidadeOrigem.trim().equals("")
		    && httpServletRequest.getParameter("tipoPesquisa") != null
			&& (httpServletRequest.getParameter("tipoPesquisa").equals("localidadeOrigem")
					|| httpServletRequest.getParameter("tipoPesquisa").equals("setorComercialOrigem")
					|| httpServletRequest.getParameter("tipoPesquisa").equals("quadraInicial"))) {

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeOrigem));

			Collection<Localidade> colecaoLocalidade = fachada.pesquisar(
					filtroLocalidade, Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Localidade localidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidade);

				informarContasEmCobrancaActionForm
						.setIdLocalidadeOrigem(localidade.getId().toString());
				informarContasEmCobrancaActionForm
						.setNomeLocalidadeOrigem(localidade.getDescricao());
				httpServletRequest.setAttribute("nomeCampo",
						"codigoSetorComercialOrigem");
				
				if (httpServletRequest.getParameter("tipoPesquisa").equals("localidadeOrigem")) {
				
					informarContasEmCobrancaActionForm
						.setIdLocalidadeDestino(localidade.getId().toString());
					
					informarContasEmCobrancaActionForm
							.setNomeLocalidadeDestino(localidade.getDescricao());
					httpServletRequest.setAttribute("nomeCampo",
							"codigoSetorComercialDestino");
					
				}

				String codigoSetorComercialOrigem = informarContasEmCobrancaActionForm
						.getCodigoSetorComercialOrigem();

				// Pesquisa o setor comercial inicial
				if (codigoSetorComercialOrigem != null
						&& !codigoSetorComercialOrigem.trim().equals("")) {

					FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
					filtroSetorComercial
							.adicionarParametro(new ParametroSimples(
									FiltroSetorComercial.ID_LOCALIDADE,
									localidade.getId()));
					filtroSetorComercial
							.adicionarParametro(new ParametroSimples(
									FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
									codigoSetorComercialOrigem));

					Collection<SetorComercial> colecaoSetorComercial = fachada
							.pesquisar(filtroSetorComercial,
									SetorComercial.class.getName());

					if (colecaoSetorComercial != null
							&& !colecaoSetorComercial.isEmpty()) {
						SetorComercial setorComercial = (SetorComercial) Util
								.retonarObjetoDeColecao(colecaoSetorComercial);

						informarContasEmCobrancaActionForm
								.setIdSetorComercialOrigem(""
										+ setorComercial.getId());
						informarContasEmCobrancaActionForm
								.setCodigoSetorComercialOrigem(""
										+ setorComercial.getCodigo());
						informarContasEmCobrancaActionForm
								.setDescricaoSetorComercialOrigem(setorComercial
										.getDescricao());
						httpServletRequest.setAttribute("nomeCampo",
								"idLocalidadeDestino");
						
						if (httpServletRequest.getParameter("tipoPesquisa").equals("setorComercialOrigem")) {

							informarContasEmCobrancaActionForm
								.setIdSetorComercialDestino(""
										+ setorComercial.getId());
							informarContasEmCobrancaActionForm
								.setCodigoSetorComercialDestino(""
										+ setorComercial.getCodigo());
							informarContasEmCobrancaActionForm
									.setDescricaoSetorComercialDestino(setorComercial
											.getDescricao());
							httpServletRequest.setAttribute("nomeCampo",
									"idLocalidadeDestino");
							
						}

						String codigoQuadraInicial = informarContasEmCobrancaActionForm
								.getCodigoQuadraInicial();
						
						// Pesquisa a quadra inicial
						if (codigoQuadraInicial != null
								&& !codigoQuadraInicial.trim().equals("")) {
							
							FiltroQuadra filtroQuadra = new FiltroQuadra();
							filtroQuadra
									.adicionarParametro(new ParametroSimples(
											FiltroQuadra.ID_SETORCOMERCIAL,
											setorComercial.getId()));
							filtroQuadra
									.adicionarParametro(new ParametroSimples(
											FiltroQuadra.NUMERO_QUADRA,
											codigoQuadraInicial));

							Collection<Quadra> colecaoQuadra = fachada
									.pesquisar(filtroQuadra,
											Quadra.class.getName());
							if (colecaoQuadra != null
									&& !colecaoQuadra.isEmpty()) {
								Quadra quadra = (Quadra) Util
										.retonarObjetoDeColecao(colecaoQuadra);

								informarContasEmCobrancaActionForm
										.setCodigoQuadraInicial(""
												+ quadra.getNumeroQuadra());
								informarContasEmCobrancaActionForm
										.setDescricaoQuadraInicial(quadra
												.getDescricao());
								httpServletRequest.setAttribute("nomeCampo",
										"codigoQuadraInicial");
								
								if (httpServletRequest.getParameter("tipoPesquisa").equals("quadraInicial")) {
									informarContasEmCobrancaActionForm
										.setCodigoQuadraFinal(""
												+ quadra.getNumeroQuadra());
									informarContasEmCobrancaActionForm
											.setDescricaoQuadraFinal(quadra
													.getDescricao());
									httpServletRequest.setAttribute("nomeCampo",
											"codigoQuadraInicial");
								}


							} else {
								informarContasEmCobrancaActionForm
										.setCodigoQuadraInicial("");
								informarContasEmCobrancaActionForm
										.setDescricaoQuadraInicial("QUADRA INEXISTENTE");
		
								httpServletRequest.setAttribute(
										"quadraInicialInexistente", true);
								httpServletRequest.setAttribute("nomeCampo",
										"codigoQuadraInicial");
							}
							
						}
						
					} else {
						informarContasEmCobrancaActionForm
							.setIdSetorComercialOrigem("");
						informarContasEmCobrancaActionForm
							.setCodigoSetorComercialOrigem("");
						informarContasEmCobrancaActionForm
								.setDescricaoSetorComercialOrigem("SETOR COMERCIAL INEXISTENTE");

						httpServletRequest.setAttribute(
								"setorComercialOrigemInexistente", true);
						httpServletRequest.setAttribute("nomeCampo",
								"codigoSetorComercialOrigem");
					}

				}

			} else {
				informarContasEmCobrancaActionForm.setIdLocalidadeOrigem("");
				informarContasEmCobrancaActionForm
						.setNomeLocalidadeOrigem("LOCALIDADE INEXISTENTE");

				httpServletRequest.setAttribute("localidadeOrigemInexistente",
						true);
				httpServletRequest.setAttribute("nomeCampo",
						"idLocalidadeOrigem");
			}

		}

		String idLocalidadeDestino = informarContasEmCobrancaActionForm
				.getIdLocalidadeDestino();

		// Pesquisa a localidade final
		if (idLocalidadeDestino != null
				&& !idLocalidadeDestino.trim().equals("")
				&& httpServletRequest.getParameter("tipoPesquisa") != null
				&& (httpServletRequest.getParameter("tipoPesquisa").equals("localidadeDestino")
						|| httpServletRequest.getParameter("tipoPesquisa").equals("setorComercialDestino")
						|| httpServletRequest.getParameter("tipoPesquisa").equals("quadraFinal"))) {

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeDestino));

			Collection<Localidade> colecaoLocalidade = fachada.pesquisar(
					filtroLocalidade, Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Localidade localidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidade);

				informarContasEmCobrancaActionForm
						.setIdLocalidadeDestino(localidade.getId().toString());
				informarContasEmCobrancaActionForm
						.setNomeLocalidadeDestino(localidade.getDescricao());
				httpServletRequest.setAttribute("nomeCampo",
						"codigoSetorComercialDestino");

				String codigoSetorComercialDestino = informarContasEmCobrancaActionForm
						.getCodigoSetorComercialDestino();

				// Pesquisa o setor comercial inicial
				if (codigoSetorComercialDestino != null
						&& !codigoSetorComercialDestino.trim().equals("")) {

					FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
					filtroSetorComercial
							.adicionarParametro(new ParametroSimples(
									FiltroSetorComercial.ID_LOCALIDADE,
									localidade.getId()));
					filtroSetorComercial
							.adicionarParametro(new ParametroSimples(
									FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
									codigoSetorComercialDestino));

					Collection<SetorComercial> colecaoSetorComercial = fachada
							.pesquisar(filtroSetorComercial,
									SetorComercial.class.getName());

					if (colecaoSetorComercial != null
							&& !colecaoSetorComercial.isEmpty()) {
						SetorComercial setorComercial = (SetorComercial) Util
								.retonarObjetoDeColecao(colecaoSetorComercial);

						informarContasEmCobrancaActionForm
								.setIdSetorComercialDestino(""
										+ setorComercial.getId());
						informarContasEmCobrancaActionForm
								.setCodigoSetorComercialDestino(""
										+ setorComercial.getCodigo());
						informarContasEmCobrancaActionForm
								.setDescricaoSetorComercialDestino(setorComercial
										.getDescricao());
						httpServletRequest.setAttribute("nomeCampo",
								"referenciaInicial");
						
						String codigoQuadraFinal = informarContasEmCobrancaActionForm
							.getCodigoQuadraFinal();

						// Pesquisa a quadra final
						if (codigoQuadraFinal != null
								&& !codigoQuadraFinal.trim().equals("")) {

							if (informarContasEmCobrancaActionForm.getCodigoQuadraInicial() != null
									&& !informarContasEmCobrancaActionForm.getCodigoQuadraInicial().trim().equals("")) {
								Integer codQuadraFinal = new Integer(codigoQuadraFinal);
								Integer codQuadraInicial = new Integer(informarContasEmCobrancaActionForm.getCodigoQuadraInicial());
								
								if (codQuadraFinal.compareTo(codQuadraInicial) < 0) {
									throw new ActionServletException("atencao.quadraInicial.maior.que.quadraFinal");
								}
							}
							
							FiltroQuadra filtroQuadra = new FiltroQuadra();
							filtroQuadra
									.adicionarParametro(new ParametroSimples(
											FiltroQuadra.ID_SETORCOMERCIAL,
											setorComercial.getId()));
							filtroQuadra
									.adicionarParametro(new ParametroSimples(
											FiltroQuadra.NUMERO_QUADRA,
											codigoQuadraFinal));

							Collection<Quadra> colecaoQuadra = fachada
									.pesquisar(filtroQuadra,
											Quadra.class.getName());
							
							if (colecaoQuadra != null
									&& !colecaoQuadra.isEmpty()) {
								Quadra quadra = (Quadra) Util
										.retonarObjetoDeColecao(colecaoQuadra);

								informarContasEmCobrancaActionForm
										.setCodigoQuadraFinal(""
												+ quadra.getNumeroQuadra());
								informarContasEmCobrancaActionForm
										.setDescricaoQuadraFinal(quadra
												.getDescricao());
								httpServletRequest.setAttribute("nomeCampo",
										"codigoQuadraFinal");
								
							} else {
								informarContasEmCobrancaActionForm
										.setCodigoQuadraFinal("");
								informarContasEmCobrancaActionForm
										.setDescricaoQuadraFinal("QUADRA INEXISTENTE");
		
								httpServletRequest.setAttribute(
										"quadraFinalInexistente", true);
								httpServletRequest.setAttribute("nomeCampo",
										"codigoQuadraFinal");
							}
							
						}
						
					} else {
						informarContasEmCobrancaActionForm
							.setIdSetorComercialDestino("");
						informarContasEmCobrancaActionForm
							.setCodigoSetorComercialDestino("");
						informarContasEmCobrancaActionForm
								.setDescricaoSetorComercialDestino("SETOR COMERCIAL INEXISTENTE");

						httpServletRequest.setAttribute(
								"setorComercialDestinoInexistente", true);
						httpServletRequest.setAttribute("nomeCampo",
								"codigoSetorComercialDestino");
					}

				}

			} else {
				informarContasEmCobrancaActionForm.setIdLocalidadeDestino("");
				informarContasEmCobrancaActionForm
						.setNomeLocalidadeDestino("LOCALIDADE INEXISTENTE");

				httpServletRequest.setAttribute("localidadeDestinoInexistente",
						true);
				httpServletRequest.setAttribute("nomeCampo",
						"idLocalidadeDestino");
			}

		}
	}

	private ComandoEmpresaCobrancaContaHelper retornaQtdContas(InformarContasEmCobrancaActionForm informarContasEmCobrancaActionForm) {

		ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta = new ComandoEmpresaCobrancaConta();
		
		comandoEmpresaCobrancaConta.setIndicadorResidencial(ConstantesSistema.NAO.intValue());
		comandoEmpresaCobrancaConta.setIndicadorComercial(ConstantesSistema.NAO.intValue());
		comandoEmpresaCobrancaConta.setIndicadorIndustrial(ConstantesSistema.NAO.intValue());
		comandoEmpresaCobrancaConta.setIndicadorPublico(ConstantesSistema.NAO.intValue());

		boolean algumParametroInformado = false;
		
		// Imovel
		if (informarContasEmCobrancaActionForm.getIdImovel() != null && 
			!informarContasEmCobrancaActionForm.getIdImovel().equals("")) {
			
			algumParametroInformado = true;

			FiltroImovel filtroImovel = new FiltroImovel();

			filtroImovel.adicionarParametro(
				new ParametroSimples(
					FiltroImovel.ID, 
					informarContasEmCobrancaActionForm.getIdImovel()));

			Collection colecaoImovel = this.getFachada().pesquisar(filtroImovel,Imovel.class.getName());

			if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

				Imovel imovel = (Imovel) colecaoImovel.iterator().next();
				imovel.setId(new Integer(informarContasEmCobrancaActionForm.getIdImovel()));

				comandoEmpresaCobrancaConta.setImovel(imovel);

			} else {
				throw new ActionServletException("atencao.imovel.inexistente");
			}

		}

		// Cliente
		if (informarContasEmCobrancaActionForm.getIdCliente() != null && 
			!informarContasEmCobrancaActionForm.getIdCliente().equals("")) {
			
			algumParametroInformado = true;

			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(
				new ParametroSimples(
					FiltroCliente.ID, 
					informarContasEmCobrancaActionForm.getIdCliente()));

			Collection colecaoCliente = 
				this.getFachada().pesquisar(filtroCliente,Cliente.class.getName());

			if (colecaoCliente != null && !colecaoCliente.isEmpty()) {

				Cliente cliente = (Cliente) colecaoCliente.iterator().next();
				cliente.setId(new Integer(informarContasEmCobrancaActionForm.getIdCliente()));

				comandoEmpresaCobrancaConta.setCliente(cliente);
			} else {
				throw new ActionServletException("atencao.cliente.inexistente");
			}

		}
		// Localidade Inicial
		if (informarContasEmCobrancaActionForm.getIdLocalidadeOrigem() != null && 
			!informarContasEmCobrancaActionForm.getIdLocalidadeOrigem().equals("")) {
			
			algumParametroInformado = true;

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(
				new ParametroSimples(
					FiltroLocalidade.ID, 
					informarContasEmCobrancaActionForm.getIdLocalidadeOrigem()));

			Collection colecaoLocalidade = 
				this.getFachada().pesquisar(filtroLocalidade,Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

				Localidade localidadeInicial = new Localidade();

				localidadeInicial.setId(
					new Integer(informarContasEmCobrancaActionForm.getIdLocalidadeOrigem()));

				comandoEmpresaCobrancaConta.setLocalidadeInicial(localidadeInicial);
			} else {

				throw new ActionServletException(
						"atencao.pesquisa.localidade_inicial_inexistente");

			}
		}

		// Localidade Final
		if (informarContasEmCobrancaActionForm.getIdLocalidadeDestino() != null && 
			!informarContasEmCobrancaActionForm.getIdLocalidadeDestino().equals("")) {
			
			algumParametroInformado = true;

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(
				new ParametroSimples(
					FiltroLocalidade.ID, 
					informarContasEmCobrancaActionForm.getIdLocalidadeDestino()));

			Collection colecaoLocalidade = 
				this.getFachada().pesquisar(filtroLocalidade,Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

				Localidade localidadeFinal = new Localidade();

				localidadeFinal.setId(
					new Integer(informarContasEmCobrancaActionForm.getIdLocalidadeDestino()));

				comandoEmpresaCobrancaConta.setLocalidadeFinal(localidadeFinal);
			} else {

				throw new ActionServletException("atencao.pesquisa.localidade_final_inexistente");

			}

		}

		// Setor Comercial Inicial
		if (informarContasEmCobrancaActionForm.getCodigoSetorComercialOrigem() != null
				&& !informarContasEmCobrancaActionForm
						.getCodigoSetorComercialOrigem().equals("")) {
			
			algumParametroInformado = true;

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

			filtroSetorComercial
					.adicionarParametro(new ParametroSimples(
							FiltroSetorComercial.ID_LOCALIDADE,
							informarContasEmCobrancaActionForm
									.getIdLocalidadeOrigem()));

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
					informarContasEmCobrancaActionForm
							.getCodigoSetorComercialOrigem()));

			Collection colecaoSetorComercial = this.getFachada().pesquisar(
					filtroSetorComercial, SetorComercial.class.getName());

			if (colecaoSetorComercial != null
					&& !colecaoSetorComercial.isEmpty()) {

				SetorComercial setorComercialInicial = (SetorComercial) colecaoSetorComercial
						.iterator().next();

				setorComercialInicial.setCodigo(new Integer(
						informarContasEmCobrancaActionForm
								.getCodigoSetorComercialOrigem()));

				comandoEmpresaCobrancaConta
						.setCodigoSetorComercialInicial(setorComercialInicial
								.getCodigo());
			} else {

				throw new ActionServletException(
						"atencao.pesquisa.setor_inicial_inexistente");

			}

		}

		// Setor Comercial Final
		if (informarContasEmCobrancaActionForm.getCodigoSetorComercialDestino() != null
				&& !informarContasEmCobrancaActionForm
						.getCodigoSetorComercialDestino().equals("")) {
			
			algumParametroInformado = true;

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

			filtroSetorComercial
					.adicionarParametro(new ParametroSimples(
							FiltroSetorComercial.ID_LOCALIDADE,
							informarContasEmCobrancaActionForm
									.getIdLocalidadeDestino()));

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
					informarContasEmCobrancaActionForm
							.getCodigoSetorComercialDestino()));

			Collection colecaoSetorComercial = this.getFachada().pesquisar(
					filtroSetorComercial, SetorComercial.class.getName());

			if (colecaoSetorComercial != null
					&& !colecaoSetorComercial.isEmpty()) {

				SetorComercial setorComercialFinal = (SetorComercial) colecaoSetorComercial
						.iterator().next();

				setorComercialFinal.setCodigo(new Integer(
						informarContasEmCobrancaActionForm
								.getCodigoSetorComercialDestino()));

				comandoEmpresaCobrancaConta
						.setCodigoSetorComercialFinal(setorComercialFinal
								.getCodigo());
			} else {

				throw new ActionServletException(
						"atencao.pesquisa.setor_final_inexistente");

			}

		}

		// Quadra Inicial
		if (informarContasEmCobrancaActionForm.getCodigoQuadraInicial() != null
				&& !informarContasEmCobrancaActionForm
						.getCodigoQuadraInicial().equals("")) {
			
			algumParametroInformado = true;

			FiltroQuadra filtroQuadra = new FiltroQuadra();

			filtroQuadra
					.adicionarParametro(new ParametroSimples(
							FiltroQuadra.ID_SETORCOMERCIAL,
							informarContasEmCobrancaActionForm
									.getIdSetorComercialOrigem()));

			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.NUMERO_QUADRA,
					informarContasEmCobrancaActionForm
							.getCodigoQuadraInicial()));

			Collection colecaoQuadra = this.getFachada().pesquisar(
					filtroQuadra, Quadra.class.getName());

			if (colecaoQuadra != null
					&& !colecaoQuadra.isEmpty()) {

				Quadra quadraInicial = (Quadra) colecaoQuadra
						.iterator().next();

				quadraInicial.setNumeroQuadra(new Integer(
						informarContasEmCobrancaActionForm
								.getCodigoQuadraInicial()));

				comandoEmpresaCobrancaConta
						.setQuadraInicial(quadraInicial);
			} else {

				throw new ActionServletException(
						"atencao.pesquisa.quadra_inicial_inexistente");

			}

		}


		// Quadra Final
		if (informarContasEmCobrancaActionForm.getCodigoQuadraInicial() != null
				&& !informarContasEmCobrancaActionForm
						.getCodigoQuadraInicial().equals("")) {
			
			algumParametroInformado = true;

			FiltroQuadra filtroQuadra = new FiltroQuadra();

			filtroQuadra
					.adicionarParametro(new ParametroSimples(
							FiltroQuadra.ID_SETORCOMERCIAL,
							informarContasEmCobrancaActionForm
									.getIdSetorComercialDestino()));

			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.NUMERO_QUADRA,
					informarContasEmCobrancaActionForm
							.getCodigoQuadraFinal()));

			Collection colecaoQuadra = this.getFachada().pesquisar(
					filtroQuadra, Quadra.class.getName());

			if (colecaoQuadra != null
					&& !colecaoQuadra.isEmpty()) {

				Quadra quadra = (Quadra) colecaoQuadra
						.iterator().next();

				quadra.setNumeroQuadra(new Integer(
						informarContasEmCobrancaActionForm
								.getCodigoQuadraFinal()));

				comandoEmpresaCobrancaConta
						.setQuadraFinal(quadra);
			} else {

				throw new ActionServletException(
						"atencao.pesquisa.quadra_final_inexistente");

			}

		}
		
		if (informarContasEmCobrancaActionForm.getIdsCategoria() != null) {
			
			String[] idsCategoria = informarContasEmCobrancaActionForm.getIdsCategoria();
			
			for (int i = 0; i < idsCategoria.length; i++) {
				
				if (idsCategoria[i].equals(Categoria.COMERCIAL.toString())) {
					comandoEmpresaCobrancaConta.setIndicadorComercial(ConstantesSistema.SIM.intValue());
				} else if (idsCategoria[i].equals(Categoria.INDUSTRIAL.toString())) {
					comandoEmpresaCobrancaConta.setIndicadorIndustrial(ConstantesSistema.SIM.intValue());
				} else if (idsCategoria[i].equals(Categoria.RESIDENCIAL.toString())) {
					comandoEmpresaCobrancaConta.setIndicadorResidencial(ConstantesSistema.SIM.intValue());
				} else if (idsCategoria[i].equals(Categoria.PUBLICO.toString())) {
					comandoEmpresaCobrancaConta.setIndicadorPublico(ConstantesSistema.SIM.intValue());
				}
				
			}
		}
		
		Collection colecaoUnidadeNegocio = null;
		// Unidade Negocio
		if (informarContasEmCobrancaActionForm.getIdsUnidadeNegocio() != null
				&& informarContasEmCobrancaActionForm.getIdsUnidadeNegocio().length > 0) {
			
			String[] idsUnidadeNegocio = informarContasEmCobrancaActionForm.getIdsUnidadeNegocio();
			Collection<String> colecaoIdsUnidadeNegocio = new ArrayList();
			boolean unidadeInformada = true;
			
			for (int i = 0; i < idsUnidadeNegocio.length; i++) {
				if (idsUnidadeNegocio[i].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					unidadeInformada = false;
					break;
				}
				colecaoIdsUnidadeNegocio.add(idsUnidadeNegocio[i]);
			}
			
			if (unidadeInformada) {
				algumParametroInformado = true;

				FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
	
				filtroUnidadeNegocio.adicionarParametro(new ParametroSimplesIn(
						FiltroUnidadeNegocio.ID, colecaoIdsUnidadeNegocio));
	
				colecaoUnidadeNegocio = this.getFachada().pesquisar(
						filtroUnidadeNegocio, UnidadeNegocio.class.getName());
	
				if (colecaoUnidadeNegocio != null
						&& !colecaoUnidadeNegocio.isEmpty()) {
	
					if (colecaoUnidadeNegocio.size() == 1) {
						UnidadeNegocio unidadeNegocio = (UnidadeNegocio) colecaoUnidadeNegocio
								.iterator().next();
		
						comandoEmpresaCobrancaConta.setUnidadeNegocio(unidadeNegocio);
					}
	
				} else {
					throw new ActionServletException(
							"atencao.unidade_negocio.inexistente");
				}
			}
		}

		Collection colecaoGerenciaRegional = null;
		// Gerência Regional
		if (informarContasEmCobrancaActionForm.getIdsGerenciaRegional() != null
				&& informarContasEmCobrancaActionForm.getIdsGerenciaRegional().length > 0) {
			
			String[] idsGerenciaRegional = informarContasEmCobrancaActionForm.getIdsGerenciaRegional();
			Collection<String> colecaoIdsGerenciaRegional = new ArrayList();
			boolean gerenciaRegionalInformada = true;
			
			for (int i = 0; i < idsGerenciaRegional.length; i++) {
				if (idsGerenciaRegional[i].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					gerenciaRegionalInformada = false;
					break;
				}
				colecaoIdsGerenciaRegional.add(idsGerenciaRegional[i]);
			}
			
			if (gerenciaRegionalInformada) {
				algumParametroInformado = true;

				FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
	
				filtroGerenciaRegional.adicionarParametro(new ParametroSimplesIn(
						FiltroGerenciaRegional.ID, colecaoIdsGerenciaRegional));
	
				colecaoGerenciaRegional = this.getFachada().pesquisar(
						filtroGerenciaRegional, GerenciaRegional.class.getName());
	
				if (colecaoGerenciaRegional != null
						&& !colecaoGerenciaRegional.isEmpty()) {
	
					if (colecaoGerenciaRegional.size() == 1) {
						GerenciaRegional gerenciaRegional = (GerenciaRegional) colecaoGerenciaRegional
								.iterator().next();
		
						comandoEmpresaCobrancaConta.setGerenciaRegional(gerenciaRegional);
					}
	
				} else {
					throw new ActionServletException(
							"atencao.unidade_negocio.inexistente");
				}
			}
		}

		Collection colecaoImovelPerfil = null;
		// Imovel Perfil
		if (informarContasEmCobrancaActionForm.getIdsImovelPerfil() != null
				&& informarContasEmCobrancaActionForm.getIdsImovelPerfil().length > 0) {
			
			String[] idsImovelPerfil = informarContasEmCobrancaActionForm.getIdsImovelPerfil();
			Collection<String> colecaoIdsImovelPerfil = new ArrayList();
			boolean imovelPerfilInformada = true;
			
			for (int i = 0; i < idsImovelPerfil.length; i++) {
				if (idsImovelPerfil[i].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					imovelPerfilInformada = false;
					break;
				}
				colecaoIdsImovelPerfil.add(idsImovelPerfil[i]);
			}
			
			if (imovelPerfilInformada) {
				algumParametroInformado = true;

				FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
	
				filtroImovelPerfil.adicionarParametro(new ParametroSimplesIn(
						FiltroImovelPerfil.ID, colecaoIdsImovelPerfil));
	
				colecaoImovelPerfil = this.getFachada().pesquisar(
						filtroImovelPerfil, ImovelPerfil.class.getName());
	
				if (colecaoImovelPerfil != null
						&& !colecaoImovelPerfil.isEmpty()) {
	
					if (colecaoImovelPerfil.size() == 1) {
						ImovelPerfil imovelPerfil = (ImovelPerfil) colecaoImovelPerfil
								.iterator().next();
		
						comandoEmpresaCobrancaConta.setImovelPerfil(imovelPerfil);
					}
	
				} else {
					throw new ActionServletException(
							"atencao.unidade_negocio.inexistente");
				}
			}
		}
		
		Collection colecaoLigacaoAguaSituacao = null;
		// LigacaoAguaSituacao
		if (informarContasEmCobrancaActionForm.getIdsLigacaoAguaSituacao() != null
				&& informarContasEmCobrancaActionForm.getIdsLigacaoAguaSituacao().length > 0) {
			
			String[] idsLigacaoAguaSituacao = informarContasEmCobrancaActionForm.getIdsLigacaoAguaSituacao();
			Collection<String> colecaoIdsLigacaoAguaSituacao = new ArrayList();
			boolean ligacaoAguaSituacaoInformada = true;
			
			for (int i = 0; i < idsLigacaoAguaSituacao.length; i++) {
				if (idsLigacaoAguaSituacao[i].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					ligacaoAguaSituacaoInformada = false;
					break;
				}
				colecaoIdsLigacaoAguaSituacao.add(idsLigacaoAguaSituacao[i]);
			}
			
			if (ligacaoAguaSituacaoInformada) {
				algumParametroInformado = true;

				FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
	
				filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimplesIn(
						FiltroLigacaoAguaSituacao.ID, colecaoIdsLigacaoAguaSituacao));
	
				colecaoLigacaoAguaSituacao = this.getFachada().pesquisar(
						filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
	
				if (colecaoLigacaoAguaSituacao != null
						&& !colecaoLigacaoAguaSituacao.isEmpty()) {
	
					if (colecaoLigacaoAguaSituacao.size() == 1) {
						LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) colecaoLigacaoAguaSituacao
								.iterator().next();
		
						comandoEmpresaCobrancaConta.setLigacaoAguaSituacao(ligacaoAguaSituacao);
					}
	
				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", "Situação da Ligação de Água");
				}
			}
		}
		
		// Data Vencimento Inicial
		if (informarContasEmCobrancaActionForm.getDataVencimentoInicial() != null
				&& !informarContasEmCobrancaActionForm
						.getDataVencimentoInicial().equals("")) {
			
			algumParametroInformado = true;
			
			comandoEmpresaCobrancaConta.setDataVencimentoContaInicial(Util
					.converteStringParaDate(informarContasEmCobrancaActionForm
							.getDataVencimentoInicial()));
		}

		// Data Vencimento Final
		if (informarContasEmCobrancaActionForm.getDataVencimentoFinal() != null
				&& !informarContasEmCobrancaActionForm.getDataVencimentoFinal()
						.equals("")) {
			
			algumParametroInformado = true;
			
			comandoEmpresaCobrancaConta.setDataVencimentoContaFinal(Util
					.converteStringParaDate(informarContasEmCobrancaActionForm
							.getDataVencimentoFinal()));
		}

		// Referencia Inicial
		if (informarContasEmCobrancaActionForm.getReferenciaInicial() != null
				&& !informarContasEmCobrancaActionForm.getReferenciaInicial()
						.equals("")) {
			
			algumParametroInformado = true;
			
			comandoEmpresaCobrancaConta
					.setReferenciaContaInicial(Util
							.formatarMesAnoComBarraParaAnoMes(informarContasEmCobrancaActionForm
									.getReferenciaInicial()));
		}else{
			Integer referenciaInicialFormatada = 198001;
			comandoEmpresaCobrancaConta
			 .setReferenciaContaInicial(referenciaInicialFormatada);
		}

		// Referencia Final
		if (informarContasEmCobrancaActionForm.getReferenciaFinal() != null
				&& !informarContasEmCobrancaActionForm.getReferenciaFinal()
						.equals("")) {
			
			algumParametroInformado = true;
			
			comandoEmpresaCobrancaConta
			.setReferenciaContaFinal(Util
					.formatarMesAnoComBarraParaAnoMes(informarContasEmCobrancaActionForm
							.getReferenciaFinal()));
		}else{
			SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
			comandoEmpresaCobrancaConta
			.setReferenciaContaFinal(sistemaParametro.getAnoMesArrecadacao());
		}

		// Valor Conta inicial
		if (informarContasEmCobrancaActionForm.getValorMinimo() != null
				&& !informarContasEmCobrancaActionForm.getValorMinimo().equals(
						"")) {
			
			algumParametroInformado = true;
			
			comandoEmpresaCobrancaConta
					.setValorMinimoConta(Util
							.formatarMoedaRealparaBigDecimal(informarContasEmCobrancaActionForm
									.getValorMinimo()));
		}

		// Valor Conta Final
		if (informarContasEmCobrancaActionForm.getValorMaximo() != null
				&& !informarContasEmCobrancaActionForm.getValorMaximo().equals(
						"")) {
			
			algumParametroInformado = true;
			
			comandoEmpresaCobrancaConta
					.setValorMaximoConta(Util
							.formatarMoedaRealparaBigDecimal(informarContasEmCobrancaActionForm
									.getValorMaximo()));
		}

		// Quantidade de Contas inicial
		if (informarContasEmCobrancaActionForm.getQuantidadeContasInicial() != null
				&& !informarContasEmCobrancaActionForm.getQuantidadeContasInicial().equals(
						"")) {
			
			algumParametroInformado = true;
			
			comandoEmpresaCobrancaConta
					.setQtdContasInicial(new Integer(informarContasEmCobrancaActionForm
									.getQuantidadeContasInicial()));
		}

		// Quantidade de Contas Final
		if (informarContasEmCobrancaActionForm.getQuantidadeContasFinal() != null
				&& !informarContasEmCobrancaActionForm.getQuantidadeContasFinal().equals(
						"")) {
			
			algumParametroInformado = true;
			
			comandoEmpresaCobrancaConta
					.setQtdContasFinal(new Integer(informarContasEmCobrancaActionForm
									.getQuantidadeContasFinal()));
		}
		
		if (comandoEmpresaCobrancaConta.getQtdContasInicial() != null
				&& comandoEmpresaCobrancaConta.getQtdContasFinal() == null) {

			throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio", "Quantidade de Contas Final");
			
		}
		
		if (comandoEmpresaCobrancaConta.getQtdContasInicial() == null
				&& comandoEmpresaCobrancaConta.getQtdContasFinal() != null) {

			throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio", "Quantidade de Contas Inicial");
			
		}
		
		// [FS0017] ? Verificar quantidade de contas final menor que quantidade inicial
		if (comandoEmpresaCobrancaConta.getQtdContasInicial() != null
				&& comandoEmpresaCobrancaConta.getQtdContasFinal() != null
				&& comandoEmpresaCobrancaConta.getQtdContasFinal()
					.compareTo(comandoEmpresaCobrancaConta.getQtdContasInicial()) < 0) {

			throw new ActionServletException(
					"atencao.quantidade.contas_final.menor.quantidade_inicial");
			
		}

		// Quantidade de Dias de Vencimento
		if (informarContasEmCobrancaActionForm.getQuantidadeDiasVencimento() != null
				&& !informarContasEmCobrancaActionForm.getQuantidadeDiasVencimento().equals(
						"")) {
			
			algumParametroInformado = true;
			
			comandoEmpresaCobrancaConta
					.setQtdDiasVencimento(new Integer(informarContasEmCobrancaActionForm
									.getQuantidadeDiasVencimento()));
		}
		
		if (algumParametroInformado) {
			ComandoEmpresaCobrancaContaHelper comandoEmpresaCobrancaContaHelper = new ComandoEmpresaCobrancaContaHelper();
			comandoEmpresaCobrancaContaHelper.setComandoEmpresaCobrancaConta(comandoEmpresaCobrancaConta);
			comandoEmpresaCobrancaContaHelper.setColecaoUnidadeNegocio(colecaoUnidadeNegocio);
			comandoEmpresaCobrancaContaHelper.setColecaoGerenciaRegional(colecaoGerenciaRegional);
			comandoEmpresaCobrancaContaHelper.setColecaoImovelPerfil(colecaoImovelPerfil);
			comandoEmpresaCobrancaContaHelper.setColecaoLigacaoAguaSituacao(colecaoLigacaoAguaSituacao);
			
			return comandoEmpresaCobrancaContaHelper;
		} else {
			return null;
		}

	}
}
