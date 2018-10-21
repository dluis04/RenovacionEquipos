package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.CaracteristicasComputo;
import com.psicovirtual.procesos.modelo.ejb.session.SBCaracteristicasComputoLocal;

@ManagedBean(value = "DNCaracteristicasComputo")
@ApplicationScoped
public class DNCaracteristicasComputo {

	SBCaracteristicasComputoLocal sBCaracteristicasComputoLocal;

	public DNCaracteristicasComputo() throws Exception {
		sBCaracteristicasComputoLocal = ServiceLocator.getInstance()
				.obtenerServicio(Parametros.PREFIJO_JNDI + "SBCaracteristicasComputo"
						+ Parametros.PREFIJO_ADICIONAL_JNDI + "SBCaracteristicasComputoLocal",
						SBCaracteristicasComputoLocal.class);
	}

	public CaracteristicasComputo crearCaracteristicasComputo(CaracteristicasComputo nuevo) throws Exception {
		return sBCaracteristicasComputoLocal.crearCaracteristicasComputo(nuevo);
	}

	public CaracteristicasComputo actualizarCaracteristicasComputo(CaracteristicasComputo update) throws Exception {
		return sBCaracteristicasComputoLocal.actualizarCaracteristicasComputo(update);
	}

	public CaracteristicasComputo consultarDetalleCaracteristicasComputo(String id) throws Exception {
		return sBCaracteristicasComputoLocal.consultarDetalleCaracteristicasComputo(id);
	}

	public List<CaracteristicasComputo> consultarAllCaracteristicasComputoActivos() throws Exception {
		return sBCaracteristicasComputoLocal.consultarAllCaracteristicasComputoActivos();
	}

}
