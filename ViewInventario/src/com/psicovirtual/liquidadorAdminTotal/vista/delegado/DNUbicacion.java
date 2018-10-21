package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Ubicacion;
import com.psicovirtual.procesos.modelo.ejb.session.SBUbicacionLocal;

@ManagedBean(value = "DNUbicacion")
@ApplicationScoped
public class DNUbicacion {

	SBUbicacionLocal sBUbicacionLocal;

	public DNUbicacion() throws Exception {
		sBUbicacionLocal = ServiceLocator.getInstance().obtenerServicio(
				Parametros.PREFIJO_JNDI + "SBUbicacion" + Parametros.PREFIJO_ADICIONAL_JNDI + "SBUbicacionLocal",
				SBUbicacionLocal.class);
	}

	public Ubicacion crearUbicacion(Ubicacion nuevo) throws Exception {
		return sBUbicacionLocal.crearUbicacion(nuevo);
	}

	public Ubicacion actualizarUbicacion(Ubicacion update) throws Exception {
		return sBUbicacionLocal.actualizarUbicacion(update);
	}

	public Ubicacion consultarDetalleUbicacion(String id) throws Exception {
		return sBUbicacionLocal.consultarDetalleUbicacion(id);
	}

	public List<Ubicacion> consultarAllUbicacionActivos() throws Exception {
		return sBUbicacionLocal.consultarAllUbicacionActivos();
	}

}
