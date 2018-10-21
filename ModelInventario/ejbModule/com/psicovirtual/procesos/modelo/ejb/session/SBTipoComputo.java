package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.TipoComputo;

/**
 * Session Bean implementation class SBTipoComputo
 */
@Stateless
public class SBTipoComputo implements SBTipoComputoLocal {

	@EJB
	SBFacadeProcesosLocal sbFacade;

	/**
	 * Default constructor.
	 */
	public SBTipoComputo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public TipoComputo crearTipoComputo(TipoComputo nuevo) throws Exception {
		TipoComputo entity = (TipoComputo) sbFacade.insertEntity(nuevo);
		return entity;
	}

	@Override
	public TipoComputo actualizarTipoComputo(TipoComputo update) throws Exception {
		TipoComputo entity = (TipoComputo) sbFacade.updateEntity(update);
		return entity;
	}

	@Override
	public TipoComputo consultarDetalleTipoComputo(String id) throws Exception {

		String query = "SELECT u FROM TipoComputo u where u.idTipo='" + id + "' and u.idEstado='1' ";
		List<TipoComputo> listTipoComputo = sbFacade.executeQuery(query, null);
		TipoComputo temp = null;

		for (TipoComputo lists : listTipoComputo) {
			temp = lists;
		}
		return temp;
	}

	@Override
	public List<TipoComputo> consultarAllTipoComputoActivos() throws Exception {

		String query = "SELECT u FROM TipoComputo u where u.idEstado='1' ";
		List<TipoComputo> listTipoComputo = sbFacade.executeQuery(query, null);

		return listTipoComputo;
	}

}
