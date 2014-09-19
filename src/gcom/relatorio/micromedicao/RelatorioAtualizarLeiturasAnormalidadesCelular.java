package gcom.relatorio.micromedicao;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrador
 *
 */
public class RelatorioAtualizarLeiturasAnormalidadesCelular extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	private Collection<RelatorioAtualizarLeiturasAnormalidadesCelularBean> leituras;
	
	private Collection<RelatorioAtualizarLeiturasAnormalidadesCelularBean> anormalidades;
	
	private Collection<RelatorioAtualizarLeiturasAnormalidadesCelularBean> mensagem;
	
	
	public RelatorioAtualizarLeiturasAnormalidadesCelular(Usuario usuario,
			Collection<RelatorioAtualizarLeiturasAnormalidadesCelularBean> leituras,
			Collection<RelatorioAtualizarLeiturasAnormalidadesCelularBean> anormalidades,
			Collection<RelatorioAtualizarLeiturasAnormalidadesCelularBean> mensagem) {
				
				super(usuario, ConstantesRelatorios.RELATORIO_ATUALIZAR_LEITURAS_ANORMALIDADES_CELULAR);
				this.anormalidades = anormalidades;
				this.leituras = leituras;
				this.mensagem = mensagem;
		
	}
	
	private Collection<RelatorioAtualizarLeiturasAnormalidadesCelularBean> gerarBeans(){
		Collection<RelatorioAtualizarLeiturasAnormalidadesCelularBean> colecaoBean = 
			new ArrayList<RelatorioAtualizarLeiturasAnormalidadesCelularBean>();
				
				colecaoBean.addAll(this.leituras);
				colecaoBean.addAll(this.anormalidades);
 				colecaoBean.addAll(this.mensagem);
		
		
		return colecaoBean;
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

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("grupo", grupoFaturamento.toString());
		parametros.put("codigoEmpresa", codigoEmpresa.toString());
		parametros.put("empresa", empresa);		
		parametros.put("localidade", localidade);
		
		
		Collection<RelatorioAtualizarLeiturasAnormalidadesCelularBean> colecaoBean = this.gerarBeans();		
		
		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		// Gerar o Data Source do Relatório.
		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);
		
		return this.gerarRelatorio(ConstantesRelatorios.RELATORIO_ATUALIZAR_LEITURAS_ANORMALIDADES_CELULAR,
						parametros,ds,TarefaRelatorio.TIPO_PDF);		
		
	}

	@Override
	public void agendarTarefaBatch() {
		
		AgendadorTarefas.agendarTarefa("RelatorioAtualizarLeiturasAnormalidadesCelular", this);
		
	}

		

}
