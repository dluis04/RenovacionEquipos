package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.inventario.PlantillaHito;

@Local
public interface SBPlantillaHitoLocal {

	public PlantillaHito crearPlantillaHito(PlantillaHito nuevo) throws Exception;

	public PlantillaHito actualizarPlantillaHito(PlantillaHito update) throws Exception;

	public PlantillaHito consultarDetallePlantillaHito(String id) throws Exception;

	public List<PlantillaHito> consultarAllPlantillaHitoActivos() throws Exception;

}
