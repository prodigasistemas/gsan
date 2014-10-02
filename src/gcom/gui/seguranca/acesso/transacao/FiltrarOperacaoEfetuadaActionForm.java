package gcom.gui.seguranca.acesso.transacao;

import java.util.Collection;
import java.util.Vector;

import org.apache.struts.action.ActionForm;

public class FiltrarOperacaoEfetuadaActionForm  extends ActionForm {

	private String nomeFuncionalidade = "";
	private String idFuncionalidade  = "";
	private String funcionalidadeNaoEncontrada = "false";
	private static final long serialVersionUID = 1L;
	private String[] operacoes = new String[0];
	
	private String unidadeNegocio = "";
	
	private String nomeUsuario = "";
	private String idUsuario = "";
	private String usuarioNaoEncontrada = "false";
	private String idUsuarioAcao = "";
	private String dataInicial = "";
	private String dataFinal = "";
	private String horaInicial = "";
	private String horaFinal = "";
	private String nomeTabela = "";
	private String idTabela = "";
	private String tabelaNaoEncontrada = "false";	
	private String[] idTabelaColuna = new String[0];	
	private String id1 = "";

	private String idTabelaColunaSemOperacao = "";
	private String lebelArgumentoPesquisa = "";
	private String idArgumentoPesquisa = "";
	private String nomeArgumentoPesquisa = "";
	private String valorArgumentoPesquisa = "";
	private String url = "";
	private String msgArgumentoPesquisaEncontrado = "";
	
	/** ATRIBUTO QUE SERVE COMO INDICADOR SE EH PRA HABILITAR OU NAO OS CAMPOS DESCRITOS */
	private String desabilitaPesquisarPor = "false";
	private String desabilitaArgumentoPesquisa = "false";
	private String desabilitaInformacoesDisponiveis = "";

	private Collection collUsuarioAcao;
	private Collection collTabelaColuna;
	private Collection collTabelaColunaSemOperacao;
	
	private String acao;

//	private String  = "";

//	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
//		
//		super.reset(arg0, arg1);
//
//		 acao = "";
//		 nomeFuncionalidade = "";
//		 idFuncionalidade = "";
//		 nomeUsuario = "";
//		 idUsuario = "";
//		 idUsuarioAcao = "";
//		 dataInicial = "";
//		 dataFinal = "";
//		 horaInicial = "";
//		 horaFinal = "";
//		 nomeTabela = "";
//		 idTabela = "";
//		 idTabelaColuna = new String[0];
//		 id1 = "";
//		 funcionalidadeNaoEncontrada = "false";
//		 usuarioNaoEncontrada = "false";
//		 tabelaNaoEncontrada = "false";
//		 idTabelaColunaSemOperacao = "";
//
//		 idTabelaColunaSemOperacao = "";
//		 lebelArgumentoPesquisa = "";
//		 idArgumentoPesquisa = "";
//		 nomeArgumentoPesquisa = "";
//		 valorArgumentoPesquisa = "";
//
//		 url = "";			 
// 		 desabilitaPesquisarPor = "false";
//		 desabilitaArgumentoPesquisa = "false";
//		 desabilitaInformacoesDisponiveis = "";
//
//		 collUsuarioAcao = new Vector(); 
//		 collTabelaColuna = new Vector();
//		 collTabelaColunaSemOperacao = new Vector();
//	}

	/**
	 * @return Retorna o campo url.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url O url a ser setado.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return Retorna o campo desabilitaArgumentoPesquisa.
	 */
	public String getDesabilitaArgumentoPesquisa() {
		return desabilitaArgumentoPesquisa;
	}

	/**
	 * @param desabilitaArgumentoPesquisa O desabilitaArgumentoPesquisa a ser setado.
	 */
	public void setDesabilitaArgumentoPesquisa(String desabilitaArgumentoPesquisa) {
		this.desabilitaArgumentoPesquisa = desabilitaArgumentoPesquisa;
		this.idArgumentoPesquisa = "";
		this.nomeArgumentoPesquisa = "";
	}

