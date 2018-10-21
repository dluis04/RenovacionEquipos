package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.PlantillaHito;
import com.psicovirtual.procesos.modelo.ejb.session.SBPlantillaHitoLocal;

@ManagedBean(value = "DNPlantillaHito")
@ApplicationScoped
public class DNPlantillaHito {

	SBPlantillaHitoLocal sBPlantillaHitoLocal;

	public DNPlantillaHito() throws Exception {
		sBPlantillaHitoLocal = ServiceLocator.getInstance().obtenerServicio(Parametros.PREFIJO_JNDI + "SBPlantillaHito"
				+ Parametros.PREFIJO_ADICIONAL_JNDI + "SBPlantillaHitoLocal", SBPlantillaHitoLocal.class);
	}

	public PlantillaHito crearPlantillaHito(PlantillaHito nuevo) throws Exception {
		return sBPlantillaHitoLocal.crearPlantillaHito(nuevo);
	}

	public PlantillaHito actualizarPlantillaHito(PlantillaHito update) throws Exception {
		return sBPlantillaHitoLocal.actualizarPlantillaHito(update);
	}

	public PlantillaHito consultarDetallePlantillaHito(String id) throws Exception {
		return sBPlantillaHitoLocal.consultarDetallePlantillaHito(id);
	}

	public List<PlantillaHito> consultarAllPlantillaHitoActivos() throws Exception {
		return sBPlantillaHitoLocal.consultarAllPlantillaHitoActivos();
	}

}
