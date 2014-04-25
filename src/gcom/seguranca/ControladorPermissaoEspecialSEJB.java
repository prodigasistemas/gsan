package gcom.seguranca;

import gcom.cadastro.imovel.Imovel;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroPemissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroUsuarioPemissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecial;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * Definição da lógica de negócio do Session Bean de
 * ControladorPermissaoEspecial
 * 
 * @author Rodrigo Silveira
 * @created 07/11/2006
 */

public class ControladorPermissaoEspecialSEJB implements SessionBean {
	private static final long serialVersionUID = 1L;
	SessionContext sessionContext;

	/**
	 * < <Descrição do método>>
	 * 
	 * @exception CreateException
	 *                Descrição da exceção
	 */
	public void ejbCreate() throws CreateException {

	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbRemove() {
	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbActivate() {
	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbPassivate() {
	}

	/**
	 * Seta o valor de sessionContext
	 * 
	 * @param sessionContext
	 *            O novo valor de sessionContext
	 */
	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}

	/**
	 * Verifica as permissões especiais do usuário por funcionalidade informada
	 * no sistema
	 * 
	 * @author Rodrigo Silveira
	 * @date 07/11/2006
	 * 
	 * @param permissaoEspecial
	 * @param usuario
	 * @return
	 */
	public boolean verificarPermissaoEspecial(int permissaoEspecial,
			Usuario usuario) throws ControladorException {

		boolean retorno = false;

		FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
		filtroUsuarioPemissaoEspecial
				.adicionarParametro(new ParametroSimples(
						FiltroUsuarioPemissaoEspecial.USUARIO_COMP_ID, usuario
								.getId()));
		filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(
				FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_COMP_ID,
				permissaoEspecial));

		if (getControladorUtil().pesquisar(filtroUsuarioPemissaoEspecial,
				UsuarioPermissaoEspecial.class.getName()).size() > 0) {
			retorno = true;

		}

		return retorno;

	}

	private ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}

	/**
	 * Verifica as permissões especiais do usuário por funcionalidade informada
	 * no sistema
	 * 
	 * @author Vivianne Sousa
	 * @date 09/11/2006
	 * 
	 * @param permissaoEspecial
	 * @param usuario
	 * @param objeto
	 * 
	 */
	public void verificarPermissaoEspecial(int permissaoEspecial,
			Usuario usuario, Object objeto) throws ControladorException {

		switch (permissaoEspecial) {

		case PermissaoEspecial.IMOVEL_EM_SITUACAO_COBRANCA:

			if (objeto instanceof Imovel) {
				Imovel imovel = (Imovel) objeto;
				verificarPermissaoEspecialImovelSituacaoCobranca(
						permissaoEspecial, usuario, imovel);
			}

			break;

		}

	}

	/**
	 * Verifica as permissões especiais do usuário por funcionalidade informada
	 * no sistema
	 * 
	 * @author Vivianne Sousa
	 * @date 09/11/2006
	 * 
	 * @param permissaoEspecial
	 * @param usuario
	 * @param imovel
	 */
	public void verificarPermissaoEspecialImovelSituacaoCobranca(
			int permissaoEspecial, Usuario usuario, Imovel imovel)
			throws ControladorException {

		if (imovel.getCobrancaSituacao() != null
				&& imovel.getCobrancaSituacao().getId() != null
				&& !imovel.getCobrancaSituacao().getId().equals("")
				&& !imovel.getCobrancaSituacao().getId().equals("0")
                && imovel.getCobrancaSituacao().getIndicadorBloqueioParcelamento().equals(ConstantesSistema.SIM)) {

			if (!verificarPermissaoEspecial(permissaoEspecial, usuario)) {
				throw new ControladorException(
						"atencao.imovel.em.situacao.cobranca", null, imovel
								.getCobrancaSituacao().getDescricao());
			}

		}

	}

