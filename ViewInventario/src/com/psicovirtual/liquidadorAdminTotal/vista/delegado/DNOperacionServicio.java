package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.OperacionServicio;
import com.psicovirtual.procesos.modelo.ejb.session.SBOperacionServicioLocal;

@ManagedBean(value = "DNOperacionServicio")
@ApplicationScoped
public class DNOperacionServicio {

	SBOperacionServicioLocal sBOperacionServicioLocal;

	public DNOperacionServicio() throws Exception {
		sBOperacionServicioLocal = ServiceLocator.getInstance().obtenerServicio(Parametros.PREFIJO_JNDI
				+ "SBOperacionServicio" + Parametros.PREFIJO_ADICIONAL_JNDI + "SBOperacionServicioLocal",
				SBOperacionServicioLocal.class);
	}

	public OperacionServicio crearOperacionServicio(OperacionServicio nuevo) throws Exception {
		return sBOperacionServicioLocal.crearOperacionServicio(nuevo);
	}

	public OperacionServicio actualizarOperacionServicio(OperacionServicio update) throws Exception {
		return sBOperacionServicioLocal.actualizarOperacionServicio(update);
	}

	public OperacionServicio consultarDetalleOperacionServicio(String id) throws Exception {
		return sBOperacionServicioLocal.consultarDetalleOperacionServicio(id);
	}

	public List<OperacionServicio> consultarAllOperacionServicioActivos() throws Exception {
		return sBOperacionServicioLocal.consultarAllOperacionServicioActivos();
	}

}
