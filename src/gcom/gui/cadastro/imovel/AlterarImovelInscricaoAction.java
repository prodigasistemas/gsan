package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelInscricaoAlterada;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelInscricaoAlterada;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoAtividade;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoAtividadeCronograma;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

public class AlterarImovelInscricaoAction extends GcomAction {
	
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        
        SistemaParametro sistemaParametro = this.getSistemaParametro();

        DynaValidatorForm alterarImovelInscricaoActionForm = (DynaValidatorForm) sessao.getAttribute("AlterarImovelInscricaoActionForm");

        //Dados da inscrição de origem
        String localidadeOrigemID = (String) alterarImovelInscricaoActionForm.get("localidadeOrigemID");
        String setorComercialOrigemCD = (String) alterarImovelInscricaoActionForm.get("setorComercialOrigemCD");
        String quadraOrigemNM = (String) alterarImovelInscricaoActionForm.get("quadraOrigemNM");
        String loteOrigemTemp = (String) alterarImovelInscricaoActionForm.get("loteOrigem");
        //short loteOrigem = 0;

        if (loteOrigemTemp != null && !loteOrigemTemp.equalsIgnoreCase("")) {
            //loteOrigem = Short.parseShort(loteOrigemTemp);
        }

        boolean flagContinuar = true;

        Localidade localidadeOrigem = (Localidade) validarCampo(localidadeOrigemID, null, 1);
        SetorComercial setorComercialOrigem = null;
        Quadra quadraOrigem = null;

        //Validação dos campos da inscrição de origem (FS0002, FS0003, FS0004)
        if (localidadeOrigem == null) {
            //Nenhuma localidade encontrada
            flagContinuar = false;
            throw new ActionServletException("atencao.pesquisa.localidade_origem_inexistente");
        }
       
        if (setorComercialOrigemCD != null && !setorComercialOrigemCD.equalsIgnoreCase("")) {

            setorComercialOrigem = (SetorComercial) validarCampo( localidadeOrigem.getId().toString(), setorComercialOrigemCD, 2);

            if (setorComercialOrigem == null) {
                //Nenhum Setor Comercial encontrado
                flagContinuar = false;
                throw new ActionServletException("atencao.pesquisa.setor_origem_inexistente");
            }
           
            if (quadraOrigemNM != null && !quadraOrigemNM.equalsIgnoreCase("")) {

                quadraOrigem = (Quadra) validarCampo( setorComercialOrigem.getId().toString(), quadraOrigemNM, 3);

                if (quadraOrigem == null) {
                    //Nenhuma Quadra encontrada
                    flagContinuar = false;
                    throw new ActionServletException(
                            "atencao.pesquisa.quadra_origem_inexistente");
                }
            }
        }
        

        //Dados da inscrição de destino
        String localidadeDestinoID = (String) alterarImovelInscricaoActionForm.get("localidadeDestinoID");
        String setorComercialDestinoCD = (String) alterarImovelInscricaoActionForm.get("setorComercialDestinoCD");
        String quadraDestinoNM = (String) alterarImovelInscricaoActionForm.get("quadraDestinoNM");
        String loteDestinoTemp = (String) alterarImovelInscricaoActionForm.get("loteDestino");
       
        //Verificar a existencia de Setor alternativo
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
    	filtroSetorComercial.adicionarParametro( new ParametroSimples ( FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercialDestinoCD ) );
    	
    	Collection setorComercial = this.getFachada().pesquisar( filtroSetorComercial, SetorComercial.class.getName() );

    	Iterator iteratorSetorComercial = setorComercial.iterator();
    	SetorComercial setor = null;
    
    	while ( iteratorSetorComercial.hasNext() ) {
    	
    		setor = (SetorComercial) iteratorSetorComercial.next();
            
    		if ( setor.getIndicadorSetorAlternativo().equals( ConstantesSistema.INDICADOR_USO_ATIVO ) &&
    				setor.getLocalidade().getId().equals( new Integer( localidadeDestinoID ) ) &&
    						  ("" + setor.getCodigo()).equals(setorComercialDestinoCD) )  {
    			
    			throw new ActionServletException("atencao.setor_comercial_alternativo_nao_pode_ser_informado");
    		}
    	}
        
        short loteDestino = 0;

        if (loteDestinoTemp != null && !loteDestinoTemp.equalsIgnoreCase("")) {
            loteDestino = Short.parseShort(loteDestinoTemp);
        }

        Localidade localidadeDestino = (Localidade) validarCampo(localidadeDestinoID, null, 1);
        SetorComercial setorComercialDestino = null;
        Quadra quadraDestino = null;

        if (flagContinuar) {

            //Validação dos campos da inscrição de destino (FS0002, FS0003, FS0004)
            if (localidadeDestino == null) {
                //Nenhuma localidade encontrada
                flagContinuar = false;
                throw new ActionServletException("atencao.pesquisa.localidade_destino_inexistente");
            }
			if (setorComercialDestinoCD != null && !setorComercialDestinoCD.equalsIgnoreCase("")) {

			    setorComercialDestino = (SetorComercial) validarCampo(localidadeDestino.getId().toString(), setorComercialDestinoCD, 2);

			    if (setorComercialDestino == null) {
			        //Nenhum Setor Comercial encontrado
			        flagContinuar = false;
			        throw new ActionServletException("atencao.pesquisa.setor_destino_inexistente");
			    }
				if (quadraDestinoNM != null && !quadraDestinoNM.equalsIgnoreCase("")) {

				    quadraDestino = (Quadra) validarCampo( setorComercialDestino.getId().toString(), quadraDestinoNM, 3);

				    if (quadraDestino == null) {
				        //Nenhuma Quadra encontrada
				        flagContinuar = false;
				        throw new ActionServletException("atencao.pesquisa.quadra_destino_inexistente");
				    }
				}
			}
        }

