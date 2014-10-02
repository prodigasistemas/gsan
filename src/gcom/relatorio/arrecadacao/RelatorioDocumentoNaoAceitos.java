package gcom.relatorio.arrecadacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;

/**
 * [UC 1215] – Gerar Relatório de Documentos não Aceitos
 * 
 * @author Raimundo Martins
 *
 * @date 19/08/2011
 */
public class RelatorioDocumentoNaoAceitos extends TarefaRelatorio{

	public RelatorioDocumentoNaoAceitos(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_DOCUMENTOS_NAO_ACEITOS);		
	}

	
	private static final long serialVersionUID = 1L;

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}

	@Override
	public Object executar() throws TarefaException {		
		
		byte[] retorno = null;
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		
		Arrecadador arrecadador = (Arrecadador) this.getParametro("arrecadador");
		String periodoIni = (String)this.getParametro("periodoInicial");		
		String periodoFin = (String) this.getParametro("periodoFinal");
		String moviArrecCod = (String) this.getParametro("movimentoArrecadadorCodigo");		
		Integer movimentoArrecadadorCodigo = null;
		if(moviArrecCod !=null && !moviArrecCod.equals("")){
			movimentoArrecadadorCodigo = Integer.parseInt(moviArrecCod);
		}
		
		AvisoBancario avisoBancario = (AvisoBancario) this.getParametro("avisoBancario");
		ArrecadacaoForma arrecadacaoForma = (ArrecadacaoForma) this.getParametro("arrecadacaoForma");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		List<RelatorioDocumentoNaoAceitosBean> colecaoBean = new ArrayList();
		
		Map parametros = new HashMap();
		//parametros.put("arrecadador",arrecadador.getCliente().getNome());
		parametros.put("periodoIni", periodoIni);
		parametros.put("periodoFin", periodoFin);
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		colecaoBean = Fachada.getInstancia().pesquisarDocumentosNaoAceitos(arrecadador, periodoIni, 
				periodoFin, movimentoArrecadadorCodigo, avisoBancario, arrecadacaoForma);
		
		parametros.put("qtdTotalPagamento", colecaoBean.size());		
		RelatorioDataSource ds = new RelatorioDataSource(colecaoBean);
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_DOCUMENTOS_NAO_ACEITOS,parametros, ds, tipoFormatoRelatorio);
		
		
		
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		
		
	}

}