	/**
	 * @return Retorna o campo desabilitaInformacoesDisponíveis.
	 */
	public String getDesabilitaInformacoesDisponiveis() {
		return desabilitaInformacoesDisponiveis;
	}

	/**
	 * @param desabilitaInformacoesDisponíveis O desabilitaInformacoesDisponíveis a ser setado.
	 */
	public void setDesabilitaInformacoesDisponiveis(
			String desabilitaInformacoesDisponiveis) {
		this.desabilitaInformacoesDisponiveis = desabilitaInformacoesDisponiveis;
	}

	/**
	 * @return Retorna o campo desabilitaPesquisarPor.
	 */
	public String getDesabilitaPesquisarPor() {
		return desabilitaPesquisarPor;
	}

	/**
	 * @param desabilitaPesquisarPor O desabilitaPesquisarPor a ser setado.
	 */
	public void setDesabilitaPesquisarPor(String desabilitaPesquisarPor) {
		this.desabilitaPesquisarPor = desabilitaPesquisarPor;
	}

	/**
	 * @return Retorna o campo idArgumentoPesquisa.
	 */
	public String getIdArgumentoPesquisa() {
		return idArgumentoPesquisa;
	}

	/**
	 * @param idArgumentoPesquisa O idArgumentoPesquisa a ser setado.
	 */
	public void setIdArgumentoPesquisa(String idArgumentoPesquisa) {
		this.idArgumentoPesquisa = idArgumentoPesquisa;
	}

	/**
	 * @return Retorna o campo lebelArgumentoPesquisa.
	 */
	public String getLebelArgumentoPesquisa() {
		return lebelArgumentoPesquisa;
	}

	/**
	 * @param lebelArgumentoPesquisa O lebelArgumentoPesquisa a ser setado.
	 */
	public void setLebelArgumentoPesquisa(String lebelArgumentoPesquisa) {
		this.lebelArgumentoPesquisa = lebelArgumentoPesquisa;
	}

	/**
	 * @return Retorna o campo nomeArgumentoPesquisa.
	 */
	public String getNomeArgumentoPesquisa() {
		return nomeArgumentoPesquisa;
	}

	/**
	 * @param nomeArgumentoPesquisa O nomeArgumentoPesquisa a ser setado.
	 */
	public void setNomeArgumentoPesquisa(String nomeArgumentoPesquisa) {
		this.nomeArgumentoPesquisa = nomeArgumentoPesquisa;
	}

	/**
	 * @return Retorna o campo idTabelaColunaSemOperacao.
	 */
	public String getIdTabelaColunaSemOperacao() {
		return idTabelaColunaSemOperacao;
	}

	/**
	 * @param idTabelaColunaSemOperacao O idTabelaColunaSemOperacao a ser setado.
	 */
	public void setIdTabelaColunaSemOperacao(String idTabelaColunaSemOperacao) {
		this.idTabelaColunaSemOperacao = idTabelaColunaSemOperacao;
	}

	/**
	 * @return Retorna o campo collTabelaColunaSemOperacao.
	 */
	public Collection getCollTabelaColunaSemOperacao() {
		return collTabelaColunaSemOperacao;
	}

	/**
	 * @param collTabelaColunaSemOperacao O collTabelaColunaSemOperacao a ser setado.
	 */
	public void setCollTabelaColunaSemOperacao(
			Collection collTabelaColunaSemOperacao) {
		this.collTabelaColunaSemOperacao = collTabelaColunaSemOperacao;
	}

	public Collection getCollTabelaColuna() {
		return collTabelaColuna;
	}

	public void setCollTabelaColuna(Collection collTabelaColuna) {
		this.collTabelaColuna = collTabelaColuna;
	}

	public Collection getCollUsuarioAcao() {
		return collUsuarioAcao;
	}

