package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.EjecucionCronograma;
import com.psicovirtual.procesos.modelo.ejb.session.SBEjecucionCronogramaLocal;

@ManagedBean(value = "DNEjecucionCronograma")
@ApplicationScoped
public class DNEjecucionCronograma {

	SBEjecucionCronogramaLocal sBEjecucionCronogramaLocal;

	public DNEjecucionCronograma() throws Exception {
		sBEjecucionCronogramaLocal = ServiceLocator.getInstance().obtenerServicio(Parametros.PREFIJO_JNDI
				+ "SBEjecucionCronograma" + Parametros.PREFIJO_ADICIONAL_JNDI + "SBEjecucionCronogramaLocal",
				SBEjecucionCronogramaLocal.class);
	}

	public EjecucionCronograma crearEjecucionCronograma(EjecucionCronograma nuevo) throws Exception {
		return sBEjecucionCronogramaLocal.crearEjecucionCronograma(nuevo);
	}

	public EjecucionCronograma actualizarEjecucionCronograma(EjecucionCronograma update) throws Exception {
		return sBEjecucionCronogramaLocal.actualizarEjecucionCronograma(update);
	}

	public EjecucionCronograma consultarDetalleEjecucionCronograma(String id) throws Exception {
		return sBEjecucionCronogramaLocal.consultarDetalleEjecucionCronograma(id);
	}

	public List<EjecucionCronograma> consultarAllEjecucionCronogramaActivos() throws Exception {
		return sBEjecucionCronogramaLocal.consultarAllEjecucionCronogramaActivos();
	}

}
