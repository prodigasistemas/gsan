package gcom.relatorio.atendimentopublico;

import gcom.atendimentopublico.ligacaoagua.FiltroMotivoCorte;
import gcom.atendimentopublico.ligacaoagua.MotivoCorte;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
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

public class RelatorioManterMotivoCorte extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterMotivoCorte(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_MOTIVO_CORTE);
	}
	
	@Deprecated
	public RelatorioManterMotivoCorte() {
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
//		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroMotivoCorte filtroMotivoCorte = (FiltroMotivoCorte) getParametro("filtroMotivoCorte");
		MotivoCorte motivoCorteParametros = (MotivoCorte) getParametro("motivoCorteParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterMotivoCorteBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		filtroMotivoCorte.setConsultaSemLimites(true);

		Collection<MotivoCorte> colecaoMotivosCorte = fachada.pesquisar(filtroMotivoCorte,
				MotivoCorte.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoMotivosCorte != null && !colecaoMotivosCorte.isEmpty()) {

			// laço para criar a coleção de parâmetros da analise
			for (MotivoCorte motivoCorte : colecaoMotivosCorte) {

				
				String ativoInativo = "";

				if ( motivoCorte.getIndicadorUso().equals( ConstantesSistema.INDICADOR_USO_ATIVO ) ){
				ativoInativo = "Ativo";
				} else {
				ativoInativo = "Inativo";
				}
				
				
				relatorioBean = new RelatorioManterMotivoCorteBean(
						// Código
						motivoCorte.getId().toString(), 
						
						// Descrição
						motivoCorte.getDescricao(), 
						
						// Indicador de Uso
						ativoInativo);

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

		if (motivoCorteParametros.getId() != null) {
			parametros.put("idMotivoCorte",
					motivoCorteParametros.getId().toString());
		} else {
			parametros.put("idMotivoCorte", "");
		}

		parametros.put("descricao", motivoCorteParametros.getDescricao());

		String indicadorUso = "";

		if (motivoCorteParametros.getIndicadorUso() != null
				&& !motivoCorteParametros.getIndicadorUso().equals("")) {
			if (motivoCorteParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (motivoCorteParametros.getIndicadorUso().equals(
					new Short("2"))) {
				indicadorUso = "Inativo";
			}
		}

		parametros.put("indicadorUso", indicadorUso);

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MANTER_MOTIVO_CORTE, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
//		try {
//			persistirRelatorioConcluido(retorno, Relatorio.MANTER_LOCALIDADE,
//					idFuncionalidadeIniciada);
//		} catch (ControladorException e) {
//			e.printStackTrace();
//			throw new TarefaException("Erro ao gravar relatório no sistema", e);
//		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterMotivoCorte", this);
	}

}
