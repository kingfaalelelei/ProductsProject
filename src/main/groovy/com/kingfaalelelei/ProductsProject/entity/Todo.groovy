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
import java.awt.TexturePaintContext

@Validated
@Entity
@Table(name = "todos")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class Todo implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    Integer getId() {
        return id
    }

    void setId(Integer id) {
        this.id = id
    }

    Boolean getCompleted() {
        return completed
    }

    void setCompleted(Boolean completed) {
        this.completed = completed
    }

    String getTitle() {
        return title
    }

    void setTitle(String title) {
        this.title = title
    }
    @NotBlank
    @Size(max = 255)
    private String title;

    private Boolean completed
}