	/**
	 * Verifica permissão especial para aceitar um valor de entrada menor q o
	 * valor mínimo de entrada na terceira página de Efetuar Parcelamento
	 * Débitos
	 * 
	 * @author Vivianne Sousa
	 * @date 28/11/2006
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoValMinimoEntrada(Usuario usuario)
			throws ControladorException {

		boolean temPermissaoValMinimoEntrada = this.verificarPermissaoEspecial(
				PermissaoEspecial.TESTAR_VAL_MINIMO_ENTRADA, usuario);

		return temPermissaoValMinimoEntrada;
	}
	
	/**
	 * Verifica permissão especial para habilitar ou não o formulário
	 * 
	 * @author Arthur Carvalho
	 * @date 13/10/2008
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoValidarAcrescimosImpontualidade(Usuario usuario)
			throws ControladorException {

		boolean validarAcrescimoImpontualidade = this.verificarPermissaoEspecial(
				PermissaoEspecial.VALIDAR_ACRESCIMOS_IMPONTUALIDADE, usuario);

		return validarAcrescimoImpontualidade;
	}

	/**
	 * Verifica permissão especial para atualizar um cliente que seja usuário da
	 * tarifa social
	 * 
	 * @author Rafael Corrêa
	 * @date 16/02/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoAtualizarUsuarioTarifaSocial(
			Usuario usuario) throws ControladorException {

		boolean temPermissaoAtualizarUsuarioTarifaSocial = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.CLIENTE_USUARIO_TARIFA_SOCIAL,
						usuario);

		return temPermissaoAtualizarUsuarioTarifaSocial;
	}

	
	
	/**
	 * Verifica permissão especial para atualizar um LOGRADOURO_BAIRRO
	 * 
	 * @author Raphael Rossiter, Romulo Aurelio
	 * @date 24/02/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	 public boolean verificarPermissaoAtualizarLogradouroBairro(
			   Usuario usuario) throws ControladorException {

		 boolean retorno = this.verificarPermissaoEspecial(
				 PermissaoEspecial.ATUALIZAR_LOGRADOURO_BAIRRO,
			     usuario);

		return retorno;
	}
	 
	
	 /**
	  * Verifica permissão especial para NÃO gerar débito no informar retorno OS fiscalização
	  * 
	  * @author Raphael Rossiter
	  * @date 03/03/2007
	  * 
	  * @param usuario
	  */
	 public boolean verificarPermissaoGeracaoDebitoOSFiscalizacao(
				   Usuario usuario) throws ControladorException {

		 boolean retorno = this.verificarPermissaoEspecial(
					 PermissaoEspecial.GERACAO_DEBITO_OS_FISCALIZACAO,
				     usuario);

			return retorno;
	 }
	 
	 
	


