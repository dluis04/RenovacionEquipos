package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.inventario.PlanCronograma;

@Local
public interface SBPlanCronogramaLocal {

	public PlanCronograma crearPlanCronograma(PlanCronograma nuevo) throws Exception;

	public PlanCronograma actualizarPlanCronograma(PlanCronograma update) throws Exception;

	public PlanCronograma consultarDetallePlanCronograma(String id) throws Exception;

	public List<PlanCronograma> consultarAllPlanCronogramaActivos() throws Exception;

}
