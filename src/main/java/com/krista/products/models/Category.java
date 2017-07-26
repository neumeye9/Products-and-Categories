package com.krista.products.models;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="categories")
public class Category {
	
    @Id
    @GeneratedValue
    private Long id;
    
    @Column
    @Size(min = 3, max = 45, message="Category name must be between 3 and 45 characters long")
    private String name;
    
    @Column
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date created_at;
    
    @Column
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date updated_at;
    
    
    @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(
		   name = "categories_products",
		   joinColumns = @JoinColumn(name="category_id"),
		   inverseJoinColumns = @JoinColumn(name= "product_id")
		   )
      
    
   private List<Product> products;
    
    public Category() {
        
    }
    
    public Category(String name) {
        this.name = name;
        this.created_at = new Date();
        this.updated_at = new Date();    
    }

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

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	@PrePersist
    protected void onCreate(){
            this.created_at = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
            this.updated_at = new Date();
    }

}
