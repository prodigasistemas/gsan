/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
 *
 * This file is part of GSAN, an integrated service management system for Sanitation
 *
 * GSAN is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License.
 *
 * GSAN is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
 */

/*
 * GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
 * Copyright (C) <2007> 
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Araújo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Cláudio de Andrade Lira
 * Denys Guimarães Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fabíola Gomes de Araújo
 * Flávio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento Júnior
 * Homero Sampaio Cavalcanti
 * Ivan Sérgio da Silva Júnior
 * José Edmar de Siqueira
 * José Thiago Tenório Lopes
 * Kássia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * Márcio Roberto Batista da Silva
 * Maria de Fátima Sampaio Leite
 * Micaela Maria Coelho de Araújo
 * Nelson Mendonça de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corrêa Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Araújo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * Rosana Carvalho Barbosa
 * Sávio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa é software livre; você pode redistribuí-lo e/ou
 * modificá-lo sob os termos de Licença Pública Geral GNU, conforme
 * publicada pela Free Software Foundation; versão 2 da
 * Licença.
 * Este programa é distribuído na expectativa de ser útil, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia implícita de
 * COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
 * PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
 * detalhes.
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU
 * junto com este programa; se não, escreva para Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307, USA.
 */
package gcom.gui.util.tabelaauxiliar;

import gcom.atendimentopublico.ligacaoagua.CorteTipo;
import gcom.atendimentopublico.ligacaoagua.EmissaoOrdemCobrancaTipo;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaDiametro;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil;
import gcom.atendimentopublico.ligacaoagua.SupressaoTipo;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoCaixaInspecao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoAguasPluviais;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoDejetos;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial;
import gcom.cadastro.cliente.PessoaSexo;
import gcom.cadastro.dadocensitario.FonteDadosCensitario;
import gcom.cadastro.dadocensitario.IbgeSetorCensitario;
import gcom.cadastro.endereco.CepTipo;
import gcom.cadastro.endereco.EnderecoTipo;
import gcom.cadastro.geografico.RegiaoDesenvolvimento;
import gcom.cadastro.imovel.EloAnormalidade;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelTipoCobertura;
import gcom.cadastro.imovel.ImovelTipoConstrucao;
import gcom.cadastro.imovel.ImovelTipoHabitacao;
import gcom.cadastro.imovel.ImovelTipoPropriedade;
import gcom.cadastro.imovel.PocoTipo;
import gcom.cadastro.localidade.LocalidadeClasse;
import gcom.cadastro.localidade.LocalidadePorte;
import gcom.cadastro.tarifasocial.RendaTipo;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.gui.Funcionalidade;
import gcom.micromedicao.consumo.ConsumoTipo;
import gcom.micromedicao.hidrometro.HidrometroRelojoaria;
import gcom.operacional.SistemaEsgotoTratamentoTipo;
import gcom.operacional.TipoCaptacao;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.util.HibernateUtil;
import gcom.util.SistemaException;
import gcom.util.tabelaauxiliar.TabelaAuxiliar;
import gcom.util.tabelaauxiliar.TabelaAuxiliarAbstrata;
import gcom.util.tabelaauxiliar.faixa.TabelaAuxiliarFaixaReal;

import java.util.HashMap;

/**
 * Title: GCOM
 * Description: Sistema de Gestão Comercial
 * Copyright: Copyright (c) 2004
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * @author not attributable
 * @version 1.0
 */

public class DadosTelaTabelaAuxiliar {
	private String titulo;

	private TabelaAuxiliar tabela;

	private String funcionalidadeTabelaAux;
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

