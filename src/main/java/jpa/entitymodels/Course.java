package jpa.entitymodels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cId;
	private String cName;
	private String cInstuctorName;	
	
	public Course() {	
	}
	
	public Course(int cId, String cName, String cInstuctorName) {
		super();
		this.cId = cId;
		this.cName = cName;
		this.cInstuctorName = cInstuctorName;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcInstuctorName() {
		return cInstuctorName;
	}

	public void setcInstuctorName(String cInstuctorName) {
		this.cInstuctorName = cInstuctorName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cId;
		result = prime * result + ((cInstuctorName == null) ? 0 : cInstuctorName.hashCode());
		result = prime * result + ((cName == null) ? 0 : cName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (cId != other.cId)
			return false;
		if (cInstuctorName == null) {
			if (other.cInstuctorName != null)
				return false;
		} else if (!cInstuctorName.equals(other.cInstuctorName))
			return false;
		if (cName == null) {
			if (other.cName != null)
				return false;
		} else if (!cName.equals(other.cName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Course [cId=" + cId + ", cName=" + cName + ", cInstuctorName=" + cInstuctorName + "]";
	}	
}
