package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.DetalleInventarioHistorial;

/**
 * Session Bean implementation class SBInventarioHis
 */
@Stateless
public class SBInventarioHis implements SBInventarioHisLocal {

	@EJB
	SBFacadeProcesosLocal sbFacade;

	/**
	 * Default constructor.
	 */
	public SBInventarioHis() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public DetalleInventarioHistorial crearDetalleInventarioHistorial(DetalleInventarioHistorial nuevo)
			throws Exception {
		DetalleInventarioHistorial entity = (DetalleInventarioHistorial) sbFacade.insertEntity(nuevo);
		return entity;
	}

	@Override
	public DetalleInventarioHistorial actualizarDetalleInventarioHistorial(DetalleInventarioHistorial update)
			throws Exception {
		DetalleInventarioHistorial entity = (DetalleInventarioHistorial) sbFacade.updateEntity(update);
		return entity;
	}

	@Override
	public DetalleInventarioHistorial consultarDetalleDetalleInventarioHistorial(String id) throws Exception {

		String query = "SELECT u FROM DetalleInventarioHistorial u where u.idInventarioHist='" + id
				+ "' and u.idEstado='1' ";
		List<DetalleInventarioHistorial> listDetalleInventarioHistorial = sbFacade.executeQuery(query, null);
		DetalleInventarioHistorial temp = null;

		for (DetalleInventarioHistorial lists : listDetalleInventarioHistorial) {
			temp = lists;
		}
		return temp;
	}

	@Override
	public List<DetalleInventarioHistorial> consultarAllDetalleInventarioHistorialActivos() throws Exception {

		String query = "SELECT u FROM DetalleInventarioHistorial u where u.idEstado='1' ";
		List<DetalleInventarioHistorial> listDetalleInventarioHistorial = sbFacade.executeQuery(query, null);

		return listDetalleInventarioHistorial;
	}

}
