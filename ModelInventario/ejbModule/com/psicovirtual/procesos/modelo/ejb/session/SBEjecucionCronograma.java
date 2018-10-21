package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.EjecucionCronograma;

/**
 * Session Bean implementation class SBEjecucionCronograma
 */
@Stateless
public class SBEjecucionCronograma implements SBEjecucionCronogramaLocal {

	@EJB
	SBFacadeProcesosLocal sbFacade;

	/**
	 * Default constructor.
	 */
	public SBEjecucionCronograma() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public EjecucionCronograma crearEjecucionCronograma(EjecucionCronograma nuevo) throws Exception {
		EjecucionCronograma entity = (EjecucionCronograma) sbFacade.insertEntity(nuevo);
		return entity;
	}

	@Override
	public EjecucionCronograma actualizarEjecucionCronograma(EjecucionCronograma update) throws Exception {
		EjecucionCronograma entity = (EjecucionCronograma) sbFacade.updateEntity(update);
		return entity;
	}

	@Override
	public EjecucionCronograma consultarDetalleEjecucionCronograma(String id) throws Exception {

		String query = "SELECT u FROM EjecucionCronograma u where u.idEjecucion='" + id + "' and u.idEstado='1' ";
		List<EjecucionCronograma> listEjecucionCronograma = sbFacade.executeQuery(query, null);
		EjecucionCronograma temp = null;

		for (EjecucionCronograma lists : listEjecucionCronograma) {
			temp = lists;
		}
		return temp;
	}

	@Override
	public List<EjecucionCronograma> consultarAllEjecucionCronogramaActivos() throws Exception {

		String query = "SELECT u FROM EjecucionCronograma u where u.idEstado='1' ";
		List<EjecucionCronograma> listEjecucionCronograma = sbFacade.executeQuery(query, null);

		return listEjecucionCronograma;
	}

}
