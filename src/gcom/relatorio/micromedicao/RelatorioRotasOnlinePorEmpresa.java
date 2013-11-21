package gcom.relatorio.micromedicao;

import gcom.batch.Relatorio;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.bean.PesquisarRelatorioRotasOnlinePorEmpresaHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [UC0???] Gerar Relatorio Rotas Online por Empresa
 * 
 * @see gcom.gui.relatorio.micromedicao.ExibirGerarRelatorioRotasOnlinePorEmpresaAction
 * @see gcom.gui.relatorio.micromedicao.GerarRelatorioRotasOnlinePorEmpresaAction
 * @see gcom.relatorio.micromedicao.RelatorioRotasOnlinePorEmpresa
 * @see gcom.micromedicao.RepositorioMicromedicaoHBM#pesquisarRelatorioRotasOnlinePorEmpresa(PesquisarRelatorioRotasOnlinePorEmpresaHelper)
 * 
 * @author Victor Cisneiros
 * @date 28/10/2008
 */
public class RelatorioRotasOnlinePorEmpresa extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	
	@Deprecated
	public RelatorioRotasOnlinePorEmpresa() {
		super(null, "");
	}
	
	public RelatorioRotasOnlinePorEmpresa(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ROTAS_ONLINE_POR_EMPRESA);
	}
	
	@Override
	public Object executar() throws TarefaException {
		Fachada fachada = Fachada.getInstancia();
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		Integer anoMesReferencia = (Integer) getParametro("anoMesReferencia");
		Integer idFaturamentoGrupo = (Integer) getParametro("idFaturamentoGrupo");
		Integer idLeiturista = (Integer) getParametro("idLeiturista");
		Integer idEmpresa = (Integer) getParametro("idEmpresa");
		Integer tipoRelatorio = (Integer) getParametro("tipoRelatorio");
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		if (anoMesReferencia != null) {
			parametros.put("mesAnoReferencia", Util.formatarAnoMesParaMesAno(anoMesReferencia));
		}
		if (idFaturamentoGrupo != null) {
			parametros.put("idFaturamentoGrupo", idFaturamentoGrupo);
		}
		if (idEmpresa != null) {
			FiltroEmpresa filtro = new FiltroEmpresa();
			filtro.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, idEmpresa));
			Collection pesquisa = fachada.pesquisar(filtro, Empresa.class.getName());
			Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(pesquisa);
			
			parametros.put("nomeEmpresa", empresa.getDescricao()); 
		}
		if (idLeiturista != null) {
			FiltroLeiturista filtro = new FiltroLeiturista();
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
			filtro.adicionarParametro(new ParametroSimples(FiltroLeiturista.ID, idLeiturista));
			Collection pesquisa = fachada.pesquisar(filtro, Leiturista.class.getName());
			Leiturista Leiturista = (Leiturista) Util.retonarObjetoDeColecao(pesquisa);
			
			parametros.put("nomeLeiturista", Leiturista.getCliente().getNome());
		}
		
		PesquisarRelatorioRotasOnlinePorEmpresaHelper filtro = new PesquisarRelatorioRotasOnlinePorEmpresaHelper();
		filtro.setAnoMesReferencia(anoMesReferencia);
		filtro.setIdEmpresa(idEmpresa);
		filtro.setIdLeiturista(idLeiturista);
		filtro.setIdFaturamentoGrupo(idFaturamentoGrupo);
		
		Collection<RelatorioRotasOnlinePorEmpresaBean> pesquisa = fachada.pesquisarRelatorioRotasOnlinePorEmpresa(filtro);
		
		if (pesquisa == null || pesquisa.isEmpty()) {
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		List<RelatorioRotasOnlinePorEmpresaBean> beans = new ArrayList<RelatorioRotasOnlinePorEmpresaBean>();
		beans.addAll(pesquisa);
		
		byte[] retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_ROTAS_ONLINE_POR_EMPRESA,
				parametros, new RelatorioDataSource(beans), tipoRelatorio);
		  
		try {
			persistirRelatorioConcluido(retorno, 
					Relatorio.RELATORIO_ROTAS_ONLINE_POR_EMPRESA, getIdFuncionalidadeIniciada());
		} catch (Exception e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		Integer idFaturamentoGrupo = (Integer) getParametro("idFaturamentoGrupo");
		Integer idLeiturista = (Integer) getParametro("idLeiturista");
		Integer idEmpresa = (Integer) getParametro("idEmpresa");
		
		PesquisarRelatorioRotasOnlinePorEmpresaHelper filtro = new PesquisarRelatorioRotasOnlinePorEmpresaHelper();
		filtro.setIdEmpresa(idEmpresa);
		filtro.setIdLeiturista(idLeiturista);
		filtro.setIdFaturamentoGrupo(idFaturamentoGrupo);
		
		return Fachada.getInstancia().pesquisarRelatorioRotasOnlinePorEmpresaCount(filtro);
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioRotasOnlinePorEmpresa", this);
	}

}
