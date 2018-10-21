package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.inventario.CaracteristicasComputo;

@Local
public interface SBCaracteristicasComputoLocal {

	public CaracteristicasComputo crearCaracteristicasComputo(CaracteristicasComputo nuevo) throws Exception;

	public CaracteristicasComputo actualizarCaracteristicasComputo(CaracteristicasComputo update) throws Exception;

	public CaracteristicasComputo consultarDetalleCaracteristicasComputo(String id) throws Exception;

	public List<CaracteristicasComputo> consultarAllCaracteristicasComputoActivos() throws Exception;
}
