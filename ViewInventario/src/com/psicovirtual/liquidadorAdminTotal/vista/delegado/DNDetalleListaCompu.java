package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.DetalleListaComputo;
import com.psicovirtual.procesos.modelo.ejb.session.SBDetalleListaComputoLocal;

@ManagedBean(value = "DNDetalleListaCompu")
@ApplicationScoped
public class DNDetalleListaCompu {

	SBDetalleListaComputoLocal sBDetalleListaComputoLocal;

	public DNDetalleListaCompu() throws Exception {
		sBDetalleListaComputoLocal = ServiceLocator.getInstance().obtenerServicio(Parametros.PREFIJO_JNDI
				+ "SBDetalleListaComputo" + Parametros.PREFIJO_ADICIONAL_JNDI + "SBDetalleListaComputoLocal",
				SBDetalleListaComputoLocal.class);
	}

	public DetalleListaComputo crearDetalleListaComputo(DetalleListaComputo nuevo) throws Exception {
		return sBDetalleListaComputoLocal.crearDetalleListaComputo(nuevo);
	}

	public DetalleListaComputo actualizarDetalleListaComputo(DetalleListaComputo update) throws Exception {
		return sBDetalleListaComputoLocal.actualizarDetalleListaComputo(update);
	}

	public DetalleListaComputo consultarDetalleDetalleListaComputo(String id) throws Exception {
		return sBDetalleListaComputoLocal.consultarDetalleDetalleListaComputo(id);
	}

	public List<DetalleListaComputo> consultarAllDetalleListaComputoActivos() throws Exception {
		return sBDetalleListaComputoLocal.consultarAllDetalleListaComputoActivos();
	}

	public List<DetalleListaComputo> consultarAllDetalleListaComputo(String idComputador) throws Exception {
		return sBDetalleListaComputoLocal.consultarAllDetalleListaComputo(idComputador);
	}

}
