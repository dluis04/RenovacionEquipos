package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.CaracteristicasComputo;

/**
 * Session Bean implementation class SBCaracteristicasComputo
 */
@Stateless
public class SBCaracteristicasComputo implements SBCaracteristicasComputoLocal {

	@EJB
	SBFacadeProcesosLocal sbFacade;

	/**
	 * Default constructor.
	 */
	public SBCaracteristicasComputo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CaracteristicasComputo crearCaracteristicasComputo(CaracteristicasComputo nuevo) throws Exception {
		CaracteristicasComputo entity = (CaracteristicasComputo) sbFacade.insertEntity(nuevo);
		return entity;
	}

	@Override
	public CaracteristicasComputo actualizarCaracteristicasComputo(CaracteristicasComputo update) throws Exception {
		CaracteristicasComputo entity = (CaracteristicasComputo) sbFacade.updateEntity(update);
		return entity;
	}

	@Override
	public CaracteristicasComputo consultarDetalleCaracteristicasComputo(String id) throws Exception {

		String query = "SELECT u FROM CaracteristicasComputo u where u.idCaracteristicas='" + id + "' and u.idEstado='1' ";
		List<CaracteristicasComputo> listCaracteristicasComputo = sbFacade.executeQuery(query, null);
		CaracteristicasComputo temp = null;

		for (CaracteristicasComputo lists : listCaracteristicasComputo) {
			temp = lists;
		}
		return temp;
	}

	@Override
	public List<CaracteristicasComputo> consultarAllCaracteristicasComputoActivos() throws Exception {

		String query = "SELECT u FROM CaracteristicasComputo u where u.idEstado='1' ";
		List<CaracteristicasComputo> listCaracteristicasComputo = sbFacade.executeQuery(query, null);

		return listCaracteristicasComputo;
	}

}
