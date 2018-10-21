package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.inventario.UnidadEstrategicaServicio;

@Local
public interface SBUnidadEstrategicaServicioLocal {

	public UnidadEstrategicaServicio crearUnidadEstrategicaServicio(UnidadEstrategicaServicio nuevo) throws Exception;

	public UnidadEstrategicaServicio actualizarUnidadEstrategicaServicio(UnidadEstrategicaServicio update)
			throws Exception;

	public UnidadEstrategicaServicio consultarDetalleUnidadEstrategicaServicio(String id) throws Exception;

	public List<UnidadEstrategicaServicio> consultarAllUnidadEstrategicaServicioActivos() throws Exception;
}
