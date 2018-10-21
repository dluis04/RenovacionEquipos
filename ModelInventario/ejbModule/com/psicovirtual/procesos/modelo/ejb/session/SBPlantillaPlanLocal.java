package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.inventario.PlantillaPlan;

@Local
public interface SBPlantillaPlanLocal {

	public PlantillaPlan crearPlantillaPlan(PlantillaPlan nuevo) throws Exception;

	public PlantillaPlan actualizarPlantillaPlan(PlantillaPlan update) throws Exception;

	public PlantillaPlan consultarDetallePlantillaPlan(String id) throws Exception;

	public List<PlantillaPlan> consultarAllPlantillaPlanActivos() throws Exception;

}