	/**
	 * Verifica permissão especial para inserir Imovel com logradouro.municipio
	 * diferente de setorComercial.municipio
	 * 
	 * @author Rômulo Aurélio
	 * @date 23/02/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoInserirImovelMunicipioLogradouroDiferenteSetor(
			Usuario usuario) throws ControladorException {

		boolean temPermissaoInserirImovelMunicipioLogradouroDiferenteSetor = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.INSERIR_IMOVEL_MUNICIPIO_LOGRADOURO_DIFERENTE_SETOR,
						usuario);

		return temPermissaoInserirImovelMunicipioLogradouroDiferenteSetor;
	}

	/**
	 * Verifica permissão especial para atualizar Imovel com
	 * logradouro.municipio diferente de setorComercial.municipio
	 * 
	 * @author Rômulo Aurélio
	 * @date 23/02/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoAtualizarImovelMunicipioLogradouroDiferenteSetor(
			Usuario usuario) throws ControladorException {

		boolean temPermissaoAtualizarImovelMunicipioLogradouroDiferenteSetor = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.ATUALIZAR_IMOVEL_MUNICIPIO_LOGRADOURO_DIFERENTE_SETOR,
						usuario);

		return temPermissaoAtualizarImovelMunicipioLogradouroDiferenteSetor;
	}

	/**
	 * Verifica permissão especial para inserir débito a cobrar
	 * sem valor da entrada e a taxa de juros
	 * 
	 * @author Ana Maria
	 * @date 27/02/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoInserirDebitoACobrarSemEntradaSemJuros(
			Usuario usuario) throws ControladorException {

		boolean temPermissaoInserirDebitoACobrarSemEntradaSemJuros = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.INSERIR_DEBITO_A_COBRAR_SEM_ENTRADA_SEM_JUROS,
						usuario);

		return temPermissaoInserirDebitoACobrarSemEntradaSemJuros;
	}	
	
	/**
	 * Verifica permissão especial para inserir motivo
	 * da não cobrança
	 * 
	 * @author Ana Maria
	 * @date 03/03/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoInformarMotivoNaoCobranca(
			Usuario usuario) throws ControladorException {

		boolean temPermissaoInformarMotivoNaoCobranca = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.INFORMAR_MOTIVO_NAO_COBRANCA,
						usuario);

		return temPermissaoInformarMotivoNaoCobranca;
	}	
	
	
	/**
	 * Verifica permissão especial para informar nova data para vencimento alternativo 
	 * 
	 * @author Vivianne Sousa
	 * @date 06/03/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoInformarVencimentoAlternativoNovaData(
			Usuario usuario) throws ControladorException {

		boolean temPermissaoInformarMotivoNaoCobranca = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.INFORMAR_VENCIMENTO_ALTERNATIVO_NOVA_DATA,
						usuario);

		return temPermissaoInformarMotivoNaoCobranca;
	}	
	
	/**
	 * Verifica permissão especial para não testar quantidade de parcelas no Efetuar Parcelamento
	 * 
	 * @author Vivianne Sousa
	 * @date 16/03/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoNaoTestarQtdePrestacaoParcelamento(
			Usuario usuario) throws ControladorException {

		boolean temPermissaoInformarMotivoNaoCobranca = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.PARCELAR_NAO_TESTAR_QTDE_DE_PRESTACAO,
						usuario);

		return temPermissaoInformarMotivoNaoCobranca;
	}
	

	/**
	 * Verifica permissão especial para informar 
	 * nova data para vencimento alternativo antes do periodo válido
	 * 
	 * @author Vivianne Sousa
	 * @date 19/03/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoInformarVencimentoAlternativoAntesDoPeriodoValido(
			Usuario usuario) throws ControladorException {

		boolean temPermissaoInformarMotivoNaoCobranca = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.INFORMAR_VENCIMENTO_ALTERNATIVO_ANTES_DO_PERIODO_VALIDO,
						usuario);

		return temPermissaoInformarMotivoNaoCobranca;
	}
	
	/**
	 * Manter conta - Alterar vencimento sem ra
	 * 
	 * @author Ana Maria
	 * @date 26/03/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoAlterarVencimentoSemRa(Usuario usuario) throws ControladorException {

		boolean temPermissaoInformarMotivoNaoCobranca = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.ALTERAR_VENCIMENTO_CONTA_SEM_RA, usuario);

		return temPermissaoInformarMotivoNaoCobranca;
	}
	
	
	/**
	 * Inserir conta - inserir conta sem cronograma de faturamento e sem atividade efetuar leitura
	 * 
	 * @author Raphael Rossiter
	 * @date 08/05/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoInserirContaFaturamentoAntecipado(Usuario usuario) throws ControladorException {

		boolean temPermissaoInformarMotivoNaoCobranca = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.INSERIR_CONTA_FATURAMENTO_ANTECIPADO, usuario);

		return temPermissaoInformarMotivoNaoCobranca;
	}
	
	/**
	 * Inseir Débito a cobrar
	 * 
	 * @author Ana Maria
	 * @date 23/05/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoInserirDebitoACobrarSemRa(Usuario usuario) throws ControladorException {

		boolean temPermissaoInserirDebitoACobrarSemRa = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.INSERIR_DEBITO_A_COBRAR_SEM_RA, usuario);

		return temPermissaoInserirDebitoACobrarSemRa;
	}
	
	/**
	 * Atualizar Cliente
	 * 
	 * @author Vivianne Sousa
	 * @date 30/07/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoAlterarNomeCliente(Usuario usuario) throws ControladorException {

		boolean temPermissaoAlterarNomeCliente = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.ALTERAR_NOME_CLIENTE, usuario);

		return temPermissaoAlterarNomeCliente;
	}
	
	/**
	 * Incluir Devolução
	 * 
	 * @author Vivianne Sousa
	 * @date 30/07/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoIcluirDevolucaoMaiorValorMaximo(Usuario usuario) throws ControladorException {

		boolean temPermissaoIcluirDevolucaoMaiorValorMaximo = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.INCLUIR_DEVOLUCAO_MAIOR_VALOR_MAXIMO, usuario);

		return temPermissaoIcluirDevolucaoMaiorValorMaximo;
	}
	
	/**
	 * [UC0194] Crédito a Realizar Permite inserir um crédito a realizar
	 * 
	 * @author Sávio Luiz
	 * @since 21/08/2006
	 */
	public boolean verificarPermissaoIcluirCreditoARealizarValorMaximo(Usuario usuario) throws ControladorException {

		boolean temPermissaoIcluirDevolucaoMaiorValorMaximo = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.INCLUIR_CREDITO_A_REALIZAR_VALOR_MAXIMO, usuario);

