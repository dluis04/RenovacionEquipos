package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.PlantillaPlan;
import com.psicovirtual.procesos.modelo.ejb.session.SBPlantillaPlanLocal;

@ManagedBean(value = "DNPlantillaPlan")
@ApplicationScoped
public class DNPlantillaPlan {

	SBPlantillaPlanLocal sBPlantillaPlanLocal;

	public DNPlantillaPlan() throws Exception {
		sBPlantillaPlanLocal = ServiceLocator.getInstance().obtenerServicio(Parametros.PREFIJO_JNDI + "SBPlantillaPlan"
				+ Parametros.PREFIJO_ADICIONAL_JNDI + "SBPlantillaPlanLocal", SBPlantillaPlanLocal.class);
	}

	public PlantillaPlan crearPlantillaPlan(PlantillaPlan nuevo) throws Exception {
		return sBPlantillaPlanLocal.crearPlantillaPlan(nuevo);
	}

	public PlantillaPlan actualizarPlantillaPlan(PlantillaPlan update) throws Exception {
		return sBPlantillaPlanLocal.actualizarPlantillaPlan(update);
	}

	public PlantillaPlan consultarDetallePlantillaPlan(String id) throws Exception {
		return sBPlantillaPlanLocal.consultarDetallePlantillaPlan(id);
	}

	public List<PlantillaPlan> consultarAllPlantillaPlanActivos() throws Exception {
		return sBPlantillaPlanLocal.consultarAllPlantillaPlanActivos();
	}

}
