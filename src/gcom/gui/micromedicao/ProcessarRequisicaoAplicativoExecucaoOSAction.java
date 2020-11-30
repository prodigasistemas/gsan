package gcom.gui.micromedicao;

import java.math.BigDecimal;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import gcom.api.ordemservico.dto.OrdemServicoDTO;
import gcom.api.ordemservico.helper.ArquivoRetornoAplicativoExecucaoOSHelper;
import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

public class ProcessarRequisicaoAplicativoExecucaoOSAction extends GcomAction {

	private Fachada fachada = Fachada.getInstancia();
	private static ProcessarRequisicaoAplicativoExecucaoOSAction instancia;

	public static ProcessarRequisicaoAplicativoExecucaoOSAction getInstancia() {
		if (instancia == null) {
			instancia = new ProcessarRequisicaoAplicativoExecucaoOSAction();
		}
		return instancia;
	}

	public void execute(JsonObject json) {

		OrdemServico ordemServico = null;

		Gson gson = new Gson();
		OrdemServicoDTO ordemServicoDTO = gson.fromJson(json, OrdemServicoDTO.class);

		ArquivoRetornoAplicativoExecucaoOSHelper helper = new ArquivoRetornoAplicativoExecucaoOSHelper(ordemServicoDTO);

		Usuario usuario = fachada.pesquisarUsuario(helper.getOrdemServico().getIdUsuarioEncerramento());
		ordemServico = fachada.recuperaOSPorId(helper.getOrdemServico().getId());
		Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();

		Integer idOperacao = fachada.pesquisarServicoTipoOperacao(ordemServico.getServicoTipo().getId());

		if (idOperacao != null) {

			switch (idOperacao) {
			case (Operacao.OPERACAO_RELIGACAO_AGUA_EFETUAR_INT):
				operacaoReligacaoAguaEfetuar(ordemServico, imovel, helper, usuario);
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

	protected void operacaoReligacaoAguaEfetuar(OrdemServico ordemServico, Imovel imovel, ArquivoRetornoAplicativoExecucaoOSHelper helper,
			Usuario usuario) {

		fachada.validarExibirRestabelecimentoLigacaoAgua(ordemServico, true);

		if (ordemServico != null && helper.getIdTipoDebito() != null) {

			ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;

			ordemServico.setIndicadorComercialAtualizado(new Short("1"));

			BigDecimal valorAtual = new BigDecimal(0);
			if (helper.getValorDebito() != null) {

				String valorDebito = helper.getValorDebito().toString().replace(".", "");

				valorDebito = valorDebito.replace(",", ".");

				valorAtual = new BigDecimal(valorDebito);

				ordemServico.setValorAtual(valorAtual);
			}

			if (helper.getIdServicoMotivoNaoCobranca() != null) {
				servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
				servicoNaoCobrancaMotivo.setId(helper.getIdServicoMotivoNaoCobranca());
			}
			ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);

			if (helper.getValorPercentual() != null) {
				ordemServico.setPercentualCobranca(new BigDecimal(helper.getPercentualCobranca()));
			}
		}

		Date data = Util.converteStringParaDate(helper.getDataReligacao());
		LigacaoAgua ligacaoAgua = new LigacaoAgua();
		ligacaoAgua.setId(imovel.getId());
		ligacaoAgua.setDataReligacao(data);

		IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

		integracaoComercialHelper.setImovel(imovel);
		integracaoComercialHelper.setLigacaoAgua(ligacaoAgua);
		integracaoComercialHelper.setOrdemServico(ordemServico);
		integracaoComercialHelper.setQtdParcelas(helper.getQtdParcelas());
		integracaoComercialHelper.setUsuarioLogado(usuario);

		fachada.atualizarOSViaApp(helper.getIdServicoTipo(), integracaoComercialHelper, usuario);
	}

}
