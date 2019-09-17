package gcom.atualizacaocadastral;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.FiltroOrdemServico;
import gcom.atendimentopublico.ordemservico.FiltroOrdemServicoUnidade;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoUnidade;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.FiltroTramite;
import gcom.atendimentopublico.registroatendimento.RABuilder;
import gcom.atendimentopublico.registroatendimento.RADadosGeraisHelper;
import gcom.atendimentopublico.registroatendimento.RALocalOcorrenciaHelper;
import gcom.atendimentopublico.registroatendimento.RASolicitanteHelper;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.Tramite;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.RepositorioCadastroHBM;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.IClienteFone;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelImagem;
import gcom.cadastro.imovel.IImovel;
import gcom.cadastro.imovel.IImovelSubcategoria;
import gcom.cadastro.imovel.IImovelTipoOcupanteQuantidade;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelImagem;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelRamoAtividade;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSubcategoriaPK;
import gcom.cadastro.imovel.ImovelTipoOcupanteQuantidade;
import gcom.cadastro.imovel.ImovelTipoOcupanteQuantidadeAtualizacaoCadastral;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.ComunicadoEmitirConta;
import gcom.gui.cadastro.atualizacaocadastral.ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionHelper;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarGerarLoteAtualizacaoCadastralActionHelper;
import gcom.relatorio.cadastro.atualizacaocadastral.RelatorioFichaFiscalizacaoCadastralHelper;
import gcom.relatorio.cadastro.atualizacaocadastral.RelatorioRelacaoImoveisRotaBean;
import gcom.seguranca.IRepositorioSeguranca;
import gcom.seguranca.RepositorioSegurancaHBM;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.transacao.AlteracaoTipo;
import gcom.seguranca.transacao.Tabela;
import gcom.seguranca.transacao.TabelaAtualizacaoCadastral;
import gcom.seguranca.transacao.TabelaColuna;
import gcom.seguranca.transacao.TabelaColunaAtualizacaoCadastral;
import gcom.util.CollectionUtil;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorComum;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.IRepositorioUtil;
import gcom.util.MergeProperties;
import gcom.util.RepositorioUtilHBM;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

public class ControladorAtualizacaoCadastral extends ControladorComum implements IControladorAtualizacaoCadastral {
		
	private static final long serialVersionUID = -3792912776769033056L;

	private static Logger logger = Logger.getLogger(ControladorAtualizacaoCadastral.class);

	private IRepositorioAtualizacaoCadastral repositorioAtualizacaoCadastral = null;
	private IRepositorioCadastro             repositorioCadastro = null;
	private IRepositorioSeguranca            repositorioSeguranca;
	private IRepositorioUtil                 repositorioUtil;
	private static List<Integer> listaRAParaExclusao = new ArrayList<Integer>();
	private Usuario usuario = null;

	public void ejbCreate() throws CreateException {
		repositorioAtualizacaoCadastral = RepositorioAtualizacaoCadastralHBM.getInstancia();
		repositorioSeguranca            = RepositorioSegurancaHBM.getInstancia();
		repositorioCadastro             = RepositorioCadastroHBM.getInstancia();
		repositorioUtil                 = RepositorioUtilHBM.getInstancia();
	}

