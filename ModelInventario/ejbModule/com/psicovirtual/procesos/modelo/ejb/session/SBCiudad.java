package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Ciudad;

/**
 * Session Bean implementation class SBCiudad
 */
@Stateless
public class SBCiudad implements SBCiudadLocal {

	@EJB
	SBFacadeProcesosLocal sbFacade;

	/**
	 * Default constructor.
	 */
	public SBCiudad() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Ciudad crearCiudad(Ciudad nuevo) throws Exception {
		Ciudad entity = (Ciudad) sbFacade.insertEntity(nuevo);
		return entity;
	}

	@Override
	public Ciudad actualizarCiudad(Ciudad update) throws Exception {
		Ciudad entity = (Ciudad) sbFacade.updateEntity(update);
		return entity;
	}

	@Override
	public Ciudad consultarDetalleCiudad(String id) throws Exception {

		String query = "SELECT u FROM Ciudad u where u.idCiudad='" + id + "' and u.idEstado='1' ";
		List<Ciudad> listCiudad = sbFacade.executeQuery(query, null);
		Ciudad temp = null;

		for (Ciudad lists : listCiudad) {
			temp = lists;
		}
		return temp;
	}

	@Override
	public List<Ciudad> consultarAllCiudadActivos() throws Exception {

		String query = "SELECT u FROM Ciudad u where u.idEstado='1' ";
		List<Ciudad> listCiudad = sbFacade.executeQuery(query, null);

		return listCiudad;
	}

}
