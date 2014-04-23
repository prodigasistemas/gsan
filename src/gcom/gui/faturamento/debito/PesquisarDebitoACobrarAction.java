package gcom.gui.faturamento.debito;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.FiltroDebitoACobrar;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
 * Realiza a pesquisa de débitos a cobrar de imóvel de acordo com os parâmetros informados na página 
 *
 * @author Pedro Alexandre
 * @date 13/03/2006
 */
public class PesquisarDebitoACobrarAction extends GcomAction {
	
	/**
	 * Pesquisa os débitos a cobrar existentes para o imóvel
	 *
	 * [UC0271] Pesquisar Débito a Cobrar
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
	 * @date 13/03/2006
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

		//Seta o mapeamento de retorno para a tela de resultado da pesquisa de débitos a cobrar do imóvel
		ActionForward retorno = actionMapping.findForward("listaDebitoACobrar");

		//Cria uma instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		//Cria uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		//Recupera o form de pesquisa de débitos a cobrar do imóvel
		PesquisarDebitoACobrarActionForm pesquisarDebitoACobrarActionForm = (PesquisarDebitoACobrarActionForm) actionForm;
		
		// Recupera os parâmetros do form
		String idImovel =  pesquisarDebitoACobrarActionForm.getIdImovel();
		String referenciaDebitoInicial =  pesquisarDebitoACobrarActionForm.getReferenciaDebitoInicial();
		String referenciaDebitoFinal =  pesquisarDebitoACobrarActionForm.getReferenciaDebitoFinal();
		String dataGeracaoDebitoInicialEmString =  pesquisarDebitoACobrarActionForm.getDataGeracaoDebitoInicial();
		String dataGeracaoDebitoFinalEmString =  pesquisarDebitoACobrarActionForm.getDataGeracaoDebitoFinal();
		String[] idSituacaoDebitoACobrar =  pesquisarDebitoACobrarActionForm.getIdSituacaoDebitoACobrar();
		String[] idTipoDebito =  pesquisarDebitoACobrarActionForm.getIdTipoDebitoSelecionados();
		
		//Cria o filtro de débito a cobrar e seta a ordenação de resultado da pesquisa
		//pelo tipo de débito do débito a cobrar
		FiltroDebitoACobrar filtroDebitoACobrar = new FiltroDebitoACobrar();
		filtroDebitoACobrar.setCampoOrderBy(FiltroDebitoACobrar.DEBITO_TIPO);

		//Cria flag que vai indicar se o usuário informou ao menos um parâmetro para pesquisar
		boolean peloMenosUmParametroInformado = false;

		//Caso o usuário informou o código do imóvel, pesquisa todos os débitos a cobrar relacionados com o imóvel
		//caso contrário indica que o usuário não informou o imóvel
		if(idImovel != null && !idImovel.trim().equalsIgnoreCase("")){
			
			//Indica que o usuário informou um parâmetro para pesquisar os débitos a cobrar do imóvel 
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
        		throw new ActionServletException(
                        "atencao.naocadastrado", null, "cliente do tipo usuário foi");
        	}
        	
        	//Recupera o relacionamento entre cliente e imóvel da coleção pesquisada 
        	ClienteImovel objetoClienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoClienteImovel);
        	        	
        	//Seta no form de pesquisar débito a cobrar todos os dados de imóvel para exibição na página de resultado da pesquisa
        	pesquisarDebitoACobrarActionForm.setInscricaoImovel(objetoImovel.getInscricaoFormatada());
        	pesquisarDebitoACobrarActionForm.setNomeClienteUsuario(objetoClienteImovel.getCliente().getNome());
        	pesquisarDebitoACobrarActionForm.setSituacaoAguaImovel(objetoImovel.getLigacaoAguaSituacao().getDescricao());        	
        	pesquisarDebitoACobrarActionForm.setSituacaoEsgotoImovel(objetoImovel.getLigacaoEsgotoSituacao().getDescricao());
        	
			//Seta no filtro de débito a cobrar o código do imóvel informado pelo usuário
        	filtroDebitoACobrar.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.IMOVEL_ID, idImovel));
		}else{
			throw new ActionServletException("atencao.naoinformado",null, "Imóvel");
		}
		
		//Caso o usuário tenha informado a referência inicial do débito a cobrar, pesquisa os débito a cobrar entre a referência inicial e final, replicando 
		//a referência inicial na final caso essa não tenha sido informada
		//Caso contrário seta a referência final para vazio, e pesquisa os débito a cobrar para todas as referências existentes
		if (referenciaDebitoInicial != null && !referenciaDebitoInicial.trim().equalsIgnoreCase("")) {
			
			//Formata a referência inicial informada, para o formato de ano e depois mês
			referenciaDebitoInicial = Util.formatarMesAnoParaAnoMesSemBarra((String) pesquisarDebitoACobrarActionForm.getReferenciaDebitoInicial());
			
			//Caso a referência final não tenha sido informada , replica a referência inicial na final
			//Caso contrário, formata a referência final para ano e mês
			if(referenciaDebitoFinal == null || referenciaDebitoFinal.trim().equalsIgnoreCase("")){
				//Replica a referência inicial na referência final
				referenciaDebitoFinal = referenciaDebitoInicial;
			}else{
				//Formata a referência final informada, para o formato de ano e depois mês
				referenciaDebitoFinal = Util.formatarMesAnoParaAnoMesSemBarra((String) pesquisarDebitoACobrarActionForm.getReferenciaDebitoFinal());
				
				//[FS0002]Caso a referência final do débito a cobrar seja anterior a inicial 
				if((new Integer(referenciaDebitoInicial)).intValue() > (new Integer (referenciaDebitoFinal)).intValue()){
					throw new ActionServletException("atencao.referenciafinal.menorque");
				}
			}
		} else{
			//Seta a referência final da conta para nula
			referenciaDebitoFinal = null; 
		}

		//Caso a referência final do débito a cobrar esteja diferente de nulo,
		//Pesquisa os débitos a cobrar entre a referência inicial e final
		if (referenciaDebitoFinal != null && !referenciaDebitoFinal.trim().equalsIgnoreCase("")) {
			//Indica que o usuário informou um parâmetro para pesquisar os débitos a cobrar do imóvel
			peloMenosUmParametroInformado = true;
			
			//Indica no filtro para pesquisar os débitos a cobrar no intervalo entre a referência inicial e a final
			filtroDebitoACobrar.adicionarParametro(new MaiorQue(FiltroDebitoACobrar.REFERENCIA_DEBITO, referenciaDebitoInicial, ParametroSimples.CONECTOR_AND));
			filtroDebitoACobrar.adicionarParametro(new MenorQue(FiltroDebitoACobrar.REFERENCIA_DEBITO, referenciaDebitoFinal));
		}

		//Cria o formato da data
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		//Cria as variáveis que vai armazenar as datas inicial e final, da geração do débito
		Date dataGeracaoDebitoInicial = null;
		Date dataGeracaoDebitoFinal = null;
		
		
		 
		//Caso o usuário informou a data de geração do débito a cobrar inicial, pesquisa os débitos a cobrar do imóvel referentes a data de 
		//geração do débito informada
		//Caso contrário, pesquisa os débitos a cobrar sem restrição de data de geração
		if (dataGeracaoDebitoInicialEmString != null && !dataGeracaoDebitoInicialEmString.toString().trim().equalsIgnoreCase("")) {
			
			//[FS0003] Valida a data de geração do débito inicial
			try {
				dataGeracaoDebitoInicial = formato.parse(dataGeracaoDebitoInicialEmString);
				
				//dataGeracaoDebitoInicialFormatoTimeStamp = new Timestamp(dataGeracaoDebitoInicial.getTime());
			} catch (ParseException e) {
				throw new ActionServletException("atencao.datageracaoinicial.invalida");
			}

			//Caso a data final de geração do débito não foi informada, replica a data inicial na final
			//Caso contrário, formata a data final 
			if(dataGeracaoDebitoFinalEmString == null || dataGeracaoDebitoFinalEmString.trim().equalsIgnoreCase("")){
				dataGeracaoDebitoFinal = dataGeracaoDebitoInicial;
				//dataGeracaoDebitoFinalFormatoTimeStamp = dataGeracaoDebitoInicialFormatoTimeStamp;
			}else{
				
				//[FS0003] Valida a data de geração do débito final
				try {
					dataGeracaoDebitoFinal = formato.parse(dataGeracaoDebitoFinalEmString);
					
					//dataGeracaoDebitoFinalFormatoTimeStamp = new Timestamp(dataGeracaoDebitoFinal.getTime());
				} catch (ParseException e) {
					throw new ActionServletException("atencao.datageracaofinal.invalida");
				}
				
				//[FS0004]Caso a data de geração final do débito seja anterior a inicial 
				//levanta a exceção para o usuário indicando que a data final é anterior a data inicial
				if(dataGeracaoDebitoFinal.before(dataGeracaoDebitoInicial)){
					throw new ActionServletException("atencao.datageracaofinal.menorque");
				}
			}
		
		} else{
			//Seta para nula a data final de geração do débito
			dataGeracaoDebitoFinalEmString = null;
		}

		//Cria uma variável que vai auxiliar na data final de geração,
		//para setar a data para o último segundo do dia informado
		Calendar data = Calendar.getInstance();
		
		//Caso a data de geração final do débito esteja diferente de nulo,
		//Pesquisa os débitos entre a data de geração inicial e final
		if (dataGeracaoDebitoFinalEmString != null && !dataGeracaoDebitoFinalEmString.toString().trim().equalsIgnoreCase("")) {
			//Indica que o usuário informou um parâmetro para pesquisar os débitos a cobrar do imóvel
			peloMenosUmParametroInformado = true;
		
			//Seta a data final de geração no calendar
			data.setTime(dataGeracaoDebitoFinal);
			
			//Seta a data final de geração do débito para o último segundo do dia informado 
			data.set(Calendar.HOUR, data.getMaximum(Calendar.HOUR_OF_DAY));
			data.set(Calendar.MINUTE, data.getMaximum(Calendar.MINUTE));
			data.set(Calendar.SECOND, data.getMaximum(Calendar.SECOND));
			data.set(Calendar.MILLISECOND, data.getMaximum(Calendar.MILLISECOND));
			
			//Recupera a data final 
			dataGeracaoDebitoFinal = data.getTime();
			
			//Indica no filtro para pesquisar os débitos a cobrar no intervalo entre a data de geração inicial e a final
			filtroDebitoACobrar.adicionarParametro(new Intervalo(FiltroDebitoACobrar.GERACAO_DEBITO, new Timestamp(dataGeracaoDebitoInicial.getTime()), new Timestamp(dataGeracaoDebitoFinal.getTime())));
		}
		
		//Caso o usuário informou algum tipo de débito do débito a cobrar 
		if(idTipoDebito != null && 
				!idTipoDebito[0].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)&&
				idTipoDebito.length >0){
			  //Indica que o usuário informou um parâmetro para pesquisar
			  peloMenosUmParametroInformado = true;
				
			  //Laço para setar no filtro de guia todos os tipos de débitos selecionados
			  for(int i=0; i< idTipoDebito.length; i++ ){
			    if(! (new Integer(idTipoDebito[i]).equals(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)))){
					  
				  if(idTipoDebito.length == 1){
					  filtroDebitoACobrar.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.DEBITO_TIPO_ID,idTipoDebito[i]));
				  }else{
				    if( i == 0 ){
				    	filtroDebitoACobrar.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.DEBITO_TIPO_ID,idTipoDebito[i], ParametroSimples.CONECTOR_OR,idTipoDebito.length ));	
					}else{
					  if( i  == (idTipoDebito.length - 1) ){
						  filtroDebitoACobrar.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.DEBITO_TIPO_ID,idTipoDebito[i]));
					  }else{
						  filtroDebitoACobrar.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.DEBITO_TIPO_ID,idTipoDebito[i], ParametroSimples.CONECTOR_OR));
					  }
					}
				  }
				}
			  }
			}
		
		
		//Caso o usuário informou alguma situação de débito do débito a cobrar 
		if(idSituacaoDebitoACobrar != null && 
				!idSituacaoDebitoACobrar[0].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)&&
				idSituacaoDebitoACobrar.length >0){
			//Indica que o usuário informou um parâmetro para pesquisar os débitos a cobrar do imóvel
			peloMenosUmParametroInformado = true;
			
			//Laço para incluir no filtro todas as situações de débito a cobrar informadas pelo usuário para pesquisa 
			for(int i=0; i< idSituacaoDebitoACobrar.length; i++ ){
			  if(! (new Integer(idSituacaoDebitoACobrar[i]).equals(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)))){
				  
			    if(idSituacaoDebitoACobrar.length==1){
				  filtroDebitoACobrar.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.DEBITO_CREDITO_SITUACAO_ATUAL_ID,idSituacaoDebitoACobrar[i]));	
				}else{  
				  
				  if( i == 0 ){
					filtroDebitoACobrar.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.DEBITO_CREDITO_SITUACAO_ATUAL_ID,idSituacaoDebitoACobrar[i], ParametroSimples.CONECTOR_OR,idSituacaoDebitoACobrar.length ));	
				  }else{
				    if( i  == (idSituacaoDebitoACobrar.length - 1) ){
				      filtroDebitoACobrar.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.DEBITO_CREDITO_SITUACAO_ATUAL_ID,idSituacaoDebitoACobrar[i]));
				    }else{
				      filtroDebitoACobrar.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.DEBITO_CREDITO_SITUACAO_ATUAL_ID,idSituacaoDebitoACobrar[i], ParametroSimples.CONECTOR_OR));
				    }
				  }
			    }  
			  }
			}
		}
		
		// [FS0006] Erro caso o usuário mandou pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}

		//Carrega no filtro os objetos necessários para pesquisa
		filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");

		//Pesquisa os débitos a cobrar do imóvel, com os parâmetros indicados no filtro
		Collection colecaoDebitosACobrar = null;//fachada.pesquisar(filtroDebitoACobrar,DebitoACobrar.class.getName());
		
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroDebitoACobrar, DebitoACobrar.class.getName());
		colecaoDebitosACobrar = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");

		//Caso nenhum débito a cobrar tenha sido encontrado com os parâmetros indicados
		if (colecaoDebitosACobrar == null || colecaoDebitosACobrar.isEmpty()) {
			//[FS0008]Nenhum débito a cobrar cadastrado
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "Débito a Cobrar");
			
			//Caso o nº de débitos a cobrar retornados pela pesquisa seja maior que o nº máximo de registros permitidos
		} else if (colecaoDebitosACobrar.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
			//[FS0007]Muitos registros encontrados
			throw new ActionServletException("atencao.pesquisa.muitosregistros");
		} else {
			//Coloca a coleção de débitos a cobrar do imóvel pesquisados na sessão 
			sessao.setAttribute("colecaoDebitosACobrar", colecaoDebitosACobrar);
		}

		//Retorna o mapeamento contido na variável retorno
		return retorno;
	}
}
