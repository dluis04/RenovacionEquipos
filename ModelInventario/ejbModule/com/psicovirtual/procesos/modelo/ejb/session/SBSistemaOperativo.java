package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.SistemaOperativo;

/**
 * Session Bean implementation class SBSistemaOperativo
 */
@Stateless
public class SBSistemaOperativo implements SBSistemaOperativoLocal {

	@EJB
	SBFacadeProcesosLocal sbFacade;

	/**
	 * Default constructor.
	 */
	public SBSistemaOperativo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public SistemaOperativo crearSistemaOperativo(SistemaOperativo nuevo) throws Exception {
		SistemaOperativo entity = (SistemaOperativo) sbFacade.insertEntity(nuevo);
		return entity;
	}

	@Override
	public SistemaOperativo actualizarSistemaOperativo(SistemaOperativo update) throws Exception {
		SistemaOperativo x = (SistemaOperativo) sbFacade.updateEntity(update);
		return x;
	}

	@Override
	public SistemaOperativo consultarDetalleSistemaOperativo(String id) throws Exception {
		String query = "SELECT u FROM SistemaOperativo u where u.idSistema='" + id + "' and u.idEstado='1' ";
		List<SistemaOperativo> listSistemaOperativo = sbFacade.executeQuery(query, null);
		SistemaOperativo temp = null;

		for (SistemaOperativo lists : listSistemaOperativo) {
			temp = lists;
		}
		return temp;
	}

	@Override
	public List<SistemaOperativo> consultarAllSistemaOperativoActivos() throws Exception {
		String query = "SELECT u FROM SistemaOperativo u where u.idEstado='1' ";

		List<SistemaOperativo> listSistemaOperativo = sbFacade.executeQuery(query, null);
		return listSistemaOperativo;
	}

}
