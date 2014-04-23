package gcom.relatorio.faturamento;

import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class RelatorioMultasAutosInfracaoPendentes extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioMultasAutosInfracaoPendentes(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MULTAS_AUTOS_INFRACAO_PENTENTES);
	}


	public Object executar() throws TarefaException {

		
		//ParÃ¢metros
		ArrayList beans = (ArrayList)getParametro("colecaoMultas");
		Funcionario funcionario = (Funcionario)getParametro("funcionario");
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo)getParametro("grupo");
		
		
		
		//Variï¿½veis
		Fachada fachada = Fachada.getInstancia();
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		Map parametros = new HashMap();

				
		//valor de retorno
		byte[] retorno = null;
		

		//Montar Cabeçalho
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		if(faturamentoGrupo != null)
			parametros.put("grupo", faturamentoGrupo.getDescricao());
		if(funcionario != null)
			parametros.put("funcionario", funcionario.getNome());
		
		
		//Montar relatório
		RelatorioDataSource ds = new RelatorioDataSource(beans);
		
		
		
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_MULTAS_AUTOS_INFRACAO_PENTENTES,
		parametros, ds, TarefaRelatorio.TIPO_PDF);

		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		
	}

}
