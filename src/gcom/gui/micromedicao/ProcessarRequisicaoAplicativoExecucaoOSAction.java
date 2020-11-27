package gcom.gui.micromedicao;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts.action.ActionForward;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import gcom.api.ordemServico.helper.ArquivoRetornoAplicativoExecucaoOSHelper;
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
		                
		if (idOperacao == Operacao.OPERACAO_RELIGACAO_AGUA_EFETUAR) {
			operacaoReligacaoAguaEfetuar(ordemServico, imovel, araeOSH, usuario);
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
		
		fachada.atualizarOSViaApp(araeOSH.getIdServicoTipo(), integracaoComercialHelper, null);
	}
	
	
}
