package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.TipoUsuario;

/**
 * Session Bean implementation class SBTipoUsuario
 */
@Stateless
public class SBTipoUsuario implements SBTipoUsuarioLocal {

	@EJB
	SBFacadeProcesosLocal sbFacade;

	/**
	 * Default constructor.
	 */
	public SBTipoUsuario() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public TipoUsuario crearTipoUsuario(TipoUsuario nuevo) throws Exception {
		TipoUsuario entity = (TipoUsuario) sbFacade.insertEntity(nuevo);
		return entity;
	}

	@Override
	public TipoUsuario actualizarTipoUsuario(TipoUsuario update) throws Exception {
		TipoUsuario x = (TipoUsuario) sbFacade.updateEntity(update);
		return x;
	}

	@Override
	public TipoUsuario consultarDetalleTipoUsuario(String id) throws Exception {
		String query = "SELECT u FROM TipoUsuario u where u.idTipo='" + id + "' and u.idEstado='1' ";
		List<TipoUsuario> listTipoUsuario = sbFacade.executeQuery(query, null);
		TipoUsuario temp = null;

		for (TipoUsuario lists : listTipoUsuario) {
			temp = lists;
		}
		return temp;
	}

	@Override
	public List<TipoUsuario> consultarAllTiposUsuariosActivos() throws Exception {
		String query = "SELECT u FROM TipoUsuario u where u.idEstado='1' ";

		List<TipoUsuario> listTipoUsuario = sbFacade.executeQuery(query, null);

		return listTipoUsuario;
	}

}
