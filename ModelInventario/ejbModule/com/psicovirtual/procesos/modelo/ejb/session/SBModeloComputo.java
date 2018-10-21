package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.ModeloComputo;

/**
 * Session Bean implementation class SBModeloComputo
 */
@Stateless
public class SBModeloComputo implements SBModeloComputoLocal {

	@EJB
	SBFacadeProcesosLocal sbFacade;

	/**
	 * Default constructor.
	 */
	public SBModeloComputo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ModeloComputo crearModeloComputo(ModeloComputo nuevo) throws Exception {
		ModeloComputo entity = (ModeloComputo) sbFacade.insertEntity(nuevo);
		return entity;
	}

	@Override
	public ModeloComputo actualizarModeloComputo(ModeloComputo update) throws Exception {
		ModeloComputo entity = (ModeloComputo) sbFacade.updateEntity(update);
		return entity;
	}

	@Override
	public ModeloComputo consultarDetalleModeloComputo(String id) throws Exception {

		String query = "SELECT u FROM ModeloComputo u where u.idModelo='" + id + "' and u.idEstado='1' ";
		List<ModeloComputo> listModeloComputo = sbFacade.executeQuery(query, null);
		ModeloComputo temp = null;

		for (ModeloComputo lists : listModeloComputo) {
			temp = lists;
		}
		return temp;
	}

	@Override
	public List<ModeloComputo> consultarAllModeloComputoActivos() throws Exception {

		String query = "SELECT u FROM ModeloComputo u where u.idEstado='1' ";
		List<ModeloComputo> listModeloComputo = sbFacade.executeQuery(query, null);

		return listModeloComputo;
	}

}