        // [FS0005 - Verificar preenchimento dos campos]
        if (flagContinuar) {
        	
            if (setorComercialOrigem == null && setorComercialDestino == null) {
                flagContinuar = compararObjetos(localidadeOrigem,localidadeDestino, 1);
                if (!flagContinuar) {
                    throw new ActionServletException("atencao.pesquisa.setorcomercial_nao_correspondente");
                }
                
            } else if (setorComercialOrigem == null && setorComercialDestino != null) {
                if (quadraDestino == null) {
                    //Verificar se o setor de destino existe na localidade de origem
                    setorComercialOrigem = (SetorComercial) pesquisarRetornarObjeto(setorComercialDestino, localidadeOrigem, 1);
                    //Verificar se existe as mesmas quadras do setor de destino no setor de origem
                    if (setorComercialOrigem == null) {
                        flagContinuar = false;
                        throw new ActionServletException("atencao.pesquisa.setorcomercial_nao_correspondente");
                    }
					flagContinuar = compararObjetos(setorComercialOrigem, setorComercialDestino, 2);
					//Valor original do setor de origem
					setorComercialOrigem = null;
					if (!flagContinuar) {
					    throw new ActionServletException("atencao.pesquisa.quadra_nao_correspondente");
					}
                } else {
                    //Verificar se o setor de destino existe na localidade de origem
                    setorComercialOrigem = (SetorComercial) pesquisarRetornarObjeto(setorComercialDestino, localidadeOrigem, 1);
                    //Verificar se a quadra de destino existe na localidade de origem
                    if (setorComercialOrigem == null) {
                        flagContinuar = false;
                        throw new ActionServletException("atencao.pesquisa.setorcomercial_nao_correspondente");
                    }
					flagContinuar = pesquisarObjeto(quadraDestino, setorComercialOrigem, 2);
					//Valor original do setor de origem
					setorComercialOrigem = null;
					if (!flagContinuar) {
					    throw new ActionServletException("atencao.pesquisa.quadra_nao_correspondente");
					}
                }
            } else {
                if (quadraOrigem == null && quadraDestino == null) {
                    //Verificar se o setor de origem existe na localidade de destino
                    flagContinuar = pesquisarObjeto(setorComercialOrigem, localidadeDestino, 1);
                    //Verificar se existe as mesmas quadras do setor de origem no setor de destino
                    if (flagContinuar) {
                        flagContinuar = compararObjetos(setorComercialOrigem,setorComercialDestino, 2);
                        if (!flagContinuar) {
                            throw new ActionServletException("atencao.pesquisa.quadra_nao_correspondente");
                        }
                    } else {
                        throw new ActionServletException("atencao.pesquisa.setorcomercial_nao_correspondente");
                    }
                } else if (quadraOrigem == null && quadraDestino != null) {
                    //Verificar se o setor de destino existe na localidade de origem
                    flagContinuar = pesquisarObjeto(setorComercialDestino, localidadeOrigem, 1);
                    //Verificar se a quadra de destino existe no setor de origem
                    if (flagContinuar) {
                        flagContinuar = pesquisarObjeto(quadraDestino, setorComercialOrigem, 2);
                        if (!flagContinuar) {
                            throw new ActionServletException("atencao.pesquisa.quadra_nao_correspondente");
                        }

                    } else {
                        throw new ActionServletException("atencao.pesquisa.setorcomercial_nao_correspondente");
                    }

                } else if (quadraOrigem != null && quadraDestino == null){
                    //Verificar se o setor de destino existe na localidade de origem
                    flagContinuar = pesquisarObjeto(setorComercialDestino, localidadeOrigem, 1);
                    //Verificar se a quadra de origem existe no setor de destino
                    if (flagContinuar) {
                        flagContinuar = pesquisarObjeto(quadraOrigem, setorComercialDestino, 2);
                        if (!flagContinuar) {
                            throw new ActionServletException("atencao.pesquisa.quadra_nao_correspondente");
                        }
                    } else {
                        throw new ActionServletException("atencao.pesquisa.setorcomercial_nao_correspondente");
                    }
                }
            }
        }

        // [FS0006 - Verificar pré-requisitos para alteração da inscrição]
        if (flagContinuar) {
            Collection colecaoSetorOrigem = null;
            Iterator iteratorSetorOrigem = null;
           // Collection colecaoSetorDestino = null;
           // Iterator iteratorSetorDestino = null;
            Collection colecaoQuadraOrigem = null;
            Iterator iteratorQuadraOrigem = null;
           // Collection colecaoQuadraDestino = null;
           // Iterator iteratorQuadraDestino = null;

            if (setorComercialOrigem == null && setorComercialDestino == null) {

                colecaoSetorOrigem = pesquisarDependentes(localidadeOrigem, 1);
                if (colecaoSetorOrigem != null && !colecaoSetorOrigem.isEmpty()) {
                    iteratorSetorOrigem = (colecaoSetorOrigem).iterator();
                    while (iteratorSetorOrigem.hasNext()) {
                        setorComercialOrigem = (SetorComercial) iteratorSetorOrigem
                                .next();
                        colecaoQuadraOrigem = pesquisarDependentes(setorComercialOrigem, 2);
                        if (colecaoQuadraOrigem != null && !colecaoQuadraOrigem.isEmpty()) {
                            iteratorQuadraOrigem = (colecaoQuadraOrigem).iterator();
                            while (iteratorQuadraOrigem.hasNext()) {
                                quadraOrigem = (Quadra) iteratorQuadraOrigem.next();
                                if (!verificarSituacaoRota(quadraOrigem)) {
                                    setorComercialDestino = (SetorComercial) pesquisarRetornarObjeto(setorComercialOrigem, localidadeDestino, 1);
                                    quadraDestino = (Quadra) pesquisarRetornarObjeto(quadraOrigem,setorComercialDestino, 2);
                                    if (verificarSituacaoRota(quadraDestino)) {
                                        flagContinuar = false;
                                        throw new ActionServletException(
                                                "atencao.pesquisa.quadra_rota_nao_faturada");
                                    }
                                }
                            }
                        }
                    }
                }
                setorComercialOrigem = null;
                setorComercialDestino = null;
                quadraOrigem = null;
                quadraDestino = null;
            } else if (setorComercialOrigem == null && setorComercialDestino != null) {

                setorComercialOrigem = (SetorComercial) pesquisarRetornarObjeto( setorComercialDestino, localidadeOrigem, 1);

                if (quadraDestino == null) {

                    colecaoQuadraOrigem = pesquisarDependentes(setorComercialOrigem, 2);
                    if (colecaoQuadraOrigem != null && !colecaoQuadraOrigem.isEmpty()) {
                        iteratorQuadraOrigem = (colecaoQuadraOrigem).iterator();
                        while (iteratorQuadraOrigem.hasNext()) {
                            quadraOrigem = (Quadra) iteratorQuadraOrigem.next();
                            
                            if (!verificarSituacaoRota(quadraOrigem)) {
                                quadraDestino = (Quadra) pesquisarRetornarObjeto(quadraOrigem, setorComercialDestino, 2);
                            
                                if (verificarSituacaoRota(quadraDestino)) {
                                
                                	flagContinuar = false;
                                    throw new ActionServletException("atencao.pesquisa.quadra_rota_nao_faturada");
                                }
                            }
                        }
                    }
                    quadraOrigem = null;
                    quadraDestino = null;

                } else {

                    quadraOrigem = (Quadra) pesquisarRetornarObjeto(
                            quadraDestino, setorComercialOrigem, 2);
                    if (!verificarSituacaoRota(quadraOrigem)) {
                        if (verificarSituacaoRota(quadraDestino)) {
                            flagContinuar = false;
                            throw new ActionServletException( "atencao.pesquisa.quadra_rota_nao_faturada");
                        }
                    }
                    quadraOrigem = null;
                }

                setorComercialOrigem = null;
            } else {
                if (quadraOrigem == null && quadraDestino == null) {

                    colecaoQuadraOrigem = pesquisarDependentes( setorComercialOrigem, 2);
                    
                    if (colecaoQuadraOrigem != null && !colecaoQuadraOrigem.isEmpty()) {
                        iteratorQuadraOrigem = (colecaoQuadraOrigem).iterator();
                        while (iteratorQuadraOrigem.hasNext()) {
                            quadraOrigem = (Quadra) iteratorQuadraOrigem.next();
                            if (!verificarSituacaoRota(quadraOrigem)) {
                                quadraDestino = (Quadra) pesquisarRetornarObjeto(
                                        quadraOrigem, setorComercialDestino, 2);
                                if (verificarSituacaoRota(quadraDestino)) {
                                    flagContinuar = false;
                                    throw new ActionServletException( "atencao.pesquisa.quadra_rota_nao_faturada");
                                }
                            }
                        }
                    }
                    quadraOrigem = null;
                    quadraDestino = null;

                } else if (quadraOrigem == null && quadraDestino != null) {

                    quadraOrigem = (Quadra) pesquisarRetornarObjeto( quadraDestino, setorComercialOrigem, 2);
                    if (!verificarSituacaoRota(quadraOrigem)) {
                        if (verificarSituacaoRota(quadraDestino)) {
                            flagContinuar = false;
                            throw new ActionServletException("atencao.pesquisa.quadra_rota_nao_faturada");
                        }
                    }
                    quadraOrigem = null;

                } else {

                    if (!verificarSituacaoRota(quadraOrigem)) {
                        if (verificarSituacaoRota(quadraDestino)) {
                            flagContinuar = false;
                            throw new ActionServletException("atencao.pesquisa.quadra_rota_nao_faturada");
                        }
                    }

                }
            }
        }

