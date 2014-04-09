package gcom.relatorio.micromedicao.hidrometro;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.micromedicao.hidrometro.FiltroHidrometroDiametro;
import gcom.micromedicao.hidrometro.HidrometroDiametro;
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
 * @author Vinicius Medeiros
 * @version 1.0
 */

public class RelatorioManterHidrometroDiametro extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterHidrometroDiametro(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_HIDROMETRO_DIAMETRO);
	}
	
	@Deprecated
	public RelatorioManterHidrometroDiametro() {
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

		FiltroHidrometroDiametro filtroHidrometroDiametro = (FiltroHidrometroDiametro) getParametro("filtroHidrometroDiametro");
		HidrometroDiametro hidrometroDiametroParametros = (HidrometroDiametro) getParametro("hidrometroDiametroParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterHidrometroDiametroBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		filtroHidrometroDiametro.setConsultaSemLimites(true);

		Collection<HidrometroDiametro> colecaoHidrometrosDiametro = fachada.pesquisar(filtroHidrometroDiametro,
				HidrometroDiametro.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoHidrometrosDiametro != null && !colecaoHidrometrosDiametro.isEmpty()) {

			// laço para criar a coleção de parâmetros da analise
			for (HidrometroDiametro hidrometroDiametro : colecaoHidrometrosDiametro) {

				String ativoInativo = "";

				if ( hidrometroDiametro.getIndicadorUso().equals( ConstantesSistema.INDICADOR_USO_ATIVO ) ){
					ativoInativo = "Ativo";
				} else {
					ativoInativo = "Inativo";
				}
				
				relatorioBean = new RelatorioManterHidrometroDiametroBean(
						// Código
						hidrometroDiametro.getId().toString(), 
						
						// Descrição
						hidrometroDiametro.getDescricao(), 
						
						// Descrição Abreviada
						hidrometroDiametro.getDescricaoAbreviada(), 
						
						// NumeroOrdem
						hidrometroDiametro.getNumeroOrdem().toString(),
						
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

		if (hidrometroDiametroParametros.getId() != null) {
			parametros.put("id",
					hidrometroDiametroParametros.getId().toString());
		} else {
			parametros.put("id", "");
		}

		parametros.put("descricao", hidrometroDiametroParametros.getDescricao());

		parametros.put("descricaoAbreviada", hidrometroDiametroParametros.getDescricaoAbreviada());
		
		parametros.put("numeroOrdem", hidrometroDiametroParametros.getNumeroOrdem().toString());
		
		String indicadorUso = "";

		if (hidrometroDiametroParametros.getIndicadorUso() != null
				&& !hidrometroDiametroParametros.getIndicadorUso().equals("")) {
			if (hidrometroDiametroParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (hidrometroDiametroParametros.getIndicadorUso().equals(
					new Short("2"))) {
				indicadorUso = "Inativo";
			}
		}

		parametros.put("indicadorUso", indicadorUso);

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MANTER_HIDROMETRO_DIAMETRO, parametros,
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
		AgendadorTarefas.agendarTarefa("RelatorioManterHidrometroDiametro", this);
	}

}
