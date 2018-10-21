package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.PlanCronograma;
import com.psicovirtual.procesos.modelo.ejb.session.SBPlanCronogramaLocal;

@ManagedBean(value = "DNPlanCronograma")
@ApplicationScoped
public class DNPlanCronograma {

	SBPlanCronogramaLocal sBPlanCronogramaLocal;

	public DNPlanCronograma() throws Exception {
		sBPlanCronogramaLocal = ServiceLocator.getInstance().obtenerServicio(Parametros.PREFIJO_JNDI
				+ "SBPlanCronograma" + Parametros.PREFIJO_ADICIONAL_JNDI + "SBPlanCronogramaLocal",
				SBPlanCronogramaLocal.class);
	}

	public PlanCronograma crearPlanCronograma(PlanCronograma nuevo) throws Exception {
		return sBPlanCronogramaLocal.crearPlanCronograma(nuevo);
	}

	public PlanCronograma actualizarPlanCronograma(PlanCronograma update) throws Exception {
		return sBPlanCronogramaLocal.actualizarPlanCronograma(update);
	}

	public PlanCronograma consultarDetallePlanCronograma(String id) throws Exception {
		return sBPlanCronogramaLocal.consultarDetallePlanCronograma(id);
	}

	public List<PlanCronograma> consultarAllPlanCronogramaActivos() throws Exception {
		return sBPlanCronogramaLocal.consultarAllPlanCronogramaActivos();
	}

}
