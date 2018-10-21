package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.PlantillaHito;

/**
 * Session Bean implementation class SBPlantillaHito
 */
@Stateless
public class SBPlantillaHito implements SBPlantillaHitoLocal {

	@EJB
	SBFacadeProcesosLocal sbFacade;

	/**
	 * Default constructor.
	 */
	public SBPlantillaHito() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PlantillaHito crearPlantillaHito(PlantillaHito nuevo) throws Exception {
		PlantillaHito entity = (PlantillaHito) sbFacade.insertEntity(nuevo);
		return entity;
	}

	@Override
	public PlantillaHito actualizarPlantillaHito(PlantillaHito update) throws Exception {
		PlantillaHito entity = (PlantillaHito) sbFacade.updateEntity(update);
		return entity;
	}

	@Override
	public PlantillaHito consultarDetallePlantillaHito(String id) throws Exception {

		String query = "SELECT u FROM PlantillaHito u where u.idPlantiHito='" + id + "' and u.idEstado='1' ";
		List<PlantillaHito> listPlantillaHito = sbFacade.executeQuery(query, null);
		PlantillaHito temp = null;

		for (PlantillaHito lists : listPlantillaHito) {
			temp = lists;
		}
		return temp;
	}

	@Override
	public List<PlantillaHito> consultarAllPlantillaHitoActivos() throws Exception {

		String query = "SELECT u FROM PlantillaHito u where u.idEstado='1' ";
		List<PlantillaHito> listPlantillaHito = sbFacade.executeQuery(query, null);

		return listPlantillaHito;
	}
}
