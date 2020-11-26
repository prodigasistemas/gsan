package gcom.gui.micromedicao;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForward;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.ArquivoRetornoAplicativoExecucaoOSHelper;
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

	public ActionForward execute(ArquivoRetornoAplicativoExecucaoOSHelper arquivoRetornoAplicativoExecucaoOSHelper, Usuario usuario ){

			OrdemServico ordemServico = null;
			Boolean veioEncerrarOS = Boolean.TRUE;
			
			LigacaoAgua ligacaoAgua = new LigacaoAgua();
			
			ordemServico = fachada.recuperaOSPorId(arquivoRetornoAplicativoExecucaoOSHelper.getIdOrdemServico()); 
			Integer idOperacao = fachada.pesquisarServicoTipoOperacao(ordemServico.getServicoTipo().getId());
			Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();
		                                      
			 
			if(idOperacao == Operacao.OPERACAO_RELIGACAO_AGUA_EFETUAR) {
			
					fachada.validarExibirRestabelecimentoLigacaoAgua(ordemServico, veioEncerrarOS);
						
					if (ordemServico != null && arquivoRetornoAplicativoExecucaoOSHelper.getIdTipoDebito() != null) {
		
						ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;
		
						ordemServico.setIndicadorComercialAtualizado(new Short("1"));
		
						BigDecimal valorAtual = new BigDecimal(0);
						if (arquivoRetornoAplicativoExecucaoOSHelper.getValorDebito() != null) {
							
							String valorDebito = arquivoRetornoAplicativoExecucaoOSHelper.getValorDebito().toString().replace(".", "");
		
							valorDebito = valorDebito.replace(",", ".");
		
							valorAtual = new BigDecimal(valorDebito);
		
							ordemServico.setValorAtual(valorAtual);
						}
		
						if (arquivoRetornoAplicativoExecucaoOSHelper.getIdServicoMotivoNaoCobranca() != null) {
							servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
							servicoNaoCobrancaMotivo.setId(arquivoRetornoAplicativoExecucaoOSHelper.getIdServicoMotivoNaoCobranca());
						}
						ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);
		
						if (arquivoRetornoAplicativoExecucaoOSHelper.getValorPercentual() != null) {
							ordemServico.setPercentualCobranca(new BigDecimal(arquivoRetornoAplicativoExecucaoOSHelper.getPercentualCobranca()));
						}
					}
		
							Date data = Util.converteStringParaDate(arquivoRetornoAplicativoExecucaoOSHelper.getDataReligacao());
							ligacaoAgua.setId(imovel.getId());
							ligacaoAgua.setDataReligacao(data);
			
			}//fim if_religaçãoagua
			
			
			IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

			integracaoComercialHelper.setImovel(imovel);
			integracaoComercialHelper.setLigacaoAgua(ligacaoAgua);
			integracaoComercialHelper.setOrdemServico(ordemServico);
			integracaoComercialHelper.setQtdParcelas(arquivoRetornoAplicativoExecucaoOSHelper.getQtdParcelas());
			integracaoComercialHelper.setUsuarioLogado(usuario);
			
			//fachada.atualizarOSViaApp(arquivoRetornoAplicativoExecucaoOSHelper.getIdServicoTipo(), integracaoComercialHelper, arquivoRetornoAplicativoExecucaoOSHelper.getUsuario());
			fachada.atualizarOSViaApp(arquivoRetornoAplicativoExecucaoOSHelper.getIdServicoTipo(), integracaoComercialHelper,
					null);

			
		return null;		
	}
	
	
}
