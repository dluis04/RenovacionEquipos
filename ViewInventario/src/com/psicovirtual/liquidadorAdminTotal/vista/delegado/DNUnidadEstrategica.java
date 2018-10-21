package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.UnidadEstrategicaServicio;
import com.psicovirtual.procesos.modelo.ejb.session.SBUnidadEstrategicaServicioLocal;

@ManagedBean(value = "DNUnidadEstrategica")
@ApplicationScoped
public class DNUnidadEstrategica {

	SBUnidadEstrategicaServicioLocal sBUnidadEstrategicaServicioLocal;

	public DNUnidadEstrategica() throws Exception {
		sBUnidadEstrategicaServicioLocal = ServiceLocator.getInstance()
				.obtenerServicio(Parametros.PREFIJO_JNDI + "SBUnidadEstrategicaServicio"
						+ Parametros.PREFIJO_ADICIONAL_JNDI + "SBUnidadEstrategicaServicioLocal",
						SBUnidadEstrategicaServicioLocal.class);
	}

	public UnidadEstrategicaServicio crearUnidadEstrategicaServicio(UnidadEstrategicaServicio nuevo) throws Exception {
		return sBUnidadEstrategicaServicioLocal.crearUnidadEstrategicaServicio(nuevo);
	}

	public UnidadEstrategicaServicio actualizarUnidadEstrategicaServicio(UnidadEstrategicaServicio update)
			throws Exception {
		return sBUnidadEstrategicaServicioLocal.actualizarUnidadEstrategicaServicio(update);
	}

	public UnidadEstrategicaServicio consultarDetalleUnidadEstrategicaServicio(String id) throws Exception {
		return sBUnidadEstrategicaServicioLocal.consultarDetalleUnidadEstrategicaServicio(id);
	}

	public List<UnidadEstrategicaServicio> consultarAllUnidadEstrategicaServicioActivos() throws Exception {
		return sBUnidadEstrategicaServicioLocal.consultarAllUnidadEstrategicaServicioActivos();
	}

}
