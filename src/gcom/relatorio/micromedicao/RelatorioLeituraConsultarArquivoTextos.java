package gcom.relatorio.micromedicao;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.relatorio.micromedicao.FiltroRelatorioLeituraConsultarArquivosTextoHelper;
import gcom.micromedicao.ArquivoTextoRoteiroEmpresa;
import gcom.micromedicao.bean.SituacaoLeituraHelper;
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


/**
 * 
 * Descrição da classe
 * 
 * Essa classe é responsável por realizar a formatação
 * e lógica do relatório
 *
 * @author José Guilherme Macedo Vieira
 * @date 09/06/2009
 */
public class RelatorioLeituraConsultarArquivoTextos extends TarefaRelatorio {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RelatorioLeituraConsultarArquivoTextos(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_LEITURA_CONSULTAR_ARQUIVO_TEXTOS);
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		int tamanhoColecao = new Integer((Integer)this.getParametro("tamanhoColecaoArquivosText"));
		
		return tamanhoColecao;
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		FiltroRelatorioLeituraConsultarArquivosTextoHelper helper = 
			(FiltroRelatorioLeituraConsultarArquivosTextoHelper) getParametro("helper");
		
		//Collection colecaoArquivosTextos = (Collection)getParametro("colecaoArquivosText");
		
		//valor de retorno
		byte[] retorno = null;
		
		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();
		
		
		Fachada fachada = Fachada.getInstancia();
		
		//Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		if(this.getParametro("nomeEmpresa")!= null){
			parametros.put("nomeEmpresa", this.getParametro("nomeEmpresa"));	
		}
		
		
		if(this.getParametro("nomeGrupoFaturamento") != null){
			parametros.put("nomeGrupoFaturamento", this.getParametro("nomeGrupoFaturamento"));	
		}else{
			parametros.put("nomeGrupoFaturamento","");
		}
		
		if(this.getParametro("mesAno") != null){
			parametros.put("mesAno", this.getParametro("mesAno")+"");
		}else{
			parametros.put("mesAno", this.getParametro("mesAno")+"");
		}
		
		
		if(this.getParametro("nomeLeiturista") != null){
			parametros.put("nomeLeiturista", this.getParametro("nomeLeiturista"));	
		}else{
			parametros.put("nomeLeiturista", "");
		}
		
		if(this.getParametro("nomeEmpresa") != null ){
			parametros.put("nomeEmpresa", this.getParametro("nomeEmpresa"));	
		}else{
			parametros.put("nomeEmpresa", "");
		}
		
		parametros.put("tipoFormatoRelatorio","R0629");
		
		if(this.getParametro("situacaoTextoLeitura") != null){
			parametros.put("situacaoTextoLeitura",this.getParametro("situacaoTextoLeitura"));
		}
		
		String servicoTipoCelularDescricao = "";
		
		Collection colecaoArquivosTextos = fachada.consultarRelatorioLeituraConsultarArquivosTexto(helper);
		
		for (Iterator iter = colecaoArquivosTextos.iterator(); iter.hasNext();) {
			
			ArquivoTextoRoteiroEmpresa arq = (ArquivoTextoRoteiroEmpresa) iter.next();
			
			if(arq != null){
				String nomeLeiturista = null;
				
				if(arq.getLeiturista() != null){
					if(arq.getLeiturista().getFuncionario() != null){
						nomeLeiturista = arq.getLeiturista().getFuncionario().getNome();
					}else{
						nomeLeiturista = arq.getLeiturista().getCliente().getNome();
					}
				}
				
				if(servicoTipoCelularDescricao != null || servicoTipoCelularDescricao.equals("")){
					if(arq.getServicoTipoCelular() != null){
						servicoTipoCelularDescricao = arq.getServicoTipoCelular().getDescricao();
					}									
				}
				
				SituacaoLeituraHelper helperSituacao = 
					fachada.pesquisarSituacaoLeitura(
							arq.getAnoMesReferencia(),
							arq.getFaturamentoGrupo().getId(),
							arq.getRota().getId());
				
				Integer qtdLeiturasRealizadas = 
					fachada.quantidadeLeiturasRealizada(
						arq.getRota().getId(),
						arq.getAnoMesReferencia(),
						arq.getServicoTipoCelular().getId());
				
				RelatorioLeituraConsultarArquivosTextosBean relatorioLeituraArquivoTextoBean = 
					new RelatorioLeituraConsultarArquivosTextosBean( 
							arq.getNumeroSequenciaLeitura()+"", 
							arq.getLocalidade().getId()+" - "+ arq.getLocalidade().getDescricao(), 
							arq.getCodigoSetorComercial1()+"", 
							arq.getRota().getCodigo()+"", 
							arq.getQuantidadeImovel()+"", 
							nomeLeiturista, 
							arq.getSituacaoTransmissaoLeitura().getDescricaoSituacao(), 
							Util.formatarData(arq.getUltimaAlteracao()),servicoTipoCelularDescricao,
							arq.getMotivoFinalizacao()!=null?arq.getMotivoFinalizacao():"",
							helperSituacao.getMedidosEnviados(),
							helperSituacao.getMedidosImpressos(),
							helperSituacao.getMedidosNaoImpressos(),
							helperSituacao.getNaoMedidosEnviados(),
							helperSituacao.getNaoMedidosImpressos(),
							helperSituacao.getNaoMedidosNaoImpressos(),
							qtdLeiturasRealizadas.toString());
				
				relatorioBeans.add(relatorioLeituraArquivoTextoBean);
			}
		}
		
		
		
		parametros.put("servicoTipoCelular",servicoTipoCelularDescricao);
		
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_LEITURA_CONSULTAR_ARQUIVO_TEXTOS,
				parametros, ds, tipoFormatoRelatorio);
		
		return retorno;
	}
	
	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioLeituraConsultarArquivosTextos", this);
		
	}
	
	
	
	
}
