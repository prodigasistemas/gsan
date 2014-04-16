package gcom.gui.micromedicao;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ImovelMicromedicao;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.FiltroConsumoHistorico;
import gcom.micromedicao.medicao.FiltroMedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de inserir bairro
 * 
 * @author Sávio Luiz
 * @created 28 de Junho de 2004
 */
public class ExibirEfetuarAnaliseExcecoesLeiturasConsumosAction extends GcomAction {
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

         ActionForward retorno = actionMapping.findForward("efetuarAnaliseExcecoesLeiturasConsumosJsp");
         
         Fachada fachada = Fachada.getInstancia();
        
        
//       Mudar isso quando tiver esquema de segurança
 		HttpSession sessao = httpServletRequest.getSession(false); 
         
        //Collection<ImovelMicromedicao> imoveisMicromedicao = new ArrayList();
        
        //imoveisMicromedicao = (Collection<ImovelMicromedicao>) httpServletRequest.getAttribute("imoveisFiltrados");
        Collection imoveisFiltradosInicial = (Collection) sessao.getAttribute("imoveisFiltrados");
        
        Collection imovelPesquisa = null;
        Collection medicaoPesquisa = null;
        Collection consumoPesquisa = null;
        Collection imoveisFiltrados = new ArrayList();
        
        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
        
        
        FiltroImovel filtroImovel = new FiltroImovel();
        FiltroMedicaoHistorico filtroMedicaoHistorico = new FiltroMedicaoHistorico();
        FiltroConsumoHistorico filtroConsumoHistorico = new FiltroConsumoHistorico();
        
        Iterator iterator = imoveisFiltradosInicial.iterator();
        
        Imovel imovel = null;
        MedicaoHistorico medicaoHistorico = null;
        ConsumoHistorico consumoHistorico = null;
        ImovelMicromedicao imovelMicromedicao = null;
        
        while(iterator.hasNext()){
        	Integer idImovel = (Integer)iterator.next();
        	
        	filtroImovel.limparListaParametros();
        	filtroMedicaoHistorico.limparListaParametros();
        	filtroConsumoHistorico.limparListaParametros();
        	
        	//---- Montar objeto ImovelMicromedicao para mostrar na tela efetuarExcecoesLeitura
        	
        	
        	//-----Imovel q fara parte do objeto
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.rota.faturamentoGrupo");
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
        	
        	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
        	
        	imovelPesquisa = fachada.pesquisar(filtroImovel, Imovel.class.getName());
        	if(!imovelPesquisa.isEmpty()){
        		imovel = (Imovel)imovelPesquisa.iterator().next();
        	}
        	
        	//------Medicao que fara parte do objeto        	
        	filtroMedicaoHistorico.adicionarCaminhoParaCarregamentoEntidade("medicaoTipo");
        	filtroMedicaoHistorico.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeFaturamento");
        	
        	filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(FiltroMedicaoHistorico.IMOVEL_ID, 
        			idImovel, ParametroSimples.CONECTOR_OR));
        	filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(FiltroMedicaoHistorico.LIGACAO_AGUA_ID,
        			idImovel));
        	//Faz o teste do ano mes faturamento para pegar a medicao do ano de referencia
        	if(imovel != null && imovel.getQuadra() != null &&
        			imovel.getQuadra().getRota() != null &&
        			imovel.getQuadra().getRota().getFaturamentoGrupo() != null){
        		
        		filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(FiltroMedicaoHistorico.ANO_MES_REFERENCIA_FATURAMENTO,
        				sistemaParametro.getAnoMesFaturamento()));
        		
        	}
        	
        	medicaoPesquisa = fachada.pesquisar(filtroMedicaoHistorico, MedicaoHistorico.class.getName());
        	
        	if(!medicaoPesquisa.isEmpty()){
        		medicaoHistorico = (MedicaoHistorico)medicaoPesquisa.iterator().next();
        	}
        	
        	//-------Consumo Historico
        	filtroConsumoHistorico.adicionarCaminhoParaCarregamentoEntidade("consumoAnormalidade");
        	
        	filtroConsumoHistorico.adicionarParametro(new ParametroSimples(FiltroConsumoHistorico.IMOVEL_ID,
        			idImovel));
        	
//        	Faz o teste do ano mes faturamento para pegar a medicao do ano de referencia
        	if(imovel != null && imovel.getQuadra() != null &&
        			imovel.getQuadra().getRota() != null &&
        			imovel.getQuadra().getRota().getFaturamentoGrupo() != null){
        	
        		filtroConsumoHistorico.adicionarParametro(new ParametroSimples(
        				FiltroConsumoHistorico.ANO_MES_FATURAMENTO,
        				sistemaParametro.getAnoMesFaturamento()));
        	}
        	
        	consumoPesquisa = fachada.pesquisar(filtroConsumoHistorico, ConsumoHistorico.class.getName());
        	
        	if(!consumoPesquisa.isEmpty()){
        		consumoHistorico = (ConsumoHistorico)consumoPesquisa.iterator().next();
        	}
        	
        	imovelMicromedicao = new ImovelMicromedicao();
        	imovelMicromedicao.setImovel(imovel);
        	imovelMicromedicao.setMedicaoHistorico(medicaoHistorico);
        	imovelMicromedicao.setConsumoHistorico(consumoHistorico);
        	
        	imoveisFiltrados.add(imovelMicromedicao);
        }
        
        sessao.setAttribute("imoveisFiltrados", imoveisFiltrados);
        
        return retorno;
    }
}
