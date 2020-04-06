package eportfolium.com.karuta.model.bean;
// Generated Apr 3, 2020 1:11:44 AM by Hibernate Tools 5.2.12.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Usersfolder generated by hbm2java
 */
@Entity
@Table(name = "usersfolder")
public class Usersfolder implements java.io.Serializable {

	private Long id;
	private Usersfolder parentFolder;
	private int active;
	private String code;
	private String label;
	private Date modifDate;
	private Set<Credential> users = new HashSet<Credential>(0);
	private Set<Usersfolder> folders = new HashSet<Usersfolder>(0);

	public Usersfolder() {
	}

	public Usersfolder(Long id) {
		this.id = id;
	}

	public Usersfolder(int active, String code) {
		this.active = active;
		this.code = code;
	}
/*
	public Usersfolder(Usersfolder usersfolder, int active, String code, String label, Date modifDate,
			Set<Credential> users, Set<Usersfolder> usersfolders) {
		this.parentFolder = usersfolder;
		this.active = active;
		this.code = code;
		this.label = label;
		this.modifDate = modifDate;
		this.users = users;
		this.folders = usersfolders;
	}
*/
	public Usersfolder(int active, String code, String label) {
		this.active = active;
		this.code = code;
		this.label = label;
	}

	public Usersfolder(String code, String label) {
		this.code = code;
		this.label = label;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentid")
	public Usersfolder getParentFolder() {
		return this.parentFolder;
	}

	public void setParentFolder(Usersfolder usersfolder) {
		this.parentFolder = usersfolder;
	}

	@Column(name = "active", nullable = false)
	public int getActive() {
		return this.active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@Column(name = "code", nullable = false)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "label")
	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modif_date")
	public Date getModifDate() {
		return this.modifDate;
	}

	public void setModifDate(Date modifDate) {
		this.modifDate = modifDate;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usersfolder_user", joinColumns = {
			@JoinColumn(name = "fid", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "userid", nullable = false, updatable = false) })
	public Set<Credential> getUsers() {
		return this.users;
	}

	public void setUsers(Set<Credential> users) {
		this.users = users;
	}

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentFolder")
	public Set<Usersfolder> getFolders() {
		return this.folders;
	}

	public void setFolders(Set<Usersfolder> folders) {
		this.folders = folders;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Usersfolder other = (Usersfolder) obj;
		return Objects.equals(id, other.id);
	}

}
