package gcom.gui.micromedicao;

import gcom.gui.GcomAction;
import gcom.micromedicao.Rota;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class GerarDadosParaLeituraAction extends GcomAction {
    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	
//   	Seta o retorno
        ActionForward retorno = actionMapping
                .findForward("telaPrincipal");
//        
//        String txtAntigo = "11111111111111111111";
//        String txtNovo = "22222";
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(txtAntigo);
//        
//        stringBuilder.replace(10,15,txtNovo);
//        
//        StringBuilder stringBuilder1 = new StringBuilder();
//        stringBuilder1 = stringBuilder;
//        
//        //Obtém a instância da fachada
       // Fachada fachada = Fachada.getInstancia();
        
        Collection rotas = new ArrayList();
        
        Rota rota = new Rota();
        rota.setId(572);
        
        rotas.add(rota);
        
        /*Rota rota1 = new Rota();
        
        rota1.setId(3);
        
        rotas.add(rota1); */
        
       // Integer anoMesCorrente = 200601;
        
      //  Integer idLeituraTipo = 2;
        
//        try {
//			Collection gerarDados = fachada.gerarDadosPorLeituraConvencional(rotas,
//					anoMesCorrente, idLeituraTipo);
//		} catch (ControladorException e) {
//			
//			e.printStackTrace();
//		}
        
//        Imovel imovel = new Imovel();
//        Quadra quadra = new Quadra(); 
//        Rota rota = new Rota();
//        rota.setPercentualGeracaoFaixaFalsa(new BigDecimal(1.5));
//        quadra.setRota(rota);
//        imovel.setQuadra(quadra);
//        imovel.setId(54653212);
//        
////      Faixa de leitura esperada
//
//		SistemaParametro sistemaParametro = null;
//		sistemaParametro = fachada
//					.pesquisarParametrosDoSistema();
//		
//		Integer leituraAnterior = null;
//
//			MedicaoTipo medicaoTipo = new MedicaoTipo();
//
//			medicaoTipo.setId(MedicaoTipo.LIGACAO_AGUA);
//			
//			
//			try {
//				leituraAnterior = fachada.pesquisarLeituraAnteriorTipoLigacaoAgua(imovel.getId());
//			} catch (ControladorException e1) {
//				
//				e1.printStackTrace();
//			}
//
//			
//			
//			
//        int[] mediaPeriodo = fachada.obterConsumoMedioHidrometro(imovel,sistemaParametro,medicaoTipo);
//        
//        int mediaConsumoHidrometro = mediaPeriodo[0];
//        
//        boolean hidrometroSelecionado = true;
//        MedicaoHistorico medicaoHistorico = new MedicaoHistorico();
//        
//        LeituraSituacao leituraSituacaoAtual = new LeituraSituacao(); 
//        leituraSituacaoAtual.setId(2);
//        medicaoHistorico.setLeituraSituacaoAtual(leituraSituacaoAtual);
//        
//        Hidrometro hidrometro = new Hidrometro();
//        hidrometro.setNumeroDigitosLeitura(new Short("6"));
//        Object[] consumoMinino = null;
//        try {
//			consumoMinino = fachada.calcularFaixaLeituraFalsa(imovel,mediaConsumoHidrometro,leituraAnterior,medicaoHistorico,hidrometroSelecionado,hidrometro);
//		} catch (ControladorException e) {
//			
//			e.printStackTrace();
//		}
//		Object[] consumoMinino1 = consumoMinino;
//       
        return retorno;
    }
}
