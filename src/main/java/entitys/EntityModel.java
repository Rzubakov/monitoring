package entitys;
import java.io.Serializable;


import javax.persistence.*;
@MappedSuperclass
public abstract class EntityModel implements Serializable {

	private static final long serialVersionUID = 7969446196353287071L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
} 
