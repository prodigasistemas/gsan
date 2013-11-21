package gcom.relatorio.atendimentopublico.ordemservico;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.atendimentopublico.ordemservico.EmitirOrdemFiscalizacaoForm;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioOrdemFiscalizacaoOnline extends TarefaRelatorio {
	
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioOrdemFiscalizacaoOnline(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ORDEM_FISCALIZACAO_ONLINE);
	}

	@Deprecated
	public RelatorioOrdemFiscalizacaoOnline() {
		super(null, "");
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioOrdemFiscalizacaoOnline", this);

	}

	@Override
	public Object executar() throws TarefaException {
		
		byte[] retorno = null;
		
		Fachada fachada = Fachada.getInstancia();
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		
		EmitirOrdemFiscalizacaoForm dados = (EmitirOrdemFiscalizacaoForm) getParametro("form");
		
		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("nomeCompletoEmpresa", sistemaParametro.getNomeEmpresa());
		parametros.put("nomeEmpresa", sistemaParametro.getNomeAbreviadoEmpresa());
		parametros.put("cnpjEmpresa", Util.formatarCnpj(sistemaParametro.getCnpjEmpresa()));
		parametros.put("inscEstadual", sistemaParametro.getInscricaoEstadual());
		parametros.put("inscricaoEmpresa", "");
		
		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();
		
		RelatorioOrdemFiscalizacaoOnlineBean bean = 
			new RelatorioOrdemFiscalizacaoOnlineBean(
					// Matricula
					dados.getMatriculaImovel()!=null ? 
							Util.retornaMatriculaImovelFormatada(new Integer(dados.getMatriculaImovel())):"",
					// Inscricao Imovel
					dados.getInscricaoImovel()!=null ? 
							dados.getInscricaoImovel():"",	
					// Endereco Imovel
					dados.getEnderecoImovel()!=null ? 
							dados.getEnderecoImovel():"",
					// Data Emissao
					dados.getDataEmissao()!=null ? 
							dados.getDataEmissao():"",
					// Perfil
					dados.getDescricaoPerfilImovel()!=null ? 
							dados.getDescricaoPerfilImovel():"",			
					// Ultima Alteracao
					dados.getUltimaAlteracao()!=null ? 
							dados.getUltimaAlteracao():"",
					// Faturamento Grupo
					dados.getFaturamentoGrupo()!=null ? 
							dados.getFaturamentoGrupo():"",
					// qtdeEconResidencial
					dados.getQtdeEconResidencial()!=null &&  dados.getQtdeEconResidencial().intValue()!=0 ? 
							dados.getQtdeEconResidencial().toString():"",
					// qtdeEconComercial
					dados.getQtdeEconComercial()!=null &&	dados.getQtdeEconComercial().intValue()!=0 ? 
							dados.getQtdeEconComercial().toString():"",		
					// qtdeEconIndustrial
					dados.getQtdeEconIndustrial()!=null && dados.getQtdeEconIndustrial().intValue()!=0 ? 
							dados.getQtdeEconIndustrial().toString():"",
					// qtdeEconPublica
					dados.getQtdeEconPublica()!=null && dados.getQtdeEconPublica().intValue()!=0  ? 
							dados.getQtdeEconPublica().toString():"",
					// qtdeEconTotal
					dados.getQtdeEconTotal()!=null && dados.getQtdeEconTotal().intValue()!=0  ? 
							dados.getQtdeEconTotal().toString():"",				
					// situacaoLigacaoAgua
					dados.getSituacaoLigacaoAgua()!=null ? 
							dados.getSituacaoLigacaoAgua():"",
					// consumoMedioAgua
					dados.getConsumoMedioAgua()!=null ? 
							dados.getConsumoMedioAgua():"",
					// dataCorte
					dados.getDataCorte()!=null ? 
							dados.getDataCorte():"",
					// dataSupressaoParcial
					dados.getDataSupressaoParcial()!=null ? 
							dados.getDataSupressaoParcial():"",
					// dataSupressaoTotal
					dados.getDataSupressaoTotal()!=null ? 
							dados.getDataSupressaoTotal():"",
					// situacaoLigacaoEsgoto
					dados.getSituacaoLigacaoEsgoto()!=null ? 
							dados.getSituacaoLigacaoEsgoto():"",
					// volumeFixoEsgoto
					dados.getVolumeFixoEsgoto()!=null ? 
							dados.getVolumeFixoEsgoto():"",
					// Ocorrencia
					dados.getOcorrencia()!=null ? 
							dados.getOcorrencia():"",
					// valorServicos
					dados.getValorServicos()!=null ? 
							dados.getValorServicos():"",
					// valorDebitosAteDataVencimento
					dados.getValorDebitosAteDataVencimento()!=null ? 
							dados.getValorDebitosAteDataVencimento():"",
					// nomeCliente
					dados.getNomeCliente()!=null ? 
							dados.getNomeCliente():"",		
					// cpfCnpj
					dados.getCpfCnpj()!=null ? 
							dados.getCpfCnpj():"",
					// Rg
					dados.getRg()!=null ? 
							dados.getRg():"",		
					// DDD
					dados.getDdd()!=null ? 
							dados.getDdd():"",
					// numeroTelefone
					dados.getNumeroTelefone()!=null ? 
							dados.getNumeroTelefone():"",
					// ramal
					dados.getRamal()!=null ? 
							dados.getRamal():"",		
					// tipoTelefone
					dados.getTipoTelefone()!=null ? 
							dados.getTipoTelefone():"",
					"ONLINE",
					dados.getOrdemServico()!=null?
							dados.getOrdemServico():"",
					Util.formatarData(new Date()),
					// Uf
					dados.getUf()!=null ? 
							dados.getUf():""
					
			);
		
		relatorioBeans.add(bean);
		
		
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_ORDEM_FISCALIZACAO_ONLINE, parametros, ds,
				tipoFormatoRelatorio);

		// retorna o relatório gerado
		return retorno;
		
		
	}

}
