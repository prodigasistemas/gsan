package gcom.gui.util.tabelaauxiliar.unidade;

import gcom.atendimentopublico.ordemservico.Material;
import gcom.gui.Funcionalidade;
import gcom.gui.util.tabelaauxiliar.abreviada.DadosTelaTabelaAuxiliarAbreviada;
import gcom.seguranca.acesso.Operacao;
import gcom.util.HibernateUtil;
import gcom.util.SistemaException;
import gcom.util.tabelaauxiliar.TabelaAuxiliar;
import gcom.util.tabelaauxiliar.unidade.TabelaAuxiliarUnidade;

import java.util.HashMap;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 02/08/2006
 */
public class DadosTelaTabelaAuxiliarUnidade extends
		DadosTelaTabelaAuxiliarAbreviada {

	private static HashMap telas = new HashMap();

	private static HashMap configuracaoParametrosTelas = new HashMap();

	/**
	 * Este método busca um map de funcionalidades cadastradas e cria uma
	 * instância da funcionalidade para ser usada na Tabela Auxiliar
	 * 
	 * @param nome
	 * @return
	 */
	public static DadosTelaTabelaAuxiliarUnidade obterDadosTelaTabelaAuxiliarAbreviada(
			String nome) {
		System.out.println("-----------Adicionando itens");
		// Verifica se a funcionalidade desejada já foi instanciada e já está no
		// cache
		if (!telas.containsKey(nome)) {
			String[] configuracaoTela = (String[]) configuracaoParametrosTelas
					.get(nome);

			try {
				// Cria a instância do objeto DadosTelaTabelaAuxiliar
				DadosTelaTabelaAuxiliarUnidade dadosTela = new DadosTelaTabelaAuxiliarUnidade(
						configuracaoTela[1], (TabelaAuxiliarUnidade) Class
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
		} else {
			// Se o a funcionalidade já estiver no cache, então ela é retornada
			// sem a necessidade de passar pelo método
			return (DadosTelaTabelaAuxiliarUnidade) telas.get(nome);
		}
	}

	static {

		configuracaoParametrosTelas.put("operacao", new String[] {
				Operacao.class.getName(), "Operação",
				Funcionalidade.TELA_OPERACAO });

		configuracaoParametrosTelas.put("material", new String[] {
				Material.class.getName(), "Material",
				Funcionalidade.TELA_MATERIAL });

	}

	/**
	 * Construtor da classe DadosTelaTabelaAuxiliarAbreviada
	 * 
	 * @param titulo
	 *            Descrição do parâmetro
	 * @param tabela
	 *            Descrição do parâmetro
	 * @param funcionalidadeTabelaAux
	 *            Descrição do parâmetro
	 */
	protected DadosTelaTabelaAuxiliarUnidade(String titulo,
			TabelaAuxiliar tabela, String funcionalidadeTabelaAux,
			String nomeParametroFuncionalidade) {
		super(titulo, tabela, funcionalidadeTabelaAux,
				nomeParametroFuncionalidade);
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param dados
	 *            Descrição do parâmetro
	 */
	public static void adicionarDadosTela(DadosTelaTabelaAuxiliarAbreviada dados) {
		DadosTelaTabelaAuxiliarAbreviada.adicionarDadosTela(dados);
	}

	/**
	 * Retorna o valor de dadosTela
	 * 
	 * @param nome
	 *            Descrição do parâmetro
	 * @return O valor de dadosTela
	 */
	public static DadosTelaTabelaAuxiliarAbreviada getDadosTela(String nome) {
		return (DadosTelaTabelaAuxiliarAbreviada) DadosTelaTabelaAuxiliarAbreviada.getDadosTela(nome);
	}

	/**
	 * Método sobrescrito que retorna o valor de funcionalidadeTabelaAuxManter
	 * 
	 * @return O valor de funcionalidadeTabelaAuxManter
	 */
	public String getFuncionalidadeTabelaAuxManter() {
		return super.getFuncionalidadeTabelaAuxManter().replaceAll(
				Funcionalidade.TABELA_AUXILIAR_MANTER,
				Funcionalidade.TABELA_AUXILIAR_ABREVIADA_MANTER);
	}

	/**
	 * Método sobrescrito que retorna o valor de funcionalidadeTabelaAuxManter
	 * 
	 * @return O valor de funcionalidadeTabelaAuxManter
	 */
	public String getFuncionalidadeTabelaAuxInserir() {
		return super.getFuncionalidadeTabelaAuxInserir().replaceAll(
				Funcionalidade.TABELA_AUXILIAR_INSERIR,
				Funcionalidade.TABELA_AUXILIAR_ABREVIADA_INSERIR);
	}

	/**
	 * Retorna o valor de tamanhoMaximoCampo
	 * 
	 * @return O valor de tamanhoMaximoCampo
	 */
	public int getTamanhoMaximoDescricaoAbreviada() {
		return HibernateUtil.getColumnSize(this.getTabelaAuxiliar().getClass(),
				"descricaoAbreviada");
	}

}
