package com.psicovirtual.procesos.modelo.ejb.entity.inventario;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SISTEMA_OPERATIVO database table.
 * 
 */
@Entity
@Table(name="SISTEMA_OPERATIVO")
@NamedQuery(name="SistemaOperativo.findAll", query="SELECT s FROM SistemaOperativo s")
public class SistemaOperativo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_SISTEMA")
	private int idSistema;

	@Column(name="ID_ESTADO")
	private int idEstado;

	@Column(name="SISTEMA")
	private String sistema;

	@Column(name="TIPO_SISTEMA_OP")
	private String tipoSistemaOp;

	@Column(name="VERSION")
	private String version;

	//bi-directional many-to-one association to Computador
	@OneToMany(mappedBy="sistemaOperativo")
	private List<Computador> computadors;

	public SistemaOperativo() {
	}

	public int getIdSistema() {
		return this.idSistema;
	}

	public void setIdSistema(int idSistema) {
		this.idSistema = idSistema;
	}

	public int getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public String getSistema() {
		return this.sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public String getTipoSistemaOp() {
		return this.tipoSistemaOp;
	}

	public void setTipoSistemaOp(String tipoSistemaOp) {
		this.tipoSistemaOp = tipoSistemaOp;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<Computador> getComputadors() {
		return this.computadors;
	}

	public void setComputadors(List<Computador> computadors) {
		this.computadors = computadors;
	}

	public Computador addComputador(Computador computador) {
		getComputadors().add(computador);
		computador.setSistemaOperativo(this);

		return computador;
	}

	public Computador removeComputador(Computador computador) {
		getComputadors().remove(computador);
		computador.setSistemaOperativo(null);

		return computador;
	}

}