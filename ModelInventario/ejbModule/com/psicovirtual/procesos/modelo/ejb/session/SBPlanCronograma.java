package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.PlanCronograma;

/**
 * Session Bean implementation class SBPlanCronograma
 */
@Stateless
public class SBPlanCronograma implements SBPlanCronogramaLocal {

	@EJB
	SBFacadeProcesosLocal sbFacade;

	/**
	 * Default constructor.
	 */
	public SBPlanCronograma() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PlanCronograma crearPlanCronograma(PlanCronograma nuevo) throws Exception {
		PlanCronograma entity = (PlanCronograma) sbFacade.insertEntity(nuevo);
		return entity;
	}

	@Override
	public PlanCronograma actualizarPlanCronograma(PlanCronograma update) throws Exception {
		PlanCronograma entity = (PlanCronograma) sbFacade.updateEntity(update);
		return entity;
	}

	@Override
	public PlanCronograma consultarDetallePlanCronograma(String id) throws Exception {

		String query = "SELECT u FROM PlanCronograma u where u.idPlan='" + id + "' and u.idEstado='1' ";
		List<PlanCronograma> listPlanCronograma = sbFacade.executeQuery(query, null);
		PlanCronograma temp = null;

		for (PlanCronograma lists : listPlanCronograma) {
			temp = lists;
		}
		return temp;
	}

	@Override
	public List<PlanCronograma> consultarAllPlanCronogramaActivos() throws Exception {

		String query = "SELECT u FROM PlanCronograma u where u.idEstado='1' ";
		List<PlanCronograma> listPlanCronograma = sbFacade.executeQuery(query, null);

		return listPlanCronograma;
	}

}
