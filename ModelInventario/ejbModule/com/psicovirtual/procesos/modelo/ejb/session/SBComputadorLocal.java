package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Computador;

@Local
public interface SBComputadorLocal {

	public Computador crearComputador(Computador nuevo) throws Exception;

	public Computador actualizarComputador(Computador update) throws Exception;

	public Computador consultarDetalleComputador(String id) throws Exception;

	public List<Computador> consultarAllComputadorActivos() throws Exception;

	public List<Computador> consultarAllComputadorNuevos() throws Exception;

	public List<Computador> consultarComputadoresNuevosSeleccionLista() throws Exception;

	public List<Computador> consultarComputadoresNuevosUES() throws Exception;

	public List<Computador> consultarAllComputadoresBase() throws Exception;

	public List<Computador> consultarComputadoresNuevosParaRenovacion() throws Exception;

}
