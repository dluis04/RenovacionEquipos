package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Ciudad;
import com.psicovirtual.procesos.modelo.ejb.session.SBCiudadLocal;

@ManagedBean(value = "DNCiudad")
@ApplicationScoped
public class DNCiudad {

	SBCiudadLocal sBCiudadLocal;

	public DNCiudad() throws Exception {
		sBCiudadLocal = ServiceLocator.getInstance().obtenerServicio(
				Parametros.PREFIJO_JNDI + "SBCiudad" + Parametros.PREFIJO_ADICIONAL_JNDI + "SBCiudadLocal",
				SBCiudadLocal.class);
	}

	public Ciudad crearCiudad(Ciudad nuevo) throws Exception {
		return sBCiudadLocal.crearCiudad(nuevo);
	}

	public Ciudad actualizarCiudad(Ciudad update) throws Exception {
		return sBCiudadLocal.actualizarCiudad(update);
	}

	public Ciudad consultarDetalleCiudad(String id) throws Exception {
		return sBCiudadLocal.consultarDetalleCiudad(id);
	}

	public List<Ciudad> consultarAllCiudadActivos() throws Exception {
		return sBCiudadLocal.consultarAllCiudadActivos();
	}

}
