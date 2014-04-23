package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.cliente.ClienteImovelSimplificado;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.cliente.FiltroClienteImovelEconomia;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelEconomia;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelEconomia;
import gcom.cadastro.tarifasocial.FiltroTarifaSocialDadoEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.fachada.Fachada;
import gcom.gui.ControladorGcomAction;
import gcom.gui.ControladorGcomActionForm;
import gcom.gui.ControladorInclusaoGcomAction;
import gcom.gui.StatusWizard;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author thiago toscano
 */
public class MostrarDadosTarifaSocialAction extends ControladorInclusaoGcomAction {

	public static final String CASO_USO = MostrarDadosTarifaSocialAction.class.getSimpleName()  + ".do";

	public static final String ACAO_EXIBIR = CASO_USO + "?" + ControladorGcomAction.PARAMETRO_ACAO + "=" + ControladorGcomAction.PARAMETRO_ACAO_EXIBIR;

	public static final String ACAO_PROCESSAR = CASO_USO + "?" + ControladorGcomAction.PARAMETRO_ACAO + "=" + ControladorGcomAction.PARAMETRO_ACAO_PROCESSAR;

	public ActionForward exibirAuxiliar(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		MostrarDadosTarifaSocialActionForm form = (MostrarDadosTarifaSocialActionForm) actionForm;

		        form.resetOTD(request);

        Imovel imovel = new Imovel();
        Integer idImovel = new Integer(form.getIdRegistroAtualizacao());
        imovel.setId(idImovel);
        
        Fachada fachada = Fachada.getInstancia();
        
        //[FS0002] - Verificar Existência de RA
        fachada.verificarExistenciaRegistroAtendimentoTarifaSocial(idImovel, "atencao.manter_tarifa_social_existencia_registro_atendimento"); 
        

        int quantidade = Fachada.getInstancia().obterQuantidadeEconomias(imovel);

        OTDManterDadosClienteImovelEconomia otd = new OTDManterDadosClienteImovelEconomia(request, idImovel, quantidade);

        criarWizard(request);

        // pesquisando o imovel ou imovel economia e seu respectivo usuario 
        if (quantidade == 1) {
        	// quando so tem uma economia
        	FiltroImovel filtroImovel = new FiltroImovel();
        	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,idImovel));
        	Collection imoveis = Fachada.getInstancia().pesquisar(filtroImovel, Imovel.class.getName());
        	imovel = (Imovel)imoveis.iterator().next();

