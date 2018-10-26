package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.DetalleInventario;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.DetalleInventarioHistorial;
import com.psicovirtual.procesos.modelo.ejb.session.SBDetalleInventarioLocal;
import com.psicovirtual.procesos.modelo.ejb.session.SBInventarioHis;
import com.psicovirtual.procesos.modelo.ejb.session.SBInventarioHisLocal;

@ManagedBean(value = "DNDetalleInventarioHistorial")
@ApplicationScoped
public class DNDetalleInventarioHistorial {

	SBInventarioHisLocal sBInventarioHisLocal;

	public DNDetalleInventarioHistorial() throws Exception {
		sBInventarioHisLocal = ServiceLocator.getInstance().obtenerServicio(Parametros.PREFIJO_JNDI + "SBInventarioHis"
				+ Parametros.PREFIJO_ADICIONAL_JNDI + "SBInventarioHisLocal", SBInventarioHisLocal.class);
	}

	public DetalleInventarioHistorial crearDetalleInventarioHistorial(DetalleInventarioHistorial nuevo)
			throws Exception {
		return sBInventarioHisLocal.crearDetalleInventarioHistorial(nuevo);
	}

	public DetalleInventarioHistorial actualizarDetalleInventarioHistorial(DetalleInventarioHistorial update)
			throws Exception {
		return sBInventarioHisLocal.actualizarDetalleInventarioHistorial(update);
	}

	public DetalleInventarioHistorial consultarDetalleDetalleInventarioHistorial(String id) throws Exception {
		return sBInventarioHisLocal.consultarDetalleDetalleInventarioHistorial(id);
	}

	public List<DetalleInventarioHistorial> consultarAllDetalleInventarioHistorialActivos() throws Exception {
		return sBInventarioHisLocal.consultarAllDetalleInventarioHistorialActivos();
	}

}
