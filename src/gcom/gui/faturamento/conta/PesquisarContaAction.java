package gcom.gui.faturamento.conta;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Realiza a pesquisa de contas de imóvel de acordo com os parâmetros informados na página 
 *
 * @author Pedro Alexandre
 * @date 02/03/2006
 */
public class PesquisarContaAction extends GcomAction {
	/**
	 * Pesquisa as contas existentes para o imóvel
	 *
	 * [UC0248] Pesquisar Contas do Imóvel
	 *
	 * <Breve descrição sobre o subfluxo>
	 *
	 * <Identificador e nome do subfluxo>	
	 *
	 * <Breve descrição sobre o fluxo secundário>
	 *
	 * <Identificador e nome do fluxo secundário> 
	 *
	 * @author Pedro Alexandre
	 * @date 02/03/2006
	 *
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
								ActionForm actionForm, 
								HttpServletRequest httpServletRequest,
								HttpServletResponse httpServletResponse) {

		//Seta o mapeamento de retorno para a tela de resultado da pesquisa de contas do imóvel 
		ActionForward retorno = actionMapping.findForward("listaConta");

		//Cria uma instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		//Cria uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		//Recupera o form de pesquisa de contas do imóvel
		PesquisarContaActionForm pesquisarContaActionForm = (PesquisarContaActionForm) actionForm;
		
		// Recupera os parâmetros do form
		String idImovel = (String) pesquisarContaActionForm.getIdImovel();
		String referenciaContaInicial = (String) pesquisarContaActionForm.getReferenciaContaInicial();
		String referenciaContaFinal = (String) pesquisarContaActionForm.getReferenciaContaFinal();
		String dataEmissaoContaInicialEmString = (String) pesquisarContaActionForm.getDataEmissaoContaInicial();
		String dataEmissaoContaFinalEmString = (String) pesquisarContaActionForm.getDataEmissaoContaFinal();
		String dataVencimentoContaInicialEmString = (String) pesquisarContaActionForm.getDataVencimentoContaInicial();
		String dataVencimentoContaFinalEmString = (String) pesquisarContaActionForm.getDataVencimentoContaFinal();
		String[] idSituacaoConta = (String[]) pesquisarContaActionForm.getIdSituacaoConta();
		
		
		//Cria o filtro de conta e seta a ordenação de resultado da pesquisa
		//pelo código da conta
		FiltroConta filtroConta = new FiltroConta();
		filtroConta.setCampoOrderBy(FiltroConta.ID);

		//Cria flag que vai indicar se o usuário informou ao menos um parâmetro para pesquisar
		boolean peloMenosUmParametroInformado = false;

		//Caso o usuário informou o código do imóvel, pesquisa todas as contas relacionadas com o imóvel
		//caso contrário indica que o usuário não informou o imóvel
		if(idImovel != null && !idImovel.trim().equalsIgnoreCase("")){
			
			//Indica que o usuário informou um parâmetro para pesquisar as contas do imóvel 
			//peloMenosUmParametroInformado = true;

			//Cria o fitro de imóvel, e seta no filtro quais objetos necessários para a pesquisa de imóvel
			FiltroImovel filtroImovel = new FiltroImovel();
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");        	
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");        	
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");        	
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");        	
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");        	
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgoto");
        	
        	//Seta o código do imóvel no filtro 
        	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
        	
        	//Pesquisa o imóvel informado, no sistema 
        	Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
        	
        	//Caso o imóvel informado pelo usuário não tenha sido encontrado no sistema
        	//caso contrário manda o código do imóvel no request 
        	if (colecaoImovel == null || colecaoImovel.isEmpty()){
        		throw new ActionServletException("atencao.naocadastrado", null, "imóvel");
        	}else{
        		httpServletRequest.setAttribute("idImovel",idImovel);
        	}
        	
        	//Recupera o objeto imóvel da coleção
        	Imovel objetoImovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);        	
        	
        	//Cria o filtro para recuperar o relacionamento entre cliente e imóvel,
        	//e seta os objetos necessários no retorno da pesquisa
        	FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
        	filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
        	filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));
        	filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO, ClienteRelacaoTipo.USUARIO));
        	filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.FIM_RELACAO_MOTIVO));
        	
        	//Efetua a pesquisa do relacionamento entre cliente e imóvel
        	Collection colecaoClienteImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
        	
        	//Caso não exista nenhum cliente relacionado com o imóvel
        	if (colecaoClienteImovel == null || colecaoClienteImovel.isEmpty()){
        		throw new ActionServletException("atencao.naocadastrado", null, "cliente do tipo usuário foi");
        	}
        	
        	
        	//Recupera o relacionamento entre cliente e imóvel da coleção pesquisada 
        	ClienteImovel objetoClienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoClienteImovel);
        	        	
        	//Seta no form de pesquisar conta todos os dados de imóvel para exibição na página de resultado da pesquisa
        	pesquisarContaActionForm.setInscricaoImovel(objetoImovel.getInscricaoFormatada());
        	pesquisarContaActionForm.setNomeClienteUsuario(objetoClienteImovel.getCliente().getNome());
        	pesquisarContaActionForm.setSituacaoAguaImovel(objetoImovel.getLigacaoAguaSituacao().getDescricao());        	
        	pesquisarContaActionForm.setSituacaoEsgotoImovel(objetoImovel.getLigacaoEsgotoSituacao().getDescricao());
        	
			//Seta no filtro de conta o código do imóvel informado pelo usuário
			filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.IMOVEL_ID, idImovel));
		}else{
			throw new ActionServletException("atencao.naoinformado",null, "Imóvel");
		}
		
		
		//Caso o usuário tenha informado a referência inicial da conta, pesquisa as contas entre a referência inicial e final, replicando 
		//a referência inicial na final caso essa não tenha sido informada
		//Caso contrário seta a referência final para vazio, e pesquisa as contas para todas as referências existentes
		if (referenciaContaInicial != null && !referenciaContaInicial.trim().equalsIgnoreCase("")) {
			
			//Formata a referência inicial informada, para o formato de ano e depois mês
			referenciaContaInicial = Util.formatarMesAnoParaAnoMesSemBarra((String) pesquisarContaActionForm.getReferenciaContaInicial());
			
			//Caso a referência final não tenha sido informada , replica a referência inicial na final
			//Caso contrário, formata a referência final para ano e mês
			if(referenciaContaFinal == null || referenciaContaFinal.trim().equalsIgnoreCase("")){
				//Replica a referência inicial na referência final
				referenciaContaFinal = referenciaContaInicial;
			}else{
				//Formata a referência final informada, para o formato de ano e depois mês
				referenciaContaFinal = Util.formatarMesAnoParaAnoMesSemBarra((String) pesquisarContaActionForm.getReferenciaContaFinal());
				
				//[FS0002]Caso a referência final da conta seja anterior a inicial, levanta a exceção para o
				//usuário indicando que a referência final é anterior a inicial
				if((new Integer(referenciaContaInicial)).intValue() > (new Integer (referenciaContaFinal)).intValue()){
					throw new ActionServletException("atencao.referenciafinal.menorque");
				}
			}
		} else{
			//Seta a referência final da conta para nula
			referenciaContaFinal = null; 
		}

		//Caso a referência final da conta esteja diferente de nulo,
		//Pesquisa as contas entre a referência inicial e final
		if (referenciaContaFinal != null && !referenciaContaFinal.trim().equalsIgnoreCase("")) {
			//Indica que o usuário informou um parâmetro para pesquisar as contas do imóvel
			peloMenosUmParametroInformado = true;
			
			//Indica no filtro para pesquisar as contas no intervalo entre a referência inicial e a final
			filtroConta.adicionarParametro(new MaiorQue(FiltroConta.REFERENCIA, referenciaContaInicial, ParametroSimples.CONECTOR_AND));
			filtroConta.adicionarParametro(new MenorQue(FiltroConta.REFERENCIA, referenciaContaFinal));
		}

		
		//Cria o formato da data
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		//Cria as variáveis que vai armazenar as datas inicial e final, da emissão das contas
		Date dataEmissaoContaInicial = null;
		Date dataEmissaoContaFinal = null;
		
		//Caso o usuário informou a data de emissão da conta inicial, pesquisa as contas do imóvel referentes a data de 
		//emissão da conta informada
		//Caso contrário, pesquisa as contas sem restrição de data de emissão
		if (dataEmissaoContaInicialEmString != null && !dataEmissaoContaInicialEmString.toString().trim().equalsIgnoreCase("")) {
			
			//[FS0003] Valida a data de emissão da conta inicial
			try {
				dataEmissaoContaInicial = formato.parse(dataEmissaoContaInicialEmString);
			} catch (ParseException e) {
				throw new ActionServletException("atencao.dataemissaoinicial.invalida");
			}

			//Caso a data final de emissão da conta não foi informada, replica a data inicial na final
			//Caso contrário, formata a data final 
			if(dataEmissaoContaFinalEmString == null || dataEmissaoContaFinalEmString.trim().equalsIgnoreCase("")){
				dataEmissaoContaFinal = dataEmissaoContaInicial;
			}else{
				
				//[FS0003] Valida a data de emissão da conta final
				try {
					dataEmissaoContaFinal = formato.parse(dataEmissaoContaFinalEmString);
				} catch (ParseException e) {
					throw new ActionServletException("atencao.dataemissaofinal.invalida");
				}
				
				//[FS0004]Caso a data de emissão final da conta seja anterior a inicial 
				//levanta a exceção para o usuário indicando que a data final é anterior a data inicial
				if(dataEmissaoContaFinal.before(dataEmissaoContaInicial)){
					throw new ActionServletException("atencao.dataemissaofinal.menorque");
				}
			}
		
		} else{
			//Seta para nula a data final de emissão da conta
			dataEmissaoContaFinalEmString = null;
		}

		//Caso a data de emissão final da conta esteja diferente de nulo,
		//Pesquisa as contas entre a data de emissão inicial e final
		if (dataEmissaoContaFinalEmString != null && !dataEmissaoContaFinalEmString.toString().trim().equalsIgnoreCase("")) {
			//Indica que o usuário informou um parâmetro para pesquisar as contas do imóvel
			peloMenosUmParametroInformado = true;
			
			//Indica no filtro para pesquisar as contas no intervalo entre a data de emissão inicial e a final
			filtroConta.adicionarParametro(new Intervalo(FiltroConta.DATA_EMISSAO, dataEmissaoContaInicial, dataEmissaoContaFinal));
		}
		
		
		//Cria as variáveis que vai armazenar as datas inicial e final, de vencimento das contas
		Date dataVencimentoContaInicial = null;
		Date dataVencimentoContaFinal = null;
		
		//Caso o usuário informou a data de vencimento da conta inicial, pesquisa as contas do imóvel referentes a data de 
		//vencimento da conta informada
		//Caso contrário, pesquisa as contas sem restrição de data de vencimento
		if (dataVencimentoContaInicialEmString != null && !dataVencimentoContaInicialEmString.toString().trim().equalsIgnoreCase("")) {
			
			//[FS0003] valida a data de vencimento da conta inicial
			try {
				dataVencimentoContaInicial = formato.parse(dataVencimentoContaInicialEmString);
			} catch (ParseException e) {
				throw new ActionServletException("atencao.datavencimentoinicial.invalida");
			}

			//Caso a data final de vencimento da conta não foi informada, replica a data inicial na final
			//Caso contrário, formata a data final 
			if(dataVencimentoContaFinalEmString == null || dataVencimentoContaFinalEmString.trim().equalsIgnoreCase("")){
				dataVencimentoContaFinal = dataVencimentoContaInicial;
			}else{
				//[FS0003] valida a data de vencimento da conta final
				try {
					dataVencimentoContaFinal = formato.parse(dataVencimentoContaFinalEmString);
				} catch (ParseException e) {
					throw new ActionServletException("atencao.datavencimentofinal.invalida");
				}
				
				//[FS0004]Caso a data de vencimento final da conta for anterior a inicial 
				if(dataVencimentoContaFinal.before(dataVencimentoContaInicial)){
					throw new ActionServletException("atencao.datavencimentofinal.menorque");
				}
			}
		
		} else{
			//Seta para nula a data final de vencimento da conta
			dataVencimentoContaFinalEmString = null;
		}

		//Caso a data de vencimento final da conta for diferente de nulo,
		//Pesquisa as contas entre a data de vencimento inicial e final
		if (dataVencimentoContaFinalEmString != null && !dataVencimentoContaFinalEmString.toString().trim().equalsIgnoreCase("")) {
			//indica que o usuário informou um parâmetro para pesquisar as contas do imóvel
			peloMenosUmParametroInformado = true;
			
			//Indica no filtro para pesquisar as contas no intervalo entre a data de vencimento inicial e a final
			filtroConta.adicionarParametro(new Intervalo(FiltroConta.DATA_VENCIMENTO, dataVencimentoContaInicial, dataVencimentoContaFinal));
		}
		
		
		//Caso o usuário informou alguma situação de conta 
		if(idSituacaoConta != null && idSituacaoConta.length >0){
			//Indica que o usuário informou um parâmetro para pesquisar as contas do imóvel
			peloMenosUmParametroInformado = true;
			
			//Laço para incluir no filtro todas as situações de conta informadas pelo usuário para pesquisa 
			for(int i=0; i< idSituacaoConta.length; i++ ){
			  if(! (new Integer(idSituacaoConta[i]).equals(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)))){
				if( i == 0 ){
					filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL_ID,idSituacaoConta[i], ParametroSimples.CONECTOR_OR,idSituacaoConta.length ));	
				}else{
				  if( i  == (idSituacaoConta.length - 1) ){
				    filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL_ID,idSituacaoConta[i]));
				  }else{
				    filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL_ID,idSituacaoConta[i], ParametroSimples.CONECTOR_OR));
				  }
				}
			  }
			}
		}else if (sessao.getAttribute("situacaoConta") != null) {
			
			filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL_ID,
					DebitoCreditoSituacao.NORMAL,ParametroSimples.CONECTOR_OR,3 ));
			
			filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL_ID,
					DebitoCreditoSituacao.RETIFICADA));
			
			filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL_ID,
					DebitoCreditoSituacao.INCLUIDA, ParametroSimples.CONECTOR_OR));
		}
		
		// [FS0006] Erro caso o usuário mandou pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}


		//Carrega no filtro os objetos necessários para pesquisa
		filtroConta.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");

		//Pesquisa as contas do imóvel, com os parâmetros indicados no filtro
		Collection colecaoContasImovel = null; // = fachada.pesquisar(filtroConta,Conta.class.getName());

		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroConta, Conta.class.getName());
		colecaoContasImovel = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		
		//Caso nenhuma conta tenha sido encontrada com os parâmetros indicados
		if (colecaoContasImovel == null || colecaoContasImovel.isEmpty()) {
			//[FS0008]Nenhuma conta cadastrado
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "Conta");
			
			//Caso o nº de contas retornadas pela pesquisa seja maior que o nº máximo de registros permitidos  
		} else if (colecaoContasImovel.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
			//[FS0007]Muitos registros encontrados
			throw new ActionServletException("atencao.pesquisa.muitosregistros");
		} else {
			//Coloca a coleção de contas do imóvel pesquisadas na sessão 
			sessao.setAttribute("colecaoContasImovel", colecaoContasImovel);
		}

		//Retorna o mapeamento contido na variável retorno
		return retorno;
	}
}