        // Continuaçao [FS0006 - Verificar pré-requisitos para alteração da
        // inscrição]
        if (flagContinuar) {

            /*
             * Prepara os objetos para a pesquisa dos imoveis que estão
             * localizados na inscrição de origem
             */

            Collection<Object> colecaoImovel = null;
            if (setorComercialOrigem != null) {
                if (quadraOrigem != null) {
                    if (!loteOrigemTemp.equals("")) {
                        colecaoImovel = fachada.pesquisarImovel(
                                localidadeOrigem.getId(), setorComercialOrigem
                                        .getId(), quadraOrigem.getId(),
                                new Short(loteOrigemTemp));
                    } else {
                        colecaoImovel = fachada.pesquisarImovel(
                                localidadeOrigem.getId(), setorComercialOrigem
                                        .getId(), quadraOrigem.getId(), null);
                    }
                } else {
                    colecaoImovel = fachada.pesquisarImovel(localidadeOrigem
                            .getId(), setorComercialOrigem.getId(), null, null);
                }
            } else {
                colecaoImovel = fachada.pesquisarImovel(localidadeOrigem
                        .getId(), null, null, null);
            }

            Imovel imovel = new Imovel();

            if (colecaoImovel == null || colecaoImovel.isEmpty()) {
                flagContinuar = false;
                throw new ActionServletException(
                        "atencao.pesquisa.imovel_inexistente");
            }
			Iterator itImovel = (colecaoImovel).iterator();

			
			//se descomentar a validação da situação do imóvel,
			//verificar caso de uso. Verificação não é p ser feita por CBST_ID<>Nulo.
			//Validando a situação do imóvel
			while (itImovel.hasNext()) {
                    imovel = (Imovel) itImovel.next();

                    if (imovel.getFaturamentoSituacaoTipo() != null) {
                        flagContinuar = false;
                        throw new ActionServletException(
                                "atencao.pesquisa.imovel_situacao_especial_faturamento");
                    } else if (imovel.getCobrancaSituacaoTipo() != null) {
                        flagContinuar = false;
                        throw new ActionServletException(
                                "atencao.pesquisa.imovel_situacao_especial_cobranca");
                    }
                }
             

			// Preparação dos objetos do tipo Imovel
			// ===========================
			itImovel = (colecaoImovel).iterator();

			SetorComercial setorComercialDestinoAuxiliar = new SetorComercial();
			Quadra quadraDestinoAuxiliar = new Quadra();
			Imovel imovelAuxiliar = new Imovel();

			// Array onde serão armazenados os imóveis que sofrerão
			// alterações.
			int indexArray = 0;
			Imovel[] imoveisAlteracao = new Imovel[colecaoImovel.size()];
			while (itImovel.hasNext()) {
			    imovel = (Imovel) itImovel.next();

			    imovel.setLocalidade(localidadeDestino);
			    Integer idQuadraOrigem = imovel.getQuadra().getId();
			    setorComercialDestinoAuxiliar = null;
			    quadraDestinoAuxiliar = null;

			    if (setorComercialDestino != null) {
			        imovel.setSetorComercial(setorComercialDestino);
			        if (quadraDestino != null) {
			            imovel.setQuadra(quadraDestino);
			            if (loteDestinoTemp != null
			                    && !loteDestinoTemp.equalsIgnoreCase("")) {
			                imovel.setLote(loteDestino);
			            }
			        } else {
			            //Pesquisa os objetos equivalentes na localidade de
			            // destino
			            quadraDestinoAuxiliar = (Quadra) pesquisarRetornarObjeto(
			                    imovel.getQuadra(), setorComercialDestino,
			                    2);
			            imovel.setQuadra(quadraDestinoAuxiliar);
			            if (loteDestinoTemp != null
			                    && !loteDestinoTemp.equalsIgnoreCase("")) {
			                imovel.setLote(loteDestino);
			            }
			        }
			    } else {
			        //Pesquisa os objetos equivalentes na localidade de
			        // destino
			        setorComercialDestinoAuxiliar = (SetorComercial) pesquisarRetornarObjeto(
			                imovel.getSetorComercial(), localidadeDestino,
			                1);
			        quadraDestinoAuxiliar = (Quadra) pesquisarRetornarObjeto(
			                imovel.getQuadra(),
			                setorComercialDestinoAuxiliar, 2);
			        imovel.setSetorComercial(setorComercialDestinoAuxiliar);
			        imovel.setQuadra(quadraDestinoAuxiliar);
			        if (loteDestinoTemp != null
			                && !loteDestinoTemp.equalsIgnoreCase("")) {
			            imovel.setLote(loteDestino);
			        }
			        
			    }

			    // [FS0008 - Verificar duplicidade de inscrição]
			    imovelAuxiliar = (Imovel) pesquisarRetornarObjeto(imovel,
			            imovel.getQuadra(), 3);

			    if (imovelAuxiliar != null) {
			    	sessao.removeAttribute("acao");
			    	throw new ActionServletException(
			                "atencao.imovel.duplicidade_inscricao");
			    }
			    
			    /**
				 *  [SB0001] – Verificar Alteração da Inscrição dos Imóveis
				 *  @author Arthur Carvalho
				 *  @date 18/09/2010
				 */
			    Integer idQuadraDestino = null ;
			    if ( quadraDestinoAuxiliar != null && quadraDestinoAuxiliar.getId() != null ) {
			    	idQuadraDestino =  quadraDestinoAuxiliar.getId();
			    } else if ( quadraDestino != null && quadraDestino.getId() != null ) {
			    	idQuadraDestino = quadraDestino.getId();
			    }
				retorno = this.verificaAlteracaoInscricaoImovel( imovel, idQuadraOrigem, idQuadraDestino, sistemaParametro, 
						fachada, httpServletRequest, actionMapping , sessao );
				
			    imoveisAlteracao[indexArray] = imovel;
			    indexArray++;

			}
			
			
			
			// Data: 06/07/2010
			// Analista: Fátima Sampaio
			// Desenvolvedor: Hugo Amorim
			// CRC4701
			// [FS0006] - Verificar pré-requisitos para alteração da inscrição
			
			int contadorImoveisEmProcessoFaturamento = 0;
			boolean flagNaoConfirmado = false;
			
			String confirmado = httpServletRequest.getParameter("confirmado");
			
			Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
			
			// Se tiver sido aprensentada a tela confirmação e o usuario confirmou.
			if ( confirmado != null && confirmado.equalsIgnoreCase("ok") ) {
				// 	Caso o usuário confirme, não efetivar a alteração da inscrição na tabela IMOVEL 
				// e gravar as informações da inscrição alterada na tabela IMOVEL_INSCR_ALTERADA. 
				
				for ( indexArray = 0; indexArray < imoveisAlteracao.length; indexArray++ ) {
		    		
			    	Imovel imovelHelper = imoveisAlteracao[indexArray];	
			    		
		    		contadorImoveisEmProcessoFaturamento++;
		    		
		    		prepararAlteracaoInscricaoEncerramentoFaturamento( fachada,  imovelHelper, usuario );
					
					imoveisAlteracao[indexArray] = imovelHelper;
					retorno = actionMapping.findForward("telaSucesso");		
					
				}	
			
			}// Caso não exista imovel em processo de faturamento, os imoveis serao atualizados na tabela de imovel 
			else if ( sessao.getAttribute("acaoInserir") != null && sessao.getAttribute("acaoInserir").equals("inserirImovel") ) {
				
				
				for (indexArray = 0; indexArray < imoveisAlteracao.length; indexArray++) {

					/**
					 * @author Arthur Carvalho
					 * @Analista Leonardo Vieira
					 * Alteração feita para suprir necessidade da compesa, onde qualquer alteracao de inscricao do imovel,
					 * somente sera efetivada no encerramento do faturamento.
					 * 
					 * @date 24/11/2010
					 */
					fachada.atualizarImovelInscricao(imoveisAlteracao[indexArray], usuario);
				}
			}
			
			
			//Se tiver sido aprensentada a tela confirmação e o usuario nao confirmou.
			 if(confirmado != null && confirmado.equalsIgnoreCase("nao")){
					//	Caso contrário (usuário não confirmou), não efetivar a alteração 
					// da inscrição na tabela IMOVEL.		
					flagNaoConfirmado = true;
					retorno = actionMapping.findForward("telaSucesso");			
			}
		
			sessao.removeAttribute("acao");
			sessao.removeAttribute("acaoInserir");
			
			String mensagemSucesso  = 
				imoveisAlteracao.length +" Inscrições de Imóvel alteradas com sucesso.";
			
			if(flagNaoConfirmado){
				mensagemSucesso  = "Atenção! Dados da inscrição do(s) imóvel(is) não alterados devido ao(s) imóvel(is) estar(em) em processo de faturamento.";
			}

			//Mensagem de retorno para o usuário
			montarPaginaSucesso(httpServletRequest,
					mensagemSucesso,
			        "Atualizar outra Inscrição de Imóvel",
			        "exibirAlterarImovelInscricaoAction.do");
        }