	public void atualizarImoveisAprovados(Integer idFuncionalidade, Usuario usuarioLogado) throws ControladorException{
		int idUnidadeIniciada = 0;

		try {
			idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidade, UnidadeProcessamento.FUNCIONALIDADE, 0);


			usuario = getControladorBatch().obterUsuarioQueDisparouProcesso(idFuncionalidade);

			atualizarImagensImoveisAprovados();
			
			processarClientes();
			processarImoveis();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
			listaRAParaExclusao.clear();
		} catch (Exception e) {
		    logger.error("Erro ao processar imoveis aprovados", e);

			if (!listaRAParaExclusao.isEmpty()) {
				deletarRAsPendente(listaRAParaExclusao);
				listaRAParaExclusao.clear();
			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);
			throw new ControladorException("Erro ao atualizar imoveis aprovados.", e);
		}
	}

	public void apagarInformacoesRetornoImovelAtualizacaoCadastral(Integer idImovel) throws Exception {
		atualizarImovelControle(idImovel);
		apagarTabelaAtualizacaoCadastralPorIdImovel(idImovel);
		apagarImovelQuantidadesOcupantes(idImovel);
		apagarImovelRetorno(idImovel);
		apagarImagemRetorno(idImovel);
		apagarImovelSubcategoriaRetorno(idImovel);
		apagarImovelRamoAtividade(idImovel);
		apagarInformacoesRetornoCliente(idImovel);
	}

	public Collection<ImovelSubcategoria> pesquisarImovelSubcategoriaAtualizacaoCadastral(Integer idImovel, Integer idSubcategoria,Integer idCategoria)
		throws ControladorException {
        try {
            return repositorioAtualizacaoCadastral.pesquisarImovelSubcategoriaAtualizacaoCadastral(idImovel, idSubcategoria, idCategoria);
        } catch (ErroRepositorioException ex) {
        	logger.error("Erro ao pesquisar imovel subcategoria para atualizacao cadastral do imovel " + idImovel, ex);
			throw new ControladorException("Erro ao pesquisar imovel subcategoria para atualizacao cadastral do imovel " + idImovel, ex);
        }
    }

	public ImovelAtualizacaoCadastral pesquisarImovelAtualizacaoCadastral(Integer idImovel) throws ControladorException {
        try {
            return repositorioAtualizacaoCadastral.pesquisarImovelAtualizacaoCadastral(idImovel);
        } catch (ErroRepositorioException ex) {
            logger.error("Erro ao pesquisar imovel para atualizacao cadastral " + idImovel, ex);
			throw new ControladorException("Erro ao pesquisar imovel para atualizacao cadastral " + idImovel, ex);
        }
    }


	public Integer recuperaValorSequenceImovelRetorno() throws Exception {
		return repositorioAtualizacaoCadastral.recuperaValorSequenceImovelRetorno();
	}

	public void aprovarImoveisEmLote(Usuario usuarioLogado, Collection<ConsultarMovimentoAtualizacaoCadastralHelper> listaImoveis) throws ControladorException {
		try {
			this.aprovarImoveis(converterListaEmImovelRetorno(listaImoveis), usuarioLogado);
		} catch (ErroRepositorioException e) {
			logger.error("Erro ao pesquisar atualizar imoveis em lote.", e);
			throw new ControladorException("Erro ao atualizar imoveis em lote.", e);
		}

	}

	public ImovelControleAtualizacaoCadastral obterImovelControlePorImovelRetorno(Integer idImovelRetorno) throws ControladorException {
		return  repositorioAtualizacaoCadastral.obterImovelControlePorImovelRetorno(idImovelRetorno);
	}

	public ImovelControleAtualizacaoCadastral obterImovelControle(Integer idImovel) throws ControladorException {
		return  repositorioAtualizacaoCadastral.obterImovelControle(idImovel);
	}

	public Integer obterQuantidadeDeVisitasPorImovelControle(ImovelControleAtualizacaoCadastral imovelControle) throws ControladorException {
		try {
			return repositorioAtualizacaoCadastral.pesquisarVisitasPorImovelControle(imovelControle).size();
		} catch (ErroRepositorioException e) {
			logger.error("Erro ao pesquisar visitas por ImovelControle.", e);
			throw new ControladorException("Erro ao pesquisar visitas por ImovelControle.", e);
		}
	}

	/************************************************************
	 *PRIVATE METHODS
	 * @throws ErroRepositorioException
	 ************************************************************/

	private void processarClientes() throws ControladorException, ErroRepositorioException {
		atualizarClientes();
		incluirClientes();
		excluirClientes();
	}

	private void processarImoveis() throws ControladorException {
		atualizarImoveis();
		incluirImoveis();
		excluirImoveis();
	}

	private void atualizarImovelAtualizacaoCadastral(IImovel imovelRetorno) throws ControladorException {
		Imovel imovel = getControladorImovel().pesquisarImovel(imovelRetorno.getIdImovel());

		LigacaoAguaSituacao situacaoAgua = imovel.getLigacaoAguaSituacao();
		LigacaoEsgotoSituacao situacaoEsgoto = imovel.getLigacaoEsgotoSituacao();

		String coordenadaX = imovel.getCoordenadaX();
		String coordenadaY = imovel.getCoordenadaY();
		MergeProperties.mergeProperties(imovel, imovelRetorno);

		imovel.setId(imovelRetorno.getIdImovel());
		imovel.setUltimaAlteracao(new Date());
		imovel.setLigacaoAguaSituacao(situacaoAgua);
		imovel.setLigacaoEsgotoSituacao(situacaoEsgoto);
		imovel.setUsuarioParaHistorico(usuario);

		if (imovel.isCoordenadasZeradas()) {
			imovel.setCoordenadaX(coordenadaX);
			imovel.setCoordenadaY(coordenadaY);
		}
		
		getControladorAtualizacaoCadastro().atualizar(imovel);

		logger.info(String.format("Imovel atualizado pelo processo de atualizacao cadastral: %s", imovel.getId()));

		inserirImovelImagens(imovel.getId());
	}

	private void inserirImovelImagens(Integer idImovel) throws ControladorException {
		Collection<ImagemRetorno> colecaoImagemRetorno = this.pesquisarImagensRetornoPorIdImovel(idImovel);
		int ordemImagem = 0;

		for (ImagemRetorno imagemRetorno : colecaoImagemRetorno) {
			File imagem = this.copiarImagensRetorno(imagemRetorno);

			ImovelImagem imovelImagem = new ImovelImagem();
			imovelImagem.setIdImovel(idImovel);
			imovelImagem.setNomeImagem(imagem.getName());
			String caminhoImagem = imagem.getAbsolutePath();
			String caminhoFinal = caminhoImagem.substring(caminhoImagem.indexOf("/imovel_imagem"));
			imovelImagem.setCaminhoImagem(caminhoFinal);
			imovelImagem.setUltimaAlteracao(new Date());

			Integer idImovelImagem = (Integer) getControladorUtil().inserir(imovelImagem);

			imovelImagem.setId(idImovelImagem);

			ordemImagem++;
			renomearImovelImagem(imagem, imovelImagem, ordemImagem);
		}
	}

	private void renomearImovelImagem(File imagem, ImovelImagem imovelImagem, int ordemImagem) throws ControladorException {
		String nomeImagem = imagem.getName();
		String extensao = nomeImagem.substring(nomeImagem.indexOf("."), nomeImagem.length());
		String novoNome = imovelImagem.getIdImovel() + "-" + ordemImagem + extensao;
		File imagemRenomeada = new File(imagem.getParentFile().getAbsolutePath(), novoNome);
		imagem.renameTo(imagemRenomeada);

		imovelImagem.setNomeImagem(imagemRenomeada.getName());
		String caminhoImagem = imagemRenomeada.getAbsolutePath();
		String caminhoFinal = caminhoImagem.substring(caminhoImagem.indexOf("/imovel_imagem"));
		imovelImagem.setCaminhoImagem(caminhoFinal);
		getControladorUtil().atualizar(imovelImagem);
	}

	@SuppressWarnings("unchecked")
	public Collection<ImagemRetorno> pesquisarImagensRetornoPorIdImovel(Integer idImovel) throws ControladorException {
		FiltroImagemRetorno filtro = new FiltroImagemRetorno(FiltroImagemRetorno.IMOVEL_ID);
		filtro.adicionarParametro(new ParametroSimples(FiltroImagemRetorno.IMOVEL_ID, idImovel));

		Collection<ImagemRetorno> colecaoImagemRetorno = getControladorUtil().pesquisar(filtro, ImagemRetorno.class.getName());

		return colecaoImagemRetorno;
	}

	@SuppressWarnings("resource")
	private File copiarImagensRetorno(ImagemRetorno imagemRetorno) throws ControladorException {
		String caminhoJboss = System.getProperty("jboss.server.home.dir");
		File arquivoOrigem = new File(caminhoJboss, imagemRetorno.getPathImagem());

		File arquivoDestino = this.criarArquivoDestinoImovelImagem(imagemRetorno);

		FileChannel origemChannel = null;
		FileChannel destinoChannel = null;

		try {
			if (!arquivoOrigem.exists()) {
				arquivoOrigem = new File(caminhoJboss, "images/" + arquivoOrigem.getName());
			}

			origemChannel = new FileInputStream(arquivoOrigem).getChannel();
			destinoChannel = new FileOutputStream(arquivoDestino).getChannel();
			origemChannel.transferTo(0, origemChannel.size(), destinoChannel);
			
			atualizarImagemRetorno(imagemRetorno, ConstantesSistema.SIM);
		}
		catch (IOException e){
			e.printStackTrace();
			atualizarImagemRetorno(imagemRetorno, ConstantesSistema.NAO);
			if (e.getClass().equals(FileNotFoundException.class)) {
			} else {
				throw new ControladorException("Erro ao copiar imagens do retorno", e);
			}
		}
		finally {
		    try {
		        if (origemChannel != null && origemChannel.isOpen())
		            origemChannel.close();
		        if (destinoChannel != null && destinoChannel.isOpen())
		            destinoChannel.close();
            } catch (Exception e) {
            }
		}

		return arquivoDestino;
	}
	
	private void atualizarImagemRetorno(ImagemRetorno imagem, Short indicadorAtualizado) throws ControladorException{
		try {
			imagem.setIndicadorImagemAtualizada(indicadorAtualizado);
			getControladorUtil().atualizar(imagem);
		} catch (ControladorException e) {
			throw new ControladorException("Erro ao atualizar indicador atualizado na imagem de retorno.", e);
		}
	}

	private File criarArquivoDestinoImovelImagem(ImagemRetorno imagemRetorno) {
		String pastaDestino = retornarPastaDestinoImovelImagem(imagemRetorno.getIdImovel());
		
		File arquivoDestino = new File(pastaDestino, imagemRetorno.getNomeImagem());
		
		File caminhoDestino = arquivoDestino.getParentFile().getAbsoluteFile();
		
		if (!caminhoDestino.exists())
			caminhoDestino.mkdir();

		return arquivoDestino;
	}

	@SuppressWarnings("unchecked")
	private String retornarPastaDestinoImovelImagem(Integer idImovel) {
		Imovel imovel = null;

		try {
			FiltroImovel filtro = new FiltroImovel();
			filtro.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOCALIDADE);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA_ROTA);
			
			imovel = (Imovel) Util.retonarObjetoDeColecao(getControladorUtil().pesquisar(filtro, Imovel.class.getName()));
		} catch (ControladorException e) {
			e.printStackTrace();
		}

		String pasta = Util.completaStringComZeroAEsquerda(imovel.getLocalidade().getId()+"", 3) + "_"
				+ Util.completaStringComZeroAEsquerda(imovel.getSetorComercial().getCodigo()+"", 3) + "_"
				+ Util.completaStringComZeroAEsquerda(imovel.getQuadra().getRota().getCodigo()+"", 2);

		String caminhoJboss = System.getProperty("jboss.server.home.dir");

		return caminhoJboss + "/imovel_imagem/" + pasta;
	}
	
	private void atualizarImovelSubcategoriaAtualizacaoCadastral(IImovel imovelRetorno) throws Exception {
		imovelRetorno.setId(imovelRetorno.getIdImovel());

        Collection<IImovelSubcategoria> subcategoriasRetorno = repositorioAtualizacaoCadastral.obterImovelSubcategoriaParaAtualizar(imovelRetorno.getIdImovel());
		Collection<Integer> idsSubcategorias = this.obterIdsSubcategoriasImovel(imovelRetorno.getIdImovel());

		for (IImovelSubcategoria subcategoriaRetorno : subcategoriasRetorno) {
			ImovelSubcategoria imovelSubcategoria = this.obterSubcategoriaDoImovel(imovelRetorno.getIdImovel(), subcategoriaRetorno.getSubcategoria().getId());

			if (imovelSubcategoria != null) {
				MergeProperties.mergeProperties(imovelSubcategoria, subcategoriaRetorno);
				imovelSubcategoria.setUltimaAlteracao(new Date());
				getControladorUtil().atualizar(imovelSubcategoria);
				idsSubcategorias.remove(imovelSubcategoria.getComp_id().getSubcategoria().getId());
			} else {
				imovelSubcategoria = new ImovelSubcategoria(new ImovelSubcategoriaPK(subcategoriaRetorno.getImovel(), subcategoriaRetorno.getSubcategoria()));
				MergeProperties.mergeProperties(imovelSubcategoria, subcategoriaRetorno);
				imovelSubcategoria.setUltimaAlteracao(new Date());
				getControladorUtil().inserir(imovelSubcategoria);
			}
		}

		atualizarImovelPerfil(imovelRetorno.getId(), subcategoriasRetorno);

		this.removerSubcategoriasDoImovel(imovelRetorno.getIdImovel(), idsSubcategorias);
	}

	private void atualizarImovelQuantidadesOcupantes(IImovel imovelRetorno) throws Exception {
	    try {
            Collection<IImovelTipoOcupanteQuantidade> ocupantes = repositorioAtualizacaoCadastral.obterImovelQuantidadesOcupantesParaAtualizar(imovelRetorno.getIdImovel());

            repositorioCadastro.removerQuantidadesOcupantesImovel(imovelRetorno.getIdImovel());

            for (IImovelTipoOcupanteQuantidade item : ocupantes) {
                ImovelTipoOcupanteQuantidade novoRegistro = new ImovelTipoOcupanteQuantidade();
                novoRegistro.setImovel(item.getImovel());
                novoRegistro.setTipoOcupante(item.getTipoOcupante());
                novoRegistro.setQuantidade(item.getQuantidade());
                repositorioUtil.inserir(novoRegistro);
            }
        } catch (Exception e) {
            throw new Exception("Erro ao atualizar quantidades de ocupantes do imovel", e);

        }
	}

	private void atualizarImovelRamoAtividadeAtualizacaoCadastral(IImovel imovelRetorno) throws Exception {
		try {
			Collection<IImovelRamoAtividade> ramosAtividadeRetorno = repositorioAtualizacaoCadastral.obterImovelRamoAtividadeParaAtualizar(imovelRetorno.getIdImovel());

			Collection<Integer> idsRamosAtividadesImovel = this.obterIdsRamosAtividadesImovel(imovelRetorno.getIdImovel());

			for (IImovelRamoAtividade ramoAtividadeRetorno : ramosAtividadeRetorno) {
				ImovelRamoAtividade imovelRamoAtividade = this.obterRamoAtividadeDoImovel(imovelRetorno.getIdImovel(), ramoAtividadeRetorno.getRamoAtividade().getId());

				if (imovelRamoAtividade != null) {
					MergeProperties.mergeProperties(imovelRamoAtividade, ramoAtividadeRetorno);
					imovelRamoAtividade.setUltimaAlteracao(new Date());
					getControladorUtil().atualizar(imovelRamoAtividade);
					idsRamosAtividadesImovel.remove(imovelRamoAtividade.getComp_id().getRamo_atividade().getId());
				} else {
					ImovelRamoAtividade imovelRamoAtividadeNovo = new ImovelRamoAtividade(ramoAtividadeRetorno.getImovel().getId(), ramoAtividadeRetorno.getRamoAtividade().getId());
					imovelRamoAtividadeNovo.setUltimaAlteracao(new Date());

					getControladorUtil().inserir(imovelRamoAtividadeNovo);
				}
			}
			this.removerRamosAtividadeDoImovel(imovelRetorno.getIdImovel(), idsRamosAtividadesImovel);
		} catch (ControladorException e) {
			logger.error("Erro ao atualizar ramo de atividade retorno do imovel " + imovelRetorno.getId(), e);
			throw new ControladorException("Erro ao atualizar ramo de atividade retorno do imovel " + imovelRetorno.getId(), e);
		}
	}

	private void atualizarClienteFone(ClienteImovelRetorno clienteImovelRetorno) throws Exception {
		getControladorCliente().removerTodosTelefonesPorCliente(clienteImovelRetorno.getCliente().getId());

		Collection<IClienteFone> retornos = repositorioAtualizacaoCadastral.obterClienteFoneParaAtualizar(
				clienteImovelRetorno.getImovel().getId(), clienteImovelRetorno.getCliente().getId());
		
		boolean telefonePadraoSetado = false;

		for (IClienteFone retorno : retornos) {
			ClienteFone telefone = new ClienteFone();
			MergeProperties.mergeProperties(telefone, retorno);
			telefone.setId(null);
			telefone.setUltimaAlteracao(new Date());
			
			if (telefonePadraoSetado) {
				telefone.setIndicadorTelefonePadrao(ConstantesSistema.INDICADOR_NAO_TELEFONE_PRINCIPAL);
			} else {
				telefone.setIndicadorTelefonePadrao(ConstantesSistema.INDICADOR_TELEFONE_PRINCIPAL);
				telefonePadraoSetado = true;
			}
			
			getControladorUtil().inserir(telefone);
		}
	}

	private void removerSubcategoriasDoImovel(Integer idImovel, Collection<Integer> idsSubcategorias) throws ControladorException {
		for (Integer idSubcategoria : idsSubcategorias) {
			ImovelSubcategoria imovelSubcategoria = this.obterSubcategoriaDoImovel(idImovel, idSubcategoria);
			getControladorUtil().remover(imovelSubcategoria);
		}
	}

	private void removerRamosAtividadeDoImovel(Integer idImovel, Collection<Integer> idsRamosAtividades) throws ControladorException {
		for (Integer id : idsRamosAtividades) {
			ImovelRamoAtividade imovelRamoAtividade = this.obterRamoAtiviadeDoImovel(new Imovel(idImovel), id);
			getControladorUtil().remover(imovelRamoAtividade);
		}
	}

	private ImovelRamoAtividade obterRamoAtiviadeDoImovel(IImovel imovel, Integer id) throws ControladorException {
		Collection<ImovelRamoAtividade> ramosAtividadeImovel = getControladorImovel().pesquisarRamoAtividadeDoImovel(imovel.getId());

		for (ImovelRamoAtividade ramoAtividade : ramosAtividadeImovel) {
			if (ramoAtividade.getComp_id().getRamo_atividade().getId().equals(id)) {
				return ramoAtividade;
			}
		}
		return null;
	}

	private ImovelSubcategoria obterSubcategoriaDoImovel(Integer idImovel, Integer idSubcategoria) throws ControladorException {
		Collection<ImovelSubcategoria> subcategoriasImovel = getControladorImovel().pesquisarImovelSubcategorias(new Imovel(idImovel));

		for (ImovelSubcategoria subcategoria : subcategoriasImovel) {
			if (subcategoria.getComp_id().getSubcategoria().getId().equals(idSubcategoria)) {
				return subcategoria;
			}
		}

		return null;
	}

	private ImovelRamoAtividade obterRamoAtividadeDoImovel(Integer idImovel, Integer id) throws ControladorException {
		Collection<ImovelRamoAtividade> ramosAtividadeImovel = getControladorImovel().pesquisarRamoAtividadeDoImovel(idImovel);

		for (ImovelRamoAtividade imovelRamoAtividade : ramosAtividadeImovel) {
			if (imovelRamoAtividade.getComp_id().getRamo_atividade().getId().equals(id)) {
				return imovelRamoAtividade;
			}
		}

		return null;
	}

	private Collection<Integer> obterIdsSubcategoriasImovel(Integer idImovel) throws ControladorException {
		Collection<Integer> ids = new ArrayList<Integer>();
		Collection<ImovelSubcategoria> subcategoriasImovel = getControladorImovel().pesquisarImovelSubcategorias(new Imovel(idImovel));

		for (ImovelSubcategoria imovelSubcategoria : subcategoriasImovel) {
			ids.add(imovelSubcategoria.getComp_id().getSubcategoria().getId());
		}

		return ids;

	}
	
	private Collection<Integer> obterIdsRamosAtividadesImovel(Integer idImovel) throws ControladorException{
		Collection<Integer> ids = new ArrayList<Integer>();
		Collection<ImovelRamoAtividade> ramosAtividade = getControladorImovel().pesquisarRamoAtividadeDoImovel(idImovel);

		for (ImovelRamoAtividade imovelRamoAtividade : ramosAtividade) {
			ids.add(imovelRamoAtividade.getComp_id().getRamo_atividade().getId());
		}
		return ids;
	}

	private Collection<IImovel> obterImoveisParaAtualizar(Integer tipoOperacao) throws ControladorException {
		Collection<IImovel> imoveis = null;
		try {
			imoveis = repositorioAtualizacaoCadastral.obterImoveisParaAtualizar(tipoOperacao);
		} catch (ErroRepositorioException e) {
			logger.error("Erro ao pesquisar imoveis para atualizar.", e);
			throw new ControladorException("Erro ao pesquisar imoveis para atualizar.", e);

		}
		return imoveis;
	}

	private Collection<ClienteImovelRetorno> obterClientesParaAtualizar() throws ControladorException {
		Collection<ClienteImovelRetorno> clienteImoveisRetorno = null;
		try {
			clienteImoveisRetorno = repositorioAtualizacaoCadastral.obterClientesParaAtualizar();
		} catch (ErroRepositorioException e) {
			logger.error("Erro ao pesquisar clientes para atualizar.", e);
			throw new ControladorException("Erro ao pesquisar clientes para atualizar.", e);

		}
		return clienteImoveisRetorno;
	}

	private Collection<ClienteImovelRetorno> obterClientesParaIncluir() throws ControladorException {
		Collection<ClienteImovelRetorno> clienteImoveisRetorno = null;
		try {
			clienteImoveisRetorno = repositorioAtualizacaoCadastral.obterClientesParaIncluir();
		} catch (ErroRepositorioException e) {
			logger.error("Erro ao pesquisar clientes para incluir.", e);
			throw new ControladorException("Erro ao pesquisar clientes para incluir.", e);

		}
		return clienteImoveisRetorno;
	}

	private Collection<IClienteImovel> obterClientesParaExcluirRelacao() throws ControladorException {
		Collection<IClienteImovel> clienteImoveis = null;
		try {
			clienteImoveis = repositorioAtualizacaoCadastral.obterClientesParaExcluirRelacao();
		} catch (ErroRepositorioException e) {
			logger.error("Erro ao pesquisar clientes para excluir.", e);
			throw new ControladorException("Erro ao pesquisar clientes para excluir.", e);

		}
		return clienteImoveis;
	}

	private boolean isImovelEmCampo(Integer idImovel) {
		try {
			getControladorMicromedicao().validarImovelEmCampo(idImovel);
			return false;
		} catch (ControladorException e) {
			return true;
		}
	}

	private void atualizarImovelControle(Integer idImovel) throws Exception {
		ImovelControleAtualizacaoCadastral controle = repositorioAtualizacaoCadastral.pesquisarImovelControleAtualizacao(idImovel);
		if (controle != null){
			controle.setImovelRetorno(null);
			controle.setSituacaoAtualizacaoCadastral(new SituacaoAtualizacaoCadastral(SituacaoAtualizacaoCadastral.EM_CAMPO));
			this.getControladorUtil().atualizar(controle);
		}
	}

	private void apagarInformacoesRetornoCliente(Integer idImovel) throws Exception  {
		Collection<Integer> idsClientesRetorno = repositorioAtualizacaoCadastral.pesquisarIdsClienteRetorno(idImovel);
		if(!idsClientesRetorno.isEmpty()) {
			apagarClienteEnderecoRetornoPorIdsClientes(idsClientesRetorno);
			apagarClienteFoneRetornoPorIdsClientes(idsClientesRetorno);
			apagarClienteRetorno(idsClientesRetorno);
		}
		apagarImovelClienteRetorno(idImovel);
	}

	private void apagarImovelClienteRetorno(Integer idImovel) throws Exception {
		repositorioAtualizacaoCadastral.apagarClienteImovelRetornoPorIdImovel(idImovel);
	}

	private void apagarImovelRetorno(Integer idImovel) throws Exception {
		repositorioAtualizacaoCadastral.apagarImovelRetornoPorIdImovel(idImovel);
	}

	private void apagarTabelaAtualizacaoCadastralPorIdImovel(Integer idImovel) throws Exception {
		List<TabelaAtualizacaoCadastral> colecaoTabelaAtualizacaoCadastral;
		List<TabelaColunaAtualizacaoCadastral> colecaoTabelaColunaAtualizacaoCadastral;
		colecaoTabelaAtualizacaoCadastral = repositorioSeguranca.pesquisaTabelaAtualizacaoCadastralPorImovel(idImovel);

		if(colecaoTabelaAtualizacaoCadastral != null) {
			for(TabelaAtualizacaoCadastral tabelaAtlzCad : colecaoTabelaAtualizacaoCadastral){
				colecaoTabelaColunaAtualizacaoCadastral = repositorioSeguranca.pesquisaTabelaColunaAtualizacaoCadastral(tabelaAtlzCad.getId());
				for(TabelaColunaAtualizacaoCadastral tabelaColunaAtlzCad : colecaoTabelaColunaAtualizacaoCadastral) {
					this.getControladorUtil().remover(tabelaColunaAtlzCad);
				}
				this.getControladorUtil().remover(tabelaAtlzCad);
			}
		}
	}

	private void apagarImovelSubcategoriaRetorno(Integer idImovel) throws Exception {
		List<ImovelSubcategoriaRetorno> colecaoImovelSubCategoriaRetorno;
		colecaoImovelSubCategoriaRetorno = repositorioAtualizacaoCadastral.pesquisarImovelSubcategoriaRetornoPorIdImovel(idImovel);
		if(colecaoImovelSubCategoriaRetorno != null) {
			for(ImovelSubcategoriaRetorno imovelSubCategoriaRetorno : colecaoImovelSubCategoriaRetorno) {
				this.getControladorUtil().remover(imovelSubCategoriaRetorno);
			}
		}
	}

	private void apagarImovelQuantidadesOcupantes(Integer idImovel) throws Exception {
		repositorioAtualizacaoCadastral.apagarImovelQuantidadesOcupantes(idImovel);
	}

	private void apagarImovelRamoAtividade(Integer idImovel) throws Exception {
	    repositorioAtualizacaoCadastral.apagarImovelRetornoRamoAtividadeRetornoPorIdImovel(idImovel);
	}

	private void apagarClienteEnderecoRetornoPorIdsClientes(Collection<Integer> idsClientesRetorno) throws Exception{
		repositorioAtualizacaoCadastral.apagarClienteEnderecoRetorno(idsClientesRetorno);
	}

	private void apagarClienteFoneRetornoPorIdsClientes(Collection<Integer> idsClientesRetorno) throws Exception {
		repositorioAtualizacaoCadastral.apagarClienteFoneRetorno(idsClientesRetorno);
	}

	private void apagarClienteRetorno(Collection<Integer> idsClientesRetorno) throws Exception{
		repositorioAtualizacaoCadastral.apagarClienteRetorno(idsClientesRetorno);
	}

	public Collection<ImovelSubcategoriaAtualizacaoCadastral> pesquisarSubCategoriasAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException{
		return repositorioAtualizacaoCadastral.pesquisarSubCategoriasAtualizacaoCadastral(idImovel);
	}

	public void apagarImagemRetorno(Integer idImovel) throws Exception {
		repositorioAtualizacaoCadastral.apagarImagemRetornoPorIdImovel(idImovel);
	}

	private void atualizarImoveis() throws ControladorException {
		int idImovelRetorno = -1;

		try {

			Collection<IImovel> imoveisAlteracao = this.obterImoveisParaAtualizar(AlteracaoTipo.ALTERACAO);
			for (IImovel imovelRetorno : imoveisAlteracao) {
				if (!isImovelEmCampo(imovelRetorno.getIdImovel())) {
					idImovelRetorno = imovelRetorno.getId();

					atualizarImovelAtualizacaoCadastral(imovelRetorno);
					atualizarImovelSubcategoriaAtualizacaoCadastral(imovelRetorno);
					atualizarImovelRamoAtividadeAtualizacaoCadastral(imovelRetorno);
					atualizarImovelQuantidadesOcupantes(imovelRetorno);
					atualizarImovelProcessado(idImovelRetorno);
				}
			}
		} catch (Exception e) {
			logger.error("Erro ao atualizar imovel retorno " + idImovelRetorno, e);
			logger.error("Erro ao atualizar imovel retorno. " + e.getMessage() , e);
			throw new ControladorException("Erro ao atualizar imovel retorno  " + idImovelRetorno, e);
		}
	}
	
	private void atualizarImagensImoveisAprovados() throws ControladorException {
		
		try {
			List<Integer> imoveis = repositorioAtualizacaoCadastral.obterImagensImoveisAprovador();
			
			for (Integer idImovel: imoveis) {
					inserirImovelImagens(idImovel);
			}
		} catch (ControladorException e) {
			throw new ControladorException("Erro ao atualizar imagens de imoveis aprovados.", e);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("Erro ao atualizar imagens de imoveis aprovados.", e);
		}
	}

	private void incluirImoveis() throws ControladorException {
		Integer idImovel = null;

		try {
			Collection<IImovel> imoveisInclusao = this.obterImoveisParaAtualizar(AlteracaoTipo.INCLUSAO);

			for (IImovel imovelRetorno : imoveisInclusao) {

				imovelRetorno.setIdImovel(null);
				Integer idSetorComercial = getControladorCadastro().pesquisarIdSetorComercialPorCodigoELocalidade(imovelRetorno.getIdLocalidade(), imovelRetorno.getCodigoSetorComercial());

				String protocoloAtendimento = getControladorRegistroAtendimento().obterProtocoloAtendimento();

				HashMap<ClienteRelacaoTipo, ICliente> mapClientesImovel = this.obterClientesImovel(imovelRetorno.getId());

				UnidadeOrganizacional unidade = getControladorCadastro().obterUnidadePorLocalidade(imovelRetorno.getIdLocalidade());
				
				RADadosGeraisHelper raDadosGeraisHelper = RABuilder.buildRADadosGeraisAtualizacaoCadastralInclusaoImovel(imovelRetorno, mapClientesImovel, AlteracaoTipo.INCLUSAO, protocoloAtendimento);
				RALocalOcorrenciaHelper raLocalOcorrenciaHelper = RABuilder.buildRALocalOcorrenciaAtualizacaoCadastral(imovelRetorno, idSetorComercial, AlteracaoTipo.INCLUSAO, unidade);
				RASolicitanteHelper raSolicitanteHelper = RABuilder.buildRASolicitanteAtualizacaoCadastral();

				Integer[] retorno = getControladorRegistroAtendimento().inserirRegistroAtendimento(raDadosGeraisHelper, raLocalOcorrenciaHelper, raSolicitanteHelper);

				listaRAParaExclusao.add(retorno[0]);

				atualizarImovelProcessado(imovelRetorno.getId());
			}
		} catch (Exception e) {
			logger.error("Erro ao inserir imovel retorno " + idImovel);
			throw new ControladorException("Erro ao inserir imovel retorno  " + idImovel, e);

		}
	}

	private HashMap<ClienteRelacaoTipo, ICliente> obterClientesImovel(Integer idImovelRetorno) throws ControladorException {
		HashMap<ClienteRelacaoTipo, ICliente> mapClientes = new HashMap<ClienteRelacaoTipo, ICliente>();

		try {
			Collection<ClienteImovelRetorno> clientesImovel = repositorioAtualizacaoCadastral.obterClienteImoveisDoImovel(idImovelRetorno);

			for (ClienteImovelRetorno clienteImovelRetorno :clientesImovel) {
				ICliente clienteRetorno = repositorioAtualizacaoCadastral.pesquisarClienteRetorno(clienteImovelRetorno);

				mapClientes.put(clienteImovelRetorno.getClienteRelacaoTipo(), clienteRetorno);
			}

		} catch (ErroRepositorioException e) {
			logger.error("Erro ao obter clientes do imovel retorno" + idImovelRetorno);
			throw new ControladorException("Erro ao obter clientes do imovel retorno  "+ idImovelRetorno, e);
		}
		return mapClientes;
	}

	private void excluirImoveis() throws ControladorException {
		Integer idImovel = null;

		try {
			Collection<IImovel> imoveisExclusao = this.obterImoveisParaAtualizar(AlteracaoTipo.EXCLUSAO);

			for (IImovel imovelRetorno : imoveisExclusao) {

				if (!isImovelEmCampo(imovelRetorno.getIdImovel())) {
					Integer idSetorComercial = getControladorCadastro().pesquisarIdSetorComercialPorCodigoELocalidade(imovelRetorno.getIdLocalidade(), imovelRetorno.getCodigoSetorComercial());

					String protocoloAtendimento = getControladorRegistroAtendimento().obterProtocoloAtendimento();

					Integer idUsuarioAprovacao = repositorioSeguranca.pesquisarIdUsuarioAutorizadorImoveis(imovelRetorno.getIdImovel());

					UnidadeOrganizacional unidade = getControladorCadastro().obterUnidadePorLocalidade(imovelRetorno.getIdLocalidade());
					
					RADadosGeraisHelper raDadosGeraisHelper = RABuilder.buildRADadosGeraisAtualizacaoCadastral(imovelRetorno, AlteracaoTipo.EXCLUSAO, protocoloAtendimento, idUsuarioAprovacao);
					RALocalOcorrenciaHelper raLocalOcorrenciaHelper = RABuilder.buildRALocalOcorrenciaAtualizacaoCadastral(imovelRetorno, idSetorComercial, AlteracaoTipo.EXCLUSAO, unidade);
					RASolicitanteHelper raSolicitanteHelper = RABuilder.buildRASolicitanteAtualizacaoCadastral();

					Integer[] retorno = getControladorRegistroAtendimento().inserirRegistroAtendimento(raDadosGeraisHelper, raLocalOcorrenciaHelper, raSolicitanteHelper);

					listaRAParaExclusao.add(retorno[0]);

					atualizarImovelProcessado(imovelRetorno.getId());
				}
			}

		} catch (Exception e) {
			logger.error("Erro ao excluir imovel retorno" + idImovel);
			throw new ControladorException("Erro ao excluir imovel retorno  "+ idImovel, e);
		}
	}

	@SuppressWarnings("unused")
	private void atualizarImoveisProcessados(Collection<IImovel> listaImoveis) throws ControladorException {

		Collection<ImovelControleAtualizacaoCadastral> listaImoveisControle = repositorioAtualizacaoCadastral.obterImoveisControle(listaImoveis);

		for (ImovelControleAtualizacaoCadastral imovelControle :  listaImoveisControle) {
			imovelControle.setDataProcessamento(new Date());
			getControladorUtil().atualizar(imovelControle);
		}
	}

	private void atualizarImovelProcessado(Integer idImovelRetorno) throws ControladorException {

		ImovelControleAtualizacaoCadastral imovelControle = repositorioAtualizacaoCadastral.obterImovelControlePorImovelRetorno(idImovelRetorno);

		imovelControle.setDataProcessamento(new Date());
		imovelControle.setSituacaoAtualizacaoCadastral(new SituacaoAtualizacaoCadastral(SituacaoAtualizacaoCadastral.ATUALIZADO));

		getControladorUtil().atualizar(imovelControle);
		
	}

	public Collection<Integer> pesquisarImoveisPorSituacaoPeriodo(Date dataInicial, Date dataFinal, Integer idSituacaoCadastral)
			throws ControladorException {
		try {
			return repositorioAtualizacaoCadastral.pesquisarImoveisPorSituacaoPeriodo(idSituacaoCadastral, dataInicial, dataFinal);
		} catch (ErroRepositorioException e) {
			 throw new ControladorException("erro.sistema", e);
		}
	}

	private void atualizarClientes() throws ControladorException {
		int idImovel = -1;

		try {
			Collection<ClienteImovelRetorno> clientesAlteracao = this.obterClientesParaAtualizar();

			for (ClienteImovelRetorno clienteImovelRetorno : clientesAlteracao) {

				idImovel = clienteImovelRetorno.getImovel().getId();

				if (!isImovelEmCampo(idImovel)) {

					if (existeRelacaoClienteImovel(clienteImovelRetorno)) {
						atualizarInformacoesCliente(clienteImovelRetorno);
						atualizarClienteFone(clienteImovelRetorno);

					} else {
						incluirNovaRelacaoCliente(clienteImovelRetorno);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Erro ao atualizar clientes do imovel " + idImovel, e);
			throw new ControladorException("Erro ao atualizar clientes do imovel.", e);
		}
	}

	private void atualizarInformacoesCliente(ClienteImovelRetorno clienteImovelRetorno)	throws ControladorException {
		try {
			ICliente clienteRetorno = repositorioAtualizacaoCadastral.pesquisarClienteRetorno(clienteImovelRetorno);
			ICliente cliente = getControladorCliente().pesquisarCliente(clienteImovelRetorno.getCliente().getId());

			if (cliente != null) {
				MergeProperties.mergeInterfaceProperties(cliente, clienteRetorno);

				((Cliente)cliente).setUsuarioParaHistorico(usuario);

				getControladorAtualizacaoCadastro().atualizar(cliente);
			}
		} catch (ErroRepositorioException e) {
			logger.error("Erro ao atualizar cliente imovel retorno: " + clienteImovelRetorno.getId(), e);
			throw new ControladorException("Erro ao atualizar cliente imovel retorno.", e);
		}
	}

	private void incluirNovaRelacaoCliente(ClienteImovelRetorno clienteImovelRetorno) throws ControladorException {
		Integer idImovel = null;

		try {
			ICliente clienteRetorno = repositorioAtualizacaoCadastral.pesquisarClienteRetorno(clienteImovelRetorno);
			IImovel imovelRetorno = repositorioAtualizacaoCadastral.pesquisarImovelRetorno(clienteImovelRetorno.getImovel().getId());
			Collection<IClienteFone> telefones  = repositorioAtualizacaoCadastral.pesquisarClienteFoneRetorno(clienteImovelRetorno.getId());
			Collection<IClienteEndereco> enderecos  = repositorioAtualizacaoCadastral.pesquisarClienteEnderecoRetorno(clienteImovelRetorno.getId());
			
			Integer idSetorComercial = getControladorCadastro().pesquisarIdSetorComercialPorCodigoELocalidade(imovelRetorno.getIdLocalidade(), imovelRetorno.getCodigoSetorComercial());

			String protocoloAtendimento = getControladorRegistroAtendimento().obterProtocoloAtendimento();

			Integer idUsuarioAprovacao = repositorioSeguranca.pesquisarIdUsuarioAutorizadorImoveis(imovelRetorno.getIdImovel());

			UnidadeOrganizacional unidade = getControladorCadastro().obterUnidadePorLocalidade(imovelRetorno.getIdLocalidade());
			
			RADadosGeraisHelper raDadosGeraisHelper = RABuilder.buildRADadosGeraisAtualizacaoCadastral(imovelRetorno, clienteRetorno, clienteImovelRetorno, 
					AlteracaoTipo.INCLUSAO, protocoloAtendimento, idUsuarioAprovacao, telefones, enderecos);
			
			RALocalOcorrenciaHelper raLocalOcorrenciaHelper = RABuilder.buildRALocalOcorrenciaAtualizacaoCadastral(imovelRetorno, idSetorComercial, AlteracaoTipo.INCLUSAO, unidade);
			RASolicitanteHelper raSolicitanteHelper = RABuilder.buildRASolicitanteAtualizacaoCadastral();

			getControladorRegistroAtendimento().inserirRegistroAtendimento(raDadosGeraisHelper, raLocalOcorrenciaHelper, raSolicitanteHelper);
		} catch (Exception e) {
			logger.error("Erro ao incluir nova relacao de cliente para o imovel " + idImovel);
			throw new ControladorException("Erro ao incluir nova relacao de cliente para o imovel.", e);
		}
	}

	private boolean existeRelacaoClienteImovel(ClienteImovelRetorno clienteImovelRetorno) throws ControladorException {
		boolean existeRelacao = false;
		try {
			existeRelacao = repositorioAtualizacaoCadastral.existeRelacaoClienteImovel(
								clienteImovelRetorno.getImovel().getId(),
								clienteImovelRetorno.getCliente().getId(),
								clienteImovelRetorno.getClienteRelacaoTipo().getId());
		} catch (ErroRepositorioException e) {
			logger.error("Erro ao verificar existencia de relacao cliente imovel.", e);
		}

		return existeRelacao;

	}

	private void incluirClientes() throws ControladorException {
		Integer idImovel = null;

		try {
			Collection<ClienteImovelRetorno> clientesImovelInclusao = this.obterClientesParaIncluir();

		
			for (ClienteImovelRetorno clienteImovelRetorno : clientesImovelInclusao) {

				if (!isImovelEmCampo(clienteImovelRetorno.getImovel().getId())) {
					logger.info("Atualizando imóvel " + clienteImovelRetorno.getImovel().getId() + ", cliente: " + clienteImovelRetorno.getIdClienteRetorno() );
					ICliente clienteRetorno = repositorioAtualizacaoCadastral.pesquisarClienteRetorno(clienteImovelRetorno);
					IImovel imovelRetorno = repositorioAtualizacaoCadastral.pesquisarImovelRetorno(clienteImovelRetorno.getImovel().getId());
					Collection<IClienteFone> telefones  = repositorioAtualizacaoCadastral.pesquisarClienteFoneRetorno(clienteImovelRetorno.getIdClienteRetorno());
					Collection<IClienteEndereco> enderecos  = repositorioAtualizacaoCadastral.pesquisarClienteEnderecoRetorno(clienteImovelRetorno.getIdClienteRetorno());
					
					Integer idSetorComercial = getControladorCadastro().pesquisarIdSetorComercialPorCodigoELocalidade(imovelRetorno.getIdLocalidade(), imovelRetorno.getCodigoSetorComercial());

					String protocoloAtendimento = getControladorRegistroAtendimento().obterProtocoloAtendimento();

					Integer idUsuarioAprovacao = repositorioSeguranca.pesquisarIdUsuarioAutorizadorImoveis(imovelRetorno.getIdImovel());

					UnidadeOrganizacional unidade = getControladorCadastro().obterUnidadePorLocalidade(imovelRetorno.getIdLocalidade());
					
					RADadosGeraisHelper raDadosGeraisHelper = RABuilder.buildRADadosGeraisAtualizacaoCadastral(imovelRetorno, clienteRetorno, clienteImovelRetorno, AlteracaoTipo.INCLUSAO, 
							protocoloAtendimento, idUsuarioAprovacao, telefones, enderecos);
					
					RALocalOcorrenciaHelper raLocalOcorrenciaHelper = RABuilder.buildRALocalOcorrenciaAtualizacaoCadastral(imovelRetorno, idSetorComercial, AlteracaoTipo.INCLUSAO, unidade);
					RASolicitanteHelper raSolicitanteHelper = RABuilder.buildRASolicitanteAtualizacaoCadastral();

					Integer[] retorno = getControladorRegistroAtendimento().inserirRegistroAtendimento(raDadosGeraisHelper, raLocalOcorrenciaHelper, raSolicitanteHelper);

					listaRAParaExclusao.add(retorno[0]);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao inserir cliente." + idImovel);
			throw new ControladorException("Erro ao inserir cliente.", e);

		}
	}

	@SuppressWarnings("rawtypes")
	public void deletarRAsPendente(List<Integer> listaRAs) {
		try {


			for (Integer integer : listaRAs) {

				RegistroAtendimento registroAtendimento = getRegistroAtendimento(integer).iterator().next();

				FiltroRegistroAtendimentoSolicitante filtroRegistroAtendimentoSolicitante = new FiltroRegistroAtendimentoSolicitante();
				filtroRegistroAtendimentoSolicitante.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimentoSolicitante.REGISTRO_ATENDIMENTO_ID, registroAtendimento.getId()));

				FiltroTramite filtroTramite = new FiltroTramite();
				filtroTramite.adicionarParametro(new ParametroSimples(FiltroTramite.REGISTRO_ATENDIMENTO_ID, registroAtendimento.getId()));

				FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
				filtroOrdemServico.adicionarParametro(new ParametroSimples(FiltroOrdemServico.REGISTRO_ATENDIMENTO_ID, registroAtendimento.getId()));

				FiltroRegistroAtendimentoUnidade filtroRegistroAtendimentoUnidade = new FiltroRegistroAtendimentoUnidade();
				filtroRegistroAtendimentoUnidade.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimentoUnidade.REGISTRO_ATENDIMENTO_ID, registroAtendimento.getId()));

				Collection rasHelper = getControladorUtil().pesquisar(filtroRegistroAtendimentoSolicitante, RegistroAtendimentoSolicitante.class.getName());
				Collection tramiteHelper = getControladorUtil().pesquisar(filtroTramite, Tramite.class.getName());
				Collection osHelper = getControladorUtil().pesquisar(filtroOrdemServico, OrdemServico.class.getName());
				Collection raUnidadeHelper = getControladorUtil().pesquisar(filtroRegistroAtendimentoUnidade, RegistroAtendimentoUnidade.class.getName());

				if (osHelper != null && !osHelper.isEmpty()) {

					for (Object ordemServico : osHelper) {
						Collection osuHelper = getOrdemServicoUnidade(((OrdemServico) ordemServico).getId());

						if (osuHelper == null)
							continue;

						Iterator i = osuHelper.iterator();
						while (i.hasNext()) {
							getControladorUtil().remover(i.next());
						}
					}

					Iterator i = osHelper.iterator();

					while (i.hasNext()) {
						getControladorUtil().remover(i.next());
					}
				}



				if (raUnidadeHelper != null) {
					Iterator i = raUnidadeHelper.iterator();

					while (i.hasNext()) {
						getControladorUtil().remover(i.next());
					}
				}

				if (tramiteHelper != null) {
					Iterator i = tramiteHelper.iterator();

					while (i.hasNext()) {
						getControladorUtil().remover(i.next());
					}
				}

				if (rasHelper != null) {
					Iterator i = rasHelper.iterator();

					while (i.hasNext()) {
						getControladorUtil().remover(i.next());
					}
				}

				getControladorUtil().remover(registroAtendimento);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public Collection<OrdemServicoUnidade> getOrdemServicoUnidade(Integer numeroOS) throws Exception {
		FiltroOrdemServicoUnidade filtroOrdemServicoUnidade = new FiltroOrdemServicoUnidade();
		filtroOrdemServicoUnidade.adicionarParametro(new ParametroSimples(FiltroOrdemServicoUnidade.ORDEM_SERVICO_ID, numeroOS));

		Collection<OrdemServicoUnidade> colecaoOrdemServicoUnidade = getControladorUtil().pesquisar(filtroOrdemServicoUnidade, OrdemServicoUnidade.class.getName());

		if (colecaoOrdemServicoUnidade.isEmpty()) {
			return null;
		}

		return colecaoOrdemServicoUnidade;


	}



	@SuppressWarnings("unchecked")
	public Collection<RegistroAtendimento> getRegistroAtendimento(Integer idRA) {
		try {
			FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
			filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID, idRA));

			Collection<RegistroAtendimento> colecaoRegistroAtendimento = getControladorUtil().pesquisar(filtroRegistroAtendimento, RegistroAtendimento.class.getName());

			if (colecaoRegistroAtendimento.isEmpty()) {
				return null;
			}

			return colecaoRegistroAtendimento;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public Collection<RegistroAtendimentoUnidade> getRAUnidade(Integer numeroRA) {
		try {
			FiltroRegistroAtendimentoUnidade filtroRegistroAtendimentoUnidade = new FiltroRegistroAtendimentoUnidade();
			filtroRegistroAtendimentoUnidade.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimentoSolicitante.REGISTRO_ATENDIMENTO_ID, numeroRA));

			Collection<RegistroAtendimentoUnidade> colecaoRegistroAtendimentoUnidade = getControladorUtil().pesquisar(filtroRegistroAtendimentoUnidade, RegistroAtendimentoUnidade.class.getName());

			if (colecaoRegistroAtendimentoUnidade.isEmpty()) {
				return null;
			}

			return colecaoRegistroAtendimentoUnidade;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private void excluirClientes() throws ControladorException, ErroRepositorioException {
		Collection<IClienteImovel> clientesImovelExcluirRelacao = this.obterClientesParaExcluirRelacao();

		for (IClienteImovel clienteImovel : clientesImovelExcluirRelacao) {
			if (!isImovelEmCampo(clienteImovel.getImovel().getId())) {
				ICliente cliente = getControladorCliente().pesquisarCliente(clienteImovel.getCliente().getId());
				Imovel imovel = getControladorImovel().pesquisarImovel(clienteImovel.getImovel().getId());

				Integer idSetorComercial = imovel.getSetorComercia().getId();

				String protocoloAtendimento = getControladorRegistroAtendimento().obterProtocoloAtendimento();

				Integer idUsuarioAprovacao = repositorioSeguranca.pesquisarIdUsuarioAutorizadorImoveis(imovel.getId());

				UnidadeOrganizacional unidade = getControladorCadastro().obterUnidadePorLocalidade(imovel.getIdLocalidade());
				
				RADadosGeraisHelper raDadosGeraisHelper = RABuilder.buildRADadosGeraisAtualizacaoCadastralExclusaoCLiente(imovel, cliente, clienteImovel, AlteracaoTipo.EXCLUSAO, protocoloAtendimento, idUsuarioAprovacao);
				RALocalOcorrenciaHelper raLocalOcorrenciaHelper = RABuilder.buildRALocalOcorrenciaAtualizacaoCadastral(imovel, idSetorComercial, AlteracaoTipo.EXCLUSAO, unidade);
				RASolicitanteHelper raSolicitanteHelper = RABuilder.buildRASolicitanteAtualizacaoCadastral();

				Integer[] retorno = getControladorRegistroAtendimento().inserirRegistroAtendimento(raDadosGeraisHelper, raLocalOcorrenciaHelper, raSolicitanteHelper);

				listaRAParaExclusao.add(retorno[0]);
			}

		}
	}

	private void aprovarImoveis(Collection<IImovel> imoveisParaAprovar, Usuario usuarioLogado) throws ControladorException {
		try {

			for(IImovel imovel : imoveisParaAprovar) {
				Collection<TabelaAtualizacaoCadastral> colecaoTabelaAtualizacaoCadastral = repositorioSeguranca.pesquisaTabelaAtualizacaoCadastralPorImovel(imovel.getIdImovel());
				for(TabelaAtualizacaoCadastral tabela : colecaoTabelaAtualizacaoCadastral) {
					Collection<TabelaColunaAtualizacaoCadastral> colecaoTabelaColuna = repositorioSeguranca.pesquisaTabelaColunaAtualizacaoCadastral(tabela.getId());
					for(TabelaColunaAtualizacaoCadastral tabelaColuna : colecaoTabelaColuna ) {
						this.validarSubcategoria(imovel.getIdImovel(), tabelaColuna);
						
						tabelaColuna.setUsuario(usuarioLogado);
						getControladorUtil().atualizar(tabelaColuna);
					}
				}
				this.aprovarImovel(imovel.getIdImovel());
				
			}
		} catch (Exception e) {
			logger.error("Erro ao aprovar imoveis em lote. " + e);
			throw new ControladorException("Erro ao aprovar imoveis em lote.", e);
		}
	}
	
	public void aprovarImovel(Integer idImovel) throws ControladorException {
			try {
				ImovelControleAtualizacaoCadastral controle = this.obterImovelControle(idImovel);

				Date dataLiberacao = new Date();
				SistemaParametro parametros = getControladorUtil().pesquisarParametrosDoSistema();
				
				if (isImovelComAlteracaoFaturamento(idImovel)) {
					if (getControladorImovel().isImovelHidrometrado(idImovel))
						dataLiberacao = Util.adicionarNumeroDiasDeUmaData(dataLiberacao, parametros.getQuantidadeDiasLiberacaoProcessamento());
					else
						dataLiberacao = Util.adicionarNumeroDiasDeUmaData(dataLiberacao, 400);
					
					getControladorUtil().inserir(new ComunicadoEmitirConta(idImovel, 
																		getControladorImovel().pesquisarGrupoImovel(idImovel).getAnoMesReferencia(), 
																		ComunicadoEmitirConta.ALTERACAO_CADASTRAL));
					
					if (!controle.isAprovado())
						controle.setSituacaoAtualizacaoCadastral(new SituacaoAtualizacaoCadastral(SituacaoAtualizacaoCadastral.APROVADO));
					
				} else {
					if (!controle.isAprovado())
						controle.setSituacaoAtualizacaoCadastral(new SituacaoAtualizacaoCadastral(SituacaoAtualizacaoCadastral.APROVADO));
					else
						dataLiberacao = controle.getDataAprovacao();
				}
				
				if (controle.getDataAprovacao() == null)
					controle.setDataAprovacao(new Date());
				
				controle.setDataLiberacaoProcessamento(dataLiberacao);

				getControladorUtil().atualizar(controle);
			} catch (Exception e) {
				logger.error("Erro ao aprovar imovel " + idImovel);
				throw new ControladorException("Erro ao aprovar imovel " + idImovel, e);
			}
		
	}
	
	private void validarSubcategoria(Integer idImovelRetorno, TabelaColunaAtualizacaoCadastral tabelaColuna) {
		try {
			
			if (!existeSubcategoriaRetorno(idImovelRetorno))
				if (tabelaColuna.isTipoColuna(Tabela.IMOVEL_SUBCATEGORIA_ATUALIZACAO_CADASTRAL.intValue()) && tabelaColuna.possuiValorPreAprovado()) {
					ImovelSubcategoriaRetorno subcategoria = criarImovelSubcategoriaRetorno(tabelaColuna, idImovelRetorno, tabelaColuna.getColunaValorPreAprovado());
					getControladorUtil().inserir(subcategoria);
				}

		} catch (ControladorException e) {
			e.printStackTrace();
		}
	}
	
	private ImovelSubcategoriaRetorno criarImovelSubcategoriaRetorno(TabelaColunaAtualizacaoCadastral tabelaColuna, Integer idImovelRetorno, String qtdEconomias) {
		ImovelSubcategoriaRetorno imovelSubcategoriaRetorno = new ImovelSubcategoriaRetorno();
		
		imovelSubcategoriaRetorno.setImovel(new Imovel(tabelaColuna.getTabelaAtualizacaoCadastral().getCodigoImovel()));
		imovelSubcategoriaRetorno.setSubcategoria(new Subcategoria(Subcategoria.obterIdSubcategoria(tabelaColuna.getTabelaAtualizacaoCadastral().getComplemento())));
		imovelSubcategoriaRetorno.setQuantidadeEconomias(Short.valueOf(qtdEconomias));
		imovelSubcategoriaRetorno.setUltimaAlteracao(new Date());
		imovelSubcategoriaRetorno.setIdImovelRetorno(idImovelRetorno);
		
		return imovelSubcategoriaRetorno;
	}
	
	private boolean existeSubcategoriaRetorno(Integer idImovel) throws ControladorException {
		List<ImovelSubcategoriaRetorno> subcategorias;
		try {
			subcategorias = repositorioAtualizacaoCadastral.pesquisarImovelSubcategoriaRetornoPorIdImovel(idImovel);
			return subcategorias != null && !subcategorias.isEmpty();
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private Collection<IImovel> converterListaEmImovelRetorno(Collection<ConsultarMovimentoAtualizacaoCadastralHelper> listaImoveis) throws ErroRepositorioException {
		Collection<IImovel> listaImoveisRetorno = new ArrayList<IImovel>();

		for (ConsultarMovimentoAtualizacaoCadastralHelper helper : listaImoveis) {
			IImovel imovelRetorno = repositorioAtualizacaoCadastral.pesquisarImovelRetorno(helper.getIdImovel());

			if (imovelRetorno != null)
				listaImoveisRetorno.add(imovelRetorno);
		}
		return listaImoveisRetorno;
	}

	public Integer obterquantidadeImoveisAprovadosArquivo(Integer idArquivoAtualizacaoCadastral) throws ControladorException {
		return null;
	}

	public HashMap<String, Integer> obterDadosAnaliseSituacaoArquivoAtualizacaoCadastral(Integer idArquivo) throws ControladorException {
		HashMap<String, Integer> mapDadosAnalise = new HashMap<String, Integer>();

		try {

			ArquivoTextoAtualizacaoCadastral arquivo = getControladorCadastro().pesquisarArquivoTextoAtualizacaoCadastro(idArquivo);

			mapDadosAnalise.put(ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm.TOTAL_IMOVEIS, arquivo.getQuantidadeImovel());
			mapDadosAnalise.put(ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm.TRANSMITIDOS, (arquivo.getQuantidadeImoveisTransmitidos() != null ? arquivo.getQuantidadeImoveisTransmitidos() : 0));
			mapDadosAnalise.put(ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm.APROVADOS, repositorioAtualizacaoCadastral.obterquantidadeImoveisAprovadosArquivo(idArquivo));
			mapDadosAnalise.put(ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm.ANORMALIDADE, repositorioAtualizacaoCadastral.obterquantidadeImoveisComAnormalidadeArquivo(idArquivo));
			mapDadosAnalise.put(ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm.ALTERACAO_HIDROMETRO, repositorioAtualizacaoCadastral.obterquantidadeImoveisComAlteracaoFaturamentoArquivo(idArquivo, TabelaColuna.NOME_COLUNA_NUMERO_HUDROMETRO));
			mapDadosAnalise.put(ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm.ALTERACAO_LIGACAO_AGUA, repositorioAtualizacaoCadastral.obterquantidadeImoveisComAlteracaoFaturamentoArquivo(idArquivo, TabelaColuna.NOME_COLUNA_AGUA));
			mapDadosAnalise.put(ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm.ALTERACAO_LIGACAO_ESGOTO, repositorioAtualizacaoCadastral.obterquantidadeImoveisComAlteracaoFaturamentoArquivo(idArquivo, TabelaColuna.NOME_COLUNA_ESGOTO));
			mapDadosAnalise.put(ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm.ALTERACAO_CATEGORIA_SUB_ECONOMIAS, repositorioAtualizacaoCadastral.obterquantidadeImoveisComAlteracaoFaturamentoArquivo(idArquivo, TabelaColuna.NOME_COLUNA_ECONOMIAS));
		} catch (Exception e) {
			logger.error("Erro ao obter dados para analise do aquivo." + e);
			throw new ControladorException("Erro ao obter dados para analise do aquivo.", e);
		}
		return mapDadosAnalise;
	}

	public void fiscalizarImovel(Integer idImovel) throws ControladorException {
		try {

			ImovelControleAtualizacaoCadastral controle = repositorioAtualizacaoCadastral.pesquisarImovelControleAtualizacao(idImovel);

			if (controle != null){
				controle.setSituacaoAtualizacaoCadastral(new SituacaoAtualizacaoCadastral(
						SituacaoAtualizacaoCadastral.EM_FISCALIZACAO));
				this.getControladorUtil().atualizar(controle);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	@SuppressWarnings("rawtypes")
	public Collection<RelatorioFichaFiscalizacaoCadastralHelper> pesquisarDadosFichaFiscalizacaoCadastral(List<Integer> listaIdImoveis, boolean dadosOriginais) throws ControladorException {

		Collection<RelatorioFichaFiscalizacaoCadastralHelper> retorno = new ArrayList<RelatorioFichaFiscalizacaoCadastralHelper>();

		try {
			Collection colecaoDadosFicha = null; 
			
			if (dadosOriginais)
				colecaoDadosFicha = repositorioAtualizacaoCadastral.pesquisarDadosOriginaisFichaFiscalizacaoCadastral(listaIdImoveis);
			else
				colecaoDadosFicha = repositorioAtualizacaoCadastral.pesquisarDadosRetornoFichaFiscalizacaoCadastral(listaIdImoveis);
			
			if (colecaoDadosFicha != null && !colecaoDadosFicha.isEmpty()) {
				Iterator iterator = colecaoDadosFicha.iterator();

				while (iterator.hasNext()) {
					RelatorioFichaFiscalizacaoCadastralHelper helper = new RelatorioFichaFiscalizacaoCadastralHelper();
					Object[] objeto = (Object[]) iterator.next();

					helper.setIdImovel(dadosOriginais? (Integer) objeto[0]: null);
					helper.setNomeLocalidade((String) objeto[1]);
					helper.setCodigoSetor((Integer) objeto[2]);
					helper.setNumeroQuadra((Integer) objeto[3]);
					helper.setNumeroLote(dadosOriginais? (Integer) objeto[4]: null);
					helper.setNumeroSublote(dadosOriginais? (Integer) objeto[5]: null);
					helper.setDescricaoLogradouroImovel((String) objeto[6]);
					helper.setIdLogradouroImovel((Integer) objeto[7]);
					helper.setNumeroImovel((String) objeto[8]);
					helper.setComplementoEnderecoImovel((String) objeto[9]);
					helper.setBairroImovel((String) objeto[10]);
					helper.setCepImovel((Integer) objeto[11]);
					helper.setCodigoRota((Integer) objeto[12]);
					helper.setNumeroFace((Integer) objeto[13]);
					helper.setNomeMunicipioImovel((String) objeto[14]);
					helper.setIdMunicipioImovel((Integer) objeto[15]);
					helper.setIdCliente(dadosOriginais? (Integer) objeto[16] : null);
					helper.setNomeCliente((String) objeto[17]);
					helper.setCpfCnpj((String) objeto[18]);
					helper.setRg((String) objeto[19]);
					helper.setUf((String) objeto[20]);
					helper.setSexo((Integer) objeto[21]);
					helper.setDescricaoLogradouroCliente((String) objeto[22]);
					helper.setNumeroImovelCliente((String) objeto[23]);
					helper.setEnderecoTipoCliente((Integer) objeto[24]);
					helper.setNomeMunicipioCliente((String) objeto[25]);
					helper.setComplementoEnderecoCliente((String) objeto[26]);
					helper.setBairroCliente((String) objeto[27]);
					helper.setCepCliente((Integer) objeto[28]);
					helper.setEmailCliente((String) objeto[29]);
					helper.setDdd((String) objeto[30]);
					helper.setTelefone((String) objeto[31]);
					helper.setCelular((String) objeto[32]);
					
					if (!dadosOriginais) {
						helper.setContratoEnergia((String) objeto[33]);
						helper.setFonteAbastecimento((Integer) objeto[34]);
						helper.setLigacaoAguaSituacao((Integer) objeto[35]);
						helper.setLigacaoEsgotoSituacao((Integer) objeto[36]);
						helper.setNumeroHidrometro((String) objeto[37]);
						helper.setHidrometroCapacidade((String) objeto[38]);
						helper.setHidrometroProtecao((Integer) objeto[39]);
						helper.setHidrometroMarca((String) objeto[40]);
						helper.setOutrasInformacoes((String) objeto[41]);
						helper.setAreaConstruida((BigDecimal) objeto[42]);
						helper.setPontosUtilizacao((Integer) objeto[43]);
						helper.setMoradores((Integer) objeto[44]);
						
						helper.setSubcategorias(repositorioAtualizacaoCadastral.pesquisarDadosSubcategoriaRetornoFichaFiscalizacaoCadastral((Integer) objeto[0]));
					}

					retorno.add(helper);
				}
			}

			return retorno;
		} catch(ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	public ImovelControleAtualizacaoCadastral pesquisarImovelControleAtualizacao(Integer idImovel) throws ControladorException {
		try {
			return repositorioAtualizacaoCadastral.pesquisarImovelControleAtualizacao(idImovel);
		} catch (Exception e) {
			throw new ControladorException("Erro ao pesquisar ImovelControleAtualizacaoCadastral.", e);
		}
	}

	@SuppressWarnings("rawtypes")
	public Collection<RelatorioRelacaoImoveisRotaBean> pesquisarDadosRelatorioRelacaoImoveisRotaAtualizacaoCadastral(String idLocalidade, String cdSetorComercial, String cdRota)
			throws ControladorException {

		Collection<RelatorioRelacaoImoveisRotaBean> retorno = new ArrayList<RelatorioRelacaoImoveisRotaBean>();

		try {
			Collection colecaoImoveisRetorno = repositorioAtualizacaoCadastral.pesquisarDadosImoveisPorRotaAtualizacaoCadastral(
					idLocalidade, cdSetorComercial, cdRota);

			if (colecaoImoveisRetorno != null && !colecaoImoveisRetorno.isEmpty()) {
				Iterator iterator = colecaoImoveisRetorno.iterator();

				while (iterator.hasNext()) {
					RelatorioRelacaoImoveisRotaBean bean = new RelatorioRelacaoImoveisRotaBean();
					Object[] objeto = (Object[]) iterator.next();

					String idImovel = objeto[0].toString();
					Integer idImovelRetorno = (Integer) objeto[10];

					Integer tipoOperacao = (Integer) objeto[9];
					if (tipoOperacao.equals(AlteracaoTipo.INCLUSAO)) {
						bean.setIdImovel("NOVO");
						bean.setDescCategorias(this.getCategoriasImovelRetornoRelatorioPorRota(idImovelRetorno));

					} else if (tipoOperacao.equals(AlteracaoTipo.ALTERACAO)) {
						bean.setIdImovel(idImovel);
						bean.setDescCategorias(this.getCategoriasImovelRetornoRelatorioPorRota(idImovelRetorno));

					} else if (tipoOperacao.equals(AlteracaoTipo.EXCLUSAO)) {
						bean.setIdImovel(idImovel + " - EXCLUï¿½DO");
						bean.setDescCategorias(this.getCategoriasImovelRetornoRelatorioPorRota(idImovelRetorno));

					} else if (tipoOperacao.equals(new Integer(0))) {
						bean.setIdImovel(idImovel);
						bean.setDescCategorias(this.getCategoriasImovelRelatorioPorRota(Integer.valueOf(idImovel)));
					}

					bean.setIdLocalidade(objeto[1].toString());
					bean.setNomeLocalidade(objeto[2].toString());
					bean.setCodigoSetorComercial(objeto[3].toString());
					bean.setNumQuadra(objeto[4].toString());
					bean.setNumLote(objeto[5].toString());
					bean.setNumSubLote(objeto[6].toString());
					bean.setDescSituacaoLigacaoAgua(objeto[7].toString());
					bean.setDescSituacaoImovelRecadastramento(objeto[8].toString());

					retorno.add(bean);
				}
			}

			return retorno;
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	@SuppressWarnings("rawtypes")
	private String getCategoriasImovelRelatorioPorRota(Integer idImovel) throws ControladorException {
		String categorias = "";

		Collection colecaoCategorias = getControladorImovel().pesquisarCategoriasImovel(idImovel);
		for (Iterator iteratorCategorias = colecaoCategorias.iterator(); iteratorCategorias.hasNext();) {
			ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria) iteratorCategorias.next();

			String descCategoria = imovelSubcategoria.getComp_id().getSubcategoria().getCategoria().getDescricao();

			if (!categorias.contains(descCategoria))
				categorias += descCategoria + "\n";

			if (!iteratorCategorias.hasNext()) {
				int index = categorias.lastIndexOf("\n");
				categorias = categorias.substring(0, index);
			}
		}

		return categorias;
	}

	@SuppressWarnings("rawtypes")
	private String getCategoriasImovelRetornoRelatorioPorRota(Integer idImovelRetorno) {
		String categorias = "";

		Collection<ImovelSubcategoriaRetorno> subcategorias;
		try {
			subcategorias = repositorioAtualizacaoCadastral.pesquisarSubcategoriasImovelRetorno(idImovelRetorno);

			for (Iterator iterator = subcategorias.iterator(); iterator.hasNext();) {
				ImovelSubcategoriaRetorno subcategoria = (ImovelSubcategoriaRetorno) iterator.next();

				String descCategoria = subcategoria.getSubcategoria().getCategoria().getDescricao();

				if (!categorias.contains(descCategoria))
					categorias += descCategoria + "\n";

				if (!iterator.hasNext()) {
					int index = categorias.lastIndexOf("\n");
					categorias = categorias.substring(0, index);
				}
			}
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}

		return categorias;
	}

	private void atualizarImovelPerfil(Integer idImovel, Collection<IImovelSubcategoria> subcategoriasRetorno) throws ControladorException {
		ImovelPerfil perfil = getControladorImovel().obterImovelPerfil(idImovel);

		if (perfil.getId().equals(ImovelPerfil.TARIFA_SOCIAL)
				&& subcategoriasRetorno != null && !subcategoriasRetorno.isEmpty()) {

			Imovel imovel = getControladorImovel().pesquisarImovel(idImovel);
			imovel.setImovelPerfil(new ImovelPerfil(ImovelPerfil.NORMAL));
			getControladorUtil().atualizar(imovel);
		}
	}

	public boolean verificarPermissaoAprovarImovel(Integer idUsuarioLogado, Integer idImovel) {
		boolean temPermissao = true;
		try {
			Integer idUsuarioAprovacao = repositorioSeguranca.pesquisarIdUsuarioAutorizadorImoveis(idImovel);
			if (idUsuarioAprovacao != null && idUsuarioAprovacao.intValue() != idUsuarioLogado.intValue())
				temPermissao = false;

		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
		return temPermissao;
	}

	public Collection<ImovelTipoOcupanteQuantidadeAtualizacaoCadastral> pesquisarOcupantesAtualizacaoCadastral(Integer idImovel) throws ControladorException{
	    try {
	        return repositorioAtualizacaoCadastral.pesquisarOcupantesAtualizacaoCadastral(idImovel);
        } catch (Exception e) {
            throw new ControladorException("Erro ao recuperar os tipos de ocupantes", e);
        }
	}

	public List<ArquivoTextoAtualizacaoCadastral> regerarArquivosAtualizacaoCadastral(List<Integer> idsArquivos, String tipoArquivo, 
			Date dataUltimaTransmissao, Integer idEmpresa) throws ControladorException {
		try {
			List<ArquivoTextoAtualizacaoCadastral> arquivos = new ArrayList<ArquivoTextoAtualizacaoCadastral>();
			for (Integer idArquivo : idsArquivos) {
				List<ImovelControleAtualizacaoCadastral> imoveisControle = 
						repositorioAtualizacaoCadastral.obterImoveisPorArquivoSituacaoLoteTrazendoInformativos(idArquivo, definirSituacaoCadastral(tipoArquivo), dataUltimaTransmissao);

				if (CollectionUtil.naoEhVazia(imoveisControle)) {
					arquivos.add(getControladorCadastro().regerarArquivoTextoAtualizacaoCadastralComObjetos(imoveisControle, idArquivo, tipoArquivo, idEmpresa));
				}
			}
			return arquivos;
		} catch (Exception e) {
			throw new ControladorException("Erro ao regerar Arquivo de Atualizacao Cadastral", e);
		}
	}
	
	private List<Integer> definirSituacaoCadastral(String tipoArquivo) {
		if (tipoArquivo.equals(ArquivoTextoAtualizacaoCadastral.TIPO_ARQUIVO_REVISITA)) {
			return Arrays.asList(SituacaoAtualizacaoCadastral.TRANSMITIDO, SituacaoAtualizacaoCadastral.REVISITA);
		}
		return Arrays.asList(SituacaoAtualizacaoCadastral.EM_CAMPO);
	}
	
	public List<ArquivoTextoAtualizacaoCadastral> gerarArquivosRevisaoAtualizacaoCadastral(List<Integer> idsArquivos, double percentualAleatorios, Integer idEmpresa) throws ControladorException {
		try {
			Integer situacao = SituacaoAtualizacaoCadastral.EM_REVISAO;
			List<ArquivoTextoAtualizacaoCadastral> arquivos = new ArrayList<ArquivoTextoAtualizacaoCadastral>();
			for (Integer idArquivo : idsArquivos) {
				List<Integer> imoveis = repositorioAtualizacaoCadastral.obterImoveisPorSituacao(idArquivo, situacao);
				List<Integer> sorteados = this.sortearImoveis(idArquivo, SituacaoAtualizacaoCadastral.TRANSMITIDO, percentualAleatorios, null);

				ArquivoTextoAtualizacaoCadastral arquivo = obterArquivoComImoveisSorteados(imoveis, sorteados, idArquivo, 
						ArquivoTextoAtualizacaoCadastral.TIPO_ARQUIVO_REVISAO, situacao, idEmpresa);
				
				if (arquivo != null)
					arquivos.add(arquivo);
			}

			return arquivos;
		} catch (Exception e) {
			throw new ControladorException("erro.gerar.arquivo.atualizacao.cadastral.revisao", e);
		}
	}
	
	public List<ArquivoTextoAtualizacaoCadastral> gerarArquivosFiscalizacaoAtualizacaoCadastral(List<Integer> idsArquivos, 
			double percentualAleatorios, Integer lote, Integer idEmpresa) throws ControladorException {
		try {
			Integer situacao = SituacaoAtualizacaoCadastral.EM_FISCALIZACAO;
			List<ArquivoTextoAtualizacaoCadastral> arquivos = new ArrayList<ArquivoTextoAtualizacaoCadastral>();
			
			if (CollectionUtil.ehVazia(idsArquivos)) {
				idsArquivos = repositorioAtualizacaoCadastral.obterIdsArquivosPorLote(lote);
			}
			
			for (Integer idArquivo : idsArquivos) {
				List<Integer> imoveis = repositorioAtualizacaoCadastral.obterImoveisPorSituacaoELote(idArquivo, situacao, lote);
				List<Integer> sorteados = this.sortearImoveis(idArquivo, SituacaoAtualizacaoCadastral.PRE_APROVADO, percentualAleatorios, lote);
				
				ArquivoTextoAtualizacaoCadastral arquivo = obterArquivoComImoveisSorteados(imoveis, sorteados, idArquivo, 
						ArquivoTextoAtualizacaoCadastral.TIPO_ARQUIVO_FISCALIZACAO, situacao, idEmpresa);
				
				if (arquivo != null) {
					arquivos.add(arquivo);
				}
			}

			return arquivos;
		} catch (Exception e) {
			throw new ControladorException("erro.gerar.arquivo.atualizacao.cadastral.fiscalizacao", e);
		}
	}
	
	private ArquivoTextoAtualizacaoCadastral obterArquivoComImoveisSorteados(List<Integer> imoveis, List<Integer> sorteados, 
			Integer idArquivo, String tipoArquivo, Integer situacao, Integer idEmpresa) throws ControladorException {
		try {
			ArquivoTextoAtualizacaoCadastral arquivo = null;
			
			imoveis.addAll(sorteados);
			
			if (CollectionUtil.naoEhVazia(imoveis)) {
				arquivo = getControladorCadastro().regerarArquivoTextoAtualizacaoCadastral(imoveis, idArquivo, tipoArquivo, idEmpresa);
				
				if (CollectionUtil.naoEhVazia(sorteados)) {
					repositorioAtualizacaoCadastral.atualizarSituacaoConjuntoImovelControle(situacao, sorteados);
				}
			}
			return arquivo;
		} catch (Exception e) {
			throw new ControladorException("erro.gerar.arquivo.atualizacao.cadastral", e);
		}
	}
	
	public List<Integer> obterImoveisParaRegerarArquivo(Integer idArquivo, String tipoArquivo, Date dataUltimaTransmissao) throws ControladorException {
		try {
			if (tipoArquivo.equals(ArquivoTextoAtualizacaoCadastral.TIPO_ARQUIVO_REVISITA)) {
				return obterImoveisParaRevisita(idArquivo, dataUltimaTransmissao);
			} else {
				return repositorioAtualizacaoCadastral.obterImoveisPorSituacao(idArquivo, SituacaoAtualizacaoCadastral.EM_CAMPO);
			}
		} catch (Exception e) {
			throw new ControladorException("erro.gerar.arquivo.atualizacao.cadastral", e);
		}
	}

	private List<Integer> obterImoveisParaRevisita(Integer idArquivo, Date dataUltimaTransmissao) throws ErroRepositorioException, ControladorException {
		List<Integer> imoveisParaRevisita = repositorioAtualizacaoCadastral.obterImoveisARevisitar(idArquivo, dataUltimaTransmissao);
		
		if (!imoveisParaRevisita.isEmpty()) {
			List<ImovelControleAtualizacaoCadastral> controles = repositorioAtualizacaoCadastral.obterImoveisControlePorImovel(imoveisParaRevisita);
			
			for (ImovelControleAtualizacaoCadastral controle : controles) {
				Integer quantidadeVisitas = this.obterQuantidadeDeVisitasPorImovelControle(controle);
				
				if (quantidadeVisitas >= Visita.QUANTIDADE_MAXIMA_SEM_PRE_AGENDAMENTO)
					imoveisParaRevisita.remove(controle.getImovel().getId());
			}
		}
		
		return imoveisParaRevisita;
	}

	private List<Integer> sortearImoveis(Integer idArquivo, Integer situacao, double percentual, Integer lote) throws ControladorException {
		List<Integer> sorteados = new ArrayList<Integer>();
		List<Integer> imoveis = new ArrayList<Integer>();
		try {
			if (lote == null) {
				imoveis = repositorioAtualizacaoCadastral.obterImoveisPorSituacao(idArquivo, situacao);
				Collections.shuffle(imoveis);
				sorteados = Util.sortear(imoveis, percentual);
			} else {
				int total = repositorioAtualizacaoCadastral.obterQuantidadeImoveisPorSituacaoELote(idArquivo, situacao, lote);
				int quantidade = Util.calcularQuantidadeFinalAPartirDoPercentual(total, percentual);
				sorteados = 
						repositorioAtualizacaoCadastral.obterImoveisPorIdArquivoESituacaoELoteParaSorteioComQuantidadeEAleatoriamente(
								idArquivo, situacao, lote, quantidade);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.gerar.arquivo.atualizacao.cadastral", e);
		}

		return sorteados;
	}
	
	public TabelaColunaAtualizacaoCadastral pesquisarTabelaColunaPorImovel(TabelaColuna coluna, Integer idImovel, String complemento) throws ControladorException {
		try {
			return repositorioAtualizacaoCadastral.obterTabelaColuna(coluna, idImovel, complemento);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.pesquisar.tabela.coluna.atualizacao.cadastral", e);
		}
	}
	
	public TabelaColunaAtualizacaoCadastral pesquisarTabelaColunaPorImovel(TabelaColuna coluna, Integer idImovel, String complemento, String complementoColuna) throws ControladorException {
		try {
			return repositorioAtualizacaoCadastral.obterTabelaColuna(coluna, idImovel, complemento, complementoColuna);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.pesquisar.tabela.coluna.atualizacao.cadastral", e);
		}
	}
	
	public List<TabelaColunaAtualizacaoCadastral> pesquisarTabelaColunasPorImovel(TabelaColuna coluna, Integer idImovel) throws ControladorException {
		try {
			return repositorioAtualizacaoCadastral.obterTabelaColunas(coluna, idImovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.pesquisar.tabela.coluna.atualizacao.cadastral", e);
		}
	}
	
	public TabelaAtualizacaoCadastral pesquisarTabelaPorImovel(Tabela tabela, Integer idImovel, String complemento) throws ControladorException {
		try {
			return repositorioAtualizacaoCadastral.obterTabela(tabela, idImovel, complemento);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.pesquisar.tabela.coluna.atualizacao.cadastral", e);
		}
	}
	
	public void atualizarImovelRetorno(TabelaColunaAtualizacaoCadastral tabelaColunaAtualizacaoCadastral, String campo) throws ControladorException {
		try {
			repositorioAtualizacaoCadastral.atualizarImovelRetorno(tabelaColunaAtualizacaoCadastral, campo);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.pesquisar.tabela.coluna.atualizacao.cadastral", e);
		}
	}
	
	public void atualizarImovelParaSituacaoEmCampoPorArquivo(Integer idArquivo) throws ControladorException {
		try {
			repositorioAtualizacaoCadastral.atualizarImovelParaSituacaoEmCampoPorArquivo(idArquivo);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("Erro atualizar situacao de imovel controle atualizacao cadastral por arquivo", e);
		}
	}
	
	public void atualizarSituacaoImovelControle(Integer idImovelControle, Integer idNovaSituacao) throws ControladorException {
		ImovelControleAtualizacaoCadastral imovelControle = pesquisarImovelControleAtualizacao(idImovelControle);
		imovelControle.setSituacaoAtualizacaoCadastral(new SituacaoAtualizacaoCadastral(idNovaSituacao));
		getControladorUtil().atualizar(imovelControle);
	}
	
	public boolean possuiInformacoesFiscalizacao(ImovelControleAtualizacaoCadastral imovelControle) throws ControladorException {
		try {
			return repositorioAtualizacaoCadastral.possuiInformacoesFiscalizacao(imovelControle);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("Erro atualizar pesquisar se o imóvel possui informações de fiscalização", e);
		}
	}
	
	public void atualizarRetornoPreAprovado(ImovelControleAtualizacaoCadastral imovelControle) throws ControladorException {
		try {
			Collection<TabelaColunaAtualizacaoCadastral> colunasAlteradas = repositorioAtualizacaoCadastral.obterColunasPreAprovadas(imovelControle);
			
			for (TabelaColunaAtualizacaoCadastral tabelaColuna : colunasAlteradas) {
				this.atualizarImovelRetorno(tabelaColuna, TabelaColunaAtualizacaoCadastral.VALOR_CAMPO_PRE_APROVADO);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("Erro atualizar retorno da atualizacao cadastral", e);
		}
	}

	public List<Visita> obterVisitasPorImovelControleECoordenadas(ImovelControleAtualizacaoCadastral imovelControle, String latitude, String longitude) throws ControladorException {
		try {
			return repositorioAtualizacaoCadastral.pesquisarVisitasPorImovelControleELatitudeELongitude(imovelControle, latitude, longitude);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("Erro buscar visitas do imovel com latitude e longitude", e);
		}
	}

	public void inserirImagemRetorno(Integer matricula, String nomeImagem, String pasta, Integer idImovelRetorno) throws ControladorException {
		String caminhoJboss = System.getProperty("jboss.server.home.dir");
				
		File imagem = new File(caminhoJboss + pasta, nomeImagem);

		ImagemRetorno imagemRetorno = new ImagemRetorno();
		imagemRetorno.setIdImovelRetorno(idImovelRetorno);

		if (matricula > 0) {
			imagemRetorno.setIdImovel(matricula);
		}

		imagemRetorno.setNomeImagem(imagem.getName());
		imagemRetorno.setPathImagem(pasta + "/" + nomeImagem);
		imagemRetorno.setUltimaAlteracao(new Date());
		imagemRetorno.setIndicadorImagemAtualizada(ConstantesSistema.NAO);

		getControladorUtil().inserir(imagemRetorno);
	}
	
	public Integer obterIdImovelRetorno(Integer idImovel) throws ControladorException {
		try {
			return repositorioAtualizacaoCadastral.obterIdImovelRetorno(idImovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("Erro ao obter id do imovel", e);
		}
	}
	
	public boolean isImovelParaRemover(ConsultarMovimentoAtualizacaoCadastralHelper item, FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro) throws ControladorException {
		
		try {
			if (isQuantidadeDeVisitasDiferente(item.getControle(), filtro.getQuantidadeVisitas()) ||
				isRemoverImovelFiltroCpfCnpj(filtro.getCpfCnpjCadastrado(), repositorioAtualizacaoCadastral.possuiClienteComCpfOuCnpjCadastrado(item.getIdImovel())) ||
				isRemoverImovelFiltroCpfCnpj(filtro.getCpfCnpjTransmitido(), repositorioAtualizacaoCadastral.possuiClienteComCpfOuCnpjTransmitido(item.getIdImovel())))
				
				return true;
			else
				return false;
			
		} catch (ErroRepositorioException e) {
			throw new ControladorException("Erro ao verificar imovel para remover", e);
		}	
	}
	
	private boolean isQuantidadeDeVisitasDiferente(ImovelControleAtualizacaoCadastral controle, int filtroQuantidadeVisitas) throws ControladorException {
		boolean diferente = false;

		if (filtroQuantidadeVisitas >= 0) {
			int visitas = getControladorAtualizacaoCadastral().obterQuantidadeDeVisitasPorImovelControle(controle);

			if (visitas != filtroQuantidadeVisitas) {
				diferente = true;
			}
		}

		return diferente;
	}

	private boolean isRemoverImovelFiltroCpfCnpj(int filtro, boolean possui) throws ControladorException {
		if (filtro != -1) {
			boolean sim = filtro == ConstantesSistema.SIM;
			
			if ((sim && possui) || (!sim && !possui))
				return false;
			else
				return true;
		} else {
			return false;
		}
	}
	
	public boolean existeSubcategoriaRetorno(Integer idImovel, Integer idSubcategoria)  throws ControladorException {
		try {
			return repositorioAtualizacaoCadastral.existeSubcategoriaRetorno(idImovel, idSubcategoria);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("Erro ao verificar se já existe a subcategoria do imóvel retorno: " + idImovel, e);
		}
	}
	
	public void atualizarSubcategoriaAoFiscalizar(Integer idImovel)  throws ControladorException {
		try {
			repositorioAtualizacaoCadastral.atualizarSubcategoriarAoFiscalizar(idImovel);
			repositorioAtualizacaoCadastral.atualizarSubcategoriarAoPreAprovar(idImovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("Erro ao atualizar subcategorias - fiscalização do imóvel: " + idImovel, e);
		}
	}
	
	public void atualizarSubcategoriarAoPreAprovar(Integer idImovel) throws ControladorException {
		try {
			repositorioAtualizacaoCadastral.atualizarSubcategoriarAoFiscalizar(idImovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("Erro ao atualizar subcategorias - preaprovacao do imóvel: " + idImovel, e);
		}
	}
	
     
    public List<ImovelControleAtualizacaoCadastral> obterIdsImovelControleGeracaoLote(FiltrarGerarLoteAtualizacaoCadastralActionHelper helper) throws ControladorException {
        try {
            return repositorioAtualizacaoCadastral.obterIdsImovelControleGeracaoLote(helper);
        } catch (ErroRepositorioException e) {
            throw new ControladorException("erro.lote.atualizacaocadastral.pesquisar.imoveis", e);
        }
    }
    
    public void gerarLote(List<ImovelControleAtualizacaoCadastral> imoveisControle, Integer lote) throws ControladorException {
        
        try {
            boolean existeLote = repositorioAtualizacaoCadastral.isLoteExistente(lote);
            
            if (existeLote) {
                throw new ControladorException("erro.lote.atualizacaocadastral.existente");
            } else {
                for (ImovelControleAtualizacaoCadastral controle : imoveisControle) {
                    controle.setLote(lote);
                    controle.setDataGeracaoLote(new Date());
                    
                    getControladorUtil().atualizar(controle);
                }
            }

        } catch (ErroRepositorioException e) {
            throw new ControladorException("erro.lote.atualizacaocadastral", e);
        }
        
    }
    
    public Integer obterProximoLote() throws ControladorException {
        try {
            return repositorioAtualizacaoCadastral.obterProximoLote();
        } catch (ErroRepositorioException e) {
            throw new ControladorException("erro.lote.atualizacaocadastral", e);
        }
    }
    
    public boolean isDefinicaoSubcategoriaValida(String idImovel,String[] registrosSelecionados) throws ControladorException {
    	ImovelControleAtualizacaoCadastral controle = this.pesquisarImovelControleAtualizacao(new Integer(idImovel));
    	
    	if (controle.isPreAprovado())
    		return isDefinicaoSubcategoriaValidaImovelPreAprovado(idImovel, registrosSelecionados);
    	else 
    		return isDefinicaoSubcategoriaValidaImovelEmFIscalizacao(idImovel, registrosSelecionados);
    }
    
    public boolean isDefinicaoSubcategoriaValidaImovelEmFIscalizacao(String idImovel,String[] registrosSelecionados) throws ControladorException {
        for (int i = 0; i < registrosSelecionados.length; i++) {

            Integer idAtualizacaoCadastral = new Integer(registrosSelecionados[i]);
            
            TabelaColunaAtualizacaoCadastral tabelaColuna = getControladorTransacao().pesquisarTabelaColunaAtualizacaoCadastral(idAtualizacaoCadastral);

            if (tabelaColuna.isTipoColuna(Tabela.IMOVEL_SUBCATEGORIA_ATUALIZACAO_CADASTRAL.intValue())) {
                if (tabelaColuna.possuiValorFiscalizado())
                    return true;
            }
        }
        
        if (existeSubcategoriaPreAprovada(new Integer(idImovel)))
            return true;
        else if (existeSubcategoriaSemAlteracao(new Integer(idImovel)))
            return true;
        
        return false;
    }
	
	public boolean isDefinicaoSubcategoriaValidaImovelPreAprovado(String idImovel,String[] registrosSelecionados) throws ControladorException {
    	for (int i = 0; i < registrosSelecionados.length; i++) {

			Integer idAtualizacaoCadastral = new Integer(registrosSelecionados[i]);
			
			TabelaColunaAtualizacaoCadastral tabelaColuna = getControladorTransacao().pesquisarTabelaColunaAtualizacaoCadastral(idAtualizacaoCadastral);

			if (tabelaColuna.isTipoColuna(Tabela.IMOVEL_SUBCATEGORIA_ATUALIZACAO_CADASTRAL.intValue()) 
					&& tabelaColuna.possuiValorPreAprovado())
				return true;
		}
    	
    	if (existeSubcategoriaSemAlteracao(new Integer(idImovel)))
    		return true;
    	
    	return false;
    }
    
	private boolean existeSubcategoriaPreAprovada(Integer idImovel) throws ControladorException{
    	boolean retorno = false;
    	
    	TabelaColuna tabelaColuna = new TabelaColuna();
    	
    	tabelaColuna.setTabela(new Tabela(Tabela.IMOVEL_SUBCATEGORIA_ATUALIZACAO_CADASTRAL));
    	tabelaColuna.setDescricaoColuna(TabelaColuna.NOME_COLUNA_ID_SUBCATEGORIA);
    	
		List<TabelaColunaAtualizacaoCadastral> colunas = this.pesquisarTabelaColunasPorImovel(tabelaColuna, idImovel);
		
		for (TabelaColunaAtualizacaoCadastral coluna : colunas) {
			if (coluna.getColunaValorPreAprovado() != null ) {
				Integer valor = new Integer(coluna.getColunaValorPreAprovado());
				
				if (valor > 0)
					retorno = true;
			}
		}

		return retorno;
    }
	
    private boolean existeSubcategoriaSemAlteracao(Integer idImovel) throws ControladorException{
    	boolean retorno = false;
    	
    	TabelaColuna coluna = new TabelaColuna();
    	
    	coluna.setTabela(new Tabela(Tabela.IMOVEL_SUBCATEGORIA_ATUALIZACAO_CADASTRAL));
    	coluna.setDescricaoColuna(TabelaColuna.NOME_COLUNA_ID_SUBCATEGORIA);
    	
    	try {
    		Collection<ImovelSubcategoriaAtualizacaoCadastral> subcategorias = this.pesquisarSubCategoriasAtualizacaoCadastral(idImovel);
			
			for (ImovelSubcategoriaAtualizacaoCadastral original : subcategorias) {
				TabelaColunaAtualizacaoCadastral tabelaColuna = this.pesquisarTabelaColunaPorImovel(coluna, idImovel, original.getComplemento());
				
				if (tabelaColuna == null)
					retorno = true;
			}
		} catch (ErroRepositorioException e) {
            throw new ControladorException("erro.verificar.subcategorias.originais", e);
		}
    	return retorno;
    }
    
    public Integer reprovarImoveisEmLote(Usuario usuarioLogado, Collection<ConsultarMovimentoAtualizacaoCadastralHelper> listaImoveis) throws ControladorException {
    	
    	List<Integer> imoveisParaReprovar = new ArrayList<Integer>();
		
		for (ConsultarMovimentoAtualizacaoCadastralHelper helper : listaImoveis){
			
			ImovelControleAtualizacaoCadastral controle = obterImovelControle(helper.getIdImovel());
			
			Integer quantidadeVisitas = this.obterQuantidadeDeVisitasPorImovelControle(controle);
			if (quantidadeVisitas < Visita.QUANTIDADE_MAXIMA_SEM_PRE_AGENDAMENTO && !controle.isAprovado())
				imoveisParaReprovar.add(controle.getId());					
		}
		if (!imoveisParaReprovar.isEmpty()) 
			this.reprovarImoveis(imoveisParaReprovar, usuarioLogado);
			
		return imoveisParaReprovar.size();
	}
	
	private void reprovarImoveis(List<Integer> imoveisParaReprovar, Usuario usuarioLogado) throws ControladorException {
		try {
			repositorioAtualizacaoCadastral.reprovarImoveis(imoveisParaReprovar);

		} catch (Exception e) {
			logger.error("Erro ao reprovar imoveis em lote. " + e);
			throw new ControladorException("Erro ao aprovar imoveis em lote.", e);
		}
	}
	
	public void copiarImagensRestantes() {
		@SuppressWarnings("rawtypes")
		Collection imagens = Fachada.getInstancia().pesquisar(new FiltroImovelImagem(), ImovelImagem.class.getName());
		
		try {
			@SuppressWarnings("unchecked")
			Iterator<ImovelImagem> itImagens = imagens.iterator();
			
			while (itImagens.hasNext()) {
				ImovelImagem imagem = itImagens.next();
				
				String caminhoJboss = System.getProperty("jboss.server.home.dir");
				File arquivo = new File(caminhoJboss, imagem.getCaminhoImagem());
				
				if (!arquivo.exists()) 
					getControladorUtil().remover(imagem);
			}
		} catch (ControladorException e) {
			e.printStackTrace();
		}
	}
	
	public ImovelRetorno pesquisarImovelRetornoPorIdRetorno(Integer idImovelRetorno) throws ControladorException {
		try {
			return repositorioAtualizacaoCadastral.pesquisarImovelRetornoPorIdRetorno(idImovelRetorno);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("Erro ao pesquisar imovel retortno.", e);
		}
	}
	
//	public void verificarAlteracoesSubcategorias(Integer idImovel) throws ControladorException {
//        try {
//            
//            FaturamentoGrupo grupo = getControladorImovel().pesquisarGrupoImovel(idImovel);
//            Integer referenciaFinal = null;
//            
//            boolean inserirSituacao = false;
//            
//            if (!getControladorFaturamento().isImovelEmsituacaoEspecialFaturamento(idImovel, grupo.getAnoMesReferencia())) {
//                
//                if (!getControladorImovel().isImovelHidrometrado(idImovel)) {
//                    
//                    if (houveMudancaCategoria(idImovel)) {
//                        inserirSituacao = true;
//                        referenciaFinal = Util.somaMesAnoMesReferencia(grupo.getAnoMesReferencia(), 2);
//                        
//                    } else if (houveMudancaoEconomiasPorCategoria(idImovel)){
//                        inserirSituacao = true;
//                        referenciaFinal = Util.somaMesAnoMesReferencia(grupo.getAnoMesReferencia(), 2);
//                        
//                    } else if (houveMudancaSubcategoria(idImovel)){
//                        inserirSituacao = true;
//                        referenciaFinal = Util.somaMesAnoMesReferencia(grupo.getAnoMesReferencia(), 80);
//                    }
//                    
//                }  else if (houveMudancaCategoria(idImovel) || houveMudancaoEconomiasPorCategoria(idImovel) || houveMudancaSubcategoria(idImovel)) {
//                    inserirSituacao = true;
//                    referenciaFinal = Util.somaMesAnoMesReferencia(grupo.getAnoMesReferencia(), 2);
//                }
//                
//                if (inserirSituacao ) {
//                    this.inserisSituacaoEspecialFaturamentoAlteracaoSubcategoria(idImovel, FaturamentoSituacaoTipo.FATURAR_MEDIA_RECADASTRAMENTO, grupo.getAnoMesReferencia(), referenciaFinal);
//                }
//            }
//        } catch (ErroRepositorioException e) {
//            e.printStackTrace();
//        }
//    }
	
	public boolean isImovelAprovadoComAlteracaoFaturamento(Integer idImovel) throws ControladorException {
		try {
			ImovelControleAtualizacaoCadastral controle = this.obterImovelControle(idImovel); 
			
			if (controle != null && (controle.isAprovado() && isImovelComAlteracaoFaturamento(idImovel)))
				return true;
			else
				return false;
		} catch (ControladorException e) {
			throw new ControladorException("Erro ao verificar se houve alteracao de faturamento no imóvel.", e);
		}
	}
	
	private boolean isImovelComAlteracaoFaturamento(Integer idImovel) throws ControladorException {
		try {
			ImovelControleAtualizacaoCadastral controle = this.obterImovelControle(idImovel);
			
			if (controle != null && (houveAlteracaoTabelaColunaSubcategoria(controle.getImovel().getId()) 
						&& (houveMudancaCategoria(controle.getImovel().getId()) 
							|| houveMudancaoEconomiasPorCategoria(controle.getImovel().getId()) 
							|| houveMudancaSubcategoria(controle.getImovel().getId()) ) ) )
				return true;
			else
				return false;
			
		} catch (ControladorException e) {
			throw new ControladorException("Erro ao verificar se houve alteracao de faturamento no imóvel.", e);
		}
	}
    
    private boolean houveAlteracaoTabelaColunaSubcategoria(Integer idImovel) {
        boolean alteracao = false;
        
        try {
            TabelaColuna coluna = new TabelaColuna();
            
            coluna.setTabela(new Tabela(Tabela.IMOVEL_SUBCATEGORIA_ATUALIZACAO_CADASTRAL));
            coluna.setDescricaoColuna(TabelaColuna.NOME_COLUNA_ID_SUBCATEGORIA);
            
            List<TabelaColunaAtualizacaoCadastral> colunas = this.pesquisarTabelaColunasPorImovel(coluna, idImovel);

            if (colunas != null && !colunas.isEmpty())
                alteracao = true;
        
        } catch (ControladorException e) {
            e.printStackTrace();
        }
        
        return alteracao;
    }
    
//    @SuppressWarnings({ "rawtypes", "unchecked" })
//    private void inserisSituacaoEspecialFaturamentoAlteracaoSubcategoria(Integer idImovel, Integer idFaturamentoSituacaoTipo, Integer anoMesReferenciaInicial, Integer anoMesReferenciaFinal) {
//        
//        Object[] dadosImoveis = new Object[]{idImovel, new Date()};
//        try {
//            
//            Collection<Object> imoveis = new ArrayList();
//            
//            imoveis.add(dadosImoveis);
//            
//            SituacaoEspecialFaturamentoHelper helper = new SituacaoEspecialFaturamentoHelper();
//            
//            helper.setIdImovel(idImovel.toString());
//            helper.setObservacao("ALTERACAO DE DADOS DE FATURAMENTO - ATUALIZACAO CADASTRAL");
//            helper.setObservacaoInforma("ALTERACAO DE DADOS DE FATURAMENTO - ATUALIZACAO CADASTRAL");
//            helper.setUsuarioLogado(Usuario.USUARIO_BATCH);
//            helper.setIdFaturamentoSituacaoMotivo(FaturamentoSituacaoMotivo.ATUALIZACAO_CADASTRAL_RECADASTRAMENTO.toString());
//            helper.setIdFaturamentoSituacaoTipo(idFaturamentoSituacaoTipo.toString());
//            helper.setNumeroConsumoAguaMedido(null);
//            helper.setNumeroConsumoAguaNaoMedido(this.obterConsumoFixadoSituacaoEspecialFaturamento(idImovel));
//            helper.setNumeroVolumeEsgotoMedido(null);
//            helper.setNumeroVolumeEsgotoNaoMedido(null);
//            helper.setQuantidadeDeImoveis("1");
//
//            getControladorFaturamento().inserirSituacaoEspecialFaturamento(helper, false, imoveis, idFaturamentoSituacaoTipo, anoMesReferenciaInicial, anoMesReferenciaFinal);
//        
//        } catch (ControladorException e) {
//            logger.error("ERRO AO INSERIR SITUACAO ESPECIAL FATURAMENTO - RECADASTRAMENTO [IMOVEL: " + idImovel + "]");
//            e.printStackTrace();
//        }
//    }
//    
//    private Integer obterConsumoFixadoSituacaoEspecialFaturamento(Integer idImovel) {
//        Integer consumo = 0;
//        try {
//            ImovelControleAtualizacaoCadastral controle = this.obterImovelControle(idImovel);
//            
//            Integer referenciaAnterior = Util.recuperaAnoMesDaData(controle.getDataProcessamento());
//
//            IConta conta = getControladorFaturamento().obterContaOuContaHistorico(idImovel, referenciaAnterior);
//
//            if (conta != null)
//                consumo = conta.getConsumoAgua();
//            else {
//            }
//            
//        } catch (ControladorException e) {
//            e.printStackTrace();
//        }
//        return consumo;
//    }
    
    public boolean houveMudancaCategoria(Integer idImovel) throws ControladorException {
        boolean retorno = false;
        try {
            List<ImovelSubcategoriaAtualizacaoCadastral> originais = this.obterSubcategoriasOriginais(idImovel);
            List<ImovelSubcategoriaRetorno> novas = (List<ImovelSubcategoriaRetorno>) this.obterSubcategoriasRetorno(idImovel);
            
            if (originais != null && novas != null) {
                
                List<Integer> idsOriginais = this.obterIdsCategoriasOriginais(originais);
                List<Integer> idsNovas = this.obterIdsCategoriasNovas(novas);
                
                for (Integer nova : idsNovas) {
                    if (!idsOriginais.contains(nova)) {
                        retorno =  true;
                    }
                }
            }
            
            return retorno;
        } catch (ControladorException e) {
            e.printStackTrace();
            throw new ControladorException("Erro ao inserir situação especial de faturamento para imóveis com alteração de subcategoria.", e);
        }
    }
    
    public String[] getDescricaoMudancaCategoria(Integer idImovel) throws ControladorException {
        String retorno[] = new String[]{"", ""};
        StringBuilder descricao = new StringBuilder();

        try {
            List<ImovelSubcategoriaAtualizacaoCadastral> originais = this.obterSubcategoriasOriginais(idImovel);
            List<ImovelSubcategoriaRetorno> novas = this.obterSubcategoriasRetorno(idImovel);
            
            if (originais != null && novas != null) {
                
                List<Integer> idsOriginais = this.obterIdsCategoriasOriginais(originais);
                
                for (Integer original : idsOriginais) {
                	descricao.append(Categoria.getNomeCategoria(original)).append(", ");
                }
                retorno[0] = Util.removerUltimosCaracteres(descricao.toString(), 2);
                
                descricao = new StringBuilder();
                List<Integer> idsNovas = this.obterIdsCategoriasNovas(novas);
                for (Integer nova : idsNovas) {
                	descricao.append(Categoria.getNomeCategoria(nova)).append(", ");
                }
                retorno[1] = Util.removerUltimosCaracteres(descricao.toString(), 2);
            }
            
            return retorno;
        } catch (ControladorException e) {
            e.printStackTrace();
            throw new ControladorException("Erro ao inserir situação especial de faturamento para imóveis com alteração de subcategoria.", e);
        }
    }
    
    public boolean houveMudancaoEconomiasPorCategoria(Integer idImovel) throws ControladorException {
        boolean retorno = false;
        try {
            
            List<ImovelSubcategoriaAtualizacaoCadastral> originais = this.obterSubcategoriasOriginais(idImovel);
            List<ImovelSubcategoriaRetorno> novas = (List<ImovelSubcategoriaRetorno>) this.obterSubcategoriasRetorno(idImovel);
            
            if (originais != null && novas != null) {
                
                if (originais.size() >= novas.size()) {
                    
                    for (ImovelSubcategoriaAtualizacaoCadastral original : originais) {
                        
                        Short qtdEconomiasNovas = getControladorImovel().pesquisarObterQuantidadeEconomias(new Imovel(idImovel), original.getCategoria());
                        
                        Short qtdEconomiasOriginais = this.pesquisarQuantidadeEconomiasOriginais(idImovel, original.getCategoria().getId());
                        
                        if (qtdEconomiasNovas == null || qtdEconomiasNovas.intValue() == 0) {
                            retorno = true;

                        } else if (qtdEconomiasNovas.intValue() != qtdEconomiasOriginais.intValue()) {
                            retorno = true;
                        }
                    }
                } else if (originais.size() < novas.size()) {
                    
                    for (ImovelSubcategoriaRetorno nova : novas) {
                        
                        Short qtdEconomiasNovas = getControladorImovel().pesquisarObterQuantidadeEconomias(new Imovel(idImovel), nova.getSubcategoria().getCategoria());
                        
                        Short qtdEconomiasOriginais = this.pesquisarQuantidadeEconomiasOriginais(idImovel, nova.getSubcategoria().getCategoria().getId());
                        
                        if (qtdEconomiasOriginais == null || qtdEconomiasOriginais.intValue() == 0) {
                            retorno = true;
                       
                        } else if (qtdEconomiasNovas.intValue() != qtdEconomiasOriginais.intValue()) {
                            retorno = true;
                        }
                    }
                }
                
            }
            return retorno;
        } catch (ControladorException e) {
            e.printStackTrace();
            throw new ControladorException("Erro ao inserir situação especial de faturamento para imóveis com alteração de subcategoria.", e);
        }
    }
    
    public String[] getDescricaoMudancaEconomiasPorSubcategoria(Integer idImovel) throws ControladorException {
        String retorno[] = new String[]{"", ""};
        StringBuilder descricao = new StringBuilder();

        try {
            List<ImovelSubcategoriaAtualizacaoCadastral> originais = this.obterSubcategoriasOriginais(idImovel);
            List<ImovelSubcategoria> novas = (List<ImovelSubcategoria>) getControladorImovel().pesquisarImovelSubcategorias(new Imovel(idImovel));
            
            if (originais != null && novas != null) {
                
                for (ImovelSubcategoriaAtualizacaoCadastral original : originais) {
                	Short qtdEconomiasOriginais = this.pesquisarQuantidadeEconomiasOriginais(idImovel, original.getCategoria().getId());
                	
                	descricao.append(Subcategoria.getDescricaoSubcategoria(original.getSubcategoria().getId())).append(" - ").append(qtdEconomiasOriginais).append(", ");
                }
                
                retorno[0] = Util.removerUltimosCaracteres(descricao.toString(), 2);
                
            	descricao = new StringBuilder();
                	
                for (ImovelSubcategoria nova : novas) {
                    
                    Short qtdEconomiasNovas = getControladorImovel().pesquisarObterQuantidadeEconomias(new Imovel(idImovel), nova.getSubcategoria().getCategoria());
                    
                    descricao.append(Subcategoria.getDescricaoSubcategoria(nova.getSubcategoria().getId())).append(" - ").append(qtdEconomiasNovas).append(", ");
                }
                    
                retorno[1] = Util.removerUltimosCaracteres(descricao.toString(), 2);
                
            }
            
            return retorno;
        } catch (ControladorException e) {
            e.printStackTrace();
            throw new ControladorException("Erro ao inserir situação especial de faturamento para imóveis com alteração de subcategoria.", e);
        }
    }
    
    public boolean houveMudancaSubcategoria(Integer idImovel) throws ControladorException {
        boolean retorno = false;
        try {
            List<ImovelSubcategoriaAtualizacaoCadastral> originais = this.obterSubcategoriasOriginais(idImovel);
            List<ImovelSubcategoriaRetorno> novas = (List<ImovelSubcategoriaRetorno>) this.obterSubcategoriasRetorno(idImovel);
            
            if (originais != null && novas != null) {
                
                if (originais.size() != novas.size()) {
                    retorno = true;
                    
                } else {
                    
                    List<Integer> idsOriginais = this.obterIdsSubcategoriasOriginais(originais);
                    
                    for (ImovelSubcategoriaRetorno nova : novas) {
                        if (!idsOriginais.contains(nova.getSubcategoria().getId())) {
                            retorno = true;
                        }
                    }
                }
            }
            
            return retorno;
        } catch (ControladorException e) {
            e.printStackTrace();
            throw new ControladorException("Erro ao inserir situação especial de faturamento para imóveis com alteração de subcategoria.", e);
        }
    }
    
    public String[] getDescricaoMudancaSubcategoria(Integer idImovel) throws ControladorException {
        String retorno[] = new String[]{"", ""};
        StringBuilder descricao = new StringBuilder();

        try {
            List<ImovelSubcategoriaAtualizacaoCadastral> originais = this.obterSubcategoriasOriginais(idImovel);
            List<ImovelSubcategoriaRetorno> novas = (List<ImovelSubcategoriaRetorno>) this.obterSubcategoriasRetorno(idImovel);
            
            if (originais != null && novas != null) {
                
            	List<Integer> idsOriginais = this.obterIdsSubcategoriasOriginais(originais);
                
                for (Integer original : idsOriginais) {
                	descricao.append(Subcategoria.getDescricaoSubcategoria(original) + ", ");
                }
                
                retorno[0] = Util.removerUltimosCaracteres(descricao.toString(), 2);
                
                descricao = new StringBuilder();
                List<Integer> idsNovas = this.obterIdsSubcategoriasNovas(novas);
                for (Integer nova : idsNovas) {
                	descricao.append(Subcategoria.getDescricaoSubcategoria(nova) + ", ");
                }
                
                retorno[1] = Util.removerUltimosCaracteres(descricao.toString(), 2);
            }
            
            return retorno;
        } catch (ControladorException e) {
            e.printStackTrace();
            throw new ControladorException("Erro ao inserir situação especial de faturamento para imóveis com alteração de subcategoria.", e);
        }
    }
    
    private List<Integer> obterIdsSubcategoriasOriginais(List<ImovelSubcategoriaAtualizacaoCadastral> imoveis) {
        List<Integer> subcategorias = new ArrayList<Integer>();
        
        for (ImovelSubcategoriaAtualizacaoCadastral subcategoria : imoveis) {
            subcategorias.add(subcategoria.getSubcategoria().getId());
        }
        
        return subcategorias;
    }
    
    private List<Integer> obterIdsSubcategoriasNovas(List<ImovelSubcategoriaRetorno> imoveis) {
        List<Integer> subcategorias = new ArrayList<Integer>();
        
        for (ImovelSubcategoriaRetorno subcategoria : imoveis) {
            subcategorias.add(subcategoria.getSubcategoria().getId());
        }
        
        return subcategorias;
    }
    
    private List<Integer> obterIdsCategoriasNovas(List<ImovelSubcategoriaRetorno> imoveis) {
        List<Integer> subcategorias = new ArrayList<Integer>();
        
        for (ImovelSubcategoriaRetorno subcategoria : imoveis) {
            subcategorias.add(subcategoria.getSubcategoria().getCategoria().getId());
        }
        
        return subcategorias;
    }
    
    private List<Integer> obterIdsCategoriasOriginais(List<ImovelSubcategoriaAtualizacaoCadastral> imoveis) {
        List<Integer> subcategorias = new ArrayList<Integer>();
        
        for (ImovelSubcategoriaAtualizacaoCadastral subcategoria : imoveis) {
            subcategorias.add(subcategoria.getSubcategoria().getCategoria().getId());
        }
        
        return subcategorias;
    }
    
//    private List<Integer> obterIdsSubcategoriasNovas(List<ImovelSubcategoria> imoveis) {
//        List<Integer> subcategorias = new ArrayList<Integer>();
//        
//        for (ImovelSubcategoria subcategoria : imoveis) {
//            subcategorias.add(subcategoria.getSubcategoria().getId());
//        }
//        
//        return subcategorias;
//    }
    
    public Short pesquisarQuantidadeEconomiasOriginais(Integer idImovel, Integer idCategoria) throws ControladorException {
        try {
            return repositorioAtualizacaoCadastral.pesquisarQuantidadeEconomiasOriginais(idImovel, idCategoria);
        } catch (ErroRepositorioException e) {
            throw new ControladorException("Erro obter quantidade de economias da categoria original do imovel - atualizacao cadastral", e);
        }
    }
    
    private List<ImovelSubcategoriaAtualizacaoCadastral> obterSubcategoriasOriginais(Integer idImovel) throws ControladorException {
        try {
            return repositorioAtualizacaoCadastral.obterSubcategoriasOriginais(idImovel);
        } catch (ErroRepositorioException e) {
            throw new ControladorException("Erro ao consultar subcategorias originais ", e);
        }
    }
    
    private List<ImovelSubcategoriaRetorno> obterSubcategoriasRetorno(Integer idImovel) throws ControladorException {
        try {
            return repositorioAtualizacaoCadastral.obterSubcategoriasRetorno(idImovel);
        } catch (ErroRepositorioException e) {
            throw new ControladorException("Erro ao consultar subcategorias retorno ", e);
        }
    }
    
    public boolean isAtualizadaoAntesFaturamento(Integer idImovel, Integer referenciaFaturamento) throws ControladorException {
    	boolean retorno = false;
    	try {
	    	ImovelControleAtualizacaoCadastral controle = this.pesquisarImovelControleAtualizacao(idImovel);
	    	
	    	if (controle.isAtualizado()) {
	    		Integer referenciaAtualizacao = Util.getAnoMesComoInteger(controle.getDataProcessamento());
		    		
	    		if (referenciaAtualizacao < referenciaFaturamento)
	    			retorno = true;
	    	} 
    	
    	} catch (ControladorException e) {
    		throw new ControladorException("Erro ao verificar data atualizacao ", e);
    	}
		return retorno;
    }
    
//    private void emitirComunicadosAprovacoesAntigas() throws ControladorException {
//    	Integer idImovel = null; 
//    	try {
//    		
//    		Collection<Integer> imoveisAprovados = repositorioAtualizacaoCadastral.pesquisarImoveisPorSituacaoPeriodo(
//    								SituacaoAtualizacaoCadastral.APROVADO, 
//    								Util.obterUltimaDataMes(01, 2018), 
//    								new Date());
//    		Iterator<Integer> itImoveis = imoveisAprovados.iterator();
//    		
//    		while (itImoveis.hasNext()) {
//                idImovel = itImoveis.next();
//                ComunicadoEmitirConta comunicado = getControladorFaturamento().pesquisarComunicadoNaoEmitido(idImovel, ComunicadoEmitirConta.ALTERACAO_CADASTRAL);
//                        
//                if (comunicado == null)
//                    this.aprovarImovel(idImovel);
//            }
//    		
//		} catch (ErroRepositorioException e) {
//			logger.error("Erro ao emitir comunicados para imóveis previamente aprovados " + idImovel, e);
//			throw new ControladorException("Erro ao gerar comunicado de irregularidade de faturamento para o imovel" + idImovel, e);
//		}
//    }
    
    private List<ComunicadoEmitirConta> obterComunicadosParaSeremEmitidos() throws ControladorException {
			
    	List<ComunicadoEmitirConta> retorno = new ArrayList<ComunicadoEmitirConta>();

    	try {
				Collection<ComunicadoEmitirConta> comunicados = getControladorFaturamento().pesquisarComunicadosNaoEmitidos(ComunicadoEmitirConta.ALTERACAO_CADASTRAL);
				
				Iterator<ComunicadoEmitirConta> itComunicados = comunicados.iterator();
				
				
				while (itComunicados.hasNext()) {
					
					ComunicadoEmitirConta comunicado = itComunicados.next();
					
					if (getControladorImovel().isImovelHidrometrado(comunicado.getImovel().getId())) {
						this.atualizarDataLiberacaoProcessamento(comunicado.getImovel().getId());
						retorno.add(comunicado);
					}
				}
			} catch (ErroRepositorioException e) {
				logger.error("Erro ao consultar comunicados para serem emitidos.", e);
				throw new ControladorException("Erro ao consultar comunicados para serem emitidos.", e);
			}
			
		return retorno;
    }
    
    private void atualizarDataLiberacaoProcessamento(Integer idImovel) throws ControladorException {
    	try {
			ImovelControleAtualizacaoCadastral controle = obterImovelControle(idImovel);
			SistemaParametro parametros = getControladorUtil().pesquisarParametrosDoSistema();
			
			controle.setDataLiberacaoProcessamento(Util.adicionarNumeroDiasDeUmaData(new Date(), parametros.getQuantidadeDiasLiberacaoProcessamento()));
			
			getControladorUtil().atualizar(controle);
			
		} catch (ControladorException e) {
			logger.error("Erro ao atualizar data de liberacao de processamento do imovel " + idImovel, e);
			throw new ControladorException("Erro ao atualizar data de liberacao de processamento do imovel " + idImovel, e);
		}
    	
    }
    
    public void emitirTermoAlteracaoCadastral(Integer idFuncionalidade, Usuario usuarioLogado) throws ControladorException {
    	int idUnidadeIniciada = 0;
    	try {
    		
    		//this.emitirComunicadosAprovacoesAntigas();
    		
    		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidade, UnidadeProcessamento.FUNCIONALIDADE, 0);

    		List<ComunicadoEmitirConta> comunicados = this.obterComunicadosParaSeremEmitidos();
			
			StringBuilder arquivo = new StringBuilder();
			StringBuilder arquivoComSeparador = new StringBuilder();
			
			arquivo.append(getCabecalhoComunicadoAlteracaoCadqstral(comunicados.size())).append(System.getProperty("line.separator"));
			arquivoComSeparador.append(getCabecalhoComunicadoAlteracaoCadqstral(comunicados.size())).append(System.getProperty("line.separator"));
			
			String dataFormatada = Util.formatarDataAAAAMMDD(new Date());

			for (ComunicadoEmitirConta comunicado : comunicados) {

				logger.info("Emitindo comunicado - " + comunicado.getImovel().getId());
				
				arquivo.append(Util.completaString(comunicado.getImovel().getId().toString(), 9));
				arquivo.append(Util.completaString(getControladorCliente().obterNomeClienteConta(comunicado.getImovel().getId()), 40));
				arquivo.append(Util.completaString(getControladorEndereco().obterDescricaoEnderecoImovel(comunicado.getImovel().getId()), 120));
				arquivo.append(Util.completaString(getControladorEndereco().obterEnderecoCorrespondenciaImovel(comunicado.getImovel().getId()), 120));
				arquivo.append(descricaoAlteracoesComunicadoIrregularidadefaturamento(comunicado.getImovel().getId()));
				arquivo.append(Util.formatarAnoMesParaMesAnoComBarra(comunicado.getReferencia()));
				arquivo.append(dataFormatada);
				arquivo.append(System.getProperty("line.separator"));
				
				arquivoComSeparador.append(Util.completaString(comunicado.getImovel().getId().toString(), 9));
				arquivoComSeparador.append(";");
				arquivoComSeparador.append(Util.completaString(getControladorCliente().obterNomeClienteConta(comunicado.getImovel().getId()), 40));
				arquivoComSeparador.append(";");
				arquivoComSeparador.append(Util.completaString(getControladorEndereco().obterDescricaoEnderecoImovel(comunicado.getImovel().getId()), 120));
				arquivoComSeparador.append(";");
				arquivoComSeparador.append(Util.completaString(getControladorEndereco().obterEnderecoCorrespondenciaImovel(comunicado.getImovel().getId()), 120));
				arquivoComSeparador.append(";");
				arquivoComSeparador.append(descricaoAlteracoesComunicadoIrregularidadefaturamento(comunicado.getImovel().getId()));
				arquivoComSeparador.append(";");
				arquivoComSeparador.append(Util.formatarAnoMesParaMesAnoComBarra(comunicado.getReferencia()));
				arquivoComSeparador.append(";");
				arquivoComSeparador.append(dataFormatada);
				arquivoComSeparador.append(";");
				arquivoComSeparador.append(System.getProperty("line.separator"));
				
				comunicado.setIndicadorEmitido(ConstantesSistema.SIM);
				getControladorUtil().atualizar(comunicado);
			}
			
			String nomeZip = "TERMO_ALTERACAO_CADASTRAL_" + dataFormatada;
			String nomeZipComSeparados = "TERMO_ALTERACAO_CADASTRAL_SEPARADO_" + dataFormatada;
			getControladorUtil().salvarArquivoZip(arquivo, nomeZip, "recadastramento");
			
			getControladorUtil().salvarArquivoZip(arquivoComSeparador, nomeZipComSeparados, "recadastramento");
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
			
		} catch (ControladorException e) {
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);
			logger.error("Erro ao emitir comunicados não emitidos", e);
			throw new EJBException(e);
		}  
    }
    
    private String getCabecalhoComunicadoAlteracaoCadqstral(Integer qtdRegistros) throws ControladorException {
    	StringBuilder cabecalho = new StringBuilder();
    	SistemaParametro parametros = getControladorUtil().pesquisarParametrosDoSistema();
    	
    	cabecalho.append(Util.completaStringComZeroAEsquerda(qtdRegistros.toString(), 4));
    	cabecalho.append(Util.completaString(Util.formatarCnpj(parametros.getCnpjEmpresa()), 18));
    	cabecalho.append(Util.completaString(Util.formatarInscricaoEstadual(parametros.getInscricaoEstadual()), 12));
    	
    	return cabecalho.toString();
    }
    
    private String descricaoAlteracoesComunicadoIrregularidadefaturamento(Integer idImovel) throws ControladorException {
    	StringBuilder arquivo = new StringBuilder();
    	
    	try {
			if (this.houveMudancaCategoria(idImovel)) {
				arquivo.append(Util.completaString("CATEGORIA", 40));
				
				String categorias[] = this.getDescricaoMudancaCategoria(idImovel);
				
				arquivo.append(Util.completaString(categorias[0], 30)); // DE
				arquivo.append(Util.completaString(categorias[1], 30)); // PARA
				
			} else if (this.houveMudancaSubcategoria(idImovel)) {
				arquivo.append(Util.completaString("SUBCATEGORIA", 40));
				
				String subcategorias[] = this.getDescricaoMudancaSubcategoria(idImovel);
				
				arquivo.append(Util.completaString(subcategorias[0], 30)); // DE
				arquivo.append(Util.completaString(subcategorias[1], 30)); // PARA
				
			} else {
				arquivo.append(Util.completaString("QUANTIDADE DE ECONOMIAS POR CATEGORIA", 40));

				String economias[] = this.getDescricaoMudancaEconomiasPorSubcategoria(idImovel);
				
				arquivo.append(Util.completaString(economias[0], 30)); // DE
				arquivo.append(Util.completaString(economias[1], 30)); // PARA
			}
			
			return arquivo.toString();
		} catch (ControladorException e) {
			logger.error("Erro ao obter descricao das mudancas do faturamento - comunicado - " + idImovel, e);
			throw new ControladorException("Erro ao obter descricao das mudancas do faturamento - comunicado - " + idImovel, e);
		}
    }

	public List<Visita> obterVisitasPorCoordenadas(String latitude, String longitude) throws ControladorException {
		try {
			return repositorioAtualizacaoCadastral.obterVisitasPorCoordenadas(latitude, longitude);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("Erro ao obter visita por coordenadas.", e);
		}
	}
	
	public Date pesquisarDataDaUltimaAlteracaoDoImovel(Integer idImovel) throws ControladorException {
		try {
			return repositorioCadastro.buscarUltimadataAlteracaoNoImovel(idImovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("Erro ao obter data da ultima alteracao no imovel.", e);
		}
	}
}
