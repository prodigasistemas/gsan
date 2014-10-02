package gcom.gui.util.tabelaauxiliar.faixa;

import gcom.cadastro.imovel.AreaConstruidaFaixa;
import gcom.gui.Funcionalidade;
import gcom.gui.util.tabelaauxiliar.DadosTelaTabelaAuxiliar;
import gcom.util.HibernateUtil;
import gcom.util.SistemaException;
import gcom.util.tabelaauxiliar.TabelaAuxiliar;
import gcom.util.tabelaauxiliar.TabelaAuxiliarAbstrata;
import gcom.util.tabelaauxiliar.faixa.TabelaAuxiliarFaixaInteiro;

import java.util.HashMap;

/**
 * @author R�mulo Aur�lio
 *
 */
public class DadosTelaTabelaAuxiliarFaixaInteiro extends DadosTelaTabelaAuxiliar {

	// protected DadosTelaTabelaAuxiliarFaixaReal(String titulo, TabelaAuxiliar
	// tabela, String funcionalidadeTabelaAux, String
	// nomeParametroFuncionalidade) {
	// super(titulo, tabela, funcionalidadeTabelaAux,
	// nomeParametroFuncionalidade);
	// 
	// }

	private String titulo;

	private TabelaAuxiliarAbstrata tabela;

	private String funcionalidadeTabelaAux;

	// private int tamanhoMaximoCampo;

	private String nomeParametroFuncionalidade;

	private static HashMap<String,DadosTelaTabelaAuxiliar> telas = new HashMap<String,DadosTelaTabelaAuxiliar>();

	private static HashMap<String,String[]> configuracaoParametrosTelas = new HashMap<String,String[]>();

	/**
	 * Este m�todo busca um map de funcionalidades cadastradas e cria uma
	 * inst�ncia da funcionalidade para ser usada na Tabela Auxiliar
	 * 
	 * @param nome
	 * @return
	 */
	public static DadosTelaTabelaAuxiliar obterDadosTelaTabelaAuxiliar(
			String nome) {
		System.out.println("-----------Adicionando itens");
		// Verifica se a funcionalidade desejada j� foi instanciada e j� est� no
		// cache
		if (!telas.containsKey(nome)) {
			String[] configuracaoTela = configuracaoParametrosTelas.get(nome);

			try {
				// Cria a inst�ncia do objeto DadosTelaTabelaAuxiliar
				DadosTelaTabelaAuxiliarFaixaInteiro dadosTela = new DadosTelaTabelaAuxiliarFaixaInteiro(
						configuracaoTela[1], (TabelaAuxiliarFaixaInteiro) Class
								.forName(configuracaoTela[0]).newInstance(),
						configuracaoTela[2], nome);
				// Coloca a inst�ncia criada no map que representa o cache com
				// as inst�ncia j� criadas
				telas.put(nome, dadosTela);

				return dadosTela;
			} catch (ClassNotFoundException ex) {
				throw new SistemaException();
			} catch (IllegalAccessException ex) {
				throw new SistemaException();
			} catch (InstantiationException ex) {
				throw new SistemaException();
			}
		}
		// Se o a funcionalidade j� estiver no cache, ent�o ela � retornada
		// sem a necessidade de passar pelo m�todo
		return telas.get(nome);
	}

	static {

		System.out.println("-----------Adicionando itens");

		configuracaoParametrosTelas.put("areaConstruidaFaixa", new String[] {
				AreaConstruidaFaixa.class.getName(), "Faixa Area Construida",
				Funcionalidade.TELA_AREA_CONSTRUIDA_FAIXA });

	}

	/**
	 * Construtor da classe DadosTelaTabelaAuxiliar
	 * 
	 * @param titulo
	 *            Descri��o do par�metro
	 * @param tabela
	 *            Descri��o do par�metro
	 * @param funcionalidadeTabelaAuxManter
	 *            Descri��o do par�metro
	 */
	protected DadosTelaTabelaAuxiliarFaixaInteiro(String titulo,
			TabelaAuxiliarAbstrata tabela, String funcionalidadeTabelaAux,
			String nomeParametroFuncionalidade) {
		super(nomeParametroFuncionalidade, tabela,
				nomeParametroFuncionalidade, nomeParametroFuncionalidade);
		this.titulo = titulo;
		this.tabela = tabela;
		this.funcionalidadeTabelaAux = funcionalidadeTabelaAux;
		this.nomeParametroFuncionalidade = nomeParametroFuncionalidade;
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param dados
	 *            Descri��o do par�metro
	 */
	protected static void adicionarDadosTela(DadosTelaTabelaAuxiliar dados) {
		String nomeCompletoClasse = dados.getTabelaAuxiliar().getClass()
				.getName();
		String nomeClasse = nomeCompletoClasse.substring(nomeCompletoClasse
				.lastIndexOf(".") + 1, nomeCompletoClasse.length());
		System.out.println("--------adicionando " + nomeClasse);
		telas.put(nomeClasse.toLowerCase(), dados);
	}

	/**
	 * Retorna o valor de dadosTela
	 * 
	 * @param nome
	 *            Descri��o do par�metro
	 * @return O valor de dadosTela
	 */
	public static DadosTelaTabelaAuxiliar getDadosTela(String nome) {
		return telas.get(nome.toLowerCase());
	}

	/**
	 * Retorna o valor de titulo
	 * 
	 * @return O valor de titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Retorna o valor de tabelaAuxiliar
	 * 
	 * @return O valor de tabelaAuxiliar
	 */
	public TabelaAuxiliarAbstrata getTabelaAuxiliarAbstrata() {
		return tabela;
	}

	/**
	 * Retorna o valor de funcionalidadeTabelaAuxManter
	 * 
	 * @return O valor de funcionalidadeTabelaAuxManter
	 */
	public String getFuncionalidadeTabelaFaixaInteiroManter() {
		return Funcionalidade.TABELA_AUXILIAR_FAIXA_INTEIRO_MANTER
				+ funcionalidadeTabelaAux;
	}

	/**
	 * Retorna o valor de funcionalidadeTabelaAuxManter
	 * 
	 * @return O valor de funcionalidadeTabelaAuxManter
	 */
	public String getFuncionalidadeTabelaFaixaInteiroFiltrar() {
		return Funcionalidade.TABELA_AUXILIAR_FAIXA_INTEIRO_FILTRAR
				+ funcionalidadeTabelaAux;
	}

	/**
	 * Retorna o valor de funcionalidadeTabelaAuxManter
	 * 
	 * @return O valor de funcionalidadeTabelaAuxManter
	 */
	public String getFuncionalidadeTabelaFaixaInteiroInserir() {
		return Funcionalidade.TABELA_AUXILIAR_FAIXA_INTEIRO_INSERIR
				+ funcionalidadeTabelaAux;
	}

	/**
	 * Retorna o valor de tamanhoMaximoCampo
	 * 
	 * @return O valor de tamanhoMaximoCampo
	 */
	public int getTamanhoMaximoDescricao() {
		return HibernateUtil.getColumnSize(tabela.getClass(), "descricao");
	}

	public String getNomeParametroFuncionalidade() {
		return nomeParametroFuncionalidade;
	}

	public void setNomeParametroFuncionalidade(
			String nomeParametroFuncionalidade) {
		this.nomeParametroFuncionalidade = nomeParametroFuncionalidade;
	}

	/**
	 * Retorna o valor de tabelaAuxiliar
	 * 
	 * @return O valor de tabelaAuxiliar
	 */
	public TabelaAuxiliar getTabela() {
		return super.getTabelaAuxiliar();
	}
}