	public void setCollUsuarioAcao(Collection collUsuarioAcao) {
		this.collUsuarioAcao = collUsuarioAcao;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}

	public String getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(String horaInicial) {
		this.horaInicial = horaInicial;
	}

	public String[] getIdTabelaColuna() {
		return idTabelaColuna;
	}

	public void setIdTabelaColuna(String idTabelaColuna[]) {
		Vector v = new Vector();
		if (idTabelaColuna != null) {
			for (int i = 0; i < idTabelaColuna.length; i++) {
				Integer integer = new Integer(idTabelaColuna[i]);
				if (integer.intValue() > 0){
					v.add(integer);
				}
			}
		}
		this.idTabelaColuna = new String[v.size()];
		for (int i = 0; i < v.size(); i++) {
			this.idTabelaColuna[i] = v.get(i).toString();
		}
	}

	public String getIdFuncionalidade() {
		return idFuncionalidade;
	}

	public void setIdFuncionalidade(String idFuncionalidade) {
		this.idFuncionalidade = idFuncionalidade;
	}

	public String getIdTabela() {
		return idTabela;
	}

	public void setIdTabela(String idTabela) {
		this.idTabela = idTabela;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdUsuarioAcao() {
		return idUsuarioAcao;
	}

	public void setIdUsuarioAcao(String idUsuarioAcao) {
		this.idUsuarioAcao = idUsuarioAcao;
	}

	public String getNomeFuncionalidade() {
		return nomeFuncionalidade;
	}

	public void setNomeFuncionalidade(String nomeFuncionalidade) {
		this.nomeFuncionalidade = nomeFuncionalidade;
	}

	public String getNomeTabela() {
		return nomeTabela;
	}

	public void setNomeTabela(String nomeTabela) {
		this.nomeTabela = nomeTabela;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getFuncionalidadeNaoEncontrada() {
		return funcionalidadeNaoEncontrada;
	}

	public void setFuncionalidadeNaoEncontrada(String funcionalidadeNaoEncontrada) {
		this.funcionalidadeNaoEncontrada = funcionalidadeNaoEncontrada;
	}

	public String getId1() {
		return id1;
	}

	public void setId1(String id1) {
		this.id1 = id1;
	}

	public String getTabelaNaoEncontrada() {
		return tabelaNaoEncontrada;
	}

	public void setTabelaNaoEncontrada(String tabelaNaoEncontrada) {
		this.tabelaNaoEncontrada = tabelaNaoEncontrada;
	}

	public String getUsuarioNaoEncontrada() {
		return usuarioNaoEncontrada;
	}

	public void setUsuarioNaoEncontrada(String usuarioNaoEncontrada) {
		this.usuarioNaoEncontrada = usuarioNaoEncontrada;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	/**
	 * @return Returns the valorArgumentoPesquisa.
	 */
	public String getValorArgumentoPesquisa() {
		return valorArgumentoPesquisa;
	}

	/**
	 * @param valorArgumentoPesquisa The valorArgumentoPesquisa to set.
	 */
	public void setValorArgumentoPesquisa(String valorArgumentoPesquisa) {
		this.valorArgumentoPesquisa = valorArgumentoPesquisa;
	}

	/**
	 * @return Returns the valorArgumentoPesquisaNaoEncontrado.
	 */
	public String getMsgArgumentoPesquisaEncontrado() {
		return msgArgumentoPesquisaEncontrado;
	}

	/**
	 * @param valorArgumentoPesquisaNaoEncontrado The valorArgumentoPesquisaNaoEncontrado to set.
	 */
	public void setMsgArgumentoPesquisaEncontrado(
			String msgArgumentoPesquisaEncontrado) {
		this.msgArgumentoPesquisaEncontrado = msgArgumentoPesquisaEncontrado;
	}

	public String[] getOperacoes() {
		return operacoes;
	}

	public void setOperacoes(String[] operacoes) {
		this.operacoes = operacoes;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

}
