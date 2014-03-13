package gcom.seguranca.acesso;

import gcom.gui.faturamento.bean.FiltrarImovelInserirManterContaHelper;
import gcom.relatorio.seguranca.FiltrarRelatorioAcessosUsuariosHelper;
import gcom.relatorio.seguranca.FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper;
import gcom.relatorio.seguranca.FiltrarRelatorioSolicitacaoAcessoHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.seguranca.transacao.Tabela;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.util.Collection;

public interface ControladorAcessoLocal extends javax.ejb.EJBLocalObject {

	public Collection getTabelaColunaPertencenteOperacao() throws ControladorException;

	public Collection getTabelaColunaDasOperacaoTabela(Integer idOperacao) throws ControladorException;

	public Integer inserirFuncionalidade(Funcionalidade funcionalidade, Collection colecaoFuncionalidadeDependencia) throws ControladorException;

	public void atualizarFuncionalidade(Funcionalidade funcionalidade, Collection colecaoFuncionalidadeDependencia) throws ControladorException;

	public void inserirGrupo(Grupo grupo, Collection grupoFuncionalidadeOperacao, Usuario usuarioLogado) throws ControladorException;

	public void atualizarGrupo(Grupo grupo, Collection grupoFuncionalidadeOperacao, Usuario usuarioLogado) throws ControladorException;
		
	public void removerGrupo(String[] idsGrupos, Usuario usuarioLogado) throws ControladorException ;

	public Integer inserirSituacaoUsuario(UsuarioSituacao usuarioSituacao) throws ControladorException;
	public void inserirOperacao(Operacao operacao, Collection<Tabela> colecaoOperacaoTabela, Usuario usuarioLogado)
			throws ControladorException;

	public Integer inserirAbrangenciaUsuario(UsuarioAbrangencia usuarioAbrangencia) throws ControladorException ;

	public void atualizarSituacaoUsuario(UsuarioSituacao usuarioSituacao, Collection colecaoUsuarioSituacao)
			throws ControladorException;

	public void atualizarAbrangenciaUsuario(UsuarioAbrangencia usuarioAbrangencia) throws ControladorException;

	public String construirMenuAcesso(Usuario usuarioLogado, String linkRetorno,Integer idGrupo) throws ControladorException ;
		
	public Usuario validarUsuario(String login, String senha) throws ControladorException ;
	
	public void registrarAcessoUsuario(Usuario usuario)	throws ControladorException ;
	
	public FuncionalidadeCategoria pesquisarArvoreFuncionalidades(Collection permissoesUsuario) throws ControladorException ;

	public FuncionalidadeCategoria pesquisarArvoreFuncionalidades(Integer modulo) throws ControladorException;
	
	public void efetuarAlteracaoSenha(Usuario usuarioLogado, String dataNascimentoString,String cpf,String lembreteSenha, 
			String novaSenha,String confirmacaoNovaSenha) throws ControladorException ;

	public void lembrarSenha(String login, String cpf, String dataNascimentoString) throws ControladorException;

	public String verificarTipoURL(String url) throws ControladorException;
	
	public boolean verificarAcessoPermitidoFuncionalidade(Usuario usuarioLogado, String urlFuncionalidade, 
			Collection colecaoGruposUsuario, Integer idFuncionalidade) throws ControladorException;
	
	public boolean verificarAcessoPermitidoOperacao(Usuario usuarioLogado, String urlOperacao, Collection colecaoGruposUsuario) throws ControladorException;

	public void atualizarOperacao(Operacao operacao,Collection<OperacaoTabela> colecaoOperacaoTabela, Usuario usuarioLogado) throws ControladorException ;

	public void removerOperacao(String[] idsOperacao,Usuario usuarioLogado) throws ControladorException ;

    public boolean verificarAcessoAbrangencia(Abrangencia abrangencia) throws ControladorException;
    
	public Collection pesquisarUsuarioFavorito(Integer idUsuario) throws ControladorException ;
	
	public boolean existeLocalidadeForaDaAbrangenciaUsuario( FiltrarImovelInserirManterContaHelper filtro, Integer nivelAbrangencia,Usuario usuarioLogado)
			throws ControladorException;
	
	public Collection pesquisarRelatorioAcessosPorUsuario(FiltrarRelatorioAcessosUsuariosHelper helper) throws ControladorException;
	
	public Integer pesquisarTotalRelatorioAcessosPorUsuario(FiltrarRelatorioAcessosUsuariosHelper filtro) throws ControladorException;
	
	public Collection pesquisarRelatorioFuncionalidadeOperacoesPorGrupo(FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper helper) 
			throws ControladorException;
	
	public Integer pesquisarTotalRelatorioFuncionalidadeOperacoesPorGrupo(FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper filtro) 
			throws ControladorException;
	
	public Collection pesquisarGrupos(FiltroGrupo filtroGrupo, Integer numeroPagina) throws ControladorException;
	
	public Integer inserirControleLiberacaoPermissaoEspecial(ControleLiberacaoPermissaoEspecial controleLiberacaoPermissaoEspecial, Usuario usuarioLogado)
			throws ControladorException;
	
	public void manterControleLiberacaoPermissaoEspecial(ControleLiberacaoPermissaoEspecial controleLiberacaoPermissaoEspecial, Usuario usuarioLogado)
			throws ControladorException;
	
	public boolean existeControlePermissaoEspecialFuncionalidade(Integer idFuncionalidade) throws ControladorException;
	
	public void removerGrupoDeSolicitacaoAcesso( Integer idsolicitacaoAcesso) throws ControladorException;
	
	public void atualizarCadastroSolicitacaoAcesso( Integer idsolicitacaoAcesso) throws ControladorException;
	
	public Collection pesquisarRelatorioSolicitacaoAcesso(FiltrarRelatorioSolicitacaoAcessoHelper helper)throws ControladorException;
}