package br.usjt.arqsw.service;

/** @authora Marina Barros
 * 	816155372
 **/

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.arqsw.dao.ChamadoDAO;
import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;

@Service
public class ChamadoService {
	ChamadoDAO dao;
	FilaService fService;
	
	@Autowired
	public ChamadoService(ChamadoDAO dao, FilaService fs){
		this.dao = dao;
		this.fService = fs;
	}

	public int novoChamado(Chamado chamado) throws IOException, ParseException{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String recebe = dateFormat.format(date);
		date = dateFormat.parse(recebe);
		//Atribuindo valores ao Chamado
		chamado.setDataAbertura(date);
		chamado.setStatus("Aberto");
		Fila fila = fService.carregar(chamado.getFila().getId());
		chamado.setFila(fila);
		return dao.inserirChamado(chamado);
	}
	
	public List<Chamado> listarChamados(Fila fila) throws IOException{
		return dao.listarChamados(fila);
	}

}
