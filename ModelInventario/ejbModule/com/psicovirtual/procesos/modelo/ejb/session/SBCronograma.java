package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Cronograma;

/**
 * Session Bean implementation class SBCronograma
 */
@Stateless
public class SBCronograma implements SBCronogramaLocal {

	@EJB
	SBFacadeProcesosLocal sbFacade;

	/**
	 * Default constructor.
	 */
	public SBCronograma() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cronograma crearCronograma(Cronograma nuevo) throws Exception {
		Cronograma entity = (Cronograma) sbFacade.insertEntity(nuevo);
		return entity;
	}

	@Override
	public Cronograma actualizarCronograma(Cronograma update) throws Exception {
		Cronograma entity = (Cronograma) sbFacade.updateEntity(update);
		return entity;
	}

	@Override
	public Cronograma consultarDetalleCronograma(String id) throws Exception {

		String query = "SELECT u FROM Cronograma u where u.idCronograma='" + id + "' and u.idEstado='1' ";
		List<Cronograma> listCronograma = sbFacade.executeQuery(query, null);
		Cronograma temp = null;

		for (Cronograma lists : listCronograma) {
			temp = lists;
		}
		return temp;
	}

	@Override
	public List<Cronograma> consultarAllCronogramaActivos() throws Exception {

		String query = "SELECT u FROM Cronograma u where u.idEstado='1' ";
		List<Cronograma> listCronograma = sbFacade.executeQuery(query, null);

		return listCronograma;
	}

}
