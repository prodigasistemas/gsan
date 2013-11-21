package gcom.relatorio.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
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

public class RelatorioManterImovelPerfil extends TarefaRelatorio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public  RelatorioManterImovelPerfil(Usuario usuario){
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_IMOVEL_PERFIL);
	}
	
	@Deprecated
	public RelatorioManterImovelPerfil() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param localidades
	 *            Description of the Parameter
	 * @param localidadeParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {	
		
		// ------------------------------------
	//	Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroImovelPerfil filtroImovelPerfil = (FiltroImovelPerfil) getParametro("filtroImovelPerfil");
		ImovelPerfil imovelPerfilParametros = (ImovelPerfil) getParametro("imovelPerfilParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterImovelPerfilBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		filtroImovelPerfil.setConsultaSemLimites(true);

		Collection<ImovelPerfil> colecaoImovelPerfil = fachada.pesquisar(filtroImovelPerfil,
				ImovelPerfil.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoImovelPerfil != null && !colecaoImovelPerfil.isEmpty()) {

			// laço para criar a coleção de parâmetros da analise
			for (ImovelPerfil imovelPerfil : colecaoImovelPerfil) {

				relatorioBean = new RelatorioManterImovelPerfilBean(
						// Código
						imovelPerfil.getId().toString(),					 
						
						// Descrição
						imovelPerfil.getDescricao());						

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

		if (imovelPerfilParametros.getId() != null) {
			parametros.put("id",
					imovelPerfilParametros.getId().toString());
		} else {
			parametros.put("id", "");
		}

		if ( imovelPerfilParametros.getDescricao() != null &&
				!imovelPerfilParametros.getDescricao().equals("") ) {
			
			parametros.put("descricao", imovelPerfilParametros.getDescricao());
		} else {
			parametros.put("descricao", "" );
		}
		
		
		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MANTER_IMOVEL_PERFIL, parametros,
				ds, tipoFormatoRelatorio);
		
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterImovelPerfil", this);
	}

}
