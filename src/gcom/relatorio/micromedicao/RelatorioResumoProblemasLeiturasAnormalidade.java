package gcom.relatorio.micromedicao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;

public class RelatorioResumoProblemasLeiturasAnormalidade extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	
	public RelatorioResumoProblemasLeiturasAnormalidade(Usuario usuario) {
		super(usuario, "");
		
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	@Override
	public Object executar() throws TarefaException {
		Fachada fachada = Fachada.getInstancia();

		// Parâmetros do relatório
		Map parametros = new HashMap();

		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();
		
		String grupoFaturamento = this.getParametro("grupo").toString();
		String codigoEmpresa = this.getParametro("codigoEmpresa").toString();
		String empresa = this.getParametro("empresa").toString();
		String localidade = this.getParametro("localidade").toString();
		String erro = this.getParametro("erro").toString();
		String setor = this.getParametro("setor").toString();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("grupo", grupoFaturamento.toString());
		parametros.put("codigoEmpresa", codigoEmpresa.toString());
		parametros.put("empresa", empresa);		
		parametros.put("localidade", localidade);
		
		
		Collection<RelatorioResumoProblemasLeiturasAnormalidadeBean> colecaoBean = 
			new ArrayList<RelatorioResumoProblemasLeiturasAnormalidadeBean>();		

		colecaoBean.add(new RelatorioResumoProblemasLeiturasAnormalidadeBean(erro));
		colecaoBean.add(new RelatorioResumoProblemasLeiturasAnormalidadeBean("Arquivo precessado com problema e enviado para a operação para processar o movimento."));
		colecaoBean.add(new RelatorioResumoProblemasLeiturasAnormalidadeBean("Setor Comercial: "+setor));
		
		
		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		// Gerar o Data Source do Relatório.
		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);
		
		return this.gerarRelatorio(ConstantesRelatorios.RELATORIO_RESUMO_PROBLEMAS_LEITURAS_ANORMALIDADES_CELULAR,parametros,ds,TarefaRelatorio.TIPO_PDF);
			
		
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioResumoProblemasLeiturasAnormalidade", this);
		
	}

}
