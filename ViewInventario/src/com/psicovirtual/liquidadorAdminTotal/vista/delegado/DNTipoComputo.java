package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.TipoComputo;
import com.psicovirtual.procesos.modelo.ejb.session.SBTipoComputoLocal;

@ManagedBean(value = "DNTipoComputo")
@ApplicationScoped
public class DNTipoComputo {

	SBTipoComputoLocal sBTipoComputo;

	public DNTipoComputo() throws Exception {
		sBTipoComputo = ServiceLocator.getInstance().obtenerServicio(Parametros.PREFIJO_JNDI + "SBTipoComputo"
				+ Parametros.PREFIJO_ADICIONAL_JNDI + "SBTipoComputoLocal", SBTipoComputoLocal.class);
	}

	public TipoComputo crearTipoComputo(TipoComputo nuevo) throws Exception {
		return sBTipoComputo.crearTipoComputo(nuevo);
	}

	public TipoComputo actualizarTipoComputo(TipoComputo update) throws Exception {
		return sBTipoComputo.actualizarTipoComputo(update);
	}

	public TipoComputo consultarDetalleTipoComputo(String id) throws Exception {
		return sBTipoComputo.consultarDetalleTipoComputo(id);
	}

	public List<TipoComputo> consultarAllTipoComputoActivos() throws Exception {
		return sBTipoComputo.consultarAllTipoComputoActivos();
	}
}
