package gcom.api.ordemservico.bo;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import gcom.api.ordemservico.dto.FotoDTO;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoFoto;
import gcom.enums.TipoImagem;
import gcom.fachada.Fachada;
import gcom.util.Util;

public class RequisicaoFotosOrdemServico {

	private Fachada fachada = Fachada.getInstancia();
	private List<FotoDTO> listaDTO;

	public RequisicaoFotosOrdemServico(List<FotoDTO> listaDTO) {
		super();
		this.listaDTO = listaDTO;
	}

	public void processar() throws Exception {
		for (FotoDTO dto : listaDTO) {
			OrdemServico ordemServico = new OrdemServico(dto.getOrdemServicoId());

			OrdemServicoFoto ordemServicoFoto = new OrdemServicoFoto(
					ordemServico,
					Util.converterStringParaDateComDataETempo(dto.getData()), 
					dto.getDescricao(), 
					new Date(),
					
					dto.getNome());

			byte[] arquivo = Base64.decodeBase64(dto.getArquivo().getBytes());
			
			inserirFoto(ordemServicoFoto, dto.getImovelId(), arquivo);
		}
	}
	
	private void inserirFoto(OrdemServicoFoto ordemServicoFoto, Integer imovelId, byte[] bytes) {
		String diretorio = "ordem_servico";
		String caminho = fachada.getPastaDestinoDaImagemOrgemServico(imovelId, diretorio);
		
		
		try {
			String nomeArquivo = fachada.gravaImagem(
					bytes, 
					imovelId,
					diretorio,
					ordemServicoFoto.getOrdemServico().getId().toString(), 
					TipoImagem.JPEG.name(), 
					false);

			ordemServicoFoto.setNomeArquivo(nomeArquivo);
			ordemServicoFoto.setCaminhoArquivo(caminho);

			fachada.inserir(ordemServicoFoto);
		} catch (Exception e) {
			File arquivo = new File(caminho, ordemServicoFoto.getNomeArquivo());
			arquivo.delete();
			e.printStackTrace();
		}
	}
}
