package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Sede;
import com.psicovirtual.procesos.modelo.ejb.session.SBSedeLocal;

@ManagedBean(value = "DNSede")
@ApplicationScoped
public class DNSede {

	SBSedeLocal sBSedeLocal;

	public DNSede() throws Exception {
		sBSedeLocal = ServiceLocator.getInstance().obtenerServicio(
				Parametros.PREFIJO_JNDI + "SBSede" + Parametros.PREFIJO_ADICIONAL_JNDI + "SBSedeLocal",
				SBSedeLocal.class);
	}

	public Sede crearSede(Sede nuevo) throws Exception {
		return sBSedeLocal.crearSede(nuevo);
	}

	public Sede actualizarSede(Sede update) throws Exception {
		return sBSedeLocal.actualizarSede(update);
	}

	public Sede consultarDetalleSede(String id) throws Exception {
		return sBSedeLocal.consultarDetalleSede(id);
	}

	public List<Sede> consultarAllSedeActivos() throws Exception {
		return sBSedeLocal.consultarAllSedeActivos();
	}

}
