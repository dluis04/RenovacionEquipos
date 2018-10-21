package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.inventario.TipoUsuario;

@Local
public interface SBTipoUsuarioLocal {

	public TipoUsuario crearTipoUsuario(TipoUsuario nuevo) throws Exception;

	public TipoUsuario actualizarTipoUsuario(TipoUsuario update) throws Exception;

	public TipoUsuario consultarDetalleTipoUsuario(String id) throws Exception;

	public List<TipoUsuario> consultarAllTiposUsuariosActivos() throws Exception;
}
