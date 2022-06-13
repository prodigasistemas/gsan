package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ImovelAbaConclusaoHelper;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class AtualizarImovelConclusaoAction extends GcomAction {

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

        ActionForward retorno = actionMapping
                .findForward("gerenciadorProcesso");

        //Obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        //Instanciando o ActionForm de InserirImovelLocalidadeActionForm
        DynaValidatorForm inserirImovelConclusaoActionForm = (DynaValidatorForm) actionForm;
        
        //Cria variaveis
        String iptu = (String) inserirImovelConclusaoActionForm.get("numeroIptu");
        String contratoCelpe = (String) inserirImovelConclusaoActionForm.get("numeroContratoCelpe");
		String cordenadasX = (String) inserirImovelConclusaoActionForm.get("cordenadasUtmX");
		String cordenadasY = (String) inserirImovelConclusaoActionForm.get("cordenadasUtmY");
		String idQuadra = (String) inserirImovelConclusaoActionForm.get("idQuadra");
		String idImovelPrincipal = (String) inserirImovelConclusaoActionForm.get("idImovel");
		String sequencialRotaEntrega = (String) inserirImovelConclusaoActionForm.get("sequencialRotaEntrega");
		String numeroQuadraEntrega = (String) inserirImovelConclusaoActionForm.get("numeroQuadraEntrega");
		String idRotaEntrega = (String) inserirImovelConclusaoActionForm.get("idRota");
		String idRotaAlternativa = (String) inserirImovelConclusaoActionForm.get("idRotaAlternativa");
		String numeroMedidorEnergia = (String) inserirImovelConclusaoActionForm.get("numeroMedidorEnergia");
		String informacoesComplementares = (String) inserirImovelConclusaoActionForm.get("informacoesComplementares");
		String indicadorEnvioContaFisica = (String) inserirImovelConclusaoActionForm.get("indicadorEnvioContaFisica");
		//String dataVisitaComercialInformada  = (String) inserirImovelConclusaoActionForm.get("dataVisitaComercial");
		
		Imovel imovelAtualizar = (Imovel) sessao.getAttribute("imovelAtualizacao");
		
		Collection clientes = (Collection) sessao.getAttribute("imovelClientesNovos");

        //Obtém a instância da Fachada
        Fachada fachada = Fachada.getInstancia();
        
        if(idRotaAlternativa != null && !"".equals(idRotaAlternativa)){
        	FiltroRota filtroRota = new FiltroRota();
        	
        	filtroRota.adicionarParametro(
        		new ParametroSimples(
        			FiltroRota.INDICADOR_ROTA_ALTERNATIVA,
        			ConstantesSistema.SIM));        	
        	
        	filtroRota.adicionarParametro(
        		new ParametroSimples(
        			FiltroRota.ID_ROTA,
        			idRotaAlternativa));
        	
        	Collection  rotasAlternativas = fachada.pesquisar(filtroRota,Rota.class.getName());
        	
        	if(rotasAlternativas != null && !rotasAlternativas.isEmpty()){

        		Rota rotaAlternativa = (Rota) Util.retonarObjetoDeColecao(rotasAlternativas);
            	
            	if(rotaAlternativa.getIndicadorUso().equals(ConstantesSistema.INDICADOR_USO_DESATIVO)){
            		//se passou a rota informada não está ativa
            		throw new ActionServletException("atencao.rota_informada_nao_esta_ativa");
            	}
        	}else{
        		throw new ActionServletException("atencao.rota_informada_nao_e_alternativa");        			
        	}
        	
        	      	
        }
        
        if(informacoesComplementares != null && !informacoesComplementares.equals("")){
        	imovelAtualizar.setInformacoesComplementares(informacoesComplementares);
        }
        
        //Verifica se o campo ENVIO DA CONTA é obrigatorio
        if ( clientes != null && !clientes.isEmpty() ){
        
        	Iterator clientesIterator = clientes.iterator();
            while (clientesIterator.hasNext() ){
            	
            	ClienteImovel clienteImovel = (ClienteImovel) clientesIterator.next();
        		
            	if ( clienteImovel.getClienteRelacaoTipo().getId().intValue() == ClienteRelacaoTipo.RESPONSAVEL.intValue()){
            		
            		if(inserirImovelConclusaoActionForm.get("imovelContaEnvio") == null ||
                    		inserirImovelConclusaoActionForm.get("imovelContaEnvio").equals("-1")){
            			throw new ActionServletException("atencao.envio.conta.obrigatorio");	
            		}
            	}
            }
        }
        
		// Validacao dos dados
		ImovelAbaConclusaoHelper helperConclusao = new ImovelAbaConclusaoHelper();
		helperConclusao.setSetorComercial((SetorComercial) sessao.getAttribute("setorComercial"));
		helperConclusao.setIdQuadraImovel(idQuadra);
		helperConclusao.setIdImovelPrincipal(idImovelPrincipal);
		helperConclusao.setNumeroIptu(iptu);
		helperConclusao.setNumeroContratoCelpe(contratoCelpe);
		helperConclusao.setCoordenadasUtmX(cordenadasX);
		helperConclusao.setCoordenadasUtmY(cordenadasY);
		helperConclusao.setSequencialRotaEntrega(sequencialRotaEntrega);
		helperConclusao.setNumeroQuadraEntrega(numeroQuadraEntrega);
		
		helperConclusao.setIdRotaEntrega(idRotaEntrega);
		helperConclusao.setIdRotaAlternativa(idRotaAlternativa);
		helperConclusao.setImoveisClientes(clientes);
		helperConclusao.setIdImovelAtualizar(imovelAtualizar.getId());
		helperConclusao.setNumeroMedidorEnergia(numeroMedidorEnergia);
		helperConclusao.setInformacoesComplementares(informacoesComplementares);
		helperConclusao.setIndicadorEnvioContaFisica(indicadorEnvioContaFisica);
		fachada.validarImovelAbaConclusao(helperConclusao);
        return retorno;
    }
}
