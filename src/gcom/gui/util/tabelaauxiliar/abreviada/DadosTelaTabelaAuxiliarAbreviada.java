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
package gcom.gui.util.tabelaauxiliar.abreviada;

import gcom.arrecadacao.banco.Banco;
import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.ordemservico.EquipamentosEspeciais;
import gcom.cadastro.cliente.OrgaoExpedidorRg;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.endereco.LogradouroTitulo;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.imovel.Despejo;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.localidade.Zeis;
import gcom.gui.Funcionalidade;
import gcom.gui.util.tabelaauxiliar.DadosTelaTabelaAuxiliar;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gcom.operacional.FonteCaptacao;
import gcom.operacional.SistemaAbastecimento;
import gcom.seguranca.transacao.Tabela;
import gcom.util.HibernateUtil;
import gcom.util.SistemaException;
import gcom.util.tabelaauxiliar.TabelaAuxiliar;
import gcom.util.tabelaauxiliar.abreviada.TabelaAuxiliarAbreviada;

import java.util.HashMap;

/**
 * Title: GCOM
 * Description: Sistema de Gestão Comercial
 * Copyright: Copyright (c) 2004
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * @version 1.0
 */

public class DadosTelaTabelaAuxiliarAbreviada extends DadosTelaTabelaAuxiliar {
	
	private static HashMap telas = new HashMap();
	private static HashMap configuracaoParametrosTelas = new HashMap();

	/**
	 * Este método busca um map de funcionalidades cadastradas e cria uma
	 * instância da funcionalidade para ser usada na Tabela Auxiliar
	 * 
	 * @param nome
	 * @return
	 */
	public static DadosTelaTabelaAuxiliarAbreviada obterDadosTelaTabelaAuxiliar(String nome) {
		
		System.out.println("-----------Adicionando itens");
		// Verifica se a funcionalidade desejada já foi instanciada e já está no
		// cache
		if (!telas.containsKey(nome)) {
			String[] configuracaoTela = (String[]) configuracaoParametrosTelas.get(nome);

			try {
				// Cria a instância do objeto DadosTelaTabelaAuxiliar
				DadosTelaTabelaAuxiliarAbreviada dadosTela = 
					new DadosTelaTabelaAuxiliarAbreviada(configuracaoTela[1], 
						(TabelaAuxiliarAbreviada) Class.forName(configuracaoTela[0]).newInstance(),
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
			return (DadosTelaTabelaAuxiliarAbreviada) telas.get(nome);
		}
	}
	

	static {
			
				
 		configuracaoParametrosTelas.put("hidrometroLocalArmazenagem",
			new String[] { 
 				HidrometroLocalArmazenagem.class.getName(),
				"Local de Armazenagem do Hidrômetro",
				Funcionalidade.TELA_HIDROMETRO_LOCAL_ARMAZENAGEM });

		configuracaoParametrosTelas.put("tipoPavimentoRua", 
			new String[] {
				PavimentoRua.class.getName(), 
				"Tipo Pavimento Rua",
				Funcionalidade.TELA_TIPO_PAVIMENTO_RUA });

		configuracaoParametrosTelas.put("zeis",
			new String[] { 
				Zeis.class.getName(),
				"Zeis",
				Funcionalidade.TELA_ZEIS });
		
		configuracaoParametrosTelas.put("sistemaAbastecimento",
			new String[] { 
				SistemaAbastecimento.class.getName(),
				"Sistema de Abastecimento",
				Funcionalidade.TELA_SISTEMA_ABASTECIMENTO });
		
		configuracaoParametrosTelas.put("despejo",
			new String[] { 
				Despejo.class.getName(),
				"Despejo",
				Funcionalidade.TELA_DESPEJO });
		
		configuracaoParametrosTelas.put("orgaoExpeditorRg",
			new String[] { 
				OrgaoExpedidorRg.class.getName(),
				"Órgao Expeditor do RG",
				Funcionalidade.TELA_ORGAO_EXPEDITOR_RG });
		
		configuracaoParametrosTelas.put("unidadeFederacao",
			new String[] { 
				UnidadeFederacao.class.getName(),
				"Unidade da Federação",
				Funcionalidade.TELA_UNIDADE_FEDERACAO });
		
		configuracaoParametrosTelas.put("equipamentosEspeciais",
			new String[] { 
				EquipamentosEspeciais.class.getName(),
				"Equipamento Especial",
				Funcionalidade.TELA_EQUIPAMENTOS_ESPECIAIS});
		
		configuracaoParametrosTelas.put("banco",
			new String[] { 
				Banco.class.getName(),
				"Banco",
				Funcionalidade.TELA_BANCO});

		configuracaoParametrosTelas.put("tabela", 
			new String[] { 
				Tabela.class.getName(), 
				"Tabela",
				Funcionalidade.TELA_TABELA });				
	
		configuracaoParametrosTelas.put("sistemaAbastecimento", 
			new String[] { 
				SistemaAbastecimento.class.getName(), 
				"Sistema de Abastecimento",
				Funcionalidade.TELA_SISTEMA_ABASTECIMENTO });	
	
		configuracaoParametrosTelas.put("ligacaoOrigem", 
			new String[] { 
				LigacaoOrigem.class.getName(), 
				"Origem da Ligação",
				Funcionalidade.TELA_LIGACAO_ORIGEM });

		configuracaoParametrosTelas.put("logradouroTipo", 
			new String[] {
				LogradouroTipo.class.getName(), 
				"Tipo do Logradouro",
				Funcionalidade.TELA_LOGRADOURO_TIPO });

		configuracaoParametrosTelas.put("tituloLogradouro", 
			new String[] {
				LogradouroTitulo.class.getName(), 
				"Titulo do Logradouro",
				Funcionalidade.TELA_LOGRADOURO_TITULO });
		
		configuracaoParametrosTelas.put("fonteCaptacao", 
			new String[] {
				FonteCaptacao.class.getName(), 
				"Fonte de Captação",
				Funcionalidade.TELA_FONTE_CAPTACAO });
		
		
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
	protected DadosTelaTabelaAuxiliarAbreviada(String titulo,
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
				Funcionalidade.TABELA_AUXILIAR_ABREVIADA_MANTER);
	}
	
	/**
	 * Método sobrescrito que retorna o valor de funcionalidadeTabelaAuxManter
	 * 
	 * @return O valor de funcionalidadeTabelaAuxManter
	 */
	public String getFuncionalidadeTabelaAuxFiltrar() {
		return super.getFuncionalidadeTabelaAuxFiltrar().replaceAll(
				Funcionalidade.TABELA_AUXILIAR_FILTRAR,
				Funcionalidade.TABELA_AUXILIAR_ABREVIADA_FILTRAR);
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
	
	/**
	 * Retorna o valor de tabelaAuxiliar
	 * 
	 * @return O valor de tabelaAuxiliar
	 */
	public TabelaAuxiliar getTabela() {
		return  super.getTabelaAuxiliar();
	}
}