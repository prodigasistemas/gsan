package gcom.gui.util.tabelaauxiliar.indicador;

import gcom.cadastro.localidade.QuadraPerfil;
import gcom.gui.Funcionalidade;
import gcom.gui.util.tabelaauxiliar.DadosTelaTabelaAuxiliar;
import gcom.util.SistemaException;
import gcom.util.tabelaauxiliar.TabelaAuxiliarAbstrata;
import gcom.util.tabelaauxiliar.indicador.TabelaAuxiliarIndicador;

import java.util.HashMap;

/**
 * @author Rômulo Aurélio
 *
 */
public class DadosTelaTabelaAuxiliarIndicador extends DadosTelaTabelaAuxiliar {

	private String titulo;

	private TabelaAuxiliarAbstrata tabela;

	private String funcionalidadeTabelaAux;

	// private int tamanhoMaximoCampo;

	private String nomeParametroFuncionalidade;

	private static HashMap<String,DadosTelaTabelaAuxiliar> telas = new HashMap<String,DadosTelaTabelaAuxiliar>();

	private static HashMap<String,String[]> configuracaoParametrosTelas = new HashMap<String,String[]>();

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
	protected DadosTelaTabelaAuxiliarIndicador(String titulo,
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
				DadosTelaTabelaAuxiliarIndicador dadosTela = new DadosTelaTabelaAuxiliarIndicador(
						configuracaoTela[1], (TabelaAuxiliarIndicador) Class
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

		configuracaoParametrosTelas.put("quadraPerfil",
			new String[] { 
				QuadraPerfil.class.getName(), 
				"Perfil Quadra",
				Funcionalidade.TELA_QUADRA_PERFIL });
		
	}

	/**
	 * @return Returns the nomeParametroFuncionalidade.
	 */
	public String getNomeParametroFuncionalidade() {
		return nomeParametroFuncionalidade;
	}

	/**
	 * @param nomeParametroFuncionalidade The nomeParametroFuncionalidade to set.
	 */
	public void setNomeParametroFuncionalidade(
			String nomeParametroFuncionalidade) {
		this.nomeParametroFuncionalidade = nomeParametroFuncionalidade;
	}

	/**
	 * @return Returns the tabela.
	 */
	public TabelaAuxiliarAbstrata getTabela() {
		return tabela;
	}

	/**
	 * @param tabela The tabela to set.
	 */
	public void setTabela(TabelaAuxiliarAbstrata tabela) {
		this.tabela = tabela;
	}

	/**
	 * @return Returns the titulo.
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo The titulo to set.
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return Returns the funcionalidadeTabelaAux.
	 */
	public String getFuncionalidadeTabelaAux() {
		return funcionalidadeTabelaAux;
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
	 * @param funcionalidadeTabelaAux The funcionalidadeTabelaAux to set.
	 */
	public void setFuncionalidadeTabelaAux(String funcionalidadeTabelaAux) {
		this.funcionalidadeTabelaAux = funcionalidadeTabelaAux;
	}
	
	/**
	 * Retorna o valor de funcionalidadeTabelaAuxManter
	 * 
	 * @return O valor de funcionalidadeTabelaAuxManter
	 */
	public String getFuncionalidadeTabelaIndicadorManter() {
		return Funcionalidade.TABELA_AUXILIAR_INDICADOR_MANTER
				+ funcionalidadeTabelaAux;
	}

	/**
	 * Retorna o valor de funcionalidadeTabelaAuxManter
	 * 
	 * @return O valor de funcionalidadeTabelaAuxManter
	 */
	public String getFuncionalidadeTabelaIndicadorFiltrar() {
		return Funcionalidade.TABELA_AUXILIAR_INDICADOR_FILTRAR
				+ funcionalidadeTabelaAux;
	}

	/**
	 * Retorna o valor de funcionalidadeTabelaAuxManter
	 * 
	 * @return O valor de funcionalidadeTabelaAuxManter
	 */
	public String getFuncionalidadeTabelaIndicadorInserir() {
		return Funcionalidade.TABELA_AUXILIAR_INDICADOR_INSERIR
				+ funcionalidadeTabelaAux;
	}

}
