package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.UnidadEstrategicaServicio;

/**
 * Session Bean implementation class SBUnidadEstrategicaServicio
 */
@Stateless
public class SBUnidadEstrategicaServicio implements SBUnidadEstrategicaServicioLocal {

	@EJB
	SBFacadeProcesosLocal sbFacade;

	/**
	 * Default constructor.
	 */
	public SBUnidadEstrategicaServicio() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public UnidadEstrategicaServicio crearUnidadEstrategicaServicio(UnidadEstrategicaServicio nuevo) throws Exception {
		UnidadEstrategicaServicio entity = (UnidadEstrategicaServicio) sbFacade.insertEntity(nuevo);
		return entity;
	}

	@Override
	public UnidadEstrategicaServicio actualizarUnidadEstrategicaServicio(UnidadEstrategicaServicio update)
			throws Exception {
		UnidadEstrategicaServicio entity = (UnidadEstrategicaServicio) sbFacade.updateEntity(update);
		return entity;
	}

	@Override
	public UnidadEstrategicaServicio consultarDetalleUnidadEstrategicaServicio(String id) throws Exception {

		String query = "SELECT u FROM UnidadEstrategicaServicio u where u.idUnidad='" + id
				+ "' and u.idEstado='1' ";
		List<UnidadEstrategicaServicio> listUnidadEstrategicaServicio = sbFacade.executeQuery(query, null);
		UnidadEstrategicaServicio temp = null;

		for (UnidadEstrategicaServicio lists : listUnidadEstrategicaServicio) {
			temp = lists;
		}
		return temp;
	}

	@Override
	public List<UnidadEstrategicaServicio> consultarAllUnidadEstrategicaServicioActivos() throws Exception {

		String query = "SELECT u FROM UnidadEstrategicaServicio u where u.idEstado='1' ";
		List<UnidadEstrategicaServicio> listUnidadEstrategicaServicio = sbFacade.executeQuery(query, null);

		return listUnidadEstrategicaServicio;
	}

}
