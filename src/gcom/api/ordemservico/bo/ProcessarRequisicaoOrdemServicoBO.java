package gcom.api.ordemservico.bo;

import java.math.BigDecimal;
import java.util.Date;

import gcom.api.ordemservico.dto.OrdemServicoDTO;
import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaDiametro;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaMaterial;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil;
import gcom.atendimentopublico.ligacaoagua.RamalLocalInstalacao;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.fachada.Fachada;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

public class ProcessarRequisicaoOrdemServicoBO {

	private Fachada fachada = Fachada.getInstancia();

	private static ProcessarRequisicaoOrdemServicoBO instancia;

	public static ProcessarRequisicaoOrdemServicoBO getInstancia() {
		if (instancia == null) {
			instancia = new ProcessarRequisicaoOrdemServicoBO();
		}
		return instancia;
	}

	public void execute(OrdemServicoDTO dto) {
		OrdemServico ordemServico = fachada.recuperaOSPorId(dto.getId());
		Usuario usuario = fachada.pesquisarUsuario(dto.getIdUsuarioEncerramento());
		Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();

		Integer idOperacao = dto.getOperacao();

		if (idOperacao != null) {

			switch (idOperacao) {

			case (Operacao.OPERACAO_RELIGACAO_AGUA_EFETUAR_INT):
				operacaoReligacaoAguaEfetuar(ordemServico, imovel, usuario, dto);
				break;

			case (Operacao.OPERACAO_INSTALACAO_HIDROMETRO_EFETUAR_INT):
				break;

			case (Operacao.OPERACAO_SUBSTITUICAO_HIDROMETRO_EFETUAR_INT):
				break;

			case (Operacao.OPERACAO_RESTABELECIMENTO_LIGACAO_AGUA_EFETUAR_INT):
				break;

			case (Operacao.OPERACAO_LIGACAO_AGUA_EFETUAR_INT):
				operacaoLigacaoAguaEfetuar(ordemServico, imovel, usuario, dto);
				break;
			}
		}
	}

	protected void operacaoReligacaoAguaEfetuar(OrdemServico ordemServico, Imovel imovel, Usuario usuario, OrdemServicoDTO ordemServicoDTO) {

//		fachada.validarExibirRestabelecimentoLigacaoAgua(ordemServico, true); // TODO - Retornar boolean

		ordemServico.setIndicadorComercialAtualizado(new Short("1"));
		ordemServico.setValorAtual(ordemServico.getValorOriginal());
		ordemServico.setPercentualCobranca(new BigDecimal(100));

		Date data = Util.converteStringParaDate(ordemServicoDTO.getDataEncerramento());
		LigacaoAgua ligacaoAgua = new LigacaoAgua();
		ligacaoAgua.setId(imovel.getId());
		ligacaoAgua.setDataReligacao(data);

		IntegracaoComercialHelper helper = new IntegracaoComercialHelper();
		helper.setImovel(imovel);
		helper.setLigacaoAgua(ligacaoAgua);
		helper.setOrdemServico(ordemServico);
		helper.setQtdParcelas("1"); // TODO - Verificar
		helper.setUsuarioLogado(usuario);

		fachada.atualizarOSViaApp(ordemServico.getServicoTipo().getId(), helper, usuario);
	}
	
