package gcom.api.ordemservico.bo;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import gcom.api.ordemservico.dto.OrdemServicoDTO;
import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaDiametro;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaMaterial;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil;
import gcom.atendimentopublico.ligacaoagua.RamalLocalInstalacao;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.FachadaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

public class ProcessarRequisicaoOrdemServicoBO {

	private Fachada fachada = Fachada.getInstancia();

	private static ProcessarRequisicaoOrdemServicoBO instancia;

	public static ProcessarRequisicaoOrdemServicoBO getInstancia() {
		if (instancia == null) {
			instancia = new ProcessarRequisicaoOrdemServicoBO();
		}
		return instancia;
	}

	public boolean execute(OrdemServicoDTO dto) {
		OrdemServico ordemServico = fachada.recuperaOSPorId(dto.getId());
		Usuario usuario = fachada.pesquisarUsuario(dto.getIdUsuarioEncerramento());
		Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();

		Integer idOperacao = dto.getOperacao();

		if (idOperacao != null) {

			switch (idOperacao) {

			case (Operacao.OPERACAO_RELIGACAO_AGUA_EFETUAR_INT):
				return operacaoReligacaoAguaEfetuar(ordemServico, imovel, usuario, dto);

			case (Operacao.OPERACAO_INSTALACAO_HIDROMETRO_EFETUAR_INT):
				break;

			case (Operacao.OPERACAO_SUBSTITUICAO_HIDROMETRO_EFETUAR_INT):
				return operacaoSubstituicaoHidrometroEfetuar(ordemServico, imovel, usuario, dto);
				
			case (Operacao.OPERACAO_RESTABELECIMENTO_LIGACAO_AGUA_EFETUAR_INT):
				break;

			case (Operacao.OPERACAO_LIGACAO_AGUA_EFETUAR_INT):
				return operacaoLigacaoAguaEfetuar(ordemServico, imovel, usuario, dto);
			}
		}
		
		return false;
	}

	private boolean operacaoSubstituicaoHidrometroEfetuar(OrdemServico ordemServico, Imovel imovel, Usuario usuario, OrdemServicoDTO dto) {
		
		        
        Integer localArmazenagemHidrometro = null;
          
        // caso o hidrometro esteja extraviado, nao pega o local de armazenagem
        if(dto.getHidrometro().getSituacao() != null ){   //situacaodohidrometro extraviado ???
        	localArmazenagemHidrometro = dto.getHidrometro().getLocalArmazenagem();        	
        }
        
       HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = new HidrometroInstalacaoHistorico();
		
       if (dto.getHidrometro().getNumero() != null) {

			//Constrói o filtro para pesquisa do Hidrômetro
			FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
			filtroHidrometro.adicionarParametro(new ParametroSimples(FiltroHidrometro.NUMERO_HIDROMETRO, dto.getHidrometro().getNumero() ));
	        
			//Realiza a pesquisa do Hidrômetro
			Collection colecaoHidrometro = null;
			colecaoHidrometro = fachada.pesquisar(filtroHidrometro,Hidrometro.class.getName());
			
			//verifica se o número do hidrômetro não está cadastrado
			if (colecaoHidrometro == null || colecaoHidrometro.isEmpty()) {
				throw new ActionServletException("atencao.numero_hidrometro_inexistente", null, dto.getHidrometro().getNumero());
			}
			
			Iterator iteratorHidrometro = colecaoHidrometro.iterator();
			Hidrometro hidrometro = (Hidrometro) iteratorHidrometro.next();
			
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade.hidrometroLocalArmazenagem");
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, imovel.getId()));
			
			Collection colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
			
			Imovel imovelComLocalidade = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
			
			if (imovelComLocalidade != null && imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem() != null &&
					hidrometro.getHidrometroLocalArmazenagem() != null &&
				!hidrometro.getHidrometroLocalArmazenagem().getId().equals(imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem().getId())) {
					throw new ActionServletException("atencao.hidrometro_local_armazenagem_imovel_diferente_hidrometro_local_armazenagem_hidrometro");
			}
			
