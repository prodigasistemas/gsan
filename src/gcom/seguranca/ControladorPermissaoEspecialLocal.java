package gcom.seguranca;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;

/**
 * Definição da lógica de negócio do Session Bean de
 * ControladorPermissaoEspecial
 * 
 * @author Rodrigo Silveira
 * @created 07/11/2006
 */

public interface ControladorPermissaoEspecialLocal
		extends
			javax.ejb.EJBLocalObject {

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
	public boolean verificarPermissaoEspecial(
			int permissaoEspecial, Usuario usuario)
			throws ControladorException;
	
	
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
			Usuario usuario,Object objeto) throws ControladorException;
	
	/**
	 * Verifica permissão especial para aceitar um valor de entrada menor q o valor mínimo de entrada 
	 * na terceira página de Efetuar Parcelamento Débitos
	 * 
	 * @author Vivianne Sousa
	 * @date 28/11/2006
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoValMinimoEntrada(Usuario usuario) 
		throws ControladorException;
	
	/**
	 * Verifica permissão especial para atualizar um cliente que seja usuário da tarifa social
	 * 
	 * @author Rafael Corrêa
	 * @date 16/02/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoAtualizarUsuarioTarifaSocial(Usuario usuario) 
		throws ControladorException;
	
	public boolean verificarPermissaoAtualizarUsuarioBolsaAgua(Usuario usuario) 
			throws ControladorException;
	
	public boolean verificarPermissaoConsultarCpfNis(Usuario usuario) 
			throws ControladorException;
	
	public boolean verificarPermissaoRecadastramentoAguaPara(
			Usuario usuario) throws ControladorException;
		
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
			   Usuario usuario) throws ControladorException ;
	
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
			Usuario usuario) throws ControladorException;
	
	/**
	 * Verifica permissão especial para atualizar Imovel com logradouro.municipio
	 * diferente de setorComercial.municipio
	 * 
	 * @author Rômulo Aurélio
	 * @date 23/02/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoAtualizarImovelMunicipioLogradouroDiferenteSetor(
			Usuario usuario) throws ControladorException;
	
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
			Usuario usuario) throws ControladorException;

	
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
			Usuario usuario) throws ControladorException;

	
	
	/**
	  * Verifica permissão especial para NÃO gerar débito no informar retorno OS fiscalização
	  * 
	  * @author Raphael Rossiter
	  * @date 03/03/2007
	  * 
	  * @param usuario
	  */
	 public boolean verificarPermissaoGeracaoDebitoOSFiscalizacao(
				   Usuario usuario) throws ControladorException ;
	 
	 
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
			Usuario usuario)throws ControladorException ;
	
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
			Usuario usuario) throws ControladorException ;

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
			Usuario usuario) throws ControladorException ;
	
	/**
	 * Manter conta - Alterar vencimento sem ra
	 * 
	 * @author Ana Maria
	 * @date 26/03/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoAlterarVencimentoSemRa(Usuario usuario) 
		throws ControladorException;
	
	
	/**
	 * Inserir conta - inserir conta sem cronograma de faturamento e sem atividade efetuar leitura
	 * 
	 * @author Raphael Rossiter
	 * @date 08/05/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoInserirContaFaturamentoAntecipado(Usuario usuario) throws ControladorException ;
	
	/**
	 * Inseir Débito a cobrar sem RA
	 * 
	 * @author Ana Maria
	 * @date 23/05/2007
	 * 
	 * @param httpServletRequest
	 * @param usuario
	 */
	public boolean verificarPermissaoInserirDebitoACobrarSemRa(Usuario usuario) throws ControladorException;
	
	/**
	 * Atualizar Cliente
	 * 
	 * @author Vivianne Sousa
	 * @date 30/07/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoAlterarNomeCliente(Usuario usuario) throws ControladorException ;
	
	/**
	 * Incluir Devolução
	 * 
	 * @author Vivianne Sousa
	 * @date 30/07/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoIcluirDevolucaoMaiorValorMaximo(Usuario usuario) throws ControladorException;
	
	/**
	 * [UC0194] Crédito a Realizar Permite inserir um crédito a realizar
	 * 
	 * @author Sávio Luiz
	 * @since 21/08/2006
	 */
	public boolean verificarPermissaoIcluirCreditoARealizarValorMaximo(Usuario usuario) throws ControladorException;
	
	/**
	 * [UC0194] Crédito a Realizar Permite inserir um crédito a realizar
	 * 
	 * @author Sávio Luiz
	 * @since 21/08/2006
	 */
	public boolean verificarPermissaoIcluirCreditoARealizarQuantidadeParcelasMaximo(Usuario usuario) throws ControladorException;
	
	/**
	 * [UC0630] Solicitar Emissão do Extrato de Debitos
	 * 
	 * @author Vivianne Sousa
	 * @since 30/08/2007
	 */
	public boolean verificarPermissaoIncluirAcrescimoImpontualidadeNoExtratoDeDebitosComDesconto(Usuario usuario) throws ControladorException ;

	/**
	 * [UC0630] Solicitar Emissão do Extrato de Debitos
	 * 
	 * @author Vivianne Sousa
	 * @since 30/08/2007
	 */
	public boolean verificarPermissaoRetirarTaxaCobrancaDoExtratoDeDebitos(Usuario usuario) throws ControladorException; 

	/**
	 * [UC0XXX] Consultar Débitos
	 * 
	 * @author Rafael Corrêa
	 * @since 13/09/2007
	 */
	public boolean verificarPermissaoConsultarDebitosAtualDoImovelOuTodos(Usuario usuario) throws ControladorException;

	
	/**
	 * Inserir débito a cobrar - inserir debito a cobrar independente da situacao da ligacao de agua e esgoto do
	 * imovel 
	 * 
	 * @author Raphael Rossiter
	 * @date 03/10/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoInserirDebitoACobrarImovelSituacao(Usuario usuario) 
	throws ControladorException;
	
	/**
	 * Reiniciar um batch
	 * 
	 * @author Rafael Corrêa
	 * @date 06/11/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoReiniciarBatch(Usuario usuario) throws ControladorException;
	
	/**
	 * Permite retificar uma conta sem RA 
	 * 
	 * @author Raphael Rossiter
	 * @date 09/11/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoRetificarContaSemRA(Usuario usuario) throws ControladorException;
	
	/**
	 * Permite excluir debito a cobrar 
	 * 
	 * @author Marcio Roberto
	 * @date 26/11/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoExcluirDebitoACobrar(Usuario usuario) throws ControladorException;
	
	/**
	 * Permite Gerar OS Seletivas de Hidrometro
	 * 
	 * @author Ivan Sérgio
	 * @date 06/12/2007
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoGerarOSSeletivasHidrometro(Usuario usuario) throws ControladorException;
	
	/**
	 * Permite Cancelar a Conta Sem RA 
	 * 
	 * @author Ivan Sérgio
	 * @date 28/01/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoCancelarContaSemRA(Usuario usuario) throws ControladorException;
	
	/**
	 * Permite Atualizar os Dados da Fiscalizacao 
	 * 
	 * @author Ivan Sérgio
	 * @date 10/04/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoAtualizarDadosFiscalizacao(Usuario usuario) throws ControladorException;
	
	
	/**
	 * Permite Visualizar Dia de Vencimento da Conta no Cliente
	 * 
	 * @author Rômulo Aurélio
	 * @date 07/05/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoVisualizarDiaVencimentoContaCliente(Usuario usuario) throws ControladorException ;
	
	/**
	 * Permite Desfazer a Fiscalizacao do Boletim de OS Concluida 
	 * 
	 * @author Ivan Sérgio
	 * @date 02/05/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoDesfazerFiscalizacaoBoletimOSConcluida(
			Usuario usuario) throws ControladorException;
    
    /**
     * [UC0011] Inserir Imovel
     * 
     * @author Vivianne Sousa
     * @since 22/05/2008
     */
    public boolean verificarPermissaoInserirImovelComPerfilCorporativo(Usuario usuario) throws ControladorException; 
  
  
    /**
     * [UC0014] Manter Imovel
     * 
     * @author Vivianne Sousa
     * @since 22/05/2008
     */
    public boolean verificarPermissaoAlterarPerfilCorporativoImovel(Usuario usuario) throws ControladorException;

    /**
     * [UC457]Encerrar Ordem Servico.
     * 
     * @author Yara Taciane
     * @since 22/05/2008
     */
	public boolean verificarPermissaoInformarDataEncOSAnteriorDataCorrente(Usuario usuario) throws ControladorException;
	
    /**
     * [UC399] Inserir Tipo de Solicitação com Especificações
     * 
     * @author Rafael Corrêa
     * @since 12/08/2008
     */
    public boolean verificarPermissaoAlterarIndicadorUsoSistemaTipoSolicitacao(Usuario usuario) throws ControladorException;
    
    /**
     * @author Vivianne Sousa
     * @since 15/09/2008
     */
    public boolean verificarPermissaoAlterarValidadeExtratoDebito(Usuario usuario) throws ControladorException;


    /**
	 * Verifica permissão especial para habilitar ou não o formulário
	 * 
	 * @author Arthur Carvalho
	 * @date 13/10/2008
	 */
	public boolean verificarPermissaoValidarAcrescimosImpontualidade(Usuario usuario)
			throws ControladorException;
	
	/**
	 * Permite emitir segunda via de uma conta sem documento de CPF OU CNPJ cadastrados no sistema. Esse situação
	 * ocorrerá para as empresas que optarem pela obrigatoriedade do CPF OU CNPJ informado. 
	 * 
	 * @author Raphael Rossiter
	 * @date 24/10/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoEmitir2ViaSemDocumentoValido(Usuario usuario) throws ControladorException;
	
    /**
	 * Permite emitir certidão negativa mesmo que o cliente tenha um superior. 
	 * 
	 * @author Rafael Corrêa
	 * @date 12/11/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoEmitirCertidaoNegativaComClienteSuperior(Usuario usuario) throws ControladorException;
	
	
	/**
	 * Permite retificar um conjunto de contas a partir do manter conta. 
	 * 
	 * @author Raphael Rossiter
	 * @date 03/11/2008
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoRetificarConjuntoConta(Usuario usuario) throws ControladorException;
	
	/**
	 * Permite efetuar supressão de ligação de água. 
	 * 
	 * @author Vivianne Sousa
	 * @date 09/03/2009
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoEfetuarSupressaoAgua(Usuario usuario) throws ControladorException;
	
	/**
	 * Permite alterar situação de ligação para imovel com debitos . 
	 * 
	 * @author Vivianne Sousa
	 * @date 18/03/2009
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoAlterarSituacaoLigacaoParaImovelComDebito(Usuario usuario) throws ControladorException;
	
	/**
	 * Permite retificar conta do imóvel com o perfil bloqueado. 
	 * 
	 * @author Ana Maria
	 * @date 20/04/2009
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoRetificarContaImovelPefilBloqueado(Usuario usuario) throws ControladorException;
	
	/**
	 * Permite retificar conta do imóvel com o perfil bloqueado. 
	 * 
	 * @author Sávio Luiz
	 * @date 15/09/2009
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoEmissaoDocumentoCobranca(Usuario usuario) throws ControladorException;
	
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
			Usuario usuario) throws ControladorException;

	/**
	 * @author Vivianne Sousa
	 * @date 23/09/2010
	 * 
	 * @param usuario
	 */
	public boolean verificarPermissaoRetificarContaSemVerificarConsumoEsgoto(
			Usuario usuario) throws ControladorException ;
}
