package gcom.api.ordemservico.bo;

import java.math.BigDecimal;
import java.util.Date;

import gcom.api.ordemservico.dto.OrdemServicoDTO;
import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
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
				break;
			}
		}
	}

	protected void operacaoReligacaoAguaEfetuar(OrdemServico ordemServico, Imovel imovel, Usuario usuario, OrdemServicoDTO ordemServicoDTO) {

		fachada.validarExibirRestabelecimentoLigacaoAgua(ordemServico, true); // TODO - Retornar boolean

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
}
