package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.DetalleInventario;
import com.psicovirtual.procesos.modelo.ejb.session.SBDetalleInventarioLocal;

@ManagedBean(value = "DNDetalleInventario")
@ApplicationScoped
public class DNDetalleInventario {

	SBDetalleInventarioLocal sBDetalleInventarioLocal;

	public DNDetalleInventario() throws Exception {
		sBDetalleInventarioLocal = ServiceLocator.getInstance().obtenerServicio(Parametros.PREFIJO_JNDI
				+ "SBDetalleInventario" + Parametros.PREFIJO_ADICIONAL_JNDI + "SBDetalleInventarioLocal",
				SBDetalleInventarioLocal.class);
	}

	public DetalleInventario crearDetalleInventario(DetalleInventario nuevo) throws Exception {
		return sBDetalleInventarioLocal.crearDetalleInventario(nuevo);
	}

	public DetalleInventario actualizarDetalleInventario(DetalleInventario update) throws Exception {
		return sBDetalleInventarioLocal.actualizarDetalleInventario(update);
	}

	public DetalleInventario consultarDetalleDetalleInventario(String id) throws Exception {
		return sBDetalleInventarioLocal.consultarDetalleDetalleInventario(id);
	}

	public List<DetalleInventario> consultarAllDetalleInventarioActivos() throws Exception {
		return sBDetalleInventarioLocal.consultarAllDetalleInventarioActivos();
	}

	public List<DetalleInventario> consultarAllDetalleInventarioComputadorNuevos() throws Exception {
		return sBDetalleInventarioLocal.consultarAllDetalleInventarioComputadorNuevos();
	}

}
