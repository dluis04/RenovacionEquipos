package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.inventario.TipoComputo;

@Local
public interface SBTipoComputoLocal {

	public TipoComputo crearTipoComputo(TipoComputo nuevo) throws Exception;

	public TipoComputo actualizarTipoComputo(TipoComputo update) throws Exception;

	public TipoComputo consultarDetalleTipoComputo(String id) throws Exception;

	public List<TipoComputo> consultarAllTipoComputoActivos() throws Exception;

}
