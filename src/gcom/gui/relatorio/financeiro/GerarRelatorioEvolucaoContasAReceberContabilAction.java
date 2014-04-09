package gcom.gui.relatorio.financeiro;

import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.financeiro.RelatorioEvolucaoContasAReceberContabil;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Geração do relatório 
 * [UC0718] Gerar Relatório da Evolução do Contas a Receber Contábil
 * 
 */

public class GerarRelatorioEvolucaoContasAReceberContabilAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

        GerarRelatorioEvolucaoContasAReceberContabilActionForm form = (GerarRelatorioEvolucaoContasAReceberContabilActionForm) actionForm;

		String mesAno = form.getMesAno();
		Integer gerenciaRegional = null;
		Integer localidade = null;
		Integer unidadeNegocio = null;
		Integer municpio = null;
		String opcaoTotalizacao = form.getOpcaoTotalizacao();
		
		if(mesAno == null || mesAno.equals("")){
			throw new ActionServletException("atencao.required", null,	"Mês/Ano de Referência Contábil");
		}
		
		if (opcaoTotalizacao == null || opcaoTotalizacao.equalsIgnoreCase("")) {
			throw new ActionServletException("atencao.required", null, "Opção de Totalização ");
		}

		if (opcaoTotalizacao.trim().equals("gerenciaRegional")) {
			gerenciaRegional = Integer.parseInt(form.getGerenciaRegionalId());
			if (gerenciaRegional == null
					|| gerenciaRegional.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null, "Gerência Regional");
			}
		} else if (opcaoTotalizacao.trim().equals("gerenciaRegionalUnidadeNegocio")) {
			gerenciaRegional = Integer.parseInt(form.getGerenciaRegionalporUnidadeNegocioId());
			if (gerenciaRegional == null
					|| gerenciaRegional.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null, "Gerência Regional");
			}
		} else if (opcaoTotalizacao.trim().equals("gerenciaRegionalLocalidade")) {
            gerenciaRegional = Integer.parseInt(form.getGerenciaRegionalporLocalidadeId());
            if (gerenciaRegional == null
                    || gerenciaRegional.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
                throw new ActionServletException("atencao.required", null, "Gerência Regional");
            }
        }

		if (opcaoTotalizacao.trim().equals("localidade")) {
			String codigoLocalidade = form.getCodigoLocalidade();

			if (codigoLocalidade == null || codigoLocalidade.equalsIgnoreCase("")) {
				throw new ActionServletException("atencao.required", null, "Localidade");

			} else {
				pesquisarLocalidade(codigoLocalidade, httpServletRequest);
			}

			localidade = Integer.parseInt(codigoLocalidade);
		}
		
		if (opcaoTotalizacao.trim().equals("municipio")) {
			String codigoMunicipio = form.getCodigoMunicipio();

			if (codigoMunicipio == null || codigoMunicipio.equalsIgnoreCase("")) {
				throw new ActionServletException("atencao.required", null, "Município");

			} else {
				pesquisarMunicipio(codigoMunicipio, httpServletRequest);
				municpio = Integer.parseInt(codigoMunicipio);
			}
		}
		
		if (opcaoTotalizacao.trim().equals("unidadeNegocio")) {
            unidadeNegocio = Integer.parseInt(form.getUnidadeNegocioId());

			if (unidadeNegocio == null
					|| unidadeNegocio.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null, "Unidade de Negócio");

			}

		}else if (opcaoTotalizacao.trim().equals("unidadeNegocioLocalidade")) {
            unidadeNegocio = Integer.parseInt(form.getUnidadeNegocioLocalidadeId());
            
            if (unidadeNegocio == null
                    || unidadeNegocio.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
                throw new ActionServletException("atencao.required", null, "Unidade de Negócio");

            }
        }
		
		int mesAnoInteger = Integer.parseInt(mesAno.substring(0, 2)	+ mesAno.substring(3, 7));

		// Verificar se o Ano/Mês está valido [FS0002]
		if (!Util.validarMesAno(mesAno)) {
			// Se deu algum erro neste ponto, indica falha no sistema
			throw new ActionServletException("erro.sistema");
		}

		Fachada fachada = Fachada.getInstancia();
        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
        
		// [FS0002] Verificar referência contabil
        verificarReferenciaContabil(mesAno, sistemaParametro);
        
		// Parte que vai mandar o relatório para a tela

		// cria uma instância da classe do relatório
        RelatorioEvolucaoContasAReceberContabil relatorioEvolucaoContasAReceberContabil = new RelatorioEvolucaoContasAReceberContabil(
				(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
        relatorioEvolucaoContasAReceberContabil.addParametro("opcaoTotalizacao", opcaoTotalizacao);
        relatorioEvolucaoContasAReceberContabil.addParametro("mesAnoInteger", mesAnoInteger);
        relatorioEvolucaoContasAReceberContabil.addParametro("localidade", localidade);
        relatorioEvolucaoContasAReceberContabil.addParametro("municipio", municpio);
        relatorioEvolucaoContasAReceberContabil.addParametro("gerenciaRegional", gerenciaRegional);
        relatorioEvolucaoContasAReceberContabil.addParametro("unidadeNegocio",unidadeNegocio);

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

        relatorioEvolucaoContasAReceberContabil.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioEvolucaoContasAReceberContabil,
					tipoRelatorio, httpServletRequest, httpServletResponse,	actionMapping);

		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");

		}

		return retorno;
	}
	
	private void pesquisarLocalidade(String idLocalidade,
			HttpServletRequest httpServletRequest) {

		Fachada fachada = Fachada.getInstancia();

		// Pesquisa a localidade na base
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, idLocalidade));

		Collection<Localidade> localidadePesquisada = fachada.pesquisar(
				filtroLocalidade, Localidade.class.getName());

		if (localidadePesquisada == null || localidadePesquisada.isEmpty()) {
			// a localidade não foi encontrada
			throw new ActionServletException("atencao.localidade.inexistente");

		}
	}
	
	private void pesquisarMunicipio(String idMunicipio,
			HttpServletRequest httpServletRequest) {

		Fachada fachada = Fachada.getInstancia();

		// Pesquisa a localidade na base
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(new ParametroSimples(
				FiltroMunicipio.ID, idMunicipio));

		Collection<Localidade> municipioPesquisado = fachada.pesquisar(
				filtroMunicipio, Municipio.class.getName());

		if (municipioPesquisado == null || municipioPesquisado.isEmpty()) {
			// a localidade não foi encontrada
			throw new ActionServletException("atencao.municipio.inexistente");

		}
	}
    
	/**
     * Verifica se o Mês/Ano informado é inferior ao Mês/Ano do Sistema
     */
    public boolean verificarReferenciaContabil(
            String anoMesReferenciaContabil,SistemaParametro sistemaParametro)  {

        boolean verificacao = Util.compararAnoMesReferencia(anoMesReferenciaContabil
                .substring(3, 7)
                + anoMesReferenciaContabil.substring(0, 2), sistemaParametro
                .getAnoMesFaturamento().toString(), ">");

        if (verificacao) {        	
            String anoMesSistema = Util.formatarAnoMesParaMesAno(
            		sistemaParametro.getAnoMesFaturamento());
            throw new ActionServletException(
                    "atencao.referencia_contabil.ano_mes.inferior", null, 
                     	anoMesSistema);

        }
        return true;
    }
}
