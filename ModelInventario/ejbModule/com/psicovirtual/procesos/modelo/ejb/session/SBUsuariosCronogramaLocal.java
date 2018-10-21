package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.inventario.UsuariosCronograma;

@Local
public interface SBUsuariosCronogramaLocal {
	
	public UsuariosCronograma crearUsuariosCronograma(UsuariosCronograma nuevo) throws Exception;

	public UsuariosCronograma actualizarUsuariosCronograma(UsuariosCronograma update) throws Exception;

	public UsuariosCronograma consultarDetalleUsuariosCronograma(String id) throws Exception;

	public List<UsuariosCronograma> consultarAllUsuariosCronogramaActivos() throws Exception;

}