        	Cliente cliente = null;
            FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
            filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, form.getIdRegistroAtualizacao()));
            filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO, ClienteRelacaoTipo.USUARIO));
            filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente.unidadeFederacao");
            filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente.orgaoExpedidorRg");
            Collection collClienteImovel = Fachada.getInstancia().pesquisarClienteImovel(filtroClienteImovel);
            if (collClienteImovel != null && !collClienteImovel.isEmpty()) {
            	ClienteImovelSimplificado clienteImovel = (ClienteImovelSimplificado) collClienteImovel.iterator().next();
            	FiltroCliente filtroCliente = new FiltroCliente();
            	filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, clienteImovel.getCliente().getId()));
                filtroCliente.adicionarCaminhoParaCarregamentoEntidade("orgaoExpedidorRg");
                filtroCliente.adicionarCaminhoParaCarregamentoEntidade("unidadeFederacao");
                Collection collCliente = Fachada.getInstancia().pesquisarCliente(filtroCliente);
                if (collCliente != null && !collCliente.isEmpty()) {
                	cliente = ((Cliente) collCliente.iterator().next());
                	//sessao.setAttribute("cliente", cliente);
                }	
            }

            FiltroTarifaSocialDadoEconomia filtroTarifaSocialDadoEconomia = (FiltroTarifaSocialDadoEconomia) this.gerarFiltro(form);

            Collection coll = Fachada.getInstancia().pesquisarTarifaSocialDadoEconomia(filtroTarifaSocialDadoEconomia);
            TarifaSocialDadoEconomia tarifa = (TarifaSocialDadoEconomia) coll.iterator().next(); 

        	OTDClienteEconomia otdCliente = new OTDClienteEconomia();
        	otdCliente.setImovel(imovel);
        	otdCliente.setTarifaSocialDadoEconomia(tarifa);
        	otdCliente.setCliente(cliente);
        	
        	otd.addOtdClienteEconomia(otdCliente);

        } else {
        	// quando tem varias economia
        	FiltroImovelEconomia filtroImovelEconomia = new FiltroImovelEconomia();
        	filtroImovelEconomia.adicionarParametro(new ParametroSimples(FiltroImovelEconomia.IMOVEL_ID,idImovel));
        	Collection imoveis = Fachada.getInstancia().pesquisar(filtroImovelEconomia, ImovelEconomia.class.getName());
        	Iterator it = imoveis.iterator();
        	while (it.hasNext()) {
        		
        		ImovelEconomia imovelEconomia = (ImovelEconomia) it.next();

	        	Cliente cliente = null;
	            FiltroClienteImovelEconomia filtroClienteImovelEconomia = new FiltroClienteImovelEconomia();
	            filtroClienteImovelEconomia.adicionarParametro(new ParametroSimples(FiltroClienteImovelEconomia.IMOVEL_ECONOMIA_ID,imovelEconomia.getId()));
	            filtroClienteImovelEconomia.adicionarParametro(new ParametroSimples(FiltroClienteImovelEconomia.CLIENTE_RELACAO_TIPO, ClienteRelacaoTipo.USUARIO));
	            filtroClienteImovelEconomia.adicionarParametro(new ParametroNulo(FiltroClienteImovelEconomia.DATA_FIM_RELACAO));
	            filtroClienteImovelEconomia.adicionarCaminhoParaCarregamentoEntidade("cliente.unidadeFederacao");
	            filtroClienteImovelEconomia.adicionarCaminhoParaCarregamentoEntidade("cliente.orgaoExpedidorRg");
	            
	            Collection collClienteImovelEconomia = Fachada.getInstancia().pesquisar(filtroClienteImovelEconomia,ClienteImovelEconomia.class.getName());
	            if (collClienteImovelEconomia != null && !collClienteImovelEconomia.isEmpty()) {
	            	ClienteImovelEconomia clienteImovelEconomia = (ClienteImovelEconomia) collClienteImovelEconomia.iterator().next();
	            	FiltroCliente filtroCliente = new FiltroCliente();
	            	filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, clienteImovelEconomia.getCliente().getId()));
	                filtroCliente.adicionarCaminhoParaCarregamentoEntidade("orgaoExpedidorRg");
	                filtroCliente.adicionarCaminhoParaCarregamentoEntidade("unidadeFederacao");
	                Collection collCliente = Fachada.getInstancia().pesquisarCliente(filtroCliente);
	                if (collCliente != null && !collCliente.isEmpty()) {
	                	cliente = ((Cliente) collCliente.iterator().next());
	                }
	            }

	            FiltroTarifaSocialDadoEconomia filtroTarifaSocialDadoEconomia = new FiltroTarifaSocialDadoEconomia();
	            
	            filtroTarifaSocialDadoEconomia.adicionarParametro(new ParametroSimples(FiltroTarifaSocialDadoEconomia.IMOVEL_ECONOMIA, imovelEconomia.getId()));
	            filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroTarifaSocialDadoEconomia.TARIFA_SOCIAL_CARTAO_TIPO);
	            filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroTarifaSocialDadoEconomia.RENDA_TIPO);
	            filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroTarifaSocialDadoEconomia.TARIFA_SOCIAL_DADO);
	            filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroTarifaSocialDadoEconomia.IMOVEL_ECONOMIA);
	            filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroTarifaSocialDadoEconomia.TARIFA_SOCIAL_DADO_IMOVEL);

	            Collection coll = Fachada.getInstancia().pesquisarTarifaSocialDadoEconomia(filtroTarifaSocialDadoEconomia);
	            if (coll != null && !coll.isEmpty()) {
		            TarifaSocialDadoEconomia tarifa = (TarifaSocialDadoEconomia) coll.iterator().next(); 

		        	OTDClienteEconomia otdCliente = new OTDClienteEconomia();
		        	otdCliente.setImovelEconomia(imovelEconomia);
		        	otdCliente.setTarifaSocialDadoEconomia(tarifa);
		        	otdCliente.setCliente(cliente);
		        	
		        	otd.addOtdClienteEconomia(otdCliente);
	            }
        	}
        }

        form.setCollObjeto(otd.getOtdClienteEconomia());
        
        return null;
	}

	public ActionForward reexibir(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		//MostrarDadosTarifaSocialActionForm form = (MostrarDadosTarifaSocialActionForm) actionForm;

//		Cliente cliente = this.getCliente(form);
//        this.montarFormulario(cliente,form);
		
		return actionMapping.findForward(ControladorGcomAction.FORWARD_EXIBIR);
	}

	public ActionForward processarAuxiliar(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		MostrarDadosTarifaSocialActionForm form = (MostrarDadosTarifaSocialActionForm) actionForm;

    	OTDManterDadosClienteImovelEconomia otd = (OTDManterDadosClienteImovelEconomia) form.getOTD(request);
    	
    	Collection collTarifaSocial = otd.getOtdClienteEconomia();

        if(collTarifaSocial != null) {
        	//consulta o imovel 
    		FiltroImovel filtroImovel = new FiltroImovel();
    		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, form.getIdRegistroAtualizacao()));
    		Collection coll = Fachada.getInstancia().pesquisar(filtroImovel, Imovel.class.getName());
    		Imovel imovel = null;
    		if (coll != null && !coll.isEmpty()) {
    			imovel = (Imovel) coll.iterator().next();
    		}
    		
    		// pra todas as tarifas atualiza-se
        	Iterator it = collTarifaSocial.iterator();
        	while (it.hasNext()) {

        		OTDClienteEconomia otdClienteEconomia = (OTDClienteEconomia) it.next();
        		
        		TarifaSocialDadoEconomia tarifa = otdClienteEconomia.getTarifaSocialDadoEconomia();

        		if (tarifa.getConsumoCelpe() == null) {
        			tarifa.setConsumoCelpe(new Integer(100));
        		}
        		
        		// consulta do banco a tarifa social dado
                FiltroTarifaSocialDadoEconomia filtroTarifaSocialDadoEconomia = new FiltroTarifaSocialDadoEconomia();
                filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroTarifaSocialDadoEconomia.TARIFA_SOCIAL_DADO);
                filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade("tarifaSocialDado.imovel");
                filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroTarifaSocialDadoEconomia.IMOVEL_ECONOMIA);
                filtroTarifaSocialDadoEconomia.adicionarParametro(new ParametroSimples(FiltroTarifaSocialDadoEconomia.ID, tarifa.getId()));

        		coll = Fachada.getInstancia().pesquisar(filtroTarifaSocialDadoEconomia, TarifaSocialDadoEconomia.class.getName());
        		/*TarifaSocialDado tarifaSocialDado = null;
        		if (coll != null && !coll.isEmpty()) {
        			TarifaSocialDadoEconomia temp  = (TarifaSocialDadoEconomia) coll.iterator().next();
        			tarifaSocialDado = temp.getTarifaSocialDado();
        			tarifa.setImovelEconomia(temp.getImovelEconomia());
        			tarifa.setTarifaSocialDado(tarifaSocialDado);
        		}*/
        		tarifa.setUltimaAlteracao(new Date());
        		// atualiza a tarifa social
        		Fachada.getInstancia().atualizar(tarifa);

        		// para todos os clientes atualizar
        		if (otd.getCollectionCliente(tarifa.getId()) != null) {
            		it = otd.getCollectionCliente(tarifa.getId()).iterator();
            		
            		while (it.hasNext()) {
    					OTDClienteImovelEconomia otdClienteImovelEconomia = (OTDClienteImovelEconomia) it.next();
    	        		if (otd.getQuantidadeEconomia() == 1) {
    	        			// manipula os clientes imoveis
    						percistirClienteImovel(otdClienteImovelEconomia, imovel);
//    						 atualizar o imovel
    					} else {
    						
//    						 atualizar o imovel economia
    						if (otdClienteEconomia.getImovelEconomia() != null) {
    							percistirClienteImovelEconomia(tarifa.getId(), otdClienteImovelEconomia, otdClienteEconomia.getImovelEconomia());
    						}
    					}
    				}
            		
            		if (otd.getQuantidadeEconomia() == 1) {
            			// manipula os clientes imoveis
    					if (otdClienteEconomia.getImovel() != null) { 
    						imovel.setAreaConstruida(otdClienteEconomia.getImovel().getAreaConstruida());
    						imovel.setAreaConstruidaFaixa(otdClienteEconomia.getImovel().getAreaConstruidaFaixa());
    						imovel.setNumeroIptu(otdClienteEconomia.getImovel().getNumeroIptu());
    						imovel.setNumeroCelpe(otdClienteEconomia.getImovel().getNumeroCelpe());

    						// atualiza o imovel
    						Fachada.getInstancia().atualizar(imovel);
    					}
//    						 atualizar o imovel
    				} else {
    					
//    						 atualizar o imovel economia
    					if (otdClienteEconomia.getImovelEconomia() != null) {
    						// atualiza o imovel
    						Fachada.getInstancia().atualizar(otdClienteEconomia.getImovelEconomia());
    					}
    				}
            	}
        	}
        }

		return this.telaSucesso(actionMapping,request,"Dados da Tarifa Social do imóvel de matrícula " + form.getIdRegistroAtualizacao() + " foram atualizados com sucesso.","Realizar outra Manutenção de Tarifa Social",ConsultaImovelTarifaSocialAction.ACAO_EXIBIR);
	}
	
	
	private void percistirClienteImovelEconomia(Integer idTarifaSocial, OTDClienteImovelEconomia e, ImovelEconomia imovelEconomia) {
		// caso o id for == null é pra adicionar

		if (e.getId() == null ) {
			ClienteImovelEconomia clienteImovelEconomia = new ClienteImovelEconomia();
			clienteImovelEconomia.setCliente(e.getCliente());
			clienteImovelEconomia.setClienteRelacaoTipo(e.getClienteRelacaoTipo());
			clienteImovelEconomia.setDataInicioRelacao(e.getDataInicioRelacao());
			clienteImovelEconomia.setImovelEconomia(imovelEconomia);
			clienteImovelEconomia.setUltimaAlteracao(new Date());

			Fachada.getInstancia().inserir(clienteImovelEconomia);
		} else{
			// caso tenha id e nao tenha motivo de fim de relacionamento
			if (e.getDataFimRelacao() != null || e.getClienteImovelFimRelacaoMotivo() != null) {
		       	FiltroClienteImovelEconomia filtroClienteImovelEconomia = new FiltroClienteImovelEconomia();
	        	filtroClienteImovelEconomia.adicionarParametro(new ParametroSimples(FiltroClienteImovelEconomia.ID, e.getId()));
	        	filtroClienteImovelEconomia.adicionarCaminhoParaCarregamentoEntidade("clienteImovelFimRelacaoMotivo");
	        	filtroClienteImovelEconomia.adicionarCaminhoParaCarregamentoEntidade("cliente");
	        	filtroClienteImovelEconomia.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");

	        	Collection coll = Fachada.getInstancia().pesquisar(filtroClienteImovelEconomia, ClienteImovelEconomia.class.getName());
	            ClienteImovelEconomia clienteImovelEconomia;
	            if (coll != null && !coll.isEmpty()) {
	            	clienteImovelEconomia = ((ClienteImovelEconomia) coll.iterator().next());
	            	clienteImovelEconomia.setDataFimRelacao(e.getDataFimRelacao());
	            	clienteImovelEconomia.setClienteImovelFimRelacaoMotivo(e.getClienteImovelFimRelacaoMotivo());
	            	Fachada.getInstancia().atualizar(clienteImovelEconomia);	
	            }
			}
		}
	}

	private void percistirClienteImovel(OTDClienteImovelEconomia e, Imovel imovel) {

		// caso o id for == null é pra adicionar
		if (e.getId() == null ) {
			ClienteImovel clienteImovel = new ClienteImovel();
			clienteImovel.setCliente(e.getCliente());
			clienteImovel.setClienteRelacaoTipo(e.getClienteRelacaoTipo());
			clienteImovel.setDataInicioRelacao(e.getDataInicioRelacao());
			clienteImovel.setImovel(imovel);
			clienteImovel.setUltimaAlteracao(new Date());

			Fachada.getInstancia().inserirClienteImovel(clienteImovel);
		} else{
			// caso tenha id e nao tenha motivo de fim de relacionamento
			if (e.getDataFimRelacao() != null || e.getClienteImovelFimRelacaoMotivo() != null) {
		       	FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
	        	filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.ID, e.getId()));
	        	filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("clienteImovelFimRelacaoMotivo");
	        	filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
	        	filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");
	        	filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel");
	        	
	        	Collection coll = Fachada.getInstancia().pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
	            ClienteImovel clienteImovel;
	            if (coll != null && !coll.isEmpty()) {
	            	clienteImovel = ((ClienteImovel) coll.iterator().next());
	            	clienteImovel.setDataFimRelacao(e.getDataFimRelacao());
	            	clienteImovel.setClienteImovelFimRelacaoMotivo(e.getClienteImovelFimRelacaoMotivo());
	            	Fachada.getInstancia().atualizar(clienteImovel);	
	            }
			}
		}				
	}
	
	
	public Object gerarObject(ControladorGcomActionForm actionForm, HttpServletRequest request) {

		return null;
	}

	public void inserirObjeto(Object obj, HttpServletRequest request, ControladorGcomActionForm actionForm) throws Exception {

	}


	public void carregarColecao(ControladorGcomActionForm actionForm) {
		
	}

	public Filtro gerarFiltro(ControladorGcomActionForm actionForm) {

		MostrarDadosTarifaSocialActionForm form = (MostrarDadosTarifaSocialActionForm) actionForm;

        FiltroTarifaSocialDadoEconomia filtroTarifaSocialDadoEconomia = new FiltroTarifaSocialDadoEconomia();
        filtroTarifaSocialDadoEconomia.adicionarParametro(new ParametroSimples(FiltroTarifaSocialDadoEconomia.IMOVEL_ID, form.getIdRegistroAtualizacao()));
        filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroTarifaSocialDadoEconomia.TARIFA_SOCIAL_CARTAO_TIPO);
        filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroTarifaSocialDadoEconomia.RENDA_TIPO);
        filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroTarifaSocialDadoEconomia.TARIFA_SOCIAL_DADO);
        filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroTarifaSocialDadoEconomia.IMOVEL_ECONOMIA);
        filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroTarifaSocialDadoEconomia.TARIFA_SOCIAL_DADO_IMOVEL);
        
		return filtroTarifaSocialDadoEconomia;
	}


	public void montarFormulario(Object obj, ControladorGcomActionForm actionForm) {
		MostrarDadosTarifaSocialActionForm form = (MostrarDadosTarifaSocialActionForm) actionForm;
		if (obj instanceof Cliente) {

			Cliente cliente = (Cliente) obj;

			if (cliente.getNome() != null)	
				form.setNomeCliente(cliente.getNome());
			if (cliente.getNome() != null)	
				form.setComplementoEndereco("");
			if (cliente.getCpf() != null)	
				form.setCpf(cliente.getCpf());
			if (cliente.getRg() != null)	
				form.setRg(cliente.getRg());
			if (cliente.getDataEmissaoRg() != null)	
				form.setDataEmissao(Util.formatarData(cliente.getDataEmissaoRg()));
			if (cliente.getOrgaoExpedidorRg() != null)	
				form.setOrgaoExpedidor(cliente.getOrgaoExpedidorRg().getDescricao());
			if (cliente.getUnidadeFederacao() != null)	
				form.setUf(cliente.getUnidadeFederacao().getSigla());

		}
	}

	/*private Cliente getCliente(MostrarDadosTarifaSocialActionForm form){
		Cliente cliente = null;
        FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
        filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, form.getIdRegistroAtualizacao()));
        filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO, ClienteRelacaoTipo.USUARIO));
        
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente.unidadeFederacao");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente.orgaoExpedidorRg");

        Collection collClienteImovel = Fachada.getInstancia().pesquisarClienteImovel(filtroClienteImovel);

        if (collClienteImovel != null && !collClienteImovel.isEmpty()) {
        	ClienteImovelSimplificado clienteImovel = (ClienteImovelSimplificado) collClienteImovel.iterator().next();
        	FiltroCliente filtroCliente = new FiltroCliente();
        	filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, clienteImovel.getCliente().getId()));
            filtroCliente.adicionarCaminhoParaCarregamentoEntidade("orgaoExpedidorRg");
            filtroCliente.adicionarCaminhoParaCarregamentoEntidade("unidadeFederacao");
            Collection collCliente = Fachada.getInstancia().pesquisarCliente(filtroCliente);
            if (collCliente != null && !collCliente.isEmpty()) {
            	cliente = ((Cliente) collCliente.iterator().next());
            	//sessao.setAttribute("cliente", cliente);
            }	
        }
        return cliente;
	}*/
	
	
	private void criarWizard(HttpServletRequest request){
		//Monta o Status do Wizard
        StatusWizard statusWizard = new StatusWizard("AtualizarDadosTarifaSocialWizardAction", "AtualizarDadosTarifaSocialAction", "AtualizarDadosTarifaSocialClienteAction","");

        statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        1, "TarifaSocialTarifaA.gif", "TarifaSocialTarifaD.gif",
                        "exibirPopUpDadosTarifaSocial",
                        "processarPopUpDadosTarifaSocial"));

        statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        2, "ClienteA.gif", "ClienteD.gif",
                        "exibirPopUpCliente",
                        "processarPopUpCliente"));

        //manda o statusWizard para a sessao
        getSessao(request).setAttribute("statusWizard", statusWizard);

		
	}
}