        //devolve o mapeamento de retorno
        return retorno;
    }

	/**
     * Verifica a situação em que se encontra a rota que pertence a quadra
     * passada como parâmetro - não faturada = false e faturada = true
     * 
     * @param quadra
     * @return um boleano
     */

    private boolean verificarSituacaoRota(Quadra quadra) {

        boolean retorno = true;
        Collection colecaoPesquisa = null;
        SistemaParametro sistemaParametro = null;
        
        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //Retorna o único objeto da tabela sistemaParametro
        sistemaParametro = fachada.pesquisarParametrosDoSistema();

        if (sistemaParametro == null) {
            retorno = false;
            throw new ActionServletException(
                    "atencao.pesquisa.sistemaparametro_inexistente");
        }
		FiltroFaturamentoAtividadeCronograma filtroFaturamentoAtividadeCronograma = new FiltroFaturamentoAtividadeCronograma();

		filtroFaturamentoAtividadeCronograma
		        .adicionarParametro(new ParametroSimples(
		                FiltroFaturamentoAtividadeCronograma.FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_FATURAMENTO_GRUPO_ID,
		                quadra.getRota().getFaturamentoGrupo().getId()));

		filtroFaturamentoAtividadeCronograma
		        .adicionarParametro(new ParametroSimples(
		                FiltroFaturamentoAtividadeCronograma.FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_ANO_MES_REFERENCIA,
		                new Integer(sistemaParametro.getAnoMesFaturamento())));

		//O valor do ID será fixo
		// =============================================
		filtroFaturamentoAtividadeCronograma
		        .adicionarParametro(new ParametroSimples(
		                FiltroFaturamentoAtividadeCronograma.FATURAMENTO_ATIVIDADE_ID,
		                FaturamentoAtividade.FATURAR_GRUPO));
		//=====================================================================

		colecaoPesquisa = fachada.pesquisar(
		        filtroFaturamentoAtividadeCronograma,
		        FaturamentoAtividadeCronograma.class.getName());

		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
		    /*retorno = false;
		    throw new ActionServletException(
		            "atencao.pesquisa.faturamento_atividade_cronograma_inexistente");*/
		} else {
		    FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = (FaturamentoAtividadeCronograma) Util
		            .retonarObjetoDeColecao(colecaoPesquisa);

		    //Local da verificação da situação da Rota (Faturada ou não
		    // Faturada)
		    if (faturamentoAtividadeCronograma.getDataRealizacao() == null) {
		        retorno = false;
		    }
		}

        return retorno;
    }

    /**
     * 
     * @param objetoPesquisa
     * @param objetoPai
     * @param tipoObjeto
     * @return
     * @throws RemoteException
     * @throws ErroRepositorioException
     */

    private boolean pesquisarObjeto(Object objetoPesquisa, Object objetoPai,
            int tipoObjeto) {

        boolean retorno = true;
        Collection colecaoPesquisa = null;
        SetorComercial setorComercial = null;
        
        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        switch (tipoObjeto) {
        //Setor Comercial
        case 1:

            Localidade localidade = (Localidade) objetoPai;
            setorComercial = (SetorComercial) objetoPesquisa;

            FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.ID_LOCALIDADE, localidade.getId()));

            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, String
                            .valueOf(setorComercial.getCodigo())));

            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.INDICADORUSO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
                    SetorComercial.class.getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                retorno = false;
            }

            break;
        //Quadra
        case 2:

            setorComercial = (SetorComercial) objetoPai;
            Quadra quadra = (Quadra) objetoPesquisa;

            FiltroQuadra filtroQuadra = new FiltroQuadra();

            filtroQuadra.adicionarParametro(new ParametroSimples(
                    FiltroQuadra.ID_SETORCOMERCIAL, String
                            .valueOf(setorComercial.getId())));

            filtroQuadra.adicionarParametro(new ParametroSimples(
                    FiltroQuadra.NUMERO_QUADRA, String.valueOf(quadra
                            .getNumeroQuadra())));

            filtroQuadra.adicionarParametro(new ParametroSimples(
                    FiltroQuadra.INDICADORUSO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class
                    .getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                retorno = false;
            }

            break;
        default:
            break;
        }

        return retorno;
    }

    /**
     * 
     * @param objetoPesquisa
     * @param objetoPai
     * @param tipoObjeto
     * @return
     * @throws RemoteException
     * @throws ErroRepositorioException
     */

    private Object pesquisarRetornarObjeto(Object objetoPesquisa,
            Object objetoPai, int tipoObjeto) {

        Object retorno = null;
        Collection colecaoPesquisa = null;
        SetorComercial setorComercial = null;
        Quadra quadra = null;
        Imovel imovel = null;
        
        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        switch (tipoObjeto) {
        //Setor Comercial
        case 1:

            Localidade localidade = (Localidade) objetoPai;
            setorComercial = (SetorComercial) objetoPesquisa;

            FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.ID_LOCALIDADE, localidade.getId()));

            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, String
                            .valueOf(setorComercial.getCodigo())));

            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.INDICADORUSO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
                    SetorComercial.class.getName());

            if ( !Util.isVazioOrNulo(colecaoPesquisa)) {
                retorno = Util.retonarObjetoDeColecao(colecaoPesquisa);
            }

            break;
        //Quadra
        case 2:

            setorComercial = (SetorComercial) objetoPai;
            quadra = (Quadra) objetoPesquisa;

            FiltroQuadra filtroQuadra = new FiltroQuadra();
            
            //Objetos que serão retornados pelo hibernate
            filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota.faturamentoGrupo");

            filtroQuadra.adicionarParametro(new ParametroSimples(
                    FiltroQuadra.ID_SETORCOMERCIAL, String
                            .valueOf(setorComercial.getId())));

            filtroQuadra.adicionarParametro(new ParametroSimples(
                    FiltroQuadra.NUMERO_QUADRA, String.valueOf(quadra
                            .getNumeroQuadra())));

            filtroQuadra.adicionarParametro(new ParametroSimples(
                    FiltroQuadra.INDICADORUSO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class
                    .getName());

            if ( !Util.isVazioOrNulo(colecaoPesquisa)) {
                retorno = Util.retonarObjetoDeColecao(colecaoPesquisa);
            }

            break;

        //Imovel
        case 3:

            quadra = (Quadra) objetoPai;
            imovel = (Imovel) objetoPesquisa;

            FiltroImovel filtroImovel = new FiltroImovel();

            filtroImovel.adicionarParametro(new ParametroSimples(
                    FiltroImovel.QUADRA_ID, quadra.getId()));

            filtroImovel.adicionarParametro(new ParametroSimples(
                    FiltroImovel.LOTE, new Short(imovel.getLote())));

            filtroImovel.adicionarParametro(new ParametroSimples(
                    FiltroImovel.SUBLOTE, new Short(imovel.getSubLote())));

            colecaoPesquisa = fachada.pesquisar(filtroImovel, Imovel.class
                    .getName());

            if ( !Util.isVazioOrNulo(colecaoPesquisa)) {
                retorno = Util.retonarObjetoDeColecao(colecaoPesquisa);
            }

            break;

        default:
            break;
        }

        return retorno;
    }

    /**
     * Compara os objetos para validar suas referencias
     * 
     * @param origem
     * @param destino
     * @param tipoObjeto
     * @return
     * @throws RemoteException
     * @throws ErroRepositorioException
     */

    private boolean compararObjetos(Object origem, Object destino,
            int tipoObjeto) {

        boolean retorno = true;
        //Collection colecaoImoveis;

        switch (tipoObjeto) {
        //Localidade
        case 1:
            Collection colecaoSetorOrigem = pesquisarDependentes(origem, 1);

            //Comparação de setores
            if (colecaoSetorOrigem != null && !colecaoSetorOrigem.isEmpty()) {
                Collection colecaoSetorDestino = pesquisarDependentes(destino,
                        1);

                if (colecaoSetorDestino != null
                        && !colecaoSetorDestino.isEmpty()) {

                    Iterator itSetorOrigem = (colecaoSetorOrigem).iterator();
                    Iterator itSetorDestino = null;
                    Iterator itQuadraOrigem = null;
                    Iterator itQuadraDestino = null;

                    SetorComercial setorComercialOrigem = null;
                    SetorComercial setorComercialDestino = null;
                    Quadra quadraOrigem = null;
                    Quadra quadraDestino = null;

                    while (itSetorOrigem.hasNext()) {
                        if (!retorno) {
                            // Verifica a existência de imóveis no setor
                            // comercial
                            if (existeImovel(setorComercialOrigem, 1)) {
                                break;
                            }
                        }

                        setorComercialOrigem = (SetorComercial) itSetorOrigem
                                .next();

                        //Colocar o índice para o primeiro registro
                        itSetorDestino = (colecaoSetorDestino).iterator();

                        //flag auxiliar para controlar a saída da rotina de
                        // repetição
                        boolean sairLoop = false;
                        while (itSetorDestino.hasNext() && sairLoop == false) {

                            setorComercialDestino = (SetorComercial) itSetorDestino
                                    .next();

                            if (setorComercialDestino.getCodigo() == setorComercialOrigem
                                    .getCodigo()) {

                                retorno = true;
                                sairLoop = true;

                                //Comparação de quadras
                                Collection colecaoQuadraOrigem = pesquisarDependentes(
                                        setorComercialOrigem, 2);

                                if (colecaoQuadraOrigem == null
                                        || colecaoQuadraOrigem.isEmpty()) {
                                    retorno = true;
                                    break;
                                }
								Collection colecaoQuadraDestino = pesquisarDependentes(
								        setorComercialDestino, 2);

								if (colecaoQuadraDestino != null
								        && !colecaoQuadraDestino.isEmpty()) {

								    itQuadraOrigem = (colecaoQuadraOrigem)
								            .iterator();

								    while (itQuadraOrigem.hasNext()) {
								        if (!retorno) {
								            // Verifica a existência de
								            // imóveis na quadra
								            if (existeImovel(quadraOrigem,
								                    2)) {
								                break;
								            }
								        }

								        quadraOrigem = (Quadra) itQuadraOrigem
								                .next();

								        //Colocar o índice para o primeiro
								        // registro
								        itQuadraDestino = (colecaoQuadraDestino)
								                .iterator();

								        while (itQuadraDestino.hasNext()) {

								            quadraDestino = (Quadra) itQuadraDestino
								                    .next();

								            if (quadraDestino
								                    .getNumeroQuadra() == quadraOrigem
								                    .getNumeroQuadra()) {
								                retorno = true;
								                break;
								            }
											retorno = false;
								        }

								    }
								} else {
								    retorno = false;
								}
                            } else {
                                retorno = false;
                            }
                        }
                    }
                } else {
                    retorno = false;
                }
            }

            break;
        //Setor Comercial
        case 2:
            Collection colecaoQuadraOrigem = pesquisarDependentes(origem, 2);

            //Comparação de quadras
            if (colecaoQuadraOrigem != null && !colecaoQuadraOrigem.isEmpty()) {
                Collection colecaoQuadraDestino = pesquisarDependentes(destino,
                        2);

                if (colecaoQuadraDestino != null
                        && !colecaoQuadraDestino.isEmpty()) {
                    Iterator itQuadraOrigem = (colecaoQuadraOrigem).iterator();
                    Iterator itQuadraDestino = null;

                    while (itQuadraOrigem.hasNext()) {
                        if (!retorno) {
                            break;
                        }
                        Quadra quadraOrigem = (Quadra) itQuadraOrigem.next();

                        //Colocar o índice para o primeiro registro
                        itQuadraDestino = (colecaoQuadraDestino).iterator();

                        while (itQuadraDestino.hasNext()) {
                            Quadra quadraDestino = (Quadra) itQuadraDestino
                                    .next();
                            if (quadraDestino.getNumeroQuadra() == quadraOrigem
                                    .getNumeroQuadra()) {
                                retorno = true;
                                break;
                            }
							retorno = false;
                        }
                    }
                } else {
                    retorno = false;
                }
            }

            break;
        default:
            break;
        }

        return retorno;
    }

    /**
     * Retorna os dependentes do objeto passado (Localidade ou Setor Comercial)
     * 
     * @param objetoPai
     * @param tipoObjeto
     * @return uma colecao com objetos dependentes do objeto passado
     * @throws RemoteException
     * @throws ErroRepositorioException
     */

    private Collection pesquisarDependentes(Object objetoPai, int tipoObjeto) {

        Collection colecaoPesquisa = null;
        
        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        switch (tipoObjeto) {
        //Localidade - retorna uma coleção de setor comercial
        case 1:
            Localidade localidade = (Localidade) objetoPai;

            //Indicador de uso considerado como ATIVO
            colecaoPesquisa = fachada.pesquisarSetorComercial(localidade
                    .getId().intValue());

            break;
        //Setor Comercial - retorna uma coleção de quadra
        case 2:
            SetorComercial setorComercial = (SetorComercial) objetoPai;

            //Indicador de uso considerado como ATIVO
            colecaoPesquisa = fachada.pesquisarQuadra(setorComercial.getId()
                    .intValue());

            break;
        default:
            break;
        }

        return colecaoPesquisa;
    }

    /**
     * Valida os valores digitados pelo usuário
     * 
     * @param campoDependencia
     * @param dependente
     * @param tipoObjeto
     * @return Object
     * @throws RemoteException
     * @throws ErroRepositorioException
     */

    private Object validarCampo(String campoDependencia, String dependente,
            int tipoObjeto) {

        Object objeto = null;
        Collection colecaoPesquisa;
        
        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        if (campoDependencia != null && !campoDependencia.equalsIgnoreCase("")) {

            if (dependente == null || tipoObjeto == 1) {
                //Localidade
                FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

                filtroLocalidade.adicionarParametro(new ParametroSimples(
                        FiltroLocalidade.ID, campoDependencia));

                filtroLocalidade.adicionarParametro(new ParametroSimples(
                        FiltroLocalidade.INDICADORUSO,
                        ConstantesSistema.INDICADOR_USO_ATIVO));

                colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
                        Localidade.class.getName());

                if ( !Util.isVazioOrNulo(colecaoPesquisa)) {
                    objeto = Util.retonarObjetoDeColecao(colecaoPesquisa);
                }
            } else if (dependente != null && !dependente.equalsIgnoreCase("")
                    && tipoObjeto > 1) {
                switch (tipoObjeto) {
                // Setor Comercial
                case 2:
                    FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

                    filtroSetorComercial
                            .adicionarParametro(new ParametroSimples(
                                    FiltroSetorComercial.ID_LOCALIDADE,
                                    campoDependencia));

                    filtroSetorComercial
                            .adicionarParametro(new ParametroSimples(
                                    FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
                                    new Integer(dependente)));

                    filtroSetorComercial
                            .adicionarParametro(new ParametroSimples(
                                    FiltroSetorComercial.INDICADORUSO,
                                    ConstantesSistema.INDICADOR_USO_ATIVO));

                    colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
                            SetorComercial.class.getName());

                    if ( !Util.isVazioOrNulo(colecaoPesquisa)) {
                        objeto = Util.retonarObjetoDeColecao(colecaoPesquisa);
                    }

                    break;
                // Quadra
                case 3:
                    FiltroQuadra filtroQuadra = new FiltroQuadra();
                    
                    //Objetos que serão retornados pelo hibernate
                    filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota.faturamentoGrupo");
                    
                    filtroQuadra.adicionarParametro(new ParametroSimples(
                            FiltroQuadra.ID_SETORCOMERCIAL, campoDependencia));

                    filtroQuadra.adicionarParametro(new ParametroSimples(
                            FiltroQuadra.NUMERO_QUADRA, new Integer(dependente)));

                    filtroQuadra.adicionarParametro(new ParametroSimples(
                            FiltroQuadra.INDICADORUSO,
                            ConstantesSistema.INDICADOR_USO_ATIVO));

                    colecaoPesquisa = fachada.pesquisar(filtroQuadra,
                            Quadra.class.getName());

                    if ( !Util.isVazioOrNulo(colecaoPesquisa)) {
                        objeto = Util.retonarObjetoDeColecao(colecaoPesquisa);
                    }

                    break;
                default:
                    break;
                }
            }
        }

        return objeto;
    }

    /**
     * Verifica a existência de imóveis no objeto passado como parâmetro Os
     * objetos passados podem ser do tipo SetorComercial = 1 e Quadra = 2
     * 
     * @param objetoCondicao
     * @param tipoObjeto
     * @return um boleano
     */
    private boolean existeImovel(Object objetoCondicao, int tipoObjeto) {

        boolean retorno = false;
        Collection colecaoPesquisa;
        
        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        if (tipoObjeto == 1) {
            SetorComercial setorComercial = (SetorComercial) objetoCondicao;

            colecaoPesquisa = fachada.pesquisarImovel(null, setorComercial
                    .getId(), null, null);

            if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
                retorno = true;
            }
        } else {
            Quadra quadra = (Quadra) objetoCondicao;

            colecaoPesquisa = fachada.pesquisarImovel(null, null, quadra
                    .getId(), null);

            if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
                retorno = true;
            }
        }

        return retorno;
    }

    
    /**
	 * [UC0074] Alterar Inscrição de Imóvel
	 * [FS0010] – Verificar Duplicidade de Inscrição
	 * @author Arthur Carvalho
	 * @date 17/09/2010
	 * @return
	 */
	private void verificarDuplicidadeInscricao( Imovel imovelHelper, Fachada fachada ) {
		
		ImovelInscricaoAlterada imovelInscricaoAlterada = new ImovelInscricaoAlterada();
		
		Integer idQuadraFace = null;
		if ( imovelHelper.getQuadraFace() != null && imovelHelper.getQuadraFace().getId() != null ) {
			idQuadraFace = imovelHelper.getQuadraFace().getId();
		}
		imovelInscricaoAlterada = fachada.verificarDuplicidadeImovelInscricaoAlterada(imovelHelper.getLocalidade().getId(), 
				imovelHelper.getSetorComercial().getId(), imovelHelper.getQuadra().getId(),
				idQuadraFace, new Integer(imovelHelper.getLote()), new Integer(imovelHelper.getSubLote())
				);
		

		if ( imovelInscricaoAlterada != null && imovelInscricaoAlterada.getId() != null ) {
			
			throw new ActionServletException("atencao.imovel_inscricao_alterada_em_duplicidade", null,
					imovelInscricaoAlterada.getId().toString());
		}
		
	}
	
	/**
	 *  [SB0001] – Verificar Alteração da Inscrição dos Imóveis
	 *  
	 * @author Arthur Carvalho
	 * @date 20/09/2010
	 * @param imoveisAlteracao
	 * @param idQuadraOrigem
	 * @param idQuadraDestino
	 * @param sistemaParametro
	 * @param fachada
	 * @param httpServletRequest
	 * @param actionMapping
	 * @param sessao
	 * @return
	 */
	private ActionForward verificaAlteracaoInscricaoImovel(Imovel imovel, Integer idQuadraOrigem, Integer idQuadraDestino,
			SistemaParametro sistemaParametro, Fachada fachada, HttpServletRequest httpServletRequest, ActionMapping actionMapping,
			HttpSession sessao)  {
		
		//localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		/*
		 * 1. Caso o indicador de alteração da inscrição esteja ativo (PARM_ICALTERACAOINSCRICAOIMOVEL=1 da tabela SISTEMA_PARAMETROS), 
		 * verificar para cada um dos imóveis da Inscrição Inicial a impossibilidade de efetuar a alteração das inscrições em tempo real:
		 */
		if (sistemaParametro.getIndicadorAlteracaoInscricaoImovel().equals(ConstantesSistema.SIM)){
			
			//if (fachada.verificarImovelEmProcessoDeFaturamento(imovel.getId())){
				
				httpServletRequest.setAttribute("nomeBotao1", "Sim");
				httpServletRequest.setAttribute("nomeBotao3", "Não");

				sessao.setAttribute("acao" , "inserirImovelInscricaoAlterada");
				
				return montarPaginaConfirmacao("atencao.imoveis_em_processo_de_faturamento",
				httpServletRequest, actionMapping);
			//}
		}
		/*
		 * 2. Caso o indicador de alteração da inscrição esteja inativo (PARM_ICALTERACAOINSCRICAOIMOVEL=2 da tabela SISTEMA_PARAMETROS), 
		 * para cada um dos imóveis do conjunto de imóveis da Inscrição Inicial, verificar a impossibilidade de efetuar a alteração das 
		 * inscrições em tempo real:
		 */
		else{
			
			FaturamentoGrupo[] faturamentoGrupos = new FaturamentoGrupo[2];
			FaturamentoGrupo faturamentoGrupoOrigem = new FaturamentoGrupo();
			FaturamentoGrupo faturamentoGrupoDestino = new FaturamentoGrupo();
			//boolean retorno = false;
			
			faturamentoGrupos = fachada.verificaInscricaoAlteradaAcarretaMudancaDoGrupoFaturamento( idQuadraOrigem , idQuadraDestino );
			faturamentoGrupoOrigem = faturamentoGrupos[0];
			faturamentoGrupoDestino = faturamentoGrupos[1];
			
			//verifica se o imovel foi mudado de grupo ao ser alterado a quadra
			if ( !faturamentoGrupoOrigem.getId().toString().equals(faturamentoGrupoDestino.getId().toString()) ) {

				//1.3.1
				if ( faturamentoGrupoOrigem.getAnoMesReferencia() > sistemaParametro.getAnoMesFaturamento() ) {
				
					//1.3.1.1
					if ( fachada.verificaGeracaoDadosLeituraGrupoFaturamento(faturamentoGrupoOrigem) ) {
					
						//1.3.1.1.1 
						//1.3.1.1.2
						httpServletRequest.setAttribute("nomeBotao1", "Sim");
						httpServletRequest.setAttribute("nomeBotao3", "Não");
						
						sessao.setAttribute("acao" , "inserirImovelInscricaoAlterada");
						
						return montarPaginaConfirmacao(
								"atencao.imovel_inscricao_alterada_grupo_origem_gerou_dados_leituras",
								httpServletRequest, actionMapping, imovel.getId().toString(), faturamentoGrupoDestino.getId().toString() ,
								faturamentoGrupoOrigem.getId().toString()   );
						
					}//1.3.1.2 
					else {
						
						//1.3.1.2.1
						if ( faturamentoGrupoDestino.getAnoMesReferencia() > sistemaParametro.getAnoMesFaturamento() ) {
							
							//1.3.1.2.1.1.1
							if ( fachada.verificaGeracaoDadosLeituraGrupoFaturamento(faturamentoGrupoDestino)) {
								//1.3.1.2.1.1.1 
								//1.3.1.2.1.1.2
								httpServletRequest.setAttribute("nomeBotao1", "Sim");
								httpServletRequest.setAttribute("nomeBotao3", "Não");

								sessao.setAttribute("acao" , "inserirImovelInscricaoAlterada");
								
								return 	montarPaginaConfirmacao(
										"atencao.imovel_inscricao_alterada_grupo_gerou_dados_leituras_proximo_faturamento",
										httpServletRequest, actionMapping, imovel.getId().toString(), faturamentoGrupoDestino.getId().toString() );
						
							}//1.3.1.2.1.2
							else {
								//efetivar a alteração da inscrição na tabela IMOVEL
								sessao.setAttribute("acaoInserir" , "inserirImovel");
							}
							
						}//1.3.1.2.2
						else {
							
							//1.3.1.2.2.1
							if ( fachada.verificaGeracaoDadosLeituraGrupoFaturamento(faturamentoGrupoDestino)) {
								
								//1.3.1.2.2.1.1 
								//1.3.1.2.2.1.2
								httpServletRequest.setAttribute("nomeBotao1", "Sim");
								httpServletRequest.setAttribute("nomeBotao3", "Não");

								sessao.setAttribute("acao" , "inserirImovelInscricaoAlterada");
								
								return montarPaginaConfirmacao(
										"atencao.dados_leituras_gerados_faturamento_grupo_destino_imovel_inscricao_alterada",
										httpServletRequest, actionMapping, imovel.getId().toString(), faturamentoGrupoDestino.getId().toString() );

								//[SB0005 – Preparar Alteração Inscrição no Encerramento Faturamento]
							} else {
								//1.3.1.2.2.2
								//efetivar a alteração da inscrição na tabela IMOVEL
								sessao.setAttribute("acaoInserir" , "inserirImovel");
							}
						}
					}
				} //1.3.2
				else {
					//1.3.2.1
					if ( fachada.verificaGeracaoDadosLeituraGrupoFaturamento(faturamentoGrupoOrigem) ) {
						//1.3.2.1.1
						//1.3.2.1.2
						httpServletRequest.setAttribute("nomeBotao1", "Sim");
						httpServletRequest.setAttribute("nomeBotao3", "Não");

						sessao.setAttribute("acao" , "inserirImovelInscricaoAlterada");
						
						return montarPaginaConfirmacao(
								"atencao.dados_leituras_gerados_faturamento_grupo_origem_imovel_inscricao_alterada",
									httpServletRequest, actionMapping, imovel.getId().toString(), 
										faturamentoGrupoDestino.getId().toString(), faturamentoGrupoOrigem.getId().toString() );
					}//1.3.2.2
					else {
						//1.3.2.2.1
						if ( faturamentoGrupoDestino.getAnoMesReferencia() > sistemaParametro.getAnoMesFaturamento() ) {
							//1.3.2.2.1.1
							//1.3.2.2.1.2
							httpServletRequest.setAttribute("nomeBotao1", "Sim");
							httpServletRequest.setAttribute("nomeBotao3", "Não");

							sessao.setAttribute("acao" , "inserirImovelInscricaoAlterada");
							
							return montarPaginaConfirmacao(
									"atencao.faturamento_grupo_destino_ja_faturado_imovel_inscricao_alterada",
										httpServletRequest, actionMapping, imovel.getId().toString(), 
											faturamentoGrupoDestino.getId().toString() );
						}//1.3.2.2.2
						else {
							//1.3.2.2.2.1
							if ( fachada.verificaGeracaoDadosLeituraGrupoFaturamento(faturamentoGrupoDestino) ) {
								//1.3.2.2.2.1.1
								//1.3.2.2.2.1.2
								httpServletRequest.setAttribute("nomeBotao1", "Sim");
								httpServletRequest.setAttribute("nomeBotao3", "Não");

								sessao.setAttribute("acao" , "inserirImovelInscricaoAlterada");
								
								return montarPaginaConfirmacao(
										"atencao.faturamento_grupo_destino_nao_faturado_imovel_inscricao_alterada",
											httpServletRequest, actionMapping, imovel.getId().toString(), 
												faturamentoGrupoDestino.getId().toString() );
							}//1.3.2.2.2.2 
							else {
								//efetivar a alteração da inscrição na tabela IMOVEL
								sessao.setAttribute("acaoInserir" , "inserirImovel");
							}
						}
					}
				}
			}
		}
		
		sessao.setAttribute("acaoInserir" , "inserirImovel");
    		
    	return retorno;
	}



	
	/**
	 * [SB0002] – Preparar Alteração Inscrição no Encerramento Faturamento
	 * @author Arthur Carvalho
	 * @date 20/09/2010
	 * @param fachada
	 * @param imovelHelper
	 */
	private void prepararAlteracaoInscricaoEncerramentoFaturamento(Fachada fachada, Imovel imovelHelper, Usuario usuario ){
		
		//[FS0009 – Verificar Existência de Alteração de Inscrição Pendente para o Imóvel] 
		verificarExistenciaAlteracaoInscricaoPendenteImovel(imovelHelper, fachada);
		
		//[FS0010 – Verificar Duplicidade de Inscrição]
		verificarDuplicidadeInscricao(imovelHelper, fachada );
		
		
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, imovelHelper.getId()));
		Collection colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
		
		Imovel imovelSemAtualizacao = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
		
		ImovelInscricaoAlterada imovelInscricaoAlterada = new ImovelInscricaoAlterada();
		
		
		imovelInscricaoAlterada.setImovel(imovelHelper);
		imovelInscricaoAlterada.setFaturamentoGrupo(null);
		
		imovelInscricaoAlterada.setLocalidadeAnterior(imovelSemAtualizacao.getLocalidade());
		imovelInscricaoAlterada.setSetorComercialAnterior(imovelSemAtualizacao.getSetorComercial());
		imovelInscricaoAlterada.setQuadraAnterior(imovelSemAtualizacao.getQuadra());
		imovelInscricaoAlterada.setQuadraFaceAnterior(imovelSemAtualizacao.getQuadraFace()!=null?imovelSemAtualizacao.getQuadraFace():null);
		imovelInscricaoAlterada.setLoteAnterior(imovelSemAtualizacao.getLote());
		imovelInscricaoAlterada.setSubLoteAnterior(imovelSemAtualizacao.getSubLote());
		
		imovelInscricaoAlterada.setLocalidadeAtual(imovelHelper.getLocalidade());
		imovelInscricaoAlterada.setSetorComercialAtual(imovelHelper.getSetorComercial());
		imovelInscricaoAlterada.setQuadraAtual(imovelHelper.getQuadra());
		imovelInscricaoAlterada.setQuadraFaceAtual(imovelHelper.getQuadraFace()!=null?imovelHelper.getQuadraFace():null);
		imovelInscricaoAlterada.setLoteAtual(imovelHelper.getLote());
		imovelInscricaoAlterada.setSubLoteAtual(imovelHelper.getSubLote());
		
		imovelInscricaoAlterada.setIndicadorAtualizado(ConstantesSistema.NAO);
		imovelInscricaoAlterada.setIndicadorAtualizacaoExcluida(ConstantesSistema.NAO);
		imovelInscricaoAlterada.setIndicadorImovelExcluido(ConstantesSistema.NAO);
		imovelInscricaoAlterada.setIndicadorErroAlteracao(null);
		
		if (this.getSistemaParametro().getIndicadorAlteracaoInscricaoImovel().toString().equals(""+ConstantesSistema.SIM)){
			
			imovelInscricaoAlterada.setIndicadorAutorizado(ConstantesSistema.NAO);
			} else {
				
				imovelInscricaoAlterada.setIndicadorAutorizado(ConstantesSistema.SIM);
			}
		
		imovelInscricaoAlterada.setUsuarioAlteracao(usuario);
		imovelInscricaoAlterada.setDataAlteracaoOnline(new Date());
		imovelInscricaoAlterada.setDataAlteracaoBatch(null);
		imovelInscricaoAlterada.setUltimaAlteracao(new Date());
		
		fachada.inserir(imovelInscricaoAlterada);
		
	}

	/**
	 * [FS0009 – Verificar Existência de Alteração de Inscrição Pendente para o Imóvel] 
	 * @author Arthur Carvalho
	 * @date 17/09/2010
	 * @param imovelSemAtualizacao
	 * @param fachada
	 */
	private void verificarExistenciaAlteracaoInscricaoPendenteImovel(Imovel imovelSemAtualizacao, Fachada fachada) {
	
		FiltroImovelInscricaoAlterada filtroImovelInscricaoAlterada = new FiltroImovelInscricaoAlterada();
		
		filtroImovelInscricaoAlterada.adicionarParametro(new ParametroSimples(
				FiltroImovelInscricaoAlterada.IMOVEL_ID, imovelSemAtualizacao.getId()));
		
		filtroImovelInscricaoAlterada.adicionarParametro(new ParametroSimples(
				FiltroImovelInscricaoAlterada.INDICADOR_ATUALIZADO, ConstantesSistema.NAO));
		
		filtroImovelInscricaoAlterada.adicionarParametro(new ParametroSimples(
				FiltroImovelInscricaoAlterada.INDICADOR_ALTERACAO_EXCLUIDA,ConstantesSistema.NAO));
		
		filtroImovelInscricaoAlterada.adicionarParametro(new ParametroNulo(
				FiltroImovelInscricaoAlterada.INDICADOR_ERRO_ALTERACAO));
		
		Collection<ImovelInscricaoAlterada> colecaoImovelInscricaoAlterada 
			= fachada.pesquisar(filtroImovelInscricaoAlterada,
								ImovelInscricaoAlterada.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoImovelInscricaoAlterada)){		
			for (ImovelInscricaoAlterada imovelInscricaoAlterada : colecaoImovelInscricaoAlterada) {
				
				imovelInscricaoAlterada.setIndicadorAtualizacaoExcluida(ConstantesSistema.SIM);
				imovelInscricaoAlterada.setUltimaAlteracao(new Date());
				
				fachada.atualizar(imovelInscricaoAlterada);
			}
		}
		
	}



}
