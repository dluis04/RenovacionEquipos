package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Cronograma;
import com.psicovirtual.procesos.modelo.ejb.session.SBCronogramaLocal;

@ManagedBean(value = "DNCronograma")
@ApplicationScoped
public class DNCronograma {

	SBCronogramaLocal sBCronogramaLocal;

	public DNCronograma() throws Exception {
		sBCronogramaLocal = ServiceLocator.getInstance().obtenerServicio(
				Parametros.PREFIJO_JNDI + "SBCronograma" + Parametros.PREFIJO_ADICIONAL_JNDI + "SBCronogramaLocal",
				SBCronogramaLocal.class);
	}

	public Cronograma crearCronograma(Cronograma nuevo) throws Exception {
		return sBCronogramaLocal.crearCronograma(nuevo);
	}

	public Cronograma actualizarCronograma(Cronograma update) throws Exception {
		return sBCronogramaLocal.actualizarCronograma(update);
	}

	public Cronograma consultarDetalleCronograma(String id) throws Exception {
		return sBCronogramaLocal.consultarDetalleCronograma(id);
	}

	public List<Cronograma> consultarAllCronogramaActivos() throws Exception {
		return sBCronogramaLocal.consultarAllCronogramaActivos();
	}

}
