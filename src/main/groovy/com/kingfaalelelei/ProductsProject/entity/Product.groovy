package com.kingfaalelelei.ProductsProject.entity

import lombok.Getter
import lombok.Setter
import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.springframework.validation.annotation.Validated

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Validated
@Entity
@Table(name = "products")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id

    @NotBlank
    @Size(max = 255)
    private String name

    @NotBlank
    @Size(max = 255)
    private String description

    @NotBlank
    private BigDecimal price

    @NotBlank
    @Size(max = 255)
    private String image_title

    @NotBlank
    @Size(max = 255)
    private String image

    Integer getId() {
        return id
    }

    void setId(Integer id) {
        this.id = id
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getDescription() {
        return description
    }

    void setDescription(String description) {
        this.description = description
    }

    BigDecimal getPrice() {
        return price
    }

    void setPrice(BigDecimal price) {
        this.price = price
    }

    String getImage_title() {
        return image_title
    }

    void setImage_title(String image_title) {
        this.image_title = image_title
    }

    String getImage() {
        return image
    }

    void setImage(String image) {
        this.image = image
    }
}

// id, name, description, price, image_title, image
