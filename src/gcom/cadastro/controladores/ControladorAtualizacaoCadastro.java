package gcom.cadastro.controladores;

import java.lang.reflect.Constructor;

import javax.ejb.CreateException;

import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.FaturamentoAtividade;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.Rota;
import gcom.model.IAtualizacaoCadastro;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorComum;
import gcom.util.ControladorException;
import gcom.util.RepositorioUtilHBM;
import gcom.util.Util;

public class ControladorAtualizacaoCadastro extends ControladorComum {
	private static final long serialVersionUID = -6591008548768058900L;
	
	private RepositorioUtilHBM repositorioUtil;
	
	public void ejbCreate() throws CreateException {
		repositorioUtil = RepositorioUtilHBM.getInstancia();
	}	

	public void atualizar(Object objeto) throws ControladorException{
		try {
			
			if (objeto instanceof Imovel){
				Imovel imovel = (Imovel) objeto;
				
				if (imovel.validarSeImovelEmCampo()){
					validarImovelEmCampo(imovel.getId());
				}
			}
			
			if (objeto instanceof IAtualizacaoCadastro){
				if (((ObjetoTransacao) objeto).registrarHistorico()){
					repositorioUtil.registrarHistorico(objeto);
				}
			}
						
			repositorioUtil.atualizar(objeto);
		} catch (ControladorException e){
			throw e;
		} catch (Exception e) {
			throw new ControladorException("Erro ao atualizar objeto de cadastro", e);
		}
	}
	
	private void validarImovelEmCampo(Integer idImovel) throws Exception {
		if (isImovelEmCampo(idImovel)) {
			Rota rota = getControladorMicromedicao().buscarRotaDoImovel(idImovel);
			throw new ControladorException("atencao.imovel_em_campo", null, Util.formatarAnoMesParaMesAno(rota.getFaturamentoGrupo().getAnoMesReferencia()).toString());
		}
	}
	
	private boolean isImovelEmCampo(Integer idImovel) throws Exception {
		Rota rota = getControladorMicromedicao().buscarRotaDoImovel(idImovel);

		FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = getControladorBatch().pesquisarProcessoIniciadoParaGrupo(rota.getFaturamentoGrupo().getId(), 
				rota.getFaturamentoGrupo().getAnoMesReferencia(), FaturamentoAtividade.GERAR_ARQUIVO_LEITURA);

		return (rota.isRotaImpressaoSimultanea() && faturamentoAtividadeCronograma != null && faturamentoAtividadeCronograma.getDataRealizacao() != null);
	}	
}