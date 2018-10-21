package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.OperacionServicio;

/**
 * Session Bean implementation class SBOperacionServicio
 */
@Stateless
public class SBOperacionServicio implements SBOperacionServicioLocal {

	@EJB
	SBFacadeProcesosLocal sbFacade;

	/**
	 * Default constructor.
	 */
	public SBOperacionServicio() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public OperacionServicio crearOperacionServicio(OperacionServicio nuevo) throws Exception {
		OperacionServicio entity = (OperacionServicio) sbFacade.insertEntity(nuevo);
		return entity;
	}

	@Override
	public OperacionServicio actualizarOperacionServicio(OperacionServicio update) throws Exception {
		OperacionServicio entity = (OperacionServicio) sbFacade.updateEntity(update);
		return entity;
	}

	@Override
	public OperacionServicio consultarDetalleOperacionServicio(String id) throws Exception {

		String query = "SELECT u FROM OperacionServicio u where u.idServicio='" + id + "' and u.idEstado='1' ";
		List<OperacionServicio> listOperacionServicio = sbFacade.executeQuery(query, null);
		OperacionServicio temp = null;

		for (OperacionServicio lists : listOperacionServicio) {
			temp = lists;
		}
		return temp;
	}

	@Override
	public List<OperacionServicio> consultarAllOperacionServicioActivos() throws Exception {

		String query = "SELECT u FROM OperacionServicio u where u.idEstado='1' ";
		List<OperacionServicio> listOperacionServicio = sbFacade.executeQuery(query, null);

		return listOperacionServicio;
	}

}
