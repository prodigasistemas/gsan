package gcom.gui.atendimentopublico;

import gcom.cadastro.cliente.Cliente;

/**
 * [UC0738] Gerar Relat�rio de Im�veis com Faturas em Atraso
 * 
 * @author Bruno Barros
 *
 * @date 22/01/2008
 */


public class ExibirGerarCertidaoNegativaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,

		// Seta o mapeamento de retorno
		GerarCertidaoNegativaActionForm form = 
		// Flag indicando que o usu�rio fez uma consulta a partir da tecla Enter
		// Pesquisar Imovel
	
	/**
	private void pesquisarImovel(GerarCertidaoNegativaActionForm form,
		String idImovel = form.getIdImovel();
		FiltroImovel filtroImovel = new FiltroImovel();
		// Recupera o Imovel
		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {
			httpServletRequest.setAttribute("idImovelNaoEncontrado", null );
			Imovel imovel = 
			// Encontramos o cliente Usuario
			// Carregamos as informa��es
			// Recupera o Cliente
			cliente = 
			if ( cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica().intValue() == ClienteTipo.INDICADOR_PESSOA_JURIDICA.intValue()  ){
			form.setNomeClienteUsuario( cliente.getNome() );
			try {
		} else {
			form.setIdImovel( null );
}