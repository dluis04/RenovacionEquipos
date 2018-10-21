package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.PlantillaPlan;

/**
 * Session Bean implementation class SBPlantillaPlan
 */
@Stateless
public class SBPlantillaPlan implements SBPlantillaPlanLocal {

	@EJB
	SBFacadeProcesosLocal sbFacade;

	/**
	 * Default constructor.
	 */
	public SBPlantillaPlan() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PlantillaPlan crearPlantillaPlan(PlantillaPlan nuevo) throws Exception {
		PlantillaPlan entity = (PlantillaPlan) sbFacade.insertEntity(nuevo);
		return entity;
	}

	@Override
	public PlantillaPlan actualizarPlantillaPlan(PlantillaPlan update) throws Exception {
		PlantillaPlan entity = (PlantillaPlan) sbFacade.updateEntity(update);
		return entity;
	}

	@Override
	public PlantillaPlan consultarDetallePlantillaPlan(String id) throws Exception {

		String query = "SELECT u FROM PlantillaPlan u where u.idPlantillaPlan='" + id + "' and u.idEstado='1' ";
		List<PlantillaPlan> listPlantillaPlan = sbFacade.executeQuery(query, null);
		PlantillaPlan temp = null;

		for (PlantillaPlan lists : listPlantillaPlan) {
			temp = lists;
		}
		return temp;
	}

	@Override
	public List<PlantillaPlan> consultarAllPlantillaPlanActivos() throws Exception {

		String query = "SELECT u FROM PlantillaPlan u where u.idEstado='1' ";
		List<PlantillaPlan> listPlantillaPlan = sbFacade.executeQuery(query, null);

		return listPlantillaPlan;
	}

}
