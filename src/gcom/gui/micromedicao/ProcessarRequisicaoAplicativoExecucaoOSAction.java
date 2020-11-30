package gcom.gui.micromedicao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import gcom.api.ordemservico.helper.ArquivoRetornoAplicativoExecucaoOSHelper;
import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaDiametro;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaMaterial;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil;
import gcom.atendimentopublico.ligacaoagua.RamalLocalInstalacao;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.fachada.Fachada;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifa;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;


public class ProcessarRequisicaoAplicativoExecucaoOSAction extends GcomAction {
	
	private static ProcessarRequisicaoAplicativoExecucaoOSAction instancia;

	public static ProcessarRequisicaoAplicativoExecucaoOSAction getInstancia() {
		if (instancia == null) {
			instancia = new ProcessarRequisicaoAplicativoExecucaoOSAction();
		}
		return instancia;
	}

	// Fachada
	Fachada fachada = Fachada.getInstancia();

	public void execute(JsonObject json){
		
		OrdemServico ordemServico = null;

		// Carrega informações básicas
		Gson gson = new Gson();
		
		ArquivoRetornoAplicativoExecucaoOSHelper araeOSH = gson.fromJson(json, ArquivoRetornoAplicativoExecucaoOSHelper.class);

		Usuario usuario = fachada.pesquisarUsuario(araeOSH.getIdUsuario());
		ordemServico = fachada.recuperaOSPorId(araeOSH.getIdOrdemServico()); 
		Integer idOperacao = fachada.pesquisarServicoTipoOperacao(ordemServico.getServicoTipo().getId());
		Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();
		                
		if (idOperacao != null) {

			switch (idOperacao) {
				case (Operacao.OPERACAO_RELIGACAO_AGUA_EFETUAR_INT):
					operacaoReligacaoAguaEfetuar(ordemServico, imovel, araeOSH, usuario);
					break;
				case (Operacao.OPERACAO_INSTALACAO_HIDROMETRO_EFETUAR_INT):
					break;
				case (Operacao.OPERACAO_SUBSTITUICAO_HIDROMETRO_EFETUAR_INT):
					break;
				case (Operacao.OPERACAO_RESTABELECIMENTO_LIGACAO_AGUA_EFETUAR_INT):
					break;
				case (Operacao.OPERACAO_LIGACAO_AGUA_EFETUAR_INT):
					operacaoLigacaoAguaEfetuar(ordemServico, imovel, araeOSH, usuario);
					break;
			}
		}
			
	}
	
	protected void operacaoReligacaoAguaEfetuar(OrdemServico ordemServico, Imovel imovel, 
			ArquivoRetornoAplicativoExecucaoOSHelper araeOSH, Usuario usuario) {
			
		fachada.validarExibirRestabelecimentoLigacaoAgua(ordemServico, true);
			
		if (ordemServico != null && araeOSH.getIdTipoDebito() != null) {

			ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;

			ordemServico.setIndicadorComercialAtualizado(new Short("1"));

			BigDecimal valorAtual = new BigDecimal(0);
			if (araeOSH.getValorDebito() != null) {
				
				String valorDebito = araeOSH.getValorDebito().toString().replace(".", "");

				valorDebito = valorDebito.replace(",", ".");

				valorAtual = new BigDecimal(valorDebito);

				ordemServico.setValorAtual(valorAtual);
			}

			if (araeOSH.getIdServicoMotivoNaoCobranca() != null) {
				servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
				servicoNaoCobrancaMotivo.setId(araeOSH.getIdServicoMotivoNaoCobranca());
			}
			ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);

			if (araeOSH.getValorPercentual() != null) {
				ordemServico.setPercentualCobranca(new BigDecimal(araeOSH.getPercentualCobranca()));
			}
		}

		Date data = Util.converteStringParaDate(araeOSH.getDataReligacao());
		LigacaoAgua ligacaoAgua = new LigacaoAgua();
		ligacaoAgua.setId(imovel.getId());
		ligacaoAgua.setDataReligacao(data);
	
		IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();
	
		integracaoComercialHelper.setImovel(imovel);
		integracaoComercialHelper.setLigacaoAgua(ligacaoAgua);
		integracaoComercialHelper.setOrdemServico(ordemServico);
		integracaoComercialHelper.setQtdParcelas(araeOSH.getQtdParcelas());
		integracaoComercialHelper.setUsuarioLogado(usuario);
		
