package anuar.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "values")
public class Value {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "specification_id")
    private Specification specifications;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product products;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Specification getSpecifications() {
        return specifications;
    }

    public void setSpecifications(Specification specifications) {
        this.specifications = specifications;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }
}