package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.ModeloComputo;
import com.psicovirtual.procesos.modelo.ejb.session.SBModeloComputoLocal;

@ManagedBean(value = "DNModeloComputo")
@ApplicationScoped
public class DNModeloComputo {

	SBModeloComputoLocal sBModeloComputoLocal;

	public DNModeloComputo() throws Exception {
		sBModeloComputoLocal = ServiceLocator.getInstance().obtenerServicio(Parametros.PREFIJO_JNDI + "SBModeloComputo"
				+ Parametros.PREFIJO_ADICIONAL_JNDI + "SBModeloComputoLocal", SBModeloComputoLocal.class);
	}

	public ModeloComputo crearModeloComputo(ModeloComputo nuevo) throws Exception {
		return sBModeloComputoLocal.crearModeloComputo(nuevo);
	}

	public ModeloComputo actualizarModeloComputo(ModeloComputo update) throws Exception {
		return sBModeloComputoLocal.actualizarModeloComputo(update);
	}

	public ModeloComputo consultarDetalleModeloComputo(String id) throws Exception {
		return sBModeloComputoLocal.consultarDetalleModeloComputo(id);
	}

	public List<ModeloComputo> consultarAllModeloComputoActivos() throws Exception {
		return sBModeloComputoLocal.consultarAllModeloComputoActivos();
	}

}
