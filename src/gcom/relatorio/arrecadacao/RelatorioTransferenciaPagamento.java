package gcom.relatorio.arrecadacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.batch.Relatorio;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.DocumentoTipo;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * [UC1217] Gerar relatorio de transferencia de pagamento
 * @author Raimundo Martins
 * @date 24/08/2011
 */
public class RelatorioTransferenciaPagamento extends TarefaRelatorio{


	private static final long serialVersionUID = 1L;
	
	public RelatorioTransferenciaPagamento(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_TRANSFERENCIA_PAGAMENTO);	 
	}

	@Deprecated
	public RelatorioTransferenciaPagamento() {
		super(null, "");
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {		
		return 2;
	}

	@Override
	public Object executar() throws TarefaException {
		byte[] retorno = null;		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		Arrecadador arrecadador = (Arrecadador) this.getParametro("arrecadador");
		String periodoIni = (String)this.getParametro("periodoInicial");		
		String periodoFin = (String) this.getParametro("periodoFinal");		
		AvisoBancario avisoBancario = (AvisoBancario) this.getParametro("avisoBancario");
		ArrecadacaoForma arrecadacaoForma = (ArrecadacaoForma) this.getParametro("arrecadacaoForma");
		DebitoTipo debitoTipo = (DebitoTipo) this.getParametro("tipoDebito");
		DocumentoTipo documentoTipo = (DocumentoTipo) this.getParametro("tipoDocumento");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		List<RelatorioTranferenciaPagamentoBean> colecaoBean = new ArrayList();
		List<RelatorioTranferenciaPagamentoBean> colecaoBeanTemp = new ArrayList();
		
		Map parametros = new HashMap();
		//parametros.put("arrecadador",arrecadador.getCliente().getNome());
		parametros.put("periodoIni", periodoIni);
		parametros.put("periodoFin", periodoFin);
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		colecaoBeanTemp = Fachada.getInstancia().pesquisarTransfereciasPagamento(arrecadador, periodoIni, 
				periodoFin, avisoBancario, arrecadacaoForma, debitoTipo, documentoTipo);		
			Iterator iter = colecaoBeanTemp.iterator();
			if(iter.hasNext()){
				while(iter.hasNext()){
					RelatorioTranferenciaPagamentoBean relatorioTranferenciaPagamentoBean = (RelatorioTranferenciaPagamentoBean) iter.next();
					Categoria cat = Fachada.getInstancia().obterPrincipalCategoriaImovel(
							Integer.parseInt(relatorioTranferenciaPagamentoBean.getMatricula()));
					Iterator clientesImovel = Fachada.getInstancia().obterClienteImovelporRelacaoTipo(
							Integer.parseInt(relatorioTranferenciaPagamentoBean.getMatricula()), 
							ClienteRelacaoTipo.USUARIO.intValue()).iterator();
					ClienteImovel clienteImovel = (ClienteImovel) clientesImovel.next();
					relatorioTranferenciaPagamentoBean.setNomeCliente(clienteImovel.getCliente().getNome());
					relatorioTranferenciaPagamentoBean.setCategoria(cat.getDescricao());
					colecaoBean.add(relatorioTranferenciaPagamentoBean);
				}
				parametros.put("qtdTotalPagamento", colecaoBean.size());		
				RelatorioDataSource ds = new RelatorioDataSource(colecaoBean);			
				retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_TRANSFERENCIA_PAGAMENTO,parametros, ds, tipoFormatoRelatorio);
		}
		else{
			colecaoBean.add(new RelatorioTranferenciaPagamentoBean());
			RelatorioDataSource ds = new RelatorioDataSource(colecaoBean);
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_VAZIO,
					parametros, ds, tipoFormatoRelatorio);
		}
		
		
		
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RELATORIO_TRANSFERENCIA_PAGAMENTO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		
		
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioTransferenciaPagamento", this);
		
	}

}
