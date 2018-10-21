package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Sede;

/**
 * Session Bean implementation class SBSede
 */
@Stateless
public class SBSede implements SBSedeLocal {

	@EJB
	SBFacadeProcesosLocal sbFacade;

	/**
	 * Default constructor.
	 */
	public SBSede() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Sede crearSede(Sede nuevo) throws Exception {
		Sede entity = (Sede) sbFacade.insertEntity(nuevo);
		return entity;
	}

	@Override
	public Sede actualizarSede(Sede update) throws Exception {
		Sede entity = (Sede) sbFacade.updateEntity(update);
		return entity;
	}

	@Override
	public Sede consultarDetalleSede(String id) throws Exception {

		String query = "SELECT u FROM Sede u where u.idSede='" + id + "' and u.idEstado='1' ";
		List<Sede> listSede = sbFacade.executeQuery(query, null);
		Sede temp = null;

		for (Sede lists : listSede) {
			temp = lists;
		}
		return temp;
	}

	@Override
	public List<Sede> consultarAllSedeActivos() throws Exception {

		String query = "SELECT u FROM Sede u where u.idEstado='1' ";
		List<Sede> listSede = sbFacade.executeQuery(query, null);

		return listSede;
	}

}