	protected void operacaoLigacaoAguaEfetuar(OrdemServico ordemServico, Imovel imovel, Usuario usuario, OrdemServicoDTO ordemServicoDTO) {
			
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
		
		Date data = Util.converteStringParaDate(ordemServicoDTO.getDataEncerramento());
		ligacaoAgua.setDataLigacao(data);
		
		LigacaoAguaDiametro ligacaoAguaDiametro = new LigacaoAguaDiametro(ordemServicoDTO.getLigacaoAgua().getDiametro());
		ligacaoAgua.setLigacaoAguaDiametro(ligacaoAguaDiametro);
			
		LigacaoAguaMaterial ligacaoAguaMaterialMaterial = new LigacaoAguaMaterial(ordemServicoDTO.getLigacaoAgua().getMaterial());
		ligacaoAgua.setLigacaoAguaMaterial(ligacaoAguaMaterialMaterial);

		LigacaoAguaPerfil ligacaoAguaPerfil = new LigacaoAguaPerfil(ordemServicoDTO.getLigacaoAgua().getPerfil());
		ligacaoAgua.setLigacaoAguaPerfil(ligacaoAguaPerfil);
		
			
			if (isIdValido(ordemServicoDTO.getLigacaoAgua().getLocalInstalacaoRamal().toString())) {
				RamalLocalInstalacao ramalLocal = new RamalLocalInstalacao(ordemServicoDTO.getLigacaoAgua().getLocalInstalacaoRamal());
				ligacaoAgua.setRamalLocalInstalacao(ramalLocal);
			}

			if(isIdValido(ordemServicoDTO.getLigacaoAgua().getOrigem().toString())){
				LigacaoOrigem ligacaoOrigem = new LigacaoOrigem(ordemServicoDTO.getLigacaoAgua().getOrigem());
				ligacaoAgua.setLigacaoOrigem(ligacaoOrigem);
			}
			
			if(ordemServicoDTO.getLigacaoAgua().getLacre() !=null && !ordemServicoDTO.getLigacaoAgua().getLacre().equals("")){
				ligacaoAgua.setNumeroLacre(ordemServicoDTO.getLigacaoAgua().getLacre().toString());
			}
			
			if(isIdValido(ordemServicoDTO.getLigacaoAgua().getPavimentoRua().toString())){
				PavimentoRua pavimentoRua = new PavimentoRua(ordemServicoDTO.getLigacaoAgua().getPavimentoRua());
				ligacaoAgua.setPavimentoRua(pavimentoRua);
			}
				
			if(isIdValido(ordemServicoDTO.getLigacaoAgua().getPavimentoCalcada().toString())){
					PavimentoCalcada pavimentoCalcada = new PavimentoCalcada(ordemServicoDTO.getLigacaoAgua().getPavimentoCalcada());
					ligacaoAgua.setPavimentoCalcada(pavimentoCalcada);
			}
			
			/*
			if (ordemServicoDTO.getLigacaoAgua().getProfundidadeRamal() != null && !ordemServicoDTO.getLigacaoAgua().getProfundidadeRamal().isEmpty())
					ligacaoAgua.setProfundidadeRamal(new BigDecimal(ordemServicoDTO.getLigacaoAgua().getProfundidadeRamal().replace(",", ".")));
				
			if (ordemServicoDTO.getLigacaoAgua().getDistanciaInstalacaoRamal() != null && !ordemServicoDTO.getLigacaoAgua().getDistanciaInstalacaoRamal().isEmpty())
					ligacaoAgua.setDistanciaInstalacaoRamal(new BigDecimal(ordemServicoDTO.getLigacaoAgua().getDistanciaInstalacaoRamal().replace(",", ".")));
			 */
			
			ordemServico.setIndicadorComercialAtualizado(new Short("1"));
			ordemServico.setValorAtual(ordemServico.getValorOriginal());
			ordemServico.setPercentualCobranca(new BigDecimal(100));
			ordemServico.setUltimaAlteracao(new Date());
		
		IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();
				
		integracaoComercialHelper.setVeioEncerrarOS(Boolean.TRUE);	
		integracaoComercialHelper.setImovel(imovel);
		integracaoComercialHelper.setLigacaoAgua(ligacaoAgua);
		integracaoComercialHelper.setOrdemServico(ordemServico);
		integracaoComercialHelper.setQtdParcelas("1");
		integracaoComercialHelper.setUsuarioLogado(usuario);
		
		fachada.atualizarOSViaApp(ordemServico.getServicoTipo().getId(), integracaoComercialHelper, usuario);
	}
	
	private boolean isIdValido(String idCampo) {
		return idCampo != null && !idCampo.equals("") &&!idCampo.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO); 
	}
}