		return temPermissaoIcluirDevolucaoMaiorValorMaximo;
	}
	
	/**
	 * [UC0194] Crédito a Realizar Permite inserir um crédito a realizar
	 * 
	 * @author Sávio Luiz
	 * @since 21/08/2006
	 */
	public boolean verificarPermissaoIcluirCreditoARealizarQuantidadeParcelasMaximo(Usuario usuario) throws ControladorException {

		boolean temPermissaoIcluirDevolucaoMaiorValorMaximo = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.INCLUIR_CREDITO_A_REALIZAR_QUANTIDADE_PARCELAS_MAXIMO, usuario);

		return temPermissaoIcluirDevolucaoMaiorValorMaximo;
	}
	
	/**
	 * [UC0630] Solicitar Emissão do Extrato de Debitos
	 * 
	 * @author Vivianne Sousa
	 * @since 30/08/2007
	 */
	public boolean verificarPermissaoIncluirAcrescimoImpontualidadeNoExtratoDeDebitosComDesconto(Usuario usuario) throws ControladorException {

		boolean temPermissaoIncluirAcrescimoImpontualidadeNoExtratoDeDebitosComDesconto = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.INCLUIR_ACRESCIMO_IMPONTUALIDADE_NO_EXTRATO_DE_DEBITOS_COM_DESCONTO, usuario);

		return temPermissaoIncluirAcrescimoImpontualidadeNoExtratoDeDebitosComDesconto;
	}
	
	/**
	 * [UC0630] Solicitar Emissão do Extrato de Debitos
	 * 
	 * @author Vivianne Sousa
	 * @since 30/08/2007
	 */
	public boolean verificarPermissaoRetirarTaxaCobrancaDoExtratoDeDebitos(Usuario usuario) throws ControladorException {

		boolean temPermissaoRetirarTaxaCobrancaDoExtratoDeDebitos = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.RETIRAR_TAXA_COBRANCA_DO_EXTRATO_DE_DEBITOS, usuario);

		return temPermissaoRetirarTaxaCobrancaDoExtratoDeDebitos;
	}
	
	/**
	 * [UC0XXX] Consultar Débitos
	 * 
	 * @author Rafael Corrêa
	 * @since 13/09/2007
	 */
	public boolean verificarPermissaoConsultarDebitosIndicadoNaContaOuTodos(Usuario usuario) throws ControladorException {

		boolean verificarPermissaoConsultarDebitosIndicadoNaContaOuTodos = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.CONSULTAR_DEBITOS_INDICADO_NA_CONTA_OU_TODOS, usuario);

		return verificarPermissaoConsultarDebitosIndicadoNaContaOuTodos;
	}
	
	
	/**
	 * Inserir débito a cobrar - inserir debito a cobrar independente da situacao da ligacao de agua e esgoto do
	 * imovel 
	 * 
	 * @author Raphael Rossiter
	 * @date 03/10/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoInserirDebitoACobrarImovelSituacao(Usuario usuario) throws ControladorException {

		boolean temPermissaoInserirDebitoACobrarImovelSituacao = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.INSERIR_DEBITO_A_COBRAR_IMOVEL_SITUACAO, usuario);

		return temPermissaoInserirDebitoACobrarImovelSituacao;
	}
	
	/**
	 * Reiniciar um batch
	 * 
	 * @author Rafael Corrêa
	 * @date 06/11/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoReiniciarBatch(Usuario usuario) throws ControladorException {

		boolean temPermissaoReiniciarBatch = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.REINICIAR_BATCH, usuario);

		return temPermissaoReiniciarBatch;
	}
	
	/**
	 * Permite retificar uma conta sem RA 
	 * 
	 * @author Raphael Rossiter
	 * @date 09/11/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoRetificarContaSemRA(Usuario usuario) throws ControladorException {

		boolean temPermissaoRetificarContaSemRA = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.RETIFICAR_CONTA_SEM_RA, usuario);

		return temPermissaoRetificarContaSemRA;
	}
	
	/**
	 * Permite excluir debito a cobrar 
	 * 
	 * @author Marcio Roberto
	 * @date 26/11/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoExcluirDebitoACobrar(Usuario usuario) throws ControladorException {

		boolean temPermissaoExcluirDebitoACobrar = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.EXCLUIR_DEBITO_A_COBRAR, usuario);

		return temPermissaoExcluirDebitoACobrar;
	}
	
	/**
	 * Permite Gerar OS Seletivas de Hidrometro 
	 * 
	 * @author Ivan Sérgio
	 * @date 06/12/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoGerarOSSeletivasHidrometro(Usuario usuario) throws ControladorException {

		boolean temPermissao = this.verificarPermissaoEspecial(
						PermissaoEspecial.GERAR_OS_SELETIVA_HIDROMETRO, usuario);

		return temPermissao;
	}
	
	/**
	 * Permite Cancelar a Conta Sem RA 
	 * 
	 * @author Ivan Sérgio
	 * @date 28/01/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoCancelarContaSemRA(Usuario usuario) throws ControladorException {

		boolean temPermissao = this.verificarPermissaoEspecial(
						PermissaoEspecial.CANCELAR_CONTA_SEM_RA, usuario);

		return temPermissao;
	}
	
	/**
	 * Permite Atualizar os Dados da Fiscalizacao 
	 * 
	 * @author Ivan Sérgio
	 * @date 10/04/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoAtualizarDadosFiscalizacao(Usuario usuario) throws ControladorException {

		boolean temPermissao = this.verificarPermissaoEspecial(
						PermissaoEspecial.ATUALIZAR_DADOS_FISCALIZACAO, usuario);

		return temPermissao;
	}
	
	/**
	 * Permite Visualizar Dia de Vencimento da Conta em Cliente
	 * 
	 * @author Rômulo Aurélio
	 * @date 07/05/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoVisualizarDiaVencimentoContaCliente(Usuario usuario) throws ControladorException {

		boolean temPermissaoVisualizarDiaVencimentoContaCliente = this
				.verificarPermissaoEspecial(
						PermissaoEspecial.VISUALIZAR_DIA_VENCIMENTO_CLIENTE, usuario);

		return temPermissaoVisualizarDiaVencimentoContaCliente;
	}
	/**
	 * Permite Desfazer a Fiscalizacao do Boletim de OS Concluida 
	 * 
	 * @author Ivan Sérgio
	 * @date 02/05/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoDesfazerFiscalizacaoBoletimOSConcluida(
			Usuario usuario) throws ControladorException {

		boolean temPermissao = this.verificarPermissaoEspecial(
						PermissaoEspecial.DESFAZER_FISCALIZACAO_BOLETIM_OS_CONCLUIDA, usuario);

		return temPermissao;
	}
	
    /**
     * [UC0011] Inserir Imovel
     * 
     * @author Vivianne Sousa
     * @since 22/05/2008
     */
    public boolean verificarPermissaoInserirImovelComPerfilCorporativo(Usuario usuario) throws ControladorException {

        boolean temPermissaoInserirImovelComPerfilCorporativo = this
                .verificarPermissaoEspecial(
                        PermissaoEspecial.INSERIR_IMOVEL_COM_PERFIL_CORPORATIVO, usuario);

        return temPermissaoInserirImovelComPerfilCorporativo;
    }
    
    /**
     * [UC0014] Manter Imovel
     * 
     * @author Vivianne Sousa
     * @since 22/05/2008
     */
    public boolean verificarPermissaoAlterarPerfilCorporativoImovel(Usuario usuario) throws ControladorException {

        boolean temPermissaoAlterarPerfilCorporativoImovel = this
                .verificarPermissaoEspecial(
                        PermissaoEspecial.ALTERAR_PERFIL_CORPORATIVO_IMOVEL, usuario);

        return temPermissaoAlterarPerfilCorporativoImovel;
    }
    
    
    /**
     * [UC457]Encerrar Ordem Servico.
     * 
     * @author Yara Taciane
     * @since 18/06/2008
     */
    public boolean verificarPermissaoInformarDataEncOSAnteriorDataCorrente(Usuario usuario) throws ControladorException {

        boolean temPermissaoInformarDataEncOSAnteriorDataCorrente = this
                .verificarPermissaoEspecial(
                        PermissaoEspecial.INFORMAR_DATA_ENC_OS_ANTERIOR_DATA_CORRENTE, usuario);

        return temPermissaoInformarDataEncOSAnteriorDataCorrente;
    }
    
    
    /**
     * [UC399] Inserir Tipo de Solicitação com Especificações
     * 
     * @author Rafael Corrêa
     * @since 12/08/2008
     */
    public boolean verificarPermissaoAlterarIndicadorUsoSistemaTipoSolicitacao(Usuario usuario) throws ControladorException {

        boolean temPermissaoAlterarIndicadorUsoSistemaTipoSolicitacao = this
                .verificarPermissaoEspecial(
                        PermissaoEspecial.ALTERAR_INDICADOR_USO_SISTEMA_TIPO_SOLICITACAO, usuario);

        return temPermissaoAlterarIndicadorUsoSistemaTipoSolicitacao;
    }
   
    /**
     * @author Vivianne Sousa
     * @since 15/09/2008
     */
    public boolean verificarPermissaoAlterarValidadeExtratoDebito(Usuario usuario) throws ControladorException {

        boolean temPermissaoAlterarValidadeExtratoDebito = this
                .verificarPermissaoEspecial(
                        PermissaoEspecial.ALTERAR_VALIDADE_EXTRATO_DEBITO, usuario);

        return temPermissaoAlterarValidadeExtratoDebito;
    }
    
    /**
	 * Permite emitir segunda via de uma conta sem documento de CPF OU CNPJ cadastrados no sistema. Esse situação
	 * ocorrerá para as empresas que optarem pela obrigatoriedade do CPF OU CNPJ informado. 
	 * 
	 * @author Raphael Rossiter
	 * @date 24/10/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoEmitir2ViaSemDocumentoValido(Usuario usuario) throws ControladorException {

		boolean temPermissaoEmitir2ViaSemDocumentoValido = this
				.verificarPermissaoEspecial(PermissaoEspecial.EMITIR_2_VIA_SEM_DOCUMENTO_VALIDO, usuario);

		return temPermissaoEmitir2ViaSemDocumentoValido;
	}
	
    /**
	 * Permite emitir certidão negativa mesmo que o cliente tenha um superior. 
	 * 
	 * @author Rafael Corrêa
	 * @date 12/11/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoEmitirCertidaoNegativaComClienteSuperior(Usuario usuario) throws ControladorException {

		boolean temPermissaoEmitirCertidaoNegativaComClienteSuperior = this
				.verificarPermissaoEspecial(PermissaoEspecial.EMITIR_CERTIDAO_NEGATIVA_COM_CLIENTE_SUPERIOR, usuario);

		return temPermissaoEmitirCertidaoNegativaComClienteSuperior;
	}
	
	/**
	 * Permite retificar um conjunto de contas a partir do manter conta. 
	 * 
	 * @author Raphael Rossiter
	 * @date 03/11/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoRetificarConjuntoConta(Usuario usuario) throws ControladorException {

		boolean temPermissaoRetificarConjuntoConta = this
				.verificarPermissaoEspecial(PermissaoEspecial.RETIFICAR_CONJUNTO_CONTA, usuario);

		return temPermissaoRetificarConjuntoConta;
	}
	
	
	/**
	 * Permite efetuar supressão de ligação de água. 
	 * 
	 * @author Vivianne Sousa
	 * @date 09/03/2009
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoEfetuarSupressaoAgua(Usuario usuario) throws ControladorException {

		boolean temPermissaoEfetuarSupressaoAgua = this
				.verificarPermissaoEspecial(PermissaoEspecial.EFETUAR_SUPRESSAO_DE_LIGACAO_AGUA, usuario);

		return temPermissaoEfetuarSupressaoAgua;
	}
    
	/**
	 * Permite alterar situação de ligação para imovel com debitos . 
	 * 
	 * @author Vivianne Sousa
	 * @date 18/03/2009
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoAlterarSituacaoLigacaoParaImovelComDebito(Usuario usuario) throws ControladorException {

		boolean temPermissaoAlterarSituacaoLigacaoParaImovelComDebito = this
				.verificarPermissaoEspecial(PermissaoEspecial.ALTERAR_SITUACAO_LIGACAO_PARA_IMOVEL_COM_DEBITO, usuario);

		return temPermissaoAlterarSituacaoLigacaoParaImovelComDebito;
	}
	
	/**
	 * Permite retificar conta do imóvel com o perfil bloqueado. 
	 * 
	 * @author Ana Maria
	 * @date 20/04/2009
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoRetificarContaImovelPefilBloqueado(Usuario usuario) throws ControladorException {

		boolean temPermissaoRetificarContaImovelPefilBloqueado = this
				.verificarPermissaoEspecial(PermissaoEspecial.RETIFICAR_CONTA_IMOVEL_PERFIL_BLOQUEADO, usuario);

		return temPermissaoRetificarContaImovelPefilBloqueado;
	}
	
	/**
	 * Permite retificar conta do imóvel com o perfil bloqueado. 
	 * 
	 * @author Sávio Luiz
	 * @date 15/09/2009
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoEmissaoDocumentoCobranca(Usuario usuario) throws ControladorException {

		boolean temPermissaoEmissaoDocumentoCobranca = this
				.verificarPermissaoEspecial(PermissaoEspecial.EMITIR_DOCUMENTO_COBRANCA,usuario);

		return temPermissaoEmissaoDocumentoCobranca;
	}
	
	
	/**
	 * Verifica as permissões especiais do usuário 
	 * 
	 * @author Hugo Amorim
	 * @date 30/12/2009
	 * 
	 * @param permissaoEspecial
	 * @param usuario
	 * @return true or false
	 */
	public boolean verificarPermissaoEspecialAtiva(int permissaoEspecial,
			Usuario usuario) throws ControladorException {

		boolean retorno = false;
		
		FiltroPemissaoEspecial filtroPemissaoEspecial = new FiltroPemissaoEspecial();
		
		filtroPemissaoEspecial.adicionarParametro(
				new ParametroSimples(FiltroPemissaoEspecial.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroPemissaoEspecial.adicionarParametro(
				new ParametroSimples(FiltroPemissaoEspecial.ID, permissaoEspecial));
		
		Collection<PermissaoEspecial> colecaoPermissaoEspecial = 
			getControladorUtil().pesquisar(filtroPemissaoEspecial,
					PermissaoEspecial.class.getName());
		
		PermissaoEspecial permissaoEspecialPesquisa = 
			(PermissaoEspecial) Util.retonarObjetoDeColecao(colecaoPermissaoEspecial);
		
		//CASO NÃO EXISTE A PERMISSÃO NA BASE
		//COM INDICADOR DE USO ATIVO
		//RETORNAR TRUE
		if(permissaoEspecialPesquisa!=null){
		
		FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
		filtroUsuarioPemissaoEspecial
				.adicionarParametro(new ParametroSimples(
						FiltroUsuarioPemissaoEspecial.USUARIO_COMP_ID, usuario
								.getId()));
		filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(
				FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_COMP_ID,
				permissaoEspecialPesquisa.getId()));
		
		filtroUsuarioPemissaoEspecial.adicionarCaminhoParaCarregamentoEntidade("usuario");
		filtroUsuarioPemissaoEspecial.adicionarCaminhoParaCarregamentoEntidade("permissaoEspecial");

		Collection<UsuarioPermissaoEspecial> colecaoUsuarioPermissaoEspecial = 
			getControladorUtil().pesquisar(filtroUsuarioPemissaoEspecial,
				UsuarioPermissaoEspecial.class.getName());
		
			for (Iterator iterator = colecaoUsuarioPermissaoEspecial.iterator(); iterator.hasNext();) {
				UsuarioPermissaoEspecial usuarioPermissaoEspecial = 
					(UsuarioPermissaoEspecial) iterator.next();
			
				//VERIFICA SE POSSUI NA TABELA A PERMISSÃO 
				//COM INDICADOR DE USO IGUAL A ATIVO
				if(usuarioPermissaoEspecial.getUsuario().getId().toString().equals(usuario.getId().toString())
						&& usuarioPermissaoEspecial.getPermissaoEspecial().getId().toString().equals(permissaoEspecialPesquisa.getId().toString())){
					retorno = true;
				}
			
			}		
		}else{	
			retorno = true;			
		}

		return retorno;

	}
	
	/**
	 * @author Vivianne Sousa
	 * @date 23/09/2010
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoRetificarContaSemVerificarConsumoEsgoto(
			Usuario usuario) throws ControladorException {

		boolean temPermissaoRetificarContaSemVerificarConsumoEsgoto = this
				.verificarPermissaoEspecial(PermissaoEspecial.RETIFICAR_CONTA_SEM_VERIFICAR_CONSUMO_ESGOTO,usuario);

		return temPermissaoRetificarContaSemVerificarConsumoEsgoto;
	}
}
