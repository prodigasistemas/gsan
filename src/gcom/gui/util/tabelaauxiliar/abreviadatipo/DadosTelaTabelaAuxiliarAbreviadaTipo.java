package gcom.gui.util.tabelaauxiliar.abreviadatipo;

import gcom.gui.Funcionalidade;
import gcom.gui.util.tabelaauxiliar.DadosTelaTabelaAuxiliar;
import gcom.operacional.SetorAbastecimento;
import gcom.operacional.ZonaAbastecimento;
import gcom.util.HibernateUtil;
import gcom.util.SistemaException;
import gcom.util.tabelaauxiliar.TabelaAuxiliar;
import gcom.util.tabelaauxiliar.abreviadatipo.TabelaAuxiliarAbreviadaTipo;

import java.util.HashMap;

/**
 * Title: GCOM
 * Description: Sistema de Gestão Comercial
 * Copyright: Copyright (c) 2004
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * Esse componente serve para SetorAbastecimento e ZonaAbastecimento, sendo o tipo SistemaAbastecimento
 * @author not attributable
 * @version 1.0
 */

public class DadosTelaTabelaAuxiliarAbreviadaTipo extends DadosTelaTabelaAuxiliar {
	
	private static HashMap telas = new HashMap();
	private static HashMap configuracaoParametrosTelas = new HashMap();

	/**
	 * Este método busca um map de funcionalidades cadastradas e cria uma
	 * instância da funcionalidade para ser usada na Tabela Auxiliar
	 * 
	 * @param nome
	 * @return
	 */
	public static DadosTelaTabelaAuxiliarAbreviadaTipo obterDadosTelaTabelaAuxiliar(
			String nome) {
		System.out.println("-----------Adicionando itens");
		// Verifica se a funcionalidade desejada já foi instanciada e já está no
		// cache
		if (!telas.containsKey(nome)) {
			String[] configuracaoTela = (String[]) configuracaoParametrosTelas
					.get(nome);

			try {
				// Cria a instância do objeto DadosTelaTabelaAuxiliar
				DadosTelaTabelaAuxiliarAbreviadaTipo dadosTela = 
					new DadosTelaTabelaAuxiliarAbreviadaTipo(
						configuracaoTela[1], 
						(TabelaAuxiliarAbreviadaTipo) Class.forName(configuracaoTela[0]).newInstance(),
						configuracaoTela[2], 
						nome);
				
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
			return (DadosTelaTabelaAuxiliarAbreviadaTipo) telas.get(nome);
		}
	}
	
	static {
		
		configuracaoParametrosTelas.put("setorAbastecimento", new String[] {
				SetorAbastecimento.class.getName(), "Setor de Abastecimento",
				Funcionalidade.TELA_SETOR_ABASTECIMENTO });		
		
		configuracaoParametrosTelas.put("zonaAbastecimento", new String[] {
				ZonaAbastecimento.class.getName(), "Zona de Abastecimento",
				Funcionalidade.TELA_ZONA_ABASTECIMENTO });		

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
	protected DadosTelaTabelaAuxiliarAbreviadaTipo(String titulo,
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
	public static void adicionarDadosTela(DadosTelaTabelaAuxiliar dados) {
		DadosTelaTabelaAuxiliar.adicionarDadosTela(dados);
	}

	/**
	 * Retorna o valor de dadosTela
	 * 
	 * @param nome
	 *            Descrição do parâmetro
	 * @return O valor de dadosTela
	 */
	public static DadosTelaTabelaAuxiliar getDadosTela(String nome) {
		return DadosTelaTabelaAuxiliar.getDadosTela(nome);
	}

	/**
	 * Método sobrescrito que retorna o valor de funcionalidadeTabelaAuxManter
	 * 
	 * @return O valor de funcionalidadeTabelaAuxManter
	 */
	public String getFuncionalidadeTabelaAuxManter() {
		return super.getFuncionalidadeTabelaAuxManter().replaceAll(
				Funcionalidade.TABELA_AUXILIAR_MANTER,
				Funcionalidade.TABELA_AUXILIAR_ABREVIADA_TIPO_MANTER);
		
	}
	
	/**
	 * Método sobrescrito que retorna o valor de funcionalidadeTabelaAuxManter
	 * 
	 * @return O valor de funcionalidadeTabelaAuxManter
	 */
	public String getFuncionalidadeTabelaAuxFiltrar() {
		return super.getFuncionalidadeTabelaAuxFiltrar().replaceAll(
				Funcionalidade.TABELA_AUXILIAR_FILTRAR,
				Funcionalidade.TABELA_AUXILIAR_ABREVIADA_TIPO_FILTRAR);
	}

	/**
	 * Método sobrescrito que retorna o valor de funcionalidadeTabelaAuxManter
	 * 
	 * @return O valor de funcionalidadeTabelaAuxManter
	 */
	public String getFuncionalidadeTabelaAuxInserir() {
		return super.getFuncionalidadeTabelaAuxInserir().replaceAll(
				Funcionalidade.TABELA_AUXILIAR_INSERIR,
				Funcionalidade.TABELA_AUXILIAR_ABREVIADA_TIPO_INSERIR);
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
	
	/**
	 * Retorna o valor de tabelaAuxiliar
	 * 
	 * @return O valor de tabelaAuxiliar
	 */
	public TabelaAuxiliar getTabela() {
		return  super.getTabelaAuxiliar();
	}
}
