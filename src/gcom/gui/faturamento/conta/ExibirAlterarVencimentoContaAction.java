package gcom.gui.faturamento.conta;

import gcom.atendimentopublico.registroatendimento.EspecificacaoTipoValidacao;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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


public class ExibirAlterarVencimentoContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirAlterarVencimentoConta");
        
        //Instância do formulário que está sendo utilizado
        AlterarVencimentoContaActionForm alterarVencimentoContaActionForm = (AlterarVencimentoContaActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        //Carregando o identificador das contas selecionadas
        String contaSelected = httpServletRequest.getParameter("conta");
        
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		

    	//FiltroConta filtroConta = new FiltroConta();
    	//filtroConta.adicionarCaminhoParaCarregamentoEntidade("imovel.id");
    	//String idConta  = contaSelected.split("-")[0];
		// filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.IMOVEL_ID);
		// filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.ID, idConta));
    
		// Collection colecaoConta = fachada.pesquisar(filtroConta, Conta.class.getName());
    	
    	//Conta contaSelecao = fachada.pesquisarContaRetificacao(new Integer(idConta));
    	
    	String idImovel = httpServletRequest.getParameter("idImovel");
    	
		/*
		 * Colocado por Ana Maria em 22/04/2009				
		 */
		if (!fachada.verificarPermissaoRetificarContaImovelPefilBloqueado((Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO))){
			Boolean imovelBloqueado = fachada.verificarImovelPerfilBloqueado(new Integer(httpServletRequest.getParameter("idImovel")));
			if(imovelBloqueado){						
                throw new ActionServletException(
                        "atencao.perfil_imovel_nao_permite_operacao");
			}
		}
    	
    	//Conta contaSelecao = (Conta) colecaoConta.iterator().next();
    	
    	Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
    	
    	//Contas selecionadas pelo usuário
        String[] arrayIdentificadores = contaSelected.split(",");
        Collection idsConta = new ArrayList();
        
    	for (int i = 0; i < arrayIdentificadores.length; i++) {
    		String dadosConta = arrayIdentificadores[i];
			String[] idContaArray = dadosConta.split("-");
			Integer idConta = new Integer (idContaArray[0]);
			idsConta.add(idConta);
    	}	
        
    	// Alterado por: Hugo Leonardo 
    	// Data: 10/08/2010
    	// Analista: Aryed Lins
    	
    	//E o usuário não tenha permissão especial.	
		boolean temPermissaoParaAlterarVencimentoJaAlterado = 
			fachada.verificarPermissaoEspecial(PermissaoEspecial.ALTERAR_VENCIMENTO_JA_ALTERADO,
				usuario);	
		
		if(!temPermissaoParaAlterarVencimentoJaAlterado 
				&& sistemaParametro.getIndicadorLimiteAlteracaoVencimento().equals(ConstantesSistema.SIM)){
			
			fachada.verificarQuantidadeAlteracoesVencimentoConta(idsConta);
		}
    	
    	//[FS0018] Verificar ocorrência de conta(s) sem revisão ou em revisão por ação do usuário
		//Vivianne Sousa 11/05/2007
        Collection contasRevisaoAcaoUsuario = fachada.obterContasNaoEmRevisaoOuEmRevisaoPorAcaoUsuario(idsConta);
        
        if (contasRevisaoAcaoUsuario != null && !contasRevisaoAcaoUsuario.isEmpty()){
        	
    		// -----------------------------------------------------------
    		// Verificar permissão especial
    		boolean temPermissaoAlterarVencimentoSemRa = fachada.verificarPermissaoAlterarVencimentoSemRa(usuario);
    		// -----------------------------------------------------------
    		if(!temPermissaoAlterarVencimentoSemRa){
              //[FS0001] - Verificar Existência de RA
              fachada.verificarExistenciaRegistroAtendimento(
            		  new Integer(idImovel), "atencao.conta_existencia_registro_atendimento",
            		  EspecificacaoTipoValidacao.ALTERACAO_CONTA); 
    		}
        }
        
        //Carregar a data corrente do sistema
        //====================================
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dataCorrente = new GregorianCalendar();
        
        //Ultimo dia do mês corrente.        
        Date ultimaDataMes = Util.obterUltimaDataMes(Util.getMes(dataCorrente.getTime()), Util.getAno(dataCorrente.getTime()));        
        httpServletRequest.setAttribute("ultimaDataMes", formatoData.format(ultimaDataMes));
       
    	httpServletRequest.setAttribute("indicadorCalculaVencimento", sistemaParametro.getIndicadorCalculaVencimento().toString());
        
        //Data Corrente
        httpServletRequest.setAttribute("dataAtual", formatoData
        .format(dataCorrente.getTime()));
        
        //Data Corrente + 60 dias
        dataCorrente.add(Calendar.DATE, 60);
        httpServletRequest.setAttribute("dataAtual60", formatoData
        .format(dataCorrente.getTime()));
        
        
    	//-------------------------------------------------------------------------------------------
		// Alterado por :  Yara Taciane  - data : 17/07/2008 
		// Analista :  Denys Guimarães
		//-------------------------------------------------------------------------------------------	
        if(sistemaParametro.getIndicadorCalculaVencimento() == 1){
        	Date dtCorrente = new Date();
        	
        	Integer diasAdicionais = 0;
        	
        	if(sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior() != null){
        		diasAdicionais = sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior().intValue();
        	}
        	
			Date dataCorrenteComDias = Util.adicionarNumeroDiasDeUmaData(dtCorrente, diasAdicionais.intValue());
        	alterarVencimentoContaActionForm.setDataVencimento(Util.formatarData(dataCorrenteComDias));
        }	
        //--------------------------------------------------------------------------------------------
        
        
        alterarVencimentoContaActionForm.setContaSelected(contaSelected);
        
        return retorno;
    }

}