		// Verifica se a funcionalidade desejada já foi instanciada e já está no
		// cache
		if (!telas.containsKey(nome)) {

			String[] configuracaoTela = configuracaoParametrosTelas.get(nome);

			try {

				// Cria a instância do objeto DadosTelaTabelaAuxiliar
				DadosTelaTabelaAuxiliar dadosTela = new DadosTelaTabelaAuxiliar(
						configuracaoTela[1], (TabelaAuxiliar) Class.forName(
								configuracaoTela[0]).newInstance(),
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

		//Arrumar
		configuracaoParametrosTelas.put("perfilImovel", new String[] {
				ImovelPerfil.class.getName(), "Perfil do Imóvel",
				Funcionalidade.TELA_IMOVEL_PERFIL });
		
		//Arrumar
		configuracaoParametrosTelas.put("ibgeSetorCensitario", new String[] {
				IbgeSetorCensitario.class.getName(), "IBGE Setor Censitário",
				Funcionalidade.TELA_IBGE_SETOR_CENSITARIO });	
		
		//Arrumar
		configuracaoParametrosTelas.put("tipoCorte", new String[] {
				CorteTipo.class.getName(), "Tipo de Corte",
				Funcionalidade.TELA_CORTE_TIPO });
		
		//Arrumar
		configuracaoParametrosTelas.put("tipoSupressao", new String[] {
				SupressaoTipo.class.getName(), "Tipo de Supressao",
				Funcionalidade.TELA_SUPRESSAO_TIPO });
		
		//Arrumar
		configuracaoParametrosTelas.put("tipoConsumo", new String[] {
				ConsumoTipo.class.getName(), "Tipo de Consumo",
				Funcionalidade.TELA_CONSUMO_TIPO });
		
		configuracaoParametrosTelas.put("localidadeClasse", 
			new String[] {
				LocalidadeClasse.class.getName(), 
				"Localidade Classe",
				Funcionalidade.TELA_LOCALIDADE_CLASSE });

		configuracaoParametrosTelas.put("localidadePorte", 
			new String[] {
				LocalidadePorte.class.getName(), 
				"Localidade Porte",
				Funcionalidade.TELA_LOCALIDADE_PORTE });

		configuracaoParametrosTelas.put("fonteDadosCensitario",
			new String[] { 
				FonteDadosCensitario.class.getName(),
				"Fonte Dados Censitários",
				Funcionalidade.TELA_SETOR_CENSITARIO });

		configuracaoParametrosTelas.put("cepTipo", 
			new String[] {
				CepTipo.class.getName(), 
				"Tipo de CEP",
				Funcionalidade.TELA_CEP_TIPO });

		configuracaoParametrosTelas.put("tipoEndereco", 
			new String[] {
				EnderecoTipo.class.getName(), 
				"Tipo do Endereço",
				Funcionalidade.TELA_ENDERECO_TIPO });

		configuracaoParametrosTelas.put("pessoaSexo", 
			new String[] {
				PessoaSexo.class.getName(), 
				"Pessoa do Sexo",
				Funcionalidade.TELA_PESSOA_SEXO });

		configuracaoParametrosTelas.put("tipoRenda", 
			new String[] {
				RendaTipo.class.getName(), 
				"Tipo da Renda",
				Funcionalidade.TELA_RENDA_TIPO });

		configuracaoParametrosTelas.put("acaoUsuario", 
			new String[] {
				UsuarioAcao.class.getName(), 
				"Ação do Usuário",
				Funcionalidade.TELA_USUARIO_ACAO });

		configuracaoParametrosTelas.put("materialLigacaoEsgoto", 
			new String[] {
				LigacaoEsgotoMaterial.class.getName(),
				"Material de Ligação do Esgoto",
				Funcionalidade.TELA_LIGACAO_ESGOTO_MATERIAL });

		configuracaoParametrosTelas.put("diametroLigacaoEsgoto", 
			new String[] {
				LigacaoEsgotoDiametro.class.getName(),
				"Diametro de Ligação do Esgoto",
				Funcionalidade.TELA_LIGACAO_ESGOTO_DIAMETRO });

		configuracaoParametrosTelas.put("diametroLigacaoAgua", 
			new String[] {
				LigacaoAguaDiametro.class.getName(),
				"Diametro de Ligação da Água",
				Funcionalidade.TELA_LIGACAO_AGUA_DIAMETRO });

		configuracaoParametrosTelas.put("tipoOrdemEmissaoCobranca",
			new String[] { 
				EmissaoOrdemCobrancaTipo.class.getName(),
				"Tipo da Ordem de Emissão de Cobranca",
				Funcionalidade.TELA_EMISSAO_ORDEM_COBRANCA_TIPO });

		configuracaoParametrosTelas.put("perfilLigacaoAgua", 
			new String[] {
				LigacaoAguaPerfil.class.getName(), 
				"Perfil de Ligação da Água",
				Funcionalidade.TELA_LIGACAO_AGUA_PERFIL });

		configuracaoParametrosTelas.put("poco", 
			new String[] {
				PocoTipo.class.getName(), 
				"Poço", 
				Funcionalidade.TELA_POCO });

		configuracaoParametrosTelas.put("eloAnormalidade", 
			new String[] {
				EloAnormalidade.class.getName(), 
				"Elo Anormalidade",
				Funcionalidade.TELA_ELO_ANORMALIDADE });
		
		configuracaoParametrosTelas.put("imovelTipoHabitacao", 
			new String[] {
				ImovelTipoHabitacao.class.getName(), 
				"Tipo de Habitação",
				Funcionalidade.TELA_TIPO_HABITACAO });

		configuracaoParametrosTelas.put("imovelTipoPropriedade", 
			new String[] {
				ImovelTipoPropriedade.class.getName(), 
				"Tipo de Propriedade",
				Funcionalidade.TELA_TIPO_PROPRIEDADE });
		
		configuracaoParametrosTelas.put("imovelTipoConstrucao", 
			new String[] {
				ImovelTipoConstrucao.class.getName(), 
				"Tipo de Construção",
				Funcionalidade.TELA_TIPO_CONSTRUCAO });
		
		configuracaoParametrosTelas.put("imovelTipoCobertura", 
			new String[] {
				ImovelTipoCobertura.class.getName(), 
				"Tipo de Cobertura",
				Funcionalidade.TELA_TIPO_COBERTURA });
		
		configuracaoParametrosTelas.put("hidrometroRelojoaria", 
			new String[] {
				HidrometroRelojoaria.class.getName(), 
				"Hidrometro Relojoaria",
				Funcionalidade.TELA_HIDROMETRO_RELOJOARIA });

		configuracaoParametrosTelas.put("ligacaoEsgotoDestinoDejetos", 
			new String[] {
				LigacaoEsgotoDestinoDejetos.class.getName(), 
				"Ligação de Esgoto destino de dejetos",
				Funcionalidade.TELA_LIGACAO_ESGOTO_DESTINO_DEJETOS });
		
		configuracaoParametrosTelas.put("ligacaoEsgotoEsgotamento", 
			new String[] {
				LigacaoEsgotoEsgotamento.class.getName(), 
				"Ligação de Esgoto Esgotamento",
				Funcionalidade.TELA_LIGACAO_ESGOTO_ESGOTAMENTO });
		
		configuracaoParametrosTelas.put("ligacaoEsgotoCaixaInspecao", 
			new String[] {
				LigacaoEsgotoCaixaInspecao.class.getName(), 
				"Ligação de Esgoto Caixa de Inspeção",
				Funcionalidade.TELA_LIGACAO_ESGOTO_CAIXA_INSPECAO });
		
		configuracaoParametrosTelas.put("ligacaoEsgotoDestinoAguasPluviais", 
			new String[] {
				LigacaoEsgotoDestinoAguasPluviais.class.getName(), 
				"Ligação de Esgoto Destino das Águas Pluviais",
				Funcionalidade.TELA_LIGACAO_ESGOTO_DESTINO_AGUAS_PLUVIAIS });
		
		configuracaoParametrosTelas.put("regiaoIntegracao", 
			new String[] {
				RegiaoDesenvolvimento.class.getName(), 
				"Região de Integração",
				Funcionalidade.TELA_REGIAO_INTEGRACAO});
		
		
		configuracaoParametrosTelas.put("sistemaEsgotoTratamentoTipo", 
			new String[] {
				SistemaEsgotoTratamentoTipo.class.getName(), 
				"Tipo de Tratamento do Sistema de Esgoto",
				Funcionalidade.TELA_ESGOTO_TRATAMENTO_TIPO});
		
		configuracaoParametrosTelas.put("tipoCaptacao", 
				new String[] {
					TipoCaptacao.class.getName(), 
					"Tipo de Captação",
					Funcionalidade.TELA_TIPO_CAPTACAO });
		
		configuracaoParametrosTelas.put("contaMotivoRetificacao", 
				new String[] {
				ContaMotivoRetificacao.class.getName(), 
					"Motivo de Retificação da Conta",
					Funcionalidade.TELA_CONTA_MOTIVO_RETIFICACAO });
		
	
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
	protected DadosTelaTabelaAuxiliar(String titulo, TabelaAuxiliar tabela,
			String funcionalidadeTabelaAux, String nomeParametroFuncionalidade) {
		this.titulo = titulo;
		this.tabela = tabela;
		this.funcionalidadeTabelaAux = funcionalidadeTabelaAux;
		this.nomeParametroFuncionalidade = nomeParametroFuncionalidade;
		System.out.println("inicializando objeto");
	}

	public DadosTelaTabelaAuxiliar(String titulo2,
			TabelaAuxiliarFaixaReal tabela2, String funcionalidadeTabelaAux2,
			String nomeParametroFuncionalidade2) {
	}

	public DadosTelaTabelaAuxiliar(String nomeParametroFuncionalidade2,
			TabelaAuxiliarAbstrata abstrata,
			String nomeParametroFuncionalidade3,
			String nomeParametroFuncionalidade4) {
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
	public TabelaAuxiliar getTabelaAuxiliar() {
		return tabela;
	}

	/**
	 * Retorna o valor de funcionalidadeTabelaAuxManter
	 * 
	 * @return O valor de funcionalidadeTabelaAuxManter
	 */
	public String getFuncionalidadeTabelaAuxManter() {
		return Funcionalidade.TABELA_AUXILIAR_MANTER + funcionalidadeTabelaAux;
	}

	/**
	 * Retorna o valor de funcionalidadeTabelaAuxManter
	 * 
	 * @return O valor de funcionalidadeTabelaAuxManter
	 */
	public String getFuncionalidadeTabelaAuxFiltrar() {
		return Funcionalidade.TABELA_AUXILIAR_FILTRAR
				+ funcionalidadeTabelaAux;
	}

	/**
	 * Retorna o valor de funcionalidadeTabelaAuxManter
	 * 
	 * @return O valor de funcionalidadeTabelaAuxManter
	 */
	public String getFuncionalidadeTabelaAuxInserir() {
		return Funcionalidade.TABELA_AUXILIAR_INSERIR + funcionalidadeTabelaAux;
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

}