			hidrometroInstalacaoHistorico.setHidrometro(hidrometro);
		}

		//Atualiza a entidade com os valores do formulário
        hidrometroInstalacaoHistorico = setFormValues(hidrometroInstalacaoHistorico,dto);
		
		HidrometroInstalacaoHistorico hidrometroSubstituicaoHistorico = new HidrometroInstalacaoHistorico();
		
			// Tipo medição - Ligação Água
		if (ordemServico.getRegistroAtendimento() == null || ordemServico.getRegistroAtendimento().getSolicitacaoTipoEspecificacao()
				.getIndicadorLigacaoAgua().equals(MedicaoTipo.LIGACAO_AGUA.shortValue())) {
			LigacaoAgua ligacaoAgua = imovel.getLigacaoAgua();
			hidrometroSubstituicaoHistorico = ligacaoAgua.getHidrometroInstalacaoHistorico();
		
			// Tipo medição- Poço
		} else {
			hidrometroSubstituicaoHistorico = imovel.getHidrometroInstalacaoHistorico();
		}

		Date dataRetirada = Util.converteStringParaDate(dto.getHidrometro().getDataRetirada());
		
		hidrometroSubstituicaoHistorico.setDataRetirada(dataRetirada);
		
		if (dto.getHidrometro().getLeituraRetirada() != null){
			hidrometroSubstituicaoHistorico.setNumeroLeituraRetirada(dto.getHidrometro().getLeituraRetirada());
		}
		
		hidrometroSubstituicaoHistorico.setUltimaAlteracao(new Date());
				
		ordemServico.setIndicadorComercialAtualizado(ConstantesSistema.SIM);
		ordemServico.setValorAtual(ordemServico.getValorOriginal());
		ordemServico.setPercentualCobranca(new BigDecimal(100));
		ordemServico.setUltimaAlteracao(new Date());
		
			IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();
		
			integracaoComercialHelper.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
			integracaoComercialHelper.setHidrometroSubstituicaoHistorico(hidrometroSubstituicaoHistorico);
			integracaoComercialHelper.setSituacaoHidrometroSubstituido(dto.getHidrometro().getSituacao().toString());
		if(localArmazenagemHidrometro != null){
			integracaoComercialHelper.setLocalArmazenagemHidrometro(localArmazenagemHidrometro);
		}
			integracaoComercialHelper.setMatriculaImovel(imovel.getMatriculaFormatada());
			integracaoComercialHelper.setOrdemServico(ordemServico);
			integracaoComercialHelper.setQtdParcelas("1");
			integracaoComercialHelper.setUsuarioLogado(usuario);
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.TRUE);	
			
			fachada.validacaoSubstituicaoHidrometro(imovel.getMatriculaFormatada(),hidrometroInstalacaoHistorico.getHidrometro().getNumero(), dto.getHidrometro().getSituacao().toString());

			fachada.atualizarOSViaApp(ordemServico.getServicoTipo().getId(), integracaoComercialHelper, usuario);	
				
		
		return false;
	}

	protected boolean operacaoReligacaoAguaEfetuar(OrdemServico ordemServico, Imovel imovel, Usuario usuario, OrdemServicoDTO ordemServicoDTO) {

		try {
			ordemServico.setIndicadorComercialAtualizado(ConstantesSistema.SIM);
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
			
			return true;
			
		} catch (FachadaException e) {
			return false;
		}
	}
	
	protected boolean operacaoLigacaoAguaEfetuar(OrdemServico ordemServico, Imovel imovel, Usuario usuario, OrdemServicoDTO ordemServicoDTO) {
			
		try {
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
			
			ordemServico.setIndicadorComercialAtualizado(ConstantesSistema.SIM);
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
		
		return true;
		
		} catch (FachadaException e) {
			return false;
		}
		
	}
	
	private boolean isIdValido(String idCampo) {
		return idCampo != null && !idCampo.equals("") &&!idCampo.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO); 
	}
	
	public HidrometroInstalacaoHistorico setFormValues(HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico, OrdemServicoDTO dto) {
		
		/*
		 * Campos obrigatórios
		 */
		
		//data instalação
		hidrometroInstalacaoHistorico.setDataInstalacao(Util.converteStringParaDate(dto.getHidrometroInstalacao().getData()));
		
		if (dto.getHidrometro().getTipoMedicao().equals(""+MedicaoTipo.POCO)) {

		  Imovel imovel = new Imovel();
		  imovel.setId(new Integer(dto.getImovel().getMatricula()));
						
		  hidrometroInstalacaoHistorico.setImovel(imovel);
		  hidrometroInstalacaoHistorico.setLigacaoAgua(null);
					
		} else if (dto.getHidrometro().getTipoMedicao().equals(""+MedicaoTipo.LIGACAO_AGUA)) {

		  LigacaoAgua ligacaoAgua = new LigacaoAgua();
		  ligacaoAgua.setId(new Integer(dto.getImovel().getMatricula()));
						
		  hidrometroInstalacaoHistorico.setLigacaoAgua(ligacaoAgua);
		  hidrometroInstalacaoHistorico.setImovel(null);
	    }
		//medição tipo
		MedicaoTipo medicaoTipo = new MedicaoTipo();
		medicaoTipo.setId(Integer.parseInt(dto.getHidrometro().getTipoMedicao()));
		hidrometroInstalacaoHistorico.setMedicaoTipo(medicaoTipo);
		
		//hidrômetro local instalação
		HidrometroLocalInstalacao hidrometroLocalInstalacao = new HidrometroLocalInstalacao();
		hidrometroLocalInstalacao.setId(dto.getHidrometroInstalacao().getLocal());		
		hidrometroInstalacaoHistorico.setHidrometroLocalInstalacao(hidrometroLocalInstalacao);
		
		//proteção
		HidrometroProtecao hidrometroProtecao = new HidrometroProtecao();
		hidrometroProtecao.setId(dto.getHidrometroInstalacao().getProtecao());
		hidrometroInstalacaoHistorico.setHidrometroProtecao(hidrometroProtecao);
		
		//leitura instalação
		if(dto.getHidrometroInstalacao().getLeitura() != null && !dto.getHidrometroInstalacao().getLeitura().equals("")){
		    hidrometroInstalacaoHistorico.setNumeroLeituraInstalacao(dto.getHidrometroInstalacao().getLeitura());
		}else{
			hidrometroInstalacaoHistorico.setNumeroLeituraInstalacao(0);	
		}
		
		//cavalete
		hidrometroInstalacaoHistorico.setIndicadorExistenciaCavalete(dto.getHidrometroInstalacao().getCavalete().shortValue());
		
		/*
		 * Campos opcionais 
		 */
		//leitura corte
		hidrometroInstalacaoHistorico.setNumeroLeituraCorte(null);
		
		//leitura supressão
		hidrometroInstalacaoHistorico.setNumeroLeituraSupressao(null);
		
		//numero selo
		if (dto.getHidrometroInstalacao().getSelo() != null && !dto.getHidrometroInstalacao().getSelo().equals("")){
			hidrometroInstalacaoHistorico.setNumeroSelo(dto.getHidrometroInstalacao().getSelo().toString());
		} else {
			hidrometroInstalacaoHistorico.setNumeroSelo(null);
		}
		
		//numero lacre
		if (dto.getHidrometroInstalacao().getLacre() != null && !dto.getHidrometroInstalacao().getLacre().equals("")){
			hidrometroInstalacaoHistorico.setNumeroLacre(dto.getHidrometroInstalacao().getLacre().toString());
		} else {
			hidrometroInstalacaoHistorico.setNumeroLacre(null);
		}
		
		//tipo de rateio
		hidrometroInstalacaoHistorico.setRateioTipo(null);
		hidrometroInstalacaoHistorico.setDataImplantacaoSistema(new Date());

		//indicador instalação substituição
		hidrometroInstalacaoHistorico.setIndicadorInstalcaoSubstituicao(new Short("1"));		
		
		//data última alteração
		hidrometroInstalacaoHistorico.setUltimaAlteracao(new Date());
        
        if(dto.getHidrometroInstalacao().getTrocaProtecao() != null){
            hidrometroInstalacaoHistorico.setIndicadorTrocaProtecao(dto.getHidrometroInstalacao().getTrocaProtecao().shortValue());
        }
        if(dto.getHidrometroInstalacao().getTrocaRegistro() != null){
            hidrometroInstalacaoHistorico.setIndicadorTrocaRegistro(dto.getHidrometroInstalacao().getTrocaRegistro().shortValue());
        }
		
		return hidrometroInstalacaoHistorico;
	}
}
