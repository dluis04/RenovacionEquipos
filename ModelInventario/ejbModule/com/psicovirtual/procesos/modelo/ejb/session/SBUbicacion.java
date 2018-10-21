package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Ubicacion;

/**
 * Session Bean implementation class SBUbicacion
 */
@Stateless
public class SBUbicacion implements SBUbicacionLocal {

	@EJB
	SBFacadeProcesosLocal sbFacade;

	/**
	 * Default constructor.
	 */
	public SBUbicacion() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Ubicacion crearUbicacion(Ubicacion nuevo) throws Exception {
		Ubicacion entity = (Ubicacion) sbFacade.insertEntity(nuevo);
		return entity;
	}

	@Override
	public Ubicacion actualizarUbicacion(Ubicacion update) throws Exception {
		Ubicacion entity = (Ubicacion) sbFacade.updateEntity(update);
		return entity;
	}

	@Override
	public Ubicacion consultarDetalleUbicacion(String id) throws Exception {

		String query = "SELECT u FROM Ubicacion u where u.idUbicacion='" + id + "' and u.idEstado='1' ";
		List<Ubicacion> listUbicacion = sbFacade.executeQuery(query, null);
		Ubicacion temp = null;

		for (Ubicacion lists : listUbicacion) {
			temp = lists;
		}
		return temp;
	}

	@Override
	public List<Ubicacion> consultarAllUbicacionActivos() throws Exception {

		String query = "SELECT u FROM Ubicacion u where u.idEstado='1' ";
		List<Ubicacion> listUbicacion = sbFacade.executeQuery(query, null);

		return listUbicacion;
	}

}