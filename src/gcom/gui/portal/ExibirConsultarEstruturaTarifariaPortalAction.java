package gcom.gui.portal;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**[UC1194] Consultar Estrutura Tarifária Loja Virtual
 * 
 * Classe responsável por configurar as coleções da estrutura
 * tarifária para serem exibidas na tela 
 * informacoes_estrutura_tarifaria_portal_consultar.jsp
 * 
 * @author Diogo Peixoto
 * @since 14/07/2011
 *
 */
public class ExibirConsultarEstruturaTarifariaPortalAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward retorno = mapping.findForward("exibirConsultarEstruturaTarifariaPortalAction");
		HttpSession sessao = request.getSession(false);
		request.setAttribute("voltarInformacoes", true);
		
		//Verifica se os downloads dos arquivos estão disponíveis.
		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		Collection<SistemaParametro> colecaoSistemaParametro = this.getFachada().pesquisar(filtroSistemaParametro, SistemaParametro.class.getName());
		SistemaParametro sistemaParametro = colecaoSistemaParametro.iterator().next();
		this.setarDownloadsLoja(sistemaParametro, request);
		
		String download = (String)request.getParameter("download");
		if(Util.verificarNaoVazio(download)){
			this.retornaArquivo(download, response, sistemaParametro, request);
		}
		
		//Consumos Não medidos
		ArrayList<ConsultarEstruturaTarifariaPortalHelper> consumosNaoMedidos = new ArrayList<ConsultarEstruturaTarifariaPortalHelper>();
		ConsultarEstruturaTarifariaPortalHelper consumoNaoMedido = null;
		
		//Consumos Medidos Residencial
		ArrayList<ConsultarEstruturaTarifariaPortalHelper> helperResidencial = 
			this.getFachada().pesquisarEstruturaTarifaria(Categoria.RESIDENCIAL);
		if(!Util.isVazioOrNulo(helperResidencial)){
			request.setAttribute("helperResidencial", helperResidencial);
			
			//------------------------- Carregando os consumos não medidos (Residencial) -------------------------//
			//Recupera os dados referentes ao consumo da tarifa social
			consumoNaoMedido = helperResidencial.get(0); 
			if(consumoNaoMedido != null){
				consumosNaoMedidos.add(new ConsultarEstruturaTarifariaPortalHelper(
						"Residencial Tarifa Social", "por mês", consumoNaoMedido.getValor(), 2));
			}
			
			//Recupera os dados referentes ao consumo da tarifa mínima
			consumoNaoMedido = helperResidencial.get(1);
			if(consumoNaoMedido != null){
				consumosNaoMedidos.add(new ConsultarEstruturaTarifariaPortalHelper("Residencial", "por mês", consumoNaoMedido.getValor(), 2));
			}
			//------------------------- Fim do carregar os consumos não medidos (Residencial) -------------------------//
		}
		
		//Consumos Medidos Comercial
		ArrayList<ConsultarEstruturaTarifariaPortalHelper> helperComercial = 
			this.getFachada().pesquisarEstruturaTarifaria(Categoria.COMERCIAL);
		if(!Util.isVazioOrNulo(helperComercial)){
			request.setAttribute("helperComercial", helperComercial);
		
			//------------------------- Carregando os consumos não medidos (Residencial) -------------------------//
			consumoNaoMedido = helperComercial.get(0); 
			if(consumoNaoMedido != null){
				consumosNaoMedidos.add(new ConsultarEstruturaTarifariaPortalHelper("Comercial", "por mês", consumoNaoMedido.getValor(), 2));
			}
		}
		
		//Consumos Medidos Industrial
		ArrayList<ConsultarEstruturaTarifariaPortalHelper> helperIndustrial = 
			this.getFachada().pesquisarEstruturaTarifaria(Categoria.INDUSTRIAL);
		if(!Util.isVazioOrNulo(helperIndustrial)){
			request.setAttribute("helperIndustrial", helperIndustrial);
			//------------------------- Carregando os consumos não medidos (Industrial) -------------------------//
			consumoNaoMedido = helperIndustrial.get(0); 
			if(consumoNaoMedido != null){
				consumosNaoMedidos.add(new ConsultarEstruturaTarifariaPortalHelper("Industrial", "por mês", consumoNaoMedido.getValor(), 2));
			}

		}
		
		//Consumos Medidos Pública
		ArrayList<ConsultarEstruturaTarifariaPortalHelper> helperPublica = 
			this.getFachada().pesquisarEstruturaTarifaria(Categoria.PUBLICO);
		if(!Util.isVazioOrNulo(helperPublica)){
			request.setAttribute("helperPublica", helperPublica);
			
			//------------------------- Carregando os consumos não medidos (Publico) -------------------------//
			consumoNaoMedido = helperPublica.get(0); 
			if(consumoNaoMedido != null){
				consumosNaoMedidos.add(new ConsultarEstruturaTarifariaPortalHelper("Público", "por mês", consumoNaoMedido.getValor(), 2));
			}
		}
		
		//------------------------- Carregando os consumos não medidos  -------------------------//
		consumosNaoMedidos.add(new ConsultarEstruturaTarifariaPortalHelper("Fornecimento por Carros-pipa ", "por 1.000L", "9,58", 2));
		consumosNaoMedidos.add(new ConsultarEstruturaTarifariaPortalHelper("Fornecimento por Carros-pipa Órgãos Públicos  ", "por 1.000L", "1,33", 2));
		consumosNaoMedidos.add(this.getFachada().pesquisarEstruturaTarifariaChafarizPublico());
		
		request.setAttribute("consumosNaoMedidos", consumosNaoMedidos);
		
		/*
		 * Serve para inicializar as tarifas de água bruta. O índice de consumo para a tarifa de água bruta
		 * é 3. Necessita iterar pelos 3 helper (Residencial, Comercial, Industrial)
		 */
		ArrayList<ConsultarEstruturaTarifariaPortalHelper> helperAguaBruta = new ArrayList<ConsultarEstruturaTarifariaPortalHelper>();
		helperAguaBruta = this.getFachada().pesquisarEstruturaTarifariaAguaBruta(Categoria.RESIDENCIAL);
		helperAguaBruta.addAll(this.getFachada().pesquisarEstruturaTarifariaAguaBruta(Categoria.COMERCIAL));
		helperAguaBruta.addAll(this.getFachada().pesquisarEstruturaTarifariaAguaBruta(Categoria.INDUSTRIAL));
		request.setAttribute("helperAguaBruta", helperAguaBruta);
		
		/*
		 * Vai colocar na sessão o bean que será necesário para gerar o ralatório,
		 * caso o usuário selecione para gerar o relatório. 
		 */
		ArrayList<ConsultarEstruturaTarifariaPortalHelper> estruturaTarifariaBeans = new ArrayList<ConsultarEstruturaTarifariaPortalHelper>();
		estruturaTarifariaBeans.addAll(helperResidencial);
		estruturaTarifariaBeans.addAll(helperComercial);
		estruturaTarifariaBeans.addAll(helperIndustrial);
		estruturaTarifariaBeans.addAll(helperPublica);
		estruturaTarifariaBeans.addAll(consumosNaoMedidos);
		estruturaTarifariaBeans.addAll(helperAguaBruta);
		sessao.setAttribute("estruturaTarifariaBeans", estruturaTarifariaBeans);
		
		return retorno;
	}

	//Verifica a existencias dos arquivos no banco	
	private void setarDownloadsLoja(SistemaParametro sistemaParametro, HttpServletRequest request){
		if(sistemaParametro.getArquivoLeiEstTarif() != null && sistemaParametro.getArquivoLeiEstTarif().length != 0){
			request.setAttribute("leiEstadual", true);
			if(Util.verificarNaoVazio(sistemaParametro.getDescricaoLeiEstTarif())){
				request.setAttribute("labelLeiEstadual", sistemaParametro.getDescricaoLeiEstTarif());
			}
		}
		
		if(sistemaParametro.getArquivoLeiEstTarif() != null && sistemaParametro.getArquivoLeiEstTarif().length != 0){
			request.setAttribute("decretoEstadual", true);
			if(Util.verificarNaoVazio(sistemaParametro.getDescricaoDecreto())){
				request.setAttribute("labelDecretoEstadual", sistemaParametro.getDescricaoDecreto());
			}
		}
	}
	
	//Nétodo responsável por retornar o arquivo para o usuário caso ele aperte no link do pdf.
	private void retornaArquivo(String arquivo, HttpServletResponse response, SistemaParametro sistemaParametro, HttpServletRequest request){
		String mimeType = ConstantesSistema.CONTENT_TYPE_PDF;
		OutputStream out = null;
		byte[] file = null;
		
		if(arquivo.equalsIgnoreCase("decretoEstadual")){
			file = sistemaParametro.getArquivoDecreto();
		}else if(arquivo.equalsIgnoreCase("leiEstadual")){
			file = sistemaParametro.getArquivoLeiEstTarif();
		}
		
		try {
			response.setContentType(mimeType);
			out = response.getOutputStream();
			out.write(file);
			out.flush();
			out.close();
		} 
		catch (IOException e) {
			request.setAttribute("arquivoNaoEncontrado", true);
		}
		
	}
}