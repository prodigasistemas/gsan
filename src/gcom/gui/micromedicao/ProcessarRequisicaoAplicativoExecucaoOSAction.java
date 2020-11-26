package gcom.gui.micromedicao;

import gcom.api.servicosOperacionais.EncerraOSServlet;
import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.ArquivoRetornoAplicativoExecucaoOSHelper;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.JsonObject;

/**
 * IMplementação do Fechamento das ordens de serviço
 * 
 * @author mgssantos
 * @author pauloalmeida
 */
public class ProcessarRequisicaoAplicativoExecucaoOSAction {
	
	private static final byte[] RESPOSTA_ERRO = null;


	private static final byte[] RESPOSTA_OK = null;


	//preparando a instância do objeto
	private static ProcessarRequisicaoAplicativoExecucaoOSAction instance;


	// Fachada
	Fachada fachada = Fachada.getInstancia();
	
	/**
	 * Método que executa o encerramento da OS
	 * 
	 * @param ArquivoRetornoAplicativoExecucaoOSHelper
	 */
	public void executeEncerramento(ArquivoRetornoAplicativoExecucaoOSHelper araeOSH){

		// Carrega as informações básicas
		OrdemServico ordemServico = fachada.recuperaOSPorId(araeOSH.getIdOrdemServico()); 
		Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();
		
		System.out.println(ordemServico.getServicoTipo());
		System.out.println(ordemServico.getServicoTipo().getOperacao());
		System.out.println(ordemServico.getServicoTipo().getOperacao().getCaminhoUrl());
		
		try {
			envocaMetdodoEncerramento(araeOSH.getIdServicoTipo(), ordemServico, imovel, araeOSH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

			integracaoComercialHelper.setImovel(imovel);
			integracaoComercialHelper.setLigacaoAgua(ligacaoAgua);
			integracaoComercialHelper.setOrdemServico(ordemServico);
			integracaoComercialHelper.setQtdParcelas(araeOSH.getQtdParcelas());
			integracaoComercialHelper.setUsuarioLogado(araeOSH.getUsuario());
							
			fachada.atualizarOSViaApp(araeOSH.getIdServicoTipo(), integracaoComercialHelper,
					araeOSH.getUsuario());
		
		//return null;
	}
	
	
	// Método singleton
	public static ProcessarRequisicaoAplicativoExecucaoOSAction getInstance() {
		if (instance == null) {
			instance = new ProcessarRequisicaoAplicativoExecucaoOSAction();
		}
		
		return instance;
	}
}
