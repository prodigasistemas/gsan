package gcom.relatorio.micromedicao.hidrometro;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.micromedicao.hidrometro.FiltroHidrometroMotivoMovimentacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroMovimentacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroMovimentado;
import gcom.micromedicao.hidrometro.HidrometroMotivoMovimentacao;
import gcom.micromedicao.hidrometro.HidrometroMovimentacao;
import gcom.micromedicao.hidrometro.HidrometroMovimentado;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*
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
 * Rômulo Aurélio de Melo Souza Filho
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

/**
 * classe responsável por criar o relatório de movimentacao de hidrometro
 * 
 * @author Rômulo Aurélio de Melo
 * @created 01/09/2008
 */
public class RelatorioMovimentacaoHidrometro extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioMovimentacaoHidrometro(Usuario usuario) {
		super(
				usuario,
				ConstantesRelatorios.RELATORIO_CONSULTAR_MOVIMENTACAO_HIDROMETRO);
	}

	@Deprecated
	public RelatorioMovimentacaoHidrometro() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */
	public Object executar() throws TarefaException {

		FiltroHidrometroMovimentacao filtroHidrometroMovimentacao = (FiltroHidrometroMovimentacao) getParametro("filtroHidrometroMovimentacao");
		HidrometroMovimentacao hidrometroMovimentacaoParametros = (HidrometroMovimentacao) getParametro("hidrometroMovimentacaoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioMovimentacaoHidrometroBean relatorioBean = null;

		//filtroHidrometroMovimentacao.setConsultaSemLimites(true);
		
		Collection colecaoHidrometroMovimentacao = null;
		
		//Caso a pesquisa tenha sido feita atraves dos valores Fixo, Faixa Inicial e Faixa Final
		if (hidrometroMovimentacaoParametros.getFixo() != null && 
	        	!hidrometroMovimentacaoParametros.getFixo().equals("")) {
			
			String faixaInicial = hidrometroMovimentacaoParametros.getFixo() + hidrometroMovimentacaoParametros.getFaixaInicial();
			String faixaFinal = hidrometroMovimentacaoParametros.getFixo() + hidrometroMovimentacaoParametros.getFaixaFinal();
			colecaoHidrometroMovimentacao = fachada.pesquisarNumeroHidrometroMovimentacaoPorFaixa(faixaInicial, faixaFinal);
			
		}else{
			filtroHidrometroMovimentacao
			.adicionarCaminhoParaCarregamentoEntidade("hidrometroMotivoMovimentacao");
			filtroHidrometroMovimentacao
			.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalArmazenagemOrigem");
			filtroHidrometroMovimentacao
			.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalArmazenagemDestino");
			
			colecaoHidrometroMovimentacao = fachada.pesquisar(
					filtroHidrometroMovimentacao, HidrometroMovimentacao.class
					.getName());
			
		}
		
		Collection colecaoHidrometroMovimentado = null;
		
		// se a coleção de parâmetros da analise não for vazia
		if (colecaoHidrometroMovimentacao != null
				&& !colecaoHidrometroMovimentacao.isEmpty()) {
			
			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoHidrometroMovimentacaoIterator = colecaoHidrometroMovimentacao
			.iterator();
			
			// laço para criar a coleção de parâmetros da analise
			while (colecaoHidrometroMovimentacaoIterator.hasNext()) {
				
				HidrometroMovimentacao hidrometroMovimentacao = (HidrometroMovimentacao) colecaoHidrometroMovimentacaoIterator
				.next();
				
				// Faz as validações dos campos necessáriose e formata a String
				// para a forma como irá aparecer no relatório
				
				// Local Armagenagem Origem
				String localArmazenagemOrigem = "";
				
				if (hidrometroMovimentacao
						.getHidrometroLocalArmazenagemOrigem() != null) {
					localArmazenagemOrigem = hidrometroMovimentacao
					.getHidrometroLocalArmazenagemOrigem()
					.getDescricao();
				}
				
				// Local Armagenagem Destino
				String localArmazenagemDestino = "";
				
				if (hidrometroMovimentacao
						.getHidrometroLocalArmazenagemDestino() != null) {
					localArmazenagemDestino = hidrometroMovimentacao
					.getHidrometroLocalArmazenagemDestino()
					.getDescricao();
				}
				
				// Motivo Movimentacao
				String motivoMovimentacao = "";
				
				if (hidrometroMovimentacao.getHidrometroMotivoMovimentacao() != null) {
					motivoMovimentacao = hidrometroMovimentacao
					.getHidrometroMotivoMovimentacao().getDescricao();
				}
				
				// Data
				
				String data = "";
				
				if (hidrometroMovimentacao.getData() != null) {
					data = Util.formatarData(hidrometroMovimentacao.getData());
				}
				
				String hora = "";
				
				if (hidrometroMovimentacao.getHora() != null) {
					hora = hidrometroMovimentacao.getHora().toString();
				}
				
				FiltroHidrometroMovimentado filtroHidrometroMovimentado = new FiltroHidrometroMovimentado();
				
				filtroHidrometroMovimentado
				.adicionarCaminhoParaCarregamentoEntidade("hidrometro");
				
				filtroHidrometroMovimentado
				.adicionarParametro(new ParametroSimples(
						FiltroHidrometroMovimentado.HIDROMETRO_MOVIMENTACAO_ID,
						hidrometroMovimentacao.getId()));
				
				//filtroHidrometroMovimentado.setConsultaSemLimites(true);
				
				colecaoHidrometroMovimentado = fachada.pesquisar(
						filtroHidrometroMovimentado,
						HidrometroMovimentado.class.getName());
				
				Integer quantidade = colecaoHidrometroMovimentado.size();
				
				hidrometroMovimentacao.setQuantidade(quantidade.toString());
				
				relatorioBean = new RelatorioMovimentacaoHidrometroBean(
						
						// Local Armagenagem Origem
						localArmazenagemOrigem,
						
						// Motivo Movimentacao
						motivoMovimentacao,
						
						// Data
						data,
						
						// Local Armagenagem Destino
						localArmazenagemDestino,
						
						// hora
						hora,
						
						// hidrometros
						hidrometroMovimentacao.getQuantidade());
				
				/** [MA2011061014]
				 * 	Autor: Paulo Diniz
				 *  Data: 12/07/2011
				 *  InclusÃ£o do Número Inicial e Final do Hidrômetro e Fixos
				 * */
				//Calculando a FaixaInicial, FaixaFinal e Caracteres Fixos
				String faixaInicial = ((HidrometroMovimentado) ((List) colecaoHidrometroMovimentado).get(0)).getHidrometro().getNumero();
				String faixaFinal = ((HidrometroMovimentado) ((List) colecaoHidrometroMovimentado).get(quantidade-1)).getHidrometro().getNumero();
				String fixo = "";
				if(faixaInicial != null)
				{
					Integer tamanhoFaixaInicial = faixaInicial.length();
					if(tamanhoFaixaInicial > 4)
					{
						fixo = faixaInicial.substring(0,4);
						hidrometroMovimentacao.setFixo(fixo);
					}
					faixaInicial = faixaInicial.substring(4,tamanhoFaixaInicial-1);
				}
				if(faixaFinal != null)
				{
					Integer tamanhoFaixaFinal = faixaFinal.length();
					faixaFinal = faixaFinal.substring(4,tamanhoFaixaFinal-1);
				}
				
				String descricaoHidrometros = fixo+" - "+ faixaInicial+ " / " + faixaFinal;
				relatorioBean.setDescricaoHidrometros(descricaoHidrometros);
				
				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}

		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		if (hidrometroMovimentacaoParametros
				.getHidrometroLocalArmazenagemOrigem() != null) {
			parametros.put("localArmazenagemOrigem",
					hidrometroMovimentacaoParametros
							.getHidrometroLocalArmazenagemOrigem()
							.getDescricao());
		}
		if (hidrometroMovimentacaoParametros
				.getHidrometroLocalArmazenagemDestino() != null) {
			parametros.put("localArmazenagemDestino",
					hidrometroMovimentacaoParametros
							.getHidrometroLocalArmazenagemDestino()
							.getDescricao());
		}
		if (hidrometroMovimentacaoParametros.getHidrometroMotivoMovimentacao() != null) {

			FiltroHidrometroMotivoMovimentacao filtroHidrometroMotivoMovimentacao = new FiltroHidrometroMotivoMovimentacao();

			filtroHidrometroMotivoMovimentacao
					.adicionarParametro(new ParametroSimples(
							FiltroHidrometroMotivoMovimentacao.ID,
							hidrometroMovimentacaoParametros
									.getHidrometroMotivoMovimentacao().getId()));

			Collection colecaoHidrometroMotivoMovimentacao = fachada.pesquisar(
					filtroHidrometroMotivoMovimentacao,
					HidrometroMotivoMovimentacao.class.getName());
			HidrometroMotivoMovimentacao hidrometroMotivoMovimentacao = (HidrometroMotivoMovimentacao) colecaoHidrometroMotivoMovimentacao
					.iterator().next();

			parametros.put("motivoMovimentacao", hidrometroMotivoMovimentacao
					.getDescricao());
		}
		if (hidrometroMovimentacaoParametros.getUsuario() != null) {

			parametros.put("usuario", hidrometroMovimentacaoParametros
					.getUsuario().getNomeUsuario());
		}

		if (hidrometroMovimentacaoParametros.getHoraMovimentacaoInicial() != null) {

			parametros.put("horaMovimentacaoInicial",
					hidrometroMovimentacaoParametros
							.getHoraMovimentacaoInicial());

		} else {
			parametros.put("horaMovimentacaoInicial", "");
		}

		if (hidrometroMovimentacaoParametros.getHoraMovimentacaoFinal() != null) {

			parametros
					.put("horaMovimentacaoFinal",
							hidrometroMovimentacaoParametros
									.getHoraMovimentacaoFinal());

		} else {
			parametros.put("horaMovimentacaoFinal", "");
		}

		if (hidrometroMovimentacaoParametros.getDataMovimentacaoInicial() != null) {

			parametros.put("dataMovimentacaoInicial",
					hidrometroMovimentacaoParametros
							.getDataMovimentacaoInicial());

		} else {
			parametros.put("dataMovimentacaoInicial", "");
		}

		if (hidrometroMovimentacaoParametros.getDataMovimentacaoFinal() != null) {

			parametros
					.put("dataMovimentacaoFinal",
							hidrometroMovimentacaoParametros
									.getDataMovimentacaoFinal());

		} else {
			parametros.put("dataMovimentacaoFinal", "");
		}
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_CONSULTAR_MOVIMENTACAO_HIDROMETRO,
				parametros, ds, tipoFormatoRelatorio);

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

//		retorno = Fachada
//				.getInstancia()
//				.totalRegistrosPesquisa(
//						(FiltroHidrometroMovimentacao) getParametro("filtroHidrometroMovimentacao"),
//						HidrometroMovimentacao.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioMovimentacaoHidrometro", this);
	}

}
