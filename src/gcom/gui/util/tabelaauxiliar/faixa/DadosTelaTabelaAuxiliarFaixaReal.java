package gcom.gui.util.tabelaauxiliar.faixa;

import gcom.cadastro.imovel.PiscinaVolumeFaixa;
import gcom.cadastro.imovel.ReservatorioVolumeFaixa;
import gcom.gui.Funcionalidade;
import gcom.gui.util.tabelaauxiliar.DadosTelaTabelaAuxiliar;
import gcom.util.HibernateUtil;
import gcom.util.SistemaException;
import gcom.util.tabelaauxiliar.TabelaAuxiliar;
import gcom.util.tabelaauxiliar.TabelaAuxiliarAbstrata;
import gcom.util.tabelaauxiliar.faixa.TabelaAuxiliarFaixaReal;

import java.util.HashMap;

/**
 * @author Administrador
 * 
 */
public class DadosTelaTabelaAuxiliarFaixaReal extends DadosTelaTabelaAuxiliar {

	// protected DadosTelaTabelaAuxiliarFaixaReal(String titulo, TabelaAuxiliar
	// tabela, String funcionalidadeTabelaAux, String
	// nomeParametroFuncionalidade) {
	// super(titulo, tabela, funcionalidadeTabelaAux,
	// nomeParametroFuncionalidade);
	// // TODO Auto-generated constructor stub
	// }

	private String titulo;

	private TabelaAuxiliarAbstrata tabela;

	private String funcionalidadeTabelaAux;

	// private int tamanhoMaximoCampo;

	private String nomeParametroFuncionalidade;

	private static HashMap<String,DadosTelaTabelaAuxiliar> telas = new HashMap<String,DadosTelaTabelaAuxiliar>();

	private static HashMap<String,String[]> configuracaoParametrosTelas = new HashMap<String,String[]>();

	/**
	 * Este método busca um map de funcionalidades cadastradas e cria uma
	 * instância da funcionalidade para ser usada na Tabela Auxiliar
	 * 
	 * @param nome
	 * @return
	 */
	public static DadosTelaTabelaAuxiliar obterDadosTelaTabelaAuxiliar(
			String nome) {
		System.out.println("-----------Adicionando itens");
		// Verifica se a funcionalidade desejada já foi instanciada e já está no
		// cache
		if (!telas.containsKey(nome)) {
			String[] configuracaoTela = configuracaoParametrosTelas.get(nome);

			try {
				// Cria a instância do objeto DadosTelaTabelaAuxiliar
				DadosTelaTabelaAuxiliarFaixaReal dadosTela = new DadosTelaTabelaAuxiliarFaixaReal(
						configuracaoTela[1], (TabelaAuxiliarFaixaReal) Class
								.forName(configuracaoTela[0]).newInstance(),
						configuracaoTela[2], nome);
				// Coloca a instância criada no map que representa o cache com
				// as instância já criadas
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
		// Se o a funcionalidade já estiver no cache, então ela é retornada
		// sem a necessidade de passar pelo método
		return telas.get(nome);
	}

	static {

		System.out.println("-----------Adicionando itens");

		configuracaoParametrosTelas.put("piscinaVolumeFaixa", new String[] {
				PiscinaVolumeFaixa.class.getName(), "Faixa Volume Piscina",
				Funcionalidade.TELA_PISCINA_VOLUME_FAIXA });

		configuracaoParametrosTelas.put("reservatorioVolumeFaixa",
				new String[] { ReservatorioVolumeFaixa.class.getName(),
						"Faixa Volume Reservatorio",
						Funcionalidade.TELA_RESERVATORIO_VOLUME_FAIXA });

	}

	/**
	 * Construtor da classe DadosTelaTabelaAuxiliar
	 * 
	 * @param titulo
	 *            Descrição do parâmetro
	 * @param tabela
	 *            Descrição do parâmetro
	 * @param funcionalidadeTabelaAuxManter
	 *            Descrição do parâmetro
	 */
	protected DadosTelaTabelaAuxiliarFaixaReal(String titulo,
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
	 * < <Descrição do método>>
	 * 
	 * @param dados
	 *            Descrição do parâmetro
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
	 *            Descrição do parâmetro
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
	public String getFuncionalidadeTabelaAuxManter() {
		return Funcionalidade.TABELA_AUXILIAR_FAIXA_MANTER
				+ funcionalidadeTabelaAux;
	}

	/**
	 * Retorna o valor de funcionalidadeTabelaAuxManter
	 * 
	 * @return O valor de funcionalidadeTabelaAuxManter
	 */
	public String getFuncionalidadeTabelaFaixaRealFiltrar() {
		return Funcionalidade.TABELA_AUXILIAR_FAIXA_REAL_FILTRAR
				+ funcionalidadeTabelaAux;
	}

	/**
	 * Retorna o valor de funcionalidadeTabelaAuxManter
	 * 
	 * @return O valor de funcionalidadeTabelaAuxManter
	 */
	public String getFuncionalidadeTabelaFaixaRealInserir() {
		return Funcionalidade.TABELA_AUXILIAR_FAIXA_REAL_INSERIR
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