		fachada.atualizarOSViaApp(araeOSH.getIdServicoTipo(), integracaoComercialHelper, usuario);
	}
	
	
	protected void operacaoLigacaoAguaEfetuar(OrdemServico ordemServico, Imovel imovel, 
			ArquivoRetornoAplicativoExecucaoOSHelper araeOSH, Usuario usuario) {
		
		
		
	/*	
		FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
		filtroConsumoTarifa.adicionarParametro(new ParametroSimples (FiltroConsumoTarifa.LIGACAO_AGUA_PERFIL,araeOSH.getDados_ligacao_agua().getPerfil()));
		
		Collection pesquisa = fachada.pesquisar(filtroConsumoTarifa, ConsumoTarifa.class.getName());
		
		if (!pesquisa.isEmpty()){
			Boolean existeTarifaIgual = false;
			Iterator iteratorColecaoConsumoTarifa = pesquisa.iterator();
							
			while(iteratorColecaoConsumoTarifa.hasNext()){
				
				ConsumoTarifa consumoTarifa = (ConsumoTarifa) iteratorColecaoConsumoTarifa.next();
				if (consumoTarifa.getLigacaoAguaPerfil() != null){
						if (imovel.getConsumoTarifa().getId().intValue() ==  consumoTarifa.getId().intValue()){
							existeTarifaIgual = true;
						}
					}
			}
			
			if (!existeTarifaIgual){
				throw new ActionServletException("atencao.tarifa_consumo_perfil_ligacao",null, "Perfil da Ligação");
			}
		}
		
		*/
		
		LigacaoAgua ligacaoAgua = new LigacaoAgua();
		imovel.setUltimaAlteracao(new Date());
		ligacaoAgua.setImovel(imovel); 
		
		Date data = Util.converteStringParaDate(araeOSH.getDados_ligacao_agua().getDataLigacao());
		ligacaoAgua.setDataLigacao(data);
		
		LigacaoAguaDiametro ligacaoAguaDiametro = new LigacaoAguaDiametro(araeOSH.getDados_ligacao_agua().getDiametro());
		ligacaoAgua.setLigacaoAguaDiametro(ligacaoAguaDiametro);
			
		LigacaoAguaMaterial ligacaoAguaMaterialMaterial = new LigacaoAguaMaterial(araeOSH.getDados_ligacao_agua().getMaterial());
		ligacaoAgua.setLigacaoAguaMaterial(ligacaoAguaMaterialMaterial);

		LigacaoAguaPerfil ligacaoAguaPerfil = new LigacaoAguaPerfil(araeOSH.getDados_ligacao_agua().getPerfil());
		ligacaoAgua.setLigacaoAguaPerfil(ligacaoAguaPerfil);
		
			
			if (isIdValido(araeOSH.getDados_ligacao_agua().getLocalInstalacaoRamal().toString())) {
				RamalLocalInstalacao ramalLocal = new RamalLocalInstalacao(araeOSH.getDados_ligacao_agua().getLocalInstalacaoRamal());
				ligacaoAgua.setRamalLocalInstalacao(ramalLocal);
			}

			if(isIdValido(araeOSH.getDados_ligacao_agua().getOrigem().toString())){
				LigacaoOrigem ligacaoOrigem = new LigacaoOrigem(araeOSH.getDados_ligacao_agua().getOrigem());
				ligacaoAgua.setLigacaoOrigem(ligacaoOrigem);
			}
			
			if(araeOSH.getDados_ligacao_agua().getLacreAgua() !=null && !araeOSH.getDados_ligacao_agua().getLacreAgua().equals("")){
				ligacaoAgua.setNumeroLacre(araeOSH.getDados_ligacao_agua().getLacreAgua().toString());
			}
			
			if(isIdValido(araeOSH.getDados_ligacao_agua().getPavimentoRua().toString())){
				PavimentoRua pavimentoRua = new PavimentoRua(araeOSH.getDados_ligacao_agua().getPavimentoRua());
				ligacaoAgua.setPavimentoRua(pavimentoRua);
			}
				
			if(isIdValido(araeOSH.getDados_ligacao_agua().getPavimentoCalcada().toString())){
					PavimentoCalcada pavimentoCalcada = new PavimentoCalcada(araeOSH.getDados_ligacao_agua().getPavimentoCalcada());
					ligacaoAgua.setPavimentoCalcada(pavimentoCalcada);
			}
			
			/*
			if (araeOSH.getDados_ligacao_agua().getProfundidade() != null && !araeOSH.getDados_ligacao_agua().getProfundidade().isEmpty())
					ligacaoAgua.setProfundidadeRamal(new BigDecimal(araeOSH.getDados_ligacao_agua().getProfundidade().replace(",", ".")));
				
			if (araeOSH.getDados_ligacao_agua().getDistanciaInstalacaoRamal() != null && !araeOSH.getDados_ligacao_agua().getDistanciaInstalacaoRamal().isEmpty())
					ligacaoAgua.setDistanciaInstalacaoRamal(new BigDecimal(araeOSH.getDados_ligacao_agua().getDistanciaInstalacaoRamal().replace(",", ".")));
			 */
				
			if (ordemServico != null && araeOSH.getIdTipoDebito() != null) {

				ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;

				ordemServico.setIndicadorComercialAtualizado(new Short("1"));
				
				if (araeOSH.getIdServicoMotivoNaoCobranca() != null) {
					servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
					servicoNaoCobrancaMotivo.setId(araeOSH.getIdServicoMotivoNaoCobranca());
				}
				ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);

				if (araeOSH.getValorPercentual() != null) {
					ordemServico.setPercentualCobranca(new BigDecimal(araeOSH.getPercentualCobranca()));
				}
				ordemServico.setUltimaAlteracao(new Date());
						
			}
			
			BigDecimal valorAtual = new BigDecimal(0);
			if (araeOSH.getValorDebito() != null) {
				
				String valorDebito = araeOSH.getValorDebito().toString().replace(".", "");

				valorDebito = valorDebito.replace(",", ".");

				valorAtual = new BigDecimal(valorDebito);

				ordemServico.setValorAtual(valorAtual);
			}

		IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();
		
		
		integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);	
		integracaoComercialHelper.setImovel(imovel);
		integracaoComercialHelper.setLigacaoAgua(ligacaoAgua);
		integracaoComercialHelper.setOrdemServico(ordemServico);
		integracaoComercialHelper.setQtdParcelas(araeOSH.getQtdParcelas());
		integracaoComercialHelper.setUsuarioLogado(usuario);
		
		fachada.atualizarOSViaApp(araeOSH.getIdServicoTipo(), integracaoComercialHelper, usuario);
	}
	
	private boolean isIdValido(String idCampo) {
		return idCampo != null && !idCampo.equals("") &&!idCampo.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO); 
	}
	
	
	
}
