package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.inventario.DetalleListaComputo;

@Local
public interface SBDetalleListaComputoLocal {

	public DetalleListaComputo crearDetalleListaComputo(DetalleListaComputo nuevo) throws Exception;

	public DetalleListaComputo actualizarDetalleListaComputo(DetalleListaComputo update) throws Exception;

	public DetalleListaComputo consultarDetalleDetalleListaComputo(String id) throws Exception;

	public List<DetalleListaComputo> consultarAllDetalleListaComputoActivos() throws Exception;

}
