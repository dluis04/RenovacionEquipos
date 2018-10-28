package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Computador;

/**
 * Session Bean implementation class SBComputador
 */
@Stateless
@LocalBean
public class SBComputador implements SBComputadorLocal {

	@EJB
	SBFacadeProcesosLocal sbFacade;

	@EJB
	SBCaracteristicasComputoLocal sBCaracteristicasComputo;

	@EJB
	SBModeloComputoLocal sBModeloComputo;

	@EJB
	SBTipoComputoLocal sBTipoComputo;

	@EJB
	SBSistemaOperativoLocal sBSistemaOperativo;

	@EJB
	SBOperacionServicioLocal sBOperacionServicio;

	/**
	 * Default constructor.
	 */
	public SBComputador() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Computador crearComputador(Computador nuevo) throws Exception {
		Computador entity = (Computador) sbFacade.insertEntity(nuevo);
		return entity;
	}

	@Override
	public Computador actualizarComputador(Computador update) throws Exception {
		Computador x = (Computador) sbFacade.updateEntity(update);
		return x;
	}

	@Override
	public Computador consultarDetalleComputador(String id) throws Exception {
		String query = "SELECT u FROM Computador u where u.idComputador='" + id + "' and u.idEstado='1' ";
		List<Computador> listComputador = sbFacade.executeQuery(query, null);
		Computador temp = null;

		for (Computador lists : listComputador) {
			temp = lists;
		}
		return temp;
	}

	@Override
	public List<Computador> consultarAllComputadorActivos() throws Exception {
		String query = "SELECT u FROM Computador u where u.idEstado='1' ";

		List<Computador> listComputador = sbFacade.executeQuery(query, null);

		return listComputador;
	}

	@Override
	public List<Computador> consultarAllComputadorNuevos() throws Exception {

		List<Computador> listComputador = new ArrayList<Computador>();

		String query = "select com.ID_COMPUTADOR, com.ID_MODELO, com.ID_TIPO, com.ID_SISTEMA, com.ID_CARACTERISTICAS, "
				+ "com.ID_SERVICIO, com.NOMBRE_COMPUTO, com.SERIAL__MONITOR, com.SERIAL_MOUSE, com.SERIAL_TECLADO, com.DIRECCION_IP "
				+ "from COMPUTADOR com LEFT join DETALLE_INVENTARIO deta  on com.ID_COMPUTADOR=deta.ID_COMPUTADOR where com.ID_ESTADO='1' and com.ID_ESTADO_COMPU='1' and deta.ID_COMPUTADOR is null ";

		HashMap parametros = new HashMap();

		List<Object[]> registrosList = sbFacade.executeNativeQuery(query, parametros);
		Computador compu = null;

		for (int i = 0; i < registrosList.size(); i++) {

			compu = new Computador();
			compu.setIdComputador(Integer.parseInt(registrosList.get(i)[0].toString()));

			compu.setModeloComputo(sBModeloComputo.consultarDetalleModeloComputo(registrosList.get(i)[1].toString()));

			compu.setTipoComputo(sBTipoComputo.consultarDetalleTipoComputo(registrosList.get(i)[2].toString()));

			compu.setSistemaOperativo(
					sBSistemaOperativo.consultarDetalleSistemaOperativo(registrosList.get(i)[3].toString()));

			compu.setCaracteristicasComputo(sBCaracteristicasComputo
					.consultarDetalleCaracteristicasComputo(registrosList.get(i)[4].toString()));

			compu.setOperacionServicio(
					sBOperacionServicio.consultarDetalleOperacionServicio(registrosList.get(i)[5].toString()));

			compu.setNombreComputo(registrosList.get(i)[6].toString());
			compu.setSerialMonitor(registrosList.get(i)[7].toString());
			compu.setSerialMouse(registrosList.get(i)[8].toString());
			compu.setSerialTeclado(registrosList.get(i)[9].toString());
			compu.setDireccionIp(registrosList.get(i)[10].toString());

			listComputador.add(compu);

		}

		return listComputador;
	}

	@Override
	public List<Computador> consultarComputadoresNuevosSeleccionLista() throws Exception {
		List<Computador> listComputador = new ArrayList<Computador>();

		String query = "select compu.ID_COMPUTADOR,compu.NOMBRE_COMPUTO,compu.SERIAL__MONITOR,compu.SERIAL_MOUSE,compu.SERIAL_TECLADO, "
				+ "compu.ID_MODELO, compu.ID_TIPO, compu.ID_SISTEMA, " + "compu.ID_CARACTERISTICAS, compu.ID_SERVICIO, "
				+ "compu.DIRECCION_IP, compu.MAC, compu.ID_USUARIO_REG, " + "compu.ID_USUARIO_MOD, "
				+ "compu.ID_ESTADO_COMPU, compu.ID_ESTADO from COMPUTADOR compu "
				+ "LEFT join DETALLE_LISTA_COMPUTO deta on compu.ID_COMPUTADOR=deta.ID_COMPUTADOR where compu.ID_UNIDAD is null and compu.ID_ESTADO_COMPU='1' ";

		HashMap parametros = new HashMap();

		List<Object[]> registrosList = sbFacade.executeNativeQuery(query, parametros);
		Computador compu = null;

		for (int i = 0; i < registrosList.size(); i++) {

			compu = new Computador();

			compu.setIdComputador(Integer.parseInt(registrosList.get(i)[0].toString()));
			compu.setNombreComputo(registrosList.get(i)[1].toString());
			compu.setSerialMonitor(registrosList.get(i)[2].toString());
			compu.setSerialMouse(registrosList.get(i)[3].toString());
			compu.setSerialTeclado(registrosList.get(i)[4].toString());
			compu.setModeloComputo(sBModeloComputo.consultarDetalleModeloComputo(registrosList.get(i)[5].toString()));
			compu.setTipoComputo(sBTipoComputo.consultarDetalleTipoComputo(registrosList.get(i)[6].toString()));
			compu.setSistemaOperativo(
					sBSistemaOperativo.consultarDetalleSistemaOperativo(registrosList.get(i)[7].toString()));
			compu.setCaracteristicasComputo(sBCaracteristicasComputo
					.consultarDetalleCaracteristicasComputo(registrosList.get(i)[8].toString()));

			compu.setOperacionServicio(
					sBOperacionServicio.consultarDetalleOperacionServicio(registrosList.get(i)[9].toString()));

			compu.setDireccionIp(registrosList.get(i)[10].toString());
			compu.setMac(registrosList.get(i)[11].toString());
			compu.setIdUsuarioReg(Integer.parseInt(registrosList.get(i)[12].toString()));
			compu.setIdUsuarioMod(Integer.parseInt(registrosList.get(i)[13].toString()));
			compu.setIdEstadoCompu(Integer.parseInt(registrosList.get(i)[14].toString()));
			compu.setIdEstado(Integer.parseInt(registrosList.get(i)[15].toString()));
			listComputador.add(compu);

		}

		return listComputador;
	}

	@Override
	public List<Computador> consultarComputadoresNuevosUES() throws Exception {
		String query = "SELECT u FROM Computador u where u.idEstado='1' " + "and u.idEstadoCompu='1' "
				+ "and u.unidadEstrategicaServicio != null ";

		List<Computador> listComputador = sbFacade.executeQuery(query, null);

		return listComputador;
	}

}
