package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Usuario;

@Local
public interface SBUsuarioLocal {

	public Usuario crearUsuario(Usuario nuevo) throws Exception;

	public Usuario actualizarUsuario(Usuario update) throws Exception;

	public Usuario consultarDetalleUsuario(String id) throws Exception;

	public int consultarUsuarioInicio(Usuario user) throws Exception;
	
	public List<Usuario> consultarAllUsuariosActivos() throws Exception;
	
	public boolean recuperarContrasena(Usuario user) throws Exception;

}
