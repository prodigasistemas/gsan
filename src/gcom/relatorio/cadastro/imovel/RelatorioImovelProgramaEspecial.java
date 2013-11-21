package gcom.relatorio.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelProgramaEspecial;
import gcom.cadastro.imovel.ImovelProgramaEspecial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioImovelProgramaEspecial extends TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RelatorioImovelProgramaEspecial(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEL_PROGRAMA_ESPECIAL);
	}
	
	@Deprecated
	public RelatorioImovelProgramaEspecial() {
		super(null, "");
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioImovelProgramaEspecial", this);
	}

	@Override
	public Object executar() throws TarefaException {
		FiltroImovelProgramaEspecial filtroImovelProgramaEspecial = 
			(FiltroImovelProgramaEspecial) getParametro("filtroImovelProgramaEspecial");

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterImovelProgramaEspecialBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();
		
		filtroImovelProgramaEspecial.setCampoOrderBy(FiltroImovelProgramaEspecial.ID);
		
		Collection colecaoImoveis = fachada.pesquisar(filtroImovelProgramaEspecial,
				ImovelProgramaEspecial.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoImoveis != null && !colecaoImoveis.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator itera = colecaoImoveis.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (itera.hasNext()) {

				ImovelProgramaEspecial imovelProgramaEspecial = (ImovelProgramaEspecial) itera.next();

				relatorioBean = new RelatorioManterImovelProgramaEspecialBean(
						

						imovelProgramaEspecial.getId()!=null ? 
								imovelProgramaEspecial.getId().toString():"",
						
						imovelProgramaEspecial.getDescricaoDocumentos()!=null?
						imovelProgramaEspecial.getDescricaoDocumentos():"",

						imovelProgramaEspecial.getDataApresentacaoDocumentos()!=null?
						Util.formatarData(imovelProgramaEspecial.getDataApresentacaoDocumentos()):"",
						
						imovelProgramaEspecial.getMesAnoInicioPrograma()!=null?
						imovelProgramaEspecial.getMesAnoInicio():"",		
						
						imovelProgramaEspecial.getMesAnoSaidaPrograma()!=null?
						imovelProgramaEspecial.getMesAnoSaida():"",	
						
						imovelProgramaEspecial.getDataInclusao()!=null?
						Util.formatarData(imovelProgramaEspecial.getDataInclusao()):"",
						
						imovelProgramaEspecial.getDataSuspensao()!=null?
						Util.formatarData(imovelProgramaEspecial.getDataSuspensao()):""


				);
				
				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("id", getParametro("id"));
		parametros.put("inscricao", getParametro("inscricao"));

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_IMOVEL_PROGRAMA_ESPECIAL, parametros, ds,
				tipoFormatoRelatorio);

		// retorna o relatório gerado
		return retorno;
	}

}
