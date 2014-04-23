package gcom.gui.faturamento.conta;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.consumo.ConsumoAnormalidadeAcao;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidade;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidadeAcao;
import gcom.micromedicao.consumo.FiltroConsumoHistorico;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ColocarRevisaoContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirColocarRevisaoConta");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        //Instância do formulário que está sendo utilizado
        ColocarRevisaoContaActionForm colocarRevisaoContaActionForm = (ColocarRevisaoContaActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();
        
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        
        //Contas selecionadas pelo usuário
        String identificadoresConta = colocarRevisaoContaActionForm.getContaSelected();
        
        String[] arrayIdentificadores = identificadoresConta.split(",");
        
        int flag = 0;
		Integer contaNaBase = null; 
		Conta conta = null;
		ConsumoHistorico consumoHistoricoAgua = null;
		ConsumoAnormalidade consumoAnormalidade = null;
		ConsumoAnormalidadeAcao consumoAnormalidadeAcao = null;
		
		for (int i = 0; i < arrayIdentificadores.length; i++) {
			// Carregando a conta que está na base
			String dadosConta = arrayIdentificadores[i];
			String[] idUltimaAlteracao = dadosConta.split("-");
			
			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			
			Calendar data = new GregorianCalendar();
			data.setTimeInMillis(new Long(idUltimaAlteracao[1].trim())
					.longValue());
			
			String time = formatter.format(data.getTime());
			
			// alterado para fazer a pesquisa por Hql e nao com filtro como estava sendo feita antes - Fernanda Paiva - 23/08/2006
			contaNaBase = fachada.pesquisarExistenciaContaParaConcorrencia(idUltimaAlteracao[0],time);
			
			
			// -------------------------------------------------------------------------------------------
			// Alterado por :  Hugo Leonardo - data : 05/07/2010 
			// Analista :  Jeferson Pedrosa.
        	// [FS0007] - Validar data de vencimento.
        	// -------------------------------------------------------------------------------------------
        	
        	// E o usuário não tenha permissão especial.
			boolean temPermissaoParaColocarContaEmRevisao = 
				fachada.verificarPermissaoEspecial(PermissaoEspecial.ENVIAR_CONTA_PARA_REVISAO,	usuarioLogado);		
			
			if(!temPermissaoParaColocarContaEmRevisao){
								
				SistemaParametro sistemaParametro = 
					this.getFachada().pesquisarParametrosDoSistema();
				
				int qtdDiasAtrasoVencimentoConta = sistemaParametro.getNumeroDiasRevisaoComPermEspecial();
				
				conta = (Conta) Util.retonarObjetoDeColecao( fachada.obterConta(contaNaBase));
				
				// SB0005 3.1.3
				// verifica se a conta esta vencida
				if(Util.compararData(conta.getDataVencimentoConta(), new Date()) == -1){
					// obtém a quantidade de dias de atraso.
					int qtdDiasVencidas = Util.obterQuantidadeDiasEntreDuasDatas(
							conta.getDataVencimentoConta(), new Date());
					
					if(qtdDiasVencidas > qtdDiasAtrasoVencimentoConta){
						// Realizar um reload na tela de manter conta
			        	httpServletRequest.setAttribute("reloadPage", "OK");
						throw new ActionServletException("atencao.necessario_permissao_especial_para_revisao_conta_para_dias_atraso", 
								Util.formatarAnoMesParaMesAno(conta.getReferencia()));
					}
				}
						
				FiltroConsumoHistorico filtroConsumoHistorico = new FiltroConsumoHistorico();
	            filtroConsumoHistorico.adicionarParametro( 
	            		new ParametroSimples( FiltroConsumoHistorico.IMOVEL_ID, conta.getImovel().getId() )  );
	            filtroConsumoHistorico.adicionarParametro( 
	            		new ParametroSimples( FiltroConsumoHistorico.LIGACAO_TIPO_ID, LigacaoTipo.LIGACAO_AGUA  ) );
	            filtroConsumoHistorico.adicionarParametro( 
	            		new ParametroSimples( FiltroConsumoHistorico.ANO_MES_FATURAMENTO, conta.getReferencia() ) );
	            Collection<ConsumoHistorico> colConsumoHistorico = Fachada.getInstancia().pesquisar( 
	            		filtroConsumoHistorico, ConsumoHistorico.class.getName() );
	            consumoHistoricoAgua = ( ConsumoHistorico ) Util.retonarObjetoDeColecao( colConsumoHistorico );

	            if(consumoHistoricoAgua != null && consumoHistoricoAgua.getConsumoAnormalidade() != null){
	            	// pesquisa Consumo Anormalidade
		            FiltroConsumoAnormalidade filtroConsumoAnormalidade = new FiltroConsumoAnormalidade();
					filtroConsumoAnormalidade.adicionarParametro( 
							new ParametroSimples( FiltroConsumoAnormalidade.ID, 
									consumoHistoricoAgua.getConsumoAnormalidade()));
					
					Collection<ConsumoAnormalidade> colConsumoAnormalidade = 
						Fachada.getInstancia().pesquisar( filtroConsumoAnormalidade, 
								ConsumoAnormalidade.class.getName() );
					
					consumoAnormalidade = ( ConsumoAnormalidade ) Util.retonarObjetoDeColecao( colConsumoAnormalidade );
					
					if(consumoAnormalidade != null && consumoAnormalidade.getIndicadorRevisaoPermissaoEspecial() != null
							&& consumoAnormalidade.getIndicadorRevisaoPermissaoEspecial().toString().equalsIgnoreCase("1")){
						
						// SB0005 3.1.4	
						// verifica se o Consumo Anormalidade exige permissão Especial
						if(consumoAnormalidade.getId().toString().equals(ConsumoAnormalidade.BAIXO_CONSUMO.toString()) 
								|| consumoAnormalidade.getId().toString().equals(ConsumoAnormalidade.ESTOURO_CONSUMO.toString())
								|| consumoAnormalidade.getId().toString().equals(ConsumoAnormalidade.ALTO_CONSUMO.toString()) ){
							
							
							// pesquisa Consumo Anormalidade
				            FiltroConsumoAnormalidadeAcao filtroConsumoAnormalidadeAcao = new FiltroConsumoAnormalidadeAcao();
				            filtroConsumoAnormalidadeAcao.adicionarParametro( 
									new ParametroSimples( FiltroConsumoAnormalidadeAcao.CONSUMO_ANORMALIDADE, 
											consumoAnormalidade.getId()));
							
							Collection<ConsumoAnormalidadeAcao> colConsumoAnormalidadeAcao = 
								Fachada.getInstancia().pesquisar( filtroConsumoAnormalidadeAcao, 
										ConsumoAnormalidadeAcao.class.getName() );
							
							consumoAnormalidadeAcao = ( ConsumoAnormalidadeAcao ) Util.retonarObjetoDeColecao( colConsumoAnormalidadeAcao );
							
							// Realizar um reload na tela de manter conta
				        	httpServletRequest.setAttribute("reloadPage", "OK");
							throw new ActionServletException("atencao.necessario_permissao_especial_para_revisao_conta_com_anormalidade",
									Util.formatarAnoMesParaMesAno(conta.getReferencia()), 
									consumoAnormalidadeAcao.getNumerofatorConsumoMes1().toString());
						}else{
							
							// Realizar um reload na tela de manter conta
				        	httpServletRequest.setAttribute("reloadPage", "OK");
							throw new ActionServletException("atencao.necessario_permissao_especial_para_revisao_conta_anormalidade_sem_permissao",
									Util.formatarAnoMesParaMesAno(conta.getReferencia()));
							
						}
						
					}
	            }
	            /*
	             * Comentado por Hugo Leonardo
	             * Data: 09/08/2010
	             * Analista: Jeferson Pedrosa.
	             * CRC: 4478
	              
	            /*
	             * Comentado por Hugo Leonardo
	             * Data: 09/08/2010
	             * Analista: Jeferson Pedrosa.
	             * CRC: 4478
	              
	            // SB0005 3.1.4
	            // Caso a situação de debito e credito for diferente de "NORMAL"
	            if( conta.getDebitoCreditoSituacaoAtual() != null 
	            		&& !conta.getDebitoCreditoSituacaoAtual().getId().toString().equals("0")){
	            	
	            	// Realizar um reload na tela de manter conta
		        	httpServletRequest.setAttribute("reloadPage", "OK");
					throw new ActionServletException("atencao.necessario_permissao_especial_para_revisao_conta_com_situacao_debito_credito", 
							Util.formatarAnoMesParaMesAno(conta.getReferencia()));
	            	
	            }
	            */
			}
			
			
			// Verificar atualização realizada antes por outro usuário
			
			if (contaNaBase == null || contaNaBase.equals("")) {
				httpServletRequest.setAttribute("reloadPage", "OK");
				flag = 1;
				sessao.setAttribute("erroConcorrencia","erroConcorrencia");
			}
		}

        //MotivoRevisaoConta selecinado pelo usuário
        ContaMotivoRevisao contaMotivoRevisao = new ContaMotivoRevisao();
        contaMotivoRevisao.setId(new Integer(colocarRevisaoContaActionForm.getMotivoRevisaoContaID()));
        
        
        if (sessao.getAttribute("colecaoContaImovel") != null && (identificadoresConta != null &&
        	!identificadoresConta.equalsIgnoreCase("")) && flag == 0){
        
        	Collection<Conta> colecaoContaImovel = (Collection) sessao.getAttribute("colecaoContaImovel");
        	
            //Cancelando uma ou várias contas
        	fachada.colocarRevisaoConta(colecaoContaImovel, identificadoresConta, contaMotivoRevisao,
        	usuarioLogado);
        	
        	//Realizar um reload na tela de manter conta
        	httpServletRequest.setAttribute("reloadPage", "OK");
        	
        }
        
                
        return retorno;
    }

}

