package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.inventario.ModeloComputo;

@Local
public interface SBModeloComputoLocal {

	public ModeloComputo crearModeloComputo(ModeloComputo nuevo) throws Exception;

	public ModeloComputo actualizarModeloComputo(ModeloComputo update) throws Exception;

	public ModeloComputo consultarDetalleModeloComputo(String id) throws Exception;

	public List<ModeloComputo> consultarAllModeloComputoActivos() throws Exception;

}
