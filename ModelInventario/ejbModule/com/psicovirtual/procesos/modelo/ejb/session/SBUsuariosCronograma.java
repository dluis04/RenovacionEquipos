package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.UsuariosCronograma;

/**
 * Session Bean implementation class SBUsuariosCronograma
 */
@Stateless
public class SBUsuariosCronograma implements SBUsuariosCronogramaLocal {
	@EJB
	SBFacadeProcesosLocal sbFacade;

	/**
	 * Default constructor.
	 */
	public SBUsuariosCronograma() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public UsuariosCronograma crearUsuariosCronograma(UsuariosCronograma nuevo) throws Exception {
		UsuariosCronograma entity = (UsuariosCronograma) sbFacade.insertEntity(nuevo);
		return entity;
	}

	@Override
	public UsuariosCronograma actualizarUsuariosCronograma(UsuariosCronograma update) throws Exception {
		UsuariosCronograma entity = (UsuariosCronograma) sbFacade.updateEntity(update);
		return entity;
	}

	@Override
	public UsuariosCronograma consultarDetalleUsuariosCronograma(String id) throws Exception {

		String query = "SELECT u FROM UsuariosCronograma u where u.idUsuarioCrono='" + id
				+ "' and u.idEstado='1' ";
		List<UsuariosCronograma> listUsuariosCronograma = sbFacade.executeQuery(query, null);
		UsuariosCronograma temp = null;

		for (UsuariosCronograma lists : listUsuariosCronograma) {
			temp = lists;
		}
		return temp;
	}

	@Override
	public List<UsuariosCronograma> consultarAllUsuariosCronogramaActivos() throws Exception {

		String query = "SELECT u FROM UsuariosCronograma u where u.idEstado='1' ";
		List<UsuariosCronograma> listUsuariosCronograma = sbFacade.executeQuery(query, null);

		return listUsuariosCronograma;
	}

}
