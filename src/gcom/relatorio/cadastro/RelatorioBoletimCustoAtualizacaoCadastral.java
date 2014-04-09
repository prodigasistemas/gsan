package gcom.relatorio.cadastro;

import gcom.batch.Relatorio;
import gcom.cadastro.AtributosBoletimChaveHelper;
import gcom.cadastro.AtributosBoletimHelper;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.Atributo;
import gcom.seguranca.AtributoGrupo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
 * 
 * @author Vivianne Sousa
 * @date 30/06/2009
 */

public class RelatorioBoletimCustoAtualizacaoCadastral extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
		
	public RelatorioBoletimCustoAtualizacaoCadastral(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_BOLETIM_CUSTO_ATUALIZACAO_CADASTRAL);
	}
	
	@Deprecated
	public RelatorioBoletimCustoAtualizacaoCadastral() {
		super(null, "");
	}
	
	
	private Collection<RelatorioBoletimCustoAtualizacaoCadastralBean> inicializarBeanRelatorio(
			TreeMap<AtributosBoletimChaveHelper, AtributosBoletimHelper> mapAtributosBoletim) {
		
		
		Collection<RelatorioBoletimCustoAtualizacaoCadastralBean> retorno = new ArrayList<RelatorioBoletimCustoAtualizacaoCadastralBean>();
		
		Atributo atributo = null;
		AtributoGrupo atributoGrupo = null;
		
		String itemAtributoGrupo = ""; 
		String itemAtributo = "";
		BigDecimal valorAtualizacao = BigDecimal.ZERO;
		
	    Iterator<AtributosBoletimHelper> iter = mapAtributosBoletim.values().iterator();
	    while (iter.hasNext()) {
	    	
	    	AtributosBoletimHelper helper = (AtributosBoletimHelper) iter.next();
	    	atributo = helper.getAtributo();
			atributoGrupo = atributo.getAtributoGrupo();
			
			itemAtributoGrupo = atributoGrupo.getId() + ".0"; 
			itemAtributo = atributoGrupo.getId() + "." + atributo.getNumeroOrdemEmissao();
			valorAtualizacao = helper.getValorAtualizacaoAtributo().multiply(new BigDecimal(helper.getQuantidadeAtualizacaoAtributo().toString()));
			
			RelatorioBoletimCustoAtualizacaoCadastralBean bean = new RelatorioBoletimCustoAtualizacaoCadastralBean(
					itemAtributoGrupo,atributoGrupo.getDescricaoAtributoGrupo(),
					itemAtributo,atributo.getNomeAtributo(),valorAtualizacao,
					helper.getQuantidadeAtualizacaoAtributo());
			retorno.add(bean);
			
		}
		
		return retorno;
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 * 
	 */
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		Fachada fachada = Fachada.getInstancia();

		TreeMap<AtributosBoletimChaveHelper, AtributosBoletimHelper> mapAtributosBoletim = 
			(TreeMap<AtributosBoletimChaveHelper, AtributosBoletimHelper>) getParametro("mapAtributosBoletim");
		
		Empresa empresa = (Empresa) getParametro("empresa");
		String dataInicial = (String) getParametro("dataInicial");
		String dataFinal = (String) getParametro("dataFinal");
		Integer numImoveisAtualizados = (Integer) getParametro("numImoveisAtualizados");

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
//		String nomeEmpresa = (String) getParametro("nomeEmpresa");
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
//		String cnpjEmpresa = "";
//		if (sistemaParametro.getCnpjEmpresa() != null) {
//			cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
//		}
			
		
		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map<String, String> parametros = new HashMap();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
//		parametros.put("nomeEmpresa",nomeEmpresa);
//		parametros.put("cnpjEmpresa", cnpjEmpresa);
//		parametros.put("idUsuario", idUsuario);
//		parametros.put("nomeUsuario", nomeUsuario);
		
		parametros.put("empresa",empresa.getDescricao());
		parametros.put("dataInicial",dataInicial);
		parametros.put("dataFinal",dataFinal);
		parametros.put("numImoveisAtualizados",numImoveisAtualizados.toString());
		
		

		Collection<RelatorioBoletimCustoAtualizacaoCadastralBean> colecaoBean = this.inicializarBeanRelatorio(mapAtributosBoletim);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_BOLETIM_CUSTO_ATUALIZACAO_CADASTRAL, parametros, ds,
				tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_BOLETIM_CUSTO_ATUALIZACAO_CADASTRAL,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;

	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioBoletimCustoAtualizacaoCadastral", this);
	}
	
}
