package gcom.gui.micromedicao;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.cadastro.imovel.bean.ImovelMicromedicao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.FiltroConsumoHistorico;
import gcom.micromedicao.medicao.FiltroMedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de inserir bairro
 * 
 * @author Sávio Luiz
 * @created 28 de Junho de 2004
 */
public class ExibirConsultarHistoricoMedicaoConsumoAction extends GcomAction {
    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("consultarHistoricoMedicaoConsumo");
        
        LeituraConsumoActionForm leituraConsumoActionForm = (LeituraConsumoActionForm) actionForm;
        
        Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		//HttpSession sessao = httpServletRequest.getSession(false);
        
		String codigoImovel = leituraConsumoActionForm.getImovel();
		
		Collection<MedicaoHistorico> medicoesHistorico = new ArrayList();
		Collection<ImovelMicromedicao> imoveisMicromedicao = new ArrayList();
		
		FiltroMedicaoHistorico filtroMedicaoHistorico = new FiltroMedicaoHistorico();
		FiltroConsumoHistorico filtroConsumoHistorico = new FiltroConsumoHistorico();

		//MedicaoHistorico
		filtroMedicaoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroMedicaoHistorico.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeFaturamento");
		filtroMedicaoHistorico.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeInformada");
		filtroMedicaoHistorico.adicionarCaminhoParaCarregamentoEntidade("leituraSituacaoAtual");
		filtroMedicaoHistorico.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua");
		
		
		//Consumo Historico
		filtroConsumoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroConsumoHistorico.adicionarCaminhoParaCarregamentoEntidade("consumoTipo");
		filtroConsumoHistorico.adicionarCaminhoParaCarregamentoEntidade("consumoAnormalidade");
		
		FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
		filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroLigacaoAgua.adicionarParametro(new ParametroSimples(FiltroLigacaoAgua.ID, codigoImovel));
		Collection<LigacaoAgua> ligacoesAgua = fachada.pesquisar(filtroLigacaoAgua, LigacaoAgua.class.getName());
		
		filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(FiltroMedicaoHistorico.IMOVEL_ID, codigoImovel, ParametroSimples.CONECTOR_OR));
		filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
				FiltroMedicaoHistorico.LIGACAO_AGUA_ID, codigoImovel));
		filtroMedicaoHistorico.setCampoOrderBy(FiltroMedicaoHistorico.ANO_MES_REFERENCIA_FATURAMENTO);
		
		if(!ligacoesAgua.isEmpty()){
			filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(FiltroMedicaoHistorico.MEDICAO_TIPO_ID, MedicaoTipo.LIGACAO_AGUA));
		}else{
			filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(FiltroMedicaoHistorico.MEDICAO_TIPO_ID, MedicaoTipo.POCO));
		}
		medicoesHistorico = fachada.pesquisar(filtroMedicaoHistorico, MedicaoHistorico.class.getName());
		if(!medicoesHistorico.isEmpty()){
			Iterator iteratorMedicaoHistorico = medicoesHistorico.iterator();
			
			while(iteratorMedicaoHistorico.hasNext()){
				MedicaoHistorico medicaoHistoricoConsumo = (MedicaoHistorico)iteratorMedicaoHistorico.next();
				if(medicaoHistoricoConsumo.getAnoMesReferencia()!= 0){
					filtroConsumoHistorico.limparListaParametros();
					filtroConsumoHistorico.adicionarParametro(new ParametroSimples(FiltroConsumoHistorico.ANO_MES_FATURAMENTO, medicaoHistoricoConsumo.getAnoMesReferencia()));
					filtroConsumoHistorico.adicionarParametro(new ParametroSimples(FiltroConsumoHistorico.IMOVEL_ID, codigoImovel));
					filtroConsumoHistorico.setCampoOrderBy(FiltroConsumoHistorico.ANO_MES_FATURAMENTO);
					
					Collection<ConsumoHistorico> collectionConsumoHistorico = fachada.pesquisar(filtroConsumoHistorico, ConsumoHistorico.class.getName());
					if(!collectionConsumoHistorico.isEmpty()){
						ConsumoHistorico consumoHistoricoNovo = (ConsumoHistorico) collectionConsumoHistorico.iterator().next();
						ImovelMicromedicao imovelMicromedicao = new ImovelMicromedicao();
						imovelMicromedicao.setConsumoHistorico(consumoHistoricoNovo);
						imovelMicromedicao.setMedicaoHistorico(medicaoHistoricoConsumo);
						//if(!imoveisMicromedicao.contains(imovelMicromedicao)){
							imoveisMicromedicao.add(imovelMicromedicao);
						//}
						//obtem qtd de dias do consumo
						if(medicaoHistoricoConsumo.getDataLeituraAnteriorFaturamento() != null &&
								medicaoHistoricoConsumo.getDataLeituraAtualFaturamento() != null){
							int qtdDias = Util.obterQuantidadeDiasEntreDuasDatas(medicaoHistoricoConsumo.getDataLeituraAnteriorFaturamento(),
									medicaoHistoricoConsumo.getDataLeituraAtualFaturamento());
							imovelMicromedicao.setQtdDias("" + qtdDias);
						}
					}
				}
			}
			
			//coloca a colecao de medicao historico no request
			httpServletRequest.setAttribute("medicoesHistoricos", medicoesHistorico);
			//coloca colecao de consumo historico no request
			httpServletRequest.setAttribute("imoveisMicromedicao", imoveisMicromedicao);
		}
		
		//sessao.setAttribute("leituraConsumoActionForm", leituraConsumoActionForm);
		
        return retorno;
    }
}
