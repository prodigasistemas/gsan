package gcom.operacional;

import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;

import java.util.Collection;

/**
 * Declaração pública de serviços do Session Bean de ControladorCliente
 * 
 * @author Sávio Luiz
 * @created 25 de Abril de 2005
 */
public interface ControladorOperacionalLocal extends
		javax.ejb.EJBLocalObject {


	/**
	 * [UC0414] - Informar Programação de Abastecimento e Manutenção
	 * 
	 * [SB0006] - Atualizar Programação de Abastecimento na Base de Dados
	 * [SB0007] - Atualizar Programação de Manutenção na Base de Dados
	 * 
	 * @author Rafael Pinto
	 * @created 09/11/2006
	 * 
	 * @throws ControladorException Controlador Exception
	 */
	public void atualizarProgramacaoAbastecimentoManutencao(Collection colecaoProgramacaoAbastecimento,
		Collection colecaoProgramacaoAbastecimentoRemovidas,Collection colecaoProgramacaoManutencao,
		Collection colecaoProgramacaoManutencaoRemovidas,Usuario usuario) throws ControladorException ;
	
	/**
	 * Permite inserir um Distrito Operacional
	 * 
	 * [UC0521] Inserir Distrito Operacional
	 * 
	 * @author Eduardo Bianchi	
	 * @date 29/01/2007
	 * 
	 */
	public Integer inserirDistritoOperacional(String descricao, String descricaoAbreviada, String setorAbastecimento,
			 Usuario usuarioLogado)throws ControladorException;	

	/**
	 * [UC0522] Manter Distrito Operacional 
	 * 			
	 * 			Remover Distrito Operacional
	 * 
	 * @author Eduardo Bianchi
	 * @date 05/02/2007
	 * 
	 */
	public void removerDistritoOperacional(String[] ids, Usuario usuarioLogado)throws ControladorException;
	
	/**
	 * [UC005] Manter Distrito Operacional [SB0001] Atualizar Distrito Operacional 
	 * 
	 * @author Eduardo Bianchi
	 * @date 09/02/2007
	 * 
	 * @pparam distritoOperacinal
	 * @throws ControladorException
	 */
	public void atualizarDistritoOperacional(DistritoOperacional distritoOperacional,Usuario usuarioLogado) throws ControladorException;
	
	
	/**
	 * Permite inserir um Sistema de Esgoto
	 * 
	 * [UC0524] Inserir Sistema de Esgoto
	 * 
	 * @author Kassia Albuquerque
	 * @date 09/03/2007
	 * 
	 */
	public Integer inserirSistemaEsgoto(SistemaEsgoto sistemaEsgoto, Usuario usuarioLogado) throws ControladorException;

	/**
	 * [UC0525] Manter Sistema Esgoto [SB0001]Atualizar Sistema Esgoto
	 * 
	 * @author Kassia Albuquerque
	 * @date 19/03/2007
	 * 
	 */

	public void atualizarSistemaEsgoto(SistemaEsgoto sistemaEsgoto,Usuario usuarioLogado) throws ControladorException;
	
	
	/**
	 * [UC0525] Manter Sistema Esgoto [SB0002]Remover Sistema Esgoto
	 * 
	 * @author Kassia Albuquerque
	 * @date 20/03/2007
	 * 
	 */
	public void removerSistemaEsgoto(String[] ids, Usuario usuarioLogado)throws ControladorException;
	
	/**
	 * [UC0081] Manter Marca Hidrometro
	 * 
	 * @author Bruno Barros
	 * @date 03/07/2007
	 * 
	 */
	
	public void removerHidrometroMarca(String[] ids, Usuario usuarioLogado)throws ControladorException;
	
	/**
	 * [UC0081] Manter Hidrometro Marca
	 * 
	 * @author Bruno Barros
	 * @date 04/07/2007
	 * 
	 */		
	public void atualizarHidrometroMarca(HidrometroMarca hidrometroMarca,Usuario usuarioLogado) 
						throws ControladorException;
	
	/**
	 * [UC0596] - Inserir Qualidade de agua
	 * 
	 * Pesquisa as fonte de captacao apatir da tabela de SetorFonteCaptacao
	 * 
	 * @author Rafael Pinto
	 * @date 15/10/2008
	 * 
	 * @param Collection colecaoSetorComercial
	 * @throws ControladorException
	 */
	
	public Collection<FonteCaptacao> pesquisarFonteCaptacao(Collection colecaoSetorComercial)
		throws ControladorException ;
}
