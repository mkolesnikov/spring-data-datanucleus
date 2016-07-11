package mydomain.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Person")
public class Person extends BaseEntity<Long>
{
    private static final long serialVersionUID = 1L;

    private String name;
    
    @JoinColumn(name = "address_id")
    private Address address;

    public Person(long id, String name)
    {
        super(id);
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

}
