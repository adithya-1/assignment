package com.assignment.three.core;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "blogs")
@NamedQueries({
        @NamedQuery(name = "com.assignment.three.core.Blog.findAllBlogs",
                query = "SELECT B " +
                        "FROM Blog AS B"
                        ),
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Blog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="blogId")
    @JsonProperty
    private  Integer blogId;
    @Column(name="blogName")
    @JsonProperty
    private String blogName;
    @Column(name="blogContent")
    @JsonProperty
    private String blogContent;
    @Column(name="createdAt",columnDefinition = "default CURRENT_TIMESTAMP")
    @JsonProperty
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "authorId")
    @JsonProperty
    private  Author author;
}


