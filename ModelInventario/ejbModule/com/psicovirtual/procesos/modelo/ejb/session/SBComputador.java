package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Computador;

/**
 * Session Bean implementation class SBComputador
 */
@Stateless
public class SBComputador implements SBComputadorLocal {

	@EJB
	SBFacadeProcesosLocal sbFacade;

	/**
	 * Default constructor.
	 */
	public SBComputador() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Computador crearComputador(Computador nuevo) throws Exception {
		Computador entity = (Computador) sbFacade.insertEntity(nuevo);
		return entity;
	}

	@Override
	public Computador actualizarComputador(Computador update) throws Exception {
		Computador x = (Computador) sbFacade.updateEntity(update);
		return x;
	}

	@Override
	public Computador consultarDetalleComputador(String id) throws Exception {
		String query = "SELECT u FROM Computador u where u.idComputador='" + id + "' and u.idEstado='1' ";
		List<Computador> listComputador = sbFacade.executeQuery(query, null);
		Computador temp = null;

		for (Computador lists : listComputador) {
			temp = lists;
		}
		return temp;
	}

	@Override
	public List<Computador> consultarAllComputadorActivos() throws Exception {
		String query = "SELECT u FROM Computador u where u.idEstado='1' ";

		List<Computador> listComputador = sbFacade.executeQuery(query, null);

		return listComputador;
	}

}
