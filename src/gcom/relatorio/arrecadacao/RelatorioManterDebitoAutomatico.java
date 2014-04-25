package gcom.relatorio.arrecadacao;

import gcom.arrecadacao.debitoautomatico.DebitoAutomatico;
import gcom.arrecadacao.debitoautomatico.FiltroDebitoAutomatico;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
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
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corrêa
 * @version 1.0
 */

public class RelatorioManterDebitoAutomatico extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioManterDebitoAutomatico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_DEBITO_AUTOMATICO);
	}
	
	public Object executar() throws TarefaException {

		FiltroDebitoAutomatico filtroDebitoAutomatico = (FiltroDebitoAutomatico) getParametro("filtroDebitoAutomatico");
		DebitoAutomatico debitoAutomaticoParametros = (DebitoAutomatico) getParametro("debitoAutomaticoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		byte[] retorno = null;

		List relatorioBeans = new ArrayList();

		RelatorioManterDebitoAutomaticoBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		filtroDebitoAutomatico.setConsultaSemLimites(true);

		Collection<DebitoAutomatico> colecaoDebitosAutomaticos = fachada.pesquisar(filtroDebitoAutomatico,DebitoAutomatico.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoDebitosAutomaticos != null && !colecaoDebitosAutomaticos.isEmpty()) {

			// laço para criar a coleção de parâmetros da analise
			for (DebitoAutomatico debitoAutomatico : colecaoDebitosAutomaticos) {

				String matricula = "";
				String banco= "";
				String codigoAgencia = "";
				String nomeCliente = "";
				
				if(debitoAutomatico.getImovel()!=null){
					matricula = Util.retornaMatriculaImovelFormatada( debitoAutomatico.getImovel().getId());
					Cliente cliente = Fachada.getInstancia().pesquisarClienteUsuarioImovelExcluidoOuNao( debitoAutomatico.getImovel().getId() );
					nomeCliente = cliente.getNome();
				}
				if(debitoAutomatico.getAgencia()!=null){
					codigoAgencia = debitoAutomatico.getAgencia().getCodigoAgencia();
					
					if(debitoAutomatico.getAgencia().getBanco()!=null){
						banco = debitoAutomatico.getAgencia().getBanco().getDescricaoAbreviada();
					}
				}
				relatorioBean = new RelatorioManterDebitoAutomaticoBean(matricula,banco,codigoAgencia,nomeCliente); 
						
				relatorioBeans.add(relatorioBean);				
			}			
		}

		Map parametros = new HashMap();

		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		if(debitoAutomaticoParametros.getImovel()!=null){
			parametros.put("matricula",Util.retornaMatriculaImovelFormatada(debitoAutomaticoParametros.getImovel().getId()));
		}
		if(debitoAutomaticoParametros.getAgencia()!=null){
			if(debitoAutomaticoParametros.getAgencia().getId()!=null){
				parametros.put("agencia",debitoAutomaticoParametros.getAgencia().getCodigoAgencia() + " - "+ debitoAutomaticoParametros.getAgencia().getNomeAgencia());				
			}
			
			if(debitoAutomaticoParametros.getAgencia().getBanco()!=null){
				parametros.put("banco",debitoAutomaticoParametros.getAgencia().getBanco().getId() + " - " + debitoAutomaticoParametros.getAgencia().getBanco().getDescricaoAbreviada());
			}
		}

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_MANTER_DEBITO_AUTOMATICO, parametros,ds, tipoFormatoRelatorio);

		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterDebitoAutomatico", this);
	}

}
