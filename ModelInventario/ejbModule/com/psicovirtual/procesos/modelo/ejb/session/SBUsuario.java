package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.estandar.modelo.utilidades.Email;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Usuario;

/**
 * Session Bean implementation class SBUsuario
 */
@Stateless
@LocalBean
public class SBUsuario implements SBUsuarioLocal {

	/**
	 * Default constructor.
	 */

	@EJB
	SBFacadeProcesosLocal sbFacade;

	public SBUsuario() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Usuario crearUsuario(Usuario nuevo) throws Exception {
		Usuario entity = (Usuario) sbFacade.insertEntity(nuevo);
		return entity;
	}

	@Override
	public int consultarUsuarioInicio(Usuario user) throws Exception {

		int retorna = 0;

		String query = "SELECT u.cedula FROM Usuario u where u.usuario='" + user.getUsuario() + "' "
				+ "and u.password='" + user.getPassword() + "' and u.idEstado='1' ";

		List listUsuario = sbFacade.executeQuery(query, null);
		retorna = listUsuario.size();

		return retorna;
	}

	@Override
	public Usuario actualizarUsuario(Usuario update) throws Exception {
		Usuario x = (Usuario) sbFacade.updateEntity(update);
		return x;
	}

	@Override
	public Usuario consultarDetalleUsuario(String usuario) throws Exception {
		String query = "SELECT u FROM Usuario u where u.usuario='" + usuario + "' and u.idEstado='1' ";

		List<Usuario> listUsuario = sbFacade.executeQuery(query, null);
		Usuario temp = null;

		for (int i = 0; i < listUsuario.size(); i++) {
			temp = listUsuario.get(i);
		}
		return temp;
	}

	@Override
	public List<Usuario> consultarAllUsuariosActivos() throws Exception {

		String query = "SELECT u FROM Usuario u where u.idEstado='1' ";
		List<Usuario> listUsuario = sbFacade.executeQuery(query, null);
		return listUsuario;
	}

	@Override
	public boolean recuperarContrasena(Usuario user) throws Exception {
		boolean isEnvio = false;
		Email x = new Email();

		if (x.sendMailSimples(user.getCorreo(), "Recuperacion de contraseña",
				"Cordial Saludo, " + " \n Su contraseña es: " + user.getPassword()) == 1) {
			isEnvio = true;
		}
		return isEnvio;
	}

	@Override
	public List<Usuario> consultarAllUsuariosActivosAdministrativos() throws Exception {
		String query = "SELECT u FROM Usuario u where u.idEstado='1' and u.tipoUsuario.idTipo='3' ";
		List<Usuario> listUsuario = sbFacade.executeQuery(query, null);
		return listUsuario;
	}

}
